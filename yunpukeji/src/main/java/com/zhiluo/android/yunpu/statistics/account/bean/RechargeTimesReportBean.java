package com.zhiluo.android.yunpu.statistics.account.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/6 17:20.
 * 邮箱：137615198@qq.com
 */

public class RechargeTimesReportBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":3,"PageSize":20,"DataCount":41,"PageIndex":1,"DataList":[{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"88e1d27f-4cb4-42bc-a2f4-5fcf3f0d0be0","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708251158434401","MC_Way":"现金(7338.00)","MC_Code":null,"MC_Amount":7338,"MC_AmountDis":7338,"MC_Number":6,"MC_Integral":0,"MC_ChargeTime":"2017-08-25 11:58:46","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"90c6d6ae-ff14-4223-a0e1-f2f40e6bc8a7","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708231612468938","MC_Way":"现金(200.00)","MC_Code":null,"MC_Amount":200,"MC_AmountDis":200,"MC_Number":10,"MC_Integral":0,"MC_ChargeTime":"2017-08-23 16:12:49","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"4ad7d0bb-b5f7-42a2-bcf5-362bbea823cb","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708221749553389","MC_Way":"现金(800.00)","MC_Code":null,"MC_Amount":800,"MC_AmountDis":800,"MC_Number":10,"MC_Integral":0,"MC_ChargeTime":"2017-08-22 17:49:58","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"df2bccaf-74a5-480e-8dfa-81c900ae0a9b","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708141734252375","MC_Way":"现金(160.00)","MC_Code":null,"MC_Amount":160,"MC_AmountDis":160,"MC_Number":2,"MC_Integral":0,"MC_ChargeTime":"2017-08-14 17:34:32","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"f7e21bbe-7161-466e-b7fc-912e722967a4","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708141725114164","MC_Way":"现金(80.00)","MC_Code":null,"MC_Amount":80,"MC_AmountDis":80,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-08-14 17:25:20","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"741","VIP_Name":"酷狗","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"e7cf1db8-70a4-452f-9c24-8d034914ad26","SG_GID":null,"VIP_GID":"d223b91c-54e8-4127-b4c5-2f645102a6f5","MC_Order":"CC1708141722193293","MC_Way":"现金(80.00)","MC_Code":null,"MC_Amount":80,"MC_AmountDis":80,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-08-14 17:22:24","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9002","VIP_Name":"吴强","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"7d3dcad8-b4ab-43fb-b6ae-41b9c4f0ae3c","SG_GID":null,"VIP_GID":"b4ddd898-fcb9-43b1-aa79-8bbff0b02861","MC_Order":"CC1708101044305797","MC_Way":"现金(80.00)","MC_Code":null,"MC_Amount":80,"MC_AmountDis":80,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-08-10 10:44:34","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"403e6b88-7ace-4a37-abd8-bae5290ad571","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708092037234339","MC_Way":"现金(1223.00)","MC_Code":null,"MC_Amount":1223,"MC_AmountDis":1223,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-08-09 20:37:26","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"91730973-8165-438c-91b5-4dcc1b9b137c","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708081557152152","MC_Way":"现金(398.00)","MC_Code":null,"MC_Amount":398,"MC_AmountDis":358.2,"MC_Number":2,"MC_Integral":7880,"MC_ChargeTime":"2017-08-08 15:57:20","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"741","VIP_Name":"酷狗","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"e9f65584-a2fd-4c30-a929-27f89b2ccf8e","SG_GID":null,"VIP_GID":"d223b91c-54e8-4127-b4c5-2f645102a6f5","MC_Order":"CC1708071715021482","MC_Way":"现金(46.00)","MC_Code":null,"MC_Amount":46,"MC_AmountDis":46,"MC_Number":2,"MC_Integral":0,"MC_ChargeTime":"2017-08-07 17:15:05","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"741","VIP_Name":"酷狗","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"8770d0c2-fe9c-4247-a079-cc73da0c02b7","SG_GID":null,"VIP_GID":"d223b91c-54e8-4127-b4c5-2f645102a6f5","MC_Order":"CC1708071521003736","MC_Way":"现金(1990.00)","MC_Code":null,"MC_Amount":1990,"MC_AmountDis":1592,"MC_Number":10,"MC_Integral":4776,"MC_ChargeTime":"2017-08-07 15:21:06","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"001","VIP_Name":"赵三","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"60a72bc9-0d3e-4b66-9e32-c54c95592fac","SG_GID":null,"VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","MC_Order":"CC1708051146452089","MC_Way":"现金(160.00)","MC_Code":null,"MC_Amount":160,"MC_AmountDis":160,"MC_Number":2,"MC_Integral":0,"MC_ChargeTime":"2017-08-05 11:46:49","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"6666","VIP_Name":"刘丹","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"2c414897-8e36-4b6c-876b-8b0c77048ffb","SG_GID":null,"VIP_GID":"44409500-fad8-4be5-900d-8b6ca8abd095","MC_Order":"CC1707281732076075","MC_Way":"余额(80.00)","MC_Code":null,"MC_Amount":80,"MC_AmountDis":80,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-07-28 17:32:15","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"001","VIP_Name":"赵三","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"aea99bc7-3739-4cc5-b688-69f5aadb8ad7","SG_GID":null,"VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","MC_Order":"CC1707142027584101","MC_Way":"现金(199.00)","MC_Code":null,"MC_Amount":199,"MC_AmountDis":199,"MC_Number":1,"MC_Integral":199,"MC_ChargeTime":"2017-07-14 20:28:00","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"001","VIP_Name":"赵三","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"636ec9a2-4167-476d-bdbd-376bc8aacf7c","SG_GID":null,"VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","MC_Order":"CC1707141847228762","MC_Way":"现金(80.00)","MC_Code":null,"MC_Amount":80,"MC_AmountDis":80,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-07-14 18:47:27","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"001","VIP_Name":"赵三","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"63f4cf59-51e0-4dd4-8f62-a76a8f2f22aa","SG_GID":null,"VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","MC_Order":"CC1707131647128582","MC_Way":"余额(369.00)","MC_Code":null,"MC_Amount":369,"MC_AmountDis":279,"MC_Number":3,"MC_Integral":0,"MC_ChargeTime":"2017-07-13 16:47:38","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"1231","VIP_Name":"李香兰","MC_Device":0,"MC_IdentifyingState":"0","SM_GID":null,"GID":"82ecde5d-d70f-40b1-a69b-c68707c9e178","SG_GID":null,"VIP_GID":"c90d8fac-65ed-4876-960f-dd097a4e0803","MC_Order":"CC1707121933255608","MC_Way":"现金(83.7),余额(39.3)","MC_Code":null,"MC_Amount":123,"MC_AmountDis":123,"MC_Number":1,"MC_Integral":108,"MC_ChargeTime":"2017-07-12 19:33:42","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"1231","VIP_Name":"李香兰","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"8c8e0096-10b5-4e18-8b1b-bbe6ff8bdab1","SG_GID":null,"VIP_GID":"c90d8fac-65ed-4876-960f-dd097a4e0803","MC_Order":"CC1707121932117276","MC_Way":"现金(123.0)","MC_Code":null,"MC_Amount":123,"MC_AmountDis":123,"MC_Number":1,"MC_Integral":108,"MC_ChargeTime":"2017-07-12 19:33:00","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"1234","VIP_Name":"于蓝","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"c3f5326d-05ff-4239-b29f-400826897861","SG_GID":null,"VIP_GID":"5f39714d-774b-4364-ba30-381c760c6fad","MC_Order":"CC1707121421185906","MC_Way":"现金(46.0)","MC_Code":null,"MC_Amount":46,"MC_AmountDis":46,"MC_Number":2,"MC_Integral":0,"MC_ChargeTime":"2017-07-12 14:21:22","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"1234","VIP_Name":"于蓝","MC_Device":0,"MC_IdentifyingState":"0","SM_GID":null,"GID":"c0247fed-9291-4bd5-865a-e627b1aa7946","SG_GID":null,"VIP_GID":"5f39714d-774b-4364-ba30-381c760c6fad","MC_Order":"CC1707121408015500","MC_Way":"现金(203)","MC_Code":null,"MC_Amount":203,"MC_AmountDis":203,"MC_Number":2,"MC_Integral":0,"MC_ChargeTime":"2017-07-12 14:08:46","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null}],"StatisticsInfo":{"RechargeTimeTotalMoney":"15827.00","TotalDisMoney":"17095.20","TotalTimesNum":"103"}}
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
         * DataCount : 41
         * PageIndex : 1
         * DataList : [{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"88e1d27f-4cb4-42bc-a2f4-5fcf3f0d0be0","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708251158434401","MC_Way":"现金(7338.00)","MC_Code":null,"MC_Amount":7338,"MC_AmountDis":7338,"MC_Number":6,"MC_Integral":0,"MC_ChargeTime":"2017-08-25 11:58:46","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"90c6d6ae-ff14-4223-a0e1-f2f40e6bc8a7","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708231612468938","MC_Way":"现金(200.00)","MC_Code":null,"MC_Amount":200,"MC_AmountDis":200,"MC_Number":10,"MC_Integral":0,"MC_ChargeTime":"2017-08-23 16:12:49","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"4ad7d0bb-b5f7-42a2-bcf5-362bbea823cb","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708221749553389","MC_Way":"现金(800.00)","MC_Code":null,"MC_Amount":800,"MC_AmountDis":800,"MC_Number":10,"MC_Integral":0,"MC_ChargeTime":"2017-08-22 17:49:58","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"df2bccaf-74a5-480e-8dfa-81c900ae0a9b","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708141734252375","MC_Way":"现金(160.00)","MC_Code":null,"MC_Amount":160,"MC_AmountDis":160,"MC_Number":2,"MC_Integral":0,"MC_ChargeTime":"2017-08-14 17:34:32","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"f7e21bbe-7161-466e-b7fc-912e722967a4","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708141725114164","MC_Way":"现金(80.00)","MC_Code":null,"MC_Amount":80,"MC_AmountDis":80,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-08-14 17:25:20","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"741","VIP_Name":"酷狗","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"e7cf1db8-70a4-452f-9c24-8d034914ad26","SG_GID":null,"VIP_GID":"d223b91c-54e8-4127-b4c5-2f645102a6f5","MC_Order":"CC1708141722193293","MC_Way":"现金(80.00)","MC_Code":null,"MC_Amount":80,"MC_AmountDis":80,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-08-14 17:22:24","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9002","VIP_Name":"吴强","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"7d3dcad8-b4ab-43fb-b6ae-41b9c4f0ae3c","SG_GID":null,"VIP_GID":"b4ddd898-fcb9-43b1-aa79-8bbff0b02861","MC_Order":"CC1708101044305797","MC_Way":"现金(80.00)","MC_Code":null,"MC_Amount":80,"MC_AmountDis":80,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-08-10 10:44:34","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"403e6b88-7ace-4a37-abd8-bae5290ad571","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708092037234339","MC_Way":"现金(1223.00)","MC_Code":null,"MC_Amount":1223,"MC_AmountDis":1223,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-08-09 20:37:26","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"9001","VIP_Name":"Adele","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"91730973-8165-438c-91b5-4dcc1b9b137c","SG_GID":null,"VIP_GID":"dd411089-755c-409c-9b27-a6e5b7f65c4d","MC_Order":"CC1708081557152152","MC_Way":"现金(398.00)","MC_Code":null,"MC_Amount":398,"MC_AmountDis":358.2,"MC_Number":2,"MC_Integral":7880,"MC_ChargeTime":"2017-08-08 15:57:20","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"741","VIP_Name":"酷狗","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"e9f65584-a2fd-4c30-a929-27f89b2ccf8e","SG_GID":null,"VIP_GID":"d223b91c-54e8-4127-b4c5-2f645102a6f5","MC_Order":"CC1708071715021482","MC_Way":"现金(46.00)","MC_Code":null,"MC_Amount":46,"MC_AmountDis":46,"MC_Number":2,"MC_Integral":0,"MC_ChargeTime":"2017-08-07 17:15:05","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"741","VIP_Name":"酷狗","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"8770d0c2-fe9c-4247-a079-cc73da0c02b7","SG_GID":null,"VIP_GID":"d223b91c-54e8-4127-b4c5-2f645102a6f5","MC_Order":"CC1708071521003736","MC_Way":"现金(1990.00)","MC_Code":null,"MC_Amount":1990,"MC_AmountDis":1592,"MC_Number":10,"MC_Integral":4776,"MC_ChargeTime":"2017-08-07 15:21:06","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"001","VIP_Name":"赵三","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"60a72bc9-0d3e-4b66-9e32-c54c95592fac","SG_GID":null,"VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","MC_Order":"CC1708051146452089","MC_Way":"现金(160.00)","MC_Code":null,"MC_Amount":160,"MC_AmountDis":160,"MC_Number":2,"MC_Integral":0,"MC_ChargeTime":"2017-08-05 11:46:49","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"6666","VIP_Name":"刘丹","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"2c414897-8e36-4b6c-876b-8b0c77048ffb","SG_GID":null,"VIP_GID":"44409500-fad8-4be5-900d-8b6ca8abd095","MC_Order":"CC1707281732076075","MC_Way":"余额(80.00)","MC_Code":null,"MC_Amount":80,"MC_AmountDis":80,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-07-28 17:32:15","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"001","VIP_Name":"赵三","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"aea99bc7-3739-4cc5-b688-69f5aadb8ad7","SG_GID":null,"VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","MC_Order":"CC1707142027584101","MC_Way":"现金(199.00)","MC_Code":null,"MC_Amount":199,"MC_AmountDis":199,"MC_Number":1,"MC_Integral":199,"MC_ChargeTime":"2017-07-14 20:28:00","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"001","VIP_Name":"赵三","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"636ec9a2-4167-476d-bdbd-376bc8aacf7c","SG_GID":null,"VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","MC_Order":"CC1707141847228762","MC_Way":"现金(80.00)","MC_Code":null,"MC_Amount":80,"MC_AmountDis":80,"MC_Number":1,"MC_Integral":0,"MC_ChargeTime":"2017-07-14 18:47:27","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"001","VIP_Name":"赵三","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"63f4cf59-51e0-4dd4-8f62-a76a8f2f22aa","SG_GID":null,"VIP_GID":"0be2551e-53a7-45f2-aa03-a2373edf65b0","MC_Order":"CC1707131647128582","MC_Way":"余额(369.00)","MC_Code":null,"MC_Amount":369,"MC_AmountDis":279,"MC_Number":3,"MC_Integral":0,"MC_ChargeTime":"2017-07-13 16:47:38","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"1231","VIP_Name":"李香兰","MC_Device":0,"MC_IdentifyingState":"0","SM_GID":null,"GID":"82ecde5d-d70f-40b1-a69b-c68707c9e178","SG_GID":null,"VIP_GID":"c90d8fac-65ed-4876-960f-dd097a4e0803","MC_Order":"CC1707121933255608","MC_Way":"现金(83.7),余额(39.3)","MC_Code":null,"MC_Amount":123,"MC_AmountDis":123,"MC_Number":1,"MC_Integral":108,"MC_ChargeTime":"2017-07-12 19:33:42","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"1231","VIP_Name":"李香兰","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"8c8e0096-10b5-4e18-8b1b-bbe6ff8bdab1","SG_GID":null,"VIP_GID":"c90d8fac-65ed-4876-960f-dd097a4e0803","MC_Order":"CC1707121932117276","MC_Way":"现金(123.0)","MC_Code":null,"MC_Amount":123,"MC_AmountDis":123,"MC_Number":1,"MC_Integral":108,"MC_ChargeTime":"2017-07-12 19:33:00","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"1234","VIP_Name":"于蓝","MC_Device":1,"MC_IdentifyingState":"0","SM_GID":null,"GID":"c3f5326d-05ff-4239-b29f-400826897861","SG_GID":null,"VIP_GID":"5f39714d-774b-4364-ba30-381c760c6fad","MC_Order":"CC1707121421185906","MC_Way":"现金(46.0)","MC_Code":null,"MC_Amount":46,"MC_AmountDis":46,"MC_Number":2,"MC_Integral":0,"MC_ChargeTime":"2017-07-12 14:21:22","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null},{"MC_ServiceName":null,"MC_Price":null,"VCH_Card":"1234","VIP_Name":"于蓝","MC_Device":0,"MC_IdentifyingState":"0","SM_GID":null,"GID":"c0247fed-9291-4bd5-865a-e627b1aa7946","SG_GID":null,"VIP_GID":"5f39714d-774b-4364-ba30-381c760c6fad","MC_Order":"CC1707121408015500","MC_Way":"现金(203)","MC_Code":null,"MC_Amount":203,"MC_AmountDis":203,"MC_Number":2,"MC_Integral":0,"MC_ChargeTime":"2017-07-12 14:08:46","MC_Declare":null,"MC_Identifying":null,"MC_Remark":"","MC_Creator":"1540004275@qq.com","MC_UpdateTime":null,"CY_GID":null}]
         * StatisticsInfo : {"RechargeTimeTotalMoney":"15827.00","TotalDisMoney":"17095.20","TotalTimesNum":"103"}
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
             * RechargeTimeTotalMoney : 15827.00
             * TotalDisMoney : 17095.20
             * TotalTimesNum : 103
             */

            private String RechargeTimeTotalMoney;
            private String TotalDisMoney;
            private String TotalTimesNum;

            public String getRechargeTimeTotalMoney() {
                return RechargeTimeTotalMoney;
            }

            public void setRechargeTimeTotalMoney(String RechargeTimeTotalMoney) {
                this.RechargeTimeTotalMoney = RechargeTimeTotalMoney;
            }

            public String getTotalDisMoney() {
                return TotalDisMoney;
            }

            public void setTotalDisMoney(String TotalDisMoney) {
                this.TotalDisMoney = TotalDisMoney;
            }

            public String getTotalTimesNum() {
                return TotalTimesNum;
            }

            public void setTotalTimesNum(String TotalTimesNum) {
                this.TotalTimesNum = TotalTimesNum;
            }
        }

        public static class DataListBean implements Serializable {
            /**
             * MC_ServiceName : null
             * MC_Price : null
             * VCH_Card : 9001
             * VIP_Name : Adele
             * MC_Device : 1
             * MC_IdentifyingState : 0
             * SM_GID : null
             * GID : 88e1d27f-4cb4-42bc-a2f4-5fcf3f0d0be0
             * SG_GID : null
             * VIP_GID : dd411089-755c-409c-9b27-a6e5b7f65c4d
             * MC_Order : CC1708251158434401
             * MC_Way : 现金(7338.00)
             * MC_Code : null
             * MC_Amount : 7338.0
             * MC_AmountDis : 7338.0
             * MC_Number : 6
             * MC_Integral : 0
             * MC_ChargeTime : 2017-08-25 11:58:46
             * MC_Declare : null
             * MC_Identifying : null
             * MC_Remark :
             * MC_Creator : 1540004275@qq.com
             * MC_UpdateTime : null
             * CY_GID : null
             */

            private Object MC_ServiceName;
            private Object MC_Price;
            private String VCH_Card;
            private String VIP_Name;
            private int MC_Device;
            private String MC_IdentifyingState;
            private Object SM_GID;
            private String GID;
            private Object SG_GID;
            private String VIP_GID;
            private String MC_Order;
            private String MC_Way;
            private Object MC_Code;
            private double MC_Amount;
            private double MC_AmountDis;
            private int MC_Number;
            private double MC_Integral;
            private String MC_ChargeTime;
            private Object MC_Declare;
            private Object MC_Identifying;
            private String MC_Remark;
            private String MC_Creator;
            private Object MC_UpdateTime;
            private Object CY_GID;
            private boolean isVisiable;
            /**
             * 每天第一条item的钱数
             */
            private double itemMoney=0;

            public double getItemMoney() {
                return itemMoney;
            }

            public void setItemMoney(double itemMoney) {
                this.itemMoney = itemMoney;
            }

            public boolean isVisiable() {
                return isVisiable;
            }

            public void setVisiable(boolean visiable) {
                isVisiable = visiable;
            }

            public Object getMC_ServiceName() {
                return MC_ServiceName;
            }

            public void setMC_ServiceName(Object MC_ServiceName) {
                this.MC_ServiceName = MC_ServiceName;
            }

            public Object getMC_Price() {
                return MC_Price;
            }

            public void setMC_Price(Object MC_Price) {
                this.MC_Price = MC_Price;
            }

            public String getVCH_Card() {
                return VCH_Card;
            }

            public void setVCH_Card(String VCH_Card) {
                this.VCH_Card = VCH_Card;
            }

            public String getVIP_Name() {
                return VIP_Name;
            }

            public void setVIP_Name(String VIP_Name) {
                this.VIP_Name = VIP_Name;
            }

            public int getMC_Device() {
                return MC_Device;
            }

            public void setMC_Device(int MC_Device) {
                this.MC_Device = MC_Device;
            }

            public String getMC_IdentifyingState() {
                return MC_IdentifyingState;
            }

            public void setMC_IdentifyingState(String MC_IdentifyingState) {
                this.MC_IdentifyingState = MC_IdentifyingState;
            }

            public Object getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(Object SM_GID) {
                this.SM_GID = SM_GID;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public Object getSG_GID() {
                return SG_GID;
            }

            public void setSG_GID(Object SG_GID) {
                this.SG_GID = SG_GID;
            }

            public String getVIP_GID() {
                return VIP_GID;
            }

            public void setVIP_GID(String VIP_GID) {
                this.VIP_GID = VIP_GID;
            }

            public String getMC_Order() {
                return MC_Order;
            }

            public void setMC_Order(String MC_Order) {
                this.MC_Order = MC_Order;
            }

            public String getMC_Way() {
                return MC_Way;
            }

            public void setMC_Way(String MC_Way) {
                this.MC_Way = MC_Way;
            }

            public Object getMC_Code() {
                return MC_Code;
            }

            public void setMC_Code(Object MC_Code) {
                this.MC_Code = MC_Code;
            }

            public double getMC_Amount() {
                return MC_Amount;
            }

            public void setMC_Amount(double MC_Amount) {
                this.MC_Amount = MC_Amount;
            }

            public double getMC_AmountDis() {
                return MC_AmountDis;
            }

            public void setMC_AmountDis(double MC_AmountDis) {
                this.MC_AmountDis = MC_AmountDis;
            }

            public int getMC_Number() {
                return MC_Number;
            }

            public void setMC_Number(int MC_Number) {
                this.MC_Number = MC_Number;
            }

            public double getMC_Integral() {
                return MC_Integral;
            }

            public void setMC_Integral(double MC_Integral) {
                this.MC_Integral = MC_Integral;
            }

            public String getMC_ChargeTime() {
                return MC_ChargeTime;
            }

            public void setMC_ChargeTime(String MC_ChargeTime) {
                this.MC_ChargeTime = MC_ChargeTime;
            }

            public Object getMC_Declare() {
                return MC_Declare;
            }

            public void setMC_Declare(Object MC_Declare) {
                this.MC_Declare = MC_Declare;
            }

            public Object getMC_Identifying() {
                return MC_Identifying;
            }

            public void setMC_Identifying(Object MC_Identifying) {
                this.MC_Identifying = MC_Identifying;
            }

            public String getMC_Remark() {
                return MC_Remark;
            }

            public void setMC_Remark(String MC_Remark) {
                this.MC_Remark = MC_Remark;
            }

            public String getMC_Creator() {
                return MC_Creator;
            }

            public void setMC_Creator(String MC_Creator) {
                this.MC_Creator = MC_Creator;
            }

            public Object getMC_UpdateTime() {
                return MC_UpdateTime;
            }

            public void setMC_UpdateTime(Object MC_UpdateTime) {
                this.MC_UpdateTime = MC_UpdateTime;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }
        }
    }
}
