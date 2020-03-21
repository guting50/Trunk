package com.zhiluo.android.yunpu.ui.activity.good;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsWarehousingAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.historyconsumption.VipRechangeMoney;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.InRecoderAdapter;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.OutRecoderAdapter;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InRecoderBean;
import com.zhiluo.android.yunpu.ui.activity.good.bean.OutRecoderBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;
import com.zhiluo.android.yunpu.yslutils.DataUtils;


import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * Created by ${YSL} on 2018-05-18.
 */

public class OutRecoderActivity extends AppCompatActivity implements OutRecoderAdapter.OnItemClick, View.OnClickListener {
    private OutRecoderAdapter adapter;
    private RecyclerView recyclerView;
    private TextView mTitledates, mStartDates, mEndDates, tvTitle,tvfirst_name,tvnull;
    private Button mDataConfirm;
    private TextView mBack;
    private TextView tvTotalStock;
    private RelativeLayout rlTitleDate;
    private List<OutRecoderBean.DataBean.DataListBean> outRecoderBean;
    private String SM_GID = "";
    private Bundle mBundle;
    private Intent mIntent;
    private double number;
    private RelativeLayout mRTi;
    private LinearLayout mL;
    private Dialog dateDialog;
    private String mStartTime;
    private String mEndTime;
    private Dialog chooseDialog;
    private List<String> list;

    private WaveSwipeRefreshLayout mRefresh;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private boolean mIsLoadMore;//加载更多

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inrecoder);
        initViews();
        initDatas();
        setListener();

    }

    private void setListener() {
        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                obtainData(1, 20);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    obtainData(mRefreshIndex, 20);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(OutRecoderActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    mRefresh.setLoading(false);
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
    private void initDatas() {

//        getGoods();
        list = new ArrayList<>();
        list.add("今日");
        list.add("昨日");
        list.add("本周");
        list.add("其它");

        mStartTime = DateTimeUtil.getNowDate();
        mEndTime = DateTimeUtil.getNowDate();
        obtainData(1, 20);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recycler_activity_inrecoder);
        mBack = findViewById(R.id.tv_back);
        tvTotalStock = findViewById(R.id.tv_toatal_stock);
        rlTitleDate = findViewById(R.id.rl_title_date);
        mTitledates = findViewById(R.id.tv_title_date);//时间段选择
        mRTi = findViewById(R.id.rl_title_date);//时间段选择
        mL = findViewById(R.id.l1);
        tvfirst_name = findViewById(R.id.tv_title_first_name);
        tvfirst_name.setText("出库金额");
        mStartDates = findViewById(R.id.tv_start_date);//选择后的开始时间段
        mEndDates = findViewById(R.id.tv_end_date);//选择后的截止时间段
        mDataConfirm = findViewById(R.id.btn_date_confirm);//确定选择时间段
        tvTitle = findViewById(R.id.tv_title);//设置标题
        mRefresh = (WaveSwipeRefreshLayout) findViewById(R.id.refresh);
        tvnull = findViewById(R.id.tv_null);
        tvTitle.setText("出库记录");
        mBack.setOnClickListener(this);
        mL.setOnClickListener(this);
        mStartDates.setOnClickListener(this);
        mEndDates.setOnClickListener(this);
        mDataConfirm.setOnClickListener(this);
        mRTi.setOnClickListener(this);
    }

    @Override
    public void itemClick(View view, int position) {
        mBundle = new Bundle();
        mIntent = new Intent(this, OutWareItemDetailActivity.class);
        mBundle.putSerializable("Bean", (Serializable) outRecoderBean);
        mIntent.putExtra("position", position);
        mIntent.putExtra("bundle", mBundle);
        startActivity(mIntent);

    }


    private void obtainSM_GID() {
        SM_GID = (String) uSharedPreferencesUtiles.get(this, "StoreGid", "");
    }

    public void getGoods() {
        RequestParams params = new RequestParams();
        params.put("PageIndex", 1);
        params.put("PageSize", 20);
        params.put("PT_GID", "");
        params.put("PM_IsService", "");
        params.put("PM_UnitPriceMin", "");
        params.put("PM_UnitPriceMax", "");
        params.put("PM_SynType", "");
        params.put("PM_CodeOrNameOrSimpleCode", "");
        params.put("DataType", 3);
        HttpHelper.post(this, HttpAPI.API().SERVICEGOODS, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                GoodsCheckResponseByType entity = CommonFun.JsonToObj(responseString, GoodsCheckResponseByType.class);
                for (int i = 0; i < entity.getData().getDataList().size(); i++) {
                    number += entity.getData().getDataList().get(i).getStock_Number();
                }
                tvTotalStock.setText(Decima2KeeplUtil.stringToDecimal(number+""));
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(OutRecoderActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * @param PageIndex 查询页数
     * @param PageSize  每页条数

     *                  查询出库记录
     */
    private void obtainData(int PageIndex, int PageSize) {
        obtainSM_GID();
        RequestParams params = new RequestParams();
        params.put("PageIndex", PageIndex);
        params.put("PageSize", PageSize);
        params.put("OrderNo", "");
        params.put("InType", "1");
        params.put("SM_GID", SM_GID);
        params.put("StartTime", mStartTime);
        params.put("EndTime", mEndTime);
        Le(params.toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                OutRecoderBean entity = CommonFun.JsonToObj(responseString, OutRecoderBean.class);
                mPageTotal = entity.getData().getPageTotal();
                number = 0;
                if (outRecoderBean != null && mIsLoadMore) {
                    outRecoderBean.addAll(entity.getData().getDataList());
                } else {
                    outRecoderBean = entity.getData().getDataList();
                }

                for (int i = 0; i < outRecoderBean.size(); i++) {
                    number += outRecoderBean.get(i).getSO_TotalPrice();
                }
                tvTotalStock.setText(Decima2KeeplUtil.stringToDecimal(number+""));


                mRefresh.setRefreshing(false);
                mRefresh.setLoading(false);
                mIsLoadMore = false;
                if (outRecoderBean.size()>0){
                    tvnull.setVisibility(View.GONE);
                }else {
                    tvnull.setVisibility(View.VISIBLE);
                }
                LinearLayoutManager manager = new LinearLayoutManager(OutRecoderActivity.this);
                manager.setOrientation(OrientationHelper.VERTICAL);

                if (adapter == null){
                    adapter = new OutRecoderAdapter(OutRecoderActivity.this, outRecoderBean, OutRecoderActivity.this);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(adapter);
                }else {
                    adapter.setParm(outRecoderBean);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(OutRecoderActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, "StockManager/GetStockOutDataList", params, callBack);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                OutRecoderActivity.this.finish();
                break;
            case R.id.tv_start_date:
                if (mStartDates.getText().toString() == null || mStartDates.getText().toString().equals("")) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mStartDates,0);
                } else {
                    showDateDialog(DateUtil.getDateForString(mStartDates.getText().toString()), mStartDates,0);
                }
                break;
            case R.id.tv_end_date:
                if (mEndDates.getText().toString() == null || mEndDates.getText().toString().equals("")) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mEndDates,1);
                } else {
                    showDateDialog(DateUtil.getDateForString(mEndDates.getText().toString()), mEndDates,1);
                }
                break;
            case R.id.btn_date_confirm:
                if (mStartDates.getText().toString().isEmpty()) {
                    CustomToast.makeText(this, "请选择开始日期", Toast.LENGTH_SHORT).show();
                    return;
                } else if (mEndDates.getText().toString().isEmpty()) {
                    CustomToast.makeText(this, "请选择截止日期", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if ( !DateTimeUtil.isCurTime(OutRecoderActivity.this,mStartTime)){
                        return;
                    }else if (!DateTimeUtil.isCurTime(OutRecoderActivity.this,mEndTime)){
                        return;
                    }
                    try {
                        if (DateTimeUtil.isOverTime(OutRecoderActivity.this, mStartTime, mEndTime)) {
                    mL.setVisibility(View.GONE);
                    mStartTime = mStartDates.getText().toString();
                    mEndTime = mEndDates.getText().toString();
                    String date = mStartTime + "\t" + mEndTime;
                    mTitledates.setText(date);
                    obtainData(1, 20);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                break;
            case R.id.rl_title_date:
                showChooseDialog(list, mTitledates);

                break;
        }
    }

    private void showChooseDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0;i<mlist.size();i++){
            if (mTextView.getText().toString().equals(mlist.get(i))){
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        switch (position) {
                            case 0:
                                mL.setVisibility(View.GONE);
                                mTitledates.setText("今日");
                                mStartTime = DateTimeUtil.getNowDate();
                                mEndTime = DateTimeUtil.getNowDate();
                                obtainData(1, 20);
                                break;
                            case 1:
                                mL.setVisibility(View.GONE);
                                mTitledates.setText("昨日");
                                mStartTime = DateTimeUtil.getLastDate();
                                mEndTime = DateTimeUtil.getLastDate();
                                obtainData(1, 20);
                                break;
                            case 2:
                                mL.setVisibility(View.GONE);
                                mTitledates.setText("本周");
                                mStartTime = DateTimeUtil.getNowWeekFirstDate();
                                mEndTime = DateTimeUtil.getNowWeekFinalDate();
                                obtainData(1, 20);
                                break;
                            case 3:
                                mL.setVisibility(View.VISIBLE);
                                mTitledates.setText("其它");
                                mStartTime = "";
                                mEndTime = "";
                                obtainData(1, 20);
                                break;
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    private void showDateDialog(List<Integer> date, final TextView textViews,final int tag) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                String str = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));
                textViews.setText(str);
                if (tag ==0){
                    mStartTime = str;
                    DateTimeUtil.isCurTime(OutRecoderActivity.this,mStartTime);
                }else if (tag ==1){
                    mEndTime = str;
                    DateTimeUtil.isCurTime(OutRecoderActivity.this,mEndTime);
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
}
