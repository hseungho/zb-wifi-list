package service.repository;

import global.constants.SQLConstants;
import service.entity.WifiBookmark;
import service.repository.base.BaseRepository;
import service.repository.base.ConnectionPool;

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
    public void save(WifiBookmark wifiBookmark) {
        String query = SQLConstants.WIFI_BOOKMARK_TABLE.INSERT_BASIC_STATEMENT;

//        super.save(query,
//                wifiBookmark.getWifiId(),
//                wifiBookmark.getBookmarkId(),
//                wifiBookmark.getCreatedAt().toString()
//        );
    }

    @Override
    public Optional<WifiBookmark> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<WifiBookmark> findAll() {
        return null;
    }

    @Override
    public void update(WifiBookmark entity) {

    }

    @Override
    public void delete(WifiBookmark entity) {

    }

}
