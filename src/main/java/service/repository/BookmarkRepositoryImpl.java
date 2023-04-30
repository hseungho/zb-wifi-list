package service.repository;

import global.constants.SQLConstants;
import service.entity.Bookmark;
import service.repository.base.BaseRepository;
import service.repository.base.ConnectionPool;

import java.util.List;
import java.util.Optional;

public class BookmarkRepositoryImpl extends BaseRepository<Bookmark, Long> implements BookmarkRepository {


    public BookmarkRepositoryImpl(ConnectionPool connectionPool) {
        super(connectionPool);
        super.DDL(SQLConstants.BOOKMARK_TABLE.DDL);
    }

    @Override
    public void save(Bookmark bookmark) {
        String query = SQLConstants.BOOKMARK_TABLE.INSERT_BASIC_STATEMENT;

//        super.save(query,
//                bookmark.getName(),
//                bookmark.getOrder(),
//                bookmark.getCreatedAt().toString());
    }

    @Override
    public void update(Bookmark entity) {

    }

    @Override
    public void delete(Bookmark entity) {

    }

    @Override
    public Optional<Bookmark> findById(Long id) {

        return Optional.empty();
    }

    @Override
    public List<Bookmark> findAll() {
        return null;
    }

}
