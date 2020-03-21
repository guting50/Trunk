package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2019-07-23.
 */

public class DeptBean implements Serializable{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"506400d2-dd4c-45a3-8c8e-ab964a8c3cf6","CY_GID":null,"DM_Name":"营销","DM_Remark":"","DM_UpdateTime":"2018-06-29 16:07:23","DM_Creator":"1058346971@qq.com"},{"GID":"142ec34a-2451-4af4-bd84-aebd2c6ce713","CY_GID":null,"DM_Name":"销售","DM_Remark":"","DM_UpdateTime":"2018-06-29 16:07:43","DM_Creator":"1058346971@qq.com"},{"GID":"473c9d88-d53c-40d5-9896-e9df345cb585","CY_GID":null,"DM_Name":"吃鸡小分队","DM_Remark":"","DM_UpdateTime":"2018-08-29 16:43:50","DM_Creator":"销售001"}]
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
         * GID : 506400d2-dd4c-45a3-8c8e-ab964a8c3cf6
         * CY_GID : null
         * DM_Name : 营销
         * DM_Remark :
         * DM_UpdateTime : 2018-06-29 16:07:23
         * DM_Creator : 1058346971@qq.com
         */

        private String GID;
        private Object CY_GID;
        private String DM_Name;
        private String DM_Remark;
        private String DM_UpdateTime;
        private String DM_Creator;

        public boolean isCheck() {
            return Check;
        }

        public void setCheck(boolean check) {
            Check = check;
        }

        private boolean Check;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public Object getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(Object CY_GID) {
            this.CY_GID = CY_GID;
        }

        public String getDM_Name() {
            return DM_Name;
        }

        public void setDM_Name(String DM_Name) {
            this.DM_Name = DM_Name;
        }

        public String getDM_Remark() {
            return DM_Remark;
        }

        public void setDM_Remark(String DM_Remark) {
            this.DM_Remark = DM_Remark;
        }

        public String getDM_UpdateTime() {
            return DM_UpdateTime;
        }

        public void setDM_UpdateTime(String DM_UpdateTime) {
            this.DM_UpdateTime = DM_UpdateTime;
        }

        public String getDM_Creator() {
            return DM_Creator;
        }

        public void setDM_Creator(String DM_Creator) {
            this.DM_Creator = DM_Creator;
        }
    }
}
