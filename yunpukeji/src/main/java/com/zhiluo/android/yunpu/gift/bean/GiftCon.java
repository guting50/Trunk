package com.zhiluo.android.yunpu.gift.bean;

/**
 * Created by 70700 on 2016/11/1.
 */
public class GiftCon {
    /**
     * Num : 1
     * GT_Name : 日用品
     * GM_Name : 纸巾
     * GM_Code : 001
     * GM_Acount : 4
     * GM_Integral : 500
     * EGD_Score : 99
     *
     */

    private int Num;
    private String GT_Name;
    private String GM_Name;
    private String GM_Code;
    private int GM_Acount;
    private int GM_Integral;
    private int EGD_Score;
    private int posion;
    private String GM_GID;

    public GiftCon(int num, String GT_Name, String GM_Name, String GM_Code, int GM_Acount, int GM_Integral, int EGD_Score, int posion, String GM_GID) {
        Num = num;
        this.GT_Name = GT_Name;
        this.GM_Name = GM_Name;
        this.GM_Code = GM_Code;
        this.GM_Acount = GM_Acount;
        this.GM_Integral = GM_Integral;
        this.EGD_Score = EGD_Score;
        this.posion = posion;
        this.GM_GID = GM_GID;
    }

    public GiftCon() {
    }

    @Override
    public String toString() {
        return "GiftCon{" +
                "Num=" + Num +
                ", GT_Name='" + GT_Name + '\'' +
                ", GM_Name='" + GM_Name + '\'' +
                ", GM_Code='" + GM_Code + '\'' +
                ", GM_Acount=" + GM_Acount +
                ", GM_Integral=" + GM_Integral +
                ", EGD_Score=" + EGD_Score +
                ", posion=" + posion +
                ", GM_GID='" + GM_GID + '\'' +
                '}';
    }

    public int getNum() {
        return Num;
    }

    public void setNum(int num) {
        Num = num;
    }

    public String getGT_Name() {
        return GT_Name;
    }

    public void setGT_Name(String GT_Name) {
        this.GT_Name = GT_Name;
    }

    public String getGM_Name() {
        return GM_Name;
    }

    public void setGM_Name(String GM_Name) {
        this.GM_Name = GM_Name;
    }

    public String getGM_Code() {
        return GM_Code;
    }

    public void setGM_Code(String GM_Code) {
        this.GM_Code = GM_Code;
    }

    public int getGM_Acount() {
        return GM_Acount;
    }

    public void setGM_Acount(int GM_Acount) {
        this.GM_Acount = GM_Acount;
    }

    public int getGM_Integral() {
        return GM_Integral;
    }

    public void setGM_Integral(int GM_Integral) {
        this.GM_Integral = GM_Integral;
    }

    public int getEGD_Score() {
        return EGD_Score;
    }

    public void setEGD_Score(int EGD_Score) {
        this.EGD_Score = EGD_Score;
    }

    public int getPosion() {
        return posion;
    }

    public void setPosion(int posion) {
        this.posion = posion;
    }

    public String getGM_GID() {
        return GM_GID;
    }

    public void setGM_GID(String GM_GID) {
        this.GM_GID = GM_GID;
    }
}

