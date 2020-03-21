/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhiluo.android.yunpu.zxing.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.google.zxing.Result;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.activity.PayConfirmActivity;
import com.zhiluo.android.yunpu.goods.consume.adapter.GoodsConsumeRightAdapter;
import com.zhiluo.android.yunpu.goods.consume.adapter.ShopRulesAdapter;
import com.zhiluo.android.yunpu.goods.consume.adapter.ShopeCarAdapter;
import com.zhiluo.android.yunpu.goods.consume.bean.IntegralScalingBean;
import com.zhiluo.android.yunpu.goods.consume.view.GoodsView;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.activity.AddGoodsActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.YSLAddGoodsActivity;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsModelListBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;
import com.zhiluo.android.yunpu.mvp.presenter.WareHousingPresenter;
import com.zhiluo.android.yunpu.mvp.view.IWareHousingView;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.camera.CameraManager;
import com.zhiluo.android.yunpu.zxing.decode.DecodeThread;
import com.zhiluo.android.yunpu.zxing.utils.BeepManager;
import com.zhiluo.android.yunpu.zxing.utils.CaptureActivityHandler;
import com.zhiluo.android.yunpu.zxing.utils.InactivityTimer;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * This activity opens the camera and does the actual scanning on a background
 * thread. It draws a viewfinder to help the user place the barcode correctly,
 * shows feedback as the image processing is happening, and then overlays the
 * results when a scan is successful.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 * @author Sean Owen
 */
public final class CaptureActivity extends Activity implements GoodsConsumeRightAdapter.ItemViewClick, PopupWindow.OnDismissListener, SurfaceHolder.Callback, ShopRulesAdapter.modelItemListener {


    private static final String TAG = CaptureActivity.class.getSimpleName();
    private static final int SCAN_RETURN = 7777;

    @Bind(R.id.tv_goods_comsume_back)
    TextView tvGoodsComsumeBack;
    @Bind(R.id.blackbraod_white)
    ImageView blackbraodWhite;
    @Bind(R.id.black_a)
    RelativeLayout blackA;
    @Bind(R.id.im_search)
    ImageView imSearch;
    @Bind(R.id.et_goods_consume_search)
    EditText etGoodsConsumeSearch;
    @Bind(R.id.img_sure)
    ImageView imgSure;
    @Bind(R.id.black_b)
    RelativeLayout blackB;
    @Bind(R.id.lv_goods_consume_goods)
    BaseListView lvGoodsConsumeGoods;
    @Bind(R.id.tv_content1)
    TextView tvContent1;
    @Bind(R.id.tv_content2)
    TextView tvContent2;
    @Bind(R.id.rl_notice)
    RelativeLayout rlNotice;
    @Bind(R.id.iv_shop_car)
    ImageView ivShopCar;
    @Bind(R.id.tv_goods_consume_num)
    TextView mTvHaveChossed;
    @Bind(R.id.tv_goods_consume_aa)
    TextView tvGoodsConsumeAa;
    @Bind(R.id.tv_goods_consume_total_money)
    TextView mTvTotalMoney;
    @Bind(R.id.btn_goods_consume_submit)
    Button btnGoodsConsumeSubmit;
    @Bind(R.id.rl_cart)
    LinearLayout rlCart;


    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private InactivityTimer inactivityTimer;
    private BeepManager beepManager;

    private SurfaceView scanPreview = null;
    private RelativeLayout scanContainer;
    private RelativeLayout scanCropView;
    private ImageView scanLine;

    private Rect mCropRect = null;
    private boolean isHasSurface = false;

    private boolean isSPXF = false;

    private PopupWindow mPopupWindow;//购物车详情弹窗
    private ShopeCarAdapter mShopeCarAdapter;//购物车商品adapter
    private int mShoppingCartWidth;//购物车宽度

    private PopupWindow mRulePopupWindow;
    private ShopRulesAdapter mShopRulesAdapter;


    private WareHousingPresenter mPresenter;
    private IWareHousingView mView;
    private GoodsConsumeRightAdapter mRightAdapter;//右边ListView的Adapter

    private String Searchcontetnt;//搜索条件
    private double zuidiPercent;//最低折扣
    private double tejiaPercent;//特价折扣
    private double percent;//计算后的百分比
    private String mCardNo = "00000";//会员卡号
    private String mMemberName = "散客";//会员姓名
    private int mOrderType = 2;//订单类型  默认：0   散客、空卡号：2  会员：1
    private int mGoodsNum;//已选商品数量
    private double mTotalMoney;//已选商品总价
    private double mPoint;//获得积分
    private double mOrderMoney;//订单金额
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodsList;//商品信息（根据会员卡号得到）
    private List<IntegralScalingBean> mIntegralScalingList = new ArrayList<>();//单个商品获得积分占总积分比例集合
    private ViewGroup mViewGroup;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mHaveChoosedGoodList;//已选商品信息
    private GoodsCheckResponseByType.DataBean goodsList;//商品列表
    private GoodsCheckResponseByType.DataBean.DataListBean goodsitem;//选中商品
    SweetAlertDialog mSweetAlertDialog;

    private GoodsModelListBean ModelList;

    private List<List<GoodsModelListBean.DataBean>> modelList = new ArrayList<>();


    public Handler getHandler() {
        return handler;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }


    /**
     * 使Zxing能够继续扫描
     */
    public void continuePreview() {
        if (handler != null) {
            handler.restartPreviewAndDecode();
        }
    }


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        Intent intent = getIntent();
        if (intent != null) {
            isSPXF = intent.getBooleanExtra("isSPXF", false);
        }
        if (!isSPXF) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            setContentView(R.layout.activity_capture);

        } else {
            setContentView(R.layout.activity_sm_goodsconsunme);
            ButterKnife.bind(this);

        }

        initview();
        if (isSPXF){
            initData();
            initLisenter();
        }
    }

    private void initview() {

        scanPreview = (SurfaceView) findViewById(R.id.capture_preview);
        scanContainer = (RelativeLayout) findViewById(R.id.capture_container);
        scanCropView = (RelativeLayout) findViewById(R.id.capture_crop_view);
        scanLine = (ImageView) findViewById(R.id.capture_scan_line);

        inactivityTimer = new InactivityTimer(this);
        beepManager = new BeepManager(this);

        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation
                .RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT,
                0.9f);
        animation.setDuration(3000);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        scanLine.startAnimation(animation);


    }

    private void initData() {

        mPopupWindow = new PopupWindow(this);
        mViewGroup = (ViewGroup) getWindow().getDecorView();
        mHaveChoosedGoodList = new ArrayList<>();
        mPresenter = new WareHousingPresenter(this);
        mPresenter.onCreate("SPXF");

        mView = new IWareHousingView() {
            @Override
            public void getTypeSuccess(List<GoodsType.DataBean> parent, Map<GoodsType.DataBean, List<GoodsType.DataBean>> map) {

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
                goodsList = entity.getData();
                rlNotice.setVisibility(View.GONE);

                if (goodsList.getDataList()!=null &&goodsList.getDataList().size()>0){

                    if (goodsList.getDataList().size() ==1){
                        double numgoods = 0;
                        if (mHaveChoosedGoodList.size() > 0 && goodsList.getDataList().size()>0) {
                            for (int i = 0; i < goodsList.getDataList().size(); i++) {
                                for (int j = 0; j < mHaveChoosedGoodList.size(); j++) {
                                    if (goodsList.getDataList().get(i).getGID().equals(mHaveChoosedGoodList.get(j).getGID())) {
                                        numgoods = mHaveChoosedGoodList.get(j).getNum();
                                        mHaveChoosedGoodList.remove(j);
                                    }
                                }
                            }
                        }

                        goodsList.getDataList().get(0).setNum(numgoods+1);
                        mHaveChoosedGoodList.add(goodsList.getDataList().get(0));
                        countGoodsNum();
                        setRightAdapter();

                        mTvHaveChossed.setText(mGoodsNum + "");
                        mTvTotalMoney.setText(Decima2KeeplUtil.stringToDecimal(mTotalMoney + ""));
                        continuePreview();
                        try {
                            Thread.currentThread().sleep(1500);
                        }catch (Exception e){

                        }
                    } else {
                        if (ModelList != null) {
                            //初始化
                            for (int i = 0;i<ModelList.getData().size();i++){
                                ModelList.getData().get(i).setChecked(false);
                                ModelList.getData().get(i).setEnable(false);
                            }
                            //将商品有的规格设置成可点击
                            for (int i = 0; i < goodsList.getDataList().size(); i++) {
                                if (goodsList.getDataList().get(i).getPM_Modle() != null && !goodsList.getDataList().get(i).getPM_Modle().equals("")) {
                                    List<String> list = DateUtil.getStringForlist(goodsList.getDataList().get(i).getPM_Modle());
                                    for (int j = 0; j < list.size(); j++) {
                                        for (int k = 0; k < ModelList.getData().size(); k++) {
                                            if (list.get(j).equals(ModelList.getData().get(k).getPM_Properties())){
                                                ModelList.getData().get(k).setEnable(true);
                                            }
                                        }
                                    }
                                }
                            }
//                            for (int i= 0;i<ModelList.getData().size();i++){
//                                if (!ModelList.getData().get(i).isEnable() &&ModelList.getData().get(i).getPM_Type() !=0){
//                                    ModelList.getData().get(i).remove();
//                                }
//                            }
                            modelList.clear();

                            //组装规格数据
                            if (ModelList!=null &&ModelList.getData().size()>1){
                                for (int i = 0;i<ModelList.getData().size();i++) {
                                    if (ModelList.getData().get(i).getPM_Type() ==0){
                                        List<GoodsModelListBean.DataBean> list = new ArrayList<>();
                                        list.add(ModelList.getData().get(i));
                                        modelList.add(list);
                                    }else {
                                        for (int j = 0;j<modelList.size();j++){
                                            if (modelList.get(j).get(0).getPM_Name().equals(ModelList.getData().get(i).getPM_Name())){
                                                modelList.get(j).add(ModelList.getData().get(i));
                                            }
                                        }
                                    }
                                }
                            }
                            //设置第一个默认选中
                            for (int i = 0;i<modelList.size();i++){
                                int num = 0;
                                for (int j = 0;j<modelList.get(i).size();j++){
                                    if (modelList.get(i).get(j).isEnable()&&modelList.get(i).get(j).getPM_Type()!=0){
                                        modelList.get(i).get(j).setChecked(true);
                                        num++;
                                        break;
                                    }
                                }
                                if (num ==0){
                                    modelList.remove(i);
                                    i--;
                                }else {
                                    modelList.get(i).remove(0);
                                }
                            }
                            showRulePop();
                        } else {
                            CustomToast.makeText(CaptureActivity.this, "没有获取到规格列表，请刷新后尝试", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (goodsList.getDataList() != null && goodsList.getDataList().size() == 0) {
                    mSweetAlertDialog = new SweetAlertDialog(CaptureActivity.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("没有该商品");
                    mSweetAlertDialog.setConfirmText("新增");
                    mSweetAlertDialog.setCancelText("关闭");
                    mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mSweetAlertDialog.dismiss();
                            Intent intent = new Intent(CaptureActivity.this, YSLAddGoodsActivity.class);
                            intent.putExtra("Searchcontetnt",Searchcontetnt);
                            startActivity(intent);
                        }
                    });
                    mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            continuePreview();
                        }
                    });
                    mSweetAlertDialog.show();
                }
            }

            @Override
            public void getCombofail(String result) {
                CustomToast.makeText(CaptureActivity.this,result,Toast.LENGTH_SHORT).show();
            }
        };
        mPresenter.attachView(mView);
        getproductmodel();

    }

    /**
     * 设置右边ListViewAdapter
     */
    private void setRightAdapter() {
        if (mRightAdapter == null) {
            mRightAdapter = new GoodsConsumeRightAdapter(CaptureActivity.this,
                    mHaveChoosedGoodList, CaptureActivity.this);
            lvGoodsConsumeGoods.setAdapter(mRightAdapter);
        } else {
            mRightAdapter.setParm(mHaveChoosedGoodList);
            mRightAdapter.notifyDataSetChanged();
        }
    }

    private void initLisenter() {


        tvGoodsComsumeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivShopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                YSLUtils.setBackgroundAlpha(0.5f, CaptureActivity.this);
                showPop();
            }
        });

        //搜索监听
        imgSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Searchcontetnt =etGoodsConsumeSearch.getText().toString();
                if (Searchcontetnt!=null&&!Searchcontetnt.equals("")){
                    getGoods(1, 99);
                }else {
                    CustomToast.makeText(CaptureActivity.this,"请先输入商品编码",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //结算
        btnGoodsConsumeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jump2PayConfirm();
            }
        });

        blackbraodWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackA.setVisibility(View.GONE);
                blackB.setVisibility(View.VISIBLE);
            }
        });

        imSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blackA.setVisibility(View.VISIBLE);
                blackB.setVisibility(View.GONE);
            }
        });

    }

    /**
     * 跳转到收银台
     */
    private void jump2PayConfirm() {
        Intent intent = new Intent(CaptureActivity.this, PayConfirmActivity.class);
        ArrayList list = new ArrayList();
        if (mHaveChoosedGoodList.size() == 0) {
            CustomToast.makeText(CaptureActivity.this, "未选择任何商品，请选择商品", Toast.LENGTH_SHORT).show();
            return;
        }
        list.add(mHaveChoosedGoodList);
        Bundle bundle = new Bundle();
        intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) list);
        bundle.putParcelableArrayList("list", list);
        intent.putExtra("isSPXF", true);
        intent.putExtra(MyApplication.PAGE_FLAG, "SPXF");//消费标识
        intent.putExtra("scaling", (Serializable) mIntegralScalingList);
        intent.putExtra("bundle", bundle);
        intent.putExtra("card", mCardNo);
        intent.putExtra("order_num", CreateOrder.createOrder("SP"));
        intent.putExtra("order_type", mOrderType + "");
        intent.putExtra("order_money", Decima2KeeplUtil.stringToDecimal(String.valueOf(mOrderMoney)));
        intent.putExtra("point", Decima2KeeplUtil.stringToDecimal(String.valueOf(mPoint)));
        intent.putExtra("memberName", mMemberName);
        intent.putExtra("discount_money", Decima2KeeplUtil.stringToDecimal(mTvTotalMoney.getText().toString()));
        startActivity(intent);
    }

    /**
     * 计算已选商品数量和总价
     */

    private void countGoodsNum() {
        mGoodsNum = 0;//置零
        mTotalMoney = 0;
        mOrderMoney = 0;
        mPoint = 0;
        mIntegralScalingList.clear();
        for (int i = 0; i < mHaveChoosedGoodList.size(); i++) {
            GoodsCheckResponseByType.DataBean.DataListBean bean = mHaveChoosedGoodList.get(i);
            zuidiPercent = bean.getPM_MinDisCountValue();
            tejiaPercent = bean.getPM_SpecialOfferValue();
            //计算折后金额
            if (bean.getNum() > 0) {
                mGoodsNum += bean.getNum();
            }
            double a = DoubleMathUtil.mul(bean.getPM_UnitPrice(), bean.getNum());


            if (bean.getPM_IsDiscount() ==1){
                if (bean.getPM_SpecialOfferMoney()>-1){
                    mOrderMoney += DoubleMathUtil.mul(bean.getPM_SpecialOfferMoney(), bean.getNum());
                    bean.setEachMoney(bean.getPM_SpecialOfferMoney());
                    bean.setZHMoney(bean.getPM_SpecialOfferMoney() * bean.getNum());
                }else if (tejiaPercent<1&& tejiaPercent>0){
                    percent = compareTandD(zuidiPercent, tejiaPercent);
                    if (percent == 0) {
                        mOrderMoney += DoubleMathUtil.mul(a, 1);
                        bean.setEachMoney(bean.getPM_UnitPrice());
                        bean.setZHMoney(bean.getPM_UnitPrice()*bean.getNum());
                    } else {
                        mOrderMoney += DoubleMathUtil.mul(a, percent);
                        bean.setEachMoney(bean.getPM_UnitPrice() * percent);
                        bean.setZHMoney(bean.getPM_UnitPrice() * percent*bean.getNum());
                    }
                }else {
                    mOrderMoney += DoubleMathUtil.mul(a, 1);
                    bean.setEachMoney(bean.getPM_UnitPrice());
                    bean.setZHMoney(bean.getPM_UnitPrice()*bean.getNum());
                }


            }else {
                mOrderMoney += DoubleMathUtil.mul(a, 1);
                bean.setEachMoney(bean.getPM_UnitPrice());
                bean.setZHMoney(bean.getPM_UnitPrice()*bean.getNum());
            }

            mTotalMoney = mOrderMoney;
            mPoint = 0;
//            }
        }
        mTvHaveChossed.setText(mGoodsNum + "");
        mTvTotalMoney.setText(Decima2KeeplUtil.stringToDecimal(mTotalMoney + ""));
        if (mRightAdapter != null) {
            mRightAdapter.notifyDataSetChanged();
        }
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


    private ShapedImageView ivGoodsImage ;
    private TextView tvName ;
    private TextView tvPrice ;
    private TextView tvStork;

    private void showRulePop() {
        mRulePopupWindow = new PopupWindow(this);
        View anchor = LayoutInflater.from(this).inflate(R.layout.activity_goods_consume, null);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_sm_goods_rule_pop, null);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvSure = view.findViewById(R.id.tv_sure);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        mShopRulesAdapter = new ShopRulesAdapter(this,modelList,CaptureActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        mRulePopupWindow.setOnDismissListener(this);
        recyclerView.setAdapter(mShopRulesAdapter);
        mRulePopupWindow.setContentView(view);
        mRulePopupWindow.setAnimationStyle(R.style.pop_show_style);
        mRulePopupWindow.setTouchable(true);
        mRulePopupWindow.setFocusable(true);
        mRulePopupWindow.setBackgroundDrawable(new ColorDrawable());
        mRulePopupWindow.setWidth(YSLUtils.getPhoneWidth());
        mRulePopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mRulePopupWindow.showAtLocation(anchor, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

        ivGoodsImage = view.findViewById(R.id.iv_item_goods);
        tvName = view.findViewById(R.id.tv_item_goods_name);
        tvPrice = view.findViewById(R.id.tv_item_goods_price);
        tvStork = view.findViewById(R.id.tv_item_goods_stock);
        initpop();
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goodsitem != null){
                    double numgoods = 0;
                    if (mHaveChoosedGoodList.size() > 0 ) {
                        for (int j = 0; j < mHaveChoosedGoodList.size(); j++) {
                            if (goodsitem.getGID().equals(mHaveChoosedGoodList.get(j).getGID())) {
                                numgoods = mHaveChoosedGoodList.get(j).getNum();
                                mHaveChoosedGoodList.remove(j);
                            }
                        }
                    }
                    goodsitem.setNum(numgoods + 1);
                    mHaveChoosedGoodList.add(goodsitem);
                }

                countGoodsNum();
                setRightAdapter();

                mTvHaveChossed.setText(mGoodsNum + "");
                mTvTotalMoney.setText(Decima2KeeplUtil.stringToDecimal(mTotalMoney + ""));
                continuePreview();
                mRulePopupWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, CaptureActivity.this);
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuePreview();
                mRulePopupWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, CaptureActivity.this);
            }
        });


    }



    private  void initpop(){
        goodsitem = null;
        StringBuilder modelName = new StringBuilder("");
        for (int j = 0; j < modelList.size(); j++) {
            for (int k = 0; k < modelList.get(j).size(); k++) {
                if (modelList.get(j).get(k).isChecked()) {
                    modelName.append(modelList.get(j).get(k).getPM_Properties() + "|");
                }
            }
        }
        String str = String.valueOf(modelName);
        if (str.substring(str.length() - 1, str.length()).equals("|")) {
            str = str.substring(0, str.length() - 1);
        }

        for (int i = 0;i<goodsList.getDataList().size();i++){
            if (str.equals(goodsList.getDataList().get(i).getPM_Modle())){

                goodsitem = goodsList.getDataList().get(i);
            }
        }

        if (goodsitem!= null){
            //头像
            if (goodsitem.getPM_BigImg() != null) {
                if (goodsitem.getPM_BigImg().contains("http")) {
                    Glide.with(this).load(goodsitem.getPM_BigImg()).into(ivGoodsImage);
                } else {
                    String url = goodsitem.getPM_BigImg();
                    if (url.contains("../")) {
                        String[] s = url.split("\\.\\./");
                        url = s[2];
                    }
                    Glide.with(this).load(HttpAPI.API().MAIN_DOMAIN + "/"+url).into(ivGoodsImage);
                }
            } else {
                ivGoodsImage.setImageResource(R.drawable.defalut_goods);
            }
            tvName.setText(goodsitem.getPM_Name()+"");
            tvPrice.setText("¥  " + Decima2KeeplUtil.stringToDecimal(goodsitem.getPM_UnitPrice() + ""));
            //库存
            if (goodsitem.getPM_Metering() != null) {
                tvStork.setText(String.valueOf(goodsitem.getStock_Number()) + goodsitem.getPM_Metering());
            } else {
                tvStork.setText(goodsitem.getStock_Number() + "");
            }
        }else {
            ivGoodsImage.setImageResource(R.drawable.defalut_goods);
            tvName.setText("无此规格商品");
            tvPrice.setText("¥0.00");
            tvStork.setText("0");
        }
    }


    private void showPop() {
        mPopupWindow = new PopupWindow(this);
        View anchor = LayoutInflater.from(this).inflate(R.layout.activity_goods_consume, null);
        View view = LayoutInflater.from(this).inflate(R.layout.layout_shop_car_pop, null);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvClear = view.findViewById(R.id.tv_clear);
        final TextView tvCount = view.findViewById(R.id.tv_count);
        tvCount.setText(mHaveChoosedGoodList.size() + "种商品" + ",共" + (int) getReallyCount() + "件");
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        mShopeCarAdapter = new ShopeCarAdapter(this, mHaveChoosedGoodList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        mPopupWindow.setOnDismissListener(this);
        recyclerView.setAdapter(mShopeCarAdapter);
        mPopupWindow.setContentView(view);
        mPopupWindow.setAnimationStyle(R.style.pop_show_style);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.setWidth(YSLUtils.getPhoneWidth());
        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.showAtLocation(anchor, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCount.setText("购物车空空如也");
                mHaveChoosedGoodList.clear();
//                getGoods(1, 20);
                mTvTotalMoney.setText("0.00");
                mGoodsNum = 0;
                mTotalMoney = 0.0;
                mPoint = 0.0;
                mTvHaveChossed.setText("0");
                mOrderMoney = 0.0;
                mGoodsList = null;
                //initSpacialData();
                mShopeCarAdapter.notifyDataSetChanged();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, CaptureActivity.this);
            }
        });


    }

    /**
     * @return ，获取商品的具体件数
     */
    private double getReallyCount() {
        double k = 0;
        for (int i = 0; i < mHaveChoosedGoodList.size(); i++) {
            k += mHaveChoosedGoodList.get(i).getNum();
        }
        return k;
    }


    @Override
    public void click(View view) {
        int position = (int) view.getTag();
        double num;
        num = mHaveChoosedGoodList.get(position).getNum();
        switch (view.getId()) {
            //移除
            case R.id.iv_reduce:
                if (num > 0) {
                    num--;
                    if (num > 0) {
                        mHaveChoosedGoodList.get(position).setNum(num);
                    }else {
                        mHaveChoosedGoodList.remove(position);
                    }
                    countGoodsNum();
                }
                break;
            //增加
            case R.id.iv_add:
                setAddGoodsAnimation(view);
                num++;
                mHaveChoosedGoodList.get(position).setNum(num);
                countGoodsNum();
                break;
        }

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
        GoodsView goodsView = new GoodsView(CaptureActivity.this);
        goodsView.setCircleStartPoint(goodsPoint[0], goodsPoint[1]);
        mShoppingCartWidth = mTvHaveChossed.getMeasuredWidth();
        goodsView.setCircleEndPoint(shoppingCartPoint[0] + mShoppingCartWidth / 2, shoppingCartPoint[1]);
        //添加View并执行动画
        mViewGroup.addView(goodsView);
        goodsView.startAnimation();
    }

    private void getGoods(int index, int size) {

        RequestParams params = new RequestParams();

        params.put("DataType", 2);
        params.put("pageIndex", index);
        params.put("pageSize", size);
        params.put("PT_GID", "");
//        params.put("PM_CodeOrNameOrSimpleCode",Searchcontetnt);//模糊查找
        params.put("PM_CodePrecise",Searchcontetnt);//条码精确查找

        mPresenter.getcombo(params);

    }

    private void getproductmodel(){

        HttpHelper.post(this, HttpAPI.API().GOODSMODEL, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Log.d(TAG, "onSuccess: ");
                ModelList = CommonFun.JsonToObj(responseString,GoodsModelListBean.class);


            }

            @Override
            public void onFailure(String msg) {
                Log.d(TAG, "onFailure: "+msg);

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        // CameraManager must be initialized here, not in onCreate(). This is
        // necessary because we don't
        // want to open the camera driver and measure the screen size if we're
        // going to show the help on
        // first launch. That led to bugs where the scanning rectangle was the
        // wrong size and partially
        // off screen.
        cameraManager = new CameraManager(getApplication());

        handler = null;

        if (isHasSurface) {
            // The activity was paused but not stopped, so the surface still
            // exists. Therefore
            // surfaceCreated() won't be called, so init the camera here.
            initCamera(scanPreview.getHolder());
        } else {
            // Install the callback and wait for surfaceCreated() to init the
            // camera.
            scanPreview.getHolder().addCallback(this);
        }

        inactivityTimer.onResume();
    }

    @Override
    protected void onPause() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        beepManager.close();
        cameraManager.closeDriver();
        if (!isHasSurface) {
            scanPreview.getHolder().removeCallback(this);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!isHasSurface) {
            isHasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isHasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * A valid barcode has been found, so give an indication of success and show
     * the results.
     *
     * @param rawResult The contents of the barcode.
     * @param bundle    The extras
     */
    public void handleDecode(Result rawResult, Bundle bundle) {
        inactivityTimer.onActivity();
        beepManager.playBeepSoundAndVibrate();

        if (isSPXF){
            Searchcontetnt = rawResult.getText();
            getGoods(1,99);
        }else {
            Intent resultIntent = new Intent();
            bundle.putInt("width", mCropRect.width());
            bundle.putInt("height", mCropRect.height());
            bundle.putString("result", rawResult.getText());
            resultIntent.putExtras(bundle);
            this.setResult(SCAN_RETURN, resultIntent);
            CaptureActivity.this.finish();
        }


    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            cameraManager.openDriver(surfaceHolder);
            // Creating the handler starts the preview, which can also throw a
            // RuntimeException.
            if (handler == null) {
                handler = new CaptureActivityHandler(this, cameraManager, DecodeThread.ALL_MODE);
            }

            initCrop();
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            Log.w(TAG, "Unexpected error initializing camera", e);
            displayFrameworkBugMessageAndExit();
        }
    }

    private void displayFrameworkBugMessageAndExit() {
        // camera error
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("Camera error");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        builder.show();
    }

    public void restartPreviewAfterDelay(long delayMS) {
        if (handler != null) {
            handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
        }
    }

    public Rect getCropRect() {
        return mCropRect;
    }

    /**
     * 初始化截取的矩形区域
     */
    private void initCrop() {
        int cameraWidth = cameraManager.getCameraResolution().y;
        int cameraHeight = cameraManager.getCameraResolution().x;

        /** 获取布局中扫描框的位置信息 */
        int[] location = new int[2];
        scanCropView.getLocationInWindow(location);

        int cropLeft = location[0];
        int cropTop = location[1] - getStatusBarHeight();

        int cropWidth = scanCropView.getWidth();
        int cropHeight = scanCropView.getHeight();

        /** 获取布局容器的宽高 */
        int containerWidth = scanContainer.getWidth();
        int containerHeight = scanContainer.getHeight();

        /** 计算最终截取的矩形的左上角顶点x坐标 */
        int x = cropLeft * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的左上角顶点y坐标 */
        int y = cropTop * cameraHeight/ containerHeight;


        /** 计算最终截取的矩形的宽度 */
        int width = cropWidth * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的高度 */
        int height = cropHeight *cameraHeight /containerHeight;


        /** 生成最终的截取的矩形 */
        mCropRect = new Rect(x, y, width + x, height + y);


    }

    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void onDismiss() {
        YSLUtils.setBackgroundAlpha(1.0f, this);
    }

    @Override
    public void modelItemChecked(GoodsModelListBean.DataBean conditionBean, boolean isChecked,int position) {

        for (int j = 0; j < modelList.get(position).size(); j++) {
            modelList.get(position).get(j).setChecked(false);
        }
        if (isChecked) {
            conditionBean.setChecked(true);
        }
        initpop();

    }
}