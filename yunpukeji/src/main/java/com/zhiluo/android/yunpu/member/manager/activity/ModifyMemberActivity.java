package com.zhiluo.android.yunpu.member.manager.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
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
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.example.liangmutian.mypicker.calendarselecter.CalendarSelector;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.consume.activity.SignChooseActivity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.jsonbean.MemberPhotoBean;
import com.zhiluo.android.yunpu.member.manager.adapter.addCostomfieldsAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.LabelBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberLabel;
import com.zhiluo.android.yunpu.member.manager.bean.VarLabBean;
import com.zhiluo.android.yunpu.member.manager.bean.YSLMemberInfoBean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomPopWindow;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.Base64Encoder;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CheckPromiss;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.GetImagePath;
import com.zhiluo.android.yunpu.utils.RegexUtil;
import com.zhiluo.android.yunpu.utils.ShowDialog;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 修改会员
 * 作者：罗咏哲 on 2017/7/25 14:25.
 * 邮箱：137615198@qq.com
 */

public class ModifyMemberActivity extends BaseActivity implements CustomPopWindow.OnItemClickListener, CalendarSelector
        .ICalendarSelectorCallBack, addCostomfieldsAdapter.OnImageClickEvent {
    @Bind(R.id.tv_add_card_num)
    TextView tvAddCardNum;
    @Bind(R.id.et_add_card_num)
    EditText etAddCardNum;//卡面号码
    @Bind(R.id.tv_add_member_tel)
    TextView tvAddMemberTel;
    @Bind(R.id.et_add_member_tel)
    EditText etAddMemberTel;//固定电话
    @Bind(R.id.tv_num_start)
    TextView tvNumStart;
    @Bind(R.id.rl_add_card_num)
    RelativeLayout rlAddCardNum;
    //会员卡号，姓名，手机，邮箱，身份证，地址，备注。
    private EditText etCardNum, etMemberName, etPhoneNum, etEmail, etID, etAddress, etRemark;
    private TextView tvBirthday, etOverdueDate, istelview;
    ;//过期时间，提成员工，生日，推荐人,会员标签。
    private Spinner spMemberGrade;//会员等级
    private CircleImageView civHeadImg;//会员头像
    private RadioGroup rgSex;//性别RadioGroup
    private RelativeLayout rlLabel;//会员标签布局
    private ImageView ivOverdueDate;//会员过期时间

    private Handler mHandler;
    private List<ReportMessageBean.DataBean.VIPGradeListBean> mMemberGrade;//会员等级实体
    private List<String> mGradeNameList;//会员等级名称集合
    private ArrayAdapter<String> mGradeSpinerAdapter;//会员等级Adapter
    private SweetAlertDialog mSweetAlertDialog;//提示框

    //卡号，会员姓名，手机号码，会员等级名称，过期时间，
    // 推荐人姓名，生日，提成员工姓名，推荐人卡号，
    // 邮箱，身份证，地址，备注。
    private String mCardNum, mMemberName, mPhoneNum, mGradeName, mOverdueDate, mBirthday, calaryMonth, isLunar,
            mEmail, mId, mAddress, mRemark;
    private String mcardId, mTelNum;
    private String mGradeGid, mGid;//等级GID,会员Gid
    private int mSex;//性别 0 男，1 女，2 未知
    private int mIsForver;//是否是永久会员 1 永久 0 非永久

    StringBuilder mBuilder;//会员标签显示内容
    private YSLMemberInfoBean.DataBean.DataListBean mMemberInfo;//会员信息
    private AllMemberListBean.DataBean.DataListBean mMemberInfocom = new AllMemberListBean.DataBean.DataListBean();//推荐人会员信息
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;
    private boolean mIsfilltel = true, isCardNum;//是否必填手机号

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
    private boolean mDeletvip;//删除会员权限
    private boolean mEditvip;//编辑会员权限

    private List<String> mSexList;
    private List<String> mPayWayList;
    private List<LabelBean> mLabList;
    private String mPayWay;
    private TextView tvVipLevel;
    private Dialog chooseDialog;
    private Dialog datesDialog;
    private TextView tvVipSex;
    private ImageView imgVipBirthday;
    private Bundle mBundle;
    private String overTime;
    private boolean mCardContactPhone;//会员卡号同手机号
    private String mRecommendCardNum = "";
    private TextView etRecommendCardNum;
    private TextView tvmemsign;
    private StringBuilder mLabName;

    private RecyclerView reLabRecycle;
    private addCostomfieldsAdapter mLabAdapter;
    private List<ReportMessageBean.DataBean.GetCustomFieldsVIPBean> costomfields;//会员自定义属性
    private ChangeHandler changeHandler;
    private CalendarSelector mCalendarSelector;
    private String shopName;

    //会员自定义属性图片类型
    private boolean isImageType;
    //会员自定义属性position
    private int mCustomImageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_member);
        ButterKnife.bind(this);
        initView();
        initDatas();
        initVariable();
        initSwitch();
        loadData();
        setListener();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        changeHandler = new ChangeHandler();
        etCardNum = (EditText) findViewById(R.id.et_add_member_num);
        etCardNum.setInputType(InputType.TYPE_NULL);
        etCardNum.setEnabled(false);
        etCardNum.setBackgroundColor(getResources().getColor(R.color.lightgray));
        etMemberName = (EditText) findViewById(R.id.et_add_member_name);
        etPhoneNum = (EditText) findViewById(R.id.et_add_member_phone);
        etPhoneNum.requestFocus();
        etEmail = (EditText) findViewById(R.id.et_add_member_email);
        etID = (EditText) findViewById(R.id.et_add_member_id);
        etAddress = (EditText) findViewById(R.id.et_add_member_address);
        etRemark = (EditText) findViewById(R.id.et_add_member_remark);

        etOverdueDate = findViewById(R.id.et_modify_member_overdue_date);
        ivOverdueDate = (ImageView) findViewById(R.id.iv_modify_member_overdue_date);
        tvBirthday = (TextView) findViewById(R.id.tv_add_member_birthday);
        spMemberGrade = (Spinner) findViewById(R.id.sp_add_member_grade);
        civHeadImg = (CircleImageView) findViewById(R.id.civ_add_member_head);
        tvVipLevel = findViewById(R.id.tv_vip_level);
        tvVipSex = findViewById(R.id.tv_vip_sex);
        imgVipBirthday = findViewById(R.id.img_vip_birthday);
        istelview = findViewById(R.id.istel_view);
        etRecommendCardNum = findViewById(R.id.et_add_member_recommend);
        tvmemsign = findViewById(R.id.tv_add_member_sign);
        reLabRecycle = findViewById(R.id.lv_add_costomfiled);
    }

    private void initDatas() {
        mBundle = new Bundle();
        mSexList = new ArrayList<>();
        mPayWayList = new ArrayList<>();
        mLabList = new ArrayList<>();
        mLabName = new StringBuilder("");
        mSexList.add("男");
        mSexList.add("女");
        mSexList.add("保密");
        mPayWayList.add("现金支付");
        mPayWayList.add("银联支付");
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//        reLabRecycle.setLayoutManager(gridLayoutManager);
//        mLabAdapter = new AddVipLabAdapter(ModifyMemberActivity.this, mLabList, this);
//        reLabRecycle.setAdapter(mLabAdapter);
    }


    private void initSwitch() {
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {

                    //是否必填手机号
                    if ("211".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mIsfilltel = true;
                            istelview.setVisibility(View.VISIBLE);
                        } else {
                            mIsfilltel = false;
                            istelview.setVisibility(View.GONE);
                        }
                    }

                    //会员卡号同手机号
                    if ("201".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mCardContactPhone = true;
                            etCardNum.setInputType(InputType.TYPE_NULL);
                        } else {
                            mCardContactPhone = false;
                        }
                    }
                    //卡面号码
                    if ("208".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            rlAddCardNum.setVisibility(View.VISIBLE);
                            tvNumStart.setVisibility(View.VISIBLE);
                            isCardNum = true;
                        } else {
                            rlAddCardNum.setVisibility(View.GONE);
                            tvNumStart.setVisibility(View.GONE);
                            isCardNum = false;
                        }
                    }
                }
            }
        }

    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        Intent intent = getIntent();
        if (intent != null) {
            mBundle = intent.getBundleExtra("bundle");
            mMemberInfo = (YSLMemberInfoBean.DataBean.DataListBean) mBundle.getSerializable("vipdetail");
            mDeletvip = intent.getBooleanExtra("deletvip", false);
            mEditvip = intent.getBooleanExtra("editvip", false);
            if (mMemberInfo.getVIP_HeadImg() != null && !"".equals(mMemberInfo.getVIP_HeadImg())) {
                String uri;
                if (mMemberInfo.getVIP_HeadImg().contains("http")) {
                    uri = mMemberInfo.getVIP_HeadImg();
                } else {
                    uri = HttpAPI.API().MAIN_DOMAIN + mMemberInfo.getVIP_HeadImg();
                }
                mMemberPhotoAddress = mMemberInfo.getVIP_HeadImg();
                Glide.with(ModifyMemberActivity.this).load(Uri.parse(uri)).into(civHeadImg);
            }
            mGid = mMemberInfo.getGID();
            mCardNum = mMemberInfo.getVCH_Card();
            etCardNum.setText(mCardNum);
            mMemberName = mMemberInfo.getVIP_Name();
            etMemberName.setText(mMemberName);
            mPhoneNum = mMemberInfo.getVIP_CellPhone();
            etPhoneNum.setText(mPhoneNum);
            etPhoneNum.setSelection(etPhoneNum.getText().toString().length());
            mGradeName = mMemberInfo.getVG_Name();
            mGradeGid = mMemberInfo.getVG_GID();
            mSex = mMemberInfo.getVIP_Sex();
            mcardId = mMemberInfo.getVIP_FaceNumber();
            etAddCardNum.setText(mcardId);
            mTelNum = mMemberInfo.getVIP_FixedPhone();
            etAddMemberTel.setText(mTelNum);

            tvVipLevel.setText(mGradeName);
            if (mSex == 0) {
                tvVipSex.setText("男");
            } else if (mSex == 1) {
                tvVipSex.setText("女");
            } else {
                tvVipSex.setText("保密");
            }
            mIsForver = mMemberInfo.getVIP_IsForver();
            if (mIsForver == 1) {
                etOverdueDate.setText("永久有效");
                ivOverdueDate.setEnabled(true);
                etOverdueDate.setBackgroundColor(getResources().getColor(R.color.white));
            }
            if (mIsForver == 0) {
                if (mMemberInfo.getVIP_Overdue() == null) {
                    etOverdueDate.setText("");
                    ivOverdueDate.setEnabled(true);
                    etOverdueDate.setBackgroundColor(getResources().getColor(R.color.white));
                } else if (mMemberInfo.getVIP_Overdue().length() >= 10) {
                    etOverdueDate.setText(mMemberInfo.getVIP_Overdue().substring(0, 10));
                    ivOverdueDate.setEnabled(false);
                    etOverdueDate.setBackgroundColor(getResources().getColor(R.color.lightgray));
                } else {
                    etOverdueDate.setText(mMemberInfo.getVIP_Overdue());
                    ivOverdueDate.setEnabled(false);
                    etOverdueDate.setBackgroundColor(getResources().getColor(R.color.lightgray));
                }
            }
            if (mMemberInfo.getVIP_Birthday() != null && mMemberInfo.getVIP_Birthday().length() > 9) {
                mBirthday = mMemberInfo.getVIP_Birthday().substring(0, 10);
            } else {
                mBirthday = mMemberInfo.getVIP_Birthday();
            }
//            mBirthday = mMemberInfo.getVIP_Birthday().substring(0, 10);
            isLunar = mMemberInfo.getVIP_IsLunarCalendar() + "";
            calaryMonth = mMemberInfo.getVIP_InterCalaryMonth() + "";
            if (isLunar != null && isLunar.equals("1")) {
                List<Integer> mlist = DateUtil.getDateForString(mMemberInfo.getVIP_Birthday().substring(0, 10) + "");
                if (calaryMonth == null || calaryMonth.equals("0")) {
                    tvBirthday.setText(mlist.get(0) + "年" + DateUtil.monthNumToMonthName(mlist.get(1) + "") + DateUtil.dayToDayName(mlist.get(2) + ""));
                } else {
                    tvBirthday.setText(mlist.get(0) + "年闰" + DateUtil.monthNumToMonthName(mlist.get(1) + "") + DateUtil.dayToDayName(mlist.get(2) + ""));
                }
            } else {
                tvBirthday.setText(mBirthday);
            }
//            if (mBirthday != null && !"".equals(mBirthday)) {
//                if (mBirthday.length() >= 10) {
//                    tvBirthday.setText(mBirthday.substring(0, 10));
//                } else {
//                    tvBirthday.setText(mBirthday);
//                }
//            }
            mId = mMemberInfo.getVIP_ICCard();
            if (mId != null && !"".equals(mId)) {
                etID.setText(mId);
            }
            mAddress = mMemberInfo.getVIP_Addr();
            if (mAddress != null && !"".equals(mAddress)) {
                etAddress.setText(mAddress);
            }
            mRemark = mMemberInfo.getVIP_Remark();
            if (mRemark != null && !"".equals(mRemark)) {
                etRemark.setText(mRemark);
            }

            etRecommendCardNum.setText(mMemberInfo.getVIP_Referee());
            mRecommendCardNum = etRecommendCardNum.getText().toString();
            if (mMemberInfo.getVIP_Label() != null && !"".equals(mMemberInfo.getVIP_Label())
                    && !mMemberInfo.getVIP_Label().contains("|")) {
                Type type = new TypeToken<ArrayList<LabelBean>>() {
                }.getType();
                Gson gson = new Gson();
                ArrayList<LabelBean> beans = CommonFun.JsonToObj(mMemberInfo.getVIP_Label(), type);
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < beans.size(); i++) {
                    builder.append(beans.get(i).getItemName());
                    builder.append(" ");

                }
                // tvLabel.setVisibility(View.VISIBLE);
                //tvLabel.setText(builder.toString());
            }
//            if (mMemberInfo.getVIP_Label() != null && !"".equals(mMemberInfo.getVIP_Label())
//                    && mMemberInfo.getVIP_Label().contains("|")) {
//                tvLabel.setVisibility(View.VISIBLE);
//                String label = mMemberInfo.getVIP_Label();
//                StringBuilder builder = new StringBuilder();
//                String[] label2 = label.split(",");
//                String[] label3;
//                for (int i = 0; i < label2.length; i++) {
//                    if (!label2[i].contains("#") && !label2[i].contains("|")) {
//                        builder.append(label2[i]);
//                        builder.append(" ");
//                    }
//                    if (label2[i].contains("|")) {
//                        label3 = label2[i].split("\\|");
//                        for (int j = 0; j < label3.length; j++) {
//                            if (!label3[j].contains("#")) {
//                                builder.append(label3[j]);
//                                builder.append(" ");
//                            }
//                        }
//                    }
//                }
//                tvLabel.setText(builder);
//            }

            //标签
            if (mMemberInfo.getVIP_Label() != null && !"".equals(mMemberInfo.getVIP_Label())
                    && !mMemberInfo.getVIP_Label().contains("|")) {
                Gson gson1 = new Gson();
                VarLabBean varLabBean = gson1.fromJson("{'lab':" + mMemberInfo.getVIP_Label() + "}", VarLabBean.class);
                if (varLabBean != null) {
                    mLabList.clear();
                    for (int i = 0; i < varLabBean.getLab().size(); i++) {
                        LabelBean bean = new LabelBean();
                        bean.setChecked(true);
                        bean.setItemGID(varLabBean.getLab().get(i).getItemGID());
                        bean.setItemName(varLabBean.getLab().get(i).getItemName());
                        bean.setItemColor(varLabBean.getLab().get(i).getItemColor());
                        mLabList.add(bean);
                        if (i == varLabBean.getLab().size() - 1 || i == 0) {
                            mLabName.append(varLabBean.getLab().get(i).getItemName());
                        } else {
                            mLabName.append(varLabBean.getLab().get(i).getItemName() + "、");
                        }
                    }
                }
                tvmemsign.setText(mLabName);

            }

            mEmail = mMemberInfo.getVIP_Email();
            if (mEmail != null && !"".equals(mEmail)) {
                etEmail.setText(mEmail);
            }
        }

//        //标签
//        if (mMemberInfo.getVIP_Label() != null && !"".equals(mMemberInfo.getVIP_Label())
//                && !mMemberInfo.getVIP_Label().contains("|")) {
//            Gson gson1 = new Gson();
//            VarLabBean varLabBean = gson1.fromJson("{'lab':" + mMemberInfo.getVIP_Label() + "}", VarLabBean.class);
//            if (varLabBean != null) {
//                for (int i = 0; i < varLabBean.getLab().size(); i++) {
//
//                    if (i == mLabList.size() - 1 || i == 0) {
//                        mLabName.append(varLabBean.getLab().get(i).getItemName());
//                    } else {
//                        mLabName.append(varLabBean.getLab().get(i).getItemName() + "、");
//                    }
//                }
//            }
//            etAddMemberLab.setText(mLabName);
//
//        }

        mGradeNameList = new ArrayList<>();

        mBuilder = new StringBuilder();

        mCustomPopWindow = new CustomPopWindow(ModifyMemberActivity.this);
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    //查询会员等级完成
                    case 1:
                        mGradeSpinerAdapter = new ArrayAdapter<String>(ModifyMemberActivity.this,
                                R.layout.item_spinner, R.id.tv_item_spinner, mGradeNameList);
                        spMemberGrade.setAdapter(mGradeSpinerAdapter);
                        for (int i = 0; i < mGradeNameList.size(); i++) {
                            if (mGradeName.equals(mGradeNameList.get(i))) {
                                spMemberGrade.setSelection(i, true);
                            }
                        }
                        break;
                    //初始金额，开卡金额
                    case 2:
                        break;
                    //推荐人
                    case 3:
                        break;
                    //会员标签
                    case 4:
//                        if (mLabelList.size() > 0) {
//                            loadLabel();
//                        } else {
//                            rlLabel.setVisibility(View.GONE);
//                        }
                        break;
                    //隐藏会员标签
                    case 5:
                        break;
                    //显示选中的内容
                    case 6:
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    /**
     * 加载数据
     */
    private void loadData() {
        mMemberGrade = (List<ReportMessageBean.DataBean.VIPGradeListBean>) CacheData.restoreObject("grade");
        costomfields = (List<ReportMessageBean.DataBean.GetCustomFieldsVIPBean>) CacheData.restoreObject("costomfield");
        shopName = (String) uSharedPreferencesUtiles.get(this, "ShopName", "默认店铺");
        if (mMemberGrade == null) {
            getMemberGrade(1);//获取会员等级
        } else {
            groupGradeName();//组装会员等级名称集合
            mHandler.sendEmptyMessage(1);
        }


        if (costomfields == null) {
            getMemberGrade(2);//获取自定义属性
        } else {

            initadatpter();

        }
//        getMemberLabel();//获取会员标签
    }

    private void initadatpter() {
        for (int i = 0; i < costomfields.size(); i++) {
            if (mMemberInfo.getCustomeFieldList() != null && mMemberInfo.getCustomeFieldList().size() > 0) {
                for (int j = 0; j < mMemberInfo.getCustomeFieldList().size(); j++) {
                    if (costomfields.get(i).getCF_FieldName().equals(mMemberInfo.getCustomeFieldList().get(j).getCF_FieldName())) {
                        costomfields.get(i).setM_ItemsValue(mMemberInfo.getCustomeFieldList().get(j).getCF_Value());
                    }
                }
            }

//            if (costomfields.get(i).getCF_IsShowVIP() == 0) {
//                costomfields.remove(i);
//            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        reLabRecycle.setLayoutManager(linearLayoutManager);
        mLabAdapter = new addCostomfieldsAdapter(ModifyMemberActivity.this, costomfields, changeHandler, 1);
        mLabAdapter.setHasStableIds(true);
        mLabAdapter.setImageClickEvent(this);
        reLabRecycle.setAdapter(mLabAdapter);
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
                if (mBirthday == null || mBirthday.equals("")) {
                    mCalendarSelector.setPosition(DateUtil.getDateForString(DateTimeUtil.getNowDate()), "0", "0");
                } else {
                    mCalendarSelector.setPosition(DateUtil.getDateForString(mBirthday), isLunar, calaryMonth);
                }

//                if (tvBirthday.getText().toString().isEmpty()||etOverdueDate.getText().toString().equals("永久有效")){
//                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvBirthday);
//                }else {
//                    showDateDialog(DateUtil.getDateForString(tvBirthday.getText().toString()), tvBirthday);
//                }
            }
        });
        tvVipSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVipSexDialog(mSexList, tvVipSex);
            }
        });
        tvVipLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVipLevelDialog(mGradeNameList, tvVipLevel);
            }
        });
        mCustomPopWindow.setOnItemClickListener(this);
        //返回
        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //保存
        findViewById(R.id.tv_add_member_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getTextValue()) {
                    modifyMemberPost();//修改会员
                }
            }
        });
        //会员等级Spinner监听
        spMemberGrade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mGradeGid = mMemberGrade.get(i).getGID();
                mHandler.sendEmptyMessage(2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //过期时间点击监听
        ivOverdueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // showDatePickOverdueDlg();

                if (etOverdueDate.getText().toString().isEmpty() || etOverdueDate.getText().toString().equals("永久有效")) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), etOverdueDate);
                } else {
                    showDateDialog(DateUtil.getDateForString(etOverdueDate.getText().toString()), etOverdueDate);
                }
            }
        });

        //会员头像点击监听
        civHeadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isImageType = false;
                mCustomPopWindow.showAtLocation(ModifyMemberActivity.this.findViewById(R.id.activity_add_member),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });

        //推荐人输入框监听
        etRecommendCardNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyMemberActivity.this, CheckMemberInfoActivity.class);
                intent.putExtra("addmember", "yes");
                startActivityForResult(intent, 777);
            }
        });

        //选择标签
        tvmemsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ModifyMemberActivity.this, SignChooseActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("sign", (Serializable) mLabList);
                intent.putExtras(bundle);
                startActivityForResult(intent, 666);
            }
        });
    }

    /**
     * @param date      ,
     * @param mTextView ,
     *                  选择日期
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

                        if (mMemberGrade.get(position).getVG_IsTime() == 1 && mMemberGrade.get(position).getVG_IsTimeNum() != null) {
                            addTime(mMemberGrade.get(position).getVG_IsTimeUnit().toString(), Double.parseDouble(mMemberGrade.get(position).getVG_IsTimeNum() + ""));
                            etOverdueDate.setText(overTime);
                            ivOverdueDate.setEnabled(false);
                            etOverdueDate.setBackgroundColor(getResources().getColor(R.color.lightgray));
                        } else if (mMemberGrade.get(position).getVG_IsTime() == 1 && mMemberGrade.get(position).getVG_IsTimeNum() == null) {
                            etOverdueDate.setText("永久有效");
                            ivOverdueDate.setEnabled(true);
                            etOverdueDate.setBackgroundColor(getResources().getColor(R.color.white));
                        } else {
                            etOverdueDate.setText("");
                            ivOverdueDate.setEnabled(true);
                            etOverdueDate.setBackgroundColor(getResources().getColor(R.color.white));
                        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE_REQUEST_CODE://版本<7.0  图库后返回
                if (data != null) {
                    startPhotoZoom(data.getData());
//                    civHeadImg.setImageURI(data.getData());
                }
                break;
            case SELECT_PIC_KITKAT://版本>= 7.0
                if (data != null) {
                    if (GetImagePath.getPath(getApplicationContext(), data.getData()) != null) {
                        File imgUri = new File(GetImagePath.getPath(getApplicationContext(), data.getData()));
                        Uri dataUri = FileProvider.getUriForFile(ModifyMemberActivity.this, MyApplication.FILE_ADDRESS, imgUri);
                        startPhotoZoom(dataUri);
//                        civHeadImg.setImageURI(dataUri);
                    }
                }
                break;
            case CAMERA_REQUEST_CODE: //照相后返回
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri inputUri = FileProvider.getUriForFile(ModifyMemberActivity.this, MyApplication.FILE_ADDRESS, mCameraFile);//通过FileProvider创建一个content类型的Uri
                        startPhotoZoom(inputUri);//设置输入类型
                    } else {
                        Uri inputUri = Uri.fromFile(mCameraFile);
                        startPhotoZoom(inputUri);
                    }
                }
                break;
            case RESULT_REQUEST_CODE://剪裁结果用ImageView显示
                if (resultCode == RESULT_OK) {
                    Uri inputUri = FileProvider.getUriForFile(ModifyMemberActivity.this,
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
            Log.i(TAG, "onActivityResult: " + mMemberPhotoAddress);
            civHeadImg.setImageURI(imageUri);
        }

        if (requestCode == 777 && resultCode == 2222) {
            mMemberInfocom = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");
            if (mMemberInfocom != null) {
                etRecommendCardNum.setText(mMemberInfocom.getVCH_Card());
                mRecommendCardNum = etRecommendCardNum.getText().toString();
            }
        }
        if (resultCode == 666) {
            mLabList = (List<LabelBean>) data.getSerializableExtra("sign");
            mLabName = new StringBuilder("");
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
    }

    /**
     * 拍照
     */
    private void jump2Caramel() {
        if (CheckPromiss.hasCamera()) {
            if (CheckPromiss.checkPermission(ModifyMemberActivity.this)) {
                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
                    Uri uriForFile = FileProvider.getUriForFile(ModifyMemberActivity.this,
                            MyApplication.FILE_ADDRESS, mCameraFile);
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile));
                }
                startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
            } else {
                ShowDialog.warnDialog(ModifyMemberActivity.this, "未获取到相机权限，请确认后尝试！");
            }

        } else {
            CustomToast.makeText(ModifyMemberActivity.this, "未获取到相机，请确认后再尝试！", Toast.LENGTH_SHORT).show();
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
            Uri uriForFile = FileProvider.getUriForFile(ModifyMemberActivity.this, MyApplication.FILE_ADDRESS, mGalleryFile);
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
                    Glide.with(ModifyMemberActivity.this).load(mMemberPhotoAddress).into(civHeadImg);
                } else {
                    String url = mMemberPhotoAddress;
                    if (url.contains("../")) {
                        String[] s = url.split("\\.\\./");
                        url = s[2];
                    }
                    Glide.with(ModifyMemberActivity.this).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).into(civHeadImg);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(ModifyMemberActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在上传头像...", false);
        HttpHelper.post(this, HttpAPI.API().UPLOAD_MEMBER_PHOTO, params, callBack);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
        }
    }

    /**
     * 查询会员等级
     */
    private void getMemberGrade(final int num) {

        RequestParams params = new RequestParams();
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {

                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                mMemberGrade = reportbean.getData().getVIPGradeList();
                CacheData.saveObject("grade", mMemberGrade);//缓存等级列表到本地

                costomfields = reportbean.getData().getGetCustomFieldsVIP();
                CacheData.saveObject("costomfield", costomfields);//缓存自定义属性到本地
                if (num == 1) {
                    groupGradeName();//组装会员等级名称集合
                    mHandler.sendEmptyMessage(1);
                } else if (num == 2) {
                    initadatpter();
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        };
        callBack.setLoadingAnimation(this, "加载数据...", false);
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, params, callBack);
    }

    /**
     * 组装会员等级名称数据集合
     */
    private void groupGradeName() {
        mGradeNameList.clear();//清空
        if (mMemberGrade != null) {
            for (int i = 0; i < mMemberGrade.size(); i++) {
                mGradeNameList.add(mMemberGrade.get(i).getVG_Name());
                if (mMemberGrade.get(i).getGID().equals(mGradeGid)) {
                    if (mMemberGrade.get(i).getVG_IsTime() == 1 && mMemberGrade.get(i).getVG_IsTimeNum() != null) {
                        ivOverdueDate.setEnabled(false);
                        etOverdueDate.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    } else if (mMemberGrade.get(i).getVG_IsTime() == 1 && mMemberGrade.get(i).getVG_IsTimeNum() == null) {
                        ivOverdueDate.setEnabled(true);
                        etOverdueDate.setBackgroundColor(getResources().getColor(R.color.white));
                    } else {
                        ivOverdueDate.setEnabled(true);
                        etOverdueDate.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                }
            }
//            String overdue = mMemberInfo.getVIP_Overdue();
//            if (overdue!=null &&overdue.length()>8){
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                try {
//                    long overtime =  sdf.parse(overdue+"").getTime();
//                    long currentTime =  sdf.parse(sdf.format(System.currentTimeMillis())).getTime();
//                    long time = overtime- currentTime;
//                    if (time<0){
//                        ivOverdueDate.setEnabled(true);
//                        etOverdueDate.setBackgroundColor(getResources().getColor(R.color.white));
//                    }
//                } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            }
        }
    }


    /**
     * 添加会员请求
     */
    private void modifyMemberPost() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("GID", mGid);
        params.put("VCH_Card", mCardNum);
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
        params.put("VIP_State", 0);
        params.put("SM_Name", shopName);//店铺名称
        params.put("VIP_Label", getLab(mLabList));//标签
        params.put("VIP_Addr", mAddress);
        params.put("VIP_Remark", mRemark);
        params.put("VIP_Overdue", mOverdueDate);//过期日期
        params.put("VIP_FixedPhone", mTelNum);//固定电话
        params.put("VIP_FaceNumber", mcardId);//卡面号码
        params.put("VIP_Referee", mRecommendCardNum);//推荐人卡号
        for (int i = 0; i < costomfields.size(); i++) {//自定义属性
            params.put("FildsId[" + i + "]", costomfields.get(i).getCF_GID());
            params.put("FildsValue[" + i + "]", costomfields.get(i).getM_ItemsValue() + "");
        }

        if (mMemberPhotoAddress != null) {
            params.put("VIP_HeadImg", mMemberPhotoAddress);
        }
        params.put("MA_AvailableIntegral", "");
        params.put("MA_AggregateAmount", "");
        params.put("EM_Name", "");


        MyTextHttpResponseHandler responseHandler = new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(ModifyMemberActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("提示");
                mSweetAlertDialog.setContentText("修改会员成功");
                mSweetAlertDialog.setCancelable(false);
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ActivityManager.getInstance().exit();
                        Intent intent = new Intent(ModifyMemberActivity.this, MemberListActivity.class);
                        startActivity(intent);
//                        setResult(10,intent);
                        finish();
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                warnDialog(msg);
            }
        };
        responseHandler.setDialog(ModifyMemberActivity.this, "正在提交..", false);
        client.post(HttpAPI.API().EDIVIP, params, responseHandler);
    }

    /**
     * @param labList ，
     * @return 获取上传到服务器标签
     */
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


    /**
     * 获取文本框中的值
     */
    private boolean getTextValue() {
        if (!TextUtils.isEmpty(etCardNum.getText())) {
            mCardNum = etCardNum.getText().toString();
        } else {
            warnDialog("【卡号】不能为空");
            return false;
        }
        if (!TextUtils.isEmpty(etMemberName.getText())) {
            mMemberName = etMemberName.getText().toString();
        } else {
            warnDialog("【姓名】不能为空");
            return false;
        }

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
                mPhoneNum = "";
            }


        } else {
            if (mCardContactPhone && TextUtils.isEmpty(etPhoneNum.getText())) {
                warnDialog("您开启了会员卡同手机号，请填写手机号");
                return false;
            }
        }
//        if (!TextUtils.isEmpty(etPhoneNum.getText())) {
//            mPhoneNum = etPhoneNum.getText().toString();
//            if (!RegexUtil.isTelPhoneNumber(mPhoneNum)) {
//                mPhoneNum = "";
//                warnDialog("【手机号码】格式不正确");
//                return false;
//            }
//        } else {
//
//            if(mIsfilltel){
//                warnDialog("【手机】不能为空");
//                return false;
//            }
//        }

        if (!TextUtils.isEmpty(etOverdueDate.getText()) && !"永久有效".equals(etOverdueDate.getText().toString())) {//过期时间存在，不为永久会员
            mOverdueDate = etOverdueDate.getText().toString();
          /*  if (mOverdueDate.length() >= 10) {
                mOverdueDate = mOverdueDate.substring(0, 9);
            }*/
            mIsForver = 0;
        } else if (!TextUtils.isEmpty(etOverdueDate.getText()) && "永久有效".equals(etOverdueDate.getText().toString())) {
            mIsForver = 1;
        } else {//过期时间不存在，则为永久会员
            mIsForver = 1;
        }
//        if (!TextUtils.isEmpty(tvBirthday.getText())) {
//            mBirthday = tvBirthday.getText().toString();
//            if (mBirthday.length() >= 10) {
//                mBirthday = mBirthday.substring(0, 9);
//            }
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
        if (isCardNum) {
            if (!TextUtils.isEmpty(etAddCardNum.getText())) {
                if (etAddCardNum.getText().toString().length() > 1) {
                    mcardId = etAddCardNum.getText().toString();
                } else {
                    warnDialog("【卡面号码】不能少于两位");
                    return false;
                }
            } else {
                warnDialog("【卡面号码】不能为空");
                return false;
            }
        }

        if (!TextUtils.isEmpty(etAddMemberTel.getText())) {
            mTelNum = etAddMemberTel.getText().toString();
        }
        for (int i = 0; i < costomfields.size(); i++) {
            if (costomfields.get(i).getCF_Required().equals("是") && (costomfields.get(i).getM_ItemsValue() == null || costomfields.get(i).getM_ItemsValue().equals(""))) {
                warnDialog("请填写" + costomfields.get(i).getCF_FieldName() + "!");
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
        mSweetAlertDialog = new SweetAlertDialog(ModifyMemberActivity.this, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCancelable(false);
        mSweetAlertDialog.setConfirmText("确定");
        mSweetAlertDialog.show();
    }

    /**
     * 弹出时间选择器，用于选择生日
     */
    protected void showDatePickDlg() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(ModifyMemberActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int momth = monthOfYear + 1;
                ModifyMemberActivity.this.tvBirthday.setText(year + "-" + momth + "-" + dayOfMonth);
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
        @SuppressLint("WrongConstant") final DatePickerDialog datePickerDialog = new DatePickerDialog(ModifyMemberActivity.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int momth = monthOfYear + 1;
                ModifyMemberActivity.this.etOverdueDate.setText(year + "-" + momth + "-" + dayOfMonth);
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
//
//    /**
//     * 获取会员标签
//     */
//    private void getMemberLabel() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
//        client.setCookieStore(myCookieStore);
//        RequestParams params = new RequestParams();
//        params.put("ML_Name", "");
//        client.post(HttpAPI.API().MEMBER_LABEL, params, new MyTextHttpResponseHandler() {
//            @Override
//            public void onSuccess(String responseString, Gson gson) {
//                mMemberLabel = CommonFun.JsonToObj(responseString, MemberLabel.class);
//                if (mLabList != null) {
//                    mLabList.clear();
//                }
//                for (int i = 0; i < mMemberLabel.getData().size(); i++) {
//                    LabelBean labelBean = new LabelBean();
//                    labelBean.setItemName(mMemberLabel.getData().get(i).getML_Name());
//                    labelBean.setItemGID(mMemberLabel.getData().get(i).getML_GID());
//                    labelBean.setItemColor(mMemberLabel.getData().get(i).getML_ColorValue());
//                    mLabList.add(labelBean);
//                }
//                if (mMemberInfo.getVIP_Label() != null && !"".equals(mMemberInfo.getVIP_Label())
//                        && !mMemberInfo.getVIP_Label().contains("|")) {
//                    Gson gson1 = new Gson();
//                    VarLabBean varLabBean = gson1.fromJson("{'lab':" + mMemberInfo.getVIP_Label() + "}", VarLabBean.class);
//                    for (int j = 0; j < mLabList.size(); j++) {
//                        for (int k = 0; k < varLabBean.getLab().size(); k++) {
//                            if (mLabList.get(j).getItemName().equals(varLabBean.getLab().get(k).getItemName())) {
//                                mLabList.get(j).setChecked(true);
//                            }
//                        }
//                    }
//                    // tvLabel.setVisibility(View.VISIBLE);
//                    //tvLabel.setText(builder.toString());
//                }
//                mLabAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                //   mHandler.sendEmptyMessage(5);
//            }
//        });
//    }
//
//    /**
//     * 设置标签
//     */
//    private void loadLabel() {
//        mUpLabel = new ArrayList<>();
//        mInflater = LayoutInflater.from(getApplicationContext());
//        mFlowLayout.setAdapter(new TagAdapter<String>(mLabelList) {
//            @Override
//            public View getView(FlowLayout flowLayout, int i, String s) {
//                TextView textView = (TextView) mInflater.inflate(R.layout.item_tag, mFlowLayout, false);
//                textView.setText(s);
//                return textView;
//            }
//
//            @Override
//            public boolean setSelected(int position, String s) {
//                return s.equals("android");
//            }
//        });
//        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
//            @Override
//            public void onSelected(Set<Integer> set) {
//                if (mLabel != null) {
//                    mLabel = null;
//                }
//                if (mBuilder != null) {
//                    mBuilder.delete(0, mBuilder.length());
//                }
//                if (mUpLabel != null) {
//                    mUpLabel.clear();
//                }
//                Iterator<Integer> it = set.iterator();
//                while (it.hasNext()) {
//                    int i = it.next();
//                    //组装上传到服务器的数据
//                    LabelBean labelBean = new LabelBean();
//                    labelBean.setItemGID(mMemberLabel.getData().get(i).getML_GID());
//                    labelBean.setItemColor(mMemberLabel.getData().get(i).getML_ColorValue());
//                    labelBean.setItemName(mMemberLabel.getData().get(i).getML_Name());
//                    mUpLabel.add(labelBean);
//                    //组装显示数据
//                    mBuilder.append(mMemberLabel.getData().get(i).getML_Name());
//                    mBuilder.append("  ");
//                }
//                Gson gson = new Gson();
//                mLabel = gson.toJson(mUpLabel);
//                mHandler.sendEmptyMessage(6);
//            }
//        });
//        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int i, FlowLayout flowLayout) {
//                return true;
//            }
//        });
//
//    }

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
        mCustomPopWindow.showAtLocation(ModifyMemberActivity.this.findViewById(R.id.activity_add_member), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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
                CustomToast.makeText(ModifyMemberActivity.this, msg, Toast.LENGTH_SHORT).show();
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
