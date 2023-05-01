package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.controller.dto.BookmarkSaveRequestDto;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;

import java.util.List;

class BookmarkSaveServiceImplTest {

    private final BookmarkSaveService bookmarkSaveService = InstanceFactory.BookmarkSaveServiceFactory.getInstance();
    private final BookmarkRepository bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();

    @Test
    void test_saveBookmark() {
        BookmarkSaveRequestDto request = BookmarkSaveRequestDto.of("북마크 테스트", 1);

        bookmarkSaveService.saveBookmark(request);

        List<Bookmark> bookmarks = bookmarkRepository.findAll();
        Assertions.assertFalse(bookmarks.isEmpty());

        bookmarks.forEach(bookmarkRepository::delete);
    }

    @Test
    void test_save_five_bookmark() {
        BookmarkSaveRequestDto request1 = BookmarkSaveRequestDto.of("북마크 테스트1", 1);
        BookmarkSaveRequestDto request2 = BookmarkSaveRequestDto.of("북마크 테스트2", 3);
        BookmarkSaveRequestDto request3 = BookmarkSaveRequestDto.of("북마크 테스트3", 4);
        BookmarkSaveRequestDto request4 = BookmarkSaveRequestDto.of("북마크 테스트4", 2);
        BookmarkSaveRequestDto request5 = BookmarkSaveRequestDto.of("북마크 테스트5", 5);

        bookmarkSaveService.saveBookmark(request1);
        bookmarkSaveService.saveBookmark(request2);
        bookmarkSaveService.saveBookmark(request3);
        bookmarkSaveService.saveBookmark(request4);
        bookmarkSaveService.saveBookmark(request5);
    }

}