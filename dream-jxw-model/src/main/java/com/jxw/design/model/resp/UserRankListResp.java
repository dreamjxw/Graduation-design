package com.jxw.design.model.resp;

import java.io.Serializable;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 16:56
 */
public class UserRankListResp implements Serializable {
    private static final long serialVersionUID = -8594541228760196057L;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userHeadImg;

    /**
     * 用户粉丝数
     */
    private Long userFansNum;
    /**
     * 用户等级
     */
    private Long userLevel;

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

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public Long getUserFansNum() {
        return userFansNum;
    }

    public void setUserFansNum(Long userFansNum) {
        this.userFansNum = userFansNum;
    }

    public Long getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Long userLevel) {
        this.userLevel = userLevel;
    }
}
