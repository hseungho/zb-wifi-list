package global.adapter.openapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import global.adapter.openapi.dto.OpenApiResponseDto;
import global.adapter.webclient.WebClient;
import global.util.LocalDateTimeDeserializer;
import lombok.RequiredArgsConstructor;
import okhttp3.ResponseBody;
import service.entity.Wifi;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OpenApiWifiAdapterImpl implements OpenApiWifiAdapter {

    private static final String PROTOCOL = "http";
    private static final String HOSTNAME = "openapi.seoul.go.kr:8088";
    private static final String API_KEY = "5a6572597668736534387855467364";
    private static final String CONTENT_TYPE = "json";
    private static final String SERVICE = "TbPublicWifiInfo";
    private static final GsonBuilder gsonBuilder;
    static {
        gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
    }

    @Override
    public List<Wifi> getWifiInfo() {
        try {
            List<Wifi> wifiList = new ArrayList<>();

            int max_size = Integer.MAX_VALUE;
            int offset = 1, nextOffset = 1000;
            while(wifiList.size() < max_size) {
                final String url = getUrl(offset, nextOffset);

                ResponseBody response = WebClient.get(url).body();
                if (response != null) {
                    String body = response.string();

                    Gson gson = gsonBuilder.create();
                    OpenApiResponseDto responseDto = gson.fromJson(body, OpenApiResponseDto.class);

                    List<Wifi> wifis = responseDto.getTbPublicWifiInfo().getRow().stream().map(Wifi::of).collect(Collectors.toList());
                    wifiList.addAll(wifis);

                    offset += 1000;
                    nextOffset += 1000;

                    if (max_size == Integer.MAX_VALUE) {
                        max_size = responseDto.getTbPublicWifiInfo().getList_total_count();
                    }
                } else {
                    System.err.println("OpenApiWifiAdapter ERROR - Response is null");
                }
            }

            return wifiList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getUrl(int startIdx, int endIdx) {
        StringBuilder sb = new StringBuilder();
        sb.append(PROTOCOL).append("://");
        StringJoiner sj = new StringJoiner("/");
        sj.add(HOSTNAME).add(API_KEY).add(CONTENT_TYPE).add(SERVICE)
                .add(String.valueOf(startIdx)).add(String.valueOf(endIdx));
        sb.append(sj);
        return sb.toString();
    }
}
