package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.ui.adapter.GradeDiscountSetAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import java.io.Serializable;

/**
 * Created by ${YSL} on 2018-04-11.
 * 折扣设置
 */

public class YSLDiscountACtivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edFastBiLi, edTongYi;
    private CheckBox cbTongYi, cbXiangQiang;
    private DataBean mGrade, mFlagData;
    private RecyclerView mRecyclerView;
    private GradeDiscountSetAdapter mAdapter;
    //    private GradeSetBean mSetBean;
    private String mGid;
    private String mType;

    private boolean isXiangQiang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_discount);
        initViews();
        mGrade = (DataBean) getIntent().getSerializableExtra("Grade");
        mFlagData = (DataBean) getIntent().getSerializableExtra("Grade");
        mGid = getIntent().getStringExtra("GID");
//        mSetBean = (GradeSetBean) getIntent().getSerializableExtra("ZK");
        getData();
        listener();
        updateViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
//                CustomToast.makeText(this, "您取消了折扣设定", Toast.LENGTH_SHORT).show();
                YSLDiscountACtivity.this.finish();
                break;
            case R.id.img_ok:
                String tongFlag = edTongYi.getText().toString();
                for (int i = 0, len = mGrade.getSettings().size(); i < len; i++) {
                    if (mGrade.getSettings().get(i).getPD_Discount() == 0) {
                        mGrade.getSettings().get(i).setPD_Discount(mFlagData.getSettings().get(i).getPD_Discount());
                    }
                }
                if (tongFlag.equals("") || (Integer.parseInt(tongFlag) == 0) || (Integer.parseInt(tongFlag) > 100)) {
                    CustomToast.makeText(this, "统一设置不能为空,输入值为1-100", Toast.LENGTH_SHORT).show();
                    return;
                }
                String fastFlag = edFastBiLi.getText().toString();
                if (fastFlag.equals("") || (Integer.parseInt(fastFlag) <= 0) || (Integer.parseInt(fastFlag) > 100)) {
                    CustomToast.makeText(this, "快消比例不能为空,输入值为1-100", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (cbTongYi.isChecked()) {
                    if (TextUtils.isEmpty(edTongYi.getText().toString()) || edTongYi.getText().toString().equals("")) {
                        CustomToast.makeText(this, "您选择了统一设置，请设置折扣", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Intent intent = new Intent();
                if (cbTongYi.isChecked()) {
                    intent.putExtra("tongyi", "tongyi");
                } else {
                    mGrade.setVG_DiscountUniformRuleValue(mFlagData.getVG_DiscountUniformRuleValue());
                    intent.putExtra("tongyi", "details");
                }
                intent.putExtra("kuaixiaobili", edFastBiLi.getText().toString().replace("%", ""));
                intent.putExtra("tongyizhekou", edTongYi.getText().toString().replace("%", ""));
                intent.putExtra("discount_set", (Serializable) mGrade);
                setResult(30, intent);
                YSLDiscountACtivity.this.finish();
                break;
        }
    }

    private void updateViews() {
        switch (mGrade.getVG_DiscountRuleType()) {
            case 0://统一设置
                cbTongYi.setChecked(true);
                cbXiangQiang.setChecked(false);
                edTongYi.setEnabled(true);
                edTongYi.setText(mGrade.getVG_DiscountUniformRuleValue() + "");
                mRecyclerView.setVisibility(View.INVISIBLE);
                cbTongYi.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                cbXiangQiang.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                cbTongYi.setTextColor(Color.parseColor("#FF8739"));
                cbXiangQiang.setTextColor(Color.parseColor("#333333"));
                edTongYi.setBackgroundResource(R.color.white);
                break;
            case 1://详情设置
                isXiangQiang = true;
                cbTongYi.setChecked(false);
                cbXiangQiang.setChecked(true);
                mRecyclerView.setVisibility(View.VISIBLE);
                edTongYi.setEnabled(false);
                edTongYi.setBackgroundResource(R.color.lightgray);
                cbXiangQiang.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                cbTongYi.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                cbXiangQiang.setTextColor(Color.parseColor("#FF8739"));
                cbTongYi.setTextColor(Color.parseColor("#333333"));
                break;
        }
        //快速消费比例
        edFastBiLi.setText((int) mGrade.getDS_Value() + "");
        edTongYi.setText(mFlagData.getVG_DiscountUniformRuleValue() + "");
//        if (mGrade.getVS_Value() > 0) {
//            double ds = DoubleMathUtil.div(mGrade.getVS_CMoney(), mGrade.getVS_Value());
//            edFastBiLi.setText(Decima2KeeplUtil.stringToDecimal("" + ds));
//        } else {
//            edFastBiLi.setText("0");
//        }
    }

    private void getData() {

        mAdapter = new GradeDiscountSetAdapter(mGrade);
        LinearLayoutManager manager = new LinearLayoutManager(YSLDiscountACtivity.this);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);

//        RequestParams params = new RequestParams();
//        params.put("GID", mGid);
//        HttpHelper.post(this, "VIPGrade/GetPTRemoveParentList", params, new CallBack() {
//            @Override
//            public void onSuccess(String responseString, Gson gson) {
//                mSetBean = CommonFun.JsonToObj(responseString, GradeSetBean.class);
//                mAdapter = new GradeDiscountSetAdapter(mSetBean);
//                LinearLayoutManager manager = new LinearLayoutManager(YSLDiscountACtivity.this);
//                mRecyclerView.setLayoutManager(manager);
//                mRecyclerView.setAdapter(mAdapter);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                CustomToast.makeText(YSLDiscountACtivity.this, msg, Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void initViews() {
        edFastBiLi = findViewById(R.id.et_fast_shop);//快速消费比例
        edTongYi = findViewById(R.id.et_unified_set);//统一设置商品折扣比例
        cbTongYi = findViewById(R.id.cb_unitfied_set);//统一设置开关
        cbXiangQiang = findViewById(R.id.cb_detail_set);//详情设置开关
        mRecyclerView = findViewById(R.id.rv_integral_set);
    }

    private void listener() {
        findViewById(R.id.tv_back).setOnClickListener(this);
        findViewById(R.id.img_ok).setOnClickListener(this);
        cbTongYi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isXiangQiang = false;
                    cbXiangQiang.setChecked(false);
                    edTongYi.setEnabled(true);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                    cbTongYi.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbXiangQiang.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbTongYi.setTextColor(Color.parseColor("#FF8739"));
                    cbXiangQiang.setTextColor(Color.parseColor("#333333"));
                    edTongYi.setBackgroundResource(R.color.white);
                } else {
                    String tongFlag = edTongYi.getText().toString();
                    if (tongFlag.equals("") || (Integer.parseInt(tongFlag) == 0) || (Integer.parseInt(tongFlag) > 100)) {
                        edTongYi.setText(mFlagData.getVG_DiscountUniformRuleValue() + "");
                    }
                }
            }
        });
        cbXiangQiang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String tongFlag = edTongYi.getText().toString();
                if(tongFlag.equals("") || (Integer.parseInt(tongFlag) == 0) || (Integer.parseInt(tongFlag) > 100)){
                    CustomToast.makeText(YSLDiscountACtivity.this, "统一设置不能为空,输入值为1-100", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isChecked) {
                    if (!isXiangQiang) {
                        if (tongFlag.equals("") || (Integer.parseInt(tongFlag) == 0) || (Integer.parseInt(tongFlag) > 100)) {

                        } else {
                            for (int i = 0, len = mGrade.getSettings().size(); i < len; i++) {
                                mGrade.getSettings().get(i).setPD_Discount(Integer.parseInt(tongFlag));
                            }
                            mAdapter.notifyDataSetChanged();
                        }
                        isXiangQiang = true;
                    }
                    cbTongYi.setChecked(false);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    edTongYi.setEnabled(false);
//                    edTongYi.setText("0");
                    edTongYi.setBackgroundResource(R.color.lightgray);
                    cbXiangQiang.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbTongYi.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbXiangQiang.setTextColor(Color.parseColor("#FF8739"));
                    cbTongYi.setTextColor(Color.parseColor("#333333"));
                }
            }
        });
        edFastBiLi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s.toString().equals("0")) {
                    com.blankj.utilcode.util.ToastUtils.showShort("快消比例只能输入1-100");
                }
//                if(s.toString().equals("")){
//                    edFastBiLi.setText("100");
//                    com.blankj.utilcode.util.ToastUtils.showShort("快消比例只能输入1-100");
//                }
//                if (!TextUtils.isEmpty(s)) {
//                    if (RegexUtil.isDis(s.toString())) {
//
//                    } else {
//                        edFastBiLi.setText("100");
//                        com.blankj.utilcode.util.ToastUtils.showShort("快消比例只能输入1-100");
//                    }
//                }
            }
        });
        edTongYi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s.toString().equals("0")) {
                    com.blankj.utilcode.util.ToastUtils.showShort("快消比例只能输入1-100");
                }
//                if(s.toString().equals("")){
//                    edTongYi.setText("100");
//                    com.blankj.utilcode.util.ToastUtils.showShort("统一设置只能输入1-100");
//                }
//                if (!TextUtils.isEmpty(s)) {
//                    if (RegexUtil.isDis(s.toString())) {
//
//                    } else {
//                        edTongYi.setText("100");
//                        com.blankj.utilcode.util.ToastUtils.showShort("统一设置只能输入1-100");
//                    }
//                }
            }
        });
    }


}
