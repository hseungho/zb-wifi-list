package service.applicationservice.wifibookmark;

import global.config.InstanceFactory;
import service.repository.WifiBookmarkRepository;

public class WifiBookmarkDeleteServiceImpl implements WifiBookmarkDeleteService {

    private final WifiBookmarkRepository wifiBookmarkRepository;
    public WifiBookmarkDeleteServiceImpl() {
        wifiBookmarkRepository = InstanceFactory.WifiBookmarkRepositoryFactory.getInstance();
    }

    @Override
    public void deleteWifiBookmark(Long id) {
        wifiBookmarkRepository.deleteById(id);
    }

}
