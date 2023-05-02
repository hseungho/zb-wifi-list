package service.applicationservice.history;

import global.config.InstanceFactory;
import service.controller.dto.HistoryResponseDto;
import service.entity.History;
import service.repository.HistoryRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryFindServiceImpl implements HistoryFindService {

    private final HistoryRepository historyRepository;

    public HistoryFindServiceImpl() {
        historyRepository = InstanceFactory.HistoryRepositoryFactory.getInstance();
    }

    @Override
    public List<HistoryResponseDto> getAllHistories() {
        List<History> histories = historyRepository.findAll();

        if (histories.isEmpty()) {
            System.err.println("NO Histories Data!!");
            return null;
        }

        return histories.stream()
                .map(HistoryResponseDto::of)
                .sorted(Comparator.comparing(HistoryResponseDto::getId).reversed())
                .collect(Collectors.toList());
    }

}
