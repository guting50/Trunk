package com.zhiluo.android.yunpu.statistics.handover;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.handduty.HandDutyActivity;
import com.zhiluo.android.yunpu.ui.activity.handduty.HandDutyAdapter;
import com.zhiluo.android.yunpu.ui.activity.handduty.HandDutyListBean;
import com.zhiluo.android.yunpu.ui.activity.handduty.HandDutyReportAdapter;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2018-11-29.
 */

public class HandOverReportDetail extends BaseActivity {
    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_pay_confirm_title)
    RelativeLayout rlPayConfirmTitle;
    @Bind(R.id.listview)
    ExpandableListView listview;
    @Bind(R.id.tv_pay_confirm_order)
    EditText tvPayConfirmOrder;
    @Bind(R.id.r_layout_order)
    RelativeLayout rLayoutOrder;
    @Bind(R.id.tv_pay_confirm_receivable)
    TextView tvPayConfirmReceivable;
    @Bind(R.id.l_oder_moenty)
    RelativeLayout lOderMoenty;
    @Bind(R.id.et_pay_confirm_discount_money)
    EditText etPayConfirmDiscountMoney;
    @Bind(R.id.et_pay_confirm_discount)
    EditText etPayConfirmDiscount;
    @Bind(R.id.em_goods)
    TextView emGoods;
    @Bind(R.id.tv_pay_confirm_employee)
    TextView tvPayConfirmEmployee;
    @Bind(R.id.rl_pay_confirm_employee)
    RelativeLayout rlPayConfirmEmployee;
    @Bind(R.id.rl_pay_way)
    RelativeLayout rlPayWay;
    @Bind(R.id.l_shoukuan)
    LinearLayout lShoukuan;
    @Bind(R.id.img_g)
    TextView imgG;


    private HandOverReportBean.DataBean.DataListBean mBean;
    private String lastmoney;
    private List<HandDutyListBean> handDutyListBean;
    private HandDutyReportAdapter adapter;
    private double tatol;
    public int[] isExpand = new int[] {0,0,0,0,0,0,0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_over_duty);
        ButterKnife.bind(this);
        initView();
        loadData();
        setLisenter();

    }


    private void setLisenter() {
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 通过监听展开和关闭事件动态设置高度

        listview.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override

            public void onGroupExpand(int groupPosition) {

                isExpand[groupPosition] = 1;

                setListHeight(listview, adapter);

            }

        });

        listview.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override

            public void onGroupCollapse(int groupPosition) {

                isExpand[groupPosition] = 0;

                setListHeight(listview, adapter);
            }

        });

    }

    private void setAdapter() {
        isExpand = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        adapter = new HandDutyReportAdapter(handDutyListBean, this);
        listview.setGroupIndicator(null);
        listview.setAdapter(adapter);
        setListHeight(listview, adapter);
    }

    public void setListHeight(ExpandableListView listView, HandDutyReportAdapter listAdapter) {

        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        int total = 0;
        View listItem;

        for (int i = 0; i < listAdapter.getGroupCount(); i++) {
            listItem = listAdapter.getGroupView(i, false, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
            total += (listAdapter.getChildrenCount(0) - 1);
        }

        for (int i = 0; i < isExpand.length; i++) {
            if (isExpand[i] == 1)
                for (int j = 0; j < listAdapter.getChildrenCount(i); j++) {
                    listItem = listAdapter.getChildView(i, j, false, null, listView);
                    listItem.measure(0, 0);
                    totalHeight += listItem.getMeasuredHeight();
                    total += (listAdapter.getChildrenCount(i) - 1);
                }
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * total);
        listView.setLayoutParams(params);

    }

    private void loadData() {
        lastmoney = getIntent().getStringExtra("lastmoney");
        mBean = (HandOverReportBean.DataBean.DataListBean) getIntent().getSerializableExtra("handover");
        editData();
        setAdapter();

        tvPayConfirmOrder.setText(lastmoney);
        tatol = Double.parseDouble(lastmoney) + mBean.getSA_AllTotal();
        tvPayConfirmReceivable.setText(Decima2KeeplUtil.stringToDecimal(tatol + "")+"");
        etPayConfirmDiscountMoney.setText(mBean.getSA_HandInBusiness() + "");
        etPayConfirmDiscount.setText(mBean.getSA_IssuedBusiness() + "");
        tvPayConfirmEmployee.setText(mBean.getSA_SuccessionName());


    }

    private void initView() {
        lShoukuan.setVisibility(View.GONE);
        imgG.setVisibility(View.GONE);
        rLayoutOrder.setBackgroundColor(Color.WHITE);
        lOderMoenty.setBackgroundColor(Color.WHITE);
        etPayConfirmDiscountMoney.setEnabled(false);
        etPayConfirmDiscountMoney.setHint("");
        etPayConfirmDiscount.setEnabled(false);
        etPayConfirmDiscount.setHint("");
    }

    private void editData() {

        handDutyListBean = new ArrayList<>();
        HandDutyListBean bean1 = new HandDutyListBean();
        bean1.setName("会员开卡");
        bean1.setCash(mBean.getSA_OpenCardCash());
        bean1.setYue(0.00);
        bean1.setUnion(mBean.getSA_OpenCardUnion());
        bean1.setWx(mBean.getSA_OpenCardWeChatpay());
        bean1.setZfb(mBean.getSA_OpenCardAlipaypay());
        bean1.setJf(0.00);
        bean1.setYhj(0.00);
        bean1.setSm(0.00);
        bean1.setQt(mBean.getSA_OpenCardOtherpay());
        bean1.setMoling(0.00);
        bean1.setReture(0.00);
        bean1.setTotal(mBean.getSA_OpenCardTotal());
        handDutyListBean.add(bean1);

        HandDutyListBean bean2 = new HandDutyListBean();
        bean2.setName("会员充值");
        bean2.setCash(mBean.getSA_RechargeCash());
        bean2.setYue(0.00);
        bean2.setUnion(mBean.getSA_RechargeUnion());
        bean2.setWx(mBean.getSA_RechargeWeChatpay());
        bean2.setZfb(mBean.getSA_RechargeAlipaypay());
        bean2.setJf(0.00);
        bean2.setYhj(0.00);
        bean2.setSm(mBean.getSA_RechargeBarCode());
        bean2.setQt(mBean.getSA_RechargeOtherpay());
        bean2.setMoling(mBean.getSA_RechargeEraseZero());
        bean2.setReture(0.00);
        bean2.setTotal(mBean.getSA_RechargeTotal());
        handDutyListBean.add(bean2);

        HandDutyListBean bean3 = new HandDutyListBean();
        bean3.setName("会员充次");
        bean3.setCash(mBean.getSA_ChargeCash());
        bean3.setYue(mBean.getSA_ChargeBalance());
        bean3.setUnion(mBean.getSA_ChargeUnion());
        bean3.setWx(mBean.getSA_ChargeWeChatpay());
        bean3.setZfb(mBean.getSA_ChargeAlipaypay());
        bean3.setJf(mBean.getSA_ChargeIntegral());
        bean3.setYhj(mBean.getSA_ChargeCoupon());
        bean3.setSm(mBean.getSA_ChargeBarCode());
        bean3.setQt(mBean.getSA_ChargeOtherpay());
        bean3.setMoling(mBean.getSA_ChargeEraseZero());
        bean3.setReture(0.00);
        bean3.setTotal(mBean.getSA_ChargeTotal());
        handDutyListBean.add(bean3);

        HandDutyListBean bean4 = new HandDutyListBean();
        bean4.setName("会员延期");
        bean4.setCash(mBean.getSA_DelayCash());
        bean4.setYue(mBean.getSA_DelayBalance());
        bean4.setUnion(mBean.getSA_DelayUnion());
        bean4.setWx(mBean.getSA_DelayWeChatpay());
        bean4.setZfb(mBean.getSA_DelayWeAlipaypay());
        bean4.setJf(mBean.getSA_DelayIntegral());
        bean4.setYhj(mBean.getSA_DelayCoupon());
        bean4.setSm(mBean.getSA_DelayBarCode());
        bean4.setQt(mBean.getSA_DelayOtherpay());
        bean4.setMoling(mBean.getSA_DelayEraseZero());
        bean4.setReture(0.00);
        bean4.setTotal(mBean.getSA_DelayTotal());
        handDutyListBean.add(bean4);

        HandDutyListBean bean5 = new HandDutyListBean();
        bean5.setName("商品消费");
        bean5.setCash(mBean.getSA_ConsumptionCash());
        bean5.setYue(mBean.getSA_ConsumptionBalance());
        bean5.setUnion(mBean.getSA_ConsumptionUnionpay());
        bean5.setWx(mBean.getSA_ConsumptionWeChatpay());
        bean5.setZfb(mBean.getSA_ConsumptionAlipaypay());
        bean5.setJf(mBean.getSA_ConsumptionIntegral());
        bean5.setYhj(mBean.getSA_ConsumptionCoupon());
        bean5.setSm(mBean.getSA_ConsumptionBarCode());
        bean5.setQt(mBean.getSA_ConsumptionOtherpay());
        bean5.setMoling(mBean.getSA_ConsumptionEraseZero());
        bean5.setReture(0.00);
        bean5.setTotal(mBean.getSA_ConsumptionTotal());
        handDutyListBean.add(bean5);

        HandDutyListBean bean6 = new HandDutyListBean();
        bean6.setName("快速消费");
        bean6.setCash(mBean.getSA_FastConsumptionCash());
        bean6.setYue(mBean.getSA_FastConsumptionBalance());
        bean6.setUnion(mBean.getSA_FastConsumptionUnion());
        bean6.setWx(mBean.getSA_FastConsumptionWeChatpay());
        bean6.setZfb(mBean.getSA_FastConsumptionAlipaypay());
        bean6.setJf(mBean.getSA_FastConsumptionIntegral());
        bean6.setYhj(mBean.getSA_FastConsumptionCoupon());
        bean6.setSm(mBean.getSA_FastConsumptionBarCode());
        bean6.setQt(mBean.getSA_FastConsumptionOtherpay());
        bean6.setMoling(mBean.getSA_FastConsumptionEraseZero());
        bean6.setReture(0.00);
        bean6.setTotal(mBean.getSA_FastConsumptionTotal());
        handDutyListBean.add(bean6);

//        HandDutyListBean bean7 = new HandDutyListBean();
//        bean7.setName("套餐消费");
//        bean7.setCash(mBean.getSA_ComboCash());
//        bean7.setYue(mBean.getSA_ComboBalance());
//        bean7.setUnion(mBean.getSA_ComboUnion());
//        bean7.setWx(mBean.getSA_ComboWeChatpay());
//        bean7.setZfb(mBean.getSA_ComboAlipaypay());
//        bean7.setJf(mBean.getSA_ComboIntegral());
//        bean7.setYhj(mBean.getSA_ComboCoupon());
//        bean7.setSm(mBean.getSA_ComboBarCode());
//        bean7.setQt(mBean.getSA_ComboOtherpay());
//        bean7.setMoling(0.00);
//        bean7.setReture(0.00);
//        bean7.setTotal(mBean.getSA_ComboTotal());
//        handDutyListBean.add(bean7);

        HandDutyListBean bean8 = new HandDutyListBean();
        bean8.setName("计时消费");
        bean8.setCash(mBean.getSA_TimeCash());
        bean8.setYue(mBean.getSA_TimeBalance());
        bean8.setUnion(mBean.getSA_TimeUnion());
        bean8.setWx(mBean.getSA_TimeWeChatpay());
        bean8.setZfb(mBean.getSA_TimeAlipaypay());
        bean8.setJf(mBean.getSA_TimeIntegral());
        bean8.setYhj(mBean.getSA_TimeCoupon());
        bean8.setSm(mBean.getSA_TimeBarCode());
        bean8.setQt(mBean.getSA_TimeOtherpay());
        bean8.setMoling(mBean.getSA_TimeEraseZero());
        bean8.setReture(0.00);
        bean8.setTotal(mBean.getSA_TimeTotal());
        handDutyListBean.add(bean8);

        HandDutyListBean bean9 = new HandDutyListBean();
        bean9.setName("房台消费");
        bean9.setCash(mBean.getSA_HouseCash());
        bean9.setYue(mBean.getSA_HouseBalance());
        bean9.setUnion(mBean.getSA_HouseUnion());
        bean9.setWx(mBean.getSA_HouseWeChatpay());
        bean9.setZfb(mBean.getSA_HouseAlipaypay());
        bean9.setJf(mBean.getSA_HouseIntegral());
        bean9.setYhj(mBean.getSA_HouseCoupon());
        bean9.setSm(mBean.getSA_HouseBarCode());
        bean9.setQt(mBean.getSA_HouseOtherpay());
        bean9.setMoling(mBean.getSA_HouseEraseZero());
        bean9.setReture(0.00);
        bean9.setTotal(mBean.getSA_HouseTotal());
        handDutyListBean.add(bean9);

        HandDutyListBean bean10 = new HandDutyListBean();
        bean10.setName("商品退货");
        bean10.setCash(mBean.getSA_ReturnGoodsCash());
        bean10.setYue(mBean.getSA_ReturnGoodsBalance());
        bean10.setUnion(0.00);
        bean10.setWx(mBean.getSA_ReturnGoodsWechat());
        bean10.setZfb(mBean.getSA_ReturnGoodsAlipay());
        bean10.setJf(0.00);
        bean10.setYhj(0.00);
        bean10.setSm(0.00);
        bean10.setQt(0.00);
        bean10.setMoling(0.00);
        bean10.setReture(0.00);
        bean10.setTotal(mBean.getSA_ReturnGoodsTotal());
        handDutyListBean.add(bean10);

        HandDutyListBean bean12 = new HandDutyListBean();
        bean12.setName("一键加油");
        bean12.setCash(mBean.getSA_RefuelCash());
        bean12.setYue(mBean.getSA_RefuelBalance());
        bean12.setUnion(mBean.getSA_RefuelUnionpay());
        bean12.setWx(mBean.getSA_RefuelWeChatpay());
        bean12.setZfb(mBean.getSA_RefuelAlipaypay());
        bean12.setJf(mBean.getSA_RefuelIntegral());
        bean12.setYhj(mBean.getSA_RefuelCoupon());
        bean12.setSm(mBean.getSA_RefuelBarCode());
        bean12.setQt(mBean.getSA_RefuelOtherpay());
        bean12.setMoling(mBean.getSA_RefuelEraseZero());
        bean12.setReture(0.00);
        bean12.setTotal(mBean.getSA_RefuelTotal());
        handDutyListBean.add(bean12);

        HandDutyListBean bean11 = new HandDutyListBean();
        bean11.setName("总收入");
        bean11.setCash(mBean.getSA_CashTotal());
        bean11.setYue(mBean.getSA_BalanceTotal());
        bean11.setUnion(mBean.getSA_UnionTotal());
        bean11.setWx(mBean.getSA_WeChatpayTotal());
        bean11.setZfb(mBean.getSA_AlipaypayTotal());
        bean11.setJf(mBean.getSA_IntegralTotal());
        bean11.setYhj(mBean.getSA_CouponTotal());
        bean11.setSm(mBean.getSA_BarCodeTotal());
        bean11.setQt(mBean.getSA_OtherpayTotal());
        bean11.setTotal(mBean.getSA_AllTotal());
        handDutyListBean.add(bean11);
    }

}
