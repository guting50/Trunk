package com.zhiluo.android.yunpu.ui.activity.good;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.print.util.GetPrintSet;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.order.activity.GoodsOrderDetailActivity;
import com.zhiluo.android.yunpu.statistics.order.activity.GoodsRetureActivity;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.InRecoderDetailAdapter;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InReCoderDetailBean;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InRecoderBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

/**
 * Created by ${YSL} on 2018-05-18.
 */

public class InWareItemDetailActivity extends AppCompatActivity {
    private TextView tvSingPrices;//单价
    private TextView tvInCounts;//入库数量
    private TextView tvTotalPrice;//总金额
    private TextView tvSupplier;//供应商
    private TextView tvPayWay;//支付方式
    private TextView tvInTime;//入库时间
    private TextView tvInType;//入库类型
    private TextView tvOderNumber;//单号
    private TextView tvStatus;//状态
    private TextView tvOperator;//操作员
    private TextView tvRemakes;//备注
    private ImageView igMore;
    private TextView tvBack;
    private RecyclerView recyclerView;
    private  List<InRecoderBean.DataBean.DataListBean> inRecoderBean;
    private Bundle bundle;
    private int position;
    private InRecoderBean.DataBean.DataListBean dataListBean;
    private InRecoderDetailAdapter adapter;
    private double mCount = 0;//入库总数量
    private PopupWindow popupWindow;
    private InReCoderDetailBean inReCoderDetailBean;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_inware_itemdetail);
        initViews();
        initDatas();
    }

    private void initDatas() {
        bundle = getIntent().getBundleExtra("bundle");
        inRecoderBean = (List<InRecoderBean.DataBean.DataListBean>) bundle.getSerializable("Bean");
        position = getIntent().getIntExtra("position", 0);
        inRecoderBean.get(position).getGID();
        //
        tvOderNumber.setText(inRecoderBean.get(position).getSI_TrackingNo());
        tvSupplier.setText(inRecoderBean.get(position).getSL_Name());
        tvPayWay.setText(inRecoderBean.get(position).getSI_PayType() ==null ?"0.00":inRecoderBean.get(position).getSI_PayType());
        tvInTime.setText(inRecoderBean.get(position).getSI_CreateTime());
        if (inRecoderBean.get(position).getSI_InType().equals("1")){//1采购进货 2调拨 3退货
            tvInType.setText("采购进货");
        }else if (inRecoderBean.get(position).getSI_InType().equals("2")){
            tvInType.setText("库存调拨");
        }else if (inRecoderBean.get(position).getSI_InType().equals("3")){
            tvInType.setText("商品退货");
        }
        if (inRecoderBean.get(position).getSI_ISReturn() == 0){//0正常 1 已退货
            tvStatus.setText("正常");
        }else if (inRecoderBean.get(position).getSI_ISReturn() == 1){
            tvStatus.setText("已退货");
        }
        tvOperator.setText(inRecoderBean.get(position).getSI_Creator());
        tvRemakes.setText(inRecoderBean.get(position).getSI_Remark());
        tvSingPrices.setText(inRecoderBean.get(position).getSI_TotalPrice() + "");
        tvTotalPrice.setText(inRecoderBean.get(position).getSI_TotalPrice() + "");

        obtainDatas(inRecoderBean.get(position).getGID());
    }



    private void obtainDatas(String GID) {
        RequestParams params = new RequestParams();
        params.put("GID", GID);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                inReCoderDetailBean = CommonFun.JsonToObj(responseString, InReCoderDetailBean.class);
                setDatas(inReCoderDetailBean);
            }

            @Override
            public void onFailure(String msg) {

            }
        };
        HttpHelper.post(this, HttpAPI.API().OUT_STOCK_DETAIL_LIST, params, callBack);
    }

    private void setDatas(InReCoderDetailBean inReCoderDetailBean) {
        adapter = new InRecoderDetailAdapter(InWareItemDetailActivity.this, inReCoderDetailBean);
        LinearLayoutManager manager = new LinearLayoutManager(InWareItemDetailActivity.this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < inReCoderDetailBean.getData().size(); i++) {
            mCount += inReCoderDetailBean.getData().get(i).getSID_Amount();
        }
        tvInCounts.setText(mCount + "");
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
        RelativeLayout allreture = view.findViewById(R.id.r_all_reture);
        RelativeLayout print = view.findViewById(R.id.r_print);
        TextView tvreture = view.findViewById(R.id.tv_reture);
        tvreture.setText("退货");
        if ((inReCoderDetailBean!=null &&inReCoderDetailBean.getData().size()>0)&&!inRecoderBean.get(position).getSI_InType().equals("3")){
            allreture.setVisibility(View.VISIBLE);
            popupWindow.setHeight(height / 5);
        }else {
            allreture.setVisibility(View.GONE);
            popupWindow.setHeight(height / 11);
        }
        popupWindow.showAsDropDown(v);
        if (MyApplication.mGoodsIn.isEmpty()){
            GetPrintSet.getPrintParamSet();
        }
        allreture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (inRecoderBean.get(position).getSI_ISReturn() == 0){
                    Intent intent = new Intent(InWareItemDetailActivity.this,InWareRetureActivity.class);
                    intent.putExtra("GID",inRecoderBean.get(position).getGID());
                    startActivity(intent);
                }else {
                    CustomToast.makeText(InWareItemDetailActivity.this,"已退，无法再次退货", Toast.LENGTH_SHORT).show();
                }


            }
        });
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (MyApplication.PRINT_IS_OPEN){
                    new HttpGetPrintContents(InWareItemDetailActivity.this, MyApplication.RK_PRINT_TIMES, inRecoderBean.get(position).getGID()).RK();
                }else {
                    CustomToast.makeText(InWareItemDetailActivity.this,"打印开关未开启", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void initViews() {
        tvSingPrices = findViewById(R.id.tv_sing_price);
        tvTotalPrice = findViewById(R.id.tv_total_price);
        tvSupplier = findViewById(R.id.tv_supplier_name);
        tvInCounts = findViewById(R.id.tv_inware_counts);
        tvPayWay = findViewById(R.id.tv_pay_way);
        tvInTime = findViewById(R.id.tv_in_time);
        tvInType = findViewById(R.id.tv_in_type);
        tvOderNumber = findViewById(R.id.tv_oder_number);
        tvStatus = findViewById(R.id.tv_status);
        tvOperator = findViewById(R.id.tv_operator);
        tvRemakes = findViewById(R.id.tv_remakes);
        recyclerView = findViewById(R.id.recycler_view);
        tvBack = findViewById(R.id.tv_back);
        igMore = findViewById(R.id.iv_more);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InWareItemDetailActivity.this.finish();
            }
        });
        igMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop(v);
            }
        });
    }


}
