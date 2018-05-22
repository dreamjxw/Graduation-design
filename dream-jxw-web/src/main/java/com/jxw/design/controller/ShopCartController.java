package com.jxw.design.controller;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.jxw.design.model.ShopCart;
import com.jxw.design.model.req.ShopCartReq;
import com.jxw.design.service.ShopCartService;
import com.jxw.design.view.Result;
import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/26 19:49
 * @Description
 */
@CrossOrigin
@Controller
@RequestMapping("/jxw/design/shopcart")
public class ShopCartController {
    private static Logger logger = LoggerFactory.getLogger(ShopCartController.class);
    @Autowired
    private ShopCartService shopCartService;

    @RequestMapping(value ="insert.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result insertShopCart(@RequestBody ShopCartReq shopCartReq) {
        try {
            Preconditions.checkArgument(shopCartReq.getUserId() != null, "用户ID不可为空");
            Preconditions.checkArgument(shopCartReq.getWineId() != null, "酒品ID不可为空");
            Preconditions.checkArgument(shopCartReq.getWineNum() != null, "酒品数量不可为空");
            logger.info("【购物车系统】商品请求添加购物车，请求数据:{}", new Gson().toJson(shopCartReq));
            int i = shopCartService.insertShopCart(shopCartReq);
            if (i > 0) {
                logger.info("【购物车系统】商品添加购物车成功");
                return Result.buildSuccessResult("添加购物车成功!");
            }
            logger.info("【购物车系统】商品添加购物车失败");
            return Result.buildFailedResult(-1, "添加购物车失败");
        } catch (IllegalArgumentException ie) {
            logger.error("【购物车系统】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【购物车系统】商品添加购物车时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value ="delete.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteShopCart(@RequestBody ShopCartReq shopCartReq) {
        try {
            Preconditions.checkArgument(shopCartReq.getUserId() != null, "用户ID不可为空");
            Preconditions.checkArgument(shopCartReq.getWineId() != null, "酒品ID不可为空");
            logger.info("【购物车系统】请求删除购物车商品，请求数据:{}", new Gson().toJson(shopCartReq));
            int i = shopCartService.deleteShopCart(shopCartReq);
            if (i > 0) {
                logger.info("【购物车系统】删除购物车商品成功");
                return Result.buildSuccessResult("删除购物车商品成功!");
            }
            logger.info("【购物车系统】删除购物车商品失败");
            return Result.buildFailedResult(-1, "删除购物车商品失败");
        } catch (IllegalArgumentException ie) {
            logger.error("【购物车系统】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【购物车系统】删除购物车时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value ="deleteAll.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteAllShopCart(@RequestBody String userId) {
        try {
            Preconditions.checkArgument(userId != null, "用户ID不可为空");
            logger.info("【购物车系统】请求清空购物车，请求数据:{}", new Gson().toJson(userId));
            int i = shopCartService.deleteAllShopCart(userId);
            if (i > 0) {
                logger.info("【购物车系统】清空购物车成功");
                return Result.buildSuccessResult("清空购物车成功!");
            }
            logger.info("【购物车系统】清空购物车失败");
            return Result.buildFailedResult(-1, "清空购物车失败");
        } catch (IllegalArgumentException ie) {
            logger.error("【购物车系统】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【购物车系统】清空购物车时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value ="update.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result updateShopCart(@RequestBody ShopCartReq shopCartReq) {
        try {
            Preconditions.checkArgument(shopCartReq.getUserId() != null, "用户ID不可为空");
            Preconditions.checkArgument(shopCartReq.getWineId() != null, "酒品ID不可为空");
            Preconditions.checkArgument(shopCartReq.getWineNum() != null, "酒品数量不可为空");
            logger.info("【购物车系统】请求更新购物车，请求数据:{}", new Gson().toJson(shopCartReq));
            int i = shopCartService.updateShopCart(shopCartReq);
            if (i > 0) {
                logger.info("【购物车系统】更新购物车成功");
                return Result.buildSuccessResult("更新购物车成功!");
            }
            logger.info("【购物车系统】更新购物车失败");
            return Result.buildFailedResult(-1, "更新购物车失败");
        } catch (IllegalArgumentException ie) {
            logger.error("【购物车系统】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【购物车系统】更新购物车时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value ="select.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result selectShopCart(@RequestBody String userId) {
        try {
            String str = new StringBuffer(userId).deleteCharAt(userId.length() - 1).toString();
            Preconditions.checkArgument(userId != null, "用户ID不可为空");
            logger.info("【购物车系统】请求查询购物车信息，请求数据:{}", new Gson().toJson(str));
            List<ShopCart> shopCarts = shopCartService.selectByUserId(str);
            if (CollectionUtils.isEmpty(shopCarts)) {
                logger.info("【购物车系统】该用户购物车中无任何商品");
                return Result.buildFailedResult(-1, "您的购物车空空如也，快去选购吧~~~");
            }
            logger.info("购物车查询结果,:{}", new Gson().toJson(shopCarts));
            return Result.buildSuccessResult(shopCarts);
        } catch (IllegalArgumentException ie) {
            logger.error("【购物车系统】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【购物车系统】查询购物车信息时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }
    @RequestMapping(value ="keepDate.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result keepDate(@RequestBody String userId) {
        try {
            String str = new StringBuffer(userId).deleteCharAt(userId.length() - 1).toString();
            Preconditions.checkArgument(userId != null, "用户ID不可为空");
            logger.info("【购物车系统】请求查询购物车信息，请求数据:{}", new Gson().toJson(str));
            List<ShopCart> shopCarts = shopCartService.selectByUserId(str);
            if (CollectionUtils.isEmpty(shopCarts)) {
                logger.info("【购物车系统】该用户购物车中无任何商品");
                return Result.buildFailedResult(-1, "您的购物车空空如也，快去选购吧~~~");
            }
            logger.info("购物车查询结果,:{}", new Gson().toJson(shopCarts));
            return Result.buildSuccessResult(shopCarts);
        } catch (IllegalArgumentException ie) {
            logger.error("【购物车系统】非法参数异常",ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【购物车系统】查询购物车信息时出现异常",e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }
}
