package com.zhiluo.android.yunpu.goods.consume.bean;

import java.util.List;

public class ResultManyModelBean {
    private boolean success;
    private Object code;
    private String msg;
    private List<GoodsModelBean> data;

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

    public List<GoodsModelBean> getData() {
        return data;
    }

    public void setData(List<GoodsModelBean> data) {
        this.data = data;
    }
}
