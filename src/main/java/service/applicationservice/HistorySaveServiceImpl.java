package service.applicationservice;

import global.config.InstanceFactory;
import service.controller.dto.HistorySaveRequestDto;
import service.controller.dto.HistorySaveResponseDto;
import service.entity.History;
import service.repository.HistoryRepository;

public class HistorySaveServiceImpl implements HistorySaveService {

    private final HistoryRepository historyRepository;

    public HistorySaveServiceImpl() {
        historyRepository = InstanceFactory.HistoryRepositoryFactory.getInstance();
    }

    @Override
    public HistorySaveResponseDto saveHistory(HistorySaveRequestDto dto) {
        History newHistory = History.of(dto);
        History save = historyRepository.save(newHistory);
        return HistorySaveResponseDto.of(save.getId());
    }

}
