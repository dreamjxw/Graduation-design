package com.jxw.design.model;

import java.io.Serializable;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/17 20:22
 */
public class OrderGoods implements Serializable {
    private static final long serialVersionUID = -3572806481602672266L;
    /**
     * 红酒id
     */
    private Long wineId;
    /**
     * 红酒名
     */
    private String wineName;
    /**
     * 红酒数量
     */
    private Integer wineNum;

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

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public OrderGoods() {
    }

    public OrderGoods(Long wineId, String wineName, Integer wineNum) {
        this.wineId = wineId;
        this.wineName = wineName;
        this.wineNum = wineNum;
    }
}