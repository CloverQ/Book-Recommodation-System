package com.sakura.book_recommodation.controller;

import com.sakura.book_recommodation.req.FavoriteSaveReq;
import com.sakura.book_recommodation.resp.CommonResp;
import com.sakura.book_recommodation.service.Users2FavoriteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Resource
    private Users2FavoriteService users2FavoriteService;
    @PostMapping("/save")
    public CommonResp save(@RequestBody FavoriteSaveReq req) {
        CommonResp resp = new CommonResp<>();
        users2FavoriteService.save(req);
        return resp;
    }
}
