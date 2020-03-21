package com.zhiluo.android.yunpu.member.manager.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.activity.GoodsConsumeActivity;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.member.consume.activity.MemberChargeManagementActivity;
import com.zhiluo.android.yunpu.member.consume.activity.MemberRechargeActivity;
import com.zhiluo.android.yunpu.member.consume.activity.VipDelayActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.YSLMemberInfoBean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.statistics.account.activity.ChuZhiActivity;
import com.zhiluo.android.yunpu.statistics.account.activity.JiCiActivity;
import com.zhiluo.android.yunpu.statistics.account.activity.JiFenActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.OrderActivity;
import com.zhiluo.android.yunpu.statistics.order.bean.JiCiBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * Created by ${YSL} on 2018-03-30.
 * 会员信息
 */

public class YSLMemberDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.id_vip_inflate_money)
    RelativeLayout idVipInflateMoney;
    @Bind(R.id.id_vip_inflate_count)
    RelativeLayout idVipInflateCount;
    @Bind(R.id.id_goods_sale)
    RelativeLayout idGoodsSale;
    @Bind(R.id.id_jici_xiaofei)
    RelativeLayout idJiciXiaofei;

    private TextView mBack, mHistoryConsume, mYue, mVipintegral, mTimes, mUsername, mUserno;
    private ImageView mMore;
    private ShapedImageView mheadImg;
    private RelativeLayout mRHistoryConsume, mRYue, mRVipintegral, mRTimes;
    private Intent mIntent;
    private Bundle mBundle;
    private AllMemberListBean.DataBean.DataListBean vipdetail;//会员信息
    private YSLMemberInfoBean.DataBean.DataListBean mVipInfo;
    private JiCiBean jiCiBean;
    private YSLMemberInfoBean.DataBean.DataListBean mBackInfo;
    private RelativeLayout mChangePassword, mVipDelay, mVipchangeCard, mVipCardService, mVipBaseMessage;
    private PopupWindow popupWindow;
    private boolean mDeleteMem, mModifyMem, mGuaShiMem, mMemRecord, mModifyPaw, mHuanKa, mMemDelay, mGoodConsume,
            mMemRechargeMoney, mTimeConsume, mFastConsume, mExchangeIntegral;
    private LoginUpbean mLoginBean;

    private RelativeLayout ChuZi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(YSLMemberDetailsActivity.this);
        setContentView(R.layout.activity_ysl_member_detail);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        setLisenter();
    }

    private void setLisenter() {
        idVipInflateMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YSLUtils.isOpenActivon("会员充值")) {
                    mIntent = new Intent(YSLMemberDetailsActivity.this, MemberRechargeActivity.class);
                    mIntent.putExtra("MDZZ", "mdzz");
                    mIntent.putExtra("card", vipdetail.getVCH_Card());
                    mIntent.putExtra("name", vipdetail.getVIP_Name());
                    mIntent.putExtra("vip", vipdetail);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLMemberDetailsActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
        idVipInflateCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YSLUtils.isOpenActivon("会员充次")) {
                    mIntent = new Intent(YSLMemberDetailsActivity.this, GoodsConsumeActivity.class);
                    mIntent.putExtra("type","HYCC");
                    mIntent.putExtra("MDZZ", "mdzz");
                    mIntent.putExtra("card", vipdetail.getVCH_Card());
                    mIntent.putExtra("name", vipdetail.getVIP_Name());
                    mIntent.putExtra("vip", vipdetail);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLMemberDetailsActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
        idGoodsSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YSLUtils.isOpenActivon("商品消费")) {
                    mIntent = new Intent(YSLMemberDetailsActivity.this, GoodsConsumeActivity.class);
                    mIntent.putExtra("card", vipdetail.getVCH_Card());
                    mIntent.putExtra("name", vipdetail.getVIP_Name());
                    mIntent.putExtra("vip", vipdetail);
                    mIntent.putExtra("MDZZ", "mdzz");
                    mIntent.putExtra("type","SPXF");
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLMemberDetailsActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
        idJiciXiaofei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YSLUtils.isOpenActivon("计次消费")) {
                    mIntent = new Intent(YSLMemberDetailsActivity.this, MemberChargeManagementActivity.class);
                    mIntent.putExtra("card", vipdetail.getVCH_Card());
                    mIntent.putExtra("name", vipdetail.getVIP_Name());
                    mIntent.putExtra("MDZZ", "mdzz");
                    mIntent.putExtra("vip", vipdetail);
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(YSLMemberDetailsActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initDatas() {
        mBundle = getIntent().getBundleExtra("bundle");
        vipdetail = (AllMemberListBean.DataBean.DataListBean) mBundle.getSerializable("VIP_INFO");//不完整的会员信息
        mVipInfo = (YSLMemberInfoBean.DataBean.DataListBean) mBundle.getSerializable("YSL_VIP_INFO");//带计次的会员信息
        mUserno.setText(vipdetail.getVG_Name() + " | NO" + vipdetail.getVCH_Card());//会员卡号
        mYue.setText(Decima2KeeplUtil.stringToDecimal(vipdetail.getMA_AvailableBalance() + ""));//显示余额
        mHistoryConsume.setText(Decima2KeeplUtil.stringToDecimal(vipdetail.getMA_AggregateAmount() + ""));//累计消费
        mVipintegral.setText(Decima2KeeplUtil.stringToDecimal(vipdetail.getMA_AvailableIntegral() + ""));//会员积分
        checkMemberInfo(mVipInfo.getVCH_Card());
        if (vipdetail.getVIP_Name() != null && !vipdetail.getVIP_Name().isEmpty()) {//会员姓名
            mUsername.setText(vipdetail.getVIP_Name());
        } else {
            mUsername.setText("姓名未设置");
        }
        if (vipdetail.getVIP_HeadImg() != null) {//会员头像
            if (vipdetail.getVIP_HeadImg().contains("http")) {
                Glide.with(getApplicationContext()).load(Uri.parse(vipdetail.getVIP_HeadImg())).into(mheadImg);
            } else {
                Glide.with(getApplicationContext()).load(Uri.parse(HttpAPI.API().MAIN_DOMAIN + vipdetail.getVIP_HeadImg())).into(mheadImg);
            }
        }
        getPower();

    }

    /**
     * 初始化布局变量
     */
    private void initViews() {
        mBack = findViewById(R.id.tv_backvipdetail_activity);
        mHistoryConsume = findViewById(R.id.tv_history_consume);
        mYue = findViewById(R.id.tv_yue);
        mVipintegral = findViewById(R.id.tv_vip_integral);
        mTimes = findViewById(R.id.tv_times);
        mheadImg = findViewById(R.id.img_head);
        mRHistoryConsume = findViewById(R.id.r2);
        mRYue = findViewById(R.id.r3);
        mRVipintegral = findViewById(R.id.r4);
        mRTimes = findViewById(R.id.r5);
        mUsername = findViewById(R.id.username);
        mUserno = findViewById(R.id.userno);
        mMore = findViewById(R.id.img_more);
        mVipBaseMessage = findViewById(R.id.rl_baseMessage);

        mBack.setOnClickListener(this);//返回按钮
        mRHistoryConsume.setOnClickListener(this);//历史消费
        mRYue.setOnClickListener(this);//储值余额
        mRVipintegral.setOnClickListener(this);//会员积分
        mRTimes.setOnClickListener(this);//计次卡
        mheadImg.setOnClickListener(this);//头像点击
        mMore.setOnClickListener(this);//更多
        mVipBaseMessage.setOnClickListener(this);//基本信息

    }

    /**
     * 获取权限部分
     */
    private void getPower() {
        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        if (mLoginBean != null) {
            if (mLoginBean.getData().getUM_IsAmin() == 1) {
                mDeleteMem = mModifyMem = mGuaShiMem = mMemRecord = mModifyPaw = mHuanKa = mMemDelay
                        = mGoodConsume = mMemRechargeMoney = mTimeConsume = mFastConsume = mExchangeIntegral = true;
            } else {
                for (int i = 0; i < mLoginBean.getData().getMenuInfoList().size(); i++) {
                    LoginUpbean.DataBean.MenuInfoListBean bean = mLoginBean.getData().getMenuInfoList().get(i);
                    if (bean.getMM_Name().equals("商品消费")) {
                        mGoodConsume = true;
                    }
                    if (bean.getMM_Name().equals("快速消费")) {
                        mFastConsume = true;
                    }
                    if (bean.getMM_Name().equals("计次消费")) {
                        mTimeConsume = true;
                    }
                    if (bean.getMM_Name().equals("积分兑换")) {
                        mExchangeIntegral = true;
                    }
                    if (bean.getMM_Name().equals("报表管理")) {
                        mMemRecord = true;
                    }
                    if (bean.getMM_Name().equals("删除会员")) {
                        mDeleteMem = true;
                    }
                    if (bean.getMM_Name().equals("编辑会员")) {
                        mModifyMem = true;
                    }
                    if (bean.getMM_Name().equals("修改会员")) {
                        mModifyMem = true;
                    }
                    if (bean.getMM_Name().equals("会员挂失")) {
                        mGuaShiMem = true;
                    }
                    if (bean.getMM_Name().equals("修改密码")) {
                        mModifyPaw = true;
                    }
                    if (bean.getMM_Name().equals("会员换卡")) {
                        mHuanKa = true;
                    }
                    if (bean.getMM_Name().equals("会员延期")) {
                        mMemDelay = true;
                    }
                }
            }
        }
    }

    /**
     * 查询单个会员信息获取计次卡信息
     *
     * @param k 会员卡号
     */
    private void checkMemberInfo(String k) {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("Card", k);
        client.post(HttpAPI.API().CHARGELIST, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                jiCiBean = CommonFun.JsonToObj(responseString, JiCiBean.class);

                int sum = 0;
                for (int i = 0; i < jiCiBean.getData().size(); i++) {
                    sum += jiCiBean.getData().get(i).getMCA_HowMany();
                }
                mTimes.setText(sum + "");//剩余计次

//                vipdetail = CommonFun.JsonToObj(responseString, AllMemberListBean.DataBean.DataListBean.class);
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r2://历史记录
                mIntent = new Intent(YSLMemberDetailsActivity.this, OrderActivity.class);
                mIntent.putExtra("vipcard", mVipInfo.getVCH_Card());
                mIntent.putExtra("bundle", mBundle);
                startActivity(mIntent);
                break;
            case R.id.r3://储值
                mIntent = new Intent(YSLMemberDetailsActivity.this, ChuZhiActivity.class);
                mIntent.putExtra("vipcard", mVipInfo.getGID());
                startActivity(mIntent);
                break;
            case R.id.r4://积分
                mIntent = new Intent(YSLMemberDetailsActivity.this, JiFenActivity.class);
                mIntent.putExtra("vipcard", mVipInfo.getGID());
                startActivity(mIntent);
                break;
            case R.id.r5://计次
                mIntent = new Intent(YSLMemberDetailsActivity.this, JiCiActivity.class);
                mIntent.putExtra("vipcard", mVipInfo.getVCH_Card());
                startActivity(mIntent);
                break;
            case R.id.tv_backvipdetail_activity:
                YSLMemberDetailsActivity.this.finish();//返回按钮
                break;
            case R.id.img_more:
                showPupWindow(v);
                break;
            case R.id.mean_r1://修改密码
                popupWindow.dismiss();
                if (YSLUtils.isOpenActivon("修改密码")) {
                    mIntent = new Intent(YSLMemberDetailsActivity.this, ChangePwdActivity.class);
                    mBundle.putSerializable("YSL_VIP_INFO", mVipInfo);
                    mIntent.putExtra("bundle", mBundle);
                    mIntent.putExtra("TYPE", "vip");
                    startActivity(mIntent);
                } else {
                    CustomToast.makeText(this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mean_r2://会员延期
                popupWindow.dismiss();
                if (YSLUtils.isOpenActivon("会员延期")) {
                    if (vipdetail.getVIP_IsForver() == 1) {
                        new SweetAlertDialog(YSLMemberDetailsActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("提示")
                                .setContentText("该会员是永久会员！")
                                .setConfirmText("了解")
                                .show();
                    } else {
                        mIntent = new Intent(YSLMemberDetailsActivity.this, VipDelayActivity.class);
                        mBundle.putSerializable("vipinfo", vipdetail);
                        mBundle.putSerializable("YSL_VIP_INFO", mVipInfo);
                        mIntent.putExtra("bundle", mBundle);
                        startActivity(mIntent);
                    }
                } else {
                    CustomToast.makeText(this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mean_r3://会员换卡
                popupWindow.dismiss();
                if (YSLUtils.isOpenActivon("会员换卡")) {
                    mIntent = new Intent(YSLMemberDetailsActivity.this, ChangeCardActivity.class);
                    mBundle.putSerializable("YSL_VIP_INFO", mVipInfo);
                    mIntent.putExtra("bundle", mBundle);
                    startActivityForResult(mIntent, 923);
                } else {
                    CustomToast.makeText(this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.mean_r4://挂失/解约
                popupWindow.dismiss();
                if (YSLUtils.isOpenActivon("会员挂失")) {
                    mIntent = new Intent(YSLMemberDetailsActivity.this, LossAndLockActivity.class);
                    mBundle.putSerializable("YSL_VIP_INFO", mVipInfo);
                    mIntent.putExtra("bundle", mBundle);
                    startActivityForResult(mIntent, 92);
                } else {
                    CustomToast.makeText(this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
                break;
//            case R.id.mean_r5://会员充值
//                popupWindow.dismiss();
//                mIntent = new Intent(YSLMemberDetailsActivity.this, MemberRechargeActivity.class);
//                mIntent.putExtra("cardNo", vipdetail.getVCH_Card());
//                mIntent.putExtra("MDZZ", "mdzz");
//                startActivity(mIntent);
//                break;
            case R.id.rl_baseMessage:
                mIntent = new Intent(YSLMemberDetailsActivity.this, VipBaseMessage.class);
                mIntent.putExtra("deletvip", mDeleteMem);//删除会员权限
                mIntent.putExtra("editvip", mModifyMem);//编辑会员权限
                mBundle.putSerializable("YSL_VIP_INFO", mVipInfo);//被初始化的实体类
                mBundle.putSerializable("VIP_INFO", vipdetail);
                mIntent.putExtra("bundle", mBundle);
                startActivity(mIntent);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 953) {
            /*Bundle bundle=new Bundle();
            bundle= getIntent().getBundleExtra("bundles");
            mBackInfo = (YSLMemberInfoBean.DataBean.DataListBean) bundle.getSerializable("YSL_VIP_INFO_BACK");//带计次的会员信息
            if (mBackInfo!=null){
                mVipInfo=mBackInfo;
            }*/
            String cardString = data.getStringExtra("card");
            mUserno.setText("普通会员 | NO" + cardString);//会员卡号
            mVipInfo.setVCH_Card(cardString);


        }
        if (requestCode == 92) {
            if (data != null) {
                int state = data.getIntExtra("state", 0);
                mVipInfo.setVIP_State(state);//带计次的会员信息
            }
        }
    }

    /**
     * 弹出窗口
     *
     * @param v 弹出控件根
     */
    private void showPupWindow(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.pup_ysl_vip_detail, null);
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(view);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        popupWindow.setWidth(width / 2);
        popupWindow.setHeight(height * 8 / 20);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        mChangePassword = (RelativeLayout) view.findViewById(R.id.mean_r1);
        mVipDelay = (RelativeLayout) view.findViewById(R.id.mean_r2);
        mVipchangeCard = (RelativeLayout) view.findViewById(R.id.mean_r3);
        mVipCardService = (RelativeLayout) view.findViewById(R.id.mean_r4);
//        mVipRecharge = view.findViewById(R.id.mean_r5);
        mChangePassword.setOnClickListener(this);
        mVipDelay.setOnClickListener(this);
        mVipchangeCard.setOnClickListener(this);
        mVipCardService.setOnClickListener(this);
//        mVipRecharge.setOnClickListener(this);
        popupWindow.showAsDropDown(v);


    }
}
