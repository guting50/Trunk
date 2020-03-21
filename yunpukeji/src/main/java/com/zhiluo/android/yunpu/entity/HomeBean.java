package com.zhiluo.android.yunpu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/12/6 11:23.
 * 邮箱：137615198@qq.com
 */

public class HomeBean implements Serializable{
    private static final long serialVersionUID = 1L;
    List<DrawerData> list;

    public List<DrawerData> getList() {
        return list;
    }

    public void setList(List<DrawerData> list) {
        this.list = list;
    }
}
