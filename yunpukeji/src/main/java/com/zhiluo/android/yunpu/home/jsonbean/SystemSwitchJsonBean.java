package com.zhiluo.android.yunpu.home.jsonbean;

import java.util.List;

/**
 * Created by Cheng on 2017/3/31.
 */

public class SystemSwitchJsonBean
{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"SS_Value":null,"GID":"7bf3ec78-3749-459b-9146-29a26a6df651","SS_Name":"现金支付","SS_Code":"101","SS_State":1,"SS_Remark":null,"SS_Update":"18986810706",
     * "SS_UpdateTime":"2017-03-31 21:19:37","CY_GID":null,"SS_Sort":1},{"SS_Value":null,"GID":"bfb5f74c-39d7-4893-b97b-6ac32fb7cd4c","SS_Name":"余额支付","SS_Code":"102",
     * "SS_State":1,"SS_Remark":null,"SS_Update":"18986810706","SS_UpdateTime":"2017-03-31 21:19:37","CY_GID":null,"SS_Sort":2},{"SS_Value":null,
     * "GID":"edfaf64f-cb54-440d-a528-21ac4b955923","SS_Name":"银联支付","SS_Code":"103","SS_State":1,"SS_Remark":null,"SS_Update":"18986810706","SS_UpdateTime":"2017-03-31
     * 21:19:37","CY_GID":null,"SS_Sort":3},{"SS_Value":null,"GID":"0108a739-33b6-42a2-904d-c4a471ef9641","SS_Name":"会员卡号同手机号","SS_Code":"201","SS_State":0,"SS_Remark":null,
     * "SS_Update":"18986810706","SS_UpdateTime":"2017-03-31 21:19:37","CY_GID":null,"SS_Sort":4},{"SS_Value":null,"GID":"c5e84ab1-8c25-4717-b2ad-0b40eb171234",
     * "SS_Name":"卡片余额和剩余积分","SS_Code":"203","SS_State":0,"SS_Remark":null,"SS_Update":"18986810706","SS_UpdateTime":"2017-03-31 21:19:37","CY_GID":null,"SS_Sort":5},
     * {"SS_Value":null,"GID":"69a636b1-9cd7-4dfe-b1dd-17f99e7b28d9","SS_Name":"员工提成","SS_Code":"301","SS_State":0,"SS_Remark":null,"SS_Update":"18986810706",
     * "SS_UpdateTime":"2017-03-31 21:19:37","CY_GID":null,"SS_Sort":6},{"SS_Value":"111111","GID":"afa2c587-4f42-4045-87c4-bb719e6f9b06","SS_Name":"开卡初始密码","SS_Code":"202",
     * "SS_State":1,"SS_Remark":null,"SS_Update":"18986810706","SS_UpdateTime":"2017-03-31 21:19:37","CY_GID":null,"SS_Sort":7},{"SS_Value":null,
     * "GID":"145d73a2-77c8-4ae5-8970-13435a0130a0","SS_Name":"消费密码验证","SS_Code":"204","SS_State":1,"SS_Remark":null,"SS_Update":"18986810706","SS_UpdateTime":"2017-03-31
     * 21:19:37","CY_GID":null,"SS_Sort":8},{"SS_Value":null,"GID":"8dbec491-4224-4ff1-937f-dee926cdca6f","SS_Name":"转账密码验证","SS_Code":"205","SS_State":0,"SS_Remark":null,
     * "SS_Update":"18986810706","SS_UpdateTime":"2017-03-31 21:19:37","CY_GID":null,"SS_Sort":9},{"SS_Value":null,"GID":"bb013055-d1dd-42a5-8e9d-85d62033bc08",
     * "SS_Name":"兑换密码验证","SS_Code":"206","SS_State":0,"SS_Remark":null,"SS_Update":"18986810706","SS_UpdateTime":"2017-03-31 21:19:37","CY_GID":null,"SS_Sort":10},
     * {"SS_Value":null,"GID":"1bfe6e82-0b29-4e76-8218-a37fc7a6a169","SS_Name":"换卡密码验证","SS_Code":"207","SS_State":0,"SS_Remark":null,"SS_Update":"18986810706",
     * "SS_UpdateTime":"2017-03-31 21:19:37","CY_GID":null,"SS_Sort":11}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<DataBean> data;

    public boolean isSuccess() { return success;}

    public void setSuccess(boolean success) { this.success = success;}

    public Object getCode() { return code;}

    public void setCode(Object code) { this.code = code;}

    public String getMsg() { return msg;}

    public void setMsg(String msg) { this.msg = msg;}

    public List<DataBean> getData() { return data;}

    public void setData(List<DataBean> data) { this.data = data;}

    public static class DataBean
    {
        /**
         * SS_Value : null
         * GID : 7bf3ec78-3749-459b-9146-29a26a6df651
         * SS_Name : 现金支付
         * SS_Code : 101
         * SS_State : 1
         * SS_Remark : null
         * SS_Update : 18986810706
         * SS_UpdateTime : 2017-03-31 21:19:37
         * CY_GID : null
         * SS_Sort : 1
         */

        private Object SS_Value;
        private String GID;
        private String SS_Name;
        private String SS_Code;
        private int SS_State;
        private Object SS_Remark;
        private String SS_Update;
        private String SS_UpdateTime;
        private Object CY_GID;
        private int SS_Sort;

        public Object getSS_Value() { return SS_Value;}

        public void setSS_Value(Object SS_Value) { this.SS_Value = SS_Value;}

        public String getGID() { return GID;}

        public void setGID(String GID) { this.GID = GID;}

        public String getSS_Name() { return SS_Name;}

        public void setSS_Name(String SS_Name) { this.SS_Name = SS_Name;}

        public String getSS_Code() { return SS_Code;}

        public void setSS_Code(String SS_Code) { this.SS_Code = SS_Code;}

        public int getSS_State() { return SS_State;}

        public void setSS_State(int SS_State) { this.SS_State = SS_State;}

        public Object getSS_Remark() { return SS_Remark;}

        public void setSS_Remark(Object SS_Remark) { this.SS_Remark = SS_Remark;}

        public String getSS_Update() { return SS_Update;}

        public void setSS_Update(String SS_Update) { this.SS_Update = SS_Update;}

        public String getSS_UpdateTime() { return SS_UpdateTime;}

        public void setSS_UpdateTime(String SS_UpdateTime) { this.SS_UpdateTime = SS_UpdateTime;}

        public Object getCY_GID() { return CY_GID;}

        public void setCY_GID(Object CY_GID) { this.CY_GID = CY_GID;}

        public int getSS_Sort() { return SS_Sort;}

        public void setSS_Sort(int SS_Sort) { this.SS_Sort = SS_Sort;}
    }
}
