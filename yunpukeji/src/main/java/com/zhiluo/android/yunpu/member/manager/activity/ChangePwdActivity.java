package com.zhiluo.android.yunpu.member.manager.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.UserBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.member.manager.bean.YSLMemberInfoBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

/**
 * Created by ${YSL} on 2018-04-04.
 */

public class ChangePwdActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mOldPwd, mNewPwdOne, mNewPwdTwo;
    private TextView mCommit, mBack;
    private String StringmOldPwd, StringmNewPwdOne, StringmNewPwdTwo;
    private Bundle mBundle;
    private YSLMemberInfoBean.DataBean.DataListBean mVipInfo;
    private String type;
    private UserBean mUserBean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_change_pwd);
        initViews();
        initDatas();
    }

    private void initDatas() {
        type = getIntent().getStringExtra("TYPE");
        if (type.equals("vip")){
            mBundle = getIntent().getBundleExtra("bundle");
            mVipInfo = (YSLMemberInfoBean.DataBean.DataListBean) mBundle.getSerializable("YSL_VIP_INFO");
        }else if (type.equals("user")){
            mUserBean = (UserBean) getIntent().getSerializableExtra("User");
        }

    }

    private void initViews() {
        mCommit = findViewById(R.id.tv_ok);
        mOldPwd = findViewById(R.id.ed_old_pwd);
        mNewPwdOne = findViewById(R.id.ed_new_pwd_one);
        mNewPwdTwo = findViewById(R.id.ed_new_pwd_two);
        mBack = findViewById(R.id.tv_back);
        mCommit.setOnClickListener(this);
        mBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ok:
                StringmOldPwd = mOldPwd.getText().toString();
                StringmNewPwdOne = mNewPwdOne.getText().toString();
                StringmNewPwdTwo = mNewPwdTwo.getText().toString();
//                if (!TextUtils.isEmpty(mOldPwd.getText())) {
                    if (!TextUtils.isEmpty(mNewPwdOne.getText())) {
                        if (StringmNewPwdOne.equals(StringmNewPwdTwo)) {
                            if (mNewPwdOne.length() > 5) {
                                alterpw(StringmOldPwd, StringmNewPwdTwo);
                            } else {
                                CustomToast.makeText(ChangePwdActivity.this, "密码长度不能小于6", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            CustomToast.makeText(ChangePwdActivity.this, "两次输入密码不一致", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        CustomToast.makeText(ChangePwdActivity.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    }
//                } else {
//                    CustomToast.makeText(ChangePwdActivity.this, "请输入原密码", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.tv_back:
                ChangePwdActivity.this.finish();
                break;
        }
    }

    /**
     * 请求换新密码
     */
    private void alterpw(String oldpwd, String newpwd) {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Adduserbean adduserbean = CommonFun.JsonToObj(responseString, Adduserbean.class);
                boolean isture = adduserbean.isSuccess();
                if (isture) {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ChangePwdActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("提示");
                    sweetAlertDialog.setContentText("密码修改成功！");
                    sweetAlertDialog.setConfirmText("确认");
                    sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            finish();

                        }
                    });
                    sweetAlertDialog.show();
                    //                            CustomToast.makeText(MemberDetailsActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    //                            finish();
                } else {
                    new SweetAlertDialog(ChangePwdActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText(adduserbean.getMsg() + "!")
                            .setConfirmText("了解")
                            .show();
                    //                            CustomToast.makeText(MemberDetailsActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        } ;
        RequestParams params = new RequestParams();
        if (type.equals("vip")){
            params.put("GID", mVipInfo.getGID());
            params.put("VCH_Card", mVipInfo.getVCH_Card());
            params.put("VCH_Pwd", newpwd);
            HttpHelper.post(this,HttpAPI.API().ALTERPWD,params,callBack);
        }else if (type.equals("user")){
            params.put("GID", mUserBean.getGID());
            params.put("ResetPwd", newpwd);
            HttpHelper.post(this,"UserManager/ResetPwd",params,callBack);
        }


//        client.post(HttpAPI.API().ALTERPWD, params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                if (statusCode == 200) {
//                    String resurt = null;
//                    try {
//                        resurt = new String(responseBody, "UTF-8");
//                        Gson gson = new Gson();
//                        Adduserbean adduserbean = CommonFun.JsonToObj(resurt, Adduserbean.class);
//                        boolean isture = adduserbean.isSuccess();
//                        if (isture) {
//                            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(ChangePwdActivity.this, SweetAlertDialog.SUCCESS_TYPE);
//                            sweetAlertDialog.setTitleText("提示");
//                            sweetAlertDialog.setContentText("密码修改成功！");
//                            sweetAlertDialog.setConfirmText("确认");
//                            sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                                @Override
//                                public void onDismiss(DialogInterface dialogInterface) {
//                                    finish();
//
//                                }
//                            });
//                            sweetAlertDialog.show();
//                            //                            CustomToast.makeText(MemberDetailsActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
//                            //                            finish();
//                        } else {
//                            new SweetAlertDialog(ChangePwdActivity.this, SweetAlertDialog.WARNING_TYPE)
//                                    .setTitleText("提示")
//                                    .setContentText(adduserbean.getMsg() + "!")
//                                    .setConfirmText("了解")
//                                    .show();
//                            //                            CustomToast.makeText(MemberDetailsActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//            }
//        });
    }
}
