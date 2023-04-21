package com.sakura.book_recommodation.controller;

import com.sakura.book_recommodation.req.CommentsSaveReq;
import com.sakura.book_recommodation.resp.CommonResp;
import com.sakura.book_recommodation.service.Book2CommentsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Resource
    private Book2CommentsService book2CommentsService;
    @PostMapping("/save")
    public CommonResp save(@RequestBody CommentsSaveReq req) {
        CommonResp resp = new CommonResp<>();
        book2CommentsService.save(req);
        return resp;
    }
}
