package global.adapter.openapi;

import org.junit.jupiter.api.Test;

public class OpenApiWifiAdapterTest {

    private static final OpenApiWifiAdapter openApiWifiAdapter;
    static {
        openApiWifiAdapter = OpenApiWifiAdapter.getInstance();
    }

    @Test
    void test_getWifiInfo() {
        openApiWifiAdapter.getWifiInfo();
    }
}
