package service.applicationservice;

import global.adapter.openapi.OpenApiWifiAdapter;
import global.config.InstanceFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import service.entity.Wifi;
import service.repository.WifiRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WifiSaveServiceImpl implements WifiSaveService {

    private final OpenApiWifiAdapter openApiWifiAdapter;
    private final WifiRepository wifiRepository;

    public WifiSaveServiceImpl() {
        openApiWifiAdapter = OpenApiWifiAdapter.getInstance();
        wifiRepository = InstanceFactory.WifiRepositoryFactory.getInstance();
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
