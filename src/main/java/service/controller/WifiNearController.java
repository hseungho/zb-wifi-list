package service.controller;

import global.config.InstanceFactory;
import global.util.ServletUtils;
import service.applicationservice.wifi.WifiFindService;
import service.controller.dto.WifiNearResponseDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        int page = Integer.parseInt(req.getParameter("page"));

        Double lat = Double.parseDouble(latStr);
        Double lnt = Double.parseDouble(lntStr);

        WifiNearResponseDto wifiList = wifiFindService.getDistanceWifiList(lat, lnt, page);
        ServletUtils.response(resp, wifiList);
    }
}
