package com.zhiluo.android.yunpu.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.activity.ProviderManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.GradeManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.PayParamsSetActivity;
import com.zhiluo.android.yunpu.ui.activity.PrintSetActivity;
import com.zhiluo.android.yunpu.ui.activity.SystemParamsSetActivity;
import com.zhiluo.android.yunpu.ui.activity.label.LabelManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.recharge.RechargeManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.staff.StaffManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.tc.TCManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.timesrule.TimesRuleManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.user.UserManagerActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

/**
 * Created by free on 2018/4/8.
 */

public class YSLSystemSetActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mPrintSet, mParamsSet, mSupplierManager, mLeveManager, mRechangeManager,
            mPackageManager, mStaffManager, mUserManager, mLabManager, mPwdManager,mrulesManager,mpayManager;
    private Intent mIntent;
    private TextView mBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_systemset);
        initViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r_print_set://打印设置
                if (YSLUtils.isOpenActivon("打印设置")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, PrintSetActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.r_params_set://参数设置
                if (YSLUtils.isOpenActivon("参数设置")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, SystemParamsSetActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.r_supplier_manager://供应商管理
                if (YSLUtils.isOpenActivon("供应商管理")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, ProviderManagerActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.r_level_manager://等级管理
                if (YSLUtils.isOpenActivon("等级管理")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, GradeManagerActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.r_rechange_manager://充值管理
                if (YSLUtils.isOpenActivon("充值管理")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, RechargeManagerActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.r_package_manager://套餐管理
                if (YSLUtils.isOpenActivon("套餐管理")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, TCManagerActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.r_times_manager://计次规则管理
                if (YSLUtils.isOpenActivon("计次规则")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, TimesRuleManagerActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.r_staff_manager://员工管理
                if (YSLUtils.isOpenActivon("员工管理")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, StaffManagerActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.r_user_manager://用户管理
                if (YSLUtils.isOpenActivon("用户管理")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, UserManagerActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.r_lab_manager://标签管理
                if (YSLUtils.isOpenActivon("标签管理")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, LabelManagerActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_back:
                YSLSystemSetActivity.this.finish();
                break;
            case R.id.r_pwd_manager://密码管理
                if (YSLUtils.isOpenActivon("密码管理")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, SystemSettingActivity.class);
                    startActivity(mIntent);
                }else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.r_pay_manager://支付设置
                if (YSLUtils.isOpenActivon("支付设置")) {
                    mIntent = null;
                    mIntent = new Intent(YSLSystemSetActivity.this, PayParamsSetActivity.class);
                    startActivity(mIntent);
                }else {
                    CustomToast.makeText(YSLSystemSetActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initViews() {
        mBack = findViewById(R.id.tv_back);
        mPrintSet = findViewById(R.id.r_print_set);
        mParamsSet = findViewById(R.id.r_params_set);
        mSupplierManager = findViewById(R.id.r_supplier_manager);
        mLeveManager = findViewById(R.id.r_level_manager);
        mRechangeManager = findViewById(R.id.r_rechange_manager);
        mPackageManager = findViewById(R.id.r_package_manager);
        mStaffManager = findViewById(R.id.r_staff_manager);
        mUserManager = findViewById(R.id.r_user_manager);
        mLabManager = findViewById(R.id.r_lab_manager);
        mPwdManager = findViewById(R.id.r_pwd_manager);
        mrulesManager = findViewById(R.id.r_times_manager);
        mpayManager = findViewById(R.id.r_pay_manager);
        mPrintSet.setOnClickListener(this);
        mParamsSet.setOnClickListener(this);
        mSupplierManager.setOnClickListener(this);
        mLeveManager.setOnClickListener(this);
        mRechangeManager.setOnClickListener(this);
        mPackageManager.setOnClickListener(this);
        mStaffManager.setOnClickListener(this);
        mUserManager.setOnClickListener(this);
        mLabManager.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mPwdManager.setOnClickListener(this);
        mrulesManager.setOnClickListener(this);
        mpayManager.setOnClickListener(this);
    }


}
