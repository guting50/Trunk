package com.zhiluo.android.yunpu.goods.manager.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.goods.consume.bean.GoodsModelBean;
import com.zhiluo.android.yunpu.goods.consume.bean.ResultManyModelBean;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.GoodsFiledsBean;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.member.jsonbean.MemberPhotoBean;
import com.zhiluo.android.yunpu.member.manager.adapter.addGoodsfieldsAdapter;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.popup.ManyChoosePopup;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.ChoiceTypeActivity;
import com.zhiluo.android.yunpu.ui.view.CustomPopWindow;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.Base64Encoder;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CheckPromiss;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.GetImagePath;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;
import com.zhiluo.android.yunpu.utils.ShowDialog;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 商品修改
 */
public class GoodsModificationActivity extends BaseActivity implements CustomPopWindow.OnItemClickListener , addGoodsfieldsAdapter.OnImageClickEvent{
    @Bind(R.id.tv_goods_type_start)
    TextView tvGoodsTypeStart;
    @Bind(R.id.ll_discount)
    LinearLayout llDiscount;
    private EditText edigoodsname;
    private EditText edigoodscode;
    private TextView tvgoodstype;
    private EditText edigoodsunitp;
    private EditText edigoodssimcode;
    private EditText edigoodsmetering;
    private EditText edigoodsbrand;
    private TextView tvkeep;
    private String goodsname;
    private String goodscode;
    private String goodstype;
    private String goodsunitp;
    private String goodssimcode;
    private String goodsmetering;
    private String goodsbrand;
    private String goodsdetail;
    private String goodsremark;
    private TextView edigoodsmodel;
    private String goodsmodel;
    private String ptid;
    private ImageView ivsaoma;
    private TextView tv_back_activity, tvtitle, goodprice;
    private GoodsCheckResponseByType.DataBean.DataListBean goodsbean;

    private int mGoodsType;//0 普通商品，1服务商品 2礼品
    private Spinner mSpinner;
    private ArrayAdapter<String> mSpinnerAdapter, mPointAdapter;
    private List<String> mGoodsTypeList, mGoodPointTypeList;
    private EditText etFixedPoint, etSpcielDis, etLowerDis, etSoreWarn, etMemberPrice, etRepertory;
    private Spinner spPoint;//商品积分
    private RadioButton rbOpen, rbClose;//折扣开关打开、关闭
    private RadioGroup rgDisSwitch;//折扣开关
    private int mPointType, mSyncType, mDisSwitch;
    private String mSpecileDis, mLowerDis, mDefaultPrice, mMemberPrice;
    private TextView teGooodsType, teIntergalRule;

    /**
     * 修改图片相关
     */
    private String mGoodsImageAddress = "/img/goods.png";//商品图片地址
    private ImageView mGoodsImage;//商品图片控件
    private CustomPopWindow mCustomPopWindow;//自定义PopWindow声明
    private static String IMAGE_FILE_NAME = "vip_photo.png";
    private static String CROP_IMAGE_FILE_NAME = "vip_photo_temp.png";
    private static String IMAGE_GALLERY_NAME = "image/*";
    File mCameraFile = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);//照相机的File对象
    File mCropFile = new File(Environment.getExternalStorageDirectory(), CROP_IMAGE_FILE_NAME);//裁剪后的File对象
    File mGalleryFile = new File(Environment.getExternalStorageDirectory(), IMAGE_GALLERY_NAME);//相册的File对象
    private static final int RESULT_REQUEST_CODE = 1001;//剪裁结果处理
    private static final int RESULT_REQUEST_CODE_CUSTOM = 1011;//会员自定义图片剪裁结果处理
    private static final int IMAGE_REQUEST_CODE = 1002;//从相册选择照片结果处理（版本小于7.0）
    private static final int SELECT_PIC_KITKAT = 1003;//从相册选择照片结果处理（版本大于等于7.0）
    private static final int CAMERA_REQUEST_CODE = 1004;//拍照结果处理
    private MemberPhotoBean memberPhotoBean;//上传商品图片响应结果
    private Uri mUri;
    private Dialog chooseDialog;
    private List<String> intergalList = new ArrayList<>();
    private List<String> synStateList = new ArrayList<>();
    private List<String> goodstypelist = new ArrayList<>();

    private RecyclerView recyclerView;
    private addGoodsfieldsAdapter mgoodsfieldsAdapter;
    private List<GoodsFiledsBean.DataBean> goodsfields;//商品自定义属性
    private ChangeHandler changeHandler;

    //多规格列表
    private List<GoodsModelBean> mModelList;
    //组装规格数据
    private List<List<GoodsModelBean>> mManyModelList = new ArrayList<>();

    //是不是多规格商品
    private String mManyStr;

    //会员自定义属性图片类型
    private boolean isImageType;
    //会员自定义属性position
    private int mCustomImageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_modification_goods);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        goodsbean = (GoodsCheckResponseByType.DataBean.DataListBean) intent.getSerializableExtra("goodsdetail");
        mManyStr = getIntent().getStringExtra("extra_many");

        initView();
        initData();
        updateView();
        initLinsinner();

        getSpecifications();
    }

    private void initData() {
        changeHandler = new ChangeHandler();
        goodsfields = (List<GoodsFiledsBean.DataBean>) CacheData.restoreObject("goodsfields");
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
        if (goodstypelist.size() > 0) {
            goodstypelist.clear();
        } else {
            String[] synstate = getResources().getStringArray(R.array.goodstype);
            for (String str : synstate) {
                goodstypelist.add(str);
            }
        }
        mGoodsTypeList = new ArrayList<>();
        mGoodsTypeList.add("普通商品");
        mGoodsTypeList.add("服务商品");
        mGoodsTypeList.add("礼品");
        mSpinnerAdapter = new ArrayAdapter<String>(GoodsModificationActivity.this, R.layout.item_spinner,
                R.id.tv_item_spinner, mGoodsTypeList);
        mSpinner.setAdapter(mSpinnerAdapter);

        mCustomPopWindow = new CustomPopWindow(this);

        if (goodsbean.getPM_BigImg() != null) {
//            mGoodsImageAddress = HttpAPI.API().MAIN_DOMAIN + goodsbean.getPM_BigImg();
            mGoodsImageAddress = goodsbean.getPM_BigImg();
        }
        if (goodsbean.getPM_IsService() == 0) {
            mGoodsType = 0;
            //mSpinner.setSelection(0);
            teGooodsType.setText("普通商品");
            etRepertory.setEnabled(false);
            etRepertory.setBackgroundColor(getResources().getColor(R.color.lightgray));
            etSoreWarn.setEnabled(true);
            etSoreWarn.setBackgroundColor(getResources().getColor(R.color.white));
            etRepertory.setText(goodsbean.getStock_Number() + "");
            etSoreWarn.setText(goodsbean.getPM_StockPoliceValue() + "");
            llDiscount.setVisibility(View.VISIBLE);
        }
        if (goodsbean.getPM_IsService() == 1) {
            mGoodsType = 1;
//            mSpinner.setSelection(1);
            teGooodsType.setText("服务商品");
            etRepertory.setEnabled(false);
            etSoreWarn.setEnabled(false);
            etRepertory.setBackgroundColor(getResources().getColor(R.color.lightgray));
            etSoreWarn.setBackgroundColor(getResources().getColor(R.color.lightgray));
            llDiscount.setVisibility(View.VISIBLE);
        }
        if (goodsbean.getPM_IsService() == 2) {
            mGoodsType = 2;
//            mSpinner.setSelection(1);
            teGooodsType.setText("礼品");
            goodprice.setText("兑换积分");
            edigoodsunitp.setHint("请输入兑换所需积分");
            etRepertory.setEnabled(false);
            etRepertory.setBackgroundColor(getResources().getColor(R.color.lightgray));
            etSoreWarn.setEnabled(true);
            etSoreWarn.setBackgroundColor(getResources().getColor(R.color.white));
            etRepertory.setText(goodsbean.getStock_Number() + "");
            etSoreWarn.setText(goodsbean.getPM_StockPoliceValue() + "");
            llDiscount.setVisibility(View.GONE);
        }
        tvGoodsTypeStart.setVisibility(View.GONE);
        teGooodsType.setBackgroundColor(getResources().getColor(R.color.lightgray));
        teGooodsType.setEnabled(false);

        mGoodPointTypeList = new ArrayList<>();
        mGoodPointTypeList.add("按会员等级积分规则");
        mGoodPointTypeList.add("按商品固定积分规则");
        mGoodPointTypeList.add("本商品不积分");
        mPointAdapter = new ArrayAdapter<String>(GoodsModificationActivity.this, R.layout.item_spinner,
                R.id.tv_item_spinner, mGoodPointTypeList);
        spPoint.setAdapter(mPointAdapter);
        if (goodsbean.getPM_IsPoint() == 1) {
//             spPoint.setSelection(0);
            mPointType = 1;
            teIntergalRule.setText("按会员等级积分规则");
        }
        if (goodsbean.getPM_IsPoint() == 2) {
            //spPoint.setSelection(1);
            mPointType = 2;
            teIntergalRule.setText("按商品固定积分规则");
        }
        if (goodsbean.getPM_IsPoint() == 3) {
//            spPoint.setSelection(2);
            mPointType = 3;
            teIntergalRule.setText("本商品不积分");
        }
        if (goodsbean.getPM_IsPoint() == 2) {
            etFixedPoint.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
            etFixedPoint.setBackgroundColor(getResources().getColor(R.color.white));
        } else {
            etFixedPoint.setInputType(InputType.TYPE_NULL);
            etFixedPoint.setText("0.0");
            etFixedPoint.setBackgroundColor(getResources().getColor(R.color.lightgray));
        }
        spPoint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    mPointType = 1;
                    etFixedPoint.setInputType(InputType.TYPE_NULL);
                    etFixedPoint.setBackgroundColor(getResources().getColor(R.color.lightgray));
                } else if (i == 1) {
                    mPointType = 2;
                    etFixedPoint.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    etFixedPoint.setBackgroundColor(getResources().getColor(R.color.white));
                } else {
                    mPointType = 3;
                    etFixedPoint.setInputType(InputType.TYPE_NULL);
                    etFixedPoint.setBackgroundColor(getResources().getColor(R.color.lightgray));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        rgDisSwitch.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == rbOpen.getId()) {
                    mDisSwitch = 1;
                    etSpcielDis.setEnabled(true);
                    etSpcielDis.setBackgroundColor(getResources().getColor(R.color.white));
                    etLowerDis.setEnabled(true);
                    etLowerDis.setBackgroundColor(getResources().getColor(R.color.white));
                } else if (i == rbClose.getId()) {
                    mDisSwitch = 0;
                    etSpcielDis.setEnabled(false);
                    etSpcielDis.setBackgroundColor(getResources().getColor(R.color.lightgray));
                    etLowerDis.setEnabled(false);
                    etLowerDis.setBackgroundColor(getResources().getColor(R.color.lightgray));
                }
            }
        });
        if (goodsbean.getPM_IsDiscount() == 0) {
            rgDisSwitch.check(rbClose.getId());
            mDisSwitch = 0;
        } else {
            rgDisSwitch.check(rbOpen.getId());
            mDisSwitch = 1;
        }

    }


    private void initAdapter() {
        for (int i = 0; i < goodsfields.size(); i++) {
            if (goodsbean.getCustomFields() != null && goodsbean.getCustomFields().size() > 0) {
                for (int j = 0; j < goodsbean.getCustomFields().size(); j++) {
                    if (goodsfields.get(i).getCF_FieldName().equals(goodsbean.getCustomFields().get(j).getCF_FieldName())) {
                        goodsfields.get(i).setM_ItemsValue(goodsbean.getCustomFields().get(j).getCF_Value());
                    }
                }
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        mgoodsfieldsAdapter = new addGoodsfieldsAdapter(GoodsModificationActivity.this, goodsfields, changeHandler, 1);
        mgoodsfieldsAdapter.setImageClickEvent(this);
        mgoodsfieldsAdapter.setHasStableIds(true);
        recyclerView.setAdapter(mgoodsfieldsAdapter);
    }

    /**
     * @param mlist
     * @param mTextView
     */
    private void showGoodsTypeDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
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
                        mGoodsType = position;
                        switch (position) {
                            case 0:
                                etRepertory.setEnabled(false);
                                etRepertory.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                etSoreWarn.setEnabled(true);
                                etSoreWarn.setBackgroundColor(getResources().getColor(R.color.white));
                                goodprice.setText("商品售价");
                                edigoodsunitp.setHint("请输入商品售价");
                                break;
                            case 1:
                                etRepertory.setEnabled(false);
                                etSoreWarn.setEnabled(false);
                                etRepertory.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                etSoreWarn.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                goodprice.setText("商品售价");
                                edigoodsunitp.setHint("请输入商品售价");
                                break;
                            case 2:
                                etRepertory.setEnabled(false);
                                etSoreWarn.setEnabled(true);
                                etRepertory.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                etSoreWarn.setBackgroundColor(getResources().getColor(R.color.white));
                                goodprice.setText("兑换积分");
                                edigoodsunitp.setHint("请输入兑换所需积分");
                                break;
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }


    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                etFixedPoint.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                etFixedPoint.setBackgroundColor(getResources().getColor(R.color.white));
            } else {
                etFixedPoint.setInputType(InputType.TYPE_NULL);
                etFixedPoint.setText("0.0");
                etFixedPoint.setBackgroundColor(getResources().getColor(R.color.background));
            }
        }
    };

    private void showIntergalRuleDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mTextView.getText().toString().contains(mlist.get(i)) || mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        mPointType = position + 1;
                        handler.sendEmptyMessage(position);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    private void updateView() {
        if (goodsbean.getPM_BigImg() != null) {//图片地址
            if (goodsbean.getPM_BigImg().contains("http")) {
                Glide.with(GoodsModificationActivity.this).load(goodsbean.getPM_BigImg()).error(R.drawable.ysl_add_goods).into(mGoodsImage);
            } else {
                Glide.with(GoodsModificationActivity.this).load(HttpAPI.API().MAIN_DOMAIN + goodsbean.
                        getPM_BigImg()).into(mGoodsImage);
            }
        } else {
            mGoodsImage.setImageResource(R.drawable.ysl_add_goods);
        }


        tvtitle.setText("编辑商品");

        edigoodsname.setText(goodsbean.getPM_Name());
        edigoodscode.setText(goodsbean.getPM_Code());
        edigoodscode.setSelection(edigoodscode.getText().toString().length());
        tvgoodstype.setText(goodsbean.getPT_Name() + "");
        ptid = goodsbean.getPT_ID();
//        tvgoodstype.setText(goodsbean.getPM_SynType() + "");
        edigoodsunitp.setText(Decima2KeeplUtil.stringToDecimal(goodsbean.getPM_UnitPrice() + ""));
        edigoodssimcode.setText(goodsbean.getPM_SimpleCode());
        edigoodsmetering.setText(goodsbean.getPM_Metering());
        edigoodsbrand.setText(goodsbean.getPM_Brand());

        if (goodsbean.getPM_Modle() != null && !goodsbean.getPM_Modle().equals("null")) {
            edigoodsmodel.setText(goodsbean.getPM_Modle());
        }
        etFixedPoint.setText(goodsbean.getPM_FixedIntegralValue() + "");


        etLowerDis.setText(goodsbean.getPM_MinDisCountValue() + "");
        etSpcielDis.setText(goodsbean.getPM_SpecialOfferValue() + "");
//        etDefaultPrice.setText("" + goodsbean.getPM_PurchasePrice());
        if (goodsbean.getPM_MemPrice() == null) {
            etMemberPrice.setText("");
        } else {
            etMemberPrice.setText("" + goodsbean.getPM_MemPrice());
        }
    }

    private void initLinsinner() {
        teGooodsType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGoodsTypeDialog(mGoodsTypeList, teGooodsType);
            }
        });

        teIntergalRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIntergalRuleDialog(intergalList, teIntergalRule);
            }
        });
        mCustomPopWindow.setOnItemClickListener(this);
        mGoodsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isImageType = false;
                mCustomPopWindow.showAtLocation(GoodsModificationActivity.this.findViewById(R.id.add_goods),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        ivsaoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(GoodsModificationActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
        tv_back_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityManager.getInstance().exit();
                GoodsModificationActivity.this.finish();
                startActivity(new Intent(GoodsModificationActivity.this, GoodsManagerActivity.class));
            }
        });
        tvgoodstype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsModificationActivity.this, ChoiceTypeActivity.class);
                intent.putExtra("PT_Name", goodstype);
                intent.putExtra("PT_GID", ptid);
                startActivityForResult(intent, 1);
            }
        });

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    mGoodsType = 0;
                }
                if (i == 1) {
                    mGoodsType = 1;
                }
                if (i == 2) {
                    mGoodsType = 2;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mGoodsType = 0;
            }
        });


        tvkeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gainPostdata();
                if (goodsfields != null && mGoodsType != 2) {
                    for (int i = 0; i < goodsfields.size(); i++) {
                        if (goodsfields.get(i).getCF_Required().equals("是") && (goodsfields.get(i).getM_ItemsValue() == null || goodsfields.get(i).getM_ItemsValue().equals(""))) {
                            CustomToast.makeText(GoodsModificationActivity.this, "请填写" + goodsfields.get(i).getCF_FieldName(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }

                if (!TextUtils.isEmpty(goodsname) && !TextUtils.isEmpty(goodscode) && !TextUtils.isEmpty(goodsunitp) && !TextUtils.isEmpty(goodstype)) {
                    PostEditGoods();
                } else {
                    new SweetAlertDialog(GoodsModificationActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("请检查输入是否正确")
                            .setConfirmText("了解")
                            .show();
//                    CustomToast.makeText(GoodsModificationActivity.this, "请检查输入是否正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        etSpcielDis.addTextChangedListener(new ExamineTextWatcher(etSpcielDis));
//        etLowerDis.addTextChangedListener(new ExamineTextWatcher(etLowerDis));
        //金额过滤器
        MoneyInputFilter filter = new MoneyInputFilter(9, true, "最多只能输入9位金额");
        filter.setMessage("最多只能输入9位金额");
        etMemberPrice.setFilters(new InputFilter[]{filter});
//        etDefaultPrice.setFilters(new InputFilter[]{filter});
        edigoodsunitp.setFilters(new InputFilter[]{filter});

        etMemberPrice.addTextChangedListener(new TextWatcher() {
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
                    etMemberPrice.setText("999999");
                    CustomToast.makeText(GoodsModificationActivity.this, "会员价格已超上限！", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        etDefaultPrice.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.toString().isEmpty() || s.toString().equals("")) {
//
//                } else if (Double.parseDouble(s.toString()) > 999999.99) {
//                    etDefaultPrice.setText("999999");
//                    CustomToast.makeText(GoodsModificationActivity.this, "商品价格已超上限！", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        edigoodsunitp.addTextChangedListener(new TextWatcher() {
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
                    edigoodsunitp.setText("999999");
                    CustomToast.makeText(GoodsModificationActivity.this, "商品价格已超上限！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //选规格
        edigoodsmodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ManyChoosePopup(GoodsModificationActivity.this, edigoodsmodel, mManyModelList, mManyStr, new ManyChoosePopup.OnShopClickEvent() {
                    @Override
                    public void OnShopClickEvent(String str) {
                        edigoodsmodel.setText(str);
                    }
                });
            }
        });
    }


    /**
     * 查询会员等级
     */
    private void getgoodsfields() {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                ActivityManager.getInstance().exit();
                startActivity(new Intent(GoodsModificationActivity.this, GoodsManagerActivity.class));
                GoodsModificationActivity.this.finish();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void PostEditGoods() {
        if (mDisSwitch == 1) {
            if (!etSpcielDis.getText().toString().isEmpty()) {
                if (Double.parseDouble(etSpcielDis.getText().toString()) > 1 || Double.parseDouble(etSpcielDis.getText().toString()) < 0) {
                    CustomToast.makeText(GoodsModificationActivity.this, "特价折扣取值范围0-1之间", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if (!etLowerDis.getText().toString().isEmpty()) {
                if (Double.parseDouble(etLowerDis.getText().toString()) > 1 || Double.parseDouble(etLowerDis.getText().toString()) < 0) {
                    CustomToast.makeText(GoodsModificationActivity.this, "最低折扣取值范围0-1之间", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        }
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("PT_ID", ptid);
        params.put("PT_Name", goodstype);
        params.put("PM_Code", goodscode);
        params.put("PM_Name", goodsname);
        params.put("PM_SimpleCode", goodssimcode);
        params.put("PM_Metering", goodsmetering);
        params.put("PM_UnitPrice", goodsunitp);
        params.put("PM_Description", goodsdetail);
        params.put("PM_Modle", goodsmodel);
        params.put("PM_Brand", goodsbrand);
        params.put("PM_SynType", 0);
        params.put("PM_IsService", mGoodsType);
        params.put("PM_Remark", goodsremark);
        params.put("PM_Detail", "");
        params.put("GID", goodsbean.getGID());
        params.put("PM_BigImg", mGoodsImageAddress);
        Li("地址----" + mGoodsImageAddress);
        params.put("PM_IsDiscount", mDisSwitch);
        params.put("PM_IsPoint", mPointType);
        params.put("PM_StockPoliceValue", etSoreWarn.getText().toString());
        params.put("PM_FixedIntegralValue", etFixedPoint.getText().toString());
        params.put("PM_SpecialOfferValue", etSpcielDis.getText().toString());
        params.put("PM_SpecialOfferMoney", -1);
        params.put("PM_MinDisCountValue", etLowerDis.getText().toString());
//        params.put("PM_PurchasePrice", etDefaultPrice.getText().toString());//参考进价
        params.put("PM_MemPrice", etMemberPrice.getText().toString());//会员价格
        for (int i = 0; i < goodsfields.size(); i++) {//自定义属性
            params.put("FildsId[" + i + "]", goodsfields.get(i).getCF_GID());
            params.put("FildsValue[" + i + "]", goodsfields.get(i).getM_ItemsValue() == null ? "" : goodsfields.get(i).getM_ItemsValue());
        }
        client.post(HttpAPI.API().EDITGOODS, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resurt = null;
                    try {
                        resurt = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        Adduserbean adduserbean = CommonFun.JsonToObj(resurt, Adduserbean.class);
                        boolean isture = adduserbean.isSuccess();
                        if (isture) {
//                            CustomToast.makeText(GoodsModificationActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
//                            finish();
                            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(GoodsModificationActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                            sweetAlertDialog.setTitleText("修改商品成功！");
                            sweetAlertDialog.setConfirmText("确认");
                            sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                @Override
                                public void onDismiss(DialogInterface dialogInterface) {
                                    ActivityManager.getInstance().exit();
                                    GoodsModificationActivity.this.finish();
                                    startActivity(new Intent(GoodsModificationActivity.this, GoodsManagerActivity.class));

                                }
                            });
                            sweetAlertDialog.show();
                        } else {
                            new SweetAlertDialog(GoodsModificationActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(adduserbean.getMsg() + "!")
                                    .setConfirmText("了解")
                                    .show();
//                            CustomToast.makeText(GoodsModificationActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }

    private void gainPostdata() {
        goodsname = edigoodsname.getText().toString();
        goodscode = edigoodscode.getText().toString();
        goodstype = tvgoodstype.getText().toString();
        goodsunitp = edigoodsunitp.getText().toString();
        goodssimcode = edigoodssimcode.getText().toString();
        goodsmetering = edigoodsmetering.getText().toString();
        goodsbrand = edigoodsbrand.getText().toString();
        goodsremark = edigoodsmetering.getText().toString();
        goodsmodel = edigoodsmodel.getText().toString();
//        mDefaultPrice = etDefaultPrice.getText().toString();
        mMemberPrice = etMemberPrice.getText().toString();
    }

    private void initView() {
        mGoodsImage = (ImageView) findViewById(R.id.iv_upload_goods_image);
        ivsaoma = (ImageView) findViewById(R.id.iv_saoma);
        tvtitle = (TextView) findViewById(R.id.tv_titlerg_activity);
        edigoodsname = (EditText) findViewById(R.id.edi_addgoodsgoodsname_activity);
        edigoodscode = (EditText) findViewById(R.id.edi_addgoodsgoodscode_activity);
        tvgoodstype = (TextView) findViewById(R.id.edi_addgoodsgoodstype_activity);
        edigoodsunitp = (EditText) findViewById(R.id.edi_addgoodsgoodsunitp_activity);
        edigoodssimcode = (EditText) findViewById(R.id.edi_addgoodsgoodssimtype_activity);
        edigoodsmetering = (EditText) findViewById(R.id.edi_addgoodsgoodsmetering_activity);
        edigoodsbrand = (EditText) findViewById(R.id.edi_addgoodsgoodsbrand_activity);
        edigoodsmodel = (TextView) findViewById(R.id.edi_addgoodsgoodsmodel_activity);
        tvkeep = (TextView) findViewById(R.id.tv_addgoodskeep_activity);
        tv_back_activity = (TextView) findViewById(R.id.tv_back_activity);
        mSpinner = (Spinner) findViewById(R.id.sp_addgoods_goodtype);
        etFixedPoint = (EditText) findViewById(R.id.et_add_good_fixed_point);
        etLowerDis = (EditText) findViewById(R.id.et_add_good_lower_dis);
        etSpcielDis = (EditText) findViewById(R.id.et_add_good_special_dis);
        etSoreWarn = (EditText) findViewById(R.id.et_add_good_warn_num);
        spPoint = (Spinner) findViewById(R.id.sp_add_goods_point);
        rbClose = (RadioButton) findViewById(R.id.rb_add_good_dis_close);
        rbOpen = (RadioButton) findViewById(R.id.rb_add_good_dis_open);
        rgDisSwitch = (RadioGroup) findViewById(R.id.rg_add_good_dis_switch);
//        etDefaultPrice = (EditText) findViewById(R.id.et_add_good_default_price);
        etMemberPrice = (EditText) findViewById(R.id.et_add_good_member_price);
        etRepertory = findViewById(R.id.et_add_good_stock);
        teGooodsType = findViewById(R.id.ed_goods_type);
        teIntergalRule = findViewById(R.id.ed_intergal_rule);
        edigoodscode.requestFocus();

        goodprice = findViewById(R.id.good_price_name);
        recyclerView = findViewById(R.id.lv_add_costomfiled);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            try {
                goodstype = data.getStringExtra("PT_Name");
                ptid = data.getStringExtra("PT_GID");
                if (!goodstype.equals("")) {
                    tvgoodstype.setText(goodstype);
                }
            } catch (NullPointerException e) {
            }
        }


//        if (resultCode == RESULT_OK) {
//            Bundle bundle = data.getExtras();
//            String scanResult = bundle.getString("result");
//            edigoodscode.setText(scanResult);
//        }

        switch (requestCode) {
            case IMAGE_REQUEST_CODE://版本<7.0  图库后返回
                if (data != null) {
                    startPhotoZoom(data.getData());
//                    mGoodsImage.setImageURI(data.getData());
                }
                break;
            case SELECT_PIC_KITKAT://版本>= 7.0
                if (data != null && GetImagePath.getPath(getApplicationContext(), data.getData()) != null) {
                    File imgUri = new File(GetImagePath.getPath(getApplicationContext(), data.getData()));
                    Uri dataUri = FileProvider.getUriForFile(GoodsModificationActivity.this, MyApplication.FILE_ADDRESS, imgUri);
                    startPhotoZoom(dataUri);
//                    mGoodsImage.setImageURI(dataUri);
                }
                break;
            case CAMERA_REQUEST_CODE: //照相后返回
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri inputUri = FileProvider.getUriForFile(GoodsModificationActivity.this, MyApplication.FILE_ADDRESS, mCameraFile);//通过FileProvider创建一个content类型的Uri
                        mUri = inputUri;
                        startPhotoZoom(inputUri);//设置输入类型
                    } else {
                        Uri inputUri = Uri.fromFile(mCameraFile);
                        mUri = inputUri;
                        startPhotoZoom(inputUri);
                    }
                }

                break;
            case RESULT_REQUEST_CODE://剪裁结果用ImageView显示
                if (resultCode == RESULT_OK) {
                    Uri inputUri = FileProvider.getUriForFile(GoodsModificationActivity.this,
                            MyApplication.FILE_ADDRESS, mCropFile);//通过FileProvider创建一个content类型的Uri
                    mUri = inputUri;
                    postUploadGoodsPhoto();
                    mGoodsImage.setImageURI(mUri);
                }
                break;
            case RESULT_REQUEST_CODE_CUSTOM://剪裁结果用ImageView显示
                if (resultCode == RESULT_OK) {
                    customImageUpload();
                }
                break;
        }
        if (requestCode == 10086 && resultCode == 10086) {
            Uri imageUri = Uri.parse(data.getStringExtra("memberPhoto"));
            mGoodsImageAddress = data.getStringExtra("result");
            mUri = imageUri;
            mGoodsImage.setImageURI(imageUri);
        }

        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            edigoodscode.setText(scanResult);
        }
    }

    /**
     * 拍照
     */
    private void jump2Caramel() {
        if (CheckPromiss.hasCamera()) {
            if (CheckPromiss.checkPermission(GoodsModificationActivity.this)) {

                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
                    Uri uriForFile = FileProvider.getUriForFile(GoodsModificationActivity.this,
                            MyApplication.FILE_ADDRESS, mCameraFile);
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile));
                }
                startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
            } else {
                ShowDialog.warnDialog(GoodsModificationActivity.this, "未获取到相机权限，请确认后尝试！");
            }

        } else {
            CustomToast.makeText(GoodsModificationActivity.this, "未获取到相机，请确认后再尝试！", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    /**
     * 从相册中选择
     */
    private void jump2Album() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
            Uri uriForFile = FileProvider.getUriForFile(GoodsModificationActivity.this, MyApplication.FILE_ADDRESS, mGalleryFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, SELECT_PIC_KITKAT);
        } else {
            startActivityForResult(intent, IMAGE_REQUEST_CODE);
        }
    }

    /**
     * 剪裁图片
     *
     * @param inputUri
     */
    public void startPhotoZoom(Uri inputUri) {
        if (inputUri == null) {
            Log.i("TAG", "The uri is not exist.");
            return;
        }

        Intent intent = new Intent("com.android.camera.action.CROP");
        //sdk>=24
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri outPutUri = Uri.fromFile(mCropFile);
            intent.setDataAndType(inputUri, "image/*");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
            intent.putExtra("noFaceDetection", false);//去除默认的人脸识别，否则和剪裁匡重叠
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            Uri outPutUri = Uri.fromFile(mCropFile);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                String url = GetImagePath.getPath(getApplicationContext(), inputUri);//这个方法是处理4.4以上图片返回的Uri对象不同的处理方法
                intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
            } else {
                intent.setDataAndType(inputUri, "image/*");
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
        }

        // 设置裁剪

        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());// 图片格式
        if (isImageType) {
            startActivityForResult(intent, RESULT_REQUEST_CODE_CUSTOM);//这里就将裁剪后的图片的Uri返回了
        } else {
            startActivityForResult(intent, RESULT_REQUEST_CODE);//这里就将裁剪后的图片的Uri返回了
        }
    }

    /**
     * 上传图片
     */
    private void postUploadGoodsPhoto() {
        RequestParams params = new RequestParams();
        try {
            params.put("photo", mCropFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                memberPhotoBean = CommonFun.JsonToObj(responseString, MemberPhotoBean.class);
                mGoodsImageAddress = memberPhotoBean.getData();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(GoodsModificationActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在上传图片...", false);
        HttpHelper.post(this, HttpAPI.API().UPLOAD_MEMBER_PHOTO, params, callBack);
    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            //  拍照
            case R.id.tv_popwindow_photo:
                jump2Caramel();
                mCustomPopWindow.dismiss();
                break;
            //从手机相册选择
            case R.id.tv_popwindow_select:
                jump2Album();
                mCustomPopWindow.dismiss();
                break;
            //退出
            case R.id.tv_popwindow_cancel:
                mCustomPopWindow.dismiss();
                break;
        }
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
        HttpHelper.post(this, HttpAPI.API().GOODSMODEL, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mModelList = CommonFun.JsonToObj(responseString, ResultManyModelBean.class).getData();
//                for (GoodsModelBean data : mModelList) {
//                    Li("=多规格列表=======random:" + new Gson().toJson(data));
//                }

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

        //商品规格名称
        List<String> shopManyList = new ArrayList<>();
        //将商品有的规格设置成可点击
        if (goodsbean.getPM_Modle() != null && !goodsbean.getPM_Modle().equals("")) {
            List<String> list = DateUtil.getStringForlist(goodsbean.getPM_Modle());
            Li("购物车列表======11111=======random:" + new Gson().toJson(list));
            for (int j = 0; j < list.size(); j++) {
                for (int k = 0; k < mModelList.size(); k++) {
                    GoodsModelBean modeBean = mModelList.get(k);
                    Li("购物车列表====2222=========random:" + new Gson().toJson(modeBean));
                    if (list.get(j).equals(modeBean.getPM_Properties())) {
                        modeBean.setChecked(true);
                        shopManyList.add(modeBean.getPM_Name());
                    }
                }
            }
        }

        List<GoodsModelBean> flagModelList = new ArrayList<>();

        if (mManyStr.equals("many") && shopManyList.size() > 0) {
            for (String name : shopManyList) {
                for (int i = 0, len = mModelList.size(); i < len; i++) {
                    if (name.equals(mModelList.get(i).getPM_Name())) {
                        flagModelList.add(mModelList.get(i));
                    }
                }
            }
            mModelList.clear();
            mModelList.addAll(flagModelList);
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

    @Override
    public void OnImageClickEvent(int position, boolean isEmpty) {
        isImageType = true;
        mCustomImageId = position;
        mCustomPopWindow.showAtLocation(GoodsModificationActivity.this.findViewById(R.id.add_goods), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    /**
     * 上传会员自定义图片
     */
    private void customImageUpload() {
        RequestParams params = new RequestParams();
        params.put("DataBase64", imageToBase64(mCropFile));
        params.put("ZIP", "");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                MemberPhotoBean bean = CommonFun.JsonToObj(responseString, MemberPhotoBean.class);
                if (bean.isSuccess()) {
                    goodsfields.get(mCustomImageId).setCF_Value(bean.getData());
                    mgoodsfieldsAdapter.notifyItemChanged(mCustomImageId);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(GoodsModificationActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在上传头像...", false);
        HttpHelper.post(this, HttpAPI.API().UPLOAD_GIFT_IMAGE, params, callBack);
    }

    /**
     * 将图片文件转成Base64编码
     *
     * @param file 文件
     * @return base64码
     */
    public static String imageToBase64(File file) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        Base64Encoder encoder = new Base64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
}
