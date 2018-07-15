package com.jxw.design.controller;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.jxw.design.model.Wine;
import com.jxw.design.model.req.StringReq;
import com.jxw.design.model.resp.BannerPictureResp;
import com.jxw.design.service.RecommendService;
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
 * @Date 2018/2/5 15:07
 * @Description 个性推荐
 */
@CrossOrigin
@Controller
@RequestMapping("/jxw/design/recommend")
public class RecommendController {
    private static Logger logger = LoggerFactory.getLogger(RecommendController.class);
    private static int retryNum = 0;
    private static final int retryNumMax = 3;
    @Autowired
    private RecommendService recommendService;

    @RequestMapping(value = "banner.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result bannerPicture(@RequestBody StringReq stringReq) {
        try {
            Preconditions.checkArgument(stringReq != null, "用户Id不能为空");
            logger.info("【个性推荐Controller】请求获取私人酒窖,请求参数:{}", new Gson().toJson(stringReq));
            List<Wine> wineList = recommendService.privateWine(stringReq.getMessage());
            if (CollectionUtils.isEmpty(wineList)) {
                logger.info("【个性推荐Controller】获取私人酒窖失败，尝试第" + retryNum + "次重新获取...");
                retryNum++;
                if (retryNum == retryNumMax) {
                    logger.info("【个性推荐Controller】获取私人酒窖失败，尝试重新获取次数已到上限，获取失败");
                    return Result.buildFailedResult(-1, "获取私人酒窖失败");
                }
                privateWine(stringReq);
            }
            logger.info("【个性推荐Controller】请求获取私人酒窖成功,返回参数:{}", new Gson().toJson(wineList));
            return Result.buildSuccessResult(wineList);
        } catch (IllegalArgumentException ie) {
            logger.error("【个性推荐Controller】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【个性推荐Controller】获取私人酒窖时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "recommendWine.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result recommendWine(@RequestBody String userId) {
        try {
            Preconditions.checkArgument(userId != null, "用户Id不能为空");
            logger.info("【个性推荐Controller】请求获取推荐酒单，请求参数:{}", new Gson().toJson(userId));
            List<Wine> wineList = recommendService.recommendWine(userId);
            if (CollectionUtils.isEmpty(wineList)) {
                logger.info("【个性推荐Controller】获取推荐酒单失败，尝试第" + retryNum + "次重新获取...");
                retryNum++;
                if (retryNum == retryNumMax) {
                    logger.info("【个性推荐Controller】获取推荐酒单失败，尝试重新获取次数已到上限，获取失败");
                    return Result.buildFailedResult(-1, "获取推荐酒单失败");
                }
                recommendWine(userId);
            }
            logger.info("【个性推荐Controller】请求获取推荐酒单成功,返回参数:{}", new Gson().toJson(wineList));
            return Result.buildSuccessResult(wineList);
        } catch (IllegalArgumentException ie) {
            logger.error("【个性推荐Controller】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【个性推荐Controller】获取推荐酒单时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(name = "privateWines.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result privateWine(@RequestBody StringReq stringReq) {
        try {
            Preconditions.checkArgument(stringReq != null, "用户Id不能为空");
            logger.info("【个性推荐Controller】请求获取私人酒窖,请求参数:{}", new Gson().toJson(stringReq));
            List<Wine> wineList = recommendService.privateWine(stringReq.getMessage());
            if (CollectionUtils.isEmpty(wineList)) {
                logger.info("【个性推荐Controller】获取私人酒窖失败，尝试第" + retryNum + "次重新获取...");
                retryNum++;
                if (retryNum == retryNumMax) {
                    logger.info("【个性推荐Controller】获取私人酒窖失败，尝试重新获取次数已到上限，获取失败");
                    return Result.buildFailedResult(-1, "获取私人酒窖失败");
                }
                privateWine(stringReq);
            }
            logger.info("【个性推荐Controller】请求获取私人酒窖成功,返回参数:{}", new Gson().toJson(wineList));
            return Result.buildSuccessResult(wineList);
        } catch (IllegalArgumentException ie) {
            logger.error("【个性推荐Controller】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【个性推荐Controller】获取私人酒窖时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

//    @RequestMapping(name = "hotWineRank.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result hotWines() {
        try {
            logger.info("【个性推荐Controller】请求获取热销榜,请求参数:{}");
            List<Wine> wineList = recommendService.hotListWine();
            if (CollectionUtils.isEmpty(wineList)) {
                logger.info("【个性推荐Controller】获取热销榜失败，尝试第" + retryNum + "次重新获取...");
                retryNum++;
                if (retryNum == retryNumMax) {
                    logger.info("【个性推荐Controller】获取热销榜失败，尝试重新获取次数已到上限，获取失败");
                    return Result.buildFailedResult(-1, "获取热销榜失败");
                }
                hotWines();
            }
            logger.info("【个性推荐Controller】请求获取热销榜成功,返回参数:{}", new Gson().toJson(wineList));
            return Result.buildSuccessResult(wineList);
        } catch (IllegalArgumentException ie) {
            logger.error("【个性推荐Controller】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【个性推荐Controller】获取热销榜时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

}
