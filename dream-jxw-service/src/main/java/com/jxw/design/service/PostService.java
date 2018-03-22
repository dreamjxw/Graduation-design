package com.jxw.design.service;

import com.jxw.design.model.Post;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/27 17:56
 * @Description
 */
public interface PostService {
    /**
     * 添加收货信息
     *
     * @param post
     * @return
     */
    int insertPostInfo(Post post);

    /**
     * 删除收货信息
     *
     * @param post
     * @return
     */
    int deletePostInfo(Post post);

    /**
     * 更改收货信息
     *
     * @param post
     * @return
     */
    int updatePostInfo(Post post);

    /**
     * 查询收货信息
     *
     * @param userId
     * @return
     */
    List<Post> selectPostInfo(String userId);
}
