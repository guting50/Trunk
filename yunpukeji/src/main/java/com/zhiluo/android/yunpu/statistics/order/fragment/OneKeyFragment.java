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
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.account.fragment.LazyLoadFragment;
import com.zhiluo.android.yunpu.statistics.order.activity.GoodsOrderDetailActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.YJJYOrderDetailActivity;
import com.zhiluo.android.yunpu.statistics.order.adapter.GoodsRecoderAdapter;
import com.zhiluo.android.yunpu.statistics.order.adapter.YJJYReportAdapter;
import com.zhiluo.android.yunpu.statistics.order.bean.GoodsOrderReportBean;
import com.zhiluo.android.yunpu.statistics.order.bean.YJJYOrderDetailBean;
import com.zhiluo.android.yunpu.statistics.order.event.GoodsScreenEvent;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
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
 * Created by ZPH on 2019-05-31.
 */

public class OneKeyFragment  extends LazyLoadFragment implements YJJYReportAdapter.OnItemClickListener{

    private BaseListView mListView;
    private WaveSwipeRefreshLayout mRefreshLayout;
    private View mView;
    private String mStartTime, mEndTime;
    // private GoodsOrderAdapter mAdapter;
    private YJJYOrderDetailBean mReportBean;
    private YJJYOrderDetailBean.DataBean.DataListBean mBean;
    private GetValueEvent mEvent = new GetValueEvent();
    private String mVipCondition, mOrder, OM_Name, State = "99", mDevice,mOperat;

    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private boolean mIsLoadMore;//加载更多
    private TextView mTvDate, tvnull;
    private String mSmGid;

    private YJJYReportAdapter adapter;
    private RecyclerView recyclerView;
    private int lastOffset;
    private int lastPosition;
    private LoginUpbean mLoginBean;

    @Override
    protected int setContentView() {
        return R.layout.fragment_goods_consume;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mVipCondition = ((OrderActivity) activity).getVipcard();
    }

    @Override
    protected void initView(View view) {
        mView = view;
        //  mListView = (BaseListView) mView.findViewById(R.id.goods_list_view);
        mRefreshLayout = (WaveSwipeRefreshLayout) mView.findViewById(R.id.goods_refresh);
        mTvDate = (TextView) getActivity().findViewById(R.id.tv_title_date);
        tvnull = mView.findViewById(R.id.tv_null);
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
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView = mView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(manager);
        setListener();
    }

    private void setListener() {
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getGoodsOrderData(mView, 1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getGoodsOrderData(mView, mRefreshIndex, 10);
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
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (recyclerView.getLayoutManager() != null) {
                    getPositionAndOffset();
                }
            }
        });

    }

    /**
     * 记录RecyclerView当前位置
     */
    private void getPositionAndOffset() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        //获取可视的第一个view
        View topView = layoutManager.getChildAt(0);
        if (topView != null) {
            //获取与该view的顶部的偏移量
            lastOffset = topView.getTop();
            //得到该View的数组位置
            lastPosition = layoutManager.getPosition(topView);
        }
    }

    private void scrollToPosition() {
        if (recyclerView.getLayoutManager() != null && lastPosition >= 0) {
            ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(lastPosition, lastOffset);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        scrollToPosition();
    }

    @Override
    protected void lazyLoad() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);//订阅事件
        }
        getGoodsOrderData(mView, 1, 10);
    }

    @Override
    protected void stopLoad() {
        super.stopLoad();
    }

    private void getGoodsOrderData(final View view, final int index, int pageSize) {
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("VIPInfo", mVipCondition);
        params.put("OM_Name", OM_Name);
        params.put("OrderCode", mOrder);
        params.put("Creator", mOperat);
        params.put("State", State);
        params.put("StartTime", mStartTime);
        params.put("EndTime", mEndTime);
        params.put("Device", mDevice);
        params.put("SM_GID", mSmGid);

//        params.put("CO_WayCode", mPayWayCode);
//        params.put("CO_GtMonetary", mBig);
//        params.put("CO_LessMonetary", mSmall);
//        params.put("CO_TypeCode", "YJJY");
//        params.put("DeviceType", mDevice);
//        params.put("CO_StartUpdateTime", mStartTime);
//        params.put("CO_EndUpdateTime", mEndTime);
//        params.put("SM_GID", mSmGid);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                YJJYOrderDetailBean reportBean = CommonFun.JsonToObj(responseString, YJJYOrderDetailBean.class);
                if (mReportBean != null && mIsLoadMore) {
                    List<YJJYOrderDetailBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mReportBean.getData().getDataList());
                    list.addAll(reportBean.getData().getDataList());
                    mReportBean.getData().setDataList(list);
                } else {
                    mReportBean = reportBean;
                }
                mPageTotal = mReportBean.getData().getPageTotal();
                mEvent.setName1("消费总计（元）");
                mEvent.setName2("加油数量");
                mEvent.setValue1(Decima2KeeplUtil.stringToDecimal(mReportBean.getData()
                        .getStatisticsInfo().getOrderAmount() + ""));
                mEvent.setValue2(Decima2KeeplUtil.stringToDecimal(mReportBean.getData()
                        .getStatisticsInfo().getOrderNumber() + ""));
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
        HttpHelper.post(getActivity(), HttpAPI.API().YJJY_REPORT, params, callBack);

    }

    private void updateView(View view,int index) {
        if (adapter == null){
            adapter = new YJJYReportAdapter(view.getContext(), mReportBean, this);
            recyclerView.setAdapter(adapter);
        }else {
            if (index ==1){
                adapter = new YJJYReportAdapter(view.getContext(), mReportBean, this);
                recyclerView.setAdapter(adapter);
            }else {
                adapter.setParm(mReportBean);
                adapter.notifyDataSetChanged();
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(GoodsScreenEvent event) {
        mStartTime = event.getStartDate() ==null ? "":event.getStartDate()+" 00:00:00";
        mEndTime = event.getEndDate()==null ? "":event.getEndDate()+" 23:59:59";

        mVipCondition = event.getVipCondition();
        mOrder = event.getOrder();
        OM_Name = event.getOilName();
        mDevice = event.getDevice();

        if (State == null){
            State = "99";
        }else {
            State = event.getState();
        }
        if (event.getGid() ==null){
            if (mLoginBean != null) {
                mSmGid = mLoginBean.getData().getShopID();
            }
        }else {
            mSmGid = event.getGid();
        }
        mOperat = event.getOpreat();
        if (getUserVisibleHint()) {
            getGoodsOrderData(mView, 1, 10);
        }
    }

    @Override
    public void itemClick(int position) {
        mBean = mReportBean.getData().getDataList().get(position);
        Intent intent = new Intent(getActivity(), YJJYOrderDetailActivity.class);
        intent.putExtra("goods", mBean);
        getActivity().startActivity(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
