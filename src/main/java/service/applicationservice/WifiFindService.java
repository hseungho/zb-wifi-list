package service.applicationservice;

import service.controller.dto.WifiDistanceDto;

import java.util.List;

public interface WifiFindService {

    List<WifiDistanceDto> getDistanceWifiList(Double lat, Double lnt);

}
