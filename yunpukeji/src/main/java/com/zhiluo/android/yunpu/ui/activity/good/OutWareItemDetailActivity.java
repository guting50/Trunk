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
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.print.util.GetPrintSet;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.InRecoderDetailAdapter;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.OutRecoderDetailAdapter;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InReCoderDetailBean;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InRecoderBean;
import com.zhiluo.android.yunpu.ui.activity.good.bean.OutReCoderDetailBean;
import com.zhiluo.android.yunpu.ui.activity.good.bean.OutRecoderBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.List;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-05-18.
 */

public class OutWareItemDetailActivity extends AppCompatActivity {
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
    private TextView tvBack;
    private ImageView igMore;
    private RecyclerView recyclerView;
    private List<OutRecoderBean.DataBean.DataListBean> outRecoderBean;
    private Bundle bundle;
    private int position;
    private OutRecoderDetailAdapter adapter;
    private double mCount = 0;//出库总数量
    private PopupWindow popupWindow;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outware_itemdetail);
        initViews();
        initDatas();
    }

    private void initDatas() {
        bundle = getIntent().getBundleExtra("bundle");
        outRecoderBean = (List<OutRecoderBean.DataBean.DataListBean>) bundle.getSerializable("Bean");
        position = getIntent().getIntExtra("position", 0);
        outRecoderBean.get(position).getGID();
        Li("-----" + new Gson().toJson(outRecoderBean.get(0)));
        tvOderNumber.setText(outRecoderBean.get(position).getSO_TrackingNo() + "");
        //   tvSupplier.setText(outRecoderBean.getData().getDataList().get(position).getS());
        tvPayWay.setText(outRecoderBean.get(position).getSO_PayType());
        tvInTime.setText(outRecoderBean.get(position).getSO_CreateTime());

        String outputtype = outRecoderBean.get(position).getSO_OutType();
        if (outputtype.equals("3")) {
            tvInType.setText("其它");
        } else if (outputtype.equals("2")) {
            tvInType.setText("采购退货");
        } else if (outputtype.equals("0")) {
            tvInType.setText("商品报废");
        }else if (outputtype.equals("1")){
            tvInType.setText("商品消费");
        } else {
            /***outputtype为4的时候页面显示空 20191019 cy*/
            tvInType.setText("其它");
            /******************************** 20191019 cy*/
        }
        tvOperator.setText(outRecoderBean.get(position).getSO_Creator());
        tvRemakes.setText(outRecoderBean.get(position).getSO_Remark());
        tvSingPrices.setText(outRecoderBean.get(position).getSO_TotalPrice() + "");
        tvTotalPrice.setText(outRecoderBean.get(position).getSO_TotalPrice() + "");
        tvInCounts.setText(outRecoderBean.get(position).getSO_Amount()+"");
        obtainDatas(outRecoderBean.get(position).getGID());
    }

    private void obtainDatas(String GID) {
        RequestParams params = new RequestParams();
        params.put("PageIndex",1);
        params.put("PageSize",999);
        params.put("GID", GID);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                OutReCoderDetailBean outReCoderDetailBean = CommonFun.JsonToObj(responseString, OutReCoderDetailBean.class);
                setDatas(outReCoderDetailBean);
            }

            @Override
            public void onFailure(String msg) {

            }
        };
        HttpHelper.post(this, "StockManager/GetStockOutDetailDataList", params, callBack);
    }

    private void setDatas(OutReCoderDetailBean outReCoderDetailBean) {
        adapter = new OutRecoderDetailAdapter(OutWareItemDetailActivity.this, outReCoderDetailBean);
        LinearLayoutManager manager = new LinearLayoutManager(OutWareItemDetailActivity.this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < outReCoderDetailBean.getData().getDataList().size(); i++) {
            mCount += outReCoderDetailBean.getData().getDataList().get(i).getSOD_Amount();
        }
        tvInCounts.setText(mCount + "");
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
                OutWareItemDetailActivity.this.finish();
            }
        });
        igMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop(v);
            }
        });
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
        popupWindow.setHeight(height / 10);
        popupWindow.showAsDropDown(v);
        RelativeLayout allreture = view.findViewById(R.id.r_all_reture);
        RelativeLayout print = view.findViewById(R.id.r_print);
        allreture.setVisibility(View.GONE);
        if (MyApplication.mGoodsOut.isEmpty()) {
            GetPrintSet.getPrintParamSet();
        }
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (MyApplication.PRINT_IS_OPEN) {
                    new HttpGetPrintContents(OutWareItemDetailActivity.this, MyApplication.CK_PRINT_TIMES, outRecoderBean.get(position).getGID()).CK();
                }else {
                    CustomToast.makeText(OutWareItemDetailActivity.this,"打印开关未开启", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
