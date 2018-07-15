package com.jxw.design.model;

import java.io.Serializable;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/21  19:02
 */
public class Wine extends WineStock implements Serializable {
    private static final long serialVersionUID = 3917778030365191179L;
    /**
     * 红酒id
     */
    private transient Long wineId;
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
    /**
     * 红酒产地
     */
    private String wineAddress;
    /**
     * 红酒年份
     */
    private String wineYear;
    /**
     * 红酒类别
     */
    private String wineClass;
    /**
     * 红酒评分
     */
    private Double wineScore;
    /**
     * 红酒品牌
     */
    private String wineBrand;

    @Override
    public Long getWineId() {
        return wineId;
    }

    @Override
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

    public String getWineAddress() {
        return wineAddress;
    }

    public void setWineAddress(String wineAddress) {
        this.wineAddress = wineAddress;
    }

    public String getWineYear() {
        return wineYear;
    }

    public void setWineYear(String wineYear) {
        this.wineYear = wineYear;
    }

    public String getWineClass() {
        return wineClass;
    }

    public void setWineClass(String wineClass) {
        this.wineClass = wineClass;
    }

    public Double getWineScore() {
        return wineScore;
    }

    public void setWineScore(Double wineScore) {
        this.wineScore = wineScore;
    }

    public String getWineBrand() {
        return wineBrand;
    }

    public void setWineBrand(String wineBrand) {
        this.wineBrand = wineBrand;
    }
}
