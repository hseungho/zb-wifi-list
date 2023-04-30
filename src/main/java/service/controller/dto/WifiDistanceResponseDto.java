package service.controller.dto;

import lombok.*;
import service.entity.Wifi;

import java.time.LocalDateTime;

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
    private LocalDateTime workedAt;

    public static WifiDistanceResponseDto of(Double distance, Wifi wifi) {
        WifiDistanceResponseDto dto = new WifiDistanceResponseDto();
        dto.distance = distance;
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
        this.workedAt = entity.getWorkedAt();
        return this;
    }

}
