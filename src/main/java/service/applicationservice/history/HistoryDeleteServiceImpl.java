package service.applicationservice.history;

import global.config.InstanceFactory;
import service.entity.History;
import service.repository.HistoryRepository;

public class HistoryDeleteServiceImpl implements HistoryDeleteService {

    private final HistoryRepository historyRepository;
    public HistoryDeleteServiceImpl() {
        historyRepository = InstanceFactory.HistoryRepositoryFactory.getInstance();
    }

    @Override
    public void deleteHistory(Long id) {
        History history = historyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No History Data"));

        historyRepository.delete(history);
    }
}
