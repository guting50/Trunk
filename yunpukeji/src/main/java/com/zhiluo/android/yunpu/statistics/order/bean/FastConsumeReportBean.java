package com.zhiluo.android.yunpu.statistics.order.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/11 14:38.
 * 邮箱：137615198@qq.com
 */

public class FastConsumeReportBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":3,"PageIndex":1,"DataList":[{"GoodsOrderDetailList":null,"Goods":null,"CC_GID":null,"EM_GIDList":null,"PayTypeList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"IS_Sms":false,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"GID":"8cf1b4db-b41c-4ca4-971c-93b135a2c786","VIP_GID":"7754f7e8-60f0-4d84-a94d-5dfa9e2719cd","VIP_Card":"852","VIP_Name":"hvbs","VIP_Phone":"15855424548","VIP_FaceNumber":"","CO_OrderCode":"KS1709111346032697","CO_ConsumeWay":"现金(52.00)","CO_WayCode":"XJZF","CO_Monetary":65,"CO_TotalPrice":52,"CO_Integral":26,"CO_BalanceCard":100,"CO_Type":"快速消费","CO_TypeCode":"KSXF","CO_CommissionAmount":null,"CO_Remark":null,"CO_Creator":"137615198@qq.com","CO_UpdateTime":"2017-09-11 13:46:05","CY_GID":null,"SM_GID":"fbec9c01-c91f-4041-b675-0cf574996578","SM_Name":"默认店铺","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"正常","CO_IdentifyingState":2,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":1},{"GoodsOrderDetailList":null,"Goods":null,"CC_GID":null,"EM_GIDList":null,"PayTypeList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"IS_Sms":false,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"GID":"ea99d446-20f9-44f1-93fd-bbb4152ef094","VIP_GID":"7754f7e8-60f0-4d84-a94d-5dfa9e2719cd","VIP_Card":"852","VIP_Name":"hvbs","VIP_Phone":"15855424548","VIP_FaceNumber":"","CO_OrderCode":"KS1709111345277580","CO_ConsumeWay":"现金(25.60)","CO_WayCode":"XJZF","CO_Monetary":32,"CO_TotalPrice":25.6,"CO_Integral":12.8,"CO_BalanceCard":100,"CO_Type":"快速消费","CO_TypeCode":"KSXF","CO_CommissionAmount":null,"CO_Remark":null,"CO_Creator":"137615198@qq.com","CO_UpdateTime":"2017-09-11 13:45:30","CY_GID":null,"SM_GID":"fbec9c01-c91f-4041-b675-0cf574996578","SM_Name":"默认店铺","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"正常","CO_IdentifyingState":2,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":1},{"GoodsOrderDetailList":null,"Goods":null,"CC_GID":null,"EM_GIDList":null,"PayTypeList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"IS_Sms":false,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"GID":"21df03a4-ba08-4251-9a1a-e80fa86bbae8","VIP_GID":"0f4365d6-ae3a-4f06-ab09-b5106cc3ec7e","VIP_Card":"4587","VIP_Name":"陈建州","VIP_Phone":"15688458484","VIP_FaceNumber":"","CO_OrderCode":"KS1709111344265354","CO_ConsumeWay":"现金(17.50)","CO_WayCode":"XJZF","CO_Monetary":25,"CO_TotalPrice":17.5,"CO_Integral":17.5,"CO_BalanceCard":289,"CO_Type":"快速消费","CO_TypeCode":"KSXF","CO_CommissionAmount":null,"CO_Remark":null,"CO_Creator":"137615198@qq.com","CO_UpdateTime":"2017-09-11 13:44:29","CY_GID":null,"SM_GID":"fbec9c01-c91f-4041-b675-0cf574996578","SM_Name":"默认店铺","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"正常","CO_IdentifyingState":2,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":1}],"StatisticsInfo":{"Monetary":122,"DiscountMoney":95.1,"AllNumber":3}}
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
         * PageTotal : 1
         * PageSize : 20
         * DataCount : 3
         * PageIndex : 1
         * DataList : [{"GoodsOrderDetailList":null,"Goods":null,"CC_GID":null,"EM_GIDList":null,"PayTypeList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"IS_Sms":false,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"GID":"8cf1b4db-b41c-4ca4-971c-93b135a2c786","VIP_GID":"7754f7e8-60f0-4d84-a94d-5dfa9e2719cd","VIP_Card":"852","VIP_Name":"hvbs","VIP_Phone":"15855424548","VIP_FaceNumber":"","CO_OrderCode":"KS1709111346032697","CO_ConsumeWay":"现金(52.00)","CO_WayCode":"XJZF","CO_Monetary":65,"CO_TotalPrice":52,"CO_Integral":26,"CO_BalanceCard":100,"CO_Type":"快速消费","CO_TypeCode":"KSXF","CO_CommissionAmount":null,"CO_Remark":null,"CO_Creator":"137615198@qq.com","CO_UpdateTime":"2017-09-11 13:46:05","CY_GID":null,"SM_GID":"fbec9c01-c91f-4041-b675-0cf574996578","SM_Name":"默认店铺","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"正常","CO_IdentifyingState":2,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":1},{"GoodsOrderDetailList":null,"Goods":null,"CC_GID":null,"EM_GIDList":null,"PayTypeList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"IS_Sms":false,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"GID":"ea99d446-20f9-44f1-93fd-bbb4152ef094","VIP_GID":"7754f7e8-60f0-4d84-a94d-5dfa9e2719cd","VIP_Card":"852","VIP_Name":"hvbs","VIP_Phone":"15855424548","VIP_FaceNumber":"","CO_OrderCode":"KS1709111345277580","CO_ConsumeWay":"现金(25.60)","CO_WayCode":"XJZF","CO_Monetary":32,"CO_TotalPrice":25.6,"CO_Integral":12.8,"CO_BalanceCard":100,"CO_Type":"快速消费","CO_TypeCode":"KSXF","CO_CommissionAmount":null,"CO_Remark":null,"CO_Creator":"137615198@qq.com","CO_UpdateTime":"2017-09-11 13:45:30","CY_GID":null,"SM_GID":"fbec9c01-c91f-4041-b675-0cf574996578","SM_Name":"默认店铺","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"正常","CO_IdentifyingState":2,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":1},{"GoodsOrderDetailList":null,"Goods":null,"CC_GID":null,"EM_GIDList":null,"PayTypeList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"IS_Sms":false,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"GID":"21df03a4-ba08-4251-9a1a-e80fa86bbae8","VIP_GID":"0f4365d6-ae3a-4f06-ab09-b5106cc3ec7e","VIP_Card":"4587","VIP_Name":"陈建州","VIP_Phone":"15688458484","VIP_FaceNumber":"","CO_OrderCode":"KS1709111344265354","CO_ConsumeWay":"现金(17.50)","CO_WayCode":"XJZF","CO_Monetary":25,"CO_TotalPrice":17.5,"CO_Integral":17.5,"CO_BalanceCard":289,"CO_Type":"快速消费","CO_TypeCode":"KSXF","CO_CommissionAmount":null,"CO_Remark":null,"CO_Creator":"137615198@qq.com","CO_UpdateTime":"2017-09-11 13:44:29","CY_GID":null,"SM_GID":"fbec9c01-c91f-4041-b675-0cf574996578","SM_Name":"默认店铺","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"正常","CO_IdentifyingState":2,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":1}]
         * StatisticsInfo : {"Monetary":122,"DiscountMoney":95.1,"AllNumber":3}
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private StatisticsInfoBean StatisticsInfo;
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

        public StatisticsInfoBean getStatisticsInfo() {
            return StatisticsInfo;
        }

        public void setStatisticsInfo(StatisticsInfoBean StatisticsInfo) {
            this.StatisticsInfo = StatisticsInfo;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class StatisticsInfoBean {
            /**
             * Monetary : 122.0
             * DiscountMoney : 95.1
             * AllNumber : 3
             */

            private double Monetary;
            private double DiscountMoney;
            private int AllNumber;

            public double getMonetary() {
                return Monetary;
            }

            public void setMonetary(double Monetary) {
                this.Monetary = Monetary;
            }

            public double getDiscountMoney() {
                return DiscountMoney;
            }

            public void setDiscountMoney(double DiscountMoney) {
                this.DiscountMoney = DiscountMoney;
            }

            public int getAllNumber() {
                return AllNumber;
            }

            public void setAllNumber(int AllNumber) {
                this.AllNumber = AllNumber;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * GoodsOrderDetailList : null
             * Goods : null
             * CC_GID : null
             * EM_GIDList : null
             * PayTypeList : null
             * PayPoint : 0.0
             * DisMoney : 0.0
             * VCH_Money : 0.0
             * VCH_Point : 0.0
             * IS_Sms : false
             * CO_SSMoney : 0.0
             * CO_ZLMoney : 0.0
             * CO_ActivityName : null
             * CO_ActivityValue : null
             * CO_Discount : null
             * GID : 8cf1b4db-b41c-4ca4-971c-93b135a2c786
             * VIP_GID : 7754f7e8-60f0-4d84-a94d-5dfa9e2719cd
             * VIP_Card : 852
             * VIP_Name : hvbs
             * VIP_Phone : 15855424548
             * VIP_FaceNumber :
             * CO_OrderCode : KS1709111346032697
             * CO_ConsumeWay : 现金(52.00)
             * CO_WayCode : XJZF
             * CO_Monetary : 65.0
             * CO_TotalPrice : 52.0
             * CO_Integral : 26.0
             * CO_BalanceCard : 100.0
             * CO_Type : 快速消费
             * CO_TypeCode : KSXF
             * CO_CommissionAmount : null
             * CO_Remark : null
             * CO_Creator : 137615198@qq.com
             * CO_UpdateTime : 2017-09-11 13:46:05
             * CY_GID : null
             * SM_GID : fbec9c01-c91f-4041-b675-0cf574996578
             * SM_Name : 默认店铺
             * SM_Contacter : null
             * SM_Phone : null
             * SM_Addr : null
             * CO_Identifying : 正常
             * CO_IdentifyingState : 2
             * CO_OrderState : null
             * CO_StateValue : null
             * CO_Device : 1
             */

            private Object GoodsOrderDetailList;
            private Object Goods;
            private Object CC_GID;
            private Object EM_GIDList;
            private Object PayTypeList;
            private double PayPoint;
            private double DisMoney;
            private double VCH_Money;
            private double VCH_Point;
            private boolean IS_Sms;
            private double CO_SSMoney;
            private double CO_ZLMoney;
            private Object CO_ActivityName;
            private Object CO_ActivityValue;
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
            private double CO_BalanceCard;
            private String CO_Type;
            private String CO_TypeCode;
            private Object CO_CommissionAmount;
            private String CO_Remark;
            private String CO_Creator;
            private String CO_UpdateTime;
            private Object CY_GID;
            private String SM_GID;
            private String SM_Name;
            private Object SM_Contacter;
            private Object SM_Phone;
            private Object SM_Addr;
            private String CO_Identifying;
            private int CO_IdentifyingState;
            private Object CO_OrderState;
            private Object CO_StateValue;
            private int CO_Device;
            private boolean visiable;

            public boolean isVisiable() {
                return visiable;
            }

            public void setVisiable(boolean visiable) {
                this.visiable = visiable;
            }

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

            public Object getCC_GID() {
                return CC_GID;
            }

            public void setCC_GID(Object CC_GID) {
                this.CC_GID = CC_GID;
            }

            public Object getEM_GIDList() {
                return EM_GIDList;
            }

            public void setEM_GIDList(Object EM_GIDList) {
                this.EM_GIDList = EM_GIDList;
            }

            public Object getPayTypeList() {
                return PayTypeList;
            }

            public void setPayTypeList(Object PayTypeList) {
                this.PayTypeList = PayTypeList;
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

            public boolean isIS_Sms() {
                return IS_Sms;
            }

            public void setIS_Sms(boolean IS_Sms) {
                this.IS_Sms = IS_Sms;
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

            public Object getCO_ActivityValue() {
                return CO_ActivityValue;
            }

            public void setCO_ActivityValue(Object CO_ActivityValue) {
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

            public double getCO_BalanceCard() {
                return CO_BalanceCard;
            }

            public void setCO_BalanceCard(double CO_BalanceCard) {
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

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
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

            public Object getSM_Contacter() {
                return SM_Contacter;
            }

            public void setSM_Contacter(Object SM_Contacter) {
                this.SM_Contacter = SM_Contacter;
            }

            public Object getSM_Phone() {
                return SM_Phone;
            }

            public void setSM_Phone(Object SM_Phone) {
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
        }
    }
}
