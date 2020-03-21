package com.zhiluo.android.yunpu.analysis.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.analysis.bean.StatisticsBean;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.account.activity.MemberAccountActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.rebate.activity.RebateActivity;
import com.zhiluo.android.yunpu.statistics.staff.activity.StaffReportActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;

import java.util.Calendar;

public class StatisticsActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private String mTabs[];
    private LinearLayout mLlDateSelector;
    private TextView mTvStartDate, mTvEndDate;//开始、结束时间
    private String mStartTime, mEndTime;
    private int mFlag = 1;
    private StatisticsBean mStatisticsBean;
    private Handler mHandler;
    private TextView mTvTotal, mTvCash, mTvCard, mTvBalance, mTvGoodConsume, mTvFastConsume, mTvGoodReturn,
            mTvVipCreate, mTvVipRecharge, mTvVipTimes, mTvKouKuan, mTvDelay, mTvZFB, mTvWX;
    private SaveScreenStateUtil mStateUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        mStateUtil = new SaveScreenStateUtil(this);
        initView();
        initVariable();
        loadData();
        setListener();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_statistics);
        mLlDateSelector = (LinearLayout) findViewById(R.id.ll_statistics_time_other);
        mLlDateSelector.setVisibility(View.GONE);
        mTvStartDate = (TextView) findViewById(R.id.tv_start_date);
        mTvEndDate = (TextView) findViewById(R.id.tv_end_date);
        mTvTotal = (TextView) findViewById(R.id.tv_statistics_total);
        mTvCash = (TextView) findViewById(R.id.tv_statistics_cash);
        mTvCard = (TextView) findViewById(R.id.tv_statistics_card);
        mTvBalance = (TextView) findViewById(R.id.tv_statistics_balance);
        mTvGoodConsume = (TextView) findViewById(R.id.tv_statistics_good);
        mTvFastConsume = (TextView) findViewById(R.id.tv_statistics_fast);
        mTvGoodReturn = (TextView) findViewById(R.id.tv_statistics_cancel);
        mTvVipCreate = (TextView) findViewById(R.id.tv_statistics_vip_create);
        mTvVipRecharge = (TextView) findViewById(R.id.tv_statistics_vip_recharge);
        mTvVipTimes = (TextView) findViewById(R.id.tv_statistics_vip_times);
        mTvKouKuan = (TextView) findViewById(R.id.tv_statistics_vip_koukuan);
        mTvDelay = (TextView) findViewById(R.id.tv_statistics_vip_delay);
        mTvZFB = (TextView) findViewById(R.id.tv_statistics_zfb);
        mTvWX = (TextView) findViewById(R.id.tv_statistics_wx);
    }

    private void initVariable() {
        mTabs = new String[]{"今日", "昨日", "本周", "其它"};
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabs[0]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabs[1]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabs[2]));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTabs[3]));
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if (message.what == 1) {
                    updateView();
                }
                return false;
            }
        });
    }

    private void loadData() {
        mFlag = 1;
        getData(mStartTime, mEndTime);
    }

    /**
     * 更新视图
     */
    private void updateView() {
        mTvTotal.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_AllTotal() + ""));
        mTvCash.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_CashTotal() + ""));
        mTvCard.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_UnionTotal() + ""));
        mTvBalance.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_BalanceTotal() + ""));
        mTvGoodConsume.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_ConsumptionTotal() + ""));
        mTvFastConsume.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_FastConsumptionTotal() + ""));
        mTvGoodReturn.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_ReturnGoodsTotal() + ""));
        mTvVipCreate.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_OpenCardTotal() + ""));
        mTvVipRecharge.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_RechargeTotal() + ""));
        mTvVipTimes.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_ChargeTotal() + ""));
        mTvKouKuan.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_BalanceTotal() + ""));
        mTvDelay.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_DelayTotal() + ""));
        mTvWX.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_WeChatpayTotal() + ""));
        mTvZFB.setText(Decima2KeeplUtil.stringToDecimal(mStatisticsBean.getData().getSA_AlipaypayTotal() + ""));
    }

    private void setListener() {
        findViewById(R.id.tv_statistics_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getText().toString()) {
                    case "其它":
                        mFlag = 6;
                        mLlDateSelector.setVisibility(View.VISIBLE);
                        getData(mStartTime, mEndTime);
                        break;
                    case "今日":
                        mFlag = 1;
                        mLlDateSelector.setVisibility(View.GONE);
                        getData(mStartTime, mEndTime);
                        break;
                    case "昨日":
                        mFlag = 2;
                        mLlDateSelector.setVisibility(View.GONE);
                        getData(mStartTime, mEndTime);
                        break;
                    case "本周":
                        mFlag = 3;
                        mLlDateSelector.setVisibility(View.GONE);
                        getData(mStartTime, mEndTime);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //商品分析
        findViewById(R.id.tv_statistics_good_analysis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, GoodAnalysisActivity.class);
                startActivity(intent);
            }
        });
        //会员分析
        findViewById(R.id.tv_statistics_member_analysis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StatisticsActivity.this, MemberAnalysisActivity.class);
                startActivity(intent);
            }
        });
        //确定时间
        findViewById(R.id.btn_date_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = mTvStartDate.getText().toString();
                String end = mTvEndDate.getText().toString();
                if (start.isEmpty() && !end.isEmpty()) {
                    CustomToast.makeText(StatisticsActivity.this, "请选择开始时间！", Toast.LENGTH_SHORT).show();
                }
                if (end.isEmpty() && !start.isEmpty()) {
                    CustomToast.makeText(StatisticsActivity.this, "请选择结束时间！", Toast.LENGTH_SHORT).show();
                }
//                mLlDateSelector.setVisibility(View.GONE);
                if (!start.isEmpty() && !end.isEmpty()) {
                    getData(mStartTime, mEndTime);
                }
            }
        });
        //选择开始时间监听
        mTvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickDlg(view);
            }
        });
        //选择结束时间监听
        mTvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickDlg(view);
            }
        });

        findViewById(R.id.tv_statistics_member_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticsActivity.this, MemberAccountActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_statistics_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticsActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_statistics_staff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticsActivity.this, StaffReportActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.tv_statistics_recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StatisticsActivity.this, RebateActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void showDatePickDlg(final View v) {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(StatisticsActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int momth = monthOfYear + 1;
                        if (v.getId() == R.id.tv_start_date) {
                            StatisticsActivity.this.mTvStartDate.setText(year + "-" + momth + "-" + dayOfMonth);
                            mStartTime = year + "-" + momth + "-" + dayOfMonth;
                        }
                        if (v.getId() == R.id.tv_end_date) {
                            StatisticsActivity.this.mTvEndDate.setText(year + "-" + momth + "-" + dayOfMonth);
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

    private void getData(String startTime, String endTime) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(StatisticsActivity.this);
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("Flag", mFlag);
        if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)) {
            params.put("StartTime", startTime);
            params.put("EndTime", endTime);
        }
        client.post(HttpAPI.API().SHIFT_AMOUNT, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mStatisticsBean = CommonFun.JsonToObj(responseString, StatisticsBean.class);
                if (mStatisticsBean != null) {
                    mHandler.sendEmptyMessage(1);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(StatisticsActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStateUtil.clean("TC_data");
        mStateUtil.clean("JC_data");
        mStateUtil.clean("GO_data");
        mStateUtil.clean("JFDH_data");
        mStateUtil.clean("JF_data");
        mStateUtil.clean("JE_data");
        mStateUtil.clean("mr_data");
        mStateUtil.clean("CC_data");
        mStateUtil.clean("YQ_data");
        HttpHelper.getmClient().cancelRequests(this, true);
    }
}
