package com.zhiluo.android.yunpu.home.activity;

import android.content.Intent;
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
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.home.jsonbean.AllShopListBean;
import com.zhiluo.android.yunpu.home.jsonbean.ShopInfoDataBean;
import com.zhiluo.android.yunpu.home.jsonbean.SingleShopInfoJsonBean;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.RegexUtil;
import com.zhiluo.android.yunpu.utils.ShowSweetDialogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by ${YSL} on 2018-04-09.
 */

public class LinkTelsetActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mBack, mSave;
    private EditText mContent;
    private List<AllShopListBean.DataBean> shoplisst = new ArrayList<>();
    private String gid;
    private String state;
    private String mStorePictureUrl;
    private Intent mIntent = getIntent();
    private Bundle mBundle;
    private ShopInfoDataBean mShopInfoBean;
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((19[0-8])|(17[0-9])|(14[0-9])|(13[0-9])|(15[0-9])|(18[0-9]))\\d{8}$";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_linktel_set);
        iniViews();
        initDatas();
    }

    private void initDatas() {
        mBundle = getIntent().getBundleExtra("bundle1");
        mShopInfoBean = (ShopInfoDataBean) mBundle.getSerializable("shopinfo");
        if (mShopInfoBean!=null){
            mContent.setText(getIntent().getStringExtra("linktel"));
            mContent.setSelection(mContent.getText().toString().length());
        }

    }

    private void iniViews() {
        mBack = findViewById(R.id.tv_back);
        mContent = findViewById(R.id.tv_edit);
        mSave = findViewById(R.id.tv_save);
        mBack.setOnClickListener(this);
        mSave.setOnClickListener(this);
    }

    /**
     * 提交数据保存
     */
    private void postSave() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("GID", mShopInfoBean.getData().getGID());
//        params.put("SM_State", mShopInfoBean.getData().getSM_State());
//        params.put("SM_Province", mShopInfoBean.getData().getSM_Province());
//        params.put("SM_City", mShopInfoBean.getData().getSM_City());
//        params.put("SM_Disctrict",mShopInfoBean.getData().getSM_Disctrict());

        params.put("SM_Contacter", getIntent().getStringExtra("linkname"));
        params.put("SM_Name", getIntent().getStringExtra("shopname"));
        params.put("SM_Phone", mContent.getText().toString());
        params.put("SM_Addr", "");
        params.put("SM_IndustryType", HomeActivity.induryGid);
        //params.put("SM_Picture", mStorePictureUrl);
        MyTextHttpResponseHandler responseHandler = new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Intent intent = new Intent();
                intent.putExtra("cotent", mContent.getText().toString());
                setResult(30, intent);
                ShowSweetDialogUtil.showSuccessDialog(LinkTelsetActivity.this, "提交成功！", true);
            }

            @Override
            public void onFailure(String msg) {
                ShowSweetDialogUtil.showWarningDialog(LinkTelsetActivity.this, msg);
            }
        };
        responseHandler.setDialog(LinkTelsetActivity.this, "正在提交...", false);
        client.post(HttpAPI.API().EDITORSHOP, params, responseHandler);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                LinkTelsetActivity.this.finish();
                break;
            case R.id.tv_save:
                if (TextUtils.isEmpty(mContent.getText().toString()) || mContent.getText().toString().equals("")) {
                    CustomToast.makeText(this, "输入内容不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    if (!RegexUtil.isTelPhoneNumber(mContent.getText().toString())) {
                        CustomToast.makeText(this, "手机号码格式不正确！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    postSave();
                }
                break;
        }
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
}
