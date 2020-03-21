package com.zhiluo.android.yunpu.statistics.order.activity;

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
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.order.adapter.OrderFragmentPagerAdapter;
import com.zhiluo.android.yunpu.statistics.order.bean.QueryOrderBean;
import com.zhiluo.android.yunpu.statistics.order.event.GoodsScreenEvent;
import com.zhiluo.android.yunpu.statistics.order.event.PointExchangeEvent;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class OrderActivity extends BaseActivity {
    private TextView mTvTitle;
    private TextView mTvDate;
    private TextView mTvFirstName;
    private TextView mTvFirstValue;
    private TextView mTvSecondName;
    private TextView mTvSecondValue;
    private TextView mTxScreen;
    private ImageView mIvScreen;//筛选条件
    private LinearLayout mLlDateSelector;
    private TextView mTvStartDate, mTvEndDate;//开始、结束时间
    private Button mBtnDateSelector;//时间选择器确认
    private RelativeLayout mRlDate;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private OrderFragmentPagerAdapter mPagerAdapter;
    private String[] mTitles = new String[]{"商品消费", "快速消费", "计次消费", "积分兑换","限时消费"};
    private String mTitle = "SPXF";

    private GoodsScreenEvent mEvent = new GoodsScreenEvent();
    private String mStartTime, mEndTime;
    private PointExchangeEvent mExchangeEvent = new PointExchangeEvent();
    private Dialog dateDialog;
    private Dialog chooseDialog;
    private String VipCard;
    private String mMemberCard;
    private String start;
    private String end;

    private List<String> list = new ArrayList<>();
    private LoginUpbean mLoginBean;
    private String mSmGid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        loadData();
//        sendMsgToFragment();
        initVariable();
        setListener();



    }



    private void sendMsgToFragment() {
        switch (mTvDate.getText().toString()) {
            case "今日":
                mEvent.setStartDate(DateTimeUtil.getNowDate());
                mEvent.setEndDate(DateTimeUtil.getNowDate());
                EventBus.getDefault().post(mEvent);//发布事件
                mExchangeEvent.setStartTime(DateTimeUtil.getNowDate());
                mExchangeEvent.setEndTime(DateTimeUtil.getNowDate());
                EventBus.getDefault().post(mExchangeEvent);//发布事件
                getQueryOrderDate(DateTimeUtil.getNowDate(),DateTimeUtil.getNowDate());
                break;
            case "昨日":
                mEvent.setStartDate(DateTimeUtil.getLastDate());
                mEvent.setEndDate(DateTimeUtil.getLastDate());
                EventBus.getDefault().post(mEvent);//发布事件
                mExchangeEvent.setStartTime(DateTimeUtil.getLastDate());
                mExchangeEvent.setEndTime(DateTimeUtil.getLastDate());
                EventBus.getDefault().post(mExchangeEvent);//发布事件
                getQueryOrderDate(DateTimeUtil.getLastDate(),DateTimeUtil.getLastDate());
                break;
            case "本周":
                mEvent.setStartDate(DateTimeUtil.getNowWeekFirstDate());
                mEvent.setEndDate(DateTimeUtil.getNowWeekFinalDate());
                EventBus.getDefault().post(mEvent);//发布事件
                mExchangeEvent.setStartTime(DateTimeUtil.getNowWeekFirstDate());
                mExchangeEvent.setEndTime(DateTimeUtil.getNowWeekFirstDate());
                EventBus.getDefault().post(mExchangeEvent);//发布事件
                getQueryOrderDate(DateTimeUtil.getNowWeekFirstDate(),DateTimeUtil.getNowWeekFinalDate());
                break;
        }

        if (mTvDate.getText().toString().equals(mStartTime + "\t" + mEndTime)){
            mEvent.setStartDate(mStartTime);
            mEvent.setEndDate(mEndTime);
            EventBus.getDefault().post(mEvent);//发布事件
            mExchangeEvent.setStartTime(mStartTime);
            mExchangeEvent.setEndTime(mEndTime);
            EventBus.getDefault().post(mExchangeEvent);//发布事件
            getQueryOrderDate(mStartTime,mEndTime);
        }

//        if (VipGid != null) {
//            mEvent.setVipCondition(VipCard);
//            mExchangeEvent.setVip(VipCard);
//            mEvent.setStartDate(DateTimeUtil.getNowDate());
//            mEvent.setEndDate(DateTimeUtil.getNowDate());
//            mExchangeEvent.setStartTime(DateTimeUtil.getNowDate());
//            mExchangeEvent.setEndTime(DateTimeUtil.getNowDate());
//            EventBus.getDefault().post(mEvent);
//            EventBus.getDefault().post(mExchangeEvent);
//        }
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_title_name);
        mTvTitle.setText("订单记录");
        mTvDate = (TextView) findViewById(R.id.tv_title_date);
        mTvFirstName = (TextView) findViewById(R.id.tv_title_first_name);
        mRlDate = (RelativeLayout) findViewById(R.id.rl_title_date);
        mTvFirstValue = (TextView) findViewById(R.id.tv_title_first_value);
        mTvSecondName = (TextView) findViewById(R.id.tv_title_second_name);

        mIvScreen = (ImageView) findViewById(R.id.iv_title_screen);
        mTxScreen = findViewById(R.id.tv_sx);
        mTvSecondValue = (TextView) findViewById(R.id.tv_title_second_value);
        mLlDateSelector = (LinearLayout) findViewById(R.id.ll_order_date_selector);
        mLlDateSelector.setVisibility(View.GONE);
        mTvStartDate = (TextView) findViewById(R.id.tv_start_date);
        mTvEndDate = (TextView) findViewById(R.id.tv_end_date);
        mBtnDateSelector = (Button) findViewById(R.id.btn_date_confirm);

        mViewPager = (ViewPager) findViewById(R.id.vp_order);
        mTabLayout = (TabLayout) findViewById(R.id.tab_order);
    }

    private void initVariable() {
        mPagerAdapter = new OrderFragmentPagerAdapter(getSupportFragmentManager(),mTitles);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        String mSmGid = (String) uSharedPreferencesUtiles.get(this, "StoreGid", "");
        mEvent.setGid(mSmGid+"");
        list.add("今日");
        list.add("昨日");
        list.add("本周");
        list.add("其它");
    }

    private void loadData() {
        mMemberCard = getIntent().getStringExtra("vipcard");
        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        getQueryOrderDate(DateTimeUtil.getNowDate(),DateTimeUtil.getNowDate());
        if (mLoginBean != null) {
            mSmGid = mLoginBean.getData().getShopID();
            try {
                if (mLoginBean.getData().getShopList().get(0).getSM_FunctionList() != null && !mLoginBean.getData().getShopList().get(0).getSM_FunctionList().equals("")) {

                    JSONArray jsonArray = new JSONArray(mLoginBean.getData().getShopList().get(0).getSM_FunctionList());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.get("code").equals("1JY")) {
                            Integer s = (Integer) jsonObject.get("value");
                            if (s == 1)
                                mTitles = new String[]{"商品消费","一键加油", "快速消费", "计次消费", "积分兑换","限时消费"};
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getVipcard(){
        return mMemberCard;
    }

    private void showChooseDialog(List<String> mlist) {

        int j = 0;
        for (int i = 0;i<mlist.size();i++){
            if (mlist.get(i).equals(mTvDate.getText().toString())){
                j= i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTvDate.setText(itemValue);
                        switch (itemValue) {
                            case "今日":
                                mLlDateSelector.setVisibility(View.GONE);
                                mTvDate.setText("今日");
                                mEvent.setStartDate(DateTimeUtil.getNowDate());
                                mEvent.setEndDate(DateTimeUtil.getNowDate());
                                mExchangeEvent.setStartTime(DateTimeUtil.getNowDate());
                                mExchangeEvent.setEndTime(DateTimeUtil.getNowDate());
                                mEvent.setVipCondition(VipCard);
                                mExchangeEvent.setVip(VipCard);
                                chooseDialog.dismiss();
                                EventBus.getDefault().post(mExchangeEvent);
                                EventBus.getDefault().post(mEvent);//发布事件
                                getQueryOrderDate(DateTimeUtil.getNowDate(),DateTimeUtil.getNowDate());
                                break;
                            case "昨日":
                                mLlDateSelector.setVisibility(View.GONE);
                                mTvDate.setText("昨日");
                                mEvent.setStartDate(DateTimeUtil.getLastDate());
                                mEvent.setEndDate(DateTimeUtil.getLastDate());
                                mExchangeEvent.setStartTime(DateTimeUtil.getLastDate());
                                mExchangeEvent.setEndTime(DateTimeUtil.getLastDate());
                                mEvent.setVipCondition(VipCard);
                                mExchangeEvent.setVip(VipCard);
                                chooseDialog.dismiss();
                                EventBus.getDefault().post(mExchangeEvent);
                                EventBus.getDefault().post(mEvent);//发布事件
                                getQueryOrderDate(DateTimeUtil.getLastDate(),DateTimeUtil.getLastDate());
                                break;
                            case "本周":
                                mLlDateSelector.setVisibility(View.GONE);
                                mTvDate.setText("本周");
                                mEvent.setStartDate(DateTimeUtil.getNowWeekFirstDate());
                                mEvent.setEndDate(DateTimeUtil.getNowWeekFinalDate());
                                mExchangeEvent.setStartTime(DateTimeUtil.getNowWeekFirstDate());
                                mExchangeEvent.setEndTime(DateTimeUtil.getNowWeekFinalDate());
                                mEvent.setVipCondition(VipCard);
                                mExchangeEvent.setVip(VipCard);
                                chooseDialog.dismiss();
                                EventBus.getDefault().post(mExchangeEvent);
                                EventBus.getDefault().post(mEvent);//发布事件
                                getQueryOrderDate(DateTimeUtil.getNowWeekFirstDate(),DateTimeUtil.getNowWeekFinalDate());
                                break;
                            case "其它":
                                mLlDateSelector.setVisibility(View.VISIBLE);
                                mTvDate.setText("其它");
//                                mEvent.setStartDate(mStartTime);
//                                mEvent.setEndDate(mEndTime);
//                                mExchangeEvent.setStartTime(mStartTime);
//                                mExchangeEvent.setEndTime(mEndTime);
//                                mEvent.setVipCondition(VipCard);
//                                mExchangeEvent.setVip(VipCard);
//                                chooseDialog.dismiss();
//                                EventBus.getDefault().post(mExchangeEvent);
//                                EventBus.getDefault().post(mEvent);//发布事件
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

    private void setListener() {
        findViewById(R.id.tv_title_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SaveScreenStateUtil mStateUtil = new  SaveScreenStateUtil(OrderActivity.this);
                mStateUtil.clean("GO_data");//清除筛选里面共享参数
                mStateUtil.clean("JC_data");
                mStateUtil.clean("JFDH_data");

                finish();
            }
        });
        EventBus.getDefault().register(this);
        mRlDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChooseDialog(list);
//                sendMsgToFragment();
            }
        });
        jump2Screen(GoodsOrderScreenActivity.class);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (mTitles.length ==6){
                    switch (i) {
                        case 0:
                            jump2Screen(GoodsOrderScreenActivity.class);
                            mTitle = "SPXF";
                            break;
                        case 1:
                            jump2Screen(YJJYOrderScreenActivity.class);
                            mTitle = "YJJY";
                            break;
                        case 2:
                            jump2Screen(GoodsOrderScreenActivity.class);
                            mTitle = "KSXF";
                            break;
                        case 3:
                            jump2Screen(TimesConsumeScreenActivity.class);
                            mTitle = "JCXF";
                            break;
                        case 4:
                            jump2Screen(PointExchangeScreenActivity.class);
                            mTitle = "JFDH";
                            break;
                        case 5:
                            jump2Screen(LimitedConsumeScreenActivity.class);
                            mTitle = "XSXF";
                            break;
                    }
                }else {
                    switch (i) {
                        case 0:
                            jump2Screen(GoodsOrderScreenActivity.class);
                            mTitle = "SPXF";
                            break;
                        case 1:
                            jump2Screen(GoodsOrderScreenActivity.class);
                            mTitle = "KSXF";
                            break;
                        case 2:
                            jump2Screen(TimesConsumeScreenActivity.class);
                            mTitle = "JCXF";
                            break;
                        case 3:
                            jump2Screen(PointExchangeScreenActivity.class);
                            mTitle = "JFDH";
                            break;
                        case 4:
                            jump2Screen(LimitedConsumeScreenActivity.class);
                            mTitle = "XSXF";
                            break;
                    }
                }
                sendMsgToFragment();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        //选择开始时间监听
        mTvStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mTvStartDate.getText().toString() == null || mTvStartDate.getText().toString().equals("")) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvStartDate, 0);
                } else {
                    showDateDialog(DateUtil.getDateForString(mTvStartDate.getText().toString()), mTvStartDate, 0);
                }
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvStartDate);
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
                    CustomToast.makeText(OrderActivity.this, "请选择开始时间！", Toast.LENGTH_SHORT).show();
                }
                if (end.isEmpty()) {
                    CustomToast.makeText(OrderActivity.this, "请选择结束时间！", Toast.LENGTH_SHORT).show();
                }
                if (!start.isEmpty() && !end.isEmpty()) {
                    if ( !DateTimeUtil.isCurTime(OrderActivity.this,start)){
                        return;
                    }else if (!DateTimeUtil.isCurTime(OrderActivity.this,end)){
                        return;
                    }
                        try {
                            if (DateTimeUtil.isOverTime(OrderActivity.this, start, end)){
                                String date = start + "\t" + end;
                                mTvDate.setText(date);
                                sendMsgToFragment();
                                mLlDateSelector.setVisibility(View.GONE);
                            }

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                }

            }
        });


    }

    /**
     * @param date ,
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
                    mStartTime = str;
                    DateTimeUtil.isCurTime(OrderActivity.this,start);
                }else if (tag ==1){
                    end = str;
                    mEndTime = str;
                    DateTimeUtil.isCurTime(OrderActivity.this,end);
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
                Intent intent = new Intent(OrderActivity.this, cls);
                intent.putExtra("vipcard",VipCard);
                startActivityForResult(intent, 666);
            }
        });

        mTxScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, cls);
                intent.putExtra("vipcard",VipCard);
                startActivityForResult(intent, 666);
            }
        });
    }

    protected void showDatePickDlg(final View v) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(OrderActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int momth = monthOfYear + 1;
                        if (v.getId() == R.id.tv_start_date) {
                            OrderActivity.this.mTvStartDate.setText(year + "-" + momth + "-" + dayOfMonth);
                            mStartTime = year + "-" + momth + "-" + dayOfMonth;
                        }
                        if (v.getId() == R.id.tv_end_date) {
                            OrderActivity.this.mTvEndDate.setText(year + "-" + momth + "-" + dayOfMonth);
                            mEndTime = year + "-" + momth + "-" + dayOfMonth;
                        }
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        DatePicker dp = datePickerDialog.getDatePicker();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dp.setMaxDate(System.currentTimeMillis());//设置可选的最大日期
        dp.setMinDate(sdf.parse("1900-01-01").getTime());
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.show();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateValue(GetValueEvent event) {
        if (mTitle.equals("JCXF")){
            mTvFirstName.setText("");
            mTvSecondName.setText("");
        }else {
            mTvFirstName.setText(event.getName1());
            mTvSecondName.setText(event.getName2());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    private void getQueryOrderDate(String startTime,String endTime){

        if (!mTitle.equals("XSXF") && !mTitle.equals("JCXF") ) {

            RequestParams params = new RequestParams();
            params.put("PageIndex", 1);
            params.put("PageSize", 20);
            params.put("CO_StartUpdateTime", startTime);
            params.put("CO_EndUpdateTime", endTime);
            params.put("CO_TypeCode", mTitle);
            params.put("SM_GID", mSmGid);
            if (mMemberCard !=null && !mMemberCard.equals("")){
                params.put("VIP_Card",mMemberCard);
            }
            CallBack callBack = new CallBack() {
                @Override
                public void onSuccess(String responseString, Gson gson) {
                    QueryOrderBean queryOrderBean = CommonFun.JsonToObj(responseString, QueryOrderBean.class);
                    if (queryOrderBean != null) {
                        mTvFirstValue.setText(Decima2KeeplUtil.stringToDecimal(queryOrderBean.getData().getMonetary() + ""));
                        if (mTitle.equals("JCXF")) {
                            mTvSecondValue.setText(Decima2KeeplUtil.stringToDecimal(queryOrderBean.getData().getAllNumber() + ""));
                        } else {
                            mTvSecondValue.setText(Decima2KeeplUtil.stringToDecimal(queryOrderBean.getData().getDiscountMoney() + ""));
                        }
                    }
                }

                @Override
                public void onFailure(String msg) {
                    CustomToast.makeText(OrderActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            };
            callBack.setLoadingAnimation(this, "加载中...", false);
            HttpHelper.post(this, HttpAPI.API().QUERY_ORDER, params, callBack);
        }else {
            mTvFirstValue.setText("");
            mTvSecondValue.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 666) {

            String card = data.getStringExtra("Name");//姓名
            String oilname = data.getStringExtra("oilname");//油品名
            String state = data.getStringExtra("state");//订单状态
            String order = data.getStringExtra("Order");//订单号
            String opreat = data.getStringExtra("Opreat");//操作员
            String payWayCode = data.getStringExtra("Pay");//支付方式
            String device = data.getStringExtra("Device");//设备
            String dayu = data.getStringExtra("Big");//大于
            String xiaoyu = data.getStringExtra("Small");  //小于
            String gid = data.getStringExtra("Store");
            if (gid != null) {
                mEvent.setGid(gid);
            } else {
                mEvent.setGid("");
            }

            if (oilname != null) {
                mEvent.setOilName(oilname);
            } else {
                mEvent.setOilName("");
            }

            if (state != null) {
                mEvent.setState(state);
            } else {
                mEvent.setState("");
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
            if (opreat != null) {
                mEvent.setOpreat(opreat);
            } else {
                mEvent.setOpreat("");
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
            mEvent.setOpType("0");
            EventBus.getDefault().post(mEvent);//发布事件
        }
        if (resultCode == 777) {
            String vip = data.getStringExtra("vip");//会员信息
            String order = data.getStringExtra("order");//订单号
            String phone = data.getStringExtra("phone");//会员手机号
            String operation = data.getStringExtra("operation");//操作人员
            String device = data.getStringExtra("device");//设备
            String gid = data.getStringExtra("Store");
            if (gid != null) {
                mEvent.setGid(gid);
            } else {
                mEvent.setGid("");
            }
            if (vip != null) {
                mEvent.setVipCondition(vip);
            } else {
                mEvent.setVipCondition("");
            }
            if (order != null) {
                mEvent.setOrder(order);
            } else {
                mEvent.setOrder("");
            }
            mEvent.setDevice(device);
            if (phone != null) {
                mEvent.setPhone(phone);
            } else {
                mEvent.setPhone("");
            }
            if (operation != null) {
                mEvent.setOperation(operation);
            } else {
                mEvent.setOperation("");
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

            mEvent.setOpType("2");
            EventBus.getDefault().post(mEvent);//发布事件
        }
        if (resultCode == 888) {
            String order = data.getStringExtra("order");
            String device = data.getStringExtra("device");
            String vip = data.getStringExtra("vip");
            String operation = data.getStringExtra("operation");//操作人员
            String gid = data.getStringExtra("Store");
            if (gid != null) {
                mExchangeEvent.setGid(gid);
            } else {
                mExchangeEvent.setGid("");
            }
            if (vip != null) {
                mExchangeEvent.setVip(vip);
            } else {
                mExchangeEvent.setVip("");
            }
            if (order != null) {
                mExchangeEvent.setOrder(order);
            } else {
                mExchangeEvent.setOrder("");
            }
            if (operation != null) {
                mExchangeEvent.setOperation(operation);
            } else {
                mEvent.setOperation("");
            }
            if ("今日".equals(mTvDate.getText().toString())) {
                mExchangeEvent.setStartTime(DateTimeUtil.getNowDate());
                mExchangeEvent.setEndTime(DateTimeUtil.getNowDate());
            }
            if ("昨日".equals(mTvDate.getText().toString())) {
                mExchangeEvent.setStartTime(DateTimeUtil.getLastDate());
                mExchangeEvent.setEndTime(DateTimeUtil.getLastDate());
            }
            if ("本周".equals(mTvDate.getText().toString())) {
                mExchangeEvent.setStartTime(DateTimeUtil.getNowWeekFirstDate());
                mExchangeEvent.setEndTime(DateTimeUtil.getNowWeekFinalDate());
            }
            if ("其它".equals(mTvDate.getText().toString())) {
                mExchangeEvent.setStartTime(mStartTime);
                mExchangeEvent.setEndTime(mEndTime);
            }
            mEvent.setOpType("3");
            mExchangeEvent.setDevice(device);
            EventBus.getDefault().post(mExchangeEvent);
        }

    }
}
