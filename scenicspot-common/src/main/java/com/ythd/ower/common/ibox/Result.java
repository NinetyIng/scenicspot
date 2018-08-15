package com.ythd.ower.common.ibox;

public class Result<T> {
    private static final boolean SUCCESS = Boolean.TRUE;
    private static final boolean FAILURE = Boolean.FALSE;

    /**
     *  状态
     */
    private boolean isSuccess;

    /**
     * 数据
     */
    private T data;

    public Result() {

    }

    public Result(boolean status, T data) {
        this.isSuccess = status;
        this.data = data;
    }


    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isFailure() {
        return !isSuccess;
    }

    public void setSuccess(boolean status) {
        isSuccess = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS, data);
    }

    public static <T> Result<T> failure() {
        return failure(null);
    }

    public static <T> Result<T> failure(T data) {
        return new Result<>(FAILURE, data);
    }

    public static <T> Result<T> judge(boolean status, T data) {
        return new Result<>(status, data);
    }

    public static <T> Result<T> judge(boolean status) {
        return new Result<>(status, null);
    }
}
