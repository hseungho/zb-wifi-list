package service.applicationservice;

import global.config.InstanceFactory;
import service.entity.History;
import service.repository.HistoryRepository;
import service.repository.base.transaction.Transactional;

public class HistoryDeleteServiceImpl implements HistoryDeleteService {

    private final HistoryRepository historyRepository;
    public HistoryDeleteServiceImpl() {
        historyRepository = InstanceFactory.HistoryRepositoryFactory.getInstance();
    }

    @Override
    @Transactional
    public void deleteHistory(Long id) {
        History history = historyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No History Data"));

        historyRepository.delete(history);
    }
}
