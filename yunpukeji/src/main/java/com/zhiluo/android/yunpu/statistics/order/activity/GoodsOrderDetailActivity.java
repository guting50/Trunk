package com.zhiluo.android.yunpu.statistics.order.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.order.adapter.GoodsDetailREcoderAdapter;
import com.zhiluo.android.yunpu.statistics.order.bean.GoodsOrderReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;


public class GoodsOrderDetailActivity extends BaseActivity implements GoodsDetailREcoderAdapter.retureClick {
    private TextView mTvTitle, mTvOrderNum, mTvVipName, mTvVipCardNo, mTvConsumeMoney, mTvDiscountMoney, mTvBalance,
            mTvPoint, mTvPayWay, mTvConsumeTime, mTvDevice, mTvOperation, mTvRemark, mTvDetail, mTvstusta;
    private ImageView igMore;
    private GoodsOrderReportBean.DataBean.DataListBean mBean;
    private String mType;
    private RecyclerView recyclerView;
    private GoodsDetailREcoderAdapter adapter;
    private PopupWindow popupWindow;
    private boolean isYJJY = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_goods_order_detail);
        initView();
        initVariable();
        setListener();
//        getGoodDetail(mGid);
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("商品消费详情");
        mTvOrderNum = (TextView) findViewById(R.id.tv_goods_order_num);
        mTvVipName = (TextView) findViewById(R.id.tv_goods_order_name);
        mTvVipCardNo = (TextView) findViewById(R.id.tv_goods_order_card);
        mTvConsumeMoney = (TextView) findViewById(R.id.tv_goods_order_consume_money);
        mTvDiscountMoney = (TextView) findViewById(R.id.tv_goods_order_discount_money);
        mTvBalance = (TextView) findViewById(R.id.tv_goods_order_balance);
        mTvPoint = (TextView) findViewById(R.id.tv_goods_order_point);
        mTvPayWay = (TextView) findViewById(R.id.tv_goods_order_pay_way);
        mTvConsumeTime = (TextView) findViewById(R.id.tv_goods_order_time);
        mTvDevice = (TextView) findViewById(R.id.tv_goods_order_device);
        mTvOperation = (TextView) findViewById(R.id.tv_goods_order_staff);
        mTvRemark = (TextView) findViewById(R.id.tv_goods_order_remark);
        mTvDetail = (TextView) findViewById(R.id.tv_goods_order_detail);
        recyclerView = findViewById(R.id.recycler_view);
        mTvstusta = findViewById(R.id.tv_goods_order_stusta);
        igMore = findViewById(R.id.iv_more);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(manager);
    }

    private void initVariable() {
        mBean = (GoodsOrderReportBean.DataBean.DataListBean) getIntent().getSerializableExtra("goods");
        mType = getIntent().getStringExtra("type");
        if (mType != null) {
            if (mType.equals("YJJY")) {
                isYJJY = true;
                mTvTitle.setText("一键加油详情");
                igMore.setVisibility(View.GONE);

            } else {
                isYJJY = false;
                mTvTitle.setText("商品消费详情");
                igMore.setVisibility(View.VISIBLE);
            }
        }

        mTvOrderNum.setText(mBean.getCO_OrderCode() == null ? "" : mBean.getCO_OrderCode());
        mTvVipName.setText(mBean.getVIP_Name() == null ? "" : mBean.getVIP_Name());
        mTvVipCardNo.setText(mBean.getVIP_Card() == null ? "" : mBean.getVIP_Card());
        mTvConsumeMoney.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getCO_Monetary() + ""));
        mTvDiscountMoney.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getCO_TotalPrice() + ""));
        mTvBalance.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getCO_BalanceCard() + ""));
        mTvPoint.setText(Decima2KeeplUtil.stringToDecimal(mBean.getCO_Integral() + ""));
        mTvPayWay.setText(mBean.getCO_ConsumeWay() == null ? "" : mBean.getCO_ConsumeWay());
        mTvConsumeTime.setText(mBean.getCO_UpdateTime() == null ? "" : mBean.getCO_UpdateTime());

        if (mBean.getCO_Device() == 2) {
            mTvDevice.setText("安卓手机");
        } else if (mBean.getCO_Device() == 4) {
            mTvDevice.setText("IOS");
        } else {
            mTvDevice.setText("浏览器");
        }
        mTvOperation.setText(mBean.getCO_Creator() == null ? "" : mBean.getCO_Creator());
        mTvstusta.setText(mBean.getCO_Identifying() == null ? "" : mBean.getCO_Identifying());
        mTvRemark.setText(mBean.getCO_Remark() == null ? "" : mBean.getCO_Remark());
        for (int i = 0; i < mBean.getViewGoodsDetail().size(); i++) {
            if (mBean.getViewGoodsDetail().get(i).getState().equals("退货")) {
                for (int j = 0; j < mBean.getViewGoodsDetail().size(); j++) {
                    if (j != i && mBean.getViewGoodsDetail().get(i).getPM_GID().equals(mBean.getViewGoodsDetail().get(j).getPM_GID())) {
                        mBean.getViewGoodsDetail().get(j).setState("已退货");
                    }
                }
            }

        }
        setAdapter();
    }

    private void setAdapter() {

        adapter = new GoodsDetailREcoderAdapter(GoodsOrderDetailActivity.this, mBean, isYJJY, GoodsOrderDetailActivity.this);
        recyclerView.setAdapter(adapter);
    }

    private void setListener() {
        findViewById(R.id.tv_no_confirm_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        igMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop(v);
            }
        });

      /*  mTvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetailDialog();
            }
        });*/
    }

    private void showPop(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.popwindow_goods_reture, null);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(width / 2);
        popupWindow.setHeight(height / 5);
        popupWindow.showAsDropDown(v);
        RelativeLayout allreture = view.findViewById(R.id.r_all_reture);
        RelativeLayout print = view.findViewById(R.id.r_print);
        allreture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                //挂单就不显示退货
                if (mBean.getCO_Identifying() != null && (mBean.getCO_Identifying().equals("挂单") || mBean.getCO_Identifying().equals("挂账"))) {
                    CustomToast.makeText(GoodsOrderDetailActivity.this, "挂单不能退货", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(GoodsOrderDetailActivity.this, GoodsRetureActivity.class);
                    intent.putExtra("TYPE", "all");
                    intent.putExtra("mBean", mBean);
                    startActivity(intent);
                }
            }
        });
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (MyApplication.PRINT_IS_OPEN) {
                    new HttpGetPrintContents(GoodsOrderDetailActivity.this, MyApplication.SPXF_PRINT_TIMES, mBean.getGID()).SPXF();
                } else {
                    CustomToast.makeText(GoodsOrderDetailActivity.this, "打印开关未开启", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void showDetailDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_report_layout, (ViewGroup) findViewById(R.id.dialog_report_detail));
//        mDialogListView = (BaseListView) dialog.findViewById(R.id.lv_report_detail);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.create();
        alertDialog.setContentView(dialog);
        alertDialog.setView(dialog);
    }

    @Override
    public void reClick(View view) {
        int pos = (int) view.getTag();
        Intent intent = new Intent(GoodsOrderDetailActivity.this, GoodsRetureActivity.class);
        intent.putExtra("TYPE", "single");
        intent.putExtra("VIP_Card", mBean.getVIP_Card());
        intent.putExtra("data", mBean.getViewGoodsDetail().get(pos));
        startActivityForResult(intent, 2222);

    }

}
