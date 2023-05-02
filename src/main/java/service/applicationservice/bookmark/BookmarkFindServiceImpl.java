package service.applicationservice.bookmark;

import global.config.InstanceFactory;
import service.controller.dto.BookmarkResponseDto;
import service.entity.Bookmark;
import service.repository.BookmarkRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookmarkFindServiceImpl implements BookmarkFindService {

    private final BookmarkRepository bookmarkRepository;
    public BookmarkFindServiceImpl() {
        bookmarkRepository = InstanceFactory.BookmarkRepositoryFactory.getInstance();
    }

    @Override
    public List<BookmarkResponseDto> getBookmarkList() {
        List<Bookmark> bookmarks = bookmarkRepository.findAll();

        return bookmarks.stream()
                .sorted(Comparator.comparing(Bookmark::getOrder))
                .map(BookmarkResponseDto::of)
                .collect(Collectors.toList());
    }

    @Override
    public BookmarkResponseDto getBookmarkById(Long id) {
        System.out.println("GET BOOKMARK BY ID");
        Bookmark bookmark = bookmarkRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("NO BOOKMARK DATA"));

        return BookmarkResponseDto.of(bookmark);
    }
}
