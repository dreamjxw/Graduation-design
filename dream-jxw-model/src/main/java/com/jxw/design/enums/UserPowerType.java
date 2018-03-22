package com.jxw.design.enums;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/27 19:21
 * @Description 用户权限类型
 */
public enum UserPowerType {
    /**
     * 普通用户
     */
    CONSUMER("普通用户"),
    /**
     * 商家
     */
    BUSINESS("商家");
    String userPower;

    UserPowerType(String userPower) {
        this.userPower = userPower;
    }

    public String getUserPower() {
        return userPower;
    }

    public void setUserPower(String userPower) {
        this.userPower = userPower;
    }
}
