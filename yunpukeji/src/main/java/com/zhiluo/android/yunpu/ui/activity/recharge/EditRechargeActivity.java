package com.zhiluo.android.yunpu.ui.activity.recharge;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.RechargeBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class EditRechargeActivity extends AppCompatActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_edit_recharge_save)
    TextView tvSave;
    @Bind(R.id.et_edit_recharge_name)
    EditText etName;
    @Bind(R.id.et_edit_recharge_money)
    EditText etMoney;
    @Bind(R.id.et_edit_recharge_give_money)
    EditText etGiveMoney;
    @Bind(R.id.et_edit_recharge_give_integral)
    EditText etIntegral;
    @Bind(R.id.rb_edit_recharge_forever)
    RadioButton rbForever;
    @Bind(R.id.rb_edit_recharge_birthday)
    RadioButton rbBirthday;
    @Bind(R.id.rb_edit_recharge_fixed)
    RadioButton rbFixed;
    @Bind(R.id.rb_edit_recharge_week)
    RadioButton rbWeek;
    @Bind(R.id.rb_edit_recharge_month)
    RadioButton rbMonth;
    @Bind(R.id.rg_edit_recharge)
    RadioGroup mRadioGroup;
    @Bind(R.id.tv_edit_recharge_start)
    TextView tvStart;
    @Bind(R.id.tv_edit_recharge_end)
    TextView tvEnd;
    @Bind(R.id.et_edit_recharge_week)
    EditText etWeek;
    @Bind(R.id.et_edit_recharge_month)
    EditText etMonth;
    private int mType;
    private SweetAlertDialog mSweetAlertDialog;
    private RechargeBean mRechargeBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recharge);
        ButterKnife.bind(this);
        mRechargeBean = (RechargeBean) getIntent().getSerializableExtra("RE");
        setListener();
        init();
    }

    private void init() {
        etWeek.setBackgroundColor(getResources().getColor(R.color.background));
        etMonth.setBackgroundColor(getResources().getColor(R.color.background));
        etWeek.setEnabled(false);
        etMonth.setEnabled(false);

        if (mRechargeBean != null) {
            etName.setText(mRechargeBean.getRP_Name());
            etMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mRechargeBean.getRP_RechargeMoney()));
            etGiveMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mRechargeBean.getRP_GiveMoney()));
            etIntegral.setText("" + mRechargeBean.getRP_GivePoint());
            switch (mRechargeBean.getRP_ValidType()) {
                case 0:
                    rbForever.setChecked(true);
                    break;
                case 1:
                    String start = mRechargeBean.getRP_ValidStartTime();
                    String end = mRechargeBean.getRP_ValidEndTime();
                    if (start.length() > 10) {
                        start = start.substring(0, 10);
                    }
                    if (end.length() > 10) {
                        end = end.substring(0, 10);
                    }
                    rbFixed.setChecked(true);
                    tvStart.setText(start);
                    tvEnd.setText(end);
                    tvStart.setClickable(true);
                    tvEnd.setClickable(true);
                    break;
                case 2:
                    rbWeek.setChecked(true);
                    etWeek.setText("" + mRechargeBean.getRP_ValidWeekMonth());
                    break;
                case 3:
                    rbMonth.setChecked(true);
                    etMonth.setText("" + mRechargeBean.getRP_ValidWeekMonth());
                    break;
                case 4:
                    rbBirthday.setChecked(true);
                    break;
            }
        }
    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify()) {
                    save();
                }
            }
        });
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceStart();
            }
        });
        tvEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceEnd();
            }
        });
        tvStart.setClickable(false);
        tvEnd.setClickable(false);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_edit_recharge_forever:
                        etWeek.setBackgroundColor(getResources().getColor(R.color.background));
                        etMonth.setBackgroundColor(getResources().getColor(R.color.background));
                        etWeek.setEnabled(false);
                        etMonth.setEnabled(false);
                        etWeek.setText("");
                        etMonth.setText("");
                        tvStart.setText("");
                        tvEnd.setText("");
                        tvStart.setClickable(false);
                        tvEnd.setClickable(false);
                        mType = 0;
                        break;
                    case R.id.rb_edit_recharge_birthday:
                        etWeek.setBackgroundColor(getResources().getColor(R.color.background));
                        etMonth.setBackgroundColor(getResources().getColor(R.color.background));
                        etWeek.setEnabled(false);
                        etMonth.setEnabled(false);
                        etWeek.setText("");
                        etMonth.setText("");
                        tvStart.setText("");
                        tvEnd.setText("");
                        tvStart.setClickable(false);
                        tvEnd.setClickable(false);
                        mType = 4;
                        break;
                    case R.id.rb_edit_recharge_fixed:
                        etWeek.setBackgroundColor(getResources().getColor(R.color.background));
                        etMonth.setBackgroundColor(getResources().getColor(R.color.background));
                        etWeek.setEnabled(false);
                        etMonth.setEnabled(false);
                        etWeek.setText("");
                        etMonth.setText("");
                        tvStart.setClickable(true);
                        tvEnd.setClickable(true);
                        mType = 1;
                        break;
                    case R.id.rb_edit_recharge_week:
                        etWeek.setBackgroundResource(R.drawable.common_edit_background);
                        etMonth.setBackgroundColor(getResources().getColor(R.color.background));
                        etWeek.setEnabled(true);
                        etMonth.setEnabled(false);
                        etMonth.setText("");
                        tvStart.setText("");
                        tvEnd.setText("");
                        tvStart.setClickable(false);
                        tvEnd.setClickable(false);
                        mType = 2;
                        break;
                    case R.id.rb_edit_recharge_month:
                        etWeek.setBackgroundColor(getResources().getColor(R.color.background));
                        etMonth.setBackgroundResource(R.drawable.common_edit_background);
                        etWeek.setEnabled(false);
                        etMonth.setEnabled(true);
                        etWeek.setText("");
                        tvStart.setText("");
                        tvEnd.setText("");
                        tvStart.setClickable(true);
                        tvEnd.setClickable(true);
                        mType = 3;
                        break;
                }
            }
        });
    }

    private boolean verify() {
        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(this, "请输入充值名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etMoney.getText())) {
            CustomToast.makeText(this, "请输入充值金额", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etGiveMoney.getText())) {
            CustomToast.makeText(this, "请输入赠送金额", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etIntegral.getText())) {
            CustomToast.makeText(this, "请输入赠送积分", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (rbFixed.isChecked()) {
            if (TextUtils.isEmpty(tvStart.getText())) {
                CustomToast.makeText(this, "请选择开始时间", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(tvEnd.getText())) {
                CustomToast.makeText(this, "请选择结束时间", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (rbWeek.isChecked()) {
            if (TextUtils.isEmpty(etWeek.getText())) {
                CustomToast.makeText(this, "请输入星期", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (rbMonth.isChecked()) {
            if (TextUtils.isEmpty(etMonth.getText())) {
                CustomToast.makeText(this, "请输入天", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    /**
     * 保存
     */
    private void save() {
        RequestParams params = new RequestParams();
        params.put("GID", mRechargeBean.getGID());
        params.put("RP_Type", 1);
        params.put("RP_ValidType", mType);
        params.put("RP_Name", etName.getText().toString());
        params.put("RP_RechargeMoney", etMoney.getText().toString());
        params.put("RP_GiveMoney", etGiveMoney.getText().toString());
        params.put("RP_ReduceMoney", 0);
        params.put("RP_GivePoint", etIntegral.getText().toString());
        if (rbWeek.isChecked()) {
            params.put("RP_ValidWeekMonth", etWeek.getText().toString());
        } else if (rbMonth.isChecked()) {
            params.put("RP_ValidWeekMonth", etMonth.getText().toString());
        } else {
            params.put("RP_ValidWeekMonth", "");
        }
        params.put("RP_ValidStartTime", tvStart.getText().toString());
        params.put("RP_ValidEndTime", tvEnd.getText().toString());
        params.put("RP_ISDouble", 0);
        params.put("RP_IsOpen", 1);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(EditRechargeActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("编辑成功");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        finish();
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditRechargeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, "RechargePackage/Edit", params, callBack);
    }

    /**
     * 弹出时间选择器，用于选择过期时间
     */
    protected void choiceStart() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(
                EditRechargeActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int momth = monthOfYear + 1;
                EditRechargeActivity.this.tvStart.setText(year + "-" + momth + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        DatePicker dp = datePickerDialog.getDatePicker();
        dp.setMinDate(System.currentTimeMillis());//设置可选的最小时间
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.show();
    }

    /**
     * 弹出时间选择器，用于选择过期时间
     */
    protected void choiceEnd() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(
                EditRechargeActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int momth = monthOfYear + 1;
                EditRechargeActivity.this.tvEnd.setText(year + "-" + momth + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        DatePicker dp = datePickerDialog.getDatePicker();
        dp.setMinDate(System.currentTimeMillis());//设置可选的最小时间
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
            mSweetAlertDialog = null;
        }
    }
}
