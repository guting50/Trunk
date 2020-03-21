package com.zhiluo.android.yunpu.paymanager.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.paymanager.activity.ChoiceExpensesActivity;
import com.zhiluo.android.yunpu.paymanager.activity.PayDetailActivity;
import com.zhiluo.android.yunpu.paymanager.adapter.PayDetailAdapter;
import com.zhiluo.android.yunpu.paymanager.bean.PayDetailBean;
import com.zhiluo.android.yunpu.statistics.account.activity.MemberAccountActivity;
import com.zhiluo.android.yunpu.statistics.handover.HandOverReportActivity;
import com.zhiluo.android.yunpu.statistics.handover.HandOverReportBean;
import com.zhiluo.android.yunpu.statistics.handover.HandOverReportDetail;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2019-05-21.
 */

public class PayDetailFragment extends Fragment {

    @Bind(R.id.tv_start_date)
    TextView mTvStartDate;
    @Bind(R.id.tv_end_date)
    TextView mTvEndDate;
    @Bind(R.id.btn_date_confirm)
    Button btnDateConfirm;
    @Bind(R.id.ll_handover_date_selector)
    LinearLayout llHandoverDateSelector;
    @Bind(R.id.tv_null)
    TextView tvNull;
    @Bind(R.id.list_view)
    BaseListView listView;
    @Bind(R.id.refresh)
    WaveSwipeRefreshLayout refresh;

    private View mView;
    private PayDetailReceiver payDetailReceiver;
    private String vip, oneGid, twoGid;
    private Dialog dateDialog;
    private String start;
    private String end;
    private PayDetailBean mPayBean;
    private PayDetailBean.DataBean.DataListBean mBean;
    private PayDetailAdapter mAdapter;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数

    private boolean mIsLoadMore;//加载更多


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(payDetailReceiver);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_detail, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GetExpensesDetailListPage(1,20);
        setListener();

        if (payDetailReceiver == null) {
            payDetailReceiver = new PayDetailReceiver();
        }
        IntentFilter filer1 = new IntentFilter();
        filer1.addAction("android.intent.action.DETAIL_BROADCAST");
        getActivity().registerReceiver(payDetailReceiver, filer1);
    }




    private void setListener() {

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
        btnDateConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start = mTvStartDate.getText().toString();
                end = mTvEndDate.getText().toString();
                if (start.isEmpty()) {
                    CustomToast.makeText(getActivity(), "请选择开始时间！", Toast.LENGTH_SHORT).show();
                }
                if (end.isEmpty()) {
                    CustomToast.makeText(getActivity(), "请选择结束时间！", Toast.LENGTH_SHORT).show();
                }
                if (!start.isEmpty() && !end.isEmpty()) {
                    if ( !DateTimeUtil.isCurTime(getActivity(),start)){
                        return;
                    }else if (!DateTimeUtil.isCurTime(getActivity(),end)){
                        return;
                    }

                    try {
                        if (DateTimeUtil.isOverTime(getActivity(),start,end)){

                            GetExpensesDetailListPage(1,20);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        refresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GetExpensesDetailListPage(1, 20);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    GetExpensesDetailListPage(mRefreshIndex, 20);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                    refresh.setLoading(false);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBean = mPayBean.getData().getDataList().get(i);

                Intent intent = new Intent(getActivity(), PayDetailActivity.class);

                intent.putExtra("PAYDETAIL", (Serializable) mBean);
                startActivityForResult(intent,0);
            }
        });

    }

    /**
     * @param date      ,
     * @param mTextView ,
     *                  选择日期
     */
    private void showDateDialog(List<Integer> date, final TextView mTextView,final int tag) {
        com.example.liangmutian.mypicker.DatePickerDialog.Builder builder = new com.example.liangmutian.mypicker.DatePickerDialog.Builder(getActivity());
        builder.setOnDateSelectedListener(new com.example.liangmutian.mypicker.DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                String str = dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]));
                mTextView.setText(str);
                if (tag ==0){
                    start = str;
                    DateTimeUtil.isCurTime(getActivity(),start);
                }else if (tag ==1){
                    end = str;
                    DateTimeUtil.isCurTime(getActivity(),start);
                }
            }

            @Override
            public void onCancel() {

            }
        }).setSelectYear(date.get(0) - 1).setSelectMonth(date.get(1) - 1).setSelectDay(date.get(2) - 1);
        dateDialog = builder.create();
        dateDialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    class PayDetailReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getBundleExtra("Detail") != null) {
                vip = intent.getBundleExtra("Detail").getString("vip", "");
                oneGid = intent.getBundleExtra("Detail").getString("One", "");
                twoGid = intent.getBundleExtra("Detail").getString("Two", "");

                GetExpensesDetailListPage(1,20);
            }

            if (intent.getBundleExtra("updata") != null) {
                GetExpensesDetailListPage(1,20);
            }
        }
    }

    private void GetExpensesDetailListPage(int index,int pagesize){
        RequestParams params = new RequestParams();
        params.put("PageIndex",index);
        params.put("PageSize",pagesize);
        params.put("Expendiator",vip);
        params.put("StartTime",start);
        params.put("EndTime",end);
        params.put("ETPTGID",oneGid);
        params.put("ETGID",twoGid);

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                PayDetailBean PayBean = CommonFun.JsonToObj(responseString,PayDetailBean.class);
                if (mPayBean != null && mIsLoadMore) {
                    List<PayDetailBean.DataBean.DataListBean> list = new ArrayList<>();
                    list.addAll(mPayBean.getData().getDataList());
                    list.addAll(PayBean.getData().getDataList());
                    mPayBean.getData().setDataList(list);
                } else {
                    mPayBean = PayBean;
                }
                mPageTotal = mPayBean.getData().getPageTotal();
                refresh.setRefreshing(false);
                refresh.setLoading(false);
                mIsLoadMore = false;
                if (mPayBean.getData().getDataList().size()>0){
                    tvNull.setVisibility(View.GONE);
                }else {
                    tvNull.setVisibility(View.VISIBLE);
                }
                setmAdapter();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
                refresh.setRefreshing(false);
                refresh.setLoading(false);
            }
        };
        callBack.setLoadingAnimation(getActivity(),"加载中...",false);
        HttpHelper.post(getActivity(), HttpAPI.API().Expenses_DetailList,params,callBack);

    }

    private void setmAdapter(){
        if (mAdapter ==null){
            mAdapter = new PayDetailAdapter(getActivity(),mPayBean);
            listView.setAdapter(mAdapter);
        }else {
            mAdapter.setParm(mPayBean);
            mAdapter.notifyDataSetChanged();
        }
    }
}
