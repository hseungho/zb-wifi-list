package service.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import service.entity.Wifi;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WifiDistanceResponseDto {

    private Double distance;
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
    private String workedAt;

    public static WifiDistanceResponseDto of(Wifi wifi) {
        WifiDistanceResponseDto response = new WifiDistanceResponseDto();
        response.distance = Math.round(wifi.getDistance()*10000)/10000.0;
        response.entityOf(wifi);
        return response;
    }

    public static WifiDistanceResponseDto of(Double distance, Wifi wifi) {
        WifiDistanceResponseDto dto = new WifiDistanceResponseDto();
        dto.distance = Math.round(distance*10000)/10000.0;
        dto.entityOf(wifi);
        return dto;
    }

    private WifiDistanceResponseDto entityOf(Wifi entity) {
        this.id = entity.getId();
        this.district = entity.getDistrict();
        this.name = entity.getName();
        this.address1 = entity.getAddress1();
        this.address2 = entity.getAddress2();
        this.instlFloor = entity.getInstlFloor();
        this.instlType = entity.getInstlType();
        this.instlOrg = entity.getInstlOrg();
        this.serviceClass = entity.getServiceClass();
        this.netType = entity.getNetType();
        this.instlYear = entity.getInstlYear();
        this.inOutType = entity.getInOutType();
        this.connectEnv = entity.getConnectEnv();
        this.lat = entity.getLat();
        this.lnt = entity.getLnt();
        this.workedAt = entity.getWorkedAt().toString();
        return this;
    }

}
