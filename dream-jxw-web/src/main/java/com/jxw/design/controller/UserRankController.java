package com.jxw.design.controller;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.jxw.design.model.User;
import com.jxw.design.model.req.UserRankReq;
import com.jxw.design.model.resp.UserRankListResp;
import com.jxw.design.service.UserInfoService;
import com.jxw.design.service.UserRankService;
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
 * @date 2018/1/19 16:43
 * @Description 大V用户
 */
@CrossOrigin
@Controller
@RequestMapping("/jxw/design/userrank")
public class UserRankController {
    private final Logger logger = LoggerFactory.getLogger(UserRankController.class);
    @Autowired
    private UserRankService userRankService;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "allRank.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result getAllRank(@RequestBody UserRankReq userRankReq) {
        try {
            Preconditions.checkArgument(userRankReq.getUserId() != null, "用户ID不可为空");
            logger.info("【用户排行】：请求获取用户排行榜，请求参数:{}", new Gson().toJson(userRankReq));
            User user = new User();
            user.setUserId(userRankReq.getUserId());
            List<UserRankListResp> userRankListResps = userRankService.rankingList(user);
            if (!CollectionUtils.isEmpty(userRankListResps)) {
                logger.info("【用户排行】：成功获取用户排行榜，返回结果:{}", new Gson().toJson(userRankListResps));
                return Result.buildSuccessResult(userRankListResps);
            }
            logger.warn("【用户排行】：返回排行榜为空，请求Id：", userRankReq.getUserId());
            return Result.buildFailedResult(-1, "排行榜为空");
        } catch (IllegalArgumentException ie) {
            logger.error("【用户排行】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【用户排行】获取用户排行榜时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "rankByCity.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result getRankByCity(@RequestBody UserRankReq userRankReq) {
        try {
            Preconditions.checkArgument(userRankReq.getUserId() != null, "用户ID不可为空");
            logger.info("【用户排行】：请求获取用户排行榜，请求参数:{}", new Gson().toJson(userRankReq));
            User userInfo = userInfoService.obtainUserInfo(userRankReq.getUserId());
            if (userInfo==null){
                logger.info("【用户排行】查询不到该用户信息");
                return Result.buildFailedResult(-1,"该用户不存在");
            }
            User user = new User();
            user.setUserId(userRankReq.getUserId());
            user.setUserCity(userInfo.getUserCity());
            List<UserRankListResp> userRankListByCityResps = userRankService.rankingListByCity(user);
            if (!CollectionUtils.isEmpty(userRankListByCityResps)) {
                logger.info("【用户排行】：成功获取用户同城排行榜，返回结果:{}", new Gson().toJson(userRankListByCityResps));
                return Result.buildSuccessResult(userRankListByCityResps);
            }
            logger.warn("【用户排行】：返回同城排行榜为空，请求Id：", user.getUserId());
            return Result.buildFailedResult(-1, "同城排行榜为空");
        } catch (IllegalArgumentException ie) {
            logger.error("【用户排行】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【用户排行】获取用户同城排行榜时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }
}
