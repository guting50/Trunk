package com.zhiluo.android.yunpu.ui.activity.tc;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.TCBean;
import com.zhiluo.android.yunpu.entity.TCGoodBean;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.jsonbean.MemberPhotoBean;
import com.zhiluo.android.yunpu.ui.adapter.InitTCAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomPopWindow;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyListView;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CheckPromiss;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.ExamineTextWatcher;
import com.zhiluo.android.yunpu.utils.GetImagePath;
import com.zhiluo.android.yunpu.utils.ShowDialog;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Date: 2018/1/27 10:55
 * Author: 罗咏哲
 * Description: 编辑套餐
 * Version:
 */
public class EditTCActivity extends AppCompatActivity implements CustomPopWindow.OnItemClickListener,
        InitTCAdapter.IItemClick {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_edit_tc_save)
    TextView tvSave;
    @Bind(R.id.et_edit_tc_code)
    EditText etCode;
    @Bind(R.id.iv_edit_tc_scan)
    ImageView ivScan;
    @Bind(R.id.et_edit_tc_name)
    EditText etName;
    @Bind(R.id.iv_upload_tv_picture)
    ShapedImageView mIvUploadGoodsImage;
    @Bind(R.id.et_edit_tc_price)
    EditText etPrice;
    @Bind(R.id.et_edit_tc_model)
    EditText etModel;
    @Bind(R.id.et_edit_tc_dis)
    EditText etDis;
    @Bind(R.id.et_edit_tc_integral)
    EditText etIntegral;
    @Bind(R.id.et_edit_tc_remark)
    EditText etRemark;
    @Bind(R.id.btn_clear)
    Button btnClear;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.lv_edit_tc)
    MyListView lvTc;
    @Bind(R.id.te_tc_type)
    TextView teTcType;

    private float price;
    private TCBean mTcBean;
    private List<GoodsCheckResponseByType.DataBean.DataListBean> mHaveChoicedGoodList;
    private InitTCAdapter mInitTCAdapter;
    private SweetAlertDialog mSweetAlertDialog;
    /**
     * 商品图片相关
     */
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
    private Dialog chooseDialog;
    private int mTcType;
    private ArrayList<String> mTcTypeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tc);
        ButterKnife.bind(this);
        etCode.requestFocus();
        init();
        update();
        setListener();
    }

    private void init() {
        mHaveChoicedGoodList = new ArrayList<>();
        mTcBean = (TCBean) getIntent().getSerializableExtra("TC");
        mCustomPopWindow = new CustomPopWindow(this);
   

        mTcTypeList = new ArrayList<>();
        mTcTypeList.add("普通套餐");
        mTcTypeList.add("充次套餐");

        etPrice.setEnabled(false);
        etPrice.setBackgroundColor(getResources().getColor(R.color.lightgray));

    }

    private void update() {
        if (mTcBean != null) {
            //设置图片
            if (mTcBean.getPC_Image() != null) {
                mGoodsImageAddress = mTcBean.getPC_Image();
                if (mTcBean.getPC_Image().contains("http")) {
                    Glide.with(this).load(mTcBean.getPC_Image()).into(mIvUploadGoodsImage);
                } else {
                    String url = mTcBean.getPC_Image();
                    if (url.contains("../")) {
                        String[] s = url.split("\\.\\./");
                        url = s[2];
                    }
                    Glide.with(this).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).
                            into(mIvUploadGoodsImage);
                }
            } else {
                mIvUploadGoodsImage.setImageResource(R.drawable.ysl_goods);
            }
            etCode.setText(mTcBean.getPC_Code());
            etCode.setSelection(etCode.getText().toString().length());
            etName.setText(mTcBean.getPC_Name());
            etPrice.setText(Decima2KeeplUtil.stringToDecimal("" + mTcBean.getPC_Price()));
            etModel.setText(mTcBean.getPC_specification() == null ? "" : mTcBean.getPC_specification());
            etDis.setText(Decima2KeeplUtil.stringToDecimal("" + mTcBean.getPC_Discount()));
            etIntegral.setText("" + mTcBean.getPC_Integral());
            teTcType.setText(mTcBean.getPC_Type()==0?"普通套餐":"充次套餐");
            mTcType = mTcBean.getPC_Type();

            etRemark.setText(mTcBean.getPC_Remark() == null ? "" : mTcBean.getPC_Remark());
            if (mTcBean.getPC_ProductJson() != null) {
                Type type = new TypeToken<ArrayList<TCGoodBean>>() {
                }.getType();
                Gson gson = new Gson();
                ArrayList<TCGoodBean> beans = CommonFun.JsonToObj(mTcBean.getPC_ProductJson(), type);
                for (int i = 0; i < beans.size(); i++) {
                    GoodsCheckResponseByType.DataBean.DataListBean bean = new GoodsCheckResponseByType.DataBean.DataListBean();
                    if (beans.get(i).getPM_Number() == null) {
                        bean.setNum(0);
                    } else {
                        bean.setNum(Double.parseDouble(beans.get(i).getPM_Number()));
                    }
                    bean.setGID(beans.get(i).getGID());
                    bean.setPM_Name(beans.get(i).getPM_Name());
                    mHaveChoicedGoodList.add(bean);
                }
                mInitTCAdapter = new InitTCAdapter(EditTCActivity.this, mHaveChoicedGoodList, EditTCActivity.this);
                lvTc.setAdapter(mInitTCAdapter);
            }
        }
    }

    private void setListener() {
        mCustomPopWindow.setOnItemClickListener(this);
        
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        teTcType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showtypeDialog(mTcTypeList,teTcType);
            }
        });
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(EditTCActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
        mIvUploadGoodsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCustomPopWindow.showAtLocation(EditTCActivity.this.findViewById(R.id.rl_edit_tc_head),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTCActivity.this, ChoiceGoodsActivity.class);
                intent.putExtra("mHaveChoicedGoodList", (Serializable) mHaveChoicedGoodList);
                
                intent.putExtra("TCTYPE",mTcType);
                startActivityForResult(intent, 200);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHaveChoicedGoodList != null && mHaveChoicedGoodList.size() > 0) {
                    mHaveChoicedGoodList.clear();
                    mInitTCAdapter.notifyDataSetChanged();
                }
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify()) {
                    edit();
                }
            }
        });
        etDis.addTextChangedListener(new ExamineTextWatcher(etDis));
    }

     private void showtypeDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
  public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        mTcType = position;
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            etCode.setText(scanResult);
        }
        if (requestCode == 200 && resultCode == 200) {
            mHaveChoicedGoodList = (List<GoodsCheckResponseByType.DataBean.DataListBean>) data.getSerializableExtra("GOODS");
            mInitTCAdapter = new InitTCAdapter(EditTCActivity.this, mHaveChoicedGoodList, EditTCActivity.this);
            lvTc.setAdapter(mInitTCAdapter);
            allPrice();
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
                    Uri dataUri = FileProvider.getUriForFile(EditTCActivity.this, MyApplication.FILE_ADDRESS, imgUri);
                    startPhotoZoom(dataUri);
//                    mIvUploadGoodsImage.setImageURI(dataUri);
                }
                break;
            case CAMERA_REQUEST_CODE: //照相后返回
                
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri inputUri = FileProvider.getUriForFile(EditTCActivity.this, MyApplication.FILE_ADDRESS, mCameraFile);//通过FileProvider创建一个content类型的Uri
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
                    Uri inputUri = FileProvider.getUriForFile(EditTCActivity.this,
                            MyApplication.FILE_ADDRESS, mCropFile);//通过FileProvider创建一个content类型的Uri
                    mUri = inputUri;
                    postUploadGoodsPhoto();
                }
//                mIvUploadGoodsImage.setImageURI(mUri);
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
        if (CheckPromiss.hasCamera()) {
            if (CheckPromiss.checkPermission(EditTCActivity.this)) {
                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
                    Uri uriForFile = FileProvider.getUriForFile(EditTCActivity.this,
                            MyApplication.FILE_ADDRESS, mCameraFile);
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile));
                }
                startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
           
            } else {
                ShowDialog.warnDialog(EditTCActivity.this, "未获取到相机权限，请确认后尝试！");
            }

       
        } else {
            CustomToast.makeText(EditTCActivity.this, "未获取到相机，请确认后再尝试！", Toast.LENGTH_SHORT).show();
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
            Uri uriForFile = FileProvider.getUriForFile(EditTCActivity.this, MyApplication.FILE_ADDRESS, mGalleryFile);
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
                    Glide.with(EditTCActivity.this).load(mGoodsImageAddress).into(mIvUploadGoodsImage);
                } else {
                    String url = mGoodsImageAddress;
                    if (url.contains("../")) {
                        String[] s = url.split("\\.\\./");
                        url = s[2];
                    }
                    Glide.with(EditTCActivity.this).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).into(mIvUploadGoodsImage);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditTCActivity.this, msg, Toast.LENGTH_SHORT).show();
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

    @Override
    public void itemClick(View view) {

        int position = (int) view.getTag();
        double num;
        num = mHaveChoicedGoodList.get(position).getNum();
        switch (view.getId()) {

            //移除
            case R.id.iv_reduce:
                if (num > 0) {
                    num--;
                } else {
                    num = 0;
                }
                if (num > 0) {
                    mHaveChoicedGoodList.get(position).setNum(num);
                    allPrice();
                }
                break;
            //增加
            case R.id.iv_add:
                num++;
                mHaveChoicedGoodList.get(position).setNum(num);
                allPrice();
                break;
            case R.id.iv_item_init_delete:

                mHaveChoicedGoodList.remove(position);
                if (mHaveChoicedGoodList.size()>0){
                    allPrice();
                }else {
                    etPrice.setText("");
                }
                break;
        }
        if (mInitTCAdapter != null) {
            mInitTCAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 计算套餐售价
     */
    private void allPrice(){
        price = 0.00f;
        for (int i = 0;i < mHaveChoicedGoodList.size();i++){
            price += mHaveChoicedGoodList.get(i).getPM_UnitPrice()*mHaveChoicedGoodList.get(i).getNum();
        }
        etPrice.setText(Decima2KeeplUtil.stringToDecimal(price+""));
    }

    /**
     * 验证输入合法
     *
     * @return
     */
    private boolean verify() {
        if (TextUtils.isEmpty(etCode.getText())) {
            CustomToast.makeText(EditTCActivity.this, "套餐编号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(EditTCActivity.this, "套餐名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etPrice.getText())) {
            CustomToast.makeText(EditTCActivity.this, "套餐售价不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mHaveChoicedGoodList == null || mHaveChoicedGoodList.size() == 0) {
            CustomToast.makeText(EditTCActivity.this, "套餐商品不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * 新增套餐
     */
    private void edit() {
        RequestParams params = new RequestParams();
        params.put("GID", mTcBean.getGID());
        params.put("PC_Code", etCode.getText().toString());
        params.put("PC_Name", etName.getText().toString());
        params.put("PC_specification", TextUtils.isEmpty(etModel.getText()) ? "" : etModel.getText().toString());
        params.put("PC_Price", etPrice.getText().toString());
        params.put("PC_SynState", mTcBean.getPC_SynState());
        params.put("PC_Type",mTcType);
        params.put("PC_Remark", TextUtils.isEmpty(etRemark.getText()) ? "" : etRemark.getText().toString());
        params.put("PC_Discount", TextUtils.isEmpty(etDis.getText()) ? "" : etDis.getText().toString());
        params.put("PC_Integral", TextUtils.isEmpty(etIntegral.getText()) ? "" : etIntegral.getText().toString());
        params.put("PC_Image", mGoodsImageAddress);
        for (int i = 0; i < mHaveChoicedGoodList.size(); i++) {
            if (mHaveChoicedGoodList.get(i).getPM_IsService() ==0&&mTcType ==1){
                CustomToast.makeText(EditTCActivity.this, "充次套餐不能包含普通商品", Toast.LENGTH_SHORT).show();
            }
            params.put("PC_ProductJsonList[" + i + "][GID]", mHaveChoicedGoodList.get(i).getGID());
            params.put("PC_ProductJsonList[" + i + "][PM_Name]", mHaveChoicedGoodList.get(i).getPM_Name());
            params.put("PC_ProductJsonList[" + i + "][PM_Number]", mHaveChoicedGoodList.get(i).getNum());
        }
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(EditTCActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("编辑成功");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();

                        ActivityManager.getInstance().exit();
                        Intent intent = new Intent(EditTCActivity.this, TCManagerActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditTCActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(EditTCActivity.this, "提交中...", false);
        HttpHelper.post(this, "ProductCombo/EditProductCombo", params, callBack);
    }
}
