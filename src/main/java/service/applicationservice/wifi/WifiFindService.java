package service.applicationservice.wifi;

import service.controller.dto.WifiDistanceResponseDto;

import java.util.List;

public interface WifiFindService {

    List<WifiDistanceResponseDto> getDistanceWifiList(Double lat, Double lnt);

    WifiDistanceResponseDto getWifiInfo(String id);

}
