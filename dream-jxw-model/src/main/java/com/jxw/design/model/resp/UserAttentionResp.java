package com.jxw.design.model.resp;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/24 20:20
 */
public class UserAttentionResp {
    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userHeadImg;

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
}
