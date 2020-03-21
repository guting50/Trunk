package com.zhiluo.android.yunpu.sms.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.sms.adapter.SMSContentsAdapter;
import com.zhiluo.android.yunpu.sms.jsonbean.MsgTemplateListBean;
import com.zhiluo.android.yunpu.sms.jsonbean.SMSJsonBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * 根据选中的短信模板跳转到本页面显示该模板对应的短信内容列表
 *
 * @author Cheng
 */
public class ChooseSMSContentsActivity extends AppCompatActivity
{
    private LinearLayout mActivityChooseSmscontents;
    private RelativeLayout mRlHeadChoosesmscontents;
    private TextView mTvBackChoosesmscontents;
    private TextView mTvTitleChoosesmscontents;
    private WaveSwipeRefreshLayout mWsrlSmsContentsChoosesmscontents;
    private ListView mLvSmsContentsListChoosesmscontents;
    private SMSContentsAdapter mSMSContentsAdapter;
    private List<String> mContentMsgs = new ArrayList<String>();
    private List<SMSJsonBean.DataBean.TempManagerListBean> mTempManagerList;
    private List<AllMemberListBean.DataBean.DataListBean> namelist;
    private ArrayList<String> plist;


    /**
     * 接收从短信模板界面传递过来的classcode
     * @param context
     * @param classCode
     */
    public static void fromChooseSMSTemplatesActivity(Context context, String classCode,String className,ArrayList<String> plist, List<AllMemberListBean.DataBean.DataListBean> namelist)
    {
        Intent intent = new Intent(context, ChooseSMSContentsActivity.class);
        intent.putExtra("classCode", classCode);
        intent.putExtra("className", className);
        intent.putStringArrayListExtra("plist", plist);
        intent.putExtra("namelist", (Serializable) namelist);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_choose_smscontents);

        namelist = (List<AllMemberListBean.DataBean.DataListBean>) getIntent().getSerializableExtra("namelist");
        plist= (ArrayList<String>) getIntent().getStringArrayListExtra("plist");

        if (getIntent().getStringExtra("className").equals("自定义模板")){
            GetMsgTemplateList();
        }else {
            doPost();
        }



        assignViews();

        initEvents();
    }

    private void assignViews()
    {
        mActivityChooseSmscontents = (LinearLayout) findViewById(R.id.activity_choose_smscontents);
        mRlHeadChoosesmscontents = (RelativeLayout) findViewById(R.id.rl_head_choosesmscontents);
        mTvBackChoosesmscontents = (TextView) findViewById(R.id.tv_back_activity);
        mTvTitleChoosesmscontents = (TextView) findViewById(R.id.tv_title_choosesmscontents);
        mWsrlSmsContentsChoosesmscontents = (WaveSwipeRefreshLayout) findViewById(R.id.wsrl_sms_contents_choosesmscontents);
        mLvSmsContentsListChoosesmscontents = (ListView) findViewById(R.id.lv_sms_contents_list_choosesmscontents);

        //将选中的模板名称设置成标题
        mTvTitleChoosesmscontents.setText(getIntent().getStringExtra("className"));
    }

    private void initDatas() {

        //根据得到classcode查找对应的短信内容集合
        for (SMSJsonBean.DataBean.TempManagerListBean tempManagerBean:mTempManagerList)
        {
            if (tempManagerBean.getTC_ClassCode().equals(getIntent().getStringExtra("classCode")))
            {
                mContentMsgs.add(tempManagerBean.getTM_Content());
            }
        }
        mLvSmsContentsListChoosesmscontents.setAdapter(mSMSContentsAdapter = new SMSContentsAdapter(this, mContentMsgs,plist,namelist));
    }

    private void initEvents()
    {
        //返回
        mTvBackChoosesmscontents.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }

    /**
     * 网络请求API获取短信模板列表数据
     */
    private void doPost()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("TM_Group", 3);
        client.post(HttpAPI.API().SMSTEMPLATES, params, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                if (statusCode == 200)
                {
                    try
                    {
                        Gson gson = new Gson();
                        SMSJsonBean smsJsonBean = CommonFun.JsonToObj(new String(responseBody, "UTF-8"), SMSJsonBean.class);
                        if (smsJsonBean.isSuccess())
                        {
                            mTempManagerList = smsJsonBean.getData().getTempManagerList();
                            initDatas();
                        }
                    }
                    catch (UnsupportedEncodingException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {

            }
        });
    }


    private void GetMsgTemplateList(){

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                MsgTemplateListBean beanlist = CommonFun.JsonToObj(responseString,MsgTemplateListBean.class);

                for (MsgTemplateListBean.DataBean bean :beanlist.getData()){
                    mContentMsgs.add(bean.getTM_Content());
                }
                mLvSmsContentsListChoosesmscontents.setAdapter(mSMSContentsAdapter = new SMSContentsAdapter(ChooseSMSContentsActivity.this, mContentMsgs,plist,namelist));
            }

            @Override
            public void onFailure(String msg) {

            }
        };
        callBack.setmAPI("MessageTemp/GetMsgTemplateList");
        HttpHelper.post(this,HttpAPI.API().GETMSGTEMPLATES,callBack);
    }
}
