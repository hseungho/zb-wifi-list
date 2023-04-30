package global.config;

import service.applicationservice.WifiFindService;
import service.applicationservice.WifiFindServiceImpl;
import service.applicationservice.WifiSaveService;
import service.applicationservice.WifiSaveServiceImpl;
import service.repository.HistoryRepository;
import service.repository.HistoryRepositoryImpl;
import service.repository.WifiRepository;
import service.repository.WifiRepositoryImpl;

public class InstanceFactory {

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

}
