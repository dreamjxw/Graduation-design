package com.jxw.design.dao.master;

import com.jxw.design.model.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/27 17:35
 * @Description 收货信息
 */
@Repository
public interface PostDao {
    /**
     * 添加收货信息
     *
     * @param post
     * @return
     */
    int insertPostInfo(@Param("post") Post post);

    /**
     * 删除收货信息
     *
     * @param post
     * @return
     */
    int deletePostInfo(@Param("post") Post post);

    /**
     * 更改收货信息
     *
     * @param post
     * @return
     */
    int updatePostInfo(@Param("post") Post post);
}
