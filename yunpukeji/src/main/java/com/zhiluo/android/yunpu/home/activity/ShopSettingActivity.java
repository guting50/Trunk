package com.zhiluo.android.yunpu.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.UserNumEntity;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.home.jsonbean.ShopInfoDataBean;
import com.zhiluo.android.yunpu.home.jsonbean.SingleShopInfoJsonBean;
import com.zhiluo.android.yunpu.home.jsonbean.VipMemberNumBean;
import com.zhiluo.android.yunpu.home.jsonbean.WelcomeData;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.sms.jsonbean.SMSNumBean;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.bean.Indurybean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.ShowSweetDialogUtil;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import java.io.UnsupportedEncodingException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-04-09.
 */

public class ShopSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private SingleShopInfoJsonBean mShopInfoBean;
    private ShopInfoDataBean mAllShopinfoBean;
    private Bundle mBundle;
    private ImageView mLogo;
    private RelativeLayout mRShopName, mRLinkName, mRLinkTel;
    private String mStoreGID;//店铺GID
    private TextView mTShopName, mTLinkName, mTLinkTel, mTShopType, mTVipTotalCount, mTGoodsTotal, mTUserTotalCount, mTShortMessageStock, mTLimitTime,mInsdutry;
    private String storeGid;
    private TextView mBack;
    private Intent mIntent;
    private Indurybean indurybean;
    private String imgurl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_shopsetting);
        initViews();
        initDatas();
        postsmsnum();
    }

    private void attachViews() {

        mTShopName.setText(mAllShopinfoBean.getData().getShopName());
        mTLinkName.setText(mAllShopinfoBean.getData().getShopContact());
        mTLinkTel.setText(mAllShopinfoBean.getData().getShopTel());
        mTShopType.setText(mAllShopinfoBean.getData().getShopType());
        try {
            imgurl = mAllShopinfoBean.getData().getShopImg();
            if (!imgurl.contains("http")) {
                imgurl = MyApplication.IMAGE_URL + mAllShopinfoBean.getData().getShopImg();
            }
            Glide.with(this).load(imgurl).error(R.drawable.defalut_store).into(mLogo);
        } catch (Exception e) {
            Glide.with(this).load(R.drawable.defalut_store).into(mLogo);
        }

        String endTime = mAllShopinfoBean.getData().getShopOverTime();
        if (endTime != null) {
            endTime = endTime.substring(0, 10);
        }
        mTLimitTime.setText(endTime);
        mTVipTotalCount.setText(mAllShopinfoBean.getData().getShopMbers());
        mTGoodsTotal.setText(mAllShopinfoBean.getData().getShopGoods());
        mTUserTotalCount.setText(mAllShopinfoBean.getData().getShopUsers());


    }

    //获取短信条数
    private void postsmsnum() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        client.post(HttpAPI.API().SMSNUM, params, new AsyncHttpResponseHandler() {
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
                            int i = adduserbean.getMsg().length();
                            if (i == 4) {
                                SMSNumBean smsNumBean = CommonFun.JsonToObj(resurt, SMSNumBean.class);
                                mTShortMessageStock.setText(smsNumBean.getData().getUStorage() + "");
                            } else {
                                mTShortMessageStock.setText("" + 0);
                            }
                        } else {
                          CustomToast.makeText(ShopSettingActivity.this,"获取短信库存失败，请前往PC端设置库存开关",Toast.LENGTH_SHORT).show();
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
     * 获取店铺信息
     */
    private void getdata() {
        RequestParams params = new RequestParams();
        HttpHelper.post(ShopSettingActivity.this, HttpAPI.API().GET_SHOP_INFO, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mAllShopinfoBean = CommonFun.JsonToObj(responseString, ShopInfoDataBean.class);
                attachViews();
            }

            @Override
            public void onFailure(String msg) {
//                mTUserTotalCount.setText("1/1");
            }
        });
    }


    private void initDatas() {

        storeGid = (String) uSharedPreferencesUtiles.get(ShopSettingActivity.this, "StoreGid", "");
        mBundle = getIntent().getBundleExtra("bundle");
        mShopInfoBean = (SingleShopInfoJsonBean) mBundle.getSerializable("shopinfo");

        loadDatas();
        getdata();
        getIduryType();
    }


    /**
     * 加载数据
     */
    private void loadDatas() {
        RequestParams params = new RequestParams();
        mStoreGID = (String) uSharedPreferencesUtiles.get(this, "StoreGid", "");
        params.put("GID", mStoreGID);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mShopInfoBean = CommonFun.JsonToObj(responseString, SingleShopInfoJsonBean.class);
                Le("ss----"+responseString);
            }

            @Override
            public void onFailure(String msg) {
                mShopInfoBean = new SingleShopInfoJsonBean();
                Le("ff----"+msg);

            }
        };
        HttpHelper.post(this,  HttpAPI.API().SINGLE_SHOP_INFO, params, callBack);
    }

        private void getIduryType(){
        RequestParams params = new RequestParams();
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                indurybean = CommonFun.JsonToObj(responseString,Indurybean.class);
                for (int i = 0;i<indurybean.getData().size();i++){
                    if (HomeActivity.induryGid !=null &&HomeActivity.induryGid.equals(indurybean.getData().get(i).getGID()+"")){
                        HomeActivity.induryGid = indurybean.getData().get(i).getGID()+"";
                        mInsdutry.setText(indurybean.getData().get(i).getIT_Name()+"");
                    }
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ShopSettingActivity.this, msg, Toast.LENGTH_SHORT);
            }
        };
        callBack.setLoadingAnimation(this,"请稍后",false);
        HttpHelper.post(this, MyApplication.BASE_URL+"Shops/GetIndustryList",params,callBack);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                ShopSettingActivity.this.finish();
                break;
            case R.id.r_shop_name:
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("shopinfo", mAllShopinfoBean);
                mIntent = new Intent(ShopSettingActivity.this, YSLShopEditActivity.class);
                mIntent.putExtra("shopname", mTShopName.getText().toString());
                mIntent.putExtra("linkname", mTLinkName.getText().toString());
                mIntent.putExtra("linktel", mTLinkTel.getText().toString());
                mIntent.putExtra("bundle1", bundle1);
                startActivityForResult(mIntent, 1);
                break;
            case R.id.r_link_name:
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("shopinfo", mAllShopinfoBean);
                mIntent = new Intent(ShopSettingActivity.this, LinkNameSetActivity.class);
                mIntent.putExtra("shopname", mTShopName.getText().toString());
                mIntent.putExtra("linkname", mTLinkName.getText().toString());
                mIntent.putExtra("linktel", mTLinkTel.getText().toString());
                mIntent.putExtra("bundle1", bundle2);
                startActivityForResult(mIntent, 2);
                break;
            case R.id.r_link_tel:
                Bundle bundle3 = new Bundle();
                bundle3.putSerializable("shopinfo", mAllShopinfoBean);
                mIntent = new Intent(ShopSettingActivity.this, LinkTelsetActivity.class);
                mIntent.putExtra("linkname", mTLinkName.getText().toString());
                mIntent.putExtra("shopname", mTShopName.getText().toString());
                mIntent.putExtra("linktel", mTLinkTel.getText().toString());
                mIntent.putExtra("bundle1", bundle3);
                startActivityForResult(mIntent, 3);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                try {
                    mTShopName.setText(data.getStringExtra("cotent"));
                } catch (Exception e) {
                }
                break;
            case 2:
                try {
                    mTLinkName.setText(data.getStringExtra("cotent"));
                } catch (Exception e) {
                }
                break;
            case 3:
                try {
                    mTLinkTel.setText(data.getStringExtra("cotent"));
                } catch (Exception e) {
                }
                break;
        }
    }

    private void initViews() {
        mBack = findViewById(R.id.tv_back);
        mLogo = findViewById(R.id.img_logo);
        mRShopName = findViewById(R.id.r_shop_name);
        mRLinkName = findViewById(R.id.r_link_name);
        mRLinkTel = findViewById(R.id.r_link_tel);
        mTShopName = findViewById(R.id.tv_shop_name);
        mTLinkName = findViewById(R.id.tv_link_name);
        mTLinkTel = findViewById(R.id.tv_link_tel);
        mTShopType = findViewById(R.id.tv_shop_type);
        mInsdutry = findViewById(R.id.tv_industry);
        mTVipTotalCount = findViewById(R.id.tv_vip_total_count);
        mTGoodsTotal = findViewById(R.id.tv_goods_total);
        mTUserTotalCount = findViewById(R.id.tv_user_total_count);
        mTShortMessageStock = findViewById(R.id.tv_shotmessage_stock);
        mTLimitTime = findViewById(R.id.tv_limit_time);
        mRShopName.setOnClickListener(this);
        mRLinkName.setOnClickListener(this);
        mRLinkTel.setOnClickListener(this);
        mBack.setOnClickListener(this);

    }


}
