package com.jxw.design.service.impl;

import com.google.gson.Gson;
import com.jxw.design.dao.master.UserDao;
import com.jxw.design.dao.slave.OrderSlaveDao;
import com.jxw.design.dao.slave.UserSlaveDao;
import com.jxw.design.model.User;
import com.jxw.design.model.resp.UserRankListResp;
import com.jxw.design.service.UserRankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/10 17:44
 */
@Service
public class UserRankServiceImpl implements UserRankService {

    @Autowired
    private OrderSlaveDao orderSlaveDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserSlaveDao userSlaveDao;
    private final Logger logger = LoggerFactory.getLogger(UserRankServiceImpl.class);

    @Override
    public int updateUserRank(String userId) {
        logger.info("【用户排行】：请求更新用户Rank值，请求参数{}", new Gson().toJson(userId));
        Long userBuyTotal = orderSlaveDao.countUserBuyTotal(userId);
//        String userRankLevel = null;
//        if (userBuyTotal >= UserLevelType.DIAMONDS_VIP_SCORE.getScore()) {
//            userRankLevel = UserLevelType.DIAMONDS_VIP_SCORE.getLevel();
//        }
//        if (userBuyTotal >= UserLevelType.PLATINUM_VIP_SCORE.getScore() && userBuyTotal < UserLevelType
//                .DIAMONDS_VIP_SCORE.getScore()) {
//            userRankLevel = UserLevelType.PLATINUM_VIP_SCORE.getLevel();
//        }
//        if (userBuyTotal >= UserLevelType.GOLD_VIP_SCORE.getScore() && userBuyTotal < UserLevelType
//                .PLATINUM_VIP_SCORE.getScore()) {
//            userRankLevel = UserLevelType.GOLD_VIP_SCORE.getLevel();
//        }
//        if (userBuyTotal >= UserLevelType.SILVER_VIP_SCORE.getScore() && userBuyTotal < UserLevelType
//                .GOLD_VIP_SCORE.getScore()) {
//            userRankLevel = UserLevelType.SILVER_VIP_SCORE.getLevel();
//        }
//        if (userBuyTotal < UserLevelType.SILVER_VIP_SCORE.getScore()) {
//            userRankLevel = UserLevelType.BRONZE_VIP_SCORE.getLevel();
//        }
        int updateResult = userDao.updateUserRank(userId, userBuyTotal);
        logger.info("【用户排行】：更新用户Rank值完成，返回参数{}", new Gson().toJson(updateResult));
        return updateResult;
    }

    @Override
    public List<UserRankListResp> rankingListByCity(User user) {
        int i = updateUserRank(user.getUserId());
        if (i > 0) {
            return userSlaveDao.selectUserRankListByCity(user);
        }
        logger.info("【用户排行】：获取用户同城榜失败，请求用户ID{}", new Gson().toJson(user.getUserId()));
        return null;
    }

    @Override
    public List<UserRankListResp> rankingList(User user) {
        int i = updateUserRank(user.getUserId());
        if (i > 0) {
            return userSlaveDao.selectUserRankList();
        }
        logger.info("【用户排行】：获取用户排行榜失败，请求用户ID{}", new Gson().toJson(user.getUserId()));
        return null;
    }

}
