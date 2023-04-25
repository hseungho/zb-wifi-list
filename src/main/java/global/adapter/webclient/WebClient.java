package global.adapter.webclient;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class WebClient {

    public static Response get(String url) {
        try {
            Request request = new Request.Builder().url(url).get().build();
            Response response = new OkHttpClient().newCall(request).execute();

            if (response.isSuccessful()) {
                return response;
            } else {
                System.err.println("WebClient ERROR - Http Method: GET, URL: "+url);
                throw new RuntimeException();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
