package service.applicationservice;

import service.controller.dto.HistorySaveRequestDto;

public interface HistorySaveService {

    void saveHistory(HistorySaveRequestDto dto);

}
