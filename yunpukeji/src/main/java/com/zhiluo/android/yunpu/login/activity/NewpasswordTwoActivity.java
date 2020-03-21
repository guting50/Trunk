package com.zhiluo.android.yunpu.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

/**
 * 找回密码界面2，用于提交修改的密码
 */
public class NewpasswordTwoActivity extends BaseActivity {
    private Button button;
    private TextView imvback;
    private EditText password;
    private EditText passwordtwo;
    private String Account;
    private SweetAlertDialog mSweetAlertDialog;//提示框

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newpassword_two);
        Account = getIntent().getStringExtra("Account");
        initView();
        initListenin();
    }

    private void initListenin() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(password.getText()) || password.getText().length() <6 ){
                    CustomToast.makeText(NewpasswordTwoActivity.this, "请输入6-20位密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.getText().toString().equals(passwordtwo.getText().toString())) {
                    initPostdata();
                } else {
                    new SweetAlertDialog(NewpasswordTwoActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("两次密码不一致！")
                            .setConfirmText("了解")
                            .show();
//                    CustomToast.makeText(NewpasswordTwoActivity.this, "两次密码不一致或密码为空", Toast.LENGTH_SHORT).show();
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
        params.put("NewPwd", password.getText().toString());
        params.put("OkPwd", passwordtwo.getText().toString());
        params.put("Account",Account );
        client.post(HttpAPI.API().SETNEWPWD, params, new AsyncHttpResponseHandler() {
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

                            toastDialog("修改密码成功！");
//                            Intent intent = new Intent(NewpasswordTwoActivity.this, NewpasswordEndActivity.class);
//                            startActivity(intent);
                        } else {
                            new SweetAlertDialog(NewpasswordTwoActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(adduserbean.getMsg() + "!")
                                    .setConfirmText("了解")
                                    .show();
//                            CustomToast.makeText(NewpasswordTwoActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
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

    /**
     * 提示弹出
     *
     * @param msg
     */
    private void toastDialog(String msg) {
        mSweetAlertDialog = new SweetAlertDialog(NewpasswordTwoActivity.this, SweetAlertDialog.SUCCESS_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCancelable(false);
        mSweetAlertDialog.setConfirmText("确定");
        mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mSweetAlertDialog.dismiss();
                Intent intent = new Intent(NewpasswordTwoActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        mSweetAlertDialog.show();
    }

    private void initView() {
        button = (Button) findViewById(R.id.btn_newpasswordtwonext_activity);
        imvback =  findViewById(R.id.imv_newpassworedtwoback_activity);
        password = (EditText) findViewById(R.id.edi_passwordtwopassword_activity);
        passwordtwo = (EditText) findViewById(R.id.edi_passwordtwopasswordtwo_activity);
    }
}
