package service.applicationservice;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Test;
import service.controller.dto.WifiDistanceResponseDto;

import java.util.List;

class WifiFindServiceImplTest {

    private static final WifiFindService wifiFindService;
    static {
        wifiFindService = InstanceFactory.WifiFindServiceFactory.getInstance();
    }

    @Test
    void test_getDistanceWifiList() {
        Double lat = 37.5544069;
        Double lnt = 126.8998666;
        List<WifiDistanceResponseDto> dtos = wifiFindService.getDistanceWifiList(lat, lnt);

        for (int i = 0; i < 20; i++) {
            System.out.println(dtos.get(i));
        }
    }

}