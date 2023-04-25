package service.repository.base;

import global.config.DBConfig;

import java.sql.*;

public class Repository {
    protected Connection connect = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;

    public void connect() {
        try {
            Class.forName(DBConfig.SQLITE_DRIVER);
            this.connect = DriverManager.getConnection(DBConfig.SQLITE_FILE_DB_URL);
            this.connect.setAutoCommit(DBConfig.OPT_AUTO_COMMIT);

            System.out.println("DB Connected");

        } catch (SQLException e) {
            System.out.println("ERROR:: DB CONNECTION");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR:: JDBC DRIVER");
        }
    }

    public void save(String query) {
        try {
            statement = connect.createStatement();
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet find(String query) {
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public boolean update(String query) {
        int resultRows = executeUpdate(query);
        return resultRows > 0;
    }

    private int executeUpdate(String query) {
        int result = 0;
        try {
            statement = connect.createStatement();
            result = statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean delete(String query) {
        int resultRows = executeUpdate(query);
        return resultRows > 0;
    }

    public void close() {
        if (resultSet != null) {
            try{
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connect != null) {
            try{
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("DB Closed");
    }
}
