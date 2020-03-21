package com.zhiluo.android.yunpu.setting.gesture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rey.material.widget.Switch;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.login.activity.ConfirmGesturePatternPasswordActivity;
import com.zhiluo.android.yunpu.login.activity.SettingGesturePatternPasswordActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.R.id.rl_gesture_password_reset;

/**
 * 手势滑动图案解锁密码设置界面
 * author Yuan Cheng
 * blog yuancheng91.top
 * email jasoncheng9111@163.com
 * created 2017/3/18 15:18
 */
public class GesturePasswordSettingActivity extends BaseActivity {
    private RelativeLayout mRlOnOffGesturePassword;
    private RelativeLayout mRlGesturePasswordReset;
    private Switch mSwOnOffGesturePassword;
    private TextView mTvBackActivity;
    private TextView mTvOnOffGesturePassword;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    /**
     * ture:回调设置按钮的状态不做业务处理
     * false：人为更改了按钮状态需要进入业务流程
     */
    private boolean isTriggerSwitchChecked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_password_setting);

        sp = getSharedPreferences("login", 0);
        editor = sp.edit();

        assignViews();

        initViews();

        initListener();
    }


    private void assignViews() {
        mTvOnOffGesturePassword = (TextView) findViewById(R.id.tv_on_off_gesturepassword);
        mSwOnOffGesturePassword = (Switch) findViewById(R.id.sw_on_off_gesturepassword);
        mTvBackActivity = (TextView) findViewById(R.id.tv_back_activity);
        mRlOnOffGesturePassword = (RelativeLayout) findViewById(R.id.rl_on_off_gesture_password);
        mRlGesturePasswordReset = (RelativeLayout) findViewById(rl_gesture_password_reset);
    }

    private void initViews() {
        if (sp.getString("isHasPattern", "").equals("y")) {
            mSwOnOffGesturePassword.setChecked(true);
        } else {
            mSwOnOffGesturePassword.setChecked(false);
        }
    }

    private void initListener() {
        /**
         * 返回上一个activity
         */
        mTvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /**
         * 是否开启图案解锁密码
         */
        mSwOnOffGesturePassword.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(Switch view, boolean checked) {

                if (isTriggerSwitchChecked) {
                    return;
                }

                //启用图形解锁
                if (checked) {
                    Intent intent = new Intent(GesturePasswordSettingActivity.this, SettingGesturePatternPasswordActivity.class);
                    intent.putExtra("is_ONOFF", "on");
                    startActivityForResult(intent, 1);


                }
                //关闭图形解锁
                else {
                    Intent intent = new Intent(GesturePasswordSettingActivity.this, ConfirmGesturePatternPasswordActivity.class);
                    intent.putExtra("is_ONOFF", "off");
                    startActivityForResult(intent, 1);
                }
            }
        });

        /**
         * 重置手势密码
         */
        mRlGesturePasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //已经设置过手势密码后才能重置手势密码
                if (sp.getString("isHasPattern", "").equals("y")) {
                    //先输入登录密码
                    show_Dialog_ResetGesturePassword();
                } else {
                    new SweetAlertDialog(GesturePasswordSettingActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("请先启用并设置手势密码！")
                            .setConfirmText("了解")
                            .show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            isTriggerSwitchChecked = true;

            if (data.getStringExtra("success").equals("on")) {
                mTvOnOffGesturePassword.setText("关闭手势密码");
                mSwOnOffGesturePassword.setChecked(true);
                isTriggerSwitchChecked = false;
            } else {
                mTvOnOffGesturePassword.setText("开启手势密码");
                mSwOnOffGesturePassword.setChecked(false);
                isTriggerSwitchChecked = false;
            }
        }
    }

    /**
     * 重置手势密码前弹出的对话框
     */
    private void show_Dialog_ResetGesturePassword() {
        LayoutInflater layoutInflater = getLayoutInflater();
        View dialog = layoutInflater.inflate(R.layout.dialog_reset_gesturepassword, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();

        final EditText et_reset_gesturepassword = (EditText) dialog.findViewById(R.id.et_reset_gesturepassword);
        Button btn_confirm = (Button) dialog.findViewById(R.id.btn_confirm);
        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_reset_gesturepassword.getText() != null) {
                    if (MyApplication.password.equals(et_reset_gesturepassword.getText().toString())) {
//                        editor.putString("password", "");
//                        editor.putString("username", "");
//                        editor.putString("isHasPattern", "");
//                        editor.putString("isForgotPassword", "");
//                        editor.commit();
//                        startActivity(new Intent(GesturePasswordSettingActivity.this, LoginActivity.class));
                        Intent intent = new Intent(GesturePasswordSettingActivity.this, SettingGesturePatternPasswordActivity.class);
                        intent.putExtra("is_ONOFF", "on");
                        startActivityForResult(intent, 1);
                    } else {
                        new SweetAlertDialog(GesturePasswordSettingActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("提示")
                                .setContentText("密码输入有误,请重新输入！")
                                .setConfirmText("了解")
                                .show();
                    }
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        TextView title = new TextView(this);
        title.setText("重置手势密码");
        title.setPadding(10, 10, 10, 10);//边距			        
        title.setGravity(Gravity.CENTER);//位置	 
        title.setTextSize(23);
        title.setTextColor(getResources().getColor(R.color.black));  //字体的颜色
        alertDialog.setCustomTitle(title);//设置字体

//        alertDialog.setTitle("重置手势密码");
        alertDialog.setView(dialog);
        alertDialog.show();
    }
}
