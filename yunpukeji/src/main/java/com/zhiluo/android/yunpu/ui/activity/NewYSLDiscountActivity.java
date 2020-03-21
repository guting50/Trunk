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

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.entity.NewGradeBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.adapter.GradeDiscountSetAdapter;
import com.zhiluo.android.yunpu.ui.adapter.NewGradeDiscountSetAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.RegexUtil;

import java.io.Serializable;

/**
 * Created by ${YSL} on 2018-04-11.
 */

public class NewYSLDiscountActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edFastBiLi, edTongYi;
    private CheckBox cbTongYi, cbXiangQiang;
    private RecyclerView mRecyclerView;
    private NewGradeDiscountSetAdapter mAdapter;
    private NewGradeBean mSetBean;
    private String s="";
    private String kxbl,tyzk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_discount);
        initViews();
        getData();
        listener();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
//                Intent intent0 = new Intent();
//                intent0.putExtra("kuaixiaobili", edFastBiLi.getText().toString());
//                intent0.putExtra("tongyizhekou", edTongYi.getText().toString());
//                intent0.putExtra("discount_set", (Serializable) mSetBean);
//                if (cbTongYi.isChecked()) {
//                    intent0.putExtra("tongyi", "tongyi");
//                }else {
//                    intent0.putExtra("tongyi", "");
//                }
//                setResult(300, intent0);
//                CustomToast.makeText(this, "您取消了折扣设定", Toast.LENGTH_SHORT).show();
                NewYSLDiscountActivity.this.finish();
                break;
            case R.id.img_ok:
                if (cbTongYi.isChecked()) {
                    if (TextUtils.isEmpty(edTongYi.getText().toString()) || edTongYi.getText().toString().equals("")) {
                        CustomToast.makeText(this, "您选择了统一设置，请设置折扣", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Intent intent = new Intent();
                intent.putExtra("kuaixiaobili", edFastBiLi.getText().toString());
                intent.putExtra("tongyizhekou", edTongYi.getText().toString());
                intent.putExtra("discount_set", (Serializable) mSetBean);

                if (cbTongYi.isChecked()) {
                    intent.putExtra("tongyi", "tongyi");
                }else {
                    intent.putExtra("tongyi", "");
                }
                setResult(300, intent);
                NewYSLDiscountActivity.this.finish();
                break;
        }
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent !=null){
            kxbl = intent.getStringExtra("kxzk");
            tyzk = intent.getStringExtra("tyzk");
            edFastBiLi.setText(kxbl+"");
            edTongYi.setText(tyzk+"");
        }

        RequestParams params = new RequestParams();
        params.put("GID", "");
        HttpHelper.post(this, "VIPGrade/GetPTRemoveParentList", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSetBean = CommonFun.JsonToObj(responseString, NewGradeBean.class);
                mAdapter = new NewGradeDiscountSetAdapter(mSetBean);
                LinearLayoutManager manager = new LinearLayoutManager(NewYSLDiscountActivity.this);
                mRecyclerView.setLayoutManager(manager);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(NewYSLDiscountActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        edFastBiLi = findViewById(R.id.et_fast_shop);//快速消费比例
        edTongYi = findViewById(R.id.et_unified_set);//统一设置商品折扣比例
        cbTongYi = findViewById(R.id.cb_unitfied_set);//统一设置开关
        cbXiangQiang = findViewById(R.id.cb_detail_set);//详情设置开关
        mRecyclerView = findViewById(R.id.rv_integral_set);

        cbTongYi.setChecked(true);
        cbXiangQiang.setChecked(false);
        edTongYi.setEnabled(true);
        mRecyclerView.setVisibility(View.INVISIBLE);
        cbTongYi.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
        cbXiangQiang.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
        cbTongYi.setTextColor(Color.parseColor("#FF8739"));
        cbXiangQiang.setTextColor(Color.parseColor("#333333"));
        edTongYi.setBackgroundResource(R.color.white);
    }

    private void listener() {
        findViewById(R.id.tv_back).setOnClickListener(this);
        findViewById(R.id.img_ok).setOnClickListener(this);
        cbTongYi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbXiangQiang.setChecked(false);
                    edTongYi.setEnabled(true);
                    mRecyclerView.setVisibility(View.INVISIBLE);
                    cbTongYi.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbXiangQiang.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbTongYi.setTextColor(Color.parseColor("#FF8739"));
                    cbXiangQiang.setTextColor(Color.parseColor("#333333"));
                    edTongYi.setBackgroundResource(R.color.white);
                    edTongYi.setText("100");
                }
            }
        });
        cbXiangQiang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbTongYi.setChecked(false);
                    mRecyclerView.setVisibility(View.VISIBLE);
                    edTongYi.setEnabled(false);
                    edTongYi.setText("");
                    edTongYi.setBackgroundResource(R.color.gray);
                    cbXiangQiang.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbTongYi.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbXiangQiang.setTextColor(Color.parseColor("#FF8739"));
                    cbTongYi.setTextColor(Color.parseColor("#333333"));
                }
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
                if (!TextUtils.isEmpty(s)) {
                    if (RegexUtil.isDis(s.toString())) {

                    } else {
                        edTongYi.setText("100");
//                        CustomToast.makeText(viewGroup.getContext(), "请输入1-100", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
