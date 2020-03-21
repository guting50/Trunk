package com.zhiluo.android.yunpu.sms.jsonbean;

import java.util.List;

/**
 * Created by ZPH on 2019-03-30.
 */

public class MsgTemplateListBean {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"94273827-e0bd-4489-8d6a-8b8fa3e0a7e9","TC_GID":"d9e07983-10da-11e6-9174-1c872c481c7f","TC_ClassName":"用户定义","TC_ClassCode":"017","TM_Name":"手机测试模板 添","TM_Content":"测试模板添加#会员名称#","TM_Creator":"分号1","TM_Update":null,"TM_CreatorTime":"2019-03-30 17:11:01","TM_UpdateTime":null,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TM_Group":2},{"GID":"a705c93c-737a-4e30-a8cd-808b8c1ace08","TC_GID":"d9e07983-10da-11e6-9174-1c872c481c7f","TC_ClassName":"用户定义","TC_ClassCode":"017","TM_Name":"手机测试模板 添","TM_Content":"测试模板添加#会员名称#","TM_Creator":"分号1","TM_Update":null,"TM_CreatorTime":"2019-03-30 17:11:33","TM_UpdateTime":null,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TM_Group":2},{"GID":"bed035cb-d75a-42cb-9717-11db5e874a69","TC_GID":"d9e07983-10da-11e6-9174-1c872c481c7f","TC_ClassName":"用户定义","TC_ClassCode":"017","TM_Name":"手机测试模板 添","TM_Content":"测试模板添加#会员名称#","TM_Creator":"分号1","TM_Update":null,"TM_CreatorTime":"2019-03-30 17:10:59","TM_UpdateTime":null,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","TM_Group":2}]
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
         * GID : 94273827-e0bd-4489-8d6a-8b8fa3e0a7e9
         * TC_GID : d9e07983-10da-11e6-9174-1c872c481c7f
         * TC_ClassName : 用户定义
         * TC_ClassCode : 017
         * TM_Name : 手机测试模板 添
         * TM_Content : 测试模板添加#会员名称#
         * TM_Creator : 分号1
         * TM_Update : null
         * TM_CreatorTime : 2019-03-30 17:11:01
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
