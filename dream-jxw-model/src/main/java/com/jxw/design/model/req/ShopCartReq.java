package com.jxw.design.model.req;

import java.io.Serializable;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/26 19:29
 * @Description
 */
public class ShopCartReq implements Serializable {
    private static final long serialVersionUID = 8178450860140003545L;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 红酒 id
     */
    private Long wineId;
    /**
     * 红酒数量
     */
    private Integer wineNum;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    public Integer getWineNum() {
        return wineNum;
    }

    public void setWineNum(Integer wineNum) {
        this.wineNum = wineNum;
    }
}
