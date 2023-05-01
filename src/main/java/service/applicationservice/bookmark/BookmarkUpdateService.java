package service.applicationservice.bookmark;

public interface BookmarkUpdateService {

    void updateBookmarkNameAndOrder(Long id, String name, Integer order);

}
