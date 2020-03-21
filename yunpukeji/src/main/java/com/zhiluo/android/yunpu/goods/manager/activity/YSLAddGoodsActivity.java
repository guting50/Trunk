package com.zhiluo.android.yunpu.goods.manager.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.adapters.AddGooodsAdapter;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.fragments.AddGiftFragment;
import com.zhiluo.android.yunpu.fragments.OrdinaryGoodsFragment;
import com.zhiluo.android.yunpu.fragments.ServiceFragment;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.jsonbean.MemberPhotoBean;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.manager.activity.ModifyMemberActivity;
import com.zhiluo.android.yunpu.ui.view.CustomPopWindow;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CheckPromiss;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.GetImagePath;
import com.zhiluo.android.yunpu.utils.ShowDialog;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Lb;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * Created by ${YSL} on 2018-04-20.
 */

public class YSLAddGoodsActivity extends AppCompatActivity implements CustomPopWindow.OnItemClickListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private String mGoodsClass = "";//商品类型，传给fragment中去，
    private Bundle mBundle;
    private int whichOne;
    private MyHandler myHandler;
    private OrdinaryGoodsFragment f1;
    private ServiceFragment f2;
    private AddGiftFragment f3;
    private TextView teSave, teAddMore;
    private String goodstype;
    private String ptid;
    private int mGoodsSyncType;
    private Uri uirBackToFragment;
    private int flags = 0;
    private static int mPositon = 1;
    private final String GoodsRecevier = "android.intent.action.MY_BROADCAST";
    private final String SerRecevier = "android.intent.action.Ser_BROADCAST";
    private final String GiftRecevier = "android.intent.action.Gift_BROADCAST";

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
    private GoodsReceiver goodsreceiver;
    private AddGooodsAdapter myPagerAdapter;
    private ArrayList<Fragment> fragments;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_add_goods);
        initView();
        initViewPager();
        listener();
        if (goodsreceiver == null) {
            goodsreceiver = new GoodsReceiver();
        }
        IntentFilter filer1 = new IntentFilter();
        filer1.addAction("android.intent.action.Goods_BROADCAST");
        registerReceiver(goodsreceiver, filer1);

        sendIntent();

    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_add_goods);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        teSave = findViewById(R.id.te_save);
        teAddMore = findViewById(R.id.te_add_more);
        myHandler = new MyHandler();
        mBundle = new Bundle();
        mCustomPopWindow = new CustomPopWindow(this);
        myPagerAdapter = new AddGooodsAdapter(getSupportFragmentManager());

        teSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();

            }
        });
        teAddMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMore();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(goodsreceiver);

    }

    private void listener() {
        mCustomPopWindow.setOnItemClickListener(this);//上传图片

        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YSLAddGoodsActivity.this.finish();
//                startActivity(new Intent(YSLAddGoodsActivity.this, GoodsManagerActivity.class));
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
            YSLAddGoodsActivity.this.finish();
//            startActivity(new Intent(YSLAddGoodsActivity.this, GoodsManagerActivity.class));
            break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initViewPager() {
        f1 = new OrdinaryGoodsFragment();
        f2 = new ServiceFragment();
        f3 = new AddGiftFragment();
        // 创建一个集合,装填Fragment
        fragments = new ArrayList<>();
        // 装填
        fragments.add(f1);
        fragments.add(f2);
        fragments.add(f3);
        // 创建ViewPager适配器
        myPagerAdapter.setFragments(fragments);
        // 给ViewPager设置适配器
        mViewPager.setAdapter(myPagerAdapter);
        // TabLayout 指示器 (记得自己手动创建3个Fragment,注意是 app包下的Fragment 还是 V4包下的 Fragment)
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        mTabLayout.addTab(mTabLayout.newTab());
        // 使用 TabLayout 和 ViewPager 相关联
        mTabLayout.setupWithViewPager(mViewPager);
        // TabLayout指示器添加文本
        mTabLayout.getTabAt(0).setText("普通商品");
        mTabLayout.getTabAt(1).setText("服务商品");
        mTabLayout.getTabAt(2).setText("礼品");
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        mPositon = 1;
                        handler.sendEmptyMessage(1);
                        break;
                    case 1:
                        mPositon = 2;
                        handler.sendEmptyMessage(2);
                        break;
                    case 2:
                        mPositon = 3;
                        handler.sendEmptyMessage(3);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            sendInt("goodsclass", msg.what);
        }
    };

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            sendDatas("goodcode", scanResult);//扫面返回的编码
        }
        if (resultCode == 55 && data != null) {
            goodstype = data.getStringExtra("PT_Name");//选择商品的
            ptid = data.getStringExtra("PT_GID");
            mGoodsSyncType = data.getIntExtra("PT_SYNC", 0);//0同步，1不同步
            Lb(goodstype + "------" + ptid + "--------" + mGoodsSyncType);
            Bundle bundle = new Bundle();
            bundle.putString("PT_Name", goodstype);
            bundle.putString("PT_GID", ptid);
            bundle.putInt("PT_SYNC", mGoodsSyncType);
            sendBundle("goodstype", bundle);
        }
        switch (requestCode) {
            case IMAGE_REQUEST_CODE://版本<7.0  图库后返回
                if (data != null) {
                    startPhotoZoom(data.getData());
                    sendImgUri("imguri", data.getData());
                }
                break;
            case SELECT_PIC_KITKAT://版本>= 7.0
                if (data != null && GetImagePath.getPath(getApplicationContext(), data.getData()) != null) {
                    File imgUri = new File(GetImagePath.getPath(getApplicationContext(), data.getData()));
                    Uri dataUri = FileProvider.getUriForFile(YSLAddGoodsActivity.this, MyApplication.FILE_ADDRESS, imgUri);
                    startPhotoZoom(dataUri);
                    sendDatas("imguri", dataUri.toString());
                }
                break;
            case CAMERA_REQUEST_CODE: //照相后返回
                if (resultCode == RESULT_OK){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri inputUri = FileProvider.getUriForFile(YSLAddGoodsActivity.this, MyApplication.FILE_ADDRESS, mCameraFile);//通过FileProvider创建一个content类型的Uri
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
                if (resultCode == RESULT_OK){
                    Uri inputUri = FileProvider.getUriForFile(YSLAddGoodsActivity.this, MyApplication.FILE_ADDRESS, mCropFile);//通过FileProvider创建一个content类型的Uri
                    mUri = inputUri;
                    postUploadGoodsPhoto();
                }
                break;
        }
        if (requestCode == 10086 && resultCode == 10086) {
            Uri imageUri = Uri.parse(data.getStringExtra("memberPhoto"));
            mGoodsImageAddress = data.getStringExtra("result");
            mUri = imageUri;
            sendDatas("imguri", data.getData().toString());

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

                Bundle bundle = new Bundle();
                Intent intent = new Intent();
                bundle.putString("imguri", mUri.toString());
                bundle.putString("imgaddress", mGoodsImageAddress);
                sendBundle("imges", bundle);
                Le("发送图片地址---" + mGoodsImageAddress);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(YSLAddGoodsActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在上传图片...", false);
        HttpHelper.post(this, HttpAPI.API().UPLOAD_MEMBER_PHOTO, params, callBack);
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
     * 广播Fragment，让其执行保存
     */
    public void save() {
        Intent intent = null;
        if (mPositon == 1) {
            intent = new Intent("android.intent.action.MY_BROADCAST");
        } else if (mPositon == 2) {
            intent = new Intent("android.intent.action.Ser_BROADCAST");
        } else if (mPositon == 3) {
            intent = new Intent("android.intent.action.Gift_BROADCAST");
        }
        intent.putExtra("save", "save");
        Lb("发送 ---save--》" + mPositon);
        sendBroadcast(intent);
        flags = flags + 1;
        Lb("发送次数--------------" + flags);
    }

    /**
     * 广播Fragment，让其执行清空界面
     */
    public void addMore() {
        Intent intent = null;
        if (mPositon == 1) {
            intent = new Intent("android.intent.action.MY_BROADCAST");
        } else if (mPositon == 2) {
            intent = new Intent("android.intent.action.Ser_BROADCAST");
        } else if (mPositon == 3) {
            intent = new Intent("android.intent.action.Gift_BROADCAST");
        }
        intent.putExtra("addmore", "addmore");
        sendBroadcast(intent);
    }


    /**
     * @param info 传给Fragment的数据
     */
    private void sendDatas(String msg, String info) {
        Intent intent = null;
        if (mPositon == 1) {
            intent = new Intent("android.intent.action.MY_BROADCAST");
        } else if (mPositon == 2) {
            intent = new Intent("android.intent.action.Ser_BROADCAST");
        } else if (mPositon == 3) {
            intent = new Intent("android.intent.action.Gift_BROADCAST");
        }
        intent.putExtra(msg, info);
        sendBroadcast(intent);
    }

    /**
     * @param info 传给Fragment的数据
     */
    private void sendInt(String msg, int info) {
        Intent intent = null;
        if (info == 1) {
            intent = new Intent("android.intent.action.MY_BROADCAST");
        } else if (info == 2) {
            intent = new Intent("android.intent.action.Ser_BROADCAST");
        } else if (info == 3) {
            intent = new Intent("android.intent.action.Gift_BROADCAST");
        }
        intent.putExtra(msg, info);
        if (getIntent()!=null){
            intent.putExtra("Searchcontetnt", getIntent().getStringExtra("Searchcontetnt"));
        }
        sendBroadcast(intent);
    }

    private void sendImgUri(String msg, Uri info) {
        Intent intent = new Intent("android.intent.action.MY_BROADCAST");
        intent.putExtra(msg, info);
        sendBroadcast(intent);
    }
    private void sendIntent() {
        if (getIntent()!=null){
            Intent intent = new Intent("android.intent.action.MY_BROADCAST");
            intent.putExtra("Searchcontetnt", getIntent().getStringExtra("Searchcontetnt"));
            sendBroadcast(intent);
        }

    }

    /**
     * @param msg    ,,
     * @param bundle ,,
     */
    private void sendBundle(String msg, Bundle bundle) {
        Intent intent = null;
        if (mPositon == 1) {
            intent = new Intent("android.intent.action.MY_BROADCAST");
        } else if (mPositon == 2) {
            intent = new Intent("android.intent.action.Ser_BROADCAST");
        } else if (mPositon == 3) {
            intent = new Intent("android.intent.action.Gift_BROADCAST");
        }
        intent.putExtra(msg, bundle);
        sendBroadcast(intent);
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
        if (CheckPromiss.hasCamera()) {
            if (CheckPromiss.checkPermission(YSLAddGoodsActivity.this)) {

                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
                    Uri uriForFile = FileProvider.getUriForFile(YSLAddGoodsActivity.this,
                            MyApplication.FILE_ADDRESS, mCameraFile);
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile));
                }
                startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
            }else {
                ShowDialog.warnDialog(YSLAddGoodsActivity.this,"未获取到相机权限，请确认后尝试！");
            }

        }else {
            CustomToast.makeText(YSLAddGoodsActivity.this, "未获取到相机，请确认后再尝试！", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    /**
     * 从相册中选择
     */
    private void jump2Album() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
            Uri uriForFile = FileProvider.getUriForFile(YSLAddGoodsActivity.this, MyApplication.FILE_ADDRESS, mGalleryFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, SELECT_PIC_KITKAT);
        } else {
            startActivityForResult(intent, IMAGE_REQUEST_CODE);
        }
    }

    class GoodsReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getStringExtra("msg").equals("uploadimg")) {
                mCustomPopWindow.showAtLocation(YSLAddGoodsActivity.this.findViewById(R.id.add_goods),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        }
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    f1.setArguments(mBundle);
                    break;
                case 2:
                    f2.setArguments(mBundle);
                    break;
                case 3:
                    f3.setArguments(mBundle);
                    break;
            }
        }
    }
}
