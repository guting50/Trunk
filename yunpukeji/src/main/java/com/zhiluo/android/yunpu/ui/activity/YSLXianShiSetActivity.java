package com.zhiluo.android.yunpu.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${YSL} on 2018-04-11.
 */

public class YSLXianShiSetActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edShiJian, edCiShu;
    private CheckBox cbCiShu;
    private RelativeLayout rChoise;
    private TextView tShiJianDanWei;
    private DataBean mGrade;
    private Dialog chooseDialog;
    private List<String> mlist;
    private boolean typeCiShu;
    private String timeType,stringcishu,stringshijian;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_xianshiset);
        initViews();
        listener();
        undateViews();

    }

    private void undateViews() {
        mGrade = (DataBean) getIntent().getSerializableExtra("Grade");
        Intent intent = getIntent();

        //有效次数
        if (mGrade.getVG_IsTimeTimes() != null) {
            cbCiShu.setChecked(false);
            edCiShu.setText("" + Integer.parseInt(mGrade.getVG_IsTimeTimes() == null ? "0" : mGrade.getVG_IsTimeTimes()));
        } else {
            cbCiShu.setChecked(true);
        }
        //有效时间
        if (mGrade.getVG_IsTimeNum() != null) {
            edShiJian.setText("" + Integer.parseInt(mGrade.getVG_IsTimeNum() == null ? "0" : mGrade.getVG_IsTimeNum()));
        } else {
//            cbShiJian.setChecked(true);
        }
        //时间显示判断
        switch (((String) mGrade.getVG_IsTimeUnit()) == null ? "天" : (String) mGrade.getVG_IsTimeUnit()) {
            case "天":
                tShiJianDanWei.setText("天");
                break;
            case "月":
                tShiJianDanWei.setText("月");
                break;
            case "年":
                tShiJianDanWei.setText("年");
                break;
        }
        mlist = new ArrayList<>();
        mlist.add("天");
        mlist.add("月");
        mlist.add("年");
        if (intent!=null){
            typeCiShu = intent.getBooleanExtra("cishu",false);
            timeType = intent.getStringExtra("tShiJianDanWei");
            stringcishu = intent.getStringExtra("edcishu");
            stringshijian = intent.getStringExtra("edshijian");
            cbCiShu.setChecked(typeCiShu);
            if (timeType!=null&&!timeType.equals(""))
                tShiJianDanWei.setText(timeType);
            if (stringcishu!=null&&!stringcishu.equals("")&&!stringcishu.equals("null"))
                edCiShu.setText(stringcishu);
            if (stringshijian!=null&&!stringshijian.equals(""))
                edShiJian.setText(stringshijian);

        }

    }

    private void listener() {
        cbCiShu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edCiShu.setText("");
                    edCiShu.setEnabled(false);
                } else {
                    edCiShu.setEnabled(true);
                }
            }
        });
        edCiShu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()||s.toString().equals("")){

                }else if (Integer.parseInt(s.toString())==0){
                    edCiShu.setText("");
                }
            }
        });
        edShiJian.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()||s.toString().equals("")){

                }else if (Integer.parseInt(s.toString())==0){
                    edShiJian.setText("");
                }
            }
        });

//        cbShiJian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    edShiJian.setText("0");
//                    edShiJian.setEnabled(false);
//                    tShiJianDanWei.setTextColor(Color.parseColor("#878787"));
//                } else {
//                    edShiJian.setEnabled(true);
//                    tShiJianDanWei.setTextColor(Color.parseColor("#333333"));
//                }
//            }
//        });
    }

    private void initViews() {
        edCiShu = findViewById(R.id.ed_cishu);
        edShiJian = findViewById(R.id.ed_shijian);

        cbCiShu = findViewById(R.id.cb_cishu);
        rChoise = findViewById(R.id.r_choisetime);//选择
        tShiJianDanWei = findViewById(R.id.t_timetype);//时间类型，这里是天，月，年
        rChoise.setOnClickListener(this);
        findViewById(R.id.tv_back).setOnClickListener(this);
        findViewById(R.id.img_ok).setOnClickListener(this);
        findViewById(R.id.r_choisetime).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.r_choisetime:
                showChooseDialog(mlist, tShiJianDanWei);
                break;
            case R.id.img_ok:
                if ((edCiShu.getText().toString().equals("")||Integer.parseInt( edCiShu.getText().toString())<1)&&!cbCiShu.isChecked()){
                    CustomToast.makeText(this, "次数不能小于1", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edShiJian.getText().toString().equals("")||Integer.parseInt( edShiJian.getText().toString())<1){
                    CustomToast.makeText(this, "时间不能小于1", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("cishu", cbCiShu.isChecked());
                intent.putExtra("tShiJianDanWei", tShiJianDanWei.getText().toString());
                intent.putExtra("edcishu", edCiShu.getText().toString());
                intent.putExtra("edshijian", edShiJian.getText().toString());
                setResult(500, intent);
                YSLXianShiSetActivity.this.finish();
                break;
            case R.id.tv_back:
//                CustomToast.makeText(this, "您取消了限时设定", Toast.LENGTH_SHORT).show();
                YSLXianShiSetActivity.this.finish();
                break;
        }
    }

    /**
     * chooseDialog
     */
    private void showChooseDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {//设置默认值
            if (mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }
}
