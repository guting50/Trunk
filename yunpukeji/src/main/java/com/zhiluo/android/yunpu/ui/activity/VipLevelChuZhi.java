package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

/**
 * Created by ${YSL} on 2018-04-10.
 */

public class VipLevelChuZhi extends AppCompatActivity {
    private EditText mContent;

    private DataBean mGrade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viplevelchuzhi);

        mGrade = (DataBean) getIntent().getSerializableExtra("Grade");
        initViews();
        initDatas();
        action();
    }

    private void action() {
        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mContent.getText().toString()) || mContent.getText().toString().equals("")) {
                    CustomToast.makeText(VipLevelChuZhi.this, "您未设置初始金额", Toast.LENGTH_SHORT).show();
                }
                VipLevelChuZhi.this.finish();
            }
        });
        findViewById(R.id.img_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("chuzhi", mContent.getText().toString());
                setResult(11, intent);
                VipLevelChuZhi.this.finish();
            }
        });
    }

    private void initViews() {
        mContent = findViewById(R.id.et_content);
    }


    private void initDatas(){

        if (mGrade != null)
            mContent.setText((int) mGrade.getVG_InitialAmount()+"");
    }

}
