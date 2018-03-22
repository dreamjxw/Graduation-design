package com.jxw.design.exceptions;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/24  15:46
 * task异常
 */
public class TaskRunErrorException extends RuntimeException {
    private static final long serialVersionUID = -6631848393448001423L;

    public TaskRunErrorException() {
        super();
    }

    public TaskRunErrorException(String message) {
        super(message);
    }
    public TaskRunErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
