package com.zhiluo.android.yunpu.member.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.ShopeEntity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.adapter.VipStoreAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.ConditionBean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.SaveScreenStateUtil;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import java.util.ArrayList;
import java.util.List;

public class ScreenStoreActivity extends AppCompatActivity implements VipStoreAdapter.StoreItemListener {
    private VipStoreAdapter mStoreAdapter;//店铺Adapter
    private String mStoreGid;
    private ShopeEntity mShopeEntity;
    private List<ConditionBean> mVipStoreList = new ArrayList<>();//店铺数据
    private RecyclerView reVipStore;
    private LayoutInflater mInflater;
    private RadioButton mRbAll, mRbYes, mRbNo;
    private RadioGroup mRgFirst;
    private String mPayWayCode;
    private SaveScreenStateUtil mStateUtil;
    private List<String> mGradeNameList;//会员等级名称集合
    private List<String> mStatusNameList;
    private List<ReportMessageBean.DataBean.VIPGradeListBean> mMemberGrade;//会员等级实体
    private int Gradepos = 0, stauspos = 0;
    private ArrayAdapter<String> mGSpinnerAdapter, mSSpinnerAdapter;
    private Spinner spGrade, spstatus;
    private String mGradeGid, mStatusGid;
    private LoginUpbean mLoginBean;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean isvisiblestore = true;
    private int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_store);

        initView();
        loadData();
        initDataView();
        setStoreLabel();
        setListener();
    }


    private void initView() {
        mRgFirst = findViewById(R.id.rg_yjyx_type);
        mRbAll = (RadioButton) findViewById(R.id.rb_goods_1);
        mRbYes = (RadioButton) findViewById(R.id.rb_goods_2);
        mRbNo = (RadioButton) findViewById(R.id.rb_goods_3);
        spGrade = (Spinner) findViewById(R.id.text_grade);
        spstatus = (Spinner) findViewById(R.id.text_status);
        reVipStore = findViewById(R.id.re_vip_store);
        mStoreGid = (String) uSharedPreferencesUtiles.get(ScreenStoreActivity.this, "Store", "");
        mInflater = LayoutInflater.from(getApplicationContext());
        GridLayoutManager gridLayoutManager6 = new GridLayoutManager(this, 3);
        reVipStore.setLayoutManager(gridLayoutManager6);
    }


    /**
     * 加载数据
     */
    private void loadData() {

        mLoginBean = (LoginUpbean) CacheData.restoreObject("LG");
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {
                    if ("210".equals(mSwitchEntity.get(i).getSS_Code())) {//是否显示他店会员
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            isvisiblestore = true;
                        } else {
                            isvisiblestore = false;
                        }
                    }
                }
            }
        }

        Intent intent = getIntent();
        if (intent != null) {
            mStoreGid = intent.getStringExtra("STORE");
            mGradeGid = intent.getStringExtra("GRADE");
            mPayWayCode = intent.getStringExtra("YJYX");
            mStatusGid = intent.getStringExtra("STATUS");
//            mStoreGid = intent.getStringExtra("");
        }

        mMemberGrade = (List<ReportMessageBean.DataBean.VIPGradeListBean>) CacheData.restoreObject("grade");
        if (mMemberGrade == null) {
            getMemberGrade();//获取会员等级
        } else {
            groupGradeName();//组装会员等级名称集合

        }

        mStatusNameList = new ArrayList<>();
        mStatusNameList.clear();
        mStatusNameList.add("所有状态");
        mStatusNameList.add("正常");
        mStatusNameList.add("锁定");
        mStatusNameList.add("挂失");


    }

    private void initDataView() {
        if (mMemberGrade != null) {
            for (int i = 0; i < mMemberGrade.size(); i++) {
                if (mGradeGid != null) {
                    if (mGradeGid.equals(mMemberGrade.get(i).getGID())) {
                        Gradepos = i + 1;
                    }
                } else {
                    Gradepos = 0;
                }
            }
        }
        if (mStatusGid != null && !mStatusGid.equals("")) {
            stauspos = Integer.parseInt(mStatusGid) + 1;
        }

        if (mPayWayCode != null && !mPayWayCode.equals("")) {
            switch (mPayWayCode) {
                case "1":
                    mRbYes.setChecked(true);
                    break;
                case "0":
                    mRbNo.setChecked(true);
                    break;
            }
        }
        mGSpinnerAdapter = new ArrayAdapter<String>(ScreenStoreActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, mGradeNameList);
        spGrade.setAdapter(mGSpinnerAdapter);
        spGrade.setSelection(Gradepos, true);

        mSSpinnerAdapter = new ArrayAdapter<String>(ScreenStoreActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, mStatusNameList);
        spstatus.setAdapter(mSSpinnerAdapter);
        spstatus.setSelection(stauspos, true);


    }


    /**
     * 查询会员等级
     */
    private void getMemberGrade() {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        client.post(HttpAPI.API().PRE_LOAD, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                mMemberGrade = reportbean.getData().getVIPGradeList();
                CacheData.saveObject("grade", mMemberGrade);//缓存等级列表到本地

                groupGradeName();//组装会员等级名称集合

            }

            @Override
            public void onFailure(String msg) {
            }
        });

    }

    /**
     * 组装会员等级名称数据集合
     */
    private void groupGradeName() {
        mGradeNameList = new ArrayList<>();
        mGradeNameList.clear();//清空
        mGradeNameList.add("所有等级");
        if (mMemberGrade != null) {
            for (int i = 0; i < mMemberGrade.size(); i++) {
                mGradeNameList.add(mMemberGrade.get(i).getVG_Name());
            }
        }

    }

    private void setListener() {
        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.btn_screen_member_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int s = 0; s < mVipStoreList.size(); s++) {
                    mVipStoreList.get(s).setChecked(false);
                    mVipStoreList.get(pos).setChecked(true);
                    mStoreAdapter.notifyDataSetChanged();
                }

                mRgFirst.check(mRbAll.getId());
                spGrade.setSelection(0);
                spstatus.setSelection(0);
                mPayWayCode = "";
                mGradeGid = "";
                mStatusGid = "";
            }
        });
        findViewById(R.id.btn_screen_member_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("STORE", vipStore());
                intent.putExtra("GRADE", mGradeGid);
                intent.putExtra("YJYX", mPayWayCode);
                intent.putExtra("STATUS", mStatusGid);
                setResult(44, intent);
                finish();
            }
        });


        spGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mGradeGid = "";
                } else {
                    mGradeGid = mMemberGrade.get(position - 1).getGID();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spstatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mStatusGid = "";
                } else {
                    mStatusGid = String.valueOf(position - 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mRgFirst.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == mRbAll.getId()) {
                    mRgFirst.check(mRbAll.getId());
                    mPayWayCode = "";
                }
                if (i == mRbYes.getId()) {
                    mRgFirst.check(mRbYes.getId());
                    mPayWayCode = "1";
                }
                if (i == mRbNo.getId()) {
                    mRgFirst.check(mRbNo.getId());
                    mPayWayCode = "0";
                }

            }
        });


    }

    private void setStoreLabel() {
        HttpHelper.post(ScreenStoreActivity.this, HttpAPI.API().GET_SHOP_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mShopeEntity = CommonFun.JsonToObj(responseString, ShopeEntity.class);
                mVipStoreList.clear();
                ConditionBean conditionBean2 = new ConditionBean();
                conditionBean2.setCondition("全部");
                conditionBean2.setGID("");
                mVipStoreList.add(conditionBean2);
                for (int i = 0; i < mShopeEntity.getData().size(); i++) {
                    ConditionBean conditionBean = new ConditionBean();
                    conditionBean.setCondition(mShopeEntity.getData().get(i).getSM_Name());
                    conditionBean.setGID(mShopeEntity.getData().get(i).getGID());
                    if (!isvisiblestore) {
                        if (mShopeEntity.getData().get(i).getGID().equals(mStoreGid)) {
                            mVipStoreList.clear();
                            mVipStoreList.add(conditionBean);
                            break;
                        }
                    } else {
                        mVipStoreList.add(conditionBean);
                    }
                }

                //店铺
//                if (TextUtils.isEmpty(mStoreGid)||isvisiblestore) {
//                    mVipStoreList.get(0).setChecked(true);
//
//                }else {
                for (int i = 0; i < mVipStoreList.size(); i++) {
                    if (mVipStoreList.get(i).getGID().equals(mStoreGid)) {
                        mVipStoreList.get(i).setChecked(true);
                        pos = i;
                        break;
                    }
                }
//                }

                mStoreAdapter = new VipStoreAdapter(ScreenStoreActivity.this, mVipStoreList, ScreenStoreActivity.this, isvisiblestore, pos);
                reVipStore.setAdapter(mStoreAdapter);
                //                mStoreAdapter.setParam(isvisiblestore,isSuperUser);
//                mHandler.sendEmptyMessage(4);

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void storeItemChecked(ConditionBean conditionBean, boolean isChecked) {
        for (int j = 0; j < mVipStoreList.size(); j++) {
            mVipStoreList.get(j).setChecked(false);
        }
        if (isChecked) {
            conditionBean.setChecked(true);
        }
        mStoreAdapter.notifyDataSetChanged();
    }

    /****
     *
     * 返回筛选后的店铺
     *
     * ********/

    private String vipStore() {
        String store = "";
        for (int i = 0; i < mVipStoreList.size(); i++) {
            if (mVipStoreList.get(i).isChecked()) {
                store = mVipStoreList.get(i).getGID();
            }
        }

        return store;
    }
}
