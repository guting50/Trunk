package com.zhiluo.android.yunpu.sms.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.sms.adapter.SMSTemplatesAdapter;
import com.zhiluo.android.yunpu.sms.jsonbean.OwnSMSJsonBean;
import com.zhiluo.android.yunpu.sms.jsonbean.SMSJsonBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 选择短信模板列表界面
 *
 * @author Cheng
 */
public class ChooseSMSTemplatesActivity extends BaseActivity {
    @Bind(R.id.tv_sms_add)
    TextView tvSmsAdd;
    private LinearLayout mActivityChooseSmstemplates;
    private RelativeLayout mRlHeadMember;
    private TextView tv_back_activity;
    private TextView mTvTitleMember;
    private WaveSwipeRefreshLayout mWsrlSmsTemplatesMember;
    private ListView mLvSmsTemplatesListMember;
    private SMSTemplatesAdapter mAdapter;
    private List<SMSJsonBean.DataBean.TempClassListBean> mTempClassList;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_choose_smstemplates);
        ButterKnife.bind(this);
        assignViews();
        //数据绑定
        assignData();
        //获取模板列表数据
        doPost();
        doSMSPost();

        initEvents();

    }

    private void assignData() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        break;
                }
                return false;
            }
        });

    }

    /**
     * 绑定视图控件
     */
    private void assignViews() {
        mActivityChooseSmstemplates = (LinearLayout) findViewById(R.id.activity_choose_smscontents);
        mRlHeadMember = (RelativeLayout) findViewById(R.id.rl_head_choosesmscontents);
        tv_back_activity = (TextView) findViewById(R.id.tv_back_activity);
        mTvTitleMember = (TextView) findViewById(R.id.tv_title_choosesmscontents);
        mWsrlSmsTemplatesMember = (WaveSwipeRefreshLayout) findViewById(R.id.wsrl_sms_contents_choosesmscontents);
        mLvSmsTemplatesListMember = (ListView) findViewById(R.id.lv_sms_contents_list_choosesmscontents);
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        mLvSmsTemplatesListMember.setAdapter(mAdapter = new SMSTemplatesAdapter(this, mTempClassList));
    }

    /**
     * 初始化监听事件
     */
    private void initEvents() {
        //选中模板
        mLvSmsTemplatesListMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //将选中模板的classcode传递到短信内容显示列表
                ChooseSMSContentsActivity.fromChooseSMSTemplatesActivity(ChooseSMSTemplatesActivity.this,
                        mTempClassList.get(i).getTC_ClassCode(),
                        mTempClassList.get(i).getTC_ClassName(),
                        getIntent().getStringArrayListExtra("plist"),
                        (List<AllMemberListBean.DataBean.DataListBean>) getIntent().getSerializableExtra("namelist"));
            }
        });

        //点击头部的返回按钮返回到上一个activity界面
        tv_back_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //模板列表下拉列表刷新列表数据
        mWsrlSmsTemplatesMember.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("TAG", "onRefresh: ");
                mWsrlSmsTemplatesMember.setRefreshing(false);
            }

            @Override
            public void onLoad() {
                Log.d("TAG", "onLoad: ");
                mWsrlSmsTemplatesMember.setRefreshing(true);
            }

            @Override
            public boolean canLoadMore() {
                return false;
            }

            @Override
            public boolean canRefresh() {
                return false;
            }
        });

        tvSmsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseSMSTemplatesActivity.this,AddSmsTemplatesActivity.class);

                startActivityForResult(intent,10);
            }
        });
    }


    /**
     * 网络请求API获取短信模板列表数据
     */
    private void doPost() {
        RequestParams params = new RequestParams();
        params.put("Group", "3");
        HttpHelper.post(this, HttpAPI.API().SMSTEMPLATES, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SMSJsonBean smsJsonBean = CommonFun.JsonToObj(responseString, SMSJsonBean.class);
                if (smsJsonBean.isSuccess()) {
                    mTempClassList = smsJsonBean.getData().getTempClassList();
                    SMSJsonBean.DataBean.TempClassListBean bean = new SMSJsonBean.DataBean.TempClassListBean();
                    bean.setGID("");
                    bean.setTC_ClassCode("");
                    bean.setTC_ClassName("自定义模板");
                    mTempClassList.add(bean);
                    initDatas();
                }
            }

            @Override
            public void onFailure(String msg) {
                Log.i(TAG, "onFailure: ");

            }
        });
    }

    /**
     * 网络请求API获取自定义短信模板列表数据
     */
    private void doSMSPost() {
        HttpHelper.post(this, HttpAPI.API().SMSTEMPLATES, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                OwnSMSJsonBean JsonBean = CommonFun.JsonToObj(responseString, OwnSMSJsonBean.class);

            }

            @Override
            public void onFailure(String msg) {
                Log.i(TAG, "onFailure: ");

            }
        });
    }
}
