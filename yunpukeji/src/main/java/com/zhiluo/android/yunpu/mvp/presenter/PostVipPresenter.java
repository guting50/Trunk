package com.zhiluo.android.yunpu.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.mvp.view.IBaseView;
import com.zhiluo.android.yunpu.mvp.view.IPostVipView;
import com.zhiluo.android.yunpu.utils.CommonFun;


/**
 * Created by ZPH on 2019-02-15.
 */

public class PostVipPresenter implements Presenter {

    private Context mContext;
    private IPostVipView mView;

    public PostVipPresenter(Context context){
        this.mContext = context;
    }

    @Override
    public void onCreate(String s) {
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    @Override
    public void attachView(IBaseView view) {
      mView = (IPostVipView) view;
    }

    /**
     * 根据会员卡号查询会员相关信息
     *
     * @param k 会员卡号
     */
    public void postVip(String k,boolean isSearch) {
        RequestParams params = new RequestParams();
        if (!isSearch){
            params.put("AccurateOfCard", k);
        }
        params.put("VCH_Card", k);

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                MemberInfoBean memberInfoBean = CommonFun.JsonToObj(responseString, MemberInfoBean.class);
                mView.getvipsuccesss(memberInfoBean);
            }

            @Override
            public void onFailure(String msg) {
                mView.getvipfail(msg);

            }
        };
//        callBack.setLoadingAnimation(this,"查找会员...",false);
        HttpHelper.post(mContext, HttpAPI.API().QUERY_SINGLE_MEMBER,params,callBack);
    }

}
