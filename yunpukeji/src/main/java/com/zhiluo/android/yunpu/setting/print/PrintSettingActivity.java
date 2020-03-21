package com.zhiluo.android.yunpu.setting.print;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.R.id.btn_bingo_ps;
import static com.zhiluo.android.yunpu.R.id.et_recepit_num_ps;

/**
 * 打印设置界面
 */
public class PrintSettingActivity extends BaseActivity {
    private LinearLayout mActivityPrintSetting;
    private TextView mIvBackActivity;
    private EditText mEtRecepitNumPs;
    private Button mBtn_bingo_ps;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_setting);
        assignViews();
        initEvents();
        pref = this.getSharedPreferences("recepits", Context.MODE_PRIVATE);
        if (pref.getInt("recepits_num", 2) > 0) {
            mEtRecepitNumPs.setText(pref.getInt("recepits_num", 2) + "");
        }
    }


    private void assignViews() {
        mActivityPrintSetting = (LinearLayout) findViewById(R.id.activity_print_setting);
        mIvBackActivity = (TextView) findViewById(R.id.tv_back_activity);
        mEtRecepitNumPs = (EditText) findViewById(et_recepit_num_ps);
        mBtn_bingo_ps = (Button) findViewById(btn_bingo_ps);
    }

    private void initEvents() {
        //返回
        mIvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBtn_bingo_ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mEtRecepitNumPs.getText().toString().equals("")) {


                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PrintSettingActivity.this, SweetAlertDialog.WARNING_TYPE);
                    sweetAlertDialog.setTitleText("提示");
                    sweetAlertDialog.setContentText("小票打印份数不能为空！");
                    sweetAlertDialog.setConfirmText("确认");
                    sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            return;
                        }
                    });
                    sweetAlertDialog.show();
//                    CustomToast.makeText(PrintSettingActivity.this, "小票打印份数不能为空！", Toast.LENGTH_SHORT).show();
//                    return;
                } else {

                    SharedPreferences.Editor editor = getSharedPreferences("recepits", MODE_PRIVATE).edit();
                    editor.putInt("recepits_num", Integer.parseInt(mEtRecepitNumPs.getText().toString()));
                    editor.commit();
//                    CustomToast.makeText(PrintSettingActivity.this, "设置成功！", Toast.LENGTH_SHORT).show();
                    //                    finish();
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PrintSettingActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("设置成功！");
                    sweetAlertDialog.setConfirmText("确认");
                    sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            finish();
                        }
                    });
                    sweetAlertDialog.show();


                }
            }
        });
    }
}
