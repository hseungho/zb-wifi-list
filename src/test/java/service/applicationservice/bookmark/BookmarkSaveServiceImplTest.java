package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.controller.dto.BookmarkSaveRequestDto;
import service.controller.dto.BookmarkSaveResponseDto;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;

class BookmarkSaveServiceImplTest {

    private final BookmarkSaveService bookmarkSaveService = InstanceFactory.BookmarkSaveServiceFactory.getInstance();
    private final BookmarkRepository bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();

    @Test
    void test_saveBookmark() {
        BookmarkSaveRequestDto request = BookmarkSaveRequestDto.of("북마크 테스트", 1);

        BookmarkSaveResponseDto save = bookmarkSaveService.saveBookmark(request);

        Bookmark bookmark = bookmarkRepository.findById(save.getId()).get();
        Assertions.assertNotNull(bookmark);
        Assertions.assertEquals("북마크 테스트", bookmark.getName());
        Assertions.assertEquals(1, bookmark.getOrder());

        bookmarkRepository.delete(bookmark);
    }

}