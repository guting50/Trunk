package com.zhiluo.android.yunpu.paymanager.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsManagerActivity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.activity.LoginActivity;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.paymanager.activity.ChoiceExpensesActivity;
import com.zhiluo.android.yunpu.paymanager.activity.PayManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.good.GoodsWarehousingActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by ZPH on 2019-05-21.
 */

public class PayInputFragment extends Fragment {


    private TextView mTvType, mTvPerson, mTvDate;
    private EditText mEtMoney, mEtRemark;
    private ImageView mIvdate;
    private Button mBtnComfirm;
    private View mView;
    private Dialog datesDialog;
    private Dialog chooseDialog;
    private List<ReportMessageBean.DataBean.EmplistBean> staffbean;
    private List<String> staffNameList = new ArrayList<>();
    private String staffGid;
    private InputReceiver inputReceiver;
    private String mTypeName,mTypeGid;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(inputReceiver);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pay_input, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        setListener();

        if (inputReceiver == null) {
            inputReceiver = new InputReceiver();
        }
        IntentFilter filer1 = new IntentFilter();
        filer1.addAction("android.intent.action.INPUT_BROADCAST");
        getActivity().registerReceiver(inputReceiver, filer1);
    }



    protected void initView(View view) {
        mView = view;
        mTvType = mView.findViewById(R.id.tv_input_type);
        mEtMoney = mView.findViewById(R.id.et_pay_input_money);
        mTvPerson = mView.findViewById(R.id.tv_pay_input_person);
        mTvDate = mView.findViewById(R.id.tv_pay_input_date);
        mIvdate = mView.findViewById(R.id.iv_pay_input_date);
        mEtRemark = mView.findViewById(R.id.et_pay_input_remark);
        mBtnComfirm = mView.findViewById(R.id.btn_comfirm);

        mTvDate.setText(DateTimeUtil.getReallyTimeNow());

    }

    private void initData() {
        staffbean = (List<ReportMessageBean.DataBean.EmplistBean>) CacheData.restoreObject("staff");

        if (staffbean!=null){
            for (int i= 0;i<staffbean.size();i++){
                staffNameList.add(staffbean.get(i).getEM_Name());
            }
        }else {
            getAllMessage();
        }

    }

    private void setListener() {
        mTvType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChoiceExpensesActivity.class);
                startActivityForResult(intent,0);
            }
        });

        mTvPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVipSexDialog(staffNameList,mTvPerson);
            }
        });

        mIvdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTvDate.getText().toString().isEmpty()) {
                    showReallyDateDialog(DateUtil.getReallyDateForString(DateTimeUtil.getReallyTimeNow()), mTvDate);
                } else {
                    showReallyDateDialog(DateUtil.getReallyDateForString(mTvDate.getText().toString()), mTvDate);
                }
            }
        });

        mBtnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpenses();
            }
        });

    }

    /***
     *
     * 获取相关缓存数据
     *
     * **/

    private void getAllMessage(){
        HttpHelper.post(getActivity(), HttpAPI.API().PRE_LOAD, new CallBack() {
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

                new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
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
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(getActivity());
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
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(getActivity());
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
    private void AddExpenses(){
        if (mTvType.getText().toString().isEmpty()||mTvType.getText().toString().equals("")){
            CustomToast.makeText(getActivity(),"请先选择支出类型", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEtMoney.getText().toString().isEmpty()||mEtMoney.getText().toString().equals("")){
            CustomToast.makeText(getActivity(),"请输入正确的金额", Toast.LENGTH_SHORT).show();
            return;
        }
        if (staffGid ==null ||staffGid.equals("")){
            CustomToast.makeText(getActivity(),"请先选择支出人员", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestParams params = new RequestParams();

        params.put("ETGID",mTypeGid);
        params.put("Money",mEtMoney.getText().toString());
        params.put("Time",mTvDate.getText().toString());
        params.put("ExpendiatorGID",staffGid);
        params.put("Remark",mEtRemark.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText("支出录入成功！");
                sweetAlertDialog.setConfirmText("完成");
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        clearAll();
                    }
                });
                sweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(getActivity(), HttpAPI.API().AddExpenses,params,callBack);

    }

    private void clearAll(){
        mTypeGid = null;
        mTypeName = null;
        mTvType.setText("");
        mEtMoney.setText("");
        mTvPerson.setText("");
        mTvDate.setText(DateTimeUtil.getReallyTimeNow());
        mEtRemark.setText("");
    }

    class InputReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getBundleExtra("inputtype")!=null) {
                mTypeName = intent.getBundleExtra("inputtype").getString("ET_Name", "");
                mTypeGid = intent.getBundleExtra("inputtype").getString("ET_GID", "");

                mTvType.setText(mTypeName);
            }
        }
    }

}
