package service.applicationservice;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Test;
import service.repository.WifiBookmarkRepository;

import java.util.ArrayList;
import java.util.List;

class WifiBookmarkSaveServiceImplTest {

    private final WifiBookmarkSaveService wifiBookmarkSaveService = InstanceFactory.WifiBookmarkSaveServiceFactory.getInstance();
    private final WifiBookmarkRepository wifiBookmarkRepository = InstanceFactory.WifiBookmarkRepositoryFactory.getInstance();

    @Test
    void test_saveWifiBookmark() {
        String wifiId = "---EP000001";
        Long bookmarkId = 1L;

        wifiBookmarkSaveService.saveWifiBookmark(wifiId, bookmarkId);
    }

    @Test
    void test_save_five_wifiBookmark() {
        List<String> wifiIds = new ArrayList<>();
        wifiIds.add("---EP000001");
        wifiIds.add("---EP000003");
        wifiIds.add("---EP000004");
        wifiIds.add("---EP000008");
        wifiIds.add("---EP000013");

        List<Long> bookmarkIds = new ArrayList<>();
        bookmarkIds.add(1L);
        bookmarkIds.add(2L);
        bookmarkIds.add(3L);
        bookmarkIds.add(4L);
        bookmarkIds.add(5L);

        for (int i = 0; i < 5; i++) {
            wifiBookmarkSaveService.saveWifiBookmark(wifiIds.get(i), bookmarkIds.get(i));
        }
    }
}