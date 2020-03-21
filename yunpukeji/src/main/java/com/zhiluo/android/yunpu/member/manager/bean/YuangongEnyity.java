package com.zhiluo.android.yunpu.member.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${YSL} on 2018-04-08.
 */

public class YuangongEnyity implements Serializable{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"0c4e33d5-9781-484e-bff9-4e4009bea79f","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","CY_GID":null,"DM_GID":"206182ea-f3a6-4735-ae70-777ce3d39fdb","EM_Code":"110","EM_Name":"员工张三","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2018-04-08 10:28:11","EM_Creator":"18381306468","EM_Birthday":null,"SM_Name":"默认店铺","DM_Name":"前台","EM_TipCard":1,"EM_TipRecharge":1,"EM_TipChargeTime":1,"EM_TipGoodsConsume":1,"EM_TipFastConsume":1,"EM_TipTimesConsume":1,"EM_TipComboConsume":1},{"GID":"138291bd-ebdb-4f95-b1de-525375661913","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","CY_GID":null,"DM_GID":"9071a097-67fe-4a97-8954-c0893f01570b","EM_Code":"000","EM_Name":"李四","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2018-04-08 10:28:26","EM_Creator":"18381306468","EM_Birthday":null,"SM_Name":"默认店铺","DM_Name":"开发","EM_TipCard":0,"EM_TipRecharge":0,"EM_TipChargeTime":1,"EM_TipGoodsConsume":0,"EM_TipFastConsume":0,"EM_TipTimesConsume":1,"EM_TipComboConsume":0},{"GID":"50cd7c6a-7092-4237-bf86-4a95d5a4104f","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","CY_GID":null,"DM_GID":"4e97451d-c4b6-4cff-b233-4538b225d533","EM_Code":"202","EM_Name":"王五","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2018-04-08 10:28:38","EM_Creator":"18381306468","EM_Birthday":null,"SM_Name":"默认店铺","DM_Name":"客服","EM_TipCard":0,"EM_TipRecharge":0,"EM_TipChargeTime":0,"EM_TipGoodsConsume":1,"EM_TipFastConsume":0,"EM_TipTimesConsume":0,"EM_TipComboConsume":0},{"GID":"cc943da2-0d0d-4fe3-8614-21bc168e40f7","SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","CY_GID":null,"DM_GID":"206182ea-f3a6-4735-ae70-777ce3d39fdb","EM_Code":"","EM_Name":"1212","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2018-04-08 10:27:17","EM_Creator":"18381306468","EM_Birthday":null,"SM_Name":"默认店铺","DM_Name":"前台","EM_TipCard":0,"EM_TipRecharge":0,"EM_TipChargeTime":0,"EM_TipGoodsConsume":0,"EM_TipFastConsume":0,"EM_TipTimesConsume":0,"EM_TipComboConsume":0}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * GID : 0c4e33d5-9781-484e-bff9-4e4009bea79f
         * SM_GID : 966d986b-0caa-465b-bbe1-8bd871bf8a0a
         * CY_GID : null
         * DM_GID : 206182ea-f3a6-4735-ae70-777ce3d39fdb
         * EM_Code : 110
         * EM_Name : 员工张三
         * EM_Sex : 0
         * EM_Phone :
         * EM_Addr :
         * EM_Remark :
         * EM_UpdateTime : 2018-04-08 10:28:11
         * EM_Creator : 18381306468
         * EM_Birthday : null
         * SM_Name : 默认店铺
         * DM_Name : 前台
         * EM_TipCard : 1
         * EM_TipRecharge : 1
         * EM_TipChargeTime : 1
         * EM_TipGoodsConsume : 1
         * EM_TipFastConsume : 1
         * EM_TipTimesConsume : 1
         * EM_TipComboConsume : 1
         */

        private String GID;
        private String SM_GID;
        private Object CY_GID;
        private String DM_GID;
        private String EM_Code;
        private String EM_Name;
        private int EM_Sex;
        private String EM_Phone;
        private String EM_Addr;
        private String EM_Remark;
        private String EM_UpdateTime;
        private String EM_Creator;
        private Object EM_Birthday;
        private String SM_Name;
        private String DM_Name;
        private int EM_TipCard;
        private int EM_TipRecharge;
        private int EM_TipChargeTime;
        private int EM_TipGoodsConsume;
        private int EM_TipFastConsume;
        private int EM_TipTimesConsume;
        private int EM_TipComboConsume;

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

        public Object getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(Object CY_GID) {
            this.CY_GID = CY_GID;
        }

        public String getDM_GID() {
            return DM_GID;
        }

        public void setDM_GID(String DM_GID) {
            this.DM_GID = DM_GID;
        }

        public String getEM_Code() {
            return EM_Code;
        }

        public void setEM_Code(String EM_Code) {
            this.EM_Code = EM_Code;
        }

        public String getEM_Name() {
            return EM_Name;
        }

        public void setEM_Name(String EM_Name) {
            this.EM_Name = EM_Name;
        }

        public int getEM_Sex() {
            return EM_Sex;
        }

        public void setEM_Sex(int EM_Sex) {
            this.EM_Sex = EM_Sex;
        }

        public String getEM_Phone() {
            return EM_Phone;
        }

        public void setEM_Phone(String EM_Phone) {
            this.EM_Phone = EM_Phone;
        }

        public String getEM_Addr() {
            return EM_Addr;
        }

        public void setEM_Addr(String EM_Addr) {
            this.EM_Addr = EM_Addr;
        }

        public String getEM_Remark() {
            return EM_Remark;
        }

        public void setEM_Remark(String EM_Remark) {
            this.EM_Remark = EM_Remark;
        }

        public String getEM_UpdateTime() {
            return EM_UpdateTime;
        }

        public void setEM_UpdateTime(String EM_UpdateTime) {
            this.EM_UpdateTime = EM_UpdateTime;
        }

        public String getEM_Creator() {
            return EM_Creator;
        }

        public void setEM_Creator(String EM_Creator) {
            this.EM_Creator = EM_Creator;
        }

        public Object getEM_Birthday() {
            return EM_Birthday;
        }

        public void setEM_Birthday(Object EM_Birthday) {
            this.EM_Birthday = EM_Birthday;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

        public String getDM_Name() {
            return DM_Name;
        }

        public void setDM_Name(String DM_Name) {
            this.DM_Name = DM_Name;
        }

        public int getEM_TipCard() {
            return EM_TipCard;
        }

        public void setEM_TipCard(int EM_TipCard) {
            this.EM_TipCard = EM_TipCard;
        }

        public int getEM_TipRecharge() {
            return EM_TipRecharge;
        }

        public void setEM_TipRecharge(int EM_TipRecharge) {
            this.EM_TipRecharge = EM_TipRecharge;
        }

        public int getEM_TipChargeTime() {
            return EM_TipChargeTime;
        }

        public void setEM_TipChargeTime(int EM_TipChargeTime) {
            this.EM_TipChargeTime = EM_TipChargeTime;
        }

        public int getEM_TipGoodsConsume() {
            return EM_TipGoodsConsume;
        }

        public void setEM_TipGoodsConsume(int EM_TipGoodsConsume) {
            this.EM_TipGoodsConsume = EM_TipGoodsConsume;
        }

        public int getEM_TipFastConsume() {
            return EM_TipFastConsume;
        }

        public void setEM_TipFastConsume(int EM_TipFastConsume) {
            this.EM_TipFastConsume = EM_TipFastConsume;
        }

        public int getEM_TipTimesConsume() {
            return EM_TipTimesConsume;
        }

        public void setEM_TipTimesConsume(int EM_TipTimesConsume) {
            this.EM_TipTimesConsume = EM_TipTimesConsume;
        }

        public int getEM_TipComboConsume() {
            return EM_TipComboConsume;
        }

        public void setEM_TipComboConsume(int EM_TipComboConsume) {
            this.EM_TipComboConsume = EM_TipComboConsume;
        }
    }
}
