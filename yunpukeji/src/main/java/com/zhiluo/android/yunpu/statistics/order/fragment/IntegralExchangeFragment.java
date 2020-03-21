package com.zhiluo.android.yunpu.statistics.order.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.account.fragment.LazyLoadFragment;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.PointExchangeDetailActivity;
import com.zhiluo.android.yunpu.statistics.order.adapter.KuaiSuRecoderAdapter;
import com.zhiluo.android.yunpu.statistics.order.event.PointExchangeEvent;
import com.zhiluo.android.yunpu.statistics.rebate.adapter.IntegralRecoderAdapter;
import com.zhiluo.android.yunpu.statistics.rebate.bean.IntegralNewBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/7 15:14.
 * 邮箱：137615198@qq.com
 */

public class IntegralExchangeFragment extends LazyLoadFragment implements IntegralRecoderAdapter.OnItemClickListener{
    // private BaseListView mListView;
    private WaveSwipeRefreshLayout mRefreshLayout;
    private View mView;
    private String mStartTime, mEndTime;
   // private PointExchangeReportAdapter mAdapter;
    private IntegralNewBean mReportBean;
    private IntegralNewBean.DataBean.DataListBean mBean;
    private GetValueEvent mEvent = new GetValueEvent();
    private String mVipCondition, mOrder, mDevice,mOperat;

    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private int mCurrentPosition;//当前滑动位置
    private boolean mIsLoadMore;//加载更多
    private TextView mTvDate,tvnull;
    private String mSmGid;
    private LoginUpbean mLoginBean;

    private IntegralRecoderAdapter adapter;
    private RecyclerView recyclerView;
    private String memberCard ;

    @Override
    protected int setContentView() {
        return R.layout.fragment_integral_exchange;
    }

    @Override
    protected void initView(View view) {
        mView = view;
        recyclerView = view.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        //  mListView = (BaseListView) mView.findViewById(R.id.point_exchange_list_view);
        mRefreshLayout = (WaveSwipeRefreshLayout) mView.findViewById(R.id.point_exchange_refresh);
        mTvDate = (TextView) getActivity().findViewById(R.id.tv_title_date);
        tvnull = view.findViewById(R.id.tv_null);
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


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        memberCard = ((OrderActivity) activity).getVipcard();
    }
    @Override
    protected void lazyLoad() {

        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);//订阅事件
        }
//        getPointExchangeData(mView, 1, 10);
    }

    @Override
    protected void stopLoad() {
        super.stopLoad();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    private void setListener() {
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPointExchangeData(mView, 1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getPointExchangeData(mView, mRefreshIndex, 10);
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
                Intent intent = new Intent(getActivity(), PointExchangeDetailActivity.class);
                intent.putExtra("point", mBean);
                getActivity().startActivity(intent);
            }
        });*/
    }

    private void getPointExchangeData(final View view, final int index, int pageSize) {
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("IEG_OrderNo", mOrder);
        params.put("Operator", mOperat);
        params.put("VIP_CardName", mVipCondition);
        if (memberCard !=null && !memberCard.equals("")){
            params.put("VIP_Card",memberCard);
        }
        params.put("Device", mDevice);
        params.put("StartTime", mStartTime);
        params.put("EndTime", mEndTime);
        params.put("SM_GID", mSmGid);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                IntegralNewBean reportBean = CommonFun.JsonToObj(responseString, IntegralNewBean.class);

                if (mReportBean != null && mIsLoadMore) {
                    List<IntegralNewBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mReportBean.getData().getDataList());
                    list.addAll(reportBean.getData().getDataList());
                    mReportBean.getData().setDataList(list);
                } else {
                    mReportBean = reportBean;
                }
                mPageTotal = mReportBean.getData().getPageTotal();
                mEvent.setName1("已兑换积分");
                mEvent.setName2("剩余积分");
                mEvent.setValue1(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getStatisticsInfo()
                        .getSA_ExchangedScore()));
                mEvent.setValue2(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getStatisticsInfo()
                        .getSA_RemainingScore()));
                EventBus.getDefault().post(mEvent);//发布事件
                if (mReportBean.getData().getDataList().size() > 0) {
                    tvnull.setVisibility(View.GONE);
                } else {
                    tvnull.setVisibility(View.VISIBLE);
                }
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
        callBack.setLoadingAnimation(getActivity(),"加载中...",false);
        HttpHelper.post(getActivity(), HttpAPI.API().REPORT_POINT_EXCHANGE, params, callBack);

    }

    private void updateView(View view, int index) {
        if (adapter==null||index ==1){
            adapter = new IntegralRecoderAdapter(view.getContext(),mReportBean,this);
            recyclerView.setAdapter(adapter);
        }else {
            adapter.setParam(mReportBean);
            adapter.notifyDataSetChanged();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(PointExchangeEvent event) {
        mStartTime = event.getStartTime();
        mEndTime = event.getEndTime();
        mOperat = event.getOperation();
        mVipCondition = event.getVip();
        mOrder = event.getOrder();
        mDevice = event.getDevice();
        if (event.getGid() ==null){
            if (mLoginBean != null) {
                mSmGid = mLoginBean.getData().getShopID();
            }
        }else {
            mSmGid = event.getGid();
        }
        if (getUserVisibleHint()) {
            getPointExchangeData(mView, 1, 10);
        }
    }

    @Override
    public void itemClick(int position) {
        mBean = mReportBean.getData().getDataList().get(position);
        Intent intent = new Intent(getActivity(), PointExchangeDetailActivity.class);
        intent.putExtra("point", mBean);
        getActivity().startActivity(intent);
    }
}
