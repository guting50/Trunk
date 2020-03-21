package com.zhiluo.android.yunpu.goods.manager.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.SuccessBean;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 新增商品主分类
 */
public class AddGoodsOriginalCategoryActivity extends BaseActivity {

    private TextView tv_back_activity;
    private EditText et;
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_originalcategory);
        initview();
        clicklisenler();
    }

    private void clicklisenler() {
        tv_back_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String k = et.getText().toString();
                if (k == null || k.equals("")) {
                    new SweetAlertDialog(AddGoodsOriginalCategoryActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("请输入有效的分类名称！")
                            .setConfirmText("了解")
                            .show();
//                    CustomToast.makeText(AddGoodsOriginalCategoryActivity.this, "请输入有效的分类名称...", Toast.LENGTH_SHORT).show();
                } else {
                    AddTybe(k);
                }

            }
        });
    }

    private void AddTybe(String ptname) {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("PT_Remark", "");
        params.put("PT_SynType", 0);
        params.put("PT_Name", ptname);
        params.put("PT_Parent", "");
        client.post(HttpAPI.API().ADDTYPE, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SuccessBean bean = CommonFun.JsonToObj(responseString, SuccessBean.class);
                if (bean.isSuccess()) {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddGoodsOriginalCategoryActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("新增成功！");
                    sweetAlertDialog.setContentText("新增主分类成功，请刷新列表！");
                    sweetAlertDialog.setConfirmText("确认");
                    sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            finish();

                        }
                    });
                    sweetAlertDialog.show();
//                    CacheData.deleteDir();//删除缓存
                } else {
                    new SweetAlertDialog(AddGoodsOriginalCategoryActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText(bean.getMsg() + "!")
                            .setConfirmText("了解")
                            .show();
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });


    }


    private void initview() {
        tv_back_activity = (TextView) findViewById(R.id.tv_back_activity);
        et = (EditText) findViewById(R.id.et_addbig);
        btn = (Button) findViewById(R.id.btn_addbig);
    }
}
