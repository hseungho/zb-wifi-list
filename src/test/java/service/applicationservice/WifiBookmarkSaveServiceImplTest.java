package service.applicationservice;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Test;
import service.entity.WifiBookmark;
import service.repository.WifiBookmarkRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WifiBookmarkSaveServiceImplTest {

    private final WifiBookmarkSaveService wifiBookmarkSaveService = InstanceFactory.WifiBookmarkSaveServiceFactory.getInstance();
    private final WifiBookmarkRepository wifiBookmarkRepository = InstanceFactory.WifiBookmarkRepositoryFactory.getInstance();

    @Test
    void test_saveWifiBookmark() {
        String wifiId = "---EP000001";
        Long bookmarkId = 1L;

        wifiBookmarkSaveService.saveWifiBookmark(wifiId, bookmarkId);

        List<WifiBookmark> wifiBookmarks = wifiBookmarkRepository.findAll();
        System.out.println(wifiBookmarks);
    }

}