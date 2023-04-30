package service.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HistorySaveRequestDto {

    private Double lat;
    private Double lnt;

}
