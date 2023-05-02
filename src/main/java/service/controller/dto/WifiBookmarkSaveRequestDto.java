package service.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WifiBookmarkSaveRequestDto {

    private String wifiId;
    private Long bookmarkId;

    public static WifiBookmarkSaveRequestDto of(String wifiId, Long bookmarkId) {
        return new WifiBookmarkSaveRequestDto(wifiId, bookmarkId);
    }

}
