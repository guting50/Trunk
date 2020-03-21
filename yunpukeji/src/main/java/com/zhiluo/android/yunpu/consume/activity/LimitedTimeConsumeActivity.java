package com.zhiluo.android.yunpu.consume.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.bean.LimitedTimeConsumeBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.consume.activity.MemberRechargeActivity;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity;
import com.zhiluo.android.yunpu.member.manager.activity.MemberSearchProjectActivity;
import com.zhiluo.android.yunpu.member.manager.activity.MemberSignActivity;
import com.zhiluo.android.yunpu.member.manager.activity.MemberSignDetailActivity;
import com.zhiluo.android.yunpu.member.manager.adapter.MemberCheckInListAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberFastCheckInBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberProjectListBean;
import com.zhiluo.android.yunpu.mvp.presenter.PostVipPresenter;
import com.zhiluo.android.yunpu.mvp.view.IPostVipView;
import com.zhiluo.android.yunpu.print.bean.PrintSetBean;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.sms.jsonbean.SmsSwitch;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CacheFun;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ZPH on 2019-05-16.
 */

public class LimitedTimeConsumeActivity extends BaseActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_limite_title)
    RelativeLayout rlLimiteTitle;
    @Bind(R.id.et_membercard_limite)
    EditText etSearch;
    @Bind(R.id.iv_scan_limite)
    ImageView ivScan;
    @Bind(R.id.ll_scan_limite)
    LinearLayout llScanLimite;
    @Bind(R.id.tv_limite_member)
    TextView tvLimiteMember;
    @Bind(R.id.et_limite_num)
    EditText etLimiteNum;
    @Bind(R.id.et_limite_remark)
    EditText etLimiteRemark;
    @Bind(R.id.cb_short_message)
    CheckBox cbMessage;
    @Bind(R.id.cb_print)
    CheckBox cbPrint;
    @Bind(R.id.tv_pay_confirm_submit)
    TextView tvPayConfirmSubmit;
    @Bind(R.id.l_recharge)
    LinearLayout lRecharge;

    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;
    private boolean mMustCard;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;//参数开关

    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间
    private PostVipPresenter vippresenter;
    private IPostVipView vipView;
    private MemberInfoBean.DataBean vipdatalist;
    private String mVipCard = null;
    private String content;
    private AllMemberListBean.DataBean.DataListBean mMemberInfo = new AllMemberListBean.DataBean.DataListBean();//会员信息

    private LimitedTimeConsumeBean mLimitedConsumeBean;
    private List<MemberFastCheckInBean.DataBean.DataListBean> mMemberCheckList = new ArrayList<>();
    private MemberCheckInListAdapter mAdapter;
    private int pribean = 0;
    private SweetAlertDialog mSweetAlertDialog;//提示框
    private boolean isSearch = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limited_time_consume);
        ButterKnife.bind(this);

        initData();
        initLisenter();
        setCbPrint();
        setCbShortMessage("017");

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
                Intent openCameraIntent = new Intent(LimitedTimeConsumeActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        //设置选择会员监听
        tvLimiteMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LimitedTimeConsumeActivity.this, CheckMemberInfoActivity.class);
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
                    CustomToast.makeText(LimitedTimeConsumeActivity.this, "打印开关未开启，请设置", Toast.LENGTH_SHORT).show();
                    cbPrint.setChecked(false);
                }

            }
        });

        tvPayConfirmSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                limitedCheckIn();
            }
        });

    }

    private void initData() {

        comfirmParams();

        vippresenter = new PostVipPresenter(this);
        vipView = new IPostVipView() {
            @Override
            public void getvipsuccesss(MemberInfoBean bean) {
                isSearch = true;
                vipdatalist = bean.getData();
                mVipCard = vipdatalist.getVCH_Card();
                String mMemberName = vipdatalist.getVIP_Name();
                    if (mMemberName != null && !"".equals(mMemberName)) {
                    tvLimiteMember.setText(mMemberName);
                } else {
                    tvLimiteMember.setText(mVipCard);
                }
                if (vipdatalist.getVG_IsTime() !=1){
                    CustomToast.makeText(LimitedTimeConsumeActivity.this,"该会员没有开启限时功能",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void getvipfail(String result) {

                if (MyApplication.VIP_CARD != null) {
                    vippresenter.postVip(MyApplication.VIP_CARD,isSearch);
                    MyApplication.VIP_CARD = null;
                } else {
                    tvLimiteMember.setText("");
                }
                if (result.contains("没有任何执行操作")) {
                    Intent intent = new Intent(LimitedTimeConsumeActivity.this, CheckMemberInfoActivity.class);
                    intent.putExtra("number", content);
                    startActivityForResult(intent, 888);
                }
            }
        };
        vippresenter.attachView(vipView);

    }



    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {
            if (!TextUtils.isEmpty(etSearch.getText())) {
                vippresenter.postVip(content,isSearch);
//                postVip(content);
            }
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
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
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {


                    //必须刷卡
                    if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mMustCard = true;
                            tvLimiteMember.setEnabled(false);
                            llScanLimite.setEnabled(false);
                            llScanLimite.setBackgroundColor(getResources().getColor(R.color.lightgray));
                            etSearch.setHint("请使用刷卡机刷卡");
                            etSearch.setFocusable(false);
                            etSearch.setFocusableInTouchMode(false);
                            ivScan.setEnabled(false);


                        } else {
                            mMustCard = false;
                            tvLimiteMember.setEnabled(true);
                            llScanLimite.setEnabled(true);
                            llScanLimite.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
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

                            //必须刷卡
                            if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                                if (mSwitchEntity.get(i).getSS_State() == 1) {
                                    mMustCard = true;
                                    tvLimiteMember.setEnabled(false);
                                    llScanLimite.setEnabled(false);
                                    llScanLimite.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                    etSearch.setHint("请使用刷卡机刷卡");
                                    etSearch.setFocusable(false);
                                    etSearch.setFocusableInTouchMode(false);
                                    ivScan.setEnabled(false);


                                } else {
                                    mMustCard = false;
                                    tvLimiteMember.setEnabled(true);
                                    llScanLimite.setEnabled(true);
                                    llScanLimite.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                                    etSearch.setHint("请输入会员卡号/手机号");
                                    etSearch.setFocusable(true);
                                    etSearch.setFocusableInTouchMode(true);
                                    ivScan.setEnabled(true);
                                }
                            }
                        }
                    } else {
                        mMustCard = false;

                    }
                } else {
                    mMustCard = false;

                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(LimitedTimeConsumeActivity.this, "获取系统开关失败" + msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(LimitedTimeConsumeActivity.this, HttpAPI.API().PRE_LOAD, callBack);
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
                            CustomToast.makeText(LimitedTimeConsumeActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
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
        HttpHelper.post(LimitedTimeConsumeActivity.this, HttpAPI.API().SMS_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SmsSwitch bean = CommonFun.JsonToObj(responseString, SmsSwitch.class);
                for (int i=0;i<bean.getData().size();i++){
                    if (bean.getData().get(i).getST_Code().equals(code)){
                        if (bean.getData().get(i).getST_State() == null || !bean.getData().get(i).getST_State().equals("1")) {
                            cbMessage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CustomToast.makeText(LimitedTimeConsumeActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
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
        HttpHelper.post(LimitedTimeConsumeActivity.this, HttpAPI.API().PRINTSET, new CallBack() {
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

    private void limitedCheckIn(){

        if (mVipCard ==null){
            CustomToast.makeText(LimitedTimeConsumeActivity.this,"请先选择会员",Toast.LENGTH_SHORT).show();
            return;
        }
        if (vipdatalist == null){
            CustomToast.makeText(LimitedTimeConsumeActivity.this,"没有该会员信息",Toast.LENGTH_SHORT).show();
            return;
        }else {
            if (vipdatalist.getVG_IsTime() !=1){
                CustomToast.makeText(LimitedTimeConsumeActivity.this,"该会员没有开启限时功能",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (etLimiteNum.getText().toString().isEmpty()){
            CustomToast.makeText(LimitedTimeConsumeActivity.this,"请输入扣减次数",Toast.LENGTH_SHORT).show();
            return;
        }

        RequestParams params = new RequestParams();
        params.put("Times",etLimiteNum.getText().toString());
        params.put("Remark",etLimiteRemark.getText().toString());
        params.put("Device",2);
        params.put("VIP_Card",mVipCard);
        params.put("Is_Sms",cbMessage.isChecked());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                tvLimiteMember.setText("");
                etSearch.setText("");
                etLimiteNum.setText("1");
                etLimiteRemark.setText("");
                mVipCard = null;
                vipdatalist = null;
                mLimitedConsumeBean = CommonFun.JsonToObj(responseString,LimitedTimeConsumeBean.class);

                mSweetAlertDialog = new SweetAlertDialog(LimitedTimeConsumeActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("提示");
                mSweetAlertDialog.setContentText("限时消费成功");
                mSweetAlertDialog.setCancelable(false);
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        if (cbPrint.isChecked()){
                            //打印
                            new HttpGetPrintContents(LimitedTimeConsumeActivity.this, MyApplication.XSXF_PRINT_TIMES, mLimitedConsumeBean.getData()).XSXF();
                        }
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(LimitedTimeConsumeActivity.this,msg,Toast.LENGTH_SHORT).show();
                tvLimiteMember.setText("");
                etSearch.setText("");
                etLimiteNum.setText("1");
                etLimiteRemark.setText("");
                mVipCard = null;
                vipdatalist = null;
            }
        };
        HttpHelper.post(this,HttpAPI.API().CHECK_IN_ACCOUNT,params,callBack);

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
//                if (mMemberInfo.getVIP_Overdue() == null || mMemberInfo.getVIP_Overdue().equals("")) {
////                    postVip(mMemberInfo.getVCH_Card());
//                } else {
//                    if (!isOverTime(mMemberInfo.getVIP_Overdue())) {
//                        return;
//                    }
//
//                }
                mVipCard = mMemberInfo.getVCH_Card();
                if (mVipCard !=null && !mVipCard.equals("")){
                    isSearch = false;
                    etSearch.setText(mVipCard);
                    etSearch.setSelection(mVipCard.length());
                    if (mMemberInfo.getVIP_Name() != null) {
                        tvLimiteMember.setText(mMemberInfo.getVIP_Name());
                    } else {
                        tvLimiteMember.setText(mMemberInfo.getVCH_Card());
                    }
                }else {
                    CustomToast.makeText(LimitedTimeConsumeActivity.this,"会员卡号不能为空",Toast.LENGTH_SHORT).show();
                }
            } else {
                tvLimiteMember.setText("");
                etSearch.setText("");

            }
        }

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
}
