package com.jxw.design.service;

import com.jxw.design.model.User;
import com.jxw.design.model.resp.UserRankListResp;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/10 17:26
 */
public interface UserRankService {
    /**
     * 更新rank值
     *
     * @param userId
     * @return
     */
    int updateUserRank(String userId);

    /**
     * 同城排名
     *
     * @param user
     * @return
     */
    List<UserRankListResp> rankingListByCity(User user);

    /**
     * 所有排名
     *
     * @param user
     * @return
     */
    List<UserRankListResp> rankingList(User user);
}