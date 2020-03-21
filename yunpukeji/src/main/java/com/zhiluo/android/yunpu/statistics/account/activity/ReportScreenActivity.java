package com.zhiluo.android.yunpu.statistics.account.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;

/**
 * 报表筛选条件
 * 作者：罗咏哲 on 2017/9/1 14:20.
 * 邮箱：137615198@qq.com
 */
public class ReportScreenActivity extends BaseActivity {
    private ImageView mIvScan;
    private EditText mEtByMember, mEtByOrder;
    private RadioButton mRbPayAll, mRbPayCash, mRbPayYue, mRbPayBank, mRbPayIntargel;
    private RadioButton mDeviceAll, mDeviceWeb, mDeviceApp;
    private RadioButton mCustomTypeAll, mCustomTypeVip, mCustomTypeTourist;
    private RadioGroup mRgPayWay1, mRgPayWay2, mRgDevice, mRgCustomType;

    private int mDevice;//设备
    private String mPayWayCode;//支付类型
    private String mCustomWayCode;//消费类型


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_screen);
        initView();
        initVariable();
        setListener();
    }

    private void initView() {
        mIvScan = (ImageView) findViewById(R.id.iv_report_screen_scan);
        mEtByMember = (EditText) findViewById(R.id.et_report_screen_search);
        mEtByOrder = (EditText) findViewById(R.id.et_search_by_order);
        mRbPayAll = (RadioButton) findViewById(R.id.cb_report_screen_1);
        mRbPayCash = (RadioButton) findViewById(R.id.cb_report_screen_2);
        mRbPayYue = (RadioButton) findViewById(R.id.cb_report_screen_3);
        mRbPayBank = (RadioButton) findViewById(R.id.cb_report_screen_4);
        mRbPayIntargel = (RadioButton) findViewById(R.id.cb_report_screen_5);
        mDeviceAll = (RadioButton) findViewById(R.id.cb_report_screen_6);
        mDeviceWeb = (RadioButton) findViewById(R.id.cb_report_screen_7);
        mDeviceApp = (RadioButton) findViewById(R.id.cb_report_screen_8);
        mCustomTypeAll = (RadioButton) findViewById(R.id.cb_report_screen_9);
        mCustomTypeVip = (RadioButton) findViewById(R.id.cb_report_screen_10);
        mCustomTypeTourist = (RadioButton) findViewById(R.id.cb_report_screen_11);
        mRgPayWay1 = (RadioGroup) findViewById(R.id.rg_pay_way_first);
        mRgPayWay2 = (RadioGroup) findViewById(R.id.rg_pay_way_second);
        mRgDevice = (RadioGroup) findViewById(R.id.rg_device_type);
        mRgCustomType = (RadioGroup) findViewById(R.id.rg_custom_type);
    }

    private void initVariable() {

    }

    private void setListener() {
        //返回
        findViewById(R.id.tv_report_screen_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReportScreenActivity.this.finish();
            }
        });
        //清空筛选条件
        findViewById(R.id.btn_report_screen_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtByMember.setText("");
                mEtByOrder.setText("");
                mRgPayWay1.check(mRbPayAll.getId());
                mRgDevice.check(mDeviceAll.getId());
                mRgCustomType.check(mCustomTypeAll.getId());
                mRgPayWay2.clearCheck();
            }
        });
        //确定
        findViewById(R.id.tv_report_screen_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("VIP_NO", mEtByMember.getText().toString());
                intent.putExtra("ORDER_NO", mEtByOrder.getText().toString());
                intent.putExtra("Pay_Way_code", mPayWayCode);
                intent.putExtra("Device", mDevice);
                intent.putExtra("Custom", mCustomWayCode);
                setResult(666, intent);
                finish();
            }
        });
        mRgPayWay1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbPayAll.getId()) {
                    mPayWayCode = "";
                }
                if (i == mRbPayCash.getId()) {
                    mPayWayCode = "XJZF";
                }
                if (i == mRbPayYue.getId()) {
                    mPayWayCode = "YEZF";
                }
            }
        });
        mRgPayWay2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbPayBank.getId()) {
                    mPayWayCode = "YLZF";
                }
                if (i == mRbPayIntargel.getId()) {
                    mPayWayCode = "JFZF";
                }
            }
        });
        mRgDevice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mDeviceAll.getId()) {
                    mDevice = -1;
                }
                if (i == mDeviceWeb.getId()) {
                    mDevice = 1;
                }
                if (i == mDeviceApp.getId()) {
                    mDevice = 2;
                }
            }
        });
    }
}
