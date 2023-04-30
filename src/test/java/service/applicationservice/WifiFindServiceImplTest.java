package service.applicationservice;

import org.junit.jupiter.api.Test;
import service.controller.dto.WifiDistanceDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WifiFindServiceImplTest {

    private static final WifiFindService wifiFindService;
    static {
        wifiFindService = WifiFindServiceImpl.getInstance();
    }

    @Test
    void test_getDistanceWifiList() {
        Double lat = 37.5544069;
        Double lnt = 126.8998666;
        List<WifiDistanceDto> dtos = wifiFindService.getDistanceWifiList(lat, lnt);

        for (int i = 0; i < 20; i++) {
            System.out.println(dtos.get(i));
        }
    }

}