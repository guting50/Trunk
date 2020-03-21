package com.zhiluo.android.yunpu.member.manager.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.basewin.services.LEDBinder;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.jsonbean.StaffCommissionBean;
import com.zhiluo.android.yunpu.entity.ShopeEntity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.adapter.BirthdayAdapter;
import com.zhiluo.android.yunpu.member.manager.adapter.ConditionSelectAdapter;
import com.zhiluo.android.yunpu.member.manager.adapter.LabAdapter;
import com.zhiluo.android.yunpu.member.manager.adapter.LevelAdapter;
import com.zhiluo.android.yunpu.member.manager.adapter.StaffAdapter;
import com.zhiluo.android.yunpu.member.manager.adapter.StateAdapter;
import com.zhiluo.android.yunpu.member.manager.adapter.VipStoreAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.ConditionBean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.LoadingDialogUtil;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.SuccessBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberLabel;
import com.zhiluo.android.yunpu.member.manager.bean.MemberGrade;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 筛选会员
 * 作者：罗咏哲 on 2017/7/21 14:40
 * 邮箱：137615198@qq.com
 * Changed by ${YSL} on 2018-05-02.
 */
public class ScreenMemberActivity extends BaseActivity implements LevelAdapter.LevelItemListener, LabAdapter.LabItemListener,
        BirthdayAdapter.BirthdayItemListener, StaffAdapter.StaffItemListener, StateAdapter.StateItemListener {
//    private MemberGrade mMemberGrade;//会员等级实体
    private List<ReportMessageBean.DataBean.VIPGradeListBean> mMemberGrade;//会员等级实体
    private MemberLabel mMemberLabel;//会员标签实体
    private Dialog mDialog;//加载动画
    private Handler mHandler;//
    private StaffCommissionBean mCommissionBean;//所以员工信息
    private RecyclerView reVipLevel, reVipLab, revipState, reVipBirthday, reVipStaff,reVipStore;
    private List<ConditionBean> mVipLevelList, mLabList, mVipStateList, mVipBirthdayList, mVipStaffList,mVipStoreList;
    private LevelAdapter mLevelAdapter;
    private LabAdapter mLabAdapter;
    private BirthdayAdapter mBirthdayAdapter;
    private StaffAdapter mStaffAdapter;
    private StateAdapter mStateAdapter;
    private VipStoreAdapter mStoreAdapter;
    private TextView tvClear, tvCancel, tvComfirm;
    private String GID;
    private int STATE;
    private int BIRTHDAY;
    private String LAB;
    private String EM_GID;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private ShopeEntity mShopeEntity;
//    private List<String> mStoreList = new ArrayList<>();//店铺数据
    private String mStoreGid,StoreGid;
    private String StartTime,EndTime;
    private int pos =0;
    private LoginUpbean mLoginBean;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean isvisiblestore = true;
    private boolean isSuperUser = false;
//    private TagAdapter<String> mStoreAdapter;//店铺Adapter
    private LayoutInflater mInflater;
//    private TagFlowLayout mFlowStore;//店铺
    private TextView teStartTime,teEndTime;
    private Dialog datesDialog;
    private VipStoreAdapter.StoreItemListener mCheckListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_member);
        initDatas();
        initView();
        getData();
        initVariable();
        loadData();
        setListener();

    }

    private void initDatas() {
        StoreGid = (String) uSharedPreferencesUtiles.get(MyApplication.getmContext(), "Store", "");
        preferences = getSharedPreferences("sx_vip", MODE_PRIVATE);
        editor = preferences.edit();
        mVipLevelList = new ArrayList<>();
        mLabList = new ArrayList<>();
        mVipStateList = new ArrayList<>();
        mVipBirthdayList = new ArrayList<>();
        mVipStaffList = new ArrayList<>();
        mVipStoreList = new ArrayList<>();
        initVipStateData(mVipStateList);
        initVipBirthdayData(mVipBirthdayList);
        mLevelAdapter = new LevelAdapter(this, mVipLevelList, this);
        mLabAdapter = new LabAdapter(this, mLabList, this);
        mBirthdayAdapter = new BirthdayAdapter(this, mVipBirthdayList, this);
        mStaffAdapter = new StaffAdapter(this, mVipStaffList, this);
        mStateAdapter = new StateAdapter(this, mVipStateList, this);

    }

    private void initVipBirthdayData(List<ConditionBean> conditionBeans) {
        ConditionBean bean1 = new ConditionBean();
        ConditionBean bean2 = new ConditionBean();
        ConditionBean bean3 = new ConditionBean();
        ConditionBean bean4 = new ConditionBean();
        bean1.setCondition("无条件");
        bean2.setCondition("今天");
        bean3.setCondition("本周");
        bean4.setCondition("本月");
        conditionBeans.add(bean1);
        conditionBeans.add(bean2);
        conditionBeans.add(bean3);
        conditionBeans.add(bean4);
    }

    private void initVipStateData(List<ConditionBean> conditionBeans) {
        ConditionBean bean1 = new ConditionBean();
        ConditionBean bean2 = new ConditionBean();
        ConditionBean bean3 = new ConditionBean();
        ConditionBean bean4 = new ConditionBean();
        bean1.setCondition("所有状态");
        bean2.setCondition("正常");
        bean3.setCondition("锁定");
        bean4.setCondition("挂失");
        conditionBeans.add(bean1);
        conditionBeans.add(bean2);
        conditionBeans.add(bean3);
        conditionBeans.add(bean4);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(this, 3);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 3);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(this, 3);
        GridLayoutManager gridLayoutManager4 = new GridLayoutManager(this, 3);
        GridLayoutManager gridLayoutManager5 = new GridLayoutManager(this, 3);
        GridLayoutManager gridLayoutManager6 = new GridLayoutManager(this, 3);
        reVipLevel = findViewById(R.id.re_vip_level);
        reVipLab = findViewById(R.id.re_vip_lab);
        revipState = findViewById(R.id.re_vip_state);
        reVipBirthday = findViewById(R.id.re_vip_birthday);
        reVipStaff = findViewById(R.id.re_vip_staff);
        reVipStore = findViewById(R.id.re_vip_store);
        tvClear = findViewById(R.id.tv_clear);
        tvCancel = findViewById(R.id.tv_cancel);
        tvComfirm = findViewById(R.id.tv_comfirm);
//        mFlowStore = (TagFlowLayout) findViewById(R.id.flow_store);
        mInflater = LayoutInflater.from(getApplicationContext());
        teStartTime = findViewById(R.id.te_start_time);
        teEndTime = findViewById(R.id.te_end_time);

        reVipBirthday.setLayoutManager(gridLayoutManager1);
        reVipLab.setLayoutManager(gridLayoutManager2);
        reVipLevel.setLayoutManager(gridLayoutManager3);
        reVipStaff.setLayoutManager(gridLayoutManager4);
        revipState.setLayoutManager(gridLayoutManager5);
        reVipStore.setLayoutManager(gridLayoutManager6);

        reVipBirthday.setAdapter(mBirthdayAdapter);
        reVipLab.setAdapter(mLabAdapter);
        reVipLevel.setAdapter(mLevelAdapter);
        reVipStaff.setAdapter(mStaffAdapter);
        Le(mVipStateList.size() + "");
        revipState.setAdapter(mStateAdapter);

        mCheckListener = new VipStoreAdapter.StoreItemListener(){

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
        };

    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    //等级
                    case 1:
                        mLevelAdapter.notifyDataSetChanged();
                        break;
                    //标签
                    case 2:
                        mLabAdapter.notifyDataSetChanged();
                        break;
                    case 3:
                        mStaffAdapter.notifyDataSetChanged();
                        break;
                    case 4:
                        mStoreAdapter.notifyDataSetChanged();
                        break;

                }
                return false;
            }
        });
    }


    /**
     * 获取数据
     */
    private void getData() {
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
        if (mLoginBean != null)
            if (mLoginBean.getData().getUM_IsAmin() == 1) {//判断是不是超管
                isSuperUser = true;
            } else {
                isSuperUser = false;
            }

        Intent intent = getIntent();
        if (intent != null) {
            GID = intent.getStringExtra("GID");
            STATE = intent.getIntExtra("STATE", 9999);
            BIRTHDAY = intent.getIntExtra("BIRTHDAY",9999);
            LAB = intent.getStringExtra("LABEL");
            EM_GID = intent.getStringExtra("EM_GID");
            mStoreGid = intent.getStringExtra("STOREID");
            StartTime = intent.getStringExtra("StartTime");
            EndTime = intent.getStringExtra("EndTime");
            teStartTime.setText(StartTime);
            teEndTime.setText(EndTime);
        }
        preferences = getSharedPreferences("sx_vip", MODE_PRIVATE);
//        GID = preferences.getString("GID", "");
//        STATE = preferences.getInt("STATE", 9999);
//        BIRTHDAY = preferences.getInt("BIRTHDAY", 9999);
//        LAB = preferences.getString("LABEL", "");
//        EM_GID = preferences.getString("EM_GID", "");
        //会员状态
        if (STATE == 9999||STATE ==-1) {
            mVipStateList.get(0).setChecked(true);
        } else if (STATE == 0) {
            mVipStateList.get(1).setChecked(true);
        } else if (STATE == 1) {
            mVipStateList.get(2).setChecked(true);
        } else if (STATE == 2) {
            mVipStateList.get(3).setChecked(true);
        }
        //生日
        if (BIRTHDAY == 9999||BIRTHDAY ==-1) {
            mVipBirthdayList.get(0).setChecked(true);
        } else if (STATE == 0) {
            mVipBirthdayList.get(1).setChecked(true);
        } else if (STATE == 1) {
            mVipBirthdayList.get(2).setChecked(true);
        } else if (STATE == 2) {
            mVipBirthdayList.get(3).setChecked(true);
        }

    }

    /**
     * 保存数据
     */
    private void saveData() {
        editor.putString("GID", initGID());
        editor.putInt("STATE", vipState() == -1 ? 9999 : vipState());
        editor.putInt("BIRTHDAY", vipBirthday() == -1 ? 9999 : vipBirthday());
        editor.putString("LABEL", vipLab());
        editor.putString("EM_GID", vipStaff());
        editor.commit();
    }

    /**
     * 设置返回上一页数据
     */
    private void setReturnData() {
        Intent intent = new Intent();
        intent.putExtra("GID", initGID());
        intent.putExtra("STATE", vipState() == -1 ? "" : vipState());
        intent.putExtra("BIRTHDAY", vipBirthday() == -1 ? "" : vipBirthday());
        intent.putExtra("LABEL", vipLab());
        intent.putExtra("EM_GID", vipStaff());
        intent.putExtra("STOREID",vipStore());
        intent.putExtra("StartTime",teStartTime.getText().toString());
        intent.putExtra("EndTime",teEndTime.getText().toString());
        //     Le("GID--" + initGID() + "--STATE--" + (vipState() == -1 ? "" : vipState()) + "---BIRTHDAY---" + (vipBirthday() == -1 ? "" : vipBirthday()) + "---LABEL---" + vipLab() + "--EM_GID--" + vipStaff());
        setResult(777, intent);
        ScreenMemberActivity.this.finish();
    }


    /****
     *
     * 返回筛选后的店铺
     *
     * ********/

    private String vipStore(){
        String store = "";
        for (int i = 0; i < mVipStoreList.size(); i++) {
            if (mVipStoreList.get(i).isChecked()) {
                store = mVipStoreList.get(i).getGID();
            }
        }
        return store;
    }

    /**
     * @return , 返回筛选后的开卡员工
     */
    private String vipStaff() {
        String staff = "";
        for (int i = 0; i < mVipStaffList.size(); i++) {
            if (mVipStaffList.get(i).isChecked()) {
                staff = mVipStaffList.get(i).getGID();
            }
        }
        return staff;
    }

    /**
     * @return 筛选后的标签
     */
    private String vipLab() {
        String lab = "";
        for (int i = 0; i < mLabList.size(); i++) {
            if (mLabList.get(i).isChecked()) {
                if (mLabList.get(i).getCondition().equals("全部")){
                    lab = "";
                }else {
                    lab = mLabList.get(i).getCondition();
                }

            }
        }
        return lab;
    }

    /**
     * //会员生日 0 今天，1 本周，2 本月
     * 筛选后的生日
     */
    private int vipBirthday() {
        int birthday = -1;
        String s = "";
        for (int i = 0; i < mVipBirthdayList.size(); i++) {
            if (mVipBirthdayList.get(i).isChecked()) {
                s = mVipBirthdayList.get(i).getCondition();
            }
        }
        if (s.equals("今天")) {
            birthday = 0;
        } else if (s.equals("本周")) {
            birthday = 1;
        } else if (s.equals("本月")) {
            birthday = 2;
        }
        return birthday;
    }

    /**
     * //会员状态 0 正常，1 锁定，2 挂失
     * 筛选后的状态
     */
    private int vipState() {
        int state = -1;
        String s = "";
        for (int i = 0; i < mVipStateList.size(); i++) {
            if (mVipStateList.get(i).isChecked()) {
                s = mVipStateList.get(i).getCondition();
            }
        }
        if (s.equals("正常")) {
            state = 0;
        } else if (s.equals("锁定")) {
            state = 1;
        } else if (s.equals("挂失")) {
            state = 2;
        }
        return state;
    }

    /**
     * @return 返回筛选后的会员等级
     */
    private String initGID() {
        String GID = "";
        for (int i = 0; i < mVipLevelList.size(); i++) {
            if (mVipLevelList.get(i).isChecked()) {
                GID = mVipLevelList.get(i).getGID();
            }
        }
        return GID;
    }

    /**
     * 加载数据
     */
    private void loadData() {
        mMemberGrade = (List<ReportMessageBean.DataBean.VIPGradeListBean>) CacheData.restoreObject("grade");
        if (mMemberGrade == null) {
            getMemberGrade();//获取会员等级
        }else {
            groupGradeName();//组装会员等级名称集合

            //会员等级
            if (!TextUtils.isEmpty(GID)) {
                Li(GID);
                for (int i = 0; i < mVipLevelList.size(); i++) {
                    Li("=-------as=---" + new Gson().toJson(mVipLevelList.get(i)));
                    if (mVipLevelList.get(i).getGID().equals(GID)) {
                        mVipLevelList.get(i).setChecked(true);
                        break;
                    }
                }
            }else {
                mVipLevelList.get(0).setChecked(true);
            }

            mHandler.sendEmptyMessage(1);
        }
        getMemberLabel();//获取标签数据
        getStaff();//员工
        setStoreLabel();
    }

    /**
     * 设置监听
     */
    private void setListener() {
        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!teStartTime.getText().toString().isEmpty() && !teEndTime.getText().toString().isEmpty()) {
                    if (!DateTimeUtil.isCurTime(ScreenMemberActivity.this, teStartTime.getText().toString())) {
                        return;
                    } else if (!DateTimeUtil.isCurTime(ScreenMemberActivity.this, teEndTime.getText().toString())) {
                        return;
                    }
                    try {
                        if (!DateTimeUtil.isOverTime(ScreenMemberActivity.this, teStartTime.getText().toString(), teEndTime.getText().toString())) {
                            return;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                setReturnData();
//                saveData();
            }
        });
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clerAll();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clerAll();
                finish();
            }
        });

        //开始时间点击监听
        teStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showDatePickOverdueDlg();
                if (teStartTime.getText().toString().isEmpty()){
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), teStartTime);
                }else {
                    showDateDialog(DateUtil.getDateForString(teStartTime.getText().toString()), teStartTime);
                }
            }
        });
        teEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showDatePickOverdueDlg();
                if (teEndTime.getText().toString().isEmpty()) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), teEndTime);
                } else {
                    showDateDialog(DateUtil.getDateForString(teEndTime.getText().toString()), teEndTime);
                }
            }
        });
    }

    /**
     * @param date      ,
     * @param mTextView ,
     *                  选择日期
     */
    private void showDateDialog(List<Integer> date, final TextView mTextView) {
        com.example.liangmutian.mypicker.DatePickerDialog.Builder builder = new com.example.liangmutian.mypicker.DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new com.example.liangmutian.mypicker.DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                mTextView.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
            }

            @Override
            public void onCancel() {

            }
        })
                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);
        datesDialog = builder.create();
        datesDialog.show();
    }

    /**
     * 重置
     */
    private void clerAll() {
        for (int i = 0; i < mVipBirthdayList.size(); i++) {
            mVipBirthdayList.get(i).setChecked(false);
            mVipBirthdayList.get(0).setChecked(true);
            mBirthdayAdapter.notifyDataSetChanged();
        }
        for (int j = 0; j < mVipLevelList.size(); j++) {
            mVipLevelList.get(j).setChecked(false);
            mVipLevelList.get(0).setChecked(true);
            mLevelAdapter.notifyDataSetChanged();
        }
        for (int k = 0; k < mLabList.size(); k++) {
            mLabList.get(k).setChecked(false);
            mLabList.get(0).setChecked(true);
            mLabAdapter.notifyDataSetChanged();
        }
        for (int l = 0; l < mVipStaffList.size(); l++) {
            mVipStaffList.get(l).setChecked(false);
            mVipStaffList.get(0).setChecked(true);
            mStaffAdapter.notifyDataSetChanged();
        }
        for (int m = 0; m < mVipStateList.size(); m++) {
            mVipStateList.get(m).setChecked(false);
            mVipStateList.get(0).setChecked(true);
            mStateAdapter.notifyDataSetChanged();
        }
        for (int s = 0; s < mVipStoreList.size(); s++) {
            mVipStoreList.get(s).setChecked(false);
            mVipStoreList.get(pos).setChecked(true);
            mStoreAdapter.notifyDataSetChanged();
        }

//        for (int i = 0; i < mShopeEntity.getData().size(); i++) {
//            if (mLoginBean != null) {
//                if (mLoginBean.getData().getShopID().equals(mShopeEntity.getData().get(i).getGID())) {
//                    pos = i + 1;
//                }
//            }
//        }
//        mStoreAdapter.setSelectedList(pos);
        teEndTime.setText("");
        teStartTime.setText("");
        editor.putString("GID", "");
        editor.putString("LABEL", "");
        editor.putString("EM_GID", "");
        editor.commit();
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

                //会员等级
                if (!TextUtils.isEmpty(GID)) {
                    Li(GID);
                    for (int i = 0; i < mVipLevelList.size(); i++) {
                        Li("=-------as=---" + new Gson().toJson(mVipLevelList.get(i)));
                        if (mVipLevelList.get(i).getGID().equals(GID)) {
                            mVipLevelList.get(i).setChecked(true);
                            break;
                        }
                    }
                }else {
                    mVipLevelList.get(0).setChecked(true);
                }

                mHandler.sendEmptyMessage(1);
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
        //  mGradeList.clear();//清空
        //mVipLevelList.clear();

        ConditionBean conditionBeanall = new ConditionBean();
        conditionBeanall.setCondition("全部");
        conditionBeanall.setGID("");
        mVipLevelList.add(conditionBeanall);
        if (mMemberGrade != null) {
            for (int i = 0; i < mMemberGrade.size(); i++) {
                //  mGradeList.add(mMemberGrade.getData().get(i).getVG_Name());
                ConditionBean conditionBean = new ConditionBean();
                conditionBean.setCondition(mMemberGrade.get(i).getVG_Name());
                conditionBean.setGID(mMemberGrade.get(i).getGID());
                mVipLevelList.add(conditionBean);
            }
        }
    }


    /**
     * 获取会员标签
     */
    private void getMemberLabel() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("ML_Name", "");
        client.post(HttpAPI.API().MEMBER_LABEL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    try {
                        String response = new String(responseBody, "utf-8");
                        Gson gson = new Gson();
                        SuccessBean successBean = CommonFun.JsonToObj(response, SuccessBean.class);
                        if (successBean.isSuccess()) {
                            mMemberLabel = CommonFun.JsonToObj(response, MemberLabel.class);
                            if (mLabList != null) {
                                mLabList.clear();
                            }
                            ConditionBean conditionBean1 = new ConditionBean();
                            conditionBean1.setCondition("全部");
                            mLabList.add(conditionBean1);
                            for (int i = 0; i < mMemberLabel.getData().size(); i++) {
                                ConditionBean conditionBean = new ConditionBean();
                                conditionBean.setCondition(mMemberLabel.getData().get(i).getML_Name());
                                mLabList.add(conditionBean);
                                // mLabList.add(mMemberLabel.getData().get(i).getML_Name());
                            }

                            //会员标签
                            if (!TextUtils.isEmpty(LAB)) {
                                Li("当前标签========" + mLabList.size());
                                for (int i = 0; i < mLabList.size(); i++) {
                                    Li("当前标签========" + new Gson().toJson(mLabList.get(i)));
                                    if (mLabList.get(i).getCondition().equals(LAB)) {
                                        mLabList.get(i).setChecked(true);
                                    }
                                }
                            }else {
                                mLabList.get(0).setChecked(true);
                            }
                            mHandler.sendEmptyMessage(2);
                        } else {
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

    /**
     * 获取开卡员工
     */
    private void getStaff() {
        HttpHelper.post(this, "Empl/GetEmplList", new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mCommissionBean = CommonFun.JsonToObj(responseString, StaffCommissionBean.class);
                mVipStaffList.clear();
                ConditionBean conditionBean2 = new ConditionBean();
                conditionBean2.setCondition("全部");
                conditionBean2.setGID("");
                mVipStaffList.add(conditionBean2);
                for (int i = 0; i < mCommissionBean.getData().size(); i++) {
                    ConditionBean conditionBean = new ConditionBean();
                    conditionBean.setCondition(mCommissionBean.getData().get(i).getEM_Name());
                    conditionBean.setGID(mCommissionBean.getData().get(i).getGID());
                    mVipStaffList.add(conditionBean);
                    //mCreateStaffList.add(mCommissionBean.getData().get(i).getEM_Name());
                }

                //员工
                if (!TextUtils.isEmpty(EM_GID)) {
                    Li(EM_GID);
                    for (int i = 0; i < mVipStaffList.size(); i++) {
                        Li("员工提成-----" + new Gson().toJson(mVipStaffList.get(i)));
                        if (mVipStaffList.get(i).getGID().equals(EM_GID)) {
                            mVipStaffList.get(i).setChecked(true);
                            break;
                        }
                    }
                }else {
                    mVipStaffList.get(0).setChecked(true);
                }
                mHandler.sendEmptyMessage(3);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    private void setStoreLabel() {
        HttpHelper.post(ScreenMemberActivity.this, HttpAPI.API().GET_SHOP_LIST, new CallBack() {
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
                    mVipStoreList.add(conditionBean);
                    //mCreateStaffList.add(mCommissionBean.getData().get(i).getEM_Name());
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

                mStoreAdapter = new VipStoreAdapter(ScreenMemberActivity.this,mVipStoreList,mCheckListener,isvisiblestore,pos);
                reVipStore.setAdapter(mStoreAdapter);
                //                mStoreAdapter.setParam(isvisiblestore,isSuperUser);
//                mHandler.sendEmptyMessage(4);

            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }
    /**
     * @param conditionBean ，实体类对象
     * @param isChecked     ，实体类对象是否被选中
     *                      该方法主要是初始列表和选择后的列表刷新，
     *                      下面几个实现的接口方法功能也都是一样一样的操作，
     */
    @Override
    public void levelItemChecked(ConditionBean conditionBean, boolean isChecked) {
        for (int j = 0; j < mVipLevelList.size(); j++) {
            mVipLevelList.get(j).setChecked(false);
        }
        if (isChecked) {
            conditionBean.setChecked(true);
        }
     /*   for (int k = 0; k < mVipLevelList.size(); k++) {
            if (mVipLevelList.get(k).isChecked()) {
                CustomToast.makeText(this, mVipLevelList.get(k).getCondition(), Toast.LENGTH_SHORT).show();
            }
        }*/
    }

    @Override
    public void labItemChecked(ConditionBean conditionBean, boolean isChecked) {
        for (int j = 0; j < mLabList.size(); j++) {
            mLabList.get(j).setChecked(false);
        }
        if (isChecked) {
            conditionBean.setChecked(true);
        }
    }

    @Override
    public void birthdayItemChecked(ConditionBean conditionBean, boolean isChecked) {
        for (int j = 0; j < mVipBirthdayList.size(); j++) {
            mVipBirthdayList.get(j).setChecked(false);
        }
        if (isChecked) {
            conditionBean.setChecked(true);
        }
    }

    @Override
    public void staffItemChecked(ConditionBean conditionBean, boolean isChecked) {
        for (int j = 0; j < mVipStaffList.size(); j++) {
            mVipStaffList.get(j).setChecked(false);
        }
        if (isChecked) {
            conditionBean.setChecked(true);
        }
    }

    @Override
    public void stateItemChecked(ConditionBean conditionBean, boolean isChecked) {
        for (int j = 0; j < mVipStateList.size(); j++) {
            mVipStateList.get(j).setChecked(false);
        }
        if (isChecked) {
            conditionBean.setChecked(true);
        }
    }



}
