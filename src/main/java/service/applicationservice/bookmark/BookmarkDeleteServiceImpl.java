package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import service.entity.WifiBookmark;
import service.repository.BookmarkRepository;
import service.repository.WifiBookmarkRepository;

import java.util.List;

public class BookmarkDeleteServiceImpl implements BookmarkDeleteService {

    private final BookmarkRepository bookmarkRepository;
    private final WifiBookmarkRepository wifiBookmarkRepository;
    public BookmarkDeleteServiceImpl() {
        bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
        wifiBookmarkRepository = InstanceFactory.WifiBookmarkRepositoryFactory.getInstance();
    }

    @Override
    public void deleteBookmark(Long id) {
        List<WifiBookmark> wifiBookmarks = wifiBookmarkRepository.findListByBookmarkId(id);
        if (!wifiBookmarks.isEmpty()) {
            wifiBookmarks.forEach(wifiBookmarkRepository::delete);
        }

        bookmarkRepository.deleteById(id);
    }
}
