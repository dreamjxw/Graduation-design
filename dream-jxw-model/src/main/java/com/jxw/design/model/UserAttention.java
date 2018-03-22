package com.jxw.design.model;

import java.io.Serializable;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/18 20:09
 */
public class UserAttention implements Serializable {
    private static final long serialVersionUID = -8478165642807206329L;
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 关注人id
     */
    private String attentionId;

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

    public String getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(String attentionId) {
        this.attentionId = attentionId == null ? null : attentionId.trim();
    }
}