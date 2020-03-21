package com.zhiluo.android.yunpu.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.SystemParamsSetBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ZPH on 2019-02-17.
 * 支付设置界面
 */

public class PayParamsSetActivity extends BaseActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_system_set_save)
    TextView tvSystemSetSave;
    @Bind(R.id.img_save)
    ImageView imgSave;
    @Bind(R.id.cb_system_set_cash)
    CheckBox cbSystemSetCash;
    @Bind(R.id.cb_system_set_balance)
    CheckBox cbSystemSetBalance;
    @Bind(R.id.et_system_set_balance_max)
    EditText etSystemSetBalaceMax;
    @Bind(R.id.cb_system_set_wx)
    CheckBox cbSystemSetWx;
    @Bind(R.id.cb_system_set_zfb)
    CheckBox cbSystemSetZfb;
    @Bind(R.id.cb_system_set_yl)
    CheckBox cbSystemSetYl;
    @Bind(R.id.cb_system_set_yhq)
    CheckBox cbSystemSetYhq;
    @Bind(R.id.cb_system_set_sm)
    CheckBox cbSystemSetSm;
    @Bind(R.id.cb_system_set_other)
    CheckBox cbSystemSetOther;
    @Bind(R.id.cb_system_set_integral)
    CheckBox cbSystemSetIntegral;
    @Bind(R.id.et_system_set_integral)
    EditText etSystemSetIntegral;
    @Bind(R.id.cb_system_set_integral_limit)
    CheckBox cbSystemSetIntegralLimit;
    @Bind(R.id.et_system_set_integral_max)
    EditText etSystemSetIntegralMax;
    @Bind(R.id.cb_system_set_default)
    CheckBox cbSystemSetDefault;
    @Bind(R.id.img_go)
    ImageView imgGo;
    @Bind(R.id.tv_default_payway)
    TextView tvDefaultPayway;
    @Bind(R.id.r_default_payway)
    RelativeLayout rDefaultPayway;
    @Bind(R.id.ll_saoma_more)
    LinearLayout llSaomaMore;


    private Dialog chooseDialog;
    private SweetAlertDialog mSweetAlertDialog;
    private ArrayList<String> mDefaultPayList = new ArrayList<>();
    private ArrayList<SystemParamsSetBean> mSwitchList;//设置数据集合
    private String mDefaultPay;//默认支付方式
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_params_set);
        ButterKnife.bind(this);

        initData();
        setLisenter();
    }


    private void setLisenter() {

        //默认支付方式选择
        rDefaultPayway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(mDefaultPayList, tvDefaultPayway);
            }
        });
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set();
            }
        });
        cbSystemSetIntegral.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSystemSetIntegralMax.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                    etSystemSetIntegral.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                    cbSystemSetIntegralLimit.setClickable(true);
                } else {
                    etSystemSetIntegral.setInputType(InputType.TYPE_NULL);
                    etSystemSetIntegral.setText("");
                    cbSystemSetIntegralLimit.setChecked(false);
                    cbSystemSetIntegralLimit.setClickable(false);
                    etSystemSetIntegralMax.setInputType(InputType.TYPE_NULL);
                    etSystemSetIntegralMax.setText("");
                }
            }
        });
        cbSystemSetIntegralLimit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSystemSetIntegralMax.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                } else {
                    etSystemSetIntegralMax.setInputType(InputType.TYPE_NULL);
                    etSystemSetIntegralMax.setText("");
                }
            }
        });
        cbSystemSetBalance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etSystemSetBalaceMax.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                } else {
                    etSystemSetBalaceMax.setInputType(InputType.TYPE_NULL);
                    etSystemSetBalaceMax.setText("");
                }
            }
        });
        cbSystemSetDefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rDefaultPayway.setEnabled(true);
                } else {
                    rDefaultPayway.setEnabled(false);
                }
            }
        });

        llSaomaMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转扫呗支付详情
                Intent intent = new Intent(PayParamsSetActivity.this, QRPayDetail.class);
                startActivity(intent);
            }
        });

    }

    private void initData() {

        mDefaultPayList.add("现金支付");
        mDefaultPayList.add("余额支付");
        mDefaultPayList.add("微信记账");
        mDefaultPayList.add("支付宝记账");
        mDefaultPayList.add("银联支付");
        mDefaultPayList.add("扫码支付");
        mDefaultPayList.add("其他支付");


        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity == null) {
            getSwitch();
        } else {
            update(mSwitchEntity);
        }

        mSwitchList = new ArrayList<>();

    }

    /**
     * 更新显示
     */
    private void update(List<ReportMessageBean.DataBean.GetSysSwitchListBean> entity) {
        if (entity != null) {
            for (int i = 0; i < entity.size(); i++) {
                ReportMessageBean.DataBean.GetSysSwitchListBean bean = entity.get(i);

                switch (bean.getSS_Code()) {
                    case "101":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetCash.setChecked(true);
                        } else {
                            cbSystemSetCash.setChecked(false);
                        }
                        break;
                    case "102":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetBalance.setChecked(true);
                            etSystemSetBalaceMax.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                            etSystemSetBalaceMax.setText(bean.getSS_Value() + "");
                        } else {
                            cbSystemSetBalance.setChecked(false);
                            etSystemSetBalaceMax.setInputType(InputType.TYPE_NULL);
                            etSystemSetBalaceMax.setText("");
                        }
                        break;
                    case "105":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetWx.setChecked(true);
                        } else {
                            cbSystemSetWx.setChecked(false);
                        }
                        break;
                    case "106":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetZfb.setChecked(true);
                        } else {
                            cbSystemSetZfb.setChecked(false);
                        }
                        break;
                    case "103":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetYl.setChecked(true);
                        } else {
                            cbSystemSetYl.setChecked(false);
                        }
                        break;
                    case "110":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetYhq.setChecked(true);
                        } else {
                            cbSystemSetYhq.setChecked(false);
                        }
                        break;

                    case "111"://扫码支付
                        if (bean.getSS_State() == 1) {
                            cbSystemSetSm.setChecked(true);
                        } else {
                            cbSystemSetSm.setChecked(false);
                        }
                        break;
                    case "113"://其他支付
                        if (bean.getSS_State() == 1) {
                            cbSystemSetOther.setChecked(true);
                        } else {
                            cbSystemSetOther.setChecked(false);
                        }
                        break;
                    case "107":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetIntegral.setChecked(true);
                            etSystemSetIntegral.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                            etSystemSetIntegral.setText(bean.getSS_Value() + "");
                        } else {
                            cbSystemSetIntegral.setChecked(false);
                            etSystemSetIntegral.setInputType(InputType.TYPE_NULL);
                            etSystemSetIntegral.setText("");
                        }
                        break;
                    //积分支付限制
                    case "109":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetIntegralLimit.setChecked(true);
                            etSystemSetIntegralMax.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                            etSystemSetIntegralMax.setText(bean.getSS_Value() + "");
                        } else {
                            cbSystemSetIntegralLimit.setChecked(false);
                            etSystemSetIntegralMax.setInputType(InputType.TYPE_NULL);
                            etSystemSetIntegralMax.setText("");
                        }
                        break;
                    //默认支付
                    case "108":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetDefault.setChecked(true);
                            switch (String.valueOf(bean.getSS_Value())) {
                                case "YEZF":
                                    tvDefaultPayway.setText("余额支付");
                                    if (!cbSystemSetBalance.isChecked()) {
                                        tvDefaultPayway.setText("");
                                    }
                                    break;
                                case "XJZF":
                                    tvDefaultPayway.setText("现金支付");
                                    if (!cbSystemSetCash.isChecked()) {
                                        tvDefaultPayway.setText("");
                                    }
                                    break;
                                case "YLZF":
                                    tvDefaultPayway.setText("银联支付");
                                    if (!cbSystemSetYl.isChecked()) {
                                        tvDefaultPayway.setText("");
                                    }
                                    break;
                                case "WX_JZ":
                                    tvDefaultPayway.setText("微信记账");
                                    if (!cbSystemSetWx.isChecked()) {
                                        tvDefaultPayway.setText("");
                                    }
                                    break;
                                case "ZFB_JZ":
                                    tvDefaultPayway.setText("支付宝记账");
                                    if (!cbSystemSetZfb.isChecked()) {
                                        tvDefaultPayway.setText("");
                                    }
                                    break;
                            }
                        } else {
                            cbSystemSetDefault.setChecked(false);
                            rDefaultPayway.setEnabled(false);
                            tvDefaultPayway.setText("");
                        }
                        break;

                }
            }
        }
    }

    /**
     * 保存设置
     */
    private void set() {
        if (getSet()) {
            RequestParams params = new RequestParams();
            for (int i = 0; i < mSwitchList.size(); i++) {
                params.put("listSwitch[" + i + "][SS_Name]", mSwitchList.get(i).getSS_Name());
                params.put("listSwitch[" + i + "][SS_Code]", mSwitchList.get(i).getSS_Code());
                params.put("listSwitch[" + i + "][SS_State]", mSwitchList.get(i).getSS_State());
                if (mSwitchList.get(i).getSS_Value() != null) {
                    params.put("listSwitch[" + i + "][SS_Value]", mSwitchList.get(i).getSS_Value());
                }else {
                    params.put("listSwitch[" + i + "][SS_Value]", 100);
                }
            }
            Log.d("-------保存参数表单-----", "" + params.toString());

            Li("===============保存参数表单======== random:" + params);
            CallBack callBack = new CallBack() {
                @Override
                public void onSuccess(String responseString, Gson gson) {
                    mSweetAlertDialog = new SweetAlertDialog(PayParamsSetActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("设置系统参数");
                    mSweetAlertDialog.setContentText("成功");
                    mSweetAlertDialog.setConfirmText("确认");
                    mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mSweetAlertDialog.dismiss();
                            getSwitch();
                        }
                    });
                    mSweetAlertDialog.show();
                }

                @Override
                public void onFailure(String msg) {
                    CustomToast.makeText(PayParamsSetActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            };
            callBack.setLoadingAnimation(this, "保存设置中...", false);
            HttpHelper.post(this, "SetSwitch/EditSysSwitch", params, callBack);
        }
    }

    private void getSwitch() {
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean entity = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                mSwitchEntity = entity.getData().getGetSysSwitchList();
                Li("===============123123======== random:" + new Gson().toJson(mSwitchEntity));
                try {
                    //mSwitchEntity可能为空，或者里面的属性为空，这里要做异常处理
                    update(mSwitchEntity);
                } catch (NullPointerException e) {
                }
                CacheData.saveObject("switch", mSwitchEntity);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(PayParamsSetActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 获取参数设置
     */
    private boolean getSet() {
        mSwitchList.clear();
        if (cbSystemSetCash.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("现金支付", 101, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("现金支付", 101, 0));
        }
        if (cbSystemSetBalance.isChecked()) {
            if (!TextUtils.isEmpty(etSystemSetBalaceMax.getText())) {
                String value = etSystemSetBalaceMax.getText().toString();
                mSwitchList.add(new SystemParamsSetBean("余额支付", 102, 1, value));
            } else {
                CustomToast.makeText(this, "请输入余额支付限制比例", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            mSwitchList.add(new SystemParamsSetBean("余额支付", 102, 0));
        }
        if (cbSystemSetWx.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("微信记账", 105, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("微信记账", 105, 0));
        }
        if (cbSystemSetZfb.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("支付宝记账", 106, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("支付宝记账", 106, 0));
        }
        if (cbSystemSetYl.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("银联支付", 103, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("银联支付", 103, 0));
        }
        if (cbSystemSetYhq.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("优惠券", 110, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("优惠券", 110, 0));
        }
        if (cbSystemSetSm.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("扫码支付", 111, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("扫码支付", 111, 0));
        }
        if (cbSystemSetOther.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("其他支付", 113, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("其他支付", 113, 0));
        }

        if (cbSystemSetIntegral.isChecked()) {
            if (!TextUtils.isEmpty(etSystemSetIntegral.getText())) {
                String value = etSystemSetIntegral.getText().toString();
                mSwitchList.add(new SystemParamsSetBean("积分支付", 107, 1, value));
            } else {
                CustomToast.makeText(this, "请输入积分支付抵扣金额", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            mSwitchList.add(new SystemParamsSetBean("积分支付", 107, 0));
        }
        if (cbSystemSetIntegralLimit.isChecked()) {
            if (!TextUtils.isEmpty(etSystemSetIntegralMax.getText())) {
                String value = etSystemSetIntegralMax.getText().toString();
                mSwitchList.add(new SystemParamsSetBean("积分支付限制", 109, 1, value));
            } else {
                CustomToast.makeText(this, "请输入积分支付限制比例", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            mSwitchList.add(new SystemParamsSetBean("积分支付限制", 109, 0));
        }
        if (cbSystemSetDefault.isChecked()) {
            mDefaultPay = tvDefaultPayway.getText().toString();
            if (mDefaultPay != null && !"".equals(mDefaultPay)) {
                zfJudge();
                mSwitchList.add(new SystemParamsSetBean("默认支付", 108, 1, mDefaultPay));
            } else {
                CustomToast.makeText(this, "请选择默认支付方式", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            mSwitchList.add(new SystemParamsSetBean("默认支付", 108, 0));
        }

        return true;
    }


    private void zfJudge() {
        if (mDefaultPay.equals("现金支付")) {
            mDefaultPay = "XJZF";
        } else if (mDefaultPay.equals("余额支付")) {
            mDefaultPay = "YEZF";
        } else if (mDefaultPay.equals("银联支付")) {
            mDefaultPay = "YLZF";
        } else if (mDefaultPay.equals("微信记账")) {
            mDefaultPay = "WX_JZ";
        } else if (mDefaultPay.equals("支付宝记账")) {
            mDefaultPay = "ZFB_JZ";
        } else if (mDefaultPay.equals("扫码支付")) {
            mDefaultPay = "SMZF";
        } else if (mDefaultPay.equals("其他支付")) {
            mDefaultPay = "QTZF";
        }

    }

    /**
     * chooseDialog
     */
    private void showChooseDialog(List<String> mlist, final TextView mTextViews) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {//设置默认值
            if (mTextViews.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        Li("===============默认支付======== random:" + itemValue);
                        if (itemValue.equals("现金支付") && !cbSystemSetCash.isChecked()) {
                            CustomToast.makeText(PayParamsSetActivity.this, "请勾选现金支付", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (itemValue.equals("余额支付") && !cbSystemSetBalance.isChecked()) {
                            CustomToast.makeText(PayParamsSetActivity.this, "请勾选余额支付", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (itemValue.equals("微信记账") && !cbSystemSetWx.isChecked()) {
                            CustomToast.makeText(PayParamsSetActivity.this, "请勾选微信支付", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (itemValue.equals("支付宝记账") && !cbSystemSetZfb.isChecked()) {
                            CustomToast.makeText(PayParamsSetActivity.this, "请勾选支付宝支付", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (itemValue.equals("银联支付") && !cbSystemSetYl.isChecked()) {
                            CustomToast.makeText(PayParamsSetActivity.this, "请勾选银联支付", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (itemValue.equals("扫码支付") && !cbSystemSetSm.isChecked()) {
                            CustomToast.makeText(PayParamsSetActivity.this, "请勾选扫码支付", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (itemValue.equals("其他支付") && !cbSystemSetOther.isChecked()) {
                            CustomToast.makeText(PayParamsSetActivity.this, "请勾选其他支付", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mTextViews.setText(itemValue);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

}
