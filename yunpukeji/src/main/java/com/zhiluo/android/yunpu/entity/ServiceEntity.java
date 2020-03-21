package com.zhiluo.android.yunpu.entity;

/**
 * 作者：罗咏哲 on 2017/11/25 11:37.
 * 邮箱：137615198@qq.com
 */

public class ServiceEntity {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"SU_Telephone":"071762112359","SU_QQCode":"1624904344","SU_WeChat":"11111111","SU_WeChatImageUrl":null}
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
         * SU_Telephone : 071762112359
         * SU_QQCode : 1624904344
         * SU_WeChat : 11111111
         * SU_WeChatImageUrl : null
         */

        private String SU_Telephone;
        private String SU_QQCode;
        private String SU_WeChat;
        private Object SU_WeChatImageUrl;

        public String getSU_Telephone() {
            return SU_Telephone;
        }

        public void setSU_Telephone(String SU_Telephone) {
            this.SU_Telephone = SU_Telephone;
        }

        public String getSU_QQCode() {
            return SU_QQCode;
        }

        public void setSU_QQCode(String SU_QQCode) {
            this.SU_QQCode = SU_QQCode;
        }

        public String getSU_WeChat() {
            return SU_WeChat;
        }

        public void setSU_WeChat(String SU_WeChat) {
            this.SU_WeChat = SU_WeChat;
        }

        public Object getSU_WeChatImageUrl() {
            return SU_WeChatImageUrl;
        }

        public void setSU_WeChatImageUrl(Object SU_WeChatImageUrl) {
            this.SU_WeChatImageUrl = SU_WeChatImageUrl;
        }
    }
}
