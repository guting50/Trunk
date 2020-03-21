package com.zhiluo.android.yunpu.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.fragment.MessageFragment;
import com.zhiluo.android.yunpu.ui.fragment.ShopFragment;

/**
 * Created by ${YSL} on 2018-04-09.
 */

public class YSLMessagesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_messages);
        MessageFragment mShopFragment = new MessageFragment();
        startFragment(mShopFragment);
        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YSLMessagesActivity.this.finish();
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
