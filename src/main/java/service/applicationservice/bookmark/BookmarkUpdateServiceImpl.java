package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import service.controller.dto.BookmarkSaveRequestDto;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;

public class BookmarkUpdateServiceImpl implements BookmarkUpdateService {

    private final BookmarkRepository bookmarkRepository;
    public BookmarkUpdateServiceImpl() {
        bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
    }

    @Override
    public void updateBookmarkNameAndOrder(Long id, BookmarkSaveRequestDto dto) {
        Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Bookmark Data"));

        bookmark.update(dto.getName(), dto.getOrder());

        bookmarkRepository.update(bookmark);
    }
}
