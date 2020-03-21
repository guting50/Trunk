package com.zhiluo.android.yunpu.home.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.HttpGet;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.BuildConfig;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.HomeBean;
import com.zhiluo.android.yunpu.home.jsonbean.UpdateJsonBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.activity.GetVersion;
import com.zhiluo.android.yunpu.login.activity.LoginActivity;
import com.zhiluo.android.yunpu.setting.gesture.GesturePasswordSettingActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheFun;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DownloadUtils;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

import static com.zhiluo.android.yunpu.config.MyApplication.IS_LAKALA_POS_DEVICE;
import static com.zhiluo.android.yunpu.config.MyApplication.IS_SUNMI_POS_P1_DEVICE;
import static com.zhiluo.android.yunpu.config.MyApplication.VICODE;

/**
 * 系统设置
 */
public class SystemSettingActivity extends BaseActivity {
    @Bind(R.id.tv_system_setting_version_code)
    TextView tvVersionCode;
    @Bind(R.id.rl_system_setting_check_update)
    RelativeLayout rlCheckUpdate;
    private TextView ivback;
    //    private RelativeLayout relativeLayout;
    private RelativeLayout rl_gesture_password_set;
    private String mDownloadAddr;//下载地址
    private DownloadUtils mDownloadUtils;
    private String mVersion;
    private SweetAlertDialog mSweetAlertDialog;
    private ProgressDialog pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initview();
        click();
    }

    private void click() {
        rl_gesture_password_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SystemSettingActivity.this, GesturePasswordSettingActivity.class));
            }
        });
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rlCheckUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkVersion();
            }
        });
        findViewById(R.id.rl_system_setting_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSweetAlertDialog = new SweetAlertDialog(SystemSettingActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("恢复默认设置");
                mSweetAlertDialog.setContentText("仅限于恢复首页功能菜单显示默认设置");
                mSweetAlertDialog.setCancelText("取消");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        CacheFun.deleteDir();
                        EventBus.getDefault().post(new HomeBean());
                        mSweetAlertDialog.dismiss();
                    }
                });
                        mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                    }
                });
                mSweetAlertDialog.show();
            }
        });
    }

    private void initview() {
        rl_gesture_password_set = (RelativeLayout) findViewById(R.id.rl_gesture_password_set);
        ivback = (TextView) findViewById(R.id.tv_back_activity);
        tvVersionCode.setText("版本号：" + getVersionName());
    }

    private String getVersionName() {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String version = packInfo.versionName;
            return version;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 检查版本更新
     */
    private void checkVersion() {
        CallBack callBack = new CallBack(){
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final UpdateJsonBean bean = CommonFun.JsonToObj(responseString, UpdateJsonBean.class);
                mVersion = bean.getData().getVA_Version();
                if (Double.parseDouble(bean.getData().getVA_Version()) > VICODE) {
                    String str = MyApplication.CTMONEY_URL.substring(0, MyApplication.CTMONEY_URL.length() - 1);
                    mDownloadAddr = str + bean.getData().getVA_VersionAddress();
                    if (IS_LAKALA_POS_DEVICE) {
                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(SystemSettingActivity.this, SweetAlertDialog.NORMAL_TYPE);
                        sweetAlertDialog.setTitleText("有新版本啦！");
                        sweetAlertDialog.setContentText("检测到新版本，请前往应用商店下载更新！");
                        sweetAlertDialog.setConfirmText("确定");
                        sweetAlertDialog.setCanceledOnTouchOutside(true);
                        sweetAlertDialog.setCancelable(true);
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                uSharedPreferencesUtiles.put(SystemSettingActivity.this, "VER", bean.getData().getVA_Version());
                                sweetAlertDialog.dismiss();
                            }
                        });
                        sweetAlertDialog.show();
                    } else if (IS_SUNMI_POS_P1_DEVICE) {

                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(SystemSettingActivity.this, SweetAlertDialog.NORMAL_TYPE);
                        sweetAlertDialog.setTitleText("有新版本啦！");
                        sweetAlertDialog.setContentText("检测到新版本，请前往应用商店下载更新！");
                        sweetAlertDialog.setConfirmText("确定");
                        sweetAlertDialog.setCanceledOnTouchOutside(true);
                        sweetAlertDialog.setCancelable(true);
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                uSharedPreferencesUtiles.put(SystemSettingActivity.this, "VER", bean.getData().getVA_Version());
                                sweetAlertDialog.dismiss();
                            }
                        });
                        sweetAlertDialog.show();
                    } else {
                        mSweetAlertDialog = new SweetAlertDialog(SystemSettingActivity.this, SweetAlertDialog.NORMAL_TYPE);
                        mSweetAlertDialog.setTitleText("检查新版本");
                        mSweetAlertDialog.setConfirmText("更新");
                        mSweetAlertDialog.setCancelText("下次再说");
                        mSweetAlertDialog.setContentText("有新版本了");
                        mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mDownloadUtils = new DownloadUtils(SystemSettingActivity.this);
                                //DownloadUtils.downloadAPK(mDownloadAddr, Constant.APP_NAME);
                                downFile(mDownloadAddr);
                                upLoadUpdateTimes(mVersion);
                                mSweetAlertDialog.dismiss();
                            }
                        });
                        mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                            }
                        });
                        mSweetAlertDialog.show();
                    }
                } else {
                    CustomToast.makeText(SystemSettingActivity.this, "当前已是最新版本了", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(SystemSettingActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(SystemSettingActivity.this, "正在检查新版本...", false);
        HttpHelper.post(this, MyApplication.CTMONEY_URL + GetVersion.upApi, callBack);
    }

    /**
     * 上传APP更新状态
     *
     * @param version
     */
    public void upLoadUpdateTimes(String version) {
        RequestParams params = new RequestParams();
        params.put("Version", version);
        HttpHelper.post(this, HttpAPI.API().UPDATE_TIMES, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Log.i("lyz", "上次APP更新状态 成功");
            }

            @Override
            public void onFailure(String msg) {
                Log.i("lyz", "上次APP更新状态 失败" + msg);
            }
        });
    }

    /**
     * 从远程下载升级版本的app文件
     *
     * @param address
     */
    private void downFile(final String address) {
        pBar = new ProgressDialog(this);    //进度条，在下载的时候实时更新进度，提高用户友好度
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setTitle("正在下载...");
        pBar.setMessage("下载中，请不要进行其它操作");
        pBar.setCanceledOnTouchOutside(false);
        pBar.setCancelable(false);
        pBar.setProgress(0);
        pBar.show();
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(address);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    int length = (int) entity.getContentLength();   //获取文件大小
                    pBar.setMax(length);                            //设置进度条的总长度
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                       // File file = new File(Environment.getExternalStorageDirectory(), "vip.apk");
                        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "vip.apk");
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];   //这个是缓冲区
                        int ch = -1;
                        int process = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            process += ch;
                            pBar.setProgress(process);       //这里就是关键的实时更新进度了！
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    void down() {
        handler1.post(new Runnable() {
            public void run() {
                pBar.cancel();
                update();
            }
        });
    }

    private Handler handler1 = new Handler();
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

}
