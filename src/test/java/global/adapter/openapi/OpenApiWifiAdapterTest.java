package global.adapter.openapi;

import global.config.InstanceFactory;
import org.junit.jupiter.api.Test;

public class OpenApiWifiAdapterTest {

    private final OpenApiWifiAdapter openApiWifiAdapter = InstanceFactory.OpenApiWifiAdapterFactory.getInstance();

    @Test
    void test_getWifiInfo() {
        openApiWifiAdapter.getWifiInfo();
    }
}
