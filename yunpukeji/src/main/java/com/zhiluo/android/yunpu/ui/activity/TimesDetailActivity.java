package com.zhiluo.android.yunpu.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.ui.adapter.GradeTimesDetailAdapter;
import com.zhiluo.android.yunpu.ui.view.BaseListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2019-01-15.
 */

public class TimesDetailActivity extends BaseActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_title_times)
    TextView tvTitleTimes;
    @Bind(R.id.r_layout)
    RelativeLayout rLayout;
    @Bind(R.id.recycler_view)
    BaseListView recyclerView;
    @Bind(R.id.tv_null)
    TextView tvNull;

    private DataBean mGrade;
    private GradeTimesDetailAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grade_times_detail);
        ButterKnife.bind(this);
        initData();
        initLisenter();
    }

    private void initLisenter() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        mGrade = (DataBean) getIntent().getSerializableExtra("GD");
        if (mGrade != null && mGrade.getInitServiceList().size() > 0) {
            adapter = new GradeTimesDetailAdapter(this, mGrade);
            recyclerView.setAdapter(adapter);
            tvNull.setVisibility(View.GONE);
        } else {
            tvNull.setVisibility(View.VISIBLE);
        }

    }
}
