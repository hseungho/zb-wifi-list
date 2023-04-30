package global.config;

import service.applicationservice.*;
import service.repository.*;
import service.repository.base.ConnectionPool;
import service.repository.base.transaction.TransactionalProxy;

import java.lang.reflect.Proxy;
import java.sql.SQLException;

public class InstanceFactory {

    //////////////////////////////////////////////////////////////////////////////
    // Service Factory
    public static class WifiSaveServiceFactory {
        public static WifiSaveService getInstance() {
            return LazyHolder.INSTANCE;
        }

        private static class LazyHolder {
            private static final WifiSaveService INSTANCE = new WifiSaveServiceImpl();
        }
    }

    public static class WifiFindServiceFactory {
        public static WifiFindService getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final WifiFindService INSTANCE = new WifiFindServiceImpl();
        }
    }

    public static class HistorySaveServiceFactory {
        public static HistorySaveService getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final HistorySaveService INSTANCE = new HistorySaveServiceImpl();
        }
    }

    public static class HistoryFindServiceFactory {
        public static HistoryFindService getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final HistoryFindService INSTANCE = new HistoryFindServiceImpl();
        }
    }

    public static class BookmarkSaveServiceFactory {
        public static BookmarkSaveService getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final BookmarkSaveService INSTANCE = new BookmarkSaveServiceImpl();
        }
    }

    public static class WifiBookmarkSaveServiceFactory {
        public static WifiBookmarkSaveService getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final WifiBookmarkSaveService INSTANCE = new WifiBookmarkSaveServiceImpl();
        }
    }

    public static class WifiBookmarkFindServiceFactory {
        public static WifiBookmarkFindService getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final WifiBookmarkFindService INSTANCE = new WifiBookmarkFindServiceImpl();
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    // Repository Factory
    public static class ConnectionPoolFactory {
        public static ConnectionPool getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final ConnectionPool INSTANCE;
            static {
                try {
                    INSTANCE = new ConnectionPool(DBConfig.OPT_CONNECTION_POOL_MAX);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static class WifiRepositoryFactory {
        public static WifiRepository getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final WifiRepository INSTANCE = (WifiRepository) Proxy.newProxyInstance(
                    WifiRepositoryImpl.class.getClassLoader(),
                    new Class[] { WifiRepository.class },
                    new TransactionalProxy(new WifiRepositoryImpl(ConnectionPoolFactory.getInstance()))
            );
        }
    }

    public static class HistoryRepositoryFactory {
        public static HistoryRepository getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final HistoryRepository INSTANCE = (HistoryRepository) Proxy.newProxyInstance(
                    HistoryRepositoryImpl.class.getClassLoader(),
                    new Class[] { HistoryRepository.class },
                    new TransactionalProxy(new HistoryRepositoryImpl(ConnectionPoolFactory.getInstance()))
            );
        }
    }

    public static class BookmarkRepositoryFactory {
        public static BookmarkRepository getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final BookmarkRepository INSTANCE = (BookmarkRepository) Proxy.newProxyInstance(
                    BookmarkRepositoryImpl.class.getClassLoader(),
                    new Class[] { BookmarkRepository.class },
                    new TransactionalProxy(new BookmarkRepositoryImpl(ConnectionPoolFactory.getInstance()))
            );
        }
    }

    public static class WifiBookmarkRepositoryFactory {
        public static WifiBookmarkRepository getInstance() {
            return LazyHolder.INSTANCE;

        }
        private static class LazyHolder {
            private static final WifiBookmarkRepository INSTANCE = (WifiBookmarkRepository) Proxy.newProxyInstance(
                    WifiBookmarkRepositoryImpl.class.getClassLoader(),
                    new Class[] { WifiBookmarkRepository.class },
                    new TransactionalProxy(new WifiBookmarkRepositoryImpl(ConnectionPoolFactory.getInstance()))
            );
        }
    }



}
