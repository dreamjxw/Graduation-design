package com.jxw.design.model;

import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/17 20:19
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 3001944946965238857L;
    /**
     * 订单号
     */
    private Long orderId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 订单总价
     */
    private Double wineTotalPrice;
    /**
     * 订单详情
     */
    private List<OrderGoods> orderGoods;
    /**
     * 订单创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date orderDateStart;
    /**
     * 订单支付时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date orderDateEnd;
    /**
     * 订单状态   0：未支付    1：已支付    2.支付超时
     */
    private Integer payStatusId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Double getWineTotalPrice() {
        return wineTotalPrice;
    }

    public void setWineTotalPrice(Double wineTotalPrice) {
        this.wineTotalPrice = wineTotalPrice;
    }

    public Date getOrderDateStart() {
        return orderDateStart;
    }

    public void setOrderDateStart(Date orderDateStart) {
        this.orderDateStart = orderDateStart;
    }

    public Date getOrderDateEnd() {
        return orderDateEnd;
    }

    public void setOrderDateEnd(Date orderDateEnd) {
        this.orderDateEnd = orderDateEnd;
    }

    public Integer getPayStatusId() {
        return payStatusId;
    }

    public void setPayStatusId(Integer payStatusId) {
        this.payStatusId = payStatusId;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }
}