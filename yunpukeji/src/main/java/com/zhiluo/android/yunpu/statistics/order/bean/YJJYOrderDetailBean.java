package com.zhiluo.android.yunpu.statistics.order.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2019-07-03.
 */

public class YJJYOrderDetailBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":10,"PageIndex":1,"DataList":[{"GID":"816bba1a-cb13-4a50-ae8e-00102b55006c","CO_OrderCode":"JY1907031550091940","OM_GID":"cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8","OM_Name":"#96","CO_Number":16.67,"CO_Price":6,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":0,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"现金(100.00)","CO_PayCode":"XJZF","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":2,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 15:49:58","dataCount":null},{"GID":"f6d82d14-a793-46bc-8f83-b58f92ed2da3","CO_OrderCode":"JY1907031546588424","OM_GID":"cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8","OM_Name":"#96","CO_Number":16.67,"CO_Price":6,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"现金(100)","CO_PayCode":"XJZF","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":2,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 15:49:58","dataCount":null},{"GID":"1e6e20dc-fc02-4f06-b9f0-fed7d60075e6","CO_OrderCode":"JY1907031503318956","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"微信(100)","CO_PayCode":"WX_JZ","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 15:03:31","dataCount":null},{"GID":"5fab858f-dae6-4ae4-8550-3b9e9f911c7b","CO_OrderCode":"JY1907031350059589","OM_GID":"cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8","OM_Name":"#96","CO_Number":16.67,"CO_Price":6,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"微信(100)","CO_PayCode":"WX_JZ","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 13:50:05","dataCount":null},{"GID":"f8ff8698-e73b-4d92-a9ef-d325121ed695","CO_OrderCode":"JY1907031349103380","OM_GID":"cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8","OM_Name":"#96","CO_Number":16.67,"CO_Price":6,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"银联(100)","CO_PayCode":"YLZF","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 13:49:10","dataCount":null},{"GID":"d9cb6dd9-4ec2-48d2-8655-ae50c9d8fa2c","CO_OrderCode":"JY1907031028282704","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"CO_InPrice":null,"CO_SSMoney":null,"CO_ZLMoney":null,"VIP_FaceNumber":null,"VIP_Balance":null,"CO_Integral":null,"CO_PayWay":null,"CO_PayCode":null,"CO_Identifying":"已下单","CO_IdentifyingState":-1,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 10:28:28","dataCount":null},{"GID":"d4c3ec0b-0297-4152-92d5-eb7a9f39f617","CO_OrderCode":"JY1907031022371428","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":null,"CO_Integral":null,"CO_PayWay":"微信(100)","CO_PayCode":"WX_JZ","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 10:22:37","dataCount":null},{"GID":"4fe3ad00-94f4-4d9d-9863-79be77196aa3","CO_OrderCode":"JY1907031020213315","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":null,"CO_Integral":null,"CO_PayWay":"微信(100)","CO_PayCode":"WX_JZ","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 10:20:21","dataCount":null},{"GID":"78ab9404-3a60-4b66-b617-1bc2aab27c31","CO_OrderCode":"JY1907031019087642","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":null,"CO_Integral":null,"CO_PayWay":"微信(100)","CO_PayCode":"WX_JZ","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 10:19:08","dataCount":null},{"GID":"a12f98ee-8f58-4d9d-a233-d44a339cb686","CO_OrderCode":"JY1907031018165644","OM_GID":"cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8","OM_Name":"#96","CO_Number":16.67,"CO_Price":6,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":null,"CO_Integral":null,"CO_PayWay":"银联(100)","CO_PayCode":"YLZF","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 10:18:16","dataCount":null}],"StatisticsInfo":{"OrderNumber":154.8,"OrderAmount":1000},"TrendData":null}
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
         * PageTotal : 1
         * PageSize : 20
         * DataCount : 10
         * PageIndex : 1
         * DataList : [{"GID":"816bba1a-cb13-4a50-ae8e-00102b55006c","CO_OrderCode":"JY1907031550091940","OM_GID":"cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8","OM_Name":"#96","CO_Number":16.67,"CO_Price":6,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":0,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"现金(100.00)","CO_PayCode":"XJZF","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":2,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 15:49:58","dataCount":null},{"GID":"f6d82d14-a793-46bc-8f83-b58f92ed2da3","CO_OrderCode":"JY1907031546588424","OM_GID":"cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8","OM_Name":"#96","CO_Number":16.67,"CO_Price":6,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"现金(100)","CO_PayCode":"XJZF","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":2,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 15:49:58","dataCount":null},{"GID":"1e6e20dc-fc02-4f06-b9f0-fed7d60075e6","CO_OrderCode":"JY1907031503318956","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"微信(100)","CO_PayCode":"WX_JZ","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 15:03:31","dataCount":null},{"GID":"5fab858f-dae6-4ae4-8550-3b9e9f911c7b","CO_OrderCode":"JY1907031350059589","OM_GID":"cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8","OM_Name":"#96","CO_Number":16.67,"CO_Price":6,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"微信(100)","CO_PayCode":"WX_JZ","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 13:50:05","dataCount":null},{"GID":"f8ff8698-e73b-4d92-a9ef-d325121ed695","CO_OrderCode":"JY1907031349103380","OM_GID":"cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8","OM_Name":"#96","CO_Number":16.67,"CO_Price":6,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":0,"CO_Integral":12.5,"CO_PayWay":"银联(100)","CO_PayCode":"YLZF","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 13:49:10","dataCount":null},{"GID":"d9cb6dd9-4ec2-48d2-8655-ae50c9d8fa2c","CO_OrderCode":"JY1907031028282704","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"CO_InPrice":null,"CO_SSMoney":null,"CO_ZLMoney":null,"VIP_FaceNumber":null,"VIP_Balance":null,"CO_Integral":null,"CO_PayWay":null,"CO_PayCode":null,"CO_Identifying":"已下单","CO_IdentifyingState":-1,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 10:28:28","dataCount":null},{"GID":"d4c3ec0b-0297-4152-92d5-eb7a9f39f617","CO_OrderCode":"JY1907031022371428","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":null,"CO_Integral":null,"CO_PayWay":"微信(100)","CO_PayCode":"WX_JZ","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 10:22:37","dataCount":null},{"GID":"4fe3ad00-94f4-4d9d-9863-79be77196aa3","CO_OrderCode":"JY1907031020213315","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":null,"CO_Integral":null,"CO_PayWay":"微信(100)","CO_PayCode":"WX_JZ","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 10:20:21","dataCount":null},{"GID":"78ab9404-3a60-4b66-b617-1bc2aab27c31","CO_OrderCode":"JY1907031019087642","OM_GID":"284a406b-ccd5-43e1-90ed-1217764134b0","OM_Name":"646","CO_Number":14.29,"CO_Price":7,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":null,"CO_Integral":null,"CO_PayWay":"微信(100)","CO_PayCode":"WX_JZ","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 10:19:08","dataCount":null},{"GID":"a12f98ee-8f58-4d9d-a233-d44a339cb686","CO_OrderCode":"JY1907031018165644","OM_GID":"cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8","OM_Name":"#96","CO_Number":16.67,"CO_Price":6,"CO_OrderAmount":100,"VIP_GID":"198b3c1a-15f9-4d49-94c3-18b9c4dcc90c","VIP_Card":"8846546616","VIP_Name":"sada541","VIP_Phone":"18265665626","CO_InPrice":null,"CO_SSMoney":100,"CO_ZLMoney":0,"VIP_FaceNumber":"65+562223","VIP_Balance":null,"CO_Integral":null,"CO_PayWay":"银联(100)","CO_PayCode":"YLZF","CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_Device":1,"CO_Creator":"销售001","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll","CO_CreateTime":"2019-07-03 10:18:16","dataCount":null}]
         * StatisticsInfo : {"OrderNumber":154.8,"OrderAmount":1000}
         * TrendData : null
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private StatisticsInfoBean StatisticsInfo;
        private Object TrendData;
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

        public Object getTrendData() {
            return TrendData;
        }

        public void setTrendData(Object TrendData) {
            this.TrendData = TrendData;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class StatisticsInfoBean implements Serializable{
            /**
             * OrderNumber : 154.8
             * OrderAmount : 1000.0
             */

            private double OrderNumber;
            private double OrderAmount;

            public double getOrderNumber() {
                return OrderNumber;
            }

            public void setOrderNumber(double OrderNumber) {
                this.OrderNumber = OrderNumber;
            }

            public double getOrderAmount() {
                return OrderAmount;
            }

            public void setOrderAmount(double OrderAmount) {
                this.OrderAmount = OrderAmount;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * GID : 816bba1a-cb13-4a50-ae8e-00102b55006c
             * CO_OrderCode : JY1907031550091940
             * OM_GID : cd0097cc-62ad-4d41-bfc0-5e7a0e3e9cb8
             * OM_Name : #96
             * CO_Number : 16.67
             * CO_Price : 6.0
             * CO_OrderAmount : 100.0
             * VIP_GID : 198b3c1a-15f9-4d49-94c3-18b9c4dcc90c
             * VIP_Card : 8846546616
             * VIP_Name : sada541
             * VIP_Phone : 18265665626
             * CO_InPrice : null
             * CO_SSMoney : 0.0
             * CO_ZLMoney : 0.0
             * VIP_FaceNumber : 65+562223
             * VIP_Balance : 0.0
             * CO_Integral : 12.5
             * CO_PayWay : 现金(100.00)
             * CO_PayCode : XJZF
             * CO_Identifying : 已完成
             * CO_IdentifyingState : 99
             * CO_Device : 2
             * CO_Creator : 销售001
             * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * SM_Name : jll
             * CO_CreateTime : 2019-07-03 15:49:58
             * dataCount : null
             */

            private String GID;
            private String CO_OrderCode;
            private String OM_GID;
            private String OM_Name;
            private double CO_Number;
            private double CO_Price;
            private double CO_OrderAmount;
            private String VIP_GID;
            private String VIP_Card;
            private String VIP_Name;
            private String VIP_Phone;
            private Object CO_InPrice;
            private double CO_SSMoney;
            private double CO_ZLMoney;
            private String VIP_FaceNumber;
            private double VIP_Balance;
            private double CO_Integral;
            private String CO_PayWay;
            private String CO_PayCode;
            private String CO_Identifying;
            private int CO_IdentifyingState;
            private int CO_Device;
            private String CO_Creator;
            private String CY_GID;
            private String SM_GID;
            private String SM_Name;
            private String CO_CreateTime;
            private Object dataCount;

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getCO_OrderCode() {
                return CO_OrderCode;
            }

            public void setCO_OrderCode(String CO_OrderCode) {
                this.CO_OrderCode = CO_OrderCode;
            }

            public String getOM_GID() {
                return OM_GID;
            }

            public void setOM_GID(String OM_GID) {
                this.OM_GID = OM_GID;
            }

            public String getOM_Name() {
                return OM_Name;
            }

            public void setOM_Name(String OM_Name) {
                this.OM_Name = OM_Name;
            }

            public double getCO_Number() {
                return CO_Number;
            }

            public void setCO_Number(double CO_Number) {
                this.CO_Number = CO_Number;
            }

            public double getCO_Price() {
                return CO_Price;
            }

            public void setCO_Price(double CO_Price) {
                this.CO_Price = CO_Price;
            }

            public double getCO_OrderAmount() {
                return CO_OrderAmount;
            }

            public void setCO_OrderAmount(double CO_OrderAmount) {
                this.CO_OrderAmount = CO_OrderAmount;
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

            public Object getCO_InPrice() {
                return CO_InPrice;
            }

            public void setCO_InPrice(Object CO_InPrice) {
                this.CO_InPrice = CO_InPrice;
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

            public String getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(String VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public double getVIP_Balance() {
                return VIP_Balance;
            }

            public void setVIP_Balance(double VIP_Balance) {
                this.VIP_Balance = VIP_Balance;
            }

            public double getCO_Integral() {
                return CO_Integral;
            }

            public void setCO_Integral(double CO_Integral) {
                this.CO_Integral = CO_Integral;
            }

            public String getCO_PayWay() {
                return CO_PayWay;
            }

            public void setCO_PayWay(String CO_PayWay) {
                this.CO_PayWay = CO_PayWay;
            }

            public String getCO_PayCode() {
                return CO_PayCode;
            }

            public void setCO_PayCode(String CO_PayCode) {
                this.CO_PayCode = CO_PayCode;
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

            public int getCO_Device() {
                return CO_Device;
            }

            public void setCO_Device(int CO_Device) {
                this.CO_Device = CO_Device;
            }

            public String getCO_Creator() {
                return CO_Creator;
            }

            public void setCO_Creator(String CO_Creator) {
                this.CO_Creator = CO_Creator;
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

            public String getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(String SM_Name) {
                this.SM_Name = SM_Name;
            }

            public String getCO_CreateTime() {
                return CO_CreateTime;
            }

            public void setCO_CreateTime(String CO_CreateTime) {
                this.CO_CreateTime = CO_CreateTime;
            }

            public Object getDataCount() {
                return dataCount;
            }

            public void setDataCount(Object dataCount) {
                this.dataCount = dataCount;
            }
        }
    }
}
