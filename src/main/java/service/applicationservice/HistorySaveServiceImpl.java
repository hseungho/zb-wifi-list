package service.applicationservice;

import global.config.InstanceFactory;
import service.controller.dto.HistorySaveRequestDto;
import service.entity.History;
import service.repository.HistoryRepository;

public class HistorySaveServiceImpl implements HistorySaveService {

    private final HistoryRepository historyRepository;

    public HistorySaveServiceImpl() {
        historyRepository = InstanceFactory.HistoryRepositoryFactory.getInstance();
    }

    @Override
    public void saveHistory(HistorySaveRequestDto dto) {
        History newHistory = History.of(dto);
        historyRepository.save(newHistory);
    }
}
