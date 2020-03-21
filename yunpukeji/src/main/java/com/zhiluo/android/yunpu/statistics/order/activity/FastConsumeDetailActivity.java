package com.zhiluo.android.yunpu.statistics.order.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.activity.PayConfirmActivity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.mvp.model.PasswordVerifyBean;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.order.bean.FastConsumeReportBean;
import com.zhiluo.android.yunpu.statistics.order.bean.GoodsOrderReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class FastConsumeDetailActivity extends BaseActivity {
    private TextView mTvTitle, mTvOrderNum, mTvVipName, mTvVipCardNo, mTvConsumeMoney, mTvDiscountMoney, mTvBalance,
            mTvPoint, mTvPayWay, mTvConsumeTime, mTvDevice, mTvOperation, mTvRemark,mTvstusta;
    private ImageView igMore;
    private FastConsumeReportBean.DataBean.DataListBean mBean;
    private PopupWindow popupWindow;
    private SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_consume_detail);
        initView();
        initVariable();
        setListener();
    }
    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("快速消费详情");
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
        mTvstusta = findViewById(R.id.tv_goods_order_stusta);
        igMore = findViewById(R.id.iv_more);

    }

    private void initVariable() {
        mBean = (FastConsumeReportBean.DataBean.DataListBean) getIntent().getSerializableExtra("fast");
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
        }else if (mBean.getCO_Device() == 4) {
            mTvDevice.setText("IOS");
        }else {
            mTvDevice.setText("浏览器");
        }
        mTvOperation.setText(mBean.getCO_Creator() == null ? "" : mBean.getCO_Creator());
        mTvstusta.setText(mBean.getCO_Identifying() == null?"":mBean.getCO_Identifying());
        mTvRemark.setText(mBean.getCO_Remark() == null ? "" : mBean.getCO_Remark());
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
                if ( MyApplication.IS_CANCEL_ORDER){
                    showPasswordDialog();
                }else {
                    undoFastOrder();
                }

            }
        });
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (MyApplication.PRINT_IS_OPEN){
                    new HttpGetPrintContents(FastConsumeDetailActivity.this, MyApplication.KSXF_PRINT_TIMES, mBean.getGID()).KSXF();
                }else {
                    CustomToast.makeText(FastConsumeDetailActivity.this,"打印开关未开启", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(FastConsumeDetailActivity.this);
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
                if (!etPassword.getText().toString().isEmpty()&&etPassword.getText().toString().equals(MyApplication.CANCEL_PAW)){
                    undoFastOrder();
                }else {
                    CustomToast.makeText(FastConsumeDetailActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    private void undoFastOrder(){
        RequestParams params = new RequestParams();
        params.put("GID",mBean.getGID());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(FastConsumeDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
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
                CustomToast.makeText(FastConsumeDetailActivity.this,msg,Toast.LENGTH_SHORT).show();

            }
        };
        HttpHelper.post(this, HttpAPI.API().UNDO_FAST_ORDER,params,callBack);
    }
}
