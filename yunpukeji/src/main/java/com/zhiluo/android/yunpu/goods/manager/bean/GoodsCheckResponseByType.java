package com.zhiluo.android.yunpu.goods.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 通过商品类型查询商品信息返回实体
 * 作者：罗咏哲 on 2017/7/31 12:37.
 * 邮箱：137615198@qq.com
 */

public class GoodsCheckResponseByType implements Serializable {
    private static final long UID = 1L;


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":2,"PageIndex":1,"DataList":[{"FildsId":null,"FildsValue":null,"CustomFields":[{"CF_Order":1,"CF_GID":"bf502f7e-6abb-4e4f-b707-67ef27f8bffa","CF_FieldName":"爱买不买","CF_FieldType":"选项","CF_ItemsValue":"买|不买","CF_Value":"买","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:23:33"},{"CF_Order":2,"CF_GID":"8f0601bc-0615-404d-a28b-191bdf8dfa3a","CF_FieldName":"保质期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-30","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:23:49"},{"CF_Order":3,"CF_GID":"06d2d088-6235-467e-ad7e-2e4865f57ae2","CF_FieldName":"备注","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"111","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:24:01"},{"CF_Order":4,"CF_GID":"63d7cd59-6322-4f66-aa93-de580299c4bd","CF_FieldName":"数量","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:24:12"}],"SM_Name":null,"Stock_Number":0,"PM_PurchasePrice":1,"PM_MemPrice":null,"PM_IsDiscount":1,"PM_IsPoint":1,"PM_IsService":0,"PM_IsServiceName":null,"GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","PT_ID":"c4db81ee-addc-44d1-b98e-2a66ef409d13","PM_Code":"154086271236163","PM_Name":"豆浆加奶","PM_SimpleCode":"DJJN","PM_Metering":"","PM_UnitPrice":5,"PM_UpdateTime":"2018-10-30 09:26:07","PM_Remark":"","PM_Creator":"销售001","PM_BigImg":"/img/product.png","PM_SmallImg":null,"PM_Detail":"","PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","PM_SynType":0,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0,"PM_StockPoliceValue":0,"PM_DeleteSign":0,"PT_Name":"普通商品","SM_ID":"4a7b1141-8ca3-4bef-961f-ffd153357823","PM_Repertory":0,"SP_GID":"44fb6b4b-a286-4946-9755-94813fa339ce","SP_Message":null},{"FildsId":null,"FildsValue":null,"CustomFields":[{"CF_Order":1,"CF_GID":"c9638862-4645-443d-83e0-036bd5b8e057","CF_FieldName":"爱买不买","CF_FieldType":"选项","CF_ItemsValue":"买|不买","CF_Value":"买","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"98d40b21-9171-4133-8278-b412766c8a47","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:23:33"},{"CF_Order":2,"CF_GID":"2b62f42c-b26e-4421-a78a-70b47c13bdb3","CF_FieldName":"保质期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-30","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"98d40b21-9171-4133-8278-b412766c8a47","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:23:49"},{"CF_Order":3,"CF_GID":"ff627114-25d6-4319-86c6-f2cce3e2bb90","CF_FieldName":"备注","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"2","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"98d40b21-9171-4133-8278-b412766c8a47","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:24:01"},{"CF_Order":4,"CF_GID":"8507e5dd-b59f-47de-808b-b2dd525a720b","CF_FieldName":"数量","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"665","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"98d40b21-9171-4133-8278-b412766c8a47","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:24:12"}],"SM_Name":null,"Stock_Number":0,"PM_PurchasePrice":10,"PM_MemPrice":null,"PM_IsDiscount":0,"PM_IsPoint":1,"PM_IsService":0,"PM_IsServiceName":null,"GID":"98d40b21-9171-4133-8278-b412766c8a47","PT_ID":"c4db81ee-addc-44d1-b98e-2a66ef409d13","PM_Code":"653138932","PM_Name":"一个安静的普通商品","PM_SimpleCode":"YGAJDPTSP","PM_Metering":"","PM_UnitPrice":10,"PM_UpdateTime":"2018-10-30 11:38:11","PM_Remark":"","PM_Creator":"销售001","PM_BigImg":"/img/goods.png","PM_SmallImg":null,"PM_Detail":"","PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","PM_SynType":0,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0,"PM_StockPoliceValue":0,"PM_DeleteSign":0,"PT_Name":"普通商品","SM_ID":"4a7b1141-8ca3-4bef-961f-ffd153357823","PM_Repertory":0,"SP_GID":"62eac1db-9fe3-4551-8885-6c056e882886","SP_Message":null}],"StatisticsInfo":null}
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

    public static class DataBean implements Serializable {
        /**
         * PageTotal : 1
         * PageSize : 20
         * DataCount : 2
         * PageIndex : 1
         * DataList : [{"FildsId":null,"FildsValue":null,"CustomFields":[{"CF_Order":1,"CF_GID":"bf502f7e-6abb-4e4f-b707-67ef27f8bffa","CF_FieldName":"爱买不买","CF_FieldType":"选项","CF_ItemsValue":"买|不买","CF_Value":"买","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:23:33"},{"CF_Order":2,"CF_GID":"8f0601bc-0615-404d-a28b-191bdf8dfa3a","CF_FieldName":"保质期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-30","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:23:49"},{"CF_Order":3,"CF_GID":"06d2d088-6235-467e-ad7e-2e4865f57ae2","CF_FieldName":"备注","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"111","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:24:01"},{"CF_Order":4,"CF_GID":"63d7cd59-6322-4f66-aa93-de580299c4bd","CF_FieldName":"数量","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:24:12"}],"SM_Name":null,"Stock_Number":0,"PM_PurchasePrice":1,"PM_MemPrice":null,"PM_IsDiscount":1,"PM_IsPoint":1,"PM_IsService":0,"PM_IsServiceName":null,"GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","PT_ID":"c4db81ee-addc-44d1-b98e-2a66ef409d13","PM_Code":"154086271236163","PM_Name":"豆浆加奶","PM_SimpleCode":"DJJN","PM_Metering":"","PM_UnitPrice":5,"PM_UpdateTime":"2018-10-30 09:26:07","PM_Remark":"","PM_Creator":"销售001","PM_BigImg":"/img/product.png","PM_SmallImg":null,"PM_Detail":"","PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","PM_SynType":0,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0,"PM_StockPoliceValue":0,"PM_DeleteSign":0,"PT_Name":"普通商品","SM_ID":"4a7b1141-8ca3-4bef-961f-ffd153357823","PM_Repertory":0,"SP_GID":"44fb6b4b-a286-4946-9755-94813fa339ce","SP_Message":null},{"FildsId":null,"FildsValue":null,"CustomFields":[{"CF_Order":1,"CF_GID":"c9638862-4645-443d-83e0-036bd5b8e057","CF_FieldName":"爱买不买","CF_FieldType":"选项","CF_ItemsValue":"买|不买","CF_Value":"买","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"98d40b21-9171-4133-8278-b412766c8a47","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:23:33"},{"CF_Order":2,"CF_GID":"2b62f42c-b26e-4421-a78a-70b47c13bdb3","CF_FieldName":"保质期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-30","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"98d40b21-9171-4133-8278-b412766c8a47","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:23:49"},{"CF_Order":3,"CF_GID":"ff627114-25d6-4319-86c6-f2cce3e2bb90","CF_FieldName":"备注","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"2","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"98d40b21-9171-4133-8278-b412766c8a47","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:24:01"},{"CF_Order":4,"CF_GID":"8507e5dd-b59f-47de-808b-b2dd525a720b","CF_FieldName":"数量","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"665","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"98d40b21-9171-4133-8278-b412766c8a47","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:24:12"}],"SM_Name":null,"Stock_Number":0,"PM_PurchasePrice":10,"PM_MemPrice":null,"PM_IsDiscount":0,"PM_IsPoint":1,"PM_IsService":0,"PM_IsServiceName":null,"GID":"98d40b21-9171-4133-8278-b412766c8a47","PT_ID":"c4db81ee-addc-44d1-b98e-2a66ef409d13","PM_Code":"653138932","PM_Name":"一个安静的普通商品","PM_SimpleCode":"YGAJDPTSP","PM_Metering":"","PM_UnitPrice":10,"PM_UpdateTime":"2018-10-30 11:38:11","PM_Remark":"","PM_Creator":"销售001","PM_BigImg":"/img/goods.png","PM_SmallImg":null,"PM_Detail":"","PM_Description":"","PM_Modle":"","PM_Brand":"","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","PM_SynType":0,"PM_SpecialOfferValue":0,"PM_MinDisCountValue":0,"PM_FixedIntegralValue":0,"PM_StockPoliceValue":0,"PM_DeleteSign":0,"PT_Name":"普通商品","SM_ID":"4a7b1141-8ca3-4bef-961f-ffd153357823","PM_Repertory":0,"SP_GID":"62eac1db-9fe3-4551-8885-6c056e882886","SP_Message":null}]
         * StatisticsInfo : null
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private Object StatisticsInfo;
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

        public Object getStatisticsInfo() {
            return StatisticsInfo;
        }

        public void setStatisticsInfo(Object StatisticsInfo) {
            this.StatisticsInfo = StatisticsInfo;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class DataListBean implements Serializable {
            /**
             * FildsId : null
             * FildsValue : null
             * CustomFields : [{"CF_Order":1,"CF_GID":"bf502f7e-6abb-4e4f-b707-67ef27f8bffa","CF_FieldName":"爱买不买","CF_FieldType":"选项","CF_ItemsValue":"买|不买","CF_Value":"买","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:23:33"},{"CF_Order":2,"CF_GID":"8f0601bc-0615-404d-a28b-191bdf8dfa3a","CF_FieldName":"保质期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-30","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:23:49"},{"CF_Order":3,"CF_GID":"06d2d088-6235-467e-ad7e-2e4865f57ae2","CF_FieldName":"备注","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"111","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:24:01"},{"CF_Order":4,"CF_GID":"63d7cd59-6322-4f66-aa93-de580299c4bd","CF_FieldName":"数量","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"","PM_GID":"451359a3-0ec4-4e12-bd39-5554f77508bd","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-30 09:24:12"}]
             * SM_Name : null
             * Stock_Number : 0.0
             * PM_PurchasePrice : 1.0
             * PM_MemPrice : null
             * PM_IsDiscount : 1
             * PM_IsPoint : 1
             * PM_IsService : 0
             * PM_IsServiceName : null
             * GID : 451359a3-0ec4-4e12-bd39-5554f77508bd
             * PT_ID : c4db81ee-addc-44d1-b98e-2a66ef409d13
             * PM_Code : 154086271236163
             * PM_Name : 豆浆加奶
             * PM_SimpleCode : DJJN
             * PM_Metering :
             * PM_UnitPrice : 5.0
             * PM_UpdateTime : 2018-10-30 09:26:07
             * PM_Remark :
             * PM_Creator : 销售001
             * PM_BigImg : /img/product.png
             * PM_SmallImg : null
             * PM_Detail :
             * PM_Description :
             * PM_Modle :
             * PM_Brand :
             * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
             * PM_SynType : 0
             * PM_SpecialOfferValue : 0.0
             * PM_MinDisCountValue : 0.0
             * PM_FixedIntegralValue : 0.0
             * PM_StockPoliceValue : 0.0
             * PM_DeleteSign : 0
             * PT_Name : 普通商品
             * SM_ID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * PM_Repertory : 0.0
             * SP_GID : 44fb6b4b-a286-4946-9755-94813fa339ce
             * SP_Message : null
             */

            private Object FildsId;
            private Object FildsValue;
            private Object SM_Name;
            private double Stock_Number;
            private double PM_PurchasePrice;
            private String PM_MemPrice;
            private int PM_IsDiscount;
            private int PM_IsPoint;
            private int PM_IsService;
            private Object PM_IsServiceName;
            private String GID;
            private String PT_ID;
            private String PM_Code;
            private String PM_Name;
            private String PM_SimpleCode;
            private String PM_Metering;
            private double PM_UnitPrice;
            private String PM_UpdateTime;
            private String PM_Remark;
            private String PM_Creator;
            private String PM_BigImg;
            private Object PM_SmallImg;
            private String PM_Detail;
            private String PM_Description;
            private String PM_Modle;
            private String PM_Brand;
            private String CY_GID;
            private int PM_SynType;
            private double PM_SpecialOfferMoney;
            private double PM_SpecialOfferValue;
            private double PM_MinDisCountValue;
            private double PM_FixedIntegralValue;
            private double PM_StockPoliceValue;
            private int PM_DeleteSign;
            private String PT_Name;
            private String SM_ID;
            private double PM_Repertory;
            private String SP_GID;
            private Object SP_Message;
            private double Percent;
            private double EachMoney;
            private double ZHMoney;
            private boolean haveDengjiMoney;
            private String employeeValue;
            private double Now_Stock;
            private boolean Flag;
            private String PM_GroupGID;
            private int GroupCount;
            private String money_d;

            public String getMoney_d() {
                return money_d;
            }

            public void setMoney_d(String money_d) {
                this.money_d = money_d;
            }

            public int getGroupCount() {
                return GroupCount;
            }

            public void setGroupCount(int groupCount) {
                GroupCount = groupCount;
            }

            public String getPM_GroupGID() {
                return PM_GroupGID;
            }

            public void setPM_GroupGID(String PM_GroupGID) {
                this.PM_GroupGID = PM_GroupGID;
            }

            public String getSPD_GID() {
                return SPD_GID;
            }

            public void setSPD_GID(String SPD_GID) {
                this.SPD_GID = SPD_GID;
            }

            private String SPD_GID;

            public boolean isFlag() {
                return Flag;
            }

            public void setFlag(boolean flag) {
                Flag = flag;
            }

            public double getNow_Stock() {
                return Now_Stock;
            }

            public void setNow_Stock(double now_Stock) {
                Now_Stock = now_Stock;
            }

            public String getEmployeeValue() {
                return employeeValue;
            }

            public void setEmployeeValue(String employeeValue) {
                this.employeeValue = employeeValue;
            }

            public Object getHasMemPrice() {
                return HasMemPrice;
            }

            public void setHasMemPrice(Object hasMemPrice) {
                HasMemPrice = hasMemPrice;
            }

            public int getRemindList_State() {
                return RemindList_State;
            }

            public void setRemindList_State(int remindList_State) {
                RemindList_State = remindList_State;
            }

            public Object getCF_GID() {
                return CF_GID;
            }

            public void setCF_GID(Object CF_GID) {
                this.CF_GID = CF_GID;
            }

            public Object getCF_FieldName() {
                return CF_FieldName;
            }

            public void setCF_FieldName(Object CF_FieldName) {
                this.CF_FieldName = CF_FieldName;
            }

            public Object getCF_Value() {
                return CF_Value;
            }

            public void setCF_Value(Object CF_Value) {
                this.CF_Value = CF_Value;
            }

            private Object HasMemPrice;
            private int RemindList_State;
            private Object CF_GID;
            private Object CF_FieldName;
            private Object CF_Value;

            public boolean isChecked() {
                return Checked;
            }

            public void setChecked(boolean checked) {
                Checked = checked;
            }

            private boolean Checked;

            public double getDengjipercen() {
                return dengjipercen;
            }

            public void setDengjipercen(double dengjipercen) {
                this.dengjipercen = dengjipercen;
            }

            private double dengjipercen;

            public double getEachPoint() {
                return EachPoint;
            }

            public void setEachPoint(double eachPoint) {
                EachPoint = eachPoint;
            }

            private double EachPoint;

            public double getPercent() {
                return Percent;
            }

            public void setPercent(double percent) {
                Percent = percent;
            }

            public double getEachMoney() {
                return EachMoney;
            }

            public void setEachMoney(double eachMoney) {
                EachMoney = eachMoney;
            }

            public double getZHMoney() {
                return ZHMoney;
            }

            public void setZHMoney(double ZHMoney) {
                this.ZHMoney = ZHMoney;
            }

            public boolean isHaveDengjiMoney() {
                return haveDengjiMoney;
            }

            public void setHaveDengjiMoney(boolean haveDengjiMoney) {
                this.haveDengjiMoney = haveDengjiMoney;
            }

            public double getNum() {
                return Num;
            }

            public void setNum(double num) {
                Num = num;
            }

            private double Num;
            private List<CustomFieldsBean> CustomFields;


            public Object getFildsId() {
                return FildsId;
            }

            public void setFildsId(Object FildsId) {
                this.FildsId = FildsId;
            }

            public Object getFildsValue() {
                return FildsValue;
            }

            public void setFildsValue(Object FildsValue) {
                this.FildsValue = FildsValue;
            }

            public Object getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(Object SM_Name) {
                this.SM_Name = SM_Name;
            }

            public double getStock_Number() {
                return Stock_Number;
            }

            public void setStock_Number(double Stock_Number) {
                this.Stock_Number = Stock_Number;
            }

            public double getPM_PurchasePrice() {
                return PM_PurchasePrice;
            }

            public void setPM_PurchasePrice(double PM_PurchasePrice) {
                this.PM_PurchasePrice = PM_PurchasePrice;
            }

            public String getPM_MemPrice() {
                return PM_MemPrice;
            }

            public void setPM_MemPrice(String PM_MemPrice) {
                this.PM_MemPrice = PM_MemPrice;
            }

            public int getPM_IsDiscount() {
                return PM_IsDiscount;
            }

            public void setPM_IsDiscount(int PM_IsDiscount) {
                this.PM_IsDiscount = PM_IsDiscount;
            }

            public int getPM_IsPoint() {
                return PM_IsPoint;
            }

            public void setPM_IsPoint(int PM_IsPoint) {
                this.PM_IsPoint = PM_IsPoint;
            }

            public int getPM_IsService() {
                return PM_IsService;
            }

            public void setPM_IsService(int PM_IsService) {
                this.PM_IsService = PM_IsService;
            }

            public Object getPM_IsServiceName() {
                return PM_IsServiceName;
            }

            public void setPM_IsServiceName(Object PM_IsServiceName) {
                this.PM_IsServiceName = PM_IsServiceName;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getPT_ID() {
                return PT_ID;
            }

            public void setPT_ID(String PT_ID) {
                this.PT_ID = PT_ID;
            }

            public String getPM_Code() {
                return PM_Code;
            }

            public void setPM_Code(String PM_Code) {
                this.PM_Code = PM_Code;
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

            public String getPM_UpdateTime() {
                return PM_UpdateTime;
            }

            public void setPM_UpdateTime(String PM_UpdateTime) {
                this.PM_UpdateTime = PM_UpdateTime;
            }

            public String getPM_Remark() {
                return PM_Remark;
            }

            public void setPM_Remark(String PM_Remark) {
                this.PM_Remark = PM_Remark;
            }

            public String getPM_Creator() {
                return PM_Creator;
            }

            public void setPM_Creator(String PM_Creator) {
                this.PM_Creator = PM_Creator;
            }

            public String getPM_BigImg() {
                return PM_BigImg;
            }

            public void setPM_BigImg(String PM_BigImg) {
                this.PM_BigImg = PM_BigImg;
            }

            public Object getPM_SmallImg() {
                return PM_SmallImg;
            }

            public void setPM_SmallImg(Object PM_SmallImg) {
                this.PM_SmallImg = PM_SmallImg;
            }

            public String getPM_Detail() {
                return PM_Detail;
            }

            public void setPM_Detail(String PM_Detail) {
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

            public String getPM_Brand() {
                return PM_Brand;
            }

            public void setPM_Brand(String PM_Brand) {
                this.PM_Brand = PM_Brand;
            }

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
                this.CY_GID = CY_GID;
            }

            public int getPM_SynType() {
                return PM_SynType;
            }

            public void setPM_SynType(int PM_SynType) {
                this.PM_SynType = PM_SynType;
            }


            public double getPM_SpecialOfferMoney() {
                return PM_SpecialOfferMoney;
            }

            public void setPM_SpecialOfferMoney(double PM_SpecialOfferMoney) {
                this.PM_SpecialOfferMoney = PM_SpecialOfferMoney;
            }

            public double getPM_SpecialOfferValue() {
                return PM_SpecialOfferValue;
            }

            public void setPM_SpecialOfferValue(double PM_SpecialOfferValue) {
                this.PM_SpecialOfferValue = PM_SpecialOfferValue;
            }

            public double getPM_MinDisCountValue() {
                return PM_MinDisCountValue;
            }

            public void setPM_MinDisCountValue(double PM_MinDisCountValue) {
                this.PM_MinDisCountValue = PM_MinDisCountValue;
            }

            public double getPM_FixedIntegralValue() {
                return PM_FixedIntegralValue;
            }

            public void setPM_FixedIntegralValue(double PM_FixedIntegralValue) {
                this.PM_FixedIntegralValue = PM_FixedIntegralValue;
            }

            public double getPM_StockPoliceValue() {
                return PM_StockPoliceValue;
            }

            public void setPM_StockPoliceValue(double PM_StockPoliceValue) {
                this.PM_StockPoliceValue = PM_StockPoliceValue;
            }

            public int getPM_DeleteSign() {
                return PM_DeleteSign;
            }

            public void setPM_DeleteSign(int PM_DeleteSign) {
                this.PM_DeleteSign = PM_DeleteSign;
            }

            public String getPT_Name() {
                return PT_Name;
            }

            public void setPT_Name(String PT_Name) {
                this.PT_Name = PT_Name;
            }

            public String getSM_ID() {
                return SM_ID;
            }

            public void setSM_ID(String SM_ID) {
                this.SM_ID = SM_ID;
            }

            public double getPM_Repertory() {
                return PM_Repertory;
            }

            public void setPM_Repertory(double PM_Repertory) {
                this.PM_Repertory = PM_Repertory;
            }

            public String getSP_GID() {
                return SP_GID;
            }

            public void setSP_GID(String SP_GID) {
                this.SP_GID = SP_GID;
            }

            public Object getSP_Message() {
                return SP_Message;
            }

            public void setSP_Message(Object SP_Message) {
                this.SP_Message = SP_Message;
            }

            public List<CustomFieldsBean> getCustomFields() {
                return CustomFields;
            }

            public void setCustomFields(List<CustomFieldsBean> CustomFields) {
                this.CustomFields = CustomFields;
            }


            public static class CustomFieldsBean implements Serializable {
                /**
                 * CF_Order : 1
                 * CF_GID : bf502f7e-6abb-4e4f-b707-67ef27f8bffa
                 * CF_FieldName : 爱买不买
                 * CF_FieldType : 选项
                 * CF_ItemsValue : 买|不买
                 * CF_Value : 买
                 * CF_Required : 是
                 * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
                 * VIP_GID :
                 * PM_GID : 451359a3-0ec4-4e12-bd39-5554f77508bd
                 * CF_CreateUser : 销售001
                 * CF_CreateTime : 2018-10-30 09:23:33
                 */

                private int CF_Order;
                private String CF_GID;
                private String CF_FieldName;
                private String CF_FieldType;
                private String CF_ItemsValue;
                private String CF_Value;
                private String CF_Required;
                private String CY_GID;
                private String VIP_GID;
                private String PM_GID;
                private String CF_CreateUser;
                private String CF_CreateTime;

                public int getCF_Order() {
                    return CF_Order;
                }

                public void setCF_Order(int CF_Order) {
                    this.CF_Order = CF_Order;
                }

                public String getCF_GID() {
                    return CF_GID;
                }

                public void setCF_GID(String CF_GID) {
                    this.CF_GID = CF_GID;
                }

                public String getCF_FieldName() {
                    return CF_FieldName;
                }

                public void setCF_FieldName(String CF_FieldName) {
                    this.CF_FieldName = CF_FieldName;
                }

                public String getCF_FieldType() {
                    return CF_FieldType;
                }

                public void setCF_FieldType(String CF_FieldType) {
                    this.CF_FieldType = CF_FieldType;
                }

                public String getCF_ItemsValue() {
                    return CF_ItemsValue;
                }

                public void setCF_ItemsValue(String CF_ItemsValue) {
                    this.CF_ItemsValue = CF_ItemsValue;
                }

                public String getCF_Value() {
                    return CF_Value;
                }

                public void setCF_Value(String CF_Value) {
                    this.CF_Value = CF_Value;
                }

                public String getCF_Required() {
                    return CF_Required;
                }

                public void setCF_Required(String CF_Required) {
                    this.CF_Required = CF_Required;
                }

                public String getCY_GID() {
                    return CY_GID;
                }

                public void setCY_GID(String CY_GID) {
                    this.CY_GID = CY_GID;
                }

                public String getVIP_GID() {
                    return VIP_GID;
                }

                public void setVIP_GID(String VIP_GID) {
                    this.VIP_GID = VIP_GID;
                }

                public String getPM_GID() {
                    return PM_GID;
                }

                public void setPM_GID(String PM_GID) {
                    this.PM_GID = PM_GID;
                }

                public String getCF_CreateUser() {
                    return CF_CreateUser;
                }

                public void setCF_CreateUser(String CF_CreateUser) {
                    this.CF_CreateUser = CF_CreateUser;
                }

                public String getCF_CreateTime() {
                    return CF_CreateTime;
                }

                public void setCF_CreateTime(String CF_CreateTime) {
                    this.CF_CreateTime = CF_CreateTime;
                }
            }
        }
    }
}
