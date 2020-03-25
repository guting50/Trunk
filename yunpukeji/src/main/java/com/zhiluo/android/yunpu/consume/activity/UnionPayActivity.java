package com.zhiluo.android.yunpu.consume.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.bean.FastPayStepOneBean;
import com.zhiluo.android.yunpu.consume.bean.PayWayBean;
import com.zhiluo.android.yunpu.consume.bean.YHQBean;
import com.zhiluo.android.yunpu.consume.jsonbean.SystemSwitchBean;
import com.zhiluo.android.yunpu.entity.TCListBean;
import com.zhiluo.android.yunpu.goods.consume.activity.GoodsConsumeActivity;
import com.zhiluo.android.yunpu.goods.consume.bean.GoodsStepOneBeans;
import com.zhiluo.android.yunpu.goods.consume.bean.OilStepOneBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.OilListBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.consume.activity.MemberRechargeActivity;
import com.zhiluo.android.yunpu.member.consume.activity.VipDelayActivity;
import com.zhiluo.android.yunpu.member.consume.bean.VipInflateMoneyBean;
import com.zhiluo.android.yunpu.member.consume.bean.VipInflateStepOneBean;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.mvp.model.PasswordVerifyBean;
import com.zhiluo.android.yunpu.mvp.presenter.PostVipPresenter;
import com.zhiluo.android.yunpu.mvp.view.IPostVipView;
import com.zhiluo.android.yunpu.print.bean.HYCC_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.HYCZ_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.KSXF_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.SPXF_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.YJJY_Success_Bean;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 联合支付
 * 作者：罗咏哲 on 2017/6/24 10:01.
 * 邮箱：137615198@qq.com
 */
public class UnionPayActivity extends BaseActivity {
    private Handler mHandler;
    private Intent mIntent;//Intent
    private TextView tvYue, tvJifen, tv_order_money, tvPay, tvZhaoLing, tvYHQ;//可用余额、可抵扣金额、应付金额、找零金额
    private EditText etpaymoling, etPayCash, etPayCard, etPayYue, etPayJiFen, etWx, etZfb, etYHQ;//现金支付，银联支付，余额支付，积分支付
    private Button btnConfirm;//支付确认
    private SystemSwitchBean mSystemSwitch;//系统开关
    private List<SystemSwitchBean.DataBean> mSwitchList;//系统开关集合
    private double mPointPercent;//积分兑换比例（多少积分兑换一元）
    private double mMemberPoint;//会员积分
    private double mDeductibleMoney;//可抵扣金额
    private double mAvailableMoney;//可用余额
    private double mBalanceMoney;//可用支付余额
    private double mReceivableAmount;//应收金额
    private double mOrderMoney;//订单金额
    private double mZLMoney;//找零金额
    private double mGetPoints;//获得积分
    private double mSSMoney;//实收金额
    private double mPointSpend;//花费积分
    private double mRechargeMoney;//充值金额
    private double mGiveMoney;//赠送金额
    private double mRechargeTotal;//充值总金额
    private String mCardNo, mOrderNo;//会员卡号、订单号
    private String mSG_GID;
    private String mSG_Name;
    private int mMC_Number;
    private String mPageFlag;//页面标志
    private int mOrderType;//支付类型（会员 1，散客 2）
    SweetAlertDialog mSweetAlertDialog;
    private AllMemberListBean.DataBean.DataListBean mMemberInfo;//会员信息
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodeBeanList = new ArrayList<>();
    private List<OilListBean.DataBean> mYJJYBeanList = new ArrayList<>();
    private String mDiscountActivityGid;//优惠活动GID
    private ArrayList<String> mStaffListGid = new ArrayList<>();//提成员工GID
    private Map<String, ArrayList<String>> mStaffListGidList = new HashMap<>();//商品消费、会员充次提成员工GID
    private List<PayWayBean> mPayWayBeanList;//支付方式集合
    private PayWayBean mPayWayBean;
    //会员管理->商品消费->支付确认
    private String MDZZ;
    private double YUEMoney = 0;

    private SharedPreferences sp, pref;
    private String mMaccode;//蓝牙地址
    private String mOperator;//操作人员
    private String mShopName;//店铺名称

    private String mDelayTime;//延长时间
    private String mDelayType;
    private String mRemark;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean mPasswordSwitch;
    private YHQBean mYhqBean;

    private String[] mYH_GID;//优惠活动GID
    private double mYH_Money;//优惠金额
    private int mYHType;//优惠活动类型
    private boolean mYhqSwitch = false;
    private int mZeroType = -1;//抹零类型
    //抹零值
    private double mZeroMoney = 0.00;
    MemberInfoBean.DataBean.CouponsListBean mMemberYHQBean;
    private List<YHQBean.DataBean> mCheckedList = new ArrayList<>();
    private TCListBean.DataBean mTCList;

    private String mOrderGID;//订单提交成功后返回参数
    private String mVipInflatCountGID;//会员充次订单提交返回参数
    private String mVipInflateMoneyGID;//会员充值订单提交返回参数
    private String mGoodsBeanGID;//商品消费提交订单返回
    private String remark;//
    private String OrderTime;
    private boolean isTurn = true;//是否在销毁当前界面的同时，跳转到快捷收款界面去；
    private boolean mMessage;//是否发送短信
    private boolean mPrint;//是否打印
    private double mPayPointMoney = 0;//用户输入的用积分抵消的金额
    private String type = "";
    private double mTotalMoney, ksxfmDiscountMoney;
    private MemberInfoBean memberInfoBean;

    private PostVipPresenter vippresenter;
    private IPostVipView vipView;
    private IntentHandler intentHandler;
    private boolean isSearch = true;
    private String moren;

    /**
     * 会员充值联合支付，单个会员信息
     */
    private MemberInfoBean.DataBean vipdatalist;

    /**
     * 会员延期延长次数
     */
    private String mMIA_SurplusTimes;

    //可用支付余额百分比
    private double mPercentMoney;

    private RelativeLayout rl_check_yhq_legal;

    //价格总合(不算优惠,算了会员优惠的)
    private double mPriceTotal;

    //优惠活动价格
    private double mCouponMoney = 0.00;
    //优惠之前的价格,主要是用来计算优惠券的(最初价格)
    private double mNotCouponMoney = 0.00;

    private boolean mYhqBool = false;
    private String extra_ksxf_discount;
    //快速消费金额
    private double mKsxfFlagMoney = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getSystemSwitch();
        setContentView(R.layout.activity_union);
        initView();
        initVipData();
        initVariable();
        initSp();
        Li("initVariable----try");
        setListener();

    }


    /**
     * 初始化控件
     */
    private void initView() {

        tvYue = (TextView) findViewById(R.id.tv_union_pay_yue);
        tvJifen = (TextView) findViewById(R.id.tv_union_pay_jifeng);
        tvPay = (TextView) findViewById(R.id.tv_union_pay_receivable);
        tv_order_money = (TextView) findViewById(R.id.tv_order_money);
        tvZhaoLing = (TextView) findViewById(R.id.tv_union_pay_zhaoling);
        etpaymoling = findViewById(R.id.et_union_pay_moling);
        etPayCash = (EditText) findViewById(R.id.et_union_pay_cash);
        etPayCard = (EditText) findViewById(R.id.et_union_pay_card);
        etPayYue = (EditText) findViewById(R.id.et_union_pay_yue);
        etPayJiFen = (EditText) findViewById(R.id.et_union_pay_jifen);
        btnConfirm = (Button) findViewById(R.id.btn_union_pay_confirm);
        etWx = (EditText) findViewById(R.id.et_union_pay_wx);
        etZfb = (EditText) findViewById(R.id.et_union_pay_zfb);
        etYHQ = (EditText) findViewById(R.id.et_union_pay_yhq);
        tvYHQ = (TextView) findViewById(R.id.tv_check_yhq_legal);
        rl_check_yhq_legal = (RelativeLayout) findViewById(R.id.rl_check_yhq_legal);

    }

    private void initVipData() {
        intentHandler = new IntentHandler();
        vippresenter = new PostVipPresenter(this);
        vipView = new IPostVipView() {
            @Override
            public void getvipsuccesss(MemberInfoBean bean) {
                isSearch = true;
                memberInfoBean = bean;
                mAvailableMoney = memberInfoBean.getData().getMA_AvailableBalance();
//                tvYue.setText(Decima2KeeplUtil.stringToDecimal(memberInfoBean.getData().getMA_AvailableBalance() + ""));
            }

            @Override
            public void getvipfail(String result) {

            }
        };
        vippresenter.attachView(vipView);

        vippresenter.postVip(getIntent().getStringExtra(MyApplication.CARD_NO), isSearch);

    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        mPayWayBeanList = new ArrayList<>();
        mIntent = getIntent();
        type = mIntent.getStringExtra("type");
        if (mIntent != null) {
            if (mIntent.getStringExtra("type") != null && mIntent.getStringExtra("type").equals("KJZF")) {
                isTurn = false;
            }
            MDZZ = mIntent.getStringExtra("MDZZ");
            mPageFlag = mIntent.getStringExtra(MyApplication.PAGE_FLAG);
            try {
                mMemberInfo = (AllMemberListBean.DataBean.DataListBean) mIntent.getSerializableExtra("VIP_INFO");
            } catch (ClassCastException e) {
            }
            vipdatalist = (MemberInfoBean.DataBean) mIntent.getSerializableExtra("VIP_INFO_RE");
            Li("mMemberInfo----Uni---" + mMemberInfo);
            mCardNo = mIntent.getStringExtra(MyApplication.CARD_NO);//卡号
            mOrderNo = mIntent.getStringExtra(MyApplication.ORDER_NO);//订单号
            mOrderMoney = mIntent.getDoubleExtra("ORDER_MONEY", 0.00);//订单金额
            mNotCouponMoney = mOrderMoney;

            mGetPoints = mIntent.getDoubleExtra("POINT", 0.00);//获得积分
            mDiscountActivityGid = mIntent.getStringExtra("CC_GID");
            mStaffListGid = mIntent.getStringArrayListExtra("EM_GIDList");
            mMessage = mIntent.getBooleanExtra("message", false);
            mPrint = mIntent.getBooleanExtra("print", false);
            remark = mIntent.getStringExtra("remark");
            OrderTime = mIntent.getStringExtra("OrderTime");
            switch (mPageFlag) {
                case "YJJY":
                    mReceivableAmount = mIntent.getDoubleExtra("MONEY", 0.00);
                    Bundle bundle = getIntent().getBundleExtra("bundle");
                    ArrayList list = bundle.getParcelableArrayList("list");
                    mYJJYBeanList = (List<OilListBean.DataBean>) list.get(0);
                    break;
                case "KSXF":
                    mStaffListGid = mIntent.getStringArrayListExtra("EM_GIDList");
                    mAvailableMoney = mIntent.getDoubleExtra("MONEY", 0.00);
                    mPriceTotal = mIntent.getDoubleExtra("MONEY", 0.00);
                    mReceivableAmount = mIntent.getDoubleExtra("ORDER_MONEY", 0.00);
                    mMemberPoint = mIntent.getDoubleExtra("point", 0);
                    mNotCouponMoney = mIntent.getDoubleExtra("NotCouponMoney", 0.00);
                    mTotalMoney = mIntent.getDoubleExtra("CO_Monetary", 0);
                    mCouponMoney = mIntent.getDoubleExtra("CouponMoney", 0.00);
                    ksxfmDiscountMoney = mIntent.getDoubleExtra("ksxfmDiscountMoney", 0);
                    extra_ksxf_discount = mIntent.getStringExtra("extra_ksxf_discount");
                    Li(extra_ksxf_discount + "   mPriceTotal:" + mPriceTotal + "   " + mReceivableAmount + "-----KSXF-------initVariable-random:" + mTotalMoney + "   mNotCouponMoney:" + mNotCouponMoney);
                    mYHType = 1;
                    break;
                case "SPXF":
                    mStaffListGidList = (Map<String, ArrayList<String>>) mIntent.getSerializableExtra("EM_GIDList");
                    mReceivableAmount = mIntent.getDoubleExtra("MONEY", 0.00);
                    mNotCouponMoney = mIntent.getDoubleExtra("NotCouponMoney", 0.00);
                    mCouponMoney = mIntent.getDoubleExtra("CouponMoney", 0.00);
                    bundle = getIntent().getBundleExtra("bundle");
                    list = bundle.getParcelableArrayList("list");
                    mGoodeBeanList = (List<GoodsCheckResponseByType.DataBean.DataListBean>) list.get(0);
                    mYHType = 1;
                    Li(mReceivableAmount + "--SPXF---------initVariable-random:" + mTotalMoney + "    mCouponMoney:" + mCouponMoney);
                    break;
                case "HYCZ":
                    mStaffListGid = mIntent.getStringArrayListExtra("EM_GIDList");
                    findViewById(R.id.ll_union_pay_yhq).setVisibility(View.GONE);
                    findViewById(R.id.ll_wipe_zero).setVisibility(View.GONE);
                    mRechargeMoney = mIntent.getDoubleExtra("RECHARGE", 0);
                    mGiveMoney = mIntent.getDoubleExtra("GIVE", 0);
                    mRechargeTotal = mIntent.getDoubleExtra("TOTAL", 0);
                    mReceivableAmount = mRechargeMoney;
                    mYHType = 2;
                    break;
                case "HYCC":
                    mStaffListGidList = (Map<String, ArrayList<String>>) mIntent.getSerializableExtra("EM_GIDList");
                    mReceivableAmount = mIntent.getDoubleExtra("MONEY", 0.00);
                    bundle = getIntent().getBundleExtra("bundle");
                    list = bundle.getParcelableArrayList("list");
                    mGoodeBeanList = (List<GoodsCheckResponseByType.DataBean.DataListBean>) list.get(0);
                    mYHType = 1;
//                    mReceivableAmount = mIntent.getDoubleExtra("MONEY", 0.00);
//                    mSG_GID = mIntent.getStringExtra("SG_GID");
//                    mSG_Name = mIntent.getStringExtra("SG_Name");
//                    mMC_Number = mIntent.getIntExtra("MC_Number", 0);
//                    mYHType = 1;
                    break;
            }
            if (mMemberInfo != null) {
                mOrderType = 1;
                mAvailableMoney = mMemberInfo.getMA_AvailableBalance();//可用余额
                mMemberPoint = mMemberInfo.getMA_AvailableIntegral();//可用积分
            } else {
                mOrderType = 2;
                mAvailableMoney = 0;
                mMemberPoint = 0;
            }
            if ("HYYQ".equals(mPageFlag)) {
                mStaffListGid = mIntent.getStringArrayListExtra("EM_GIDList");
//                findViewById(R.id.ll_union_pay_yhq).setVisibility(View.GONE);
                mAvailableMoney = mIntent.getDoubleExtra("balance", 0);
                mMemberPoint = mIntent.getDoubleExtra("point", 0);
                mAvailableMoney = mIntent.getDoubleExtra("MONEY", 0.00);
                mReceivableAmount = mOrderMoney;
                mDelayTime = mIntent.getStringExtra("Months");
                mDelayType = mIntent.getStringExtra("TimesTyepe");
                mRemark = mIntent.getStringExtra("remark");
                mMIA_SurplusTimes = mIntent.getStringExtra("MIA_SurplusTimes");
            }
        }
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        //更新可抵扣金额UI
                        tvJifen.setText(Decima2KeeplUtil.stringToDecimal(mDeductibleMoney + ""));
                        break;
                    case 2:
                        //更新找零金额
                        tvZhaoLing.setText(Decima2KeeplUtil.stringToDecimal(mZLMoney + ""));


                        Li(mZLMoney + "=========mHandler======123======== random:" + mReceivableAmount + "　　mZeroMoney:" + mZeroMoney);

                        double flagMoney = 0;
                        if (mZLMoney < 0) {
                            flagMoney = mReceivableAmount + flagMoney;
                        } else {
                            flagMoney = mReceivableAmount - flagMoney;
                        }
                        Li("=========mHandler====flagMoney========== random:" + flagMoney);
                        /** 如果有抹零和优惠券就计算*/
                        if (!etYHQ.getText().toString().equals("")) {
                            flagMoney = flagMoney - Double.parseDouble(etYHQ.getText().toString());
                            Li("=========mHandler====111========== random:" + flagMoney);
                            tv_order_money.setText(Decima2KeeplUtil.stringToDecimal(flagMoney + ""));
                        }
//                        if (!etpaymoling.getText().toString().equals("")) {
//                            flagMoney = flagMoney - Double.parseDouble(etpaymoling.getText().toString());
////                                Li("=========mHandler====222========== random:" + flagMoney);
//                            tv_order_money.setText(Decima2KeeplUtil.stringToDecimal(flagMoney + ""));
//                        }
                        //自由抹零
                        if (mZeroType == 0) {
                            if (!etpaymoling.getText().toString().equals("")) {
                                flagMoney = flagMoney - Double.parseDouble(etpaymoling.getText().toString());
                                Li("=========mHandler====222========== random:" + flagMoney);
                                tv_order_money.setText(Decima2KeeplUtil.stringToDecimal(flagMoney + ""));
                            }
                        }
                        if (mZeroType > 0) {
                            initZero();
                            Li(flagMoney + "=========mHandler====mYhqBool========== random:" + mYhqBool + "　　mZeroMoney:" + mZeroMoney);
                            if (mYhqBool) {
                                etpaymoling.setText(mZeroMoney + "");
                                mYhqBool = false;
                            }
                            tv_order_money.setText(Decima2KeeplUtil.stringToDecimal((flagMoney - mZeroMoney) + ""));
                            if (mZeroType == 3 && mPageFlag.equals("HYCC") && tvZhaoLing.getText().toString().equals("0.00")) {
                                Li(Double.parseDouble(etpaymoling.getText().toString())+"=========mHandler====aaaaa========== random:" + flagMoney);
                                Li(mDiscountActivityGid+"=========mHandler====aaaaa========== random:" + flagMoney);
                                if (mDiscountActivityGid != null && !mDiscountActivityGid.equals("")) {
                                    Li("=========mHandler====222=====666===== random:" + flagMoney);
                                    tv_order_money.setText(Decima2KeeplUtil.stringToDecimal((flagMoney - Double.parseDouble(etpaymoling.getText().toString())) + ""));
                                }
                            }
                        }

                        /***************************/
//                        tv_order_money.setText(Decima2KeeplUtil.stringToDecimal(flagMoney + ""));
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 77 && resultCode == 77) {
                mCheckedList = (List<YHQBean.DataBean>) data.getSerializableExtra("YHQ");

                double flagMoney = mReceivableAmount;
                double newMoney = mNotCouponMoney;

                if (mCheckedList != null && mCheckedList.size() > 0) {
                    Li("============newMoney=========== random:" + newMoney);
                    mYH_Money = 0.00;
                    mYH_GID = new String[mCheckedList.size()];
                    for (int i = 0; i < mYH_GID.length; i++) {
                        mYH_GID[i] = mCheckedList.get(i).getGID();

                        if (mCheckedList.get(i).getEC_DiscountType() == 2) {
//                            mYH_Money += (100 - mCheckedList.get(i).getEC_Discount()) * mCheckedList.get(i).getOrderMoney() / 100;
                            newMoney = newMoney * (mCheckedList.get(i).getEC_Discount() / 100);
                            Li(i + "　a　" + newMoney + "============mYH_Money=========== random:" + mCheckedList.get(i).getEC_Discount() / 100);
                        } else {
//                            mYH_Money += mCheckedList.get(i).getEC_Discount();
                            newMoney = newMoney - mCheckedList.get(i).getEC_Discount();
                        }
                    }
                    mYH_Money = mNotCouponMoney - newMoney;
                    etYHQ.setText(Decima2KeeplUtil.stringToDecimal(mYH_Money + ""));

                    flagMoney = flagMoney - mYH_Money;

                } else {
                    etYHQ.setText("");
                    mYH_GID = null;
                }
                //自由抹零
                if (mZeroType == 0) {
                    if (!etpaymoling.getText().toString().equals("")) {
                        flagMoney = flagMoney - Double.parseDouble(etpaymoling.getText().toString());
                        tv_order_money.setText(Decima2KeeplUtil.stringToDecimal(flagMoney + ""));
                    }
                }
                if (mZeroType > 0) {
                    initZero();
                    etpaymoling.setText(mZeroMoney + "");
                    tv_order_money.setText(Decima2KeeplUtil.stringToDecimal((flagMoney - mZeroMoney) + ""));
                }

                tv_order_money.setText(Decima2KeeplUtil.stringToDecimal(flagMoney + ""));
                mYhqBool = true;
            }
        }
    }

    /**
     * 设置监听
     */
    private void setListener() {
        //返回
        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
//        tvYue.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mBalanceMoney)));
        tvYue.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mAvailableMoney)));
        tvJifen.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mDeductibleMoney)));
        tvPay.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mReceivableAmount)));
        Li("============newMoney=====11111====== random:" + mReceivableAmount);
        tv_order_money.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mReceivableAmount)));
        //设置抹零金额
        double result = 0;
        if (!"HYCZ".equals(mPageFlag)) {
            //没有设置抹零
            if (mZeroType == -1) {
                etpaymoling.setText("未开通抹零设置");
                etpaymoling.setText("");
                etpaymoling.setEnabled(false);
            } else if (mZeroType == 0) {//自由抹零
                etpaymoling.setEnabled(true);
            } else if (mZeroType == 1) {//四舍五入到元
                etpaymoling.setEnabled(false);
//                result = DoubleMathUtil.sub(mReceivableAmount, Double.parseDouble(String.format("%.0f", mReceivableAmount)));
                result = DoubleMathUtil.sub(mReceivableAmount, DoubleMathUtil.round(mReceivableAmount,0));
                etpaymoling.setText(result + "");
                double moneyFlag = mReceivableAmount - result;
                tv_order_money.setText(Decima2KeeplUtil.stringToDecimal(moneyFlag + ""));
            } else if (mZeroType == 2) {//四舍五入到角
                etpaymoling.setEnabled(false);
//                result = DoubleMathUtil.sub(mReceivableAmount, Double.parseDouble(String.format("%.1f", mReceivableAmount)));
                result = DoubleMathUtil.sub(mReceivableAmount, DoubleMathUtil.round(mReceivableAmount,1));
                etpaymoling.setText(result + "");
                double moneyFlag = mReceivableAmount - result;
                tv_order_money.setText(Decima2KeeplUtil.stringToDecimal(moneyFlag + ""));
            } else if (mZeroType == 3) {//直接舍弃角
                etpaymoling.setEnabled(false);
                result = DoubleMathUtil.sub(mReceivableAmount, (int) (mReceivableAmount / 1));
                etpaymoling.setText(result + "");
                double moneyFlag = mReceivableAmount - result;
                tv_order_money.setText(Decima2KeeplUtil.stringToDecimal(moneyFlag + ""));
            } else if (mZeroType == 4) {//直接舍弃分
                etpaymoling.setEnabled(false);
                String sMoney = tvPay.getText().toString();
                result = DoubleMathUtil.sub(mReceivableAmount, Double.parseDouble(sMoney.substring(0, sMoney.length() - 1)));
                etpaymoling.setText(result + "");
                double moneyFlag = mReceivableAmount - result;
                tv_order_money.setText(Decima2KeeplUtil.stringToDecimal(moneyFlag + ""));
            }
        }

        moren(DoubleMathUtil.sub(mReceivableAmount, result));


        if ("HYCZ".equals(mPageFlag)) {
            etPayYue.setText("");
            etPayYue.setEnabled(false);
//            etPayJiFen.setEnabled(false);
            etPayYue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CustomToast.makeText(UnionPayActivity.this, "会员充值不能使用余额", Toast.LENGTH_SHORT).show();
                }
            });
        }
        //使用优惠券
        tvYHQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mYhqSwitch) {
                    Intent intent = new Intent(UnionPayActivity.this, YouHuiQuanActivity.class);

                    intent.putExtra("VIP", mCardNo);
//                    intent.putExtra("orderMoney", mReceivableAmount);
                    intent.putExtra("orderMoney", mNotCouponMoney);
                    intent.putExtra("mCheckedList", (Serializable) mCheckedList);
                    startActivityForResult(intent, 77);
                } else {
                    CustomToast.makeText(UnionPayActivity.this, "未开启优惠券支付开关！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rl_check_yhq_legal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UnionPayActivity.this, YouHuiQuanActivity.class);

                intent.putExtra("VIP", mCardNo);
//                intent.putExtra("orderMoney", mReceivableAmount);
                intent.putExtra("orderMoney", mNotCouponMoney);
                intent.putExtra("mCheckedList", (Serializable) mCheckedList);
                startActivityForResult(intent, 77);
            }
        });

        etpaymoling.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                if (mZeroType != 0) {
//                    return;
//                }
                double payml = 0;
                double payPoint = 0L;
                double payCash = 0L;
                double payYue = 0L;
                double payCard = 0;
                double payWx = 0;
                double payZfb = 0;
                double yh = 0;
                if (!TextUtils.isEmpty(s)) {
                    if (s.toString().equals(".")) {
                        etpaymoling.setText("");
                        CustomToast.makeText(UnionPayActivity.this, "请输入正确金额", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    payml = Double.parseDouble(s.toString());

                }

                if (!String.valueOf(etPayYue.getText()).isEmpty()) {
                    payYue = Double.parseDouble(etPayYue.getText().toString());
                }
                if (!String.valueOf(etPayJiFen.getText()).isEmpty()) {
                    payPoint = Double.parseDouble(etPayJiFen.getText().toString());
                }
                if (!String.valueOf(etPayCard.getText()).isEmpty()) {
                    payCard = Double.parseDouble(etPayCard.getText().toString());
                }
                if (!String.valueOf(etWx.getText()).isEmpty()) {
                    payWx = Double.parseDouble(etWx.getText().toString());
                }
                if (!String.valueOf(etPayCash.getText()).isEmpty()) {
                    payCash = Double.parseDouble(etPayCash.getText().toString());
                }
                if (!String.valueOf(etZfb.getText()).isEmpty()) {
                    payZfb = Double.parseDouble(etZfb.getText().toString());
                }
                if (!String.valueOf(etYHQ.getText()).isEmpty()) {
                    yh = Double.parseDouble(etYHQ.getText().toString());
                }
                mSSMoney = payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh;
                mZLMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh))) - mReceivableAmount;

                mHandler.sendEmptyMessage(2);//通知主线程更新找零金额UI

            }
        });


        //现金支付输入框监听
        etPayCash.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double payml = 0;
                double payPoint = 0L;
                double payCash = 0L;
                double payYue = 0L;
                double payCard = 0;
                double payWx = 0;
                double payZfb = 0;
                double yh = 0;
                if (!TextUtils.isEmpty(editable)) {
                    if (editable.toString().equals(".")) {
                        etPayCash.setText("");
                        CustomToast.makeText(UnionPayActivity.this, "请输入正确金额", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    payCash = Double.parseDouble(editable.toString());

                }
                if (!etpaymoling.getText().toString().equals("")) {
                    payml = Double.parseDouble(etpaymoling.getText().toString());
                } else {
                    payml = 0;
                }
                if (!String.valueOf(etPayYue.getText()).isEmpty()) {
                    payYue = Double.parseDouble(etPayYue.getText().toString());
                }
                if (!String.valueOf(etPayJiFen.getText()).isEmpty()) {
                    payPoint = Double.parseDouble(etPayJiFen.getText().toString());
                }
                if (!String.valueOf(etPayCard.getText()).isEmpty()) {
                    payCard = Double.parseDouble(etPayCard.getText().toString());
                }
                if (!String.valueOf(etWx.getText()).isEmpty()) {
                    payWx = Double.parseDouble(etWx.getText().toString());
                }
                if (!String.valueOf(etZfb.getText()).isEmpty()) {
                    payZfb = Double.parseDouble(etZfb.getText().toString());
                }
                if (!String.valueOf(etYHQ.getText()).isEmpty()) {
                    yh = Double.parseDouble(etYHQ.getText().toString());
                }
                mSSMoney = payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh;
                mZLMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh))) - mReceivableAmount;

                mHandler.sendEmptyMessage(2);//通知主线程更新找零金额UI
            }
        });
        //银联支付
        etPayCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double payml = 0;
                double payPoint = 0;
                double payCash = 0;
                double payYue = 0;
                double payCard = 0;
                double payWx = 0;
                double payZfb = 0;
                double yh = 0;
                if (!TextUtils.isEmpty(editable)) {
                    if (editable.toString().equals(".")) {
                        etPayCard.setText("");
                        CustomToast.makeText(UnionPayActivity.this, "请输入正确金额", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    payCard = Double.parseDouble(editable.toString());
                }
                if (!String.valueOf(etpaymoling.getText()).isEmpty()) {
                    payml = Double.parseDouble(etpaymoling.getText().toString());
                }
                if (!String.valueOf(etPayYue.getText()).isEmpty()) {
                    payYue = Double.parseDouble(etPayYue.getText().toString());
                }
                if (!String.valueOf(etPayJiFen.getText()).isEmpty()) {
                    payPoint = Double.parseDouble(etPayJiFen.getText().toString());
                }
                if (!String.valueOf(etPayCash.getText()).isEmpty()) {
                    payCash = Double.parseDouble(etPayCash.getText().toString());
                }
                if (!String.valueOf(etWx.getText()).isEmpty()) {
                    payWx = Double.parseDouble(etWx.getText().toString());
                }
                if (!String.valueOf(etZfb.getText()).isEmpty()) {
                    payZfb = Double.parseDouble(etZfb.getText().toString());
                }
                if (!String.valueOf(etYHQ.getText()).isEmpty()) {
                    yh = Double.parseDouble(etYHQ.getText().toString());
                }

                mSSMoney = payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh;
                mZLMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh))) - mReceivableAmount;

                mHandler.sendEmptyMessage(2);//通知主线程更新找零金额UI
            }
        });

        final int[] num = {0};
        //监听软键盘的删除键
        etPayCard.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    num[0]++;
                    //在这里加判断的原因是点击一次软键盘的删除键,会触发两次回调事件
                    if (num[0] % 2 != 0) {
                        String s = etPayCard.getText().toString();
                        if (!TextUtils.isEmpty(s)) {
                            etPayCard.setText("" + s.substring(0, s.length() - 1));
                            //将光标移到最后
                            etPayCard.setSelection(etPayCard.getText().length());
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        //余额支付输入框监听
        etPayYue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double payml = 0;
                double payPoint = 0L;
                double payCash = 0L;
                double payYue = 0L;
                double payCard = 0;
                double payWx = 0;
                double payZfb = 0;
                double yh = 0;
                if (!TextUtils.isEmpty(editable)) {

                    if (mMemberInfo == null) {
                        etPayYue.setText("");
                        CustomToast.makeText(UnionPayActivity.this, "非会员不能使用余额支付", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (editable.toString().equals(".")) {
                        etPayYue.setText("");
                        CustomToast.makeText(UnionPayActivity.this, "请输入正确金额", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    payYue = Double.parseDouble(editable.toString());
                    if (payYue > mAvailableMoney) {
                        hintDialog("你的余额不足");
                        etPayYue.setText("");
                        payYue = 0;
                    }
//                    if (payYue > mBalanceMoney){
                    if (payYue > mReceivableAmount * mPercentMoney) {
//                        hintDialog("超出可抵扣余额限制");
                        Li("======================= random:" + mPercentMoney);
                        hintDialog("            你设置的余额比例\n余额支付不能大于该单金额" + (int) (mPercentMoney * 100) + "%");
                        etPayYue.setText("");
                        payYue = 0;
                    }
                }
                if (!String.valueOf(etpaymoling.getText()).isEmpty()) {
                    payml = Double.parseDouble(etpaymoling.getText().toString());
                }
                if (!String.valueOf(etPayCash.getText()).isEmpty()) {
                    payCash = Double.parseDouble(etPayCash.getText().toString());
                }
                if (!String.valueOf(etPayJiFen.getText()).isEmpty()) {
                    payPoint = Double.parseDouble(etPayJiFen.getText().toString());
                }
                if (!String.valueOf(etPayCard.getText()).isEmpty()) {
                    payCard = Double.parseDouble(etPayCard.getText().toString());
                }
                if (!String.valueOf(etWx.getText()).isEmpty()) {
                    payWx = Double.parseDouble(etWx.getText().toString());
                }
                if (!String.valueOf(etZfb.getText()).isEmpty()) {
                    payZfb = Double.parseDouble(etZfb.getText().toString());
                }
                if (!String.valueOf(etYHQ.getText()).isEmpty()) {
                    yh = Double.parseDouble(etYHQ.getText().toString());
                }
                mSSMoney = payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh;
                mZLMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh))) - mReceivableAmount;


                mHandler.sendEmptyMessage(2);//通知主线程更新找零金额UI

            }
        });
        //积分输入框监听
        etPayJiFen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double payml = 0;
                double payPoint = 0;
                double payCash = 0;
                double payYue = 0;
                double payCard = 0;
                double payWx = 0;
                double payZfb = 0;
                double yh = 0;
                if (!TextUtils.isEmpty(editable)) {
                    if (editable.toString().equals(".")) {
                        etPayJiFen.setText("");
                        CustomToast.makeText(UnionPayActivity.this, "请输入正确金额", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    payPoint = Double.parseDouble(editable.toString());
                }
                if (payPoint > mDeductibleMoney) {
                    hintDialog("你的可抵扣积分不足");
                    etPayJiFen.setText("");
                } else {
                    if (!String.valueOf(etPayCash.getText()).isEmpty()) {
                        payCash = Double.parseDouble(etPayCash.getText().toString());
                    }
                    if (!String.valueOf(etPayYue.getText()).isEmpty()) {
                        payYue = Double.parseDouble(etPayYue.getText().toString());
                    }
                    if (!String.valueOf(etPayCard.getText()).isEmpty()) {
                        payCard = Double.parseDouble(etPayCard.getText().toString());
                    }
                    if (!String.valueOf(etWx.getText()).isEmpty()) {
                        payWx = Double.parseDouble(etWx.getText().toString());
                    }
                    if (!String.valueOf(etZfb.getText()).isEmpty()) {
                        payZfb = Double.parseDouble(etZfb.getText().toString());
                    }
                    if (!String.valueOf(etYHQ.getText()).isEmpty()) {
                        yh = Double.parseDouble(etYHQ.getText().toString());
                    }
                    if (!String.valueOf(etpaymoling.getText()).isEmpty()) {
                        payml = Double.parseDouble(etpaymoling.getText().toString());
                    }
                    mPointSpend = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(payPoint * mPointPercent + ""));
                    mSSMoney = payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh;
                    mZLMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh))) - mReceivableAmount;
                    mPayPointMoney = payPoint;
                    mHandler.sendEmptyMessage(2);//通知主线程更新找零金额UI
                }

            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (YSLUtils.isFastClick()) {
                    double payml = 0;
                    if (!etpaymoling.getText().toString().isEmpty()) {
                        payml = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etpaymoling.getText().toString()));
                    }
                    double payCash = 0;
                    if (!etPayCash.getText().toString().isEmpty()) {
                        payCash = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etPayCash.getText().toString()));
                    }
                    double payCard = 0;
                    if (!etPayCard.getText().toString().isEmpty()) {
                        payCard = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etPayCard.getText().toString()));
                    }
                    double payYue = 0;
                    if (!etPayYue.getText().toString().isEmpty()) {
                        payYue = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etPayYue.getText().toString()));
                        System.out.println("===============11111111 random:" + payYue);
                        if (payYue > mReceivableAmount * mPercentMoney) {
//                        hintDialog("超出可抵扣余额限制");
                            System.out.println("===========random:" + mPercentMoney);
                            hintDialog("超出可抵扣余额限制,最多可抵扣" + mReceivableAmount * mPercentMoney + "元");
                            etPayYue.setText("");
                            payYue = 0;
                            return;
                        }
                    }
                    double payPoint = 0;
                    if (!etPayJiFen.getText().toString().isEmpty()) {
                        payPoint = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etPayJiFen.getText().toString()));
                    }
                    double zhaolin = 0;
                    if (!tvZhaoLing.getText().toString().isEmpty()) {
                        zhaolin = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(tvZhaoLing.getText().toString()));
                    }
                    double payWx = 0;
                    if (!TextUtils.isEmpty(etWx.getText())) {
                        payWx = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etWx.getText().toString()));
                    }
                    double payZfb = 0;
                    if (!TextUtils.isEmpty(etZfb.getText())) {
                        payZfb = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etZfb.getText().toString()));
                    }
                    double payYh = 0;
                    if (!TextUtils.isEmpty(etYHQ.getText())) {
                        payYh = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etYHQ.getText().toString()));
                    }
                    double ssmoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(payml + payCash + payCard + payYue + payPoint + payWx + payZfb + payYh + ""));
                    if (ssmoney >= mReceivableAmount && (payml != 0 || payCash != 0 || payCard != 0 || payPoint != 0 || payYue != 0 || payWx != 0 || payZfb != 0 || payYh != 0) && zhaolin >= 0) {
                        double sss;
                        if (etPayCash.getText().toString().isEmpty()) {
                            sss = 0;
                        } else {
                            sss = Double.parseDouble(etPayCash.getText().toString());
                        }
                        if ((sss == 0 && mZLMoney == 0) || mZLMoney < sss) {
                            switch (mPageFlag) {
                                case "KJSK":
                                    if (mPasswordSwitch && (!etPayYue.getText().toString().isEmpty() || !etPayJiFen.getText().toString().isEmpty() || !etYHQ.getText().toString().isEmpty())) {
                                        showPasswordDialog(1);
                                    } else {
                                        UnionFastPayStepOne();
                                    }
                                    break;
                                case "KSXF":
                                    if (mPasswordSwitch && (!etPayYue.getText().toString().isEmpty() || !etPayJiFen.getText().toString().isEmpty() || !etYHQ.getText().toString().isEmpty())) {
                                        showPasswordDialog(1);
                                    } else {
//                                    fastPay();
                                        UnionFastPayStepOne();
                                    }
                                    break;
                                case "SPXF":
                                    if (mPasswordSwitch && (!etPayJiFen.getText().toString().isEmpty() || !etPayYue.getText().toString().isEmpty() || !etYHQ.getText().toString().isEmpty())) {
                                        showPasswordDialog(2);
                                    } else {
//                                    goodsPay();
                                        unnionGoodsPayStepOne();
                                    }
                                    break;
                                case "HYCZ":
//                                chargeMoney();
                                    UnionVipInflateMoneyStepOne();
                                    break;
                                case "HYCC":
                                    if (mPasswordSwitch && (!etPayYue.getText().toString().isEmpty() || !etPayJiFen.getText().toString().isEmpty() || !etYHQ.getText().toString().isEmpty())) {
                                        showPasswordDialog(3);
                                    } else {
//                                    chargeTimePay();
                                        UnionInflateCountStepOne();
                                    }
                                    break;
                                case "YJJY":
                                    if (mPasswordSwitch && (!etPayYue.getText().toString().isEmpty() || !etPayJiFen.getText().toString().isEmpty() || !etYHQ.getText().toString().isEmpty())) {
                                        showPasswordDialog(4);
                                    } else {
//                                    goodsPay();
                                        unnionOneKeyPayStepOne();
                                    }
                                    break;
                                /**9364会员延期，输入费用，联合支付，点击确认收款没有反应 20191028 cy */
                                case "HYYQ":
                                    if (mPasswordSwitch && (!etPayYue.getText().toString().isEmpty() || !etPayJiFen.getText().toString().isEmpty() || !etYHQ.getText().toString().isEmpty())) {
                                        showPasswordDialog(5);
                                    } else {
                                        memberDelay();
                                    }
                                    break;
                                /******************************************************** 20191028 cy */
                            }
                        } else {
                            hintDialog("找零金额不能大于等于实收金额");
                        }
                    } else {
                        hintDialog("支付金额不足");
                    }
                }

            }
        });
        //微信记账输入框监听
        etWx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double payml = 0;
                double payPoint = 0L;
                double payCash = 0L;
                double payYue = 0L;
                double payCard = 0;
                double payWx = 0;
                double payZfb = 0;
                double yh = 0;
                if (!TextUtils.isEmpty(editable)) {
                    if (editable.toString().equals(".")) {
                        etWx.setText("");
                        CustomToast.makeText(UnionPayActivity.this, "请输入正确金额", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    payWx = Double.parseDouble(editable.toString());

                }
                if (!String.valueOf(etPayYue.getText()).isEmpty()) {
                    payYue = Double.parseDouble(etPayYue.getText().toString());
                }
                if (!String.valueOf(etPayJiFen.getText()).isEmpty()) {
                    payPoint = Double.parseDouble(etPayJiFen.getText().toString());
                }
                if (!String.valueOf(etPayCard.getText()).isEmpty()) {
                    payCard = Double.parseDouble(etPayCard.getText().toString());
                }
                if (!String.valueOf(etpaymoling.getText()).isEmpty()) {
                    payml = Double.parseDouble(etpaymoling.getText().toString());
                }
                if (!String.valueOf(etPayCash.getText()).isEmpty()) {
                    payCash = Double.parseDouble(etPayCash.getText().toString());
                }
                if (!String.valueOf(etZfb.getText()).isEmpty()) {
                    payZfb = Double.parseDouble(etZfb.getText().toString());
                }
                if (!String.valueOf(etYHQ.getText()).isEmpty()) {
                    yh = Double.parseDouble(etYHQ.getText().toString());
                }
                mSSMoney = payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh;
                mZLMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh))) - mReceivableAmount;

                mHandler.sendEmptyMessage(2);//通知主线程更新找零金额UI
            }
        });
        //支付宝记账输入框监听
        etZfb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                double payml = 0;
                double payPoint = 0L;
                double payCash = 0L;
                double payYue = 0L;
                double payCard = 0;
                double payWx = 0;
                double payZfb = 0;
                double yh = 0;
                if (!TextUtils.isEmpty(editable)) {
                    if (editable.toString().equals(".")) {
                        etZfb.setText("");
                        CustomToast.makeText(UnionPayActivity.this, "请输入正确金额", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    payZfb = Double.parseDouble(editable.toString());

                }
                if (!String.valueOf(etPayYue.getText()).isEmpty()) {
                    payYue = Double.parseDouble(etPayYue.getText().toString());
                }
                if (!String.valueOf(etPayJiFen.getText()).isEmpty()) {
                    payPoint = Double.parseDouble(etPayJiFen.getText().toString());
                }
                if (!String.valueOf(etPayCard.getText()).isEmpty()) {
                    payCard = Double.parseDouble(etPayCard.getText().toString());
                }
                if (!String.valueOf(etpaymoling.getText()).isEmpty()) {
                    payml = Double.parseDouble(etpaymoling.getText().toString());
                }
                if (!String.valueOf(etPayCash.getText()).isEmpty()) {
                    payCash = Double.parseDouble(etPayCash.getText().toString());
                }
                if (!String.valueOf(etWx.getText()).isEmpty()) {
                    payWx = Double.parseDouble(etWx.getText().toString());
                }
                if (!String.valueOf(etYHQ.getText()).isEmpty()) {
                    yh = Double.parseDouble(etYHQ.getText().toString());
                }
                mSSMoney = payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh;
                mZLMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh))) - mReceivableAmount;

                mHandler.sendEmptyMessage(2);//通知主线程更新找零金额UI
            }
        });
        etYHQ.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                double payml = 0;
                double payPoint = 0L;
                double payCash = 0L;
                double payYue = 0L;
                double payCard = 0;
                double payWx = 0;
                double payZfb = 0;
                double yh = 0;
                if (!TextUtils.isEmpty(s)) {
                    yh = Double.parseDouble(s.toString());

                }
                if (!String.valueOf(etpaymoling.getText()).isEmpty()) {
                    payml = Double.parseDouble(etpaymoling.getText().toString());
                }
                if (!String.valueOf(etPayYue.getText()).isEmpty()) {
                    payYue = Double.parseDouble(etPayYue.getText().toString());
                }
                if (!String.valueOf(etPayJiFen.getText()).isEmpty()) {
                    payPoint = Double.parseDouble(etPayJiFen.getText().toString());
                }
                if (!String.valueOf(etPayCard.getText()).isEmpty()) {
                    payCard = Double.parseDouble(etPayCard.getText().toString());
                }
                if (!String.valueOf(etPayCash.getText()).isEmpty()) {
                    payCash = Double.parseDouble(etPayCash.getText().toString());
                }
                if (!String.valueOf(etWx.getText()).isEmpty()) {
                    payWx = Double.parseDouble(etWx.getText().toString());
                }
                if (!String.valueOf(etZfb.getText()).isEmpty()) {
                    payZfb = Double.parseDouble(etZfb.getText().toString());
                }
                mSSMoney = payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh;
                mZLMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(payml + payCash + payPoint + payYue + payCard + payZfb + payWx + yh))) - mReceivableAmount;

                mHandler.sendEmptyMessage(2);//通知主线程更新找零金额UI
            }
        });

        if (moren == null) {
            tvZhaoLing.setText("-" + tv_order_money.getText().toString());
        }
    }

//    /**
//     * 检查优惠券
//     *
//     * @param s
//     */
//    private void checkYHQ(String s, int type) {
//        RequestParams params = new RequestParams();
//        params.put("Code", s);
//        params.put("Money", tvPay.getText().toString());
//        params.put("Type", type);
//        HttpHelper.post(this, "CouponManager/QueryCouponsByCode", params, new CallBack() {
//            @Override
//            public void onSuccess(String responseString, Gson gson) {
//                mYhqBean = CommonFun.JsonToObj(responseString, YHQBean.class);
//                rlYhMoney.setVisibility(View.VISIBLE);
//                etYhMoney.setText(mYhqBean.getData().getEC_Discount() + "");
//                mYH_GID = mYhqBean.getData().getGID();
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                CustomToast.makeText(UnionPayActivity.this, msg, Toast.LENGTH_SHORT).show();
//                etYhMoney.setText("");
//                rlYhMoney.setVisibility(View.VISIBLE);
//            }
//        });
//    }


    /**
     * 显示警告对话框
     *
     * @param msg
     */
    private void hintDialog(String msg) {
        mSweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setCanceledOnTouchOutside(true);
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setConfirmText("了解");
        mSweetAlertDialog.show();
    }

    //设置默认支付
    private void moren(double yfMoney) {
        if (moren != null) {
            if (moren.equals("XJZF")) {
                etPayCash.setText(yfMoney + "");
            } else if (moren.equals("YLZF")) {
                etPayCard.setText(yfMoney + "");
            } else if (moren.equals("YEZF")) {
                if (mMemberInfo != null) {
                    etPayYue.setText(yfMoney + "");
                }
            } else if (moren.equals("JFZF")) {
                etPayJiFen.setText(yfMoney + "");
            } else if (moren.equals("WX_JZ")) {
                etWx.setText(yfMoney + "");
            } else if (moren.equals("ZFB_JZ")) {
                etZfb.setText(yfMoney + "");
            } else if (moren.equals("YHJZF")) {
                etYHQ.setText(yfMoney + "");
            }
        }
    }


    /**
     * 支付方式参数
     */
    private void payWay() {
//        if (!etpaymoling.getText().toString().isEmpty()) {
//            mPayWayBean = new PayWayBean();
//            mPayWayBean.setPayName("抹零记账");
//            mPayWayBean.setPayCode("EZJZ");
//            mPayWayBean.setPayMoney(Decima2KeeplUtil.stringToDecimal(etpaymoling.getText().toString()));
//            mPayWayBeanList.add(mPayWayBean);
//        }

        if (!etPayCash.getText().toString().isEmpty()) {
            mPayWayBean = new PayWayBean();
            mPayWayBean.setPayName("现金支付");
            mPayWayBean.setPayCode("XJZF");

            double x = Double.parseDouble(etPayCash.getText().toString()) - Double.parseDouble(tvZhaoLing.getText().toString().equals("") ? "0" : tvZhaoLing.getText().toString());
            mPayWayBean.setPayMoney(Decima2KeeplUtil.stringToDecimal(x + ""));
            mPayWayBeanList.add(mPayWayBean);
        }
        if (!etPayCard.getText().toString().isEmpty()) {
            mPayWayBean = new PayWayBean();
            mPayWayBean.setPayName("银联支付");
            mPayWayBean.setPayCode("YLZF");
            mPayWayBean.setPayMoney(Decima2KeeplUtil.stringToDecimal(etPayCard.getText().toString()));
            mPayWayBeanList.add(mPayWayBean);
        }
        if (!etPayYue.getText().toString().isEmpty()) {
            mPayWayBean = new PayWayBean();
            mPayWayBean.setPayName("余额支付");
            mPayWayBean.setPayCode("YEZF");
            mPayWayBean.setPayMoney(Decima2KeeplUtil.stringToDecimal(etPayYue.getText().toString()));
            mPayWayBeanList.add(mPayWayBean);
        }
        if (!etPayJiFen.getText().toString().isEmpty()) {
            mPayWayBean = new PayWayBean();
            mPayWayBean.setPayName("积分支付");
            mPayWayBean.setPayCode("JFZF");
            mPayWayBean.setPayMoney(Decima2KeeplUtil.stringToDecimal(etPayJiFen.getText().toString()));
            mPayWayBean.setPayPoint(mPayPointMoney * mPointPercent);
            mPayWayBeanList.add(mPayWayBean);
        }
        if (!TextUtils.isEmpty(etWx.getText())) {
            mPayWayBean = new PayWayBean();
            mPayWayBean.setPayName("微信记账");
            mPayWayBean.setPayCode("WX_JZ");
            mPayWayBean.setPayMoney(Decima2KeeplUtil.stringToDecimal(etWx.getText().toString()));
            mPayWayBeanList.add(mPayWayBean);
        }
        if (!TextUtils.isEmpty(etZfb.getText())) {
            mPayWayBean = new PayWayBean();
            mPayWayBean.setPayName("支付宝记账");
            mPayWayBean.setPayCode("ZFB_JZ");
            mPayWayBean.setPayMoney(Decima2KeeplUtil.stringToDecimal(etZfb.getText().toString()));
            mPayWayBeanList.add(mPayWayBean);
        }
//        if (!TextUtils.isEmpty(etYHQ.getText()) && !etYHQ.getText().toString().equals("")) {
//            mPayWayBean = new PayWayBean();
//            mPayWayBean.setPayName("优惠券");
//            mPayWayBean.setPayCode("YHJZF");
//            mPayWayBean.setGID(mYH_GID);
//            mPayWayBean.setPayMoney(Decima2KeeplUtil.stringToDecimal(etYHQ.getText().toString()));
//            mPayWayBeanList.add(mPayWayBean);
//        }
    }

    /**
     * 获取相关数据
     */
    private void initSp() {
        sp = getSharedPreferences("setting", MODE_PRIVATE);
        mMaccode = sp.getString("adress", "");//获取蓝牙地址
        mOperator = sp.getString("myuser", "");//获取操作人员
        mShopName = sp.getString("mytitle", "");//获取店铺名称
        pref = this.getSharedPreferences("recepits", Context.MODE_PRIVATE);
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {
                    if ("204".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mPasswordSwitch = true;
                        } else {
                            mPasswordSwitch = false;
                        }
                    }
                    if ("101".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() != 1) {
                            etPayCash.setHint("未开启支付");
                            etPayCash.setEnabled(false);
//                            etPayCash.setInputType(InputType.TYPE_NULL);
//                            etPayCash.setBackgroundColor(getResources().getColor(R.color.background));
                        }
                    }
                    if ("102".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() != 1) {
                            etPayYue.setHint("未开启支付");
                            etPayYue.setEnabled(false);
//                            etPayYue.setInputType(InputType.TYPE_NULL);
//                            etPayYue.setBackgroundColor(getResources().getColor(R.color.background));
                        }
                        if (mSwitchEntity.get(i).getSS_State() == 1 && mSwitchEntity.get(i).getSS_Value() != null) {
                            double a = Double.parseDouble(mSwitchEntity.get(i).getSS_Value() + "");
                            mPercentMoney = DoubleMathUtil.div(a, 100);
//                            mBalanceMoney =mAvailableMoney * b;
                        }
                    }
                    //积分支付
                    if ("107".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() != 1) {
                            etPayJiFen.setHint("未开启支付");
                            etPayJiFen.setEnabled(false);
//                            etPayJiFen.setInputType(InputType.TYPE_NULL);
//                            etPayJiFen.setBackgroundColor(getResources().getColor(R.color.background));
                        } else {
                            mPointPercent = Double.parseDouble(mSwitchEntity.get(i).getSS_Value() + "");
                            mDeductibleMoney = mMemberPoint / mPointPercent;
                            Li(mMemberPoint + " 会员--11111--random:" + mDeductibleMoney);
//                            mDeductibleMoney = Math.floor(mMemberPoint / mPointPercent);
                            mHandler.sendEmptyMessage(1);//发送消息通知主线程更新可抵扣金额UI
                        }
                    }
                    //积分支付限制
                    if ("109".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1 && mSwitchEntity.get(i).getSS_Value() != null) {
                            double a = Double.parseDouble(mSwitchEntity.get(i).getSS_Value() + "");
                            double b = DoubleMathUtil.div(a, 100);
                            mDeductibleMoney = DoubleMathUtil.mul(b, mDeductibleMoney);
                            Li(mMemberPoint + " 会员--2222--random:" + mDeductibleMoney);
                            mHandler.sendEmptyMessage(1);//发送消息通知主线程更新可抵扣金额UI
                        }
                    }
                    if ("103".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() != 1) {
                            etPayCard.setHint("未开启支付");
                            etPayCard.setEnabled(false);
//                            etPayCard.setInputType(InputType.TYPE_NULL);
//                            etPayCard.setBackgroundColor(getResources().getColor(R.color.background));
                        }
                    }
                    if ("105".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() != 1) {
                            etWx.setHint("未开启支付");
                            etWx.setEnabled(false);
//                            etWx.setInputType(InputType.TYPE_NULL);
//                            etWx.setBackgroundColor(getResources().getColor(R.color.background));
                        }
                    }
                    if ("106".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() != 1) {
                            etZfb.setHint("未开启支付");
                            etZfb.setEnabled(false);
//                            etZfb.setInputType(InputType.TYPE_NULL);
//                            etZfb.setBackgroundColor(getResources().getColor(R.color.background));
                        }
                    }
                    if ("108".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            moren = mSwitchEntity.get(i).getSS_Value();
                        } else {
                            tvZhaoLing.setText("-" + tv_order_money.getText().toString());
                        }
                    }

                    if ("110".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mYhqSwitch = true;
                        } else {
//                            etYHQ.setHint("未开启支付");
                            etYHQ.setEnabled(false);
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
                mPasswordSwitch = false;
                etPayJiFen.setInputType(InputType.TYPE_NULL);
            }
        }

    }


    /**
     * 联合支付，快速消费提交订单信息
     */
    private void UnionFastPayStepOne() {
        RequestParams params = new RequestParams();
        //会员卡号
        params.put("VIP_Card", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
        //订单编号
        params.put("CO_OrderCode", mOrderNo);
        //订单时间
        params.put("OrderTime", OrderTime);
        //应收金额
        params.put("CO_Monetary", Decima2KeeplUtil.stringToDecimal(mTotalMoney + ""));
        //折后金额(应收金额)
        if (mMemberInfo == null) {
            params.put("CO_TotalPrice", mPriceTotal);
            mKsxfFlagMoney = mPriceTotal;
            Li("=-------111--mPriceTotal----random:" + mPriceTotal + "   mOrderMoney:" + mOrderMoney + "　mPriceTotal：" + mPriceTotal);
        } else {
            if (extra_ksxf_discount != null && extra_ksxf_discount.equals("isdiscount")) {
                Li(tv_order_money.getText().toString() + "=-------2222--mPriceTotal----random:" + mPriceTotal + "   mOrderMoney:" + mOrderMoney + "　mPriceTotal：" + mPriceTotal);
                double flag = Double.parseDouble(tv_order_money.getText().toString());
                if (mDiscountActivityGid != null && !mDiscountActivityGid.equals("")) {
                    params.put("CO_TotalPrice", mPriceTotal);
                    mKsxfFlagMoney = mPriceTotal;
                } else {
                    if (mPriceTotal > mOrderMoney) {
//                        params.put("CO_TotalPrice", flag);
                        params.put("CO_TotalPrice", mReceivableAmount);
                        mKsxfFlagMoney = flag;
                    } else {
                        params.put("CO_TotalPrice", mPriceTotal);
                        mKsxfFlagMoney = mPriceTotal;
                    }
                }

            } else {
                Li("=-------333--mPriceTotal----random:" + mPriceTotal + "   mOrderMoney:" + mOrderMoney + "　mPriceTotal：" + mPriceTotal);
                params.put("CO_TotalPrice", (ksxfmDiscountMoney == 0.00 ? mPriceTotal : ksxfmDiscountMoney) + "");
            }
        }

        Li(mPriceTotal + "  mPriceTotal 订-------------random:" + mAvailableMoney + "   mOrderMoney:" + mOrderMoney + "　mPriceTotal：" + mPriceTotal);
        Li(mMemberInfo + "  mMemberInfo 订单金额: " + mReceivableAmount + " =-------------random:" + mAvailableMoney + "   mOrderMoney:" + mOrderMoney + "　mPriceTotal：" + mPriceTotal);
        //备注
        params.put("CO_Remark", remark);

//        params.put("CO_Integral", Decima2KeeplUtil.stringToDecimal(mGetPoints + ""));//可获得积分
//        params.put("OrderType", mOrderType);
//        params.put("CC_GID", mDiscountActivityGid);//优惠活动GID
//        params.put("DisMoney", Decima2KeeplUtil.stringToDecimal(mReceivableAmount + ""));//折后金额
//        params.put("ActivityValue", "抵扣0元");
//        if (mStaffListGid != null) {
//            for (int i = 0; i < mStaffListGid.size(); i++) {//提成员工GID
//                params.put("EM_GIDList[" + i + "]", mStaffListGid.get(i));
//            }
//        }
        Li(ksxfmDiscountMoney + "   订单金额: " + mOrderMoney + " 联合支付，快速消费提交订单信息---------------------random:" + params.toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                FastPayStepOneBean stepOneBean = CommonFun.JsonToObj(responseString, FastPayStepOneBean.class);
                mOrderGID = stepOneBean.getData().getGID();
                UnionFastPayStepTwo();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(UnionPayActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交商品订单", false);
        HttpHelper.post(this, HttpAPI.API().FAST_CONSUME_SUB, params, callBack);

    }

    /**
     * 联合支付 快速消费确认支付
     */
    private void UnionFastPayStepTwo() {
        RequestParams params = new RequestParams();
        //订单GID
        params.put("OrderGID", mOrderGID);
        //是否发送短信
        params.put("Smsg", mMessage ? 1 : 0);
        //应收金额
//        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(mReceivableAmount + ""));
        double moneyFlag = mReceivableAmount;
        /** 如果有抹零和优惠券就计算*/
        if (!etpaymoling.getText().toString().equals("")) {
            moneyFlag = mReceivableAmount - Double.parseDouble(etpaymoling.getText().toString());
        }
        if (!etYHQ.getText().toString().equals("")) {
            moneyFlag = moneyFlag - Double.parseDouble(etYHQ.getText().toString());
        }
        /***************************/
        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(moneyFlag + ""));
        //找零金额
        params.put("PayResult[GiveChange]", "0");
        //抹零金额
        String slipZero = etpaymoling.getText().toString();
        if (mZeroType == 4) {
            if (Decima2KeeplUtil.stringToDecimal(mKsxfFlagMoney + "").endsWith("0")) {
                params.put("PayResult[EraseOdd]", 0);
            } else {
                params.put("PayResult[EraseOdd]", slipZero);
            }
        } else {
            if (slipZero != null && !slipZero.equals("")) {
                params.put("PayResult[EraseOdd]", slipZero);
            } else {
                params.put("PayResult[EraseOdd]", 0.00);
            }
        }


        //优惠活动GID
        if (mDiscountActivityGid != null && !mDiscountActivityGid.equals("")) {
            params.put("PayResult[CC_GID]", mDiscountActivityGid);
        }
        //使用的优惠券与会员的关系GID集合
        for (int i = 0, len = mCheckedList.size(); i < len; i++) {
            params.put("PayResult[GIDList][" + i + "]", mCheckedList.get(i).getGID());
        }
//        params.put("PayResult[PayPoint]", Decima2KeeplUtil.stringToDecimal(mPointSpend + ""));//积分支付
        payWay();
        double moneyAll = 0;
        for (int i = 0; i < mPayWayBeanList.size(); i++) {
            params.put("PayResult[PayTypeList][" + i + "][PayName]", mPayWayBeanList.get(i).getPayName());//支付方式名称
            params.put("PayResult[PayTypeList][" + i + "][PayCode]", mPayWayBeanList.get(i).getPayCode());//支付方式代码
            params.put("PayResult[PayTypeList][" + i + "][PayMoney]", mPayWayBeanList.get(i).getPayMoney());//支付金额
            moneyAll = moneyAll + Double.parseDouble(mPayWayBeanList.get(i).getPayMoney());
            if (mPayWayBeanList.get(i).getPayCode().equals("JFZF")) {
                params.put("PayResult[PayTypeList][" + i + "][PayPoint]", mPayWayBeanList.get(i).getPayPoint());//支付积分
            }
            if (mPayWayBeanList.get(i).getGID() != null || !"".equals(mPayWayBeanList.get(i).getGID())) {
                params.put("PayResult[PayTypeList][" + i + "][GID]", mPayWayBeanList.get(i).getGID());
            }
        }
        //实收金额
        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(moneyAll + ""));
//        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(mSSMoney + ""));

        Li("联合支付 快速消费确认支付---------------------random:" + params.toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    final KSXF_Success_Bean ksxf_success_bean = CommonFun.JsonToObj(responseString, KSXF_Success_Bean.class);
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setConfirmText("确定");
                    if (mMemberInfo == null) {
                        mSweetAlertDialog.setCancelText("添加为会员");
                    }
                    mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent intent = new Intent(UnionPayActivity.this, AddMemberActivity.class);
                            startActivity(intent);
                        }
                    });
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (mPrint) {
                                //打印小票
                                new HttpGetPrintContents(UnionPayActivity.this, MyApplication.KSXF_PRINT_TIMES, ksxf_success_bean.getData().getGID(), intentHandler).KSXF();
                            } else {
                                Intent intent = new Intent(UnionPayActivity.this, YSLFastConsumeActivity.class);
                                startActivity(intent);
                                finish();
                            }
//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {

//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } catch (JsonSyntaxException e) {
                    CustomToast.makeText(UnionPayActivity.this, "打印失败！", Toast.LENGTH_SHORT).show();
                    finish();
                    mPayWayBeanList.clear();
                }
            }

            @Override
            public void onFailure(String msg) {
                mPayWayBeanList.clear();
                if (msg.contains("SmsSign")) {//短信未设置默认签名
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，未设置默认签名！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {
                            finish();
//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.contains("BuySms")) {//短信库存不足
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，短信库存不足!");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {
                            finish();
//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else {
                    hintDialog(msg);
                }
            }
        };
        callBack.setLoadingAnimation(this, "正在支付...", false);
        HttpHelper.post(this, HttpAPI.API().FAST_CONSUME_PAY, params, callBack);
    }

    /**
     * 商品消费联合支付提交订单
     */
    private void unnionGoodsPayStepOne() {
        RequestParams params = new RequestParams();
        //订单编号
//        params.put("CO_OrderCode", CreateOrder.createOrder("SP"));
        params.put("CO_OrderCode", mOrderNo);
        //订单时间
//        params.put("OrderTime", DateTimeUtil.getReallyTimeNow());
        params.put("OrderTime", OrderTime);
        //会员卡号
        params.put("VIP_Card", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
        //备注
        params.put("CO_Remark", remark);

//        params.put("OrderType", mOrderType);
//        params.put("CC_GID", mDiscountActivityGid);//优惠活动GID
//        params.put("DisMoney", Decima2KeeplUtil.stringToDecimal(mReceivableAmount + ""));//折后金额
//        params.put("ActivityValue", "");
//        params.put("CO_TotalPrice", mReceivableAmount);
        for (int i = 0; i < mGoodeBeanList.size(); i++) {
            String typec = "0";

            if (mGoodeBeanList.get(i).getPM_IsService() == 3 || mGoodeBeanList.get(i).getPM_IsService() == 4) {
                typec = "1";
            } else {
                typec = "0";
            }
            //商品GID(或是套餐GID)
            params.put("Goods[" + i + "][PM_GID]", mGoodeBeanList.get(i).getGID());
            //商品数量
            params.put("Goods[" + i + "][PM_Number]", mGoodeBeanList.get(i).getNum());
            //商品类型
            params.put("Goods[" + i + "][Type]", typec);
            //商品单价
            params.put("Goods[" + i + "][PM_UnitPrice]", mGoodeBeanList.get(i).getPM_UnitPrice());
            //商品折扣
//            params.put("Goods[" + i + "][PM_Discount]", mGoodeBeanList.get(i).getPM_SpecialOfferValue());
            double moneyD = Double.parseDouble(mGoodeBeanList.get(i).getMoney_d()) % (mGoodeBeanList.get(i).getPM_UnitPrice() * mGoodeBeanList.get(i).getNum());
            if (mGoodeBeanList.get(i).getMoney_d().equals("") || moneyD == 0) {
                params.put("Goods[" + i + "][PM_Discount]", 1);
            } else {
                params.put("Goods[" + i + "][PM_Discount]", Double.parseDouble(mGoodeBeanList.get(i).getMoney_d()) / (mGoodeBeanList.get(i).getPM_UnitPrice() * mGoodeBeanList.get(i).getNum()));
            }
            //商品小计
            params.put("Goods[" + i + "][PM_Money]", Decima2KeeplUtil.stringToDecimal(mGoodeBeanList.get(i).getZHMoney() + ""));

//            params.put("Goods[" + i + "][PM_Name]", mGoodeBeanList.get(i).getPM_Name());

            //提成员工
            if (mStaffListGidList != null && mStaffListGidList.get(String.valueOf(i)) != null) {
                for (int j = 0; j < mStaffListGidList.get(String.valueOf(i)).size(); j++) {//提成员工GID
                    params.put("Goods[" + i + "][EM_GIDList][" + j + "]", mStaffListGidList.get(String.valueOf(i)).get(j));
                }
            }
        }

        Li("联合支付 商品消费提交订单---params-------random:" + params);

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                GoodsStepOneBeans stepOneBeans = CommonFun.JsonToObj(responseString, GoodsStepOneBeans.class);
                Li("联合支付 商品消费支付---params--111-----random:" + stepOneBeans.getData().getGID());
                mGoodsBeanGID = stepOneBeans.getData().getGID();
                unnionGoodsPayStepTwo();
            }

            @Override
            public void onFailure(String msg) {
                hintDialog(msg);
            }
        };
        callBack.setLoadingAnimation(this, "正在提交订单...", false);
        HttpHelper.post(this, HttpAPI.API().GOODS_CONSUME_SUB, params, callBack);
    }

    /**
     * 商品消费联合支付
     */
    private void unnionGoodsPayStepTwo() {
        RequestParams params = new RequestParams();
        //订单GID
        params.put("OrderGID", mGoodsBeanGID);
        //是否发送短信
        params.put("Smsg", mMessage ? 1 : 0);
        //应收金额
//        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(mReceivableAmount + ""));
        double moneyFlag = mReceivableAmount;
        /** 如果有抹零和优惠券就计算*/
        if (!etpaymoling.getText().toString().equals("")) {
            moneyFlag = mReceivableAmount - Double.parseDouble(etpaymoling.getText().toString());
        }
        if (!etYHQ.getText().toString().equals("")) {
            moneyFlag = moneyFlag - Double.parseDouble(etYHQ.getText().toString());
        }
        /***************************/
        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(moneyFlag + ""));
        //找零金额
        params.put("PayResult[GiveChange]", "0");
        //抹零
        //抹零金额
        String slipZero = etpaymoling.getText().toString();
        if (slipZero != null && !slipZero.equals("")) {
            params.put("PayResult[EraseOdd]", slipZero);
        } else {
            params.put("PayResult[EraseOdd]", 0.00);
        }
        //优惠活动GID
        if (mDiscountActivityGid != null && !mDiscountActivityGid.equals("")) {
            params.put("PayResult[CC_GID]", mDiscountActivityGid);
        }
        //使用的优惠券与会员的关系GID集合
        for (int i = 0, len = mCheckedList.size(); i < len; i++) {
            params.put("PayResult[GIDList][" + i + "]", mCheckedList.get(i).getGID());
        }
//        params.put("PayResult[PayPoint]", Decima2KeeplUtil.stringToDecimal(mPointSpend + ""));//抵扣积分

        payWay();
        double moneyAll = 0;
        for (int i = 0; i < mPayWayBeanList.size(); i++) {
            //支付方式编码
            params.put("PayResult[PayTypeList][" + i + "][PayCode]", mPayWayBeanList.get(i).getPayCode());
            //支付方式名称
            params.put("PayResult[PayTypeList][" + i + "][PayName]", mPayWayBeanList.get(i).getPayName());
            //支付金额
            params.put("PayResult[PayTypeList][" + i + "][PayMoney]", mPayWayBeanList.get(i).getPayMoney());

            moneyAll = moneyAll + Double.parseDouble(mPayWayBeanList.get(i).getPayMoney());

            if (mPayWayBeanList.get(i).getPayCode().equals("JFZF")) {
                params.put("PayResult[PayTypeList][" + i + "][PayPoint]", mPayWayBeanList.get(i).getPayPoint());//支付积分
            }
            if (mPayWayBeanList.get(i).getGID() != null || !"".equals(mPayWayBeanList.get(i).getGID())) {
                params.put("PayResult[PayTypeList][" + i + "][GID]", mPayWayBeanList.get(i).getGID());
            }
        }
        //实收金额
        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(moneyAll + ""));
//        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(mTotalMoney + ""));

        Li("联合支付 商品消费支付---params-------random:" + params);

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    final SPXF_Success_Bean spxf_success_bean = CommonFun.JsonToObj(responseString, SPXF_Success_Bean.class);
                    Li("联合支付 商品消费支付--1111-params-------random:" + new Gson().toJson(spxf_success_bean));
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setConfirmText("确定");
                    if (mMemberInfo == null) {
                        mSweetAlertDialog.setCancelText("添加为会员");
                    }
                    mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent intent = new Intent(UnionPayActivity.this, AddMemberActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (mPrint) {
                                //打印小票
                                new HttpGetPrintContents(UnionPayActivity.this, MyApplication.SPXF_PRINT_TIMES, spxf_success_bean.getData().getGID(), intentHandler).SPXF();
                            } else {

                                Intent intent = new Intent(UnionPayActivity.this, GoodsConsumeActivity.class);
                                intent.putExtra("type", "SPXF");
                                startActivity(intent);
                                finish();

                            }

                        }
                    });
                    mSweetAlertDialog.show();
                } catch (JsonSyntaxException e) {
                    CustomToast.makeText(UnionPayActivity.this, "打印失败！", Toast.LENGTH_SHORT).show();
                    finish();
                    mPayWayBeanList.clear();
                }
            }

            @Override
            public void onFailure(String msg) {
                Li("联合支付 商品消费支付--onFailure-params-------random:" + msg);
                mPayWayBeanList.clear();
                if (msg.contains("SmsSign")) {
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，未设置默认签名！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {

                            Intent intent = new Intent(UnionPayActivity.this, GoodsConsumeActivity.class);
                            intent.putExtra("type", "SPXF");
                            startActivity(intent);
                            finish();

//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.contains("BuySms")) {
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，短信库存不足！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {

                            Intent intent = new Intent(UnionPayActivity.this, GoodsConsumeActivity.class);
                            intent.putExtra("type", "SPXF");
                            startActivity(intent);
                            finish();

//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else {
                    hintDialog(msg);
                }
            }
        };
        callBack.setLoadingAnimation(this, "正在支付...", false);
        HttpHelper.post(this, HttpAPI.API().GOODS_CONSUME_PAY, params, callBack);

    }


    /**
     * 一键加油联合支付提交订单
     */
    private void unnionOneKeyPayStepOne() {
        RequestParams params = new RequestParams();

        //会员卡号
        params.put("VIP_Card", vipdatalist == null ? "00000" : vipdatalist.getVCH_Card());
        //油品
        for (int i = 0; i < mYJJYBeanList.size(); i++) {
            if (mYJJYBeanList.get(i).isChecked()) {
                params.put("OM_GID", mYJJYBeanList.get(i).getGID());
//                params.put("Number", mYJJYBeanList.get(i).getNum());
//                params.put("OrderAmount", mYJJYBeanList.get(i).getZHMoney());
            }
        }
        //加油金额
        params.put("OrderAmount", mYJJYBeanList.get(0).getZHMoney());
        //加油数量
        params.put("Number", mYJJYBeanList.get(0).getNum());
        //订单号
//        params.put("OrderCode", CreateOrder.createOrder("JY"));
        params.put("OrderCode", mOrderNo);
        //订单时间
        params.put("OrderTime", OrderTime);

        Li("联合支付 一键加油订单---params-------random:" + params);

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                OilStepOneBean stepOneBeans = CommonFun.JsonToObj(responseString, OilStepOneBean.class);
                mGoodsBeanGID = stepOneBeans.getData().getGID();
                unnionOneKeyPayStepTwo();
            }

            @Override
            public void onFailure(String msg) {
                hintDialog(msg);
            }
        };
        callBack.setLoadingAnimation(this, "正在提交订单...", false);
        HttpHelper.post(this, HttpAPI.API().ONE_KEY_OIL_SUB, params, callBack);
    }

    /**
     * 一键加油联合支付
     */
    private void unnionOneKeyPayStepTwo() {
        RequestParams params = new RequestParams();
        //订单GID
        params.put("OrderGID", mGoodsBeanGID);
        //是否发送短信
        params.put("Smsg", mMessage ? 1 : 0);
        //应收金额
//        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(mReceivableAmount + ""));
        double moneyFlag = mReceivableAmount;
        /** 如果有抹零和优惠券就计算*/
        if (!etpaymoling.getText().toString().equals("")) {
            moneyFlag = mReceivableAmount - Double.parseDouble(etpaymoling.getText().toString());
        }
        if (!etYHQ.getText().toString().equals("")) {
            moneyFlag = moneyFlag - Double.parseDouble(etYHQ.getText().toString());
        }
        /***************************/
        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(moneyFlag + ""));
        //找零金额
        params.put("PayResult[GiveChange]", "0");
        //抹零金额
        String slipZero = etpaymoling.getText().toString();
        if (slipZero != null && !slipZero.equals("")) {
            params.put("PayResult[EraseOdd]", slipZero);
        } else {
            params.put("PayResult[EraseOdd]", 0.00);
        }
        //优惠活动GID
        if (mDiscountActivityGid != null && !mDiscountActivityGid.equals("")) {
            params.put("PayResult[CC_GID]", mDiscountActivityGid);
        }
        //使用的优惠券与会员的关系GID集合
        for (int i = 0, len = mCheckedList.size(); i < len; i++) {
            params.put("PayResult[GIDList][" + i + "]", mCheckedList.get(i).getGID());
        }
//        params.put("PayResult[PayPoint]", Decima2KeeplUtil.stringToDecimal(mPointSpend + ""));//抵扣积分
        payWay();
        double moneyAll = 0;
        for (int i = 0; i < mPayWayBeanList.size(); i++) {
            //支付方式编码
            params.put("PayResult[PayTypeList][" + i + "][PayCode]", mPayWayBeanList.get(i).getPayCode());
            //支付方式名称
            params.put("PayResult[PayTypeList][" + i + "][PayName]", mPayWayBeanList.get(i).getPayName());
            //支付金额
            params.put("PayResult[PayTypeList][" + i + "][PayMoney]", mPayWayBeanList.get(i).getPayMoney());
            moneyAll = moneyAll + Double.parseDouble(mPayWayBeanList.get(i).getPayMoney());
            if (mPayWayBeanList.get(i).getPayCode().equals("JFZF")) {
                params.put("PayResult[PayTypeList][" + i + "][PayPoint]", mPayWayBeanList.get(i).getPayPoint());//支付积分
            }
            if (mPayWayBeanList.get(i).getGID() != null || !"".equals(mPayWayBeanList.get(i).getGID())) {
                params.put("PayResult[PayTypeList][" + i + "][GID]", mPayWayBeanList.get(i).getGID());
            }
        }
        //实收金额
        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(moneyAll + ""));
//        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(mTotalMoney + ""));

        Li("联合支付 一键加油支付---params-------random:" + params);

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    final YJJY_Success_Bean yjjy_success_bean = CommonFun.JsonToObj(responseString, YJJY_Success_Bean.class);
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setConfirmText("确定");
                    if (mMemberInfo == null) {
                        mSweetAlertDialog.setCancelText("添加为会员");
                    }
                    mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            ActivityManager.getInstance().exit();
                            Intent intent = new Intent(UnionPayActivity.this, AddMemberActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (mPrint) {
                                //打印小票
                                new HttpGetPrintContents(UnionPayActivity.this, MyApplication.YJJY_PRINT_TIMES, yjjy_success_bean.getData().getGID(), intentHandler).YJJY();
                            } else {

                                Intent intent = new Intent(UnionPayActivity.this, OneKeyRefuelingActivity.class);
                                startActivity(intent);
                                finish();

                            }

                        }
                    });
                    mSweetAlertDialog.show();
                } catch (JsonSyntaxException e) {
                    CustomToast.makeText(UnionPayActivity.this, "打印失败！", Toast.LENGTH_SHORT).show();
                    finish();
                    mPayWayBeanList.clear();
                }
            }

            @Override
            public void onFailure(String msg) {
                mPayWayBeanList.clear();
                if (msg.contains("SmsSign")) {
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，未设置默认签名！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {
                            ActivityManager.getInstance().exit();
                            Intent intent = new Intent(UnionPayActivity.this, OneKeyRefuelingActivity.class);
                            startActivity(intent);
                            finish();

//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.contains("BuySms")) {
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，短信库存不足！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {
                            ActivityManager.getInstance().exit();
                            Intent intent = new Intent(UnionPayActivity.this, OneKeyRefuelingActivity.class);
                            startActivity(intent);
                            finish();

//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else {
                    hintDialog(msg);
                }
            }
        };
        callBack.setLoadingAnimation(this, "正在支付...", false);
        HttpHelper.post(this, HttpAPI.API().ONE_KEY_OIL_PAY, params, callBack);

    }


    /**
     * 会员充次提交订单
     */
    private void UnionInflateCountStepOne() {
        RequestParams params = new RequestParams();
        //充次订单
//        params.put("MC_Order", CreateOrder.createOrder("CC"));
        params.put("MC_Order", mOrderNo);
        //订单时间
        params.put("OrderTime", OrderTime);
        //会员卡号
        params.put("VIP_Card", mCardNo);
        //备注
        params.put("MC_Remark", remark);

        for (int i = 0; i < mGoodeBeanList.size(); i++) {
            String typec = "0";
            if (mGoodeBeanList.get(i).getPM_IsService() == 3 || mGoodeBeanList.get(i).getPM_IsService() == 4) {
                typec = "1";
            }
            //商品GID(或是套餐GID)
            params.put("ServeceList[" + i + "][PM_GID]", mGoodeBeanList.get(i).getGID());
            //商品数量
            params.put("ServeceList[" + i + "][PM_Number]", mGoodeBeanList.get(i).getNum());
            //商品类型
            params.put("ServeceList[" + i + "][Type]", typec);
            //商品单价
            params.put("ServeceList[" + i + "][PM_UnitPrice]", mGoodeBeanList.get(i).getPM_UnitPrice());
            //商品折扣
//            params.put("ServeceList[" + i + "][PM_Discount]", mGoodeBeanList.get(i).getPM_IsDiscount());
            double moneyD = Double.parseDouble(mGoodeBeanList.get(i).getMoney_d()) % (mGoodeBeanList.get(i).getPM_UnitPrice() * mGoodeBeanList.get(i).getNum());
            if (mGoodeBeanList.get(i).getMoney_d().equals("") || moneyD == 0) {
                params.put("Goods[" + i + "][PM_Discount]", 1);
            } else {
                params.put("Goods[" + i + "][PM_Discount]", Double.parseDouble(mGoodeBeanList.get(i).getMoney_d()) / (mGoodeBeanList.get(i).getPM_UnitPrice() * mGoodeBeanList.get(i).getNum()));
            }
            //商品小计
            params.put("ServeceList[" + i + "][PM_Money]", Decima2KeeplUtil.stringToDecimal(mGoodeBeanList.get(i).getZHMoney() + ""));
            //提成员工
            if (mStaffListGidList != null && mStaffListGidList.get(String.valueOf(i)) != null) {
                for (int j = 0; j < mStaffListGidList.get(String.valueOf(i)).size(); j++) {//提成员工GID
                    params.put("ServeceList[" + i + "][EM_GIDList][" + j + "]", mStaffListGidList.get(String.valueOf(i)).get(j));
                }
            }
//            params.put("ServeceList[" + i + "][PM_Name]", mGoodeBeanList.get(i).getPM_Name());
        }
//        params.put("AfterDiscount", Decima2KeeplUtil.stringToDecimal(mReceivableAmount + ""));//折后金额
        Li("联合支付 会员充次提交订单---params-------random:" + params);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {

                Li("会员充次提交订单----random:" + responseString);


                VipInflateStepOneBean stepOneBean = CommonFun.JsonToObj(responseString, VipInflateStepOneBean.class);
                mVipInflatCountGID = stepOneBean.getData().getGID();
                UnionInflateCountStepTwo();
            }

            @Override
            public void onFailure(String msg) {
                hintDialog(msg);
            }
        };
        callBack.setLoadingAnimation(this, "正在提交订单...", false);
        HttpHelper.post(this, HttpAPI.API().MEM_CHARGE_SUB, params, callBack);

    }

    /**
     * todo:会员充次支付
     */
    private void UnionInflateCountStepTwo() {
        RequestParams params = new RequestParams();
        //订单GID
        params.put("OrderGID", mVipInflatCountGID);
        //是否发送短信
        params.put("Smsg", mMessage ? 1 : 0);
        //应收金额
        double moneyFlag;
        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(tv_order_money.getText().toString()));
        //实收金额
        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(tv_order_money.getText().toString()));

        //找零金额
        params.put("PayResult[GiveChange]", "0");
        //抹零金额
        String slipZero = etpaymoling.getText().toString();
        if (slipZero != null && !slipZero.equals("")) {
            params.put("PayResult[EraseOdd]", slipZero);
        } else {
            params.put("PayResult[EraseOdd]", 0.00);
        }
        //优惠活动GID
        if (mDiscountActivityGid != null && !mDiscountActivityGid.equals("")) {
            params.put("PayResult[CC_GID]", mDiscountActivityGid);
        }
        //使用的优惠券与会员的关系GID集合
        for (int i = 0, len = mCheckedList.size(); i < len; i++) {
            params.put("PayResult[GIDList][" + i + "]", mCheckedList.get(i).getGID());
        }
//        if (mStaffListGid != null) {
//            for (int i = 0; i < mStaffListGid.size(); i++) {//提成员工GID
//                params.put("ServeceList[0][EM_GIDList][" + i + "]", mStaffListGid.get(i));
//            }
//        }
        payWay();

        for (int i = 0; i < mPayWayBeanList.size(); i++) {
            //支付方式编码
            params.put("PayResult[PayTypeList][" + i + "][PayCode]", mPayWayBeanList.get(i).getPayCode());
            //支付方式名称
            params.put("PayResult[PayTypeList][" + i + "][PayName]", mPayWayBeanList.get(i).getPayName());
            //支付金额
            params.put("PayResult[PayTypeList][" + i + "][PayMoney]", mPayWayBeanList.get(i).getPayMoney());
            //支付积分
            if (mPayWayBeanList.get(i).getPayCode().equals("JFZF")) {
                params.put("PayResult[PayTypeList][" + i + "][PayPoint]", mPayWayBeanList.get(i).getPayPoint());
            }
            /**（已过时）优惠券GID集合*/
//            if (mPayWayBeanList.get(i).getGID() != null || !"".equals(mPayWayBeanList.get(i).getGID())) {
//                params.put("PayResult[PayTypeList][" + i + "][GID]", mPayWayBeanList.get(i).getGID());
//            }
            /****************/
        }
        Li("联合支付 会员充次支付----random:" + params.toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {


                    Li("responseString---------random:" + responseString);

                    final HYCC_Success_Bean hycc_success_bean = CommonFun.JsonToObj(responseString, HYCC_Success_Bean.class);
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("充次成功");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (mPrint) {
                                //打印小票
                                new HttpGetPrintContents(UnionPayActivity.this, MyApplication.HYCC_PRINT_TIMES, hycc_success_bean.getData().getGID(), intentHandler).HYCC();
                            } else {
                                Intent intent = new Intent(UnionPayActivity.this, GoodsConsumeActivity.class);
                                intent.putExtra("type", "HYCC");
                                startActivity(intent);
                                finish();
                            }
//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {

//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } catch (JsonSyntaxException e) {
                    CustomToast.makeText(UnionPayActivity.this, "打印失败！", Toast.LENGTH_SHORT).show();
                    finish();
                    mPayWayBeanList.clear();
                }
            }

            @Override
            public void onFailure(String msg) {
                mPayWayBeanList.clear();
                if (msg.contains("SmsSign")) {
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("充次成功");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setContentText("短信未发送，未设置默认签名!");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {
                            Intent intent = new Intent(UnionPayActivity.this, GoodsConsumeActivity.class);
                            intent.putExtra("type", "HYCC");
                            startActivity(intent);
                            finish();
//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.contains("BuySms")) {
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("充次成功");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setContentText("短信未发送，短信库存不足!");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {
                            Intent intent = new Intent(UnionPayActivity.this, GoodsConsumeActivity.class);
                            intent.putExtra("type", "HYCC");
                            startActivity(intent);
                            finish();
//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else {
                    hintDialog(msg);
                }
            }
        };
        callBack.setLoadingAnimation(this, "正在支付...", false);
        HttpHelper.post(this, HttpAPI.API().MEM_CHARGE_PAY, params, callBack);
    }


    /**
     * 会员充值提交订单
     */
    private void UnionVipInflateMoneyStepOne() {
        RequestParams params = new RequestParams();
        //订单号
        params.put("MR_Order", mOrderNo);
        //订单时间
        params.put("OrderTime", OrderTime);
        //会员卡号
        params.put("VIP_Card", vipdatalist == null ? "00000" : vipdatalist.getVCH_Card());
        //快捷充值GID
        params.put("CC_GID", mDiscountActivityGid);
        //充值金额
        params.put("MR_Amount", Decima2KeeplUtil.stringToDecimal(mRechargeMoney + ""));
        //赠送金额
        params.put("MR_GivenAmount", Decima2KeeplUtil.stringToDecimal(mGiveMoney + ""));
        //赠送积分
        params.put("MR_Integral", (int) mGetPoints);
        //提成员工
        if (mStaffListGid != null) {
            for (int j = 0; j < mStaffListGid.size(); j++) {
                params.put("EM_GIDList[" + j + "]", mStaffListGid.get(j));
            }
        }
        //备注
        params.put("MR_Remark", remark);

//        params.put("MR_Total", Decima2KeeplUtil.stringToDecimal(mRechargeTotal + ""));//充值合计

        Li("联合支付 会员充值提交订单---------------------random:" + params.toString());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                VipInflateMoneyBean moneyBean = CommonFun.JsonToObj(responseString, VipInflateMoneyBean.class);
                mVipInflateMoneyGID = moneyBean.getData().getGID();
                UnionVipInflateMoneyStepTwo();
            }

            @Override
            public void onFailure(String msg) {
                hintDialog(msg);

            }
        };
        callBack.setLoadingAnimation(this, "正在提交订单...", false);
        HttpHelper.post(this, HttpAPI.API().MEM_RECHARGE_SUB, params, callBack);
    }

    /**
     * 会员充值支付
     */
    private void UnionVipInflateMoneyStepTwo() {
        RequestParams params = new RequestParams();
        //订单GID
        params.put("OrderGID", mVipInflateMoneyGID);
        //是否发送短信
        params.put("Smsg", mMessage ? 1 : 0);
        //应收金额
//        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(mReceivableAmount + ""));
        double moneyFlag;
        if (!etpaymoling.getText().toString().equals("")) {
            moneyFlag = mReceivableAmount - Double.parseDouble(etpaymoling.getText().toString());
        } else {
            moneyFlag = mReceivableAmount;
        }
        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(moneyFlag + ""));
        //找零金额
        params.put("PayResult[GiveChange]", "0");
        //抹零金额
        String slipZero = etpaymoling.getText().toString();
        if (slipZero != null && !slipZero.equals("")) {
            params.put("PayResult[EraseOdd]", slipZero);
        } else {
            params.put("PayResult[EraseOdd]", 0.00);
        }
//        params.put("PayResult[PayPoint]", Decima2KeeplUtil.stringToDecimal(mPointSpend + ""));//积分支付
        payWay();
        double moneyAll = 0;
        for (int i = 0; i < mPayWayBeanList.size(); i++) {
            //支付方式编码
            params.put("PayResult[PayTypeList][" + i + "][PayCode]", mPayWayBeanList.get(i).getPayCode());
            //支付方式名称
            params.put("PayResult[PayTypeList][" + i + "][PayName]", mPayWayBeanList.get(i).getPayName());
            //支付金额
            params.put("PayResult[PayTypeList][" + i + "][PayMoney]", mPayWayBeanList.get(i).getPayMoney());
            moneyAll = moneyAll + Double.parseDouble(mPayWayBeanList.get(i).getPayMoney());
            if (mPayWayBeanList.get(i).getPayCode().equals("JFZF")) {
                params.put("PayResult[PayTypeList][" + i + "][PayPoint]", mPayWayBeanList.get(i).getPayPoint());//支付积分
            }
            if (mPayWayBeanList.get(i).getGID() != null || !"".equals(mPayWayBeanList.get(i).getGID())) {
                params.put("PayResult[PayTypeList][" + i + "][GID]", mPayWayBeanList.get(i).getGID());
            }
        }
        //实收金额
        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(moneyAll + ""));
//        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(mReceivableAmount + ""));

        Li("联合支付 会员充值支付---------------------random:" + params.toString());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    final HYCZ_Success_Bean hycz_success_bean = CommonFun.JsonToObj(responseString, HYCZ_Success_Bean.class);
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("充值成功");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (mPrint) {
                                //打印小票
                                new HttpGetPrintContents(UnionPayActivity.this, MyApplication.HYCZ_PRINT_TIMES, hycz_success_bean.getData().getGID(), intentHandler).HYCZ();
                            } else {
                                ActivityManager.getInstance().exit();
                                Intent intent = new Intent(UnionPayActivity.this, MemberRechargeActivity.class);
                                startActivity(intent);
                                finish();
                            }
//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {

//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } catch (JsonSyntaxException e) {
                    CustomToast.makeText(UnionPayActivity.this, "打印失败！", Toast.LENGTH_SHORT).show();
                    finish();
                    mPayWayBeanList.clear();
                }
            }

            @Override
            public void onFailure(String msg) {
                if (msg.contains("SmsSign")) {
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("充值成功");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setContentText("短信未发送，未设置默认签名！");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {
                            ActivityManager.getInstance().exit();
                            Intent intent = new Intent(UnionPayActivity.this, MemberRechargeActivity.class);
                            startActivity(intent);
                            finish();
//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.contains("BuySms")) {
                    mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("充值成功");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setContentText("短信未发送，短信库存不足！");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

//                            if (!"".equals(MDZZ) && MDZZ != null) {
//                                Intent intent = new Intent(UnionPayActivity.this, MemberListActivity.class);
//                                intent.putExtra("carddd", mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card());
//                                startActivity(intent);
//                                finish();
//                            } else {
                            ActivityManager.getInstance().exit();
                            Intent intent = new Intent(UnionPayActivity.this, MemberRechargeActivity.class);
                            startActivity(intent);
                            finish();
//                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else {
                    hintDialog(msg);
                }
            }
        };
        callBack.setLoadingAnimation(this, "正在支付...", false);
        HttpHelper.post(this, HttpAPI.API().MEM_RECHARGE_PAY, params, callBack);
    }

    public class IntentHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent;
            switch (msg.what) {
                case 1://商品消费

                    intent = new Intent(UnionPayActivity.this, GoodsConsumeActivity.class);
                    intent.putExtra("type", "SPXF");
                    startActivity(intent);
                    finish();
                    break;
                case 2://会员充次
                    intent = new Intent(UnionPayActivity.this, GoodsConsumeActivity.class);
                    intent.putExtra("type", "HYCC");
                    startActivity(intent);
                    finish();
                    break;
                case 3://快速消费
                    intent = new Intent(UnionPayActivity.this, YSLFastConsumeActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case 4://会员充值
                    ActivityManager.getInstance().exit();
                    intent = new Intent(UnionPayActivity.this, MemberRechargeActivity.class);
                    startActivity(intent);
                    finish();
                    break;

                case 5://一键加油
                    intent = new Intent(UnionPayActivity.this, OneKeyRefuelingActivity.class);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
        }
        super.onDestroy();
    }


    /**
     * 密码输入框
     */
    private void showPasswordDialog(final int type) {
        LayoutInflater inflater = getLayoutInflater();
        final View dialog = inflater.inflate(R.layout.input_password_dialog_layout, (ViewGroup) findViewById(R.id.input_dialog));
        final EditText etPassword = (EditText) dialog.findViewById(R.id.et_input_dialog_password);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_input_cancel);
        Button btnConfirm = (Button) dialog.findViewById(R.id.btn_input_confirm);
        AlertDialog.Builder builder = new AlertDialog.Builder(UnionPayActivity.this);
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
                params.put("VCH_Card", mCardNo);
                params.put("VCH_Pwd", etPassword.getText().toString());
                HttpHelper.post(UnionPayActivity.this, HttpAPI.API().PASSWORDVERIFY, params, new CallBack() {
                    @Override
                    public void onSuccess(String responseString, Gson gson) {
                        PasswordVerifyBean bean = CommonFun.JsonToObj(responseString, PasswordVerifyBean.class);
                        if (bean.isSuccess()) {
                            switch (type) {
                                case 1:
//                                    fastPay();
                                    UnionFastPayStepOne();
                                    break;
                                case 2:
//                                    goodsPay();
                                    unnionGoodsPayStepOne();
                                    break;
                                case 3:
//                                    chargeTimePay();
                                    UnionInflateCountStepOne();
                                    break;
                                case 4:
                                    unnionOneKeyPayStepOne();
                                    break;
                                /**9364会员延期，输入费用，联合支付，点击确认收款没有反应 20191028 cy */
                                case 5:
                                    memberDelay();
                                    break;
                                /******************************************************** 20191028 cy */
                            }
                            alertDialog.dismiss();
                        } else {
                            CustomToast.makeText(UnionPayActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        CustomToast.makeText(UnionPayActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
            }
        });
        alertDialog.show();
    }

    /**
     * 9364会员延期，输入费用，联合支付，点击确认收款没有反应 20191028 cy
     *
     * @Description: 会员延期支付
     */
    private void memberDelay() {
        DecimalFormat df = new DecimalFormat("#.00");

        RequestParams params = new RequestParams();

        //订单编号
//        params.put("CO_OrderCode", CreateOrder.createOrder("YQ"));
        params.put("CO_OrderCode", mOrderNo);
        //会员卡号
        params.put("VCH_Card", mMemberInfo.getVCH_Card());
        //延期时间
        params.put("Months", mDelayTime);
        //延期时间单位:天、月、年
        params.put("TimesTyepe", mDelayType);
        //延期次数
        params.put("MIA_SurplusTimes", mMIA_SurplusTimes);
        //延期费用
        params.put("CO_Monetary", df.format(mReceivableAmount));
        //备注
        params.put("DelayRemark", mRemark == null ? "" : mRemark);
        //应收金额
//        params.put("PayResult[DisMoney]", df.format(mReceivableAmount));
        double moneyFlag = mReceivableAmount;
        /** 如果有抹零和优惠券就计算*/
        if (!etpaymoling.getText().toString().equals("")) {
            moneyFlag = mReceivableAmount - Double.parseDouble(etpaymoling.getText().toString());
        }
        if (!etYHQ.getText().toString().equals("")) {
            moneyFlag = moneyFlag - Double.parseDouble(etYHQ.getText().toString());
        }
        /***************************/
        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(moneyFlag + ""));
        //找零金额
        params.put("PayResult[GiveChange]", "0");
        //抹零金额
        String slipZero = etpaymoling.getText().toString();
        if (slipZero != null && !slipZero.equals("")) {
            params.put("PayResult[EraseOdd]", slipZero);
        } else {
            params.put("PayResult[EraseOdd]", 0.00);
        }
        //使用的优惠券与会员的关系GID集合
        for (int i = 0, len = mCheckedList.size(); i < len; i++) {
            params.put("PayResult[GIDList][" + i + "]", mCheckedList.get(i).getGID());
        }

//        params.put("PayPoint", "0");
//        params.put("DisMoney", df.format(mReceivableAmount));
//        params.put("SSMoney", df.format(mReceivableAmount));
//        params.put("ZLMoney", mZLMoney);

        mPayWayBeanList.clear();
        payWay();
        double moneyAll = 0;
        for (int i = 0; i < mPayWayBeanList.size(); i++) {
            //支付方式编码
            params.put("PayResult[PayTypeList][" + i + "][PayCode]", mPayWayBeanList.get(i).getPayCode());
            //支付方式名称
            params.put("PayResult[PayTypeList][" + i + "][PayName]", mPayWayBeanList.get(i).getPayName());
            //支付金额
            params.put("PayResult[PayTypeList][" + i + "][PayMoney]", mPayWayBeanList.get(i).getPayMoney());
            moneyAll = moneyAll + Double.parseDouble(mPayWayBeanList.get(i).getPayMoney());
            if (mPayWayBeanList.get(i).getPayCode().equals("JFZF")) {
                params.put("PayResult[PayTypeList][" + i + "][PayPoint]", mPayWayBeanList.get(i).getPayPoint());//支付积分
            }
        }
        //实收金额
        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(moneyAll + ""));
//        params.put("PayResult[PayTotalMoney]", df.format(mReceivableAmount));

        HttpHelper.post(UnionPayActivity.this, HttpAPI.API().VIPDELAY, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("延期成功!");
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        UnionPayActivity.this.finish();
                        ActivityUtils.finishActivity(VipDelayActivity.class);
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                mSweetAlertDialog = new SweetAlertDialog(UnionPayActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("延期失败!");
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setContentText(msg);
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                    }
                });
                mSweetAlertDialog.show();
            }
        });
    }
    /******************************************************** 20191028 cy */


    /**
     * 抹零
     */
    private void initZero() {
        if (mZeroType < 1) {
            return;
        }
        String flagMoney = tv_order_money.getText().toString();
        double moneyFlag = Double.parseDouble(flagMoney);
        if (mZeroType == 1) {//四舍五入到元
            mZeroMoney = DoubleMathUtil.sub(moneyFlag, Double.parseDouble(String.format("%.0f", moneyFlag)));
        } else if (mZeroType == 2) {//四舍五入到角
            mZeroMoney = DoubleMathUtil.sub(moneyFlag, Double.parseDouble(String.format("%.1f", moneyFlag)));
        } else if (mZeroType == 3) {//直接舍弃角
            Li(moneyFlag + "====initZero=====moneyFlag======random:" + mZeroMoney);
            mZeroMoney = DoubleMathUtil.sub(moneyFlag, (int) (moneyFlag / 1));
            Li("====initZero=======2====random:" + mZeroMoney);
        } else if (mZeroType == 4) {//直接舍弃分
            String s = moneyFlag + "";
            if (s.substring(s.indexOf("."), s.length()).length() == 3) {
                s = s.substring(s.length() - 1, s.length());
                mZeroMoney = Double.parseDouble("0.0" + s);
            } else {
                mZeroMoney = 0.00;
            }
        }
        Li(mPageFlag + "====initZero===========random:" + mZeroMoney);
        if ("HYCZ".equals(mPageFlag)) {
            mZeroMoney = 0.00;
        }
    }
}
