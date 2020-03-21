package com.zhiluo.android.yunpu.login.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.takwolf.android.lock9.Lock9View;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.R;

import java.util.HashMap;

/**
 * 设置手势图案解锁密码
 * author Yuan Cheng
 * blog yuancheng91.top
 * created 2017/4/1 15:52
 */
public class SettingGesturePatternPasswordActivity extends BaseActivity {
    private TextView mPlMessageText;
    private Lock9View mLock9View;
    private RelativeLayout mRlButtonContainer;
    private TextView mTvCancelButton;
    private TextView mTvRightButton;
    private Lock9View mLock9ViewContraction;
    private LinearLayout ll_contraction_1;
    private LinearLayout ll_contraction_2;
    private LinearLayout ll_contraction_3;
    private ImageView iv_11;
    private ImageView iv_12;
    private ImageView iv_13;
    private ImageView iv_21;
    private ImageView iv_22;
    private ImageView iv_23;
    private ImageView iv_31;
    private ImageView iv_32;
    private ImageView iv_33;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;

    private HashMap<String, ImageView> mHashMap = new HashMap<>();

    String patternSha1 = "";


    private void assignViews() {
        mPlMessageText = (TextView) findViewById(R.id.tv_message_text);
        mLock9View = (Lock9View) findViewById(R.id.lock_9_view_setting);
        mRlButtonContainer = (RelativeLayout) findViewById(R.id.rl_button_container);
        mTvCancelButton = (TextView) findViewById(R.id.tv_cancel_button);
        mTvRightButton = (TextView) findViewById(R.id.tv_right_button);

        ll_contraction_1 = (LinearLayout) findViewById(R.id.ll_contraction_1);
        ll_contraction_2 = (LinearLayout) findViewById(R.id.ll_contraction_2);
        ll_contraction_3 = (LinearLayout) findViewById(R.id.ll_contraction_3);

        iv_11 = (ImageView) findViewById(R.id.iv_11);
        iv_12 = (ImageView) findViewById(R.id.iv_12);
        iv_13 = (ImageView) findViewById(R.id.iv_13);

        iv_21 = (ImageView) findViewById(R.id.iv_21);
        iv_22 = (ImageView) findViewById(R.id.iv_22);
        iv_23 = (ImageView) findViewById(R.id.iv_23);

        iv_31 = (ImageView) findViewById(R.id.iv_31);
        iv_32 = (ImageView) findViewById(R.id.iv_32);
        iv_33 = (ImageView) findViewById(R.id.iv_33);

        mHashMap.put("1", iv_11);
        mHashMap.put("2", iv_12);
        mHashMap.put("3", iv_13);
        mHashMap.put("4", iv_21);
        mHashMap.put("5", iv_22);
        mHashMap.put("6", iv_23);
        mHashMap.put("7", iv_31);
        mHashMap.put("8", iv_32);
        mHashMap.put("9", iv_33);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gesturepattern_setting);

        assignViews();

        mSharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        editor = mSharedPreferences.edit();

        mLock9View.setCallBack(new Lock9View.CallBack() {

            @Override
            public void onFinish(String password) {
                if (password.length() < 4) {
                    mPlMessageText.setText("请至少连接4个点");
                    mPlMessageText.setTextColor(Color.RED);
                    return;

                }

                if (patternSha1.equals("")) {
                    patternSha1 = password;

                    for (int i = 0; i < patternSha1.length(); i++) {
                        mHashMap.get(patternSha1.charAt(i) + "").setImageDrawable(getResources().getDrawable(R.drawable.shape_gesturepatter_change_circle_contraction));
                    }
                    mPlMessageText.setText("图案已保存↓，请再次绘制确认");
                    mPlMessageText.setTextColor(Color.BLACK);
                } else if (!patternSha1.equals("") | patternSha1 != null) {
                    //判断两次绘制的密码图案是否一致
                    if (patternSha1.equals(password)) {
                        editor.putString("sha1", patternSha1);   //存入登陆用户对应的解锁图案密码的SHA1
                        editor.putString("password", MyApplication.password);
                        editor.commit();

                        Intent result = new Intent();

                        if (getIntent().getStringExtra("is_ONOFF") != null && getIntent().getStringExtra("is_ONOFF").equals("on")) {
                            editor.putString("isHasPattern", "y");
                            editor.putBoolean("exit_login",false);
                            editor.putString("isForgotPassword", "n");
                            editor.putString("noIgnoreAsked", "n");
                            editor.commit();
                            result.putExtra("success", "on");
                            setResult(RESULT_OK, result);
                        }

                        Intent intent = new Intent(SettingGesturePatternPasswordActivity.this, HomeActivity.class);
                        intent.putExtra("shopname", getIntent().getStringExtra("shopname"));
                        intent.putExtra("creator", getIntent().getStringExtra("creator"));
                        intent.putExtra("shopGid", getIntent().getStringExtra("shopGid"));
                        startActivity(intent);
                        finish();

                        CustomToast.makeText(SettingGesturePatternPasswordActivity.this, "设置成功！", Toast.LENGTH_SHORT).show();
                    } else {
                        mPlMessageText.setText("两次绘制的图案不一致");
                        mPlMessageText.setTextColor(Color.RED);
                    }
                }
            }

        });


        mTvCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent result = new Intent();
                result.putExtra("success", "off");
                setResult(RESULT_OK, result);
//                editor.putString("isHasPattern", "n");
                editor.putString("isForgotPassword", "n");
                editor.putString("noIgnoreAsked", "n");
                editor.commit();
                finish();
            }
        });


    }


    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra("success", "off");
        setResult(RESULT_OK, result);
//        editor.putString("isHasPattern", "n");
        editor.putString("isForgotPassword", "n");
        editor.putString("noIgnoreAsked", "n");
        editor.commit();
        super.onBackPressed();
    }
}
