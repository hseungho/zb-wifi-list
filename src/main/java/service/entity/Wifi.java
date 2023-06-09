package service.entity;

import global.adapter.openapi.dto.OpenApiResponseDto;
import global.constants.WifiConstants;
import lombok.Getter;
import lombok.ToString;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class Wifi {

    private String id;
    private String district;
    private String name;
    private String address1;
    private String address2;
    private String instlFloor;
    private String instlType;
    private String instlOrg;
    private String serviceClass;
    private String netType;
    private Integer instlYear;
    private String inOutType;
    private String connectEnv;
    private Double lat;
    private Double lnt;
    private LocalDateTime workedAt;
    private Double distance;

    public Double calcDistance(Double oLat, Double oLnt) {
        double theta = this.lnt - oLnt;
        double dist = Math.sin(deg2rad(this.lat)) * Math.sin(deg2rad(oLat))
                + Math.cos(deg2rad(this.lat)) * Math.cos(deg2rad(oLat)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515 * 1.609344;
        return dist;
    }
    private Double deg2rad(Double deg) {
        return (deg * Math.PI / 180.0);
    }
    private Double rad2deg(Double rad) {
        return (rad * 180 / Math.PI);
    }


    ////////////////////////////////////////////////////////////////////////
    // Entity Factory
    public static Wifi of(OpenApiResponseDto.TbPublicWifiInfo.WifiInfoDto dto) {
        Wifi wifi = new Wifi();
        wifi.id = dto.getX_SWIFI_MGR_NO();
        wifi.district = dto.getX_SWIFI_WRDOFC();
        wifi.name = dto.getX_SWIFI_MAIN_NM();
        wifi.address1 = dto.getX_SWIFI_ADRES1();
        wifi.address2 = dto.getX_SWIFI_ADRES2();
        wifi.instlFloor = dto.getX_SWIFI_INSTL_FLOOR();
        wifi.instlType = dto.getX_SWIFI_INSTL_TY();
        wifi.instlOrg = dto.getX_SWIFI_INSTL_MBY();
        wifi.serviceClass = dto.getX_SWIFI_SVC_SE();
        wifi.netType = dto.getX_SWIFI_CMCWR();
        wifi.instlYear = dto.getX_SWIFI_CNSTC_YEAR();
        wifi.inOutType = dto.getX_SWIFI_INOUT_DOOR();
        wifi.connectEnv = dto.getX_SWIFI_REMARS3();
        if (-90 <= dto.getLAT() && dto.getLAT() >= 90) {
            wifi.lat = dto.getLNT();
            wifi.lnt = dto.getLAT();
        } else {
            wifi.lat = dto.getLAT();
            wifi.lnt = dto.getLNT();
        }
        wifi.workedAt = dto.getWORK_DTTM();
        return wifi;
    }

    public static Wifi of(ResultSet rs) throws SQLException {
        Wifi wifi = new Wifi();
        wifi.id = rs.getString(WifiConstants.FIELD_ID);
        wifi.district = rs.getString(WifiConstants.FIELD_DISTRICT);
        wifi.name = rs.getString(WifiConstants.FIELD_NAME);
        wifi.address1 = rs.getString(WifiConstants.FIELD_ADDRESS1);
        wifi.address2 = rs.getString(WifiConstants.FIELD_ADDRESS2);
        wifi.instlFloor = rs.getString(WifiConstants.FIELD_INSTL_FLOOR);
        wifi.instlType = rs.getString(WifiConstants.FIELD_INSTL_TYPE);
        wifi.instlOrg = rs.getString(WifiConstants.FIELD_INSTL_ORG);
        wifi.serviceClass = rs.getString(WifiConstants.FIELD_SERVICE_CLASS);
        wifi.netType = rs.getString(WifiConstants.FIELD_NET_TYPE);
        wifi.instlYear = rs.getInt(WifiConstants.FIELD_INSTL_YEAR);
        wifi.inOutType = rs.getString(WifiConstants.FIELD_IN_OUT_TYPE);
        wifi.connectEnv = rs.getString(WifiConstants.FIELD_CONNECT_ENV);
        wifi.lat = rs.getDouble(WifiConstants.FIELD_LAT);
        wifi.lnt = rs.getDouble(WifiConstants.FIELD_LNT);
        wifi.workedAt = LocalDateTime.parse(rs.getString(WifiConstants.FIELD_WORKED_AT));
        try {
            wifi.distance = rs.getDouble("distance");
        } catch (SQLException e) {
            return wifi;
        }
        return wifi;
    }

    public static Wifi wifiBookmarkOf(ResultSet rs) throws SQLException {
        Wifi wifi = new Wifi();
        wifi.id = rs.getString("w_id");
        wifi.name = rs.getString("w_name");
        return wifi;
    }

    public Map<String, Object> getFieldMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(WifiConstants.FIELD_ID, id);
        map.put(WifiConstants.FIELD_DISTRICT, district);
        map.put(WifiConstants.FIELD_NAME, name);
        map.put(WifiConstants.FIELD_ADDRESS1, address1);
        map.put(WifiConstants.FIELD_ADDRESS2, address2);
        map.put(WifiConstants.FIELD_INSTL_FLOOR, instlFloor);
        map.put(WifiConstants.FIELD_INSTL_TYPE, instlType);
        map.put(WifiConstants.FIELD_INSTL_ORG, instlOrg);
        map.put(WifiConstants.FIELD_SERVICE_CLASS, serviceClass);
        map.put(WifiConstants.FIELD_NET_TYPE, netType);
        map.put(WifiConstants.FIELD_INSTL_YEAR, instlYear);
        map.put(WifiConstants.FIELD_IN_OUT_TYPE, inOutType);
        map.put(WifiConstants.FIELD_CONNECT_ENV, connectEnv);
        map.put(WifiConstants.FIELD_LAT, lat);
        map.put(WifiConstants.FIELD_LNT, lnt);
        map.put(WifiConstants.FIELD_WORKED_AT, workedAt.toString());
        return map;
    }

}
