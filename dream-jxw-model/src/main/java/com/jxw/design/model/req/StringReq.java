package com.jxw.design.model.req;

import com.jxw.design.model.Wine;

import java.io.Serializable;

/**
 * @author Xingwu.Jia
 * @date 2018/5/26  17:43
 */
public class StringReq extends Wine implements Serializable {
    private static final long serialVersionUID = 3823629143408982334L;
    /**
     * 传入的String类型的参数
     */
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
