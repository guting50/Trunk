package com.zhiluo.android.yunpu.historyconsumption;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.adapters.VipRechangeMoneyAdapter;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.member.manager.bean.YSLMemberInfoBean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.account.activity.MemberAccountActivity;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeMoneyReportBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;


/**
 * Created by ${YSL} on 2018-03-30.
 */

public class VipRechangeMoney extends AppCompatActivity implements View.OnClickListener, MyMenuPopWindow.OnItemClickListener {
    private String mStartTime = "";//开始时间
    private String mEndTime = "";//截止时间
    private String mOrder = "";
    private String mVipCondition = "";
    private String mPayWayCode = "";
    private String mBig = "";
    private String mSmall = "";
    private String mDevice = "";
    private String mSmGid = "";
    private RechargeMoneyReportBean mReportBean = new RechargeMoneyReportBean();
    private boolean mIsLoadMore;//加载更多
    private int mPageTotal;//数据总页数
    private TextView mRechangeTotal;//充值合计
    private TextView mRechangeTotalNum;//充值合计数
    private TextView mGiveTotal;//赠送合计
    private TextView mGiveTotalNum;//赠送合计数
    private ExpandableListView mListView;
    private VipRechangeMoneyAdapter mAdapter;
    private int lastClick = -1;//上一次点击的group的position，定义在全局
    private RechargeMoneyReportBean.DataBean mDatas;
    private TextView mBack;
    private Bundle mBundle;
    private YSLMemberInfoBean.DataBean.DataListBean mVipInfo;
    private TextView mEndTimes, mStartTimes, mTos, mTitledates, mStartDates, mEndDates;
    private Button mDataConfirm;
    private Dialog dateDialog, timeDialog, chooseDialog;
    private TextView mTextView;
    private List<String> mDateList;
    private String mStringTime1, mStringTime2;
    private LinearLayout mL;
    private ImageView mDropDown;
    private MyMenuPopWindow mMenuWindow;
    private RelativeLayout mRTi;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_vip_rechange_recoder);
        initViews();
        initDatas();
        listener();
        getRechargeMoneyReport(1, 20);
    }

    private void initDatas() {
        mBundle = getIntent().getBundleExtra("bundle");
        mVipInfo = (YSLMemberInfoBean.DataBean.DataListBean) mBundle.getSerializable("YSL_VIP_INFO");
        mDateList = new ArrayList<>();
        mMenuWindow = new MyMenuPopWindow(VipRechangeMoney.this, "今日", "昨日",
                "本周", "其它");
        mMenuWindow.setOnItemClickListener(this);
        mTos.setVisibility(View.GONE);
        mStartTime = DateTimeUtil.getNowDate();
        mEndTime = DateTimeUtil.getNowDate();
    }

    /**
     * 初始化变量
     */
    private void initAdapter() {
        mAdapter = new VipRechangeMoneyAdapter(mDatas, this);
        mListView.setGroupIndicator(null);
        mListView.setAdapter(mAdapter);
    }

    /**
     * 初始化视图变量
     */
    private void initViews() {
        mL = findViewById(R.id.l1);
        mRechangeTotalNum = findViewById(R.id.tv_rechangenum);
        mGiveTotalNum = findViewById(R.id.tv_getnum);
        mListView = findViewById(R.id.listview);
        mBack = findViewById(R.id.tv_backvipdetail_activity);
        mEndTimes = findViewById(R.id.tv_endTime);//显示开始日期
        mStartTimes = findViewById(R.id.tv_startTime);//显示结束日期
        mTos = findViewById(R.id.tv_to_);//至
        mTitledates = findViewById(R.id.tv_title_date);//时间段选择
        mRTi = findViewById(R.id.rl_title_date);//时间段选择

        mStartDates = findViewById(R.id.tv_start_date);//选择后的开始时间段
        mEndDates = findViewById(R.id.tv_end_date);//选择后的截止时间段
        mDataConfirm = findViewById(R.id.btn_date_confirm);//确定选择时间段

        mBack.setOnClickListener(this);
        mL.setOnClickListener(this);
        mStartDates.setOnClickListener(this);
        mEndDates.setOnClickListener(this);
        mDataConfirm.setOnClickListener(this);
        mRTi.setOnClickListener(this);


    }

    private void showDateDialog(List<Integer> date, final TextView textViews) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                textViews.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));

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
     * 事件
     */
    private void listener() {
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if (lastClick == -1) {
                    mListView.expandGroup(i);
                }
                if (lastClick != -1 && lastClick != i) {
                    mListView.collapseGroup(lastClick);
                    mListView.expandGroup(i);
                } else if (lastClick == i) {
                    if (mListView.isGroupExpanded(i)) {
                        mListView.collapseGroup(i);
                    } else if (!mListView.isGroupExpanded(i)) {
                        mListView.expandGroup(i);
                    }
                }
                lastClick = i;
                return true;
            }
        });
        mListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int parentPos, int childPos, long l) {

                return true;
            }
        });
    }

    /**
     * 查询会员充值记录，里面的一些筛选条件暂未添加
     */
    private void getRechargeMoneyReport(int index, int pageSize) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("OrderCode", mOrder);
        params.put("VCH_CardName", mVipInfo.getVCH_Card());
        params.put("MR_Code", mPayWayCode);
        params.put("MR_AmountMin", mBig);
        params.put("MR_AmountMax", mSmall);
        params.put("MR_PrepaidTimeMin", mStartTime);
        params.put("MR_PrepaidTimeMax", mEndTime);
        params.put("Device", mDevice);
        params.put("SM_GID", mVipInfo.getSM_GID());
        client.post(MyApplication.BASE_URL+"Recharge/GetMoneyRecords", params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mReportBean = CommonFun.JsonToObj(responseString, RechargeMoneyReportBean.class);
                mPageTotal = mReportBean.getData().getPageTotal();
                //充值合计
                mRechangeTotalNum.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getStatisticsInfo().getRechargeTotalMoney() + ""));
                //赠送合计
                mGiveTotalNum.setText(Decima2KeeplUtil.stringToDecimal(mReportBean.getData().getStatisticsInfo().getGiveTotalMoney() + ""));
                mIsLoadMore = false;
                mDatas = mReportBean.getData();
                initAdapter();

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_backvipdetail_activity:
                VipRechangeMoney.this.finish();
                break;
            case R.id.tv_start_date:
                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mStartDates);
                break;
            case R.id.tv_end_date:
                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mEndDates);
                break;
            case R.id.btn_date_confirm:
                if (mStartDates.getText().toString().isEmpty()) {
                    CustomToast.makeText(this, "请选择开始日期", Toast.LENGTH_SHORT).show();
                    return;
                } else if (mEndDates.getText().toString().isEmpty()) {
                    CustomToast.makeText(this, "请选择截止日期", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    mL.setVisibility(View.GONE);
                    mStartTimes.setText(mStartDates.getText().toString());
                    mEndTimes.setText(mEndDates.getText().toString());
                    mStartTime = mStartDates.getText().toString();
                    mEndTime = mEndDates.getText().toString();
                    getRechargeMoneyReport(1, 20);

                }
                break;
            case R.id.rl_title_date:
                mMenuWindow.showAsDropDown(mTitledates);
                mL.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.tv_menu_pop_first:
                mTitledates.setText("今日");
                mTos.setVisibility(View.VISIBLE);
                mStartTimes.setText(DateTimeUtil.getNowDate());
                mEndTimes.setText(DateTimeUtil.getNowDate());
                mMenuWindow.dismiss();
                mStartTime = DateTimeUtil.getNowDate();
                mEndTime = DateTimeUtil.getNowDate();
                getRechargeMoneyReport(1, 20);
                break;
            case R.id.tv_menu_pop_second:
                mTitledates.setText("昨日");
                mTos.setVisibility(View.VISIBLE);
                mStartTimes.setText(DateTimeUtil.getLastDate());
                mEndTimes.setText(DateTimeUtil.getLastDate());
                mMenuWindow.dismiss();
                mStartTime = DateTimeUtil.getLastDate();
                mEndTime = DateTimeUtil.getLastDate();
                getRechargeMoneyReport(1, 20);
                break;
            case R.id.tv_menu_pop_third:
                mTitledates.setText("本周");
                mTos.setVisibility(View.VISIBLE);
                mStartTimes.setText(DateTimeUtil.getNowWeekFirstDate());
                mEndTimes.setText(DateTimeUtil.getNowWeekFinalDate());
                mMenuWindow.dismiss();
                mStartTime = DateTimeUtil.getNowWeekFirstDate();
                mEndTime = DateTimeUtil.getNowWeekFinalDate();
                getRechargeMoneyReport(1, 20);
                break;
            case R.id.tv_menu_pop_fourth:
                mTos.setVisibility(View.VISIBLE);
                mL.setVisibility(View.VISIBLE);
                mTitledates.setText("其它");
                mStartTimes.setText(mStartTime);
                mEndTimes.setText(mEndTime);
                mMenuWindow.dismiss();
                break;
            default:
                mMenuWindow.dismiss();
                break;
        }
    }
}
