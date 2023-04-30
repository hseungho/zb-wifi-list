package service.repository.base;

import global.config.DBConfig;

import java.sql.*;

public class Repository {
    protected Connection connect = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;

    public void connect(String dbName) {
        try {
            Class.forName(DBConfig.SQLITE_DRIVER);
            this.connect = DriverManager.getConnection(DBConfig.SQLITE_FILE_DB_URL);
            this.connect.setAutoCommit(DBConfig.OPT_AUTO_COMMIT);

            System.out.println(dbName+" DB Connected");

        } catch (SQLException e) {
            System.out.println("ERROR:: DB CONNECTION");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR:: JDBC DRIVER");
        }
    }

    protected void initEachTable(String query) {
        try {
            statement = connect.createStatement();
            statement.executeUpdate(query);
            connect.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(String query) {
        try {
            statement = connect.createStatement();
            statement.executeUpdate(query);
            connect.commit();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet findQuery(String query) {
        try {
            PreparedStatement preparedStatement = connect.prepareStatement(query);
            resultSet = executeQuery(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public boolean update(String query) {
        int resultRows = executeUpdate(query);
        return resultRows > 0;
    }

    protected ResultSet executeQuery(String query, Object... conditions) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(query);

            for (int i = 1; i <= conditions.length; i++) {
                preparedStatement.setString(i, String.valueOf(conditions[i-1]));
            }
            resultSet = executeQuery(preparedStatement);
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ResultSet executeQuery(PreparedStatement statement) {
        try {
            ResultSet rs = statement.executeQuery();
            connect.commit();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected int executeUpdate(PreparedStatement statement) {
        try {
            int executeUpdate = statement.executeUpdate();
            connect.commit();
            return executeUpdate;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public boolean deleteById(String query, Long id) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connect.prepareStatement(query);

            preparedStatement.setObject(1, id);

            return delete(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public boolean delete(PreparedStatement statement) {
        int resultRows = executeUpdate(statement);
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
