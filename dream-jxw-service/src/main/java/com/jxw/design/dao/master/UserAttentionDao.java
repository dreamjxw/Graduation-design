package com.jxw.design.dao.master;


import com.jxw.design.model.UserAttention;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 11:36
 */
@Repository
public interface UserAttentionDao {
    /**
     * 添加关注人
     *
     * @param userAttention
     * @return
     */
    int addAttention(@Param("userAttention") UserAttention userAttention);

    /**
     * 取消关注
     *
     * @param userAttention
     * @return
     */
    int cancelAttention(@Param("userAttention") UserAttention userAttention);



}