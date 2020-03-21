package com.zhiluo.android.yunpu.gift.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.SuccessBean;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsChooseCategoryListActivity;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.jsonbean.MemberPhotoBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.ChoiceTypeActivity;
import com.zhiluo.android.yunpu.ui.activity.tc.AddTCActivity;
import com.zhiluo.android.yunpu.ui.view.CustomPopWindow;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.GetImagePath;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

/**
 * 修改礼品
 */
public class GiftsModificationActivity extends BaseActivity implements CustomPopWindow.OnItemClickListener {

    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_modify_gift_save)
    TextView tvSave;
    @Bind(R.id.et_modify_gift_name)
    EditText etName;
    @Bind(R.id.et_modify_gift_code)
    EditText etCode;
    @Bind(R.id.iv_modify_gift_scan)
    ImageView ivScan;
    @Bind(R.id.et_modify_gift_type)
    TextView etType;
    @Bind(R.id.ll_modify_gift_type)
    LinearLayout rlType;
    @Bind(R.id.et_modify_gift_need_integral)
    EditText etNeedIntegral;
    @Bind(R.id.et_modify_gift_reference_price)
    EditText etReferencePrice;
    @Bind(R.id.et_modify_gift_simple_code)
    EditText etSimpleCode;
    @Bind(R.id.et_modify_gift_unit)
    EditText etUnit;
    @Bind(R.id.et_modify_gift_model)
    EditText etModel;
    @Bind(R.id.et_modify_gift_brand)
    EditText etBrand;
    @Bind(R.id.et_modify_gift_sync)
    EditText etSync;
    @Bind(R.id.et_modify_gift_warn_num)
    EditText etWarnNum;
    @Bind(R.id.iv_upload_gift_image)
    ImageView ivUploadGiftImage;
    @Bind(R.id.modify_gift)
    LinearLayout modifyGift;

    private GoodsCheckResponseByType.DataBean.DataListBean mGift;//礼品信息
    private String mTypeGid;//类型Gid
    private SweetAlertDialog mSweetAlertDialog;
    private int mSyncType;//同步类型
    /**
     * 修改图片相关
     */
    private String mGoodsImageAddress;//商品图片地址
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_gifts_modification);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    private void init() {
        mCustomPopWindow = new CustomPopWindow(this);
        Intent intent = getIntent();
        mGift = (GoodsCheckResponseByType.DataBean.DataListBean) intent.getSerializableExtra("giftdetail");
        if (mGift.getPM_BigImg() != null) {
            mGoodsImageAddress = mGift.getPM_BigImg();
            if (mGift.getPM_BigImg().contains("http")) {
                Glide.with(GiftsModificationActivity.this).load(mGift.getPM_BigImg()).into(ivUploadGiftImage);
            } else {
                Glide.with(GiftsModificationActivity.this).load(HttpAPI.API().MAIN_DOMAIN +
                        mGift.getPM_BigImg()).into(ivUploadGiftImage);
            }
        } else {
            ivUploadGiftImage.setImageResource(R.drawable.defalut_goods);
        }
        mTypeGid = mGift.getPT_ID();
        if (mGift.getPT_Name() != null) {
            etType.setText(mGift.getPT_Name());
        }
        etName.setText(mGift.getPM_Name());
        etCode.setText(mGift.getPM_Code());
        etNeedIntegral.setText("" + mGift.getPM_UnitPrice());
        etReferencePrice.setText("" + mGift.getPM_PurchasePrice());
        etSimpleCode.setText(mGift.getPM_SimpleCode() == null ? "" : mGift.getPM_SimpleCode());
        etUnit.setText(mGift.getPM_Metering() == null ? "" : mGift.getPM_Metering());
        etModel.setText(mGift.getPM_Modle() == null ? "" : mGift.getPM_Modle());
        etBrand.setText(mGift.getPM_Brand() == null ? "" : mGift.getPM_Brand());
        etWarnNum.setText("" + mGift.getPM_StockPoliceValue());
        if (mGift.getPM_SynType() == 1) {
            mSyncType = 1;
            etSync.setText("同步");
        } else {
            etSync.setText("不同步");
            mSyncType = 0;
        }
    }

    private void setListener() {
        mCustomPopWindow.setOnItemClickListener(this);

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyInputLegal()) {
                    modifyGift();
                } else {
                    new SweetAlertDialog(GiftsModificationActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("请检查输入是否正确！")
                            .setConfirmText("了解")
                            .show();
                }
            }
        });
        //选择礼品类型
        rlType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiftsModificationActivity.this, ChoiceTypeActivity.class);
                startActivityForResult(intent, 55);
            }
        });
        //返回
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ivUploadGiftImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomPopWindow.showAtLocation(GiftsModificationActivity.this.findViewById(R.id.modify_gift),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        //扫码
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(GiftsModificationActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
        //金额过滤器
        MoneyInputFilter filter = new MoneyInputFilter(8, true, "最多只能输入8位金额");
        filter.setMessage("最多只能输入8位金额");
        etReferencePrice.setFilters(new InputFilter[]{filter});
    }

    /**
     * 验证输入是否合法
     *
     * @return
     */
    private boolean verifyInputLegal() {
        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(this, "请输入礼品名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etCode.getText())) {
            CustomToast.makeText(this, "请输入礼品编码", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etType.getText())) {
            CustomToast.makeText(this, "请选择礼品分类", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etNeedIntegral.getText())) {
            CustomToast.makeText(this, "请输入兑换礼品所需积分", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etReferencePrice.getText())) {
            CustomToast.makeText(this, "请输入礼品参考进价", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 修改礼品
     */
    private void modifyGift() {
        RequestParams params = new RequestParams();
        params.put("PT_ID", mTypeGid);
        params.put("PT_Name", etType.getText().toString());
        params.put("PM_Code", etCode.getText().toString());
        params.put("PM_Name", etName.getText().toString());
        params.put("PM_SimpleCode", TextUtils.isEmpty(etSimpleCode.getText()) ? "" : etSimpleCode.getText().toString());
        params.put("PM_Metering", TextUtils.isEmpty(etUnit.getText()) ? "" : etUnit.getText().toString());
        params.put("PM_UnitPrice", etNeedIntegral.getText().toString());
        params.put("PM_Modle", TextUtils.isEmpty(etModel.getText()) ? "" : etModel.getText().toString());
        params.put("PM_Brand", TextUtils.isEmpty(etBrand.getText()) ? "" : etBrand.getText().toString());
        params.put("PM_SynType", mSyncType);
        params.put("PM_IsService", 2);
        params.put("PM_Remark", "");
        params.put("PM_Detail", "");
        params.put("GID", mGift.getGID());
        params.put("PM_BigImg", mGoodsImageAddress);
        params.put("PM_StockPoliceValue", TextUtils.isEmpty(etWarnNum.getText()) ? "" : etWarnNum.getText().toString());
        params.put("PM_PurchasePrice", etReferencePrice.getText().toString());//参考进价
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(GiftsModificationActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("修改礼品成功！");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        Intent intent = new Intent(GiftsModificationActivity.this, GiftsManagementListActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                mSweetAlertDialog = new SweetAlertDialog(GiftsModificationActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("提示");
                mSweetAlertDialog.setContentText(msg + "!");
                mSweetAlertDialog.setConfirmText("了解");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                    }
                });
                mSweetAlertDialog.show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, HttpAPI.API().EDITGOODS, params, callBack);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 55&&resultCode==55) {
            String typeName = data.getStringExtra("PT_Name");
            if (typeName != null && !"".equals(typeName)) {
                mTypeGid = data.getStringExtra("PT_GID");
                etType.setText(typeName);
            }
        }
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            etCode.setText(scanResult);
        }

        switch (requestCode) {
            case IMAGE_REQUEST_CODE://版本<7.0  图库后返回
                if (data != null) {
                    startPhotoZoom(data.getData());
//                    ivUploadGiftImage.setImageURI(data.getData());
                }
                break;
            case SELECT_PIC_KITKAT://版本>= 7.0
                if (data != null) {
                    try {
                        File imgUri = new File(GetImagePath.getPath(getApplicationContext(), data.getData()));
                        Uri dataUri = FileProvider.getUriForFile(GiftsModificationActivity.this, MyApplication.FILE_ADDRESS, imgUri);
                        startPhotoZoom(dataUri);
//                        ivUploadGiftImage.setImageURI(dataUri);
                    } catch (Exception e) {
                        break;
                    }
                }
                break;
            case CAMERA_REQUEST_CODE: //照相后返回
                if (resultCode ==RESULT_OK){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri inputUri = FileProvider.getUriForFile(GiftsModificationActivity.this,
                                MyApplication.FILE_ADDRESS, mCameraFile);//通过FileProvider创建一个content类型的Uri
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
                    Uri inputUri = FileProvider.getUriForFile(GiftsModificationActivity.this,
                            MyApplication.FILE_ADDRESS, mCropFile);//通过FileProvider创建一个content类型的Uri
                    mUri = inputUri;
                    postUploadGoodsPhoto();
//                    ivUploadGiftImage.setImageURI(mUri);
                }
                break;
        }
        if (requestCode == 10086 && resultCode == 10086) {
            Uri imageUri = Uri.parse(data.getStringExtra("memberPhoto"));
            mGoodsImageAddress = data.getStringExtra("result");
            mUri = imageUri;
            ivUploadGiftImage.setImageURI(imageUri);
        }
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
                    Glide.with(GiftsModificationActivity.this).load(mGoodsImageAddress).into(ivUploadGiftImage);
                } else {
                    String url = mGoodsImageAddress;
                    if (url.contains("../")) {
                        String[] s = url.split("\\.\\./");
                        url = s[2];
                    }
                    Glide.with(GiftsModificationActivity.this).load(HttpAPI.API().MAIN_DOMAIN + "/"+url).into(ivUploadGiftImage);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(GiftsModificationActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在上传图片...", false);
        HttpHelper.post(this, HttpAPI.API().UPLOAD_MEMBER_PHOTO, params, callBack);
    }

    /**
     * 拍照
     */
    private void jump2Caramel() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
            Uri uriForFile = FileProvider.getUriForFile(GiftsModificationActivity.this,
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
            Uri uriForFile = FileProvider.getUriForFile(GiftsModificationActivity.this, MyApplication.FILE_ADDRESS, mGalleryFile);
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
        startActivityForResult(intent, RESULT_REQUEST_CODE);//这里就将裁剪后的图片的Uri返回了
    }
}
