package service.applicationservice;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WifiSaveServiceImplTest {

    private static final WifiSaveService wifiSaveService;
    static {
        wifiSaveService = WifiSaveServiceImpl.getInstance();
    }

    @Test
    void test_getOpenApiWifiListAndSave() {
        wifiSaveService.getOpenApiWifiListAndSave();
    }
}