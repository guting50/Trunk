package com.zhiluo.android.yunpu.statistics.staff.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
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
import com.zhiluo.android.yunpu.statistics.account.activity.MemberAccountActivity;
import com.zhiluo.android.yunpu.statistics.account.event.ScreenConditionEvent;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.rebate.activity.RebateActivity;
import com.zhiluo.android.yunpu.statistics.staff.adapter.StaffRebateAdapter;
import com.zhiluo.android.yunpu.statistics.staff.bean.StaffRebateReportBean;
import com.zhiluo.android.yunpu.ui.activity.good.OutRecoderActivity;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.OutRecoderAdapter;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StaffReportActivity extends AppCompatActivity implements MyMenuPopWindow.OnItemClickListener {
    private TextView mTvTitle, mTvDate, mTvFirstName, mTvFirstValue, mTvSecondName, mTvSecondValue,tvnull;
    private BaseListView mListView;
    private WaveSwipeRefreshLayout mRefresh;
    private ImageView mIvScreen;//筛选条件
    private TextView mTvSreen;
    private LinearLayout mLlDateSelector;
    private TextView mTvStartDate, mTvEndDate;//开始、结束时间
    private Button mBtnDateSelector;//时间选择器确认
    private MyMenuPopWindow mMenuWindow;
    private String mStartTime, mEndTime;
    private ScreenConditionEvent mEvent = new ScreenConditionEvent();
    private String mStaffName, mRebateType;//员工姓名，提成类型

    private StaffRebateReportBean mReportBean;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数

    private boolean mIsLoadMore;//加载更多
    private StaffRebateAdapter mAdapter;
    private RelativeLayout mRlDate;
    private StaffRebateReportBean.DataBean.DataListBean mBean;
    private String mSmGid;
    private List<String> mList;
    private Dialog dateDialog;
    private Dialog chooseDialog;
    private String start;
    private String end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_report);
        initView();
        initVariable();
        loadData();
        setListener();
    }

    private void initView() {
        tvnull = findViewById(R.id.tv_null);
        mTvTitle = (TextView) findViewById(R.id.tv_title_name);
        mTvTitle.setText("员工提成");
        mTvDate = (TextView) findViewById(R.id.tv_title_date);
        mTvFirstName = (TextView) findViewById(R.id.tv_title_first_name);
        mRlDate = (RelativeLayout) findViewById(R.id.rl_title_date);

        mTvFirstValue = (TextView) findViewById(R.id.tv_title_first_value);
        mTvSecondName = (TextView) findViewById(R.id.tv_title_second_name);

        mIvScreen = (ImageView) findViewById(R.id.iv_title_screen);
        mTvSreen = findViewById(R.id.tv_sx);
        mTvSecondValue = (TextView) findViewById(R.id.tv_title_second_value);
        mLlDateSelector = (LinearLayout) findViewById(R.id.ll_staff_date_selector);
        mLlDateSelector.setVisibility(View.GONE);
        mTvStartDate = (TextView) findViewById(R.id.tv_start_date);
        mTvEndDate = (TextView) findViewById(R.id.tv_end_date);
        mBtnDateSelector = (Button) findViewById(R.id.btn_date_confirm);
        mRefresh = (WaveSwipeRefreshLayout) findViewById(R.id.refresh);
        mListView = (BaseListView) findViewById(R.id.list_view);
    }

    private void initVariable() {
        EventBus.getDefault().register(this);
        mMenuWindow = new MyMenuPopWindow(StaffReportActivity.this, "今日", "昨日",
                "本周", "其它");
    }

    private void loadData() {
        mList = new ArrayList<>();
        mList.add("今日");
        mList.add("昨日");
        mList.add("本周");
        mList.add("其它");
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
                                mLlDateSelector.setVisibility(View.GONE);
                                mTvDate.setText("今日");
                                mEvent.setStartDate(DateTimeUtil.getNowDate());
                                mEvent.setEndDate(DateTimeUtil.getNowDate());
                                mMenuWindow.dismiss();
                                EventBus.getDefault().post(mEvent);//发布事件
                                break;
                            case "昨日":
                                mLlDateSelector.setVisibility(View.GONE);
                                mTvDate.setText("昨日");
                                mEvent.setStartDate(DateTimeUtil.getLastDate());
                                mEvent.setEndDate(DateTimeUtil.getLastDate());
                                mMenuWindow.dismiss();
                                EventBus.getDefault().post(mEvent);//发布事件
                                break;
                            case "本周":
                                mLlDateSelector.setVisibility(View.GONE);
                                mTvDate.setText("本周");
                                mEvent.setStartDate(DateTimeUtil.getNowWeekFirstDate());
                                mEvent.setEndDate(DateTimeUtil.getNowWeekFinalDate());
                                mMenuWindow.dismiss();
                                EventBus.getDefault().post(mEvent);//发布事件
                                break;
                            case "其它":
                                mLlDateSelector.setVisibility(View.VISIBLE);
                                mTvDate.setText("其它");
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
                    DateTimeUtil.isCurTime(StaffReportActivity.this,start);
                } else if (tag == 1) {
                    end = str;
                    DateTimeUtil.isCurTime(StaffReportActivity.this,start);
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

    private void setListener() {
        findViewById(R.id.tv_title_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveScreenStateUtil mStateUtil = new  SaveScreenStateUtil(StaffReportActivity.this);
                mStateUtil.clean("TC_data");//清除筛选里面共享参数

                finish();
            }
        });
        mMenuWindow.setOnItemClickListener(this);
        mRlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    mMenuWindow.showAsDropDown(mTvDate);

                showPop(StaffReportActivity.this, mList, mTvDate);
            }
        });
        //条件筛选监听
        mIvScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffReportActivity.this, StaffScreenActivity.class);
                startActivityForResult(intent, 666);
            }
        });

        mTvSreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffReportActivity.this, StaffScreenActivity.class);
                startActivityForResult(intent, 666);
            }
        });
        //选择开始时间监听
        mTvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvStartDate);
                if (mTvStartDate.getText().toString() ==null || mTvStartDate.getText().toString().equals("")){
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvStartDate,0);
                }else {
                    showDateDialog(DateUtil.getDateForString(mTvStartDate.getText().toString()), mTvStartDate,0);
                }
            }
        });
        //选择结束时间监听
        mTvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTvEndDate.getText().toString() ==null || mTvEndDate.getText().toString().equals("")){
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvEndDate,1);
                }else {
                    showDateDialog(DateUtil.getDateForString(mTvEndDate.getText().toString()), mTvEndDate,1);
                }
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvEndDate);
            }
        });
        mBtnDateSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = mTvStartDate.getText().toString();
                end = mTvEndDate.getText().toString();
                if (start.isEmpty()) {
                    CustomToast.makeText(StaffReportActivity.this, "请选择开始时间！", Toast.LENGTH_SHORT).show();
                }
                if (end.isEmpty()) {
                    CustomToast.makeText(StaffReportActivity.this, "请选择结束时间！", Toast.LENGTH_SHORT).show();
                }
                if (!start.isEmpty() && !end.isEmpty()) {
                    if ( !DateTimeUtil.isCurTime(StaffReportActivity.this,start)){
                        return;
                    }else if (!DateTimeUtil.isCurTime(StaffReportActivity.this,end)){
                        return;
                    }
                    try {
                        if (DateTimeUtil.isOverTime(StaffReportActivity.this, start, end)){
                            String date = start + "\t" + end;
                            mTvDate.setText(date);
                            mLlDateSelector.setVisibility(View.GONE);

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
        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getStaffData(1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getStaffData(mRefreshIndex, 10);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(StaffReportActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBean = mReportBean.getData().getDataList().get(i);
                Intent intent = new Intent(StaffReportActivity.this, StaffRebateDetailActivity.class);
                intent.putExtra("staff", mBean);
                startActivity(intent);
            }
        });

    }

    protected void showDatePickDlg(final View v) {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(StaffReportActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int momth = monthOfYear + 1;
                        if (v.getId() == R.id.tv_start_date) {
                            StaffReportActivity.this.mTvStartDate.setText(year + "-" + momth + "-" + dayOfMonth);
                            mStartTime = year + "-" + momth + "-" + dayOfMonth;
                        }
                        if (v.getId() == R.id.tv_end_date) {
                            StaffReportActivity.this.mTvEndDate.setText(year + "-" + momth + "-" + dayOfMonth);
                            mEndTime = year + "-" + momth + "-" + dayOfMonth;
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        DatePicker dp = datePickerDialog.getDatePicker();
        dp.setMaxDate(System.currentTimeMillis());//设置可选的最大日期
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.show();
    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.tv_menu_pop_first:
                mTvDate.setText("今日");
                mEvent.setStartDate(DateTimeUtil.getNowDate());
                mEvent.setEndDate(DateTimeUtil.getNowDate());
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case R.id.tv_menu_pop_second:
                mTvDate.setText("昨日");
                mEvent.setStartDate(DateTimeUtil.getLastDate());
                mEvent.setEndDate(DateTimeUtil.getLastDate());
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case R.id.tv_menu_pop_third:
                mTvDate.setText("本周");
                mEvent.setStartDate(DateTimeUtil.getNowWeekFirstDate());
                mEvent.setEndDate(DateTimeUtil.getNowWeekFinalDate());
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case R.id.tv_menu_pop_fourth:
                mLlDateSelector.setVisibility(View.VISIBLE);
                mTvDate.setText("其它");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 666 && requestCode == 666) {
            mStaffName = data.getStringExtra("Name");
            mRebateType = data.getStringExtra("Type");
            getStaffData(1, 10);
        }
    }

    private void getStaffData(int index, int pageSize) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(StaffReportActivity.this);
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("SS_EmployeeName", mStaffName);
        params.put("SS_CommissionType", mRebateType);
        params.put("SS_DeptGID", "");
        params.put("StartDate", mStartTime);
        params.put("EndDate", mEndTime);
        params.put("SM_GID", mSmGid);
        client.post(HttpAPI.API().REPORT_STAFF_REBATE, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                StaffRebateReportBean reportBean = CommonFun.JsonToObj(responseString, StaffRebateReportBean.class);
                if (mReportBean != null && mIsLoadMore) {
                    List<StaffRebateReportBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mReportBean.getData().getDataList());
                    list.addAll(reportBean.getData().getDataList());
                    mReportBean.getData().setDataList(list);
                } else {
                    mReportBean = reportBean;
                }
                mPageTotal = mReportBean.getData().getPageTotal();
                mRefresh.setRefreshing(false);
                mRefresh.setLoading(false);
                mIsLoadMore = false;
                if (mReportBean.getData().getDataList().size()>0){
                    tvnull.setVisibility(View.GONE);
                }else {
                    tvnull.setVisibility(View.VISIBLE);
                }
                setAdapter();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(StaffReportActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setAdapter() {

        if (mAdapter == null){
            mAdapter = new StaffRebateAdapter(StaffReportActivity.this, mReportBean);
            mListView.setAdapter(mAdapter);
        }else {
            mAdapter.setParm(mReportBean);
            mAdapter.notifyDataSetChanged();
        }

        mTvFirstName.setText("总提成金额");
        mTvFirstValue.setText(mReportBean.getData().getStatisticsInfo().getMonetary() + "");
        mTvSecondName.setText("");
        mTvSecondValue.setText("");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(ScreenConditionEvent event) {
        mStartTime = event.getStartDate();
        mEndTime = event.getEndDate();
        mSmGid = event.getGid();
        getStaffData(1, 10);
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (mTvDate.getText().toString()) {
            case "今日":
                mStartTime = DateTimeUtil.getNowDate();
                mEndTime = DateTimeUtil.getNowDate();
                getStaffData(1, 10);
                break;
            case "昨日":
                mStartTime = DateTimeUtil.getLastDate();
                mEndTime = DateTimeUtil.getLastDate();
                getStaffData(1, 10);
                break;
            case "本周":
                mStartTime = DateTimeUtil.getNowWeekFirstDate();
                mEndTime = DateTimeUtil.getNowWeekFinalDate();
                getStaffData(1, 10);
                break;
            case "其它":
                mStartTime = "";
                mEndTime = "";
                getStaffData(1, 10);
                break;
            default:
                String[] str = mTvDate.getText().toString().split("\\t");
                mStartTime = str[0];
                mEndTime = str[1];
                getStaffData(1, 10);
                break;
        }
    }

}
