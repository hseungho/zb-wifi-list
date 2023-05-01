package service.repository;

import service.entity.WifiBookmark;
import service.repository.base.CrudRepository;

import java.util.List;

public interface WifiBookmarkRepository extends CrudRepository<WifiBookmark, Long> {

    List<WifiBookmark> findListByBookmarkId(Long id);

}
