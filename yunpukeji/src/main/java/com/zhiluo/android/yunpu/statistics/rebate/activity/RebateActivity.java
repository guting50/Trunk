package com.zhiluo.android.yunpu.statistics.rebate.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.account.event.ScreenConditionEvent;
import com.zhiluo.android.yunpu.statistics.order.activity.GoodsOrderScreenActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.PointExchangeScreenActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.TimesConsumeScreenActivity;
import com.zhiluo.android.yunpu.statistics.rebate.adapter.RebateFragmentPagerAdapter;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RebateActivity extends BaseActivity implements MyMenuPopWindow.OnItemClickListener {
    private static Dialog chooseDialog;
    private TextView mTvTitle;
    private TextView mTvDate;
    private TextView mTvFirstName;
    private TextView mTvFirstValue;
    private TextView mTvSecondName;
    private TextView mTvSecondValue;
    private ImageView mIvScreen;//筛选条件
    private TextView tvSX;
    private LinearLayout mLlDateSelector;
    private TextView mTvStartDate, mTvEndDate;//开始、结束时间
    private Button mBtnDateSelector;//时间选择器确认
    private RelativeLayout mRlDate;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyMenuPopWindow mMenuWindow;
    private ScreenConditionEvent mEvent = new ScreenConditionEvent();
    private String mStartTime, mEndTime;
    private RebateFragmentPagerAdapter mPagerAdapter;

    private List<String> mList;
    private Dialog dateDialog;
    private String start ;
    private String end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebate);
        initView();
        initVariable();
        loadData();
        setListener();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title_name);
        mTvTitle.setText("推荐返利");
        mTvDate = (TextView) findViewById(R.id.tv_title_date);
        mTvFirstName = (TextView) findViewById(R.id.tv_title_first_name);
        mRlDate = (RelativeLayout) findViewById(R.id.rl_title_date);
        mTvFirstValue = (TextView) findViewById(R.id.tv_title_first_value);
        mTvSecondName = (TextView) findViewById(R.id.tv_title_second_name);
        tvSX=findViewById(R.id.tv_sx);
        tvSX.setVisibility(View.GONE);
        mIvScreen = (ImageView) findViewById(R.id.iv_title_screen);
        mIvScreen.setVisibility(View.GONE);
        mTvSecondValue = (TextView) findViewById(R.id.tv_title_second_value);
        mLlDateSelector = (LinearLayout) findViewById(R.id.ll_rebate_date_selector);
        mLlDateSelector.setVisibility(View.GONE);
        mTvStartDate = (TextView) findViewById(R.id.tv_start_date);
        mTvEndDate = (TextView) findViewById(R.id.tv_end_date);
        mBtnDateSelector = (Button) findViewById(R.id.btn_date_confirm);

        mViewPager = (ViewPager) findViewById(R.id.vp_rebate);
        mTabLayout = (TabLayout) findViewById(R.id.tab_rebate);

    }

    private void initVariable() {
        mPagerAdapter = new RebateFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mMenuWindow = new MyMenuPopWindow(RebateActivity.this, "今日", "昨日",
                "本周", "其它");

        EventBus.getDefault().register(this);//订阅事件

    }

    private void loadData() {
        mList = new ArrayList<>();
        mList.add("今日");
        mList.add("昨日");
        mList.add("本周");
        mList.add("其它");
    }

    private void setListener() {
        findViewById(R.id.tv_title_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mMenuWindow.setOnItemClickListener(this);
        mRlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  mMenuWindow.showAsDropDown(mTvDate);

                showPop(RebateActivity.this, mList, mTvDate);
            }
        });

        //选择开始时间监听
        mTvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  showDatePickDlg(view);
                if (mTvStartDate.getText().toString() ==null || mTvStartDate.getText().toString().equals("")){
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvStartDate,0);
                }else {
                    showDateDialog(DateUtil.getDateForString(mTvStartDate.getText().toString()), mTvStartDate,0);
                }
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()),mTvStartDate);
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
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()),mTvEndDate);
            }
        });
        mBtnDateSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = mTvStartDate.getText().toString();
                end = mTvEndDate.getText().toString();
                if (start.isEmpty()) {
                    CustomToast.makeText(RebateActivity.this, "请选择开始时间！", Toast.LENGTH_SHORT).show();
                }
                if (end.isEmpty()) {
                    CustomToast.makeText(RebateActivity.this, "请选择结束时间！", Toast.LENGTH_SHORT).show();
                }
                if (!start.isEmpty() && !end.isEmpty()) {
                    if ( !DateTimeUtil.isCurTime(RebateActivity.this,start)){
                        return;
                    }else if (!DateTimeUtil.isCurTime(RebateActivity.this,end)){
                        return;
                    }
                    try {
                        if (DateTimeUtil.isOverTime(RebateActivity.this,start,end)){
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

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

                sendMsgToFragment();

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    private void sendMsgToFragment() {
        switch (mTvDate.getText().toString()) {
            case "今日":
                mEvent.setStartDate(DateTimeUtil.getNowDate());
                mEvent.setEndDate(DateTimeUtil.getNowDate());
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case "昨日":
                mEvent.setStartDate(DateTimeUtil.getLastDate());
                mEvent.setEndDate(DateTimeUtil.getLastDate());
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case "本周":
                mEvent.setStartDate(DateTimeUtil.getNowWeekFirstDate());
                mEvent.setEndDate(DateTimeUtil.getNowWeekFinalDate());
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case "其它":
                mEvent.setStartDate(mStartTime);
                mEvent.setEndDate(mEndTime);
                EventBus.getDefault().post(mEvent);//发布事件
                break;
        }
    }

    /**
     * @param context   上下文
     * @param mlist     显示的元素集合
     * @param mTextView 。
     *                  根据mlist中的元素来显示一个供用户选择的弹窗，
     */
    public  void showPop(Context context, List<String> mlist, final TextView mTextView) {
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
                        query(itemValue);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();
        chooseDialog.show();
    }
    private void showDateDialog(List<Integer> date,final TextView mTextView,final int tag) {
        com.example.liangmutian.mypicker.DatePickerDialog.Builder builder = new com.example.liangmutian.mypicker.DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new com.example.liangmutian.mypicker.DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                String str = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));
                mTextView.setText(str);
                if (tag ==0){
                    start = str;
                    DateTimeUtil.isCurTime(RebateActivity.this,start);
                }else if (tag ==1){
                    end = str;
                    DateTimeUtil.isCurTime(RebateActivity.this,end);
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
                mEvent.setStartDate(mStartTime);
                mEvent.setEndDate(mEndTime);
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            default:
                mMenuWindow.dismiss();
                break;
        }
    }

    private void query(String s) {
        switch (s) {
            case "今日":
                mLlDateSelector.setVisibility(View.GONE);
                mEvent.setStartDate(DateTimeUtil.getNowDate());
                mEvent.setEndDate(DateTimeUtil.getNowDate());
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case "昨日":
                mLlDateSelector.setVisibility(View.GONE);
                mEvent.setStartDate(DateTimeUtil.getLastDate());
                mEvent.setEndDate(DateTimeUtil.getLastDate());
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case "本周":
                mLlDateSelector.setVisibility(View.GONE);
                mEvent.setStartDate(DateTimeUtil.getNowWeekFirstDate());
                mEvent.setEndDate(DateTimeUtil.getNowWeekFinalDate());
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
            case "其它":
                mLlDateSelector.setVisibility(View.VISIBLE);
                mEvent.setStartDate(mStartTime);
                mEvent.setEndDate(mEndTime);
                mMenuWindow.dismiss();
                EventBus.getDefault().post(mEvent);//发布事件
                break;
        }
    }

    protected void showDatePickDlg(final View v) {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(RebateActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int momth = monthOfYear + 1;
                        if (v.getId() == R.id.tv_start_date) {
                            RebateActivity.this.mTvStartDate.setText(year + "-" + momth + "-" + dayOfMonth);
                            mStartTime = year + "-" + momth + "-" + dayOfMonth;
                        }
                        if (v.getId() == R.id.tv_end_date) {
                            RebateActivity.this.mTvEndDate.setText(year + "-" + momth + "-" + dayOfMonth);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateValue(GetValueEvent event) {
        mTvFirstName.setText(event.getName1());
        mTvSecondName.setText(event.getName2());
        mTvFirstValue.setText(event.getValue1());
        mTvSecondValue.setText(event.getValue2());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

}
