package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.suke.widget.SwitchButton;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.entity.EditGradeResultBean;
import com.zhiluo.android.yunpu.entity.GradeSetBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.adapter.InitServiceAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyListView;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.utils.RegexUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class EditGradeActivity extends AppCompatActivity implements InitServiceAdapter.DeleteClick,
        InitServiceAdapter.CheckBoxChange {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_edit_grade_save)
    TextView tvSave;
    @Bind(R.id.et_grade_detail_name)
    EditText etName;
    @Bind(R.id.et_grade_detail_code)
    EditText etCode;
    @Bind(R.id.et_grade_detail_condition)
    EditText etCondition;
    @Bind(R.id.et_grade_detail_money)
    EditText etMoney;
    @Bind(R.id.cb_grade_detail_up_grade)
    CheckBox cbUpGrade;
    @Bind(R.id.cb_grade_detail_down_grade)
    CheckBox cbDownGrade;
    @Bind(R.id.switch_button_recharge_money)
    SwitchButton sbRechargeMoney;
    @Bind(R.id.et_grade_detail_init_money)
    EditText etInitMoney;
    @Bind(R.id.switch_button_integral)
    SwitchButton sbIntegral;
    @Bind(R.id.et_grade_detail_init_integral)
    EditText etInitIntegral;
    @Bind(R.id.et_grade_recharge_integral_scale)
    EditText etRechargeIntegralScale;
    @Bind(R.id.et_grade_fast_consume_integral_scale)
    EditText etFastConsumeIntegralScale;
    @Bind(R.id.cb_grade_integral_set)
    CheckBox cbIntegralSet;
    @Bind(R.id.et_grade_goods_consume_integral_scale)
    EditText etGoodsConsumeIntegralScale;
    @Bind(R.id.cb_grade_integral_detail_set)
    CheckBox cbIntegralDetailSet;
    @Bind(R.id.tv_integral_set_detail)
    TextView tvIntegralSetDetail;
    @Bind(R.id.switch_button_discount)
    SwitchButton sbDiscount;
    @Bind(R.id.et_grade_fast_consume_discount_scale)
    EditText etFastConsumeDiscountScale;
    @Bind(R.id.cb_grade_discount_set)
    CheckBox cbDiscountSet;
    @Bind(R.id.et_grade_goods_consume_discount_scale)
    EditText etGoodsConsumeDiscountScale;
    @Bind(R.id.cb_grade_discount_detail_set)
    CheckBox cbDiscountDetailSet;
    @Bind(R.id.tv_discount_set_detail)
    TextView tvDiscountSetDetail;
    @Bind(R.id.switch_button_limit_time)
    SwitchButton sbLimitTime;
    @Bind(R.id.et_grade_effective_times)
    EditText etEffectiveTimes;
    @Bind(R.id.cb_grade_effective_count)
    CheckBox cbEffectiveCount;
    @Bind(R.id.et_grade_effective_time)
    EditText etEffectiveTime;
    @Bind(R.id.sp_grader_detail_time_type)
    Spinner spDetailTimeType;
    @Bind(R.id.cb_grade_effective_time)
    CheckBox cbEffectiveTime;
    @Bind(R.id.switch_button_limit_times)
    SwitchButton sbLimitTimes;
    @Bind(R.id.btn_clear)
    Button btnClear;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.lv_grade_detail)
    MyListView lvGradeDetail;

    private DataBean mGrade;
    private InitServiceAdapter mAdapter;
    private List<DataBean.InitServiceListBean> mList = new ArrayList<>();
    private String mLimitTimeType;
    private SweetAlertDialog mSweetAlertDialog;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mCheckedList;//选择的初始服务项目
//    private GradeSetBean mGradeSetBean = new GradeSetBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_grade);
        ButterKnife.bind(this);
        setListener();
        init();
    }


    private void init() {
        mGrade = (DataBean) getIntent().getSerializableExtra("Grade");
//        List<GradeSetBean.DataBean> ls = new ArrayList<>();
//        for (int i = 0; i < mGrade.getSettings().size(); i++) {
//            GradeSetBean.DataBean bean = new GradeSetBean.DataBean();
//            bean.setPT_Name(mGrade.getSettings().get(i).getPT_Name());
//            bean.setGID(mGrade.getSettings().get(i).getPT_GID());
//            bean.setPD_Discount(mGrade.getSettings().get(i).getPD_Discount());
//            bean.setVS_CMoney(mGrade.getSettings().get(i).getVS_CMoney());
//            bean.setVS_Number(mGrade.getSettings().get(i).getVS_Number());
//            ls.add(bean);
//        }
//        mGradeSetBean.setData(ls);
        ArrayList<String> list = new ArrayList<>();
        list.add("天");
        list.add("月");
        list.add("年");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_spinner, R.id.tv_item_spinner, list);
        spDetailTimeType.setAdapter(adapter);
        etName.setText(mGrade.getVG_Name());
        if (mGrade.getVG_Code() != null) {
            etCode.setText(mGrade.getVG_Code());
        }
        etMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_CardAmount()));
        etCondition.setText("" + mGrade.getUS_Value());
        etInitMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_InitialAmount()));
        //自动升级
        if (mGrade.getVG_IsLock() == 1) {
            cbUpGrade.setChecked(true);
        } else {
            cbUpGrade.setChecked(false);
        }
        //自动降级
        if (mGrade.getVG_IsDownLock() == 1) {
            cbDownGrade.setChecked(true);
        } else {
            cbDownGrade.setChecked(false);
        }
        //储值设定
        if (mGrade.getVG_IsAccount() == 1) {
            sbRechargeMoney.setChecked(true);
        } else {
            sbRechargeMoney.setChecked(false);
        }
        //积分设定
        if (mGrade.getVG_IsIntegral() == 1) {
            sbIntegral.setChecked(true);
        } else {
            sbIntegral.setChecked(false);
        }
        etInitIntegral.setText(Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_InitialIntegral()));
        //充值积分比例
        if (mGrade.getRS_CMoney() > 0 && mGrade.getRS_Value() > 0) {
            double rIntegralScale = DoubleMathUtil.div(mGrade.getRS_CMoney(), mGrade.getRS_Value(), 2);
            etRechargeIntegralScale.setText(Decima2KeeplUtil.stringToDecimal("" + rIntegralScale));
        }
        //快消积分比例
        if (mGrade.getVS_CMoney() > 0 && mGrade.getVS_Value() > 0) {
            double kIntegralScale = DoubleMathUtil.div(mGrade.getVS_CMoney(), mGrade.getVS_Value(), 2);
            etFastConsumeIntegralScale.setText(Decima2KeeplUtil.stringToDecimal("" + kIntegralScale));
        }

        //折扣设定
        if (mGrade.getVG_IsDiscount() == 1) {
            sbDiscount.setChecked(true);
        } else {
            sbDiscount.setChecked(false);
        }
        etFastConsumeDiscountScale.setText("" + (int) mGrade.getDS_Value());
        if (mGrade.getVS_Value() > 0) {
            double ds = DoubleMathUtil.div(mGrade.getVS_CMoney(), mGrade.getVS_Value());
            etFastConsumeIntegralScale.setText(Decima2KeeplUtil.stringToDecimal("" + ds));
        } else {
            etFastConsumeIntegralScale.setText("0");
        }

        //商品折扣统一设置详情设置

        //限时设置
        if (mGrade.getVG_IsTime() == 1) {
            sbLimitTime.setChecked(true);
        } else {
            sbLimitTime.setChecked(false);
        }
        //有效次数
        if (mGrade.getVG_IsTimeTimes() != null) {
            cbEffectiveCount.setChecked(false);
            etEffectiveTimes.setText("" + (int) Double.parseDouble(String.valueOf(mGrade.getVG_IsTimeTimes())));
        } else {
            cbEffectiveCount.setChecked(true);
        }
        //有效时间
        if (mGrade.getVG_IsTimeNum() != null) {
            cbEffectiveTime.setChecked(false);
            etEffectiveTime.setText("" + (int) Double.parseDouble(String.valueOf(mGrade.getVG_IsTimeNum())));
        } else {
            cbEffectiveTime.setChecked(true);
        }
        //计次设定
        if (mGrade.getVG_IsCount() == 1) {
            sbLimitTimes.setChecked(true);
        } else {
            sbLimitTimes.setChecked(false);
            btnAdd.setEnabled(false);
            btnClear.setEnabled(false);
        }

        if (mGrade.getInitServiceList().size() > 0) {
            mList.addAll(mGrade.getInitServiceList());
            mAdapter = new InitServiceAdapter(EditGradeActivity.this, mList, EditGradeActivity.this,
                    EditGradeActivity.this);
            lvGradeDetail.setAdapter(mAdapter);
        }

        switch (mGrade.getVG_IntegralRuleType()) {
            case 0:
                cbIntegralSet.setChecked(true);
                cbIntegralSet.setClickable(false);
                cbIntegralDetailSet.setClickable(true);
                tvIntegralSetDetail.setClickable(false);
                tvIntegralSetDetail.setTextColor(getResources().getColor(R.color.gray));
                etGoodsConsumeIntegralScale.setText(Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_IntegralUniformRuleValue()));
                break;
            case 1:
                cbIntegralDetailSet.setChecked(true);
                cbIntegralSet.setClickable(true);
                cbIntegralDetailSet.setClickable(false);
                tvIntegralSetDetail.setClickable(true);
                tvIntegralSetDetail.setTextColor(getResources().getColor(R.color.blue));
                etGoodsConsumeIntegralScale.setText("");
                break;
        }
        switch (mGrade.getVG_DiscountRuleType()) {
            case 0:
                cbDiscountSet.setChecked(true);
                cbDiscountSet.setClickable(false);
                cbDiscountDetailSet.setClickable(true);
                tvDiscountSetDetail.setClickable(false);
                etGoodsConsumeDiscountScale.setText("" + mGrade.getVG_DiscountUniformRuleValue());
                tvDiscountSetDetail.setTextColor(getResources().getColor(R.color.gray));
                break;
            case 1:
                tvDiscountSetDetail.setClickable(true);
                cbDiscountDetailSet.setChecked(true);
                cbDiscountSet.setClickable(true);
                cbDiscountDetailSet.setClickable(false);
                tvDiscountSetDetail.setTextColor(getResources().getColor(R.color.blue));
                etGoodsConsumeDiscountScale.setText("");
                break;
        }
        spDetailTimeType.setClickable(false);
        if (mGrade.getVG_IsTimeUnit() != null) {
            switch ((String) mGrade.getVG_IsTimeUnit()) {
                case "天":
                    spDetailTimeType.setSelection(0);
                    break;
                case "月":
                    spDetailTimeType.setSelection(1);
                    break;
                case "年":
                    spDetailTimeType.setSelection(2);
                    break;
            }
        }
    }

    private void setListener() {
        //保存
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verfiy()) {
                    edit();
                }
            }
        });
        //返回
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sbRechargeMoney.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton switchButton, boolean b) {
                if (b) {
                    etInitMoney.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                } else {
                    etInitMoney.setText("0");
                    etInitMoney.setInputType(InputType.TYPE_NULL);
                }
            }
        });
        sbIntegral.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton switchButton, boolean b) {
                if (b) {
                    etInitIntegral.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    etRechargeIntegralScale.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    etFastConsumeIntegralScale.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    etGoodsConsumeIntegralScale.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    cbIntegralSet.setClickable(true);
                    cbIntegralDetailSet.setClickable(true);
                    tvIntegralSetDetail.setClickable(true);
                } else {
                    etInitIntegral.setText("0");
                    etInitIntegral.setInputType(InputType.TYPE_NULL);
                    etRechargeIntegralScale.setText("0");
                    etRechargeIntegralScale.setInputType(InputType.TYPE_NULL);
                    etFastConsumeIntegralScale.setText("0");
                    etFastConsumeIntegralScale.setInputType(InputType.TYPE_NULL);
                    etGoodsConsumeIntegralScale.setText("0");
                    etGoodsConsumeIntegralScale.setInputType(InputType.TYPE_NULL);
                    cbIntegralSet.setClickable(false);
                    cbIntegralDetailSet.setClickable(false);
                    tvIntegralSetDetail.setClickable(false);
                }
            }
        });
        sbDiscount.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton switchButton, boolean b) {
                if (b) {
                    etFastConsumeDiscountScale.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    etGoodsConsumeDiscountScale.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    cbDiscountSet.setClickable(true);
                    cbDiscountDetailSet.setClickable(true);
                    tvIntegralSetDetail.setClickable(true);
                } else {
                    etFastConsumeDiscountScale.setText("100");
                    etFastConsumeDiscountScale.setInputType(InputType.TYPE_NULL);
                    etGoodsConsumeDiscountScale.setText("100");
                    etGoodsConsumeDiscountScale.setInputType(InputType.TYPE_NULL);
                    cbDiscountSet.setClickable(false);
                    cbDiscountDetailSet.setClickable(false);
                    tvDiscountSetDetail.setClickable(false);
                }
            }
        });
        //添加开卡初始项目
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditGradeActivity.this, ChoiceInitServiceActivity.class);
                startActivityForResult(intent, 200);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                if (mAdapter != null) {
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        tvIntegralSetDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditGradeActivity.this, GradeIntegralSetActivity.class);
                intent.putExtra("GD", mGrade);
                startActivityForResult(intent, 11);
            }
        });
        tvDiscountSetDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditGradeActivity.this, GradeDiscountSetActivity.class);
                intent.putExtra("ZK", mGrade);
                startActivityForResult(intent, 12);
            }
        });
        spDetailTimeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mLimitTimeType = "天";
                        break;
                    case 1:
                        mLimitTimeType = "月";
                        break;
                    case 2:
                        mLimitTimeType = "年";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //商品积分统一设置
        cbIntegralSet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbIntegralDetailSet.setChecked(false);
                    tvIntegralSetDetail.setClickable(false);
                    cbIntegralSet.setClickable(false);
                    tvIntegralSetDetail.setTextColor(getResources().getColor(R.color.gray));
                } else {
                    tvIntegralSetDetail.setClickable(true);
                    cbIntegralSet.setClickable(true);
                    tvIntegralSetDetail.setTextColor(getResources().getColor(R.color.blue));
                }
            }
        });
        //商品积分详情设置
        cbIntegralDetailSet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbIntegralSet.setChecked(false);
                    etGoodsConsumeIntegralScale.setInputType(InputType.TYPE_NULL);
                    etGoodsConsumeIntegralScale.setText("0");
                    cbIntegralDetailSet.setClickable(false);
                } else {
                    etGoodsConsumeIntegralScale.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    cbIntegralDetailSet.setClickable(true);
                }
            }
        });
        //商品折扣统一设置
        cbDiscountSet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbDiscountDetailSet.setChecked(false);
                    tvDiscountSetDetail.setClickable(false);
                    cbDiscountSet.setClickable(false);
                    tvDiscountSetDetail.setTextColor(getResources().getColor(R.color.gray));
                } else {
                    tvDiscountSetDetail.setClickable(true);
                    cbDiscountSet.setClickable(true);
                    tvDiscountSetDetail.setTextColor(getResources().getColor(R.color.blue));
                }
            }
        });
        //商品积分详情设置
        cbDiscountDetailSet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cbDiscountSet.setChecked(false);
                    etGoodsConsumeDiscountScale.setInputType(InputType.TYPE_NULL);
                    etGoodsConsumeDiscountScale.setText("100");
                    cbDiscountDetailSet.setClickable(false);
                } else {
                    etGoodsConsumeDiscountScale.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    cbDiscountDetailSet.setClickable(true);
                }
            }
        });
        //限时设定
        sbLimitTime.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton switchButton, boolean b) {
                if (b) {
                    etEffectiveTimes.setInputType(InputType.TYPE_CLASS_NUMBER);
                    etEffectiveTime.setInputType(InputType.TYPE_CLASS_NUMBER);
                    spDetailTimeType.setEnabled(true);
                    cbEffectiveCount.setClickable(true);
                    cbEffectiveTime.setClickable(true);
                } else {
                    etEffectiveTimes.setText("0");
                    etEffectiveTime.setText("0");
                    spDetailTimeType.setSelection(0);
                    etEffectiveTimes.setInputType(InputType.TYPE_NULL);
                    etEffectiveTime.setInputType(InputType.TYPE_NULL);
                    spDetailTimeType.setEnabled(false);
                    cbEffectiveCount.setClickable(false);
                    cbEffectiveTime.setClickable(false);
                    cbEffectiveCount.setChecked(false);
                    cbEffectiveTime.setChecked(false);
                }
            }
        });
        //计次设定
        sbLimitTimes.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton switchButton, boolean b) {
                if (b) {
                    btnAdd.setEnabled(true);
                    btnClear.setEnabled(true);
                } else {
                    mList.clear();
                    if (mAdapter != null) {
                        mAdapter.notifyDataSetChanged();
                    }
                    btnAdd.setEnabled(false);
                    btnClear.setEnabled(false);
                }
            }
        });
        //有效次数开关
        cbEffectiveCount.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etEffectiveTimes.setInputType(InputType.TYPE_NULL);
                    etEffectiveTimes.setText("");
                } else {
                    etEffectiveTimes.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
            }
        });
        //有效时间
        cbEffectiveTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etEffectiveTime.setInputType(InputType.TYPE_NULL);
                    etEffectiveTime.setText("");
                    spDetailTimeType.setClickable(false);
                } else {
                    etEffectiveTime.setInputType(InputType.TYPE_CLASS_NUMBER);
                    spDetailTimeType.setClickable(true);
                }
            }
        });
        etEffectiveTimes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    cbEffectiveCount.setChecked(false);
                } else {
                    cbEffectiveCount.setChecked(true);
                }
            }
        });
        etEffectiveTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    cbEffectiveTime.setChecked(false);
                } else {
                    cbEffectiveTime.setChecked(true);
                }
            }
        });
        //充值积分比例
        etRechargeIntegralScale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    String temp = s.toString();
                    int posDot = temp.indexOf(".");
                    if (posDot > 0) {
                        if (temp.length() - posDot - 1 > 2) {
                            s.delete(posDot + 3, posDot + 4);
                        }
                    }
                }
            }
        });
        //快消积分比例
        etFastConsumeIntegralScale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    String temp = s.toString();
                    int posDot = temp.indexOf(".");
                    if (posDot > 0) {
                        if (temp.length() - posDot - 1 > 2) {
                            s.delete(posDot + 3, posDot + 4);
                        }
                    }
                }
            }
        });
        //商品积分积分比例
        etGoodsConsumeIntegralScale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    String temp = s.toString();
                    int posDot = temp.indexOf(".");
                    if (posDot > 0) {
                        if (temp.length() - posDot - 1 > 2) {
                            s.delete(posDot + 3, posDot + 4);
                        }
                    }
                }
            }
        });
        etFastConsumeDiscountScale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    if (!RegexUtil.isDis(s.toString())) {
                        CustomToast.makeText(EditGradeActivity.this, "请输入1-100", Toast.LENGTH_SHORT).show();
                        etFastConsumeDiscountScale.setText("100");
                        etFastConsumeDiscountScale.setSelection(etFastConsumeDiscountScale.getText().length());
                    }
                }
            }
        });
        etGoodsConsumeDiscountScale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!TextUtils.isEmpty(s)) {
                    if (!RegexUtil.isDis(s.toString())) {
                        CustomToast.makeText(EditGradeActivity.this, "请输入1-100", Toast.LENGTH_SHORT).show();
                        etGoodsConsumeDiscountScale.setText("100");
                        etGoodsConsumeDiscountScale.setSelection(etGoodsConsumeDiscountScale.getText().length());
                    }
                }
            }
        });
    }

    private boolean verfiy() {
        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(EditGradeActivity.this, "请输入会员等级名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etCondition.getText())) {
            CustomToast.makeText(EditGradeActivity.this, "请输入等级升级条件", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sbIntegral.isChecked()) {
            if (TextUtils.isEmpty(etRechargeIntegralScale.getText())) {
                CustomToast.makeText(EditGradeActivity.this, "请输入充值积分比例", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(etFastConsumeIntegralScale.getText())) {
                CustomToast.makeText(EditGradeActivity.this, "请输入快消积分比例", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (cbIntegralSet.isChecked()) {
                if (TextUtils.isEmpty(etGoodsConsumeIntegralScale.getText())) {
                    CustomToast.makeText(EditGradeActivity.this, "请输入商品统一积分比例", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        if (sbDiscount.isChecked()) {
            if (TextUtils.isEmpty(etFastConsumeDiscountScale.getText())) {
                CustomToast.makeText(EditGradeActivity.this, "请输入快消折扣比例", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (cbDiscountSet.isChecked()) {
                if (TextUtils.isEmpty(etGoodsConsumeDiscountScale.getText())) {
                    CustomToast.makeText(EditGradeActivity.this, "请输入商品统一折扣比例", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 编辑
     */
    private void edit() {
        RequestParams params = new RequestParams();
        params.put("GID", mGrade.getGID());
        params.put("US_Code", "IntegerFulfil");
        params.put("US_Value", etCondition.getText().toString());
        params.put("VG_Name", etName.getText().toString());
        params.put("VG_Code", etCode.getText() == null ? "" : etCode.getText().toString());
        params.put("VG_IsLock", cbUpGrade.isChecked() ? 1 : 0);
        params.put("VG_IsDownLock", cbDownGrade.isChecked() ? 1 : 0);
        params.put("VG_Remark", "");
        params.put("VG_CardAmount", etMoney.getText() == null ? 0 : etMoney.getText().toString());
        params.put("VG_InitialAmount", etInitMoney.getText() == null ? 0 : etInitMoney.getText().toString());
        params.put("VG_InitialIntegral", etInitIntegral.getText() == null ? 0 : etInitIntegral.getText().toString());
        params.put("VG_IsAccount", sbRechargeMoney.isChecked() ? 1 : 0);
        params.put("VG_IsIntegral", sbIntegral.isChecked() ? 1 : 0);
        params.put("VG_IsDiscount", sbDiscount.isChecked() ? 1 : 0);
        params.put("VG_IsCount", sbLimitTimes.isChecked() ? 1 : 0);
        params.put("VG_IsTime", sbLimitTime.isChecked() ? 1 : 0);
        if (!cbEffectiveCount.isChecked()) {
            params.put("VG_IsTimeTimes", TextUtils.isEmpty(etEffectiveTimes.getText()) ? "" : etEffectiveTimes.getText().toString());
        }
        if (!cbEffectiveTime.isChecked()) {
            params.put("VG_IsTimeNum", TextUtils.isEmpty(etEffectiveTime.getText()) ? "" : etEffectiveTime.getText().toString());
            params.put("VG_IsTimeUnit", mLimitTimeType);
        }
        params.put("DS_Value", etFastConsumeDiscountScale.getText().toString());
        params.put("RS_CMoney", etRechargeIntegralScale.getText() == null ? 0 : etRechargeIntegralScale.getText().toString());
        int rv = 0;
        if (etRechargeIntegralScale.getText() != null && !etRechargeIntegralScale.getText().toString().equals("0")) {
            rv = 1;
        }
        params.put("RS_Value", rv);
        params.put("VS_CMoney", etFastConsumeIntegralScale.getText() == null ? 0 : etFastConsumeIntegralScale.getText().toString());
        int fv = 0;
        if (etFastConsumeIntegralScale.getText() != null && !etFastConsumeIntegralScale.getText().toString().equals("0")) {
            fv = 1;
        }
        params.put("VS_Value", fv);
        //积分统一设置
        if (cbIntegralSet.isChecked()) {
            params.put("VG_IntegralRuleType", 0);
            params.put("VG_IntegralUniformRuleValue", etGoodsConsumeIntegralScale.getText().toString());
            params.put("Settings[]", "");
        } else {
            params.put("VG_IntegralRuleType", 1);
            for (int i = 0; i < mGrade.getSettings().size(); i++) {
                params.put("Settings[" + i + "][PT_ID]", mGrade.getSettings().get(i).getPT_GID());
                params.put("Settings[" + i + "][VS_CMoney]", mGrade.getSettings().get(i).getVS_CMoney());
                params.put("Settings[" + i + "][VS_Number]", mGrade.getSettings().get(i).getVS_Number());
                params.put("Settings[" + i + "][PTD_Type]", 0);
            }
        }
        //折扣统一设置
        if (cbDiscountSet.isChecked()) {
            params.put("VG_DiscountRuleType", 0);
            params.put("VG_DiscountUniformRuleValue", etGoodsConsumeDiscountScale.getText().toString());
            params.put("Settings[]", "");
        } else {
            params.put("VG_DiscountRuleType", 1);
            for (int i = 0; i < mGrade.getSettings().size(); i++) {
                params.put("Settings[" + i + "][PT_ID]", mGrade.getSettings().get(i).getPT_GID());
                params.put("Settings[" + i + "][PD_Discount]", mGrade.getSettings().get(i).getPD_Discount());
                params.put("Settings[" + i + "][PTD_Type]", 0);
            }
        }
        if (mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                params.put("InitServiceList[" + i + "][SC_GD]", mList.get(i).getSC_GD());
                params.put("InitServiceList[" + i + "][SR_Number]", mList.get(i).getSR_Number());
                if (mList.get(i).getSR_Timer() > 0) {
                    params.put("InitServiceList[" + i + "][SR_Timer]", mList.get(i).getSR_Timer());
                    params.put("InitServiceList[" + i + "][SR_TimeUnit]", mList.get(i).getSR_TimeUnit());
                }
            }
        }
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                EditGradeResultBean bean = CommonFun.JsonToObj(responseString, EditGradeResultBean.class);
                final DataBean dataBean = bean.getData();
                mSweetAlertDialog = new SweetAlertDialog(EditGradeActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("编辑成功");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        Intent intent = new Intent();
                        intent.putExtra("GD", dataBean);
                        setResult(500, intent);
                        EditGradeActivity.this.finish();
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditGradeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(EditGradeActivity.this, "提交中...", false);
        HttpHelper.post(this, HttpAPI.API().EDITVIPGRADE, params, callBack);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 200) {
            mCheckedList = (List<GoodsCheckResponseByType.DataBean.DataListBean>) data.getSerializableExtra("Service");
            if (mCheckedList != null && mCheckedList.size() > 0) {
                mList.clear();
                for (int i = 0; i < mCheckedList.size(); i++) {
                    DataBean.InitServiceListBean bean = new DataBean.InitServiceListBean();
                    bean.setSC_GD(mCheckedList.get(i).getGID());
                    bean.setSG_Name(mCheckedList.get(i).getPM_Name());
                    bean.setSR_Number(1);
                    mList.add(bean);
                }
                mAdapter = new InitServiceAdapter(EditGradeActivity.this, mList, EditGradeActivity.this,
                        EditGradeActivity.this);
                lvGradeDetail.setAdapter(mAdapter);
            }
        }
        if (requestCode == 11 && resultCode == 11) {
            GradeSetBean set = (GradeSetBean) data.getSerializableExtra("integral_set");
            if (set != null) {
                for (int i = 0; i < set.getData().size(); i++) {
                    mGrade.getSettings().get(i).setVS_CMoney(set.getData().get(i).getVS_CMoney());
                    mGrade.getSettings().get(i).setVS_Number(set.getData().get(i).getVS_Number());
                }
            }
        }
        if (requestCode == 12 && resultCode == 12) {
            GradeSetBean set = (GradeSetBean) data.getSerializableExtra("discount_set");
            if (set != null) {
                for (int i = 0; i < set.getData().size(); i++) {
                    mGrade.getSettings().get(i).setPD_Discount(set.getData().get(i).getPD_Discount());
                }
            }
        }
    }

    @Override
    public void deleteItem(View view) {
        int pos = (int) view.getTag();
        mList.remove(pos);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void change(View view, boolean isChecked) {
        int pos = (int) view.getTag();
        if (isChecked) {
            mList.get(pos).setSR_Timer(1);
        } else {
            mList.get(pos).setSR_Timer(0);
        }
        mAdapter.notifyDataSetChanged();
    }
}
