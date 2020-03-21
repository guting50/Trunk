package com.zhiluo.android.yunpu.statistics.staff.activity;

import android.content.Intent;
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
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;

import java.util.ArrayList;

/**
 * 报表筛选条件
 * 作者：罗咏哲 on 2017/9/1 14:20.
 * 邮箱：137615198@qq.com
 */
public class StaffScreenActivity extends BaseActivity {
    private ImageView mIvScan;
    private EditText mEtByMember;
    private RadioButton mRbAll, mRbCard, mRbRecharge, mRbTimes, mRbFast, mRbGoods, mRbTimesConsume,mRbTC;
    private RadioGroup mRg1, mRg2, mRg3;
    private String mRebateType;//提成类型
    private Spinner mSpinner;
    private LoginUpbean mLoginBean;
    private ArrayAdapter<String> mSpinnerAdapter;
    private ArrayList<String> mStoreList;//店铺
    private String mStoreGid;//店铺GID
    private int pos;
    private ShopListBean mShopListBean;
    private final String mFileName = "TC_data";
    private SaveScreenStateUtil mStateUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_screen);
        mStateUtil = new SaveScreenStateUtil(this, mFileName);
        initView();
        initVariable();
        setListener();
    }

    private void initView() {
        mIvScan = (ImageView) findViewById(R.id.iv_report_screen_scan);
        mEtByMember = (EditText) findViewById(R.id.et_report_screen_search);
        mRbAll = (RadioButton) findViewById(R.id.rb_1);
        mRbCard = (RadioButton) findViewById(R.id.rb_2);
        mRbRecharge = (RadioButton) findViewById(R.id.rb_3);
        mRbTimes = (RadioButton) findViewById(R.id.rb_4);
        mRbFast = (RadioButton) findViewById(R.id.rb_5);
        mRbGoods = (RadioButton) findViewById(R.id.rb_6);
        mRbTimesConsume = (RadioButton) findViewById(R.id.rb_7);
        mRbTC = (RadioButton) findViewById(R.id.rb_8);
        mRg1 = (RadioGroup) findViewById(R.id.rg_1);
        mRg2 = (RadioGroup) findViewById(R.id.rg_2);
        mRg3 = (RadioGroup) findViewById(R.id.rg_3);
        mSpinner = (Spinner) findViewById(R.id.sp_staff_store);
    }

    private void initVariable() {
        String type = mStateUtil.get("Type", null);
        String storeGid =  mStateUtil.get("Store", null);
        if (type != null) {
            mRebateType = type;
            switch (type) {
                case "10":
                    mRbCard.setChecked(true);
                    break;
                case "20":
                    mRbRecharge.setChecked(true);
                    break;
                case "30":
                    mRbTimes.setChecked(true);
                    mRbAll.setChecked(false);
                    break;
                case "40":
                    mRbFast.setChecked(true);
                    mRbAll.setChecked(false);
                    break;
                case "50":
                    mRbGoods.setChecked(true);
                    mRbAll.setChecked(false);
                    break;
                case "60":
                    mRbTimesConsume.setChecked(true);
                    mRbAll.setChecked(false);
                    break;
                case "70":
                    mRbTC.setChecked(true);
                    mRbAll.setChecked(false);
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
        mSpinnerAdapter = new ArrayAdapter<String>(StaffScreenActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, mStoreList);
        mSpinner.setAdapter(mSpinnerAdapter);
        mSpinner.setSelection(pos);

        if (mLoginBean!=null){
            if (mLoginBean.getData().getUM_IsAmin() ==1 ){
                mSpinner.setEnabled(true);
            }else {
                mSpinner.setEnabled(false);
            }
        }
    }

    private void setListener() {
        //返回
        findViewById(R.id.tv_report_screen_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaffScreenActivity.this.finish();
            }
        });
        //清空筛选条件
        findViewById(R.id.btn_report_screen_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEtByMember.setText("");
                mStateUtil.clean();
                mRg1.check(mRbAll.getId());
                mRg2.clearCheck();
                mRg3.clearCheck();
                mRebateType = "";
                mSpinner.setSelection(pos);
            }
        });
        //确定
        findViewById(R.id.tv_report_screen_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.put("Type", mRebateType);
                mStateUtil.put("Store", mStoreGid == null ? "" : mStoreGid);
                Intent intent = new Intent();
                intent.putExtra("Name", mEtByMember.getText().toString());
                intent.putExtra("Type", mRebateType);
                intent.putExtra("Store", mStoreGid);
                setResult(666, intent);
                finish();
            }
        });
        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbAll.getId()) {
                    mRebateType = "";
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg1.check(mRbAll.getId());
                }
                if (i == mRbCard.getId()) {
                    mRebateType = "10";
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg1.check(mRbCard.getId());
                }
                if (i == mRbRecharge.getId()) {
                    mRebateType = "20";
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg1.check(mRbRecharge.getId());
                }

            }
        });
        mRg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbTimes.getId()) {
                    mRebateType = "30";
                    mRg1.clearCheck();
                    mRg3.clearCheck();
                    mRg2.check(mRbTimes.getId());
                }
                if (i == mRbFast.getId()) {
                    mRebateType = "40";
                    mRg1.clearCheck();
                    mRg3.clearCheck();
                    mRg2.check(mRbFast.getId());
                }
                if (i == mRbGoods.getId()) {
                    mRebateType = "50";
                    mRg1.clearCheck();
                    mRg3.clearCheck();
                    mRg2.check(mRbGoods.getId());
                }

            }
        });
        mRg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbTimesConsume.getId()) {
                    mRebateType = "60";
                    mRg2.clearCheck();
                    mRg1.clearCheck();
                    mRg3.check(mRbTimesConsume.getId());
                }
                if (i == mRbTC.getId()) {
                    mRebateType = "70";
                    mRg2.clearCheck();
                    mRg1.clearCheck();
                    mRg3.check(mRbTC.getId());
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
                mStoreGid = mLoginBean.getData().getShopID();
                mSpinner.setClickable(false);
            }
        }
    }
}
