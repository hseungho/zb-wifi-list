package service.applicationservice;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Test;
import service.controller.dto.WifiBookmarkResponseDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WifiBookmarkFindServiceTest {

    private final WifiBookmarkFindService wifiBookmarkFindService = InstanceFactory.WifiBookmarkFindServiceFactory.getInstance();

    @Test
    void test_getWifiBookmarkList() {
        List<WifiBookmarkResponseDto> wifiBookmarkList = wifiBookmarkFindService.getWifiBookmarkList();
        wifiBookmarkList.forEach(System.out::println);
    }

}