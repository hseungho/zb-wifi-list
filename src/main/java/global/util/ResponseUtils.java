package global.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {

    public static void response(HttpServletResponse resp, Object data) throws IOException {
        String gson = new Gson().toJson(data);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().println(gson);
    }

}
