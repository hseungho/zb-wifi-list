package service.applicationservice.wifibookmark;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Test;
import service.controller.dto.WifiBookmarkSaveRequestDto;
import service.controller.dto.WifiBookmarkSaveResponseDto;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;
import service.repository.WifiBookmarkRepository;

class WifiBookmarkSaveServiceImplTest {

    public final BookmarkRepository bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
    private final WifiBookmarkSaveService wifiBookmarkSaveService = InstanceFactory.WifiBookmarkSaveServiceFactory.getInstance();
    private final WifiBookmarkRepository wifiBookmarkRepository = InstanceFactory.WifiBookmarkRepositoryFactory.getInstance();

    @Test
    void test_saveWifiBookmark() {
        String wifiId = "---EP000001";
        Bookmark test_bookmark = bookmarkRepository.save(Bookmark.of("tester", 13));

        WifiBookmarkSaveResponseDto save = wifiBookmarkSaveService.saveWifiBookmark(WifiBookmarkSaveRequestDto.of(wifiId, test_bookmark.getId()));

        wifiBookmarkRepository.deleteById(save.getId());
        bookmarkRepository.delete(test_bookmark);
    }

}