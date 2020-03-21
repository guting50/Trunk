package com.zhiluo.android.yunpu.statistics.account.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.bean.IntegralDetailReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

public class IntegralDetailActivity extends BaseActivity {
    private TextView mTvTitle, mTvName, mTvCard, mTvIntegralType, mTvIntegralNum, mTvChannel, mTvExplain,
            mTvTime, mTvOperation;
    private IntegralDetailReportBean.DataBean.DataListBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_detail);
        initView();
        initVariable();
        setListener();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("积分明细详情");
        mTvName = (TextView) findViewById(R.id.tv_money_detail_name);
        mTvCard = (TextView) findViewById(R.id.tv_money_detail_card);
        mTvIntegralType = (TextView) findViewById(R.id.tv_money_detail_money_type);
        mTvIntegralNum = (TextView) findViewById(R.id.tv_money_detail_pay_total);
        mTvChannel = (TextView) findViewById(R.id.tv_money_detail_account_yu);
        mTvExplain = (TextView) findViewById(R.id.tv_money_detail_pay_cash);
        mTvTime = (TextView) findViewById(R.id.tv_money_detail_pay_yue);
        mTvOperation = (TextView) findViewById(R.id.tv_money_detail_pay_bank);
    }

    private void initVariable() {
        mBean = (IntegralDetailReportBean.DataBean.DataListBean) getIntent().getSerializableExtra("point");
        mTvName.setText(mBean.getVIP_Name() == null ? "" : mBean.getVIP_Name());
        mTvCard.setText(mBean.getVCH_Card() == null ? "" : mBean.getVCH_Card());
        if ("1".equals(mBean.getType())) {
            mTvIntegralType.setText("增加");
        } else if ("0".equals(mBean.getType())) {
            mTvIntegralType.setText("减少");
        } else {
            mTvIntegralType.setText("");
        }
        mTvIntegralNum.setText(Decima2KeeplUtil.stringToDecimal(mBean.getNumber()));
        mTvChannel.setText(mBean.getIdentifying() == null ? "" : mBean.getIdentifying());
        mTvExplain.setText(mBean.getReamrk() == null ? "" : mBean.getReamrk());
        mTvTime.setText(mBean.getIntegralTime() == null ? "" : mBean.getIntegralTime());
        mTvOperation.setText(mBean.getCreator() == null ? "" : mBean.getCreator());
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
