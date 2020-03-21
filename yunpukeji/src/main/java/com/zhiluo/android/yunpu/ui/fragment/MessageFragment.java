package com.zhiluo.android.yunpu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.home.activity.MessageContentActivity;
import com.zhiluo.android.yunpu.home.adapter.MessageCenterAdapter;
import com.zhiluo.android.yunpu.home.jsonbean.MessageCenterJsonBean;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.mvp.presenter.MessageFragmentPresenter;
import com.zhiluo.android.yunpu.mvp.view.IMessageFragmentView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import org.greenrobot.eventbus.EventBus;

/**
 * 消息
 * 作者：罗咏哲 on 2017/10/23 18:17.
 * 邮箱：137615198@qq.com
 */

public class MessageFragment extends Fragment {
    private ListView mListView;
    private String mAccountGid, mCommanyGid;
    private MessageFragmentPresenter mPresenter;
    private IMessageFragmentView mView;
    private MessageCenterAdapter mAdapter;
    private MessageCenterJsonBean mMessageBean;
    private String mNoticeGid;//消息GID

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage_message, container, false);
        initView(view);
        loadData(1, 10);
        setListener();
        return view;
    }

    private void initView(View view) {
        mListView = (ListView) view.findViewById(R.id.lv_message_fragment);
        mPresenter = new MessageFragmentPresenter(getContext());
        mPresenter.onCreate("");
        mView = new IMessageFragmentView() {
            @Override
            public void getMessageListSuccess(MessageCenterJsonBean bean) {
                mMessageBean = bean;
                mAdapter = new MessageCenterAdapter(getContext(), mMessageBean);
                mListView.setAdapter(mAdapter);
                EventBus.getDefault().post(bean);
            }

            @Override
            public void getMessageListFail(String result) {
                CustomToast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void markSuccess() {
                RequestParams params = new RequestParams();
                params.put("User_GID", mAccountGid);
                params.put("CY_GID", mCommanyGid);
                params.put("PageIndex", 1);
                params.put("PageSize", 10);
                mPresenter.getMessageList(params);
            }

            @Override
            public void markFail(String result) {
//                CustomToast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }
        };
        mPresenter.attachView(mView);
    }

    private void loadData(int index, int size) {
        mAccountGid = (String) uSharedPreferencesUtiles.get(getContext(),
                "Account_GID", "");
        mCommanyGid = (String) uSharedPreferencesUtiles.get(getContext(),
                "CY_GID", "");
        RequestParams params = new RequestParams();
        params.put("User_GID", mAccountGid);
        params.put("CY_GID", mCommanyGid);
        params.put("PageIndex", index);
        params.put("PageSize", size);
        mPresenter.getMessageList(params);
    }

    private void setListener() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), MessageContentActivity.class);
                intent.putExtra("content", mMessageBean.getData().getDataList().get(position).getContent());
                startActivity(intent);
                if (mMessageBean.getData().getDataList().get(position).getPopState() != 1) {
                    mNoticeGid = mMessageBean.getData().getDataList().get(position).get_id();
                    RequestParams params = new RequestParams();
                    params.put("User_GID", mAccountGid);
                    params.put("CY_GID", mCommanyGid);
                    params.put("Notice_GID", mNoticeGid);
                    mPresenter.markHaveReadFlag(params);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HttpHelper.getmClient().cancelRequests(getContext(), true);
    }
}
