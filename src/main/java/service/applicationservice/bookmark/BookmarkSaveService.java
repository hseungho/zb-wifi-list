package service.applicationservice.bookmark;

import service.controller.dto.BookmarkSaveRequestDto;
import service.controller.dto.BookmarkSaveResponseDto;

public interface BookmarkSaveService {

    BookmarkSaveResponseDto saveBookmark(BookmarkSaveRequestDto dto);

}
