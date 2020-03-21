package com.zhiluo.android.yunpu.entity;

import org.litepal.crud.DataSupport;

/**
 * 保存Banner数据信息JavaBean
 * 作者：罗咏哲 on 2017/9/14 11:39.
 * 邮箱：137615198@qq.com
 */

public class BannerDataBean extends DataSupport {
    /**
     * 排序
     */
    private int order;
    /**
     * 图片地址
     */
    private String bannerUrl;

    /**
     * 图片GID
     */
    private String gid;
    /**
     * 连接地址
     */
    private String linkUrl;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
