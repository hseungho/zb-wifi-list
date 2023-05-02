package service.controller;

import global.config.InstanceFactory;
import global.util.ServletUtils;
import service.applicationservice.bookmark.BookmarkFindService;
import service.controller.dto.BookmarkResponseDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/bookmark/list")
public class BookmarkListController extends HttpServlet {

    private BookmarkFindService bookmarkFindService;
    @Override
    public void init() throws ServletException {
        bookmarkFindService = InstanceFactory.BookmarkFindServiceFactory.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BookmarkResponseDto> bookmarkList = bookmarkFindService.getBookmarkList();

        ServletUtils.response(resp, bookmarkList);
    }
}
