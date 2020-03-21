package com.zhiluo.android.yunpu.member.consume.bean;

import java.util.List;

/**
 * Created by ${YSL} on 2018-03-24.
 */

public class VipInflateStepOneBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"EM_GIDList":null,"VCH_Card":"123","ServeceList":[{"EM_GIDList":null,"PM_GID":"50f126d5-188f-4429-a675-de782afa3201","PM_Name":"<img src='/img/goods.png' style='height:16px;float:left;margin-top:2px;' />快餐盒","PM_Number":2}],"MC_IdentifyingState":-1,"MC_EMName":"","MC_YSMoney":0,"MC_SSMoney":0,"MC_ZLMoney":0,"SG_Name":null,"MC_Discount":null,"MC_ServiceName":null,"MC_Price":null,"GID":"293afde4-2ab2-42ad-b349-a692044d57a0","SM_GID":"1393ac39-84e9-464f-93c3-e47865396555","SG_GID":null,"VIP_GID":"4879305f-8170-48a9-8af5-92b62fccf04a","MC_Order":"CC1803241431048978","MC_Way":null,"MC_Code":null,"MC_Amount":20,"MC_AmountDis":20,"MC_Number":2,"MC_Integral":20,"MC_ChargeTime":"2018-03-24 14:31:26","MC_Declare":null,"MC_Identifying":"待支付","MC_Remark":"","MC_Creator":"18381306468","MC_UpdateTime":null,"CY_GID":"d9e20c93-b7b3-465c-93b2-8ba0729d80d7","MC_Device":1}
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
         * VCH_Card : 123
         * ServeceList : [{"EM_GIDList":null,"PM_GID":"50f126d5-188f-4429-a675-de782afa3201","PM_Name":"<img src='/img/goods.png' style='height:16px;float:left;margin-top:2px;' />快餐盒","PM_Number":2}]
         * MC_IdentifyingState : -1
         * MC_EMName :
         * MC_YSMoney : 0.0
         * MC_SSMoney : 0.0
         * MC_ZLMoney : 0.0
         * SG_Name : null
         * MC_Discount : null
         * MC_ServiceName : null
         * MC_Price : null
         * GID : 293afde4-2ab2-42ad-b349-a692044d57a0
         * SM_GID : 1393ac39-84e9-464f-93c3-e47865396555
         * SG_GID : null
         * VIP_GID : 4879305f-8170-48a9-8af5-92b62fccf04a
         * MC_Order : CC1803241431048978
         * MC_Way : null
         * MC_Code : null
         * MC_Amount : 20.0
         * MC_AmountDis : 20.0
         * MC_Number : 2
         * MC_Integral : 20.0
         * MC_ChargeTime : 2018-03-24 14:31:26
         * MC_Declare : null
         * MC_Identifying : 待支付
         * MC_Remark :
         * MC_Creator : 18381306468
         * MC_UpdateTime : null
         * CY_GID : d9e20c93-b7b3-465c-93b2-8ba0729d80d7
         * MC_Device : 1
         */

        private Object EM_GIDList;
        private String VCH_Card;
        private int MC_IdentifyingState;
        private String MC_EMName;
        private double MC_YSMoney;
        private double MC_SSMoney;
        private double MC_ZLMoney;
        private Object SG_Name;
        private Object MC_Discount;
        private Object MC_ServiceName;
        private Object MC_Price;
        private String GID;
        private String SM_GID;
        private Object SG_GID;
        private String VIP_GID;
        private String MC_Order;
        private Object MC_Way;
        private Object MC_Code;
        private double MC_Amount;
        private double MC_AmountDis;
        private int MC_Number;
        private double MC_Integral;
        private String MC_ChargeTime;
        private Object MC_Declare;
        private String MC_Identifying;
        private String MC_Remark;
        private String MC_Creator;
        private Object MC_UpdateTime;
        private String CY_GID;
        private int MC_Device;
        private List<ServeceListBean> ServeceList;

        public Object getEM_GIDList() {
            return EM_GIDList;
        }

        public void setEM_GIDList(Object EM_GIDList) {
            this.EM_GIDList = EM_GIDList;
        }

        public String getVCH_Card() {
            return VCH_Card;
        }

        public void setVCH_Card(String VCH_Card) {
            this.VCH_Card = VCH_Card;
        }

        public int getMC_IdentifyingState() {
            return MC_IdentifyingState;
        }

        public void setMC_IdentifyingState(int MC_IdentifyingState) {
            this.MC_IdentifyingState = MC_IdentifyingState;
        }

        public String getMC_EMName() {
            return MC_EMName;
        }

        public void setMC_EMName(String MC_EMName) {
            this.MC_EMName = MC_EMName;
        }

        public double getMC_YSMoney() {
            return MC_YSMoney;
        }

        public void setMC_YSMoney(double MC_YSMoney) {
            this.MC_YSMoney = MC_YSMoney;
        }

        public double getMC_SSMoney() {
            return MC_SSMoney;
        }

        public void setMC_SSMoney(double MC_SSMoney) {
            this.MC_SSMoney = MC_SSMoney;
        }

        public double getMC_ZLMoney() {
            return MC_ZLMoney;
        }

        public void setMC_ZLMoney(double MC_ZLMoney) {
            this.MC_ZLMoney = MC_ZLMoney;
        }

        public Object getSG_Name() {
            return SG_Name;
        }

        public void setSG_Name(Object SG_Name) {
            this.SG_Name = SG_Name;
        }

        public Object getMC_Discount() {
            return MC_Discount;
        }

        public void setMC_Discount(Object MC_Discount) {
            this.MC_Discount = MC_Discount;
        }

        public Object getMC_ServiceName() {
            return MC_ServiceName;
        }

        public void setMC_ServiceName(Object MC_ServiceName) {
            this.MC_ServiceName = MC_ServiceName;
        }

        public Object getMC_Price() {
            return MC_Price;
        }

        public void setMC_Price(Object MC_Price) {
            this.MC_Price = MC_Price;
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

        public Object getSG_GID() {
            return SG_GID;
        }

        public void setSG_GID(Object SG_GID) {
            this.SG_GID = SG_GID;
        }

        public String getVIP_GID() {
            return VIP_GID;
        }

        public void setVIP_GID(String VIP_GID) {
            this.VIP_GID = VIP_GID;
        }

        public String getMC_Order() {
            return MC_Order;
        }

        public void setMC_Order(String MC_Order) {
            this.MC_Order = MC_Order;
        }

        public Object getMC_Way() {
            return MC_Way;
        }

        public void setMC_Way(Object MC_Way) {
            this.MC_Way = MC_Way;
        }

        public Object getMC_Code() {
            return MC_Code;
        }

        public void setMC_Code(Object MC_Code) {
            this.MC_Code = MC_Code;
        }

        public double getMC_Amount() {
            return MC_Amount;
        }

        public void setMC_Amount(double MC_Amount) {
            this.MC_Amount = MC_Amount;
        }

        public double getMC_AmountDis() {
            return MC_AmountDis;
        }

        public void setMC_AmountDis(double MC_AmountDis) {
            this.MC_AmountDis = MC_AmountDis;
        }

        public int getMC_Number() {
            return MC_Number;
        }

        public void setMC_Number(int MC_Number) {
            this.MC_Number = MC_Number;
        }

        public double getMC_Integral() {
            return MC_Integral;
        }

        public void setMC_Integral(double MC_Integral) {
            this.MC_Integral = MC_Integral;
        }

        public String getMC_ChargeTime() {
            return MC_ChargeTime;
        }

        public void setMC_ChargeTime(String MC_ChargeTime) {
            this.MC_ChargeTime = MC_ChargeTime;
        }

        public Object getMC_Declare() {
            return MC_Declare;
        }

        public void setMC_Declare(Object MC_Declare) {
            this.MC_Declare = MC_Declare;
        }

        public String getMC_Identifying() {
            return MC_Identifying;
        }

        public void setMC_Identifying(String MC_Identifying) {
            this.MC_Identifying = MC_Identifying;
        }

        public String getMC_Remark() {
            return MC_Remark;
        }

        public void setMC_Remark(String MC_Remark) {
            this.MC_Remark = MC_Remark;
        }

        public String getMC_Creator() {
            return MC_Creator;
        }

        public void setMC_Creator(String MC_Creator) {
            this.MC_Creator = MC_Creator;
        }

        public Object getMC_UpdateTime() {
            return MC_UpdateTime;
        }

        public void setMC_UpdateTime(Object MC_UpdateTime) {
            this.MC_UpdateTime = MC_UpdateTime;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public int getMC_Device() {
            return MC_Device;
        }

        public void setMC_Device(int MC_Device) {
            this.MC_Device = MC_Device;
        }

        public List<ServeceListBean> getServeceList() {
            return ServeceList;
        }

        public void setServeceList(List<ServeceListBean> ServeceList) {
            this.ServeceList = ServeceList;
        }

        public static class ServeceListBean {
            /**
             * EM_GIDList : null
             * PM_GID : 50f126d5-188f-4429-a675-de782afa3201
             * PM_Name : <img src='/img/goods.png' style='height:16px;float:left;margin-top:2px;' />快餐盒
             * PM_Number : 2.0
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
