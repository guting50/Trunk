package com.zhiluo.android.yunpu.login.jsonbean;

import java.io.Serializable;

/**
 * Created by ZPH on 2019-05-29.
 */

public class FunctionListBean implements Serializable{


    /**
     * code : 800
     * name : 计时功能
     * type : 0
     * value : 0
     */

    private String code;
    private String name;
    private int type;
    private int value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
