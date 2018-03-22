package com.jxw.design.model.req;

import java.io.Serializable;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/28 11:20
 * @Description
 */
public class WineStockReq implements Serializable {
    private static final long serialVersionUID = -3337298774328997109L;
    /**
     * 红酒id
     */
    private Long wineId;
    /**
     * 红酒库存数量
     */
    private Long wineStock;

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
}
