package com.zhiluo.android.yunpu.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.HttpGet;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.jsonbean.MemberPhotoBean;
import com.zhiluo.android.yunpu.mvp.model.SuccessEntry;
import com.zhiluo.android.yunpu.ui.adapter.ImageLoadAdapter;
import com.zhiluo.android.yunpu.ui.bean.BankBean;
import com.zhiluo.android.yunpu.ui.bean.CityBean;
import com.zhiluo.android.yunpu.ui.bean.CountryBean;
import com.zhiluo.android.yunpu.ui.bean.GetMerchantInfoBean;
import com.zhiluo.android.yunpu.ui.bean.LoadImageBean;
import com.zhiluo.android.yunpu.ui.bean.Mccwx1idBean;
import com.zhiluo.android.yunpu.ui.bean.Mccwx2idBean;
import com.zhiluo.android.yunpu.ui.view.CustomPopWindow;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.Base64Encoder;
import com.zhiluo.android.yunpu.utils.CheckPromiss;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;
import com.zhiluo.android.yunpu.utils.GetImagePath;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;

/**
 * Created by ZPH on 2019-08-26.
 */

public class OpenQRPayActivity extends BaseActivity implements CustomPopWindow.OnItemClickListener {


    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.rb_goods_1)
    RadioButton rbGoods1;
    @Bind(R.id.rb_goods_2)
    RadioButton rbGoods2;
    @Bind(R.id.rb_goods_3)
    RadioButton rbGoods3;
    @Bind(R.id.rg_shop_type)
    RadioGroup rgShopType;
    @Bind(R.id.et_shop_name)
    EditText etShopName;
    @Bind(R.id.et_license_num)
    EditText etLicenseNum;
    @Bind(R.id.tv_license_overtime)
    TextView tvLicenseOvertime;
    @Bind(R.id.iv_license_overtime)
    ImageView ivLicenseOvertime;
    @Bind(R.id.view_license_overtime)
    View viewLicenseOvertime;
    @Bind(R.id.et_shop_name_short)
    EditText etShopNameShort;
    @Bind(R.id.et_registe_name)
    EditText etRegisteName;
    @Bind(R.id.et_contacts_name)
    EditText etContactsName;
    @Bind(R.id.et_contacts_phone)
    EditText etContactsPhone;
    @Bind(R.id.et_contacts_email)
    EditText etContactsEmail;
    @Bind(R.id.tv_industry_type)
    TextView tvIndustryType;
    @Bind(R.id.tv_industry_type_two)
    TextView tvIndustryTypeTwo;
    @Bind(R.id.tv_industry_type_three)
    TextView tvIndustryTypeThree;
    @Bind(R.id.tv_area)
    TextView tvArea;
    @Bind(R.id.tv_city)
    TextView tvCity;
    @Bind(R.id.tv_country)
    TextView tvCountry;
    @Bind(R.id.et_contacts_address)
    EditText etContactsAddress;
    @Bind(R.id.tv_bank)
    TextView tvBank;
    @Bind(R.id.tv_bank_province)
    TextView tvBankProvince;
    @Bind(R.id.tv_bank_city)
    TextView tvBankCity;
    @Bind(R.id.tv_bank_branch)
    TextView tvBankBranch;
    @Bind(R.id.et_account_name)
    EditText etAccountName;
    @Bind(R.id.et_account_num)
    EditText etAccountNum;
    @Bind(R.id.tv_account_type)
    TextView tvAccountType;
    @Bind(R.id.tv_account_person)
    TextView tvAccountPerson;
    @Bind(R.id.et_card_num)
    EditText etCardNum;
    @Bind(R.id.tv_id_card_overtime)
    TextView tvIdCardOvertime;
    @Bind(R.id.iv_id_card_overtime)
    ImageView ivIdCardOvertime;
    @Bind(R.id.gridview)
    GridView gridview;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.ll_bottom)
    LinearLayout llBottom;
    @Bind(R.id.activity_open_qr)
    RelativeLayout activityOpenQr;
    @Bind(R.id.ll_license)
    LinearLayout llLicense;
    @Bind(R.id.ll_account)
    LinearLayout llAccount;
    @Bind(R.id.tv_status)
    TextView tvStatus;
    @Bind(R.id.tv_save_twice)
    TextView tvSaveTwice;
    @Bind(R.id.sl_body)
    ScrollView slBody;

    @Bind(R.id.ll_zhengjian)
    LinearLayout llZhengjian;
    @Bind(R.id.et_faren_name)
    EditText etFarenName;
    @Bind(R.id.et_faren_idcard)
    EditText etFarenIdcard;
    @Bind(R.id.ll_faren)
    LinearLayout llFaren;
    @Bind(R.id.view_license_num)
    View viewLicenseNum;
    @Bind(R.id.ll_account_person)
    LinearLayout llAccountPerson;
    @Bind(R.id.v_account_person)
    View vAccountPerson;
    private AsyncHttpClient mClient = new AsyncHttpClient();
    private Gson mGson = new Gson();
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
    private MemberPhotoBean memberPhotoBean;//上传会员头像响应结果
    private GetMerchantInfoBean merchantInfoBean;
    private Uri mUri;
//    private String mMemberPhotoAddress = "/img/nohead.png";//会员头像地址

    private SweetAlertDialog mSweetAlertDialog;
    private List<LoadImageBean> imgList = new ArrayList<>();//上传图片地址
    private int pos = 0;
    private ImageLoadAdapter mShopRightAdapter;
    private Dialog chooseDialog;
    private Dialog datesDialog;
    private int business_type = 3;
    private static ProgressDialog pBar;
    private String mDownloadAddr = "http://img.yunvip123.com/CmemberFile/Document/%E9%9D%9E%E6%B3%95%E4%BA%BA%E6%8E%88%E6%9D%83%E4%B9%A6%E6%A8%A1%E6%9D%BF.doc";


    private List<String> industryTypeOne = new ArrayList<>();
    private List<Integer> industryCodeOne = new ArrayList<>();
    private String mindustryTypeOne = "";
    private int mindustryCodeOne = -1;
    private List<String> industryTypeTwo = new ArrayList<>();
    private List<Integer> industryCodeTwo = new ArrayList<>();
    private String mindustryTypeTwo = "";
    private int mindustryCodeTwo = -1;
    private List<String> industryTypeThree = new ArrayList<>();
    private List<String> industryCodeThree = new ArrayList<>();
    private String mindustryTypeThree;
    private String mindustryCodeThree;
    private List<Mccwx1idBean> mccwx1idBean;
    private List<Mccwx2idBean> mccwx2idBeans;


    //所属区域
    private List<String> province = new ArrayList<>();
    private List<Integer> provinceCode = new ArrayList<>();
    private List<String> provinceid = new ArrayList<>();
    private String mprovince = "";
    private int mprovinceyCode = -1;
    private String mProviceId = "";
    private List<String> city = new ArrayList<>();
    private String mcity = "";
    private int mcityId = -1;
    private String mcityCode = "";

    private List<String> country = new ArrayList<>();
    private List<String> countryCode = new ArrayList<>();
    private String mcountry = "";
    private String mcountryCode;
    private List<CityBean> cityBeanList;
    private List<CountryBean> countryBeanList;

    private List<String> bank = new ArrayList<>();
    private List<String> bankCode = new ArrayList<>();
    private String mbank = "";
    private String mbankCode = "-1";

    private List<String> bankcity = new ArrayList<>();
    private List<Integer> bankcityCode = new ArrayList<>();


    private String mBankprovince = "";
    private int mBankprovinceyCode = -1;

    private String mBankcity = "";
    private int mBankcityId = -1;
//    private String mBankcityCode = "";

    private List<String> bankbranch = new ArrayList<>();
    private List<String> bankbranchCode = new ArrayList<>();
    private String mbankbranch = "";
    private String mbankbranchCode = "-1";

    private List<BankBean> bankBeanList;
    private boolean istongguo;

    private List<String> accountType = new ArrayList<>();
    private List<Integer> accountTypeCode = new ArrayList<>();
    private String maccountType = "";
    private int maccountTypeCode = 2;

    private List<String> accountforward = new ArrayList<>();
    private List<Integer> accountforwardCode = new ArrayList<>();
    private String maccountforward = "";
    private int maccountforwardCode = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saomashengqing);
        ButterKnife.bind(this);
        initData();
        initListener();
    }

    private void initData() {
//        tvIndustryType.setText("高收益类（餐饮 酒店 美容）");
        mindustryCodeOne = 1;
//        tvIndustryTypeTwo.setText("宾馆类");
        mindustryCodeTwo = 1;

        mCustomPopWindow = new CustomPopWindow(OpenQRPayActivity.this);
        mCustomPopWindow.setOnItemClickListener(this);

        mShopRightAdapter = new ImageLoadAdapter(this, imgList);
        gridview.setAdapter(mShopRightAdapter);
        loadImageBean();
        addList();
        GetMerchantInfo();


    }

    private void addList() {


        //行业类别
        industryTypeOne.add("请选择");
        industryTypeOne.add("高收益类（餐饮 酒店 美容）");
        industryTypeOne.add("一般类（零售业  服务业 ）");
        industryTypeOne.add("民生类 （加油 百货 停车）");
        industryTypeOne.add("批发类");
        industryTypeOne.add("房产汽车金融");
        industryTypeOne.add("公益（学校 医院 慈善）");

        industryCodeOne.add(-1);
        industryCodeOne.add(1);
        industryCodeOne.add(2);
        industryCodeOne.add(3);
        industryCodeOne.add(4);
        industryCodeOne.add(5);
        industryCodeOne.add(6);

        //所在省
        province.add("所在省");
        provinceCode.add(-1);
        provinceid.add("");
        province.add("北京市");
        provinceCode.add(1);
        provinceid.add("110");
        province.add("天津市");
        provinceCode.add(2);
        provinceid.add("120");
        province.add("河北省");
        provinceCode.add(3);
        provinceid.add("130");
        province.add("山西省");
        provinceCode.add(4);
        provinceid.add("140");
        province.add("内蒙古自治区");
        provinceCode.add(5);
        provinceid.add("150");
        province.add("辽宁省");
        provinceCode.add(6);
        provinceid.add("210");
        province.add("吉林省");
        provinceCode.add(7);
        provinceid.add("220");
        province.add("黑龙江省");
        provinceCode.add(8);
        provinceid.add("230");
        province.add("上海市");
        provinceCode.add(9);
        provinceid.add("310");
        province.add("江苏省");
        provinceCode.add(10);
        provinceid.add("320");
        province.add("浙江省");
        provinceCode.add(11);
        provinceid.add("330");
        province.add("安徽省");
        provinceCode.add(12);
        provinceid.add("340");
        province.add("福建省");
        provinceCode.add(13);
        provinceid.add("350");
        province.add("江西省");
        provinceCode.add(14);
        provinceid.add("360");
        province.add("山东省");
        provinceCode.add(15);
        provinceid.add("370");
        province.add("河南省");
        provinceCode.add(16);
        provinceid.add("410");
        province.add("湖北省");
        provinceCode.add(17);
        provinceid.add("420");
        province.add("湖南省");
        provinceCode.add(18);
        provinceid.add("430");
        province.add("广东省");
        provinceCode.add(19);
        provinceid.add("440");
        province.add("广西省");
        provinceCode.add(20);
        provinceid.add("450");
        province.add("海南省");
        provinceCode.add(21);
        provinceid.add("460");
        province.add("重庆市");
        provinceCode.add(22);
        provinceid.add("500");
        province.add("四川省");
        provinceCode.add(23);
        provinceid.add("510");
        province.add("贵州省");
        provinceCode.add(24);
        provinceid.add("520");
        province.add("云南省");
        provinceCode.add(25);
        provinceid.add("530");
        province.add("西藏自治区");
        provinceCode.add(26);
        provinceid.add("540");
        province.add("陕西省");
        provinceCode.add(27);
        provinceid.add("610");
        province.add("甘肃省");
        provinceCode.add(28);
        provinceid.add("620");
        province.add("青海省");
        provinceCode.add(29);
        provinceid.add("630");
        province.add("宁夏回族自治区");
        provinceCode.add(30);
        provinceid.add("640");
        province.add("新疆维吾尔自治区");
        provinceCode.add(31);
        provinceid.add("650");
        province.add("台湾省");
        provinceCode.add(32);
        provinceid.add("710");
        province.add("香港特别行政区");
        provinceCode.add(33);
        provinceid.add("810");
        province.add("澳门特别行政区");
        provinceCode.add(34);
        provinceid.add("820");


        //开户行

        bank.add("请选择开户银行");
        bankCode.add("-1");
        bank.add("中国工商银行");
        bankCode.add("63");
        bank.add("中国农业银行");
        bankCode.add("62");
        bank.add("中国银行");
        bankCode.add("61");
        bank.add("中国建设银行");
        bankCode.add("60");
        bank.add("国家开发银行");
        bankCode.add("69");
        bank.add("中国进出口银行");
        bankCode.add("64");
        bank.add("中国农业发展银行");
        bankCode.add("1");
        bank.add("交通银行");
        bankCode.add("67");
        bank.add("中信银行");
        bankCode.add("4");
        bank.add("中国光大银行");
        bankCode.add("70");
        bank.add("华夏银行");
        bankCode.add("68");
        bank.add("中国民生银行");
        bankCode.add("72");
        bank.add("广发银行");
        bankCode.add("71");
        bank.add("平安银行");
        bankCode.add("74");
        bank.add("招商银行");
        bankCode.add("73");
        bank.add("兴业银行");
        bankCode.add("5");
        bank.add("上海浦东发展银行");
        bankCode.add("59");
        bank.add("城市商业银行");
        bankCode.add("58");
        bank.add("农村商业银行");
        bankCode.add("56");
        bank.add("恒丰银行");
        bankCode.add("57");
        bank.add("浙商银行");
        bankCode.add("55");
        bank.add("农村合作银行");
        bankCode.add("12");
        bank.add("渤海银行");
        bankCode.add("54");
        bank.add("徽商银行");
        bankCode.add("11");
        bank.add("地方银行");
        bankCode.add("77");
        bank.add("重庆三峡银行");
        bankCode.add("13");
        bank.add("上海农商银行");
        bankCode.add("6");
        bank.add("城市信用社");
        bankCode.add("14");
        bank.add("农村信用合作联社");
        bankCode.add("9");
        bank.add("中国邮政储蓄银行");
        bankCode.add("8");


        accountType.add("对公结算");
        accountTypeCode.add(1);
        accountType.add("对私结算");
        accountTypeCode.add(2);

        accountforward.add("法人");
        accountforwardCode.add(1);
        accountforward.add("非法人");
        accountforwardCode.add(2);


    }

    private void loadImageBean() {
        imgList.clear();
        if (business_type == 1 && maccountTypeCode == 2 && maccountforwardCode == 1) {
            LoadImageBean bean1 = new LoadImageBean();
            bean1.setImageAddress("");
            bean1.setNamme("营业执照照片");
            bean1.setStar(false);
            imgList.add(bean1);
            LoadImageBean bean2 = new LoadImageBean();
            bean2.setImageAddress("");
            bean2.setNamme("银行卡正面照片");
            bean2.setStar(true);
            imgList.add(bean2);
            LoadImageBean bean3 = new LoadImageBean();
            bean3.setImageAddress("");
            bean3.setNamme("银行卡反面照片");
            bean3.setStar(true);
            imgList.add(bean3);
            LoadImageBean bean4 = new LoadImageBean();
            bean4.setImageAddress("");
            bean4.setNamme("商户门头照片");
            bean4.setStar(true);
            imgList.add(bean4);
            LoadImageBean bean5 = new LoadImageBean();
            bean5.setImageAddress("");
            bean5.setNamme("店内场景照");
            bean5.setStar(true);
            imgList.add(bean5);
            LoadImageBean bean6 = new LoadImageBean();
            bean6.setImageAddress("");
            bean6.setNamme("开户许可证照片");
            bean6.setStar(false);
            imgList.add(bean6);
            LoadImageBean bean7 = new LoadImageBean();
            bean7.setImageAddress("");
            bean7.setNamme("结算人身份证正面");
            bean7.setStar(true);
            imgList.add(bean7);
            LoadImageBean bean8 = new LoadImageBean();
            bean8.setImageAddress("");
            bean8.setNamme("结算人身份证反面");
            bean8.setStar(true);
            imgList.add(bean8);
        } else if (business_type == 1 && maccountTypeCode == 2 && maccountforwardCode == 2) {
            LoadImageBean bean1 = new LoadImageBean();
            bean1.setImageAddress("");
            bean1.setNamme("营业执照照片");
            bean1.setStar(false);
            imgList.add(bean1);
            LoadImageBean bean2 = new LoadImageBean();
            bean2.setImageAddress("");
            bean2.setNamme("法人身份证正面");
            bean2.setStar(true);
            imgList.add(bean2);
            LoadImageBean bean3 = new LoadImageBean();
            bean3.setImageAddress("");
            bean3.setNamme("法人身份证反面");
            bean3.setStar(true);
            imgList.add(bean3);
            LoadImageBean bean4 = new LoadImageBean();
            bean4.setImageAddress("");
            bean4.setNamme("银行卡正面照片");
            bean4.setStar(true);
            imgList.add(bean4);
            LoadImageBean bean5 = new LoadImageBean();
            bean5.setImageAddress("");
            bean5.setNamme("银行卡反面照片");
            bean5.setStar(true);
            imgList.add(bean5);
            LoadImageBean bean6 = new LoadImageBean();
            bean6.setImageAddress("");
            bean6.setNamme("商户门头照片");
            bean6.setStar(true);
            imgList.add(bean6);
            LoadImageBean bean7 = new LoadImageBean();
            bean7.setImageAddress("");
            bean7.setNamme("店内场景照");
            bean7.setStar(true);
            imgList.add(bean7);
            LoadImageBean bean8 = new LoadImageBean();
            bean8.setImageAddress("");
            bean8.setNamme("开户许可证照片");
            bean8.setStar(false);
            imgList.add(bean8);
            LoadImageBean bean9 = new LoadImageBean();
            bean9.setImageAddress("");
            bean9.setNamme("结算非法人授权书");
            bean9.setStar(true);
            imgList.add(bean9);
            LoadImageBean bean10 = new LoadImageBean();
            bean10.setImageAddress("");
            bean10.setNamme("结算人身份证正面");
            bean10.setStar(true);
            imgList.add(bean10);
            LoadImageBean bean11 = new LoadImageBean();
            bean11.setImageAddress("");
            bean11.setNamme("结算人身份证反面");
            bean11.setStar(true);
            imgList.add(bean11);
            if (merchantInfoBean == null) {
                LoadImageBean bean12 = new LoadImageBean();
                bean12.setImageAddress("");
                bean12.setNamme("下载非法人授权书模板");
                bean12.setStar(false);
                imgList.add(bean12);
            }
        } else if (business_type == 1 && maccountTypeCode == 1) {
            LoadImageBean bean1 = new LoadImageBean();
            bean1.setImageAddress("");
            bean1.setNamme("营业执照照片");
            bean1.setStar(false);
            imgList.add(bean1);
            LoadImageBean bean2 = new LoadImageBean();
            bean2.setImageAddress("");
            bean2.setNamme("法人身份证正面");
            bean2.setStar(true);
            imgList.add(bean2);
            LoadImageBean bean3 = new LoadImageBean();
            bean3.setImageAddress("");
            bean3.setNamme("法人身份证反面");
            bean3.setStar(true);
            imgList.add(bean3);
            LoadImageBean bean4 = new LoadImageBean();
            bean4.setImageAddress("");
            bean4.setNamme("商户门头照片");
            bean4.setStar(true);
            imgList.add(bean4);
            LoadImageBean bean5 = new LoadImageBean();
            bean5.setImageAddress("");
            bean5.setNamme("店内场景照");
            bean5.setStar(true);
            imgList.add(bean5);
            LoadImageBean bean6 = new LoadImageBean();
            bean6.setImageAddress("");
            bean6.setNamme("开户许可证照片");
            bean6.setStar(false);
            imgList.add(bean6);

        } else if (business_type == 2 && maccountTypeCode == 2 && maccountforwardCode == 1) {
            LoadImageBean bean1 = new LoadImageBean();
            bean1.setImageAddress("");
            bean1.setNamme("营业执照照片");
            bean1.setStar(false);
            imgList.add(bean1);
            LoadImageBean bean2 = new LoadImageBean();
            bean2.setImageAddress("");
            bean2.setNamme("银行卡正面照片");
            bean2.setStar(true);
            imgList.add(bean2);
            LoadImageBean bean3 = new LoadImageBean();
            bean3.setImageAddress("");
            bean3.setNamme("银行卡反面照片");
            bean3.setStar(true);
            imgList.add(bean3);
            LoadImageBean bean4 = new LoadImageBean();
            bean4.setImageAddress("");
            bean4.setNamme("商户门头照片");
            bean4.setStar(true);
            imgList.add(bean4);
            LoadImageBean bean5 = new LoadImageBean();
            bean5.setImageAddress("");
            bean5.setNamme("店内场景照");
            bean5.setStar(true);
            imgList.add(bean5);
            LoadImageBean bean6 = new LoadImageBean();
            bean6.setImageAddress("");
            bean6.setNamme("结算人身份证正面");
            bean6.setStar(true);
            imgList.add(bean6);
            LoadImageBean bean7 = new LoadImageBean();
            bean7.setImageAddress("");
            bean7.setNamme("结算人身份证反面");
            bean7.setStar(true);
            imgList.add(bean7);
        } else if (business_type == 2 && maccountTypeCode == 2 && maccountforwardCode == 2) {
            LoadImageBean bean1 = new LoadImageBean();
            bean1.setImageAddress("");
            bean1.setNamme("营业执照照片");
            bean1.setStar(false);
            imgList.add(bean1);
            LoadImageBean bean2 = new LoadImageBean();
            bean2.setImageAddress("");
            bean2.setNamme("法人身份证正面");
            bean2.setStar(true);
            imgList.add(bean2);
            LoadImageBean bean3 = new LoadImageBean();
            bean3.setImageAddress("");
            bean3.setNamme("法人身份证反面");
            bean3.setStar(true);
            imgList.add(bean3);
            LoadImageBean bean4 = new LoadImageBean();
            bean4.setImageAddress("");
            bean4.setNamme("银行卡正面照片");
            bean4.setStar(true);
            imgList.add(bean4);
            LoadImageBean bean5 = new LoadImageBean();
            bean5.setImageAddress("");
            bean5.setNamme("银行卡反面照片");
            bean5.setStar(true);
            imgList.add(bean5);
            LoadImageBean bean6 = new LoadImageBean();
            bean6.setImageAddress("");
            bean6.setNamme("商户门头照片");
            bean6.setStar(true);
            imgList.add(bean6);
            LoadImageBean bean7 = new LoadImageBean();
            bean7.setImageAddress("");
            bean7.setNamme("店内场景照");
            bean7.setStar(true);
            imgList.add(bean7);
            LoadImageBean bean9 = new LoadImageBean();
            bean9.setImageAddress("");
            bean9.setNamme("结算非法人授权书");
            bean9.setStar(true);
            imgList.add(bean9);
            LoadImageBean bean10 = new LoadImageBean();
            bean10.setImageAddress("");
            bean10.setNamme("结算人身份证正面");
            bean10.setStar(true);
            imgList.add(bean10);
            LoadImageBean bean11 = new LoadImageBean();
            bean11.setImageAddress("");
            bean11.setNamme("结算人身份证反面");
            bean11.setStar(true);
            imgList.add(bean11);
            if (merchantInfoBean == null) {
                LoadImageBean bean12 = new LoadImageBean();
                bean12.setImageAddress("");
                bean12.setNamme("下载非法人授权书模板");
                bean12.setStar(false);
                imgList.add(bean12);
            }
        }else if (business_type == 2 && maccountTypeCode == 1) {
            LoadImageBean bean1 = new LoadImageBean();
            bean1.setImageAddress("");
            bean1.setNamme("营业执照照片");
            bean1.setStar(false);
            imgList.add(bean1);
            LoadImageBean bean2 = new LoadImageBean();
            bean2.setImageAddress("");
            bean2.setNamme("法人身份证正面");
            bean2.setStar(true);
            imgList.add(bean2);
            LoadImageBean bean3 = new LoadImageBean();
            bean3.setImageAddress("");
            bean3.setNamme("法人身份证反面");
            bean3.setStar(true);
            imgList.add(bean3);
            LoadImageBean bean4 = new LoadImageBean();
            bean4.setImageAddress("");
            bean4.setNamme("商户门头照片");
            bean4.setStar(true);
            imgList.add(bean4);
            LoadImageBean bean5 = new LoadImageBean();
            bean5.setImageAddress("");
            bean5.setNamme("店内场景照");
            bean5.setStar(true);
            imgList.add(bean5);
            LoadImageBean bean6 = new LoadImageBean();
            bean6.setImageAddress("");
            bean6.setNamme("开户许可证照片");
            bean6.setStar(false);
            imgList.add(bean6);

        } else if (business_type == 3) {
            LoadImageBean bean1 = new LoadImageBean();
            bean1.setImageAddress("");
            bean1.setNamme("手持身份证照片");
            bean1.setStar(true);
            imgList.add(bean1);
            LoadImageBean bean2 = new LoadImageBean();
            bean2.setImageAddress("");
            bean2.setNamme("银行卡正面照片");
            bean2.setStar(true);
            imgList.add(bean2);
            LoadImageBean bean3 = new LoadImageBean();
            bean3.setImageAddress("");
            bean3.setNamme("银行卡反面照片");
            bean3.setStar(true);
            imgList.add(bean3);
            LoadImageBean bean4 = new LoadImageBean();
            bean4.setImageAddress("");
            bean4.setNamme("商户门头照片");
            bean4.setStar(true);
            imgList.add(bean4);
            LoadImageBean bean5 = new LoadImageBean();
            bean5.setImageAddress("");
            bean5.setNamme("店内场景照");
            bean5.setStar(true);
            imgList.add(bean5);
            LoadImageBean bean6 = new LoadImageBean();
            bean6.setImageAddress("");
            bean6.setNamme("结算人身份证正面");
            bean6.setStar(true);
            imgList.add(bean6);
            LoadImageBean bean7 = new LoadImageBean();
            bean7.setImageAddress("");
            bean7.setNamme("结算人身份证反面");
            bean7.setStar(true);
            imgList.add(bean7);
        }
        mShopRightAdapter.notifyDataSetChanged();
    }

    private void initListener() {

        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvSaveTwice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!istongguo) {
                    tvStatus.setVisibility(View.GONE);
                    tvSaveTwice.setVisibility(View.GONE);
                    slBody.setVisibility(View.VISIBLE);
                    llBottom.setVisibility(View.VISIBLE);
                } else {
                    openPay();
                }

            }
        });


        etShopName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                etRegisteName.setText(s.toString());


            }
        });

        rgShopType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == rbGoods1.getId()) {//个人
                    rgShopType.check(rbGoods1.getId());
                    business_type = 3;
                    llLicense.setVisibility(View.GONE);
                    llAccount.setVisibility(View.GONE);
                    loadImageBean();
                }
                if (i == rbGoods2.getId()) {//企业
                    rgShopType.check(rbGoods2.getId());
                    business_type = 1;
                    llLicense.setVisibility(View.VISIBLE);
                    llAccount.setVisibility(View.VISIBLE);
                    loadImageBean();
                }
                if (i == rbGoods3.getId()) {//个体工商
                    rgShopType.check(rbGoods3.getId());
                    business_type = 2;
                    llLicense.setVisibility(View.VISIBLE);
                    llAccount.setVisibility(View.VISIBLE);
                    loadImageBean();
                }

            }
        });

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify()) {
                    saveData();
                }

            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
                LoadImageBean bean = (LoadImageBean) parent.getItemAtPosition(position);
                if (bean.getNamme().equals("下载非法人授权书模板")) {
                    downFile(mDownloadAddr, OpenQRPayActivity.this);
                } else {
                    mCustomPopWindow.showAtLocation(OpenQRPayActivity.this.findViewById(R.id.activity_open_qr),
                            Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                }
            }
        });

        //一级行业类别选择
        tvIndustryType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseShopDialog(industryTypeOne, tvIndustryType, 1);
            }
        });
        //二级行业类别选择
        tvIndustryTypeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (industryTypeTwo.size() > 1) {
                    showChooseShopDialog(industryTypeTwo, tvIndustryTypeTwo, 2);
                } else {
                    CustomToast.makeText(OpenQRPayActivity.this, "请先选择行业类别", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //三级行业类别选择
        tvIndustryTypeThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (industryTypeThree.size() > 1) {
                    showChooseShopDialog(industryTypeThree, tvIndustryTypeThree, 3);
                } else {
                    CustomToast.makeText(OpenQRPayActivity.this, "请先选择二级行业类别", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //所在省选择
        tvArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseShopDialog(province, tvArea, 4);
            }
        });

        //所在市选择
        tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (city.size() > 1) {
                    showChooseShopDialog(city, tvCity, 5);
                } else {
                    CustomToast.makeText(OpenQRPayActivity.this, "请先选择所在省", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //所在区县选择
        tvCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (country.size() > 1) {
                    showChooseShopDialog(country, tvCountry, 6);
                } else {
                    CustomToast.makeText(OpenQRPayActivity.this, "请先选择所在市", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //开户银行
        tvBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseShopDialog(bank, tvBank, 7);
            }
        });
        //支行所在省
        tvBankProvince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseShopDialog(province, tvBankProvince, 8);
            }
        });

        //支行所在市
        tvBankCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bankcity.size() > 1) {
                    showChooseShopDialog(bankcity, tvBankCity, 9);
                } else {
                    CustomToast.makeText(OpenQRPayActivity.this, "请先选择支行所在省", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //选择支行
        tvBankBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bankbranch.size() > 1) {
                    showChooseShopDialog(bankbranch, tvBankBranch, 10);
                } else {
                    CustomToast.makeText(OpenQRPayActivity.this, "请先选择所在市", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //账户类型
        tvAccountType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseShopDialog(accountType, tvAccountType, 11);
            }
        });

        //入账对象
        tvAccountPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseShopDialog(accountforward, tvAccountPerson, 12);
            }
        });

        ivIdCardOvertime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvIdCardOvertime.getText().toString().isEmpty()) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvIdCardOvertime);
                } else {
                    showDateDialog(DateUtil.getDateForString(tvIdCardOvertime.getText().toString()), tvIdCardOvertime);
                }
            }
        });

        ivLicenseOvertime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvLicenseOvertime.getText().toString().isEmpty()) {
                    showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), tvLicenseOvertime);
                } else {
                    showDateDialog(DateUtil.getDateForString(tvLicenseOvertime.getText().toString()), tvLicenseOvertime);
                }
            }
        });


    }

    /**
     * @param date      ,
     * @param mTextView ,
     *                  选择日期
     */
    private void showDateDialog(List<Integer> date, final TextView mTextView) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
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
     */
    private void showChooseShopDialog(List<String> mlist, final TextView mTextView, final int type) {
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
                        if (itemValue.equals("请选择")) {
                            mTextView.setText("");
                        } else {
                            mTextView.setText(itemValue);
                        }
                        choose(position, type);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    private void choose(int position, int type) {
        if (type == 1) {
            if (position == 0) {
                mindustryCodeOne = -1;
            } else {
                mindustryCodeOne = position;
            }
            industryTypeTwo.clear();
            industryCodeTwo.clear();
            tvIndustryTypeTwo.setText("");
            mindustryCodeTwo = -1;
            industryTypeThree.clear();
            industryCodeThree.clear();
            tvIndustryTypeThree.setText("");
            mindustryTypeThree = null;
            mindustryCodeThree = null;
            getmccwx1(mindustryCodeOne);
        } else if (type == 2) {
            if (position == 0) {
                mindustryCodeTwo = -1;
            } else {
                mindustryCodeTwo = industryCodeTwo.get(position);
            }
            industryTypeThree.clear();
            industryCodeThree.clear();
            tvIndustryTypeThree.setText("");
            mindustryTypeThree = null;
            mindustryCodeThree = null;
            getmccwx2(mindustryCodeTwo);
        } else if (type == 3) {
            mindustryCodeThree = industryCodeThree.get(position);
            mindustryTypeThree = industryTypeThree.get(position);
        } else if (type == 4) {
            mprovince = province.get(position);
            mprovinceyCode = provinceCode.get(position);
            mProviceId = provinceid.get(position);

            city.clear();
            countryCode.clear();
            tvCity.setText("");
            mcityId = -1;
            mcityCode = "";
            country.clear();
            countryCode.clear();
            tvCountry.setText("");
            mcountry = null;
            mcountryCode = null;
            getcity(mprovinceyCode, 1);

        } else if (type == 5) {
            mcity = city.get(position);
            if (position == 0) {
                mcityId = -1;
                mcityCode = "";
            } else {
                mcityId = cityBeanList.get(position - 1).getId();
                mcityCode = cityBeanList.get(position - 1).getCode();
            }
            country.clear();
            countryCode.clear();
            tvCountry.setText("");
            mcountry = null;
            mcountryCode = null;
            getcounty(mcityId);

        } else if (type == 6) {

            mcountry = country.get(position);
            mcountryCode = countryCode.get(position);
        } else if (type == 7) {
            mbank = bank.get(position);
            mbankCode = bankCode.get(position);

            bankbranch.clear();
            bankbranchCode.clear();
            tvBankBranch.setText("");
            mbankbranch = null;
            mbankbranchCode = null;
            getbank(mbankCode, mBankcityId);

        } else if (type == 8) {
            mBankprovince = province.get(position);
            mBankprovinceyCode = provinceCode.get(position);

            bankcity.clear();
            bankcityCode.clear();
            tvBankCity.setText("");
            mBankcityId = -1;
//            mBankcityCode = "";
            getcity(mBankprovinceyCode, 2);

        } else if (type == 9) {
            mBankcity = bankcity.get(position);
            if (position == 0) {
                mBankcityId = -1;
//                mBankcityCode = "";
            } else {
                mBankcityId = cityBeanList.get(position - 1).getId();
//                mBankcityCode = cityBeanList.get(position-1).getCode();
            }


            bankbranch.clear();
            bankbranchCode.clear();
            tvBankBranch.setText("");
            mbankbranch = null;
            mbankbranchCode = null;
            getbank(mbankCode, mBankcityId);
        } else if (type == 10) {
            mbankbranch = bankbranch.get(position);
            mbankbranchCode = bankbranchCode.get(position);
        } else if (type == 11) {
            maccountTypeCode = accountTypeCode.get(position);
            if (maccountTypeCode == 1) {
                llZhengjian.setVisibility(View.GONE);
                llFaren.setVisibility(View.VISIBLE);
                llAccountPerson.setVisibility(View.GONE);
                vAccountPerson.setVisibility(View.GONE);
            } else if (maccountTypeCode == 2) {//对私
                llZhengjian.setVisibility(View.VISIBLE);
                llFaren.setVisibility(View.GONE);
                llAccountPerson.setVisibility(View.VISIBLE);
                vAccountPerson.setVisibility(View.VISIBLE);
            }
            loadImageBean();

        } else if (type == 12) {
            maccountforwardCode = accountforwardCode.get(position);
            if (maccountforwardCode == 1) {
                llFaren.setVisibility(View.GONE);
            } else {
                llFaren.setVisibility(View.VISIBLE);
            }
            loadImageBean();
        }
    }


    /**
     * 从远程下载升级版本的app文件
     *
     * @param address
     */
    private void downFile(final String address, final Context context) {
        pBar = new ProgressDialog(context);    //进度条，在下载的时候实时更新进度，提高用户友好度
        pBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pBar.setTitle("正在下载");
        pBar.setMessage("请稍候...");
        pBar.setCanceledOnTouchOutside(false);
        pBar.setCancelable(false);
        pBar.setProgress(0);
        pBar.show();

        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(address);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    int length = (int) entity.getContentLength();   //获取文件大小
                    pBar.setMax(length);                            //设置进度条的总长度
                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory() + "/VipManage",
                                "非法人授权书模板.doc");
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];   //这个是缓冲区
                        int ch = -1;
                        int process = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            process += ch;
                            pBar.setProgress(process);       //这里就是关键的实时更新进度了！
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    handle.sendEmptyMessage(1);
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }


    private Handler handle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    pBar.cancel();
                    CustomToast.makeText(OpenQRPayActivity.this, "下载完成，请到手机VipManage文件夹目录下打开", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };


    private void getmccwx1(int code) {
        RequestParams params = new RequestParams();
        params.put("mccuni1id", code);

        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        mClient.setCookieStore(cookieStore);
        mClient.addHeader("Accept", "application/json, text/javascript, */*; q=0.01");
        mClient.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

        mClient.post(HttpAPI.API().getmccwx1, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    industryTypeTwo.add("请选择");
                    industryCodeTwo.add(-1);
                    String responseString = null;
                    responseString = new String(responseBody, "UTF-8");
                    if (responseString != null && !responseString.equals("")) {
                        Type listType = new TypeToken<List<Mccwx1idBean>>() {
                        }.getType();
                        mccwx1idBean = CommonFun.JsonToObj(responseString, listType);
                        for (Mccwx1idBean bean : mccwx1idBean) {
                            industryTypeTwo.add(bean.getSecondname());
                            industryCodeTwo.add(bean.getId());
                        }


                    }


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(TAG, "error: " + error.toString());

            }
        });

    }


    private void getmccwx2(int code) {
        RequestParams params = new RequestParams();
        params.put("mccuni2id", code);

        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        mClient.setCookieStore(cookieStore);

        mClient.post(HttpAPI.API().getmccwx2, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    industryTypeThree.add("请选择");
                    industryCodeThree.add("-1");
                    String responseString = new String(responseBody, "UTF-8");
                    if (responseString != null && !responseString.equals("")) {
                        Type listType = new TypeToken<List<Mccwx2idBean>>() {
                        }.getType();
                        mccwx2idBeans = CommonFun.JsonToObj(responseString, listType);
                        for (Mccwx2idBean bean : mccwx2idBeans) {
                            industryTypeThree.add(bean.getThirdname());
                            industryCodeThree.add(bean.getCode());
                        }

                    }


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    private void getcity(int code, final int type) {
        RequestParams params = new RequestParams();
        params.put("provinceid", code);

        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        mClient.setCookieStore(cookieStore);

        mClient.post(HttpAPI.API().getcity, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {

                    city.add("请选择");

                    bankcity.add("请选择");
                    bankcityCode.add(-1);
                    String responseString = new String(responseBody, "UTF-8");
                    if (responseString != null && !responseString.equals("")) {
                        Type listType = new TypeToken<List<CityBean>>() {
                        }.getType();
                        cityBeanList = CommonFun.JsonToObj(responseString, listType);
                        for (CityBean bean : cityBeanList) {
                            if (type == 1) {
                                city.add(bean.getName());
                            } else {
                                bankcity.add(bean.getName());
                                bankcityCode.add(bean.getId());
                            }
                        }
                    }

                    for (int i = 0; i < bankcityCode.size(); i++) {
                        if (mBankcityId == bankcityCode.get(i)) {
                            tvBankCity.setText(bankcity.get(i));
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(TAG, "onFailure: " + error.toString());
            }
        });
    }


    private void getcounty(int code) {
        RequestParams params = new RequestParams();
        params.put("cityid", code);

        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        mClient.setCookieStore(cookieStore);

        mClient.post(HttpAPI.API().getcounty, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    country.add("请选择");
                    countryCode.add("-1");
                    String responseString = new String(responseBody, "UTF-8");
                    if (responseString != null && !responseString.equals("")) {
                        Type listType = new TypeToken<List<CountryBean>>() {
                        }.getType();
                        countryBeanList = CommonFun.JsonToObj(responseString, listType);
                        for (CountryBean bean : countryBeanList) {
                            country.add(bean.getName());
                            countryCode.add(bean.getCode());
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    private void getbank(String code, int cityid) {
        RequestParams params = new RequestParams();
        params.put("bankparentid", code);
        params.put("cityid", cityid);


        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        mClient.setCookieStore(cookieStore);

        mClient.post(HttpAPI.API().getbank, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    bankbranch.add("请选择");
                    bankbranchCode.add("-1");
                    String responseString = new String(responseBody, "UTF-8");
                    if (responseString != null && !responseString.equals("")) {
                        Type listType = new TypeToken<List<BankBean>>() {
                        }.getType();
                        bankBeanList = CommonFun.JsonToObj(responseString, listType);
                        for (BankBean bean : bankBeanList) {
                            bankbranch.add(bean.getBankName());
                            bankbranchCode.add(bean.getUnionBankNo());
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }


    private boolean verify() {
        if (TextUtils.isEmpty(etShopName.getText())) {
            CustomToast.makeText(this, "商户名称不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (business_type == 2 || business_type == 1) {
            if (TextUtils.isEmpty(etLicenseNum.getText())) {
                CustomToast.makeText(this, "执照证件号码不能为空", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (TextUtils.isEmpty(tvLicenseOvertime.getText())) {
                CustomToast.makeText(this, "证件到期日期不能为空", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if (TextUtils.isEmpty(etShopNameShort.getText())) {
            CustomToast.makeText(this, "商户简称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(etRegisteName.getText())) {
            CustomToast.makeText(this, "注册名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (TextUtils.isEmpty(etContactsName.getText())) {
            CustomToast.makeText(this, "联系人姓名不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etContactsPhone.getText())) {
            CustomToast.makeText(this, "联系人手机不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(etContactsEmail.getText())) {
            CustomToast.makeText(this, "联系人邮箱不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (TextUtils.isEmpty(tvIndustryType.getText())) {
            CustomToast.makeText(this, "请先选择行业类别", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(tvIndustryTypeTwo.getText())) {
            CustomToast.makeText(this, "请先选择行业二级类别", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(tvIndustryTypeThree.getText())) {
            CustomToast.makeText(this, "请先选择行业三级类别", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (TextUtils.isEmpty(tvArea.getText())) {
            CustomToast.makeText(this, "请先选择所在省", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(tvCity.getText())) {
            CustomToast.makeText(this, "请先选择所在市", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(tvCountry.getText())) {
            CustomToast.makeText(this, "请先选择所在区县", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (TextUtils.isEmpty(etContactsAddress.getText())) {
            CustomToast.makeText(this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(tvArea.getText())) {
            CustomToast.makeText(this, "请先选择所在省", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(tvCity.getText())) {
            CustomToast.makeText(this, "请先选择所在市", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(tvCountry.getText())) {
            CustomToast.makeText(this, "请先选择所在区县", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (TextUtils.isEmpty(etContactsAddress.getText())) {
            CustomToast.makeText(this, "详细地址不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(tvBank.getText())) {
            CustomToast.makeText(this, "请先选择开户银行", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(tvBankProvince.getText())) {
            CustomToast.makeText(this, "请先选择支行所在省市", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(tvBankCity.getText())) {
            CustomToast.makeText(this, "请先选择支行所在市", Toast.LENGTH_SHORT).show();
            return false;
        }


        if (TextUtils.isEmpty(tvBankBranch.getText())) {
            CustomToast.makeText(this, "请先选择开户支行", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(etAccountName.getText())) {
            CustomToast.makeText(this, "开户名称不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (TextUtils.isEmpty(etAccountNum.getText())) {
            CustomToast.makeText(this, "开户账号不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (business_type == 2 || business_type == 1) {
            if (TextUtils.isEmpty(tvAccountType.getText())) {
                CustomToast.makeText(this, "请先选择账户类型", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (maccountTypeCode == 2) {//对私

                if (TextUtils.isEmpty(tvAccountPerson.getText())) {
                    CustomToast.makeText(this, "请先选择入账对象", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if (maccountforwardCode == 2) {

                    if (TextUtils.isEmpty(etFarenName.getText())) {
                        CustomToast.makeText(this, "法人名称不能为空", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    if (TextUtils.isEmpty(etFarenIdcard.getText())) {
                        CustomToast.makeText(this, "法人身份证号不能为空", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
                if (TextUtils.isEmpty(etCardNum.getText())) {
                    CustomToast.makeText(this, "证件号码不能为空", Toast.LENGTH_SHORT).show();
                    return false;
                }
                if (TextUtils.isEmpty(tvIdCardOvertime.getText())) {
                    CustomToast.makeText(this, "证件过期时间不能为空", Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {//对公
                if (TextUtils.isEmpty(etFarenName.getText())) {
                    CustomToast.makeText(this, "法人名称不能为空", Toast.LENGTH_SHORT).show();
                    return false;
                }

                if (TextUtils.isEmpty(etFarenIdcard.getText())) {
                    CustomToast.makeText(this, "法人身份证号不能为空", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }

        }

        for (int i = 0; i < imgList.size(); i++) {
            if (imgList.get(i).isStar() && imgList.get(i).getImageAddress().equals("")) {
                CustomToast.makeText(this, "请先选择" + imgList.get(i).getNamme(), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    private void saveData() {
        RequestParams params = new RequestParams();
        params.put("merchant_name", etShopName.getText().toString());//商户名称
        if (business_type == 2 || business_type == 1) {

        }
        params.put("merchant_alias", etShopNameShort.getText().toString());//商户简称
        params.put("merchant_company", etRegisteName.getText().toString());//商户名称注册名称
        params.put("merchant_province", mprovince);//北京市
        params.put("merchant_province_code", mProviceId);//110
        params.put("merchant_city", mcity);//北京市
        params.put("merchant_city_code", mcityCode);//1000
        params.put("merchant_county", mcountry);//东城区
        params.put("merchant_county_code", mcountryCode);//1000
        params.put("merchant_address", etContactsAddress.getText().toString());//详细地址
        params.put("merchant_email", etContactsEmail.getText().toString());//联系人邮箱
        params.put("merchant_phone", etContactsPhone.getText().toString());//联系人电话
        params.put("merchant_person", etContactsName.getText().toString());//联系人
        params.put("business_name", mindustryTypeThree);//超市
        params.put("business_code", mindustryCodeThree);//204
        params.put("merchant_business_type", business_type);// 商户类型:1企业，2个体工商户，3小微商户

        params.put("account_name", etAccountName.getText().toString());//银行开户户头
        params.put("account_no", etAccountNum.getText().toString());//654654544656546464555
        params.put("bank_name", mbankbranch);//中国工商银行股份有限公司泌阳支行
        params.put("bank_no", mbankbranchCode);//102511302847
        params.put("license_no", etCardNum.getText().toString());//500242199210288473
        params.put("license_expire", tvIdCardOvertime.getText().toString());//2019-08-23


        params.put("legalIdnumExpire", "2999-12-31");//2999-12-31
        params.put("merchant_id_no", etCardNum.getText().toString());//500242199210288473

        params.put("account_phone", "");//

        for (int i = 0; i < imgList.size(); i++) {
            if (imgList.get(i).getNamme().equals("手持身份证照片")) {
                params.put("img_idcard_holding", imgList.get(i).getImageAddress());//本人手持身份证照片
            } else if (imgList.get(i).getNamme().equals("银行卡正面照片")) {
                params.put("img_bankcard_a", imgList.get(i).getImageAddress());//入账银行卡正面照片
            } else if (imgList.get(i).getNamme().equals("银行卡反面照片")) {
                params.put("img_bankcard_b", imgList.get(i).getImageAddress());//入账银行卡反面照片
            } else if (imgList.get(i).getNamme().equals("商户门头照片")) {
                params.put("img_logo", imgList.get(i).getImageAddress());//商户门头照片
            } else if (imgList.get(i).getNamme().equals("店内场景照")) {
                params.put("img_indoor", imgList.get(i).getImageAddress());//店内场景照
            } else if (imgList.get(i).getNamme().equals("结算人身份证正面")) {
//                params.put("img_idcard_a", imgList.get(i).getImageAddress());//法人身份证正面照片
                params.put("img_private_idcard_a", imgList.get(i).getImageAddress());//对私账户身份证正面照片
            } else if (imgList.get(i).getNamme().equals("结算人身份证反面")) {
//                params.put("img_idcard_b", imgList.get(i).getImageAddress());//法人身份证反面照片
                params.put("img_private_idcard_b: ", imgList.get(i).getImageAddress());//对私账户身份证反面照片
            } else if (imgList.get(i).getNamme().equals("法人身份证正面")) {
                params.put("img_idcard_a", imgList.get(i).getImageAddress());//法人身份证正面照片
            } else if (imgList.get(i).getNamme().equals("法人身份证反面")) {
                params.put("img_idcard_b", imgList.get(i).getImageAddress());//法人身份证反面照片
            } else if (imgList.get(i).getNamme().equals("营业执照照片")) {
                params.put("img_license", imgList.get(i).getImageAddress());//营业执照照片
            } else if (imgList.get(i).getNamme().equals("开户许可证照片")) {
                params.put("img_open_permits", imgList.get(i).getImageAddress());//开户许可证照片
            } else if (imgList.get(i).getNamme().equals("银行卡正面照片")) {
                params.put("img_bankcard_a", imgList.get(i).getImageAddress());//入账银行卡正面照片
            } else if (imgList.get(i).getNamme().equals("银行卡正面照片")) {
                params.put("img_bankcard_a", imgList.get(i).getImageAddress());//入账银行卡正面照片
            } else if (imgList.get(i).getNamme().equals("结算非法人授权书")) {
                params.put("img_unincorporated", imgList.get(i).getImageAddress());//入账非法人证明照片
            }
        }

        params.put("img_org_code", "");//组织机构代码证照片
        params.put("img_tax_reg", "");//税务登记证照片

        params.put("account_type", maccountTypeCode);//账户类型，1对公，2对私
        params.put("artif_nm", etFarenName.getText().toString());//法人名称
        params.put("legalIdnum", etFarenIdcard.getText().toString());//法人身份证号 500242199210288455

        params.put("merchant_id_expire", datachange(tvIdCardOvertime.getText().toString()));//20190907

        params.put("settlement_type", maccountforwardCode);//结算账户(1法人、2非法人)
        if (business_type == 3) {
            params.put("license_type", "2");//营业证件类型：0营业执照，1三证合一，2手持身份证
        } else {
            params.put("license_type", "1");
        }
        params.put("merchantwx1id", mindustryCodeOne);//
        params.put("merchantwx2id", industryCodeTwo);//
        params.put("bankparentID", mbankCode);//
        params.put("bankprovinceID", mBankprovinceyCode); //mBankprovinceyCode
        params.put("bankcityID", mBankcityId); //mBankcityCode

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(OpenQRPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("提示");
                mSweetAlertDialog.setContentText("申请提交成功");
                mSweetAlertDialog.setCancelable(false);
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        finish();

                    }
                });
                mSweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(OpenQRPayActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        String url = null;
        if (merchantInfoBean == null) {
            url = HttpAPI.API().MERCHANT;
        } else {
            url = HttpAPI.API().UPDATAMERCHANT;
        }
        HttpHelper.post(this, url, params, callBack);
    }


    private void openPay() {
        RequestParams params = new RequestParams();
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(OpenQRPayActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("提示");
                mSweetAlertDialog.setContentText("开通扫呗支付成功");
                mSweetAlertDialog.setCancelable(false);
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        finish();

                    }
                });
                mSweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(OpenQRPayActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().OPENSAOBEI, params, callBack);

    }

    private String datachange(String data) {
        String result = data;
        result = result.replace("-", "");

        return result;
    }

    private String changedata(String data) {
        String result = data;

        StringBuilder sb = new StringBuilder(result);//构造一个StringBuilder对象
        sb.insert(6, "-");
        sb.insert(4, "-");
        result = sb.toString();

        return result;
    }


    /**
     * 获取申请信息
     */
    private void GetMerchantInfo() {
        RequestParams params = new RequestParams();
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                merchantInfoBean = CommonFun.JsonToObj(responseString, GetMerchantInfoBean.class);
                if (merchantInfoBean != null) {
                    setData();
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(OpenQRPayActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().MERCHANT_INFO, params, callBack);
    }


    private void setData() {
        if (merchantInfoBean.getData().getMerchant_status() == 1
                || merchantInfoBean.getData().getMerchant_status() == 5) {//商户状态，01审核通过，但未签署电子协议，02审核驳回，03审核中,05审核通过且已签署电子协议
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText("你的资料已提交,审核通过");
            tvSaveTwice.setVisibility(View.VISIBLE);
            tvSaveTwice.setText("开通支付");
            slBody.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);
            istongguo = true;

        } else if (merchantInfoBean.getData().getMerchant_status() == 2) {
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText("审核未通过，原因：" + merchantInfoBean.getData().getReturn_msg());
            tvSaveTwice.setVisibility(View.VISIBLE);
            tvSaveTwice.setText("重新提交");
            slBody.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);
            istongguo = false;

        } else if (merchantInfoBean.getData().getMerchant_status() == 3) {
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText("你的资料已提交,正在审核中。审核时间大概1~3日。");
            tvSaveTwice.setVisibility(View.GONE);
            slBody.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);
            istongguo = false;
        }

        etShopName.setText(merchantInfoBean.getData().getMerchant_name());
        etShopNameShort.setText(merchantInfoBean.getData().getMerchant_alias());
        etRegisteName.setText(merchantInfoBean.getData().getMerchant_company());
        etContactsAddress.setText(merchantInfoBean.getData().getMerchant_address());
        etContactsEmail.setText(merchantInfoBean.getData().getMerchant_email());
        etContactsPhone.setText(merchantInfoBean.getData().getMerchant_phone());
        etContactsName.setText(merchantInfoBean.getData().getMerchant_person());
        etAccountName.setText(merchantInfoBean.getData().getAccount_name());
        etAccountNum.setText(merchantInfoBean.getData().getAccount_no());

        etAccountName.setText(merchantInfoBean.getData().getArtif_nm());

        etCardNum.setText(merchantInfoBean.getData().getMerchant_id_no());
        tvIdCardOvertime.setText(changedata(merchantInfoBean.getData().getMerchant_id_expire()));


        for (int i = 0; i < imgList.size(); i++) {
            if (imgList.get(i).getNamme().equals("手持身份证照片")) {
                imgList.get(i).setImageAddress(merchantInfoBean.getData().getImg_idcard_holding());
            } else if (imgList.get(i).getNamme().equals("银行卡正面照片")) {
                imgList.get(i).setImageAddress(merchantInfoBean.getData().getImg_bankcard_a());
            } else if (imgList.get(i).getNamme().equals("银行卡反面照片")) {
                imgList.get(i).setImageAddress(merchantInfoBean.getData().getImg_bankcard_b());
            } else if (imgList.get(i).getNamme().equals("商户门头照片")) {
                imgList.get(i).setImageAddress(merchantInfoBean.getData().getImg_logo());
            } else if (imgList.get(i).getNamme().equals("店内场景照")) {
                imgList.get(i).setImageAddress(merchantInfoBean.getData().getImg_indoor());
            } else if (imgList.get(i).getNamme().equals("结算人身份证正面")) {
                imgList.get(i).setImageAddress(merchantInfoBean.getData().getImg_private_idcard_a());
            } else if (imgList.get(i).getNamme().equals("结算人身份证反面")) {
                imgList.get(i).setImageAddress(merchantInfoBean.getData().getImg_private_idcard_b());
            } else if (imgList.get(i).getNamme().equals("营业执照照片")) {
                imgList.get(i).setImageAddress(merchantInfoBean.getData().getImg_license());
            } else if (imgList.get(i).getNamme().equals("开户许可证照片")) {
                imgList.get(i).setImageAddress(merchantInfoBean.getData().getImg_open_permits());
            }
        }
        mShopRightAdapter.notifyDataSetChanged();
        tvIndustryTypeThree.setText(merchantInfoBean.getData().getBusiness_name());
        mindustryTypeThree = merchantInfoBean.getData().getBusiness_name();
        mindustryCodeThree = merchantInfoBean.getData().getBusiness_code();

        tvArea.setText(merchantInfoBean.getData().getMerchant_province());
        mprovince = merchantInfoBean.getData().getMerchant_province();
        mProviceId = merchantInfoBean.getData().getMerchant_province_code();

        tvCity.setText(merchantInfoBean.getData().getMerchant_city());
        mcity = merchantInfoBean.getData().getMerchant_city();
        mcityCode = merchantInfoBean.getData().getMerchant_city_code();

        tvCountry.setText(merchantInfoBean.getData().getMerchant_county());
        mcountry = merchantInfoBean.getData().getMerchant_county();
        mcountryCode = merchantInfoBean.getData().getMerchant_county_code();


//        tvCountry.setText(merchantInfoBean.getData().getMerchant_county());
//        mcountry = merchantInfoBean.getData().getMerchant_county();
//        mcountryCode = merchantInfoBean.getData().getMerchant_county_code();
//
//        tvCountry.setText(merchantInfoBean.getData().getMerchant_county());
//        mcountry = merchantInfoBean.getData().getMerchant_county();
//        mcountryCode = merchantInfoBean.getData().getMerchant_county_code();

        mbankCode = merchantInfoBean.getData().getBankparentID();
        if (mbankCode != null) {
            for (int i = 0; i < bankCode.size(); i++) {
                if (mbankCode.equals(bankCode.get(i))) {
                    tvBank.setText(bank.get(i));
                }
            }
        }

        if (merchantInfoBean.getData().getBankprovinceID() != null) {
            mBankprovinceyCode = Integer.valueOf(merchantInfoBean.getData().getBankprovinceID());
            for (int i = 0; i < provinceCode.size(); i++) {
                if (mBankprovinceyCode == provinceCode.get(i)) {
                    tvBankProvince.setText(province.get(i));
                }
            }
            getcity(mBankprovinceyCode, 2);
        }

        if (merchantInfoBean.getData().getBankcityID() != null) {
            mBankcityId = Integer.valueOf(merchantInfoBean.getData().getBankcityID());

            for (int i = 0; i < provinceCode.size(); i++) {
                if (mBankprovinceyCode == provinceCode.get(i)) {
                    tvBankProvince.setText(province.get(i));
                }
            }

            getbank(mbankCode, mBankcityId);
        }


    }


    /**
     * 上传图片
     */
    private void postUploadMemberPhoto() {
        RequestParams params = new RequestParams();
        params.put("DataBase64", imageToBase64(mCropFile));
        params.put("ZIP", "");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                memberPhotoBean = CommonFun.JsonToObj(responseString, MemberPhotoBean.class);
                String PhotoAddress = memberPhotoBean.getData();

                imgList.get(pos).setImageAddress(PhotoAddress);

                mShopRightAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(OpenQRPayActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在上传头像...", false);
        HttpHelper.post(this, HttpAPI.API().UPLOAD_GIFT_IMAGE, params, callBack);
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
                if (data != null && GetImagePath.getPath(getApplicationContext(), data.getData()) != null) {
                    File imgUri = new File(GetImagePath.getPath(getApplicationContext(), data.getData()));
                    Uri dataUri = FileProvider.getUriForFile(OpenQRPayActivity.this, MyApplication.FILE_ADDRESS, imgUri);
                    startPhotoZoom(dataUri);
//                    civHeadImg.setImageURI(dataUri);
                }
                break;
            case CAMERA_REQUEST_CODE: //照相后返回

                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri inputUri = FileProvider.getUriForFile(OpenQRPayActivity.this, MyApplication.FILE_ADDRESS, mCameraFile);//通过FileProvider创建一个content类型的Uri
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
                    Uri inputUri = FileProvider.getUriForFile(OpenQRPayActivity.this,
                            MyApplication.FILE_ADDRESS, mCropFile);//通过FileProvider创建一个content类型的Uri
                    mUri = inputUri;
                    postUploadMemberPhoto();
//                    civHeadImg.setImageURI(mUri);
                }
                break;
        }
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
            Uri uriForFile = FileProvider.getUriForFile(OpenQRPayActivity.this, MyApplication.FILE_ADDRESS, mGalleryFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, SELECT_PIC_KITKAT);
        } else {
            startActivityForResult(intent, IMAGE_REQUEST_CODE);
        }
    }


    /**
     * 拍照
     */
    private void jump2Caramel() {

        if (hasCamera()) {
            if (CheckPromiss.checkPermission(OpenQRPayActivity.this)) {
                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
                    Uri uriForFile = FileProvider.getUriForFile(OpenQRPayActivity.this,
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
            CustomToast.makeText(OpenQRPayActivity.this, "未获取到相机，请确认后再尝试！", Toast.LENGTH_SHORT).show();
            return;
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
     * 警告弹窗
     *
     * @param msg
     */
    private void warnDialog(String msg) {
        mSweetAlertDialog = new SweetAlertDialog(OpenQRPayActivity.this, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCancelable(false);
        mSweetAlertDialog.setConfirmText("确定");
        mSweetAlertDialog.show();
    }

}
