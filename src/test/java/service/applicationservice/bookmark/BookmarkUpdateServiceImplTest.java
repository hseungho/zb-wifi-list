package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.applicationservice.bookmark.BookmarkSaveService;
import service.applicationservice.bookmark.BookmarkUpdateService;
import service.controller.dto.BookmarkSaveRequestDto;
import service.controller.dto.BookmarkSaveResponseDto;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;

class BookmarkUpdateServiceImplTest {

    private final BookmarkSaveService bookmarkSaveService = InstanceFactory.BookmarkSaveServiceFactory.getInstance();
    private final BookmarkUpdateService bookmarkUpdateService = InstanceFactory.BookmarkUpdateServiceFactory.getInstance();
    private final BookmarkRepository bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();

    @Test
    void test_updateBookmark() {
        BookmarkSaveResponseDto before = bookmarkSaveService.saveBookmark(BookmarkSaveRequestDto.of("북마크 수정 전", 10));

        bookmarkUpdateService.updateBookmarkNameAndOrder(before.getId(), "북마크 수정 후", 5);

        Bookmark after = bookmarkRepository.findById(before.getId()).get();

        Assertions.assertNotNull(after);
        Assertions.assertEquals("북마크 수정 후", after.getName());
        Assertions.assertEquals(5, after.getOrder());

        bookmarkRepository.delete(after);
    }
}