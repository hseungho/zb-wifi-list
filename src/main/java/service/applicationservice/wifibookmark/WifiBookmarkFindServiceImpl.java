package service.applicationservice.wifibookmark;

import global.config.InstanceFactory;
import service.controller.dto.WifiBookmarkResponseDto;
import service.entity.WifiBookmark;
import service.repository.WifiBookmarkRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WifiBookmarkFindServiceImpl implements WifiBookmarkFindService {

    private final WifiBookmarkRepository wifiBookmarkRepository;
    public WifiBookmarkFindServiceImpl() {
        wifiBookmarkRepository = InstanceFactory.WifiBookmarkRepositoryFactory.getInstance();
    }

    @Override
    public List<WifiBookmarkResponseDto> getWifiBookmarkList() {
        List<WifiBookmark> wifiBookmarks = wifiBookmarkRepository.findAll();

        return wifiBookmarks.stream()
                .sorted(Comparator.comparing(WifiBookmark::getId))
                .map(WifiBookmarkResponseDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public WifiBookmarkResponseDto getWifiBookmarkById(Long id) {
        WifiBookmark wifiBookmark = wifiBookmarkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NO WIFI_BOOKMARK DATA"));

        return WifiBookmarkResponseDto.of(wifiBookmark);
    }
}
