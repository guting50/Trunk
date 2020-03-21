package com.zhiluo.android.yunpu.statistics.account.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.adapters.VipRechangeMoneyAdapter;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.account.bean.MoneyDetailReportBean;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeMoneyReportBean;
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by YSL on 2018-06-08.
 */

public class ChuZhiActivity extends AppCompatActivity implements VipRechangeMoneyAdapter.cancelClick,VipRechangeMoneyAdapter.printClick{
    private RechargeMoneyReportBean mReportBean;
    private RechargeMoneyReportBean.DataBean.DataListBean mBean;
    private GetValueEvent mEvent = new GetValueEvent();
    private WaveSwipeRefreshLayout mRefreshLayout;
    private View mView;
    private String mVipCondition, mOrder, mBig, mSmall, mDevice, mPayWayCode, mStartTime, mEndTime;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private int mCurrentPosition;//当前滑动位置
    private boolean mIsLoadMore;//加载更多
    private TextView mTvDate;
    private String mSmGid;

    private ExpandableListView mListView;
    private VipRechangeMoneyAdapter mAdapter;
    private RechargeMoneyReportBean.DataBean mDatas;
    private TextView tvBack;
    private List<String> list;
    private Dialog chooseDialog;
    private SweetAlertDialog mSweetAlertDialog;
    private LinearLayout lDate, lShaoXuan;
    private TextView tvDate, tvStartTime, tvEndTime,tvnull;
    private Button btComfirm;
    private Dialog dateDialog;
    private TextView tvChuZhi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuzhi);
        initViews();
        setListener();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add("今日");
        list.add("昨日");
        list.add("本周");
        list.add("其它");
        mVipCondition = getIntent().getStringExtra("vipcard");
        getRechargeMoneyReport(1, 10);
    }


    private void initViews() {
        mListView = findViewById(R.id.listview);
        mRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.recharge_money_refresh);
        tvBack = findViewById(R.id.tv_back);
        tvDate = findViewById(R.id.tv_date);
        lDate = findViewById(R.id.ll_order_date_selector);
        lShaoXuan = findViewById(R.id.l_shaixuan);
        tvStartTime = findViewById(R.id.tv_start_date);
        tvEndTime = findViewById(R.id.tv_end_date);
        btComfirm = findViewById(R.id.btn_date_confirm);
        tvChuZhi = findViewById(R.id.tv_titel);
        lDate.setVisibility(View.GONE);
        tvnull = findViewById(R.id.tv_null);
        tvChuZhi.setText("储值详情");
    }

    private void initAdapter(RechargeMoneyReportBean.DataBean mDatas) {
        mAdapter = new VipRechangeMoneyAdapter(mDatas, this,this,this);
        mListView.setGroupIndicator(null);
        mListView.setAdapter(mAdapter);
        mListView.setSelection(mCurrentPosition);
    }

    private void setListener() {
        btComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (TextUtils.isEmpty(tvStartTime.getText())) {
                        CustomToast.makeText(ChuZhiActivity.this, "请选择开始时间", Toast.LENGTH_SHORT).show();
                    } else if (TextUtils.isEmpty(tvEndTime.getText())) {
                        CustomToast.makeText(ChuZhiActivity.this, "请选择结束时间", Toast.LENGTH_SHORT).show();
                    } else if (DateTimeUtil.isOverTime(ChuZhiActivity.this, mStartTime, mEndTime)) {
                        if ( !DateTimeUtil.isCurTime(ChuZhiActivity.this,mStartTime)){
                            return;
                        }else if (!DateTimeUtil.isCurTime(ChuZhiActivity.this,mEndTime)){
                            return;
                        }
                        lDate.setVisibility(View.GONE);
                        mStartTime = tvStartTime.getText().toString();
                        mEndTime = tvEndTime.getText().toString();
                        getRechargeMoneyReport(1, 10);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tvStartTime.getText().toString() == null || tvStartTime.getText().toString().equals("")) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvStartTime,0);
                } else {
                    showDateDialog(DateUtil.getDateForString(tvStartTime.getText().toString()), tvStartTime,0);
                }
//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvStartTime);
            }
        });
        tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvEndTime.getText().toString() == null || tvEndTime.getText().toString().equals("")) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvEndTime,1);
                } else {
                    showDateDialog(DateUtil.getDateForString(tvEndTime.getText().toString()), tvEndTime,1);
                }

//                showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvEndTime);
            }
        });
        lShaoXuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(list, tvDate);
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChuZhiActivity.this.finish();
            }
        });
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRechargeMoneyReport(1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getRechargeMoneyReport(mRefreshIndex, 10);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(ChuZhiActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    mRefreshLayout.setLoading(false);
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
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    mCurrentPosition = mListView.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
    }

    private void getRechargeMoneyReport(int index, int pageSize) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
//        params.put("OrderCode", mOrder);
        params.put("VIP_GID", mVipCondition);
//        params.put("MR_Code", mPayWayCode);
//        params.put("MR_AmountMin", mBig);
//        params.put("MR_AmountMax", mSmall);
        params.put("MR_PrepaidTimeMin", mStartTime);
        params.put("MR_PrepaidTimeMax", mEndTime);
//        params.put("Device", mDevice);
//        params.put("SM_GID", mSmGid);
        client.post(MyApplication.BASE_URL+"Recharge/GetMoneyRecords", params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                RechargeMoneyReportBean reportBean = CommonFun.JsonToObj(responseString, RechargeMoneyReportBean.class);
                if (mReportBean != null && mIsLoadMore) {
                    List<RechargeMoneyReportBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mReportBean.getData().getDataList());
                    list.addAll(reportBean.getData().getDataList());
                    mReportBean.getData().setDataList(list);
                } else {
                    mReportBean = reportBean;
                }
                mDatas = mReportBean.getData();
                mPageTotal = mReportBean.getData().getPageTotal();
                if (mReportBean.getData().getDataList().size() > 0) {
                    tvnull.setVisibility(View.GONE);
                } else {
                    tvnull.setVisibility(View.VISIBLE);
                }
                initAdapter(mDatas);
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setLoading(false);
                mIsLoadMore = false;


            }

            @Override
            public void onFailure(String msg) {
                Log.i("lyz", "onFailure: " + msg);
            }
        });



//        params.put("PageIndex", index);
//        params.put("PageSize", pageSize);
//        params.put("OrderCode", mOrder);
//        params.put("VIP_GID", mVipCondition);
//        params.put("StartDate", mStartTime);
//        params.put("EndDate", mEndTime);
//        params.put("Type", "");
//        params.put("SM_GID", mSmGid);
//
//        client.post(MyApplication.BASE_URL+"BillingDetails/GetAmountDetails", params, new MyTextHttpResponseHandler() {
//            @Override
//            public void onSuccess(String responseString, Gson gson) {
//
//
//                MoneyDetailReportBean reportBean = CommonFun.JsonToObj(responseString, MoneyDetailReportBean.class);
//                if (mReportBean != null && mIsLoadMore) {
//                    List<MoneyDetailReportBean.DataBean.DataListBean> list = new ArrayList<>();
//                    list.addAll(mReportBean.getData().getDataList());
//                    list.addAll(reportBean.getData().getDataList());
//                    mReportBean.getData().setDataList(list);
//                } else {
//                    mReportBean = reportBean;
//                }
//                mDatas = mReportBean;
//                mPageTotal = mReportBean.getData().getPageTotal();
//
//                if (mReportBean.getData().getDataList().size() > 0) {
//                    tvnull.setVisibility(View.GONE);
//                } else {
//                    tvnull.setVisibility(View.VISIBLE);
//                }
//                initAdapter(mDatas,index);
//                mRefreshLayout.setRefreshing(false);
//                mRefreshLayout.setLoading(false);
//                mIsLoadMore = false;
//
//
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                Log.i("lyz", "onFailure: " + msg);
//            }
//        });
    }

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
                        switch (itemValue) {
                            case "今日":
                                mStartTime = DateTimeUtil.getNowDate();
                                mEndTime = DateTimeUtil.getNowDate();
                                lDate.setVisibility(View.GONE);
                                getRechargeMoneyReport(1, 10);
                                break;
                            case "昨日":
                                mStartTime = DateTimeUtil.getLastDate();
                                mEndTime = DateTimeUtil.getLastDate();
                                lDate.setVisibility(View.GONE);
                                getRechargeMoneyReport(1, 10);
                                break;
                            case "本周":
                                mStartTime = DateTimeUtil.getNowWeekFirstDate();
                                mEndTime = DateTimeUtil.getNowWeekFinalDate();
                                lDate.setVisibility(View.GONE);
                                getRechargeMoneyReport(1, 10);
                                break;
                            case "其它":
                                lDate.setVisibility(View.VISIBLE);
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

    private void showDateDialog(List<Integer> date, final TextView mTextView,final int tag) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                String str = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));
                mTextView.setText(str);
                if (tag ==0){
                    mStartTime = str;
                    DateTimeUtil.isCurTime(ChuZhiActivity.this,mStartTime);
                }else if (tag ==1){
                    mEndTime = str;
                    DateTimeUtil.isCurTime(ChuZhiActivity.this,mEndTime);
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
    public void pClick(View view) {
        int pos = (int) view.getTag();
        if (MyApplication.PRINT_IS_OPEN){
            new HttpGetPrintContents(ChuZhiActivity.this, MyApplication.HYCZ_PRINT_TIMES, mDatas.getDataList().get(pos).getGID()).HYCZ();
        }else {
            CustomToast.makeText(ChuZhiActivity.this,"打印开关未开启", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void cClick(View view) {
        int pos = (int) view.getTag();
        if (MyApplication.IS_CANCEL_ORDER){
            showPasswordDialog(pos);
        }else {
            undoMoneyOrder(pos);
        }
    }

    /**
     * 密码输入框
     */
    private void showPasswordDialog(final  int pos) {
        LayoutInflater inflater = getLayoutInflater();
        final View dialog = inflater.inflate(R.layout.input_password_dialog_layout, (ViewGroup) findViewById(R.id.input_dialog));
        final EditText etPassword = (EditText) dialog.findViewById(R.id.et_input_dialog_password);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_input_cancel);
        Button btnConfirm = (Button) dialog.findViewById(R.id.btn_input_confirm);
        AlertDialog.Builder builder = new AlertDialog.Builder(ChuZhiActivity.this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setContentView(dialog);
        alertDialog.setView(dialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (!etPassword.getText().toString().isEmpty()&&etPassword.getText().toString().equals(MyApplication.CANCEL_PAW)){
                    undoMoneyOrder(pos);
                }else {
                    CustomToast.makeText(ChuZhiActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    //充值撤单
    private void undoMoneyOrder(int pos){
        RequestParams params = new RequestParams();
        params.put("GID",mDatas.getDataList().get(pos).getGID());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(ChuZhiActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("撤单成功");
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mSweetAlertDialog.dismiss();
                        getRechargeMoneyReport( 1, 10);
                    }
                });
                mSweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ChuZhiActivity.this,msg,Toast.LENGTH_SHORT).show();

            }
        };
        HttpHelper.post(ChuZhiActivity.this, HttpAPI.API().CZ_UNDO_ORDER,params,callBack);
    }
}
