package com.zhiluo.android.yunpu.goods.consume.bean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/16 14:18.
 * 邮箱：137615198@qq.com
 */

public class VipIncludeGradeDisBean {
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

        private double DS_Value;
        private double VS_Value;
        private double RS_Value;
        private String GID;
        private String VG_GID;
        private Object VT_Code;
        private Object VIP_Name;
        private Object VIP_CellPhone;
        private Object VIP_Birthday;
        private Object VIP_Email;
        private Object EM_ID;
        private Object VIP_Overdue;
        private int VIP_IsForver;
        private int VIP_State;
        private int VIP_Sex;
        private Object VIP_FixedPhone;
        private String VIP_UpdateTime;
        private Object VIP_Referee;
        private Object VIP_FaceNumber;
        private String VIP_Remark;
        private Object CY_GID;
        private Object VIP_HeadPicture;
        private Object SM_ID;
        private int VIP_IsDeleted;
        private int VIP_Percent;
        private String VCH_Card;
        private int VCH_Fee;
        private String VG_Name;
        private Object VGC_Code;
        private Object US_Value;
        private Object VT_Name;
        private int VCH_Type;
        private String VCH_UpdateTime;
        private double MA_AccountBalance;
        private double MA_AggregateAmount;
        private double MA_AvailableBalance;
        private double MA_AvailableIntegral;
        private double MA_FreezeBalance;
        private Object VIP_Addr;
        private Object VIP_HeadImg;
        private String SM_Name;
        private String VCH_CreateTime;
        private String VIP_Creator;
        private Object VCH_Pwd;
        private Object VIP_ICCard;
        private Object US_Name;
        private Object VIP_Label;
        private int VG_IsAccount;
        private int VG_IsIntegral;
        private int VG_IsDiscount;
        private int VG_IsCount;
        private int VG_IsTime;
        private List<VGInfoBean> VGInfo;

        public double getDS_Value() {
            return DS_Value;
        }

        public void setDS_Value(double DS_Value) {
            this.DS_Value = DS_Value;
        }

        public double getVS_Value() {
            return VS_Value;
        }

        public void setVS_Value(double VS_Value) {
            this.VS_Value = VS_Value;
        }

        public double getRS_Value() {
            return RS_Value;
        }

        public void setRS_Value(double RS_Value) {
            this.RS_Value = RS_Value;
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

        public Object getVT_Code() {
            return VT_Code;
        }

        public void setVT_Code(Object VT_Code) {
            this.VT_Code = VT_Code;
        }

        public Object getVIP_Name() {
            return VIP_Name;
        }

        public void setVIP_Name(Object VIP_Name) {
            this.VIP_Name = VIP_Name;
        }

        public Object getVIP_CellPhone() {
            return VIP_CellPhone;
        }

        public void setVIP_CellPhone(Object VIP_CellPhone) {
            this.VIP_CellPhone = VIP_CellPhone;
        }

        public Object getVIP_Birthday() {
            return VIP_Birthday;
        }

        public void setVIP_Birthday(Object VIP_Birthday) {
            this.VIP_Birthday = VIP_Birthday;
        }

        public Object getVIP_Email() {
            return VIP_Email;
        }

        public void setVIP_Email(Object VIP_Email) {
            this.VIP_Email = VIP_Email;
        }

        public Object getEM_ID() {
            return EM_ID;
        }

        public void setEM_ID(Object EM_ID) {
            this.EM_ID = EM_ID;
        }

        public Object getVIP_Overdue() {
            return VIP_Overdue;
        }

        public void setVIP_Overdue(Object VIP_Overdue) {
            this.VIP_Overdue = VIP_Overdue;
        }

        public int getVIP_IsForver() {
            return VIP_IsForver;
        }

        public void setVIP_IsForver(int VIP_IsForver) {
            this.VIP_IsForver = VIP_IsForver;
        }

        public int getVIP_State() {
            return VIP_State;
        }

        public void setVIP_State(int VIP_State) {
            this.VIP_State = VIP_State;
        }

        public int getVIP_Sex() {
            return VIP_Sex;
        }

        public void setVIP_Sex(int VIP_Sex) {
            this.VIP_Sex = VIP_Sex;
        }

        public Object getVIP_FixedPhone() {
            return VIP_FixedPhone;
        }

        public void setVIP_FixedPhone(Object VIP_FixedPhone) {
            this.VIP_FixedPhone = VIP_FixedPhone;
        }

        public String getVIP_UpdateTime() {
            return VIP_UpdateTime;
        }

        public void setVIP_UpdateTime(String VIP_UpdateTime) {
            this.VIP_UpdateTime = VIP_UpdateTime;
        }

        public Object getVIP_Referee() {
            return VIP_Referee;
        }

        public void setVIP_Referee(Object VIP_Referee) {
            this.VIP_Referee = VIP_Referee;
        }

        public Object getVIP_FaceNumber() {
            return VIP_FaceNumber;
        }

        public void setVIP_FaceNumber(Object VIP_FaceNumber) {
            this.VIP_FaceNumber = VIP_FaceNumber;
        }

        public String getVIP_Remark() {
            return VIP_Remark;
        }

        public void setVIP_Remark(String VIP_Remark) {
            this.VIP_Remark = VIP_Remark;
        }

        public Object getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(Object CY_GID) {
            this.CY_GID = CY_GID;
        }

        public Object getVIP_HeadPicture() {
            return VIP_HeadPicture;
        }

        public void setVIP_HeadPicture(Object VIP_HeadPicture) {
            this.VIP_HeadPicture = VIP_HeadPicture;
        }

        public Object getSM_ID() {
            return SM_ID;
        }

        public void setSM_ID(Object SM_ID) {
            this.SM_ID = SM_ID;
        }

        public int getVIP_IsDeleted() {
            return VIP_IsDeleted;
        }

        public void setVIP_IsDeleted(int VIP_IsDeleted) {
            this.VIP_IsDeleted = VIP_IsDeleted;
        }

        public int getVIP_Percent() {
            return VIP_Percent;
        }

        public void setVIP_Percent(int VIP_Percent) {
            this.VIP_Percent = VIP_Percent;
        }

        public String getVCH_Card() {
            return VCH_Card;
        }

        public void setVCH_Card(String VCH_Card) {
            this.VCH_Card = VCH_Card;
        }

        public int getVCH_Fee() {
            return VCH_Fee;
        }

        public void setVCH_Fee(int VCH_Fee) {
            this.VCH_Fee = VCH_Fee;
        }

        public String getVG_Name() {
            return VG_Name;
        }

        public void setVG_Name(String VG_Name) {
            this.VG_Name = VG_Name;
        }

        public Object getVGC_Code() {
            return VGC_Code;
        }

        public void setVGC_Code(Object VGC_Code) {
            this.VGC_Code = VGC_Code;
        }

        public Object getUS_Value() {
            return US_Value;
        }

        public void setUS_Value(Object US_Value) {
            this.US_Value = US_Value;
        }

        public Object getVT_Name() {
            return VT_Name;
        }

        public void setVT_Name(Object VT_Name) {
            this.VT_Name = VT_Name;
        }

        public int getVCH_Type() {
            return VCH_Type;
        }

        public void setVCH_Type(int VCH_Type) {
            this.VCH_Type = VCH_Type;
        }

        public String getVCH_UpdateTime() {
            return VCH_UpdateTime;
        }

        public void setVCH_UpdateTime(String VCH_UpdateTime) {
            this.VCH_UpdateTime = VCH_UpdateTime;
        }

        public double getMA_AccountBalance() {
            return MA_AccountBalance;
        }

        public void setMA_AccountBalance(double MA_AccountBalance) {
            this.MA_AccountBalance = MA_AccountBalance;
        }

        public double getMA_AggregateAmount() {
            return MA_AggregateAmount;
        }

        public void setMA_AggregateAmount(double MA_AggregateAmount) {
            this.MA_AggregateAmount = MA_AggregateAmount;
        }

        public double getMA_AvailableBalance() {
            return MA_AvailableBalance;
        }

        public void setMA_AvailableBalance(double MA_AvailableBalance) {
            this.MA_AvailableBalance = MA_AvailableBalance;
        }

        public double getMA_AvailableIntegral() {
            return MA_AvailableIntegral;
        }

        public void setMA_AvailableIntegral(double MA_AvailableIntegral) {
            this.MA_AvailableIntegral = MA_AvailableIntegral;
        }

        public double getMA_FreezeBalance() {
            return MA_FreezeBalance;
        }

        public void setMA_FreezeBalance(double MA_FreezeBalance) {
            this.MA_FreezeBalance = MA_FreezeBalance;
        }

        public Object getVIP_Addr() {
            return VIP_Addr;
        }

        public void setVIP_Addr(Object VIP_Addr) {
            this.VIP_Addr = VIP_Addr;
        }

        public Object getVIP_HeadImg() {
            return VIP_HeadImg;
        }

        public void setVIP_HeadImg(Object VIP_HeadImg) {
            this.VIP_HeadImg = VIP_HeadImg;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

        public String getVCH_CreateTime() {
            return VCH_CreateTime;
        }

        public void setVCH_CreateTime(String VCH_CreateTime) {
            this.VCH_CreateTime = VCH_CreateTime;
        }

        public String getVIP_Creator() {
            return VIP_Creator;
        }

        public void setVIP_Creator(String VIP_Creator) {
            this.VIP_Creator = VIP_Creator;
        }

        public Object getVCH_Pwd() {
            return VCH_Pwd;
        }

        public void setVCH_Pwd(Object VCH_Pwd) {
            this.VCH_Pwd = VCH_Pwd;
        }

        public Object getVIP_ICCard() {
            return VIP_ICCard;
        }

        public void setVIP_ICCard(Object VIP_ICCard) {
            this.VIP_ICCard = VIP_ICCard;
        }

        public Object getUS_Name() {
            return US_Name;
        }

        public void setUS_Name(Object US_Name) {
            this.US_Name = US_Name;
        }

        public Object getVIP_Label() {
            return VIP_Label;
        }

        public void setVIP_Label(Object VIP_Label) {
            this.VIP_Label = VIP_Label;
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

        public List<VGInfoBean> getVGInfo() {
            return VGInfo;
        }

        public void setVGInfo(List<VGInfoBean> VGInfo) {
            this.VGInfo = VGInfo;
        }

        public static class VGInfoBean {
            /**
             * VG_GID : bd9a443b-1337-4f6b-bd5b-4ed5bc416b53
             * VG_Name : 白金会员
             * PT_GID : 08085e51-300d-4166-b53f-4b7e3d245456
             * PT_Name : 服装
             * PT_Type : 商品
             * PD_Discount : 80
             * VS_CMoney : 1.0
             * VS_Number : 3.0
             * SM_GID : d754599d-3004-4256-b462-2d841d6f962d
             * SM_Name : 云上铺
             * PT_Parent :
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
        }
    }
}
