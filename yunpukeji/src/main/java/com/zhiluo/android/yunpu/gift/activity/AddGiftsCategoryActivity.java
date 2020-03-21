package com.zhiluo.android.yunpu.gift.activity;

import android.content.DialogInterface;
import android.os.Bundle;
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
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

/**
 * 新增礼品分类
 */
public class AddGiftsCategoryActivity extends BaseActivity
{

    private TextView tv_back_activity;
    private EditText ettype, etabs;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gift_category);
        intview();
        clicklisenler();

    }

    private void clicklisenler()
    {
        tv_back_activity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String k = ettype.getText().toString();
                String abstrct = etabs.getText().toString();
                if (k == null || k.equals(""))
                {
                    new SweetAlertDialog(AddGiftsCategoryActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("请输入有效的分类名称！")
                            .setConfirmText("了解")
                            .show();
//                    CustomToast.makeText(AddGiftsCategoryActivity.this, "请输入有效的分类名称...", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    AddTybe(k, abstrct);
                }

            }
        });
    }

    private void AddTybe(String k, String abstrct)
    {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("GT_Name", k);
        params.put("GT_Remark", abstrct);
        client.post(HttpAPI.API().GIFTTYPELIST, params, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                if (statusCode == 200)
                {
                    String resurt = null;
                    try
                    {
                        resurt = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        Adduserbean adduserbean = CommonFun.JsonToObj(resurt, Adduserbean.class);
                        boolean isture = adduserbean.isSuccess();
                        if (isture)
                        {

                            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddGiftsCategoryActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                            sweetAlertDialog.setTitleText("新增成功！");
                            sweetAlertDialog.setContentText("新增礼品分类成功，请下拉刷新列表！");
                            sweetAlertDialog.setConfirmText("确认");
                            sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener()
                            {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface)
                                {
                                    finish();

                                }
                            });
                            sweetAlertDialog.show();
//                            CustomToast.makeText(AddGiftsCategoryActivity.this, "添加成功,请下拉刷新列表", Toast.LENGTH_SHORT).show();
//                            finish();
                        }
                        else
                        {
                            new SweetAlertDialog(AddGiftsCategoryActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(adduserbean.getMsg() + "!")
                                    .setConfirmText("了解")
                                    .show();
//                            CustomToast.makeText(AddGiftsCategoryActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (UnsupportedEncodingException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {
            }
        });
    }

    private void intview()
    {
        etabs = (EditText) findViewById(R.id.et_addabstrcact);
        ettype = (EditText) findViewById(R.id.et_addbig);
        tv_back_activity = (TextView) findViewById(R.id.tv_back_activity);
        btn = (Button) findViewById(R.id.btn_addtype);
    }
}
