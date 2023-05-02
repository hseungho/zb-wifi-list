package global.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class ServletUtils {

    public static void response(HttpServletResponse resp, Object data) throws IOException {
        String gson = new Gson().toJson(data);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().println(gson);
    }

    public static <T> T request(HttpServletRequest request, Class<T> target) throws JsonSyntaxException, IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        String body = sb.toString();
        return new Gson().fromJson(body, target);
    }
}
