package com.zhiluo.android.yunpu.member.manager.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.basewin.utils.StringUtil;
import com.bumptech.glide.Glide;
import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.example.liangmutian.mypicker.calendarselecter.CalendarSelector;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.activity.PayConfirmActivity;
import com.zhiluo.android.yunpu.consume.activity.SignChooseActivity;
import com.zhiluo.android.yunpu.consume.activity.StaffCommissionActivity;
import com.zhiluo.android.yunpu.goods.consume.activity.GoodsConsumeActivity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.jsonbean.MemberPhotoBean;
import com.zhiluo.android.yunpu.member.manager.adapter.addCostomfieldsAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.LabelBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberLabel;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.print.bean.HYKK_Success_Bean;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomPopWindow;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.LoadingDialogUtil;
import com.zhiluo.android.yunpu.utils.Base64Encoder;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CheckPromiss;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.GetImagePath;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;
import com.zhiluo.android.yunpu.utils.RegexUtil;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 新增会员
 * 作者：罗咏哲 on 2017/7/18 19:39.
 * 邮箱：137615198@qq.com
 */
public class AddMemberActivity extends BaseActivity implements CustomPopWindow.OnItemClickListener ,CalendarSelector
        .ICalendarSelectorCallBack, addCostomfieldsAdapter.OnImageClickEvent{
    //会员卡号，姓名，手机，开卡费用，初始金额，初始积分，推荐人卡号，邮箱，身份证，地址，备注。
    private EditText etCardNum, etMemberName, etPhoneNum, etPayCountMoney, etInitMoney, etInitPoint,
             etEmail, etID, etAddress, etRemark, etFaceNum, etInitPassword;
    private TextView tvCommission, tvBirthday, tvRecommend, tvLabel, istelview,isfacenumview, etRecommendCardNum;//提成员工，生日，推荐人,会员标签。推荐人
    private CircleImageView civHeadImg;//会员头像
    private Dialog mDialog;//加载中
    private RelativeLayout rlLabel;//会员标签布局
    private LinearLayout rlReommendStaff;
    private ImageView ivOverdueDate;
//            ,ivphone;//选择会员过期时间  选择联系人
    private TextView etOverdueDate;
    private Handler mHandler;
    private List<ReportMessageBean.DataBean.VIPGradeListBean> mMemberGrade;//会员等级实体
    private List<ReportMessageBean.DataBean.GetCustomFieldsVIPBean> costomfields;//会员自定义属性
    private List<String> mGradeNameList;//会员等级名称集合
    private ArrayAdapter<String> mGradeSpinerAdapter;//会员等级Adapter
    private ArrayAdapter<String> mPayWaySpinnerAdapter;//支付方式Adapter
    private SweetAlertDialog mSweetAlertDialog;//提示框
    private String mPayTypeCode, mPayTypeName;//支付类型编码，支付类型名字
    //卡号，会员姓名，手机号码，会员等级名称，过期时间，
    // 推荐人姓名，生日，提成员工姓名，推荐人卡号，
    // 邮箱，身份证，地址，备注。
    private String mCardNum, mMemberName, mPhoneNum, mGradeName, mOverdueDate,
            mRecommendName, mBirthday, mCommissionName, mRecommendCardNum,calaryMonth,isLunar,
            mEmail, mId, mAddress, mRemark;
    private String mGradeGid;//等级GID
    private String  mInitMoney, mInitPoint,initpoint;//开卡费用，初始金额，初始积分
    private double mMoney;
    private int mSex = 0;//性别 0 男，1 女，2 未知
  
    private int mIsForver;//是否是永久会员 1 永久 0 非永久
    private List<ReportMessageBean.DataBean.EmplistBean> mStaffInfo;//提成员工
    private ArrayList<String> mStaffListGid = new ArrayList<>();//提成员工GID
 
    private StringBuilder mStaffName;//提成员工姓名
    private MemberLabel mMemberLabel;//会员标签对象
    private List<LabelBean> mUpLabel;//上传的会员标签
    private TagFlowLayout mFlowLayout;
    LayoutInflater mInflater;
    private List<String> mLabelList;//会员标签数据
    String mLabel;//会员标签上传到服务器的字符串
    StringBuilder mBuilder;//会员标签显示内容
    private ImageView mIvScan;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean mCardContactPhone, mIsfilltel = true;//会员卡号同手机号
    private boolean mPassordSwitch;
    private int operate;
    private String inType;
    private boolean isCardNum;
    private String mcardId;


    /**
     * 会员头像相关
     */
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
    private MemberPhotoBean memberPhotoBean;//上传会员头像响应结果
    private Uri mUri;
    private String mMemberPhotoAddress = "/img/nohead.png";//会员头像地址
    private boolean canUse;
    private boolean cash = true, bank = true, zfb = false, wx = false;

   
    private TextView tvVipLevel;
    private TextView tvSex;
    private TextView tvPayWay;
    private TextView tvmemsign;
    private TextView tvSave, tvClear;
    private ImageView imgVipBirthday;
    private Dialog chooseDialog;
    private List<String> mSexList;
    private List<String> mPayWayList;
    private List<LabelBean> mLabList;
    private StringBuilder mLabName;
    private String mPayWay;
    private Dialog dateDialog;
    private RecyclerView reLabRecycle;
    private addCostomfieldsAdapter mLabAdapter;
    private Dialog datesDialog;
    private String overTime;
    private boolean isStaff;

    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;

    private AllMemberListBean.DataBean.DataListBean mMemberInfo = new AllMemberListBean.DataBean.DataListBean();//会员信息
    private AddMemberActivity.ChangeHandler changeHandler;
    private CalendarSelector mCalendarSelector;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    /**
     * 结算完成跳转
     */
    private IntentHandler intentHandler;

    //会员自定义属性图片类型
    private boolean isImageType;
    //会员自定义属性position
    private int mCustomImageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);
        initView();
        initVariable();
        initDatas();
        loadData();
        setListener();
    }

    private void initDatas() {
        Intent intent = getIntent();

        if (intent!=null){
            inType = intent.getStringExtra("intype");
        }
        mSexList = new ArrayList<>();
//        mPayWayList = new ArrayList<>();
        mLabList = new ArrayList<>();
        mSexList.add("男");
        mSexList.add("女");
        mSexList.add("保密");

        mPayWayList.add("现金支付");
//        mPayWayList.add("微信记账");
//        mPayWayList.add("支付宝记账");
//        mPayWayList.add("银联支付");

//        tvVipLevel.setText("默认等级");
        tvSex.setText("男");

        //V1S刷卡
        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            Intent nfcIntent = new Intent(this, getClass());
            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mPendingIntent = PendingIntent.getActivity(this, 0, nfcIntent, 0);
        }
    }


    //获取系统隐式启动的
    @Override
    public void onNewIntent(Intent intent) {
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        if (tagFromIntent!=null){
            String CardId = ByteArrayToHexString.ByteArrayToHex(tagFromIntent.getId());
            if (null != CardId) {
                MyApplication.VIP_CARD = CardId;
                while (CardId.length() < 10) {
                    CardId = "0" + CardId;
                }
                etCardNum.setText(CardId);
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        if (mNFCAdapter != null) {
            //隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
        /**
         * lkl、sunmi 、basewin的检卡 读卡操作
         */
        if (!MyApplication.IS_SUNMI_POS_V1S_DEVICE){
            new CardOperationUtils(this, etCardNum);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNFCAdapter != null) {
            //隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    @Override
    protected void onStop() {
        //终止检卡
        new CardOperationUtils().close();

        Log.d("TAG", "onStop: ");
        super.onStop();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        changeHandler = new ChangeHandler();

        istelview = findViewById(R.id.istel_view);
        isfacenumview = findViewById(R.id.isfacenum_view);
        rlReommendStaff = (LinearLayout) findViewById(R.id.rl_recommend_staff);
        mIvScan = (ImageView) findViewById(R.id.iv_add_member_scan);
        etCardNum = (EditText) findViewById(R.id.et_add_member_num);
        etMemberName = (EditText) findViewById(R.id.et_add_member_name);
        etPhoneNum = (EditText) findViewById(R.id.et_add_member_phone);
        etPhoneNum.requestFocus();
        etPayCountMoney = (EditText) findViewById(R.id.et_add_member_money);
        etInitMoney = (EditText) findViewById(R.id.et_add_member_init_money);
        etInitPoint = (EditText) findViewById(R.id.et_add_member_init_point);
        etRecommendCardNum = findViewById(R.id.et_add_member_recommend);
        etEmail = (EditText) findViewById(R.id.et_add_member_email);
        etID = (EditText) findViewById(R.id.et_add_member_id);
        etAddress = (EditText) findViewById(R.id.et_add_member_address);
        etRemark = (EditText) findViewById(R.id.et_add_member_remark);

        etOverdueDate = findViewById(R.id.et_add_member_overdue_date);
        ivOverdueDate = (ImageView) findViewById(R.id.iv_add_member_overdue_date);
        tvCommission = (TextView) findViewById(R.id.tv_add_member_commission);
        tvBirthday = (TextView) findViewById(R.id.tv_add_member_birthday);
        tvRecommend = (TextView) findViewById(R.id.tv_add_member_recommend);
        civHeadImg = (CircleImageView) findViewById(R.id.civ_add_member_head);
        etFaceNum = (EditText) findViewById(R.id.et_add_member_face_num);
        etInitPassword = (EditText) findViewById(R.id.et_add_member_password);
        tvVipLevel = findViewById(R.id.tv_vip_level);
        tvSex = findViewById(R.id.tv_sex);
        tvmemsign = findViewById(R.id.tv_add_member_sign);
        tvPayWay = findViewById(R.id.tv_pay_way);
        imgVipBirthday = findViewById(R.id.iv_vip_biryjday);
        reLabRecycle = findViewById(R.id.lv_add_costomfiled);
        tvSave = findViewById(R.id.tv_save);
        tvClear = findViewById(R.id.tv_clear);
//        ivphone = findViewById(R.id.iv_add_member_phone);
    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        intentHandler = new IntentHandler();
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {

                    //会员卡号同手机号
                    if ("201".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mCardContactPhone = true;
                            etCardNum.setInputType(InputType.TYPE_NULL);
                            etCardNum.setEnabled(false);
                            mIvScan.setEnabled(false);

                        } else {
                            mCardContactPhone = false;
                            mIvScan.setEnabled(true);
                            etCardNum.setEnabled(true);
                        }
                    }
                    //是否必填手机号
                    if ("211".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mIsfilltel = true;
                            istelview.setVisibility(View.VISIBLE);
                        } else if (mSwitchEntity.get(i).getSS_State() == 0) {
                            mIsfilltel = false;
                            istelview.setVisibility(View.GONE);
                        }
                    }

                    //卡面号码
                    if ("208".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            findViewById(R.id.rl_add_member_face_number).setVisibility(View.VISIBLE);
                            findViewById(R.id.view_line).setVisibility(View.VISIBLE);
                            isCardNum = true;
                        } else {
                            findViewById(R.id.rl_add_member_face_number).setVisibility(View.GONE);
                            findViewById(R.id.view_line).setVisibility(View.GONE);
                            isCardNum = false;
                        }
                    }
                    //初始密码
                    if ("202".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            findViewById(R.id.rl_add_member_init_password).setVisibility(View.VISIBLE);
                            findViewById(R.id.view_paw_line).setVisibility(View.VISIBLE);
                            etInitPassword.setText(mSwitchEntity.get(i).getSS_Value() + "");
                            mPassordSwitch = true;
                        } else {
                            findViewById(R.id.rl_add_member_init_password).setVisibility(View.GONE);
                            findViewById(R.id.view_paw_line).setVisibility(View.GONE);
                            mPassordSwitch = false;
                        }
                    }
                    if ("103".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            bank = true;
                        } else if (mSwitchEntity.get(i).getSS_State() == 0) {
                            bank = false;
                        }
                    }
                    if ("101".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            cash = true;
                        } else if (mSwitchEntity.get(i).getSS_State() == 0) {
                            cash = false;
                        }
                    }

                    //支付宝记账
                    if ("106".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            zfb = true;
                        } else if (mSwitchEntity.get(i).getSS_State() == 0) {
                            zfb = false;
                        }
                    }
                    //微信记账
                    if ("105".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            wx = true;
                        } else if (mSwitchEntity.get(i).getSS_State() == 0) {
                            wx = false;
                        }
                    }
                    //员工提成
                    if ("301".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            isStaff = true;
                        } else {
                            isStaff = false;
                        }
                    }

                }
            } else {
//                rlReommendStaff.setVisibility(View.GONE);
                mCardContactPhone = false;//会员卡号同手机号默认关闭
                findViewById(R.id.rl_add_member_init_password).setVisibility(View.GONE);
                mPassordSwitch = false;
                findViewById(R.id.rl_add_member_face_number).setVisibility(View.GONE);
                findViewById(R.id.view_paw_line).setVisibility(View.GONE);
                findViewById(R.id.view_line).setVisibility(View.GONE);
                Log.i(TAG, "未设置开关");
            }
        } else {
//            rlReommendStaff.setVisibility(View.GONE);
            mCardContactPhone = false;//会员卡号同手机号默认关闭
            findViewById(R.id.rl_add_member_init_password).setVisibility(View.GONE);
            mPassordSwitch = false;
            findViewById(R.id.rl_add_member_face_number).setVisibility(View.GONE);
            findViewById(R.id.view_paw_line).setVisibility(View.GONE);
            findViewById(R.id.view_line).setVisibility(View.GONE);
            Log.i(TAG, "未设置开关");
            cash = true;
        }
        mGradeNameList = new ArrayList<>();
        mPayWayList = new ArrayList<>();
        mLabelList = new ArrayList<>();
        mBuilder = new StringBuilder();
        if (cash) {
            mPayWayList.add("现金支付");
        }
        if (bank) {
            mPayWayList.add("银联支付");
        }
        if (wx) {
            mPayWayList.add("微信记账");
        }
        if (zfb) {
            mPayWayList.add("支付宝记账");
        }
        mStaffName = new StringBuilder("");
        mLabName = new StringBuilder("");
//        mStaffGid = new StringBuilder("");
        mCustomPopWindow = new CustomPopWindow(AddMemberActivity.this);
        mHandler = new Handler(new Handler.Callback() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    //查询会员等级完成
                    case 1:
                        break;
                    //初始金额，开卡金额
                    case 2:
                        etInitMoney.setText(mInitMoney);
                        etPayCountMoney.setText(mMoney + "");
                        etInitPoint.setText(mInitPoint);

//                        tvVipLevel.setText("默认等级");
                        if (mMoney > 0) {
                            if (mPayWayList.size() > 0) {
                                tvPayWay.setText(mPayWayList.get(0));
                                mPayTypeName = mPayWayList.get(0);
                                if (mPayTypeName.equals("现金支付")) {
                                    mPayTypeCode = "XJZF";
                                } else if (mPayTypeName.equals("银联支付")) {
                                    mPayTypeCode = "YLZF";
                                } else if (mPayTypeName.equals("微信记账")) {
                                    mPayTypeCode = "WXJZ";
                                } else if (mPayTypeName.equals("支付宝记账")) {
                                    mPayTypeCode = "ZFBJZ";
                                }
                            } else {
                                warnDialog("请先设置支付方式！");
                            }
                        }
                        break;
                    //推荐人
                    case 3:
                        tvRecommend.setText(mRecommendName);
                        break;
                    //会员标签
                    case 4:
                        if (mLabelList.size() > 0) {
                            loadLabel();
                        } else {
                            rlLabel.setVisibility(View.GONE);
                        }
                        break;
                    //隐藏会员标签
                    case 5:
                        break;
                    //显示选中的内容
                    case 6:
                        tvLabel.setText(mBuilder);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**
     * @param date      ,
     * @param mTextView ,
     *  选择日期
     */
    private void showDateDialog(List<Integer> date, final TextView mTextView) {
        com.example.liangmutian.mypicker.DatePickerDialog.Builder builder = new com.example.liangmutian.mypicker.DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new com.example.liangmutian.mypicker.DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                mTextView.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
            }

            @Override
            public void onCancel() {

            }
        })
                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);
        datesDialog = builder.create();
        datesDialog.show();
    }

    /**
     * @param mlist     ,
     * @param mTextView ,
     *                  支付方式选择
     */
    private void showPayWayDialog(List<String> mlist, final TextView mTextView) {
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
                        if (position == 0) {
                            mPayTypeCode = "XJZF";
                            mPayTypeName = "现金支付";
                        }
                        if (position == 1) {
                            mPayTypeCode = "YLZF";
                            mPayTypeName = "银联支付";
                        }
                        if (position == 2) {
                            mPayTypeCode = "WX_JZ";
                            mPayTypeName = "微信记账";
                        }
                        if (position == 3) {
                            mPayTypeCode = "ZFB_JZ";
                            mPayTypeName = "支付宝记账";
                        }
                    }
                    @Override
                    public void onCancel() {
                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * @param mlist
     * @param mTextView 选择会员等级
     */
    private void showVipLevelDialog(final List<String> mlist, final TextView mTextView) {
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
                        mPayWay = mlist.get(position);
                        mGradeGid = mMemberGrade.get(position).getGID();
                        mInitMoney = Decima2KeeplUtil.stringToDecimal(mMemberGrade.get(position).getVG_InitialAmount() + "");//会员初始金额
                        mMoney = mMemberGrade.get(position).getVG_CardAmount();//售卡金额
                        mInitPoint = (int) mMemberGrade.get(position).getVG_InitialIntegral() + "";//初始积分
                        if (mMemberGrade.get(position).getVG_IsTime() == 1 && mMemberGrade.get(position).getVG_IsTimeNum() != null) {
                            addTime(mMemberGrade.get(position).getVG_IsTimeUnit().toString(), Double.parseDouble(mMemberGrade.get(position).getVG_IsTimeNum() + ""));
                        etOverdueDate.setText(overTime);
                            ivOverdueDate.setEnabled(false);
                     
                        } else if (mMemberGrade.get(position).getVG_IsTime() == 1 && mMemberGrade.get(position).getVG_IsTimeNum() == null) {
                            etOverdueDate.setText("永久");
                            ivOverdueDate.setEnabled(false);
                      
                        } else {
                            etOverdueDate.setText("");
                            ivOverdueDate.setEnabled(true);
                        }
                        mHandler.sendEmptyMessage(2);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }


    private void addTime(String str, double num) {

        int No = (int) num;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = new Date();

        Calendar c = Calendar.getInstance();

        c.setTime(date);

        if (str.equals("天")) {
            c.add(Calendar.DATE, No);
        } else if (str.equals("月")) {
            c.add(Calendar.MONTH, No);
        } else if (str.equals("年")) {
            c.add(Calendar.YEAR, No);
        }

        overTime = sdf.format(c.getTime());
    }

    /**
     * @param mlist     // 0 ,男；1，女；2，保密
     * @param mTextView ,,,
     *                  选择会员性别
     */
    private void showVipSexDialog(List<String> mlist, final TextView mTextView) {
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
                        mSex = position;
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * 加载数据
     */
    private void loadData() {

        mMemberGrade = (List<ReportMessageBean.DataBean.VIPGradeListBean>) CacheData.restoreObject("grade");
        costomfields = (List<ReportMessageBean.DataBean.GetCustomFieldsVIPBean>) CacheData.restoreObject("costomfield");
        if (mMemberGrade == null) {
            getMemberGrade(1);//获取会员等级
        } else {
            if (mMemberGrade != null && mMemberGrade.size() > 0) {
                tvVipLevel.setText(mMemberGrade.get(0).getVG_Name());
                mGradeGid = mMemberGrade.get(0).getGID();
                mInitMoney = Decima2KeeplUtil.stringToDecimal(mMemberGrade.get(0).getVG_InitialAmount() + "");//会员初始金额
                mMoney = mMemberGrade.get(0).getVG_CardAmount();//售卡金额
                mInitPoint = (int) mMemberGrade.get(0).getVG_InitialIntegral() + "";//初始积分
                initpoint = (int) mMemberGrade.get(0).getVG_InitialIntegral() + "";//初始积分
                if (mMemberGrade.get(0).getVG_IsTime() == 1 && mMemberGrade.get(0).getVG_IsTimeNum() != null) {
                    addTime(mMemberGrade.get(0).getVG_IsTimeUnit().toString(), Double.parseDouble(mMemberGrade.get(0).getVG_IsTimeNum() + ""));
                    etOverdueDate.setText(overTime);
                    ivOverdueDate.setEnabled(false);
                } else if (mMemberGrade.get(0).getVG_IsTime() == 1 && mMemberGrade.get(0).getVG_IsTimeNum() == null) {
                    etOverdueDate.setText("永久");
                    ivOverdueDate.setEnabled(false);
                } else {
                    etOverdueDate.setText("");
                    ivOverdueDate.setEnabled(true);
                }
                mHandler.sendEmptyMessage(2);

                groupGradeName();//组装会员等级名称集合
                mHandler.sendEmptyMessage(1);
            }
        }

        if (costomfields == null) {
            getMemberGrade(2);//获取自定义属性
        } else {
            initAdapter();
        }


    }


    private void initAdapter() {
//        for (int i = 0; i < costomfields.size(); i++) {
//            if (costomfields.get(i).getCF_IsShowVIP() == 0) {
//                costomfields.remove(i);
//            }
//        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        reLabRecycle.setLayoutManager(linearLayoutManager);
        mLabAdapter = new addCostomfieldsAdapter(AddMemberActivity.this, costomfields, changeHandler, 0);
        mLabAdapter.setHasStableIds(true);
        mLabAdapter.setImageClickEvent(this);
        reLabRecycle.setAdapter(mLabAdapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (inType != null && inType.equals("1")) {
                finish();
            }else {
                Intent intent = new Intent(AddMemberActivity.this, MemberListActivity.class);
                startActivity(intent);
                finish();
            }
        }
        return true;
    }

    /**
     * 设置监听
     */
    private void setListener() {

        mCalendarSelector = new CalendarSelector(this, 0, this);

        imgVipBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCalendarSelector.show(tvBirthday);
                if (tvBirthday.getText().toString().isEmpty()) {
                    mCalendarSelector.setPosition(DateUtil.getDateForString(DateTimeUtil.getNowDate()),"0","0");
                }
            }
        });
        tvPayWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPayWayList.size() > 0) {
                    showPayWayDialog(mPayWayList, tvPayWay);
                } else {
                    warnDialog("请先设置支付方式！");
                }
            }
        });
        tvSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVipSexDialog(mSexList, tvSex);
            }
        });
        tvVipLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGradeNameList.size()>0){
                    showVipLevelDialog(mGradeNameList, tvVipLevel);
                }else {
                    warnDialog("请先添加会员等级！");
                }

            }
        });
        mCustomPopWindow.setOnItemClickListener(this);
        //为支付方式Spinner设置Adapter
        mPayWaySpinnerAdapter = new ArrayAdapter<String>(AddMemberActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, mPayWayList);
        //返回
        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inType != null && inType.equals("1")) {
                    finish();
                }else {
                    Intent intent = new Intent(AddMemberActivity.this, MemberListActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
        //保存
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getTextValue()) {
                    operate = 1;
                    addMemberPost();//添加会员
                }
            }
        });
        //继续添加
        tvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getTextValue()) {
                    operate = 2;
                    addMemberPost();//添加会员
                }

            }
        });
        //过期时间点击监听
        ivOverdueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showDatePickOverdueDlg();

                if (etOverdueDate.getText().toString().isEmpty()) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), etOverdueDate);
                } else {
                    showDateDialog(DateUtil.getDateForString(etOverdueDate.getText().toString()), etOverdueDate);
                }
            }
        });

     /*   //生日点击监听
        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickDlg();
            }
        });*/
        //选择开卡员工监听
        tvCommission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              
                if (isStaff) {
                    Intent intent = new Intent(AddMemberActivity.this, StaffCommissionActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("PAGE_FLAG", "ADD");
                    bundle.putSerializable("mStaffInfo", (Serializable) mStaffInfo);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 999);
               
                } else {
                    CustomToast.makeText(AddMemberActivity.this, "员工提成未开启，请确认后再尝试！", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //推荐人输入框监听
        etRecommendCardNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             
                Intent intent = new Intent(AddMemberActivity.this, CheckMemberInfoActivity.class);
                intent.putExtra("addmember","yes");
                startActivityForResult(intent, 777);
            }
        });

        //会员头像点击监听
        civHeadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isImageType = false;
                mCustomPopWindow.showAtLocation(AddMemberActivity.this.findViewById(R.id.activity_add_member),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
        //扫码
        mIvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(AddMemberActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });
        etPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (mCardContactPhone) {
                    if (!s.toString().isEmpty()) {
                        etCardNum.setText(s.toString());
                    } else {
                        etCardNum.setText("");
                    }
                }
            }
        });

//        ivphone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                check();
//
//            }
//        });

        //选择标签
        tvmemsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddMemberActivity.this, SignChooseActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("sign", (Serializable) mLabList);
                intent.putExtras(bundle);
                startActivityForResult(intent, 666);
            }
        });
        //金额过滤器
        MoneyInputFilter filter = new MoneyInputFilter(10, true, "最多只能输入10位金额");
        filter.setMessage("最多只能输入10位金额");
        etPayCountMoney.setFilters(new InputFilter[]{filter});
        etInitMoney.setFilters(new InputFilter[]{filter});

    }

//
//    /**
//     * 检查通讯录权限
//     */
//    private void check() {
//        //判断是否有权限
//        if(ContextCompat.checkSelfPermission(AddMemberActivity.this, Manifest.permission.READ_CONTACTS)
//                != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(AddMemberActivity.this,new String[]{Manifest.permission.READ_CONTACTS},201);
//        }else{
//            Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
//            if (intent.resolveActivity(getPackageManager()) != null)
//            {
//                startActivityForResult(intent, 23);
//            }else {
//                CustomToast.makeText(AddMemberActivity.this,"没有找到通讯录",Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


    /**
     * 清除所有已添加数据
     */
    private void clearAll() {
        if (mLabList != null) {
           
            for (int i = 0; i < mLabList.size(); i++) {
                mLabList.get(i).setChecked(false);
            }
            mLabAdapter.notifyDataSetChanged();
        }
        etPhoneNum.setText("");//手机号
        civHeadImg.setImageResource(R.mipmap.ysl_member_head_imgs);//头像
        etMemberName.setText("");//会员姓名
        etCardNum.setText("");//卡号
        etFaceNum.setText("");//卡面号码
        tvVipLevel.setText("默认等级");//会员等级
        loadData();
       
        if (!mPassordSwitch) {
            etInitPassword.setText("");//初始密码
        }

//        tvSex.setText("男");//性别
//        etPayCountMoney.setText("");//开卡费用
//        tvPayWay.setText("");//开卡费用支付方式
//        etInitMoney.setText("");//初始金额
//        etInitPoint.setText("");//初始积分
        etOverdueDate.setText("");//过期时间
        tvCommission.setText("");//开卡人员
        tvBirthday.setText("");//会员生日
        etRecommendCardNum.setText("");//推荐人卡号
        etEmail.setText("");//电子邮件
        etID.setText("");//身份证号码
        etAddress.setText("");//会员地址
        etRemark.setText("");//备注信息
        mStaffName = new StringBuilder("");
        mStaffListGid.clear();
      
        if (mStaffInfo != null) {
            mStaffInfo.clear();
        }
        mPayWay = "";

        for (int i = 0;i<costomfields.size();i++){
           costomfields.get(i).setM_ItemsValue("");
        }
        initAdapter();


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            etCardNum.setText(scanResult);
        }
        if (resultCode == 1002) {
            mStaffInfo = (List<ReportMessageBean.DataBean.EmplistBean>) data.getSerializableExtra("staff");
            if (mStaffInfo != null) {
                for (int i = 0; i < mStaffInfo.size(); i++) {
                    mStaffListGid.add(mStaffInfo.get(i).getGID());
                    if (i == mStaffInfo.size() - 1) {
                        mStaffName.append(mStaffInfo.get(i).getEM_Name());
                    } else {
                        mStaffName.append(mStaffInfo.get(i).getEM_Name() + "、");
                    }
                    Log.i(TAG, "onActivityResult: " + mStaffInfo.get(i).getEM_Name());
                }
                tvCommission.setText(mStaffName);
                mStaffName.delete(0, mStaffName.length());//清空数据
            }
        }

      
        if (resultCode == 666) {
            mLabList = (List<LabelBean>) data.getSerializableExtra("sign");

           
            if (mLabList != null) {
                for (int i = 0; i < mLabList.size(); i++) {
                  
                    if (mLabList.get(i).isChecked()) {
                        if (i == mLabList.size() - 1) {
                            mLabName.append(mLabList.get(i).getItemName());
                        } else {
                            mLabName.append(mLabList.get(i).getItemName() + "、");
                        }
                    }
                }
            }
            tvmemsign.setText(mLabName);
            mLabName.delete(0, mLabName.length());//清空数据

        }
        if (requestCode == 777 && resultCode == 2222) {
            mMemberInfo = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");
            if (mMemberInfo != null) {
                etRecommendCardNum.setText(mMemberInfo.getVCH_Card());
                mRecommendCardNum = etRecommendCardNum.getText().toString();
            }
        }
//        if (resultCode)
        switch (requestCode) {
            case 23://选择联系人后返回
                if (resultCode == Activity.RESULT_OK)
                {
//                    if (resultCode == RESULT_OK) {
//
//                        final CursorLoader cursorLoader = new CursorLoader(this, data.getData(), new String[] { "data1" }, null, null, null);
//                        cursorLoader.registerListener(1,new AddressCursorLoader());
//                        cursorLoader.startLoading();
//                    }

//                    //处理返回的data,获取选择的联系人信息
                    Uri uri=data.getData();
                    String[] contacts=getPhoneContacts(uri);
                    etMemberName.setText(contacts[0]);
                    if (contacts[1].contains(" ")){
                        contacts[1] = contacts[1].replace(" ","");
                    }
                    etPhoneNum.setText(contacts[1]);
                }
                break;

            case IMAGE_REQUEST_CODE://版本<7.0  图库后返回
                if (data != null) {
                    startPhotoZoom(data.getData());
//                    civHeadImg.setImageURI(data.getData());
                }
                break;
            case SELECT_PIC_KITKAT://版本>= 7.0
                if (data != null && GetImagePath.getPath(getApplicationContext(), data.getData()) != null) {
                    File imgUri = new File(GetImagePath.getPath(getApplicationContext(), data.getData()));
                    Uri dataUri = FileProvider.getUriForFile(AddMemberActivity.this, MyApplication.FILE_ADDRESS, imgUri);
                    startPhotoZoom(dataUri);
//                    civHeadImg.setImageURI(dataUri);
                }
                break;
            case CAMERA_REQUEST_CODE: //照相后返回
               
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri inputUri = FileProvider.getUriForFile(AddMemberActivity.this, MyApplication.FILE_ADDRESS, mCameraFile);//通过FileProvider创建一个content类型的Uri
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
                    Uri inputUri = FileProvider.getUriForFile(AddMemberActivity.this,
                            MyApplication.FILE_ADDRESS, mCropFile);//通过FileProvider创建一个content类型的Uri
                    mUri = inputUri;
                    postUploadMemberPhoto();
//                    civHeadImg.setImageURI(mUri);
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
            mMemberPhotoAddress = data.getStringExtra("result");
            mUri = imageUri;
            civHeadImg.setImageURI(imageUri);
        }
    }


    //从回调的uri中查询数据库拿到电话号码和姓名**

    @SuppressLint("SupportAnnotationUsage")
    @RequiresPermission
    @RequiresApi(api = Build.VERSION_CODES.M)
    public String[] getPhoneContacts(Uri uri){
        String[] contact=new String[2];
        //得到ContentResolver对象**
        ContentResolver cr = getContentResolver();
        //取得电话本中开始一项的光标**
        Cursor cursor=cr.query(uri,null,null,null,null);
        if(cursor!=null)
        {
            cursor.moveToFirst();
            //取得联系人姓名**
            int nameFieldColumnIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            contact[0]=cursor.getString(nameFieldColumnIndex);
            //取得电话号码**
            String ContactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));


//            int hasWriteContactsPermisson = checkSelfPermission(
//                    android.Manifest.permission.READ_CONTACTS);
//            if(hasWriteContactsPermisson != PackageManager.PERMISSION_GRANTED) {
//                requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, REQUEST_CODE_ASK_PERMISSIONS);
//
//                return null;
//            }

            Cursor phone = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + ContactId, null, null);
            if(phone != null){
                phone.moveToFirst();
                contact[1] = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phone.close();
            cursor.close();
        } else {
            return null;
        }
        return contact;
    }




    /**
     * 拍照
     */
    private void jump2Caramel() {
       
        if (hasCamera()) {
            if (CheckPromiss.checkPermission(AddMemberActivity.this)) {
                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
                    Uri uriForFile = FileProvider.getUriForFile(AddMemberActivity.this,
                            MyApplication.FILE_ADDRESS, mCameraFile);
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile));
                }
                startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
         
            } else {
                warnDialog("未获取到相机权限，请确认后尝试！");
            }

        
        } else {
            CustomToast.makeText(AddMemberActivity.this, "未获取到相机，请确认后再尝试！", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    public static boolean hasCamera() {
        return hasBackFacingCamera() || hasFrontFacingCamera();
    }

  
    /**
     * 检查设备是否有后置摄像头
     *
     * @return true, 有后置摄像头；false,后置摄像头
     */
    public static boolean hasBackFacingCamera() {
        final int CAMERA_FACING_BACK = 0;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }

   
    /**
     * 检查设备是否有前置摄像头
     *
     * @return true, 有前置摄像头；false,前置摄像头
     */
    public static boolean hasFrontFacingCamera() {
        final int CAMERA_FACING_BACK = 1;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }

    private static boolean checkCameraFacing(final int facing) {
//        if (getSdkVersion() < Build.VERSION_CODES.GINGERBREAD) {
//            return false;
//        }
        final int cameraCount = Camera.getNumberOfCameras();
        Camera.CameraInfo info = new Camera.CameraInfo();
        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, info);
            if (facing == info.facing) {
                return true;
            }
        }
        return false;
    }


    /**
     * 从相册中选择
     */
    private void jump2Album() {
      
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
            Uri uriForFile = FileProvider.getUriForFile(AddMemberActivity.this, MyApplication.FILE_ADDRESS, mGalleryFile);
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
    private void postUploadMemberPhoto() {
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
                mMemberPhotoAddress = memberPhotoBean.getData();
                if (mMemberPhotoAddress.contains("http")) {
                    Glide.with(AddMemberActivity.this).load(mMemberPhotoAddress).into(civHeadImg);
                } else {
                    String url = mMemberPhotoAddress;
                    if (url.contains("../")) {
                        String[] s = url.split("\\.\\./");
                        url = s[2];
                    }
                    Glide.with(AddMemberActivity.this).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).into(civHeadImg);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddMemberActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在上传头像...", false);
        HttpHelper.post(this, HttpAPI.API().UPLOAD_MEMBER_PHOTO, params, callBack);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

//        CacheData.deleteFile("grade");
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
        }
    }

    /**
     * 查询会员等级
     */
   
    private void getMemberGrade(final int num) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();
       
        client.post(HttpAPI.API().PRE_LOAD, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                 mMemberGrade = reportbean.getData().getVIPGradeList();
                CacheData.saveObject("grade", mMemberGrade);//缓存等级列表到本地

                costomfields = reportbean.getData().getGetCustomFieldsVIP();
                CacheData.saveObject("costomfield", costomfields);//缓存自定义属性到本地

                if (num ==1){
                    if(mMemberGrade!=null &&mMemberGrade.size()>0){
                        groupGradeName();//组装会员等级名称集合
                        mHandler.sendEmptyMessage(1);

                        tvVipLevel.setText(mMemberGrade.get(0).getVG_Name());
                        mGradeGid = mMemberGrade.get(0).getGID();
                        mInitMoney = Decima2KeeplUtil.stringToDecimal(mMemberGrade.get(0).getVG_InitialAmount() + "");//会员初始金额
                        mMoney = mMemberGrade.get(0).getVG_CardAmount();//售卡金额
                        mInitPoint = (int) mMemberGrade.get(0).getVG_InitialIntegral() + "";//初始积分
                        initpoint = (int) mMemberGrade.get(0).getVG_InitialIntegral() + "";//初始积分

                        if (mMemberGrade.get(0).getVG_IsTime() == 1 && mMemberGrade.get(0).getVG_IsTimeNum() != null) {
                            addTime(mMemberGrade.get(0).getVG_IsTimeUnit().toString(), Double.parseDouble(mMemberGrade.get(0).getVG_IsTimeNum() + ""));
                            etOverdueDate.setText(overTime);
                            ivOverdueDate.setEnabled(false);

                        } else if (mMemberGrade.get(0).getVG_IsTime() == 1 && mMemberGrade.get(0).getVG_IsTimeNum() == null) {
                            etOverdueDate.setText("永久");
                            ivOverdueDate.setEnabled(false);

                        } else {
                            etOverdueDate.setText("");
                            ivOverdueDate.setEnabled(true);
                        }
                        mHandler.sendEmptyMessage(2);
                    }

                } else if (num == 2) {
                    initAdapter();
                }

            }

            @Override
            public void onFailure(String msg) {
            }
        });

    }

    /**
     * 组装会员等级名称数据集合
     */
    private void groupGradeName() {
        mGradeNameList.clear();//清空
        if (mMemberGrade != null) {
            for (int i = 0; i < mMemberGrade.size(); i++) {
                mGradeNameList.add(mMemberGrade.get(i).getVG_Name());
            }
        }
    }
//
//    /**
//     * 查询会员姓名
//     *
//     * @param card 卡号
//     */
//    private void checkMemberName(String card) {
//        AsyncHttpClient client = new AsyncHttpClient();
//        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
//        client.setCookieStore(cookieStore);
//        RequestParams params = new RequestParams();
//        params.put("VCH_Card", card);
//        client.post(HttpAPI.API().QUERY_SINGLE_MEMBER, params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                if (statusCode == 200) {
//                    try {
//                        Gson gson = new Gson();
//                        String response = new String(responseBody, "utf-8");
//                        SuccessBean successBean = CommonFun.JsonToObj(response, SuccessBean.class);
//                        if (successBean.isSuccess()) {
//                            MemberInfoBean infoBean = CommonFun.JsonToObj(response, MemberInfoBean.class);
//                            mRecommendName = infoBean.getData().getVIP_Name();
//                            mHandler.sendEmptyMessage(3);
//                        }
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//            }
//        });
//    }

    /**
     * 添加会员请求
     */
    private void addMemberPost() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("VCH_Card", mCardNum);
        if (mPassordSwitch && !etInitPassword.getText().toString().isEmpty()) {
            params.put("VCH_Pwd", etInitPassword.getText().toString());
        }
        params.put("VG_GID", mGradeGid);
        params.put("VT_Code", "IntegerCard");
        params.put("VIP_Name", mMemberName);
        params.put("VIP_Sex", mSex);
        params.put("VIP_CellPhone", mPhoneNum);
        params.put("VIP_Email", mEmail);
        params.put("VIP_InterCalaryMonth", calaryMonth);
        params.put("VIP_Birthday", mBirthday);
        params.put("VIP_IsLunarCalendar", isLunar);
        params.put("VIP_ICCard", mId);
        params.put("VIP_IsForver", mIsForver);//永久会员
        params.put("VIP_FixedPhone", "");
        params.put("VIP_State", 1);
        params.put("SM_Name", "默认店铺");//店铺名称
        params.put("VIP_Referee", mRecommendCardNum);//推荐人卡号
        params.put("VIP_Label", getLab(mLabList));//标签
        params.put("VIP_FaceNumber", mcardId);//卡面号码
        params.put("VCH_Fee", mMoney);//开卡费用
        params.put("VCH_Fee_PayType", mPayTypeCode);
        params.put("VCH_Fee_PayTypeText", mPayTypeName);
        params.put("VIP_Addr", mAddress);
        params.put("VIP_Remark", mRemark);
        params.put("MA_AvailableIntegral", Integer.parseInt(mInitPoint));//初始积分
        params.put("MA_AggregateAmount", mInitMoney);//初始金额
        params.put("VIP_Overdue", mOverdueDate+ " 23:59:59");//过期日期
         for (int i = 0;i<costomfields.size();i++){//自定义属性
             params.put("FildsId["+i+"]", costomfields.get(i).getCF_GID());
             params.put("FildsValue["+i+"]", costomfields.get(i).getM_ItemsValue()==null?"":costomfields.get(i).getM_ItemsValue());
         }

//        for (int i = 0; i < mStaffListGid.size(); i++) {//提成员工GID
        if (mStaffListGid.size() > 0) {

            params.put("EM_GIDList[]", mStaffListGid.get(0));
        }
//        }
        if (mMemberPhotoAddress != null) {
            params.put("VIP_HeadImg", mMemberPhotoAddress);
        } else {
            params.put("VIP_HeadImg", HttpAPI.API().DEFALUT_HEAD_IMAGE);
        }
        params.put("IS_Sms", true);
        params.put("VIP_RegSource", 2);

        MyTextHttpResponseHandler responseHandler = new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                LoadingDialogUtil.closeDialog(mDialog);//关闭加载动画
//                toastDialog("添加会员成功！");
                final HYKK_Success_Bean hykk_success_bean = CommonFun.JsonToObj(responseString, HYKK_Success_Bean.class);
                mSweetAlertDialog = new SweetAlertDialog(AddMemberActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("提示");
                mSweetAlertDialog.setContentText("添加会员成功");
                mSweetAlertDialog.setCancelable(false);
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        new HttpGetPrintContents(AddMemberActivity.this, MyApplication.HYKK_PRINT_TIMES, hykk_success_bean.getData().getGID(),intentHandler).HYKK();
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                LoadingDialogUtil.closeDialog(mDialog);//关闭加载动画
                if (msg.contains("SmsSign")) {
                    toastDialog("添加会员成功,短信未发送，未设置默认签名！");
                } else if (msg.contains("BuySms")) {
                    toastDialog("添加会员成功，短信未发送，短信库存不足！");
                } else if (msg.contains("UpgradeShop")) {
                    warnDialog("会员数已达上限,请升级店铺！");
                } else {
                    warnDialog(msg);
                }
            }
        };
        responseHandler.setDialog(AddMemberActivity.this, "正在提交...", false);//设置加载动画
       
        client.post(HttpAPI.API().ADDUSER, params, responseHandler);
    }

    public class  IntentHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent intent;
            switch (msg.what){
                case 1:
                    if (inType != null && inType.equals("1") && operate == 1) {
                        finish();
                    } else if (operate == 1) {
                        intent = new Intent(AddMemberActivity.this, MemberListActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (operate == 2) {
                        clearAll();
                    }
                    break;

            }
        }
    }

    /**
     * 获取文本框中的值
     */
    private boolean getTextValue() {



        mPhoneNum = etPhoneNum.getText().toString();
       
        if (mIsfilltel) {
            if (!TextUtils.isEmpty(etPhoneNum.getText())) {

                if (!RegexUtil.isTelPhoneNumber(mPhoneNum)) {
                    mPhoneNum = "";
                    warnDialog("【手机号码】格式不正确");
                    return false;
                }
            } else {

                warnDialog("【手机号】不能为空");
//                if (mIsfilltel && mCardContactPhone){
//                    warnDialog("【手机号】不能为空");
//                    return false;
//                }else if (mCardContactPhone && !mIsfilltel){
//                    warnDialog("您开启了会员卡同手机号，请填写手机号");
//                    return false;
//                }
               
                mPhoneNum = "";
            }


     
        } else {
            if (mCardContactPhone && TextUtils.isEmpty(etPhoneNum.getText())) {
                warnDialog("您开启了会员卡同手机号，请填写手机号");
                    return false;
            }
        }

        if (!TextUtils.isEmpty(etMemberName.getText())) {
            mMemberName = etMemberName.getText().toString();
        } else {
            warnDialog("【姓名】不能为空");
            return false;
        }

        if (!TextUtils.isEmpty(etCardNum.getText())) {
            mCardNum = etCardNum.getText().toString();
        } else {
            warnDialog("【卡号】不能为空");
            return false;
        }

        if (isCardNum){
            if (!TextUtils.isEmpty(etFaceNum.getText())) {
                if (etFaceNum.getText().toString().length()>1){
                    mcardId = etFaceNum.getText().toString();
                }else {
                    warnDialog("【卡面号码】不能少于两位");
                    return false;
                }
            } else {
                warnDialog("【卡面号码】不能为空");
                return false;
            }
        }

        if (!TextUtils.isEmpty(etPayCountMoney.getText())) {
            mMoney = Double.parseDouble(etPayCountMoney.getText().toString());
       
        } else {
            mMoney = 0;
        }
        if (!TextUtils.isEmpty(etInitMoney.getText())) {
            mInitMoney = Decima2KeeplUtil.stringToDecimal(etInitMoney.getText().toString());
        }
        if (!TextUtils.isEmpty(etInitPoint.getText())) {
//            mInitPoint = Decima2KeeplUtil.stringToDecimal(etInitPoint.getText().toString());
            mInitPoint = etInitPoint.getText().toString();
        }
       
        if (!TextUtils.isEmpty(etOverdueDate.getText()) && !etOverdueDate.getText().toString().equals("永久")) {//过期时间存在，不为永久会员
            mOverdueDate = etOverdueDate.getText().toString();
            mIsForver = 0;
        } else {//过期时间不存在，则为永久会员
            mIsForver = 1;
        }
//        if (!TextUtils.isEmpty(tvBirthday.getText())) {
//
//
//            mBirthday = tvBirthday.getText().toString();
//        }
        if (!TextUtils.isEmpty(etEmail.getText())) {
            mEmail = etEmail.getText().toString();
        }
        if (!TextUtils.isEmpty(etID.getText())) {
            mId = etID.getText().toString();
            if (!RegexUtil.isLegalId(mId)) {
                warnDialog("【身份证号】格式不正确");
                mId = "";
                return false;
            }
        }
        if (!TextUtils.isEmpty(etAddress.getText())) {
            mAddress = etAddress.getText().toString();
        }
        if (!TextUtils.isEmpty(etRemark.getText())) {
            mRemark = etRemark.getText().toString();
        }
       
        if (!etPayCountMoney.getText().toString().isEmpty()) {
            if (Double.parseDouble(etPayCountMoney.getText().toString()) > 0 && tvPayWay.getText().toString().isEmpty()) {
                warnDialog("请选择支付方式！");
                return false;
            }
        }
        for (int i = 0;i<costomfields.size();i++){
            if (costomfields.get(i).getCF_Required().equals("是")&&(costomfields.get(i).getM_ItemsValue()==null||costomfields.get(i).getM_ItemsValue().equals(""))){
                warnDialog("请填写"+costomfields.get(i).getCF_FieldName()+"!");
                return false;
            }
        }
        return true;
    }

    /**
     * 警告弹窗
     *
     * @param msg
     */
    private void warnDialog(String msg) {
        mSweetAlertDialog = new SweetAlertDialog(AddMemberActivity.this, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCancelable(false);
        mSweetAlertDialog.setConfirmText("确定");
        mSweetAlertDialog.show();
    }

    /**
     * 提示弹出
     *
     * @param msg
     */
    private void toastDialog(String msg) {
        mSweetAlertDialog = new SweetAlertDialog(AddMemberActivity.this, SweetAlertDialog.SUCCESS_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCancelable(false);
        mSweetAlertDialog.setConfirmText("确定");
        mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                mSweetAlertDialog.dismiss();
             
                if (operate == 1) {
                    Intent intent = new Intent(AddMemberActivity.this, MemberListActivity.class);
                    startActivity(intent);
                    finish();
                } else if (operate == 2) {
                    clearAll();
                }
            }
        });
        mSweetAlertDialog.show();
    }

    /**
     * 弹出时间选择器，用于选择生日
     */
    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(AddMemberActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int momth = monthOfYear + 1;
                        AddMemberActivity.this.tvBirthday.setText(year + "-" + momth + "-" + dayOfMonth);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        DatePicker dp = datePickerDialog.getDatePicker();
        dp.setMaxDate(System.currentTimeMillis());//设置可选的最大日期
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.show();
    }

    /**
     * 弹出时间选择器，用于选择过期时间
     */
    protected void showDatePickOverdueDlg() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(AddMemberActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int momth = monthOfYear + 1;
                AddMemberActivity.this.etOverdueDate.setText(year + "-" + momth + "-" + dayOfMonth);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        DatePicker dp = datePickerDialog.getDatePicker();
        dp.setMinDate(System.currentTimeMillis());//设置可选的最小时间
        datePickerDialog.getDatePicker().setCalendarViewShown(false);
        datePickerDialog.getDatePicker().setSpinnersShown(true);
        datePickerDialog.show();
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
     * 设置标签
     */
    private void loadLabel() {
        mUpLabel = new ArrayList<>();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, list);
//        adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
//        spLabel.setAdapter(adapter);
        mInflater = LayoutInflater.from(getApplicationContext());
       
        mFlowLayout.setAdapter(new TagAdapter<String>(mLabelList) {
            @Override
            public View getView(FlowLayout flowLayout, int i, String s) {
                TextView textView = (TextView) mInflater.inflate(R.layout.item_tag, mFlowLayout, false);
                textView.setText(s);
                return textView;
            }

            @Override
            public boolean setSelected(int position, String s) {
                return s.equals("android");
            }
        });
        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> set) {
                if (mLabel != null) {
                    mLabel = null;
                }
                if (mBuilder != null) {
                    mBuilder.delete(0, mBuilder.length());
                }
                if (mUpLabel != null) {
                    mUpLabel.clear();
                }
                Iterator<Integer> it = set.iterator();
                while (it.hasNext()) {
                    int i = it.next();
                    //组装上传到服务器的数据
                    LabelBean labelBean = new LabelBean();
                    labelBean.setItemGID(mMemberLabel.getData().get(i).getML_GID());
                    labelBean.setItemColor(mMemberLabel.getData().get(i).getML_ColorValue());
                    labelBean.setItemName(mMemberLabel.getData().get(i).getML_Name());
                    mUpLabel.add(labelBean);
                    //组装显示数据
                    mBuilder.append(mMemberLabel.getData().get(i).getML_Name());
                    mBuilder.append("  ");
                }
                Gson gson = new Gson();
                mLabel = gson.toJson(mUpLabel);
                mHandler.sendEmptyMessage(6);
            }
        });
        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
                return true;
            }
        });

    }

    private String getLab(List<LabelBean> labList) {
        String labString = "";
        List<LabelBean> list = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < labList.size(); i++) {
            if (labList.get(i).isChecked()) {
                list.add(labList.get(i));
            }
        }
        labString = gson.toJson(list);
        return labString;
    }

    @Override
    public void transmitPeriod(HashMap<String, String> result) {
        tvBirthday.setText(result.get("year") + result.get("month") + result.get("day"));
        int year = Integer.parseInt(result.get("yearpos")) + 1901;
        String month;
        String day = (Integer.parseInt(result.get("daypos")) + 1) < 10 ? ("0" + (Integer.parseInt(result.get("daypos")) + 1)) : (Integer.parseInt(result.get("daypos")) + 1) + "";
        calaryMonth = result.get("CalaryMonth");
        isLunar = result.get("islunar");
        if (!calaryMonth.equals("0")) {
            if (Integer.parseInt(result.get("monthpos")) < Integer.parseInt(calaryMonth)) {
                month = (Integer.parseInt(result.get("monthpos")) + 1) < 10 ? ("0" + (Integer.parseInt(result.get("monthpos")) + 1)) : (Integer.parseInt(result.get("monthpos")) + 1) + "";
            } else {
                month = Integer.parseInt(result.get("monthpos")) < 10 ? ("0" + Integer.parseInt(result.get("monthpos"))) : Integer.parseInt(result.get("monthpos")) + "";
            }
        } else {
            month = (Integer.parseInt(result.get("monthpos")) + 1) < 10 ? ("0" + (Integer.parseInt(result.get("monthpos")) + 1)) : (Integer.parseInt(result.get("monthpos")) + 1) + "";
        }
        mBirthday = year + "-" + month + "-" + day;
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
                    costomfields = (List<ReportMessageBean.DataBean.GetCustomFieldsVIPBean>) msg.obj;

                    break;
                case 2:


                    break;
            }
        }
    }

//    @Override
//    public void labItemChecked(LabelBean labelBean, boolean isChecked, int position) {
//        if (isChecked) {
//            mLabList.get(position).setChecked(true);
//        } else {
//            mLabList.get(position).setChecked(false);
//        }
//    }

    @Override
    public void OnImageClickEvent(int position,boolean isEmpty) {
        isImageType = true;
        mCustomImageId = position;
        mCustomPopWindow.showAtLocation(AddMemberActivity.this.findViewById(R.id.activity_add_member), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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
                    costomfields.get(mCustomImageId).setM_ItemsValue(bean.getData());
                    mLabAdapter.notifyItemChanged(mCustomImageId);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddMemberActivity.this, msg, Toast.LENGTH_SHORT).show();
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
