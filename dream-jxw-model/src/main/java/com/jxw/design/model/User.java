package com.jxw.design.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/17 20:09
 */
public class User implements Serializable {
    private static final long serialVersionUID = 8935073391029989705L;
    /**
     * 主键id
     */
    private BigDecimal id;
    /**
     * 用户id（微信端openid）
     */
    private String userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户性别
     */
    private String userSex;
    /**
     * 用户所在城市
     */
    private String userCity;
    /**
     * 用户所在省份
     */
    private String userProvince;
    /**
     * 用户所在国家
     */
    private String userCountry;
    /**
     * 用户头像
     */
    private String userHeadImg;
    /**
     * 用户权限 （商户、用户）
     */
    private String userPower;
    /**
     * 用户粉丝数
     */
    private Long userFansNum;
    /**
     * 用户关注数
     */
    private Long userAttentionNum;
    /**
     * 用户等级
     */
    private Long userLevel;
    /**
     * 用户账户余额
     */
    private Double userAccount;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getUserPower() {
        return userPower;
    }

    public void setUserPower(String userPower) {
        this.userPower = userPower;
    }

    public Long getUserFansNum() {
        return userFansNum;
    }

    public void setUserFansNum(Long userFansNum) {
        this.userFansNum = userFansNum;
    }

    public Long getUserAttentionNum() {
        return userAttentionNum;
    }

    public void setUserAttentionNum(Long userAttentionNum) {
        this.userAttentionNum = userAttentionNum;
    }

    public Long getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Long userLevel) {
        this.userLevel = userLevel;
    }

    public Double getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Double userAccount) {
        this.userAccount = userAccount;
    }
}
