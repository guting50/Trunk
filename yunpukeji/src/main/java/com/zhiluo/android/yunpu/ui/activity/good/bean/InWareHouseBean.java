package com.zhiluo.android.yunpu.ui.activity.good.bean;

/**
 * Created by YSL on 2018-11-21.
 */

public class InWareHouseBean {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : d1d9402d-e613-4d79-bd1f-48aa2325bf41
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
