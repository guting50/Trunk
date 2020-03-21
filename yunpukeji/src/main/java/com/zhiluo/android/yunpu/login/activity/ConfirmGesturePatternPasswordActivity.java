package com.zhiluo.android.yunpu.login.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.takwolf.android.lock9.Lock9View;
import com.yanzhenjie.permission.AndPermission;
import com.zhiluo.android.yunpu.BuildConfig;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.mvp.model.SwitchEntity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import java.io.File;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 登录手势图案解锁密码
 * author Yuan Cheng
 * blog yuancheng91.top
 * email jasoncheng9111@163.com
 * created 2017/4/1 15:50
 */
public class ConfirmGesturePatternPasswordActivity extends BaseActivity {
    private TextView mPlMessageText;
    private Lock9View mLock9View;
    private RelativeLayout mRlButtonContainer;
    private TextView mTvLeftButton;
    private TextView mTvRightButton;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mShopGid;
    private upDataHandler dataHandler;

    private void assignViews() {
        mPlMessageText = (TextView) findViewById(R.id.pl_message_text);
        mLock9View = (Lock9View) findViewById(R.id.lock_9_view_confirm);
        mRlButtonContainer = (RelativeLayout) findViewById(R.id.rl_button_container);
        mTvLeftButton = (TextView) findViewById(R.id.tv_left_button);
        mTvRightButton = (TextView) findViewById(R.id.tv_right_button);
        dataHandler = new upDataHandler();
    }


    public class upDataHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    update();
                    break;
            }
        }
    }

    /**
     * 升级
     */
    void update() {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri apkUri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                apkUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", new File(MyApplication.APK_ADDDRESS + "/vip.apk"));
            } else {
                apkUri = Uri.fromFile(new File(MyApplication.APK_ADDDRESS + "/vip.apk"));
            }
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            // 查询所有符合 intent 跳转目标应用类型的应用，注意此方法必须放置在 setDataAndType 方法之后
            List<ResolveInfo> resolveLists = this.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            // 然后全部授权
            for (ResolveInfo resolveInfo : resolveLists) {
                String packageName = resolveInfo.activityInfo.packageName;
                this.grantUriPermission(packageName, apkUri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gesturepattern_confirm);

        assignViews();

        //权限等相关设置申请

        AndPermission.with(ConfirmGesturePatternPasswordActivity.this)
                .requestCode(100)
                .permission(
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.REQUEST_INSTALL_PACKAGES
                )
                .send();




        mTvLeftButton.setText("切换账号登录");
        mTvRightButton.setText("忘记手势密码");

        mSharedPreferences = getSharedPreferences("login", 0);
        mEditor = mSharedPreferences.edit();

        if (getIntent().getStringExtra("is_ONOFF") != null) {
            mTvLeftButton.setVisibility(View.GONE);
            mTvRightButton.setVisibility(View.GONE);
        }else {
            GetVersion.getVersion(ConfirmGesturePatternPasswordActivity.this,dataHandler);
        }


        //切换账号
        mTvLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditor.putString("isForgotPassword", "n");
                mEditor.putString("isHasPattern", "y");
                mEditor.putString("noIgnoreAsked", "n");
                mEditor.putString("isChangeAcount", "y");
                mEditor.commit();
                startActivity(new Intent(ConfirmGesturePatternPasswordActivity.this, LoginActivity.class));
                finish();
            }
        });

        //忘记手势密码
        mTvRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditor.putString("isForgotPassword", "y");
                mEditor.putString("isHasPattern", "y");
                mEditor.putString("noIgnoreAsked", "n");
//                mEditor.putString("password", "");
                mEditor.commit();
                startActivity(new Intent(ConfirmGesturePatternPasswordActivity.this, LoginActivity.class));
                finish();
            }
        });

        mLock9View.setCallBack(new Lock9View.CallBack() {

            @Override
            public void onFinish(String password) {
                if (password.length() < 4) {
                    mPlMessageText.setText("请至少连接4个点");
                    mPlMessageText.setTextColor(Color.RED);
                }
                if (password.equals(mSharedPreferences.getString("sha1", ""))) {
                    Intent result = new Intent();
                    if (getIntent().getStringExtra("is_ONOFF") == null) {
                        mEditor.putString("isForgotPassword", "n");
                        mEditor.putString("isHasPattern", "y");
                        mEditor.putString("noIgnoreAsked", "n");
                        mEditor.commit();

                        mPlMessageText.setText("正确");
                        mPlMessageText.setTextColor(Color.BLACK);

                        RequestParams params = new RequestParams();
                        params.put("UserAcount", mSharedPreferences.getString("username", ""));
                        params.put("PassWord", mSharedPreferences.getString("password", ""));
                        params.put("Type",2);
//                        params.put("VerifyCode", "");
                        HttpHelper.post(ConfirmGesturePatternPasswordActivity.this, HttpAPI.API().LOGIN, params, new CallBack() {
                            @Override
                            public void onSuccess(String responseString, Gson gson) {
                                LoginUpbean loginUpbean = CommonFun.JsonToObj(responseString, LoginUpbean.class);

                                LoginUpbean.DataBean bean = loginUpbean.getData();
                                MyApplication.LOGINCODE = true;
                                if (bean.getShopID() != null) {
                                    for (int i = 0; i < bean.getShopList().size(); i++) {
                                        if (bean.getShopID().equals(bean.getShopList().get(i).getGID())) {
                                            mShopGid = bean.getShopList().get(i).getGID();
                                            break;
                                        }
                                    }
                                } else {
                                    mShopGid = loginUpbean.getData().getShopList().get(0).getGID();
                                }

                                CacheData.deleteDir();//清空保存到本地的商品信息和会员等级信息
//                                getSystemSwitch();
                                getAllMessage();


                                /**
                                 * 若不是超管，将登录数据保存到本地备用
                                 */
                                if (loginUpbean.getData().getMenuInfoList() != null) {
                                    CacheData.saveObject("login", loginUpbean);
                                }
                                CacheData.saveObject("LG", loginUpbean);

                                uSharedPreferencesUtiles.put(ConfirmGesturePatternPasswordActivity.this, "StoreGid", mShopGid);
                                uSharedPreferencesUtiles.put(ConfirmGesturePatternPasswordActivity.this, "Account_GID", loginUpbean.getData().getGID());
                                uSharedPreferencesUtiles.put(ConfirmGesturePatternPasswordActivity.this, "CY_GID", loginUpbean.getData().getCY_GID());

                                Intent intent = new Intent(ConfirmGesturePatternPasswordActivity.this, HomeActivity.class);
                                intent.putExtra("shopname", loginUpbean.getData().getShopList().get(0).getSM_Name());
                                intent.putExtra("creator", loginUpbean.getData().getShopList().get(0).getSM_Creator());
                                intent.putExtra("shopGid", loginUpbean.getData().getShopList().get(0).getGID());
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onFailure(String msg) {
                                mEditor.putString("isForgotPassword", "n");
                                mEditor.putString("isHasPattern", "y");
                                mEditor.putString("noIgnoreAsked", "n");
                                mEditor.putString("isChangeAcount", "y");
                                mEditor.commit();
                                startActivity(new Intent(ConfirmGesturePatternPasswordActivity.this, LoginActivity.class));
                                finish();
                                CustomToast.makeText(MyApplication.getmContext(),
                                        "密码错误，请重新登录！", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void redirect(String statusCode) {
                                Loging();
                            }
                        });
                    } else if (getIntent().getStringExtra("is_ONOFF").equals("off")) {
                        mTvLeftButton.setVisibility(View.GONE);
                        mTvRightButton.setVisibility(View.GONE);
                        mEditor.putString("isForgotPassword", "n");
                        mEditor.putString("isHasPattern", "n");
                        mEditor.putString("noIgnoreAsked", "y");
                        mEditor.putString("sha1", "");
                        mEditor.commit();
                        result.putExtra("success", "off");
                        setResult(RESULT_OK, result);
                        finish();
                    }
                } else {
                    mPlMessageText.setText("密码错误");
                    mPlMessageText.setTextColor(Color.RED);
                }
            }

        });

    }

    private void Loging() {
        RequestParams params = new RequestParams();
        params.put("UserAcount", mSharedPreferences.getString("username", ""));
        params.put("PassWord", mSharedPreferences.getString("password", ""));
//        params.put("VerifyCode", "");
        params.put("Type",2);
        HttpHelper.post(ConfirmGesturePatternPasswordActivity.this, HttpAPI.API().LOGIN, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CacheData.deleteDir();//清空保存到本地的商品信息和会员等级信息
                LoginUpbean loginUpbean = CommonFun.JsonToObj(responseString, LoginUpbean.class);
                /**
                 * 若不是超管，将登录数据保存到本地备用
                 */
                if (loginUpbean.getData().getMenuInfoList() != null) {
                    CacheData.saveObject("login", loginUpbean);
                }
                CacheData.saveObject("LG", loginUpbean);
                LoginUpbean.DataBean bean = loginUpbean.getData();
                if (bean.getShopID() != null) {
                    for (int i = 0; i < bean.getShopList().size(); i++) {
                        if (bean.getShopID().equals(bean.getShopList().get(i).getGID())) {
                            mShopGid = bean.getShopList().get(i).getGID();
                            break;
                        }
                    }
                } else {
                    mShopGid = loginUpbean.getData().getShopList().get(0).getGID();
                }
                getAllMessage();
//                getSystemSwitch();
                uSharedPreferencesUtiles.put(ConfirmGesturePatternPasswordActivity.this, "StoreGid", mShopGid);
                uSharedPreferencesUtiles.put(ConfirmGesturePatternPasswordActivity.this, "Account_GID", loginUpbean.getData().getGID());
                uSharedPreferencesUtiles.put(ConfirmGesturePatternPasswordActivity.this, "CY_GID", loginUpbean.getData().getCY_GID());

                Intent intent = new Intent(ConfirmGesturePatternPasswordActivity.this, HomeActivity.class);
                intent.putExtra("shopname", loginUpbean.getData().getShopList().get(0).getSM_Name());
                intent.putExtra("creator", loginUpbean.getData().getShopList().get(0).getSM_Creator());
                intent.putExtra("shopGid", loginUpbean.getData().getShopList().get(0).getGID());
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String msg) {
                mEditor.putString("isForgotPassword", "n");
                mEditor.putString("isHasPattern", "y");
                mEditor.putString("noIgnoreAsked", "n");
                mEditor.putString("isChangeAcount", "y");
                mEditor.commit();
                startActivity(new Intent(ConfirmGesturePatternPasswordActivity.this, LoginActivity.class));
                finish();
                CustomToast.makeText(MyApplication.getmContext(),
                        "密码错误，请重新登录！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra("success", "on");
        setResult(RESULT_OK, result);
        mEditor.putString("isHasPattern", "y");
        mEditor.putString("isForgotPassword", "n");
        mEditor.putString("noIgnoreAsked", "n");
        mEditor.commit();
        super.onBackPressed();
    }


    /***
     *
     * 获取相关缓存数据
     *
     * **/

    private void getAllMessage(){
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                if (reportbean!=null){
                    List<ReportMessageBean.DataBean.GetSysSwitchListBean> switchEntity = reportbean.getData().getGetSysSwitchList();
                    if (switchEntity!=null){
                        CacheData.saveObject("switch", switchEntity);//缓存系统开关到本地
                    }
                    ReportMessageBean.DataBean.PrintSetBean printbean = reportbean.getData().getPrintSet();
                    if (printbean!=null){
                        CacheData.saveObject("print", printbean);//缓存打印开关到本地
                        if (printbean.getPS_IsEnabled() == 1) {
                            MyApplication.PRINT_IS_OPEN = true;
                        } else {
                            MyApplication.PRINT_IS_OPEN = false;
                        }
                        if (printbean != null && printbean.getPrintTimesList() != null) {
                            for (int i = 0; i < printbean.getPrintTimesList().size(); i++) {
                                ReportMessageBean.DataBean.PrintSetBean.PrintTimesListBean bean = printbean.getPrintTimesList().get(i);
                                if ("HYCZ".equals(bean.getPT_Code())) {
                                    MyApplication.HYCZ_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("HYCC".equals(bean.getPT_Code())) {
                                    MyApplication.HYCC_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("SPXF".equals(bean.getPT_Code())) {
                                    MyApplication.SPXF_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("YJJY".equals(bean.getPT_Code())) {
                                    MyApplication.YJJY_PRINT_TIMES = bean.getPT_Times();
                                }

                                if ("KSXF".equals(bean.getPT_Code())) {
                                    MyApplication.KSXF_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("JCXF".equals(bean.getPT_Code())) {
                                    MyApplication.JCXF_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("JFDH".equals(bean.getPT_Code())) {
                                    MyApplication.JFDH_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("TCXF".equals(bean.getPT_Code())) {
                                    MyApplication.TCXF_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("HYKK".equals(bean.getPT_Code())) {
                                    MyApplication.HYKK_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("JB".equals(bean.getPT_Code())) {
                                    MyApplication.JB_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("RK".equals(bean.getPT_Code())) {
                                    MyApplication.RK_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("CK".equals(bean.getPT_Code())) {
                                    MyApplication.CK_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("SPTH".equals(bean.getPT_Code())) {
                                    MyApplication.SPTH_PRINT_TIMES = bean.getPT_Times();
                                }
                                if ("XSXF".equals(bean.getPT_Code())) {
                                    MyApplication.XSXF_PRINT_TIMES = bean.getPT_Times();
                                }

                            }
                        }
                    }
                    List<ReportMessageBean.DataBean.VIPGradeListBean> gradebean = reportbean.getData().getVIPGradeList();
                    if (gradebean!=null){
                        CacheData.saveObject("grade", gradebean);//缓存等级列表到本地
                    }
                    List<ReportMessageBean.DataBean.DepartmentListBean> deptbean = reportbean.getData().getDepartmentList();
                    if (deptbean !=null){
                        CacheData.saveObject("dept", deptbean);//缓存部门列表到本地
                    }
                    List<ReportMessageBean.DataBean.EmplistBean> staffbean = reportbean.getData().getEmplist();
                    if (staffbean !=null){
                        CacheData.saveObject("staff", staffbean);//缓存员工列表到本地
                    }
                }
            }

            @Override
            public void onFailure(String msg) {

                MyApplication.PRINT_IS_OPEN = false;

                new SweetAlertDialog(ConfirmGesturePatternPasswordActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        .setContentText(msg)
                        .setConfirmText("了解")
                        .show();
            }
        });
    }
}
