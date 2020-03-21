package com.zhiluo.android.yunpu.member.manager.bean;

import com.zhiluo.android.yunpu.entity.DataBean;

import java.io.Serializable;
import java.util.List;

/**
 * 会员等级相关设定
 * 作者：罗咏哲 on 2017/7/9 22:19.
 * 邮箱：137615198@qq.com
 */

public class MemberGrade implements Serializable {
    private static final long UID = 1L;

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"VG_IsDownLock":0,"VG_IsTimeTimes":9,"GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","US_Code":"IntegerFulfil","US_Value":0,"VG_Name":"默认等级","VG_Code":"默认等级","VG_CardAmount":0,"VG_InitialAmount":0,"VG_InitialIntegral":55,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":1,"VG_IsTimeNum":null,"VG_IsTimeUnit":null,"VG_Remark":"","DS_Value":100,"RS_CMoney":5,"RS_Value":2,"VS_CMoney":10,"VS_Value":8,"US_Name":"积分达到","Settings":[{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"0be06d90-11c6-46f0-bbf1-7c7ca20f46df","PT_Name":"洗剪吹服务","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"fad86152-3f15-42bc-a20d-e723bf95b371","PT_SynType":"0"},{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"14265702-ca99-4cf0-8537-821ce39c8996","PT_Name":"手机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"171c168e-e00b-4f2d-86d4-b1a9a5c5f0aa","PT_Name":"文学","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"26bbcaf4-952a-4eca-8078-c3cbdb0a6f20","PT_SynType":"0"},{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"95d7f9ce-1f98-43e3-9648-8c76943c8b5d","PT_Name":"洗澡","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"fad86152-3f15-42bc-a20d-e723bf95b371","PT_SynType":"0"},{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"86efb174-e863-4c52-9029-186de4b576bf","PT_Name":"抽纸","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"4657c0e0-b1dc-4850-bfb2-be23c2dfefb2","PT_SynType":"0"},{"VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","VG_Name":"默认等级","PT_GID":"e8407dd0-f3b2-4c2e-8a38-67a93b9c36c3","PT_Name":"编程","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"26bbcaf4-952a-4eca-8078-c3cbdb0a6f20","PT_SynType":"0"}],"InitServiceList":[{"SR_Timer":30,"SR_TimeUnit":1,"GID":"68c5576e-0d57-460e-a67c-545693c45c1a","VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","SC_GD":"ea01ba75-0582-4128-a20a-ebe23dccaa18","SR_Number":12,"SR_UpdateTime":"2018-01-13 15:02:56","SG_Name":"洗澡"},{"SR_Timer":10,"SR_TimeUnit":2,"GID":"9aeda6e0-d775-452f-bd79-d46a801040ac","VG_GID":"cbc5852a-34d4-43e4-bd67-ab191d34625d","SC_GD":"5c2f188e-8a2b-42af-8f98-881134914ab4","SR_Number":1,"SR_UpdateTime":"2018-01-13 15:02:56","SG_Name":"洗头"}]},{"VG_IsDownLock":1,"VG_IsTimeTimes":null,"GID":"b4273b8b-8617-4a64-9907-cbc284966bbd","US_Code":"IntegerFulfil","US_Value":100,"VG_Name":"银牌会员","VG_Code":"LV1","VG_CardAmount":0,"VG_InitialAmount":0,"VG_InitialIntegral":0,"VG_IsLock":1,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":1,"VG_IsTimeNum":6,"VG_IsTimeUnit":"天","VG_Remark":"","DS_Value":100,"RS_CMoney":1,"RS_Value":0,"VS_CMoney":1,"VS_Value":0,"US_Name":"积分达到","Settings":[{"VG_GID":"b4273b8b-8617-4a64-9907-cbc284966bbd","VG_Name":"银牌会员","PT_GID":"0be06d90-11c6-46f0-bbf1-7c7ca20f46df","PT_Name":"洗剪吹服务","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"fad86152-3f15-42bc-a20d-e723bf95b371","PT_SynType":"0"},{"VG_GID":"b4273b8b-8617-4a64-9907-cbc284966bbd","VG_Name":"银牌会员","PT_GID":"14265702-ca99-4cf0-8537-821ce39c8996","PT_Name":"手机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"b4273b8b-8617-4a64-9907-cbc284966bbd","VG_Name":"银牌会员","PT_GID":"171c168e-e00b-4f2d-86d4-b1a9a5c5f0aa","PT_Name":"文学","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"26bbcaf4-952a-4eca-8078-c3cbdb0a6f20","PT_SynType":"0"},{"VG_GID":"b4273b8b-8617-4a64-9907-cbc284966bbd","VG_Name":"银牌会员","PT_GID":"95d7f9ce-1f98-43e3-9648-8c76943c8b5d","PT_Name":"洗澡","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"fad86152-3f15-42bc-a20d-e723bf95b371","PT_SynType":"0"},{"VG_GID":"b4273b8b-8617-4a64-9907-cbc284966bbd","VG_Name":"银牌会员","PT_GID":"86efb174-e863-4c52-9029-186de4b576bf","PT_Name":"抽纸","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"4657c0e0-b1dc-4850-bfb2-be23c2dfefb2","PT_SynType":"0"},{"VG_GID":"b4273b8b-8617-4a64-9907-cbc284966bbd","VG_Name":"银牌会员","PT_GID":"e8407dd0-f3b2-4c2e-8a38-67a93b9c36c3","PT_Name":"编程","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"26bbcaf4-952a-4eca-8078-c3cbdb0a6f20","PT_SynType":"0"}],"InitServiceList":[]},{"VG_IsDownLock":0,"VG_IsTimeTimes":0,"GID":"2acb6712-58cf-466b-b363-fce96e6c03b4","US_Code":"IntegerFulfil","US_Value":1000,"VG_Name":"金牌会员","VG_Code":"LV2","VG_CardAmount":0,"VG_InitialAmount":0,"VG_InitialIntegral":0,"VG_IsLock":1,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":1,"VG_IsTimeNum":0,"VG_IsTimeUnit":"天","VG_Remark":"","DS_Value":100,"RS_CMoney":1,"RS_Value":0,"VS_CMoney":1,"VS_Value":0,"US_Name":"积分达到","Settings":[{"VG_GID":"2acb6712-58cf-466b-b363-fce96e6c03b4","VG_Name":"金牌会员","PT_GID":"0be06d90-11c6-46f0-bbf1-7c7ca20f46df","PT_Name":"洗剪吹服务","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"fad86152-3f15-42bc-a20d-e723bf95b371","PT_SynType":"0"},{"VG_GID":"2acb6712-58cf-466b-b363-fce96e6c03b4","VG_Name":"金牌会员","PT_GID":"14265702-ca99-4cf0-8537-821ce39c8996","PT_Name":"手机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"2acb6712-58cf-466b-b363-fce96e6c03b4","VG_Name":"金牌会员","PT_GID":"171c168e-e00b-4f2d-86d4-b1a9a5c5f0aa","PT_Name":"文学","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"26bbcaf4-952a-4eca-8078-c3cbdb0a6f20","PT_SynType":"0"},{"VG_GID":"2acb6712-58cf-466b-b363-fce96e6c03b4","VG_Name":"金牌会员","PT_GID":"95d7f9ce-1f98-43e3-9648-8c76943c8b5d","PT_Name":"洗澡","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"fad86152-3f15-42bc-a20d-e723bf95b371","PT_SynType":"0"},{"VG_GID":"2acb6712-58cf-466b-b363-fce96e6c03b4","VG_Name":"金牌会员","PT_GID":"86efb174-e863-4c52-9029-186de4b576bf","PT_Name":"抽纸","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"4657c0e0-b1dc-4850-bfb2-be23c2dfefb2","PT_SynType":"0"},{"VG_GID":"2acb6712-58cf-466b-b363-fce96e6c03b4","VG_Name":"金牌会员","PT_GID":"e8407dd0-f3b2-4c2e-8a38-67a93b9c36c3","PT_Name":"编程","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"26bbcaf4-952a-4eca-8078-c3cbdb0a6f20","PT_SynType":"0"}],"InitServiceList":[{"SR_Timer":null,"SR_TimeUnit":null,"GID":"bf41a69b-35f0-463b-acb8-eda27b52cb26","VG_GID":"2acb6712-58cf-466b-b363-fce96e6c03b4","SC_GD":"ea01ba75-0582-4128-a20a-ebe23dccaa18","SR_Number":1,"SR_UpdateTime":"2018-01-12 14:14:57","SG_Name":"洗澡"}]}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

}
