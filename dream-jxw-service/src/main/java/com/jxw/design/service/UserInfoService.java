package com.jxw.design.service;

import com.jxw.design.model.User;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/23 16:22
 */
public interface UserInfoService {
    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    User obtainUserInfo(String userId);

    /**
     * 更新用户关注和粉丝数
     *
     * @param userId
     * @param attentionNumber
     * @param fansNumber
     * @return
     */
    int updateAttFansNumber(String userId, Long attentionNumber, Long fansNumber);

    /**
     * 更改用户账户（扣减or充值）
     *
     * @param userId
     * @param userAccount
     * @return
     */
    int updateUserAccount(String userId, Double userAccount);
}
