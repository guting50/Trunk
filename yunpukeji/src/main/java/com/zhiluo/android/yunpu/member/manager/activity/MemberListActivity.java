package com.zhiluo.android.yunpu.member.manager.activity;


import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.Utils;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.adapter.MemberListAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.member.manager.bean.YSLMemberInfoBean;
import com.zhiluo.android.yunpu.sms.activity.SendMsgActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 会员列表界面
 */
public class MemberListActivity extends BaseActivity implements MemberListAdapter.ImageClick {

    private ArrayList<String> phonelist = new ArrayList<>();
    private ArrayList<AllMemberListBean.DataBean.DataListBean> namelist = new ArrayList<>();
    private int refreshnum = 2;
    private int checkNum, k;

    /**
     * 罗咏哲重构
     */
    private WaveSwipeRefreshLayout mRefresh;//刷新控件
    private WaveSwipeRefreshLayout.OnRefreshListener mRefreshListener;//刷新监听
    private TextView sendmessage;//发送短信按钮
    private RelativeLayout rSendmessage;//发送短信布局
    private EditText etSearch;//搜索框控件
    private CheckBox checkBox;//全选框
    private ImageView mImageScan;//扫码控件声明
    private TextView mTvBack, mTvVipTotal;//返回,会员总数
    private AllMemberListBean mMemberListBean;//会员信息实体
    private List<AllMemberListBean.DataBean.DataListBean> mMemberList;//会员信息
    private List<MemberInfoBean.DataBean> mMemberInfoList;//会员详细信息
    private int mIndex;//页码
    private int mPageNum;//每页数据条数
    private StringBuilder mCondition;//查询条件
    private String mGid;//会员等级GID
    private int mIsForver;//会员是否永久有效 1 永久有效，0 不是永久有效
    private int mMemberState;//会员状态 0 正常，1 锁定，2 挂失
    private int mMemberBirthday;//会员生日 0 今天，1 本周，2 本月
    private Handler mHandler;
    private MemberListAdapter mAdapter;//会员列表信息Adapter
    private RelativeLayout mIvScreenMembers;//筛选会员
    private BaseListView mMemberListView;//会员列表ListView
    private String mLabel;//会员标签
    private AsyncHttpClient mClient;
    private Map<Integer, Boolean> mIsCheckedMap;
    private int mPageTotal;//数据总页数
    private int mCurrentPosition;//当前滑动位置
    private boolean mIsLoadMore;
    private String mSelectCondition;//搜索条件
    private FloatingActionButton mFab;
    private int mVipTotal;//会员总数
    private String mStoreGid;//店铺Gid
    private String teStartTime,teEndTime;
    private boolean mAddMember, mSendMessage;
    private LoginUpbean mLoginBean;
    private String mEM_GID;//开卡员工GID
    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;//延时标志
    private long pretime;//第一次的时间
    private TextView tvtotal2;
    private LinearLayout ll2;

    private CardOperationUtils mCard;

    private List<YSLMemberInfoBean.DataBean.DataListBean> mVipInfoLIst;
    private YSLMemberInfoBean mVipInfo;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    String lim = "";
    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean isallStoremember = false;
    private String memberListType = "home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(MemberListActivity.this);
        setContentView(R.layout.activity_ysl_memberlist);
        initView();
        initVariable();//初始化变量
        initSwitch();
        loadData();//加载数据
        setListener();
//        mCard = new CardOperationUtils(this, etSearch);

        //V1S刷卡
        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE){
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            Intent nfcIntent = new Intent(this, getClass());
            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mPendingIntent =
                    PendingIntent.getActivity(this, 0, nfcIntent, 0);
        }

    }

    /**
     * 初始化控件
     */
    private void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//进入页面关闭软键盘
        mIvScreenMembers = (RelativeLayout) findViewById(R.id.iv_member_filtrates);
        mImageScan = (ImageView) findViewById(R.id.iv_scan_memberlistactivity);
        sendmessage = findViewById(R.id.tv_sendmessage);
        checkBox = (CheckBox) findViewById(R.id.cb_allchose);
        rSendmessage = (RelativeLayout) findViewById(R.id.rl_sent);
        mTvBack = (TextView) findViewById(R.id.tv_back_memberlistactivity);
        mMemberListView = (BaseListView) findViewById(R.id.lv_vipmanege_activity);
        etSearch = (EditText) findViewById(R.id.et_membercard_memberlistactivity);
        mRefresh = (WaveSwipeRefreshLayout) findViewById(R.id.srl_freshmanage_activity);
        mRefresh.setBackgroundColor(getResources().getColor(R.color.yunpu));
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mTvVipTotal = (TextView) findViewById(R.id.tv_vip_total1);
        tvtotal2 = (TextView) findViewById(R.id.tv_vip_total2);
        ll2 = (LinearLayout) findViewById(R.id.ll2);
    }

    /**
     * 罗咏哲重构
     * 初始化变量
     */
    private void initVariable() {
        Utils.init(this);
        preferences = getSharedPreferences("viplocation", MODE_PRIVATE);
        editor = preferences.edit();
        mClient = new AsyncHttpClient();
        mIsCheckedMap = new HashMap<>();
        mIndex = 1;
        mPageNum = 20;
        mCondition = new StringBuilder();
        mGid = "";
        mEM_GID = "";
        mIsForver = -1;
        mMemberState = -1;
        mMemberBirthday = -1;
        mLabel = "";
        mStoreGid = (String) uSharedPreferencesUtiles.get(MyApplication.getmContext(), "Store", "");
        mHandler = new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    //初始化页面
                    case 999:
                        setMemberAdapter();
                        mTvVipTotal.setText("共有" + mVipTotal + "位会员");
                        break;
                }
                return false;
            }
        });
        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        if (mLoginBean != null) {
            if (mLoginBean.getData().getUM_IsAmin() == 1) {
                mSendMessage = true;
                mAddMember = true;
            }else {
                for (int i = 0; i < mLoginBean.getData().getMenuInfoList().size(); i++) {
                    LoginUpbean.DataBean.MenuInfoListBean bean = mLoginBean.getData().getMenuInfoList().get(i);
                    if (bean.getMM_Name().equals("发送短信")) {
                        mSendMessage = true;
                    }
                    if (bean.getMM_Name().equals("新增会员")) {
                        mAddMember = true;
                    }
                }
            }
        }
    }
    private void initSwitch() {
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {
                    //是否显示他店会员
                    if ("210".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            isallStoremember = true;
                        } else {
                            isallStoremember = false;
                        }
                    }
                }
            }
        }
    }

    /**
     * 罗咏哲重构
     * 加载数据
     */

    private void loadData() {
        checkMemberInfo(mIndex, mCondition.toString());//初始化数据
        Intent intent = getIntent();
        if (intent!=null){
            memberListType = intent.getStringExtra("memberlisttype") ==null?"home":intent.getStringExtra("memberlisttype");
            namelist = (ArrayList<AllMemberListBean.DataBean.DataListBean>) intent.getSerializableExtra("intent");
            phonelist = intent.getStringArrayListExtra("phonelist");
            if (memberListType.equals("msg")){
                rSendmessage.setVisibility(View.VISIBLE);
                mFab.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 设置Adapter
     */
    private void setMemberAdapter() {
        if (mAdapter ==null){
            mAdapter = new MemberListAdapter(MemberListActivity.this, mMemberList, this);
            mMemberListView.setAdapter(mAdapter);
//            mMemberListView.setSelection(mCurrentPosition);
        }else {
            mAdapter.setParams(mMemberList);
            mAdapter.notifyDataSetChanged();
        }

        //mFab.attachToListView(mMemberListView);//这个会引起每次上滑加载更多的时候加载后列表数据都从第一行开始展示，极不友好
    }

    /**
     * 设置监听
     */
    private void setListener() {
        /**
         * 过滤筛选
         */
        mIvScreenMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (YSLUtils.isFastClick()) {
                    Intent intent = new Intent(MemberListActivity.this, ScreenMemberActivity.class);
                    intent.putExtra("GID", mGid);
                    intent.putExtra("STATE", mMemberState);
                    intent.putExtra("BIRTHDAY", mMemberBirthday);
                    intent.putExtra("LABEL", mLabel);
                    intent.putExtra("EM_GID", mEM_GID);
                    intent.putExtra("STOREID", mStoreGid);
                    intent.putExtra("StartTime", teStartTime);
                    intent.putExtra("EndTime", teEndTime);
                    startActivityForResult(intent, 777);
                }
            }
        });
        mRefreshListener = new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rSendmessage.setVisibility(View.GONE);
                checkMemberInfo(1, mSelectCondition);
                refreshnum = 2;
            }

            @Override
            public void onLoad() {
                if (refreshnum <= mPageTotal) {
                    mIsLoadMore = true;
                    checkMemberInfo(refreshnum, mSelectCondition);
                    refreshnum++;
                } else {
                    CustomToast.makeText(MemberListActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    mRefresh.setLoading(false);
                }
            }

            @Override
            public boolean canLoadMore() {
                return true;
            }

            @Override
            public boolean canRefresh() {
                return true;
            }
        };

        mRefresh.setOnRefreshListener(mRefreshListener);

        etSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //取消未完成的请求
//                mClient.cancelAllRequests(true);
//                mClient.cancelRequests(MemberListActivity.this, true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pretime = System.currentTimeMillis();
                //已经调用了一次就不在调用
                if (!hasTimerDown) {
                    doQurry();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mMemberList != null){
                    if (mMemberList.size() > 0) {
                        mMemberList.clear();
                        if (mAdapter !=null){
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
                if (s.toString() != null) {
                    mSelectCondition = s.toString();
                    //checkMemberInfo(1, etSearch.getText().toString());
                }
            }
        });
        mImageScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(MemberListActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
        //q全选按钮
        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!mMemberList.isEmpty()) {
                    if (checkBox.isChecked()) {
                        mIsCheckedMap.clear();
                        rSendmessage.setVisibility(View.VISIBLE);
                        // 遍历list的长度，将MyAdapter中的map值全部设为true
                        for (int i = 0; i < mMemberList.size(); i++) {
                            mMemberList.get(i).setChecked(true);
                            if (mMemberList.get(i).getVIP_CellPhone() != null) {
                                phonelist.add(mMemberList.get(i).getVIP_CellPhone());
                            }
                        }
                        tvtotal2.setText("已选择"+mMemberList.size()+"位会员");

                    } else {
                        rSendmessage.setVisibility(View.GONE);
                        phonelist.clear();
                        // 遍历list的长度，将已选的按钮设为未选
                        for (int i = 0; i < mMemberList.size(); i++) {
                            mMemberList.get(i).setChecked(false);
                        }
                        tvtotal2.setText("已选择"+0+"位会员");


                    }
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        /**
         * 返回首页
         */
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (memberListType.equals("msg")) {
                    finish();
                } else {
                    Intent intent = new Intent(MemberListActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        /**
         * 添加会员
         */
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (YSLUtils.isOpenActivon("新增会员")) {
                    Intent intent = new Intent(MemberListActivity.this, AddMemberActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    CustomToast.makeText(MemberListActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /**
         * 会员详情
         */
        mMemberListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent intent = new Intent(MemberListActivity.this, MemberDetailsActivity.class);
                Intent intent = new Intent(MemberListActivity.this, YSLMemberDetailsActivity.class);
                Bundle bundle = new Bundle();
                //不完整的会员信息
                bundle.putSerializable("VIP_INFO", mMemberList.get(position));
                //带计次的会员信息
                bundle.putSerializable("YSL_VIP_INFO", mVipInfoLIst.get(position));
                intent.putExtra("bundle", bundle);
                editor.putInt("position", position);
                editor.commit();
                startActivity(intent);

            }
        });
        mMemberListView.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < mMemberList.size(); i++) {
                    mMemberList.get(i).setLongClick(true);
                }
                mAdapter.notifyDataSetChanged();
                ll2.setVisibility(View.GONE);
                tvtotal2.setText("共有" + mVipTotal + "位会员");
                return false;
            }
        });
        /**
         * 发送短信
         */
        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (mSendMessage) {
                    // TODO: 2016/12/13 0013 跳转发送短信
                    Intent intent = new Intent(MemberListActivity.this, SendMsgActivity.class);
                    intent.putExtra("mSendMessage",mSendMessage);
                    if (namelist==null){
                        namelist = new ArrayList<>();
                    }
                    if (phonelist ==null){
                        phonelist = new ArrayList<>();
                    }
                    phonelist.clear();
                    namelist.clear();
                    for (int i = 0; i < mMemberList.size(); i++) {
                        if (mMemberList.get(i).isChecked()) {
                            AllMemberListBean.DataBean.DataListBean bean = mMemberList.get(i);
                            if (mMemberList.get(i).getVIP_CellPhone() != null&& !mMemberList.get(i).getVIP_CellPhone().equals("")) {
                                phonelist.add(mMemberList.get(i).getVIP_CellPhone());
                                namelist.add(bean);
                            }

                        }
                    }
                    if (phonelist.size()<=0){
                        CustomToast.makeText(MemberListActivity.this, "会员手机号码不能为空", Toast.LENGTH_SHORT).show();
                    }else {
                        ActivityManager.getInstance().removeActivity(MemberListActivity.this);
                        intent.putStringArrayListExtra("phonelist", phonelist);
                        intent.putExtra("namelist", namelist);

                        if (memberListType.equals("msg")) {
                            setResult(222,intent);
                            finish();
                        }else {
                            startActivity(intent);
                        }
                    }

//                } else {
//                    CustomToast.makeText(MemberListActivity.this, "没有此权限", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }



    //获取系统隐式启动的
    @Override
    public void onNewIntent(Intent intent) {
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String CardId = ByteArrayToHexString.ByteArrayToHex(tagFromIntent.getId());
        if (null != CardId) {
            MyApplication.VIP_CARD =  CardId;
            while (CardId.length()<10){
                CardId = "0"+CardId;
            }
            etSearch.setText(CardId);

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mNFCAdapter != null) {
            //隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }


        /**
         * lkl、sunmi 、basewin的检卡 读卡操作
         */
        Li("开始检卡");

        //new CardOperationUtils(this, etSearch);
        if (!MyApplication.IS_SUNMI_POS_V1S_DEVICE){
            mCard = new CardOperationUtils(this, etSearch);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onStop() {
        //终止检卡
        new CardOperationUtils().close();
//        mCard.close();
        super.onStop();
        Li("onstop---结束检卡");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNFCAdapter != null) {
            //隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        loadData();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            etSearch.setText(scanResult);
        }
        if (resultCode == 777 && requestCode == 777) {
            Intent intent = getIntent();
            if (intent != null) {
                mGid = data.getStringExtra("GID");
                mMemberState = data.getIntExtra("STATE", -1);
                mMemberBirthday = data.getIntExtra("BIRTHDAY", -1);
                mLabel = data.getStringExtra("LABEL");
                mEM_GID = data.getStringExtra("EM_GID");
                mStoreGid = data.getStringExtra("STOREID");
                teStartTime = data.getStringExtra("StartTime");
                teEndTime = data.getStringExtra("EndTime");

                checkMemberInfo(1, mCondition.toString());
            }
        }
    }

    /**
     * 罗咏哲重构
     * 查询会员信息
     *
     * @param index     页码
     * @param condition 查询条件
     */
    public void checkMemberInfo(int index, String condition) {
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", mPageNum);
        //params.put("SM_GID", mStoreGid);//店铺的GID
        Log.i(TAG, "checkMemberInfo: " + mStoreGid);
        if (condition != null) {
            params.put("CardOrNameOrCellPhoneOrFace", condition);
        } else {
            params.put("SM_GID", mStoreGid);
        }
        if (!"".equals(mGid) && mGid != null) {
            params.put("VG_GID", mGid);
        }
        if (mIsForver >= 0) {
            params.put("VIP_IsForver", mIsForver);
        }
        if (mMemberState >= 0) {
            params.put("VIP_State", mMemberState);
        }
        if (mMemberBirthday >= 0) {
            params.put("DayType", mMemberBirthday);
        }
        if (!"".equals(mLabel) && mLabel != null) {
            params.put("VIP_Label", mLabel);
        }

//        if (isallStoremember) {
//            params.put("SM_GID", "");
//        } else {
            if (mStoreGid != null && !mStoreGid.equals("")) {
                params.put("SM_GID", mStoreGid);
            }
//        }
        if (teStartTime!=null&&!teStartTime.equals("")){
            params.put("VIP_StartDate", teStartTime);
            params.put("VIP_EndDate", teEndTime);
        }
        params.put("EM_GID", mEM_GID);
        params.put("VIP_SortType", 0);

        Le("------" + params.toString());
        HttpHelper.post(this, HttpAPI.API().VIPLIST, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                if (mMemberList != null && !mIsLoadMore) {
                    mMemberList.clear();
                    mVipInfoLIst.clear();
                }

                List<YSLMemberInfoBean.DataBean.DataListBean> mlist = new ArrayList<>();
                List<AllMemberListBean.DataBean.DataListBean> list = new ArrayList<>();
                if (mMemberList != null) {
                    list.addAll(mMemberList);
                    mlist.addAll(mVipInfoLIst);
                }
                mVipInfo = CommonFun.JsonToObj(responseString, YSLMemberInfoBean.class);
                mMemberListBean = CommonFun.JsonToObj(responseString, AllMemberListBean.class);
                if (mVipInfo.getData().getDataList().size()==0){
                    if (MyApplication.VIP_CARD !=null){
                        checkMemberInfo(1,MyApplication.VIP_CARD);
                        MyApplication.VIP_CARD = null;
                    }
                }
                mVipInfoLIst = mVipInfo.getData().getDataList();
                mMemberList = mMemberListBean.getData().getDataList();
                mPageTotal = mMemberListBean.getData().getPageTotal();
                mVipTotal = mMemberListBean.getData().getDataCount();
                list.addAll(mMemberList);
                mlist.addAll(mVipInfoLIst);
                mMemberList = list;
                mVipInfoLIst = mlist;
                if (phonelist!=null && phonelist.size()>0){
                    for (int i = 0;i<phonelist.size();i++){
                        for (int j = 0;j<mMemberList.size();j++){
                            if (phonelist.get(i).equals(mMemberList.get(j).getVIP_CellPhone())){
                                mMemberList.get(j).setChecked(true);
                            }
                        }
                    }
                }
                mIsLoadMore = false;
                mRefresh.setRefreshing(false);
                mRefresh.setLoading(false);
                mHandler.sendEmptyMessage(999);

            }

            @Override
            public void onFailure(String msg) {
                if (MyApplication.VIP_CARD !=null){
                    checkMemberInfo(1,MyApplication.VIP_CARD);
                    MyApplication.VIP_CARD = null;
                }

            }
        });
    }

    @Override
    public void click(View v) {
        int pos = (int) v.getTag();
        if (mMemberList.get(pos).isChecked()) {
            mMemberList.get(pos).setChecked(false);
            checkBox.setChecked(false);
        } else {
            mMemberList.get(pos).setChecked(true);
        }
        rSendmessage.setVisibility(View.GONE);
        int num = 0;
        for (int i = 0; i < mMemberList.size(); i++) {
            if (mMemberList.get(i).isChecked()) {
                rSendmessage.setVisibility(View.VISIBLE);
                break;
            }
        }
        for (int i = 0; i < mMemberList.size(); i++) {
            if (mMemberList.get(i).isChecked()) {
                num = num+1;
            }
        }
        if (num == mMemberList.size()){
            checkBox.setChecked(true);
        }
        tvtotal2.setText("已选择"+num+"位会员");
        mAdapter.notifyDataSetChanged();
    }

    private class InputHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0://0正在输入
                    Li("正在输入");
                    break;
                case 1://1输入完成
                    Li("输入完成");
                    doQurry();
                    break;
            }
        }

    }

    /**
     * 查询
     */
    private void doQurry() {
        hasTimerDown = true;
        //当前时间与输入最后一次时间 做差 若大于1.5秒那么可能已经输入完成那么开始验证
        if (System.currentTimeMillis() - pretime > 500) {
            checkMemberInfo(1, etSearch.getText().toString());
            //可能输入完毕
            //便于下次重新检验输入事件重置为false;
            hasTimerDown = false;
        } else {
            //若间隔不大于0.5秒 那么继续发给handler每隔300毫秒进行判断是否间隔大于0.5秒
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }

}

