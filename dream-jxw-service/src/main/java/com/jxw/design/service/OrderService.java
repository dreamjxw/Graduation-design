package com.jxw.design.service;

import com.jxw.design.model.Order;

import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/25 20:16
 */
public interface OrderService {
    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    int insertOrder(Order order);

    /**
     * 支付订单
     *
     * @param orderId
     * @param userId
     * @return
     */
    int paymentOrder(Long orderId, String userId);

    /**
     * 查询订单
     *
     * @param orderId
     * @return
     */
    Order selectOrder(Long orderId);

    /**
     * 根据用户Id查询订单
     *
     * @param userId
     * @return
     */
    List<Order> selectOrderByUserId(String userId);
}
