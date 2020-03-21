package com.zhiluo.android.yunpu.statistics.order.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/11 17:25.
 * 邮箱：137615198@qq.com
 */

public class PointExchangeReportBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":3,"PageSize":20,"DataCount":52,"PageIndex":1,"DataList":[{"GID":"435160f5-7abd-46c6-8d89-3b00b82be5fe","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708311737563094","EGD_DateTime":"2017-08-31 17:38:02","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":711,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-31 17:38:02","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"dd82b500-e558-43ed-95bc-3c0d237b1361","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708311719303972","EGD_DateTime":"2017-08-31 17:19:49","IEG_Number":1,"IEG_Score":300,"IEG_RemainingPoints":731,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-31 17:19:49","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"283f7148-742f-49ab-8902-306efa2ac6d3","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251204553968","EGD_DateTime":"2017-08-25 12:05:09","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":10864.8,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 12:05:09","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"8dcaf0db-b5ba-4e30-b393-8f4e0daaaea7","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251203403753","EGD_DateTime":"2017-08-25 12:04:05","IEG_Number":2,"IEG_Score":100,"IEG_RemainingPoints":10884.8,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 12:04:05","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"7023234f-960a-47b9-9519-86b20faaccd4","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251203007182","EGD_DateTime":"2017-08-25 12:03:11","IEG_Number":1,"IEG_Score":50,"IEG_RemainingPoints":10984.8,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 12:03:11","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"7eafc5ca-599b-478a-921f-244fd1d826dd","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251159483026","EGD_DateTime":"2017-08-25 12:00:00","IEG_Number":1,"IEG_Score":50,"IEG_RemainingPoints":11034.8,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 12:00:00","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"f8384d1b-2c1b-42ad-bfed-3a73adf0a47f","VIP_GID":"568f3856-d558-457b-a012-ea07cf46cda8","VIP_Card":"9003","VIP_Name":"悟空","VIP_Phone":"15784584845","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251155495674","EGD_DateTime":"2017-08-25 11:55:59","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":2553,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 11:55:59","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"05bb03bc-d3d6-457b-977e-0b00cb4665e2","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251153123034","EGD_DateTime":"2017-08-25 11:53:20","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":29,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 11:53:20","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"4abdb549-4363-413c-b998-38d3813bee48","VIP_GID":"be7fd660-54af-4ee6-9c55-5e14e7d5af4d","VIP_Card":"9005","VIP_Name":"余文乐","VIP_Phone":"18631214444","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251110554435","EGD_DateTime":"2017-08-25 11:11:06","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":195.9,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 11:11:06","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"4e9e6345-7a17-4c89-a6eb-8845875a1050","VIP_GID":"54636ddd-18d6-480c-80fe-3bbe4c368b4e","VIP_Card":"9008","VIP_Name":"林心如","VIP_Phone":"15889658865","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251105331174","EGD_DateTime":"2017-08-25 11:05:46","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":81,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 11:05:46","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"b2fa02b6-ebc5-443a-9357-4a67d66ba555","VIP_GID":"be7fd660-54af-4ee6-9c55-5e14e7d5af4d","VIP_Card":"9005","VIP_Name":"余文乐","VIP_Phone":"18631214444","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251103462898","EGD_DateTime":"2017-08-25 11:03:57","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":215.9,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 11:03:57","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"32cd4129-1cf7-48a4-973c-cf9ef7231455","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251028446003","EGD_DateTime":"2017-08-25 10:28:56","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":41,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 10:28:56","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"16b9ec71-6fd1-4348-916d-8d3ef3663f88","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251025116604","EGD_DateTime":"2017-08-25 10:25:19","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":61,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 10:25:19","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"bcb5282e-8e87-4c5a-94de-ad4f284dde85","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708231105574400","EGD_DateTime":"2017-08-23 11:06:16","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":81,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-23 11:06:16","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"415ccbaa-c169-407a-97d8-76668cbd972b","VIP_GID":"d77e6e63-7a72-47d2-b218-3e35a34e01ce","VIP_Card":"9006","VIP_Name":"何玲","VIP_Phone":"17854318848","VIP_FaceNumber":"","IEG_OrderNo":"LP1708221833015508","EGD_DateTime":"2017-08-22 18:33:17","IEG_Number":2,"IEG_Score":400,"IEG_RemainingPoints":12,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-22 18:33:17","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"3827f17f-b29b-4e97-8a44-61a9d0503fb9","VIP_GID":"be7fd660-54af-4ee6-9c55-5e14e7d5af4d","VIP_Card":"9005","VIP_Name":"余文乐","VIP_Phone":"18631214444","VIP_FaceNumber":"","IEG_OrderNo":"LP1708221830126104","EGD_DateTime":"2017-08-22 18:30:35","IEG_Number":5,"IEG_Score":190,"IEG_RemainingPoints":235.9,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-22 18:30:35","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"9616f0f6-db6e-45ca-8cc5-7dd03f10e1c5","VIP_GID":"96b3f41d-f214-4557-bdcb-e30e49068ccf","VIP_Card":"90009","VIP_Name":"刘备","VIP_Phone":"15785855588","VIP_FaceNumber":"","IEG_OrderNo":"LP1708211539371969","EGD_DateTime":"2017-08-21 15:40:00","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":10,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-21 15:40:00","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"a074530f-ef8b-40f7-9153-0e5b636fb6c2","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708211533415989","EGD_DateTime":"2017-08-21 15:35:25","IEG_Number":1,"IEG_Score":300,"IEG_RemainingPoints":6604.4,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-21 15:35:25","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"3b81e030-8582-4fbe-8f34-aa32e5f23697","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708211533415989","EGD_DateTime":"2017-08-21 15:34:18","IEG_Number":4,"IEG_Score":1200,"IEG_RemainingPoints":6974.4,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-21 15:34:18","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"e2e7595f-ac8c-4c87-bdd8-e02b6c14d3db","VIP_GID":"568f3856-d558-457b-a012-ea07cf46cda8","VIP_Card":"9003","VIP_Name":"悟空","VIP_Phone":"15784584845","VIP_FaceNumber":"","IEG_OrderNo":"LP1708211533415989","EGD_DateTime":"2017-08-21 15:33:52","IEG_Number":2,"IEG_Score":70,"IEG_RemainingPoints":-70,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-21 15:33:52","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1}],"StatisticsInfo":{"SA_ExchangedScore":"8620.00","SA_RemainingScore":"142878.38"}}
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
         * PageTotal : 3
         * PageSize : 20
         * DataCount : 52
         * PageIndex : 1
         * DataList : [{"GID":"435160f5-7abd-46c6-8d89-3b00b82be5fe","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708311737563094","EGD_DateTime":"2017-08-31 17:38:02","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":711,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-31 17:38:02","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"dd82b500-e558-43ed-95bc-3c0d237b1361","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708311719303972","EGD_DateTime":"2017-08-31 17:19:49","IEG_Number":1,"IEG_Score":300,"IEG_RemainingPoints":731,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-31 17:19:49","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"283f7148-742f-49ab-8902-306efa2ac6d3","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251204553968","EGD_DateTime":"2017-08-25 12:05:09","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":10864.8,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 12:05:09","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"8dcaf0db-b5ba-4e30-b393-8f4e0daaaea7","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251203403753","EGD_DateTime":"2017-08-25 12:04:05","IEG_Number":2,"IEG_Score":100,"IEG_RemainingPoints":10884.8,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 12:04:05","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"7023234f-960a-47b9-9519-86b20faaccd4","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251203007182","EGD_DateTime":"2017-08-25 12:03:11","IEG_Number":1,"IEG_Score":50,"IEG_RemainingPoints":10984.8,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 12:03:11","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"7eafc5ca-599b-478a-921f-244fd1d826dd","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251159483026","EGD_DateTime":"2017-08-25 12:00:00","IEG_Number":1,"IEG_Score":50,"IEG_RemainingPoints":11034.8,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 12:00:00","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"f8384d1b-2c1b-42ad-bfed-3a73adf0a47f","VIP_GID":"568f3856-d558-457b-a012-ea07cf46cda8","VIP_Card":"9003","VIP_Name":"悟空","VIP_Phone":"15784584845","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251155495674","EGD_DateTime":"2017-08-25 11:55:59","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":2553,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 11:55:59","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"05bb03bc-d3d6-457b-977e-0b00cb4665e2","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251153123034","EGD_DateTime":"2017-08-25 11:53:20","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":29,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 11:53:20","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"4abdb549-4363-413c-b998-38d3813bee48","VIP_GID":"be7fd660-54af-4ee6-9c55-5e14e7d5af4d","VIP_Card":"9005","VIP_Name":"余文乐","VIP_Phone":"18631214444","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251110554435","EGD_DateTime":"2017-08-25 11:11:06","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":195.9,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 11:11:06","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"4e9e6345-7a17-4c89-a6eb-8845875a1050","VIP_GID":"54636ddd-18d6-480c-80fe-3bbe4c368b4e","VIP_Card":"9008","VIP_Name":"林心如","VIP_Phone":"15889658865","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251105331174","EGD_DateTime":"2017-08-25 11:05:46","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":81,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 11:05:46","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"b2fa02b6-ebc5-443a-9357-4a67d66ba555","VIP_GID":"be7fd660-54af-4ee6-9c55-5e14e7d5af4d","VIP_Card":"9005","VIP_Name":"余文乐","VIP_Phone":"18631214444","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251103462898","EGD_DateTime":"2017-08-25 11:03:57","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":215.9,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 11:03:57","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"32cd4129-1cf7-48a4-973c-cf9ef7231455","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251028446003","EGD_DateTime":"2017-08-25 10:28:56","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":41,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 10:28:56","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"16b9ec71-6fd1-4348-916d-8d3ef3663f88","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708251025116604","EGD_DateTime":"2017-08-25 10:25:19","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":61,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-25 10:25:19","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"bcb5282e-8e87-4c5a-94de-ad4f284dde85","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","VIP_Card":"6785","VIP_Name":"张合","VIP_Phone":"18762525266","VIP_FaceNumber":"","IEG_OrderNo":"LP1708231105574400","EGD_DateTime":"2017-08-23 11:06:16","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":81,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-23 11:06:16","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"415ccbaa-c169-407a-97d8-76668cbd972b","VIP_GID":"d77e6e63-7a72-47d2-b218-3e35a34e01ce","VIP_Card":"9006","VIP_Name":"何玲","VIP_Phone":"17854318848","VIP_FaceNumber":"","IEG_OrderNo":"LP1708221833015508","EGD_DateTime":"2017-08-22 18:33:17","IEG_Number":2,"IEG_Score":400,"IEG_RemainingPoints":12,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-22 18:33:17","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"3827f17f-b29b-4e97-8a44-61a9d0503fb9","VIP_GID":"be7fd660-54af-4ee6-9c55-5e14e7d5af4d","VIP_Card":"9005","VIP_Name":"余文乐","VIP_Phone":"18631214444","VIP_FaceNumber":"","IEG_OrderNo":"LP1708221830126104","EGD_DateTime":"2017-08-22 18:30:35","IEG_Number":5,"IEG_Score":190,"IEG_RemainingPoints":235.9,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-22 18:30:35","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"9616f0f6-db6e-45ca-8cc5-7dd03f10e1c5","VIP_GID":"96b3f41d-f214-4557-bdcb-e30e49068ccf","VIP_Card":"90009","VIP_Name":"刘备","VIP_Phone":"15785855588","VIP_FaceNumber":"","IEG_OrderNo":"LP1708211539371969","EGD_DateTime":"2017-08-21 15:40:00","IEG_Number":1,"IEG_Score":20,"IEG_RemainingPoints":10,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-21 15:40:00","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"a074530f-ef8b-40f7-9153-0e5b636fb6c2","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708211533415989","EGD_DateTime":"2017-08-21 15:35:25","IEG_Number":1,"IEG_Score":300,"IEG_RemainingPoints":6604.4,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-21 15:35:25","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"3b81e030-8582-4fbe-8f34-aa32e5f23697","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","IEG_OrderNo":"LP1708211533415989","EGD_DateTime":"2017-08-21 15:34:18","IEG_Number":4,"IEG_Score":1200,"IEG_RemainingPoints":6974.4,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-21 15:34:18","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1},{"GID":"e2e7595f-ac8c-4c87-bdd8-e02b6c14d3db","VIP_GID":"568f3856-d558-457b-a012-ea07cf46cda8","VIP_Card":"9003","VIP_Name":"悟空","VIP_Phone":"15784584845","VIP_FaceNumber":"","IEG_OrderNo":"LP1708211533415989","EGD_DateTime":"2017-08-21 15:33:52","IEG_Number":2,"IEG_Score":70,"IEG_RemainingPoints":-70,"IEG_Remark":null,"IEG_UpdateTime":"2017-08-21 15:33:52","IEG_Creator":"1540004275@qq.com","CY_GID":null,"SM_GID":null,"SM_Name":"云上铺","GiftsDetail":null,"IS_Sms":false,"IEG_Device":1}]
         * StatisticsInfo : {"SA_ExchangedScore":"8620.00","SA_RemainingScore":"142878.38"}
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private StatisticsInfoBean StatisticsInfo;
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

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class StatisticsInfoBean {
            /**
             * SA_ExchangedScore : 8620.00
             * SA_RemainingScore : 142878.38
             */

            private String SA_ExchangedScore;
            private String SA_RemainingScore;

            public String getSA_ExchangedScore() {
                return SA_ExchangedScore;
            }

            public void setSA_ExchangedScore(String SA_ExchangedScore) {
                this.SA_ExchangedScore = SA_ExchangedScore;
            }

            public String getSA_RemainingScore() {
                return SA_RemainingScore;
            }

            public void setSA_RemainingScore(String SA_RemainingScore) {
                this.SA_RemainingScore = SA_RemainingScore;
            }
        }

        public static class DataListBean implements Serializable {
            /**
             * GID : 435160f5-7abd-46c6-8d89-3b00b82be5fe
             * VIP_GID : 8c56319b-3c29-4b88-9d2e-85ddbc6437ed
             * VIP_Card : 6785
             * VIP_Name : 张合
             * VIP_Phone : 18762525266
             * VIP_FaceNumber :
             * IEG_OrderNo : LP1708311737563094
             * EGD_DateTime : 2017-08-31 17:38:02
             * IEG_Number : 1
             * IEG_Score : 20.0
             * IEG_RemainingPoints : 711.0
             * IEG_Remark : null
             * IEG_UpdateTime : 2017-08-31 17:38:02
             * IEG_Creator : 1540004275@qq.com
             * CY_GID : null
             * SM_GID : null
             * SM_Name : 云上铺
             * GiftsDetail : null
             * IS_Sms : false
             * IEG_Device : 1
             */

            private String GID;
            private String VIP_GID;
            private String VIP_Card;
            private String VIP_Name;
            private String VIP_Phone;
            private String VIP_FaceNumber;
            private String IEG_OrderNo;
            private String EGD_DateTime;
            private int IEG_Number;
            private double IEG_Score;
            private double IEG_RemainingPoints;
            private String IEG_Remark;
            private String IEG_UpdateTime;
            private String IEG_Creator;
            private Object CY_GID;
            private Object SM_GID;
            private String SM_Name;
            private Object GiftsDetail;
            private boolean IS_Sms;
            private int IEG_Device;

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
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

            public String getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(String VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public String getIEG_OrderNo() {
                return IEG_OrderNo;
            }

            public void setIEG_OrderNo(String IEG_OrderNo) {
                this.IEG_OrderNo = IEG_OrderNo;
            }

            public String getEGD_DateTime() {
                return EGD_DateTime;
            }

            public void setEGD_DateTime(String EGD_DateTime) {
                this.EGD_DateTime = EGD_DateTime;
            }

            public int getIEG_Number() {
                return IEG_Number;
            }

            public void setIEG_Number(int IEG_Number) {
                this.IEG_Number = IEG_Number;
            }

            public double getIEG_Score() {
                return IEG_Score;
            }

            public void setIEG_Score(double IEG_Score) {
                this.IEG_Score = IEG_Score;
            }

            public double getIEG_RemainingPoints() {
                return IEG_RemainingPoints;
            }

            public void setIEG_RemainingPoints(double IEG_RemainingPoints) {
                this.IEG_RemainingPoints = IEG_RemainingPoints;
            }

            public String getIEG_Remark() {
                return IEG_Remark;
            }

            public void setIEG_Remark(String IEG_Remark) {
                this.IEG_Remark = IEG_Remark;
            }

            public String getIEG_UpdateTime() {
                return IEG_UpdateTime;
            }

            public void setIEG_UpdateTime(String IEG_UpdateTime) {
                this.IEG_UpdateTime = IEG_UpdateTime;
            }

            public String getIEG_Creator() {
                return IEG_Creator;
            }

            public void setIEG_Creator(String IEG_Creator) {
                this.IEG_Creator = IEG_Creator;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }

            public Object getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(Object SM_GID) {
                this.SM_GID = SM_GID;
            }

            public String getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(String SM_Name) {
                this.SM_Name = SM_Name;
            }

            public Object getGiftsDetail() {
                return GiftsDetail;
            }

            public void setGiftsDetail(Object GiftsDetail) {
                this.GiftsDetail = GiftsDetail;
            }

            public boolean isIS_Sms() {
                return IS_Sms;
            }

            public void setIS_Sms(boolean IS_Sms) {
                this.IS_Sms = IS_Sms;
            }

            public int getIEG_Device() {
                return IEG_Device;
            }

            public void setIEG_Device(int IEG_Device) {
                this.IEG_Device = IEG_Device;
            }
        }
    }
}
