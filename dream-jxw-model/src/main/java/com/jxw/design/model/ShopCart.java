package com.jxw.design.model;

import java.io.Serializable;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/17 20:49
 */
public class ShopCart implements Serializable {
    private static final long serialVersionUID = 2777747821669660605L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 红酒 id
     */
    private Long wineId;
    /**
     * 红酒名称
     */
    private String wineName;
    /**
     * 红酒评分
     */
    private Double wineScore;
    /**
     * 红酒图片
     */
    private String wineImg;
    /**
     * 红酒单价
     */
    private Double winePrice;
    /**
     * 红酒数量
     */
    private Integer wineNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

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
        this.wineName = wineName == null ? null : wineName.trim();
    }

    public Double getWineScore() {
        return wineScore;
    }

    public void setWineScore(Double wineScore) {
        this.wineScore = wineScore;
    }

    public String getWineImg() {
        return wineImg;
    }

    public void setWineImg(String wineImg) {
        this.wineImg = wineImg == null ? null : wineImg.trim();
    }

    public Double getWinePrice() {
        return winePrice;
    }

    public void setWinePrice(Double winePrice) {
        this.winePrice = winePrice;
    }

    public Integer getWineNum() {
        return wineNum;
    }

    public void setWineNum(Integer wineNum) {
        this.wineNum = wineNum;
    }

    public ShopCart() {
    }

    public ShopCart(Long id, String userId, Long wineId, String wineName, Double wineScore, String wineImg, Double
            winePrice, Integer wineNum) {
        this.id = id;
        this.userId = userId;
        this.wineId = wineId;
        this.wineName = wineName;
        this.wineScore = wineScore;
        this.wineImg = wineImg;
        this.winePrice = winePrice;
        this.wineNum = wineNum;
    }
}