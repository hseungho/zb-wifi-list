package service.repository;

import global.config.DBConfig;
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
                preparedStatement.setObject(1, wifiMap.get("id"));
                preparedStatement.setObject(2, wifiMap.get("district"));
                preparedStatement.setObject(3, wifiMap.get("name"));
                preparedStatement.setObject(4, wifiMap.get("address1"));
                preparedStatement.setObject(5, wifiMap.get("address2"));
                preparedStatement.setObject(6, wifiMap.get("instl_floor"));
                preparedStatement.setObject(7, wifiMap.get("instl_type"));
                preparedStatement.setObject(8, wifiMap.get("instl_org"));
                preparedStatement.setObject(9, wifiMap.get("service_class"));
                preparedStatement.setObject(10, wifiMap.get("net_type"));
                preparedStatement.setObject(11, wifiMap.get("instl_year"));
                preparedStatement.setObject(12, wifiMap.get("in_out_type"));
                preparedStatement.setObject(13, wifiMap.get("connect_env"));
                preparedStatement.setObject(14, wifiMap.get("lat"));
                preparedStatement.setObject(15, wifiMap.get("lnt"));
                preparedStatement.setObject(16, wifiMap.get("worked_at"));

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
