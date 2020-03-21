package com.zhiluo.android.yunpu.member.manager.activity;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.member.manager.bean.YSLMemberInfoBean;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.UnsupportedEncodingException;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;


/**
 * Created by ${YSL} on 2018-04-04.
 */

public class ChangeCardActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mBack, mCommit;
    private EditText mCardNum,mCardfacenum, mPassWordOne, mPassWordTwo;
    private ImageView mSaoMa;
    private CheckBox mUseOldPwd;
    private Bundle mBundle;
    private YSLMemberInfoBean.DataBean.DataListBean mVipInfo;
    private Intent mIntent;
    private boolean isChanged;
    private String mCardString="",mCardFaceNum;
    private boolean isCardNum;

    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_change_card);
        initViews();
        initDates();

        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE){
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            Intent nfcIntent = new Intent(this, getClass());
            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mPendingIntent = PendingIntent.getActivity(this, 0, nfcIntent, 0);
        }
    }


    //获取系统隐式启动的
    @Override
    public void onNewIntent(Intent intent) {
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        if (tagFromIntent!=null){
            String CardId = ByteArrayToHexString.ByteArrayToHex(tagFromIntent.getId());
            if (null != CardId) {
                MyApplication.VIP_CARD =  CardId;
                while (CardId.length()<10){
                    CardId = "0"+CardId;
                }
                mCardNum.setText(CardId);
            }
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
        if (!MyApplication.IS_SUNMI_POS_V1S_DEVICE){
            new CardOperationUtils(this, mCardNum);
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

    private void initDates() {
        mBundle = getIntent().getBundleExtra("bundle");
        mVipInfo = (YSLMemberInfoBean.DataBean.DataListBean) mBundle.getSerializable("YSL_VIP_INFO");//带计次的会员信息
        mIntent = new Intent();
        isChanged = false;

        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {

                    //卡面号码
                    if ("208".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mCardfacenum.setEnabled(true);
                            mCardfacenum.setBackground(null);
                            isCardNum = true;
                        } else {
                            mCardfacenum.setEnabled(false);
                            mCardfacenum.setBackground(new ColorDrawable(getResources().getColor(R.color.lightgray)));
                            isCardNum = false;
                        }
                    }
                }
            } else {
                isCardNum = false;
                mCardfacenum.setEnabled(false);
                mCardfacenum.setBackground(new ColorDrawable(getResources().getColor(R.color.lightgray)));
            }
        } else {
            isCardNum = false;
            mCardfacenum.setEnabled(false);
            mCardfacenum.setBackground(new ColorDrawable(getResources().getColor(R.color.lightgray)));
            findViewById(R.id.rl_add_member_face_number).setVisibility(View.GONE);
        }
    }

    private void initViews() {
        mBack = findViewById(R.id.tv_back);
        mCardNum = findViewById(R.id.tv_card);
        mCardfacenum = findViewById(R.id.tv_cardnum);
        mPassWordOne = findViewById(R.id.ed_password_one);
        mPassWordTwo = findViewById(R.id.ed_password_two);
        mSaoMa = findViewById(R.id.img_erweima);
        mUseOldPwd = findViewById(R.id.cb_use_old_pwd);
        mCommit = findViewById(R.id.tv_ok);

        mBack.setOnClickListener(this);
        mCommit.setOnClickListener(this);
        mCardNum.setOnClickListener(this);
        mPassWordOne.setOnClickListener(this);
        mPassWordTwo.setOnClickListener(this);
        mSaoMa.setOnClickListener(this);

        mUseOldPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mPassWordOne.setEnabled(false);
                    mPassWordTwo.setEnabled(false);
                    mPassWordOne.setText("");
                    mPassWordTwo.setText("");
                    mPassWordOne.setBackground(new ColorDrawable(getResources().getColor(R.color.lightgray)));
                    mPassWordTwo.setBackground(new ColorDrawable(getResources().getColor(R.color.lightgray)));
                } else {
                    mPassWordOne.setEnabled(true);
                    mPassWordTwo.setEnabled(true);
                    mPassWordOne.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
                    mPassWordTwo.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                ChangeCardActivity.this.finish();
                break;
            case R.id.img_erweima:
                Intent openCameraIntent = new Intent(ChangeCardActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;
            case R.id.tv_ok:
                commit();
                isChanged = true;
                break;


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            mCardNum.setText(scanResult);
        }
    }

    private void commit() {
        String pwd2;
        String pwd;
        int same;
        mCardString = mCardNum.getText().toString();
        mCardFaceNum = mCardfacenum.getText().toString();

        if (mCardString.length() >= 3) {
            if (mCardFaceNum.length() >= 2 || !isCardNum) {
                if (mUseOldPwd.isChecked()) {
                    same = 1;
                    vipnewcard(same, mCardString, null);
                } else {
                    same = 0;
                    pwd = mPassWordOne.getText().toString();
                    pwd2 = mPassWordTwo.getText().toString();
                    if (pwd.equals(pwd2) && pwd.length() >= 6) {
                        vipnewcard(same, mCardString, pwd);
                    } else {
                        new SweetAlertDialog(ChangeCardActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("提示")
                                .setContentText("请输入相同的密码且密码不小于6位数!")
                                .setConfirmText("了解")
                                .show();
                    }
                }
            } else {
                new SweetAlertDialog(ChangeCardActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        .setContentText("卡面卡号不能小于2位数!")
                        .setConfirmText("了解")
                        .show();
            }

        } else {
            new SweetAlertDialog(ChangeCardActivity.this, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("提示")
                    .setContentText("卡号不能小于3位数!")
                    .setConfirmText("了解")
                    .show();
        }

    }

    /**
     * 用来请求换卡数据
     */
    private void vipnewcard(int issame, String card, String pwd) {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("VCH_Card", card);
        params.put("VCH_OldCard", mVipInfo.getVCH_Card());
        params.put("VCH_IsSamePwd", issame);
        params.put("VCH_Pwd", pwd);
        params.put("VCH_FaceNumber", mCardFaceNum);
        client.post(HttpAPI.API().ALTERCARD, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resurt = null;
                    try {
                        resurt = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        Adduserbean adduserbean = CommonFun.JsonToObj(resurt, Adduserbean.class);
                        boolean isture = adduserbean.isSuccess();
                        if (isture) {
                            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ChangeCardActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                            sweetAlertDialog.setTitleText("提示");
                            sweetAlertDialog.setContentText("换卡成功！");
                            sweetAlertDialog.setConfirmText("确认");
                            sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    /**
                                     * 换卡后更新会员详情卡号
                                     * **/
                                    if (isChanged) {
                                        mIntent.putExtra("card",mCardString );
                                        setResult(953,mIntent);
                                    }
                                    /****/
                                    finish();
                                }
                            });
                            sweetAlertDialog.show();
                        } else {
                            String str ;
                            if (adduserbean.getMsg().contains("不能同时为空")){
                                str = "卡号不能为空!";
                            }else {
                                str = adduserbean.getMsg();
                            }
                            new SweetAlertDialog(ChangeCardActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(str)
                                    .setConfirmText("了解")
                                    .show();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }
}

