package service.repository;

import global.config.InstanceFactory;
import global.constants.SQLConstants;
import service.entity.Bookmark;
import service.repository.base.BaseRepository;
import service.repository.base.ConnectionPool;
import service.repository.base.transaction.TransactionalProxy;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookmarkRepositoryImpl extends BaseRepository<Bookmark, Long> implements BookmarkRepository {


    public BookmarkRepositoryImpl(ConnectionPool connectionPool) {
        super(connectionPool);
        super.DDL(SQLConstants.BOOKMARK_TABLE.DDL);
    }

    @Override
    protected Connection getTxConnection() {
        return ((TransactionalProxy) Proxy.getInvocationHandler(InstanceFactory.BookmarkRepositoryFactory.getInstance())).getConnection();
    }

    @Override
    public Bookmark save(Bookmark bookmark) {
        String query = SQLConstants.BOOKMARK_TABLE.INSERT_BASIC_STATEMENT;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            super.executeUpdate(preparedStatement,
                    bookmark.getName(),
                    bookmark.getOrder(),
                    bookmark.getCreatedAt().toString()
            );
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                Long id = generatedKeys.getLong(1);
                bookmark.setId(id);
                return bookmark;
            } else {
                throw new RuntimeException("Cannot get Bookmark id");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public Optional<Bookmark> findById(Long id) {
        String query = SQLConstants.BOOKMARK_TABLE.SELECT_WHERE_ID;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            resultSet = super.executeQuery(preparedStatement, id);
            if (resultSet.next()) {
                Bookmark bookmark = Bookmark.of(resultSet);
                return Optional.of(bookmark);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement, resultSet);
        }
        return Optional.empty();
    }

    @Override
    public List<Bookmark> findAll() {
        String query = SQLConstants.BOOKMARK_TABLE.SELECT_ALL;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            resultSet = super.executeQuery(preparedStatement);
            List<Bookmark> bookmarks = new ArrayList<>();
            while (resultSet.next()) {
                Bookmark bookmark = Bookmark.of(resultSet);
                bookmarks.add(bookmark);
            }
            return bookmarks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            close(preparedStatement, resultSet);
        }
    }

    @Override
    public Bookmark update(Bookmark entity) {
        String query = SQLConstants.BOOKMARK_TABLE.UPDATE_NAME_ORDER_WHERE_ID;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            super.executeUpdate(preparedStatement,
                    entity.getName(),
                    entity.getOrder(),
                    entity.getUpdatedAt(),
                    entity.getId()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement);
        }
        return entity;
    }

    @Override
    public void delete(Bookmark entity) {
        deleteById(entity.getId());
    }

    private void deleteById(Long id) {
        String query = SQLConstants.BOOKMARK_TABLE.DELETE_WHERE_ID;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            super.executeUpdate(preparedStatement, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement);
        }
    }

}
