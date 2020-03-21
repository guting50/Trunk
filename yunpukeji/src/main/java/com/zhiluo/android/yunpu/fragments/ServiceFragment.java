package com.zhiluo.android.yunpu.fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.suke.widget.SwitchButton;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.goods.consume.bean.GoodsModelBean;
import com.zhiluo.android.yunpu.goods.consume.bean.ResultManyModelBean;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsManagerActivity;
import com.zhiluo.android.yunpu.goods.manager.bean.AddGoodsResponseBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.GoodsFiledsBean;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.member.manager.adapter.addGoodsfieldsAdapter;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.popup.ManyChoosePopup;
import com.zhiluo.android.yunpu.ui.activity.ChoiceTypeActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;
import com.zhiluo.android.yunpu.utils.PinyinUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-04-19.
 */

public class ServiceFragment extends Fragment implements View.OnClickListener {
    //    @Bind(R.id.ll_add_good_default_price)
//    LinearLayout llAddGoodDefaultPrice;
//    @Bind(R.id.v_add_good_default_price)
//    View vAddGoodDefaultPrice;
    private EditText edGoodsCode, edGoodsSimpleCode, edGoodsName, edGoodsSalePrice, edGoodsBidPrice, edGoodsStock, edGoodsMetering;
    private EditText edGoodsVipPrice, edGoodsSpecialDiscount, edGoodsMinimumDiscount, edGoodsFixedIntergal;
    private EditText edGoodsBrand, edGoodsStockWarnNum;
    private SwitchButton sbGoodsDiscount;
    private TextView teGoodsType, teGoodsIntergal;
    private TextView edGoodsModel;
    private ImageView imgSaoMa, imgGoodsImgView;
    private String ptid;
    private String goodsname, goodscode, goodstype, goodssimcode;
    private String goodsmetering;
    private String goodsbrand;
    private String goodsmodel;
    private String mGoodsTypeFromActviity;//商品分类
    private String mDefaultPrice;
    private String mMemberPrice;
    private String goodsaleprice;
    private int goodsclass;//商品类型 普通生商品，服务商品，礼品 int型 普通商品 0；服务商品 1，礼品 2

    private Uri imgUri;
    private String mGoodsImageAddress = "/img/goods.png";
    private int mDisSwitch = 0;//折扣开关，0关闭，1打开；
    private Dialog chooseDialog;
    private List<String> intergalList = new ArrayList<>();//积分规则
    private List<String> synStateList = new ArrayList<>();//同步状态 ,同步和不同步两种状态
    private int mPosition = 1;
    private SerReceiver receiver;
    private int flags = 0;
    private int opType = 1;
    private int type;

    private RecyclerView recyclerView;
    private addGoodsfieldsAdapter mgoodsfieldsAdapter;
    private List<GoodsFiledsBean.DataBean> goodsfields;//商品自定义属性
    private LoginUpbean mLoginBean;
    private ChangeHandler changeHandler;
    //多规格列表
    private List<GoodsModelBean> mModelList;
    //组装规格数据
    private List<List<GoodsModelBean>> mManyModelList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        getSpecifications();
        initDats();
        listener();
        if (receiver == null) {
            receiver = new SerReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.Ser_BROADCAST");
        getActivity().registerReceiver(receiver, filter);
    }

    private void initDats() {
        changeHandler = new ChangeHandler();
        goodsfields = (List<GoodsFiledsBean.DataBean>) CacheData.restoreObject("goodsfields");

        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");

//        if (mLoginBean != null) {
//            if (mLoginBean.getData().getAuthorityList() != null && mLoginBean.getData().getAuthorityList().size() > 0) {
//                for (int i = 0; i < mLoginBean.getData().getAuthorityList().size(); i++) {
//                    LoginUpbean.DataBean.AuthorityListBean bean = mLoginBean.getData().getAuthorityList().get(i);
//                    if (bean.getMM_Code().equals("AU002")) { //参考进价
//                        llAddGoodDefaultPrice.setVisibility(View.VISIBLE);
//                        vAddGoodDefaultPrice.setVisibility(View.VISIBLE);
//
//                    }
//                }
//            } else {
//                llAddGoodDefaultPrice.setVisibility(View.GONE);
//                vAddGoodDefaultPrice.setVisibility(View.GONE);
//            }
//
//        }

        if (goodsfields == null) {
            getgoodsfields();//获取自定义属性
        } else {
            initAdapter();
        }


        if (intergalList.size() > 0) {
            intergalList.clear();
        } else {
            String[] intergal = getResources().getStringArray(R.array.intergalrule);
            for (String str : intergal) {
                intergalList.add(str);
            }
        }
        if (synStateList.size() > 0) {
            synStateList.clear();
        } else {
            String[] synstate = getResources().getStringArray(R.array.synstate);
            for (String str : synstate) {
                synStateList.add(str);
            }
        }
        edGoodsSpecialDiscount.setEnabled(false);
        edGoodsSpecialDiscount.setBackgroundColor(getResources().getColor(R.color.ysl_gray));
        edGoodsMinimumDiscount.setEnabled(false);
        edGoodsMinimumDiscount.setBackgroundColor(getResources().getColor(R.color.ysl_gray));

        edGoodsFixedIntergal.setEnabled(false);
        edGoodsFixedIntergal.setBackgroundColor(getResources().getColor(R.color.ysl_gray));

        //金额过滤器
        MoneyInputFilter filter = new MoneyInputFilter(9, true, "最多只能输入9位金额");
        filter.setMessage("最多只能输入9位金额");
        edGoodsVipPrice.setFilters(new InputFilter[]{filter});//会员价
        edGoodsBidPrice.setFilters(new InputFilter[]{filter});//商品进价
        edGoodsSalePrice.setFilters(new InputFilter[]{filter});//商品售价


        teGoodsIntergal.setText("会员等级积分");
    }

    private void initAdapter() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mgoodsfieldsAdapter = new addGoodsfieldsAdapter(getContext(), goodsfields, changeHandler, 3);
        mgoodsfieldsAdapter.setHasStableIds(true);
        recyclerView.setAdapter(mgoodsfieldsAdapter);
    }

    private void listener() {
        sbGoodsDiscount.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    handler.sendEmptyMessage(1);
                    mDisSwitch = 1;
                } else {
                    mDisSwitch = 0;
                    handler.sendEmptyMessage(0);
                }
            }
        });
        edGoodsSpecialDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDisSwitch == 0) {
                    CustomToast.makeText(getContext(), "请打开商品折扣后，再设置", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edGoodsMinimumDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDisSwitch == 0) {
                    CustomToast.makeText(getContext(), "请打开商品折扣后，再设置", Toast.LENGTH_SHORT).show();
                }
            }
        });


        edGoodsName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String temp = s.toString();
                edGoodsSimpleCode.setText(PinyinUtils.getAlpha(temp));


            }
        });
        edGoodsVipPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty() || s.toString().equals("")) {

                } else if (Double.parseDouble(s.toString()) > 999999.99) {
                    edGoodsVipPrice.setText("999999");
                    CustomToast.makeText(getContext(), "会员价格已超上限！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edGoodsBidPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty() || s.toString().equals("")) {

                } else if (Double.parseDouble(s.toString()) > 999999.99) {
                    edGoodsBidPrice.setText("999999");
                    CustomToast.makeText(getContext(), "商品价格已超上限！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edGoodsSalePrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty() || s.toString().equals("")) {

                } else if (Double.parseDouble(s.toString()) > 999999.99) {
                    edGoodsSalePrice.setText("999999");
                    CustomToast.makeText(getContext(), "商品价格已超上限！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0://关闭
                    edGoodsSpecialDiscount.setEnabled(false);
                    edGoodsSpecialDiscount.setBackgroundColor(getResources().getColor(R.color.ysl_gray));
                    edGoodsMinimumDiscount.setEnabled(false);
                    edGoodsMinimumDiscount.setBackgroundColor(getResources().getColor(R.color.ysl_gray));
                    break;
                case 1://打开
                    edGoodsSpecialDiscount.setEnabled(true);
                    edGoodsSpecialDiscount.setBackgroundColor(getResources().getColor(R.color.white));
                    edGoodsMinimumDiscount.setEnabled(true);
                    edGoodsMinimumDiscount.setBackgroundColor(getResources().getColor(R.color.white));
                    break;
            }
        }
    };


    /**
     * 查询会员等级
     */
    private void getgoodsfields() {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(getContext());
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
        params.put("PM_GID", 1);
        client.post(MyApplication.BASE_URL + "CustomFields/GetCustomFields", params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                GoodsFiledsBean reportbean = CommonFun.JsonToObj(responseString, GoodsFiledsBean.class);

                goodsfields = reportbean.getData();
                CacheData.saveObject("goodsfields", goodsfields);//缓存自定义属性到本地

                initAdapter();


            }

            @Override
            public void onFailure(String msg) {
            }
        });

    }

    private void postAddGoods() {
        if (mDisSwitch == 1) {
            if (!edGoodsSpecialDiscount.getText().toString().isEmpty()) {
                if (Double.parseDouble(edGoodsSpecialDiscount.getText().toString()) > 1 || Double.parseDouble(edGoodsSpecialDiscount.getText().toString()) < 0) {
                    CustomToast.makeText(getActivity(), "特价折扣取值范围0-1之间", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if (!edGoodsMinimumDiscount.getText().toString().isEmpty()) {
                if (Double.parseDouble(edGoodsMinimumDiscount.getText().toString()) > 1 || Double.parseDouble(edGoodsMinimumDiscount.getText().toString()) < 0) {
                    CustomToast.makeText(getActivity(), "最低折扣取值范围0-1之间", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }


        RequestParams params = new RequestParams();
        params.put("PT_ID", ptid);//商品所属分类
        params.put("PT_Name", goodstype);//商品类型
        params.put("PM_Code", edGoodsCode.getText().toString());//商品编码
        params.put("PM_Name", goodsname);//商品名字
        params.put("PM_SimpleCode", goodssimcode);//商品简码
        params.put("PM_Metering", goodsmetering);
        params.put("PM_UnitPrice", goodsaleprice);//商品售价
        params.put("PM_Description", "");
        params.put("PM_Modle", goodsmodel);//商品规格型号
        params.put("PM_Brand", goodsbrand);//商品品牌
        params.put("PM_Remark", "");
        params.put("PM_Detail", "");
        params.put("GID", "");
        params.put("PM_IsService", 1);//商品类型，普通生商品，服务商品，礼品 int型 普通商品 0；服务商品 1,礼品2
        params.put("PM_BigImg", mGoodsImageAddress);//商品图片地址
        params.put("PM_IsDiscount", mDisSwitch);//折扣开关
        params.put("PM_IsPoint", mPosition);//积分规则
        params.put("PM_StockPoliceValue", edGoodsStockWarnNum.getText().toString());//库存警告
        params.put("PM_FixedIntegralValue", edGoodsFixedIntergal.getText().toString());//固定积分
        params.put("PM_SpecialOfferValue", edGoodsSpecialDiscount.getText().toString());//特价折扣
        params.put("PM_MinDisCountValue", edGoodsMinimumDiscount.getText().toString());//最低折扣
        params.put("PM_PurchasePrice", mDefaultPrice);//参考进价
        params.put("PM_MemPrice", mMemberPrice);//会员价格
        params.put("PM_Repertory", edGoodsStock.getText().toString());//库存
        if (goodsfields != null) {
            for (int i = 0; i < goodsfields.size(); i++) {//自定义属性
                params.put("FildsId[" + i + "]", goodsfields.get(i).getCF_GID());
                params.put("FildsValue[" + i + "]", goodsfields.get(i).getM_ItemsValue() == null ? "" : goodsfields.get(i).getM_ItemsValue());
            }
        }

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                AddGoodsResponseBean responseBean = CommonFun.JsonToObj(responseString, AddGoodsResponseBean.class);
                if (responseBean.isSuccess()) {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("添加商品成功！");
                    sweetAlertDialog.setConfirmText("完成");
                    sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            if (type == 3) {
                                getActivity().finish();
                            } else if (opType == 1) {
                                startActivity(new Intent(getActivity(), GoodsManagerActivity.class));
                                getActivity().finish();
                            } else if (opType == 2) {
                                addMore();
                            }

                        }
                    });
                    sweetAlertDialog.show();
//                    CacheData.deleteDir();//删除缓存
                } else {
                    new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText(responseBean.getMsg() + "!")
                            .setConfirmText("了解")
                            .show();
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(getContext(), HttpAPI.API().ADDGOODS, params, callBack);
    }

    private void gainPostdata() {
        goodsname = edGoodsName.getText().toString();//商品名字
        goodscode = edGoodsCode.getText().toString();//商品编码
        goodstype = teGoodsType.getText().toString();//商品分类
        goodsaleprice = edGoodsSalePrice.getText().toString();//商品售价
        goodssimcode = edGoodsSimpleCode.getText().toString();//商品简码
        goodsbrand = edGoodsBrand.getText().toString();//商品品牌
        goodsmodel = edGoodsModel.getText().toString();//商品规格
        mDefaultPrice = edGoodsBidPrice.getText().toString();//参考进价
        mMemberPrice = edGoodsVipPrice.getText().toString();//会员价
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_saoma://扫码
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
                break;
            case R.id.iv_upload_goods_image://上传商品头像
                sendMsgToActivity("msg", "uploadimg");
                break;
            case R.id.edi_addgoodsgoodstype_activity://商品分类
                Intent intent = new Intent(getActivity(), ChoiceTypeActivity.class);
                intent.putExtra("PT_Name", goodstype);
                intent.putExtra("PT_GID", ptid);
                getActivity().startActivityForResult(intent, 55);
                break;
            case R.id.ed_goods_intergal://商品积分
                showChooseDialog(intergalList, teGoodsIntergal);//选择商品积分
                break;

            case R.id.et_add_good_fixed_point:
                if (mPosition != 2) {
                    CustomToast.makeText(getContext(), "仅对商品固定积分规则有效！", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.edi_addgoodsgoodsmodel_activity:
                new ManyChoosePopup(getContext(), edGoodsModel, mManyModelList, "not_many", new ManyChoosePopup.OnShopClickEvent() {
                    @Override
                    public void OnShopClickEvent(String str) {
                        edGoodsModel.setText(str);
                    }
                });
                break;
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler handlera = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mPosition = 1;
                    edGoodsFixedIntergal.setEnabled(false);
                    edGoodsFixedIntergal.setBackgroundColor(getResources().getColor(R.color.ysl_gray));
                    break;
                case 1:
                    mPosition = 2;
                    edGoodsFixedIntergal.setEnabled(true);
                    edGoodsFixedIntergal.setBackgroundColor(getResources().getColor(R.color.white));
                    break;
                case 2:
                    mPosition = 3;
                    edGoodsFixedIntergal.setEnabled(false);
                    edGoodsFixedIntergal.setBackgroundColor(getResources().getColor(R.color.ysl_gray));
                    break;
            }

        }
    };

    /**
     * chooseDialog
     */
    private void showChooseDialog(List<String> mlist, final TextView mTextView) {
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
                        handlera.sendEmptyMessage(position);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();
        chooseDialog.show();
    }


    private void showIsTongbuDialog(List<String> mlist, final TextView mTextView) {
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
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();
        chooseDialog.show();
    }

    /**
     * 判断参数并提交
     */
    private synchronized void JudgeParmasAndPost() {
        gainPostdata();
        if (TextUtils.isEmpty(goodsname)) {
            CustomToast.makeText(getContext(), "请输入商品名称！", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(goodscode) && goodscode.equals("")) {
            CustomToast.makeText(getContext(), "请输入商品编码！", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(goodsaleprice) && goodsaleprice.equals("")) {
            CustomToast.makeText(getContext(), "请输入商品售价！", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(goodstype) && goodstype.equals("")) {
            CustomToast.makeText(getContext(), "请选择商品分类！", Toast.LENGTH_SHORT).show();
            return;
        }
//        else if (TextUtils.isEmpty(mDefaultPrice) && mDefaultPrice.equals("")) {
//            CustomToast.makeText(getContext(), "请输入参考进价！", Toast.LENGTH_SHORT).show();
//        }
        if (!TextUtils.isEmpty(mMemberPrice) && !mMemberPrice.equals("")) {
            double m_price = Double.parseDouble(mMemberPrice);
            double price = Double.parseDouble(goodsaleprice);
            if (m_price > price) {
                CustomToast.makeText(getContext(), "会员价格不能大于商品售价！", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (goodsfields != null) {
            for (int i = 0; i < goodsfields.size(); i++) {
                if (goodsfields.get(i).getCF_Required().equals("是") && (goodsfields.get(i).getM_ItemsValue() == null || goodsfields.get(i).getM_ItemsValue().equals(""))) {
                    CustomToast.makeText(getContext(), "请填写" + goodsfields.get(i).getCF_FieldName(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }

        postAddGoods();
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * lkl、sunmi 、basewin的检卡 读卡操作
         */
        new CardOperationUtils(getContext(), edGoodsCode);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //终止检卡
        new CardOperationUtils().close();
        Log.d("TAG", "onStop: ");
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    private void initViews(View view) {
        edGoodsCode = view.findViewById(R.id.edi_addgoodsgoodscode_activity);
        edGoodsSimpleCode = view.findViewById(R.id.edi_addgoodsgoodssimtype_activity);
        edGoodsName = view.findViewById(R.id.edi_addgoodsgoodsname_activity);
        teGoodsType = view.findViewById(R.id.edi_addgoodsgoodstype_activity);
        edGoodsSalePrice = view.findViewById(R.id.edi_addgoodsgoodsunitp_activity);
        edGoodsBidPrice = view.findViewById(R.id.et_add_good_default_price);
        edGoodsStock = view.findViewById(R.id.et_add_good_stock);//库存
        edGoodsMetering = view.findViewById(R.id.edi_addgoodsgoodsmetering_activity);//计量单位
        edGoodsModel = view.findViewById(R.id.edi_addgoodsgoodsmodel_activity);//商品规格型号
        edGoodsVipPrice = view.findViewById(R.id.et_add_good_member_price);
        sbGoodsDiscount = view.findViewById(R.id.switch_button_discount);
        edGoodsSpecialDiscount = view.findViewById(R.id.et_add_good_special_dis);
        edGoodsMinimumDiscount = view.findViewById(R.id.et_add_good_lower_dis);
        teGoodsIntergal = view.findViewById(R.id.ed_goods_intergal);
        edGoodsFixedIntergal = view.findViewById(R.id.et_add_good_fixed_point);//固定积分
        edGoodsBrand = view.findViewById(R.id.edi_addgoodsgoodsbrand_activity);//商品品牌
        edGoodsStockWarnNum = view.findViewById(R.id.et_add_good_warn_num);
        imgSaoMa = view.findViewById(R.id.iv_saoma);//扫码
        imgGoodsImgView = view.findViewById(R.id.iv_upload_goods_image);//上传商品头像
        recyclerView = view.findViewById(R.id.lv_add_costomfiled);

        imgSaoMa.setOnClickListener(this);
        imgGoodsImgView.setOnClickListener(this);
        teGoodsType.setOnClickListener(this);
        teGoodsIntergal.setOnClickListener(this);
        edGoodsModel.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    class SerReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getBundleExtra("imges") != null) {
                mGoodsImageAddress = intent.getBundleExtra("imges").getString("imgaddress");
                imgUri = Uri.parse(intent.getBundleExtra("imges").getString("imguri"));
//                imgGoodsImgView.setImageURI(imgUri);
                if (mGoodsImageAddress.contains("http")) {
                    Glide.with(getActivity()).load(mGoodsImageAddress).into(imgGoodsImgView);
                } else {
                    String url = mGoodsImageAddress;
                    if (url.contains("../")) {
                        String[] s = url.split("\\.\\./");
                        url = s[2];
                    }
                    Glide.with(getActivity()).load(MyApplication.IMAGE_URL + "/" + url).into(imgGoodsImgView);
                }
            }
            if (intent.getIntExtra("goodsclass", 0) != 90) {
                goodsclass = intent.getIntExtra("goodsclass", 0);
            }
            if (intent.getStringExtra("save") != null) {
                opType = 1;
                JudgeParmasAndPost();
            }
            if (intent.getStringExtra("addmore") != null) {
                opType = 2;
                JudgeParmasAndPost();
            }
            if (intent.getStringExtra("goodcode") != null) {
                edGoodsCode.setText(intent.getStringExtra("goodcode"));

            }
            if (intent.getBundleExtra("goodstype") != null) {
                goodstype = intent.getBundleExtra("goodstype").getString("PT_Name", "");
                ptid = intent.getBundleExtra("goodstype").getString("PT_GID", "");

                teGoodsType.setText(goodstype);

            }
            if (intent.getStringExtra("Searchcontetnt") != null) {
                edGoodsCode.setText(intent.getStringExtra("Searchcontetnt"));
                type = 3;
            }
        }

    }

    private void sendMsgToActivity(String msg, String info) {
        Intent intent = new Intent("android.intent.action.Goods_BROADCAST");
        intent.putExtra(msg, info);
        getContext().sendBroadcast(intent);
    }

    /**
     * 清空界面所有数据，重置界面
     */
    private void addMore() {
        edGoodsCode.setText("");
        edGoodsSimpleCode.setText("");
        edGoodsName.setText("");
        edGoodsSalePrice.setText("");
        edGoodsBidPrice.setText("");
        edGoodsStock.setText("");
        edGoodsMetering.setText("");
        edGoodsModel.setText("");
        edGoodsVipPrice.setText("");
        edGoodsSpecialDiscount.setText("");
        edGoodsMinimumDiscount.setText("");
        edGoodsFixedIntergal.setText("");
        edGoodsBrand.setText("");
        edGoodsStockWarnNum.setText("");
        edGoodsCode.setText("");
        edGoodsCode.setText("");
        sbGoodsDiscount.setChecked(false);
        mDisSwitch = 0;
        handler.sendEmptyMessage(0);
        //固定积分设置
        edGoodsFixedIntergal.setEnabled(false);
        edGoodsFixedIntergal.setBackgroundColor(getResources().getColor(R.color.ysl_gray));
        //金额过滤器
        MoneyInputFilter filter = new MoneyInputFilter(9, true, "最多只能输入9位金额");
        filter.setMessage("最多只能输入9位金额");
        edGoodsVipPrice.setFilters(new InputFilter[]{filter});//会员价
        edGoodsBidPrice.setFilters(new InputFilter[]{filter});//商品进价
        edGoodsSalePrice.setFilters(new InputFilter[]{filter});//商品售价

        teGoodsType.setText("");
        ptid = "";
        if (goodsfields != null) {
            for (int i = 0; i < goodsfields.size(); i++) {
                goodsfields.get(i).setM_ItemsValue("");
            }
        }

        mgoodsfieldsAdapter.setParam(goodsfields);
        mgoodsfieldsAdapter.notifyDataSetChanged();

    }

    public class ChangeHandler extends Handler {
        /**
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    goodsfields = (List<GoodsFiledsBean.DataBean>) msg.obj;

                    break;
                case 2:


                    break;
            }
        }
    }

    /**
     * 获取多规格列表(颜色,尺码)
     */
    private void getSpecifications() {
        HttpHelper.post(getContext(), HttpAPI.API().GOODSMODEL, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mModelList = CommonFun.JsonToObj(responseString, ResultManyModelBean.class).getData();
                composeManyGoods();
            }

            @Override
            public void onFailure(String msg) {
                Li("onFailure===random:" + msg);
            }
        });
    }


    /**
     * 拼接商品多规格
     */
    private void composeManyGoods() {
        for (int i = 0; i < mModelList.size(); i++) {
            mModelList.get(i).setChecked(false);
            mModelList.get(i).setEnable(true);
        }

        mManyModelList.clear();
        //组装规格数据
        if (mModelList != null && mModelList.size() > 1) {
            for (int i = 0; i < mModelList.size(); i++) {
                if (mModelList.get(i).getPM_Type() == 0) {
                    List<GoodsModelBean> list = new ArrayList<>();
                    list.add(mModelList.get(i));
                    mManyModelList.add(list);
                } else {
                    for (int j = 0; j < mManyModelList.size(); j++) {
                        if (mManyModelList.get(j).get(0).getPM_Name().equals(mModelList.get(i).getPM_Name())) {
                            mManyModelList.get(j).add(mModelList.get(i));
                        }
                    }
                }
            }
        }
    }

}
