package service.repository.base;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T, ID> implements CrudRepository<T, ID> {

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

    protected abstract Connection getTxConnection();

    public abstract Optional<T> findById(ID id);

    public abstract List<T> findAll();

    public abstract T save(T entity);

    public abstract T update(T entity);

    public abstract void delete(T entity);

    public abstract boolean existsById(ID id);

    public Connection getConnection() {
        return this.connectionPool.getConnection();
    }

    public ConnectionPool getConnectionPool() {
        return this.connectionPool;
    }

    protected void returnConnection(Connection connection) {
        this.connectionPool.releaseConnection(connection);
    }

    protected void executeUpdate(PreparedStatement preparedStatement, Object... values) {
        try {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected ResultSet executeQuery(PreparedStatement preparedStatement, Object... conditions) {
        try {
            for (int i = 0; i < conditions.length; i++) {
                preparedStatement.setObject(i + 1, conditions[i]);
            }
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean executeExistsById(String query, Object id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            resultSet = executeQuery(preparedStatement, id);
            if (resultSet.next()) {
                exists = resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement, resultSet);
        }
        return exists;
    }

    protected void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void close(Statement statement, ResultSet resultSet) {
        close(statement);
        close(resultSet);
    }
}
