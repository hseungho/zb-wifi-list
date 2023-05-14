package service.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WifiNearResponseDto {

    private Integer totalItems;
    private List<WifiDistanceResponseDto> wifiList;

    public static WifiNearResponseDto of(Integer totalItems, List<WifiDistanceResponseDto> wifiList) {
        return new WifiNearResponseDto(totalItems, wifiList);
    }

    public static WifiNearResponseDto of(List<WifiDistanceResponseDto> wifiList) {
        return new WifiNearResponseDto(wifiList);
    }

    private WifiNearResponseDto(List<WifiDistanceResponseDto> wifiList) {
        this.wifiList = wifiList;
    }

}
