package com.zhiluo.android.yunpu.network;

import android.util.Log;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.zhiluo.android.yunpu.home.jsonbean.BannerJsonBean;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * iauthor：Yc
 * date: 2017/6/17 10:54
 * email：jasoncheng9111@gmail.com
 */
public class HttpBannerImageHelper {

    private static AsyncHttpClient mAsyncHttpClient;
    private static List<String> sList_imageUrl = new ArrayList<>();
    private static List<String> sList_imageLingkUrl = new ArrayList<>();


    public static List<String> getList_imageUrl() {
        return sList_imageUrl;
    }

    public static List<String> getList_imageLingkUrl() {
        return sList_imageLingkUrl;
    }


    /**
     * 获得轮播图片
     */
    public static void post_BannerImage() {
        mAsyncHttpClient = new AsyncHttpClient();

        mAsyncHttpClient.post(HttpAPI.API().BANNER, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String str = new String(responseBody, "UTF-8");
                    Gson gson = new Gson();
                    BannerJsonBean bannerJsonBean = CommonFun.JsonToObj(new String(responseBody, "UTF-8"), BannerJsonBean.class);
                    for (int i = 0; i < bannerJsonBean.getData().size(); i++) {
                        if (bannerJsonBean.getData().get(i).getAD_ImgUrl().contains("http")) {
                            sList_imageUrl.add(bannerJsonBean.getData().get(i).getAD_ImgUrl());
                            Log.i("lyz", "Banner地址: " + bannerJsonBean.getData().get(i).getAD_ImgUrl());
                        } else {
                            sList_imageUrl.add("http://core.yunvip123.com" + bannerJsonBean.getData().get(i).getAD_ImgUrl());
                            Log.i("lyz", "Banner地址: " + "http://core.yunvip123.com" + bannerJsonBean.getData().get(i).getAD_ImgUrl());
                        }
                        sList_imageLingkUrl.add(bannerJsonBean.getData().get(i).getAD_Link_Real());

                        Log.i("lyz", "Banner跳转地址: " + bannerJsonBean.getData().get(i).getAD_Link_Real());
//                        util.savePicture("banner" + i, bannerJsonBean.getData().get(i).getAD_ImgUrl());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
}
