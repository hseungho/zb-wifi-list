package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;

public class BookmarkUpdateServiceImpl implements BookmarkUpdateService {

    private final BookmarkRepository bookmarkRepository;
    public BookmarkUpdateServiceImpl() {
        bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
    }

    @Override
    public void updateBookmarkNameAndOrder(Long id, String name, Integer order) {
        Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Bookmark Data"));

        bookmark.update(name, order);

        bookmarkRepository.update(bookmark);
    }
}
