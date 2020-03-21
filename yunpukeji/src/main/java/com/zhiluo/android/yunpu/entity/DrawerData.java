package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/6.
 */
public class DrawerData implements Serializable {
    private String item;
    private int image;
    private Class<?> cls;
    private static final long serialVersionUID = 1L;
    private boolean flag;

    public DrawerData(String item, int image, Class<?> cls, boolean flag) {
        this.item = item;
        this.image = image;
        this.cls = cls;
        this.flag = flag;
    }

    public DrawerData(String item, int image, Class<?> cls) {
        this.item = item;
        this.image = image;
        this.cls = cls;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
