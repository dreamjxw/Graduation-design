package com.jxw.design.exceptions;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/24  15:46
 * 更新数据库失败异常
 */
public class UpdateFailException extends RuntimeException {
    private static final long serialVersionUID = -6631848393448001423L;

    public UpdateFailException() {
        super();
    }

    public UpdateFailException(String message) {
        super(message);
    }
}
