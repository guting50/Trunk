package com.zhiluo.android.yunpu.mvp.presenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.goods.manager.bean.BarCodePayBean;
import com.zhiluo.android.yunpu.goods.manager.bean.QuerPayBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.activity.LoginActivity;
import com.zhiluo.android.yunpu.mvp.model.SuccessEntry;
import com.zhiluo.android.yunpu.mvp.view.IBaseView;
import com.zhiluo.android.yunpu.mvp.view.SaoMaPayView;
import com.zhiluo.android.yunpu.ui.activity.ActivityCollector;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.LoadingDialogUtil;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by YSL on 2018-10-31.
 */

public class SaoMaPayPresntter implements Presenter {
    private Context mContext;
    private SaoMaPayView mView;



    public SaoMaPayPresntter(Context context){
        this.mContext= context;

    }
    @Override
    public void onCreate(String s) {

    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    @Override
    public void attachView(IBaseView view) {
        mView = (SaoMaPayView) view;

    }

    public void barCode(RequestParams params){

        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(mContext);
        client.setCookieStore(myCookieStore);
        String url = MyApplication.BASE_URL+"PayOrder/BarcodePay";
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = null;
                try {
                    responseString = new String(responseBody, "UTF-8");

                    if (statusCode == 200) {
                        Gson gson = new Gson();
                        SuccessEntry entity = CommonFun.JsonToObj(responseString, SuccessEntry.class);

                        if (entity.isSuccess()) {
                            BarCodePayBean entityresult = CommonFun.JsonToObj(responseString, BarCodePayBean.class);
                            if (!entityresult.getData().equals("")&&!String.valueOf(entityresult.getData()).equals("1")){
                                mView.barCodeSuccess(entityresult);
                            }else {
                                onCancel();
                            }
                        } else if (entity.getMsg().contains("登录") || entity.getMsg().contains("登陆")) {

                            if (MyApplication.LOGINCODE){
                                MyApplication.LOGINCODE = false;
                                reLogin(entity.getMsg());//重新登录
                            }

                        } else {
                            if (!entity.getData().equals("")&&!String.valueOf(entity.getData()).equals("1")){
                                mView.barCodeFailString(entity.getMsg());
                            }else {
                                BarCodePayBean entityresult = CommonFun.JsonToObj(responseString, BarCodePayBean.class);
                                mView.barCodeFail(entityresult);
                            }
                        }
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                if (statusCode != 200) {
                    if (statusCode == 301 || statusCode == 302 || statusCode == 307) {
                        for (int i = 0;i<headers.length;i++){
                            CustomToast.makeText(MyApplication.getContext(), "请重新登录", Toast.LENGTH_LONG).show();

                        }
//                redirect(statusCode);
                    } else {
                        CustomToast.makeText(MyApplication.getContext(), "错误码：" + statusCode + " 网络错误,请检查网络是否连接!", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });

    }

    public void querPay(RequestParams params){

        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(mContext);
        client.setCookieStore(myCookieStore);
        String url = MyApplication.BASE_URL+"PayOrder/QuerPay";
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String responseString = null;
                try {
                    responseString = new String(responseBody, "UTF-8");

                    if (statusCode == 200) {
                        Gson gson = new Gson();
                        QuerPayBean entityresult = CommonFun.JsonToObj(responseString, QuerPayBean.class);
                        if (entityresult.isSuccess()) {
                            mView.querPaySuccess(entityresult);
                        } else if (entityresult.getMsg().contains("登录") || entityresult.getMsg().contains("登陆")) {
                            if (MyApplication.LOGINCODE){
                                MyApplication.LOGINCODE = false;
                                reLogin(entityresult.getMsg());//重新登录
                            }

                        } else {
                            mView.querPayFail(entityresult);
                        }
                    }

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                if (statusCode != 200) {
                    if (statusCode == 301 || statusCode == 302 || statusCode == 307) {
                        for (int i = 0;i<headers.length;i++){
                            CustomToast.makeText(MyApplication.getContext(), "请重新登录", Toast.LENGTH_LONG).show();

                        }
                    } else {
                        CustomToast.makeText(MyApplication.getContext(), "错误码：" + statusCode + " 网络错误,请检查网络是否连接!", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }

    /**
     * 重新登录
     */
    private void reLogin(String msg) {

        ActivityCollector.finishAll();
        Intent intent = new Intent(MyApplication.getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.getContext().startActivity(intent);
        CustomToast.makeText(MyApplication.getmContext(),msg, Toast.LENGTH_SHORT).show();
    }
}
