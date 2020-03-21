package com.zhiluo.android.yunpu.statistics.order.bean;

/**
 * Created by ZPH on 2019-04-08.
 */

public class GoodsOrderRetureBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"IsStockIn":true,"ReturnDetail":null,"GID":"1a8704a9-fc78-49d1-8433-3b5269304411","CO_GID":"b75c077f-8b97-4aa4-b977-9f3019cddd17","VIP_GID":"8d824808-1de5-4ca1-9869-d162e913e9a1","VIP_Card":"18338258258","VIP_Name":"来了","VIP_Phone":"18338258258","VIP_FaceNumber":"他特踏踏","CO_OrderCode":"SP1904040929296961","RO_ConsumeWay":"余额退款","RO_WayCode":"YEZF","RO_Monetary":14,"RO_TotalPrice":null,"RO_Integral":11,"RO_BalanceCard":1.11112766E7,"RO_Type":"商品消费","RO_TypeCode":"SPXF","RO_CommissionAmount":null,"RO_Remark":"","RO_Creator":"销售001","RO_UpdateTime":"2019-04-08 15:16:59","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SM_Contacter":"6545646","SM_Phone":"18328578333","SM_Addr":"","RO_Identifying":null,"RO_IdentifyingState":null,"ReturnType":1}
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

    public static class DataBean {
        /**
         * IsStockIn : true
         * ReturnDetail : null
         * GID : 1a8704a9-fc78-49d1-8433-3b5269304411
         * CO_GID : b75c077f-8b97-4aa4-b977-9f3019cddd17
         * VIP_GID : 8d824808-1de5-4ca1-9869-d162e913e9a1
         * VIP_Card : 18338258258
         * VIP_Name : 来了
         * VIP_Phone : 18338258258
         * VIP_FaceNumber : 他特踏踏
         * CO_OrderCode : SP1904040929296961
         * RO_ConsumeWay : 余额退款
         * RO_WayCode : YEZF
         * RO_Monetary : 14.0
         * RO_TotalPrice : null
         * RO_Integral : 11.0
         * RO_BalanceCard : 1.11112766E7
         * RO_Type : 商品消费
         * RO_TypeCode : SPXF
         * RO_CommissionAmount : null
         * RO_Remark :
         * RO_Creator : 销售001
         * RO_UpdateTime : 2019-04-08 15:16:59
         * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
         * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
         * SM_Name : jll2
         * SM_Contacter : 6545646
         * SM_Phone : 18328578333
         * SM_Addr :
         * RO_Identifying : null
         * RO_IdentifyingState : null
         * ReturnType : 1
         */

        private boolean IsStockIn;
        private Object ReturnDetail;
        private String GID;
        private String CO_GID;
        private String VIP_GID;
        private String VIP_Card;
        private String VIP_Name;
        private String VIP_Phone;
        private String VIP_FaceNumber;
        private String CO_OrderCode;
        private String RO_ConsumeWay;
        private String RO_WayCode;
        private double RO_Monetary;
        private Object RO_TotalPrice;
        private double RO_Integral;
        private double RO_BalanceCard;
        private String RO_Type;
        private String RO_TypeCode;
        private Object RO_CommissionAmount;
        private String RO_Remark;
        private String RO_Creator;
        private String RO_UpdateTime;
        private String CY_GID;
        private String SM_GID;
        private String SM_Name;
        private String SM_Contacter;
        private String SM_Phone;
        private String SM_Addr;
        private Object RO_Identifying;
        private Object RO_IdentifyingState;
        private int ReturnType;

        public boolean isIsStockIn() {
            return IsStockIn;
        }

        public void setIsStockIn(boolean IsStockIn) {
            this.IsStockIn = IsStockIn;
        }

        public Object getReturnDetail() {
            return ReturnDetail;
        }

        public void setReturnDetail(Object ReturnDetail) {
            this.ReturnDetail = ReturnDetail;
        }

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

        public String getCO_OrderCode() {
            return CO_OrderCode;
        }

        public void setCO_OrderCode(String CO_OrderCode) {
            this.CO_OrderCode = CO_OrderCode;
        }

        public String getRO_ConsumeWay() {
            return RO_ConsumeWay;
        }

        public void setRO_ConsumeWay(String RO_ConsumeWay) {
            this.RO_ConsumeWay = RO_ConsumeWay;
        }

        public String getRO_WayCode() {
            return RO_WayCode;
        }

        public void setRO_WayCode(String RO_WayCode) {
            this.RO_WayCode = RO_WayCode;
        }

        public double getRO_Monetary() {
            return RO_Monetary;
        }

        public void setRO_Monetary(double RO_Monetary) {
            this.RO_Monetary = RO_Monetary;
        }

        public Object getRO_TotalPrice() {
            return RO_TotalPrice;
        }

        public void setRO_TotalPrice(Object RO_TotalPrice) {
            this.RO_TotalPrice = RO_TotalPrice;
        }

        public double getRO_Integral() {
            return RO_Integral;
        }

        public void setRO_Integral(double RO_Integral) {
            this.RO_Integral = RO_Integral;
        }

        public double getRO_BalanceCard() {
            return RO_BalanceCard;
        }

        public void setRO_BalanceCard(double RO_BalanceCard) {
            this.RO_BalanceCard = RO_BalanceCard;
        }

        public String getRO_Type() {
            return RO_Type;
        }

        public void setRO_Type(String RO_Type) {
            this.RO_Type = RO_Type;
        }

        public String getRO_TypeCode() {
            return RO_TypeCode;
        }

        public void setRO_TypeCode(String RO_TypeCode) {
            this.RO_TypeCode = RO_TypeCode;
        }

        public Object getRO_CommissionAmount() {
            return RO_CommissionAmount;
        }

        public void setRO_CommissionAmount(Object RO_CommissionAmount) {
            this.RO_CommissionAmount = RO_CommissionAmount;
        }

        public String getRO_Remark() {
            return RO_Remark;
        }

        public void setRO_Remark(String RO_Remark) {
            this.RO_Remark = RO_Remark;
        }

        public String getRO_Creator() {
            return RO_Creator;
        }

        public void setRO_Creator(String RO_Creator) {
            this.RO_Creator = RO_Creator;
        }

        public String getRO_UpdateTime() {
            return RO_UpdateTime;
        }

        public void setRO_UpdateTime(String RO_UpdateTime) {
            this.RO_UpdateTime = RO_UpdateTime;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
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

        public String getSM_Contacter() {
            return SM_Contacter;
        }

        public void setSM_Contacter(String SM_Contacter) {
            this.SM_Contacter = SM_Contacter;
        }

        public String getSM_Phone() {
            return SM_Phone;
        }

        public void setSM_Phone(String SM_Phone) {
            this.SM_Phone = SM_Phone;
        }

        public String getSM_Addr() {
            return SM_Addr;
        }

        public void setSM_Addr(String SM_Addr) {
            this.SM_Addr = SM_Addr;
        }

        public Object getRO_Identifying() {
            return RO_Identifying;
        }

        public void setRO_Identifying(Object RO_Identifying) {
            this.RO_Identifying = RO_Identifying;
        }

        public Object getRO_IdentifyingState() {
            return RO_IdentifyingState;
        }

        public void setRO_IdentifyingState(Object RO_IdentifyingState) {
            this.RO_IdentifyingState = RO_IdentifyingState;
        }

        public int getReturnType() {
            return ReturnType;
        }

        public void setReturnType(int ReturnType) {
            this.ReturnType = ReturnType;
        }
    }
}
