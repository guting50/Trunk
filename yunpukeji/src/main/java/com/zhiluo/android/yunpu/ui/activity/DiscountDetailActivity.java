package com.zhiluo.android.yunpu.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.ui.adapter.GradeDiscountDetailAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DiscountDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.rv_discount_detail)
    RecyclerView rvDiscountDetail;
    @Bind(R.id.et_fast_shop)
    EditText etFastShop;
    private DataBean mGrade;
    private GradeDiscountDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_detail);
        ButterKnife.bind(this);
        mGrade = (DataBean) getIntent().getSerializableExtra("GD");
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (mGrade != null) {
            GridLayoutManager manager = new GridLayoutManager(this, 2);
            rvDiscountDetail.setLayoutManager(manager);
            mAdapter = new GradeDiscountDetailAdapter(mGrade);
            rvDiscountDetail.setAdapter(mAdapter);
            etFastShop.setText((int) mGrade.getDS_Value()+"%");
        }

    }
}
