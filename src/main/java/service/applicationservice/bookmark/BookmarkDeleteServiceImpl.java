package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import service.repository.BookmarkRepository;

public class BookmarkDeleteServiceImpl implements BookmarkDeleteService {

    private final BookmarkRepository bookmarkRepository;

    public BookmarkDeleteServiceImpl() {
        bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
    }

    @Override
    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }
}
