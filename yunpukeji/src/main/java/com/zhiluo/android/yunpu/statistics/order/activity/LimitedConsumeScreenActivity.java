package com.zhiluo.android.yunpu.statistics.order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.ShopListBean;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;

import java.util.ArrayList;

/**
 * Created by ZPH on 2019-05-17.
 */

public class LimitedConsumeScreenActivity extends BaseActivity{

    private EditText mEtVipInfo,mEtOperation;
    private String mDeviceType;
    private Spinner mSpinner;
    private RadioButton mRbDeviceAll, mRbDeviceWeb, mRbDeviceApp,mRbDeviceIOS;
    private RadioGroup mRgDevice;
    private LoginUpbean mLoginBean;
    private ArrayAdapter<String> mSpinnerAdapter;
    private ArrayList<String> mStoreList;//店铺
    private String mStoreGid;//店铺GID
    private int pos;
    private ShopListBean mShopListBean;
    private final String mFileName = "XSXF_data";
    private SaveScreenStateUtil mStateUtil;
    private String vipCard;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limited_screen);
        mStateUtil = new SaveScreenStateUtil(this, mFileName);
        vipCard = getIntent().getStringExtra("vipCard");
        initView();
        initVariable();
        setListener();
    }

    private void initView() {
        mEtOperation = (EditText) findViewById(R.id.et_search_by_opreat);
        mEtVipInfo = (EditText) findViewById(R.id.et_report_screen_search);
        mSpinner = (Spinner) findViewById(R.id.sp_point_exchange_store);
        mRbDeviceAll = (RadioButton) findViewById(R.id.rb_goods_6);
        mRbDeviceWeb = (RadioButton) findViewById(R.id.rb_goods_7);
        mRbDeviceApp = (RadioButton) findViewById(R.id.rb_goods_8);
        mRbDeviceIOS = (RadioButton) findViewById(R.id.rb_goods_9);
        mRgDevice = (RadioGroup) findViewById(R.id.rg_device_type);

        layout = findViewById(R.id.l_yout);
        if (vipCard != null) {
            layout.setVisibility(View.GONE);
        }
    }

    private void initVariable() {
        String device = mStateUtil.get("Device", null);
        String storeGid = (String) mStateUtil.get("Store", null);
        String operation = mStateUtil.get("Operation", null);

        if (operation != null && !"".equals(operation)) {
            mEtOperation.setText(operation);
        }
        if (device != null && !device.equals("")) {
            switch (device) {
                case "1":
                    mRbDeviceWeb.setChecked(true);
                    mDeviceType = "1";
                    break;
                case "2":
                    mRbDeviceApp.setChecked(true);
                    mDeviceType = "2";
                    break;
                case "4":
                    mRbDeviceIOS.setChecked(true);
                    mDeviceType = "4";
                    break;
            }
        }
        mStoreList = new ArrayList<>();
        mStoreList.add("全部");
        mShopListBean = (ShopListBean) CacheData.restoreObject("shop");
        mLoginBean = (LoginUpbean) CacheData.restoreObject("LG");
        if (mShopListBean != null) {
            for (int i = 0; i < mShopListBean.getData().size(); i++) {
                mStoreList.add(mShopListBean.getData().get(i).getSM_Name());
                if (mLoginBean != null) {
                    if (mLoginBean.getData().getShopID().equals(mShopListBean.getData().get(i).getGID())) {
                        pos = i + 1;
                    }
                    if (storeGid != null && storeGid.equals(mShopListBean.getData().get(i).getGID())) {
                        pos = i + 1;
                    }
                }
            }
        }
        mSpinnerAdapter = new ArrayAdapter<String>(LimitedConsumeScreenActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, mStoreList);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setSelection(pos);
        if (mLoginBean.getData().getUM_IsAmin() == 1) {//判断是不是超管
            mSpinner.setEnabled(true);
        }else {
            mSpinner.setEnabled(false);
        }


    }

    private void setListener() {
        //返回
        findViewById(R.id.tv_report_screen_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //确定
        findViewById(R.id.tv_report_screen_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mStateUtil.put("Device", mDeviceType);
                mStateUtil.put("Store", mStoreGid == null ? "" : mStoreGid);
                mStateUtil.put("Operation", mEtOperation.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("device", mDeviceType);
                intent.putExtra("vip", mEtVipInfo.getText().toString());
                intent.putExtra("operation", mEtOperation.getText().toString());
                intent.putExtra("Store", mStoreGid);
                setResult(888, intent);
                finish();
            }
        });
        //清楚筛选条件
        findViewById(R.id.btn_report_screen_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.clean();
                mEtVipInfo.setText("");
                mEtOperation.setText("");
                mSpinner.setSelection(pos);
                mRgDevice.check(mRbDeviceAll.getId());

            }
        });

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mStoreGid = "";
                } else {
                    mStoreGid = mShopListBean.getData().get(position - 1).getGID();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mRgDevice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbDeviceAll.getId()) {
                    mDeviceType = "";
                }
                if (i == mRbDeviceWeb.getId()) {
                    mDeviceType = "1";
                }
                if (i == mRbDeviceApp.getId()) {
                    mDeviceType = "2";
                }
                if (i == mRbDeviceIOS.getId()) {
                    mDeviceType = "4";
                }
            }
        });

        if (mLoginBean != null) {
            if (mLoginBean.getData().getUM_IsAmin() != 1) {
                mStoreGid = mLoginBean.getData().getShopID();
                mSpinner.setClickable(false);
            }
        }
    }
}