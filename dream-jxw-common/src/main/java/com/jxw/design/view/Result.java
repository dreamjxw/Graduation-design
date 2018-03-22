package com.jxw.design.view;

/**
 * @author Xingwu.Jia [xingwuj@tujia.com]
 * @date 2018/1/19 16:51
 */
public class Result<T> {
    private static final String ver = "1.0";
    /**
     * 请求成功or失败标记
     */
    private boolean ret;
    /**
     * 信息
     */
    private String msg;
    /**
     * 错误对象
     */
    private Object[] errargs;
    /**
     * 返回数据
     */
    private T data;

    public Result() {
    }

    public Result(T t) {
        this.ret = true;
        this.data = t;
    }

    public Result(int code, String msg) {
        this.ret = false;
        this.msg = msg;
    }

    public Result(int code, String msg, Object[] errargs) {
        this.ret = false;
        this.msg = msg;
        this.errargs = errargs;
    }

    public static <T> Result<T> buildSuccessResult() {
        Result result = new Result<T>();
        result.setRet(true);
        return result;
    }

    public static <T> Result<T> buildSuccessResult(T t) {
        return new Result<T>(t);
    }

    public static <T> Result<T> buildFailedResult(int code, String msg) {
        return new Result<T>(code, msg);
    }

    public static <T> Result<T> buildFailedResult(int code, String msg, Object[] errArgs) {
        return new Result<T>(code, msg, errArgs);
    }

    public String getVer() {
        return ver;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object[] getErrargs() {
        return errargs;
    }

    public void setErrargs(Object[] errargs) {
        this.errargs = errargs;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
