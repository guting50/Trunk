package com.zhiluo.android.yunpu.member.consume.activity;

import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.basewin.aidl.OnPrinterListener;
import com.basewin.services.ServiceManager;
import com.google.gson.Gson;
import com.lkl.cloudpos.aidl.printer.AidlPrinterListener;
import com.lkl.cloudpos.aidl.printer.PrintItemObj;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.activity.LoginActivity;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.jsonbean.VipDetails;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.setting.BluePrintSettingActivity;
import com.zhiluo.android.yunpu.setting.bluetooth.activity.BlueToothActivity;
import com.zhiluo.android.yunpu.setting.bluetooths.BluetoothUtil;
import com.zhiluo.android.yunpu.sms.jsonbean.SmsSwitch;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.ESCUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.config.MyApplication.printerDev;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 加减积分
 */
public class PlusReduceIntegralActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_scan)
    LinearLayout llScan;
    private TextView vipname, vipcard, vipyue, vipjifen, tvhadjifen, tvnowjifen, ivback;
    private ImageView saoma;
    private TextView btnsure;
    private VipDetails.DataBean vipdatalist;
    private EditText inputjifen, etbeizhu, searchvip;
    private RadioGroup radioGroup;
    private String istate;
    private boolean isAdd;
    private String vcard = null;
    private SharedPreferences sp, pref;
    private SharedPreferences.Editor editor;
    private String type;
    private BluetoothUtil bluetoothUtil = new BluetoothUtil(this);
    private BluetoothAdapter btAdapter;
    private String mMaccode;
    private boolean flag;
    private String muser;
    private String mytitle;

    private JSONObject printJson = new JSONObject();
    private PrinterListener printer_callback = new PrinterListener();
    private BlueToothActivity mBlueToothActivity = new BlueToothActivity();

    private AllMemberListBean.DataBean.DataListBean mMemberInfo;//会员信息
    private SweetAlertDialog mSweetAlertDialog;
    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间
    private RelativeLayout rChoiseVip;
    private ImageView imgRight;//右边的图标
    private TextView tvVipName;//中间的字
    private CheckBox cbMessage, cbPrint;
    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;//参数开关
    private String content;
    private boolean isSearch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plusreduceintegral);
        ButterKnife.bind(this);
        initview();
        initSp();
        setCbShortMessage("007");
        clickLisener();
        pref = this.getSharedPreferences("recepits", Context.MODE_PRIVATE);

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
            searchvip.setText(CardId);
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
            new CardOperationUtils(this, searchvip);
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

    private void initSp() {
        sp = getSharedPreferences("setting", MODE_PRIVATE);
        editor = sp.edit();
        mMaccode = sp.getString("adress", "");
        muser = sp.getString("myuser", "");
        mytitle = sp.getString("mytitle", "");
        tvTitle.setText("加减积分");

        if (mMaccode != null && bluetoothUtil.isOpen() && !mMaccode.equals("")) {
            flag = bluetoothUtil.connect(mMaccode);
            if (flag == false) {
                // 连接失败
                new SweetAlertDialog(PlusReduceIntegralActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        .setContentText("连接失败！")
                        .setConfirmText("了解")
                        .show();
                //                CustomToast.makeText(PlusReduceIntegralActivity.this, "连接失败！", Toast.LENGTH_SHORT).show();
            } else {
                // 连接成功
                //  CustomToast.makeText(MemberRechargeActivity.this,"连接成功！",Toast.LENGTH_SHORT).show();
            }
        }

        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {

                    //必须刷卡
                    if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            rChoiseVip.setEnabled(false);
                            llScan.setEnabled(false);
                            llScan.setBackgroundColor(getResources().getColor(R.color.lightgray));
                            searchvip.setHint("请使用刷卡机刷卡");
                            searchvip.setFocusable(false);
                            searchvip.setFocusableInTouchMode(false);
                            saoma.setEnabled(false);
                        } else {

                            rChoiseVip.setEnabled(true);
                            llScan.setEnabled(true);
                            llScan.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                            searchvip.setHint("请输入会员卡号/手机号");
                            searchvip.setFocusable(true);
                            searchvip.setFocusableInTouchMode(true);
                            saoma.setEnabled(true);
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
                                    rChoiseVip.setEnabled(false);
                                    llScan.setEnabled(false);
                                    llScan.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                    searchvip.setHint("请使用刷卡机刷卡");
                                    searchvip.setFocusable(false);
                                    searchvip.setFocusableInTouchMode(false);
                                    saoma.setEnabled(false);
                                } else {

                                    rChoiseVip.setEnabled(true);
                                    llScan.setEnabled(true);
                                    llScan.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                                    searchvip.setHint("请输入会员卡号/手机号");
                                    searchvip.setFocusable(true);
                                    searchvip.setFocusableInTouchMode(true);
                                    saoma.setEnabled(true);
                                }
                            }

                        }
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(PlusReduceIntegralActivity.this, "获取系统开关失败" + msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(PlusReduceIntegralActivity.this, HttpAPI.API().PRE_LOAD, callBack);
    }


    private void clickLisener() {
        saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(PlusReduceIntegralActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        //选择会员
        rChoiseVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (YSLUtils.isFastClick()) {
                    Intent intent = new Intent(PlusReduceIntegralActivity.this, CheckMemberInfoActivity.class);
                    startActivityForResult(intent, 888);
                }
            }
        });

        searchvip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //         postVip(charSequence.toString()); // TODO: 2018-03-21  这方法是接手前注释的
                pretime = System.currentTimeMillis();
                //已经调用了一次就不在调用
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
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                inputjifen.setText("");//当用户改变积分状态的时候（增减积分<------>扣除积分）清空  输入积分 控件的值
                switch (checkedId) {
                    //增加积分
                    case R.id.rb1:
                        istate = "4";
                        isAdd = true;
                        break;
                    //扣除积分
                    case R.id.rb2:
                        istate = "3";
                        isAdd = false;
                        break;
                }
            }
        });
        RadioButton childAt = (RadioButton) radioGroup.getChildAt(0);
        childAt.setChecked(true);
        inputjifen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                double inputdouble;
                if (vcard != null && !vcard.equals("")) {
                    if (s.toString().length() > 0) {
                        inputdouble = Double.parseDouble(inputjifen.getText().toString());
                    } else {
                        inputdouble = 0;
                    }
                    double hanjf = Double.parseDouble(vipjifen.getText().toString());
                    double nowjifen;
                    if (isAdd) {
                        nowjifen = inputdouble + hanjf;
                    } else {
                        nowjifen = hanjf - inputdouble;
                    }
                    if (nowjifen < 0) {
                        CustomToast.makeText(PlusReduceIntegralActivity.this, "亲，您的积分不够啦", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    tvnowjifen.setText(Decima2KeeplUtil.stringToDecimal(nowjifen + ""));
                }
            }
        });

        btnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //提交修改信息
                if (inputjifen.getText().toString().isEmpty()) {
                    mSweetAlertDialog = new SweetAlertDialog(PlusReduceIntegralActivity.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("提示");
                    mSweetAlertDialog.setContentText("请输入积分！");
                    mSweetAlertDialog.setConfirmText("确认");
                    mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mSweetAlertDialog.dismiss();
                        }
                    });
                    mSweetAlertDialog.show();
                } else {
                    posta();
                }
            }
        });

    }

    private void posta() {
        double d = Double.parseDouble(Decima2KeeplUtil.stringToDecimal(inputjifen.getText().toString()));
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("Card", vipcard.getText().toString());
        params.put("IdentifyingState", istate);
        params.put("Remark", etbeizhu.getText().toString());
        params.put("Number", d);
        params.put("IS_Sms", cbMessage.isChecked());
        MyTextHttpResponseHandler responseHandler = new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
//                if (IS_BASEWIN_POS_DEVICE) {
//                    print();
//                } else if (IS_LAKALA_POS_DEVICE) {
//                    if (getPrintState()) {
//                        new Thread(runnable).start();
//                    }
//                } else {
//                    byte[] bytes = vipcharData();
//                    mBlueToothActivity.send(bytes, pref.getInt("recepits_num", 2), getSharedPreferences("bluetooth_address", MODE_PRIVATE));
//                }

                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PlusReduceIntegralActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText("积分已改动！");
                sweetAlertDialog.setConfirmText("确定");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.setOnDismissListener(
                        new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        vipdatalist = null;
                        initnew();
                        isSearch = false;
                        inputjifen.setText("");
                        content = "";
                        searchvip.setText("");
                        etbeizhu.setText("");
                    }
                });
                sweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                if (msg.contains("SmsSign")) {//短信未设置默认签名
                    mSweetAlertDialog = new SweetAlertDialog(PlusReduceIntegralActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，未设置默认签名！");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            finish();
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.contains("BuySms")) {//短信库存不足
                    mSweetAlertDialog = new SweetAlertDialog(PlusReduceIntegralActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("支付成功");
                    mSweetAlertDialog.setContentText("短信未发送，短信库存不足！");
                    mSweetAlertDialog.setConfirmText("确定");
                    if (mMemberInfo == null) {
                        mSweetAlertDialog.setCancelText("添加为会员");
                    }
                    mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent intent = new Intent(PlusReduceIntegralActivity.this, AddMemberActivity.class);
                            startActivity(intent);
                        }
                    });
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            finish();
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (msg.contains("VCH_C")) {
                    new SweetAlertDialog(PlusReduceIntegralActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("请选择会员！")
                            .setConfirmText("了解")
                            .show();

                } else {
                    new SweetAlertDialog(PlusReduceIntegralActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText(msg)
                            .setConfirmText("了解")
                            .show();
                }
            }
        };
        responseHandler.setDialog(PlusReduceIntegralActivity.this, "正在提交...", false);
        client.post(HttpAPI.API().ADDJIFEN, params, responseHandler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
        }
    }

    private void postVip(String k, final boolean issearch) {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        if (!isSearch){
            params.put("AccurateOfCard", k);
        }
        params.put("VCH_Card", k);
        client.post(HttpAPI.API().QUERY_SINGLE_MEMBER, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                isSearch = true;
                VipDetails vipdetails = CommonFun.JsonToObj(responseString, VipDetails.class);
                vipdatalist = vipdetails.getData();
                initnew();
                String mMemberName = vipdatalist.getVIP_Name();
                if (mMemberName != null && !"".equals(mMemberName)) {
                    tvVipName.setText(mMemberName);
                } else {
                    tvVipName.setText(vipdatalist.getVCH_Card());
                }
            }

            @Override
            public void onFailure(String msg) {

                if (MyApplication.VIP_CARD != null) {
                    postVip(MyApplication.VIP_CARD,true);
                    MyApplication.VIP_CARD = null;
                } else {
                    tvVipName.setText("选择会员");
                    tvhadjifen.setText("");
                    tvnowjifen.setText("");
                    vipname.setText("");
                    vipcard.setText("");
                    vipjifen.setText("0.00");
                    vipyue.setText("0.00");
                }

                if (msg.contains("没有任何执行操作")) {
                    //查询结果有多个 跳到选择会员页面选择
                    Intent intent = new Intent(PlusReduceIntegralActivity.this, CheckMemberInfoActivity.class);
                    intent.putExtra("number", content);
                    startActivityForResult(intent, 888);
                }

            }
        });
    }

    private void initnew() {
        if (vipdatalist!=null){
            vcard = vipdatalist.getVCH_Card();
            vipname.setText(vipdatalist.getVIP_Name());
            vipcard.setText(vipdatalist.getVCH_Card());
            vipjifen.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableIntegral()));
            vipyue.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableBalance()));
            tvhadjifen.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableIntegral()));
            tvnowjifen.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableIntegral()));
            inputjifen.setText("");
        }else {
            vcard = null;
            vipname.setText("无");
            vipcard.setText("无");
            vipjifen.setText("0.00");
            vipyue.setText("0.00");
            tvhadjifen.setText("0.00");
            tvnowjifen.setText("0.00");
            inputjifen.setText("");
        }


    }

    private void initview() {
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        cbMessage = findViewById(R.id.cb_print);
        cbMessage = findViewById(R.id.cb_short_message);
        ivback = (TextView) findViewById(R.id.tv_back_activity);
        saoma = (ImageView) findViewById(R.id.iv_scan_plusreduceintegralactivity);
        //        ivsearch = (ImageView) findViewById(R.id.imv_search_list);
        vipname = (TextView) findViewById(R.id.tv_member_info_name);
        vipcard = (TextView) findViewById(R.id.tv_member_info_card);
        vipyue = (TextView) findViewById(R.id.tv_member_info_balance);
        vipjifen = (TextView) findViewById(R.id.tv_member_info_integral);
        tvhadjifen = (TextView) findViewById(R.id.tvhadjifen_activity);
        tvnowjifen = (TextView) findViewById(R.id.tv_nowjifen_activity);
        btnsure = findViewById(R.id.btn_sure);
        searchvip = (EditText) findViewById(R.id.et_membercard_plusreduceintegralactivity);
        inputjifen = (EditText) findViewById(R.id.edi_vipgivemannyrecharge_activity);
        etbeizhu = (EditText) findViewById(R.id.edi_beuzhu_activity);
        radioGroup = (RadioGroup) findViewById(R.id.rgroup);
        rChoiseVip = findViewById(R.id.r_choise_vip);
        imgRight = findViewById(R.id.iv_choose_member);
        tvVipName = findViewById(R.id.tv_choose_member);
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
                            CustomToast.makeText(PlusReduceIntegralActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
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
     * 获取短信开关
     */
    private void getSmsSet(final String code) {
        HttpHelper.post(PlusReduceIntegralActivity.this, HttpAPI.API().SMS_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SmsSwitch bean = CommonFun.JsonToObj(responseString, SmsSwitch.class);
                for (int i=0;i<bean.getData().size();i++){
                    if (bean.getData().get(i).getST_Code().equals(code)){
                        if (bean.getData().get(i).getST_State() == null || !bean.getData().get(i).getST_State().equals("1")) {
                            cbMessage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CustomToast.makeText(PlusReduceIntegralActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            searchvip.setText(scanResult);
        }
        if (requestCode == 888 && resultCode == 2222) {
            mMemberInfo = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");
            if (mMemberInfo.getVIP_Name() != null && !"".equals(mMemberInfo.getVIP_Name())) {
                tvVipName.setText(mMemberInfo.getVIP_Name());
            }
            if (mMemberInfo != null) {
                isSearch = false;
                searchvip.setText(mMemberInfo.getVCH_Card());
                if (mMemberInfo.getVIP_Name() != null) {
                    vipname.setText(mMemberInfo.getVIP_Name());
                } else {
                    vipname.setText(mMemberInfo.getVCH_Card());
                }
                vipcard.setText(mMemberInfo.getVCH_Card());
                vipyue.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableBalance() + ""));
                vipjifen.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableIntegral() + ""));
//                postVip(mMemberInfo.getVCH_Card(),false);
            } else {
                tvVipName.setText("选择会员");
                vipname.setText("无");
                vipcard.setText("无");
                vipyue.setText("0.00");
                vipjifen.setText("0.00");
            }
        }
    }


    //积分充值小票
    public byte[] vipcharData() {

        BluePrintSettingActivity bset = new BluePrintSettingActivity();
        String titleset = bset.getHeadSet();
        String bottomset = bset.getBottonSet();
        //  String danhao = "消费单号:" + goc;
        String huiyuankahao = "会员卡号:" + vipcard.getText().toString();
        String huiyuanming = "会员名称:" + vipname.getText().toString();
        String xiaofeijine = "原始积分:" + Decima2KeeplUtil.stringToDecimal(tvhadjifen.getText().toString());
        String sfje = "操作积分:" + Decima2KeeplUtil.stringToDecimal(inputjifen.getText().toString());
        String yinfujine = "剩余积分:" + Decima2KeeplUtil.stringToDecimal(tvnowjifen.getText().toString());
        String zengson = "备注:" + etbeizhu.getText().toString();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String date = "消费时间:" + sDateFormat.format(new Date());
        LoginActivity loginActivity = new LoginActivity();
        String creator = "操作人员:" + muser;
        String tiltle = loginActivity.getShopname() + "默认店铺";
        try {
            byte[] next2Line = ESCUtil.nextLine(2);
            //            byte[] title = titleset.getBytes("gb2312");
            byte[] title = mytitle.getBytes("gb2312");
            byte[] bottom = "谢谢惠顾！".getBytes("gb2312");
            //            byte[] bottom = bottomset.getBytes("gb2312");
            byte[] tickname = "积分加减小票".getBytes("gb2312");
            //  byte[] ordernum = danhao.getBytes("gb2312");
            byte[] vipcardnum = huiyuankahao.getBytes("gb2312");
            byte[] vipname = huiyuanming.getBytes("gb2312");
            byte[] xiahuaxian = "------------------------------".getBytes("gb2312");
            byte[] xiaofeinum = xiaofeijine.getBytes("gb2312");
            byte[] youhuinum = zengson.getBytes("gb2312");
            byte[] yinfu = yinfujine.getBytes("gb2312");
            byte[] shoulepay = yinfujine.getBytes("gb2312");
            byte[] factpay = sfje.getBytes("gb2312");
            byte[] zhaoqian = "找零金额:".getBytes("gb2312");
            byte[] user = creator.getBytes("gb2312");
            byte[] ordertime = date.getBytes("gb2312");
            byte[] customs = "客户签名:".getBytes("gb2312");
            byte[] sername = "服务名称:".getBytes("gb2312");
            byte[] serprice = "单价:".getBytes("gb2312");
            byte[] sernum = "次数:".getBytes("gb2312");
            byte[] sertotlenum = "剩余:".getBytes("gb2312");

            byte[] boldOn = ESCUtil.boldOn();
            byte[] fontSize2Big = ESCUtil.fontSizeSetBig(3);
            byte[] center = ESCUtil.alignCenter();
            byte[] Focus = "网 507".getBytes("gb2312");
            byte[] boldOff = ESCUtil.boldOff();
            byte[] fontSize2Small = ESCUtil.fontSizeSetSmall(3);
            byte[] left = ESCUtil.alignLeft();
            boldOn = ESCUtil.boldOn();
            byte[] fontSize1Big = ESCUtil.fontSizeSetBig(2);
            boldOff = ESCUtil.boldOff();
            byte[] fontSize1Small = ESCUtil.fontSizeSetSmall(2);
            next2Line = ESCUtil.nextLine(2);
            byte[] nextLine = ESCUtil.nextLine(1);
            nextLine = ESCUtil.nextLine(1);
            byte[] tips_2 = "饭后点评再奖5毛".getBytes("gb2312");
            byte[] next4Line = ESCUtil.nextLine(4);
            byte[] breakPartial = ESCUtil.feedPaperCutPartial();
            byte[][] mticket = {nextLine, center, boldOn, title, boldOff, nextLine, left, tickname, nextLine, left,
                    vipcardnum, nextLine,
                    left, vipname, nextLine, xiahuaxian, nextLine, left, xiaofeinum,
                    nextLine, left, factpay, nextLine, left, shoulepay, nextLine, xiahuaxian, next2Line,
                    left, user, nextLine, left, ordertime, nextLine, left, customs, next2Line, center, bottom,
                    nextLine, breakPartial};

            return ESCUtil.byteMerger(mticket);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * basewin_pos打印小票
     */
    private void print() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String date = "消费时间：" + sDateFormat.format(new Date());
        //        String detial =
        //                "=====================" + "\n" +
        //                "会员：" + tvvipname.getText().toString() + "\n"+
        //                "卡号：" + tvvipcarnum.getText().toString() + "\n" +
        //                "充值金额：" + edimanny.getText().toString() + "\n" +
        //                "赠送金额：" + edigivemanny.getText().toString() + "\n" +
        //                "充值合计：" + tvtotalmanny.getText().toString() + "\n";
        String detial = "==============================" + "\n" +
                "积分充值小票" + "\n" +
                "会员编号：" + vipcard.getText().toString() + "\n" +
                "会员名称：" + vipname.getText().toString() + "\n" +
                "==============================" + "\n" +
                "原始积分：" + Decima2KeeplUtil.stringToDecimal(tvhadjifen.getText().toString()) + "\n" +
                "操作积分：" + Decima2KeeplUtil.stringToDecimal(inputjifen.getText().toString()) + "\n" +
                "剩余积分：" + Decima2KeeplUtil.stringToDecimal(tvnowjifen.getText().toString()) + "\n" +
                "==============================" + "\n" +
                "备注：" + etbeizhu.getText().toString() + "\n" +
                "操作人员：" + muser + "\n" +
                date;
        // 組打印json字符串
        JSONArray printTest = new JSONArray();
        // 添加文本打印,正常
        // add text printer
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        JSONObject json3 = new JSONObject();
        try {
            //头部
            json1.put("content-type", "txt");
            json1.put("content", mytitle);
            json1.put("size", "2");
            json1.put("position", "center");
            json1.put("offset", "0");
            json1.put("bold", "0");
            json1.put("italic", "0");
            json1.put("height", "-1");
            //正文
            json2.put("content-type", "txt");
            json2.put("content", detial);
            json2.put("size", "2");
            json2.put("position", "left");
            json2.put("offset", "0");
            json2.put("bold", "0");
            json2.put("italic", "0");
            json2.put("height", "-1");
            //尾部
            json3.put("content-type", "txt");
            json3.put("content", "谢谢惠顾！");
            json3.put("size", "2");
            json3.put("position", "center");
            json3.put("offset", "0");
            json3.put("bold", "0");
            json3.put("italic", "0");
            json3.put("height", "-1");
            printTest.put(json1);
            printTest.put(json2);
            printTest.put(json3);
            printJson.put("spos", printTest);
            // 设置底部空3行
            // Set at the bottom of the empty 3 rows
            ServiceManager.getInstence().getPrinter().printBottomFeedLine(3);
            Bitmap qr = BitmapFactory.decodeResource(getResources(), R.mipmap.abc_ic_go);
            Bitmap[] bitmaps = new Bitmap[]{Bitmap.createScaledBitmap(qr, 240, 240, true)};
            ServiceManager.getInstence().getPrinter().print(printJson.toString(), bitmaps, printer_callback);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int recepits_num = pref.getInt("recepits_num", 2);
            for (int i = 0; i < recepits_num; i++) {
                try {
                    printText();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * 拉卡拉打印小票
     */
    @SuppressWarnings("serial")
    public void printText() {
        try {
            if (printerDev != null) {
                printerDev.printText(new ArrayList<PrintItemObj>() {
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
                    String date = "消费时间：" + sDateFormat.format(new Date());
                    //        String detial =
                    //                "=====================" + "\n" +
                    //                "会员：" + tvvipname.getText().toString() + "\n"+
                    //                "卡号：" + tvvipcarnum.getText().toString() + "\n" +
                    //                "充值金额：" + edimanny.getText().toString() + "\n" +
                    //                "赠送金额：" + edigivemanny.getText().toString() + "\n" +
                    //                "充值合计：" + tvtotalmanny.getText().toString() + "\n";
                    String detail = "==============================" + "\n" +
                            "积分充值小票" + "\n" +
                            "会员编号：" + vipcard.getText().toString() + "\n" +
                            "会员名称：" + vipname.getText().toString() + "\n" +
                            "==============================" + "\n" +
                            "原始积分：" + Decima2KeeplUtil.stringToDecimal(tvhadjifen.getText().toString()) + "\n" +
                            "操作积分：" + Decima2KeeplUtil.stringToDecimal(inputjifen.getText().toString()) + "\n" +
                            "剩余积分：" + Decima2KeeplUtil.stringToDecimal(tvnowjifen.getText().toString()) + "\n" +
                            "==============================" + "\n" +
                            "备注：" + etbeizhu.getText().toString() + "\n" +
                            "操作人员：" + muser + "\n" +
                            date;

                    {

                        add(new PrintItemObj(mytitle, 8, false, PrintItemObj.ALIGN.CENTER));
                        add(new PrintItemObj(detail, 8, false, PrintItemObj.ALIGN.LEFT));
                        add(new PrintItemObj("谢谢惠顾\n\n\n", 8, false, PrintItemObj.ALIGN.CENTER, false, true, 1000));
                    }
                }, new AidlPrinterListener.Stub() {
                    @Override
                    public void onPrintFinish() throws RemoteException {
                        Log.d("TAG", "打印完成");
                    }

                    @Override
                    public void onError(int arg0) throws RemoteException {
                        Log.d("TAG", "打印出错，错误码为：" + arg0);
                    }
                });
            } else {
                Log.d("TAG", "打印机对象为空");
                return;
            }

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("TAG", "打印异常");
        }
    }

    private class PrinterListener implements OnPrinterListener {
        @Override
        public void onError(int i, String s) {
            //            new SweetAlertDialog(PlusReduceIntegralActivity.this, SweetAlertDialog.WARNING_TYPE)
            //                    .setTitleText("提示")
            //                    .setContentText("打印出错" + i + " detail = " + s + "\n")
            //                    .setConfirmText("了解")
            //                    .show();
            CustomToast.makeText(PlusReduceIntegralActivity.this, "打印出错" + i + " detail = " + s + "\n", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFinish() {

        }

        @Override
        public void onStart() {

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
                    Li("输入完成");
                    doQurry();
                    break;
            }
        }
    }

    /**
     * 查询会员信息
     */
    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {

            if (content != null && !content.equals("")) {
                postVip(content,isSearch);
            }
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }
}
