package service.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WifiNearResponseDto {

    private Integer totalItems;
    private List<WifiDistanceResponseDto> wifiList;

    public static WifiNearResponseDto of(Integer totalItems, List<WifiDistanceResponseDto> wifiList) {
        return new WifiNearResponseDto(totalItems, wifiList);
    }

}
