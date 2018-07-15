package com.jxw.design.model.resp;

import java.io.Serializable;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/6 14:43
 * @Description 酒品展示resp
 */
public class ShowWineResp implements Serializable {
    private static final long serialVersionUID = 1845706284459132673L;
    /**
     * 红酒id
     */
    private Long wineId;
    /**
     * 红酒名
     */
    private String wineName;
    /**
     * 红酒价格
     */
    private Double winePrice;
    /**
     * 红酒图片
     */
    private String wineImg;

    public Long getWineId() {
        return wineId;
    }

    public void setWineId(Long wineId) {
        this.wineId = wineId;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public Double getWinePrice() {
        return winePrice;
    }

    public void setWinePrice(Double winePrice) {
        this.winePrice = winePrice;
    }

    public String getWineImg() {
        return wineImg;
    }

    public void setWineImg(String wineImg) {
        this.wineImg = wineImg;
    }
}
