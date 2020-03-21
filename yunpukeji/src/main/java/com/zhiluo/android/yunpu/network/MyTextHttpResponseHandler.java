package com.zhiluo.android.yunpu.network;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.TextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.ActivityCollector;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.LoadingDialogUtil;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.SuccessBean;
import com.zhiluo.android.yunpu.login.activity.LoginActivity;
import com.zhiluo.android.yunpu.utils.CommonFun;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

/**
 * 网络请求封装
 * 作者：罗咏哲 on 2017/8/14 15:32.
 * 邮箱：137615198@qq.com
 */

public abstract class MyTextHttpResponseHandler extends TextHttpResponseHandler {
    public Gson mGson;
    Dialog mDialog;

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        if (mDialog != null) {
            LoadingDialogUtil.closeDialog(mDialog);
        }
        if (statusCode != 200) {
            CustomToast.makeText(MyApplication.getmContext(), "错误码：" + statusCode + " 网络错误,请检查网络是否连接!",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        if (mDialog != null) {
            LoadingDialogUtil.closeDialog(mDialog);
        }
        if (statusCode == 200) {
            mGson = new Gson();
            SuccessBean bean = CommonFun.JsonToObj(responseString, SuccessBean.class);
            if (bean.isSuccess()) {
                this.onSuccess(responseString, mGson);//成功处理
            } else if (bean.getMsg().contains("登录") || bean.getMsg().contains("登陆")) {
               /* if (MyApplication.dialogFlag) {
                    MyApplication.dialogFlag = false;
                    reLogin();//此账号异地登录，重新登录
                }*/
                reLogin(bean.getMsg());//此账号异地登录，重新登录
            } else {
                this.onFailure(bean.getMsg());//请求失败信息提示
            }
        }
    }

    /**
     * 设置加载动画
     *
     * @param context context
     * @param msg     动画提示信息
     * @param b       是否可取消动画
     */

    public void setDialog(Context context, String msg, boolean b) {
        mDialog = LoadingDialogUtil.createLoadingDialog(context, msg, b);
    }

    /**
     * 请求成功回掉
     *
     * @param responseString 返回字符串
     */
    public abstract void onSuccess(String responseString, Gson gson);

    /**
     * 请求失败提示
     *
     * @param msg 提示信息
     */
    public abstract void onFailure(String msg);

    /**
     * 重新登录
     */
    public void reLogin(String msg) {
//        SweetAlertDialog dialog = new SweetAlertDialog(MyApplication.getmContext(), SweetAlertDialog.WARNING_TYPE);
//        dialog.setTitleText("提示");
//        dialog.setConfirmText("确认");
//        dialog.setCancelable(false);
//        dialog.setContentText("长时间未登录或已在别处登录，请重新登录！");
//        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//            @Override
//            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                ActivityCollector.finishAll();
//                Intent intent = new Intent(MyApplication.getmContext(), LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                MyApplication.getmContext().startActivity(intent);
//                sweetAlertDialog.dismiss();
//                MyApplication.dialogFlag = true;
//            }
//        });
        // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);//小米，魅族不支持，故采用另一种方式
      //  dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
       // dialog.show();
        ActivityCollector.finishAll();
        Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
        CustomToast.makeText(MyApplication.getmContext(),msg,Toast.LENGTH_SHORT).show();
    }

}
