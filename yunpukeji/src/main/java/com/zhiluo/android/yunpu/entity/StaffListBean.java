package com.zhiluo.android.yunpu.entity;

import java.util.List;

/**
 * 员工列表
 * Created by dell on 2018/2/25.
 */

public class StaffListBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"412be354-c89c-499e-85f2-867af975a2a4","SM_GID":"","CY_GID":null,"DM_GID":"8e0468c9-eff7-4013-960c-85328afaa934","EM_Code":"","EM_Name":"小陈","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2017-07-07 17:04:46","EM_Creator":"137615198@qq.com","EM_Birthday":null,"SM_Name":null,"DM_Name":"导购","EM_TipCard":1,"EM_TipRecharge":1,"EM_TipChargeTime":1,"EM_TipGoodsConsume":1,"EM_TipFastConsume":1,"EM_TipTimesConsume":0,"EM_TipComboConsume":0},{"GID":"6b060dc1-f488-49ed-b176-3c43c389450b","SM_GID":"","CY_GID":null,"DM_GID":"8e0468c9-eff7-4013-960c-85328afaa934","EM_Code":"","EM_Name":"小刘","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2017-07-07 17:04:36","EM_Creator":"137615198@qq.com","EM_Birthday":null,"SM_Name":null,"DM_Name":"导购","EM_TipCard":1,"EM_TipRecharge":1,"EM_TipChargeTime":1,"EM_TipGoodsConsume":1,"EM_TipFastConsume":1,"EM_TipTimesConsume":1,"EM_TipComboConsume":0},{"GID":"6ed7959b-c967-4b71-8381-3ec1a3a6ff6b","SM_GID":"","CY_GID":null,"DM_GID":"df5e1a29-a050-4313-9df3-074680da9fcb","EM_Code":"","EM_Name":"小李","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2017-07-06 17:12:40","EM_Creator":"137615198@qq.com","EM_Birthday":null,"SM_Name":null,"DM_Name":"前台","EM_TipCard":1,"EM_TipRecharge":1,"EM_TipChargeTime":1,"EM_TipGoodsConsume":1,"EM_TipFastConsume":0,"EM_TipTimesConsume":0,"EM_TipComboConsume":0},{"GID":"ce727af5-8bc4-467d-ae70-85c4fbe92fd4","SM_GID":"","CY_GID":null,"DM_GID":"df5e1a29-a050-4313-9df3-074680da9fcb","EM_Code":"","EM_Name":"小王","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2017-07-06 17:12:21","EM_Creator":"137615198@qq.com","EM_Birthday":null,"SM_Name":null,"DM_Name":"前台","EM_TipCard":1,"EM_TipRecharge":1,"EM_TipChargeTime":1,"EM_TipGoodsConsume":1,"EM_TipFastConsume":1,"EM_TipTimesConsume":1,"EM_TipComboConsume":0}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<StaffBean> data;

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

    public List<StaffBean> getData() {
        return data;
    }

    public void setData(List<StaffBean> data) {
        this.data = data;
    }
}
