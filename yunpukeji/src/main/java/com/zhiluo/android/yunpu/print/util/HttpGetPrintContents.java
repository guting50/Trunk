package com.zhiluo.android.yunpu.print.util;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.consume.activity.PayConfirmActivity;
import com.zhiluo.android.yunpu.consume.activity.UnionPayActivity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.consume.activity.IntegralExchangeActivity;
import com.zhiluo.android.yunpu.member.consume.activity.MemberChargePayActivity;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.print.bean.CK_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.JB_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_HYCC_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_HYCZ_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_HYKK_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_JCXF_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_JFDH_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_KSXF_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_SPTH_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_SPXF_Bean;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.print.bean.Print_YJJY_Bean;
import com.zhiluo.android.yunpu.print.bean.RK_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.XSXF_Success_Bean;
import com.zhiluo.android.yunpu.statistics.order.activity.GoodsRetureActivity;
import com.zhiluo.android.yunpu.ui.activity.handduty.HandDutyActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.PrinterUtils;
import java.io.UnsupportedEncodingException;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;


/**
 * 获取打印参数
 * 作者：罗咏哲 on 2017/7/26 21:04.
 * 邮箱：137615198@qq.com
 */

public class HttpGetPrintContents {
    private Context mContext;
    private String mGID;
    private Gson gson;
    private RequestParams params;
    private int mPrintNum;
    private String SM_GID = "";
    private PayConfirmActivity.IntentHandler PintentHandler;
    private UnionPayActivity.IntentHandler UintentHandler;
    private AddMemberActivity.IntentHandler AintentHandler;
    private MemberChargePayActivity.IntentHandler MintentHandler;


    public HttpGetPrintContents(Context context, int printNum, String gid,PayConfirmActivity.IntentHandler intentHandler) {
        this.mContext = context;
        this.mGID = gid;
        this.mPrintNum = printNum;
        this.gson = new Gson();
        this.PintentHandler = intentHandler;

        params = new RequestParams();
        params.put("OrderGID", mGID);//订单GID
    }

    public HttpGetPrintContents(Context context, int printNum, String gid, AddMemberActivity.IntentHandler intentHandler) {
        this.mContext = context;
        this.mGID = gid;
        this.mPrintNum = printNum;
        this.gson = new Gson();
        this.AintentHandler = intentHandler;

        params = new RequestParams();
        params.put("OrderGID", mGID);//订单GID
    }

    public HttpGetPrintContents(Context context, int printNum, String gid, MemberChargePayActivity.IntentHandler intentHandler) {
        this.mContext = context;
        this.mGID = gid;
        this.mPrintNum = printNum;
        this.gson = new Gson();
        this.MintentHandler = intentHandler;

        params = new RequestParams();
        params.put("OrderGID", mGID);//订单GID
    }


    public HttpGetPrintContents(Context context, int printNum, String gid, UnionPayActivity.IntentHandler intentHandler) {
        this.mContext = context;
        this.mGID = gid;
        this.mPrintNum = printNum;
        this.gson = new Gson();
        this.UintentHandler = intentHandler;

        params = new RequestParams();
        params.put("OrderGID", mGID);//订单GID
    }

    public HttpGetPrintContents(Context context, int printNum, String gid) {
        this.mContext = context;
        this.mGID = gid;
        this.mPrintNum = printNum;
        this.gson = new Gson();

        params = new RequestParams();
        params.put("OrderGID", mGID);//订单GID
    }

    /**
     * 进行快速消费的打印
     */
    public void KSXF() {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                    try {
                        if (MyApplication.PRINT_IS_OPEN) {
                            Print_KSXF_Bean print_ksxf_bean = CommonFun.JsonToObj(responseString, Print_KSXF_Bean.class);
                            //打印小票
                            PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, print_ksxf_bean, "KSXF");
                            printerUtils.print();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }finally {
                        if (UintentHandler!=null){
                            UintentHandler.sendEmptyMessage(3);
                        }else if (PintentHandler!=null){
                            PintentHandler.sendEmptyMessage(3);
                        }
                    }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
                Li("failure--"+msg);
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext,HttpAPI.API().GET_FAST_PRINT_DATA, params, callBack);

    }

    /**
     * 进行商品消费的打印
     */
    public void SPXF() {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                if (MyApplication.PRINT_IS_OPEN) {
                    Print_SPXF_Bean print_spxf_bean = CommonFun.JsonToObj(responseString, Print_SPXF_Bean.class);
                    //打印小票
                    PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, print_spxf_bean, "SPXF");
                    printerUtils.print();

                }
                if (UintentHandler!=null){
                    UintentHandler.sendEmptyMessage(1);
                }else if (PintentHandler!=null){
                    PintentHandler.sendEmptyMessage(1);
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext,HttpAPI.API().GET_GOODS_PRINT_DATA, params, callBack);

    }


    /**
     * 进行一键加油的打印
     */
    public void YJJY() {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                if (MyApplication.PRINT_IS_OPEN) {
                    Print_YJJY_Bean print_spxf_bean = CommonFun.JsonToObj(responseString, Print_YJJY_Bean.class);
                    //打印小票

                    PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, print_spxf_bean, "YJJY");
                    printerUtils.print();

                }
                if (UintentHandler!=null){
                    UintentHandler.sendEmptyMessage(5);
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext,HttpAPI.API().PRINT_YJJY, params, callBack);

    }


    /**
     * 进行会员充值的打印
     */
    public void HYCZ() {
//        RequestParams params = new RequestParams();
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    if (MyApplication.PRINT_IS_OPEN) {
                        Print_HYCZ_Bean print_hycz_bean = CommonFun.JsonToObj(responseString, Print_HYCZ_Bean.class);
                        //打印小票
                        PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, print_hycz_bean, "HYCZ");
                        printerUtils.print();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (UintentHandler!=null){
                        UintentHandler.sendEmptyMessage(4);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext,HttpAPI.API().PRINT_HYCZ, params, callBack);
    }

    /**
     * 进行会员充次的打印
     */
    public void HYCC() {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    if (MyApplication.PRINT_IS_OPEN) {
                        Print_HYCC_Bean print_hycc_bean = CommonFun.JsonToObj(responseString, Print_HYCC_Bean.class);
                        //打印小票
                        PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, print_hycc_bean, "HYCC");
                        printerUtils.print();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (UintentHandler!=null){
                        UintentHandler.sendEmptyMessage(2);
                    }else if (PintentHandler!=null){
                        PintentHandler.sendEmptyMessage(2);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                Log.i("onFailure", "onFailure: " + msg);
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext, HttpAPI.API().PRINT_HYCC, params, callBack);
    }


    /**
     * 进行计次消费的打印
     */
    public void JCXF() {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    if (MyApplication.PRINT_IS_OPEN) {
                        Print_JCXF_Bean print_jcxf_bean = CommonFun.JsonToObj(responseString, Print_JCXF_Bean.class);
                        //打印小票
                        PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, print_jcxf_bean, "JCXF");
                        printerUtils.print();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (MintentHandler!=null){
                        MintentHandler.sendEmptyMessage(1);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext,HttpAPI.API().GET_JICI_PRINT_DATA, params,callBack);
    }


    /**
     * 进行限时消费的打印
     */
    public void XSXF() {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    if (MyApplication.PRINT_IS_OPEN) {
                        XSXF_Success_Bean print_xsxf_bean = CommonFun.JsonToObj(responseString, XSXF_Success_Bean.class);
                        //打印小票
                        PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, print_xsxf_bean, "XSXF");
                        printerUtils.print();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext,HttpAPI.API().CHECK_IN_PRINT, params,callBack);
    }

    /**
     * 进行金粉兑换的打印
     */
    public void JFDH() {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    if (MyApplication.PRINT_IS_OPEN) {
                        Print_JFDH_Bean print_jfdh_bean = CommonFun.JsonToObj(responseString, Print_JFDH_Bean.class);
                        //打印小票
                        PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, print_jfdh_bean, "JFDH");
                        printerUtils.print();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();

            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext,HttpAPI.API().PRINT_JFDH, params, callBack);
    }


    /**
     * 进行会员开卡的打印
     */
    public void HYKK() {
        RequestParams params = new RequestParams();
        params.put("OrderGID", mGID);//订单GID
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    if (MyApplication.PRINT_IS_OPEN) {
                        Print_HYKK_Bean bean = CommonFun.JsonToObj(responseString, Print_HYKK_Bean.class);
                        PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, bean, "HYKK");
                        printerUtils.print();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (AintentHandler!=null){
                        AintentHandler.sendEmptyMessage(1);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                Log.i("onFailure", "onFailure: " + msg);
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext, HttpAPI.API().PRINT_HYKK, params, callBack);
    }


    /**
     * 入库打印
     *   每页条数
     *   查询入库记录
     */

    public void RK() {

        RequestParams params = new RequestParams();
        params.put("OrderGID", mGID);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    RK_Success_Bean bean = CommonFun.JsonToObj(responseString, RK_Success_Bean.class);
                    PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, bean, "RK");
                    printerUtils.print();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext, HttpAPI.API().PRINT_RK, params, callBack);
    }

    /**
     * 入库打印
     *   每页条数
     *   查询入库记录
     */

    public void CK() {

        RequestParams params = new RequestParams();
        params.put("OrderGID", mGID);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    CK_Success_Bean bean = CommonFun.JsonToObj(responseString, CK_Success_Bean.class);
                    PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, bean, "CK");
                    printerUtils.print();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext, HttpAPI.API().PRINT_CK, params, callBack);
    }

    /**
     * 退货打印
     *
     * */

    public void SPTH(final GoodsRetureActivity.IntentHandler intentHandler) {

        RequestParams params = new RequestParams();
        params.put("ReturnGID", mGID);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    Print_SPTH_Bean bean = CommonFun.JsonToObj(responseString, Print_SPTH_Bean.class);
                    PrinterUtils printerUtils = new PrinterUtils(mContext, mPrintNum, bean, "SPTH");
                    printerUtils.print();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (intentHandler!=null){
                        intentHandler.sendEmptyMessage(1);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext, HttpAPI.API().PRINT_SPTH, params, callBack);
    }

    public void JB(){
        RequestParams params = new RequestParams();
        params.put("PageSize", 10);
        params.put("PageIndex", 1);
        params.put("OrderGID", mGID);

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    JB_Success_Bean bean = CommonFun.JsonToObj(responseString, JB_Success_Bean.class);
                    PrinterUtils printerUtils = new PrinterUtils(mContext, MyApplication.JB_PRINT_TIMES, bean, "JB");
                    printerUtils.print();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(mContext,"请稍等...",false);
        HttpHelper.post(mContext, HttpAPI.API().PRINT_JB, params, callBack);
    }

}
