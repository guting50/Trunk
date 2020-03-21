package com.zhiluo.android.yunpu.historyconsumption;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.manager.bean.YSLMemberInfoBean;

/**
 * Created by ${YSL} on 2018-03-30.
 */

public class HistoryRecoderActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mViprechange, mVipinflatecount, mFastconsume, mGoodsconsume, mcConsumebycount;
    private RelativeLayout mIntegralexchange, mIntegraldetail, mMoneydetail, mVipdelay;
    private TextView mTitleback;
    private Intent mIntent;
    private Bundle mBundle;
    private YSLMemberInfoBean.DataBean.DataListBean mVipInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_historyrecoder);
        initViews();
        initDatas();

    }

    /**
     * 初始化化变量
     */
    private void initDatas() {
        mBundle = getIntent().getBundleExtra("bundle");
        mVipInfo = (YSLMemberInfoBean.DataBean.DataListBean) mBundle.getSerializable("YSL_VIP_INFO");
    }

    private void initViews() {
        mViprechange = findViewById(R.id.r_vip_rechange);//会员充值
        mVipinflatecount = findViewById(R.id.r_vip_inflatecount);//会员充次
        mFastconsume = findViewById(R.id.r_fast_consume);//快速消费
        mGoodsconsume = findViewById(R.id.r_goods_consume);//商品消费
        mcConsumebycount = findViewById(R.id.r_consume_bycount);//计次消费
        mIntegralexchange = findViewById(R.id.r_integral_exchange);//积分兑换
        mIntegraldetail = findViewById(R.id.r_integral_detail);//积分详情
        mMoneydetail = findViewById(R.id.r_money_detail);//金额明细
        mVipdelay = findViewById(R.id.r_vip_delay);//会员延期
        mTitleback = findViewById(R.id.tv_title_back);//返回按钮

        mViprechange.setOnClickListener(this);
        mVipinflatecount.setOnClickListener(this);
        mFastconsume.setOnClickListener(this);
        mGoodsconsume.setOnClickListener(this);
        mcConsumebycount.setOnClickListener(this);
        mIntegralexchange.setOnClickListener(this);
        mIntegraldetail.setOnClickListener(this);
        mMoneydetail.setOnClickListener(this);
        mVipdelay.setOnClickListener(this);
        mTitleback.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r_vip_rechange:
                mIntent = new Intent(HistoryRecoderActivity.this, VipRechangeMoney.class);
                mBundle.putSerializable("YSL_VIP_INFO", mVipInfo);
                mIntent.putExtra("bundle", mBundle);
                startActivity(mIntent);
                break;
            case R.id.r_vip_inflatecount:
                break;
            case R.id.r_fast_consume:
                break;
            case R.id.r_goods_consume:
                break;
            case R.id.r_consume_bycount:
                break;
            case R.id.r_integral_exchange:
                break;
            case R.id.r_integral_detail:
                break;
            case R.id.r_money_detail:
                break;
            case R.id.r_vip_delay:
                break;
            case R.id.tv_title_back:
                HistoryRecoderActivity.this.finish();
                break;


        }
    }
}
