package service.applicationservice.wifibookmark;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.controller.dto.WifiBookmarkResponseDto;
import service.controller.dto.WifiBookmarkSaveRequestDto;
import service.controller.dto.WifiBookmarkSaveResponseDto;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;

import java.util.List;

class WifiBookmarkFindServiceTest {

    public final BookmarkRepository bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
    private final WifiBookmarkSaveService wifiBookmarkSaveService = InstanceFactory.WifiBookmarkSaveServiceFactory.getInstance();
    private final WifiBookmarkFindService wifiBookmarkFindService = InstanceFactory.WifiBookmarkFindServiceFactory.getInstance();

    @Test
    void test_getWifiBookmarkList() {
        String wifiId = "---EP000001";
        Bookmark test_bookmark = bookmarkRepository.save(Bookmark.of("tester", 13));

        WifiBookmarkSaveResponseDto save = wifiBookmarkSaveService.saveWifiBookmark(WifiBookmarkSaveRequestDto.of(wifiId, test_bookmark.getId()));
        List<WifiBookmarkResponseDto> wifiBookmarkList = wifiBookmarkFindService.getWifiBookmarkList();

        Assertions.assertFalse(wifiBookmarkList.isEmpty());
    }

}