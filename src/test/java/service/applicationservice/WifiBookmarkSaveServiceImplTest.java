package service.applicationservice;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WifiBookmarkSaveServiceImplTest {

    private static final WifiBookmarkSaveService wifiBookmarkSaveService;
    static {
        wifiBookmarkSaveService = InstanceFactory.WifiBookmarkSaveServiceFactory.getInstance();
    }

    @Test
    void test_saveWifiBookmark() {
        String wifiId = "---EP000001";
        Long bookmarkId = 1L;

        wifiBookmarkSaveService.saveWifiBookmark(wifiId, bookmarkId);

    }

}