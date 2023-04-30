package service.repository;

import global.config.InstanceFactory;
import global.constants.SQLConstants;
import service.entity.WifiBookmark;
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

public class WifiBookmarkRepositoryImpl extends BaseRepository<WifiBookmark, Long> implements WifiBookmarkRepository {

    public WifiBookmarkRepositoryImpl(ConnectionPool connectionPool) {
        super(connectionPool);
        super.DDL(SQLConstants.WIFI_TABLE.DDL);
        super.DDL(SQLConstants.BOOKMARK_TABLE.DDL);
        super.DDL(SQLConstants.WIFI_BOOKMARK_TABLE.DDL);
    }

    @Override
    protected Connection getTxConnection() {
        return ((TransactionalProxy) Proxy.getInvocationHandler(InstanceFactory.WifiBookmarkRepositoryFactory.getInstance())).getConnection();
    }

    @Override
    public void save(WifiBookmark wifiBookmark) {
        String query = SQLConstants.WIFI_BOOKMARK_TABLE.INSERT_BASIC_STATEMENT;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            super.executeUpdate(preparedStatement,
                    wifiBookmark.getWifiId(),
                    wifiBookmark.getBookmarkId(),
                    wifiBookmark.getCreatedAt().toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement);
        }
    }

    @Override
    public Optional<WifiBookmark> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<WifiBookmark> findAll() {
        String query = SQLConstants.WIFI_BOOKMARK_TABLE.SELECT_ALL;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            resultSet = executeQuery(preparedStatement);
            List<WifiBookmark> wifiBookmarks = new ArrayList<>();
            while(resultSet.next()) {
                WifiBookmark wifiBookmark = WifiBookmark.of(resultSet);
                wifiBookmarks.add(wifiBookmark);
            }
            return wifiBookmarks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement, resultSet);
        }
    }

    @Override
    public void update(WifiBookmark entity) {

    }

    @Override
    public void delete(WifiBookmark entity) {

    }

}
