package com.zhiluo.android.yunpu.statistics.order.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/11 09:21.
 * 邮箱：137615198@qq.com
 */

public class GoodsOrderReportBean implements Serializable {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":15,"PageSize":10,"DataCount":147,"PageIndex":1,"DataList":[{"ViewGoodsDetail":[{"GID":"77703f7c-f3e4-4f87-a669-f6c9d68cad98","CO_GID":"9c6e4529-63ef-4a7f-ad7d-909c2961933c","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"61160364-44ac-47b9-bf45-233a6adaae44","CO_GID":"9c6e4529-63ef-4a7f-ad7d-909c2961933c","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"1f9df93a-0bdb-4e5a-9736-e82f13b172db","CO_GID":"9c6e4529-63ef-4a7f-ad7d-909c2961933c","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 17:06:34","GID":"9c6e4529-63ef-4a7f-ad7d-909c2961933c","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041643293897","CO_ConsumeWay":"现金(11111131.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111131E7,"CO_TotalPrice":1.1111131E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:43:32","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"38ccdd49-8a07-4ce5-a113-9ee453e114bc","CO_GID":"1ba1ccda-5d19-4056-be9f-5e0050c51863","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"07afaecc-e0ec-4c1a-abe3-500f47bd687e","CO_GID":"1ba1ccda-5d19-4056-be9f-5e0050c51863","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"f254d2b3-81b7-47c3-980e-c40114be468d","CO_GID":"1ba1ccda-5d19-4056-be9f-5e0050c51863","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 17:05:26","GID":"1ba1ccda-5d19-4056-be9f-5e0050c51863","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041642377447","CO_ConsumeWay":"现金(11111131.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111131E7,"CO_TotalPrice":1.1111131E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:42:41","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"f2680dde-dfff-4cd5-8372-fb24133a32ea","CO_GID":"acd5efc1-3ba6-44e1-902e-e5ad58e51a3f","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"5f27db4d-7f52-4e9d-a58b-f876ba842e24","CO_GID":"acd5efc1-3ba6-44e1-902e-e5ad58e51a3f","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"9a0af8f1-c0ac-4c28-bcc9-cb2c88b07b82","CO_GID":"acd5efc1-3ba6-44e1-902e-e5ad58e51a3f","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 17:05:06","GID":"acd5efc1-3ba6-44e1-902e-e5ad58e51a3f","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041642193856","CO_ConsumeWay":"现金(11111131.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111131E7,"CO_TotalPrice":1.1111131E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:42:24","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"ce5e2ca4-68eb-493b-9f6a-e4f94997ba0a","CO_GID":"d30e3528-7355-4ed5-99e4-21ee292348df","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"dc17385c-a7ec-4a02-ba7e-e311fa559333","CO_GID":"d30e3528-7355-4ed5-99e4-21ee292348df","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:58:00","GID":"d30e3528-7355-4ed5-99e4-21ee292348df","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041634496053","CO_ConsumeWay":"现金(11111121.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111121E7,"CO_TotalPrice":1.1111121E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:34:57","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"6f6193c4-97bc-4a63-bc48-15ebd155c8cd","CO_GID":"8ab35150-29eb-4603-b638-ac3da2058753","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"81f7564d-7521-4716-bac7-42b52ed7d745","CO_GID":"8ab35150-29eb-4603-b638-ac3da2058753","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:57:16","GID":"8ab35150-29eb-4603-b638-ac3da2058753","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041634077760","CO_ConsumeWay":"现金(11111121.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111121E7,"CO_TotalPrice":1.1111121E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:34:33","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"980e1254-c51c-4118-a898-8d4837a1cb01","CO_GID":"db31ac2b-c120-4951-b8d8-6871b203b5eb","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"a9b0c2ae-fa42-4c93-a7fb-6da69395151f","CO_GID":"db31ac2b-c120-4951-b8d8-6871b203b5eb","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:56:37","GID":"db31ac2b-c120-4951-b8d8-6871b203b5eb","VIP_GID":"8d824808-1de5-4ca1-9869-d162e913e9a1","VIP_Card":"18338258258","VIP_Name":"来了","VIP_Phone":"18338258258","VIP_FaceNumber":"他特踏踏","CO_OrderCode":"SP1904041633424471","CO_ConsumeWay":"现金(11111121.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111121E7,"CO_TotalPrice":1.1111121E7,"CO_Integral":0,"CO_BalanceCard":141.6,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:33:46","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"fa6d30fb-e473-48dd-bad3-5848784432b2","CO_GID":"ade64c17-3851-4389-8728-8a5a0ea9a3e5","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"840634c5-9e6a-45f9-a1e7-696f985e56f3","CO_GID":"ade64c17-3851-4389-8728-8a5a0ea9a3e5","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"e61e02ff-6de0-4297-b10a-8c5b355c96f0","CO_GID":"ade64c17-3851-4389-8728-8a5a0ea9a3e5","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:36:39","GID":"ade64c17-3851-4389-8728-8a5a0ea9a3e5","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041612171752","CO_ConsumeWay":"现金(11111131.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111131E7,"CO_TotalPrice":1.1111131E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:12:21","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"ccf06b4a-7a8f-400e-81f9-ab75c523d4e6","CO_GID":"fe5fc83a-6ccb-4465-89d8-0bbeb7ba570d","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"97e1978d-b471-4567-b075-a817fba87697","CO_GID":"fe5fc83a-6ccb-4465-89d8-0bbeb7ba570d","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"7fdada92-e1d0-4884-82eb-47e059ed07e4","CO_GID":"fe5fc83a-6ccb-4465-89d8-0bbeb7ba570d","PM_GID":"085064cb-decd-4bf5-b6e6-cd9ec3386228","PM_IsService":0,"PM_Code":"6914068012933","PM_Name":"158444回","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":10,"State":"","GOD_EMName":"营销全部,商品、快速消费,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:07:17","GID":"fe5fc83a-6ccb-4465-89d8-0bbeb7ba570d","VIP_GID":"8d824808-1de5-4ca1-9869-d162e913e9a1","VIP_Card":"18338258258","VIP_Name":"来了","VIP_Phone":"18338258258","VIP_FaceNumber":"他特踏踏","CO_OrderCode":"SP1904041606134516","CO_ConsumeWay":"现金(30.00)","CO_WayCode":"XJZF","CO_Monetary":30,"CO_TotalPrice":30,"CO_Integral":10,"CO_BalanceCard":141.6,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:06:17","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"e5fffa8e-fc0c-4713-ada0-5d36eaaf005c","CO_GID":"4e02a6ab-bceb-4fa2-b9f4-572fa8f562e9","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"095f8cdd-08b4-47c2-ab38-d57a95b46fbc","CO_GID":"4e02a6ab-bceb-4fa2-b9f4-572fa8f562e9","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"8491ccab-e904-482e-a86b-b0d38018d6c5","CO_GID":"4e02a6ab-bceb-4fa2-b9f4-572fa8f562e9","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"全部","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:14:26","GID":"4e02a6ab-bceb-4fa2-b9f4-572fa8f562e9","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041550364263","CO_ConsumeWay":"现金(11111131.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111131E7,"CO_TotalPrice":1.1111131E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 15:50:42","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"e226664f-3d3c-456a-9ed1-0aff8cf396aa","CO_GID":"6e56a135-2505-43da-b568-9a0279968c07","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"c02b01d5-3ba1-4cfc-9725-a03cfe3f9568","CO_GID":"6e56a135-2505-43da-b568-9a0279968c07","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"b6fb5dbc-3767-4c75-9b3d-87807b541cc3","CO_GID":"6e56a135-2505-43da-b568-9a0279968c07","PM_GID":"085064cb-decd-4bf5-b6e6-cd9ec3386228","PM_IsService":0,"PM_Code":"6914068012933","PM_Name":"158444回","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"全部","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:12:20","GID":"6e56a135-2505-43da-b568-9a0279968c07","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041548514838","CO_ConsumeWay":"现金(30.00)","CO_WayCode":"XJZF","CO_Monetary":30,"CO_TotalPrice":30,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 15:48:57","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null}],"StatisticsInfo":{"DataCount":0,"Monetary":1.116136592571E11,"DiscountMoney":1.6213164185E9,"AllNumber":147,"FavorableMoney":0,"GrossMargin":0,"CostMoney":0},"TrendData":null}
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

    public static class DataBean implements Serializable{
        /**
         * PageTotal : 15
         * PageSize : 10
         * DataCount : 147
         * PageIndex : 1
         * DataList : [{"ViewGoodsDetail":[{"GID":"77703f7c-f3e4-4f87-a669-f6c9d68cad98","CO_GID":"9c6e4529-63ef-4a7f-ad7d-909c2961933c","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"61160364-44ac-47b9-bf45-233a6adaae44","CO_GID":"9c6e4529-63ef-4a7f-ad7d-909c2961933c","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"1f9df93a-0bdb-4e5a-9736-e82f13b172db","CO_GID":"9c6e4529-63ef-4a7f-ad7d-909c2961933c","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 17:06:34","GID":"9c6e4529-63ef-4a7f-ad7d-909c2961933c","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041643293897","CO_ConsumeWay":"现金(11111131.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111131E7,"CO_TotalPrice":1.1111131E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:43:32","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"38ccdd49-8a07-4ce5-a113-9ee453e114bc","CO_GID":"1ba1ccda-5d19-4056-be9f-5e0050c51863","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"07afaecc-e0ec-4c1a-abe3-500f47bd687e","CO_GID":"1ba1ccda-5d19-4056-be9f-5e0050c51863","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"f254d2b3-81b7-47c3-980e-c40114be468d","CO_GID":"1ba1ccda-5d19-4056-be9f-5e0050c51863","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 17:05:26","GID":"1ba1ccda-5d19-4056-be9f-5e0050c51863","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041642377447","CO_ConsumeWay":"现金(11111131.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111131E7,"CO_TotalPrice":1.1111131E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:42:41","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"f2680dde-dfff-4cd5-8372-fb24133a32ea","CO_GID":"acd5efc1-3ba6-44e1-902e-e5ad58e51a3f","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"5f27db4d-7f52-4e9d-a58b-f876ba842e24","CO_GID":"acd5efc1-3ba6-44e1-902e-e5ad58e51a3f","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"9a0af8f1-c0ac-4c28-bcc9-cb2c88b07b82","CO_GID":"acd5efc1-3ba6-44e1-902e-e5ad58e51a3f","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 17:05:06","GID":"acd5efc1-3ba6-44e1-902e-e5ad58e51a3f","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041642193856","CO_ConsumeWay":"现金(11111131.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111131E7,"CO_TotalPrice":1.1111131E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:42:24","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"ce5e2ca4-68eb-493b-9f6a-e4f94997ba0a","CO_GID":"d30e3528-7355-4ed5-99e4-21ee292348df","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"dc17385c-a7ec-4a02-ba7e-e311fa559333","CO_GID":"d30e3528-7355-4ed5-99e4-21ee292348df","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:58:00","GID":"d30e3528-7355-4ed5-99e4-21ee292348df","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041634496053","CO_ConsumeWay":"现金(11111121.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111121E7,"CO_TotalPrice":1.1111121E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:34:57","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"6f6193c4-97bc-4a63-bc48-15ebd155c8cd","CO_GID":"8ab35150-29eb-4603-b638-ac3da2058753","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"81f7564d-7521-4716-bac7-42b52ed7d745","CO_GID":"8ab35150-29eb-4603-b638-ac3da2058753","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:57:16","GID":"8ab35150-29eb-4603-b638-ac3da2058753","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041634077760","CO_ConsumeWay":"现金(11111121.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111121E7,"CO_TotalPrice":1.1111121E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:34:33","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"980e1254-c51c-4118-a898-8d4837a1cb01","CO_GID":"db31ac2b-c120-4951-b8d8-6871b203b5eb","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"a9b0c2ae-fa42-4c93-a7fb-6da69395151f","CO_GID":"db31ac2b-c120-4951-b8d8-6871b203b5eb","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:56:37","GID":"db31ac2b-c120-4951-b8d8-6871b203b5eb","VIP_GID":"8d824808-1de5-4ca1-9869-d162e913e9a1","VIP_Card":"18338258258","VIP_Name":"来了","VIP_Phone":"18338258258","VIP_FaceNumber":"他特踏踏","CO_OrderCode":"SP1904041633424471","CO_ConsumeWay":"现金(11111121.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111121E7,"CO_TotalPrice":1.1111121E7,"CO_Integral":0,"CO_BalanceCard":141.6,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:33:46","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"fa6d30fb-e473-48dd-bad3-5848784432b2","CO_GID":"ade64c17-3851-4389-8728-8a5a0ea9a3e5","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"840634c5-9e6a-45f9-a1e7-696f985e56f3","CO_GID":"ade64c17-3851-4389-8728-8a5a0ea9a3e5","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"e61e02ff-6de0-4297-b10a-8c5b355c96f0","CO_GID":"ade64c17-3851-4389-8728-8a5a0ea9a3e5","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:36:39","GID":"ade64c17-3851-4389-8728-8a5a0ea9a3e5","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041612171752","CO_ConsumeWay":"现金(11111131.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111131E7,"CO_TotalPrice":1.1111131E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:12:21","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"ccf06b4a-7a8f-400e-81f9-ab75c523d4e6","CO_GID":"fe5fc83a-6ccb-4465-89d8-0bbeb7ba570d","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"97e1978d-b471-4567-b075-a817fba87697","CO_GID":"fe5fc83a-6ccb-4465-89d8-0bbeb7ba570d","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"7fdada92-e1d0-4884-82eb-47e059ed07e4","CO_GID":"fe5fc83a-6ccb-4465-89d8-0bbeb7ba570d","PM_GID":"085064cb-decd-4bf5-b6e6-cd9ec3386228","PM_IsService":0,"PM_Code":"6914068012933","PM_Name":"158444回","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":10,"State":"","GOD_EMName":"营销全部,商品、快速消费,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部,营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"营销全部,商品、快速消费","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:07:17","GID":"fe5fc83a-6ccb-4465-89d8-0bbeb7ba570d","VIP_GID":"8d824808-1de5-4ca1-9869-d162e913e9a1","VIP_Card":"18338258258","VIP_Name":"来了","VIP_Phone":"18338258258","VIP_FaceNumber":"他特踏踏","CO_OrderCode":"SP1904041606134516","CO_ConsumeWay":"现金(30.00)","CO_WayCode":"XJZF","CO_Monetary":30,"CO_TotalPrice":30,"CO_Integral":10,"CO_BalanceCard":141.6,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 16:06:17","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"e5fffa8e-fc0c-4713-ada0-5d36eaaf005c","CO_GID":"4e02a6ab-bceb-4fa2-b9f4-572fa8f562e9","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"095f8cdd-08b4-47c2-ab38-d57a95b46fbc","CO_GID":"4e02a6ab-bceb-4fa2-b9f4-572fa8f562e9","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"8491ccab-e904-482e-a86b-b0d38018d6c5","CO_GID":"4e02a6ab-bceb-4fa2-b9f4-572fa8f562e9","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"全部","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:14:26","GID":"4e02a6ab-bceb-4fa2-b9f4-572fa8f562e9","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041550364263","CO_ConsumeWay":"现金(11111131.00)","CO_WayCode":"XJZF","CO_Monetary":1.1111131E7,"CO_TotalPrice":1.1111131E7,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 15:50:42","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null},{"ViewGoodsDetail":[{"GID":"e226664f-3d3c-456a-9ed1-0aff8cf396aa","CO_GID":"6e56a135-2505-43da-b568-9a0279968c07","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"c02b01d5-3ba1-4cfc-9725-a03cfe3f9568","CO_GID":"6e56a135-2505-43da-b568-9a0279968c07","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"b6fb5dbc-3767-4c75-9b3d-87807b541cc3","CO_GID":"6e56a135-2505-43da-b568-9a0279968c07","PM_GID":"085064cb-decd-4bf5-b6e6-cd9ec3386228","PM_IsService":0,"PM_Code":"6914068012933","PM_Name":"158444回","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}],"RL_GID":null,"CC_GID":null,"EM_GIDList":null,"PayPoint":0,"DisMoney":0,"VCH_Money":0,"VCH_Point":0,"CO_EMName":"全部","CO_EMGID":null,"CO_SSMoney":0,"CO_ZLMoney":0,"CO_ActivityName":null,"CO_ActivityValue":null,"CO_Discount":null,"CO_CreateTime":"2019-04-04 16:12:20","GID":"6e56a135-2505-43da-b568-9a0279968c07","VIP_GID":"69542ce9-f091-40cb-b40b-fe4a3ac1694c","VIP_Card":"00000","VIP_Name":"散客","VIP_Phone":null,"VIP_FaceNumber":null,"CO_OrderCode":"SP1904041548514838","CO_ConsumeWay":"现金(30.00)","CO_WayCode":"XJZF","CO_Monetary":30,"CO_TotalPrice":30,"CO_Integral":0,"CO_BalanceCard":0,"CO_Type":"商品消费","CO_TypeCode":"SPXF","CO_CommissionAmount":null,"CO_Remark":"","CO_Creator":"分号1","CO_UpdateTime":"2019-04-04 15:48:57","CY_GID":null,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":null,"SM_Phone":null,"SM_Addr":null,"CO_Identifying":"已完成","CO_IdentifyingState":99,"CO_OrderState":null,"CO_StateValue":null,"CO_Device":2,"CO_ActivityType":null,"CO_ActivityMoney":null}]
         * StatisticsInfo : {"DataCount":0,"Monetary":1.116136592571E11,"DiscountMoney":1.6213164185E9,"AllNumber":147,"FavorableMoney":0,"GrossMargin":0,"CostMoney":0}
         * TrendData : null
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private StatisticsInfoBean StatisticsInfo;
        private Object TrendData;
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

        public Object getTrendData() {
            return TrendData;
        }

        public void setTrendData(Object TrendData) {
            this.TrendData = TrendData;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class StatisticsInfoBean implements Serializable{
            /**
             * DataCount : 0
             * Monetary : 1.116136592571E11
             * DiscountMoney : 1.6213164185E9
             * AllNumber : 147.0
             * FavorableMoney : 0.0
             * GrossMargin : 0.0
             * CostMoney : 0.0
             */

            private int DataCount;
            private double Monetary;
            private double DiscountMoney;
            private double AllNumber;
            private double FavorableMoney;
            private double GrossMargin;
            private double CostMoney;

            public int getDataCount() {
                return DataCount;
            }

            public void setDataCount(int DataCount) {
                this.DataCount = DataCount;
            }

            public double getMonetary() {
                return Monetary;
            }

            public void setMonetary(double Monetary) {
                this.Monetary = Monetary;
            }

            public double getDiscountMoney() {
                return DiscountMoney;
            }

            public void setDiscountMoney(double DiscountMoney) {
                this.DiscountMoney = DiscountMoney;
            }

            public double getAllNumber() {
                return AllNumber;
            }

            public void setAllNumber(double AllNumber) {
                this.AllNumber = AllNumber;
            }

            public double getFavorableMoney() {
                return FavorableMoney;
            }

            public void setFavorableMoney(double FavorableMoney) {
                this.FavorableMoney = FavorableMoney;
            }

            public double getGrossMargin() {
                return GrossMargin;
            }

            public void setGrossMargin(double GrossMargin) {
                this.GrossMargin = GrossMargin;
            }

            public double getCostMoney() {
                return CostMoney;
            }

            public void setCostMoney(double CostMoney) {
                this.CostMoney = CostMoney;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * ViewGoodsDetail : [{"GID":"77703f7c-f3e4-4f87-a669-f6c9d68cad98","CO_GID":"9c6e4529-63ef-4a7f-ad7d-909c2961933c","PM_GID":"7ace77f3-219b-4680-993f-ea1fc5e543bc","PM_IsService":1,"PM_Code":"10","PM_Name":"1010","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"牙刷","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"61160364-44ac-47b9-bf45-233a6adaae44","CO_GID":"9c6e4529-63ef-4a7f-ad7d-909c2961933c","PM_GID":"9122060d-9cbd-4848-b27d-6cb92579af54","PM_IsService":0,"PM_Code":"1111","PM_Name":"111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"积分","PM_UnitPrice":1.1111111E7,"PM_Number":1,"SumPrice":1.1111111E7,"DiscountPrice":1.1111111E7,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0},{"GID":"1f9df93a-0bdb-4e5a-9736-e82f13b172db","CO_GID":"9c6e4529-63ef-4a7f-ad7d-909c2961933c","PM_GID":"6993ae3b-7eed-4a90-9117-ddc074b1e13e","PM_IsService":0,"PM_Code":"1454464511","PM_Name":"11111411114754664946619843436946464111","PM_BigImg":"/img/goods.png","PM_Modle":"","PM_Metering":"","PT_Name":"1","PM_UnitPrice":10,"PM_Number":1,"SumPrice":10,"DiscountPrice":10,"PM_Discount":1,"GOD_Integral":0,"State":"","GOD_EMName":"营销全部,商品、快速消费,全部","WR_Name":null,"GOD_ExpireDate":null,"GOD_Type":0}]
             * RL_GID : null
             * CC_GID : null
             * EM_GIDList : null
             * PayPoint : 0.0
             * DisMoney : 0.0
             * VCH_Money : 0.0
             * VCH_Point : 0.0
             * CO_EMName : 营销全部,商品、快速消费
             * CO_EMGID : null
             * CO_SSMoney : 0.0
             * CO_ZLMoney : 0.0
             * CO_ActivityName : null
             * CO_ActivityValue : null
             * CO_Discount : null
             * CO_CreateTime : 2019-04-04 17:06:34
             * GID : 9c6e4529-63ef-4a7f-ad7d-909c2961933c
             * VIP_GID : 69542ce9-f091-40cb-b40b-fe4a3ac1694c
             * VIP_Card : 00000
             * VIP_Name : 散客
             * VIP_Phone : null
             * VIP_FaceNumber : null
             * CO_OrderCode : SP1904041643293897
             * CO_ConsumeWay : 现金(11111131.00)
             * CO_WayCode : XJZF
             * CO_Monetary : 1.1111131E7
             * CO_TotalPrice : 1.1111131E7
             * CO_Integral : 0.0
             * CO_BalanceCard : 0.0
             * CO_Type : 商品消费
             * CO_TypeCode : SPXF
             * CO_CommissionAmount : null
             * CO_Remark :
             * CO_Creator : 分号1
             * CO_UpdateTime : 2019-04-04 16:43:32
             * CY_GID : null
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * SM_Name : jll2
             * SM_Contacter : null
             * SM_Phone : null
             * SM_Addr : null
             * CO_Identifying : 已完成
             * CO_IdentifyingState : 99
             * CO_OrderState : null
             * CO_StateValue : null
             * CO_Device : 2
             * CO_ActivityType : null
             * CO_ActivityMoney : null
             */

            private Object RL_GID;
            private Object CC_GID;
            private Object EM_GIDList;
            private double PayPoint;
            private double DisMoney;
            private double VCH_Money;
            private double VCH_Point;
            private String CO_EMName;
            private Object CO_EMGID;
            private double CO_SSMoney;
            private double CO_ZLMoney;
            private Object CO_ActivityName;
            private Object CO_ActivityValue;
            private Object CO_Discount;
            private String CO_CreateTime;
            private String GID;
            private String VIP_GID;
            private String VIP_Card;
            private String VIP_Name;
            private Object VIP_Phone;
            private Object VIP_FaceNumber;
            private String CO_OrderCode;
            private String CO_ConsumeWay;
            private String CO_WayCode;
            private double CO_Monetary;
            private double CO_TotalPrice;
            private double CO_Integral;
            private double CO_BalanceCard;
            private String CO_Type;
            private String CO_TypeCode;
            private Object CO_CommissionAmount;
            private String CO_Remark;
            private String CO_Creator;
            private String CO_UpdateTime;
            private Object CY_GID;
            private String SM_GID;
            private String SM_Name;
            private Object SM_Contacter;
            private Object SM_Phone;
            private Object SM_Addr;
            private String CO_Identifying;
            private int CO_IdentifyingState;
            private Object CO_OrderState;
            private Object CO_StateValue;
            private int CO_Device;
            private Object CO_ActivityType;
            private Object CO_ActivityMoney;
            private List<ViewGoodsDetailBean> ViewGoodsDetail;

            public boolean isVisiable() {
                return visiable;
            }

            public void setVisiable(boolean visiable) {
                this.visiable = visiable;
            }

            private boolean visiable;

            public Object getRL_GID() {
                return RL_GID;
            }

            public void setRL_GID(Object RL_GID) {
                this.RL_GID = RL_GID;
            }

            public Object getCC_GID() {
                return CC_GID;
            }

            public void setCC_GID(Object CC_GID) {
                this.CC_GID = CC_GID;
            }

            public Object getEM_GIDList() {
                return EM_GIDList;
            }

            public void setEM_GIDList(Object EM_GIDList) {
                this.EM_GIDList = EM_GIDList;
            }

            public double getPayPoint() {
                return PayPoint;
            }

            public void setPayPoint(double PayPoint) {
                this.PayPoint = PayPoint;
            }

            public double getDisMoney() {
                return DisMoney;
            }

            public void setDisMoney(double DisMoney) {
                this.DisMoney = DisMoney;
            }

            public double getVCH_Money() {
                return VCH_Money;
            }

            public void setVCH_Money(double VCH_Money) {
                this.VCH_Money = VCH_Money;
            }

            public double getVCH_Point() {
                return VCH_Point;
            }

            public void setVCH_Point(double VCH_Point) {
                this.VCH_Point = VCH_Point;
            }

            public String getCO_EMName() {
                return CO_EMName;
            }

            public void setCO_EMName(String CO_EMName) {
                this.CO_EMName = CO_EMName;
            }

            public Object getCO_EMGID() {
                return CO_EMGID;
            }

            public void setCO_EMGID(Object CO_EMGID) {
                this.CO_EMGID = CO_EMGID;
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

            public Object getCO_ActivityName() {
                return CO_ActivityName;
            }

            public void setCO_ActivityName(Object CO_ActivityName) {
                this.CO_ActivityName = CO_ActivityName;
            }

            public Object getCO_ActivityValue() {
                return CO_ActivityValue;
            }

            public void setCO_ActivityValue(Object CO_ActivityValue) {
                this.CO_ActivityValue = CO_ActivityValue;
            }

            public Object getCO_Discount() {
                return CO_Discount;
            }

            public void setCO_Discount(Object CO_Discount) {
                this.CO_Discount = CO_Discount;
            }

            public String getCO_CreateTime() {
                return CO_CreateTime;
            }

            public void setCO_CreateTime(String CO_CreateTime) {
                this.CO_CreateTime = CO_CreateTime;
            }

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

            public Object getVIP_Phone() {
                return VIP_Phone;
            }

            public void setVIP_Phone(Object VIP_Phone) {
                this.VIP_Phone = VIP_Phone;
            }

            public Object getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(Object VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public String getCO_OrderCode() {
                return CO_OrderCode;
            }

            public void setCO_OrderCode(String CO_OrderCode) {
                this.CO_OrderCode = CO_OrderCode;
            }

            public String getCO_ConsumeWay() {
                return CO_ConsumeWay;
            }

            public void setCO_ConsumeWay(String CO_ConsumeWay) {
                this.CO_ConsumeWay = CO_ConsumeWay;
            }

            public String getCO_WayCode() {
                return CO_WayCode;
            }

            public void setCO_WayCode(String CO_WayCode) {
                this.CO_WayCode = CO_WayCode;
            }

            public double getCO_Monetary() {
                return CO_Monetary;
            }

            public void setCO_Monetary(double CO_Monetary) {
                this.CO_Monetary = CO_Monetary;
            }

            public double getCO_TotalPrice() {
                return CO_TotalPrice;
            }

            public void setCO_TotalPrice(double CO_TotalPrice) {
                this.CO_TotalPrice = CO_TotalPrice;
            }

            public double getCO_Integral() {
                return CO_Integral;
            }

            public void setCO_Integral(double CO_Integral) {
                this.CO_Integral = CO_Integral;
            }

            public double getCO_BalanceCard() {
                return CO_BalanceCard;
            }

            public void setCO_BalanceCard(double CO_BalanceCard) {
                this.CO_BalanceCard = CO_BalanceCard;
            }

            public String getCO_Type() {
                return CO_Type;
            }

            public void setCO_Type(String CO_Type) {
                this.CO_Type = CO_Type;
            }

            public String getCO_TypeCode() {
                return CO_TypeCode;
            }

            public void setCO_TypeCode(String CO_TypeCode) {
                this.CO_TypeCode = CO_TypeCode;
            }

            public Object getCO_CommissionAmount() {
                return CO_CommissionAmount;
            }

            public void setCO_CommissionAmount(Object CO_CommissionAmount) {
                this.CO_CommissionAmount = CO_CommissionAmount;
            }

            public String getCO_Remark() {
                return CO_Remark;
            }

            public void setCO_Remark(String CO_Remark) {
                this.CO_Remark = CO_Remark;
            }

            public String getCO_Creator() {
                return CO_Creator;
            }

            public void setCO_Creator(String CO_Creator) {
                this.CO_Creator = CO_Creator;
            }

            public String getCO_UpdateTime() {
                return CO_UpdateTime;
            }

            public void setCO_UpdateTime(String CO_UpdateTime) {
                this.CO_UpdateTime = CO_UpdateTime;
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

            public Object getSM_Contacter() {
                return SM_Contacter;
            }

            public void setSM_Contacter(Object SM_Contacter) {
                this.SM_Contacter = SM_Contacter;
            }

            public Object getSM_Phone() {
                return SM_Phone;
            }

            public void setSM_Phone(Object SM_Phone) {
                this.SM_Phone = SM_Phone;
            }

            public Object getSM_Addr() {
                return SM_Addr;
            }

            public void setSM_Addr(Object SM_Addr) {
                this.SM_Addr = SM_Addr;
            }

            public String getCO_Identifying() {
                return CO_Identifying;
            }

            public void setCO_Identifying(String CO_Identifying) {
                this.CO_Identifying = CO_Identifying;
            }

            public int getCO_IdentifyingState() {
                return CO_IdentifyingState;
            }

            public void setCO_IdentifyingState(int CO_IdentifyingState) {
                this.CO_IdentifyingState = CO_IdentifyingState;
            }

            public Object getCO_OrderState() {
                return CO_OrderState;
            }

            public void setCO_OrderState(Object CO_OrderState) {
                this.CO_OrderState = CO_OrderState;
            }

            public Object getCO_StateValue() {
                return CO_StateValue;
            }

            public void setCO_StateValue(Object CO_StateValue) {
                this.CO_StateValue = CO_StateValue;
            }

            public int getCO_Device() {
                return CO_Device;
            }

            public void setCO_Device(int CO_Device) {
                this.CO_Device = CO_Device;
            }

            public Object getCO_ActivityType() {
                return CO_ActivityType;
            }

            public void setCO_ActivityType(Object CO_ActivityType) {
                this.CO_ActivityType = CO_ActivityType;
            }

            public Object getCO_ActivityMoney() {
                return CO_ActivityMoney;
            }

            public void setCO_ActivityMoney(Object CO_ActivityMoney) {
                this.CO_ActivityMoney = CO_ActivityMoney;
            }

            public List<ViewGoodsDetailBean> getViewGoodsDetail() {
                return ViewGoodsDetail;
            }

            public void setViewGoodsDetail(List<ViewGoodsDetailBean> ViewGoodsDetail) {
                this.ViewGoodsDetail = ViewGoodsDetail;
            }

            public static class ViewGoodsDetailBean implements Serializable{
                /**
                 * GID : 77703f7c-f3e4-4f87-a669-f6c9d68cad98
                 * CO_GID : 9c6e4529-63ef-4a7f-ad7d-909c2961933c
                 * PM_GID : 7ace77f3-219b-4680-993f-ea1fc5e543bc
                 * PM_IsService : 1
                 * PM_Code : 10
                 * PM_Name : 1010
                 * PM_BigImg : /img/goods.png
                 * PM_Modle :
                 * PM_Metering :
                 * PT_Name : 牙刷
                 * PM_UnitPrice : 10.0
                 * PM_Number : 1.0
                 * SumPrice : 10.0
                 * DiscountPrice : 10.0
                 * PM_Discount : 1.0
                 * GOD_Integral : 0.0
                 * State :
                 * GOD_EMName : 营销全部
                 * WR_Name : null
                 * GOD_ExpireDate : null
                 * GOD_Type : 0
                 */

                private String GID;
                private String CO_GID;
                private String PM_GID;
                private int PM_IsService;
                private String PM_Code;
                private String PM_Name;
                private String PM_BigImg;
                private String PM_Modle;
                private String PM_Metering;
                private String PT_Name;
                private double PM_UnitPrice;
                private double PM_Number;
                private double SumPrice;
                private double DiscountPrice;
                private double PM_Discount;
                private double GOD_Integral;
                private String State;
                private String GOD_EMName;
                private Object WR_Name;
                private Object GOD_ExpireDate;
                private int GOD_Type;

                public String getGID() {
                    return GID;
                }

                public void setGID(String GID) {
                    this.GID = GID;
                }

                public String getCO_GID() {
                    return CO_GID;
                }

                public void setCO_GID(String CO_GID) {
                    this.CO_GID = CO_GID;
                }

                public String getPM_GID() {
                    return PM_GID;
                }

                public void setPM_GID(String PM_GID) {
                    this.PM_GID = PM_GID;
                }

                public int getPM_IsService() {
                    return PM_IsService;
                }

                public void setPM_IsService(int PM_IsService) {
                    this.PM_IsService = PM_IsService;
                }

                public String getPM_Code() {
                    return PM_Code;
                }

                public void setPM_Code(String PM_Code) {
                    this.PM_Code = PM_Code;
                }

                public String getPM_Name() {
                    return PM_Name;
                }

                public void setPM_Name(String PM_Name) {
                    this.PM_Name = PM_Name;
                }

                public String getPM_BigImg() {
                    return PM_BigImg;
                }

                public void setPM_BigImg(String PM_BigImg) {
                    this.PM_BigImg = PM_BigImg;
                }

                public String getPM_Modle() {
                    return PM_Modle;
                }

                public void setPM_Modle(String PM_Modle) {
                    this.PM_Modle = PM_Modle;
                }

                public String getPM_Metering() {
                    return PM_Metering;
                }

                public void setPM_Metering(String PM_Metering) {
                    this.PM_Metering = PM_Metering;
                }

                public String getPT_Name() {
                    return PT_Name;
                }

                public void setPT_Name(String PT_Name) {
                    this.PT_Name = PT_Name;
                }

                public double getPM_UnitPrice() {
                    return PM_UnitPrice;
                }

                public void setPM_UnitPrice(double PM_UnitPrice) {
                    this.PM_UnitPrice = PM_UnitPrice;
                }

                public double getPM_Number() {
                    return PM_Number;
                }

                public void setPM_Number(double PM_Number) {
                    this.PM_Number = PM_Number;
                }

                public double getSumPrice() {
                    return SumPrice;
                }

                public void setSumPrice(double SumPrice) {
                    this.SumPrice = SumPrice;
                }

                public double getDiscountPrice() {
                    return DiscountPrice;
                }

                public void setDiscountPrice(double DiscountPrice) {
                    this.DiscountPrice = DiscountPrice;
                }

                public double getPM_Discount() {
                    return PM_Discount;
                }

                public void setPM_Discount(double PM_Discount) {
                    this.PM_Discount = PM_Discount;
                }

                public double getGOD_Integral() {
                    return GOD_Integral;
                }

                public void setGOD_Integral(double GOD_Integral) {
                    this.GOD_Integral = GOD_Integral;
                }

                public String getState() {
                    return State;
                }

                public void setState(String State) {
                    this.State = State;
                }

                public String getGOD_EMName() {
                    return GOD_EMName;
                }

                public void setGOD_EMName(String GOD_EMName) {
                    this.GOD_EMName = GOD_EMName;
                }

                public Object getWR_Name() {
                    return WR_Name;
                }

                public void setWR_Name(Object WR_Name) {
                    this.WR_Name = WR_Name;
                }

                public Object getGOD_ExpireDate() {
                    return GOD_ExpireDate;
                }

                public void setGOD_ExpireDate(Object GOD_ExpireDate) {
                    this.GOD_ExpireDate = GOD_ExpireDate;
                }

                public int getGOD_Type() {
                    return GOD_Type;
                }

                public void setGOD_Type(int GOD_Type) {
                    this.GOD_Type = GOD_Type;
                }
            }
        }
    }
}
