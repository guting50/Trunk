package com.zhiluo.android.yunpu.statistics.order.activity;

import android.content.Context;
import android.content.Intent;
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

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.activity.LimitedTimeConsumeActivity;
import com.zhiluo.android.yunpu.member.manager.activity.MemberSignDetailActivity;
import com.zhiluo.android.yunpu.print.util.GetPrintSet;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.account.bean.LimitedConsumeReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.yslutils.DataUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2019-05-17.
 */

public class LimitedCosumeReportDetailActivity extends BaseActivity {


    @Bind(R.id.tv_no_confirm_back)
    TextView tvNoConfirmBack;
    @Bind(R.id.tv_no_confirm_title)
    TextView tvNoConfirmTitle;
    @Bind(R.id.tv_project_time)
    TextView tvProjectTime;
    @Bind(R.id.tv_project_overtime)
    TextView tvProjectOvertime;
    @Bind(R.id.tv_sy_times)
    TextView tvSyTimes;
    @Bind(R.id.tv_project_store)
    TextView tvProjectStore;
    @Bind(R.id.tv_kj_times)
    TextView tvKjTimes;
    @Bind(R.id.tv_project_device)
    TextView tvProjectDevice;
    @Bind(R.id.tv_project_creator)
    TextView tvProjectCreator;
    @Bind(R.id.tv_project_remark)
    TextView tvProjectRemark;
    @Bind(R.id.iv_more)
    ImageView igMore;

    private LimitedConsumeReportBean.DataBean.DataListBean mBean;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limitedconsume_detail);
        ButterKnife.bind(this);

        initData();
        initLisenter();
    }

    private void initData() {
        tvNoConfirmTitle.setText("限时消费详情");
        Intent intent = getIntent();
        if (intent != null) {

            mBean = (LimitedConsumeReportBean.DataBean.DataListBean) intent.getSerializableExtra("XSXF");

            if (mBean != null) {
                tvProjectTime.setText(DataUtils.checkNull(mBean.getTS_UpdateTime()));
                if (mBean.getTS_OverTime() != null && mBean.getTS_OverTime().length() > 10) {
                    tvProjectOvertime.setText(mBean.getTS_OverTime().substring(0, 10));
                } else {
                    tvProjectOvertime.setText(DataUtils.checkNull(mBean.getTS_OverTime()));
                }
                if (mBean.getTS_SurplusTimes() == null || mBean.getTS_SurplusTimes().equals("null")) {
                    tvSyTimes.setText("不限");
                } else {
                    tvSyTimes.setText(mBean.getTS_SurplusTimes() + "");
                }

                tvProjectStore.setText(DataUtils.checkNull(mBean.getSM_Name()));
                tvKjTimes.setText(mBean.getTS_MinusTimes() + "");
                String device = null;
                if (mBean.getTS_Device() == 4) {
                    device = "IOS";
                } else if (mBean.getTS_Device() == 2) {
                    device = "安卓手机";
                } else if (mBean.getTS_Device() == 1) {
                    device = "浏览器";
                } else {
                    device = "浏览器";
                }
                tvProjectDevice.setText(device + "");
                tvProjectCreator.setText(DataUtils.checkNull(mBean.getTS_Creator()));
                tvProjectRemark.setText(DataUtils.checkNull(mBean.getTS_Remark()));
            }
        }
    }

    private void initLisenter() {
        tvNoConfirmBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    //打印
                    new HttpGetPrintContents(LimitedCosumeReportDetailActivity.this, MyApplication.XSXF_PRINT_TIMES, mBean.getGID()).XSXF();
                }else {
                    CustomToast.makeText(LimitedCosumeReportDetailActivity.this,"打印开关未开启", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
