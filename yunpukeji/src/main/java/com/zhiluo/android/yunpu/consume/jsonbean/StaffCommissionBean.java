package com.zhiluo.android.yunpu.consume.jsonbean;

import java.io.Serializable;
import java.util.List;

/**
 * 员工JavaBean
 * 作者：罗咏哲 on 2017/7/6 18:02.
 * 邮箱：137615198@qq.com
 */

public class StaffCommissionBean implements Serializable{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"412be354-c89c-499e-85f2-867af975a2a4","SM_GID":"","CY_GID":null,"DM_GID":"8e0468c9-eff7-4013-960c-85328afaa934","EM_Code":"","EM_Name":"小陈","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2017-07-06 17:13:20","EM_Creator":"137615198@qq.com","EM_Birthday":null,"SM_Name":null,"DM_Name":"导购","EM_TipCard":1,"EM_TipRecharge":0,"EM_TipChargeTime":0,"EM_TipGoodsConsume":1,"EM_TipFastConsume":0,"EM_TipTimesConsume":0},{"GID":"6b060dc1-f488-49ed-b176-3c43c389450b","SM_GID":"","CY_GID":null,"DM_GID":"8e0468c9-eff7-4013-960c-85328afaa934","EM_Code":"","EM_Name":"小刘","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2017-07-06 17:12:59","EM_Creator":"137615198@qq.com","EM_Birthday":null,"SM_Name":null,"DM_Name":"导购","EM_TipCard":0,"EM_TipRecharge":0,"EM_TipChargeTime":0,"EM_TipGoodsConsume":1,"EM_TipFastConsume":1,"EM_TipTimesConsume":0},{"GID":"6ed7959b-c967-4b71-8381-3ec1a3a6ff6b","SM_GID":"","CY_GID":null,"DM_GID":"df5e1a29-a050-4313-9df3-074680da9fcb","EM_Code":"","EM_Name":"小李","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2017-07-06 17:12:40","EM_Creator":"137615198@qq.com","EM_Birthday":null,"SM_Name":null,"DM_Name":"前台","EM_TipCard":1,"EM_TipRecharge":1,"EM_TipChargeTime":1,"EM_TipGoodsConsume":1,"EM_TipFastConsume":0,"EM_TipTimesConsume":0},{"GID":"ce727af5-8bc4-467d-ae70-85c4fbe92fd4","SM_GID":"","CY_GID":null,"DM_GID":"df5e1a29-a050-4313-9df3-074680da9fcb","EM_Code":"","EM_Name":"小王","EM_Sex":0,"EM_Phone":"","EM_Addr":"","EM_Remark":"","EM_UpdateTime":"2017-07-06 17:12:21","EM_Creator":"137615198@qq.com","EM_Birthday":null,"SM_Name":null,"DM_Name":"前台","EM_TipCard":1,"EM_TipRecharge":1,"EM_TipChargeTime":1,"EM_TipGoodsConsume":1,"EM_TipFastConsume":1,"EM_TipTimesConsume":1}]
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

    public static class DataBean implements Serializable{
        /**
         * GID : 412be354-c89c-499e-85f2-867af975a2a4
         * SM_GID :
         * CY_GID : null
         * DM_GID : 8e0468c9-eff7-4013-960c-85328afaa934
         * EM_Code :
         * EM_Name : 小陈
         * EM_Sex : 0
         * EM_Phone :
         * EM_Addr :
         * EM_Remark :
         * EM_UpdateTime : 2017-07-06 17:13:20
         * EM_Creator : 137615198@qq.com
         * EM_Birthday : null
         * SM_Name : null
         * DM_Name : 导购
         * EM_TipCard : 1
         * EM_TipRecharge : 0
         * EM_TipChargeTime : 0
         * EM_TipGoodsConsume : 1
         * EM_TipFastConsume : 0
         * EM_TipTimesConsume : 0
         */

        private String GID;
        private String SM_GID;
        private Object CY_GID;
        private String DM_GID;
        private String EM_Code;
        private String EM_Name;
        private int EM_Sex;
        private String EM_Phone;
        private String EM_Addr;
        private String EM_Remark;
        private String EM_UpdateTime;
        private String EM_Creator;
        private Object EM_Birthday;
        private Object SM_Name;
        private String DM_Name;
        private int EM_TipCard;//售卡提成
        private int EM_TipRecharge;//充值提成
        private int EM_TipChargeTime;//充次提成
        private int EM_TipGoodsConsume;//商品消费提成
        private int EM_TipFastConsume;//快速消费提成
        private int EM_TipTimesConsume;//计次消费提成
        private int EM_TipComboConsume;//套餐消费
        private boolean check;

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getSM_GID() {
            return SM_GID;
        }

        public void setSM_GID(String SM_GID) {
            this.SM_GID = SM_GID;
        }

        public Object getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(Object CY_GID) {
            this.CY_GID = CY_GID;
        }

        public String getDM_GID() {
            return DM_GID;
        }

        public void setDM_GID(String DM_GID) {
            this.DM_GID = DM_GID;
        }

        public String getEM_Code() {
            return EM_Code;
        }

        public void setEM_Code(String EM_Code) {
            this.EM_Code = EM_Code;
        }

        public String getEM_Name() {
            return EM_Name;
        }

        public void setEM_Name(String EM_Name) {
            this.EM_Name = EM_Name;
        }

        public int getEM_Sex() {
            return EM_Sex;
        }

        public void setEM_Sex(int EM_Sex) {
            this.EM_Sex = EM_Sex;
        }

        public String getEM_Phone() {
            return EM_Phone;
        }

        public void setEM_Phone(String EM_Phone) {
            this.EM_Phone = EM_Phone;
        }

        public String getEM_Addr() {
            return EM_Addr;
        }

        public void setEM_Addr(String EM_Addr) {
            this.EM_Addr = EM_Addr;
        }

        public String getEM_Remark() {
            return EM_Remark;
        }

        public void setEM_Remark(String EM_Remark) {
            this.EM_Remark = EM_Remark;
        }

        public String getEM_UpdateTime() {
            return EM_UpdateTime;
        }

        public void setEM_UpdateTime(String EM_UpdateTime) {
            this.EM_UpdateTime = EM_UpdateTime;
        }

        public String getEM_Creator() {
            return EM_Creator;
        }

        public void setEM_Creator(String EM_Creator) {
            this.EM_Creator = EM_Creator;
        }

        public Object getEM_Birthday() {
            return EM_Birthday;
        }

        public void setEM_Birthday(Object EM_Birthday) {
            this.EM_Birthday = EM_Birthday;
        }

        public Object getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(Object SM_Name) {
            this.SM_Name = SM_Name;
        }

        public String getDM_Name() {
            return DM_Name;
        }

        public void setDM_Name(String DM_Name) {
            this.DM_Name = DM_Name;
        }

        public int getEM_TipCard() {
            return EM_TipCard;
        }

        public void setEM_TipCard(int EM_TipCard) {
            this.EM_TipCard = EM_TipCard;
        }

        public int getEM_TipRecharge() {
            return EM_TipRecharge;
        }

        public void setEM_TipRecharge(int EM_TipRecharge) {
            this.EM_TipRecharge = EM_TipRecharge;
        }

        public int getEM_TipChargeTime() {
            return EM_TipChargeTime;
        }

        public void setEM_TipChargeTime(int EM_TipChargeTime) {
            this.EM_TipChargeTime = EM_TipChargeTime;
        }

        public int getEM_TipGoodsConsume() {
            return EM_TipGoodsConsume;
        }

        public void setEM_TipGoodsConsume(int EM_TipGoodsConsume) {
            this.EM_TipGoodsConsume = EM_TipGoodsConsume;
        }

        public int getEM_TipFastConsume() {
            return EM_TipFastConsume;
        }

        public void setEM_TipFastConsume(int EM_TipFastConsume) {
            this.EM_TipFastConsume = EM_TipFastConsume;
        }

        public int getEM_TipTimesConsume() {
            return EM_TipTimesConsume;
        }

        public void setEM_TipTimesConsume(int EM_TipTimesConsume) {
            this.EM_TipTimesConsume = EM_TipTimesConsume;
        }

        public int getEM_TipComboConsume() {
            return EM_TipComboConsume;
        }

        public void setEM_TipComboConsume(int EM_TipComboConsume) {
            this.EM_TipComboConsume = EM_TipComboConsume;
        }
    }
}
