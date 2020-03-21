package com.zhiluo.android.yunpu.member.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 会员标签JavaBean
 * 作者：罗咏哲 on 2017/7/20 10:20.
 * 邮箱：137615198@qq.com
 */

public class MemberLabel {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"ML_GID":"3e2eb32e-f8c7-470f-98d8-6b6be4a2f729","ML_StoreID":null,"ML_CYGID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","ML_Name":"标签4","ML_ColorValue":"#ff0000","ML_CreateTime":"2017-06-28 13:55:34","ML_CreateUser":"1540004275@qq.com","ML_Remark":"444444444"},{"ML_GID":"8bf90237-a654-48ec-a30c-5eac43e9afd6","ML_StoreID":null,"ML_CYGID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","ML_Name":"标签3","ML_ColorValue":"#ff0000","ML_CreateTime":"2017-06-28 13:55:25","ML_CreateUser":"1540004275@qq.com","ML_Remark":"3333333333"},{"ML_GID":"9a020618-94ac-4121-b126-96abbc637827","ML_StoreID":null,"ML_CYGID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","ML_Name":"标签5","ML_ColorValue":"#ff0000","ML_CreateTime":"2017-06-28 13:55:47","ML_CreateUser":"1540004275@qq.com","ML_Remark":"555555555555555555"},{"ML_GID":"9c717722-3dcb-4199-bda6-85c30182465b","ML_StoreID":null,"ML_CYGID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","ML_Name":"标签1","ML_ColorValue":"#0000ff","ML_CreateTime":"2017-06-28 13:53:43","ML_CreateUser":"1540004275@qq.com","ML_Remark":"标签1111111"},{"ML_GID":"f9f8b754-de26-4cea-8090-4243a05e196e","ML_StoreID":null,"ML_CYGID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","ML_Name":"标签2","ML_ColorValue":"#ff0000","ML_CreateTime":"2017-06-28 13:55:16","ML_CreateUser":"1540004275@qq.com","ML_Remark":"222222222222222"}]
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

    public static class DataBean implements Serializable {
        private static final long UID = 1L;
        /**
         * ML_GID : 3e2eb32e-f8c7-470f-98d8-6b6be4a2f729
         * ML_StoreID : null
         * ML_CYGID : 348e79f2-ff35-4d62-8298-9d37f06fd5e0
         * ML_Name : 标签4
         * ML_ColorValue : #ff0000
         * ML_CreateTime : 2017-06-28 13:55:34
         * ML_CreateUser : 1540004275@qq.com
         * ML_Remark : 444444444
         */

        private String ML_GID;
        private Object ML_StoreID;
        private String ML_CYGID;
        private String ML_Name;
        private String ML_ColorValue;
        private String ML_CreateTime;
        private String ML_CreateUser;
        private String ML_Remark;
        private int ML_Type;

        public String getML_GID() {
            return ML_GID;
        }

        public void setML_GID(String ML_GID) {
            this.ML_GID = ML_GID;
        }

        public Object getML_StoreID() {
            return ML_StoreID;
        }

        public void setML_StoreID(Object ML_StoreID) {
            this.ML_StoreID = ML_StoreID;
        }

        public String getML_CYGID() {
            return ML_CYGID;
        }

        public void setML_CYGID(String ML_CYGID) {
            this.ML_CYGID = ML_CYGID;
        }

        public String getML_Name() {
            return ML_Name;
        }

        public void setML_Name(String ML_Name) {
            this.ML_Name = ML_Name;
        }

        public String getML_ColorValue() {
            return ML_ColorValue;
        }

        public void setML_ColorValue(String ML_ColorValue) {
            this.ML_ColorValue = ML_ColorValue;
        }

        public String getML_CreateTime() {
            return ML_CreateTime;
        }

        public void setML_CreateTime(String ML_CreateTime) {
            this.ML_CreateTime = ML_CreateTime;
        }

        public String getML_CreateUser() {
            return ML_CreateUser;
        }

        public void setML_CreateUser(String ML_CreateUser) {
            this.ML_CreateUser = ML_CreateUser;
        }

        public String getML_Remark() {
            return ML_Remark;
        }

        public void setML_Remark(String ML_Remark) {
            this.ML_Remark = ML_Remark;
        }

        public int getML_Type() {
            return ML_Type;
        }

        public void setML_Type(int ML_Type) {
            this.ML_Type = ML_Type;
        }
    }
}
