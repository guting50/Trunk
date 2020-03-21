package com.zhiluo.android.yunpu.consume.bean;

import java.io.Serializable;

/**
 * 作者：罗咏哲 on 2018/1/2 15:19.
 * 邮箱：137615198@qq.com
 */

public class YHQBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GID":"387cdceb-4736-42ff-992f-615014baba4e","VIP_GID":null,"EC_GID":"4256942b-1e35-4c16-aebc-4ed488853b35","EC_ReddemCode":"5615B7BA788","VCR_IsForver":1,"VCR_StatrTime":null,"VCR_EndTime":null,"VCR_IsUse":0,"VCR_CreatorTime":"2019-01-09 10:25:00","SM_GID":"","CY_GID":"e6f9eab1-ebd0-4544-a246-d2e0f836d3f9","EC_DiscountType":1,"EC_UseType":1,"EC_Discount":20,"EC_Denomination":21,"EC_Name":"买包子送手机","EC_IsOverlay":0,"EC_GiftCondition":null,"SM_Name":null}
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
         * GID : 387cdceb-4736-42ff-992f-615014baba4e
         * VIP_GID : null
         * EC_GID : 4256942b-1e35-4c16-aebc-4ed488853b35
         * EC_ReddemCode : 5615B7BA788
         * VCR_IsForver : 1
         * VCR_StatrTime : null
         * VCR_EndTime : null
         * VCR_IsUse : 0
         * VCR_CreatorTime : 2019-01-09 10:25:00
         * SM_GID :
         * CY_GID : e6f9eab1-ebd0-4544-a246-d2e0f836d3f9
         * EC_DiscountType : 1
         * EC_UseType : 1
         * EC_Discount : 20.0
         * EC_Denomination : 21.0
         * EC_Name : 买包子送手机
         * EC_IsOverlay : 0
         * EC_GiftCondition : null
         * SM_Name : null
         */

        private String GID;
        private Object VIP_GID;
        private String EC_GID;
        private String EC_ReddemCode;
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
        private String EC_Name;
        private int EC_IsOverlay;
        private Object EC_GiftCondition;
        private Object SM_Name;
        private boolean isCheck;
        private double orderMoney;

        public double getOrderMoney() {
            return orderMoney;
        }

        public void setOrderMoney(double orderMoney) {
            this.orderMoney = orderMoney;
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

        public Object getVIP_GID() {
            return VIP_GID;
        }

        public void setVIP_GID(Object VIP_GID) {
            this.VIP_GID = VIP_GID;
        }

        public String getEC_GID() {
            return EC_GID;
        }

        public void setEC_GID(String EC_GID) {
            this.EC_GID = EC_GID;
        }

        public String getEC_ReddemCode() {
            return EC_ReddemCode;
        }

        public void setEC_ReddemCode(String EC_ReddemCode) {
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

        public String getEC_Name() {
            return EC_Name;
        }

        public void setEC_Name(String EC_Name) {
            this.EC_Name = EC_Name;
        }

        public int getEC_IsOverlay() {
            return EC_IsOverlay;
        }

        public void setEC_IsOverlay(int EC_IsOverlay) {
            this.EC_IsOverlay = EC_IsOverlay;
        }

        public Object getEC_GiftCondition() {
            return EC_GiftCondition;
        }

        public void setEC_GiftCondition(Object EC_GiftCondition) {
            this.EC_GiftCondition = EC_GiftCondition;
        }

        public Object getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(Object SM_Name) {
            this.SM_Name = SM_Name;
        }
    }
}
