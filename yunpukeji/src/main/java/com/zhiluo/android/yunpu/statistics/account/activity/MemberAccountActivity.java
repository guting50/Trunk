package com.zhiluo.android.yunpu.statistics.account.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import com.zhiluo.android.yunpu.statistics.account.adapter.MyFragmentPagerAdapter;
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.account.event.ScreenConditionEvent;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.rebate.activity.RebateActivity;
import com.zhiluo.android.yunpu.statistics.staff.activity.StaffReportActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MemberAccountActivity extends BaseActivity implements MyMenuPopWindow.OnItemClickListener {
    private TextView mTvTitle, mTvDate, mTvFirstName, mTvFirstValue, mTvSecondName, mTvSecondValue;
    private ImageView mIvScreen;//筛选条件
    private TextView mTxScreen;
    private LinearLayout mLlDateSelector;
    private TextView mTvStartDate, mTvEndDate;//开始、结束时间
    private Button mBtnDateSelector;//时间选择器确认
    private RelativeLayout mRlDate;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MyFragmentPagerAdapter mFragmentPagerAdapter;
    private MyMenuPopWindow mMenuWindow;
    private ScreenConditionEvent mEvent = new ScreenConditionEvent();
    private String mStartTime, mEndTime;
    private Dialog dateDialog;
    private Dialog chooseDialog;
    private List<String> list;//日期
    private String start;
    private String end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_member_account);
        initView();
        initVariable();
        loadData();
        setListener();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title_name);
        mTvTitle.setText("会员账户");
        mTvDate = (TextView) findViewById(R.id.tv_title_date);
        mTvFirstName = (TextView) findViewById(R.id.tv_title_first_name);
        mRlDate = (RelativeLayout) findViewById(R.id.rl_title_date);

        mTvFirstValue = (TextView) findViewById(R.id.tv_title_first_value);
        mTvSecondName = (TextView) findViewById(R.id.tv_title_second_name);

        mIvScreen = (ImageView) findViewById(R.id.iv_title_screen);
        mTxScreen = findViewById(R.id.tv_sx);
        mTvSecondValue = (TextView) findViewById(R.id.tv_title_second_value);
        mLlDateSelector = (LinearLayout) findViewById(R.id.ll_account_date_selector);
        mLlDateSelector.setVisibility(View.GONE);
        mTvStartDate = (TextView) findViewById(R.id.tv_start_date);
        mTvEndDate = (TextView) findViewById(R.id.tv_end_date);
        mBtnDateSelector = (Button) findViewById(R.id.btn_date_confirm);

        mViewPager = (ViewPager) findViewById(R.id.vp_account);
        mTabLayout = (TabLayout) findViewById(R.id.tab_account);

    }

    private void initVariable() {
        mFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mMenuWindow = new MyMenuPopWindow(MemberAccountActivity.this, "今日", "昨日",
                "本周", "其它");
        EventBus.getDefault().register(this);//订阅事件
        list = new ArrayList<>();
        list.add("今日");
        list.add("昨日");
        list.add("本周");
        list.add("其它");

    }

    /**
     * @param date      ,
     * @param mTextView ,
     *                  选择日期
     */
    private void showDateDialog(List<Integer> date, final TextView mTextView,final int tag) {
        com.example.liangmutian.mypicker.DatePickerDialog.Builder builder = new com.example.liangmutian.mypicker.DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new com.example.liangmutian.mypicker.DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                String str = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));
                mTextView.setText(str);
                if (tag ==0){
                    start = str;
                    DateTimeUtil.isCurTime(MemberAccountActivity.this,start);
                }else if (tag ==1){
                    end = str;
                    DateTimeUtil.isCurTime(MemberAccountActivity.this,start);
                }
            }

            @Override
            public void onCancel() {

            }
        }).setSelectYear(date.get(0) - 1).setSelectMonth(date.get(1) - 1).setSelectDay(date.get(2) - 1);
        dateDialog = builder.create();
        dateDialog.show();
    }

    /**
     * chooseDialog
     */
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
                        switch (mTextView.getText().toString()) {
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

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * 加载数据
     */
    private void loadData() {

    }

    private void setListener() {
        findViewById(R.id.tv_title_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveScreenStateUtil mStateUtil = new  SaveScreenStateUtil(MemberAccountActivity.this);
                mStateUtil.clean("mr_data");//清除筛选里面共享参数
                mStateUtil.clean("CC_data");
                mStateUtil.clean("YQ_data");
                mStateUtil.clean("JF_data");
                mStateUtil.clean("JE_data");
                finish();
            }
        });
        mMenuWindow.setOnItemClickListener(this);
        mRlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  mMenuWindow.showAsDropDown(mTvDate);
                showChooseDialog(list, mTvDate);

            }
        });
        //条件筛选监听
        mIvScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberAccountActivity.this, ReportScreenActivity.class);
                startActivityForResult(intent, 666);
            }
        });
        //选择开始时间监听
        mTvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showDatePickDlg(view);
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
//                showDatePickDlg(view);
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
                    CustomToast.makeText(MemberAccountActivity.this, "请选择开始时间！", Toast.LENGTH_SHORT).show();
                }
                if (end.isEmpty()) {
                    CustomToast.makeText(MemberAccountActivity.this, "请选择结束时间！", Toast.LENGTH_SHORT).show();
                }
                if (!start.isEmpty() && !end.isEmpty()) {
                    if ( !DateTimeUtil.isCurTime(MemberAccountActivity.this,start)){
                        return;
                    }else if (!DateTimeUtil.isCurTime(MemberAccountActivity.this,end)){
                        return;
                    }

                    try {
                        if (DateTimeUtil.isOverTime(MemberAccountActivity.this,start,end)){
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
        jump2Screen(RechargeScreenActivity.class);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
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
                switch (i) {
                    case 0:
                        jump2Screen(RechargeScreenActivity.class);
                        break;
                    case 1:
                        jump2Screen(TimesRechargeScreenActivity.class);
                        break;
                    case 2:
                        jump2Screen(DelayScreenActivity.class);
                        break;
                    case 3:
                        jump2Screen(IntegralScreenActivity.class);
                        break;
                    case 4:
                        jump2Screen(MoneyDetailScreenActivity.class);
                        break;
                    case 5:
                        jump2Screen(MemberSignScreenActivity.class);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    /**
     * 跳转到查询条件筛选界面
     *
     * @param cls
     */
    private void jump2Screen(final Class cls) {
        //条件筛选监听
        mIvScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberAccountActivity.this, cls);
                startActivityForResult(intent, 666);
            }
        });

        mTxScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberAccountActivity.this, cls);

                startActivityForResult(intent, 666);
            }
        });
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

    protected void showDatePickDlg(final View v) {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(MemberAccountActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int momth = monthOfYear + 1;
                        if (v.getId() == R.id.tv_start_date) {
                            MemberAccountActivity.this.mTvStartDate.setText(year + "-" + momth + "-" + dayOfMonth);
                            mStartTime = year + "-" + momth + "-" + dayOfMonth;
                        }
                        if (v.getId() == R.id.tv_end_date) {
                            MemberAccountActivity.this.mTvEndDate.setText(year + "-" + momth + "-" + dayOfMonth);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 666) {
            String card = data.getStringExtra("Name");//姓名
            String order = data.getStringExtra("Order");//订单号
            String payWayCode = data.getStringExtra("Pay");//支付方式
            String device = data.getStringExtra("Device");//设备
            String dayu = data.getStringExtra("Big");//大于
            String xiaoyu = data.getStringExtra("Small");  //小于
            String service = data.getStringExtra("service_name");
            String type = data.getStringExtra("Type");
            String gid = data.getStringExtra("Store");
            if (gid != null) {
                mEvent.setGid(gid);
            } else {
                mEvent.setGid("");
            }
            if (card != null) {
                mEvent.setVipCondition(card);
            } else {
                mEvent.setVipCondition("");
            }
            if (order != null) {
                mEvent.setOrder(order);
            } else {
                mEvent.setOrder("");
            }
            if (payWayCode != null) {
                mEvent.setPayWayCode(payWayCode);
            } else {
                mEvent.setPayWayCode("");
            }
            mEvent.setDevice(device);
            if (dayu != null) {
                mEvent.setBig(dayu);
            } else {
                mEvent.setBig("");
            }
            if (xiaoyu != null) {
                mEvent.setSmall(xiaoyu);
            } else {
                mEvent.setSmall("");
            }
            if (service != null) {
                mEvent.setServiceName(service);
            } else {
                mEvent.setServiceName("");
            }
            if (type != null) {
                mEvent.setType(type);
            } else {
                mEvent.setType("");
            }
            if ("今日".equals(mTvDate.getText().toString())) {
                mEvent.setStartDate(DateTimeUtil.getNowDate());
                mEvent.setEndDate(DateTimeUtil.getNowDate());
            }
            if ("昨日".equals(mTvDate.getText().toString())) {
                mEvent.setStartDate(DateTimeUtil.getLastDate());
                mEvent.setEndDate(DateTimeUtil.getLastDate());
            }
            if ("本周".equals(mTvDate.getText().toString())) {
                mEvent.setStartDate(DateTimeUtil.getNowWeekFirstDate());
                mEvent.setEndDate(DateTimeUtil.getNowWeekFinalDate());
            }
            if ("其它".equals(mTvDate.getText().toString())) {
                mEvent.setStartDate(mStartTime);
                mEvent.setEndDate(mEndTime);
            }
            EventBus.getDefault().post(mEvent);//发布事件
        }
        if (resultCode == 7) {
            String card = data.getStringExtra("Name");//姓名
            String order = data.getStringExtra("Order");//订单号
            String type = data.getStringExtra("Type");
            String gid = data.getStringExtra("Store");
            String projectNum = data.getStringExtra("projectNum");
            String creator = data.getStringExtra("creator");
            if (gid != null) {
                mEvent.setGid(gid);
            } else {
                mEvent.setGid("");
            }
            if (card != null) {
                mEvent.setVipCondition(card);
            } else {
                mEvent.setVipCondition("");
            }
            if (order != null) {
                mEvent.setOrder(order);
            } else {
                mEvent.setOrder("");
            }
            if (type != null) {
                mEvent.setType(type);
            } else {
                mEvent.setType("");
            }

            if (projectNum != null) {
                mEvent.setProjectNum(projectNum);
            } else {
                mEvent.setProjectNum("");
            }
            if (creator != null) {
                mEvent.setCreator(creator);
            } else {
                mEvent.setCreator("");
            }
            if ("今日".equals(mTvDate.getText().toString())) {
                mEvent.setStartDate(DateTimeUtil.getNowDate());
                mEvent.setEndDate(DateTimeUtil.getNowDate());
            }
            if ("昨日".equals(mTvDate.getText().toString())) {
                mEvent.setStartDate(DateTimeUtil.getLastDate());
                mEvent.setEndDate(DateTimeUtil.getLastDate());
            }
            if ("本周".equals(mTvDate.getText().toString())) {
                mEvent.setStartDate(DateTimeUtil.getNowWeekFirstDate());
                mEvent.setEndDate(DateTimeUtil.getNowWeekFinalDate());
            }
            if ("其它".equals(mTvDate.getText().toString())) {
                mEvent.setStartDate(mStartTime);
                mEvent.setEndDate(mEndTime);
            }
            EventBus.getDefault().post(mEvent);//发布事件
        }
    }

}
