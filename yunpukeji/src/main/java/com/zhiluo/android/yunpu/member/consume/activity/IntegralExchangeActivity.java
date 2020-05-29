package com.zhiluo.android.yunpu.member.consume.activity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.goods.consume.adapter.GoodsConsumeRightAdapter;
import com.zhiluo.android.yunpu.goods.consume.view.GoodsView;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsTypeExpandAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;
import com.zhiluo.android.yunpu.mvp.model.PasswordVerifyBean;
import com.zhiluo.android.yunpu.mvp.presenter.PostVipPresenter;
import com.zhiluo.android.yunpu.mvp.presenter.WareHousingPresenter;
import com.zhiluo.android.yunpu.mvp.view.IPostVipView;
import com.zhiluo.android.yunpu.mvp.view.IWareHousingView;
import com.zhiluo.android.yunpu.print.bean.JFDH_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.PrintSetBean;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.setting.bluetooths.BluetoothUtil;
import com.zhiluo.android.yunpu.sms.jsonbean.SmsSwitch;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;


/**
 * 积分兑换
 */
public class IntegralExchangeActivity extends BaseActivity implements GoodsConsumeRightAdapter.ItemViewClick {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    private TextView tvname, tvcard, tvjifen, tvyue;
    private MemberInfoBean.DataBean vipdatalist;
    private String jifen;
    private double basepoint;
    private String name, vyue;
    private SharedPreferences sp, sp2, pref;
    private SharedPreferences.Editor editor;
    private BluetoothUtil bluetoothUtil = new BluetoothUtil(this);
    private String mMaccode;
    private boolean flag;
    private String muser;
    private String mytitle;
    private String strCard;


    private ExpandableListView mExpandableListView;
    private WareHousingPresenter mPresenter;
    private IWareHousingView mView;
    private List<GoodsType.DataBean> mParent;
    private Map<GoodsType.DataBean, List<GoodsType.DataBean>> mMap;
    private int mLastParentPos;
    int expandFlag = -1;//控制列表的展开
    GoodsTypeExpandAdapter adapter;
    private GoodsConsumeRightAdapter mRightAdapter;//右边ListView的Adapter
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodsList;//商品信息（根据会员卡号得到）
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mHaveChoosedGoodList;//已选商品信息
    private WaveSwipeRefreshLayout mRefreshLayout;
    private BaseListView mGoodsListView;
    private TextView tvTotal;
    private EditText etSearch;//搜索框
    private String mTypeGid;//类型GID
    private int mGiftNum;//已选礼品数量
    private double mNeedIntegral;//需要积分
    private double mSurplusIntegral;//剩余积分
    private boolean tag = true;
    private AllMemberListBean.DataBean.DataListBean mVipInfo;//会员信息
    private int mRefreshIndex = 2;
    private boolean mIsLoadMore;//加载更多
    private int mPageTotal;//数据总页数
    private int mCurrentPosition;//当前滑动位置
    private String mSearchCondition;//搜索条件
    private ImageView ivScan;
    private int mShoppingCartWidth;//购物车宽度
    private ViewGroup mViewGroup;
    private SweetAlertDialog mSweetAlertDialog;
    private TextView mTvChooseMember;
    private AllMemberListBean.DataBean.DataListBean mMemberInfo;//会员信息
    private String MDZZ;
    private String mOrderNum;//订单号
    private TextView btnExchange;//兑换
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean mPasswordSwitch = false;//积分兑换密码开关

    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间
    private CheckBox cbPrint, cbMessage;//打印和发送短信
    private int pribean = 0;
    private RelativeLayout rChoiseVip;//选择会员
    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;
    private PostVipPresenter vippresenter;
    private IPostVipView vipView;
    private boolean isSearch = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral_exchange);
        ButterKnife.bind(this);
        initSp();
        initView();
        initdata();
        pref = this.getSharedPreferences("recepits", Context.MODE_PRIVATE);
        init();
        setListener();
        setCbPrint();
        setCbShortMessage("013");

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

    private void init() {
        mHaveChoosedGoodList = new ArrayList<>();

        mPresenter = new WareHousingPresenter(this);
        mPresenter.onCreate("");
        mView = new IWareHousingView() {
            @Override
            public void getTypeSuccess(List<GoodsType.DataBean> parent, Map<GoodsType.DataBean, List<GoodsType.DataBean>> map) {
                mMap = map;
                mParent = parent;
                adapter = new GoodsTypeExpandAdapter(IntegralExchangeActivity.this,
                        parent, map);
                mExpandableListView.setGroupIndicator(null);
                mExpandableListView.setVerticalScrollBarEnabled(false);
                mParent.get(0).setChecked(true);
                mLastParentPos = 0;
                expandFlag = 0;
                mExpandableListView.setAdapter(adapter);
                mExpandableListView.expandGroup(0);
            }

            @Override
            public void getTypeFail(String result) {

            }

            @Override
            public void getGoodsSuccess(GoodsCheckResponseByType entity) {
                mPageTotal = entity.getData().getPageTotal();
                if (mHaveChoosedGoodList.size() > 0) {
                    for (int i = 0; i < mHaveChoosedGoodList.size(); i++) {
                        for (int j = 0; j < entity.getData().getDataList().size(); j++) {
                            if (entity.getData().getDataList().get(j).getGID()
                                    .equals(mHaveChoosedGoodList.get(i).getGID())) {
                                entity.getData().getDataList().get(j).setNum(mHaveChoosedGoodList.get(i).getNum());
                            }
                        }
                    }
                }
                if (mGoodsList != null && mIsLoadMore) {
                    mGoodsList.addAll(entity.getData().getDataList());
                } else {
                    mGoodsList = entity.getData().getDataList();
                }
                if (mRightAdapter == null) {
                    mRightAdapter = new GoodsConsumeRightAdapter(IntegralExchangeActivity.this,
                            mGoodsList, IntegralExchangeActivity.this);
                    mGoodsListView.setAdapter(mRightAdapter);
                } else {
                    mRightAdapter.setParm(mGoodsList);
                    mRightAdapter.notifyDataSetChanged();
                }
                tvTotal.setText(Decima2KeeplUtil.stringToDecimal(mNeedIntegral + ""));
                mIsLoadMore = false;
                mRefreshLayout.setLoading(false);
                mRefreshLayout.setRefreshing(false);
//                mGoodsListView.setSelection(mCurrentPosition);
            }

            @Override
            public void getGoodsFail(String result) {

            }

            @Override
            public void getComboSuccess(GoodsCheckResponseByType entity) {

            }


            @Override
            public void getCombofail(String result) {

            }
        };
        mPresenter.attachView(mView);
        getGoods(1, 20);
    }


    private void getGoods(int index, int size) {
        RequestParams params = new RequestParams();
        params.put("PT_GID", mTypeGid);
        params.put("PageIndex", index);
        params.put("PageSize", size);
        params.put("PM_IsService", 2);
        params.put("PM_CodeOrNameOrSimpleCode", mSearchCondition);
        mPresenter.getGoods(params);
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
        if (!MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            new CardOperationUtils(this, etSearch);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    private void initSp() {
        tvTitle.setText("积分兑换");
        sp = getSharedPreferences("setting", MODE_PRIVATE);
        sp2 = getSharedPreferences("login", 0);
        editor = sp.edit();
        mMaccode = sp.getString("adress", "");
        muser = sp.getString("myuser", "");
        mytitle = sp.getString("mytitle", "");
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {
                    if ("206".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mPasswordSwitch = true;
                        } else {
                            mPasswordSwitch = false;
                        }
                    }
                }
            } else {
                mPasswordSwitch = false;
            }
        } else {
            mPasswordSwitch = false;
        }
    }

//    /**
//     * 查询单个会员
//     *
//     * @param k
//     */
//    private void postVip(String k) {
//
//
//        RequestParams params = new RequestParams();
//        params.put("VCH_Card", k);
//        HttpHelper.post(this, "VIP/QuerySingle", params, new CallBack() {
//            @Override
//            public void onSuccess(String responseString, Gson gson) {
//                VipDetails vipdetails = CommonFun.JsonToObj(responseString, VipDetails.class);
//                vipdatalist = vipdetails.getData();
//                initnew();
//                String mMemberName = vipdatalist.getVIP_Name();
//                if (mMemberName != null && !"".equals(mMemberName)) {
//                    mTvChooseMember.setText(mMemberName);
//                }else {
//                    mTvChooseMember.setText(vipdatalist.getVCH_Card());
//                }
//            }
//
//            @Override
//            public void onFailure(String msg) {
//
//                if (MyApplication.VIP_CARD !=null){
//                    postVip(MyApplication.VIP_CARD);
//                    MyApplication.VIP_CARD = null;
//                }else {
//                    tvjifen.setText("0.00");
//                    tvcard.setText("");
//                    tvname.setText("");
//                    tvyue.setText("0.00");
//                    mTvChooseMember.setText("选择会员");
//                }
//
//            }
//        });
//    }

    private void initnew() {
        basepoint = Double.parseDouble(vipdatalist.getMA_AvailableIntegral() + "");
        tvjifen.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableIntegral() + ""));
        tvcard.setText(vipdatalist.getVCH_Card());
        tvname.setText(vipdatalist.getVIP_Name());
        tvyue.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableBalance() + ""));
        mSurplusIntegral = basepoint - mNeedIntegral;
        Li("basepoint--" + basepoint + "---mNeedIntegral---" + mNeedIntegral + "---mSurplusIntegral--" + mSurplusIntegral);
    }

    private void initdata() {
        Intent intent = getIntent();
        if (intent.getBooleanExtra("is", false)) {
            strCard = intent.getStringExtra("card");
            name = intent.getStringExtra("name");
            jifen = intent.getStringExtra("jifen");
            vyue = intent.getStringExtra("yue");
            mTvChooseMember.setText("选择会员");
            if (name != null && name.length() >= 1) {
                mTvChooseMember.setText(name.substring(0, 1));
            }
            mVipInfo = (AllMemberListBean.DataBean.DataListBean) intent.getSerializableExtra("vip");
            MDZZ = intent.getStringExtra("MDZZ");
            basepoint = Double.parseDouble(jifen);
            tvjifen.setText(Decima2KeeplUtil.stringToDecimal(jifen));
            tvcard.setText(strCard);
            tvname.setText(name);
            tvyue.setText(Decima2KeeplUtil.stringToDecimal(vyue));
//            mSurplusIntegral = Double.parseDouble(jifen);
        }

        vippresenter = new PostVipPresenter(this);
        vipView = new IPostVipView() {
            @Override
            public void getvipsuccesss(MemberInfoBean bean) {
                isSearch = true;
                vipdatalist = bean.getData();
                initnew();
                String mMemberName = vipdatalist.getVIP_Name();
                if (mMemberName != null && !"".equals(mMemberName)) {
                    mTvChooseMember.setText(mMemberName);
                } else {
                    mTvChooseMember.setText(vipdatalist.getVCH_Card());
                }
            }

            @Override
            public void getvipfail(String result) {
                if (MyApplication.VIP_CARD != null) {
                    vippresenter.postVip(MyApplication.VIP_CARD, isSearch);
//                    postVip(MyApplication.VIP_CARD);
                    MyApplication.VIP_CARD = null;
                } else {
                    tvjifen.setText("0.00");
                    tvcard.setText("");
                    tvname.setText("");
                    tvyue.setText("0.00");
                    mTvChooseMember.setText("选择会员");
                }
            }
        };
        vippresenter.attachView(vipView);
    }


    /**
     * @param code ,参照SmsSwitch实体类的值
     *             根据短信发送开关是否打开，设置checkbox
     */
    private void setCbShortMessage(String code) {
        try {
            SmsSwitch.DataBean smsSwitch = YSLUtils.getSmsSwitch(code);
            if (smsSwitch != null) {
                if (smsSwitch.getST_State() == null || !smsSwitch.getST_State().equals("1")) {
                    cbMessage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CustomToast.makeText(IntegralExchangeActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
                            cbMessage.setChecked(false);
                        }
                    });
                } else {
                    cbMessage.setChecked(true);
                }
            } else {
                getSmsSet(code);

            }
        } catch (Exception e) {
            cbMessage.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 根据打印开关是否打开，设置checkbox
     */
    private void setCbPrint() {
        try {
            if (YSLUtils.getPrints() != null) {

                if (YSLUtils.getPrints().getPS_IsEnabled() != 1) {
                    cbPrint.setChecked(false);
                    pribean = 0;
                } else {
                    cbPrint.setChecked(true);
                    pribean = 1;
                }
            } else {
                getPrintSet();
            }

        } catch (Exception e) {
            cbPrint.setVisibility(View.INVISIBLE);
        }
    }


    /**
     * 获取短信开关
     */
    private void getSmsSet(final String code) {
        HttpHelper.post(IntegralExchangeActivity.this, HttpAPI.API().SMS_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SmsSwitch bean = CommonFun.JsonToObj(responseString, SmsSwitch.class);
                for (int i = 0; i < bean.getData().size(); i++) {
                    if (bean.getData().get(i).getST_Code().equals(code)) {
                        if (bean.getData().get(i).getST_State() == null || !bean.getData().get(i).getST_State().equals("1")) {
                            cbMessage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CustomToast.makeText(IntegralExchangeActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
                                    cbMessage.setChecked(false);
                                }
                            });
                        } else {
                            cbMessage.setChecked(true);
                        }
                    }
                }


                CacheData.saveObject("shortmessage", bean);//缓存短信开关到本地
                Li("获取短信开关成功");
            }

            @Override
            public void onFailure(String msg) {
                Li("获取短信开关" + msg);
            }
        });
    }


    /**
     * //     * 获取打印设置
     * //
     */
    private void getPrintSet() {  // http://dj.zhiluo.net/api/PrintSet/GetPrintSet
        HttpHelper.post(IntegralExchangeActivity.this, HttpAPI.API().PRINTSET, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                PrintSetBean bean = CommonFun.JsonToObj(responseString, PrintSetBean.class);

                if (bean.getData().getPS_IsEnabled() != 1) {
                    cbPrint.setChecked(false);
                    pribean = 0;
                } else {
                    cbPrint.setChecked(true);
                    pribean = 1;
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 设置监听
     */
    private void setListener() {
        //选择会员
        rChoiseVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntegralExchangeActivity.this, CheckMemberInfoActivity.class);
                startActivityForResult(intent, 888);
            }
        });

        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(IntegralExchangeActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(IntegralExchangeActivity.this, HomeActivity.class);
                startActivity(intent);

                finish();
            }
        });

        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tag = true;
                getGoods(1, 20);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    tag = true;
                    mIsLoadMore = true;
                    getGoods(mRefreshIndex, 20);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(IntegralExchangeActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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

        btnExchange.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                if (YSLUtils.isFastClick()) {
                    if (verification()) {
                        if (mPasswordSwitch) {
                            showPasswordDialog();
//                            Intent intent = new Intent(IntegralExchangeActivity.this, VerifyPasswordActivity.class);
//                            intent.putExtra("Card", tvcard.getText().toString());
//                            startActivityForResult(intent, 200);
                        } else {
                            exchange();
                        }
                    }
                }

            }
        });
        //获取当前滑动位置
        mGoodsListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                    mCurrentPosition = mGoodsListView.getFirstVisiblePosition();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
        //扫码
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(IntegralExchangeActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandFlag == -1) {
                    mExpandableListView.expandGroup(groupPosition);
                    mExpandableListView.setSelectedGroup(groupPosition);
                    expandFlag = groupPosition;
                } else if (expandFlag == groupPosition) {
                    mExpandableListView.collapseGroup(expandFlag);
                    expandFlag = -1;
                } else {
                    mExpandableListView.collapseGroup(expandFlag);
                    mExpandableListView.expandGroup(groupPosition);
                    mExpandableListView.setSelectedGroup(groupPosition);
                    expandFlag = groupPosition;
                }
                mParent.get(groupPosition).setChecked(true);
                for (int i = 0; i < mParent.size(); i++) {
                    if (groupPosition != i) {
                        mParent.get(i).setChecked(false);
                    }
                }
                if (mLastParentPos != groupPosition) {
                    for (int i = 0; i < mMap.get(mParent.get(mLastParentPos)).size(); i++) {
                        mMap.get(mParent.get(mLastParentPos)).get(i).setChecked(false);
                    }
                    mLastParentPos = groupPosition;
                }
                mTypeGid = mParent.get(groupPosition).getGID();
                getGoods(1, 20);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mMap.get(mParent.get(groupPosition)).get(childPosition).setChecked(true);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < mMap.get(mParent.get(groupPosition)).size(); i++) {
                    if (i != childPosition) {
                        mMap.get(mParent.get(groupPosition)).get(i).setChecked(false);
                    }
                }
                mTypeGid = mMap.get(mParent.get(groupPosition)).get(childPosition).getGID();
                getGoods(1, 20);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        //搜索框监听
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pretime = System.currentTimeMillis();
                if (!hasTimerDown) {
                    doQurry();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    mSearchCondition = "";
                } else {
                    mSearchCondition = s.toString();
                }
                // getGoods(1,20);
            }
        });

        cbPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pribean == 1) {
                    if (cbPrint.isChecked()) {
                        cbPrint.setChecked(true);
                    } else {
                        cbPrint.setChecked(false);
                    }
                } else {
                    CustomToast.makeText(IntegralExchangeActivity.this, "打印开关未开启，请设置", Toast.LENGTH_SHORT).show();
                    cbPrint.setChecked(false);
                }

            }
        });
    }

    /**
     * 密码输入框
     */
    private void showPasswordDialog() {
        LayoutInflater inflater = getLayoutInflater();
        final View dialog = inflater.inflate(R.layout.input_password_dialog_layout, (ViewGroup) findViewById(R.id.input_dialog));
        final EditText etPassword = (EditText) dialog.findViewById(R.id.et_input_dialog_password);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_input_cancel);
        Button btnConfirm = (Button) dialog.findViewById(R.id.btn_input_confirm);
        AlertDialog.Builder builder = new AlertDialog.Builder(IntegralExchangeActivity.this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setContentView(dialog);
        alertDialog.setView(dialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams();
                params.put("VCH_Card", tvcard.getText().toString());
                params.put("VCH_Pwd", etPassword.getText().toString());
                HttpHelper.post(IntegralExchangeActivity.this, HttpAPI.API().PASSWORDVERIFY, params, new CallBack() {
                    @Override
                    public void onSuccess(String responseString, Gson gson) {
                        PasswordVerifyBean bean = CommonFun.JsonToObj(responseString, PasswordVerifyBean.class);
                        if (bean.isSuccess()) {
                            exchange();
                            alertDialog.dismiss();
                        } else {
                            CustomToast.makeText(IntegralExchangeActivity.this, bean.getMsg(), Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {
                        CustomToast.makeText(IntegralExchangeActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                        alertDialog.dismiss();
                    }
                });
            }
        });
        alertDialog.show();
    }


    /**
     * 验证
     *
     * @return
     */
    private boolean verification() {
        if (TextUtils.isEmpty(tvcard.getText()) || "无".equals(tvcard.getText().toString())) {
            CustomToast.makeText(IntegralExchangeActivity.this, "请选择会员", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (mSurplusIntegral < 0) {
//            CustomToast.makeText(IntegralExchangeActivity.this, "积分不足", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(IntegralExchangeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
        return true;
    }

    private void initView() {
        mViewGroup = (ViewGroup) getWindow().getDecorView();
        mTvChooseMember = (TextView) findViewById(R.id.tv_choose_member);
        ivScan = (ImageView) findViewById(R.id.iv_scan_integralexchangeactivity);
        mRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.srl_freshgiftmng_activity);
        mGoodsListView = (BaseListView) findViewById(R.id.lv_giftdmgt_activity);
        btnExchange = findViewById(R.id.btn_exchange);
        tvname = (TextView) findViewById(R.id.tv_member_info_name);
        tvcard = (TextView) findViewById(R.id.tv_member_info_card);
        tvyue = (TextView) findViewById(R.id.tv_member_info_balance);
        tvjifen = (TextView) findViewById(R.id.tv_member_info_integral);
        rChoiseVip = findViewById(R.id.r_choise_vip);
        cbPrint = findViewById(R.id.cb_print);
        cbMessage = findViewById(R.id.cb_short_message);
        mExpandableListView = (ExpandableListView) findViewById(R.id.elv_integral_exchange);
        if (mMaccode != null && bluetoothUtil.isOpen() && !mMaccode.equals("")) {
            flag = bluetoothUtil.connect(mMaccode);
            if (flag == false) {
                // 连接失败
                new SweetAlertDialog(IntegralExchangeActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("提示")
                        .setContentText("连接失败！")
                        .setConfirmText("了解")
                        .show();
                //                CustomToast.makeText(IntegralExchangeActivity.this, "连接失败！", Toast.LENGTH_SHORT).show();
            } else {
                // 连接成功
                //  CustomToast.makeText(MemberRechargeActivity.this,"连接成功！",Toast.LENGTH_SHORT).show();

            }
        }
        tvTotal = (TextView) findViewById(R.id.tv_integral_exchange_total);
        etSearch = (EditText) findViewById(R.id.et_integral_exchange_search);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            etSearch.setText(scanResult);
        }
//        if (requestCode == 200 && resultCode == 200) {
//            if (data.getBooleanExtra("result", false)) {
//                exchange();
//            }
//        }
        if (requestCode == 888 && resultCode == 2222) {
            mMemberInfo = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");
            if (mMemberInfo.getVIP_Name() != null && !"".equals(mMemberInfo.getVIP_Name())) {
                mTvChooseMember.setText(mMemberInfo.getVIP_Name().substring(0, 1));
            }
            if (mMemberInfo != null) {
                if (mMemberInfo.getVIP_Name() != null) {
                    tvname.setText(mMemberInfo.getVIP_Name());
                } else {
                    tvname.setText(mMemberInfo.getVCH_Card());
                }
                tvcard.setText(mMemberInfo.getVCH_Card());
                tvyue.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableBalance() + ""));
                tvjifen.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableIntegral() + ""));
                isSearch = false;
                vippresenter.postVip(mMemberInfo.getVCH_Card(), isSearch);
//                postVip(mMemberInfo.getVCH_Card());
            } else {
                tvname.setText("无");
                tvcard.setText("无");
                tvyue.setText("0.00");
                tvjifen.setText("0.00");
                mTvChooseMember.setText("选择会员");
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
        }
        super.onDestroy();
    }

    /**
     * 设置增加商品动画
     *
     * @param view 触发动画控件
     */
    private void setAddGoodsAnimation(View view) {
        int[] goodsPoint = new int[2];
        view.getLocationInWindow(goodsPoint);
        int[] shoppingCartPoint = new int[2];
        //   tvChoiceNum.getLocationInWindow(shoppingCartPoint);
        GoodsView goodsView = new GoodsView(IntegralExchangeActivity.this);
        goodsView.setCircleStartPoint(goodsPoint[0], goodsPoint[1]);
        //   mShoppingCartWidth = tvChoiceNum.getMeasuredWidth();
        goodsView.setCircleEndPoint(shoppingCartPoint[0] + mShoppingCartWidth / 2, shoppingCartPoint[1]);
        //添加View并执行动画
        mViewGroup.addView(goodsView);
        goodsView.startAnimation();
    }

    @Override
    public void click(View view) {

        if (TextUtils.isEmpty(tvcard.getText()) || "无".equals(tvcard.getText().toString())) {
            CustomToast.makeText(IntegralExchangeActivity.this, "请选择会员", Toast.LENGTH_SHORT).show();
        } else {
            int position = (int) view.getTag();
            GoodsCheckResponseByType.DataBean.DataListBean bean = mGoodsList.get(position);
            if (!mHaveChoosedGoodList.isEmpty()) {
                for (int i = 0; i < mHaveChoosedGoodList.size(); i++) {
                    if (bean.getGID().equals(mHaveChoosedGoodList.get(i).getGID())) {
                        mHaveChoosedGoodList.remove(i);
                    }
                }
            }
            double num;
            num = bean.getNum();
//            if (num == 0) {
//                mSurplusIntegral = basepoint;
//            }
            switch (view.getId()) {
                //移除
                case R.id.iv_reduce:
                    if (num > 0) {
                        num--;
                    } else {
                        num = 0;
                    }
                    bean.setNum(num);
                    if (num > 0) {
                        mHaveChoosedGoodList.add(bean);
                    }
                    countGiftNumReduce();
                    break;
                //增加
                case R.id.iv_add:
                    if (verification()) {
                        if (bean.getPM_UnitPrice() > mSurplusIntegral) {
                            CustomToast.makeText(IntegralExchangeActivity.this, "积分不足", Toast.LENGTH_SHORT).show();
                        } else {
//                            if (bean.getStock_Number() > bean.getNum() && bean.getStock_Number() != 0) {

//                                String gid = bean.getGID();
//                                if (!mHaveChoosedGoodList.isEmpty()) {
//                                    for (int i = 0; i < mHaveChoosedGoodList.size(); i++) {
//                                        if (mHaveChoosedGoodList.get(i).getGID().equals(gid)) {
//                                            mHaveChoosedGoodList.remove(i);
//                                        }
//                                    }
//                                }
                            // setAddGoodsAnimation(view);
                            num++;
                            bean.setNum(num);
                            mHaveChoosedGoodList.add(bean);
                            countGiftNumAdd();

//                            } else {
//                                CustomToast.makeText(this, "库存不足", Toast.LENGTH_SHORT).show();
//                            }
                        }

                    }
                    break;
            }

        }
    }

    private void countGiftNumAdd() {
        mGiftNum = 0;
        mNeedIntegral = 0;
        if (mHaveChoosedGoodList.isEmpty()) {
            mGiftNum = 0;
            mNeedIntegral = 0;
        } else {
            for (int i = 0; i < mHaveChoosedGoodList.size(); i++) {
                mGiftNum += mHaveChoosedGoodList.get(i).getNum();
                if (mSurplusIntegral < mHaveChoosedGoodList.get(i).getPM_UnitPrice()) {
//                    CustomToast.makeText(this, "积分不足", Toast.LENGTH_SHORT).show();
                    mNeedIntegral += DoubleMathUtil.mul(mHaveChoosedGoodList.get(i).getNum(), mHaveChoosedGoodList.get(i).getPM_UnitPrice());
                } else {
                    mNeedIntegral += DoubleMathUtil.mul(mHaveChoosedGoodList.get(i).getNum(), mHaveChoosedGoodList.get(i).getPM_UnitPrice());
                }
            }
        }
        mSurplusIntegral = basepoint - mNeedIntegral;
        tvTotal.setText(Decima2KeeplUtil.stringToDecimal(mNeedIntegral + ""));
        if (mRightAdapter != null) {
            mRightAdapter.notifyDataSetChanged();
        }
    }

    private void countGiftNumReduce() {
        mGiftNum = 0;
        mNeedIntegral = 0;
        if (mHaveChoosedGoodList.isEmpty()) {
            mGiftNum = 0;
            mNeedIntegral = 0;
        } else {
            for (int i = 0; i < mHaveChoosedGoodList.size(); i++) {
                mGiftNum += mHaveChoosedGoodList.get(i).getNum();
                // mNeedIntegral += DoubleMathUtil.mul(mHaveChoosedGoodList.get(i).getNum(), mHaveChoosedGoodList.get(i).getPM_UnitPrice());
                mNeedIntegral += mHaveChoosedGoodList.get(i).getNum() * mHaveChoosedGoodList.get(i).getPM_UnitPrice();
                Li("长度---" + mHaveChoosedGoodList.size() + "----数量----" + mHaveChoosedGoodList.get(i).getNum() + "----单价----" + mHaveChoosedGoodList.get(i).getPM_UnitPrice() + "---积分---" + mNeedIntegral);
            }
        }
        mSurplusIntegral = basepoint - mNeedIntegral;
        tvTotal.setText(Decima2KeeplUtil.stringToDecimal(mNeedIntegral + ""));
        if (mRightAdapter != null) {
            mRightAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 积分兑换
     */
    private void exchange() {
        RequestParams params = new RequestParams();
        params.put("VIP_Card", tvcard.getText().toString());
        params.put("OrderNo", CreateOrder.createOrder("LP"));
        params.put("Number", mGiftNum);
        params.put("Score", mNeedIntegral);
        params.put("RemainingPoints", big(mSurplusIntegral));
        for (int i = 0; i < mHaveChoosedGoodList.size(); i++) {
            params.put("GiftsDetail[" + i + "][GT_Name]", mHaveChoosedGoodList.get(i).getPT_ID());
            params.put("GiftsDetail[" + i + "][GM_Name]", mHaveChoosedGoodList.get(i).getPM_Name());
            params.put("GiftsDetail[" + i + "][GM_Code]", mHaveChoosedGoodList.get(i).getPM_Code());
            params.put("GiftsDetail[" + i + "][GM_Acount]", (int) mHaveChoosedGoodList.get(i).getNum());
            params.put("GiftsDetail[" + i + "][GM_Integral]", mHaveChoosedGoodList.get(i).getPM_UnitPrice());
            params.put("GiftsDetail[" + i + "][EGD_Score]", DoubleMathUtil.mul(mHaveChoosedGoodList.get(i).getNum(),
                    mHaveChoosedGoodList.get(i).getPM_UnitPrice()));
            params.put("GiftsDetail[" + i + "][GM_GID]", mHaveChoosedGoodList.get(i).getGID());
        }
        params.put("IS_Sms", cbMessage.isChecked());
        params.put("DeviceType", 1);//设备
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                try {
                    final JFDH_Success_Bean bean = CommonFun.JsonToObj(responseString, JFDH_Success_Bean.class);
                    mSweetAlertDialog = new SweetAlertDialog(IntegralExchangeActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    mSweetAlertDialog.setTitleText("兑换成功");
                    mSweetAlertDialog.setConfirmText("确定");
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            //打印小票
                            if (cbPrint.isChecked()) {
                                new HttpGetPrintContents(IntegralExchangeActivity.this, MyApplication.JFDH_PRINT_TIMES, bean.getData().getGID()).JFDH();
                            }
                            mHaveChoosedGoodList.clear();
                            mGiftNum = 0;
                            mNeedIntegral = 0.00;
                            getGoods(1, 20);
                            tvname.setText("无");
                            tvcard.setText("无");
                            tvyue.setText("0.00");
                            tvjifen.setText("0.00");
                            mTvChooseMember.setText("选择会员");
                            mSweetAlertDialog.dismiss();
                        }
                    });
                    mSweetAlertDialog.show();
                } catch (JsonSyntaxException e) {
                    CustomToast.makeText(IntegralExchangeActivity.this, "打印失败！", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(IntegralExchangeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "兑换中...", false);
        HttpHelper.post(this, HttpAPI.API().EXCHANGEGIFT, params, callBack);
    }


    private static String big(double d) {
        NumberFormat nf = NumberFormat.getInstance();
        // 是否以逗号隔开, 默认true以逗号隔开,如[123,456,789.128]
        nf.setGroupingUsed(false);
        // 结果未做任何处理
        return nf.format(d);
    }


    private class InputHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0://0正在输入
                    break;
                case 1://1输入完成
                    doQurry();
                    break;
            }
        }

    }

    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {
            getGoods(1, 20);
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }
}
