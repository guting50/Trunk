package com.zhiluo.android.yunpu.sms.jsonbean;

import java.util.List;

/**
 * Created by ZPH on 2019-03-28.
 */

public class OwnSMSJsonBean {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"e007f4b8-a8ba-4a35-bf48-cb490a627d36","TC_GID":"d9e07983-10da-11e6-9174-1c872c481c7f","TC_ClassName":"用户定义","TC_ClassCode":"017","TM_Name":"板添加测试","TM_Content":"板添加测试","TM_Creator":"销售001","TM_Update":null,"TM_CreatorTime":"2019-03-28 09:57:31","TM_UpdateTime":null,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TM_Group":2}]
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
         * GID : e007f4b8-a8ba-4a35-bf48-cb490a627d36
         * TC_GID : d9e07983-10da-11e6-9174-1c872c481c7f
         * TC_ClassName : 用户定义
         * TC_ClassCode : 017
         * TM_Name : 板添加测试
         * TM_Content : 板添加测试
         * TM_Creator : 销售001
         * TM_Update : null
         * TM_CreatorTime : 2019-03-28 09:57:31
         * TM_UpdateTime : null
         * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
         * TM_Group : 2
         */

        private String GID;
        private String TC_GID;
        private String TC_ClassName;
        private String TC_ClassCode;
        private String TM_Name;
        private String TM_Content;
        private String TM_Creator;
        private Object TM_Update;
        private String TM_CreatorTime;
        private Object TM_UpdateTime;
        private String CY_GID;
        private int TM_Group;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getTC_GID() {
            return TC_GID;
        }

        public void setTC_GID(String TC_GID) {
            this.TC_GID = TC_GID;
        }

        public String getTC_ClassName() {
            return TC_ClassName;
        }

        public void setTC_ClassName(String TC_ClassName) {
            this.TC_ClassName = TC_ClassName;
        }

        public String getTC_ClassCode() {
            return TC_ClassCode;
        }

        public void setTC_ClassCode(String TC_ClassCode) {
            this.TC_ClassCode = TC_ClassCode;
        }

        public String getTM_Name() {
            return TM_Name;
        }

        public void setTM_Name(String TM_Name) {
            this.TM_Name = TM_Name;
        }

        public String getTM_Content() {
            return TM_Content;
        }

        public void setTM_Content(String TM_Content) {
            this.TM_Content = TM_Content;
        }

        public String getTM_Creator() {
            return TM_Creator;
        }

        public void setTM_Creator(String TM_Creator) {
            this.TM_Creator = TM_Creator;
        }

        public Object getTM_Update() {
            return TM_Update;
        }

        public void setTM_Update(Object TM_Update) {
            this.TM_Update = TM_Update;
        }

        public String getTM_CreatorTime() {
            return TM_CreatorTime;
        }

        public void setTM_CreatorTime(String TM_CreatorTime) {
            this.TM_CreatorTime = TM_CreatorTime;
        }

        public Object getTM_UpdateTime() {
            return TM_UpdateTime;
        }

        public void setTM_UpdateTime(Object TM_UpdateTime) {
            this.TM_UpdateTime = TM_UpdateTime;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public int getTM_Group() {
            return TM_Group;
        }

        public void setTM_Group(int TM_Group) {
            this.TM_Group = TM_Group;
        }
    }
}
