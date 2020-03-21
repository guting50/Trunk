package com.zhiluo.android.yunpu.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.home.jsonbean.SingleShopInfoJsonBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.mvp.model.UpLoadPictureEntity;
import com.zhiluo.android.yunpu.mvp.view.IBaseView;
import com.zhiluo.android.yunpu.mvp.view.IMyView;
import com.zhiluo.android.yunpu.utils.CommonFun;

/**
 * 作者：罗咏哲 on 2017/10/25 11:30.
 * 邮箱：137615198@qq.com
 */

public class MyPresenter implements Presenter {
    private Context mContext;
    private IMyView mView;

    public MyPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(String s) {

    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    @Override
    public void attachView(IBaseView view) {
        mView = (IMyView) view;
    }

    /**
     * 获取店铺类型名称
     *
     * @param params
     */
    public void getCurrentStore(RequestParams params) {
        HttpHelper.post(mContext, "Shops/GetShops", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SingleShopInfoJsonBean bean = CommonFun.JsonToObj(responseString, SingleShopInfoJsonBean.class);
                mView.getCurrentStoreSuccess(bean);
            }

            @Override
            public void onFailure(String msg) {
                mView.getCurrentStoreFail(msg);
            }
        });
    }

    /**
     * 上传图片
     *
     * @param params
     */
    public void upStorePicture(RequestParams params) {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                UpLoadPictureEntity entity = CommonFun.JsonToObj(responseString, UpLoadPictureEntity.class);
                mView.upStorePictureSuccess(entity);
            }

            @Override
            public void onFailure(String msg) {
                mView.upStorePictureFail(msg);
            }
        };
        callBack.setLoadingAnimation(mContext, "正在上传图片...", false);
        HttpHelper.post(mContext, HttpAPI.API().UPLOAD_MEMBER_PHOTO, params, callBack);
    }
}
