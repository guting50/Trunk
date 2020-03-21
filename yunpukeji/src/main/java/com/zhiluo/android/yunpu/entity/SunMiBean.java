package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;

public class SunMiBean implements Serializable {

    private String snCode;
    private int code;

    public String getSnCode() {
        return snCode;
    }

    public void setSnCode(String snCode) {
        this.snCode = snCode;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
