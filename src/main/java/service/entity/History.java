package service.entity;

import lombok.Getter;
import lombok.ToString;
import service.controller.dto.HistoryCreateRequestDto;

import java.time.LocalDateTime;

@Getter
@ToString
public class History {

    private Long id;
    private Double lat;
    private Double lnt;
    private LocalDateTime createdAt;


    ////////////////////////////////////////////////////////////////////
    // Entity Factory
    public static History of(HistoryCreateRequestDto dto) {
        History history = new History();
        history.lat = dto.getLat();
        history.lnt = dto.getLnt();
        history.createdAt = LocalDateTime.now();
        return history;
    }

}
