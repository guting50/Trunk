package com.zhiluo.android.yunpu.statistics.account.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.ShopListBean;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;

import java.util.ArrayList;

public class DelayScreenActivity extends BaseActivity {
    private EditText mEtVipInfo, mEtOrderNo;
    private Spinner mSpinner;
    private LoginUpbean mLoginBean;
    private ArrayAdapter<String> mSpinnerAdapter;
    private ArrayList<String> mStoreList;//店铺
    private String mStoreGid;//店铺GID
    private int pos;
    private ShopListBean mShopListBean;

    private final String mFileName = "YQ_data";
    private SaveScreenStateUtil mStateUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delay_screen);
        mStateUtil = new SaveScreenStateUtil(this, mFileName);
        mEtVipInfo = (EditText) findViewById(R.id.et_report_screen_search);
        mEtOrderNo = (EditText) findViewById(R.id.et_search_by_order);
        mSpinner = (Spinner) findViewById(R.id.sp_delay_store);
        initVariable();
        setListener();
    }

    private void initVariable() {
        String order = mStateUtil.get("Order", null);
        String storeGid = mStateUtil.get("Store", null);
        if (order != null) {
            mEtOrderNo.setText(order);
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

        mSpinnerAdapter = new ArrayAdapter<String>(DelayScreenActivity.this,
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
        //确定
        findViewById(R.id.tv_report_screen_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.put("Order", mEtOrderNo.getText().toString());
                mStateUtil.put("Store", mStoreGid == null ? "" : mStoreGid);
                Intent intent = new Intent();
                intent.putExtra("Name", mEtVipInfo.getText().toString());
                intent.putExtra("Order", mEtOrderNo.getText().toString());
                intent.putExtra("Store", mStoreGid);
                setResult(666, intent);
                finish();
            }
        });
        //清除筛选条件
        findViewById(R.id.btn_report_screen_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.clean();
                mEtVipInfo.setText("");
                mEtOrderNo.setText("");
                mSpinner.setSelection(pos);
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
