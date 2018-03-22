package com.jxw.design.dao.master;

import com.jxw.design.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/17 19:57
 */
@Repository
public interface UserDao {
    /**
     * 更新用户Rank值
     *
     * @param userId
     * @param userLevel
     * @return 影响行数
     */
    int updateUserRank(@Param("userId") String userId, @Param("userLevel") Long userLevel);

    /**
     * 插入用户信息
     *
     * @param user
     * @return
     */
    int insertSelective(@Param("user") User user);

    /**
     * 更新用户关注和粉丝数
     *
     * @param userId
     * @param attentionNumber
     * @param fansNumber
     * @return
     */
    int updateAttFansNumber(@Param("userId") String userId, @Param("attentionNumber") Long attentionNumber, @Param
            ("fansNumber") Long fansNumber);

    /**
     * 更改用户账户（扣减or充值）
     *
     * @param userId
     * @param userAccount
     * @return
     */
    int updateUserAccount(@Param("userId") String userId, @Param("userAccount") Double userAccount);

}
