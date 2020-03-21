package com.zhiluo.android.yunpu.member.jsonbean;

import java.io.Serializable;

/**
 * Created by 70700 on 2016/11/15.
 */
public class VipDetails {
    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GID":"2be79c78-d8f0-40f3-97a1-e6c427400d35","VG_GID":"0fb2f30c-4ff4-4941-8aaf-1e1c425488e8","VT_Code":"DiscountCard","VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":null,"EM_ID":"请选择办卡员","VIP_Overdue":null,"VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":null,"VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"8030","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":"打折卡","VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"哇哈哈汽车美容","VCH_CreateTime":"2016-04-12 17:46:05","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null}
     */

    private boolean success;
    private Object code;
    private String msg;
    /**
     * GID : 2be79c78-d8f0-40f3-97a1-e6c427400d35
     * VG_GID : 0fb2f30c-4ff4-4941-8aaf-1e1c425488e8
     * VT_Code : DiscountCard
     * VIP_Name : null
     * VIP_CellPhone : null
     * VIP_Birthday : 0001-01-01 00:00:00
     * VIP_Email : null
     * EM_ID : 请选择办卡员
     * VIP_Overdue : null
     * VIP_IsForver : 1
     * VIP_State : 0
     * VIP_Sex : 0
     * VIP_FixedPhone : null
     * VIP_UpdateTime : 0001-01-01 00:00:00
     * VIP_Referee : null
     * VIP_FaceNumber : null
     * VIP_Remark : null
     * CY_GID : null
     * VIP_HeadPicture : null
     * SM_ID : null
     * VIP_IsDeleted : 0
     * VIP_Percent : 0
     * VCH_Card : 8030
     * VCH_Fee : 0
     * VG_Name : 默认等级
     * VGC_Code : null
     * US_Value : 0
     * VT_Name : 打折卡
     * VCH_Type : 0
     * VCH_UpdateTime : 0001-01-01 00:00:00
     * MA_AccountBalance : 0
     * MA_AggregateAmount : 0
     * MA_AvailableBalance : 0
     * MA_AvailableIntegral : 0
     * MA_FreezeBalance : 0
     * VIP_Addr : null
     * SM_Name : 哇哈哈汽车美容
     * VCH_CreateTime : 2016-04-12 17:46:05
     * VIP_Creator : null
     * VCH_Pwd : null
     * VIP_ICCard : null
     * US_Name : null
     */

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
        private String GID;
        private String VG_GID;
        private String VT_Code;
        private String VIP_Name;
        private Object VIP_CellPhone;
        private String VIP_Birthday;
        private Object VIP_Email;
        private String EM_ID;
        private Object VIP_Overdue;
        private int VIP_IsForver;
        private int VIP_State;
        private int VIP_Sex;
        private Object VIP_FixedPhone;
        private String VIP_UpdateTime;
        private Object VIP_Referee;
        private Object VIP_FaceNumber;
        private Object VIP_Remark;
        private Object CY_GID;
        private Object VIP_HeadPicture;
        private Object SM_ID;
        private int VIP_IsDeleted;
        private int VIP_Percent;
        private String VCH_Card;
        private int VCH_Fee;
        private String VG_Name;
        private Object VGC_Code;
        private String US_Value;
        private String VT_Name;
        private int VCH_Type;
        private String VCH_UpdateTime;
        private String MA_AccountBalance;
        private String MA_AggregateAmount;
        private String MA_AvailableBalance;
        private String MA_AvailableIntegral;
        private String MA_FreezeBalance;
        private Object VIP_Addr;
        private String SM_Name;
        private String VCH_CreateTime;
        private Object VIP_Creator;
        private Object VCH_Pwd;
        private Object VIP_ICCard;
        private Object US_Name;

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

        public Object getVIP_CellPhone() {
            return VIP_CellPhone;
        }

        public void setVIP_CellPhone(Object VIP_CellPhone) {
            this.VIP_CellPhone = VIP_CellPhone;
        }

        public String getVIP_Birthday() {
            return VIP_Birthday;
        }

        public void setVIP_Birthday(String VIP_Birthday) {
            this.VIP_Birthday = VIP_Birthday;
        }

        public Object getVIP_Email() {
            return VIP_Email;
        }

        public void setVIP_Email(Object VIP_Email) {
            this.VIP_Email = VIP_Email;
        }

        public String getEM_ID() {
            return EM_ID;
        }

        public void setEM_ID(String EM_ID) {
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

        public Object getVIP_Remark() {
            return VIP_Remark;
        }

        public void setVIP_Remark(Object VIP_Remark) {
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

        public String getUS_Value() {
            return US_Value;
        }

        public void setUS_Value(String US_Value) {
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

        public String getMA_AccountBalance() {
            return MA_AccountBalance;
        }

        public void setMA_AccountBalance(String MA_AccountBalance) {
            this.MA_AccountBalance = MA_AccountBalance;
        }

        public String getMA_AggregateAmount() {
            return MA_AggregateAmount;
        }

        public void setMA_AggregateAmount(String MA_AggregateAmount) {
            this.MA_AggregateAmount = MA_AggregateAmount;
        }

        public String getMA_AvailableBalance() {
            return MA_AvailableBalance;
        }

        public void setMA_AvailableBalance(String MA_AvailableBalance) {
            this.MA_AvailableBalance = MA_AvailableBalance;
        }

        public String getMA_AvailableIntegral() {
            return MA_AvailableIntegral;
        }

        public void setMA_AvailableIntegral(String MA_AvailableIntegral) {
            this.MA_AvailableIntegral = MA_AvailableIntegral;
        }

        public String getMA_FreezeBalance() {
            return MA_FreezeBalance;
        }

        public void setMA_FreezeBalance(String MA_FreezeBalance) {
            this.MA_FreezeBalance = MA_FreezeBalance;
        }

        public Object getVIP_Addr() {
            return VIP_Addr;
        }

        public void setVIP_Addr(Object VIP_Addr) {
            this.VIP_Addr = VIP_Addr;
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

        public Object getVIP_Creator() {
            return VIP_Creator;
        }

        public void setVIP_Creator(Object VIP_Creator) {
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
    }
}
