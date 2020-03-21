package com.zhiluo.android.yunpu.statistics.order.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/11 16:18.
 * 邮箱：137615198@qq.com
 */

public class TimesConsumeReportBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":2,"PageSize":20,"DataCount":23,"PageIndex":1,"DataList":[{"GID":"8362ba9e-af40-4c9f-9380-d31a5c53d974","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708251159343093","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-25 11:59:38","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"477260e7-a91a-4260-a143-1d379862bb66","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708251158139668","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-25 11:58:16","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"aac31f41-caef-4cd1-886a-5675b8c50455","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708221750199899","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-22 17:50:24","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"43a85a8f-475b-48e3-9790-55df0d903e04","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708221745147897","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-22 17:45:21","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"be4566e9-7353-4d7f-9c10-48c1b76cb31f","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708151609142946","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-15 16:09:18","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"5fffb030-6191-42cb-a1a6-8878b8041e0b","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708150948296013","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-15 09:48:38","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"0"},{"GID":"8eb7044a-54ea-4019-b5c8-278e7e1824e8","VIP_GID":"6e8aff9c-f83a-4f0f-82da-2706b7b82b1b","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"WO_OrderCode":"JC1708010934046295","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-01 09:34:35","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"老王","SM_Phone":"18800000001","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"04ae2d3c-f7fd-445b-b9f9-b5811817375c","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1707131646406949","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-07-13 16:46:44","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"c1251500-79f8-4efd-b3f8-64efeb94c752","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1707121347079146","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-07-12 13:47:12","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"f32162d3-673f-4d29-abaf-749416bc13cb","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1707111711164551","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-07-11 17:11:21","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"29a87859-d9c6-406b-896a-c73f74526afa","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1707061033381324","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-07-06 10:33:45","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"0"},{"GID":"12d2eaf3-e9cb-4907-b7b1-b8f9f27953a0","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1707061033275532","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-07-06 10:33:33","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"0"},{"GID":"8ec1ab70-ce65-41a4-a9e5-f8d8a1cb6539","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706291530308605","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-29 15:30:42","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"258538f9-418d-41bf-8ffe-80767b0eb008","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706291528545821","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-29 15:29:01","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"ec4cb680-b55c-428a-983e-c5d18364e083","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706261443377657","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-26 14:43:41","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"70e0ef13-00dd-4e13-a5ae-4ef1756d5440","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706151501134027","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-15 15:01:25","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"3290e7dc-2cf2-4961-9f1f-937d0471b9ae","VIP_GID":"6362cbfc-d264-4a6e-850b-b764548ad197","VIP_Card":"002","VIP_Name":"李斯","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706141105506137","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-14 11:05:57","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"4069215c-a177-4825-bc98-33d85cbc1b82","VIP_GID":"6362cbfc-d264-4a6e-850b-b764548ad197","VIP_Card":"002","VIP_Name":"李斯","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706141048362011","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-14 10:48:40","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"c11bb3e0-26d0-481c-ac0c-9daa40ab199f","VIP_GID":"6362cbfc-d264-4a6e-850b-b764548ad197","VIP_Card":"002","VIP_Name":"李斯","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706141046302675","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-14 10:46:35","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"51f118b2-26e1-47f6-aed0-0d318922dcbc","VIP_GID":"6362cbfc-d264-4a6e-850b-b764548ad197","VIP_Card":"002","VIP_Name":"李斯","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706141045316402","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-14 10:45:47","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null}],"StatisticsInfo":null}
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
         * PageTotal : 2
         * PageSize : 20
         * DataCount : 23
         * PageIndex : 1
         * DataList : [{"GID":"8362ba9e-af40-4c9f-9380-d31a5c53d974","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708251159343093","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-25 11:59:38","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"477260e7-a91a-4260-a143-1d379862bb66","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708251158139668","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-25 11:58:16","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"aac31f41-caef-4cd1-886a-5675b8c50455","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708221750199899","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-22 17:50:24","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"43a85a8f-475b-48e3-9790-55df0d903e04","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708221745147897","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-22 17:45:21","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"be4566e9-7353-4d7f-9c10-48c1b76cb31f","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708151609142946","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-15 16:09:18","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"5fffb030-6191-42cb-a1a6-8878b8041e0b","VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","VIP_Card":"9001","VIP_Name":"Adele","VIP_Phone":"18800000001","VIP_FaceNumber":"","WO_OrderCode":"JC1708150948296013","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-15 09:48:38","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"罗","SM_Phone":"18800000000","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"0"},{"GID":"8eb7044a-54ea-4019-b5c8-278e7e1824e8","VIP_GID":"6e8aff9c-f83a-4f0f-82da-2706b7b82b1b","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"WO_OrderCode":"JC1708010934046295","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-08-01 09:34:35","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","SM_Contacter":"老王","SM_Phone":"18800000001","SM_Addr":"","wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"04ae2d3c-f7fd-445b-b9f9-b5811817375c","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1707131646406949","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-07-13 16:46:44","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"c1251500-79f8-4efd-b3f8-64efeb94c752","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1707121347079146","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-07-12 13:47:12","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"f32162d3-673f-4d29-abaf-749416bc13cb","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1707111711164551","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-07-11 17:11:21","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"1"},{"GID":"29a87859-d9c6-406b-896a-c73f74526afa","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1707061033381324","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-07-06 10:33:45","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"0"},{"GID":"12d2eaf3-e9cb-4907-b7b1-b8f9f27953a0","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1707061033275532","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-07-06 10:33:33","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":"0"},{"GID":"8ec1ab70-ce65-41a4-a9e5-f8d8a1cb6539","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706291530308605","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-29 15:30:42","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"258538f9-418d-41bf-8ffe-80767b0eb008","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706291528545821","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-29 15:29:01","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"ec4cb680-b55c-428a-983e-c5d18364e083","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706261443377657","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-26 14:43:41","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"70e0ef13-00dd-4e13-a5ae-4ef1756d5440","VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","VIP_Card":"001","VIP_Name":"赵三","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706151501134027","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-15 15:01:25","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"3290e7dc-2cf2-4961-9f1f-937d0471b9ae","VIP_GID":"6362cbfc-d264-4a6e-850b-b764548ad197","VIP_Card":"002","VIP_Name":"李斯","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706141105506137","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-14 11:05:57","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"4069215c-a177-4825-bc98-33d85cbc1b82","VIP_GID":"6362cbfc-d264-4a6e-850b-b764548ad197","VIP_Card":"002","VIP_Name":"李斯","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706141048362011","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-14 10:48:40","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"c11bb3e0-26d0-481c-ac0c-9daa40ab199f","VIP_GID":"6362cbfc-d264-4a6e-850b-b764548ad197","VIP_Card":"002","VIP_Name":"李斯","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706141046302675","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-14 10:46:35","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null},{"GID":"51f118b2-26e1-47f6-aed0-0d318922dcbc","VIP_GID":"6362cbfc-d264-4a6e-850b-b764548ad197","VIP_Card":"002","VIP_Name":"李斯","VIP_Phone":"17100000000","VIP_FaceNumber":"","WO_OrderCode":"JC1706141045316402","WO_Remark":"","WO_Creator":"1540004275@qq.com","WO_UpdateTime":"2017-06-14 10:45:47","CY_GID":null,"SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"默认店铺","SM_Contacter":"小明","SM_Phone":"18800000000","SM_Addr":null,"wouldOrderDetail":null,"IS_Sms":false,"WO_Device":null}]
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
             * GID : 8362ba9e-af40-4c9f-9380-d31a5c53d974
             * VIP_GID : dd411089-755c-409c-9b27-a6e5b7f65c4d
             * VIP_Card : 9001
             * VIP_Name : Adele
             * VIP_Phone : 18800000001
             * VIP_FaceNumber :
             * WO_OrderCode : JC1708251159343093
             * WO_Remark :
             * WO_Creator : 1540004275@qq.com
             * WO_UpdateTime : 2017-08-25 11:59:38
             * CY_GID : null
             * SM_GID : d754599d-3004-4256-b462-2d841d6f962d
             * SM_Name : 云上铺
             * SM_Contacter : 罗
             * SM_Phone : 18800000000
             * SM_Addr :
             * wouldOrderDetail : null
             * IS_Sms : false
             * WO_Device : 1
             */

            private String GID;
            private String VIP_GID;
            private String VIP_Card;
            private String VIP_Name;
            private String VIP_Phone;
            private String VIP_FaceNumber;
            private String WO_OrderCode;
            private String WO_Remark;
            private String WO_Creator;
            private String WO_UpdateTime;
            private Object CY_GID;
            private String SM_GID;
            private String SM_Name;
            private String SM_Contacter;
            private String SM_Phone;
            private String SM_Addr;
            private Object wouldOrderDetail;
            private boolean IS_Sms;
            private String WO_Device;

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

            public String getWO_OrderCode() {
                return WO_OrderCode;
            }

            public void setWO_OrderCode(String WO_OrderCode) {
                this.WO_OrderCode = WO_OrderCode;
            }

            public String getWO_Remark() {
                return WO_Remark;
            }

            public void setWO_Remark(String WO_Remark) {
                this.WO_Remark = WO_Remark;
            }

            public String getWO_Creator() {
                return WO_Creator;
            }

            public void setWO_Creator(String WO_Creator) {
                this.WO_Creator = WO_Creator;
            }

            public String getWO_UpdateTime() {
                return WO_UpdateTime;
            }

            public void setWO_UpdateTime(String WO_UpdateTime) {
                this.WO_UpdateTime = WO_UpdateTime;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
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

            public String getSM_Contacter() {
                return SM_Contacter;
            }

            public void setSM_Contacter(String SM_Contacter) {
                this.SM_Contacter = SM_Contacter;
            }

            public String getSM_Phone() {
                return SM_Phone;
            }

            public void setSM_Phone(String SM_Phone) {
                this.SM_Phone = SM_Phone;
            }

            public String getSM_Addr() {
                return SM_Addr;
            }

            public void setSM_Addr(String SM_Addr) {
                this.SM_Addr = SM_Addr;
            }

            public Object getWouldOrderDetail() {
                return wouldOrderDetail;
            }

            public void setWouldOrderDetail(Object wouldOrderDetail) {
                this.wouldOrderDetail = wouldOrderDetail;
            }

            public boolean isIS_Sms() {
                return IS_Sms;
            }

            public void setIS_Sms(boolean IS_Sms) {
                this.IS_Sms = IS_Sms;
            }

            public String getWO_Device() {
                return WO_Device;
            }

            public void setWO_Device(String WO_Device) {
                this.WO_Device = WO_Device;
            }
        }
    }
}
