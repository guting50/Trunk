package com.zhiluo.android.yunpu.paymanager.activity;

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

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsDetailActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsManagerActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsModificationActivity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.paymanager.bean.PayDetailBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;


import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ZPH on 2019-05-23.
 */

public class PayDetailActivity extends BaseActivity {

    @Bind(R.id.tv_no_confirm_back)
    TextView tvNoConfirmBack;
    @Bind(R.id.tv_no_confirm_title)
    TextView tvNoConfirmTitle;
    @Bind(R.id.tv_pay_type)
    TextView tvPayType;
    @Bind(R.id.tv_pay_money)
    TextView tvPayMoney;
    @Bind(R.id.tv_pay_time)
    TextView tvPayTime;
    @Bind(R.id.tv_pay_person)
    TextView tvPayPerson;
    @Bind(R.id.tv_pay_remark)
    TextView tvPayRemark;
    @Bind(R.id.tv_pay_creater)
    TextView tvPayCreater;
    @Bind(R.id.iv_more)
    ImageView ivMore;

    private PayDetailBean.DataBean.DataListBean mBean;
    private PopupWindow mPopupWindow = new PopupWindow();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(PayDetailActivity.this);
        setContentView(R.layout.activity_pay_detail);
        ButterKnife.bind(this);
        initData();
        Lisenter();
    }


    private void initData() {
        mBean = (PayDetailBean.DataBean.DataListBean) getIntent().getSerializableExtra("PAYDETAIL");
        tvNoConfirmTitle.setText("支出明细详情");

        if (mBean != null) {
            tvPayType.setText(mBean.getET_Name());
            tvPayMoney.setText(mBean.getED_Money() + "");
            tvPayTime.setText(mBean.getED_Time());
            tvPayPerson.setText(mBean.getED_Expendiator());
            tvPayRemark.setText(mBean.getED_Remark());
            tvPayCreater.setText(mBean.getED_Creator());
        }
    }

    private void Lisenter() {
        tvNoConfirmBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow(v);
            }
        });

    }

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
                    Intent intent = new Intent(PayDetailActivity.this, ModifyPayDetailActivity.class);
                    intent.putExtra("paydetail", mBean);
                    startActivity(intent);
                    mPopupWindow.dismiss();

            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                    showdeleteDialog();
                    mPopupWindow.dismiss();
            }
        });
    }

    /**
     * 展示删除会员窗口
     */
    private void showdeleteDialog() {

        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("删除明细")
                .setContentText("是否删除该条明细？")
                .setConfirmText("确定")
                .setCancelText("取消")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        delExpenses();
                    }
                })
                .show();

    }

    private void delExpenses(){
        RequestParams params = new RequestParams();
        params.put("GID",mBean.getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(PayDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText("删除支出明细成功！");
                sweetAlertDialog.setConfirmText("确定");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
//                        ActivityManager.getInstance().exit();
//                                    GoodsDetailActivity.this.finish();
                        setResult(222);
                        finish();
                    }
                });
                sweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(PayDetailActivity.this,msg, Toast.LENGTH_SHORT).show();

            }
        };
        HttpHelper.post(this, HttpAPI.API().Expenses_DelDetail,params,callBack);
    }
}
