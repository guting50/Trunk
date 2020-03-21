package com.zhiluo.android.yunpu.ui.fragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.analysis.activity.GoodAnalysisActivity;
import com.zhiluo.android.yunpu.analysis.activity.MemberAnalysisActivity;
import com.zhiluo.android.yunpu.analysis.bean.StatisticsBean;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.account.activity.MemberAccountActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.rebate.activity.RebateActivity;
import com.zhiluo.android.yunpu.statistics.staff.activity.StaffReportActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

/**
 * 服务
 * 作者：罗咏哲 on 2017/10/23 18:17.
 * 邮箱：137615198@qq.com
 */

public class ReportFragment extends Fragment {
    private TabLayout mTabLayout;
    private String mTabs[];
    private LinearLayout mLlDateSelector;
    private TextView mTvStartDate, mTvEndDate;//开始、结束时间
    private String mStartTime, mEndTime;
    private int mFlag = 1;
    private StatisticsBean mStatisticsBean;
    private Handler mHandler;
    private Dialog dateDialog;
    private TextView mTvTotal, mTvCash, mTvCard, mTvBalance, mTvGoodConsume, mTvFastConsume, mTvGoodReturn,
            mTvVipCreate, mTvVipRecharge, mTvVipTimes, mTvKouKuan, mTvDelay, mTvZFB, mTvWX;

    private SaveScreenStateUtil mStateUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage_report, container, false);
        mStateUtil=new SaveScreenStateUtil(getContext());
        initView(view);
        initVariable();
        loadData();
        setListener(view);
        return view;
    }

    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_statistics);
        mLlDateSelector = (LinearLayout) view.findViewById(R.id.ll_statistics_time_other);
        mLlDateSelector.setVisibility(View.GONE);
        mTvStartDate = (TextView) view.findViewById(R.id.tv_start_date);
        mTvEndDate = (TextView) view.findViewById(R.id.tv_end_date);
        mTvTotal = (TextView) view.findViewById(R.id.tv_statistics_total);
        mTvCash = (TextView) view.findViewById(R.id.tv_statistics_cash);
        mTvCard = (TextView) view.findViewById(R.id.tv_statistics_card);
        mTvBalance = (TextView) view.findViewById(R.id.tv_statistics_balance);
        mTvGoodConsume = (TextView) view.findViewById(R.id.tv_statistics_good);
        mTvFastConsume = (TextView) view.findViewById(R.id.tv_statistics_fast);
        mTvGoodReturn = (TextView) view.findViewById(R.id.tv_statistics_cancel);
        mTvVipCreate = (TextView) view.findViewById(R.id.tv_statistics_vip_create);
        mTvVipRecharge = (TextView) view.findViewById(R.id.tv_statistics_vip_recharge);
        mTvVipTimes = (TextView) view.findViewById(R.id.tv_statistics_vip_times);
        mTvKouKuan = (TextView) view.findViewById(R.id.tv_statistics_vip_koukuan);
        mTvDelay = (TextView) view.findViewById(R.id.tv_statistics_vip_delay);
        mTvZFB = (TextView) view.findViewById(R.id.tv_statistics_zfb);
        mTvWX = (TextView) view.findViewById(R.id.tv_statistics_wx);
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
    private void setListener(View view) {
        view.findViewById(R.id.tv_statistics_member_account).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MemberAccountActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.tv_statistics_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OrderActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.tv_statistics_staff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), StaffReportActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.tv_statistics_recommend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RebateActivity.class);
                startActivity(intent);
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
        view.findViewById(R.id.tv_statistics_good_analysis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), GoodAnalysisActivity.class);
                startActivity(intent);
            }
        });
        //会员分析
        view.findViewById(R.id.tv_statistics_member_analysis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MemberAnalysisActivity.class);
                startActivity(intent);
            }
        });
        //确定时间
        view.findViewById(R.id.btn_date_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String start = mTvStartDate.getText().toString();
                String end = mTvEndDate.getText().toString();
                if (start.isEmpty() && !end.isEmpty()) {
                    CustomToast.makeText(getContext(), "请选择开始时间！", Toast.LENGTH_SHORT).show();
                }
                if (end.isEmpty() && !start.isEmpty()) {
                    CustomToast.makeText(getContext(), "请选择结束时间！", Toast.LENGTH_SHORT).show();
                }
//                mLlDateSelector.setVisibility(View.GONE);
                if (!start.isEmpty() && !end.isEmpty()) {
                    try {
                        if (DateTimeUtil.isOverTime(getContext(),mStartTime,mEndTime)){
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
//                    showDatePickDlg(view,DateUtil.getDateForString(DateTimeUtil.getNowDate()));
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvStartDate);
                }else {
                    showDateDialog(DateUtil.getDateForString(mTvStartDate.getText().toString()), mTvStartDate);
                }

//                showDatePickDlg(view);
            }
        });
        //选择结束时间监听
        mTvEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTvEndDate.getText().toString() ==null || mTvEndDate.getText().toString().equals("")){
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), mTvEndDate);
                }else {
                    showDateDialog(DateUtil.getDateForString(mTvEndDate.getText().toString()), mTvEndDate);
                }
//                showDatePickDlg(view);
            }
        });
    }


    /**
        * @param date      ,
        * @param mTextView ,
        *                  选择日期
     */
    private void showDateDialog(List<Integer> date, final TextView mTextView) {
        com.example.liangmutian.mypicker.DatePickerDialog.Builder builder = new com.example.liangmutian.mypicker.DatePickerDialog.Builder(getContext());
        builder.setOnDateSelectedListener(new com.example.liangmutian.mypicker.DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                String data = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));
                mTextView.setText(data);

                if (mTextView.getId() == R.id.tv_start_date) {

                    mStartTime = data;
                    DateTimeUtil.isCurTime(getActivity(),mStartTime);
                }
                if (mTextView.getId() == R.id.tv_end_date) {
                    mEndTime = data;
                    DateTimeUtil.isCurTime(getActivity(),mEndTime);
                }
            }

            @Override
            public void onCancel() {

            }
        }).setSelectYear(date.get(0) - 1).setSelectMonth(date.get(1) - 1).setSelectDay(date.get(2) - 1);
        dateDialog = builder.create();
        dateDialog.show();
    }
    protected void showDatePickDlg(final View v,List<Integer> date) {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int momth = monthOfYear + 1;
                        if (v.getId() == R.id.tv_start_date) {
                            ReportFragment.this.mTvStartDate.setText(year + "-" + momth + "-" + dayOfMonth);
                            mStartTime = year + "-" + momth + "-" + dayOfMonth;
                        }
                        if (v.getId() == R.id.tv_end_date) {
                            ReportFragment.this.mTvEndDate.setText(year + "-" + momth + "-" + dayOfMonth);
                            mEndTime = year + "-" + momth + "-" + dayOfMonth;
                        }
                    }
                }, calendar.get(date.get(0)-1), calendar.get(date.get(1)-1), calendar.get(date.get(2)-1));
        DatePicker dp = datePickerDialog.getDatePicker();
        dp.setMaxDate(System.currentTimeMillis());//设置可选的最大日期
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.show();
    }

    private void getData(String startTime, String endTime) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(getContext());
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
                CustomToast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy() {
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
        HttpHelper.getmClient().cancelRequests(getContext(), true);
    }
}
