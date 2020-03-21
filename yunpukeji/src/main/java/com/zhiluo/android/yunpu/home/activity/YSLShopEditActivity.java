package com.zhiluo.android.yunpu.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.home.jsonbean.AllShopListBean;
import com.zhiluo.android.yunpu.home.jsonbean.ShopInfoDataBean;
import com.zhiluo.android.yunpu.home.jsonbean.SingleShopInfoJsonBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.bean.Indurybean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ShowSweetDialogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${YSL} on 2018-04-09.
 */

public class YSLShopEditActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mBack, mSave;
//    private Spinner hangye;
    private EditText mContent;
    private  int pos =0;
    private List<AllShopListBean.DataBean> shoplisst = new ArrayList<>();
    private String gid;
    private String state;
    private String mStorePictureUrl;
    private Intent mIntent = getIntent();
    private Bundle mBundle;
    private ShopInfoDataBean mShopInfoBean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_shopedit);
        iniViews();
        initDatas();
        lisenter();
    }

    private void lisenter() {

//        hangye.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                HomeActivity.induryGid = indurybean.getData().get(position).getGID()+"";
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    private void initDatas() {
        mBundle = getIntent().getBundleExtra("bundle1");
        mShopInfoBean = (ShopInfoDataBean) mBundle.getSerializable("shopinfo");
        if (mShopInfoBean!=null){
            mContent.setText(getIntent().getStringExtra("shopname"));
            mContent.setSelection(mContent.getText().toString().length());
        }
//        getIduryType();

    }

    private void iniViews() {
        mBack = findViewById(R.id.tv_back);
        mContent = findViewById(R.id.tv_edit);
        mSave = findViewById(R.id.tv_save);
//        hangye = findViewById(R.id.ed_edit_main_type);
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
        params.put("SM_Name", mContent.getText().toString());
        params.put("SM_Phone", getIntent().getStringExtra("linktel"));
        params.put("SM_Addr", "");
        params.put("SM_IndustryType", HomeActivity.induryGid);
        MyTextHttpResponseHandler responseHandler = new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                //将修改好的店铺名称保存到本地，以便于回到主界面后店铺名称不能及时更新
              /*  SharedPreferences.Editor editor = getSharedPreferences("setting", 0).edit();
                editor.putString("mytitle", etshopname.getText().toString());
                editor.commit();*/
                Intent intent = new Intent();
                intent.putExtra("cotent", mContent.getText().toString());
                setResult(10, intent);
                ShowSweetDialogUtil.showSuccessDialog(YSLShopEditActivity.this, "提交成功！", true);
            }

            @Override
            public void onFailure(String msg) {
                ShowSweetDialogUtil.showWarningDialog(YSLShopEditActivity.this, msg);
            }
        };
        responseHandler.setDialog(YSLShopEditActivity.this, "正在提交...", false);
        client.post(HttpAPI.API().EDITORSHOP, params, responseHandler);
    }

//    private void getIduryType(){
//        RequestParams params = new RequestParams();
//        CallBack callBack = new CallBack() {
//            @Override
//            public void onSuccess(String responseString, Gson gson) {
//                indurybean = CommonFun.JsonToObj(responseString,Indurybean.class);
//                for (int i = 0;i<indurybean.getData().size();i++){
//                    indurylist.add(indurybean.getData().get(i).getIT_Name());
//                    if (HomeActivity.induryGid !=null &&HomeActivity.induryGid.equals(indurybean.getData().get(i).getGID()+"")){
//                        HomeActivity.induryGid = indurybean.getData().get(i).getGID()+"";
//                        pos = i;
//                    }
//                }
//                mSpinnerAdapter = new ArrayAdapter<String>(YSLShopEditActivity.this,
//                        R.layout.item_spinner, R.id.tv_item_spinner, indurylist);
//                hangye.setAdapter(mSpinnerAdapter);
//                hangye.setSelection(pos);
//
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                CustomToast.makeText(YSLShopEditActivity.this, msg, Toast.LENGTH_SHORT);
//            }
//        };
//        callBack.setLoadingAnimation(this,"请稍后",false);
//        HttpHelper.post(this, MyApplication.BASE_URL+"Shops/GetIndustryList",params,callBack);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                YSLShopEditActivity.this.finish();
                break;
            case R.id.tv_save:
                if (TextUtils.isEmpty(mContent.getText().toString()) || mContent.getText().toString().equals("")) {
                    CustomToast.makeText(this, "输入内容不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    postSave();
                }
                break;
        }
    }
}
