package com.zhiluo.android.yunpu.gift.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.GradeDetailActivity;
import com.zhiluo.android.yunpu.ui.activity.YSLVipLevelEdit;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 礼品详情
 */
public class GiftsDetailActivity extends BaseActivity implements MyMenuPopWindow.OnItemClickListener {
    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.iv_gift_detail_more)
    ImageView ivMore;
    @Bind(R.id.iv_gift_detail_picture)
    ShapedImageView ivPicture;
    @Bind(R.id.tv_gift_detail_name)
    TextView tvName;
    @Bind(R.id.tv_gift_detail_price)
    TextView tvPrice;
    @Bind(R.id.tv_gift_detail_code)
    TextView tvCode;
    @Bind(R.id.tv_gift_detail_reference_price)
    TextView tvReferencePrice;
    @Bind(R.id.tv_gift_detail_type)
    TextView tvType;
    @Bind(R.id.tv_gift_detail_simple_code)
    TextView tvSimpleCode;
    @Bind(R.id.tv_gift_detail_model)
    TextView tvModel;
    @Bind(R.id.tv_gift_detail_brand)
    TextView tvBrand;
    @Bind(R.id.tv_gift_detail_sync)
    TextView tvSync;
    @Bind(R.id.tv_gift_detail_stock_notice_num)
    TextView tvStockNoticeNum;
    @Bind(R.id.tv_gift_detail_unit)
    TextView tvUnit;

    private MyMenuPopWindow mPopupMenu;
    private GoodsCheckResponseByType.DataBean.DataListBean mGift;//礼品信息
    private SweetAlertDialog mSweetAlertDialog;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifts_detail);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    private void init() {
        Intent intent = getIntent();
        mGift = (GoodsCheckResponseByType.DataBean.DataListBean) intent.getSerializableExtra("giftdetail");
        mPopupMenu = new MyMenuPopWindow(this, "删除礼品", "修改礼品", null);
        mPopupWindow = new PopupWindow(this);
        if (mGift.getPM_BigImg() != null) {
            if (mGift.getPM_BigImg().contains("http")) {
                Glide.with(GiftsDetailActivity.this).load(mGift.getPM_BigImg()).into(ivPicture);
            } else {
                Glide.with(GiftsDetailActivity.this).load(HttpAPI.API().MAIN_DOMAIN +
                        mGift.getPM_BigImg()).into(ivPicture);
            }
        } else {
            ivPicture.setImageResource(R.drawable.defalut_goods);
        }
        tvName.setText(mGift.getPM_Name());
        tvPrice.setText(Decima2KeeplUtil.stringToDecimal(mGift.getPM_UnitPrice() + ""));
        tvCode.setText(mGift.getPM_Code() == null ? "" : mGift.getPM_Code());
        tvReferencePrice.setText("" + mGift.getPM_PurchasePrice());
        tvType.setText(mGift.getPT_Name());
        tvSimpleCode.setText(mGift.getPM_SimpleCode() == null ? "" : mGift.getPM_SimpleCode());
        tvModel.setText(mGift.getPM_Modle() == null ? "" : mGift.getPM_Modle());
        tvBrand.setText(mGift.getPM_Brand() == null ? "" : mGift.getPM_Brand());
        if (mGift.getPM_SynType() == 1) {
            tvSync.setText("同步");
        } else {
            tvSync.setText("不同步");
        }
        tvStockNoticeNum.setText("" + mGift.getPM_StockPoliceValue());
        tvUnit.setText(mGift.getPM_Metering() == null ? "" : mGift.getPM_Metering());
    }

    private void setListener() {
        mPopupMenu.setOnItemClickListener(this);
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // showPopupWindow(v);
                showPopWindow(v);
            }
        });

    }

    /**
     * @param archon ,
     *               编辑弹窗
     */
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
                //编辑
                Intent intent = new Intent(GiftsDetailActivity.this, GiftsModificationActivity.class);
                intent.putExtra("giftdetail", mGift);
                startActivity(intent);
            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                toastDeleteGift();
            }
        });

    }

    /**
     * 显示PopMenu
     */
    private void showPopupWindow(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        mPopupMenu.showAsDropDown(GiftsDetailActivity.this.findViewById(R.id.rl_gift_detail_title),
                location[0] - mPopupMenu.getWidth() - 250, 0);
    }

    /**
     * 删除礼品提示框
     */
    private void toastDeleteGift() {
        mSweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText("删除礼品");
        mSweetAlertDialog.setContentText("确定要删除该礼品？");
        mSweetAlertDialog.setConfirmText("确定");
        mSweetAlertDialog.setCancelText("取消");
        mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                deleteGift();
                mSweetAlertDialog.dismiss();
            }
        });
        mSweetAlertDialog.show();
    }

    /**
     * 删除礼品
     */
    private void deleteGift() {
        RequestParams params = new RequestParams();
        params.put("GID", mGift.getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(GiftsDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("删除成功！");
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setCancelable(false);
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        mSweetAlertDialog.dismiss();
                        finish();
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                mSweetAlertDialog = new SweetAlertDialog(GiftsDetailActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("提示");
                mSweetAlertDialog.setContentText(msg + "!");
                mSweetAlertDialog.setConfirmText("了解");
                mSweetAlertDialog.show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, HttpAPI.API().DELETEGOODS, params, callBack);
    }


    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.tv_menu_pop_first:
                toastDeleteGift();
                mPopupMenu.dismiss();
                break;
            case R.id.tv_menu_pop_second:
                Intent intent = new Intent(GiftsDetailActivity.this, GiftsModificationActivity.class);
                intent.putExtra("giftdetail", mGift);
                startActivity(intent);
                mPopupMenu.dismiss();
                break;
            default:
                mPopupMenu.dismiss();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
            mSweetAlertDialog = null;
        }
    }
}
