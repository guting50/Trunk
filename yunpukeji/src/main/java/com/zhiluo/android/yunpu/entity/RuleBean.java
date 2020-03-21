package com.zhiluo.android.yunpu.entity;

import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/23 11:41.
 * 邮箱：137615198@qq.com
 * 员工部门
 */

public class RuleBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"e62e3c69-5597-4484-bfe3-d433c0f09e90","CY_GID":null,"DM_Name":"销售部","DM_Remark":"","DM_UpdateTime":"2017-12-29 14:48:45","DM_Creator":"17358638575"},{"GID":"f121c7be-5480-4267-8f97-2dcbbe94d2b2","CY_GID":null,"DM_Name":"售后部","DM_Remark":"","DM_UpdateTime":"2017-12-29 14:49:00","DM_Creator":"17358638575"}]
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

    public static class DataBean {
        /**
         * GID : e62e3c69-5597-4484-bfe3-d433c0f09e90
         * CY_GID : null
         * DM_Name : 销售部
         * DM_Remark :
         * DM_UpdateTime : 2017-12-29 14:48:45
         * DM_Creator : 17358638575
         */

        private String GID;
        private Object CY_GID;
        private String DM_Name;
        private String DM_Remark;
        private String DM_UpdateTime;
        private String DM_Creator;
        private boolean check;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

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
