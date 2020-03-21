package com.zhiluo.android.yunpu.member.jsonbean;

import java.util.List;

/**
 * iauthor：Cheng
 * date: 2017/6/7 01:35
 * email：jasoncheng9111@gmail.com
 */
public class VIPGradeQueryDataListJsonBean
{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","US_Code":"IntegerFulfil","US_Value":0,"VG_Name":"默认等级","VG_Code":"01","VG_CardAmount":0,"VG_InitialAmount":100,
     * "VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":0,"VG_IsTime":1,"VG_IsTimeNum":0,"VG_IsTimeUnit":"天","VG_Remark":"","DS_Value":100,
     * "RS_CMoney":1,"RS_Value":10,"VS_CMoney":1,"VS_Value":10,"US_Name":"积分达到","Settings":[{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级",
     * "PT_GID":"a33cc6d3-430c-4b2f-8580-eddf8aedd6d2","PT_Name":"iMac","PT_Type":"商品","PD_Discount":80,"VS_CMoney":1,"VS_Number":100,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级",
     * "PT_GID":"452bd56b-0ffe-47e6-a51c-220061611058","PT_Name":"iphone","PT_Type":"商品","PD_Discount":50,"VS_CMoney":1,"VS_Number":100,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级",
     * "PT_GID":"0385858e-e4d8-4101-924b-6f3a088e5be7","PT_Name":"西点","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":10,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级",
     * "PT_GID":"8a98df6e-d470-4f18-ba80-49099d73c3a6","PT_Name":"计算机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":10,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级",
     * "PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":10,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级",
     * "PT_GID":"77e9dbdc-b3a0-4231-af18-bc3dfbd90fbd","PT_Name":"大概","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":10,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级",
     * "PT_GID":"eaa60fd6-e69d-402f-bb7f-d989d9a23427","PT_Name":"隆康路","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":10,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"}],"InitServiceList":[]},{"GID":"cc55afb7-d9ae-4ccd-b050-81e8edda3c42","US_Code":"IntegerFulfil",
     * "US_Value":1,"VG_Name":"黄铜","VG_Code":"02","VG_CardAmount":0,"VG_InitialAmount":0,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,
     * "VG_IsTime":0,"VG_IsTimeNum":0,"VG_IsTimeUnit":"天","VG_Remark":"","DS_Value":100,"RS_CMoney":1,"RS_Value":0,"VS_CMoney":1,"VS_Value":0,"US_Name":"积分达到",
     * "Settings":[{"VG_GID":"cc55afb7-d9ae-4ccd-b050-81e8edda3c42","VG_Name":"黄铜","PT_GID":"a33cc6d3-430c-4b2f-8580-eddf8aedd6d2","PT_Name":"iMac","PT_Type":"商品",
     * "PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"cc55afb7-d9ae-4ccd-b050-81e8edda3c42",
     * "VG_Name":"黄铜","PT_GID":"452bd56b-0ffe-47e6-a51c-220061611058","PT_Name":"iphone","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"cc55afb7-d9ae-4ccd-b050-81e8edda3c42","VG_Name":"黄铜",
     * "PT_GID":"0385858e-e4d8-4101-924b-6f3a088e5be7","PT_Name":"西点","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"cc55afb7-d9ae-4ccd-b050-81e8edda3c42","VG_Name":"黄铜",
     * "PT_GID":"8a98df6e-d470-4f18-ba80-49099d73c3a6","PT_Name":"计算机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"cc55afb7-d9ae-4ccd-b050-81e8edda3c42","VG_Name":"黄铜",
     * "PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"cc55afb7-d9ae-4ccd-b050-81e8edda3c42","VG_Name":"黄铜",
     * "PT_GID":"77e9dbdc-b3a0-4231-af18-bc3dfbd90fbd","PT_Name":"大概","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"cc55afb7-d9ae-4ccd-b050-81e8edda3c42","VG_Name":"黄铜",
     * "PT_GID":"eaa60fd6-e69d-402f-bb7f-d989d9a23427","PT_Name":"隆康路","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"}],"InitServiceList":[]},{"GID":"aae580c3-ff64-4fa8-9308-7d00e2a67143","US_Code":"IntegerFulfil",
     * "US_Value":2,"VG_Name":"白银","VG_Code":"03","VG_CardAmount":0,"VG_InitialAmount":0,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,
     * "VG_IsTime":0,"VG_IsTimeNum":0,"VG_IsTimeUnit":"天","VG_Remark":"","DS_Value":100,"RS_CMoney":1,"RS_Value":0,"VS_CMoney":1,"VS_Value":0,"US_Name":"积分达到",
     * "Settings":[{"VG_GID":"aae580c3-ff64-4fa8-9308-7d00e2a67143","VG_Name":"白银","PT_GID":"a33cc6d3-430c-4b2f-8580-eddf8aedd6d2","PT_Name":"iMac","PT_Type":"商品",
     * "PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"aae580c3-ff64-4fa8-9308-7d00e2a67143",
     * "VG_Name":"白银","PT_GID":"452bd56b-0ffe-47e6-a51c-220061611058","PT_Name":"iphone","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"aae580c3-ff64-4fa8-9308-7d00e2a67143","VG_Name":"白银",
     * "PT_GID":"0385858e-e4d8-4101-924b-6f3a088e5be7","PT_Name":"西点","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"aae580c3-ff64-4fa8-9308-7d00e2a67143","VG_Name":"白银",
     * "PT_GID":"8a98df6e-d470-4f18-ba80-49099d73c3a6","PT_Name":"计算机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"aae580c3-ff64-4fa8-9308-7d00e2a67143","VG_Name":"白银",
     * "PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"aae580c3-ff64-4fa8-9308-7d00e2a67143","VG_Name":"白银",
     * "PT_GID":"77e9dbdc-b3a0-4231-af18-bc3dfbd90fbd","PT_Name":"大概","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"aae580c3-ff64-4fa8-9308-7d00e2a67143","VG_Name":"白银",
     * "PT_GID":"eaa60fd6-e69d-402f-bb7f-d989d9a23427","PT_Name":"隆康路","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"}],"InitServiceList":[]},{"GID":"61547ec4-5490-4f09-b1f6-c7dab1ec9f5c","US_Code":"IntegerFulfil",
     * "US_Value":3,"VG_Name":"黄金","VG_Code":"04","VG_CardAmount":0,"VG_InitialAmount":0,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,
     * "VG_IsTime":0,"VG_IsTimeNum":0,"VG_IsTimeUnit":"天","VG_Remark":"","DS_Value":100,"RS_CMoney":1,"RS_Value":0,"VS_CMoney":1,"VS_Value":0,"US_Name":"积分达到",
     * "Settings":[{"VG_GID":"61547ec4-5490-4f09-b1f6-c7dab1ec9f5c","VG_Name":"黄金","PT_GID":"a33cc6d3-430c-4b2f-8580-eddf8aedd6d2","PT_Name":"iMac","PT_Type":"商品",
     * "PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"61547ec4-5490-4f09-b1f6-c7dab1ec9f5c",
     * "VG_Name":"黄金","PT_GID":"452bd56b-0ffe-47e6-a51c-220061611058","PT_Name":"iphone","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"61547ec4-5490-4f09-b1f6-c7dab1ec9f5c","VG_Name":"黄金",
     * "PT_GID":"0385858e-e4d8-4101-924b-6f3a088e5be7","PT_Name":"西点","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"61547ec4-5490-4f09-b1f6-c7dab1ec9f5c","VG_Name":"黄金",
     * "PT_GID":"8a98df6e-d470-4f18-ba80-49099d73c3a6","PT_Name":"计算机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"61547ec4-5490-4f09-b1f6-c7dab1ec9f5c","VG_Name":"黄金",
     * "PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"61547ec4-5490-4f09-b1f6-c7dab1ec9f5c","VG_Name":"黄金",
     * "PT_GID":"77e9dbdc-b3a0-4231-af18-bc3dfbd90fbd","PT_Name":"大概","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"61547ec4-5490-4f09-b1f6-c7dab1ec9f5c","VG_Name":"黄金",
     * "PT_GID":"eaa60fd6-e69d-402f-bb7f-d989d9a23427","PT_Name":"隆康路","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"}],"InitServiceList":[]},{"GID":"c25b3b70-bacf-40ee-9f87-48829555868f","US_Code":"IntegerFulfil",
     * "US_Value":4,"VG_Name":"铂金","VG_Code":"05","VG_CardAmount":0,"VG_InitialAmount":0,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,
     * "VG_IsTime":0,"VG_IsTimeNum":0,"VG_IsTimeUnit":"天","VG_Remark":"","DS_Value":100,"RS_CMoney":1,"RS_Value":0,"VS_CMoney":1,"VS_Value":0,"US_Name":"积分达到",
     * "Settings":[{"VG_GID":"c25b3b70-bacf-40ee-9f87-48829555868f","VG_Name":"铂金","PT_GID":"a33cc6d3-430c-4b2f-8580-eddf8aedd6d2","PT_Name":"iMac","PT_Type":"商品",
     * "PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c25b3b70-bacf-40ee-9f87-48829555868f",
     * "VG_Name":"铂金","PT_GID":"452bd56b-0ffe-47e6-a51c-220061611058","PT_Name":"iphone","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c25b3b70-bacf-40ee-9f87-48829555868f","VG_Name":"铂金",
     * "PT_GID":"0385858e-e4d8-4101-924b-6f3a088e5be7","PT_Name":"西点","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c25b3b70-bacf-40ee-9f87-48829555868f","VG_Name":"铂金",
     * "PT_GID":"8a98df6e-d470-4f18-ba80-49099d73c3a6","PT_Name":"计算机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c25b3b70-bacf-40ee-9f87-48829555868f","VG_Name":"铂金",
     * "PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c25b3b70-bacf-40ee-9f87-48829555868f","VG_Name":"铂金",
     * "PT_GID":"77e9dbdc-b3a0-4231-af18-bc3dfbd90fbd","PT_Name":"大概","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c25b3b70-bacf-40ee-9f87-48829555868f","VG_Name":"铂金",
     * "PT_GID":"eaa60fd6-e69d-402f-bb7f-d989d9a23427","PT_Name":"隆康路","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"}],"InitServiceList":[]},{"GID":"bbd30248-983e-443f-9d84-f92a9993c13a","US_Code":"IntegerFulfil",
     * "US_Value":5,"VG_Name":"大师","VG_Code":"06","VG_CardAmount":0,"VG_InitialAmount":0,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,
     * "VG_IsTime":0,"VG_IsTimeNum":0,"VG_IsTimeUnit":"天","VG_Remark":"","DS_Value":100,"RS_CMoney":1,"RS_Value":0,"VS_CMoney":1,"VS_Value":0,"US_Name":"积分达到",
     * "Settings":[{"VG_GID":"bbd30248-983e-443f-9d84-f92a9993c13a","VG_Name":"大师","PT_GID":"a33cc6d3-430c-4b2f-8580-eddf8aedd6d2","PT_Name":"iMac","PT_Type":"商品",
     * "PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"bbd30248-983e-443f-9d84-f92a9993c13a",
     * "VG_Name":"大师","PT_GID":"452bd56b-0ffe-47e6-a51c-220061611058","PT_Name":"iphone","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"bbd30248-983e-443f-9d84-f92a9993c13a","VG_Name":"大师",
     * "PT_GID":"0385858e-e4d8-4101-924b-6f3a088e5be7","PT_Name":"西点","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"bbd30248-983e-443f-9d84-f92a9993c13a","VG_Name":"大师",
     * "PT_GID":"8a98df6e-d470-4f18-ba80-49099d73c3a6","PT_Name":"计算机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"bbd30248-983e-443f-9d84-f92a9993c13a","VG_Name":"大师",
     * "PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"bbd30248-983e-443f-9d84-f92a9993c13a","VG_Name":"大师",
     * "PT_GID":"77e9dbdc-b3a0-4231-af18-bc3dfbd90fbd","PT_Name":"大概","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"bbd30248-983e-443f-9d84-f92a9993c13a","VG_Name":"大师",
     * "PT_GID":"eaa60fd6-e69d-402f-bb7f-d989d9a23427","PT_Name":"隆康路","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"}],"InitServiceList":[]},{"GID":"c626358c-470b-40e2-ab7f-bd0b6f872841","US_Code":"IntegerFulfil",
     * "US_Value":6,"VG_Name":"王者","VG_Code":"07","VG_CardAmount":0,"VG_InitialAmount":0,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,
     * "VG_IsTime":0,"VG_IsTimeNum":0,"VG_IsTimeUnit":"天","VG_Remark":"","DS_Value":100,"RS_CMoney":1,"RS_Value":0,"VS_CMoney":1,"VS_Value":0,"US_Name":"积分达到",
     * "Settings":[{"VG_GID":"c626358c-470b-40e2-ab7f-bd0b6f872841","VG_Name":"王者","PT_GID":"a33cc6d3-430c-4b2f-8580-eddf8aedd6d2","PT_Name":"iMac","PT_Type":"商品",
     * "PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c626358c-470b-40e2-ab7f-bd0b6f872841",
     * "VG_Name":"王者","PT_GID":"452bd56b-0ffe-47e6-a51c-220061611058","PT_Name":"iphone","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c626358c-470b-40e2-ab7f-bd0b6f872841","VG_Name":"王者",
     * "PT_GID":"0385858e-e4d8-4101-924b-6f3a088e5be7","PT_Name":"西点","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":55,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c626358c-470b-40e2-ab7f-bd0b6f872841","VG_Name":"王者",
     * "PT_GID":"8a98df6e-d470-4f18-ba80-49099d73c3a6","PT_Name":"计算机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c626358c-470b-40e2-ab7f-bd0b6f872841","VG_Name":"王者",
     * "PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c626358c-470b-40e2-ab7f-bd0b6f872841","VG_Name":"王者",
     * "PT_GID":"77e9dbdc-b3a0-4231-af18-bc3dfbd90fbd","PT_Name":"大概","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"c626358c-470b-40e2-ab7f-bd0b6f872841","VG_Name":"王者",
     * "PT_GID":"eaa60fd6-e69d-402f-bb7f-d989d9a23427","PT_Name":"隆康路","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,
     * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"}],"InitServiceList":[]}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<DataBean> data;

    public boolean isSuccess() { return success;}

    public void setSuccess(boolean success) { this.success = success;}

    public Object getCode() { return code;}

    public void setCode(Object code) { this.code = code;}

    public String getMsg() { return msg;}

    public void setMsg(String msg) { this.msg = msg;}

    public List<DataBean> getData() { return data;}

    public void setData(List<DataBean> data) { this.data = data;}

    public static class DataBean
    {
        /**
         * GID : 3b347ef4-0270-427e-bc3a-2964c661ffb1
         * US_Code : IntegerFulfil
         * US_Value : 0
         * VG_Name : 默认等级
         * VG_Code : 01
         * VG_CardAmount : 0
         * VG_InitialAmount : 100
         * VG_IsLock : 0
         * VG_IsAccount : 1
         * VG_IsIntegral : 1
         * VG_IsDiscount : 1
         * VG_IsCount : 0
         * VG_IsTime : 1
         * VG_IsTimeNum : 0
         * VG_IsTimeUnit : 天
         * VG_Remark :
         * DS_Value : 100
         * RS_CMoney : 1
         * RS_Value : 10
         * VS_CMoney : 1
         * VS_Value : 10
         * US_Name : 积分达到
         * Settings : [{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级","PT_GID":"a33cc6d3-430c-4b2f-8580-eddf8aedd6d2","PT_Name":"iMac","PT_Type":"商品",
         * "PD_Discount":80,"VS_CMoney":1,"VS_Number":100,"SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},
         * {"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级","PT_GID":"452bd56b-0ffe-47e6-a51c-220061611058","PT_Name":"iphone","PT_Type":"商品","PD_Discount":50,
         * "VS_CMoney":1,"VS_Number":100,"SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1",
         * "VG_Name":"默认等级","PT_GID":"0385858e-e4d8-4101-924b-6f3a088e5be7","PT_Name":"西点","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":10,
         * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级",
         * "PT_GID":"8a98df6e-d470-4f18-ba80-49099d73c3a6","PT_Name":"计算机","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":10,
         * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级",
         * "PT_GID":"64963879-1d6f-4501-afce-65661fa7e5f4","PT_Name":"哈","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":10,
         * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级",
         * "PT_GID":"77e9dbdc-b3a0-4231-af18-bc3dfbd90fbd","PT_Name":"大概","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":10,
         * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"},{"VG_GID":"3b347ef4-0270-427e-bc3a-2964c661ffb1","VG_Name":"默认等级",
         * "PT_GID":"eaa60fd6-e69d-402f-bb7f-d989d9a23427","PT_Name":"隆康路","PT_Type":"服务","PD_Discount":100,"VS_CMoney":1,"VS_Number":10,
         * "SM_GID":"bfb6cf66-cf97-4239-bb1e-bece3a86d09f","SM_Name":"Tokyo Hot"}]
         * InitServiceList : []
         */

        private String GID;
        private String US_Code;
        private int US_Value;
        private String VG_Name;
        private String VG_Code;
        private int VG_CardAmount;
        private int VG_InitialAmount;
        private int VG_IsLock;
        private int VG_IsAccount;
        private int VG_IsIntegral;
        private int VG_IsDiscount;
        private int VG_IsCount;
        private int VG_IsTime;
        private int VG_IsTimeNum;
        private String VG_IsTimeUnit;
        private String VG_Remark;
        private int DS_Value;
        private int RS_CMoney;
        private int RS_Value;
        private int VS_CMoney;
        private int VS_Value;
        private String US_Name;
        private List<SettingsBean> Settings;
        private List<?> InitServiceList;

        public String getGID() { return GID;}

        public void setGID(String GID) { this.GID = GID;}

        public String getUS_Code() { return US_Code;}

        public void setUS_Code(String US_Code) { this.US_Code = US_Code;}

        public int getUS_Value() { return US_Value;}

        public void setUS_Value(int US_Value) { this.US_Value = US_Value;}

        public String getVG_Name() { return VG_Name;}

        public void setVG_Name(String VG_Name) { this.VG_Name = VG_Name;}

        public String getVG_Code() { return VG_Code;}

        public void setVG_Code(String VG_Code) { this.VG_Code = VG_Code;}

        public int getVG_CardAmount() { return VG_CardAmount;}

        public void setVG_CardAmount(int VG_CardAmount) { this.VG_CardAmount = VG_CardAmount;}

        public int getVG_InitialAmount() { return VG_InitialAmount;}

        public void setVG_InitialAmount(int VG_InitialAmount) { this.VG_InitialAmount = VG_InitialAmount;}

        public int getVG_IsLock() { return VG_IsLock;}

        public void setVG_IsLock(int VG_IsLock) { this.VG_IsLock = VG_IsLock;}

        public int getVG_IsAccount() { return VG_IsAccount;}

        public void setVG_IsAccount(int VG_IsAccount) { this.VG_IsAccount = VG_IsAccount;}

        public int getVG_IsIntegral() { return VG_IsIntegral;}

        public void setVG_IsIntegral(int VG_IsIntegral) { this.VG_IsIntegral = VG_IsIntegral;}

        public int getVG_IsDiscount() { return VG_IsDiscount;}

        public void setVG_IsDiscount(int VG_IsDiscount) { this.VG_IsDiscount = VG_IsDiscount;}

        public int getVG_IsCount() { return VG_IsCount;}

        public void setVG_IsCount(int VG_IsCount) { this.VG_IsCount = VG_IsCount;}

        public int getVG_IsTime() { return VG_IsTime;}

        public void setVG_IsTime(int VG_IsTime) { this.VG_IsTime = VG_IsTime;}

        public int getVG_IsTimeNum() { return VG_IsTimeNum;}

        public void setVG_IsTimeNum(int VG_IsTimeNum) { this.VG_IsTimeNum = VG_IsTimeNum;}

        public String getVG_IsTimeUnit() { return VG_IsTimeUnit;}

        public void setVG_IsTimeUnit(String VG_IsTimeUnit) { this.VG_IsTimeUnit = VG_IsTimeUnit;}

        public String getVG_Remark() { return VG_Remark;}

        public void setVG_Remark(String VG_Remark) { this.VG_Remark = VG_Remark;}

        public int getDS_Value() { return DS_Value;}

        public void setDS_Value(int DS_Value) { this.DS_Value = DS_Value;}

        public int getRS_CMoney() { return RS_CMoney;}

        public void setRS_CMoney(int RS_CMoney) { this.RS_CMoney = RS_CMoney;}

        public int getRS_Value() { return RS_Value;}

        public void setRS_Value(int RS_Value) { this.RS_Value = RS_Value;}

        public int getVS_CMoney() { return VS_CMoney;}

        public void setVS_CMoney(int VS_CMoney) { this.VS_CMoney = VS_CMoney;}

        public int getVS_Value() { return VS_Value;}

        public void setVS_Value(int VS_Value) { this.VS_Value = VS_Value;}

        public String getUS_Name() { return US_Name;}

        public void setUS_Name(String US_Name) { this.US_Name = US_Name;}

        public List<SettingsBean> getSettings() { return Settings;}

        public void setSettings(List<SettingsBean> Settings) { this.Settings = Settings;}

        public List<?> getInitServiceList() { return InitServiceList;}

        public void setInitServiceList(List<?> InitServiceList) { this.InitServiceList = InitServiceList;}

        public static class SettingsBean
        {
            /**
             * VG_GID : 3b347ef4-0270-427e-bc3a-2964c661ffb1
             * VG_Name : 默认等级
             * PT_GID : a33cc6d3-430c-4b2f-8580-eddf8aedd6d2
             * PT_Name : iMac
             * PT_Type : 商品
             * PD_Discount : 80
             * VS_CMoney : 1
             * VS_Number : 100
             * SM_GID : bfb6cf66-cf97-4239-bb1e-bece3a86d09f
             * SM_Name : Tokyo Hot
             */

            private String VG_GID;
            private String VG_Name;
            private String PT_GID;
            private String PT_Name;
            private String PT_Type;
            private int PD_Discount;
            private int VS_CMoney;
            private int VS_Number;
            private String SM_GID;
            private String SM_Name;

            public String getVG_GID() { return VG_GID;}

            public void setVG_GID(String VG_GID) { this.VG_GID = VG_GID;}

            public String getVG_Name() { return VG_Name;}

            public void setVG_Name(String VG_Name) { this.VG_Name = VG_Name;}

            public String getPT_GID() { return PT_GID;}

            public void setPT_GID(String PT_GID) { this.PT_GID = PT_GID;}

            public String getPT_Name() { return PT_Name;}

            public void setPT_Name(String PT_Name) { this.PT_Name = PT_Name;}

            public String getPT_Type() { return PT_Type;}

            public void setPT_Type(String PT_Type) { this.PT_Type = PT_Type;}

            public int getPD_Discount() { return PD_Discount;}

            public void setPD_Discount(int PD_Discount) { this.PD_Discount = PD_Discount;}

            public int getVS_CMoney() { return VS_CMoney;}

            public void setVS_CMoney(int VS_CMoney) { this.VS_CMoney = VS_CMoney;}

            public int getVS_Number() { return VS_Number;}

            public void setVS_Number(int VS_Number) { this.VS_Number = VS_Number;}

            public String getSM_GID() { return SM_GID;}

            public void setSM_GID(String SM_GID) { this.SM_GID = SM_GID;}

            public String getSM_Name() { return SM_Name;}

            public void setSM_Name(String SM_Name) { this.SM_Name = SM_Name;}
        }
    }
}
