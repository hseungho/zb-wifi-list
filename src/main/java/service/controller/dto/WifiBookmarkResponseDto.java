package service.controller.dto;

import lombok.*;
import service.entity.WifiBookmark;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WifiBookmarkResponseDto {

    private Long id;
    private String bookmarkName;
    private Integer bookmarkOrder;
    private String wifiId;
    private String wifiName;
    private LocalDateTime createdAt;

    public static WifiBookmarkResponseDto of(WifiBookmark entity) {
        WifiBookmarkResponseDto response = new WifiBookmarkResponseDto();
        response.id = entity.getId();
        response.bookmarkName = entity.getBookmark().getName();
        response.bookmarkOrder = entity.getBookmark().getOrder();
        response.wifiId = entity.getWifi().getId();
        response.wifiName = entity.getWifi().getName();
        response.createdAt = entity.getCreatedAt();
        return response;
    }

}
