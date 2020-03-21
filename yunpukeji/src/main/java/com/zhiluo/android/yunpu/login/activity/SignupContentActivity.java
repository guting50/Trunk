package com.zhiluo.android.yunpu.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

/**
 * 注册界面3，用于提交数据，
 * 数据有 注册手机号 验证码 手机验证码
 */
public class SignupContentActivity extends BaseActivity {
    private TextView textView;
    private String phone;
    private Button button;
    private String yzmstr;
    private EditText edipassword;
    private EditText edipasswordtwo;
    private String sjyzm;
    private TextView imvback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_content);
        initData();
        initView();
        initListenin();
    }

    private void initListenin() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edipassword.getText().toString().equals(edipasswordtwo.getText().toString())) {
                    initPostdata();
                } else {
                    new SweetAlertDialog(SignupContentActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("两次密码不一致,请重新输入！")
                            .setConfirmText("了解")
                            .show();
//                    CustomToast.makeText(SignupContentActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                }
            }
        });
        imvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initPostdata() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("UserAcount", phone);
        params.put("PassWord", edipassword.getText().toString());
        params.put("Code", "");
        params.put("MeshCode", sjyzm);
        params.put("RadomCode", yzmstr);
        params.put("CallCode", "0003000");
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
                            Intent intent = new Intent(SignupContentActivity.this, SignupEndActivity.class);
                            startActivity(intent);
                        } else {
                            new SweetAlertDialog(SignupContentActivity.this, SweetAlertDialog.WARNING_TYPE)
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

    private void initView() {
        textView = (TextView) findViewById(R.id.tet_signcontentphone_activity);
        button = (Button) findViewById(R.id.btn_singupcontent_activity);
        edipassword = (EditText) findViewById(R.id.edi_passwordsupc_activity);
        edipasswordtwo = (EditText) findViewById(R.id.edi_passwordtwosupc_activity);
        imvback = (TextView) findViewById(R.id.tv_goods_comsume_back);
        textView.setText(phone);
    }

    private void initData() {
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        yzmstr = intent.getStringExtra("yzm");
        sjyzm = intent.getStringExtra("sjyzm");
    }
}
