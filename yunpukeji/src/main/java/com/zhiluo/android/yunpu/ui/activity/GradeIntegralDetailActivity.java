package com.zhiluo.android.yunpu.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.ui.adapter.GradeIntegralDetailAdapter;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date: 2018/1/18 15:52
 * Author: 罗咏哲
 * Description:积分详情
 * Version:
 */
public class GradeIntegralDetailActivity extends AppCompatActivity {
    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.rv_integral_detail)
    RecyclerView rvIntegralDetail;
    @Bind(R.id.et_origin_intergal)
    EditText edOriginIntergal;
    @Bind(R.id.et_onekey_oil)
    EditText etOnekeyOil;
    @Bind(R.id.ll_oil)
    LinearLayout llOil;
    @Bind(R.id.v_oil)
    View vOil;
    @Bind(R.id.et_fast_shop)
    EditText etFastShop;
    @Bind(R.id.et_vip_rechange)
    EditText edVipRechange;

    private DataBean mGrade;
    private GradeIntegralDetailAdapter mAdapter;
    private LoginUpbean mLoginBean;
    private boolean isOneKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_integral_detail);
        ButterKnife.bind(this);
        loadData();

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadData(){


        mGrade = (DataBean) getIntent().getSerializableExtra("GD");
        if (mGrade != null) {
            GridLayoutManager manager = new GridLayoutManager(this, 1);
            rvIntegralDetail.setLayoutManager(manager);
            mAdapter = new GradeIntegralDetailAdapter(mGrade);
            rvIntegralDetail.setAdapter(mAdapter);
        }

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
            etFastShop.setText(Decima2KeeplUtil.stringToDecimal("" + kIntegralScale));
        }
    }
}
