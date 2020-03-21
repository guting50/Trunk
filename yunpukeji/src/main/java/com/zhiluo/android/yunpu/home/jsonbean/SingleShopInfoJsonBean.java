package com.zhiluo.android.yunpu.home.jsonbean;

import java.io.Serializable;

/**
 * iauthor：Cheng
 * date: 2017/4/21 10:42
 * email：jasoncheng9111@gmail.com
 */
public class SingleShopInfoJsonBean implements Serializable{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"SM_DefaultCode":"1234","GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","CY_GID":null,"SM_Name":"Tokyo ot","SM_Contacter":"h24","SM_Phone":"18866866668",
     * "SM_Addr":"","SM_Remark":"","SM_State":0,"SM_UpdateTime":"2017-04-17 11:16:37","SM_Picture":"","SM_Creator":"18986810706","SM_XLong":null,"SM_YLat":null,"SM_Industry":"",
     * "SM_Range":"","SM_Country":null,"SM_Province":"内蒙古","SM_Disctrict":"白云矿区","SM_DetailAddr":"","SM_MapAddr":null,"SM_UpdateState":1,"SM_AcountNum":1,"SM_Type":0,
     * "SM_EndTime":"2117-01-17 11:02:54","SM_CreateTime":"2017-04-17 11:16:37","SM_City":"中国"}
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

    public static class DataBean implements Serializable{
        /**
         * SM_DefaultCode : 1234
         * GID : bfb6cf66-cf97-4239-bb1e-bece3a86d09f
         * CY_GID : null
         * SM_Name : Tokyo ot
         * SM_Contacter : h24
         * SM_Phone : 18866866668
         * SM_Addr :
         * SM_Remark :
         * SM_State : 0
         * SM_UpdateTime : 2017-04-17 11:16:37
         * SM_Picture :
         * SM_Creator : 18986810706
         * SM_XLong : null
         * SM_YLat : null
         * SM_Industry :
         * SM_Range :
         * SM_Country : null
         * SM_Province : 内蒙古
         * SM_Disctrict : 白云矿区
         * SM_DetailAddr :
         * SM_MapAddr : null
         * SM_UpdateState : 1
         * SM_AcountNum : 1
         * SM_Type : 0
         * SM_EndTime : 2117-01-17 11:02:54
         * SM_CreateTime : 2017-04-17 11:16:37
         * SM_City : 中国
         */

        private String SM_DefaultCode;
        private String GID;
        private String CY_GID;
        private String SM_Name;
        private String SM_Contacter;
        private String SM_Phone;
        private String SM_Addr;
        private String SM_Remark;
        private int SM_State;
        private String SM_UpdateTime;
        private String SM_Picture;
        private String SM_Creator;
        private double SM_XLong;
        private double SM_YLat;
        private String SM_Industry;
        private String SM_Range;
        private String SM_Country;
        private String SM_Province;
        private String SM_Disctrict;
        private String SM_DetailAddr;
        private String SM_MapAddr;
        private int SM_UpdateState;
        private int SM_AcountNum;
        private int SM_Type;
        private String SM_EndTime;
        private String SM_CreateTime;
        private String SM_City;

        public String getSM_DefaultCode() {
            return SM_DefaultCode;
        }

        public void setSM_DefaultCode(String SM_DefaultCode) {
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

        public String getSM_Contacter() {
            return SM_Contacter;
        }

        public void setSM_Contacter(String SM_Contacter) {
            this.SM_Contacter = SM_Contacter;
        }

        public String getSM_Phone() {
            return SM_Phone;
        }

        public void setSM_Phone(String SM_Phone) {
            this.SM_Phone = SM_Phone;
        }

        public String getSM_Addr() {
            return SM_Addr;
        }

        public void setSM_Addr(String SM_Addr) {
            this.SM_Addr = SM_Addr;
        }

        public String getSM_Remark() {
            return SM_Remark;
        }

        public void setSM_Remark(String SM_Remark) {
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

        public String getSM_Picture() {
            return SM_Picture;
        }

        public void setSM_Picture(String SM_Picture) {
            this.SM_Picture = SM_Picture;
        }

        public String getSM_Creator() {
            return SM_Creator;
        }

        public void setSM_Creator(String SM_Creator) {
            this.SM_Creator = SM_Creator;
        }

        public double getSM_XLong() {
            return SM_XLong;
        }

        public void setSM_XLong(double SM_XLong) {
            this.SM_XLong = SM_XLong;
        }

        public double getSM_YLat() {
            return SM_YLat;
        }

        public void setSM_YLat(double SM_YLat) {
            this.SM_YLat = SM_YLat;
        }

        public String getSM_Industry() {
            return SM_Industry;
        }

        public void setSM_Industry(String SM_Industry) {
            this.SM_Industry = SM_Industry;
        }

        public String getSM_Range() {
            return SM_Range;
        }

        public void setSM_Range(String SM_Range) {
            this.SM_Range = SM_Range;
        }

        public String getSM_Country() {
            return SM_Country;
        }

        public void setSM_Country(String SM_Country) {
            this.SM_Country = SM_Country;
        }

        public String getSM_Province() {
            return SM_Province;
        }

        public void setSM_Province(String SM_Province) {
            this.SM_Province = SM_Province;
        }

        public String getSM_Disctrict() {
            return SM_Disctrict;
        }

        public void setSM_Disctrict(String SM_Disctrict) {
            this.SM_Disctrict = SM_Disctrict;
        }

        public String getSM_DetailAddr() {
            return SM_DetailAddr;
        }

        public void setSM_DetailAddr(String SM_DetailAddr) {
            this.SM_DetailAddr = SM_DetailAddr;
        }

        public String getSM_MapAddr() {
            return SM_MapAddr;
        }

        public void setSM_MapAddr(String SM_MapAddr) {
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

        public String getSM_City() {
            return SM_City;
        }

        public void setSM_City(String SM_City) {
            this.SM_City = SM_City;
        }
    }
}
