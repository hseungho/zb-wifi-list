package service.repository;

import global.config.DBConfig;
import global.constants.WifiConstants;
import service.entity.Wifi;
import service.repository.base.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class WifiRepositoryImpl extends Repository implements WifiRepository {

    public static WifiRepository getInstance() {
        return LazyHolder.INSTANCE;
    }
    private static class LazyHolder {
        private static final WifiRepository INSTANCE = new WifiRepositoryImpl();
    }

    private WifiRepositoryImpl() {
        super.connect();
    }

    @Override
    public void save(Wifi wifi) {
        String prepareQuery = "INSERT INTO WIFI " +
                "(id, district, name, address1, address2, instl_floor, instl_type, instl_org, service_class, net_type, instl_year, in_out_type, connect_env, lat, lnt, worked_at) " +
                "VALUES " +
                "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%d', '%s', '%s', '%f', '%f', '%s')";

        String query = String.format(prepareQuery,
                wifi.getId(), wifi.getDistrict(), wifi.getName(), wifi.getAddress1(), wifi.getAddress2(),
                wifi.getInstlFloor(), wifi.getInstlType(), wifi.getInstlOrg(), wifi.getServiceClass(),
                wifi.getNetType(), wifi.getInstlYear(), wifi.getInOutType(), wifi.getConnectEnv(),
                wifi.getLat(), wifi.getLnt(), wifi.getWorkedAt());

        super.save(query);
    }

    public void saveAll(List<Map<String, Object>> wifiMapList) {
        String query = "INSERT INTO WIFI " +
                "(id, district, name, address1, address2, instl_floor, instl_type, instl_org, service_class, net_type, instl_year, in_out_type, connect_env, lat, lnt, worked_at) " +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connect.prepareStatement(query);

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
                    connect.commit();
                    preparedStatement.clearParameters();
                }
            }

            preparedStatement.executeBatch();
            connect.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public Wifi find(int id) {
        return null;
    }

    @Override
    public List<Wifi> findAll() {
        String query = "SELECT * FROM WIFI";

        ResultSet rs = super.find(query);

        List<Wifi> wifiList = new ArrayList<>();
        try {
            while (rs.next()) {
                if (!rs.next()) break;
                Wifi wifi = Wifi.of(rs);
                wifiList.add(wifi);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return wifiList;
    }

    @Override
    public boolean update(int id) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
