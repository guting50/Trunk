package com.zhiluo.android.yunpu.member.manager.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.activity.MemberRecordActivity;
import com.zhiluo.android.yunpu.statistics.account.fragment.LazyLoadFragment;
import com.zhiluo.android.yunpu.statistics.order.activity.FastConsumeDetailActivity;
import com.zhiluo.android.yunpu.statistics.order.adapter.FastConsumeReportAdapter;
import com.zhiluo.android.yunpu.statistics.order.bean.FastConsumeReportBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class FastConsumeRecordFragment extends LazyLoadFragment {

    @Bind(R.id.lv_fast_consume_record)
    ListView mListView;
    @Bind(R.id.refresh_fast_consume_record)
    WaveSwipeRefreshLayout mRefreshLayout;
    private String mSmGid, mCard;
    private FastConsumeReportAdapter mAdapter;
    private FastConsumeReportBean mReportBean;
    private FastConsumeReportBean.DataBean.DataListBean mBean;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private int mCurrentPosition;//当前滑动位置
    private boolean mIsLoadMore;//加载更多

    @Override
    protected int setContentView() {
        return R.layout.fragment_fast_consume_record;
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
        setListener();
    }

    @Override
    protected void lazyLoad() {
        initVariable();
    }

    private void initVariable() {
        mSmGid = (String) uSharedPreferencesUtiles.get(getContext(), "StoreGid", "");
        getFastConsumeData(1, 20);
    }

    private void setListener() {
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getFastConsumeData(1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getFastConsumeData(mRefreshIndex, 20);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                    mRefreshLayout.setLoading(false);
                }
            }

            @Override
            public boolean canLoadMore() {
                return true;
            }

            @Override
            public boolean canRefresh() {
                return true;
            }
        });
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    mCurrentPosition = mListView.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBean = mReportBean.getData().getDataList().get(i);
                Intent intent = new Intent(getActivity(), FastConsumeDetailActivity.class);
                intent.putExtra("fast", mBean);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCard = ((MemberRecordActivity) activity).getmVipCard();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void getFastConsumeData(int index, int pageSize) {
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("VIP_CardName", mCard);
        params.put("SM_GID", mSmGid);
        HttpHelper.post(getContext(), HttpAPI.API().History, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                FastConsumeReportBean reportBean = CommonFun.JsonToObj(responseString, FastConsumeReportBean.class);
                if (mReportBean != null && mIsLoadMore) {
                    List<FastConsumeReportBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mReportBean.getData().getDataList());
                    list.addAll(reportBean.getData().getDataList());
                    mReportBean.getData().setDataList(list);
                } else {
                    mReportBean = reportBean;
                }
                mPageTotal = mReportBean.getData().getPageTotal();
                mAdapter = new FastConsumeReportAdapter(MyApplication.getmContext(), mReportBean);
                if (mListView != null) {
                    mListView.setAdapter(mAdapter);
                mListView.setSelection(mCurrentPosition);}
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setLoading(false);
                mIsLoadMore = false;
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
