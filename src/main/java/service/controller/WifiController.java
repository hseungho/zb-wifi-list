package service.controller;

import global.config.InstanceFactory;
import global.util.ServletUtils;
import service.applicationservice.wifi.WifiFindService;
import service.controller.dto.WifiDistanceResponseDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/wifi")
public class WifiController extends HttpServlet {

    private WifiFindService wifiFindService;

    @Override
    public void init() throws ServletException {
        wifiFindService = InstanceFactory.WifiFindServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = URLDecoder.decode(req.getParameter("id"), "UTF-8");
        Double lat = Double.valueOf(req.getParameter("lat"));
        Double lnt = Double.valueOf(req.getParameter("lnt"));

        System.out.println(id);

        WifiDistanceResponseDto wifi = wifiFindService.getWifiInfo(id, lat, lnt);

        ServletUtils.response(resp, wifi);
    }
}
