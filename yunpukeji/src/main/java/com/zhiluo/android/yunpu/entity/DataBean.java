package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 会员等级
 * 作者：罗咏哲 on 2018/1/24 14:39.
 * 邮箱：137615198@qq.com
 */

public class DataBean implements Serializable {
    private static final long UID = 1L;
    /**
     * VG_IsDownLock : 0
     * VG_IsTimeTimes : 9
     * GID : cbc5852a-34d4-43e4-bd67-ab191d34625d
     * US_Code : IntegerFulfil
     * US_Value : 0
     * VG_Name : 默认等级
     * VG_Code : 默认等级
     * VG_CardAmount : 0.0
     * VG_InitialAmount : 0.0
     * VG_InitialIntegral : 55.0
     * VG_IsLock : 0
     * VG_IsAccount : 1
     * VG_IsIntegral : 1
     * VG_IsDiscount : 1
     * VG_IsCount : 1
     * VG_IsTime : 1
     * VG_IsTimeNum : null
     * VG_IsTimeUnit : null
     * VG_Remark :
     * DS_Value : 100.0
     * RS_CMoney : 5.0
     * RS_Value : 2.0
     * VS_CMoney : 10.0
     * VS_Value : 8.0
     * US_Name : 积分达到
     * Settings : [{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"0be06d90-11c6-46f0-bbf1-7c7ca20f46df","PT_Name":"洗剪吹服务","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"fad86152-3f15-42bc-a20d-e723bf95b371","PT_SynType":"0"},{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"14265702-ca99-4cf0-8537-821ce39c8996","PT_Name":"手机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"171c168e-e00b-4f2d-86d4-b1a9a5c5f0aa","PT_Name":"文学","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"26bbcaf4-952a-4eca-8078-c3cbdb0a6f20","PT_SynType":"0"},{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"95d7f9ce-1f98-43e3-9648-8c76943c8b5d","PT_Name":"洗澡","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"fad86152-3f15-42bc-a20d-e723bf95b371","PT_SynType":"0"},{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"86efb174-e863-4c52-9029-186de4b576bf","PT_Name":"抽纸","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"4657c0e0-b1dc-4850-bfb2-be23c2dfefb2","PT_SynType":"0"},{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"e8407dd0-f3b2-4c2e-8a38-67a93b9c36c3","PT_Name":"编程","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"26bbcaf4-952a-4eca-8078-c3cbdb0a6f20","PT_SynType":"0"}]
     * InitServiceList : [{"SR_Timer":30,"SR_TimeUnit":1,"GID":"68c5576e-0d57-460e-a67c-545693c45c1a","VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","SC_GD":"ea01ba75-0582-4128-a20a-ebe23dccaa18","SR_Number":12,"SR_UpdateTime":"2018-01-13 15:02:56","SG_Name":"洗澡"},{"SR_Timer":10,"SR_TimeUnit":2,"GID":"9aeda6e0-d775-452f-bd79-d46a801040ac","VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","SC_GD":"5c2f188e-8a2b-42af-8f98-881134914ab4","SR_Number":1,"SR_UpdateTime":"2018-01-13 15:02:56","SG_Name":"洗头"}]
     */

    private int VG_IsDownLock;
    private String VG_IsTimeTimes;
    private Integer VG_UpDownType;
    private String VG_NextGradeName;
    private String VG_NextGradeGID;
    private String VG_LastGradeName;
    private String VG_LastGradeGID;
    private String GID;
    private String US_Code;
    private double US_Value;
    private String US_ValueMax;
    private String VG_Name;
    private String VG_Code;
    private double VG_CardAmount;
    private double VG_InitialAmount;
    private double VG_InitialIntegral;
    private int VG_IsLock;
    private int VG_IsAccount;
    private int VG_IsIntegral;
    private int VG_IsDiscount;
    private int VG_IsCount;
    private int VG_IsTime;
    private String VG_IsTimeNum;
    private Object VG_IsTimeUnit;
    private String VG_Remark;
    private double DS_Value;
    private double RS_CMoney;
    private double RS_Value;
    private double VS_CMoney;
    private double VS_Value;
    private String US_Name;
    private int VG_DiscountRuleType;
    private int VG_DiscountUniformRuleValue;
    private int VG_IntegralRuleType;
    private double VG_IntegralUniformRuleValue;
    private double VG_OilIntegral;
    private List<SettingsBean> Settings;
    private List<InitServiceListBean> InitServiceList;

    public double getVG_OilIntegral() {
        return VG_OilIntegral;
    }

    public void setVG_OilIntegral(double VG_OilIntegral) {
        this.VG_OilIntegral = VG_OilIntegral;
    }

    public Integer getVG_UpDownType() {
        return VG_UpDownType;
    }

    public void setVG_UpDownType(Integer VG_UpDownType) {
        this.VG_UpDownType = VG_UpDownType;
    }

    public String getVG_NextGradeName() {
        return VG_NextGradeName;
    }

    public void setVG_NextGradeName(String VG_NextGradeName) {
        this.VG_NextGradeName = VG_NextGradeName;
    }

    public String getVG_NextGradeGID() {
        return VG_NextGradeGID;
    }

    public void setVG_NextGradeGID(String VG_NextGradeGID) {
        this.VG_NextGradeGID = VG_NextGradeGID;
    }

    public String getVG_LastGradeName() {
        return VG_LastGradeName;
    }

    public void setVG_LastGradeName(String VG_LastGradeName) {
        this.VG_LastGradeName = VG_LastGradeName;
    }

    public String getVG_LastGradeGID() {
        return VG_LastGradeGID;
    }

    public void setVG_LastGradeGID(String VG_LastGradeGID) {
        this.VG_LastGradeGID = VG_LastGradeGID;
    }

    public String getUS_ValueMax() {
        return US_ValueMax;
    }

    public void setUS_ValueMax(String US_ValueMax) {
        this.US_ValueMax = US_ValueMax;
    }

    public int getVG_DiscountRuleType() {
        return VG_DiscountRuleType;
    }

    public void setVG_DiscountRuleType(int VG_DiscountRuleType) {
        this.VG_DiscountRuleType = VG_DiscountRuleType;
    }

    public int getVG_DiscountUniformRuleValue() {
        return VG_DiscountUniformRuleValue;
    }

    public void setVG_DiscountUniformRuleValue(int VG_DiscountUniformRuleValue) {
        this.VG_DiscountUniformRuleValue = VG_DiscountUniformRuleValue;
    }

    public int getVG_IntegralRuleType() {
        return VG_IntegralRuleType;
    }

    public void setVG_IntegralRuleType(int VG_IntegralRuleType) {
        this.VG_IntegralRuleType = VG_IntegralRuleType;
    }

    public double getVG_IntegralUniformRuleValue() {
        return VG_IntegralUniformRuleValue;
    }

    public void setVG_IntegralUniformRuleValue(double VG_IntegralUniformRuleValue) {
        this.VG_IntegralUniformRuleValue = VG_IntegralUniformRuleValue;
    }

    public int getVG_IsDownLock() {
        return VG_IsDownLock;
    }

    public void setVG_IsDownLock(int VG_IsDownLock) {
        this.VG_IsDownLock = VG_IsDownLock;
    }

    public String getVG_IsTimeTimes() {
        return VG_IsTimeTimes;
    }

    public void setVG_IsTimeTimes(String VG_IsTimeTimes) {
        this.VG_IsTimeTimes = VG_IsTimeTimes;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getUS_Code() {
        return US_Code;
    }

    public void setUS_Code(String US_Code) {
        this.US_Code = US_Code;
    }

    public double getUS_Value() {
        return US_Value;
    }

    public void setUS_Value(double US_Value) {
        this.US_Value = US_Value;
    }

    public String getVG_Name() {
        return VG_Name;
    }

    public void setVG_Name(String VG_Name) {
        this.VG_Name = VG_Name;
    }

    public String getVG_Code() {
        return VG_Code;
    }

    public void setVG_Code(String VG_Code) {
        this.VG_Code = VG_Code;
    }

    public double getVG_CardAmount() {
        return VG_CardAmount;
    }

    public void setVG_CardAmount(double VG_CardAmount) {
        this.VG_CardAmount = VG_CardAmount;
    }

    public double getVG_InitialAmount() {
        return VG_InitialAmount;
    }

    public void setVG_InitialAmount(double VG_InitialAmount) {
        this.VG_InitialAmount = VG_InitialAmount;
    }

    public double getVG_InitialIntegral() {
        return VG_InitialIntegral;
    }

    public void setVG_InitialIntegral(double VG_InitialIntegral) {
        this.VG_InitialIntegral = VG_InitialIntegral;
    }

    public int getVG_IsLock() {
        return VG_IsLock;
    }

    public void setVG_IsLock(int VG_IsLock) {
        this.VG_IsLock = VG_IsLock;
    }

    public int getVG_IsAccount() {
        return VG_IsAccount;
    }

    public void setVG_IsAccount(int VG_IsAccount) {
        this.VG_IsAccount = VG_IsAccount;
    }

    public int getVG_IsIntegral() {
        return VG_IsIntegral;
    }

    public void setVG_IsIntegral(int VG_IsIntegral) {
        this.VG_IsIntegral = VG_IsIntegral;
    }

    public int getVG_IsDiscount() {
        return VG_IsDiscount;
    }

    public void setVG_IsDiscount(int VG_IsDiscount) {
        this.VG_IsDiscount = VG_IsDiscount;
    }

    public int getVG_IsCount() {
        return VG_IsCount;
    }

    public void setVG_IsCount(int VG_IsCount) {
        this.VG_IsCount = VG_IsCount;
    }

    public int getVG_IsTime() {
        return VG_IsTime;
    }

    public void setVG_IsTime(int VG_IsTime) {
        this.VG_IsTime = VG_IsTime;
    }

    public String getVG_IsTimeNum() {
        return VG_IsTimeNum;
    }

    public void setVG_IsTimeNum(String VG_IsTimeNum) {
        this.VG_IsTimeNum = VG_IsTimeNum;
    }

    public Object getVG_IsTimeUnit() {
        return VG_IsTimeUnit;
    }

    public void setVG_IsTimeUnit(Object VG_IsTimeUnit) {
        this.VG_IsTimeUnit = VG_IsTimeUnit;
    }

    public String getVG_Remark() {
        return VG_Remark;
    }

    public void setVG_Remark(String VG_Remark) {
        this.VG_Remark = VG_Remark;
    }

    public double getDS_Value() {
        return DS_Value;
    }

    public void setDS_Value(double DS_Value) {
        this.DS_Value = DS_Value;
    }

    public double getRS_CMoney() {
        return RS_CMoney;
    }

    public void setRS_CMoney(double RS_CMoney) {
        this.RS_CMoney = RS_CMoney;
    }

    public double getRS_Value() {
        return RS_Value;
    }

    public void setRS_Value(double RS_Value) {
        this.RS_Value = RS_Value;
    }

    public double getVS_CMoney() {
        return VS_CMoney;
    }

    public void setVS_CMoney(double VS_CMoney) {
        this.VS_CMoney = VS_CMoney;
    }

    public double getVS_Value() {
        return VS_Value;
    }

    public void setVS_Value(double VS_Value) {
        this.VS_Value = VS_Value;
    }

    public String getUS_Name() {
        return US_Name;
    }

    public void setUS_Name(String US_Name) {
        this.US_Name = US_Name;
    }

    public List<SettingsBean> getSettings() {
        return Settings;
    }

    public void setSettings(List<SettingsBean> Settings) {
        this.Settings = Settings;
    }

    public List<InitServiceListBean> getInitServiceList() {
        return InitServiceList;
    }

    public void setInitServiceList(List<InitServiceListBean> InitServiceList) {
        this.InitServiceList = InitServiceList;
    }

    public static class SettingsBean implements Serializable {
        private static final long UID = 1L;
        /**
         * VG_GID : cbc5852a-34d4-43e4-bd67-ab191d34625d
         * VG_Name : 默认等级
         * PT_GID : 0be06d90-11c6-46f0-bbf1-7c7ca20f46df
         * PT_Name : 洗剪吹服务
         * PT_Type : 商品
         * PD_Discount : 100
         * VS_CMoney : 1.0
         * VS_Number : 0.0
         * SM_GID : af4b45fc-bab8-4af4-a3b8-83cbc5896d3c
         * SM_Name : 默认店铺
         * PT_Parent : fad86152-3f15-42bc-a20d-e723bf95b371
         * PT_SynType : 0
         */

        private String VG_GID;
        private String VG_Name;
        private String PT_GID;
        private String PT_Name;
        private String PT_Type;
        private int PD_Discount;
        private double VS_CMoney;
        private double VS_Number;
        private String SM_GID;
        private String SM_Name;
        private String PT_Parent;
        private String PT_SynType;

        public String getVG_GID() {
            return VG_GID;
        }

        public void setVG_GID(String VG_GID) {
            this.VG_GID = VG_GID;
        }

        public String getVG_Name() {
            return VG_Name;
        }

        public void setVG_Name(String VG_Name) {
            this.VG_Name = VG_Name;
        }

        public String getPT_GID() {
            return PT_GID;
        }

        public void setPT_GID(String PT_GID) {
            this.PT_GID = PT_GID;
        }

        public String getPT_Name() {
            return PT_Name;
        }

        public void setPT_Name(String PT_Name) {
            this.PT_Name = PT_Name;
        }

        public String getPT_Type() {
            return PT_Type;
        }

        public void setPT_Type(String PT_Type) {
            this.PT_Type = PT_Type;
        }

        public int getPD_Discount() {
            return PD_Discount;
        }

        public void setPD_Discount(int PD_Discount) {
            this.PD_Discount = PD_Discount;
        }

        public double getVS_CMoney() {
            return VS_CMoney;
        }

        public void setVS_CMoney(double VS_CMoney) {
            this.VS_CMoney = VS_CMoney;
        }

        public double getVS_Number() {
            return VS_Number;
        }

        public void setVS_Number(double VS_Number) {
            this.VS_Number = VS_Number;
        }

        public String getSM_GID() {
            return SM_GID;
        }

        public void setSM_GID(String SM_GID) {
            this.SM_GID = SM_GID;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

        public String getPT_Parent() {
            return PT_Parent;
        }

        public void setPT_Parent(String PT_Parent) {
            this.PT_Parent = PT_Parent;
        }

        public String getPT_SynType() {
            return PT_SynType;
        }

        public void setPT_SynType(String PT_SynType) {
            this.PT_SynType = PT_SynType;
        }
    }

    public static class InitServiceListBean implements Serializable {
        private static final long UID = 1L;
        /**
         * SR_Timer : 30
         * SR_TimeUnit : 1
         * GID : 68c5576e-0d57-460e-a67c-545693c45c1a
         * VG_GID : cbc5852a-34d4-43e4-bd67-ab191d34625d
         * SC_GD : ea01ba75-0582-4128-a20a-ebe23dccaa18
         * SR_Number : 12
         * SR_UpdateTime : 2018-01-13 15:02:56
         * SG_Name : 洗澡
         */

        private int SR_Timer;
        private int SR_TimeUnit;
        private String GID;
        private String VG_GID;
        private String SC_GD;
        private int SR_Number;
        private String SR_UpdateTime;
        private String SG_Name;
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public int getSR_Timer() {
            return SR_Timer;
        }

        public void setSR_Timer(int SR_Timer) {
            this.SR_Timer = SR_Timer;
        }

        public int getSR_TimeUnit() {
            return SR_TimeUnit;
        }

        public void setSR_TimeUnit(int SR_TimeUnit) {
            this.SR_TimeUnit = SR_TimeUnit;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getVG_GID() {
            return VG_GID;
        }

        public void setVG_GID(String VG_GID) {
            this.VG_GID = VG_GID;
        }

        public String getSC_GD() {
            return SC_GD;
        }

        public void setSC_GD(String SC_GD) {
            this.SC_GD = SC_GD;
        }

        public int getSR_Number() {
            return SR_Number;
        }

        public void setSR_Number(int SR_Number) {
            this.SR_Number = SR_Number;
        }

        public String getSR_UpdateTime() {
            return SR_UpdateTime;
        }

        public void setSR_UpdateTime(String SR_UpdateTime) {
            this.SR_UpdateTime = SR_UpdateTime;
        }

        public String getSG_Name() {
            return SG_Name;
        }

        public void setSG_Name(String SG_Name) {
            this.SG_Name = SG_Name;
        }
    }
}
