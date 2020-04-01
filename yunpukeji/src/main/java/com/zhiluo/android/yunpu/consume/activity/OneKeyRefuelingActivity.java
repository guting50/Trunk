package com.zhiluo.android.yunpu.consume.activity;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.adapter.OneKeyRefuelingadapter;
import com.zhiluo.android.yunpu.consume.bean.LimitedTimeConsumeBean;
import com.zhiluo.android.yunpu.entity.SuccessBean;
import com.zhiluo.android.yunpu.goods.consume.bean.OilStepOneBean;
import com.zhiluo.android.yunpu.goods.manager.bean.BarCodePayBean;
import com.zhiluo.android.yunpu.goods.manager.bean.OilListBean;
import com.zhiluo.android.yunpu.goods.manager.bean.QuerPayBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity;
import com.zhiluo.android.yunpu.member.manager.adapter.MemberCheckInListAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberFastCheckInBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.mvp.model.PasswordVerifyBean;
import com.zhiluo.android.yunpu.mvp.presenter.SaoMaPayPresntter;
import com.zhiluo.android.yunpu.mvp.view.SaoMaPayView;
import com.zhiluo.android.yunpu.print.bean.PrintSetBean;
import com.zhiluo.android.yunpu.print.bean.YJJY_Success_Bean;
import com.zhiluo.android.yunpu.print.util.GetPrintSet;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.sms.jsonbean.SmsSwitch;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.LoadingDialogUtil;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;
import com.zhiluo.android.yunpu.yslutils.YSLPayPopWindow;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ZPH on 2019-05-20.
 * 一键加油
 */

public class OneKeyRefuelingActivity extends BaseActivity implements OneKeyRefuelingadapter.modelItemListener, YSLPayPopWindow.OnItemClickListener, YSLPayPopWindow.OnDismissListener {

    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_delete_vip)
    ImageView ivDeleteVip;
    @Bind(R.id.iv_choise_vip_right)
    ImageView ivChoiseVipRight;
    @Bind(R.id.tv_choose_member)
    TextView tvChooseMember;
    @Bind(R.id.iv_choise_vip_left)
    ImageView ivChoiseVipLeft;
    @Bind(R.id.r_choise_vip)
    RelativeLayout rChoiseVip;
    @Bind(R.id.rl_pay_confirm_title)
    RelativeLayout rlPayConfirmTitle;
    @Bind(R.id.et_membercard_onekey)
    EditText etSearch;
    @Bind(R.id.iv_scan_limite)
    ImageView ivScan;
    @Bind(R.id.ll_scan_limite)
    LinearLayout llScanLimite;
    @Bind(R.id.tv_member_info_name)
    TextView tvMemberInfoName;
    @Bind(R.id.tv_member_info_card)
    TextView tvMemberInfoCard;
    @Bind(R.id.tv_member_info_balance)
    TextView tvMemberInfoBalance;
    @Bind(R.id.tv_member_info_integral)
    TextView tvMemberInfoIntegral;
    @Bind(R.id.re_oil_type)
    RecyclerView reOilType;
    @Bind(R.id.tv_oil_price)
    TextView tvOilPrice;
    @Bind(R.id.et_oil_money)
    EditText etOilMoney;
    @Bind(R.id.et_oil_num)
    EditText etOilNum;
    @Bind(R.id.tv_oil_integral)
    TextView tvOilIntegral;
    @Bind(R.id.cb_short_message)
    CheckBox cbMessage;
    @Bind(R.id.cb_print)
    CheckBox cbPrint;
    @Bind(R.id.tv_pay_confirm_submit)
    TextView tvPayConfirmSubmit;
    @Bind(R.id.l_recharge)
    LinearLayout lRecharge;
    @Bind(R.id.tv_pay_confirm_order)
    EditText tvPayConfirmOrder;
    @Bind(R.id.et_add_member_order_date)
    TextView etAddMemberOrderDate;
    @Bind(R.id.iv_add_member_order_date)
    ImageView ivAddMemberOrderDate;
    @Bind(R.id.tv_oil_unit_price)
    TextView tvOilUnitPrice;
    @Bind(R.id.tv_oil_rise)
    TextView tvOilUnitRise;

    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;
    private boolean mMustCard;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;//参数开关

    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间


    private String content;
    private AllMemberListBean.DataBean.DataListBean mMemberInfo;//会员信息

    private LimitedTimeConsumeBean mLimitedConsumeBean;
    private List<MemberFastCheckInBean.DataBean.DataListBean> mMemberCheckList = new ArrayList<>();
    private MemberCheckInListAdapter mAdapter;
    private int pribean = 0;
    private SweetAlertDialog mSweetAlertDialog;//提示框
    private int mOrderType;//支付类型（会员 1，散客 2）
    private String mMemberName = "散客";//会员姓名
    private String mMemberCardNo = "00000";//会员卡号

    private OilListBean mGoodsList;//商品列表
    private boolean isChecked;
    /**
     * 商品消费折后金额
     */
    private double spxfmoneyafterdiscount = 0.00;//折后金额;
    private OneKeyRefuelingadapter mshopRuleItemAdapter;
    private double zuidiPercent;//最低折扣
    private double dengjiPercent;
    private double tejiaPercent;//特价折扣
    private double percent;//计算后的百分比
    private double mOrderMoney;//订单金额
    private double mPoint;//获得积分
    private MemberInfoBean memberInfoBean;//查询会员所有信息返回数据
    private MemberInfoBean singleVip;
    private Handler mHandler;
    private YSLPayPopWindow mPayWayPopWindow;

    private String mPayWayName;//支付方式名称
    private String mPayWayCode;//支付方式代码
    private boolean mPasswordSwitch, mCashSwitch, mCardSwitch, mIntegralSwitch, mBalanceSwitch, mWXSwitch, mZfbSwitch, mSmSwitch, mOtherSwitch;
    private boolean isMoneyFocus, isnumFocus;//金额、数量输入框是否选中
    private String mGoodsBeanGID;//商品消费提交订单返回
    private String barcodeResultGID;
    private Dialog mDialog;
    private String mSmPayCode = "";//扫码返回条码
    private SaoMaPayPresntter presenter;
    private SaoMaPayView view;
    private String mOrderNo;//订单号
    private Dialog datesDialog;

    //抹零类型
    private int mZeroType = -1;
    //抹零值
    private double mZeroMoney = 0.00, mZeroTotalMoney = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_onekey_refueling);
        ButterKnife.bind(this);

        initData();
        initLisenter();
        setCbPrint();
        setCbShortMessage("011");

        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            Intent nfcIntent = new Intent(this, getClass());
            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mPendingIntent =
                    PendingIntent.getActivity(this, 0, nfcIntent, 0);

        }

    }


    private void initLisenter() {

        //金额过滤器
        MoneyInputFilter filter = new MoneyInputFilter(8, true, "最多只能输入8位金额");
        filter.setMessage("最多只能输入8位金额");
        etOilMoney.setFilters(new InputFilter[]{filter});
        etOilNum.setFilters(new InputFilter[]{filter});

        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pretime = System.currentTimeMillis();
                if (!hasTimerDown) {
                    doQurry();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable)) {
                    content = editable.toString();
                    //postVip(editable.toString());
                }
            }
        });

        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(OneKeyRefuelingActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        //设置选择会员监听
        rlPayConfirmTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OneKeyRefuelingActivity.this, CheckMemberInfoActivity.class);
                startActivityForResult(intent, 888);
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
                    CustomToast.makeText(OneKeyRefuelingActivity.this, "打印开关未开启，请设置", Toast.LENGTH_SHORT).show();
                    cbPrint.setChecked(false);
                }

            }
        });

        tvPayConfirmSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isChecked) {
                    CustomToast.makeText(OneKeyRefuelingActivity.this, "请先选择商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mMemberCardNo == null || mMemberCardNo.equals("")) {
                    CustomToast.makeText(OneKeyRefuelingActivity.this, "请先选择会员", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etOilMoney.getText().toString().isEmpty() || etOilMoney.getText().toString().equals("")) {
                    CustomToast.makeText(OneKeyRefuelingActivity.this, "请先输入加油金额", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Double.valueOf(etOilMoney.getText().toString()) <= 0) {
                    CustomToast.makeText(OneKeyRefuelingActivity.this, "加油金额不能为0元", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (etOilNum.getText().toString().isEmpty() || etOilNum.getText().toString().equals("")) {
                    CustomToast.makeText(OneKeyRefuelingActivity.this, "请先输入加油数量", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 0; i < mGoodsList.getData().size(); i++) {
                    if (mGoodsList.getData().get(i).isChecked()) {
                        mGoodsList.getData().get(i).setNum(Double.parseDouble(etOilNum.getText().toString()));
                        mGoodsList.getData().get(i).setZHMoney(Double.parseDouble(etOilMoney.getText().toString()));
                    }
                }
                initZero();
                YSLUtils.setBackgroundAlpha(0.5f, OneKeyRefuelingActivity.this);
                if (mZeroType > 0) {
                    mPayWayPopWindow = new YSLPayPopWindow(OneKeyRefuelingActivity.this, Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")), mSwitchEntity);
                } else {
                    mPayWayPopWindow = new YSLPayPopWindow(OneKeyRefuelingActivity.this, etOilMoney.getText().toString(), mSwitchEntity);
                }
                mPayWayPopWindow.setOnItemClickListener(OneKeyRefuelingActivity.this);
                mPayWayPopWindow.setOnDismissListener(OneKeyRefuelingActivity.this);
                mPayWayPopWindow.showAtLocation(OneKeyRefuelingActivity.this.findViewById(R.id.rl_pay_way),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);


//                goodsShopStepOne();
            }
        });


        //金额监听
        etOilMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                isMoneyFocus = hasFocus;
            }
        });

        //金额监听
        etOilMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isMoneyFocus) {
                    if (s.toString() != null && !s.toString().equals("") && !s.toString().equals(".")) {
                        spxfmoneyafterdiscount = Double.parseDouble(s.toString());
                        double num;
                        for (int i = 0; i < mGoodsList.getData().size(); i++) {
                            if (mGoodsList.getData().get(i).isChecked()) {
                                num = spxfmoneyafterdiscount / mGoodsList.getData().get(i).getOM_Price();
                                mGoodsList.getData().get(i).setZHMoney(spxfmoneyafterdiscount);
                                etOilNum.setText(Decima2KeeplUtil.stringToDecimal(num + ""));
                                if (singleVip != null) {
                                    mPoint = num * mGoodsList.getData().get(i).getEachPoint();
                                    tvOilIntegral.setText(Decima2KeeplUtil.stringToDecimal("" + mPoint));
                                }
                            }
                        }
                    } else {
                        etOilNum.setText("");
                    }
                }

            }
        });

        etOilNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean Focus) {
                isnumFocus = Focus;
            }
        });

        //数量监听
        etOilNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isnumFocus) {//获得焦点
                    if (s.toString() != null && !s.toString().equals("") && !s.toString().equals(".") && mGoodsList != null) {
                        double num = Double.parseDouble(s.toString());
                        for (int i = 0; i < mGoodsList.getData().size(); i++) {
                            if (mGoodsList.getData().get(i).isChecked()) {
                                spxfmoneyafterdiscount = Double.parseDouble(Decima2KeeplUtil.stringToDecimal((num * mGoodsList.getData().get(i).getOM_Price()) + ""));
                                mGoodsList.getData().get(i).setZHMoney(spxfmoneyafterdiscount);
                                etOilMoney.setText(spxfmoneyafterdiscount + "");
                                if (singleVip != null) {
                                    mPoint = num * mGoodsList.getData().get(i).getEachPoint();
                                    tvOilIntegral.setText(Decima2KeeplUtil.stringToDecimal("" + mPoint));
                                }
                            }
                        }
                    } else {
                        etOilMoney.setText("");
                    }
                }
            }
        });

        ivDeleteVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spxfmoneyafterdiscount = 0;
                mOrderType = 2;
                mPoint = 0;
                ivChoiseVipRight.setVisibility(View.VISIBLE);//右边图标
                ivDeleteVip.setVisibility(View.INVISIBLE);//删除会员图标
                tvChooseMember.setText("选择会员");
                mMemberCardNo = "00000";
                mMemberName = "散客";
                etSearch.setText("");
                tvMemberInfoName.setText("散客");
                tvMemberInfoCard.setText("00000");
                tvMemberInfoBalance.setText("0.00");
                tvMemberInfoIntegral.setText("0.00");
                for (int k = 0; k < mGoodsList.getData().size(); k++) {//删除会员后，把等级折后金额还原，
                    if (mGoodsList.getData().get(k).isChecked()) {
                        mGoodsList.getData().get(k).setEachPoint(0);
                    }
                }
                tvOilIntegral.setText(Decima2KeeplUtil.stringToDecimal(mPoint + ""));

            }
        });

        ivAddMemberOrderDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etAddMemberOrderDate.getText().toString().isEmpty()) {
                    showReallyDateDialog(DateUtil.getReallyDateForString(DateTimeUtil.getReallyTimeNow()), etAddMemberOrderDate);
                } else {
                    showReallyDateDialog(DateUtil.getReallyDateForString(etAddMemberOrderDate.getText().toString()), etAddMemberOrderDate);
                }
            }
        });

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

        mOrderNo = CreateOrder.createOrder("JY");
        tvPayConfirmOrder.setText(mOrderNo);
        etAddMemberOrderDate.setText(DateTimeUtil.getReallyTimeNow());
        tvMemberInfoName.setText("散客");
        tvMemberInfoCard.setText("00000");
        comfirmParams();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {

                    case 2:
                        singleVip = (MemberInfoBean) msg.getData().getSerializable("USER");
                        //只需选择会员后计算积分
                        countGoodsNum();
                        break;
                }

            }

        };


        presenter = new SaoMaPayPresntter(this);
        view = new SaoMaPayView() {
            @Override
            public void barCodeSuccess(BarCodePayBean entity) {
                barcodeResultGID = entity.getData().getGID();
                if (mDialog != null) {
                    LoadingDialogUtil.closeDialog(mDialog);
                }
                goodsShopStepTwo();
//                querPay();
            }

            @Override
            public void barCodeFail(BarCodePayBean entity) {
                if (entity.getCode().equals("410004")) {
                    barcodeResultGID = entity.getData().getGID();
                    querPay();
                } else {
                    CustomToast.makeText(OneKeyRefuelingActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void barCodeFailString(String responseString) {
                if (mDialog != null) {
                    LoadingDialogUtil.closeDialog(mDialog);
                }
                CustomToast.makeText(OneKeyRefuelingActivity.this, responseString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void querPaySuccess(QuerPayBean entity) {
                if (entity.isSuccess()) {
                    if (mDialog != null) {
                        LoadingDialogUtil.closeDialog(mDialog);
                    }
//                    goodsShopStepTwo();
                    payComplete(entity.getData().getGID());
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
                    CustomToast.makeText(OneKeyRefuelingActivity.this, YSLUtils.payResult(entity.getCode()), Toast.LENGTH_SHORT).show();
                } else {
                    if (mDialog != null) {
                        LoadingDialogUtil.closeDialog(mDialog);
                    }
                    CustomToast.makeText(OneKeyRefuelingActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        presenter.attachView(view);


        getGoods(1, 5);

    }


    /**
     * 计算已选商品积分
     */

    private void countGoodsNum() {
        mPoint = 0;
        for (int z = 0; z < mGoodsList.getData().size(); z++) {
            if (mGoodsList.getData().get(z).isChecked()) {
                OilListBean.DataBean bean = mGoodsList.getData().get(z);
                if (singleVip != null) {

                    //商品积分计算
                    if (singleVip.getData().getVG_IsIntegral() == 0) {//会员等级积分开关没有有打开
                        bean.setEachPoint(0);
                    } else if (singleVip.getData().getVG_IsIntegral() == 1) {
                        double bl = singleVip.getData().getVG_OilIntegral();
                        if (bl != 0) {

                            double fb = bean.getOM_Price() / bl;
                            bean.setEachPoint(fb);

                        } else {
                            bean.setEachPoint(0);
                        }
                    }
                } else {
                    bean.setEachPoint(0);
                }
                //计算商品消费获得总积分

                mPoint = bean.getEachPoint() * bean.getNum();
                tvOilIntegral.setText(Decima2KeeplUtil.stringToDecimal(mPoint + ""));
                tvOilPrice.setText(bean.getOM_Price() + "");

                /**一键加油升和立方单判断 20191021 cy */
                etOilMoney.setText("");
                etOilNum.setText("");
                if (bean.getOM_Unit().equals("升")) {
                    tvOilUnitPrice.setText("油品单价(元/升)");
                    tvOilUnitRise.setText("加油数量(升)");
                    etOilNum.setHint("请输入加油升数量");
                } else if (bean.getOM_Unit().equals("立方")) {
                    tvOilUnitPrice.setText("油品单价(元/立方)");
                    tvOilUnitRise.setText("加油数量(立方)");
                    etOilNum.setHint("请输入加油立方数量");
                }
                /********************************* 20191021 cy */

            }

        }

        if (etOilMoney.getText().toString() != null) {
            etOilMoney.requestFocus();
            etOilMoney.setText(etOilMoney.getText().toString());
        }


    }


    private void loadSwitch() {
        if (mSwitchEntity.size() > 0) {
            for (int i = 0; i < mSwitchEntity.size(); i++) {
                //必须刷卡
                if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mMustCard = true;
                        rlPayConfirmTitle.setEnabled(false);
                        llScanLimite.setEnabled(false);
                        llScanLimite.setBackgroundColor(getResources().getColor(R.color.lightgray));
                        etSearch.setHint("请使用刷卡机刷卡");
                        etSearch.setFocusable(false);
                        etSearch.setFocusableInTouchMode(false);
                        ivScan.setEnabled(false);

                    } else {
                        mMustCard = false;
                        rlPayConfirmTitle.setEnabled(true);
                        llScanLimite.setEnabled(true);
                        llScanLimite.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                        etSearch.setHint("请输入会员卡号/手机号");
                        etSearch.setFocusable(true);
                        etSearch.setFocusableInTouchMode(true);
                        ivScan.setEnabled(true);
                    }
                }

                if ("204".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mPasswordSwitch = true;
                    } else {
                        mPasswordSwitch = false;
                    }
                }
                if ("101".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mCashSwitch = true;
                    }
                }
                if ("102".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mBalanceSwitch = true;
                    }
                }
                if ("107".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mIntegralSwitch = true;
                    }
                }
                if ("103".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mCardSwitch = true;
                    }
                }

                //支付宝记账
                if ("106".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mZfbSwitch = true;
                    }
                }
                //微信记账
                if ("105".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mWXSwitch = true;
                    }
                }
                //扫码
                if ("111".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mSmSwitch = true;
                    }
                }
                //其他支付
                if ("113".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mOtherSwitch = true;
                    }
                }
                //自由抹零
                if ("801".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mZeroType = 0;
                    }
                }
                //四舍五入到元
                if ("803".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mZeroType = 1;
                    }
                }
                //四舍五入到角
                if ("802".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mZeroType = 2;
                    }
                }

                //直接舍弃角
                if ("804".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mZeroType = 3;
                    }
                }
                //直接舍弃分
                if ("805".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mZeroType = 4;
                    }
                }
            }
        } else {
            loadOtherSwitch();
        }
    }

    private void loadOtherSwitch() {
        mBalanceSwitch = mCashSwitch = mCardSwitch = true;
        mIntegralSwitch = mPasswordSwitch = mZfbSwitch = mWXSwitch = mSmSwitch = mOtherSwitch = mMustCard = false
        ;
//                etDiscountMoney.setInputType(InputType.TYPE_NULL);

    }

    /**
     * 获取系统开关
     */
    public void getSystemSwitch() {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean entity = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                mSwitchEntity = entity.getData().getGetSysSwitchList();
                CacheData.saveObject("switch", mSwitchEntity);//缓存系统开关到本地
                if (mSwitchEntity != null) {
                    loadSwitch();
                } else {
                    loadOtherSwitch();
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(OneKeyRefuelingActivity.this, "获取系统开关失败" + msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(OneKeyRefuelingActivity.this, HttpAPI.API().PRE_LOAD, callBack);
    }

    private void getGoods(int index, int size) {

        RequestParams params = new RequestParams();

//        params.put("DataType", 2);
//        params.put("pageIndex", index);
//        params.put("pageSize", size);
//        params.put("PT_GID", "");
//        params.put("PM_CodeOrNameOrSimpleCode","");//模糊查找
////        params.put("PM_CodePrecise",Searchcontetnt);//条码精确查找

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mGoodsList = CommonFun.JsonToObj(responseString, OilListBean.class);
                setRightAdapter();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(OneKeyRefuelingActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "加载中...", false);
        HttpHelper.post(this, HttpAPI.API().GET_OIL_LIST, params, callBack);

    }


    /**
     * 设置右边ListViewAdapter
     */
    private void setRightAdapter() {
        reOilType.setLayoutManager(new GridLayoutManager(this, 5));
        mshopRuleItemAdapter = new OneKeyRefuelingadapter(this, mGoodsList, this);
        mshopRuleItemAdapter.setCategoryBeans(mGoodsList);
        reOilType.setAdapter(mshopRuleItemAdapter);
    }

    /**
     * @return ,
     * 计算折扣
     */
    private double getPercent() {
        double result1;
        double result;

        if (zuidiPercent == 0 || zuidiPercent == 1) {
            result = dengjiPercent;
        } else {
            if (zuidiPercent > dengjiPercent) {
                result = zuidiPercent;
            } else {
                result = dengjiPercent;
            }
            if (result == 0) {
                result = 1;
            }
        }

        return result;

    }


    /**
     * @param tejia ,特价折扣
     * @param zuidi ，最低折扣
     * @return ，
     */
    private double compareTandD(double zuidi, double tejia) {
        double percent = 0;
        if (zuidi == 1) {
            zuidi = 0;
        }
        if (tejia == 1) {
            tejia = 0;
        }
        if (zuidi == 0) {
            percent = tejia;
        } else if (tejia == 0) {
            percent = zuidi;
        } else {
            if (tejia >= zuidi) {
                percent = tejia;
            } else {
                percent = zuidi;
            }
        }
        return percent;
    }

    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {
            if (!TextUtils.isEmpty(etSearch.getText())) {
                postVip(content);

            }
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }

    @Override
    public void modelItemChecked(OilListBean.DataBean conditionBean, boolean isChecked, int position) {
        for (int j = 0; j < mGoodsList.getData().size(); j++) {
            mGoodsList.getData().get(j).setChecked(false);
        }
        if (isChecked) {
            conditionBean.setChecked(true);
            this.isChecked = isChecked;
        }
        countGoodsNum();
    }

    @Override
    public void onDismiss() {
        YSLUtils.setBackgroundAlpha(1.0f, this);
    }


    private class InputHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0://0正在输入
                    break;
                case 1://1输入完成
                    doQurry();
                    break;
            }
        }
    }

    private void comfirmParams() {
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            loadSwitch();
        } else {
            getSystemSwitch();
            loadOtherSwitch();
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
                    cbMessage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CustomToast.makeText(OneKeyRefuelingActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
                            cbMessage.setChecked(false);
                        }
                    });
                } else {
                    cbMessage.setChecked(true);
                }
            } else {
                getSmsSet(code);
            }
        } catch (Exception e) {
            cbMessage.setVisibility(View.INVISIBLE);
        }
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
            } else {
                getPrintSet();
            }

        } catch (Exception e) {
            cbPrint.setVisibility(View.INVISIBLE);
        }
    }


    /**
     * 获取短信开关
     */
    private void getSmsSet(final String code) {
        HttpHelper.post(OneKeyRefuelingActivity.this, HttpAPI.API().SMS_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SmsSwitch bean = CommonFun.JsonToObj(responseString, SmsSwitch.class);
                for (int i = 0; i < bean.getData().size(); i++) {
                    if (bean.getData().get(i).getST_Code().equals(code)) {
                        if (bean.getData().get(i).getST_State() == null || !bean.getData().get(i).getST_State().equals("1")) {
                            cbMessage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CustomToast.makeText(OneKeyRefuelingActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
                                    cbMessage.setChecked(false);
                                }
                            });
                        } else {
                            cbMessage.setChecked(true);
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
     * //     * 获取打印设置
     * //
     */
    private void getPrintSet() {  // http://dj.zhiluo.net/api/PrintSet/GetPrintSet
        HttpHelper.post(OneKeyRefuelingActivity.this, HttpAPI.API().PRINTSET, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                PrintSetBean bean = CommonFun.JsonToObj(responseString, PrintSetBean.class);
                if (bean.getData().getPS_IsEnabled() != 1) {
                    cbPrint.setChecked(false);
                    pribean = 0;
                } else {
                    cbPrint.setChecked(true);
                    pribean = 1;
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }


    /**
     * 一键加油提交订单
     */
    private void goodsShopStepOne() {

        RequestParams params = new RequestParams();
        //会员卡号
        params.put("VIP_Card", mMemberCardNo == null ? "00000" : mMemberCardNo);
        //油品
        for (int i = 0; i < mGoodsList.getData().size(); i++) {
            if (mGoodsList.getData().get(i).isChecked()) {
                params.put("OM_GID", mGoodsList.getData().get(i).getGID());
            }
        }
        //加油金额
        params.put("OrderAmount", etOilMoney.getText().toString());
        //加油数量
        params.put("Number", etOilNum.getText().toString());
        //订单号
//        params.put("OrderCode", CreateOrder.createOrder("JY"));
        params.put("OrderCode", tvPayConfirmOrder.getText().toString());
        //订单时间
        params.put("OrderTime", etAddMemberOrderDate.getText().toString());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Le("订单提交成功");
                OilStepOneBean stepOneBeans = CommonFun.JsonToObj(responseString, OilStepOneBean.class);
                mGoodsBeanGID = stepOneBeans.getData().getGID();

//                goodsShopStepTwo();

                if (mPayWayCode.equals("SMZF")) {
                    //打开扫描界面扫描条形码或二维码
                    Intent openCameraIntent = new Intent(OneKeyRefuelingActivity.this, CaptureActivity.class);
                    startActivityForResult(openCameraIntent, 1);
                } else {
                    goodsShopStepTwo();
                }

            }

            @Override
            public void onFailure(String msg) {
                new SweetAlertDialog(OneKeyRefuelingActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        .setContentText(msg)
                        .setConfirmText("了解")
                        .show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交订单...", false);
        HttpHelper.post(this, HttpAPI.API().ONE_KEY_OIL_SUB, params, callBack);//GOODS_CONSUME_SUB
    }

    /**
     * 商品订单支付
     */
    private void goodsShopStepTwo() {

        RequestParams params = new RequestParams();
        //订单GID
        params.put("OrderGID", mGoodsBeanGID);
        //是否发送短信
        params.put("Smsg", cbMessage.isChecked() ? 1 : 0);
        if (mZeroType > 0) {
            //应收金额
            params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
            //实收金额
            params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
            //支付金额
            params.put("PayResult[PayTypeList][0][PayMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
        } else {
            //应收金额
            params.put("PayResult[DisMoney]", etOilMoney.getText().toString());
            //实收金额
            params.put("PayResult[PayTotalMoney]", etOilMoney.getText().toString());
            //支付金额
            params.put("PayResult[PayTypeList][0][PayMoney]", etOilMoney.getText().toString());
        }
        //抹零金额
        params.put("PayResult[EraseOdd]", mZeroMoney);
        //找零金额
        params.put("PayResult[GiveChange]", 0);
        //支付方式编码
        params.put("PayResult[PayTypeList][0][PayCode]", mPayWayCode);
        //支付方式名称
        params.put("PayResult[PayTypeList][0][PayName]", mPayWayName);

        Li("一键加油--two" + params.toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final YJJY_Success_Bean yjjy_success_bean = CommonFun.JsonToObj(responseString, YJJY_Success_Bean.class);
                payComplete(yjjy_success_bean.getData().getGID());
            }

            @Override
            public void onFailure(String msg) {
                etAddMemberOrderDate.setText(DateTimeUtil.getReallyTimeNow());
                if (msg.contains("SmsSign")) {
                    mSweetAlertDialog = new SweetAlertDialog(OneKeyRefuelingActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，未设置默认签名！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            viewClear();

                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.equals("BuySms")) {
                    mSweetAlertDialog = new SweetAlertDialog(OneKeyRefuelingActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，短信库存不足！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            viewClear();
                        }
                    });
                    mSweetAlertDialog.show();
                } else {
                    new SweetAlertDialog(OneKeyRefuelingActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText(msg)
                            .setConfirmText("了解")
                            .show();
                }
            }
        };
        callBack.setLoadingAnimation(this, "正在支付...", false);
        HttpHelper.post(this, HttpAPI.API().ONE_KEY_OIL_PAY, params, callBack);
    }

    private void payComplete(final String GID) {
        try {
            mSweetAlertDialog = new SweetAlertDialog(OneKeyRefuelingActivity.this, SweetAlertDialog.SUCCESS_TYPE);
            mSweetAlertDialog.setTitleText("支付成功");
            mSweetAlertDialog.setConfirmText("确定");
            if (mOrderType != 1) {
                mSweetAlertDialog.setCancelText("添加为会员");
            }
            mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intent = new Intent(OneKeyRefuelingActivity.this, AddMemberActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    mPayWayPopWindow.dismiss();

                    //打印小票
                    if (cbPrint.isChecked()) {
                        if (MyApplication.mGoodsConsumeMap.isEmpty()) {
                            GetPrintSet.getPrintParamSet();
                        }
                        new HttpGetPrintContents(OneKeyRefuelingActivity.this, MyApplication.YJJY_PRINT_TIMES, GID).YJJY();
                    }

                    viewClear();

                }
            });
            mSweetAlertDialog.show();
            mOrderNo = CreateOrder.createOrder("JY");
            tvPayConfirmOrder.setText(mOrderNo);
            etAddMemberOrderDate.setText(DateTimeUtil.getReallyTimeNow());
        } catch (JsonSyntaxException e) {
            CustomToast.makeText(OneKeyRefuelingActivity.this, "打印失败！", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * @param v ,
     *          选择付款方式
     */
    @Override
    public void setOnItemClick(View v) {

        switch (v.getId()) {
            case R.id.tv_cancel:
                YSLUtils.setBackgroundAlpha(1.0f, this);
                mPayWayPopWindow.dismiss();
                break;
            case R.id.l_cash:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mCashSwitch) {
                        //商品消费
                        setCashPayVariable();
//                        goodsConsumePay();
                        goodsShopStepOne();
                    } else {
                        CustomToast.makeText(OneKeyRefuelingActivity.this, "未开启现金支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.l_yue:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mBalanceSwitch) {
                        if (mOrderType == 1) {
                            if (memberInfoBean != null && memberInfoBean.getData().getMA_AvailableBalance() >= spxfmoneyafterdiscount) {
                                if (mPasswordSwitch) {
                                    showPasswordDialog();
                                } else {
                                    setYuEPayVariable();
//                                    goodsConsumePay();
                                    goodsShopStepOne();
                                }
                            } else {

                                mSweetAlertDialog = new SweetAlertDialog(OneKeyRefuelingActivity.this, SweetAlertDialog.WARNING_TYPE);
                                mSweetAlertDialog.setTitleText("提示")
                                        .setContentText("余额不足，请选择其它支付方式!")
                                        .setConfirmText("了解")
                                        .show();
                            }
                        } else {
                            mSweetAlertDialog = new SweetAlertDialog(OneKeyRefuelingActivity.this, SweetAlertDialog.WARNING_TYPE);
                            mSweetAlertDialog.setTitleText("提示")
                                    .setContentText("非会员无法使用余额支付!")
                                    .setConfirmText("了解")
                                    .show();
                        }

                    } else {
                        CustomToast.makeText(OneKeyRefuelingActivity.this, "未开启余额支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            //联合支付
            case R.id.r_union:

                YSLUtils.setBackgroundAlpha(1.0f, this);
                mPayWayPopWindow.dismiss();
                Intent intent = new Intent(OneKeyRefuelingActivity.this, UnionPayActivity.class);
                ArrayList list = new ArrayList();


                List<OilListBean.DataBean> list1 = new ArrayList();
                for (int i = 0; i < mGoodsList.getData().size(); i++) {
                    if (mGoodsList.getData().get(i).isChecked()) {
                        list1.add(mGoodsList.getData().get(i));
                    }
                }
                list.add(list1);
                Bundle bundle = new Bundle();
                intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) list);
                bundle.putParcelableArrayList("list", list);
                intent.putExtra("bundle", bundle);
                intent.putExtra("VIP_INFO_RE", memberInfoBean == null ? null : memberInfoBean.getData());

                /**加油站版本联合支付不可使用余额支付（提示非会员无法使用余额）,选择会员信息为mMemberInfo 20191021 cy */
//                intent.putExtra("VIP_INFO", memberInfoBean);
                intent.putExtra("VIP_INFO", mMemberInfo);
                /********************************* 20191021 cy */
//                intent.putExtra(MyApplication.ORDER_NO, mOrderNo);
                intent.putExtra(MyApplication.ORDER_NO, CreateOrder.createOrder("JY"));
                intent.putExtra(MyApplication.CARD_NO, mMemberCardNo);
                intent.putExtra("ORDER_MONEY", Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etOilMoney.getText().toString() + "")));
                intent.putExtra("CO_Monetary", Double.parseDouble(etOilMoney.getText().toString()));
//                    intent.putExtra("MONEY", mDiscountMoney);
                intent.putExtra("MONEY", Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etOilMoney.getText().toString() + "")));
                intent.putExtra("POINT", mPoint);
                intent.putExtra(MyApplication.PAGE_FLAG, "YJJY");
                intent.putExtra("message", cbMessage.isChecked());
                intent.putExtra("print", cbPrint.isChecked());
                intent.putExtra("CC_GID", "");
                intent.putExtra("remark", "");
                intent.putExtra("OrderTime", etAddMemberOrderDate.getText().toString());

                startActivity(intent);

                break;
            //银联刷卡
            case R.id.l_card:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mCardSwitch) {
                        setBankPayVariable();
                        goodsShopStepOne();
                    } else {
                        CustomToast.makeText(OneKeyRefuelingActivity.this, "未开启银联支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.l_zhifubao:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mZfbSwitch) {
                        //商品消费
                        setZfbPayVariable();
//                        goodsConsumePay();
                        goodsShopStepOne();
                    } else {
                        CustomToast.makeText(OneKeyRefuelingActivity.this, "未开启支付宝记账，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.l_weixin:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mWXSwitch) {
                        //商品消费
                        setWxPayVariable();
//                        goodsConsumePay();
                        goodsShopStepOne();

                    } else {
                        CustomToast.makeText(OneKeyRefuelingActivity.this, "未开启微信记账，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            //扫码支付
            case R.id.l_saoma:
                if (mSmSwitch) {
                    setScanCodePayVariable();
                    goodsShopStepOne();
                } else {
                    CustomToast.makeText(OneKeyRefuelingActivity.this, "未开启扫码支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.l_other:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mOtherSwitch) {
                        //商品消费
                        setOtherPayVariable();
//                        goodsConsumePay();
                        goodsShopStepOne();

                    } else {
                        CustomToast.makeText(OneKeyRefuelingActivity.this, "未开启其他支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
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
        AlertDialog.Builder builder = new AlertDialog.Builder(OneKeyRefuelingActivity.this);
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
                params.put("VCH_Card", mMemberCardNo);
                params.put("VCH_Pwd", etPassword.getText().toString());
                HttpHelper.post(OneKeyRefuelingActivity.this, HttpAPI.API().PASSWORDVERIFY, params, new CallBack() {
                    @Override
                    public void onSuccess(String responseString, Gson gson) {
                        PasswordVerifyBean bean = CommonFun.JsonToObj(responseString, PasswordVerifyBean.class);
                        if (bean.isSuccess()) {
                            setYuEPayVariable();
                            goodsShopStepOne();
                            alertDialog.dismiss();
                        } else {
                            CustomToast.makeText(OneKeyRefuelingActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        CustomToast.makeText(OneKeyRefuelingActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
            }
        });
        alertDialog.show();
    }

    /**
     * 设置现金支付变量的值
     */
    private void setCashPayVariable() {

        mPayWayName = "现金支付";
        mPayWayCode = "XJZF";
    }

    /**
     * 设置余额支付变量的值
     */
    private void setYuEPayVariable() {

        mPayWayName = "余额支付";
        mPayWayCode = "YEZF";
    }

    /**
     * 设置银联支付变量的值
     */
    private void setBankPayVariable() {

        mPayWayName = "银联支付";
        mPayWayCode = "YLZF";

    }


    /**
     * 设置支付宝记账变量的值
     */
    private void setZfbPayVariable() {

        mPayWayName = "支付宝记账";
        mPayWayCode = "ZFB_JZ";
    }

    /**
     * 设置微信记账变量的值
     */
    private void setWxPayVariable() {

        mPayWayName = "微信记账";
        mPayWayCode = "WX_JZ";
    }

    /**
     * 设置扫码支付变量的值
     */
    private void setScanCodePayVariable() {

        mPayWayName = "扫码支付";
        mPayWayCode = "SMZF";

    }

    /**
     * 设置其他支付变量
     **/

    private void setOtherPayVariable() {

        mPayWayName = "其他支付";
        mPayWayCode = "QTZF";

    }

    private void viewClear() {
        mPayWayPopWindow.dismiss();
        ivChoiseVipRight.setVisibility(View.VISIBLE);//右边图标
        ivChoiseVipLeft.setVisibility(View.VISIBLE);//左边图标
        ivDeleteVip.setVisibility(View.INVISIBLE);//删除会员图标
        mMemberName = "散客";
        mMemberCardNo = "00000";
        etSearch.setText("");
        tvChooseMember.setText("选择会员");
        tvMemberInfoName.setText(mMemberName);
        tvMemberInfoCard.setText(mMemberCardNo);
        tvMemberInfoBalance.setText("0.00");
        tvMemberInfoIntegral.setText("0.00");
        tvOilPrice.setText("");
        etOilMoney.setText("");
        etOilNum.setText("");
        tvOilIntegral.setText("");

        singleVip = null;
        memberInfoBean = null;
        mOrderNo = CreateOrder.createOrder("JY");
        tvPayConfirmOrder.setText(mOrderNo);
        getGoods(1, 5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            etSearch.setText(scanResult);
        }
        if (requestCode == 888 && resultCode == 2222) {
            mMemberInfo = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");

            if (mMemberInfo != null) {
                mOrderType = 1;
                mMemberName = mMemberInfo.getVIP_Name();
                mMemberCardNo = mMemberInfo.getVCH_Card();

                etSearch.setText(mMemberCardNo);
                etSearch.setSelection(mMemberCardNo.length());
                if (mMemberInfo.getVIP_Name() != null && !"".equals(mMemberInfo.getVIP_Name())) {
                    tvChooseMember.setText(mMemberInfo.getVIP_Name());
                } else {
                    tvChooseMember.setText(mMemberInfo.getVCH_Card());
                }
                if (mMemberInfo.getVIP_Name() != null && !"".equals(mMemberInfo.getVIP_Name())) {
                    tvMemberInfoName.setText(mMemberInfo.getVIP_Name());
                }
                tvMemberInfoCard.setText(mMemberInfo.getVCH_Card());
                tvMemberInfoBalance.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableBalance() + ""));
                tvMemberInfoIntegral.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableIntegral() + ""));

            } else {
                mOrderType = 2;
                mMemberName = "散客";
                mMemberCardNo = "00000";
                tvChooseMember.setText("选择会员");
                tvMemberInfoName.setText(mMemberName);
                tvMemberInfoCard.setText(mMemberCardNo);
                tvMemberInfoBalance.setText("0.00");
                tvMemberInfoIntegral.setText("0.00");
            }

        }

        if (requestCode == 1 && resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            mSmPayCode = bundle.getString("result");
            mDialog = LoadingDialogUtil.createLoadingDialog(OneKeyRefuelingActivity.this, "支付中...", false);
            barCode(10, mGoodsBeanGID);

        }

    }

    /**
     * @param k ，会员卡号
     */
    private void postVip(String k) {
        //card = "";
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("VCH_Card", k);
        params.put("AccurateOfCard", k);
        params.put("isNeedVG", 1);
        client.post(HttpAPI.API().QUERY_SINGLE_MEMBER, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resurt = null;
                    try {
                        resurt = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        SuccessBean bean = CommonFun.JsonToObj(resurt, SuccessBean.class);
                        if (bean.isSuccess()) {
                            ivChoiseVipRight.setVisibility(View.INVISIBLE);//右边图标
                            ivChoiseVipLeft.setVisibility(View.VISIBLE);//左边图标
                            ivDeleteVip.setVisibility(View.VISIBLE);//删除会员图标
                            mOrderType = 1;
                            memberInfoBean = CommonFun.JsonToObj(resurt, MemberInfoBean.class);
                            mMemberCardNo = memberInfoBean.getData().getVCH_Card();
                            mMemberName = memberInfoBean.getData().getVIP_Name();
                            if (mMemberName != null && !"".equals(mMemberName)) {
                                tvChooseMember.setText(mMemberName);
                            } else {
                                tvChooseMember.setText(mMemberCardNo);
                            }

                            if (mMemberName != null && !"".equals(mMemberName)) {
                                tvMemberInfoName.setText(mMemberName);
                            }
                            tvMemberInfoCard.setText(mMemberCardNo);
                            tvMemberInfoBalance.setText(Decima2KeeplUtil.stringToDecimal(memberInfoBean.getData().getMA_AvailableBalance() + ""));
                            tvMemberInfoIntegral.setText(Decima2KeeplUtil.stringToDecimal(memberInfoBean.getData().getMA_AvailableIntegral() + ""));

                            if (memberInfoBean != null) {
                                Message message = new Message();
                                message.what = 2;
                                Bundle b = new Bundle();
                                b.putSerializable("USER", memberInfoBean);
                                message.setData(b);
                                mHandler.sendMessage(message);
                            }
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                if (MyApplication.VIP_CARD != null) {
                    postVip(MyApplication.VIP_CARD);
                    MyApplication.VIP_CARD = null;
                } else {
                    tvChooseMember.setText("选择会员");
                    tvMemberInfoName.setText(mMemberName);
                    tvMemberInfoCard.setText(mMemberCardNo);
                    tvMemberInfoBalance.setText("0.00");
                    tvMemberInfoIntegral.setText("0.00");
                }
                Log.i(TAG, "onFailure: ");
            }
        });
    }


    //获取系统隐式启动的
    @Override
    public void onNewIntent(Intent intent) {
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tagFromIntent != null) {
            String CardId = ByteArrayToHexString.ByteArrayToHex(tagFromIntent.getId());
            if (null != CardId) {
                MyApplication.VIP_CARD = CardId;
                while (CardId.length() < 10) {
                    CardId = "0" + CardId;
                }
                etSearch.setText(CardId);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mNFCAdapter != null) {

//             mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, intentFiltersArray, techListsArray);
            //隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
        /**
         * lkl、sunmi 、basewin的检卡 读卡操作
         */
        if (!MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            new CardOperationUtils(this, etSearch);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNFCAdapter != null) {
            //隐式启动
            mNFCAdapter.disableForegroundDispatch(this);
        }
    }

    private void barCode(int type, String Gid) {
        RequestParams params = new RequestParams();
        params.put("Code", mSmPayCode);
        params.put("Money", etOilMoney.getText().toString());
        params.put("OrderGID", Gid);
        params.put("OrderType", type);//10商品消费 20充次 30计时消费 40 充值 50 套餐消费 60快速消费
//        params.put("OrderNo", tvPayConfirmOrder.getText().toString());
        params.put("OrderNo", CreateOrder.createOrder("JY"));
        params.put("OrderPayInfo[PayPoint]", 0);
        params.put("OrderPayInfo[PayTotalMoney]", etOilMoney.getText().toString());
        params.put("OrderPayInfo[GiveChange]", 0);
        params.put("OrderPayInfo[DisMoney]", etOilMoney.getText().toString());
        params.put("OrderPayInfo[IsEraseZero]", false);
        params.put("OrderPayInfo[PayTypeList][0][PayName]", mPayWayName);
        params.put("OrderPayInfo[PayTypeList][0][PayCode]", mPayWayCode);
        params.put("OrderPayInfo[PayTypeList][0][PayMoney]", etOilMoney.getText().toString());

        presenter.barCode(params);
    }

    private void querPay() {
        RequestParams params = new RequestParams();
        params.put("ResultGID", barcodeResultGID);
        presenter.querPay(params);
    }

    /**
     * 抹零
     */
    private void initZero() {
        if (mZeroType < 1) {
            return;
        }
        String flagMoney = etOilMoney.getText().toString();
        double moneyFlag = Double.parseDouble(flagMoney);
        if (mZeroType == 1) {//四舍五入到元
            mZeroMoney = DoubleMathUtil.sub(moneyFlag, Double.parseDouble(String.format("%.0f", moneyFlag)));
        } else if (mZeroType == 2) {//四舍五入到角
            mZeroMoney = DoubleMathUtil.sub(moneyFlag, Double.parseDouble(String.format("%.1f", moneyFlag)));
        } else if (mZeroType == 3) {//直接舍弃角
            mZeroMoney = DoubleMathUtil.sub(moneyFlag, (int) (moneyFlag / 1));
        } else if (mZeroType == 4) {//直接舍弃分
            String s = moneyFlag + "";
            if (s.substring(s.indexOf("."), s.length()).length() == 3) {
                s = s.substring(s.length() - 1, s.length());
                mZeroMoney = Double.parseDouble("0.0" + s);
            } else {
                mZeroMoney = 0.00;
            }
        }
        mZeroTotalMoney = moneyFlag - mZeroMoney;
        Li(mZeroTotalMoney + "====initZero===========random:" + mZeroMoney);
    }

}
