package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.controller.dto.BookmarkResponseDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkFindServiceImplTest {

    private final BookmarkFindService bookmarkFindService = InstanceFactory.BookmarkFindServiceFactory.getInstance();

    @Test
    void test_getBookmarkList() {
        List<BookmarkResponseDto> bookmarkList = bookmarkFindService.getBookmarkList();

        Assertions.assertNotNull(bookmarkList);
        Assertions.assertFalse(bookmarkList.isEmpty());
    }

}