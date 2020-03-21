package com.zhiluo.android.yunpu.ui.activity.handduty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YSL on 2018-11-26.
 */

public class HandDutyBean implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":0,"PageSize":0,"DataCount":0,"PageIndex":0,"DataList":[{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"开卡费用","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":0,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"充值","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":100,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"充次","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":0,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"延期费用","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":0,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":110,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"商品消费","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":681.51,"MAI_BalanceChange":20,"MAI_UnionChange":10,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":10,"MAI_AlipayPayChange":10,"MAI_CouponPayChange":20,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"一键加油","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":870,"MAI_BalanceChange":0,"MAI_UnionChange":163,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":200,"MAI_AlipayPayChange":68,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":20,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"快速消费","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":101.21,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"计时消费","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":2085.04,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"开台消费","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":0,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"微信订单","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":0,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"撤单","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":270,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null}],"StatisticsInfo":{"GID":"879a90d1-b1d0-4dd6-ac82-a5925271fc27","SA_NewMemberNumber":8,"SA_AllTotal":0,"SA_CashTotal":0,"SA_BalanceTotal":0,"SA_UnionTotal":0,"SA_WeChatpayTotal":0,"SA_AlipaypayTotal":0,"SA_IntegralTotal":0,"SA_CouponTotal":0,"SA_BarCodeTotal":0,"SA_OtherpayTotal":0,"SA_EraseZeroTotal":0,"SA_OpenCardTotal":0,"SA_OpenCardCash":0,"SA_OpenCardUnion":0,"SA_OpenCardWeChatpay":0,"SA_OpenCardAlipaypay":0,"SA_OpenCardOtherpay":0,"SA_RechargeTotal":0,"SA_RechargeCash":0,"SA_RechargeUnion":0,"SA_RechargeWeChatpay":0,"SA_RechargeAlipaypay":0,"SA_RechargeBarCode":0,"SA_RechargeOtherpay":0,"SA_RechargeEraseZero":0,"SA_ChargeTotal":0,"SA_ChargeCash":0,"SA_ChargeBalance":0,"SA_ChargeUnion":0,"SA_ChargeWeChatpay":0,"SA_ChargeAlipaypay":0,"SA_ChargeIntegral":0,"SA_ChargeCoupon":0,"SA_ChargeBarCode":0,"SA_ChargeOtherpay":0,"SA_ChargeEraseZero":0,"SA_DeductionTotal":0,"SA_DeductionBalance":0,"SA_DelayTotal":0,"SA_DelayCash":0,"SA_DelayBalance":0,"SA_DelayUnion":0,"SA_DelayWeChatpay":0,"SA_DelayWeAlipaypay":0,"SA_DelayIntegral":0,"SA_DelayCoupon":0,"SA_DelayBarCode":0,"SA_DelayOtherpay":0,"SA_DelayEraseZero":0,"SA_ConsumptionTotal":0,"SA_ConsumptionCash":0,"SA_ConsumptionBalance":0,"SA_ConsumptionUnionpay":0,"SA_ConsumptionWeChatpay":0,"SA_ConsumptionAlipaypay":0,"SA_ConsumptionIntegral":0,"SA_ConsumptionCoupon":0,"SA_ConsumptionBarCode":0,"SA_ConsumptionOtherpay":0,"SA_ConsumptionEraseZero":0,"SA_RefuelTotal":0,"SA_RefuelCash":0,"SA_RefuelBalance":0,"SA_RefuelUnionpay":0,"SA_RefuelWeChatpay":0,"SA_RefuelAlipaypay":0,"SA_RefuelIntegral":0,"SA_RefuelCoupon":0,"SA_RefuelBarCode":0,"SA_RefuelOtherpay":0,"SA_RefuelEraseZero":0,"SA_FastConsumptionTotal":0,"SA_FastConsumptionCash":0,"SA_FastConsumptionBalance":0,"SA_FastConsumptionUnion":0,"SA_FastConsumptionWeChatpay":0,"SA_FastConsumptionAlipaypay":0,"SA_FastConsumptionIntegral":0,"SA_FastConsumptionCoupon":0,"SA_FastConsumptionBarCode":0,"SA_FastConsumptionOtherpay":0,"SA_FastConsumptionEraseZero":0,"SA_ComboTotal":0,"SA_ComboCash":0,"SA_ComboBalance":0,"SA_ComboUnion":0,"SA_ComboWeChatpay":0,"SA_ComboAlipaypay":0,"SA_ComboIntegral":0,"SA_ComboCoupon":0,"SA_ComboBarCode":0,"SA_ComboOtherpay":0,"SA_TimeTotal":0,"SA_TimeCash":0,"SA_TimeBalance":0,"SA_TimeUnion":0,"SA_TimeWeChatpay":0,"SA_TimeAlipaypay":0,"SA_TimeIntegral":0,"SA_TimeCoupon":0,"SA_TimeBarCode":0,"SA_TimeOtherpay":0,"SA_TimeEraseZero":0,"SA_ReturnGoodsTotal":0,"SA_ReturnGoodsCash":0,"SA_ReturnGoodsBalance":0,"SA_ReturnGoodsWechat":0,"SA_ReturnGoodsAlipay":0,"SA_HouseTotal":0,"SA_HouseCash":0,"SA_HouseBalance":0,"SA_HouseUnion":0,"SA_HouseWeChatpay":0,"SA_HouseAlipaypay":0,"SA_HouseIntegral":0,"SA_HouseCoupon":0,"SA_HouseBarCode":0,"SA_HouseOtherpay":0,"SA_HouseEraseZero":0,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SA_CreateTime":"2018-12-03 18:03:31","SA_ExchangeState":0,"SA_IssuedBusiness":0.3,"SA_HandInBusiness":0,"SA_ShiftDateTime":null,"SU_GID":null,"SA_ShiftName":"销售001","SA_ShiftAccount":"1058346971@qq.com","SA_SuccessionName":null,"SA_SuccessionAccount":null},"TrendData":null}
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
         * PageTotal : 0
         * PageSize : 0
         * DataCount : 0
         * PageIndex : 0
         * DataList : [{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"开卡费用","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":0,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"充值","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":100,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"充次","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":0,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"延期费用","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":0,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":110,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"商品消费","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":681.51,"MAI_BalanceChange":20,"MAI_UnionChange":10,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":10,"MAI_AlipayPayChange":10,"MAI_CouponPayChange":20,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"一键加油","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":870,"MAI_BalanceChange":0,"MAI_UnionChange":163,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":200,"MAI_AlipayPayChange":68,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":20,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"快速消费","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":101.21,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"计时消费","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":2085.04,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"开台消费","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":0,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"微信订单","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":0,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null},{"MAI_EraseZeroChange":0,"MAI_OtherChange":0,"VIP_Card":null,"VIP_Name":null,"VIP_FaceNumber":null,"VIP_Phone":null,"SM_Name":null,"PayCodeList":null,"MAI_Remark":null,"MAI_IncomeAmount":null,"MAI_PayCode":null,"MAI_OrderCode":null,"SM_GID":null,"GID":null,"VIP_GID":null,"MAI_IncomeTime":null,"MAI_Explain":null,"MAI_Identifying":"撤单","MAI_IdentifyingState":null,"MAI_Creator":null,"MAI_UpdateTime":null,"CY_GID":null,"MAI_BalanceAfterChange":null,"MAI_CashChange":270,"MAI_BalanceChange":0,"MAI_UnionChange":0,"MAI_IntegralChange":0,"MAI_Integral":null,"MAI_ShopName":null,"UM_GID":null,"MAI_WeChatPayChange":0,"MAI_AlipayPayChange":0,"MAI_CouponPayChange":0,"MAI_BarCodePayChange":0,"MAI_ReturnGoods":0,"MAI_Cancellation":null}]
         * StatisticsInfo : {"GID":"879a90d1-b1d0-4dd6-ac82-a5925271fc27","SA_NewMemberNumber":8,"SA_AllTotal":0,"SA_CashTotal":0,"SA_BalanceTotal":0,"SA_UnionTotal":0,"SA_WeChatpayTotal":0,"SA_AlipaypayTotal":0,"SA_IntegralTotal":0,"SA_CouponTotal":0,"SA_BarCodeTotal":0,"SA_OtherpayTotal":0,"SA_EraseZeroTotal":0,"SA_OpenCardTotal":0,"SA_OpenCardCash":0,"SA_OpenCardUnion":0,"SA_OpenCardWeChatpay":0,"SA_OpenCardAlipaypay":0,"SA_OpenCardOtherpay":0,"SA_RechargeTotal":0,"SA_RechargeCash":0,"SA_RechargeUnion":0,"SA_RechargeWeChatpay":0,"SA_RechargeAlipaypay":0,"SA_RechargeBarCode":0,"SA_RechargeOtherpay":0,"SA_RechargeEraseZero":0,"SA_ChargeTotal":0,"SA_ChargeCash":0,"SA_ChargeBalance":0,"SA_ChargeUnion":0,"SA_ChargeWeChatpay":0,"SA_ChargeAlipaypay":0,"SA_ChargeIntegral":0,"SA_ChargeCoupon":0,"SA_ChargeBarCode":0,"SA_ChargeOtherpay":0,"SA_ChargeEraseZero":0,"SA_DeductionTotal":0,"SA_DeductionBalance":0,"SA_DelayTotal":0,"SA_DelayCash":0,"SA_DelayBalance":0,"SA_DelayUnion":0,"SA_DelayWeChatpay":0,"SA_DelayWeAlipaypay":0,"SA_DelayIntegral":0,"SA_DelayCoupon":0,"SA_DelayBarCode":0,"SA_DelayOtherpay":0,"SA_DelayEraseZero":0,"SA_ConsumptionTotal":0,"SA_ConsumptionCash":0,"SA_ConsumptionBalance":0,"SA_ConsumptionUnionpay":0,"SA_ConsumptionWeChatpay":0,"SA_ConsumptionAlipaypay":0,"SA_ConsumptionIntegral":0,"SA_ConsumptionCoupon":0,"SA_ConsumptionBarCode":0,"SA_ConsumptionOtherpay":0,"SA_ConsumptionEraseZero":0,"SA_RefuelTotal":0,"SA_RefuelCash":0,"SA_RefuelBalance":0,"SA_RefuelUnionpay":0,"SA_RefuelWeChatpay":0,"SA_RefuelAlipaypay":0,"SA_RefuelIntegral":0,"SA_RefuelCoupon":0,"SA_RefuelBarCode":0,"SA_RefuelOtherpay":0,"SA_RefuelEraseZero":0,"SA_FastConsumptionTotal":0,"SA_FastConsumptionCash":0,"SA_FastConsumptionBalance":0,"SA_FastConsumptionUnion":0,"SA_FastConsumptionWeChatpay":0,"SA_FastConsumptionAlipaypay":0,"SA_FastConsumptionIntegral":0,"SA_FastConsumptionCoupon":0,"SA_FastConsumptionBarCode":0,"SA_FastConsumptionOtherpay":0,"SA_FastConsumptionEraseZero":0,"SA_ComboTotal":0,"SA_ComboCash":0,"SA_ComboBalance":0,"SA_ComboUnion":0,"SA_ComboWeChatpay":0,"SA_ComboAlipaypay":0,"SA_ComboIntegral":0,"SA_ComboCoupon":0,"SA_ComboBarCode":0,"SA_ComboOtherpay":0,"SA_TimeTotal":0,"SA_TimeCash":0,"SA_TimeBalance":0,"SA_TimeUnion":0,"SA_TimeWeChatpay":0,"SA_TimeAlipaypay":0,"SA_TimeIntegral":0,"SA_TimeCoupon":0,"SA_TimeBarCode":0,"SA_TimeOtherpay":0,"SA_TimeEraseZero":0,"SA_ReturnGoodsTotal":0,"SA_ReturnGoodsCash":0,"SA_ReturnGoodsBalance":0,"SA_ReturnGoodsWechat":0,"SA_ReturnGoodsAlipay":0,"SA_HouseTotal":0,"SA_HouseCash":0,"SA_HouseBalance":0,"SA_HouseUnion":0,"SA_HouseWeChatpay":0,"SA_HouseAlipaypay":0,"SA_HouseIntegral":0,"SA_HouseCoupon":0,"SA_HouseBarCode":0,"SA_HouseOtherpay":0,"SA_HouseEraseZero":0,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SA_CreateTime":"2018-12-03 18:03:31","SA_ExchangeState":0,"SA_IssuedBusiness":0.3,"SA_HandInBusiness":0,"SA_ShiftDateTime":null,"SU_GID":null,"SA_ShiftName":"销售001","SA_ShiftAccount":"1058346971@qq.com","SA_SuccessionName":null,"SA_SuccessionAccount":null}
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
             * GID : 879a90d1-b1d0-4dd6-ac82-a5925271fc27
             * SA_NewMemberNumber : 8
             * SA_AllTotal : 0.0
             * SA_CashTotal : 0.0
             * SA_BalanceTotal : 0.0
             * SA_UnionTotal : 0.0
             * SA_WeChatpayTotal : 0.0
             * SA_AlipaypayTotal : 0.0
             * SA_IntegralTotal : 0.0
             * SA_CouponTotal : 0.0
             * SA_BarCodeTotal : 0.0
             * SA_OtherpayTotal : 0.0
             * SA_EraseZeroTotal : 0.0
             * SA_OpenCardTotal : 0.0
             * SA_OpenCardCash : 0.0
             * SA_OpenCardUnion : 0.0
             * SA_OpenCardWeChatpay : 0.0
             * SA_OpenCardAlipaypay : 0.0
             * SA_OpenCardOtherpay : 0.0
             * SA_RechargeTotal : 0.0
             * SA_RechargeCash : 0.0
             * SA_RechargeUnion : 0.0
             * SA_RechargeWeChatpay : 0.0
             * SA_RechargeAlipaypay : 0.0
             * SA_RechargeBarCode : 0.0
             * SA_RechargeOtherpay : 0.0
             * SA_RechargeEraseZero : 0.0
             * SA_ChargeTotal : 0.0
             * SA_ChargeCash : 0.0
             * SA_ChargeBalance : 0.0
             * SA_ChargeUnion : 0.0
             * SA_ChargeWeChatpay : 0.0
             * SA_ChargeAlipaypay : 0.0
             * SA_ChargeIntegral : 0.0
             * SA_ChargeCoupon : 0.0
             * SA_ChargeBarCode : 0.0
             * SA_ChargeOtherpay : 0.0
             * SA_ChargeEraseZero : 0.0
             * SA_DeductionTotal : 0.0
             * SA_DeductionBalance : 0.0
             * SA_DelayTotal : 0.0
             * SA_DelayCash : 0.0
             * SA_DelayBalance : 0.0
             * SA_DelayUnion : 0.0
             * SA_DelayWeChatpay : 0.0
             * SA_DelayWeAlipaypay : 0.0
             * SA_DelayIntegral : 0.0
             * SA_DelayCoupon : 0.0
             * SA_DelayBarCode : 0.0
             * SA_DelayOtherpay : 0.0
             * SA_DelayEraseZero : 0.0
             * SA_ConsumptionTotal : 0.0
             * SA_ConsumptionCash : 0.0
             * SA_ConsumptionBalance : 0.0
             * SA_ConsumptionUnionpay : 0.0
             * SA_ConsumptionWeChatpay : 0.0
             * SA_ConsumptionAlipaypay : 0.0
             * SA_ConsumptionIntegral : 0.0
             * SA_ConsumptionCoupon : 0.0
             * SA_ConsumptionBarCode : 0.0
             * SA_ConsumptionOtherpay : 0.0
             * SA_ConsumptionEraseZero : 0.0
             * SA_RefuelTotal : 0.0
             * SA_RefuelCash : 0.0
             * SA_RefuelBalance : 0.0
             * SA_RefuelUnionpay : 0.0
             * SA_RefuelWeChatpay : 0.0
             * SA_RefuelAlipaypay : 0.0
             * SA_RefuelIntegral : 0.0
             * SA_RefuelCoupon : 0.0
             * SA_RefuelBarCode : 0.0
             * SA_RefuelOtherpay : 0.0
             * SA_RefuelEraseZero : 0.0
             * SA_FastConsumptionTotal : 0.0
             * SA_FastConsumptionCash : 0.0
             * SA_FastConsumptionBalance : 0.0
             * SA_FastConsumptionUnion : 0.0
             * SA_FastConsumptionWeChatpay : 0.0
             * SA_FastConsumptionAlipaypay : 0.0
             * SA_FastConsumptionIntegral : 0.0
             * SA_FastConsumptionCoupon : 0.0
             * SA_FastConsumptionBarCode : 0.0
             * SA_FastConsumptionOtherpay : 0.0
             * SA_FastConsumptionEraseZero : 0.0
             * SA_ComboTotal : 0.0
             * SA_ComboCash : 0.0
             * SA_ComboBalance : 0.0
             * SA_ComboUnion : 0.0
             * SA_ComboWeChatpay : 0.0
             * SA_ComboAlipaypay : 0.0
             * SA_ComboIntegral : 0.0
             * SA_ComboCoupon : 0.0
             * SA_ComboBarCode : 0.0
             * SA_ComboOtherpay : 0.0
             * SA_TimeTotal : 0.0
             * SA_TimeCash : 0.0
             * SA_TimeBalance : 0.0
             * SA_TimeUnion : 0.0
             * SA_TimeWeChatpay : 0.0
             * SA_TimeAlipaypay : 0.0
             * SA_TimeIntegral : 0.0
             * SA_TimeCoupon : 0.0
             * SA_TimeBarCode : 0.0
             * SA_TimeOtherpay : 0.0
             * SA_TimeEraseZero : 0.0
             * SA_ReturnGoodsTotal : 0.0
             * SA_ReturnGoodsCash : 0.0
             * SA_ReturnGoodsBalance : 0.0
             * SA_ReturnGoodsWechat : 0.0
             * SA_ReturnGoodsAlipay : 0.0
             * SA_HouseTotal : 0.0
             * SA_HouseCash : 0.0
             * SA_HouseBalance : 0.0
             * SA_HouseUnion : 0.0
             * SA_HouseWeChatpay : 0.0
             * SA_HouseAlipaypay : 0.0
             * SA_HouseIntegral : 0.0
             * SA_HouseCoupon : 0.0
             * SA_HouseBarCode : 0.0
             * SA_HouseOtherpay : 0.0
             * SA_HouseEraseZero : 0.0
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * SM_Name : 默认店铺
             * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
             * SA_CreateTime : 2018-12-03 18:03:31
             * SA_ExchangeState : 0
             * SA_IssuedBusiness : 0.3
             * SA_HandInBusiness : 0.0
             * SA_ShiftDateTime : null
             * SU_GID : null
             * SA_ShiftName : 销售001
             * SA_ShiftAccount : 1058346971@qq.com
             * SA_SuccessionName : null
             * SA_SuccessionAccount : null
             */

            private String GID;
            private int SA_NewMemberNumber;
            private double SA_AllTotal;
            private double SA_CashTotal;
            private double SA_BalanceTotal;
            private double SA_UnionTotal;
            private double SA_WeChatpayTotal;
            private double SA_AlipaypayTotal;
            private double SA_IntegralTotal;
            private double SA_CouponTotal;
            private double SA_BarCodeTotal;
            private double SA_OtherpayTotal;
            private double SA_EraseZeroTotal;
            private double SA_OpenCardTotal;
            private double SA_OpenCardCash;
            private double SA_OpenCardUnion;
            private double SA_OpenCardWeChatpay;
            private double SA_OpenCardAlipaypay;
            private double SA_OpenCardOtherpay;
            private double SA_RechargeTotal;
            private double SA_RechargeCash;
            private double SA_RechargeUnion;
            private double SA_RechargeWeChatpay;
            private double SA_RechargeAlipaypay;
            private double SA_RechargeBarCode;
            private double SA_RechargeOtherpay;
            private double SA_RechargeEraseZero;
            private double SA_ChargeTotal;
            private double SA_ChargeCash;
            private double SA_ChargeBalance;
            private double SA_ChargeUnion;
            private double SA_ChargeWeChatpay;
            private double SA_ChargeAlipaypay;
            private double SA_ChargeIntegral;
            private double SA_ChargeCoupon;
            private double SA_ChargeBarCode;
            private double SA_ChargeOtherpay;
            private double SA_ChargeEraseZero;
            private double SA_DeductionTotal;
            private double SA_DeductionBalance;
            private double SA_DelayTotal;
            private double SA_DelayCash;
            private double SA_DelayBalance;
            private double SA_DelayUnion;
            private double SA_DelayWeChatpay;
            private double SA_DelayWeAlipaypay;
            private double SA_DelayIntegral;
            private double SA_DelayCoupon;
            private double SA_DelayBarCode;
            private double SA_DelayOtherpay;
            private double SA_DelayEraseZero;
            private double SA_ConsumptionTotal;
            private double SA_ConsumptionCash;
            private double SA_ConsumptionBalance;
            private double SA_ConsumptionUnionpay;
            private double SA_ConsumptionWeChatpay;
            private double SA_ConsumptionAlipaypay;
            private double SA_ConsumptionIntegral;
            private double SA_ConsumptionCoupon;
            private double SA_ConsumptionBarCode;
            private double SA_ConsumptionOtherpay;
            private double SA_ConsumptionEraseZero;
            private double SA_RefuelTotal;
            private double SA_RefuelCash;
            private double SA_RefuelBalance;
            private double SA_RefuelUnionpay;
            private double SA_RefuelWeChatpay;
            private double SA_RefuelAlipaypay;
            private double SA_RefuelIntegral;
            private double SA_RefuelCoupon;
            private double SA_RefuelBarCode;
            private double SA_RefuelOtherpay;
            private double SA_RefuelEraseZero;
            private double SA_FastConsumptionTotal;
            private double SA_FastConsumptionCash;
            private double SA_FastConsumptionBalance;
            private double SA_FastConsumptionUnion;
            private double SA_FastConsumptionWeChatpay;
            private double SA_FastConsumptionAlipaypay;
            private double SA_FastConsumptionIntegral;
            private double SA_FastConsumptionCoupon;
            private double SA_FastConsumptionBarCode;
            private double SA_FastConsumptionOtherpay;
            private double SA_FastConsumptionEraseZero;
            private double SA_ComboTotal;
            private double SA_ComboCash;
            private double SA_ComboBalance;
            private double SA_ComboUnion;
            private double SA_ComboWeChatpay;
            private double SA_ComboAlipaypay;
            private double SA_ComboIntegral;
            private double SA_ComboCoupon;
            private double SA_ComboBarCode;
            private double SA_ComboOtherpay;
            private double SA_TimeTotal;
            private double SA_TimeCash;
            private double SA_TimeBalance;
            private double SA_TimeUnion;
            private double SA_TimeWeChatpay;
            private double SA_TimeAlipaypay;
            private double SA_TimeIntegral;
            private double SA_TimeCoupon;
            private double SA_TimeBarCode;
            private double SA_TimeOtherpay;
            private double SA_TimeEraseZero;
            private double SA_ReturnGoodsTotal;
            private double SA_ReturnGoodsCash;
            private double SA_ReturnGoodsBalance;
            private double SA_ReturnGoodsWechat;
            private double SA_ReturnGoodsAlipay;
            private double SA_HouseTotal;
            private double SA_HouseCash;
            private double SA_HouseBalance;
            private double SA_HouseUnion;
            private double SA_HouseWeChatpay;
            private double SA_HouseAlipaypay;
            private double SA_HouseIntegral;
            private double SA_HouseCoupon;
            private double SA_HouseBarCode;
            private double SA_HouseOtherpay;
            private double SA_HouseEraseZero;
            private String SM_GID;
            private String SM_Name;
            private String CY_GID;
            private String SA_CreateTime;
            private int SA_ExchangeState;
            private double SA_IssuedBusiness;
            private double SA_HandInBusiness;
            private Object SA_ShiftDateTime;
            private Object SU_GID;
            private String SA_ShiftName;
            private String SA_ShiftAccount;
            private Object SA_SuccessionName;
            private Object SA_SuccessionAccount;

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public int getSA_NewMemberNumber() {
                return SA_NewMemberNumber;
            }

            public void setSA_NewMemberNumber(int SA_NewMemberNumber) {
                this.SA_NewMemberNumber = SA_NewMemberNumber;
            }

            public double getSA_AllTotal() {
                return SA_AllTotal;
            }

            public void setSA_AllTotal(double SA_AllTotal) {
                this.SA_AllTotal = SA_AllTotal;
            }

            public double getSA_CashTotal() {
                return SA_CashTotal;
            }

            public void setSA_CashTotal(double SA_CashTotal) {
                this.SA_CashTotal = SA_CashTotal;
            }

            public double getSA_BalanceTotal() {
                return SA_BalanceTotal;
            }

            public void setSA_BalanceTotal(double SA_BalanceTotal) {
                this.SA_BalanceTotal = SA_BalanceTotal;
            }

            public double getSA_UnionTotal() {
                return SA_UnionTotal;
            }

            public void setSA_UnionTotal(double SA_UnionTotal) {
                this.SA_UnionTotal = SA_UnionTotal;
            }

            public double getSA_WeChatpayTotal() {
                return SA_WeChatpayTotal;
            }

            public void setSA_WeChatpayTotal(double SA_WeChatpayTotal) {
                this.SA_WeChatpayTotal = SA_WeChatpayTotal;
            }

            public double getSA_AlipaypayTotal() {
                return SA_AlipaypayTotal;
            }

            public void setSA_AlipaypayTotal(double SA_AlipaypayTotal) {
                this.SA_AlipaypayTotal = SA_AlipaypayTotal;
            }

            public double getSA_IntegralTotal() {
                return SA_IntegralTotal;
            }

            public void setSA_IntegralTotal(double SA_IntegralTotal) {
                this.SA_IntegralTotal = SA_IntegralTotal;
            }

            public double getSA_CouponTotal() {
                return SA_CouponTotal;
            }

            public void setSA_CouponTotal(double SA_CouponTotal) {
                this.SA_CouponTotal = SA_CouponTotal;
            }

            public double getSA_BarCodeTotal() {
                return SA_BarCodeTotal;
            }

            public void setSA_BarCodeTotal(double SA_BarCodeTotal) {
                this.SA_BarCodeTotal = SA_BarCodeTotal;
            }

            public double getSA_OtherpayTotal() {
                return SA_OtherpayTotal;
            }

            public void setSA_OtherpayTotal(double SA_OtherpayTotal) {
                this.SA_OtherpayTotal = SA_OtherpayTotal;
            }

            public double getSA_EraseZeroTotal() {
                return SA_EraseZeroTotal;
            }

            public void setSA_EraseZeroTotal(double SA_EraseZeroTotal) {
                this.SA_EraseZeroTotal = SA_EraseZeroTotal;
            }

            public double getSA_OpenCardTotal() {
                return SA_OpenCardTotal;
            }

            public void setSA_OpenCardTotal(double SA_OpenCardTotal) {
                this.SA_OpenCardTotal = SA_OpenCardTotal;
            }

            public double getSA_OpenCardCash() {
                return SA_OpenCardCash;
            }

            public void setSA_OpenCardCash(double SA_OpenCardCash) {
                this.SA_OpenCardCash = SA_OpenCardCash;
            }

            public double getSA_OpenCardUnion() {
                return SA_OpenCardUnion;
            }

            public void setSA_OpenCardUnion(double SA_OpenCardUnion) {
                this.SA_OpenCardUnion = SA_OpenCardUnion;
            }

            public double getSA_OpenCardWeChatpay() {
                return SA_OpenCardWeChatpay;
            }

            public void setSA_OpenCardWeChatpay(double SA_OpenCardWeChatpay) {
                this.SA_OpenCardWeChatpay = SA_OpenCardWeChatpay;
            }

            public double getSA_OpenCardAlipaypay() {
                return SA_OpenCardAlipaypay;
            }

            public void setSA_OpenCardAlipaypay(double SA_OpenCardAlipaypay) {
                this.SA_OpenCardAlipaypay = SA_OpenCardAlipaypay;
            }

            public double getSA_OpenCardOtherpay() {
                return SA_OpenCardOtherpay;
            }

            public void setSA_OpenCardOtherpay(double SA_OpenCardOtherpay) {
                this.SA_OpenCardOtherpay = SA_OpenCardOtherpay;
            }

            public double getSA_RechargeTotal() {
                return SA_RechargeTotal;
            }

            public void setSA_RechargeTotal(double SA_RechargeTotal) {
                this.SA_RechargeTotal = SA_RechargeTotal;
            }

            public double getSA_RechargeCash() {
                return SA_RechargeCash;
            }

            public void setSA_RechargeCash(double SA_RechargeCash) {
                this.SA_RechargeCash = SA_RechargeCash;
            }

            public double getSA_RechargeUnion() {
                return SA_RechargeUnion;
            }

            public void setSA_RechargeUnion(double SA_RechargeUnion) {
                this.SA_RechargeUnion = SA_RechargeUnion;
            }

            public double getSA_RechargeWeChatpay() {
                return SA_RechargeWeChatpay;
            }

            public void setSA_RechargeWeChatpay(double SA_RechargeWeChatpay) {
                this.SA_RechargeWeChatpay = SA_RechargeWeChatpay;
            }

            public double getSA_RechargeAlipaypay() {
                return SA_RechargeAlipaypay;
            }

            public void setSA_RechargeAlipaypay(double SA_RechargeAlipaypay) {
                this.SA_RechargeAlipaypay = SA_RechargeAlipaypay;
            }

            public double getSA_RechargeBarCode() {
                return SA_RechargeBarCode;
            }

            public void setSA_RechargeBarCode(double SA_RechargeBarCode) {
                this.SA_RechargeBarCode = SA_RechargeBarCode;
            }

            public double getSA_RechargeOtherpay() {
                return SA_RechargeOtherpay;
            }

            public void setSA_RechargeOtherpay(double SA_RechargeOtherpay) {
                this.SA_RechargeOtherpay = SA_RechargeOtherpay;
            }

            public double getSA_RechargeEraseZero() {
                return SA_RechargeEraseZero;
            }

            public void setSA_RechargeEraseZero(double SA_RechargeEraseZero) {
                this.SA_RechargeEraseZero = SA_RechargeEraseZero;
            }

            public double getSA_ChargeTotal() {
                return SA_ChargeTotal;
            }

            public void setSA_ChargeTotal(double SA_ChargeTotal) {
                this.SA_ChargeTotal = SA_ChargeTotal;
            }

            public double getSA_ChargeCash() {
                return SA_ChargeCash;
            }

            public void setSA_ChargeCash(double SA_ChargeCash) {
                this.SA_ChargeCash = SA_ChargeCash;
            }

            public double getSA_ChargeBalance() {
                return SA_ChargeBalance;
            }

            public void setSA_ChargeBalance(double SA_ChargeBalance) {
                this.SA_ChargeBalance = SA_ChargeBalance;
            }

            public double getSA_ChargeUnion() {
                return SA_ChargeUnion;
            }

            public void setSA_ChargeUnion(double SA_ChargeUnion) {
                this.SA_ChargeUnion = SA_ChargeUnion;
            }

            public double getSA_ChargeWeChatpay() {
                return SA_ChargeWeChatpay;
            }

            public void setSA_ChargeWeChatpay(double SA_ChargeWeChatpay) {
                this.SA_ChargeWeChatpay = SA_ChargeWeChatpay;
            }

            public double getSA_ChargeAlipaypay() {
                return SA_ChargeAlipaypay;
            }

            public void setSA_ChargeAlipaypay(double SA_ChargeAlipaypay) {
                this.SA_ChargeAlipaypay = SA_ChargeAlipaypay;
            }

            public double getSA_ChargeIntegral() {
                return SA_ChargeIntegral;
            }

            public void setSA_ChargeIntegral(double SA_ChargeIntegral) {
                this.SA_ChargeIntegral = SA_ChargeIntegral;
            }

            public double getSA_ChargeCoupon() {
                return SA_ChargeCoupon;
            }

            public void setSA_ChargeCoupon(double SA_ChargeCoupon) {
                this.SA_ChargeCoupon = SA_ChargeCoupon;
            }

            public double getSA_ChargeBarCode() {
                return SA_ChargeBarCode;
            }

            public void setSA_ChargeBarCode(double SA_ChargeBarCode) {
                this.SA_ChargeBarCode = SA_ChargeBarCode;
            }

            public double getSA_ChargeOtherpay() {
                return SA_ChargeOtherpay;
            }

            public void setSA_ChargeOtherpay(double SA_ChargeOtherpay) {
                this.SA_ChargeOtherpay = SA_ChargeOtherpay;
            }

            public double getSA_ChargeEraseZero() {
                return SA_ChargeEraseZero;
            }

            public void setSA_ChargeEraseZero(double SA_ChargeEraseZero) {
                this.SA_ChargeEraseZero = SA_ChargeEraseZero;
            }

            public double getSA_DeductionTotal() {
                return SA_DeductionTotal;
            }

            public void setSA_DeductionTotal(double SA_DeductionTotal) {
                this.SA_DeductionTotal = SA_DeductionTotal;
            }

            public double getSA_DeductionBalance() {
                return SA_DeductionBalance;
            }

            public void setSA_DeductionBalance(double SA_DeductionBalance) {
                this.SA_DeductionBalance = SA_DeductionBalance;
            }

            public double getSA_DelayTotal() {
                return SA_DelayTotal;
            }

            public void setSA_DelayTotal(double SA_DelayTotal) {
                this.SA_DelayTotal = SA_DelayTotal;
            }

            public double getSA_DelayCash() {
                return SA_DelayCash;
            }

            public void setSA_DelayCash(double SA_DelayCash) {
                this.SA_DelayCash = SA_DelayCash;
            }

            public double getSA_DelayBalance() {
                return SA_DelayBalance;
            }

            public void setSA_DelayBalance(double SA_DelayBalance) {
                this.SA_DelayBalance = SA_DelayBalance;
            }

            public double getSA_DelayUnion() {
                return SA_DelayUnion;
            }

            public void setSA_DelayUnion(double SA_DelayUnion) {
                this.SA_DelayUnion = SA_DelayUnion;
            }

            public double getSA_DelayWeChatpay() {
                return SA_DelayWeChatpay;
            }

            public void setSA_DelayWeChatpay(double SA_DelayWeChatpay) {
                this.SA_DelayWeChatpay = SA_DelayWeChatpay;
            }

            public double getSA_DelayWeAlipaypay() {
                return SA_DelayWeAlipaypay;
            }

            public void setSA_DelayWeAlipaypay(double SA_DelayWeAlipaypay) {
                this.SA_DelayWeAlipaypay = SA_DelayWeAlipaypay;
            }

            public double getSA_DelayIntegral() {
                return SA_DelayIntegral;
            }

            public void setSA_DelayIntegral(double SA_DelayIntegral) {
                this.SA_DelayIntegral = SA_DelayIntegral;
            }

            public double getSA_DelayCoupon() {
                return SA_DelayCoupon;
            }

            public void setSA_DelayCoupon(double SA_DelayCoupon) {
                this.SA_DelayCoupon = SA_DelayCoupon;
            }

            public double getSA_DelayBarCode() {
                return SA_DelayBarCode;
            }

            public void setSA_DelayBarCode(double SA_DelayBarCode) {
                this.SA_DelayBarCode = SA_DelayBarCode;
            }

            public double getSA_DelayOtherpay() {
                return SA_DelayOtherpay;
            }

            public void setSA_DelayOtherpay(double SA_DelayOtherpay) {
                this.SA_DelayOtherpay = SA_DelayOtherpay;
            }

            public double getSA_DelayEraseZero() {
                return SA_DelayEraseZero;
            }

            public void setSA_DelayEraseZero(double SA_DelayEraseZero) {
                this.SA_DelayEraseZero = SA_DelayEraseZero;
            }

            public double getSA_ConsumptionTotal() {
                return SA_ConsumptionTotal;
            }

            public void setSA_ConsumptionTotal(double SA_ConsumptionTotal) {
                this.SA_ConsumptionTotal = SA_ConsumptionTotal;
            }

            public double getSA_ConsumptionCash() {
                return SA_ConsumptionCash;
            }

            public void setSA_ConsumptionCash(double SA_ConsumptionCash) {
                this.SA_ConsumptionCash = SA_ConsumptionCash;
            }

            public double getSA_ConsumptionBalance() {
                return SA_ConsumptionBalance;
            }

            public void setSA_ConsumptionBalance(double SA_ConsumptionBalance) {
                this.SA_ConsumptionBalance = SA_ConsumptionBalance;
            }

            public double getSA_ConsumptionUnionpay() {
                return SA_ConsumptionUnionpay;
            }

            public void setSA_ConsumptionUnionpay(double SA_ConsumptionUnionpay) {
                this.SA_ConsumptionUnionpay = SA_ConsumptionUnionpay;
            }

            public double getSA_ConsumptionWeChatpay() {
                return SA_ConsumptionWeChatpay;
            }

            public void setSA_ConsumptionWeChatpay(double SA_ConsumptionWeChatpay) {
                this.SA_ConsumptionWeChatpay = SA_ConsumptionWeChatpay;
            }

            public double getSA_ConsumptionAlipaypay() {
                return SA_ConsumptionAlipaypay;
            }

            public void setSA_ConsumptionAlipaypay(double SA_ConsumptionAlipaypay) {
                this.SA_ConsumptionAlipaypay = SA_ConsumptionAlipaypay;
            }

            public double getSA_ConsumptionIntegral() {
                return SA_ConsumptionIntegral;
            }

            public void setSA_ConsumptionIntegral(double SA_ConsumptionIntegral) {
                this.SA_ConsumptionIntegral = SA_ConsumptionIntegral;
            }

            public double getSA_ConsumptionCoupon() {
                return SA_ConsumptionCoupon;
            }

            public void setSA_ConsumptionCoupon(double SA_ConsumptionCoupon) {
                this.SA_ConsumptionCoupon = SA_ConsumptionCoupon;
            }

            public double getSA_ConsumptionBarCode() {
                return SA_ConsumptionBarCode;
            }

            public void setSA_ConsumptionBarCode(double SA_ConsumptionBarCode) {
                this.SA_ConsumptionBarCode = SA_ConsumptionBarCode;
            }

            public double getSA_ConsumptionOtherpay() {
                return SA_ConsumptionOtherpay;
            }

            public void setSA_ConsumptionOtherpay(double SA_ConsumptionOtherpay) {
                this.SA_ConsumptionOtherpay = SA_ConsumptionOtherpay;
            }

            public double getSA_ConsumptionEraseZero() {
                return SA_ConsumptionEraseZero;
            }

            public void setSA_ConsumptionEraseZero(double SA_ConsumptionEraseZero) {
                this.SA_ConsumptionEraseZero = SA_ConsumptionEraseZero;
            }

            public double getSA_RefuelTotal() {
                return SA_RefuelTotal;
            }

            public void setSA_RefuelTotal(double SA_RefuelTotal) {
                this.SA_RefuelTotal = SA_RefuelTotal;
            }

            public double getSA_RefuelCash() {
                return SA_RefuelCash;
            }

            public void setSA_RefuelCash(double SA_RefuelCash) {
                this.SA_RefuelCash = SA_RefuelCash;
            }

            public double getSA_RefuelBalance() {
                return SA_RefuelBalance;
            }

            public void setSA_RefuelBalance(double SA_RefuelBalance) {
                this.SA_RefuelBalance = SA_RefuelBalance;
            }

            public double getSA_RefuelUnionpay() {
                return SA_RefuelUnionpay;
            }

            public void setSA_RefuelUnionpay(double SA_RefuelUnionpay) {
                this.SA_RefuelUnionpay = SA_RefuelUnionpay;
            }

            public double getSA_RefuelWeChatpay() {
                return SA_RefuelWeChatpay;
            }

            public void setSA_RefuelWeChatpay(double SA_RefuelWeChatpay) {
                this.SA_RefuelWeChatpay = SA_RefuelWeChatpay;
            }

            public double getSA_RefuelAlipaypay() {
                return SA_RefuelAlipaypay;
            }

            public void setSA_RefuelAlipaypay(double SA_RefuelAlipaypay) {
                this.SA_RefuelAlipaypay = SA_RefuelAlipaypay;
            }

            public double getSA_RefuelIntegral() {
                return SA_RefuelIntegral;
            }

            public void setSA_RefuelIntegral(double SA_RefuelIntegral) {
                this.SA_RefuelIntegral = SA_RefuelIntegral;
            }

            public double getSA_RefuelCoupon() {
                return SA_RefuelCoupon;
            }

            public void setSA_RefuelCoupon(double SA_RefuelCoupon) {
                this.SA_RefuelCoupon = SA_RefuelCoupon;
            }

            public double getSA_RefuelBarCode() {
                return SA_RefuelBarCode;
            }

            public void setSA_RefuelBarCode(double SA_RefuelBarCode) {
                this.SA_RefuelBarCode = SA_RefuelBarCode;
            }

            public double getSA_RefuelOtherpay() {
                return SA_RefuelOtherpay;
            }

            public void setSA_RefuelOtherpay(double SA_RefuelOtherpay) {
                this.SA_RefuelOtherpay = SA_RefuelOtherpay;
            }

            public double getSA_RefuelEraseZero() {
                return SA_RefuelEraseZero;
            }

            public void setSA_RefuelEraseZero(double SA_RefuelEraseZero) {
                this.SA_RefuelEraseZero = SA_RefuelEraseZero;
            }

            public double getSA_FastConsumptionTotal() {
                return SA_FastConsumptionTotal;
            }

            public void setSA_FastConsumptionTotal(double SA_FastConsumptionTotal) {
                this.SA_FastConsumptionTotal = SA_FastConsumptionTotal;
            }

            public double getSA_FastConsumptionCash() {
                return SA_FastConsumptionCash;
            }

            public void setSA_FastConsumptionCash(double SA_FastConsumptionCash) {
                this.SA_FastConsumptionCash = SA_FastConsumptionCash;
            }

            public double getSA_FastConsumptionBalance() {
                return SA_FastConsumptionBalance;
            }

            public void setSA_FastConsumptionBalance(double SA_FastConsumptionBalance) {
                this.SA_FastConsumptionBalance = SA_FastConsumptionBalance;
            }

            public double getSA_FastConsumptionUnion() {
                return SA_FastConsumptionUnion;
            }

            public void setSA_FastConsumptionUnion(double SA_FastConsumptionUnion) {
                this.SA_FastConsumptionUnion = SA_FastConsumptionUnion;
            }

            public double getSA_FastConsumptionWeChatpay() {
                return SA_FastConsumptionWeChatpay;
            }

            public void setSA_FastConsumptionWeChatpay(double SA_FastConsumptionWeChatpay) {
                this.SA_FastConsumptionWeChatpay = SA_FastConsumptionWeChatpay;
            }

            public double getSA_FastConsumptionAlipaypay() {
                return SA_FastConsumptionAlipaypay;
            }

            public void setSA_FastConsumptionAlipaypay(double SA_FastConsumptionAlipaypay) {
                this.SA_FastConsumptionAlipaypay = SA_FastConsumptionAlipaypay;
            }

            public double getSA_FastConsumptionIntegral() {
                return SA_FastConsumptionIntegral;
            }

            public void setSA_FastConsumptionIntegral(double SA_FastConsumptionIntegral) {
                this.SA_FastConsumptionIntegral = SA_FastConsumptionIntegral;
            }

            public double getSA_FastConsumptionCoupon() {
                return SA_FastConsumptionCoupon;
            }

            public void setSA_FastConsumptionCoupon(double SA_FastConsumptionCoupon) {
                this.SA_FastConsumptionCoupon = SA_FastConsumptionCoupon;
            }

            public double getSA_FastConsumptionBarCode() {
                return SA_FastConsumptionBarCode;
            }

            public void setSA_FastConsumptionBarCode(double SA_FastConsumptionBarCode) {
                this.SA_FastConsumptionBarCode = SA_FastConsumptionBarCode;
            }

            public double getSA_FastConsumptionOtherpay() {
                return SA_FastConsumptionOtherpay;
            }

            public void setSA_FastConsumptionOtherpay(double SA_FastConsumptionOtherpay) {
                this.SA_FastConsumptionOtherpay = SA_FastConsumptionOtherpay;
            }

            public double getSA_FastConsumptionEraseZero() {
                return SA_FastConsumptionEraseZero;
            }

            public void setSA_FastConsumptionEraseZero(double SA_FastConsumptionEraseZero) {
                this.SA_FastConsumptionEraseZero = SA_FastConsumptionEraseZero;
            }

            public double getSA_ComboTotal() {
                return SA_ComboTotal;
            }

            public void setSA_ComboTotal(double SA_ComboTotal) {
                this.SA_ComboTotal = SA_ComboTotal;
            }

            public double getSA_ComboCash() {
                return SA_ComboCash;
            }

            public void setSA_ComboCash(double SA_ComboCash) {
                this.SA_ComboCash = SA_ComboCash;
            }

            public double getSA_ComboBalance() {
                return SA_ComboBalance;
            }

            public void setSA_ComboBalance(double SA_ComboBalance) {
                this.SA_ComboBalance = SA_ComboBalance;
            }

            public double getSA_ComboUnion() {
                return SA_ComboUnion;
            }

            public void setSA_ComboUnion(double SA_ComboUnion) {
                this.SA_ComboUnion = SA_ComboUnion;
            }

            public double getSA_ComboWeChatpay() {
                return SA_ComboWeChatpay;
            }

            public void setSA_ComboWeChatpay(double SA_ComboWeChatpay) {
                this.SA_ComboWeChatpay = SA_ComboWeChatpay;
            }

            public double getSA_ComboAlipaypay() {
                return SA_ComboAlipaypay;
            }

            public void setSA_ComboAlipaypay(double SA_ComboAlipaypay) {
                this.SA_ComboAlipaypay = SA_ComboAlipaypay;
            }

            public double getSA_ComboIntegral() {
                return SA_ComboIntegral;
            }

            public void setSA_ComboIntegral(double SA_ComboIntegral) {
                this.SA_ComboIntegral = SA_ComboIntegral;
            }

            public double getSA_ComboCoupon() {
                return SA_ComboCoupon;
            }

            public void setSA_ComboCoupon(double SA_ComboCoupon) {
                this.SA_ComboCoupon = SA_ComboCoupon;
            }

            public double getSA_ComboBarCode() {
                return SA_ComboBarCode;
            }

            public void setSA_ComboBarCode(double SA_ComboBarCode) {
                this.SA_ComboBarCode = SA_ComboBarCode;
            }

            public double getSA_ComboOtherpay() {
                return SA_ComboOtherpay;
            }

            public void setSA_ComboOtherpay(double SA_ComboOtherpay) {
                this.SA_ComboOtherpay = SA_ComboOtherpay;
            }

            public double getSA_TimeTotal() {
                return SA_TimeTotal;
            }

            public void setSA_TimeTotal(double SA_TimeTotal) {
                this.SA_TimeTotal = SA_TimeTotal;
            }

            public double getSA_TimeCash() {
                return SA_TimeCash;
            }

            public void setSA_TimeCash(double SA_TimeCash) {
                this.SA_TimeCash = SA_TimeCash;
            }

            public double getSA_TimeBalance() {
                return SA_TimeBalance;
            }

            public void setSA_TimeBalance(double SA_TimeBalance) {
                this.SA_TimeBalance = SA_TimeBalance;
            }

            public double getSA_TimeUnion() {
                return SA_TimeUnion;
            }

            public void setSA_TimeUnion(double SA_TimeUnion) {
                this.SA_TimeUnion = SA_TimeUnion;
            }

            public double getSA_TimeWeChatpay() {
                return SA_TimeWeChatpay;
            }

            public void setSA_TimeWeChatpay(double SA_TimeWeChatpay) {
                this.SA_TimeWeChatpay = SA_TimeWeChatpay;
            }

            public double getSA_TimeAlipaypay() {
                return SA_TimeAlipaypay;
            }

            public void setSA_TimeAlipaypay(double SA_TimeAlipaypay) {
                this.SA_TimeAlipaypay = SA_TimeAlipaypay;
            }

            public double getSA_TimeIntegral() {
                return SA_TimeIntegral;
            }

            public void setSA_TimeIntegral(double SA_TimeIntegral) {
                this.SA_TimeIntegral = SA_TimeIntegral;
            }

            public double getSA_TimeCoupon() {
                return SA_TimeCoupon;
            }

            public void setSA_TimeCoupon(double SA_TimeCoupon) {
                this.SA_TimeCoupon = SA_TimeCoupon;
            }

            public double getSA_TimeBarCode() {
                return SA_TimeBarCode;
            }

            public void setSA_TimeBarCode(double SA_TimeBarCode) {
                this.SA_TimeBarCode = SA_TimeBarCode;
            }

            public double getSA_TimeOtherpay() {
                return SA_TimeOtherpay;
            }

            public void setSA_TimeOtherpay(double SA_TimeOtherpay) {
                this.SA_TimeOtherpay = SA_TimeOtherpay;
            }

            public double getSA_TimeEraseZero() {
                return SA_TimeEraseZero;
            }

            public void setSA_TimeEraseZero(double SA_TimeEraseZero) {
                this.SA_TimeEraseZero = SA_TimeEraseZero;
            }

            public double getSA_ReturnGoodsTotal() {
                return SA_ReturnGoodsTotal;
            }

            public void setSA_ReturnGoodsTotal(double SA_ReturnGoodsTotal) {
                this.SA_ReturnGoodsTotal = SA_ReturnGoodsTotal;
            }

            public double getSA_ReturnGoodsCash() {
                return SA_ReturnGoodsCash;
            }

            public void setSA_ReturnGoodsCash(double SA_ReturnGoodsCash) {
                this.SA_ReturnGoodsCash = SA_ReturnGoodsCash;
            }

            public double getSA_ReturnGoodsBalance() {
                return SA_ReturnGoodsBalance;
            }

            public void setSA_ReturnGoodsBalance(double SA_ReturnGoodsBalance) {
                this.SA_ReturnGoodsBalance = SA_ReturnGoodsBalance;
            }

            public double getSA_ReturnGoodsWechat() {
                return SA_ReturnGoodsWechat;
            }

            public void setSA_ReturnGoodsWechat(double SA_ReturnGoodsWechat) {
                this.SA_ReturnGoodsWechat = SA_ReturnGoodsWechat;
            }

            public double getSA_ReturnGoodsAlipay() {
                return SA_ReturnGoodsAlipay;
            }

            public void setSA_ReturnGoodsAlipay(double SA_ReturnGoodsAlipay) {
                this.SA_ReturnGoodsAlipay = SA_ReturnGoodsAlipay;
            }

            public double getSA_HouseTotal() {
                return SA_HouseTotal;
            }

            public void setSA_HouseTotal(double SA_HouseTotal) {
                this.SA_HouseTotal = SA_HouseTotal;
            }

            public double getSA_HouseCash() {
                return SA_HouseCash;
            }

            public void setSA_HouseCash(double SA_HouseCash) {
                this.SA_HouseCash = SA_HouseCash;
            }

            public double getSA_HouseBalance() {
                return SA_HouseBalance;
            }

            public void setSA_HouseBalance(double SA_HouseBalance) {
                this.SA_HouseBalance = SA_HouseBalance;
            }

            public double getSA_HouseUnion() {
                return SA_HouseUnion;
            }

            public void setSA_HouseUnion(double SA_HouseUnion) {
                this.SA_HouseUnion = SA_HouseUnion;
            }

            public double getSA_HouseWeChatpay() {
                return SA_HouseWeChatpay;
            }

            public void setSA_HouseWeChatpay(double SA_HouseWeChatpay) {
                this.SA_HouseWeChatpay = SA_HouseWeChatpay;
            }

            public double getSA_HouseAlipaypay() {
                return SA_HouseAlipaypay;
            }

            public void setSA_HouseAlipaypay(double SA_HouseAlipaypay) {
                this.SA_HouseAlipaypay = SA_HouseAlipaypay;
            }

            public double getSA_HouseIntegral() {
                return SA_HouseIntegral;
            }

            public void setSA_HouseIntegral(double SA_HouseIntegral) {
                this.SA_HouseIntegral = SA_HouseIntegral;
            }

            public double getSA_HouseCoupon() {
                return SA_HouseCoupon;
            }

            public void setSA_HouseCoupon(double SA_HouseCoupon) {
                this.SA_HouseCoupon = SA_HouseCoupon;
            }

            public double getSA_HouseBarCode() {
                return SA_HouseBarCode;
            }

            public void setSA_HouseBarCode(double SA_HouseBarCode) {
                this.SA_HouseBarCode = SA_HouseBarCode;
            }

            public double getSA_HouseOtherpay() {
                return SA_HouseOtherpay;
            }

            public void setSA_HouseOtherpay(double SA_HouseOtherpay) {
                this.SA_HouseOtherpay = SA_HouseOtherpay;
            }

            public double getSA_HouseEraseZero() {
                return SA_HouseEraseZero;
            }

            public void setSA_HouseEraseZero(double SA_HouseEraseZero) {
                this.SA_HouseEraseZero = SA_HouseEraseZero;
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

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getSA_CreateTime() {
                return SA_CreateTime;
            }

            public void setSA_CreateTime(String SA_CreateTime) {
                this.SA_CreateTime = SA_CreateTime;
            }

            public int getSA_ExchangeState() {
                return SA_ExchangeState;
            }

            public void setSA_ExchangeState(int SA_ExchangeState) {
                this.SA_ExchangeState = SA_ExchangeState;
            }

            public double getSA_IssuedBusiness() {
                return SA_IssuedBusiness;
            }

            public void setSA_IssuedBusiness(double SA_IssuedBusiness) {
                this.SA_IssuedBusiness = SA_IssuedBusiness;
            }

            public double getSA_HandInBusiness() {
                return SA_HandInBusiness;
            }

            public void setSA_HandInBusiness(double SA_HandInBusiness) {
                this.SA_HandInBusiness = SA_HandInBusiness;
            }

            public Object getSA_ShiftDateTime() {
                return SA_ShiftDateTime;
            }

            public void setSA_ShiftDateTime(Object SA_ShiftDateTime) {
                this.SA_ShiftDateTime = SA_ShiftDateTime;
            }

            public Object getSU_GID() {
                return SU_GID;
            }

            public void setSU_GID(Object SU_GID) {
                this.SU_GID = SU_GID;
            }

            public String getSA_ShiftName() {
                return SA_ShiftName;
            }

            public void setSA_ShiftName(String SA_ShiftName) {
                this.SA_ShiftName = SA_ShiftName;
            }

            public String getSA_ShiftAccount() {
                return SA_ShiftAccount;
            }

            public void setSA_ShiftAccount(String SA_ShiftAccount) {
                this.SA_ShiftAccount = SA_ShiftAccount;
            }

            public Object getSA_SuccessionName() {
                return SA_SuccessionName;
            }

            public void setSA_SuccessionName(Object SA_SuccessionName) {
                this.SA_SuccessionName = SA_SuccessionName;
            }

            public Object getSA_SuccessionAccount() {
                return SA_SuccessionAccount;
            }

            public void setSA_SuccessionAccount(Object SA_SuccessionAccount) {
                this.SA_SuccessionAccount = SA_SuccessionAccount;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * MAI_EraseZeroChange : 0.0
             * MAI_OtherChange : 0.0
             * VIP_Card : null
             * VIP_Name : null
             * VIP_FaceNumber : null
             * VIP_Phone : null
             * SM_Name : null
             * PayCodeList : null
             * MAI_Remark : null
             * MAI_IncomeAmount : null
             * MAI_PayCode : null
             * MAI_OrderCode : null
             * SM_GID : null
             * GID : null
             * VIP_GID : null
             * MAI_IncomeTime : null
             * MAI_Explain : null
             * MAI_Identifying : 开卡费用
             * MAI_IdentifyingState : null
             * MAI_Creator : null
             * MAI_UpdateTime : null
             * CY_GID : null
             * MAI_BalanceAfterChange : null
             * MAI_CashChange : 0.0
             * MAI_BalanceChange : 0.0
             * MAI_UnionChange : 0.0
             * MAI_IntegralChange : 0.0
             * MAI_Integral : null
             * MAI_ShopName : null
             * UM_GID : null
             * MAI_WeChatPayChange : 0.0
             * MAI_AlipayPayChange : 0.0
             * MAI_CouponPayChange : 0.0
             * MAI_BarCodePayChange : 0.0
             * MAI_ReturnGoods : 0.0
             * MAI_Cancellation : null
             */

            private double MAI_EraseZeroChange;
            private double MAI_OtherChange;
            private Object VIP_Card;
            private Object VIP_Name;
            private Object VIP_FaceNumber;
            private Object VIP_Phone;
            private Object SM_Name;
            private Object PayCodeList;
            private Object MAI_Remark;
            private Object MAI_IncomeAmount;
            private Object MAI_PayCode;
            private Object MAI_OrderCode;
            private Object SM_GID;
            private Object GID;
            private Object VIP_GID;
            private Object MAI_IncomeTime;
            private Object MAI_Explain;
            private String MAI_Identifying;
            private Object MAI_IdentifyingState;
            private Object MAI_Creator;
            private Object MAI_UpdateTime;
            private Object CY_GID;
            private Object MAI_BalanceAfterChange;
            private double MAI_CashChange;
            private double MAI_BalanceChange;
            private double MAI_UnionChange;
            private double MAI_IntegralChange;
            private Object MAI_Integral;
            private Object MAI_ShopName;
            private Object UM_GID;
            private double MAI_WeChatPayChange;
            private double MAI_AlipayPayChange;
            private double MAI_CouponPayChange;
            private double MAI_BarCodePayChange;
            private double MAI_ReturnGoods;
            private Object MAI_Cancellation;
            private String Name;
            private double Total;

            public String getName() {
                return Name;
            }

            public void setName(String name) {
                Name = name;
            }

            public double getTotal() {
                return Total;
            }

            public void setTotal(double total) {
                Total = total;
            }

            public double getMAI_EraseZeroChange() {
                return MAI_EraseZeroChange;
            }

            public void setMAI_EraseZeroChange(double MAI_EraseZeroChange) {
                this.MAI_EraseZeroChange = MAI_EraseZeroChange;
            }

            public double getMAI_OtherChange() {
                return MAI_OtherChange;
            }

            public void setMAI_OtherChange(double MAI_OtherChange) {
                this.MAI_OtherChange = MAI_OtherChange;
            }

            public Object getVIP_Card() {
                return VIP_Card;
            }

            public void setVIP_Card(Object VIP_Card) {
                this.VIP_Card = VIP_Card;
            }

            public Object getVIP_Name() {
                return VIP_Name;
            }

            public void setVIP_Name(Object VIP_Name) {
                this.VIP_Name = VIP_Name;
            }

            public Object getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(Object VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public Object getVIP_Phone() {
                return VIP_Phone;
            }

            public void setVIP_Phone(Object VIP_Phone) {
                this.VIP_Phone = VIP_Phone;
            }

            public Object getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(Object SM_Name) {
                this.SM_Name = SM_Name;
            }

            public Object getPayCodeList() {
                return PayCodeList;
            }

            public void setPayCodeList(Object PayCodeList) {
                this.PayCodeList = PayCodeList;
            }

            public Object getMAI_Remark() {
                return MAI_Remark;
            }

            public void setMAI_Remark(Object MAI_Remark) {
                this.MAI_Remark = MAI_Remark;
            }

            public Object getMAI_IncomeAmount() {
                return MAI_IncomeAmount;
            }

            public void setMAI_IncomeAmount(Object MAI_IncomeAmount) {
                this.MAI_IncomeAmount = MAI_IncomeAmount;
            }

            public Object getMAI_PayCode() {
                return MAI_PayCode;
            }

            public void setMAI_PayCode(Object MAI_PayCode) {
                this.MAI_PayCode = MAI_PayCode;
            }

            public Object getMAI_OrderCode() {
                return MAI_OrderCode;
            }

            public void setMAI_OrderCode(Object MAI_OrderCode) {
                this.MAI_OrderCode = MAI_OrderCode;
            }

            public Object getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(Object SM_GID) {
                this.SM_GID = SM_GID;
            }

            public Object getGID() {
                return GID;
            }

            public void setGID(Object GID) {
                this.GID = GID;
            }

            public Object getVIP_GID() {
                return VIP_GID;
            }

            public void setVIP_GID(Object VIP_GID) {
                this.VIP_GID = VIP_GID;
            }

            public Object getMAI_IncomeTime() {
                return MAI_IncomeTime;
            }

            public void setMAI_IncomeTime(Object MAI_IncomeTime) {
                this.MAI_IncomeTime = MAI_IncomeTime;
            }

            public Object getMAI_Explain() {
                return MAI_Explain;
            }

            public void setMAI_Explain(Object MAI_Explain) {
                this.MAI_Explain = MAI_Explain;
            }

            public String getMAI_Identifying() {
                return MAI_Identifying;
            }

            public void setMAI_Identifying(String MAI_Identifying) {
                this.MAI_Identifying = MAI_Identifying;
            }

            public Object getMAI_IdentifyingState() {
                return MAI_IdentifyingState;
            }

            public void setMAI_IdentifyingState(Object MAI_IdentifyingState) {
                this.MAI_IdentifyingState = MAI_IdentifyingState;
            }

            public Object getMAI_Creator() {
                return MAI_Creator;
            }

            public void setMAI_Creator(Object MAI_Creator) {
                this.MAI_Creator = MAI_Creator;
            }

            public Object getMAI_UpdateTime() {
                return MAI_UpdateTime;
            }

            public void setMAI_UpdateTime(Object MAI_UpdateTime) {
                this.MAI_UpdateTime = MAI_UpdateTime;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }

            public Object getMAI_BalanceAfterChange() {
                return MAI_BalanceAfterChange;
            }

            public void setMAI_BalanceAfterChange(Object MAI_BalanceAfterChange) {
                this.MAI_BalanceAfterChange = MAI_BalanceAfterChange;
            }

            public double getMAI_CashChange() {
                return MAI_CashChange;
            }

            public void setMAI_CashChange(double MAI_CashChange) {
                this.MAI_CashChange = MAI_CashChange;
            }

            public double getMAI_BalanceChange() {
                return MAI_BalanceChange;
            }

            public void setMAI_BalanceChange(double MAI_BalanceChange) {
                this.MAI_BalanceChange = MAI_BalanceChange;
            }

            public double getMAI_UnionChange() {
                return MAI_UnionChange;
            }

            public void setMAI_UnionChange(double MAI_UnionChange) {
                this.MAI_UnionChange = MAI_UnionChange;
            }

            public double getMAI_IntegralChange() {
                return MAI_IntegralChange;
            }

            public void setMAI_IntegralChange(double MAI_IntegralChange) {
                this.MAI_IntegralChange = MAI_IntegralChange;
            }

            public Object getMAI_Integral() {
                return MAI_Integral;
            }

            public void setMAI_Integral(Object MAI_Integral) {
                this.MAI_Integral = MAI_Integral;
            }

            public Object getMAI_ShopName() {
                return MAI_ShopName;
            }

            public void setMAI_ShopName(Object MAI_ShopName) {
                this.MAI_ShopName = MAI_ShopName;
            }

            public Object getUM_GID() {
                return UM_GID;
            }

            public void setUM_GID(Object UM_GID) {
                this.UM_GID = UM_GID;
            }

            public double getMAI_WeChatPayChange() {
                return MAI_WeChatPayChange;
            }

            public void setMAI_WeChatPayChange(double MAI_WeChatPayChange) {
                this.MAI_WeChatPayChange = MAI_WeChatPayChange;
            }

            public double getMAI_AlipayPayChange() {
                return MAI_AlipayPayChange;
            }

            public void setMAI_AlipayPayChange(double MAI_AlipayPayChange) {
                this.MAI_AlipayPayChange = MAI_AlipayPayChange;
            }

            public double getMAI_CouponPayChange() {
                return MAI_CouponPayChange;
            }

            public void setMAI_CouponPayChange(double MAI_CouponPayChange) {
                this.MAI_CouponPayChange = MAI_CouponPayChange;
            }

            public double getMAI_BarCodePayChange() {
                return MAI_BarCodePayChange;
            }

            public void setMAI_BarCodePayChange(double MAI_BarCodePayChange) {
                this.MAI_BarCodePayChange = MAI_BarCodePayChange;
            }

            public double getMAI_ReturnGoods() {
                return MAI_ReturnGoods;
            }

            public void setMAI_ReturnGoods(double MAI_ReturnGoods) {
                this.MAI_ReturnGoods = MAI_ReturnGoods;
            }

            public Object getMAI_Cancellation() {
                return MAI_Cancellation;
            }

            public void setMAI_Cancellation(Object MAI_Cancellation) {
                this.MAI_Cancellation = MAI_Cancellation;
            }
        }
    }
}
