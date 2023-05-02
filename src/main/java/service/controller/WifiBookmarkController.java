package service.controller;

import global.config.InstanceFactory;
import global.util.ServletUtils;
import service.applicationservice.wifibookmark.WifiBookmarkDeleteService;
import service.applicationservice.wifibookmark.WifiBookmarkFindService;
import service.applicationservice.wifibookmark.WifiBookmarkSaveService;
import service.controller.dto.WifiBookmarkResponseDto;
import service.controller.dto.WifiBookmarkSaveRequestDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wifi-bookmark")
public class WifiBookmarkController extends HttpServlet {

    private WifiBookmarkSaveService wifiBookmarkSaveService;
    private WifiBookmarkFindService wifiBookmarkFindService;
    private WifiBookmarkDeleteService wifiBookmarkDeleteService;

    @Override
    public void init() throws ServletException {
        wifiBookmarkSaveService = InstanceFactory.WifiBookmarkSaveServiceFactory.getInstance();
        wifiBookmarkFindService = InstanceFactory.WifiBookmarkFindServiceFactory.getInstance();
        wifiBookmarkDeleteService = InstanceFactory.WifiBookmarkDeleteServiceFactory.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WifiBookmarkSaveRequestDto requestDto = ServletUtils.request(req, WifiBookmarkSaveRequestDto.class);

        wifiBookmarkSaveService.saveWifiBookmark(requestDto);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        WifiBookmarkResponseDto responseDto = wifiBookmarkFindService.getWifiBookmarkById(id);

        ServletUtils.response(resp, responseDto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        wifiBookmarkDeleteService.deleteWifiBookmark(id);
    }
}
