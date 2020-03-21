package com.zhiluo.android.yunpu.member.consume.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date: 2017/11/28 17:48
 * Author: 罗咏哲
 * Description: 密码验证
 * Version:
 */
public class VerifyPasswordActivity extends Activity {

    @Bind(R.id.et_password_input)
    EditText etPasswordInput;
    @Bind(R.id.tv_password_confirm)
    TextView tvPasswordConfirm;
    private String mMemberCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_password);
        ButterKnife.bind(this);
        mMemberCard = getIntent().getStringExtra("Card");
        tvPasswordConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMemberCard != null && !"".equals(mMemberCard) && !"00000".equals(mMemberCard)) {
                    if (!TextUtils.isEmpty(etPasswordInput.getText())) {
                        verify();
                    } else {
                        CustomToast.makeText(VerifyPasswordActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    CustomToast.makeText(VerifyPasswordActivity.this, "会员卡号不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void verify() {
        RequestParams params = new RequestParams();
        params.put("VCH_Card", mMemberCard);
        params.put("VCH_Pwd", etPasswordInput.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Intent intent=new Intent();
                intent.putExtra("result",true);
                setResult(200,intent);
                finish();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(VerifyPasswordActivity.this, "支付密码错误！", Toast.LENGTH_SHORT).show();
                finish();
            }
        };
        callBack.setLoadingAnimation(this, "正在验证密码...", false);
        HttpHelper.post(this, HttpAPI.API().PASSWORDVERIFY, params, callBack);
    }
}
