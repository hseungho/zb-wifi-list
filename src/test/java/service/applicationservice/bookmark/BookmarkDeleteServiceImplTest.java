package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.controller.dto.BookmarkSaveRequestDto;
import service.controller.dto.BookmarkSaveResponseDto;
import service.repository.BookmarkRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkDeleteServiceImplTest {

    private final BookmarkSaveService bookmarkSaveService = InstanceFactory.BookmarkSaveServiceFactory.getInstance();
    private final BookmarkDeleteService bookmarkDeleteService = InstanceFactory.BookmarkDeleteServiceFactory.getInstance();
    private final BookmarkRepository bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();

    @Test
    void test_deleteBookmark() {
        BookmarkSaveResponseDto save = bookmarkSaveService.saveBookmark(BookmarkSaveRequestDto.of("북마크 삭제", 10));

        bookmarkDeleteService.deleteBookmark(save.getId());

        Assertions.assertEquals(Optional.empty(), bookmarkRepository.findById(save.getId()));
    }

    @Test
    void test_deleteNotExistsBookmark() {

        Assertions.assertThrows(RuntimeException.class, () -> bookmarkDeleteService.deleteBookmark(100000L));

    }

}