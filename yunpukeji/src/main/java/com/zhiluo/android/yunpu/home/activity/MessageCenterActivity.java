package com.zhiluo.android.yunpu.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.home.adapter.MessageCenterAdapter;
import com.zhiluo.android.yunpu.home.jsonbean.MessageCenterJsonBean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import java.util.ArrayList;
import java.util.List;

/**
 * 消息中心列表
 * iauthor：Cheng
 * date: 2017/5/11 13:51
 * email：jasoncheng9111@gmail.com
 */
public class MessageCenterActivity extends BaseActivity {
    private TextView mTvBack;
    private BaseListView mListView;
    private MessageCenterAdapter mAdapter;

    private List<MessageCenterJsonBean.DataBean.DataListBean> messageList = new ArrayList<>();
    private String mAccountGid;//账户GID
    private String mCommanyGid;//企业GID
    private String mNoticeGid;//消息GID
    private MessageCenterJsonBean mMeassageBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_center);
        initViews();
        postData();
        setListener();

    }

    /**
     * 初始化控件
     */
    private void initViews() {
        mTvBack = (TextView) findViewById(R.id.tv_back_activity);
        mListView = (BaseListView) findViewById(R.id.lv_message_center);
        mListView.setDividerHeight(0);
        mAccountGid = (String) uSharedPreferencesUtiles.get(MessageCenterActivity.this,
                "Account_GID", "");
        mCommanyGid = (String) uSharedPreferencesUtiles.get(MessageCenterActivity.this,
                "CY_GID", "");
    }

    /**
     * 设置监听
     */
    private void setListener() {
        //返回
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MessageCenterActivity.this, MessageContentActivity.class);
                intent.putExtra("content", mMeassageBean.getData().getDataList().get(i).getContent());
                startActivity(intent);
                mNoticeGid = mMeassageBean.getData().getDataList().get(i).get_id();
                markHaveReadFlag(mNoticeGid);
            }
        });
    }

    /**
     * 获取消息数据
     */
    private void postData() {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookies = new PersistentCookieStore(this);
        client.setCookieStore(cookies);
        RequestParams params = new RequestParams();
        params.put("User_GID", mAccountGid);
        params.put("CY_GID", mCommanyGid);
        params.put("PageIndex", 1);
        params.put("PageSize", 10);
        client.post(HttpAPI.API().NOTICE, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Log.i(TAG, "onSuccess: " + responseString);
                mMeassageBean = CommonFun.JsonToObj(responseString, MessageCenterJsonBean.class);
                mAdapter = new MessageCenterAdapter(MessageCenterActivity.this, mMeassageBean);
                mListView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(String msg) {
                Log.i(TAG, "onFailure: " + msg);
            }
        });
    }

    /**
     * 标记为已读
     */
    private void markHaveReadFlag(String noticeGid) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookies = new PersistentCookieStore(this);
        client.setCookieStore(cookies);
        RequestParams params = new RequestParams();
        params.put("User_GID", mAccountGid);
        params.put("CY_GID", mCommanyGid);
        params.put("Notice_GID", noticeGid);
        client.post(HttpAPI.API().FLAG_HAVE_READ, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Log.i(TAG, "onSuccess: " + responseString);

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }


}
