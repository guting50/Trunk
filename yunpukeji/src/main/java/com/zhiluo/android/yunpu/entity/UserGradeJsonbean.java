package com.zhiluo.android.yunpu.entity;

import java.util.List;

/**
 * iauthor：Cheng
 * date: 2017/5/27 10:58
 * email：jasoncheng9111@gmail.com
 */
public class UserGradeJsonbean
{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"VG_IsDownLock":1,"VG_UpDownType":0,"VG_NextGradeName":"","VG_NextGradeGID":"","VG_LastGradeName":"三等级","VG_LastGradeGID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","US_ValueMax":null,"VG_IsTimeTimes":null,"VG_OilIntegral":0,"GID":"98c73594-ac37-48c5-8773-375b5a290c0e","US_Code":null,"US_Value":1000,"VG_Name":"特等级","VG_Code":"","VG_CardAmount":10,"VG_InitialAmount":100,"VG_InitialIntegral":100,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":0,"VG_IsTimeNum":null,"VG_IsTimeUnit":null,"VG_Remark":"","VG_IntegralUniformRuleValue":100,"VG_IntegralRuleType":0,"VG_DiscountUniformRuleValue":85,"VG_DiscountRuleType":0,"DS_Value":85,"RS_CMoney":0.1,"RS_Value":1,"VS_CMoney":0.1,"VS_Value":1,"US_Name":null,"Settings":[{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"d6eae90a-578c-4fd5-8015-150cf20dbc93","PT_Name":"899","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"a53f70f0-0032-482f-90d2-0173a7079bee","PT_Name":"水果类","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"80459eff-53fa-4819-b34f-d5b921ca9c44","PT_Name":"777","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"3ef581f9-098d-4112-9664-20f1d7638839","PT_Name":"21324","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"bc2f597a-c3cf-4f88-8834-b465e9da885e","PT_Name":"蔬菜","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"00e0e50f-52d3-48bc-a1a2-b87303691010","PT_Name":"补水","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"0fe55592-e37f-40ee-aa3c-a20d4dcab634","PT_Name":"T恤","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"1034d47d-b1ce-4968-92eb-eefa02a6861e","PT_Name":"美妆","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"12e57bfd-6830-4bbc-bfee-3305aa6540ef","PT_Name":"黑龙江大米","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"26d238e6-8b85-4484-95bd-a27d8208ca4e","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"2061649e-402b-4834-b773-759ef8cd4dcd","PT_Name":"12","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"e5c6c224-829c-4bdf-a829-3a964962c76f","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"28d8e8b4-cd64-43b9-b3aa-aa23d83603c3","PT_Name":"保湿","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"37b32140-ef07-4184-a233-aa599dca6461","PT_Name":"美白","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"4aa4fe3f-cfd0-4bce-9c3f-246700fd17a0","PT_Name":"男装","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"9aabd76d-9fea-4e29-8381-7802ca756744","PT_Name":"女装","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"ce51680f-c246-489b-9d82-d93edd5514ad","PT_Name":"2","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"ead17b2d-bf10-4f0a-93fa-cec636c00f3f","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"e67d3c51-cc0a-4d28-8cad-70718fcf4c1b","PT_Name":"花店","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"e9630b91-5f66-442d-9ae8-11e99ef49b7b","PT_Name":"111","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"52851e49-93c8-449d-8fe3-74ccbb2d9b85","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"f1bfb818-df74-4723-911d-094466c5708c","PT_Name":"护肤","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"}],"InitServiceList":[{"SR_Timer":null,"SR_TimeUnit":null,"GID":"1c253af6-c5c5-4ef8-8bc6-c548cec32a66","VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","SC_GD":"ff9be1a9-ff7c-49fd-ad6f-172e148148c8","SR_Number":4,"SR_UpdateTime":"2019-09-25 09:37:41","SG_Name":"美容"},{"SR_Timer":null,"SR_TimeUnit":null,"GID":"72ff2053-d1bf-481c-8f09-9143ce5e6c8c","VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","SC_GD":"487b47e0-e147-460c-b598-bc9bf5c31cf2","SR_Number":4,"SR_UpdateTime":"2019-09-25 09:37:41","SG_Name":"按摩"}]},{"VG_IsDownLock":0,"VG_UpDownType":0,"VG_NextGradeName":"","VG_NextGradeGID":"","VG_LastGradeName":"","VG_LastGradeGID":"","US_ValueMax":null,"VG_IsTimeTimes":null,"VG_OilIntegral":0,"GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","US_Code":"IntegerFulfil","US_Value":null,"VG_Name":"初始等级","VG_Code":"","VG_CardAmount":0,"VG_InitialAmount":0,"VG_InitialIntegral":0,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":0,"VG_IsTimeNum":null,"VG_IsTimeUnit":null,"VG_Remark":"","VG_IntegralUniformRuleValue":2,"VG_IntegralRuleType":0,"VG_DiscountUniformRuleValue":0,"VG_DiscountRuleType":1,"DS_Value":100,"RS_CMoney":2,"RS_Value":1,"VS_CMoney":2,"VS_Value":1,"US_Name":"积分达到","Settings":[{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"d6eae90a-578c-4fd5-8015-150cf20dbc93","PT_Name":"899","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"a53f70f0-0032-482f-90d2-0173a7079bee","PT_Name":"水果类","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"80459eff-53fa-4819-b34f-d5b921ca9c44","PT_Name":"777","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"3ef581f9-098d-4112-9664-20f1d7638839","PT_Name":"21324","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"bc2f597a-c3cf-4f88-8834-b465e9da885e","PT_Name":"蔬菜","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"00e0e50f-52d3-48bc-a1a2-b87303691010","PT_Name":"补水","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"0"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"0fe55592-e37f-40ee-aa3c-a20d4dcab634","PT_Name":"T恤","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"1034d47d-b1ce-4968-92eb-eefa02a6861e","PT_Name":"美妆","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"12e57bfd-6830-4bbc-bfee-3305aa6540ef","PT_Name":"黑龙江大米","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"26d238e6-8b85-4484-95bd-a27d8208ca4e","PT_SynType":"1"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"2061649e-402b-4834-b773-759ef8cd4dcd","PT_Name":"12","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"e5c6c224-829c-4bdf-a829-3a964962c76f","PT_SynType":"1"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"28d8e8b4-cd64-43b9-b3aa-aa23d83603c3","PT_Name":"保湿","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"37b32140-ef07-4184-a233-aa599dca6461","PT_Name":"美白","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"4aa4fe3f-cfd0-4bce-9c3f-246700fd17a0","PT_Name":"男装","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"9aabd76d-9fea-4e29-8381-7802ca756744","PT_Name":"女装","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"ce51680f-c246-489b-9d82-d93edd5514ad","PT_Name":"2","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"ead17b2d-bf10-4f0a-93fa-cec636c00f3f","PT_SynType":"1"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"e67d3c51-cc0a-4d28-8cad-70718fcf4c1b","PT_Name":"花店","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"e9630b91-5f66-442d-9ae8-11e99ef49b7b","PT_Name":"111","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"52851e49-93c8-449d-8fe3-74ccbb2d9b85","PT_SynType":"1"},{"VG_GID":"25f2ab49-7cc6-4e63-9812-74188f3d4c15","VG_Name":"初始等级","PT_GID":"f1bfb818-df74-4723-911d-094466c5708c","PT_Name":"护肤","PT_Type":"商品","PD_Discount":100,"VS_CMoney":2,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"}],"InitServiceList":[]},{"VG_IsDownLock":0,"VG_UpDownType":0,"VG_NextGradeName":"","VG_NextGradeGID":"","VG_LastGradeName":"","VG_LastGradeGID":"","US_ValueMax":null,"VG_IsTimeTimes":999,"VG_OilIntegral":0,"GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","US_Code":null,"US_Value":null,"VG_Name":"三等级","VG_Code":"","VG_CardAmount":8,"VG_InitialAmount":80,"VG_InitialIntegral":80,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":1,"VG_IsTimeNum":10,"VG_IsTimeUnit":"天","VG_Remark":"","VG_IntegralUniformRuleValue":0,"VG_IntegralRuleType":1,"VG_DiscountUniformRuleValue":0,"VG_DiscountRuleType":1,"DS_Value":0,"RS_CMoney":0.25,"RS_Value":1,"VS_CMoney":0.25,"VS_Value":1,"US_Name":null,"Settings":[{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"d6eae90a-578c-4fd5-8015-150cf20dbc93","PT_Name":"899","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"a53f70f0-0032-482f-90d2-0173a7079bee","PT_Name":"水果类","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"80459eff-53fa-4819-b34f-d5b921ca9c44","PT_Name":"777","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"3ef581f9-098d-4112-9664-20f1d7638839","PT_Name":"21324","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"bc2f597a-c3cf-4f88-8834-b465e9da885e","PT_Name":"蔬菜","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"00e0e50f-52d3-48bc-a1a2-b87303691010","PT_Name":"补水","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"0"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"0fe55592-e37f-40ee-aa3c-a20d4dcab634","PT_Name":"T恤","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"1034d47d-b1ce-4968-92eb-eefa02a6861e","PT_Name":"美妆","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"12e57bfd-6830-4bbc-bfee-3305aa6540ef","PT_Name":"黑龙江大米","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"26d238e6-8b85-4484-95bd-a27d8208ca4e","PT_SynType":"1"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"2061649e-402b-4834-b773-759ef8cd4dcd","PT_Name":"12","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"e5c6c224-829c-4bdf-a829-3a964962c76f","PT_SynType":"1"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"28d8e8b4-cd64-43b9-b3aa-aa23d83603c3","PT_Name":"保湿","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"37b32140-ef07-4184-a233-aa599dca6461","PT_Name":"美白","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"4aa4fe3f-cfd0-4bce-9c3f-246700fd17a0","PT_Name":"男装","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"9aabd76d-9fea-4e29-8381-7802ca756744","PT_Name":"女装","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"ce51680f-c246-489b-9d82-d93edd5514ad","PT_Name":"2","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"ead17b2d-bf10-4f0a-93fa-cec636c00f3f","PT_SynType":"1"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"e67d3c51-cc0a-4d28-8cad-70718fcf4c1b","PT_Name":"花店","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"e9630b91-5f66-442d-9ae8-11e99ef49b7b","PT_Name":"111","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"52851e49-93c8-449d-8fe3-74ccbb2d9b85","PT_SynType":"1"},{"VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","VG_Name":"三等级","PT_GID":"f1bfb818-df74-4723-911d-094466c5708c","PT_Name":"护肤","PT_Type":"商品","PD_Discount":90,"VS_CMoney":0.25,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"}],"InitServiceList":[{"SR_Timer":null,"SR_TimeUnit":null,"GID":"3ca21c16-3461-4c46-a4bc-b6697974182a","VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","SC_GD":"ff9be1a9-ff7c-49fd-ad6f-172e148148c8","SR_Number":3,"SR_UpdateTime":"2019-09-25 18:55:07","SG_Name":"美容"},{"SR_Timer":null,"SR_TimeUnit":null,"GID":"91faa931-284c-47a3-bb15-ef46a79e5e98","VG_GID":"a879cd97-19ef-44f3-911f-d37cd86fe87a","SC_GD":"487b47e0-e147-460c-b598-bc9bf5c31cf2","SR_Number":3,"SR_UpdateTime":"2019-09-25 18:55:07","SG_Name":"按摩"}]},{"VG_IsDownLock":0,"VG_UpDownType":null,"VG_NextGradeName":null,"VG_NextGradeGID":null,"VG_LastGradeName":null,"VG_LastGradeGID":null,"US_ValueMax":null,"VG_IsTimeTimes":null,"VG_OilIntegral":0,"GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","US_Code":null,"US_Value":null,"VG_Name":"储值","VG_Code":"","VG_CardAmount":0,"VG_InitialAmount":0,"VG_InitialIntegral":0,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":0,"VG_IsDiscount":0,"VG_IsCount":0,"VG_IsTime":0,"VG_IsTimeNum":null,"VG_IsTimeUnit":null,"VG_Remark":"","VG_IntegralUniformRuleValue":0,"VG_IntegralRuleType":1,"VG_DiscountUniformRuleValue":100,"VG_DiscountRuleType":1,"DS_Value":100,"RS_CMoney":0,"RS_Value":0,"VS_CMoney":0,"VS_Value":0,"US_Name":null,"Settings":[{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"d6eae90a-578c-4fd5-8015-150cf20dbc93","PT_Name":"899","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"a53f70f0-0032-482f-90d2-0173a7079bee","PT_Name":"水果类","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"80459eff-53fa-4819-b34f-d5b921ca9c44","PT_Name":"777","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"3ef581f9-098d-4112-9664-20f1d7638839","PT_Name":"21324","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"bc2f597a-c3cf-4f88-8834-b465e9da885e","PT_Name":"蔬菜","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"00e0e50f-52d3-48bc-a1a2-b87303691010","PT_Name":"补水","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"0"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"0fe55592-e37f-40ee-aa3c-a20d4dcab634","PT_Name":"T恤","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"1034d47d-b1ce-4968-92eb-eefa02a6861e","PT_Name":"美妆","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"12e57bfd-6830-4bbc-bfee-3305aa6540ef","PT_Name":"黑龙江大米","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"26d238e6-8b85-4484-95bd-a27d8208ca4e","PT_SynType":"1"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"2061649e-402b-4834-b773-759ef8cd4dcd","PT_Name":"12","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"e5c6c224-829c-4bdf-a829-3a964962c76f","PT_SynType":"1"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"28d8e8b4-cd64-43b9-b3aa-aa23d83603c3","PT_Name":"保湿","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"37b32140-ef07-4184-a233-aa599dca6461","PT_Name":"美白","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"4aa4fe3f-cfd0-4bce-9c3f-246700fd17a0","PT_Name":"男装","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"9aabd76d-9fea-4e29-8381-7802ca756744","PT_Name":"女装","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"ce51680f-c246-489b-9d82-d93edd5514ad","PT_Name":"2","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"ead17b2d-bf10-4f0a-93fa-cec636c00f3f","PT_SynType":"1"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"e67d3c51-cc0a-4d28-8cad-70718fcf4c1b","PT_Name":"花店","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"e9630b91-5f66-442d-9ae8-11e99ef49b7b","PT_Name":"111","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"52851e49-93c8-449d-8fe3-74ccbb2d9b85","PT_SynType":"1"},{"VG_GID":"49fbeed0-cc1e-436d-8e49-1338d128b4d2","VG_Name":"储值","PT_GID":"f1bfb818-df74-4723-911d-094466c5708c","PT_Name":"护肤","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"}],"InitServiceList":[]},{"VG_IsDownLock":0,"VG_UpDownType":null,"VG_NextGradeName":null,"VG_NextGradeGID":null,"VG_LastGradeName":null,"VG_LastGradeGID":null,"US_ValueMax":null,"VG_IsTimeTimes":null,"VG_OilIntegral":0,"GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","US_Code":null,"US_Value":null,"VG_Name":"加油0","VG_Code":"","VG_CardAmount":0,"VG_InitialAmount":0,"VG_InitialIntegral":0,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":0,"VG_IsTimeNum":null,"VG_IsTimeUnit":null,"VG_Remark":"","VG_IntegralUniformRuleValue":0,"VG_IntegralRuleType":0,"VG_DiscountUniformRuleValue":100,"VG_DiscountRuleType":0,"DS_Value":100,"RS_CMoney":0,"RS_Value":0,"VS_CMoney":0,"VS_Value":0,"US_Name":null,"Settings":[{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"d6eae90a-578c-4fd5-8015-150cf20dbc93","PT_Name":"899","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"a53f70f0-0032-482f-90d2-0173a7079bee","PT_Name":"水果类","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"80459eff-53fa-4819-b34f-d5b921ca9c44","PT_Name":"777","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"3ef581f9-098d-4112-9664-20f1d7638839","PT_Name":"21324","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"bc2f597a-c3cf-4f88-8834-b465e9da885e","PT_Name":"蔬菜","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"00e0e50f-52d3-48bc-a1a2-b87303691010","PT_Name":"补水","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"0"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"0fe55592-e37f-40ee-aa3c-a20d4dcab634","PT_Name":"T恤","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"1034d47d-b1ce-4968-92eb-eefa02a6861e","PT_Name":"美妆","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"12e57bfd-6830-4bbc-bfee-3305aa6540ef","PT_Name":"黑龙江大米","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"26d238e6-8b85-4484-95bd-a27d8208ca4e","PT_SynType":"1"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"2061649e-402b-4834-b773-759ef8cd4dcd","PT_Name":"12","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"e5c6c224-829c-4bdf-a829-3a964962c76f","PT_SynType":"1"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"28d8e8b4-cd64-43b9-b3aa-aa23d83603c3","PT_Name":"保湿","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"37b32140-ef07-4184-a233-aa599dca6461","PT_Name":"美白","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"4aa4fe3f-cfd0-4bce-9c3f-246700fd17a0","PT_Name":"男装","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"9aabd76d-9fea-4e29-8381-7802ca756744","PT_Name":"女装","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"ce51680f-c246-489b-9d82-d93edd5514ad","PT_Name":"2","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"ead17b2d-bf10-4f0a-93fa-cec636c00f3f","PT_SynType":"1"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"e67d3c51-cc0a-4d28-8cad-70718fcf4c1b","PT_Name":"花店","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"e9630b91-5f66-442d-9ae8-11e99ef49b7b","PT_Name":"111","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"52851e49-93c8-449d-8fe3-74ccbb2d9b85","PT_SynType":"1"},{"VG_GID":"eab5fc11-126a-4549-863c-cf8105dc34d5","VG_Name":"加油0","PT_GID":"f1bfb818-df74-4723-911d-094466c5708c","PT_Name":"护肤","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"}],"InitServiceList":[]},{"VG_IsDownLock":0,"VG_UpDownType":0,"VG_NextGradeName":"","VG_NextGradeGID":"","VG_LastGradeName":"","VG_LastGradeGID":"","US_ValueMax":null,"VG_IsTimeTimes":null,"VG_OilIntegral":0,"GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","US_Code":null,"US_Value":null,"VG_Name":"一等级","VG_Code":"","VG_CardAmount":0.01,"VG_InitialAmount":20,"VG_InitialIntegral":20,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":0,"VG_IsTimeNum":null,"VG_IsTimeUnit":null,"VG_Remark":"","VG_IntegralUniformRuleValue":1,"VG_IntegralRuleType":0,"VG_DiscountUniformRuleValue":98,"VG_DiscountRuleType":0,"DS_Value":98,"RS_CMoney":1,"RS_Value":1,"VS_CMoney":1,"VS_Value":1,"US_Name":null,"Settings":[{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"d6eae90a-578c-4fd5-8015-150cf20dbc93","PT_Name":"899","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"a53f70f0-0032-482f-90d2-0173a7079bee","PT_Name":"水果类","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"80459eff-53fa-4819-b34f-d5b921ca9c44","PT_Name":"777","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"3ef581f9-098d-4112-9664-20f1d7638839","PT_Name":"21324","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"bc2f597a-c3cf-4f88-8834-b465e9da885e","PT_Name":"蔬菜","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"00e0e50f-52d3-48bc-a1a2-b87303691010","PT_Name":"补水","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"0"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"0fe55592-e37f-40ee-aa3c-a20d4dcab634","PT_Name":"T恤","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"1034d47d-b1ce-4968-92eb-eefa02a6861e","PT_Name":"美妆","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"12e57bfd-6830-4bbc-bfee-3305aa6540ef","PT_Name":"黑龙江大米","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"26d238e6-8b85-4484-95bd-a27d8208ca4e","PT_SynType":"1"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"2061649e-402b-4834-b773-759ef8cd4dcd","PT_Name":"12","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"e5c6c224-829c-4bdf-a829-3a964962c76f","PT_SynType":"1"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"28d8e8b4-cd64-43b9-b3aa-aa23d83603c3","PT_Name":"保湿","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"37b32140-ef07-4184-a233-aa599dca6461","PT_Name":"美白","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"4aa4fe3f-cfd0-4bce-9c3f-246700fd17a0","PT_Name":"男装","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"9aabd76d-9fea-4e29-8381-7802ca756744","PT_Name":"女装","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"ce51680f-c246-489b-9d82-d93edd5514ad","PT_Name":"2","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"ead17b2d-bf10-4f0a-93fa-cec636c00f3f","PT_SynType":"1"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"e67d3c51-cc0a-4d28-8cad-70718fcf4c1b","PT_Name":"花店","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"e9630b91-5f66-442d-9ae8-11e99ef49b7b","PT_Name":"111","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"52851e49-93c8-449d-8fe3-74ccbb2d9b85","PT_SynType":"1"},{"VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","VG_Name":"一等级","PT_GID":"f1bfb818-df74-4723-911d-094466c5708c","PT_Name":"护肤","PT_Type":"商品","PD_Discount":98,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"}],"InitServiceList":[{"SR_Timer":null,"SR_TimeUnit":null,"GID":"c110016c-52b2-4d32-a3a4-ae6cd0b20dd2","VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","SC_GD":"487b47e0-e147-460c-b598-bc9bf5c31cf2","SR_Number":1,"SR_UpdateTime":"2019-07-30 15:26:37","SG_Name":"按摩"},{"SR_Timer":null,"SR_TimeUnit":null,"GID":"fc1dfb13-ddf7-4205-8fff-584df5bad70e","VG_GID":"00d8e2ad-9579-4ac5-bfdd-d678fbe3f94a","SC_GD":"ff9be1a9-ff7c-49fd-ad6f-172e148148c8","SR_Number":1,"SR_UpdateTime":"2019-07-30 15:26:37","SG_Name":"美容"}]},{"VG_IsDownLock":0,"VG_UpDownType":null,"VG_NextGradeName":null,"VG_NextGradeGID":null,"VG_LastGradeName":null,"VG_LastGradeGID":null,"US_ValueMax":null,"VG_IsTimeTimes":null,"VG_OilIntegral":2.56,"GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","US_Code":null,"US_Value":null,"VG_Name":"加油一","VG_Code":"","VG_CardAmount":0,"VG_InitialAmount":1,"VG_InitialIntegral":1,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":0,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":0,"VG_IsTimeNum":null,"VG_IsTimeUnit":null,"VG_Remark":"","VG_IntegralUniformRuleValue":1,"VG_IntegralRuleType":1,"VG_DiscountUniformRuleValue":99,"VG_DiscountRuleType":0,"DS_Value":99,"RS_CMoney":1,"RS_Value":1,"VS_CMoney":1,"VS_Value":1,"US_Name":null,"Settings":[{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"d6eae90a-578c-4fd5-8015-150cf20dbc93","PT_Name":"899","PT_Type":"商品","PD_Discount":99,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"a53f70f0-0032-482f-90d2-0173a7079bee","PT_Name":"水果类","PT_Type":"商品","PD_Discount":99,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"80459eff-53fa-4819-b34f-d5b921ca9c44","PT_Name":"777","PT_Type":"商品","PD_Discount":99,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"3ef581f9-098d-4112-9664-20f1d7638839","PT_Name":"21324","PT_Type":"商品","PD_Discount":99,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"bc2f597a-c3cf-4f88-8834-b465e9da885e","PT_Name":"蔬菜","PT_Type":"商品","PD_Discount":99,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"00e0e50f-52d3-48bc-a1a2-b87303691010","PT_Name":"补水","PT_Type":"商品","PD_Discount":99,"VS_CMoney":1,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"0"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"0fe55592-e37f-40ee-aa3c-a20d4dcab634","PT_Name":"T恤","PT_Type":"商品","PD_Discount":99,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"1034d47d-b1ce-4968-92eb-eefa02a6861e","PT_Name":"美妆","PT_Type":"商品","PD_Discount":99,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"12e57bfd-6830-4bbc-bfee-3305aa6540ef","PT_Name":"黑龙江大米","PT_Type":"商品","PD_Discount":99,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"26d238e6-8b85-4484-95bd-a27d8208ca4e","PT_SynType":"1"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"2061649e-402b-4834-b773-759ef8cd4dcd","PT_Name":"12","PT_Type":"商品","PD_Discount":99,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"e5c6c224-829c-4bdf-a829-3a964962c76f","PT_SynType":"1"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"28d8e8b4-cd64-43b9-b3aa-aa23d83603c3","PT_Name":"保湿","PT_Type":"商品","PD_Discount":99,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"37b32140-ef07-4184-a233-aa599dca6461","PT_Name":"美白","PT_Type":"商品","PD_Discount":99,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"4aa4fe3f-cfd0-4bce-9c3f-246700fd17a0","PT_Name":"男装","PT_Type":"商品","PD_Discount":99,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"9aabd76d-9fea-4e29-8381-7802ca756744","PT_Name":"女装","PT_Type":"商品","PD_Discount":99,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"ce51680f-c246-489b-9d82-d93edd5514ad","PT_Name":"2","PT_Type":"商品","PD_Discount":99,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"ead17b2d-bf10-4f0a-93fa-cec636c00f3f","PT_SynType":"1"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"e67d3c51-cc0a-4d28-8cad-70718fcf4c1b","PT_Name":"花店","PT_Type":"商品","PD_Discount":99,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"e9630b91-5f66-442d-9ae8-11e99ef49b7b","PT_Name":"111","PT_Type":"商品","PD_Discount":99,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"52851e49-93c8-449d-8fe3-74ccbb2d9b85","PT_SynType":"1"},{"VG_GID":"bf36fc3b-2e73-4a38-b794-56ef053427cb","VG_Name":"加油一","PT_GID":"f1bfb818-df74-4723-911d-094466c5708c","PT_Name":"护肤","PT_Type":"商品","PD_Discount":99,"VS_CMoney":1,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"}],"InitServiceList":[]},{"VG_IsDownLock":0,"VG_UpDownType":0,"VG_NextGradeName":"","VG_NextGradeGID":"","VG_LastGradeName":"","VG_LastGradeGID":"","US_ValueMax":null,"VG_IsTimeTimes":null,"VG_OilIntegral":0,"GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","US_Code":null,"US_Value":null,"VG_Name":"二等级","VG_Code":"","VG_CardAmount":4,"VG_InitialAmount":40,"VG_InitialIntegral":40,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":0,"VG_IsTimeNum":null,"VG_IsTimeUnit":null,"VG_Remark":"","VG_IntegralUniformRuleValue":0.5,"VG_IntegralRuleType":0,"VG_DiscountUniformRuleValue":95,"VG_DiscountRuleType":0,"DS_Value":95,"RS_CMoney":0.5,"RS_Value":1,"VS_CMoney":0.5,"VS_Value":1,"US_Name":null,"Settings":[{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"d6eae90a-578c-4fd5-8015-150cf20dbc93","PT_Name":"899","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"a53f70f0-0032-482f-90d2-0173a7079bee","PT_Name":"水果类","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"80459eff-53fa-4819-b34f-d5b921ca9c44","PT_Name":"777","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"3ef581f9-098d-4112-9664-20f1d7638839","PT_Name":"21324","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"bc2f597a-c3cf-4f88-8834-b465e9da885e","PT_Name":"蔬菜","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"00e0e50f-52d3-48bc-a1a2-b87303691010","PT_Name":"补水","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"0"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"0fe55592-e37f-40ee-aa3c-a20d4dcab634","PT_Name":"T恤","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"1034d47d-b1ce-4968-92eb-eefa02a6861e","PT_Name":"美妆","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"12e57bfd-6830-4bbc-bfee-3305aa6540ef","PT_Name":"黑龙江大米","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"26d238e6-8b85-4484-95bd-a27d8208ca4e","PT_SynType":"1"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"2061649e-402b-4834-b773-759ef8cd4dcd","PT_Name":"12","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"e5c6c224-829c-4bdf-a829-3a964962c76f","PT_SynType":"1"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"28d8e8b4-cd64-43b9-b3aa-aa23d83603c3","PT_Name":"保湿","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"37b32140-ef07-4184-a233-aa599dca6461","PT_Name":"美白","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"4aa4fe3f-cfd0-4bce-9c3f-246700fd17a0","PT_Name":"男装","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"9aabd76d-9fea-4e29-8381-7802ca756744","PT_Name":"女装","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"ce51680f-c246-489b-9d82-d93edd5514ad","PT_Name":"2","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"ead17b2d-bf10-4f0a-93fa-cec636c00f3f","PT_SynType":"1"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"e67d3c51-cc0a-4d28-8cad-70718fcf4c1b","PT_Name":"花店","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"e9630b91-5f66-442d-9ae8-11e99ef49b7b","PT_Name":"111","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"52851e49-93c8-449d-8fe3-74ccbb2d9b85","PT_SynType":"1"},{"VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","VG_Name":"二等级","PT_GID":"f1bfb818-df74-4723-911d-094466c5708c","PT_Name":"护肤","PT_Type":"商品","PD_Discount":95,"VS_CMoney":0.5,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"}],"InitServiceList":[{"SR_Timer":null,"SR_TimeUnit":null,"GID":"7fb34225-172f-4a68-9aaf-eb6e8e72d3cf","VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","SC_GD":"487b47e0-e147-460c-b598-bc9bf5c31cf2","SR_Number":2,"SR_UpdateTime":"2019-07-03 10:06:49","SG_Name":"按摩"},{"SR_Timer":null,"SR_TimeUnit":null,"GID":"9a877afe-d1af-464b-a3e0-0f6ab64eca35","VG_GID":"2f9b4100-f847-4711-82b9-8bcea26bcf1d","SC_GD":"ff9be1a9-ff7c-49fd-ad6f-172e148148c8","SR_Number":2,"SR_UpdateTime":"2019-07-03 10:06:49","SG_Name":"美容"}]},{"VG_IsDownLock":0,"VG_UpDownType":null,"VG_NextGradeName":null,"VG_NextGradeGID":null,"VG_LastGradeName":null,"VG_LastGradeGID":null,"US_ValueMax":null,"VG_IsTimeTimes":null,"VG_OilIntegral":0,"GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","US_Code":null,"US_Value":null,"VG_Name":"先测试","VG_Code":"","VG_CardAmount":0,"VG_InitialAmount":0,"VG_InitialIntegral":0,"VG_IsLock":0,"VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":0,"VG_IsTimeNum":null,"VG_IsTimeUnit":null,"VG_Remark":"","VG_IntegralUniformRuleValue":0,"VG_IntegralRuleType":0,"VG_DiscountUniformRuleValue":100,"VG_DiscountRuleType":0,"DS_Value":100,"RS_CMoney":0,"RS_Value":0,"VS_CMoney":0,"VS_Value":0,"US_Name":null,"Settings":[{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"d6eae90a-578c-4fd5-8015-150cf20dbc93","PT_Name":"899","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"a53f70f0-0032-482f-90d2-0173a7079bee","PT_Name":"水果类","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"80459eff-53fa-4819-b34f-d5b921ca9c44","PT_Name":"777","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"3ef581f9-098d-4112-9664-20f1d7638839","PT_Name":"21324","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"bc2f597a-c3cf-4f88-8834-b465e9da885e","PT_Name":"蔬菜","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"00e0e50f-52d3-48bc-a1a2-b87303691010","PT_Name":"补水","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"0"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"0fe55592-e37f-40ee-aa3c-a20d4dcab634","PT_Name":"T恤","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"1034d47d-b1ce-4968-92eb-eefa02a6861e","PT_Name":"美妆","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"12e57bfd-6830-4bbc-bfee-3305aa6540ef","PT_Name":"黑龙江大米","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"26d238e6-8b85-4484-95bd-a27d8208ca4e","PT_SynType":"1"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"2061649e-402b-4834-b773-759ef8cd4dcd","PT_Name":"12","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"e5c6c224-829c-4bdf-a829-3a964962c76f","PT_SynType":"1"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"28d8e8b4-cd64-43b9-b3aa-aa23d83603c3","PT_Name":"保湿","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"37b32140-ef07-4184-a233-aa599dca6461","PT_Name":"美白","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"4aa4fe3f-cfd0-4bce-9c3f-246700fd17a0","PT_Name":"男装","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"9aabd76d-9fea-4e29-8381-7802ca756744","PT_Name":"女装","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"ce51680f-c246-489b-9d82-d93edd5514ad","PT_Name":"2","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"ead17b2d-bf10-4f0a-93fa-cec636c00f3f","PT_SynType":"1"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"e67d3c51-cc0a-4d28-8cad-70718fcf4c1b","PT_Name":"花店","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"e9630b91-5f66-442d-9ae8-11e99ef49b7b","PT_Name":"111","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"52851e49-93c8-449d-8fe3-74ccbb2d9b85","PT_SynType":"1"},{"VG_GID":"d74b45c4-cd6a-43d8-b800-84dbbfc8b92a","VG_Name":"先测试","PT_GID":"f1bfb818-df74-4723-911d-094466c5708c","PT_Name":"护肤","PT_Type":"商品","PD_Discount":100,"VS_CMoney":0,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"}],"InitServiceList":[]}]
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

    public static class DataBean {
        /**
         * VG_IsDownLock : 1
         * VG_UpDownType : 0
         * VG_NextGradeName :
         * VG_NextGradeGID :
         * VG_LastGradeName : 三等级
         * VG_LastGradeGID : a879cd97-19ef-44f3-911f-d37cd86fe87a
         * US_ValueMax : null
         * VG_IsTimeTimes : null
         * VG_OilIntegral : 0.0
         * GID : 98c73594-ac37-48c5-8773-375b5a290c0e
         * US_Code : null
         * US_Value : 1000.0
         * VG_Name : 特等级
         * VG_Code :
         * VG_CardAmount : 10.0
         * VG_InitialAmount : 100.0
         * VG_InitialIntegral : 100.0
         * VG_IsLock : 0
         * VG_IsAccount : 1
         * VG_IsIntegral : 1
         * VG_IsDiscount : 1
         * VG_IsCount : 1
         * VG_IsTime : 0
         * VG_IsTimeNum : null
         * VG_IsTimeUnit : null
         * VG_Remark :
         * VG_IntegralUniformRuleValue : 100.0
         * VG_IntegralRuleType : 0
         * VG_DiscountUniformRuleValue : 85
         * VG_DiscountRuleType : 0
         * DS_Value : 85.0
         * RS_CMoney : 0.1
         * RS_Value : 1.0
         * VS_CMoney : 0.1
         * VS_Value : 1.0
         * US_Name : null
         * Settings : [{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"d6eae90a-578c-4fd5-8015-150cf20dbc93","PT_Name":"899","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"a53f70f0-0032-482f-90d2-0173a7079bee","PT_Name":"水果类","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"80459eff-53fa-4819-b34f-d5b921ca9c44","PT_Name":"777","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"3ef581f9-098d-4112-9664-20f1d7638839","PT_Name":"21324","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"bc2f597a-c3cf-4f88-8834-b465e9da885e","PT_Name":"蔬菜","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"00e0e50f-52d3-48bc-a1a2-b87303691010","PT_Name":"补水","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":"c65e5942-18c3-4cb4-be00-909aadc8d1ad","SM_Name":"BIG店铺","PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"0"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"0fe55592-e37f-40ee-aa3c-a20d4dcab634","PT_Name":"T恤","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"1034d47d-b1ce-4968-92eb-eefa02a6861e","PT_Name":"美妆","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"12e57bfd-6830-4bbc-bfee-3305aa6540ef","PT_Name":"黑龙江大米","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"26d238e6-8b85-4484-95bd-a27d8208ca4e","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"2061649e-402b-4834-b773-759ef8cd4dcd","PT_Name":"12","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"e5c6c224-829c-4bdf-a829-3a964962c76f","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"28d8e8b4-cd64-43b9-b3aa-aa23d83603c3","PT_Name":"保湿","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"37b32140-ef07-4184-a233-aa599dca6461","PT_Name":"美白","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"4aa4fe3f-cfd0-4bce-9c3f-246700fd17a0","PT_Name":"男装","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"9aabd76d-9fea-4e29-8381-7802ca756744","PT_Name":"女装","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"aa38e583-ddb7-4b8d-9782-9beef0f90b5a","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"ce51680f-c246-489b-9d82-d93edd5514ad","PT_Name":"2","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"ead17b2d-bf10-4f0a-93fa-cec636c00f3f","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"e67d3c51-cc0a-4d28-8cad-70718fcf4c1b","PT_Name":"花店","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"e9630b91-5f66-442d-9ae8-11e99ef49b7b","PT_Name":"111","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"52851e49-93c8-449d-8fe3-74ccbb2d9b85","PT_SynType":"1"},{"VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","VG_Name":"特等级","PT_GID":"f1bfb818-df74-4723-911d-094466c5708c","PT_Name":"护肤","PT_Type":"商品","PD_Discount":85,"VS_CMoney":100,"VS_Number":1,"SM_GID":null,"SM_Name":null,"PT_Parent":"18ab8332-8be1-46fd-890e-b959d4029747","PT_SynType":"1"}]
         * InitServiceList : [{"SR_Timer":null,"SR_TimeUnit":null,"GID":"1c253af6-c5c5-4ef8-8bc6-c548cec32a66","VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","SC_GD":"ff9be1a9-ff7c-49fd-ad6f-172e148148c8","SR_Number":4,"SR_UpdateTime":"2019-09-25 09:37:41","SG_Name":"美容"},{"SR_Timer":null,"SR_TimeUnit":null,"GID":"72ff2053-d1bf-481c-8f09-9143ce5e6c8c","VG_GID":"98c73594-ac37-48c5-8773-375b5a290c0e","SC_GD":"487b47e0-e147-460c-b598-bc9bf5c31cf2","SR_Number":4,"SR_UpdateTime":"2019-09-25 09:37:41","SG_Name":"按摩"}]
         */

        private int VG_IsDownLock;
        private int VG_UpDownType;
        private String VG_NextGradeName;
        private String VG_NextGradeGID;
        private String VG_LastGradeName;
        private String VG_LastGradeGID;
        private Object US_ValueMax;
        private Object VG_IsTimeTimes;
        private double VG_OilIntegral;
        private String GID;
        private Object US_Code;
        private double US_Value;
        private String VG_Name;
        private String VG_Code;
        private double VG_CardAmount;
        private double VG_InitialAmount;
        private double VG_InitialIntegral;
        private int VG_IsLock;
        private int VG_IsAccount;
        private int VG_IsIntegral;
        private int VG_IsDiscount;
        private int VG_IsCount;
        private int VG_IsTime;
        private Object VG_IsTimeNum;
        private Object VG_IsTimeUnit;
        private String VG_Remark;
        private double VG_IntegralUniformRuleValue;
        private int VG_IntegralRuleType;
        private int VG_DiscountUniformRuleValue;
        private int VG_DiscountRuleType;
        private double DS_Value;
        private double RS_CMoney;
        private double RS_Value;
        private double VS_CMoney;
        private double VS_Value;
        private Object US_Name;
        private List<SettingsBean> Settings;
        private List<InitServiceListBean> InitServiceList;

        public int getVG_IsDownLock() {
            return VG_IsDownLock;
        }

        public void setVG_IsDownLock(int VG_IsDownLock) {
            this.VG_IsDownLock = VG_IsDownLock;
        }

        public int getVG_UpDownType() {
            return VG_UpDownType;
        }

        public void setVG_UpDownType(int VG_UpDownType) {
            this.VG_UpDownType = VG_UpDownType;
        }

        public String getVG_NextGradeName() {
            return VG_NextGradeName;
        }

        public void setVG_NextGradeName(String VG_NextGradeName) {
            this.VG_NextGradeName = VG_NextGradeName;
        }

        public String getVG_NextGradeGID() {
            return VG_NextGradeGID;
        }

        public void setVG_NextGradeGID(String VG_NextGradeGID) {
            this.VG_NextGradeGID = VG_NextGradeGID;
        }

        public String getVG_LastGradeName() {
            return VG_LastGradeName;
        }

        public void setVG_LastGradeName(String VG_LastGradeName) {
            this.VG_LastGradeName = VG_LastGradeName;
        }

        public String getVG_LastGradeGID() {
            return VG_LastGradeGID;
        }

        public void setVG_LastGradeGID(String VG_LastGradeGID) {
            this.VG_LastGradeGID = VG_LastGradeGID;
        }

        public Object getUS_ValueMax() {
            return US_ValueMax;
        }

        public void setUS_ValueMax(Object US_ValueMax) {
            this.US_ValueMax = US_ValueMax;
        }

        public Object getVG_IsTimeTimes() {
            return VG_IsTimeTimes;
        }

        public void setVG_IsTimeTimes(Object VG_IsTimeTimes) {
            this.VG_IsTimeTimes = VG_IsTimeTimes;
        }

        public double getVG_OilIntegral() {
            return VG_OilIntegral;
        }

        public void setVG_OilIntegral(double VG_OilIntegral) {
            this.VG_OilIntegral = VG_OilIntegral;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public Object getUS_Code() {
            return US_Code;
        }

        public void setUS_Code(Object US_Code) {
            this.US_Code = US_Code;
        }

        public double getUS_Value() {
            return US_Value;
        }

        public void setUS_Value(double US_Value) {
            this.US_Value = US_Value;
        }

        public String getVG_Name() {
            return VG_Name;
        }

        public void setVG_Name(String VG_Name) {
            this.VG_Name = VG_Name;
        }

        public String getVG_Code() {
            return VG_Code;
        }

        public void setVG_Code(String VG_Code) {
            this.VG_Code = VG_Code;
        }

        public double getVG_CardAmount() {
            return VG_CardAmount;
        }

        public void setVG_CardAmount(double VG_CardAmount) {
            this.VG_CardAmount = VG_CardAmount;
        }

        public double getVG_InitialAmount() {
            return VG_InitialAmount;
        }

        public void setVG_InitialAmount(double VG_InitialAmount) {
            this.VG_InitialAmount = VG_InitialAmount;
        }

        public double getVG_InitialIntegral() {
            return VG_InitialIntegral;
        }

        public void setVG_InitialIntegral(double VG_InitialIntegral) {
            this.VG_InitialIntegral = VG_InitialIntegral;
        }

        public int getVG_IsLock() {
            return VG_IsLock;
        }

        public void setVG_IsLock(int VG_IsLock) {
            this.VG_IsLock = VG_IsLock;
        }

        public int getVG_IsAccount() {
            return VG_IsAccount;
        }

        public void setVG_IsAccount(int VG_IsAccount) {
            this.VG_IsAccount = VG_IsAccount;
        }

        public int getVG_IsIntegral() {
            return VG_IsIntegral;
        }

        public void setVG_IsIntegral(int VG_IsIntegral) {
            this.VG_IsIntegral = VG_IsIntegral;
        }

        public int getVG_IsDiscount() {
            return VG_IsDiscount;
        }

        public void setVG_IsDiscount(int VG_IsDiscount) {
            this.VG_IsDiscount = VG_IsDiscount;
        }

        public int getVG_IsCount() {
            return VG_IsCount;
        }

        public void setVG_IsCount(int VG_IsCount) {
            this.VG_IsCount = VG_IsCount;
        }

        public int getVG_IsTime() {
            return VG_IsTime;
        }

        public void setVG_IsTime(int VG_IsTime) {
            this.VG_IsTime = VG_IsTime;
        }

        public Object getVG_IsTimeNum() {
            return VG_IsTimeNum;
        }

        public void setVG_IsTimeNum(Object VG_IsTimeNum) {
            this.VG_IsTimeNum = VG_IsTimeNum;
        }

        public Object getVG_IsTimeUnit() {
            return VG_IsTimeUnit;
        }

        public void setVG_IsTimeUnit(Object VG_IsTimeUnit) {
            this.VG_IsTimeUnit = VG_IsTimeUnit;
        }

        public String getVG_Remark() {
            return VG_Remark;
        }

        public void setVG_Remark(String VG_Remark) {
            this.VG_Remark = VG_Remark;
        }

        public double getVG_IntegralUniformRuleValue() {
            return VG_IntegralUniformRuleValue;
        }

        public void setVG_IntegralUniformRuleValue(double VG_IntegralUniformRuleValue) {
            this.VG_IntegralUniformRuleValue = VG_IntegralUniformRuleValue;
        }

        public int getVG_IntegralRuleType() {
            return VG_IntegralRuleType;
        }

        public void setVG_IntegralRuleType(int VG_IntegralRuleType) {
            this.VG_IntegralRuleType = VG_IntegralRuleType;
        }

        public int getVG_DiscountUniformRuleValue() {
            return VG_DiscountUniformRuleValue;
        }

        public void setVG_DiscountUniformRuleValue(int VG_DiscountUniformRuleValue) {
            this.VG_DiscountUniformRuleValue = VG_DiscountUniformRuleValue;
        }

        public int getVG_DiscountRuleType() {
            return VG_DiscountRuleType;
        }

        public void setVG_DiscountRuleType(int VG_DiscountRuleType) {
            this.VG_DiscountRuleType = VG_DiscountRuleType;
        }

        public double getDS_Value() {
            return DS_Value;
        }

        public void setDS_Value(double DS_Value) {
            this.DS_Value = DS_Value;
        }

        public double getRS_CMoney() {
            return RS_CMoney;
        }

        public void setRS_CMoney(double RS_CMoney) {
            this.RS_CMoney = RS_CMoney;
        }

        public double getRS_Value() {
            return RS_Value;
        }

        public void setRS_Value(double RS_Value) {
            this.RS_Value = RS_Value;
        }

        public double getVS_CMoney() {
            return VS_CMoney;
        }

        public void setVS_CMoney(double VS_CMoney) {
            this.VS_CMoney = VS_CMoney;
        }

        public double getVS_Value() {
            return VS_Value;
        }

        public void setVS_Value(double VS_Value) {
            this.VS_Value = VS_Value;
        }

        public Object getUS_Name() {
            return US_Name;
        }

        public void setUS_Name(Object US_Name) {
            this.US_Name = US_Name;
        }

        public List<SettingsBean> getSettings() {
            return Settings;
        }

        public void setSettings(List<SettingsBean> Settings) {
            this.Settings = Settings;
        }

        public List<InitServiceListBean> getInitServiceList() {
            return InitServiceList;
        }

        public void setInitServiceList(List<InitServiceListBean> InitServiceList) {
            this.InitServiceList = InitServiceList;
        }

        public static class SettingsBean {
            /**
             * VG_GID : 98c73594-ac37-48c5-8773-375b5a290c0e
             * VG_Name : 特等级
             * PT_GID : d6eae90a-578c-4fd5-8015-150cf20dbc93
             * PT_Name : 899
             * PT_Type : 商品
             * PD_Discount : 85
             * VS_CMoney : 100.0
             * VS_Number : 1.0
             * SM_GID : c65e5942-18c3-4cb4-be00-909aadc8d1ad
             * SM_Name : BIG店铺
             * PT_Parent :
             * PT_SynType : 0
             */

            private String VG_GID;
            private String VG_Name;
            private String PT_GID;
            private String PT_Name;
            private String PT_Type;
            private int PD_Discount;
            private double VS_CMoney;
            private double VS_Number;
            private String SM_GID;
            private String SM_Name;
            private String PT_Parent;
            private String PT_SynType;

            public String getVG_GID() {
                return VG_GID;
            }

            public void setVG_GID(String VG_GID) {
                this.VG_GID = VG_GID;
            }

            public String getVG_Name() {
                return VG_Name;
            }

            public void setVG_Name(String VG_Name) {
                this.VG_Name = VG_Name;
            }

            public String getPT_GID() {
                return PT_GID;
            }

            public void setPT_GID(String PT_GID) {
                this.PT_GID = PT_GID;
            }

            public String getPT_Name() {
                return PT_Name;
            }

            public void setPT_Name(String PT_Name) {
                this.PT_Name = PT_Name;
            }

            public String getPT_Type() {
                return PT_Type;
            }

            public void setPT_Type(String PT_Type) {
                this.PT_Type = PT_Type;
            }

            public int getPD_Discount() {
                return PD_Discount;
            }

            public void setPD_Discount(int PD_Discount) {
                this.PD_Discount = PD_Discount;
            }

            public double getVS_CMoney() {
                return VS_CMoney;
            }

            public void setVS_CMoney(double VS_CMoney) {
                this.VS_CMoney = VS_CMoney;
            }

            public double getVS_Number() {
                return VS_Number;
            }

            public void setVS_Number(double VS_Number) {
                this.VS_Number = VS_Number;
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

            public String getPT_Parent() {
                return PT_Parent;
            }

            public void setPT_Parent(String PT_Parent) {
                this.PT_Parent = PT_Parent;
            }

            public String getPT_SynType() {
                return PT_SynType;
            }

            public void setPT_SynType(String PT_SynType) {
                this.PT_SynType = PT_SynType;
            }
        }

        public static class InitServiceListBean {
            /**
             * SR_Timer : null
             * SR_TimeUnit : null
             * GID : 1c253af6-c5c5-4ef8-8bc6-c548cec32a66
             * VG_GID : 98c73594-ac37-48c5-8773-375b5a290c0e
             * SC_GD : ff9be1a9-ff7c-49fd-ad6f-172e148148c8
             * SR_Number : 4
             * SR_UpdateTime : 2019-09-25 09:37:41
             * SG_Name : 美容
             */

            private Object SR_Timer;
            private Object SR_TimeUnit;
            private String GID;
            private String VG_GID;
            private String SC_GD;
            private int SR_Number;
            private String SR_UpdateTime;
            private String SG_Name;

            public Object getSR_Timer() {
                return SR_Timer;
            }

            public void setSR_Timer(Object SR_Timer) {
                this.SR_Timer = SR_Timer;
            }

            public Object getSR_TimeUnit() {
                return SR_TimeUnit;
            }

            public void setSR_TimeUnit(Object SR_TimeUnit) {
                this.SR_TimeUnit = SR_TimeUnit;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getVG_GID() {
                return VG_GID;
            }

            public void setVG_GID(String VG_GID) {
                this.VG_GID = VG_GID;
            }

            public String getSC_GD() {
                return SC_GD;
            }

            public void setSC_GD(String SC_GD) {
                this.SC_GD = SC_GD;
            }

            public int getSR_Number() {
                return SR_Number;
            }

            public void setSR_Number(int SR_Number) {
                this.SR_Number = SR_Number;
            }

            public String getSR_UpdateTime() {
                return SR_UpdateTime;
            }

            public void setSR_UpdateTime(String SR_UpdateTime) {
                this.SR_UpdateTime = SR_UpdateTime;
            }

            public String getSG_Name() {
                return SG_Name;
            }

            public void setSG_Name(String SG_Name) {
                this.SG_Name = SG_Name;
            }
        }
    }
}
