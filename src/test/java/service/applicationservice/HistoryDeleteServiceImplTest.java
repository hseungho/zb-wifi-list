package service.applicationservice;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.controller.dto.HistorySaveRequestDto;
import service.controller.dto.HistorySaveResponseDto;
import service.repository.HistoryRepository;

import static org.junit.jupiter.api.Assertions.*;

class HistoryDeleteServiceImplTest {

    private final HistoryRepository historyRepository = InstanceFactory.HistoryRepositoryFactory.getInstance();
    private final HistorySaveService historySaveService = InstanceFactory.HistorySaveServiceFactory.getInstance();
    private final HistoryDeleteService historyDeleteService = InstanceFactory.HistoryDeleteServiceFactory.getInstance();

    @Test
    void test_deleteHistory() {
        HistorySaveResponseDto save = historySaveService.saveHistory(new HistorySaveRequestDto(37.444, 126.3333));

        historyDeleteService.deleteHistory(save.getId());

        Assertions.assertAll(
                () -> Assertions.assertThrows(RuntimeException.class, () -> historyRepository.findById(save.getId()).get())
        );
    }

}