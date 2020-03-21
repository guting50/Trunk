package com.zhiluo.android.yunpu.goods.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2019-04-17.
 */

public class GoodsModelListBean {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"543ed187-b93a-4dae-bcc6-9077a1182d47","PM_Name":"颜色","PM_Properties":"","PM_Type":0,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-12 14:51:35","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"},{"GID":"7ac59980-9edf-4bec-bd55-3b0448db949c","PM_Name":"颜色","PM_Properties":"红","PM_Type":1,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-12 14:51:43","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"},{"GID":"610f74ec-cd0d-4c33-bbf6-d8e48fc64c21","PM_Name":"颜色","PM_Properties":"黄","PM_Type":1,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-12 14:51:47","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"},{"GID":"6a423e59-668c-4912-afe0-58a590029dd0","PM_Name":"颜色","PM_Properties":"蓝","PM_Type":1,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-12 14:51:54","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"},{"GID":"c8fcb2cf-3ccf-45f8-86b6-4ad2c3e2632e","PM_Name":"尺寸","PM_Properties":"","PM_Type":0,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-12 14:52:01","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"},{"GID":"a94a8137-a199-4d16-92ea-168b5891eae6","PM_Name":"尺寸","PM_Properties":"S","PM_Type":1,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-12 14:52:10","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"},{"GID":"6cbf2a20-a9bc-49d0-a76b-7e4309472d65","PM_Name":"尺寸","PM_Properties":"M","PM_Type":1,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-12 14:52:14","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"},{"GID":"9f0e38ed-971d-4812-8bcf-cc44e8568047","PM_Name":"尺寸","PM_Properties":"L","PM_Type":1,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-12 14:52:17","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"},{"GID":"d6d03362-66b5-4a56-bc31-e81467567e76","PM_Name":"65","PM_Properties":"","PM_Type":0,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-16 13:53:34","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"},{"GID":"41f6f789-37ad-4d10-8718-d08d35ae9448","PM_Name":"65","PM_Properties":"1","PM_Type":1,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-16 16:55:48","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"},{"GID":"eb13b0b6-099c-40f6-bede-6de014a145cb","PM_Name":"65","PM_Properties":"2","PM_Type":1,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-16 16:55:51","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"},{"GID":"f7934ae0-1f96-4e3c-b9be-ff49553ede5e","PM_Name":"65","PM_Properties":"3","PM_Type":1,"SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","PM_Creator":"销售001","PM_CreateTime":"2019-04-16 16:55:56","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4"}]
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
         * GID : 543ed187-b93a-4dae-bcc6-9077a1182d47
         * PM_Name : 颜色
         * PM_Properties :
         * PM_Type : 0
         * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
         * SM_Name : jll2
         * PM_Creator : 销售001
         * PM_CreateTime : 2019-04-12 14:51:35
         * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
         */

        private String GID;
        private String PM_Name;
        private String PM_Properties;
        private int PM_Type;
        private String SM_GID;
        private String SM_Name;
        private String PM_Creator;
        private String PM_CreateTime;
        private String CY_GID;


        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        private boolean isChecked;

        public boolean isEnable() {
            return isEnable;
        }

        public void setEnable(boolean enable) {
            isEnable = enable;
        }

        private boolean isEnable;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getPM_Name() {
            return PM_Name;
        }

        public void setPM_Name(String PM_Name) {
            this.PM_Name = PM_Name;
        }

        public String getPM_Properties() {
            return PM_Properties;
        }

        public void setPM_Properties(String PM_Properties) {
            this.PM_Properties = PM_Properties;
        }

        public int getPM_Type() {
            return PM_Type;
        }

        public void setPM_Type(int PM_Type) {
            this.PM_Type = PM_Type;
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

        public String getPM_Creator() {
            return PM_Creator;
        }

        public void setPM_Creator(String PM_Creator) {
            this.PM_Creator = PM_Creator;
        }

        public String getPM_CreateTime() {
            return PM_CreateTime;
        }

        public void setPM_CreateTime(String PM_CreateTime) {
            this.PM_CreateTime = PM_CreateTime;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }
    }
}
