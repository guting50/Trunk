package com.zhiluo.android.yunpu.member.activity;

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
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomPopWindow;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.jsonbean.MemberPhotoBean;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.GetImagePath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;

/**
 * 上传会员头像Activity
 */
public class MemberPhotoActivity extends BaseActivity implements CustomPopWindow.OnItemClickListener {
    private ImageView ivMemberSelect;//选择上传方式：从相册中选择/手机拍照
    private ImageView ivMemberPhoto;//显示上传的头像
    private CustomPopWindow mCustomPopWindow;//自定义PopWindow控件声明
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
    private MemberPhotoBean memberPhotoBean;//上传会员头像响应结果
    private Intent mIntent = new Intent();//给上一个Activity回传数据（图片本地Uri，上传图片返回的图片服务器路径）
    private Handler mHandler;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_photo);
        initView();
        initVariable();
        setListener();

    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 100:
                        setResultForAddMenberActivity(mUri);
                        break;
                }
            }
        };
    }

    /**
     * 初始化控件
     */
    private void initView() {
        ivMemberSelect = (ImageView) findViewById(R.id.iv_member_more);
        ivMemberPhoto = (ImageView) findViewById(R.id.iv_member_photo_large);
        mCustomPopWindow = new CustomPopWindow(MemberPhotoActivity.this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE_REQUEST_CODE://版本<7.0  图库后返回
                startPhotoZoom(data.getData());
                break;
            case SELECT_PIC_KITKAT://版本>= 7.0
                File imgUri = new File(GetImagePath.getPath(getApplicationContext(), data.getData()));
                Uri dataUri = FileProvider.getUriForFile(MemberPhotoActivity.this, MyApplication.FILE_ADDRESS, imgUri);
                startPhotoZoom(dataUri);
                break;
            case CAMERA_REQUEST_CODE: //照相后返回
                if (resultCode ==RESULT_OK){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri inputUri = FileProvider.getUriForFile(MemberPhotoActivity.this, MyApplication.FILE_ADDRESS, mCameraFile);//通过FileProvider创建一个content类型的Uri
                        startPhotoZoom(inputUri);//设置输入类型
                    } else {
                        Uri inputUri = Uri.fromFile(mCameraFile);
                        startPhotoZoom(inputUri);
                    }
                }

                break;
            case RESULT_REQUEST_CODE://剪裁结果用ImageView显示
                if (resultCode ==RESULT_OK){
                    Uri inputUri = FileProvider.getUriForFile(MemberPhotoActivity.this,
                            MyApplication.FILE_ADDRESS, mCropFile);//通过FileProvider创建一个content类型的Uri
                    ivMemberPhoto.setImageURI(inputUri);
                    mUri = inputUri;
                    postUploadMemberPhoto();
                    Log.i(TAG, "onActivityResult: " + inputUri.toString());
                }

                break;
        }
    }

    /**
     * 设置监听
     */
    private void setListener() {
        mCustomPopWindow.setOnItemClickListener(this);
        //设置监听，弹出选择PopWindow，选择从相册中上传还是手机拍照
        ivMemberSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomPopWindow.showAtLocation(MemberPhotoActivity.this.findViewById(R.id.ll_member_photo_activity),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        //返回
        findViewById(R.id.tv_goods_comsume_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
     * 拍照
     */
    private void jump2Caramel() {
        Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
            Uri uriForFile = FileProvider.getUriForFile(MemberPhotoActivity.this,
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
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
            Uri uriForFile = FileProvider.getUriForFile(MemberPhotoActivity.this, MyApplication.FILE_ADDRESS, mGalleryFile);
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
    private void postUploadMemberPhoto() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        try {
            params.put("photo", mCropFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        client.post(HttpAPI.API().UPLOAD_MEMBER_PHOTO, params, new AsyncHttpResponseHandler() {
            public String resurt;
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    try {
                        resurt = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        memberPhotoBean = CommonFun.JsonToObj(resurt, MemberPhotoBean.class);
                        Log.i(TAG, "onSuccess: "+memberPhotoBean.toString());
                        mHandler.sendEmptyMessage(100);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(TAG, "onFailure: "+"上传失败");
                Log.i(TAG, "onFailure: "+statusCode+"::"+error);
            }
        });
    }


    /**
     * setResult,设置Activity退出时，返回给上一个Activity的值
     * @param uri
     */
    private void setResultForAddMenberActivity(Uri uri){
        mIntent.putExtra("memberPhoto", uri.toString());
        if (memberPhotoBean != null){
            mIntent.putExtra("result",memberPhotoBean.getData());
        }
        setResult(10086, mIntent);
        finish();
    }
}
