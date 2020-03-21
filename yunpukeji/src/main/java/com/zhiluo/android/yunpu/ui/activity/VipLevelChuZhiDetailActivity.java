package com.zhiluo.android.yunpu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;

/**
 * Created by ${YSL} on 2018-04-10.
 */

public class VipLevelChuZhiDetailActivity extends AppCompatActivity{
    private TextView mContent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viplevelchuzhidetail);
        initViews();
        activon();
    }

    private void initViews() {
        mContent=findViewById(R.id.tv_content);
        mContent.setText(getIntent().getStringExtra("chuzhicontent"));
    }

    private void activon() {
        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VipLevelChuZhiDetailActivity.this.finish();
            }
        });
    }
}
