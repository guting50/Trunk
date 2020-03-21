package com.zhiluo.android.yunpu.paymanager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2019-05-22.
 */

public class ChoiceExpensesTypeBean  implements Serializable{


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":"c9cfcf63-8e5f-4fa4-962b-aafeb682bf01","ET_Name":"办公用品","ET_ParentGID":"3908b66b-f874-442b-84d4-50feb87bb194","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:42","ET_Remark":null},{"GID":"8867635c-89e2-4dff-96c0-fefb91b96f14","ET_Name":"餐饮","ET_ParentGID":"63d91203-da37-43ec-94cb-c0b7785b2fc3","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:41","ET_Remark":null},{"GID":"511e5141-9756-4eab-8d4a-c61b13b14b73","ET_Name":"地税","ET_ParentGID":"3908b66b-f874-442b-84d4-50feb87bb194","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:42","ET_Remark":null},{"GID":"22d7153d-10f5-407c-8b8f-7fa6d017f15c","ET_Name":"电费","ET_ParentGID":"3908b66b-f874-442b-84d4-50feb87bb194","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:41","ET_Remark":null},{"GID":"2820669e-af66-4bb1-8b8d-2f697b5ea3da","ET_Name":"电话费","ET_ParentGID":"3908b66b-f874-442b-84d4-50feb87bb194","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:42","ET_Remark":null},{"GID":"a856e1bd-25b9-4b8f-b1b0-db7945628e07","ET_Name":"房租","ET_ParentGID":"45b7f072-0069-4515-bc0a-58092f17b4c9","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:42","ET_Remark":null},{"GID":"63d91203-da37-43ec-94cb-c0b7785b2fc3","ET_Name":"个人支出","ET_ParentGID":"","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:40","ET_Remark":""},{"GID":"4e1c0532-1d52-4df8-8ec3-2e0e230f7454","ET_Name":"管理费用","ET_ParentGID":"45b7f072-0069-4515-bc0a-58092f17b4c9","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:42","ET_Remark":null},{"GID":"c4dc98d9-321d-464f-af4d-ea376624e8e3","ET_Name":"国税","ET_ParentGID":"3908b66b-f874-442b-84d4-50feb87bb194","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:41","ET_Remark":null},{"GID":"648efb56-c242-4409-b157-dbf6311efea6","ET_Name":"奖金","ET_ParentGID":"45b7f072-0069-4515-bc0a-58092f17b4c9","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:43","ET_Remark":null},{"GID":"ac5805e8-5d1e-4029-9be5-071f1c9f99b7","ET_Name":"交通","ET_ParentGID":"63d91203-da37-43ec-94cb-c0b7785b2fc3","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:41","ET_Remark":null},{"GID":"568daf43-7565-46d1-a847-65b144442d92","ET_Name":"进货成本","ET_ParentGID":"45b7f072-0069-4515-bc0a-58092f17b4c9","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:42","ET_Remark":null},{"GID":"95f3565e-cfdc-415e-a0fe-4606c2688a12","ET_Name":"其他","ET_ParentGID":"3908b66b-f874-442b-84d4-50feb87bb194","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:42","ET_Remark":null},{"GID":"a1129bc4-ea76-4ed6-b45b-0201954268ff","ET_Name":"其他","ET_ParentGID":"63d91203-da37-43ec-94cb-c0b7785b2fc3","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:41","ET_Remark":null},{"GID":"db3c4d49-ff90-47c3-a806-7c5f871a95b2","ET_Name":"其他","ET_ParentGID":"45b7f072-0069-4515-bc0a-58092f17b4c9","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:43","ET_Remark":null},{"GID":"3908b66b-f874-442b-84d4-50feb87bb194","ET_Name":"日常费用","ET_ParentGID":null,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:40","ET_Remark":null},{"GID":"bd620dde-4af8-4868-970c-46a7e86060b0","ET_Name":"社交","ET_ParentGID":"63d91203-da37-43ec-94cb-c0b7785b2fc3","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:41","ET_Remark":null},{"GID":"4a3c96e8-dad7-4e0a-8b05-133ff5bf086f","ET_Name":"水费","ET_ParentGID":"3908b66b-f874-442b-84d4-50feb87bb194","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:41","ET_Remark":null},{"GID":"87a73e2c-348e-4a0c-abe5-8be29506dd54","ET_Name":"物流","ET_ParentGID":"45b7f072-0069-4515-bc0a-58092f17b4c9","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:42","ET_Remark":null},{"GID":"a3c2ff83-3b1c-491d-ba50-e49c68013f06","ET_Name":"医疗","ET_ParentGID":"63d91203-da37-43ec-94cb-c0b7785b2fc3","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:41","ET_Remark":null},{"GID":"52c7a832-5345-4bf6-8383-efdefdb18a53","ET_Name":"娱乐","ET_ParentGID":"63d91203-da37-43ec-94cb-c0b7785b2fc3","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:41","ET_Remark":null},{"GID":"760fb0bf-bfd2-4857-ba56-224c07ca2112","ET_Name":"员工工资","ET_ParentGID":"45b7f072-0069-4515-bc0a-58092f17b4c9","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:42","ET_Remark":null},{"GID":"dd9e8cad-5226-4957-8c7e-c2fd4a1e7b19","ET_Name":"员工借支","ET_ParentGID":"45b7f072-0069-4515-bc0a-58092f17b4c9","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:42","ET_Remark":null},{"GID":"ec0e45e8-727e-4b44-8006-4df7e438a3d6","ET_Name":"运动","ET_ParentGID":"63d91203-da37-43ec-94cb-c0b7785b2fc3","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:41","ET_Remark":null},{"GID":"45b7f072-0069-4515-bc0a-58092f17b4c9","ET_Name":"运营成本","ET_ParentGID":null,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","ET_Creator":"销售001","ET_CreateTime":"2018-12-26 11:16:40","ET_Remark":null}]
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
         * GID : c9cfcf63-8e5f-4fa4-962b-aafeb682bf01
         * ET_Name : 办公用品
         * ET_ParentGID : 3908b66b-f874-442b-84d4-50feb87bb194
         * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
         * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
         * ET_Creator : 销售001
         * ET_CreateTime : 2018-12-26 11:16:42
         * ET_Remark : null
         */

        private String GID;
        private String ET_Name;
        private String ET_ParentGID;
        private String CY_GID;
        private String SM_GID;
        private String ET_Creator;
        private String ET_CreateTime;
        private String ET_Remark;

        public boolean isChecked() {
            return Checked;
        }

        public void setChecked(boolean checked) {
            Checked = checked;
        }

        private boolean Checked;

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getET_Name() {
            return ET_Name;
        }

        public void setET_Name(String ET_Name) {
            this.ET_Name = ET_Name;
        }

        public String getET_ParentGID() {
            return ET_ParentGID;
        }

        public void setET_ParentGID(String ET_ParentGID) {
            this.ET_ParentGID = ET_ParentGID;
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

        public String getET_Creator() {
            return ET_Creator;
        }

        public void setET_Creator(String ET_Creator) {
            this.ET_Creator = ET_Creator;
        }

        public String getET_CreateTime() {
            return ET_CreateTime;
        }

        public void setET_CreateTime(String ET_CreateTime) {
            this.ET_CreateTime = ET_CreateTime;
        }

        public String getET_Remark() {
            return ET_Remark;
        }

        public void setET_Remark(String ET_Remark) {
            this.ET_Remark = ET_Remark;
        }
    }
}
