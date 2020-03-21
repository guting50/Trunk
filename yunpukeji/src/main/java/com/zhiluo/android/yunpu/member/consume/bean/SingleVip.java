package com.zhiluo.android.yunpu.member.consume.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${YSL} on 2018-05-25.
 * 在会员充次的时候查询单个vip信息时用，其它地方占时还没有用到，
 */

public class SingleVip implements Serializable{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GID":"3b958ebd-d27a-4bdd-a200-c7bebc55478f","VIP_RegSource":null,"EM_Name":"","VIP_HeadImg":"http://img.yunvip123.com/CommonImg/f7efefcb-54a4-474a-9315-2a4b0c5b4d4b.png","VCH_Card":"123","VIP_Name":"张飞","VIP_Sex":0,"VCH_CreateTime":"2018-03-13 15:04:58","VIP_Birthday":"2018-03-13 00:00:00","VIP_CellPhone":"18381306468","VIP_ICCard":"510823199205117037","VIP_Email":"875111437@qq.com","VIP_Remark":"","VIP_IsForver":1,"VIP_Overdue":null,"VIP_State":0,"VIP_FaceNumber":"","VIP_Label":"[]","VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","EM_ID":"","VIP_Referee":"","VIP_Addr":"中国四川","VIP_FixedPhone":"628304","VIP_Creator":"18381306468","SM_Name":"云上铺1号店","SM_GID":null,"VCH_Fee":0,"VIP_OpenID":null,"VG_Name":"一级","VG_IsAccount":1,"VG_IsIntegral":1,"VG_IsDiscount":1,"VG_IsCount":1,"VG_IsTime":0,"DS_Value":1,"VS_Value":0,"RS_Value":1,"VGInfo":[{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"5d798406-2996-4eeb-aa79-20b211bc75db","PT_Name":"嗯","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"c3cec91a-ea35-4778-850c-b9a5a1ca2ffb","PT_Name":"！？","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"529e98b6-fb7f-452a-be37-3629a1b78758","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"4784f18f-ff62-485f-b597-1315e893845b","PT_Name":"1号","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"fbf030de-19e9-47b8-9843-07fef4aa1ff3","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"7f4592b2-f65c-450a-bc54-bc0330114300","PT_Name":"，","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"ad24bcb8-d0b9-4833-b8ec-8233d8f8c0d4","PT_Name":"饿了来咯","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"529e98b6-fb7f-452a-be37-3629a1b78758","PT_SynType":"1"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"5a71e610-963b-4627-975c-aa3205f5bf37","PT_Name":"笔呦提否","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"5d798406-2996-4eeb-aa79-20b211bc75db","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"18b8d0c2-dc08-434e-a58b-32dcdb269700","PT_Name":"噢","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"fbf030de-19e9-47b8-9843-07fef4aa1ff3","PT_Name":"导弹","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"82cb1245-38a7-4f28-9a69-a68c002b578c","PT_Name":"哈哈","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"ccc53713-777d-4847-abe3-2f8bc6975b6a","PT_Name":"呵呵","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","PT_Name":"，，","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"650d8572-ae55-42c3-a269-d63c850756df","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"529e98b6-fb7f-452a-be37-3629a1b78758","PT_Name":"？。。。。","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"1"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"e67acb7b-62b4-47e4-8930-2b8131cf6b75","PT_Name":"给你","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"930be979-c386-4514-b06b-3b9845c5c007","PT_Name":"搜狗","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"77a8700f-13f5-4753-bdc7-d26c10ea081d","PT_Name":"yttt","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"650d8572-ae55-42c3-a269-d63c850756df","PT_Name":"哈塞给","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"}],"CustomeFieldList":null,"MA_AvailableBalance":2399,"MA_AggregateAmount":473944,"MA_AggregateStoredValue":2412,"MA_AvailableIntegral":965,"MCA_HowMany":0,"MCA_TotalCharge":0,"MA_UpdateTime":"2018-05-25 14:59:39","CouponsList":[],"MessageVIP":null}
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
         * GID : 3b958ebd-d27a-4bdd-a200-c7bebc55478f
         * VIP_RegSource : null
         * EM_Name :
         * VIP_HeadImg : http://img.yunvip123.com/CommonImg/f7efefcb-54a4-474a-9315-2a4b0c5b4d4b.png
         * VCH_Card : 123
         * VIP_Name : 张飞
         * VIP_Sex : 0
         * VCH_CreateTime : 2018-03-13 15:04:58
         * VIP_Birthday : 2018-03-13 00:00:00
         * VIP_CellPhone : 18381306468
         * VIP_ICCard : 510823199205117037
         * VIP_Email : 875111437@qq.com
         * VIP_Remark :
         * VIP_IsForver : 1
         * VIP_Overdue : null
         * VIP_State : 0
         * VIP_FaceNumber :
         * VIP_Label : []
         * VG_GID : 533067a6-e9f5-4d95-86f2-62edd8050bc7
         * EM_ID :
         * VIP_Referee :
         * VIP_Addr : 中国四川
         * VIP_FixedPhone : 628304
         * VIP_Creator : 18381306468
         * SM_Name : 云上铺1号店
         * SM_GID : null
         * VCH_Fee : 0
         * VIP_OpenID : null
         * VG_Name : 一级
         * VG_IsAccount : 1
         * VG_IsIntegral : 1
         * VG_IsDiscount : 1
         * VG_IsCount : 1
         * VG_IsTime : 0
         * DS_Value : 1.0
         * VS_Value : 0.0
         * RS_Value : 1.0
         * VGInfo : [{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"5d798406-2996-4eeb-aa79-20b211bc75db","PT_Name":"嗯","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"c3cec91a-ea35-4778-850c-b9a5a1ca2ffb","PT_Name":"！？","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"529e98b6-fb7f-452a-be37-3629a1b78758","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"4784f18f-ff62-485f-b597-1315e893845b","PT_Name":"1号","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"fbf030de-19e9-47b8-9843-07fef4aa1ff3","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"7f4592b2-f65c-450a-bc54-bc0330114300","PT_Name":"，","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"ad24bcb8-d0b9-4833-b8ec-8233d8f8c0d4","PT_Name":"饿了来咯","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"529e98b6-fb7f-452a-be37-3629a1b78758","PT_SynType":"1"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"5a71e610-963b-4627-975c-aa3205f5bf37","PT_Name":"笔呦提否","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"5d798406-2996-4eeb-aa79-20b211bc75db","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"18b8d0c2-dc08-434e-a58b-32dcdb269700","PT_Name":"噢","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"fbf030de-19e9-47b8-9843-07fef4aa1ff3","PT_Name":"导弹","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"82cb1245-38a7-4f28-9a69-a68c002b578c","PT_Name":"哈哈","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"ccc53713-777d-4847-abe3-2f8bc6975b6a","PT_Name":"呵呵","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"b9fe359f-e29c-4bf9-9344-b0b9cfaa713f","PT_Name":"，，","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"650d8572-ae55-42c3-a269-d63c850756df","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"529e98b6-fb7f-452a-be37-3629a1b78758","PT_Name":"？。。。。","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"1"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"e67acb7b-62b4-47e4-8930-2b8131cf6b75","PT_Name":"给你","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"930be979-c386-4514-b06b-3b9845c5c007","PT_Name":"搜狗","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"77a8700f-13f5-4753-bdc7-d26c10ea081d","PT_Name":"yttt","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"},{"VG_GID":"533067a6-e9f5-4d95-86f2-62edd8050bc7","VG_Name":"一级","PT_GID":"650d8572-ae55-42c3-a269-d63c850756df","PT_Name":"哈塞给","PT_Type":"商品","PD_Discount":100,"VS_CMoney":1,"VS_Number":0,"SM_GID":"966d986b-0caa-465b-bbe1-8bd871bf8a0a","SM_Name":"云上铺1号店","PT_Parent":"","PT_SynType":"0"}]
         * CustomeFieldList : null
         * MA_AvailableBalance : 2399.0
         * MA_AggregateAmount : 473944.0
         * MA_AggregateStoredValue : 2412.0
         * MA_AvailableIntegral : 965.0
         * MCA_HowMany : 0
         * MCA_TotalCharge : 0
         * MA_UpdateTime : 2018-05-25 14:59:39
         * CouponsList : []
         * MessageVIP : null
         */

        private String GID;
        private Object VIP_RegSource;
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
        private int VIP_IsForver;
        private Object VIP_Overdue;
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
        private Object SM_GID;
        private int VCH_Fee;
        private Object VIP_OpenID;
        private String VG_Name;
        private int VG_IsAccount;
        private int VG_IsIntegral;
        private int VG_IsDiscount;
        private int VG_IsCount;
        private int VG_IsTime;
        private double DS_Value;
        private double VS_Value;
        private double RS_Value;
        private Object CustomeFieldList;
        private double MA_AvailableBalance;
        private double MA_AggregateAmount;
        private double MA_AggregateStoredValue;
        private double MA_AvailableIntegral;
        private int MCA_HowMany;
        private int MCA_TotalCharge;
        private String MA_UpdateTime;
        private Object MessageVIP;
        private List<VGInfoBean> VGInfo;
        private List<?> CouponsList;
        private double VG_OilIntegral;

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

        public Object getVIP_RegSource() {
            return VIP_RegSource;
        }

        public void setVIP_RegSource(Object VIP_RegSource) {
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

        public int getVIP_IsForver() {
            return VIP_IsForver;
        }

        public void setVIP_IsForver(int VIP_IsForver) {
            this.VIP_IsForver = VIP_IsForver;
        }

        public Object getVIP_Overdue() {
            return VIP_Overdue;
        }

        public void setVIP_Overdue(Object VIP_Overdue) {
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

        public String getEM_ID() {
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

        public Object getSM_GID() {
            return SM_GID;
        }

        public void setSM_GID(Object SM_GID) {
            this.SM_GID = SM_GID;
        }

        public int getVCH_Fee() {
            return VCH_Fee;
        }

        public void setVCH_Fee(int VCH_Fee) {
            this.VCH_Fee = VCH_Fee;
        }

        public Object getVIP_OpenID() {
            return VIP_OpenID;
        }

        public void setVIP_OpenID(Object VIP_OpenID) {
            this.VIP_OpenID = VIP_OpenID;
        }

        public String getVG_Name() {
            return VG_Name;
        }

        public void setVG_Name(String VG_Name) {
            this.VG_Name = VG_Name;
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

        public Object getCustomeFieldList() {
            return CustomeFieldList;
        }

        public void setCustomeFieldList(Object CustomeFieldList) {
            this.CustomeFieldList = CustomeFieldList;
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

        public Object getMessageVIP() {
            return MessageVIP;
        }

        public void setMessageVIP(Object MessageVIP) {
            this.MessageVIP = MessageVIP;
        }

        public List<VGInfoBean> getVGInfo() {
            return VGInfo;
        }

        public void setVGInfo(List<VGInfoBean> VGInfo) {
            this.VGInfo = VGInfo;
        }

        public List<?> getCouponsList() {
            return CouponsList;
        }

        public void setCouponsList(List<?> CouponsList) {
            this.CouponsList = CouponsList;
        }

        public static class VGInfoBean implements Serializable{
            /**
             * VG_GID : 533067a6-e9f5-4d95-86f2-62edd8050bc7
             * VG_Name : 一级
             * PT_GID : 5d798406-2996-4eeb-aa79-20b211bc75db
             * PT_Name : 嗯
             * PT_Type : 商品
             * PD_Discount : 100
             * VS_CMoney : 1.0
             * VS_Number : 0.0
             * SM_GID : 966d986b-0caa-465b-bbe1-8bd871bf8a0a
             * SM_Name : 云上铺1号店
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
    }
}
