package com.zhiluo.android.yunpu.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.mvp.view.IBaseView;
import com.zhiluo.android.yunpu.mvp.view.IPrintSetView;
import com.zhiluo.android.yunpu.print.bean.PrintSetBean;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.logging.Level;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * 作者：罗咏哲 on 2017/10/24 17:44.
 * 邮箱：137615198@qq.com
 */

public class IPrintSetPresenter implements Presenter {
    private Context mContext;
    private IPrintSetView mView;

    public IPrintSetPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(String s) {
        getPrintSet();
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    @Override
    public void attachView(IBaseView view) {
        mView = (IPrintSetView) view;
    }

    /**
     * 保存打印设置
     *
     * @param params
     */
    public void savePrintSet(Context context,RequestParams params) {

         CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mView.saveSetSuccess();
            }

            @Override
            public void onFailure(String msg) {
//                mView.saveSetFail(msg);
            }
        };
        callBack.setLoadingAnimation(context,"保存中...",false);
        HttpHelper.post(mContext, "PrintSet/EditPrintSet", params,callBack);

    }

    /**
     * 获取打印设置
     */
    public void getPrintSet() {  // http://dj.zhiluo.net/api/PrintSet/GetPrintSet
        HttpHelper.post(mContext, HttpAPI.API().PRINTSET, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                PrintSetBean bean = CommonFun.JsonToObj(responseString, PrintSetBean.class);
                mView.getPrintSetSuccess(bean);
            }

            @Override
            public void onFailure(String msg) {
                mView.getPrintSetFail(msg);
            }
        });
    }
}
