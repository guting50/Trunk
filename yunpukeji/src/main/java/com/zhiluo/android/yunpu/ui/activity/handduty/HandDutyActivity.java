package com.zhiluo.android.yunpu.ui.activity.handduty;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.activity.LoginActivity;
import com.zhiluo.android.yunpu.mvp.presenter.IPrintSetPresenter;
import com.zhiluo.android.yunpu.mvp.view.IPrintSetView;
import com.zhiluo.android.yunpu.print.bean.PrintSetBean;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;
import com.zhiluo.android.yunpu.utils.PrinterUtils;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by YSL on 2018-11-26.
 */

public class HandDutyActivity extends AppCompatActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.rl_pay_confirm_title)
    RelativeLayout rlPayConfirmTitle;
    @Bind(R.id.tv_constmoney)
    TextView tvConstmoney;
    @Bind(R.id.cb_print)
    CheckBox cbPrint;
    @Bind(R.id.btn_pay_confirm_submit)
    TextView btnPayConfirmSubmit;
    @Bind(R.id.l_shoukuan)
    LinearLayout lShoukuan;
    @Bind(R.id.tv_pay_confirm_order)
    EditText tvPayConfirmOrder;
    @Bind(R.id.r_layout_order)
    RelativeLayout rLayoutOrder;
    @Bind(R.id.tv_pay_confirm_receivable)
    TextView tvPayConfirmReceivable;
    @Bind(R.id.l_oder_moenty)
    RelativeLayout lOderMoenty;
    @Bind(R.id.et_pay_confirm_discount_money)
    EditText etPayConfirmDiscountMoney;
    @Bind(R.id.et_pay_confirm_discount)
    EditText etPayConfirmDiscount;
    @Bind(R.id.em_goods)
    TextView emGoods;
    @Bind(R.id.tv_pay_confirm_employee)
    TextView tvPayConfirmEmployee;
    @Bind(R.id.rl_pay_confirm_employee)
    RelativeLayout rlPayConfirmEmployee;
    @Bind(R.id.rl_pay_way)
    RelativeLayout rlPayWay;
    @Bind(R.id.listview)
    ExpandableListView listview;


    private HandDutyBean handDutyBean;
    private List<HandDutyBean.DataBean.DataListBean> handDutyListBean;
    private String shopId,gid;
    private HandDutyAdapter adapter;
    public int[] isExpand = new int[] {0,0,0,0,0,0,0,0,0,0,0,0};
    private double tatol;
    private Dialog chooseDialog;
    private List<String> storelist;
    private String userAcount,userName;
    private QueryUser queryUser;
    private SweetAlertDialog mSweetAlertDialog;//提示框

    private IPrintSetPresenter printPresent;
    private IPrintSetView printView;
    private int pribean = 0 ;
    private double cashTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_over_duty);
        ButterKnife.bind(this);
        loadData();
        setCbPrint();
        setListenter();
    }

    private void setListenter() {
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnPayConfirmSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (etPayConfirmDiscountMoney.getText().toString()==null ||etPayConfirmDiscountMoney.getText().toString().equals("")){
                        CustomToast.makeText(HandDutyActivity.this,"上交营业额不能为空",Toast.LENGTH_SHORT).show();
                    }else if (etPayConfirmDiscount.getText().toString()==null ||etPayConfirmDiscount.getText().toString().equals("")){
                        CustomToast.makeText(HandDutyActivity.this,"下发营业额不能为空",Toast.LENGTH_SHORT).show();
                    }else {
                        handOverDuty();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        tvPayConfirmEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storelist.size()<1){
                    getUser();
                }else {
                    showPayWayDialog(storelist,tvPayConfirmEmployee);
                }

            }
        });

        // 通过监听展开和关闭事件动态设置高度

        listview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override

            public void onGroupExpand(int groupPosition) {

                isExpand[groupPosition] = 1;

                setListHeight(listview, adapter);

            }

        });

        listview.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override

            public void onGroupCollapse(int groupPosition) {

                isExpand[groupPosition] = 0;

                setListHeight(listview, adapter);

            }

        });

        etPayConfirmDiscountMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().equals("") || s.toString().equals(".")) {
                    etPayConfirmDiscountMoney.setText("0");
                    etPayConfirmDiscountMoney.setSelection(1);
                }else if (s.toString().length()>1&&s.toString().substring(0,1).equals("0")&&!s.toString().substring(1,2).equals(".")){
                    etPayConfirmDiscountMoney.setText(s.toString().substring(1,s.toString().length()));
                    etPayConfirmDiscountMoney.setSelection(etPayConfirmDiscountMoney.getText().toString().length());
                }else {
//                    if (s.toString().substring(s.toString().indexOf(".")+1).length()>2){
//                        CustomToast.makeText(HandDutyActivity.this,"请输入两位小数",Toast.LENGTH_SHORT).show();
//                        return;
//                    }
                    double money = tatol-Double.parseDouble(s.toString());
                    etPayConfirmDiscount.setText(Decima2KeeplUtil.stringToDecimal(money+""));
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
                    CustomToast.makeText(HandDutyActivity.this, "打印开关未开启，请设置", Toast.LENGTH_SHORT).show();
                    cbPrint.setChecked(false);
                }
            }
        });
        //金额过滤器
        MoneyInputFilter filter = new MoneyInputFilter(20, true, "最多只能输入20位金额");
        filter.setMessage("最多只能输入20位金额");
        etPayConfirmDiscountMoney.setFilters(new InputFilter[]{filter});

        etPayConfirmDiscountMoney.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    etPayConfirmDiscountMoney.setCursorVisible(true);// 再次点击显示光标
                }
                return false;
            }
        });
        etPayConfirmDiscount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    etPayConfirmDiscount.setCursorVisible(true);// 再次点击显示光标
                }
                return false;
            }
        });



        etPayConfirmDiscountMoney.setCursorVisible(false);
//        etPayConfirmDiscountMoney.setFocusableInTouchMode(true);
        etPayConfirmDiscount.setCursorVisible(false);
//        etPayConfirmDiscount.setFocusableInTouchMode(true);

    }

    /**
     * 根据打印开关是否打开，设置checkbox
     */
    private void setCbPrint() {
        printPresent = new IPrintSetPresenter(this);
        printView = new IPrintSetView() {
            @Override
            public void getPrintSetSuccess(PrintSetBean bean) {

                if (bean.getData().getPS_IsEnabled() != 1) {
                    cbPrint.setChecked(false);
                    pribean = 0;
                } else {
                    cbPrint.setChecked(true);
                    pribean = 1;
                }
            }

            @Override
            public void getPrintSetFail(String result) {

            }

            @Override
            public void saveSetSuccess() {

            }

            @Override
            public void saveSetFail(String result) {

            }
        };
        printPresent.attachView(printView);

        try {
            if (YSLUtils.getPrints()!=null){

                if (YSLUtils.getPrints().getPS_IsEnabled() != 1) {
                    cbPrint.setChecked(false);
                    pribean = 0;
                } else {
                    cbPrint.setChecked(true);
                    pribean = 1;
                }
            }else {
                printPresent.getPrintSet();
            }

        } catch (Exception e) {
            cbPrint.setVisibility(View.INVISIBLE);
        }
    }

    private void loadData() {
        etPayConfirmDiscountMoney.requestFocus();
        storelist = new ArrayList();
        shopId = (String) uSharedPreferencesUtiles.get(HandDutyActivity.this, "StoreGid", "");
        gid = (String) uSharedPreferencesUtiles.get(HandDutyActivity.this, "Account_GID", "");

        getShiftAccount();
        getUser();
    }


    private void handOverDuty() throws JSONException {

        RequestParams params = new RequestParams();
        params.put("HandInBusiness",etPayConfirmDiscountMoney.getText().toString() );
        params.put("IssuedBusiness", etPayConfirmDiscount.getText().toString());
        params.put("EmplName", userName);
//        params.put("EmplCode", userAcount);
        params.put("UserAcount", userAcount);

//        JSONObject maleArray = handDutyBean.getData();
//        Iterator<String> male_Iterator = handDutyBean.getData().keys();
//
//        while(male_Iterator.hasNext()){
//            // 获得key
//            String key = male_Iterator.next();
//            //根据key获得value, value也可以是JSONObject,JSONArray,使用对应的参数接收即可
//            String value = maleArray.getString(key);
//            params.put("Sales["+key+"}", value);
//        }

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {

                final HandDutySuccess handDutySuccess = CommonFun.JsonToObj(responseString,HandDutySuccess.class);

//                handDutyBean.getData().setHandin(Double.parseDouble(etPayConfirmDiscountMoney.getText().toString()));
//                handDutyBean.getData().setHanout(Double.parseDouble(etPayConfirmDiscount.getText().toString()));
                mSweetAlertDialog = new SweetAlertDialog(HandDutyActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("提示");
                mSweetAlertDialog.setContentText("交班成功");
                mSweetAlertDialog.setCancelable(false);
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        try {
                            if (cbPrint.isChecked()) {
                                new HttpGetPrintContents(HandDutyActivity.this, MyApplication.JB_PRINT_TIMES, handDutySuccess.getData().getGID()).JB();
//                                PrinterUtils printerUtils = new PrinterUtils(HandDutyActivity.this, MyApplication.JB_PRINT_TIMES, handDutyBean, "JB");
//                                printerUtils.print();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(HandDutyActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                mSweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(HandDutyActivity.this,msg,Toast.LENGTH_SHORT).show();

            }
        };
        HttpHelper.post(HandDutyActivity.this,HttpAPI.API().FAST_HANDOVER,params,callBack);
    }

    private void getUser() {
        RequestParams params = new RequestParams();
        params.put("RM_GID", "");
        params.put("SM_GID", shopId);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                queryUser = CommonFun.JsonToObj(responseString, QueryUser.class);
                if (queryUser != null) {
                    for (int i = 0; i < queryUser.getData().size(); i++) {
                        if (queryUser.getData().get(i).getGID().equals(gid)) {
                            storelist.add("当前用户");
                            userName = queryUser.getData().get(i).getUM_Name();
                            userAcount = queryUser.getData().get(i).getUM_Acount();
                        } else {
                            if (!queryUser.getData().get(i).getRM_Name().equals("超级管理员")) {
                                storelist.add(queryUser.getData().get(i).getUM_Name());
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(HandDutyActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setmAPI("UserManager/QueryUsersList");
        HttpHelper.post(this, HttpAPI.API().USER_MANAGER_LIST, params, callBack);
    }


  /**
   * 获取交班数据详情
   * **/
    private void getShiftAccount() {
        RequestParams params = new RequestParams();
        params.put("Flag",0);
        params.put("SM_GID",shopId);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                handDutyBean = CommonFun.JsonToObj(responseString, HandDutyBean.class);
                if (handDutyBean != null) {
                    editData();
                    setAdapter();
                    tvPayConfirmOrder.setText(Decima2KeeplUtil.stringToDecimal(handDutyBean.getData().getStatisticsInfo().getSA_IssuedBusiness()+""));
                    tatol = handDutyBean.getData().getStatisticsInfo().getSA_IssuedBusiness()+cashTotal;
                    tvPayConfirmReceivable.setText(Decima2KeeplUtil.stringToDecimal(tatol+"")+"");
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(HandDutyActivity.this,msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setmAPI("Shift/GetShiftAmountCopy");
        HttpHelper.post(HandDutyActivity.this, HttpAPI.API().SHIFT_AMOUNT_COPY, params, callBack);
    }

    /**
     * @param mlist     ,
     * @param mTextView ,
     *                交班人员选择
     */
    private void showPayWayDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        userAcount = queryUser.getData().get(position).getUM_Acount();
                        userName = queryUser.getData().get(position).getUM_Name();
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    private void setAdapter() {
        isExpand = new int[] { 0, 0, 0 ,0,0,0,0,0,0,0,0,0};
        adapter = new HandDutyAdapter(handDutyBean, this);
        listview.setGroupIndicator(null);
        listview.setAdapter(adapter);
        setListHeight(listview, adapter);
    }

    /*

    * ExpandListView自适应高度 根据子项数量

    * @param listView

    * @param listAdapter listView 的适配器

    * */

    public void setListHeight(ExpandableListView listView, HandDutyAdapter listAdapter) {

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int total = 0;
        View listItem;

        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            listItem = listAdapter.getGroupView(i, false, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            total += (listAdapter.getChildrenCount(0) - 1);
        }

        for (int i = 0; i < isExpand.length; i++) {
            if (isExpand[i] == 1)
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    listItem = listAdapter.getChildView(i, j, false, null, listView);
                    listItem.measure(0, 0);
                    totalHeight += listItem.getMeasuredHeight();
                    total += (listAdapter.getChildrenCount(i) - 1);
                }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * total);
        listView.setLayoutParams(params);

    }

    private void editData() {

        HandDutyBean.DataBean.DataListBean Bean = new HandDutyBean.DataBean.DataListBean();

        Bean.setName("合计");
        Bean.setMAI_Identifying("合计");
        double cash = 0.00;
        double yue = 0.00;
        double yinlian = 0.00;
        double jifen = 0.00;
        double wx = 0.00;
        double zfb = 0.00;
        double yhq = 0.00;
        double sm = 0.00;
//        double tuihuo = 0.00;
        double other = 0.00;
        double moling = 0.00;

        for (int i = 0;i<handDutyBean.getData().getDataList().size();i++){
            if (handDutyBean.getData().getDataList().get(i).getMAI_Identifying().contains("撤单")){
                cash -= handDutyBean.getData().getDataList().get(i).getMAI_CashChange();
                yue -= handDutyBean.getData().getDataList().get(i).getMAI_BalanceChange();
                yinlian -= handDutyBean.getData().getDataList().get(i).getMAI_UnionChange();
                wx -= handDutyBean.getData().getDataList().get(i).getMAI_WeChatPayChange();
                zfb -= handDutyBean.getData().getDataList().get(i).getMAI_AlipayPayChange();
                jifen -= handDutyBean.getData().getDataList().get(i).getMAI_IntegralChange();
                yhq -= handDutyBean.getData().getDataList().get(i).getMAI_CouponPayChange();
                sm -= handDutyBean.getData().getDataList().get(i).getMAI_BarCodePayChange();
                other -= handDutyBean.getData().getDataList().get(i).getMAI_OtherChange();
                moling -= handDutyBean.getData().getDataList().get(i).getMAI_EraseZeroChange();
            }else {
                cash += handDutyBean.getData().getDataList().get(i).getMAI_CashChange();
                yue += handDutyBean.getData().getDataList().get(i).getMAI_BalanceChange();
                yinlian += handDutyBean.getData().getDataList().get(i).getMAI_UnionChange();
                wx += handDutyBean.getData().getDataList().get(i).getMAI_WeChatPayChange();
                zfb += handDutyBean.getData().getDataList().get(i).getMAI_AlipayPayChange();
                jifen += handDutyBean.getData().getDataList().get(i).getMAI_IntegralChange();
                yhq += handDutyBean.getData().getDataList().get(i).getMAI_CouponPayChange();
                sm += handDutyBean.getData().getDataList().get(i).getMAI_BarCodePayChange();
                other += handDutyBean.getData().getDataList().get(i).getMAI_OtherChange();
                moling += handDutyBean.getData().getDataList().get(i).getMAI_EraseZeroChange();
//            tuihuo += handDutyBean.getData().getDataList().get(i).getMAI_ReturnGoods();
            }


        }
        cashTotal = cash;
        Bean.setMAI_CashChange(cash);
        Bean.setMAI_BalanceChange(yue);
        Bean.setMAI_UnionChange(yinlian);
        Bean.setMAI_IntegralChange(jifen);
        Bean.setMAI_WeChatPayChange(wx);
        Bean.setMAI_AlipayPayChange(zfb);
        Bean.setMAI_CouponPayChange(yhq);
        Bean.setMAI_BarCodePayChange(sm);
        Bean.setMAI_ReturnGoods(0);
        Bean.setMAI_OtherChange(other);
        Bean.setMAI_EraseZeroChange(moling);

        handDutyListBean = new ArrayList<>();
        handDutyListBean.addAll(handDutyBean.getData().getDataList());
        handDutyListBean.add(Bean);
        handDutyBean.getData().setDataList(handDutyListBean);

        for (int j = 0 ;j<handDutyBean.getData().getDataList().size();j++){

            HandDutyBean.DataBean.DataListBean mBean = handDutyBean.getData().getDataList().get(j);
            double mtotal = 0;
            mtotal = mBean.getMAI_CashChange() +mBean.getMAI_BalanceChange()+ mBean.getMAI_UnionChange() + mBean.getMAI_WeChatPayChange()
                    +mBean.getMAI_AlipayPayChange() +mBean.getMAI_IntegralChange() + mBean.getMAI_CouponPayChange() +mBean.getMAI_BarCodePayChange()
                    + mBean.getMAI_EraseZeroChange()+mBean.getMAI_OtherChange() -mBean.getMAI_ReturnGoods();
            mBean.setTotal(mtotal);
            mBean.setName(nameConversion(mBean.getMAI_Identifying()));

        }


//        handDutyListBean = new ArrayList<>();
//        HandDutyListBean bean1 = new HandDutyListBean();
//        bean1.setName("会员开卡");
//        bean1.setCash(handDutyBean.getData().getSA_OpenCardCash());
//        bean1.setYue(0.00);
//        bean1.setUnion(handDutyBean.getData().getSA_OpenCardUnion());
//        bean1.setWx(handDutyBean.getData().getSA_OpenCardWeChatpay());
//        bean1.setZfb(handDutyBean.getData().getSA_OpenCardAlipaypay());
//        bean1.setJf(0.00);
//        bean1.setYhj(0.00);
//        bean1.setSm(0.00);
//        bean1.setQt(handDutyBean.getData().getSA_OpenCardOtherpay());
//        bean1.setTotal(handDutyBean.getData().getSA_OpenCardTotal());
//        handDutyListBean.add(bean1);
//
//        HandDutyListBean bean2 = new HandDutyListBean();
//        bean2.setName("会员充值");
//        bean2.setCash(handDutyBean.getData().getSA_RechargeCash());
//        bean2.setYue(0.00);
//        bean2.setUnion(handDutyBean.getData().getSA_RechargeUnion());
//        bean2.setWx(handDutyBean.getData().getSA_RechargeWeChatpay());
//        bean2.setZfb(handDutyBean.getData().getSA_RechargeAlipaypay());
//        bean2.setJf(0.00);
//        bean2.setYhj(0.00);
//        bean2.setSm(handDutyBean.getData().getSA_RechargeBarCode());
//        bean2.setQt(handDutyBean.getData().getSA_RechargeOtherpay());
//        bean2.setTotal(handDutyBean.getData().getSA_RechargeTotal());
//        handDutyListBean.add(bean2);
//
//        HandDutyListBean bean3 = new HandDutyListBean();
//        bean3.setName("会员充次");
//        bean3.setCash(handDutyBean.getData().getSA_ChargeCash());
//        bean3.setYue(handDutyBean.getData().getSA_ChargeBalance());
//        bean3.setUnion(handDutyBean.getData().getSA_ChargeUnion());
//        bean3.setWx(handDutyBean.getData().getSA_ChargeWeChatpay());
//        bean3.setZfb(handDutyBean.getData().getSA_ChargeAlipaypay());
//        bean3.setJf(handDutyBean.getData().getSA_ChargeIntegral());
//        bean3.setYhj(handDutyBean.getData().getSA_ChargeCoupon());
//        bean3.setSm(handDutyBean.getData().getSA_ChargeBarCode());
//        bean3.setQt(handDutyBean.getData().getSA_ChargeOtherpay());
//        bean3.setTotal(handDutyBean.getData().getSA_ChargeTotal());
//        handDutyListBean.add(bean3);
//
//        HandDutyListBean bean4 = new HandDutyListBean();
//        bean4.setName("会员延期");
//        bean4.setCash(handDutyBean.getData().getSA_DelayCash());
//        bean4.setYue(handDutyBean.getData().getSA_DelayBalance());
//        bean4.setUnion(handDutyBean.getData().getSA_DelayUnion());
//        bean4.setWx(handDutyBean.getData().getSA_DelayWeChatpay());
//        bean4.setZfb(handDutyBean.getData().getSA_DelayWeAlipaypay());
//        bean4.setJf(handDutyBean.getData().getSA_DelayIntegral());
//        bean4.setYhj(handDutyBean.getData().getSA_DelayCoupon());
//        bean4.setSm(handDutyBean.getData().getSA_DelayBarCode());
//        bean4.setQt(handDutyBean.getData().getSA_DelayOtherpay());
//        bean4.setTotal(handDutyBean.getData().getSA_DelayTotal());
//        handDutyListBean.add(bean4);
//
//        HandDutyListBean bean5 = new HandDutyListBean();
//        bean5.setName("商品消费");
//        bean5.setCash(handDutyBean.getData().getSA_ConsumptionCash());
//        bean5.setYue(handDutyBean.getData().getSA_ConsumptionBalance());
//        bean5.setUnion(handDutyBean.getData().getSA_ConsumptionUnionpay());
//        bean5.setWx(handDutyBean.getData().getSA_ConsumptionWeChatpay());
//        bean5.setZfb(handDutyBean.getData().getSA_ConsumptionAlipaypay());
//        bean5.setJf(handDutyBean.getData().getSA_ConsumptionIntegral());
//        bean5.setYhj(handDutyBean.getData().getSA_ConsumptionCoupon());
//        bean5.setSm(handDutyBean.getData().getSA_ConsumptionBarCode());
//        bean5.setQt(handDutyBean.getData().getSA_ConsumptionOtherpay());
//        bean5.setTotal(handDutyBean.getData().getSA_ConsumptionTotal());
//        handDutyListBean.add(bean5);
//
//        HandDutyListBean bean6 = new HandDutyListBean();
//        bean6.setName("快速消费");
//        bean6.setCash(handDutyBean.getData().getSA_FastConsumptionCash());
//        bean6.setYue(handDutyBean.getData().getSA_FastConsumptionBalance());
//        bean6.setUnion(handDutyBean.getData().getSA_FastConsumptionUnion());
//        bean6.setWx(handDutyBean.getData().getSA_FastConsumptionWeChatpay());
//        bean6.setZfb(handDutyBean.getData().getSA_FastConsumptionAlipaypay());
//        bean6.setJf(handDutyBean.getData().getSA_FastConsumptionIntegral());
//        bean6.setYhj(handDutyBean.getData().getSA_FastConsumptionCoupon());
//        bean6.setSm(handDutyBean.getData().getSA_FastConsumptionBarCode());
//        bean6.setQt(handDutyBean.getData().getSA_FastConsumptionOtherpay());
//        bean6.setTotal(handDutyBean.getData().getSA_FastConsumptionTotal());
//        handDutyListBean.add(bean6);
//
//        HandDutyListBean bean7 = new HandDutyListBean();
//        bean7.setName("套餐消费");
//        bean7.setCash(handDutyBean.getData().getSA_ComboCash());
//        bean7.setYue(handDutyBean.getData().getSA_ComboBalance());
//        bean7.setUnion(handDutyBean.getData().getSA_ComboUnion());
//        bean7.setWx(handDutyBean.getData().getSA_ComboWeChatpay());
//        bean7.setZfb(handDutyBean.getData().getSA_ComboAlipaypay());
//        bean7.setJf(handDutyBean.getData().getSA_ComboIntegral());
//        bean7.setYhj(handDutyBean.getData().getSA_ComboCoupon());
//        bean7.setSm(handDutyBean.getData().getSA_ComboBarCode());
//        bean7.setQt(handDutyBean.getData().getSA_ComboOtherpay());
//        bean7.setTotal(handDutyBean.getData().getSA_ComboTotal());
//        handDutyListBean.add(bean7);
//
//        HandDutyListBean bean8 = new HandDutyListBean();
//        bean8.setName("计时消费");
//        bean8.setCash(handDutyBean.getData().getSA_TimeCash());
//        bean8.setYue(handDutyBean.getData().getSA_TimeBalance());
//        bean8.setUnion(handDutyBean.getData().getSA_TimeUnion());
//        bean8.setWx(handDutyBean.getData().getSA_TimeWeChatpay());
//        bean8.setZfb(handDutyBean.getData().getSA_TimeAlipaypay());
//        bean8.setJf(handDutyBean.getData().getSA_TimeIntegral());
//        bean8.setYhj(handDutyBean.getData().getSA_TimeCoupon());
//        bean8.setSm(handDutyBean.getData().getSA_TimeBarCode());
//        bean8.setQt(handDutyBean.getData().getSA_TimeOtherpay());
//        bean8.setTotal(handDutyBean.getData().getSA_TimeTotal());
//        handDutyListBean.add(bean8);
//
//        HandDutyListBean bean9 = new HandDutyListBean();
//        bean9.setName("房台消费");
//        bean9.setCash(handDutyBean.getData().getSA_HouseCash());
//        bean9.setYue(handDutyBean.getData().getSA_HouseBalance());
//        bean9.setUnion(handDutyBean.getData().getSA_HouseUnion());
//        bean9.setWx(handDutyBean.getData().getSA_HouseWeChatpay());
//        bean9.setZfb(handDutyBean.getData().getSA_HouseAlipaypay());
//        bean9.setJf(handDutyBean.getData().getSA_HouseIntegral());
//        bean9.setYhj(handDutyBean.getData().getSA_HouseCoupon());
//        bean9.setSm(handDutyBean.getData().getSA_HouseBarCode());
//        bean9.setQt(handDutyBean.getData().getSA_HouseOtherpay());
//        bean9.setTotal(handDutyBean.getData().getSA_HouseTotal());
//        handDutyListBean.add(bean9);
//
//        HandDutyListBean bean10 = new HandDutyListBean();
//        bean10.setName("商品退货");
//        bean10.setCash(handDutyBean.getData().getSA_ReturnGoodsCash());
//        bean10.setYue(handDutyBean.getData().getSA_ReturnGoodsBalance());
//        bean10.setUnion(0.00);
//        bean10.setWx(0.00);
//        bean10.setZfb(0.00);
//        bean10.setJf(0.00);
//        bean10.setYhj(0.00);
//        bean10.setSm(0.00);
//        bean10.setQt(0.00);
//        bean10.setTotal(handDutyBean.getData().getSA_ReturnGoodsTotal());
//        handDutyListBean.add(bean10);
//
//        HandDutyListBean bean11 = new HandDutyListBean();
//        bean11.setName("总收入");
//        bean11.setCash(handDutyBean.getData().getSA_CashTotal());
//        bean11.setYue(handDutyBean.getData().getSA_BalanceTotal());
//        bean11.setUnion(handDutyBean.getData().getSA_UnionTotal());
//        bean11.setWx(handDutyBean.getData().getSA_WeChatpayTotal());
//        bean11.setZfb(handDutyBean.getData().getSA_AlipaypayTotal());
//        bean11.setJf(handDutyBean.getData().getSA_IntegralTotal());
//        bean11.setYhj(handDutyBean.getData().getSA_CouponTotal());
//        bean11.setSm(handDutyBean.getData().getSA_BarCodeTotal());
//        bean11.setQt(handDutyBean.getData().getSA_OtherpayTotal());
//        bean11.setTotal(handDutyBean.getData().getSA_AllTotal());
//        handDutyListBean.add(bean11);
    }

    private String nameConversion (String name){
        if (name!=null){
            if (name.equals("开卡费用")){
                return "会员开卡";
            }else if (name.equals("充值")){
                return "会员充值";
            }else if (name.equals("充次")){
                return "会员充次";
            }else if (name.equals("延期费用")){
                return "会员延期";
            }else if (name.equals("微信订单")){
                return "微店消费";
            }else if (name.equals("撤单")){
                return "退货+撤单";
            }
        }
        return name;
    }

}
