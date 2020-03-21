package com.zhiluo.android.yunpu.goods.manager.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.bean.ProviderBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Date: 2017/9/29 9:51
 * Author: 罗咏哲
 * Description: 供应商详情
 * Version:
 */
public class ModifyProviderActivity extends BaseActivity {
    @Bind(R.id.btn_provider_restore)
    Button btnProviderRestore;
    @Bind(R.id.et_add_provider_brand)
    EditText etBrand;
    @Bind(R.id.et_add_provider_remark)
    EditText etRemark;
    @Bind(R.id.provider_title)
    TextView providerTitle;
    private EditText mEtProvider, mEtLinkMan, mEtLinkPhone, mEtZuoji, mEtQQ, mEtWeiXin, mEtAddress;
    private String mProvider, mLinkMan, mLinkPhone, mZuoJi, mQQ, mWeiXin, mAddr, mBrand, mRemark, mProvince, mCity, mDisctrct;
    private SweetAlertDialog mAlertDialog;
    private String mGid;
    private ProviderBean.DataBean.DataListBean mBean;
    private PopupWindow mPopupWindow;
    private SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_provider);
        ButterKnife.bind(this);
        initView();
        initVariable();
        loadData();
        setListener();
    }

    private void initView() {
        mEtProvider = (EditText) findViewById(R.id.et_modify_provider_name);
        mEtProvider.requestFocus();
        mEtLinkMan = (EditText) findViewById(R.id.et_modify_provider_linkman);
        mEtLinkPhone = (EditText) findViewById(R.id.et_modify_provider_link_phone);
        mEtZuoji = (EditText) findViewById(R.id.et_add_provider_link_zuoji);
        mEtQQ = (EditText) findViewById(R.id.et_add_provider_qq);
        mEtWeiXin = (EditText) findViewById(R.id.et_add_provider_weixin);
        mEtAddress = (EditText) findViewById(R.id.et_add_provider_address);
    }

    private void reset() {
        providerTitle.setText("编辑供应商");
        mEtProvider.setEnabled(true);
        mEtLinkMan.setEnabled(true);
        mEtLinkPhone.setEnabled(true);
        mEtZuoji.setEnabled(true);
        mEtQQ.setEnabled(true);
        mEtWeiXin.setEnabled(true);
        mEtAddress.setEnabled(true);
        etRemark.setEnabled(true);
        etBrand.setEnabled(true);
        btnProviderRestore.setVisibility(View.VISIBLE);

    }

    private void initVariable() {
        mBean = (ProviderBean.DataBean.DataListBean) getIntent().getSerializableExtra("provider");
        mGid = mBean.getGID();
        mEtProvider.setText(mBean.getSL_Name() == null ? "" : mBean.getSL_Name());
        mEtProvider.setSelection(mEtProvider.getText().toString().length());
        mEtLinkMan.setText(mBean.getSL_DefaultName() == null ? "" : mBean.getSL_DefaultName());
        mEtLinkPhone.setText(mBean.getSL_DefaultPhone() == null ? "" : mBean.getSL_DefaultPhone());
        mEtZuoji.setText(mBean.getSL_FixedPhone() == null ? "" : mBean.getSL_FixedPhone());
        mEtQQ.setText(mBean.getSL_QQ() == null ? "" : mBean.getSL_QQ());
        mEtWeiXin.setText(mBean.getSL_WeChat() == null ? "" : mBean.getSL_WeChat());
        mEtAddress.setText(mBean.getSL_Addr() == null ? "" : mBean.getSL_Addr());
        etBrand.setText(mBean.getSL_Brand() == null ? "" : mBean.getSL_Brand());
        etRemark.setText(mBean.getSL_Remark() == null ? "" : mBean.getSL_Remark());


        mProvider = mBean.getSL_Name();
        mLinkMan = mBean.getSL_DefaultName();
        mLinkPhone = mBean.getSL_DefaultPhone();
        mZuoJi = mBean.getSL_FixedPhone();
        mQQ = mBean.getSL_QQ();
        mWeiXin = mBean.getSL_WeChat();
        mAddr = mBean.getSL_Addr();
        mBrand = mBean.getSL_Brand();
        mRemark = mBean.getSL_Remark();
        mProvince = mBean.getSL_Province();
        mCity = mBean.getSL_City();
        mDisctrct = mBean.getSL_Disctrict();


    }

    private void loadData() {

    }

    private void showPopWindow(View archon) {
        mPopupWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.pop_ysl_edit_vip_level, null);
        RelativeLayout mEdit = view.findViewById(R.id.r_edit);
        RelativeLayout mDelet = view.findViewById(R.id.r_delet);
        mPopupWindow.setContentView(view);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        mPopupWindow.setWidth(width / 2);
        mPopupWindow.setHeight(height / 5);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAsDropDown(archon);
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //编辑
                mPopupWindow.dismiss();
                reset();

            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                //删除
                mSweetAlertDialog = new SweetAlertDialog(ModifyProviderActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("删除供应商");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setCancelText("取消");
                mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                    }
                });
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        delete();
                        mSweetAlertDialog.dismiss();
                    }
                });
                mSweetAlertDialog.show();
            }
        });
    }

    private void comfirmParams() {
        if (mProvider != null && !"".equals(mProvider)) {
            if (mLinkMan != null && !"".equals(mLinkMan)) {
//                if (mLinkPhone != null && !"".equals(mLinkPhone)) {
                    mBrand = etBrand.getText().toString();
                    mRemark = etRemark.getText().toString();
                    addProvider();
//                } else {
//                    CustomToast.makeText(ModifyProviderActivity.this, "请输入联系电话", Toast.LENGTH_SHORT).show();
//                }
            } else {
                CustomToast.makeText(ModifyProviderActivity.this, "请输入联系人", Toast.LENGTH_SHORT).show();
            }
        } else {
            CustomToast.makeText(ModifyProviderActivity.this, "请输入供应商", Toast.LENGTH_SHORT).show();
        }
    }

    private void setListener() {
        findViewById(R.id.tv_modify_provider_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.img_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopWindow(view);

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
        mEtZuoji.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mZuoJi = s.toString();
            }
        });
        mEtQQ.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mQQ = s.toString();
            }
        });
        mEtWeiXin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mWeiXin = s.toString();
            }
        });
        mEtAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mAddr = s.toString();
            }
        });

        btnProviderRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comfirmParams();
            }
        });
    }


    private void delete() {
        RequestParams params = new RequestParams();
        params.put("GID", mGid);
        HttpHelper.post(this, "SupplierManager/DelSupplier", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {

                mAlertDialog = new SweetAlertDialog(ModifyProviderActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mAlertDialog.setTitleText("成功");
                mAlertDialog.setContentText("删除供应商成功！");
                mAlertDialog.setConfirmText("确认");
                mAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mAlertDialog.dismiss();
                        Intent intent = new Intent();
                        setResult(20, intent);
                        ModifyProviderActivity.this.finish();
                    }
                });
                mAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ModifyProviderActivity.this, msg, Toast.LENGTH_SHORT).show();
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
        params.put("SL_FixedPhone", mZuoJi);
        params.put("SL_QQ", mQQ);
        params.put("SL_WeChat", mWeiXin);
        params.put("SL_Addr", mAddr);
        params.put("SL_Brand", mBrand);//品牌
        params.put("SL_Remark", mRemark);
        params.put("SL_Province", mProvince);//省
        params.put("SL_City", mCity);//市
        params.put("SL_Disctrict", mDisctrct);//区
        params.put("GID", mGid);
        client.setCookieStore(cookieStore);
        MyTextHttpResponseHandler responseHandler = new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mAlertDialog = new SweetAlertDialog(ModifyProviderActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mAlertDialog.setTitleText("成功");
                mAlertDialog.setContentText("修改供应商成功！");
                mAlertDialog.setConfirmText("确认");
                mAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mAlertDialog.dismiss();
                        Intent intent = new Intent();
                        setResult(20, intent);
                        ModifyProviderActivity.this.finish();
                    }
                });
                mAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ModifyProviderActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        responseHandler.setDialog(ModifyProviderActivity.this, "正在提交...", false);
        client.post(HttpAPI.API().MODIFY_PROVIDER, params, responseHandler);
    }
}
