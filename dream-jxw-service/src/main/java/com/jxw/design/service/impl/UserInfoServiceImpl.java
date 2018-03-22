package com.jxw.design.service.impl;

import com.google.gson.Gson;
import com.jxw.design.dao.master.UserDao;
import com.jxw.design.dao.slave.UserSlaveDao;
import com.jxw.design.model.User;
import com.jxw.design.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/23 16:24
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    private static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserSlaveDao userSlaveDao;

    @Override
    public int insertUser(User user) {
        logger.info("【添加用户信息】：请求添加用户信息，请求参数：{}", new Gson().toJson(user));
        List<User> repeatUsers = userSlaveDao.selectRepeatUser(user);
        logger.info("【添加用户信息】：查询用户是否已存在，查询结果：{}", new Gson().toJson(repeatUsers));
        if (CollectionUtils.isEmpty(repeatUsers)) {
            logger.info("【添加用户信息】：添加新用户");
            return userDao.insertSelective(user);
        }
        logger.info("【添加用户信息】：用户已存在");
        return 1;
    }

    @Override
    public User obtainUserInfo(String userId) {
        return userSlaveDao.obtainUserInfo(userId);

    }

    @Override
    public int updateAttFansNumber(String userId, Long attentionNumber, Long fansNumber) {
        return userDao.updateAttFansNumber(userId, attentionNumber, fansNumber);
    }

    @Override
    public int updateUserAccount(String userId, Double userAccount) {
        return userDao.updateUserAccount(userId, userAccount);
    }
}
