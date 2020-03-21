package com.zhiluo.android.yunpu.goods.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class AddProviderActivity extends BaseActivity {
    @Bind(R.id.et_add_provider_brand)
    EditText etBrand;
    @Bind(R.id.et_add_provider_remark)
    EditText etRemark;
    private EditText mEtProvider, mEtLinkMan, mEtLinkPhone, mEtZuoji, mEtQQ, mEtWeiXin, mEtAddress;
    private String mProvider, mLinkMan, mLinkPhone;
    private SweetAlertDialog mAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_provider);
        ButterKnife.bind(this);
        initView();
        initVariable();
        loadData();
        setListener();
    }

    private void initView() {
        mEtProvider = (EditText) findViewById(R.id.et_add_provider_name);
        mEtLinkMan = (EditText) findViewById(R.id.et_add_provider_linkman);
        mEtLinkPhone = (EditText) findViewById(R.id.et_add_provider_link_phone);
        mEtZuoji = (EditText) findViewById(R.id.et_add_provider_link_zuoji);
        mEtQQ = (EditText) findViewById(R.id.et_add_provider_qq);
        mEtWeiXin = (EditText) findViewById(R.id.et_add_provider_weixin);
        mEtAddress = (EditText) findViewById(R.id.et_add_provider_address);
    }

    private void initVariable() {

    }

    private void loadData() {

    }

    private void setListener() {
        findViewById(R.id.tv_add_provider_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.img_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mProvider != null && !"".equals(mProvider)) {
                    if (mLinkMan != null && !"".equals(mLinkMan)) {
//                        if (mLinkPhone != null && !"".equals(mLinkPhone)) {
                            addProvider();
//                        } else {
//                            CustomToast.makeText(AddProviderActivity.this, "请输入联系电话", Toast.LENGTH_SHORT).show();
//                        }
                    } else {
                        CustomToast.makeText(AddProviderActivity.this, "请输入联系人", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    CustomToast.makeText(AddProviderActivity.this, "请输入供应商", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mEtProvider.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mProvider = editable.toString();
            }
        });
        mEtLinkMan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mLinkMan = editable.toString();
            }
        });
        mEtLinkPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mLinkPhone = editable.toString();
            }
        });
    }

    private void addProvider() {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        RequestParams params = new RequestParams();
        params.put("SL_Name", mProvider);
        params.put("SL_DefaultName", mLinkMan);
        params.put("SL_DefaultPhone", mLinkPhone);
        params.put("SL_FixedPhone", mEtZuoji.getText().toString());
        params.put("SL_QQ", mEtQQ.getText().toString());
        params.put("SL_WeChat", mEtWeiXin.getText().toString());
        params.put("SL_Addr", mEtAddress.getText().toString());
        params.put("SL_Brand", etBrand.getText().toString());//品牌
        params.put("SL_Remark", etRemark.getText().toString());
        client.setCookieStore(cookieStore);
        MyTextHttpResponseHandler responseHandler = new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mAlertDialog = new SweetAlertDialog(AddProviderActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mAlertDialog.setTitleText("成功");
                mAlertDialog.setContentText("添加供应商成功！");
                mAlertDialog.setConfirmText("确认");
                mAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent();
                        setResult(10, intent);
                        finish();
//                        mAlertDialog.dismiss();
//                        mEtProvider.setText("");
//                        mEtLinkMan.setText("");
//                        mEtLinkPhone.setText("");
//                        mEtAddress.setText("");
//                        mEtQQ.setText("");
//                        mEtWeiXin.setText("");
//                        mEtZuoji.setText("");
                    }
                });
                mAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddProviderActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        responseHandler.setDialog(AddProviderActivity.this, "正在提交...", false);
        client.post(HttpAPI.API().ADD_PROVIDER, params, responseHandler);
    }
}
