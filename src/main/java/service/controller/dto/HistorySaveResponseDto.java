package service.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HistorySaveResponseDto {

    private Long id;

    public static HistorySaveResponseDto of(Long id) {
        return new HistorySaveResponseDto(id);
    }

}
