package com.zhiluo.android.yunpu.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.ProviderBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.mvp.view.IBaseView;
import com.zhiluo.android.yunpu.mvp.view.IWareHousingConfirmView;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/10/26 16:44.
 * 邮箱：137615198@qq.com
 */

public class WareHousingConfirmPresenter implements Presenter {
    private Context mContext;
    private IWareHousingConfirmView mView;
    private String mOrderNo;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodList;

    public WareHousingConfirmPresenter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate(String s) {
        getProvider();
        mOrderNo = CreateOrder.createOrder("CG");
    }

    @Override
    public void attachIncomingIntent(Intent intent) {
        double total = intent.getDoubleExtra("total", 0);
        Bundle bundle = intent.getBundleExtra("good");
        mGoodList = (List<GoodsCheckResponseByType.DataBean.DataListBean>) bundle.getSerializable("good");
        mView.init(mOrderNo, total, mGoodList);
    }

    @Override
    public void attachView(IBaseView view) {
        mView = (IWareHousingConfirmView) view;
    }

    private void getProvider() {
        RequestParams params = new RequestParams();
        params.put("PageIndex", 1);
        params.put("PageSize", Integer.MAX_VALUE);
        HttpHelper.post(mContext, HttpAPI.API().PRIVADER, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ProviderBean bean = CommonFun.JsonToObj(responseString, ProviderBean.class);
                mView.getProviderSuccess(bean);
            }

            @Override
            public void onFailure(String msg) {
                mView.getProviderFail(msg);
            }
        });
    }

    public void warehousing(RequestParams params) {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mView.inputStockSuccess();
            }

            @Override
            public void onFailure(String msg) {
                mView.inputStockFail(msg);
            }
        };
        callBack.setLoadingAnimation(mContext, "正在入库...", false);
        HttpHelper.post(mContext, HttpAPI.API().INPUT_STOCK, params, callBack);
    }
}
