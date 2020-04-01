package com.zhiluo.android.yunpu.member.consume.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.activity.StaffCommissionActivity;
import com.zhiluo.android.yunpu.gift.bean.ChargeListBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.consume.adapter.TimesPayAdapter;
import com.zhiluo.android.yunpu.mvp.model.PasswordVerifyBean;
import com.zhiluo.android.yunpu.print.bean.JCXF_Success_Bean;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.sms.jsonbean.SmsSwitch;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by YSL on 2018-08-16.
 */

public class MemberChargePayActivity extends BaseActivity implements TimesPayAdapter.employeeClick, TimesPayAdapter.imgicClick {


    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_pay_confirm_order_name)
    EditText tvPayConfirmOrderName;
    @Bind(R.id.tv_pay_confirm_order)
    EditText tvPayConfirmOrder;
    @Bind(R.id.tv_pay_confirm_receivable)
    TextView tvPayConfirmReceivable;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.cb_short_message)
    CheckBox cbShortMessage;
    @Bind(R.id.cb_print)
    CheckBox cbPrint;
    @Bind(R.id.btn_pay_confirm_submit)
    TextView btnPayConfirmSubmit;
    @Bind(R.id.et_constom_pay_detail)
    EditText etConstomPayDetail;
    @Bind(R.id.et_add_member_order_date)
    TextView tvOrderTime;
    @Bind(R.id.iv_add_member_order_date)
    ImageView igOrderTime;
    @Bind(R.id.lr_order_time)
    LinearLayout lrOrderTime;


    private SweetAlertDialog mSweetAlertDialog;
    private List<ReportMessageBean.DataBean.EmplistBean> mStaffInfo;//提成员工信息
    private StringBuilder mStaffName;//提成员工姓名
    private int empos;
    private ArrayList<String> mStaffListGid = new ArrayList<>();
    private Map<String, ArrayList<String>> mStaffListGidList = new HashMap<>();//提成员工GID
    private Map<String, List<ReportMessageBean.DataBean.EmplistBean>> mStaffInfoList = new HashMap<>();

    private TimesPayAdapter mTimesPayAdapter;
    private List<ChargeListBean.DataBean> mList = new ArrayList<>();//提交的商品信息
    private String membercard;
    private String membername;
    private String ordder;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean isStaff = false;//是否开启员工提成
    private boolean isConsume = false;//是否开启消费密码验证
    private Dialog datesDialog;
    private int pribean = 0;
    private String MDZZ;
    private IntentHandler intentHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_pay_confirm);
        ButterKnife.bind(this);
        initData();
        initLisinner();

        setCbPrint();
        setCbShortMessage("012");
    }

    private void initLisinner() {
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btnPayConfirmSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (isConsume) {
                        showPasswordDialog();
                    } else {
                        postusecharge();
                    }
                }

            }
        });


//        rlPayConfirmEmployee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isStaff) {
//                    Intent intent = new Intent(MemberChargePayActivity.this, StaffCommissionActivity.class);
//                    Bundle bundle = new Bundle();
//                    intent.putExtra("PAGE_FLAG", "JCXF");
//                    bundle.putSerializable("mStaffInfo", (Serializable) mStaffInfo);
//                    intent.putExtras(bundle);
//                    startActivityForResult(intent, 888);
//                } else {
//                    CustomToast.makeText(MemberChargePayActivity.this, "员工提成未开启，请确认后再尝试", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        igOrderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvOrderTime.getText().toString().isEmpty()) {
                    showReallyDateDialog(DateUtil.getReallyDateForString(DateTimeUtil.getReallyTimeNow()), tvOrderTime);
                } else {
                    showReallyDateDialog(DateUtil.getReallyDateForString(tvOrderTime.getText().toString()), tvOrderTime);
                }
            }
        });

        cbPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pribean == 1) {
                    if (cbPrint.isChecked()) {
                        cbPrint.setChecked(true);
                    } else {
                        cbPrint.setChecked(false);
                    }
                } else {
                    CustomToast.makeText(MemberChargePayActivity.this, "打印开关未开启，请设置", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    /**
     * 密码输入框
     */
    private void showPasswordDialog() {
        LayoutInflater inflater = getLayoutInflater();
        final View dialog = inflater.inflate(R.layout.input_password_dialog_layout, (ViewGroup) findViewById(R.id.input_dialog));
        final EditText etPassword = (EditText) dialog.findViewById(R.id.et_input_dialog_password);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_input_cancel);
        Button btnConfirm = (Button) dialog.findViewById(R.id.btn_input_confirm);
        AlertDialog.Builder builder = new AlertDialog.Builder(MemberChargePayActivity.this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setContentView(dialog);
        alertDialog.setView(dialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.put("VCH_Card", membercard);
                params.put("VCH_Pwd", etPassword.getText().toString());
                HttpHelper.post(MemberChargePayActivity.this, HttpAPI.API().PASSWORDVERIFY, params, new CallBack() {
                    @Override
                    public void onSuccess(String responseString, Gson gson) {
                        PasswordVerifyBean bean = CommonFun.JsonToObj(responseString, PasswordVerifyBean.class);
                        if (bean.isSuccess()) {
                            postusecharge();
                            alertDialog.dismiss();
                        } else {
                            CustomToast.makeText(MemberChargePayActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        CustomToast.makeText(MemberChargePayActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
            }
        });
        alertDialog.show();
    }

    /**
     * @param date      ,
     * @param mTextView ,
     *                  选择日期
     */
    private void showReallyDateDialog(List<Integer> date, final TextView mTextView) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                mTextView.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])) + " "
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


    private void initData() {
        intentHandler = new IntentHandler();
        tvOrderTime.setText(DateTimeUtil.getReallyTimeNow());
        mStaffName = new StringBuilder();
        mList = (List<ChargeListBean.DataBean>) getIntent().getSerializableExtra("GOODS_LIST");
        MDZZ = getIntent().getStringExtra("MDZZ");
        membercard = getIntent().getStringExtra("membercard");
        ordder = getIntent().getStringExtra("WO_OrderCode");
        membername = getIntent().getStringExtra("membername");

        tvPayConfirmOrder.setText(membercard);
        tvPayConfirmReceivable.setText(ordder);
        tvPayConfirmOrderName.setText(membername);

        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {
                    //员工提成
                    if ("301".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            isStaff = true;
                        } else {
                            isStaff = false;
                        }
                    }
                    //消费密码验证
                    if ("204".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            isConsume = true;
                        } else {
                            isConsume = false;
                        }
                    }
                    Log.d(TAG, "initData: " + i + mSwitchEntity.get(i).getSS_Name());
                }
            }
        }

        if (mList != null) {
            if (mTimesPayAdapter != null) {
                mTimesPayAdapter = null;
            }
            mTimesPayAdapter = new TimesPayAdapter(MemberChargePayActivity.this, mList, MemberChargePayActivity.this, MemberChargePayActivity.this, isStaff);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(mTimesPayAdapter);
        }


    }


    /**
     * @param code ,参照SmsSwitch实体类的值
     *             根据短信发送开关是否打开，设置checkbox
     */
    private void setCbShortMessage(String code) {
        try {
            SmsSwitch.DataBean smsSwitch = YSLUtils.getSmsSwitch(code);
            if (smsSwitch != null) {
                if (smsSwitch.getST_State() == null || !smsSwitch.getST_State().equals("1")) {
                    cbShortMessage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CustomToast.makeText(MemberChargePayActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
                            cbShortMessage.setChecked(false);
                        }
                    });
                } else {
                    cbShortMessage.setChecked(true);
                }
            } else {
                getSmsSet(code);
            }

        } catch (Exception e) {
            cbShortMessage.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 获取短信开关
     */
    private void getSmsSet(final String code) {
        HttpHelper.post(MemberChargePayActivity.this, HttpAPI.API().SMS_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SmsSwitch bean = CommonFun.JsonToObj(responseString, SmsSwitch.class);
                for (int i = 0; i < bean.getData().size(); i++) {
                    if (bean.getData().get(i).getST_Code().equals(code)) {
                        if (bean.getData().get(i).getST_State() == null || !bean.getData().get(i).getST_State().equals("1")) {
                            cbShortMessage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CustomToast.makeText(MemberChargePayActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
                                    cbShortMessage.setChecked(false);
                                }
                            });
                        } else {
                            cbShortMessage.setChecked(true);
                        }
                    }
                }


                CacheData.saveObject("shortmessage", bean);//缓存短信开关到本地
                Li("获取短信开关成功");
            }

            @Override
            public void onFailure(String msg) {
                Li("获取短信开关" + msg);
            }
        });
    }

    /**
     * 根据打印开关是否打开，设置checkbox
     */
    private void setCbPrint() {
        try {
            if (YSLUtils.getPrints() != null) {

                if (YSLUtils.getPrints().getPS_IsEnabled() != 1) {
                    cbPrint.setChecked(false);
                    pribean = 0;
                } else {
                    cbPrint.setChecked(true);
                    pribean = 1;
                }
            }

        } catch (Exception e) {
            cbPrint.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //获取提成员工
        if (resultCode == 1002) {
            mStaffInfo = (List<ReportMessageBean.DataBean.EmplistBean>) data.getSerializableExtra("staff");
            if (mStaffInfo != null) {
//                mStaffListGid.clear();
//                for (int i = 0; i < mStaffInfo.size(); i++) {
//                    mStaffListGid.add(mStaffInfo.get(i).getGID());
//                    if (i == mStaffInfo.size() - 1) {
//                        String str = mStaffInfo.get(i).getEM_Name();
//                        mStaffName.append(str);
//                    } else {
//                        mStaffName.append(mStaffInfo.get(i).getEM_Name() + "、");
//                    }
//                }
//                tvPayConfirmEmployee.setText(mStaffName);
//                mStaffName.delete(0, mStaffName.length());//清空数据

                ArrayList<String> list = new ArrayList<>();
                if (mStaffInfo.size() > 0) {
                    for (int i = 0; i < mStaffInfo.size(); i++) {
                        list.add(mStaffInfo.get(i).getGID());
                        if (i == mStaffInfo.size() - 1) {
                            mStaffName.append(mStaffInfo.get(i).getEM_Name());
                        } else {
                            mStaffName.append(mStaffInfo.get(i).getEM_Name() + "、");
                        }
                    }
                } else {
                    list.add("");
                    mStaffName.append("");
                }

                mStaffListGid = list;
                mStaffInfoList.put(String.valueOf(empos), mStaffInfo);
                mStaffListGidList.put(String.valueOf(empos), mStaffListGid);
                mList.get(empos).setEmployeeValue(mStaffName + "");
                mTimesPayAdapter.notifyDataSetChanged();
                mStaffName.delete(0, mStaffName.length());//清空数据
            }
        }
    }

    /**
     * 完成计次消费
     */
    private void postusecharge() {

        RequestParams params = new RequestParams();
        params.put("VIP_Card", membercard);
        params.put("WO_OrderCode", ordder);
        params.put("WO_Remark", etConstomPayDetail.getText().toString());
        for (int j = 0; j < mList.size(); j++) {
            params.put("wouldOrderDetail[" + j + "][GID]", mList.get(j).getGID());
            params.put("wouldOrderDetail[" + j + "][SG_GID]", mList.get(j).getSG_GID());
            params.put("wouldOrderDetail[" + j + "][SG_Name]", mList.get(j).getSG_Name());
            params.put("wouldOrderDetail[" + j + "][SG_Code]", mList.get(j).getSG_Code());
            params.put("wouldOrderDetail[" + j + "][SG_Price]", mList.get(j).getSG_Price() + "");
            params.put("wouldOrderDetail[" + j + "][SGC_ClasName]", mList.get(j).getSGC_ClasName());
            params.put("wouldOrderDetail[" + j + "][WOD_UseNumber]", mList.get(j).getCount());
            params.put("wouldOrderDetail[" + j + "][WR_GID]", mList.get(j).getWR_GID());

            if (mStaffListGidList.get(String.valueOf(j)) != null) {
                for (int i = 0; i < mStaffListGidList.get(String.valueOf(j)).size(); i++) {//提成员工GID
                    params.put("wouldOrderDetail[" + j + "][EM_GIDList][" + i + "]", mStaffListGidList.get(String.valueOf(j)).get(i));
                }
            }
        }

//        if (mStaffInfo != null) {//员工提成
//            for (int j = 0; j < mStaffInfo.size(); j++) {
//                params.put("wouldOrderDetail[" + j + "][EM_GIDList]", mStaffInfo.get(j).getGID());
//            }
//        }
        params.put("IS_Sms", cbShortMessage.isChecked());//是否发短信
        params.put("OrderTime", tvOrderTime.getText().toString());
        params.put("Device", 1);//设备

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final JCXF_Success_Bean jcxf_success_bean = CommonFun.JsonToObj(responseString, JCXF_Success_Bean.class);
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MemberChargePayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText("计次结算成功!");
                sweetAlertDialog.setConfirmText("确认");
                sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (cbPrint.isChecked()) {
                            //打印小票
                            new HttpGetPrintContents(MemberChargePayActivity.this, MyApplication.JCXF_PRINT_TIMES, jcxf_success_bean.getData().getGID(), intentHandler).JCXF();
                        } else {
                            Intent intent = new Intent(MemberChargePayActivity.this, MemberChargeManagementActivity.class);
                            if (MDZZ != null && !MDZZ.equals("")) {
                                intent.putExtra("MDZZ", MDZZ);
                            }
                            startActivity(intent);
                            finish();
                        }

                    }
                });
                sweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                mSweetAlertDialog = new SweetAlertDialog(MemberChargePayActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("发送失败");
                if (msg.equals("BuySms")) {
                    mSweetAlertDialog.setContentText("短信不足，请前去pc端购买!");
                } else {
                    mSweetAlertDialog.setContentText(msg + "!");
                }
                mSweetAlertDialog.setConfirmText("了解");
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                        startActivity(new Intent(MemberChargePayActivity.this, MemberChargeManagementActivity.class));
                    }
                });
                mSweetAlertDialog.show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(MemberChargePayActivity.this, HttpAPI.API().CHAORDER, params, callBack);
    }

    public class IntentHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent;
            switch (msg.what) {
                case 1:
                    intent = new Intent(MemberChargePayActivity.this, MemberChargeManagementActivity.class);
                    if (MDZZ != null && !MDZZ.equals("")) {
                        intent.putExtra("MDZZ", MDZZ);
                    }
                    startActivity(intent);
                    finish();
                    break;

            }
        }
    }


    @Override
    public void click(View v) {

        empos = (int) v.getTag();
        if (isStaff) {
            Intent intent = new Intent(MemberChargePayActivity.this, StaffCommissionActivity.class);
            mStaffInfo = mStaffInfoList.get(String.valueOf(empos));
            intent.putExtra("PAGE_FLAG", "JCXF");

            Bundle bundle = new Bundle();
            bundle.putSerializable("mStaffInfo", (Serializable) mStaffInfo);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1002);
        } else {
            CustomToast.makeText(MemberChargePayActivity.this, "员工提成未开启，请确认后再尝试", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void imagicClick(View view) {

        int pos = (int) view.getTag();
        for (int i = 0; i < mList.size(); i++) {
            mStaffInfoList.put(String.valueOf(i), mStaffInfoList.get(String.valueOf(pos)));
            mList.get(i).setEmployeeValue(mList.get(pos).getEmployeeValue());
        }
        mTimesPayAdapter.notifyDataSetChanged();

    }
}
