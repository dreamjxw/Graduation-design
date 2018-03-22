package com.jxw.design.service;

import com.jxw.design.model.UserAttention;
import com.jxw.design.model.resp.UserAttentionResp;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/24 19:33
 */
public interface UserAttentionService {
    /**
     * 添加关注人
     *
     * @param userAttention
     * @return
     */
    int addAttention(UserAttention userAttention);

    /**
     * 取消关注
     *
     * @param userAttention
     * @return
     */
    int cancelAttention(UserAttention userAttention);

    /**
     * 查询被关注人
     *
     * @param userId
     * @return
     */
    List<UserAttentionResp> selectFans(String userId);

    /**
     * 查询已关注人
     *
     * @param userId
     * @return
     */
    List<UserAttentionResp> selectAttention(String userId);

    /**
     * 查询被关注人数
     *
     * @param userId
     * @return
     */
    Long countFans(String userId);

    /**
     * 查询已关注人数
     *
     * @param userId
     * @return
     */
    Long countAttention(String userId);

    /**
     * 查询是否重复关注
     *
     * @param userAttention
     * @return
     */
    UserAttention selectRepeatAttention(UserAttention userAttention);
}
