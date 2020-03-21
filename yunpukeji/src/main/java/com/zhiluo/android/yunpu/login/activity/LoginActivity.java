package com.zhiluo.android.yunpu.login.activity;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.yanzhenjie.permission.AndPermission;
import com.zhiluo.android.yunpu.BuildConfig;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.SunMiBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.mvp.model.SuccessEntry;
import com.zhiluo.android.yunpu.print.util.GetPrintSet;
import com.zhiluo.android.yunpu.sms.jsonbean.SmsSwitch;
import com.zhiluo.android.yunpu.ui.activity.ActivityCollector;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.LoadingDialogUtil;
import com.zhiluo.android.yunpu.ui.view.NoDoubleClickListener;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CacheFun;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.TextChange;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 登录界面用于完成登录主界面和注册以及修改密码的跳转
 */
public class LoginActivity extends BaseActivity {
    private Button tologin;
    private TextView tosignup, tvisacc;
    private EditText username;
    private EditText passerword;
    private String susername;
    private String spassword;
    private TextView nopassword;
    private SharedPreferences sp, sp1;
    private SharedPreferences.Editor editor, editor1;
    private String shopname;
    private String creator;
    private String myuser;
    private String mytitle;
    private ImageView tv_clean_acount_loginactivity;
    private SweetAlertDialog sweetAlertDialog;
    private ImageView Iv_head;
    private TextView tv_appname;

    //参考自http://stackoverflow.com/questions/32061934/permission-from-manifest-doesnt-work-in-android-6
    public static int OVERLAY_PERMISSION_REQ_CODE = 1234;
    private long exitTime = 0;
    private String mShopGid, mShopePicture, mAcountname;
    private ImageView mCleanPassword;
    private boolean isPwd = false;
    private String mExitFlag;
    private boolean exit;
    private String mAccount;//账户
    private TextView tvCopyRight;
    private CheckBox cbRemberPW;
    private SweetAlertDialog AlertDialog;
    private int REQUEST_EXTERNAL_STORAGE = 1;
    private String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private String mVersionRemark, mDownloadAddr;//版本升级备注信息,下载地址
    private boolean b;
    private ProgressDialog pBar;
    private Dialog mDialog;
    private upDataHandler dataHandler;
    private AsyncHttpClient mClient = new AsyncHttpClient();
    private Gson gson = new Gson();
    private boolean isPermission = false;

    @SuppressLint("WrongConstant")
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            CustomToast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            ActivityCollector.finishAll();
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        // 避免从桌面启动程序后，会重新实例化入口类的activity
//        if (!this.isTaskRoot()) {processName
//            Intent intent = getIntent();
//            if (intent != null) {
//                String action = intent.getAction();
//                if (intent.hasCategory(Intent.CATEGORY_LAUNCHER) && Intent.ACTION_MAIN.equals(action)) {
//                    finish();
//                    return;
//                }
//            }
//        }
//        initUM();//友盟
        mExitFlag = getIntent().getStringExtra("exit");
        sp = getSharedPreferences("login", 0);
        exit = sp.getBoolean("exit_login", false);

        String str = getApplication().getPackageName();
        if (str.equals("com.zhiluo.android.vip")) {//判断是云上铺还是中性版本
            MyApplication.IS_YUNSHANGPU = false;
            MyApplication.FILE_ADDRESS = "com.zhiluo.android.vip.fileprovider";
            GetVersion.upApi = "AppUpgrade/GetNewsVersionForAgent";
        } else {
            MyApplication.IS_YUNSHANGPU = true;
            MyApplication.FILE_ADDRESS = "com.zhiluo.android.yunpu.fileprovider";
            GetVersion.upApi = "AppUpgrade/GetNewsVersion";
        }

        if (mExitFlag == null && !exit) {
            //判断用户是否设置了图案密码
            if (sp.getString("isHasPattern", "").equals("y")
                    && sp.getString("isForgotPassword", "").equals("n")
                    && sp.getString("isChangeAcount", "").equals("n")) {
                Intent intent = new Intent(this, ConfirmGesturePatternPasswordActivity.class);
                intent.putExtra("isFirstLaunchApp", "login");
                startActivity(intent);
                finish();
            }
        }
        setContentView(R.layout.activity_login);

//        //设置沉浸式状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            //透明状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //透明导航栏
//            //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        initView();

        shangmiAuthorize();
        initSp();

        initListener();


        //权限等相关设置申请
        requestDrawOverLays();
        GetVersion.getVersion(LoginActivity.this, dataHandler);
    }

    //申请运行时权限
    private void setPermission() {
        AndPermission.with(this)
                .requestCode(100)
                .permission(
                        Manifest.permission.CAMERA,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.NFC,
                        Manifest.permission.BIND_NFC_SERVICE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_CONTACTS,
                        Manifest.permission.REQUEST_INSTALL_PACKAGES
                )
                .send();
    }


    /**
     * 权限等相关设置申请
     */
    private void requestDrawOverLays() {
        if (Build.VERSION.SDK_INT >= 23) {
            //判断该设备的生成厂商是否是联想的ZUK系列
//            if (MyApplication.CARRIER.equals("ZUK")) {
//                return;
//            }


            /**
             * 请求SYSTEM_ALERT_WINDOW权限
             */
            /*if (!Settings.canDrawOverlays(this)) {
                sweetAlertDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.NORMAL_TYPE);
                sweetAlertDialog.setTitleText("提示");
                sweetAlertDialog.setContentText("使用本软件，还需开启以下功能");
                sweetAlertDialog.setConfirmText("下一步");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + LoginActivity.this.getPackageName()));
                        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                    }
                });
                sweetAlertDialog.show();
            }*/
            setPermission();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
                if (!Settings.canDrawOverlays(this)) {
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("若未开启-‘允许出现在其他应用上’-的权限。其应用可能运行异常。")
                            .setConfirmText("了解")
                            .show();
                } else {
                    new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("权限开启成功！")
                            .setConfirmText("了解")
                            .show();
                    if (sweetAlertDialog != null) {
                        sweetAlertDialog.dismiss();
                    }
                }
            }
        }
    }


    /**
     * @param activity ,
     * @return 申请手机读写存储卡权限
     */
    private boolean getPermission(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 获取sharedpreferences里保存的数据
     */
    private void initSp() {

        //        editor = sp.edit();
        sp1 = getSharedPreferences("setting", MODE_PRIVATE);
        editor1 = sp1.edit();
        String remebername = sp.getString("username", "");
        mAccount = remebername;
        String remeberpassword = null;
//        if (mExitFlag == null) {
        remeberpassword = sp.getString("password", "");
//            Li("读取登录密码" + remeberpassword);
        passerword.setText(remeberpassword);
//        } else {
//            sp.edit().putBoolean("exit_login", true).apply();
//            //sp.edit().remove("password").apply();
//        }
        username.setText(remebername);
        if (remeberpassword == null || remeberpassword.equals("")) {
            cbRemberPW.setChecked(false);
        } else {
            cbRemberPW.setChecked(true);
        }


    }

    /**
     * 监听器
     */
    private void initListener() {

        cbRemberPW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    sp.edit().remove("password").apply();
                }
            }
        });
        //登陆
//        tologin.setOnClickListener(new NoDoubleClickListener() {
//            @Override
//            public void onNoDoubleClick(View v) {
//                if (isPermission) {
//                    //登陆
//                    if (!TextUtils.isEmpty(username.getText())) {
//                        susername = username.getText().toString();
//                        if (!TextUtils.isEmpty(passerword.getText()) && passerword.getText().toString().length() > 5 && passerword.getText().toString().length() < 21) {
//                            spassword = passerword.getText().toString();
//                            if (!getPermission(LoginActivity.this)) {
//                                AlertDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.NORMAL_TYPE);
//                                AlertDialog.setTitleText("提示");
//                                AlertDialog.setContentText("请在您手机设置中，开启读写文件权限");
//                                AlertDialog.setConfirmText("好的");
//                                AlertDialog.setCancelable(false);
//                                AlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                        AlertDialog.dismiss();
//                                        //ActivityCompat.requestPermissions(LoginActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
//                                    }
//                                });
//                                AlertDialog.show();
//                            } else {
//                                onLogin();
//                            }
//                        } else {
//                            CustomToast.makeText(LoginActivity.this, "请输入6-20位密码", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        CustomToast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    CustomToast.makeText(LoginActivity.this, "当前设备尚未授权请联系客服。", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
        //注册
        tosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisteActivity.class);
                startActivity(intent);
            }
        });
        //忘记密码
        nopassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, NewpasswordActivity.class);
                startActivity(intent);
            }
        });
        //清空账号
        tv_clean_acount_loginactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText("");
                passerword.setText("");
                username.requestFocus();

            }
        });
        mCleanPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isPwd) {
                    mCleanPassword.setImageResource(R.drawable.pwd_hidden);
                    //否则隐藏密码
                    passerword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    mCleanPassword.setImageResource(R.drawable.pwd_show);
                    //如果选中，显示密码
                    passerword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                passerword.setSelection(passerword.getText().toString().length());
                isPwd = !isPwd;
            }
        });


    }

    private String getSN() {
        String sn = null;
        try {
            Class c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            sn = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sn;
    }


    /**
     * 授权验证
     */

    private void getSNPermission() {
        final String snNum = getSN();
        RequestParams params = new RequestParams();
        params.put("SNCode", snNum);//P12117BL00512

        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        mClient.setCookieStore(cookieStore);
//        mDialog = LoadingDialogUtil.createLoadingDialog(this, "验证授权...", false);
        mClient.post(HttpAPI.API().SN_PERMISSION, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
//                    mDialog.dismiss();
                    String responseString = new String(responseBody, "UTF-8");
                    SuccessEntry entry = CommonFun.JsonToObj(responseString, SuccessEntry.class);
                    SunMiBean sunMiBean = new SunMiBean();
                    sunMiBean.setSnCode(snNum);
                    if (entry.isSuccess()) {
                        isPermission = true;
                        sunMiBean.setCode(200);
                        CacheData.saveObject("sunmi_authorize", sunMiBean);
                        loginJudge();
                    } else {
//                        if (DataUtils.isOverTime("2019-10-01 00:00:00")) {
//                            requestSNPermission();
//                        }
                        CustomToast.makeText(LoginActivity.this, "当前设备授权请求接口失败," + "返回码" + entry.getCode(), Toast.LENGTH_SHORT).show();
                        sunMiBean.setCode(205);
                        CacheData.saveObject("sunmi_authorize", sunMiBean);
                        notLogin();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                mDialog.dismiss();
                isPermission = false;
                CustomToast.makeText(LoginActivity.this, "  " + error.toString() + "  " + statusCode, Toast.LENGTH_SHORT).show();
            }
        });

    }


    /**
     * 自动授权
     */

    private void requestSNPermission() {
        RequestParams params = new RequestParams();
        params.put("SNCode", getSN());
        params.put("Remarks", "");
        params.put("CY_GID", "");
        params.put("Type", 1);

        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        mClient.setCookieStore(cookieStore);
        mDialog = LoadingDialogUtil.createLoadingDialog(this, "授权中...", false);
        mClient.post(HttpAPI.API().SN_PERMISSIONself, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    mDialog.dismiss();
                    String responseString = new String(responseBody, "UTF-8");
                    SuccessEntry entry = CommonFun.JsonToObj(responseString, SuccessEntry.class);
                    if (entry.isSuccess()) {
                        CustomToast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                        isPermission = true;
                    } else {
                        CustomToast.makeText(LoginActivity.this, "当前设备授权失败请联系客服。", Toast.LENGTH_SHORT).show();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                mDialog.dismiss();
                CustomToast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /***
     *
     * 获取相关缓存数据
     *
     * **/

    private void getAllMessage() {
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                if (reportbean != null) {
                    List<ReportMessageBean.DataBean.GetSysSwitchListBean> switchEntity = reportbean.getData().getGetSysSwitchList();
                    if (switchEntity != null) {
                        CacheData.saveObject("switch", switchEntity);//缓存系统开关到本地
                        for (int i = 0; i < switchEntity.size(); i++) {
                            ReportMessageBean.DataBean.GetSysSwitchListBean bean = switchEntity.get(i);
                            switch (bean.getSS_Code()) {
                                //撤单密码
                                case "215":
                                    if (bean.getSS_State() == 1) {
                                        MyApplication.IS_CANCEL_ORDER = true;
                                        MyApplication.CANCEL_PAW = bean.getSS_Value();
                                    } else {
                                        MyApplication.IS_CANCEL_ORDER = false;
                                    }
                                    break;
                            }
                        }
                    }
                    ReportMessageBean.DataBean.PrintSetBean printbean = reportbean.getData().getPrintSet();
                    if (printbean != null) {
                        CacheData.saveObject("print", printbean);//缓存打印开关到本地
                        if (printbean.getPS_IsEnabled() == 1) {
                            MyApplication.PRINT_IS_OPEN = true;
                        } else {
                            MyApplication.PRINT_IS_OPEN = false;
                        }
                        //小票打印份数
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
                    if (gradebean != null) {
                        CacheData.saveObject("grade", gradebean);//缓存等级列表到本地
                    }
                    List<ReportMessageBean.DataBean.DepartmentListBean> deptbean = reportbean.getData().getDepartmentList();
                    if (deptbean != null) {
                        CacheData.saveObject("dept", deptbean);//缓存部门列表到本地
                    }
                    List<ReportMessageBean.DataBean.EmplistBean> staffbean = reportbean.getData().getEmplist();
                    if (staffbean != null) {
                        CacheData.saveObject("staff", staffbean);//缓存员工列表到本地
                    }

                    List<ReportMessageBean.DataBean.GetCustomFieldsVIPBean> costomfieldsvipbean = reportbean.getData().getGetCustomFieldsVIP();
                    if (costomfieldsvipbean != null) {
                        CacheData.saveObject("costomfield", costomfieldsvipbean);//缓存自定义属性到本地
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                MyApplication.PRINT_IS_OPEN = false;
                CustomToast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });
    }


    /**
     * 网络请求登录
     */

    private void onLogin() {
        RequestParams params = new RequestParams();
        params.put("UserAcount", susername);
        params.put("PassWord", spassword);
        params.put("Type", 2);
        editor = sp.edit();
//        mDialog = LoadingDialogUtil.createLoadingDialog(this, "登录中...", false);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Li("登录-----》" + responseString);
                MyApplication.LOGINCODE = true;

                LoginUpbean loginUpbean = CommonFun.JsonToObj(responseString, LoginUpbean.class);
                if (loginUpbean.getData().getAG_GID() != null) {
                    editor1.putString("yunpu", loginUpbean.getData().getAG_GID());
                    editor1.commit();
                }
                //判断用户是否首次登陆
                if (sp.getString("isHasPattern", "").equals("") && sp.getString("isForgotPassword", "").equals("")) {
                    //提示用户设置密码解锁图案对话框
                    isSetGesturePatternDialog(gson, responseString);
                    //清除首页功能缓存
                    CacheFun.deleteDir();
                }
                //判断该用户是否设置了图案解锁或者切换了正常登陆
                else if (sp.getString("isHasPattern", "").equals("n") || sp.getString("isChangeAcount", "").equals("y")) {
                    //登陆后的相关数据存储并跳转主页
                    afterOnLoginSaveData(gson, responseString);
                    return;
                }
                //判断该用户是否设置图形解锁但却又忘记了解锁密码
                else if (sp.getString("isHasPattern", "").equals("y") && sp.getString("isForgotPassword", "").equals("y")) {
                    //登陆后的相关数据存储并跳转主页
                    afterOnLoginSaveData(gson, responseString);
                    editor.putString("isForgotPassword", "n");
                    editor.putString("isHasPattern", "y");
                    editor.putString("noIgnoreAsked", "n");
                    editor.commit();

                    return;
                } else if (sp.getString("isHasPattern", "").equals("y") || sp.getString("isChangeAcount", "").equals("y")) {
                    //登陆后的相关数据存储并跳转主页
                    afterOnLoginSaveData(gson, responseString);
                    return;
                } else {
                    //登陆后的相关数据存储并跳转主页
                    afterOnLoginSaveData(gson, responseString);
                    return;
                }
            }

            @Override
            public void onFailure(String msg) {
                if (mDialog != null) {
                    LoadingDialogUtil.closeDialog(mDialog);
                    mDialog.dismiss();
                }
                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        .setContentText(msg)
                        .setConfirmText("了解")
                        .show();
            }

            @Override
            public void redirect(String statusCode) {
                MyApplication.BASE_URL = TextChange.subString(4, MyApplication.BASE_URL);
                MyApplication.IMAGE_URL = TextChange.subString(4, MyApplication.IMAGE_URL);
                MyApplication.CTMONEY_URL = TextChange.subString(4, MyApplication.CTMONEY_URL);
                Loging(statusCode);
            }
        };
        callBack.setmAPI("UserManager/Login");
        callBack.setLoadingAnimation(this, "登录中...", false);
        HttpHelper.post(this, HttpAPI.API().LOGIN, params, callBack);
    }

    private void Loging(String str) {
        RequestParams params = new RequestParams();
        params.put("UserAcount", susername);
        params.put("PassWord", spassword);
        params.put("Type", 2);
        editor = sp.edit();
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                LoginUpbean loginUpbean = CommonFun.JsonToObj(responseString, LoginUpbean.class);
                if (loginUpbean.getData().getAG_GID() != null) {
                    editor1.putString("yunpu", loginUpbean.getData().getAG_GID());
                    editor1.commit();
                    Li(sp1.getString("yunpu", "没取到"));
                }
                //判断用户是否首次登陆
                if (sp.getString("isHasPattern", "").equals("") && sp.getString("isForgotPassword", "").equals("")) {
                    //提示用户设置密码解锁图案对话框
                    isSetGesturePatternDialog(gson, responseString);
                }
                //判断该用户是否设置了图案解锁或者切换了正常登陆
                else if (sp.getString("isHasPattern", "").equals("n") || sp.getString("isChangeAcount", "").equals("y")) {
                    //登陆后的相关数据存储并跳转主页
                    afterOnLoginSaveData(gson, responseString);
                    return;
                }
                //判断该用户是否设置图形解锁但却又忘记了解锁密码
                else if (sp.getString("isHasPattern", "").equals("y") && sp.getString("isForgotPassword", "").equals("y")) {
                    //登陆后的相关数据存储并跳转主页
                    afterOnLoginSaveData(gson, responseString);
                    editor.putString("isForgotPassword", "n");
                    editor.putString("isHasPattern", "y");
                    editor.putString("noIgnoreAsked", "n");
                    editor.commit();
                    return;
                } else if (sp.getString("isHasPattern", "").equals("y") || sp.getString("isChangeAcount", "").equals("y")) {
                    //登陆后的相关数据存储并跳转主页
                    afterOnLoginSaveData(gson, responseString);
                    return;
                } else {
                    //登陆后的相关数据存储并跳转主页
                    afterOnLoginSaveData(gson, responseString);
                    return;
                }
            }

            @Override
            public void onFailure(String msg) {
                new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("提示")
                        .setContentText(msg)
                        .setConfirmText("了解")
                        .show();
            }
        };
        HttpHelper.post(this, str, params, callBack);
    }

    /**
     * 提示用户设置密码解锁图案对话框
     * author Yuan Cheng
     * blog yuancheng91.top
     * email jasoncheng9111@163.com
     * created 2017/3/17 17:01
     */
    private void isSetGesturePatternDialog(final Gson gson, final String finalResurt) {
        SweetAlertDialog sweetAlertDialog2;
        sweetAlertDialog2 = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.NORMAL_TYPE);
        sweetAlertDialog2.setTitleText("提示");
        sweetAlertDialog2.setContentText("建议您为应用设置登陆图案密码解锁!");
        sweetAlertDialog2.setConfirmText("好的");
        sweetAlertDialog2.setCancelText("不必，且不再询问");
        sweetAlertDialog2.setCancelable(false);
        sweetAlertDialog2.show();

        //同意设置图案解锁
        sweetAlertDialog2.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                editor.putString("noIgnoreAsked", "n");
                editor.commit();
                //设置图案密码解锁
                setGesturePatternLock(gson, finalResurt);
                sweetAlertDialog.dismiss();
            }
        });

        //不再询问我设置图案解锁
        sweetAlertDialog2.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                editor.putString("isHasPattern", "n");
                editor.putString("isForgotPassword", "n");
                editor.putString("noIgnoreAsked", "y");
                editor.commit();
                //登陆后的相关数据存储并跳转主页
                afterOnLoginSaveData(gson, finalResurt);
                sweetAlertDialog.dismiss();
            }
        });
    }

    /**
     * 设置本地图案解锁密码
     * author Yuan Cheng
     * blog yuancheng91.top
     * email jasoncheng9111@163.com
     * created 2017/3/17 16:00
     */
    private void setGesturePatternLock(Gson gson, String finalResurt) {
        LoginUpbean loginUpbean = CommonFun.JsonToObj(finalResurt, LoginUpbean.class);
        shopname = loginUpbean.getData().getShopList().get(0).getSM_Name();
        creator = loginUpbean.getData().getShopList().get(0).getSM_Creator();
        String shopGid = loginUpbean.getData().getShopList().get(0).getGID();
        String name = loginUpbean.getData().getUM_Creator();
        if (name == null || name.equals("")) {
            myuser = "默认操作员";
        } else {
            myuser = name;
        }
        if (shopname == null || shopname.equals("")) {
            mytitle = "默认店铺";
        } else {
            mytitle = shopname;
        }

        editor1.putString("mytitle", mytitle);
        editor1.putString("myuser", myuser);
        editor1.commit();

        editor.putString("username", susername);

        MyApplication.password = passerword.getText().toString();
        if (cbRemberPW.isChecked()) {
            editor.putString("password", passerword.getText().toString());
            editor.commit();
        }
//        editor.putString("password", passerword.getText().toString());
        editor.putString("shopname", shopname);
        editor.putString("creator", creator);
        editor.putString("shopGid", shopGid);
        editor.putString("isHasPattern", "y");
        editor.putString("isChangeAcount", "n");
        editor.putString("isForgotPassword", "n");
        editor.commit();

        uSharedPreferencesUtiles.put(MyApplication.getmContext(), "Store", shopGid);
        uSharedPreferencesUtiles.put(MyApplication.getmContext(), "ShopName", shopname);
        uSharedPreferencesUtiles.put(MyApplication.getmContext(), "StoreGid", shopGid);
        uSharedPreferencesUtiles.put(MyApplication.getmContext(), "Account_GID", loginUpbean.getData().getGID());
        uSharedPreferencesUtiles.put(MyApplication.getmContext(), "CY_GID", loginUpbean.getData().getCY_GID());
        Intent intent = new Intent(LoginActivity.this, SettingGesturePatternPasswordActivity.class);
        intent.putExtra("shopname", shopname);
        intent.putExtra("creator", creator);
        intent.putExtra("shopGid", shopGid);
        startActivity(intent);
    }

    /**
     * 登陆后的相关数据存储并跳转主页
     *
     * @param gson
     * @param finalResurt 提交登陆信息返回的字节数据
     */
    public void afterOnLoginSaveData(Gson gson, String finalResurt) {
        LoginUpbean loginUpbean = CommonFun.JsonToObj(finalResurt, LoginUpbean.class);
        Le("loginback-----" + finalResurt);
        LoginUpbean.DataBean bean = loginUpbean.getData();
//        getLimit(bean);
        if (bean.getShopID() != null) {
            for (int i = 0; i < bean.getShopList().size(); i++) {
                if (bean.getShopID().equals(bean.getShopList().get(i).getGID())) {
                    shopname = bean.getShopList().get(i).getSM_Name();
                    creator = bean.getShopList().get(i).getSM_Creator();
                    mShopGid = bean.getShopList().get(i).getGID();
                    mAcountname = bean.getUM_Name();
                    mShopePicture = bean.getShopList().get(i).getSM_Picture();
                    break;
                }
            }

        } else {
            shopname = loginUpbean.getData().getShopList().get(0).getSM_Name();
            creator = loginUpbean.getData().getShopList().get(0).getSM_Creator();
            mShopGid = loginUpbean.getData().getShopList().get(0).getGID();
            mAcountname = loginUpbean.getData().getUM_Name();
            mShopePicture = bean.getShopList().get(0).getSM_Picture();
        }
        String name = loginUpbean.getData().getUM_Creator();
        if (name == null || name.equals("")) {
            myuser = "默认操作员";
        } else {
            myuser = name;
        }
        if (shopname == null || shopname.equals("")) {
            mytitle = "默认店铺";
        } else {
            mytitle = shopname;
        }

        editor1.putString("mytitle", mytitle);
        editor1.putString("myuser", myuser);
        editor1.commit();

        editor.putString("username", susername);
        MyApplication.password = passerword.getText().toString();
        if (cbRemberPW.isChecked()) {
            editor.putString("password", passerword.getText().toString());
            editor.commit();
        }
        editor.putString("isChangeAcount", "n");
        editor.commit();

        CacheData.deleteDir();//清空保存到本地的商品信息和会员等级信息及权限信息

        /**
         * 若不是超管，将登录数据保存到本地备用
         */
        if (loginUpbean.getData().getMenuInfoList() != null) {
            CacheData.saveObject("login", loginUpbean);
        }
        CacheData.saveObject("LG", loginUpbean);
        if (!mAccount.equals(username.getText().toString())) {
            sp.edit().putString("isHasPattern", "n").apply();
        }

        //保存账号GID和企业GID到本地，供消息中心使用
        uSharedPreferencesUtiles.put(MyApplication.getmContext(), "Store", mShopGid);
        uSharedPreferencesUtiles.put(MyApplication.getmContext(), "mAcountname", mAcountname);
        uSharedPreferencesUtiles.put(MyApplication.getmContext(), "ShopName", shopname);
        uSharedPreferencesUtiles.put(MyApplication.getmContext(), "StoreGid", mShopGid);
        uSharedPreferencesUtiles.put(MyApplication.getmContext(), "Account_GID", loginUpbean.getData().getGID());
        uSharedPreferencesUtiles.put(MyApplication.getmContext(), "CY_GID", loginUpbean.getData().getCY_GID());
        sp.edit().putBoolean("exit_login", false).apply();
//        getSystemSwitch();//获取系统开关
//        getPrintSet();//获取打印开关
        getSmsSet();//获取短信开关

        GetPrintSet.getPrintParamSet();
//        GetPrintSet.getPrintSet();
        getAllMessage();

        if (mDialog != null) {
            LoadingDialogUtil.closeDialog(mDialog);
            mDialog.dismiss();
        }
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("shopname", shopname);
        intent.putExtra("creator", creator);
        intent.putExtra("shopGid", mShopGid);
        startActivity(intent);
        finish();


    }


    /**
     * 初始化视图
     */
    private void initView() {
        Iv_head = findViewById(R.id.iv_logo);
        tv_appname = findViewById(R.id.tv_appname);
        tv_clean_acount_loginactivity = findViewById(R.id.tv_clean_acount_loginactivity);
        tologin = (Button) findViewById(R.id.btn_tologin);
        tvisacc = findViewById(R.id.tv_isacc);
        tosignup = (TextView) findViewById(R.id.tv_tosignup);
        username = (EditText) findViewById(R.id.et_username_loginactivity);
        passerword = (EditText) findViewById(R.id.et_password_loginactivity);
        nopassword = (TextView) findViewById(R.id.tv_nopassword);
        mCleanPassword = findViewById(R.id.tv_clean_password);
        tvCopyRight = findViewById(R.id.tv_copyright);
        cbRemberPW = findViewById(R.id.cb_rember_pw);
        Display display = getWindowManager().getDefaultDisplay();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) Iv_head.getLayoutParams();
        params.height = display.getWidth() * 19 / 20;//设置当前控件布局的高度
        Iv_head.setLayoutParams(params);//将设置好的布局参数应用到控件中

        if (MyApplication.IS_YUNSHANGPU) {
            tvCopyRight.setText(MyApplication.LOGIN_VERSION_YSP);
            Iv_head.setImageResource(R.mipmap.yunshangpulogo);
            tv_appname.setText("云上铺会员管理收银系统");
            tosignup.setVisibility(View.VISIBLE);
            tvisacc.setVisibility(View.VISIBLE);
        } else {
            tvCopyRight.setText(MyApplication.LOGIN_VERSION_ZX);
            Iv_head.setImageResource(R.mipmap.zxlogo);
            tv_appname.setText("会员管理收银系统");
            tosignup.setVisibility(View.GONE);
            tvisacc.setVisibility(View.GONE);
        }
        dataHandler = new upDataHandler();

    }

    public String getShopname() {
        return shopname;
    }

    public String getMyuser() {
        return myuser;
    }


    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

//    /**
//     * 查询用户数
//     */
//    private void getLimit(final LoginUpbean.DataBean loginUpbean) {
//
//        RequestParams params = new RequestParams();
//        params.put("RM_GID", "");
//        HttpHelper.post(LoginActivity.this, HttpAPI.API().QUERY_USER_NUM, params, new CallBack() {
//            @Override
//            public void onSuccess(String responseString, Gson gson) {
//                UserNumEntity entity = CommonFun.JsonToObj(responseString, UserNumEntity.class);
//                String tel = loginUpbean.getUM_Acount();
//                for (int i = 0; i < entity.getData().size(); i++) {
//                    if (entity.getData().get(i).getUM_Acount().equals(tel)) {
//                        saveAppLimit(entity.getData().get(i).getRoleID());
//                        return;
//                    }
//                }
//            }
//
//            @Override
//
//            public void onFailure(String msg) {
//            }
//
//        });
//    }

//    /**
//     * @param RoleGid ,用GID；
//     *                查询当前登录用户的权限并保存
//     */
//    private void saveAppLimit(String RoleGid) {
//        RequestParams params = new RequestParams();
//        params.put("RM_GID", RoleGid);
//        CallBack callBack = new CallBack() {
//            @Override
//            public void onSuccess(String responseString, Gson gson) {
//                AppLimitBean bean = CommonFun.JsonToObj(responseString, AppLimitBean.class);
//                CacheData.saveObject("applimit", bean);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//
//            }
//        };
//        callBack.setmAPI("APPMenuRoleRelation/QueryAppMenuList");
//        HttpHelper.post(LoginActivity.this, HttpAPI.API().APP_LIMIT, params,callBack);
//    }


    /**
     * 获取短信开关
     */
    private void getSmsSet() {
        HttpHelper.post(LoginActivity.this, HttpAPI.API().SMS_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SmsSwitch bean = CommonFun.JsonToObj(responseString, SmsSwitch.class);
                if (bean.isSuccess() && bean.getData().size() > 0) {
                    if (bean.getData().get(1).getST_State() == null || !bean.getData().get(1).getST_State().equals("1")) {
                        MyApplication.SMS_IS_OPEN = true;
                    } else {
                        MyApplication.SMS_IS_OPEN = false;
                    }
                    CacheData.saveObject("shortmessage", bean);//缓存短信开关到本地
                }
                Li("获取短信开关成功");
            }

            @Override
            public void onFailure(String msg) {
                Li("获取短信开关" + msg);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpHelper.getmClient().cancelRequests(LoginActivity.this, true);

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
     * 商米授权
     */
    private void shangmiAuthorize() {
        if (MyApplication.IS_SUNMI_POS_DEVICE) {
            String sumSnNum = getSN();
            SunMiBean sunMiBean = (SunMiBean) CacheData.restoreObject("sunmi_authorize");
            //判断商米是否缓存
            if (sunMiBean != null && sunMiBean.getSnCode().equals(sumSnNum) && sunMiBean.getCode() == 200) {
                isPermission = true;
                loginJudge();
            } else {
                //商米授权
                getSNPermission();
            }
        } else {
            isPermission = true;
            loginJudge();
        }
    }

    /**
     * 商米授权失败
     */
    private void notLogin() {
        //登陆
        tologin.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                CustomToast.makeText(LoginActivity.this, "当前设备尚未授权请联系客服。", Toast.LENGTH_SHORT).show();
                getSNPermission();
            }
        });
    }

    /**
     * 登录判断
     */
    private void loginJudge() {
        //登陆
        tologin.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                //登陆
                if (!TextUtils.isEmpty(username.getText())) {
                    susername = username.getText().toString();
                    if (!TextUtils.isEmpty(passerword.getText()) && passerword.getText().toString().length() > 5 && passerword.getText().toString().length() < 21) {
                        spassword = passerword.getText().toString();
                        if (!getPermission(LoginActivity.this)) {
                            AlertDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.NORMAL_TYPE);
                            AlertDialog.setTitleText("提示");
                            AlertDialog.setContentText("请在您手机设置中，开启读写文件权限");
                            AlertDialog.setConfirmText("好的");
                            AlertDialog.setCancelable(false);
                            AlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    AlertDialog.dismiss();
                                    //ActivityCompat.requestPermissions(LoginActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                                }
                            });
                            AlertDialog.show();
                        } else {
                            onLogin();
                        }
                    } else {
                        CustomToast.makeText(LoginActivity.this, "请输入6-20位密码", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    CustomToast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
