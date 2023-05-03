package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.controller.dto.BookmarkResponseDto;
import service.controller.dto.BookmarkSaveRequestDto;
import service.controller.dto.BookmarkSaveResponseDto;
import service.repository.BookmarkRepository;

import java.util.List;

class BookmarkFindServiceImplTest {

    private final BookmarkSaveService bookmarkSaveService = InstanceFactory.BookmarkSaveServiceFactory.getInstance();
    private final BookmarkFindService bookmarkFindService = InstanceFactory.BookmarkFindServiceFactory.getInstance();
    private final BookmarkRepository bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();

    @Test
    void test_getBookmarkList() {
        BookmarkSaveRequestDto request1 = BookmarkSaveRequestDto.of("북마크 테스트1", 1);
        BookmarkSaveRequestDto request2 = BookmarkSaveRequestDto.of("북마크 테스트2", 3);
        BookmarkSaveRequestDto request3 = BookmarkSaveRequestDto.of("북마크 테스트3", 4);
        BookmarkSaveRequestDto request4 = BookmarkSaveRequestDto.of("북마크 테스트4", 2);
        BookmarkSaveRequestDto request5 = BookmarkSaveRequestDto.of("북마크 테스트5", 5);

        BookmarkSaveResponseDto save1 = bookmarkSaveService.saveBookmark(request1);
        BookmarkSaveResponseDto save2 = bookmarkSaveService.saveBookmark(request2);
        BookmarkSaveResponseDto save3 = bookmarkSaveService.saveBookmark(request3);
        BookmarkSaveResponseDto save4 = bookmarkSaveService.saveBookmark(request4);
        BookmarkSaveResponseDto save5 = bookmarkSaveService.saveBookmark(request5);

        List<BookmarkResponseDto> bookmarkList = bookmarkFindService.getBookmarkList();

        Assertions.assertNotNull(bookmarkList);
        Assertions.assertFalse(bookmarkList.isEmpty());

        bookmarkRepository.deleteById(save1.getId());
        bookmarkRepository.deleteById(save2.getId());
        bookmarkRepository.deleteById(save3.getId());
        bookmarkRepository.deleteById(save4.getId());
        bookmarkRepository.deleteById(save5.getId());
    }

}