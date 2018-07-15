package com.jxw.design.model;

import java.io.Serializable;

/**
 * @Author Xingwu.Jia [xingwuj@tujia.com]
 * @Date 2018/2/27 17:32
 * @Description 收货信息
 */
public class Post implements Serializable {
    private static final long serialVersionUID = 4608075345472843855L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户Id
     */
    private String userId;
    /**
     * 收货人姓名
     */
    private String postName;
    /**
     * 收货人电话
     */
    private String postPhone;
    /**
     * 收货人地址
     */
    private String postAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostPhone() {
        return postPhone;
    }

    public void setPostPhone(String postPhone) {
        this.postPhone = postPhone;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }
}
