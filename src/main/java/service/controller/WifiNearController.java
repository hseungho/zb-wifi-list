package service.controller;

import global.config.InstanceFactory;
import global.util.ResponseUtils;
import service.applicationservice.wifi.WifiFindService;
import service.controller.dto.WifiDistanceResponseDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/wifi/near")
public class WifiNearController extends HttpServlet {

    private WifiFindService wifiFindService;
    @Override
    public void init() {
        wifiFindService = InstanceFactory.WifiFindServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String latStr = req.getParameter("lat");
        String lntStr = req.getParameter("lnt");

        Double lat = Double.parseDouble(latStr);
        Double lnt = Double.parseDouble(lntStr);

        List<WifiDistanceResponseDto> wifiList = wifiFindService.getDistanceWifiList(lat, lnt);

        ResponseUtils.response(resp, wifiList);
    }
}
