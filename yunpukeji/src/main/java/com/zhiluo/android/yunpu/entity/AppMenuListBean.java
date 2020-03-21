package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * App菜单集合
 * Created by dell on 2018/2/26.
 */

public class AppMenuListBean implements Serializable {
    public static final long UID = 1L;
    private boolean success;
    private Object code;
    private String msg;
    private List<AppMenuBean> data;

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

    public List<AppMenuBean> getData() {
        return data;
    }

    public void setData(List<AppMenuBean> data) {
        this.data = data;
    }

}
