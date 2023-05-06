package service.applicationservice.wifi;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.controller.dto.WifiDistanceResponseDto;
import service.controller.dto.WifiNearResponseDto;
import service.entity.History;
import service.repository.HistoryRepository;
import service.repository.WifiRepository;

import java.util.List;

class WifiFindServiceImplTest {

    private final WifiSaveService wifiSaveService = InstanceFactory.WifiSaveServiceFactory.getInstance();
    private final WifiFindService wifiFindService = InstanceFactory.WifiFindServiceFactory.getInstance();
    private final WifiRepository wifiRepository = InstanceFactory.WifiRepositoryFactory.getInstance();
    private final HistoryRepository historyRepository = InstanceFactory.HistoryRepositoryFactory.getInstance();

    @Test
    void test_getDistanceWifiList() {
        wifiSaveService.getOpenApiWifiListAndSave();

        Double lat = 37.5544069;
        Double lnt = 126.8998666;
        WifiNearResponseDto dtos = wifiFindService.getDistanceWifiList(lat, lnt, 1);

        Assertions.assertNotNull(dtos);
        Assertions.assertNotEquals(0, dtos.getTotalItems());
        Assertions.assertFalse(dtos.getWifiList().isEmpty());

        List<History> histories = historyRepository.findAll();
        Assertions.assertNotNull(histories);
        Assertions.assertFalse(histories.isEmpty());

        histories.forEach(historyRepository::delete);
    }

    @Test
    void test_getWifiInfo() {
        wifiSaveService.getOpenApiWifiListAndSave();

        String id = "---EP000001";
        Double lat = 37.5544069;
        Double lnt = 126.8998666;

        WifiDistanceResponseDto wifiInfo = wifiFindService.getWifiInfo(id, lat, lnt);
        Assertions.assertNotNull(wifiInfo);
        Assertions.assertEquals(id, wifiInfo.getId());
    }

}