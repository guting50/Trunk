package com.zhiluo.android.yunpu.mvp.model;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/10/18 09:46.
 * 邮箱：137615198@qq.com
 */

public class SwitchEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"SS_Value":null,"GID":"8a331ecb-9141-4263-ade8-7f5c2f6db503","SS_Name":"现金支付","SS_Code":"101","SS_State":1,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":1},{"SS_Value":null,"GID":"82b84c25-3c78-4d82-83ff-692d046f9660","SS_Name":"余额支付","SS_Code":"102","SS_State":1,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":2},{"SS_Value":null,"GID":"aedd8fa0-1432-4ef7-aa58-e7fb8630e2dd","SS_Name":"微信记账","SS_Code":"105","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":3},{"SS_Value":null,"GID":"d0851899-9225-4a9f-9b4e-61886d023424","SS_Name":"支付宝记账","SS_Code":"106","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":4},{"SS_Value":null,"GID":"1f2f0a78-7782-4c00-97b6-4bb6cdefdec3","SS_Name":"银联支付","SS_Code":"103","SS_State":1,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":5},{"SS_Value":"100","GID":"2a185594-fefe-4c20-9be6-fa1a74cdbc49","SS_Name":"积分支付","SS_Code":"107","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":6},{"SS_Value":"50","GID":"3906291b-4d27-40c8-989a-17e1b643dd7d","SS_Name":"积分支付限制","SS_Code":"109","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":7},{"SS_Value":"YEZF","GID":"1edd6a35-34b1-4d52-9ed8-15bd81f3291d","SS_Name":"默认支付","SS_Code":"108","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":8},{"SS_Value":null,"GID":"922e65d7-ed08-4c14-a333-1a4597e3bddb","SS_Name":"会员卡号同手机号","SS_Code":"201","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":9},{"SS_Value":null,"GID":"61ae2df1-2c88-44c1-b867-cc4bd2f9e7a0","SS_Name":"初始金额和初始积分","SS_Code":"203","SS_State":1,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":10},{"SS_Value":null,"GID":"3462c876-5528-4aba-bbfc-f6385d91e621","SS_Name":"会员卡卡面卡号","SS_Code":"208","SS_State":1,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":11},{"SS_Value":null,"GID":"c1a69049-d226-4375-bc9b-31dcb25f68dd","SS_Name":"会员查询弹窗","SS_Code":"209","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":12},{"SS_Value":null,"GID":"d96d5c91-ee1f-46ef-9f5f-3c0c27f9af4a","SS_Name":"员工提成","SS_Code":"301","SS_State":1,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":13},{"SS_Value":null,"GID":"3520c132-7bc5-49b6-b4bc-3aadc8d2bc0b","SS_Name":"折后金额修改","SS_Code":"601","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":14},{"SS_Value":"123456","GID":"4c7bf0ec-10b2-4677-b8a6-2d4d146cfea6","SS_Name":"开卡初始密码","SS_Code":"202","SS_State":1,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":15},{"SS_Value":null,"GID":"e5b99bae-f147-4088-b65f-c182d89a0a5c","SS_Name":"消费密码验证","SS_Code":"204","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":16},{"SS_Value":null,"GID":"116b20d6-cc25-4634-8095-f049996f89df","SS_Name":"转账密码验证","SS_Code":"205","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":17},{"SS_Value":null,"GID":"63db29b5-bf5d-4628-bcf9-d07c4d2ba998","SS_Name":"兑换密码验证","SS_Code":"206","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":18},{"SS_Value":null,"GID":"04e5db07-5e90-4867-bef2-c986c110be80","SS_Name":"换卡密码验证","SS_Code":"207","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":19},{"SS_Value":"0","GID":"d9a76818-8707-4a52-9cb1-3174a661a3e9","SS_Name":"会员推荐提成","SS_Code":"503","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":20},{"SS_Value":"100","GID":"021a33cd-094c-447c-8fce-0181f5c2315c","SS_Name":"会员消费提成","SS_Code":"501","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":21},{"SS_Value":"100","GID":"2ee5f410-2022-4f0a-99a3-fba20e075f4c","SS_Name":"会员获得积分提成","SS_Code":"502","SS_State":0,"SS_Remark":null,"SS_Update":"17358638575","SS_UpdateTime":"2017-12-13 17:41:18","CY_GID":null,"SS_Sort":22}]
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
         * SS_Value : null
         * GID : 8a331ecb-9141-4263-ade8-7f5c2f6db503
         * SS_Name : 现金支付
         * SS_Code : 101
         * SS_State : 1
         * SS_Remark : null
         * SS_Update : 17358638575
         * SS_UpdateTime : 2017-12-13 17:41:18
         * CY_GID : null
         * SS_Sort : 1
         */
        private static final long serialVersionUID = 1L;
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
