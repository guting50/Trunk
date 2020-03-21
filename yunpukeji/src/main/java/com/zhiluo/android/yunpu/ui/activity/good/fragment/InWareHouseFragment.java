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
import android.text.TextUtils;
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
import com.zhiluo.android.yunpu.ui.activity.good.ChoiceProviderActivity;
import com.zhiluo.android.yunpu.ui.activity.good.GoodsWarehousingActivity;
import com.zhiluo.android.yunpu.ui.activity.good.adapter.InOutGoodsAdapter;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InWareHouseBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
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
public class InWareHouseFragment extends Fragment implements View.OnClickListener, YSLPayPopWindow.OnItemClickListener, YSLPayPopWindow.OnDismissListener {
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
    //    private EditText edInNumber;//入库数
    private TextView tvPayType;//支付方式
    private TextView tvTotalPaice;//合计金额
    private TextView tvInWareHouseTime;//入库时间
    private TextView tvComfirm;//确定
    private EditText edRemake;//备注
    private EditText edOperator;//操作人员
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mGoodList;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> DataBean;
    private List<String> payTypeList = new ArrayList<>();
    private Dialog datesDialog;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean bank;//银行卡支付开关
    private boolean cash;//现金支付开关
    private boolean zfb;//支付宝记账开关
    private boolean sm, wx;//微信记账开关
    private boolean other;//其他支付开关
    private SweetAlertDialog mAlertDialog;
    private Myhandler myhandler;
    private double mStock;//库存
    private double mTotalPrice;//总金额
    private YSLPayPopWindow mPopWindow;
    private String mPayName = "现金支付";
    private String mPayCode = "XJZF";

    private Handler mHandler;
    private ProviderBean.DataBean.DataListBean providerbean;
    private String providerID;

    private InOutGoodsAdapter mGoodsPayAdapter;

    @SuppressLint("ValidFragment")
    public InWareHouseFragment(Handler handler) {
        mHandler = handler;
    }

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgment_inwarehouse, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mHandler.sendEmptyMessage(1);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
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
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_in_warehouse_time://入库时间选择
                if (tvInWareHouseTime.getText().toString().isEmpty()) {
                    showReallyDateDialog(DateUtil.getReallyDateForString(DateTimeUtil.getReallyTimeNow()), tvInWareHouseTime);
                } else {
                    showReallyDateDialog(DateUtil.getReallyDateForString(tvInWareHouseTime.getText().toString()), tvInWareHouseTime);
                }

                break;
            case R.id.tv_pay_type://支付方式选择
                //  showPayTypeDialog(payTypeList, tvPayType);
                showPayPop();
                break;
            case R.id.tv_supplier_name://供应商选择
                Intent intent = new Intent(getActivity(), ChoiceProviderActivity.class);
                startActivityForResult(intent, 888);
                break;
            case R.id.tv_comfirm://提交
                inWareHouse();
                break;
        }
    }

    /**
     * 入库确认
     */
    private void inWareHouse() {

//        if (TextUtils.isEmpty(edInNumber.getText())||Double.parseDouble(edInNumber.getText().toString())==0) {
//            CustomToast.makeText(getContext(), "入库数量必须大于0", Toast.LENGTH_SHORT).show();
//        } else
        if (mPayCode.equals("")) {
            CustomToast.makeText(getContext(), "请选择支付方式", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(tvInWareHouseTime.getText())) {
            CustomToast.makeText(getContext(), "请选择时间", Toast.LENGTH_SHORT).show();
        } else {
            inputStock();
        }
    }


    private void inputStock() {
        RequestParams params = new RequestParams();
        params.put("InCome", mTotalPrice);
        params.put("PayType", mPayCode);
        params.put("InType", 1);
        params.put("SL_GID", providerID == null ? "" : providerID);
        params.put("SL_Name", tvSupplierName.getText().toString());
        params.put("UpDate", tvInWareHouseTime.getText().toString());//不管选择哪一天，都以当前的时分秒为准
        params.put("OtherMoney", 0.00);
        params.put("Hander", edOperator.getText().toString());
        params.put("Remark", edRemake.getText().toString());
        params.put("SI_TrackingNo", tvOderNumber.getText().toString());

        for (int i = 0; i < mGoodList.size(); i++) {
            GoodsCheckResponseByType.DataBean.DataListBean bean = mGoodList.get(i);
            params.put("OrderDetail[" + i + "][PM_GID]", bean.getGID());
            params.put("OrderDetail[" + i + "][PM_Code]", bean.getPM_Code());
            params.put("OrderDetail[" + i + "][PM_Name]", bean.getPM_Name());
            params.put("OrderDetail[" + i + "][PM_Metering]", bean.getPM_Metering());
            params.put("OrderDetail[" + i + "][PM_Modle]", bean.getPM_Modle());
            params.put("OrderDetail[" + i + "][Number]", bean.getNum());
            params.put("OrderDetail[" + i + "][SimpleCode]", bean.getPM_SimpleCode());
            params.put("OrderDetail[" + i + "][PT_ID]", bean.getPT_ID());
            params.put("OrderDetail[" + i + "][PM_Description]", bean.getPM_Description());
            params.put("OrderDetail[" + i + "][PM_Brand]", bean.getPM_Brand());
            params.put("OrderDetail[" + i + "][Price]", bean.getPM_PurchasePrice());
            params.put("OrderDetail[" + i + "][PT_Name]", bean.getPT_Name());
            params.put("OrderDetail[" + i + "][Total]", DoubleMathUtil.mul(bean.getPM_PurchasePrice(), bean.getNum()));
            params.put("OrderDetail[" + i + "][Supplier]", providerID);
        }

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final InWareHouseBean rk_success_bean = CommonFun.JsonToObj(responseString, InWareHouseBean.class);
                mAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                mAlertDialog.setTitleText("成功");
                mAlertDialog.setContentText("入库成功！");
                mAlertDialog.setConfirmText("确认");
                mAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        new HttpGetPrintContents(getActivity(), MyApplication.RK_PRINT_TIMES, rk_success_bean.getData()).RK();
                        mAlertDialog.dismiss();
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
        callBack.setLoadingAnimation(getContext(), "正在入库...", false);
        HttpHelper.post(getContext(), HttpAPI.API().INPUT_STOCK, params, callBack);
    }


    /**
     * 获取开关状态
     */
    private void initPayType() {
        String mAccountName = (String) uSharedPreferencesUtiles.get(getContext(), "mAcountname", "");
        edOperator.setText(mAccountName);

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
        tvOderNumber.setText(CreateOrder.createOrder("CG"));
        myhandler = new Myhandler();
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

    private void initAdapter(List<GoodsCheckResponseByType.DataBean.DataListBean> goodsBeanList, Myhandler changeHandler) {

        if (mGoodsPayAdapter != null) {
            mGoodsPayAdapter = null;
        }
        mGoodsPayAdapter = new InOutGoodsAdapter(getActivity(), goodsBeanList, changeHandler, false);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setSmoothScrollbarEnabled(false);
        recyclerView.setLayoutManager(manager);
        mGoodsPayAdapter.setHasStableIds(true);
        recyclerView.setAdapter(mGoodsPayAdapter);
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
                        tvCount.setText("合计数量：" + Decima2KeeplUtil.stringToDecimal(l + "")+"件");
                    } catch (Exception e) {

                    }
                    break;
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        EventBus.getDefault().unregister(this);
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
        tvCount.setText("合计数量：" + Decima2KeeplUtil.stringToDecimal(l + "")+"件");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2222) {
            if (data != null) {
                providerbean = (ProviderBean.DataBean.DataListBean) data.getSerializableExtra("Provider");
                providerID = providerbean.getGID();
                tvSupplierName.setText(providerbean.getSL_Name());
            }
        }
    }


}
