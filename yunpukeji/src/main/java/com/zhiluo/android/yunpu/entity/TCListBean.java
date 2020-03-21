package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/26 13:47.
 * 邮箱：137615198@qq.com
 */

public class TCListBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":2,"PageIndex":1,"DataList":[{"GID":"c4b01fec-392d-472a-9fc7-52abc67a108d","SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","CY_GID":"01a5ab8f-46b1-41c4-b089-0a73230ff9da","PC_Creator":"1735863857","PC_Code":"TC001","PC_Name":"套餐一","PC_Price":100,"PC_Discount":0.9,"PC_specification":"","PC_Integral":0,"PC_SynState":0,"PC_Remark":"","PC_CreateTime":"2018-01-26 13:40:42","PC_Image":"/img/goods.png","PC_ProductJson":"[{\"GID\":\"9dd2f746-d0a2-4ac7-8b9c-a0836637eb82\",\"PM_Name\":\"洗发水250ML\",\"PM_Number\":\"2\"},{\"GID\":\"43bff9ae-1652-4978-aa19-335cf1938794\",\"PM_Name\":\"洗发水100ML\",\"PM_Number\":\"3\"}]","PC_DeleteSign":0,"PC_ProductJsonList":null,"SM_Name":null},{"GID":"ecbf6588-f309-48bf-ba29-fe56df74a98a","SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","CY_GID":"01a5ab8f-46b1-41c4-b089-0a73230ff9da","PC_Creator":"1735863857","PC_Code":"TC002","PC_Name":"套餐二","PC_Price":150,"PC_Discount":0.8,"PC_specification":"规格","PC_Integral":120,"PC_SynState":0,"PC_Remark":"","PC_CreateTime":"2018-01-26 13:42:28","PC_Image":"/img/goods.png","PC_ProductJson":"[{\"GID\":\"eb81af1e-772e-4383-af43-b50d4d0ed31e\",\"PM_Name\":\"唯一\",\"PM_Number\":\"1\"},{\"GID\":\"9dd2f746-d0a2-4ac7-8b9c-a0836637eb82\",\"PM_Name\":\"洗发水250ML\",\"PM_Number\":\"3\"},{\"GID\":\"43bff9ae-1652-4978-aa19-335cf1938794\",\"PM_Name\":\"洗发水100ML\",\"PM_Number\":\"2\"}]","PC_DeleteSign":0,"PC_ProductJsonList":null,"SM_Name":null}],"StatisticsInfo":null}
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
        private static final long UID = 1L;
        /**
         * PageTotal : 1
         * PageSize : 20
         * DataCount : 2
         * PageIndex : 1
         * DataList : [{"GID":"c4b01fec-392d-472a-9fc7-52abc67a108d","SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","CY_GID":"01a5ab8f-46b1-41c4-b089-0a73230ff9da","PC_Creator":"1735863857","PC_Code":"TC001","PC_Name":"套餐一","PC_Price":100,"PC_Discount":0.9,"PC_specification":"","PC_Integral":0,"PC_SynState":0,"PC_Remark":"","PC_CreateTime":"2018-01-26 13:40:42","PC_Image":"/img/goods.png","PC_ProductJson":"[{\"GID\":\"9dd2f746-d0a2-4ac7-8b9c-a0836637eb82\",\"PM_Name\":\"洗发水250ML\",\"PM_Number\":\"2\"},{\"GID\":\"43bff9ae-1652-4978-aa19-335cf1938794\",\"PM_Name\":\"洗发水100ML\",\"PM_Number\":\"3\"}]","PC_DeleteSign":0,"PC_ProductJsonList":null,"SM_Name":null},{"GID":"ecbf6588-f309-48bf-ba29-fe56df74a98a","SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","CY_GID":"01a5ab8f-46b1-41c4-b089-0a73230ff9da","PC_Creator":"1735863857","PC_Code":"TC002","PC_Name":"套餐二","PC_Price":150,"PC_Discount":0.8,"PC_specification":"规格","PC_Integral":120,"PC_SynState":0,"PC_Remark":"","PC_CreateTime":"2018-01-26 13:42:28","PC_Image":"/img/goods.png","PC_ProductJson":"[{\"GID\":\"eb81af1e-772e-4383-af43-b50d4d0ed31e\",\"PM_Name\":\"唯一\",\"PM_Number\":\"1\"},{\"GID\":\"9dd2f746-d0a2-4ac7-8b9c-a0836637eb82\",\"PM_Name\":\"洗发水250ML\",\"PM_Number\":\"3\"},{\"GID\":\"43bff9ae-1652-4978-aa19-335cf1938794\",\"PM_Name\":\"洗发水100ML\",\"PM_Number\":\"2\"}]","PC_DeleteSign":0,"PC_ProductJsonList":null,"SM_Name":null}]
         * StatisticsInfo : null
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private Object StatisticsInfo;
        private List<TCBean> DataList;

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

        public List<TCBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<TCBean> DataList) {
            this.DataList = DataList;
        }


    }
}
