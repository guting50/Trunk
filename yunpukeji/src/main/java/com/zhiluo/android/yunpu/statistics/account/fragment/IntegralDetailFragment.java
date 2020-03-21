package com.zhiluo.android.yunpu.statistics.account.fragment;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;
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
import com.zhiluo.android.yunpu.statistics.account.adapter.NewIntegralDetailAdapter;
import com.zhiluo.android.yunpu.statistics.account.bean.IntegralDetailReportBean;
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.account.event.ScreenConditionEvent;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分明细记录
 * 作者：罗咏哲 on 2017/9/4 15:11.
 * 邮箱：137615198@qq.com
 */

public class IntegralDetailFragment extends LazyLoadFragment {
    //private BaseListView mListView;
    private WaveSwipeRefreshLayout mRefreshLayout;
    private View mView;
    private IntegralDetailReportBean mReportBean;
    private IntegralDetailReportBean.DataBean.DataListBean mBean;
    private GetValueEvent mEvent = new GetValueEvent();
   // private IntegralDetailAdapter mAdapter;
    private String mVipCondition, mOrder, mStartTime, mEndTime, mSmall, mBig,mJiFenCode;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private int mCurrentPosition;//当前滑动位置
    private boolean mIsLoadMore;//加载更多
    private TextView mTvDate,tvnull;
    private String mSmGid;

    private ExpandableListView listView;
    private NewIntegralDetailAdapter adapter;
    private SharedPreferences sp;

    @Override
    protected int setContentView() {
        return R.layout.fragment_integral_detail;
    }

    @Override
    protected void initView(View view) {
        mView = view;
        listView=mView.findViewById(R.id.list_view);
        tvnull = mView.findViewById(R.id.tv_null);
      //  mListView = (BaseListView) mView.findViewById(R.id.integral_list_view);
        mRefreshLayout = (WaveSwipeRefreshLayout) mView.findViewById(R.id.integral_refresh);
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
                getIntegralDetailData(mView, 1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getIntegralDetailData(mView, mRefreshIndex, 10);
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
      /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBean = mReportBean.getData().getDataList().get(i);
                Intent intent = new Intent(getActivity(), IntegralDetailActivity.class);
                intent.putExtra("point", mBean);
                getActivity().startActivity(intent);
            }
        });*/
    }

    @Override
    protected void lazyLoad() {
        if (!EventBus.getDefault().isRegistered(this)){
            //订阅事件
            EventBus.getDefault().register(this);
        }
//        getIntegralDetailData(mView, 1, 10);
    }

    @Override
    protected void stopLoad() {
        super.stopLoad();
        if (EventBus.getDefault().isRegistered(this)){
            //取消注册事件
            EventBus.getDefault().unregister(this);
        }
    }

    private void updateView(int index) {

        if (adapter ==null ||index==1){
            adapter = new NewIntegralDetailAdapter(mReportBean,mView.getContext());
            listView.setGroupIndicator(null);
            listView.setAdapter(adapter);
        }else {
            adapter.setParams(mReportBean);
            adapter.notifyDataSetChanged();
        }

    }

    private void getIntegralDetailData(View view, final int index, int pageSize) {

        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("IdentifyingState", mJiFenCode);
        params.put("VCH_CardName", mVipCondition);
        params.put("StartDate", mStartTime);
        params.put("EndDate", mEndTime);
        params.put("IntegralMin", mSmall);
        params.put("IntegralMax", mBig);
        params.put("SM_GID", mSmGid);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                IntegralDetailReportBean reportBean = CommonFun.JsonToObj(responseString, IntegralDetailReportBean.class);
                if (mReportBean != null && mIsLoadMore) {
                    List<IntegralDetailReportBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mReportBean.getData().getDataList());
                    list.addAll(reportBean.getData().getDataList());
                    mReportBean.getData().setDataList(list);
                } else {
                    mReportBean = reportBean;
                }
                mPageTotal = mReportBean.getData().getPageTotal();
                mEvent.setName1("赠送积分(分)");
                mEvent.setName2("订单数");
                if (mReportBean.getData().getStatisticsInfo().size()>0){
                    mEvent.setValue1(mReportBean.getData().getStatisticsInfo().get(0).getIntegral() +"");
                }else {
                    mEvent.setValue1("0");
                }
                mEvent.setValue2(mReportBean.getData().getDataCount() +"");
                EventBus.getDefault().post(mEvent);//发布事件
                if (mReportBean.getData().getDataList().size()>0){
                    tvnull.setVisibility(View.GONE);
                }else {
                    tvnull.setVisibility(View.VISIBLE);
                }
                updateView(index);
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
        HttpHelper.post(getActivity(),HttpAPI.API().REPORT_POINT_DETAIL, params, callBack);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(ScreenConditionEvent event) {
        mVipCondition = event.getVipCondition();
        mStartTime = event.getStartDate();
        mEndTime = event.getEndDate();
        mBig = event.getBig();
        mSmall = event.getSmall();
        mSmGid = event.getGid();
        mJiFenCode = event.getPayWayCode();
        if (getUserVisibleHint()) {
            getIntegralDetailData(mView, 1, 10);
        }
    }
}
