package com.sakura.book_recommodation.controller;

import com.sakura.book_recommodation.req.BookQueryReq;
import com.sakura.book_recommodation.req.BookSaveReq;
import com.sakura.book_recommodation.req.CommentsQueryReq;
import com.sakura.book_recommodation.resp.BookQueryResp;
import com.sakura.book_recommodation.resp.CommentsQueryResp;
import com.sakura.book_recommodation.resp.CommonResp;
import com.sakura.book_recommodation.resp.PageResp;
import com.sakura.book_recommodation.service.Book2CommentsService;
import com.sakura.book_recommodation.service.BookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private BookService bookService;
    @Resource
    private Book2CommentsService book2CommentsService;

    @GetMapping("/list")
    public CommonResp list(BookQueryReq req) {
        CommonResp<PageResp<BookQueryResp>> resp = new CommonResp<>();
        PageResp<BookQueryResp> list = bookService.list(req);
        resp.setContent(list);
        return resp;
    }
    /*
    * !!!!!!做个标记，之后都把get改成post!!!!!!!
    * post yyds
    * 上下两个是一个意思
    * */
    @PostMapping("/list1")
    public CommonResp list1(@RequestBody BookQueryReq req) {
        CommonResp<PageResp<BookQueryResp>> resp = new CommonResp<>();
        PageResp<BookQueryResp> list = bookService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/save")
    public CommonResp save(@RequestBody BookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        bookService.save(req);
        return resp;
    }
    @GetMapping("/comments")
    public CommonResp list(CommentsQueryReq req) {
        CommonResp<PageResp<CommentsQueryResp>> resp = new CommonResp<>();
        PageResp<CommentsQueryResp> list = book2CommentsService.list(req);
        resp.setContent(list);
        return resp;
    }
}
