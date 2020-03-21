package com.zhiluo.android.yunpu.analysis.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
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
import com.zhiluo.android.yunpu.analysis.adapter.AnalysisGoodsAdapter;
import com.zhiluo.android.yunpu.analysis.bean.GoodsAnalysisBean;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.myview.KView;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.rebate.activity.RebateActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.ui.view.MyListView;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.yslutils.DataUtils;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.YSLUtils.getPhoneWidth;

/**
 * Date: 2017/9/19 14:29
 * Author: 罗咏哲
 * Description: 商品分析
 * Version:
 */
public class GoodAnalysisActivity extends BaseActivity {
    private TabLayout mTabLayout;
    private String mTabs[];
    private LinearLayout mLlDateSelector;
    private TextView mTvStartDate, mTvEndDate;//开始、结束时间
    private String mStartTime, mEndTime;
    private TextView mTvNum, mTvMoney;
    private MyListView mListView;
    private TextView tvMoney;
    private TextView tvNumber;
    private int mFlag = 1;
    private Handler mHandler;
    private GoodsAnalysisBean mBean;
    private AnalysisGoodsAdapter mAdapter;
    private ScrollView mScrollView;
    private Dialog dateDialog;
    //K线
    private List<String> dayList;
    private KView kView;
    private List<Point> pointList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_analysis);
        initView();
        initVariable();
        loadData();
        setListener();
        initPoint();
        initDay();
        kView.setDayList(dayList);
        drawKView();
    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_analysis_good);
        mLlDateSelector = (LinearLayout) findViewById(R.id.ll_statistics_time_other);
        mLlDateSelector.setVisibility(View.GONE);
        mTvStartDate = (TextView) findViewById(R.id.tv_start_date);
        mTvEndDate = (TextView) findViewById(R.id.tv_end_date);
        mTvNum = (TextView) findViewById(R.id.tv_goods_sale_num);
        mTvMoney = (TextView) findViewById(R.id.tv_goods_sale_money);
        mListView = (MyListView) findViewById(R.id.lv_goods_order);
        mScrollView = (ScrollView) findViewById(R.id.sv_good);
        kView = findViewById(R.id.k_view);
        tvMoney = findViewById(R.id.tv_sale_money);
        tvNumber = findViewById(R.id.tv_sale_number);
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

    private void initDay() {
        dayList = new ArrayList<>();
        for (int i = 6; i >= 0; i--) {
            dayList.add(DataUtils.getDateBefore(i).substring(5, 10));
        }
    }

    private void initPoint() {
        pointList = new ArrayList<>();
        int width = getPhoneWidth();
        pointList.add(new Point(width * 1 / 14, 200));
        pointList.add(new Point(width * 3 / 14, 150));
        pointList.add(new Point(width * 5 / 14, 300));
        pointList.add(new Point(width * 7 / 14, 198));
        pointList.add(new Point(width * 9 / 14, 200));
        pointList.add(new Point(width * 11 / 14, 250));
        pointList.add(new Point(width * 13 / 14, 99));
        kView.setPointList(pointList);
        kView.setPx(dp2px(this,130)+getStatusBarHeight());

    }

    public void drawKView() {
        try {
            kView.setLineSmoothness(0.25f);
        } catch (NumberFormatException e) {
        }
        kView.startAnimation(2000);
    }

    /**
     * dp转换成px
     */
    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public int getStatusBarHeight() {
        int statusBarHeight = 0;
        if (statusBarHeight == 0) {
            Class<?> c;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }
    /**
     * @param date      ,
     *                  选择日期
     * @param mTextView ，
     * @param flags     ,标志，0，开始，1结束
     */
    private void showDateDialog(List<Integer> date, final TextView mTextView, final int flags) {
        com.example.liangmutian.mypicker.DatePickerDialog.Builder builder = new com.example.liangmutian.mypicker.DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new com.example.liangmutian.mypicker.DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) throws ParseException {

                mTextView.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
                if (flags == 0) {
                    mStartTime = mTvStartDate.getText().toString();
                    DateTimeUtil.isCurTime(GoodAnalysisActivity.this,mStartTime);
                } else if (flags == 1) {
                    mEndTime = mTvEndDate.getText().toString();
                    DateTimeUtil.isCurTime(GoodAnalysisActivity.this,mEndTime);
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
        double money=0.0;
        int number=0;
        tvNumber.setText(mBean.getData().getSA_SalesNumber());
        mTvMoney.setText(mBean.getData().getSA_SalesMoney());
        mAdapter = new AnalysisGoodsAdapter(GoodAnalysisActivity.this, mBean.getData().getPSList());
        mListView.setAdapter(mAdapter);
        mScrollView.smoothScrollTo(0, 0);
        for (int i=0;i<mBean.getData().getPSList().size();i++){
            money+=mBean.getData().getPSList().get(i).getMoney();
            number+=Integer.parseInt(mBean.getData().getPSList().get(i).getNumber());
        }
        tvMoney.setText(Decima2KeeplUtil.stringToDecimal(money+""));
        tvNumber.setText(Decima2KeeplUtil.stringToDecimal(number+""));
    }

    private void loadData() {
        mFlag = 1;
        getData(mStartTime, mEndTime);
    }

    private void setListener() {
        findViewById(R.id.tv_analysis_good_back).setOnClickListener(new View.OnClickListener() {
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
        //确定时间
        findViewById(R.id.btn_date_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = mTvStartDate.getText().toString();
                String end = mTvEndDate.getText().toString();
                if (start.isEmpty()) {
                    CustomToast.makeText(GoodAnalysisActivity.this, "请选择开始时间！", Toast.LENGTH_SHORT).show();
                }
                if (end.isEmpty()) {
                    CustomToast.makeText(GoodAnalysisActivity.this, "请选择结束时间！", Toast.LENGTH_SHORT).show();
                }
                if (!start.isEmpty() && !end.isEmpty()) {

                    if ( !DateTimeUtil.isCurTime(GoodAnalysisActivity.this,start)){
                        return;
                    }else if (!DateTimeUtil.isCurTime(GoodAnalysisActivity.this,end)){
                        return;
                    }
//                    mLlDateSelector.setVisibility(View.GONE);
                    try {
                        if (DateTimeUtil.isOverTime(GoodAnalysisActivity.this,mStartTime,mEndTime)){
                            getData(mStartTime, mEndTime);
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
        //选择开始时间监听
        mTvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });
    }

    protected void showDatePickDlg(final View v) {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(GoodAnalysisActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int momth = monthOfYear + 1;
                        if (v.getId() == R.id.tv_start_date) {
                            GoodAnalysisActivity.this.mTvStartDate.setText(year + "-" + momth + "-" + dayOfMonth);
                            mStartTime = year + "-" + momth + "-" + dayOfMonth;
                        }
                        if (v.getId() == R.id.tv_end_date) {
                            GoodAnalysisActivity.this.mTvEndDate.setText(year + "-" + momth + "-" + dayOfMonth);
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
        PersistentCookieStore cookieStore = new PersistentCookieStore(GoodAnalysisActivity.this);
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
                mBean = CommonFun.JsonToObj(responseString, GoodsAnalysisBean.class);
                if (mBean != null) {
                    mHandler.sendEmptyMessage(1);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(GoodAnalysisActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        response.setDialog(GoodAnalysisActivity.this, "加载中...", true);
        client.post(HttpAPI.API().GOOD_ANALYSIS, params, response);
    }
}
