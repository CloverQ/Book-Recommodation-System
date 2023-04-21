package com.sakura.book_recommodation.controller;

import com.alibaba.fastjson.JSONObject;
import com.sakura.book_recommodation.req.*;
import com.sakura.book_recommodation.resp.*;
import com.sakura.book_recommodation.service.Users2FavoriteService;
import com.sakura.book_recommodation.service.UsersService;
import com.sakura.book_recommodation.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/users")
public class UsersController {
    private static final Logger LOG = LoggerFactory.getLogger(UsersController.class);

    @Resource
    private UsersService usersService;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private Users2FavoriteService users2FavoriteService;

    @Resource
    private SnowFlake snowFlake;

    @GetMapping("/list")
    public CommonResp list(@Valid UsersQueryReq req) {
        CommonResp<PageResp<UsersQueryResp>> resp = new CommonResp<>();
        PageResp<UsersQueryResp> list = usersService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UsersSaveReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        usersService.save(req);
        return resp;
    }
    @PostMapping("/delete")
    public CommonResp delete(@RequestBody UsersSaveReq req) {
        CommonResp resp = new CommonResp<>();
        usersService.delete(req.getUserId());
        return resp;
    }
    @PostMapping("/reset-pwd")
    public CommonResp resetPwd(@Valid @RequestBody UsersResetPwdReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        usersService.resetPwd(req);
        return resp;
    }
    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = usersService.login(req);
        // 生成token
        Long token = snowFlake.nextId();
        userLoginResp.setToken(token.toString());
        redisTemplate.opsForValue().set(token, JSONObject.toJSONString(userLoginResp), 3600 * 24, TimeUnit.SECONDS);
        if(userLoginResp.getUserId() != -1) {
            resp.setContent(userLoginResp);
        } else {
            resp.setMessage("Username or password is incorrect!");
            resp.setSuccess(false);
        }
        return resp;
    }
    @GetMapping("/logout/{token}")
    public CommonResp logout(@PathVariable Long token) {
        CommonResp resp = new CommonResp<>();
        redisTemplate.delete(token);
        Object object = redisTemplate.opsForValue().get(token);
        if(object != null) {
            resp.setContent("Fail to delete token!!!");
        }
        return resp;
    }

    @GetMapping("/favorite")
    public CommonResp list(FavoriteQueryReq req) {
        CommonResp<PageResp<FavoriteQueryResp>> resp = new CommonResp<>();
        PageResp<FavoriteQueryResp> list = users2FavoriteService.list(req);
        resp.setContent(list);
        return resp;
    }
}
