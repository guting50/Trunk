package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;

/**
 * 作者：罗咏哲 on 2018/1/26 13:49.
 * 邮箱：137615198@qq.com
 */

public class TCBean implements Serializable {
    private static final long UID = 1L;
    /**
     * GID : c4b01fec-392d-472a-9fc7-52abc67a108d
     * SM_GID : af4b45fc-bab8-4af4-a3b8-83cbc5896d3c
     * CY_GID : 01a5ab8f-46b1-41c4-b089-0a73230ff9da
     * PC_Creator : 1735863857
     * PC_Code : TC001
     * PC_Name : 套餐一
     * PC_Price : 100.0
     * PC_Discount : 0.9
     * PC_specification :
     * PC_Integral : 0
     * PC_SynState : 0
     * PC_Remark :
     * PC_CreateTime : 2018-01-26 13:40:42
     * PC_Image : /img/goods.png
     * PC_ProductJson : [{"GID":"9dd2f746-d0a2-4ac7-8b9c-a0836637eb82","PM_Name":"洗发水250ML","PM_Number":"2"},{"GID":"43bff9ae-1652-4978-aa19-335cf1938794","PM_Name":"洗发水100ML","PM_Number":"3"}]
     * PC_DeleteSign : 0
     * PC_ProductJsonList : null
     * SM_Name : null
     */

    private String GID;
    private String SM_GID;
    private String CY_GID;
    private String PC_Creator;
    private String PC_Code;
    private String PC_Name;
    private double PC_Price;
    private double PC_Discount;
    private String PC_specification;
    private int PC_Integral;
    private int PC_SynState;
    private String PC_Remark;
    private String PC_CreateTime;
    private String PC_Image;
    private String PC_ProductJson;
    private int PC_DeleteSign;
    private String PC_ProductJsonList;
    private String SM_Name;
    private int PC_Num;
 private int PC_Type;
private double EachPoint;//商品积分
 public double getEachPoint() {
        return EachPoint;
    }
    public void setEachPoint(double eachPoint) {
        EachPoint = eachPoint;
    }
  public int getPC_Type() {
        return PC_Type;
    }

    public void setPC_Type(int PC_Type) {
        this.PC_Type = PC_Type;
    }

   
    public double getZHMoney() {
        return ZHMoney;
    }

    public void setZHMoney(double ZHMoney) {
        this.ZHMoney = ZHMoney;
    }

    private double ZHMoney;

    public int getPC_Num() {
        return PC_Num;
    }

    public void setPC_Num(int PC_Num) {
        this.PC_Num = PC_Num;
    }

    public String getGID() {
        return GID;
    }

    public void setGID(String GID) {
        this.GID = GID;
    }

    public String getSM_GID() {
        return SM_GID;
    }

    public void setSM_GID(String SM_GID) {
        this.SM_GID = SM_GID;
    }

    public String getCY_GID() {
        return CY_GID;
    }

    public void setCY_GID(String CY_GID) {
        this.CY_GID = CY_GID;
    }

    public String getPC_Creator() {
        return PC_Creator;
    }

    public void setPC_Creator(String PC_Creator) {
        this.PC_Creator = PC_Creator;
    }

    public String getPC_Code() {
        return PC_Code;
    }

    public void setPC_Code(String PC_Code) {
        this.PC_Code = PC_Code;
    }

    public String getPC_Name() {
        return PC_Name;
    }

    public void setPC_Name(String PC_Name) {
        this.PC_Name = PC_Name;
    }

    public double getPC_Price() {
        return PC_Price;
    }

    public void setPC_Price(double PC_Price) {
        this.PC_Price = PC_Price;
    }

    public double getPC_Discount() {
        return PC_Discount;
    }

    public void setPC_Discount(double PC_Discount) {
        this.PC_Discount = PC_Discount;
    }

    public String getPC_specification() {
        return PC_specification;
    }

    public void setPC_specification(String PC_specification) {
        this.PC_specification = PC_specification;
    }

    public int getPC_Integral() {
        return PC_Integral;
    }

    public void setPC_Integral(int PC_Integral) {
        this.PC_Integral = PC_Integral;
    }

    public int getPC_SynState() {
        return PC_SynState;
    }

    public void setPC_SynState(int PC_SynState) {
        this.PC_SynState = PC_SynState;
    }

    public String getPC_Remark() {
        return PC_Remark;
    }

    public void setPC_Remark(String PC_Remark) {
        this.PC_Remark = PC_Remark;
    }

    public String getPC_CreateTime() {
        return PC_CreateTime;
    }

    public void setPC_CreateTime(String PC_CreateTime) {
        this.PC_CreateTime = PC_CreateTime;
    }

    public String getPC_Image() {
        return PC_Image;
    }

    public void setPC_Image(String PC_Image) {
        this.PC_Image = PC_Image;
    }

    public String getPC_ProductJson() {
        return PC_ProductJson;
    }

    public void setPC_ProductJson(String PC_ProductJson) {
        this.PC_ProductJson = PC_ProductJson;
    }

    public int getPC_DeleteSign() {
        return PC_DeleteSign;
    }

    public void setPC_DeleteSign(int PC_DeleteSign) {
        this.PC_DeleteSign = PC_DeleteSign;
    }

    public String getPC_ProductJsonList() {
        return PC_ProductJsonList;
    }

    public void setPC_ProductJsonList(String PC_ProductJsonList) {
        this.PC_ProductJsonList = PC_ProductJsonList;
    }

    public String getSM_Name() {
        return SM_Name;
    }

    public void setSM_Name(String SM_Name) {
        this.SM_Name = SM_Name;
    }
}
