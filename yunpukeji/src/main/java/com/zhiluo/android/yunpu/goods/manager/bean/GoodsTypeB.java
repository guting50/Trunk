package com.zhiluo.android.yunpu.goods.manager.bean;

/**
 * Created by 70700 on 2016/11/16.
 */
public class GoodsTypeB {
    /**
     * "SM_ID":"38771b02-7c22-41db-9621-497489484b74",
     * "SM_Name":"哇哈哈汽车美容",
     * "GID":"58e560fa-7ce6-4718-bd33-f72a71562642",
     * "PT_Name":"运动",
     * "PT_Parent":"73f7dfa7-577c-4896-a938-e281b874ad74",
     * "PT_Remark":"",
     * "PT_Creator":"13725589468",
     * "PT_UpdateTime":"2016-11-16 14:09:54",
     * "CY_GID":null,
     * "PT_SynType":0
     */

    private String GID;
    private String PT_Name;
    private String PT_Parent;
    private int PT_SynType;

    public GoodsTypeB() {
    }

    public GoodsTypeB(String GID, String PT_Name, String PT_Parent, int PT_SynType) {
        this.GID = GID;
        this.PT_Name = PT_Name;
        this.PT_Parent = PT_Parent;
        this.PT_SynType = PT_SynType;
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

    public int getPT_SynType() {
        return PT_SynType;
    }

    public void setPT_SynType(int PT_SynType) {
        this.PT_SynType = PT_SynType;
    }
}
