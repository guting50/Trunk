package com.zhiluo.android.yunpu.statistics.rebate.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${YSL} on 2018-06-02.
 * 礼物积分兑换Bean
 */

public class IntegralNewBean implements Serializable {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":2,"PageSize":20,"DataCount":21,"PageIndex":1,"DataList":[{"GID":"fcd9a2e7-e765-4880-99c2-77a27f9234e7","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804251109076120","EGD_DateTime":"2018-04-25 11:11:35","IEG_Number":10,"IEG_Score":860,"IEG_RemainingPoints":500148,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-25 11:11:35","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"9e7ab860-fe98-4fa7-a9eb-d13d4f660c4c","IEG_GID":"fcd9a2e7-e765-4880-99c2-77a27f9234e7","EGD_DateTime":"2018-04-25 11:11:35","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":10,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":860,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-25 11:11:35","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"4d076465-cbff-4aba-aff2-aa629788c01d","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241843474509","EGD_DateTime":"2018-04-24 18:44:47","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501008,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 18:44:47","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"39f3897e-fd20-4c16-bd45-a2bdece7e7e4","IEG_GID":"4d076465-cbff-4aba-aff2-aa629788c01d","EGD_DateTime":"2018-04-24 18:44:47","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 18:44:47","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"4e49f183-f985-425e-8cf5-f38c7e46a93e","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241743096961","EGD_DateTime":"2018-04-24 17:44:00","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501094,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 17:44:00","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"9424d146-c719-40f9-aeec-850959834cfb","IEG_GID":"4e49f183-f985-425e-8cf5-f38c7e46a93e","EGD_DateTime":"2018-04-24 17:44:00","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:44:00","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"3209a5bc-8c08-46b3-8c5e-c2501a20138f","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241739187250","EGD_DateTime":"2018-04-24 17:39:56","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501180,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 17:39:56","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"011391b1-ae5f-4469-85df-4e00b93ee5c8","IEG_GID":"3209a5bc-8c08-46b3-8c5e-c2501a20138f","EGD_DateTime":"2018-04-24 17:39:56","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:39:56","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"bf3ae39d-5576-4ca0-be2b-cac6223df058","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241733077909","EGD_DateTime":"2018-04-24 17:33:49","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501266,"IEG_Remark":"","IEG_UpdateTime":"2018-04-24 17:33:49","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"a55dfe03-f1fc-473a-b317-523dcf0f2965","IEG_GID":"bf3ae39d-5576-4ca0-be2b-cac6223df058","EGD_DateTime":"2018-04-24 17:33:49","GT_Name":"，，","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:33:49","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"f984af7f-a6a3-4cf9-9eaa-6116c82f14f7","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241727213790","EGD_DateTime":"2018-04-24 17:28:06","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":24211,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 17:28:06","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"e6aec17a-c9ea-4c01-b518-5ea1ea0c5233","IEG_GID":"f984af7f-a6a3-4cf9-9eaa-6116c82f14f7","EGD_DateTime":"2018-04-24 17:28:06","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:28:06","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"03845918-6748-49f1-aefa-5a9c7a296f05","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241726444662","EGD_DateTime":"2018-04-24 17:27:36","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501352,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 17:27:36","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"e497d093-0c2d-4a2e-b1b6-46accac6e071","IEG_GID":"03845918-6748-49f1-aefa-5a9c7a296f05","EGD_DateTime":"2018-04-24 17:27:36","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:27:36","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"3f45b33e-6cf4-46d2-a691-fc208797cc2c","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241725195549","EGD_DateTime":"2018-04-24 17:25:58","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501438,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 17:25:58","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"b713c9ce-9a6c-4345-b226-5f61bd8c0d08","IEG_GID":"3f45b33e-6cf4-46d2-a691-fc208797cc2c","EGD_DateTime":"2018-04-24 17:25:58","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:25:58","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"101414e5-70f7-426d-b360-fb20c649fd7f","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241644411619","EGD_DateTime":"2018-04-24 16:45:54","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501524,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:45:54","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"0f8febeb-2aee-49a1-9044-4d0ae7b9ed72","IEG_GID":"101414e5-70f7-426d-b360-fb20c649fd7f","EGD_DateTime":"2018-04-24 16:45:54","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:45:54","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"e28bfe32-cf1a-4dd6-a1ac-90c7e3bdbe58","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241644144161","EGD_DateTime":"2018-04-24 16:44:55","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501610,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:44:55","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"ecb251ea-99c0-459c-b16d-95ce1e99c865","IEG_GID":"e28bfe32-cf1a-4dd6-a1ac-90c7e3bdbe58","EGD_DateTime":"2018-04-24 16:44:55","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:44:55","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"6678b873-5edc-4e05-9f8e-17d0163a84ac","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241641268977","EGD_DateTime":"2018-04-24 16:43:11","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501696,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:43:11","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"80e310b5-f036-4f14-8d2a-0bf8d3848499","IEG_GID":"6678b873-5edc-4e05-9f8e-17d0163a84ac","EGD_DateTime":"2018-04-24 16:43:11","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:43:11","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"731846c8-76e5-478d-bb5e-29f1be9905f6","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241641268977","EGD_DateTime":"2018-04-24 16:42:08","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501782,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:42:08","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"8b8b662b-b31e-47d2-a371-6db99fdfceb5","IEG_GID":"731846c8-76e5-478d-bb5e-29f1be9905f6","EGD_DateTime":"2018-04-24 16:42:08","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:42:08","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"df021499-ffcd-48c0-a360-19cd3ce3fdbd","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241629227502","EGD_DateTime":"2018-04-24 16:32:44","IEG_Number":3,"IEG_Score":258,"IEG_RemainingPoints":24297,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:32:44","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"a5167fd7-0dec-4121-b4e8-109f0edeea42","IEG_GID":"df021499-ffcd-48c0-a360-19cd3ce3fdbd","EGD_DateTime":"2018-04-24 16:32:44","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":3,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":258,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:32:44","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"46bb0fb2-ac22-44aa-9b97-bb252465c63d","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241629227502","EGD_DateTime":"2018-04-24 16:32:32","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":24555,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:32:32","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"e4ec942c-cee3-4711-960c-22085fb21e61","IEG_GID":"46bb0fb2-ac22-44aa-9b97-bb252465c63d","EGD_DateTime":"2018-04-24 16:32:32","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:32:32","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"267dd0aa-41fc-472c-bd6a-1733128073e5","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241629227502","EGD_DateTime":"2018-04-24 16:32:10","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":24641,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:32:10","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"77b819e1-b0f7-4c33-a962-ce5bf5c32d86","IEG_GID":"267dd0aa-41fc-472c-bd6a-1733128073e5","EGD_DateTime":"2018-04-24 16:32:10","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:32:10","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"69c2ac01-fa3f-4946-85e5-5d8bfa77ffc6","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241629227502","EGD_DateTime":"2018-04-24 16:29:52","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":24727,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:29:52","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"9921f599-1c87-4c6c-986c-2c597f932529","IEG_GID":"69c2ac01-fa3f-4946-85e5-5d8bfa77ffc6","EGD_DateTime":"2018-04-24 16:29:52","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:29:52","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"74e23728-37e8-4aea-96bb-adc5a8b0277a","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241629227502","EGD_DateTime":"2018-04-24 16:29:34","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501868,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:29:34","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"5a467ac4-a67a-4027-be03-356ca010ba59","IEG_GID":"74e23728-37e8-4aea-96bb-adc5a8b0277a","EGD_DateTime":"2018-04-24 16:29:34","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:29:34","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"a98b9e35-cfaa-4160-881e-49a96fd11db5","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241627235675","EGD_DateTime":"2018-04-24 16:29:15","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":24813,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:29:15","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"8c541b3d-b9b8-48bd-8092-d2eb8d9516ec","IEG_GID":"a98b9e35-cfaa-4160-881e-49a96fd11db5","EGD_DateTime":"2018-04-24 16:29:15","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:29:15","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"94cca29c-fd21-437d-aed0-147864197fda","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241627235675","EGD_DateTime":"2018-04-24 16:28:13","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501954,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:28:13","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"f57cd896-cfaf-4d24-b473-ac8d2881fd6b","IEG_GID":"94cca29c-fd21-437d-aed0-147864197fda","EGD_DateTime":"2018-04-24 16:28:13","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:28:13","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"e0ec7715-0b33-44ae-b08e-b30487f5c0b1","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241622144948","EGD_DateTime":"2018-04-24 16:22:46","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":502040,"IEG_Remark":"","IEG_UpdateTime":"2018-04-24 16:22:46","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"c757ed3c-4256-4d9b-9ae3-2f1d7edb8512","IEG_GID":"e0ec7715-0b33-44ae-b08e-b30487f5c0b1","EGD_DateTime":"2018-04-24 16:22:46","GT_Name":"，，","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:22:46","GM_GID":null}],"IS_Sms":false,"IEG_Device":1}],"StatisticsInfo":{"SA_ExchangedScore":"2752.00","SA_RemainingScore":"7192103.00"}}
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
         * PageTotal : 2
         * PageSize : 20
         * DataCount : 21
         * PageIndex : 1
         * DataList : [{"GID":"fcd9a2e7-e765-4880-99c2-77a27f9234e7","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804251109076120","EGD_DateTime":"2018-04-25 11:11:35","IEG_Number":10,"IEG_Score":860,"IEG_RemainingPoints":500148,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-25 11:11:35","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"9e7ab860-fe98-4fa7-a9eb-d13d4f660c4c","IEG_GID":"fcd9a2e7-e765-4880-99c2-77a27f9234e7","EGD_DateTime":"2018-04-25 11:11:35","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":10,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":860,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-25 11:11:35","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"4d076465-cbff-4aba-aff2-aa629788c01d","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241843474509","EGD_DateTime":"2018-04-24 18:44:47","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501008,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 18:44:47","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"39f3897e-fd20-4c16-bd45-a2bdece7e7e4","IEG_GID":"4d076465-cbff-4aba-aff2-aa629788c01d","EGD_DateTime":"2018-04-24 18:44:47","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 18:44:47","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"4e49f183-f985-425e-8cf5-f38c7e46a93e","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241743096961","EGD_DateTime":"2018-04-24 17:44:00","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501094,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 17:44:00","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"9424d146-c719-40f9-aeec-850959834cfb","IEG_GID":"4e49f183-f985-425e-8cf5-f38c7e46a93e","EGD_DateTime":"2018-04-24 17:44:00","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:44:00","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"3209a5bc-8c08-46b3-8c5e-c2501a20138f","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241739187250","EGD_DateTime":"2018-04-24 17:39:56","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501180,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 17:39:56","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"011391b1-ae5f-4469-85df-4e00b93ee5c8","IEG_GID":"3209a5bc-8c08-46b3-8c5e-c2501a20138f","EGD_DateTime":"2018-04-24 17:39:56","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:39:56","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"bf3ae39d-5576-4ca0-be2b-cac6223df058","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241733077909","EGD_DateTime":"2018-04-24 17:33:49","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501266,"IEG_Remark":"","IEG_UpdateTime":"2018-04-24 17:33:49","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"a55dfe03-f1fc-473a-b317-523dcf0f2965","IEG_GID":"bf3ae39d-5576-4ca0-be2b-cac6223df058","EGD_DateTime":"2018-04-24 17:33:49","GT_Name":"，，","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:33:49","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"f984af7f-a6a3-4cf9-9eaa-6116c82f14f7","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241727213790","EGD_DateTime":"2018-04-24 17:28:06","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":24211,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 17:28:06","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"e6aec17a-c9ea-4c01-b518-5ea1ea0c5233","IEG_GID":"f984af7f-a6a3-4cf9-9eaa-6116c82f14f7","EGD_DateTime":"2018-04-24 17:28:06","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:28:06","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"03845918-6748-49f1-aefa-5a9c7a296f05","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241726444662","EGD_DateTime":"2018-04-24 17:27:36","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501352,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 17:27:36","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"e497d093-0c2d-4a2e-b1b6-46accac6e071","IEG_GID":"03845918-6748-49f1-aefa-5a9c7a296f05","EGD_DateTime":"2018-04-24 17:27:36","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:27:36","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"3f45b33e-6cf4-46d2-a691-fc208797cc2c","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241725195549","EGD_DateTime":"2018-04-24 17:25:58","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501438,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 17:25:58","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"b713c9ce-9a6c-4345-b226-5f61bd8c0d08","IEG_GID":"3f45b33e-6cf4-46d2-a691-fc208797cc2c","EGD_DateTime":"2018-04-24 17:25:58","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 17:25:58","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"101414e5-70f7-426d-b360-fb20c649fd7f","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241644411619","EGD_DateTime":"2018-04-24 16:45:54","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501524,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:45:54","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"0f8febeb-2aee-49a1-9044-4d0ae7b9ed72","IEG_GID":"101414e5-70f7-426d-b360-fb20c649fd7f","EGD_DateTime":"2018-04-24 16:45:54","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:45:54","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"e28bfe32-cf1a-4dd6-a1ac-90c7e3bdbe58","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241644144161","EGD_DateTime":"2018-04-24 16:44:55","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501610,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:44:55","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"ecb251ea-99c0-459c-b16d-95ce1e99c865","IEG_GID":"e28bfe32-cf1a-4dd6-a1ac-90c7e3bdbe58","EGD_DateTime":"2018-04-24 16:44:55","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:44:55","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"6678b873-5edc-4e05-9f8e-17d0163a84ac","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241641268977","EGD_DateTime":"2018-04-24 16:43:11","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501696,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:43:11","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"80e310b5-f036-4f14-8d2a-0bf8d3848499","IEG_GID":"6678b873-5edc-4e05-9f8e-17d0163a84ac","EGD_DateTime":"2018-04-24 16:43:11","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:43:11","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"731846c8-76e5-478d-bb5e-29f1be9905f6","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241641268977","EGD_DateTime":"2018-04-24 16:42:08","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501782,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:42:08","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"8b8b662b-b31e-47d2-a371-6db99fdfceb5","IEG_GID":"731846c8-76e5-478d-bb5e-29f1be9905f6","EGD_DateTime":"2018-04-24 16:42:08","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:42:08","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"df021499-ffcd-48c0-a360-19cd3ce3fdbd","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241629227502","EGD_DateTime":"2018-04-24 16:32:44","IEG_Number":3,"IEG_Score":258,"IEG_RemainingPoints":24297,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:32:44","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"a5167fd7-0dec-4121-b4e8-109f0edeea42","IEG_GID":"df021499-ffcd-48c0-a360-19cd3ce3fdbd","EGD_DateTime":"2018-04-24 16:32:44","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":3,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":258,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:32:44","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"46bb0fb2-ac22-44aa-9b97-bb252465c63d","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241629227502","EGD_DateTime":"2018-04-24 16:32:32","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":24555,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:32:32","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"e4ec942c-cee3-4711-960c-22085fb21e61","IEG_GID":"46bb0fb2-ac22-44aa-9b97-bb252465c63d","EGD_DateTime":"2018-04-24 16:32:32","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:32:32","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"267dd0aa-41fc-472c-bd6a-1733128073e5","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241629227502","EGD_DateTime":"2018-04-24 16:32:10","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":24641,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:32:10","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"77b819e1-b0f7-4c33-a962-ce5bf5c32d86","IEG_GID":"267dd0aa-41fc-472c-bd6a-1733128073e5","EGD_DateTime":"2018-04-24 16:32:10","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:32:10","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"69c2ac01-fa3f-4946-85e5-5d8bfa77ffc6","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241629227502","EGD_DateTime":"2018-04-24 16:29:52","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":24727,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:29:52","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"9921f599-1c87-4c6c-986c-2c597f932529","IEG_GID":"69c2ac01-fa3f-4946-85e5-5d8bfa77ffc6","EGD_DateTime":"2018-04-24 16:29:52","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:29:52","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"74e23728-37e8-4aea-96bb-adc5a8b0277a","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241629227502","EGD_DateTime":"2018-04-24 16:29:34","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501868,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:29:34","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"5a467ac4-a67a-4027-be03-356ca010ba59","IEG_GID":"74e23728-37e8-4aea-96bb-adc5a8b0277a","EGD_DateTime":"2018-04-24 16:29:34","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:29:34","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"a98b9e35-cfaa-4160-881e-49a96fd11db5","VIP_GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_Card":"123","VIP_Name":"张飞","VIP_Phone":"18381306468","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241627235675","EGD_DateTime":"2018-04-24 16:29:15","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":24813,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:29:15","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"8c541b3d-b9b8-48bd-8092-d2eb8d9516ec","IEG_GID":"a98b9e35-cfaa-4160-881e-49a96fd11db5","EGD_DateTime":"2018-04-24 16:29:15","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:29:15","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"94cca29c-fd21-437d-aed0-147864197fda","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241627235675","EGD_DateTime":"2018-04-24 16:28:13","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":501954,"IEG_Remark":null,"IEG_UpdateTime":"2018-04-24 16:28:13","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"f57cd896-cfaf-4d24-b473-ac8d2881fd6b","IEG_GID":"94cca29c-fd21-437d-aed0-147864197fda","EGD_DateTime":"2018-04-24 16:28:13","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:28:13","GM_GID":null}],"IS_Sms":false,"IEG_Device":1},{"GID":"e0ec7715-0b33-44ae-b08e-b30487f5c0b1","VIP_GID":"e1e65bd8-1131-45b6-bea7-9ad122857a14","VIP_Card":"18381306031","VIP_Name":"曹操","VIP_Phone":"18381306031","VIP_FaceNumber":"","IEG_OrderNo":"LP1804241622144948","EGD_DateTime":"2018-04-24 16:22:46","IEG_Number":1,"IEG_Score":86,"IEG_RemainingPoints":502040,"IEG_Remark":"","IEG_UpdateTime":"2018-04-24 16:22:46","IEG_Creator":"18381306468","CY_GID":null,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","GiftsDetail":[{"GID":"c757ed3c-4256-4d9b-9ae3-2f1d7edb8512","IEG_GID":"e0ec7715-0b33-44ae-b08e-b30487f5c0b1","EGD_DateTime":"2018-04-24 16:22:46","GT_Name":"，，","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":1,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":86,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-24 16:22:46","GM_GID":null}],"IS_Sms":false,"IEG_Device":1}]
         * StatisticsInfo : {"SA_ExchangedScore":"2752.00","SA_RemainingScore":"7192103.00"}
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
             * SA_ExchangedScore : 2752.00
             * SA_RemainingScore : 7192103.00
             */

            private String SA_ExchangedScore;
            private String SA_RemainingScore;

            public String getSA_ExchangedScore() {
                return SA_ExchangedScore;
            }

            public void setSA_ExchangedScore(String SA_ExchangedScore) {
                this.SA_ExchangedScore = SA_ExchangedScore;
            }

            public String getSA_RemainingScore() {
                return SA_RemainingScore;
            }

            public void setSA_RemainingScore(String SA_RemainingScore) {
                this.SA_RemainingScore = SA_RemainingScore;
            }
        }

        /**
         *
         */
        public static class DataListBean implements Serializable {
            /**
             * GID : fcd9a2e7-e765-4880-99c2-77a27f9234e7
             * VIP_GID : e1e65bd8-1131-45b6-bea7-9ad122857a14
             * VIP_Card : 18381306031
             * VIP_Name : 曹操
             * VIP_Phone : 18381306031
             * VIP_FaceNumber :
             * IEG_OrderNo : LP1804251109076120
             * EGD_DateTime : 2018-04-25 11:11:35
             * IEG_Number : 10
             * IEG_Score : 860.0
             * IEG_RemainingPoints : 500148.0
             * IEG_Remark : null
             * IEG_UpdateTime : 2018-04-25 11:11:35
             * IEG_Creator : 18381306468
             * CY_GID : null
             * SM_GID : 966d986b-0caa-465b-bbe1-8bd871bf8a0a
             * SM_Name : 云上铺1号店
             * GiftsDetail : [{"GID":"9e7ab860-fe98-4fa7-a9eb-d13d4f660c4c","IEG_GID":"fcd9a2e7-e765-4880-99c2-77a27f9234e7","EGD_DateTime":"2018-04-25 11:11:35","GT_Name":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","GM_Name":"怕就怕","GM_Code":"1236","GM_Acount":10,"GM_Integral":86,"GM_PictureUrl":null,"EGD_Score":860,"EGD_Creator":"18381306468","CY_GID":null,"EGD_UpdateTime":"2018-04-25 11:11:35","GM_GID":null}]
             * IS_Sms : false
             * IEG_Device : 1
             */

            private String GID;
            private String VIP_GID;
            private String VIP_Card;
            private String VIP_Name;
            private String VIP_Phone;
            private String VIP_FaceNumber;
            private String IEG_OrderNo;
            private String EGD_DateTime;
            private int IEG_Number;
            private double IEG_Score;
            private double IEG_RemainingPoints;
            private String IEG_Remark;
            private String IEG_UpdateTime;
            private String IEG_Creator;
            private Object CY_GID;
            private String SM_GID;
            private String SM_Name;
            private boolean IS_Sms;
            private int IEG_Device;
            private List<GiftsDetailBean> GiftsDetail;
            /**
             * 是否可见
             */
            private boolean visiable;

            /**
             * @return
             */
            public boolean isVisiable() {
                return visiable;
            }

            public void setVisiable(boolean visiable) {
                this.visiable = visiable;
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

            public String getVIP_Phone() {
                return VIP_Phone;
            }

            public void setVIP_Phone(String VIP_Phone) {
                this.VIP_Phone = VIP_Phone;
            }

            public String getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(String VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public String getIEG_OrderNo() {
                return IEG_OrderNo;
            }

            public void setIEG_OrderNo(String IEG_OrderNo) {
                this.IEG_OrderNo = IEG_OrderNo;
            }

            public String getEGD_DateTime() {
                return EGD_DateTime;
            }

            public void setEGD_DateTime(String EGD_DateTime) {
                this.EGD_DateTime = EGD_DateTime;
            }

            public int getIEG_Number() {
                return IEG_Number;
            }

            public void setIEG_Number(int IEG_Number) {
                this.IEG_Number = IEG_Number;
            }

            public double getIEG_Score() {
                return IEG_Score;
            }

            public void setIEG_Score(double IEG_Score) {
                this.IEG_Score = IEG_Score;
            }

            public double getIEG_RemainingPoints() {
                return IEG_RemainingPoints;
            }

            public void setIEG_RemainingPoints(double IEG_RemainingPoints) {
                this.IEG_RemainingPoints = IEG_RemainingPoints;
            }

            public String getIEG_Remark() {
                return IEG_Remark;
            }

            public void setIEG_Remark(String IEG_Remark) {
                this.IEG_Remark = IEG_Remark;
            }

            public String getIEG_UpdateTime() {
                return IEG_UpdateTime;
            }

            public void setIEG_UpdateTime(String IEG_UpdateTime) {
                this.IEG_UpdateTime = IEG_UpdateTime;
            }

            public String getIEG_Creator() {
                return IEG_Creator;
            }

            public void setIEG_Creator(String IEG_Creator) {
                this.IEG_Creator = IEG_Creator;
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

            public boolean isIS_Sms() {
                return IS_Sms;
            }

            public void setIS_Sms(boolean IS_Sms) {
                this.IS_Sms = IS_Sms;
            }

            public int getIEG_Device() {
                return IEG_Device;
            }

            public void setIEG_Device(int IEG_Device) {
                this.IEG_Device = IEG_Device;
            }

            public List<GiftsDetailBean> getGiftsDetail() {
                return GiftsDetail;
            }

            public void setGiftsDetail(List<GiftsDetailBean> GiftsDetail) {
                this.GiftsDetail = GiftsDetail;
            }

            public static class GiftsDetailBean implements Serializable {
                /**
                 * GID : 9e7ab860-fe98-4fa7-a9eb-d13d4f660c4c
                 * IEG_GID : fcd9a2e7-e765-4880-99c2-77a27f9234e7
                 * EGD_DateTime : 2018-04-25 11:11:35
                 * GT_Name : b9fe359f-e29c-4bf9-9344-b0b9cfaa713f
                 * GM_Name : 怕就怕
                 * GM_Code : 1236
                 * GM_Acount : 10
                 * GM_Integral : 86.0
                 * GM_PictureUrl : null
                 * EGD_Score : 860.0
                 * EGD_Creator : 18381306468
                 * CY_GID : null
                 * EGD_UpdateTime : 2018-04-25 11:11:35
                 * GM_GID : null
                 */

                private String GID;
                private String IEG_GID;
                private String EGD_DateTime;
                private String GT_Name;
                private String GM_Name;
                private String GM_Code;
                private int GM_Acount;
                private double GM_Integral;
                private Object GM_PictureUrl;
                private double EGD_Score;
                private String EGD_Creator;
                private Object CY_GID;
                private String EGD_UpdateTime;
                private Object GM_GID;

                public String getGID() {
                    return GID;
                }

                public void setGID(String GID) {
                    this.GID = GID;
                }

                public String getIEG_GID() {
                    return IEG_GID;
                }

                public void setIEG_GID(String IEG_GID) {
                    this.IEG_GID = IEG_GID;
                }

                public String getEGD_DateTime() {
                    return EGD_DateTime;
                }

                public void setEGD_DateTime(String EGD_DateTime) {
                    this.EGD_DateTime = EGD_DateTime;
                }

                public String getGT_Name() {
                    return GT_Name;
                }

                public void setGT_Name(String GT_Name) {
                    this.GT_Name = GT_Name;
                }

                public String getGM_Name() {
                    return GM_Name;
                }

                public void setGM_Name(String GM_Name) {
                    this.GM_Name = GM_Name;
                }

                public String getGM_Code() {
                    return GM_Code;
                }

                public void setGM_Code(String GM_Code) {
                    this.GM_Code = GM_Code;
                }

                public int getGM_Acount() {
                    return GM_Acount;
                }

                public void setGM_Acount(int GM_Acount) {
                    this.GM_Acount = GM_Acount;
                }

                public double getGM_Integral() {
                    return GM_Integral;
                }

                public void setGM_Integral(double GM_Integral) {
                    this.GM_Integral = GM_Integral;
                }

                public Object getGM_PictureUrl() {
                    return GM_PictureUrl;
                }

                public void setGM_PictureUrl(Object GM_PictureUrl) {
                    this.GM_PictureUrl = GM_PictureUrl;
                }

                public double getEGD_Score() {
                    return EGD_Score;
                }

                public void setEGD_Score(double EGD_Score) {
                    this.EGD_Score = EGD_Score;
                }

                public String getEGD_Creator() {
                    return EGD_Creator;
                }

                public void setEGD_Creator(String EGD_Creator) {
                    this.EGD_Creator = EGD_Creator;
                }

                public Object getCY_GID() {
                    return CY_GID;
                }

                public void setCY_GID(Object CY_GID) {
                    this.CY_GID = CY_GID;
                }

                public String getEGD_UpdateTime() {
                    return EGD_UpdateTime;
                }

                public void setEGD_UpdateTime(String EGD_UpdateTime) {
                    this.EGD_UpdateTime = EGD_UpdateTime;
                }

                public Object getGM_GID() {
                    return GM_GID;
                }

                public void setGM_GID(Object GM_GID) {
                    this.GM_GID = GM_GID;
                }
            }
        }
    }
}
