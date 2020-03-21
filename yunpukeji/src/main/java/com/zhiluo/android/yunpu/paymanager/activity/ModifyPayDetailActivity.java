package com.zhiluo.android.yunpu.paymanager.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.paymanager.bean.PayDetailBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ZPH on 2019-05-23.
 */

public class ModifyPayDetailActivity extends BaseActivity {

    @Bind(R.id.tv_no_confirm_back)
    TextView tvNoConfirmBack;
    @Bind(R.id.tv_no_confirm_title)
    TextView tvNoConfirmTitle;
    @Bind(R.id.rl_pay_confirm_title)
    RelativeLayout rlPayConfirmTitle;
    @Bind(R.id.tv_input_type)
    TextView tvInputType;
    @Bind(R.id.et_pay_input_money)
    EditText etPayInputMoney;
    @Bind(R.id.tv_pay_input_person)
    TextView tvPayInputPerson;
    @Bind(R.id.tv_pay_input_date)
    TextView tvPayInputDate;
    @Bind(R.id.iv_pay_input_date)
    ImageView ivPayInputDate;
    @Bind(R.id.ll_pay_input_date)
    LinearLayout llPayInputDate;
    @Bind(R.id.et_pay_input_remark)
    EditText etPayInputRemark;
    @Bind(R.id.btn_comfirm)
    Button btnComfirm;
    @Bind(R.id.rl_pay_input)
    RelativeLayout rlPayInput;

    private PayDetailBean.DataBean.DataListBean mBean;
    private String mTypeName,mTypeGid;
    private Dialog datesDialog;
    private Dialog chooseDialog;
    private List<ReportMessageBean.DataBean.EmplistBean> staffbean;
    private List<String> staffNameList = new ArrayList<>();
    private String staffGid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pay_detail);
        ButterKnife.bind(this);
        tvNoConfirmTitle.setText("编辑明细");
        initData();
        Lisenter();

    }

    private void initData() {
        mBean = (PayDetailBean.DataBean.DataListBean) getIntent().getSerializableExtra("paydetail");
        staffbean = (List<ReportMessageBean.DataBean.EmplistBean>) CacheData.restoreObject("staff");

        if (staffbean!=null){
            for (int i= 0;i<staffbean.size();i++){
                staffNameList.add(staffbean.get(i).getEM_Name());
            }
        }else {
            getAllMessage();
        }

        if (mBean !=null){
            mTypeName = mBean.getET_Name();
            mTypeGid = mBean.getET_GID();
            tvInputType.setText(mTypeName);
            etPayInputMoney.setText(mBean.getED_Money() + "");
            etPayInputMoney.setSelection(etPayInputMoney.getText().toString().length());
            tvPayInputPerson.setText(mBean.getED_Expendiator());
            staffGid = mBean.getEM_GID();
            tvPayInputDate.setText(mBean.getED_Time());
            etPayInputRemark.setText(mBean.getED_Remark());
        }

    }

    private void Lisenter() {
        tvNoConfirmBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvInputType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyPayDetailActivity.this, ChoiceExpensesActivity.class);
                startActivityForResult(intent,0);
            }
        });

        tvPayInputPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVipSexDialog(staffNameList,tvPayInputPerson);
            }
        });

        ivPayInputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvPayInputDate.getText().toString().isEmpty()) {
                    showReallyDateDialog(DateUtil.getReallyDateForString(DateTimeUtil.getReallyTimeNow()), tvPayInputDate);
                } else {
                    showReallyDateDialog(DateUtil.getReallyDateForString(tvPayInputDate.getText().toString()), tvPayInputDate);
                }
            }
        });

        btnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPayDetail();
            }
        });

    }

    /***
     *
     * 获取相关缓存数据
     *
     * **/

    private void getAllMessage(){
        HttpHelper.post(ModifyPayDetailActivity.this, HttpAPI.API().PRE_LOAD, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                if (reportbean != null) {

                    staffbean = reportbean.getData().getEmplist();
                    if (staffbean !=null){
                        CacheData.saveObject("staff", staffbean);//缓存员工列表到本地
                        for (int i= 0;i<staffbean.size();i++){
                            staffNameList.add(staffbean.get(i).getEM_Name());
                        }
                    }
                }
            }

            @Override
            public void onFailure(String msg) {

                new SweetAlertDialog(ModifyPayDetailActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        .setContentText(msg)
                        .setConfirmText("了解")
                        .show();
            }
        });
    }

    /**
     * @param mlist     // 0 ,男；1，女；2，保密
     * @param mTextView ,,,
     *                  选择会员性别
     */
    private void showVipSexDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(ModifyPayDetailActivity.this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        staffGid = staffbean.get(position).getGID();

                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * @param date      ,
     * @param mTextView ,
     *                  选择日期
     */
    private void showReallyDateDialog(List<Integer> date, final TextView mTextView) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(ModifyPayDetailActivity.this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                mTextView.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2]))+ " "
                        + (dates[3] > 9 ? dates[3] : ("0" + dates[3]))
                        + ":"
                        + (dates[4] > 9 ? dates[4] : ("0" + dates[4]))
                        + ":"
                        + (dates[5] > 9 ? dates[5] : ("0" + dates[5]))
                );
            }

            @Override
            public void onCancel() {
                mTextView.setText("");
            }
        }).setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1)
                .setSelecthour(date.get(3) - 1)
                .setSelectmin(date.get(4) - 1)
                .setSelectsec(date.get(5) - 1)
                .setIsdetail(1);

        datesDialog = builder.create();
        datesDialog.show();
    }

    /**
     * 支出录入接口
     */
    private void editPayDetail(){
        if (tvInputType.getText().toString().isEmpty()||tvInputType.getText().toString().equals("")){
            CustomToast.makeText(ModifyPayDetailActivity.this,"请先选择支出类型", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etPayInputMoney.getText().toString().isEmpty()||etPayInputMoney.getText().toString().equals("")){
            CustomToast.makeText(ModifyPayDetailActivity.this,"请输入正确的金额", Toast.LENGTH_SHORT).show();
            return;
        }
        if (staffGid ==null ||staffGid.equals("")){
            CustomToast.makeText(ModifyPayDetailActivity.this,"请先选择支出人员", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestParams params = new RequestParams();

        params.put("ETGID",mTypeGid);
        params.put("Money",etPayInputMoney.getText().toString());
        params.put("Time",tvPayInputDate.getText().toString());
        params.put("ExpendiatorGID",staffGid);
        params.put("Remark",etPayInputRemark.getText().toString());
        params.put("GID",mBean.getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ModifyPayDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText("编辑成功！");
                sweetAlertDialog.setConfirmText("完成");
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        ActivityManager.getInstance().exit();
                        finish();
                    }
                });
                sweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ModifyPayDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().Expenses_EditDetail,params,callBack);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 55 && data != null) {
            mTypeName = data.getStringExtra("ET_Name");//选择的在支出类别
            mTypeGid = data.getStringExtra("ET_GID");

            tvInputType.setText(mTypeName);

        }

    }


}
