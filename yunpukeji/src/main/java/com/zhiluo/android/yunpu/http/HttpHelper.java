package com.zhiluo.android.yunpu.http;

import android.content.Context;
import android.os.Environment;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.utils.CacheData;

import java.io.File;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 作者：罗咏哲 on 2017/10/10 11:03.
 * 邮箱：137615198@qq.com
 */

public class HttpHelper {

    private static final AsyncHttpClient mClient = new AsyncHttpClient();

    public static AsyncHttpClient getmClient() {
        return mClient;
    }

    /**
     * @param context
     * @param api
     * @param callBack
     */
    public static void post(Context context, String api, CallBack callBack) {
        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        mClient.setCookieStore(cookieStore);

        if (api.contains("http")) {
            mClient.post(api, callBack);
        } else {
            mClient.post(MyApplication.BASE_URL + api, callBack);
        }


        Li(context + "======api=====post=========random:" + api);
    }

//    /**
//     * @param context
//     * @param api
//     * @param callBack
//     */
//    public static void post1(Context context, String api, methord f1) {
//        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
//        mClient.setCookieStore(cookieStore);
//        CallBack callBack = new CallBack() {
//            @Override
//            public void onSuccess(String responseString, Gson gson) {
//
//            }
//        }
//        if (api.contains("http")) {
//            mClient.post(api, callBack);
//        } else {
//            mClient.post(MyApplication.BASE_URL + api, callBack);
//        }
//    }


    /**
     * @param context
     * @param api
     * @param params
     * @param callBack
     */
    public static void post(Context context, String api, RequestParams params, CallBack callBack) {
        PersistentCookieStore cookieStore = new PersistentCookieStore(context);
        mClient.setCookieStore(cookieStore);
        if (api.contains("http")) {
            mClient.post(api, params, callBack);
        } else {
            mClient.post(MyApplication.BASE_URL + api, params, callBack);
        }
    }


    /**
     * 下载打印模板Logo、二维码
     *
     * @param str  下载地址
     * @param type 模板类型
     * @param b    二维码还是Logo true：Logo  false:二维码
     */
    public static void getBitmap(final String str, final String type, final boolean b) {
        Li(type + "   " + b + "===下载打印模板Logo=====str====random:" + str);
//        mClient.get(str, new BinaryHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] result) {
//                switch (type) {
//                    //会员充值
//                    case "HYCZ":
//                        if (b) {
//                            MyApplication.HYCZ_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.HYCZ_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //会员充次
//                    case "HYCC":
//                        if (b) {
//                            MyApplication.HYCC_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.HYCC_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    // 商品消费
//                    case "SPXF":
//                        if (b) {
//                            MyApplication.SPXF_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.SPXF_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //快速消费
//                    case "KSXF":
//                        if (b) {
//                            MyApplication.KSXF_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.KSXF_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //计次消费
//                    case "JCXF":
//                        if (b) {
//                            MyApplication.JCXF_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.JCXF_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //积分兑换
//                    case "JFDH":
//                        if (b) {
//                            MyApplication.JFDH_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.JFDH_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //商品退货
//                    case "SPTH":
//                        if (b) {
//                            MyApplication.SPTH_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.SPTH_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //交班
//                    case "JB":
//                        if (b) {
//                            MyApplication.JB_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.JB_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //房台消费
//                    case "FTXF":
//                        if (b) {
//                            MyApplication.FTXF_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.FTXF_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //刷卡登记
//                    case "HYDJ":
//                        if (b) {
//                            MyApplication.HYSK_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.HYSK_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //限时消费
//                    case "XSXF":
//                        if (b) {
//                            MyApplication.XSXF_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.XSXF_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //商品入库
//                    case "RK":
//                        if (b) {
//                            MyApplication.RK_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.RK_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //商品出库
//                    case "CK":
//                        if (b) {
//                            MyApplication.CK_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.CK_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//
//                    //会员开卡
//                    case "HYKK":
//                        if (b) {
//                            MyApplication.OPENCARD_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.OPENCARD_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//                    //一键加油
//                    case "YJJY":
//                        if (b) {
//                            MyApplication.YJJY_LOGO = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        } else {
//                            MyApplication.YJJY_QR = BitmapFactory.decodeByteArray(result, 0, result.length);
//                        }
//                        break;
//
//                    default:
//                        break;
//                }

        OkGo.<File>get(str)
                .tag(MyApplication.getmContext())
                .execute(new FileCallback(Environment.getExternalStorageDirectory() + "/VipManage", type + "_" + b + ".png") {
                    @Override
                    public void onSuccess(Response<File> response) {
                        switch (type) {
                            //会员充值
                            case "HYCZ":
                                if (b) {
                                    MyApplication.HYCZ_LOGO = "yes_file";
                                    CacheData.saveObject("HYCZ_LOGO", response.body());
                                } else {
                                    MyApplication.HYCZ_QR = "yes_file";
                                    CacheData.saveObject("HYCZ_QR", response.body());
                                }
                                break;
                            //会员充次
                            case "HYCC":
                                if (b) {
                                    MyApplication.HYCC_LOGO = "yes_file";
                                    CacheData.saveObject("HYCC_LOGO", response.body());
                                } else {
                                    MyApplication.HYCC_QR = "yes_file";
                                    CacheData.saveObject("HYCC_QR", response.body());
                                }
                                break;
                            // 商品消费
                            case "SPXF":
                                if (b) {
                                    MyApplication.SPXF_LOGO = "yes_file";
                                    CacheData.saveObject("SPXF_LOGO", response.body());
                                } else {
                                    MyApplication.SPXF_QR = "yes_file";
                                    CacheData.saveObject("SPXF_QR", response.body());
                                }
                                break;
                            //快速消费
                            case "KSXF":
                                if (b) {
                                    MyApplication.KSXF_LOGO = "yes_file";
                                    CacheData.saveObject("KSXF_LOGO", response.body());
                                } else {
                                    MyApplication.KSXF_QR = "yes_file";
                                    CacheData.saveObject("KSXF_QR", response.body());
                                }
                                break;
                            //计次消费
                            case "JCXF":
                                if (b) {
                                    MyApplication.JCXF_LOGO = "yes_file";
                                    CacheData.saveObject("JCXF_LOGO", response.body());
                                } else {
                                    MyApplication.JCXF_QR = "yes_file";
                                    CacheData.saveObject("JCXF_QR", response.body());
                                }
                                break;
                            //积分兑换
                            case "JFDH":
                                if (b) {
                                    MyApplication.JFDH_LOGO = "yes_file";
                                    CacheData.saveObject("JFDH_LOGO", response.body());
                                } else {
                                    MyApplication.JFDH_QR = "yes_file";
                                    CacheData.saveObject("JFDH_QR", response.body());
                                }
                                break;
                            //商品退货
                            case "SPTH":
                                if (b) {
                                    MyApplication.SPTH_LOGO = "yes_file";
                                    CacheData.saveObject("SPTH_LOGO", response.body());
                                } else {
                                    MyApplication.SPTH_QR = "yes_file";
                                    CacheData.saveObject("SPTH_QR", response.body());
                                }
                                break;
                            //交班
                            case "JB":
                                if (b) {
                                    MyApplication.JB_LOGO = "yes_file";
                                    CacheData.saveObject("JB_LOGO", response.body());
                                } else {
                                    MyApplication.JB_QR = "yes_file";
                                    CacheData.saveObject("JB_QR", response.body());
                                }
                                break;
                            //房台消费
                            case "FTXF":
                                if (b) {
                                    MyApplication.FTXF_LOGO = "yes_file";
                                    CacheData.saveObject("FTXF_LOGO", response.body());
                                } else {
                                    MyApplication.FTXF_QR = "yes_file";
                                    CacheData.saveObject("FTXF_QR", response.body());
                                }
                                break;
                            //刷卡登记
                            case "HYDJ":
                                if (b) {
                                    MyApplication.HYSK_LOGO = "yes_file";
                                    CacheData.saveObject("HYSK_LOGO", response.body());
                                } else {
                                    MyApplication.HYSK_QR = "yes_file";
                                    CacheData.saveObject("HYSK_QR", response.body());
                                }
                                break;
                            //限时消费
                            case "XSXF":
                                if (b) {
                                    MyApplication.XSXF_LOGO = "yes_file";
                                    CacheData.saveObject("XSXF_LOGO", response.body());
                                } else {
                                    MyApplication.XSXF_QR = "yes_file";
                                    CacheData.saveObject("XSXF_QR", response.body());
                                }
                                break;
                            //商品入库
                            case "RK":
                                if (b) {
                                    MyApplication.RK_LOGO = "yes_file";
                                    CacheData.saveObject("RK_LOGO", response.body());
                                } else {
                                    MyApplication.RK_QR = "yes_file";
                                    CacheData.saveObject("RK_QR", response.body());
                                }
                                break;
                            //商品出库
                            case "CK":
                                if (b) {
                                    MyApplication.CK_LOGO = "yes_file";
                                    CacheData.saveObject("CK_LOGO", response.body());
                                } else {
                                    MyApplication.CK_QR = "yes_file";
                                    CacheData.saveObject("CK_QR", response.body());
                                }
                                break;
                            //会员开卡
                            case "HYKK":
                                if (b) {
                                    MyApplication.OPENCARD_LOGO = "yes_file";
                                    CacheData.saveObject("OPENCARD_LOGO", response.body());
                                } else {
                                    MyApplication.OPENCARD_QR = "yes_file";
                                    CacheData.saveObject("OPENCARD_QR", response.body());
                                }
                                break;
                            //一键加油
                            case "YJJY":
                                if (b) {
                                    MyApplication.YJJY_LOGO = "yes_file";
                                    CacheData.saveObject("YJJY_LOGO", response.body());
                                } else {
                                    MyApplication.YJJY_QR = "yes_file";
                                    CacheData.saveObject("YJJY_QR", response.body());
                                }
                                break;
                            default:
                                break;
                        }
                    }
                });

    }


}
