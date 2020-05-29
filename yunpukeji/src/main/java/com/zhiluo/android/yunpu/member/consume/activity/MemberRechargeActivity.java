package com.zhiluo.android.yunpu.member.consume.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.activity.PayConfirmActivity;
import com.zhiluo.android.yunpu.consume.activity.StaffCommissionActivity;
import com.zhiluo.android.yunpu.consume.activity.UnionPayActivity;
import com.zhiluo.android.yunpu.consume.jsonbean.DiscountTypeBean;
import com.zhiluo.android.yunpu.goods.manager.bean.BarCodePayBean;
import com.zhiluo.android.yunpu.goods.manager.bean.QuerPayBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.consume.adapter.RechargeTypeAdapter;
import com.zhiluo.android.yunpu.member.consume.adapter.VariableMoneyAdapter;
import com.zhiluo.android.yunpu.member.consume.bean.VipInflateMoneyBean;
import com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.mvp.presenter.PostVipPresenter;
import com.zhiluo.android.yunpu.mvp.presenter.SaoMaPayPresntter;
import com.zhiluo.android.yunpu.mvp.view.IPostVipView;
import com.zhiluo.android.yunpu.mvp.view.SaoMaPayView;
import com.zhiluo.android.yunpu.print.bean.HYCZ_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.PrintSetBean;
import com.zhiluo.android.yunpu.print.util.GetPrintSet;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.sms.jsonbean.SmsSwitch;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.activity.recharge.AddRechargeActivity;
import com.zhiluo.android.yunpu.ui.adapter.KeyboardAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomGridView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.KeyboardView;
import com.zhiluo.android.yunpu.ui.view.LoadingDialogUtil;
import com.zhiluo.android.yunpu.ui.view.NoDoubleClickListener;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.utils.KeyboardUtils;
import com.zhiluo.android.yunpu.yslutils.YSLPayPopWindow;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 会员充值界面,用于会员余额的充值
 */
public class MemberRechargeActivity extends BaseActivity implements KeyboardAdapter.OnKeyboardClickListener, VariableMoneyAdapter.OnOneItemClickListener,
        VariableMoneyAdapter.OnTwoItemClickListener, VariableMoneyAdapter.OnThreeItemClickListener, YSLPayPopWindow.OnItemClickListener,
        YSLPayPopWindow.OnDismissListener, View.OnClickListener {
    @Bind(R.id.ll_scan_memberrechargeactivity)
    LinearLayout llScanMemberrechargeactivity;
    private MemberInfoBean.DataBean vipdatalist;
    private Button btnSubmit;
    private TextView tvYSMoney, tvGiveIntegral, tvTitle;
    private EditText etSearch;
    private ImageView ivScan;
    DecimalFormat df = new DecimalFormat("0.00");
    private String orderNumber; //会员充值---订单号
    private TextView mCommissionStaff;//提成员工姓名显示控件声明
    private LinearLayout llstaff;
    private String MDZZ;
    private TextView mTvChooseMember;
    private ImageView mIvChooseMember;
    private AllMemberListBean.DataBean.DataListBean mMemberInfo = new AllMemberListBean.DataBean.DataListBean();//会员信息
    private TextView tvMemberName, tvMemberCard, tvBalance, tvIntegral;
    private AllMemberListBean.DataBean.DataListBean mVipInfo;//会员信息
    private CustomGridView gvRechargeType;
    private DiscountTypeBean mDiscountTypeBean;
    private List<DiscountTypeBean.DataBean> mRechargeTypeList = new ArrayList<>();
    private RechargeTypeAdapter mAdapter;
    private double mTotal, mGiveIntegral;
    private int mPos;
    private String mCC_GID;//充值优惠GID
    private TextView tvRechargeTotal, tvRechargenum;
    private double mScale;

    private List<String> datas;
    private KeyboardView mNumKeyboardView;
    private ScrollView scrollView;
    private Handler mHandler;
    private String mInput = "";
    private String mCard;
    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间
    private String content;
    private VariableMoneyAdapter adapter;
    private RecyclerView recyclerView;
    private TextView tvPayConfirmDiscountMoney;//折后金额
    private TextView tvPayConfirmGetPoints;//获得积分
    private TextView tvPayConfirmEmployee;//提成员工
    private TextView tvPayConfirmReceivable;//充值合计
    private TextView tvConstMoney;//收款金额
    private CheckBox cbMessage;//短信
    private CheckBox cbPrint;//打印
    private int pribean = 0;
    private TextView tvSubmit;//收款
    private int mConsumeType = 1;//消费优惠类型（1：充值优惠，2：消费优惠）
    private ArrayList<String> mStaffListGid = new ArrayList<>();//提成员工GID
    private StringBuilder mStaffName = new StringBuilder("");//提成员工姓名
    private String mPageFlag = "HYCZ";
    private List<ReportMessageBean.DataBean.EmplistBean> mStaffInfo;//提成员工信息
    private double mGiveMoney;//赠送金额
    private double mRechargeMoney;//充值金额
    private String mPayWayName;
    private String mPayWayCode;
    private SweetAlertDialog mSweetAlertDialog;
    private String mVipInflateMoneyGID;
    private YSLPayPopWindow mPopWindow;
    private EditText edCotent;
    private String mPayName;
    private String mPayCode;
    private boolean mZfbSwitch;//支付宝记账开关
    private boolean mWXSwitch, mSmSwitch, mOtherSwitch;//微信记账开关
    private boolean mMustCard;
    private boolean isStaff;//员工提成
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;//参数开关，这里要用到微信和支付宝记账的开关
    private EditText edRemakes;
    private String mVipCard = null;
    private RelativeLayout rChoiseVip;
    private String vipCardNo;
    private boolean shoudongInput = false;
    private Handler myHandler;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonok, button00, btnadd;
    private LinearLayout lDelet;
    private StringBuilder mEditContentBuilder;
    private LinearLayout keybord;

    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] intentFiltersArray;
    private String[][] techListsArray;
    private String mSmPayCode = "";//扫码返回条码
    private SaoMaPayPresntter presenter;
    private SaoMaPayView mView;
    private String barcodeResultGID;
    private Dialog mDialog;
    private TextView tvOrderTime;
    private ImageView igOrderTime;
    private Dialog datesDialog;
    private PostVipPresenter vippresenter;
    private IPostVipView vipView;
    private boolean isSearch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(MemberRechargeActivity.this);
        orderNumber = CreateOrder.createOrder("CZ");//获取订单号
        setContentView(R.layout.activity_ysl_vip_recharge);
        ButterKnife.bind(this);
        initView();
        initVar();
        initData();
        setCbPrint();
        setListener();
        comfirmParams();
        setCbShortMessage("002");


        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            Intent nfcIntent = new Intent(this, getClass());
            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mPendingIntent =
                    PendingIntent.getActivity(this, 0, nfcIntent, 0);

        }

    }

    private void initVar() {
        adapter = new VariableMoneyAdapter(mRechargeTypeList, this, this, this, this, mMemberInfo);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        mEditContentBuilder = new StringBuilder("");


        presenter = new SaoMaPayPresntter(this);
        mView = new SaoMaPayView() {
            @Override
            public void barCodeSuccess(BarCodePayBean entity) {
                barcodeResultGID = entity.getData().getGID();
                if (mDialog != null) {
                    LoadingDialogUtil.closeDialog(mDialog);
                }
                vipInflateMoneyStepTwo();
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
                    CustomToast.makeText(MemberRechargeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void barCodeFailString(String responseString) {
                if (mDialog != null) {
                    LoadingDialogUtil.closeDialog(mDialog);
                }
                CustomToast.makeText(MemberRechargeActivity.this, responseString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void querPaySuccess(QuerPayBean entity) {
                if (entity.isSuccess()) {
                    if (mDialog != null) {
                        LoadingDialogUtil.closeDialog(mDialog);
                    }
//                    vipInflateMoneyStepTwo();
                    payComplete(mVipInflateMoneyGID);
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
                    CustomToast.makeText(MemberRechargeActivity.this, YSLUtils.payResult(entity.getCode()), Toast.LENGTH_SHORT).show();
                } else {
                    if (mDialog != null) {
                        LoadingDialogUtil.closeDialog(mDialog);
                    }
                    CustomToast.makeText(MemberRechargeActivity.this, entity.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        presenter.attachView(mView);

        vippresenter = new PostVipPresenter(this);
        vipView = new IPostVipView() {
            @Override
            public void getvipsuccesss(MemberInfoBean bean) {
                isSearch = true;
                vipdatalist = bean.getData();
                mVipCard = vipdatalist.getVCH_Card();
                initnew();
                String mMemberName = vipdatalist.getVIP_Name();
                if (mMemberName != null && !"".equals(mMemberName)) {
                    mTvChooseMember.setText(mMemberName);
                } else {
                    mTvChooseMember.setText(mVipCard);
                }
                if (vipdatalist.getVG_IsAccount() != 1) {
                    new SweetAlertDialog(MemberRechargeActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("该会员不能储值,请前往PC端进行相关设置！")
                            .setConfirmText("了解")
                            .show();
                }

                judgeactivity(vipdatalist.getVIP_Birthday());
                //选择会员后刷新选择会员后的信息
                Message message = new Message();
                message.what = 9;
                mInputHandler.sendMessage(message);
            }

            @Override
            public void getvipfail(String result) {

                if (MyApplication.VIP_CARD != null) {
                    vippresenter.postVip(MyApplication.VIP_CARD, isSearch);
                    MyApplication.VIP_CARD = null;
                } else {
                    mTvChooseMember.setText("选择会员");
                    tvMemberName.setText("");
                    tvMemberCard.setText("");
                    tvIntegral.setText("");
                    tvBalance.setText("0.00");
                }
                if (result.contains("没有任何执行操作")) {
                    //查询结果有多个 跳到选择会员页面选择
                    Intent intent = new Intent(MemberRechargeActivity.this, CheckMemberInfoActivity.class);
                    intent.putExtra("number", content);
                    startActivityForResult(intent, 888);
                }
            }
        };
        vippresenter.attachView(vipView);

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
                            CustomToast.makeText(MemberRechargeActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
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
        HttpHelper.post(MemberRechargeActivity.this, HttpAPI.API().SMS_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SmsSwitch bean = CommonFun.JsonToObj(responseString, SmsSwitch.class);
                for (int i = 0; i < bean.getData().size(); i++) {
                    if (bean.getData().get(i).getST_Code().equals(code)) {
                        if (bean.getData().get(i).getST_State() == null || !bean.getData().get(i).getST_State().equals("1")) {
                            cbMessage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CustomToast.makeText(MemberRechargeActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
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
        HttpHelper.post(MemberRechargeActivity.this, HttpAPI.API().PRINTSET, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                PrintSetBean bean = CommonFun.JsonToObj(responseString, PrintSetBean.class);
                try {
                    if (bean != null) {
                        if (bean.getData().getPS_IsEnabled() != 1) {
                            cbPrint.setChecked(false);
                            pribean = 0;
                        } else {
                            cbPrint.setChecked(true);
                            pribean = 1;
                        }
                    }
                } catch (Exception e) {

                }


            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    protected void onStop() {
        //终止检卡
        new CardOperationUtils().close();
        super.onStop();
    }

    private void comfirmParams() {
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {
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
                    //员工提成
                    if ("301".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            isStaff = true;
                            llstaff.setVisibility(View.VISIBLE);
                        } else {
                            isStaff = false;
                            llstaff.setVisibility(View.GONE);
                        }
                    }
                    //扫码支付
                    if ("111".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mSmSwitch = true;
                        } else {
                            mSmSwitch = false;
                        }
                    }
                    //其他支付
                    if ("113".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mOtherSwitch = true;
                        } else {
                            mOtherSwitch = false;
                        }
                    }

                    //必须刷卡
                    if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mMustCard = true;
                            rChoiseVip.setEnabled(false);
                            llScanMemberrechargeactivity.setEnabled(false);
                            llScanMemberrechargeactivity.setBackgroundColor(getResources().getColor(R.color.lightgray));
                            etSearch.setHint("请使用刷卡机刷卡");
                            etSearch.setFocusable(false);
                            etSearch.setFocusableInTouchMode(false);
                            ivScan.setEnabled(false);


                        } else {
                            mMustCard = false;
                            rChoiseVip.setEnabled(true);
                            llScanMemberrechargeactivity.setEnabled(true);
                            llScanMemberrechargeactivity.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                            etSearch.setHint("请输入会员卡号/手机号");
                            etSearch.setFocusable(true);
                            etSearch.setFocusableInTouchMode(true);
                            ivScan.setEnabled(true);
                        }
                    }
                }
            }
        } else {
            getSystemSwitch();
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
                    if (mSwitchEntity.size() > 0) {
                        for (int i = 0; i < mSwitchEntity.size(); i++) {

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
                            //员工提成
                            if ("301".equals(mSwitchEntity.get(i).getSS_Code())) {
                                if (mSwitchEntity.get(i).getSS_State() == 1) {
                                    isStaff = true;
                                    llstaff.setVisibility(View.VISIBLE);
                                } else {
                                    isStaff = false;
                                    llstaff.setVisibility(View.GONE);
                                }
                            }
                            //扫码支付
                            if ("111".equals(mSwitchEntity.get(i).getSS_Code())) {
                                if (mSwitchEntity.get(i).getSS_State() == 1) {
                                    mSmSwitch = true;
                                } else {
                                    mSmSwitch = false;
                                }
                            }
                            //其他支付
                            if ("113".equals(mSwitchEntity.get(i).getSS_Code())) {
                                if (mSwitchEntity.get(i).getSS_State() == 1) {
                                    mOtherSwitch = true;
                                } else {
                                    mOtherSwitch = false;
                                }
                            }
                            //必须刷卡
                            if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                                if (mSwitchEntity.get(i).getSS_State() == 1) {
                                    mMustCard = true;
                                    rChoiseVip.setEnabled(false);
                                    llScanMemberrechargeactivity.setEnabled(false);
                                    llScanMemberrechargeactivity.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                    etSearch.setHint("请使用刷卡机刷卡");
                                    etSearch.setFocusable(false);
                                    etSearch.setFocusableInTouchMode(false);
                                    ivScan.setEnabled(false);


                                } else {
                                    mMustCard = false;
                                    rChoiseVip.setEnabled(true);
                                    llScanMemberrechargeactivity.setEnabled(true);
                                    llScanMemberrechargeactivity.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                                    etSearch.setHint("请输入会员卡号/手机号");
                                    etSearch.setFocusable(true);
                                    etSearch.setFocusableInTouchMode(true);
                                    ivScan.setEnabled(true);
                                }
                            }
                        }
                    } else {
                        isStaff = mZfbSwitch = mWXSwitch = mSmSwitch = mOtherSwitch = mMustCard = false;

                    }
                } else {
                    isStaff = mZfbSwitch = mWXSwitch = mSmSwitch = mOtherSwitch = mMustCard = false;

                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MemberRechargeActivity.this, "获取系统开关失败" + msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(MemberRechargeActivity.this, HttpAPI.API().PRE_LOAD, callBack);
    }

    @SuppressLint("WrongConstant")
    private void initView() {
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
        buttonok = findViewById(R.id.btn_keyboard_confirm);
        lDelet = findViewById(R.id.btn_keyboard_delete);
        keybord = findViewById(R.id.ll_fast_consume_keyboard);
        keybord.setVisibility(View.GONE);
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
        buttonok.setOnClickListener(this);
        lDelet.setOnClickListener(this);

        tvTitle = findViewById(R.id.tv_title);
        etSearch = (EditText) findViewById(R.id.et_membercard_memberrechargeactivity);
        mTvChooseMember = (TextView) findViewById(R.id.tv_choose_member);
        mIvChooseMember = (ImageView) findViewById(R.id.iv_choose_member);
        ivScan = (ImageView) findViewById(R.id.iv_scan_memberrechargeactivity);
        btnSubmit = (Button) findViewById(R.id.btn_recharge_submit);
        tvYSMoney = (TextView) findViewById(R.id.tv_recharge_ys);
        llstaff = findViewById(R.id.ll_pay_confirm_employee);
        mCommissionStaff = (TextView) findViewById(R.id.tv_staff_commission_name);
        tvMemberName = (TextView) findViewById(R.id.tv_member_info_name);
        tvMemberCard = (TextView) findViewById(R.id.tv_member_info_card);
        tvBalance = (TextView) findViewById(R.id.tv_member_info_balance);
        tvIntegral = (TextView) findViewById(R.id.tv_member_info_integral);
        gvRechargeType = (CustomGridView) findViewById(R.id.gv_recharge_type);
        tvGiveIntegral = (TextView) findViewById(R.id.tv_recharge_get_integral);
        tvRechargeTotal = (TextView) findViewById(R.id.tv_recharge_total);
        tvRechargenum = (TextView) findViewById(R.id.tv_recharge_number);
        mNumKeyboardView = (KeyboardView) findViewById(R.id.keyboard_view);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        recyclerView = findViewById(R.id.recycler_view_recharge);
        tvPayConfirmDiscountMoney = findViewById(R.id.tv_pay_confirm_discount_money);
        tvPayConfirmGetPoints = findViewById(R.id.tv_pay_confirm_get_points);
        tvPayConfirmEmployee = findViewById(R.id.tv_pay_confirm_employee);
        tvConstMoney = findViewById(R.id.tv_constmoney);
        cbMessage = findViewById(R.id.cb_short_message);
        cbPrint = findViewById(R.id.cb_print);
        tvSubmit = findViewById(R.id.tv_pay_confirm_submit);
        tvPayConfirmReceivable = findViewById(R.id.tv_pay_confirm_receivable);
        edRemakes = findViewById(R.id.tv_remakes);
        rChoiseVip = findViewById(R.id.r_choise_vip);
        tvOrderTime = findViewById(R.id.et_add_member_order_date);
        igOrderTime = findViewById(R.id.iv_add_member_order_date);
        tvOrderTime.setText(DateTimeUtil.getReallyTimeNow());
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 1) {
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
                if (msg.what == 2) {
                    double d = 0;
                    if (mInput.length() > 0 && !mInput.equals(".")) {
                        d = Double.parseDouble(mInput.trim());
                        if (!mInput.contains(".")) {
                            int value = (int) d;
                            mRechargeTypeList.get(mRechargeTypeList.size() - 1).setFlag(false);
                            mRechargeTypeList.get(mRechargeTypeList.size() - 1).setRP_RechargeMoney(value);
                        } else {
                            mRechargeTypeList.get(mRechargeTypeList.size() - 1).setFlag(true);
                            mRechargeTypeList.get(mRechargeTypeList.size() - 1).setRP_RechargeMoney(d);
                        }

                        if (mInput.equals("0") || mInput.equals("0.")) {
                            mRechargeTypeList.get(mRechargeTypeList.size() - 1).setShow(true);
                            mRechargeTypeList.get(mRechargeTypeList.size() - 1).setStr(mInput);
                        } else {
                            mRechargeTypeList.get(mRechargeTypeList.size() - 1).setShow(false);
                        }
                    } else {
                        mRechargeTypeList.get(mRechargeTypeList.size() - 1).setRP_RechargeMoney(0);
                        mRechargeTypeList.get(mRechargeTypeList.size() - 1).setShow(false);
                    }
                    mRechargeTypeList.get(mRechargeTypeList.size() - 1).setRP_GiveMoney(0);
                    mAdapter.notifyDataSetChanged();
                    mRechargeMoney = d;
                    mRechargeTypeList.get(mRechargeTypeList.size() - 1).setRP_RechargeMoney(mRechargeMoney);
                    mGiveMoney = 0;
                    mTotal = mRechargeMoney;
                    mGiveIntegral = DoubleMathUtil.mul(mScale, mRechargeMoney);
                    tvYSMoney.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mRechargeMoney)));
                    tvGiveIntegral.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mGiveIntegral)));
                    tvRechargeTotal.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mTotal)));
                }
                return false;
            }
        });
    }

    private void setListener() {
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vipdatalist != null && !TextUtils.isEmpty(etSearch.getText())) {
                    if (vipdatalist.getVG_IsAccount() == 1) {
                        if (mRechargeMoney > 0) {
                            showPayPop();
                        } else {
                            hintDialog("请选择充值金额");
                        }
                    } else {
                        hintDialog("该会员不能储值,请前往PC端进行相关设置！");
                    }
                } else {
                    hintDialog("请选择会员");
                }
            }
        });
        //选择员工
        tvPayConfirmEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isStaff) {
                    Intent intent = new Intent(MemberRechargeActivity.this, StaffCommissionActivity.class);

                    intent.putExtra("PAGE_FLAG", mPageFlag);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mStaffInfo", (Serializable) mStaffInfo);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 1002);
                } else {
                    CustomToast.makeText(MemberRechargeActivity.this, "员工提成未开启，请确认后再尝试！", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //设置选择会员监听
        rChoiseVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberRechargeActivity.this, CheckMemberInfoActivity.class);
                startActivityForResult(intent, 888);
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

        //选择支付方式
        btnSubmit.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                if (vipdatalist != null) {
                    if (vipdatalist.getVG_IsAccount() == 1) {
                        if (mTotal > 0) {
                            Intent intent = new Intent(MemberRechargeActivity.this, PayConfirmActivity.class);
                            intent.putExtra(MyApplication.PAGE_FLAG, "HYCZ");//标识
                            intent.putExtra("VIP_INFO", vipdatalist);//会员信息
                            intent.putExtra(MyApplication.ORDER_NO, orderNumber);//订单号
                            intent.putExtra("CZ_MONEY", mRechargeMoney);
                            intent.putExtra("ZS_MONEY", mGiveMoney);
                            intent.putExtra("TOTAL_MONEY", mTotal);
                            intent.putExtra("Integral", mGiveIntegral);
                            intent.putExtra("MDZZ", MDZZ);
                            intent.putExtra("CC_GID", mCC_GID);
                            startActivity(intent);
                        } else {
                            hintDialog("请选择充值金额");
                        }
                    } else {
                        hintDialog("该会员不能储值,请前往PC端进行相关设置！");
                    }
                } else {
                    hintDialog("请选择会员");
                }
            }
        });

        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("mdzz".equals(MDZZ)) {
//                    Intent intent = new Intent(MemberRechargeActivity.this, YSLMemberDetailsActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("VIP_INFO", mVipInfo);
//                    intent.putExtra("bundle", bundle);
//                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MemberRechargeActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(MemberRechargeActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        igOrderTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvOrderTime.getText().toString().isEmpty()) {
                    showReallyDateDialog(DateUtil.getReallyDateForString(DateTimeUtil.getReallyTimeNow()), tvOrderTime);
//                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvOrderTime);
                } else {
//                    showDateDialog(DateUtil.getDateForString(tvOrderTime.getText().toString()), tvOrderTime);
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
                    CustomToast.makeText(MemberRechargeActivity.this, "打印开关未开启，请设置", Toast.LENGTH_SHORT).show();
                    cbPrint.setChecked(false);
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ("mdzz".equals(MDZZ)) {
//                Intent intent = new Intent(MemberRechargeActivity.this, MemberDetailsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("VIP_INFO", mVipInfo);
//                intent.putExtra("bundle", bundle);
//                startActivity(intent);
            } else {
                Intent intent = new Intent(MemberRechargeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
            finish();
        }
        return true;
    }


    private void initnew() {

        if (vipdatalist.getVIP_Name() != null) {
            tvMemberName.setText(vipdatalist.getVIP_Name());
        } else {
            tvMemberName.setText(vipdatalist.getVCH_Card());
        }
        tvMemberCard.setText(vipdatalist.getVCH_Card());
        tvIntegral.setText(df.format(vipdatalist.getMA_AvailableIntegral()));
        tvBalance.setText(df.format(vipdatalist.getMA_AvailableBalance()));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            etSearch.setText(scanResult);
        } else if (requestCode == 1 && resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            mSmPayCode = bundle.getString("result");
            mDialog = LoadingDialogUtil.createLoadingDialog(MemberRechargeActivity.this, "支付中...", false);
            barCode();
        }
        if (requestCode == 2 && resultCode == 2) {
            mCommissionStaff.setText(data.getStringExtra("employee_name"));
        }

        if (requestCode == 888 && resultCode == 2222) {
            mMemberInfo = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");
            if (mMemberInfo != null) {
                if (mMemberInfo.getVIP_Overdue() == null || mMemberInfo.getVIP_Overdue().equals("")) {
//                    postVip(mMemberInfo.getVCH_Card());
                } else {
                    if (mMemberInfo.getVIP_IsForver() == 0) {
                        if (!isOverTime(mMemberInfo.getVIP_Overdue())) {
                            return;
                        }
                    }
                }

                if (mMemberInfo.getVIP_Name() != null && !"".equals(mMemberInfo.getVIP_Name())) {
                    mTvChooseMember.setText(mMemberInfo.getVIP_Name());
                }
                mVipCard = mMemberInfo.getVCH_Card();
                isSearch = false;
                etSearch.setText(mVipCard);
                etSearch.setSelection(mVipCard.length());
                if (mMemberInfo.getVIP_Name() != null) {
                    tvMemberName.setText(mMemberInfo.getVIP_Name());
                } else {
                    tvMemberName.setText(mMemberInfo.getVCH_Card());
                }
                tvMemberCard.setText(mMemberInfo.getVCH_Card());
                tvBalance.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableBalance() + ""));
                tvIntegral.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableIntegral() + ""));


            } else {
                mTvChooseMember.setText("选择会员");
                tvMemberName.setText("无");
                tvMemberCard.setText("无");
                tvBalance.setText("0.00");
                tvIntegral.setText("0.00");
            }
        }
        //获取提成员工
        if (resultCode == 1002) {
            mStaffInfo = (List<ReportMessageBean.DataBean.EmplistBean>) data.getSerializableExtra("staff");
            if (mStaffInfo != null) {
                mStaffListGid.clear();
                for (int i = 0; i < mStaffInfo.size(); i++) {
                    mStaffListGid.add(mStaffInfo.get(i).getGID());
                    if (i == mStaffInfo.size() - 1) {
                        mStaffName.append(mStaffInfo.get(i).getEM_Name());
                    } else {
                        mStaffName.append(mStaffInfo.get(i).getEM_Name() + "、");
                    }
                    Log.i(TAG, "onActivityResult: " + mStaffInfo.get(i).getEM_Name());
                }
                tvPayConfirmEmployee.setText(mStaffName);
                mStaffName.delete(0, mStaffName.length());//清空数据
            }
        }
        //添加快捷充值后，刷新界面
        if (requestCode == 0510) {
            getDiscountActivity();
            adapter.notifyDataSetChanged();
        }

    }

    private void judgeactivity(String birthday) {
        mRechargeTypeList.clear();
        for (int i = 0; i < mDiscountTypeBean.getData().size(); i++) {
            if (mDiscountTypeBean.getData().get(i).getRP_Type() == 1) {
                mRechargeTypeList.add(mDiscountTypeBean.getData().get(i));
            }
        }
        mRechargeTypeList.add(new DiscountTypeBean.DataBean());
        if (mRechargeTypeList.size() > 0) {
            adapter.notifyDataSetChanged();
            mEditContentBuilder = new StringBuilder("");
            if (mAdapter == null) {
                mAdapter = new RechargeTypeAdapter(mRechargeTypeList, MemberRechargeActivity.this);
                gvRechargeType.setAdapter(mAdapter);
            } else {

            }
        }

        for (int i = 0; i < mRechargeTypeList.size(); i++) {
            if (mRechargeTypeList.get(i).getRP_ValidType() == 4) {
                if (birthday == null) {
                    mRechargeTypeList.remove(i);
                } else if (!birthday.contains(DateTimeUtil.getNowDate())) {
                    mRechargeTypeList.remove(i);
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private void initData() {
        tvTitle.setText("会员充值");
        Intent intent = getIntent();
        MDZZ = intent.getStringExtra("MDZZ");
        if (MDZZ != null) {
            tvMemberName.setText(intent.getStringExtra("name"));
            tvMemberCard.setText(intent.getStringExtra("card"));
            mCard = intent.getStringExtra("card");
        }
        vipCardNo = intent.getStringExtra("card");
        tvRechargenum.setText(orderNumber);
        Message message = new Message();
        message.what = 1;
        handler.sendMessageDelayed(message, 1000);
        getDiscountActivity();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                etSearch.setText(vipCardNo);
            }
        }
    };

    /**
     * 提示对话框
     *
     * @param msg
     */
    private void hintDialog(String msg) {
        new SweetAlertDialog(MemberRechargeActivity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("提示")
                .setContentText(msg + "!")
                .setConfirmText("了解")
                .show();
    }

    private void barCode() {
        RequestParams params = new RequestParams();
        params.put("Code", mSmPayCode);
        params.put("Money", mRechargeMoney);
        params.put("OrderGID", mVipInflateMoneyGID);
        params.put("OrderType", 40);//10商品消费 20充次 30计时消费 40 充值 50 套餐消费 60快速消费
        params.put("OrderNo", orderNumber);
        params.put("OrderPayInfo[PayPoint]", 0);
//        params.put("OrderPayInfo[PayTotalMoney]", mRechargeMoney);
        params.put("OrderPayInfo[GiveChange]", 0);
//        params.put("OrderPayInfo[DisMoney]", mRechargeMoney);
        params.put("OrderPayInfo[IsEraseZero]", false);
        params.put("OrderPayInfo[PayTypeList][0][PayName]", mPayName);
        params.put("OrderPayInfo[PayTypeList][0][PayCode]", mPayCode);
//        params.put("OrderPayInfo[PayTypeList][0][PayMoney]", mRechargeMoney);

        //应收金额
        params.put("OrderPayInfo[DisMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mRechargeMoney)));
        //实收金额
        params.put("OrderPayInfo[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mRechargeMoney)));
        //支付金额
        params.put("OrderPayInfo[PayTypeList][0][PayMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mRechargeMoney)));

        //抹零金额
        params.put("OrderPayInfo[EraseOdd]", 0);

        presenter.barCode(params);
    }

    private void querPay() {
        RequestParams params = new RequestParams();
        params.put("ResultGID", barcodeResultGID);
        presenter.querPay(params);
    }

    /**
     * 获取充值优惠活动
     */
    private void getDiscountActivity() {
        mRechargeTypeList.clear();
        HttpHelper.post(this, HttpAPI.API().DISSCOUNT_ACTIVITY, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mDiscountTypeBean = CommonFun.JsonToObj(responseString, DiscountTypeBean.class);
                for (int i = 0; i < mDiscountTypeBean.getData().size(); i++) {
                    if (mDiscountTypeBean.getData().get(i).getRP_Type() == 1) {
                        mRechargeTypeList.add(mDiscountTypeBean.getData().get(i));
                    }
                }
                mRechargeTypeList.add(new DiscountTypeBean.DataBean());
                if (mRechargeTypeList.size() > 0) {
                    adapter.notifyDataSetChanged();
                    if (mAdapter == null) {
                        mAdapter = new RechargeTypeAdapter(mRechargeTypeList, MemberRechargeActivity.this);
                        gvRechargeType.setAdapter(mAdapter);
                    } else {
                        mAdapter.notifyDataSetChanged();
                    }
                }
                if (mCard != null) {
                    vippresenter.postVip(mCard, isSearch);
//                    postVip(mCard);
                }

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    public void update(double d) {
        mRechargeMoney = d;
        mRechargeTypeList.get(mRechargeTypeList.size() - 1).setRP_RechargeMoney(mRechargeMoney);
        mGiveMoney = 0;
        mTotal = mRechargeMoney;
        mGiveIntegral = DoubleMathUtil.mul(mScale, mRechargeMoney);
        tvYSMoney.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mRechargeMoney)));
        tvGiveIntegral.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mGiveIntegral)));
        tvRechargeTotal.setText(Decima2KeeplUtil.stringToDecimal(String.valueOf(mTotal)));
    }


    @Override
    public void onKeyClick(View view, RecyclerView.ViewHolder holder, int position) {
        if (vipdatalist == null) {
            CustomToast.makeText(MemberRechargeActivity.this, "请先选择会员", Toast.LENGTH_SHORT).show();
            for (int i = 0; i < mRechargeTypeList.size(); i++) {
                mRechargeTypeList.get(i).setChecked(false);
            }
            adapter.notifyDataSetChanged();
            return;
        }
        switch (position) {
            case 9: // 按下小数点
                String num = mInput;
                if (!num.contains(datas.get(position))) {
                    num += datas.get(position);
                    mInput = num;
                    mHandler.sendEmptyMessage(2);
                }
                break;
            default: // 按下数字键
                if ("0".equals(mInput)) { // 第一个数字按下0的话，第二个数字只能按小数点
                    break;
                }
                mInput = mInput + datas.get(position);
                String temp = mInput;
                int posDot = temp.indexOf(".");
                if (posDot > 0) {
                    if (temp.length() - posDot - 1 > 2) {
                        mInput = mInput.substring(0, posDot + 3);
                    }
                }
                mHandler.sendEmptyMessage(2);
                break;
        }
    }

    @Override
    public void onDeleteClick(View view, RecyclerView.ViewHolder holder, int position) {
        // 点击删除按钮
        String num = mInput;
        if (num.length() > 0) {
            mInput = num.substring(0, num.length() - 1);
            mHandler.sendEmptyMessage(2);
        }
    }

    /**
     * @return , 返回整数积分，服务器接收的是整型的
     */
    private int getPoints() {
        if (!TextUtils.isEmpty(tvGiveIntegral.getText())) {
            double pointDouble = Math.floor(Double.parseDouble(tvGiveIntegral.getText().toString()));
            String pointString = pointDouble + "";
            String point = pointString.substring(0, pointString.length() - 2);
            int ponitInt = Integer.parseInt(point);
            return ponitInt;
        } else {
            return 0;
        }
    }

    /**
     * 会员充值提交订单
     */
    private void vipInflateMoneyStepOne() {
        Le("vipInflateMoneyStepOne");
        RequestParams params = new RequestParams();
        //订单号
        params.put("MR_Order", orderNumber);
        //订单时间
        params.put("OrderTime", tvOrderTime.getText().toString());
        //会员卡号
        params.put("VIP_Card", mVipCard);
        //快捷充值GID
        params.put("CC_GID", mCC_GID);
        //充值金额
        params.put("MR_Amount", Decima2KeeplUtil.stringToDecimal(String.valueOf(mRechargeMoney)));
        //赠送金额
        params.put("MR_GivenAmount", Decima2KeeplUtil.stringToDecimal(String.valueOf(mGiveMoney)));
        //赠送积分
//        params.put("MR_Integral", getPoints());
        params.put("MR_Integral", Decima2KeeplUtil.stringToDecimal(tvGiveIntegral.getText().toString()));
        //提成员工
        if (mStaffInfo != null) {
            for (int j = 0; j < mStaffInfo.size(); j++) {
                params.put("EM_GIDList[" + j + "]", mStaffInfo.get(j).getGID());
            }
        }
        //备注
        params.put("MR_Remark", edRemakes.getText().toString());

//        params.put("MR_Total", Decima2KeeplUtil.stringToDecimal(String.valueOf(shoudongInput == true ? mRechargeMoney : mTotal)));//充值合计

        Li(" 会员充值提交订单---------------------random:" + params.toString());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Le(responseString);
                VipInflateMoneyBean moneyBean = CommonFun.JsonToObj(responseString, VipInflateMoneyBean.class);
                mVipInflateMoneyGID = moneyBean.getData().getGID();
                if (mPayCode.equals("SMZF")) {
                    //打开扫描界面扫描条形码或二维码
                    Intent openCameraIntent = new Intent(MemberRechargeActivity.this, CaptureActivity.class);
                    startActivityForResult(openCameraIntent, 1);
                } else {
                    vipInflateMoneyStepTwo();
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MemberRechargeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, HttpAPI.API().MEM_RECHARGE_SUB, params, callBack);
    }

    /**
     * 会员充值确认支付
     */
    private void vipInflateMoneyStepTwo() {
        Le("vipInflateMoneyStepTwo");
        RequestParams params = new RequestParams();
        //订单GID
        params.put("OrderGID", mVipInflateMoneyGID);
        //是否发送短信
        params.put("Smsg", cbMessage.isChecked() ? 1 : 0);

        //应收金额
        params.put("PayResult[DisMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mRechargeMoney)));
        //实收金额
        params.put("PayResult[PayTotalMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mRechargeMoney)));
        //支付金额
        params.put("PayResult[PayTypeList][0][PayMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mRechargeMoney)));

        //抹零金额
        params.put("PayResult[EraseOdd]", 0);
        //找零金额
        params.put("PayResult[GiveChange]", 0);
        //支付方式编码
        params.put("PayResult[PayTypeList][0][PayCode]", mPayCode);
        //支付方式名称
        params.put("PayResult[PayTypeList][0][PayName]", mPayName);
        //支付金额
        params.put("PayResult[PayTypeList][0][PayMoney]", Decima2KeeplUtil.stringToDecimal(String.valueOf(mRechargeMoney)));

        Li(" 会员充值支付---------------------random:" + params.toString());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final HYCZ_Success_Bean hycz_success_bean = CommonFun.JsonToObj(responseString, HYCZ_Success_Bean.class);
                Li(" 会员充值支付-------------111--------random:" + new Gson().toJson(hycz_success_bean));

                payComplete(hycz_success_bean.getData().getGID());
            }

            @Override
            public void onFailure(String msg) {
                Li(" 会员充值支付-------------2222--------random:" + msg);
                if (msg.contains("BuySms")) {
                    mTvChooseMember.setText("选择会员");
                    mSweetAlertDialog = new SweetAlertDialog(MemberRechargeActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("充值成功");
                    mSweetAlertDialog.setContentText("短信未发送,短信库存不足!");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
//                            if (cbPrint.isChecked()) {
//                                //打印小票
//                                new HttpGetPrintContents(MemberRechargeActivity.this, MyApplication.HYCZ_PRINT_TIMES, hycz_success_bean.getData().getGID()).HYCZ();
//                            }
                            initUI();
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.contains("SmsSign")) {
                    mTvChooseMember.setText("选择会员");
                    mSweetAlertDialog = new SweetAlertDialog(MemberRechargeActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("充值成功");
                    mSweetAlertDialog.setContentText("短信未发送,未设置默认签名!");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
//                            if (cbPrint.isChecked()) {
//                                //打印小票
//                                new HttpGetPrintContents(MemberRechargeActivity.this, MyApplication.HYCZ_PRINT_TIMES, hycz_success_bean.getData().getGID()).HYCZ();
//                            }
                            initUI();
                        }
                    });
                    mSweetAlertDialog.show();
                }
            }
        };
        callBack.setLoadingAnimation(this, "正在支付...", false);
        HttpHelper.post(this, HttpAPI.API().MEM_RECHARGE_PAY, params, callBack);
    }

    private void payComplete(final String GID){
        mVipCard = null;
        shoudongInput = false;
        Message message = new Message();
        message.what = 1;
        message.obj = "0";
        adapter.vHandler.sendMessage(message);
        mTvChooseMember.setText("选择会员");
        try {
            mSweetAlertDialog = new SweetAlertDialog(MemberRechargeActivity.this, SweetAlertDialog.SUCCESS_TYPE);
            mSweetAlertDialog.setTitleText("充值成功");
            mSweetAlertDialog.setConfirmText("确定");
            mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialogInterface) {
                    if (cbPrint.isChecked()) {
                        if (MyApplication.mRechargeMap.isEmpty()) {
                            GetPrintSet.getPrintParamSet();
                        }
                        //打印小票
                        new HttpGetPrintContents(MemberRechargeActivity.this, MyApplication.HYCZ_PRINT_TIMES, GID).HYCZ();
                    }
                    initUI();
                }
            });
            mSweetAlertDialog.show();
        } catch (JsonSyntaxException e) {
            Li(" 会员充值支付-------------3333--------random:");
            CustomToast.makeText(MemberRechargeActivity.this, "打印失败！", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    //选择快捷充值，
    @Override
    public void oneItemClick(View view, int position) {
        if (vipdatalist == null) {
            CustomToast.makeText(MemberRechargeActivity.this, "请选择会员", Toast.LENGTH_SHORT).show();
            return;
        }
        keybord.setVisibility(View.GONE);
        buttonok.setVisibility(View.GONE);
        shoudongInput = false;
        for (int i = 0; i < mRechargeTypeList.size(); i++) {
            mRechargeTypeList.get(i).setChecked(false);
        }
        mRechargeTypeList.get(position).setChecked(true);
        Message message = new Message();
        message.arg1 = position;
        message.what = 2;
        mEditContentBuilder = new StringBuilder("");
        mInputHandler.sendMessage(message);
        adapter.notifyDataSetChanged();
    }

    //添加快捷充值
    @Override
    public void twoItemClick(View view) {
        for (int i = 0; i < mRechargeTypeList.size(); i++) {
            mRechargeTypeList.get(i).setChecked(false);
        }
        mRechargeMoney = 0.0;
        adapter.notifyDataSetChanged();
        Intent intent = new Intent(this, AddRechargeActivity.class);
        startActivityForResult(intent, 0510);
    }

    //手动输入充值金额
    @SuppressLint("HandlerLeak")
    @Override
    public void threeItemClick(TextView view, int position) {
        Li("收到手动输入click");
        if (vipdatalist == null) {

            CustomToast.makeText(MemberRechargeActivity.this, "请选择会员", Toast.LENGTH_SHORT).show();
            return;
        }
        shoudongInput = true;
        for (int i = 0; i < mRechargeTypeList.size(); i++) {
            if (mRechargeTypeList.get(i).isChecked()) {
                mRechargeTypeList.get(i).setChecked(false);
            }
        }

        mRechargeTypeList.get(position).setChecked(true);
//        message.arg1 = position;

        if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE || (recyclerView.isComputingLayout() == false)) {
            adapter.notifyDataSetChanged();
        }

        KeyboardUtils.hideKeyboard(view);
        keybord.setVisibility(View.VISIBLE);
        buttonok.setVisibility(View.VISIBLE);
        if (myHandler == null) {
            myHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what) {
                        case 1:
                            String s = mEditContentBuilder.toString();
                            if (s == null || s.toString().equals("")) {
                                mRechargeMoney = 0.0;
                            } else {
                                mRechargeMoney = Double.parseDouble(s.toString());
                            }

                            mCC_GID = "1";//手动输入的
                            mGiveMoney = 0.0;
                            mGiveIntegral = 0.0;
                            mInputHandler.sendEmptyMessage(9);
                            Message message = Message.obtain();
                            message.what = 1;
                            message.obj = mEditContentBuilder;
                            adapter.vHandler.sendMessage(message);
                            break;
                        case 2:

                            break;
                    }
                }
            };
        }
     /*   view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                orderNumber = CreateOrder.createOrder("CZ");//获取订单号
                if (s == null || s.toString().equals("")) {
                    mRechargeMoney = 0.0;
                } else {
                    mRechargeMoney = Double.parseDouble(s.toString());
                }

                mCC_GID = "1";//手动输入的
                mGiveMoney = 0.0;
                mGiveIntegral = 0.0;
                mInputHandler.sendEmptyMessage(9);

            }
        });*/

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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

            case R.id.btn_keyboard_confirm:
                keybord.setVisibility(View.GONE);
                buttonok.setVisibility(View.GONE);
                break;
            case R.id.btn_keyboard_delete:
                if (!isNull(mEditContentBuilder)) {
                    mEditContentBuilder.deleteCharAt(mEditContentBuilder.length() - 1);
                }
                myHandler.sendEmptyMessage(1);
                break;
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

    private void showPayPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_kuaijieshoukuan, null);
        mPopWindow = new YSLPayPopWindow(this, mRechargeMoney + "", mSwitchEntity);
        mPopWindow.setOnItemClickListener(this);
        mPopWindow.setOnDismissListener(this);
        YSLUtils.setBackgroundAlpha(0.5f, this);
        mPopWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.l_cash:
                mPayName = "现金支付";
                mPayCode = "XJZF";
                vipInflateMoneyStepOne();
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, this);
                break;
            case R.id.l_yue:
                CustomToast.makeText(this, "会员充值不能使用余额", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, this);
                break;
            case R.id.r_union:
                Intent intent = new Intent(MemberRechargeActivity.this, UnionPayActivity.class);
                intent.putExtra("VIP_INFO_RE", vipdatalist);
                intent.putExtra(MyApplication.ORDER_NO, orderNumber);//订单号
                intent.putExtra(MyApplication.CARD_NO, etSearch.getText().toString());
                intent.putExtra("GIVE", mGiveMoney);
                intent.putExtra("RECHARGE", mRechargeMoney);
                intent.putExtra("TOTAL", mTotal);
                intent.putExtra("ORDER_MONEY", 0);
                intent.putExtra("POINT", mGiveIntegral);
                intent.putExtra("message", cbMessage.isChecked());
                intent.putExtra("print", cbPrint.isChecked());
                intent.putExtra(MyApplication.PAGE_FLAG, mPageFlag);
                intent.putExtra("VIP_INFO", mMemberInfo);
                intent.putExtra("CC_GID", mCC_GID.isEmpty() ? "" : mCC_GID);
                intent.putExtra("remark", edRemakes.getText().toString());
                intent.putExtra("OrderTime", tvOrderTime.getText().toString());
                if (mStaffListGid.size() > 0) {
                    intent.putExtra("EM_GIDList", mStaffListGid);
                }
                startActivity(intent);
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, this);
//                initUI();
                break;
            case R.id.l_card:
                mPayName = "银联支付";
                mPayCode = "YLZF";
                vipInflateMoneyStepOne();
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, this);
                break;
            //扫码支付
            case R.id.l_saoma:
                if (mSmSwitch) {
                    mPayName = "扫码支付";
                    mPayCode = "SMZF";


                    vipInflateMoneyStepOne();
                } else {
                    CustomToast.makeText(this, "扫码支付未开启，请到PC端去开启！", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, this);
                break;
            case R.id.tv_cancel:
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, this);
                break;
            case R.id.l_weixin:
                if (mWXSwitch) {
                    mPayName = "微信记账";
                    mPayCode = "WX_JZ";
                    vipInflateMoneyStepOne();
                } else {
                    CustomToast.makeText(this, "微信记账未开启，请到PC端去开启！", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, this);
                break;
            case R.id.l_zhifubao:
                if (mZfbSwitch) {
                    mPayName = "支付宝记账";
                    mPayCode = "ZFB_JZ";
                    vipInflateMoneyStepOne();
                } else {
                    CustomToast.makeText(this, "支付宝记账未开启，请到PC端去开启！", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, this);
                break;
            case R.id.l_other:
                if (mOtherSwitch) {
                    mPayName = "其他支付";
                    mPayCode = "QTZF";
                    vipInflateMoneyStepOne();
                } else {
                    CustomToast.makeText(this, "其他支付未开启，请到PC端去开启！", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, this);
                break;


        }
    }

    @Override
    public void onDismiss() {
        YSLUtils.setBackgroundAlpha(1.0f, MemberRechargeActivity.this);
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
                case 2://充值的相关数据赋值

                    mRechargeMoney = mRechargeTypeList.get(msg.arg1).getRP_RechargeMoney();//充值的钱
                    mGiveMoney = mRechargeTypeList.get(msg.arg1).getRP_GiveMoney();//优惠的钱
                    mGiveIntegral = mRechargeTypeList.get(msg.arg1).getRP_GivePoint();//获得的积分
                    mCC_GID = mRechargeTypeList.get(msg.arg1).getGID();//充值的GID
                    mTotal = mRechargeMoney + mGiveMoney;
                    mInputHandler.sendEmptyMessage(9);
                    break;
                case 9://所有的对充值金额的修改后都来刷新界面展示的数据
                    tvRechargeTotal.setText(Decima2KeeplUtil.stringToDecimal(mGiveMoney + ""));
//                    tvGiveIntegral.setText(Decima2KeeplUtil.stringToDecimal(mGiveIntegral + ""));
                    tvPayConfirmReceivable.setText(Decima2KeeplUtil.stringToDecimal((shoudongInput == true ? mRechargeMoney : mTotal) + ""));
                    tvConstMoney.setText(Decima2KeeplUtil.stringToDecimal(mRechargeMoney + ""));

                    tvGiveIntegral.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist == null ? "0.00" : DoubleMathUtil.add(mRechargeMoney * vipdatalist.getRS_Value(), mGiveIntegral) + ""));
//                    tvGiveIntegral.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist == null ? "0.00" :
//                            DoubleMathUtil.add(Double.parseDouble(tvConstMoney.getText().toString()) * vipdatalist.getRS_Value(),mGiveIntegral)+ ""));
                    break;
            }
        }
    }

    private void initUI() {//支付完成后清空数据
        for (int i = 0; i < mRechargeTypeList.size(); i++) {
            mRechargeTypeList.get(i).setChecked(false);
        }
        mEditContentBuilder = new StringBuilder("");
        mRechargeMoney = 0.0;
        mTotal = 0.0;
        mCC_GID = "";
        mGiveIntegral = 0;
        mGiveMoney = 0;
        orderNumber = CreateOrder.createOrder("CZ");//获取订单号
        tvRechargenum.setText(orderNumber);
        adapter.notifyDataSetChanged();
        mInputHandler.sendEmptyMessage(9);
        etSearch.setText("");
        vipdatalist = null;
        mTvChooseMember.setText("选择会员");
        tvPayConfirmEmployee.setText("");


    }

    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {
            if (!TextUtils.isEmpty(etSearch.getText())) {
                vippresenter.postVip(content, isSearch);
//                postVip(content);
            }
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }

    private boolean isOverTime(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long overtime = sdf.parse(date + "").getTime();
            long currentTime = sdf.parse(sdf.format(System.currentTimeMillis())).getTime();
            long time = overtime - currentTime;
            if (time < 0) {
                new SweetAlertDialog(MemberRechargeActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        .setContentText("该会员已过期！")
                        .setConfirmText("了解")
                        .show();
                return false;
            } else {
//                postVip(mMemberInfo.getVCH_Card());

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;

    }
}
