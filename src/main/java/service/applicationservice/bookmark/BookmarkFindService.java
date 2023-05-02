package service.applicationservice.bookmark;

import service.controller.dto.BookmarkResponseDto;

import java.util.List;

public interface BookmarkFindService {

    List<BookmarkResponseDto> getBookmarkList();

    BookmarkResponseDto getBookmarkById(Long id);

}
