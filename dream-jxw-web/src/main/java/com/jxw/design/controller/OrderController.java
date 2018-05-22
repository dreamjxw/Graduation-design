package com.jxw.design.controller;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.jxw.design.exceptions.UpdateFailException;
import com.jxw.design.model.Order;
import com.jxw.design.model.OrderGoods;
import com.jxw.design.model.Wine;
import com.jxw.design.model.req.OrderReq;
import com.jxw.design.service.OrderService;
import com.jxw.design.service.WineService;
import com.jxw.design.service.WineStockService;
import com.jxw.design.view.Result;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/25 20:22
 */
@CrossOrigin
@Controller
@RequestMapping("/jxw/design/order")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    private WineService wineService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private WineStockService wineStockService;


    @RequestMapping(value ="addOrder.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result addOrder(@RequestBody OrderReq orderReq) {
        try {
            Preconditions.checkArgument(orderReq.getUserId() != null, "用户ID不可为空");
            Preconditions.checkArgument(orderReq.getOrderGoods() != null, "订单信息不可为空");
            logger.info("【订单系统】请求下订单，请求参数:{}", new Gson().toJson(orderReq));
            List<OrderGoods> orderGoods = orderReq.getOrderGoods();
            for (int i = 0; i < orderGoods.size(); i++) {
                Preconditions.checkArgument(orderReq.getOrderGoods().get(i).getWineId() != null, "第" + i + "件商品ID不可为空");
                Preconditions.checkArgument(orderReq.getOrderGoods().get(i).getWineNum() != null, "第" + i +
                        "件商品数量不可为空");
            }
            double wineTotalPrice = 0;
            for (OrderGoods orderGood : orderGoods) {
                boolean b = wineStockService.checkStock(orderGood.getWineId(), Long.valueOf(orderGood.getWineNum()));
                if (!b) {
                    logger.error("【订单系统】添加订单失败,酒品" + orderGood.getWineId() + "号，库存不足!!!");
                    return Result.buildFailedResult(-1, "添加订单失败,酒品" + orderGood.getWineId() + "号，库存不足!!!");
                }
                int i = wineStockService.updateStock(orderGood.getWineId(), Long.valueOf(orderGood.getWineNum()));
                if (i > 0) {
                    Wine wine = wineService.selectWineByWineId(orderGood.getWineId());
                    orderGood.setWineName(wine.getWineName());
                    wineTotalPrice += orderGood.getWineNum() * wine.getWinePrice();
                } else {
                    new UpdateFailException("第" + orderGood.getWineId() + "个酒品库存更新失败");
                }
            }
            Order order = new Order();
            order.setUserId(orderReq.getUserId());
            order.setWineTotalPrice(wineTotalPrice);
            order.setOrderGoods(orderGoods);
            order.setOrderDateStart(DateTime.now().toDate());
            int i = orderService.insertOrder(order);
            if (i > 0) {
                logger.info("【订单系统】添加订单成功");
                return Result.buildSuccessResult("添加订单成功");
            }
            logger.info("【订单系统】添加订单失败");
            return Result.buildFailedResult(-1, "添加订单失败");
        } catch (IllegalArgumentException ie) {
            logger.error("【订单系统】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【订单系统】添加订单时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value ="paymentOrder.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result paymentOrder(@RequestBody Long orderId, HttpServletRequest req) {
        try {
            Preconditions.checkArgument(orderId != null, "订单号不可为空");
            String userId = req.getParameter("userId");
            logger.info("【订单系统】请求支付订单，请求参数:{}", new Gson().toJson(orderId));
            int i = orderService.paymentOrder(orderId, userId);
            if (i > 0) {
                logger.info("【订单系统】支付成功");
                return Result.buildSuccessResult("支付成功");
            }
            if (i == 0) {
                logger.info("【订单系统】账户余额不足");
                return Result.buildFailedResult(-1, "账户余额不足");
            }
            if (i == -2) {
                logger.info("【订单系统】该订单已被支付");
                return Result.buildFailedResult(-1, "该订单已被支付");
            }
            logger.info("【订单系统】支付失败");
            return Result.buildFailedResult(-1, "支付失败");
        } catch (IllegalArgumentException ie) {
            logger.error("【订单系统】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【订单系统】支付订单时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value ="selectOrder.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result selectOrder(@RequestBody Long orderId) {
        try {
            Preconditions.checkArgument(orderId != null, "订单号不可为空");
            logger.info("【订单系统】请求查询订单，请求参数:{}", new Gson().toJson(orderId));
            Order order = orderService.selectOrder(orderId);
            if (order != null) {
                logger.info("【订单系统】查询订单结果:{}", new Gson().toJson(order));
                return Result.buildSuccessResult(order);
            }
            logger.info("【订单系统】查询订单结果为空");
            return Result.buildFailedResult(-1, "该订单不存在");
        } catch (IllegalArgumentException ie) {
            logger.error("【订单系统】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【订单系统】查询订单时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }

    @RequestMapping(value ="selectOrderByUser.htm", method = RequestMethod.POST)
    @ResponseBody
    public Result selectOrderByUserId(@RequestBody String userId) {
        try {
            Preconditions.checkArgument(userId != null, "用户ID不可为空");
            logger.info("【订单系统】请求查询订单，请求参数:{}", new Gson().toJson(userId));
            List<Order> orderList = orderService.selectOrderByUserId(userId);
            if (CollectionUtils.isEmpty(orderList)) {
                logger.info("【订单系统】查询订单结果为空");
                return Result.buildFailedResult(-1, "您还没有下过订单...快去购买吧~~~");
            }
            logger.info("【订单系统】查询订单结果:{}", new Gson().toJson(orderList));
            return Result.buildSuccessResult(orderList);
        } catch (IllegalArgumentException ie) {
            logger.error("【订单系统】非法参数异常", ie);
            return Result.buildFailedResult(-1, "非法参数异常");
        } catch (Exception e) {
            logger.error("【订单系统】查询订单时出现异常", e);
            return Result.buildFailedResult(-1, "服务器开小差了~~  请稍后重试");
        }
    }
}
