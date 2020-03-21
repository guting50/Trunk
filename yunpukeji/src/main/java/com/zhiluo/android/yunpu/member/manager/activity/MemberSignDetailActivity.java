package com.zhiluo.android.yunpu.member.manager.activity;

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
import com.zhiluo.android.yunpu.member.manager.bean.MemberFastCheckInBean;
import com.zhiluo.android.yunpu.print.util.GetPrintSet;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.account.bean.MemberSignReportBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.good.OutWareItemDetailActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.yslutils.DataUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2019-05-15.
 */

public class MemberSignDetailActivity extends BaseActivity {

    @Bind(R.id.tv_no_confirm_back)
    TextView tvNoConfirmBack;
    @Bind(R.id.tv_no_confirm_title)
    TextView tvNoConfirmTitle;
    @Bind(R.id.tv_project_name)
    TextView tvProjectName;
    @Bind(R.id.tv_project_member)
    TextView tvProjectMember;
    @Bind(R.id.tv_goods_card)
    TextView tvGoodsCard;
    @Bind(R.id.tv_project_phone)
    TextView tvProjectPhone;
    @Bind(R.id.tv_project_point)
    TextView tvProjectPoint;
    @Bind(R.id.tv_project_time)
    TextView tvProjectTime;
    @Bind(R.id.tv_project_creator)
    TextView tvProjectCreator;

    private MemberFastCheckInBean.DataBean.DataListBean checkInfo;
    private MemberSignReportBean.DataBean.DataListBean mBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_sign_detail);
        ButterKnife.bind(this);

        initData();
        initLisenter();
    }

    private void initData() {
        tvNoConfirmTitle.setText("签到详情");
        Intent intent = getIntent();
        if (intent != null) {
            checkInfo = (MemberFastCheckInBean.DataBean.DataListBean) intent.getSerializableExtra("checkInfo");

            if (checkInfo != null) {
                tvProjectName.setText(DataUtils.checkNull(checkInfo.getMCP_Name()));
                tvProjectMember.setText(DataUtils.checkNull(checkInfo.getVIP_Name()));
                tvGoodsCard.setText(DataUtils.checkNull(checkInfo.getVCH_Card()));
                tvProjectPhone.setText(DataUtils.checkNull(checkInfo.getVIP_CellPhone()));
                tvProjectPoint.setText(checkInfo.getMCI_Integral() + "");
                tvProjectTime.setText(DataUtils.checkNull(checkInfo.getMCI_UpdateTime()));
                tvProjectCreator.setText(DataUtils.checkNull(checkInfo.getMCI_Creator()));

            }
            mBean = (MemberSignReportBean.DataBean.DataListBean) intent.getSerializableExtra("sign");

            if (mBean != null) {
                tvProjectName.setText(DataUtils.checkNull(mBean.getMCP_Name()));
                tvProjectMember.setText(DataUtils.checkNull(mBean.getVIP_Name()));
                tvGoodsCard.setText(DataUtils.checkNull(mBean.getVCH_Card()));
                tvProjectPhone.setText(DataUtils.checkNull(mBean.getVIP_CellPhone()));
                tvProjectPoint.setText(mBean.getMCI_Integral() + "");
                tvProjectTime.setText(DataUtils.checkNull(mBean.getMCI_UpdateTime()));
                tvProjectCreator.setText(DataUtils.checkNull(mBean.getMCI_Creator()));

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



    }



}
