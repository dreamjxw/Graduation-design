package com.jxw.design.controller;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.jxw.design.model.UserAttention;
import com.jxw.design.model.resp.UserAttentionResp;
import com.jxw.design.service.UserAttentionService;
import com.jxw.design.view.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/24 20:40
 * @Description 关注、粉丝
 */
@CrossOrigin
@Controller
@RequestMapping("/jxw/design/attention")
public class UserAttentionController {
    private final Logger logger = LoggerFactory.getLogger(UserAttentionController.class);
    @Autowired
    private UserAttentionService userAttentionService;

    @RequestMapping(value = "check.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result check(@RequestBody UserAttention userAttention) {
        try {

            Preconditions.checkArgument(userAttention.getAttentionId() != null, "关注人ID不可为空");
            Preconditions.checkArgument(userAttention.getUserId() != null, "用户ID不可为空");
            logger.info("【用户关注】请求参数{}", new Gson().toJson(userAttention));
            UserAttention repeatAttention = userAttentionService.selectRepeatAttention(userAttention);
            if (repeatAttention != null) {
                logger.info("【用户关注】有重复关注，确定该次请求为取消关注");
                int i = userAttentionService.cancelAttention(userAttention);
                if (i > 0) {
                    logger.info("取消关注成功");
                    return Result.buildSuccessResult("取消关注成功");
                }
            } else {
                logger.info("【用户关注】无重复关注，确定该次请求为添加关注");
                int j = userAttentionService.addAttention(userAttention);
                if (j > 0) {
                    logger.info("添加关注成功");
                    return Result.buildSuccessResult("添加关注成功");
                }
            }
            logger.info("【用户关注】操作失败");
            return Result.buildFailedResult(-1, "操作失败");
        } catch (IllegalArgumentException ie) {
            logger.error("【用户关注】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【用户关注】用户关注时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "selectFans.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result selectFans(@RequestBody UserAttention userAttention) {
        try {
            Preconditions.checkArgument(userAttention.getUserId() != null, "用户ID不可为空");
            logger.info("【查询被关注人】请求参数{}", new Gson().toJson(userAttention));
            List<UserAttentionResp> userAttentionResps = userAttentionService.selectFans(userAttention.getUserId());
            if (!CollectionUtils.isEmpty(userAttentionResps)) {
                logger.info("【查询被关注人】获取被关注人成功，返回数据:{}", new Gson().toJson(userAttentionResps));
                return Result.buildSuccessResult(userAttentionResps);
            } else {
                logger.info("【查询被关注人】获取被关注人列表为空");
                return Result.buildFailedResult(-1, "您还没有被关注的人~~ 请提升自己的影响力");
            }
        } catch (IllegalArgumentException ie) {
            logger.error("【查询被关注人】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【查询被关注人】查询被关注人时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "selectAttentions.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result selectAttentions(@RequestBody UserAttention userAttention) {
        try {
            Preconditions.checkArgument(userAttention.getUserId() != null, "用户ID不可为空");
            logger.info("【查询关注人】请求参数{}", new Gson().toJson(userAttention));
            List<UserAttentionResp> userAttentionResps = userAttentionService.selectAttention(userAttention.getUserId
                    ());
            if (!CollectionUtils.isEmpty(userAttentionResps)) {
                logger.info("【查询关注人】获取关注人成功，返回数据:{}", new Gson().toJson(userAttentionResps));
                return Result.buildSuccessResult(userAttentionResps);
            } else {
                logger.info("【查询关注人】获取关注人列表为空");
                return Result.buildFailedResult(-1, "您还没有关注的人，快去关注你的Ta吧~~");
            }
        } catch (IllegalArgumentException ie) {
            logger.error("【查询关注人】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【查询关注人】查询关注人时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "countFans.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result countFans(@RequestBody String userId) {
        try {
            Preconditions.checkArgument(userId != null, "用户ID不可为空");
            logger.info("【查询被关注人数】请求参数{}", new Gson().toJson(userId));
            Long fansNumber = userAttentionService.countFans(userId);
            logger.info("【查询被关注人数】获取到被关注人数，:{}", new Gson().toJson(fansNumber));
            return Result.buildSuccessResult(fansNumber);
        } catch (IllegalArgumentException ie) {
            logger.error("【查询被关注人数】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【查询被关注人数】查询被关注人数时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }

    }

    @RequestMapping(value = "countAttention.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result countAttention(@RequestBody String userId) {
        try {
            Preconditions.checkArgument(userId != null, "用户ID不可为空");
            logger.info("【查询关注人数】请求参数{}", new Gson().toJson(userId));
            Long attentionNumber = userAttentionService.countAttention(userId);
            logger.info("【查询关注人数】获取到关注人数，:{}", new Gson().toJson(attentionNumber));
            return Result.buildSuccessResult(attentionNumber);
        } catch (IllegalArgumentException ie) {
            logger.error("【查询关注人数】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【查询关注人数】查询关注人数时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

}
