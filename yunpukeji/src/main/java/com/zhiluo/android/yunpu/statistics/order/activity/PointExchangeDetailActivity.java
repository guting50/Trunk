package com.zhiluo.android.yunpu.statistics.order.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.order.adapter.ExchangeDetailAdapter;
import com.zhiluo.android.yunpu.statistics.order.adapter.IntegralRecoderInnerAdapter;
import com.zhiluo.android.yunpu.statistics.order.adapter.JiCiOrderAdapter;
import com.zhiluo.android.yunpu.statistics.order.adapter.JiCiRecoderAdapter;
import com.zhiluo.android.yunpu.statistics.order.bean.PointExchangeDetailBean;
import com.zhiluo.android.yunpu.statistics.order.bean.PointExchangeReportBean;
import com.zhiluo.android.yunpu.statistics.rebate.bean.IntegralNewBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

public class PointExchangeDetailActivity extends BaseActivity {
    private TextView mTvTitle, mTvExchangeOrder, mTvVipName, mTvVipCard, mTvExchangeNum, mTvConsumePoint,
            mTvLeftPoint, mTvExchangeShop, mTvDevice, mTvExchangeTime, mTvOperation, mTvRemark, mTvDetail;
    private IntegralNewBean.DataBean.DataListBean mBean;

    private String mGid;
    private BaseListView mDialogListView;
    private ExchangeDetailAdapter mExchangeDetailAdapter;
    private PointExchangeDetailBean mPointExchangeDetailBean;

    private RecyclerView recyclerView;
    private IntegralRecoderInnerAdapter adapter;
    private ImageView igMore;
    private PopupWindow popupWindow;
    private SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integralrecoder_item);
        initView();
        initVariable();
        setListener();
    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("积分兑换详情");
        mTvExchangeOrder = (TextView) findViewById(R.id.tv_point_exchange_order);
        mTvVipName = (TextView) findViewById(R.id.tv_point_exchange_name);
        mTvVipCard = (TextView) findViewById(R.id.tv_point_exchange_card);
        mTvExchangeNum = (TextView) findViewById(R.id.tv_point_exchange_num);
        mTvConsumePoint = (TextView) findViewById(R.id.tv_point_exchange_consume);
        mTvLeftPoint = (TextView) findViewById(R.id.tv_point_exchange_left);
        mTvExchangeShop = (TextView) findViewById(R.id.tv_point_exchange_shop);
        mTvDevice = (TextView) findViewById(R.id.tv_times_consume_device);
        mTvExchangeTime = (TextView) findViewById(R.id.tv_point_exchange_exchange_time);
        mTvOperation = (TextView) findViewById(R.id.tv_point_exchange_operation);
        mTvRemark = (TextView) findViewById(R.id.tv_point_exchange_remark);
        //mTvDetail = (TextView) findViewById(R.id.tv_point_order_detail);
        recyclerView = findViewById(R.id.recycler_view);
        igMore = findViewById(R.id.iv_more);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(manager);
    }

    private void initVariable() {
        mBean = (IntegralNewBean.DataBean.DataListBean) getIntent().getSerializableExtra("point");
        if (mBean != null) {
            mTvExchangeOrder.setText(mBean.getIEG_OrderNo() == null ? "" : mBean.getIEG_OrderNo());
            mTvVipName.setText(mBean.getVIP_Name() == null ? "" : mBean.getVIP_Name());
            mTvVipCard.setText(mBean.getVIP_Card() == null ? "" : mBean.getVIP_Card());
            mTvExchangeNum.setText(mBean.getIEG_Number() + "");
            mTvConsumePoint.setText(Decima2KeeplUtil.stringToDecimal(mBean.getIEG_Score() + ""));
            mTvLeftPoint.setText(Decima2KeeplUtil.stringToDecimal(mBean.getIEG_RemainingPoints() + ""));
            mTvExchangeShop.setText(mBean.getSM_Name() == null ? "" : mBean.getSM_Name());
            if (mBean.getIEG_Device() == 2) {
                mTvDevice.setText("安卓手机");
            }else if (mBean.getIEG_Device() == 4) {
                mTvDevice.setText("IOS");
            }else {
                mTvDevice.setText("浏览器");
            }
            mTvExchangeTime.setText(mBean.getIEG_UpdateTime() == null ? "" : mBean.getIEG_UpdateTime());
            mTvOperation.setText(mBean.getIEG_Creator() == null ? "" : mBean.getIEG_Creator());
            mTvRemark.setText(mBean.getIEG_Remark() == null ? "" : mBean.getIEG_Remark());
            mGid = mBean.getGID();
            adapter = new IntegralRecoderInnerAdapter(this, mBean);
            recyclerView.setAdapter(adapter);
        }
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
                    undoOrder();
                }

            }
        });
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (MyApplication.PRINT_IS_OPEN){
                    new HttpGetPrintContents(PointExchangeDetailActivity.this, MyApplication.JFDH_PRINT_TIMES, mBean.getGID()).JFDH();
                }else {
                    CustomToast.makeText(PointExchangeDetailActivity.this,"打印开关未开启", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(PointExchangeDetailActivity.this);
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
            public void onClick(View v){
                if (!etPassword.getText().toString().isEmpty()&&etPassword.getText().toString().equals(MyApplication.CANCEL_PAW)){
                    undoOrder();
                }else {
                    CustomToast.makeText(PointExchangeDetailActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    private void undoOrder() {
        RequestParams params = new RequestParams();
        params.put("GID", mBean.getGID());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(PointExchangeDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
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
                CustomToast.makeText(PointExchangeDetailActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().JF_UNDO_ORDER, params, callBack);
    }
}
