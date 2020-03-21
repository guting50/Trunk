package com.zhiluo.android.yunpu.member.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/22.
 */
public class VipListBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":9,"PageIndex":1,"DataList":[{"GID":"55d0bca9-a890-46f5-b96b-dd280c800f1a","VG_GID":null,"VT_Code":null,"VIP_Name":"散客","VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":null,"EM_ID":"yinshangyong93@163.com","VIP_Overdue":null,"VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":null,"VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"00000","VCH_Fee":0,"VG_Name":null,"VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"2016-08-30 11:04:03","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-08-30 11:04:03","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null},{"GID":"f2c3c0ca-b8db-420c-97ea-ee621ee77981","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":"IntegerCard","VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":"","EM_ID":"请选择","VIP_Overdue":"2017-09-05 00:00:00","VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"000","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":"积分卡","VCH_Type":0,"VCH_UpdateTime":"2016-09-05 11:01:45","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-05 11:01:45","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":"123456","VIP_ICCard":"","US_Name":"积分达到"},{"GID":"f9c2b977-e086-49e7-addd-40c5f3f2d567","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":"IntegerCard","VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":"","EM_ID":"请选择","VIP_Overdue":"2017-09-07 00:00:00","VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"y123456","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":"积分卡","VCH_Type":0,"VCH_UpdateTime":"2016-09-07 13:51:17","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-07 13:51:17","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":"123456","VIP_ICCard":"","US_Name":"积分达到"},{"GID":"70a14a50-612e-4935-802f-96e89ff0054e","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":"IntegerCard","VIP_Name":"ss","VIP_CellPhone":"13545800385","VIP_Birthday":"2016-09-05 00:00:00","VIP_Email":"123456@132.com","EM_ID":"请选择","VIP_Overdue":"2017-09-07 00:00:00","VIP_IsForver":0,"VIP_State":0,"VIP_Sex":2,"VIP_FixedPhone":"123456","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"41254","VIP_Remark":"iuy8i","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"123","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":"积分卡","VCH_Type":0,"VCH_UpdateTime":"2016-09-19 09:19:30","MA_AccountBalance":902,"MA_AggregateAmount":1302,"MA_AvailableBalance":902,"MA_AvailableIntegral":10,"MA_FreezeBalance":0,"VIP_Addr":"1bgyukl","SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-19 09:19:30","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":"123456","VIP_ICCard":"411111254512145214","US_Name":"积分达到"},{"GID":"1a597da7-402b-4898-98e2-2a8bd9e784a1","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":"IntegerCard","VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":"","EM_ID":"请选择","VIP_Overdue":"2017-09-22 00:00:00","VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"8888","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":"积分卡","VCH_Type":0,"VCH_UpdateTime":"2016-09-22 09:00:12","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-22 09:00:12","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":"123456","VIP_ICCard":"","US_Name":"积分达到"},{"GID":"85454dd3-4ff8-47bc-a42d-b952ccc250df","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":null,"VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":"","EM_ID":"","VIP_Overdue":null,"VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"931206","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"2016-09-22 09:25:18","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-22 09:25:18","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":null,"VIP_ICCard":"","US_Name":"积分达到"},{"GID":"1c08fc19-8db1-4380-9958-19e5fd6bce9f","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":null,"VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":"","EM_ID":"","VIP_Overdue":null,"VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"931207","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"2016-09-22 09:25:45","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-22 09:25:45","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":null,"VIP_ICCard":"","US_Name":"积分达到"},{"GID":"7bfdf47e-6e6a-4021-9284-569ac05f5658","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":null,"VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"1993-12-05 00:00:00","VIP_Email":"","EM_ID":"","VIP_Overdue":null,"VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"931208","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"2016-09-22 09:26:22","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-22 09:26:22","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":null,"VIP_ICCard":"","US_Name":"积分达到"},{"GID":"7a0f7572-6cec-448f-b6e3-1a8d4be591a8","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":"IntegerCard","VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"2001-01-01 00:00:00","VIP_Email":"","EM_ID":"请选择","VIP_Overdue":"2017-09-22 00:00:00","VIP_IsForver":1,"VIP_State":2,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"931205","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":"积分卡","VCH_Type":0,"VCH_UpdateTime":"2016-09-22 09:36:50","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-22 09:36:50","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":null,"VIP_ICCard":"","US_Name":"积分达到"}]}
     */

    private boolean success;
    private Object code;
    private String msg;
    /**
     * PageTotal : 1
     * PageSize : 20
     * DataCount : 9
     * PageIndex : 1
     * DataList : [{"GID":"55d0bca9-a890-46f5-b96b-dd280c800f1a","VG_GID":null,"VT_Code":null,"VIP_Name":"散客","VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":null,"EM_ID":"yinshangyong93@163.com","VIP_Overdue":null,"VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":null,"VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"00000","VCH_Fee":0,"VG_Name":null,"VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"2016-08-30 11:04:03","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-08-30 11:04:03","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null},{"GID":"f2c3c0ca-b8db-420c-97ea-ee621ee77981","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":"IntegerCard","VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":"","EM_ID":"请选择","VIP_Overdue":"2017-09-05 00:00:00","VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"000","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":"积分卡","VCH_Type":0,"VCH_UpdateTime":"2016-09-05 11:01:45","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-05 11:01:45","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":"123456","VIP_ICCard":"","US_Name":"积分达到"},{"GID":"f9c2b977-e086-49e7-addd-40c5f3f2d567","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":"IntegerCard","VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":"","EM_ID":"请选择","VIP_Overdue":"2017-09-07 00:00:00","VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"y123456","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":"积分卡","VCH_Type":0,"VCH_UpdateTime":"2016-09-07 13:51:17","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-07 13:51:17","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":"123456","VIP_ICCard":"","US_Name":"积分达到"},{"GID":"70a14a50-612e-4935-802f-96e89ff0054e","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":"IntegerCard","VIP_Name":"ss","VIP_CellPhone":"13545800385","VIP_Birthday":"2016-09-05 00:00:00","VIP_Email":"123456@132.com","EM_ID":"请选择","VIP_Overdue":"2017-09-07 00:00:00","VIP_IsForver":0,"VIP_State":0,"VIP_Sex":2,"VIP_FixedPhone":"123456","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"41254","VIP_Remark":"iuy8i","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"123","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":"积分卡","VCH_Type":0,"VCH_UpdateTime":"2016-09-19 09:19:30","MA_AccountBalance":902,"MA_AggregateAmount":1302,"MA_AvailableBalance":902,"MA_AvailableIntegral":10,"MA_FreezeBalance":0,"VIP_Addr":"1bgyukl","SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-19 09:19:30","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":"123456","VIP_ICCard":"411111254512145214","US_Name":"积分达到"},{"GID":"1a597da7-402b-4898-98e2-2a8bd9e784a1","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":"IntegerCard","VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":"","EM_ID":"请选择","VIP_Overdue":"2017-09-22 00:00:00","VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"8888","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":"积分卡","VCH_Type":0,"VCH_UpdateTime":"2016-09-22 09:00:12","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-22 09:00:12","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":"123456","VIP_ICCard":"","US_Name":"积分达到"},{"GID":"85454dd3-4ff8-47bc-a42d-b952ccc250df","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":null,"VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":"","EM_ID":"","VIP_Overdue":null,"VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"931206","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"2016-09-22 09:25:18","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-22 09:25:18","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":null,"VIP_ICCard":"","US_Name":"积分达到"},{"GID":"1c08fc19-8db1-4380-9958-19e5fd6bce9f","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":null,"VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"0001-01-01 00:00:00","VIP_Email":"","EM_ID":"","VIP_Overdue":null,"VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"931207","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"2016-09-22 09:25:45","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-22 09:25:45","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":null,"VIP_ICCard":"","US_Name":"积分达到"},{"GID":"7bfdf47e-6e6a-4021-9284-569ac05f5658","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":null,"VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"1993-12-05 00:00:00","VIP_Email":"","EM_ID":"","VIP_Overdue":null,"VIP_IsForver":1,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"931208","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"2016-09-22 09:26:22","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-22 09:26:22","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":null,"VIP_ICCard":"","US_Name":"积分达到"},{"GID":"7a0f7572-6cec-448f-b6e3-1a8d4be591a8","VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","VT_Code":"IntegerCard","VIP_Name":null,"VIP_CellPhone":null,"VIP_Birthday":"2001-01-01 00:00:00","VIP_Email":"","EM_ID":"请选择","VIP_Overdue":"2017-09-22 00:00:00","VIP_IsForver":1,"VIP_State":2,"VIP_Sex":0,"VIP_FixedPhone":"","VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"","VIP_FaceNumber":"","VIP_Remark":"","CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"931205","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":"0","VT_Name":"积分卡","VCH_Type":0,"VCH_UpdateTime":"2016-09-22 09:36:50","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":0,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"SM_Name":"尹尚勇的店铺","VCH_CreateTime":"2016-09-22 09:36:50","VIP_Creator":"yinshangyong93@163.com","VCH_Pwd":null,"VIP_ICCard":"","US_Name":"积分达到"}]
     */

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
        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        /**
         * GID : 55d0bca9-a890-46f5-b96b-dd280c800f1a
         * VG_GID : null
         * VT_Code : null
         * VIP_Name : 散客
         * VIP_CellPhone : null
         * VIP_Birthday : 0001-01-01 00:00:00
         * VIP_Email : null
         * EM_ID : yinshangyong93@163.com
         * VIP_Overdue : null
         * VIP_IsForver : 1
         * VIP_State : 0
         * VIP_Sex : 0
         * VIP_FixedPhone : null
         * VIP_UpdateTime : 0001-01-01 00:00:00
         * VIP_Referee : null
         * VIP_FaceNumber : null
         * VIP_Remark : null
         * CY_GID : null
         * VIP_HeadPicture : null
         * SM_ID : null
         * VIP_IsDeleted : 0
         * VIP_Percent : 0
         * VCH_Card : 00000
         * VCH_Fee : 0
         * VG_Name : null
         * VGC_Code : null
         * US_Value : null
         * VT_Name : null
         * VCH_Type : 0
         * VCH_UpdateTime : 2016-08-30 11:04:03
         * MA_AccountBalance : 0
         * MA_AggregateAmount : 0
         * MA_AvailableBalance : 0
         * MA_AvailableIntegral : 0
         * MA_FreezeBalance : 0
         * VIP_Addr : null
         * SM_Name : 尹尚勇的店铺
         * VCH_CreateTime : 2016-08-30 11:04:03
         * VIP_Creator : yinshangyong93@163.com
         * VCH_Pwd : null
         * VIP_ICCard : null
         * US_Name : null
         */

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

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class DataListBean implements Serializable {
            private String GID;
            private boolean IS_CHECK;
            private Object VG_GID;
            private Object VT_Code;
            private String VIP_Name;
            private Object VIP_CellPhone;
            private String VIP_Birthday;
            private Object VIP_Email;
            private String EM_ID;
            private Object VIP_Overdue;
            private int VIP_IsForver;
            private String VIP_State;
            private String VIP_Sex;
            private Object VIP_FixedPhone;
            private String VIP_UpdateTime;
            private Object VIP_Referee;
            private Object VIP_FaceNumber;
            private Object VIP_Remark;
            private Object CY_GID;
            private Object VIP_HeadPicture;
            private Object SM_ID;
            private int VIP_IsDeleted;
            private int VIP_Percent;
            private String VCH_Card;
            private int VCH_Fee;
            private Object VG_Name;
            private Object VGC_Code;
            private Object US_Value;
            private Object VT_Name;
            private int VCH_Type;
            private String VCH_UpdateTime;
            private String MA_AccountBalance;
            private String MA_AggregateAmount;
            private String MA_AvailableBalance;
            private String MA_AvailableIntegral;
            private String MA_FreezeBalance;
            private Object VIP_Addr;
            private String VIP_HeadImg;
            private String SM_Name;
            private String VCH_CreateTime;
            private String VIP_Creator;
            private Object VCH_Pwd;
            private Object VIP_ICCard;
            private Object US_Name;

            public boolean IS_CHECK() {
                return IS_CHECK;
            }

            public void setIS_CHECK(boolean IS_CHECK) {
                this.IS_CHECK = IS_CHECK;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public Object getVG_GID() {
                return VG_GID;
            }

            public void setVG_GID(Object VG_GID) {
                this.VG_GID = VG_GID;
            }

            public Object getVT_Code() {
                return VT_Code;
            }

            public void setVT_Code(Object VT_Code) {
                this.VT_Code = VT_Code;
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

            public String getVIP_Birthday() {
                return VIP_Birthday;
            }

            public void setVIP_Birthday(String VIP_Birthday) {
                this.VIP_Birthday = VIP_Birthday;
            }

            public Object getVIP_Email() {
                return VIP_Email;
            }

            public void setVIP_Email(Object VIP_Email) {
                this.VIP_Email = VIP_Email;
            }

            public String getEM_ID() {
                return EM_ID;
            }

            public void setEM_ID(String EM_ID) {
                this.EM_ID = EM_ID;
            }

            public Object getVIP_Overdue() {
                return VIP_Overdue;
            }

            public void setVIP_Overdue(Object VIP_Overdue) {
                this.VIP_Overdue = VIP_Overdue;
            }

            public int getVIP_IsForver() {
                return VIP_IsForver;
            }

            public void setVIP_IsForver(int VIP_IsForver) {
                this.VIP_IsForver = VIP_IsForver;
            }

            public String getVIP_State() {
                return VIP_State;
            }

            public void setVIP_State(String VIP_State) {
                this.VIP_State = VIP_State;
            }

            public String getVIP_Sex() {
                return VIP_Sex;
            }

            public void setVIP_Sex(String VIP_Sex) {
                this.VIP_Sex = VIP_Sex;
            }

            public Object getVIP_FixedPhone() {
                return VIP_FixedPhone;
            }

            public void setVIP_FixedPhone(Object VIP_FixedPhone) {
                this.VIP_FixedPhone = VIP_FixedPhone;
            }

            public String getVIP_UpdateTime() {
                return VIP_UpdateTime;
            }

            public void setVIP_UpdateTime(String VIP_UpdateTime) {
                this.VIP_UpdateTime = VIP_UpdateTime;
            }

            public Object getVIP_Referee() {
                return VIP_Referee;
            }

            public void setVIP_Referee(Object VIP_Referee) {
                this.VIP_Referee = VIP_Referee;
            }

            public Object getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(Object VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public Object getVIP_Remark() {
                return VIP_Remark;
            }

            public void setVIP_Remark(Object VIP_Remark) {
                this.VIP_Remark = VIP_Remark;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }

            public Object getVIP_HeadPicture() {
                return VIP_HeadPicture;
            }

            public void setVIP_HeadPicture(Object VIP_HeadPicture) {
                this.VIP_HeadPicture = VIP_HeadPicture;
            }

            public Object getSM_ID() {
                return SM_ID;
            }

            public void setSM_ID(Object SM_ID) {
                this.SM_ID = SM_ID;
            }

            public int getVIP_IsDeleted() {
                return VIP_IsDeleted;
            }

            public void setVIP_IsDeleted(int VIP_IsDeleted) {
                this.VIP_IsDeleted = VIP_IsDeleted;
            }

            public int getVIP_Percent() {
                return VIP_Percent;
            }

            public void setVIP_Percent(int VIP_Percent) {
                this.VIP_Percent = VIP_Percent;
            }

            public String getVCH_Card() {
                return VCH_Card;
            }

            public void setVCH_Card(String VCH_Card) {
                this.VCH_Card = VCH_Card;
            }

            public int getVCH_Fee() {
                return VCH_Fee;
            }

            public void setVCH_Fee(int VCH_Fee) {
                this.VCH_Fee = VCH_Fee;
            }

            public Object getVG_Name() {
                return VG_Name;
            }

            public void setVG_Name(Object VG_Name) {
                this.VG_Name = VG_Name;
            }

            public Object getVGC_Code() {
                return VGC_Code;
            }

            public void setVGC_Code(Object VGC_Code) {
                this.VGC_Code = VGC_Code;
            }

            public Object getUS_Value() {
                return US_Value;
            }

            public void setUS_Value(Object US_Value) {
                this.US_Value = US_Value;
            }

            public Object getVT_Name() {
                return VT_Name;
            }

            public void setVT_Name(Object VT_Name) {
                this.VT_Name = VT_Name;
            }

            public int getVCH_Type() {
                return VCH_Type;
            }

            public void setVCH_Type(int VCH_Type) {
                this.VCH_Type = VCH_Type;
            }

            public String getVCH_UpdateTime() {
                return VCH_UpdateTime;
            }

            public void setVCH_UpdateTime(String VCH_UpdateTime) {
                this.VCH_UpdateTime = VCH_UpdateTime;
            }

            public String getMA_AccountBalance() {
                return MA_AccountBalance;
            }

            public void setMA_AccountBalance(String MA_AccountBalance) {
                this.MA_AccountBalance = MA_AccountBalance;
            }

            public String getMA_AggregateAmount() {
                return MA_AggregateAmount;
            }

            public void setMA_AggregateAmount(String MA_AggregateAmount) {
                this.MA_AggregateAmount = MA_AggregateAmount;
            }

            public String getMA_AvailableBalance() {
                return MA_AvailableBalance;
            }

            public void setMA_AvailableBalance(String MA_AvailableBalance) {
                this.MA_AvailableBalance = MA_AvailableBalance;
            }

            public String getMA_AvailableIntegral() {
                return MA_AvailableIntegral;
            }

            public void setMA_AvailableIntegral(String MA_AvailableIntegral) {
                this.MA_AvailableIntegral = MA_AvailableIntegral;
            }

            public String getMA_FreezeBalance() {
                return MA_FreezeBalance;
            }

            public void setMA_FreezeBalance(String MA_FreezeBalance) {
                this.MA_FreezeBalance = MA_FreezeBalance;
            }

            public Object getVIP_Addr() {
                return VIP_Addr;
            }

            public void setVIP_Addr(Object VIP_Addr) {
                this.VIP_Addr = VIP_Addr;
            }

            public String getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(String SM_Name) {
                this.SM_Name = SM_Name;
            }

            public String getVCH_CreateTime() {
                return VCH_CreateTime;
            }

            public void setVCH_CreateTime(String VCH_CreateTime) {
                this.VCH_CreateTime = VCH_CreateTime;
            }

            public String getVIP_Creator() {
                return VIP_Creator;
            }

            public void setVIP_Creator(String VIP_Creator) {
                this.VIP_Creator = VIP_Creator;
            }

            public Object getVCH_Pwd() {
                return VCH_Pwd;
            }

            public void setVCH_Pwd(Object VCH_Pwd) {
                this.VCH_Pwd = VCH_Pwd;
            }

            public Object getVIP_ICCard() {
                return VIP_ICCard;
            }

            public void setVIP_ICCard(Object VIP_ICCard) {
                this.VIP_ICCard = VIP_ICCard;
            }

            public Object getUS_Name() {
                return US_Name;
            }

            public void setUS_Name(Object US_Name) {
                this.US_Name = US_Name;
            }

            public String getVIP_HeadImg() {
                return VIP_HeadImg;
            }

            public void setVIP_HeadImg(String VIP_HeadImg) {
                this.VIP_HeadImg = VIP_HeadImg;
            }
        }
    }
}
