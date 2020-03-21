package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2018/1/24 14:42.
 * 邮箱：137615198@qq.com
 */

public class EditGradeResultBean implements Serializable{
    private static final long UID = 1L;
    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"VG_IsDownLock":0,"VG_IsTimeTimes":0,"GID":"ab535e93-5b37-4298-ab98-25566110e68c","US_Code":"IntegerFulfil","US_Value":10000,"VG_Name":"钻石会员","VG_Code":"","VG_CardAmount":0,"VG_InitialAmount":0,"VG_InitialIntegral":0,"VG_IsLock":1,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":1,"VG_IsTimeNum":0,"VG_IsTimeUnit":"天","VG_Remark":"","VG_IntegralUniformRuleValue":0,"VG_IntegralRuleType":0,"VG_DiscountUniformRuleValue":0,"VG_DiscountRuleType":0,"DS_Value":100,"RS_CMoney":1,"RS_Value":0,"VS_CMoney":1,"VS_Value":0,"US_Name":"积分达到","Settings":[{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"0be06d90-11c6-46f0-bbf1-7c7ca20f46df","PT_Name":"洗剪吹服务","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"fad86152-3f15-42bc-a20d-e723bf95b371","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"3162a04d-1536-462a-afe7-2401fda74423","PT_Name":"你以为","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"9d8820a3-c5ca-4c3e-9d7a-adc6b963d626","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"14265702-ca99-4cf0-8537-821ce39c8996","PT_Name":"手机","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"edf329ec-9cec-47ed-9a4a-c3caab340f18","PT_Name":"你赢","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"c1b5b2cd-d833-4d34-a99e-ab4e23740ad6","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"06a14315-8b7e-428a-a842-fbfe91862a5d","PT_Name":"明年","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"66c82897-be1f-4dd7-9718-ad98e61f613e","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"9b6bf98e-9b02-4673-95bf-f4a6792b4a38","PT_Name":"您给","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"a4be19d5-3fd4-47ef-b8b7-4d46fb3ed882","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"66c82897-be1f-4dd7-9718-ad98e61f613e","PT_Name":"命里","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"171c168e-e00b-4f2d-86d4-b1a9a5c5f0aa","PT_Name":"文学","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"26bbcaf4-952a-4eca-8078-c3cbdb0a6f20","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"47630789-1df3-4215-8693-d68282be6785","PT_Name":"哦7米咯哈","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"66c82897-be1f-4dd7-9718-ad98e61f613e","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"95d7f9ce-1f98-43e3-9648-8c76943c8b5d","PT_Name":"洗澡","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"fad86152-3f15-42bc-a20d-e723bf95b371","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"26bbcaf4-952a-4eca-8078-c3cbdb0a6f20","PT_Name":"书籍","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"62b9fa1b-47c7-4641-a154-5a0b78483bae","PT_Name":"急急急咯","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"2cd68e8e-e08a-42d1-a483-39af27d940d6","PT_Name":"你明明","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"9d8820a3-c5ca-4c3e-9d7a-adc6b963d626","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"1892f9a1-e0a4-4ba3-b6f2-d49bfd869fa3","PT_Name":"为啥子你呢","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"9d8820a3-c5ca-4c3e-9d7a-adc6b963d626","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"fad86152-3f15-42bc-a20d-e723bf95b371","PT_Name":"服务","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"86efb174-e863-4c52-9029-186de4b576bf","PT_Name":"抽纸","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"4657c0e0-b1dc-4850-bfb2-be23c2dfefb2","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"e8407dd0-f3b2-4c2e-8a38-67a93b9c36c3","PT_Name":"编程","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"26bbcaf4-952a-4eca-8078-c3cbdb0a6f20","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"a8449c28-a621-4ea1-91a8-7b0d04885232","PT_Name":"娱乐无极限","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"c1b5b2cd-d833-4d34-a99e-ab4e23740ad6","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"cb12e282-0b98-4eab-84b3-83cd12312971","PT_Name":"丽丽姐","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"a4be19d5-3fd4-47ef-b8b7-4d46fb3ed882","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"5258102b-1fb5-435b-9476-8182eaa2cae0","PT_Name":"第几节","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"c1b5b2cd-d833-4d34-a99e-ab4e23740ad6","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"a4be19d5-3fd4-47ef-b8b7-4d46fb3ed882","PT_Name":"铭记","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"c1b5b2cd-d833-4d34-a99e-ab4e23740ad6","PT_Name":"顶级了","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"9d8820a3-c5ca-4c3e-9d7a-adc6b963d626","PT_Name":"急急急梦入洪荒","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"e29240e6-88e2-4f7f-ae6c-146b5d915c8a","PT_Name":"一曲相送PK","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"a4be19d5-3fd4-47ef-b8b7-4d46fb3ed882","PT_SynType":"0"},{"VG_GID":"ab535e93-5b37-4298-ab98-25566110e68c","VG_Name":"钻石会员","PT_GID":"4657c0e0-b1dc-4850-bfb2-be23c2dfefb2","PT_Name":"纸巾","PT_Type":"商品","PD_Discount":0,"VS_CMoney":0,"VS_Number":1,"SM_GID":"af4b45fc-bab8-4af4-a3b8-83cbc5896d3c","SM_Name":"默认店铺","PT_Parent":"","PT_SynType":"0"}],"InitServiceList":null}
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
}
