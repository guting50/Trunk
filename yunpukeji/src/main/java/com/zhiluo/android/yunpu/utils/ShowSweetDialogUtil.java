package com.zhiluo.android.yunpu.utils;

import android.app.Activity;
import android.content.DialogInterface;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * iauthor：Cheng
 * date: 2017/4/12 15:00
 * email：jasoncheng9111@gmail.com
 */
public class ShowSweetDialogUtil
{
    private static SweetAlertDialog mSweetAlertDialog;

    /**
     * 显示普通的对话框
     * @param activity
     * @param msg
     */
    public final static void showNormalDialog(final Activity activity, String msg)
    {
        mSweetAlertDialog = new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCanceledOnTouchOutside(true);
        mSweetAlertDialog.setConfirmText("了解");
        mSweetAlertDialog.show();
    }

    /**
     * 显示警告对话框
     *
     * @param activity
     * @param msg
     */
    public final static void showWarningDialog(final Activity activity, String msg)
    {
        mSweetAlertDialog = new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCanceledOnTouchOutside(true);
        mSweetAlertDialog.setConfirmText("了解");
        mSweetAlertDialog.show();
    }

    /**
     * 显示成功对话框
     * @param activity 显示对话框的activity对象
     * @param msg 显示的消息文本
     * @param isFinish 对话框显示完毕后是否关闭当前的activity
     */
    public final static void showSuccessDialog(final Activity activity, String msg, final boolean isFinish)
    {
        mSweetAlertDialog = new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCanceledOnTouchOutside(true);
        mSweetAlertDialog.setConfirmText("了解");
        mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener()
        {
            @Override
            public void onDismiss(DialogInterface dialogInterface)
            {
                if (isFinish)
                {
                    activity.finish();
                }
            }
        });
        mSweetAlertDialog.show();
    }
}
