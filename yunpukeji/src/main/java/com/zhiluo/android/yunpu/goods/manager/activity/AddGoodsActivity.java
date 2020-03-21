package com.zhiluo.android.yunpu.goods.manager.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
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
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.SuccessBean;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.AddGoodsResponseBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.jsonbean.MemberPhotoBean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.ChoiceTypeActivity;
import com.zhiluo.android.yunpu.ui.activity.good.GoodsWarehousingActivity;
import com.zhiluo.android.yunpu.ui.activity.tc.AddTCActivity;
import com.zhiluo.android.yunpu.ui.view.CustomPopWindow;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.ExamineTextWatcher;
import com.zhiluo.android.yunpu.utils.GetImagePath;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

public class AddGoodsActivity extends BaseActivity implements CustomPopWindow.OnItemClickListener {

    private EditText edigoodsname;
    private EditText edigoodscode;
    private TextView tv_back_activity, tvgoodstype;
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
    private EditText edigoodsmodel;
    private String goodsmodel;
    private String ptid;
    private ImageView ivsaoma;
    private Spinner spGoodsType;//商品类型（普通商品，服务商品）控件声明
    private String mGoodsType;//商品类型（普通商品，服务商品）
    private int mGoodsTypeParam;//提交到服务器商品类型参数 0 普通商品，1 服务商品
    private ArrayAdapter<String> mSpinnerAdapter, mPointAdapter;
    private List<String> mGoodsTypeList, mGoodPointTypeList;
    private EditText etFixedPoint, etSpcielDis, etLowerDis, etSoreWarn, etDefaultPrice, etMemberPrice, etStock;
    private Spinner spPoint;//商品积分，同步状态
    private RadioButton rbOpen, rbClose;//折扣开关打开、关闭
    private RadioGroup rgDisSwitch;//折扣开关
    private int mPointType, mSyncType, mDisSwitch;
    private String mSpecileDis, mLowerDis, mDefaultPrice, mMemberPrice;


    /**
     * 商品图片相关
     */
    private ShapedImageView mIvUploadGoodsImage;//
    private CustomPopWindow mCustomPopWindow;//自定义PopWindow声明
    private static String IMAGE_FILE_NAME = "vip_photo.png";
    private static String CROP_IMAGE_FILE_NAME = "vip_photo_temp.png";
    private static String IMAGE_GALLERY_NAME = "image/*";
    File mCameraFile = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);//照相机的File对象
    File mCropFile = new File(Environment.getExternalStorageDirectory(), CROP_IMAGE_FILE_NAME);//裁剪后的File对象
    File mGalleryFile = new File(Environment.getExternalStorageDirectory(), IMAGE_GALLERY_NAME);//相册的File对象
    private static final int RESULT_REQUEST_CODE = 1001;//剪裁结果处理
    private static final int IMAGE_REQUEST_CODE = 1002;//从相册选择照片结果处理（版本小于7.0）
    private static final int SELECT_PIC_KITKAT = 1003;//从相册选择照片结果处理（版本大于等于7.0）
    private static final int CAMERA_REQUEST_CODE = 1004;//拍照结果处理
    private MemberPhotoBean memberPhotoBean;//上传商品图片响应结果
    private Uri mUri;
    private String mGoodsImageAddress = "/img/goods.png";//商品图片地址

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_modification_goods);
        initView();
        mDisSwitch = 1;
        initLinsinner();
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * lkl、sunmi 、basewin的检卡 读卡操作
         */
        new CardOperationUtils(this, edigoodscode);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //终止检卡
        new CardOperationUtils().close();
        Log.d("TAG", "onStop: ");
    }

    private void PostAddGoods() {
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
        params.put("PM_Description", "");
        params.put("PM_Modle", goodsmodel);
        params.put("PM_Brand", goodsbrand);
        params.put("PM_SynType", mSyncType);
        params.put("PM_Remark", "");
        params.put("PM_Detail", "");
        params.put("GID", "");
        params.put("PM_IsService", mGoodsTypeParam);
        params.put("PM_BigImg", mGoodsImageAddress);
        params.put("PM_IsDiscount", mDisSwitch);
        params.put("PM_IsPoint", mPointType);
        params.put("PM_StockPoliceValue", etSoreWarn.getText().toString());
        params.put("PM_FixedIntegralValue", etFixedPoint.getText().toString());
        params.put("PM_SpecialOfferValue", etSpcielDis.getText().toString());
        params.put("PM_MinDisCountValue", etLowerDis.getText().toString());
        params.put("PM_PurchasePrice", mDefaultPrice);//参考进价
        params.put("PM_MemPrice", mMemberPrice);//会员价格
        params.put("PM_Repertory", etStock.getText().toString());//库存
        client.post(HttpAPI.API().ADDGOODS, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                AddGoodsResponseBean responseBean = CommonFun.JsonToObj(responseString, AddGoodsResponseBean.class);
                if (responseBean.isSuccess()) {
                    SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(AddGoodsActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                    sweetAlertDialog.setTitleText("添加商品成功！");
                    if (mGoodsTypeParam == 0) {
                        sweetAlertDialog.setConfirmText("入库");
                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent intent = new Intent(AddGoodsActivity.this, GoodsWarehousingActivity.class);
                                intent.putExtra("GOOD_CODE", goodscode);
                                startActivity(intent);
                                sweetAlertDialog.dismiss();
                                finish();
                            }
                        });
                    }
                    sweetAlertDialog.setCancelText("完成");
                    sweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismiss();
                            finish();
                        }
                    });
                    sweetAlertDialog.show();
//                    CacheData.deleteDir();//删除缓存
                } else {
                    new SweetAlertDialog(AddGoodsActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText(responseBean.getMsg() + "!")
                            .setConfirmText("了解")
                            .show();
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddGoodsActivity.this, msg, Toast.LENGTH_SHORT).show();
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
        goodsmodel = edigoodsmodel.getText().toString();
        mGoodsType = (String) spGoodsType.getSelectedItem();
        mDefaultPrice = etDefaultPrice.getText().toString();
        mMemberPrice = etMemberPrice.getText().toString();
    }

    private void initLinsinner() {
        mGoodsTypeList = new ArrayList<>();
        mGoodsTypeList.add("普通商品");
        mGoodsTypeList.add("服务商品");
        mSpinnerAdapter = new ArrayAdapter<String>(AddGoodsActivity.this, R.layout.item_spinner,
                R.id.tv_item_spinner, mGoodsTypeList);
        spGoodsType.setAdapter(mSpinnerAdapter);





        mGoodPointTypeList = new ArrayList<>();
        mGoodPointTypeList.add("按会员等级积分规则");
        mGoodPointTypeList.add("按商品固定积分规则");
        mGoodPointTypeList.add("本商品不积分");
        mPointAdapter = new ArrayAdapter<String>(AddGoodsActivity.this, R.layout.item_spinner,
                R.id.tv_item_spinner, mGoodPointTypeList);
        spPoint.setAdapter(mPointAdapter);
        spPoint.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    mPointType = 1;
                    etFixedPoint.setInputType(InputType.TYPE_NULL);
                    etFixedPoint.setBackgroundColor(getResources().getColor(R.color.background));
                } else if (i == 1) {
                    mPointType = 2;
                    etFixedPoint.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    etFixedPoint.setBackgroundColor(getResources().getColor(R.color.white));
                } else {
                    mPointType = 3;
                    etFixedPoint.setInputType(InputType.TYPE_NULL);
                    etFixedPoint.setBackgroundColor(getResources().getColor(R.color.background));
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
                    etSpcielDis.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    etSpcielDis.setBackgroundColor(getResources().getColor(R.color.white));
                    etLowerDis.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
                    etLowerDis.setBackgroundColor(getResources().getColor(R.color.white));
                } else if (i == rbClose.getId()) {
                    mDisSwitch = 0;
                    etSpcielDis.setInputType(InputType.TYPE_NULL);
                    etSpcielDis.setBackgroundColor(getResources().getColor(R.color.background));
                    etLowerDis.setInputType(InputType.TYPE_NULL);
                    etLowerDis.setBackgroundColor(getResources().getColor(R.color.background));
                }
            }
        });

        mCustomPopWindow.setOnItemClickListener(this);
        ivsaoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(AddGoodsActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        tv_back_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvkeep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gainPostdata();
                if (!TextUtils.isEmpty(goodsname)) {
                    if (!TextUtils.isEmpty(goodscode)) {
                        if (!TextUtils.isEmpty(goodstype)) {
                            if (!TextUtils.isEmpty(goodsunitp)) {
                                if (!TextUtils.isEmpty(mDefaultPrice)) {
                                    if (!TextUtils.isEmpty(etMemberPrice.getText())) {
                                        double m_price = Double.parseDouble(etMemberPrice.getText().toString());
                                        double price = Double.parseDouble(goodsunitp);
                                        if (m_price > price) {
                                            CustomToast.makeText(AddGoodsActivity.this, "会员价格不能大于商品售价！", Toast.LENGTH_SHORT).show();
                                        } else {
                                            PostAddGoods();
                                        }
                                    } else {
                                        PostAddGoods();
                                    }
                                } else {
                                    CustomToast.makeText(AddGoodsActivity.this, "请输入参考进价！", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                CustomToast.makeText(AddGoodsActivity.this, "请输入商品售价！", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            CustomToast.makeText(AddGoodsActivity.this, "请选择商品分类！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        CustomToast.makeText(AddGoodsActivity.this, "请输入商品编码！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    CustomToast.makeText(AddGoodsActivity.this, "请输入商品名称！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvgoodstype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(AddGoodsActivity.this, GoodsChooseCategoryListActivity.class);
//                startActivityForResult(intent, 1);
                Intent intent = new Intent(AddGoodsActivity.this, ChoiceTypeActivity.class);
                startActivityForResult(intent, 55);
            }
        });
        spGoodsType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mGoodsType = adapterView.getItemAtPosition(i).toString();
                if ("普通商品".equals(mGoodsType)) {
                    mGoodsTypeParam = 0;
                    etStock.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                }
                if ("服务商品".equals(mGoodsType)) {
                    mGoodsTypeParam = 1;
                    etStock.setInputType(InputType.TYPE_NULL);
                    etStock.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        //上传图片
        mIvUploadGoodsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomPopWindow.showAtLocation(AddGoodsActivity.this.findViewById(R.id.add_goods),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        etSpcielDis.addTextChangedListener(new ExamineTextWatcher(etSpcielDis));
        etLowerDis.addTextChangedListener(new ExamineTextWatcher(etLowerDis));

        //金额过滤器
        MoneyInputFilter filter = new MoneyInputFilter(8, true, "最多只能输入8位金额");
        filter.setMessage("最多只能输入8位金额");
        etMemberPrice.setFilters(new InputFilter[]{filter});
        etDefaultPrice.setFilters(new InputFilter[]{filter});
        edigoodsunitp.setFilters(new InputFilter[]{filter});
    }

    private void initView() {
        mIvUploadGoodsImage = (ShapedImageView) findViewById(R.id.iv_upload_goods_image);
        ivsaoma = (ImageView) findViewById(R.id.iv_saoma);
        edigoodsname = (EditText) findViewById(R.id.edi_addgoodsgoodsname_activity);
        edigoodscode = (EditText) findViewById(R.id.edi_addgoodsgoodscode_activity);
        tvgoodstype = (TextView) findViewById(R.id.edi_addgoodsgoodstype_activity);
        edigoodsunitp = (EditText) findViewById(R.id.edi_addgoodsgoodsunitp_activity);
        edigoodssimcode = (EditText) findViewById(R.id.edi_addgoodsgoodssimtype_activity);
        edigoodsmetering = (EditText) findViewById(R.id.edi_addgoodsgoodsmetering_activity);
        edigoodsbrand = (EditText) findViewById(R.id.edi_addgoodsgoodsbrand_activity);
        edigoodsmodel = (EditText) findViewById(R.id.edi_addgoodsgoodsmodel_activity);
        tvkeep = (TextView) findViewById(R.id.tv_addgoodskeep_activity);
        tv_back_activity = (TextView) findViewById(R.id.tv_back_activity);
        spGoodsType = (Spinner) findViewById(R.id.sp_addgoods_goodtype);
        etFixedPoint = (EditText) findViewById(R.id.et_add_good_fixed_point);
        etLowerDis = (EditText) findViewById(R.id.et_add_good_lower_dis);
        etSpcielDis = (EditText) findViewById(R.id.et_add_good_special_dis);
        etSoreWarn = (EditText) findViewById(R.id.et_add_good_warn_num);
        spPoint = (Spinner) findViewById(R.id.sp_add_goods_point);
        rbClose = (RadioButton) findViewById(R.id.rb_add_good_dis_close);
        rbOpen = (RadioButton) findViewById(R.id.rb_add_good_dis_open);
        rgDisSwitch = (RadioGroup) findViewById(R.id.rg_add_good_dis_switch);
        mCustomPopWindow = new CustomPopWindow(this);
        etDefaultPrice = (EditText) findViewById(R.id.et_add_good_default_price);
        etMemberPrice = (EditText) findViewById(R.id.et_add_good_member_price);
        etStock = (EditText) findViewById(R.id.et_add_good_stock);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 55 && data != null) {
            goodstype = data.getStringExtra("PT_Name");
            ptid = data.getStringExtra("PT_GID");
            tvgoodstype.setText(goodstype);

        }
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            edigoodscode.setText(scanResult);
        }
        switch (requestCode) {
            case IMAGE_REQUEST_CODE://版本<7.0  图库后返回
                if (data != null) {
                    startPhotoZoom(data.getData());
//                    mIvUploadGoodsImage.setImageURI(data.getData());
                }
                break;
            case SELECT_PIC_KITKAT://版本>= 7.0
                if (data != null && GetImagePath.getPath(getApplicationContext(), data.getData()) != null) {
                    File imgUri = new File(GetImagePath.getPath(getApplicationContext(), data.getData()));
                    Uri dataUri = FileProvider.getUriForFile(AddGoodsActivity.this, MyApplication.FILE_ADDRESS, imgUri);
                    startPhotoZoom(dataUri);
//                    mIvUploadGoodsImage.setImageURI(dataUri);
                }
                break;
            case CAMERA_REQUEST_CODE: //照相后返回
                if (resultCode ==RESULT_OK){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri inputUri = FileProvider.getUriForFile(AddGoodsActivity.this, MyApplication.FILE_ADDRESS, mCameraFile);//通过FileProvider创建一个content类型的Uri
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
                if (resultCode ==RESULT_OK){
                    Uri inputUri = FileProvider.getUriForFile(AddGoodsActivity.this,
                            MyApplication.FILE_ADDRESS, mCropFile);//通过FileProvider创建一个content类型的Uri
                    mUri = inputUri;
                    postUploadGoodsPhoto();
//                    mIvUploadGoodsImage.setImageURI(mUri);
                }

                break;
        }
        if (requestCode == 10086 && resultCode == 10086) {
            Uri imageUri = Uri.parse(data.getStringExtra("memberPhoto"));
            mGoodsImageAddress = data.getStringExtra("result");
            mUri = imageUri;
            mIvUploadGoodsImage.setImageURI(imageUri);
        }

    }

    /**
     * 拍照
     */
    private void jump2Caramel() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
            Uri uriForFile = FileProvider.getUriForFile(AddGoodsActivity.this,
                    MyApplication.FILE_ADDRESS, mCameraFile);
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
            intentFromCapture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intentFromCapture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile));
        }
        startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
    }

    /**
     * 从相册中选择
     */
    private void jump2Album() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
            Uri uriForFile = FileProvider.getUriForFile(AddGoodsActivity.this, MyApplication.FILE_ADDRESS, mGalleryFile);
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
            if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
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
        startActivityForResult(intent, RESULT_REQUEST_CODE);//这里就将裁剪后的图片的Uri返回了
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
                if (mGoodsImageAddress.contains("http")) {
                    Glide.with(AddGoodsActivity.this).load(mGoodsImageAddress).into(mIvUploadGoodsImage);
                } else {
                    String url = mGoodsImageAddress;
                    if (url.contains("../")) {
                        String[] s = url.split("\\.\\./");
                        url = s[2];
                    }
                    Glide.with(AddGoodsActivity.this).load(MyApplication.IMAGE_URL + "/"+url).into(mIvUploadGoodsImage);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddGoodsActivity.this, msg, Toast.LENGTH_SHORT).show();
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
}
