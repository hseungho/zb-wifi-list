package service.applicationservice;

import service.controller.dto.BookmarkSaveRequestDto;

public interface BookmarkSaveService {

    void saveBookmark(BookmarkSaveRequestDto dto);

}
