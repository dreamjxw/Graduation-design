package com.jxw.design.dao.slave;


import com.jxw.design.model.UserAttention;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 11:52
 */
@Repository
public interface UserAttentionSlaveDao {
    /**
     * 查询被关注的人
     *
     * @param userId
     * @return
     */
    List<UserAttention> selectFans(@Param("userId") String userId);

    /**
     * 查询已关注人
     *
     * @param userId
     * @return
     */
    List<UserAttention> selectAttention(@Param("userId") String userId);

    /**
     * 查询被关注人数
     *
     * @param userId
     * @return
     */
    long countFans(@Param("userId") String userId);

    /**
     * 查询已关注人数
     *
     * @param userId
     * @return
     */
    long countAttention(@Param("userId") String userId);

    /**
     * 查询是否重复关注
     *
     * @param userAttention
     * @return
     */
    UserAttention selectRepeatAttention(@Param("userAttention") UserAttention userAttention);

}