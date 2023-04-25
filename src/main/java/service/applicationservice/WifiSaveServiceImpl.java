package service.applicationservice;

import global.adapter.openapi.OpenApiWifiAdapter;
import service.entity.Wifi;
import service.repository.WifiRepository;
import service.repository.WifiRepositoryImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WifiSaveServiceImpl implements WifiSaveService {

    public static WifiSaveService getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static final WifiSaveService INSTANCE = new WifiSaveServiceImpl();
    }

    private final OpenApiWifiAdapter openApiWifiAdapter;
    private final WifiRepository wifiRepository;

    private WifiSaveServiceImpl() {
        openApiWifiAdapter = OpenApiWifiAdapter.getInstance();
        wifiRepository = WifiRepositoryImpl.getInstance();
    }

    @Override
    public void getOpenApiWifiListAndSave() {
        List<Wifi> wifiInfo = openApiWifiAdapter.getWifiInfo();

        List<Map<String, Object>> fieldMap = wifiInfo.stream()
                .map(Wifi::getFieldMap)
                .collect(Collectors.toList());

        wifiRepository.saveAll(fieldMap);
    }
}
