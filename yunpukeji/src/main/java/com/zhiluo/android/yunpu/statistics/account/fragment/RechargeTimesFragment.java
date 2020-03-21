package com.zhiluo.android.yunpu.statistics.account.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.account.activity.TimesRechargeDetailActivity;
import com.zhiluo.android.yunpu.statistics.account.adapter.InflateCountAdapter;
import com.zhiluo.android.yunpu.statistics.account.adapter.RechargeTimesAdapter;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeTimesReportBean;
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.account.event.ScreenConditionEvent;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员充次记录
 * 作者：罗咏哲 on 2017/9/4 15:10.
 * 邮箱：137615198@qq.com
 */

public class RechargeTimesFragment extends LazyLoadFragment implements InflateCountAdapter.OnItemClickListener{
    private View mView;
    //private BaseListView mListView;
    private WaveSwipeRefreshLayout mRefreshLayout;
    private RechargeTimesAdapter mAdapter;
    private RechargeTimesReportBean mReportBean;
    private RechargeTimesReportBean.DataBean.DataListBean mBean;
    private GetValueEvent mEvent = new GetValueEvent();
    private String mVipCondition, mOrder, mServiceName, mBig, mSmall, mDevice, mPayWayCode, mStartTime, mEndTime;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private boolean mIsLoadMore;//加载更多
    private TextView mTvDate,tvnull;
    private String mSmGid;



    private RecyclerView recyclerView;
    private InflateCountAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected int setContentView() {
        return R.layout.fragment_recharge_times;
    }

    @Override
    protected void initView(View view) {
        manager=new LinearLayoutManager(getContext());
        manager.setOrientation(OrientationHelper.VERTICAL);
        tvnull = view.findViewById(R.id.tv_null);
        recyclerView=view.findViewById(R.id.recycler_view_f_r_t);
        recyclerView.setLayoutManager(manager);
        mView = view;
       // mListView = (BaseListView) mView.findViewById(R.id.recharge_time_list_view);
        mRefreshLayout = (WaveSwipeRefreshLayout) mView.findViewById(R.id.recharge_time_refresh);
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
        setListener();
    }

    private void setListener() {
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRechargeTimesReport(mView, 1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getRechargeTimesReport(mView, mRefreshIndex, 10);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(mView.getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
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
     /*   mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    mCurrentPosition = mListView.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });*/
      /*  mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBean = mReportBean.getData().getDataList().get(i);
                Intent intent = new Intent(getActivity(), TimesRechargeDetailActivity.class);
                intent.putExtra("time", mBean);
                getActivity().startActivity(intent);
            }
        });*/

    }

    @Override
    protected void lazyLoad() {
//        getRechargeTimesReport(mView, 1, 10);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(ScreenConditionEvent event) {
        mVipCondition = event.getVipCondition();
        mOrder = event.getOrder();
        mPayWayCode = event.getPayWayCode();
        mDevice = event.getDevice();
        mServiceName = event.getServiceName();
        mStartTime = event.getStartDate();
        mEndTime = event.getEndDate();
        mSmGid = event.getGid();
        if (getUserVisibleHint()) {
            getRechargeTimesReport(mView, 1, 10);
        }
    }


    private void getRechargeTimesReport(final View view, final int index, int pageSize) {

        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("OrderCode", mOrder);
        params.put("VCH_CardName", mVipCondition);
        params.put("MC_Code", mPayWayCode);
        params.put("MC_AmountDisMin", mBig);
        params.put("MC_AmountDisMax", mSmall);
        params.put("MC_ChargeTimeMin", mStartTime);
        params.put("MC_ChargeTimeMax", mEndTime);
        params.put("SG_Name", mServiceName);
        params.put("Device", mDevice);
        params.put("SM_GID", mSmGid);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                RechargeTimesReportBean reportBean = CommonFun.JsonToObj(responseString, RechargeTimesReportBean.class);
                if (mReportBean != null && mIsLoadMore) {
                    List<RechargeTimesReportBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mReportBean.getData().getDataList());
                    list.addAll(reportBean.getData().getDataList());
                    mReportBean.getData().setDataList(list);
                } else {
                    mReportBean = reportBean;
                }
                if (mReportBean.getData().getDataList().size()>0){
                    tvnull.setVisibility(View.GONE);
                }else {
                    tvnull.setVisibility(View.VISIBLE);
                }
                mPageTotal = mReportBean.getData().getPageTotal();
                mEvent.setName1("充次总额（元）");
                mEvent.setName2("充次次数（次）");
                mEvent.setValue1(Decima2KeeplUtil.stringToDecimal(mReportBean.getData()
                        .getStatisticsInfo().getRechargeTimeTotalMoney() + ""));
                mEvent.setValue2(Integer.parseInt(mReportBean.getData().getStatisticsInfo().getTotalTimesNum()) + "");
                EventBus.getDefault().post(mEvent);//发布事件
                updateView(view,index);
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setLoading(false);
                mIsLoadMore = false;
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mView.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(mView.getContext(),"加载中...",false);
        HttpHelper.post(getActivity(), HttpAPI.API().REPORT_RECHARGE_TIMES, params, callBack);
    }

    private void updateView(View view, int index) {

        if (adapter ==null||index ==1){
            adapter = new InflateCountAdapter(getContext(), mReportBean,RechargeTimesFragment.this);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.setParams(mReportBean);
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void itemClick(int position) {
        mBean = mReportBean.getData().getDataList().get(position);
        Intent intent = new Intent(getActivity(), TimesRechargeDetailActivity.class);
        intent.putExtra("time", mBean);
        getActivity().startActivity(intent);
    }
}
