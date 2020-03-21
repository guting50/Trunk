package com.zhiluo.android.yunpu.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.adapter.StorePagerAdapter;
import com.zhiluo.android.yunpu.ui.fragment.ShopFragment;

import butterknife.Bind;

/**
 * Created by ${YSL} on 2018-04-08.
 */

public class ShopMallActivity extends AppCompatActivity {

    private ShopFragment mShopFragment;
    private TextView mBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_shopmall);
        mShopFragment = new ShopFragment();
        startFragment(mShopFragment);
        initViews();
    }

    private void initViews() {
        mBack = findViewById(R.id.tv_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShopMallActivity.this.finish();
            }
        });
    }

    private void startFragment(Fragment fm) {
        if (!fm.isAdded()) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_homepage, fm);
            transaction.commit();
        }
    }
}
