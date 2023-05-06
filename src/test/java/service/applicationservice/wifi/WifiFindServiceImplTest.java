package service.applicationservice.wifi;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.controller.dto.WifiDistanceResponseDto;
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
        List<WifiDistanceResponseDto> dtos = wifiFindService.getDistanceWifiList(lat, lnt);

        Assertions.assertNotNull(dtos);
        Assertions.assertFalse(dtos.isEmpty());

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