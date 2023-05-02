package service.controller;

import global.config.InstanceFactory;
import global.util.ServletUtils;
import service.applicationservice.wifi.WifiDeleteService;
import service.applicationservice.wifi.WifiSaveService;
import service.controller.dto.WifiSaveResponseDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wifi/load")
public class WifiLoadController extends HttpServlet {

    private WifiDeleteService wifiDeleteService;
    private WifiSaveService wifiSaveService;

    @Override
    public void init() throws ServletException {
        wifiDeleteService = InstanceFactory.WifiDeleteServiceFactory.getInstance();
        wifiSaveService = InstanceFactory.WifiSaveServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        wifiDeleteService.deleteAllWifis();

        WifiSaveResponseDto saveDto = wifiSaveService.getOpenApiWifiListAndSave();

        ServletUtils.response(resp, saveDto);
    }
}
