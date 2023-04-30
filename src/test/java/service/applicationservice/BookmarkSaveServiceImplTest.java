package service.applicationservice;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.controller.dto.BookmarkSaveRequestDto;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkSaveServiceImplTest {

    private static final BookmarkSaveService bookmarkSaveService;
    private static final BookmarkRepository bookmarkRepository;
    static {
        bookmarkSaveService = InstanceFactory.BookmarkSaveServiceFactory.getInstance();
        bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
    }


    @Test
    void test_saveBookmark() {
        BookmarkSaveRequestDto request = BookmarkSaveRequestDto.of("북마크 테스트", 1);

        bookmarkSaveService.saveBookmark(request);

        List<Bookmark> bookmarks = bookmarkRepository.findAll();
        Assertions.assertFalse(bookmarks.isEmpty());

        bookmarks.forEach(bookmarkRepository::delete);
    }

}