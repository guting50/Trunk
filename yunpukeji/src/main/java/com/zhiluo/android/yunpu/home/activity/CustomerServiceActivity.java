package com.zhiluo.android.yunpu.home.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhiluo.android.yunpu.entity.ServiceEntity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.utils.CommonFun;

/**
 * 我的客服
 */
public class CustomerServiceActivity extends BaseActivity {
    private RelativeLayout rlQQ, rlWeChat, rlMobile, rlPhone;
    private TextView tvQQ, tvWeChat, tvMobile, tvPhone;
    private ServiceEntity mServiceEntity;
    private String mQQ, mWeChat, mMobil;
    private static final String mPhone = "4006302577";//服务电话
    private String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + mQQ + "&version=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_service);
        intview();
        clicklisener();
        getService();
    }

    private void clicklisener() {
        rlPhone.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mPhone));//跳转到拨号界面，同时传递电话号码
                try {
                    startActivity(dialIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    CustomToast.makeText(CustomerServiceActivity.this, "该设备不支持", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        rlMobile.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (!mMobil.equals("未设置")) {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mMobil));//跳转到拨号界面，同时传递电话号码
                    try {
                        startActivity(dialIntent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        CustomToast.makeText(CustomerServiceActivity.this, "该设备不支持", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        });
        rlQQ.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (!checkApkExist()) {
                    CustomToast.makeText(CustomerServiceActivity.this, "未找到qq程序", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!mQQ.equals("未设置")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
                }
            }
        });
    }


    public boolean checkApkExist() {
        try {
            @SuppressLint("WrongConstant") ApplicationInfo info = this.getPackageManager().getApplicationInfo("com.tencent.mobileqq",
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void intview() {
        rlQQ = (RelativeLayout) findViewById(R.id.rl_service_qq);
        rlWeChat = (RelativeLayout) findViewById(R.id.rl_service_we_chat);
        rlMobile = (RelativeLayout) findViewById(R.id.rl_service_mobile);
        rlPhone = (RelativeLayout) findViewById(R.id.rl_service_phone);
        tvQQ = (TextView) findViewById(R.id.tv_service_qq);
        tvWeChat = (TextView) findViewById(R.id.tv_service_we_chat);
        tvMobile = (TextView) findViewById(R.id.tv_service_mobile);
        tvPhone = (TextView) findViewById(R.id.tv_service_phone);
        //返回
        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getService() {
        HttpHelper.post(CustomerServiceActivity.this, "CustomerService/GetCustomerServiceInfo", new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    mServiceEntity = CommonFun.JsonToObj(responseString, ServiceEntity.class);
                    if (mServiceEntity.isSuccess()) {
                        mQQ = mServiceEntity.getData().getSU_QQCode();
                        mWeChat = mServiceEntity.getData().getSU_WeChat();
                        mMobil = mServiceEntity.getData().getSU_Telephone();
                        qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + mQQ + "&version=1";
                    }
                } catch (Exception e) {
                    mQQ = "未设置";
                    mWeChat = "未设置";
                    mMobil = "4006302577";
                }
                tvQQ.setText(mQQ);
                tvWeChat.setText(mWeChat);
                tvMobile.setText(mMobil);
                tvPhone.setText(mPhone);
            }

            @Override
            public void onFailure(String msg) {
                mQQ = "未设置";
                mWeChat = "未设置";
                mMobil = "4006302577";
                tvQQ.setText(mQQ);
                tvWeChat.setText(mWeChat);
                tvMobile.setText(mMobil);
                tvPhone.setText(mPhone);
            }
        });
    }
}
