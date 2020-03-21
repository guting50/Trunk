package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;

/**
 * 自定义属性
 * Created by dell on 2018/2/28.
 */

public class CustomValueBean implements Serializable {
    public static final long UID = 1L;
    private String CF_GID;
    private String CF_FieldName;
    private String CF_FieldType;
    private String CF_ItemsValue;
    private String CF_Value;
    private String CF_Required;
    private String CY_GID;
    private String VIP_GID;
    private String CF_CreateUser;
    private String CF_CreateTime;
    private int CF_Order;
    private int CF_IsShowVIP;

    public String getCF_GID() {
        return CF_GID;
    }

    public void setCF_GID(String CF_GID) {
        this.CF_GID = CF_GID;
    }

    public String getCF_FieldName() {
        return CF_FieldName;
    }

    public void setCF_FieldName(String CF_FieldName) {
        this.CF_FieldName = CF_FieldName;
    }

    public String getCF_FieldType() {
        return CF_FieldType;
    }

    public void setCF_FieldType(String CF_FieldType) {
        this.CF_FieldType = CF_FieldType;
    }

    public String getCF_ItemsValue() {
        return CF_ItemsValue;
    }

    public void setCF_ItemsValue(String CF_ItemsValue) {
        this.CF_ItemsValue = CF_ItemsValue;
    }

    public String getCF_Value() {
        return CF_Value;
    }

    public void setCF_Value(String CF_Value) {
        this.CF_Value = CF_Value;
    }

    public String getCF_Required() {
        return CF_Required;
    }

    public void setCF_Required(String CF_Required) {
        this.CF_Required = CF_Required;
    }

    public String getCY_GID() {
        return CY_GID;
    }

    public void setCY_GID(String CY_GID) {
        this.CY_GID = CY_GID;
    }

    public String getVIP_GID() {
        return VIP_GID;
    }

    public void setVIP_GID(String VIP_GID) {
        this.VIP_GID = VIP_GID;
    }

    public String getCF_CreateUser() {
        return CF_CreateUser;
    }

    public void setCF_CreateUser(String CF_CreateUser) {
        this.CF_CreateUser = CF_CreateUser;
    }

    public String getCF_CreateTime() {
        return CF_CreateTime;
    }

    public void setCF_CreateTime(String CF_CreateTime) {
        this.CF_CreateTime = CF_CreateTime;
    }

    public int getCF_Order() {
        return CF_Order;
    }

    public void setCF_Order(int CF_Order) {
        this.CF_Order = CF_Order;
    }

    public int getCF_IsShowVIP() {
        return CF_IsShowVIP;
    }

    public void setCF_IsShowVIP(int CF_IsShowVIP) {
        this.CF_IsShowVIP = CF_IsShowVIP;
    }
}
