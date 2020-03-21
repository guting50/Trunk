package com.zhiluo.android.yunpu.home.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.ServiceEntity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.activity.UserAgreementActivity;
import com.zhiluo.android.yunpu.ui.activity.PowerListActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-04-08.
 */

public class MineKeFuActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.r_aboutus)
    RelativeLayout rAboutus;
    @Bind(R.id.r_user_agree)
    RelativeLayout rUserAgree;
    private TextView mBack;
    private RelativeLayout mTel, mQQ, mWeiXin, r_power;
    private TextView tvTel, tvQQ, tvWeixin;
    private ServiceEntity mServiceEntity;
    private String StringTel, StringQQ, StringWeixin;
    private String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + mQQ + "&version=1";

    private SharedPreferences preferences;
    private boolean isyunpu = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_mykefu);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initlisenter();
    }

    private void initlisenter() {
        rAboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MineKeFuActivity.this, AboutActivity.class));
            }
        });

        rUserAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineKeFuActivity.this, UserAgreementActivity.class);
                intent.putExtra("yunpu", isyunpu);
                startActivity(intent);

            }
        });


    }

    private void initDatas() {
        getService();
    }

    private void setVis() {
        if (StringQQ != null) {
            mQQ.setVisibility(View.VISIBLE);
        }
        if (StringWeixin != null) {
            mWeiXin.setVisibility(View.VISIBLE);
        }
        if (StringTel != null) {
            mTel.setVisibility(View.VISIBLE);
        }
    }

    private void initViews() {
        mBack = findViewById(R.id.tv_back);
        mTel = findViewById(R.id.r_tel);
        mQQ = findViewById(R.id.r_qq);
        mWeiXin = findViewById(R.id.r_weixin);
        tvTel = findViewById(R.id.tv_Tel);
        tvQQ = findViewById(R.id.tv_QQ);
        tvWeixin = findViewById(R.id.tv_Weixin);
        r_power = findViewById(R.id.r_power);
        mQQ.setVisibility(View.GONE);
        mTel.setVisibility(View.GONE);
        mWeiXin.setVisibility(View.GONE);
        mBack.setOnClickListener(this);
        mTel.setOnClickListener(this);
        mQQ.setOnClickListener(this);
        mWeiXin.setOnClickListener(this);
        r_power.setOnClickListener(this);


        preferences = getSharedPreferences("setting", Context.MODE_PRIVATE);
        Li("平台版" + preferences.getString("yunpu", ""));
        if (!preferences.getString("yunpu", "").equals("PlatformUser")) {
            isyunpu = false;
            rAboutus.setVisibility(View.GONE);
//            mAboutUs.setVisibility(View.GONE);
//            viewAboutUs.setVisibility(View.GONE);
        }

    }

    private boolean checkApkExist() {
        try {
            @SuppressLint("WrongConstant") ApplicationInfo info = this.getPackageManager().getApplicationInfo("com.tencent.mobileqq",
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private void getService() {
        HttpHelper.post(MineKeFuActivity.this, "CustomerService/GetCustomerServiceInfo", new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    mServiceEntity = CommonFun.JsonToObj(responseString, ServiceEntity.class);
                    if (mServiceEntity.isSuccess()) {
                        StringQQ = mServiceEntity.getData().getSU_QQCode();
                        StringWeixin = mServiceEntity.getData().getSU_WeChat();
                        StringTel = mServiceEntity.getData().getSU_Telephone();
                        qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + StringQQ + "&version=1";
                    }
                } catch (Exception e) {

                    StringTel = "4006302577";
                }
                tvQQ.setText(StringQQ);
                tvWeixin.setText(StringWeixin);
                tvTel.setText(StringTel);
                setVis();
            }

            @Override
            public void onFailure(String msg) {
                StringTel = "4006302577";
                tvQQ.setText(StringQQ);
                tvWeixin.setText(StringWeixin);
                tvTel.setText(StringTel);
                setVis();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                MineKeFuActivity.this.finish();
                break;
            case R.id.r_tel:
                Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tvTel.getText().toString()));//跳转到拨号界面，同时传递电话号码
                try {
                    startActivity(dialIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    CustomToast.makeText(MineKeFuActivity.this, "该设备不支持", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case R.id.r_qq:
                if (!checkApkExist()) {
                    CustomToast.makeText(MineKeFuActivity.this, "未找到qq程序", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!mQQ.equals("未设置")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
                }
                break;
            case R.id.r_weixin:
                break;
            case R.id.r_power:
                startActivity(new Intent(this, PowerListActivity.class));
                break;
        }
    }
}
