package com.zhiluo.android.yunpu.statistics.order.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.print.util.GetPrintSet;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.order.bean.GoodsOrderReportBean;
import com.zhiluo.android.yunpu.statistics.order.bean.GoodsOrderRetureBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ZPH on 2019-04-08.
 * 单品退货
 */

public class GoodsRetureActivity extends Activity {

    @Bind(R.id.tv_no_confirm_back)
    TextView tvNoConfirmBack;
    @Bind(R.id.tv_no_confirm_title)
    TextView tvNoConfirmTitle;
    @Bind(R.id.tv_reture_num)
    EditText tvRetureNum;
    @Bind(R.id.r_layout_num)
    RelativeLayout rLayoutNum;
    @Bind(R.id.tv_reture_money)
    EditText tvRetureMoney;
    @Bind(R.id.r_layout_money)
    RelativeLayout rLayoutMoney;
    @Bind(R.id.et_reture_integral)
    EditText tvRetureReceivable;
    @Bind(R.id.tv_reture_order_date)
    TextView tvRetureOrderDate;
    @Bind(R.id.iv_reture_order_date)
    ImageView ivRetureOrderDate;
    @Bind(R.id.lr_order_time)
    LinearLayout lrOrderTime;
    @Bind(R.id.tv_em)
    TextView tvEm;
    @Bind(R.id.tv_reture_means)
    TextView tvRetureMeans;
    @Bind(R.id.rl_reture_means)
    RelativeLayout rlRetureMeans;
    @Bind(R.id.et_constom_pay_detail)
    EditText etConstomPayDetail;
    @Bind(R.id.cb_entry)
    CheckBox cbEntry;
    @Bind(R.id.cb_rebate)
    CheckBox cbRebate;
    @Bind(R.id.cb_print)
    CheckBox cbPrint;
    @Bind(R.id.btn_reture_submit)
    TextView btnRetureSubmit;
    @Bind(R.id.rl_reture_integral)
    RelativeLayout rlRetureIntegral;

    private String type;
    private GoodsOrderReportBean.DataBean.DataListBean mBean;
    private GoodsOrderReportBean.DataBean.DataListBean.ViewGoodsDetailBean viewBean;
    /**
     * 退款方式默认为空,让用户自已选择 20191021 cy
     */
//    private String cogid, VIP_Card, WayCode = "XJZF";
    private String cogid, VIP_Card, WayCode = "";
    /********************************* 20191021 cy */
    private List<String> mlist = new ArrayList<>();
    private int ReturnType;
    private Dialog chooseDialog;
    private SweetAlertDialog mSweetAlertDialog;
    private IntentHandler intentHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_reture);
        ButterKnife.bind(this);
        initView();
        initData();
        initLisenter();

    }

    private void initView() {
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getStringExtra("TYPE");
            if (type != null && type.equals("single")) {
                rLayoutNum.setVisibility(View.VISIBLE);
                cbRebate.setVisibility(View.GONE);
                rLayoutMoney.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
                tvRetureMoney.setFocusable(true);
                tvRetureMoney.setFocusableInTouchMode(true);
                ReturnType = 0;
                viewBean = (GoodsOrderReportBean.DataBean.DataListBean.ViewGoodsDetailBean) intent.getSerializableExtra("data");
                tvNoConfirmTitle.setText("单品退货");
                if (viewBean != null) {
                    cogid = viewBean.getCO_GID();
                    VIP_Card = intent.getStringExtra("VIP_Card");
                    tvRetureNum.setText(viewBean.getPM_Number() + "");
                    tvRetureMoney.setText(viewBean.getDiscountPrice() + "");
                    tvRetureReceivable.setText(viewBean.getGOD_Integral() + "");
                    if (viewBean.getGOD_Integral() > 0) {
                        rlRetureIntegral.setBackground(new ColorDrawable(getResources().getColor(R.color.white)));
                        tvRetureReceivable.setEnabled(true);
                    } else {
                        rlRetureIntegral.setBackground(new ColorDrawable(getResources().getColor(R.color.lightgray)));
                        tvRetureReceivable.setEnabled(false);
                    }
                }

            } else if (type.equals("all")) {
                tvNoConfirmTitle.setText("整单退货");
                rLayoutMoney.setBackground(new ColorDrawable(getResources().getColor(R.color.lightgray)));
                tvRetureMoney.setFocusable(false);
                tvRetureMoney.setFocusableInTouchMode(false);
                rLayoutNum.setVisibility(View.GONE);
                cbRebate.setVisibility(View.VISIBLE);
                ReturnType = 1;
                mBean = (GoodsOrderReportBean.DataBean.DataListBean) intent.getSerializableExtra("mBean");
                if (mBean != null) {
                    cogid = mBean.getGID();
                    VIP_Card = mBean.getVIP_Card();
                    double reture = mBean.getCO_TotalPrice();
                    if (mBean.getCO_ConsumeWay().contains("抹零")) {
                        String consWay = mBean.getCO_ConsumeWay();
                        int index = consWay.indexOf("零") + 2;
                        String lastWay = consWay.substring(index);
                        double moling = Double.parseDouble(lastWay.substring(0, lastWay.indexOf(")")));
                        reture = reture - moling;
                    }

                    tvRetureMoney.setText(reture + "");
                    tvRetureReceivable.setText(mBean.getCO_Integral() + "");
                }
            }
            tvRetureOrderDate.setText(DateTimeUtil.getReallyTimeNow());
        }

        if (VIP_Card == null || VIP_Card.equals("00000")) {
            /**退款方式默认为空,让用户自已选择 20191021 cy */
//            tvRetureMeans.setText("现金退款");
//            WayCode = "XJZF";
            tvRetureMeans.setHint("请选择退款方式");
            /********************************* 20191021 cy */
        } else {
            /**退款方式默认为空,让用户自已选择 20191021 cy */
//            tvRetureMeans.setText("余额退款");
//            WayCode = "YEZF";
            tvRetureMeans.setHint("请选择退款方式");
            /********************************* 20191021 cy */
            mlist.add("余额退款");
        }

        if (MyApplication.PRINT_IS_OPEN) {
            cbPrint.setChecked(true);
        } else {
            cbPrint.setChecked(false);
        }

    }

    private void initData() {
        intentHandler = new IntentHandler();
        mlist.add("微信退款");
        mlist.add("支付宝退款");
        mlist.add("现金退款");


    }

    private void initLisenter() {
        tvNoConfirmBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnRetureSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**退款方式默认为空,让用户自已选择 20191021 cy */
                if (WayCode == null || WayCode.equals("")) {
                    CustomToast.makeText(GoodsRetureActivity.this, "请选择退款方式", Toast.LENGTH_SHORT).show();
                    return;
                }
                /********************************* 20191021 cy */
                postReture();
            }
        });
        tvRetureMeans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRulesDialog(mlist, tvRetureMeans);
            }
        });

    }

    /**
     * 选择计次规则
     **/
    private void showRulesDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        if (itemValue.equals("余额退款")) {
                            WayCode = "YEZF";
                        } else if (itemValue.equals("现金退款")) {
                            WayCode = "XJZF";
                        } else if (itemValue.equals("微信退款")) {
                            WayCode = "WX_JZ";
                        } else if (itemValue.equals("支付宝退款")) {
                            WayCode = "ZFB_JZ";
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();

    }


    private void postReture() {
        RequestParams params = new RequestParams();
        //消费订单GID
        params.put("CO_GID", cogid);
        //会员卡号
        params.put("VIP_Card", VIP_Card);
        //退款类型:0单品 1整单
        params.put("ReturnType", ReturnType);
        //单品退货
        if (type.equals("single")) {
            //订单明细GID:单品退货时必传
            params.put("RD_GID", viewBean.getGID());
            //退货数量:单品退货时必传
            params.put("RO_Number", tvRetureNum.getText().toString());
        }
        //退款金额
        params.put("RO_MonetaryMoney", tvRetureMoney.getText().toString());
        //扣除积分
        params.put("RO_Integral", tvRetureReceivable.getText().toString());
        //退款方式
        params.put("ConsumeWay", tvRetureMeans.getText().toString());
        //退款方式编码
        params.put("WayCode", WayCode);
        //是否退货入库
        params.put("IsStockIn", cbEntry.isChecked());
        //是否撤销提成
        params.put("IsCancelCommission", cbRebate.isChecked());
        //备注
        params.put("RO_Remark", etConstomPayDetail.getText().toString());
        //时间
        params.put("RO_UpdateTime", tvRetureOrderDate.getText().toString());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final GoodsOrderRetureBean bean = CommonFun.JsonToObj(responseString, GoodsOrderRetureBean.class);
                mSweetAlertDialog = new SweetAlertDialog(GoodsRetureActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("退货成功");
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mSweetAlertDialog.dismiss();
                        if (cbPrint.isChecked()) {
                            if (MyApplication.mReTureOrder.isEmpty()) {
                                GetPrintSet.getPrintParamSet();
                            }
                            new HttpGetPrintContents(GoodsRetureActivity.this, MyApplication.SPTH_PRINT_TIMES, bean.getData().getGID()).SPTH(intentHandler);
                        }
                        ActivityManager.getInstance().exit();

                        Intent intent = new Intent(GoodsRetureActivity.this, OrderActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(GoodsRetureActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(GoodsRetureActivity.this, HttpAPI.API().RETURE_ORDER, params, callBack);
    }


    public class IntentHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

                    finish();
                    break;


            }
        }
    }
}
