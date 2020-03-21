package com.zhiluo.android.yunpu.ui.activity.timesrule;

import android.content.Context;
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
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ZPH on 2018-11-30.
 */

public class TimesRuleDetail extends BaseActivity {
    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.iv_recharge_detail_menu)
    ImageView ivRechargeDetailMenu;
    @Bind(R.id.rl_recharge_detail_head)
    RelativeLayout rlRechargeDetailHead;
    @Bind(R.id.tv_recharge_detail_name)
    TextView tvRechargeDetailName;
    @Bind(R.id.tv_recharge_detail_money)
    TextView tvRechargeDetailMoney;
    @Bind(R.id.tv_recharge_detail_give_integral)
    TextView tvRechargeDetailGiveIntegral;

    private TimesRuleManagerBean.DataBean.DataListBean bean;
    private PopupWindow mPopupWindow;
    private SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.times_rule_detail);
        ButterKnife.bind(this);
        loadDtat();
        setLisenter();


    }

    private void setLisenter() {
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivRechargeDetailMenu.setOnClickListener(new View.OnClickListener() {
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
                Intent intent = new Intent(TimesRuleDetail.this, EditRulesActivity.class);
                intent.putExtra("rule", bean);
                startActivity(intent);
                TimesRuleDetail.this.finish();
                mPopupWindow.dismiss();
            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean != null) {
                    mSweetAlertDialog = new SweetAlertDialog(TimesRuleDetail.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("删除该规则");
                    mSweetAlertDialog.setConfirmText("确认");
                    mSweetAlertDialog.setCancelText("取消");
                    mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mSweetAlertDialog.dismiss();
                        }
                    });
                    mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            delete(bean.getGID());
                            mSweetAlertDialog.dismiss();
                        }
                    });
                    mSweetAlertDialog.show();
                }
                mPopupWindow.dismiss();
            }
        });
    }

    private void loadDtat() {
        mPopupWindow=new PopupWindow(this);
        bean = (TimesRuleManagerBean.DataBean.DataListBean) getIntent().getSerializableExtra("rule");

        if (bean!=null){
            tvRechargeDetailName.setText(bean.getWR_Name()+"");
            tvRechargeDetailMoney.setText(bean.getWR_ConsumeRule());
            if (bean.getWR_RegularUnit() ==1){
                tvRechargeDetailGiveIntegral.setText("每天消费次数");
            }else if (bean.getWR_RegularUnit() ==2){
                tvRechargeDetailGiveIntegral.setText("每周消费次数");
            }else if (bean.getWR_RegularUnit() ==3){
                tvRechargeDetailGiveIntegral.setText("每月消费次数");
            }else if (bean.getWR_RegularUnit() ==4){
                tvRechargeDetailGiveIntegral.setText("每年消费次数");
            }
        }
    }

    /**
     * 删除
     *
     * @param gid
     */
    private void delete(String gid) {
        RequestParams params = new RequestParams();
        params.put("GID", gid);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                finish();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(TimesRuleDetail.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在删除...", false);
        HttpHelper.post(this, HttpAPI.API().DEL_RULES, params, callBack);
    }



}
