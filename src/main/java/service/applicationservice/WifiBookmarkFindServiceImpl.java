package service.applicationservice;

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
                .map(WifiBookmarkResponseDto::of)
                .sorted(Comparator.comparing(WifiBookmarkResponseDto::getBookmarkOrder))
                .collect(Collectors.toList());
    }
}
