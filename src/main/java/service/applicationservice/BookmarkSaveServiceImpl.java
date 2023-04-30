package service.applicationservice;

import global.config.InstanceFactory;
import service.controller.dto.BookmarkSaveRequestDto;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;
import service.repository.base.transaction.Transactional;

public class BookmarkSaveServiceImpl implements BookmarkSaveService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkSaveServiceImpl() {
        this.bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
    }

    @Override
    @Transactional
    public void saveBookmark(BookmarkSaveRequestDto dto) {
        Bookmark newBookmark = Bookmark.of(dto.getName(), dto.getOrder());
        bookmarkRepository.save(newBookmark);
    }

}
