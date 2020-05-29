package com.zhiluo.android.yunpu.consume.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.bean.FastPayStepOneBean;
import com.zhiluo.android.yunpu.goods.manager.bean.BarCodePayBean;
import com.zhiluo.android.yunpu.goods.manager.bean.QuerPayBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.mvp.presenter.PostVipPresenter;
import com.zhiluo.android.yunpu.mvp.presenter.SaoMaPayPresntter;
import com.zhiluo.android.yunpu.mvp.view.IPostVipView;
import com.zhiluo.android.yunpu.mvp.view.SaoMaPayView;
import com.zhiluo.android.yunpu.print.bean.KSXF_Success_Bean;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.LoadingDialogUtil;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-05-05.
 */

public class KuaiJieShouKuan extends AppCompatActivity implements View.OnClickListener {
    private TextView tvBack, tvVipName, tvCard, tvYuE, tvIntergal, tvMoney, tvCostMoney, tvTitle;
    private ImageView imgDeletVip, imgMa, imgDownChoise;
    private LinearLayout lVipMessage;
    private TextView rChoiseVip;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonok, button00, btnadd;
    private LinearLayout lDelet, buttonadd;
    private AllMemberListBean.DataBean.DataListBean mMember;
    private MemberInfoBean.DataBean mMemberInfo;
    private String mCardNum;
    private String mMemberName;
    private String YuE, JIfen;
    private MyHandler myHandler;
    private StringBuilder mEditContentBuilder;//文本框展示键盘输入内容
    private double mTempVar = 0;//中间变量
    private double mYsMoney, mOrderMoney;//应收金额，订单金额
    private double mDiscountProportion;//折扣比例
    private String content = "";//输入内容
    private String mOrderGID;
    private SweetAlertDialog mSweetAlertDialog;
    private String mPayName;
    private String mPayCode;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;//参数开关，这里要用到微信和支付宝记账的开关
    private boolean mSmSwitch;

    private String mSmPayCode = "";//扫码返回条码
    private SaoMaPayPresntter presenter;
    private SaoMaPayView mView;
    private String barcodeResultGID;
    private Dialog mDialog;
    private PostVipPresenter vippresenter;
    private IPostVipView vipView;
    private boolean isSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuaijieshoukuan);
        initViews();
        initAction();
        initData();
        initLisenter();

    }

    private void initData() {
        myHandler = new MyHandler();
        mEditContentBuilder = new StringBuilder();
        mCardNum = "00000";
        mMemberName = "散客";

        vippresenter = new PostVipPresenter(this);
        vipView = new IPostVipView() {
            @Override
            public void getvipsuccesss(MemberInfoBean bean) {
                isSearch = true;
                mMemberInfo = bean.getData();
                mCardNum = mMemberInfo.getVCH_Card();
                mMemberName = mMemberInfo.getVIP_Name();
                if (mCardNum != null && !"".equals(mCardNum)) {
                    myHandler.sendEmptyMessage(2);
                }
            }

            @Override
            public void getvipfail(String result) {
                tvVipName.setVisibility(View.INVISIBLE);
                mMember = null;
                mCardNum = "00000";
                mMemberName = "散客";
                myHandler.sendEmptyMessage(2);

            }
        };
        vippresenter.attachView(vipView);
    }

    private void initLisenter() {

        tvVipName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YSLUtils.isFastClick()) {
                    Intent intent = new Intent(KuaiJieShouKuan.this, CheckMemberInfoActivity.class);
                    startActivityForResult(intent, 888);
                }
            }
        });
        imgDownChoise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YSLUtils.isFastClick()) {
                    Intent intent = new Intent(KuaiJieShouKuan.this, CheckMemberInfoActivity.class);
                    startActivityForResult(intent, 888);
                }
            }
        });
    }


    private void initAction() {
        tvTitle.setText("扫码收款");
        tvBack.setOnClickListener(this);
        imgDeletVip.setOnClickListener(this);
        imgMa.setOnClickListener(this);
        rChoiseVip.setOnClickListener(this);
        button00.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonadd.setOnClickListener(this);
        buttonok.setOnClickListener(this);
        btnadd.setOnClickListener(this);
        lDelet.setOnClickListener(this);

        presenter = new SaoMaPayPresntter(this);
        mView = new SaoMaPayView() {
            @Override
            public void barCodeSuccess(BarCodePayBean entity) {
                barcodeResultGID = entity.getData().getGID();
                if (mDialog != null) {
                    LoadingDialogUtil.closeDialog(mDialog);
                }
                fastPayStepTwo();
//                querPay();
            }

            @Override
            public void barCodeFail(BarCodePayBean entity) {
                if (entity.getCode().equals("410004")) {
                    barcodeResultGID = entity.getData().getGID();
                    try {
                        Thread.currentThread().sleep(2500);
                    } catch (Exception e) {

                    }
                    querPay();
                } else {
                    CustomToast.makeText(KuaiJieShouKuan.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void barCodeFailString(String responseString) {
                if (mDialog != null) {
                    LoadingDialogUtil.closeDialog(mDialog);
                }
                CustomToast.makeText(KuaiJieShouKuan.this, responseString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void querPaySuccess(QuerPayBean entity) {
                if (entity.isSuccess()) {
                    if (mDialog != null) {
                        LoadingDialogUtil.closeDialog(mDialog);
                    }
//                    fastPayStepTwo();
                    payComplete(mOrderGID);
                } else {
                    querPay();
                }
            }

            @Override
            public void querPayFail(QuerPayBean entity) {
                if (entity.getCode().equals("410004")) {
                    try {
                        Thread.currentThread().sleep(2500);
                    } catch (Exception e) {

                    }
                    querPay();
                } else if (entity.getCode().contains("41000")) {
                    if (mDialog != null) {
                        LoadingDialogUtil.closeDialog(mDialog);
                    }
                    CustomToast.makeText(KuaiJieShouKuan.this, YSLUtils.payResult(entity.getCode()), Toast.LENGTH_SHORT).show();
                } else {
                    if (mDialog != null) {
                        LoadingDialogUtil.closeDialog(mDialog);
                    }
                    CustomToast.makeText(KuaiJieShouKuan.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        presenter.attachView(mView);
    }

    private void initViews() {
        tvTitle = findViewById(R.id.tv_title);
        tvBack = findViewById(R.id.tv_back_activity);
        rChoiseVip = findViewById(R.id.tv_choose_member);
        tvVipName = findViewById(R.id.tv_choose_member);
        imgDeletVip = findViewById(R.id.iv_delete_vip);
        imgMa = findViewById(R.id.iv_scan_member);
        tvCard = findViewById(R.id.tv_fast_consume_card);
        tvYuE = findViewById(R.id.tv_fast_consume_balance);
        tvIntergal = findViewById(R.id.tv_fast_consume_integral);
        tvMoney = findViewById(R.id.tv_fast_consume_show_money);
        tvCostMoney = findViewById(R.id.tv_fast_consume_ys_money);
        imgDownChoise = findViewById(R.id.iv_choise_vip_right);
        button0 = findViewById(R.id.btn_keyboard_0);
        button1 = findViewById(R.id.btn_keyboard_1);
        button2 = findViewById(R.id.btn_keyboard_2);
        button3 = findViewById(R.id.btn_keyboard_3);
        button4 = findViewById(R.id.btn_keyboard_4);
        button5 = findViewById(R.id.btn_keyboard_5);
        button6 = findViewById(R.id.btn_keyboard_6);
        button7 = findViewById(R.id.btn_keyboard_7);
        button8 = findViewById(R.id.btn_keyboard_8);
        button9 = findViewById(R.id.btn_keyboard_9);
        button00 = findViewById(R.id.btn_keyboard_00);
        buttonadd = findViewById(R.id.l_layout_add_key);
        buttonok = findViewById(R.id.btn_keyboard_confirm);
        btnadd = findViewById(R.id.btn_keyboard_add);
        lDelet = findViewById(R.id.btn_keyboard_delete);
        lVipMessage = findViewById(R.id.rl_fast_consume_member);
        comfirmParams();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back_activity:
                KuaiJieShouKuan.this.finish();
                break;
            case R.id.iv_choise_vip_right:

                break;
            case R.id.r_choise_vip:
                if (YSLUtils.isFastClick()) {
                    Intent intent1 = new Intent(KuaiJieShouKuan.this, CheckMemberInfoActivity.class);
                    startActivityForResult(intent1, 888);
                }
                break;
            case R.id.iv_delete_vip:
                imgDeletVip.setVisibility(View.INVISIBLE);
                tvVipName.setText("选择会员");
                imgDownChoise.setVisibility(View.VISIBLE);
                lVipMessage.setVisibility(View.GONE);
                mMemberInfo = null;
                break;
            case R.id.iv_scan_member:
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(KuaiJieShouKuan.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;
            case R.id.btn_keyboard_00:
                if (!isNull(mEditContentBuilder) && !mEditContentBuilder.toString().contains(".")) {
                    mEditContentBuilder.append(".");
                }
                if (isNull(mEditContentBuilder)) {
                    mEditContentBuilder.append("0.");
                }
                if (!isNull(mEditContentBuilder)) {
                    String[] str = mEditContentBuilder.toString().split("\\+");
                    if (!str[str.length - 1].contains(".")) {
                        mEditContentBuilder.append(".");
                    }
                }
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_0:
                mEditContentBuilder.append("0");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_1:
                mEditContentBuilder.append("1");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_2:
                mEditContentBuilder.append("2");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_3:
                mEditContentBuilder.append("3");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_4:
                mEditContentBuilder.append("4");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_5:
                mEditContentBuilder.append("5");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_6:
                mEditContentBuilder.append("6");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_7:
                mEditContentBuilder.append("7");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_8:
                mEditContentBuilder.append("8");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_9:
                mEditContentBuilder.append("9");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_add:
                if (!isNull(mEditContentBuilder) && !"+".equals(String.valueOf(mEditContentBuilder.toString().charAt(mEditContentBuilder.length() - 1)))) {
                    mEditContentBuilder.append("+");
                }
                break;
            case R.id.l_layout_add_key:
                if (!isNull(mEditContentBuilder) && !"+".equals(String.valueOf(mEditContentBuilder.toString().charAt(mEditContentBuilder.length() - 1)))) {
                    mEditContentBuilder.append("+");
                }
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_confirm:
                if (YSLUtils.isFastClick()) {
                    String moneyFlag = tvCostMoney.getText().toString();
                    if ("".equals(moneyFlag) || "0.0".equals(moneyFlag) || "0.00".equals(moneyFlag) || "0".equals(moneyFlag)) {
                        CustomToast.makeText(this, "请输入消费金额!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (mSmSwitch) {
                            mPayName = "扫码支付";
                            mPayCode = "SMZF";
                            fastPayStepOne();
                        } else {
                            CustomToast.makeText(this, "扫码支付未开启，请到参数设置去开启！", Toast.LENGTH_SHORT).show();
                        }
//                    showPayPop();
                    }
                }
                break;
            case R.id.btn_keyboard_delete:
                if (!isNull(mEditContentBuilder)) {
                    mEditContentBuilder.deleteCharAt(mEditContentBuilder.length() - 1);
                }
                myHandler.sendEmptyMessage(1);
                break;
        }
    }

    private void comfirmParams() {
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {
                    Li("开关-----" + mSwitchEntity.get(i).getSS_Code());
                    //扫码支付开关
                    if ("111".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mSmSwitch = true;
                        }
                    }


                }
            }
        }
    }


    private void barCode() {
        RequestParams params = new RequestParams();
        params.put("Code", mSmPayCode);//条码
        params.put("Money", mOrderMoney);//支付金额
        params.put("OrderGID", mOrderGID);//订单id
        params.put("OrderType", 60);//10商品消费 20充次 30计时消费 40 充值 50 套餐消费 60快速消费
        params.put("OrderNo", CreateOrder.createOrder("KS"));
        params.put("OrderPayInfo[PayPoint]", 0);
        params.put("OrderPayInfo[PayTotalMoney]", mOrderMoney);
        params.put("OrderPayInfo[GiveChange]", 0);
        params.put("OrderPayInfo[DisMoney]", mOrderMoney);
        params.put("OrderPayInfo[IsEraseZero]", false);
        params.put("OrderPayInfo[PayTypeList][0][PayName]", mPayName);
        params.put("OrderPayInfo[PayTypeList][0][PayCode]", mPayCode);
        params.put("OrderPayInfo[PayTypeList][0][PayMoney]", mOrderMoney);


        presenter.barCode(params);
    }

    private void querPay() {
        RequestParams params = new RequestParams();
        params.put("ResultGID", barcodeResultGID);
        presenter.querPay(params);
    }

    /**
     * 快捷消费提交订单信息
     */
    private void fastPayStepOne() {
        Le("提交前------" + (mMemberInfo == null ? "空" : mMemberInfo));
        //comfirmParams();
        RequestParams params = new RequestParams();
        params.put("VIP_Card", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());//会员卡号
        params.put("CO_Monetary", Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));//订单金额

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                FastPayStepOneBean stepOneBean = CommonFun.JsonToObj(responseString, FastPayStepOneBean.class);
                mOrderGID = stepOneBean.getData().getGID();

                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(KuaiJieShouKuan.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 1);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(KuaiJieShouKuan.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交订单...", false);
        HttpHelper.post(this, "ConsumeOrder/SubmitFastReceipt", params, callBack);
    }

    /**
     * 快捷消费确认支付
     */
    private void fastPayStepTwo() {
        RequestParams params = new RequestParams();
        //订单GID
        params.put("OrderGID", mOrderGID);
        //是否发送短信
        params.put("Smsg", true);
        //应收金额
        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));
        //实收金额
        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));
        //找零金额
        params.put("PayResult[GiveChange]", 0);
        //支付方式编码
        params.put("PayResult[PayTypeList][0][PayCode]", mPayCode);
        //支付方式名称
        params.put("PayResult[PayTypeList][0][PayName]", mPayName);
        //支付金额
        params.put("PayResult[PayTypeList][0][PayMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));

        Li("扫码支付---------------------random:" + params.toString());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final KSXF_Success_Bean ksxf_success_bean = CommonFun.JsonToObj(responseString, KSXF_Success_Bean.class);
                payComplete(ksxf_success_bean.getData().getGID());
            }

            @Override
            public void onFailure(String msg) {
                if (msg.equals("SmsSign")) {//短信未设置默认签名
                    mSweetAlertDialog = new SweetAlertDialog(KuaiJieShouKuan.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，未设置默认签名！");
                    mSweetAlertDialog.setConfirmText("确定");
                    if (mMemberInfo == null) {
                        mSweetAlertDialog.setCancelText("添加为会员");
                    }
                    mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent intent = new Intent(KuaiJieShouKuan.this, AddMemberActivity.class);
                            startActivity(intent);
                        }
                    });
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            mSweetAlertDialog.dismiss();
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.equals("BuySms")) {//短信库存不足
                    mSweetAlertDialog = new SweetAlertDialog(KuaiJieShouKuan.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，短信库存不足！");
                    mSweetAlertDialog.setConfirmText("确定");
                    if (mMemberInfo == null) {
                        mSweetAlertDialog.setCancelText("添加为会员");
                    }
                    mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent intent = new Intent(KuaiJieShouKuan.this, AddMemberActivity.class);
                            startActivity(intent);
                        }
                    });
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                        }
                    });
                    mSweetAlertDialog.show();
                } else {
                    hintDialog(msg);
                }
            }
        };
        callBack.setLoadingAnimation(this, "正在支付...", false);
        HttpHelper.post(this, "ConsumeOrder/PaymentFastReceipt", params, callBack);
    }

    private void payComplete(final String GID) {
        try {
            mSweetAlertDialog = new SweetAlertDialog(KuaiJieShouKuan.this, SweetAlertDialog.SUCCESS_TYPE);
            mSweetAlertDialog.setTitleText("支付成功");
            mSweetAlertDialog.setConfirmText("确定");
            if (mMemberInfo == null) {
                mSweetAlertDialog.setCancelText("添加为会员");
            }
            mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    mSweetAlertDialog.dismiss();
                    Intent intent = new Intent(KuaiJieShouKuan.this, AddMemberActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    //打印小票
                    new HttpGetPrintContents(KuaiJieShouKuan.this, MyApplication.KSXF_PRINT_TIMES, GID).KSXF();
                    mSweetAlertDialog.dismiss();
//                            startActivity(new Intent(KuaiJieShouKuan.this, KuaiJieShouKuan.class));
//                            KuaiJieShouKuan.this.finish();
                }
            });
            mSweetAlertDialog.show();
        } catch (JsonSyntaxException e) {
            CustomToast.makeText(KuaiJieShouKuan.this, "打印失败！", Toast.LENGTH_SHORT).show();
//                    finish();
        }
        mTempVar = 0;
        mYsMoney = 0;
        mOrderMoney = 0;
        mEditContentBuilder = new StringBuilder("");
        tvMoney.setText("");
        tvCostMoney.setText("");
        lVipMessage.setVisibility(View.GONE);
    }

    /**
     * 提示对话框
     *
     * @param msg
     */
    private void hintDialog(String msg) {
        mSweetAlertDialog = new SweetAlertDialog(KuaiJieShouKuan.this, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText("提示")
                .setContentText(msg + "!")
                .setConfirmText("了解")
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //筛选会员后返回
        if (requestCode == 888) {
            try {
                mMember = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");
            } catch (NullPointerException e) {
            }
            if (mMember != null) {
                imgDeletVip.setVisibility(View.VISIBLE);
                imgDownChoise.setVisibility(View.INVISIBLE);
                if (mMember.getVIP_Name() != null && !"".equals(mMember.getVIP_Name())) {
                    tvVipName.setText(mMember.getVIP_Name());
                    lVipMessage.setVisibility(View.VISIBLE);
                } else {
                    tvVipName.setText(mMember.getVCH_Card());
                    lVipMessage.setVisibility(View.VISIBLE);
                }
                isSearch = false;
                vippresenter.postVip(mMember.getVCH_Card(), isSearch);
//                postVip(mMember.getVCH_Card());
            }
        }
        //扫码返回
        if (requestCode == 1 && resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            mSmPayCode = bundle.getString("result");
            mDialog = LoadingDialogUtil.createLoadingDialog(KuaiJieShouKuan.this, "支付中...", false);
            barCode();
        }
    }

    /**
     * 判断StringBuilder是否为空
     *
     * @param sb
     * @return
     */
    public static boolean isNull(StringBuilder sb) {
        if (sb == null) {
            return true;
        }
        if (sb.length() == 0) {
            return true;
        }
        return false;
    }


    /**
     * 计算显示金额
     *
     * @param str
     */
    private void calculate(String str) {
        mTempVar = 0;
        if (!str.isEmpty()) {
            if (".".equals(str.indexOf(str.length() - 1))) {
                str = str.substring(0, str.length() - 2);
                if (str.contains("+")) {
                    String[] s = str.split("\\+");
                    for (int i = 0; i < s.length; i++) {
                        mTempVar = DoubleMathUtil.add(mTempVar, Double.parseDouble(s[i]));
                    }
                } else {
                    mTempVar = Double.parseDouble(str);
                }
            } else {
                if (str.contains("+")) {
                    String[] s = str.split("\\+");
                    for (int i = 0; i < s.length; i++) {
                        mTempVar = DoubleMathUtil.add(mTempVar, Double.parseDouble(s[i]));
                    }
                } else {
                    mTempVar = Double.parseDouble(str);
                }
            }
        }
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tvMoney.setText(mEditContentBuilder.toString());
                    calculate(tvMoney.getText().toString());
                    mOrderMoney = mTempVar;
                    if (mDiscountProportion > 0) {
                        mYsMoney = DoubleMathUtil.mul(mOrderMoney, mDiscountProportion);
                    } else {
                        mYsMoney = mOrderMoney;
                    }
                    tvCostMoney.setText(Decima2KeeplUtil.stringToDecimal(mOrderMoney + ""));
                    //tvMoney.setText(mYsMoney + "");
                    break;
                case 2:
                    tvYuE.setText("" + mMemberInfo.getMA_AvailableBalance());
                    tvIntergal.setText("" + mMemberInfo.getMA_AvailableIntegral());
                    tvIntergal.setText("" + mMemberInfo.getMA_AvailableIntegral());
                    tvCard.setText(mCardNum);
                    break;
            }
        }
    }
}
