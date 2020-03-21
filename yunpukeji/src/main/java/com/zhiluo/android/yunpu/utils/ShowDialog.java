package com.zhiluo.android.yunpu.utils;

import android.content.Context;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by YSL on 2018-09-04.
 */

public class ShowDialog {

    private static SweetAlertDialog mSweetAlertDialog;//提示框

    /**
     * 警告弹窗
     *
     * @param msg
     */
    public static void warnDialog(Context context,String msg) {
        mSweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCancelable(false);
        mSweetAlertDialog.setConfirmText("确定");
        mSweetAlertDialog.show();
    }

    /**
     * 提示弹出
     *
     * @param msg
     */
    public static void toastDialog(Context context,String msg) {
        mSweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCancelable(false);
        mSweetAlertDialog.setConfirmText("确定");
        mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {

//                if (operate == 1){
//                    Intent intent = new Intent(AddMemberActivity.this, MemberListActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else if (operate == 2) {
//                    clearAll();
//                }
                mSweetAlertDialog.dismiss();

            }
        });
        mSweetAlertDialog.show();
    }
}
