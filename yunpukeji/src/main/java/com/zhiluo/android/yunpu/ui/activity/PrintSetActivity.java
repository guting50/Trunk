package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.mvp.model.PrintParam;
import com.zhiluo.android.yunpu.mvp.presenter.IPrintSetPresenter;
import com.zhiluo.android.yunpu.mvp.view.IPrintSetView;
import com.zhiluo.android.yunpu.print.bean.PrintSetBean;
import com.zhiluo.android.yunpu.setting.bluetooth.activity.BlueToothActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Date: 2017/10/24 16:17
 * Author: 罗咏哲
 * Description: 打印设置
 * Version:
 */
public class PrintSetActivity extends BaseActivity {
    @Bind(R.id.et_print_set_onekey)
    EditText etPrintSetOnekey;
    @Bind(R.id.ll_onekey)
    LinearLayout llOnekey;
    private TextView mTvPrint;
    private RelativeLayout mRlBluetooth;//蓝牙设置
    private RadioGroup mRgPrintSwitch;//打印开关
    private RadioButton mRbOpen, mRbClose;
    private EditText mEtOpenCardTime, mEtRechargeMoney, mEtRechargeTimes, mEtGoodsConsume, mEtFastConsume, mEtTimesConsume, mEtHandDutyTime,
            mEtIntegralExchange;//, mEtTcConsumeTimes
    private Spinner printPaper;
    private IPrintSetPresenter mPresenter;
    private IPrintSetView mView;
    private SweetAlertDialog mAlertDialog;
    private int mPrintSwitch = 1;
    private PrintParam mParam;
    private HashMap<String, String> mPrintMap = new HashMap<>();
    private int i;//
    private ScrollView mScrollView;
    private PrintSetBean mPrintSetBean;

    private ArrayAdapter<String> mSpinnerPaperAdapter;
    private ArrayList<String> paperTypeList = new ArrayList<>();//类型
    private int pos = 0;
    private int paperType = 2;
    private LoginUpbean mLoginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_set);
        ButterKnife.bind(this);
        initView();
        loadData();
        setListener();

        String printName = (String) uSharedPreferencesUtiles.get(PrintSetActivity.this,
                "BluetoothName", "");
        if (printName != null && !"".equals(printName)) {
            mTvPrint.setText(printName);
        } else {
            mTvPrint.setText("选择打印机");
        }
    }

    private void initView() {

        mTvPrint = (TextView) findViewById(R.id.tv_print_set_print);
        mScrollView = (ScrollView) findViewById(R.id.sll_print_set_num);
        mRlBluetooth = (RelativeLayout) findViewById(R.id.tv_print_set_connect_bluetooth);
        mRgPrintSwitch = (RadioGroup) findViewById(R.id.rg_print_set_switch);
        mRbOpen = (RadioButton) findViewById(R.id.rb_print_set_open);
        mRbClose = (RadioButton) findViewById(R.id.rb_print_set_close);
        mRgPrintSwitch.check(mRbClose.getId());
        mScrollView.setVisibility(View.GONE);

        mEtOpenCardTime = (EditText) findViewById(R.id.et_print_set_opencard_money);
        mEtRechargeMoney = (EditText) findViewById(R.id.et_print_set_recharge_money);
        mEtRechargeTimes = (EditText) findViewById(R.id.et_print_set_recharge_times);
        mEtGoodsConsume = (EditText) findViewById(R.id.et_print_set_goods_consume);
        mEtFastConsume = (EditText) findViewById(R.id.et_print_set_fast_consume);
        mEtTimesConsume = (EditText) findViewById(R.id.et_print_set_times_consume);
//        mEtTcConsumeTimes = (EditText) findViewById(R.id.et_print_set_tc_consume);
        mEtIntegralExchange = (EditText) findViewById(R.id.et_print_set_exchange_integral);
        mEtHandDutyTime = findViewById(R.id.et_print_set_hand_duty);
        printPaper = findViewById(R.id.sp_print_paper);
        paperTypeList.add("58mm纸张");
        paperTypeList.add("80mm纸张");

        mSpinnerPaperAdapter = new ArrayAdapter<String>(PrintSetActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, paperTypeList);
        printPaper.setAdapter(mSpinnerPaperAdapter);
        printPaper.setSelection(pos);


    }


    /***
     *
     * 更新打印设置缓存
     *
     * **/

    private void getAllMessage() {
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                if (reportbean != null) {

                    ReportMessageBean.DataBean.PrintSetBean printbean = reportbean.getData().getPrintSet();
                    if (printbean != null) {
                        CacheData.saveObject("print", printbean);//缓存等级列表到本地
                    }

                    if (printbean.getPS_IsEnabled() == 1) {
                        MyApplication.PRINT_IS_OPEN = true;
                    } else {
                        MyApplication.PRINT_IS_OPEN = false;
                    }
                    if (printbean != null && printbean.getPrintTimesList() != null) {
                        for (int i = 0; i < printbean.getPrintTimesList().size(); i++) {
                            ReportMessageBean.DataBean.PrintSetBean.PrintTimesListBean bean = printbean.getPrintTimesList().get(i);
                            if ("HYCZ".equals(bean.getPT_Code())) {
                                MyApplication.HYCZ_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("HYCC".equals(bean.getPT_Code())) {
                                MyApplication.HYCC_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("SPXF".equals(bean.getPT_Code())) {
                                MyApplication.SPXF_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("YJJY".equals(bean.getPT_Code())) {
                                MyApplication.YJJY_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("KSXF".equals(bean.getPT_Code())) {
                                MyApplication.KSXF_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("JCXF".equals(bean.getPT_Code())) {
                                MyApplication.JCXF_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("JFDH".equals(bean.getPT_Code())) {
                                MyApplication.JFDH_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("TCXF".equals(bean.getPT_Code())) {
                                MyApplication.TCXF_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("HYKK".equals(bean.getPT_Code())) {
                                MyApplication.HYKK_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("JB".equals(bean.getPT_Code())) {
                                MyApplication.JB_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("RK".equals(bean.getPT_Code())) {
                                MyApplication.RK_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("CK".equals(bean.getPT_Code())) {
                                MyApplication.CK_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("SPTH".equals(bean.getPT_Code())) {
                                MyApplication.SPTH_PRINT_TIMES = bean.getPT_Times();
                            }
                            if ("XSXF".equals(bean.getPT_Code())) {
                                MyApplication.XSXF_PRINT_TIMES = bean.getPT_Times();
                            }
                        }
                    }

                }

            }

            @Override
            public void onFailure(String msg) {
            }
        });
    }


    private void loadData() {
        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        mPrintMap.put("HYCZ", "1");
        mPrintMap.put("HYCC", "1");
        mPrintMap.put("SPXF", "1");
        mPrintMap.put("KSXF", "1");
        mPrintMap.put("JCXF", "1");
        mPrintMap.put("JFDH", "1");
        mPrintMap.put("HYKK", "1");
        mPrintMap.put("JB", "1");

        try {
            if (mLoginBean != null) {
                if (mLoginBean.getData().getShopList().get(0).getSM_FunctionList() != null && !mLoginBean.getData().getShopList().get(0).getSM_FunctionList().equals("")) {

                    JSONArray jsonArray = new JSONArray(mLoginBean.getData().getShopList().get(0).getSM_FunctionList());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.get("code").equals("1JY")) {
                            Integer s = (Integer) jsonObject.get("value");
                            if (s == 1) {
                                MyApplication.isOneKey = true;
                                llOnekey.setVisibility(View.VISIBLE);
                            }

                        }
                    }
                } else {
                    MyApplication.isOneKey = false;
                    llOnekey.setVisibility(View.GONE);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mPresenter = new IPrintSetPresenter(this);
        mPresenter.onCreate("");
        mView = new IPrintSetView() {
            @Override
            public void getPrintSetSuccess(PrintSetBean bean) {
                mPrintSetBean = bean;
                if (mPrintSetBean != null) {
                    if (bean.getData().getPS_IsEnabled() == 1) {
                        mRgPrintSwitch.check(mRbOpen.getId());
                        if (bean.getData().getPS_PaperType() == 2) {
                            printPaper.setSelection(0);
                        } else if (bean.getData().getPS_PaperType() == 3) {
                            printPaper.setSelection(1);
                        }
                    } else {
                        mRgPrintSwitch.check(mRbClose.getId());
                    }
                    if (bean.getData().getPrintTimesList() != null) {
                        for (int j = 0; j < bean.getData().getPrintTimesList().size(); j++) {

                            if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("HYKK")) {
                                mEtOpenCardTime.setText("" + bean.getData().getPrintTimesList().get(j).getPT_Times());
                                mEtOpenCardTime.setSelection(mEtOpenCardTime.getText().toString().length());
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("HYCZ")) {
                                mEtRechargeMoney.setText("" + bean.getData().getPrintTimesList().get(j).getPT_Times());
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("KSXF")) {
                                mEtFastConsume.setText("" + bean.getData().getPrintTimesList().get(j).getPT_Times());
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("JFDH")) {
                                mEtIntegralExchange.setText("" + bean.getData().getPrintTimesList().get(j).getPT_Times());
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("SPXF")) {
                                mEtGoodsConsume.setText("" + bean.getData().getPrintTimesList().get(j).getPT_Times());
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("HYCC")) {
                                mEtRechargeTimes.setText("" + bean.getData().getPrintTimesList().get(j).getPT_Times());
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("JCXF")) {
                                mEtTimesConsume.setText("" + bean.getData().getPrintTimesList().get(j).getPT_Times());
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("JB")) {
                                mEtHandDutyTime.setText("" + bean.getData().getPrintTimesList().get(j).getPT_Times());
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("YJJY")) {
                                etPrintSetOnekey.setText("" + bean.getData().getPrintTimesList().get(j).getPT_Times());
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("SPTH")) {
                                mPrintMap.put("SPTH", String.valueOf(bean.getData().getPrintTimesList().get(j).getPT_Times()));
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("FTXF")) {
                                mPrintMap.put("FTXF", String.valueOf(bean.getData().getPrintTimesList().get(j).getPT_Times()));
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("HYDJ")) {
                                mPrintMap.put("HYDJ", String.valueOf(bean.getData().getPrintTimesList().get(j).getPT_Times()));
                            } else if (bean.getData().getPrintTimesList().get(j).getPT_Code().equals("JSXF")) {
                                mPrintMap.put("JSXF", String.valueOf(bean.getData().getPrintTimesList().get(j).getPT_Times()));
                            }
                        }
                    }
                }
            }

            @Override
            public void getPrintSetFail(String result) {
                if (!result.equals("执行失败")) {
                    CustomToast.makeText(PrintSetActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void saveSetSuccess() {
                getAllMessage();
                mAlertDialog = new SweetAlertDialog(PrintSetActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mAlertDialog.setTitleText("设置");
                mAlertDialog.setConfirmText("确认");
                mAlertDialog.setContentText("打印设置成功！");
                mAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        i = 0;
                        mAlertDialog.dismiss();
                    }
                });
                mAlertDialog.show();
            }

            @Override
            public void saveSetFail(String result) {
                CustomToast.makeText(PrintSetActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        };
        mPresenter.attachView(mView);

    }

    private void setListener() {
        //蓝牙设置
        mRlBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrintSetActivity.this, BlueToothActivity.class);
                startActivityForResult(intent, 666);
            }
        });
        //返回
        findViewById(R.id.iv_print_set_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //保存设置
        findViewById(R.id.tv_print_set_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.put("PS_IsEnabled", mPrintSwitch);
                params.put("PS_IsPreview", 0);

                if (mPrintSetBean == null) {
                    params.put("PS_PaperType", paperType);
                    params.put("PS_PrinterName", "XP-58");
                    params.put("PS_StylusPrintingName:", "XP-58");
                } else {
                    params.put("PS_PaperType", paperType);
                    params.put("PS_PrinterName", mPrintSetBean.getData().getPS_PrinterName());
                    params.put("PS_StylusPrintingName:", mPrintSetBean.getData().getPS_StylusPrintingName());
                }

                if (mPrintMap.size() > 0) {
                    Iterator iterator = mPrintMap.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        params.put("PrintTimesList[" + i + "][PT_Code]", key);
                        params.put("PrintTimesList[" + i + "][PT_Times]", value);
                        i++;
                    }
                    mPresenter.savePrintSet(PrintSetActivity.this, params);
                }

            }
        });
        mRgPrintSwitch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mRbOpen.getId()) {
                    mPrintSwitch = 1;
                    mEtOpenCardTime.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mEtRechargeMoney.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mEtRechargeTimes.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mEtTimesConsume.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mEtGoodsConsume.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mEtFastConsume.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mEtIntegralExchange.setInputType(InputType.TYPE_CLASS_NUMBER);
                    mEtHandDutyTime.setInputType(InputType.TYPE_CLASS_NUMBER);
                    etPrintSetOnekey.setInputType(InputType.TYPE_CLASS_NUMBER);

                    mEtOpenCardTime.setText("1");
                    mEtRechargeMoney.setText("1");
                    mEtFastConsume.setText("1");
                    mEtIntegralExchange.setText("1");
                    mEtGoodsConsume.setText("1");
                    mEtRechargeTimes.setText("1");
                    mEtTimesConsume.setText("1");
                    mEtHandDutyTime.setText("1");
                    etPrintSetOnekey.setText("1");

                    mScrollView.setVisibility(View.VISIBLE);
                }
                if (checkedId == mRbClose.getId()) {
                    mPrintSwitch = 0;
                    mEtOpenCardTime.setInputType(InputType.TYPE_NULL);
                    mEtRechargeMoney.setInputType(InputType.TYPE_NULL);
                    mEtRechargeTimes.setInputType(InputType.TYPE_NULL);
                    mEtTimesConsume.setInputType(InputType.TYPE_NULL);
                    mEtGoodsConsume.setInputType(InputType.TYPE_NULL);
                    mEtFastConsume.setInputType(InputType.TYPE_NULL);
                    mEtIntegralExchange.setInputType(InputType.TYPE_NULL);
                    mEtHandDutyTime.setInputType(InputType.TYPE_NULL);
                    etPrintSetOnekey.setInputType(InputType.TYPE_NULL);

                    mScrollView.setVisibility(View.GONE);
                }
            }
        });
        //输入框监听
        mEtOpenCardTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    mPrintMap.put("HYKK", s.toString());
                }
            }
        });


        //输入框监听
        mEtRechargeMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    mPrintMap.put("HYCZ", s.toString());
                }
            }
        });
        //输入框监听
        mEtRechargeTimes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    mPrintMap.put("HYCC", s.toString());
                }
            }
        });
        //输入框监听
        mEtGoodsConsume.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    mPrintMap.put("SPXF", s.toString());
                }
            }
        });
        //输入框监听
        mEtFastConsume.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    mPrintMap.put("KSXF", s.toString());
                }
            }
        });
        //输入框监听
        mEtTimesConsume.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    mPrintMap.put("JCXF", s.toString());
                }
            }
        });
        //输入框监听
        mEtIntegralExchange.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    mPrintMap.put("JFDH", s.toString());
                }
            }
        });
        //输入框监听
        mEtHandDutyTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    mPrintMap.put("JB", s.toString());
                }
            }
        });
        //输入框监听
        etPrintSetOnekey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty()) {
                    mPrintMap.put("YJJY", s.toString());
                }
            }
        });

        printPaper.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                paperType = position + 2;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        i = 0;
        mPrintMap.clear();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 666 && resultCode == 666) {
            if (data != null && !"".equals(data.getStringExtra("bluetooth")))
                mTvPrint.setText(data.getStringExtra("bluetooth"));
        }
    }
}
