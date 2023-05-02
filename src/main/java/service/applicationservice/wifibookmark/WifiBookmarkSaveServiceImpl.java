package service.applicationservice.wifibookmark;

import global.config.InstanceFactory;
import service.controller.dto.WifiBookmarkSaveRequestDto;
import service.controller.dto.WifiBookmarkSaveResponseDto;
import service.entity.Bookmark;
import service.entity.Wifi;
import service.entity.WifiBookmark;
import service.repository.BookmarkRepository;
import service.repository.WifiBookmarkRepository;
import service.repository.WifiRepository;
import service.repository.base.transaction.Transactional;

public class WifiBookmarkSaveServiceImpl implements WifiBookmarkSaveService {

    private final WifiBookmarkRepository wifiBookmarkRepository;
    private final WifiRepository wifiRepository;
    private final BookmarkRepository bookmarkRepository;

    public WifiBookmarkSaveServiceImpl() {
        wifiBookmarkRepository = InstanceFactory.WifiBookmarkRepositoryFactory.getInstance();
        wifiRepository = InstanceFactory.WifiRepositoryFactory.getInstance();
        bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
    }

    @Override
    @Transactional
    public WifiBookmarkSaveResponseDto saveWifiBookmark(WifiBookmarkSaveRequestDto dto) {
        Wifi wifi = wifiRepository.findById(dto.getWifiId()).orElseThrow(() -> new RuntimeException("NO WIFI DATA"));
        Bookmark bookmark = bookmarkRepository.findById(dto.getBookmarkId()).orElseThrow(() -> new RuntimeException("NO BOOKMARK DATA"));

        WifiBookmark wifiBookmark = WifiBookmark.of();
        wifiBookmark.associate(wifi);
        wifiBookmark.associate(bookmark);

        WifiBookmark save = wifiBookmarkRepository.save(wifiBookmark);
        return WifiBookmarkSaveResponseDto.of(save.getId());
    }
}
