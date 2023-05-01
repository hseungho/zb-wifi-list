package service.controller.dto;

import lombok.*;
import service.entity.Bookmark;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkResponseDto {

    private Long id;
    private String name;
    private Integer order;
    private String createdAt;
    private String updatedAt;

    public static BookmarkResponseDto of(Bookmark entity) {
        BookmarkResponseDto response = new BookmarkResponseDto();
        response.id = entity.getId();
        response.name = entity.getName();
        response.order = entity.getOrder();
        response.createdAt = entity.getCreatedAt().toString();
        response.updatedAt = (entity.getUpdatedAt() != null) ? entity.getUpdatedAt().toString() : "";
        return response;
    }

}
