package com.zhiluo.android.yunpu.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;
import com.zhiluo.android.yunpu.entity.BannerDataBean;
import com.zhiluo.android.yunpu.home.jsonbean.BannerJsonBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.config.MyApplication;

import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * 下载Banner工具
 * 作者：罗咏哲 on 2017/9/14 14:21.
 * 邮箱：137615198@qq.com
 */

public class DownLoadBannerUtil {
    private static Context mContext = MyApplication.getmContext();
    private String mFilePath = Environment.getExternalStorageDirectory().toString() + File.separator + "banner";
    private static List<String> sList_imageUrl = new ArrayList<>();
    private static List<String> sList_imageLingkUrl = new ArrayList<>();

    public static List<String> getsList_imageUrl() {
        return sList_imageUrl;
    }

    public static List<String> getsList_imageLingkUrl() {
        return sList_imageLingkUrl;
    }


    public static void postBannerUrl() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(HttpAPI.API().BANNER, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                BannerJsonBean bannerJsonBean = CommonFun.JsonToObj(responseString, BannerJsonBean.class);
                List<BannerDataBean> bannerList = DataSupport.findAll(BannerDataBean.class);
                for (int i = 0; i < bannerJsonBean.getData().size(); i++) {
                    BannerJsonBean.DataBean bean = bannerJsonBean.getData().get(i);
                    sList_imageUrl.add(HttpAPI.API().DOMAIN + bean.getAD_ImgUrl());
                    sList_imageLingkUrl.add(bean.getAD_Link_Real());
                    System.out.println(bean.getAD_ImgUrl());
//                    savePicture("img" + bean.getAD_Sort(), bean.getAD_ImgUrl(), bean.getAD_Sort(),
//                            bean.getGID(), bean.getAD_Link_Real());
//                    if (bannerList.size() > 0) {
//                        for (int j = 0; j < bannerList.size(); j++) {
//                            System.out.println(bannerList.get(j).getBannerUrl());
//                        }
//                    }
//                    if (bannerList.size() > 0) {
//                        for (int j = 0; j < bannerList.size(); j++) {
////                            if (bannerList.get(j).getGid().equals(bean.getGID())){
////                                BannerDataBean dataBean = new BannerDataBean();
////                                dataBean.setOrder(bean.getAD_Sort());
////                                dataBean.setBannerUrl(bannerList.get(j).getBannerUrl());
////                                dataBean.setGid(bean.getGID());
////                                dataBean.setLinkUrl(bannerList.get(j).getLinkUrl());
////                                dataBean.save();
////                            }
//                            if (bannerList.get(j).getOrder() == bean.getAD_Sort() &&
//                                    !bannerList.get(j).getGid().equals(bean.getGID())) {
//                                savePicture("img" + bean.getAD_Sort(), bean.getAD_ImgUrl(), bean.getAD_Sort(), bean.getGID(), bean.getAD_Link_Real());
//                            }
//                        }
//                    } else {
//                        savePicture("img" + bean.getAD_Sort(), bean.getAD_ImgUrl(), bean.getAD_Sort(), bean.getGID(), bean.getAD_Link_Real());
//                    }
                }
            }
        });
    }


    //Glide保存图片
    private static void savePicture(final String fileName, String url, final int order, final String gid, final String linkUrl) {
        Log.i("banner", "savePicture: " + "开始下载Banner");
        Glide.with(mContext).load(HttpAPI.API().DOMAIN + url).asBitmap().toBytes().into(new SimpleTarget<byte[]>() {
            @Override
            public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
                try {
                    saveFileToSD(fileName, bytes, order, gid, linkUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //往SD卡写入文件的方法
    private static void saveFileToSD(String filename, byte[] bytes, final int order, final String gid, final String linkUrl) throws Exception {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/banner";
            File dir1 = new File(filePath);
            if (!dir1.exists()) {
                dir1.mkdirs();
            } else {
                filename = filePath + "/" + filename + ".jpg";
                File file = new File(filename);
                if (file.exists()) {
                    file.delete();
                }
                //这里就不要用openFileOutput了,那个是往手机内存中写数据的
                FileOutputStream output = new FileOutputStream(filename);
                output.write(bytes);
                //将bytes写入到输出流中
                output.close();

                BannerDataBean dataBean = new BannerDataBean();
                dataBean.setOrder(order);
                dataBean.setBannerUrl(filename);
                dataBean.setGid(gid);
                dataBean.setLinkUrl(linkUrl);
                dataBean.save();
            }
            //关闭输出流
//            CustomToast.makeText(context, "图片已成功保存到"+filePath, Toast.LENGTH_SHORT).show();
        } else {
            CustomToast.makeText(mContext, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 下载Banner图片并保存到本地数据库
     *
     * @param url     下载地址
     * @param order   排序
     * @param gid     图片GID
     * @param linkUrl 跳转地址
     */
    private void downLoadBanner(String url, final int order, final String gid, final String linkUrl) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.post(HttpAPI.API().DOMAIN + url, new BinaryHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                Log.i("banner", "onSuccess: " + statusCode);
                File file = Environment.getExternalStorageDirectory();
                File file2 = new File(file, "banner");
                file2.mkdir();
                file2 = new File(file2, "banner" + order + ".jpg");
                if (file2.exists()) {
                    file2.delete();
                }
                try {
                    FileOutputStream outputStream = new FileOutputStream(file2);
                    outputStream.write(binaryData);
                    outputStream.flush();
                    outputStream.close();

                    BannerDataBean dataBean = new BannerDataBean();
                    dataBean.setOrder(order);
                    dataBean.setBannerUrl(mFilePath + "banner" + order + ".jpg");
                    dataBean.setGid(gid);
                    dataBean.setLinkUrl(linkUrl);
                    dataBean.save();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
                Log.i("banner", "onSuccess: " + statusCode);
            }
        });
    }
}
