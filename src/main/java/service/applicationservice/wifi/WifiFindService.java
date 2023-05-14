package service.applicationservice.wifi;

import service.controller.dto.WifiDistanceResponseDto;
import service.controller.dto.WifiExistsResponseDto;
import service.controller.dto.WifiNearResponseDto;

public interface WifiFindService {

    WifiNearResponseDto getDistanceWifiList(Double lat, Double lnt, int page);

    WifiNearResponseDto getDistanceWifiList(Double lat, Double lnt);

    WifiDistanceResponseDto getWifiInfo(String id, Double lat, Double lnt);

    WifiExistsResponseDto existsAnyWifiData();

}
