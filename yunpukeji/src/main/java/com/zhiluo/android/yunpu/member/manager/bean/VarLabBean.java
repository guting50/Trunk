package com.zhiluo.android.yunpu.member.manager.bean;

import java.util.List;

/**
 * Created by ${YSL} on 2018-05-05.
 */

public class VarLabBean {

    private List<LabBean> lab;

    public List<LabBean> getLab() {
        return lab;
    }

    public void setLab(List<LabBean> lab) {
        this.lab = lab;
    }

    public static class LabBean {
        /**
         * ItemColor : #FF8739
         * ItemGID : cb6d4595-69bf-403f-b7fb-7f6c29a33e79
         * ItemName : 大老板
         * isChecked : true
         */

        private String ItemColor;
        private String ItemGID;
        private String ItemName;
        private boolean isChecked;

        public String getItemColor() {
            return ItemColor;
        }

        public void setItemColor(String ItemColor) {
            this.ItemColor = ItemColor;
        }

        public String getItemGID() {
            return ItemGID;
        }

        public void setItemGID(String ItemGID) {
            this.ItemGID = ItemGID;
        }

        public String getItemName() {
            return ItemName;
        }

        public void setItemName(String ItemName) {
            this.ItemName = ItemName;
        }

        public boolean isIsChecked() {
            return isChecked;
        }

        public void setIsChecked(boolean isChecked) {
            this.isChecked = isChecked;
        }
    }
}
