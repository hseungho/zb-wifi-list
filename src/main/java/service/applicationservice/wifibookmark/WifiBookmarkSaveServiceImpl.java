package service.applicationservice.wifibookmark;

import global.config.InstanceFactory;
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
    public void saveWifiBookmark(String wifiId, Long bookmarkId) {
        Wifi wifi = wifiRepository.findById(wifiId).orElseThrow(() -> new RuntimeException("NO WIFI DATA"));
        Bookmark bookmark = bookmarkRepository.findById(bookmarkId).orElseThrow(() -> new RuntimeException("NO BOOKMARK DATA"));

        WifiBookmark wifiBookmark = WifiBookmark.of();
        wifiBookmark.associate(wifi);
        wifiBookmark.associate(bookmark);

        wifiBookmarkRepository.save(wifiBookmark);
    }
}
