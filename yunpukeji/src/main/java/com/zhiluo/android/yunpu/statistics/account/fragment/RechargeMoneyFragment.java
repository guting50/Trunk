package com.zhiluo.android.yunpu.statistics.account.fragment;


import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.adapters.VipRechangeMoneyAdapter;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeMoneyReportBean;
import com.zhiluo.android.yunpu.statistics.account.event.GetValueEvent;
import com.zhiluo.android.yunpu.statistics.account.event.ScreenConditionEvent;
import com.zhiluo.android.yunpu.statistics.order.activity.FastConsumeDetailActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.PointExchangeDetailActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 会员充值记录
 * 作者：罗咏哲 on 2017/9/4 15:09.
 * 邮箱：137615198@qq.com
 */

public class RechargeMoneyFragment extends LazyLoadFragment implements VipRechangeMoneyAdapter.cancelClick,VipRechangeMoneyAdapter.printClick{
    private RechargeMoneyReportBean mReportBean;
    private RechargeMoneyReportBean.DataBean.DataListBean mBean;
    private GetValueEvent mEvent = new GetValueEvent();
    private WaveSwipeRefreshLayout mRefreshLayout;
    private View mView;
    private String mVipCondition, mOrder, mBig, mSmall, mDevice, mPayWayCode, mStartTime, mEndTime;
    private int mRefreshIndex = 2;
    private int mPageTotal;//数据总页数
    private boolean mIsLoadMore;//加载更多
    private TextView mTvDate,tvnull;
    private String mSmGid;

    private ExpandableListView mListView;
    private VipRechangeMoneyAdapter mAdapter;
    private RechargeMoneyReportBean.DataBean mDatas;
    private SweetAlertDialog mSweetAlertDialog;


    @Override
    protected int setContentView() {
        return R.layout.fragment_recharge_money;
    }

    @Override
    protected void initView(View view) {
        mView = view;
        mListView = mView.findViewById(R.id.listview);
        tvnull = mView.findViewById(R.id.tv_null);
        mRefreshLayout = (WaveSwipeRefreshLayout) mView.findViewById(R.id.recharge_money_refresh);
        mTvDate = (TextView) getActivity().findViewById(R.id.tv_title_date);
        switch (mTvDate.getText().toString()) {
            case "今日":
                mStartTime = DateTimeUtil.getNowDate();
                mEndTime = DateTimeUtil.getNowDate();
                break;
            case "昨日":
                mStartTime = DateTimeUtil.getLastDate();
                mEndTime = DateTimeUtil.getLastDate();
                break;
            case "本周":
                mStartTime = DateTimeUtil.getNowWeekFirstDate();
                mEndTime = DateTimeUtil.getNowWeekFinalDate();
                break;
            case "其它":
                break;
            default:
                String[] str = mTvDate.getText().toString().split("\\t");
                mStartTime = str[0];
                mEndTime = str[1];
                break;
        }
        setListener();

    }

    /**
     * 初始化变量
     */
    private void initAdapter(View view, RechargeMoneyReportBean.DataBean mDatas, int index) {
        if (mAdapter ==null ||index ==1){
            mAdapter = new VipRechangeMoneyAdapter(mDatas,view.getContext(),this,this);
            mListView.setGroupIndicator(null);
            mListView.setAdapter(mAdapter);
        }else {
            mAdapter.setParams(mDatas);
            mAdapter.notifyDataSetChanged();
        }

    }

    private void setListener() {
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRechargeMoneyReport(mView, 1, 10);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    mIsLoadMore = true;
                    getRechargeMoneyReport(mView, mRefreshIndex, 10);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(mView.getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
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

      /*  mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mBean = mReportBean.getData().getDataList().get(i);
                Intent intent = new Intent(getActivity(), RechargeMoneyDetailActivity.class);
                intent.putExtra("recharge", mBean);
                getActivity().startActivity(intent);
            }
        });*/
    }

    @Override
    protected void lazyLoad() {
        if (!EventBus.getDefault().isRegistered(this)){
            //订阅事件
            EventBus.getDefault().register(this);
        }
        getRechargeMoneyReport(mView, 1, 10);
    }

    @Override
    protected void stopLoad() {
        super.stopLoad();
        if (EventBus.getDefault().isRegistered(this)){
            //取消注册事件
            EventBus.getDefault().unregister(this);
        }
    }

/*    private void updateView(View view) {
        mAdapter = new RechargeMoneyAdapter(view.getContext(), mReportBean);
        mListView.setAdapter(mAdapter);
        mListView.setSelection(mCurrentPosition);
    }*/

    private void getRechargeMoneyReport(final View view, final int index, int pageSize) {
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", pageSize);
        params.put("OrderCode", mOrder);
        params.put("VCH_CardName", mVipCondition);
        params.put("MR_Code", mPayWayCode);
        params.put("MR_AmountMin", mBig);
        params.put("MR_AmountMax", mSmall);
        params.put("MR_PrepaidTimeMin", mStartTime);
        params.put("MR_PrepaidTimeMax", mEndTime);
        params.put("Device", mDevice);
        params.put("SM_GID", mSmGid);
        CallBack callBack = new CallBack() {
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
                mEvent.setName1("充值合计（元）");
                mEvent.setName2("赠送合计（元）");
                mEvent.setValue1(Decima2KeeplUtil.stringToDecimal(mReportBean.getData()
                        .getStatisticsInfo().getRechargeTotalMoney() + ""));
                mEvent.setValue2(Decima2KeeplUtil.stringToDecimal(mReportBean.getData()
                        .getStatisticsInfo().getGiveTotalMoney() + ""));
                EventBus.getDefault().post(mEvent);//发布事件
                // updateView(view);
                if (mReportBean.getData().getDataList().size()>0){
                    tvnull.setVisibility(View.GONE);
                }else {
                    tvnull.setVisibility(View.VISIBLE);
                }
                initAdapter(view, mDatas,index);
                mRefreshLayout.setRefreshing(false);
                mRefreshLayout.setLoading(false);
                mIsLoadMore = false;

            }

            @Override
            public void onFailure(String msg) {

            }
        };
        callBack.setLoadingAnimation(getActivity(),"加载中...",false);
        HttpHelper.post(getActivity(), MyApplication.BASE_URL+"Recharge/GetMoneyRecords", params, callBack);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(ScreenConditionEvent event) {
        mVipCondition = event.getVipCondition();
        mOrder = event.getOrder();
        mPayWayCode = event.getPayWayCode();
        mDevice = event.getDevice();
        mStartTime = event.getStartDate();
        mEndTime = event.getEndDate();
        mSmall = event.getSmall();
        mBig = event.getBig();
        mSmGid = event.getGid();
        if (getUserVisibleHint()) {
            getRechargeMoneyReport(mView, 1, 10);
        }
    }

    @Override
    public void pClick(View view) {
        int pos = (int) view.getTag();
        if (MyApplication.PRINT_IS_OPEN){
            new HttpGetPrintContents(getActivity(), MyApplication.HYCZ_PRINT_TIMES, mDatas.getDataList().get(pos).getGID()).HYCZ();
        }else {
            CustomToast.makeText(getActivity(),"打印开关未开启", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
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
                    CustomToast.makeText(getActivity(), "密码错误！", Toast.LENGTH_SHORT).show();
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
                mSweetAlertDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("撤单成功");
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mSweetAlertDialog.dismiss();
                        getRechargeMoneyReport(mView, 1, 10);
                    }
                });
                mSweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();

            }
        };
        HttpHelper.post(getActivity(), HttpAPI.API().CZ_UNDO_ORDER,params,callBack);
    }
}
