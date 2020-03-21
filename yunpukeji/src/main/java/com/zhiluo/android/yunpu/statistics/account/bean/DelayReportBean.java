package com.zhiluo.android.yunpu.statistics.account.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/7 10:41.
 * 邮箱：137615198@qq.com
 */

public class DelayReportBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":19,"PageIndex":1,"DataList":[{"GID":"4fcb19f5-5689-4ac4-8248-70c33416e8b1","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 17:05:46","MDR_Order":"YQ1708311705346465","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"现金(2.00)"},{"GID":"9d745515-0c67-498a-ae4c-f384590e2669","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 17:05:29","MDR_Order":"YQ1708311705238544","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"de126690-fa17-4cba-8917-9fea024b5505","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 17:02:04","MDR_Order":"YQ1708311701372186","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"现金(2.00),现金(2.00)"},{"GID":"3cad9db6-4286-4551-ae4f-ed204accdcaf","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 17:01:52","MDR_Order":"YQ1708311701372186","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"现金(2.00)"},{"GID":"92e11dbb-8989-44d6-a6fc-7837ff3b5ca7","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 16:58:46","MDR_Order":"YQ1708311658346264","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":"积分(2)"},{"GID":"f8cb5a12-aba7-462f-a495-8e90a1da983b","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":3,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 16:55:32","MDR_Order":"YQ1708311655214051","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"积分(3.00)"},{"GID":"b0d6ebb2-5e52-4211-9d15-84cca9129774","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 16:41:08","MDR_Order":"YQ1708311640456087","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"积分(2.00)"},{"GID":"956d669a-1efc-4669-b3f1-16c110124315","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":20,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 16:35:12","MDR_Order":"YQ1708311634548535","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"现金(20.00)"},{"GID":"73d80a00-83c6-4e78-9154-04cdaac9fd6f","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:29:07","MDR_Order":"YQ1708311528051316","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":"余额(2.0)"},{"GID":"d7fdad6a-9b49-4274-a8c7-f58665216314","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:28:17","MDR_Order":"YQ1708311528051316","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"8a14dd9d-99f4-47d9-8e6c-2740f017fe2d","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:28:10","MDR_Order":"YQ1708311528051316","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"715086eb-6651-49c4-a105-1034b4942c8e","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":8,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:21:53","MDR_Order":"YQ1708311517584466","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":"现金(8.0)"},{"GID":"122f7f64-c132-4034-92aa-bc9e7dcc1e2a","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":8,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:21:03","MDR_Order":"YQ1708311517584466","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":"现金(8.0)"},{"GID":"b50a63e1-106d-4bb2-9e36-4e4ec27726af","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:18:01","MDR_Order":"YQ1708311517584466","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"39299145-df22-4408-b12a-b3ac19a2f402","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:13:55","MDR_Order":"YQ1708311513494081","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"6e126e27-1d7c-41dd-becf-b9ac55971213","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 14:19:14","MDR_Order":"YQ1708311419028025","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"322eb3b9-d41b-470b-b55a-eaacd4916843","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":10,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 14:11:29","MDR_Order":"YQ1708311410556560","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"备注","MDR_PayType":"现金(10)"},{"GID":"965a7a87-e64a-4fe2-96fb-e6e965e55579","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-30 18:12:23","MDR_Order":"YQ1708301812092594","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"3372c53f-608d-485e-ab15-91573b16c99c","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-30 18:11:39","MDR_Order":"YQ1708301811047070","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null}],"StatisticsInfo":{"SA_MemberDelayMoneyCount":"61.00"}}
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
         * DataCount : 19
         * PageIndex : 1
         * DataList : [{"GID":"4fcb19f5-5689-4ac4-8248-70c33416e8b1","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 17:05:46","MDR_Order":"YQ1708311705346465","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"现金(2.00)"},{"GID":"9d745515-0c67-498a-ae4c-f384590e2669","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 17:05:29","MDR_Order":"YQ1708311705238544","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"de126690-fa17-4cba-8917-9fea024b5505","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 17:02:04","MDR_Order":"YQ1708311701372186","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"现金(2.00),现金(2.00)"},{"GID":"3cad9db6-4286-4551-ae4f-ed204accdcaf","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 17:01:52","MDR_Order":"YQ1708311701372186","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"现金(2.00)"},{"GID":"92e11dbb-8989-44d6-a6fc-7837ff3b5ca7","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 16:58:46","MDR_Order":"YQ1708311658346264","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":"积分(2)"},{"GID":"f8cb5a12-aba7-462f-a495-8e90a1da983b","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":3,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 16:55:32","MDR_Order":"YQ1708311655214051","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"积分(3.00)"},{"GID":"b0d6ebb2-5e52-4211-9d15-84cca9129774","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 16:41:08","MDR_Order":"YQ1708311640456087","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"积分(2.00)"},{"GID":"956d669a-1efc-4669-b3f1-16c110124315","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1","MDR_Money":20,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 16:35:12","MDR_Order":"YQ1708311634548535","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":null,"MDR_PayType":"现金(20.00)"},{"GID":"73d80a00-83c6-4e78-9154-04cdaac9fd6f","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":2,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:29:07","MDR_Order":"YQ1708311528051316","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":"余额(2.0)"},{"GID":"d7fdad6a-9b49-4274-a8c7-f58665216314","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:28:17","MDR_Order":"YQ1708311528051316","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"8a14dd9d-99f4-47d9-8e6c-2740f017fe2d","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:28:10","MDR_Order":"YQ1708311528051316","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"715086eb-6651-49c4-a105-1034b4942c8e","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":8,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:21:53","MDR_Order":"YQ1708311517584466","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":"现金(8.0)"},{"GID":"122f7f64-c132-4034-92aa-bc9e7dcc1e2a","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":8,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:21:03","MDR_Order":"YQ1708311517584466","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":"现金(8.0)"},{"GID":"b50a63e1-106d-4bb2-9e36-4e4ec27726af","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:18:01","MDR_Order":"YQ1708311517584466","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"39299145-df22-4408-b12a-b3ac19a2f402","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 15:13:55","MDR_Order":"YQ1708311513494081","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"6e126e27-1d7c-41dd-becf-b9ac55971213","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 14:19:14","MDR_Order":"YQ1708311419028025","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"322eb3b9-d41b-470b-b55a-eaacd4916843","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":10,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-31 14:11:29","MDR_Order":"YQ1708311410556560","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"备注","MDR_PayType":"现金(10)"},{"GID":"965a7a87-e64a-4fe2-96fb-e6e965e55579","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-30 18:12:23","MDR_Order":"YQ1708301812092594","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null},{"GID":"3372c53f-608d-485e-ab15-91573b16c99c","CY_GID":"348e79f2-ff35-4d62-8298-9d37f06fd5e0","SM_GID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","VIP_Card":"6785","VIP_Name":"张合","MDR_Time":"延期1月","MDR_Money":0,"MDR_Creator":"1540004275@qq.com","MDR_UpdateTime":"2017-08-30 18:11:39","MDR_Order":"YQ1708301811047070","VIP_GID":"8c56319b-3c29-4b88-9d2e-85ddbc6437ed","DisMoney":0,"PayTypeList":null,"CO_SSMoney":0,"CO_ZLMoney":0,"months":0,"PayPoint":0,"CO_Integral":null,"MDR_DelayRemark":"","MDR_PayType":null}]
         * StatisticsInfo : {"SA_MemberDelayMoneyCount":"61.00"}
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
             * SA_MemberDelayMoneyCount : 61.00
             */

            private String SA_MemberDelayMoneyCount;

            public String getSA_MemberDelayMoneyCount() {
                return SA_MemberDelayMoneyCount;
            }

            public void setSA_MemberDelayMoneyCount(String SA_MemberDelayMoneyCount) {
                this.SA_MemberDelayMoneyCount = SA_MemberDelayMoneyCount;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * GID : 4fcb19f5-5689-4ac4-8248-70c33416e8b1
             * CY_GID : 348e79f2-ff35-4d62-8298-9d37f06fd5e0
             * SM_GID : d754599d-3004-4256-b462-2d841d6f962d
             * SM_Name : 云上铺
             * VIP_Card : 6785
             * VIP_Name : 张合
             * MDR_Time : 延期1
             * MDR_Money : 2.0
             * MDR_Creator : 1540004275@qq.com
             * MDR_UpdateTime : 2017-08-31 17:05:46
             * MDR_Order : YQ1708311705346465
             * VIP_GID : 8c56319b-3c29-4b88-9d2e-85ddbc6437ed
             * DisMoney : 0.0
             * PayTypeList : null
             * CO_SSMoney : 0.0
             * CO_ZLMoney : 0.0
             * months : 0
             * PayPoint : 0.0
             * CO_Integral : null
             * MDR_DelayRemark : null
             * MDR_PayType : 现金(2.00)
             */

            private String GID;
            private String CY_GID;
            private String SM_GID;
            private String SM_Name;
            private String VIP_Card;
            private String VIP_Name;
            private String MDR_Time;
            private double MDR_Money;
            private String MDR_Creator;
            private String MDR_UpdateTime;
            private String MDR_Order;
            private String VIP_GID;
            private double DisMoney;
            private Object PayTypeList;
            private double CO_SSMoney;
            private double CO_ZLMoney;
            private int months;
            private double PayPoint;
            private Object CO_Integral;
            private String MDR_DelayRemark;
            private String MDR_PayType;

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
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

            public String getMDR_Time() {
                return MDR_Time;
            }

            public void setMDR_Time(String MDR_Time) {
                this.MDR_Time = MDR_Time;
            }

            public double getMDR_Money() {
                return MDR_Money;
            }

            public void setMDR_Money(double MDR_Money) {
                this.MDR_Money = MDR_Money;
            }

            public String getMDR_Creator() {
                return MDR_Creator;
            }

            public void setMDR_Creator(String MDR_Creator) {
                this.MDR_Creator = MDR_Creator;
            }

            public String getMDR_UpdateTime() {
                return MDR_UpdateTime;
            }

            public void setMDR_UpdateTime(String MDR_UpdateTime) {
                this.MDR_UpdateTime = MDR_UpdateTime;
            }

            public String getMDR_Order() {
                return MDR_Order;
            }

            public void setMDR_Order(String MDR_Order) {
                this.MDR_Order = MDR_Order;
            }

            public String getVIP_GID() {
                return VIP_GID;
            }

            public void setVIP_GID(String VIP_GID) {
                this.VIP_GID = VIP_GID;
            }

            public double getDisMoney() {
                return DisMoney;
            }

            public void setDisMoney(double DisMoney) {
                this.DisMoney = DisMoney;
            }

            public Object getPayTypeList() {
                return PayTypeList;
            }

            public void setPayTypeList(Object PayTypeList) {
                this.PayTypeList = PayTypeList;
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

            public int getMonths() {
                return months;
            }

            public void setMonths(int months) {
                this.months = months;
            }

            public double getPayPoint() {
                return PayPoint;
            }

            public void setPayPoint(double PayPoint) {
                this.PayPoint = PayPoint;
            }

            public Object getCO_Integral() {
                return CO_Integral;
            }

            public void setCO_Integral(Object CO_Integral) {
                this.CO_Integral = CO_Integral;
            }

            public String getMDR_DelayRemark() {
                return MDR_DelayRemark;
            }

            public void setMDR_DelayRemark(String MDR_DelayRemark) {
                this.MDR_DelayRemark = MDR_DelayRemark;
            }

            public String getMDR_PayType() {
                return MDR_PayType;
            }

            public void setMDR_PayType(String MDR_PayType) {
                this.MDR_PayType = MDR_PayType;
            }
        }
    }
}
