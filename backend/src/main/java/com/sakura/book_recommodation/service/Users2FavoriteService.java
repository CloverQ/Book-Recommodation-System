package com.sakura.book_recommodation.service;

import com.github.pagehelper.PageInfo;
import com.sakura.book_recommodation.VO.UsersVO;
import com.sakura.book_recommodation.domain.User2favorite;
import com.sakura.book_recommodation.domain.User2favoriteExample;
import com.sakura.book_recommodation.mapper.User2favoriteMapper;
import com.sakura.book_recommodation.mapper.UsersMapper;
import com.sakura.book_recommodation.req.FavoriteQueryReq;
import com.sakura.book_recommodation.req.FavoriteSaveReq;
import com.sakura.book_recommodation.resp.FavoriteQueryResp;
import com.sakura.book_recommodation.resp.PageResp;
import com.sakura.book_recommodation.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Users2FavoriteService {
    private static final Logger LOG = LoggerFactory.getLogger(Users2FavoriteService.class);
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private User2favoriteMapper user2favoriteMapper;

    public PageResp<FavoriteQueryResp> list(FavoriteQueryReq req) {
        User2favoriteExample user2favoriteExample = new User2favoriteExample();
        User2favoriteExample.Criteria criteria = user2favoriteExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getUserId())) {
            criteria.andUserIdEqualTo(req.getUserId());
        }
        UsersVO usersVO = usersMapper.selectByUsersId(req.getUserId());
        List<User2favorite> user2favoriteList = usersVO.getUser2favorites();
        System.out.println(usersVO.getUser2favorites());
//        for(Comments s: commentsList) {
//            System.out.println(s.getContent());
//        }

        PageInfo<User2favorite> pageInfo = new PageInfo<>(user2favoriteList);
        LOG.info("Total lines: {}", pageInfo.getTotal());
        LOG.info("Total pages: {}", pageInfo.getPages());
        List<FavoriteQueryResp> list = CopyUtil.copyList(user2favoriteList, FavoriteQueryResp.class);
        PageResp<FavoriteQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void save(FavoriteSaveReq req) {
        User2favorite user2favorite = CopyUtil.copy(req, User2favorite.class);
        user2favoriteMapper.insert(user2favorite);
    }
}
