package com.zhiluo.android.yunpu.home.jsonbean;

/**
 * Created by 70700 on 2016/11/8.
 */
public class VisonBean
{


    /**
     * version : 1
     * address : http://www.yunvip123.com/Upgrade/v1.02.apk
     * text : 更新了一些日常BUG。
     */

    private int version;
    private String address;
    private String text;

    public int getVersion()
    {
        return version;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}
