package service.controller.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import service.entity.History;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HistoryResponseDto {

    private Long id;
    private Double lat;
    private Double lnt;
    private String createdAt;

    public static HistoryResponseDto of(History entity) {
        return new HistoryResponseDto(
                entity.getId(),
                entity.getLat(),
                entity.getLnt(),
                entity.getCreatedAt().toString()
        );
    }

}
