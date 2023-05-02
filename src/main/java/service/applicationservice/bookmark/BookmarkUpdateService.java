package service.applicationservice.bookmark;

import service.controller.dto.BookmarkSaveRequestDto;

public interface BookmarkUpdateService {

    void updateBookmarkNameAndOrder(Long id, BookmarkSaveRequestDto dto);

}
