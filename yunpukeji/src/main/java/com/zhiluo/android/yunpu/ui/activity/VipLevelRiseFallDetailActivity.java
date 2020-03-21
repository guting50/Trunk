package com.zhiluo.android.yunpu.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suke.widget.SwitchButton;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.yslutils.DataUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2019-03-29.
 */

public class VipLevelRiseFallDetailActivity extends Activity {


    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_titel)
    TextView tvTitel;
    @Bind(R.id.img_ok)
    ImageView imgOk;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.tv_rise_set)
    TextView tvRiseSet;
    @Bind(R.id.switch_button_rise_set)
    SwitchButton switchButtonRiseSet;
    @Bind(R.id.tv_rise_set_name)
    TextView tvRiseSetName;
    @Bind(R.id.tv_rise_set_content)
    TextView tvRiseSetContent;
    @Bind(R.id.rl_rise_set)
    RelativeLayout rlRiseSet;
    @Bind(R.id.ll_rise_set)
    LinearLayout llRiseSet;
    @Bind(R.id.tv_fall_set)
    TextView tvFallSet;
    @Bind(R.id.switch_button_fall_set)
    SwitchButton switchButtonFallSet;
    @Bind(R.id.tv_fall_set_name)
    TextView tvFallSetName;
    @Bind(R.id.tv_fall_set_content)
    TextView tvFallSetContent;
    @Bind(R.id.rl_fall_set)
    RelativeLayout rlFallSet;
    @Bind(R.id.ll_fall_set)
    LinearLayout llFallSet;
    @Bind(R.id.tv_case_name)
    TextView tvCaseName;
    @Bind(R.id.tv_case)
    TextView tvCase;
    @Bind(R.id.tv_min)
    EditText tvMin;
    @Bind(R.id.tv_max)
    EditText tvMax;
    @Bind(R.id.cb_content)
    CheckBox cbContent;
    @Bind(R.id.ll_statistics_time_other)
    LinearLayout llStatisticsTimeOther;
    @Bind(R.id.tv_notice_rise)
    TextView tvNoticeRise;
    @Bind(R.id.tv_notice_fall)
    TextView tvNoticeFall;


    private DataBean mGrade;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rise_fall_grade_set);
        ButterKnife.bind(this);

        initView();
        mGrade = (DataBean) getIntent().getSerializableExtra("mGrade");
        if (mGrade!=null){
            initData();
        }
    }

    private void initView() {
        imgOk.setVisibility(View.GONE);
        tvMin.setFocusable(false);
        tvMin.setFocusableInTouchMode(false);
        tvMax.setFocusable(false);
        tvMax.setFocusableInTouchMode(false);
        cbContent.setEnabled(false);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        tvTitel.setText("升降级详情");
        if (mGrade.getVG_IsLock() == 1) {
            switchButtonRiseSet.setChecked(true);

        } else {
            switchButtonRiseSet.setChecked(false);
            tvRiseSetContent.setText("");

        }
        if (mGrade.getVG_IsDownLock() == 1) {
            switchButtonFallSet.setChecked(true);
        } else {
            switchButtonFallSet.setChecked(false);
            tvFallSetContent.setText("");
        }
        tvRiseSetContent.setText(DataUtils.checkNull(mGrade.getVG_NextGradeName()));
        tvFallSetContent.setText(DataUtils.checkNull(mGrade.getVG_LastGradeName()));

        if (mGrade.getVG_UpDownType() != null) {
            if (mGrade.getVG_UpDownType() == 0) {
                tvCase.setText("按账户积分");

            } else if (mGrade.getVG_UpDownType() == 1) {
                tvCase.setText("按账户储值");

            } else if (mGrade.getVG_UpDownType() == 2) {
                tvCase.setText("按累计积分");

            } else if (mGrade.getVG_UpDownType() == 3) {
                tvCase.setText("按累计储值");

            } else if (mGrade.getVG_UpDownType() == 4) {
                tvCase.setText("按累计消费");

            }
        }

        tvMin.setText(mGrade.getUS_Value() + "");
        tvMax.setText(DataUtils.checkNull(mGrade.getUS_ValueMax()));
        if (mGrade.getVG_UpDownType() != null && mGrade.getUS_ValueMax() == null) {
            cbContent.setChecked(true);
            tvMax.setText("无穷大");
        }
        if (cbContent.isChecked()) {
            tvNoticeRise.setText("1.该等级条件上限为无穷大，将不会升级。");
        } else {
            if (switchButtonRiseSet.isChecked()) {
                tvNoticeRise.setText("1.当前等级的会员账户积分大于等于【" + tvMax.getText().toString() + "】时将自动升级到【" + tvRiseSetContent.getText().toString() + "】");
            } else {
                tvNoticeRise.setText("1.自动升级关闭");
            }
        }

        if (switchButtonFallSet.isChecked()) {
            tvNoticeFall.setText("2.当前等级的会员账户积分小于【" + tvMin.getText().toString() + "】时将自动降级到【" + tvFallSetContent.getText().toString() + "】");
        } else {
            tvNoticeFall.setText("2.自动降级关闭");
        }
        switchButtonFallSet.setEnabled(false);
        switchButtonRiseSet.setEnabled(false);
    }
}
