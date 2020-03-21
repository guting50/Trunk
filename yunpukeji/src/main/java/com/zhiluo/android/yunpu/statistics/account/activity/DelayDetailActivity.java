package com.zhiluo.android.yunpu.statistics.account.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.bean.DelayReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

public class DelayDetailActivity extends BaseActivity {
    private TextView mTvTitle, mTvOrder, mTvVipName, mTvVipCard, mTvDelayNum, mTvDelayMoney, mTvPayWay,
            mTvDelayTime, mTvOperation, mTvRemark;
    private DelayReportBean.DataBean.DataListBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_detail);
        initView();
        initVariable();
        setListener();

    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("延期记录详情");
        mTvOrder = (TextView) findViewById(R.id.tv_delay_order);
        mTvVipName = (TextView) findViewById(R.id.tv_delay_member_name);
        mTvVipCard = (TextView) findViewById(R.id.tv_delay_member_card);
        mTvDelayNum = (TextView) findViewById(R.id.tv_delay_num);
        mTvDelayMoney = (TextView) findViewById(R.id.tv_delay_money);
        mTvPayWay = (TextView) findViewById(R.id.tv_delay_pay_way);
        mTvDelayTime = (TextView) findViewById(R.id.tv_delay_time);
        mTvOperation = (TextView) findViewById(R.id.tv_delay_operation);
        mTvRemark = (TextView) findViewById(R.id.tv_delay_remark);
    }

    private void initVariable() {
        mBean = (DelayReportBean.DataBean.DataListBean) getIntent().getSerializableExtra("delay");
        mTvOrder.setText(mBean.getMDR_Order() == null ? "" : mBean.getMDR_Order());
        mTvVipName.setText(mBean.getVIP_Name() == null ? "" : mBean.getVIP_Name());
        mTvVipCard.setText(mBean.getVIP_Card() == null ? "" : mBean.getVIP_Card());
        mTvDelayNum.setText(mBean.getMDR_Time() == null ? "" : mBean.getMDR_Time());
        mTvDelayMoney.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getMDR_Money() + ""));
        mTvPayWay.setText(mBean.getMDR_PayType() == null ? "" : mBean.getMDR_PayType());
        mTvDelayTime.setText(mBean.getMDR_UpdateTime() == null ? "" : mBean.getMDR_UpdateTime());
        mTvOperation.setText(mBean.getMDR_Creator() == null ? "" : mBean.getMDR_Creator());
        mTvRemark.setText(mBean.getMDR_DelayRemark() == null ? "" : mBean.getMDR_DelayRemark());
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
