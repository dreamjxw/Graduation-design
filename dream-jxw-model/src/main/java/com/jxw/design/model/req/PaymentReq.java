package com.jxw.design.model.req;

import java.io.Serializable;

/**
 * @author Xingwu.Jia
 * @date 2018/5/26  23:11
 */
public class PaymentReq implements Serializable {
    private static final long serialVersionUID = -2927595076467177353L;
    /**
     * 订单Id
     */
    private Long orderId;
    /**
     * 用户Id
     */
    private String userId;

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
        this.userId = userId;
    }
}
