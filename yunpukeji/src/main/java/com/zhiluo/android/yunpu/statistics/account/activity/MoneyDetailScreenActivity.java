package com.zhiluo.android.yunpu.statistics.account.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

public class MoneyDetailScreenActivity extends BaseActivity {
    private EditText mEtByMember, mEtByOrder;
    private RadioButton mRbAll, mRbActivity, mRbGoodsConsume, mRbFastConsume, mRbRechargeTime, mRbMove,
            mRbTiXian, mRbOpenCard, mRbDelay,mRbKoukuan;
    private RadioGroup mRg1, mRg2, mRg3,mRg4;
    private String mConsumeType;//金额类型
    private Spinner mSpinner;
    private LoginUpbean mLoginBean;
    private ArrayAdapter<String> mSpinnerAdapter;
    private ArrayList<String> mStoreList;//店铺
    private String mStoreGid;//店铺GID
    private int pos;
    private ShopListBean mShopListBean;
    private final String mFileName = "JE_data";
    private SaveScreenStateUtil mStateUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_detail_screen);
        mStateUtil = new SaveScreenStateUtil(this, mFileName);
        initView();
        initVariable();
        setListener();
    }

    private void initView() {
        mEtByOrder = (EditText) findViewById(R.id.et_search_by_order);
        mEtByMember = (EditText) findViewById(R.id.et_report_screen_search);
        mRbAll = (RadioButton) findViewById(R.id.rb_1);
        mRbActivity = (RadioButton) findViewById(R.id.rb_2);
        mRbGoodsConsume = (RadioButton) findViewById(R.id.rb_3);
        mRbFastConsume = (RadioButton) findViewById(R.id.rb_4);
        mRbRechargeTime = (RadioButton) findViewById(R.id.rb_5);
        mRbMove = (RadioButton) findViewById(R.id.rb_6);
        mRbTiXian = (RadioButton) findViewById(R.id.rb_7);
        mRbOpenCard = (RadioButton) findViewById(R.id.rb_8);
        mRbDelay = (RadioButton) findViewById(R.id.rb_9);
        mRbKoukuan = (RadioButton) findViewById(R.id.rb_10);
        mRg1 = (RadioGroup) findViewById(R.id.rg_1);
        mRg2 = (RadioGroup) findViewById(R.id.rg_2);
        mRg3 = (RadioGroup) findViewById(R.id.rg_3);
        mRg4 = (RadioGroup) findViewById(R.id.rg_4);
        mSpinner = (Spinner) findViewById(R.id.sp_money_store);
    }

    private void initVariable() {
        String order = mStateUtil.get("Order", null);
        String type = (String) mStateUtil.get("Type", null);
        String storeGid = (String) mStateUtil.get("Store", null);
        if (order != null) {
            mEtByOrder.setText(order);
        }
        if (type != null && !"".equals(type)) {
            mConsumeType = type;
            switch (type) {
                case "活动赠送":
                    mRbActivity.setChecked(true);
                    mConsumeType = "活动赠送";
                    break;
                case "商品消费":
                    mRbGoodsConsume.setChecked(true);
                    mConsumeType = "商品消费";
                    break;
                case "快速消费":
                    mRbFastConsume.setChecked(true);
                    mRbAll.setChecked(false);
                    mConsumeType = "快速消费";
                    break;
                case "充次":
                    mRbRechargeTime.setChecked(true);
                    mRbAll.setChecked(false);
                    mConsumeType = "充次";
                    break;
                case "转出":
                    mRbMove.setChecked(true);
                    mRbAll.setChecked(false);
                    mConsumeType = "转出";
                    break;
                case "提现":
                    mRbTiXian.setChecked(true);
                    mRbAll.setChecked(false);
                    mConsumeType = "提现";
                    break;
                case "扣款":
                    mRbKoukuan.setChecked(true);
                    mRbAll.setChecked(false);
                    mConsumeType = "扣款";
                    break;
                case "开卡费用":
                    mRbOpenCard.setChecked(true);
                    mRbAll.setChecked(false);
                    mConsumeType = "开卡费用";
                    break;
                case "延期费用":
                    mRbDelay.setChecked(true);
                    mRbAll.setChecked(false);
                    mConsumeType = "延期费用";
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

        mSpinnerAdapter = new ArrayAdapter<String>(MoneyDetailScreenActivity.this,
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
                finish();
            }
        });
        //清空筛选条件
        findViewById(R.id.btn_report_screen_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.clean();
                mEtByOrder.setText("");
                mEtByMember.setText("");
                mRg1.check(mRbAll.getId());
                mRg2.clearCheck();
                mRg3.clearCheck();
                mRg4.clearCheck();
                mConsumeType = "";
                mSpinner.setSelection(pos);
            }
        });
        //确定
        findViewById(R.id.tv_report_screen_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.put("Order", mEtByOrder.getText().toString());
                mStateUtil.put("Type", mConsumeType);
                mStateUtil.put("Store", mStoreGid == null ? "" : mStoreGid);
                Intent intent = new Intent();
                intent.putExtra("Order", mEtByOrder.getText().toString());
                intent.putExtra("Name", mEtByMember.getText().toString());
                intent.putExtra("Type", mConsumeType);
                intent.putExtra("Store", mStoreGid);
                setResult(7, intent);
                finish();
            }
        });
        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbAll.getId()) {
                    mConsumeType = "";
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg1.check(mRbAll.getId());
                }
                if (i == mRbActivity.getId()) {
                    mConsumeType = "活动赠送";
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg1.check(mRbActivity.getId());
                }
                if (i == mRbGoodsConsume.getId()) {
                    mConsumeType = "商品消费";
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg1.check(mRbGoodsConsume.getId());
                }

            }
        });
        mRg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbFastConsume.getId()) {
                    mConsumeType = "快速消费";
                    mRg1.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg2.check(mRbFastConsume.getId());
                }
                if (i == mRbRechargeTime.getId()) {
                    mConsumeType = "充次";
                    mRg1.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg2.check(mRbRechargeTime.getId());
                }
                if (i == mRbMove.getId()) {
                    mConsumeType = "转出";
                    mRg1.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg2.check(mRbMove.getId());
                }

            }
        });
        mRg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbTiXian.getId()) {
                    mConsumeType = "提现";
                    mRg2.clearCheck();
                    mRg1.clearCheck();
                    mRg4.clearCheck();
                    mRg3.check(mRbTiXian.getId());
                }
                if (i == mRbKoukuan.getId()) {
                    mConsumeType = "扣款";
                    mRg2.clearCheck();
                    mRg1.clearCheck();
                    mRg4.clearCheck();
                    mRg3.check(mRbKoukuan.getId());
                }
                if (i == mRbOpenCard.getId()) {
                    mConsumeType = "开卡费用";
                    mRg2.clearCheck();
                    mRg1.clearCheck();
                    mRg4.clearCheck();
                    mRg3.check(mRbOpenCard.getId());
                }
            }
        });

        mRg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbDelay.getId()) {
                    mConsumeType = "延期费用";
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.check(mRbDelay.getId());
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
