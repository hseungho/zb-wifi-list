package global.config;

import service.applicationservice.*;
import service.repository.*;

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

    //////////////////////////////////////////////////////////////////////////////
    // Repository Factory
    public static class WifiRepositoryFactory {
        public static WifiRepository getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final WifiRepository INSTANCE = new WifiRepositoryImpl();
        }
    }

    public static class HistoryRepositoryFactory {
        public static HistoryRepository getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final HistoryRepository INSTANCE = new HistoryRepositoryImpl();
        }
    }

    public static class BookmarkRepositoryFactory {
        public static BookmarkRepository getInstance() {
            return LazyHolder.INSTANCE;
        }
        private static class LazyHolder {
            private static final BookmarkRepository INSTANCE = new BookmarkRepositoryImpl();
        }
    }

}
