package com.zhiluo.android.yunpu.print.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/8/24 18:25.
 * 邮箱：137615198@qq.com
 */

public class PrintSetBean implements Serializable{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PS_GID":"91f0fd48-e315-4e48-b1ec-941f7afc5631","PS_CYGID":"bf8d141a-aa81-4679-bede-af9b92a867e1","PS_SMGID":"38771b02-7c22-41db-9621-497489484b74","PS_IsEnabled":1,"PS_IsPreview":1,"PS_PaperType":2,"PS_PrintTimes":"[{\"PT_Code\":\"HYCZ\",\"PT_Times\":1},{\"PT_Code\":\"HYCC\",\"PT_Times\":1},{\"PT_Code\":\"SPXF\",\"PT_Times\":1},{\"PT_Code\":\"KSXF\",\"PT_Times\":1},{\"PT_Code\":\"JCXF\",\"PT_Times\":1},{\"PT_Code\":\"JFDH\",\"PT_Times\":1},{\"PT_Code\":\"SPTH\",\"PT_Times\":1},{\"PT_Code\":\"JB\",\"PT_Times\":1}]","PS_PrinterName":"","PrintTimesList":[{"PT_Code":"HYCZ","PT_Times":1},{"PT_Code":"HYCC","PT_Times":1},{"PT_Code":"SPXF","PT_Times":1},{"PT_Code":"KSXF","PT_Times":1},{"PT_Code":"JCXF","PT_Times":1},{"PT_Code":"JFDH","PT_Times":1},{"PT_Code":"SPTH","PT_Times":1},{"PT_Code":"JB","PT_Times":1}]}
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
         * PS_GID : 91f0fd48-e315-4e48-b1ec-941f7afc5631
         * PS_CYGID : bf8d141a-aa81-4679-bede-af9b92a867e1
         * PS_SMGID : 38771b02-7c22-41db-9621-497489484b74
         * PS_IsEnabled : 1
         * PS_IsPreview : 1
         * PS_PaperType : 2
         * PS_PrintTimes : [{"PT_Code":"HYCZ","PT_Times":1},{"PT_Code":"HYCC","PT_Times":1},{"PT_Code":"SPXF","PT_Times":1},{"PT_Code":"KSXF","PT_Times":1},{"PT_Code":"JCXF","PT_Times":1},{"PT_Code":"JFDH","PT_Times":1},{"PT_Code":"SPTH","PT_Times":1},{"PT_Code":"JB","PT_Times":1}]
         * PS_PrinterName :
         * PrintTimesList : [{"PT_Code":"HYCZ","PT_Times":1},{"PT_Code":"HYCC","PT_Times":1},{"PT_Code":"SPXF","PT_Times":1},{"PT_Code":"KSXF","PT_Times":1},{"PT_Code":"JCXF","PT_Times":1},{"PT_Code":"JFDH","PT_Times":1},{"PT_Code":"SPTH","PT_Times":1},{"PT_Code":"JB","PT_Times":1}]
         */

        private String PS_GID;
        private String PS_CYGID;
        private String PS_SMGID;
        private int PS_IsEnabled;
        private int PS_IsPreview;
        private int PS_PaperType;
        private String PS_PrintTimes;
        private String PS_PrinterName;
        private String PS_StylusPrintingName;
        private List<PrintTimesListBean> PrintTimesList;

        public String getPS_StylusPrintingName() {
            return PS_StylusPrintingName;
        }

        public void setPS_StylusPrintingName(String PS_StylusPrintingName) {
            this.PS_StylusPrintingName = PS_StylusPrintingName;
        }

        public String getPS_GID() {
            return PS_GID;
        }

        public void setPS_GID(String PS_GID) {
            this.PS_GID = PS_GID;
        }

        public String getPS_CYGID() {
            return PS_CYGID;
        }

        public void setPS_CYGID(String PS_CYGID) {
            this.PS_CYGID = PS_CYGID;
        }

        public String getPS_SMGID() {
            return PS_SMGID;
        }

        public void setPS_SMGID(String PS_SMGID) {
            this.PS_SMGID = PS_SMGID;
        }

        public int getPS_IsEnabled() {
            return PS_IsEnabled;
        }

        public void setPS_IsEnabled(int PS_IsEnabled) {
            this.PS_IsEnabled = PS_IsEnabled;
        }

        public int getPS_IsPreview() {
            return PS_IsPreview;
        }

        public void setPS_IsPreview(int PS_IsPreview) {
            this.PS_IsPreview = PS_IsPreview;
        }

        public int getPS_PaperType() {
            return PS_PaperType;
        }

        public void setPS_PaperType(int PS_PaperType) {
            this.PS_PaperType = PS_PaperType;
        }

        public String getPS_PrintTimes() {
            return PS_PrintTimes;
        }

        public void setPS_PrintTimes(String PS_PrintTimes) {
            this.PS_PrintTimes = PS_PrintTimes;
        }

        public String getPS_PrinterName() {
            return PS_PrinterName;
        }

        public void setPS_PrinterName(String PS_PrinterName) {
            this.PS_PrinterName = PS_PrinterName;
        }

        public List<PrintTimesListBean> getPrintTimesList() {
            return PrintTimesList;
        }

        public void setPrintTimesList(List<PrintTimesListBean> PrintTimesList) {
            this.PrintTimesList = PrintTimesList;
        }

        public static class PrintTimesListBean implements Serializable{
            /**
             * PT_Code : HYCZ
             * PT_Times : 1
             */

            private String PT_Code;
            private int PT_Times;

            public String getPT_Code() {
                return PT_Code;
            }

            public void setPT_Code(String PT_Code) {
                this.PT_Code = PT_Code;
            }

            public int getPT_Times() {
                return PT_Times;
            }

            public void setPT_Times(int PT_Times) {
                this.PT_Times = PT_Times;
            }
        }
    }
}
