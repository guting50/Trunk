package com.zhiluo.android.yunpu.member.manager.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 会员信息列表bean
 * 作者：罗咏哲 on 2017/7/18 14:30.
 * 邮箱：137615198@qq.com
 */

public class AllMemberListBean implements Serializable {
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

    public static class DataBean implements Serializable {
        /**
         * PageTotal : 1
         * PageSize : 20
         * DataCount : 18
         * PageIndex : 1
         * StatisticsInfo : null
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private Object StatisticsInfo;
        private List<DataListBean> DataList;

        public int getPageTotal() {
            return PageTotal;
        }

        public void setPageTotal(int PageTotal) {
            this.PageTotal = PageTotal;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public int getDataCount() {
            return DataCount;
        }

        public void setDataCount(int DataCount) {
            this.DataCount = DataCount;
        }

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int PageIndex) {
            this.PageIndex = PageIndex;
        }

        public Object getStatisticsInfo() {
            return StatisticsInfo;
        }

        public void setStatisticsInfo(Object StatisticsInfo) {
            this.StatisticsInfo = StatisticsInfo;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class DataListBean implements Serializable {
            /**
             * DS_Value : 0.0
             * VS_Value : 0.0
             * RS_Value : 0.0
             * GID : 3fa6a15a-698e-4f10-b6ad-0c062ac1f875
             * VG_GID : b6a603d4-ec07-4b85-8252-87bfa8d79697
             * VT_Code : IntegerCard
             * VIP_Name : 哈哈哈
             * VIP_CellPhone : 15858745854
             * VIP_Birthday : null
             * VIP_Email :
             * EM_ID : null
             * VIP_Overdue : null
             * VIP_IsForver : 1
             * VIP_State : 0
             * VIP_Sex : 0
             * VIP_FixedPhone :
             * VIP_UpdateTime : 2017-07-14 14:12:48
             * VIP_Referee :
             * VIP_FaceNumber :
             * VIP_Remark :
             * CY_GID : 348e79f2-ff35-4d62-8298-9d37f06fd5e0
             * VIP_HeadPicture : null
             * SM_ID : null
             * VIP_IsDeleted : 0
             * VIP_Percent : 0
             * VCH_Card : 568
             * VCH_Fee : 0
             * VG_Name : 默认等级
             * VGC_Code : null
             * US_Value : null
             * VT_Name : 积分卡
             * VCH_Type : 0
             * VCH_UpdateTime : 0001-01-01 00:00:00
             * MA_AccountBalance : 507.0
             * MA_AggregateAmount : 7917.4
             * MA_AvailableBalance : 507.0
             * MA_AvailableIntegral : 188.4
             * MA_FreezeBalance : 0.0
             * VIP_Addr :
             * VIP_HeadImg : /FileUpload/131444749687065657.png
             * SM_Name : 默认店铺
             * VCH_CreateTime : 2017-07-14 11:02:51
             * VIP_Creator : 1540004275@qq.com
             * VCH_Pwd : null
             * VIP_ICCard :
             * US_Name : null
             * VIP_Label : null
             * VG_IsAccount : null
             * VG_IsIntegral : null
             * VG_IsDiscount : null
             * VG_IsCount : null
             * VG_IsTime : null
             */

            private double DS_Value;
            private double VS_Value;
            private double RS_Value;
            private String GID;
            private String VG_GID;
            private String VT_Code;
            private String VIP_Name;
            private String VIP_CellPhone;
            private String VIP_Birthday;
            private String VIP_Email;
            private Object EM_ID;
            private String VIP_Overdue;
            private int VIP_IsForver;
            private int VIP_State;
            private int VIP_Sex;
            private String VIP_FixedPhone;
            private String VIP_UpdateTime;
            private String VIP_Referee;
            private String VIP_FaceNumber;
            private String VIP_Remark;
            private String CY_GID;
            private Object VIP_HeadPicture;
            private Object SM_ID;
            private int VIP_IsDeleted;
            private int VIP_Percent;
            private String VCH_Card;
            private String VCH_Fee;
            private String VG_Name;
            private Object VGC_Code;
            private Object US_Value;
            private String VT_Name;
            private int VCH_Type;
            private String VCH_UpdateTime;
            private double MA_AccountBalance;
            private double MA_AggregateAmount;
            private double MA_AvailableBalance;
            private double MA_AvailableIntegral;
            private double MA_FreezeBalance;
            private String VIP_Addr;
            private String VIP_HeadImg;
            private String SM_Name;
            private String VCH_CreateTime;
            private String VIP_Creator;
            private Object VCH_Pwd;
            private String VIP_ICCard;
            private Object US_Name;
            private String VIP_Label;
            private Object VG_IsAccount;
            private int VG_IsIntegral;
            private Object VG_IsDiscount;
            private Object VG_IsCount;
            private Object VG_IsTime;
            private boolean checked;
            //长点击item
            private boolean longClick;

            public boolean getLongClick() {
                return longClick;
            }

            public void setLongClick(boolean longClick) {
                this.longClick = longClick;
            }

            public boolean isChecked() {
                return checked;
            }

            public void setChecked(boolean checked) {
                this.checked = checked;
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

            public String getVT_Code() {
                return VT_Code;
            }

            public void setVT_Code(String VT_Code) {
                this.VT_Code = VT_Code;
            }

            public String getVIP_Name() {
                return VIP_Name;
            }

            public void setVIP_Name(String VIP_Name) {
                this.VIP_Name = VIP_Name;
            }

            public String getVIP_CellPhone() {
                return VIP_CellPhone;
            }

            public void setVIP_CellPhone(String VIP_CellPhone) {
                this.VIP_CellPhone = VIP_CellPhone;
            }

            public String getVIP_Birthday() {
                return VIP_Birthday;
            }

            public void setVIP_Birthday(String VIP_Birthday) {
                this.VIP_Birthday = VIP_Birthday;
            }

            public String getVIP_Email() {
                return VIP_Email;
            }

            public void setVIP_Email(String VIP_Email) {
                this.VIP_Email = VIP_Email;
            }

            public Object getEM_ID() {
                return EM_ID;
            }

            public void setEM_ID(Object EM_ID) {
                this.EM_ID = EM_ID;
            }

            public String getVIP_Overdue() {
                return VIP_Overdue;
            }

            public void setVIP_Overdue(String VIP_Overdue) {
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

            public String getVIP_FixedPhone() {
                return VIP_FixedPhone;
            }

            public void setVIP_FixedPhone(String VIP_FixedPhone) {
                this.VIP_FixedPhone = VIP_FixedPhone;
            }

            public String getVIP_UpdateTime() {
                return VIP_UpdateTime;
            }

            public void setVIP_UpdateTime(String VIP_UpdateTime) {
                this.VIP_UpdateTime = VIP_UpdateTime;
            }

            public String getVIP_Referee() {
                return VIP_Referee;
            }

            public void setVIP_Referee(String VIP_Referee) {
                this.VIP_Referee = VIP_Referee;
            }

            public String getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(String VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public String getVIP_Remark() {
                return VIP_Remark;
            }

            public void setVIP_Remark(String VIP_Remark) {
                this.VIP_Remark = VIP_Remark;
            }

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
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
                return TextUtils.isEmpty(VCH_Fee) ? 0 : Integer.parseInt(VCH_Fee);
            }

            public void setVCH_Fee(int VCH_Fee) {
                this.VCH_Fee = VCH_Fee + "";
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

            public String getVT_Name() {
                return VT_Name;
            }

            public void setVT_Name(String VT_Name) {
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

            public String getVIP_Addr() {
                return VIP_Addr;
            }

            public void setVIP_Addr(String VIP_Addr) {
                this.VIP_Addr = VIP_Addr;
            }

            public String getVIP_HeadImg() {
                return VIP_HeadImg;
            }

            public void setVIP_HeadImg(String VIP_HeadImg) {
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

            public String getVIP_ICCard() {
                return VIP_ICCard;
            }

            public void setVIP_ICCard(String VIP_ICCard) {
                this.VIP_ICCard = VIP_ICCard;
            }

            public Object getUS_Name() {
                return US_Name;
            }

            public void setUS_Name(Object US_Name) {
                this.US_Name = US_Name;
            }

            public String getVIP_Label() {
                return VIP_Label;
            }

            public void setVIP_Label(String VIP_Label) {
                this.VIP_Label = VIP_Label;
            }

            public Object getVG_IsAccount() {
                return VG_IsAccount;
            }

            public void setVG_IsAccount(Object VG_IsAccount) {
                this.VG_IsAccount = VG_IsAccount;
            }

            public int getVG_IsIntegral() {
                return VG_IsIntegral;
            }

            public void setVG_IsIntegral(int VG_IsIntegral) {
                this.VG_IsIntegral = VG_IsIntegral;
            }

            public Object getVG_IsDiscount() {
                return VG_IsDiscount;
            }

            public void setVG_IsDiscount(Object VG_IsDiscount) {
                this.VG_IsDiscount = VG_IsDiscount;
            }

            public Object getVG_IsCount() {
                return VG_IsCount;
            }

            public void setVG_IsCount(Object VG_IsCount) {
                this.VG_IsCount = VG_IsCount;
            }

            public Object getVG_IsTime() {
                return VG_IsTime;
            }

            public void setVG_IsTime(Object VG_IsTime) {
                this.VG_IsTime = VG_IsTime;
            }
        }
    }
}
