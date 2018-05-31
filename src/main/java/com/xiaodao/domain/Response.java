package com.xiaodao.domain;

import java.io.Serializable;

/**
 * Created by hujt49 on 2018/05/31.
 */
public class Response implements Serializable {

    private Integer code;

    private String message;

    private Object result;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static Response ok(String msg, Object result) {
        Response response = new Response();
        response.setCode(1);
        response.setMessage(msg);
        response.setResult(result);
        return response;
    }

    public static Response fail(String msg) {
        Response response = new Response();
        response.setCode(-1);
        response.setMessage(msg);
        return response;
    }
}
