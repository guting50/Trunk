package com.zhiluo.android.yunpu.statistics.order.activity;

import android.content.Context;
import android.content.DialogInterface;
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
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.order.bean.YJJYOrderDetailBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ZPH on 2019-07-03.
 */

public class YJJYOrderDetailActivity extends BaseActivity {

    @Bind(R.id.tv_no_confirm_back)
    TextView tvNoConfirmBack;
    @Bind(R.id.tv_no_confirm_title)
    TextView tvNoConfirmTitle;
    @Bind(R.id.iv_more)
    ImageView ivMore;
    @Bind(R.id.tv_goods_order_num)
    TextView tvGoodsOrderNum;

    @Bind(R.id.tv_yjjy_order_oil_name)
    TextView tvYjjyOrderOilName;
    @Bind(R.id.tv_yjjy_order_oil_number)
    TextView tvYjjyOrderOilNumber;


    private TextView   mTvVipName, mTvVipCardNo, mTvConsumeMoney, mTvBalance,
            mTvPoint, mTvPayWay, mTvConsumeTime, mTvDevice, mTvOperation, mTvstusta;

    private YJJYOrderDetailBean.DataBean.DataListBean mBean;
    private PopupWindow popupWindow;
    private SweetAlertDialog mSweetAlertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yjjy_order_detail);
        ButterKnife.bind(this);
        initView();
        initVariable();
        setListener();
//        getGoodDetail(mGid);
    }

    private void initView() {

        tvNoConfirmTitle.setText("一键加油详情");
        mTvVipName = (TextView) findViewById(R.id.tv_goods_order_name);
        mTvVipCardNo = (TextView) findViewById(R.id.tv_goods_order_card);
        mTvConsumeMoney = (TextView) findViewById(R.id.tv_goods_order_consume_money);
        mTvBalance = (TextView) findViewById(R.id.tv_goods_order_balance);
        mTvPoint = (TextView) findViewById(R.id.tv_goods_order_point);
        mTvPayWay = (TextView) findViewById(R.id.tv_goods_order_pay_way);
        mTvConsumeTime = (TextView) findViewById(R.id.tv_goods_order_time);
        mTvDevice = (TextView) findViewById(R.id.tv_goods_order_device);
        mTvOperation = (TextView) findViewById(R.id.tv_goods_order_staff);

        mTvstusta = findViewById(R.id.tv_goods_order_stusta);
    }

    private void initVariable() {
        mBean = (YJJYOrderDetailBean.DataBean.DataListBean) getIntent().getSerializableExtra("goods");


        tvGoodsOrderNum.setText(mBean.getCO_OrderCode() == null ? "" : mBean.getCO_OrderCode());
        mTvVipName.setText(mBean.getVIP_Name() == null ? "" : mBean.getVIP_Name());
        mTvVipCardNo.setText(mBean.getVIP_Card() == null ? "" : mBean.getVIP_Card());
        tvYjjyOrderOilName.setText(mBean.getOM_Name() == null ? "" : mBean.getOM_Name());
        tvYjjyOrderOilNumber.setText(mBean.getCO_Number() +"");

        mTvConsumeMoney.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getCO_OrderAmount() + ""));
        mTvBalance.setText("¥" + Decima2KeeplUtil.stringToDecimal(mBean.getVIP_Balance() + ""));
        mTvPoint.setText(Decima2KeeplUtil.stringToDecimal(mBean.getCO_Integral() + ""));
        mTvPayWay.setText(mBean.getCO_PayWay() == null ? "" : mBean.getCO_PayWay());
        mTvConsumeTime.setText(mBean.getCO_CreateTime() == null ? "" : mBean.getCO_CreateTime());

        if (mBean.getCO_Device() == 2) {
            mTvDevice.setText("安卓手机");
        } else if (mBean.getCO_Device() == 4) {
            mTvDevice.setText("IOS");
        } else {
            mTvDevice.setText("浏览器");
        }
        mTvOperation.setText(mBean.getCO_Creator() == null ? "" : mBean.getCO_Creator());
        mTvstusta.setText(mBean.getCO_Identifying() == null ? "" : mBean.getCO_Identifying());
//        mTvRemark.setText(mBean.getCO_Remark() == null ? "" : mBean.getCO_Remark());


    }


    private void setListener() {
        tvNoConfirmBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ivMore.setOnClickListener(new View.OnClickListener() {
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

//                popupWindow.dismiss();
//                Intent intent = new Intent(YJJYOrderDetailActivity.this, GoodsRetureActivity.class);
//                intent.putExtra("TYPE", "all");
//                intent.putExtra("mBean", mBean);
//                startActivity(intent);

            }
        });
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (MyApplication.PRINT_IS_OPEN) {
                    new HttpGetPrintContents(YJJYOrderDetailActivity.this, MyApplication.YJJY_PRINT_TIMES, mBean.getGID()).YJJY();
                } else {
                    CustomToast.makeText(YJJYOrderDetailActivity.this, "打印开关未开启", Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(YJJYOrderDetailActivity.this);
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
                    CustomToast.makeText(YJJYOrderDetailActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
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
                mSweetAlertDialog = new SweetAlertDialog(YJJYOrderDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
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
                CustomToast.makeText(YJJYOrderDetailActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().YJJY_UNDO_ORDER, params, callBack);
    }

}
