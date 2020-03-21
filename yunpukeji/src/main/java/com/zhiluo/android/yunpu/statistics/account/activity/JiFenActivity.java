package com.zhiluo.android.yunpu.statistics.account.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.account.adapter.NewIntegralDetailAdapter;
import com.zhiluo.android.yunpu.statistics.account.bean.IntegralDetailReportBean;
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.rebate.activity.RebateActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YSL on 2018-06-08.
 */

public class JiFenActivity extends AppCompatActivity {
    private WaveSwipeRefreshLayout mRefreshLayout;
    private IntegralDetailReportBean mReportBean;
    private IntegralDetailReportBean.DataBean.DataListBean mBean;
    private GetValueEvent mEvent = new GetValueEvent();
    // private IntegralDetailAdapter mAdapter;
    private String mVipCondition, mOrder, mStartTime, mEndTime, mSmall, mBig;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private boolean mIsLoadMore;//加载更多
    private TextView mTvDate;
    private String mSmGid;

    private ExpandableListView listView;
    private NewIntegralDetailAdapter adapter;
    private TextView tvBack,tvDate,tvStartTime,tvEndTime,tvChuZhi,tvnull;
    private LinearLayout lDate,lShaoXuan;
    private Button btComfirm;
    private Dialog dateDialog;
    private Dialog chooseDialog;
    private List<String> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuzhi);
        initViews();
        initData();
        setListener();
    }

    private void initViews() {
        listView = findViewById(R.id.listview);
        mRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.recharge_money_refresh);
        tvBack = findViewById(R.id.tv_back);
        tvDate = findViewById(R.id.tv_date);
        lDate = findViewById(R.id.ll_order_date_selector);
        lShaoXuan = findViewById(R.id.l_shaixuan);
        tvStartTime = findViewById(R.id.tv_start_date);
        tvEndTime = findViewById(R.id.tv_end_date);
        btComfirm = findViewById(R.id.btn_date_confirm);
        tvChuZhi = findViewById(R.id.tv_titel);
        tvnull = findViewById(R.id.tv_null);
        lDate.setVisibility(View.GONE);
        tvChuZhi.setText("积分明细");
    }

    private void getIntegralDetailData( int index, int pageSize) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("SM_GID", "");
        params.put("IdentifyingState", "");
        params.put("VIP_GID", mVipCondition);
        params.put("StartDate", mStartTime);
        params.put("EndDate", mEndTime);
        params.put("IntegralMin", mSmall);
        params.put("IntegralMax", mBig);
        params.put("SM_GID", mSmGid);
        client.post(HttpAPI.API().REPORT_POINT_DETAIL, params, new MyTextHttpResponseHandler() {
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
                if (mReportBean.getData().getDataList().size() > 0) {
                    tvnull.setVisibility(View.GONE);
                } else {
                    tvnull.setVisibility(View.VISIBLE);
                }
                updateView();
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setLoading(false);
                mIsLoadMore = false;


            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(JiFenActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setListener() {
        btComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (TextUtils.isEmpty(tvStartTime.getText())) {
                        CustomToast.makeText(JiFenActivity.this, "请选择开始时间", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(tvEndTime.getText())) {
                        CustomToast.makeText(JiFenActivity.this, "请选择结束时间", Toast.LENGTH_SHORT).show();
                    } else if (DateTimeUtil.isOverTime(JiFenActivity.this, mStartTime, mEndTime)) {
                        if ( !DateTimeUtil.isCurTime(JiFenActivity.this,mStartTime)){
                            return;
                        }else if (!DateTimeUtil.isCurTime(JiFenActivity.this,mEndTime)){
                            return;
                        }
                        lDate.setVisibility(View.GONE);
                        mStartTime = tvStartTime.getText().toString();
                        mEndTime = tvEndTime.getText().toString();
                        getIntegralDetailData(1, 10);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tvStartTime.getText().toString() == null || tvStartTime.getText().toString().equals("")) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvStartTime,0);
                } else {
                    showDateDialog(DateUtil.getDateForString(tvStartTime.getText().toString()), tvStartTime,0);
                }
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvStartTime);
            }
        });
        tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tvEndTime.getText().toString() == null || tvEndTime.getText().toString().equals("")) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvEndTime,1);
                } else {
                    showDateDialog(DateUtil.getDateForString(tvEndTime.getText().toString()), tvEndTime,1);
                }
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvEndTime);
            }
        });
        lShaoXuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(list, tvDate);
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JiFenActivity.this.finish();
            }
        });
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getIntegralDetailData(1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getIntegralDetailData( mRefreshIndex, 10);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(JiFenActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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
    }
    private void initData() {
        list = new ArrayList<>();
        list.add("今日");
        list.add("昨日");
        list.add("本周");
        list.add("其它");
        mVipCondition = getIntent().getStringExtra("vipcard");
        getIntegralDetailData(1, 10);
    }
    private void showChooseDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0;i<mlist.size();i++){
            if (mlist.get(i).equals(mTextView.getText().toString())){
                j= i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        switch (itemValue) {
                            case "今日":
                                mStartTime = DateTimeUtil.getNowDate();
                                mEndTime = DateTimeUtil.getNowDate();
                                lDate.setVisibility(View.GONE);
                                getIntegralDetailData(1, 10);
                                break;
                            case "昨日":
                                mStartTime = DateTimeUtil.getLastDate();
                                mEndTime = DateTimeUtil.getLastDate();
                                lDate.setVisibility(View.GONE);
                                getIntegralDetailData(1, 10);
                                break;
                            case "本周":
                                mStartTime = DateTimeUtil.getNowWeekFirstDate();
                                mEndTime = DateTimeUtil.getNowWeekFinalDate();
                                lDate.setVisibility(View.GONE);
                                getIntegralDetailData(1, 10);
                                break;
                            case "其它":
                                lDate.setVisibility(View.VISIBLE);
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    private void showDateDialog(List<Integer> date, final TextView mTextView,final int tag) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                String str = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));
                mTextView.setText(str);
                if (tag ==0){
                    mStartTime = str;
                    DateTimeUtil.isCurTime(JiFenActivity.this,mStartTime);
                }else if (tag ==1){
                    mEndTime = str;
                    DateTimeUtil.isCurTime(JiFenActivity.this,mEndTime);
                }

            }

            @Override
            public void onCancel() {

            }
        })

                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);
        dateDialog = builder.create();
        dateDialog.show();
    }

    private void updateView() {
        if (adapter ==null){
            adapter=new NewIntegralDetailAdapter(mReportBean,this);
            listView.setAdapter(adapter);
            listView.setGroupIndicator(null);
        }else {
            adapter.setParams(mReportBean);
            adapter.notifyDataSetChanged();
        }

    }
}
