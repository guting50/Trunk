package com.zhiluo.android.yunpu.member.jsonbean;

/**
 * iauthor：Cheng
 * date: 2017/4/22 10:38
 * email：jasoncheng9111@gmail.com
 */
public class QuerySingleMemberJsonBean
{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GID":"d39065e4-2063-47d6-88e0-6ef3fc9a2f75","VG_GID":"dc101e42-d83c-42c2-9edd-6707880ffad2","VT_Code":null,"VIP_Name":"马克炸了","VIP_CellPhone":"13333333333",
     * "VIP_Birthday":null,"VIP_Email":"","EM_ID":"","VIP_Overdue":null,"VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00",
     * "VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"01","VCH_Fee":0,
     * "VG_Name":"默认等级","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,
     * "MA_AvailableBalance":0,"MA_AvailableIntegral":48.4,"MA_FreezeBalance":0,"VIP_Addr":"","VIP_HeadImg":null,"SM_Name":"Tokyo Hot","VCH_CreateTime":"2017-04-21 11:04:26",
     * "VIP_Creator":"18986810706","VCH_Pwd":null,"VIP_ICCard":"","US_Name":null,"VIP_Label":null,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":0}
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
         * GID : d39065e4-2063-47d6-88e0-6ef3fc9a2f75
         * VG_GID : dc101e42-d83c-42c2-9edd-6707880ffad2
         * VT_Code : null
         * VIP_Name : 马克炸了
         * VIP_CellPhone : 13333333333
         * VIP_Birthday : null
         * VIP_Email :
         * EM_ID :
         * VIP_Overdue : null
         * VIP_IsForver : 1
         * VIP_State : 0
         * VIP_Sex : 0
         * VIP_FixedPhone :
         * VIP_UpdateTime : 0001-01-01 00:00:00
         * VIP_Referee :
         * VIP_FaceNumber :
         * VIP_Remark :
         * CY_GID : null
         * VIP_HeadPicture : null
         * SM_ID : null
         * VIP_IsDeleted : 0
         * VIP_Percent : 0
         * VCH_Card : 01
         * VCH_Fee : 0
         * VG_Name : 默认等级
         * VGC_Code : null
         * US_Value : null
         * VT_Name : null
         * VCH_Type : 0
         * VCH_UpdateTime : 0001-01-01 00:00:00
         * MA_AccountBalance : 0
         * MA_AggregateAmount : 0
         * MA_AvailableBalance : 0
         * MA_AvailableIntegral : 48.4
         * MA_FreezeBalance : 0
         * VIP_Addr :
         * VIP_HeadImg : null
         * SM_Name : Tokyo Hot
         * VCH_CreateTime : 2017-04-21 11:04:26
         * VIP_Creator : 18986810706
         * VCH_Pwd : null
         * VIP_ICCard :
         * US_Name : null
         * VIP_Label : null
         * VG_IsAccount : 1
         * VG_IsIntegral : 1
         * VG_IsDiscount : 1
         * VG_IsCount : 1
         * VG_IsTime : 0
         */

        private String GID;
        private String VG_GID;
        private Object VT_Code;
        private String VIP_Name;
        private String VIP_CellPhone;
        private Object VIP_Birthday;
        private String VIP_Email;
        private String EM_ID;
        private Object VIP_Overdue;
        private int VIP_IsForver;
        private int VIP_State;
        private int VIP_Sex;
        private String VIP_FixedPhone;
        private String VIP_UpdateTime;
        private String VIP_Referee;
        private String VIP_FaceNumber;
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
        private int MA_AccountBalance;
        private int MA_AggregateAmount;
        private int MA_AvailableBalance;
        private double MA_AvailableIntegral;
        private int MA_FreezeBalance;
        private String VIP_Addr;
        private Object VIP_HeadImg;
        private String SM_Name;
        private String VCH_CreateTime;
        private String VIP_Creator;
        private Object VCH_Pwd;
        private String VIP_ICCard;
        private Object US_Name;
        private Object VIP_Label;
        private int VG_IsAccount;
        private int VG_IsIntegral;
        private int VG_IsDiscount;
        private int VG_IsCount;
        private int VG_IsTime;

        public String getGID() { return GID;}

        public void setGID(String GID) { this.GID = GID;}

        public String getVG_GID() { return VG_GID;}

        public void setVG_GID(String VG_GID) { this.VG_GID = VG_GID;}

        public Object getVT_Code() { return VT_Code;}

        public void setVT_Code(Object VT_Code) { this.VT_Code = VT_Code;}

        public String getVIP_Name() { return VIP_Name;}

        public void setVIP_Name(String VIP_Name) { this.VIP_Name = VIP_Name;}

        public String getVIP_CellPhone() { return VIP_CellPhone;}

        public void setVIP_CellPhone(String VIP_CellPhone) { this.VIP_CellPhone = VIP_CellPhone;}

        public Object getVIP_Birthday() { return VIP_Birthday;}

        public void setVIP_Birthday(Object VIP_Birthday) { this.VIP_Birthday = VIP_Birthday;}

        public String getVIP_Email() { return VIP_Email;}

        public void setVIP_Email(String VIP_Email) { this.VIP_Email = VIP_Email;}

        public String getEM_ID() { return EM_ID;}

        public void setEM_ID(String EM_ID) { this.EM_ID = EM_ID;}

        public Object getVIP_Overdue() { return VIP_Overdue;}

        public void setVIP_Overdue(Object VIP_Overdue) { this.VIP_Overdue = VIP_Overdue;}

        public int getVIP_IsForver() { return VIP_IsForver;}

        public void setVIP_IsForver(int VIP_IsForver) { this.VIP_IsForver = VIP_IsForver;}

        public int getVIP_State() { return VIP_State;}

        public void setVIP_State(int VIP_State) { this.VIP_State = VIP_State;}

        public int getVIP_Sex() { return VIP_Sex;}

        public void setVIP_Sex(int VIP_Sex) { this.VIP_Sex = VIP_Sex;}

        public String getVIP_FixedPhone() { return VIP_FixedPhone;}

        public void setVIP_FixedPhone(String VIP_FixedPhone) { this.VIP_FixedPhone = VIP_FixedPhone;}

        public String getVIP_UpdateTime() { return VIP_UpdateTime;}

        public void setVIP_UpdateTime(String VIP_UpdateTime) { this.VIP_UpdateTime = VIP_UpdateTime;}

        public String getVIP_Referee() { return VIP_Referee;}

        public void setVIP_Referee(String VIP_Referee) { this.VIP_Referee = VIP_Referee;}

        public String getVIP_FaceNumber() { return VIP_FaceNumber;}

        public void setVIP_FaceNumber(String VIP_FaceNumber) { this.VIP_FaceNumber = VIP_FaceNumber;}

        public String getVIP_Remark() { return VIP_Remark;}

        public void setVIP_Remark(String VIP_Remark) { this.VIP_Remark = VIP_Remark;}

        public Object getCY_GID() { return CY_GID;}

        public void setCY_GID(Object CY_GID) { this.CY_GID = CY_GID;}

        public Object getVIP_HeadPicture() { return VIP_HeadPicture;}

        public void setVIP_HeadPicture(Object VIP_HeadPicture) { this.VIP_HeadPicture = VIP_HeadPicture;}

        public Object getSM_ID() { return SM_ID;}

        public void setSM_ID(Object SM_ID) { this.SM_ID = SM_ID;}

        public int getVIP_IsDeleted() { return VIP_IsDeleted;}

        public void setVIP_IsDeleted(int VIP_IsDeleted) { this.VIP_IsDeleted = VIP_IsDeleted;}

        public int getVIP_Percent() { return VIP_Percent;}

        public void setVIP_Percent(int VIP_Percent) { this.VIP_Percent = VIP_Percent;}

        public String getVCH_Card() { return VCH_Card;}

        public void setVCH_Card(String VCH_Card) { this.VCH_Card = VCH_Card;}

        public int getVCH_Fee() { return VCH_Fee;}

        public void setVCH_Fee(int VCH_Fee) { this.VCH_Fee = VCH_Fee;}

        public String getVG_Name() { return VG_Name;}

        public void setVG_Name(String VG_Name) { this.VG_Name = VG_Name;}

        public Object getVGC_Code() { return VGC_Code;}

        public void setVGC_Code(Object VGC_Code) { this.VGC_Code = VGC_Code;}

        public Object getUS_Value() { return US_Value;}

        public void setUS_Value(Object US_Value) { this.US_Value = US_Value;}

        public Object getVT_Name() { return VT_Name;}

        public void setVT_Name(Object VT_Name) { this.VT_Name = VT_Name;}

        public int getVCH_Type() { return VCH_Type;}

        public void setVCH_Type(int VCH_Type) { this.VCH_Type = VCH_Type;}

        public String getVCH_UpdateTime() { return VCH_UpdateTime;}

        public void setVCH_UpdateTime(String VCH_UpdateTime) { this.VCH_UpdateTime = VCH_UpdateTime;}

        public int getMA_AccountBalance() { return MA_AccountBalance;}

        public void setMA_AccountBalance(int MA_AccountBalance) { this.MA_AccountBalance = MA_AccountBalance;}

        public int getMA_AggregateAmount() { return MA_AggregateAmount;}

        public void setMA_AggregateAmount(int MA_AggregateAmount) { this.MA_AggregateAmount = MA_AggregateAmount;}

        public int getMA_AvailableBalance() { return MA_AvailableBalance;}

        public void setMA_AvailableBalance(int MA_AvailableBalance) { this.MA_AvailableBalance = MA_AvailableBalance;}

        public double getMA_AvailableIntegral() { return MA_AvailableIntegral;}

        public void setMA_AvailableIntegral(double MA_AvailableIntegral) { this.MA_AvailableIntegral = MA_AvailableIntegral;}

        public int getMA_FreezeBalance() { return MA_FreezeBalance;}

        public void setMA_FreezeBalance(int MA_FreezeBalance) { this.MA_FreezeBalance = MA_FreezeBalance;}

        public String getVIP_Addr() { return VIP_Addr;}

        public void setVIP_Addr(String VIP_Addr) { this.VIP_Addr = VIP_Addr;}

        public Object getVIP_HeadImg() { return VIP_HeadImg;}

        public void setVIP_HeadImg(Object VIP_HeadImg) { this.VIP_HeadImg = VIP_HeadImg;}

        public String getSM_Name() { return SM_Name;}

        public void setSM_Name(String SM_Name) { this.SM_Name = SM_Name;}

        public String getVCH_CreateTime() { return VCH_CreateTime;}

        public void setVCH_CreateTime(String VCH_CreateTime) { this.VCH_CreateTime = VCH_CreateTime;}

        public String getVIP_Creator() { return VIP_Creator;}

        public void setVIP_Creator(String VIP_Creator) { this.VIP_Creator = VIP_Creator;}

        public Object getVCH_Pwd() { return VCH_Pwd;}

        public void setVCH_Pwd(Object VCH_Pwd) { this.VCH_Pwd = VCH_Pwd;}

        public String getVIP_ICCard() { return VIP_ICCard;}

        public void setVIP_ICCard(String VIP_ICCard) { this.VIP_ICCard = VIP_ICCard;}

        public Object getUS_Name() { return US_Name;}

        public void setUS_Name(Object US_Name) { this.US_Name = US_Name;}

        public Object getVIP_Label() { return VIP_Label;}

        public void setVIP_Label(Object VIP_Label) { this.VIP_Label = VIP_Label;}

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
    }
}
