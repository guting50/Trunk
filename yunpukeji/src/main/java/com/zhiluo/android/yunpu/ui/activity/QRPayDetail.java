package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.home.activity.MineKeFuActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2019-08-24.
 */

public class QRPayDetail extends BaseActivity {

    @Bind(R.id.tv_open_qrpay)
    TextView tvOpenQrpay;
    @Bind(R.id.tv_askto_customer)
    TextView tvAsktoCustomer;
    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saoma_detail);
        ButterKnife.bind(this);
        initData();
        initLisent();
    }

    private void initData() {

    }

    private void initLisent() {

        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvOpenQrpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QRPayDetail.this,OpenQRPayActivity.class);
                startActivity(intent);

            }
        });

        tvAsktoCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QRPayDetail.this, MineKeFuActivity.class);
                startActivity(intent);
            }
        });
    }


}
