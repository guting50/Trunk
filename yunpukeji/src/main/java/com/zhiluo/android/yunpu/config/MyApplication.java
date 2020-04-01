package com.zhiluo.android.yunpu.config;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.basewin.database.DataBaseManager;
import com.basewin.log.LogUtil;
import com.basewin.services.ServiceManager;
import com.lkl.cloudpos.aidl.AidlDeviceService;
import com.lkl.cloudpos.aidl.magcard.AidlMagCard;
import com.lkl.cloudpos.aidl.printer.AidlPrinter;
import com.lkl.cloudpos.aidl.rfcard.AidlRFCard;
import com.sunmi.pay.hardware.aidl.readcard.ReadCardOpt;
import com.sunmi.payservice.hardware.aidl.AidlConstants;
import com.sunmi.payservice.hardware.aidl.HardwareOpt;
import com.zhiluo.android.yunpu.network.HttpBannerImageHelper;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import sunmi.paylib.SunmiPayKernel;
import sunmi.payservicelib.SunmiPayService;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class  MyApplication extends LitePalApplication {
    /***
     *测试版
     */
//    public static  String BASE_URL = "http://pcbeta.yunvip123.com/api/";
//    public static  String IMAGE_URL = "http://pcbeta.yunvip123.com";
//    public static  String CTMONEY_URL = "http://corebeta.yunvip123.com/";

    /**
     * 正式版
     **/
    public static String BASE_URL = "https://pc.yunvip123.com/api/";
    public static String IMAGE_URL = "https://pc.yunvip123.com";
    public static String CTMONEY_URL = "https://core.yunvip123.com/";



    public static HardwareOpt mHardwareOpt;
    /**
     * 友盟appKey
     */
    public static final String UM_KEY = "5a17b6bca40fa33ec100040a";

    /**
     * 获取全局Context对象
     * @return
     */
    public static Context getmContext() {
        return mContext;
    }

    public static boolean dialogFlag = true;

    /**
     * logo
     */
    public static String HYCZ_LOGO, HYCC_LOGO, SPXF_LOGO, KSXF_LOGO, JCXF_LOGO, JFDH_LOGO,
            SPTH_LOGO, JB_LOGO, FTXF_LOGO, HYSK_LOGO, XSXF_LOGO,OPENCARD_LOGO,RK_LOGO,CK_LOGO,YJJY_LOGO;
    /**
     * 二维码
     */
    public static String HYCZ_QR, HYCC_QR, SPXF_QR, KSXF_QR, JCXF_QR, JFDH_QR,
            SPTH_QR, JB_QR, FTXF_QR, HYSK_QR, XSXF_QR,OPENCARD_QR,RK_QR,CK_QR,YJJY_QR;

    /**
     * 会员充次
     */
    public static Map<String, String> mTimesRechargeMap;
    /**
     * 会员充值
     */
    public static Map<String, String> mRechargeMap;
    /**
     * 商品消费
     */
    public static Map<String, String> mGoodsConsumeMap;
    /**
     * 计次消费
     */
    public static Map<String, String> mTimesConsumeMap;
    /**
     * 积分兑换
     */
    public static Map<String, String> mIntegralExchangeMap;
    /**
     * 快速消费
     */
    public static Map<String, String> mFastConsumeMap;
    /**
     * 限时消费
     */
    public static Map<String, String> mXSXFonsumeMap;


    /**
     * 会员开卡
     * **/
    public static Map<String, String> mCardOpenMap;

    /**
     * 商品入库
     * **/
    public static Map<String, String> mGoodsIn;

    /**
     * 商品出库
     * **/
    public static Map<String, String> mGoodsOut;

    /**
     * 商品退货
     * **/
    public static Map<String, String> mReTureOrder;

    /**
     * 一键加油
     * **/
    public static Map<String, String> mYJJYMap;

    /**
     * 交班
     * **/

    public static Map<String, String> mHandOverMap;
    /**
     * 打印是否开启
     */
    public static boolean PRINT_IS_OPEN  = false;

    /**
     * 短信开关是否开启
     * */
    public static boolean SMS_IS_OPEN;
    public static String VIP_CARD;
    /**
     * 小票打印份数
     */
    public static int HYCZ_PRINT_TIMES = 1;
    public static int HYCC_PRINT_TIMES = 1;
    public static int SPXF_PRINT_TIMES = 1;
    public static int YJJY_PRINT_TIMES = 1;
    public static int KSXF_PRINT_TIMES = 1;
    public static int JCXF_PRINT_TIMES = 1;
    public static int JFDH_PRINT_TIMES = 1;
    public static int TCXF_PRINT_TIMES = 1;
    public static int HYKK_PRINT_TIMES = 1;
    public static int RK_PRINT_TIMES = 1;
    public static int CK_PRINT_TIMES = 1;
    public static int JB_PRINT_TIMES = 1;
    public static int SPTH_PRINT_TIMES = 1;
    public static int XSXF_PRINT_TIMES = 1;
    public static int PRINTPAPER = 2;

    public static final int SCAN_RETURN = 7777;//扫码返回码
    private static Context mContext;


    public static double VICODE = 1.988;   //版本号
    public final static String LOGIN_VERSION_ZX = "©2015-2019  All  Rights  Reserved  V1.9.8.8";//登录页面展示版本号的，不用没去登录页面该
    public final static String LOGIN_VERSION_YSP = "©2015-2019  All  Rights  Reserved云上铺  V1.9.8.8";
    public static boolean IS_YUNSHANGPU = true;
    public static String FILE_ADDRESS = "com.zhiluo.android.yunpu.fileprovider";
    public static final String APK_ADDDRESS = Environment.getExternalStorageDirectory()+"/VipManage";

    public static final String TAG = "TAG";
    private static MyApplication instance = null;
    public static final String CARRIER = android.os.Build.MANUFACTURER;    //获取手机厂商

    private static final String POS_BASEWIN = "basewin"; //世麦basewin
    private static final String POS_LANDI = "LANDI";   //拉卡拉   联迪厂家
    private static final String POS_CENTERM = "Centerm";   //拉卡拉   升腾厂家

    /**
     * 如果是pos设备就设置为false，如果不是pos设备就设置为true
     **/
    public static boolean IS_BASEWIN_POS_DEVICE = false;//世麦
    public static boolean IS_LAKALA_POS_DEVICE = false;//拉卡拉
    public static boolean IS_SUNMI_POS_P1_DEVICE = false;//SUNMI P1
    public static boolean IS_SUNMI_POS_DEVICE = false;//SUNMI
    public static boolean IS_SUNMI_POS_V1S_DEVICE = false;//V1s

    /*****************拉卡拉设备***************/
    private AidlDeviceService serviceManager;
    public static final String LKL_SERVICE_ACTION = "lkl_cloudpos_mid_service"; //行业应用服务名
    public static AidlMagCard magCardDev = null; // 磁条卡设备
    public static boolean MAG_CARD = false;
    public static AidlPrinter printerDev = null; // 小票打印机设备
    public static boolean PRINTER_DEV = false;
    public static AidlRFCard RF_Card = null; //非接卡操作实例
    public static boolean RFCARD_DEV = false;

    /*****************SUNMI设备*****************/
    private static SunmiPayKernel sSunmiPayKernel;  //SUNMI支付SDK操作核心对象
    /**
     * 获取读卡模块
     */
    public static ReadCardOpt sReadCardOpt;


    /**
     * 部分字段名
     */
    public static final String PAGE_FLAG = "page_flag";//页面标志（用来识别是哪一个页面跳转到支付确认页面）
    public static final String ORDER_NO = "order_no";//订单编号
    public static final String VIP_GRADE = "vip_grade";//会员等级
    public static final String SPEND_MONEY = "spend_money";//消费金额
    public static final String CARD_NO = "card_no";//卡号
    public static final String VIP_NAME = "vip_name";//会员姓名
    public static final String PAY_TYPE = "pay_type";//支付类型

    public static String SUNMI_DEVICE_MODEL;//商米设备型号

    public static boolean IS_FIRST_LOGING = true;//首次登录
    public static boolean IS_CANCEL_ORDER = false;//是否可以撤单
    public static String CANCEL_PAW = "123456";

    public static boolean LOGINCODE = true;//首页跳转标识（解决多次跳转首页问题）
    public static String password = "";//缓存登录密码

    public static boolean isOneKey = false;
    public static boolean isDingXiang = false;

    /**
     * 连接拉卡拉支付的sdk
     */
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder serviceBinder) {
            Log.d("TAG", "行业SDK服务连接成功");
            if (serviceBinder != null) {
                //获取设备服务实例
                serviceManager = AidlDeviceService.Stub.asInterface(serviceBinder);
                onDeviceConnected(serviceManager);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("TAG", "行业SDK服务断开了");
        }
    };

    /**
     * 拉卡拉pos服务绑定的回调
     */
    public void onDeviceConnected(AidlDeviceService serviceManager) {
        try {
            printerDev = AidlPrinter.Stub.asInterface(serviceManager.getPrinter());
            PRINTER_DEV = true;
            Log.d("TAG", "绑定打印服务成功");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            PRINTER_DEV = false;
            Log.d("TAG", "绑定打印服务失败");
        }

        try {
            RF_Card = AidlRFCard.Stub.asInterface(serviceManager.getRFIDReader());
            Log.d("TAG", "绑定非接触式IC卡成功");
            RF_Card.open();
            RFCARD_DEV = true;
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            RFCARD_DEV = false;
            Log.d("TAG", "绑定非接触式IC卡失败");
        }

        try {
            magCardDev = AidlMagCard.Stub.asInterface(serviceManager.getMagCardReader());
            MAG_CARD = true;
            Log.d("TAG", "绑定磁条卡服务成功");
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            MAG_CARD = false;
            Log.d("TAG", "绑定磁条卡服务失败");
        }
    }

    /**
     * SUNMI连接支付SDK
     */
    private void conn() {
        sSunmiPayKernel = SunmiPayKernel.getInstance();
        sSunmiPayKernel.connectPayService(getApplicationContext(), mConnCallback);
    }

    /**
     * SUNMI连接状态回调
     */
    private SunmiPayKernel.ConnCallback mConnCallback = new SunmiPayKernel.ConnCallback() {
        @Override
        public void onServiceConnected() {
            try {
                sReadCardOpt = sSunmiPayKernel.mReadCardOpt;
            } catch (Exception e) {
                Le("getmConnCallback异常");
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected() {
        }
    };

    public MyApplication() {
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 拉卡拉pos绑定服务
     */
    @SuppressLint("WrongConstant")
    public void bindService() {
        try {
            Intent intent = new Intent();
            intent.setAction(LKL_SERVICE_ACTION);
            intent.setPackage("com.lklcloudpos.midservice");
            Log.d("TAG", intent.toString());
            boolean flag = false;
            flag = bindService(intent, conn, Context.BIND_AUTO_CREATE);
            if (flag) {
                Log.d("TAG", "服务绑定成功");
            } else {
                Log.d("TAG", "服务绑定失败");
            }
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**全局异常处理20191107 cy */
//        Thread.setDefaultUncaughtExceptionHandler(CrashHandler.getInstance(this));
        /*************** 20191107 cy */
        LitePal.initialize(this);
        mContext = getApplicationContext();
        HttpBannerImageHelper.post_BannerImage();


        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数3:Push推送业务的secret
         */
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);//友盟分析初始化


//        LitePal.getDatabase();//创建Banner图片数据库
//
//        DownLoadBannerUtil.postBannerUrl();

        mTimesRechargeMap = new HashMap<>();
        mRechargeMap = new HashMap<>();
        mGoodsConsumeMap = new HashMap<>();
        mTimesConsumeMap = new HashMap<>();
        mIntegralExchangeMap = new HashMap<>();
        mFastConsumeMap = new HashMap<>();
        mXSXFonsumeMap = new HashMap<>();
        mCardOpenMap= new HashMap<>();
        mHandOverMap = new HashMap<>();
        mGoodsIn = new HashMap<>();
        mGoodsOut = new HashMap<>();
        mReTureOrder = new HashMap<>();
        mYJJYMap = new HashMap<>();


        /******************初始化POS机器****************/
        String s = Build.MANUFACTURER.toString();
        if (s.contains("SUNMI")) {
            IS_SUNMI_POS_DEVICE = true;
            String model = SystemProperties.get("ro.product.model");
            if (model.equals("V1s")){
                IS_SUNMI_POS_V1S_DEVICE = true;
            }else {
                IS_SUNMI_POS_P1_DEVICE = true;
                conn();
                connectPayService();
            }
        } else if (android.os.Build.MANUFACTURER.toString().contains(POS_BASEWIN)) {
            DataBaseManager.getInstance().init(getApplicationContext());
            ServiceManager.getInstence().init(getApplicationContext());
            IS_BASEWIN_POS_DEVICE = true;
        } else if (android.os.Build.MANUFACTURER.toString().equals(POS_LANDI)
                | android.os.Build.MANUFACTURER.toString().equals(POS_CENTERM)) {
            IS_LAKALA_POS_DEVICE = true;
            bindService();
        }else {
            IS_SUNMI_POS_V1S_DEVICE = true;//其它手机刷卡
        }


        //创建数据库
        //        DBHelper dbHelper = new DBHelper(this);
        //        dbHelper.createDB();

        //关闭SDK LOG
        LogUtil.closeLog();

    }

    private SunmiPayService sunmiPayService;

    private void connectPayService() {
        sunmiPayService = SunmiPayService.getInstance();
        sunmiPayService.connectPayService(getApplicationContext(), connCallback);
    }

    private SunmiPayService.ConnCallback connCallback = new SunmiPayService.ConnCallback() {
        @Override
        public void onServiceConnected() {
            Le("onSer");
            MyApplication.mHardwareOpt = sunmiPayService.mHardwareOpt;
            try {
                MyApplication.SUNMI_DEVICE_MODEL = MyApplication.mHardwareOpt.getSysParam(AidlConstants.SysParam.DEVICE_MODEL);
            } catch (RemoteException e) {
                Le("onSer异常");
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected() {

        }

    };


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base); MultiDex.install(this);
    }
}


