package com.zhiluo.android.yunpu.ui.activity.discount;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.RechargeBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.MonthBean;
import com.zhiluo.android.yunpu.member.manager.bean.WeekBean;
import com.zhiluo.android.yunpu.ui.activity.recharge.YSLEditReChargeActivity;
import com.zhiluo.android.yunpu.ui.adapter.YSLChoiseMonthAdapter;
import com.zhiluo.android.yunpu.ui.adapter.YSLChoiseWeekAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class EditDisActivity extends AppCompatActivity implements View.OnClickListener, YSLChoiseWeekAdapter.CheckItemListener
        , PopupWindow.OnDismissListener, YSLChoiseMonthAdapter.MOnthCheckItemListener {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_edit_dis_save)
    TextView tvSave;
    @Bind(R.id.et_edit_dis_name)
    EditText etName;
    @Bind(R.id.et_edit_dis_money)
    EditText etMoney;
    @Bind(R.id.sp_edit_dis_type)
    TextView tvType;
    @Bind(R.id.et_edit_dis_give_money)
    EditText etGiveMoney;
    @Bind(R.id.et_edit_dis_give_integral)
    EditText etGiveIntegral;
    @Bind(R.id.rb_edit_dis_double)
    RadioButton rbDouble;
    @Bind(R.id.rb_edit_dis_no_double)
    RadioButton rbNoDouble;
    @Bind(R.id.rg_edit_dis_double)
    RadioGroup rgDouble;


    @Bind(R.id.cb_forever)
    CheckBox cbForever;
    @Bind(R.id.cb_vip_birthday)
    CheckBox cbVipBirthday;
    @Bind(R.id.cb_fix_time)
    CheckBox cbFixTime;
    @Bind(R.id.cb_by_weeks)
    CheckBox cbByWeek;
    @Bind(R.id.cb_by_months)
    CheckBox cbByMonth;
    @Bind(R.id.te_start_time)
    TextView teStartTime;
    @Bind(R.id.te_end_time)
    TextView teEndTime;
    @Bind(R.id.te_content_week)
    TextView teContentWeek;
    @Bind(R.id.te_content_month)
    TextView teContentMonth;

    private int mType, mDouble, mDisType;
    private SweetAlertDialog mSweetAlertDialog;
    private RechargeBean mRechargeBean;
    private PopupWindow weekPop;
    private PopupWindow monthPop;
    private List<WeekBean> weekBeanList;
    private List<MonthBean> monthBeansList;
    private List<String> weekStringlist = new ArrayList<>();
    private List<String> monthStringlist = new ArrayList<>();
    private String mStringWeek = "";
    private String mStringmonth = "";
    private Dialog dateDialog;
    private Dialog chooseDialog;
    private List<String>list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dis);
        ButterKnife.bind(this);
        mRechargeBean = (RechargeBean) getIntent().getSerializableExtra("RE");
        if (mRechargeBean!=null){
            mType = mRechargeBean.getRP_ValidType();
        }
        inits();
        init();
        setListener();
    }

    private void inits() {
        weekPop = new PopupWindow(this);
        monthPop = new PopupWindow(this);
        list=new ArrayList<>();
        list.add("减少金额");
        list.add("增加金额");
        list.add("优惠折扣");
        if (mRechargeBean != null) {
            etName.setText(mRechargeBean.getRP_Name());
            etMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mRechargeBean.getRP_RechargeMoney()));
//            etGiveMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mRechargeBean.getRP_GiveMoney()));
            etGiveIntegral.setText("" + mRechargeBean.getRP_GivePoint());
            switch (mRechargeBean.getRP_ValidType()) {
                case 0://永久有效
                    cbForever.setChecked(true);
                    cbForever.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbForever.setTextColor(Color.parseColor("#FF8739"));
                    break;
                case 1://固定时间
                    String start = mRechargeBean.getRP_ValidStartTime();
                    String end = mRechargeBean.getRP_ValidEndTime();
                    if (start.length() > 10) {
                        start = start.substring(0, 10);
                    }
                    if (end.length() > 10) {
                        end = end.substring(0, 10);
                    }
                    cbFixTime.setChecked(true);
                    cbFixTime.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbFixTime.setTextColor(Color.parseColor("#FF8739"));
                    teStartTime.setText(start);
                    teEndTime.setText(end);
                    break;
                case 2://按周
                    cbByWeek.setChecked(true);
                    cbByWeek.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbByWeek.setTextColor(Color.parseColor("#FF8739"));
                    teContentWeek.setText("" + mRechargeBean.getRP_ValidWeekMonth());
                    break;
                case 3://按月
                    cbByMonth.setChecked(true);
                    cbByMonth.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbByMonth.setTextColor(Color.parseColor("#FF8739"));
                    teContentMonth.setText("" + mRechargeBean.getRP_ValidWeekMonth());
                    break;
                case 4://会员生日
                    cbVipBirthday.setChecked(true);
                    cbVipBirthday.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbVipBirthday.setTextColor(Color.parseColor("#FF8739"));
                    break;
            }
        }

    }

    private void init() {
        if (mRechargeBean != null) {
            etName.setText(mRechargeBean.getRP_Name());
            etMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mRechargeBean.getRP_RechargeMoney()));
            if (mRechargeBean.getRP_GiveMoney() >= 0) {
                etGiveMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mRechargeBean.getRP_GiveMoney()));
                tvType.setText("增加金额");
                mDisType = 1;
                rgDouble.getChildAt(1).setEnabled(true);
            } else if (mRechargeBean.getRP_ReduceMoney() >= 0) {
                etGiveMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mRechargeBean.getRP_ReduceMoney()));
                tvType.setText("减少金额");
                mDisType = 0;
                rgDouble.getChildAt(1).setEnabled(true);
            }else if (mRechargeBean.getRP_Discount()>=0){
                etGiveMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mRechargeBean.getRP_Discount()));
                tvType.setText("优惠折扣");
                mDisType = 2;
                rbNoDouble.setChecked(true);
                mDouble = 0;
                rgDouble.getChildAt(0).setEnabled(false);
            }
            etGiveIntegral.setText("" + mRechargeBean.getRP_GivePoint());

            if (mRechargeBean.getRP_ISDouble() == 0) {
                //不翻倍
                mDouble = 0;
                rbNoDouble.setChecked(true);
            } else {
                //翻倍
                rbDouble.setChecked(true);
                mDouble = 1;
            }
        }
    }

    private void setListener() {
        weekPop.setOnDismissListener(this);
        monthPop.setOnDismissListener(this);
        teStartTime.setOnClickListener(this);
        teEndTime.setOnClickListener(this);
        teContentWeek.setOnClickListener(this);
        teContentMonth.setOnClickListener(this);
//        showChooseDialog(list,tvType);
        tvType.setOnClickListener(this);
        cbForever.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbForever.isChecked()) {
                    cbByMonth.setChecked(false);
                    cbByWeek.setChecked(false);
                    cbFixTime.setChecked(false);
                    cbVipBirthday.setChecked(false);
                    cbForever.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbForever.setTextColor(Color.parseColor("#FF8739"));
                    teContentMonth.setText("");
                    teContentWeek.setText("");
                    teEndTime.setText("");
                    teStartTime.setText("");
                    mType = 0;
                    monthBeansList=null;
                    weekBeanList=null;
                } else {
                    cbForever.setChecked(false);
                    cbForever.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbForever.setTextColor(Color.parseColor("#333333"));
                }
            }
        });
        cbByMonth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbByMonth.isChecked()) {
                    cbForever.setChecked(false);
                    cbByWeek.setChecked(false);
                    cbFixTime.setChecked(false);
                    cbVipBirthday.setChecked(false);
                    cbByMonth.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbByMonth.setTextColor(Color.parseColor("#FF8739"));
                    teContentWeek.setText("");
                    teEndTime.setText("");
                    teStartTime.setText("");
                    mType = 3;
                    weekBeanList=null;
                } else {
                    cbByMonth.setChecked(false);
                    cbByMonth.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbByMonth.setTextColor(Color.parseColor("#333333"));
                }
            }
        });
        cbByWeek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbByWeek.isChecked()) {
                    cbByMonth.setChecked(false);
                    cbForever.setChecked(false);
                    cbFixTime.setChecked(false);
                    cbVipBirthday.setChecked(false);
                    cbByWeek.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbByWeek.setTextColor(Color.parseColor("#FF8739"));
                    teContentMonth.setText("");
                    teEndTime.setText("");
                    teStartTime.setText("");
                    mType = 2;
                    monthBeansList=null;
                } else {
                    cbByWeek.setChecked(false);
                    cbByWeek.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbByWeek.setTextColor(Color.parseColor("#333333"));
                }

            }
        });
        cbFixTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbFixTime.isChecked()) {
                    cbByMonth.setChecked(false);
                    cbByWeek.setChecked(false);
                    cbForever.setChecked(false);
                    cbVipBirthday.setChecked(false);
                    cbFixTime.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbFixTime.setTextColor(Color.parseColor("#FF8739"));
                    teContentMonth.setText("");
                    teContentWeek.setText("");
                    mType = 1;
                    monthBeansList=null;
                    weekBeanList=null;
                } else {
                    cbFixTime.setChecked(false);
                    cbFixTime.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbFixTime.setTextColor(Color.parseColor("#333333"));
                }
            }
        });
        cbVipBirthday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (cbVipBirthday.isChecked()) {
                    cbByMonth.setChecked(false);
                    cbByWeek.setChecked(false);
                    cbFixTime.setChecked(false);
                    cbForever.setChecked(false);
                    cbVipBirthday.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    cbVipBirthday.setTextColor(Color.parseColor("#FF8739"));
                    teContentMonth.setText("");
                    teContentWeek.setText("");
                    teEndTime.setText("");
                    teStartTime.setText("");
                    mType = 4;
                    monthBeansList=null;
                    weekBeanList=null;
                } else {
                    cbVipBirthday.setChecked(false);
                    cbVipBirthday.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    cbVipBirthday.setTextColor(Color.parseColor("#333333"));
                }
            }
        });


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
        tvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(list,tvType);
            }
        });

        rgDouble.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    //翻倍
                    case R.id.rb_edit_dis_double:
                        mDouble = 1;
                        break;
                    //不翻倍
                    case R.id.rb_edit_dis_no_double:
                        mDouble = 0;
                        break;
                }
            }
        });
    }
    /**
     * chooseDialog
     */
    private void showChooseDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i<mlist.size();i++){
            if (mTextView.getText().toString().equals(mlist.get(i))){
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        mDisType = position;//0减少 1赠送 2折扣
                        if (position ==2){
                            mDouble = 0;
                            rbNoDouble.setChecked(true);
//                            rbNoDouble.setChecked(false);
                            rgDouble.getChildAt(0).setEnabled(false);
                        }else {
                            rgDouble.getChildAt(0).setEnabled(true);
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }
    private boolean verify() {
        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(this, "请输入活动名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etMoney.getText())) {
            CustomToast.makeText(this, "请输入消费金额", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etGiveMoney.getText())) {
            CustomToast.makeText(this, "请输入优惠金额", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etGiveIntegral.getText())) {
            CustomToast.makeText(this, "请输入赠送积分", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (cbFixTime.isChecked()) {
            if (TextUtils.isEmpty(teStartTime.getText())) {
                CustomToast.makeText(this, "请选择开始时间", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (TextUtils.isEmpty(teEndTime.getText())) {
                CustomToast.makeText(this, "请选择结束时间", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (cbByWeek.isChecked()) {
            if (TextUtils.isEmpty(teContentWeek.getText())) {
                CustomToast.makeText(this, "请输入星期", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (cbByMonth.isChecked()) {
            if (TextUtils.isEmpty(teContentMonth.getText())) {
                CustomToast.makeText(this, "请输入天", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if (!cbForever.isChecked() && !cbVipBirthday.isChecked() && !cbByWeek.isChecked() && !cbFixTime.isChecked() && !cbByMonth.isChecked()) {
            CustomToast.makeText(this, "请选择有效时间", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 保存
     */
    private void save() {
        RequestParams params = new RequestParams();
        params.put("GID", mRechargeBean.getGID());
        params.put("RP_Type", 2);
        params.put("RP_ValidType", mType);
        params.put("RP_Name", etName.getText().toString());
        params.put("RP_RechargeMoney", etMoney.getText().toString());
        if (mDisType == 1) {
            params.put("RP_GiveMoney", etGiveMoney.getText().toString());
            params.put("RP_ReduceMoney", -1);
            params.put("RP_Discount", -1);
        } else if(mDisType ==0 ){
            params.put("RP_Discount", -1);
            params.put("RP_GiveMoney", -1);
            params.put("RP_ReduceMoney", etGiveMoney.getText().toString());
        }else {
            params.put("RP_GiveMoney", -1);
            params.put("RP_ReduceMoney", -1);
            params.put("RP_Discount", etGiveMoney.getText().toString());
        }
        params.put("RP_GivePoint", etGiveIntegral.getText().toString());
        if (cbByWeek.isChecked()) {
            params.put("RP_ValidWeekMonth", teContentWeek.getText().toString());
        } else if (cbByMonth.isChecked()) {
            params.put("RP_ValidWeekMonth", teContentMonth.getText().toString());
        } else {
            params.put("RP_ValidWeekMonth", "");
        }
        params.put("RP_ValidStartTime", teStartTime.getText().toString());
        params.put("RP_ValidEndTime", teEndTime.getText().toString());
        params.put("RP_ISDouble", mDouble);
        params.put("RP_IsOpen", 1);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(EditDisActivity.this, SweetAlertDialog.SUCCESS_TYPE);
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
                CustomToast.makeText(EditDisActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, "RechargePackage/Edit", params, callBack);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.te_start_time:
                if (cbFixTime.isChecked()) {
                    if (teStartTime.getText().toString() ==null || teStartTime.getText().toString().equals("")){
                        showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), teStartTime);
                    }else {
                        showDateDialog(DateUtil.getDateForString(teStartTime.getText().toString()), teStartTime);
                    }
                } else {
                    CustomToast.makeText(this, "选择'固定时间'后，可选择", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.te_end_time:
                if (cbFixTime.isChecked()) {
                    if (teEndTime.getText().toString() ==null || teEndTime.getText().toString().equals("")){
                        showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), teEndTime);
                    }else {
                        showDateDialog(DateUtil.getDateForString(teEndTime.getText().toString()), teEndTime);
                    }
                } else {
                    CustomToast.makeText(this, "选择'固定时间'后，可选择", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.te_content_week:
                if (cbByWeek.isChecked()) {
                    YSLUtils.setBackgroundAlpha(0.5f, this);
                    showWeekPop();
                } else {
                    CustomToast.makeText(this, "选择'按每周'后，可选择", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.te_content_month:
                if (cbByMonth.isChecked()) {
                    YSLUtils.setBackgroundAlpha(0.5f, this);
                    showMonthPop();
                } else {
                    CustomToast.makeText(this, "选择'按每月'后，可选择", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_back_activity:
                EditDisActivity.this.finish();
                break;
            case R.id.tv_add_recharge_save:
                if (verify()) {
                    save();
                }
                break;
        }
    }

    @Override
    public void onDismiss() {
        YSLUtils.setBackgroundAlpha(1.0f, EditDisActivity.this);
    }

    @Override
    public void itemChecked(WeekBean weekBean, boolean isChecked) {
        if (isChecked) {
            weekBean.setChecked(true);
        }
    }

    @Override
    public void monthitemChecked(MonthBean monthBean, boolean isChecked) {
        if (isChecked) {
            monthBean.setChecked(true);
        }
    }


    /**
     * @return 初始化月
     */
    private List<MonthBean> getMOnthBeanList() {
//        if (monthBeansList == null||monthBeansList.size()==0) {
            monthBeansList = new ArrayList<>();

            if (!teContentMonth.getText().toString().isEmpty()){
                List<String> list1=new ArrayList<String>();
                StringTokenizer st=new StringTokenizer(teContentMonth.getText().toString(),", ");
                while(st.hasMoreTokens()) {
                    list1.add(st.nextToken());
                }
                for (int i = 1;i<=31;i++){
                    MonthBean beans = new MonthBean();
                    beans.setWhichone(i + "");

                    for (int j = 0;j<list1.size();j++){
                        if (list1.get(j).equals(String.valueOf(i))){
                            beans.setChecked(true);
                        }
                    }
                    monthBeansList.add(beans);
                }
            }else {
                for (int i = 1; i <= 31; i++) {
                    MonthBean beans = new MonthBean();
                    beans.setWhichone(i + "");
                    monthBeansList.add(beans);
                }
            }
//        }
        return monthBeansList;
    }
    /**
     * @return 初始化周
     */
    private List<WeekBean> getWeekBeanList() {
//        if (weekBeanList == null||weekBeanList.size()==0) {
            if(!teContentWeek.getText().toString().isEmpty()){
                weekBeanList = new ArrayList<>();
                List<String> list=new ArrayList<String>();
                StringTokenizer st=new StringTokenizer(teContentWeek.getText().toString(),", ");
                while(st.hasMoreTokens()) {
                    list.add(st.nextToken());
                }
                for (int i = 1;i<=7;i++){
                    WeekBean beans = new WeekBean();
                    beans.setWhichone(i + "");

                    for (int j = 0;j<list.size();j++){
                        if (list.get(j).equals(String.valueOf(i))){
                            beans.setChecked(true);
                        }
                    }
                    weekBeanList.add(beans);
                }

            }else {
                weekBeanList = new ArrayList<>();

                for (int i = 1; i <= 7; i++) {
                    WeekBean beans = new WeekBean();
                    beans.setWhichone(i + "");
                    weekBeanList.add(beans);
                }
            }
//        }
        return weekBeanList;
    }

    private void showMonthPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.popwindow_choise_month, null);
        TextView ok = view.findViewById(R.id.te_ok);
        TextView cancel = view.findViewById(R.id.te_cancel);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_week);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        recyclerView.setLayoutManager(gridLayoutManager);
        monthBeansList = getMOnthBeanList();
        YSLChoiseMonthAdapter monthAdapter = new YSLChoiseMonthAdapter(this, monthBeansList, this);
        recyclerView.setAdapter(monthAdapter);
        weekPop.setContentView(view);
        weekPop.setOutsideTouchable(true);
        weekPop.setFocusable(true);
        weekPop.setBackgroundDrawable(new ColorDrawable());
        weekPop.setWidth(YSLUtils.getPhoneWidth());
        weekPop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        weekPop.setAnimationStyle(R.anim.dialogui_enter_center);
        weekPop.setAnimationStyle(R.style.pop_show_style);
        View view1 = LayoutInflater.from(this).inflate(R.layout.activity_add_recharge, null);

        weekPop.showAtLocation(view1, Gravity.BOTTOM, 0, 0);
//        weekPop.showAsDropDown(view1, 0, YSLUtils.getPhoneHeight());
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                monthStringlist.clear();
                for (int i = 0; i < monthBeansList.size(); i++) {
                    if (monthBeansList.get(i).isChecked()) {
                        monthStringlist.add(i + 1 + "");
                    }
                }
                if (monthBeansList.size() > 0) {
                    mStringmonth = monthStringlist.toString().substring(1, monthStringlist.toString().length() - 1);
                } else {
                    mStringmonth = "";
                }
                teContentMonth.setText(mStringmonth);
                weekPop.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, EditDisActivity.this);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                teContentMonth.setText("");
                weekPop.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, EditDisActivity.this);
            }
        });
    }

    /**
     * 按星期选择框
     */
    private void showWeekPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.popwindow_choise_weekend, null);
        TextView ok = view.findViewById(R.id.te_ok);
        TextView cancel = view.findViewById(R.id.te_cancel);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_week);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 7);
        recyclerView.setLayoutManager(gridLayoutManager);
        weekBeanList = getWeekBeanList();
        YSLChoiseWeekAdapter weekAdapter = new YSLChoiseWeekAdapter(this, weekBeanList, this);
        recyclerView.setAdapter(weekAdapter);
        weekPop.setContentView(view);
        weekPop.setOutsideTouchable(true);
        weekPop.setFocusable(true);
        weekPop.setBackgroundDrawable(new ColorDrawable());
        weekPop.setWidth(YSLUtils.getPhoneWidth());
        weekPop.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        weekPop.setAnimationStyle(R.style.pop_show_style);
        View view1 = LayoutInflater.from(this).inflate(R.layout.activity_add_recharge, null);
        weekPop.showAtLocation(view1, Gravity.BOTTOM, 0, 0);
//        weekPop.showAsDropDown(view1, 0, YSLUtils.getPhoneHeight());
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weekStringlist.clear();
                for (int i = 0; i < weekBeanList.size(); i++) {
                    if (weekBeanList.get(i).isChecked()) {
                        weekStringlist.add(i + 1 + "");
                    }
                }
                if (weekBeanList.size() > 0) {
                    mStringWeek = weekStringlist.toString().substring(1, weekStringlist.toString().length() - 1);
                } else {
                    mStringWeek = "";
                }
                teContentWeek.setText(mStringWeek);
                weekPop.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, EditDisActivity.this);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                teContentWeek.setText("");
                weekPop.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, EditDisActivity.this);
            }
        });
    }


    /**
     * @param date              ,日期集合
     * @param mTextView，显示的View 选择日期
     */
    private void showDateDialog(List<Integer> date, final TextView mTextView) {
        com.example.liangmutian.mypicker.DatePickerDialog.Builder builder = new com.example.liangmutian.mypicker.DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new com.example.liangmutian.mypicker.DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {
                mTextView.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
            }
            @Override
            public void onCancel() {

            }
        })
                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);

        dateDialog = builder.create();
        dateDialog.show();
    }


}
