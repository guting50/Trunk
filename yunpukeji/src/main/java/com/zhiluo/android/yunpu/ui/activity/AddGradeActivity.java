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
import com.zhiluo.android.yunpu.entity.GradeSetBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.MemberGrade;
import com.zhiluo.android.yunpu.ui.adapter.InitServiceAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyListView;
import com.zhiluo.android.yunpu.utils.RegexUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Date: 2017/12/27 16:25
 * Author: 罗咏哲
 * Description: 会员等级
 * Version:
 */
public class AddGradeActivity extends AppCompatActivity implements InitServiceAdapter.DeleteClick,
        InitServiceAdapter.CheckBoxChange {

    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_add_grade_save)
    TextView tvAddGradeSave;
    @Bind(R.id.et_add_grade_name)
    EditText etName;
    @Bind(R.id.et_add_grade_code)
    EditText etCode;
    @Bind(R.id.et_add_grade_condition)
    EditText etCondition;
    @Bind(R.id.et_add_grade_money)
    EditText etMoney;
    @Bind(R.id.cb_add_grade_up_grade)
    CheckBox cbUpGrade;
    @Bind(R.id.cb_add_grade_down_grade)
    CheckBox cbDownGrade;
    @Bind(R.id.switch_button_recharge_money)
    SwitchButton sbRechargeMoney;
    @Bind(R.id.et_add_grade_init_money)
    EditText etInitMoney;
    @Bind(R.id.switch_button_integral)
    SwitchButton sbIntegral;
    @Bind(R.id.et_add_grade_init_integral)
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
    @Bind(R.id.sp_add_grade_time_type)
    Spinner spTimeType;
    @Bind(R.id.cb_grade_effective_time)
    CheckBox cbEffectiveTime;
    @Bind(R.id.switch_button_limit_times)
    SwitchButton sbLimitTimes;
    @Bind(R.id.btn_clear)
    Button btnClear;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.lv_add_grade)
    MyListView lvAddGrade;

    private ArrayList<String> mSpList;//
    private ArrayAdapter<String> mAdapter;
    private InitServiceAdapter mInitAdapter;
    private String mLimitTimeType;
    List<DataBean.SettingsBean> mSetList = new ArrayList<>();
    private SweetAlertDialog mSweetAlertDialog;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mCheckedList;//选择的初始服务项目
    private List<DataBean.InitServiceListBean> mList = new ArrayList<>();
    private boolean mHasSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);
        ButterKnife.bind(this);
        setListener();
        init();
    }

    private void init() {
        mSpList = new ArrayList<>();
        mSpList.add("天");
        mSpList.add("月");
        mSpList.add("年");
        mAdapter = new ArrayAdapter<String>(AddGradeActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, mSpList);
        spTimeType.setAdapter(mAdapter);
    }

    private boolean verfiy() {
        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(AddGradeActivity.this, "请输入会员等级名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etCondition.getText())) {
            CustomToast.makeText(AddGradeActivity.this, "请输入等级升级条件", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (sbIntegral.isChecked()){
            if (TextUtils.isEmpty(etRechargeIntegralScale.getText())) {
                CustomToast.makeText(AddGradeActivity.this, "请输入充值积分比例", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(etFastConsumeIntegralScale.getText())) {
                CustomToast.makeText(AddGradeActivity.this, "请输入快消积分比例", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (cbIntegralSet.isChecked()){
                if (TextUtils.isEmpty(etGoodsConsumeIntegralScale.getText())) {
                    CustomToast.makeText(AddGradeActivity.this, "请输入商品统一积分比例", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        if (sbDiscount.isChecked()){
            if (TextUtils.isEmpty(etFastConsumeDiscountScale.getText())) {
                CustomToast.makeText(AddGradeActivity.this, "请输入快消折扣比例", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (cbDiscountSet.isChecked()){
                if (TextUtils.isEmpty(etGoodsConsumeDiscountScale.getText())) {
                    CustomToast.makeText(AddGradeActivity.this, "请输入商品统一折扣比例", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }

    private void setListener() {
        //返回
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //保存
        tvAddGradeSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verfiy()) {
                    addGrade();
                }
            }
        });
        spTimeType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        //计次设定
        sbLimitTimes.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton switchButton, boolean b) {
                if (b) {
                    btnAdd.setEnabled(true);
                    btnClear.setEnabled(true);
                } else {
                    mList.clear();
                    if (mInitAdapter != null) {
                        mInitAdapter.notifyDataSetChanged();
                    }
                    btnAdd.setEnabled(false);
                    btnClear.setEnabled(false);
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
                    spTimeType.setEnabled(true);
                    cbEffectiveCount.setClickable(true);
                    cbEffectiveTime.setClickable(true);
                } else {
                    etEffectiveTimes.setText("0");
                    etEffectiveTime.setText("0");
                    spTimeType.setSelection(0);
                    etEffectiveTimes.setInputType(InputType.TYPE_NULL);
                    etEffectiveTime.setInputType(InputType.TYPE_NULL);
                    spTimeType.setEnabled(false);
                    cbEffectiveCount.setClickable(false);
                    cbEffectiveTime.setClickable(false);
                    cbEffectiveCount.setChecked(false);
                    cbEffectiveTime.setChecked(false);
                }
            }
        });
        //添加开卡初始项目
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCheckedList != null) {
                    mCheckedList.clear();
                }
                if (mList != null) {
                    mList.clear();
                }
                Intent intent = new Intent(AddGradeActivity.this, ChoiceInitServiceActivity.class);
                startActivityForResult(intent, 200);
            }
        });
        //商品积分详情设置
        tvIntegralSetDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddGradeActivity.this, GradeIntegralSetActivity.class);
                intent.putExtra("GID", "");
                startActivityForResult(intent, 11);
            }
        });
        //商品折扣详情设置
        tvDiscountSetDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddGradeActivity.this, GradeDiscountSetActivity.class);
                intent.putExtra("GID", "");
                startActivityForResult(intent, 12);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mList.clear();
                if (mInitAdapter != null) {
                    mInitAdapter.notifyDataSetChanged();
                }
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
                    cbIntegralDetailSet.setClickable(false);
                    tvIntegralSetDetail.setTextColor(getResources().getColor(R.color.blue));
                } else {
                    etGoodsConsumeIntegralScale.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    cbIntegralDetailSet.setClickable(true);
                    tvIntegralSetDetail.setTextColor(getResources().getColor(R.color.gray));
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
                    cbDiscountDetailSet.setClickable(false);
                } else {
                    etGoodsConsumeDiscountScale.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    cbDiscountDetailSet.setClickable(true);
                }
            }
        });
        /**
         *  初始时默认选中统一设置，所以点击详情设置应置为不可点击状态
         *  而当给控件设置OnClickLister的时候，控件自动获得点击，所以应放到点击事件设置之后
         */
        tvIntegralSetDetail.setClickable(false);
        tvDiscountSetDetail.setClickable(false);
        cbIntegralSet.setClickable(false);
        cbDiscountSet.setClickable(false);
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
                    spTimeType.setClickable(false);
                } else {
                    etEffectiveTime.setInputType(InputType.TYPE_CLASS_NUMBER);
                    spTimeType.setClickable(true);
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
                        CustomToast.makeText(AddGradeActivity.this, "请输入1-100", Toast.LENGTH_SHORT).show();
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
                        CustomToast.makeText(AddGradeActivity.this, "请输入1-100", Toast.LENGTH_SHORT).show();
                        etGoodsConsumeDiscountScale.setText("100");
                        etGoodsConsumeDiscountScale.setSelection(etGoodsConsumeDiscountScale.getText().length());
                    }
                }
            }
        });
    }

    /**
     * 提交
     */
    private void addGrade() {
        RequestParams params = new RequestParams();
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
            for (int i = 0; i < mSetList.size(); i++) {
                params.put("Settings[" + i + "][PT_ID]", mSetList.get(i).getPT_GID());
                params.put("Settings[" + i + "][VS_CMoney]", mSetList.get(i).getVS_CMoney());
                params.put("Settings[" + i + "][VS_Number]", mSetList.get(i).getVS_Number());
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
            for (int i = 0; i < mSetList.size(); i++) {
                params.put("Settings[" + i + "][PT_ID]", mSetList.get(i).getPT_GID());
                params.put("Settings[" + i + "][PD_Discount]", mSetList.get(i).getPD_Discount());
                params.put("Settings[" + i + "][PTD_Type]", 0);
            }
        }
        if (mList != null && mList.size() > 0) {
            for (int i = 0; i < mList.size(); i++) {
                params.put("InitServiceList[" + i + "][SC_GD]", mList.get(i).getGID());
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
                mSweetAlertDialog = new SweetAlertDialog(AddGradeActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("新增成功");
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
                CustomToast.makeText(AddGradeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交...", false);
        HttpHelper.post(this, HttpAPI.API().ADDGRADE, params, callBack);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
            mSweetAlertDialog = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 200) {
            mCheckedList = (List<GoodsCheckResponseByType.DataBean.DataListBean>) data.getSerializableExtra("Service");
            if (mCheckedList != null && mCheckedList.size() > 0) {
                for (int i = 0; i < mCheckedList.size(); i++) {
                    DataBean.InitServiceListBean bean = new DataBean.InitServiceListBean();
                    bean.setGID(mCheckedList.get(i).getGID());
                    bean.setSG_Name(mCheckedList.get(i).getPM_Name());
                    bean.setSR_Number(1);
                    bean.setSR_TimeUnit(1);
                    bean.setSR_Timer(0);
                    bean.setSR_Number(1);
                    mList.add(bean);
                }
                mInitAdapter = new InitServiceAdapter(AddGradeActivity.this, mList, AddGradeActivity.this,
                        AddGradeActivity.this);
                lvAddGrade.setAdapter(mInitAdapter);
            }
        }
        if (requestCode == 11 && resultCode == 11) {
            GradeSetBean bean = (GradeSetBean) data.getSerializableExtra("integral_set");
            if (bean != null) {
                if (mHasSet) {
                    for (int i = 0; i < bean.getData().size(); i++) {
                        mSetList.get(i).setVS_CMoney(bean.getData().get(i).getVS_CMoney());
                        mSetList.get(i).setVS_Number(bean.getData().get(i).getVS_Number());
                    }
                } else {
                    for (int i = 0; i < bean.getData().size(); i++) {
                        DataBean.SettingsBean sBean = new DataBean.SettingsBean();
                        sBean.setVS_CMoney(bean.getData().get(i).getVS_CMoney());
                        sBean.setVS_Number(bean.getData().get(i).getVS_Number());
                        sBean.setPT_GID(bean.getData().get(i).getGID());
                        mSetList.add(sBean);
                    }
                    mHasSet = true;
                }
            }
        }
        if (requestCode == 12 && resultCode == 12) {
            GradeSetBean bean = (GradeSetBean) data.getSerializableExtra("discount_set");
            if (bean != null) {
                if (mHasSet) {
                    for (int i = 0; i < bean.getData().size(); i++) {
                        mSetList.get(i).setPD_Discount(bean.getData().get(i).getPD_Discount());
                    }
                } else {
                    for (int i = 0; i < bean.getData().size(); i++) {
                        DataBean.SettingsBean sBean = new DataBean.SettingsBean();
                        sBean.setPD_Discount(bean.getData().get(i).getPD_Discount());
                        sBean.setPT_GID(bean.getData().get(i).getGID());
                        mSetList.add(sBean);
                    }
                    mHasSet = true;
                }
            }
        }
    }

    @Override
    public void deleteItem(View view) {
        int pos = (int) view.getTag();
        mList.remove(pos);
        mInitAdapter.notifyDataSetChanged();
    }

    @Override
    public void change(View view, boolean isChecked) {
        int pos = (int) view.getTag();
        if (isChecked) {
            mList.get(pos).setSR_Timer(1);
        } else {
            mList.get(pos).setSR_Timer(0);
        }
        mInitAdapter.notifyDataSetChanged();
    }
}
