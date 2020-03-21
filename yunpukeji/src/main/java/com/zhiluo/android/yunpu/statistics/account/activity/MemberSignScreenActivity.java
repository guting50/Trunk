package com.zhiluo.android.yunpu.statistics.account.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

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
 * Created by ZPH on 2019-05-16.
 */

public class MemberSignScreenActivity extends BaseActivity {

    @Bind(R.id.tv_report_screen_back)
    TextView tvReportScreenBack;
    @Bind(R.id.tv_head_title)
    TextView tvHeadTitle;
    @Bind(R.id.tv_report_screen_confirm)
    TextView tvReportScreenConfirm;
    @Bind(R.id.et_report_screen_search)
    EditText etReportScreenSearch;
    @Bind(R.id.ll_goods_consume_scan)
    LinearLayout llGoodsConsumeScan;
    @Bind(R.id.et_search_by_order)
    EditText etSearchByOrder;
    @Bind(R.id.sp_money_store)
    Spinner spMoneyStore;
    @Bind(R.id.et_search_by_creator)
    EditText etSearchByCreator;
    @Bind(R.id.btn_report_screen_clean)
    Button btnReportScreenClean;

    private ArrayAdapter<String> mSpinnerAdapter;
    private ArrayList<String> mStoreList;//店铺
    private String mStoreGid;//店铺GID
    private int pos;
    private ShopListBean mShopListBean;
    private LoginUpbean mLoginBean;
    private SaveScreenStateUtil mStateUtil;
    private final String mFileName = "QD_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_sign_screen);
        ButterKnife.bind(this);
        mStateUtil = new SaveScreenStateUtil(this, mFileName);
        initVariable();
        setListener();
    }

    private void initVariable() {
        String projectNum = mStateUtil.get("projectNum", null);
        String creator =  mStateUtil.get("creator", null);
        String storeGid = (String) mStateUtil.get("Store", null);
        if (projectNum != null) {
            etSearchByOrder.setText(projectNum);
        }
        if (creator != null) {
            etSearchByCreator.setText(creator);
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

        mSpinnerAdapter = new ArrayAdapter<String>(MemberSignScreenActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, mStoreList);
        spMoneyStore.setAdapter(mSpinnerAdapter);
        spMoneyStore.setSelection(pos, true);
        if (mLoginBean.getData().getUM_IsAmin() == 1) {//判断是不是超管
            spMoneyStore.setEnabled(true);
        }else {
            spMoneyStore.setEnabled(false);
        }
    }

    private void setListener() {
        //返回
        tvReportScreenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //清空筛选条件
        btnReportScreenClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.clean();
                etSearchByOrder.setText("");
                etReportScreenSearch.setText("");

                etSearchByCreator.setText("");
                spMoneyStore.setSelection(pos);
            }
        });
        //确定
        tvReportScreenConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mStateUtil.put("projectNum", etSearchByOrder.getText().toString());
                mStateUtil.put("creator", etSearchByCreator.getText().toString());
                mStateUtil.put("Store", mStoreGid == null ? "" : mStoreGid);
                Intent intent = new Intent();
                intent.putExtra("Name", etReportScreenSearch.getText().toString());
                intent.putExtra("projectNum", etSearchByOrder.getText().toString());
                intent.putExtra("creator", etSearchByCreator.getText().toString());
                intent.putExtra("Store", mStoreGid);
                setResult(7, intent);
                finish();
            }
        });



        spMoneyStore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                spMoneyStore.setClickable(false);
            }
        }
    }

}
