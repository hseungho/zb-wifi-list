package service.repository.base.transaction;

import service.repository.base.BaseRepository;
import service.repository.base.ConnectionPool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

public class TransactionalProxy implements InvocationHandler {

    private final Object target;
    private final ConnectionPool connectionPool;
    private Connection connection;

    public TransactionalProxy(Object target) {
        this.target = target;
        this.connectionPool = ((BaseRepository<?, ?>) target).getConnectionPool();
    }

    public Connection setConnection() {
        this.connection = this.connectionPool.getConnection();
        return this.connection;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Connection connection = null;
        try {
            connection = setConnection();
            connection.setAutoCommit(false);

            Object result = method.invoke(target, args);

            connection.commit();
            return result;
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);
                this.connectionPool.releaseConnection(connection);
            }
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}

