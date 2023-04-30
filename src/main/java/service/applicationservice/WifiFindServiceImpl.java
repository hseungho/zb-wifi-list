package service.applicationservice;

import global.config.InstanceFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import service.controller.dto.WifiDistanceDto;
import service.entity.Wifi;
import service.repository.WifiRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WifiFindServiceImpl implements WifiFindService {

    private final WifiRepository wifiRepository;

    public WifiFindServiceImpl() {
        wifiRepository = InstanceFactory.WifiRepositoryFactory.getInstance();
    }

    @Override
    public List<WifiDistanceDto> getDistanceWifiList(Double lat, Double lnt) {
        List<Wifi> wifis = wifiRepository.findAll();

        if (wifis.isEmpty()) {

        }

        return wifis.stream()
                .filter(wifi -> wifi.getLat() > 0 && wifi.getLnt() > 0)
                .map(wifi -> WifiDistanceDto.of(wifi.calcDistance(lat, lnt), wifi))
                .sorted(Comparator.comparing(WifiDistanceDto::getDistance))
                .collect(Collectors.toList());
    }
}
