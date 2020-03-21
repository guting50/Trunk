package com.zhiluo.android.yunpu.statistics.account.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class IntegralScreenActivity extends BaseActivity {
    private EditText mEtByMember, mEtXiaoYu, mEtDaYu;
    private Spinner mSpinner;
    private LoginUpbean mLoginBean;
    private ArrayAdapter<String> mSpinnerAdapter;
    private ArrayList<String> mStoreList;//店铺
    private String mStoreGid;//店铺GID
    private int pos;
    private ShopListBean mShopListBean;
    private RadioGroup mRg1, mRg2,mRg3,mRg4, mRg5,mRg6;
    private RadioButton mRbJFAll, mRbCC, mRbSPXF, mRbKSXF,
            mRbZJJF, mRbXZHY, mRbCZ, mRbCZFL,
            mRbCCFL, mRbSPXFFL, mRbKSXFFL, mRbTJHYFL,
            mRbFD, mRbTH, mRbKCJF, mRbJFQL, mRbLPDH;

    private final String mFileName = "JF_data";
    private SaveScreenStateUtil mStateUtil;
    private String mJiFenWayCode;//积分途径

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_screen);
        mStateUtil = new SaveScreenStateUtil(this, mFileName);
        initView();
        initVariable();
        setListener();
    }

    private void initView() {

        mEtByMember = (EditText) findViewById(R.id.et_report_screen_search);
        mEtXiaoYu = (EditText) findViewById(R.id.et_consume_xiaoyu);
        mEtDaYu = (EditText) findViewById(R.id.et_consume_dayu);
        mSpinner = (Spinner) findViewById(R.id.sp_integral_store);
        mRg1 = (RadioGroup) findViewById(R.id.rg_pay_way_first);
        mRg2 = (RadioGroup) findViewById(R.id.rg_pay_way_second);
        mRg3 = findViewById(R.id.rg_pay_way_third);
        mRg4 = (RadioGroup) findViewById(R.id.rg_pay_way_fourth);
        mRg5 = (RadioGroup) findViewById(R.id.rg_pay_way_fifth);
        mRg6 = findViewById(R.id.rg_pay_way_sixth);

        mRbJFAll = (RadioButton) findViewById(R.id.rb_goods_1);
        mRbCC = (RadioButton) findViewById(R.id.rb_goods_2);
        mRbSPXF = (RadioButton) findViewById(R.id.rb_goods_3);
        mRbKSXF = (RadioButton) findViewById(R.id.rb_goods_4);
        mRbZJJF = (RadioButton) findViewById(R.id.rb_goods_5);
        mRbXZHY = (RadioButton) findViewById(R.id.rb_goods_6);
        mRbCZ = (RadioButton) findViewById(R.id.rb_goods_7);
        mRbCZFL = (RadioButton) findViewById(R.id.rb_goods_8);
        mRbCCFL = findViewById(R.id.rb_goods_9);
        mRbSPXFFL = findViewById(R.id.rb_goods_10);
        mRbKSXFFL = (RadioButton) findViewById(R.id.rb_goods_11);
        mRbTJHYFL = (RadioButton) findViewById(R.id.rb_goods_12);
        mRbFD = (RadioButton) findViewById(R.id.rb_goods_13);
        mRbTH = (RadioButton) findViewById(R.id.rb_goods_14);
        mRbKCJF = (RadioButton) findViewById(R.id.rb_goods_15);
        mRbJFQL = (RadioButton) findViewById(R.id.rb_goods_16);
        mRbLPDH = (RadioButton) findViewById(R.id.rb_goods_17);

    }


    private void initVariable() {
        String big = (String) mStateUtil.get("Big", null);
        String small = (String) mStateUtil.get("Small", null);
        String storeGid = (String) mStateUtil.get("Store", null);
        String jiFen = (String) mStateUtil.get("Pay", null);
        if (big != null) {
            mEtDaYu.setText(big);
        }
        if (small != null) {
            mEtXiaoYu.setText(small);
        }

        if (jiFen != null && !jiFen.equals("")) {
            switch (jiFen) {
                case "1"://充次
                    mRbCC.setChecked(true);
                    mJiFenWayCode = "1";
                    break;
                case "2":
                    mRbSPXF.setChecked(true);
                    mJiFenWayCode = "2";
                    break;
                case "3":
                    mRbJFAll.setChecked(false);
                    mRbKSXF.setChecked(true);
                    mJiFenWayCode = "3";
                    break;

                case "4":
                    mRbJFAll.setChecked(false);
                    mRbZJJF.setChecked(true);
                    mJiFenWayCode = "4";
                    break;
                case "5":
                    mRbJFAll.setChecked(false);
                    mRbXZHY.setChecked(true);
                    mJiFenWayCode = "5";
                    break;
                case "6":
                    mRbJFAll.setChecked(false);
                    mRbCZ.setChecked(true);
                    mJiFenWayCode = "6";
                    break;
                case "10":
                    mRbJFAll.setChecked(false);
                    mRbCZFL.setChecked(true);
                    mJiFenWayCode = "10";
                    break;
                case "11":
                    mRbJFAll.setChecked(false);
                    mRbCCFL.setChecked(true);
                    mJiFenWayCode = "11";
                    break;
                case "12":
                    mRbJFAll.setChecked(false);
                    mRbSPXFFL.setChecked(true);
                    mJiFenWayCode = "12";
                    break;
                case "13":
                    mRbJFAll.setChecked(false);
                    mRbKSXFFL.setChecked(true);
                    mJiFenWayCode = "13";
                    break;
                case "14":
                    mRbJFAll.setChecked(false);
                    mRbTJHYFL.setChecked(true);
                    mJiFenWayCode = "14";
                    break;
                case "-1":
                    mRbJFAll.setChecked(false);
                    mRbFD.setChecked(true);
                    mJiFenWayCode = "-1";
                    break;
                case "-2":
                    mRbJFAll.setChecked(false);
                    mRbTH.setChecked(true);
                    mJiFenWayCode = "-2";
                    break;
                case "-3":
                    mRbJFAll.setChecked(false);
                    mRbKCJF.setChecked(true);
                    mJiFenWayCode = "-3";
                    break;
                case "-4":
                    mRbJFAll.setChecked(false);
                    mRbJFQL.setChecked(true);
                    mJiFenWayCode = "-4";
                    break;
                case "-5":
                    mRbJFAll.setChecked(false);
                    mRbLPDH.setChecked(true);
                    mJiFenWayCode = "-5";
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

        mSpinnerAdapter = new ArrayAdapter<String>(IntegralScreenActivity.this,
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
        //清空筛选条件
        findViewById(R.id.btn_report_screen_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.clean();
                mEtByMember.setText("");
                mEtDaYu.setText("");
                mEtXiaoYu.setText("");
                mSpinner.setSelection(pos);
                mRg1.check(mRbJFAll.getId());
                mRg2.clearCheck();
                mRg3.clearCheck();
                mRg4.clearCheck();
                mRg5.clearCheck();
                mRg6.clearCheck();

            }
        });
        //确定
        findViewById(R.id.tv_report_screen_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.put("Big", mEtDaYu.getText().toString());
                mStateUtil.put("Small", mEtXiaoYu.getText().toString());
                mStateUtil.put("Store", mStoreGid == null ? "" : mStoreGid);
                mStateUtil.put("Pay", mJiFenWayCode == null ? "" : mJiFenWayCode);
                Intent intent = new Intent();
                intent.putExtra("Name", mEtByMember.getText().toString());
                intent.putExtra("Big", mEtDaYu.getText().toString());
                intent.putExtra("Small", mEtXiaoYu.getText().toString());
                intent.putExtra("Store", mStoreGid);
                intent.putExtra("Pay", mJiFenWayCode);

                setResult(666, intent);
                finish();
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

        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbJFAll.getId()) {
                    mRg1.check(mRbJFAll.getId());
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "";
                }
                if (i == mRbCC.getId()) {
                    mRg1.check(mRbCC.getId());
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "1";
                }
                if (i == mRbSPXF.getId()) {
                    mRg1.check(mRbSPXF.getId());
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "2";
                }

            }
        });


        mRg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbKSXF.getId()) {
                    mRg1.clearCheck();
                    mRg2.check(mRbKSXF.getId());
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "3";
                }
                if (i == mRbZJJF.getId()) {
                    mRg1.clearCheck();
                    mRg2.check(mRbZJJF.getId());
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "4";
                }
                if (i == mRbXZHY.getId()) {
                    mRg1.clearCheck();
                    mRg2.check(mRbXZHY.getId());
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "5";
                }

            }
        });

        mRg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbCZ.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.check(mRbCZ.getId());
                    mRg4.clearCheck();
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "6";
                }
                if (i == mRbCZFL.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.check(mRbCZFL.getId());
                    mRg4.clearCheck();
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "10";
                }
                if (i == mRbCCFL.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.check(mRbCCFL.getId());
                    mRg4.clearCheck();
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "11";
                }

            }
        });

        mRg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbSPXFFL.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.check(mRbSPXFFL.getId());
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "12";
                }
                if (i == mRbKSXFFL.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.check(mRbKSXFFL.getId());
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "13";
                }
                if (i == mRbTJHYFL.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.check(mRbTJHYFL.getId());
                    mRg5.clearCheck();
                    mRg6.clearCheck();
                    mJiFenWayCode = "14";
                }

            }
        });
        mRg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbFD.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg5.check(mRbFD.getId());
                    mRg6.clearCheck();
                    mJiFenWayCode = "-1";
                }
                if (i == mRbTH.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg5.check(mRbTH.getId());
                    mRg6.clearCheck();
                    mJiFenWayCode = "-2";
                }
                if (i == mRbKCJF.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg5.check(mRbKCJF.getId());
                    mRg6.clearCheck();
                    mJiFenWayCode = "-3";
                }

            }
        });

        mRg6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbJFQL.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg5.clearCheck();
                    mRg6.check(mRbJFQL.getId());
                    mJiFenWayCode = "-4";
                }
                if (i == mRbLPDH.getId()) {
                    mRg1.clearCheck();
                    mRg2.clearCheck();
                    mRg3.clearCheck();
                    mRg4.clearCheck();
                    mRg5.clearCheck();
                    mRg6.check(mRbLPDH.getId());
                    mJiFenWayCode = "-5";
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
