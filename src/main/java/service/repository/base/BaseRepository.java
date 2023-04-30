package service.repository.base;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T, ID> {

    private final ConnectionPool connectionPool;
    public BaseRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    protected void DDL(String query) {
        Connection connection = this.connectionPool.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            this.connectionPool.releaseConnection(connection);
            close(preparedStatement, null);
        }
    }

    public abstract Optional<T> findById(ID id) throws SQLException;

    public abstract List<T> findAll() throws SQLException;

    public abstract void save(T entity) throws SQLException;

    public abstract void update(T entity) throws SQLException;

    public abstract void delete(T entity) throws SQLException;

    public Connection getConnection() {
        return this.connectionPool.getConnection();
    }

    public ConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    protected void returnConnection(Connection connection) {
        this.connectionPool.releaseConnection(connection);
    }

    protected void close(Statement statement, ResultSet resultSet) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
