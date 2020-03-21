package com.zhiluo.android.yunpu.analysis.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.analysis.adapter.AnalysisMemberAdapter;
import com.zhiluo.android.yunpu.analysis.bean.MemberAnalysisBean;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.myview.CircleView;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.rebate.activity.RebateActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyListView;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * Date: 2017/9/19 14:29
 * Author: 罗咏哲
 * Description: 会员分析
 * Version:
 */
public class MemberAnalysisActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private String mTabs[];
    private LinearLayout mLlDateSelector;
    private TextView mTvStartDate, mTvEndDate;//开始、结束时间
    private String mStartTime, mEndTime;
    private int mFlag = 1;
    private MemberAnalysisBean.DataBean mBean;
    private Handler mHandler;
    private TextView mTvAddMember, mTvSankeSaleNum, mTvSankeSaleMoney, mTvMemberSaleNum, mTvMemberSaleMoney;
    private MyListView mListView;
    private AnalysisMemberAdapter mAdapter;
    private ScrollView mScrollView;
    private Dialog dateDialog;
    private CircleView circleView;
    private double skSales, memSales,skNum,memNum;//散客消费 会员消费 散客消费笔数 会员消费笔数

    private TextView tvNewAddVip, tvVipZhanBi, tvSanKeZHanBi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_analysis);
        initView();
        initVariable();
        loadData();
        setListener();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_analysis_member);
        mLlDateSelector = (LinearLayout) findViewById(R.id.ll_statistics_time_other);
        mTvStartDate = (TextView) findViewById(R.id.tv_start_date);
        mTvEndDate = (TextView) findViewById(R.id.tv_end_date);
        mTvAddMember = (TextView) findViewById(R.id.tv_member_analysis_add);
        mTvSankeSaleNum = (TextView) findViewById(R.id.tv_sanke_sale_num);
        mTvSankeSaleMoney = (TextView) findViewById(R.id.tv_sanke_sale_money);
        mTvMemberSaleNum = (TextView) findViewById(R.id.tv_member_sale_num);
        mTvMemberSaleMoney = (TextView) findViewById(R.id.tv_member_sale_money);
        mListView = (MyListView) findViewById(R.id.lv_member_analysis);
        mScrollView = (ScrollView) findViewById(R.id.sv_vip);

        tvNewAddVip = findViewById(R.id.tv_new_add_vip);
        tvVipZhanBi = findViewById(R.id.tv_xiaofei_vip);
        tvSanKeZHanBi = findViewById(R.id.tv_xiaofei_sanke);

        circleView = findViewById(R.id.circle_view);
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
                    circleView.setActivity(true);
                    circleView.setArrPer(calculateMoney());
                    circleView.postInvalidate();
                }
                return false;
            }
        });
    }

    private void updateView() {
        memSales = 0.00;
        memNum = 0.00;
        if (mBean != null && mBean.getMberList() != null) {
            for (int i = 0; i < mBean.getMberList().size(); i++) {
                memSales += Double.parseDouble(mBean.getMberList().get(i).getMoney() + "");
                memNum += Double.parseDouble(mBean.getMberList().get(i).getNumber() + "");
            }
        }
        tvNewAddVip.setText(mBean.getSA_NewMemberNumber() == null ? "" : mBean.getSA_NewMemberNumber());
        mTvSankeSaleNum.setText(mBean.getSA_SKSalesNumber() == null ? "" : mBean.getSA_SKSalesNumber());
        mTvSankeSaleMoney.setText(Decima2KeeplUtil.stringToDecimal(mBean.getSA_SKSalesMoney() + ""));
        mTvMemberSaleNum.setText(Decima2KeeplUtil.stringToDecimal(memNum+"")+ "");
        mTvMemberSaleMoney.setText(Decima2KeeplUtil.stringToDecimal(memSales+"")+ "");
        mAdapter = new AnalysisMemberAdapter(MemberAnalysisActivity.this, mBean.getMberList());
        mListView.setAdapter(mAdapter);
        mTvAddMember.setFocusable(true);
        mTvAddMember.setFocusableInTouchMode(true);
        mTvAddMember.requestFocus();
        mScrollView.smoothScrollBy(0, 0);
        float calculateMoney[] = calculateMoney();
        if (calculateMoney.length == 2){
            tvSanKeZHanBi.setText(calculateMoney[0] + "%");
            tvVipZhanBi.setText(calculateMoney[1]+ "%");
        }else {
            tvSanKeZHanBi.setText(calculateMoney[0] + "%");
            tvVipZhanBi.setText(calculateMoney[0] + "%");
        }

    }

    private float[] calculateMoney() {
        double a = mBean.getSA_SKSalesMoney();
        double b = memSales;
        double c = a + b;
        if (c == 0.00f) {
            // circleView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            return new float[]{0.00f};
        } else {
            circleView.setBackgroundColor(getResources().getColor(R.color.white));
            return new float[]{float2((float) ((a / c) * 100)), float2((float) ((b / c) * 100))};
        }
    }

    /**
     * @param f ,
     * @return 保留两位小数
     */
    public float float2(float f) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Float.parseFloat(df.format(f));
    }

    private void loadData() {
        mFlag = 1;
        getData(mStartTime, mEndTime);
    }

    private void setListener() {
        findViewById(R.id.tv_analysis_member_back).setOnClickListener(new View.OnClickListener() {
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
//                        mEndTime = mStartTime = "";
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
        //确定时间
        findViewById(R.id.btn_date_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = mTvStartDate.getText().toString();
                String end = mTvEndDate.getText().toString();
                if (start.isEmpty()) {
                    CustomToast.makeText(MemberAnalysisActivity.this, "请选择开始时间！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (end.isEmpty()) {
                    CustomToast.makeText(MemberAnalysisActivity.this, "请选择结束时间！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!start.isEmpty() && !end.isEmpty()) {

                    try {
                        if (DateTimeUtil.isOverTime(MemberAnalysisActivity.this,mStartTime,mEndTime)){
                            getData(mStartTime, mEndTime);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
//                    mLlDateSelector.setVisibility(View.GONE);
                }

            }
        });
        //选择开始时间监听
        mTvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDatePickDlg(view);
                if (mTvStartDate.getText().toString() ==null || mTvStartDate.getText().toString().equals("")){
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvStartDate,0);
                }else {
                    showDateDialog(DateUtil.getDateForString(mTvStartDate.getText().toString()), mTvStartDate,0);
                }
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvStartDate, 0);
            }
        });
        //选择结束时间监听
        mTvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                showDatePickDlg(view);
                if (mTvEndDate.getText().toString() ==null || mTvEndDate.getText().toString().equals("")){
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvEndDate,1);
                }else {
                    showDateDialog(DateUtil.getDateForString(mTvEndDate.getText().toString()), mTvEndDate,1);
                }
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvEndDate, 1);
            }
        });

    }

    /**
     * @param date      日期
     * @param mTextView ,
     * @param flags     标志 0 代表选择开始日期，1 代表选择结日期
     *                  筛选日期弹窗
     */
    private void showDateDialog(List<Integer> date, final TextView mTextView, final int flags) {
        com.example.liangmutian.mypicker.DatePickerDialog.Builder builder = new com.example.liangmutian.mypicker.DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new com.example.liangmutian.mypicker.DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                mTextView.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
                if (flags == 0) {
                    mStartTime = mTextView.getText().toString();
                    DateTimeUtil.isCurTime(MemberAnalysisActivity.this,mStartTime);
                } else if (flags == 1) {
                    mEndTime = mTextView.getText().toString();
                    DateTimeUtil.isCurTime(MemberAnalysisActivity.this,mEndTime);
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

    protected void showDatePickDlg(final View v) {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(MemberAnalysisActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int momth = monthOfYear + 1;
                        if (v.getId() == R.id.tv_start_date) {
                            MemberAnalysisActivity.this.mTvStartDate.setText(year + "-" + momth + "-" + dayOfMonth);
                            mStartTime = year + "-" + momth + "-" + dayOfMonth;
                        }
                        if (v.getId() == R.id.tv_end_date) {
                            MemberAnalysisActivity.this.mTvEndDate.setText(year + "-" + momth + "-" + dayOfMonth);
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
        PersistentCookieStore cookieStore = new PersistentCookieStore(MemberAnalysisActivity.this);
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("Flag", mFlag);
        if (startTime != null && !"".equals(startTime) && endTime != null && !"".equals(endTime)) {
            params.put("StartTime", startTime);
            params.put("EndTime", endTime);
        }
        MyTextHttpResponseHandler response = new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Le("------" + responseString);
                mBean = CommonFun.JsonToObj(responseString, MemberAnalysisBean.class).getData();
                if (mBean != null) {
                    mHandler.sendEmptyMessage(1);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MemberAnalysisActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        response.setDialog(MemberAnalysisActivity.this, "加载中...", true);
        client.post(HttpAPI.API().VIP_ANALYSIS, params, response);
    }
}
