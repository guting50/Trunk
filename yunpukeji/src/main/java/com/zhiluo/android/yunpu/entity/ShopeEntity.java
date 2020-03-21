package com.zhiluo.android.yunpu.entity;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/11/25 17:18.
 * 邮箱：137615198@qq.com
 */

public class ShopeEntity {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"SM_DefaultCode":null,"GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","CY_GID":"01a5ab8f-46b1-41c4-b089-0a73230ff9da","SM_Name":"默认店铺","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"SM_Remark":null,"SM_State":0,"SM_UpdateTime":"2017-11-14 10:35:02","SM_Picture":null,"SM_Creator":"17358638575","SM_XLong":null,"SM_YLat":null,"SM_Industry":null,"SM_Range":null,"SM_Country":null,"SM_Province":null,"SM_Disctrict":null,"SM_DetailAddr":null,"SM_MapAddr":null,"SM_UpdateState":1,"SM_AcountNum":1,"SM_Type":0,"SM_EndTime":"2117-11-14 10:35:02","SM_CreateTime":"2017-11-14 10:35:02","SM_City":null,"VipNumber":0}]
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
         * SM_DefaultCode : null
         * GID : af4b45fc-bab8-4af4-a3b8-83cbc5896d3c
         * CY_GID : 01a5ab8f-46b1-41c4-b089-0a73230ff9da
         * SM_Name : 默认店铺
         * SM_Contacter : null
         * SM_Phone : null
         * SM_Addr : null
         * SM_Remark : null
         * SM_State : 0
         * SM_UpdateTime : 2017-11-14 10:35:02
         * SM_Picture : null
         * SM_Creator : 17358638575
         * SM_XLong : null
         * SM_YLat : null
         * SM_Industry : null
         * SM_Range : null
         * SM_Country : null
         * SM_Province : null
         * SM_Disctrict : null
         * SM_DetailAddr : null
         * SM_MapAddr : null
         * SM_UpdateState : 1
         * SM_AcountNum : 1
         * SM_Type : 0
         * SM_EndTime : 2117-11-14 10:35:02
         * SM_CreateTime : 2017-11-14 10:35:02
         * SM_City : null
         * VipNumber : 0
         */

        private Object SM_DefaultCode;
        private String GID;
        private String CY_GID;
        private String SM_Name;
        private Object SM_Contacter;
        private Object SM_Phone;
        private Object SM_Addr;
        private Object SM_Remark;
        private int SM_State;
        private String SM_UpdateTime;
        private Object SM_Picture;
        private String SM_Creator;
        private Object SM_XLong;
        private Object SM_YLat;
        private Object SM_Industry;
        private Object SM_Range;
        private Object SM_Country;
        private Object SM_Province;
        private Object SM_Disctrict;
        private Object SM_DetailAddr;
        private Object SM_MapAddr;
        private int SM_UpdateState;
        private int SM_AcountNum;
        private int SM_Type;
        private String SM_EndTime;
        private String SM_CreateTime;
        private Object SM_City;
        private int VipNumber;

        public Object getSM_DefaultCode() {
            return SM_DefaultCode;
        }

        public void setSM_DefaultCode(Object SM_DefaultCode) {
            this.SM_DefaultCode = SM_DefaultCode;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

        public Object getSM_Contacter() {
            return SM_Contacter;
        }

        public void setSM_Contacter(Object SM_Contacter) {
            this.SM_Contacter = SM_Contacter;
        }

        public Object getSM_Phone() {
            return SM_Phone;
        }

        public void setSM_Phone(Object SM_Phone) {
            this.SM_Phone = SM_Phone;
        }

        public Object getSM_Addr() {
            return SM_Addr;
        }

        public void setSM_Addr(Object SM_Addr) {
            this.SM_Addr = SM_Addr;
        }

        public Object getSM_Remark() {
            return SM_Remark;
        }

        public void setSM_Remark(Object SM_Remark) {
            this.SM_Remark = SM_Remark;
        }

        public int getSM_State() {
            return SM_State;
        }

        public void setSM_State(int SM_State) {
            this.SM_State = SM_State;
        }

        public String getSM_UpdateTime() {
            return SM_UpdateTime;
        }

        public void setSM_UpdateTime(String SM_UpdateTime) {
            this.SM_UpdateTime = SM_UpdateTime;
        }

        public Object getSM_Picture() {
            return SM_Picture;
        }

        public void setSM_Picture(Object SM_Picture) {
            this.SM_Picture = SM_Picture;
        }

        public String getSM_Creator() {
            return SM_Creator;
        }

        public void setSM_Creator(String SM_Creator) {
            this.SM_Creator = SM_Creator;
        }

        public Object getSM_XLong() {
            return SM_XLong;
        }

        public void setSM_XLong(Object SM_XLong) {
            this.SM_XLong = SM_XLong;
        }

        public Object getSM_YLat() {
            return SM_YLat;
        }

        public void setSM_YLat(Object SM_YLat) {
            this.SM_YLat = SM_YLat;
        }

        public Object getSM_Industry() {
            return SM_Industry;
        }

        public void setSM_Industry(Object SM_Industry) {
            this.SM_Industry = SM_Industry;
        }

        public Object getSM_Range() {
            return SM_Range;
        }

        public void setSM_Range(Object SM_Range) {
            this.SM_Range = SM_Range;
        }

        public Object getSM_Country() {
            return SM_Country;
        }

        public void setSM_Country(Object SM_Country) {
            this.SM_Country = SM_Country;
        }

        public Object getSM_Province() {
            return SM_Province;
        }

        public void setSM_Province(Object SM_Province) {
            this.SM_Province = SM_Province;
        }

        public Object getSM_Disctrict() {
            return SM_Disctrict;
        }

        public void setSM_Disctrict(Object SM_Disctrict) {
            this.SM_Disctrict = SM_Disctrict;
        }

        public Object getSM_DetailAddr() {
            return SM_DetailAddr;
        }

        public void setSM_DetailAddr(Object SM_DetailAddr) {
            this.SM_DetailAddr = SM_DetailAddr;
        }

        public Object getSM_MapAddr() {
            return SM_MapAddr;
        }

        public void setSM_MapAddr(Object SM_MapAddr) {
            this.SM_MapAddr = SM_MapAddr;
        }

        public int getSM_UpdateState() {
            return SM_UpdateState;
        }

        public void setSM_UpdateState(int SM_UpdateState) {
            this.SM_UpdateState = SM_UpdateState;
        }

        public int getSM_AcountNum() {
            return SM_AcountNum;
        }

        public void setSM_AcountNum(int SM_AcountNum) {
            this.SM_AcountNum = SM_AcountNum;
        }

        public int getSM_Type() {
            return SM_Type;
        }

        public void setSM_Type(int SM_Type) {
            this.SM_Type = SM_Type;
        }

        public String getSM_EndTime() {
            return SM_EndTime;
        }

        public void setSM_EndTime(String SM_EndTime) {
            this.SM_EndTime = SM_EndTime;
        }

        public String getSM_CreateTime() {
            return SM_CreateTime;
        }

        public void setSM_CreateTime(String SM_CreateTime) {
            this.SM_CreateTime = SM_CreateTime;
        }

        public Object getSM_City() {
            return SM_City;
        }

        public void setSM_City(Object SM_City) {
            this.SM_City = SM_City;
        }

        public int getVipNumber() {
            return VipNumber;
        }

        public void setVipNumber(int VipNumber) {
            this.VipNumber = VipNumber;
        }
    }
}
