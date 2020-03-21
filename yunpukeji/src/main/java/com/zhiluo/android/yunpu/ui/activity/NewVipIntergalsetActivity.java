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
import com.zhiluo.android.yunpu.entity.GradeSetBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.ui.adapter.GradeIntegralSetAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * Created by ${YSL} on 2018-04-10.
 */

public class NewVipIntergalsetActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.et_onekey_oil)
    EditText etOnekeyOil;
    @Bind(R.id.ll_oil)
    LinearLayout llOil;
    @Bind(R.id.v_oil)
    View vOil;

    private EditText edOriginIntergal, edFastShop, edVipRechange, unitfiedSet;
    private TextView mBack;
    private ImageView mOK;
    private CheckBox cbUnitfied, cbDetail;
    private RecyclerView mRecyclerView;
    private GradeIntegralSetAdapter mSetAdapter;
    private GradeSetBean mSetBean;
    private String type = "";
    private LoginUpbean mLoginBean;
    private boolean isOneKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vipintergalset);
        ButterKnife.bind(this);
        initViews();
        charge();
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                Intent intent0 = new Intent();
                intent0.putExtra("integral_set", mSetBean);//详细设置的实体类
                intent0.putExtra("intergalsettype", type);//积分类型
                intent0.putExtra("edOneKeyOil", etOnekeyOil.getText().toString());//一键加油比列
                intent0.putExtra("edFastShop", edFastShop.getText().toString());//快速消费比例
                intent0.putExtra("edOriginIntergal", edOriginIntergal.getText().toString());//初始积分
                intent0.putExtra("edVipRechange", edVipRechange.getText().toString());//会员充值比例
                intent0.putExtra("unitfiedSets", unitfiedSet.getText().toString());//统一设置
                setResult(20, intent0);
                finish();
                CustomToast.makeText(this, "您取消了积分详细设置", Toast.LENGTH_SHORT).show();
                NewVipIntergalsetActivity.this.finish();
                break;
            case R.id.img_ok:
                Intent intent = new Intent();
                if (cbUnitfied.isChecked()) {
                    type = "nuitfied";
                } else if (cbDetail.isChecked()) {
                    type = "detail";
                }
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
                Le("---------1-=---" + type);
                break;
        }
    }

    private void getData() {
        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
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

        RequestParams params = new RequestParams();
        params.put("GID", "");
        HttpHelper.post(this, "VIPGrade/GetPTRemoveParentList", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSetBean = CommonFun.JsonToObj(responseString, GradeSetBean.class);
                mSetAdapter = new GradeIntegralSetAdapter(mSetBean);
                LinearLayoutManager manager = new LinearLayoutManager(NewVipIntergalsetActivity.this);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(mSetAdapter);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(NewVipIntergalsetActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 开关及状态控制
     */
    private void charge() {
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

        cbUnitfied.setChecked(true);
        cbDetail.setChecked(false);
        unitfiedSet.setEnabled(true);
        mRecyclerView.setVisibility(View.INVISIBLE);
        cbUnitfied.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
        cbDetail.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
        cbUnitfied.setTextColor(Color.parseColor("#FF8739"));
        cbDetail.setTextColor(Color.parseColor("#333333"));
        unitfiedSet.setBackgroundResource(R.color.white);
    }

}
