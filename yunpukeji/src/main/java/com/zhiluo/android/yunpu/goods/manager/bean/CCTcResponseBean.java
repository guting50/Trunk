package com.zhiluo.android.yunpu.goods.manager.bean;

import java.util.List;

/**
 * Created by ZPH on 2018-12-19.
 */

public class CCTcResponseBean {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":50,"DataCount":1,"PageIndex":1,"DataList":[{"PC_Type":1,"GID":"2b48ebe7-7f07-49cd-aad4-25086702de98","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","PC_Creator":"销售001","PC_Code":"10","PC_Name":"空间规划空间","PC_Price":100,"PC_Discount":0,"PC_specification":"","PC_Integral":0,"PC_SynState":0,"PC_Remark":"","PC_CreateTime":"2018-11-01 16:07:08","PC_Image":"/img/goods.png","PC_ProductJson":"[{\"GID\":\"2b641154-14e5-40f1-875e-7fb8a71ecc3d\",\"PM_Name\":\"服务商品\",\"PM_Number\":\"2.0\",\"PM_UnitPrice\":0}]","PC_DeleteSign":0,"PC_ProductJsonList":null,"SM_Name":null}],"StatisticsInfo":null}
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
         * PageSize : 50
         * DataCount : 1
         * PageIndex : 1
         * DataList : [{"PC_Type":1,"GID":"2b48ebe7-7f07-49cd-aad4-25086702de98","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","PC_Creator":"销售001","PC_Code":"10","PC_Name":"空间规划空间","PC_Price":100,"PC_Discount":0,"PC_specification":"","PC_Integral":0,"PC_SynState":0,"PC_Remark":"","PC_CreateTime":"2018-11-01 16:07:08","PC_Image":"/img/goods.png","PC_ProductJson":"[{\"GID\":\"2b641154-14e5-40f1-875e-7fb8a71ecc3d\",\"PM_Name\":\"服务商品\",\"PM_Number\":\"2.0\",\"PM_UnitPrice\":0}]","PC_DeleteSign":0,"PC_ProductJsonList":null,"SM_Name":null}]
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

        public static class DataListBean {
            /**
             * PC_Type : 1
             * GID : 2b48ebe7-7f07-49cd-aad4-25086702de98
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
             * PC_Creator : 销售001
             * PC_Code : 10
             * PC_Name : 空间规划空间
             * PC_Price : 100.0
             * PC_Discount : 0.0
             * PC_specification :
             * PC_Integral : 0
             * PC_SynState : 0
             * PC_Remark :
             * PC_CreateTime : 2018-11-01 16:07:08
             * PC_Image : /img/goods.png
             * PC_ProductJson : [{"GID":"2b641154-14e5-40f1-875e-7fb8a71ecc3d","PM_Name":"服务商品","PM_Number":"2.0","PM_UnitPrice":0}]
             * PC_DeleteSign : 0
             * PC_ProductJsonList : null
             * SM_Name : null
             */

            private int PC_Type;
            private String GID;
            private String SM_GID;
            private String CY_GID;
            private String PC_Creator;
            private String PC_Code;
            private String PC_Name;
            private double PC_Price;
            private double PC_Discount;
            private String PC_specification;
            private int PC_Integral;
            private int PC_SynState;
            private String PC_Remark;
            private String PC_CreateTime;
            private String PC_Image;
            private String PC_ProductJson;
            private int PC_DeleteSign;
            private Object PC_ProductJsonList;
            private Object SM_Name;

            public int getPC_Type() {
                return PC_Type;
            }

            public void setPC_Type(int PC_Type) {
                this.PC_Type = PC_Type;
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

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getPC_Creator() {
                return PC_Creator;
            }

            public void setPC_Creator(String PC_Creator) {
                this.PC_Creator = PC_Creator;
            }

            public String getPC_Code() {
                return PC_Code;
            }

            public void setPC_Code(String PC_Code) {
                this.PC_Code = PC_Code;
            }

            public String getPC_Name() {
                return PC_Name;
            }

            public void setPC_Name(String PC_Name) {
                this.PC_Name = PC_Name;
            }

            public double getPC_Price() {
                return PC_Price;
            }

            public void setPC_Price(double PC_Price) {
                this.PC_Price = PC_Price;
            }

            public double getPC_Discount() {
                return PC_Discount;
            }

            public void setPC_Discount(double PC_Discount) {
                this.PC_Discount = PC_Discount;
            }

            public String getPC_specification() {
                return PC_specification;
            }

            public void setPC_specification(String PC_specification) {
                this.PC_specification = PC_specification;
            }

            public int getPC_Integral() {
                return PC_Integral;
            }

            public void setPC_Integral(int PC_Integral) {
                this.PC_Integral = PC_Integral;
            }

            public int getPC_SynState() {
                return PC_SynState;
            }

            public void setPC_SynState(int PC_SynState) {
                this.PC_SynState = PC_SynState;
            }

            public String getPC_Remark() {
                return PC_Remark;
            }

            public void setPC_Remark(String PC_Remark) {
                this.PC_Remark = PC_Remark;
            }

            public String getPC_CreateTime() {
                return PC_CreateTime;
            }

            public void setPC_CreateTime(String PC_CreateTime) {
                this.PC_CreateTime = PC_CreateTime;
            }

            public String getPC_Image() {
                return PC_Image;
            }

            public void setPC_Image(String PC_Image) {
                this.PC_Image = PC_Image;
            }

            public String getPC_ProductJson() {
                return PC_ProductJson;
            }

            public void setPC_ProductJson(String PC_ProductJson) {
                this.PC_ProductJson = PC_ProductJson;
            }

            public int getPC_DeleteSign() {
                return PC_DeleteSign;
            }

            public void setPC_DeleteSign(int PC_DeleteSign) {
                this.PC_DeleteSign = PC_DeleteSign;
            }

            public Object getPC_ProductJsonList() {
                return PC_ProductJsonList;
            }

            public void setPC_ProductJsonList(Object PC_ProductJsonList) {
                this.PC_ProductJsonList = PC_ProductJsonList;
            }

            public Object getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(Object SM_Name) {
                this.SM_Name = SM_Name;
            }
        }
    }
}
