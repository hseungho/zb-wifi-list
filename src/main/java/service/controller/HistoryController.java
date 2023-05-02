package service.controller;

import global.config.InstanceFactory;
import global.util.ResponseUtils;
import service.applicationservice.history.HistoryDeleteService;
import service.applicationservice.history.HistoryFindService;
import service.controller.dto.HistoryResponseDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryController extends HttpServlet {

    private HistoryFindService historyFindService;
    private HistoryDeleteService historyDeleteService;

    @Override
    public void init() throws ServletException {
        historyFindService = InstanceFactory.HistoryFindServiceFactory.getInstance();
        historyDeleteService = InstanceFactory.HistoryDeleteServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<HistoryResponseDto> histories = historyFindService.getAllHistories();

        ResponseUtils.response(resp, histories);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long historyId = Long.valueOf(req.getParameter("id"));

        historyDeleteService.deleteHistory(historyId);
    }
}
