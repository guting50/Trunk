package com.zhiluo.android.yunpu.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
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

public class SystemParamsSetActivity extends AppCompatActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_system_set_save)
    TextView tvSystemSetSave;

    //    Spinner spSystemSetDefaultPayWay;
    @Bind(R.id.cb_system_set_card_equal_phone)
    CheckBox cbSystemSetCardEqualPhone;
    @Bind(R.id.cb_system_set_fixed_card)
    CheckBox cbSystemSetFixedCard;
    @Bind(R.id.cb_system_set_toast)
    CheckBox cbSystemSetToast;
    @Bind(R.id.cb_system_set_staff_commission)
    CheckBox cbSystemSetStaffCommission;
    @Bind(R.id.cb_system_set_modify_money)
    CheckBox cbSystemSetModifyMoney;
    @Bind(R.id.cb_system_set_init_paw)
    CheckBox cbSystemSetInitPaw;
    @Bind(R.id.et_system_set_init_paw)
    EditText etSystemSetInitPaw;
    @Bind(R.id.cb_system_set_consume_paw)
    CheckBox cbSystemSetConsumePaw;
    @Bind(R.id.cb_system_set_zhuanzhang_paw)
    CheckBox cbSystemSetZhuanzhangPaw;
    @Bind(R.id.cb_system_set_exchange_paw)
    CheckBox cbSystemSetExchangePaw;
    @Bind(R.id.cb_system_set_huanka_paw)
    CheckBox cbSystemSetHuankaPaw;
    //    @Bind(R.id.cb_system_set_hytj)
//    CheckBox cbSystemSetHytj;
//    @Bind(R.id.et_system_set_hytj)
//    EditText etSystemSetHytj;
//    @Bind(R.id.cb_system_set_consume_fl)
//    CheckBox cbSystemSetConsumeFl;
//    @Bind(R.id.et_system_set_consume_fl)
//    EditText etSystemSetConsumeFl;
//    @Bind(R.id.cb_system_set_integral_fl)
//    CheckBox cbSystemSetIntegralFl;
//    @Bind(R.id.et_system_set_integral_fl)
//    EditText etSystemSetIntegralFl;
    @Bind(R.id.cb_system_set_istel)
    CheckBox cbSystemSetIstel;
    @Bind(R.id.cb_system_set_issamenumber)
    CheckBox cbIssamenumber;
    @Bind(R.id.cb_system_set_timesrule)
    CheckBox cbTimesrule;
    @Bind(R.id.cb_system_set_cancel_paw)
    CheckBox cbCancelPaw;
    @Bind(R.id.et_system_set_cansel_paw)
    EditText etCanselPaw;
    @Bind(R.id.cb_mastto_card)
    CheckBox cbMasttoCard;
    @Bind(R.id.cb_system_set_ismember)
    CheckBox cbSystemSetIsmember;
    @Bind(R.id.cb_zdppyyhd)
    CheckBox cbZdppyyhd;
    @Bind(R.id.cb_no_zero)
    CheckBox cbNoZero;
    @Bind(R.id.cb_fading_double_integral)
    CheckBox cbFadingDoubleIntegral;
    @Bind(R.id.cb_zero_fredom)
    CheckBox cbZeroFredom;
    @Bind(R.id.cb_zero_yuan)
    CheckBox cbZeroYuan;
    @Bind(R.id.cb_zero_jiao)
    CheckBox cbZeroJiao;
    @Bind(R.id.cb_zero_jiao_no)
    CheckBox cbZeroJiaoNo;
    @Bind(R.id.cb_zero_fen_no)
    CheckBox cbZeroFenNo;


    private List<String> mPayWaymList = new ArrayList<>();
    private ArrayList<SystemParamsSetBean> mSwitchList;//设置数据集合
    private String mDefaultPay;//默认支付方式
    private ArrayList<String> mDefaultPayList = new ArrayList<>();
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private ArrayAdapter<String> mPayWaySpinnerAdapter;
    private SweetAlertDialog mSweetAlertDialog;
    private Dialog chooseDialog;

    /**请求保存设置中就不让点(连点会出现多次请求，成功弹框关闭不了) 20191018 cy */
    private boolean mIsSave;
    /************************************************************* 20191018 cy */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_params_set);
        ButterKnife.bind(this);
        init();
        setListener();

    }

    private void init() {
        mDefaultPayList.add("现金支付");
        mDefaultPayList.add("余额支付");
        mDefaultPayList.add("银联支付");
        mDefaultPayList.add("微信记账");
        mDefaultPayList.add("支付宝记账");
        mPayWaySpinnerAdapter = new ArrayAdapter<String>(SystemParamsSetActivity.this, R.layout.item_spinner, R.id.tv_item_spinner, mDefaultPayList);

        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity == null) {
            getSwitch();
        } else {
            update(mSwitchEntity);
        }
        String[] data = getResources().getStringArray(R.array.paywaylist);
        for (String str : data) {
            mPayWaymList.add(str);
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
                Log.d("update", bean.getSS_Code() + bean.getSS_Name());
                switch (bean.getSS_Code()) {
                    //会员卡号同手机号
                    case "201":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetCardEqualPhone.setChecked(true);
                        } else {
                            cbSystemSetCardEqualPhone.setChecked(false);
                        }
                        break;
                    //会员卡卡面号码
                    case "208":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetFixedCard.setChecked(true);
                        } else {
                            cbSystemSetFixedCard.setChecked(false);
                        }
                        break;
                    //会员查询弹窗
                    case "209":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetToast.setChecked(true);
                        } else {
                            cbSystemSetToast.setChecked(false);
                        }
                        break;

                    //是否显示他店会员
                    case "210":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetIsmember.setChecked(true);
                        } else {
                            cbSystemSetIsmember.setChecked(false);
                        }
                        break;
                    //是否必填手机号
                    case "211":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetIstel.setChecked(true);
                        } else {
                            cbSystemSetIstel.setChecked(false);
                        }
                        break;

                    //计次消费规则
                    case "212":
                        if (bean.getSS_State() == 1) {
                            cbTimesrule.setChecked(true);
                        } else {
                            cbTimesrule.setChecked(false);
                        }
                        break;
                    //手机号可重复
                    case "213":
                        if (bean.getSS_State() == 1) {
                            cbIssamenumber.setChecked(true);
                        } else {
                            cbIssamenumber.setChecked(false);
                        }
                        break;

                    //自动匹配优惠活动
                    case "216":
                        if (bean.getSS_State() == 1) {
                            cbZdppyyhd.setChecked(true);
                        } else {
                            cbZdppyyhd.setChecked(false);
                        }
                        break;
                       //禁止0库存销售
                    case "602":
                        if (bean.getSS_State() == 1) {
                            cbNoZero.setChecked(true);
                        } else {
                            cbNoZero.setChecked(false);
                        }
                        break;
                        //法定假日积分翻倍
                    case "305":
                        if (bean.getSS_State() == 1) {
                            cbFadingDoubleIntegral.setChecked(true);
                        } else {
                            cbFadingDoubleIntegral.setChecked(false);
                        }
                        break;
                        //自由抹零
                    case "801":
                        if (bean.getSS_State() == 1) {
                            cbZeroFredom.setChecked(true);
                        } else {
                            cbZeroFredom.setChecked(false);
                        }
                        break;
                        //四舍五入到元
                    case "803":
                        if (bean.getSS_State() == 1) {
                            cbZeroYuan.setChecked(true);
                        } else {
                            cbZeroYuan.setChecked(false);
                        }
                        break;
                        //四舍五入到角
                    case "802":
                        if (bean.getSS_State() == 1) {
                            cbZeroJiao.setChecked(true);
                        } else {
                            cbZeroJiao.setChecked(false);
                        }
                        break;
                        //直接舍弃角
                    case "804":
                        if (bean.getSS_State() == 1) {
                            cbZeroJiaoNo.setChecked(true);
                        } else {
                            cbZeroJiaoNo.setChecked(false);
                        }
                        break;
                        //直接舍弃分
                    case "805":
                        if (bean.getSS_State() == 1) {
                            cbZeroFenNo.setChecked(true);
                        } else {
                            cbZeroFenNo.setChecked(false);
                        }
                        break;


                    //员工提成
                    case "301":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetStaffCommission.setChecked(true);
                        } else {
                            cbSystemSetStaffCommission.setChecked(false);
                        }
                        break;
                    //折后金额修改
                    case "601":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetModifyMoney.setChecked(true);
                        } else {
                            cbSystemSetModifyMoney.setChecked(false);
                        }
                        break;
                    //开卡初始密码
                    case "202":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetInitPaw.setChecked(true);
                            etSystemSetInitPaw.setText(bean.getSS_Value() + "");
                            cbSystemSetConsumePaw.setClickable(true);
                            cbSystemSetZhuanzhangPaw.setClickable(true);
                            cbSystemSetExchangePaw.setClickable(true);
                            cbSystemSetHuankaPaw.setClickable(true);
                        } else {
                            cbSystemSetInitPaw.setChecked(false);
                            etSystemSetInitPaw.setText("");
                            cbSystemSetConsumePaw.setChecked(false);
                            cbSystemSetZhuanzhangPaw.setChecked(false);
                            cbSystemSetExchangePaw.setChecked(false);
                            cbSystemSetHuankaPaw.setChecked(false);

                            cbSystemSetConsumePaw.setClickable(false);
                            cbSystemSetZhuanzhangPaw.setClickable(false);
                            cbSystemSetExchangePaw.setClickable(false);
                            cbSystemSetHuankaPaw.setClickable(false);
                        }
                        break;
                    //消费密码验证
                    case "204":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetConsumePaw.setChecked(true);
                        } else {
                            cbSystemSetConsumePaw.setChecked(false);
                        }
                        break;
                    //转账密码验证
                    case "205":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetZhuanzhangPaw.setChecked(true);
                        } else {
                            cbSystemSetZhuanzhangPaw.setChecked(false);
                        }
                        break;
                    //兑换密码验证
                    case "206":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetExchangePaw.setChecked(true);
                        } else {
                            cbSystemSetExchangePaw.setChecked(false);
                        }
                        break;
                    //撤单密码
                    case "215":
                        if (bean.getSS_State() == 1) {
                            cbCancelPaw.setChecked(true);
                            etCanselPaw.setText(bean.getSS_Value() + "");
                            MyApplication.IS_CANCEL_ORDER = true;
                            MyApplication.CANCEL_PAW = bean.getSS_Value();
                        } else {
                            cbCancelPaw.setChecked(false);
                            etCanselPaw.setText("");
                            MyApplication.IS_CANCEL_ORDER = false;
                        }
                        break;
                    //换卡密码验证
                    case "207":
                        if (bean.getSS_State() == 1) {
                            cbSystemSetHuankaPaw.setChecked(true);
                        } else {
                            cbSystemSetHuankaPaw.setChecked(false);
                        }
                        break;
//                    //会员推荐提成
//                    case "503":
//                        if (bean.getSS_State() == 1) {
//                            cbSystemSetHytj.setChecked(true);
//                            etSystemSetHytj.setText(bean.getSS_Value() + "");
//                        } else {
//                            cbSystemSetHytj.setChecked(false);
//                            cbSystemSetConsumeFl.setChecked(false);
//                            cbSystemSetIntegralFl.setChecked(false);
//                        }
//                        break;
//                    //会员消费提成
//                    case "501":
//                        if (bean.getSS_State() == 1) {
//                            cbSystemSetConsumeFl.setChecked(true);
//                            etSystemSetConsumeFl.setText(bean.getSS_Value() + "");
//                        } else {
//                            cbSystemSetConsumeFl.setChecked(false);
//                        }
//                        break;
//                    //会员获得积分提成
//                    case "502":
//                        if (bean.getSS_State() == 1) {
//                            cbSystemSetIntegralFl.setChecked(true);
//                            etSystemSetIntegralFl.setText(bean.getSS_Value() + "");
//                        } else {
//                            cbSystemSetIntegralFl.setChecked(false);
//                        }
//                        break;
                    //必须刷卡
                    case "214":
                        if (bean.getSS_State() == 1) {
                            cbMasttoCard.setChecked(true);

                        } else {
                            cbMasttoCard.setChecked(false);
                        }
                        break;
                }
            }
        }
    }

    private void setListener() {
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.img_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set();
            }
        });
        cbSystemSetInitPaw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbSystemSetConsumePaw.setClickable(true);
                    cbSystemSetZhuanzhangPaw.setClickable(true);
                    cbSystemSetExchangePaw.setClickable(true);
                    cbSystemSetHuankaPaw.setClickable(true);
                } else {
                    cbSystemSetConsumePaw.setClickable(false);
                    cbSystemSetZhuanzhangPaw.setClickable(false);
                    cbSystemSetExchangePaw.setClickable(false);
                    cbSystemSetHuankaPaw.setClickable(false);

                    cbSystemSetConsumePaw.setChecked(false);
                    cbSystemSetZhuanzhangPaw.setChecked(false);
                    cbSystemSetExchangePaw.setChecked(false);
                    cbSystemSetHuankaPaw.setChecked(false);
                }
            }
        });
//        cbSystemSetHytj.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    cbSystemSetConsumeFl.setClickable(true);
//                    cbSystemSetIntegralFl.setClickable(true);
//                } else {
//                    cbSystemSetConsumeFl.setChecked(false);
//                    cbSystemSetIntegralFl.setChecked(false);
//                    cbSystemSetConsumeFl.setClickable(false);
//                    cbSystemSetIntegralFl.setClickable(false);
//                }
//            }
//        });
//        cbSystemSetConsumeFl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    cbSystemSetIntegralFl.setChecked(false);
//                } else {
//                }
//            }
//        });
//        cbSystemSetIntegralFl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    cbSystemSetConsumeFl.setChecked(false);
//                } else {
//                }
//            }
//        });
        cbIssamenumber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbSystemSetCardEqualPhone.setChecked(false);
                }
            }
        });
        cbSystemSetCardEqualPhone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbIssamenumber.setChecked(false);
                }
            }
        });


        cbZeroFredom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbZeroYuan.setChecked(false);
                    cbZeroJiao.setChecked(false);
                    cbZeroJiaoNo.setChecked(false);
                    cbZeroFenNo.setChecked(false);
                }
            }
        });

        cbZeroYuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbZeroFredom.setChecked(false);
                    cbZeroJiao.setChecked(false);
                    cbZeroJiaoNo.setChecked(false);
                    cbZeroFenNo.setChecked(false);
                }
            }
        });

        cbZeroJiao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbZeroYuan.setChecked(false);
                    cbZeroFredom.setChecked(false);
                    cbZeroJiaoNo.setChecked(false);
                    cbZeroFenNo.setChecked(false);
                }
            }
        });

        cbZeroJiaoNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbZeroYuan.setChecked(false);
                    cbZeroJiao.setChecked(false);
                    cbZeroFredom.setChecked(false);
                    cbZeroFenNo.setChecked(false);
                }
            }
        });

        cbZeroFenNo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbZeroYuan.setChecked(false);
                    cbZeroJiao.setChecked(false);
                    cbZeroJiaoNo.setChecked(false);
                    cbZeroFredom.setChecked(false);
                }
            }
        });

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
                        mTextViews.setText(itemValue);
                        Li(itemValue);

                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    private void getSwitch() {
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean entity = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                mSwitchEntity = entity.getData().getGetSysSwitchList();

                try {
                    //mSwitchEntity可能为空，或者里面的属性为空，这里要做异常处理
                    update(mSwitchEntity);
                } catch (NullPointerException e) {
                }
                CacheData.saveObject("switch", mSwitchEntity);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(SystemParamsSetActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 保存设置
     */
    private void set() {
        /**请求保存设置中就不让点 20191018 cy */
        if(mIsSave){
            return;
        }
        /************************20191018 cy */
        if (getSet()) {
            /**请求保存设置中就不让点 20191018 cy */
            mIsSave = true;
            /************************20191018 cy */
            RequestParams params = new RequestParams();
            for (int i = 0; i < mSwitchList.size(); i++) {
                params.put("listSwitch[" + i + "][SS_Name]", mSwitchList.get(i).getSS_Name());
                params.put("listSwitch[" + i + "][SS_Code]", mSwitchList.get(i).getSS_Code());
                params.put("listSwitch[" + i + "][SS_State]", mSwitchList.get(i).getSS_State());
                if (mSwitchList.get(i).getSS_Value() != null) {
                    params.put("listSwitch[" + i + "][SS_Value]", mSwitchList.get(i).getSS_Value());
                }
            }
            Li("-------保存参数表单-----" + params.toString());
            CallBack callBack = new CallBack() {
                @Override
                public void onSuccess(String responseString, Gson gson) {
                    mSweetAlertDialog = new SweetAlertDialog(SystemParamsSetActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("设置系统参数");
                    mSweetAlertDialog.setContentText("成功");
                    mSweetAlertDialog.setConfirmText("确认");
                    mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mSweetAlertDialog.dismiss();
                            getSwitch();
                            /**请求保存设置中就不让点 20191018 cy */
                            mIsSave = false;
                            /*********************** 20191018 cy */
                        }
                    });
                    mSweetAlertDialog.show();
                }

                @Override
                public void onFailure(String msg) {
                    CustomToast.makeText(SystemParamsSetActivity.this, msg, Toast.LENGTH_SHORT).show();
                    /**请求保存设置中就不让点 20191018*/
                    mIsSave = false;
                    /*********************** 20191018*/
                }
            };
            callBack.setLoadingAnimation(this, "保存设置中...", false);
            HttpHelper.post(this, "SetSwitch/EditSysSwitch", params, callBack);
        }
    }

    /**
     * 获取参数设置
     */
    private boolean getSet() {
        mSwitchList.clear();

        if (cbSystemSetCardEqualPhone.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("会员卡号同手机号", 201, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("会员卡号同手机号", 201, 0));
        }


        if (cbSystemSetIsmember.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("是否显示他店会员", 210, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("是否显示他店会员", 210, 0));
        }
        if (cbSystemSetIstel.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("是否必填手机号", 211, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("是否必填手机号", 211, 0));
        }
        if (cbTimesrule.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("计次消费规则", 212, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("计次消费规则", 212, 0));
        }
        if (cbMasttoCard.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("必须刷卡", 214, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("必须刷卡", 214, 0));
        }



        if (cbZdppyyhd.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("自动匹配优惠活动", 216, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("自动匹配优惠活动", 216, 0));
        }
        if (cbNoZero.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("禁止0库存销售", 602, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("禁止0库存销售", 602, 0));
        }
        if (cbFadingDoubleIntegral.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("法定假日积分翻倍", 305, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("法定假日积分翻倍", 305, 0));
        }
        if (cbZeroFredom.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("自由抹零", 801, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("自由抹零", 801, 0));
        }
        if (cbZeroYuan.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("四舍五入到元", 803, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("四舍五入到元", 803, 0));
        }
        if (cbZeroJiao.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("四舍五入到角", 802, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("四舍五入到角", 802, 0));
        }
        if (cbZeroJiaoNo.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("直接舍弃角", 804, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("直接舍弃角", 804, 0));
        }
        if (cbZeroFenNo.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("直接舍弃分", 805, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("直接舍弃分", 805, 0));
        }




        if (cbIssamenumber.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("手机号可重复", 213, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("手机号可重复", 213, 0));
        }

        if (cbSystemSetFixedCard.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("会员卡卡面卡号", 208, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("会员卡卡面卡号", 208, 0));
        }
        if (cbSystemSetToast.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("会员查询弹窗", 209, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("会员查询弹窗", 209, 0));
        }
        if (cbSystemSetStaffCommission.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("员工提成", 301, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("员工提成", 301, 0));
        }
        if (cbSystemSetModifyMoney.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("折后金额修改", 601, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("折后金额修改", 601, 0));
        }

        if (cbCancelPaw.isChecked()) {
            if (!TextUtils.isEmpty(etCanselPaw.getText())) {
                if (etCanselPaw.getText().toString().length() > 3) {
                    String value = etCanselPaw.getText().toString();
                    mSwitchList.add(new SystemParamsSetBean("撤单密码", 215, 1, value));
                } else {
                    CustomToast.makeText(this, "密码长度不能小于4", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                CustomToast.makeText(this, "请输入撤单密码", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            mSwitchList.add(new SystemParamsSetBean("撤单密码", 215, 0));
        }
        if (cbSystemSetInitPaw.isChecked()) {
            if (!TextUtils.isEmpty(etSystemSetInitPaw.getText())) {
                if (etSystemSetInitPaw.getText().toString().length() > 3) {
                    String value = etSystemSetInitPaw.getText().toString();
                    mSwitchList.add(new SystemParamsSetBean("开卡初始密码", 202, 1, value));
                } else {
                    CustomToast.makeText(this, "密码长度不能小于4", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                CustomToast.makeText(this, "请输入开卡初始密码", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            mSwitchList.add(new SystemParamsSetBean("开卡初始密码", 202, 0));
        }
        if (cbSystemSetConsumePaw.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("消费密码验证", 204, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("消费密码验证", 204, 0));
        }
        if (cbSystemSetZhuanzhangPaw.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("转账密码验证", 205, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("转账密码验证", 205, 0));
        }
        if (cbSystemSetExchangePaw.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("兑换密码验证", 206, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("兑换密码验证", 206, 0));
        }
        if (cbSystemSetHuankaPaw.isChecked()) {
            mSwitchList.add(new SystemParamsSetBean("换卡密码验证", 207, 1));
        } else {
            mSwitchList.add(new SystemParamsSetBean("换卡密码验证", 207, 0));
        }
//        if (cbSystemSetHytj.isChecked()) {
//            if (!TextUtils.isEmpty(etSystemSetHytj.getText())) {
//                String value = etSystemSetHytj.getText().toString();
//                mSwitchList.add(new SystemParamsSetBean("会员推荐提成", 503, 1, value));
//            } else {
//                CustomToast.makeText(this, "请输入会员推荐可获得积分", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        } else {
//            mSwitchList.add(new SystemParamsSetBean("会员推荐提成", 503, 0));
//        }
//        if (cbSystemSetConsumeFl.isChecked()) {
//            if (!TextUtils.isEmpty(etSystemSetConsumeFl.getText())) {
//                String value = etSystemSetConsumeFl.getText().toString();
//                mSwitchList.add(new SystemParamsSetBean("会员消费提成", 501, 1, value));
//            } else {
//                CustomToast.makeText(this, "请输入会员消费可获得积分", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        } else {
//            mSwitchList.add(new SystemParamsSetBean("会员消费提成", 501, 0));
//        }
//        if (cbSystemSetIntegralFl.isChecked()) {
//            if (!TextUtils.isEmpty(etSystemSetIntegralFl.getText())) {
//                String value = etSystemSetIntegralFl.getText().toString();
//                mSwitchList.add(new SystemParamsSetBean("会员获得积分提成", 502, 1, value));
//            } else {
//                CustomToast.makeText(this, "请输入下线每获得多少积分上线可获得1积分", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        } else {
//            mSwitchList.add(new SystemParamsSetBean("会员获得积分提成", 502, 0));
//        }
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
        }

    }


}

