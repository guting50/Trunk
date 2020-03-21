package com.zhiluo.android.yunpu.sms.jsonbean;

/**
 * 短信条数
 */

public class SMSNumBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"_id":"58e7645c6f90fc13d09f998a","CY_GID":"61ec52b1-6da8-415a-b66a-9cec063bda5d","CYCode":null,"CYName":null,"Storage":4841,"UStorage":4841,"FStorage":0,"ChangeDateUtc":1.517738589617E12,"ChangeDateLocal":"2018-02-04 18:03:09"}
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
         * _id : 58e7645c6f90fc13d09f998a
         * CY_GID : 61ec52b1-6da8-415a-b66a-9cec063bda5d
         * CYCode : null
         * CYName : null
         * Storage : 4841
         * UStorage : 4841
         * FStorage : 0
         * ChangeDateUtc : 1.517738589617E12
         * ChangeDateLocal : 2018-02-04 18:03:09
         */

        private String _id;
        private String CY_GID;
        private Object CYCode;
        private Object CYName;
        private int Storage;
        private int UStorage;
        private int FStorage;
        private double ChangeDateUtc;
        private String ChangeDateLocal;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public Object getCYCode() {
            return CYCode;
        }

        public void setCYCode(Object CYCode) {
            this.CYCode = CYCode;
        }

        public Object getCYName() {
            return CYName;
        }

        public void setCYName(Object CYName) {
            this.CYName = CYName;
        }

        public int getStorage() {
            return Storage;
        }

        public void setStorage(int Storage) {
            this.Storage = Storage;
        }

        public int getUStorage() {
            return UStorage;
        }

        public void setUStorage(int UStorage) {
            this.UStorage = UStorage;
        }

        public int getFStorage() {
            return FStorage;
        }

        public void setFStorage(int FStorage) {
            this.FStorage = FStorage;
        }

        public double getChangeDateUtc() {
            return ChangeDateUtc;
        }

        public void setChangeDateUtc(double ChangeDateUtc) {
            this.ChangeDateUtc = ChangeDateUtc;
        }

        public String getChangeDateLocal() {
            return ChangeDateLocal;
        }

        public void setChangeDateLocal(String ChangeDateLocal) {
            this.ChangeDateLocal = ChangeDateLocal;
        }
    }
}
