package com.zhiluo.android.yunpu.member.consume.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.activity.UnionPayActivity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.mvp.model.PasswordVerifyBean;
import com.zhiluo.android.yunpu.mvp.presenter.PostVipPresenter;
import com.zhiluo.android.yunpu.mvp.view.IPostVipView;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.payment.lkl.PayLKL;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.yslutils.YSLPayPopWindow;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.config.MyApplication.IS_LAKALA_POS_DEVICE;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;


/**
 * Created by ${YSL} on 2018-04-04.
 * 会员延期
 */

public class VipDelayActivity extends AppCompatActivity implements View.OnClickListener, YSLPayPopWindow.OnItemClickListener {
    @Bind(R.id.ed_extend_times)
    EditText edExtendTimes;
    @Bind(R.id.rl_extend_times)
    RelativeLayout rlExtendTimes;
    private EditText mExtendTime, mExtendCost, mNotice;
    private Spinner mVipDelayType;
    private TextView mOk, mLimitTime, mtvback;
    private Bundle mBundle;
    private AllMemberListBean.DataBean.DataListBean vipdetail;
    private double times;
    private String timesUnit;

    private MemberInfoBean mVipInfo;
    private String TypeString;
    private double mSSMoney, mZLMoney, mYSMoney;
    private YSLPayPopWindow mPopWindow;
    private SweetAlertDialog mSweetAlertDialog;
    private String mPayName, mPayCode, mPayMoney;
    private int mPayPoint;
    private String mOrderNo;
    private List<ReportMessageBean.DataBean.VIPGradeListBean> mMemberGrade;//会员等级实体
    private PostVipPresenter vippresenter;
    private IPostVipView vipView;
    private boolean isSearch = true;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean mPasswordSwitch, mCashSwitch, mCardSwitch, mIntegralSwitch, mBalanceSwitch, mWXSwitch, mZfbSwitch, mSmSwitch, mOtherSwitch;

    //0：刷卡   1：扫码  默认为0
    private String mLklPayType = "0";
    //交易处理码  000000：刷卡  660000：扫码
    private String mLklDisPosCode = "000000";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_vip_delay);
        ButterKnife.bind(this);
        initViews();
        loadSwitch();
        initDatas();
        lisenter();
    }

    private void lisenter() {

        mVipDelayType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    TypeString = "天";
                    if (!mExtendTime.getText().toString().isEmpty() && !mExtendTime.getText().toString().equals("")) {
                        edExtendTimes.setText((int) (Integer.valueOf(mExtendTime.getText().toString()) * times) + "");
                    }
                }
                if (i == 1) {
                    TypeString = "月";
                    if (!mExtendTime.getText().toString().isEmpty() && !mExtendTime.getText().toString().equals("")) {
                        edExtendTimes.setText((int) (addMonth(Integer.valueOf(mExtendTime.getText().toString())) * times) + "");
                    }
                }
                if (i == 2) {
                    TypeString = "年";
                    if (!mExtendTime.getText().toString().isEmpty() && !mExtendTime.getText().toString().equals("")) {
                        edExtendTimes.setText((int) (addYear(Integer.valueOf(mExtendTime.getText().toString())) * times) + "");
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        mExtendTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().isEmpty() && !s.toString().equals("")) {
                    if (timesUnit != null) {
                        int day;
                        if (TypeString.equals("天")) {
                            day = (int) (Integer.valueOf(s.toString()) * times);
                            edExtendTimes.setText(day + "");
                        } else if (TypeString.equals("月")) {
                            day = (int) (addMonth(Integer.valueOf(s.toString())) * times);
                            edExtendTimes.setText(day + "");
                        } else if (TypeString.equals("年")) {
                            day = (int) (addYear(Integer.valueOf(s.toString())) * times);
                            edExtendTimes.setText(day + "");
                        }
                    }
                } else {
                    edExtendTimes.setText("0");
                }

            }
        });

    }

    private void loadSwitch() {
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
                }
            }
        }

    }

    private int addMonth(int addmonth) {
        int xiangcha = 0;
        Calendar calendar = Calendar.getInstance();
        Date curDate = new Date(System.currentTimeMillis());
        calendar.setTime(curDate);
        calendar.add(Calendar.MONTH, addmonth);//增加月
        //计算两个日期相差的天数
        long intervalMilli = calendar.getTime().getTime() - System.currentTimeMillis();
        xiangcha = (int) (Math.round(intervalMilli / (24 * 60 * 60 * 1000)));
        return xiangcha;
    }

    private int addYear(int addtear) {
        int xiangcha = 0;
        Calendar calendar = Calendar.getInstance();
        Date curDate = new Date(System.currentTimeMillis());
        calendar.setTime(curDate);
        calendar.add(Calendar.YEAR, addtear);//增加年
        //计算两个日期相差的天数
        long intervalMilli = calendar.getTime().getTime() - System.currentTimeMillis();
        xiangcha = (int) (Math.floor(intervalMilli / (24 * 60 * 60 * 1000)));
        return xiangcha;
    }

    private void initDatas() {
        mBundle = getIntent().getBundleExtra("bundle");
        vipdetail = (AllMemberListBean.DataBean.DataListBean) mBundle.getSerializable("vipinfo");//不完整的会员信息

        getOrder();

        vippresenter = new PostVipPresenter(this);
        vipView = new IPostVipView() {
            @Override
            public void getvipsuccesss(MemberInfoBean bean) {
                try {
                    isSearch = true;
                    mVipInfo = bean;
                    if (mVipInfo.getData().getVIP_Overdue() != null) {
                        mLimitTime.setText(mVipInfo.getData().getVIP_Overdue().substring(0, 10));//会员过期时间
                    }
                    if (mVipInfo.getData().getVG_IsTime() == 1) {
                        mMemberGrade = (List<ReportMessageBean.DataBean.VIPGradeListBean>) CacheData.restoreObject("grade");
                        for (int i = 0; i < mMemberGrade.size(); i++) {
                            if (mMemberGrade.get(i).getGID().equals(mVipInfo.getData().getVG_GID())) {
                                if (mMemberGrade.get(i).getVG_IsTimeTimes() == null) {
                                    edExtendTimes.setText("不限次数");
                                } else if (Integer.parseInt(mMemberGrade.get(i).getVG_IsTimeTimes() + "") > 0) {
                                    timesUnit = mMemberGrade.get(i).getVG_IsTimeUnit();
                                    countTimes(mMemberGrade.get(i).getVG_IsTimeTimes(), Integer.valueOf(mMemberGrade.get(i).getVG_IsTimeNum()));
                                    edExtendTimes.setText(mVipInfo.getData().getMIA_SurplusTimes() + "");
                                    edExtendTimes.setEnabled(true);
                                    rlExtendTimes.setBackground(getResources().getDrawable(R.drawable.ysl_f));
                                }
                            }
                        }
                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void getvipfail(String result) {

            }
        };
        vippresenter.attachView(vipView);

        vippresenter.postVip(vipdetail.getVCH_Card(), isSearch);

    }

    private void countTimes(Integer time, Integer timenum) {
        double dtime = Double.valueOf(time);
        if (timesUnit != null) {
            if (timesUnit.equals("天")) {
                times = dtime / timenum;
            } else if (timesUnit.equals("月")) {
                times = dtime / timenum / 30;
            } else if (timesUnit.equals("年")) {
                times = dtime / timenum / 365;
            }
        }
    }

    private void initViews() {
        mLimitTime = findViewById(R.id.tv_limit_time);
        mExtendCost = findViewById(R.id.ed_extend_cost);
        mNotice = findViewById(R.id.ed_notice);
        mExtendTime = findViewById(R.id.ed_extend_time);
        mVipDelayType = findViewById(R.id.sp_vip_delay_type);
        mOk = findViewById(R.id.tv_ok);
        mtvback = findViewById(R.id.tv_back);

        mOk.setOnClickListener(this);
        mNotice.setOnClickListener(this);
        mExtendCost.setOnClickListener(this);
        mLimitTime.setOnClickListener(this);
        mtvback.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                VipDelayActivity.this.finish();
                break;
            case R.id.tv_limit_time:
                break;
            case R.id.ed_extend_cost:
                break;
            case R.id.ed_notice:
                break;
            case R.id.sp_vip_delay_type:
                break;
            case R.id.tv_ok:
                //asetAlpha(getActivity(), VipDelayActivity.this, 1 + "", 0.5f);
                judgeDataLegal();
                break;
        }
    }

    /**
     * 判断数据合法并提交
     */
    private void judgeDataLegal() {
        if (!"永久会员".equals(mLimitTime.getText().toString())) {
            if (mExtendTime.getText() != null && !mExtendTime.getText().toString().isEmpty()) {
                if (mExtendCost.getText() != null && !mExtendCost.getText().toString().isEmpty()
                        && !"0".equals(mExtendCost.getText().toString())) {
                    mYSMoney = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(mExtendCost.getText().toString()));
                    mPopWindow = new YSLPayPopWindow(this, Decima2KeeplUtil.stringToDecimal(mYSMoney + ""), mSwitchEntity);
                    mPopWindow.setOnItemClickListener(this);
                    mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            setAlpha(1.0f);
                        }
                    });
                    setAlpha(0.5f);
                    mPopWindow.showAtLocation(VipDelayActivity.this.findViewById(R.id.vip_delays),
                            Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                } else {
                    mYSMoney = 0;
                    mSSMoney = 0;
                    mZLMoney = 0;
                    mPayPoint = 0;
                    postDelay();
                }
            } else {
                CustomToast.makeText(VipDelayActivity.this, "请输入延期时长！", Toast.LENGTH_SHORT).show();
            }
        } else {
            mSweetAlertDialog = new SweetAlertDialog(VipDelayActivity.this, SweetAlertDialog.WARNING_TYPE);
            mSweetAlertDialog.setTitleText("提示");
            mSweetAlertDialog.setContentText("该会员永久有效无需延期");
            mSweetAlertDialog.setConfirmText("了解");
            mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    mSweetAlertDialog.dismiss();
                }
            });
            mSweetAlertDialog.show();
//            CustomToast.makeText(MemberDelayActivity.this, "该会员为永久会员，无需延期！", Toast.LENGTH_LONG).show();
        }
    }

    private void getOrder() {
        mOrderNo = CreateOrder.createOrder("YQ");
    }

    private void postDelay() {

        DecimalFormat df = new DecimalFormat("#.00");

        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        //订单编号
        params.put("CO_OrderCode", mOrderNo);
        //会员卡号
        params.put("VCH_Card", vipdetail.getVCH_Card());
        //延期时间
        params.put("Months", mExtendTime.getText());
        //延期时间单位:天、月、年
        params.put("TimesTyepe", TypeString);
        //延期次数
        params.put("MIA_SurplusTimes", edExtendTimes.getText().toString());
        //延期费用
        params.put("CO_Monetary", df.format(mYSMoney));
        //备注
        params.put("DelayRemark", mNotice.getText());

        //收银台信息
        if (mYSMoney > 0) {
            //应收金额
            params.put("PayResult[DisMoney]", df.format(mSSMoney));
            //实收金额
            params.put("PayResult[PayTotalMoney]", df.format(mSSMoney));
            //找零金额
            params.put("PayResult[GiveChange]", 0);
            //支付方式编码
            params.put("PayResult[PayTypeList][0][PayCode]", mPayCode);
            //支付方式名称
            params.put("PayResult[PayTypeList][0][PayName]", mPayName);
            //支付金额
            params.put("PayResult[PayTypeList][0][PayMoney]", mPayMoney);
        } else {
            //应收金额
            params.put("PayResult[DisMoney]", 0);
            //实收金额
            params.put("PayResult[PayTotalMoney]", 0);
            //找零金额
            params.put("PayResult[GiveChange]", 0);
        }

        Li("=============params========== random:" + params);

//        params.put("PayPoint", mPayPoint);
//        params.put("DisMoney", df.format(mSSMoney));
//        params.put("SSMoney", df.format(mSSMoney));
//        params.put("ZLMoney", mZLMoney);
        client.post(HttpAPI.API().VIPDELAY, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(VipDelayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("延期成功!");
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        VipDelayActivity.this.finish();
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                mSweetAlertDialog = new SweetAlertDialog(VipDelayActivity.this, SweetAlertDialog.WARNING_TYPE);
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


    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.l_cash:
                if (mCashSwitch) {
                    mSSMoney = mYSMoney;
                    mZLMoney = 0;
                    mPayPoint = 0;
                    mPayName = "现金支付";
                    mPayCode = "XJZF";
                    mPayMoney = String.valueOf(mYSMoney);
                    postDelay();

                } else {
                    CustomToast.makeText(VipDelayActivity.this, "未开启现金支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                setAlpha(1.0f);
                break;
            case R.id.l_yue:
                if (mBalanceSwitch) {
                    if (mPasswordSwitch) {
                        showPasswordDialog();
                    } else {
                        mSSMoney = mYSMoney;
                        mZLMoney = 0;
                        mPayPoint = 0;
                        mPayName = "余额支付";
                        mPayCode = "YEZF";
                        mPayMoney = String.valueOf(mYSMoney);
                        postDelay();
                    }
                } else {
                    CustomToast.makeText(VipDelayActivity.this, "未开启余额支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                setAlpha(1.0f);
                break;
            case R.id.r_union:

                Intent intent = new Intent(VipDelayActivity.this, UnionPayActivity.class);
                intent.putExtra("ORDER_MONEY", mYSMoney);
                intent.putExtra(MyApplication.ORDER_NO, mOrderNo);
                intent.putExtra(MyApplication.CARD_NO, vipdetail.getVCH_Card());
                intent.putExtra("balance", Double.parseDouble(vipdetail.getMA_AccountBalance() + ""));
                intent.putExtra("point", Double.parseDouble(vipdetail.getMA_AvailableIntegral() + ""));
                intent.putExtra("MONEY", Double.parseDouble(vipdetail.getMA_AvailableBalance() + ""));
                intent.putExtra(MyApplication.PAGE_FLAG, "HYYQ");
                intent.putExtra("Months", mExtendTime.getText().toString());
                intent.putExtra("TimesTyepe", TypeString);
                if (mNotice.getText() != null && !mNotice.getText().toString().isEmpty()) {
                    intent.putExtra("remark", mNotice.getText().toString());
                }
                /**9363会员延期，输入费用，联合支付，余额支付提示非会员不能使用余额支付 20191028 cy */
                intent.putExtra("VIP_INFO", vipdetail);
                intent.putExtra("type", "HYYQ");
                intent.putExtra("MIA_SurplusTimes", edExtendTimes.getText().toString());
                /********************************* 20191028 cy */

                startActivity(intent);
                mPopWindow.dismiss();
                setAlpha(1.0f);
                break;
            case R.id.l_card:
                if (mCardSwitch) {
                    //实收金额
                    mSSMoney = mYSMoney;
                    //找零金额
                    mZLMoney = 0;
                    mPayPoint = 0;
                    mPayName = "银联支付";
                    mPayCode = "YLZF";
                    mPayMoney = String.valueOf(mYSMoney);
                    setPayPos("会员延期");
                } else {
                    CustomToast.makeText(VipDelayActivity.this, "未开启银联支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                setAlpha(1.0f);
                break;
            /**9366会员延期，用支付宝支付、银联、微信记账支付不成功 20191028 cy */
            case R.id.l_zhifubao:
                //支付宝
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mZfbSwitch) {
                        //实收金额
                        mSSMoney = mYSMoney;
                        //找零金额
                        mZLMoney = 0;
                        mPayPoint = 0;
                        mPayName = "支付宝记账";
                        mPayCode = "ZFB_JZ";
                        mPayMoney = String.valueOf(mYSMoney);
                        postDelay();
                    } else {
                        CustomToast.makeText(VipDelayActivity.this, "未开启支付宝记账，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.l_weixin:
                //微信
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (mWXSwitch) {
                        //实收金额
                        mSSMoney = mYSMoney;
                        //找零金额
                        mZLMoney = 0;
                        mPayPoint = 0;
                        mPayName = "微信记账";
                        mPayCode = "WX_JZ";
                        mPayMoney = String.valueOf(mYSMoney);
                        postDelay();
                    } else {
                        CustomToast.makeText(VipDelayActivity.this, "未开启微信记账，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            /********************************* 20191028 cy */
            //扫码支付
            case R.id.l_saoma:
//                mSSMoney = mYSMoney;
//                mZLMoney = 0;
//                mPayPoint = 0;
//                mPayName = "扫码支付";
//                mPayCode = "SMZF";
//                mPayMoney = String.valueOf(mYSMoney);
//                postDelay();
                CustomToast.makeText(VipDelayActivity.this, "会员延期扫码支付不可用", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                setAlpha(1.0f);
                break;
            //其他支付
            case R.id.l_other:
                if (mOtherSwitch) {
                    mSSMoney = mYSMoney;
                    mZLMoney = 0;
                    mPayPoint = 0;
                    mPayName = "其他支付";
                    mPayCode = "QTZF";
                    mPayMoney = String.valueOf(mYSMoney);
                    postDelay();

                } else {
                    CustomToast.makeText(VipDelayActivity.this, "未开启现金支付，请到参数设置开启！", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                setAlpha(1.0f);
                break;
            case R.id.tv_cancel:
                mPopWindow.dismiss();
                setAlpha(1.0f);
                break;

        }
    }

    /**
     * alpha在0.0f到1.0f之间。1.0完全不透明，0.0f完全透明
     */
    private void setAlpha(float al) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = al;
        getWindow().setAttributes(lp);

    }

    /**
     * 判断是否是在POS机上进行消费
     * 如果是，则根据POS机型号调用对应的POS机支付接口
     *
     * @param orderInfo 消费界面名称
     */
    public void setPayPos(String orderInfo) {
        //如果是拉卡拉设备则调用拉卡拉支付接口
        if (IS_LAKALA_POS_DEVICE) {
            //调用拉卡拉的刷卡与感应卡支付功能
            PayLKL payLKL = new PayLKL();

            payLKL.pay4Lkl(
                    VipDelayActivity.this,
                    mOrderNo,
                    Decima2KeeplUtil.stringToDecimal(String.valueOf(mPayMoney)),
                    orderInfo,
                    mLklPayType,
                    mLklDisPosCode
            );
        } else {
            postDelay();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(VipDelayActivity.this);
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
                params.put("VCH_Card", vipdetail.getVCH_Card());
                params.put("VCH_Pwd", etPassword.getText().toString());
                HttpHelper.post(VipDelayActivity.this, HttpAPI.API().PASSWORDVERIFY, params, new CallBack() {
                    @Override
                    public void onSuccess(String responseString, Gson gson) {
                        PasswordVerifyBean bean = CommonFun.JsonToObj(responseString, PasswordVerifyBean.class);
                        if (bean.isSuccess()) {
                            mSSMoney = mYSMoney;
                            mZLMoney = 0;
                            mPayPoint = 0;
                            mPayName = "余额支付";
                            mPayCode = "YEZF";
                            mPayMoney = String.valueOf(mYSMoney);
                            postDelay();
                            alertDialog.dismiss();
                        } else {
                            CustomToast.makeText(VipDelayActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        CustomToast.makeText(VipDelayActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
            }
        });
        alertDialog.show();
    }

}
