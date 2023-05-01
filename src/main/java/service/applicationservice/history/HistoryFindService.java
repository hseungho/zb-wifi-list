package service.applicationservice.history;

import service.controller.dto.HistoryResponseDto;

import java.util.List;

public interface HistoryFindService {

    List<HistoryResponseDto> getAllHistories();

}
