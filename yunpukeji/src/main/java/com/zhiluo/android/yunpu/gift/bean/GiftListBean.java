package com.zhiluo.android.yunpu.gift.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/10/20.
 */
public class GiftListBean
{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":3,"PageIndex":1,"DataList":[{"GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","GT_GID":"9f678590-4aa9-464e-b4cc-2189d817d099",
     * "GM_Code":"123","GM_Name":"zaz","GM_Integral":100,"GM_Acount":null,"GM_EndTime":null,"GM_IsForver":1,"GM_Remark":"","GM_PictureUrl":null,"GM_Creator":"yinshangyong93@163
     * .com","GM_UpdateTime":"2016-10-20 11:22:04","CY_GID":null,"GM_SynType":0,"VGList":[{"GID":"11a2dd60-294d-411b-8f9b-700dbe55c13d",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"084f75fb-6c17-42da-aa45-dfda4b4c0775","CY_GID":null,"VG_Name":"yinaa"},{"GID":"1718e6dc-7781-4b69-8b10-a1425808ab07",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"a1c54139-c1ca-40a6-b2b6-6c6de33a3736","CY_GID":null,"VG_Name":"as"},{"GID":"23c8dd40-0c40-4b74-a8de-21473b7b3751",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"3ec8cfe3-8c69-4dc0-9373-99b5a85286bf","CY_GID":null,"VG_Name":"引进"},{"GID":"4cf98c53-ae6b-417b-9ecf-d7c48a0ba55e",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"2b547d2d-5f46-4f0a-9136-71b00ce1696e","CY_GID":null,"VG_Name":"zzx"},{"GID":"6f7558e2-c2db-4678-811b-00978b9e8ed6",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"106b9580-6b2d-4621-91b6-2ffee5ef1f6c","CY_GID":null,"VG_Name":"物理"},{"GID":"77d2a53c-ebb6-4f86-8fb7-cfbad5da35aa",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"49272d4b-8a5e-421d-be37-e16840ec7dba","CY_GID":null,"VG_Name":"尹尚勇"},{"GID":"78ff2c8c-e525-471b-9bdc-806ff53affb3",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","CY_GID":null,"VG_Name":"黄金会员"},{"GID":"9883b839-dc8b-4ad1-889a-3aa071404ac2",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","CY_GID":null,"VG_Name":"默认等级"},{"GID":"ba7d92e1-2658-4539-bae1-c15649289e2d",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"da91f3f1-79f1-446d-8af8-f844ab768fa7","CY_GID":null,"VG_Name":"zca"}],"GT_Name":"ssss","GM_Amount":12},
     * {"GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","GT_GID":"9f678590-4aa9-464e-b4cc-2189d817d099","GM_Code":null,"GM_Name":"ssssss","GM_Integral":1000,"GM_Acount":null,
     * "GM_EndTime":null,"GM_IsForver":1,"GM_Remark":"","GM_PictureUrl":null,"GM_Creator":"yinshangyong93@163.com","GM_UpdateTime":"2016-10-17 16:34:16","CY_GID":null,
     * "GM_SynType":0,"VGList":[{"GID":"2062d3ec-c739-455f-9112-b566ebdf3497","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","CY_GID":null,
     * "VG_Name":"默认等级"},{"GID":"23d99156-21aa-4533-9454-53c198394ba4","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"3ec8cfe3-8c69-4dc0-9373-99b5a85286bf","CY_GID":null,
     * "VG_Name":"引进"},{"GID":"72402781-5816-4cf3-a938-5f47ec7d27cb","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"49272d4b-8a5e-421d-be37-e16840ec7dba","CY_GID":null,
     * "VG_Name":"尹尚勇"},{"GID":"8cb25951-e6b0-43a3-8f27-c6732aaf8e75","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"084f75fb-6c17-42da-aa45-dfda4b4c0775","CY_GID":null,
     * "VG_Name":"yinaa"},{"GID":"9ceea2ca-ebc9-4d6a-a842-5f1481be2d7e","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"106b9580-6b2d-4621-91b6-2ffee5ef1f6c","CY_GID":null,
     * "VG_Name":"物理"},{"GID":"a3ba39ec-0a5a-43b7-b23b-ae931855263c","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"da91f3f1-79f1-446d-8af8-f844ab768fa7","CY_GID":null,
     * "VG_Name":"zca"},{"GID":"a6279a94-ab2e-48fb-a6d5-267be6d1c814","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","CY_GID":null,
     * "VG_Name":"黄金会员"},{"GID":"cb6c2510-077a-4725-8969-2cd94ee5cfa5","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"a1c54139-c1ca-40a6-b2b6-6c6de33a3736","CY_GID":null,
     * "VG_Name":"as"}],"GT_Name":"ssss","GM_Amount":23},{"GID":"78564678-e1ed-480b-9ffb-5ab49b4eb3bb","GT_GID":"9f678590-4aa9-464e-b4cc-2189d817d099","GM_Code":"123",
     * "GM_Name":"aaa","GM_Integral":100,"GM_Acount":null,"GM_EndTime":null,"GM_IsForver":1,"GM_Remark":"","GM_PictureUrl":"/FileUpload/131203727118514835.png",
     * "GM_Creator":"yinshangyong93@163.com","GM_UpdateTime":"2016-10-08 11:58:32","CY_GID":null,"GM_SynType":0,"VGList":[{"GID":"897d10e7-aad8-4fbf-ba6e-0a8aa4fa8bf5",
     * "GM_GID":"78564678-e1ed-480b-9ffb-5ab49b4eb3bb","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-08
     * 11:58:32","GV_Remark":null,"VG_GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","CY_GID":null,"VG_Name":"黄金会员"}],"GT_Name":"ssss","GM_Amount":4}]}
     */

    private boolean success;
    private Object code;
    private String msg;
    /**
     * PageTotal : 1
     * PageSize : 20
     * DataCount : 3
     * PageIndex : 1
     * DataList : [{"GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","GT_GID":"9f678590-4aa9-464e-b4cc-2189d817d099","GM_Code":"123","GM_Name":"zaz","GM_Integral":100,
     * "GM_Acount":null,"GM_EndTime":null,"GM_IsForver":1,"GM_Remark":"","GM_PictureUrl":null,"GM_Creator":"yinshangyong93@163.com","GM_UpdateTime":"2016-10-20 11:22:04",
     * "CY_GID":null,"GM_SynType":0,"VGList":[{"GID":"11a2dd60-294d-411b-8f9b-700dbe55c13d","GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48",
     * "SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20 11:22:04","GV_Remark":null,
     * "VG_GID":"084f75fb-6c17-42da-aa45-dfda4b4c0775","CY_GID":null,"VG_Name":"yinaa"},{"GID":"1718e6dc-7781-4b69-8b10-a1425808ab07",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"a1c54139-c1ca-40a6-b2b6-6c6de33a3736","CY_GID":null,"VG_Name":"as"},{"GID":"23c8dd40-0c40-4b74-a8de-21473b7b3751",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"3ec8cfe3-8c69-4dc0-9373-99b5a85286bf","CY_GID":null,"VG_Name":"引进"},{"GID":"4cf98c53-ae6b-417b-9ecf-d7c48a0ba55e",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"2b547d2d-5f46-4f0a-9136-71b00ce1696e","CY_GID":null,"VG_Name":"zzx"},{"GID":"6f7558e2-c2db-4678-811b-00978b9e8ed6",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"106b9580-6b2d-4621-91b6-2ffee5ef1f6c","CY_GID":null,"VG_Name":"物理"},{"GID":"77d2a53c-ebb6-4f86-8fb7-cfbad5da35aa",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"49272d4b-8a5e-421d-be37-e16840ec7dba","CY_GID":null,"VG_Name":"尹尚勇"},{"GID":"78ff2c8c-e525-471b-9bdc-806ff53affb3",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","CY_GID":null,"VG_Name":"黄金会员"},{"GID":"9883b839-dc8b-4ad1-889a-3aa071404ac2",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","CY_GID":null,"VG_Name":"默认等级"},{"GID":"ba7d92e1-2658-4539-bae1-c15649289e2d",
     * "GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20
     * 11:22:04","GV_Remark":null,"VG_GID":"da91f3f1-79f1-446d-8af8-f844ab768fa7","CY_GID":null,"VG_Name":"zca"}],"GT_Name":"ssss","GM_Amount":12},
     * {"GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","GT_GID":"9f678590-4aa9-464e-b4cc-2189d817d099","GM_Code":null,"GM_Name":"ssssss","GM_Integral":1000,"GM_Acount":null,
     * "GM_EndTime":null,"GM_IsForver":1,"GM_Remark":"","GM_PictureUrl":null,"GM_Creator":"yinshangyong93@163.com","GM_UpdateTime":"2016-10-17 16:34:16","CY_GID":null,
     * "GM_SynType":0,"VGList":[{"GID":"2062d3ec-c739-455f-9112-b566ebdf3497","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","CY_GID":null,
     * "VG_Name":"默认等级"},{"GID":"23d99156-21aa-4533-9454-53c198394ba4","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"3ec8cfe3-8c69-4dc0-9373-99b5a85286bf","CY_GID":null,
     * "VG_Name":"引进"},{"GID":"72402781-5816-4cf3-a938-5f47ec7d27cb","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"49272d4b-8a5e-421d-be37-e16840ec7dba","CY_GID":null,
     * "VG_Name":"尹尚勇"},{"GID":"8cb25951-e6b0-43a3-8f27-c6732aaf8e75","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"084f75fb-6c17-42da-aa45-dfda4b4c0775","CY_GID":null,
     * "VG_Name":"yinaa"},{"GID":"9ceea2ca-ebc9-4d6a-a842-5f1481be2d7e","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"106b9580-6b2d-4621-91b6-2ffee5ef1f6c","CY_GID":null,
     * "VG_Name":"物理"},{"GID":"a3ba39ec-0a5a-43b7-b23b-ae931855263c","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"da91f3f1-79f1-446d-8af8-f844ab768fa7","CY_GID":null,
     * "VG_Name":"zca"},{"GID":"a6279a94-ab2e-48fb-a6d5-267be6d1c814","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","CY_GID":null,
     * "VG_Name":"黄金会员"},{"GID":"cb6c2510-077a-4725-8969-2cd94ee5cfa5","GM_GID":"08567c9f-42f5-4947-b0cd-5ebc28c11ad0","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
     * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-17 16:34:16","GV_Remark":null,"VG_GID":"a1c54139-c1ca-40a6-b2b6-6c6de33a3736","CY_GID":null,
     * "VG_Name":"as"}],"GT_Name":"ssss","GM_Amount":23},{"GID":"78564678-e1ed-480b-9ffb-5ab49b4eb3bb","GT_GID":"9f678590-4aa9-464e-b4cc-2189d817d099","GM_Code":"123",
     * "GM_Name":"aaa","GM_Integral":100,"GM_Acount":null,"GM_EndTime":null,"GM_IsForver":1,"GM_Remark":"","GM_PictureUrl":"/FileUpload/131203727118514835.png",
     * "GM_Creator":"yinshangyong93@163.com","GM_UpdateTime":"2016-10-08 11:58:32","CY_GID":null,"GM_SynType":0,"VGList":[{"GID":"897d10e7-aad8-4fbf-ba6e-0a8aa4fa8bf5",
     * "GM_GID":"78564678-e1ed-480b-9ffb-5ab49b4eb3bb","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6","GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-08
     * 11:58:32","GV_Remark":null,"VG_GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","CY_GID":null,"VG_Name":"黄金会员"}],"GT_Name":"ssss","GM_Amount":4}]
     */

    private DataBean data;

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public Object getCode()
    {
        return code;
    }

    public void setCode(Object code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public DataBean getData()
    {
        return data;
    }

    public void setData(DataBean data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        /**
         * GID : 5d46edc8-11fc-4501-9c15-f3ec0474ce48
         * GT_GID : 9f678590-4aa9-464e-b4cc-2189d817d099
         * GM_Code : 123
         * GM_Name : zaz
         * GM_Integral : 100
         * GM_Acount : null
         * GM_EndTime : null
         * GM_IsForver : 1
         * GM_Remark :
         * GM_PictureUrl : null
         * GM_Creator : yinshangyong93@163.com
         * GM_UpdateTime : 2016-10-20 11:22:04
         * CY_GID : null
         * GM_SynType : 0
         * VGList : [{"GID":"11a2dd60-294d-411b-8f9b-700dbe55c13d","GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
         * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20 11:22:04","GV_Remark":null,"VG_GID":"084f75fb-6c17-42da-aa45-dfda4b4c0775","CY_GID":null,
         * "VG_Name":"yinaa"},{"GID":"1718e6dc-7781-4b69-8b10-a1425808ab07","GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
         * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20 11:22:04","GV_Remark":null,"VG_GID":"a1c54139-c1ca-40a6-b2b6-6c6de33a3736","CY_GID":null,
         * "VG_Name":"as"},{"GID":"23c8dd40-0c40-4b74-a8de-21473b7b3751","GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
         * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20 11:22:04","GV_Remark":null,"VG_GID":"3ec8cfe3-8c69-4dc0-9373-99b5a85286bf","CY_GID":null,
         * "VG_Name":"引进"},{"GID":"4cf98c53-ae6b-417b-9ecf-d7c48a0ba55e","GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
         * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20 11:22:04","GV_Remark":null,"VG_GID":"2b547d2d-5f46-4f0a-9136-71b00ce1696e","CY_GID":null,
         * "VG_Name":"zzx"},{"GID":"6f7558e2-c2db-4678-811b-00978b9e8ed6","GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
         * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20 11:22:04","GV_Remark":null,"VG_GID":"106b9580-6b2d-4621-91b6-2ffee5ef1f6c","CY_GID":null,
         * "VG_Name":"物理"},{"GID":"77d2a53c-ebb6-4f86-8fb7-cfbad5da35aa","GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
         * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20 11:22:04","GV_Remark":null,"VG_GID":"49272d4b-8a5e-421d-be37-e16840ec7dba","CY_GID":null,
         * "VG_Name":"尹尚勇"},{"GID":"78ff2c8c-e525-471b-9bdc-806ff53affb3","GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
         * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20 11:22:04","GV_Remark":null,"VG_GID":"28ed6e06-ec4d-4c05-9db4-f0b32bf9f3d5","CY_GID":null,
         * "VG_Name":"黄金会员"},{"GID":"9883b839-dc8b-4ad1-889a-3aa071404ac2","GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
         * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20 11:22:04","GV_Remark":null,"VG_GID":"43b0789e-1026-450f-8f57-9222efb03bce","CY_GID":null,
         * "VG_Name":"默认等级"},{"GID":"ba7d92e1-2658-4539-bae1-c15649289e2d","GM_GID":"5d46edc8-11fc-4501-9c15-f3ec0474ce48","SM_GID":"a07d8272-4c2e-4590-aca6-e92abc46e1a6",
         * "GV_Creator":"yinshangyong93@163.com","GV_CreateTime":"2016-10-20 11:22:04","GV_Remark":null,"VG_GID":"da91f3f1-79f1-446d-8af8-f844ab768fa7","CY_GID":null,
         * "VG_Name":"zca"}]
         * GT_Name : ssss
         * GM_Amount : 12
         */

        private List<DataListBean> DataList;

        public int getPageTotal()
        {
            return PageTotal;
        }

        public void setPageTotal(int PageTotal)
        {
            this.PageTotal = PageTotal;
        }

        public int getPageSize()
        {
            return PageSize;
        }

        public void setPageSize(int PageSize)
        {
            this.PageSize = PageSize;
        }

        public int getDataCount()
        {
            return DataCount;
        }

        public void setDataCount(int DataCount)
        {
            this.DataCount = DataCount;
        }

        public int getPageIndex()
        {
            return PageIndex;
        }

        public void setPageIndex(int PageIndex)
        {
            this.PageIndex = PageIndex;
        }

        public List<DataListBean> getDataList()
        {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList)
        {
            this.DataList = DataList;
        }

        public static class DataListBean implements Serializable
        {
            private String GID;
            private String GT_GID;
            private String GM_Code;
            private String GM_Name;
            private int GM_Integral;
            private Object GM_Acount;
            private String GM_EndTime;
            private int GM_IsForver;
            private String GM_Remark;
            private String GM_PictureUrl;
            private String GM_Creator;
            private String GM_UpdateTime;
            private Object CY_GID;
            private int GM_SynType;
            private String GT_Name;
            private int GM_Amount;
            private int GM_Num;//礼品数量

            public int getGM_Num() {
                return GM_Num;
            }

            public void setGM_Num(int GM_Num) {
                this.GM_Num = GM_Num;
            }

            /**
             * GID : 11a2dd60-294d-411b-8f9b-700dbe55c13d
             * GM_GID : 5d46edc8-11fc-4501-9c15-f3ec0474ce48
             * SM_GID : a07d8272-4c2e-4590-aca6-e92abc46e1a6
             * GV_Creator : yinshangyong93@163.com
             * GV_CreateTime : 2016-10-20 11:22:04
             * GV_Remark : null
             * VG_GID : 084f75fb-6c17-42da-aa45-dfda4b4c0775
             * CY_GID : null
             * VG_Name : yinaa
             */

            private List<VGListBean> VGList;

            public String getGID()
            {
                return GID;
            }

            public void setGID(String GID)
            {
                this.GID = GID;
            }

            public String getGT_GID()
            {
                return GT_GID;
            }

            public void setGT_GID(String GT_GID)
            {
                this.GT_GID = GT_GID;
            }

            public String getGM_Code()
            {
                return GM_Code;
            }

            public void setGM_Code(String GM_Code)
            {
                this.GM_Code = GM_Code;
            }

            public String getGM_Name()
            {
                return GM_Name;
            }

            public void setGM_Name(String GM_Name)
            {
                this.GM_Name = GM_Name;
            }

            public int getGM_Integral()
            {
                return GM_Integral;
            }

            public void setGM_Integral(int GM_Integral)
            {
                this.GM_Integral = GM_Integral;
            }

            public Object getGM_Acount()
            {
                return GM_Acount;
            }

            public void setGM_Acount(Object GM_Acount)
            {
                this.GM_Acount = GM_Acount;
            }

            public String getGM_EndTime()
            {
                return GM_EndTime;
            }

            public void setGM_EndTime(String GM_EndTime)
            {
                this.GM_EndTime = GM_EndTime;
            }

            public int getGM_IsForver()
            {
                return GM_IsForver;
            }

            public void setGM_IsForver(int GM_IsForver)
            {
                this.GM_IsForver = GM_IsForver;
            }

            public String getGM_Remark()
            {
                return GM_Remark;
            }

            public void setGM_Remark(String GM_Remark)
            {
                this.GM_Remark = GM_Remark;
            }

            public String getGM_PictureUrl()
            {
                return GM_PictureUrl;
            }

            public void setGM_PictureUrl(String GM_PictureUrl)
            {
                this.GM_PictureUrl = GM_PictureUrl;
            }

            public String getGM_Creator()
            {
                return GM_Creator;
            }

            public void setGM_Creator(String GM_Creator)
            {
                this.GM_Creator = GM_Creator;
            }

            public String getGM_UpdateTime()
            {
                return GM_UpdateTime;
            }

            public void setGM_UpdateTime(String GM_UpdateTime)
            {
                this.GM_UpdateTime = GM_UpdateTime;
            }

            public Object getCY_GID()
            {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID)
            {
                this.CY_GID = CY_GID;
            }

            public int getGM_SynType()
            {
                return GM_SynType;
            }

            public void setGM_SynType(int GM_SynType)
            {
                this.GM_SynType = GM_SynType;
            }

            public String getGT_Name()
            {
                return GT_Name;
            }

            public void setGT_Name(String GT_Name)
            {
                this.GT_Name = GT_Name;
            }

            public int getGM_Amount()
            {
                return GM_Amount;
            }

            public void setGM_Amount(int GM_Amount)
            {
                this.GM_Amount = GM_Amount;
            }

            public List<VGListBean> getVGList()
            {
                return VGList;
            }

            public void setVGList(List<VGListBean> VGList)
            {
                this.VGList = VGList;
            }

            public static class VGListBean implements Serializable
            {
                private String GID;
                private String GM_GID;
                private String SM_GID;
                private String GV_Creator;
                private String GV_CreateTime;
                private Object GV_Remark;
                private String VG_GID;
                private Object CY_GID;
                private String VG_Name;

                public String getGID()
                {
                    return GID;
                }

                public void setGID(String GID)
                {
                    this.GID = GID;
                }

                public String getGM_GID()
                {
                    return GM_GID;
                }

                public void setGM_GID(String GM_GID)
                {
                    this.GM_GID = GM_GID;
                }

                public String getSM_GID()
                {
                    return SM_GID;
                }

                public void setSM_GID(String SM_GID)
                {
                    this.SM_GID = SM_GID;
                }

                public String getGV_Creator()
                {
                    return GV_Creator;
                }

                public void setGV_Creator(String GV_Creator)
                {
                    this.GV_Creator = GV_Creator;
                }

                public String getGV_CreateTime()
                {
                    return GV_CreateTime;
                }

                public void setGV_CreateTime(String GV_CreateTime)
                {
                    this.GV_CreateTime = GV_CreateTime;
                }

                public Object getGV_Remark()
                {
                    return GV_Remark;
                }

                public void setGV_Remark(Object GV_Remark)
                {
                    this.GV_Remark = GV_Remark;
                }

                public String getVG_GID()
                {
                    return VG_GID;
                }

                public void setVG_GID(String VG_GID)
                {
                    this.VG_GID = VG_GID;
                }

                public Object getCY_GID()
                {
                    return CY_GID;
                }

                public void setCY_GID(Object CY_GID)
                {
                    this.CY_GID = CY_GID;
                }

                public String getVG_Name()
                {
                    return VG_Name;
                }

                public void setVG_Name(String VG_Name)
                {
                    this.VG_Name = VG_Name;
                }
            }
        }
    }
}
