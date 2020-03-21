package com.zhiluo.android.yunpu.login.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;

import com.zhiluo.android.yunpu.ui.activity.ActivityCollector;
import com.zhiluo.android.yunpu.login.activity.LoginActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 强制下线广播接收处理
 * author Yuan Cheng
 * blog yuancheng91.top
 * created 2017/4/7 17:18
 */
public class FrorceOfflineReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        sweetAlertDialog.setTitleText("提示");
        sweetAlertDialog.setConfirmText("确认");
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setContentText("长时间未登录，或已在别处登陆，请重新登陆！");
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                ActivityCollector.finishAll();
                Intent intent = new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                sweetAlertDialog.dismiss();
            }
        });
        sweetAlertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        sweetAlertDialog.show();
    }
}