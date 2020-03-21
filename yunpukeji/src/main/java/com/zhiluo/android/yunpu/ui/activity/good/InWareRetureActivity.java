package com.zhiluo.android.yunpu.ui.activity.good;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.InWareRetureAdapter;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InReCoderDetailBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ZPH on 2019-04-19.
 */

public class InWareRetureActivity extends BaseActivity implements InWareRetureAdapter.IItemClick {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_titel)
    TextView tvTitel;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.tv_oder_number)
    TextView tvOderNumber;
    @Bind(R.id.tv_supplier_name)
    TextView tvSupplierName;
    @Bind(R.id.tv_pay_type)
    TextView tvPayType;
    @Bind(R.id.tv_total_price)
    EditText tvTotalPrice;
    @Bind(R.id.tv_comfirm)
    TextView tvComfirm;
    @Bind(R.id.lv_inware_reture)
    RecyclerView lvInwareReture;

    private String GID;
    private InWareRetureAdapter madapter;
    private double mCount = 0;//合计金额
    private InReCoderDetailBean inReCoderDetailBean;
    private SweetAlertDialog mSweetAlertDialog;
    private String mOrderNo;
    private Dialog chooseDialog;
    private String mPayTypeCode = "XJZF", mPayTypeName = "现金支付";//支付类型编码，支付类型名字
    private List<String> mPayWayList = new ArrayList<>();
    private ChangeHandler changeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inwarereture_activity);
        ButterKnife.bind(this);
        initData();
        Lisenter();

    }

    private void Lisenter() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvPayType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPayWayDialog(mPayWayList, tvPayType);
            }
        });
        tvComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (judge()) {
                    StockOut();
                }
            }
        });


    }

    private boolean judge() {

        return true;
    }

    private void initData() {
        changeHandler = new ChangeHandler();
        mOrderNo = CreateOrder.createOrder("CT");
        tvOderNumber.setText(mOrderNo);
        if (getIntent() != null) {
            GID = getIntent().getStringExtra("GID");
        }

        if (GID != null) {
            gettockInDetailDataList();
        }
        mPayWayList.add("现金支付");
        mPayWayList.add("银联支付");
        mPayWayList.add("微信记账");
        mPayWayList.add("支付宝记账");
        tvPayType.setText(mPayTypeName);

    }

    /**
     * @param mlist     ,
     * @param mTextView ,
     *                  支付方式选择
     */
    private void showPayWayDialog(List<String> mlist, final TextView mTextView) {
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
                        if (position == 0) {
                            mPayTypeCode = "XJZF";
                            mPayTypeName = "现金支付";
                        }
                        if (position == 1) {
                            mPayTypeCode = "YLZF";
                            mPayTypeName = "银联支付";
                        }
                        if (position == 2) {
                            mPayTypeCode = "WX_JZ";
                            mPayTypeName = "微信记账";
                        }
                        if (position == 3) {
                            mPayTypeCode = "ZFB_JZ";
                            mPayTypeName = "支付宝记账";
                        }
                    }

                    @Override
                    public void onCancel() {
                    }
                }).create();

        chooseDialog.show();
    }


    private void gettockInDetailDataList() {
        RequestParams params = new RequestParams();
        params.put("PageIndex", 1);
        params.put("PageSize", 99999);
        params.put("GID", GID);


        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                inReCoderDetailBean = CommonFun.JsonToObj(responseString, InReCoderDetailBean.class);
                setDatas();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(InWareRetureActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        };

        HttpHelper.post(this, HttpAPI.API().OUT_STOCK_DETAIL_LIST, params, callBack);


    }

    private void StockOut() {

        RequestParams params = new RequestParams();
        params.put("OutCome", Decima2KeeplUtil.stringToDecimal(tvTotalPrice.getText().toString()));
        params.put("PayType", mPayTypeCode);
        params.put("OutType", 2);
        params.put("SO_TrackingNo", mOrderNo);
        params.put("InGID", GID);
        for (int i = 0; i < inReCoderDetailBean.getData().size(); i++) {
            params.put("ProductList[" + i + "][PM_GID]", inReCoderDetailBean.getData().get(i).getPM_GID());
            params.put("ProductList[" + i + "][Number]", inReCoderDetailBean.getData().get(i).getNowAccount());
            params.put("ProductList[" + i + "][Price]", inReCoderDetailBean.getData().get(i).getPM_UnitPrice());
            params.put("ProductList[" + i + "][Supplier]", "");
        }

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(InWareRetureActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("退货成功")
                        .setConfirmText("确定");
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mSweetAlertDialog.dismiss();
                        ActivityManager.getInstance().exit();
                        Intent intent = new Intent(InWareRetureActivity.this,InRecoderActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                mSweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(InWareRetureActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        };

        HttpHelper.post(this, HttpAPI.API().OUT_STOCK, params, callBack);
    }

    private void setDatas() {

        mCount = 0;
        for (int i = 0; i < inReCoderDetailBean.getData().size(); i++) {
            mCount += inReCoderDetailBean.getData().get(i).getSID_TotalPrice();
            inReCoderDetailBean.getData().get(i).setNowAccount(inReCoderDetailBean.getData().get(i).getSID_Amount());
        }
        tvSupplierName.setText(Decima2KeeplUtil.stringToDecimal(mCount + ""));
        tvTotalPrice.setText(Decima2KeeplUtil.stringToDecimal(mCount + ""));
        madapter = new InWareRetureAdapter(InWareRetureActivity.this, inReCoderDetailBean, InWareRetureActivity.this, changeHandler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setSmoothScrollbarEnabled(false);
        lvInwareReture.setLayoutManager(manager);
        madapter.setHasStableIds(true);
        lvInwareReture.setAdapter(madapter);

    }

    private void upPrice() {
        mCount = 0;
        for (int i = 0; i < inReCoderDetailBean.getData().size(); i++) {
            mCount += DoubleMathUtil.mul(inReCoderDetailBean.getData().get(i).getPM_UnitPrice(), inReCoderDetailBean.getData().get(i).getNowAccount());
        }

        tvTotalPrice.setText(Decima2KeeplUtil.stringToDecimal(mCount + ""));
        tvTotalPrice.setSelection(tvTotalPrice.getText().toString().length());
    }


    @Override
    public void itemClick(View view) {

        int position = (int) view.getTag();
        double num;
        num = inReCoderDetailBean.getData().get(position).getNowAccount();
        switch (view.getId()) {
            //移除
            case R.id.iv_reduce:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    if (num >= 1) {
                        num--;
                    } else {
                        num = 0;
                    }
                    inReCoderDetailBean.getData().get(position).setNowAccount(Double.parseDouble(Decima2KeeplUtil.stringToDecimal(num + "")));
                    upPrice();
                }
                break;
            //增加
            case R.id.iv_add:
                if (YSLUtils.isFastClick()) {//防止卡顿多次点击
                    num++;
                    if (num <= inReCoderDetailBean.getData().get(position).getSID_Amount()) {
                        inReCoderDetailBean.getData().get(position).setNowAccount(Double.parseDouble(Decima2KeeplUtil.stringToDecimal(num + "")));
                        upPrice();
                    } else {
                        CustomToast.makeText(InWareRetureActivity.this, "退货数量不能大于采购数量", Toast.LENGTH_SHORT).show();
                    }
                }

                break;

        }
        if (madapter != null) {
            madapter.notifyDataSetChanged();
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
                    try {
                        inReCoderDetailBean = (InReCoderDetailBean) msg.obj;
                        upPrice();

                    } catch (Exception e) {

                    }

                    break;
            }
        }
    }


}
