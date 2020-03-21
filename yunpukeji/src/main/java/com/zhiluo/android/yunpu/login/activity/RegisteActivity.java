package com.zhiluo.android.yunpu.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.TextChange;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

/**
 * Created by YSL on 2018-08-30.
 */

public class RegisteActivity extends BaseActivity implements TextWatcher {


    @Bind(R.id.tv_member_recharge_back)
    ImageView tvMemberRechargeBack;
    @Bind(R.id.et_account)
    EditText etAccount;
    @Bind(R.id.edi_passwordtwosupc_activity)
    EditText ediPasswordone;
    @Bind(R.id.edi_passwordsupc_activity)
    EditText ediPasswordtwo;
    @Bind(R.id.edi_singtwonum_activity)
    EditText ediSingtwonumActivity;
    @Bind(R.id.tet_singtime_activity)
    TextView tetSingtimeActivity;
    @Bind(R.id.btn_singupnext_activity)
    Button nextActivity;
    @Bind(R.id.tv_clean_acount)
    ImageView tvCleanAcount;
    @Bind(R.id.tv_clean_pwd)
    ImageView tvCleanPwd;
    @Bind(R.id.tv_clean_ensure_pwd)
    ImageView tvCleanEnsurePwd;
    @Bind(R.id.tv_xieyi)
    TextView tvXieyi;

    private SweetAlertDialog mSweetAlertDialog;//提示框
    private boolean isPwd = false;
    private boolean isEnsurePwd = false;


    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tetSingtimeActivity.setEnabled(false);
            tetSingtimeActivity.setText((millisUntilFinished / 1000) + "秒后重发");

        }

        @Override
        public void onFinish() {
            tetSingtimeActivity.setText("获取验证码");
            tetSingtimeActivity.setEnabled(true);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist_activity);
        ButterKnife.bind(this);

        initListenin();
    }


    private void initListenin() {


        tetSingtimeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etAccount.getText())) {
                    CustomToast.makeText(RegisteActivity.this, "手机号码或邮箱不能为空", Toast.LENGTH_SHORT).show();
                } else if (!isMobile(etAccount.getText().toString()) && !YSLUtils.isEmail(etAccount.getText().toString())) {
                    CustomToast.makeText(RegisteActivity.this, "请输入正确的手机号码或邮箱", Toast.LENGTH_SHORT).show();
                } else {
                    RegisterVerify();
                }
            }
        });
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etAccount.getText())) {
                    CustomToast.makeText(RegisteActivity.this, "手机号码或邮箱不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isMobile(etAccount.getText().toString()) && !YSLUtils.isEmail(etAccount.getText().toString())) {
                    CustomToast.makeText(RegisteActivity.this, "请输入正确的手机号码或邮箱", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ediPasswordone.getText())) {
                    CustomToast.makeText(RegisteActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ediPasswordone.getText().toString().length() < 6) {
                    CustomToast.makeText(RegisteActivity.this, "请输入6-20位密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ediPasswordtwo.getText())) {
                    CustomToast.makeText(RegisteActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (ediSingtwonumActivity.getText().toString().isEmpty()) {
                    CustomToast.makeText(RegisteActivity.this, "请输入验证码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (ediPasswordone.getText().toString().equals(ediPasswordtwo.getText().toString())) {
                    initPostdata();
                } else {
                    new SweetAlertDialog(RegisteActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("两次密码不一致,请重新输入！")
                            .setConfirmText("了解")
                            .show();
//                    CustomToast.makeText(SignupContentActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvCleanAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etAccount.setText("");
                etAccount.requestFocus();
            }
        });

        tvCleanPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPwd) {
                    tvCleanPwd.setImageResource(R.drawable.pwd_hidden);
                    //否则隐藏密码
                    ediPasswordone.setTransformationMethod(PasswordTransformationMethod.getInstance());

                } else {
                    tvCleanPwd.setImageResource(R.drawable.pwd_show);
                    //如果选中，显示密码
                    ediPasswordone.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                isPwd = !isPwd;
            }
        });

        tvCleanEnsurePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEnsurePwd) {
                    tvCleanEnsurePwd.setImageResource(R.drawable.pwd_hidden);
                    //否则隐藏密码
                    ediPasswordtwo.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    tvCleanEnsurePwd.setImageResource(R.drawable.pwd_show);
                    //如果选中，显示密码
                    ediPasswordtwo.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
                isEnsurePwd = !isEnsurePwd;
            }
        });

        tvMemberRechargeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvXieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisteActivity.this,UserAgreementActivity.class);
                intent.putExtra("yunpu","yes");
                startActivity(intent);
            }
        });
    }


    /**
     * 提交注册
     **/

    private void initPostdata() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("UserAcount", etAccount.getText().toString());
        params.put("PassWord", ediPasswordtwo.getText().toString());
        params.put("Code", "");
        params.put("MeshCode", ediSingtwonumActivity.getText().toString());
        params.put("RadomCode", "");
        params.put("SUCode", "");
//        params.put("Domain", "");
        params.put("RegSource", "安卓APP");
        params.put("PromoCode", "");
        client.post(HttpAPI.API().ZHUCE, params, new AsyncHttpResponseHandler() {
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
                            toastDialog("注册成功");
                        } else {
                            new SweetAlertDialog(RegisteActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(adduserbean.getMsg() + "!")
                                    .setConfirmText("了解")
                                    .show();
//                            CustomToast.makeText(SignupContentActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
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


    /***
     * 获取验证码
     *
     * **/

    private void RegisterVerify() {

        RequestParams params = new RequestParams();
        params.put("LoginAccount", etAccount.getText().toString());
        params.put("OEMGID", "PlatformUser");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {

                Adduserbean adduserbean = CommonFun.JsonToObj(responseString, Adduserbean.class);
                boolean isture = adduserbean.isSuccess();
                if (isture) {
                    CustomToast.makeText(RegisteActivity.this, "验证码已发送，请注意查收", Toast.LENGTH_SHORT).show();
                    //记时
                    timer.start();

                } else {
                    new SweetAlertDialog(RegisteActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText(adduserbean.getMsg() + "!")
                            .setConfirmText("了解")
                            .show();
//                            CustomToast.makeText(SignupActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(RegisteActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void redirect(String statusCode) {
                MyApplication.BASE_URL = TextChange.subString(4, MyApplication.BASE_URL);
                MyApplication.IMAGE_URL = TextChange.subString(4, MyApplication.IMAGE_URL);
                MyApplication.CTMONEY_URL = TextChange.subString(4, MyApplication.CTMONEY_URL);
                RegisterVerify(statusCode);
            }
        };
        HttpHelper.post(this, HttpAPI.API().REGISTER, params, callBack);

    }

    private void RegisterVerify(String str) {

        RequestParams params = new RequestParams();
        params.put("LoginAccount", etAccount.getText().toString());
        params.put("OEMGID", "PlatformUser");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {

                Adduserbean adduserbean = CommonFun.JsonToObj(responseString, Adduserbean.class);
                boolean isture = adduserbean.isSuccess();
                if (isture) {
                    CustomToast.makeText(RegisteActivity.this, "验证码已发送，请注意查收", Toast.LENGTH_SHORT).show();
                    //记时
                    timer.start();

                } else {
                    new SweetAlertDialog(RegisteActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText(adduserbean.getMsg() + "!")
                            .setConfirmText("了解")
                            .show();
//                            CustomToast.makeText(SignupActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(RegisteActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

        };
        HttpHelper.post(this, str, params, callBack);

    }


    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][356879]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    /**
     * 提示弹出
     *
     * @param msg
     */
    private void toastDialog(String msg) {
        mSweetAlertDialog = new SweetAlertDialog(RegisteActivity.this, SweetAlertDialog.SUCCESS_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCancelable(false);
        mSweetAlertDialog.setConfirmText("确定");
        mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mSweetAlertDialog.dismiss();
                Intent intent = new Intent(RegisteActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();


            }
        });
        mSweetAlertDialog.show();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
