package service.applicationservice.history;

import service.controller.dto.HistorySaveRequestDto;
import service.controller.dto.HistorySaveResponseDto;

public interface HistorySaveService {

    HistorySaveResponseDto saveHistory(HistorySaveRequestDto dto);

}
