package com.jxw.design.controller;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.jxw.design.model.Post;
import com.jxw.design.service.PostService;
import com.jxw.design.view.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/27 18:04
 * @Description 收货信息
 */
@CrossOrigin
@Controller
@RequestMapping("/jxw/design/post")
public class PostController {
    private static Logger logger = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private PostService postService;

    @RequestMapping(value = "insert.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result insertPostInfo(@RequestBody Post post) {
        try {
            Preconditions.checkArgument(post.getUserId() != null, "用户Id不能为空");
            Preconditions.checkArgument(post.getPostName() != null, "收货人名不能为空");
            Preconditions.checkArgument(post.getPostPhone() != null, "收货人电话不能为空");
            Preconditions.checkArgument(post.getPostAddress() != null, "收货地址不能为空");
            logger.info("【收货信息】请求添加收货信息，请求数据:{}", new Gson().toJson(post));
            int i = postService.insertPostInfo(post);
            if (i > 0) {
                logger.info("添加收货信息成功");
                return Result.buildSuccessResult("添加收货信息成功");
            }
            if (i == -1) {
                logger.info("该收货信息已存在");
                return Result.buildFailedResult(-1, "该收货信息已存在");
            }
            return Result.buildFailedResult(-1, "添加收货信息失败");
        } catch (IllegalArgumentException ie) {
            logger.error("【收货信息】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【收货信息】添加收货信息时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "delete.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result deletePostInfo(@RequestBody Post post) {
        try {
            Preconditions.checkArgument(post.getId() != null, "收货信息Id不能为空");
            logger.info("【收货信息】请求删除收货信息，请求数据:{}", new Gson().toJson(post));
            int i = postService.deletePostInfo(post);
            if (i > 0) {
                logger.info("【收货信息】删除收货信息成功");
                return Result.buildSuccessResult("删除收货信息成功");
            }
            return Result.buildFailedResult(-1, "删除收货信息失败");
        } catch (IllegalArgumentException ie) {
            logger.error("【收货信息】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【收货信息】删除收货信息时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "update.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePostInfo(@RequestBody Post post) {
        try {
            Preconditions.checkArgument(post.getPostName() != null, "收货人名不能为空");
            Preconditions.checkArgument(post.getPostPhone() != null, "收货人电话不能为空");
            Preconditions.checkArgument(post.getPostAddress() != null, "收货地址不能为空");
            logger.info("【收货信息】请求修改收货信息，请求数据:{}", new Gson().toJson(post));
            int i = postService.updatePostInfo(post);
            if (i > 0) {
                logger.info("【收货信息】修改收货信息成功");
                return Result.buildSuccessResult("修改收货信息成功");
            }
            return Result.buildFailedResult(-1, "修改收货信息失败");
        } catch (IllegalArgumentException ie) {
            logger.error("【收货信息】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【收货信息】修改收货信息时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "select.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result selectPostInfo(@RequestBody String userId) {
        try {
            String str = new StringBuffer(userId).deleteCharAt(userId.length() - 1).toString();
            Preconditions.checkArgument(userId != null, "用户Id不能为空");
            logger.info("【收货信息】请求查询收货信息，请求数据:{}", new Gson().toJson(userId));
            List<Post> postList = postService.selectPostInfo(str);
            if (CollectionUtils.isEmpty(postList)) {
                logger.warn("【收货信息】查询到收货信息为空");
                return Result.buildFailedResult(-1, "您没有收货信息，请先添加...");
            }
            logger.info("【收货信息】查询收货信息成功");
            return Result.buildSuccessResult(postList);
        } catch (IllegalArgumentException ie) {
            logger.error("【收货信息】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【收货信息】查询收货信息时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }
}
