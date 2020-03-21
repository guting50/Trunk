package com.zhiluo.android.yunpu.home.jsonbean;

import java.util.List;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class TotalVipNum {
    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"month":null,"total":"19"}]
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

    public static class DataBean {
        /**
         * month : null
         * total : 19
         */

        private Object month;
        private String total;

        public Object getMonth() {
            return month;
        }

        public void setMonth(Object month) {
            this.month = month;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }
    }
}
