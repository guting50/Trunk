package com.zhiluo.android.yunpu.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.YanZhengMaBean;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.RegexUtil;
import com.zhiluo.android.yunpu.utils.TextChange;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 找回密码界面1
 */
public class NewpasswordActivity extends BaseActivity implements TextWatcher {
    private Button button;
    private TextView back;
    private EditText ediphone;
    private TextView tvget;
    private CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tvget.setEnabled(false);
            tvget.setText((millisUntilFinished / 1000) + "秒后可重发");
        }

        @Override
        public void onFinish() {
            ok();
        }
    };
    private Button btnnext;
    private String yzmurl;
    private EditText edinum;

    private void ok() {
        tvget.setText("获取验证码");
        tvget.setEnabled(true);
    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword);
        initView();
     //   initcode();
        initListenin();
        ok();
    }

    private void getCode() {
        RequestParams params = new RequestParams();
        params.put("LoginAccount", ediphone.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(NewpasswordActivity.this, "验证码已发送，请注意查收", Toast.LENGTH_SHORT).show();
                timer.start();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(NewpasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void redirect(String statusCode) {
                MyApplication.BASE_URL = TextChange.subString(4,MyApplication.BASE_URL);
                MyApplication.IMAGE_URL =TextChange.subString(4,MyApplication.IMAGE_URL);
                MyApplication.CTMONEY_URL = TextChange.subString(4,MyApplication.CTMONEY_URL);
                getCode(statusCode);
            }
        };
        HttpHelper.post(NewpasswordActivity.this, "User/ResetPasswordVerify", params, callBack);
    }

    private void getCode(String str) {
        RequestParams params = new RequestParams();
        params.put("LoginAccount", ediphone.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(NewpasswordActivity.this, "验证码已发送，请注意查收", Toast.LENGTH_SHORT).show();
                timer.start();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(NewpasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

        };
        HttpHelper.post(NewpasswordActivity.this, str, params, callBack);
    }


    private void initnextnewpassword() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("Verify", edinum.getText().toString());
        params.put("Account",ediphone.getText().toString());
        client.post(HttpAPI.API().SENDCODE, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    Li("-----yanzhengma"+ new String(responseBody, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (statusCode == 200) {
                    String resurt = null;
                    try {
                        resurt = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        Adduserbean adduserbean = CommonFun.JsonToObj(resurt, Adduserbean.class);
                        boolean isture = adduserbean.isSuccess();
                        if (isture) {
                            Intent intent = new Intent(NewpasswordActivity.this, NewpasswordTwoActivity.class);
                            intent.putExtra("Account",ediphone.getText().toString());
                            intent.putExtra("phone", "");
                            startActivity(intent);
                        } else {
                            new SweetAlertDialog(NewpasswordActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(adduserbean.getMsg() + "!")
                                    .setConfirmText("了解")
                                    .show();
//                            CustomToast.makeText(NewpasswordActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
                        }

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                CustomToast.makeText(NewpasswordActivity.this,"获取验证码失败",Toast.LENGTH_SHORT).show();
                try {
                    Li("-----yanzhengma"+ new String(responseBody, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initListenin() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ediphone.getText())) {
                    CustomToast.makeText(NewpasswordActivity.this, "手机号码或邮箱不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(edinum.getText())) {
                    CustomToast.makeText(NewpasswordActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    initnextnewpassword();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    RegisterVerify();
                String account = ediphone.getText().toString();
                if (TextUtils.isEmpty(account)) {
                    CustomToast.makeText(NewpasswordActivity.this, "请输入手机号或邮箱", Toast.LENGTH_SHORT).show();
                } else if (!RegexUtil.isTelPhoneNumber(account) && !YSLUtils.isEmail(account)) {
                    CustomToast.makeText(NewpasswordActivity.this, "手机号或邮箱不正确，请重新输入", Toast.LENGTH_SHORT).show();
                } else {
                    getCode();
                }

            }
        });


    }


    private void initView() {
        button = (Button) findViewById(R.id.btn_newpasswordnext_activity);
        back = (TextView) findViewById(R.id.tv_newpassworedback_activity);
        ediphone = (EditText) findViewById(R.id.edi_newpasswordphone_activity);
        tvget = (TextView) findViewById(R.id.tet_newpasswordget_activity);
        edinum = (EditText) findViewById(R.id.edi_singtwonum_activity);
        ediphone.addTextChangedListener(this);

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
