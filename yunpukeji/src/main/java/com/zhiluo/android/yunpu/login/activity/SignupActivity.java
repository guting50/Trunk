package com.zhiluo.android.yunpu.login.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.YanZhengMaBean;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

/**
 * 注册界面1，作用发送手机验证码
 */
public class SignupActivity extends BaseActivity implements TextWatcher {

    private EditText ediphone;
    private Button next;
    private EditText ediyam;
    private WebView webyzm;
    private String yzmurl;
    private Button btnfreshyzm;
    private TextView imvback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
       // initcode();
        initListenin();
    }

    private void initcode() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        client.post(HttpAPI.API().GETCODE, params, new AsyncHttpResponseHandler() {
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
                            YanZhengMaBean yanZhengMaBean = CommonFun.JsonToObj(resurt, YanZhengMaBean.class);
                            yzmurl = yanZhengMaBean.getData();
                            webyzm.loadUrl("data:image/gif;base64," + yzmurl);
                        } else {
                            new SweetAlertDialog(SignupActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(adduserbean.getMsg() + "!")
                                    .setConfirmText("了解")
                                    .show();
//                            CustomToast.makeText(SignupActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void initListenin() {
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ediphone.getText())) {
                    CustomToast.makeText(SignupActivity.this, "手机号码或邮箱不能为空", Toast.LENGTH_SHORT).show();
                } else if (!isMobile(ediphone.getText().toString()) && !YSLUtils.isEmail(ediphone.getText().toString())) {
                    CustomToast.makeText(SignupActivity.this, "请输入正确的手机号码或邮箱", Toast.LENGTH_SHORT).show();
                } else {
                    RegisterVerify();
                }
            }
        });
        btnfreshyzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                initcode();
            }
        });
        imvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void RegisterVerify() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("LoginAccount", ediphone.getText().toString());
        params.put("Code", ediyam.getText().toString());
        client.post(HttpAPI.API().REGISTER, params, new AsyncHttpResponseHandler() {
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
                            Intent intent = new Intent(SignupActivity.this, SignupTwoActivity.class);
                            intent.putExtra("phone", ediphone.getText().toString());
                            intent.putExtra("yzm", ediyam.getText().toString());
                            startActivity(intent);
                        } else {
                            new SweetAlertDialog(SignupActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(adduserbean.getMsg() + "!")
                                    .setConfirmText("了解")
                                    .show();
//                            CustomToast.makeText(SignupActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void initView() {
        ediphone = (EditText) findViewById(R.id.edi_singupphone_activity);
        ediphone.addTextChangedListener(this);
        next = (Button) findViewById(R.id.btn_singupnext_activity);
        ediyam = (EditText) findViewById(R.id.edi_yzmsignup_activity);
        webyzm = (WebView) findViewById(R.id.web_yzmsignup_activity);
        btnfreshyzm = (Button) findViewById(R.id.btn_freshyzmsignup_activity);
        imvback = (TextView) findViewById(R.id.tv_singupback_activity);
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
        String num = "[1][35879]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

}
