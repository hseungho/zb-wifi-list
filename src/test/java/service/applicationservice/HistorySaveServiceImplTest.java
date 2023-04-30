package service.applicationservice;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Test;
import service.controller.dto.HistorySaveRequestDto;
import service.entity.History;
import service.repository.HistoryRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HistorySaveServiceImplTest {

    private static final HistorySaveService historySaveService;
    private static final HistoryRepository historyRepository;
    static {
        historySaveService = InstanceFactory.HistorySaveServiceFactory.getInstance();
        historyRepository = InstanceFactory.HistoryRepositoryFactory.getInstance();
    }

    @Test
    void test_saveHistory() {
        Double lat = 37.5544069;
        Double lnt = 126.8998666;
        HistorySaveRequestDto dto = new HistorySaveRequestDto(lat, lnt);

        historySaveService.saveHistory(dto);

        List<History> histories = historyRepository.findAll();
        System.out.println(histories);

        histories.forEach(h -> historyRepository.delete(h.getId()));
    }
}