package com.zhiluo.android.yunpu.statistics.handover;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.statistics.account.event.ScreenConditionEvent;
import com.zhiluo.android.yunpu.statistics.staff.activity.StaffRebateDetailActivity;
import com.zhiluo.android.yunpu.statistics.staff.activity.StaffReportActivity;
import com.zhiluo.android.yunpu.statistics.staff.activity.StaffScreenActivity;
import com.zhiluo.android.yunpu.statistics.staff.adapter.StaffRebateAdapter;
import com.zhiluo.android.yunpu.statistics.staff.bean.StaffRebateReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.handduty.HandDutyActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2018-11-28.
 */

public class HandOverReportActivity extends BaseActivity implements MyMenuPopWindow.OnItemClickListener{


    @Bind(R.id.tv_title_back)
    TextView tvTitleBack;
    @Bind(R.id.tv_title_name)
    TextView tvTitleName;
    @Bind(R.id.iv_title_screen)
    ImageView ivTitleScreen;
    @Bind(R.id.tv_sx)
    TextView tvSx;
    @Bind(R.id.rl_title_title)
    RelativeLayout rlTitleTitle;
    @Bind(R.id.tv_title_date)
    TextView tvTitleDate;
    @Bind(R.id.iv_drop_down)
    ImageView ivDropDown;
    @Bind(R.id.rl_title_date)
    RelativeLayout rlTitleDate;
    @Bind(R.id.tv_title_first_name)
    TextView tvTitleFirstName;
    @Bind(R.id.tv_title_first_value)
    TextView tvTitleFirstValue;
    @Bind(R.id.tv_title_second_name)
    TextView tvTitleSecondName;
    @Bind(R.id.tv_title_second_value)
    TextView tvTitleSecondValue;
    @Bind(R.id.ll_title_search)
    LinearLayout llTitleSearch;
    @Bind(R.id.tv_start_date)
    TextView tvStartDate;
    @Bind(R.id.tv_end_date)
    TextView tvEndDate;
    @Bind(R.id.btn_date_confirm)
    Button btnDateConfirm;
    @Bind(R.id.ll_handover_date_selector)
    LinearLayout llHandoverDateSelector;
    @Bind(R.id.tv_null)
    TextView tvNull;
    @Bind(R.id.list_view)
    BaseListView listView;
    @Bind(R.id.refresh)
    WaveSwipeRefreshLayout refresh;

    private MyMenuPopWindow mMenuWindow;
    private String mStartTime, mEndTime;
    private ScreenConditionEvent mEvent = new ScreenConditionEvent();
    private String mStaffName, mRebateType;//员工姓名，提成类型

    private HandOverReportBean mReportBean;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数

    private boolean mIsLoadMore;//加载更多
    private HandOverReportAdapter mAdapter;
    private HandOverReportBean.DataBean.DataListBean mBean;
    private String mSmGid;
    private List<String> mList;
    private Dialog dateDialog;
    private Dialog chooseDialog;
    private String start;
    private String end;
    private HandOverReportBean handOverReportBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_over_report);
        ButterKnife.bind(this);
        loadData();
        setLisenter();
    }


    private void setLisenter() {

        findViewById(R.id.tv_title_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        mMenuWindow.setOnItemClickListener(this);
        rlTitleDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    mMenuWindow.showAsDropDown(mTvDate);

                showPop(HandOverReportActivity.this, mList, tvTitleDate);
            }
        });
        ivTitleScreen.setVisibility(View.GONE);
        tvSx.setVisibility(View.GONE);
        //条件筛选监听
        ivTitleScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HandOverReportActivity.this, StaffScreenActivity.class);
                startActivityForResult(intent, 666);
            }
        });

        //选择开始时间监听
        tvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvStartDate);
                if (tvStartDate.getText().toString() == null || tvStartDate.getText().toString().equals("")) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvStartDate, 0);
                } else {
                    showDateDialog(DateUtil.getDateForString(tvStartDate.getText().toString()), tvStartDate, 0);
                }
            }
        });
        //选择结束时间监听
        tvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvEndDate.getText().toString() == null || tvEndDate.getText().toString().equals("")) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvEndDate, 1);
                } else {
                    showDateDialog(DateUtil.getDateForString(tvEndDate.getText().toString()), tvEndDate, 1);
                }
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvEndDate);
            }
        });
        btnDateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = tvStartDate.getText().toString();
                end = tvEndDate.getText().toString();
                if (start.isEmpty()) {
                    CustomToast.makeText(HandOverReportActivity.this, "请选择开始时间！", Toast.LENGTH_SHORT).show();
                }
                if (end.isEmpty()) {
                    CustomToast.makeText(HandOverReportActivity.this, "请选择结束时间！", Toast.LENGTH_SHORT).show();
                }
                if (!start.isEmpty() && !end.isEmpty()) {
                    if (!DateTimeUtil.isCurTime(HandOverReportActivity.this, start)) {
                        return;
                    } else if (!DateTimeUtil.isCurTime(HandOverReportActivity.this, end)) {
                        return;
                    }
                    try {
                        if (DateTimeUtil.isOverTime(HandOverReportActivity.this, start, end)) {
                            String date = start + "\t" + end;
                            tvTitleDate.setText(date);
                            llHandoverDateSelector.setVisibility(View.GONE);

                            mEvent.setStartDate(start);
                            mEvent.setEndDate(end);
                            EventBus.getDefault().post(mEvent);//发布事件
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        refresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getReport(1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getReport(mRefreshIndex, 10);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(HandOverReportActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBean = mReportBean.getData().getDataList().get(i);
                String lastmoney;
                if (i == mReportBean.getData().getDataList().size()-1){
                    lastmoney = "0.00";
                }else {
                    lastmoney = mReportBean.getData().getDataList().get(i+1).getSA_IssuedBusiness()+"";
                }
                Intent intent = new Intent(HandOverReportActivity.this, HandOverReportDetail.class);
                intent.putExtra("lastmoney",lastmoney);
                intent.putExtra("handover", (Serializable) mBean);
                startActivity(intent);
            }
        });
    }

    private void showDateDialog(List<Integer> date, final TextView mTextView, final int tag) {
        com.example.liangmutian.mypicker.DatePickerDialog.Builder builder = new com.example.liangmutian.mypicker.DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new com.example.liangmutian.mypicker.DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                String str = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));
                mTextView.setText(str);
                if (tag == 0) {
                    start = str;
                    DateTimeUtil.isCurTime(HandOverReportActivity.this,start);
                } else if (tag == 1) {
                    end = str;
                    DateTimeUtil.isCurTime(HandOverReportActivity.this,start);
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


    public void showPop(Context context, List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0;i<mlist.size();i++){
            if (mlist.get(i).equals(mTextView.getText().toString())){
                j= i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(context);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        switch (itemValue) {
                            case "今日":
                                llHandoverDateSelector.setVisibility(View.GONE);
                                tvTitleDate.setText("今日");
                                mEvent.setStartDate(DateTimeUtil.getNowDate());
                                mEvent.setEndDate(DateTimeUtil.getNowDate());
                                mMenuWindow.dismiss();
                                EventBus.getDefault().post(mEvent);//发布事件
                                break;
                            case "昨日":
                                llHandoverDateSelector.setVisibility(View.GONE);
                                tvTitleDate.setText("昨日");
                                mEvent.setStartDate(DateTimeUtil.getLastDate());
                                mEvent.setEndDate(DateTimeUtil.getLastDate());
                                mMenuWindow.dismiss();
                                EventBus.getDefault().post(mEvent);//发布事件
                                break;
                            case "本周":
                                llHandoverDateSelector.setVisibility(View.GONE);
                                tvTitleDate.setText("本周");
                                mEvent.setStartDate(DateTimeUtil.getNowWeekFirstDate());
                                mEvent.setEndDate(DateTimeUtil.getNowWeekFinalDate());
                                mMenuWindow.dismiss();
                                EventBus.getDefault().post(mEvent);//发布事件
                                break;
                            case "其它":
                                llHandoverDateSelector.setVisibility(View.VISIBLE);
                                tvTitleDate.setText("其它");
                                mEvent.setStartDate("");
                                mEvent.setEndDate("");
                                mMenuWindow.dismiss();
                                EventBus.getDefault().post(mEvent);//发布事件
                                break;
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();
        chooseDialog.show();
    }


    private void loadData() {
        tvTitleName.setText("交班记录");
        EventBus.getDefault().register(this);
        mMenuWindow = new MyMenuPopWindow(HandOverReportActivity.this, "今日", "昨日",
                "本周", "其它");
        mSmGid = (String) uSharedPreferencesUtiles.get(HandOverReportActivity.this, "StoreGid", "");
        mList = new ArrayList<>();
        mList.add("今日");
        mList.add("昨日");
        mList.add("本周");
        mList.add("其它");
        tvTitleFirstName.setText("");
        tvTitleFirstValue.setText("");
        tvTitleSecondName.setText("");
        tvTitleSecondValue.setText("");
        mStartTime = DateTimeUtil.getNowDate();
        mEndTime = DateTimeUtil.getNowDate();
        getReport(1, 20);
    }

    private void getReport(int index, int pageSize) {
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("ShifUser", "");
        params.put("StartTime", mStartTime);
        params.put("EndTime", mEndTime);
        params.put("SM_GID", mSmGid);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                handOverReportBean = CommonFun.JsonToObj(responseString,HandOverReportBean.class);
                if (mReportBean != null && mIsLoadMore) {
                    List<HandOverReportBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mReportBean.getData().getDataList());
                    list.addAll(handOverReportBean.getData().getDataList());
                    mReportBean.getData().setDataList(list);
                } else {
                    mReportBean = handOverReportBean;
                }
                mPageTotal = mReportBean.getData().getPageTotal();
                refresh.setRefreshing(false);
                refresh.setLoading(false);
                mIsLoadMore = false;
                if (mReportBean.getData().getDataList().size()>0){
                    tvNull.setVisibility(View.GONE);
                }else {
                    tvNull.setVisibility(View.VISIBLE);
                }
                setAdapter();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(HandOverReportActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(HandOverReportActivity.this, MyApplication.BASE_URL+"Shift/GetExchangeReport",params,callBack);
    }

    private void setAdapter() {

        if (mAdapter == null){
            mAdapter = new HandOverReportAdapter(HandOverReportActivity.this, mReportBean);
            listView.setAdapter(mAdapter);
        }else {
            mAdapter.setParm(mReportBean);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 666 && requestCode == 666) {
            mStaffName = data.getStringExtra("Name");
            mRebateType = data.getStringExtra("Type");
            getReport(1, 10);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(ScreenConditionEvent event) {
        mStartTime = event.getStartDate();
        mEndTime = event.getEndDate();
//        mSmGid = event.getGid();
        getReport(1, 10);
    }


    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.tv_menu_pop_first:
                tvTitleDate.setText("今日");
                mEvent.setStartDate(DateTimeUtil.getNowDate());
                mEvent.setEndDate(DateTimeUtil.getNowDate());
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case R.id.tv_menu_pop_second:
                tvTitleDate.setText("昨日");
                mEvent.setStartDate(DateTimeUtil.getLastDate());
                mEvent.setEndDate(DateTimeUtil.getLastDate());
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case R.id.tv_menu_pop_third:
                tvTitleDate.setText("本周");
                mEvent.setStartDate(DateTimeUtil.getNowWeekFirstDate());
                mEvent.setEndDate(DateTimeUtil.getNowWeekFinalDate());
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case R.id.tv_menu_pop_fourth:
                llHandoverDateSelector.setVisibility(View.VISIBLE);
                tvTitleDate.setText("其它");
                mEvent.setStartDate("");
                mEvent.setEndDate("");
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            default:
                mMenuWindow.dismiss();
                break;
        }
    }
}
