package service.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WifiBookmarkSaveResponseDto {

    private Long id;

    public static WifiBookmarkSaveResponseDto of(Long id) {
        return new WifiBookmarkSaveResponseDto(id);
    }
}
