package com.zhiluo.android.yunpu.goods.consume.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.activity.PayConfirmActivity;
import com.zhiluo.android.yunpu.goods.consume.NotifyShopCarEvent;
import com.zhiluo.android.yunpu.goods.consume.adapter.GoodsConsumeNewAdapter;
import com.zhiluo.android.yunpu.goods.consume.adapter.ShopeCarAdapter;
import com.zhiluo.android.yunpu.goods.consume.bean.GoodsModelBean;
import com.zhiluo.android.yunpu.goods.consume.bean.IntegralScalingBean;
import com.zhiluo.android.yunpu.goods.consume.bean.ResultManyModelBean;
import com.zhiluo.android.yunpu.goods.consume.bean.ResultShopBean;
import com.zhiluo.android.yunpu.goods.consume.bean.ShopEntity;
import com.zhiluo.android.yunpu.goods.consume.view.GoodsView;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsTypeExpandAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsDataBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;
import com.zhiluo.android.yunpu.mvp.presenter.WareHousingPresenter;
import com.zhiluo.android.yunpu.mvp.view.IWareHousingView;
import com.zhiluo.android.yunpu.popup.ManyShopPopup;
import com.zhiluo.android.yunpu.popup.ShoppingCartPopup;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 商品消费
 * 作者：罗咏哲 on 2017/8/1 10:00.
 * 邮箱：137615198@qq.com
 */
public class GoodsConsumeActivity extends BaseActivity implements GoodsConsumeNewAdapter.ItemViewClick, PopupWindow.OnDismissListener {
    private TextView mTvBack, mTvChooseMember, mTvTotalMoney, mTvHaveChossed, mTvtitle;//返回，选择会员，总计金额,已选数量
    private EditText mEtSearchCondition;//搜索框
    private int Serchtype = 0;//搜索区分0正常输入搜索 1扫码后搜索
    private ImageView mIvScann, mIvChooseMember;//扫描，选择会员
    private Button mBtnSubmit;//结算
    private BaseListView mGoodsListView;//商品
    private WaveSwipeRefreshLayout mRefreshLayout;//刷新布局
//    private AllMemberListBean.DataBean.DataListBean mMemberInfo;//会员信息
//    private VipIncludeGradeDisBean mVip;

    private String mTypeGid;//商品类型GID
    private String mSearchCondition = "";//搜索条件
    private String mCardNo = "00000";//会员卡号
    private String mMemberName = "散客";//会员姓名
    private String mOrderNum;//订单号
    private int mOrderType = 2;//订单类型  默认：0   散客、空卡号：2  会员：1
    private AsyncHttpClient mClient;

    private GoodsConsumeNewAdapter mRightAdapter;//右边ListView的Adapter
    private int mGoodsNum;//已选商品数量
    /**
     * 之前购物数量，主要是判断超过99，改边距 20191018 cy
     */
    private int mBeforeNum;
    /**************************************** 20191018 cy*/
    private double mTotalMoney;//已选商品总价
    private double mPoint;//获得积分
    private double mOrderMoney;//订单金额
    private Map<Integer, Boolean> mLeftMap;
    private int mCurrentClickPosition = 0;//当前点击的Item位置
    private int mShoppingCartWidth;//购物车宽度
    private ViewGroup mViewGroup;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodsList;//商品信息（根据会员卡号得到）
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mCCGoodsList = new ArrayList<>();//会员充次套餐信息
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mCCTcGoodsList = new ArrayList<>();
    public static List<GoodsCheckResponseByType.DataBean.DataListBean> mHaveChoosedGoodList;//已选商品信息
    //会员管理->商品消费->支付确认
    private String MDZZ;
    private boolean tag = true;
    private AllMemberListBean.DataBean.DataListBean mVipInfo;//会员信息
    private int mRefreshIndex = 2;
    private boolean mIsLoadMore;//加载更多
    private int mPageTotal;//数据总页数

    private boolean isCache;//是否缓存


    private ExpandableListView mExpandableListView;
    private WareHousingPresenter mPresenter;
    private IWareHousingView mView;
    private List<GoodsType.DataBean> mParent;
    private Map<GoodsType.DataBean, List<GoodsType.DataBean>> mMap;
    private int mLastParentPos;
    int expandFlag = -1;//控制列表的展开
    private GoodsTypeExpandAdapter adapter;
    private List<IntegralScalingBean> mIntegralScalingList = new ArrayList<>();//单个商品获得积分占总积分比例集合
    private IntegralScalingBean mIntegralScalingBean = new IntegralScalingBean();
    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间

    private PopupWindow mPopupWindow;//购物车详情弹窗
    private ShopeCarAdapter mShopeCarAdapter;//购物车商品adapter
    private ImageView ivShopCar;

    private double zuidiPercent;//最低折扣
    private double dengjiPercent;//等级折扣
    private double tejiaPercent;//特价折扣
    private double percent;//计算后的百分比
    private String type;//判断是会员充次还是商品消费

    /**
     * 从会员管理进入商品消费,用户信息 20191019 cy
     */
    private AllMemberListBean.DataBean.DataListBean mYSLMemberInfo;
    /************************************************************* 20191019 cy */
    //存临时信息
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodsFirstList = new ArrayList<>();
    //多规格列表
    private List<GoodsModelBean> mModelList;
    //组装规格数据
    private List<List<GoodsModelBean>> mManyModelList = new ArrayList<>();
    //选中商品的多规格列表
    private List<ShopEntity> mSelectGoodsData;
    //点击多规格,存个临时值
    private GoodsCheckResponseByType.DataBean.DataListBean mManyFlagData;
    //0库库销售
    private boolean mIsZeroSale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_consume);
        EventBus.getDefault().register(this);
        /**清空购物车数据库 */
        DataSupport.deleteAll(GoodsDataBean.class);
        getSpecifications();
        /***************/
        initView();
        initVariable();
        loadData();
        setListener();

        List<ReportMessageBean.DataBean.GetSysSwitchListBean> flagList = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        for (ReportMessageBean.DataBean.GetSysSwitchListBean item : flagList) {
            if (item.getSS_Code().equals("602")) {
                mIsZeroSale = (item.getSS_State() == 1) ? true : false;
                break;
            }
        }
    }

    /**
     * 初始控件
     */
    private void initView() {

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//进入页面关闭软键盘
        mTvtitle = findViewById(R.id.tv_head_title);
        mTvBack = (TextView) findViewById(R.id.tv_goods_comsume_back);
        mTvChooseMember = (TextView) findViewById(R.id.tv_choose_member);
        mTvTotalMoney = (TextView) findViewById(R.id.tv_goods_consume_total_money);
        mTvHaveChossed = (TextView) findViewById(R.id.tv_goods_consume_num);
        mEtSearchCondition = (EditText) findViewById(R.id.et_goods_consume_search);
        mIvScann = (ImageView) findViewById(R.id.iv_goods_consume_scan);
        mIvChooseMember = (ImageView) findViewById(R.id.iv_choose_member);
        mBtnSubmit = (Button) findViewById(R.id.btn_goods_consume_submit);
        mExpandableListView = (ExpandableListView) findViewById(R.id.lv_goods_consume_type);
        mGoodsListView = (BaseListView) findViewById(R.id.lv_goods_consume_goods);
        mRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.refresh_goods_consume);
        ivShopCar = findViewById(R.id.iv_shop_car);
        mViewGroup = (ViewGroup) getWindow().getDecorView();
    }

    /**
     * todo
     */
    private void initSpacialData() {
        mView = new IWareHousingView() {
            @Override
            public void getTypeSuccess(List<GoodsType.DataBean> parent, Map<GoodsType.DataBean, List<GoodsType.DataBean>> map) {

                mMap = map;
                mParent = parent;
                adapter = new GoodsTypeExpandAdapter(GoodsConsumeActivity.this,
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

            }

            @Override
            public void getGoodsFail(String result) {

            }

            @Override
            public void getComboSuccess(GoodsCheckResponseByType entity) {
                mPageTotal = entity.getData().getPageTotal();
                if (mGoodsList != null && mIsLoadMore) {
                    mGoodsList.addAll(entity.getData().getDataList());
                } else {
                    mGoodsList = entity.getData().getDataList();
                }
                setRightAdapter();
//                mRightAdapter = new GoodsConsumeRightAdapter(GoodsConsumeActivity.this,
//                        mGoodsList, GoodsConsumeActivity.this);
//                mGoodsListView.setAdapter(mRightAdapter);
//                mTvHaveChossed.setText(mGoodsNum + "");
//                mTvTotalMoney.setText(Decima2KeeplUtil.stringToDecimal(mTotalMoney + ""));
                mIsLoadMore = false;
                mRefreshLayout.setLoading(false);
                mRefreshLayout.setRefreshing(false);
                Serchtype = 0;
            }

            @Override
            public void getCombofail(String result) {

            }
        };
        mPresenter.attachView(mView);
    }

    /**
     * 设置右边ListViewAdapter
     */
    private void setRightAdapter() {
        if (mRightAdapter == null) {
            mRightAdapter = new GoodsConsumeNewAdapter(GoodsConsumeActivity.this,
                    mGoodsList, GoodsConsumeActivity.this);
            mGoodsListView.setAdapter(mRightAdapter);
        } else {
            mRightAdapter.setParm(mGoodsList);
            mRightAdapter.notifyDataSetChanged();
        }
    }

//    private void showPop() {
//        mPopupWindow = new PopupWindow(this);
//        View anchor = LayoutInflater.from(this).inflate(R.layout.activity_goods_consume, null);
//        View view = LayoutInflater.from(this).inflate(R.layout.layout_shop_car_pop, null);
//        TextView tvCancel = view.findViewById(R.id.tv_cancel);
//        TextView tvClear = view.findViewById(R.id.tv_clear);
//        final TextView tvCount = view.findViewById(R.id.tv_count);
//        tvCount.setText(mHaveChoosedGoodList.size() + "种商品" + ",共" + (int) getReallyCount() + "件");
//        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
//        mShopeCarAdapter = new ShopeCarAdapter(this, mHaveChoosedGoodList);
//        LinearLayoutManager manager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(manager);
//        mPopupWindow.setOnDismissListener(this);
//        recyclerView.setAdapter(mShopeCarAdapter);
//        mPopupWindow.setContentView(view);
//        mPopupWindow.setAnimationStyle(R.style.pop_show_style);
//        mPopupWindow.setTouchable(true);
//        mPopupWindow.setFocusable(true);
//        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
//        mPopupWindow.setWidth(YSLUtils.getPhoneWidth());
//        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
//        mPopupWindow.showAtLocation(anchor, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//        tvClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvCount.setText("购物车空空如也");
//                mHaveChoosedGoodList.clear();
//                getMealData(1, 20);
//                mTvTotalMoney.setText("");
//                mGoodsNum = 0;
//                //清空之前购物数量
//                mBeforeNum = 0;
//                mTotalMoney = 0.0;
//                mPoint = 0.0;
//                //  mTvHaveChossed.setText("0");
//                mOrderMoney = 0.0;
//                mGoodsList = null;
//                //initSpacialData();
//                mShopeCarAdapter.notifyDataSetChanged();
//            }
//        });
//        tvCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPopupWindow.dismiss();
//                YSLUtils.setBackgroundAlpha(1.0f, GoodsConsumeActivity.this);
//            }
//        });
//    }

//    /**
//     * @return ，获取商品的具体件数
//     */
//    private double getReallyCount() {
//        double k = 0;
//        for (int i = 0; i < mHaveChoosedGoodList.size(); i++) {
//            k += mHaveChoosedGoodList.get(i).getNum();
//        }
//        return k;
//    }

    /**
     * 初始变量
     */
    private void initVariable() {
        mHaveChoosedGoodList = new ArrayList<>();
        Intent intent = getIntent();

        if (intent != null) {
            type = intent.getStringExtra("type");
            MDZZ = intent.getStringExtra("MDZZ");
            if (MDZZ != null) {
                mCardNo = intent.getStringExtra("card");
                mMemberName = intent.getStringExtra("name");
                /**从会员管理进入商品消费,用户信息 20191019 cy */
                mYSLMemberInfo = (AllMemberListBean.DataBean.DataListBean) intent.getSerializableExtra("vip");
                /************************************************************* 20191019 cy */
            }
            if (type != null && type.equals("HYCC")) {
                mTvtitle.setText("会员充次");
            }
        }
        mClient = new AsyncHttpClient();

        mPresenter = new WareHousingPresenter(this);
        mPresenter.onCreate(type);
        initSpacialData();

    }

    /**
     * 加载数据
     */
    private void loadData() {
        getMealData(1, 20);
        getOrderCode();//获取订单号
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (MDZZ != null && "mdzz".equals(MDZZ)) {
//                Intent intent = new Intent(GoodsConsumeActivity.this, MemberDetailsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("VIP_INFO", mVipInfo);
//                intent.putExtra("bundle", bundle);
//                startActivity(intent);
            } else {
                Intent intent = new Intent(GoodsConsumeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
            finish();
        }
        return true;
    }

    /**
     * 设置监听
     */
    private void setListener() {
        //购物图标
        ivShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                YSLUtils.setBackgroundAlpha(0.5f, GoodsConsumeActivity.this);
//                showPop();
                if (mHaveChoosedGoodList.size() == 0) {
                    return;
                }
                new ShoppingCartPopup(GoodsConsumeActivity.this, ivShopCar, new ShoppingCartPopup.OnShopRefreshClickEvent() {
                    @Override
                    public void OnShopRefreshEvent() {
                        countGoodsNum();
                        mRightAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
        //返回首页,当会员详情跳转过来时返回会员详情
        mTvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MDZZ != null && "mdzz".equals(MDZZ)) {
//                    Intent intent = new Intent(GoodsConsumeActivity.this, MemberDetailsActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("VIP_INFO", mVipInfo);
//                    intent.putExtra("bundle", bundle);
//                    startActivity(intent);
                } else {
                    Intent intent = new Intent(GoodsConsumeActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });
        //选择会员
        mIvChooseMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (YSLUtils.isFastClick()) {
                    tag = true;
                    Intent intent = new Intent(GoodsConsumeActivity.this, CheckMemberInfoActivity.class);
                    startActivityForResult(intent, 888);
                }
            }
        });
        mTvChooseMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (YSLUtils.isFastClick()) {
                    tag = true;
                    Intent intent = new Intent(GoodsConsumeActivity.this, CheckMemberInfoActivity.class);
                    startActivityForResult(intent, 888);
                }
            }
        });
        //设置搜索框监听
        mEtSearchCondition.addTextChangedListener(new TextWatcher() {
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
                if (editable != null) {
                    mSearchCondition = editable.toString();
                }
                tag = true;
                //getGoods(1, 20);
            }
        });
        //结算
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (YSLUtils.isFastClick()) {
                    jump2PayConfirm();
                }
            }
        });
        //刷新或加载更多
        mRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tag = true;
                getMealData(1, 20);
                mRefreshIndex = 2;
            }

            @Override
            public void onLoad() {
                if (mRefreshIndex <= mPageTotal) {
                    tag = true;
                    mIsLoadMore = true;
                    getMealData(mRefreshIndex, 20);
                    mRefreshIndex++;
                } else {
                    CustomToast.makeText(GoodsConsumeActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
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

        //扫码
        mIvScann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开扫描界面扫描条形码或二维码
                if (type.equals("SPXF")) {
                    Intent openCameraIntent = new Intent(GoodsConsumeActivity.this, CaptureActivity.class);
                    openCameraIntent.putExtra("isSPXF", true);
                    startActivity(openCameraIntent);
                } else {
                    Intent openCameraIntent = new Intent(GoodsConsumeActivity.this, CaptureActivity.class);
                    startActivityForResult(openCameraIntent, 0);
                }


            }
        });
        //左边点击显示右边数据
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
                getMealData(1, 20);
                mRefreshIndex = 2;
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
                getMealData(1, 20);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        //设置ListView item点击事件
//        mGoodsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String groupGID = mGoodsList.get(i).getPM_GroupGID();
//
//                Intent intent = new Intent(GoodsConsumeActivity.this, GoodsSpecsActivity.class);
//                intent.putExtra("groupGID", groupGID);
//                if (mGoodsList.get(i).getPT_ID().equals("combo")) {
//                    intent.putExtra("tancan", "taocan");
//                    GoodsSpecsListEvent event = new GoodsSpecsListEvent();
//                    event.setMsg("taocan_list");
//                    event.setmGoodsList(mGoodsList.get(i));
//                    EventBus.getDefault().postSticky(event);
//                }
//                startActivity(intent);
//                int manyGoodsCount = mGoodsList.get(i).getGroupCount();
//                if (manyGoodsCount <= 1) {
//                    return;
//                }
//                manyGoodsData(mGoodsList.get(i));
//            }
//        });
    }

    /**
     * 跳转到收银台
     */
    private void jump2PayConfirm() {
        Intent intent = new Intent(GoodsConsumeActivity.this, PayConfirmActivity.class);
        ArrayList list = new ArrayList();
        if (mHaveChoosedGoodList.size() == 0) {
            CustomToast.makeText(GoodsConsumeActivity.this, "未选择任何商品，请选择商品", Toast.LENGTH_SHORT).show();
            return;
        }
        list.add(mHaveChoosedGoodList);
        Bundle bundle = new Bundle();
        intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) list);
        bundle.putParcelableArrayList("list", list);
        if (type != null && type.equals("HYCC")) {
            intent.putExtra("isHYCC", true);
            intent.putExtra(MyApplication.PAGE_FLAG, "HYCC");//
        } else {
            intent.putExtra("isSPXF", true);
            intent.putExtra(MyApplication.PAGE_FLAG, "SPXF");//消费标识
        }
        intent.putExtra("scaling", (Serializable) mIntegralScalingList);
        intent.putExtra("bundle", bundle);
        intent.putExtra("card", mCardNo);
        intent.putExtra("order_num", mOrderNum);
        intent.putExtra("order_type", mOrderType + "");
        intent.putExtra("order_money", Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));
        intent.putExtra("point", Decima2KeeplUtil.stringToDecimal(String.valueOf(mPoint)));
        intent.putExtra("memberName", mMemberName);
        intent.putExtra("discount_money", Decima2KeeplUtil.stringToDecimal(mTvTotalMoney.getText().toString()));
        if (MDZZ != null && MDZZ.equals("mdzz")) {
            intent.putExtra("MDZZ", MDZZ);
            /**从会员管理进入商品消费,用户信息 20191019 cy */
            intent.putExtra("MDZZ_YSL_INFO", mYSLMemberInfo);
            /************************************************************* 20191019 cy */
        }
        startActivity(intent);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            mEtSearchCondition.setText(scanResult);
            Serchtype = 1;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mClient != null) {
            mClient.cancelAllRequests(true);
        }
        EventBus.getDefault().unregister(this);
        mHaveChoosedGoodList = null;
    }


    @Override
    public void click(View view) {
        int position = (int) view.getTag();
        GoodsCheckResponseByType.DataBean.DataListBean bean = mGoodsList.get(position);
        String gid = bean.getGID();

        if (mIsZeroSale && bean.getPM_IsService() == 0) {
            if (bean.getStock_Number() == 0) {
                CustomToast.makeText(GoodsConsumeActivity.this, "当前库存不足", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        /**存购物车列表*/
        List<GoodsDataBean> findList = DataSupport.where("gid==?", bean.getGID()).find(GoodsDataBean.class);
        boolean findBool = false;
        int mHavaPosition = -1;
        if (findList != null && findList.size() > 0) {
            findBool = true;
            for (int i = 0, len = mHaveChoosedGoodList.size(); i < len; i++) {
                if (mHaveChoosedGoodList.get(i).getGID().equals(bean.getGID())) {
                    mHavaPosition = i;
                }
            }
        }
        /*****************/
        if (view.getId() == R.id.iv_reduce) {
            //移除
            if (findBool) {
                GoodsDataBean flag = findList.get(0);
                if (flag.getNum() > 1) {
                    flag.setNum(flag.getNum() - 1);
                    flag.save();
                    //当前页面存商品信息数量减1
                    bean.setNum(flag.getNum());
                    mHaveChoosedGoodList.get(mHavaPosition).setNum(flag.getNum());
                } else if (flag.getNum() == 1) {
                    //数据从1减到0就删除这一条
                    DataSupport.deleteAll(GoodsDataBean.class, "gid==?", bean.getGID());
                    //当前页面存商品信息删除
                    bean.setNum(0);
                    mHaveChoosedGoodList.remove(mHavaPosition);
                }
            }
        } else if (view.getId() == R.id.iv_add) {
            //增加
            setAddGoodsAnimation(view);
            if (findBool) {
                //购物车里有就加数字
                GoodsDataBean flag = findList.get(0);
                flag.setNum(flag.getNum() + 1);
                flag.save();
                //当前页面存商品信息数量加1
                bean.setNum(flag.getNum());
                mHaveChoosedGoodList.get(mHavaPosition).setNum(flag.getNum());
            } else {
                //购物车里没有就加一条
                GoodsDataBean beanLitePal = new GoodsDataBean();
                beanLitePal.setNum(1);
                beanLitePal.setGid(bean.getGID());
                beanLitePal.save();
                //当前页面存商品信息
                bean.setNum(1);
                mHaveChoosedGoodList.add(bean);
            }
        }
        if (mRightAdapter != null) {
            mRightAdapter.notifyDataSetChanged();
        }
        countGoodsNum();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMemberValueRefresh(NotifyShopCarEvent notifyShopCarEvent) {
        if (notifyShopCarEvent.getMsg().equals("goods_consume_shop_car_refresh")) {
            countGoodsNum();
        }
    }

    /**
     * 计算已选商品数量和总价
     */
    private void countGoodsNum() {
        mGoodsNum = 0;//已选商品数量
        mTotalMoney = 0;
        mOrderMoney = 0;
        mPoint = 0;
        mIntegralScalingList.clear();

        for (int i = 0; i < mHaveChoosedGoodList.size(); i++) {
            GoodsCheckResponseByType.DataBean.DataListBean bean = mHaveChoosedGoodList.get(i);
            Li("购物车列表=============random:" + new Gson().toJson(bean));
            zuidiPercent = bean.getPM_MinDisCountValue();
            tejiaPercent = bean.getPM_SpecialOfferValue();
            //计算折后金额
            if (bean.getNum() > 0) {
                mGoodsNum += bean.getNum();
            }
            double a = DoubleMathUtil.mul(bean.getPM_UnitPrice(), bean.getNum());


            if (bean.getPM_IsDiscount() == 1) {
                if (bean.getPM_SpecialOfferMoney() > -1) {
                    mOrderMoney += DoubleMathUtil.mul(bean.getPM_SpecialOfferMoney(), bean.getNum());
                    bean.setEachMoney(bean.getPM_SpecialOfferMoney());
                    bean.setZHMoney(bean.getPM_SpecialOfferMoney() * bean.getNum());
                } else if (tejiaPercent < 1 && tejiaPercent > 0) {
                    percent = compareTandD(zuidiPercent, tejiaPercent);
                    if (percent == 0) {
                        mOrderMoney += DoubleMathUtil.mul(a, 1);
                        bean.setEachMoney(bean.getPM_UnitPrice());
                        bean.setZHMoney(bean.getPM_UnitPrice() * bean.getNum());
                    } else {
                        mOrderMoney += DoubleMathUtil.mul(a, percent);
                        bean.setEachMoney(bean.getPM_UnitPrice() * percent);
                        bean.setZHMoney(bean.getPM_UnitPrice() * percent * bean.getNum());
                    }
                } else {
                    mOrderMoney += DoubleMathUtil.mul(a, 1);
                    bean.setEachMoney(bean.getPM_UnitPrice());
                    bean.setZHMoney(bean.getPM_UnitPrice() * bean.getNum());
                }


            } else {
                mOrderMoney += DoubleMathUtil.mul(a, 1);
                bean.setEachMoney(bean.getPM_UnitPrice());
                bean.setZHMoney(bean.getPM_UnitPrice() * bean.getNum());
            }

            mTotalMoney = mOrderMoney;
            mPoint = 0;
        }

        /**购物数量超过99就靠最右边，不超过99就设备4dp*/
        if (mGoodsNum > 99) {
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mTvHaveChossed.getLayoutParams();
            lp.setMarginEnd(0);
            mTvHaveChossed.setLayoutParams(lp);
            mBeforeNum = mGoodsNum;
        } else if (mGoodsNum < 100 && mBeforeNum > 99) {
            //之前购物数量超过99，然后点击减到99就改变位置
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mTvHaveChossed.getLayoutParams();
            lp.setMarginEnd(12);
            mTvHaveChossed.setLayoutParams(lp);
            mBeforeNum = 0;
        }
        /*********************************************/
        mTvHaveChossed.setText(mGoodsNum + "");
        mTvTotalMoney.setText(Decima2KeeplUtil.stringToDecimal(mTotalMoney + ""));
    }


    /**
     * @param tejia ,特价折扣
     * @param zuidi ，最低折扣
     * @return ，
     */
    private double compareTandD(double zuidi, double tejia) {
        double percent = 0;
        if (zuidi == 1) {
            zuidi = 0;
        }
        if (tejia == 1) {
            tejia = 0;
        }
        if (zuidi == 0) {
            percent = tejia;
        } else if (tejia == 0) {
            percent = zuidi;
        } else {
            if (tejia >= zuidi) {
                percent = tejia;
            } else {
                percent = zuidi;
            }
        }
        return percent;
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
        mTvHaveChossed.getLocationInWindow(shoppingCartPoint);
        GoodsView goodsView = new GoodsView(GoodsConsumeActivity.this);
        goodsView.setCircleStartPoint(goodsPoint[0], goodsPoint[1]);
        mShoppingCartWidth = mTvHaveChossed.getMeasuredWidth();
        goodsView.setCircleEndPoint(shoppingCartPoint[0] + mShoppingCartWidth / 2, shoppingCartPoint[1]);
        //添加View并执行动画
        mViewGroup.addView(goodsView);
        goodsView.startAnimation();
    }

    /**
     * 获取订单号
     */
    private void getOrderCode() {
        if (type != null && type.equals("HYCC")) {
            mOrderNum = CreateOrder.createOrder("CC");
        } else {
            mOrderNum = CreateOrder.createOrder("SP");
        }

    }

    @Override
    public void onDismiss() {
        YSLUtils.setBackgroundAlpha(1.0f, this);
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
            getMealData(1, 20, false);
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }

    /**
     * 点击套餐获取列表
     */

    private void getMealData(int index, int size, boolean showLoading) {
        RequestParams params = new RequestParams();
        if (type != null && type.equals("HYCC")) {
            params.put("DataType", 10);
        } else {
            params.put("DataType", 2);
        }
        params.put("pageIndex", index);
        params.put("pageSize", size);
        params.put("showGroupPro", "0");
        params.put("PT_GID", mTypeGid);
        params.put("PM_CodeOrNameOrSimpleCode", mSearchCondition);
        mPresenter.getcombo(params, showLoading);
    }

    private void getMealData(int index, int size) {
        getMealData(index, size, true);
    }

    /**
     * 获取多规格列表(颜色,尺码)
     */
    private void getSpecifications() {
        HttpHelper.post(this, HttpAPI.API().GOODSMODEL, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mModelList = CommonFun.JsonToObj(responseString, ResultManyModelBean.class).getData();
//                for (GoodsModelBean data : mModelList) {
//                    Li("=多规格列表=======random:" + new Gson().toJson(data));
//                }
            }

            @Override
            public void onFailure(String msg) {
                Li("onFailure===random:" + msg);
            }
        });
    }

    @Override
    public void manyNormsClick(int position) {
        int manyGoodsCount = mGoodsList.get(position).getGroupCount();
        if (manyGoodsCount <= 1) {
            return;
        }
        manyGoodsData(mGoodsList.get(position));
    }

    /**
     * 获取商品多规格列表信息
     */
    private void manyGoodsData(GoodsCheckResponseByType.DataBean.DataListBean bean) {
        Li("获取商品多规格列表信息=======manyGoodsData======random:");
        mManyFlagData = bean;
        RequestParams params = new RequestParams();
        params.put("GroupGID", bean.getPM_GroupGID());
        HttpHelper.post(this, HttpAPI.API().GROUPGOODSLIST, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ResultShopBean entity = CommonFun.JsonToObj(responseString, ResultShopBean.class);
//                for (ShopEntity data : entity.getData()) {
//                    Li("获取商品多规格列表信息==i=random:" + new Gson().toJson(data));
//                }
                if (entity != null && entity.getData() != null && entity.getData().size() > 0) {
                    composeManyGoods(entity);
                }
            }

            @Override
            public void onFailure(String msg) {
            }
        });
    }

    /**
     * 拼接商品多规格
     */
    private void composeManyGoods(ResultShopBean listBean) {
        mSelectGoodsData = listBean.getData();
        for (int i = 0; i < mModelList.size(); i++) {
            mModelList.get(i).setChecked(false);
            mModelList.get(i).setEnable(false);
        }
        //将商品有的规格设置成可点击
        for (int i = 0, len = listBean.getData().size(); i < len; i++) {
            ShopEntity data = listBean.getData().get(i);
            if (data.getPM_Modle() != null && !data.getPM_Modle().equals("")) {
                List<String> list = DateUtil.getStringForlist(data.getPM_Modle());
                for (int j = 0; j < list.size(); j++) {
                    for (int k = 0; k < mModelList.size(); k++) {
                        GoodsModelBean modeBean = mModelList.get(k);
                        if (list.get(j).equals(modeBean.getPM_Properties())) {
                            modeBean.setEnable(true);
                        }
                    }
                }
            }
        }
        mManyModelList.clear();
        //组装规格数据
        if (mModelList != null && mModelList.size() > 1) {
            for (int i = 0; i < mModelList.size(); i++) {
                if (mModelList.get(i).getPM_Type() == 0) {
                    List<GoodsModelBean> list = new ArrayList<>();
                    list.add(mModelList.get(i));
                    mManyModelList.add(list);
                } else {
                    for (int j = 0; j < mManyModelList.size(); j++) {
                        if (mManyModelList.get(j).get(0).getPM_Name().equals(mModelList.get(i).getPM_Name())) {
                            mManyModelList.get(j).add(mModelList.get(i));
                        }
                    }
                }
            }
        }

        new ManyShopPopup(this, mBtnSubmit, mManyModelList, mSelectGoodsData, mManyFlagData, mIsZeroSale, new ManyShopPopup.OnShopClickEvent() {
            @Override
            public void OnShopClickEvent() {
                countGoodsNum();
            }
        });
    }

}
