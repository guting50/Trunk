package com.zhiluo.android.yunpu.consume.bean;

/**
 * Created by ZPH on 2019-05-16.
 */

public class LimitedTimeConsumeBean {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : 66312369-eed9-43e8-89c2-c442a5dda816
     */

    private boolean success;
    private Object code;
    private String msg;
    private String data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
