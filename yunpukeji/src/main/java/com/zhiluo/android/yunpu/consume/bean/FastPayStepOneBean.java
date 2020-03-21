package com.zhiluo.android.yunpu.consume.bean;

/**
 * Created by ${YSL} on 2018-03-23.
 */

public class FastPayStepOneBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GoodsOrderDetailList":null,"Goods":null,"CC_GID":"","EM_GIDList":null,"PayPoint":0,"DisMoney":1000,"VCH_Money":0,"VCH_Point":0,"CO_EMName":null,"CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":"","CO_Discount":1,"GID":"fe757b35-4c61-476d-9799-518d51b8a09f","VIP_GID":"4879305f-8170-48a9-8af5-92b62fccf04a","VIP_Card":"123","VIP_Name":"关羽","VIP_Phone":"18381306468","VIP_FaceNumber":"123","CO_OrderCode":"KS1803230859084064","CO_ConsumeWay":"","CO_WayCode":"","CO_Monetary":1000,"CO_TotalPrice":1000,"CO_Integral":0,"CO_BalanceCard":null,"CO_Type":"快速消费","CO_TypeCode":"KSXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"18381306468","CO_UpdateTime":"2018-03-23 08:59:18","CY_GID":"d9e20c93-b7b3-465c-93b2-8ba0729d80d7","SM_GID":"1393ac39-84e9-464f-93c3-e47865396555","SM_Name":"店铺一","SM_Contacter":"张三","SM_Phone":"18381306468","SM_Addr":null,"CO_Identifying":"待支付","CO_IdentifyingState":-1,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":1,"CO_ActivityType":null,"CO_ActivityMoney":null}
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
         * GoodsOrderDetailList : null
         * Goods : null
         * CC_GID :
         * EM_GIDList : null
         * PayPoint : 0.0
         * DisMoney : 1000.0
         * VCH_Money : 0.0
         * VCH_Point : 0.0
         * CO_EMName : null
         * CO_EMGID : null
         * CO_SSMoney : 0.0
         * CO_ZLMoney : 0.0
         * CO_ActivityName : null
         * CO_ActivityValue :
         * CO_Discount : 1.0
         * GID : fe757b35-4c61-476d-9799-518d51b8a09f
         * VIP_GID : 4879305f-8170-48a9-8af5-92b62fccf04a
         * VIP_Card : 123
         * VIP_Name : 关羽
         * VIP_Phone : 18381306468
         * VIP_FaceNumber : 123
         * CO_OrderCode : KS1803230859084064
         * CO_ConsumeWay :
         * CO_WayCode :
         * CO_Monetary : 1000.0
         * CO_TotalPrice : 1000.0
         * CO_Integral : 0.0
         * CO_BalanceCard : null
         * CO_Type : 快速消费
         * CO_TypeCode : KSXF
         * CO_CommissionAmount : null
         * CO_Remark :
         * CO_Creator : 18381306468
         * CO_UpdateTime : 2018-03-23 08:59:18
         * CY_GID : d9e20c93-b7b3-465c-93b2-8ba0729d80d7
         * SM_GID : 1393ac39-84e9-464f-93c3-e47865396555
         * SM_Name : 店铺一
         * SM_Contacter : 张三
         * SM_Phone : 18381306468
         * SM_Addr : null
         * CO_Identifying : 待支付
         * CO_IdentifyingState : -1
         * CO_OrderState : null
         * CO_StateValue : null
         * CO_Device : 1
         * CO_ActivityType : null
         * CO_ActivityMoney : null
         */

        private Object GoodsOrderDetailList;
        private Object Goods;
        private String CC_GID;
        private Object EM_GIDList;
        private double PayPoint;
        private double DisMoney;
        private double VCH_Money;
        private double VCH_Point;
        private Object CO_EMName;
        private Object CO_EMGID;
        private double CO_SSMoney;
        private double CO_ZLMoney;
        private Object CO_ActivityName;
        private String CO_ActivityValue;
        private double CO_Discount;
        private String GID;
        private String VIP_GID;
        private String VIP_Card;
        private String VIP_Name;
        private String VIP_Phone;
        private String VIP_FaceNumber;
        private String CO_OrderCode;
        private String CO_ConsumeWay;
        private String CO_WayCode;
        private double CO_Monetary;
        private double CO_TotalPrice;
        private double CO_Integral;
        private Object CO_BalanceCard;
        private String CO_Type;
        private String CO_TypeCode;
        private Object CO_CommissionAmount;
        private String CO_Remark;
        private String CO_Creator;
        private String CO_UpdateTime;
        private String CY_GID;
        private String SM_GID;
        private String SM_Name;
        private String SM_Contacter;
        private String SM_Phone;
        private Object SM_Addr;
        private String CO_Identifying;
        private int CO_IdentifyingState;
        private Object CO_OrderState;
        private Object CO_StateValue;
        private int CO_Device;
        private Object CO_ActivityType;
        private Object CO_ActivityMoney;

        public Object getGoodsOrderDetailList() {
            return GoodsOrderDetailList;
        }

        public void setGoodsOrderDetailList(Object GoodsOrderDetailList) {
            this.GoodsOrderDetailList = GoodsOrderDetailList;
        }

        public Object getGoods() {
            return Goods;
        }

        public void setGoods(Object Goods) {
            this.Goods = Goods;
        }

        public String getCC_GID() {
            return CC_GID;
        }

        public void setCC_GID(String CC_GID) {
            this.CC_GID = CC_GID;
        }

        public Object getEM_GIDList() {
            return EM_GIDList;
        }

        public void setEM_GIDList(Object EM_GIDList) {
            this.EM_GIDList = EM_GIDList;
        }

        public double getPayPoint() {
            return PayPoint;
        }

        public void setPayPoint(double PayPoint) {
            this.PayPoint = PayPoint;
        }

        public double getDisMoney() {
            return DisMoney;
        }

        public void setDisMoney(double DisMoney) {
            this.DisMoney = DisMoney;
        }

        public double getVCH_Money() {
            return VCH_Money;
        }

        public void setVCH_Money(double VCH_Money) {
            this.VCH_Money = VCH_Money;
        }

        public double getVCH_Point() {
            return VCH_Point;
        }

        public void setVCH_Point(double VCH_Point) {
            this.VCH_Point = VCH_Point;
        }

        public Object getCO_EMName() {
            return CO_EMName;
        }

        public void setCO_EMName(Object CO_EMName) {
            this.CO_EMName = CO_EMName;
        }

        public Object getCO_EMGID() {
            return CO_EMGID;
        }

        public void setCO_EMGID(Object CO_EMGID) {
            this.CO_EMGID = CO_EMGID;
        }

        public double getCO_SSMoney() {
            return CO_SSMoney;
        }

        public void setCO_SSMoney(double CO_SSMoney) {
            this.CO_SSMoney = CO_SSMoney;
        }

        public double getCO_ZLMoney() {
            return CO_ZLMoney;
        }

        public void setCO_ZLMoney(double CO_ZLMoney) {
            this.CO_ZLMoney = CO_ZLMoney;
        }

        public Object getCO_ActivityName() {
            return CO_ActivityName;
        }

        public void setCO_ActivityName(Object CO_ActivityName) {
            this.CO_ActivityName = CO_ActivityName;
        }

        public String getCO_ActivityValue() {
            return CO_ActivityValue;
        }

        public void setCO_ActivityValue(String CO_ActivityValue) {
            this.CO_ActivityValue = CO_ActivityValue;
        }

        public double getCO_Discount() {
            return CO_Discount;
        }

        public void setCO_Discount(double CO_Discount) {
            this.CO_Discount = CO_Discount;
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

        public String getVIP_Card() {
            return VIP_Card;
        }

        public void setVIP_Card(String VIP_Card) {
            this.VIP_Card = VIP_Card;
        }

        public String getVIP_Name() {
            return VIP_Name;
        }

        public void setVIP_Name(String VIP_Name) {
            this.VIP_Name = VIP_Name;
        }

        public String getVIP_Phone() {
            return VIP_Phone;
        }

        public void setVIP_Phone(String VIP_Phone) {
            this.VIP_Phone = VIP_Phone;
        }

        public String getVIP_FaceNumber() {
            return VIP_FaceNumber;
        }

        public void setVIP_FaceNumber(String VIP_FaceNumber) {
            this.VIP_FaceNumber = VIP_FaceNumber;
        }

        public String getCO_OrderCode() {
            return CO_OrderCode;
        }

        public void setCO_OrderCode(String CO_OrderCode) {
            this.CO_OrderCode = CO_OrderCode;
        }

        public String getCO_ConsumeWay() {
            return CO_ConsumeWay;
        }

        public void setCO_ConsumeWay(String CO_ConsumeWay) {
            this.CO_ConsumeWay = CO_ConsumeWay;
        }

        public String getCO_WayCode() {
            return CO_WayCode;
        }

        public void setCO_WayCode(String CO_WayCode) {
            this.CO_WayCode = CO_WayCode;
        }

        public double getCO_Monetary() {
            return CO_Monetary;
        }

        public void setCO_Monetary(double CO_Monetary) {
            this.CO_Monetary = CO_Monetary;
        }

        public double getCO_TotalPrice() {
            return CO_TotalPrice;
        }

        public void setCO_TotalPrice(double CO_TotalPrice) {
            this.CO_TotalPrice = CO_TotalPrice;
        }

        public double getCO_Integral() {
            return CO_Integral;
        }

        public void setCO_Integral(double CO_Integral) {
            this.CO_Integral = CO_Integral;
        }

        public Object getCO_BalanceCard() {
            return CO_BalanceCard;
        }

        public void setCO_BalanceCard(Object CO_BalanceCard) {
            this.CO_BalanceCard = CO_BalanceCard;
        }

        public String getCO_Type() {
            return CO_Type;
        }

        public void setCO_Type(String CO_Type) {
            this.CO_Type = CO_Type;
        }

        public String getCO_TypeCode() {
            return CO_TypeCode;
        }

        public void setCO_TypeCode(String CO_TypeCode) {
            this.CO_TypeCode = CO_TypeCode;
        }

        public Object getCO_CommissionAmount() {
            return CO_CommissionAmount;
        }

        public void setCO_CommissionAmount(Object CO_CommissionAmount) {
            this.CO_CommissionAmount = CO_CommissionAmount;
        }

        public String getCO_Remark() {
            return CO_Remark;
        }

        public void setCO_Remark(String CO_Remark) {
            this.CO_Remark = CO_Remark;
        }

        public String getCO_Creator() {
            return CO_Creator;
        }

        public void setCO_Creator(String CO_Creator) {
            this.CO_Creator = CO_Creator;
        }

        public String getCO_UpdateTime() {
            return CO_UpdateTime;
        }

        public void setCO_UpdateTime(String CO_UpdateTime) {
            this.CO_UpdateTime = CO_UpdateTime;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
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

        public Object getSM_Addr() {
            return SM_Addr;
        }

        public void setSM_Addr(Object SM_Addr) {
            this.SM_Addr = SM_Addr;
        }

        public String getCO_Identifying() {
            return CO_Identifying;
        }

        public void setCO_Identifying(String CO_Identifying) {
            this.CO_Identifying = CO_Identifying;
        }

        public int getCO_IdentifyingState() {
            return CO_IdentifyingState;
        }

        public void setCO_IdentifyingState(int CO_IdentifyingState) {
            this.CO_IdentifyingState = CO_IdentifyingState;
        }

        public Object getCO_OrderState() {
            return CO_OrderState;
        }

        public void setCO_OrderState(Object CO_OrderState) {
            this.CO_OrderState = CO_OrderState;
        }

        public Object getCO_StateValue() {
            return CO_StateValue;
        }

        public void setCO_StateValue(Object CO_StateValue) {
            this.CO_StateValue = CO_StateValue;
        }

        public int getCO_Device() {
            return CO_Device;
        }

        public void setCO_Device(int CO_Device) {
            this.CO_Device = CO_Device;
        }

        public Object getCO_ActivityType() {
            return CO_ActivityType;
        }

        public void setCO_ActivityType(Object CO_ActivityType) {
            this.CO_ActivityType = CO_ActivityType;
        }

        public Object getCO_ActivityMoney() {
            return CO_ActivityMoney;
        }

        public void setCO_ActivityMoney(Object CO_ActivityMoney) {
            this.CO_ActivityMoney = CO_ActivityMoney;
        }
    }
}
