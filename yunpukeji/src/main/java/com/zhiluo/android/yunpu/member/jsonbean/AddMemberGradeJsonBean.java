package com.zhiluo.android.yunpu.member.jsonbean;

import java.util.List;

/**
 * Created by Cheng on 2017/4/1.
 */

public class AddMemberGradeJsonBean
{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GID":"7e15bd9b-d83a-497d-8833-08b9bb0e0a8b","US_Code":null,"US_Value":5,"VG_Name":"33","VG_Code":null,"VG_CardAmount":0,"VG_InitialAmount":0,"VG_IsLock":0,
     * "VG_IsAccount":0,"VG_IsIntegral":0,"VG_IsDiscount":0,"VG_IsCount":0,"VG_IsTime":0,"VG_IsTimeNum":0,"VG_IsTimeUnit":"0","VG_Remark":null,"DS_Value":100,"RS_CMoney":0,
     * "RS_Value":1,"VS_CMoney":0,"VS_Value":0,"US_Name":null,"Settings":[],"InitServiceList":null}
     */

    private boolean success;
    private Object code;
    private String msg;
    private DataBean data;

    public boolean isSuccess() { return success;}

    public void setSuccess(boolean success) { this.success = success;}

    public Object getCode() { return code;}

    public void setCode(Object code) { this.code = code;}

    public String getMsg() { return msg;}

    public void setMsg(String msg) { this.msg = msg;}

    public DataBean getData() { return data;}

    public void setData(DataBean data) { this.data = data;}

    public static class DataBean
    {
        /**
         * GID : 7e15bd9b-d83a-497d-8833-08b9bb0e0a8b
         * US_Code : null
         * US_Value : 5
         * VG_Name : 33
         * VG_Code : null
         * VG_CardAmount : 0
         * VG_InitialAmount : 0
         * VG_IsLock : 0
         * VG_IsAccount : 0
         * VG_IsIntegral : 0
         * VG_IsDiscount : 0
         * VG_IsCount : 0
         * VG_IsTime : 0
         * VG_IsTimeNum : 0
         * VG_IsTimeUnit : 0
         * VG_Remark : null
         * DS_Value : 100
         * RS_CMoney : 0
         * RS_Value : 1
         * VS_CMoney : 0
         * VS_Value : 0
         * US_Name : null
         * Settings : []
         * InitServiceList : null
         */

        private String GID;
        private Object US_Code;
        private int US_Value;
        private String VG_Name;
        private Object VG_Code;
        private int VG_CardAmount;
        private int VG_InitialAmount;
        private int VG_IsLock;
        private int VG_IsAccount;
        private int VG_IsIntegral;
        private int VG_IsDiscount;
        private int VG_IsCount;
        private int VG_IsTime;
        private int VG_IsTimeNum;
        private String VG_IsTimeUnit;
        private Object VG_Remark;
        private int DS_Value;
        private int RS_CMoney;
        private int RS_Value;
        private int VS_CMoney;
        private int VS_Value;
        private Object US_Name;
        private Object InitServiceList;
        private List<?> Settings;

        public String getGID() { return GID;}

        public void setGID(String GID) { this.GID = GID;}

        public Object getUS_Code() { return US_Code;}

        public void setUS_Code(Object US_Code) { this.US_Code = US_Code;}

        public int getUS_Value() { return US_Value;}

        public void setUS_Value(int US_Value) { this.US_Value = US_Value;}

        public String getVG_Name() { return VG_Name;}

        public void setVG_Name(String VG_Name) { this.VG_Name = VG_Name;}

        public Object getVG_Code() { return VG_Code;}

        public void setVG_Code(Object VG_Code) { this.VG_Code = VG_Code;}

        public int getVG_CardAmount() { return VG_CardAmount;}

        public void setVG_CardAmount(int VG_CardAmount) { this.VG_CardAmount = VG_CardAmount;}

        public int getVG_InitialAmount() { return VG_InitialAmount;}

        public void setVG_InitialAmount(int VG_InitialAmount) { this.VG_InitialAmount = VG_InitialAmount;}

        public int getVG_IsLock() { return VG_IsLock;}

        public void setVG_IsLock(int VG_IsLock) { this.VG_IsLock = VG_IsLock;}

        public int getVG_IsAccount() { return VG_IsAccount;}

        public void setVG_IsAccount(int VG_IsAccount) { this.VG_IsAccount = VG_IsAccount;}

        public int getVG_IsIntegral() { return VG_IsIntegral;}

        public void setVG_IsIntegral(int VG_IsIntegral) { this.VG_IsIntegral = VG_IsIntegral;}

        public int getVG_IsDiscount() { return VG_IsDiscount;}

        public void setVG_IsDiscount(int VG_IsDiscount) { this.VG_IsDiscount = VG_IsDiscount;}

        public int getVG_IsCount() { return VG_IsCount;}

        public void setVG_IsCount(int VG_IsCount) { this.VG_IsCount = VG_IsCount;}

        public int getVG_IsTime() { return VG_IsTime;}

        public void setVG_IsTime(int VG_IsTime) { this.VG_IsTime = VG_IsTime;}

        public int getVG_IsTimeNum() { return VG_IsTimeNum;}

        public void setVG_IsTimeNum(int VG_IsTimeNum) { this.VG_IsTimeNum = VG_IsTimeNum;}

        public String getVG_IsTimeUnit() { return VG_IsTimeUnit;}

        public void setVG_IsTimeUnit(String VG_IsTimeUnit) { this.VG_IsTimeUnit = VG_IsTimeUnit;}

        public Object getVG_Remark() { return VG_Remark;}

        public void setVG_Remark(Object VG_Remark) { this.VG_Remark = VG_Remark;}

        public int getDS_Value() { return DS_Value;}

        public void setDS_Value(int DS_Value) { this.DS_Value = DS_Value;}

        public int getRS_CMoney() { return RS_CMoney;}

        public void setRS_CMoney(int RS_CMoney) { this.RS_CMoney = RS_CMoney;}

        public int getRS_Value() { return RS_Value;}

        public void setRS_Value(int RS_Value) { this.RS_Value = RS_Value;}

        public int getVS_CMoney() { return VS_CMoney;}

        public void setVS_CMoney(int VS_CMoney) { this.VS_CMoney = VS_CMoney;}

        public int getVS_Value() { return VS_Value;}

        public void setVS_Value(int VS_Value) { this.VS_Value = VS_Value;}

        public Object getUS_Name() { return US_Name;}

        public void setUS_Name(Object US_Name) { this.US_Name = US_Name;}

        public Object getInitServiceList() { return InitServiceList;}

        public void setInitServiceList(Object InitServiceList) { this.InitServiceList = InitServiceList;}

        public List<?> getSettings() { return Settings;}

        public void setSettings(List<?> Settings) { this.Settings = Settings;}
    }
}
