package com.zhiluo.android.yunpu.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;

/**
 * 找回密码成功
 * 点击跳转到登陆界面
 */
public class NewpasswordEndActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword_end);
        initView();
    }

    private void initView() {
        Button button = (Button) findViewById(R.id.btn_newpasswordendlogin_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewpasswordEndActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
