package com.zhiluo.android.yunpu.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zhiluo.android.yunpu.R;

import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.activity.LimitedTimeConsumeActivity;
import com.zhiluo.android.yunpu.consume.activity.OneKeyRefuelingActivity;
import com.zhiluo.android.yunpu.entity.DrawerData;
import com.zhiluo.android.yunpu.entity.HomeBean;
import com.zhiluo.android.yunpu.goods.consume.activity.GoodsConsumeActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.ConsumeOneActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.FastChargesActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsManagerActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.StockTakingActivity;
import com.zhiluo.android.yunpu.home.adapter.MoreGridViewAdapter;
import com.zhiluo.android.yunpu.home.adapter.MyUtilAdapter;
import com.zhiluo.android.yunpu.login.jsonbean.FunctionListBean;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.member.consume.activity.IntegralExchangeActivity;
import com.zhiluo.android.yunpu.member.consume.activity.MemberChargeManagementActivity;
import com.zhiluo.android.yunpu.member.consume.activity.MemberRechargeActivity;
import com.zhiluo.android.yunpu.member.consume.activity.PlusReduceIntegralActivity;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.manager.activity.MemberListActivity;
import com.zhiluo.android.yunpu.member.manager.activity.MemberSignActivity;
import com.zhiluo.android.yunpu.paymanager.activity.PayManagerActivity;
import com.zhiluo.android.yunpu.sms.activity.SendMsgActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.customvlue.CustomValueManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.discount.DiscountManagerActivity;
import com.zhiluo.android.yunpu.ui.activity.good.GoodsWarehousingActivity;
import com.zhiluo.android.yunpu.ui.activity.handduty.HandDutyActivity;
import com.zhiluo.android.yunpu.ui.view.CustomGridView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CacheFun;
import com.zhiluo.android.yunpu.utils.SaveListData;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date: 2017/9/19 9:55
 * Author: 罗咏哲
 * Description: 我的工具
 * Version: 1.0
 */
public class MoreActivity extends BaseActivity implements MoreGridViewAdapter.ItemClick,
        MyUtilAdapter.MyUtilItemClick {
    @Bind(R.id.tv_more_back)
    TextView tvMoreBack;
    @Bind(R.id.tv_no_confirm_title)
    TextView tvNoConfirmTitle;
    @Bind(R.id.tv_more_manage)
    TextView tvMoreManage;
    @Bind(R.id.rl_more_head)
    RelativeLayout rlMoreHead;
    @Bind(R.id.gv_my_util)
    CustomGridView gvMyUtil;
    @Bind(R.id.gv_more_util)
    CustomGridView gvMoreUtil;

    private MoreGridViewAdapter mMoreUtilAdapter;
    private MyUtilAdapter mMyUtilAdapter;
    private List<DrawerData> mMyUtilList, mMoreUtilList;
    private int mManagerFlag = 0;//0-管理，1-确定
    private HomeBean mMy, mMore;
    private LoginUpbean mLoginBean;
    private boolean mNewMem,mMemManager, mGoodManager, mTimesConsume,
            mMemRechargeTime, mMemRecharge, mJiaJianIntegral,
            mExchangeIntegral,mFastCharge,mPanDian,mGoodStock,
            mDiscountManager, mCustomValueManager,mHandDuty = true,
            mPrividerManager,mGradeManager,mRechargeManager, mTCManager,
            mStaffManager,mUserManager, mLabelManager,mMemberSign
            ,mLimitedConsume,mgoodsConsume,moneConsume,mPayManage = true;

//    private boolean isOneKey;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        initVariable();
        loadData();
        setListener();
    }


    private void initVariable() {
        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        if (mLoginBean != null) {
            if (mLoginBean.getData().getUM_IsAmin() == 1) {
                mMemManager = mNewMem  = mMemRecharge  = mGoodManager = mJiaJianIntegral = mRechargeManager =
                        mFastCharge  = mMemRechargeTime  = mExchangeIntegral = mTimesConsume =
                         mHandDuty = mTCManager  = mGoodStock = mPanDian = mPrividerManager = mGradeManager = true;
                 mDiscountManager = mStaffManager = mUserManager = mLabelManager = mCustomValueManager = true;
                mMemberSign = mLimitedConsume = mgoodsConsume = moneConsume = mPayManage = true;
            } else {
                for (int i = 0; i < mLoginBean.getData().getMenuInfoList().size(); i++) {
                    LoginUpbean.DataBean.MenuInfoListBean bean = mLoginBean.getData().getMenuInfoList().get(i);

                    if (bean.getMM_Name().equals("会员管理")) {
                        mMemManager = true;
                    }
                    if (bean.getMM_Name().equals("积分兑换")) {
                        mExchangeIntegral = true;
                    }
                    if (bean.getMM_Name().equals("计次消费")) {
                        mTimesConsume = true;
                    }
                    if (bean.getMM_Name().equals("会员充值")) {
                        mMemRecharge = true;
                    }
                    if (bean.getMM_Name().equals("会员充次")) {
                        mMemRechargeTime = true;
                    }
                    if (bean.getMM_Name().equals("快速计次")) {
                        mFastCharge = true;
                    }
                    if (bean.getMM_Name().equals("交班")) {
                        mHandDuty = true;
                    }
                    if (bean.getMM_Name().equals("积分变动")) {
                        mJiaJianIntegral = true;
                    }

                    if (bean.getMM_Name().equals("等级管理")) {
                        mGradeManager = true;
                    }
                    if (bean.getMM_Name().equals("新增会员")) {
                        mNewMem = true;
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
                    if (bean.getMM_Name().equals("定项消费")) {
                        moneConsume = true;
                    }
                }
            }
            MyApplication.isDingXiang = false;
            MyApplication.isOneKey = false;
            try {
            if (mLoginBean.getData().getShopList().get(0).getSM_FunctionList() != null && !mLoginBean.getData().getShopList().get(0).getSM_FunctionList().equals("")) {

                JSONArray jsonArray = new JSONArray(mLoginBean.getData().getShopList().get(0).getSM_FunctionList());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.get("code").equals("1JY")) {
                        Integer  s = (Integer) jsonObject.get("value");
                        if (s == 1)
                        MyApplication.isOneKey = true;
                    }
                    if (jsonObject.get("code").equals("1D0")) {
                        Integer  s = (Integer) jsonObject.get("value");
                        if (s == 1)
                            MyApplication.isDingXiang = true;
                    }
                }
            } else {
                MyApplication.isOneKey = false;
                MyApplication.isDingXiang = false;
            }
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        mMy = (HomeBean) CacheFun.restoreObject(mLoginBean.getData().getUM_Acount()+"FUN_MY");
        mMore = (HomeBean) CacheFun.restoreObject(mLoginBean.getData().getUM_Acount()+"FUN_MORE");
        if (mMy != null) {
            mMyUtilList = mMy.getList();
            for (int i = 0; i < mMyUtilList.size(); i++) {
                switch (mMyUtilList.get(i).getItem()) {
                    case "会员管理":
                        mMyUtilList.get(i).setFlag(mMemManager);
                        break;
                    case "新增会员":
                        mMyUtilList.get(i).setFlag(mNewMem);
                        break;
                    case "快速计次":
                        mMyUtilList.get(i).setFlag(mFastCharge);
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
                    case "商品出入库":
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
                    case "定项消费":
                    mMyUtilList.get(i).setFlag(moneConsume);
                    break;
                }
            }
        }
        if (mMore != null) {
            mMoreUtilList = mMore.getList();
            for (int i = 0; i < mMoreUtilList.size(); i++) {
                switch (mMoreUtilList.get(i).getItem()) {
                    case "会员管理":
                        mMoreUtilList.get(i).setFlag(mMemManager);
                        break;
                    case "新增会员":
                        mMoreUtilList.get(i).setFlag(mNewMem);
                        break;
                    case "会员充值":
                        mMoreUtilList.get(i).setFlag(mMemRecharge);
                        break;
                    case "商品管理":
                        mMoreUtilList.get(i).setFlag(mGoodManager);
                        break;
                    case "加减积分":
                        mMoreUtilList.get(i).setFlag(mJiaJianIntegral);
                        break;
                    case "会员充次":
                        mMoreUtilList.get(i).setFlag(mMemRechargeTime);
                        break;
                    case "积分兑换":
                        mMoreUtilList.get(i).setFlag(mExchangeIntegral);
                        break;
                    case "计次消费":
                        mMoreUtilList.get(i).setFlag(mTimesConsume);
                        break;
                    case "商品出入库":
                        mMoreUtilList.get(i).setFlag(mGoodStock);
                        break;
                    case "库存盘点":
                        mMoreUtilList.get(i).setFlag(mPanDian);
                        break;
                    case "供应商管理":
                        mMoreUtilList.get(i).setFlag(mPrividerManager);
                        break;
                    case "等级管理":
                        mMoreUtilList.get(i).setFlag(mGradeManager);
                        break;
                    case "套餐管理":
                        mMoreUtilList.get(i).setFlag(mTCManager);
                        break;
                    case "优惠活动":
                        mMoreUtilList.get(i).setFlag(mDiscountManager);
                        break;
                    case "员工管理":
                        mMoreUtilList.get(i).setFlag(mStaffManager);
                        break;
                    case "用户管理":
                        mMoreUtilList.get(i).setFlag(mUserManager);
                        break;
                    case "标签管理":
                        mMoreUtilList.get(i).setFlag(mLabelManager);
                        break;
                    case "自定义属性":
                        mMoreUtilList.get(i).setFlag(mCustomValueManager);
                        break;
                    case "交班":
                        mMoreUtilList.get(i).setFlag(mHandDuty);
                        break;
                    case "充值管理":
                        mMoreUtilList.get(i).setFlag(mRechargeManager);
                        break;
                    case "快速计次":
                        mMoreUtilList.get(i).setFlag(mFastCharge);
                        break;
                    case "会员签到":
                        mMoreUtilList.get(i).setFlag(mMemberSign);
                        break;

                    case "限时消费":
                        mMoreUtilList.get(i).setFlag(mLimitedConsume);
                        break;
                    case "商品消费":
                        mMoreUtilList.get(i).setFlag(mgoodsConsume);
                        break;
                    case "支出管理":
                        mMoreUtilList.get(i).setFlag(mPayManage);
                        break;
                    case "定项消费":
                        mMoreUtilList.get(i).setFlag(moneConsume);
                        break;
                }
            }
        }

        if (mMyUtilList == null || mMyUtilList.isEmpty()) {
            mMyUtilList = new ArrayList<>();
            mMyUtilList.add(new DrawerData("新增会员", R.mipmap.addmember_meng, AddMemberActivity.class, mNewMem));
            mMyUtilList.add(new DrawerData("会员管理", R.mipmap.ysl_vip_manager, MemberListActivity.class, mMemManager));
            mMyUtilList.add(new DrawerData("商品管理", R.mipmap.ysl_shop_manager, GoodsManagerActivity.class, mGoodManager));
            mMyUtilList.add(new DrawerData("计次消费", R.mipmap.ysl_counts_shop, MemberChargeManagementActivity.class, mTimesConsume));
            mMyUtilList.add(new DrawerData("会员充次", R.mipmap.ysl_inflate_count1, GoodsConsumeActivity.class, mMemRechargeTime));
            mMyUtilList.add(new DrawerData("会员充值", R.mipmap.ysl_vip_inflatemoney, MemberRechargeActivity.class, mMemRecharge));
            mMyUtilList.add(new DrawerData("加减积分", R.mipmap.ysl_in_reduce_add, PlusReduceIntegralActivity.class, mJiaJianIntegral));
//            mMyUtilList.add(new DrawerData("更多功能", R.drawable.upload_goods, MoreActivity.class, true));
        }

        if (mMoreUtilList == null || mMoreUtilList.isEmpty()) {
            mMoreUtilList = new ArrayList<>();
            mMoreUtilList.add(new DrawerData("积分兑换", R.mipmap.ysl_jifen_duihuan, IntegralExchangeActivity.class, mExchangeIntegral));
            mMoreUtilList.add(new DrawerData("快速计次", R.mipmap.fast_charge, FastChargesActivity.class, mFastCharge));
            mMoreUtilList.add(new DrawerData("库存盘点", R.mipmap.ysl_stock_pandian, StockTakingActivity.class, mPanDian));
            mMoreUtilList.add(new DrawerData("商品出入库", R.mipmap.ysl_goods_in_out, GoodsWarehousingActivity.class, mGoodStock));
            mMoreUtilList.add(new DrawerData("优惠活动", R.mipmap.ysl_youhui_activity, DiscountManagerActivity.class, mDiscountManager));
            mMoreUtilList.add(new DrawerData("自定义属性", R.mipmap.ysl_zidingyi, CustomValueManagerActivity.class, mCustomValueManager));
            mMoreUtilList.add(new DrawerData("交班", R.mipmap.jiaoban_home, HandDutyActivity.class, mHandDuty));
            mMoreUtilList.add(new DrawerData("发送短信", R.mipmap.send_msg, SendMsgActivity.class, mMemManager));//短信发送权限在会员管理里面

            mMoreUtilList.add(new DrawerData("会员签到", R.mipmap.member_sign, MemberSignActivity.class, mMemberSign));
            mMoreUtilList.add(new DrawerData("限时消费", R.mipmap.limited_consume, LimitedTimeConsumeActivity.class, mLimitedConsume));
            mMoreUtilList.add(new DrawerData("支出管理", R.mipmap.pay_manager, PayManagerActivity.class, mPayManage));
            if (MyApplication.isOneKey){
                mMoreUtilList.add(new DrawerData("一键加油", R.mipmap.onekey_oil, OneKeyRefuelingActivity.class, mgoodsConsume));
            }
            if (MyApplication.isDingXiang){
                mMoreUtilList.add(new DrawerData("定项消费", R.mipmap.one_consume, ConsumeOneActivity.class, moneConsume));
            }


//            mMoreUtilList.add(new DrawerData("综合统计", R.mipmap.icon_statistics, StatisticsActivity.class, mZongHeTongji));
//            mMoreUtilList.add(new DrawerData("供应商管理", R.mipmap.ysl_supplier_manager, ProviderManagerActivity.class, mPrividerManager));
//            mMoreUtilList.add(new DrawerData("等级管理", R.mipmap.ysl_level_manager, GradeManagerActivity.class, mGradeManager));
//            mMoreUtilList.add(new DrawerData("充值管理", R.mipmap.ysl_rechange_manager, RechargeManagerActivity.class, mRechargeManager));
//            mMoreUtilList.add(new DrawerData("套餐管理", R.mipmap.ysl_package_manager, TCManagerActivity.class, mTCManager));
//            mMoreUtilList.add(new DrawerData("员工管理", R.mipmap.ysl_staff_manager, StaffManagerActivity.class, mStaffManager));
//            mMoreUtilList.add(new DrawerData("用户管理", R.mipmap.ysl_user_manager, UserManagerActivity.class, mUserManager));
//            mMoreUtilList.add(new DrawerData("标签管理", R.mipmap.ysl_lab_manager, LabelManagerActivity.class, mLabelManager));
        }
        setAdapter();
    }

    private void loadData() {

    }

    private void setAdapter() {
        mMyUtilAdapter = new MyUtilAdapter(MoreActivity.this, mMyUtilList, 0, this);
        gvMyUtil.setAdapter(mMyUtilAdapter);
        mMoreUtilAdapter = new MoreGridViewAdapter(MoreActivity.this, mMoreUtilList, 0, this);
        gvMoreUtil.setAdapter(mMoreUtilAdapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0){
            setResult(22222);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void setListener() {
        //返回
        findViewById(R.id.tv_more_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(22222);
                finish();
            }
        });
        //管理
        tvMoreManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //管理
                if (mManagerFlag == 0) {
                    mManagerFlag = 1;
                    tvMoreManage.setText("确定");
                    mMyUtilAdapter.setFlag(1);
                    mMoreUtilAdapter.setFlag(1);
                } else {
                    //确定
                    //将设置保存到本地
                    if (mMy == null) {
                        mMy = new HomeBean();
                    }
                    if (mMore == null) {
                        mMore = new HomeBean();
                    }
                    mMy.setList(mMyUtilList);
                    mMore.setList(mMoreUtilList);
                    if (mMy.getList().size()>1 && !mMore.getList().isEmpty()) {
                        mManagerFlag = 0;
                        tvMoreManage.setText("管理");
                        mMyUtilAdapter.setFlag(0);
                        mMoreUtilAdapter.setFlag(0);
                        CacheFun.saveObject(mLoginBean.getData().getUM_Acount()+"FUN_MY", mMy);
                        CacheFun.saveObject(mLoginBean.getData().getUM_Acount()+"FUN_MORE", mMore);
                        EventBus.getDefault().post(mMy);
                    } else {
                        CustomToast.makeText(MoreActivity.this, "设置不合法,请至少显示一个！", Toast.LENGTH_SHORT).show();
                    }
                }
                mMyUtilAdapter.notifyDataSetChanged();
                mMoreUtilAdapter.notifyDataSetChanged();
            }
        });
        gvMyUtil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mManagerFlag == 0) {
                    Intent intent;
                    if (mMyUtilList.get(i).isFlag()) {
                        if (mMyUtilList.get(i).getItem().equals("更多功能")){
                            return;
                        }
                        intent = new Intent(MoreActivity.this, mMyUtilList.get(i).getCls());
                        if (mMyUtilList.get(i).getItem().equals("新增会员")){
                            intent.putExtra("intype","1");
                        }else if (mMyUtilList.get(i).getItem().equals("会员充次")){
                            intent.putExtra("type","HYCC");
                        }
                        startActivity(intent);
                    } else {
                        CustomToast.makeText(MoreActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //更多功能
        gvMoreUtil.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                if (mManagerFlag == 0) {
                    Intent intent;
                    if (mMoreUtilList.get(i).isFlag()) {
                        intent = new Intent(MoreActivity.this, mMoreUtilList.get(i).getCls());
                        if (mMoreUtilList.get(i).getItem().equals("新增会员")){
                            intent.putExtra("intype","1");
                        }else if (mMoreUtilList.get(i).getItem().equals("会员充次")){
                            intent.putExtra("type","HYCC");
                        }
                        startActivity(intent);
                    } else {
                        CustomToast.makeText(MoreActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void click(View view) {
        int pos = (int) view.getTag();
        if (mMoreUtilAdapter.getFlag() == 1) {
            if (mMyUtilList.size() < 14) {
                mMyUtilList.add(mMoreUtilList.get(pos));
                mMoreUtilList.remove(pos);
                mMyUtilAdapter.notifyDataSetChanged();
                mMoreUtilAdapter.notifyDataSetChanged();
            } else {
                CustomToast.makeText(MoreActivity.this, "超过最大设置数", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void myClick(View view) {
        int pos = (int) view.getTag();
        if (mMyUtilAdapter.getFlag() == 1) {
            if (mMyUtilList.size()> 4) {
                mMoreUtilList.add(mMyUtilList.get(pos));
                mMyUtilList.remove(pos);
                mMyUtilAdapter.notifyDataSetChanged();
                mMoreUtilAdapter.notifyDataSetChanged();
            } else {
                CustomToast.makeText(MoreActivity.this, "不能少于4个功能", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
