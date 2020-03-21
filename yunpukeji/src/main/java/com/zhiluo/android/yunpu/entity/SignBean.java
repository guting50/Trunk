package com.zhiluo.android.yunpu.entity;

/**
 * 短信签名实体
 * 作者：罗咏哲 on 2018/2/5 09:44.
 * 邮箱：137615198@qq.com
 */

public class SignBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GID":"b72debbe-774c-4382-9abd-aabb82152668","SM_Name":"智络科技","SM_State":1,"SM_Remark":null,"SM_Creator":"18242321639","SM_Update":"18242321639","SM_CreatorTime":"2017-12-29 11:19:30","SM_UpdateTime":"2017-12-29 11:19:30","CY_GID":"61ec52b1-6da8-415a-b66a-9cec063bda5d","SM_GID":"a75cbe54-a1c2-44a1-a0bc-274b2771b9ba"}
     */

    private boolean success;
    private Object code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * GID : b72debbe-774c-4382-9abd-aabb82152668
         * SM_Name : 智络科技
         * SM_State : 1
         * SM_Remark : null
         * SM_Creator : 18242321639
         * SM_Update : 18242321639
         * SM_CreatorTime : 2017-12-29 11:19:30
         * SM_UpdateTime : 2017-12-29 11:19:30
         * CY_GID : 61ec52b1-6da8-415a-b66a-9cec063bda5d
         * SM_GID : a75cbe54-a1c2-44a1-a0bc-274b2771b9ba
         */

        private String GID;
        private String SM_Name;
        private int SM_State;
        private Object SM_Remark;
        private String SM_Creator;
        private String SM_Update;
        private String SM_CreatorTime;
        private String SM_UpdateTime;
        private String CY_GID;
        private String SM_GID;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

        public int getSM_State() {
            return SM_State;
        }

        public void setSM_State(int SM_State) {
            this.SM_State = SM_State;
        }

        public Object getSM_Remark() {
            return SM_Remark;
        }

        public void setSM_Remark(Object SM_Remark) {
            this.SM_Remark = SM_Remark;
        }

        public String getSM_Creator() {
            return SM_Creator;
        }

        public void setSM_Creator(String SM_Creator) {
            this.SM_Creator = SM_Creator;
        }

        public String getSM_Update() {
            return SM_Update;
        }

        public void setSM_Update(String SM_Update) {
            this.SM_Update = SM_Update;
        }

        public String getSM_CreatorTime() {
            return SM_CreatorTime;
        }

        public void setSM_CreatorTime(String SM_CreatorTime) {
            this.SM_CreatorTime = SM_CreatorTime;
        }

        public String getSM_UpdateTime() {
            return SM_UpdateTime;
        }

        public void setSM_UpdateTime(String SM_UpdateTime) {
            this.SM_UpdateTime = SM_UpdateTime;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public String getSM_GID() {
            return SM_GID;
        }

        public void setSM_GID(String SM_GID) {
            this.SM_GID = SM_GID;
        }
    }
}
