package com.jxw.design.dao.slave;

import com.jxw.design.model.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/27 17:35
 * @Description
 */
@Repository
public interface PostSlaveDao {
    /**
     * 插入时检测重复性
     *
     * @param post
     * @return
     */
    Post checkRepeatInfo(@Param("post") Post post);

    /**
     * 查询收货信息
     *
     * @param userId
     * @return
     */
    List<Post> selectPostInfo(@Param("userId") String userId);
}
