package com.zhiluo.android.yunpu.ui.activity.timesrule;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ZPH on 2018-11-30.
 */

public class EditRulesActivity extends BaseActivity {
    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.recharge_title)
    TextView rechargeTitle;
    @Bind(R.id.tv_add_recharge_save)
    TextView tvAddRechargeSave;
    @Bind(R.id.et_add_recharge_name)
    EditText etAddRechargeName;
    @Bind(R.id.et_add_recharge_money)
    EditText etAddRechargeMoney;
    @Bind(R.id.cb_day)
    CheckBox cbDay;
    @Bind(R.id.cb_week)
    CheckBox cbWeek;
    @Bind(R.id.cb_month)
    CheckBox cbMonth;
    @Bind(R.id.cb_year)
    CheckBox cbYear;

    private int mType;//1 天 2周 3 月 4年
    private SweetAlertDialog mSweetAlertDialog;
    private TimesRuleManagerBean.DataBean.DataListBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_times_rule);
        ButterKnife.bind(this);

        loadData();
        setLisenter();
    }

    private void loadData() {
        bean = (TimesRuleManagerBean.DataBean.DataListBean) getIntent().getSerializableExtra("rule");
        if (bean!=null){
            etAddRechargeName.setText(bean.getWR_Name()+"");
            etAddRechargeMoney.setText(bean.getWR_ConsumeRule());
            mType = bean.getWR_RegularUnit();
            if (bean.getWR_RegularUnit() ==1){
                cbDay.setChecked(true);
                cbDay.setTextColor(Color.parseColor("#FF8739"));
                cbDay.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
            }else if (bean.getWR_RegularUnit() ==2){
                cbWeek.setChecked(true);
                cbWeek .setTextColor(Color.parseColor("#FF8739"));
                cbWeek.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
            }else if (bean.getWR_RegularUnit() ==3){
                cbMonth.setChecked(true);
                cbMonth.setTextColor(Color.parseColor("#FF8739"));
                cbMonth.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
            }else if (bean.getWR_RegularUnit() ==4){
                cbYear.setChecked(true);
                cbYear.setTextColor(Color.parseColor("#FF8739"));
                cbYear.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
            }
        }
        rechargeTitle.setText("编辑计次规则");
    }

    private void setLisenter() {

        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etAddRechargeMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("")||Integer.parseInt(s.toString())<1){
                    etAddRechargeMoney.setText("1");
                    CustomToast.makeText(EditRulesActivity.this,"请填写大于0整数",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cbDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbDay.isChecked()) {
                    cbWeek.setChecked(false);
                    cbMonth.setChecked(false);
                    cbYear.setChecked(false);
                    cbDay.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbDay.setTextColor(Color.parseColor("#FF8739"));

                    mType = 1;
                } else {
                    cbDay.setChecked(false);
                    cbDay.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbDay.setTextColor(Color.parseColor("#333333"));
                }
            }
        });
        cbWeek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbWeek.isChecked()) {
                    cbDay.setChecked(false);
                    cbMonth.setChecked(false);
                    cbYear.setChecked(false);
                    cbWeek.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbWeek.setTextColor(Color.parseColor("#FF8739"));

                    mType = 2;
                } else {
                    cbWeek.setChecked(false);
                    cbWeek.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbWeek.setTextColor(Color.parseColor("#333333"));
                }
            }
        });
        cbMonth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbMonth.isChecked()) {
                    cbWeek.setChecked(false);
                    cbDay.setChecked(false);
                    cbYear.setChecked(false);
                    cbMonth.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbMonth.setTextColor(Color.parseColor("#FF8739"));

                    mType = 3;
                } else {
                    cbMonth.setChecked(false);
                    cbMonth.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbMonth.setTextColor(Color.parseColor("#333333"));
                }
            }
        });
        cbYear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbYear.isChecked()) {
                    cbWeek.setChecked(false);
                    cbMonth.setChecked(false);
                    cbDay.setChecked(false);
                    cbYear.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbYear.setTextColor(Color.parseColor("#FF8739"));

                    mType = 4;
                } else {
                    cbYear.setChecked(false);
                    cbYear.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbYear.setTextColor(Color.parseColor("#333333"));
                }
            }
        });

        tvAddRechargeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etAddRechargeName.getText())){
                    CustomToast.makeText(EditRulesActivity.this,"规则名称不能为空", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(etAddRechargeMoney.getText())){
                    CustomToast.makeText(EditRulesActivity.this,"消费次数不能为空",Toast.LENGTH_SHORT).show();
                }else if (cbDay.isChecked()||cbMonth.isChecked()||cbWeek.isChecked()||cbYear.isChecked()){
                    EditRule();
                }else {
                    CustomToast.makeText(EditRulesActivity.this,"请选择消费规则",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //编辑计次
    private void EditRule(){
        RequestParams params = new RequestParams();
        params.put("WR_Name",etAddRechargeName.getText().toString());
        params.put("WR_ConsumeRule",etAddRechargeMoney.getText().toString());
        params.put("WR_RegularUnit",mType);
        params.put("GID",bean.getGID());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(EditRulesActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("新增成功");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        ActivityManager.getInstance().exit();
                        Intent intent = new Intent(EditRulesActivity.this,TimesRuleManagerActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditRulesActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(EditRulesActivity.this, HttpAPI.API().EDIT_RULES,params,callBack);
    }
}
