package com.zhiluo.android.yunpu.print.bean;

import java.io.Serializable;

/**
 * Created by ZPH on 2019-07-04.
 */

public class Print_YJJY_Bean implements Serializable{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"Cashier":"销售001","CheckoutDate":"2019-07-04 17:15:02","OrderCode":"JY1907041715025357","ActivityName":null,"ActivityValue":null,"IntegralAdd":12.5,"IntegralDeduct":0,"YSMoney":100,"SSMoney":100,"ZLMoney":0,"VCH_Card":"8846546616","VIP_Name":"sada541","VCH_Money":0,"VCH_Point":663.5,"GoodsList":null,"ServiceList":null,"GiftList":null,"ConsumeMoney":100,"ConsumeTotal":0,"GoodsMoney":0,"HM_Money":0,"HM_Name":null,"Discount":1,"GiveMoney":0,"RechargeTotal":0,"ExchangeNum":0,"TS_UpdateTime":null,"TimeLong":null,"TS_MinusTimes":null,"TS_SurplusTimes":null,"VIP_FaceNumber":"65+562223","PayInfo":"现金(100)","VCH_Fee":0,"InitialAmount":0,"InitialIntegral":0,"Remark":null,"VIPAddress":"","EMName":null,"IndividualHand":null,"OilsObj":{"GID":"2b94b68d-b00b-4138-a662-b4bf31030297","CO_OrderCode":"JY1907041715025357","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"现金(100)","CO_PayCode":"XJZF","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":2,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-04 17:15:02","dataCount":null}}
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
         * Cashier : 销售001
         * CheckoutDate : 2019-07-04 17:15:02
         * OrderCode : JY1907041715025357
         * ActivityName : null
         * ActivityValue : null
         * IntegralAdd : 12.5
         * IntegralDeduct : 0.0
         * YSMoney : 100.0
         * SSMoney : 100.0
         * ZLMoney : 0.0
         * VCH_Card : 8846546616
         * VIP_Name : sada541
         * VCH_Money : 0.0
         * VCH_Point : 663.5
         * GoodsList : null
         * ServiceList : null
         * GiftList : null
         * ConsumeMoney : 100.0
         * ConsumeTotal : 0.0
         * GoodsMoney : 0.0
         * HM_Money : 0.0
         * HM_Name : null
         * Discount : 1.0
         * GiveMoney : 0.0
         * RechargeTotal : 0.0
         * ExchangeNum : 0
         * TS_UpdateTime : null
         * TimeLong : null
         * TS_MinusTimes : null
         * TS_SurplusTimes : null
         * VIP_FaceNumber : 65+562223
         * PayInfo : 现金(100)
         * VCH_Fee : 0
         * InitialAmount : 0.0
         * InitialIntegral : 0.0
         * Remark : null
         * VIPAddress :
         * EMName : null
         * IndividualHand : null
         * OilsObj : {"GID":"2b94b68d-b00b-4138-a662-b4bf31030297","CO_OrderCode":"JY1907041715025357","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"现金(100)","CO_PayCode":"XJZF","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":2,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-04 17:15:02","dataCount":null}
         */

        private String Cashier;
        private String CheckoutDate;
        private String OrderCode;
        private Object ActivityName;
        private Object ActivityValue;
        private double IntegralAdd;
        private double IntegralDeduct;
        private double YSMoney;
        private double SSMoney;
        private double ZLMoney;
        private String VCH_Card;
        private String VIP_Name;
        private double VCH_Money;
        private double VCH_Point;
        private Object GoodsList;
        private Object ServiceList;
        private Object GiftList;
        private double ConsumeMoney;
        private double ConsumeTotal;
        private double GoodsMoney;
        private double HM_Money;
        private Object HM_Name;
        private double Discount;
        private double GiveMoney;
        private double RechargeTotal;
        private int ExchangeNum;
        private Object TS_UpdateTime;
        private Object TimeLong;
        private Object TS_MinusTimes;
        private Object TS_SurplusTimes;
        private String VIP_FaceNumber;
        private String PayInfo;
        private int VCH_Fee;
        private double InitialAmount;
        private double InitialIntegral;
        private String Remark;
        private String VIPAddress;
        private String EMName;
        private Object IndividualHand;
        private OilsObjBean OilsObj;

        public String getCashier() {
            return Cashier;
        }

        public void setCashier(String Cashier) {
            this.Cashier = Cashier;
        }

        public String getCheckoutDate() {
            return CheckoutDate;
        }

        public void setCheckoutDate(String CheckoutDate) {
            this.CheckoutDate = CheckoutDate;
        }

        public String getOrderCode() {
            return OrderCode;
        }

        public void setOrderCode(String OrderCode) {
            this.OrderCode = OrderCode;
        }

        public Object getActivityName() {
            return ActivityName;
        }

        public void setActivityName(Object ActivityName) {
            this.ActivityName = ActivityName;
        }

        public Object getActivityValue() {
            return ActivityValue;
        }

        public void setActivityValue(Object ActivityValue) {
            this.ActivityValue = ActivityValue;
        }

        public double getIntegralAdd() {
            return IntegralAdd;
        }

        public void setIntegralAdd(double IntegralAdd) {
            this.IntegralAdd = IntegralAdd;
        }

        public double getIntegralDeduct() {
            return IntegralDeduct;
        }

        public void setIntegralDeduct(double IntegralDeduct) {
            this.IntegralDeduct = IntegralDeduct;
        }

        public double getYSMoney() {
            return YSMoney;
        }

        public void setYSMoney(double YSMoney) {
            this.YSMoney = YSMoney;
        }

        public double getSSMoney() {
            return SSMoney;
        }

        public void setSSMoney(double SSMoney) {
            this.SSMoney = SSMoney;
        }

        public double getZLMoney() {
            return ZLMoney;
        }

        public void setZLMoney(double ZLMoney) {
            this.ZLMoney = ZLMoney;
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

        public Object getGoodsList() {
            return GoodsList;
        }

        public void setGoodsList(Object GoodsList) {
            this.GoodsList = GoodsList;
        }

        public Object getServiceList() {
            return ServiceList;
        }

        public void setServiceList(Object ServiceList) {
            this.ServiceList = ServiceList;
        }

        public Object getGiftList() {
            return GiftList;
        }

        public void setGiftList(Object GiftList) {
            this.GiftList = GiftList;
        }

        public double getConsumeMoney() {
            return ConsumeMoney;
        }

        public void setConsumeMoney(double ConsumeMoney) {
            this.ConsumeMoney = ConsumeMoney;
        }

        public double getConsumeTotal() {
            return ConsumeTotal;
        }

        public void setConsumeTotal(double ConsumeTotal) {
            this.ConsumeTotal = ConsumeTotal;
        }

        public double getGoodsMoney() {
            return GoodsMoney;
        }

        public void setGoodsMoney(double GoodsMoney) {
            this.GoodsMoney = GoodsMoney;
        }

        public double getHM_Money() {
            return HM_Money;
        }

        public void setHM_Money(double HM_Money) {
            this.HM_Money = HM_Money;
        }

        public Object getHM_Name() {
            return HM_Name;
        }

        public void setHM_Name(Object HM_Name) {
            this.HM_Name = HM_Name;
        }

        public double getDiscount() {
            return Discount;
        }

        public void setDiscount(double Discount) {
            this.Discount = Discount;
        }

        public double getGiveMoney() {
            return GiveMoney;
        }

        public void setGiveMoney(double GiveMoney) {
            this.GiveMoney = GiveMoney;
        }

        public double getRechargeTotal() {
            return RechargeTotal;
        }

        public void setRechargeTotal(double RechargeTotal) {
            this.RechargeTotal = RechargeTotal;
        }

        public int getExchangeNum() {
            return ExchangeNum;
        }

        public void setExchangeNum(int ExchangeNum) {
            this.ExchangeNum = ExchangeNum;
        }

        public Object getTS_UpdateTime() {
            return TS_UpdateTime;
        }

        public void setTS_UpdateTime(Object TS_UpdateTime) {
            this.TS_UpdateTime = TS_UpdateTime;
        }

        public Object getTimeLong() {
            return TimeLong;
        }

        public void setTimeLong(Object TimeLong) {
            this.TimeLong = TimeLong;
        }

        public Object getTS_MinusTimes() {
            return TS_MinusTimes;
        }

        public void setTS_MinusTimes(Object TS_MinusTimes) {
            this.TS_MinusTimes = TS_MinusTimes;
        }

        public Object getTS_SurplusTimes() {
            return TS_SurplusTimes;
        }

        public void setTS_SurplusTimes(Object TS_SurplusTimes) {
            this.TS_SurplusTimes = TS_SurplusTimes;
        }

        public String getVIP_FaceNumber() {
            return VIP_FaceNumber;
        }

        public void setVIP_FaceNumber(String VIP_FaceNumber) {
            this.VIP_FaceNumber = VIP_FaceNumber;
        }

        public String getPayInfo() {
            return PayInfo;
        }

        public void setPayInfo(String PayInfo) {
            this.PayInfo = PayInfo;
        }

        public int getVCH_Fee() {
            return VCH_Fee;
        }

        public void setVCH_Fee(int VCH_Fee) {
            this.VCH_Fee = VCH_Fee;
        }

        public double getInitialAmount() {
            return InitialAmount;
        }

        public void setInitialAmount(double InitialAmount) {
            this.InitialAmount = InitialAmount;
        }

        public double getInitialIntegral() {
            return InitialIntegral;
        }

        public void setInitialIntegral(double InitialIntegral) {
            this.InitialIntegral = InitialIntegral;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getVIPAddress() {
            return VIPAddress;
        }

        public void setVIPAddress(String VIPAddress) {
            this.VIPAddress = VIPAddress;
        }

        public String getEMName() {
            return EMName;
        }

        public void setEMName(String EMName) {
            this.EMName = EMName;
        }

        public Object getIndividualHand() {
            return IndividualHand;
        }

        public void setIndividualHand(Object IndividualHand) {
            this.IndividualHand = IndividualHand;
        }

        public OilsObjBean getOilsObj() {
            return OilsObj;
        }

        public void setOilsObj(OilsObjBean OilsObj) {
            this.OilsObj = OilsObj;
        }

        public static class OilsObjBean implements Serializable{
            /**
             * GID : 2b94b68d-b00b-4138-a662-b4bf31030297
             * CO_OrderCode : JY1907041715025357
             * OM_GID : 284a406b-ccd5-43e1-90ed-1217764134b0
             * OM_Name : 646
             * CO_Number : 14.29
             * CO_Price : 7.0
             * CO_OrderAmount : 100.0
             * VIP_GID : 198b3c1a-15f9-4d49-94c3-18b9c4dcc90c
             * VIP_Card : 8846546616
             * VIP_Name : sada541
             * VIP_Phone : 18265665626
             * CO_InPrice : null
             * CO_SSMoney : 100.0
             * CO_ZLMoney : 0.0
             * VIP_FaceNumber : 65+562223
             * VIP_Balance : 0.0
             * CO_Integral : 12.5
             * CO_PayWay : 现金(100)
             * CO_PayCode : XJZF
             * CO_Identifying : 已完成
             * CO_IdentifyingState : 99
             * CO_Device : 2
             * CO_Creator : 销售001
             * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * SM_Name : jll
             * CO_CreateTime : 2019-07-04 17:15:02
             * dataCount : null
             */

            private String GID;
            private String CO_OrderCode;
            private String OM_GID;
            private String OM_Name;
            private double CO_Number;
            private double CO_Price;
            private double CO_OrderAmount;
            private String VIP_GID;
            private String VIP_Card;
            private String VIP_Name;
            private String VIP_Phone;
            private Object CO_InPrice;
            private double CO_SSMoney;
            private double CO_ZLMoney;
            private String VIP_FaceNumber;
            private double VIP_Balance;
            private double CO_Integral;
            private String CO_PayWay;
            private String CO_PayCode;
            private String CO_Identifying;
            private int CO_IdentifyingState;
            private int CO_Device;
            private String CO_Creator;
            private String CY_GID;
            private String SM_GID;
            private String SM_Name;
            private String CO_CreateTime;
            private Object dataCount;

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getCO_OrderCode() {
                return CO_OrderCode;
            }

            public void setCO_OrderCode(String CO_OrderCode) {
                this.CO_OrderCode = CO_OrderCode;
            }

            public String getOM_GID() {
                return OM_GID;
            }

            public void setOM_GID(String OM_GID) {
                this.OM_GID = OM_GID;
            }

            public String getOM_Name() {
                return OM_Name;
            }

            public void setOM_Name(String OM_Name) {
                this.OM_Name = OM_Name;
            }

            public double getCO_Number() {
                return CO_Number;
            }

            public void setCO_Number(double CO_Number) {
                this.CO_Number = CO_Number;
            }

            public double getCO_Price() {
                return CO_Price;
            }

            public void setCO_Price(double CO_Price) {
                this.CO_Price = CO_Price;
            }

            public double getCO_OrderAmount() {
                return CO_OrderAmount;
            }

            public void setCO_OrderAmount(double CO_OrderAmount) {
                this.CO_OrderAmount = CO_OrderAmount;
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

            public Object getCO_InPrice() {
                return CO_InPrice;
            }

            public void setCO_InPrice(Object CO_InPrice) {
                this.CO_InPrice = CO_InPrice;
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

            public String getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(String VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public double getVIP_Balance() {
                return VIP_Balance;
            }

            public void setVIP_Balance(double VIP_Balance) {
                this.VIP_Balance = VIP_Balance;
            }

            public double getCO_Integral() {
                return CO_Integral;
            }

            public void setCO_Integral(double CO_Integral) {
                this.CO_Integral = CO_Integral;
            }

            public String getCO_PayWay() {
                return CO_PayWay;
            }

            public void setCO_PayWay(String CO_PayWay) {
                this.CO_PayWay = CO_PayWay;
            }

            public String getCO_PayCode() {
                return CO_PayCode;
            }

            public void setCO_PayCode(String CO_PayCode) {
                this.CO_PayCode = CO_PayCode;
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

            public int getCO_Device() {
                return CO_Device;
            }

            public void setCO_Device(int CO_Device) {
                this.CO_Device = CO_Device;
            }

            public String getCO_Creator() {
                return CO_Creator;
            }

            public void setCO_Creator(String CO_Creator) {
                this.CO_Creator = CO_Creator;
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

            public String getCO_CreateTime() {
                return CO_CreateTime;
            }

            public void setCO_CreateTime(String CO_CreateTime) {
                this.CO_CreateTime = CO_CreateTime;
            }

            public Object getDataCount() {
                return dataCount;
            }

            public void setDataCount(Object dataCount) {
                this.dataCount = dataCount;
            }
        }
    }
}
