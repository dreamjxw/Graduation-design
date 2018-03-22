package com.jxw.design.service.impl;

import com.google.gson.Gson;
import com.jxw.design.dao.master.UserAttentionDao;
import com.jxw.design.dao.slave.UserAttentionSlaveDao;
import com.jxw.design.dao.slave.UserSlaveDao;
import com.jxw.design.model.UserAttention;
import com.jxw.design.model.resp.UserAttentionResp;
import com.jxw.design.service.UserAttentionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/24 19:48
 */
@Service
public class UserAttentionServiceImpl implements UserAttentionService {
    private static Logger logger = LoggerFactory.getLogger(UserAttentionServiceImpl.class);
    @Autowired
    private UserAttentionDao userAttentionDao;
    @Autowired
    private UserAttentionSlaveDao userAttentionSlaveDao;
    @Autowired
    private UserSlaveDao userSlaveDao;

    @Override
    public int addAttention(UserAttention userAttention) {
        logger.info("【添加关注】,请求参数:{}", new Gson().toJson(userAttention));
        return userAttentionDao.addAttention(userAttention);
    }

    @Override
    public int cancelAttention(UserAttention userAttention) {
        logger.info("【取消关注】,请求参数:{}", new Gson().toJson(userAttention));
        return userAttentionDao.cancelAttention(userAttention);
    }

    @Override
    public List<UserAttentionResp> selectFans(String userId) {
        logger.info("【查询被关注人】,请求用户ID:{}", new Gson().toJson(userId));
        List<UserAttention> selectFans = userAttentionSlaveDao.selectFans(userId);
        logger.info("【查询被关注人】,被关注人UserID:{}", new Gson().toJson(selectFans));
        if (CollectionUtils.isEmpty(selectFans)) {
            logger.info("【查询被关注人】查询到被关注人列表为空");
            return null;
        }
        List<UserAttentionResp> userFansResps = userSlaveDao.selectUserByUserIdList(selectFans);
        logger.info("【查询被关注人】,请求结果:{}", new Gson().toJson(userFansResps));
        return userFansResps;
    }

    @Override
    public List<UserAttentionResp> selectAttention(String userId) {
        logger.info("【查询关注人】,请求用户ID:{}", new Gson().toJson(userId));
        List<UserAttention> selectAttention = userAttentionSlaveDao.selectAttention(userId);
        logger.info("【查询关注人】,关注人UserID:{}", new Gson().toJson(selectAttention));
        if (CollectionUtils.isEmpty(selectAttention)) {
            logger.info("【查询关注人】查询到关注人列表为空");
            return null;
        }
        List<UserAttentionResp> userAttentionResps = userSlaveDao.selectUserByUserIdList(selectAttention);
        logger.info("【查询关注人】,请求结果:{}", new Gson().toJson(userAttentionResps));
        return userAttentionResps;
    }

    @Override
    public Long countFans(String userId) {
        logger.info("【查询被关注人数】,请求用户ID:{}", new Gson().toJson(userId));
        long countFans = userAttentionSlaveDao.countFans(userId);
        logger.info("【查询被关注人数】,请求结果:{}", new Gson().toJson(countFans));
        return countFans;
    }

    @Override
    public Long countAttention(String userId) {
        logger.info("【查询关注人数】,请求用户ID:{}", new Gson().toJson(userId));
        long countAttention = userAttentionSlaveDao.countAttention(userId);
        logger.info("【查询关注人数】,请求结果:{}", new Gson().toJson(countAttention));
        return countAttention;
    }

    @Override
    public UserAttention selectRepeatAttention(UserAttention userAttention) {
        logger.info("【查询是否重复关注】,请求数据:{}", new Gson().toJson(userAttention));
        return userAttentionSlaveDao.selectRepeatAttention(userAttention);
    }
}
