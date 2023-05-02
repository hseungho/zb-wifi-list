package service.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WifiExistsResponseDto {

    boolean value;

    public static WifiExistsResponseDto of(boolean value) {
        return new WifiExistsResponseDto(value);
    }
}
