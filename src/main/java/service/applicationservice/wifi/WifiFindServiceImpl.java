package service.applicationservice.wifi;

import global.config.InstanceFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import service.controller.dto.HistorySaveRequestDto;
import service.controller.dto.WifiDistanceResponseDto;
import service.controller.dto.WifiExistsResponseDto;
import service.controller.dto.WifiNearResponseDto;
import service.entity.History;
import service.entity.Wifi;
import service.repository.HistoryRepository;
import service.repository.WifiRepository;

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
    public WifiNearResponseDto getDistanceWifiList(Double lat, Double lnt, int page) {
        List<Wifi> wifis = wifiRepository.findAll();
        if (wifis.isEmpty()) {
            return null;
        }

        List<WifiDistanceResponseDto> distanceDtos = wifis.stream()
                .filter(wifi -> wifi.getLat() > 0 && wifi.getLnt() > 0)
                .map(wifi -> WifiDistanceResponseDto.of(wifi.calcDistance(lat, lnt), wifi))
                .sorted(Comparator.comparing(WifiDistanceResponseDto::getDistance))
                .collect(Collectors.toList());

        int totalSize = distanceDtos.size();

        int size = 20;
        int endIdx = Math.min(page*size, totalSize);
        int startIdx = endIdx-size;

        distanceDtos = distanceDtos.subList(startIdx, endIdx);

        historyRepository.save(History.of(new HistorySaveRequestDto(lat, lnt)));

        return WifiNearResponseDto.of(totalSize, distanceDtos);
    }

    @Override
    public WifiNearResponseDto getDistanceWifiList(Double lat, Double lnt) {
        List<Wifi> wifis = wifiRepository.findByNearDistanceOrderByDistance(lat, lnt);
        if (wifis.isEmpty()) {
            return null;
        }
        List<WifiDistanceResponseDto> responseList = wifis.stream().map(WifiDistanceResponseDto::of).collect(Collectors.toList());

        historyRepository.save(History.of(new HistorySaveRequestDto(lat, lnt)));

        return WifiNearResponseDto.of(responseList);
    }


    @Override
    public WifiDistanceResponseDto getWifiInfo(String id, Double lat, Double lnt) {
        Wifi wifi = wifiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NO WIFI DATA!!!"));

        Double distance = wifi.calcDistance(lat, lnt);

        return WifiDistanceResponseDto.of(distance, wifi);
    }

    @Override
    public WifiExistsResponseDto existsAnyWifiData() {
        return WifiExistsResponseDto.of(wifiRepository.existsAtLeastOne());
    }


}
