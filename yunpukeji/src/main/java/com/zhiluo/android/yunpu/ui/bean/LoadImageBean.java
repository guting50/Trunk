package com.zhiluo.android.yunpu.ui.bean;

/**
 * Created by ZPH on 2019-08-29.
 */

public class LoadImageBean {

    private String namme;
    private String imageAddress;
    private boolean star;

    public boolean isStar() {
        return star;
    }

    public void setStar(boolean star) {
        this.star = star;
    }
    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }


    public String getNamme() {
        return namme;
    }

    public void setNamme(String namme) {
        this.namme = namme;
    }


}
