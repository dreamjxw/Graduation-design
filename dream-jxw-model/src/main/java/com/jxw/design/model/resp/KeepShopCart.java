package com.jxw.design.model.resp;

import java.io.Serializable;

/**
 * @author Xingwu.Jia
 * @date 2018/5/22  20:38
 */
public class KeepShopCart implements Serializable {
    private static final long serialVersionUID = 1202136881134975696L;
    /**
     * 红酒 id
     */
    private Long wineId;
    /**
     * 红酒数量
     */
    private Integer wineNum;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 红酒图片
     */
    private String winePicture;
    /**
     * 红酒单价
     */
    private Double winePrice;

    /**
     * 酒品名称
     *
     * @return
     */
    private String wineName;

    /**
     * 酒品分数
     *
     * @return
     */
    private Double wineScore;
    /**
     * 订单总价
     */
    private Double totalPrice;
    /**
     * 订单商品总件数
     */
    private Integer totalNum;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public String getWineName() {
        return wineName;
    }

    public void setWineName(String wineName) {
        this.wineName = wineName;
    }

    public Double getWineScore() {
        return wineScore;
    }

    public void setWineScore(Double wineScore) {
        this.wineScore = wineScore;
    }

    public String getWinePicture() {
        return winePicture;
    }

    public void setWinePicture(String winePicture) {
        this.winePicture = winePicture;
    }

    public Double getWinePrice() {
        return winePrice;
    }

    public void setWinePrice(Double winePrice) {
        this.winePrice = winePrice;
    }

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
