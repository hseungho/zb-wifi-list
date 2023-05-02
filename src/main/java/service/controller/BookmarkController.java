package service.controller;

import global.config.InstanceFactory;
import global.util.ServletUtils;
import service.applicationservice.bookmark.BookmarkDeleteService;
import service.applicationservice.bookmark.BookmarkFindService;
import service.applicationservice.bookmark.BookmarkSaveService;
import service.applicationservice.bookmark.BookmarkUpdateService;
import service.controller.dto.BookmarkResponseDto;
import service.controller.dto.BookmarkSaveRequestDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/bookmark")
public class BookmarkController extends HttpServlet {

    private BookmarkSaveService bookmarkSaveService;
    private BookmarkFindService bookmarkFindService;
    private BookmarkUpdateService bookmarkUpdateService;
    private BookmarkDeleteService bookmarkDeleteService;

    @Override
    public void init() throws ServletException {
        bookmarkSaveService = InstanceFactory.BookmarkSaveServiceFactory.getInstance();
        bookmarkFindService = InstanceFactory.BookmarkFindServiceFactory.getInstance();
        bookmarkUpdateService = InstanceFactory.BookmarkUpdateServiceFactory.getInstance();
        bookmarkDeleteService = InstanceFactory.BookmarkDeleteServiceFactory.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BookmarkSaveRequestDto requestDto = ServletUtils.request(req, BookmarkSaveRequestDto.class);

        bookmarkSaveService.saveBookmark(requestDto);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        BookmarkResponseDto bookmarkDto = bookmarkFindService.getBookmarkById(id);

        ServletUtils.response(resp, bookmarkDto);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        BookmarkSaveRequestDto requestDto = ServletUtils.request(req, BookmarkSaveRequestDto.class);

        bookmarkUpdateService.updateBookmarkNameAndOrder(id, requestDto);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));

        bookmarkDeleteService.deleteBookmark(id);
    }

}
