package service.controller;

import global.config.InstanceFactory;
import global.util.ServletUtils;
import service.applicationservice.wifibookmark.WifiBookmarkFindService;
import service.controller.dto.WifiBookmarkResponseDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/wifi-bookmark/list")
public class WifiBookmarkListController extends HttpServlet {

    private WifiBookmarkFindService wifiBookmarkFindService;

    @Override
    public void init() throws ServletException {
        wifiBookmarkFindService = InstanceFactory.WifiBookmarkFindServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<WifiBookmarkResponseDto> wifiBookmarkList = wifiBookmarkFindService.getWifiBookmarkList();

        ServletUtils.response(resp, wifiBookmarkList);
    }
}
