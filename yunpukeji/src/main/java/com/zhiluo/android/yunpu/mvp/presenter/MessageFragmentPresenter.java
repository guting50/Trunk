package com.zhiluo.android.yunpu.mvp.presenter;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.home.jsonbean.MessageCenterJsonBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.mvp.view.IBaseView;
import com.zhiluo.android.yunpu.mvp.view.IMessageFragmentView;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.logging.Level;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * 作者：罗咏哲 on 2017/10/24 14:21.
 * 邮箱：137615198@qq.com
 */

public class MessageFragmentPresenter implements Presenter {
    private Context mContext;
    private IMessageFragmentView mView;

    public MessageFragmentPresenter(Context mContext) {
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
        mView = (IMessageFragmentView) view;
    }

    /**
     * 获取消息列表
     *
     * @param params
     */
    public void getMessageList(RequestParams params) {
        HttpHelper.post(mContext, HttpAPI.API().NOTICE, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Le("messagess"+responseString);
                MessageCenterJsonBean bean = CommonFun.JsonToObj(responseString, MessageCenterJsonBean.class);
                mView.getMessageListSuccess(bean);
            }

            @Override
            public void onFailure(String msg) {
                mView.getMessageListFail(msg);
            }
        });
    }

    /**
     * 标记为已读
     *
     * @param params
     */
    public void markHaveReadFlag(RequestParams params) {
        HttpHelper.post(mContext, HttpAPI.API().FLAG_HAVE_READ, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mView.markSuccess();
            }

            @Override
            public void onFailure(String msg) {
                mView.markFail(msg);
            }
        });
    }
}
