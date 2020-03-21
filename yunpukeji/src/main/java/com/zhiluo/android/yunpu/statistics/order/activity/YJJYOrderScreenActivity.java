package com.zhiluo.android.yunpu.statistics.order.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2019-07-03.
 */

public class YJJYOrderScreenActivity extends BaseActivity {

    @Bind(R.id.et_search_by_oil_name)
    EditText etSearchByOilName;
    private ImageView mIvScan;
    private EditText mEtByMember, mEtByOrder, etopreat;
    private RadioButton mRbStateAll, mRbstatenomal, mRbStateCD, mRbDeviceAll, mRbDeviceWeb, mRbDeviceApp, mRbDeviceIOS;
    private RadioGroup mRg1,  mRgDevice;
    private String mStateCode = "99", mDeviceType;
    private Spinner mSpinner;
    private LinearLayout layout;
    private LoginUpbean mLoginBean;
    private ArrayAdapter<String> mSpinnerAdapter;
    private ArrayList<String> mStoreList;//店铺
    private String mStoreGid;//店铺GID
    private int pos;
    private ShopListBean mShopListBean;
    private final String mFileName = "yjjy_data";
    private SaveScreenStateUtil mStateUtil;
    private String vipCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yjjy_order_screen);
        ButterKnife.bind(this);
        mStateUtil = new SaveScreenStateUtil(this, mFileName);
        vipCard = getIntent().getStringExtra("vipcard");
        initView();
        initVariable();
        setListener();
    }

    private void initView() {
        mIvScan = (ImageView) findViewById(R.id.iv_report_screen_scan);
        mEtByMember = (EditText) findViewById(R.id.et_report_screen_search);
        mEtByOrder = (EditText) findViewById(R.id.et_search_by_order);
        etopreat = findViewById(R.id.et_search_by_opreat);
        mRbStateAll = (RadioButton) findViewById(R.id.rb_goods_1);
        mRbstatenomal = (RadioButton) findViewById(R.id.rb_goods_2);
        mRbStateCD = (RadioButton) findViewById(R.id.rb_goods_3);

        mRbDeviceAll = (RadioButton) findViewById(R.id.rb_goods_6);
        mRbDeviceWeb = (RadioButton) findViewById(R.id.rb_goods_7);
        mRbDeviceApp = (RadioButton) findViewById(R.id.rb_goods_8);
        mRbDeviceIOS = (RadioButton) findViewById(R.id.rb_goods_9);
        mRg1 = (RadioGroup) findViewById(R.id.rg_pay_way_first);
        mRgDevice = (RadioGroup) findViewById(R.id.rg_device_type);
        mSpinner = (Spinner) findViewById(R.id.sp_goods_order_store);
        layout = findViewById(R.id.l_yout);
        if (vipCard != null) {
            layout.setVisibility(View.GONE);
        }
    }

    private void initVariable() {
        String oilname = (String) mStateUtil.get("oilname", null);//油品名称
        String order = mStateUtil.get("Order", null);//订单号
        String state = (String) mStateUtil.get("state", null);//状态码
        String device = (String) mStateUtil.get("Device", null);//设备
        String storeGid = (String) mStateUtil.get("Store", null);//店铺Gid
        String opreat = mStateUtil.get("Opreat", null);//操作员


        if (order != null) {
            mEtByOrder.setText(order);
        }
        if (opreat != null) {
            etopreat.setText(opreat);
        }
        if (oilname != null) {
            etSearchByOilName.setText(oilname);
        }


        if (state != null) {
            switch (state) {
                case "99":
                    mRbstatenomal.setChecked(true);
                    mStateCode = "99";
                    break;
                case "7":
                    mRbStateCD.setChecked(true);
                    mStateCode = "7";
                    break;

                default:
                    mRbStateAll.setChecked(true);
                    mStateCode = "";
                    break;

            }
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
        mSpinnerAdapter = new ArrayAdapter<String>(YJJYOrderScreenActivity.this,
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
                YJJYOrderScreenActivity.this.finish();
            }
        });
        //清空筛选条件
        findViewById(R.id.btn_report_screen_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.clean();
                mStateCode = "99";
                mDeviceType = "";
                mEtByMember.setText("");
                mEtByOrder.setText("");
                etSearchByOilName.setText("");
                etopreat.setText("");

                mRg1.check(mRbstatenomal.getId());
                mRgDevice.check(mRbDeviceAll.getId());
                mSpinner.setSelection(pos);
            }
        });
        //确定
        findViewById(R.id.tv_report_screen_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.put("oilname", etSearchByOilName.getText().toString());
                mStateUtil.put("Order", mEtByOrder.getText().toString());
                mStateUtil.put("state", mStateCode);
                mStateUtil.put("Device", mDeviceType);
                mStateUtil.put("Store", mStoreGid == null ? "" : mStoreGid);
                mStateUtil.put("Opreat", etopreat.getText().toString());

                Intent intent = new Intent();
                intent.putExtra("Name", mEtByMember.getText().toString());
                intent.putExtra("oilname", etSearchByOilName.getText().toString());
                intent.putExtra("Order", mEtByOrder.getText().toString());
                intent.putExtra("Opreat", etopreat.getText().toString());
                intent.putExtra("state", mStateCode);
                intent.putExtra("Device", mDeviceType);
                intent.putExtra("Store", mStoreGid);
                setResult(666, intent);
                finish();
            }
        });
        //状态
        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbStateAll.getId()) {
                    mRg1.check(mRbStateAll.getId());
                    mStateCode = "";
                }
                if (i == mRbstatenomal.getId()) {

                    mRg1.check(mRbstatenomal.getId());
                    mStateCode = "99";
                }
                if (i == mRbStateCD.getId()) {

                    mRg1.check(mRbStateCD.getId());
                    mStateCode = "7";
                }

            }
        });
        //设备
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
        //店铺
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
