package service.applicationservice.wifi;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Test;
import service.applicationservice.wifi.WifiSaveService;

class WifiSaveServiceImplTest {

    private static final WifiSaveService wifiSaveService;
    static {
        wifiSaveService = InstanceFactory.WifiSaveServiceFactory.getInstance();
    }

    @Test
    void test_getOpenApiWifiListAndSave() {
        wifiSaveService.getOpenApiWifiListAndSave();
    }
}