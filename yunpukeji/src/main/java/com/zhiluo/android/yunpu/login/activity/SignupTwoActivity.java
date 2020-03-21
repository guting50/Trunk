package com.zhiluo.android.yunpu.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 注册界面2用于输入手机验证码
 */
public class SignupTwoActivity extends BaseActivity {

    private TextView textView;
    private Button nexttwo;
    private EditText editText;
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            textView.setText((millisUntilFinished / 1000) + "秒后可重发");

        }

        @Override
        public void onFinish() {
            textView.setText("获取验证码");
            textView.setEnabled(true);
        }
    };
    private String str;
    private String yamstr;
    private TextView imvback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_two);
        initView();
        initData();
        initListenin();
    }

    private void initListenin() {
        nexttwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().length() == 4) {
                    Intent intent = new Intent(SignupTwoActivity.this, SignupContentActivity.class);
                    intent.putExtra("phone", str);
                    intent.putExtra("yzm", yamstr);
                    intent.putExtra("sjyzm", editText.getText().toString());
                    startActivity(intent);
                } else {
                    new SweetAlertDialog(SignupTwoActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("您的输入有误!")
                            .setConfirmText("了解")
                            .show();
//                    CustomToast.makeText(SignupTwoActivity.this, "您的输入有误", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.edi_singtwonum_activity);
        textView = (TextView) findViewById(R.id.tet_singtime_activity);
        nexttwo = (Button) findViewById(R.id.btn_singuptwonext_activity);
        imvback = (TextView) findViewById(R.id.tv_back_signuptwoactivity);
        //记时
        timer.start();
    }

    private void initData() {
        Intent intent = getIntent();
        str = intent.getStringExtra("phone");
        yamstr = intent.getStringExtra("yzm");
    }
}
