package com.zhiluo.android.yunpu.member.manager.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${YSL} on 2018-04-02.
 */

public class YSLMemberInfoBean implements Serializable {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":18,"PageSize":20,"DataCount":350,"PageIndex":1,"DataList":[{"GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"16566332132","VIP_Name":"代码不知道写啥","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 16:45:44","VIP_Birthday":null,"VIP_CellPhone":"16566332132","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"c44c90ab-af07-43f9-9764-532d86c83039","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"3213854","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1},{"CF_GID":"0f79cf98-2701-4c90-9b81-b9093b45dc27","CF_FieldName":"潜力","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"12365","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 13:53:26","CF_Order":3,"CF_IsShowVIP":1},{"CF_GID":"8655ce6d-ab67-4986-81b7-848f35351acb","CF_FieldName":"潜","CF_FieldType":"选项","CF_ItemsValue":"1|2|3|","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 14:02:46","CF_Order":4,"CF_IsShowVIP":1},{"CF_GID":"a402637e-21ee-4a97-9fa5-b12db73d475e","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-29","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 16:45:44","CouponsList":null,"MessageVIP":null},{"GID":"54b39791-8796-4038-9c0d-74ed08473ee9","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"16597865465","VIP_Name":"小伙子在写代码","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 16:43:04","VIP_Birthday":null,"VIP_CellPhone":"16597865465","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"acd95615-4ea8-4098-b81e-f8914767d8d6","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"132654","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"54b39791-8796-4038-9c0d-74ed08473ee9","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1},{"CF_GID":"6c5f86c4-0b79-48f3-9b3e-9a6364347e9c","CF_FieldName":"潜力","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"1666","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"54b39791-8796-4038-9c0d-74ed08473ee9","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 13:53:26","CF_Order":3,"CF_IsShowVIP":1},{"CF_GID":"9f3f8feb-c74e-435b-b076-25e0853fc202","CF_FieldName":"潜","CF_FieldType":"选项","CF_ItemsValue":"1|2|3|","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"54b39791-8796-4038-9c0d-74ed08473ee9","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 14:02:46","CF_Order":4,"CF_IsShowVIP":1},{"CF_GID":"e4d9dcaa-1856-4e4c-8e96-a1c5caa4f4d2","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-29","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"54b39791-8796-4038-9c0d-74ed08473ee9","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 16:43:04","CouponsList":null,"MessageVIP":null},{"GID":"bc6852f4-85a3-4865-ad58-eb9cb47b3f64","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"18441545645","VIP_Name":"帅气的小伙子","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 16:38:13","VIP_Birthday":null,"VIP_CellPhone":"18441545645","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"f89d39c4-05e8-490a-9af7-58d20bee3e15","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"12312","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"bc6852f4-85a3-4865-ad58-eb9cb47b3f64","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1},{"CF_GID":"afea8b25-2bbe-4582-bb96-5e18272199b8","CF_FieldName":"潜力","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"123456","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"bc6852f4-85a3-4865-ad58-eb9cb47b3f64","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 13:53:26","CF_Order":3,"CF_IsShowVIP":1},{"CF_GID":"76d223a1-3326-4db8-9eea-8d31bbebfd0c","CF_FieldName":"潜","CF_FieldType":"选项","CF_ItemsValue":"1|2|3|","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"bc6852f4-85a3-4865-ad58-eb9cb47b3f64","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 14:02:46","CF_Order":4,"CF_IsShowVIP":1},{"CF_GID":"1478dbaf-3846-4fec-a4ab-6e1693d6c76e","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-29","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"bc6852f4-85a3-4865-ad58-eb9cb47b3f64","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 16:38:13","CouponsList":null,"MessageVIP":null},{"GID":"f62ffdbc-5d24-4391-a9f9-8b63fbae09f2","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"13996953465","VIP_Name":"多么帅气","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 16:36:36","VIP_Birthday":null,"VIP_CellPhone":"13996953465","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"d631b67d-da2c-4a9c-b15d-4a413e60f299","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-29","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"f62ffdbc-5d24-4391-a9f9-8b63fbae09f2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 16:36:36","CouponsList":null,"MessageVIP":null},{"GID":"40593675-a559-43e4-a27b-503417e6aa90","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"18328578374","VIP_Name":"多好的小伙子","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 16:34:00","VIP_Birthday":null,"VIP_CellPhone":"18328578374","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"19e62607-c14a-4996-8c7d-88b78f2f1c99","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-30","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"40593675-a559-43e4-a27b-503417e6aa90","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 16:34:00","CouponsList":null,"MessageVIP":null},{"GID":"f75289bb-fdfd-498b-81d4-4462b6d88fa2","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"15815625617","VIP_Name":"会员自定义属性测试","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 15:45:12","VIP_Birthday":null,"VIP_CellPhone":"15815625617","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"00ab741a-5209-4457-b4fd-d0793d046315","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"123456789000","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"f75289bb-fdfd-498b-81d4-4462b6d88fa2","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1},{"CF_GID":"2d7f95de-5a53-43f4-b61d-af0b531c8e7c","CF_FieldName":"潜力","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"潜力文本text","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"f75289bb-fdfd-498b-81d4-4462b6d88fa2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 13:53:26","CF_Order":3,"CF_IsShowVIP":1},{"CF_GID":"04c92b41-a77e-464c-a21c-9f89e3469732","CF_FieldName":"潜","CF_FieldType":"选项","CF_ItemsValue":"1|2|3|","CF_Value":"3","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"f75289bb-fdfd-498b-81d4-4462b6d88fa2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 14:02:46","CF_Order":4,"CF_IsShowVIP":1},{"CF_GID":"705a42e1-41c5-4a22-a168-723841722c14","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-31","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"f75289bb-fdfd-498b-81d4-4462b6d88fa2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 15:45:12","CouponsList":null,"MessageVIP":null},{"GID":"be3f8783-fa1a-4733-9ffb-0e7c34877b1a","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"15815621112","VIP_Name":"1淀粉软糖","VIP_Sex":0,"VCH_CreateTime":"2018-10-09 16:10:08","VIP_Birthday":"","VIP_CellPhone":"15815621112","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"12345678910111213","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"15815625611","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":10,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"b3d8f1e8-afa0-4213-844a-55e1a058dae3","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"1","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"be3f8783-fa1a-4733-9ffb-0e7c34877b1a","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1},{"CF_GID":"a2b8c900-6049-4d7c-855d-8688bbef8d3d","CF_FieldName":"潜力","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"123456","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"be3f8783-fa1a-4733-9ffb-0e7c34877b1a","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 13:53:26","CF_Order":3,"CF_IsShowVIP":1},{"CF_GID":"0d17804a-d1e2-4808-8ca6-33a6b214fa02","CF_FieldName":"潜","CF_FieldType":"选项","CF_ItemsValue":"1|2|3|","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"be3f8783-fa1a-4733-9ffb-0e7c34877b1a","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 14:02:46","CF_Order":4,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":4190,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":28,"MCA_TotalCharge":29,"MA_UpdateTime":"2018-10-26 15:27:08","CouponsList":null,"MessageVIP":null},{"GID":"0ed5d981-b306-41ba-9b47-eb479a07027c","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"16698665646","VIP_Name":"张飞","VIP_Sex":0,"VCH_CreateTime":"2018-10-09 15:58:42","VIP_Birthday":null,"VIP_CellPhone":"16698665646","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":0,"VIP_Overdue":"2018-10-19 00:00:00","VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"3df0f677-442e-43ef-8706-18a54d7a2ded","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":10,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"de06c1df-dacc-4c26-92f9-4b212f33974f","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"1","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"0ed5d981-b306-41ba-9b47-eb479a07027c","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-09 15:58:43","CouponsList":null,"MessageVIP":null},{"GID":"97c742a1-e58a-4394-9bfc-45d093df5eb2","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"13258984566","VIP_Name":"还可以","VIP_Sex":0,"VCH_CreateTime":"2018-10-09 15:47:09","VIP_Birthday":null,"VIP_CellPhone":"13258984566","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"039341a9-fb34-42e7-9760-17749d9bd05e","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"111","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"97c742a1-e58a-4394-9bfc-45d093df5eb2","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":222,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":1,"MCA_TotalCharge":1,"MA_UpdateTime":"2018-10-23 16:28:34","CouponsList":null,"MessageVIP":null},{"GID":"5d97ada1-aa0f-4332-863c-69c7fd5592f9","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"18381306467","VIP_Name":"嘤发","VIP_Sex":0,"VCH_CreateTime":"2018-10-09 15:11:11","VIP_Birthday":null,"VIP_CellPhone":"18381306467","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"90a0aea5-6df0-460c-8ec8-a6965f76ecaa","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"0","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"5d97ada1-aa0f-4332-863c-69c7fd5592f9","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-09 15:11:11","CouponsList":null,"MessageVIP":null},{"GID":"11f71339-f4dd-4503-ba8c-e8310c9b1d6e","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"15815625611","VIP_Name":"熊姿","VIP_Sex":0,"VCH_CreateTime":"2018-10-09 15:10:34","VIP_Birthday":null,"VIP_CellPhone":"15815625611","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"07ee58f7-d0f1-40ab-9640-17c498a0c5a8","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"0","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"11f71339-f4dd-4503-ba8c-e8310c9b1d6e","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-09 15:10:34","CouponsList":null,"MessageVIP":null},{"GID":"dea71328-55dd-46d8-829c-706ae4296bd9","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"13599523","VIP_Name":"555想","VIP_Sex":0,"VCH_CreateTime":"2018-10-08 17:13:27","VIP_Birthday":null,"VIP_CellPhone":"13599523","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"efb5e84a-21d8-48cd-aff0-25eefc6db1be","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":10,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"等级测试","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-08 17:13:27","CouponsList":null,"MessageVIP":null},{"GID":"a0ae0a37-9828-43d5-9694-2a7a957257db","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"1211211","VIP_Name":"12小","VIP_Sex":0,"VCH_CreateTime":"2018-10-08 15:59:12","VIP_Birthday":null,"VIP_CellPhone":"1211211","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":30,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-08 16:00:02","CouponsList":null,"MessageVIP":null},{"GID":"48b9b6f3-8852-4a1c-90dc-1c1f0be568b0","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"15100837660","VIP_Name":"张","VIP_Sex":0,"VCH_CreateTime":"2018-09-19 16:38:48","VIP_Birthday":null,"VIP_CellPhone":"15100837660","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":2,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"94f2d09b-d29d-4807-8853-481c8b780781","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"积分","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-19 16:38:48","CouponsList":null,"MessageVIP":null},{"GID":"2aa978ad-c486-4745-a07c-b3fe8f9b040b","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"1366656232","VIP_Name":"会员002","VIP_Sex":0,"VCH_CreateTime":"2018-09-19 11:15:24","VIP_Birthday":null,"VIP_CellPhone":"1366656232","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"1b2ab1c0-a22c-4c3a-b181-70d5e6690b36","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"121","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"2aa978ad-c486-4745-a07c-b3fe8f9b040b","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1}],"MA_AvailableBalance":120,"MA_AggregateAmount":0,"MA_AggregateStoredValue":120,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-27 13:42:27","CouponsList":null,"MessageVIP":null},{"GID":"a61b6319-f434-4166-bdb8-e640b4137db8","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"35465646132","VIP_Name":"王小二","VIP_Sex":0,"VCH_CreateTime":"2018-09-19 10:34:58","VIP_Birthday":null,"VIP_CellPhone":"35465646132","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":4768,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":7,"MCA_TotalCharge":7,"MA_UpdateTime":"2018-09-19 11:44:31","CouponsList":null,"MessageVIP":null},{"GID":"47a8c702-acd6-404d-b282-6c1efaa5592d","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"54564674636","VIP_Name":"王小可","VIP_Sex":0,"VCH_CreateTime":"2018-09-19 10:33:15","VIP_Birthday":null,"VIP_CellPhone":"54564674636","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-19 10:33:15","CouponsList":null,"MessageVIP":null},{"GID":"d22ee847-91f4-471f-b91a-eebf1a7a3acc","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"15598321666","VIP_Name":"小可","VIP_Sex":0,"VCH_CreateTime":"2018-09-19 10:16:14","VIP_Birthday":null,"VIP_CellPhone":"15598321666","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":0,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"0ef0e3ed-c67d-45fc-bb73-4f40dca39e04","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":10,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"临时会员1天","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-19 10:16:14","CouponsList":null,"MessageVIP":null},{"GID":"8ad23719-3d54-42af-af34-f78b4de6c644","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"http://img.yunvip123.com/CommonImg/f7c6d473-be5a-4b39-a1f1-3ebc8adce7ab.png","VCH_Card":"12676","VIP_Name":"1模糊","VIP_Sex":0,"VCH_CreateTime":"2018-09-14 15:44:03","VIP_Birthday":null,"VIP_CellPhone":"12676","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-14 15:44:03","CouponsList":null,"MessageVIP":null},{"GID":"e65e97f0-34be-414e-9a14-7a0b9ecd8d37","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"100","VIP_Name":"52546kjh","VIP_Sex":0,"VCH_CreateTime":"2018-09-11 18:53:58","VIP_Birthday":null,"VIP_CellPhone":"100","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":0,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"0ef0e3ed-c67d-45fc-bb73-4f40dca39e04","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"临时会员1天","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-11 18:53:58","CouponsList":null,"MessageVIP":null}],"StatisticsInfo":{"MA_AccountBalance":114322,"MA_AvailableIntegral":107718}}
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

    public static class DataBean implements Serializable {
        /**
         * PageTotal : 18
         * PageSize : 20
         * DataCount : 350
         * PageIndex : 1
         * DataList : [{"GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"16566332132","VIP_Name":"代码不知道写啥","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 16:45:44","VIP_Birthday":null,"VIP_CellPhone":"16566332132","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"c44c90ab-af07-43f9-9764-532d86c83039","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"3213854","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1},{"CF_GID":"0f79cf98-2701-4c90-9b81-b9093b45dc27","CF_FieldName":"潜力","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"12365","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 13:53:26","CF_Order":3,"CF_IsShowVIP":1},{"CF_GID":"8655ce6d-ab67-4986-81b7-848f35351acb","CF_FieldName":"潜","CF_FieldType":"选项","CF_ItemsValue":"1|2|3|","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 14:02:46","CF_Order":4,"CF_IsShowVIP":1},{"CF_GID":"a402637e-21ee-4a97-9fa5-b12db73d475e","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-29","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 16:45:44","CouponsList":null,"MessageVIP":null},{"GID":"54b39791-8796-4038-9c0d-74ed08473ee9","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"16597865465","VIP_Name":"小伙子在写代码","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 16:43:04","VIP_Birthday":null,"VIP_CellPhone":"16597865465","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"acd95615-4ea8-4098-b81e-f8914767d8d6","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"132654","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"54b39791-8796-4038-9c0d-74ed08473ee9","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1},{"CF_GID":"6c5f86c4-0b79-48f3-9b3e-9a6364347e9c","CF_FieldName":"潜力","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"1666","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"54b39791-8796-4038-9c0d-74ed08473ee9","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 13:53:26","CF_Order":3,"CF_IsShowVIP":1},{"CF_GID":"9f3f8feb-c74e-435b-b076-25e0853fc202","CF_FieldName":"潜","CF_FieldType":"选项","CF_ItemsValue":"1|2|3|","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"54b39791-8796-4038-9c0d-74ed08473ee9","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 14:02:46","CF_Order":4,"CF_IsShowVIP":1},{"CF_GID":"e4d9dcaa-1856-4e4c-8e96-a1c5caa4f4d2","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-29","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"54b39791-8796-4038-9c0d-74ed08473ee9","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 16:43:04","CouponsList":null,"MessageVIP":null},{"GID":"bc6852f4-85a3-4865-ad58-eb9cb47b3f64","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"18441545645","VIP_Name":"帅气的小伙子","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 16:38:13","VIP_Birthday":null,"VIP_CellPhone":"18441545645","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"f89d39c4-05e8-490a-9af7-58d20bee3e15","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"12312","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"bc6852f4-85a3-4865-ad58-eb9cb47b3f64","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1},{"CF_GID":"afea8b25-2bbe-4582-bb96-5e18272199b8","CF_FieldName":"潜力","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"123456","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"bc6852f4-85a3-4865-ad58-eb9cb47b3f64","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 13:53:26","CF_Order":3,"CF_IsShowVIP":1},{"CF_GID":"76d223a1-3326-4db8-9eea-8d31bbebfd0c","CF_FieldName":"潜","CF_FieldType":"选项","CF_ItemsValue":"1|2|3|","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"bc6852f4-85a3-4865-ad58-eb9cb47b3f64","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 14:02:46","CF_Order":4,"CF_IsShowVIP":1},{"CF_GID":"1478dbaf-3846-4fec-a4ab-6e1693d6c76e","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-29","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"bc6852f4-85a3-4865-ad58-eb9cb47b3f64","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 16:38:13","CouponsList":null,"MessageVIP":null},{"GID":"f62ffdbc-5d24-4391-a9f9-8b63fbae09f2","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"13996953465","VIP_Name":"多么帅气","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 16:36:36","VIP_Birthday":null,"VIP_CellPhone":"13996953465","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"d631b67d-da2c-4a9c-b15d-4a413e60f299","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-29","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"f62ffdbc-5d24-4391-a9f9-8b63fbae09f2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 16:36:36","CouponsList":null,"MessageVIP":null},{"GID":"40593675-a559-43e4-a27b-503417e6aa90","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"18328578374","VIP_Name":"多好的小伙子","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 16:34:00","VIP_Birthday":null,"VIP_CellPhone":"18328578374","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"19e62607-c14a-4996-8c7d-88b78f2f1c99","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-30","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"40593675-a559-43e4-a27b-503417e6aa90","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 16:34:00","CouponsList":null,"MessageVIP":null},{"GID":"f75289bb-fdfd-498b-81d4-4462b6d88fa2","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"15815625617","VIP_Name":"会员自定义属性测试","VIP_Sex":0,"VCH_CreateTime":"2018-10-29 15:45:12","VIP_Birthday":null,"VIP_CellPhone":"15815625617","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"00ab741a-5209-4457-b4fd-d0793d046315","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"123456789000","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"f75289bb-fdfd-498b-81d4-4462b6d88fa2","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1},{"CF_GID":"2d7f95de-5a53-43f4-b61d-af0b531c8e7c","CF_FieldName":"潜力","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"潜力文本text","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"f75289bb-fdfd-498b-81d4-4462b6d88fa2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 13:53:26","CF_Order":3,"CF_IsShowVIP":1},{"CF_GID":"04c92b41-a77e-464c-a21c-9f89e3469732","CF_FieldName":"潜","CF_FieldType":"选项","CF_ItemsValue":"1|2|3|","CF_Value":"3","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"f75289bb-fdfd-498b-81d4-4462b6d88fa2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 14:02:46","CF_Order":4,"CF_IsShowVIP":1},{"CF_GID":"705a42e1-41c5-4a22-a168-723841722c14","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-31","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"f75289bb-fdfd-498b-81d4-4462b6d88fa2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":10,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-29 15:45:12","CouponsList":null,"MessageVIP":null},{"GID":"be3f8783-fa1a-4733-9ffb-0e7c34877b1a","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"15815621112","VIP_Name":"1淀粉软糖","VIP_Sex":0,"VCH_CreateTime":"2018-10-09 16:10:08","VIP_Birthday":"","VIP_CellPhone":"15815621112","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"12345678910111213","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"15815625611","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":10,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"b3d8f1e8-afa0-4213-844a-55e1a058dae3","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"1","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"be3f8783-fa1a-4733-9ffb-0e7c34877b1a","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1},{"CF_GID":"a2b8c900-6049-4d7c-855d-8688bbef8d3d","CF_FieldName":"潜力","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"123456","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"be3f8783-fa1a-4733-9ffb-0e7c34877b1a","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 13:53:26","CF_Order":3,"CF_IsShowVIP":1},{"CF_GID":"0d17804a-d1e2-4808-8ca6-33a6b214fa02","CF_FieldName":"潜","CF_FieldType":"选项","CF_ItemsValue":"1|2|3|","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"be3f8783-fa1a-4733-9ffb-0e7c34877b1a","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 14:02:46","CF_Order":4,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":4190,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":28,"MCA_TotalCharge":29,"MA_UpdateTime":"2018-10-26 15:27:08","CouponsList":null,"MessageVIP":null},{"GID":"0ed5d981-b306-41ba-9b47-eb479a07027c","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"16698665646","VIP_Name":"张飞","VIP_Sex":0,"VCH_CreateTime":"2018-10-09 15:58:42","VIP_Birthday":null,"VIP_CellPhone":"16698665646","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":0,"VIP_Overdue":"2018-10-19 00:00:00","VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"3df0f677-442e-43ef-8706-18a54d7a2ded","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":10,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"de06c1df-dacc-4c26-92f9-4b212f33974f","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"1","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"0ed5d981-b306-41ba-9b47-eb479a07027c","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-09 15:58:43","CouponsList":null,"MessageVIP":null},{"GID":"97c742a1-e58a-4394-9bfc-45d093df5eb2","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"13258984566","VIP_Name":"还可以","VIP_Sex":0,"VCH_CreateTime":"2018-10-09 15:47:09","VIP_Birthday":null,"VIP_CellPhone":"13258984566","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"039341a9-fb34-42e7-9760-17749d9bd05e","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"111","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"97c742a1-e58a-4394-9bfc-45d093df5eb2","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":222,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":1,"MCA_TotalCharge":1,"MA_UpdateTime":"2018-10-23 16:28:34","CouponsList":null,"MessageVIP":null},{"GID":"5d97ada1-aa0f-4332-863c-69c7fd5592f9","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"18381306467","VIP_Name":"嘤发","VIP_Sex":0,"VCH_CreateTime":"2018-10-09 15:11:11","VIP_Birthday":null,"VIP_CellPhone":"18381306467","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"90a0aea5-6df0-460c-8ec8-a6965f76ecaa","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"0","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"5d97ada1-aa0f-4332-863c-69c7fd5592f9","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-09 15:11:11","CouponsList":null,"MessageVIP":null},{"GID":"11f71339-f4dd-4503-ba8c-e8310c9b1d6e","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"15815625611","VIP_Name":"熊姿","VIP_Sex":0,"VCH_CreateTime":"2018-10-09 15:10:34","VIP_Birthday":null,"VIP_CellPhone":"15815625611","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"07ee58f7-d0f1-40ab-9640-17c498a0c5a8","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"0","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"11f71339-f4dd-4503-ba8c-e8310c9b1d6e","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1}],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-09 15:10:34","CouponsList":null,"MessageVIP":null},{"GID":"dea71328-55dd-46d8-829c-706ae4296bd9","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"13599523","VIP_Name":"555想","VIP_Sex":0,"VCH_CreateTime":"2018-10-08 17:13:27","VIP_Birthday":null,"VIP_CellPhone":"13599523","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"efb5e84a-21d8-48cd-aff0-25eefc6db1be","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":10,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"等级测试","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-08 17:13:27","CouponsList":null,"MessageVIP":null},{"GID":"a0ae0a37-9828-43d5-9694-2a7a957257db","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"1211211","VIP_Name":"12小","VIP_Sex":0,"VCH_CreateTime":"2018-10-08 15:59:12","VIP_Birthday":null,"VIP_CellPhone":"1211211","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":30,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-10-08 16:00:02","CouponsList":null,"MessageVIP":null},{"GID":"48b9b6f3-8852-4a1c-90dc-1c1f0be568b0","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"15100837660","VIP_Name":"张","VIP_Sex":0,"VCH_CreateTime":"2018-09-19 16:38:48","VIP_Birthday":null,"VIP_CellPhone":"15100837660","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":2,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"94f2d09b-d29d-4807-8853-481c8b780781","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"积分","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-19 16:38:48","CouponsList":null,"MessageVIP":null},{"GID":"2aa978ad-c486-4745-a07c-b3fe8f9b040b","VIP_RegSource":0,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"1366656232","VIP_Name":"会员002","VIP_Sex":0,"VCH_CreateTime":"2018-09-19 11:15:24","VIP_Birthday":null,"VIP_CellPhone":"1366656232","VIP_ICCard":"","VIP_Email":"","VIP_Remark":"","VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":"","VIP_Addr":"","VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[{"CF_GID":"1b2ab1c0-a22c-4c3a-b181-70d5e6690b36","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"121","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"2aa978ad-c486-4745-a07c-b3fe8f9b040b","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1}],"MA_AvailableBalance":120,"MA_AggregateAmount":0,"MA_AggregateStoredValue":120,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-27 13:42:27","CouponsList":null,"MessageVIP":null},{"GID":"a61b6319-f434-4166-bdb8-e640b4137db8","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"35465646132","VIP_Name":"王小二","VIP_Sex":0,"VCH_CreateTime":"2018-09-19 10:34:58","VIP_Birthday":null,"VIP_CellPhone":"35465646132","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":4768,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":7,"MCA_TotalCharge":7,"MA_UpdateTime":"2018-09-19 11:44:31","CouponsList":null,"MessageVIP":null},{"GID":"47a8c702-acd6-404d-b282-6c1efaa5592d","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"54564674636","VIP_Name":"王小可","VIP_Sex":0,"VCH_CreateTime":"2018-09-19 10:33:15","VIP_Birthday":null,"VIP_CellPhone":"54564674636","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-19 10:33:15","CouponsList":null,"MessageVIP":null},{"GID":"d22ee847-91f4-471f-b91a-eebf1a7a3acc","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"15598321666","VIP_Name":"小可","VIP_Sex":0,"VCH_CreateTime":"2018-09-19 10:16:14","VIP_Birthday":null,"VIP_CellPhone":"15598321666","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":0,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"0ef0e3ed-c67d-45fc-bb73-4f40dca39e04","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":10,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"临时会员1天","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-19 10:16:14","CouponsList":null,"MessageVIP":null},{"GID":"8ad23719-3d54-42af-af34-f78b4de6c644","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"http://img.yunvip123.com/CommonImg/f7c6d473-be5a-4b39-a1f1-3ebc8adce7ab.png","VCH_Card":"12676","VIP_Name":"1模糊","VIP_Sex":0,"VCH_CreateTime":"2018-09-14 15:44:03","VIP_Birthday":null,"VIP_CellPhone":"12676","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"45a168cb-493e-43b7-8cfc-730ef4da27c7","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"默认等级","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-14 15:44:03","CouponsList":null,"MessageVIP":null},{"GID":"e65e97f0-34be-414e-9a14-7a0b9ecd8d37","VIP_RegSource":2,"EM_Name":"","VIP_HeadImg":"/img/nohead.png","VCH_Card":"100","VIP_Name":"52546kjh","VIP_Sex":0,"VCH_CreateTime":"2018-09-11 18:53:58","VIP_Birthday":null,"VIP_CellPhone":"100","VIP_ICCard":null,"VIP_Email":null,"VIP_Remark":null,"VIP_IsLunarCalendar":0,"VIP_IsForver":0,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"0ef0e3ed-c67d-45fc-bb73-4f40dca39e04","EM_ID":null,"VIP_Referee":null,"VIP_Addr":null,"VIP_FixedPhone":"","VIP_Creator":"销售001","SM_Name":"默认店铺","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","VCH_Fee":0,"VIP_OpenID":null,"VIP_InterCalaryMonth":0,"MCA_HowManyDetail":null,"VG_Name":"临时会员1天","VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null,"DS_Value":0,"VS_Value":0,"RS_Value":0,"VGInfo":null,"CustomeFieldList":[],"MA_AvailableBalance":0,"MA_AggregateAmount":0,"MA_AggregateStoredValue":0,"MA_AvailableIntegral":0,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-09-11 18:53:58","CouponsList":null,"MessageVIP":null}]
         * StatisticsInfo : {"MA_AccountBalance":114322,"MA_AvailableIntegral":107718}
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

        public static class StatisticsInfoBean implements Serializable {
            /**
             * MA_AccountBalance : 114322.0
             * MA_AvailableIntegral : 107718.0
             */

            private double MA_AccountBalance;
            private double MA_AvailableIntegral;

            public double getMA_AccountBalance() {
                return MA_AccountBalance;
            }

            public void setMA_AccountBalance(double MA_AccountBalance) {
                this.MA_AccountBalance = MA_AccountBalance;
            }

            public double getMA_AvailableIntegral() {
                return MA_AvailableIntegral;
            }

            public void setMA_AvailableIntegral(double MA_AvailableIntegral) {
                this.MA_AvailableIntegral = MA_AvailableIntegral;
            }
        }

        public static class DataListBean implements Serializable {
            /**
             * GID : d3ac98dc-d5b4-47a7-847d-5804d4fff5b2
             * VIP_RegSource : 2
             * EM_Name :
             * VIP_HeadImg : /img/nohead.png
             * VCH_Card : 16566332132
             * VIP_Name : 代码不知道写啥
             * VIP_Sex : 0
             * VCH_CreateTime : 2018-10-29 16:45:44
             * VIP_Birthday : null
             * VIP_CellPhone : 16566332132
             * VIP_ICCard : null
             * VIP_Email : null
             * VIP_Remark : null
             * VIP_IsLunarCalendar : 0
             * VIP_IsForver : 1
             * VIP_Overdue : null
             * VIP_State : 0
             * VIP_FaceNumber :
             * VIP_Label : []
             * VG_GID : 45a168cb-493e-43b7-8cfc-730ef4da27c7
             * EM_ID : null
             * VIP_Referee : null
             * VIP_Addr : null
             * VIP_FixedPhone :
             * VIP_Creator : 销售001
             * SM_Name : 默认店铺
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * VCH_Fee : 0
             * VIP_OpenID : null
             * VIP_InterCalaryMonth : 0
             * MCA_HowManyDetail : null
             * VG_Name : 默认等级
             * VG_IsAccount : null
             * VG_IsIntegral : null
             * VG_IsDiscount : null
             * VG_IsCount : null
             * VG_IsTime : null
             * DS_Value : 0.0
             * VS_Value : 0.0
             * RS_Value : 0.0
             * VGInfo : null
             * CustomeFieldList : [{"CF_GID":"c44c90ab-af07-43f9-9764-532d86c83039","CF_FieldName":"卡号","CF_FieldType":"数字","CF_ItemsValue":"","CF_Value":"3213854","CF_Required":"否","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-07-20 09:59:36","CF_Order":1,"CF_IsShowVIP":1},{"CF_GID":"0f79cf98-2701-4c90-9b81-b9093b45dc27","CF_FieldName":"潜力","CF_FieldType":"文本","CF_ItemsValue":"","CF_Value":"12365","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 13:53:26","CF_Order":3,"CF_IsShowVIP":1},{"CF_GID":"8655ce6d-ab67-4986-81b7-848f35351acb","CF_FieldName":"潜","CF_FieldType":"选项","CF_ItemsValue":"1|2|3|","CF_Value":"1","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-17 14:02:46","CF_Order":4,"CF_IsShowVIP":1},{"CF_GID":"a402637e-21ee-4a97-9fa5-b12db73d475e","CF_FieldName":"办卡日期","CF_FieldType":"日期","CF_ItemsValue":"","CF_Value":"2018-10-29","CF_Required":"是","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","VIP_GID":"d3ac98dc-d5b4-47a7-847d-5804d4fff5b2","CF_CreateUser":"销售001","CF_CreateTime":"2018-10-29 15:40:27","CF_Order":5,"CF_IsShowVIP":1}]
             * MA_AvailableBalance : 0.0
             * MA_AggregateAmount : 0.0
             * MA_AggregateStoredValue : 0.0
             * MA_AvailableIntegral : 10.0
             * MCA_HowMany : 0
             * MCA_TotalCharge : 0
             * MA_UpdateTime : 2018-10-29 16:45:44
             * CouponsList : null
             * MessageVIP : null
             */

            private String GID;
            private int VIP_RegSource;
            private String EM_Name;
            private String VIP_HeadImg;
            private String VCH_Card;
            private String VIP_Name;
            private int VIP_Sex;
            private String VCH_CreateTime;
            private String VIP_Birthday;
            private String VIP_CellPhone;
            private String VIP_ICCard;
            private String VIP_Email;
            private String VIP_Remark;
            private int VIP_IsLunarCalendar;
            private int VIP_IsForver;
            private String VIP_Overdue;
            private int VIP_State;
            private String VIP_FaceNumber;
            private String VIP_Label;
            private String VG_GID;
            private String EM_ID;
            private String VIP_Referee;
            private String VIP_Addr;
            private String VIP_FixedPhone;
            private String VIP_Creator;
            private String SM_Name;
            private String SM_GID;
            private String VCH_Fee;
            private Object VIP_OpenID;
            private int VIP_InterCalaryMonth;
            private Object MCA_HowManyDetail;
            private String VG_Name;
            private Object VG_IsAccount;
            private Object VG_IsIntegral;
            private Object VG_IsDiscount;
            private Object VG_IsCount;
            private Object VG_IsTime;
            private double DS_Value;
            private double VS_Value;
            private double RS_Value;
            private Object VGInfo;
            private double MA_AvailableBalance;
            private double MA_AggregateAmount;
            private double MA_AggregateStoredValue;
            private double MA_AvailableIntegral;
            private int MCA_HowMany;
            private int MCA_TotalCharge;
            private String MA_UpdateTime;
            private Object CouponsList;
            private Object MessageVIP;

            private List<CustomeFieldListBean> CustomeFieldList;

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public int getVIP_RegSource() {
                return VIP_RegSource;
            }

            public void setVIP_RegSource(int VIP_RegSource) {
                this.VIP_RegSource = VIP_RegSource;
            }

            public String getEM_Name() {
                return EM_Name;
            }

            public void setEM_Name(String EM_Name) {
                this.EM_Name = EM_Name;
            }

            public String getVIP_HeadImg() {
                return VIP_HeadImg;
            }

            public void setVIP_HeadImg(String VIP_HeadImg) {
                this.VIP_HeadImg = VIP_HeadImg;
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

            public int getVIP_Sex() {
                return VIP_Sex;
            }

            public void setVIP_Sex(int VIP_Sex) {
                this.VIP_Sex = VIP_Sex;
            }

            public String getVCH_CreateTime() {
                return VCH_CreateTime;
            }

            public void setVCH_CreateTime(String VCH_CreateTime) {
                this.VCH_CreateTime = VCH_CreateTime;
            }

            public String getVIP_Birthday() {
                return VIP_Birthday;
            }

            public void setVIP_Birthday(String VIP_Birthday) {
                this.VIP_Birthday = VIP_Birthday;
            }

            public String getVIP_CellPhone() {
                return VIP_CellPhone;
            }

            public void setVIP_CellPhone(String VIP_CellPhone) {
                this.VIP_CellPhone = VIP_CellPhone;
            }

            public String getVIP_ICCard() {
                return VIP_ICCard;
            }

            public void setVIP_ICCard(String VIP_ICCard) {
                this.VIP_ICCard = VIP_ICCard;
            }

            public String getVIP_Email() {
                return VIP_Email;
            }

            public void setVIP_Email(String VIP_Email) {
                this.VIP_Email = VIP_Email;
            }

            public String getVIP_Remark() {
                return VIP_Remark;
            }

            public void setVIP_Remark(String VIP_Remark) {
                this.VIP_Remark = VIP_Remark;
            }

            public int getVIP_IsLunarCalendar() {
                return VIP_IsLunarCalendar;
            }

            public void setVIP_IsLunarCalendar(int VIP_IsLunarCalendar) {
                this.VIP_IsLunarCalendar = VIP_IsLunarCalendar;
            }

            public int getVIP_IsForver() {
                return VIP_IsForver;
            }

            public void setVIP_IsForver(int VIP_IsForver) {
                this.VIP_IsForver = VIP_IsForver;
            }

            public String getVIP_Overdue() {
                return VIP_Overdue;
            }

            public void setVIP_Overdue(String VIP_Overdue) {
                this.VIP_Overdue = VIP_Overdue;
            }

            public int getVIP_State() {
                return VIP_State;
            }

            public void setVIP_State(int VIP_State) {
                this.VIP_State = VIP_State;
            }

            public String getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(String VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public String getVIP_Label() {
                return VIP_Label;
            }

            public void setVIP_Label(String VIP_Label) {
                this.VIP_Label = VIP_Label;
            }

            public String getVG_GID() {
                return VG_GID;
            }

            public void setVG_GID(String VG_GID) {
                this.VG_GID = VG_GID;
            }

            public Object getEM_ID() {
                return EM_ID;
            }

            public void setEM_ID(String EM_ID) {
                this.EM_ID = EM_ID;
            }

            public String getVIP_Referee() {
                return VIP_Referee;
            }

            public void setVIP_Referee(String VIP_Referee) {
                this.VIP_Referee = VIP_Referee;
            }

            public String getVIP_Addr() {
                return VIP_Addr;
            }

            public void setVIP_Addr(String VIP_Addr) {
                this.VIP_Addr = VIP_Addr;
            }

            public String getVIP_FixedPhone() {
                return VIP_FixedPhone;
            }

            public void setVIP_FixedPhone(String VIP_FixedPhone) {
                this.VIP_FixedPhone = VIP_FixedPhone;
            }

            public String getVIP_Creator() {
                return VIP_Creator;
            }

            public void setVIP_Creator(String VIP_Creator) {
                this.VIP_Creator = VIP_Creator;
            }

            public String getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(String SM_Name) {
                this.SM_Name = SM_Name;
            }

            public String getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(String SM_GID) {
                this.SM_GID = SM_GID;
            }

            public double getVCH_Fee() {
                return TextUtils.isEmpty(VCH_Fee) ? 0 : Double.parseDouble(VCH_Fee);
            }

            public void setVCH_Fee(double VCH_Fee) {
                this.VCH_Fee = VCH_Fee + "";
            }

            public Object getVIP_OpenID() {
                return VIP_OpenID;
            }

            public void setVIP_OpenID(Object VIP_OpenID) {
                this.VIP_OpenID = VIP_OpenID;
            }

            public int getVIP_InterCalaryMonth() {
                return VIP_InterCalaryMonth;
            }

            public void setVIP_InterCalaryMonth(int VIP_InterCalaryMonth) {
                this.VIP_InterCalaryMonth = VIP_InterCalaryMonth;
            }

            public Object getMCA_HowManyDetail() {
                return MCA_HowManyDetail;
            }

            public void setMCA_HowManyDetail(Object MCA_HowManyDetail) {
                this.MCA_HowManyDetail = MCA_HowManyDetail;
            }

            public String getVG_Name() {
                return VG_Name;
            }

            public void setVG_Name(String VG_Name) {
                this.VG_Name = VG_Name;
            }

            public Object getVG_IsAccount() {
                return VG_IsAccount;
            }

            public void setVG_IsAccount(Object VG_IsAccount) {
                this.VG_IsAccount = VG_IsAccount;
            }

            public Object getVG_IsIntegral() {
                return VG_IsIntegral;
            }

            public void setVG_IsIntegral(Object VG_IsIntegral) {
                this.VG_IsIntegral = VG_IsIntegral;
            }

            public Object getVG_IsDiscount() {
                return VG_IsDiscount;
            }

            public void setVG_IsDiscount(Object VG_IsDiscount) {
                this.VG_IsDiscount = VG_IsDiscount;
            }

            public Object getVG_IsCount() {
                return VG_IsCount;
            }

            public void setVG_IsCount(Object VG_IsCount) {
                this.VG_IsCount = VG_IsCount;
            }

            public Object getVG_IsTime() {
                return VG_IsTime;
            }

            public void setVG_IsTime(Object VG_IsTime) {
                this.VG_IsTime = VG_IsTime;
            }

            public double getDS_Value() {
                return DS_Value;
            }

            public void setDS_Value(double DS_Value) {
                this.DS_Value = DS_Value;
            }

            public double getVS_Value() {
                return VS_Value;
            }

            public void setVS_Value(double VS_Value) {
                this.VS_Value = VS_Value;
            }

            public double getRS_Value() {
                return RS_Value;
            }

            public void setRS_Value(double RS_Value) {
                this.RS_Value = RS_Value;
            }

            public Object getVGInfo() {
                return VGInfo;
            }

            public void setVGInfo(Object VGInfo) {
                this.VGInfo = VGInfo;
            }

            public double getMA_AvailableBalance() {
                return MA_AvailableBalance;
            }

            public void setMA_AvailableBalance(double MA_AvailableBalance) {
                this.MA_AvailableBalance = MA_AvailableBalance;
            }

            public double getMA_AggregateAmount() {
                return MA_AggregateAmount;
            }

            public void setMA_AggregateAmount(double MA_AggregateAmount) {
                this.MA_AggregateAmount = MA_AggregateAmount;
            }

            public double getMA_AggregateStoredValue() {
                return MA_AggregateStoredValue;
            }

            public void setMA_AggregateStoredValue(double MA_AggregateStoredValue) {
                this.MA_AggregateStoredValue = MA_AggregateStoredValue;
            }

            public double getMA_AvailableIntegral() {
                return MA_AvailableIntegral;
            }

            public void setMA_AvailableIntegral(double MA_AvailableIntegral) {
                this.MA_AvailableIntegral = MA_AvailableIntegral;
            }

            public int getMCA_HowMany() {
                return MCA_HowMany;
            }

            public void setMCA_HowMany(int MCA_HowMany) {
                this.MCA_HowMany = MCA_HowMany;
            }

            public int getMCA_TotalCharge() {
                return MCA_TotalCharge;
            }

            public void setMCA_TotalCharge(int MCA_TotalCharge) {
                this.MCA_TotalCharge = MCA_TotalCharge;
            }

            public String getMA_UpdateTime() {
                return MA_UpdateTime;
            }

            public void setMA_UpdateTime(String MA_UpdateTime) {
                this.MA_UpdateTime = MA_UpdateTime;
            }

            public Object getCouponsList() {
                return CouponsList;
            }

            public void setCouponsList(Object CouponsList) {
                this.CouponsList = CouponsList;
            }

            public Object getMessageVIP() {
                return MessageVIP;
            }

            public void setMessageVIP(Object MessageVIP) {
                this.MessageVIP = MessageVIP;
            }

            public List<CustomeFieldListBean> getCustomeFieldList() {
                return CustomeFieldList;
            }

            public void setCustomeFieldList(List<CustomeFieldListBean> CustomeFieldList) {
                this.CustomeFieldList = CustomeFieldList;
            }

            public static class CustomeFieldListBean implements Serializable {
                /**
                 * CF_GID : c44c90ab-af07-43f9-9764-532d86c83039
                 * CF_FieldName : 卡号
                 * CF_FieldType : 数字
                 * CF_ItemsValue :
                 * CF_Value : 3213854
                 * CF_Required : 否
                 * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
                 * VIP_GID : d3ac98dc-d5b4-47a7-847d-5804d4fff5b2
                 * CF_CreateUser : 销售001
                 * CF_CreateTime : 2018-07-20 09:59:36
                 * CF_Order : 1
                 * CF_IsShowVIP : 1
                 */

                private String CF_GID;
                private String CF_FieldName;
                private String CF_FieldType;
                private String CF_ItemsValue;
                private String CF_Value;
                private String CF_Required;
                private String CY_GID;
                private String VIP_GID;
                private String CF_CreateUser;
                private String CF_CreateTime;
                private int CF_Order;
                private int CF_IsShowVIP;

                public String getCF_GID() {
                    return CF_GID;
                }

                public void setCF_GID(String CF_GID) {
                    this.CF_GID = CF_GID;
                }

                public String getCF_FieldName() {
                    return CF_FieldName;
                }

                public void setCF_FieldName(String CF_FieldName) {
                    this.CF_FieldName = CF_FieldName;
                }

                public String getCF_FieldType() {
                    return CF_FieldType;
                }

                public void setCF_FieldType(String CF_FieldType) {
                    this.CF_FieldType = CF_FieldType;
                }

                public String getCF_ItemsValue() {
                    return CF_ItemsValue;
                }

                public void setCF_ItemsValue(String CF_ItemsValue) {
                    this.CF_ItemsValue = CF_ItemsValue;
                }

                public String getCF_Value() {
                    return CF_Value;
                }

                public void setCF_Value(String CF_Value) {
                    this.CF_Value = CF_Value;
                }

                public String getCF_Required() {
                    return CF_Required;
                }

                public void setCF_Required(String CF_Required) {
                    this.CF_Required = CF_Required;
                }

                public String getCY_GID() {
                    return CY_GID;
                }

                public void setCY_GID(String CY_GID) {
                    this.CY_GID = CY_GID;
                }

                public String getVIP_GID() {
                    return VIP_GID;
                }

                public void setVIP_GID(String VIP_GID) {
                    this.VIP_GID = VIP_GID;
                }

                public String getCF_CreateUser() {
                    return CF_CreateUser;
                }

                public void setCF_CreateUser(String CF_CreateUser) {
                    this.CF_CreateUser = CF_CreateUser;
                }

                public String getCF_CreateTime() {
                    return CF_CreateTime;
                }

                public void setCF_CreateTime(String CF_CreateTime) {
                    this.CF_CreateTime = CF_CreateTime;
                }

                public int getCF_Order() {
                    return CF_Order;
                }

                public void setCF_Order(int CF_Order) {
                    this.CF_Order = CF_Order;
                }

                public int getCF_IsShowVIP() {
                    return CF_IsShowVIP;
                }

                public void setCF_IsShowVIP(int CF_IsShowVIP) {
                    this.CF_IsShowVIP = CF_IsShowVIP;
                }
            }
        }
    }
}
