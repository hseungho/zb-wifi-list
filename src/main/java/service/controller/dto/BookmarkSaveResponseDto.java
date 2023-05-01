package service.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkSaveResponseDto {

    private Long id;

    public static BookmarkSaveResponseDto of(Long id) {
        return new BookmarkSaveResponseDto(id);
    }

}
