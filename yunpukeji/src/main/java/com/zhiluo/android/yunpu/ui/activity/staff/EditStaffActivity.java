package com.zhiluo.android.yunpu.ui.activity.staff;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DepListBean;
import com.zhiluo.android.yunpu.entity.EditStaffBean;
import com.zhiluo.android.yunpu.entity.ShopListBean;
import com.zhiluo.android.yunpu.entity.StaffBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class EditStaffActivity extends BaseActivity {
    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_edit_staff_save)
    TextView tvSave;
    @Bind(R.id.et_staff_detail_name)
    EditText etName;
    @Bind(R.id.et_staff_detail_number)
    EditText etNumber;
    @Bind(R.id.et_staff_detail_phone)
    EditText etPhone;
    @Bind(R.id.et_staff_detail_address)
    EditText etAddress;
    @Bind(R.id.ll_edit_staff_birthday)
    LinearLayout llBirthday;
    @Bind(R.id.cb_add_staff_tc_sale_card)
    CheckBox cbSaleCard;
    @Bind(R.id.cb_add_staff_tc_recharge_money)
    CheckBox cbRechargeMoney;
    @Bind(R.id.cb_add_staff_tc_recharge_times)
    CheckBox cbRechargeTimes;
    @Bind(R.id.cb_add_staff_tc_goods_consume)
    CheckBox cbGoodsConsume;
    @Bind(R.id.cb_add_staff_tc_fast_consume)
    CheckBox cbFastConsume;
    @Bind(R.id.cb_add_staff_tc_times_consume)
    CheckBox cbTimesConsume;
    @Bind(R.id.et_edit_staff_remark)
    EditText etRemark;
    @Bind(R.id.te_part_choise)
    TextView tePart;
    @Bind(R.id.te_shop_shoice)
    TextView teShopChoise;
    @Bind(R.id.te_sex_choise)
    TextView teSex;
    @Bind(R.id.tv_edit_staff_birthday)
    TextView teBirthday;

    private StaffBean mStaffBean;
    private DepListBean mDepListBean;
    private ArrayList<String> mDepListName;//部门名称集合
    private ArrayAdapter<String> mDepAdapter;//部门Adapter
    private ShopListBean mShopListBean;
    private ArrayList<String> mShopNameList;//店铺名称
    private ArrayAdapter<String> mShopAdapter;//店铺Adapter
    private String mDepGid, mShopGid;//部门GID、店铺GID
    private EditStaffBean mEditStaffBean;//编辑员工成功返回数据
    private SweetAlertDialog mSweetAlertDialog;
    private Dialog chooseDialog;
    private Dialog dateDialog;
    private ArrayList<String> sexList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_staff);
        ButterKnife.bind(this);
        etName.requestFocus();
        mStaffBean = (StaffBean) getIntent().getSerializableExtra("Staff");
        mDepListBean = (DepListBean) getIntent().getSerializableExtra("Dep");
        mShopListBean = (ShopListBean) CacheData.restoreObject("shop");
        mShopNameList = new ArrayList<>();
        sexList = new ArrayList<>();
        sexList.add("男");
        sexList.add("女");
        mShopNameList.add("不归属任何店铺");
        if (mShopListBean == null) {
            getShopList();
        } else {
            for (int i = 0; i < mShopListBean.getData().size(); i++) {
                mShopNameList.add(mShopListBean.getData().get(i).getSM_Name());
            }
        }
        if (mDepListBean != null) {
            mDepListName = new ArrayList<>();
            for (int i = 0; i < mDepListBean.getData().size(); i++) {
                mDepListName.add(mDepListBean.getData().get(i).getDM_Name());
            }
        }
        init(mStaffBean);
        setListener();
    }


    private void init(StaffBean bean) {
        if (bean != null) {
            mDepGid = bean.getDM_GID();
            mShopGid = bean.getSM_GID();
            etName.setText(bean.getEM_Name());
            etName.setSelection(etName.getText().toString().length());
            etNumber.setText(bean.getEM_Code());
            //所属部门
            if (bean.getDM_Name() == null || bean.getDM_Name().equals("")) {
                tePart.setText("不属于任何部门");
            } else {
                tePart.setText(bean.getDM_Name());
              /*  for (int i = 0; i < mDepListName.size(); i++) {
                    if (bean.getDM_Name().equals(mDepListName.get(i))) {
                        tePart.setText(mDepListName.get(i));
                    }
                }*/
            }
            if (mShopListBean != null) {
                //所属店铺
                if (bean.getSM_Name() == null || bean.getSM_Name().equals("")) {
                    teShopChoise.setText("默认店铺");
                } else {
                    teShopChoise.setText(bean.getSM_Name() + "");
                  /*  for (int i = 0; i < mShopNameList.size(); i++) {
                        if (bean.getDM_Name().equals(mShopNameList.get(i))) {
                            spStore.setSelection(i);
                        }
                    }*/
                }
            }
            if (bean.getEM_Sex() == 0) {
                teSex.setText("男");
            } else if (bean.getEM_Sex() == 1) {
                teSex.setText("女");
            }
            etPhone.setText(bean.getEM_Phone());
            etAddress.setText(bean.getEM_Addr());
            if (bean.getEM_Birthday() != null && !bean.getEM_Birthday().equals("null")) {
                String birthday = bean.getEM_Birthday().toString();
                if (birthday.length() > 9) {
                    birthday = birthday.substring(0, 10);
                }
                teBirthday.setText(birthday);
            }
            etRemark.setText(bean.getEM_Remark());
            //售卡
            if (bean.getEM_TipCard() == 1) {
                cbSaleCard.setChecked(true);
            } else {
                cbSaleCard.setChecked(false);
            }
            //充值
            if (bean.getEM_TipRecharge() == 1) {
                cbRechargeMoney.setChecked(true);
            } else {
                cbRechargeMoney.setChecked(false);
            }
            //充次
            if (bean.getEM_TipChargeTime() == 1) {
                cbRechargeTimes.setChecked(true);
            } else {
                cbRechargeTimes.setChecked(false);
            }
            //商品消费
            if (bean.getEM_TipGoodsConsume() == 1) {
                cbGoodsConsume.setChecked(true);
            } else {
                cbGoodsConsume.setChecked(false);
            }
            //快速消费
            if (bean.getEM_TipFastConsume() == 1) {
                cbFastConsume.setChecked(true);
            } else {
                cbFastConsume.setChecked(false);
            }
            //计次消费
            if (bean.getEM_TipTimesConsume() == 1) {
                cbTimesConsume.setChecked(true);
            } else {
                cbTimesConsume.setChecked(false);
            }
        }

        initCheckBox(cbTimesConsume, cbTimesConsume.isChecked());
        initCheckBox(cbFastConsume, cbFastConsume.isChecked());
        initCheckBox(cbGoodsConsume, cbGoodsConsume.isChecked());
        initCheckBox(cbRechargeTimes, cbRechargeTimes.isChecked());
        initCheckBox(cbRechargeMoney, cbRechargeMoney.isChecked());
        initCheckBox(cbSaleCard, cbSaleCard.isChecked());
    }

    /**
     * @param checkBox ,
     *                 改变checkbox的背景颜色
     */
    private void changeCheckBox(final CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBox.setChecked(true);
                    checkBox.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                    checkBox.setTextColor(Color.parseColor("#FF8739"));
                } else {
                    checkBox.setChecked(false);
                    checkBox.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                    checkBox.setTextColor(Color.parseColor("#333333"));
                }
            }
        });
    }

    /**
     * @param box       ,
     * @param isChecked .
     *                  初始化CheckBox
     */
    private void initCheckBox(CheckBox box, boolean isChecked) {
        if (isChecked) {
            box.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
            box.setTextColor(Color.parseColor("#FF8739"));
        } else {
            box.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
            box.setTextColor(Color.parseColor("#333333"));
        }
    }

    private void setListener() {
        changeCheckBox(cbTimesConsume);
        changeCheckBox(cbFastConsume);
        changeCheckBox(cbGoodsConsume);
        changeCheckBox(cbRechargeTimes);
        changeCheckBox(cbRechargeMoney);
        changeCheckBox(cbSaleCard);
        teShopChoise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShopDialog(mShopNameList, teShopChoise);
            }
        });
        tePart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDepListName.size() < 1) {
                    CustomToast.makeText(EditStaffActivity.this, "没有可选部门，请返回添加部门", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    showPartDialog(mDepListName, tePart);
                }
            }
        });
        teSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexDialog(sexList, teSex);
            }
        });
        //返回
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //选择生日
        llBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (teBirthday.getText().toString().isEmpty()){
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), teBirthday);
                }else {
                    showDateDialog(DateUtil.getDateForString(teBirthday.getText().toString()), teBirthday);
                }
                // choiceBirthday();
            }
        });
        //保存
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify()) {
                    edit();
                }
            }
        });

    }

    /**
     * @param date      ,
     * @param mTextView ,
     *                  生日选择对话框
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
        }).setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);
        builder.setMaxYear(DateUtil.getYear());
        builder.setMaxMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
        builder.setMaxDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        dateDialog = builder.create();
        dateDialog.show();
    }

    /**
     * @param mlist     .
     * @param mTextView ,
     *                  性别选择对话框
     */
    private void showSexDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0;i<mlist.size();i++){
            if (mlist.get(i).equals(mTextView.getText().toString())){
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * @param mlist     .
     * @param mTextView ,
     *                  条件选择对话框
     */
    private void showShopDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0;i<mlist.size();i++){
            if (mlist.get(i).equals(mTextView.getText().toString())){
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        try {
                            mShopGid = mShopListBean.getData().get(position).getGID();
                        } catch (Exception e) {
                            mShopGid = "";
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * @param mlist     .
     * @param mTextView ,
     *                  条件选择对话框
     */
    private void showPartDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0;i<mlist.size();i++){
            if (mlist.get(i).equals(mTextView.getText().toString())){
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        mDepGid = mDepListBean.getData().get(position).getGID();

                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * 获取店铺列表
     */
    private void getShopList() {
        HttpHelper.post(this, "Shops/GetShopsList", new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mShopListBean = CommonFun.JsonToObj(responseString, ShopListBean.class);
                CacheData.saveObject("shop", mShopListBean);
                for (int i = 0; i < mShopListBean.getData().size(); i++) {
                    mShopNameList.add(mShopListBean.getData().get(i).getSM_Name());
                }
                if (mStaffBean != null) {
                    //所属店铺
                    if (mStaffBean.getSM_Name() == null || mStaffBean.getSM_Name().equals("")) {
                        teShopChoise.setText("不属于任何店铺");
                    } else {
                        teShopChoise.setText(mStaffBean.getDM_Name());
                    }
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 弹出时间选择器，用于选择生日
     */
    protected void choiceBirthday() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(EditStaffActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int momth = monthOfYear + 1;
                        EditStaffActivity.this.teBirthday.setText(year + "-" + momth + "-" + dayOfMonth);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        DatePicker dp = datePickerDialog.getDatePicker();
        dp.setMaxDate(System.currentTimeMillis());//设置可选的最大日期
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.show();
    }

    /**
     * 验证输入是否合法
     *
     * @return
     */
    private boolean verify() {
        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(this, "员工姓名不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(tePart.getText())) {
            CustomToast.makeText(this, "请选择员工所属部门！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!TextUtils.isEmpty(etPhone.getText())) {
            if (etPhone.getText().toString().length() != 11) {
                CustomToast.makeText(this, "手机号码长度不正确！", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    /**
     * @return , 1男，2女，3未选择
     * 性别判断
     */
    private int getSex() {
        int sex;
        if (teSex.getText().toString().equals("男")) {
            sex = 0;
        } else if (teSex.getText().toString().equals("女")) {
            sex = 1;
        } else {
            sex = 3;
        }
        return sex;
    }

    /**
     * 提交编辑
     */
    private void edit() {
        RequestParams params = new RequestParams();
        params.put("GID", mStaffBean.getGID());
        params.put("DM_GID", mDepGid);
        params.put("SM_GID", mShopGid);
        params.put("EM_Code", TextUtils.isEmpty(etNumber.getText()) ? "" : etNumber.getText().toString());
        params.put("EM_Name", etName.getText().toString());
        params.put("EM_Sex", getSex());
        params.put("EM_Phone", TextUtils.isEmpty(etPhone.getText()) ? "" : etPhone.getText().toString());
        params.put("EM_Birthday", TextUtils.isEmpty(teBirthday.getText()) ? "" : teBirthday.getText().toString());
        params.put("EM_Addr", TextUtils.isEmpty(etAddress.getText()) ? "" : etAddress.getText().toString());
        params.put("EM_Remark", TextUtils.isEmpty(etRemark.getText()) ? "" : etRemark.getText().toString());
        params.put("EM_TipCard", cbSaleCard.isChecked() ? 1 : 0);
        params.put("EM_TipRecharge", cbRechargeMoney.isChecked() ? 1 : 0);
        params.put("EM_TipChargeTime", cbRechargeTimes.isChecked() ? 1 : 0);
        params.put("EM_TipGoodsConsume", cbGoodsConsume.isChecked() ? 1 : 0);
        params.put("EM_TipFastConsume", cbFastConsume.isChecked() ? 1 : 0);
        params.put("EM_TipTimesConsume", cbTimesConsume.isChecked() ? 1 : 0);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mEditStaffBean = CommonFun.JsonToObj(responseString, EditStaffBean.class);
                EventBus.getDefault().post(mEditStaffBean.getData());
                mSweetAlertDialog = new SweetAlertDialog(EditStaffActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("编辑成功！")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                                finish();
                            }
                        })
                        .setConfirmText("确认")
                        .show();
                getAllMessage();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditStaffActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, "Empl/EditEmpl", params, callBack);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
            mSweetAlertDialog = null;
        }
    }

    /***
     *
     * 更新部门员工缓存
     *
     * **/

    private void getAllMessage(){
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                if (reportbean!=null){

                    List<ReportMessageBean.DataBean.EmplistBean> staffbean = reportbean.getData().getEmplist();
                    CacheData.saveObject("staff", staffbean);//缓存员工列表到本地
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditStaffActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
