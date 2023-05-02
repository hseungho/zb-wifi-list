package service.controller;

import global.config.InstanceFactory;
import global.util.ServletUtils;
import service.applicationservice.wifi.WifiFindService;
import service.controller.dto.WifiExistsResponseDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wifi/exist")
public class WifiExistsCheckController extends HttpServlet {

    private WifiFindService wifiFindService;
    @Override
    public void init() throws ServletException {
        wifiFindService = InstanceFactory.WifiFindServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WifiExistsResponseDto responseDto = wifiFindService.existsAnyWifiData();

        ServletUtils.response(resp, responseDto);
    }
}
