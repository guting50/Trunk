package com.zhiluo.android.yunpu.home.jsonbean;

import java.io.Serializable;

/**
 * Created by ZPH on 2019-01-18.
 */

public class ShopInfoDataBean  implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"ShopImg":"/img/head.png","ShopName":"jll","ShopContact":"6545646","ShopTel":"18328578333","ShopType":"高级版(5年)","ShopMbers":"221/无限制","ShopGoods":"96/无限制","ShopUsers":"4/10","ShopOverTime":"2023-11-19","ShopCreateTime":"2018-06-29","ShopMaxUsers":10}
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
         * ShopImg : /img/head.png
         * ShopName : jll
         * ShopContact : 6545646
         * ShopTel : 18328578333
         * ShopType : 高级版(5年)
         * ShopMbers : 221/无限制
         * ShopGoods : 96/无限制
         * ShopUsers : 4/10
         * ShopOverTime : 2023-11-19
         * ShopCreateTime : 2018-06-29
         * ShopMaxUsers : 10
         */
        private String GID;
        private String ShopImg;
        private String ShopName;
        private String ShopContact;
        private String ShopTel;
        private String ShopType;
        private String ShopMbers;
        private String ShopGoods;
        private String ShopUsers;
        private String ShopOverTime;
        private String ShopCreateTime;
        private int ShopMaxUsers;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getShopImg() {
            return ShopImg;
        }

        public void setShopImg(String ShopImg) {
            this.ShopImg = ShopImg;
        }

        public String getShopName() {
            return ShopName;
        }

        public void setShopName(String ShopName) {
            this.ShopName = ShopName;
        }

        public String getShopContact() {
            return ShopContact;
        }

        public void setShopContact(String ShopContact) {
            this.ShopContact = ShopContact;
        }

        public String getShopTel() {
            return ShopTel;
        }

        public void setShopTel(String ShopTel) {
            this.ShopTel = ShopTel;
        }

        public String getShopType() {
            return ShopType;
        }

        public void setShopType(String ShopType) {
            this.ShopType = ShopType;
        }

        public String getShopMbers() {
            return ShopMbers;
        }

        public void setShopMbers(String ShopMbers) {
            this.ShopMbers = ShopMbers;
        }

        public String getShopGoods() {
            return ShopGoods;
        }

        public void setShopGoods(String ShopGoods) {
            this.ShopGoods = ShopGoods;
        }

        public String getShopUsers() {
            return ShopUsers;
        }

        public void setShopUsers(String ShopUsers) {
            this.ShopUsers = ShopUsers;
        }

        public String getShopOverTime() {
            return ShopOverTime;
        }

        public void setShopOverTime(String ShopOverTime) {
            this.ShopOverTime = ShopOverTime;
        }

        public String getShopCreateTime() {
            return ShopCreateTime;
        }

        public void setShopCreateTime(String ShopCreateTime) {
            this.ShopCreateTime = ShopCreateTime;
        }

        public int getShopMaxUsers() {
            return ShopMaxUsers;
        }

        public void setShopMaxUsers(int ShopMaxUsers) {
            this.ShopMaxUsers = ShopMaxUsers;
        }
    }
}
