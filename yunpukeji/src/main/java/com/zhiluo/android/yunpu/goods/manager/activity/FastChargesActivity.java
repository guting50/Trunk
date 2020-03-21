package com.zhiluo.android.yunpu.goods.manager.activity;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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
import com.zhiluo.android.yunpu.consume.activity.StaffCommissionActivity;
import com.zhiluo.android.yunpu.goods.manager.bean.FastChoiceChargeBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.consume.activity.MemberChargePayActivity;
import com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.mvp.presenter.IPrintSetPresenter;
import com.zhiluo.android.yunpu.mvp.presenter.PostVipPresenter;
import com.zhiluo.android.yunpu.mvp.view.IPostVipView;
import com.zhiluo.android.yunpu.mvp.view.IPrintSetView;
import com.zhiluo.android.yunpu.print.bean.KSJC_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.PrintSetBean;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.sms.jsonbean.SmsSwitch;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ZPH on 2018-12-27.
 */

public class FastChargesActivity extends BaseActivity {


    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.iv_delete_vip)
    ImageView ivDeleteVip;
    @Bind(R.id.iv_choise_vip_right)
    ImageView ivChoiseVipRight;
    @Bind(R.id.tv_choose_member)
    TextView tvChooseMember;
    @Bind(R.id.r_choise_vip)
    RelativeLayout rChoiseVip;
    @Bind(R.id.rl_pay_confirm_title)
    RelativeLayout rlPayConfirmTitle;
    @Bind(R.id.et_membercard)
    EditText etSearch;
    @Bind(R.id.iv_scan)
    ImageView ivScan;
    @Bind(R.id.ll_scan)
    LinearLayout llScan;
    @Bind(R.id.l_layouta)
    LinearLayout lLayouta;
    @Bind(R.id.tv_member_info_name)
    TextView tvMemberInfoName;
    @Bind(R.id.tv_member_info_card)
    TextView tvMemberInfoCard;
    @Bind(R.id.tv_member_info_balance)
    TextView tvMemberInfoBalance;
    @Bind(R.id.tv_member_info_integral)
    TextView tvMemberInfoIntegral;
    @Bind(R.id.tv_pay_confirm_project)
    TextView tvPayConfirmProject;
    @Bind(R.id.ll_pay_confirm_project)
    LinearLayout llPayConfirmProject;
    @Bind(R.id.tv_pay_confirm_employee)
    TextView tvPayConfirmEmployee;
    @Bind(R.id.ll_pay_confirm_employee)
    LinearLayout llPayConfirmEmployee;
    @Bind(R.id.tv_constmoney)
    TextView tvConstmoney;
    @Bind(R.id.cb_short_message)
    CheckBox cbMessage;
    @Bind(R.id.cb_print)
    CheckBox cbPrint;
    @Bind(R.id.l_layout)
    LinearLayout lLayout;
    private long pretime;//第一次的时间
    private boolean hasTimerDown = false;
    private String content;
    private InputHandler mInputHandler = new InputHandler();
    private AllMemberListBean.DataBean.DataListBean mMemberInfo = new AllMemberListBean.DataBean.DataListBean();//会员信息
    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;
    private String mVipCard = null;
    private GoodsCheckResponseByType.DataBean.DataListBean mServiceInfo;
    private MemberInfoBean.DataBean vipdatalist;

    private List<ReportMessageBean.DataBean.EmplistBean> mStaffInfo;//提成员工信息
    private String mPageFlag = "KSJC";
    private ArrayList<String> mStaffListGid = new ArrayList<>();//提成员工GID
    private StringBuilder mStaffName = new StringBuilder("");//提成员工姓名
    private SweetAlertDialog mSweetAlertDialog;
    private boolean isStaff;//员工提成
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;//参数开关，这里要用到微信和支付宝记账的开关
    private IPrintSetPresenter printPresent;
    private IPrintSetView printView;
    private int pribean = 0;
    private PostVipPresenter vippresenter;
    private IPostVipView vipView;
    private boolean isSearch = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastcharges);
        ButterKnife.bind(this);
        tvTitle.setText("快速计次");
        setCbPrint();
        setCbShortMessage("012");
        comfirmParams();
        setLisenter();

        //V1S刷卡
        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            Intent nfcIntent = new Intent(this, getClass());
            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mPendingIntent =
                    PendingIntent.getActivity(this, 0, nfcIntent, 0);
        }

    }

    private void comfirmParams() {
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        mServiceInfo = (GoodsCheckResponseByType.DataBean.DataListBean) CacheData.restoreObject("SEVIVCE");
        mStaffInfo = (List<ReportMessageBean.DataBean.EmplistBean>) CacheData.restoreObject("STAFFINFO");

        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {

                    //员工提成
                    if ("301".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            isStaff = true;
                            llPayConfirmEmployee.setVisibility(View.VISIBLE);
                        } else {
                            isStaff = false;
                            llPayConfirmEmployee.setVisibility(View.GONE);
                        }
                    }

                    //必须刷卡
                    if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            rChoiseVip.setEnabled(false);
                            llScan.setEnabled(false);
                            llScan.setBackgroundColor(getResources().getColor(R.color.lightgray));
                            etSearch.setHint("请使用刷卡机刷卡");
                            etSearch.setFocusable(false);
                            etSearch.setFocusableInTouchMode(false);
                            ivScan.setEnabled(false);


                        } else {
                            rChoiseVip.setEnabled(true);
                            llScan.setEnabled(true);
                            llScan.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
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

        if (mServiceInfo != null) {
            tvPayConfirmProject.setText(mServiceInfo.getPM_Name() + "");
        }
        if (mStaffInfo != null) {
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

        vippresenter = new PostVipPresenter(this);
        vipView = new IPostVipView() {
            @Override
            public void getvipsuccesss(MemberInfoBean bean) {
                isSearch = true;
                vipdatalist = bean.getData();
                mVipCard = vipdatalist.getVCH_Card();
                initnew();
                if (mServiceInfo != null) {
                    addOrder();
                } else {
                    mSweetAlertDialog = new SweetAlertDialog(FastChargesActivity.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("快速计次失败");
                    mSweetAlertDialog.setContentText("请先选择服务项目!");
                    mSweetAlertDialog.setConfirmText("了解");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }
                    });
                    mSweetAlertDialog.show();
                }
            }

            @Override
            public void getvipfail(String result) {
                if (MyApplication.VIP_CARD != null) {
                    vippresenter.postVip(MyApplication.VIP_CARD,isSearch);
//                    postVip(MyApplication.VIP_CARD);
                    MyApplication.VIP_CARD = null;
                } else {
                    tvChooseMember.setText("选择会员");
                    tvMemberInfoName.setText("无");
                    tvMemberInfoCard.setText("");
                    tvMemberInfoIntegral.setText("");
                    tvMemberInfoBalance.setText("0.00");
                }
                if (result.contains("没有任何执行操作")) {
                    Intent intent = new Intent(FastChargesActivity.this, CheckMemberInfoActivity.class);
                    intent.putExtra("number", content);
                    startActivityForResult(intent, 888);
                }

            }
        };
        vippresenter.attachView(vipView);
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
                            //员工提成
                            if ("301".equals(mSwitchEntity.get(i).getSS_Code())) {
                                if (mSwitchEntity.get(i).getSS_State() == 1) {
                                    isStaff = true;
                                    llPayConfirmEmployee.setVisibility(View.VISIBLE);
                                } else {
                                    isStaff = false;
                                    llPayConfirmEmployee.setVisibility(View.GONE);
                                }
                            }

                            //必须刷卡
                            if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                                if (mSwitchEntity.get(i).getSS_State() == 1) {
                                    rChoiseVip.setEnabled(false);
                                    llScan.setEnabled(false);
                                    llScan.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                    etSearch.setHint("请使用刷卡机刷卡");
                                    etSearch.setFocusable(false);
                                    etSearch.setFocusableInTouchMode(false);
                                    ivScan.setEnabled(false);


                                } else {
                                    rChoiseVip.setEnabled(true);
                                    llScan.setEnabled(true);
                                    llScan.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                                    etSearch.setHint("请输入会员卡号/手机号");
                                    etSearch.setFocusable(true);
                                    etSearch.setFocusableInTouchMode(true);
                                    ivScan.setEnabled(true);
                                }
                            }
                        }
                    } else {
                        isStaff = false;
                    }
                } else {
                    isStaff = false;

                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(FastChargesActivity.this, "获取系统开关失败" + msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(FastChargesActivity.this, HttpAPI.API().PRE_LOAD, callBack);
    }

    //获取系统隐式启动的
    @Override
    public void onNewIntent(Intent intent) {
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String CardId = ByteArrayToHexString.ByteArrayToHex(tagFromIntent.getId());
        if (null != CardId) {
            MyApplication.VIP_CARD = CardId;
            while (CardId.length() < 10) {
                CardId = "0" + CardId;
            }
            etSearch.setText(CardId);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mNFCAdapter != null) {
            //隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }

        /**
         * lkl、sunmi 、basewin的检卡 读卡操作
         */
        if (!MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            new CardOperationUtils(this, etSearch);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNFCAdapter != null) {
            //隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    @Override
    protected void onStop() {

        //终止检卡
        new CardOperationUtils().close();

        Log.d("TAG", "onStop: ");
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
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
                            CustomToast.makeText(FastChargesActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
                            cbMessage.setChecked(false);
                        }
                    });
                } else {
                    cbMessage.setChecked(true);
                }
            }else {
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
            if (YSLUtils.getPrints() != null) {

                if (YSLUtils.getPrints().getPS_IsEnabled() != 1) {
                    cbPrint.setChecked(false);
                    pribean = 0;
                } else {
                    cbPrint.setChecked(true);
                    pribean = 1;
                }
            } else {
                printPresent.getPrintSet();
            }

        } catch (Exception e) {
            cbPrint.setVisibility(View.INVISIBLE);
        }
    }


    /**
     * 获取短信开关
     */
    private void getSmsSet(final String code) {
        HttpHelper.post(FastChargesActivity.this, HttpAPI.API().SMS_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SmsSwitch bean = CommonFun.JsonToObj(responseString, SmsSwitch.class);
                for (int i=0;i<bean.getData().size();i++){
                    if (bean.getData().get(i).getST_Code().equals(code)){
                        if (bean.getData().get(i).getST_State() == null || !bean.getData().get(i).getST_State().equals("1")) {
                            cbMessage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CustomToast.makeText(FastChargesActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
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


    private void setLisenter() {

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvPayConfirmProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FastChargesActivity.this, ChoiceFastchargesActivity.class);
                startActivityForResult(intent, 9999);
            }
        });

        //设置选择会员监听
        rChoiseVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mServiceInfo != null) {
                    Intent intent = new Intent(FastChargesActivity.this, CheckMemberInfoActivity.class);
                    startActivityForResult(intent, 888);
                } else {
                    CustomToast.makeText(FastChargesActivity.this, "请先选择服务项目", Toast.LENGTH_SHORT).show();
                }

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
                Intent openCameraIntent = new Intent(FastChargesActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        //选择员工
        tvPayConfirmEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStaff) {
                    Intent intent = new Intent(FastChargesActivity.this, StaffCommissionActivity.class);

                    intent.putExtra("PAGE_FLAG", mPageFlag);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mStaffInfo", (Serializable) mStaffInfo);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 1002);
                } else {
                    CustomToast.makeText(FastChargesActivity.this, "员工提成未开启，请确认后再尝试！", Toast.LENGTH_SHORT).show();
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
                    CustomToast.makeText(FastChargesActivity.this, "打印开关未开启，请设置", Toast.LENGTH_SHORT).show();
                    cbPrint.setChecked(false);
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 6666) {
            mServiceInfo = (GoodsCheckResponseByType.DataBean.DataListBean) data.getSerializableExtra("SEVIVCE");
            if (mServiceInfo != null) {
                tvPayConfirmProject.setText(mServiceInfo.getPM_Name() + "");
                CacheData.saveObject("SEVIVCE", mServiceInfo);
            }
        }

        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            etSearch.setText(scanResult);
        }

        if (requestCode == 888 && resultCode == 2222) {
            mMemberInfo = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");
            if (mMemberInfo != null) {

                if (mMemberInfo.getVIP_Name() != null && !"".equals(mMemberInfo.getVIP_Name())) {
                    tvChooseMember.setText(mMemberInfo.getVIP_Name());
                } else {
                    tvChooseMember.setText(mMemberInfo.getVCH_Card());
                }
                mVipCard = mMemberInfo.getVCH_Card();
                isSearch = false;
                etSearch.setText(mVipCard);
                if (mMemberInfo.getVIP_Name() != null) {
                    tvMemberInfoName.setText(mMemberInfo.getVIP_Name());
                } else {
                    tvMemberInfoName.setText("无");
                }
                tvMemberInfoCard.setText(mMemberInfo.getVCH_Card());
                tvMemberInfoBalance.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableBalance() + ""));
                tvMemberInfoIntegral.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableIntegral() + ""));


            } else {
                tvChooseMember.setText("选择会员");
                tvMemberInfoName.setText("无");
                tvMemberInfoCard.setText("无");
                tvMemberInfoBalance.setText("0.00");
                tvMemberInfoIntegral.setText("0.00");
            }
        }

        //获取提成员工
        if (resultCode == 1002) {
            mStaffInfo = (List<ReportMessageBean.DataBean.EmplistBean>) data.getSerializableExtra("staff");
            CacheData.saveObject("STAFFINFO", mStaffInfo);
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


    private void initnew() {


        if (vipdatalist.getVIP_Name() != null && !"".equals(vipdatalist.getVIP_Name())) {
            tvChooseMember.setText(vipdatalist.getVIP_Name());
        } else {
            tvChooseMember.setText(vipdatalist.getVCH_Card());
        }

        if (vipdatalist.getVIP_Name() != null) {
            tvMemberInfoName.setText(vipdatalist.getVIP_Name());
        } else {
            tvMemberInfoName.setText("无");
        }
        tvMemberInfoCard.setText(vipdatalist.getVCH_Card());
        tvMemberInfoIntegral.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableIntegral() + ""));
        tvMemberInfoBalance.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableBalance() + ""));
    }


    /**
     * 提交快速充次
     */
    private void addOrder() {
        RequestParams params = new RequestParams();
        params.put("VIP_Card", mVipCard);
        params.put("WO_OrderCode", CreateOrder.createOrder("JC"));
        params.put("WO_Remark", "");
        params.put("wouldOrderDetail[0][SG_GID]", mServiceInfo.getGID());
        params.put("wouldOrderDetail[0][SG_Name]", mServiceInfo.getPM_Name());
        params.put("wouldOrderDetail[0][SG_Code]", mServiceInfo.getPM_Code());
        params.put("wouldOrderDetail[0][SG_Price]", mServiceInfo.getPM_UnitPrice());
        params.put("wouldOrderDetail[0][SGC_ClasName]", mServiceInfo.getPT_Name());
        params.put("wouldOrderDetail[0][WOD_UseNumber]", 1);
        //提成员工
        if (mStaffListGid.size() > 0) {
            params.put("wouldOrderDetail[0][EM_GIDList][]", mStaffListGid.get(0));
        }
        params.put("wouldOrderDetail[0][SG_Metering]", "");
        params.put("IS_Sms", cbMessage.isChecked());
        params.put("Type", "1");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final KSJC_Success_Bean ksjc_success_bean = CommonFun.JsonToObj(responseString, KSJC_Success_Bean.class);

                CustomToast.makeText(FastChargesActivity.this, "计次成功", Toast.LENGTH_SHORT).show();
                if (cbPrint.isChecked()) {
                    //打印小票
                    new HttpGetPrintContents(FastChargesActivity.this, MyApplication.JCXF_PRINT_TIMES, ksjc_success_bean.getData().getGID()).JCXF();
                }
                tvChooseMember.setText("选择会员");
                tvMemberInfoName.setText("无");
                tvMemberInfoCard.setText("");
                tvMemberInfoIntegral.setText("");
                tvMemberInfoBalance.setText("");
                etSearch.setText("");
//                        tvPayConfirmEmployee.setText("");
//                        mStaffListGid.clear();

//                        finish();
//                        startActivity(new Intent(FastChargesActivity.this, MemberChargeManagementActivity.class));

            }

            @Override
            public void onFailure(String msg) {

                mSweetAlertDialog = new SweetAlertDialog(FastChargesActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("快速计次失败");
                if (msg.equals("BuySms")) {
                    mSweetAlertDialog.setContentText("短信不足，请前去pc端购买!");
                } else {
                    mSweetAlertDialog.setContentText(msg);
                }
                mSweetAlertDialog.setConfirmText("了解");
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
//                        finish();
//                        startActivity(new Intent(FastChargesActivity.this, MemberChargeManagementActivity.class));
                    }
                });
                mSweetAlertDialog.show();
            }
        };
//        callBack.setLoadingAnimation(this,"查找会员...",false);
        HttpHelper.post(FastChargesActivity.this, HttpAPI.API().CHAORDER, params, callBack);
    }
}
