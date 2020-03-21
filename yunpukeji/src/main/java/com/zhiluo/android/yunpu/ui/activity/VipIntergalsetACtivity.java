package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.entity.GradeSetBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.ui.adapter.GradeIntegralSetAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 积分设置
 * Created by ${YSL} on 2018-04-10.
 */

public class VipIntergalsetACtivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.v_oil)
    View vOil;
    @Bind(R.id.et_origin_intergal)
    EditText etOriginIntergal;
    @Bind(R.id.et_onekey_oil)
    EditText etOnekeyOil;
    @Bind(R.id.ll_oil)
    LinearLayout llOil;
    private EditText edOriginIntergal, edFastShop, edVipRechange, unitfiedSet;
    private TextView mBack;
    private ImageView mOK;
    private CheckBox cbUnitfied, cbDetail;
    private RecyclerView mRecyclerView;
    private GradeIntegralSetAdapter mSetAdapter;
    private GradeSetBean mSetBean;
    private String mGid;
    private String type;
    private DataBean mGrade;
    private LoginUpbean mLoginBean;
    private boolean isOneKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vipintergalset);
        ButterKnife.bind(this);
        initViews();
        mGid = getIntent().getStringExtra("GID");
        mSetBean = (GradeSetBean) getIntent().getSerializableExtra("GD");
        mGrade = (DataBean) getIntent().getSerializableExtra("Grade");
        initData();
        charge();
    }

    private void initData() {
        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        mGrade = (DataBean) getIntent().getSerializableExtra("Grade");

        if (mLoginBean != null) {
            try {
                if (mLoginBean.getData().getShopList().get(0).getSM_FunctionList() != null && !mLoginBean.getData().getShopList().get(0).getSM_FunctionList().equals("")) {

                    JSONArray jsonArray = new JSONArray(mLoginBean.getData().getShopList().get(0).getSM_FunctionList());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.get("code").equals("1JY")) {
                            Integer s = (Integer) jsonObject.get("value");
                            if (s == 1)
                                isOneKey = true;
                            llOil.setVisibility(View.VISIBLE);
                            vOil.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    isOneKey = false;
                    llOil.setVisibility(View.GONE);
                    vOil.setVisibility(View.GONE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                isOneKey = false;
                llOil.setVisibility(View.GONE);
                vOil.setVisibility(View.GONE);
            }
        }


        if (mSetBean == null) {
            getData();
        } else {
            mSetAdapter = new GradeIntegralSetAdapter(mSetBean);
            LinearLayoutManager manager = new LinearLayoutManager(VipIntergalsetACtivity.this);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setAdapter(mSetAdapter);
        }
        //初始积分
        edOriginIntergal.setText(Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_InitialIntegral()));
        //充值积分比例
        if (mGrade.getRS_CMoney() > 0 && mGrade.getRS_Value() > 0) {
            double rIntegralScale = DoubleMathUtil.div(mGrade.getRS_CMoney(), mGrade.getRS_Value(), 2);
            edVipRechange.setText(Decima2KeeplUtil.stringToDecimal("" + rIntegralScale));
        }
        //一键加油比例
        if (mGrade.getVG_OilIntegral() > 0) {
            etOnekeyOil.setText(Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_OilIntegral()));
        }

        //快消积分比例
        if (mGrade.getVS_CMoney() > 0 && mGrade.getVS_Value() > 0) {
            double kIntegralScale = DoubleMathUtil.div(mGrade.getVS_CMoney(), mGrade.getVS_Value(), 2);
            edFastShop.setText(Decima2KeeplUtil.stringToDecimal("" + kIntegralScale));
        }
        switch (mGrade.getVG_IntegralRuleType()) {
            case 0://统一设置
                cbUnitfied.setChecked(true);
                cbDetail.setChecked(false);
                unitfiedSet.setEnabled(true);
                unitfiedSet.setText(Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_IntegralUniformRuleValue()));
                mRecyclerView.setVisibility(View.INVISIBLE);
                cbUnitfied.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                cbDetail.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                cbUnitfied.setTextColor(Color.parseColor("#FF8739"));
                cbDetail.setTextColor(Color.parseColor("#333333"));
                unitfiedSet.setBackgroundResource(R.color.white);
                break;
            case 1://详情设置
                cbUnitfied.setChecked(false);
                cbDetail.setChecked(true);
                mRecyclerView.setVisibility(View.VISIBLE);
                unitfiedSet.setEnabled(false);
                unitfiedSet.setText(Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_IntegralUniformRuleValue()));
                unitfiedSet.setBackgroundResource(R.color.gray);
                cbDetail.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                cbUnitfied.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                cbDetail.setTextColor(Color.parseColor("#FF8739"));
                cbUnitfied.setTextColor(Color.parseColor("#333333"));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
//                CustomToast.makeText(this, "您取消了积分详细设置", Toast.LENGTH_SHORT).show();
                VipIntergalsetACtivity.this.finish();
                break;
            case R.id.img_ok:
                Intent intent = new Intent();
                intent.putExtra("integral_set", mSetBean);//详细设置的实体类
                intent.putExtra("intergalsettype", type);//积分类型
                intent.putExtra("edOneKeyOil", etOnekeyOil.getText().toString());//一键加油比列
                intent.putExtra("edFastShop", edFastShop.getText().toString());//快速消费比例
                intent.putExtra("edOriginIntergal", edOriginIntergal.getText().toString());//初始积分
                intent.putExtra("edVipRechange", edVipRechange.getText().toString());//会员充值比例
                intent.putExtra("unitfiedSets", unitfiedSet.getText().toString());//统一设置
                setResult(20, intent);
                if (cbUnitfied.isChecked() && (TextUtils.isEmpty(unitfiedSet.getText().toString()) || unitfiedSet.equals(""))) {
                    CustomToast.makeText(this, "您选中了统一设置，请设置积分", Toast.LENGTH_SHORT).show();
                    return;
                }
                finish();
                break;
        }
    }

    private void getData() {
        RequestParams params = new RequestParams();
        params.put("GID", mGid);
        HttpHelper.post(this, "VIPGrade/GetPTRemoveParentList", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSetBean = CommonFun.JsonToObj(responseString, GradeSetBean.class);
                mSetAdapter = new GradeIntegralSetAdapter(mSetBean);
                LinearLayoutManager manager = new LinearLayoutManager(VipIntergalsetACtivity.this);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(mSetAdapter);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(VipIntergalsetACtivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 开关及状态控制
     */
    private void charge() {
        if (cbUnitfied.isChecked()) {
            type = "nuitfied";
        } else if (cbDetail.isChecked()) {
            type = "detail";
        }
        cbUnitfied.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbDetail.setChecked(false);
                    unitfiedSet.setEnabled(true);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                    cbUnitfied.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbDetail.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbUnitfied.setTextColor(Color.parseColor("#FF8739"));
                    cbDetail.setTextColor(Color.parseColor("#333333"));
                    unitfiedSet.setBackgroundResource(R.color.white);
                    type = "nuitfied";
                }
            }
        });
        cbDetail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbUnitfied.setChecked(false);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    unitfiedSet.setEnabled(false);
                    unitfiedSet.setBackgroundResource(R.color.gray);
                    cbDetail.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbUnitfied.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbDetail.setTextColor(Color.parseColor("#FF8739"));
                    cbUnitfied.setTextColor(Color.parseColor("#333333"));
                    type = "detail";
                }
            }
        });
    }

    private void initViews() {
        edOriginIntergal = findViewById(R.id.et_origin_intergal);//初始积分
        edFastShop = findViewById(R.id.et_fast_shop);//快速消费
        edVipRechange = findViewById(R.id.et_vip_rechange);//会员充值
        unitfiedSet = findViewById(R.id.et_unified_set);//统一设置
        mBack = findViewById(R.id.tv_back);
        mOK = findViewById(R.id.img_ok);
        mRecyclerView = findViewById(R.id.rv_integral_set);
        cbUnitfied = findViewById(R.id.cb_unitfied_set);
        cbDetail = findViewById(R.id.cb_detail_set);
        mBack.setOnClickListener(this);
        mOK.setOnClickListener(this);
    }
}
