package com.zhiluo.android.yunpu.statistics.order.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.member.manager.activity.MemberSignDetailActivity;
import com.zhiluo.android.yunpu.statistics.account.adapter.MemberSignAdapter;
import com.zhiluo.android.yunpu.statistics.account.bean.LimitedConsumeReportBean;
import com.zhiluo.android.yunpu.statistics.account.bean.MemberSignReportBean;
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.account.event.ScreenConditionEvent;
import com.zhiluo.android.yunpu.statistics.account.fragment.LazyLoadFragment;
import com.zhiluo.android.yunpu.statistics.account.fragment.MemberSignFragment;
import com.zhiluo.android.yunpu.statistics.order.activity.LimitedCosumeReportDetailActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.order.adapter.LimitedConsumeFragmentAdapter;
import com.zhiluo.android.yunpu.statistics.order.event.PointExchangeEvent;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZPH on 2019-05-17.
 */

public class LimitedConsumeFragment extends LazyLoadFragment implements LimitedConsumeFragmentAdapter.OnItemClickListener{

    private View mView;
    private RecyclerView recyclerViewSign;
    private WaveSwipeRefreshLayout memberSignRefresh;
    private GetValueEvent mEvent = new GetValueEvent();

    private LinearLayoutManager manager;
    private TextView mTvDate,tvNull;
    private String mSmGid;
    private String  mVipCondition, Device, mCreator,mStartTime, mEndTime;

    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private boolean mIsLoadMore;//加载更多
    private LimitedConsumeReportBean mReportBean;
    private LimitedConsumeReportBean.DataBean.DataListBean mBean;
    private LimitedConsumeFragmentAdapter adapter;
    private LoginUpbean mLoginBean;
    private String memberCard;


    @Override
    protected int setContentView() {
        return R.layout.fragment_member_sign;
    }

    @Override
    protected void initView(View view) {
        manager=new LinearLayoutManager(getContext());
        manager.setOrientation(OrientationHelper.VERTICAL);
        tvNull = view.findViewById(R.id.tv_null);
        recyclerViewSign=view.findViewById(R.id.recycler_view_sign);
        recyclerViewSign.setLayoutManager(manager);
        mView = view;
        // mListView = (BaseListView) mView.findViewById(R.id.recharge_time_list_view);
        memberSignRefresh = (WaveSwipeRefreshLayout) mView.findViewById(R.id.member_sign_refresh);
        mTvDate = (TextView) getActivity().findViewById(R.id.tv_title_date);
        switch (mTvDate.getText().toString()) {
            case "今日":
                mStartTime = DateTimeUtil.getNowDate();
                mEndTime = DateTimeUtil.getNowDate();
                break;
            case "昨日":
                mStartTime = DateTimeUtil.getLastDate();
                mEndTime = DateTimeUtil.getLastDate();
                break;
            case "本周":
                mStartTime = DateTimeUtil.getNowWeekFirstDate();
                mEndTime = DateTimeUtil.getNowWeekFinalDate();
                break;
            case "其它":
                break;
            default:
                String[] str = mTvDate.getText().toString().split("\\t");
                mStartTime = str[0];
                mEndTime = str[1];
                break;
        }
        mLoginBean = (LoginUpbean) CacheData.restoreObject("LG");
        setListener();

    }

    private void setListener() {

        memberSignRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLimitedConsumeReport(mView, 1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getLimitedConsumeReport(mView, mRefreshIndex, 10);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(mView.getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                    memberSignRefresh.setLoading(false);
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


    }

    private void getLimitedConsumeReport(final View view, final int index, int pageSize){

        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("VipCardName", mVipCondition);
        params.put("StartTime", mStartTime);
        params.put("EndTime", mEndTime);
        params.put("Device", Device);
        params.put("SM_GID", mSmGid);
        params.put("Operator", mCreator);
        if (memberCard !=null && !memberCard.equals("")){
            params.put("VIP_Card",memberCard);
        }
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                LimitedConsumeReportBean reportBean = CommonFun.JsonToObj(responseString, LimitedConsumeReportBean.class);
                if (mReportBean != null && mIsLoadMore) {
                    List<LimitedConsumeReportBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mReportBean.getData().getDataList());
                    list.addAll(reportBean.getData().getDataList());
                    mReportBean.getData().setDataList(list);
                } else {
                    mReportBean = reportBean;
                }
                if (mReportBean.getData().getDataList().size()>0){
                    tvNull.setVisibility(View.GONE);
                }else {
                    tvNull.setVisibility(View.VISIBLE);
                }
                mPageTotal = mReportBean.getData().getPageTotal();

                mEvent.setName1("");
                mEvent.setName2("");
                mEvent.setValue1("");
                mEvent.setValue2("");
                EventBus.getDefault().post(mEvent);//发布事件

                updateView(view,index);
                memberSignRefresh.setRefreshing(false);
                memberSignRefresh.setLoading(false);
                mIsLoadMore = false;
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mView.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(mView.getContext(),"加载中...",false);
        HttpHelper.post(getActivity(), HttpAPI.API().XSXF_History, params, callBack);

    }

    private void updateView(View view, int index) {

        if (adapter ==null||index ==1){
            adapter = new LimitedConsumeFragmentAdapter(getContext(), mReportBean,LimitedConsumeFragment.this);
            recyclerViewSign.setAdapter(adapter);
        }else {
            adapter.setParams(mReportBean);
            adapter.notifyDataSetChanged();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(PointExchangeEvent event) {
        mStartTime = event.getStartTime();
        mEndTime = event.getEndTime();
        mCreator = event.getOperation();
        mVipCondition = event.getVip();
        Device = event.getDevice();
        if (event.getGid() ==null){
            if (mLoginBean != null) {
                mSmGid = mLoginBean.getData().getShopID();
            }
        }else {
            mSmGid = event.getGid();
        }
        if (getUserVisibleHint()) {
            getLimitedConsumeReport(mView, 1, 10);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        memberCard = ((OrderActivity) activity).getVipcard();
    }


    @Override
    protected void lazyLoad() {
        if (!EventBus.getDefault().isRegistered(this)){
            //订阅事件
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void stopLoad() {
        super.stopLoad();
        if (EventBus.getDefault().isRegistered(this)){
            //取消注册事件
            EventBus.getDefault().unregister(this);
        }
    }


    @Override
    public void itemClick(int position) {
        mBean = mReportBean.getData().getDataList().get(position);
        Intent intent = new Intent(getActivity(), LimitedCosumeReportDetailActivity.class);
        intent.putExtra("XSXF", mBean);
        getActivity().startActivity(intent);
    }
}
