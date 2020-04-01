package com.zhiluo.android.yunpu.statistics.account.activity;

import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.account.adapter.InflateCountsDetailAdapter;
import com.zhiluo.android.yunpu.statistics.account.adapter.RechargeServiceDetailAdapter;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeServiceDetailBean;
import com.zhiluo.android.yunpu.statistics.account.bean.RechargeTimesReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

public class TimesRechargeDetailActivity extends BaseActivity {
    private TextView mTvTitle, mTvOrderNum, mTvVipName, mTvVipCardNo, mTvConsumeMoney, mTvDiscountMoney, mTvBalance,
            mTvPayWay, mTvConsumeTime, mTvDevice, mTvOperation, mTvRemark, mTvDetail;
    private RechargeTimesReportBean.DataBean.DataListBean mBean;
    private String mGid;
    private BaseListView mDialogListView;
    private RechargeServiceDetailAdapter mDetailAdapter;
    private RechargeServiceDetailBean mDetailBean;

    private RecyclerView recyclerView;
    private InflateCountsDetailAdapter adapter;
    private LinearLayoutManager manager;
    private ImageView igMore;
    private PopupWindow popupWindow;
    private SweetAlertDialog mSweetAlertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_times_recharge_detail);
        initView();
        initVariable();
        setListener();
        getServiceData(mGid);
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("充次记录详情");
        mTvOrderNum = (TextView) findViewById(R.id.tv_goods_order_num);
        mTvVipName = (TextView) findViewById(R.id.tv_goods_order_name);
        mTvVipCardNo = (TextView) findViewById(R.id.tv_goods_order_card);
        mTvConsumeMoney = (TextView) findViewById(R.id.tv_goods_order_consume_money);
        mTvDiscountMoney = (TextView) findViewById(R.id.tv_goods_order_discount_money);
        mTvBalance = (TextView) findViewById(R.id.tv_goods_order_balance);
//        mTvPoint = (TextView) findViewById(R.id.tv_goods_order_point);
        mTvPayWay = (TextView) findViewById(R.id.tv_goods_order_pay_way);
        mTvConsumeTime = (TextView) findViewById(R.id.tv_goods_order_time);
        mTvDevice = (TextView) findViewById(R.id.tv_goods_order_device);
        mTvOperation = (TextView) findViewById(R.id.tv_goods_order_staff);
        mTvRemark = (TextView) findViewById(R.id.tv_goods_order_remark);
        mTvDetail = (TextView) findViewById(R.id.tv_service_order_detail);
        recyclerView = findViewById(R.id.recycler_view);
        igMore = findViewById(R.id.iv_more);
    }

    private void initVariable() {
        mBean = (RechargeTimesReportBean.DataBean.DataListBean) getIntent().getSerializableExtra("time");
        mTvOrderNum.setText(mBean.getMC_Order() == null ? "" : mBean.getMC_Order());
        mTvVipName.setText(mBean.getVIP_Name() == null ? "" : mBean.getVIP_Name());
        mTvVipCardNo.setText(mBean.getVCH_Card() == null ? "" : mBean.getVCH_Card());
        mTvConsumeMoney.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getMC_Amount() + ""));
        mTvDiscountMoney.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getMC_AmountDis() + ""));
        mTvBalance.setText(Decima2KeeplUtil.stringToDecimal(mBean.getMC_Integral() + ""));
        //  mTvPoint.setText(mBean.getMC_Number() + "");
        mTvPayWay.setText(mBean.getMC_Way() == null ? "" : mBean.getMC_Way());
        mTvConsumeTime.setText(mBean.getMC_ChargeTime() == null ? "" : mBean.getMC_ChargeTime());
        if (mBean.getMC_Device() == 1) {
            mTvDevice.setText("浏览器");
        }
        if (mBean.getMC_Device() == 2) {
            mTvDevice.setText("安卓手机");
        }
        if (mBean.getMC_Device() == 4) {
            mTvDevice.setText("IOS");
        }
        mTvOperation.setText(mBean.getMC_Creator() == null ? "" : mBean.getMC_Creator());
        mTvRemark.setText(mBean.getMC_Remark() == null ? "" : mBean.getMC_Remark());
        mGid = mBean.getGID();
        manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(manager);
    }

    private void setListener() {
        findViewById(R.id.tv_no_confirm_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showDetailDialog();
            }
        });

        igMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop(v);
            }
        });
    }

    private void showDetailDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_report_layout, (ViewGroup) findViewById(R.id.dialog_report_detail));
        mDialogListView = (BaseListView) dialog.findViewById(R.id.lv_report_detail);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.create();
        alertDialog.setContentView(dialog);
        alertDialog.setView(dialog);
        getServiceData(mGid);
    }

    private void getServiceData(String gid) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(TimesRechargeDetailActivity.this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("PageIndex", 1);
        params.put("PageSize", 100);
        params.put("CO_GID", gid);
        client.post(HttpAPI.API().GOOD_DETAIL, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mDetailBean = CommonFun.JsonToObj(responseString, RechargeServiceDetailBean.class);
                Le("back-----" + responseString);
                // mDetailAdapter = new RechargeServiceDetailAdapter(TimesRechargeDetailActivity.this, mDetailBean);
                // mDialogListView.setAdapter(mDetailAdapter);
                // dialog.show();
                adapter = new InflateCountsDetailAdapter(TimesRechargeDetailActivity.this, mDetailBean);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(TimesRechargeDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showPop(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_menu_cancel_order, null);
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
                if (MyApplication.IS_CANCEL_ORDER) {
                    showPasswordDialog();
                } else {
                    undoFastOrder();
                }
            }
        });
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (MyApplication.PRINT_IS_OPEN) {
                    new HttpGetPrintContents(TimesRechargeDetailActivity.this, MyApplication.HYCC_PRINT_TIMES, mBean.getGID()).HYCC();
                } else {
                    CustomToast.makeText(TimesRechargeDetailActivity.this, "打印开关未开启", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    /**
     * 密码输入框
     */
    private void showPasswordDialog() {
        LayoutInflater inflater = getLayoutInflater();
        final View dialog = inflater.inflate(R.layout.input_password_dialog_layout, (ViewGroup) findViewById(R.id.input_dialog));
        final EditText etPassword = (EditText) dialog.findViewById(R.id.et_input_dialog_password);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_input_cancel);
        Button btnConfirm = (Button) dialog.findViewById(R.id.btn_input_confirm);
        AlertDialog.Builder builder = new AlertDialog.Builder(TimesRechargeDetailActivity.this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setContentView(dialog);
        alertDialog.setView(dialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etPassword.getText().toString().isEmpty() && etPassword.getText().toString().equals(MyApplication.CANCEL_PAW)) {
                    undoFastOrder();
                } else {
                    CustomToast.makeText(TimesRechargeDetailActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void undoFastOrder() {
        RequestParams params = new RequestParams();
        params.put("GID", mBean.getGID());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(TimesRechargeDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("撤单成功");
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mSweetAlertDialog.dismiss();
                        finish();
                    }
                });
                mSweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(TimesRechargeDetailActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        };
        HttpHelper.post(this, HttpAPI.API().CC_UNDO_ORDER, params, callBack);
    }
}
