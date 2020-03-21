package com.zhiluo.android.yunpu.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.login.activity.LoginActivity;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.setting.bluetooth.activity.BlueToothActivity;
import com.zhiluo.android.yunpu.setting.print.PrintSettingActivity;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

/**
 * 蓝牙打印设置
 */
public class BluePrintSettingActivity extends BaseActivity {

    private RelativeLayout toothset, rl_print_setting;
    private TextView ivback;
    private NumberPicker np_print_num;
    private String headSet;
    private String bottonSet;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blu_print_setting);
        //初始化存储数据
        initSp();
        toothset = (RelativeLayout) findViewById(R.id.rl_toothset);
        rl_print_setting = (RelativeLayout) findViewById(R.id.rl_print_setting);
        ivback = (TextView) findViewById(R.id.tv_back_activity);

        //获取打印设置
        printSet();
        initEvents();
    }

    private void initSp() {
        editor = getSharedPreferences("setting", MODE_PRIVATE).edit();
    }

    private void printSet() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        client.post(HttpAPI.API().PRINTSET, params, new AsyncHttpResponseHandler() {
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
                            // TODO: 2017/1/9 0009 获取网页端设置
                        } else {
                            LoginActivity loginActivity = new LoginActivity();
                            //小票头部
                            headSet = loginActivity.getShopname();
                            //小票脚注
                            bottonSet = "谢谢惠顾！";
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

    private void initEvents() {
        //打印设置
        rl_print_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BluePrintSettingActivity.this, PrintSettingActivity.class);
                startActivity(intent);
            }
        });
        //蓝牙设置
        toothset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BluePrintSettingActivity.this, BlueToothActivity.class));

//                startActivity(new Intent(BluePrintSettingActivity.this, BluetoothActivity.class));

            }
        });
        //返回
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public String getHeadSet() {
        return headSet;
    }

    public String getBottonSet() {
        return bottonSet;
    }

}
