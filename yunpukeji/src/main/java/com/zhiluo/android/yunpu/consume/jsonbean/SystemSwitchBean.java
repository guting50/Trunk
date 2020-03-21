package com.zhiluo.android.yunpu.consume.jsonbean;

import java.util.List;

/**
 * 系统开关JavaBean
 * 作者：罗咏哲 on 2017/7/3 17:38.
 * 邮箱：137615198@qq.com
 */

public class SystemSwitchBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"SS_Value":null,"GID":"c5b01ed2-69d4-4372-858f-35e365eb6cce","SS_Name":"现金支付","SS_Code":"101","SS_State":1,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":1},{"SS_Value":null,"GID":"0b4d3938-e88b-4966-9d44-74409d41b0c8","SS_Name":"余额支付","SS_Code":"102","SS_State":1,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":2},{"SS_Value":null,"GID":"cd4979cc-2532-4844-9822-ef94465d77b7","SS_Name":"银联支付","SS_Code":"103","SS_State":1,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":3},{"SS_Value":"500","GID":"30309930-3ac4-48e9-ad81-73fa31a31a36","SS_Name":"积分支付","SS_Code":"107","SS_State":1,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":4},{"SS_Value":"YEZF","GID":"35565110-0ace-42ae-aa05-9378f8ec1103","SS_Name":"默认支付","SS_Code":"108","SS_State":0,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":5},{"SS_Value":null,"GID":"1a28498e-8d1e-4f44-b0ea-f7524db04a90","SS_Name":"会员卡号同手机号","SS_Code":"201","SS_State":0,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":6},{"SS_Value":null,"GID":"ba7697ee-4be7-4a0c-ba6e-eba6da33a2e9","SS_Name":"卡片余额和剩余积分","SS_Code":"203","SS_State":0,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":7},{"SS_Value":null,"GID":"74f70d96-e842-4a24-80f9-faf75bf5dcc2","SS_Name":"员工提成","SS_Code":"301","SS_State":1,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":8},{"SS_Value":null,"GID":"5c5474db-cb33-4ab6-91c2-88ab9427d96f","SS_Name":"折后金额修改","SS_Code":"601","SS_State":0,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":9},{"SS_Value":"123456","GID":"682d5b5f-96e5-4174-bff1-2523ba270b81","SS_Name":"开卡初始密码","SS_Code":"202","SS_State":0,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":10},{"SS_Value":null,"GID":"c62fb6bf-18c1-4ae9-964e-d7c1cf2a1c5e","SS_Name":"消费密码验证","SS_Code":"204","SS_State":0,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":11},{"SS_Value":null,"GID":"2609d429-4ecd-4ac2-b809-8319f4bfb3a2","SS_Name":"转账密码验证","SS_Code":"205","SS_State":0,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":12},{"SS_Value":null,"GID":"00a00769-f481-44cb-8a27-3ae12ed77f1b","SS_Name":"兑换密码验证","SS_Code":"206","SS_State":0,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":13},{"SS_Value":null,"GID":"80fa480f-7840-4cdb-833f-951c8416ba0e","SS_Name":"换卡密码验证","SS_Code":"207","SS_State":0,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":14},{"SS_Value":"0","GID":"60f6ca26-9d1c-4426-845a-8610b38c07c4","SS_Name":"会员推荐提成","SS_Code":"503","SS_State":1,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":15},{"SS_Value":"100","GID":"65955621-dcaa-4954-8d11-c8f72533fad2","SS_Name":"会员消费提成","SS_Code":"501","SS_State":1,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":16},{"SS_Value":"100","GID":"799f0599-3b25-4ffa-ba5e-ff5922f74956","SS_Name":"会员获得积分提成","SS_Code":"502","SS_State":0,"SS_Remark":null,"SS_Update":"1540004275@qq.com","SS_UpdateTime":"2017-06-15 10:38:37","CY_GID":null,"SS_Sort":17}]
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
         * SS_Value : null
         * GID : c5b01ed2-69d4-4372-858f-35e365eb6cce
         * SS_Name : 现金支付
         * SS_Code : 101
         * SS_State : 1
         * SS_Remark : null
         * SS_Update : 1540004275@qq.com
         * SS_UpdateTime : 2017-06-15 10:38:37
         * CY_GID : null
         * SS_Sort : 1
         */

        private String SS_Value;
        private String GID;
        private String SS_Name;
        private String SS_Code;
        private int SS_State;
        private Object SS_Remark;
        private String SS_Update;
        private String SS_UpdateTime;
        private Object CY_GID;
        private int SS_Sort;

        public String getSS_Value() {
            return SS_Value;
        }

        public void setSS_Value(String SS_Value) {
            this.SS_Value = SS_Value;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getSS_Name() {
            return SS_Name;
        }

        public void setSS_Name(String SS_Name) {
            this.SS_Name = SS_Name;
        }

        public String getSS_Code() {
            return SS_Code;
        }

        public void setSS_Code(String SS_Code) {
            this.SS_Code = SS_Code;
        }

        public int getSS_State() {
            return SS_State;
        }

        public void setSS_State(int SS_State) {
            this.SS_State = SS_State;
        }

        public Object getSS_Remark() {
            return SS_Remark;
        }

        public void setSS_Remark(Object SS_Remark) {
            this.SS_Remark = SS_Remark;
        }

        public String getSS_Update() {
            return SS_Update;
        }

        public void setSS_Update(String SS_Update) {
            this.SS_Update = SS_Update;
        }

        public String getSS_UpdateTime() {
            return SS_UpdateTime;
        }

        public void setSS_UpdateTime(String SS_UpdateTime) {
            this.SS_UpdateTime = SS_UpdateTime;
        }

        public Object getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(Object CY_GID) {
            this.CY_GID = CY_GID;
        }

        public int getSS_Sort() {
            return SS_Sort;
        }

        public void setSS_Sort(int SS_Sort) {
            this.SS_Sort = SS_Sort;
        }
    }
}
