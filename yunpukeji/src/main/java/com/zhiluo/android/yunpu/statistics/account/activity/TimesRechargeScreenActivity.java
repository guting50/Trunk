package com.zhiluo.android.yunpu.statistics.account.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.ShopListBean;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.statistics.order.activity.GoodsOrderScreenActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;

import java.util.ArrayList;

public class TimesRechargeScreenActivity extends BaseActivity {
    private ImageView mIvScan;
    private EditText mEtByMember, mEtByOrder, mEtByName, mEtXiaoYu, mEtDaYu;
    private RadioButton mRbPayAll, mRbCash, mRbYuE, mRbCard, mRbPoint, mRbDeviceAll, mRbDeviceWeb, mRbDeviceApp,mRbDeviceIOS,mRbzfb,mRbwx;
    private RadioGroup mRg1, mRg2,mRg3, mRgDevice;
    private String mPayWayCode, mDeviceType;
    private Spinner mSpinner;
    private LoginUpbean mLoginBean;
    private ShopListBean mShopListBean;
    private ArrayAdapter<String> mSpinnerAdapter;
    private ArrayList<String> mStoreList;//店铺
    private String mStoreGid;//店铺GID
    private int pos;
    private final String mFileName = "CC_data";
    private SaveScreenStateUtil mStateUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_recharge_screen);
        mStateUtil = new SaveScreenStateUtil(this, mFileName);
        initView();
        initVariable();
        setListener();
    }

    private void initView() {
        mIvScan = (ImageView) findViewById(R.id.iv_report_screen_scan);
        mEtByMember = (EditText) findViewById(R.id.et_report_screen_search);
        mEtByName = (EditText) findViewById(R.id.et_search_by_name);
        mEtByOrder = (EditText) findViewById(R.id.et_search_by_order);
        mEtXiaoYu = (EditText) findViewById(R.id.et_consume_xiaoyu);
        mEtDaYu = (EditText) findViewById(R.id.et_consume_dayu);
        mRbPayAll = (RadioButton) findViewById(R.id.rb_goods_1);
        mRbCash = (RadioButton) findViewById(R.id.rb_goods_2);
        mRbYuE = (RadioButton) findViewById(R.id.rb_goods_3);
        mRbCard = (RadioButton) findViewById(R.id.rb_goods_4);
        mRbPoint = (RadioButton) findViewById(R.id.rb_goods_5);
        mRbwx = findViewById(R.id.rb_goods_9);
        mRbzfb = findViewById(R.id.rb_goods_10);
        mRbDeviceAll = (RadioButton) findViewById(R.id.rb_goods_6);
        mRbDeviceWeb = (RadioButton) findViewById(R.id.rb_goods_7);
        mRbDeviceApp = (RadioButton) findViewById(R.id.rb_goods_8);
        mRbDeviceIOS = (RadioButton) findViewById(R.id.rb_goods_11);

        mRg1 = (RadioGroup) findViewById(R.id.rg_pay_way_first);
        mRg2 = (RadioGroup) findViewById(R.id.rg_pay_way_second);
        mRg3 = findViewById(R.id.rg_pay_way_third);
        mRgDevice = (RadioGroup) findViewById(R.id.rg_device_type);
        mSpinner = (Spinner) findViewById(R.id.sp_times_recharge_store);
    }

    private void initVariable() {
        String order = mStateUtil.get("Order", null);
        String big = (String) mStateUtil.get("Big", null);
        String small = (String) mStateUtil.get("Small", null);
        String pay = (String) mStateUtil.get("Pay", null);
        String device = (String) mStateUtil.get("Device", null);
        String storeGid = (String) mStateUtil.get("Store", null);
        if (order != null) {
            mEtByOrder.setText(order);
        }
        if (big != null) {
            mEtDaYu.setText(big);
        }
        if (small != null) {
            mEtXiaoYu.setText(small);
        }
        if (pay != null && !pay.equals("")) {
            switch (pay) {
                case "XJZF":
                    mRbCash.setChecked(true);
                    mPayWayCode = "XJZF";
                    break;
                case "YEZF":
                    mRbYuE.setChecked(true);
                    mPayWayCode = "YEZF";
                    break;
                case "YLZF":
                    mRbPayAll.setChecked(false);
                    mRbCard.setChecked(true);
                    mPayWayCode = "YLZF";
                    break;
                case "JFZF":
                    mRbPayAll.setChecked(false);
                    mRbPoint.setChecked(true);
                    mPayWayCode = "JFZF";
                    break;
                case "WXJZ":
                    mRbPayAll.setChecked(false);
                    mRbwx.setChecked(true);
                    mPayWayCode = "WXJZ";
                    break;
                case "ZFBJZ":
                    mRbPayAll.setChecked(false);
                    mRbzfb.setChecked(true);
                    mPayWayCode = "ZFBJZ";
                    break;
            }
        }
        if (device != null && !device.equals("")) {
            switch (device) {
                case "1":
                    mRbDeviceWeb.setChecked(true);
                    break;
                case "2":
                    mRbDeviceApp.setChecked(true);
                    break;
                case "4":
                    mRbDeviceIOS.setChecked(true);
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

        mSpinnerAdapter = new ArrayAdapter<String>(TimesRechargeScreenActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, mStoreList);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setSelection(pos, true);
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
                TimesRechargeScreenActivity.this.finish();
            }
        });
        //清空筛选条件
        findViewById(R.id.btn_report_screen_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.clean();
                mPayWayCode = "";
                mDeviceType = "";
                mEtByMember.setText("");
                mEtByOrder.setText("");
                mEtByName.setText("");
                mEtDaYu.setText("");
                mEtXiaoYu.setText("");
                mRg1.check(mRbPayAll.getId());
                mRg2.clearCheck();
                mRgDevice.check(mRbDeviceAll.getId());
                mSpinner.setSelection(pos);
            }
        });
        //确定
        findViewById(R.id.tv_report_screen_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.put("Order", mEtByOrder.getText().toString());
                mStateUtil.put("Big", mEtDaYu.getText().toString());
                mStateUtil.put("Small", mEtXiaoYu.getText().toString());
                mStateUtil.put("Pay", mPayWayCode);
                mStateUtil.put("Device", mDeviceType);
                mStateUtil.put("Store", mStoreGid == null ? "" : mStoreGid);
                Intent intent = new Intent();
                intent.putExtra("Name", mEtByMember.getText().toString());
                intent.putExtra("Order", mEtByOrder.getText().toString());
                intent.putExtra("service_name", mEtByName.getText().toString());
                intent.putExtra("Big", mEtDaYu.getText().toString());
                intent.putExtra("Small", mEtXiaoYu.getText().toString());
                intent.putExtra("Pay", mPayWayCode);
                intent.putExtra("Device", mDeviceType);
                intent.putExtra("Store", mStoreGid);
                setResult(666, intent);
                finish();
            }
        });


        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbPayAll.getId()) {
                    mRg2.clearCheck();
                    mRg1.check(mRbPayAll.getId());
                    mRg3.clearCheck();
                    mPayWayCode = "";
                }
                if (i == mRbCash.getId()) {
                    mRg2.clearCheck();
                    mRg1.check(mRbCash.getId());
                    mRg3.clearCheck();
                    mPayWayCode = "XJZF";
                }
                if (i == mRbYuE.getId()) {
                    mRg2.clearCheck();
                    mRg1.check(mRbYuE.getId());
                    mRg3.clearCheck();
                    mPayWayCode = "YEZF";
                }

            }
        });
        mRg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbCard.getId()) {
                    mRg1.clearCheck();
                    mRg2.check(mRbCard.getId());
                    mRg3.clearCheck();
                    mPayWayCode = "YLZF";
                }
                if (i == mRbPoint.getId()) {
                    mRg1.clearCheck();
                    mRg2.check(mRbPoint.getId());
                    mRg3.clearCheck();
                    mPayWayCode = "JFZF";
                }
                if (i == mRbwx.getId()) {
                    mRg1.clearCheck();
                    mRg2.check(mRbwx.getId());
                    mRg3.clearCheck();
                    mPayWayCode = "WXJZ";
                }

            }
        });

        mRg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbzfb.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.check(mRbzfb.getId());
                    mPayWayCode = "ZFBJZ";
                }

            }
        });
//        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if (i == mRbPayAll.getId()) {
//                    mRg2.clearCheck();
//                    mRg1.check(mRbPayAll.getId());
//                    mPayWayCode = "";
//                }
//                if (i == mRbCash.getId()) {
//                    mRg2.clearCheck();
//                    mRg1.check(mRbCash.getId());
//                    mPayWayCode = "XJZF";
//                }
//                if (i == mRbYuE.getId()) {
//                    mRg2.clearCheck();
//                    mRg1.check(mRbYuE.getId());
//                    mPayWayCode = "YEZF";
//                }
//
//            }
//        });
//        mRg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                if (i == mRbCard.getId()) {
//                    mRg1.clearCheck();
//                    mRg2.check(mRbCard.getId());
//                    mPayWayCode = "YLZF";
//                }
//                if (i == mRbPoint.getId()) {
//                    mRg1.clearCheck();
//                    mRg2.check(mRbPoint.getId());
//                    mPayWayCode = "JFZF";
//                }
//            }
//        });
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
        if (mLoginBean != null) {
            if (mLoginBean.getData().getUM_IsAmin() != 1) {
                mSpinner.setSelection(pos, true);
                mStoreGid = mLoginBean.getData().getShopID();
                mSpinner.setClickable(false);
            }
        }
    }
}
