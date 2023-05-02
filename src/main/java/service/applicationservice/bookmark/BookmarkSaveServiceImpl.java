package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import service.controller.dto.BookmarkSaveRequestDto;
import service.controller.dto.BookmarkSaveResponseDto;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;

public class BookmarkSaveServiceImpl implements BookmarkSaveService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkSaveServiceImpl() {
        this.bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
    }

    @Override
    public BookmarkSaveResponseDto saveBookmark(BookmarkSaveRequestDto dto) {
        Bookmark newBookmark = Bookmark.of(dto.getName(), dto.getOrder());
        Bookmark save = bookmarkRepository.save(newBookmark);
        return BookmarkSaveResponseDto.of(save.getId());
    }

}
