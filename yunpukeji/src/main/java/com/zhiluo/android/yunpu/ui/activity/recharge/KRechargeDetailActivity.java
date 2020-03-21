package com.zhiluo.android.yunpu.ui.activity.recharge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.zhiluo.android.yunpu.entity.RechargeBean;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsDetailActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsModificationActivity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class KRechargeDetailActivity extends AppCompatActivity implements MyMenuPopWindow.OnItemClickListener {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.iv_recharge_detail_menu)
    ImageView ivMenu;
    @Bind(R.id.tv_recharge_detail_name)
    TextView tvName;
    @Bind(R.id.tv_recharge_detail_money)
    TextView tvMoney;
    @Bind(R.id.tv_recharge_detail_give_money)
    TextView tvGiveMoney;
    @Bind(R.id.tv_recharge_detail_give_integral)
    TextView tvGiveIntegral;
    @Bind(R.id.tv_recharge_detail_time)
    TextView tvTime;

    private RechargeBean mRechargeBean;
    private MyMenuPopWindow mPopupMenu;
    private SweetAlertDialog mSweetAlertDialog;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_krecharge_detail);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    private void init() {
        mPopupWindow=new PopupWindow(this);
       /* mPopupMenu = new MyMenuPopWindow(this, "编辑", "删除", null);
        mPopupMenu.setOnItemClickListener(this);*/
        mRechargeBean = (RechargeBean) getIntent().getSerializableExtra("Recharge");
        if (mRechargeBean != null) {
            tvName.setText(mRechargeBean.getRP_Name());
            tvMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mRechargeBean.getRP_RechargeMoney()));
            tvGiveMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mRechargeBean.getRP_GiveMoney()));
            tvGiveIntegral.setText("" + mRechargeBean.getRP_GivePoint());
            switch (mRechargeBean.getRP_ValidType()) {
                case 0:
                    tvTime.setText("永久有效");
                    break;
                case 1:
                    String start = mRechargeBean.getRP_ValidStartTime();
                    String end = mRechargeBean.getRP_ValidEndTime();
                    if (start.length() > 10) {
                        start = start.substring(0, 10);
                    }
                    if (end.length() > 10) {
                        end = end.substring(0, 10);
                    }
                    tvTime.setText(start + "至" + end);
                    break;
                case 2:
                    tvTime.setText("每周" + mRechargeBean.getRP_ValidWeekMonth());
                    break;
                case 3:
                    tvTime.setText("每月" + mRechargeBean.getRP_ValidWeekMonth());
                    break;
                case 4:
                    tvTime.setText("会员生日当天有效");
                    break;
            }
        }
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
                Intent intent = new Intent(KRechargeDetailActivity.this, YSLEditReChargeActivity.class);
                intent.putExtra("RE", mRechargeBean);
                startActivity(intent);
                KRechargeDetailActivity.this.finish();
                mPopupWindow.dismiss();
            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRechargeBean != null) {
                    mSweetAlertDialog = new SweetAlertDialog(KRechargeDetailActivity.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("删除快捷充值");
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
                            delete(mRechargeBean.getGID());
                            mSweetAlertDialog.dismiss();
                        }
                    });
                    mSweetAlertDialog.show();
                }
                mPopupWindow.dismiss();
            }
        });
    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow(v);
            }
        });
    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.tv_menu_pop_first:
                if (mRechargeBean != null) {
                    Intent intent = new Intent(KRechargeDetailActivity.this, YSLEditReChargeActivity.class);
                    intent.putExtra("RE", mRechargeBean);
                    startActivity(intent);
                    finish();
                }
                mPopupMenu.dismiss();
                break;
            case R.id.tv_menu_pop_second:
                if (mRechargeBean != null) {
                    mSweetAlertDialog = new SweetAlertDialog(KRechargeDetailActivity.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("删除快捷充值");
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
                            delete(mRechargeBean.getGID());
                            mSweetAlertDialog.dismiss();
                        }
                    });
                    mSweetAlertDialog.show();
                }
                mPopupMenu.dismiss();
                break;
            default:
                mPopupMenu.dismiss();
                break;
        }
    }

    /**
     * 显示PopMenu
     */
    private void showPopupWindow(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        mPopupMenu.showAsDropDown(KRechargeDetailActivity.this.findViewById(R.id.rl_recharge_detail_head),
                location[0] - mPopupMenu.getWidth() - 100, 0);
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
                CustomToast.makeText(KRechargeDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在删除...", false);
        HttpHelper.post(this, "RechargePackage/Delete", params, callBack);
    }
}
