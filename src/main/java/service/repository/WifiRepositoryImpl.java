package service.repository;

import global.config.DBConfig;
import global.config.InstanceFactory;
import global.constants.SQLConstants;
import global.constants.WifiConstants;
import service.entity.Wifi;
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
import java.util.Map;
import java.util.Optional;

public class WifiRepositoryImpl extends BaseRepository<Wifi, String> implements WifiRepository {

    public WifiRepositoryImpl(ConnectionPool connectionPool) {
        super(connectionPool);
        super.DDL(SQLConstants.WIFI_TABLE.DDL);
    }

    @Override
    protected Connection getTxConnection() {
        return ((TransactionalProxy) Proxy.getInvocationHandler(InstanceFactory.WifiRepositoryFactory.getInstance())).getConnection();
    }

    @Override
    public void save(Wifi entity) {

    }

    @Override
    public Optional<Wifi> findById(String id) {
        String query = SQLConstants.WIFI_TABLE.SELECT_WHERE_ID;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            resultSet = super.executeQuery(preparedStatement, id);
            if (resultSet.next()) {
                Wifi wifi = Wifi.of(resultSet);
                return Optional.of(wifi);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement, resultSet);
        }
        return Optional.empty();
    }

    @Override
    public List<Wifi> findAll() {
        String query = SQLConstants.WIFI_TABLE.SELECT_ALL;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getTxConnection().prepareStatement(query);
            resultSet = super.executeQuery(preparedStatement);
            List<Wifi> wifiList = new ArrayList<>();
            while (resultSet.next()) {
                Wifi wifi = Wifi.of(resultSet);
                wifiList.add(wifi);
            }
            return wifiList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement, resultSet);
        }
    }

    @Override
    public void update(Wifi entity) {

    }

    @Override
    public void delete(Wifi entity) {

    }

    @Override
    public void saveAll(List<Map<String, Object>> wifiMapList) {
        Connection connection = getTxConnection();

        String query = SQLConstants.WIFI_TABLE.INSERT_BASIC_STATEMENT;
        PreparedStatement preparedStatement = null;
        try {

            preparedStatement = connection.prepareStatement(query);

            for (int i = 0; i < wifiMapList.size(); i++) {
                Map<String, Object> wifiMap = wifiMapList.get(i);
                preparedStatement.setObject(1, wifiMap.get(WifiConstants.FIELD_ID));
                preparedStatement.setObject(2, wifiMap.get(WifiConstants.FIELD_DISTRICT));
                preparedStatement.setObject(3, wifiMap.get(WifiConstants.FIELD_NAME));
                preparedStatement.setObject(4, wifiMap.get(WifiConstants.FIELD_ADDRESS1));
                preparedStatement.setObject(5, wifiMap.get(WifiConstants.FIELD_ADDRESS2));
                preparedStatement.setObject(6, wifiMap.get(WifiConstants.FIELD_INSTL_FLOOR));
                preparedStatement.setObject(7, wifiMap.get(WifiConstants.FIELD_INSTL_TYPE));
                preparedStatement.setObject(8, wifiMap.get(WifiConstants.FIELD_INSTL_ORG));
                preparedStatement.setObject(9, wifiMap.get(WifiConstants.FIELD_SERVICE_CLASS));
                preparedStatement.setObject(10, wifiMap.get(WifiConstants.FIELD_NET_TYPE));
                preparedStatement.setObject(11, wifiMap.get(WifiConstants.FIELD_INSTL_YEAR));
                preparedStatement.setObject(12, wifiMap.get(WifiConstants.FIELD_IN_OUT_TYPE));
                preparedStatement.setObject(13, wifiMap.get(WifiConstants.FIELD_CONNECT_ENV));
                preparedStatement.setObject(14, wifiMap.get(WifiConstants.FIELD_LAT));
                preparedStatement.setObject(15, wifiMap.get(WifiConstants.FIELD_LNT));
                preparedStatement.setObject(16, wifiMap.get(WifiConstants.FIELD_WORKED_AT));

                preparedStatement.addBatch();

                if (i % DBConfig.OPT_BATCH_SIZE == 0) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                    connection.commit();
                    preparedStatement.clearParameters();
                }
            }

            preparedStatement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            returnConnection(connection);
            close(preparedStatement, null);
        }
    }
}
