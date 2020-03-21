package com.zhiluo.android.yunpu.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jauker.widget.BadgeView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.home.activity.AboutActivity;
import com.zhiluo.android.yunpu.home.activity.FeedbackActivity;
import com.zhiluo.android.yunpu.home.activity.MineKeFuActivity;
import com.zhiluo.android.yunpu.home.activity.ShareActivity;
import com.zhiluo.android.yunpu.home.activity.ShopSettingActivity;
import com.zhiluo.android.yunpu.home.activity.YSLMessagesActivity;
import com.zhiluo.android.yunpu.home.activity.YSLSystemSetActivity;
import com.zhiluo.android.yunpu.home.jsonbean.MessageCenterJsonBean;
import com.zhiluo.android.yunpu.home.jsonbean.SingleShopInfoJsonBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.activity.LoginActivity;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.ui.activity.ShopMallActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-04-08.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private CircleImageView mHeadImg;
    private TextView mNickName, mShopID, mVersionType, teMessageCount;
    private RelativeLayout mMesssages, mSetting, mShoppingyMalll, mFade, mAboutUs, mKeFu, mSysytemSetting;
    private String mStoreGID,mAcountname;//店铺GID
    private SingleShopInfoJsonBean mBean;
    private Intent mIntent;
    private BadgeView badgeView;
    private String imgurl;
    private SharedPreferences preferences;
    private View viewAboutUs;
    private Button btnLoginOut;
    private SweetAlertDialog mSweetAlertDialog;
    private LoginUpbean loginUpbean;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_mine, null, false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            initDatas();
        } catch (Exception e) {
        }
        preferences = getContext().getSharedPreferences("setting", Context.MODE_PRIVATE);
        Li("平台版" + preferences.getString("yunpu", ""));
        if (!preferences.getString("yunpu", "").equals("PlatformUser")) {
            mShoppingyMalll.setVisibility(View.GONE);
//            mAboutUs.setVisibility(View.GONE);
//            viewAboutUs.setVisibility(View.GONE);
        }
        try {
            getMessage();
        } catch (Exception e) {
        }

    }

    /**
     * 给view绑定数据
     */
    private void attachViews() {
        mShopID.setText("店铺名称: " + (mBean.getData().getSM_Name() == null ? "默认店铺" : mBean.getData().getSM_Name()));
        mNickName.setText(mAcountname);
        setVersionsType(mBean);
    }

    private void getMessage() {
        String mAccountGid = (String) uSharedPreferencesUtiles.get(getContext(), "Account_GID", "");
        String mCommanyGid = (String) uSharedPreferencesUtiles.get(getContext(), "CY_GID", "");
        RequestParams params = new RequestParams();
        params.put("User_GID", mAccountGid);
        params.put("CY_GID", mCommanyGid);
        params.put("PageIndex", 1);
        params.put("PageSize", 10);
        HttpHelper.post(getContext(), HttpAPI.API().NOTICE, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                int num = 0;
                MessageCenterJsonBean bean = CommonFun.JsonToObj(responseString, MessageCenterJsonBean.class);
                for (int i = 0; i < bean.getData().getDataList().size(); i++) {
                    if (bean.getData().getDataList().get(i).getPopState() != 1) {
                        num++;
                    }
                }
                if (MineFragment.this.isAdded()) {
                    if (badgeView == null) {
                        badgeView = new BadgeView(getContext());
                    }
                    badgeView.setTargetView(teMessageCount);
                    badgeView.setBadgeMargin(40, 0, 0, 0);
                    badgeView.setBadgeCount(num);
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * @param bean ,
     *             店铺版本
     */
    private void setVersionsType(SingleShopInfoJsonBean bean) {
        switch (bean.getData().getSM_Type()) {
            case 0:
                mVersionType.setText("免费版");
                break;
            case 1:
                mVersionType.setText("高级版");
                break;

            case 3001:
                mVersionType.setText("丽人美业");
                break;
            case 3002:
                mVersionType.setText("汽车美容");
                break;
            case 3003:
                mVersionType.setText("加油站");
                break;

            case 3004:
                mVersionType.setText("测试");
                break;
            case 3005:
                mVersionType.setText("服装店");
                break;

            case 3006:
                mVersionType.setText("眼镜店");
                break;
            default:
                mVersionType.setText("免费版");
                break;
        }
    }

    private void initDatas() {
        mStoreGID = (String) uSharedPreferencesUtiles.get(getContext(), "StoreGid", "");
        mAcountname =(String) uSharedPreferencesUtiles.get(getContext(), "mAcountname", "");
        loginUpbean = (LoginUpbean) CacheData.restoreObject("LG");
        loadDatas();
        try {
            imgurl = loginUpbean.getData().getUM_ChatHead();
            if (!imgurl.contains("http")) {
                imgurl = MyApplication.IMAGE_URL + loginUpbean.getData().getUM_ChatHead();
            }
            Glide.with(getContext()).load(imgurl).error(R.drawable.defalut_store).into(mHeadImg);
        } catch (Exception e) {
            Glide.with(getContext()).load(R.drawable.defalut_store).into(mHeadImg);
        }

    }

    /**
     * 加载数据
     */
    private void loadDatas() {
        RequestParams params = new RequestParams();
        params.put("GID", mStoreGID);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mBean = CommonFun.JsonToObj(responseString, SingleShopInfoJsonBean.class);
                Le(responseString);
                try {
                    attachViews();
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(String msg) {
                Li("faliure----" + msg);
                CustomToast.makeText(getContext(), "该账号没有获取店铺信息权限，请联系超级管理员添加权限", Toast.LENGTH_SHORT).show();
                mBean = new SingleShopInfoJsonBean();
            }
        };
        callBack.setmAPI("Shops/GetShops");
        HttpHelper.post(getContext(),  HttpAPI.API().SINGLE_SHOP_INFO, params, callBack);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r_mesage://消息
                mIntent = null;
                mIntent = new Intent(getActivity(), YSLMessagesActivity.class);
                startActivity(mIntent);
                break;
            case R.id.r_set://店铺设置ShopSetting
                if (YSLUtils.isOpenActivon("设置店铺")) {
                    mIntent = null;
                    mIntent = new Intent(getActivity(), ShopSettingActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("shopinfo", mBean);
                    mIntent.putExtra("bundle", bundle);
                    startActivity(mIntent);
                }else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.r_shopping_mall://云铺商城
                mIntent = null;
                mIntent = new Intent(getActivity(), ShopMallActivity.class);
                startActivity(mIntent);
                break;
            case R.id.r_fade://反馈建议
                mIntent = null;
                mIntent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(mIntent);
                break;
//            case R.id.r_share://好友分享
//                mIntent = null;
//                mIntent = new Intent(getActivity(), ShareActivity.class);
//                startActivity(mIntent);
//                break;
            case R.id.r_aboutus://关于我们
                mIntent = null;
                mIntent = new Intent(getActivity(), MineKeFuActivity.class);
//                mIntent = new Intent(getActivity(), AboutActivity.class);
                startActivity(mIntent);
                break;
            case R.id.r_mykefu://我的客服
                mIntent = null;
                mIntent = new Intent(getActivity(), MineKeFuActivity.class);
                startActivity(mIntent);
                break;
            case R.id.r_systemseting://系统设置
                mIntent = null;
                mIntent = new Intent(getActivity(), YSLSystemSetActivity.class);
                startActivity(mIntent);
                break;
            case R.id.btn_login_out://退出
                mSweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE);
                mSweetAlertDialog.setTitleText("提示");
                mSweetAlertDialog.setCancelText("取消");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setContentText("真的要退出吗？");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        exitLogin();
                    }
                });
                mSweetAlertDialog.show();
                break;
        }
    }

    /**
     * 退出登录
     */
    private void exitLogin() {
        AsyncHttpClient httpClient = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(getContext());
        httpClient.setCookieStore(cookieStore);
        httpClient.post(HttpAPI.API().SIGNOUT, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                CustomToast.makeText(getContext(), responseString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                SharedPreferences sp = getContext().getSharedPreferences("login", Activity.MODE_PRIVATE);
//                sp.edit().clear().apply();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.putExtra("exit", "exit");
                SharedPreferences sp = getContext().getSharedPreferences("login", 0);
                sp.edit().putBoolean("exit_login", true).apply();
                mSweetAlertDialog.dismiss();
                startActivity(intent);
                getActivity().finish();

//                ActivityCollector.finishAll();
                ///  android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
    }

    private void initViews(View view) {
        mHeadImg = view.findViewById(R.id.img_hedimg);
        mNickName = view.findViewById(R.id.tv_nickname);
        mShopID = view.findViewById(R.id.tv_shopname);
        mVersionType = view.findViewById(R.id.tv_versions);
        mMesssages = view.findViewById(R.id.r_mesage);
        mSetting = view.findViewById(R.id.r_set);
        mShoppingyMalll = view.findViewById(R.id.r_shopping_mall);
        mFade = view.findViewById(R.id.r_fade);
        mAboutUs = view.findViewById(R.id.r_aboutus);
        mKeFu = view.findViewById(R.id.r_mykefu);
        mSysytemSetting = view.findViewById(R.id.r_systemseting);
        teMessageCount = view.findViewById(R.id.tv_message);
        viewAboutUs = view.findViewById(R.id.view_about_us);

        btnLoginOut = view.findViewById(R.id.btn_login_out);
        btnLoginOut.setOnClickListener(this);
        mMesssages.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mShoppingyMalll.setOnClickListener(this);
        mFade.setOnClickListener(this);
        mAboutUs.setOnClickListener(this);
        mKeFu.setOnClickListener(this);
        mSysytemSetting.setOnClickListener(this);
    }

}
