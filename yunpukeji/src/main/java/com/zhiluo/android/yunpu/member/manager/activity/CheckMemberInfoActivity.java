package com.zhiluo.android.yunpu.member.manager.activity;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.adapter.CheckMemberInfoAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 选择会员界面
 * 作者：罗咏哲 on 2017/7/18 14:01.
 * 邮箱：137615198@qq.com
 */
public class CheckMemberInfoActivity extends BaseActivity {
    @Bind(R.id.ll_check_member_search)
    LinearLayout llCheckMemberSearch;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;
    private EditText etMemberInfo;//查询条件
    private ImageView ivScan;//扫描
    private TextView mCheckScreen;//筛选
    private BaseListView lvMemberInfo;//会员信息列表
    private String mInputContent;//输入框输入的内容
    private List<AllMemberListBean.DataBean.DataListBean> mMemberList = new ArrayList<>();//会员信息集合
    private AllMemberListBean mAllMemberListBean;
    private AllMemberListBean.DataBean.DataListBean mMemberInfo;
    private CheckMemberInfoAdapter mAdapter;//会员列表适配器
    private Handler mHandler;//
    private Dialog mDialog;//加载提示Dialog
    private WaveSwipeRefreshLayout mRefreshLayout;//刷新
    private int mRefreshIndex = 2;
    private boolean mIsLoadMore;//加载更多
    private int mPageTotal;//数据总页数
    private int mCurrentPosition;//当前滑动位置
    private String mStoreGid, mGradeGid, mStatusGid, mPayWayCode;
    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间
    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;//参数开关
    private boolean mMustCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_member_info);
        ButterKnife.bind(this);

        initView();
        initVariable();
        setListener();
        loadData();
        //V1S刷卡
        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            Intent nfcIntent = new Intent(this, getClass());
            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mPendingIntent =
                    PendingIntent.getActivity(this, 0, nfcIntent, 0);
        }
    }

    //获取系统隐式启动的
    @Override
    public void onNewIntent(Intent intent) {
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String CardId = ByteArrayToHexString.ByteArrayToHex(tagFromIntent.getId());
        if (null != CardId) {
            MyApplication.VIP_CARD = CardId;
            while (CardId.length() < 10) {
                CardId = "0" + CardId;
            }
            etMemberInfo.setText(CardId);

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
        if (!MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            new CardOperationUtils(this, etMemberInfo);
        }

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
    protected void onStop() {

        //终止检卡
        new CardOperationUtils().close();

        Log.d("TAG", "onStop: ");
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        etMemberInfo = (EditText) findViewById(R.id.et_check_member_search);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//进入页面关闭软键盘
        ivScan = (ImageView) findViewById(R.id.iv_check_member_scan);
        lvMemberInfo = (BaseListView) findViewById(R.id.lv_check_member_info);
        mRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.refresh_check_member);
        mCheckScreen = findViewById(R.id.iv_check_member_screen);
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        mStoreGid = (String) uSharedPreferencesUtiles.get(MyApplication.getmContext(), "Store", "");
        mInputContent = "";
        mMemberInfo = new AllMemberListBean.DataBean.DataListBean();
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        setAdapter();
                        break;
                    case 2:
                        //回传给开启Activity的Activity会员信息实体
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("VIP", mMemberInfo);
                        intent.putExtras(bundle);
                        setResult(2222, intent);
                        finish();
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 加载数据
     */
    private void loadData() {
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {

                    //必须刷卡
                    if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mMustCard = true;
                            mCheckScreen.setEnabled(false);
                            llCheckMemberSearch.setEnabled(false);
                            llCheckMemberSearch.setBackgroundColor(getResources().getColor(R.color.lightgray));
                            etMemberInfo.setHint("请使用刷卡机刷卡");
                            etMemberInfo.setFocusable(false);
                            etMemberInfo.setFocusableInTouchMode(false);
                            ivScan.setEnabled(false);


                        } else {
                            mMustCard = false;
                            mCheckScreen.setEnabled(true);
                            llCheckMemberSearch.setEnabled(true);
                            llCheckMemberSearch.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                            etMemberInfo.setHint("请输入会员卡号/手机号");
                            etMemberInfo.setFocusable(true);
                            etMemberInfo.setFocusableInTouchMode(true);
                            ivScan.setEnabled(true);
                        }
                    }

                }
            }
        } else {
            getSystemSwitch();
        }

        Intent inten = getIntent();
//        if (inten!=null){
        String number = inten.getStringExtra("number");
        if (!mMustCard) {
            if (number != null && !number.equals("")) {
                etMemberInfo.setText(number);
            } else {
                checkMemberInfo(mInputContent, 1, 20);
            }
        }

        String isaddmember = inten.getStringExtra("addmember");
        if (isaddmember != null && !isaddmember.equals("") && isaddmember.equals("yes")) {
            llBottom.setVisibility(View.VISIBLE);

        }else {
            llBottom.setVisibility(View.GONE);
        }
    }

    /**
     * 获取系统开关
     */
    public void getSystemSwitch() {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean entity = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                mSwitchEntity = entity.getData().getGetSysSwitchList();
                CacheData.saveObject("switch", mSwitchEntity);//缓存系统开关到本地
                if (mSwitchEntity != null) {
                    if (mSwitchEntity.size() > 0) {
                        for (int i = 0; i < mSwitchEntity.size(); i++) {

                            //必须刷卡
                            if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                                if (mSwitchEntity.get(i).getSS_State() == 1) {
                                    mMustCard = true;
                                    mCheckScreen.setEnabled(false);
                                    llCheckMemberSearch.setEnabled(false);
                                    llCheckMemberSearch.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                    etMemberInfo.setHint("请使用刷卡机刷卡");
                                    etMemberInfo.setFocusable(false);
                                    etMemberInfo.setFocusableInTouchMode(false);
                                    ivScan.setEnabled(false);
                                } else {
                                    mMustCard = false;
                                    mCheckScreen.setEnabled(true);
                                    llCheckMemberSearch.setEnabled(true);
                                    llCheckMemberSearch.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                                    etMemberInfo.setHint("请输入会员卡号/手机号");
                                    etMemberInfo.setFocusable(true);
                                    etMemberInfo.setFocusableInTouchMode(true);
                                    ivScan.setEnabled(true);
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(CheckMemberInfoActivity.this, "获取系统开关失败" + msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(CheckMemberInfoActivity.this, HttpAPI.API().PRE_LOAD, callBack);
    }


    /**
     * 设置适配器
     */
    private void setAdapter() {
        mAdapter = new CheckMemberInfoAdapter(CheckMemberInfoActivity.this, mMemberList);
        lvMemberInfo.setAdapter(mAdapter);
        lvMemberInfo.setSelection(mCurrentPosition);
        Li("位置----" + mCurrentPosition);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(CheckMemberInfoActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
        //设置输入框监听
        etMemberInfo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pretime = System.currentTimeMillis();
                if (!hasTimerDown) {
                    doQurry();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                mInputContent = editable.toString();
                //checkMemberInfo(mInputContent, 1, 20);
            }
        });
        //为ListView子控件添加点击监听
        lvMemberInfo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mMemberInfo = mMemberList.get(position);//获得被点击的Item的对象
                mHandler.sendEmptyMessage(2);//通知主线程加载数据完成
            }
        });
        findViewById(R.id.tv_check_member_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //下拉刷新，上拉加载更多
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!mMustCard) {
                    checkMemberInfo(mInputContent, 1, 20);
                    mRefreshIndex = 2;
                } else {
                    mRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onLoad() {
                if (!mMustCard) {
                    if (mRefreshIndex <= mPageTotal) {
                        mIsLoadMore = true;
                        checkMemberInfo(mInputContent, mRefreshIndex, 20);
                        mRefreshIndex++;
                    } else {
                        CustomToast.makeText(CheckMemberInfoActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                        mRefreshLayout.setLoading(false);
                    }
                } else {
                    mRefreshLayout.setLoading(false);
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
        });
        lvMemberInfo.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    mCurrentPosition = lvMemberInfo.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
        //筛选条件
        mCheckScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YSLUtils.isFastClick()) {
                    Intent intent = new Intent(CheckMemberInfoActivity.this, ScreenStoreActivity.class);
                    intent.putExtra("STORE", mStoreGid);
                    intent.putExtra("GRADE", mGradeGid);
                    intent.putExtra("YJYX", mPayWayCode);
                    intent.putExtra("STATUS", mStatusGid);
                    startActivityForResult(intent, 44);
                }
            }
        });

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllMemberListBean.DataBean.DataListBean bean = new AllMemberListBean.DataBean.DataListBean();
                //回传给开启Activity的Activity会员信息实体
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("VIP", bean);
                intent.putExtras(bundle);
                setResult(2222, intent);
                finish();

            }
        });
    }

    /**
     * 查询会员信息
     *
     * @param condition 查询条件
     */
    private void checkMemberInfo(String condition, int index, int size) {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("PageIndex", index);
        params.put("PageSize", size);
        params.put("CardOrNameOrCellPhoneOrFace", condition);
        params.put("VIP_State", mStatusGid);
        params.put("VIP_Label", "");
        params.put("DayType", "");
        params.put("VG_GID", mGradeGid);
        params.put("SM_GID", mStoreGid);
        params.put("VIP_IsForver", mPayWayCode);
        if (!condition.isEmpty()) {
            params.put("CardOrNameOrCellPhoneOrFace", condition);
        }
        MyTextHttpResponseHandler responseHandler = new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                if (mMemberList != null) {
                    if (mMemberList != null && !mIsLoadMore) {
                        mMemberList.clear();
                    }
                    List<AllMemberListBean.DataBean.DataListBean> list = new ArrayList<>();

                    mAllMemberListBean = CommonFun.JsonToObj(responseString, AllMemberListBean.class);
                    if (mAllMemberListBean.getData().getDataList().size() == 0) {
                        if (MyApplication.VIP_CARD != null) {
                            checkMemberInfo(MyApplication.VIP_CARD, 1, 20);
                            MyApplication.VIP_CARD = null;
                        }
                    }
                    mPageTotal = mAllMemberListBean.getData().getPageTotal();
                    list = mAllMemberListBean.getData().getDataList();
                    mMemberList.addAll(list);
                    mIsLoadMore = false;
                    mRefreshLayout.setRefreshing(false);
                    mRefreshLayout.setLoading(false);
                    mHandler.sendEmptyMessage(1);//通知主线程加载数据完成
                }
            }

            @Override
            public void onFailure(String msg) {
                if (MyApplication.VIP_CARD != null) {
                    checkMemberInfo(MyApplication.VIP_CARD, 1, 20);
                    MyApplication.VIP_CARD = null;
                } else {
                    mIsLoadMore = false;
                    mRefreshLayout.setRefreshing(false);
                    mRefreshLayout.setLoading(false);
                }
            }
        };
        client.post(HttpAPI.API().VIPLIST, params, responseHandler);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 44 && resultCode == 44) {
            mStoreGid = data.getStringExtra("STORE");
            mGradeGid = data.getStringExtra("GRADE");
            mPayWayCode = data.getStringExtra("YJYX");
            mStatusGid = data.getStringExtra("STATUS");
            checkMemberInfo(mInputContent, 1, 20);
        }
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            etMemberInfo.setText(scanResult);
        }
    }

    @OnClick(R.id.ll_check_member_search)
    public void onViewClicked() {
    }

    private class InputHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://1输入完成
                    doQurry();
                    break;
            }
        }
    }

    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {
            checkMemberInfo(mInputContent, 1, 20);
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }

}
