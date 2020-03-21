package com.zhiluo.android.yunpu.statistics.account.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.bean.MoneyDetailReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

public class MoneyDetailActivity extends BaseActivity {
    private TextView mTvTitle, mTvOrder, mTvName, mTvCard, mTvMoneyType, mTvPayTotal, mTvAccountBalance,
            mTvPayCash, mTvPayBalance, mTvPayBank, mTvPayPoint, mTvPayExplain, mTvPayTime;
    private MoneyDetailReportBean.DataBean.DataListBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_detail);
        initView();
        initVariable();
        setListener();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("金额明细详情");
        mTvOrder = (TextView) findViewById(R.id.tv_money_detail_order);
        mTvName = (TextView) findViewById(R.id.tv_money_detail_name);
        mTvCard = (TextView) findViewById(R.id.tv_money_detail_card);
        mTvMoneyType = (TextView) findViewById(R.id.tv_money_detail_money_type);
        mTvPayTotal = (TextView) findViewById(R.id.tv_money_detail_pay_total);
        mTvAccountBalance = (TextView) findViewById(R.id.tv_money_detail_account_yu);
        mTvPayCash = (TextView) findViewById(R.id.tv_money_detail_pay_cash);
        mTvPayBalance = (TextView) findViewById(R.id.tv_money_detail_pay_yue);
        mTvPayBank = (TextView) findViewById(R.id.tv_money_detail_pay_bank);
        mTvPayPoint = (TextView) findViewById(R.id.tv_money_detail_pay_point);
        mTvPayExplain = (TextView) findViewById(R.id.tv_money_detail_pay_shuomin);
        mTvPayTime = (TextView) findViewById(R.id.tv_money_detail_pay_time);
    }

    private void initVariable() {
        mBean = (MoneyDetailReportBean.DataBean.DataListBean) getIntent().getSerializableExtra("money");
        mTvOrder.setText(mBean.getOrderCode() == null ? "" : mBean.getOrderCode());
        mTvName.setText(mBean.getVIP_Name() == null ? "" : mBean.getVIP_Name());
        mTvCard.setText(mBean.getVCH_Card() == null ? "" : mBean.getVCH_Card());
        mTvMoneyType.setText(mBean.getIdentifying() == null ? "" : mBean.getIdentifying());
        mTvPayTotal.setText("¥"+Decima2KeeplUtil.stringToDecimal(mBean.getMoney()));
        mTvAccountBalance.setText("¥"+Decima2KeeplUtil.stringToDecimal(mBean.getBalanceAfterChange()));
        mTvPayCash.setText("¥"+Decima2KeeplUtil.stringToDecimal(mBean.getCashChange()));
        mTvPayBalance.setText("¥"+Decima2KeeplUtil.stringToDecimal(mBean.getBalanceChange()));
        mTvPayBank.setText("¥"+Decima2KeeplUtil.stringToDecimal(mBean.getUnionChange()));
        mTvPayPoint.setText("¥"+Decima2KeeplUtil.stringToDecimal(mBean.getIntegralChange()));
        mTvPayExplain.setText(mBean.getInstruction() == null ? "" : mBean.getInstruction());
        mTvPayTime.setText(mBean.getTime() == null ? "" : mBean.getTime());
    }

    private void setListener() {
        findViewById(R.id.tv_no_confirm_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
