package service.entity;

import global.adapter.openapi.dto.OpenApiResponseDto;
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
        wifi.lat = dto.getLAT();
        wifi.lnt = dto.getLNT();
        wifi.workedAt = dto.getWORK_DTTM();
        return wifi;
    }

    public static Wifi of(ResultSet rs) throws SQLException {
        Wifi wifi = new Wifi();
        wifi.id = rs.getString("id");
        wifi.district = rs.getString("district");
        wifi.name = rs.getString("name");
        wifi.address1 = rs.getString("address1");
        wifi.address2 = rs.getString("address2");
        wifi.instlFloor = rs.getString("instl_floor");
        return wifi;
    }

    public Map<String, Object> getFieldMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("district", district);
        map.put("name", name);
        map.put("address1", address1);
        map.put("address2", address2);
        map.put("instl_floor", instlFloor);
        map.put("instl_type", instlType);
        map.put("instl_org", instlOrg);
        map.put("service_class", serviceClass);
        map.put("net_type", netType);
        map.put("instl_year", instlYear);
        map.put("in_out_type", inOutType);
        map.put("connect_env", connectEnv);
        map.put("lat", lat);
        map.put("lnt", lnt);
        map.put("worked_at", workedAt.toString());
        return map;
    }
}
