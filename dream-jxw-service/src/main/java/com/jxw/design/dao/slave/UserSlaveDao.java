package com.jxw.design.dao.slave;

import com.jxw.design.model.User;
import com.jxw.design.model.UserAttention;
import com.jxw.design.model.resp.UserAttentionResp;
import com.jxw.design.model.resp.UserRankListResp;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/17 19:58
 */
@Repository
public interface UserSlaveDao {
    /**
     * 查询全部排行榜
     *
     * @return
     */
    List<UserRankListResp> selectUserRankList();

    /**
     * 查询本地排行榜
     *
     * @param user
     * @return
     */
    List<UserRankListResp> selectUserRankListByCity(@Param("user") User user);

    /**
     * 查询用户是否重复
     *
     * @param user
     * @return
     */
    List<User> selectRepeatUser(@Param("user") User user);

    /**
     * 根据用户id列表批量查询用户
     *
     * @param list
     * @return
     */
    List<UserAttentionResp> selectUserByUserIdList(@Param("lists") List<UserAttention> list);

    /**
     * 根据用户Id获取用户信息
     *
     * @param userId
     * @return
     */
    User obtainUserInfo(String userId);

}
