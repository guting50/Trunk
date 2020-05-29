package com.zhiluo.android.yunpu.member.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/6/30 22:02.
 * 邮箱：137615198@qq.com
 */

public class MemberInfoBean implements Serializable{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GID":"310a1936-5b8c-4f03-a9a8-6baab79ef3b1","VIP_RegSource":null,"EM_Name":null,"VIP_HeadImg":null,"VCH_Card":"999","VIP_Name":"罗","VIP_Sex":0,"VCH_CreateTime":"2017-07-03 12:01:25","VIP_Birthday":null,"VIP_CellPhone":"17358638575","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"#ff0000,重要客户","VG_GID":"186f1bd3-8fcc-45fd-b6da-f1e913d3cfcd","EM_ID":"","VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"feng","SM_Name":"总店","VCH_Fee":0,"VIP_OpenID":null,"VG_Name":"默认等级","VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":0,"DS_Value":1,"VS_Value":1,"RS_Value":0.2,"VGInfo":null,"CustomeFieldList":null,"MA_AvailableBalance":30,"MA_AggregateAmount":67,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"CouponsList":[{"GID":"bb8796fd-91e7-4f40-9030-25d2e9146433","VIP_GID":"310a1936-5b8c-4f03-a9a8-6baab79ef3b1","EC_GID":"964f63ea-624c-441c-a3e7-37bba38b0529","EC_ReddemCode":null,"VCR_IsForver":1,"VCR_StatrTime":null,"VCR_EndTime":null,"VCR_IsUse":0,"VCR_CreatorTime":"2018-01-04 14:05:16","SM_GID":"","CY_GID":"61ec52b1-6da8-415a-b66a-9cec063bda5d","EC_DiscountType":1,"EC_UseType":1,"EC_Discount":50,"EC_Denomination":100}]}
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
         * GID : 310a1936-5b8c-4f03-a9a8-6baab79ef3b1
         * VIP_RegSource : null
         * EM_Name : null
         * VIP_HeadImg : null
         * VCH_Card : 999
         * VIP_Name : 罗
         * VIP_Sex : 0
         * VCH_CreateTime : 2017-07-03 12:01:25
         * VIP_Birthday : null
         * VIP_CellPhone : 17358638575
         * VIP_ICCard :
         * VIP_Email :
         * VIP_Remark :
         * VIP_IsForver : 1
         * VIP_Overdue : null
         * VIP_State : 0
         * VIP_FaceNumber :
         * VIP_Label : #ff0000,重要客户
         * VG_GID : 186f1bd3-8fcc-45fd-b6da-f1e913d3cfcd
         * EM_ID :
         * VIP_Referee :
         * VIP_Addr :
         * VIP_FixedPhone :
         * VIP_Creator : feng
         * SM_Name : 总店
         * VCH_Fee : 0
         * VIP_OpenID : null
         * VG_Name : 默认等级
         * VG_IsAccount : 1
         * VG_IsIntegral : 1
         * VG_IsDiscount : 1
         * VG_IsCount : 1
         * VG_IsTime : 0
         * DS_Value : 1.0
         * VS_Value : 1.0
         * RS_Value : 0.2
         * VGInfo : null
         * CustomeFieldList : null
         * MA_AvailableBalance : 30.0
         * MA_AggregateAmount : 67.0
         * MA_AggregateStoredValue : 0.0
         * MA_AvailableIntegral : 0.0
         * MCA_HowMany : 0
         * MCA_TotalCharge : 0
         * CouponsList : [{"GID":"bb8796fd-91e7-4f40-9030-25d2e9146433","VIP_GID":"310a1936-5b8c-4f03-a9a8-6baab79ef3b1","EC_GID":"964f63ea-624c-441c-a3e7-37bba38b0529","EC_ReddemCode":null,"VCR_IsForver":1,"VCR_StatrTime":null,"VCR_EndTime":null,"VCR_IsUse":0,"VCR_CreatorTime":"2018-01-04 14:05:16","SM_GID":"","CY_GID":"61ec52b1-6da8-415a-b66a-9cec063bda5d","EC_DiscountType":1,"EC_UseType":1,"EC_Discount":50,"EC_Denomination":100}]
         */

        private String GID;
        private Object VIP_RegSource;
        private Object EM_Name;
        private String VIP_HeadImg;
        private String VCH_Card;
        private String VIP_Name;
        private int VIP_Sex;
        private String VCH_CreateTime;
        private String VIP_Birthday;
        private String VIP_CellPhone;
        private String VIP_ICCard;
        private String VIP_Email;
        private String VIP_Remark;
        private int VIP_IsForver;
        private String VIP_Overdue;
        private int VIP_State;
        private String VIP_FaceNumber;
        private String VIP_Label;
        private String VG_GID;
        private String EM_ID;
        private String VIP_Referee;
        private String VIP_Addr;
        private String VIP_FixedPhone;
        private String VIP_Creator;
        private String SM_Name;
        private int VCH_Fee;
        private String VIP_OpenID;
        private String VG_Name;
        private int VG_IsAccount;
        private int VG_IsIntegral;
        private int VG_IsDiscount;
        private int VG_IsCount;
        private int VG_IsTime;
        private double DS_Value;
        private double VS_Value;
        private double RS_Value;
        private List<VGInfo> VGInfo;
        private Object CustomeFieldList;
        private double MA_AvailableBalance;
        private double MA_AggregateAmount;
        private double MA_AggregateStoredValue;
        private double MA_AvailableIntegral;
        private int MIA_SurplusTimes;
        private String MIA_OverTime;
        private String MCA_HowManyDetail;
        private int MCA_HowMany;
        private int MCA_TotalCharge;
        private List<CouponsListBean> CouponsList;
        private double VG_OilIntegral;

        public double getVG_OilIntegral() {
            return VG_OilIntegral;
        }

        public void setVG_OilIntegral(double VG_OilIntegral) {
            this.VG_OilIntegral = VG_OilIntegral;
        }


        public int getMIA_SurplusTimes() {
            return MIA_SurplusTimes;
        }

        public void setMIA_SurplusTimes(int MIA_SurplusTimes) {
            this.MIA_SurplusTimes = MIA_SurplusTimes;
        }

        public String getMIA_OverTime() {
            return MIA_OverTime;
        }

        public void setMIA_OverTime(String MIA_OverTime) {
            this.MIA_OverTime = MIA_OverTime;
        }

        public String getMCA_HowManyDetail() {
            return MCA_HowManyDetail;
        }

        public void setMCA_HowManyDetail(String MCA_HowManyDetail) {
            this.MCA_HowManyDetail = MCA_HowManyDetail;
        }



        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public Object getVIP_RegSource() {
            return VIP_RegSource;
        }

        public void setVIP_RegSource(Object VIP_RegSource) {
            this.VIP_RegSource = VIP_RegSource;
        }

        public Object getEM_Name() {
            return EM_Name;
        }

        public void setEM_Name(Object EM_Name) {
            this.EM_Name = EM_Name;
        }

        public String getVIP_HeadImg() {
            return VIP_HeadImg;
        }

        public void setVIP_HeadImg(String VIP_HeadImg) {
            this.VIP_HeadImg = VIP_HeadImg;
        }

        public String getVCH_Card() {
            return VCH_Card;
        }

        public void setVCH_Card(String VCH_Card) {
            this.VCH_Card = VCH_Card;
        }

        public String getVIP_Name() {
            return VIP_Name;
        }

        public void setVIP_Name(String VIP_Name) {
            this.VIP_Name = VIP_Name;
        }

        public int getVIP_Sex() {
            return VIP_Sex;
        }

        public void setVIP_Sex(int VIP_Sex) {
            this.VIP_Sex = VIP_Sex;
        }

        public String getVCH_CreateTime() {
            return VCH_CreateTime;
        }

        public void setVCH_CreateTime(String VCH_CreateTime) {
            this.VCH_CreateTime = VCH_CreateTime;
        }

        public String getVIP_Birthday() {
            return VIP_Birthday;
        }

        public void setVIP_Birthday(String VIP_Birthday) {
            this.VIP_Birthday = VIP_Birthday;
        }

        public String getVIP_CellPhone() {
            return VIP_CellPhone;
        }

        public void setVIP_CellPhone(String VIP_CellPhone) {
            this.VIP_CellPhone = VIP_CellPhone;
        }

        public String getVIP_ICCard() {
            return VIP_ICCard;
        }

        public void setVIP_ICCard(String VIP_ICCard) {
            this.VIP_ICCard = VIP_ICCard;
        }

        public String getVIP_Email() {
            return VIP_Email;
        }

        public void setVIP_Email(String VIP_Email) {
            this.VIP_Email = VIP_Email;
        }

        public String getVIP_Remark() {
            return VIP_Remark;
        }

        public void setVIP_Remark(String VIP_Remark) {
            this.VIP_Remark = VIP_Remark;
        }

        public int getVIP_IsForver() {
            return VIP_IsForver;
        }

        public void setVIP_IsForver(int VIP_IsForver) {
            this.VIP_IsForver = VIP_IsForver;
        }

        public String getVIP_Overdue() {
            return VIP_Overdue;
        }

        public void setVIP_Overdue(String VIP_Overdue) {
            this.VIP_Overdue = VIP_Overdue;
        }

        public int getVIP_State() {
            return VIP_State;
        }

        public void setVIP_State(int VIP_State) {
            this.VIP_State = VIP_State;
        }

        public String getVIP_FaceNumber() {
            return VIP_FaceNumber;
        }

        public void setVIP_FaceNumber(String VIP_FaceNumber) {
            this.VIP_FaceNumber = VIP_FaceNumber;
        }

        public String getVIP_Label() {
            return VIP_Label;
        }

        public void setVIP_Label(String VIP_Label) {
            this.VIP_Label = VIP_Label;
        }

        public String getVG_GID() {
            return VG_GID;
        }

        public void setVG_GID(String VG_GID) {
            this.VG_GID = VG_GID;
        }

        public String getEM_ID() {
            return EM_ID;
        }

        public void setEM_ID(String EM_ID) {
            this.EM_ID = EM_ID;
        }

        public String getVIP_Referee() {
            return VIP_Referee;
        }

        public void setVIP_Referee(String VIP_Referee) {
            this.VIP_Referee = VIP_Referee;
        }

        public String getVIP_Addr() {
            return VIP_Addr;
        }

        public void setVIP_Addr(String VIP_Addr) {
            this.VIP_Addr = VIP_Addr;
        }

        public String getVIP_FixedPhone() {
            return VIP_FixedPhone;
        }

        public void setVIP_FixedPhone(String VIP_FixedPhone) {
            this.VIP_FixedPhone = VIP_FixedPhone;
        }

        public String getVIP_Creator() {
            return VIP_Creator;
        }

        public void setVIP_Creator(String VIP_Creator) {
            this.VIP_Creator = VIP_Creator;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

        public int getVCH_Fee() {
            return VCH_Fee;
        }

        public void setVCH_Fee(int VCH_Fee) {
            this.VCH_Fee = VCH_Fee;
        }

        public String getVIP_OpenID() {
            return VIP_OpenID;
        }

        public void setVIP_OpenID(String VIP_OpenID) {
            this.VIP_OpenID = VIP_OpenID;
        }

        public String getVG_Name() {
            return VG_Name;
        }

        public void setVG_Name(String VG_Name) {
            this.VG_Name = VG_Name;
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

        public List<VGInfo> getVGInfo() {
            return VGInfo;
        }

        public void setVGInfo(List<VGInfo> VGInfo) {
            this.VGInfo = VGInfo;
        }

        public Object getCustomeFieldList() {
            return CustomeFieldList;
        }

        public void setCustomeFieldList(Object CustomeFieldList) {
            this.CustomeFieldList = CustomeFieldList;
        }

        public double getMA_AvailableBalance() {
            return MA_AvailableBalance;
        }

        public void setMA_AvailableBalance(double MA_AvailableBalance) {
            this.MA_AvailableBalance = MA_AvailableBalance;
        }

        public double getMA_AggregateAmount() {
            return MA_AggregateAmount;
        }

        public void setMA_AggregateAmount(double MA_AggregateAmount) {
            this.MA_AggregateAmount = MA_AggregateAmount;
        }

        public double getMA_AggregateStoredValue() {
            return MA_AggregateStoredValue;
        }

        public void setMA_AggregateStoredValue(double MA_AggregateStoredValue) {
            this.MA_AggregateStoredValue = MA_AggregateStoredValue;
        }

        public double getMA_AvailableIntegral() {
            return MA_AvailableIntegral;
        }

        public void setMA_AvailableIntegral(double MA_AvailableIntegral) {
            this.MA_AvailableIntegral = MA_AvailableIntegral;
        }

        public int getMCA_HowMany() {
            return MCA_HowMany;
        }

        public void setMCA_HowMany(int MCA_HowMany) {
            this.MCA_HowMany = MCA_HowMany;
        }

        public int getMCA_TotalCharge() {
            return MCA_TotalCharge;
        }

        public void setMCA_TotalCharge(int MCA_TotalCharge) {
            this.MCA_TotalCharge = MCA_TotalCharge;
        }

        public List<CouponsListBean> getCouponsList() {
            return CouponsList;
        }

        public void setCouponsList(List<CouponsListBean> CouponsList) {
            this.CouponsList = CouponsList;
        }

        public static class CouponsListBean implements Serializable{
            /**
             * GID : bb8796fd-91e7-4f40-9030-25d2e9146433
             * VIP_GID : 310a1936-5b8c-4f03-a9a8-6baab79ef3b1
             * EC_GID : 964f63ea-624c-441c-a3e7-37bba38b0529
             * EC_ReddemCode : null
             * VCR_IsForver : 1
             * VCR_StatrTime : null
             * VCR_EndTime : null
             * VCR_IsUse : 0
             * VCR_CreatorTime : 2018-01-04 14:05:16
             * SM_GID :
             * CY_GID : 61ec52b1-6da8-415a-b66a-9cec063bda5d
             * EC_DiscountType : 1
             * EC_UseType : 1
             * EC_Discount : 50.0
             * EC_Denomination : 100.0
             */

            private String GID;
            private String VIP_GID;
            private String EC_GID;
            private Object EC_ReddemCode;
            private int VCR_IsForver;
            private Object VCR_StatrTime;
            private Object VCR_EndTime;
            private int VCR_IsUse;
            private String VCR_CreatorTime;
            private String SM_GID;
            private String CY_GID;
            private int EC_DiscountType;
            private int EC_UseType;
            private double EC_Discount;
            private double EC_Denomination;
            private int EC_IsOverlay;
            private Object SM_Name;
            private boolean isCheck;
            private double orderMoney;

            public double getOrderMoney() {
                return orderMoney;
            }

            public void setOrderMoney(double orderMoney) {
                this.orderMoney = orderMoney;
            }

            public String getEC_Name() {
                return EC_Name;
            }

            public void setEC_Name(String EC_Name) {
                this.EC_Name = EC_Name;
            }

            private String EC_Name;

            public int getEC_IsOverlay() {
                return EC_IsOverlay;
            }

            public void setEC_IsOverlay(int EC_IsOverlay) {
                this.EC_IsOverlay = EC_IsOverlay;
            }



            public Object getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(Object SM_Name) {
                this.SM_Name = SM_Name;
            }



            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }



            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getVIP_GID() {
                return VIP_GID;
            }

            public void setVIP_GID(String VIP_GID) {
                this.VIP_GID = VIP_GID;
            }

            public String getEC_GID() {
                return EC_GID;
            }

            public void setEC_GID(String EC_GID) {
                this.EC_GID = EC_GID;
            }

            public Object getEC_ReddemCode() {
                return EC_ReddemCode;
            }

            public void setEC_ReddemCode(Object EC_ReddemCode) {
                this.EC_ReddemCode = EC_ReddemCode;
            }

            public int getVCR_IsForver() {
                return VCR_IsForver;
            }

            public void setVCR_IsForver(int VCR_IsForver) {
                this.VCR_IsForver = VCR_IsForver;
            }

            public Object getVCR_StatrTime() {
                return VCR_StatrTime;
            }

            public void setVCR_StatrTime(Object VCR_StatrTime) {
                this.VCR_StatrTime = VCR_StatrTime;
            }

            public Object getVCR_EndTime() {
                return VCR_EndTime;
            }

            public void setVCR_EndTime(Object VCR_EndTime) {
                this.VCR_EndTime = VCR_EndTime;
            }

            public int getVCR_IsUse() {
                return VCR_IsUse;
            }

            public void setVCR_IsUse(int VCR_IsUse) {
                this.VCR_IsUse = VCR_IsUse;
            }

            public String getVCR_CreatorTime() {
                return VCR_CreatorTime;
            }

            public void setVCR_CreatorTime(String VCR_CreatorTime) {
                this.VCR_CreatorTime = VCR_CreatorTime;
            }

            public String getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(String SM_GID) {
                this.SM_GID = SM_GID;
            }

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
                this.CY_GID = CY_GID;
            }

            public int getEC_DiscountType() {
                return EC_DiscountType;
            }

            public void setEC_DiscountType(int EC_DiscountType) {
                this.EC_DiscountType = EC_DiscountType;
            }

            public int getEC_UseType() {
                return EC_UseType;
            }

            public void setEC_UseType(int EC_UseType) {
                this.EC_UseType = EC_UseType;
            }

            public double getEC_Discount() {
                return EC_Discount;
            }

            public void setEC_Discount(double EC_Discount) {
                this.EC_Discount = EC_Discount;
            }

            public double getEC_Denomination() {
                return EC_Denomination;
            }

            public void setEC_Denomination(double EC_Denomination) {
                this.EC_Denomination = EC_Denomination;
            }
        }

        public static class VGInfo implements Serializable{
            /*PD_Discount:0
            PT_GID:"8ddfdbb4-4280-4521-97e3-010b17af7a57"
            PT_Name:"95#汽油"
            PT_Parent:""
            PT_SMList:""
            PT_SynType:"0"
            PT_Type:"油品"
            SM_GID:"3ee72a59-8a90-4e4d-a084-3fcd0ccc4c0a"
            SM_Name:"默认店铺"
            VG_GID:"8288d18d-45b1-439a-a938-38ba36ea2fb4"
            VG_Name:"一等级"
            VS_CMoney:10
            VS_Number:0.1*/
            public int PD_Discount;
            public String PT_GID;
            public String PT_Name;
            public String PT_Parent;
            public String PT_SMList;
            public String PT_SynType;
            public String PT_Type;
            public String SM_GID;
            public String SM_Name;
            public String VG_GID;
            public String VG_Name;
            public int VS_CMoney;
            public double VS_Number;

            public int getPD_Discount() {
                return PD_Discount;
            }

            public void setPD_Discount(int PD_Discount) {
                this.PD_Discount = PD_Discount;
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

            public String getPT_Parent() {
                return PT_Parent;
            }

            public void setPT_Parent(String PT_Parent) {
                this.PT_Parent = PT_Parent;
            }

            public String getPT_SMList() {
                return PT_SMList;
            }

            public void setPT_SMList(String PT_SMList) {
                this.PT_SMList = PT_SMList;
            }

            public String getPT_SynType() {
                return PT_SynType;
            }

            public void setPT_SynType(String PT_SynType) {
                this.PT_SynType = PT_SynType;
            }

            public String getPT_Type() {
                return PT_Type;
            }

            public void setPT_Type(String PT_Type) {
                this.PT_Type = PT_Type;
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

            public int getVS_CMoney() {
                return VS_CMoney;
            }

            public void setVS_CMoney(int VS_CMoney) {
                this.VS_CMoney = VS_CMoney;
            }

            public double getVS_Number() {
                return VS_Number;
            }

            public void setVS_Number(double VS_Number) {
                this.VS_Number = VS_Number;
            }
        }
    }
}
