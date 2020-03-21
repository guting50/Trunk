package com.zhiluo.android.yunpu.goods.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2019-07-03.
 */

public class OilListBean implements Serializable {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8","OM_Name":"#96","OM_Price":6,"SR_Inventory":200,"SR_StockWarning":null,"SIR_InPrice":null,"OM_CreateTime":"2019-07-03 10:18:16","OM_Creator":"销售001","OM_SortNum":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823"},{"GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","OM_Price":7,"SR_Inventory":100,"SR_StockWarning":null,"SIR_InPrice":null,"OM_CreateTime":"2019-07-03 10:18:55","OM_Creator":"销售001","OM_SortNum":1,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823"}]
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
         * GID : cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8
         * OM_Name : #96
         * OM_Price : 6.0
         * SR_Inventory : 200.0
         * SR_StockWarning : null
         * SIR_InPrice : null
         * OM_CreateTime : 2019-07-03 10:18:16
         * OM_Creator : 销售001
         * OM_SortNum : 1.0
         * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
         * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
         */

        /**
         * 油、天然气单位
         * */
        private String OM_Unit;
        private String GID;
        private String OM_Name;
        private double OM_Price;
        private double SR_Inventory;
        private Object SR_StockWarning;
        private Object SIR_InPrice;
        private String OM_CreateTime;
        private String OM_Creator;
        private double OM_SortNum;
        private String CY_GID;
        private String SM_GID;
        private double Num;

        public double getEachPoint() {
            return EachPoint;
        }

        public void setEachPoint(double eachPoint) {
            EachPoint = eachPoint;
        }

        private double EachPoint;

        public double getNum() {
            return Num;
        }

        public void setNum(double num) {
            Num = num;
        }

        public double getZHMoney() {
            return ZHMoney;
        }

        public void setZHMoney(double ZHMoney) {
            this.ZHMoney = ZHMoney;
        }

        private double ZHMoney;

        public boolean isChecked() {
            return Checked;
        }

        public void setChecked(boolean checked) {
            Checked = checked;
        }

        private boolean Checked;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getOM_Name() {
            return OM_Name;
        }

        public void setOM_Name(String OM_Name) {
            this.OM_Name = OM_Name;
        }

        public double getOM_Price() {
            return OM_Price;
        }

        public void setOM_Price(double OM_Price) {
            this.OM_Price = OM_Price;
        }

        public double getSR_Inventory() {
            return SR_Inventory;
        }

        public void setSR_Inventory(double SR_Inventory) {
            this.SR_Inventory = SR_Inventory;
        }

        public Object getSR_StockWarning() {
            return SR_StockWarning;
        }

        public void setSR_StockWarning(Object SR_StockWarning) {
            this.SR_StockWarning = SR_StockWarning;
        }

        public Object getSIR_InPrice() {
            return SIR_InPrice;
        }

        public void setSIR_InPrice(Object SIR_InPrice) {
            this.SIR_InPrice = SIR_InPrice;
        }

        public String getOM_CreateTime() {
            return OM_CreateTime;
        }

        public void setOM_CreateTime(String OM_CreateTime) {
            this.OM_CreateTime = OM_CreateTime;
        }

        public String getOM_Creator() {
            return OM_Creator;
        }

        public void setOM_Creator(String OM_Creator) {
            this.OM_Creator = OM_Creator;
        }

        public double getOM_SortNum() {
            return OM_SortNum;
        }

        public void setOM_SortNum(double OM_SortNum) {
            this.OM_SortNum = OM_SortNum;
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

        public String getOM_Unit() {
            return OM_Unit;
        }

        public void setOM_Unit(String OM_Unit) {
            this.OM_Unit = OM_Unit;
        }
    }
}
