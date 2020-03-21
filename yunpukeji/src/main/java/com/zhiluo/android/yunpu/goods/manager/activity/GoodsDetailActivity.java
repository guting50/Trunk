package com.zhiluo.android.yunpu.goods.manager.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.login.jsonbean.GoodsFiledsBean;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.member.manager.adapter.addGoodsfieldsAdapter;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.popup.ImageCustomPopup;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.good.InOutWareHouseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 商品详情
 */
public class GoodsDetailActivity extends BaseActivity implements MyMenuPopWindow.OnItemClickListener, addGoodsfieldsAdapter.OnImageClickEvent {

    @Bind(R.id.goods_detail_sl)
    ScrollView goodsDetailSl;
    @Bind(R.id.te_save)
    TextView teSave;
    @Bind(R.id.rl_bottom)
    RelativeLayout rlBottom;
    private GoodsCheckResponseByType.DataBean.DataListBean goodsdetail;
    private TextView tvname;
    private TextView tvtype;
    private TextView tvcode;
    private TextView tvsimcode;
    private TextView tvmetering;
    private TextView tvmodel;
    private TextView tvunitprice;
    private TextView tvbrand;
    private TextView tv_back_activity;
    private TextView mTvType;
    private ImageView iv_more_goodsdetailactivity;
    private ShapedImageView mIvGoodsPicture;

    private MyMenuPopWindow mPopupMenu;
    private TextView tvDisType, tvPointType, tvFixedPoint, tvSpecialDis, tvLowerDis, tvStork,
            tvMemberPrice, tvDefaultPrice;
    private PopupWindow mPopupWindow = new PopupWindow();

    private RecyclerView recyclerView;
    private addGoodsfieldsAdapter mgoodsfieldsAdapter;
    private List<GoodsFiledsBean.DataBean> goodsfields;//商品自定义属性
    private ChangeHandler changeHandler;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mStockGoodList = new ArrayList<>();//入库商品列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(GoodsDetailActivity.this);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);
        initView();
        initData();
        updataView();
        initLinsinter();
    }


    private void initLinsinter() {
        mPopupMenu.setOnItemClickListener(this);
        tv_back_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsDetailActivity.this.finish();

            }
        });
        iv_more_goodsdetailactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // showPopupMenu(v);
                showPopWindow(v);
            }
        });
        teSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsDetailActivity.this, InOutWareHouseActivity.class);
                Bundle bundle = new Bundle();
                if (mStockGoodList != null) {
                    mStockGoodList.clear();
                }
                goodsdetail.setNum(1);
                mStockGoodList.add(goodsdetail);
                bundle.putSerializable("good", (Serializable) mStockGoodList);
                intent.putExtra("good", bundle);
                startActivity(intent);
            }
        });
    }


    private void updataView() {
        goodsDetailSl.smoothScrollTo(0, 0);
        tvname.setText(goodsdetail.getPM_Name());
        tvtype.setText(goodsdetail.getPT_Name());
        tvcode.setText(goodsdetail.getPM_Code());
        tvsimcode.setText(goodsdetail.getPM_SimpleCode());
        if (goodsdetail.getPM_Metering() != null && !"".equals(goodsdetail.getPM_Metering())) {
            tvmetering.setText("/" + goodsdetail.getPM_Metering());
        }
        if (goodsdetail.getPM_Modle() != null && !goodsdetail.getPM_Modle().equals("null")) {
            tvmodel.setText(goodsdetail.getPM_Modle());
        }

        tvbrand.setText(goodsdetail.getPM_Brand());
        if (goodsdetail.getPM_IsService() == 1) {
            mTvType.setText("服务商品");
            tvunitprice.setText("¥ " + goodsdetail.getPM_UnitPrice());
//            rlBottom.setVisibility(View.GONE);
        } else if (goodsdetail.getPM_IsService() == 2) {
            mTvType.setText("礼品");
            tvunitprice.setText(goodsdetail.getPM_UnitPrice() + " 分");
//            rlBottom.setVisibility(View.VISIBLE);
        } else {
            mTvType.setText("普通商品");
            tvunitprice.setText("¥ " + goodsdetail.getPM_UnitPrice());
//            rlBottom.setVisibility(View.VISIBLE);
        }
        if (goodsdetail.getPM_BigImg() != null) {
            if (goodsdetail.getPM_BigImg().contains("http")) {
                Glide.with(GoodsDetailActivity.this).load(goodsdetail.getPM_BigImg()).into(mIvGoodsPicture);
            } else {
                Glide.with(GoodsDetailActivity.this).load(HttpAPI.API().MAIN_DOMAIN + goodsdetail.
                        getPM_BigImg()).into(mIvGoodsPicture);
            }
        } else {
            mIvGoodsPicture.setImageResource(R.drawable.defalut_goods);
        }
        if (goodsdetail.getPM_IsDiscount() == 0) {
            tvDisType.setText("关闭");
        } else {
            tvDisType.setText("开启");
        }
        if (goodsdetail.getPM_IsPoint() == 1) {
            tvPointType.setText("按会员等级积分");
        }
        if (goodsdetail.getPM_IsPoint() == 2) {
            tvPointType.setText("按商品固定积分");
        }
        if (goodsdetail.getPM_IsPoint() == 3) {
            tvPointType.setText("不积分");
        }
        tvFixedPoint.setText(goodsdetail.getPM_FixedIntegralValue() + "");
        tvSpecialDis.setText(goodsdetail.getPM_SpecialOfferValue() + "");
        tvLowerDis.setText(goodsdetail.getPM_MinDisCountValue() + "");
        tvStork.setText(goodsdetail.getPM_StockPoliceValue() + "");
        tvDefaultPrice.setText("" + goodsdetail.getPM_PurchasePrice());
        if (goodsdetail.getPM_MemPrice() == null) {
            tvMemberPrice.setText("");
        } else {
            tvMemberPrice.setText("" + goodsdetail.getPM_MemPrice());
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                GoodsDetailActivity.this.finish();

                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showPopWindow(View archon) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_ysl_edit_vip_level, null);
        RelativeLayout mEdit = view.findViewById(R.id.r_edit);
        RelativeLayout mDelet = view.findViewById(R.id.r_delet);
        mPopupWindow.setContentView(view);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        mPopupWindow.setWidth(width / 2);
        mPopupWindow.setHeight(height / 5);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAsDropDown(archon);
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YSLUtils.isOpenActivon("编辑商品")) {
                    Intent intent = new Intent(GoodsDetailActivity.this, GoodsModificationActivity.class);
                    intent.putExtra("goodsdetail", goodsdetail);
                    intent.putExtra("extra_many", getIntent().getStringExtra("extra_many"));
                    startActivity(intent);
                    mPopupWindow.dismiss();
                } else {
                    CustomToast.makeText(GoodsDetailActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (YSLUtils.isOpenActivon("删除商品")) {
                    showdeleteDialog();
                    mPopupWindow.dismiss();
                } else {
                    CustomToast.makeText(GoodsDetailActivity.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {

        tvname = (TextView) findViewById(R.id.tv_shopnamegd_activity);
        tvtype = (TextView) findViewById(R.id.tv_shoptypegd_activity);
        tvcode = (TextView) findViewById(R.id.tv_shopcodegd_activity);
        tvsimcode = (TextView) findViewById(R.id.tv_shopsimcodegd_activity);
        tvmetering = (TextView) findViewById(R.id.tv_good_detail_unit);
        tvmodel = (TextView) findViewById(R.id.tv_shopmodelgd_activity);
        tvunitprice = (TextView) findViewById(R.id.tv_good_detail_price);
        tvbrand = (TextView) findViewById(R.id.tv_shopbrandgd_activity);
        tv_back_activity = (TextView) findViewById(R.id.tv_back_activity);
        iv_more_goodsdetailactivity = (ImageView) findViewById(R.id.iv_more_goodsdetailactivity);
        mTvType = (TextView) findViewById(R.id.tv_goods_detail_type);
        mIvGoodsPicture = (ShapedImageView) findViewById(R.id.iv_goods_detail_picture);

        tvDisType = (TextView) findViewById(R.id.tv_good_detail_dis_switch);
        tvPointType = (TextView) findViewById(R.id.tv_good_detail_point_type);
        tvFixedPoint = (TextView) findViewById(R.id.tv_good_detail_point);
        tvSpecialDis = (TextView) findViewById(R.id.tv_good_detail_specl);
        tvLowerDis = (TextView) findViewById(R.id.tv_good_detail_lower);
        tvStork = (TextView) findViewById(R.id.tv_good_detail_strock);
        tvMemberPrice = (TextView) findViewById(R.id.tv_good_detail_member_price);
        tvDefaultPrice = (TextView) findViewById(R.id.tv_good_detail_default_price);
        recyclerView = findViewById(R.id.lv_add_costomfiled);
    }

    private void initData() {
        changeHandler = new ChangeHandler();
        Intent intent = getIntent();
        goodsdetail = (GoodsCheckResponseByType.DataBean.DataListBean) intent.getSerializableExtra("Goods");
        goodsfields = (List<GoodsFiledsBean.DataBean>) CacheData.restoreObject("goodsfields");
//        if (goodsfields == null) {
//            getgoodsfields();//获取自定义属性
//        } else {
//            initAdapter();
//        }
        getgoodsfields();

        mPopupMenu = new MyMenuPopWindow(this, "删除商品", "修改商品", null);
    }

    private void initAdapter() {

        for (int i = 0; i < goodsfields.size(); i++) {
            if (goodsdetail.getCustomFields() != null && goodsdetail.getCustomFields().size() > 0) {
                for (int j = 0; j < goodsdetail.getCustomFields().size(); j++) {
                    if (goodsfields.get(i).getCF_FieldName().equals(goodsdetail.getCustomFields().get(j).getCF_FieldName())) {
                        goodsfields.get(i).setM_ItemsValue(goodsdetail.getCustomFields().get(j).getCF_Value());
                    }
                }
            }

        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mgoodsfieldsAdapter = new addGoodsfieldsAdapter(GoodsDetailActivity.this, goodsfields, changeHandler, 2);
        mgoodsfieldsAdapter.setImageClickEvent(this);
        mgoodsfieldsAdapter.setHasStableIds(true);
        recyclerView.setAdapter(mgoodsfieldsAdapter);
    }


    /**
     * 弹出窗口用于删除和修改产品
     */
    private void showPopupMenu(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        mPopupMenu.showAsDropDown(GoodsDetailActivity.this.findViewById(R.id.rl_goods_detail_title),
                location[0] - mPopupMenu.getWidth() - 250, 0);
    }

    /**
     * 展示删除会员窗口
     */
    private void showdeleteDialog() {

        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("删除商品")
                .setContentText("您确定要删除此商品吗？")
                .setConfirmText("确定")
                .setCancelText("取消")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        deleteGoods();
                    }
                })
                .show();

    }

    /**
     * 查询会员等级
     */
    private void getgoodsfields() {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("PM_GID", goodsdetail.getGID());
        client.post(MyApplication.BASE_URL + "CustomFields/GetCustomFields", params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Li("==========查询会员等级=============== random:" + responseString);
                GoodsFiledsBean reportbean = CommonFun.JsonToObj(responseString, GoodsFiledsBean.class);

                goodsfields = reportbean.getData();
                CacheData.saveObject("goodsfields", goodsfields);//缓存自定义属性到本地

                initAdapter();


            }

            @Override
            public void onFailure(String msg) {
            }
        });

    }

    private void deleteGoods() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("GID", goodsdetail.getGID());
        client.post(HttpAPI.API().DELETEGOODS, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resurt = null;
                    try {
                        resurt = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        Adduserbean adduserbean = CommonFun.JsonToObj(resurt, Adduserbean.class);
                        boolean isture = adduserbean.isSuccess();
                        if (isture) {
                            //                            CustomToast.makeText(GoodsDetailActivity.this, "删除商品成功", Toast.LENGTH_SHORT).show();
                            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(GoodsDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                            sweetAlertDialog.setTitleText("删除商品成功！");
                            sweetAlertDialog.setConfirmText("确定");
                            sweetAlertDialog.setCancelable(false);
                            sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    ActivityManager.getInstance().exit();
//                                    GoodsDetailActivity.this.finish();
                                    startActivity(new Intent(GoodsDetailActivity.this, GoodsManagerActivity.class));
                                }
                            });
                            sweetAlertDialog.show();
                        } else {
                            new SweetAlertDialog(GoodsDetailActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(adduserbean.getMsg() + "!")
                                    .setConfirmText("了解")
                                    .show();
//                            GoodsDetailActivity.this.finish();
//                            startActivity(new Intent(GoodsDetailActivity.this, GoodsManagerActivity.class));
//                            CustomToast.makeText(GoodsDetailActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }


    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.tv_menu_pop_first:
                showdeleteDialog();
                mPopupMenu.dismiss();
                break;
            case R.id.tv_menu_pop_second:
                Intent intent = new Intent(GoodsDetailActivity.this, GoodsModificationActivity.class);
                intent.putExtra("goodsdetail", goodsdetail);
                startActivity(intent);
                mPopupMenu.dismiss();
                break;
            default:
                mPopupMenu.dismiss();
                break;
        }
    }

    public class ChangeHandler extends Handler {
        /**
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    goodsfields = (List<GoodsFiledsBean.DataBean>) msg.obj;

                    break;
                case 2:


                    break;
            }
        }
    }

    @Override
    public void OnImageClickEvent(int position, boolean isEmpty) {
        if (isEmpty) {
            new ImageCustomPopup(this, teSave, goodsfields.get(position).getCF_Value());
        }
    }
}
