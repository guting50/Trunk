package com.zhiluo.android.yunpu.home.jsonbean;

/**
 * iauthor：Cheng
 * date: 2017/5/17 10:50
 * email：jasoncheng9111@gmail.com
 */
public class UpdateJsonBean
{

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"GID":"126e4892-1fd4-4222-a920-46c5c09ee3fe","VA_VersionNumber":"31","VA_Version":"1.31","VA_VersionAddress":"/Upload/AppReduceFile/964291256.apk","VA_Remark":"测试
     * 测试  测试  测试","VA_Time":"2017-05-17 10:41:45","VA_Number":0}
     */

    private boolean success;
    private Object code;
    private String msg;
    private DataBean data;

    public boolean isSuccess() { return success;}

    public void setSuccess(boolean success) { this.success = success;}

    public Object getCode() { return code;}

    public void setCode(Object code) { this.code = code;}

    public String getMsg() { return msg;}

    public void setMsg(String msg) { this.msg = msg;}

    public DataBean getData() { return data;}

    public void setData(DataBean data) { this.data = data;}

    public static class DataBean
    {
        /**
         * GID : 126e4892-1fd4-4222-a920-46c5c09ee3fe
         * VA_VersionNumber : 31
         * VA_Version : 1.31
         * VA_VersionAddress : /Upload/AppReduceFile/964291256.apk
         * VA_Remark : 测试 测试  测试  测试
         * VA_Time : 2017-05-17 10:41:45
         * VA_Number : 0
         */

        private String GID;
        private String VA_VersionNumber;
        private String VA_Version;
        private String VA_VersionAddress;
        private String VA_Remark;
        private String VA_Time;
        private int VA_Number;
        private int VA_UpdateMechanism;//0自动升级，1手动升级

        public int getVA_UpdateMechanism() {
            return VA_UpdateMechanism;
        }

        public void setVA_UpdateMechanism(int VA_UpdateMechanism) {
            this.VA_UpdateMechanism = VA_UpdateMechanism;
        }

        public String getGID() { return GID;}

        public void setGID(String GID) { this.GID = GID;}

        public String getVA_VersionNumber() { return VA_VersionNumber;}

        public void setVA_VersionNumber(String VA_VersionNumber) { this.VA_VersionNumber = VA_VersionNumber;}

        public String getVA_Version() { return VA_Version;}

        public void setVA_Version(String VA_Version) { this.VA_Version = VA_Version;}

        public String getVA_VersionAddress() { return VA_VersionAddress;}

        public void setVA_VersionAddress(String VA_VersionAddress) { this.VA_VersionAddress = VA_VersionAddress;}

        public String getVA_Remark() { return VA_Remark;}

        public void setVA_Remark(String VA_Remark) { this.VA_Remark = VA_Remark;}

        public String getVA_Time() { return VA_Time;}

        public void setVA_Time(String VA_Time) { this.VA_Time = VA_Time;}

        public int getVA_Number() { return VA_Number;}

        public void setVA_Number(int VA_Number) { this.VA_Number = VA_Number;}
    }
}
