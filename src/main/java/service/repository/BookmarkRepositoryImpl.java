package service.repository;

import global.constants.SQLConstants;
import service.entity.Bookmark;
import service.repository.base.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookmarkRepositoryImpl extends Repository implements BookmarkRepository {

    public BookmarkRepositoryImpl() {
        super.connect("Bookmark");
        super.initEachTable(SQLConstants.BOOKMARK_TABLE.DDL);
    }

    @Override
    public void save(Bookmark bookmark) {
        String query = SQLConstants.BOOKMARK_TABLE.INSERT_BASIC_STATEMENT;

        super.save(query,
                bookmark.getName(),
                bookmark.getOrder(),
                bookmark.getCreatedAt().toString());
    }

    @Override
    public Optional<Bookmark> findById(Long s) {
        return Optional.empty();
    }

    @Override
    public List<Bookmark> findAll() {
        String query = SQLConstants.BOOKMARK_TABLE.SELECT_ALL;

        ResultSet rs = super.findQuery(query);
        List<Bookmark> bookmarks = new ArrayList<>();
        try {
            while (rs.next()) {
                Bookmark bookmark = Bookmark.of(rs);
                bookmarks.add(bookmark);
                if (!rs.next()) break;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookmarks;
    }

    @Override
    public boolean updateById(Long s) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        String query = SQLConstants.BOOKMARK_TABLE.DELETE_WHERE_ID;
        return super.deleteById(query, id);
    }
}
