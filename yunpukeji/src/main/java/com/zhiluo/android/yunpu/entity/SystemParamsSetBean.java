package com.zhiluo.android.yunpu.entity;

/**
 * 系统开关设置JavaBean
 * 作者：罗咏哲 on 2018/1/4 10:13.
 * 邮箱：137615198@qq.com
 */

public class SystemParamsSetBean {
    private String SS_Name;
    private int SS_Code;
    private int SS_State;
    private String SS_Value;

    public SystemParamsSetBean(String SS_Name, int SS_Code, int SS_State) {
        this.SS_Name = SS_Name;
        this.SS_Code = SS_Code;
        this.SS_State = SS_State;
    }

    public SystemParamsSetBean(String SS_Name, int SS_Code, int SS_State, String SS_Value) {
        this.SS_Name = SS_Name;
        this.SS_Code = SS_Code;
        this.SS_State = SS_State;
        this.SS_Value = SS_Value;
    }

    public String getSS_Name() {
        return SS_Name;
    }

    public void setSS_Name(String SS_Name) {
        this.SS_Name = SS_Name;
    }

    public int getSS_Code() {
        return SS_Code;
    }

    public void setSS_Code(int SS_Code) {
        this.SS_Code = SS_Code;
    }

    public int getSS_State() {
        return SS_State;
    }

    public void setSS_State(int SS_State) {
        this.SS_State = SS_State;
    }

    public String getSS_Value() {
        return SS_Value;
    }

    public void setSS_Value(String SS_Value) {
        this.SS_Value = SS_Value;
    }
}
