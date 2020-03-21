package com.zhiluo.android.yunpu.entity;

import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/25 10:31.
 * 邮箱：137615198@qq.com
 * 快捷充值列表数据
 */

public class RechargeListBean {
    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"RP_IsOpen":1,"GID":"d77e670a-34b6-4fd8-bfbb-40e071791bf8","RP_Name":"充值满100送50","RP_Type":1,"RP_RechargeMoney":100,"RP_GiveMoney":50,"RP_GivePoint":100,"RP_ReduceMoney":0,"RP_ValidType":1,"RP_ValidWeekMonth":"","RP_ValidStartTime":"2017-12-26 00:00:00","RP_ValidEndTime":"2018-03-26 00:00:00","RP_ISDouble":0,"RP_Creator":"17358638575","RP_CreateTime":"2017-12-26 13:46:03","CY_GID":"01a5ab8f-46b1-41c4-b089-0a73230ff9da","RP_Remark":null},{"RP_IsOpen":1,"GID":"743963fd-e10e-4830-be72-495dd2ed093b","RP_Name":"充500送300","RP_Type":1,"RP_RechargeMoney":500,"RP_GiveMoney":300,"RP_GivePoint":250,"RP_ReduceMoney":0,"RP_ValidType":1,"RP_ValidWeekMonth":"","RP_ValidStartTime":"2017-12-25 00:00:00","RP_ValidEndTime":"2018-03-25 00:00:00","RP_ISDouble":0,"RP_Creator":"17358638575","RP_CreateTime":"2017-12-25 15:33:10","CY_GID":"01a5ab8f-46b1-41c4-b089-0a73230ff9da","RP_Remark":null},{"RP_IsOpen":1,"GID":"a42aa158-fe01-4390-aefc-8f175a20c573","RP_Name":"充200送100","RP_Type":1,"RP_RechargeMoney":200,"RP_GiveMoney":100,"RP_GivePoint":200,"RP_ReduceMoney":0,"RP_ValidType":1,"RP_ValidWeekMonth":"","RP_ValidStartTime":"2017-12-25 00:00:00","RP_ValidEndTime":"2018-03-25 00:00:00","RP_ISDouble":0,"RP_Creator":"17358638575","RP_CreateTime":"2017-12-25 15:32:43","CY_GID":"01a5ab8f-46b1-41c4-b089-0a73230ff9da","RP_Remark":null}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<RechargeBean> data;

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

    public List<RechargeBean> getData() {
        return data;
    }

    public void setData(List<RechargeBean> data) {
        this.data = data;
    }
}
