package service.applicationservice.wifi;

import global.config.InstanceFactory;
import service.repository.WifiRepository;

public class WifiDeleteServiceImpl implements WifiDeleteService {

    private final WifiRepository wifiRepository;

    public WifiDeleteServiceImpl() {
        wifiRepository = InstanceFactory.WifiRepositoryFactory.getInstance();
    }

    @Override
    public void deleteAllWifis() {
        wifiRepository.deleteAll();
    }
}
