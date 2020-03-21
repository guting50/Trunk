package com.zhiluo.android.yunpu.statistics.rebate.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.rebate.bean.RebateDetailReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;

/**
 * 作者：罗咏哲 on 2017/9/8 11:43.
 * 邮箱：137615198@qq.com
 */

public class RebateDetailActivity extends BaseActivity {
    private TextView mTvTitle;
    private RebateDetailReportBean.DataBean.DataListBean mBean;
    private TextView mCard,mName,mPoint,mType,mDiscap,mTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rebate_detail_layout);
        initView();
        initVariable();
        setListener();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("返利明细");
        mCard= (TextView) findViewById(R.id.tv_rebate_detail_card);
        mName=(TextView) findViewById(R.id.tv_rebate_detail_vip);
        mPoint=(TextView) findViewById(R.id.tv_rebate_detail_jifen);
        mType=(TextView) findViewById(R.id.tv_rebate_detail_type);
        mDiscap=(TextView) findViewById(R.id.tv_rebate_detail_depict);
        mTime=(TextView) findViewById(R.id.tv_rebate_detail_time);
    }

    private void initVariable() {
        mBean = (RebateDetailReportBean.DataBean.DataListBean) getIntent().getSerializableExtra("rebate");
        mCard.setText(mBean.getVCH_Card());
        if (mBean.getVIP_Name()!=null){
            mName.setText(mBean.getVIP_Name());
        }
        mPoint.setText(mBean.getNumber());
        mType.setText(mBean.getIdentifying());
        mDiscap.setText(mBean.getReamrk());
        mDiscap.setSelected(true);
        mTime.setText(mBean.getIntegralTime());
    }

    private void setListener() {
        findViewById(R.id.tv_no_confirm_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RebateDetailActivity.this.finish();
            }
        });
    }
}
