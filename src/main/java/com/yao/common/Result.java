package com.yao.common;

public class Result<T> {

    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = 0;
        this.msg = "SUCCESS";
        this.data = data;
    }

    private Result(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static<T> Result success(T data){
       return new Result(data);
    }

    public static<T> Result fail(int code, String msg){
        return new Result(code, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
