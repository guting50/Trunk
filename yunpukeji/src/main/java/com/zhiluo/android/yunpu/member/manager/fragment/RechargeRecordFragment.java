package com.zhiluo.android.yunpu.member.manager.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.activity.MemberRecordActivity;
import com.zhiluo.android.yunpu.statistics.account.activity.RechargeMoneyDetailActivity;
import com.zhiluo.android.yunpu.statistics.account.adapter.RechargeMoneyAdapter;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeMoneyReportBean;
import com.zhiluo.android.yunpu.statistics.account.fragment.LazyLoadFragment;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 会员充值记录
 */
public class RechargeRecordFragment extends LazyLoadFragment {

    @Bind(R.id.lv_recharge_record)
    ListView listView;
    @Bind(R.id.refresh_recharge_record)
    WaveSwipeRefreshLayout refresh;
    private RechargeMoneyReportBean mReportBean;
    private String mSmGid, mCard;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private int mCurrentPosition;//当前滑动位置
    private boolean mIsLoadMore;//加载更多
    private RechargeMoneyAdapter mAdapter;
    private RechargeMoneyReportBean.DataBean.DataListBean mBean;

    @Override
    protected int setContentView() {
        return R.layout.fragment_recharge_record;
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

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_recharge_record, container, false);
//        ButterKnife.bind(this, view);
//        initVariable();
//        setListener();
//        return view;
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCard = ((MemberRecordActivity) activity).getmVipCard();
    }

    private void initVariable() {
        mSmGid = (String) uSharedPreferencesUtiles.get(getContext(), "StoreGid", "");
        getRechargeRecord(1, 20);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void setListener() {
        refresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRechargeRecord(1, 20);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getRechargeRecord(mRefreshIndex, 20);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                    refresh.setLoading(false);
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
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    mCurrentPosition = listView.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBean = mReportBean.getData().getDataList().get(i);
                Intent intent = new Intent(getActivity(), RechargeMoneyDetailActivity.class);
                intent.putExtra("recharge", mBean);
                getActivity().startActivity(intent);
            }
        });
    }

    private void getRechargeRecord(int index, int pageSize) {
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("VCH_CardName", mCard);
        params.put("SM_GID", mSmGid);
        HttpHelper.post(getContext(), "Recharge/GetMoneyRecords", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                RechargeMoneyReportBean reportBean = CommonFun.JsonToObj(responseString, RechargeMoneyReportBean.class);
                if (mReportBean != null && mIsLoadMore) {
                    List<RechargeMoneyReportBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mReportBean.getData().getDataList());
                    list.addAll(reportBean.getData().getDataList());
                    mReportBean.getData().setDataList(list);
                } else {
                    mReportBean = reportBean;
                }
                mPageTotal = mReportBean.getData().getPageTotal();
                mAdapter = new RechargeMoneyAdapter(MyApplication.getmContext(), mReportBean);
                if (listView != null) {
                    listView.setAdapter(mAdapter);
                    listView.setSelection(mCurrentPosition);
                }
                refresh.setRefreshing(false);
                refresh.setLoading(false);
                mIsLoadMore = false;
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
