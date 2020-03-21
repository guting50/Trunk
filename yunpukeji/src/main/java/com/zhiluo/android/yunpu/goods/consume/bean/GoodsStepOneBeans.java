package com.zhiluo.android.yunpu.goods.consume.bean;

import java.util.List;

/**
 * Created by ${YSL} on 2018-03-26.
 */

public class GoodsStepOneBeans {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GoodsOrderDetailList":[{"GOD_State":0,"GOD_LastNumber":null,"EM_GIDList":null,"GOD_EMGID":null,"SumPrice":null,"GID":"fa7f826e-c505-4021-a030-42d85f9ec136","CO_GID":"0355e6d4-6bcc-4185-a90c-3be8ef6197f8","PM_GID":"b1c0a8d2-e6d4-418d-9745-a38ff9f67385","PM_Code":"2221","PT_Name":"餐饮","PT_GID":"44de0108-9956-4e23-99e1-fe72d1d31b5a","PM_Name":"豆浆","PM_SimpleCode":"DJ","PM_Metering":"杯","PM_UnitPrice":3,"PM_Detail":null,"PM_Description":"","PM_Modle":"大杯","PM_BigImg":null,"PM_Brand":"成都早餐","PM_Number":1,"PM_FixedIntegralValue":0,"PM_Discount":0.6666666666666666,"GOD_DiscountPrice":2,"GOD_Integral":0,"GOD_Creator":"18381306468","GOD_UpdateTime":"2018-03-26 18:17:45","CY_GID":"d9e20c93-b7b3-465c-93b2-8ba0729d80d7","GOD_EMName":""},{"GOD_State":0,"GOD_LastNumber":null,"EM_GIDList":null,"GOD_EMGID":null,"SumPrice":null,"GID":"df105337-112a-4f56-a6b7-55a90c99caab","CO_GID":"0355e6d4-6bcc-4185-a90c-3be8ef6197f8","PM_GID":"9b1de3b0-aa2d-4166-9e01-c3553c2a259b","PM_Code":"2220","PT_Name":"餐饮","PT_GID":"44de0108-9956-4e23-99e1-fe72d1d31b5a","PM_Name":"豆浆","PM_SimpleCode":"DJ_S","PM_Metering":"杯","PM_UnitPrice":1,"PM_Detail":null,"PM_Description":"","PM_Modle":"小杯","PM_BigImg":null,"PM_Brand":"成都早点","PM_Number":1,"PM_FixedIntegralValue":1,"PM_Discount":1,"GOD_DiscountPrice":1,"GOD_Integral":1,"GOD_Creator":"18381306468","GOD_UpdateTime":"2018-03-26 18:17:45","CY_GID":"d9e20c93-b7b3-465c-93b2-8ba0729d80d7","GOD_EMName":""},{"GOD_State":0,"GOD_LastNumber":null,"EM_GIDList":null,"GOD_EMGID":null,"SumPrice":null,"GID":"72c8b0e1-7a81-4a94-a03b-a9315f4ff854","CO_GID":"0355e6d4-6bcc-4185-a90c-3be8ef6197f8","PM_GID":"9127f970-6b61-4eaf-b571-f8db8584521d","PM_Code":"0000","PT_Name":"营养品","PT_GID":"a653eaaa-ce45-4632-836d-19f5e85a5c43","PM_Name":"鸡蛋","PM_SimpleCode":"B_JD","PM_Metering":"个","PM_UnitPrice":1,"PM_Detail":null,"PM_Description":"","PM_Modle":"笨鸡蛋","PM_BigImg":null,"PM_Brand":"成都早餐","PM_Number":1,"PM_FixedIntegralValue":0,"PM_Discount":0.8,"GOD_DiscountPrice":0.8,"GOD_Integral":0,"GOD_Creator":"18381306468","GOD_UpdateTime":"2018-03-26 18:17:45","CY_GID":"d9e20c93-b7b3-465c-93b2-8ba0729d80d7","GOD_EMName":""}],"Goods":[{"EM_GIDList":null,"PM_GID":"b1c0a8d2-e6d4-418d-9745-a38ff9f67385","PM_Name":"豆浆","PM_Number":1},{"EM_GIDList":null,"PM_GID":"9b1de3b0-aa2d-4166-9e01-c3553c2a259b","PM_Name":"豆浆","PM_Number":1},{"EM_GIDList":null,"PM_GID":"9127f970-6b61-4eaf-b571-f8db8584521d","PM_Name":"鸡蛋","PM_Number":1}],"CC_GID":"","EM_GIDList":null,"PayPoint":0,"DisMoney":3.8,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":"","CO_Discount":null,"GID":"0355e6d4-6bcc-4185-a90c-3be8ef6197f8","VIP_GID":"cc011142-bcf1-4b71-851b-4e15be83abbf","VIP_Card":"110","VIP_Name":"诸葛亮","VIP_Phone":"18381306462","VIP_FaceNumber":"111","CO_OrderCode":"SP1803261817154297","CO_ConsumeWay":"","CO_WayCode":"","CO_Monetary":5,"CO_TotalPrice":3.8,"CO_Integral":1,"CO_BalanceCard":null,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":0,"CO_Remark":"","CO_Creator":"18381306468","CO_UpdateTime":"2018-03-26 18:17:45","CY_GID":"d9e20c93-b7b3-465c-93b2-8ba0729d80d7","SM_GID":"1393ac39-84e9-464f-93c3-e47865396555","SM_Name":"店铺一","SM_Contacter":"张三","SM_Phone":"18381306468","SM_Addr":null,"CO_Identifying":"待支付","CO_IdentifyingState":-1,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":1,"CO_ActivityType":null,"CO_ActivityMoney":null}
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
         * GoodsOrderDetailList : [{"GOD_State":0,"GOD_LastNumber":null,"EM_GIDList":null,"GOD_EMGID":null,"SumPrice":null,"GID":"fa7f826e-c505-4021-a030-42d85f9ec136","CO_GID":"0355e6d4-6bcc-4185-a90c-3be8ef6197f8","PM_GID":"b1c0a8d2-e6d4-418d-9745-a38ff9f67385","PM_Code":"2221","PT_Name":"餐饮","PT_GID":"44de0108-9956-4e23-99e1-fe72d1d31b5a","PM_Name":"豆浆","PM_SimpleCode":"DJ","PM_Metering":"杯","PM_UnitPrice":3,"PM_Detail":null,"PM_Description":"","PM_Modle":"大杯","PM_BigImg":null,"PM_Brand":"成都早餐","PM_Number":1,"PM_FixedIntegralValue":0,"PM_Discount":0.6666666666666666,"GOD_DiscountPrice":2,"GOD_Integral":0,"GOD_Creator":"18381306468","GOD_UpdateTime":"2018-03-26 18:17:45","CY_GID":"d9e20c93-b7b3-465c-93b2-8ba0729d80d7","GOD_EMName":""},{"GOD_State":0,"GOD_LastNumber":null,"EM_GIDList":null,"GOD_EMGID":null,"SumPrice":null,"GID":"df105337-112a-4f56-a6b7-55a90c99caab","CO_GID":"0355e6d4-6bcc-4185-a90c-3be8ef6197f8","PM_GID":"9b1de3b0-aa2d-4166-9e01-c3553c2a259b","PM_Code":"2220","PT_Name":"餐饮","PT_GID":"44de0108-9956-4e23-99e1-fe72d1d31b5a","PM_Name":"豆浆","PM_SimpleCode":"DJ_S","PM_Metering":"杯","PM_UnitPrice":1,"PM_Detail":null,"PM_Description":"","PM_Modle":"小杯","PM_BigImg":null,"PM_Brand":"成都早点","PM_Number":1,"PM_FixedIntegralValue":1,"PM_Discount":1,"GOD_DiscountPrice":1,"GOD_Integral":1,"GOD_Creator":"18381306468","GOD_UpdateTime":"2018-03-26 18:17:45","CY_GID":"d9e20c93-b7b3-465c-93b2-8ba0729d80d7","GOD_EMName":""},{"GOD_State":0,"GOD_LastNumber":null,"EM_GIDList":null,"GOD_EMGID":null,"SumPrice":null,"GID":"72c8b0e1-7a81-4a94-a03b-a9315f4ff854","CO_GID":"0355e6d4-6bcc-4185-a90c-3be8ef6197f8","PM_GID":"9127f970-6b61-4eaf-b571-f8db8584521d","PM_Code":"0000","PT_Name":"营养品","PT_GID":"a653eaaa-ce45-4632-836d-19f5e85a5c43","PM_Name":"鸡蛋","PM_SimpleCode":"B_JD","PM_Metering":"个","PM_UnitPrice":1,"PM_Detail":null,"PM_Description":"","PM_Modle":"笨鸡蛋","PM_BigImg":null,"PM_Brand":"成都早餐","PM_Number":1,"PM_FixedIntegralValue":0,"PM_Discount":0.8,"GOD_DiscountPrice":0.8,"GOD_Integral":0,"GOD_Creator":"18381306468","GOD_UpdateTime":"2018-03-26 18:17:45","CY_GID":"d9e20c93-b7b3-465c-93b2-8ba0729d80d7","GOD_EMName":""}]
         * Goods : [{"EM_GIDList":null,"PM_GID":"b1c0a8d2-e6d4-418d-9745-a38ff9f67385","PM_Name":"豆浆","PM_Number":1},{"EM_GIDList":null,"PM_GID":"9b1de3b0-aa2d-4166-9e01-c3553c2a259b","PM_Name":"豆浆","PM_Number":1},{"EM_GIDList":null,"PM_GID":"9127f970-6b61-4eaf-b571-f8db8584521d","PM_Name":"鸡蛋","PM_Number":1}]
         * CC_GID :
         * EM_GIDList : null
         * PayPoint : 0.0
         * DisMoney : 3.8
         * VCH_Money : 0.0
         * VCH_Point : 0.0
         * CO_EMName :
         * CO_EMGID : null
         * CO_SSMoney : 0.0
         * CO_ZLMoney : 0.0
         * CO_ActivityName : null
         * CO_ActivityValue :
         * CO_Discount : null
         * GID : 0355e6d4-6bcc-4185-a90c-3be8ef6197f8
         * VIP_GID : cc011142-bcf1-4b71-851b-4e15be83abbf
         * VIP_Card : 110
         * VIP_Name : 诸葛亮
         * VIP_Phone : 18381306462
         * VIP_FaceNumber : 111
         * CO_OrderCode : SP1803261817154297
         * CO_ConsumeWay :
         * CO_WayCode :
         * CO_Monetary : 5.0
         * CO_TotalPrice : 3.8
         * CO_Integral : 1.0
         * CO_BalanceCard : null
         * CO_Type : 商品消费
         * CO_TypeCode : SPXF
         * CO_CommissionAmount : 0.0
         * CO_Remark :
         * CO_Creator : 18381306468
         * CO_UpdateTime : 2018-03-26 18:17:45
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

        private String CC_GID;
        private Object EM_GIDList;
        private double PayPoint;
        private double DisMoney;
        private double VCH_Money;
        private double VCH_Point;
        private String CO_EMName;
        private Object CO_EMGID;
        private double CO_SSMoney;
        private double CO_ZLMoney;
        private Object CO_ActivityName;
        private String CO_ActivityValue;
        private Object CO_Discount;
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
        private double CO_CommissionAmount;
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
        private List<GoodsOrderDetailListBean> GoodsOrderDetailList;
        private List<GoodsBean> Goods;

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

        public String getCO_EMName() {
            return CO_EMName;
        }

        public void setCO_EMName(String CO_EMName) {
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

        public Object getCO_Discount() {
            return CO_Discount;
        }

        public void setCO_Discount(Object CO_Discount) {
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

        public double getCO_CommissionAmount() {
            return CO_CommissionAmount;
        }

        public void setCO_CommissionAmount(double CO_CommissionAmount) {
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

        public List<GoodsOrderDetailListBean> getGoodsOrderDetailList() {
            return GoodsOrderDetailList;
        }

        public void setGoodsOrderDetailList(List<GoodsOrderDetailListBean> GoodsOrderDetailList) {
            this.GoodsOrderDetailList = GoodsOrderDetailList;
        }

        public List<GoodsBean> getGoods() {
            return Goods;
        }

        public void setGoods(List<GoodsBean> Goods) {
            this.Goods = Goods;
        }

        public static class GoodsOrderDetailListBean {
            /**
             * GOD_State : 0
             * GOD_LastNumber : null
             * EM_GIDList : null
             * GOD_EMGID : null
             * SumPrice : null
             * GID : fa7f826e-c505-4021-a030-42d85f9ec136
             * CO_GID : 0355e6d4-6bcc-4185-a90c-3be8ef6197f8
             * PM_GID : b1c0a8d2-e6d4-418d-9745-a38ff9f67385
             * PM_Code : 2221
             * PT_Name : 餐饮
             * PT_GID : 44de0108-9956-4e23-99e1-fe72d1d31b5a
             * PM_Name : 豆浆
             * PM_SimpleCode : DJ
             * PM_Metering : 杯
             * PM_UnitPrice : 3.0
             * PM_Detail : null
             * PM_Description :
             * PM_Modle : 大杯
             * PM_BigImg : null
             * PM_Brand : 成都早餐
             * PM_Number : 1.0
             * PM_FixedIntegralValue : 0.0
             * PM_Discount : 0.6666666666666666
             * GOD_DiscountPrice : 2.0
             * GOD_Integral : 0.0
             * GOD_Creator : 18381306468
             * GOD_UpdateTime : 2018-03-26 18:17:45
             * CY_GID : d9e20c93-b7b3-465c-93b2-8ba0729d80d7
             * GOD_EMName :
             */

            private int GOD_State;
            private Object GOD_LastNumber;
            private Object EM_GIDList;
            private Object GOD_EMGID;
            private Object SumPrice;
            private String GID;
            private String CO_GID;
            private String PM_GID;
            private String PM_Code;
            private String PT_Name;
            private String PT_GID;
            private String PM_Name;
            private String PM_SimpleCode;
            private String PM_Metering;
            private double PM_UnitPrice;
            private Object PM_Detail;
            private String PM_Description;
            private String PM_Modle;
            private Object PM_BigImg;
            private String PM_Brand;
            private double PM_Number;
            private double PM_FixedIntegralValue;
            private double PM_Discount;
            private double GOD_DiscountPrice;
            private double GOD_Integral;
            private String GOD_Creator;
            private String GOD_UpdateTime;
            private String CY_GID;
            private String GOD_EMName;

            public int getGOD_State() {
                return GOD_State;
            }

            public void setGOD_State(int GOD_State) {
                this.GOD_State = GOD_State;
            }

            public Object getGOD_LastNumber() {
                return GOD_LastNumber;
            }

            public void setGOD_LastNumber(Object GOD_LastNumber) {
                this.GOD_LastNumber = GOD_LastNumber;
            }

            public Object getEM_GIDList() {
                return EM_GIDList;
            }

            public void setEM_GIDList(Object EM_GIDList) {
                this.EM_GIDList = EM_GIDList;
            }

            public Object getGOD_EMGID() {
                return GOD_EMGID;
            }

            public void setGOD_EMGID(Object GOD_EMGID) {
                this.GOD_EMGID = GOD_EMGID;
            }

            public Object getSumPrice() {
                return SumPrice;
            }

            public void setSumPrice(Object SumPrice) {
                this.SumPrice = SumPrice;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getCO_GID() {
                return CO_GID;
            }

            public void setCO_GID(String CO_GID) {
                this.CO_GID = CO_GID;
            }

            public String getPM_GID() {
                return PM_GID;
            }

            public void setPM_GID(String PM_GID) {
                this.PM_GID = PM_GID;
            }

            public String getPM_Code() {
                return PM_Code;
            }

            public void setPM_Code(String PM_Code) {
                this.PM_Code = PM_Code;
            }

            public String getPT_Name() {
                return PT_Name;
            }

            public void setPT_Name(String PT_Name) {
                this.PT_Name = PT_Name;
            }

            public String getPT_GID() {
                return PT_GID;
            }

            public void setPT_GID(String PT_GID) {
                this.PT_GID = PT_GID;
            }

            public String getPM_Name() {
                return PM_Name;
            }

            public void setPM_Name(String PM_Name) {
                this.PM_Name = PM_Name;
            }

            public String getPM_SimpleCode() {
                return PM_SimpleCode;
            }

            public void setPM_SimpleCode(String PM_SimpleCode) {
                this.PM_SimpleCode = PM_SimpleCode;
            }

            public String getPM_Metering() {
                return PM_Metering;
            }

            public void setPM_Metering(String PM_Metering) {
                this.PM_Metering = PM_Metering;
            }

            public double getPM_UnitPrice() {
                return PM_UnitPrice;
            }

            public void setPM_UnitPrice(double PM_UnitPrice) {
                this.PM_UnitPrice = PM_UnitPrice;
            }

            public Object getPM_Detail() {
                return PM_Detail;
            }

            public void setPM_Detail(Object PM_Detail) {
                this.PM_Detail = PM_Detail;
            }

            public String getPM_Description() {
                return PM_Description;
            }

            public void setPM_Description(String PM_Description) {
                this.PM_Description = PM_Description;
            }

            public String getPM_Modle() {
                return PM_Modle;
            }

            public void setPM_Modle(String PM_Modle) {
                this.PM_Modle = PM_Modle;
            }

            public Object getPM_BigImg() {
                return PM_BigImg;
            }

            public void setPM_BigImg(Object PM_BigImg) {
                this.PM_BigImg = PM_BigImg;
            }

            public String getPM_Brand() {
                return PM_Brand;
            }

            public void setPM_Brand(String PM_Brand) {
                this.PM_Brand = PM_Brand;
            }

            public double getPM_Number() {
                return PM_Number;
            }

            public void setPM_Number(double PM_Number) {
                this.PM_Number = PM_Number;
            }

            public double getPM_FixedIntegralValue() {
                return PM_FixedIntegralValue;
            }

            public void setPM_FixedIntegralValue(double PM_FixedIntegralValue) {
                this.PM_FixedIntegralValue = PM_FixedIntegralValue;
            }

            public double getPM_Discount() {
                return PM_Discount;
            }

            public void setPM_Discount(double PM_Discount) {
                this.PM_Discount = PM_Discount;
            }

            public double getGOD_DiscountPrice() {
                return GOD_DiscountPrice;
            }

            public void setGOD_DiscountPrice(double GOD_DiscountPrice) {
                this.GOD_DiscountPrice = GOD_DiscountPrice;
            }

            public double getGOD_Integral() {
                return GOD_Integral;
            }

            public void setGOD_Integral(double GOD_Integral) {
                this.GOD_Integral = GOD_Integral;
            }

            public String getGOD_Creator() {
                return GOD_Creator;
            }

            public void setGOD_Creator(String GOD_Creator) {
                this.GOD_Creator = GOD_Creator;
            }

            public String getGOD_UpdateTime() {
                return GOD_UpdateTime;
            }

            public void setGOD_UpdateTime(String GOD_UpdateTime) {
                this.GOD_UpdateTime = GOD_UpdateTime;
            }

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getGOD_EMName() {
                return GOD_EMName;
            }

            public void setGOD_EMName(String GOD_EMName) {
                this.GOD_EMName = GOD_EMName;
            }
        }

        public static class GoodsBean {
            /**
             * EM_GIDList : null
             * PM_GID : b1c0a8d2-e6d4-418d-9745-a38ff9f67385
             * PM_Name : 豆浆
             * PM_Number : 1.0
             */

            private Object EM_GIDList;
            private String PM_GID;
            private String PM_Name;
            private double PM_Number;

            public Object getEM_GIDList() {
                return EM_GIDList;
            }

            public void setEM_GIDList(Object EM_GIDList) {
                this.EM_GIDList = EM_GIDList;
            }

            public String getPM_GID() {
                return PM_GID;
            }

            public void setPM_GID(String PM_GID) {
                this.PM_GID = PM_GID;
            }

            public String getPM_Name() {
                return PM_Name;
            }

            public void setPM_Name(String PM_Name) {
                this.PM_Name = PM_Name;
            }

            public double getPM_Number() {
                return PM_Number;
            }

            public void setPM_Number(double PM_Number) {
                this.PM_Number = PM_Number;
            }
        }
    }
}
