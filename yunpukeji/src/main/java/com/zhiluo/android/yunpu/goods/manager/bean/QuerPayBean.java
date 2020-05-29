package com.zhiluo.android.yunpu.goods.manager.bean;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by YSL on 2018-10-31.
 */

public class QuerPayBean implements Serializable {


    /**
     * success : false
     * code : 410004
     * msg : 410004
     * data : 1
     */

    private boolean success;
    private String code;
    private String msg;
    private QuerPayMsg data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return TextUtils.isEmpty(code) ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public QuerPayMsg getData() {
        return data;
    }

    public void setData(QuerPayMsg data) {
        this.data = data;
    }

    public static class QuerPayMsg {

        private String GID;

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getGID() {
            return GID;
        }
    }
}
