package com.jxw.design.service.impl;

import com.google.gson.Gson;
import com.jxw.design.dao.master.OrderDao;
import com.jxw.design.dao.master.ShopCartDao;
import com.jxw.design.dao.master.UserDao;
import com.jxw.design.dao.slave.OrderSlaveDao;
import com.jxw.design.dao.slave.UserSlaveDao;
import com.jxw.design.model.Order;
import com.jxw.design.model.OrderGoods;
import com.jxw.design.model.User;
import com.jxw.design.service.OrderService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/25 20:18
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private static final int overTime = 30;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderSlaveDao orderSlaveDao;
    @Autowired
    private ShopCartDao shopCartDao;
    @Autowired
    private UserSlaveDao userSlaveDao;
    @Autowired
    private UserDao userDao;

    @Override
    public int insertOrder(Order order) {
        logger.info("【订单系统】请求添加订单,请求参数:{}", new Gson().toJson(order));
        int i = orderDao.insertOrder(order);
        if (i > 0) {
            List<OrderGoods> orderGoods = order.getOrderGoods();
            int[] data = new int[orderGoods.size()];
            for (int j = 0; j < orderGoods.size(); j++) {
                data[j] = orderGoods.get(j).getWineId().intValue();
            }
            shopCartDao.deleteShopCartByWineIdBatch(order.getUserId(), data);
            logger.info("【订单系统】添加订单成功");
            return 1;
        }
        logger.info("【订单系统】添加订单失败");
        return 0;
    }

    @Override
    public int paymentOrder(Long orderId, String userId) {
        logger.info("【订单系统】请求支付订单,请求订单号:{}", new Gson().toJson(orderId));
        Order order = orderSlaveDao.selectByPrimaryKey(orderId);
        User user = userSlaveDao.obtainUserInfo(userId);
        if (order.getPayStatusId()==1){
            logger.info("【订单系统】该订单已被支付");
            return -2;
        }
        if (order != null && user != null) {
            if (order.getOrderDateStart().after(DateTime.now().minusMinutes(overTime).toDate())) {
                logger.info("【订单系统】订单支付超时");
                order.setOrderDateEnd(DateTime.now().toDate());
                order.setPayStatusId(2);
                return orderDao.updateOrderStatus(order);
            } else {
                logger.info("【订单系统】订单正常支付");
                if (order.getWineTotalPrice() <= user.getUserAccount()) {
                    order.setOrderDateEnd(DateTime.now().toDate());
                    order.setPayStatusId(1);
                    logger.info("【订单系统】账户余额" + user.getUserAccount() + "元，本次扣除" + order.getWineTotalPrice() + "元");
                    userDao.updateUserAccount(userId, user.getUserAccount() - order.getWineTotalPrice());
                    return orderDao.updateOrderStatus(order);
                }
                logger.info("【订单系统】账户余额不足");
                return 0;
            }
        }
        logger.info("【订单系统】查询不到该订单信息");
        return -1;
    }

    @Override
    public Order selectOrder(Long orderId) {
        logger.info("【订单系统】请求查询订单,请求订单号:{}", new Gson().toJson(orderId));
        return orderSlaveDao.selectByPrimaryKey(orderId);
    }

    @Override
    public List<Order> selectOrderByUserId(String userId) {
        return orderSlaveDao.selectByUserId(userId);
    }
}
