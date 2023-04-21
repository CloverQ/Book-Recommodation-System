package com.sakura.book_recommodation.controller;

import com.sakura.book_recommodation.req.RecommendationQueryReq;
import com.sakura.book_recommodation.resp.CommonResp;
import com.sakura.book_recommodation.resp.PageResp;
import com.sakura.book_recommodation.resp.RecommendationQueryResp;
import com.sakura.book_recommodation.service.RecommendationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
    @Resource
    private RecommendationService recommendationService;
    @GetMapping("/list")
    public CommonResp list(RecommendationQueryReq req) {
        CommonResp<PageResp<RecommendationQueryResp>> resp = new CommonResp<>();
        PageResp<RecommendationQueryResp> list = recommendationService.list(req);
        resp.setContent(list);
        return resp;
    }
}
