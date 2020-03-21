package com.zhiluo.android.yunpu.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.basewin.aidl.OnPrinterListener;
import com.basewin.services.ServiceManager;
import com.lkl.cloudpos.aidl.printer.AidlPrinterListener;
import com.lkl.cloudpos.aidl.printer.PrintItemObj;
import com.zhiluo.android.yunpu.print.bean.CK_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.JB_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_HYKK_Bean;

import com.zhiluo.android.yunpu.print.bean.Print_SPTH_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_YJJY_Bean;
import com.zhiluo.android.yunpu.print.bean.RK_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.XSXF_Success_Bean;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InReCoderDetailBean;
import com.zhiluo.android.yunpu.ui.activity.good.bean.InRecoderBean;
import com.zhiluo.android.yunpu.ui.activity.handduty.HandDutyBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.print.util.PrinterSetContentsImpl;
import com.zhiluo.android.yunpu.print.bean.PayBean;
import com.zhiluo.android.yunpu.print.bean.Print_HYCC_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_HYCZ_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_JCXF_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_JFDH_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_KSXF_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_SPXF_Bean;
import com.zhiluo.android.yunpu.setting.bluetooth.activity.BlueToothActivity;
import com.zhiluo.android.yunpu.config.MyApplication;

import java.util.ArrayList;

import static com.zhiluo.android.yunpu.config.MyApplication.IS_BASEWIN_POS_DEVICE;
import static com.zhiluo.android.yunpu.config.MyApplication.IS_LAKALA_POS_DEVICE;
import static com.zhiluo.android.yunpu.config.MyApplication.printerDev;

/**
 * 打印小票
 * iauthor：Yc
 * date: 2017/6/25 14:17
 * email：jasoncheng9111@gmail.com
 */
public class PrinterUtils {
    private static int mReceitsNum;//打印小票数量
    private PrinterListener printer_callback = new PrinterListener();
    private Context mContext;
    private static BlueToothActivity mBlueToothActivity = new BlueToothActivity();
    private PayBean mPayBean = new PayBean();//结算字段Bean
    private Object mPrintBean;
    private PrinterSetContentsImpl mPrinterSetContents;//设置打印小票的内容和格式
    private String mIsConsumeName = "";//判断是哪一个消费模块

    public PrinterUtils(Context context, int receitsNum, Object printBean, String isConsumeName) {
        this.mContext = context;
        mReceitsNum = receitsNum;
        //        this.mPayBean = payBean;
        this.mPrintBean = printBean;
        this.mIsConsumeName = isConsumeName;
        this.mPrinterSetContents = new PrinterSetContentsImpl(mContext);
    }

    /**
     * 获取打印机状态
     */
    public static boolean getPrintState() {
        try {
            if (printerDev != null) {
                return true;
            } else {
                Log.d("TAG", "打印机对象为空");
//                CustomToast.makeText(MyApplication.getmContext(), "未连接打印机,请到【我的】->【打印设置】->【选择打印机】连接打印机", Toast.LENGTH_LONG).show();

                return false;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("TAG", "获取到的打印机状态失败");
//            CustomToast.makeText(MyApplication.getmContext(), "未连接打印机,请到【我的】->【打印设置】->【选择打印机】连接打印机", Toast.LENGTH_LONG).show();
            return false;
        }

    }


    /**
     * 打印小票
     */
    public void print() {

        switch (mIsConsumeName) {
            //快速消费
            case "KSXF":

                //世麦POS机器
                if (IS_BASEWIN_POS_DEVICE) {
                    //配置【快速消费】世麦POS机器的小票格式与数据值
                    mPrinterSetContents.printBaseWinPOS_KSXF((Print_KSXF_Bean) mPrintBean);
                    //开始打印
                    new Thread(print_BASEWINPOS_runnable).start();
                }
                //拉卡拉POS机器
                else if (IS_LAKALA_POS_DEVICE) {
                    //判断拉卡拉打印机状态是否正常
                    if (getPrintState()) {
                        //开始打印
                        new Thread(print_LKL_runnable).start();
                    }
                }
                //手机设备 或 桑米POS机器调用蓝牙打印
                else {
                    //配置【快速消费】非拉卡拉、世麦POS机器的小票格式
                    byte[] bytes = mPrinterSetContents.printBlueTooth_KSXF((Print_KSXF_Bean) mPrintBean);
                    //发送小票格式数据开始打印
                    mBlueToothActivity.send(bytes, MyApplication.KSXF_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                }
                break;
            //会员充值
            case "HYCZ":
                //世麦POS机器
                if (IS_BASEWIN_POS_DEVICE) {
                    //配置【会员充值】世麦POS机器的小票格式与数据值
                    mPrinterSetContents.printBaseWinPOS_HYCZ((Print_HYCZ_Bean) mPrintBean);
                    //开始打印
                    new Thread(print_BASEWINPOS_runnable).start();
                }
                //拉卡拉POS机器
                else if (IS_LAKALA_POS_DEVICE) {
                    //判断拉卡拉打印机状态是否正常
                    if (getPrintState()) {
                        //开始打印
                        new Thread(print_LKL_runnable).start();
                    }
                }
                //手机设备 或 桑米POS机器调用蓝牙打印
                else {
                    //配置【会员充值】非拉卡拉、世麦POS机器的小票格式
                    byte[] bytes = mPrinterSetContents.printBlueTooth_HYCZ((Print_HYCZ_Bean) mPrintBean);
                    //发送小票格式数据开始打印
                    mBlueToothActivity.send(bytes, MyApplication.HYCZ_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                }
                break;
            //会员充次
            case "HYCC":
                //世麦POS机器
                if (IS_BASEWIN_POS_DEVICE) {
                    //配置【会员充次】世麦POS机器的小票格式与数据值
                    mPrinterSetContents.printBaseWinPOS_HYCC((Print_HYCC_Bean) mPrintBean);
                    //开始打印
                    new Thread(print_BASEWINPOS_runnable).start();
                }
                //拉卡拉POS机器
                else if (IS_LAKALA_POS_DEVICE) {
                    //判断拉卡拉打印机状态是否正常
                    if (getPrintState()) {
                        //开始打印
                        new Thread(print_LKL_runnable).start();
                    }
                }
                //手机设备 或 桑米POS机器调用蓝牙打印
                else {
                    //配置【会员充次】非拉卡拉、世麦POS机器的小票格式
                    byte[] bytes = mPrinterSetContents.printBlueTooth_HYCC((Print_HYCC_Bean) mPrintBean);
                    //发送小票格式数据开始打印
                    mBlueToothActivity.send(bytes, MyApplication.HYCC_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                }
                break;
            //计次消费
            case "JCXF":
                //世麦POS机器
                if (IS_BASEWIN_POS_DEVICE) {
                    //配置【计次消费】世麦POS机器的小票格式与数据值
                    mPrinterSetContents.printBaseWinPOS_JCXF((Print_JCXF_Bean) mPrintBean);
                    //开始打印
                    new Thread(print_BASEWINPOS_runnable).start();
                }
                //拉卡拉POS机器
                else if (IS_LAKALA_POS_DEVICE) {
                    //判断拉卡拉打印机状态是否正常
                    if (getPrintState()) {
                        //开始打印
                        new Thread(print_LKL_runnable).start();
                    }
                }
                //手机设备 或 桑米POS机器调用蓝牙打印
                else {
                    //配置【计次消费】非拉卡拉、世麦POS机器的小票格式
                    byte[] bytes = mPrinterSetContents.printBlueTooth_JCXF((Print_JCXF_Bean) mPrintBean);
                    //发送小票格式数据开始打印
                    mBlueToothActivity.send(bytes, MyApplication.JCXF_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                }
                break;
            //商品消费
            case "SPXF":
                //世麦POS机器
                if (IS_BASEWIN_POS_DEVICE) {
                    //配置【快速消费】世麦POS机器的小票格式与数据值
                    mPrinterSetContents.printBaseWinPOS_SPXF((Print_SPXF_Bean) mPrintBean);
                    //开始打印
                    new Thread(print_BASEWINPOS_runnable).start();
                }
                //拉卡拉POS机器
                else if (IS_LAKALA_POS_DEVICE) {
                    //判断拉卡拉打印机状态是否正常
                    if (getPrintState()) {
                        //开始打印
                        new Thread(print_LKL_runnable).start();
                    }
                }
                //手机设备 或 桑米POS机器调用蓝牙打印
                else {
                    //配置【快速消费】非拉卡拉、世麦POS机器的小票格式
                    byte[] bytes = mPrinterSetContents.printBlueTooth_SPXF((Print_SPXF_Bean) mPrintBean);
                    //发送小票格式数据开始打印
                    mBlueToothActivity.send(bytes, MyApplication.SPXF_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                }
                break;

                //限时消费
            case "XSXF":

                byte[] bytes = mPrinterSetContents.printBlueTooth_XSXF((XSXF_Success_Bean) mPrintBean);
                //发送小票格式数据开始打印
                mBlueToothActivity.send(bytes, MyApplication.XSXF_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                break;
            //积分兑换
            case "JFDH":
                //世麦POS机器
                if (IS_BASEWIN_POS_DEVICE) {
                    //配置【快速消费】世麦POS机器的小票格式与数据值
                    mPrinterSetContents.printBaseWinPOS_JFDH((Print_JFDH_Bean) mPrintBean);
                    //开始打印
                    new Thread(print_BASEWINPOS_runnable).start();
                }
                //拉卡拉POS机器
                else if (IS_LAKALA_POS_DEVICE) {
                    //判断拉卡拉打印机状态是否正常
                    if (getPrintState()) {
                        //开始打印
                        new Thread(print_LKL_runnable).start();
                    }
                }
                //手机设备 或 桑米POS机器调用蓝牙打印
                else {
                    //配置【快速消费】非拉卡拉、世麦POS机器的小票格式
                     bytes = mPrinterSetContents.printBlueTooth_JFDH((Print_JFDH_Bean) mPrintBean);
                    //发送小票格式数据开始打印
                    mBlueToothActivity.send(bytes, MyApplication.JFDH_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                }
                break;

            case "HYKK":

                bytes = mPrinterSetContents.printBlueTooth_HYKK((Print_HYKK_Bean) mPrintBean);
                //发送小票格式数据开始打印
                mBlueToothActivity.send(bytes, MyApplication.HYKK_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                break;

            case "RK":
                bytes = mPrinterSetContents.printBlueTooth_RK((RK_Success_Bean) mPrintBean);
                //发送小票格式数据开始打印
                mBlueToothActivity.send(bytes, MyApplication.RK_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                break;
            case "CK":
                bytes = mPrinterSetContents.printBlueTooth_CK((CK_Success_Bean) mPrintBean);
                //发送小票格式数据开始打印
                mBlueToothActivity.send(bytes, MyApplication.CK_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                break;

            case "JB":
                bytes = mPrinterSetContents.printBlueTooth_JB((JB_Success_Bean) mPrintBean);
                //发送小票格式数据开始打印
                mBlueToothActivity.send(bytes, MyApplication.JB_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                break;

            case "SPTH":
                bytes = mPrinterSetContents.printBlueTooth_SPTH((Print_SPTH_Bean) mPrintBean);
                //发送小票格式数据开始打印
                mBlueToothActivity.send(bytes, MyApplication.SPTH_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                break;

            case "YJJY":
                bytes = mPrinterSetContents.printBlueTooth_YJJY((Print_YJJY_Bean) mPrintBean);
                //发送小票格式数据开始打印
                mBlueToothActivity.send(bytes, MyApplication.YJJY_PRINT_TIMES, mContext.getSharedPreferences("bluetooth_address", 0));
                break;

        }
    }

    /**
     * 世麦POS机器的打印小票线程
     */
    private Runnable print_BASEWINPOS_runnable = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < mReceitsNum; i++) {
                try {
                    //设置底部空3行
                    ServiceManager.getInstence().getPrinter().printBottomFeedLine(3);

                    Bitmap qr = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.abc_ic_go);
                    Bitmap[] pBitmaps = new Bitmap[]{Bitmap.createScaledBitmap(qr, 240, 240, true)};
                    ServiceManager.getInstence().getPrinter().print(mPrinterSetContents.pPrintJsonStr_KS, pBitmaps, printer_callback);

                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * 拉卡拉POS机器的打印小票线程
     */
    private Runnable print_LKL_runnable = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < mReceitsNum; i++) {
                try {
                    print4LKL();
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * 拉卡拉POS机
     */
    @SuppressWarnings("serial")
    public void print4LKL() {
        try {
            if (printerDev != null) {
                printerDev.printText(new ArrayList<PrintItemObj>() {
                    {
                        //配置拉卡拉POS机器打印小票的格式
                        switch (mIsConsumeName) {
                            //快速消费
                            case "KSXF":
                                mPrinterSetContents.printLKL_KSXF((Print_KSXF_Bean) mPrintBean);
                                break;
                            //计次消费
                            case "JCXF":
                                mPrinterSetContents.printLKL_JCXF((Print_JCXF_Bean) mPrintBean);
                                break;
                            //会员充值
                            case "HYCZ":
                                mPrinterSetContents.printLKL_HYCZ((Print_HYCZ_Bean) mPrintBean);
                                break;
                            //会员充次
                            case "HYCC":
                                mPrinterSetContents.printLKL_HYCC((Print_HYCC_Bean) mPrintBean);
                                break;
                            //商品消费
                            case "SPXF":
                                mPrinterSetContents.printLKL_SPXF((Print_SPXF_Bean) mPrintBean);
                                break;
                            //积分兑换
                            case "JFDH":
                                mPrinterSetContents.printLKL_JFDH((Print_JFDH_Bean) mPrintBean);
                                break;

                        }

                        //打印
                        add(new PrintItemObj("欢迎光临", 8, true, PrintItemObj.ALIGN.CENTER));
                        add(new PrintItemObj(mPrinterSetContents.pDetails, 8, false, PrintItemObj.ALIGN.LEFT));
                        add(new PrintItemObj("谢谢惠顾！\n\n\n", 8, true, PrintItemObj.ALIGN.CENTER, false, true, 1000));
                    }
                }, new AidlPrinterListener.Stub() {
                    @Override
                    public void onPrintFinish() throws RemoteException {
                        Log.d("TAG", "打印完成");
                    }

                    @Override
                    public void onError(int arg0) throws RemoteException {
                        Log.d("TAG", "打印出错，错误码为：" + arg0);
                    }
                });
            } else {
                Log.d("TAG", "打印机对象为空");
                return;
            }

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("TAG", "打印异常");
        }
    }

    /**
     * 世麦POS机打印小票的回调
     */
    class PrinterListener implements OnPrinterListener {
        @SuppressLint("WrongConstant")
        @Override
        public void onError(int i, String s) {
            CustomToast.makeText(mContext, "打印出错" + i + " detail = " + s + "\n", Toast.LENGTH_SHORT).show();
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onFinish() {
            CustomToast.makeText(mContext, "打印完成！", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStart() {
            //            CustomToast.makeText(mPayConfirmActivity, "开始打印！",Toast.LENGTH_SHORT).show();
        }
    }

}
