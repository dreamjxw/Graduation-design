package com.jxw.design.model.req;

import com.jxw.design.model.OrderGoods;

import java.io.Serializable;
import java.util.List;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/29 12:38
 */
public class OrderReq implements Serializable {
    private static final long serialVersionUID = -1878134396293863567L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 订单详情
     */
    private List<OrderGoods> orderGoods;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }
}
