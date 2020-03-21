package com.zhiluo.android.yunpu.member.consume.bean;

/**
 * Created by ${YSL} on 2018-03-24.
 */

public class VipInflateMoneyBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"EM_GIDList":null,"MR_IdentifyingState":-1,"DisMoney":0,"PayPoint":0,"CC_GID":"1","VCH_Card":"110","IS_Sms":false,"MR_EMName":null,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":null,"VIP_CellPhone":null,"GID":"4c965faf-e63a-4239-8956-b99df8f7fd4d","SM_GID":"1393ac39-84e9-464f-93c3-e47865396555","VIP_GID":"cc011142-bcf1-4b71-851b-4e15be83abbf","MR_Order":"CZ1803241724421491","MR_Way":"","MR_Code":"","MR_Amount":20,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":20,"MR_PrepaidTime":"2018-03-24 17:24:56","MA_AvailableBalance":null,"MI_Number":null,"MR_Declare":null,"MR_Identifying":"待支付","MR_Remark":"","MR_Creator":"18381306468","MR_UpdateTime":"2018-03-24 17:24:56","CY_GID":"d9e20c93-b7b3-465c-93b2-8ba0729d80d7","MR_Device":1,"MR_EMGID":null}
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

    public static class DataBean {
        /**
         * EM_GIDList : null
         * MR_IdentifyingState : -1
         * DisMoney : 0.0
         * PayPoint : 0.0
         * CC_GID : 1
         * VCH_Card : 110
         * IS_Sms : false
         * MR_EMName : null
         * MR_SSMoney : 0.0
         * MR_ZLMoney : 0.0
         * MR_ActivityName : null
         * MR_ActivityValue : null
         * VIP_Name : null
         * VIP_CellPhone : null
         * GID : 4c965faf-e63a-4239-8956-b99df8f7fd4d
         * SM_GID : 1393ac39-84e9-464f-93c3-e47865396555
         * VIP_GID : cc011142-bcf1-4b71-851b-4e15be83abbf
         * MR_Order : CZ1803241724421491
         * MR_Way :
         * MR_Code :
         * MR_Amount : 20.0
         * MR_GivenAmount : 0.0
         * MR_Integral : 0
         * MR_Total : 20.0
         * MR_PrepaidTime : 2018-03-24 17:24:56
         * MA_AvailableBalance : null
         * MI_Number : null
         * MR_Declare : null
         * MR_Identifying : 待支付
         * MR_Remark :
         * MR_Creator : 18381306468
         * MR_UpdateTime : 2018-03-24 17:24:56
         * CY_GID : d9e20c93-b7b3-465c-93b2-8ba0729d80d7
         * MR_Device : 1
         * MR_EMGID : null
         */

        private Object EM_GIDList;
        private int MR_IdentifyingState;
        private double DisMoney;
        private double PayPoint;
        private String CC_GID;
        private String VCH_Card;
        private boolean IS_Sms;
        private Object MR_EMName;
        private double MR_SSMoney;
        private double MR_ZLMoney;
        private Object MR_ActivityName;
        private Object MR_ActivityValue;
        private Object VIP_Name;
        private Object VIP_CellPhone;
        private String GID;
        private String SM_GID;
        private String VIP_GID;
        private String MR_Order;
        private String MR_Way;
        private String MR_Code;
        private double MR_Amount;
        private double MR_GivenAmount;
        private double MR_Integral;
        private double MR_Total;
        private String MR_PrepaidTime;
        private Object MA_AvailableBalance;
        private Object MI_Number;
        private Object MR_Declare;
        private String MR_Identifying;
        private String MR_Remark;
        private String MR_Creator;
        private String MR_UpdateTime;
        private String CY_GID;
        private int MR_Device;
        private Object MR_EMGID;

        public Object getEM_GIDList() {
            return EM_GIDList;
        }

        public void setEM_GIDList(Object EM_GIDList) {
            this.EM_GIDList = EM_GIDList;
        }

        public int getMR_IdentifyingState() {
            return MR_IdentifyingState;
        }

        public void setMR_IdentifyingState(int MR_IdentifyingState) {
            this.MR_IdentifyingState = MR_IdentifyingState;
        }

        public double getDisMoney() {
            return DisMoney;
        }

        public void setDisMoney(double DisMoney) {
            this.DisMoney = DisMoney;
        }

        public double getPayPoint() {
            return PayPoint;
        }

        public void setPayPoint(double PayPoint) {
            this.PayPoint = PayPoint;
        }

        public String getCC_GID() {
            return CC_GID;
        }

        public void setCC_GID(String CC_GID) {
            this.CC_GID = CC_GID;
        }

        public String getVCH_Card() {
            return VCH_Card;
        }

        public void setVCH_Card(String VCH_Card) {
            this.VCH_Card = VCH_Card;
        }

        public boolean isIS_Sms() {
            return IS_Sms;
        }

        public void setIS_Sms(boolean IS_Sms) {
            this.IS_Sms = IS_Sms;
        }

        public Object getMR_EMName() {
            return MR_EMName;
        }

        public void setMR_EMName(Object MR_EMName) {
            this.MR_EMName = MR_EMName;
        }

        public double getMR_SSMoney() {
            return MR_SSMoney;
        }

        public void setMR_SSMoney(double MR_SSMoney) {
            this.MR_SSMoney = MR_SSMoney;
        }

        public double getMR_ZLMoney() {
            return MR_ZLMoney;
        }

        public void setMR_ZLMoney(double MR_ZLMoney) {
            this.MR_ZLMoney = MR_ZLMoney;
        }

        public Object getMR_ActivityName() {
            return MR_ActivityName;
        }

        public void setMR_ActivityName(Object MR_ActivityName) {
            this.MR_ActivityName = MR_ActivityName;
        }

        public Object getMR_ActivityValue() {
            return MR_ActivityValue;
        }

        public void setMR_ActivityValue(Object MR_ActivityValue) {
            this.MR_ActivityValue = MR_ActivityValue;
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

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getSM_GID() {
            return SM_GID;
        }

        public void setSM_GID(String SM_GID) {
            this.SM_GID = SM_GID;
        }

        public String getVIP_GID() {
            return VIP_GID;
        }

        public void setVIP_GID(String VIP_GID) {
            this.VIP_GID = VIP_GID;
        }

        public String getMR_Order() {
            return MR_Order;
        }

        public void setMR_Order(String MR_Order) {
            this.MR_Order = MR_Order;
        }

        public String getMR_Way() {
            return MR_Way;
        }

        public void setMR_Way(String MR_Way) {
            this.MR_Way = MR_Way;
        }

        public String getMR_Code() {
            return MR_Code;
        }

        public void setMR_Code(String MR_Code) {
            this.MR_Code = MR_Code;
        }

        public double getMR_Amount() {
            return MR_Amount;
        }

        public void setMR_Amount(double MR_Amount) {
            this.MR_Amount = MR_Amount;
        }

        public double getMR_GivenAmount() {
            return MR_GivenAmount;
        }

        public void setMR_GivenAmount(double MR_GivenAmount) {
            this.MR_GivenAmount = MR_GivenAmount;
        }

        public double getMR_Integral() {
            return MR_Integral;
        }

        public void setMR_Integral(double MR_Integral) {
            this.MR_Integral = MR_Integral;
        }

        public double getMR_Total() {
            return MR_Total;
        }

        public void setMR_Total(double MR_Total) {
            this.MR_Total = MR_Total;
        }

        public String getMR_PrepaidTime() {
            return MR_PrepaidTime;
        }

        public void setMR_PrepaidTime(String MR_PrepaidTime) {
            this.MR_PrepaidTime = MR_PrepaidTime;
        }

        public Object getMA_AvailableBalance() {
            return MA_AvailableBalance;
        }

        public void setMA_AvailableBalance(Object MA_AvailableBalance) {
            this.MA_AvailableBalance = MA_AvailableBalance;
        }

        public Object getMI_Number() {
            return MI_Number;
        }

        public void setMI_Number(Object MI_Number) {
            this.MI_Number = MI_Number;
        }

        public Object getMR_Declare() {
            return MR_Declare;
        }

        public void setMR_Declare(Object MR_Declare) {
            this.MR_Declare = MR_Declare;
        }

        public String getMR_Identifying() {
            return MR_Identifying;
        }

        public void setMR_Identifying(String MR_Identifying) {
            this.MR_Identifying = MR_Identifying;
        }

        public String getMR_Remark() {
            return MR_Remark;
        }

        public void setMR_Remark(String MR_Remark) {
            this.MR_Remark = MR_Remark;
        }

        public String getMR_Creator() {
            return MR_Creator;
        }

        public void setMR_Creator(String MR_Creator) {
            this.MR_Creator = MR_Creator;
        }

        public String getMR_UpdateTime() {
            return MR_UpdateTime;
        }

        public void setMR_UpdateTime(String MR_UpdateTime) {
            this.MR_UpdateTime = MR_UpdateTime;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public int getMR_Device() {
            return MR_Device;
        }

        public void setMR_Device(int MR_Device) {
            this.MR_Device = MR_Device;
        }

        public Object getMR_EMGID() {
            return MR_EMGID;
        }

        public void setMR_EMGID(Object MR_EMGID) {
            this.MR_EMGID = MR_EMGID;
        }
    }
}
