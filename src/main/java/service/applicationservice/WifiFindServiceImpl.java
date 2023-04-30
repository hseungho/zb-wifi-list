package service.applicationservice;

import global.config.InstanceFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import service.controller.dto.HistorySaveRequestDto;
import service.controller.dto.WifiDistanceResponseDto;
import service.entity.History;
import service.entity.Wifi;
import service.repository.HistoryRepository;
import service.repository.WifiRepository;
import service.repository.base.transaction.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WifiFindServiceImpl implements WifiFindService {

    private final WifiRepository wifiRepository;
    private final HistoryRepository historyRepository;
    public WifiFindServiceImpl() {
        wifiRepository = InstanceFactory.WifiRepositoryFactory.getInstance();
        historyRepository = InstanceFactory.HistoryRepositoryFactory.getInstance();
    }

    @Override
    @Transactional
    public List<WifiDistanceResponseDto> getDistanceWifiList(Double lat, Double lnt) {
        List<Wifi> wifis = wifiRepository.findAll();

        if (wifis.isEmpty()) {
            System.err.println("NO Wifi data!!");
            return null;
        }

        List<WifiDistanceResponseDto> distanceDtos = wifis.stream()
                .filter(wifi -> wifi.getLat() > 0 && wifi.getLnt() > 0)
                .map(wifi -> WifiDistanceResponseDto.of(wifi.calcDistance(lat, lnt), wifi))
                .sorted(Comparator.comparing(WifiDistanceResponseDto::getDistance))
                .collect(Collectors.toList());

        historyRepository.save(History.of(new HistorySaveRequestDto(lat, lnt)));

        return distanceDtos;
    }

    @Override
    public WifiDistanceResponseDto getWifiInfo(String id) {
        Wifi wifi = wifiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NO Data"));

        return WifiDistanceResponseDto.of(0.0000, wifi);
    }


}
