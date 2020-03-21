package com.zhiluo.android.yunpu.login.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.HttpGet;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.home.jsonbean.UpdateJsonBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

import static com.zhiluo.android.yunpu.config.MyApplication.IS_LAKALA_POS_DEVICE;
import static com.zhiluo.android.yunpu.config.MyApplication.IS_SUNMI_POS_DEVICE;
import static com.zhiluo.android.yunpu.config.MyApplication.IS_SUNMI_POS_P1_DEVICE;
import static com.zhiluo.android.yunpu.config.MyApplication.VICODE;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ZPH on 2019-01-22.
 */

public class GetVersion {

    private static String mVersionRemark, mDownloadAddr;//版本升级备注信息,下载地址
    public static boolean b;
    private static ProgressDialog pBar;
    private Dialog mDialog;
    public static String upApi = "AppUpgrade/GetNewsVersion";
    private static SweetAlertDialog sweetAlertDialog;
    public static LoginActivity.upDataHandler upDataHandler1;
    public static ConfirmGesturePatternPasswordActivity.upDataHandler upDataHandler2;
    private static int type = 1;


    public static void getVersion(final Context context, LoginActivity.upDataHandler handler) {
        upDataHandler1 = handler;
        type = 1;
        getVersion2(context);

    }

    public static void getVersion(final Context context, ConfirmGesturePatternPasswordActivity.upDataHandler handler) {
        upDataHandler2 = handler;
        type = 2;
        getVersion2(context);
    }

    /**
     * 获取版本号
     */
    public static void getVersion2(final Context context) {


        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final UpdateJsonBean bean = CommonFun.JsonToObj(responseString, UpdateJsonBean.class);

                if (Double.parseDouble(bean.getData().getVA_Version()) > VICODE) {
                    mVersionRemark = bean.getData().getVA_Remark();
                    String str = MyApplication.CTMONEY_URL.substring(0, MyApplication.CTMONEY_URL.length() - 1);
                    b = false;
                    if (b) {
                        str = "http://core.yunvip123.com";
                    }

                    Li("=======getVersion  app 地址========random:" + bean.getData().getVA_VersionAddress());
                    if (bean.getData().getVA_VersionAddress().startsWith("/")) {
                        Li("=======getVersion  app 地址=====/===random:");
                        mDownloadAddr = "http://core.yunvip123.com" + bean.getData().getVA_VersionAddress();
                    } else {
                        mDownloadAddr = bean.getData().getVA_VersionAddress();
                    }

//                    mDownloadAddr = str + bean.getData().getVA_VersionAddress();
                    String version = (String) uSharedPreferencesUtiles.get(MyApplication.getmContext(), "VER", "");

                    if (IS_LAKALA_POS_DEVICE) {
                        if (!version.equals(bean.getData().getVA_Version())) {
                            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
                            sweetAlertDialog.setTitleText("有新版本啦！");
                            sweetAlertDialog.setContentText("检测到新版本，请前往应用商店下载更新！");
                            sweetAlertDialog.setConfirmText("确定");
                            sweetAlertDialog.setCanceledOnTouchOutside(true);
                            sweetAlertDialog.setCancelable(true);
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    uSharedPreferencesUtiles.put(context, "VER", bean.getData().getVA_Version());
                                    sweetAlertDialog.dismiss();
                                }
                            });
                            sweetAlertDialog.show();
                        }
                    } else if (IS_SUNMI_POS_DEVICE) {
                        if (!version.equals(bean.getData().getVA_Version())) {
                            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
                            sweetAlertDialog.setTitleText("有新版本啦！");
                            sweetAlertDialog.setContentText("检测到新版本，请前往应用商店下载更新！");
                            sweetAlertDialog.setConfirmText("确定");
                            sweetAlertDialog.setCanceledOnTouchOutside(true);
                            sweetAlertDialog.setCancelable(true);
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    uSharedPreferencesUtiles.put(context, "VER", bean.getData().getVA_Version());
                                    sweetAlertDialog.dismiss();
                                }
                            });
                            sweetAlertDialog.show();
                        }
                    } else {
                        test(bean.getData().getVA_UpdateMechanism(), bean.getData().getVA_Version(), context);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MyApplication.getmContext(), "网络错误，请检查网络是否连接！", Toast.LENGTH_SHORT);
            }

            @Override
            public void redirect(String statusCode) {
                updateLoging(statusCode, context);
            }
        };
        callBack.setmAPI(upApi);
        HttpHelper.post(context, MyApplication.CTMONEY_URL + upApi, callBack);//正式
//        HttpHelper.post(context, "http://cttest.zhiluovip.com", callBack);//测试
    }

    private static void updateLoging(String str, final Context context) {

        HttpHelper.post(context, str, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final UpdateJsonBean bean = CommonFun.JsonToObj(responseString, UpdateJsonBean.class);
                if (Double.parseDouble(bean.getData().getVA_Version()) > VICODE) {
                    mVersionRemark = bean.getData().getVA_Remark();
                    String str = MyApplication.CTMONEY_URL.substring(0, MyApplication.CTMONEY_URL.length() - 1);
                    b = true;
                    if (b) {
                        str = "http://core.yunvip123.com";
                    }
                    mDownloadAddr = str + bean.getData().getVA_VersionAddress();
                    String version = (String) uSharedPreferencesUtiles.get(MyApplication.getmContext(), "VER", "");

                    if (IS_LAKALA_POS_DEVICE) {
                        if (!version.equals(bean.getData().getVA_Version())) {
                            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
                            sweetAlertDialog.setTitleText("有新版本啦！");
                            sweetAlertDialog.setContentText("检测到新版本，请前往应用商店下载更新！");
                            sweetAlertDialog.setConfirmText("确定");
                            sweetAlertDialog.setCanceledOnTouchOutside(true);
                            sweetAlertDialog.setCancelable(true);
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    uSharedPreferencesUtiles.put(context, "VER", bean.getData().getVA_Version());
                                    sweetAlertDialog.dismiss();
                                }
                            });
                            sweetAlertDialog.show();
                        }
                    } else if (IS_SUNMI_POS_P1_DEVICE) {
                        if (!version.equals(bean.getData().getVA_Version())) {
                            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
                            sweetAlertDialog.setTitleText("有新版本啦！");
                            sweetAlertDialog.setContentText("检测到新版本，请前往应用商店下载更新！");
                            sweetAlertDialog.setConfirmText("确定");
                            sweetAlertDialog.setCanceledOnTouchOutside(true);
                            sweetAlertDialog.setCancelable(true);
                            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    uSharedPreferencesUtiles.put(context, "VER", bean.getData().getVA_Version());
                                    sweetAlertDialog.dismiss();
                                }
                            });
                            sweetAlertDialog.show();
                        }
                    } else {
                        test(bean.getData().getVA_UpdateMechanism(), bean.getData().getVA_Version(), context);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MyApplication.getmContext(), msg, Toast.LENGTH_SHORT);
            }
        });
    }


    /**
     * 检测是否需要升级
     */
    private static void test(int isUpdate, final String v, final Context context) {
        String ver = (String) uSharedPreferencesUtiles.get(MyApplication.getmContext(), "VER", "");
        sweetAlertDialog = new SweetAlertDialog(context, SweetAlertDialog.NORMAL_TYPE);
        sweetAlertDialog.setTitleText("有新版本啦！");
        sweetAlertDialog.setContentType();
        sweetAlertDialog.setContentText(mVersionRemark);
        sweetAlertDialog.setCancelable(false);
        sweetAlertDialog.setConfirmText("立即升级");
        if (isUpdate == 1) {
            sweetAlertDialog.setCancelText("下次再说");
            sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    uSharedPreferencesUtiles.put(context, "VER", v);
                    sweetAlertDialog.dismiss();
                }
            });

        }
        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    downFile(mDownloadAddr, context);     //在下面的代码段
                    RequestParams params = new RequestParams();
                    params.put("Version", v);
                    uSharedPreferencesUtiles.put(context, "VER", v);
                    upLoadUpdateTimes(params, context);//上传APP更新状态
                    sweetAlertDialog.dismiss();
                } else {
                    SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE);
                    dialog.setTitleText("提示");
                    dialog.setContentText("SD卡不可用，请插入SD卡");
                    dialog.setConfirmText("了解");
                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        });
//        if ((isUpdate == 1 && !ver.equals(v)) || isUpdate != 1) {


//        //判断用户是否设置了图案密码
//        if (sp.getString("isHasPattern", "").equals("y")
//                && sp.getString("isForgotPassword", "").equals("n")
//                && sp.getString("isChangeAcount", "").equals("n")) {
//
//        } else {
//            sweetAlertDialog.show();
//        }
        sweetAlertDialog.show();


        //每次都提示更新
    }

    /**
     * 从远程下载升级版本的app文件
     *
     * @param address
     */
    private static void downFile(final String address, final Context context) {
        pBar = new ProgressDialog(context);    //进度条，在下载的时候实时更新进度，提高用户友好度
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setTitle("正在下载");
        pBar.setMessage("请稍候...");
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
                    pBar.setMax(length / 1024 / 1024);                            //设置进度条的总长度
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                MyApplication.APK_ADDDRESS,
                                "vip.apk");
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];   //这个是缓冲区
                        int ch = -1;
                        int process = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            process += ch;
                            pBar.setProgress(process / 1024 / 1024);       //这里就是关键的实时更新进度了！
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


    static void down() {
        handler1.post(new Runnable() {
            public void run() {
                pBar.cancel();
                if (type == 1) {
                    upDataHandler1.sendEmptyMessage(1);
                } else if (type == 2) {
                    upDataHandler2.sendEmptyMessage(1);
                }

//                update(context);
            }
        });
    }


    private static Handler handler1 = new Handler();


    public static void upLoadUpdateTimes(final RequestParams params, final Context context) {
        HttpHelper.post(context, HttpAPI.API().UPDATE_TIMES, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Log.i("lyz", "上次APP更新状态 成功");
            }

            @Override
            public void onFailure(String msg) {
                Log.i("lyz", "上次APP更新状态 失败" + msg);
            }

            @Override
            public void redirect(String statusCode) {
                up(params, context);
            }
        });
    }

    private static void up(RequestParams params, Context context) {
        HttpHelper.post(context, HttpAPI.API().UPDATE_TIMES, params, new CallBack() {
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
}
