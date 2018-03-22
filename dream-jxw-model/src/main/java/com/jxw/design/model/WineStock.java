package com.jxw.design.model;

import java.io.Serializable;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/17 21:19
 */
public class WineStock implements Serializable {
    private static final long serialVersionUID = 5715450827239575331L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 红酒id
     */
    private Long wineId;
    /**
     * 红酒库存数量
     */
    private Long wineStock;
    /**
     * 红酒卖出数量
     */
    private Long wineReal;
    /**
     * 红酒既往库存数量
     */
    private Long wineAlwaysStock;
    /**
     * 红酒既往卖出数量
     */
    private Long wineAlwaysReal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    public Long getWineStock() {
        return wineStock;
    }

    public void setWineStock(Long wineStock) {
        this.wineStock = wineStock;
    }

    public Long getWineReal() {
        return wineReal;
    }

    public void setWineReal(Long wineReal) {
        this.wineReal = wineReal;
    }

    public Long getWineAlwaysStock() {
        return wineAlwaysStock;
    }

    public void setWineAlwaysStock(Long wineAlwaysStock) {
        this.wineAlwaysStock = wineAlwaysStock;
    }

    public Long getWineAlwaysReal() {
        return wineAlwaysReal;
    }

    public void setWineAlwaysReal(Long wineAlwaysReal) {
        this.wineAlwaysReal = wineAlwaysReal;
    }
}