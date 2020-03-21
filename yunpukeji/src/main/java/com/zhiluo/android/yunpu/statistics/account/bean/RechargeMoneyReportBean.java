package com.zhiluo.android.yunpu.statistics.account.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/5 15:53.
 * 邮箱：137615198@qq.com
 */

public class RechargeMoneyReportBean implements Serializable{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":16,"PageIndex":1,"DataList":[{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"111","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":null,"VIP_CellPhone":null,"GID":"467fa1a7-de7b-45fe-9cc4-0b0682e69ca6","SM_GID":null,"VIP_GID":"a8934924-627b-45d5-8265-30e3a8093013","MR_Order":"CZ1708171038562536","MR_Way":"现金(2.00)","MR_Code":null,"MR_Amount":2,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":2,"MR_PrepaidTime":"2017-08-17 10:39:08","MA_AvailableBalance":7,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:39:08","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"115","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":null,"VIP_CellPhone":null,"GID":"6321ac9c-ebdd-4fb6-9e80-f20c3444c54d","SM_GID":null,"VIP_GID":"5949e0dc-f27e-4742-855f-738844cc908d","MR_Order":"CZ1708171026369756","MR_Way":"现金(4.00)","MR_Code":null,"MR_Amount":4,"MR_GivenAmount":0,"MR_Integral":1,"MR_Total":4,"MR_PrepaidTime":"2017-08-17 10:26:43","MA_AvailableBalance":5,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:26:43","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"111","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":null,"VIP_CellPhone":null,"GID":"3bad062a-ffd7-4b20-9ea3-86270f70772e","SM_GID":null,"VIP_GID":"a8934924-627b-45d5-8265-30e3a8093013","MR_Order":"CZ1708171024496806","MR_Way":"现金(5.00)","MR_Code":null,"MR_Amount":5,"MR_GivenAmount":0,"MR_Integral":1,"MR_Total":5,"MR_PrepaidTime":"2017-08-17 10:25:05","MA_AvailableBalance":5,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:25:05","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"115","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":null,"VIP_CellPhone":null,"GID":"8099dc30-2f4b-4821-9fe8-bff3be88d2ef","SM_GID":null,"VIP_GID":"5949e0dc-f27e-4742-855f-738844cc908d","MR_Order":"CZ1708171019219890","MR_Way":"现金(1)","MR_Code":null,"MR_Amount":1,"MR_GivenAmount":0,"MR_Integral":1,"MR_Total":1,"MR_PrepaidTime":"2017-08-17 10:19:29","MA_AvailableBalance":1,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:19:29","CY_GID":null,"MR_Device":0},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"114","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":null,"VIP_CellPhone":null,"GID":"88c4c49d-f88a-444c-a67e-97dd7381ec75","SM_GID":null,"VIP_GID":"8a9330b8-fdca-4484-adec-518822570bf0","MR_Order":"CZ1708171010326111","MR_Way":"现金(1.00)","MR_Code":null,"MR_Amount":1,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":1,"MR_PrepaidTime":"2017-08-17 10:10:53","MA_AvailableBalance":1,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:10:53","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"9001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"余文乐","VIP_CellPhone":"15874441474","GID":"9fdacb19-7df2-4a62-9b31-fade87b27b5b","SM_GID":null,"VIP_GID":"7bb541cd-f1ce-4c07-91b2-dc6381847b4b","MR_Order":"CZ1708171002566543","MR_Way":"现金(1.00)","MR_Code":null,"MR_Amount":1,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":1,"MR_PrepaidTime":"2017-08-17 10:03:04","MA_AvailableBalance":101,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:03:04","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"002","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"张三","VIP_CellPhone":"15821456987","GID":"f7ecd7a1-34c0-4af3-934b-1699a6f964dd","SM_GID":null,"VIP_GID":"554677df-3c51-49d1-bdad-2d8956ae52bc","MR_Order":"CZ1708041740201467","MR_Way":"现金(100.00)","MR_Code":null,"MR_Amount":100,"MR_GivenAmount":0,"MR_Integral":33,"MR_Total":100,"MR_PrepaidTime":"2017-08-04 17:40:36","MA_AvailableBalance":422,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-04 17:40:36","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"777","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"罗咏哲","VIP_CellPhone":"17358638575","GID":"c241c8e1-9b39-4bfc-bfb3-e899aea41d10","SM_GID":null,"VIP_GID":"d7ea0ab8-b8fc-492e-9481-7c42aaf9803a","MR_Order":"CZ1707270949587612","MR_Way":"现金(25.00)","MR_Code":null,"MR_Amount":25,"MR_GivenAmount":0,"MR_Integral":8,"MR_Total":25,"MR_PrepaidTime":"2017-07-27 09:50:16","MA_AvailableBalance":175,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-27 09:50:16","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"bed5482b-a187-4b3e-a8d5-8388a044b715","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ1707111016072220","MR_Way":null,"MR_Code":null,"MR_Amount":2,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":2,"MR_PrepaidTime":"2017-07-11 10:16:34","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-11 10:16:34","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"c7e6e43d-a56c-49d6-8a30-7352881f5438","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ1707111015524730","MR_Way":null,"MR_Code":null,"MR_Amount":1,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":1,"MR_PrepaidTime":"2017-07-11 10:16:03","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-11 10:16:03","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"a7f667ab-d279-4102-840c-42c9daae167a","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ1707111007516317","MR_Way":null,"MR_Code":null,"MR_Amount":10,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":10,"MR_PrepaidTime":"2017-07-11 10:08:03","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-11 10:08:03","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"4bf2fca3-ab6a-4a63-900f-0fd93d661bbe","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ1707111006417083","MR_Way":null,"MR_Code":null,"MR_Amount":10,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":10,"MR_PrepaidTime":"2017-07-11 10:06:55","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-11 10:06:55","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"855124c2-d519-4ce0-b817-e015a8815141","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ20170711093254","MR_Way":null,"MR_Code":null,"MR_Amount":100,"MR_GivenAmount":100,"MR_Integral":0,"MR_Total":200,"MR_PrepaidTime":"2017-07-11 09:32:55","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-11 09:32:55","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"003","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李仁","VIP_CellPhone":"15725252525","GID":"3ed6f642-ff11-4470-b24e-3276b41c8956","SM_GID":null,"VIP_GID":"ea9bc126-965a-4911-911f-f4e66bc190b8","MR_Order":"CZ1707101146586623","MR_Way":null,"MR_Code":null,"MR_Amount":250,"MR_GivenAmount":20,"MR_Integral":0,"MR_Total":370,"MR_PrepaidTime":"2017-07-10 11:47:35","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-10 11:47:35","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"565","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"罗志祥","VIP_CellPhone":"18653885588","GID":"8be5720d-e757-4d12-9737-f55324d972f9","SM_GID":null,"VIP_GID":"62a3ac24-5af5-4be4-88af-11df436373df","MR_Order":"CZ1707101130437076","MR_Way":null,"MR_Code":null,"MR_Amount":200,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":220,"MR_PrepaidTime":"2017-07-10 11:31:10","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-10 11:31:10","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"5ecf94c2-cc8d-4ef6-89a1-b99ddba3d033","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ1707071047311861","MR_Way":null,"MR_Code":null,"MR_Amount":100,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":100,"MR_PrepaidTime":"2017-07-07 10:48:58","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-07 10:48:58","CY_GID":null,"MR_Device":null}],"StatisticsInfo":{"RechargeTotalMoney":"812.00","GiveTotalMoney":"120.00"}}
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
         * PageSize : 20
         * DataCount : 16
         * PageIndex : 1
         * DataList : [{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"111","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":null,"VIP_CellPhone":null,"GID":"467fa1a7-de7b-45fe-9cc4-0b0682e69ca6","SM_GID":null,"VIP_GID":"a8934924-627b-45d5-8265-30e3a8093013","MR_Order":"CZ1708171038562536","MR_Way":"现金(2.00)","MR_Code":null,"MR_Amount":2,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":2,"MR_PrepaidTime":"2017-08-17 10:39:08","MA_AvailableBalance":7,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:39:08","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"115","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":null,"VIP_CellPhone":null,"GID":"6321ac9c-ebdd-4fb6-9e80-f20c3444c54d","SM_GID":null,"VIP_GID":"5949e0dc-f27e-4742-855f-738844cc908d","MR_Order":"CZ1708171026369756","MR_Way":"现金(4.00)","MR_Code":null,"MR_Amount":4,"MR_GivenAmount":0,"MR_Integral":1,"MR_Total":4,"MR_PrepaidTime":"2017-08-17 10:26:43","MA_AvailableBalance":5,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:26:43","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"111","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":null,"VIP_CellPhone":null,"GID":"3bad062a-ffd7-4b20-9ea3-86270f70772e","SM_GID":null,"VIP_GID":"a8934924-627b-45d5-8265-30e3a8093013","MR_Order":"CZ1708171024496806","MR_Way":"现金(5.00)","MR_Code":null,"MR_Amount":5,"MR_GivenAmount":0,"MR_Integral":1,"MR_Total":5,"MR_PrepaidTime":"2017-08-17 10:25:05","MA_AvailableBalance":5,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:25:05","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"115","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":null,"VIP_CellPhone":null,"GID":"8099dc30-2f4b-4821-9fe8-bff3be88d2ef","SM_GID":null,"VIP_GID":"5949e0dc-f27e-4742-855f-738844cc908d","MR_Order":"CZ1708171019219890","MR_Way":"现金(1)","MR_Code":null,"MR_Amount":1,"MR_GivenAmount":0,"MR_Integral":1,"MR_Total":1,"MR_PrepaidTime":"2017-08-17 10:19:29","MA_AvailableBalance":1,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:19:29","CY_GID":null,"MR_Device":0},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"114","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":null,"VIP_CellPhone":null,"GID":"88c4c49d-f88a-444c-a67e-97dd7381ec75","SM_GID":null,"VIP_GID":"8a9330b8-fdca-4484-adec-518822570bf0","MR_Order":"CZ1708171010326111","MR_Way":"现金(1.00)","MR_Code":null,"MR_Amount":1,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":1,"MR_PrepaidTime":"2017-08-17 10:10:53","MA_AvailableBalance":1,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:10:53","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"9001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"余文乐","VIP_CellPhone":"15874441474","GID":"9fdacb19-7df2-4a62-9b31-fade87b27b5b","SM_GID":null,"VIP_GID":"7bb541cd-f1ce-4c07-91b2-dc6381847b4b","MR_Order":"CZ1708171002566543","MR_Way":"现金(1.00)","MR_Code":null,"MR_Amount":1,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":1,"MR_PrepaidTime":"2017-08-17 10:03:04","MA_AvailableBalance":101,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-17 10:03:04","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"002","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"张三","VIP_CellPhone":"15821456987","GID":"f7ecd7a1-34c0-4af3-934b-1699a6f964dd","SM_GID":null,"VIP_GID":"554677df-3c51-49d1-bdad-2d8956ae52bc","MR_Order":"CZ1708041740201467","MR_Way":"现金(100.00)","MR_Code":null,"MR_Amount":100,"MR_GivenAmount":0,"MR_Integral":33,"MR_Total":100,"MR_PrepaidTime":"2017-08-04 17:40:36","MA_AvailableBalance":422,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-08-04 17:40:36","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"777","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"罗咏哲","VIP_CellPhone":"17358638575","GID":"c241c8e1-9b39-4bfc-bfb3-e899aea41d10","SM_GID":null,"VIP_GID":"d7ea0ab8-b8fc-492e-9481-7c42aaf9803a","MR_Order":"CZ1707270949587612","MR_Way":"现金(25.00)","MR_Code":null,"MR_Amount":25,"MR_GivenAmount":0,"MR_Integral":8,"MR_Total":25,"MR_PrepaidTime":"2017-07-27 09:50:16","MA_AvailableBalance":175,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-27 09:50:16","CY_GID":null,"MR_Device":1},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"bed5482b-a187-4b3e-a8d5-8388a044b715","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ1707111016072220","MR_Way":null,"MR_Code":null,"MR_Amount":2,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":2,"MR_PrepaidTime":"2017-07-11 10:16:34","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-11 10:16:34","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"c7e6e43d-a56c-49d6-8a30-7352881f5438","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ1707111015524730","MR_Way":null,"MR_Code":null,"MR_Amount":1,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":1,"MR_PrepaidTime":"2017-07-11 10:16:03","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-11 10:16:03","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"a7f667ab-d279-4102-840c-42c9daae167a","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ1707111007516317","MR_Way":null,"MR_Code":null,"MR_Amount":10,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":10,"MR_PrepaidTime":"2017-07-11 10:08:03","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-11 10:08:03","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"4bf2fca3-ab6a-4a63-900f-0fd93d661bbe","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ1707111006417083","MR_Way":null,"MR_Code":null,"MR_Amount":10,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":10,"MR_PrepaidTime":"2017-07-11 10:06:55","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-11 10:06:55","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"855124c2-d519-4ce0-b817-e015a8815141","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ20170711093254","MR_Way":null,"MR_Code":null,"MR_Amount":100,"MR_GivenAmount":100,"MR_Integral":0,"MR_Total":200,"MR_PrepaidTime":"2017-07-11 09:32:55","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-11 09:32:55","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"003","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李仁","VIP_CellPhone":"15725252525","GID":"3ed6f642-ff11-4470-b24e-3276b41c8956","SM_GID":null,"VIP_GID":"ea9bc126-965a-4911-911f-f4e66bc190b8","MR_Order":"CZ1707101146586623","MR_Way":null,"MR_Code":null,"MR_Amount":250,"MR_GivenAmount":20,"MR_Integral":0,"MR_Total":370,"MR_PrepaidTime":"2017-07-10 11:47:35","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-10 11:47:35","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"565","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"罗志祥","VIP_CellPhone":"18653885588","GID":"8be5720d-e757-4d12-9737-f55324d972f9","SM_GID":null,"VIP_GID":"62a3ac24-5af5-4be4-88af-11df436373df","MR_Order":"CZ1707101130437076","MR_Way":null,"MR_Code":null,"MR_Amount":200,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":220,"MR_PrepaidTime":"2017-07-10 11:31:10","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-10 11:31:10","CY_GID":null,"MR_Device":null},{"EM_GIDList":null,"MR_IdentifyingState":0,"PayTypeList":null,"DisMoney":0,"PayPoint":0,"CC_GID":null,"VCH_Card":"001","IS_Sms":false,"MR_SSMoney":0,"MR_ZLMoney":0,"MR_ActivityName":null,"MR_ActivityValue":null,"VIP_Name":"李涛","VIP_CellPhone":"13322222222","GID":"5ecf94c2-cc8d-4ef6-89a1-b99ddba3d033","SM_GID":null,"VIP_GID":"dc9cc991-067d-409c-9070-c633937e62e3","MR_Order":"CZ1707071047311861","MR_Way":null,"MR_Code":null,"MR_Amount":100,"MR_GivenAmount":0,"MR_Integral":0,"MR_Total":100,"MR_PrepaidTime":"2017-07-07 10:48:58","MA_AvailableBalance":0,"MI_Number":null,"MR_Declare":null,"MR_Identifying":null,"MR_Remark":"","MR_Creator":"137615198@qq.com","MR_UpdateTime":"2017-07-07 10:48:58","CY_GID":null,"MR_Device":null}]
         * StatisticsInfo : {"RechargeTotalMoney":"812.00","GiveTotalMoney":"120.00"}
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
             * RechargeTotalMoney : 812.00
             * GiveTotalMoney : 120.00
             */

            private String RechargeTotalMoney;
            private String GiveTotalMoney;

            public String getRechargeTotalMoney() {
                return RechargeTotalMoney;
            }

            public void setRechargeTotalMoney(String RechargeTotalMoney) {
                this.RechargeTotalMoney = RechargeTotalMoney;
            }

            public String getGiveTotalMoney() {
                return GiveTotalMoney;
            }

            public void setGiveTotalMoney(String GiveTotalMoney) {
                this.GiveTotalMoney = GiveTotalMoney;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * EM_GIDList : null
             * MR_IdentifyingState : 0
             * PayTypeList : null
             * DisMoney : 0.0
             * PayPoint : 0.0
             * CC_GID : null
             * VCH_Card : 111
             * IS_Sms : false
             * MR_SSMoney : 0.0
             * MR_ZLMoney : 0.0
             * MR_ActivityName : null
             * MR_ActivityValue : null
             * VIP_Name : null
             * VIP_CellPhone : null
             * GID : 467fa1a7-de7b-45fe-9cc4-0b0682e69ca6
             * SM_GID : null
             * VIP_GID : a8934924-627b-45d5-8265-30e3a8093013
             * MR_Order : CZ1708171038562536
             * MR_Way : 现金(2.00)
             * MR_Code : null
             * MR_Amount : 2.0
             * MR_GivenAmount : 0.0
             * MR_Integral : 0
             * MR_Total : 2.0
             * MR_PrepaidTime : 2017-08-17 10:39:08
             * MA_AvailableBalance : 7.0
             * MI_Number : null
             * MR_Declare : null
             * MR_Identifying : null
             * MR_Remark :
             * MR_Creator : 137615198@qq.com
             * MR_UpdateTime : 2017-08-17 10:39:08
             * CY_GID : null
             * MR_Device : 1
             */

            private Object EM_GIDList;
            private int MR_IdentifyingState;
            private Object PayTypeList;
            private double DisMoney;
            private double PayPoint;
            private Object CC_GID;
            private String VCH_Card;
            private boolean IS_Sms;
            private double MR_SSMoney;
            private double MR_ZLMoney;
            private Object MR_ActivityName;
            private Object MR_ActivityValue;
            private String VIP_Name;
            private Object VIP_CellPhone;
            private String GID;
            private Object SM_GID;
            private String VIP_GID;
            private String MR_Order;
            private String MR_Way;
            private Object MR_Code;
            private double MR_Amount;
            private double MR_GivenAmount;
            private double MR_Integral;
            private double MR_Total;
            private String MR_PrepaidTime;
            private double MA_AvailableBalance;
            private Object MI_Number;
            private Object MR_Declare;
            private Object MR_Identifying;
            private String MR_Remark;
            private String MR_Creator;
            private String MR_UpdateTime;
            private Object CY_GID;
            private int MR_Device;

            public Object getEM_GIDList() {
                return EM_GIDList;
            }

            public void setEM_GIDList(Object EM_GIDList) {
                this.EM_GIDList = EM_GIDList;
            }

            public int getMR_IdentifyingState() {
                return MR_IdentifyingState;
            }

            public void setMR_IdentifyingState(int MR_IdentifyingState) {
                this.MR_IdentifyingState = MR_IdentifyingState;
            }

            public Object getPayTypeList() {
                return PayTypeList;
            }

            public void setPayTypeList(Object PayTypeList) {
                this.PayTypeList = PayTypeList;
            }

            public double getDisMoney() {
                return DisMoney;
            }

            public void setDisMoney(double DisMoney) {
                this.DisMoney = DisMoney;
            }

            public double getPayPoint() {
                return PayPoint;
            }

            public void setPayPoint(double PayPoint) {
                this.PayPoint = PayPoint;
            }

            public Object getCC_GID() {
                return CC_GID;
            }

            public void setCC_GID(Object CC_GID) {
                this.CC_GID = CC_GID;
            }

            public String getVCH_Card() {
                return VCH_Card;
            }

            public void setVCH_Card(String VCH_Card) {
                this.VCH_Card = VCH_Card;
            }

            public boolean isIS_Sms() {
                return IS_Sms;
            }

            public void setIS_Sms(boolean IS_Sms) {
                this.IS_Sms = IS_Sms;
            }

            public double getMR_SSMoney() {
                return MR_SSMoney;
            }

            public void setMR_SSMoney(double MR_SSMoney) {
                this.MR_SSMoney = MR_SSMoney;
            }

            public double getMR_ZLMoney() {
                return MR_ZLMoney;
            }

            public void setMR_ZLMoney(double MR_ZLMoney) {
                this.MR_ZLMoney = MR_ZLMoney;
            }

            public Object getMR_ActivityName() {
                return MR_ActivityName;
            }

            public void setMR_ActivityName(Object MR_ActivityName) {
                this.MR_ActivityName = MR_ActivityName;
            }

            public Object getMR_ActivityValue() {
                return MR_ActivityValue;
            }

            public void setMR_ActivityValue(Object MR_ActivityValue) {
                this.MR_ActivityValue = MR_ActivityValue;
            }

            public String getVIP_Name() {
                return VIP_Name;
            }

            public void setVIP_Name(String VIP_Name) {
                this.VIP_Name = VIP_Name;
            }

            public Object getVIP_CellPhone() {
                return VIP_CellPhone;
            }

            public void setVIP_CellPhone(Object VIP_CellPhone) {
                this.VIP_CellPhone = VIP_CellPhone;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public Object getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(Object SM_GID) {
                this.SM_GID = SM_GID;
            }

            public String getVIP_GID() {
                return VIP_GID;
            }

            public void setVIP_GID(String VIP_GID) {
                this.VIP_GID = VIP_GID;
            }

            public String getMR_Order() {
                return MR_Order;
            }

            public void setMR_Order(String MR_Order) {
                this.MR_Order = MR_Order;
            }

            public String getMR_Way() {
                return MR_Way;
            }

            public void setMR_Way(String MR_Way) {
                this.MR_Way = MR_Way;
            }

            public Object getMR_Code() {
                return MR_Code;
            }

            public void setMR_Code(Object MR_Code) {
                this.MR_Code = MR_Code;
            }

            public double getMR_Amount() {
                return MR_Amount;
            }

            public void setMR_Amount(double MR_Amount) {
                this.MR_Amount = MR_Amount;
            }

            public double getMR_GivenAmount() {
                return MR_GivenAmount;
            }

            public void setMR_GivenAmount(double MR_GivenAmount) {
                this.MR_GivenAmount = MR_GivenAmount;
            }

            public double getMR_Integral() {
                return MR_Integral;
            }

            public void setMR_Integral(double MR_Integral) {
                this.MR_Integral = MR_Integral;
            }

            public double getMR_Total() {
                return MR_Total;
            }

            public void setMR_Total(double MR_Total) {
                this.MR_Total = MR_Total;
            }

            public String getMR_PrepaidTime() {
                return MR_PrepaidTime;
            }

            public void setMR_PrepaidTime(String MR_PrepaidTime) {
                this.MR_PrepaidTime = MR_PrepaidTime;
            }

            public double getMA_AvailableBalance() {
                return MA_AvailableBalance;
            }

            public void setMA_AvailableBalance(double MA_AvailableBalance) {
                this.MA_AvailableBalance = MA_AvailableBalance;
            }

            public Object getMI_Number() {
                return MI_Number;
            }

            public void setMI_Number(Object MI_Number) {
                this.MI_Number = MI_Number;
            }

            public Object getMR_Declare() {
                return MR_Declare;
            }

            public void setMR_Declare(Object MR_Declare) {
                this.MR_Declare = MR_Declare;
            }

            public Object getMR_Identifying() {
                return MR_Identifying;
            }

            public void setMR_Identifying(Object MR_Identifying) {
                this.MR_Identifying = MR_Identifying;
            }

            public String getMR_Remark() {
                return MR_Remark;
            }

            public void setMR_Remark(String MR_Remark) {
                this.MR_Remark = MR_Remark;
            }

            public String getMR_Creator() {
                return MR_Creator;
            }

            public void setMR_Creator(String MR_Creator) {
                this.MR_Creator = MR_Creator;
            }

            public String getMR_UpdateTime() {
                return MR_UpdateTime;
            }

            public void setMR_UpdateTime(String MR_UpdateTime) {
                this.MR_UpdateTime = MR_UpdateTime;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }

            public int getMR_Device() {
                return MR_Device;
            }

            public void setMR_Device(int MR_Device) {
                this.MR_Device = MR_Device;
            }
        }
    }
}
