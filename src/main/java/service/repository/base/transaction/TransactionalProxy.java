package service.repository.base.transaction;

import service.repository.base.BaseRepository;
import service.repository.base.ConnectionPool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * Service 의 실행 메소드 단위로 트랜잭션을 구현하고 싶어서<br>
 * Spring 의 @Transactional 을 구현하기 위해 만든 클래스인데,<br>
 * 테스트 결과, 실제로 한 메소드 안에서 한 커넥션을 가지고 트랜잭션이 구현되진 않음.<br>
 * <br>
 * 다만 SQLite의 Busy wait 문제는 해결하였기에 사용함.
 */
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

    public Connection getConnection() {
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
                this.connectionPool.releaseConnection(connection);
            }
        }
    }

}

