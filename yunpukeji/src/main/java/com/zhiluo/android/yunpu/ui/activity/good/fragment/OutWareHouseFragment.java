package com.zhiluo.android.yunpu.ui.activity.good.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.ProviderBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.ui.activity.good.GoodsWarehousingActivity;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.InOutGoodsAdapter;
import com.zhiluo.android.yunpu.ui.activity.good.bean.OutWareHouseBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;
import com.zhiluo.android.yunpu.yslutils.YSLPayPopWindow;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-05-17.
 */

@SuppressLint("ValidFragment")
public class OutWareHouseFragment extends Fragment implements View.OnClickListener, YSLPayPopWindow.OnItemClickListener, YSLPayPopWindow.OnDismissListener {
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.cb_down_up)
    CheckBox cbDownUp;
    @Bind(R.id.tv_count)
    TextView tvCount;
    @Bind(R.id.l_med)
    LinearLayout lMed;
    private TextView tvOderNumber;//订单号
    private TextView tvSupplierName;//供应商
    private TextView tvPayType;//支付方式
    private TextView tvTotalPaice;//合计金额
    private TextView tvInWareHouseTime;//出库时间
    private TextView tvComfirm;//确定

    private EditText edRemake;//备注
    private EditText edOperator;//操作人员
    private Dialog chooseDialog;
    private ProviderBean bean;
    private List<String> providerList = new ArrayList<>();
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodList;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> DataBean;
    private List<String> payTypeList = new ArrayList<>();
    private Dialog dateDialog;
    private Dialog datesDialog;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean bank;//银行卡支付开关
    private boolean cash;//现金支付开关
    private boolean zfb;//支付宝记账开关
    private boolean sm, wx;//微信记账开关
    private boolean other;//其他支付开关
    private SweetAlertDialog mAlertDialog;
    private Myhandler myhandler = new Myhandler();
    private double mTotalPrice;//总金额
    private Bundle bundle;
    private String mPayTypeCode;
    private YSLPayPopWindow mPopWindow;
    private String mPayName = "现金支付";
    private String mPayCode = "XJZF";
    private Handler mhandler;
    private int outputType = 2;
    private InOutGoodsAdapter mGoodsPayAdapter;

    @SuppressLint("ValidFragment")
    public OutWareHouseFragment(Handler mhandler) {
        this.mhandler = mhandler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.outwarehousefragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mhandler.sendEmptyMessage(2);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        getProvider();
        initPayType();
        Lisenter();
    }

    private void Lisenter() {

        cbDownUp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    recyclerView.setVisibility(View.GONE);
                    lMed.setVisibility(View.GONE);

                    cbDownUp.setText("展 开");
                } else {
                    recyclerView.setVisibility(View.VISIBLE);
                    lMed.setVisibility(View.VISIBLE);
                    cbDownUp.setText("收 起");
                }
            }
        });
    }
    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
    }

    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_in_warehouse_time://出库时间选择
                if (tvInWareHouseTime.getText().toString().isEmpty()) {
                    showReallyDateDialog(DateUtil.getReallyDateForString(DateTimeUtil.getReallyTimeNow()), tvInWareHouseTime);
//                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvInWareHouseTime);
                } else {
                    showReallyDateDialog(DateUtil.getReallyDateForString(tvInWareHouseTime.getText().toString()), tvInWareHouseTime);
//                    showDateDialog(DateUtil.getDateForString(tvInWareHouseTime.getText().toString()), tvInWareHouseTime);
                }
                break;
            case R.id.tv_pay_type://收款方式选择
//                showPayTypeDialog(payTypeList, tvPayType);
                showPayPop();
                break;
            case R.id.tv_supplier_name://出库类型选择
                showSupplierDialog(providerList, tvSupplierName);
                break;
            case R.id.tv_comfirm://提交
                outWareHouse();
                break;
        }
    }

    /**
     * 出库确认
     */
    private void outWareHouse() {
        RequestParams params = new RequestParams();
        if (TextUtils.isEmpty(tvSupplierName.getText())) {
            CustomToast.makeText(getContext(), "请选择出库类型", Toast.LENGTH_SHORT).show();
        } else {
            params.put("OutCome", Decima2KeeplUtil.stringToDecimal(mTotalPrice +""));
            params.put("PayType", mPayCode);
            params.put("OutType", outputType);
            params.put("OtherMoney", 0.00);
            params.put("Remark", edRemake.getText().toString());
            params.put("Time", tvInWareHouseTime.getText().toString());
            params.put("Hander", edOperator.getText().toString());
            params.put("SO_TrackingNo", tvOderNumber.getText().toString());
            for (int i = 0; i < mGoodList.size(); i++) {
                GoodsCheckResponseByType.DataBean.DataListBean bean = mGoodList.get(i);
                params.put("ProductList[" + i + "][PM_GID]", bean.getGID());
                params.put("ProductList[" + i + "][PM_Code]", bean.getPM_Code());
                params.put("ProductList[" + i + "][PM_Name]", bean.getPM_Name());
                params.put("ProductList[" + i + "][PM_Metering]", bean.getPM_Metering());
                params.put("ProductList[" + i + "][PM_Modle]", bean.getPM_Modle());
                params.put("ProductList[" + i + "][Number]", bean.getNum());
                params.put("ProductList[" + i + "][SimpleCode]", bean.getPM_SimpleCode());
                params.put("ProductList[" + i + "][PT_ID]", bean.getPT_ID());
                params.put("ProductList[" + i + "][PM_Description]", bean.getPM_Description());
                params.put("ProductList[" + i + "][PM_Brand]", bean.getPM_Brand());
                params.put("ProductList[" + i + "][Price]", bean.getPM_PurchasePrice());
                params.put("ProductList[" + i + "][Total]", DoubleMathUtil.mul(bean.getPM_PurchasePrice(), bean.getNum()));
                params.put("ProductList[" + i + "][SPD_GID]",bean.getSPD_GID() );
            }

            CallBack callBack = new CallBack() {
                @Override
                public void onSuccess(String responseString, Gson gson) {
                    final OutWareHouseBean ck_success_bean = CommonFun.JsonToObj(responseString, OutWareHouseBean.class);
                    mAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                    mAlertDialog.setTitleText("成功");
                    mAlertDialog.setContentText("出库成功！");
                    mAlertDialog.setConfirmText("确认");
                    mAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mAlertDialog.dismiss();
                            new HttpGetPrintContents(getActivity(), MyApplication.CK_PRINT_TIMES, ck_success_bean.getData()).CK();
                            ActivityManager.getInstance().exit();
                            Intent intent = new Intent(getActivity(), GoodsWarehousingActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    });
                    mAlertDialog.show();
                }

                @Override
                public void onFailure(String msg) {
                    mAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
                    mAlertDialog.setTitleText("提示");
                    mAlertDialog.setContentText(msg + "！");
                    mAlertDialog.setConfirmText("了解");
                    mAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mAlertDialog.dismiss();
                        }
                    });
                    mAlertDialog.show();

                    Li("sssss---" + msg);
                }
            };
            callBack.setLoadingAnimation(getContext(), "正在出库...", false);
            HttpHelper.post(getContext(), HttpAPI.API().OUT_STOCK, params, callBack);

        }

    }


    /**
     * 获取开关状态
     */
    private void initPayType() {
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {
                    if ("103".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            bank = true;
                        }
                    }
                    if ("101".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            cash = true;
                        }
                    }

                    //支付宝记账
                    if ("106".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            zfb = true;
                        }
                    }
                    //微信记账
                    if ("105".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            wx = true;
                        }
                    }
                    //扫码
                    if ("111".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            sm = true;
                        }
                    }
                    //其他支付
                    if ("113".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            other = true;
                        }
                    }
                }
            }
        } else {
            cash = true;
        }
        if (cash) {
            payTypeList.add("现金支付");
        }
        if (bank) {
            payTypeList.add("银联支付");
        }
        if (zfb) {
            payTypeList.add("支付宝记账");
        }
        if (wx) {
            payTypeList.add("微信记账");
        }
        if (sm) {
            payTypeList.add("扫码支付");
        }
        if (other) {
            payTypeList.add("其他支付");
        }
    }

    /**
     * @param date      ,
     * @param mTextView ,
     *                  选择日期
     */
    private void showReallyDateDialog(List<Integer> date, final TextView mTextView) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(getActivity());
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                mTextView.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])) + " "
                        + (dates[3] > 9 ? dates[3] : ("0" + dates[3]))
                        + ":"
                        + (dates[4] > 9 ? dates[4] : ("0" + dates[4]))
                        + ":"
                        + (dates[5] > 9 ? dates[5] : ("0" + dates[5]))
                );
            }

            @Override
            public void onCancel() {
                mTextView.setText("");
            }
        }).setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1)
                .setSelecthour(date.get(3) - 1)
                .setSelectmin(date.get(4) - 1)
                .setSelectsec(date.get(5) - 1)
                .setIsdetail(1);

        datesDialog = builder.create();
        datesDialog.show();
    }

    /**
     * 出库类型
     */
    private void getProvider() {
        String mAccountName = (String) uSharedPreferencesUtiles.get(getContext(), "mAcountname", "");
        edOperator.setText(mAccountName);
        providerList.add("采购退货");//2 采购退货  0 商品报废 4 其它
        providerList.add("商品报废");
        providerList.add("其它");
    }


    /**
     * 选择出库类型
     */
    private void showSupplierDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mlist.get(i).equals(mTextView.getText().toString())) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(getContext());
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        switch (position) {
                            case 0:
                                outputType = 2;
                                tvPayType.setEnabled(true);
                                break;
                            case 1:
                                outputType = 0;
                                //商品报废没有支付方式
                                mPayName = "";
                                mPayCode = "";
                                tvPayType.setText("");
                                tvPayType.setEnabled(false);
                                break;
                            case 2:
                                outputType = 4;
                                tvPayType.setEnabled(true);
                                break;
                        }

                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    private void initViews(View view) {
        tvOderNumber = view.findViewById(R.id.tv_oder_number);
        tvSupplierName = view.findViewById(R.id.tv_supplier_name);
        tvPayType = view.findViewById(R.id.tv_pay_type);
        tvTotalPaice = view.findViewById(R.id.tv_total_price);
        tvInWareHouseTime = view.findViewById(R.id.tv_in_warehouse_time);
        tvComfirm = view.findViewById(R.id.tv_comfirm);

        edRemake = view.findViewById(R.id.ed_remake);
        edOperator = view.findViewById(R.id.ed_operator);
        tvSupplierName.setOnClickListener(this);
        tvPayType.setOnClickListener(this);
        tvInWareHouseTime.setOnClickListener(this);
        tvComfirm.setOnClickListener(this);
        tvOderNumber.setText(CreateOrder.createOrder("CK"));

        tvSupplierName.setText("采购退货");
        tvPayType.setText("现金支付");
        tvInWareHouseTime.setText(DateTimeUtil.getReallyTimeNow());

    }

    /**
     * 显示支付弹窗
     */
    private void showPayPop() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_kuaijieshoukuan, null);
        mPopWindow = new YSLPayPopWindow(getContext(), mTotalPrice + "",mSwitchEntity);
        mPopWindow.setOnItemClickListener(this);
        mPopWindow.setOnDismissListener(this);
        YSLUtils.setBackgroundAlpha(0.5f, getContext());
        mPopWindow.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void onDismiss() {
        YSLUtils.setBackgroundAlpha(1.0f, getContext());
    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.l_cash:
                if (cash) {
                    mPayName = "现金支付";
                    mPayCode = "XJZF";
                } else {
                    CustomToast.makeText(getContext(), "现金支付开关未打开", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, getContext());
                break;
            case R.id.l_yue:
                CustomToast.makeText(getContext(), "出入库余额不可用！", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, getContext());
                break;
            case R.id.r_union:
                CustomToast.makeText(getContext(), "出入库联合支付不可用！", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, getContext());
                break;
            case R.id.l_card:
                if (bank) {
                    mPayName = "银联支付";
                    mPayCode = "YLZF";
                } else {
                    CustomToast.makeText(getContext(), "银联支付已关闭", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, getContext());
                break;
            //扫码支付
            case R.id.l_saoma:
                CustomToast.makeText(getContext(), "出入库扫码不可用！", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, getContext());
                break;
            case R.id.tv_cancel:
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, getContext());
                break;
            case R.id.l_weixin:
                if (wx) {
                    mPayName = "微信记账";
                    mPayCode = "WX_JZ";
                } else {
                    CustomToast.makeText(getContext(), "微信记账未开启，请到PC端去开启！", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, getContext());
                break;
            case R.id.l_zhifubao:
                if (zfb) {
                    mPayName = "支付宝记账";
                    mPayCode = "ZFB_JZ";
                } else {
                    CustomToast.makeText(getContext(), "支付宝记账未开启，请到PC端去开启！", Toast.LENGTH_SHORT).show();
                }
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, getContext());
                break;
            case R.id.l_other:
                CustomToast.makeText(getContext(), "出入库其他支付不可用！", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, getContext());
                break;
        }
        tvPayType.setText(mPayName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public class Myhandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    try {
                        DataBean = (List<GoodsCheckResponseByType.DataBean.DataListBean>) msg.obj;
                        double n = 0;
                        double l = 0;
                        for (int k = 0; k < DataBean.size(); k++) {

                            n += DataBean.get(k).getPM_PurchasePrice() * DataBean.get(k).getNum();
                            l += DataBean.get(k).getNum();
                        }
                        mTotalPrice = n;
                        tvTotalPaice.setText(Decima2KeeplUtil.stringToDecimal(n + ""));
                        tvCount.setText("合计数量：" + Decima2KeeplUtil.stringToDecimal(l + "") + "件");
                    } catch (Exception e) {

                    }
                    break;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(List<GoodsCheckResponseByType.DataBean.DataListBean> bean) {
        mGoodList = bean;
        initAdapter(mGoodList, myhandler);
        double n = 0;
        double l = 0;
        for (int k = 0; k < mGoodList.size(); k++) {

            n += mGoodList.get(k).getPM_PurchasePrice() * mGoodList.get(k).getNum();
            l += mGoodList.get(k).getNum();
        }
        mTotalPrice = n;
        tvTotalPaice.setText(Decima2KeeplUtil.stringToDecimal(n + ""));
        tvCount.setText("合计数量：" + Decima2KeeplUtil.stringToDecimal(l + "") + "件");

    }

    private void initAdapter(List<GoodsCheckResponseByType.DataBean.DataListBean> goodsBeanList, OutWareHouseFragment.Myhandler changeHandler) {

        if (mGoodsPayAdapter != null) {
            mGoodsPayAdapter = null;
        }
        mGoodsPayAdapter = new InOutGoodsAdapter(getActivity(), goodsBeanList, changeHandler, true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setSmoothScrollbarEnabled(false);
        recyclerView.setLayoutManager(manager);
        mGoodsPayAdapter.setHasStableIds(true);
        recyclerView.setAdapter(mGoodsPayAdapter);
    }
}
