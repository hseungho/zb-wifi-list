package service.repository.base;

import global.config.DBConfig;
import org.jetbrains.annotations.NotNull;
import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private final BlockingQueue<Connection> pool;

    public ConnectionPool() throws SQLException, ClassNotFoundException {
        int poolSize = DBConfig.OPT_CONNECTION_POOL_MAX;

        pool = new ArrayBlockingQueue<>(poolSize);
        Class.forName(DBConfig.SQLITE_DRIVER);

        String url = DBConfig.OPT_USING_FILE ? DBConfig.SQLITE_FILE_DB_URL : DBConfig.SQLITE_MEMORY_DB_URL;

        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);

        for (int i = 0; i < poolSize; i++) {
            Connection connection = createConnection(url, config);
            pool.add(connection);
        }
    }

    @NotNull
    public static Connection createConnection(String url, SQLiteConfig config) throws SQLException {
        Connection connection = DriverManager.getConnection(url, config.toProperties());
        connection.setAutoCommit(DBConfig.OPT_AUTO_COMMIT);
        return connection;
    }

    public Connection getConnection() {
        try {
            return getAvailableConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void releaseConnection(Connection connection) {
        try {
            pool.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isDatabaseFileAvailable(Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement("PRAGMA schema_version;");
            statement.executeQuery();
            statement.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    private Connection getAvailableConnection() throws SQLException {
        try {
            long startTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - startTime < DBConfig.MAX_WAIT_TIME) {
                Connection connection = pool.take();
                if (isDatabaseFileAvailable(connection)) {
                    return connection;
                } else {
                    pool.put(connection);
                }

                try {
                    Thread.sleep(DBConfig.WAIT_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        throw new SQLException("Timeout while waiting for available connection");
    }
}
