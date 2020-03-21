package com.zhiluo.android.yunpu.statistics.staff.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.staff.bean.StaffRebateReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;

public class StaffRebateDetailActivity extends BaseActivity {
    private TextView mTvTitle,mTvStaffName,mTvOrder,mTvMoney,mTvCreateTime,mTvType;
    private StaffRebateReportBean.DataBean.DataListBean mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_rebate_detail);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("员工提成详情");
        mTvStaffName= (TextView) findViewById(R.id.tv_rebate_detail_name);
        mTvOrder= (TextView) findViewById(R.id.tv_rebate_detail_order);
        mTvMoney= (TextView) findViewById(R.id.tv_rebate_detail_money);
        mTvCreateTime= (TextView) findViewById(R.id.tv_rebate_detail_create);
        mTvType= (TextView) findViewById(R.id.tv_rebate_detail_type);
    }

    private void initData() {
        mBean = (StaffRebateReportBean.DataBean.DataListBean) getIntent().getSerializableExtra("staff");
        mTvStaffName.setText(mBean.getEM_Name());
        mTvOrder.setText(mBean.getCO_GID());
        mTvMoney.setText(mBean.getSC_TipMoney()+"");
        mTvCreateTime.setText(mBean.getSC_UpdateTime());
        switch (mBean.getSC_Type()){
            case 10:
                mTvType.setText("售卡提成");
                break;
            case 20:
                mTvType.setText("充值提成");
                break;
            case 30:
                mTvType.setText("充次提成");
                break;
            case 40:
                mTvType.setText("快速消费提成");
                break;
            case 50:
                mTvType.setText("商品消费提成");
                break;
            case 60:
                mTvType.setText("计次消费提成");
                break;
        }
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
