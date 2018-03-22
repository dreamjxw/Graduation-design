package com.jxw.design.service.impl;

import com.jxw.design.dao.master.PostDao;
import com.jxw.design.dao.slave.PostSlaveDao;
import com.jxw.design.model.Post;
import com.jxw.design.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/27 17:58
 * @Description
 */
@Service
public class PostServiceImpl implements PostService {
    private static Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    @Autowired
    private PostDao postDao;
    @Autowired
    private PostSlaveDao postSlaveDao;

    @Override
    public int insertPostInfo(Post post) {
        Post repeatInfo = postSlaveDao.checkRepeatInfo(post);
        if (repeatInfo == null) {
            logger.info("【收货信息】该信息未存在，新添加入库");
            return postDao.insertPostInfo(post);
        }
        logger.info("【收货信息】该收货信息已存在，不再添加入库");
        return -1;
    }

    @Override
    public int deletePostInfo(Post post) {
        return postDao.deletePostInfo(post);
    }

    @Override
    public int updatePostInfo(Post post) {
        return postDao.updatePostInfo(post);
    }

    @Override
    public List<Post> selectPostInfo(String userId) {
        return postSlaveDao.selectPostInfo(userId);
    }
}
