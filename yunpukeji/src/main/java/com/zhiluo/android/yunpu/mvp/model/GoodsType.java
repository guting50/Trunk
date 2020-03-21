package com.zhiluo.android.yunpu.mvp.model;

import java.io.Serializable;
import java.util.List;

/**
 * 商品分类JavaBean
 * 作者：罗咏哲 on 2017/7/29 11:24.
 * 邮箱：137615198@qq.com
 */

public class GoodsType implements Serializable {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"03fa70e7-1e71-4d56-873b-e3b10b156b34","PT_Name":"手机","PT_Parent":"","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-06-13 18:05:48","CY_GID":null,"PT_SynType":0},{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"08085e51-300d-4166-b53f-4b7e3d245456","PT_Name":"服装","PT_Parent":"","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-06-13 18:06:04","CY_GID":null,"PT_SynType":0},{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"0d1b0710-5e02-4287-af51-67d63d8d6e88","PT_Name":"美食","PT_Parent":"","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-06-13 18:05:33","CY_GID":null,"PT_SynType":0},{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"5bf9a96b-7c7b-47c8-bf27-b2a01afcf4c0","PT_Name":"修","PT_Parent":"840bb8e6-f68b-43ee-9d85-1ed87ca2ebec","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-06-26 14:24:56","CY_GID":null,"PT_SynType":0},{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"66ec46db-0f55-4140-8e46-e2641433448a","PT_Name":"数学辅导","PT_Parent":"a8da9548-50af-474b-93d1-6610e3359a1f","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-06-26 14:38:37","CY_GID":null,"PT_SynType":0},{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"78f1f272-6998-4310-9900-81ad54e3e455","PT_Name":"运动","PT_Parent":"","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-06-26 10:25:37","CY_GID":null,"PT_SynType":0},{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"840bb8e6-f68b-43ee-9d85-1ed87ca2ebec","PT_Name":"维修服务","PT_Parent":"","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-06-26 14:15:32","CY_GID":null,"PT_SynType":0},{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"a8da9548-50af-474b-93d1-6610e3359a1f","PT_Name":"教育培训","PT_Parent":"","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-06-26 14:38:23","CY_GID":null,"PT_SynType":0},{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"cb1f9920-d08f-4008-b372-e0d4a01ea2e8","PT_Name":"小米","PT_Parent":"03fa70e7-1e71-4d56-873b-e3b10b156b34","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-06-26 10:25:53","CY_GID":null,"PT_SynType":0},{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"dc578f78-1bc7-4b7c-97ee-050e8107f39d","PT_Name":"华为","PT_Parent":"03fa70e7-1e71-4d56-873b-e3b10b156b34","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-06-26 10:26:16","CY_GID":null,"PT_SynType":0},{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"e0dece01-336c-4a55-9de2-666cd7953141","PT_Name":"蔬菜","PT_Parent":"0d1b0710-5e02-4287-af51-67d63d8d6e88","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-07-06 09:42:05","CY_GID":null,"PT_SynType":0},{"SM_ID":"d754599d-3004-4256-b462-2d841d6f962d","SM_Name":"云上铺","GID":"f76742b8-170e-42e0-b732-f8903a682b4d","PT_Name":"英语口语辅导","PT_Parent":"a8da9548-50af-474b-93d1-6610e3359a1f","PT_Remark":"","PT_Creator":"1540004275@qq.com","PT_UpdateTime":"2017-06-26 14:38:57","CY_GID":null,"PT_SynType":0}]
     */
    private static final long UID = 1L;
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

    public static class DataBean implements Serializable {
        private static final long UID = 1L;
        /**
         * 店铺GID
         */
        private String SM_ID;
        /**
         * 店铺名称
         */
        private String SM_Name;
        /**
         * 商品分类GID
         */
        private String GID;
        /**
         * 商品类型名称
         */
        private String PT_Name;
        /**
         * 父类型GID
         * 为“”表示没有父类型
         * 存在值则是其子类型
         */
        private String PT_Parent;
        /**
         * 备注
         */
        private String PT_Remark;
        /**
         * 添加者
         */
        private String PT_Creator;
        /**
         * 更新时间
         */
        private String PT_UpdateTime;
        /**
         * 企业GID
         */
        private String CY_GID;
        /**
         * 同步类型
         */
        private int PT_SynType;
        /**
         * 是否选中
         */
        private boolean isChecked;

        public String getSM_ID() {
            return SM_ID;
        }

        public void setSM_ID(String SM_ID) {
            this.SM_ID = SM_ID;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getPT_Name() {
            return PT_Name;
        }

        public void setPT_Name(String PT_Name) {
            this.PT_Name = PT_Name;
        }

        public String getPT_Parent() {
            return PT_Parent;
        }

        public void setPT_Parent(String PT_Parent) {
            this.PT_Parent = PT_Parent;
        }

        public String getPT_Remark() {
            return PT_Remark;
        }

        public void setPT_Remark(String PT_Remark) {
            this.PT_Remark = PT_Remark;
        }

        public String getPT_Creator() {
            return PT_Creator;
        }

        public void setPT_Creator(String PT_Creator) {
            this.PT_Creator = PT_Creator;
        }

        public String getPT_UpdateTime() {
            return PT_UpdateTime;
        }

        public void setPT_UpdateTime(String PT_UpdateTime) {
            this.PT_UpdateTime = PT_UpdateTime;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public int getPT_SynType() {
            return PT_SynType;
        }

        public void setPT_SynType(int PT_SynType) {
            this.PT_SynType = PT_SynType;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }
    }
}
