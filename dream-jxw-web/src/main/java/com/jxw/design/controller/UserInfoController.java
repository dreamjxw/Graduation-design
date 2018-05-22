package com.jxw.design.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.jxw.design.enums.AppInfo;
import com.jxw.design.json.JsonUtils;
import com.jxw.design.model.User;
import com.jxw.design.model.WeChatLogin;
import com.jxw.design.service.UserAttentionService;
import com.jxw.design.service.UserInfoService;
import com.jxw.design.view.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/23 16:14
 * @Description 微信登录
 */
@CrossOrigin
@Controller
@RequestMapping("/jxw/design/user")
public class UserInfoController {
    private final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    /**
     * TODO 后期改为使用Redis
     */
    HashMap<String, WeChatLogin> map = new HashMap<String, WeChatLogin>();
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserAttentionService userAttentionService;

    @RequestMapping(value = "login.htm")
    public void login(HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("【微信授权】：用户请求微信授权登录");
            String callBackUrl = "http://dreamjxw.imwork.net:80/jxw/design/user/callback.htm";
            String codeURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                    + AppInfo.APPID.getAppInfo() +
                    "&redirect_uri=" + URLEncoder.encode(callBackUrl) +
                    "&response_type=code" +
                    "&scope=snsapi_userinfo" +
                    "&state=STATE#wechat_redirect";
            resp.sendRedirect(codeURL);
        } catch (Exception e) {
            logger.error("【微信授权】微信授权登录时出现异常", e);
        }
    }

    @RequestMapping(value = "callback.htm")
    public void callBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            logger.info("【微信授权】：登录回调方法");
            String code = req.getParameter("code");
            String openid;
            String token;
            if (!map.containsKey(code)) {
                logger.info("已存在code，从map中直接取值");
                String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AppInfo.APPID
                        .getAppInfo()
                        + "&secret=" + AppInfo.APPSECRET.getAppInfo()
                        + "&code=" + code
                        + "&grant_type=authorization_code";
                JSONObject jsonObject = JsonUtils.doGetJson(accessTokenUrl);
                logger.info("【微信授权】：Token信息,:{}", new Gson().toJson(jsonObject));
                openid = jsonObject.getString("openid");
                token = jsonObject.getString("access_token");
                WeChatLogin weChatLogin = new WeChatLogin();
                weChatLogin.setOpenId(openid);
                weChatLogin.setToken(token);
                map.put(code, weChatLogin);
            }
            WeChatLogin weChatLogin = map.get(code);
            openid = weChatLogin.getOpenId();
            token = weChatLogin.getToken();
            String infoURL = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token + "&openid=" + openid +
                    "&lang=zh_CN";
            JSONObject jsonObjects = JsonUtils.doGetJson(infoURL);
            logger.info("【微信授权】：用户信息,:{}", new Gson().toJson(jsonObjects));
            Preconditions.checkArgument(jsonObjects.getString("openid") != null, "用户ID不可为空");
            Preconditions.checkArgument(jsonObjects.getString("nickname") != null, "用户姓名不可为空");
            Preconditions.checkArgument(jsonObjects.getString("sex") != null, "用户性别不可为空");
            Preconditions.checkArgument(jsonObjects.getString("province") != null, "用户所在省份不可为空");
            Preconditions.checkArgument(jsonObjects.getString("city") != null, "用户所在城市不可为空");
            Preconditions.checkArgument(jsonObjects.getString("country") != null, "用户所在国家不可为空");
            Preconditions.checkArgument(jsonObjects.getString("headimgurl") != null, "用户头像地址不可为空");
            User user = new User();
            user.setUserId(jsonObjects.getString("openid"));
            user.setUserName(jsonObjects.getString("nickname"));
            user.setUserSex(jsonObjects.getString("sex").equals("1") ? "男" : "女");
            user.setUserProvince(jsonObjects.getString("province"));
            user.setUserCity(jsonObjects.getString("city"));
            user.setUserCountry(jsonObjects.getString("country"));
            user.setUserHeadImg(jsonObjects.getString("headimgurl"));
            int i = userInfoService.insertUser(user);
            if (i > 0) {
                logger.info("【微信授权】用户信息插入成功,用户信息:{}", new Gson().toJson(user));

                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/success.jsp").forward(req, resp);
                // TODO 跳转至商城首页
            } else {
                logger.warn("【微信授权】用户信息插入失败，用户信息：{}", new Gson().toJson(user));
                // TODO 跳转错误页面
            }
        } catch (Exception e) {
            logger.error("【微信授权】用户信息插入时出现异常", e);
            // TODO 跳转错误页面
        }
    }

    @RequestMapping(value = "userInfo.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result userInfo(@RequestBody String userId) {
        try {
            Preconditions.checkArgument(userId != null, "用户Id不能为空");
            logger.info("【用户模块】请求获取用户信息,:{}", new Gson().toJson(userId));
            Long countFans = userAttentionService.countFans(userId);
            Long countAttention = userAttentionService.countAttention(userId);
            userInfoService.updateAttFansNumber(userId, countAttention, countFans);
            User user = userInfoService.obtainUserInfo(userId);
            if (user != null) {
                logger.info("【用户模块】查询到用户信息,:{}", new Gson().toJson(user));
                return Result.buildSuccessResult(user);
            }
            logger.info("【用户模块】查询不到该用户信息");
            return Result.buildFailedResult(-1, "查询不到该用户信息，请确认查询条件是否正确");
        } catch (IllegalArgumentException ie) {
            logger.error("【用户模块】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【用户模块】获取用户信息时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "updateAccount.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUserAccount(@RequestBody User user) {
        try {
            Preconditions.checkArgument(user.getUserId() != null, "用户Id不可为空");
            Preconditions.checkArgument(user.getUserAccount() != null, "用户充值金额不可为空");
            Preconditions.checkArgument(String.valueOf(user.getUserAccount()).matches("^[0-9]+(.[0-9]+)?$"),
                    "用户充值金额只能是数字");
            logger.info("【用户模块】请求修改用户账户金额,:{}", new Gson().toJson(user));
            User obtainUserInfo = userInfoService.obtainUserInfo(user.getUserId());
            logger.info("【用户模块】获取到该用户信息:{}", new Gson().toJson(obtainUserInfo));
            Double newUserAccount = obtainUserInfo.getUserAccount() + user.getUserAccount();
            logger.info("【用户模块】要充值金额:{}", new Gson().toJson(newUserAccount));
            int i = userInfoService.updateUserAccount(user.getUserId(), newUserAccount);
            if (i > 0) {
                logger.info("【用户模块】充值成功");
                return Result.buildSuccessResult("充值成功");
            }
            logger.info("【用户模块】充值失败");
            return Result.buildFailedResult(-1, "充值失败,请重试");
        } catch (IllegalArgumentException ie) {
            logger.error("【用户模块】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【用户模块】充值时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

}
