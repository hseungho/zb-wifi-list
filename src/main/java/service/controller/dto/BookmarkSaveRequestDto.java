package service.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BookmarkSaveRequestDto {

    private String name;
    private int order;

    public static BookmarkSaveRequestDto of(String name, int order) {
        return new BookmarkSaveRequestDto(name, order);
    }
}
