package com.jxw.design.controller;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.jxw.design.enums.UserPowerType;
import com.jxw.design.model.User;
import com.jxw.design.model.Wine;
import com.jxw.design.model.req.WineStockReq;
import com.jxw.design.service.UserInfoService;
import com.jxw.design.service.WineService;
import com.jxw.design.service.WineStockService;
import com.jxw.design.view.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/26 17:50
 * @Description 酒品
 */
@CrossOrigin
@Controller
@RequestMapping("/jxw/design/wine")
public class WineController {
    private static Logger logger = LoggerFactory.getLogger(WineController.class);
    @Autowired
    private WineService wineService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private WineStockService wineStockService;

    @RequestMapping(value = "searchWine.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result searchWine(@RequestBody String content) {
        try {
            Preconditions.checkArgument(content != null, "搜索内容不能为空");
            logger.info("【搜索系统】请求搜索酒品，请求参数:{}", new Gson().toJson(content));
            List<Wine> wines = wineService.searchWine(content);
            if (CollectionUtils.isEmpty(wines)) {
                logger.info("【搜索系统】查询商品列表为空");
                return Result.buildFailedResult(-1, "查询不到满足该条件的商品");
            }
            logger.info("【搜索系统】查询到商品列表,:{}", new Gson().toJson(wines));
            return Result.buildSuccessResult(wines);
        } catch (IllegalArgumentException ie) {
            logger.error("【搜索系统】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【搜索系统】搜索时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value = "wineInfo.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result wineInfo(@RequestBody Long wineId) {
        try {
            Preconditions.checkArgument(wineId != null, "搜索内容不能为空");
            logger.info("【酒品详情】请求获取酒品详情，请求参数:{}", new Gson().toJson(wineId));
            Wine wine = wineService.selectWineByWineId(wineId);
            if (wine != null) {
                logger.info("【酒品详情】获取酒品详情成功，:{}", new Gson().toJson(wine));
                return Result.buildSuccessResult(wine);
            }
            logger.info("【酒品详情】获取酒品详情失败，获取结果为空");
            return Result.buildFailedResult(-1, "获取酒品详情出错,请重试");
        } catch (IllegalArgumentException ie) {
            logger.error("【酒品详情】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【酒品详情】获取酒品详情时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }

    }

    @RequestMapping(value = "insertWine.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result insertWine(@RequestBody Wine wine, HttpServletRequest req) {
        try {
            Preconditions.checkArgument(wine != null, "添加信息不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(wine.getWineAddress()), "酒品产地不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(wine.getWineBrand()), "酒品品牌不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(wine.getWineClass()), "酒品类别不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(wine.getWineImg()), "酒品图片不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(wine.getWineName()), "酒品名称不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(String.valueOf(wine.getWinePrice())), "酒品价格不能为空");
            Preconditions.checkArgument(String.valueOf(wine.getWinePrice()).matches("^[0-9]+(.[0-9]+)?$"),
                    "酒品价格只能是数字");
            Preconditions.checkArgument(StringUtils.isNotBlank(wine.getWineYear()), "酒品年份不能为空");
            Preconditions.checkArgument(Pattern.compile("\\d+").matcher(wine.getWineYear())
                            .matches()
                    , "酒品年份只能是正整数");
            String userId = req.getParameter("userId");
            Long stock = Long.parseLong(req.getParameter("stock"));
            Preconditions.checkArgument(StringUtils.isNotBlank(userId), "用户Id不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(String.valueOf(stock)), "库存数不能为空");
            Preconditions.checkArgument(Pattern.compile("\\d+").matcher(String.valueOf(stock))
                            .matches()
                    , "红酒新库存数量只能是正整数");
            logger.info("【添加商品】请求添加商品，请求参数:{}", new Gson().toJson(wine), new Gson().toJson(userId));
            User user = userInfoService.obtainUserInfo(userId);
            logger.info("【添加商品】请求用户信息:{}", new Gson().toJson(user));
            if (user.getUserPower().equals(UserPowerType.CONSUMER.getUserPower())) {
                logger.warn("【添加商品】该用户为消费者，无权限添加商品");
                return Result.buildFailedResult(-1, "您无权限执行此操作");
            }
            int i = wineService.insertWine(wine);
            if (i > 0) {
                logger.info("【添加商品】添加商品成功");
                int insertStock = wineStockService.insertStock(wine.getWineId(), stock);
                if (insertStock > 0) {
                    logger.info("【添加商品】插入库存成功");
                    return Result.buildSuccessResult("添加商品成功");
                }
                wineService.deleteWine(wine);
                logger.info("【添加商品】插入库存失败，删除已添加商品");
                return Result.buildSuccessResult("添加商品失败");
            }
            logger.info("【添加商品】添加商品失败");
            return Result.buildFailedResult(-1, "添加商品出错,请重试");
        } catch (IllegalArgumentException ie) {
            logger.error("【添加商品】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【添加商品】添加商品时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }

    }

    @RequestMapping(value = "deleteWine.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteWine(@RequestBody Wine wine, HttpServletRequest req) {
        try {
            Preconditions.checkArgument(wine.getWineId() != null, "要删除的酒品ID不能为空");
            String userId = req.getParameter("userId");
            Preconditions.checkArgument(userId != null, "用户Id不能为空");
            logger.info("【删除商品】请求删除商品，请求参数:{}", new Gson().toJson(wine), new Gson().toJson(userId));
            User user = userInfoService.obtainUserInfo(userId);
            logger.info("【删除商品】请求用户信息:{}", new Gson().toJson(user));
            if (user.getUserPower().equals(UserPowerType.CONSUMER.getUserPower())) {
                logger.warn("【删除商品】该用户为消费者，无权限删除商品");
                return Result.buildFailedResult(-1, "您无权限执行此操作");
            }
            int i = wineService.deleteWine(wine);
            if (i > 0) {
                logger.info("【删除商品】删除商品成功");
                wineStockService.deleteStock(wine.getWineId());
                return Result.buildSuccessResult("删除商品成功");
            }
            logger.info("【删除商品】删除商品失败");
            return Result.buildFailedResult(-1, "删除商品出错,请重试");
        } catch (IllegalArgumentException ie) {
            logger.error("【删除商品】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【删除商品】删除商品时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }

    }

    @RequestMapping(value = "updateWine.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result updateWine(@RequestBody Wine wine, HttpServletRequest req) {
        try {
            Preconditions.checkArgument(wine != null, "更新信息不能为空");
            Preconditions.checkArgument(StringUtils.isNotBlank(String.valueOf(wine.getWinePrice())), "更新酒品价格不能为空");
            Preconditions.checkArgument(NumberUtils.isNumber(String.valueOf(wine.getWinePrice())), "更新酒品价格只能是数字");
            Preconditions.checkArgument(StringUtils.isNotBlank(String.valueOf(wine.getWineId())), "要更新的酒品ID不能为空");
            String userId = req.getParameter("userId");
            Preconditions.checkArgument(userId != null, "用户Id不能为空");
            logger.info("【更改商品】请求更改商品，请求参数:{}", new Gson().toJson(wine), new Gson().toJson(userId));
            User user = userInfoService.obtainUserInfo(userId);
            logger.info("【更改商品】请求用户信息:{}", new Gson().toJson(user));
            if (user.getUserPower().equals(UserPowerType.CONSUMER.getUserPower())) {
                logger.warn("【更改商品】该用户为消费者，无权限更改商品");
                return Result.buildFailedResult(-1, "您无权限执行此操作");
            }
            int i = wineService.updateWine(wine);
            if (i > 0) {
                logger.info("【更改商品】更改商品成功");
                return Result.buildSuccessResult("更改商品成功");
            }
            logger.info("【更改商品】更改商品失败");
            return Result.buildFailedResult(-1, "更改商品出错,请重试");
        } catch (IllegalArgumentException ie) {
            logger.error("【更改商品】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【更改商品】更改商品时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }

    }

    @RequestMapping(value = "addStock.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result upStock(@RequestBody WineStockReq wineStockReq) {
        try {
            Preconditions.checkArgument(wineStockReq.getWineId() != null, "红酒Id不可为空");
            Preconditions.checkArgument(wineStockReq.getWineStock() != null, "红酒新库存不可为空");
            Preconditions.checkArgument(Pattern.compile("\\d+").matcher(String.valueOf(wineStockReq.getWineStock()))
                            .matches()
                    , "红酒新库存数量只能是正整数");
            logger.info("【添加库存】请求添加库存，请求参数:{}", new Gson().toJson(wineStockReq));
            int i = wineStockService.upStock(wineStockReq.getWineId(), wineStockReq.getWineStock());
            if (i > 0) {
                logger.info("【添加库存】添加库存成功");
                return Result.buildSuccessResult("添加库存成功");
            }
            logger.info("【添加库存】添加库存失败");
            return Result.buildFailedResult(-1, "添加库存出错,请重试");
        } catch (IllegalArgumentException ie) {
            logger.error("【添加库存】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【添加库存】添加库存时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }

    }
}
