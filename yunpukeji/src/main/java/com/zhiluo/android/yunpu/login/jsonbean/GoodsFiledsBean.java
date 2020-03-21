package com.zhiluo.android.yunpu.login.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YSL on 2018-10-30.
 */

public class GoodsFiledsBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"CF_Order":1,"CF_GID":"fc0d2b47-1f1d-4e42-8bbe-d7692e97f67f","CF_FieldName":"商品2","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"","CF_Required":"是","CY_GID":"e6f9eab1-ebd0-4544-a246-d2e0f836d3f9","VIP_GID":"","PM_GID":"1","CF_CreateUser":"245912441@qq.com","CF_CreateTime":"2018-09-12 13:45:29"}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * CF_Order : 1
         * CF_GID : fc0d2b47-1f1d-4e42-8bbe-d7692e97f67f
         * CF_FieldName : 商品2
         * CF_FieldType : 文本
         * CF_ItemsValue :
         * CF_Value :
         * CF_Required : 是
         * CY_GID : e6f9eab1-ebd0-4544-a246-d2e0f836d3f9
         * VIP_GID :
         * PM_GID : 1
         * CF_CreateUser : 245912441@qq.com
         * CF_CreateTime : 2018-09-12 13:45:29
         */

        private int CF_Order;
        private String CF_GID;
        private String CF_FieldName;
        private String CF_FieldType;
        private String CF_ItemsValue;
        private String CF_Value;
        private String CF_Required;
        private String CY_GID;
        private String VIP_GID;
        private String PM_GID;
        private String CF_CreateUser;
        private String CF_CreateTime;

        public String getM_ItemsValue() {
            return M_ItemsValue;
        }

        public void setM_ItemsValue(String m_ItemsValue) {
            M_ItemsValue = m_ItemsValue;
        }

        private String M_ItemsValue;

        public int getCF_Order() {
            return CF_Order;
        }

        public void setCF_Order(int CF_Order) {
            this.CF_Order = CF_Order;
        }

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

        public String getPM_GID() {
            return PM_GID;
        }

        public void setPM_GID(String PM_GID) {
            this.PM_GID = PM_GID;
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
    }
}
