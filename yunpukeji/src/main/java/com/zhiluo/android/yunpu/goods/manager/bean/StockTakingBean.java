package com.zhiluo.android.yunpu.goods.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/26 15:31.
 * 邮箱：137615198@qq.com
 */

public class StockTakingBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":2,"PageSize":20,"DataCount":37,"PageIndex":1,"DataList":[{"GID":"0eff9231-e92f-4d1c-a627-3c9fd043dda8","PM_Code":"eaddd22","PM_BigImg":"/img/goods.png","PM_Name":"面巾纸","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"d87dabb9-d7ab-4082-9c61-b9577c7acdda"},{"GID":"54c91a2d-e7c3-42af-b5fe-c7437c1a7d8f","PM_Code":"582","PM_BigImg":"/img/goods.png","PM_Name":"洋葱","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"蔬菜","SM_GID":null,"PM_GID":"3b5e378b-f06d-47aa-919b-af5ab8a39ed5"},{"GID":"9ee8b20e-42b0-4c09-82b6-506b74372220","PM_Code":"855484","PM_BigImg":"/img/goods.png","PM_Name":"土豆","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"蔬菜","SM_GID":null,"PM_GID":"47394484-19d4-4cec-b1df-d6690ee9c26f"},{"GID":"c5549fae-c78a-48e5-ab00-eb3b6eed81e3","PM_Code":"5872","PM_BigImg":"/img/goods.png","PM_Name":"西兰花","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"蔬菜","SM_GID":null,"PM_GID":"79570afe-d74b-4790-8078-baf2453d9208"},{"GID":"2186c9a6-8794-416a-b5aa-4a1d95b7b078","PM_Code":"6914068019529","PM_BigImg":"/img/goods.png","PM_Name":"纸巾","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"娱乐","SM_GID":null,"PM_GID":"78f399f7-5927-4278-932b-be555e41518a"},{"GID":"930638a3-127b-4262-b835-e6a9518a72c0","PM_Code":"0048","PM_BigImg":"/img/goods.png","PM_Name":"Android","PM_Modle":"","PM_Metering":"元","SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"b65140b2-5f87-47b2-9911-5bee76bac91e"},{"GID":"6ddc00dd-e3fd-4fb5-ab2b-9579f5274c6a","PM_Code":"ndndn","PM_BigImg":"/img/goods.png","PM_Name":"hbsbbsh","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"52391c9b-01b4-44b5-83f9-bb6d15a104bf"},{"GID":"467ba0e4-2a86-44fd-9036-e5fb45f1bc13","PM_Code":"ggghg","PM_BigImg":"/img/goods.png","PM_Name":"yggg","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"da0c76c6-5ef8-4a5e-a8a3-596d244778a3"},{"GID":"4b9386c5-1455-4797-9765-b583b819677c","PM_Code":"nndn","PM_BigImg":"/img/goods.png","PM_Name":"bsbsb","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"fe3b0cc7-940c-4f26-8db3-f76c7b4f1276"},{"GID":"ce3fff4a-846a-48d6-bae4-beb9896d906a","PM_Code":"ndnnd","PM_BigImg":"/img/goods.png","PM_Name":"dhhdhsbbd","PM_Modle":"H","PM_Metering":"H","SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"441287c3-2c70-4c94-b7ab-8d7200597d57"},{"GID":"6ba30a21-db50-4ed7-ab78-269390967e59","PM_Code":"002","PM_BigImg":"/img/goods.png","PM_Name":"苹果","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"蔬菜","SM_GID":null,"PM_GID":"ec147f92-0b55-499a-8f86-d2c2194231a8"},{"GID":"e5dc108c-10ce-4863-9a3e-0c987fa7de6d","PM_Code":"90999","PM_BigImg":"/img/goods.png","PM_Name":"添加商品测试","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"93684880-df23-49d3-b757-160e16a43b9a"},{"GID":"62bb50fb-214f-4f9a-be78-621bd9f2f423","PM_Code":"00121","PM_BigImg":"/img/goods.png","PM_Name":"商品","PM_Modle":"规格","PM_Metering":"元","SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"010e4af4-050c-4a0c-9540-2756d58db4e8"},{"GID":"89f44d59-bde4-454c-a878-48c9215a2322","PM_Code":"433","PM_BigImg":"/img/goods.png","PM_Name":"茶杯","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"绿茶","SM_GID":null,"PM_GID":"0462eff8-8c27-4b28-8f59-ed0ef972a683"},{"GID":"4206e510-73b8-498a-96de-d1fa820de704","PM_Code":"5861","PM_BigImg":null,"PM_Name":"干脆面","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"蔬菜","SM_GID":null,"PM_GID":"8841502f-b4c1-42f9-8601-5a2d2615cc8d"},{"GID":"8e93ee44-39d2-4eb2-a7c9-a06e6fa6e0db","PM_Code":"sjms","PM_BigImg":"/img/goods.png","PM_Name":"设计模式","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"be9d5eb7-f670-4879-a9d2-e865170b3690"},{"GID":"196bd607-30b5-47d9-baa6-240dccc5ad82","PM_Code":"9787115439789","PM_BigImg":"/FileUpload/131472422347279118.png","PM_Name":"第一行代码","PM_Modle":"","PM_Metering":"元","SP_Number":-3,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"e9b1941c-9f80-4033-8ec9-6ed4b254598b"},{"GID":"5e5cd8a3-98f2-42ae-ade7-63d5c333dc77","PM_Code":"8568","PM_BigImg":null,"PM_Name":"铁观音","PM_Modle":"","PM_Metering":null,"SP_Number":-6,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"绿茶","SM_GID":null,"PM_GID":"461ef860-b14b-4439-b1c9-b18fbe0c38cc"},{"GID":"b211da73-d8bd-4353-9cdd-7d58e0c21fb3","PM_Code":"987667","PM_BigImg":"/img/goods.png","PM_Name":"短裤","PM_Modle":"","PM_Metering":null,"SP_Number":-6,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"服装","SM_GID":null,"PM_GID":"bac8b038-b45b-46da-b7ff-7cf3e7e0b2da"},{"GID":"27a6ec6f-8519-4200-94df-198e8331003e","PM_Code":"98762","PM_BigImg":"/FileUpload/131466546226551227.png","PM_Name":"衣服","PM_Modle":"","PM_Metering":null,"SP_Number":9,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"服装","SM_GID":null,"PM_GID":"225ff2d4-a598-4e5c-bf79-041e96807b61"}],"StatisticsInfo":null}
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
         * PageTotal : 2
         * PageSize : 20
         * DataCount : 37
         * PageIndex : 1
         * DataList : [{"GID":"0eff9231-e92f-4d1c-a627-3c9fd043dda8","PM_Code":"eaddd22","PM_BigImg":"/img/goods.png","PM_Name":"面巾纸","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"d87dabb9-d7ab-4082-9c61-b9577c7acdda"},{"GID":"54c91a2d-e7c3-42af-b5fe-c7437c1a7d8f","PM_Code":"582","PM_BigImg":"/img/goods.png","PM_Name":"洋葱","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"蔬菜","SM_GID":null,"PM_GID":"3b5e378b-f06d-47aa-919b-af5ab8a39ed5"},{"GID":"9ee8b20e-42b0-4c09-82b6-506b74372220","PM_Code":"855484","PM_BigImg":"/img/goods.png","PM_Name":"土豆","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"蔬菜","SM_GID":null,"PM_GID":"47394484-19d4-4cec-b1df-d6690ee9c26f"},{"GID":"c5549fae-c78a-48e5-ab00-eb3b6eed81e3","PM_Code":"5872","PM_BigImg":"/img/goods.png","PM_Name":"西兰花","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"蔬菜","SM_GID":null,"PM_GID":"79570afe-d74b-4790-8078-baf2453d9208"},{"GID":"2186c9a6-8794-416a-b5aa-4a1d95b7b078","PM_Code":"6914068019529","PM_BigImg":"/img/goods.png","PM_Name":"纸巾","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"娱乐","SM_GID":null,"PM_GID":"78f399f7-5927-4278-932b-be555e41518a"},{"GID":"930638a3-127b-4262-b835-e6a9518a72c0","PM_Code":"0048","PM_BigImg":"/img/goods.png","PM_Name":"Android","PM_Modle":"","PM_Metering":"元","SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"b65140b2-5f87-47b2-9911-5bee76bac91e"},{"GID":"6ddc00dd-e3fd-4fb5-ab2b-9579f5274c6a","PM_Code":"ndndn","PM_BigImg":"/img/goods.png","PM_Name":"hbsbbsh","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"52391c9b-01b4-44b5-83f9-bb6d15a104bf"},{"GID":"467ba0e4-2a86-44fd-9036-e5fb45f1bc13","PM_Code":"ggghg","PM_BigImg":"/img/goods.png","PM_Name":"yggg","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"da0c76c6-5ef8-4a5e-a8a3-596d244778a3"},{"GID":"4b9386c5-1455-4797-9765-b583b819677c","PM_Code":"nndn","PM_BigImg":"/img/goods.png","PM_Name":"bsbsb","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"fe3b0cc7-940c-4f26-8db3-f76c7b4f1276"},{"GID":"ce3fff4a-846a-48d6-bae4-beb9896d906a","PM_Code":"ndnnd","PM_BigImg":"/img/goods.png","PM_Name":"dhhdhsbbd","PM_Modle":"H","PM_Metering":"H","SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"441287c3-2c70-4c94-b7ab-8d7200597d57"},{"GID":"6ba30a21-db50-4ed7-ab78-269390967e59","PM_Code":"002","PM_BigImg":"/img/goods.png","PM_Name":"苹果","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"蔬菜","SM_GID":null,"PM_GID":"ec147f92-0b55-499a-8f86-d2c2194231a8"},{"GID":"e5dc108c-10ce-4863-9a3e-0c987fa7de6d","PM_Code":"90999","PM_BigImg":"/img/goods.png","PM_Name":"添加商品测试","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"93684880-df23-49d3-b757-160e16a43b9a"},{"GID":"62bb50fb-214f-4f9a-be78-621bd9f2f423","PM_Code":"00121","PM_BigImg":"/img/goods.png","PM_Name":"商品","PM_Modle":"规格","PM_Metering":"元","SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"010e4af4-050c-4a0c-9540-2756d58db4e8"},{"GID":"89f44d59-bde4-454c-a878-48c9215a2322","PM_Code":"433","PM_BigImg":"/img/goods.png","PM_Name":"茶杯","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"绿茶","SM_GID":null,"PM_GID":"0462eff8-8c27-4b28-8f59-ed0ef972a683"},{"GID":"4206e510-73b8-498a-96de-d1fa820de704","PM_Code":"5861","PM_BigImg":null,"PM_Name":"干脆面","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"蔬菜","SM_GID":null,"PM_GID":"8841502f-b4c1-42f9-8601-5a2d2615cc8d"},{"GID":"8e93ee44-39d2-4eb2-a7c9-a06e6fa6e0db","PM_Code":"sjms","PM_BigImg":"/img/goods.png","PM_Name":"设计模式","PM_Modle":"","PM_Metering":null,"SP_Number":0,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"be9d5eb7-f670-4879-a9d2-e865170b3690"},{"GID":"196bd607-30b5-47d9-baa6-240dccc5ad82","PM_Code":"9787115439789","PM_BigImg":"/FileUpload/131472422347279118.png","PM_Name":"第一行代码","PM_Modle":"","PM_Metering":"元","SP_Number":-3,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"计算机","SM_GID":null,"PM_GID":"e9b1941c-9f80-4033-8ec9-6ed4b254598b"},{"GID":"5e5cd8a3-98f2-42ae-ade7-63d5c333dc77","PM_Code":"8568","PM_BigImg":null,"PM_Name":"铁观音","PM_Modle":"","PM_Metering":null,"SP_Number":-6,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"绿茶","SM_GID":null,"PM_GID":"461ef860-b14b-4439-b1c9-b18fbe0c38cc"},{"GID":"b211da73-d8bd-4353-9cdd-7d58e0c21fb3","PM_Code":"987667","PM_BigImg":"/img/goods.png","PM_Name":"短裤","PM_Modle":"","PM_Metering":null,"SP_Number":-6,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"服装","SM_GID":null,"PM_GID":"bac8b038-b45b-46da-b7ff-7cf3e7e0b2da"},{"GID":"27a6ec6f-8519-4200-94df-198e8331003e","PM_Code":"98762","PM_BigImg":"/FileUpload/131466546226551227.png","PM_Name":"衣服","PM_Modle":"","PM_Metering":null,"SP_Number":9,"SCD_CreateTime":null,"SCD_Creator":null,"PT_Name":"服装","SM_GID":null,"PM_GID":"225ff2d4-a598-4e5c-bf79-041e96807b61"}]
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

        public static class DataListBean implements Serializable{
            /**
             * GID : 0eff9231-e92f-4d1c-a627-3c9fd043dda8
             * PM_Code : eaddd22
             * PM_BigImg : /img/goods.png
             * PM_Name : 面巾纸
             * PM_Modle :
             * PM_Metering : null
             * SP_Number : 0.0
             * SCD_CreateTime : null
             * SCD_Creator : null
             * PT_Name : 计算机
             * SM_GID : null
             * PM_GID : d87dabb9-d7ab-4082-9c61-b9577c7acdda
             */

            private String GID;
            private String PM_Code;
            private String PM_BigImg;
            private String PM_Name;
            private String PM_Modle;
            private Object PM_Metering;
            private double SP_Number;
            private Object SCD_CreateTime;
            private Object SCD_Creator;
            private String PT_Name;
            private Object SM_GID;
            private String PM_GID;
            private double Now_Stock;
            private boolean flag;

            public boolean isFlag() {
                return flag;
            }

            public void setFlag(boolean flag) {
                this.flag = flag;
            }

            public double getNow_Stock() {
                return Now_Stock;
            }

            public void setNow_Stock(double now_Stock) {
                Now_Stock = now_Stock;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getPM_Code() {
                return PM_Code;
            }

            public void setPM_Code(String PM_Code) {
                this.PM_Code = PM_Code;
            }

            public String getPM_BigImg() {
                return PM_BigImg;
            }

            public void setPM_BigImg(String PM_BigImg) {
                this.PM_BigImg = PM_BigImg;
            }

            public String getPM_Name() {
                return PM_Name;
            }

            public void setPM_Name(String PM_Name) {
                this.PM_Name = PM_Name;
            }

            public String getPM_Modle() {
                return PM_Modle;
            }

            public void setPM_Modle(String PM_Modle) {
                this.PM_Modle = PM_Modle;
            }

            public Object getPM_Metering() {
                return PM_Metering;
            }

            public void setPM_Metering(Object PM_Metering) {
                this.PM_Metering = PM_Metering;
            }

            public double getSP_Number() {
                return SP_Number;
            }

            public void setSP_Number(double SP_Number) {
                this.SP_Number = SP_Number;
                Now_Stock = SP_Number;
            }

            public Object getSCD_CreateTime() {
                return SCD_CreateTime;
            }

            public void setSCD_CreateTime(Object SCD_CreateTime) {
                this.SCD_CreateTime = SCD_CreateTime;
            }

            public Object getSCD_Creator() {
                return SCD_Creator;
            }

            public void setSCD_Creator(Object SCD_Creator) {
                this.SCD_Creator = SCD_Creator;
            }

            public String getPT_Name() {
                return PT_Name;
            }

            public void setPT_Name(String PT_Name) {
                this.PT_Name = PT_Name;
            }

            public Object getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(Object SM_GID) {
                this.SM_GID = SM_GID;
            }

            public String getPM_GID() {
                return PM_GID;
            }

            public void setPM_GID(String PM_GID) {
                this.PM_GID = PM_GID;
            }
        }
    }
}
