package com.sakura.book_recommodation.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sakura.book_recommodation.domain.Users;
import com.sakura.book_recommodation.domain.UsersExample;
import com.sakura.book_recommodation.exception.BusinessException;
import com.sakura.book_recommodation.exception.BusinessExceptionCode;
import com.sakura.book_recommodation.mapper.UsersMapper;
import com.sakura.book_recommodation.req.UserLoginReq;
import com.sakura.book_recommodation.req.UsersQueryReq;
import com.sakura.book_recommodation.req.UsersResetPwdReq;
import com.sakura.book_recommodation.req.UsersSaveReq;
import com.sakura.book_recommodation.resp.PageResp;
import com.sakura.book_recommodation.resp.UserLoginResp;
import com.sakura.book_recommodation.resp.UsersQueryResp;
import com.sakura.book_recommodation.util.CopyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
@Service
public class UsersService {
    private static final Logger LOG = LoggerFactory.getLogger(UsersService.class);
    @Resource
    private UsersMapper usersMapper;
    public PageResp<UsersQueryResp> list(UsersQueryReq req) {
        UsersExample userExample = new UsersExample();
        UsersExample.Criteria criteria = userExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getUserName())) {
            criteria.andUserNameEqualTo(req.getUserName());
        }
        if (!ObjectUtils.isEmpty(req.getUserId())) {
            criteria.andUserIdEqualTo(req.getUserId());
        }
        PageHelper.startPage(req.getPage(), req.getSize());
        List<Users> userList = usersMapper.selectByExample(userExample);
        PageInfo<Users> pageInfo = new PageInfo<>(userList);
        LOG.info("Total lines: ", pageInfo.getTotal());
        LOG.info("Total pages: ", pageInfo.getPages());
        List<UsersQueryResp> list = CopyUtil.copyList(userList, UsersQueryResp.class);
        PageResp<UsersQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }
    public Users selectByUsersName(String UserName) {
        UsersExample userExample = new UsersExample();
        UsersExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo(UserName);
        List<Users> userList = usersMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }
    public void save(UsersSaveReq req) {
        Users user = CopyUtil.copy(req, Users.class);
        if (ObjectUtils.isEmpty(req.getUserId())) {
            Users userDB = selectByUsersName(req.getUserName());
            if (ObjectUtils.isEmpty(userDB)) {
                // 新增
                usersMapper.insert(user);
            } else {
                // 用户名已存在
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        } else {
            // 更新
            // 更新用户信息时不修改用户名和密码
            user.setUserName(null);
            user.setPassword(null);
            usersMapper.updateByPrimaryKeySelective(user);
        }
    }
    public void delete(Integer Id) {
        usersMapper.deleteByPrimaryKey(Id);
    }

    public void resetPwd(UsersResetPwdReq req) {
        Users user = CopyUtil.copy(req, Users.class);
        usersMapper.updateByPrimaryKeySelective(user);
    }
    public UserLoginResp login(UserLoginReq req) {
        Users userDB = selectByUsersName(req.getUserName());
        if(ObjectUtils.isEmpty(userDB)) {
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
        else {
            if(userDB.getPassword().equals(req.getPassword())) {
                UserLoginResp userLoginResp = CopyUtil.copy(userDB, UserLoginResp.class);
                return userLoginResp;
            }
            else {
                UserLoginResp userLoginResp = new UserLoginResp();
                userLoginResp.setUserId(-1);
                return userLoginResp;
            }
        }
    }
}
