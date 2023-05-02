package service.controller;

import com.google.gson.Gson;
import global.config.InstanceFactory;
import service.applicationservice.wifi.WifiFindService;
import service.controller.dto.WifiExistsResponseDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wifi/exists")
public class WifiExistsCheckController extends HttpServlet {

    private WifiFindService wifiFindService;
    @Override
    public void init() throws ServletException {
        wifiFindService = InstanceFactory.WifiFindServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WifiExistsResponseDto responseDto = wifiFindService.existsAnyWifiData();

        String gson = new Gson().toJson(responseDto);
        resp.setContentType("application/json;charset=UTF-8");
        resp.getWriter().println(gson);
    }
}
