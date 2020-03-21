package com.zhiluo.android.yunpu.ui.activity.staff;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DepListBean;
import com.zhiluo.android.yunpu.entity.ShopListBean;
import com.zhiluo.android.yunpu.entity.StaffBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 新增员工
 */
public class AddStaffActivity extends AppCompatActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_add_staff_save)
    TextView tvSave;
    @Bind(R.id.et_staff_detail_name)
    EditText etName;
    @Bind(R.id.et_staff_detail_number)
    EditText etNumber;
    @Bind(R.id.et_staff_detail_phone)
    EditText etPhone;
    @Bind(R.id.et_staff_detail_address)
    EditText etAddress;
    @Bind(R.id.tv_add_staff_birthday)
    TextView tvBirthday;
    @Bind(R.id.ll_add_staff_birthday)
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
    @Bind(R.id.et_add_staff_remark)
    EditText etRemark;
    @Bind(R.id.img_choise_birthday)
    ImageView imgChoiseBirthday;
    @Bind(R.id.te_sex_choise)
    TextView teSex;
    @Bind(R.id.te_shop_shoice)
    TextView teShop;
    @Bind(R.id.te_part_choise)
    TextView tePart;

    private DepListBean mDepListBean;
    private StaffBean mStaffListBean;
    private ArrayList<String> mDepListName;//部门名称集合
    private ArrayAdapter<String> mDepAdapter;//部门Adapter
    private ShopListBean mShopListBean;
    private ArrayList<String> mShopNameList;//店铺名称
    private ArrayAdapter<String> mShopAdapter;//店铺Adapter
    private String mDepGid, mShopGid;//部门GID、店铺GID
    private SweetAlertDialog mSweetAlertDialog;
    private Dialog dateDialog;
    private Dialog chooseDialog;
    private List<String> sexList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    private void init() {
        etName.requestFocus();
        mDepListBean = (DepListBean) getIntent().getSerializableExtra("Dep");
        mStaffListBean = (StaffBean) getIntent().getSerializableExtra("shopname");
        mDepListBean.getData().remove(0);
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
        teSex.setText("男");
        if (mStaffListBean != null) {
            teShop.setText(mStaffListBean.getSM_Name() + "");
            mShopGid = mStaffListBean.getSM_GID();
        }


    }

    private void setListener() {
        changeCheckBox(cbSaleCard);//售卡提成
        changeCheckBox(cbRechargeMoney);//充值提成
        changeCheckBox(cbRechargeTimes);// 充次提成
        changeCheckBox(cbGoodsConsume);//商品消费提成
        changeCheckBox(cbFastConsume);// 快速消费提成
        changeCheckBox(cbTimesConsume);// 计次消费提成
        //部门选择
        tePart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDepListName.size() < 1) {
                    CustomToast.makeText(AddStaffActivity.this, "无可选择部门", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    showPartDialog(mDepListName, tePart);
                }

            }
        });
        //性别选择
        teSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexDialog(sexList, teSex);
            }
        });
        //店铺选择
        teShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShopDialog(mShopNameList, teShop);
            }
        });
        //生日选择
        imgChoiseBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvBirthday.getText().toString().isEmpty()||tvBirthday.getText().toString().equals("")){
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvBirthday);
                }else {
                    showDateDialog(DateUtil.getDateForString(tvBirthday.getText().toString()), tvBirthday);
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
        //保存
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify()) {
                    addStaff();
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
                            if (position != 0){
                                mShopGid = mShopListBean.getData().get(position-1).getGID();
                            } else {
                            mShopGid = "";
                        }
                        } catch (Exception e) {
//                            mShopGid = "";
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
//if (position == 0) {
//                            mDepGid = "";
//                        } else {
//                            mDepGid = mDepListBean.getData().get(position - 1).getGID();
//                        }
                            mDepGid = mDepListBean.getData().get(position).getGID();
                        }

                        @Override
                        public void onCancel () {

                        }

                }).create();

        chooseDialog.show();
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
            }

            @Override
            public void onFailure(String msg) {

            }
        });
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
    private void addStaff() {
        RequestParams params = new RequestParams();
        params.put("GID", "");
        params.put("DM_GID", mDepGid);
        params.put("SM_GID", mShopGid);
        params.put("EM_Code", TextUtils.isEmpty(etNumber.getText()) ? "" : etNumber.getText().toString());
        params.put("EM_Name", etName.getText().toString());
        params.put("EM_Sex", getSex());
        params.put("EM_Phone", TextUtils.isEmpty(etPhone.getText()) ? "" : etPhone.getText().toString());
        params.put("EM_Birthday", TextUtils.isEmpty(tvBirthday.getText()) ? "" : tvBirthday.getText().toString());
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
                mSweetAlertDialog = new SweetAlertDialog(AddStaffActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("新增成功！")
                        .setConfirmText("确认")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                                finish();
                            }
                        }).show();
                getAllMessage();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddStaffActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, "Empl/AddEmpl", params, callBack);
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
                CustomToast.makeText(AddStaffActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
