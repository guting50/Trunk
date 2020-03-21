package com.zhiluo.android.yunpu.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.zhiluo.android.yunpu.BuildConfig;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.yslutils.Constant;

import java.io.File;
import java.util.List;

/**
 * apk下载更新工具
 * 作者：罗咏哲 on 2017/12/4 11:37.
 * 邮箱：137615198@qq.com
 */

public class DownloadUtils {
    //下载器
    private DownloadManager downloadManager;
    //上下文
    private Context mContext;
    //下载的ID
    private long downloadId;

    public DownloadUtils(Context context) {
        this.mContext = context;
    }

    //下载apk
    public void downloadAPK(String url, String name) {

        //创建下载任务
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //移动网络情况下是否允许漫游
        request.setAllowedOverRoaming(false);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("新版本Apk");
        request.setDescription("Apk Downloading");
        request.setVisibleInDownloadsUi(true);

        //设置下载的路径
        request.setDestinationInExternalPublicDir(Environment.getExternalStorageDirectory().getAbsolutePath(), name);
        Log.i("sss", Environment.getExternalStorageDirectory().getAbsolutePath());

        //获取DownloadManager
        downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        //将下载请求加入下载队列，加入下载队列后会给该任务返回一个long型的id，通过该id可以取消任务，重启任务、获取下载的文件等等
        downloadId = downloadManager.enqueue(request);

        //注册广播接收者，监听下载状态
        mContext.registerReceiver(receiver,
                new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    //广播监听下载的各个状态
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkStatus();
        }
    };

    //检查下载状态
    private void checkStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        //通过下载的id查找
        query.setFilterById(downloadId);
        Cursor c = downloadManager.query(query);
        if (c.moveToFirst()) {
            int status = c.getInt(c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                //下载暂停
                case DownloadManager.STATUS_PAUSED:
                    break;
                //下载延迟
                case DownloadManager.STATUS_PENDING:
                    break;
                //正在下载
                case DownloadManager.STATUS_RUNNING:
                    break;
                //下载完成
                case DownloadManager.STATUS_SUCCESSFUL:
                    //下载完成安装APK
                    //installAPK();
                    update();
                    break;
                //下载失败
                case DownloadManager.STATUS_FAILED:
                    CustomToast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        c.close();
    }

    void update() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri apkUri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                apkUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".fileprovider", new File(MyApplication.APK_ADDDRESS + "/vip.apk"));
            } else {
                apkUri = Uri.fromFile(new File(MyApplication.APK_ADDDRESS + "/vip.apk"));
            }
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            // 查询所有符合 intent 跳转目标应用类型的应用，注意此方法必须放置在 setDataAndType 方法之后
            List<ResolveInfo> resolveLists = mContext.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            // 然后全部授权
            for (ResolveInfo resolveInfo : resolveLists) {
                String packageName = resolveInfo.activityInfo.packageName;
                mContext.grantUriPermission(packageName, apkUri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //下载到本地后执行安装
    private void installAPK() {
        //获取下载文件的Uri
        Uri downloadFileUri = downloadManager.getUriForDownloadedFile(downloadId);
        if (downloadFileUri != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            mContext.unregisterReceiver(receiver);
        }
    }

    private void installtest() {
        File file = new File(Constant.APK_URL, Constant.APP_NAME);
        if (file != null) {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(android.content.Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            mContext.startActivity(intent);
        }
    }

}
