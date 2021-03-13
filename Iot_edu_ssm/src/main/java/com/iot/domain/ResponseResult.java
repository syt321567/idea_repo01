package com.iot.domain;

public class ResponseResult {


    private  Boolean  success;
    private Integer state;
    private  String message;
    private  Object  context;

    public ResponseResult() {
    }

    public ResponseResult(Boolean success, Integer state, String message, Object context) {
        this.success = success;
        this.state = state;
        this.message = message;
        this.context = context;
    }


    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContext() {
        return context;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "success=" + success +
                ", state=" + state +
                ", message='" + message + '\'' +
                ", context=" + context +
                '}';
    }
}
