package service.applicationservice.wifi;

import service.controller.dto.WifiDistanceResponseDto;
import service.controller.dto.WifiExistsResponseDto;

import java.util.List;

public interface WifiFindService {

    List<WifiDistanceResponseDto> getDistanceWifiList(Double lat, Double lnt);

    WifiDistanceResponseDto getWifiInfo(String id, Double lat, Double lnt);

    WifiExistsResponseDto existsAnyWifiData();

}
