package com.zhiluo.android.yunpu.goods.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by YSL on 2018-10-19.
 */

public class GoodsManagerChooseActivity extends BaseActivity {


    @Bind(R.id.tv_report_screen_back)
    TextView ScreenBack;
    @Bind(R.id.tv_report_screen_confirm)
    TextView ScreenConfirm;
    @Bind(R.id.sp_goods_order_type)
    Spinner spGoodsOrderType;
    @Bind(R.id.et_consume_dayu)
    EditText etConsumeDayu;
    @Bind(R.id.et_consume_xiaoyu)
    EditText etConsumeXiaoyu;
    @Bind(R.id.btn_report_screen_clean)
    Button ScreenClean;
    @Bind(R.id.sp_goods_order)
    Spinner spGoodsOrder;


    private ArrayAdapter<String> mSpinnerAdapter;
    private ArrayAdapter<String> mSpinnerorderAdapter;
    private ArrayList<String> GoodsTypeList = new ArrayList<>();//类型
    private ArrayList<String> GoodsorderList = new ArrayList<>();//类型
    private String goodsType,SortType;
    private String maxPrice;
    private String minPrice;
    private int pos = 0;
    private int pos1 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_manager_choose);
        ButterKnife.bind(this);

        initData();
        setListenter();

    }

    private void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            goodsType = intent.getStringExtra("goodstype");
            maxPrice = intent.getStringExtra("MAX");
            minPrice = intent.getStringExtra("MIN");
            SortType = intent.getStringExtra("SortType");
        }

        if (goodsType != null&&!goodsType.equals("")) {
            pos = Integer.parseInt(goodsType.equals("") ? "0" : goodsType) + 1;
        }
        if (SortType!=null){
            pos1 = Integer.parseInt(SortType.equals("")?"0":SortType);
        }

        if (maxPrice != null) {
            etConsumeDayu.setText(maxPrice + "");
        }
        if (minPrice != null) {
            etConsumeXiaoyu.setText(minPrice);
        }

        GoodsTypeList.add("全部");
        GoodsTypeList.add("普通商品");
        GoodsTypeList.add("服务商品");
        GoodsTypeList.add("礼品");

        GoodsorderList.add("时间降序排序");
        GoodsorderList.add("名称升序排序");


        mSpinnerAdapter = new ArrayAdapter<String>(GoodsManagerChooseActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, GoodsTypeList);
        spGoodsOrderType.setAdapter(mSpinnerAdapter);
        spGoodsOrderType.setSelection(pos);

        mSpinnerorderAdapter = new ArrayAdapter<String>(GoodsManagerChooseActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, GoodsorderList);
        spGoodsOrder.setAdapter(mSpinnerorderAdapter);
        spGoodsOrder.setSelection(pos1);


    }


    private void setListenter() {
        ScreenBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ScreenConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.putExtra("goodstype", goodsType);
                intent.putExtra("MAX", etConsumeDayu.getText().toString());
                intent.putExtra("MIN", etConsumeXiaoyu.getText().toString());
                intent.putExtra("SortType", SortType);
                setResult(666, intent);
                finish();

            }
        });

        ScreenClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spGoodsOrderType.setSelection(0);
                etConsumeDayu.setText("");
                etConsumeXiaoyu.setText("");
            }
        });

        spGoodsOrderType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    goodsType = "";
                } else {
                    goodsType = String.valueOf(position - 1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spGoodsOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SortType = String.valueOf(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        /**
         * 保留两位小数
         */
        etConsumeDayu.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable edt) {
                String temp = edt.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2) {
                    edt.delete(posDot + 3, posDot + 4);
                }
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });

        /**
         * 保留两位小数
         */
        etConsumeXiaoyu.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable edt) {
                String temp = edt.toString();
                int posDot = temp.indexOf(".");
                if (posDot <= 0) return;
                if (temp.length() - posDot - 1 > 2) {
                    edt.delete(posDot + 3, posDot + 4);
                }
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }
        });
    }

}
