package com.zhiluo.android.yunpu.statistics.rebate.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.account.event.ScreenConditionEvent;
import com.zhiluo.android.yunpu.statistics.account.fragment.LazyLoadFragment;
import com.zhiluo.android.yunpu.statistics.rebate.activity.RebateListDetaillActivity;
import com.zhiluo.android.yunpu.statistics.rebate.adapter.RebateListAdapter;
import com.zhiluo.android.yunpu.statistics.rebate.bean.RebateListReportBean;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.LoadingDialogUtil;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐列表
 * 作者：罗咏哲 on 2017/9/4 15:11.
 * 邮箱：137615198@qq.com
 */

public class RebateListFragment extends LazyLoadFragment {
    private BaseListView mListView;
    private WaveSwipeRefreshLayout mRefreshLayout;
    private View mView;
    private String mStartTime, mEndTime;
    private RebateListReportBean mReportBean;
    private GetValueEvent mEvent = new GetValueEvent();
    private RebateListAdapter mAdapter;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private int mCurrentPosition;//当前滑动位置
    private boolean mIsLoadMore;//加载更多
    private TextView mTvDate,tvnull;
    private RebateListReportBean.DataBean.DataListBean mBean;
    private String mSmGid;

    @Override
    protected int setContentView() {
        return R.layout.fragment_rebate_list;
    }

    @Override
    protected void initView(View view) {
        mView = view;
        tvnull = mView.findViewById(R.id.tv_null);
        mListView = (BaseListView) mView.findViewById(R.id.rebate_list_list_view);
        mRefreshLayout = (WaveSwipeRefreshLayout) mView.findViewById(R.id.rebate_list_refresh);
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


    @Override
    protected void lazyLoad() {

        if (!EventBus.getDefault().isRegistered(this)){
            //订阅事件
            EventBus.getDefault().register(this);
        }
        getRecommendListData(mView, 1, 10);
    }

    @Override
    protected void stopLoad() {
        super.stopLoad();

        if (EventBus.getDefault().isRegistered(this)){
            //取消注册事件
            EventBus.getDefault().unregister(this);
        }

    }

    private void updateView(View view) {
        if (mAdapter ==null){
            mAdapter = new RebateListAdapter(view.getContext(), mReportBean);
            mListView.setAdapter(mAdapter);
        }else {
            mAdapter.setParams(mReportBean);
            mAdapter.notifyDataSetChanged();
        }
//        mListView.setSelection(mCurrentPosition);
    }

    private void setListener() {
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRecommendListData(mView, 1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getRecommendListData(mView, mRefreshIndex, 10);
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
//        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView absListView, int i) {
//                if (i == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
//                    mCurrentPosition = mListView.getFirstVisiblePosition();
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//
//            }
//        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBean = mReportBean.getData().getDataList().get(i);
                Intent intent = new Intent(getActivity(), RebateListDetaillActivity.class);
                intent.putExtra("rebate", mBean);
                getActivity().startActivity(intent);
            }
        });
    }

    private void getRecommendListData(final View view, int index, int pageSize) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(view.getContext());
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("VIP_Referee", "");
        params.put("VIP_StartDate", mStartTime);
        params.put("VIP_EndDate", mEndTime);
        params.put("SM_GID", mSmGid);
        final Dialog mDialog = LoadingDialogUtil.createLoadingDialog(getActivity(), "加载中...", false);
        client.post(HttpAPI.API().REPORT_RECOMEND_LIST, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mDialog.dismiss();
                RebateListReportBean reportBean = CommonFun.JsonToObj(responseString, RebateListReportBean.class);
                if (mReportBean != null && mIsLoadMore) {
                    List<RebateListReportBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mReportBean.getData().getDataList());
                    list.addAll(reportBean.getData().getDataList());
                    mReportBean.getData().setDataList(list);
                } else {
                    mReportBean = reportBean;
                }
                mPageTotal = mReportBean.getData().getPageTotal();
                mEvent.setName1("推荐合计（人）");
                mEvent.setName2("");
                mEvent.setValue1(mReportBean.getData().getDataCount() + "");
                mEvent.setValue2("");
                EventBus.getDefault().post(mEvent);//发布事件

                if (mReportBean.getData().getDataList().size()>0){
                    tvnull.setVisibility(View.GONE);
                }else {
                    tvnull.setVisibility(View.VISIBLE);
                }
                updateView(view);
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setLoading(false);
                mIsLoadMore = false;
            }

            @Override
            public void onFailure(String msg) {
                mDialog.dismiss();
                CustomToast.makeText(mView.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(ScreenConditionEvent event) {
        mStartTime = event.getStartDate();
        mEndTime = event.getEndDate();
        mSmGid = event.getGid();
        if (getUserVisibleHint()){
            getRecommendListData(mView, 1, 10);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            //取消注册事件
            EventBus.getDefault().unregister(this);
        }
    }
}
