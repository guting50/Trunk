package com.zhiluo.android.yunpu.statistics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.statistics.account.activity.MemberAccountActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.rebate.activity.RebateActivity;
import com.zhiluo.android.yunpu.statistics.staff.activity.StaffReportActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;

/**
 * 报表
 * 作者：罗咏哲 on 2017/9/1 14:14.
 * 邮箱：137615198@qq.com
 */
public class ReportFromActivity extends BaseActivity {
    private TextView mTvTitle;//标题
    private TextView mTvBack;//返回
    private RelativeLayout mRlAccount;
    private RelativeLayout mRlOrder;
    private RelativeLayout mRlStaff;
    private RelativeLayout mRlRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_from);
        initView();
        initVariable();
        laodData();
        setListener();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("报表管理");
        mTvBack = (TextView) findViewById(R.id.tv_no_confirm_back);
        mRlAccount = (RelativeLayout) findViewById(R.id.rl_report_from_member);
        mRlOrder = (RelativeLayout) findViewById(R.id.rl_report_from_order);
        mRlStaff = (RelativeLayout) findViewById(R.id.rl_report_from_ticheng);
        mRlRecommend = (RelativeLayout) findViewById(R.id.rl_report_from_recommend);
    }

    private void initVariable() {

    }

    private void laodData() {

    }

    private void setListener() {
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportFromActivity.this.finish();
            }
        });
        mRlAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportFromActivity.this, MemberAccountActivity.class);
                startActivity(intent);
            }
        });
        mRlOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportFromActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
        mRlStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportFromActivity.this, StaffReportActivity.class);
                startActivity(intent);
            }
        });
        mRlRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReportFromActivity.this, RebateActivity.class);
                startActivity(intent);
            }
        });
    }
}
