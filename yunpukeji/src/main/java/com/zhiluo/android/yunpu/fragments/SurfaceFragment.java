package com.zhiluo.android.yunpu.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.activity.KuaiJieShouKuan;
import com.zhiluo.android.yunpu.consume.activity.YSLFastConsumeActivity;
import com.zhiluo.android.yunpu.entity.DrawerData;
import com.zhiluo.android.yunpu.entity.HomeBean;
import com.zhiluo.android.yunpu.entity.ShopListBean;
import com.zhiluo.android.yunpu.goods.consume.activity.GoodsConsumeActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsManagerActivity;
import com.zhiluo.android.yunpu.home.activity.MoreActivity;
import com.zhiluo.android.yunpu.home.adapter.SurFaceeUtilAdapter;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.member.consume.activity.MemberChargeManagementActivity;
import com.zhiluo.android.yunpu.member.consume.activity.MemberRechargeActivity;
import com.zhiluo.android.yunpu.member.consume.activity.PlusReduceIntegralActivity;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.manager.activity.MemberListActivity;
import com.zhiluo.android.yunpu.ui.view.CustomGridView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CacheFun;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.util.ArrayList;
import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * Created by ${YSL} on 2018-03-28.
 */

public class SurfaceFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout mfast_pay, mfast_sale, mgoods_sale, mtoday, mmonth_sales, minflate_nums;
    private ImageView topimg;
    private Intent mIntent;
    private LinearLayout ld;
    private RelativeLayout r2;
    private View v4;
    //    private List<Fragment> fragmentList = new ArrayList<>();
//    private ImageView mImg0;
//    private ImageView mImg1;
//    private ImageView[] imgs;
//    private ViewPager mViewPager;
//    private FragmentPagerAdapter mPagerAdapter;
//    private FragmentManager childFragmentManager;
    private CustomGridView surfaceGrid;

    private MyHandler handler;
    private SBean entity;
    private TextView mTvDay;
    private TextView mTvMonth;
    private TextView mRechargeIn;
    private HomeBean mMy;
    private List<DrawerData> mMyUtilList;
    private LoginUpbean mLoginBean;
    private SurFaceeUtilAdapter surAdapter;
    private boolean mNewMem, mMemManager, mGoodManager, mTimesConsume,
            mMemRechargeTime, mMemRecharge, mJiaJianIntegral,
            mExchangeIntegral, mFastCharge, mPanDian, mGoodStock,
            mDiscountManager, mCustomValueManager, mHandDuty = true,
            mPrividerManager, mGradeManager, mRechargeManager, mTCManager,
            mStaffManager, mUserManager, mLabelManager, mMemberSign, mLimitedConsume, mgoodsConsume, mPayManage = true;

    private RelativeLayout rl_sweep_code, rl_consume;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ysl_surface, container, false);

        return view; //initView(view);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        childFragmentManager = getChildFragmentManager();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

        ViewGroup.LayoutParams params = topimg.getLayoutParams();
        params.height = YSLUtils.getPhoneWidth() * 470 / 1080;
        topimg.setLayoutParams(params);
        mfast_pay.setOnClickListener(this);
        mfast_sale.setOnClickListener(this);
        mtoday.setOnClickListener(this);
        mmonth_sales.setOnClickListener(this);
        minflate_nums.setOnClickListener(this);
        mgoods_sale.setOnClickListener(this);
        rl_sweep_code.setOnClickListener(this);
        rl_consume.setOnClickListener(this);
        initData();
        setLisenter();
        getShopList();
        getWelcomeData();
    }

    private void setLisenter() {
        surfaceGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent;
                if (mMyUtilList.get(i).isFlag()) {
                    intent = new Intent(getActivity(), mMyUtilList.get(i).getCls());
                    if (mMyUtilList.get(i).getItem().equals("快速消费")) {
                        if (YSLUtils.isOpenActivon("快速消费")) {
                            startActivity(new Intent(getActivity(), YSLFastConsumeActivity.class));
                        } else {
                            CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    } else if (mMyUtilList.get(i).getItem().equals("新增会员")) {
                        intent.putExtra("intype", "1");
                    } else if (mMyUtilList.get(i).getItem().equals("会员充次")) {
                        intent.putExtra("type", "HYCC");
                    }
                    if (mMyUtilList.get(i).getItem().equals("更多功能")) {
                        /**CacheData.restoreObject("login")(若不是超管，将登录数据保存到本地备用,这是登录界面的注释)为空，点击会闪退 20191018 cy*/
                        if (mLoginBean == null) {
                            CustomToast.makeText(getActivity(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        /***************************************************** 20191018 cy*/
                        startActivityForResult(intent, 0);
                    } else {
                        startActivity(intent);
                    }
                } else {
                    CustomToast.makeText(getActivity(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        HttpHelper.getmClient().cancelRequests(getContext(), true);
        MyApplication.IS_FIRST_LOGING = false;
    }

    /**
     *
     */
    private void initData() {
        handler = new MyHandler();

        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        if (mLoginBean != null) {
            mMy = (HomeBean) CacheFun.restoreObject(mLoginBean.getData().getUM_Acount() + "FUN_MY");
        }
        getMenuList();
        initUnitlList();
        if (mMyUtilList == null || mMyUtilList.isEmpty()) {
            mMyUtilList = new ArrayList<>();
            mMyUtilList.add(new DrawerData("快速消费", R.mipmap.ysl_fast_xiaoshou, YSLFastConsumeActivity.class, true));
            mMyUtilList.add(new DrawerData("新增会员", R.mipmap.addmember_meng, AddMemberActivity.class, mNewMem));
            mMyUtilList.add(new DrawerData("会员管理", R.mipmap.ysl_vip_manager, MemberListActivity.class, mMemManager));
            mMyUtilList.add(new DrawerData("商品管理", R.mipmap.ysl_shop_manager, GoodsManagerActivity.class, mGoodManager));
            mMyUtilList.add(new DrawerData("计次消费", R.mipmap.ysl_counts_shop, MemberChargeManagementActivity.class, mTimesConsume));
            mMyUtilList.add(new DrawerData("会员充次", R.mipmap.ysl_inflate_count1, GoodsConsumeActivity.class, mMemRechargeTime));
            mMyUtilList.add(new DrawerData("会员充值", R.mipmap.ysl_vip_inflatemoney, MemberRechargeActivity.class, mMemRecharge));
            mMyUtilList.add(new DrawerData("加减积分", R.mipmap.ysl_in_reduce_add, PlusReduceIntegralActivity.class, mJiaJianIntegral));
            mMyUtilList.add(new DrawerData("更多功能", R.drawable.upload_goods, MoreActivity.class, true));
        }
        setAdapter();

    }

    private void getMenuList() {
        if (mLoginBean != null) {
            if (mLoginBean.getData().getUM_IsAmin() == 1) {
                mMemManager = mNewMem = mMemRecharge = mGoodManager = mJiaJianIntegral = mRechargeManager =
                        mFastCharge = mMemRechargeTime = mExchangeIntegral = mTimesConsume =
                                mHandDuty = mTCManager = mGoodStock = mPanDian = mPrividerManager = mGradeManager = true;
                mDiscountManager = mStaffManager = mUserManager = mLabelManager = mCustomValueManager = true;
                mMemberSign = mLimitedConsume = mgoodsConsume = mPayManage = true;
                ld.setVisibility(View.VISIBLE);
                r2.setVisibility(View.VISIBLE);
                v4.setVisibility(View.VISIBLE);

            } else {
                for (int i = 0; i < mLoginBean.getData().getMenuInfoList().size(); i++) {
                    LoginUpbean.DataBean.MenuInfoListBean bean = mLoginBean.getData().getMenuInfoList().get(i);
                    if (bean.getMM_Name().equals("会员管理")) {
                        mMemManager = true;
                    }
                    if (bean.getMM_Name().equals("新增会员")) {
                        mNewMem = true;
                    }
                    if (bean.getMM_Name().equals("积分兑换")) {
                        mExchangeIntegral = true;
                    }
                    if (bean.getMM_Name().equals("计次消费")) {
                        mTimesConsume = true;
                    }
                    if (bean.getMM_Name().equals("快速计次")) {
                        mFastCharge = true;
                    }
                    if (bean.getMM_Name().equals("会员充值")) {
                        mMemRecharge = true;
                    }
                    if (bean.getMM_Name().equals("会员充次")) {
                        mMemRechargeTime = true;
                    }
                    if (bean.getMM_Name().equals("积分变动")) {
                        mJiaJianIntegral = true;
                    }
                    if (bean.getMM_Name().equals("会员等级")) {
                        mGradeManager = true;
                    }
                    if (bean.getMM_Name().equals("供应商管理")) {
                        mPrividerManager = true;
                    }
                    if (bean.getMM_Name().equals("库存盘点")) {
                        mPanDian = true;
                    }
                    if (bean.getMM_Name().equals("商品入库")) {
                        mGoodStock = true;
                    }
                    if (bean.getMM_Name().equals("商品管理")) {
                        mGoodManager = true;
                    }
                    if (bean.getMM_Name().equals("充值管理")) {
                        mRechargeManager = true;
                    }
                    if (bean.getMM_Name().equals("套餐管理")) {
                        mTCManager = true;
                    }
                    if (bean.getMM_Name().equals("优惠活动")) {
                        mDiscountManager = true;
                    }
                    if (bean.getMM_Name().equals("员工管理")) {
                        mStaffManager = true;
                    }
                    if (bean.getMM_Name().equals("用户管理")) {
                        mUserManager = true;
                    }
                    if (bean.getMM_Name().equals("标签管理")) {
                        mLabelManager = true;
                    }
                    if (bean.getMM_Name().equals("自定义属性")) {
                        mCustomValueManager = true;
                    }
                    if (bean.getMM_Name().equals("交班")) {
                        mHandDuty = true;
                    }
                    if (bean.getMM_Name().equals("会员签到")) {
                        mMemberSign = true;
                    }
                    if (bean.getMM_Name().equals("限时消费")) {
                        mLimitedConsume = true;
                    }
                    if (bean.getMM_Name().equals("商品消费")) {
                        mgoodsConsume = true;
                    }
                    if (bean.getMM_Name().equals("支出管理")) {
                        mPayManage = true;
                    }
                }

                if (mLoginBean.getData().getAuthorityList() != null && mLoginBean.getData().getAuthorityList().size() > 0) {
                    for (int i = 0; i < mLoginBean.getData().getAuthorityList().size(); i++) {
                        LoginUpbean.DataBean.AuthorityListBean bean = mLoginBean.getData().getAuthorityList().get(i);
                        if (bean.getMM_Code().equals("AU001")) { //首页统计
                            ld.setVisibility(View.VISIBLE);
                            r2.setVisibility(View.VISIBLE);
                            v4.setVisibility(View.VISIBLE);
                        }
                    }
                } else {
                    ld.setVisibility(View.GONE);
                    r2.setVisibility(View.GONE);
                    v4.setVisibility(View.GONE);
                }
            }
        }

    }

    private void initUnitlList() {
        if (mMy != null) {
            mMyUtilList = mMy.getList();
            mMyUtilList.add(0, new DrawerData("快速消费", R.mipmap.ysl_fast_xiaoshou, YSLFastConsumeActivity.class, true));
            mMyUtilList.add(new DrawerData("更多功能", R.drawable.upload_goods, MoreActivity.class, true));
            for (int i = 0; i < mMyUtilList.size(); i++) {
                switch (mMyUtilList.get(i).getItem()) {
                    case "会员管理":
                        mMyUtilList.get(i).setFlag(mMemManager);
                        break;
                    case "新增会员":
                        mMyUtilList.get(i).setFlag(mNewMem);
                        break;
                    case "会员充值":
                        mMyUtilList.get(i).setFlag(mMemRecharge);
                        break;
                    case "商品管理":
                        mMyUtilList.get(i).setFlag(mGoodManager);
                        break;
                    case "加减积分":
                        mMyUtilList.get(i).setFlag(mJiaJianIntegral);
                        break;
                    case "会员充次":
                        mMyUtilList.get(i).setFlag(mMemRechargeTime);
                        break;
                    case "积分兑换":
                        mMyUtilList.get(i).setFlag(mExchangeIntegral);
                        break;
                    case "计次消费":
                        mMyUtilList.get(i).setFlag(mTimesConsume);
                        break;
                    case "商品入库":
                        mMyUtilList.get(i).setFlag(mGoodStock);
                        break;
                    case "库存盘点":
                        mMyUtilList.get(i).setFlag(mPanDian);
                        break;
                    case "供应商管理":
                        mMyUtilList.get(i).setFlag(mPrividerManager);
                        break;
                    case "等级管理":
                        mMyUtilList.get(i).setFlag(mGradeManager);
                        break;
                    case "套餐管理":
                        mMyUtilList.get(i).setFlag(mTCManager);
                        break;
                    case "优惠活动":
                        mMyUtilList.get(i).setFlag(mDiscountManager);
                        break;
                    case "员工管理":
                        mMyUtilList.get(i).setFlag(mStaffManager);
                        break;
                    case "用户管理":
                        mMyUtilList.get(i).setFlag(mUserManager);
                        break;
                    case "标签管理":
                        mMyUtilList.get(i).setFlag(mLabelManager);
                        break;
                    case "自定义属性":
                        mMyUtilList.get(i).setFlag(mCustomValueManager);
                        break;
                    case "交班":
                        mMyUtilList.get(i).setFlag(mHandDuty);
                        break;
                    case "充值管理":
                        mMyUtilList.get(i).setFlag(mRechargeManager);
                        break;
                    case "快速计次":
                        mMyUtilList.get(i).setFlag(mFastCharge);
                        break;
                    case "会员签到":
                        mMyUtilList.get(i).setFlag(mMemberSign);
                        break;
                    case "限时消费":
                        mMyUtilList.get(i).setFlag(mLimitedConsume);
                        break;
                    case "商品消费":
                        mMyUtilList.get(i).setFlag(mgoodsConsume);
                        break;
                    case "支出管理":
                        mMyUtilList.get(i).setFlag(mPayManage);
                        break;
                }
            }
        }

    }

    private void setAdapter() {
        surAdapter = new SurFaceeUtilAdapter(getActivity(), mMyUtilList);
        surfaceGrid.setAdapter(surAdapter);
    }


    /**
     * 获取店铺列表
     */
    private void getShopList() {
        HttpHelper.post(getContext(), "Shops/GetShopsList", new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ShopListBean bean = CommonFun.JsonToObj(responseString, ShopListBean.class);
                CacheData.saveObject("shop", bean);
            }

            @Override
            public void onFailure(String msg) {
                Le("............" + msg);
            }
        });

    }


    /**
     * 获取首页统计数据
     */
    public void getWelcomeData() {
        RequestParams params = new RequestParams();
        params.put("AGGID", "PlatformUser");
        params.put("Type", 1);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                entity = CommonFun.JsonToObj(responseString, SBean.class);

                handler.sendEmptyMessage(1);
            }

            @Override
            public void onFailure(String msg) {
            }
        };
        callBack.setmAPI("Report/GetIndexData");
        HttpHelper.post(MyApplication.getmContext(), "Report/GetIndexData", params, callBack);
    }

    private void initView(View view) {
        topimg = view.findViewById(R.id.top_img);
        mfast_pay = view.findViewById(R.id.id_fast_pay);//快捷付款
        mfast_sale = view.findViewById(R.id.id_fast_sale);//快捷销售
        mgoods_sale = view.findViewById(R.id.id_goods_sale);//商品销售
        mtoday = view.findViewById(R.id.id_today);//今日销售
        mmonth_sales = view.findViewById(R.id.id_month_sale);//本月销售
        minflate_nums = view.findViewById(R.id.id_inflate_num);//充值金额
        ld = view.findViewById(R.id.ld);//销售金额显示
        v4 = view.findViewById(R.id.v4);//
        r2 = view.findViewById(R.id.r2);//

//        mViewPager = view.findViewById(R.id.view_pager);
//        mImg0 = view.findViewById(R.id.img0);
//        mImg1 = view.findViewById(R.id.img1);

        surfaceGrid = view.findViewById(R.id.gv_my_util);
        mTvDay = view.findViewById(R.id.ids_today_sale);
        mTvMonth = view.findViewById(R.id.ids_month_sale);
        mRechargeIn = view.findViewById(R.id.ids_inflate_num);//充值金额数目

        //扫码收款
        rl_sweep_code = view.findViewById(R.id.rl_sweep_code);
        //商品消费
        rl_consume = view.findViewById(R.id.rl_consume);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_sweep_code:
            case R.id.id_fast_pay://快捷收款，被阉割掉部分功能的快速消费
                if (YSLUtils.isOpenActivon("快捷收款")) {
                    mIntent = new Intent(getActivity(), KuaiJieShouKuan.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_fast_sale://快速消费
                if (YSLUtils.isOpenActivon("快速消费")) {
                    mIntent = new Intent(getActivity(), YSLFastConsumeActivity.class);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_consume:
            case R.id.id_goods_sale://商品消费
                if (YSLUtils.isOpenActivon("商品消费")) {
                    mIntent = new Intent(getActivity(), GoodsConsumeActivity.class);
                    mIntent.putExtra("type", "SPXF");
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(getContext(), "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_today:
                break;
            case R.id.id_month_sale:
                break;
            case R.id.id_inflate_num:
                break;

        }
    }


    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                if (SurfaceFragment.this.isAdded() && entity != null) {
                    mTvDay.setText(entity.getData().getGridData().getToday_Sale());
                    mTvMonth.setText(entity.getData().getGridData().getMonth_Sale());
                    mRechargeIn.setText(entity.getData().getGridData().getMonth_Recharge());
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 22222) {
            mMy = (HomeBean) CacheFun.restoreObject(mLoginBean.getData().getUM_Acount() + "FUN_MY");
            initUnitlList();
            setAdapter();
        }
    }
}
