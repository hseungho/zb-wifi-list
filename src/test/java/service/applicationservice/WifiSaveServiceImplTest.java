package service.applicationservice;

import global.config.InstanceFactory;

class WifiSaveServiceImplTest {

    private static final WifiSaveService wifiSaveService;
    static {
        wifiSaveService = InstanceFactory.WifiSaveServiceFactory.getInstance();
    }

//    @Test
    void test_getOpenApiWifiListAndSave() {
        wifiSaveService.getOpenApiWifiListAndSave();
    }
}