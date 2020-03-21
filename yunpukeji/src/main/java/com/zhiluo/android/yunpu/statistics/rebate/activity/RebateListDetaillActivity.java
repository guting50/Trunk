package com.zhiluo.android.yunpu.statistics.rebate.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.rebate.bean.RebateListReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;

public class RebateListDetaillActivity extends BaseActivity {
    private TextView mTvTitle, mTvMemberCard, mTvMemberName, mTvGrade, mTvPhone, mTvRecommendName, mTvRecommendCard,
            mTvCreateTime, mTvBalance, mTvPoint;
    private RebateListReportBean.DataBean.DataListBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rebate_list_detial);
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("推荐列表详情");
        mTvMemberCard = (TextView) findViewById(R.id.tv_rebate_list_card);
        mTvMemberName = (TextView) findViewById(R.id.tv_rebate_list_name);
        mTvGrade = (TextView) findViewById(R.id.tv_rebate_detail_grade);
        mTvPhone = (TextView) findViewById(R.id.tv_rebate_detail_phone);
        mTvRecommendName = (TextView) findViewById(R.id.tv_rebate_list_recommend_name);
        mTvRecommendCard = (TextView) findViewById(R.id.tv_rebate_list_recommend_card);
        mTvCreateTime = (TextView) findViewById(R.id.tv_rebate_list_time);
        mTvBalance = (TextView) findViewById(R.id.tv_rebate_list_balance);
        mTvPoint = (TextView) findViewById(R.id.tv_rebate_list_point);

        initDate();
        setListener();
    }

    private void initDate() {
        mBean = (RebateListReportBean.DataBean.DataListBean) getIntent().getSerializableExtra("rebate");
        mTvMemberCard.setText(mBean.getVCH_Card() == null ? "" : mBean.getVCH_Card());
        mTvMemberName.setText(mBean.getVIP_Name() == null ? "" : mBean.getVIP_Name());
        mTvGrade.setText(mBean.getVG_Name() == null ? "" : mBean.getVG_Name());
        mTvPhone.setText(mBean.getVIP_CellPhone() == null ? "" : mBean.getVIP_CellPhone());
        mTvRecommendName.setText(mBean.getVIP_RecommendName() == null ? "" : mBean.getVIP_RecommendName());
        mTvRecommendCard.setText(mBean.getVIP_Referee() == null ? "" : mBean.getVIP_Referee());
        mTvCreateTime.setText(mBean.getVCH_CreateTime() == null ? "" : mBean.getVCH_CreateTime());
        mTvBalance.setText(mBean.getMA_AvailableBalance() + "");
        mTvPoint.setText(mBean.getMA_AvailableIntegral() + "");
    }

    private void setListener() {
        findViewById(R.id.tv_no_confirm_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RebateListDetaillActivity.this.finish();
            }
        });
    }
}
