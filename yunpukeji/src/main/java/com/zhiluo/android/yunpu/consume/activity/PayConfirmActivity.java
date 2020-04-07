package com.zhiluo.android.yunpu.consume.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.bean.DiscountBean;
import com.zhiluo.android.yunpu.consume.bean.FastPayStepOneBean;
import com.zhiluo.android.yunpu.consume.jsonbean.DiscountTypeBean;
import com.zhiluo.android.yunpu.consume.jsonbean.SystemSwitchBean;
import com.zhiluo.android.yunpu.entity.SuccessBean;
import com.zhiluo.android.yunpu.entity.TCListBean;
import com.zhiluo.android.yunpu.goods.consume.activity.GoodsConsumeActivity;
import com.zhiluo.android.yunpu.goods.consume.adapter.GoodsPayAdapter;
import com.zhiluo.android.yunpu.goods.consume.bean.GoodsStepOneBeans;
import com.zhiluo.android.yunpu.goods.consume.bean.IntegralScalingBean;
import com.zhiluo.android.yunpu.goods.manager.bean.BarCodePayBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.QuerPayBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.consume.bean.SingleVip;
import com.zhiluo.android.yunpu.member.consume.bean.VipInflateStepOneBean;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity;
import com.zhiluo.android.yunpu.member.manager.activity.MemberListActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.mvp.model.PasswordVerifyBean;
import com.zhiluo.android.yunpu.mvp.presenter.SaoMaPayPresntter;
import com.zhiluo.android.yunpu.mvp.view.SaoMaPayView;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.payment.lkl.PayLKL;
import com.zhiluo.android.yunpu.print.bean.HYCC_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.KSXF_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.PrintSetBean;
import com.zhiluo.android.yunpu.print.bean.SPXF_Success_Bean;
import com.zhiluo.android.yunpu.print.util.GetPrintSet;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.sms.jsonbean.SmsSwitch;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.bean.TimesRulesBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.LoadingDialogUtil;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.utils.RegexUtil;
import com.zhiluo.android.yunpu.yslutils.DataUtils;
import com.zhiluo.android.yunpu.yslutils.YSLPayPopWindow;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.config.MyApplication.IS_LAKALA_POS_DEVICE;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 支付确认页面
 * 作者：罗咏哲 on 2017/6/23 10:01.
 * 邮箱：137615198@qq.com
 */
public class PayConfirmActivity extends BaseActivity implements YSLPayPopWindow.OnItemClickListener, YSLPayPopWindow.OnDismissListener, View.OnClickListener, GoodsPayAdapter.employeeClick, GoodsPayAdapter.imgicClick {

    @Bind(R.id.et_add_member_overdue_date)
    TextView etAddMemberOverdueDate;
    @Bind(R.id.iv_add_member_overdue_date)
    ImageView ivAddMemberOverdueDate;
    @Bind(R.id.lr_over_time)
    LinearLayout lrOverTime;
    @Bind(R.id.em_rule)
    TextView emRule;
    @Bind(R.id.tv_pay_confirm_rule)
    TextView tvPayConfirmRule;
    @Bind(R.id.rl_pay_confirm_rule)
    RelativeLayout rlPayConfirmRule;


    private View viewPayConfirmDiscount;
    private TextView tvOrderMoney;//订单金额
    private TextView tvDiscount;//优惠活动
    private RelativeLayout rlDiscount;//选择优惠活动
    private TextView tvEmployee;//提成员工
    private RelativeLayout rlEmployee;//选择提成员工
    private EditText etDiscountMoney;//折后金额
    private boolean isFocus;//是否选中折扣金额
    private TextView tvPonits;//获得积分
    private TextView tvCostMoney;//收款金额
    private TextView btnSubmit;//收款
    private YSLPayPopWindow mPayWayPopWindow;
    private CheckBox cbShortMessage, cbPrint;

    private String mMemberCardNo;//会员卡号
    private String mOrderNo;//订单号
    private double mOrderMoney;//订单金额
    private double mDiscountMoney, ksxfmDiscountMoney = 0.00, mMemberDiscountMoney;//折后金额,初始折后金额
    private double mGetPoints = 0.0;//获得积分
    private String mMemberName;//会员姓名
    double discount;//等级折扣
    private Intent mIntent;//Intent对象，用来获取其它页面穿过来的值
    private String mPageFlag;//页面标志，用来判断是那个页面跳转到该页面
    private String mPayType;//支付类型

    private double mPointSpend;//积分消费
    private String mRemark;//备注
    private String mPayWayName;//支付方式名称
    private String mPayWayCode;//支付方式代码

    private Handler mHandler;
    private DiscountTypeBean.DataBean mDataBean;//优惠活动
    private int mOrderType;//支付类型（会员 1，散客 2）
    private int mConsumeType;//消费优惠类型（1：充值优惠，2：消费优惠）
    private double mGiveMoney;//赠送金额
    private double mRechargeMoney;//充值金额
    private double mRechargeTotal;//充值合计（充值金额+赠送金额）
    private String mSG_GID;//服务GID
    private String mSG_Name;//服务名字
    private int mMC_Number;//充值服务次数
    private SweetAlertDialog mSweetAlertDialog;
    private List<ReportMessageBean.DataBean.EmplistBean> mStaffInfo;//提成员工信息
    private Map<String, List<ReportMessageBean.DataBean.EmplistBean>> mStaffInfoList = new HashMap<>();
    private double mGradePoint;//会员等级赠送积分
    private double mActivityPoint;//优惠活动赠送积分

    public static List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodsBeanList = new ArrayList<>();

    private StringBuilder mStaffName;//提成员工姓名
    private String mDiscountActivityGid = "";//优惠活动GID
    private ArrayList<String> mStaffListGid = new ArrayList<>();
    private Map<String, ArrayList<String>> mStaffListGidList = new HashMap<>();//提成员工GID
    private int empos;
    private SystemSwitchBean mSystemSwitch;//系统开关
    private DiscountBean mDiscountBean;//优惠金额和赠送金额对象
    private boolean isChoiceDiscount = false;
    //会员管理->商品消费->支付确认
    private String MDZZ;

    private String mLklPayType = "0"; //0：刷卡   1：扫码  默认为0
    private String mLklDisPosCode = "000000";//交易处理码  000000：刷卡  660000：扫码
    private static final int START_SCAN = 0x11;   //调用 SUNMI 扫码支付 传递的请求码

    private SharedPreferences sp, pref;
    private String mMaccode;//蓝牙地址
    private String mOperator;//操作人员
    private String mShopName;//店铺名称
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean mPasswordSwitch, mCashSwitch, mCardSwitch, mIntegralSwitch, mBalanceSwitch, mWXSwitch, mZfbSwitch, mSmSwitch, mOtherSwitch;
    private List<IntegralScalingBean> mIntegralScalingList;//单个商品获得积分占总积分比例集合
    private IntegralScalingBean mIntegralScalingBean;
    private double mInitPoint;//初始积分

    private String mOrderGID;//订单提交成功后返回参数
    private String mVipInflatCountGID;//会员充次订单提交返回参数
    private String mVipInflateMoneyGID;//会员充值订单提交返回参数
    private String mGoodsBeanGID;//商品消费提交订单返回
    private TCListBean.DataBean mTCList;

    private boolean ShortMessage = false;//是否发送短信
    private boolean Print = false;//是否打印
    private boolean isVip = false;

    /**
     * 商品消费
     ***/
    private boolean isSPXF = false;//是不是商品消费类型
    private boolean isJCXF = false;//是不是计次消费类型
    private boolean isKSXF = false;//是不是快速消费类型
    private boolean isKJSK = false;//是不是快捷消费类型
    private boolean isStaff = false;//是否开启员工提成
    private boolean istimesrule = false; //是否有计次规则
    private boolean isHYCC = false;
    private boolean isDiscount = false;//是否开启折后金额修改
    private boolean isVipRechage = false;//是不是会员充值类型
    private Intent getmIntent;
    private GoodsPayAdapter mGoodsPayAdapter;//商品消费计算界面上方收起和展开的adapter;

    private RecyclerView recyclerView;

    private CheckBox cbUpDown;
    private LinearLayout lTotalCost;
    private RelativeLayout lOderMoney;
    private TextView tvCost, tvCount;
    private EditText etPayDetail;
    private ImageView ivChoiseVipRight, ivChoiseVipLeft, ivDeletVip;
    private TextView tvVipName;
    private AllMemberListBean.DataBean.DataListBean mMemberInfoBack;
    private TextView tvOrderTime;
    private ImageView igOrderTime;
    /**
     * 快速消费
     */
    private TextView tvChoiseVip;
    private ImageView imgDeleteVip;
    private ImageView imgChoiseVip;
    private ImageView imgChoiseVipLeft;
    private RelativeLayout rLayoutOrder;
    private EditText edOrder;

    /**
     * 最低折扣
     */
    private double zuidiPercent;

    /**
     * 等级折扣
     */
    private double dengjiPercent;

    /**
     * 特价折扣
     */
    private double tejiaPercent;
    /**
     * 计算后的折扣百分比
     */
    private double mPercent;
    /**
     * 查询会员所有信息返回数据
     */
    private SingleVip memberInfoBean;
    /**
     * 最终计算后的百分比例
     */
    private double percent;
    /**
     * 商品消费折后金额
     */
    private double spxfmoneyafterdiscount = 0.00;
    /**
     * 保持优惠活动之前的值
     */
    private double flags = 0.00;
    /**
     * 修改折金额和数量的handler
     */
    private ChangeHandler changeHandler;
    /**
     * 结算完成跳转
     */
    private IntentHandler intentHandler;

    /**
     * 修改折后bean
     */
    public static List<GoodsCheckResponseByType.DataBean.DataListBean> ZHDataBean = new ArrayList<>();

    /**
     * 是否选择优惠活动
     */
    private boolean choisedActivities;

    private SingleVip singleVip;
    private Dialog datesDialog;

    private String mSmPayCode = "";//扫码返回条码
    private SaoMaPayPresntter presenter;
    private SaoMaPayView mView;
    private String barcodeResultGID;
    private Dialog mDialog;
    private ArrayList<String> rulesList = new ArrayList<>();
    private TimesRulesBean rulesBean;
    private Dialog chooseDialog;
    private String rulesGid;
    private int pribean = 0;
    private NfcAdapter mNFCAdapter;
    PendingIntent mPendingIntent;

    //活动优惠券列表
    private List<DiscountTypeBean.DataBean> mCouponList = new ArrayList<>();
    //会员优惠的金额,活动折在会员之后
    private double mBeforeCouponPrice = 0;
    //可用支付余额百分比
    private double mPercentMoney;
    //自动匹配优惠活动
    private boolean mAutoMatchingCoupon;
    //用户可用余额
    private double mUserBalance;

    //抹零类型
    private int mZeroType = -1;

    //抹零值
    private double mZeroMoney = 0.00, mZeroTotalMoney = 0.00, mCouponMoney = 0.00;

    //isKSXF
    private boolean mKSXF_modify_money_bool = false;
    //快速消费修改金额
    private double mKSXF_modify_money = 0.00, mKsxfFlagMoney = 0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getType();
        if (isSPXF || isHYCC) {//商品消费结算界面
            setContentView(R.layout.activity_ysl_goods_consume);
            ButterKnife.bind(this);
        } else {//原始界面
            setContentView(R.layout.activity_pay_confirm);
        }

        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            mPendingIntent =
                    PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        }
        initView();
        initSp();
        initData();
        setListener();
        if (mAutoMatchingCoupon) {
            getCouponList();
        }
    }

    private void getType() {
        getmIntent = getIntent();
        isKJSK = getmIntent.getBooleanExtra("isKJSK", false);//跨界收款
        isSPXF = getmIntent.getBooleanExtra("isSPXF", false);//商品消费
        isHYCC = getmIntent.getBooleanExtra("isHYCC", false);//会员充次
        isJCXF = getmIntent.getBooleanExtra("isJCXF", false);//计次消费
        isKSXF = getmIntent.getBooleanExtra("isKSXF", false);//快速消费
        isStaff = getmIntent.getBooleanExtra("isStaff", false);//员工提成
        isVipRechage = getmIntent.getBooleanExtra("isVipRechage", false);//会员充值
    }

    /**
     * @return ,
     * 计算折扣
     */
    private double getPercent() {
        double result1;
        double result;

        Li("等级折扣---" + dengjiPercent + "---最低折扣---" + zuidiPercent + "---特价折扣---" + tejiaPercent);


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
        Li("等级折扣---" + dengjiPercent + "---最低折扣---" + zuidiPercent + "---特价折扣---" + tejiaPercent);
        return result;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mNFCAdapter != null) {
            //允许隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }

    }


    /**
     * @param k    ，会员卡号
     * @param isKS ,true代表快速消费，false代表商品消费
     */
    private void postVip(String k, final boolean isKS) {
        //card = "";
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("AccurateOfCard", k);//AccurateOfCard
        params.put("VCH_Card", k);
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
                            memberInfoBean = CommonFun.JsonToObj(resurt, SingleVip.class);
                            if (memberInfoBean != null) {
                                mUserBalance = memberInfoBean.getData().getMA_AvailableBalance();
                                if (mPageFlag.equals("HYCC") && memberInfoBean.getData().getVG_IsCount() != 1) {
                                    new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.WARNING_TYPE)
                                            .setTitleText("提示")
                                            .setContentText("该会员不能充次,请前往等级管理进行相关设置！")
                                            .setConfirmText("了解")
                                            .show();
                                } else {
                                    mOrderType = 1;
                                    Message message = new Message();
                                    message.what = 2;
                                    message.obj = isKS;
                                    Bundle b = new Bundle();
                                    b.putSerializable("USER", memberInfoBean);
                                    message.setData(b);
                                    mHandler.sendMessage(message);
                                }
                            }
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(TAG, "onFailure: ");

            }
        });
    }


    @SuppressLint("WrongConstant")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5846 && resultCode == 2222) {
            try {
                mMemberInfoBack = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");
            } catch (NullPointerException e) {
            }
            if (mMemberInfoBack != null) {
                imgChoiseVip.setVisibility(View.INVISIBLE);//右边图标
                imgChoiseVipLeft.setVisibility(View.VISIBLE);//左边图标
                imgDeleteVip.setVisibility(View.VISIBLE);//删除会员图标
                mMemberName = mMemberInfoBack.getVIP_Name();
                mMemberCardNo = mMemberInfoBack.getVCH_Card();
                if (mMemberName != null) {
                    tvChoiseVip.setText(mMemberName);
                } else {
                    tvChoiseVip.setText(mMemberCardNo);
                }
                postVip(mMemberCardNo, true);
            }
        }
        if (requestCode == 888 && resultCode == 2222) {
            mMemberInfoBack = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");
            ivChoiseVipRight.setVisibility(View.INVISIBLE);//右边图标
            ivChoiseVipLeft.setVisibility(View.VISIBLE);//左边图标
            ivDeletVip.setVisibility(View.VISIBLE);//删除会员图标
            Li(requestCode + "==========mMemberInfoBack=====random:" + new Gson().toJson(mMemberInfoBack));
            if (mMemberInfoBack.getVIP_Name() != null && !"".equals(mMemberInfoBack.getVIP_Name())) {
                tvVipName.setText(mMemberInfoBack.getVIP_Name());
            } else {
                tvVipName.setText(mMemberInfoBack.getVCH_Card());
            }
            if (mMemberInfoBack != null) {
                mOrderType = 1;
                mMemberName = mMemberInfoBack.getVIP_Name();
                mMemberCardNo = mMemberInfoBack.getVCH_Card();
                postVip(mMemberCardNo, false);

            } else {
                mOrderType = 2;
                mMemberName = "散客";
                mMemberCardNo = "00000";
            }
            if (isSPXF || isHYCC) {
                initAdapter(ZHDataBean, isSPXF, changeHandler);
            }


        }
        if (resultCode == 1001) {
            calculateCoupon((DiscountTypeBean.DataBean) data.getSerializableExtra("type"));
//            isChoiceDiscount = true;
//            if (mDiscountBean != null) {
//
//                mGiveMoney = mGiveMoney - mDiscountBean.getGiveMoney();
////                mDiscountMoney = mDiscountMoney + mDiscountBean.getDiscountMoney();
////                etDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mDiscountMoney)));
//                if (mMemberInfoBack != null) {
//                    mGetPoints = mGetPoints - mDiscountBean.getGivePoint();
//                }
//            }
//            choisedActivities = true;
//
//            //获取优惠信息
//            mDataBean = (DiscountTypeBean.DataBean) data.getSerializableExtra("type");
//            if (mDataBean != null) {
//                tvDiscount.setText(mDataBean.getRP_Name());//显示优惠内容
//                mDiscountActivityGid = mDataBean.getGID();
//            }
////            mDiscountMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etDiscountMoney.getText().toString()));//折后金额
//            if (mConsumeType == 1) {
//                mDiscountBean = calculateRechargeDiscount(mMemberDiscountMoney);
//            }
//            if (mConsumeType == 2) {
//                mDiscountBean = calculateActivityDiscount(mMemberDiscountMoney);
//            }
//            mDiscountMoney = mMemberDiscountMoney - mDiscountBean.getDiscountMoney();
//            mGiveMoney = mDiscountBean.getGiveMoney();
//            if ("KSXF".equals(mPageFlag)) {
//                if (mMemberInfoBack != null && singleVip != null) {
//                    mGetPoints = mDiscountMoney * singleVip.getData().getVS_Value();
//                }
//            }
//            if (mDiscountBean.getGivePoint() > 0 && mMemberInfoBack != null) {//折扣大于0才积分，为0就不积分
//                mGetPoints = DoubleMathUtil.add(mGetPoints, mDiscountBean.getGivePoint());
//            }
//            mRechargeTotal = mRechargeTotal + mGiveMoney;
//            etDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mDiscountMoney)));
//            tvCostMoney.setText(Decima2KeeplUtil.stringToDecimal(mDiscountMoney + ""));
//            tvPonits.setText(Decima2KeeplUtil.stringToDecimal(mGetPoints + ""));
//            isChoiceDiscount = false;

        }
        //获取提成员工
        if (resultCode == 1002) {
            mStaffInfo = (List<ReportMessageBean.DataBean.EmplistBean>) data.getSerializableExtra("staff");
            if (mStaffInfo != null) {
                if (isKSXF) {
                    mStaffListGid.clear();
                    for (int i = 0; i < mStaffInfo.size(); i++) {
                        mStaffListGid.add(mStaffInfo.get(i).getGID());
                        if (i == mStaffInfo.size() - 1) {
                            mStaffName.append(mStaffInfo.get(i).getEM_Name());
                        } else {
                            mStaffName.append(mStaffInfo.get(i).getEM_Name() + "、");
                        }
                    }
                    tvEmployee.setText(mStaffName);
                    mStaffName.delete(0, mStaffName.length());//清空数据
                } else {
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
                    mGoodsBeanList.get(empos).setEmployeeValue(mStaffName + "");
                    mGoodsPayAdapter.notifyDataSetChanged();
                    mStaffName.delete(0, mStaffName.length());//清空数据
                }
            }
        }

        if (requestCode == 1 && resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            mSmPayCode = bundle.getString("result");
            mDialog = LoadingDialogUtil.createLoadingDialog(PayConfirmActivity.this, "支付中...", false);
            if (isHYCC) {
                barCode(20, mVipInflatCountGID);
            } else if (isSPXF) {
                barCode(10, mGoodsBeanGID);
            } else if (isKSXF) {
                barCode(60, mOrderGID);
            }

        }

        /**
         * 拉卡拉支付接收返回结果与参数
         */
        if (requestCode == PayLKL.RQ_LKLPAY) {
            if (resultCode == RESULT_OK) {
                if (data.getStringExtra("pay_tp") == null) {
                    mPayWayName = "刷卡支付";
                    mPayWayCode = "SKZF";
                } else {
                    //根据拉卡拉支付成功回调的参数判断是哪种支付方式
                    switch (data.getStringExtra("pay_tp")) {
                        //微信
                        case "1":
                            mPayWayName = "微信支付";
                            mPayWayCode = "WXZF";
                            break;
                        //支付宝
                        case "2":
                            mPayWayName = "支付宝支付";
                            mPayWayCode = "ZFBZF";
                            break;
                        //银联钱包
                        case "3":
                            mPayWayName = "刷卡支付";
                            mPayWayCode = "SKZF";
                            break;
                        //百度钱包
                        case "4":
                            mPayWayName = "百度钱包支付";
                            mPayWayCode = "BDQBZF";
                            break;
                        //京东钱包
                        case "5":
                            mPayWayName = "京东钱包支付";
                            mPayWayCode = "JDQBZF";
                            break;
                        //拉卡拉钱包
                        case "6":
                            mPayWayName = "拉卡拉钱包支付";
                            mPayWayCode = "LKLQBZF";
                            break;
                    }
                }
                payPost();
            }

            //拉卡拉刷卡支付取消
            if (resultCode == RESULT_CANCELED) {
                CustomToast.makeText(this, "支付取消", Toast.LENGTH_SHORT).show();
            }

            //拉卡拉刷卡支付失败
            if (resultCode == -2) {
                CustomToast.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        }
        //SUNMI扫码回调
        if (requestCode == START_SCAN) {

        }
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
            loadSwitch();
        } else {
            getSystemSwitch();
            loadOtherSwitch();
        }

    }

    private void loadSwitch() {
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
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mCashSwitch = true;
                    }
                }
                if ("102".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mBalanceSwitch = true;
                        if (mSwitchEntity.get(i).getSS_State() == 1 && mSwitchEntity.get(i).getSS_Value() != null) {
                            double a = Double.parseDouble(mSwitchEntity.get(i).getSS_Value() + "");
                            mPercentMoney = DoubleMathUtil.div(a, 100);
                        }
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
                if ("601".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        isDiscount = true;
                        if (isKSXF) {
                            etDiscountMoney.setEnabled(true);
                        }
                    } else {
                        isDiscount = false;
                        etDiscountMoney.setEnabled(false);
                    }
                }
                //员工提成
                if ("301".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        isStaff = true;
                        if (isKSXF) {
                            rlEmployee.setVisibility(View.VISIBLE);
                        }
                    } else {
                        isStaff = false;
                        if (isKSXF) {
                            rlEmployee.setVisibility(View.GONE);
                        }
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
                if (isHYCC) {
                    //计次规则
                    if ("212".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            istimesrule = true;
                            rlPayConfirmRule.setVisibility(View.VISIBLE);
                        } else {
                            istimesrule = false;
                            rlPayConfirmRule.setVisibility(View.GONE);
                        }
                    }
                }
                //自动匹配优惠活动
                if ("216".equals(mSwitchEntity.get(i).getSS_Code())) {
                    if (mSwitchEntity.get(i).getSS_State() == 1) {
                        mAutoMatchingCoupon = true;
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
        mIntegralSwitch = mPasswordSwitch = mZfbSwitch = mWXSwitch = mSmSwitch = mOtherSwitch = false;
//                etDiscountMoney.setInputType(InputType.TYPE_NULL);
        if (isKSXF) {
            rlEmployee.setVisibility(View.GONE);
        }

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
                CustomToast.makeText(PayConfirmActivity.this, "获取系统开关失败" + msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(PayConfirmActivity.this, HttpAPI.API().PRE_LOAD, callBack);
    }

    /**
     * @param discountBean ,
     * @param money        ,
     */
    private void compareXiaoFei(DiscountBean discountBean, double money) {
        int consumeType = 0;
        if (mDataBean.getRP_GiveMoney() >= 0) {//增加
            consumeType = 2;
        } else if (mDataBean.getRP_ReduceMoney() >= 0) {//减少
            consumeType = 1;
        } else if (mDataBean.getRP_Discount() > 0) {//打折
            consumeType = 3;
        }
        if (mDataBean.getRP_ISDouble() == 1) {//优惠是否翻倍，1翻倍，0不翻倍
            if (mDataBean.getRP_RechargeMoney() > 0) {
                int beishu = (int) (money / mDataBean.getRP_RechargeMoney());
                mActivityPoint = Math.floor(beishu * mDataBean.getRP_GivePoint());
                discountBean.setGivePoint(mActivityPoint);
            } else {
                mActivityPoint = 0;
                discountBean.setGivePoint(0);
            }
            if (consumeType == 2) {
                discountBean.setDiscountMoney(0);
                discountBean.setGiveMoney(Math.floor(money / mDataBean.getRP_RechargeMoney()) * mDataBean.getRP_GiveMoney());
            } else if (consumeType == 1) {
                discountBean.setDiscountMoney(Math.floor(money / mDataBean.getRP_RechargeMoney()) * mDataBean.getRP_ReduceMoney());
                discountBean.setGiveMoney(0);
            } else if (consumeType == 3) {
                discountBean.setGiveMoney(0);
                discountBean.setDiscountMoney(Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf((10 - mDataBean.getRP_Discount()) * money * 0.1))));
            } else {
                discountBean.setDiscountMoney(0);
                discountBean.setGiveMoney(0);
            }
        } else if (mDataBean.getRP_ISDouble() == 0) {//优惠不翻倍

            if (consumeType == 2) {
                discountBean.setDiscountMoney(0);
                if (money - mDataBean.getRP_RechargeMoney() >= 0) {
                    mActivityPoint = mDataBean.getRP_GivePoint();
                    discountBean.setGivePoint(mDataBean.getRP_GivePoint());
                    discountBean.setGiveMoney(mDataBean.getRP_GiveMoney());
                } else {
                    mActivityPoint = 0;
                    discountBean.setGiveMoney(0);
                    discountBean.setGivePoint(0);
                }
            } else if (consumeType == 1) {
                discountBean.setGiveMoney(0);
                if (money - mDataBean.getRP_RechargeMoney() >= 0) {
                    mActivityPoint = mDataBean.getRP_GivePoint();
                    discountBean.setGivePoint(mDataBean.getRP_GivePoint());
                    discountBean.setDiscountMoney(mDataBean.getRP_ReduceMoney());
                } else {
                    mActivityPoint = 0;
                    discountBean.setDiscountMoney(0);
                    discountBean.setGivePoint(0);
                }
            } else if (consumeType == 3) {
                discountBean.setGiveMoney(0);
                if (money - mDataBean.getRP_RechargeMoney() >= 0) {
                    mActivityPoint = mDataBean.getRP_GivePoint();
                    discountBean.setGivePoint(mDataBean.getRP_GivePoint());
                    discountBean.setDiscountMoney(Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf((10 - mDataBean.getRP_Discount()) * money * 0.1))));
                } else {
                    mActivityPoint = 0;
                    discountBean.setDiscountMoney(0);
                    discountBean.setGivePoint(0);
                }
            } else {
                mActivityPoint = 0;
                discountBean.setDiscountMoney(0);
                discountBean.setGiveMoney(0);
                discountBean.setGivePoint(0);
            }

        }
    }

    /**
     * 计算消费活动优惠
     */
    private DiscountBean calculateActivityDiscount(double money) {
        DiscountBean discountBean = new DiscountBean();
        int consumeType = mDataBean.getRP_ValidType();//mDataBean.getRP_ValidType()：期限类型 1固定时间断2按每周3按每月4会员生日当天
        Li(mMemberInfoBack + "========mMemberInfoBack=============111====933 random:" + consumeType);
        if (consumeType == 0) {//活动永久有效
            compareXiaoFei(discountBean, money);
        } else if (consumeType == 1) {//固定时间段
            if (!DataUtils.isOverTime(mDataBean.getRP_ValidStartTime()) && DataUtils.isOverTime(mDataBean.getRP_ValidEndTime())) {
                compareXiaoFei(discountBean, money);
            }
        } else if (consumeType == 2) {//按每周设置有效
            if (mDataBean.getRP_ValidWeekMonth().contains(DataUtils.getW())) {//在活动设置的星期内有效
                compareXiaoFei(discountBean, money);
            }
        } else if (consumeType == 3) {//活动按月内设置有效
            if (mDataBean.getRP_ValidWeekMonth().contains(DataUtils.getD())) {
                compareXiaoFei(discountBean, money);
            }
        } else if (consumeType == 4) {
            if (mMemberInfoBack == null) {
                CustomToast.makeText(this, "抱歉！会员生日活动只对会员有效", Toast.LENGTH_SHORT).show();
                discountBean.setDiscountMoney(0);
                discountBean.setGiveMoney(0);
                Li("=========================933 random:");
                tvDiscount.setText("");
                mDiscountActivityGid = "";
                return discountBean;
            } else if (!TextUtils.isEmpty(mMemberInfoBack.getVIP_Birthday()) && mMemberInfoBack.getVIP_Birthday().substring(5, 10).equals(DataUtils.getDataTime().substring(5, 10))) {
                compareXiaoFei(discountBean, money);
            } else {
                CustomToast.makeText(this, "抱歉！会员生日活动只对会员有效", Toast.LENGTH_SHORT).show();
                discountBean.setDiscountMoney(0);
                discountBean.setGiveMoney(0);
                Li("=========================943 random:");
                tvDiscount.setText("");
                mDiscountActivityGid = "";
                return discountBean;
            }
        } else {
            discountBean.setDiscountMoney(0);
            discountBean.setGiveMoney(0);

        }
        discountBean.setGivePoint(mActivityPoint);
        return discountBean;
    }


    /**
     * 计算会员充值活动优惠
     */
    private DiscountBean calculateRechargeDiscount(double money) {
        DiscountBean discountBean = new DiscountBean();
        int consumeType = mDataBean.getRP_ValidType();//mDataBean.getRP_ValidType()：期限类型 1固定时间断2按每周3按每月4会员生日当天
        mActivityPoint = 0;
        if (consumeType == 0) {//活动永久有效
            compareXiaoFei(discountBean, money);
        } else if (consumeType == 1) {//固定时间段
            if (!DataUtils.isOverTime(mDataBean.getRP_ValidStartTime()) && DataUtils.isOverTime(mDataBean.getRP_ValidEndTime())) {
                compareXiaoFei(discountBean, money);
            }
        } else if (consumeType == 2) {//按每周设置有效
            if (mDataBean.getRP_ValidWeekMonth().contains(DataUtils.getW())) {//在活动设置的星期内有效
                compareXiaoFei(discountBean, money);
            }
        } else if (consumeType == 3) {//活动按月内设置有效
            if (mDataBean.getRP_ValidWeekMonth().contains(DataUtils.getD())) {
                compareXiaoFei(discountBean, money);
            }
        } else if (consumeType == 4) {
            if (mMemberInfoBack == null) {
                CustomToast.makeText(this, "抱歉！会员生日活动只对会员有效", Toast.LENGTH_SHORT).show();
                discountBean.setDiscountMoney(0);
                discountBean.setGiveMoney(0);
                Li("=========================984 random:");
                tvDiscount.setText("");
                mDiscountActivityGid = "";
                return discountBean;
            } else if (!TextUtils.isEmpty(mMemberInfoBack.getVIP_Birthday()) && mMemberInfoBack.getVIP_Birthday().substring(5, 10).equals(DataUtils.getDataTime().substring(5, 10))) {//活动按会员生日设置有效
                compareXiaoFei(discountBean, money);
            } else {
                CustomToast.makeText(this, "抱歉！会员生日活动只对会员有效", Toast.LENGTH_SHORT).show();
                discountBean.setDiscountMoney(0);
                discountBean.setGiveMoney(0);
                Li("=========================994 random:");
                tvDiscount.setText("");
                mDiscountActivityGid = "";
                return discountBean;
            }
        } else {
            discountBean.setDiscountMoney(0);
            discountBean.setGiveMoney(0);
        }
        return discountBean;
    }

    /**
     * 初始化控件
     */
    private void initView() {
        tvOrderMoney = (TextView) findViewById(R.id.tv_pay_confirm_receivable);
        tvDiscount = (TextView) findViewById(R.id.tv_pay_confirm_discount);
        rlDiscount = (RelativeLayout) findViewById(R.id.rl_pay_confirm_discount);
        viewPayConfirmDiscount = findViewById(R.id.view_pay_confirm_discount);
        etDiscountMoney = (EditText) findViewById(R.id.et_pay_confirm_discount_money);
        tvPonits = (TextView) findViewById(R.id.tv_pay_confirm_get_points);
        btnSubmit = findViewById(R.id.btn_pay_confirm_submit);
        tvCostMoney = findViewById(R.id.tv_constmoney);
        cbShortMessage = findViewById(R.id.cb_short_message);
        cbPrint = findViewById(R.id.cb_print);
        etPayDetail = findViewById(R.id.et_constom_pay_detail);
        tvOrderTime = findViewById(R.id.et_add_member_order_date);
        igOrderTime = findViewById(R.id.iv_add_member_order_date);
        tvOrderTime.setText(DateTimeUtil.getReallyTimeNow());
        edOrder = findViewById(R.id.tv_pay_confirm_order);

        if (isKSXF) {
            tvEmployee = (TextView) findViewById(R.id.tv_pay_confirm_employee);
            rlEmployee = (RelativeLayout) findViewById(R.id.rl_pay_confirm_employee);
            tvChoiseVip = findViewById(R.id.tv_choose_member);
            imgChoiseVip = findViewById(R.id.iv_choise_vip_right);
            imgDeleteVip = findViewById(R.id.iv_delete_vip);
            imgChoiseVipLeft = findViewById(R.id.iv_choise_vip_left);
            rLayoutOrder = findViewById(R.id.r_layout_order);
            rLayoutOrder.setVisibility(View.VISIBLE);
            tvChoiseVip.setVisibility(View.VISIBLE);
            imgChoiseVip.setVisibility(View.VISIBLE);
            mOrderNo = CreateOrder.createOrder("KS");
            edOrder.setText(mOrderNo);
        }

        if (isSPXF || isHYCC) {//商品消费的界面多出来东西，单独初始化
            if (isHYCC) {
                lrOverTime.setVisibility(View.VISIBLE);
                rlPayConfirmRule.setVisibility(View.VISIBLE);
                mOrderNo = CreateOrder.createOrder("CC");
            } else {
                lrOverTime.setVisibility(View.GONE);
                rlPayConfirmRule.setVisibility(View.GONE);
                mOrderNo = CreateOrder.createOrder("SP");
            }
            edOrder.setText(mOrderNo);
            recyclerView = findViewById(R.id.recycler_view);
            cbUpDown = findViewById(R.id.cb_down_up);
            lTotalCost = findViewById(R.id.l_med);
            lOderMoney = findViewById(R.id.l_oder_moenty);
            ivChoiseVipRight = findViewById(R.id.iv_choise_vip_right);//没有选择你这会员时，最右边的图标
            ivChoiseVipLeft = findViewById(R.id.iv_choise_vip_left);//会员名字显示时左边的图标
            ivDeletVip = findViewById(R.id.iv_delete_vip);//删除会员图标
            tvVipName = findViewById(R.id.tv_choose_member);//筛选后会员名字
            tvCost = findViewById(R.id.tv_cost);//订单金额
            tvPonits = (TextView) findViewById(R.id.tv_pay_confirm_get_points);
            tvCount = findViewById(R.id.tv_count);//展示商品的数量


            lOderMoney.setVisibility(View.GONE);
            //  ivChoiseVipRight.setOnClickListener(this);
            tvVipName.setOnClickListener(this);
            ivChoiseVipRight.setOnClickListener(this);
            ivDeletVip.setOnClickListener(this);
            cbUpDown.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        recyclerView.setVisibility(View.GONE);
                        lTotalCost.setVisibility(View.GONE);
                        lOderMoney.setVisibility(View.VISIBLE);
                        cbUpDown.setText("展 开");
                    } else {
                        recyclerView.setVisibility(View.VISIBLE);
                        lTotalCost.setVisibility(View.VISIBLE);
                        lOderMoney.setVisibility(View.GONE);
                        cbUpDown.setText("收 起");
                    }
                }
            });
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
                            CustomToast.makeText(PayConfirmActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
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
     * 根据打印开关是否打开，设置checkbox
     */
    private void setCbPrint() {
        try {
            if (YSLUtils.getPrints() != null) {

                if (YSLUtils.getPrints().getPS_IsEnabled() != 1) {
                    cbPrint.setChecked(false);
                    Print = false;
                    pribean = 0;
                } else {
                    cbPrint.setChecked(true);
                    Print = true;
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
     * 初始化数据
     */
    @SuppressLint("HandlerLeak")
    private void initData() {
        changeHandler = new ChangeHandler();
        intentHandler = new IntentHandler();
        mStaffName = new StringBuilder();
//        mDiscountBean = new DiscountBean();
        mIntent = getIntent();
        mPageFlag = mIntent.getStringExtra(MyApplication.PAGE_FLAG);
        MDZZ = mIntent.getStringExtra("MDZZ");
        /**从会员管理进入商品消费,用户信息 20191019 cy */
        if (!"".equals(MDZZ) && MDZZ != null) {
            mMemberInfoBack = (AllMemberListBean.DataBean.DataListBean) mIntent.getSerializableExtra("MDZZ_YSL_INFO");
        }
        /********************************* 20191019 cy */

        setCbPrint();
        if (mPageFlag != null) {
            //快速消费
            if ("KSXF".equals(mPageFlag)) {
                setCbShortMessage("010");
//                etDiscountMoney.setInputType(InputType.TYPE_NULL);
                mConsumeType = 2;
                mMemberCardNo = memberInfoBean == null ? "00000" : memberInfoBean.getData().getVCH_Card();
                mMemberName = memberInfoBean == null ? "散客" : memberInfoBean.getData().getVIP_Name();
//                mOrderNo = mIntent.getStringExtra(MyApplication.ORDER_NO);//订单号
                mMemberDiscountMoney = mDiscountMoney = mIntent.getDoubleExtra(MyApplication.SPEND_MONEY, 0.00);//折后金额
                mOrderMoney = mIntent.getDoubleExtra("order_money", 0.00);//订单金额
                mKSXF_modify_money = mOrderMoney;
//                calculatePoint(mDiscountMoney);//计算积分
            }
            //商品消费
            if ("SPXF".equals(mPageFlag) || "HYCC".equals(mPageFlag)) {
                if ("HYCC".equals(mPageFlag)) {
                    setCbShortMessage("003");
                    queryrules();//获取计次规则
//                    rlDiscount.setVisibility(View.GONE);//屏蔽掉优惠活动
                } else {
                    setCbShortMessage("011");
                }
                mConsumeType = 2;
                Bundle bundle = getIntent().getBundleExtra("bundle");
                ArrayList list = bundle.getParcelableArrayList("list");
                mGoodsBeanList = (List<GoodsCheckResponseByType.DataBean.DataListBean>) list.get(0);
                mIntegralScalingList = (List<IntegralScalingBean>) mIntent.getSerializableExtra("scaling");
                mGradePoint = Double.parseDouble(mIntent.getStringExtra("point"));
                mOrderMoney = Double.parseDouble(mIntent.getStringExtra("order_money")); //订单总额
                mMemberDiscountMoney = Double.parseDouble(mIntent.getStringExtra("discount_money"));
                mMemberCardNo = mIntent.getStringExtra("card");
                mMemberName = mIntent.getStringExtra("memberName") != null ? mIntent.getStringExtra("memberName") : "散客";

//                mOrderNo = mIntent.getStringExtra("order_num");
                mOrderType = Integer.parseInt(mIntent.getStringExtra("order_type"));
                tvCount.setText("共" + mGoodsBeanList.size() + "种商品");
                Log.i(TAG, "initData: " + mOrderType);
                mGetPoints = mGradePoint;
                if (isSPXF || isHYCC) {
                    initAdapter(mGoodsBeanList, isSPXF, changeHandler);
                    tvCost.setText("¥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));
                }
                if (!mMemberCardNo.equals("00000")) {
                    ivChoiseVipRight.setVisibility(View.INVISIBLE);//右边图标
                    ivChoiseVipLeft.setVisibility(View.VISIBLE);//左边图标
                    ivDeletVip.setVisibility(View.VISIBLE);//删除会员图标
                    if (mMemberName != null && !"".equals(mMemberName)) {
                        tvVipName.setText(mMemberName);
                    } else {
                        tvVipName.setText(mMemberCardNo);
                    }
                    postVip(mMemberCardNo, false);
                }


//                checkMemberInfo(mMemberCardNo);
            }

//            //会员充值
//            if ("HYCZ".equals(mPageFlag)) {
//                setCbShortMessage(1);
////                etDiscountMoney.setInputType(InputType.TYPE_NULL);
//                rlDiscount.setVisibility(View.GONE);
//                mConsumeType = 1;
//                mMemberInfo = (MemberInfoBean.DataBean) mIntent.getSerializableExtra("VIP_INFO");
//                mMemberCardNo = mMemberInfo == null ? "00000" : mMemberInfo.getVCH_Card();
//                mMemberName = mMemberInfo == null ? "散客" : mMemberInfo.getVIP_Name();
//                mOrderNo = mIntent.getStringExtra(MyApplication.ORDER_NO);//订单号
//                mRechargeMoney = mIntent.getDoubleExtra("CZ_MONEY", 0.00);//充值金额
//                mGiveMoney = mIntent.getDoubleExtra("ZS_MONEY", 0.00);//赠送金额
//                mRechargeTotal = mIntent.getDoubleExtra("TOTAL_MONEY", 0.00);//充值合计
//                mPercent = mIntent.getFloatExtra("percent", 1);
//                mOrderMoney = mRechargeMoney;
//                mDiscountMoney = mRechargeMoney;
//                mInitDiscountMoney = mDiscountMoney;
//                mGetPoints = mIntent.getDoubleExtra("Integral", 0);
//                mInitPoint = mGetPoints;
//                mDiscountActivityGid = getIntent().getStringExtra("CC_GID");
//            }

        }
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Li("=====1243=====msg.what============random:" + msg.what);
                switch (msg.what) {
                    case 1:
                        // btnSubmit.setText("收款：¥" + Decima2KeeplUtil.stringToDecimal(mDiscountMoney + ""));
                        //                        etDiscountMoney.setText(mDiscountMoney + "");
                        tvCostMoney.setText(Decima2KeeplUtil.stringToDecimal(mDiscountMoney + ""));
                        break;
                    case 2:
                        singleVip = (SingleVip) msg.getData().getSerializable("USER");
                        boolean isKS = false;
                        try {
                            isKS = (boolean) msg.obj;
                        } catch (Exception e) {
                            isKS = false;
                        }
                        if (isKS) {//非商品消费，查询会员
                            discount = singleVip.getData().getDS_Value();
                            if (discount > 0 && discount < 1) {
                                Li("======" + discount);
                                mDiscountMoney = mMemberDiscountMoney = ksxfmDiscountMoney = mDiscountMoney * discount;
                                etDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(mMemberDiscountMoney + ""));
                                tvCostMoney.setText(Decima2KeeplUtil.stringToDecimal(mMemberDiscountMoney + ""));
                                Li("===非商品消费=====mHandler=====mDiscountMoney==random:" + mDiscountMoney);
                            }
                            mGetPoints = Double.parseDouble(etDiscountMoney.getText().toString()) * singleVip.getData().getVS_Value();
                            tvPonits.setText(Decima2KeeplUtil.stringToDecimal(mGetPoints + ""));

                        } else {//商品消费，查询会员
                            jisuanPrice();
                            jisuanpoint(mGoodsBeanList);
                            Li("===商品消费=====mHandler=======random:");
                        }

                        if (mAutoMatchingCoupon) {
                            getCouponList();
                        } else {
                            mDiscountActivityGid = "";
                            mDiscountBean = null;
                            tvDiscount.setText("");
                        }
                        break;
                }
            }
        };


        presenter = new SaoMaPayPresntter(this);
        mView = new SaoMaPayView() {
            @Override
            public void barCodeSuccess(BarCodePayBean entity) {
                Li("===商品消费=====mHandler=======random:" + new Gson().toJson(entity));
                barcodeResultGID = entity.getData().getGID();
                if (mDialog != null) {
                    LoadingDialogUtil.closeDialog(mDialog);
                }

                if (isKSXF) {
                    fastPayStepTwo();
                } else if (isHYCC) {
                    vipInflateCountStepTwo();
                } else if (isSPXF) {
                    goodsShopStepTwo();
                }
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
                    CustomToast.makeText(PayConfirmActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void barCodeFailString(String responseString) {
                if (mDialog != null) {
                    LoadingDialogUtil.closeDialog(mDialog);
                }
                CustomToast.makeText(PayConfirmActivity.this, responseString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void querPaySuccess(QuerPayBean entity) {
                if (entity.isSuccess()) {
                    if (mDialog != null) {
                        LoadingDialogUtil.closeDialog(mDialog);
                    }
                    if (isKSXF) {
//                        fastPayStepTwo();
                        fastPayStepTwoComplete(mOrderGID);
                    } else if (isHYCC) {
//                        vipInflateCountStepTwo();
                        vipInflateCountStepTwoComplete(mVipInflatCountGID);
                    } else if (isSPXF) {
//                        goodsShopStepTwo();
                        goodsShopStepTwoComplete(mGoodsBeanGID);
                    }

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
                    CustomToast.makeText(PayConfirmActivity.this, YSLUtils.payResult(entity.getCode()), Toast.LENGTH_SHORT).show();
                } else {
                    if (mDialog != null) {
                        LoadingDialogUtil.closeDialog(mDialog);
                    }
                    CustomToast.makeText(PayConfirmActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }
        };
        presenter.attachView(mView);

    }

    private void jisuanPrice() {

        //计算商品消费折后金额
        for (int i = 0; i < singleVip.getData().getVGInfo().size(); i++) {//会员信息中带有等级折扣的集合
            for (int j = 0; j < mGoodsBeanList.size(); j++) {//所选商品种类的数量，不等于所选商品数量；
                if (mGoodsBeanList.get(j).getPM_IsService() != 3) {//非服务商品，服务商品原价
                    if ("HYCC".equals(mPageFlag) && mGoodsBeanList.get(j).getPM_IsService() == 0) {//会员充次套餐
                        if (mGoodsBeanList.get(j).getPM_IsDiscount() == 1) {
                            mGoodsBeanList.get(j).setEachMoney(Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(mGoodsBeanList.get(j).getPM_UnitPrice() * mGoodsBeanList.get(j).getPM_MinDisCountValue()))));
                            mGoodsBeanList.get(j).setHaveDengjiMoney(true);
                        } else {
                            mGoodsBeanList.get(j).setHaveDengjiMoney(false);
                        }
                        mGoodsBeanList.get(j).setZHMoney(Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(mGoodsBeanList.get(j).getEachMoney()))) * mGoodsBeanList.get(j).getNum());
                        mGoodsBeanList.get(j).setPercent(mGoodsBeanList.get(j).getPM_MinDisCountValue());

                    }
                    if (singleVip.getData().getVGInfo().get(i).getPT_GID().equals(mGoodsBeanList.get(j).getPT_ID())) {
                        if ((mGoodsBeanList.get(j).getPM_SpecialOfferValue() == 0 || mGoodsBeanList.get(j).getPM_SpecialOfferValue() == 1) && mGoodsBeanList.get(j).getPM_SpecialOfferMoney() == -1) {//没有特价折扣的时候（特价折扣在选择商品时已经计算）
//                            if (mGoodsBeanList.get(j).getPM_MemPrice() != null && !mGoodsBeanList.get(j).getPM_MemPrice().equals("0.0") && !mGoodsBeanList.get(j).getPM_MemPrice().equals("0")) {
                            //会员价可以为0
                            if (mGoodsBeanList.get(j).getPM_MemPrice() != null && !mGoodsBeanList.get(j).getPM_MemPrice().equals("")) {
                                mGoodsBeanList.get(j).setPercent(-1);//有会员价,在会员价存在的情况下就选择会员价
                                mGoodsBeanList.get(j).setEachMoney(Double.parseDouble(mGoodsBeanList.get(j).getPM_MemPrice()));
                                mGoodsBeanList.get(j).setZHMoney(Double.parseDouble(mGoodsBeanList.get(j).getPM_MemPrice()) * mGoodsBeanList.get(j).getNum());
                                mGoodsBeanList.get(j).setHaveDengjiMoney(false);
                            } else {

                                if (singleVip.getData().getVG_IsDiscount() == 1 && mGoodsBeanList.get(j).getPM_IsDiscount() == 1) {//等级折扣开启
                                    dengjiPercent = singleVip.getData().getVGInfo().get(i).getPD_Discount() / 100.0;//等级折扣取值（0,100），所以这里要除以100
                                    if (mGoodsBeanList.get(j).getPM_IsDiscount() == 1) {//商品折扣开启，比较最低折扣
                                        zuidiPercent = mGoodsBeanList.get(j).getPM_MinDisCountValue();
                                        percent = getPercent();
                                        if (zuidiPercent == 0) {
                                            mGoodsBeanList.get(j).setHaveDengjiMoney(true);
                                        } else {
                                            mGoodsBeanList.get(j).setHaveDengjiMoney(true);
                                        }
                                    } else {
                                        percent = dengjiPercent;//没有最低折扣直接等于等级折扣
                                        mGoodsBeanList.get(j).setHaveDengjiMoney(true);
                                        mGoodsBeanList.get(j).setDengjipercen(percent);
                                    }
                                    mGoodsBeanList.get(j).setEachMoney(Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(mGoodsBeanList.get(j).getPM_UnitPrice() * percent))));
                                    mGoodsBeanList.get(j).setZHMoney(Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(mGoodsBeanList.get(j).getPM_UnitPrice() * percent))) * mGoodsBeanList.get(j).getNum());
                                    mGoodsBeanList.get(j).setPercent(percent);

                                } else {//等级折扣关闭
                                    dengjiPercent = 0;//等级折扣关闭
                                    mGoodsBeanList.get(j).setEachMoney(mGoodsBeanList.get(j).getPM_UnitPrice());
                                    mGoodsBeanList.get(j).setPercent(1);
                                    mGoodsBeanList.get(j).setHaveDengjiMoney(false);
                                }
                                if (percent == 0 || percent == 1) {
                                    mGoodsBeanList.get(j).setPercent(1);
                                }
                            }
                        }
                    }
                } else {
                    if (mGoodsBeanList.get(j).getPM_MemPrice() != null && !mGoodsBeanList.get(j).getPM_MemPrice().equals("")) {
                        mGoodsBeanList.get(j).setEachMoney(Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(mGoodsBeanList.get(j).getPM_MemPrice()))));
                        mGoodsBeanList.get(j).setZHMoney(Double.parseDouble(Decima2KeeplUtil.stringToDecimal(String.valueOf(mGoodsBeanList.get(j).getPM_MemPrice()))) * mGoodsBeanList.get(j).getNum());
                        mGoodsBeanList.get(j).setPercent(1);
                        mGoodsBeanList.get(j).setHaveDengjiMoney(true);
                    }
                }
            }
        }

        //计算商品消费折后总金额
        spxfmoneyafterdiscount = 0.00;
        for (int y = 0; y < mGoodsBeanList.size(); y++) {
            spxfmoneyafterdiscount += mGoodsBeanList.get(y).getEachMoney() * mGoodsBeanList.get(y).getNum();
        }

        mDiscountMoney = mMemberDiscountMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(spxfmoneyafterdiscount + ""));
        etDiscountMoney.setText(mMemberDiscountMoney + "");
        initAdapter(mGoodsBeanList, isSPXF, changeHandler);


        Li(mMemberDiscountMoney + "===商品消费=====mHandler=====折后==random:" + mDiscountMoney);
    }

    private void jisuanpoint(List<GoodsCheckResponseByType.DataBean.DataListBean> GoodsBeanList) {
        //商品积分计算
        mGetPoints = 0;
        for (int k = 0; k < GoodsBeanList.size(); k++) {
            if (GoodsBeanList.get(k).getPM_IsPoint() == 0 || GoodsBeanList.get(k).getPM_IsPoint() == 3) {//0没有设置积分规则，3本商品不计分
                GoodsBeanList.get(k).setEachPoint(0);
            } else if (GoodsBeanList.get(k).getPM_IsPoint() == 2) {//本商品按固定积分
                GoodsBeanList.get(k).setEachPoint(GoodsBeanList.get(k).getPM_FixedIntegralValue());
            } else if (GoodsBeanList.get(k).getPM_IsPoint() == 1) {//本商品按等级积分
                Li("=====getVG_IsIntegral=====random:" + singleVip.getData().getVG_IsIntegral());
                if (singleVip.getData().getVG_IsIntegral() == 0) {//会员等级积分开关没有有打开
                    GoodsBeanList.get(k).setEachPoint(0);
                } else if (singleVip.getData().getVG_IsIntegral() == 1) {
                    Li("=====aaa=====random:" + new Gson().toJson(singleVip.getData().getVGInfo()));
                    for (int m = 0; m < singleVip.getData().getVGInfo().size(); m++) {//所选商品种类的数量，不等于所选商品数量；
//                                            for (int n = 0; n < mGoodsBeanList.size(); n++) {

                        if (singleVip.getData().getVGInfo().get(m).getPT_GID().equals(GoodsBeanList.get(k).getPT_ID())) {
                            double bl = singleVip.getData().getVGInfo().get(m).getVS_CMoney();
                            if (bl != 0) {
                                if (GoodsBeanList.get(k).getPM_MemPrice() != null) {
                                    double memprice = Double.parseDouble(GoodsBeanList.get(k).getPM_MemPrice());
                                    if (memprice < GoodsBeanList.get(k).getEachMoney()) {
//                                                            double fb = YSLUtils.siOutFiveIn(memprice / bl);
                                        double fb = memprice / bl;
                                        GoodsBeanList.get(k).setEachPoint(fb);
                                    } else {
                                        double fb = GoodsBeanList.get(k).getEachMoney() / bl;
                                        GoodsBeanList.get(k).setEachPoint(fb);
                                    }
                                } else {
                                    double fb = GoodsBeanList.get(k).getEachMoney() / bl;
                                    GoodsBeanList.get(k).setEachPoint(fb);
                                }
                            }
                        }
//                                            }
                    }
                }
            }
        }

        //计算商品消费获得总积分
        for (int x = 0; x < GoodsBeanList.size(); x++) {
            double onepoint = GoodsBeanList.get(x).getEachPoint() * GoodsBeanList.get(x).getNum();
            mGetPoints += onepoint;
        }


        Li("=========================1231231231231 random:");
        tvPonits.setText(Decima2KeeplUtil.stringToDecimal(mGetPoints + ""));

    }

    private void initAdapter(List<GoodsCheckResponseByType.DataBean.DataListBean> goodsBeanList, boolean isspxf, ChangeHandler changeHandler) {

        if (mGoodsPayAdapter != null) {
            mGoodsPayAdapter = null;
        }
        mGoodsPayAdapter = new GoodsPayAdapter(this, goodsBeanList, isspxf, changeHandler, isDiscount, PayConfirmActivity.this, PayConfirmActivity.this, isStaff);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setSmoothScrollbarEnabled(false);
        recyclerView.setLayoutManager(manager);
        mGoodsPayAdapter.setHasStableIds(true);
        recyclerView.setAdapter(mGoodsPayAdapter);
    }

    /**
     * 判断是会员消费还是散客消费
     *
     * @param cardNo
     */
    private void judgeOrderType(String cardNo) {
        if ("00000".equals(cardNo) || cardNo == null || cardNo.isEmpty()) {
            mOrderType = 2;//散客
        } else {
            mOrderType = 1;//会员
        }
    }

    /**
     * 设置监听
     */
    private void setListener() {
        tvOrderMoney.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));
        etDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mDiscountMoney)));
        etDiscountMoney.setSelection(etDiscountMoney.getText().length());
        tvPonits.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mGetPoints)));
        Li("=========================1535 random:");
        tvCostMoney.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mDiscountMoney)));

        if (isKSXF) {//商品消费和其它消费来自不同的activity-xml，所以这里要判断下，是否是来自快速消费的跳转，如果是就设置监听，不是就不设置，不判断的话，会报空指针，前面初始化View也做了判断，
            tvChoiseVip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDiscountMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString()) + "");
                    etDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString() + ""));
                    Li("=========================1544 random:");
                    tvCostMoney.setText(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString() + ""));
                    tvDiscount.setText("");
                    mDiscountBean = null;
                    /** 清空优惠信息 */
                    mDataBean = null;
                    mDiscountActivityGid = "";
                    tvPonits.setText("0");
                    /*************/
                    etDiscountMoney.clearFocus();
                    mKSXF_modify_money = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString()) + "");
                    Intent intent = new Intent(PayConfirmActivity.this, CheckMemberInfoActivity.class);
                    startActivityForResult(intent, 5846);
                }
            });
            imgDeleteVip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvChoiseVip.setText("选择会员");
                    mMemberInfoBack = null;
                    mDiscountBean = null;
                    mMemberName = "";
                    mMemberCardNo = "";
                    mDiscountActivityGid = "";
                    imgChoiseVip.setVisibility(View.VISIBLE);//右边图标
                    imgDeleteVip.setVisibility(View.INVISIBLE);//删除会员图标
                    mDiscountMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString()) + "");
                    // CustomToast.makeText(PayConfirmActivity.this, "如果选择了优惠活动，请重新选择！", Toast.LENGTH_SHORT).show();
                    etDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString() + ""));
                    Li("=========================1566 random:");
                    tvCostMoney.setText(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString() + ""));
                    mGetPoints = 0;
                    mActivityPoint = 0;
                    Li("=========================22222 random:");
                    tvPonits.setText(Decima2KeeplUtil.stringToDecimal(mGetPoints + ""));
                    tvDiscount.setText("");
                }
            });
            imgChoiseVip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDiscountMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString()) + "");
                    mGetPoints = 0;
                    //  CustomToast.makeText(PayConfirmActivity.this, "如果选择了优惠活动，请重新选择！", Toast.LENGTH_SHORT).show();
                    etDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString() + ""));
                    Li("=========================1581 random:");
                    tvCostMoney.setText(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString() + ""));
                    tvPonits.setText(Decima2KeeplUtil.stringToDecimal(mGetPoints + ""));
                    tvDiscount.setText("");
                    /** 清空优惠信息 */
                    mDataBean = null;
                    mDiscountActivityGid = "";
                    tvPonits.setText("0");
                    /*************/
                    etDiscountMoney.clearFocus();
                    mKSXF_modify_money = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString()) + "");
                    Intent intent = new Intent(PayConfirmActivity.this, CheckMemberInfoActivity.class);
                    startActivityForResult(intent, 5846);
                }
            });

            //选择提成员工
            rlEmployee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (isStaff) {
                        Intent intent = new Intent(PayConfirmActivity.this, StaffCommissionActivity.class);
                        intent.putExtra("PAGE_FLAG", mPageFlag);
//                        intent.putExtra("VG_GID", memberInfoBean == null ? "" : memberInfoBean.getData().getVG_GID());
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("mStaffInfo", (Serializable) mStaffInfo);
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 1002);
                    } else {
                        CustomToast.makeText(PayConfirmActivity.this, "员工提成未开启，请确认后再尝试", Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initZero();
                etDiscountMoney.setFocusable(false);
                etDiscountMoney.setFocusableInTouchMode(true);
//                etDiscountMoney.setFocusable(true);
                if (isHYCC) {
                    if (mMemberInfoBack != null || memberInfoBean != null) {
                        for (int i = 0; i < ZHDataBean.size(); i++) {
                            if (ZHDataBean.get(i).getNum() <= 0) {
                                CustomToast.makeText(PayConfirmActivity.this, "商品数量必须大于0", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        if (!TextUtils.isEmpty(etDiscountMoney.getText())) {
                            if (RegexUtil.isMoney(etDiscountMoney.getText().toString())) {
                                YSLUtils.setBackgroundAlpha(0.5f, PayConfirmActivity.this);
                                if (mZeroType > 0) {
                                    mPayWayPopWindow = new YSLPayPopWindow(PayConfirmActivity.this, Decima2KeeplUtil.stringToDecimal(mZeroTotalMoney + ""), mSwitchEntity);
                                } else {
                                    mPayWayPopWindow = new YSLPayPopWindow(PayConfirmActivity.this, etDiscountMoney.getText().toString(), mSwitchEntity);
                                }
                                mPayWayPopWindow.setOnItemClickListener(PayConfirmActivity.this);
                                mPayWayPopWindow.setOnDismissListener(PayConfirmActivity.this);
                                mPayWayPopWindow.showAtLocation(PayConfirmActivity.this.findViewById(R.id.rl_pay_way),
                                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                            } else {
                                CustomToast.makeText(PayConfirmActivity.this, "折后金额格式不正确", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            CustomToast.makeText(PayConfirmActivity.this, "折后金额不能为空", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        CustomToast.makeText(PayConfirmActivity.this, "请先选择会员！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    for (int i = 0; i < ZHDataBean.size(); i++) {
                        if (ZHDataBean.get(i).getNum() <= 0) {
                            CustomToast.makeText(PayConfirmActivity.this, "商品数量必须大于0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    if (!TextUtils.isEmpty(etDiscountMoney.getText())) {
                        if (RegexUtil.isMoney(etDiscountMoney.getText().toString())) {
                            YSLUtils.setBackgroundAlpha(0.5f, PayConfirmActivity.this);
                            if (mZeroType > 0) {
                                mPayWayPopWindow = new YSLPayPopWindow(PayConfirmActivity.this, Decima2KeeplUtil.stringToDecimal(mZeroTotalMoney + ""), mSwitchEntity);
                            } else {
                                mPayWayPopWindow = new YSLPayPopWindow(PayConfirmActivity.this, etDiscountMoney.getText().toString(), mSwitchEntity);
                            }
                            mPayWayPopWindow.setOnItemClickListener(PayConfirmActivity.this);
                            mPayWayPopWindow.setOnDismissListener(PayConfirmActivity.this);
                            mPayWayPopWindow.showAtLocation(PayConfirmActivity.this.findViewById(R.id.rl_pay_way),
                                    Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        } else {
                            CustomToast.makeText(PayConfirmActivity.this, "折后金额格式不正确", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        CustomToast.makeText(PayConfirmActivity.this, "折后金额不能为空", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //返回
        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //选择优惠活动
        rlDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Li(mOrderMoney + "=======mOrderMoney========random:" + spxfmoneyafterdiscount);
                Li(mMemberInfoBack + "=======mMemberInfoBack========random:" + spxfmoneyafterdiscount);
                etDiscountMoney.clearFocus();
                //mOrderMoney订单金额,flags折后金额
                flags = spxfmoneyafterdiscount;//在选择优惠活动之前就把当前计算好的这后金额保存起来
                Intent intent = new Intent(PayConfirmActivity.this, DiscountTypeActivity.class);
                intent.putExtra("consume_type", mConsumeType);
                intent.putExtra("before_price", tvCostMoney.getText().toString());
                intent.putExtra("user_card", mMemberCardNo);
                intent.putExtra("user_birthady", mMemberInfoBack == null ? "00000" : mMemberInfoBack.getVIP_Birthday());

//                =======
//                mDiscountMoney = mOrderMoney;//在每次选择活动之前，把折后金额重置为订单金额
//                mGetPoints = 0;
//                tvPonits.setText("0");//积分计算和优惠活动没关系
                //  etDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(mOrderMoney + ""));
                Li(flags + "             " + tvCostMoney.getText().toString() + "======consume_type=========random:" + mConsumeType);
                startActivityForResult(intent, 1001);
            }
        });

//        etDiscountMoney.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (TextUtils.isEmpty(etDiscountMoney.getText())) {
//                    etDiscountMoney.setSelection(0);
//                } else {
//                    etDiscountMoney.setSelection(etDiscountMoney.getText().length());
//                }
//            }
//        });
        if (isSPXF || isHYCC) {

        } else {
            //折后金额修改
            etDiscountMoney.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    if (!editable.toString().isEmpty() && !".".equals(editable.toString())) {
                        String temp = editable.toString();
                        int posDot = temp.indexOf(".");
//                    if (posDot <= 0) return;
                        if (posDot > 0) {
                            if (temp.length() - posDot - 1 > 2) {
                                editable.delete(posDot + 3, posDot + 4);
                            }
                        }
                        Li("折后金额===" + mDiscountMoney);
                        mDiscountMoney = Double.parseDouble(editable.toString());
                        if (mDiscountMoney < 0) {
                            hintDialog("折后金额不能小于零");
                            mDiscountMoney = mOrderMoney;
                            etDiscountMoney.setText(mDiscountMoney + "");
                        }
                        if (mDiscountMoney > mOrderMoney) {
                            hintDialog("折后金额不能大于订单金额");
                            mDiscountMoney = mOrderMoney;
                            etDiscountMoney.setText(mDiscountMoney + "");
                        }
                    } else {
                        mDiscountMoney = 0;
                    }
                    if (!isChoiceDiscount) {
                        if (mDataBean != null) {
                            calculateActivityDiscount(mDiscountMoney);
                        }
                        calculatePoint(mDiscountMoney);
                        mGetPoints = DoubleMathUtil.add(mGetPoints, mActivityPoint);
                    }

//                    if (mPageFlag.equals("KSXF")) {


//                                if (mDataBean!=null){
//                                    calculateActivityDiscount(mDiscountMoney);
//                                }
//                                calculatePoint(mDiscountMoney);
//                                mGetPoints = DoubleMathUtil.add(mGetPoints, mActivityPoint);
//                                break;
//                            case "SPXF":
//                                mGradePoint = 0;
//                                mGradePoint = DoubleMathUtil.div(DoubleMathUtil.mul(mDiscountMoney, mInitPoint), mInitDiscountMoney);
//                                mGetPoints = DoubleMathUtil.add(mGradePoint, mActivityPoint);
//                                mGetPoints = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(mGetPoints + ""));
//                                break;
//                            case "HYCC":
//                                mGradePoint = 0;
//                                mGradePoint = DoubleMathUtil.div(DoubleMathUtil.mul(mDiscountMoney, mInitPoint), mInitDiscountMoney);
//                                mGetPoints = DoubleMathUtil.add(mGradePoint, mActivityPoint);
//                                mGetPoints = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(mGetPoints + ""));
//                                break;
//                    }
//
                    tvPonits.setText(Decima2KeeplUtil.stringToDecimal("" + mGetPoints));
                    mHandler.sendEmptyMessage(1);
                    //修改金额清空优惠活动
                    if (isDiscount) {
                        if (mKSXF_modify_money_bool) {
                            mDiscountActivityGid = "";
                            mDataBean = null;
                            tvDiscount.setText("");
                            mKSXF_modify_money = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(mDiscountMoney + ""));
                        }
                    }
                }
            });

//            etDiscountMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {
//                    if (!hasFocus) {
//
////                        isFocus = true;
//                        if (!etDiscountMoney.getText().toString().isEmpty() && !".".equals(etDiscountMoney.getText().toString())){
//                            if (mDataBean != null) {
//                                calculateActivityDiscount(Double.parseDouble(etDiscountMoney.getText().toString()));
//                            }
//                            calculatePoint(mDiscountMoney);
//                            mGetPoints = DoubleMathUtil.add(mGetPoints, mActivityPoint);
//                            tvPonits.setText(Decima2KeeplUtil.stringToDecimal("" + mGetPoints));
//                        }
//                    }
////                    else {
////                        isFocus = false;
////                    }
//                }
//            });

            etDiscountMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    Li("============hasFocus============= random:" + hasFocus);
                    mKSXF_modify_money_bool = hasFocus;
                }
            });
        }

        cbShortMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ShortMessage = true;
                } else {
                    ShortMessage = false;
                }
            }
        });
        cbPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pribean == 1) {
                    if (cbPrint.isChecked()) {
                        cbPrint.setChecked(true);
                        Print = true;
                    } else {
                        cbPrint.setChecked(false);
                        Print = false;
                    }
                } else {
                    CustomToast.makeText(PayConfirmActivity.this, "打印开关未开启，请设置", Toast.LENGTH_SHORT).show();
                    cbPrint.setChecked(false);
                    Print = false;
                }

            }
        });


//        mGoodsPayAdapter.setListener(new TimesPayAdapter.ChanggeTextListener() {
//            @Override
//            public void setText(View view, String text) {
//                ((TextView) view.findViewById(R.id.ed_money)).setText(text);
//            }
//        });
        if (isHYCC) {
            ivAddMemberOverdueDate.setOnClickListener(new View.OnClickListener() {//会员充次过期时间
                @Override
                public void onClick(View v) {
                    if (etAddMemberOverdueDate.getText().toString().isEmpty()) {
                        showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), etAddMemberOverdueDate);
                    } else {
                        showDateDialog(DateUtil.getDateForString(etAddMemberOverdueDate.getText().toString()), etAddMemberOverdueDate);
                    }
                }
            });

            tvPayConfirmRule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showRulesDialog(rulesList, tvPayConfirmRule);
                }
            });
        }
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

    }


    /**
     * 选择计次规则
     **/
    private void showRulesDialog(List<String> mlist, final TextView mTextView) {
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
                        rulesGid = rulesBean.getData().getDataList().get(position).getGID();
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

    /**
     * @param date      ,
     * @param mTextView ,
     *                  选择日期
     */
    private void showDateDialog(List<Integer> date, final TextView mTextView) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                mTextView.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
            }

            @Override
            public void onCancel() {
                mTextView.setText("");
            }
        })
                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);

        datesDialog = builder.create();
        datesDialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPayWayPopWindow != null) {
            mPayWayPopWindow.dismiss();//解除PopWindow，防止内存泄露
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
                        mPayType = "现金支付";
                        //快速消费
                        if ("KSXF".equals(mPageFlag)) {
                            judgeOrderType(memberInfoBean == null ? "00000" : memberInfoBean.getData().getVCH_Card());//判断是会员还是散客消费
                            setCashPayVariable();
                            // fastPay();
                            fastPayStepOne();
                        }
//                        //会员充值
//                        if ("HYCZ".equals(mPageFlag)) {
//                            setCashPayVariable();
////                        memberRechargePost();
//                            vipInflateMoneyStepOne();
//                        }
                        //商品消费
                        if ("SPXF".equals(mPageFlag)) {
                            setCashPayVariable();
//                        goodsConsumePay();
                            goodsShopStepOne();
                        }
                        //会员充次
                        if ("HYCC".equals(mPageFlag)) {
                            setCashPayVariable();
//                        memberRechargeTimePost();
                            vipInflateCountStepOne();
                        }

                    } else {
                        CustomToast.makeText(PayConfirmActivity.this, "未开启现金支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.l_yue:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mBalanceSwitch) {
                        mPayType = "余额支付";

                        if (!mMemberCardNo.equals("00000")) {
                            Li(mDiscountMoney + "==========mUserBalance * mPercentMoney===random:" + mPercentMoney);
                            /**算的是用户的百分比余额 20191112 cy*/
//                            if (Double.parseDouble(etDiscountMoney.getText().toString()) > mUserBalance * mPercentMoney) {
//                                hintDialog("超出可抵扣余额限制");
//                                return;
//                            }
                            /******************* 20191112 cy*/
                            /** 商品的百分比余额  20191113 cy*/
                            if (Double.parseDouble(etDiscountMoney.getText().toString()) > mDiscountMoney * mPercentMoney) {
                                hintDialog("            你设置的余额比例\n余额支付不能大于该单金额" + (int) (mPercentMoney * 100) + "%");
                                return;
                            }
                            /******************* 20191113 cy*/
                        }
                        if ("KSXF".equals(mPageFlag)) {
                            judgeOrderType(memberInfoBean == null ? "00000" : memberInfoBean.getData().getVCH_Card());
                            if (mOrderType == 1) {


                                if (memberInfoBean != null && memberInfoBean.getData().getMA_AvailableBalance() >= mDiscountMoney) {
                                    if (mPasswordSwitch) {
                                        showPasswordDialog(1);
                                    } else {
                                        setYuEPayVariable();
//                                    fastPay();
                                        fastPayStepOne();
                                    }
                                } else {
                                    hintDialog("余额不足，请选择其它支付方式");
                                }
                            } else {
                                hintDialog("非会员无法使用余额支付");
                            }
                        }
                        if ("SPXF".equals(mPageFlag)) {
                            if (mOrderType == 1) {
                                if (memberInfoBean != null && memberInfoBean.getData().getMA_AvailableBalance() >= mDiscountMoney) {
                                    if (mPasswordSwitch) {
                                        showPasswordDialog(2);
                                    } else {
                                        setYuEPayVariable();
//                                    goodsConsumePay();
                                        goodsShopStepOne();
                                    }
                                } else {
                                    hintDialog("余额不足，请选择其它支付方式");
                                }
                            } else {
                                hintDialog("非会员无法使用余额支付");
                            }
                        }
                        if ("HYCC".equals(mPageFlag)) {
                            if (mOrderType == 1) {
                                if (memberInfoBean != null && memberInfoBean.getData().getMA_AvailableBalance() >= mDiscountMoney) {
                                    if (mPasswordSwitch) {
                                        showPasswordDialog(3);
                                    } else {
                                        setYuEPayVariable();
//                                    memberRechargeTimePost();
                                        vipInflateCountStepOne();
                                    }
                                } else {
                                    hintDialog("余额不足，请选择其它支付方式");
                                }
                            } else {
                                hintDialog("非会员无法使用余额支付");
                            }
                        }
                        if ("HYCZ".equals(mPageFlag)) {
                            hintDialog("会员充值无法使用余额支付");
                        }
                    } else {
                        CustomToast.makeText(PayConfirmActivity.this, "未开启余额支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            //联合支付
            case R.id.r_union:
                if (YSLUtils.isFastClick()) {
                    mPayType = "联合支付";
                    if ("KSXF".equals(mPageFlag)) {
                        //商品价格
                        String oneStr = tvOrderMoney.getText().toString();
                        //支付价格,有活动优惠,要传到联合支付
                        String twoStr = tvCostMoney.getText().toString();
                        double couponMoney = Double.parseDouble(oneStr) - Double.parseDouble(twoStr);
                        if (couponMoney != 0) {
                            couponMoney = (double) Math.round(couponMoney * 100) / 100;
                        }
                        Li(oneStr + "--couponMoneyStr---------KSXF-random:" + twoStr + "　　couponMoney：" + couponMoney);
                        Intent intent = new Intent(PayConfirmActivity.this, UnionPayActivity.class);
                        intent.putExtra("VIP_INFO", mMemberInfoBack);
                        Li("mMemberInfo----Pay---" + memberInfoBean);
                        intent.putExtra(MyApplication.PAGE_FLAG, mPageFlag);
                        intent.putExtra(MyApplication.ORDER_NO, edOrder.getText().toString());
                        intent.putExtra(MyApplication.CARD_NO, mMemberCardNo);
                        intent.putExtra("ORDER_MONEY", Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etDiscountMoney.getText().toString() + "")));
//                        intent.putExtra("MONEY", mDiscountMoney);
                        if (mMemberInfoBack == null) {
                            if (isDiscount) {
                                Li("MONEY---1111-random---" + mKSXF_modify_money);
                                intent.putExtra("MONEY", mKSXF_modify_money);
                                intent.putExtra("extra_ksxf_discount", "isdiscount");
                            } else {
                                intent.putExtra("MONEY", Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));
                            }
                            Li("MONEY----random---" + Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));
                        } else {
                            if (isDiscount) {
                                Li("MONEY---2222-random---" + mKSXF_modify_money);
                                intent.putExtra("extra_ksxf_discount", "isdiscount");
                                if (mDiscountActivityGid == null || mDiscountActivityGid.equals("")) {
                                    intent.putExtra("MONEY", mKSXF_modify_money);
                                } else {
                                    Li("MONEY---3333-random---" + mKSXF_modify_money);
                                    intent.putExtra("MONEY", ksxfmDiscountMoney == 0.00 ? Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)) : ksxfmDiscountMoney);
                                }
                                intent.putExtra("extra_ksxf_discount", "isdiscount");
                            } else {
                                intent.putExtra("MONEY", ksxfmDiscountMoney == 0.00 ? mOrderMoney : ksxfmDiscountMoney);
                            }
                            Li(ksxfmDiscountMoney + " 13  MONEY----random---" + (ksxfmDiscountMoney == 0.00 ? mOrderMoney : ksxfmDiscountMoney) + "");
                        }
                        intent.putExtra("CO_Monetary", Double.parseDouble(tvOrderMoney.getText().toString()));
                        intent.putExtra("NotCouponMoney", Double.parseDouble(oneStr));
                        intent.putExtra("CouponMoney", couponMoney);
                        intent.putExtra("POINT", mGetPoints);
                        intent.putExtra("CC_GID", (mDiscountActivityGid == null || mDiscountActivityGid.equals("")) ? "" : mDiscountActivityGid);
                        intent.putExtra("message", cbShortMessage.isChecked());
                        intent.putExtra("print", cbPrint.isChecked());
                        intent.putExtra("isKJSK", isKJSK);
                        intent.putExtra("remark", etPayDetail.getText().toString());
                        intent.putExtra("OrderTime", tvOrderTime.getText().toString());
                        intent.putExtra("ksxfmDiscountMoney", ksxfmDiscountMoney);
                        if (mStaffListGid.size() > 0) {
                            intent.putExtra("EM_GIDList", mStaffListGid);
                        }
                        if (!"".equals(MDZZ) && MDZZ != null) {
                            intent.putExtra("MDZZ", MDZZ);
                        }
                        startActivity(intent);
                    }
                    if ("SPXF".equals(mPageFlag) || "HYCC".equals(mPageFlag)) {
                        if ("HYCC".equals(mPageFlag) && mMemberInfoBack == null) {
                            CustomToast.makeText(PayConfirmActivity.this, "请选择会员,在进行充次！", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        //商品价格
                        String oneStr = tvCost.getText().toString().replaceAll("¥", "");
                        //支付价格,有活动优惠,要传到联合支付
                        String twoStr = tvCostMoney.getText().toString();
                        double couponMoney = Double.parseDouble(oneStr) - Double.parseDouble(twoStr);
                        if (couponMoney != 0) {
                            couponMoney = (double) Math.round(couponMoney * 100) / 100;
                        }
                        Li(oneStr + "--couponMoneyStr---------SPXF-random:" + twoStr + "　　couponMoney：" + couponMoney);
                        Intent intent = new Intent(PayConfirmActivity.this, UnionPayActivity.class);
                        ArrayList list = new ArrayList();
                        list.add(mGoodsBeanList);
                        Bundle bundle = new Bundle();
                        intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) list);
                        bundle.putParcelableArrayList("list", list);
                        intent.putExtra("bundle", bundle);
                        //intent.putExtra("VIP_INFO", mMemberInfo);
                        intent.putExtra("VIP_INFO", mMemberInfoBack);
                        intent.putExtra(MyApplication.ORDER_NO, mOrderNo);
                        intent.putExtra(MyApplication.CARD_NO, mMemberCardNo);
                        intent.putExtra("ORDER_MONEY", Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etDiscountMoney.getText().toString() + "")));
//                        intent.putExtra("CO_Monetary", Double.parseDouble(tvOrderMoney.getText().toString()));
                        intent.putExtra("NotCouponMoney", Double.parseDouble(oneStr));
                        intent.putExtra("CouponMoney", couponMoney);
//                    intent.putExtra("MONEY", mDiscountMoney);
                        intent.putExtra("MONEY", Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etDiscountMoney.getText().toString() + "")));
                        intent.putExtra("POINT", mGetPoints);
                        intent.putExtra(MyApplication.PAGE_FLAG, mPageFlag);
                        intent.putExtra("message", cbShortMessage.isChecked());
                        intent.putExtra("print", cbPrint.isChecked());
                        intent.putExtra("CC_GID", (mDiscountActivityGid == null || mDiscountActivityGid.equals("")) ? "" : mDiscountActivityGid);
                        intent.putExtra("remark", etPayDetail.getText().toString());
                        intent.putExtra("OrderTime", tvOrderTime.getText().toString());
                        if (mStaffListGidList.size() > 0) {
                            intent.putExtra("EM_GIDList", (Serializable) mStaffListGidList);
                        }
                        if (!"".equals(MDZZ) && MDZZ != null) {
                            intent.putExtra("MDZZ", MDZZ);
                        }
                        startActivity(intent);
                    }
                }
                break;
            //银联刷卡
            case R.id.l_card:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mCardSwitch) {
                        mPayType = "银联支付";
                        if ("KSXF".equals(mPageFlag)) {
                            judgeOrderType(memberInfoBean == null ? "00000" : memberInfoBean.getData().getVCH_Card());//判断是会员还是散客消费
                            setBankPayVariable();
                            setPayPos("快速消费");
                        }
                        if ("SPXF".equals(mPageFlag)) {
                            setBankPayVariable();
                            setPayPos("商品消费");
                        }
                        if ("HYCC".equals(mPageFlag)) {
                            setBankPayVariable();
                            setPayPos("会员充次");
                        }

                    } else {
                        CustomToast.makeText(PayConfirmActivity.this, "未开启银联支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.l_zhifubao:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mZfbSwitch) {
                        mPayType = "支付宝记账";
                        //快速消费
                        if ("KSXF".equals(mPageFlag)) {
                            judgeOrderType(memberInfoBean == null ? "00000" : memberInfoBean.getData().getVCH_Card());//判断是会员还是散客消费
                            setZfbPayVariable();
//                        fastPay();
                            fastPayStepOne();
                        }
//                        //会员充值
//                        if ("HYCZ".equals(mPageFlag)) {
//                            setZfbPayVariable();
////                        memberRechargePost();
//                            vipInflateMoneyStepOne();
//                        }
                        //商品消费
                        if ("SPXF".equals(mPageFlag)) {
                            setZfbPayVariable();
//                        goodsConsumePay();
                            goodsShopStepOne();
                        }
                        //会员充次
                        if ("HYCC".equals(mPageFlag)) {
                            setZfbPayVariable();
//                        memberRechargeTimePost();
                            vipInflateCountStepOne();
                        }

                    } else {
                        CustomToast.makeText(PayConfirmActivity.this, "未开启支付宝记账，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.l_weixin:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mWXSwitch) {
                        mPayType = "微信记账";
                        //快速消费
                        if ("KSXF".equals(mPageFlag)) {
                            judgeOrderType(memberInfoBean == null ? "00000" : memberInfoBean.getData().getVCH_Card());//判断是会员还是散客消费
                            setWxPayVariable();
//                        fastPay();
                            fastPayStepOne();
                        }
                        //会员充值
//                        if ("HYCZ".equals(mPageFlag)) {
//                            setWxPayVariable();
////                        memberRechargePost();
//                            vipInflateMoneyStepOne();
//                        }
                        //商品消费
                        if ("SPXF".equals(mPageFlag)) {
                            setWxPayVariable();
//                        goodsConsumePay();
                            goodsShopStepOne();
                        }
                        //会员充次
                        if ("HYCC".equals(mPageFlag)) {
                            setWxPayVariable();
//                        memberRechargeTimePost();
                            vipInflateCountStepOne();
                        }

                    } else {
                        CustomToast.makeText(PayConfirmActivity.this, "未开启微信记账，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            //扫码支付
            case R.id.l_saoma:
                if (mSmSwitch) {
                    mPayType = "扫码支付";
                    if ("KSXF".equals(mPageFlag)) {
                        judgeOrderType(memberInfoBean == null ? "00000" : memberInfoBean.getData().getVCH_Card());//判断是会员还是散客消费
                        setScanCodePayVariable();
                        setPayPos("快速消费");
                    }
                    if ("HYCZ".equals(mPageFlag)) {
                        setScanCodePayVariable();
                        setPayPos("会员充值");
                    }
                    if ("SPXF".equals(mPageFlag)) {
                        setScanCodePayVariable();
                        setPayPos("商品消费");
                    }
                    if ("HYCC".equals(mPageFlag)) {
                        setScanCodePayVariable();
                        setPayPos("会员充次");
                    }
                } else {
                    CustomToast.makeText(PayConfirmActivity.this, "未开启扫码支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.l_other:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mOtherSwitch) {
                        mPayType = "其他支付";
                        //快速消费
                        if ("KSXF".equals(mPageFlag)) {
                            judgeOrderType(memberInfoBean == null ? "00000" : memberInfoBean.getData().getVCH_Card());//判断是会员还是散客消费
                            setOtherPayVariable();
//                        fastPay();
                            fastPayStepOne();
                        }
                        //会员充值
//                        if ("HYCZ".equals(mPageFlag)) {
//                            setOtherPayVariable();
////                        memberRechargePost();
//                            vipInflateMoneyStepOne();
//                        }
                        //商品消费
                        if ("SPXF".equals(mPageFlag)) {
                            setOtherPayVariable();
//                        goodsConsumePay();
                            goodsShopStepOne();
                        }
                        //会员充次
                        if ("HYCC".equals(mPageFlag)) {
                            setOtherPayVariable();
//                        memberRechargeTimePost();
                            vipInflateCountStepOne();
                        }
                    } else {
                        CustomToast.makeText(PayConfirmActivity.this, "未开启其他支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }

    /**
     * 支付并提交到服务器
     */
    private void payPost() {
        if ("KSXF".equals(mPageFlag)) {
            judgeOrderType(memberInfoBean == null ? "00000" : memberInfoBean.getData().getVCH_Card());//判断是会员还是散客消费
//            fastPay();
            fastPayStepOne();
        }
//        if ("HYCZ".equals(mPageFlag)) {
////            memberRechargePost();
//            vipInflateMoneyStepOne();
//        }
        if ("SPXF".equals(mPageFlag)) {
//            goodsConsumePay();
            goodsShopStepOne();
        }
        if ("HYCC".equals(mPageFlag)) {
//            memberRechargeTimePost();
            vipInflateCountStepOne();
        }
    }


    /**
     * 设置现金支付变量的值
     */
    private void setCashPayVariable() {
        mPointSpend = 0L;
        mPayWayName = "现金支付";
        mPayWayCode = "XJZF";
    }

    /**
     * 设置余额支付变量的值
     */
    private void setYuEPayVariable() {
        mPointSpend = 0L;
        mPayWayName = "余额支付";
        mPayWayCode = "YEZF";
    }

    /**
     * 设置银联支付变量的值
     */
    private void setBankPayVariable() {
        mPointSpend = 0L;
        mPayWayName = "银联支付";
        mPayWayCode = "YLZF";
        mLklPayType = "0";
        mLklDisPosCode = "000000";
    }


    /**
     * 设置支付宝记账变量的值
     */
    private void setZfbPayVariable() {
        mPointSpend = 0L;
        mPayWayName = "支付宝记账";
        mPayWayCode = "ZFB_JZ";
    }

    /**
     * 设置微信记账变量的值
     */
    private void setWxPayVariable() {
        mPointSpend = 0L;
        mPayWayName = "微信记账";
        mPayWayCode = "WX_JZ";
    }

    /**
     * 设置扫码支付变量的值
     */
    private void setScanCodePayVariable() {
        mPointSpend = 0L;
        mPayWayName = "扫码支付";
        mPayWayCode = "SMZF";
        mLklPayType = "1";
        mLklDisPosCode = "660000";
    }

    /**
     * 设置其他支付变量
     **/

    private void setOtherPayVariable() {

        mPointSpend = 0L;
        mPayWayName = "其他支付";
        mPayWayCode = "QTZF";

    }

    /**
     * 判断是否是在POS机上进行消费
     * 如果是，则根据POS机型号调用对应的POS机支付接口
     *
     * @param orderInfo 消费界面名称
     */
    private void setPayPos(String orderInfo) {
        //如果是拉卡拉设备则调用拉卡拉支付接口
        if (IS_LAKALA_POS_DEVICE) {
            //调用拉卡拉的刷卡与感应卡支付功能
            PayLKL payLKL = new PayLKL();

            payLKL.pay4Lkl(
                    PayConfirmActivity.this,
                    mOrderNo,
                    Decima2KeeplUtil.stringToDecimal(String.valueOf(mDiscountMoney)),
                    orderInfo,
                    mLklPayType,
                    mLklDisPosCode
            );
        } else {
            if (orderInfo.equals("快速消费")) {
//                fastPay();
                fastPayStepOne();
            } else if (orderInfo.equals("商品消费")) {
//                goodsConsumePay();
                goodsShopStepOne();
            } else if (orderInfo.equals("会员充次")) {
//                memberRechargeTimePost();
                vipInflateCountStepOne();
            }
        }
        //如果是SUNMI则调用SUNMI扫码支付
//                else if (IS_SUNMI_POS_P1_DEVICE)
//                {
//                    Intent intent = new Intent("com.summi.scan");
//
//                    intent.setPackage("com.sunmi.sunmiqrcodescanner");
//                    intent.putExtra("PLAY_SOUND", true);// 扫描完成声音提示
//                    startActivityForResult(intent, START_SCAN);
//
//                }
    }


    /**
     * 快速消费提交订单信息
     */
    private void fastPayStepOne() {
        if (TextUtils.isEmpty(edOrder.getText())) {
            CustomToast.makeText(this, "订单编号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Le("fastPayStepOne");
        RequestParams params = new RequestParams();
        //会员卡号
        params.put("VIP_Card", mMemberInfoBack == null ? "00000" : mMemberInfoBack.getVCH_Card());
        //订单编号
        params.put("CO_OrderCode", edOrder.getText().toString());
        //订单时间
        params.put("OrderTime", tvOrderTime.getText().toString());
        //应收金额
//        params.put("CO_Monetary", Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));
//        params.put("CO_Monetary", mOrderMoney);
        if (mZeroType > 0) {
//            if (mZeroType == 3) {
//                Li("==========a=======33=====random:");
//                params.put("CO_Monetary", mKSXF_modify_money);
//            } else {
//                params.put("CO_Monetary", Decima2KeeplUtil.stringToDecimal(mZeroTotalMoney + ""));
//            }
            params.put("CO_Monetary", mOrderMoney);
        } else {
            params.put("CO_Monetary", mOrderMoney);
        }

        //折后金额(应收金额)
//        params.put("CO_TotalPrice", Decima2KeeplUtil.stringToDecimal((ksxfmDiscountMoney == 0.00 ? mOrderMoney : ksxfmDiscountMoney) + ""));
        if (mMemberInfoBack == null) {
            if (isDiscount) {
                Li("=================11=====random:");
                params.put("CO_TotalPrice", mKSXF_modify_money);
            } else {
                params.put("CO_TotalPrice", mOrderMoney);
            }
        } else {
            if (isDiscount) {
                Li("=================22=====random:");

                if (mDiscountActivityGid != null && !mDiscountActivityGid.equals("")) {
                    Li("=================33=====random:");
                    params.put("CO_TotalPrice", (ksxfmDiscountMoney == 0.00 ? mOrderMoney : Decima2KeeplUtil.stringToDecimal(ksxfmDiscountMoney + "")) + "");
                    mKsxfFlagMoney = ksxfmDiscountMoney == 0.00 ? mOrderMoney : ksxfmDiscountMoney;
                } else {
                    if (mZeroType > 0) {
                        Li("==========a=======22=====random:");

                        double flag = Double.parseDouble(etDiscountMoney.getText().toString());
                        if (mKSXF_modify_money == mOrderMoney && mOrderMoney > flag) {
                            params.put("CO_TotalPrice", Decima2KeeplUtil.stringToDecimal(etDiscountMoney.getText().toString()));
                            mKsxfFlagMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etDiscountMoney.getText().toString()));
                        } else {
                            params.put("CO_TotalPrice", mKSXF_modify_money);
                            mKsxfFlagMoney = mKSXF_modify_money;
                        }
                    } else {
                        params.put("CO_TotalPrice", mKSXF_modify_money);
                        mKsxfFlagMoney = mKSXF_modify_money;
                    }
                }
            } else {
                params.put("CO_TotalPrice", (ksxfmDiscountMoney == 0.00 ? mOrderMoney : ksxfmDiscountMoney) + "");
                mKsxfFlagMoney = ksxfmDiscountMoney == 0.00 ? mOrderMoney : ksxfmDiscountMoney;
            }

            Li(mOrderMoney + "CO_TotalPrice   订单金额: " + mOrderMoney + " 快订单=-----------------random:" + ksxfmDiscountMoney);
        }

//        params.put("CO_TotalPrice", Decima2KeeplUtil.stringToDecimal(etDiscountMoney.getText().toString()));
        //备注
        params.put("CO_Remark", etPayDetail.getText().toString() == null ? "" : etPayDetail.getText().toString());

//        params.put("OrderType", mOrderType);//消费者身份，1会员，2，散客
//        params.put("DisMoney", Decima2KeeplUtil.stringToDecimal(String.valueOf(etDiscountMoney.getText().toString())));
//        params.put("CO_Integral", Decima2KeeplUtil.stringToDecimal(String.valueOf(mGetPoints)));//可获得积分
//        params.put("CC_GID", mDiscountActivityGid);//优惠活动GID
//        params.put("ActivityValue", "抵扣0元");
        //添加提成员工
        if (mStaffInfo != null) {
            for (int j = 0; j < mStaffInfo.size(); j++) {
                params.put("EM_GIDList[" + j + "]", mStaffInfo.get(j).getGID());
            }
        }

        Li(ksxfmDiscountMoney + "   订单金额: " + mOrderMoney + " 快速消费提交订单=-----------------random:" + params.toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                FastPayStepOneBean stepOneBean = CommonFun.JsonToObj(responseString, FastPayStepOneBean.class);
                mOrderGID = stepOneBean.getData().getGID();
                if (mPayWayCode.equals("SMZF")) {
                    //打开扫描界面扫描条形码或二维码
                    Intent openCameraIntent = new Intent(PayConfirmActivity.this, CaptureActivity.class);
                    startActivityForResult(openCameraIntent, 1);
                } else {
                    fastPayStepTwo();
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(PayConfirmActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交订单...", false);
        HttpHelper.post(this, HttpAPI.API().FAST_CONSUME_SUB, params, callBack);
    }

    /**
     * 快速消费确认支付
     */
    private void fastPayStepTwo() {
        Le("fastPayStepTwo");
        RequestParams params = new RequestParams();
        //订单GID
        params.put("OrderGID", mOrderGID);
        //是否发送短信
        params.put("Smsg", cbShortMessage.isChecked() ? 1 : 0);
        if (mZeroType > 0) {
            //应收金额
            params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
            //实收金额
            params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
            //支付金额
            params.put("PayResult[PayTypeList][0][PayMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
        } else {
            //应收金额
            params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(etDiscountMoney.getText().toString())));
            //实收金额
            params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(etDiscountMoney.getText().toString())));
            //支付金额
            params.put("PayResult[PayTypeList][0][PayMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(etDiscountMoney.getText().toString())));
        }
        //抹零金额
        if (mZeroType == 4) {
            if (Decima2KeeplUtil.stringToDecimal(mKsxfFlagMoney + "").endsWith("0")) {
                params.put("PayResult[EraseOdd]", 0);
            } else {
                params.put("PayResult[EraseOdd]", mZeroMoney);
            }
        } else {
            params.put("PayResult[EraseOdd]", mZeroMoney);
        }
        //找零金额
        params.put("PayResult[GiveChange]", 0);
        //支付方式编码
        params.put("PayResult[PayTypeList][0][PayCode]", mPayWayCode);
        //支付方式名称
        params.put("PayResult[PayTypeList][0][PayName]", mPayWayName);
        //优惠活动GID
        if (mDiscountActivityGid != null && !mDiscountActivityGid.equals("")) {
            params.put("PayResult[CC_GID]", mDiscountActivityGid);
        }
//        params.put("PayResult[PayPoint]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mPointSpend)));//积分支付

        Li(mZeroType + "快速消费确认支付-----------random:" + params.toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final KSXF_Success_Bean ksxf_success_bean = CommonFun.JsonToObj(responseString, KSXF_Success_Bean.class);
                Li("---------------打印小票-------------- 商品消费--random:" + new Gson().toJson(ksxf_success_bean));
                fastPayStepTwoComplete(ksxf_success_bean.getData().getGID());
            }

            @Override
            public void onFailure(String msg) {
                if (msg.contains("SmsSign")) {//短信未设置默认签名
                    mSweetAlertDialog = new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，未设置默认签名！");
                    mSweetAlertDialog.setConfirmText("确定");
                    if (mMemberInfoBack == null) {
                        mSweetAlertDialog.setCancelText("添加为会员");
                    }
                    mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent intent = new Intent(PayConfirmActivity.this, AddMemberActivity.class);
                            startActivity(intent);
                        }
                    });
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

                            if (!"".equals(MDZZ) && MDZZ != null) {
                                Intent intent = new Intent(PayConfirmActivity.this, MemberListActivity.class);
                                intent.putExtra("carddd", mMemberCardNo);
                                startActivity(intent);
                                finish();
                            } else {
                                // Intent intent = new Intent(PayConfirmActivity.this, FastConsumeActivity.class);
                                //startActivity(intent);
                                finish();
                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.contains("BuySms")) {//短信库存不足
                    mSweetAlertDialog = new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，短信库存不足！");
                    mSweetAlertDialog.setConfirmText("确定");
                    if (memberInfoBean == null) {
                        mSweetAlertDialog.setCancelText("添加为会员");
                    }
                    mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent intent = new Intent(PayConfirmActivity.this, AddMemberActivity.class);
                            startActivity(intent);
                        }
                    });
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (!"".equals(MDZZ) && MDZZ != null) {
                                Intent intent = new Intent(PayConfirmActivity.this, MemberListActivity.class);
                                intent.putExtra("carddd", mMemberCardNo);
                                startActivity(intent);
                                finish();
                            } else {
                                //  Intent intent = new Intent(PayConfirmActivity.this, FastConsumeActivity.class);
                                // startActivity(intent);
                                finish();
                            }
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

    private void fastPayStepTwoComplete(final String GID) {
        Li("---------------打印小票--------------" + GID);
        try {
            mSweetAlertDialog = new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.SUCCESS_TYPE);
            mSweetAlertDialog.setTitleText("支付成功");
            mSweetAlertDialog.setConfirmText("确定");
            if (mMemberInfoBack == null) {
                mSweetAlertDialog.setCancelText("添加为会员");
            }
            mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intent = new Intent(PayConfirmActivity.this, AddMemberActivity.class);
                    startActivity(intent);
                }
            });
            mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    mSweetAlertDialog.dismiss();
                    if (cbPrint.isChecked()) {
                        if (MyApplication.mFastConsumeMap.isEmpty()) {
                            GetPrintSet.getPrintParamSet();
                        }


                        //打印小票
                        new HttpGetPrintContents(PayConfirmActivity.this, MyApplication.KSXF_PRINT_TIMES,
                                GID, intentHandler).KSXF();
                    } else {
                        if (!"".equals(MDZZ) && MDZZ != null) {
                            Intent intent = new Intent(PayConfirmActivity.this, MemberListActivity.class);
                            intent.putExtra("carddd", mMemberCardNo);
                            startActivity(intent);
                            finish();
                        } else {
                            finish();
                        }
                    }

                }
            });
            mSweetAlertDialog.show();
        } catch (JsonSyntaxException e) {
            CustomToast.makeText(PayConfirmActivity.this, "打印失败！", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * 会员充次提交第一步，提交订单
     */
    private void vipInflateCountStepOne() {
        RequestParams params = new RequestParams();
        //充次订单
//        params.put("MC_Order", CreateOrder.createOrder("CC"));
        params.put("MC_Order", mOrderNo);
        //订单时间
        params.put("OrderTime", tvOrderTime.getText().toString());
        //会员卡号
        params.put("VIP_Card", mMemberCardNo);
        //备注
        params.put("MC_Remark", etPayDetail.getText().toString() == null ? "" : etPayDetail.getText().toString());

        for (int i = 0; i < mGoodsBeanList.size(); i++) {
            String type;
            if (mGoodsBeanList.get(i).getPM_IsService() == 3 || mGoodsBeanList.get(i).getPM_IsService() == 4) {
                type = "1";
            } else {
                type = "0";
            }
            //商品GID(或是套餐GID)
            params.put("ServeceList[" + i + "][PM_GID]", mGoodsBeanList.get(i).getGID());
            //商品数量
            params.put("ServeceList[" + i + "][PM_Number]", mGoodsBeanList.get(i).getNum());
            //商品类型
            params.put("ServeceList[" + i + "][Type]", type);
            //商品单价
            params.put("ServeceList[" + i + "][PM_UnitPrice]", mGoodsBeanList.get(i).getPM_UnitPrice());
            //商品折扣

            if (mGoodsBeanList.get(i).getMoney_d() == null) {
                mGoodsBeanList.get(i).setMoney_d(mGoodsBeanList.get(i).getPM_UnitPrice() + "");
            }
            double moneyD = Double.parseDouble(mGoodsBeanList.get(i).getMoney_d()) % (mGoodsBeanList.get(i).getPM_UnitPrice() * mGoodsBeanList.get(i).getNum());
            if (mGoodsBeanList.get(i).getMoney_d().equals("") || moneyD == 0) {
                params.put("ServeceList[" + i + "][PM_Discount]", 1);
            } else {
                params.put("ServeceList[" + i + "][PM_Discount]", Double.parseDouble(mGoodsBeanList.get(i).getMoney_d()) / (mGoodsBeanList.get(i).getPM_UnitPrice() * mGoodsBeanList.get(i).getNum()));
            }
//            params.put("ServeceList[" + i + "][PM_Discount]", mGoodsBeanList.get(i).getPM_IsDiscount());
            //商品小计
            params.put("ServeceList[" + i + "][PM_Money]", Decima2KeeplUtil.stringToDecimal(mGoodsBeanList.get(i).getZHMoney() + ""));
            //提成员工
            if (mStaffListGidList.get(String.valueOf(i)) != null) {
                for (int j = 0; j < mStaffListGidList.get(String.valueOf(i)).size(); j++) {//提成员工GID
                    params.put("ServeceList[" + i + "][EM_GIDList][" + j + "]", mStaffListGidList.get(String.valueOf(i)).get(j));
                }
            }
            //(仅会员充次时传参)-计次规则:单个规则
            params.put("ServeceList[" + i + "][WR_GIDList]", rulesGid);
            //(会员充次时传参)-过期时间
            params.put("ServeceList[" + i + "][ExpiryTime]", etAddMemberOverdueDate.getText().toString());
//            params.put("ServeceList[" + i + "][PM_Name]", mGoodsBeanList.get(i).getPM_Name());
        }
//        params.put("CC_GID", mDiscountActivityGid);
//        params.put("AfterDiscount", etDiscountMoney.getText().toString());//折后金额

        Li("会员充次支付 提交订单---------------------random:" + params.toString());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                VipInflateStepOneBean stepOneBean = CommonFun.JsonToObj(responseString, VipInflateStepOneBean.class);
                mVipInflatCountGID = stepOneBean.getData().getGID();

                if (mPayWayCode.equals("SMZF")) {
                    //打开扫描界面扫描条形码或二维码
                    Intent openCameraIntent = new Intent(PayConfirmActivity.this, CaptureActivity.class);
                    startActivityForResult(openCameraIntent, 1);
                } else {
                    vipInflateCountStepTwo();
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(PayConfirmActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交订单...", false);
        HttpHelper.post(this, HttpAPI.API().MEM_CHARGE_SUB, params, callBack);
    }

    /**
     * todo:会员充次支付 会员充次提交第二步，确认支付
     */
    private void vipInflateCountStepTwo() {
        RequestParams params = new RequestParams();
        //订单GID
        params.put("OrderGID", mVipInflatCountGID);
        //是否发送短信
        params.put("Smsg", cbShortMessage.isChecked() ? 1 : 0);
        if (mZeroType > 0) {
            //应收金额
            params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
            //实收金额
            params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
            //支付金额
            params.put("PayResult[PayTypeList][0][PayMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
        } else {
            //应收金额
            params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(etDiscountMoney.getText().toString())));
            //实收金额
            params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(etDiscountMoney.getText().toString())));
            //支付金额
            params.put("PayResult[PayTypeList][0][PayMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(etDiscountMoney.getText().toString())));
        }
        //抹零金额
        params.put("PayResult[EraseOdd]", mZeroMoney);
        //找零金额
        params.put("PayResult[GiveChange]", 0);
        //优惠活动GID
        if (mDiscountActivityGid != null && !mDiscountActivityGid.equals("")) {
            params.put("PayResult[CC_GID]", mDiscountActivityGid);
        }
        //支付方式编码
        params.put("PayResult[PayTypeList][0][PayCode]", mPayWayCode);
        //支付方式名称
        params.put("PayResult[PayTypeList][0][PayName]", mPayWayName);


        Li("会员充次支付---------------------random:" + params.toString());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final HYCC_Success_Bean hycc_success_bean = CommonFun.JsonToObj(responseString, HYCC_Success_Bean.class);
                vipInflateCountStepTwoComplete(hycc_success_bean.getData().getGID());
            }

            @Override
            public void onFailure(String msg) {
                if (msg.contains("SmsSign")) {
                    mSweetAlertDialog = new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("充次成功");
                    mSweetAlertDialog.setContentText("短信未发送，未设置默认签名！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

                            if (!"".equals(MDZZ) && MDZZ != null) {
                                Intent intent = new Intent(PayConfirmActivity.this, MemberListActivity.class);
                                intent.putExtra("carddd", mMemberCardNo);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(PayConfirmActivity.this, GoodsConsumeActivity.class);
                                intent.putExtra("type", "HYCC");
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.contains("BuySms")) {
                    mSweetAlertDialog = new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("充次成功");
                    mSweetAlertDialog.setContentText("短信未发送，短信库存不足！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

                            if (!"".equals(MDZZ) && MDZZ != null) {
                                Intent intent = new Intent(PayConfirmActivity.this, MemberListActivity.class);
                                intent.putExtra("carddd", mMemberCardNo);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(PayConfirmActivity.this, GoodsConsumeActivity.class);
                                intent.putExtra("type", "HYCC");
                                startActivity(intent);
                                finish();
                            }
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

    private void vipInflateCountStepTwoComplete(final String GID) {
        try {
            mSweetAlertDialog = new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.SUCCESS_TYPE);
            mSweetAlertDialog.setTitleText("充次成功");
            mSweetAlertDialog.setConfirmText("确定");
            mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if (cbPrint.isChecked()) {
                        if (MyApplication.mTimesRechargeMap.isEmpty()) {
                            GetPrintSet.getPrintParamSet();
                        }
                        //打印小票
                        new HttpGetPrintContents(PayConfirmActivity.this, MyApplication.HYCC_PRINT_TIMES, GID, intentHandler).HYCC();
                    } else {
                        Intent intent = new Intent(PayConfirmActivity.this, GoodsConsumeActivity.class);
                        intent.putExtra("type", "HYCC");
                        startActivity(intent);
                        finish();
                    }


                }
            });
            mSweetAlertDialog.show();

        } catch (JsonSyntaxException e) {
            CustomToast.makeText(PayConfirmActivity.this, "打印失败！", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * 商品消费提交订单
     */
    private void goodsShopStepOne() {
        Li("=========m========one" + spxfmoneyafterdiscount);
        Li("==可获得积分===mGetPoints=====random:" + mGetPoints);
        RequestParams params = new RequestParams();
        //订单编号
//        params.put("CO_OrderCode", CreateOrder.createOrder("SP"));
        params.put("CO_OrderCode", mOrderNo);
        //订单时间
//        params.put("OrderTime", DateTimeUtil.getReallyTimeNow());
        params.put("OrderTime", tvOrderTime.getText().toString());
        //会员卡号
        params.put("VIP_Card", mMemberCardNo == null ? "00000" : mMemberCardNo);
        //备注
        params.put("CO_Remark", etPayDetail.getText().toString() == null ? "" : etPayDetail.getText().toString());

//        params.put("OrderType", mOrderType);
//        params.put("CC_GID", mDiscountActivityGid);

        if (flags != 0) {
            if (choisedActivities) {
                spxfmoneyafterdiscount = mDiscountMoney;
            } else {
                spxfmoneyafterdiscount = flags;
            }
        }
//        params.put("CO_TotalPrice", etDiscountMoney.getText().toString());

        for (int i = 0; i < mGoodsBeanList.size(); i++) {
            String type;
            if (mGoodsBeanList.get(i).getPM_IsService() == 3 || mGoodsBeanList.get(i).getPM_IsService() == 4) {
                type = "1";
            } else {
                type = "0";
            }
            //商品GID(或是套餐GID)
            params.put("Goods[" + i + "][PM_GID]", mGoodsBeanList.get(i).getGID());
            //商品数量
            params.put("Goods[" + i + "][PM_Number]", mGoodsBeanList.get(i).getNum());
            //商品类型
            params.put("Goods[" + i + "][Type]", type);
            //商品单价
            params.put("Goods[" + i + "][PM_UnitPrice]", mGoodsBeanList.get(i).getPM_UnitPrice());
            //商品折扣
//            params.put("Goods[" + i + "][PM_Discount]", mGoodsBeanList.get(i).getPM_SpecialOfferValue());
            if (mGoodsBeanList.get(i).getMoney_d() == null) {
                mGoodsBeanList.get(i).setMoney_d(mGoodsBeanList.get(i).getPM_UnitPrice() + "");
            }
            double moneyD = Double.parseDouble(mGoodsBeanList.get(i).getMoney_d()) % (mGoodsBeanList.get(i).getPM_UnitPrice() * mGoodsBeanList.get(i).getNum());
            if (mGoodsBeanList.get(i).getMoney_d().equals("") || moneyD == 0) {
                params.put("Goods[" + i + "][PM_Discount]", 1);
            } else {
                params.put("Goods[" + i + "][PM_Discount]", Double.parseDouble(mGoodsBeanList.get(i).getMoney_d()) / (mGoodsBeanList.get(i).getPM_UnitPrice() * mGoodsBeanList.get(i).getNum()));
            }
            //商品小计
            params.put("Goods[" + i + "][PM_Money]", Decima2KeeplUtil.stringToDecimal(mGoodsBeanList.get(i).getZHMoney() + ""));

//            params.put("Goods[" + i + "][PM_Name]", mGoodsBeanList.get(i).getPM_Name());

            //提成员工
            if (mStaffListGidList.get(String.valueOf(i)) != null) {
                for (int j = 0; j < mStaffListGidList.get(String.valueOf(i)).size(); j++) {//提成员工GID
                    params.put("Goods[" + i + "][EM_GIDList][" + j + "]", mStaffListGidList.get(String.valueOf(i)).get(j));
                }
            }
        }
//        params.put("DisMoney", etDiscountMoney.getText().toString());//折后金额
//        params.put("ActivityValue", "");
//        params.put("MA_AvailableBalance", mMemberMoney);

        Le("商品消费--first" + params.toString());
        Li("======params====11111===random:" + params);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Le("商品订单提交成功");
                GoodsStepOneBeans stepOneBeans = CommonFun.JsonToObj(responseString, GoodsStepOneBeans.class);
                Li("=======提交成功===========random:" + new Gson().toJson(stepOneBeans));
                mGoodsBeanGID = stepOneBeans.getData().getGID();
                if (mPayWayCode.equals("SMZF")) {
                    //打开扫描界面扫描条形码或二维码
                    Intent openCameraIntent = new Intent(PayConfirmActivity.this, CaptureActivity.class);
                    startActivityForResult(openCameraIntent, 1);
                } else {
                    goodsShopStepTwo();
                }


            }

            @Override
            public void onFailure(String msg) {
                new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        .setContentText(msg)
                        .setConfirmText("了解")
                        .show();

            }
        };
        callBack.setLoadingAnimation(this, "正在提交订单...", false);
        HttpHelper.post(this, HttpAPI.API().GOODS_CONSUME_SUB, params, callBack);//GOODS_CONSUME_SUB
    }

    /**
     * 商品订单支付
     */
    private void goodsShopStepTwo() {

        RequestParams params = new RequestParams();
        //订单GID
        params.put("OrderGID", mGoodsBeanGID);
        //是否发送短信
        params.put("Smsg", cbShortMessage.isChecked() ? 1 : 0);
        if (mZeroType > 0) {
            //应收金额
            params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
            //实收金额
            params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
            //支付金额
            params.put("PayResult[PayTypeList][0][PayMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mZeroTotalMoney + "")));
        } else {
            //应收金额
            params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(etDiscountMoney.getText().toString())));
            //实收金额
            params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(etDiscountMoney.getText().toString())));
            //支付金额
            params.put("PayResult[PayTypeList][0][PayMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(etDiscountMoney.getText().toString())));
        }
        //抹零金额
        params.put("PayResult[EraseOdd]", mZeroMoney);
        //找零金额
        params.put("PayResult[GiveChange]", 0);
        //优惠活动GID
        if (mDiscountActivityGid != null && !mDiscountActivityGid.equals("")) {
            params.put("PayResult[CC_GID]", mDiscountActivityGid);
        }
        //支付方式编码
        params.put("PayResult[PayTypeList][0][PayCode]", mPayWayCode);
        //支付方式名称
        params.put("PayResult[PayTypeList][0][PayName]", mPayWayName);

//        params.put("PayResult[PayPoint]", 0);//抵扣积分

        Li("商品消费--two" + params.toString());
        Li("======params====222===random:" + params);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final SPXF_Success_Bean spxf_success_bean = CommonFun.JsonToObj(responseString, SPXF_Success_Bean.class);
                Li("=============random:" + new Gson().toJson(spxf_success_bean));
                goodsShopStepTwoComplete(spxf_success_bean.getData().getGID());
            }

            @Override
            public void onFailure(String msg) {
                if (msg.contains("SmsSign")) {
                    mSweetAlertDialog = new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，未设置默认签名！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {

                            if (MDZZ != null && "mdzz".equals(MDZZ)) {
                                Intent intent = new Intent(PayConfirmActivity.this, MemberListActivity.class);
                                intent.putExtra("carddd", mMemberCardNo);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(PayConfirmActivity.this, GoodsConsumeActivity.class);
                                intent.putExtra("type", "SPXF");
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.contains("BuySms")) {
                    mSweetAlertDialog = new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，短信库存不足！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (MDZZ != null && "mdzz".equals(MDZZ)) {
                                Intent intent = new Intent(PayConfirmActivity.this, MemberListActivity.class);
                                intent.putExtra("carddd", mMemberCardNo);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                                Intent intent = new Intent(PayConfirmActivity.this, GoodsConsumeActivity.class);
                                intent.putExtra("type", "SPXF");
                                startActivity(intent);
                                finish();
                            }
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

    private void goodsShopStepTwoComplete(final String GID) {
        try {
            mSweetAlertDialog = new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.SUCCESS_TYPE);
            mSweetAlertDialog.setTitleText("支付成功");
            mSweetAlertDialog.setConfirmText("确定");
            if (mOrderType != 1) {
                mSweetAlertDialog.setCancelText("添加为会员");
            }
            mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent intent = new Intent(PayConfirmActivity.this, AddMemberActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });
            mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {

                    //打印小票
                    if (Print) {
                        if (MyApplication.mGoodsConsumeMap.isEmpty()) {
                            GetPrintSet.getPrintParamSet();
                        }
                        new HttpGetPrintContents(PayConfirmActivity.this, MyApplication.SPXF_PRINT_TIMES, GID, intentHandler).SPXF();
                    } else {
                        Intent intent = new Intent(PayConfirmActivity.this, GoodsConsumeActivity.class);
                        intent.putExtra("type", "SPXF");
                        startActivity(intent);
                        finish();
                    }
//                            if (MDZZ != null && "mdzz".equals(MDZZ)) {
////                                Intent intent = new Intent(PayConfirmActivity.this, MemberListActivity.class);
////                                intent.putExtra("carddd", mMemberCardNo);
////                                startActivity(intent);
//                                finish();
//                            } else {

//                            }
                }
            });
            mSweetAlertDialog.show();
        } catch (JsonSyntaxException e) {
            CustomToast.makeText(PayConfirmActivity.this, "打印失败！", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    /**
     * 提示对话框
     *
     * @param msg
     */
    private void hintDialog(String msg) {
        mSweetAlertDialog = new SweetAlertDialog(PayConfirmActivity.this, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText("提示")
                .setContentText(msg + "!")
                .setConfirmText("了解")
                .show();
    }

    @Override
    protected void onDestroy() {
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
        }
        super.onDestroy();
    }

    /**
     * 计算积分
     */
    private void calculatePoint(double money) {
        if (memberInfoBean != null) {
            if (memberInfoBean.getData().getVG_IsIntegral() == 1) {//判断积分开关是否开启
                switch (mPageFlag) {
                    case "KSXF":
                        mGradePoint = money * memberInfoBean.getData().getVS_Value();
                        mGetPoints = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(mGradePoint + ""));
                        break;
                    case "SPXF":
                        break;
                    case "HYCC":
                        break;
                }
            } else {
                mGradePoint = 0;
                mGetPoints = 0;
            }
        }
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
        AlertDialog.Builder builder = new AlertDialog.Builder(PayConfirmActivity.this);
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
                HttpHelper.post(PayConfirmActivity.this, HttpAPI.API().PASSWORDVERIFY, params, new CallBack() {
                    @Override
                    public void onSuccess(String responseString, Gson gson) {
                        PasswordVerifyBean bean = CommonFun.JsonToObj(responseString, PasswordVerifyBean.class);
                        if (bean.isSuccess()) {
                            switch (type) {
                                case 1:
                                    setYuEPayVariable();
//                                    fastPay();
                                    fastPayStepOne();
                                    break;
                                case 2:
                                    setYuEPayVariable();
//                                    goodsConsumePay();
                                    goodsShopStepOne();
                                    break;
                                case 3:
                                    setYuEPayVariable();
                                    //memberRechargeTimePost();
                                    vipInflateCountStepOne();
                                    break;

                            }
                            alertDialog.dismiss();
                        } else {
                            CustomToast.makeText(PayConfirmActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        CustomToast.makeText(PayConfirmActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
            }
        });
        alertDialog.show();
    }


    @Override
    public void onDismiss() {
        YSLUtils.setBackgroundAlpha(1.0f, this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_choise_vip_right:
                spxfmoneyafterdiscount = 0;
                mDiscountMoney = 0;
                mGiveMoney = 0;
                mGetPoints = 0;
                Li("=========================3144 random:");
                tvDiscount.setText("");
                /** 清空优惠信息 */
                mDataBean = null;
                mDiscountActivityGid = "";
                tvPonits.setText("0");
                /*************/
                etDiscountMoney.clearFocus();
                mKSXF_modify_money = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString()) + "");
                Intent intent1 = new Intent(PayConfirmActivity.this, CheckMemberInfoActivity.class);
                startActivityForResult(intent1, 888);
                break;
            case R.id.tv_choose_member:
                spxfmoneyafterdiscount = 0;
                mDiscountMoney = 0;
                mGiveMoney = 0;
                mGetPoints = 0;
                Li("=========================3154 random:");
                tvDiscount.setText("");
                mDiscountBean = null;
//                tvDiscount.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));
                /** 清空优惠信息 */
                mDataBean = null;
                mDiscountActivityGid = "";
                tvPonits.setText("0");
                /*************/
                etDiscountMoney.clearFocus();
                mKSXF_modify_money = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(tvOrderMoney.getText().toString()) + "");
                Intent intent = new Intent(PayConfirmActivity.this, CheckMemberInfoActivity.class);
                startActivityForResult(intent, 888);
                break;
            case R.id.iv_delete_vip:
                ksxfmDiscountMoney = 0.00;
                spxfmoneyafterdiscount = 0;
                if (isKSXF) {
                    //快速消费就把设置为订单金额,不然优惠活动计算的价格是会员价格
                    mDiscountMoney = mMemberDiscountMoney = spxfmoneyafterdiscount = mOrderMoney;
                }
                mOrderType = 2;
                mGetPoints = 0;
                ivChoiseVipRight.setVisibility(View.VISIBLE);//右边图标
                ivDeletVip.setVisibility(View.INVISIBLE);//删除会员图标
                tvVipName.setText("选择会员");
                mMemberInfoBack = null;
                singleVip = null;
                mMemberCardNo = null;
                isVip = false;
                mDiscountActivityGid = "";
                mDiscountBean = null;
                if (isSPXF || isHYCC) {
                    for (int k = 0; k < mGoodsBeanList.size(); k++) {//删除会员后，把等级折后金额还原，
                        if (mGoodsBeanList.get(k).isHaveDengjiMoney() || mGoodsBeanList.get(k).getPercent() == -1) {
                            mGoodsBeanList.get(k).setEachMoney(mGoodsBeanList.get(k).getPM_UnitPrice());
                            mGoodsBeanList.get(k).setZHMoney(mGoodsBeanList.get(k).getPM_UnitPrice() * mGoodsBeanList.get(k).getNum());
                            mGoodsBeanList.get(k).setHaveDengjiMoney(false);
                        }
                        mGoodsBeanList.get(k).setEachPoint(0);
                    }
                    initAdapter(mGoodsBeanList, isSPXF, changeHandler);
                    for (int a = 0; a < mGoodsBeanList.size(); a++) {
                        spxfmoneyafterdiscount += mGoodsBeanList.get(a).getZHMoney();
                    }
                    mDiscountMoney = mMemberDiscountMoney = spxfmoneyafterdiscount;
                    Li("=============spxfmoneyafterdiscount=========delete: random:" + spxfmoneyafterdiscount);
                    etDiscountMoney.setText(spxfmoneyafterdiscount + "");
                    choisedActivities = false;
                    Li("=========================3191 random:");
                    tvDiscount.setText("");
                    mGetPoints = 0;
                    tvPonits.setText(Decima2KeeplUtil.stringToDecimal(mGetPoints + ""));
                    // CustomToast.makeText(PayConfirmActivity.this, "如果选择了优惠活动，请重新选择！", Toast.LENGTH_SHORT).show();
                }
                Li("======================delete: random");
                break;
        }
    }

    /**
     * 选择员工提成
     */
    @Override
    public void click(View v) {
        empos = (int) v.getTag();
        if (isStaff) {
            Intent intent = new Intent(PayConfirmActivity.this, StaffCommissionActivity.class);
            mStaffInfo = mStaffInfoList.get(String.valueOf(empos));
            intent.putExtra("PAGE_FLAG", mPageFlag);
            intent.putExtra("VG_GID", memberInfoBean == null ? "" : memberInfoBean.getData().getVG_GID());
            Bundle bundle = new Bundle();
            bundle.putSerializable("goodsMsg", (Serializable) mGoodsBeanList.get(empos));
            bundle.putSerializable("mStaffInfo", (Serializable) mStaffInfo);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1002);
        } else {
            CustomToast.makeText(PayConfirmActivity.this, "员工提成未开启，请确认后再尝试", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void imagicClick(View view) {
        int pos = (int) view.getTag();
        for (int i = 0; i < mGoodsBeanList.size(); i++) {
            mStaffInfoList.put(String.valueOf(i), mStaffInfoList.get(String.valueOf(pos)));
            mGoodsBeanList.get(i).setEmployeeValue(mGoodsBeanList.get(pos).getEmployeeValue());
        }
        mGoodsPayAdapter.notifyDataSetChanged();
    }

    public class ChangeHandler extends Handler {
        /**
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    try {
                        ZHDataBean = (List<GoodsCheckResponseByType.DataBean.DataListBean>) msg.obj;

                        if (singleVip != null) {
                            jisuanpoint(ZHDataBean);
                        }
                        double m = 0;
                        double n = 0;
                        double l = 0;
                        for (int k = 0; k < ZHDataBean.size(); k++) {
                            m += ZHDataBean.get(k).getZHMoney();
                            n += ZHDataBean.get(k).getPM_UnitPrice() * ZHDataBean.get(k).getNum();
//                            l += ZHDataBean.get(k).getEachPoint() * ZHDataBean.get(k).getNum();
                            Li("=======商品消费清单====" + ZHDataBean.get(k).getZHMoney());
                        }
                        mDiscountMoney = mMemberDiscountMoney = spxfmoneyafterdiscount = m;
//                        tvPonits.setText(Decima2KeeplUtil.stringToDecimal(l + ""));
                        tvCost.setText("¥" + Decima2KeeplUtil.stringToDecimal(m + ""));
                        etDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(m + ""));
                        Li("=========================3253 random:");
                        tvCostMoney.setText(Decima2KeeplUtil.stringToDecimal(m + ""));
                        tvOrderMoney.setText(Decima2KeeplUtil.stringToDecimal(n + ""));
                    } catch (Exception e) {

                    }
//                    mDiscountActivityGid = "";
//                    mDiscountBean = null;
//                    tvDiscount.setText("");
//                    if (mDataBean != null) {
//                        calculateCoupon(mDataBean);
//                    } else {
//                        mDiscountActivityGid = "";
//                        mDiscountBean = null;
//                        tvDiscount.setText("");
//                    }
                    if (mAutoMatchingCoupon) {
                        getCouponList();
                    } else {
                        mDiscountActivityGid = "";
                        mDiscountBean = null;
                        tvDiscount.setText("");
                    }
                    break;
            }
        }
    }

    public class IntentHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent;
            switch (msg.what) {
                case 1:
                    intent = new Intent(PayConfirmActivity.this, GoodsConsumeActivity.class);
                    intent.putExtra("type", "SPXF");
                    startActivity(intent);
                    finish();
                    break;
                case 2:
                    intent = new Intent(PayConfirmActivity.this, GoodsConsumeActivity.class);
                    intent.putExtra("type", "HYCC");
                    startActivity(intent);
                    finish();
                    break;
                case 3:
                    if (!"".equals(MDZZ) && MDZZ != null) {
                        intent = new Intent(PayConfirmActivity.this, MemberListActivity.class);
                        intent.putExtra("carddd", mMemberCardNo);
                        startActivity(intent);
                        finish();
                    } else {
                        finish();
                    }
                    break;

            }
        }
    }


    /**
     * 获取短信开关
     */
    private void getSmsSet(final String code) {
        HttpHelper.post(PayConfirmActivity.this, HttpAPI.API().SMS_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SmsSwitch bean = CommonFun.JsonToObj(responseString, SmsSwitch.class);
                for (int i = 0; i < bean.getData().size(); i++) {
                    if (bean.getData().get(i).getST_Code().equals(code)) {
                        if (bean.getData().get(i).getST_State() == null || !bean.getData().get(i).getST_State().equals("1")) {
                            cbShortMessage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CustomToast.makeText(PayConfirmActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
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
     * //     * 获取打印设置
     * //
     */
    private void getPrintSet() {  // http://dj.zhiluo.net/api/PrintSet/GetPrintSet
        HttpHelper.post(PayConfirmActivity.this, HttpAPI.API().PRINTSET, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    PrintSetBean bean = CommonFun.JsonToObj(responseString, PrintSetBean.class);

                    if (bean.getData().getPS_IsEnabled() != 1) {
                        cbPrint.setChecked(false);
                        Print = false;
                        pribean = 0;
                    } else {
                        cbPrint.setChecked(true);
                        Print = true;
                        pribean = 1;
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(PayConfirmActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void queryrules() {
        RequestParams params = new RequestParams();
        params.put("PageIndex", 1);
        params.put("PageSize", 99);
        params.put("WR_Name", "");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                rulesBean = CommonFun.JsonToObj(responseString, TimesRulesBean.class);
                for (int i = 0; i < rulesBean.getData().getDataList().size(); i++) {
                    rulesList.add(rulesBean.getData().getDataList().get(i).getWR_Name());
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(PayConfirmActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, MyApplication.BASE_URL + "WouldRules/QueryPageDataList", params, callBack);
    }

    private void barCode(int type, String Gid) {
        RequestParams params = new RequestParams();
        params.put("Code", mSmPayCode);
        params.put("Money", etDiscountMoney.getText().toString());
        params.put("OrderGID", Gid);
        params.put("OrderType", type);//10商品消费 20充次 30计时消费 40 充值 50 套餐消费 60快速消费
        params.put("OrderNo", mOrderNo);
        params.put("OrderPayInfo[PayPoint]", 0);
        params.put("OrderPayInfo[PayTotalMoney]", etDiscountMoney.getText().toString());
        params.put("OrderPayInfo[GiveChange]", 0);
        params.put("OrderPayInfo[DisMoney]", etDiscountMoney.getText().toString());
        params.put("OrderPayInfo[IsEraseZero]", false);
        params.put("OrderPayInfo[PayTypeList][0][PayName]", mPayWayName);
        params.put("OrderPayInfo[PayTypeList][0][PayCode]", mPayWayCode);
        params.put("OrderPayInfo[PayTypeList][0][PayMoney]", etDiscountMoney.getText().toString());

        Li("======barCode====params===random:" + params);

        presenter.barCode(params);
    }

    private void querPay() {
        RequestParams params = new RequestParams();
        params.put("ResultGID", barcodeResultGID);
        presenter.querPay(params);
    }


    /**
     * 获取优惠信息列表
     */
    private void getCouponList() {
        mCouponList.clear();
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        client.post(HttpAPI.API().DISSCOUNT_ACTIVITY, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                DiscountTypeBean mDiscountTypeBean = CommonFun.JsonToObj(responseString, DiscountTypeBean.class);
                List<DiscountTypeBean.DataBean> mDataBeans = mDiscountTypeBean.getData();
                Li(mMemberCardNo + "===mMemberCardNo===random:" + new Gson().toJson(mDataBeans));
                double mBeforePrice = Double.parseDouble(tvCostMoney.getText().toString());
                for (int i = 0; i < mDataBeans.size(); i++) {
                    if (mDataBeans.get(i).getRP_RechargeMoney() <= mBeforePrice && mDataBeans.get(i).getRP_Type() == 2) {
                        Li("========中标=======random:" + new Gson().toJson(mDataBeans.get(i)));
                        if (mDataBeans.get(i).getRP_ValidType() == 4) {
                            if (mMemberInfoBack == null || mMemberCardNo == null || mMemberCardNo.equals("00000")) {
                                continue;
                            } else if (!TextUtils.isEmpty(mMemberInfoBack.getVIP_Birthday()) && mMemberInfoBack.getVIP_Birthday().substring(5, 10).equals(DataUtils.getDataTime().substring(5, 10))) {
                                //会员生日券
                                mCouponList.add(mDataBeans.get(i));
                            } else {
                                continue;
                            }
                        } else {
                            mCouponList.add(mDataBeans.get(i));
                        }
                    }
                }
                Li("========中标mCouponList=======random:" + new Gson().toJson(mCouponList));

                //折扣
                List<DiscountTypeBean.DataBean> breakList = new ArrayList<>();
                //减
                List<DiscountTypeBean.DataBean> reduceList = new ArrayList<>();
                //送
                List<DiscountTypeBean.DataBean> giveList = new ArrayList<>();
                for (int j = 0, len = mCouponList.size(); j < len; j++) {
                    DiscountTypeBean.DataBean dataBean = mCouponList.get(j);
                    if (dataBean.getRP_Discount() > 0) {
                        breakList.add(dataBean);
                    } else if (dataBean.getRP_ReduceMoney() > 0) {
                        reduceList.add(dataBean);
                    } else if (dataBean.getRP_GiveMoney() > 0) {
                        giveList.add(dataBean);
                    }
                }
                if (breakList.size() > 0) {
                    Collections.sort(breakList, new Comparator<DiscountTypeBean.DataBean>() {
                        /*
                         * 返回负数表示：p1 小于p2，
                         * 返回0 表示：p1和p2相等，
                         * 返回正数表示：p1大于p2
                         */
                        public int compare(DiscountTypeBean.DataBean p1, DiscountTypeBean.DataBean p2) {
                            if (p1.getRP_Discount() > p2.getRP_Discount()) {
                                return 1;
                            }
                            if (p1.getRP_Discount() == p2.getRP_Discount()) {
                                return 0;
                            }
                            return -1;
                        }
                    });
                }
                if (reduceList.size() > 0) {
                    Collections.sort(reduceList, new Comparator<DiscountTypeBean.DataBean>() {
                        public int compare(DiscountTypeBean.DataBean p1, DiscountTypeBean.DataBean p2) {
                            if (p1.getRP_ReduceMoney() < p2.getRP_ReduceMoney()) {
                                return 1;
                            }
                            if (p1.getRP_ReduceMoney() == p2.getRP_ReduceMoney()) {
                                return 0;
                            }
                            return -1;
                        }
                    });
                }
                if (giveList.size() > 0) {
                    Collections.sort(giveList, new Comparator<DiscountTypeBean.DataBean>() {
                        public int compare(DiscountTypeBean.DataBean p1, DiscountTypeBean.DataBean p2) {
                            if (p1.getRP_GiveMoney() < p2.getRP_GiveMoney()) {
                                return 1;
                            }
                            if (p1.getRP_GiveMoney() == p2.getRP_GiveMoney()) {
                                return 0;
                            }
                            return -1;
                        }
                    });
                }
                mCouponList.clear();
                mCouponList.addAll(breakList);
                mCouponList.addAll(reduceList);
                mCouponList.addAll(giveList);
                Li("排序后的结果random：" + new Gson().toJson(breakList));
                Li("排序后的结果random：" + new Gson().toJson(reduceList));
                Li("排序后的结果random：" + new Gson().toJson(giveList));
//                Li("排序后的结果random：" + new Gson().toJson(mCouponList));
                if (mCouponList.size() > 0) {
                    calculateCoupon(mCouponList.get(0));
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 自动选择时优惠券计算
     */
    private void calculateCoupon(DiscountTypeBean.DataBean couponData) {

        Li(mConsumeType + "====calculateCoupon====Gson===random:" + new Gson().toJson(couponData));

        isChoiceDiscount = true;
        if (mDiscountBean != null) {

            Li("===start=calculateCoupon====mGiveMoney===========random:" + mGiveMoney);
            mGiveMoney = mGiveMoney - mDiscountBean.getGiveMoney();
            Li("===end=calculateCoupon====mGiveMoney===========random:" + mGiveMoney);
//                mDiscountMoney = mDiscountMoney + mDiscountBean.getDiscountMoney();
//                etDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mDiscountMoney)));
            if (mMemberInfoBack != null) {
                mGetPoints = mGetPoints - mDiscountBean.getGivePoint();
            }
        }
        choisedActivities = true;

        //获取优惠信息
        mDataBean = couponData;
        if (mDataBean != null) {
            tvDiscount.setText(mDataBean.getRP_Name());//显示优惠内容
            mDiscountActivityGid = mDataBean.getGID();
        } else {
            mDiscountActivityGid = "";
        }
//            mDiscountMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(etDiscountMoney.getText().toString()));//折后金额
        if (isKSXF && mMemberInfoBack == null) {
            //快速消费就把设置为订单金额,不然优惠活动计算的价格是会员价格
            mDiscountMoney = mMemberDiscountMoney = spxfmoneyafterdiscount = mOrderMoney;
        }
        if (mConsumeType == 1) {
            mDiscountBean = calculateRechargeDiscount(mMemberDiscountMoney);
        }
        if (mConsumeType == 2) {
            mDiscountBean = calculateActivityDiscount(mMemberDiscountMoney);
        }
        Li(mMemberDiscountMoney + "==mMemberDiscountMoney==calculateCoupon=======DiscountMoney()===random:" + mDiscountBean.getDiscountMoney());
        mDiscountMoney = mMemberDiscountMoney - mDiscountBean.getDiscountMoney();
        mGiveMoney = mDiscountBean.getGiveMoney();
        if ("KSXF".equals(mPageFlag)) {
            if (mMemberInfoBack != null && singleVip != null) {
                mGetPoints = mDiscountMoney * singleVip.getData().getVS_Value();
            }
        }
        if (mDiscountBean.getGivePoint() > 0 && mMemberInfoBack != null) {//折扣大于0才积分，为0就不积分
            mGetPoints = DoubleMathUtil.add(mGetPoints, mDiscountBean.getGivePoint());
        }
        mRechargeTotal = mRechargeTotal + mGiveMoney;
        Li("====CouponBean====mGiveMoney===========random:" + mGiveMoney);
        Li(mDiscountMoney + "====mDiscountMoney===========1111====random:" + mGetPoints + "        mRechargeTotal:" + mRechargeTotal);
        etDiscountMoney.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mDiscountMoney)));
        tvCostMoney.setText(Decima2KeeplUtil.stringToDecimal(mDiscountMoney + ""));
        tvPonits.setText(Decima2KeeplUtil.stringToDecimal((mGetPoints <= 0 ? 0 : mGetPoints) + ""));
        isChoiceDiscount = false;
    }

    /**
     * 抹零
     */
    private void initZero() {
        if (mZeroType < 1) {
            return;
        }
        String flagMoney = etDiscountMoney.getText().toString();
//        String flagMoney = Decima2KeeplUtil.stringToDecimal(Double.parseDouble(flagMoney)+"");
//        double moneyFlag = Double.parseDouble(flagMoney);
        double moneyFlag = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(Double.parseDouble(flagMoney) + ""));
        Li(moneyFlag + "====moneyFlagmoneyFlagmoneyFlag===========random:" + mZeroMoney);
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
