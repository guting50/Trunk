package com.zhiluo.android.yunpu.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import java.io.File;
import java.io.FileOutputStream;

/**
 * 下载图片工具类
 * 作者：罗咏哲 on 2017/8/25 16:30.
 * 邮箱：137615198@qq.com
 */

public class DownImageUtil {
    private Context context;

    public DownImageUtil() {
    }

    public DownImageUtil(Context context) {
        super();
        this.context = context;
    }

    //Glide保存图片
    public void savePicture(final String fileName, String url) {
        Glide.with(context).load(url).asBitmap().toBytes().into(new SimpleTarget<byte[]>() {
            @Override
            public void onResourceReady(byte[] bytes, GlideAnimation<? super byte[]> glideAnimation) {
                try {
                    saveFileToSD(fileName, bytes);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //往SD卡写入文件的方法
    private void saveFileToSD(String filename, byte[] bytes) throws Exception {
        //如果手机已插入sd卡,且app具有读写sd卡的权限
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            String filePath = Environment.getExternalStorageDirectory().getCanonicalPath() + "/banner";
            File dir1 = new File(filePath);
            if (!dir1.exists()) {
                dir1.mkdirs();
            } else {
                filename = filePath + "/" + filename + ".jpg";
                //这里就不要用openFileOutput了,那个是往手机内存中写数据的
                FileOutputStream output = new FileOutputStream(filename);
                output.write(bytes);
                //将bytes写入到输出流中
                output.close();
            }
            //关闭输出流
//            CustomToast.makeText(context, "图片已成功保存到"+filePath, Toast.LENGTH_SHORT).show();
        } else {
            CustomToast.makeText(context, "SD卡不存在或者不可读写", Toast.LENGTH_SHORT).show();
        }
    }
}
