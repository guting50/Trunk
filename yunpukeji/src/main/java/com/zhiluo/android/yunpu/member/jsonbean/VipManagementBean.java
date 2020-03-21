package com.zhiluo.android.yunpu.member.jsonbean;

/**
 * Created by Administrator on 2016/9/19.
 */
public class VipManagementBean {


    public String VipName;
    public String VipCardId;
    public String VgName;
    public String VipPhone;
    public String VipSex;
    public String VipState;

    @Override
    public String toString() {
        return "VipManagementBean{" +
                "VipName='" + VipName + '\'' +
                ", VipCardId='" + VipCardId + '\'' +
                ", VgName='" + VgName + '\'' +
                ", VipPhone='" + VipPhone + '\'' +
                ", VipSex='" + VipSex + '\'' +
                ", VipState='" + VipState + '\'' +
                '}';
    }

    public String getVipName() {
        return VipName;
    }

    public void setVipName(String vipName) {
        VipName = vipName;
    }

    public String getVipCardId() {
        return VipCardId;
    }

    public void setVipCardId(String vipCardId) {
        VipCardId = vipCardId;
    }

    public String getVgName() {
        return VgName;
    }

    public void setVgName(String vgName) {
        VgName = vgName;
    }

    public String getVipPhone() {
        return VipPhone;
    }

    public void setVipPhone(String vipPhone) {
        VipPhone = vipPhone;
    }

    public String getVipSex() {
        return VipSex;
    }

    public void setVipSex(String vipSex) {
        VipSex = vipSex;
    }

    public String getVipState() {
        return VipState;
    }

    public void setVipState(String vipState) {
        VipState = vipState;
    }

    public VipManagementBean(String vipName, String vipCardId, String vgName, String vipPhone, String vipSex, String vipState) {
        VipName = vipName;
        VipCardId = vipCardId;
        VgName = vgName;
        VipPhone = vipPhone;
        VipSex = vipSex;
        VipState = vipState;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VipManagementBean that = (VipManagementBean) o;

        if (VipName != null ? !VipName.equals(that.VipName) : that.VipName != null) return false;
        if (VipCardId != null ? !VipCardId.equals(that.VipCardId) : that.VipCardId != null)
            return false;
        if (VgName != null ? !VgName.equals(that.VgName) : that.VgName != null) return false;
        if (VipPhone != null ? !VipPhone.equals(that.VipPhone) : that.VipPhone != null)
            return false;
        if (VipSex != null ? !VipSex.equals(that.VipSex) : that.VipSex != null) return false;
        return VipState != null ? VipState.equals(that.VipState) : that.VipState == null;

    }

    @Override
    public int hashCode() {
        int result = VipName != null ? VipName.hashCode() : 0;
        result = 31 * result + (VipCardId != null ? VipCardId.hashCode() : 0);
        result = 31 * result + (VgName != null ? VgName.hashCode() : 0);
        result = 31 * result + (VipPhone != null ? VipPhone.hashCode() : 0);
        result = 31 * result + (VipSex != null ? VipSex.hashCode() : 0);
        result = 31 * result + (VipState != null ? VipState.hashCode() : 0);
        return result;
    }


}
