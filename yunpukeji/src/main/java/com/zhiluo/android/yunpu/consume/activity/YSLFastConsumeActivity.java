package com.zhiluo.android.yunpu.consume.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;

import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;


/**
 * Created by ${YSL} on 2018-05-05.
 * 快速消费
 */

public class YSLFastConsumeActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvBack, tvMoney, tvCostMoney;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonok, button00, btnadd;
    private LinearLayout lDelet, buttonadd;
    private MyHandler myHandler;
    private StringBuilder mEditContentBuilder;//文本框展示键盘输入内容
    private double mTempVar = 0;//中间变量
    private double mOrderMoney;//应收金额，订单金额

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_fast_cousume);
        initViews();
        initAction();
        initData();
    }

    private void initData() {
        myHandler = new MyHandler();
        mEditContentBuilder = new StringBuilder();
    }

    private void initAction() {
        tvBack.setOnClickListener(this);
        button00.setOnClickListener(this);
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonadd.setOnClickListener(this);
        buttonok.setOnClickListener(this);
        btnadd.setOnClickListener(this);
        lDelet.setOnClickListener(this);

        //金额过滤器
        MoneyInputFilter filter = new MoneyInputFilter(9, true, "最多只能输入9位金额");
        filter.setMessage("最多只能输入9位金额");
        tvCostMoney.setFilters(new InputFilter[]{filter});
        tvCostMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty() || s.toString().equals("")) {
                    tvCostMoney.setText("0");
                } else if (Double.parseDouble(s.toString()) > 999999.99) {
                    tvCostMoney.setText("999999.99");
                    CustomToast.makeText(YSLFastConsumeActivity.this, "只能输入低于100万金额！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initViews() {
        tvBack = findViewById(R.id.tv_back);

        tvMoney = findViewById(R.id.tv_fast_consume_show_money);
        tvCostMoney = findViewById(R.id.tv_fast_consume_ys_money);
        button0 = findViewById(R.id.btn_keyboard_0);
        button1 = findViewById(R.id.btn_keyboard_1);
        button2 = findViewById(R.id.btn_keyboard_2);
        button3 = findViewById(R.id.btn_keyboard_3);
        button4 = findViewById(R.id.btn_keyboard_4);
        button5 = findViewById(R.id.btn_keyboard_5);
        button6 = findViewById(R.id.btn_keyboard_6);
        button7 = findViewById(R.id.btn_keyboard_7);
        button8 = findViewById(R.id.btn_keyboard_8);
        button9 = findViewById(R.id.btn_keyboard_9);
        button00 = findViewById(R.id.btn_keyboard_00);
        buttonadd = findViewById(R.id.l_layout_add_key);
        buttonok = findViewById(R.id.btn_keyboard_confirm);
        btnadd = findViewById(R.id.btn_key_add_key);
        lDelet = findViewById(R.id.btn_keyboard_delete);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_back:
                YSLFastConsumeActivity.this.finish();
                break;

            case R.id.btn_keyboard_00:
                if (!isNull(mEditContentBuilder) && !mEditContentBuilder.toString().contains(".")) {
                    mEditContentBuilder.append(".");
                }
                if (isNull(mEditContentBuilder)) {
                    mEditContentBuilder.append("0.");
                }
                if (!isNull(mEditContentBuilder)) {
                    String[] str = mEditContentBuilder.toString().split("\\+");
                    if (!str[str.length - 1].contains(".")) {
                        mEditContentBuilder.append(".");
                    }
                }
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_0:
                mEditContentBuilder.append("0");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_1:
                mEditContentBuilder.append("1");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_2:
                mEditContentBuilder.append("2");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_3:
                mEditContentBuilder.append("3");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_4:
                mEditContentBuilder.append("4");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_5:
                mEditContentBuilder.append("5");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_6:
                mEditContentBuilder.append("6");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_7:
                mEditContentBuilder.append("7");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_8:
                mEditContentBuilder.append("8");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_9:
                mEditContentBuilder.append("9");
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_key_add_key:
                if (!isNull(mEditContentBuilder) && !"+".equals(String.valueOf(mEditContentBuilder.toString().charAt(mEditContentBuilder.length() - 1)))) {
                    mEditContentBuilder.append("+");
                }
                myHandler.sendEmptyMessage(1);
                break;

            case R.id.l_layout_add_key:
                if (!isNull(mEditContentBuilder) && !"+".equals(String.valueOf(mEditContentBuilder.toString().charAt(mEditContentBuilder.length() - 1)))) {
                    mEditContentBuilder.append("+");
                }
                myHandler.sendEmptyMessage(1);
                break;
            case R.id.btn_keyboard_confirm:
                if (YSLUtils.isFastClick()) {
                    String moneyFlag = tvCostMoney.getText().toString();
                    if ("".equals(moneyFlag) || "0.0".equals(moneyFlag) || "0.00".equals(moneyFlag) || "0".equals(moneyFlag)) {
                        CustomToast.makeText(this, "请输入消费金额!", Toast.LENGTH_SHORT).show();
                    } else {
                        getOrder();
                    }
                }
                break;
            case R.id.btn_keyboard_delete:
                if (!isNull(mEditContentBuilder)) {
                    mEditContentBuilder.deleteCharAt(mEditContentBuilder.length() - 1);
                }
                myHandler.sendEmptyMessage(1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //支付完成后返回该界面，清空支付数据
        if (requestCode==9787){
            mTempVar=0;

            mOrderMoney=0;
            mEditContentBuilder=new StringBuilder("");
            tvMoney.setText("");
            tvCostMoney.setText("");
        }
    }

    /**
     * 获取订单号，并跳转activity
     */
    public void getOrder() {
        String MDZZ = null;
        String goc = CreateOrder.createOrder("KS");
        double totalAmount = Double.parseDouble(tvCostMoney.getText().toString());
        //跳转到支付确认页面
        Intent toPayConfirmIntent = new Intent(YSLFastConsumeActivity.this, PayConfirmActivity.class);

        toPayConfirmIntent.putExtra(MyApplication.SPEND_MONEY, totalAmount);//折后金额
        toPayConfirmIntent.putExtra("order_money", Double.parseDouble(tvCostMoney.getText().toString()));//订单金额
        toPayConfirmIntent.putExtra(MyApplication.ORDER_NO, goc);//订单号
        toPayConfirmIntent.putExtra(MyApplication.PAGE_FLAG, "KSXF");//标志，用来判断是那个页面跳转到的支付确认页面
        toPayConfirmIntent.putExtra("MDZZ", MDZZ);
        toPayConfirmIntent.putExtra("isKSXF", true);
        startActivityForResult(toPayConfirmIntent,9787);
    }

    /**
     * 判断StringBuilder是否为空
     * @param sb
     * @return
     */
    public static boolean isNull(StringBuilder sb) {
        if (sb == null) {
            return true;
        }
        if (sb.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 计算显示金额
     *
     * @param str
     */
    private void calculate(String str) {
        mTempVar = 0;
        if (!str.isEmpty()) {
            if (".".equals(str.indexOf(str.length() - 1))) {
                str = str.substring(0, str.length() - 2);
                if (str.contains("+")) {
                    String[] s = str.split("\\+");
                    for (int i = 0; i < s.length; i++) {
                        mTempVar = DoubleMathUtil.add(mTempVar, Double.parseDouble(s[i]));
                    }
                } else {
                    mTempVar = Double.parseDouble(str);
                }
            } else {
                if (str.contains("+")) {
                    String[] s = str.split("\\+");
                    for (int i = 0; i < s.length; i++) {
                        mTempVar = DoubleMathUtil.add(mTempVar, Double.parseDouble(s[i]));
                    }
                } else {
                    mTempVar = Double.parseDouble(str);
                }
            }
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tvMoney.setText(mEditContentBuilder.toString() + "");
                    calculate(tvMoney.getText().toString());
                    mOrderMoney = mTempVar;
                    tvCostMoney.setText(Decima2KeeplUtil.stringToDecimal(mOrderMoney + ""));
                    //tvMoney.setText(mYsMoney + "");
                    break;
            }
        }
    }
}
