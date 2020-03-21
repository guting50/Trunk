package com.zhiluo.android.yunpu.login.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;


/**
 * Created by ZPH on 2019-09-25.
 */

public class UserAgreementActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getBooleanExtra("yunpu",true)){
            setContentView(R.layout.activity_user_agreement);
        }else {
            setContentView(R.layout.activity_user_agree_member);
        }

        findViewById(R.id.tv_member_recharge_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
