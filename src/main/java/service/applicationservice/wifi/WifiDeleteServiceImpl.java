package service.applicationservice.wifi;

import global.config.InstanceFactory;
import service.repository.WifiBookmarkRepository;
import service.repository.WifiRepository;

public class WifiDeleteServiceImpl implements WifiDeleteService {

    private final WifiRepository wifiRepository;
    private final WifiBookmarkRepository wifiBookmarkRepository;
    public WifiDeleteServiceImpl() {
        wifiRepository = InstanceFactory.WifiRepositoryFactory.getInstance();
        wifiBookmarkRepository = InstanceFactory.WifiBookmarkRepositoryFactory.getInstance();
    }

    @Override
    public void deleteAllWifis() {
        wifiRepository.deleteAll();
    }
}
