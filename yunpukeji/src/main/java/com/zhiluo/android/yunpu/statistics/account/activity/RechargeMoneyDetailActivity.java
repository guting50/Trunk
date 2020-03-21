package com.zhiluo.android.yunpu.statistics.account.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeMoneyReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

public class RechargeMoneyDetailActivity extends BaseActivity {
    private TextView mTvTitle, mTvOrderNum, mTvVipName, mTvVipCardNo, mTvRechargeMoney, mTvGiveMoney,
            mTvRechargeTotal, mTvGivePoint, mTvBalance, mTvPayWay, mTvConsumeTime, mTvDevice, mTvOperation, mTvRemark;
    private RechargeMoneyReportBean.DataBean.DataListBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge_money_detail);
        initView();
        initVariable();
        setListener();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("充值记录详情");
        mTvOrderNum = (TextView) findViewById(R.id.tv_goods_order_num);
        mTvVipName = (TextView) findViewById(R.id.tv_goods_order_name);
        mTvVipCardNo = (TextView) findViewById(R.id.tv_goods_order_card);
        mTvRechargeMoney = (TextView) findViewById(R.id.tv_goods_order_consume_money);
        mTvGiveMoney = (TextView) findViewById(R.id.tv_goods_order_discount_money);
        mTvBalance = (TextView) findViewById(R.id.tv_goods_order_balance);
        mTvGivePoint = (TextView) findViewById(R.id.tv_goods_order_point);
        mTvPayWay = (TextView) findViewById(R.id.tv_goods_order_pay_way);
        mTvConsumeTime = (TextView) findViewById(R.id.tv_goods_order_time);
        mTvDevice = (TextView) findViewById(R.id.tv_goods_order_device);
        mTvOperation = (TextView) findViewById(R.id.tv_goods_order_staff);
        mTvRemark = (TextView) findViewById(R.id.tv_goods_order_remark);
        mTvRechargeTotal = (TextView) findViewById(R.id.tv_recharge_total);

    }

    private void initVariable() {
        mBean = (RechargeMoneyReportBean.DataBean.DataListBean) getIntent().getSerializableExtra("recharge");
        mTvOrderNum.setText(mBean.getMR_Order() == null ? "" : mBean.getMR_Order());
        mTvVipName.setText(mBean.getVIP_Name() == null ? "" : mBean.getVIP_Name());
        mTvVipCardNo.setText(mBean.getVCH_Card() == null ? "" : mBean.getVCH_Card());
        mTvRechargeMoney.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getMR_Amount() + ""));
        mTvRechargeTotal.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getMR_Total() + ""));
        mTvGiveMoney.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getMR_GivenAmount() + ""));
        mTvBalance.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getMA_AvailableBalance() + ""));
        mTvGivePoint.setText(Decima2KeeplUtil.stringToDecimal(mBean.getMR_Integral() + ""));
        mTvPayWay.setText(mBean.getMR_Way() == null ? "" : mBean.getMR_Way());
        mTvConsumeTime.setText(mBean.getMR_UpdateTime() == null ? "" : mBean.getMR_UpdateTime());
        if (mBean.getMR_Device() == 1) {
            mTvDevice.setText("网页");
        }
        if (mBean.getMR_Device() == 2) {
            mTvDevice.setText("APP");
        }
        mTvOperation.setText(mBean.getMR_Creator() == null ? "" : mBean.getMR_Creator());
        mTvRemark.setText(mBean.getMR_Remark() == null ? "" : mBean.getMR_Remark());
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
