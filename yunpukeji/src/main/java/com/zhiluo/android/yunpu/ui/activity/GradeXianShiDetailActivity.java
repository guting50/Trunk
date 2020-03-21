package com.zhiluo.android.yunpu.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by ZPH on 2019-01-15.
 */

public class GradeXianShiDetailActivity extends BaseActivity {

    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.ed_cishu)
    TextView edCishu;
    @Bind(R.id.ed_shijian)
    TextView edShijian;


    private DataBean mGrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_detail_xianshis);
        ButterKnife.bind(this);
        initData();
        iniLisenter();
    }


    private void iniLisenter() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void initData() {
        mGrade = (DataBean) getIntent().getSerializableExtra("GD");
        //有效次数
        if (mGrade.getVG_IsTime() == 1) {
            if (mGrade.getVG_IsTimeTimes() == null){
                edCishu.setText("不限次");
            }else {
                edCishu.setText( Integer.parseInt(mGrade.getVG_IsTimeTimes())+"");
            }
            edShijian.setText("" + Integer.parseInt(mGrade.getVG_IsTimeNum() == null ? "0" : mGrade.getVG_IsTimeNum())
                    +((String)mGrade.getVG_IsTimeUnit() == null ? "天" : (String) mGrade.getVG_IsTimeUnit()));
        }
    }
}
