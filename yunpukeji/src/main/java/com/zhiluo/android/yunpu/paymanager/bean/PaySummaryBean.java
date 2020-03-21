package com.zhiluo.android.yunpu.paymanager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2019-05-24.
 */

public class PaySummaryBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"8cd1c8b6-e626-47da-a93d-82ca539fa4ce","ET_GID":"8867635c-89e2-4dff-96c0-fefb91b96f14","ET_Name":null,"ED_Money":32,"ED_Time":"2019-05-22 10:10:10","ED_Remark":"","ED_Expendiator":"C位狙击吃鸡手","EM_GID":"0f87e1fc-d8a0-4681-88e6-64bf08b0b15a","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ED_Creator":"分号1","ED_CreateTime":"2019-05-24 10:10:36"},{"GID":"be4a0cbd-a217-4518-b25e-4269ac189378","ET_GID":"8867635c-89e2-4dff-96c0-fefb91b96f14","ET_Name":null,"ED_Money":20,"ED_Time":"2019-05-22 10:39:39","ED_Remark":"","ED_Expendiator":"C位狙击吃鸡手","EM_GID":"0f87e1fc-d8a0-4681-88e6-64bf08b0b15a","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ED_Creator":"销售001","ED_CreateTime":"2019-05-22 10:39:59"},{"GID":"dcc54277-c922-4327-908a-7cbbe7b3e8d6","ET_GID":"8867635c-89e2-4dff-96c0-fefb91b96f14","ET_Name":null,"ED_Money":20,"ED_Time":"2019-05-22 17:13:01","ED_Remark":"","ED_Expendiator":"C位狙击吃鸡手","EM_GID":"0f87e1fc-d8a0-4681-88e6-64bf08b0b15a","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ED_Creator":"销售001","ED_CreateTime":"2019-05-22 17:13:12"},{"GID":"ddfd63b4-9d3f-40c1-9516-219907e4820a","ET_GID":"a1129bc4-ea76-4ed6-b45b-0201954268ff","ET_Name":null,"ED_Money":15,"ED_Time":"2019-05-23 18:10:42","ED_Remark":"","ED_Expendiator":"C位狙击吃鸡手","EM_GID":"0f87e1fc-d8a0-4681-88e6-64bf08b0b15a","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ED_Creator":"销售001","ED_CreateTime":"2019-05-23 18:10:51"},{"GID":"fca824e9-654b-4af8-a0a7-0a2bd76a3cda","ET_GID":"ac5805e8-5d1e-4029-9be5-071f1c9f99b7","ET_Name":null,"ED_Money":20,"ED_Time":"2019-05-09 10:23:01","ED_Remark":"","ED_Expendiator":"售卡","EM_GID":"358985a7-d1af-4cd9-b123-4c95bf7d9f8e","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ED_Creator":"销售001","ED_CreateTime":"2019-05-09 10:23:23"}]
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

    public static class DataBean implements Serializable{
        /**
         * GID : 8cd1c8b6-e626-47da-a93d-82ca539fa4ce
         * ET_GID : 8867635c-89e2-4dff-96c0-fefb91b96f14
         * ET_Name : null
         * ED_Money : 32.0
         * ED_Time : 2019-05-22 10:10:10
         * ED_Remark :
         * ED_Expendiator : C位狙击吃鸡手
         * EM_GID : 0f87e1fc-d8a0-4681-88e6-64bf08b0b15a
         * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
         * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
         * ED_Creator : 分号1
         * ED_CreateTime : 2019-05-24 10:10:36
         */

        private String GID;
        private String ET_GID;
        private Object ET_Name;
        private double ED_Money;
        private String ED_Time;
        private String ED_Remark;
        private String ED_Expendiator;
        private String EM_GID;
        private String CY_GID;
        private String SM_GID;
        private String ED_Creator;
        private String ED_CreateTime;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getET_GID() {
            return ET_GID;
        }

        public void setET_GID(String ET_GID) {
            this.ET_GID = ET_GID;
        }

        public Object getET_Name() {
            return ET_Name;
        }

        public void setET_Name(Object ET_Name) {
            this.ET_Name = ET_Name;
        }

        public double getED_Money() {
            return ED_Money;
        }

        public void setED_Money(double ED_Money) {
            this.ED_Money = ED_Money;
        }

        public String getED_Time() {
            return ED_Time;
        }

        public void setED_Time(String ED_Time) {
            this.ED_Time = ED_Time;
        }

        public String getED_Remark() {
            return ED_Remark;
        }

        public void setED_Remark(String ED_Remark) {
            this.ED_Remark = ED_Remark;
        }

        public String getED_Expendiator() {
            return ED_Expendiator;
        }

        public void setED_Expendiator(String ED_Expendiator) {
            this.ED_Expendiator = ED_Expendiator;
        }

        public String getEM_GID() {
            return EM_GID;
        }

        public void setEM_GID(String EM_GID) {
            this.EM_GID = EM_GID;
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

        public String getED_Creator() {
            return ED_Creator;
        }

        public void setED_Creator(String ED_Creator) {
            this.ED_Creator = ED_Creator;
        }

        public String getED_CreateTime() {
            return ED_CreateTime;
        }

        public void setED_CreateTime(String ED_CreateTime) {
            this.ED_CreateTime = ED_CreateTime;
        }
    }
}
