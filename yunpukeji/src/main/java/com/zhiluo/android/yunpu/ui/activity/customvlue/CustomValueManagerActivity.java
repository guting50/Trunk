package com.zhiluo.android.yunpu.ui.activity.customvlue;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.adapter.CustomValuePagerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 自定义属性列表
 */
public class CustomValueManagerActivity extends BaseActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tab_custom_label_type)
    TabLayout mTabLayout;
    @Bind(R.id.vp_custom_value)
    ViewPager mViewPager;
    private CustomValuePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_value_manager);
        ButterKnife.bind(this);
        mPagerAdapter = new CustomValuePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
