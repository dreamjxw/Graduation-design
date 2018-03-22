package com.jxw.design.model.resp;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 16:56
 */
public class UserRankListResp {

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
