package com.zhiluo.android.yunpu.print.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.basewin.services.ServiceManager;
import com.google.gson.Gson;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.print.bean.CK_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.JB_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_HYCC_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_HYCZ_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_HYKK_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_JCXF_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_JFDH_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_KSXF_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_SPTH_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_SPXF_Bean;
import com.zhiluo.android.yunpu.print.bean.Print_YJJY_Bean;
import com.zhiluo.android.yunpu.print.bean.RK_Success_Bean;
import com.zhiluo.android.yunpu.print.bean.XSXF_Success_Bean;
import com.zhiluo.android.yunpu.print.interfaces.IPrinterSetContents;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.utils.DoubleMathUtil;
import com.zhiluo.android.yunpu.utils.ESCUtil;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;
import com.zhiluo.android.yunpu.yslutils.DataUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.zhiluo.android.yunpu.consume.activity.PayConfirmActivity.mGoodsBeanList;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 设置小票打印内容和格式
 * iauthor：Yc
 * date: 2017/7/26 11:17
 * email：jasoncheng9111@gmail.com
 */
public class PrinterSetContentsImpl implements IPrinterSetContents {
    private JSONObject printJson = new JSONObject();
    private Context mContext;
    public String pPrintJsonStr_KS;
    public Bitmap[] pBitmaps;
    public String pDetails; //打印详细内容
    private SimpleDateFormat mConsumeTime;//消费时间
    public static String mLine = "--------------------------------";
    public static String bank = " ";
    private int mQueneNum;//排队


    /**********设置蓝牙打印属性**********/
    private byte[] boldOn = ESCUtil.boldOn();//加粗
    private byte[] boldOff = ESCUtil.boldOff();//不加粗
    private byte[] center = ESCUtil.alignCenter();//居中
    private byte[] text_four = ESCUtil.fontSizeSetBig(4);//字体放大
    private byte[] titlebigger = ESCUtil.fontSizeSetBig(2);//字体放大
    private byte[] titlesmall = ESCUtil.fontSizeSetBig(1);//缩小
    private byte[] mtitlesmall = ESCUtil.fontSizeSetBig(0);//缩小

    private byte[] left = ESCUtil.alignLeft();//居左
    private byte[] right = ESCUtil.alignRight();//居右
    private byte[] nextLine1 = ESCUtil.nextLine(1);//空1行
    private byte[] nextLine2 = ESCUtil.nextLine(2);//空2行
    private byte[] nextLine3 = ESCUtil.nextLine(3);//空3行
    private byte[] nextLine4 = ESCUtil.nextLine(4);//空4行
    private byte[] nextLine6 = ESCUtil.nextLine(6);//空4行
    private byte[] breakPartial = ESCUtil.feedPaperCutPartial();//结束


    public PrinterSetContentsImpl(Context context) {
        this.mContext = context;
        mConsumeTime = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
    }


    /**
     * 蓝牙打印 快速消费
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_KSXF(Print_KSXF_Bean printBean) {
        Li("--------------------------------蓝牙打印 快速消费--random:" + new Gson().toJson(printBean));
        try {
            List<byte[]> list = new ArrayList<>();
            String discount;
            if (printBean.getData().getDiscount() == 1 || printBean.getData().getDiscount() == 0) {
                discount = "不打折";
            } else {
                discount = String.valueOf(printBean.getData().getDiscount());
            }
            if (!MyApplication.mFastConsumeMap.isEmpty()) {
                if (MyApplication.mFastConsumeMap.containsKey("LOGO") && MyApplication.KSXF_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.KSXF_LOGO);
                    File file = (File) CacheData.restoreObject("KSXF_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mFastConsumeMap.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mFastConsumeMap.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);

                }
                if (MyApplication.mFastConsumeMap.containsKey("收银员")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));
                    list.add(titlesmall);
                }
                if (MyApplication.mFastConsumeMap.containsKey("结账日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));
                    list.add(titlesmall);
                }
                if (MyApplication.mFastConsumeMap.containsKey("流水单号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));
                    list.add(titlesmall);
                }
                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("订单金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(printBean.getData().getConsumeTotal())).getBytes("gb2312"));

                if (MyApplication.mFastConsumeMap.containsKey("优惠金额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("优惠金额: " + Decima2KeeplUtil.stringToDecimal(printBean.getData().getDiscountAmount())).getBytes("gb2312"));
                }

                if (MyApplication.mFastConsumeMap.containsKey("赠送积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("赠送积分: " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData()
                            .getIntegralAdd()))).getBytes("gb2312"));
                }

//                list.add(nextLine1);
//                list.add(left);
//                list.add(("会员折扣: " + discount).getBytes("gb2312"));
//                list.add(nextLine1);
//                list.add(left);
//                list.add(("折后金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData()
//                        .getYSMoney()))).getBytes("gb2312"));

//                String str1 = printBean.getData().getActivityName() == null ? "无" : printBean.getData().getActivityName() + "";
//                if (MyApplication.mFastConsumeMap.containsKey("优惠活动")) {
//                    list.add(nextLine1);
//                    list.add(left);
//                    list.add(("优惠活动: " + str1).getBytes("gb2312"));
//                }

//                if (MyApplication.mFastConsumeMap.containsKey("优惠金额")) {
//                    list.add(nextLine1);
//                    list.add(left);
//                    list.add(("优惠金额: " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getConsumeMoney() - printBean.getData()
//                            .getYSMoney()
//                    ))).getBytes("gb2312"));
//                }

                if (MyApplication.mFastConsumeMap.containsKey("优惠详情")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("优惠详情: " + printBean.getData().getDiscountAmountDetail()).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("应    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("实    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("支付详情: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getPayInfo().getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("找    零: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                String str = printBean.getData().getRemark() == null ? "无" : printBean.getData().getRemark();
                if (MyApplication.mFastConsumeMap.containsKey("备注")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("备    注: " + str).getBytes("gb2312"));
                }
                String str2;
                if (printBean.getData().getEMName() == null || printBean.getData().getEMName().equals("")) {
                    str2 = "无";
                } else {
                    str2 = printBean.getData().getEMName();
                }
                if (MyApplication.mFastConsumeMap.containsKey("服务员工")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("服务员工: " + str2).getBytes("gb2312"));
                }
                if (MyApplication.mFastConsumeMap.containsKey("会员卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员卡号: " + printBean.getData().getVCH_Card()).getBytes("gb2312"));
                }

                String facenum = printBean.getData().getVIP_FaceNumber() == null ? "无" : printBean.getData().getVIP_FaceNumber();
                if (facenum.equals("")) {
                    facenum = "无";
                }
                if (MyApplication.mFastConsumeMap.containsKey("卡面卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡面卡号: " + (facenum.equals("") ? "无" : facenum)).getBytes("gb2312"));
                }
                String memName = printBean.getData().getVIP_Name();
                if (memName == null || memName.equals("")) {
                    memName = "无";
                }
                if (MyApplication.mFastConsumeMap.containsKey("会员姓名")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员姓名: " + memName).getBytes("gb2312"));
                }
                if (MyApplication.mFastConsumeMap.containsKey("卡内余额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内余额: " + "￥" + printBean.getData().getVCH_Money()).getBytes("gb2312"));
                }
                if (MyApplication.mFastConsumeMap.containsKey("卡内积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内积分: " + printBean.getData().getVCH_Point()).getBytes("gb2312"));
                }
                if (MyApplication.mFastConsumeMap.containsKey("会员地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员地址: " + (printBean.getData().getVIPAddress() == null ? "" : printBean.getData().getVIPAddress())).getBytes("gb2312"));
                }
                if (MyApplication.mFastConsumeMap.containsKey("打印时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));
                }
                if (MyApplication.mFastConsumeMap.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mFastConsumeMap.get("商户电话")).getBytes("gb2312"));
                }
                if (MyApplication.mFastConsumeMap.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mFastConsumeMap.get("联系地址")).getBytes("gb2312"));
                }
                if (MyApplication.mFastConsumeMap.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mFastConsumeMap.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }
                if (MyApplication.mFastConsumeMap.containsKey("二维码") && MyApplication.KSXF_QR != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.KSXF_QR);
                    File file = (File) CacheData.restoreObject("KSXF_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                    list.add(center);
                }
                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(titlebigger);
                list.add("欢迎光临".getBytes("gb2312"));
                list.add(titlesmall);


                list.add(nextLine2);
                list.add(left);
                list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("消费金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getConsumeMoney())))
                        .getBytes("gb2312"));

//                list.add(nextLine1);
//                list.add(left);
//                list.add(("会员折扣: " + discount).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("折后金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData()
                        .getYSMoney()))).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("应    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("实    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("支付详情: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getPayInfo().getBytes("gb2312"));//


                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("找    零: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));

                list.add(nextLine2);
                list.add(center);
                list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }
            return ESCUtil.byteMerger(list);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 世麦POS机器  快速消费
     *
     * @param printBean
     */
    @Override
    public void printBaseWinPOS_KSXF(Print_KSXF_Bean printBean) {
        String detial =
                "==============================" + "\n" +
                        "快速消费小票" + "\n" +
                        "消费单号：" + printBean.getData().getOrderCode() + "\n" +
                        "会员编号：" + printBean.getData().getVCH_Card() + "\n" +
                        "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                        "账户余额：" + printBean.getData().getVCH_Money() + "\n" +
                        "==============================" + "\n" +
                        "消费金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                        "折后金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                        "获得积分：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getIntegralAdd())) + "\n" +
                        "==============================" + "\n" +
                        "应付金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                        "实付金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney())) + "\n" +
                        "支付详情：" + printBean.getData().getPayInfo() + "\n" +
                        "找零金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney())) + "\n" +
                        "操作人员：" + printBean.getData().getCashier() + "\n" +
                        "消费时间：" + mConsumeTime.format(new Date()) + "\n" +
                        "客户签名：\n\n\n";

        // 組打印json字符串
        JSONArray printTest = new JSONArray();

        // add text printer
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        JSONObject json3 = new JSONObject();
        try {
            //头部
            json1.put("content-type", "txt");
            json1.put("content", "欢迎光临");
            json1.put("size", "2");
            json1.put("position", "center");
            json1.put("offset", "0");
            json1.put("bold", "2");
            json1.put("italic", "0");
            json1.put("height", "-1");
            //正文
            json2.put("content-type", "txt");
            json2.put("content", detial);
            json2.put("size", "2");
            json2.put("position", "left");
            json2.put("offset", "0");
            json2.put("bold", "0");
            json2.put("italic", "0");
            json2.put("height", "-1");
            //尾部
            json3.put("content-type", "txt");
            json3.put("content", "谢谢惠顾！");
            json3.put("size", "2");
            json3.put("position", "center");
            json3.put("offset", "0");
            json3.put("bold", "2");
            json3.put("italic", "0");
            json3.put("height", "-1");
            printTest.put(json1);
            printTest.put(json2);
            printTest.put(json3);
            printJson.put("spos", printTest);

            //将打印的信息保存在变量中
            pPrintJsonStr_KS = printJson.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拉卡拉pos机器 快速消费
     *
     * @param printBean
     */
    @Override
    public void printLKL_KSXF(Print_KSXF_Bean printBean) {
        pDetails =
                "==============================" + "\n" +
                        "快速消费小票" + "\n" +
                        "消费单号：" + printBean.getData().getOrderCode() + "\n" +
                        "会员编号：" + printBean.getData().getVCH_Card() + "\n" +
                        "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                        "账户余额：" + printBean.getData().getVCH_Money() + "\n" +
                        "==============================" + "\n" +
                        "消费金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                        "折后金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                        "获得积分：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getIntegralAdd())) + "\n" +
                        "==============================" + "\n" +
                        "应付金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                        "实付金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney())) + "\n" +
                        "支付详情：" + printBean.getData().getPayInfo() + "\n" +
                        "找零金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney())) + "\n" +
                        "操作人员：" + printBean.getData().getCashier() + "\n" +
                        "消费时间：" + mConsumeTime.format(new Date()) + "\n" +
                        "客户签名：\n\n\n";
    }

    /**
     * 蓝牙打印 商品消费
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_SPXF(Print_SPXF_Bean printBean) {
        try {
            List<Print_SPXF_Bean.DataBean.GoodsListBean> goodList = printBean.getData().getGoodsList();
            List<byte[]> list = new ArrayList<>();

            Li("--------------------------------蓝牙打印 商品消费--random:" + new Gson().toJson(printBean));
            if (!MyApplication.mGoodsConsumeMap.isEmpty()) {
                if (MyApplication.mGoodsConsumeMap.containsKey("LOGO") && MyApplication.SPXF_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.SPXF_LOGO);
                    File file = (File) CacheData.restoreObject("SPXF_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(titlesmall);
                    list.add(text_four);
                    list.add((MyApplication.mGoodsConsumeMap.get("标题").getBytes("gb2312")));
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(titlesmall);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("收银员")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(boldOff);
                    list.add(titlesmall);
                    list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("结账日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(boldOff);
                    list.add(titlesmall);
                    list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("流水单号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(boldOff);
                    list.add(titlesmall);
                    list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));
                }
                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                String kongge = "";
                int length = 7;
                if (!MyApplication.mGoodsConsumeMap.containsKey("原价") && !MyApplication.mGoodsConsumeMap.containsKey("折扣")) {
                    length = 11;
                    kongge = "     ";
                } else if (!MyApplication.mGoodsConsumeMap.containsKey("原价") || !MyApplication.mGoodsConsumeMap.containsKey("折扣")) {
                    length = 9;
                    kongge = " ";
                } else {
                    length = 7;
                    kongge = "";
                }

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add(("商品  " + kongge + bank).getBytes("gb2312"));
                list.add(("单价").getBytes("gb2312"));
                if (MyApplication.mGoodsConsumeMap.containsKey("原价")) {
                    list.add(("/原价").getBytes("gb2312"));
                }
                list.add((bank).getBytes("gb2312"));
                if (MyApplication.mGoodsConsumeMap.containsKey("折扣")) {
                    list.add(("折扣" + bank).getBytes("gb2312"));
                }
                list.add(("数量" + bank).getBytes("gb2312"));
                list.add("小计".getBytes("gb2312"));
                list.add(boldOff);
                list.add(nextLine1);

                double total = 0;
                for (int i = 0; i < goodList.size(); i++) {
                    String siglePrice = Decima2KeeplUtil.stringToDecimal(goodList.get(i).getGOD_DiscountPrice() / goodList.get(i).getPM_Number() + "");
                    double price = goodList.get(i).getPM_UnitPrice();
                    double num = goodList.get(i).getPM_Number();
                    total += DoubleMathUtil.mul(price, num);
                    list.add(left);
                    list.add(goodList.get(i).getPM_Name().getBytes("gb2312"));
                    if (MyApplication.mGoodsConsumeMap.containsKey("规格")) {
                        list.add((bank + goodList.get(i).getPM_Modle()).getBytes("gb2312"));
                    }
                    list.add(nextLine1);

                    String pmcode = goodList.get(i).getPM_Code();
                    String pmcode1 = null;
                    String pmcode2 = null;

                    if (pmcode.length() > length) {
                        pmcode1 = pmcode.substring(0, length - 1);
                        pmcode2 = pmcode.substring(length - 1, pmcode.length());
                    } else {
                        pmcode1 = pmcode;
                        while (pmcode1.length() < length - 2) {
                            pmcode1 = pmcode1 + bank;
                        }
                    }
                    if (MyApplication.mGoodsConsumeMap.containsKey("商品编码")) {
                        list.add(left);
                        list.add(pmcode1.getBytes("gb2312"));
                        list.add((bank + siglePrice).getBytes("gb2312"));
                    } else {
                        list.add(("      " + kongge + bank + siglePrice).getBytes("gb2312"));

                    }
                    if (MyApplication.mGoodsConsumeMap.containsKey("原价")) {
//                        list.add(("/" + price).getBytes("gb2312"));
                        list.add(("/" + goodList.get(i).getPM_OriginalPrice()).getBytes("gb2312"));
                    }
                    if (MyApplication.mGoodsConsumeMap.containsKey("折扣")) {
                        list.add((bank + goodList.get(i).getPM_Discount()).getBytes("gb2312"));
                    }
                    list.add((bank + goodList.get(i).getPM_Number()).getBytes("gb2312"));
                    list.add((bank + Decima2KeeplUtil.stringToDecimal(goodList.get(i).getGOD_DiscountPrice() + "")).getBytes("gb2312"));
                    list.add(nextLine1);
                    if (MyApplication.mGoodsConsumeMap.containsKey("商品编码")) {
                        if (pmcode.length() > length) {
                            list.add(pmcode2.getBytes("gb2312"));
                            list.add(nextLine1);
                        }
                    }
                }

                list.add(left);
                list.add(mLine.getBytes("gb2312"));

//                String str1 = printBean.getData().getActivityName() == null ? "无" : printBean.getData().getActivityName() + "";
//                if (MyApplication.mGoodsConsumeMap.containsKey("优惠活动")) {
//                    list.add(nextLine1);
//                    list.add(left);
//                    list.add(("优惠活动: " + str1).getBytes("gb2312"));
//                }

                list.add(nextLine1);
                list.add(left);
                list.add(("订单金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(printBean.getData().getConsumeTotal())).getBytes("gb2312"));

                if (MyApplication.mGoodsConsumeMap.containsKey("优惠金额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("优惠金额: " + Decima2KeeplUtil.stringToDecimal(printBean.getData().getDiscountAmount())).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsConsumeMap.containsKey("赠送积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("赠送积分: " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData()
                            .getIntegralAdd()))).getBytes("gb2312"));
                }

                if (MyApplication.mFastConsumeMap.containsKey("优惠详情")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("优惠详情: " + printBean.getData().getDiscountAmountDetail()).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("应    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("实    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("支付详情: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getPayInfo().getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("找    零: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                String str = printBean.getData().getRemark() == null ? "无" : printBean.getData().getRemark();
                if (MyApplication.mGoodsConsumeMap.containsKey("备注")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("备    注: " + str).getBytes("gb2312"));
                }

                String str2;
                if (printBean.getData().getEMName() == null || printBean.getData().getEMName().equals("")) {
                    str2 = "无";
                } else {
                    str2 = printBean.getData().getEMName();
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("服务员工")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("服务员工: " + str2).getBytes("gb2312"));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("会员卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员卡号: " + printBean.getData().getVCH_Card()).getBytes("gb2312"));
                }
                String facenum = printBean.getData().getVIP_FaceNumber() == null ? "无" : printBean.getData().getVIP_FaceNumber();
                if (facenum.equals("")) {
                    facenum = "无";
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("卡面卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡面卡号: " + facenum).getBytes("gb2312"));
                }
                String memName = printBean.getData().getVIP_Name();
                if (memName == null || memName.equals("")) {
                    memName = "无";
                }

                if (MyApplication.mGoodsConsumeMap.containsKey("会员姓名")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员姓名: " + memName).getBytes("gb2312"));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("卡内余额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内余额: " + "￥" + printBean.getData().getVCH_Money()).getBytes("gb2312"));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("卡内积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内积分: " + printBean.getData().getVCH_Point()).getBytes("gb2312"));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("会员地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员地址: " + (printBean.getData().getVIPAddress() == null ? "" : printBean.getData().getVIPAddress())).getBytes("gb2312"));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("打印时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(boldOff);
                    list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mGoodsConsumeMap.get("商户电话")).getBytes("gb2312"));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mGoodsConsumeMap.get("联系地址")).getBytes("gb2312"));
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mGoodsConsumeMap.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }

                if (MyApplication.mGoodsConsumeMap.containsKey("二维码") && MyApplication.SPXF_QR != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.SPXF_QR);
                    File file = (File) CacheData.restoreObject("SPXF_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(titlebigger);
                list.add("欢迎光临".getBytes("gb2312"));
                list.add(titlesmall);

                list.add(nextLine2);
                list.add(left);
                list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(boldOn);
                list.add(("商品名称" + bank).getBytes("gb2312"));
                list.add(("单价" + bank).getBytes("gb2312"));
                list.add(("数量" + bank).getBytes("gb2312"));
                list.add(("折扣" + bank).getBytes("gb2312"));
                list.add("小计".getBytes("gb2312"));
                list.add(boldOff);
                list.add(nextLine1);

                for (int i = 0; i < goodList.size(); i++) {
                    double price = goodList.get(i).getPM_UnitPrice();
                    double num = goodList.get(i).getPM_Number();
                    double discount = goodList.get(i).getPM_Discount();
                    double total = DoubleMathUtil.mul(DoubleMathUtil.mul(price, num), discount);
                    list.add(left);
                    list.add(goodList.get(i).getPM_Name().getBytes("gb2312"));
                    list.add(nextLine1);
                    list.add((bank + "       ￥" + goodList.get(i).getPM_UnitPrice()).getBytes("gb2312"));
                    list.add((bank + goodList.get(i).getPM_Number()).getBytes("gb2312"));
                    list.add((bank + " " + goodList.get(i).getPM_Discount()).getBytes("gb2312"));
                    list.add((bank + "￥" + Decima2KeeplUtil.stringToDecimal(goodList.get(i).getGOD_DiscountPrice() + "")).getBytes("gb2312"));
                    list.add(nextLine1);
                }
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("应    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("实    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("支付详情: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getPayInfo().getBytes("gb2312"));//


                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("找    零: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));

                list.add(nextLine2);
                list.add(center);
                list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }
            return ESCUtil.byteMerger(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 世麦pos机器
     *
     * @param printBean
     */
    @Override
    public void printBaseWinPOS_SPXF(Print_SPXF_Bean printBean) {
        String head = "==============================" + "\n" +
                "商品消费小票" + "\n" +
                "消费单号：" + printBean.getData().getOrderCode() + "\n" +
                "会员编号：" + printBean.getData().getVCH_Card() + "\n" +
                "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                "账户余额：" + printBean.getData().getVCH_Money() + "\n";

        String contents = "==============================" + "\n";
        for (int i = 0; i < mGoodsBeanList.size(); i++) {
            if (mGoodsBeanList.get(i).getNum() == 0) {
                continue;
            }

            contents = contents +
                    "商品名称：" + mGoodsBeanList.get(i).getPM_Name() + "\n" +
                    "商品数量：" + mGoodsBeanList.get(i).getNum() + "\n" +
                    "商品单价：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(mGoodsBeanList.get(i).getPM_UnitPrice())) + "\n" +
                    "共计：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(mGoodsBeanList.get(i).getPM_UnitPrice() * mGoodsBeanList.get(i).getNum())) + "\n" +
                    "==============================" + "\n";

        }

        String bottom = "订单总额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                "折后金额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                "找零金额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney())) + "\n" +
                "支付详情：" + printBean.getData().getPayInfo() + "\n" +
                "获得积分：" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getIntegralAdd())) + "\n" +
                "操作人员：" + printBean.getData().getCashier() + "\n" +
                "消费时间：" + mConsumeTime.format(new Date()) + "\n" +
                "客户签名：" + "\n\n\n";

        // 組打印json字符串
        JSONArray printTest = new JSONArray();
        // 添加文本打印,正常
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        JSONObject json3 = new JSONObject();
        JSONObject json4 = new JSONObject();
        JSONObject json5 = new JSONObject();
        try {
            //店铺名称
            json1.put("content-type", "txt");
            json1.put("content", "欢迎光临");
            json1.put("size", "2");
            json1.put("position", "center");
            json1.put("offset", "0");
            json1.put("bold", "1");
            json1.put("italic", "0");
            json1.put("height", "-1");
            //头部
            json2.put("content-type", "txt");
            json2.put("content", head);
            json2.put("size", "2");
            json2.put("position", "left");
            json2.put("offset", "0");
            json2.put("bold", "0");
            json2.put("italic", "0");
            json2.put("height", "-1");
            //正文
            json3.put("content-type", "txt");
            json3.put("content", contents);
            json3.put("size", "2");
            json3.put("position", "left");
            json3.put("offset", "0");
            json3.put("bold", "0");
            json3.put("italic", "0");
            json3.put("height", "-1");
            //尾部
            json4.put("content-type", "txt");
            json4.put("content", bottom);
            json4.put("size", "2");
            json4.put("position", "left");
            json4.put("offset", "0");
            json4.put("bold", "0");
            json4.put("italic", "0");
            json4.put("height", "-1");
            //结束
            json5.put("content-type", "txt");
            json5.put("content", "谢谢惠顾！");
            json5.put("size", "2");
            json5.put("position", "center");
            json5.put("offset", "0");
            json5.put("bold", "1");
            json5.put("italic", "0");
            json5.put("height", "-1");
            printTest.put(json1);
            printTest.put(json2);
            printTest.put(json3);
            printTest.put(json4);
            printTest.put(json5);
            printJson.put("spos", printTest);

            // 设置底部空3行
            ServiceManager.getInstence().getPrinter().printBottomFeedLine(3);

            pPrintJsonStr_KS = printJson.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拉卡拉pos 商品消费
     *
     * @param printBean
     */
    @Override
    public void printLKL_SPXF(Print_SPXF_Bean printBean) {
        String head = "==============================" + "\n" +
                "商品消费小票" + "\n" +
                "消费单号：" + printBean.getData().getOrderCode() + "\n" +
                "会员编号：" + printBean.getData().getVCH_Card() + "\n" +
                "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                "账户余额：" + printBean.getData().getVCH_Money() + "\n";
        String contents = "==============================" + "\n";

        for (int i = 0; i < mGoodsBeanList.size(); i++) {
            if (mGoodsBeanList.get(i).getNum() == 0) {
                continue;
            }

            contents = contents +
                    "商品名称：" + mGoodsBeanList.get(i).getPM_Name() + "\n" +
                    "商品数量：" + mGoodsBeanList.get(i).getNum() + "\n" +
                    "商品单价：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(mGoodsBeanList.get(i).getPM_UnitPrice())) + "\n" +
                    "共计：" + Decima2KeeplUtil.stringToDecimal(String.valueOf(mGoodsBeanList.get(i).getPM_UnitPrice() * mGoodsBeanList.get(i).getNum())) + "\n" +
                    "\n" + "==============================" + "\n";

        }

        String bottom = "订单总额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                "折后金额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                "支付详情：" + printBean.getData().getPayInfo() + "\n" +
                "获得积分：" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getIntegralAdd())) + "\n" +
                "操作人员：" + printBean.getData().getCashier() + "\n" +
                "消费时间：" + mConsumeTime.format(new Date()) + "\n" +
                "客户签名：" + "\n\n\n";

        pDetails = head + contents + bottom;
    }

    /**
     * 蓝牙打印 会员充值
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_HYCZ(Print_HYCZ_Bean printBean) {
        Li("--------------------------------蓝牙打印 会员充值--random:" + new Gson().toJson(printBean));
        try {
            List<byte[]> list = new ArrayList<>();

            if (!MyApplication.mRechargeMap.isEmpty()) {
                if (MyApplication.mRechargeMap.containsKey("LOGO") && MyApplication.HYCZ_LOGO != null && !MyApplication.HYCZ_LOGO.equals("")) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.HYCZ_LOGO);
                    File file = (File) CacheData.restoreObject("HYCZ_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mRechargeMap.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mRechargeMap.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);
                }

                if (MyApplication.mRechargeMap.containsKey("收银员")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));
                }
                if (MyApplication.mRechargeMap.containsKey("结账日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));
                }
                if (MyApplication.mRechargeMap.containsKey("流水单号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));
                }
                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("充值金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getConsumeTotal())))
                        .getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("赠送金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getGiveMoney())))
                        .getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("充值合计: " + "￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData()
                        .getRechargeTotal()))).getBytes("gb2312"));

//                String STR = printBean.getData().getActivityName() == null ? "无" : printBean.getData().getActivityName();
//                if (MyApplication.mRechargeMap.containsKey("优惠活动")) {
//                    list.add(nextLine1);
//                    list.add(left);
//                    list.add(("优惠活动: " + STR).getBytes("gb2312"));
//                }

                if (MyApplication.mRechargeMap.containsKey("赠送积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("赠送积分: " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData()
                            .getIntegralAdd()))).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("应    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("实    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("支付详情: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getPayInfo().getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("找    零: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                String str = printBean.getData().getRemark() == null ? "无" : printBean.getData().getRemark();
                if (MyApplication.mRechargeMap.containsKey("备注")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("备    注: " + str).getBytes("gb2312"));
                }

                String str2;
                if (printBean.getData().getEMName() == null || printBean.getData().getEMName().equals("")) {
                    str2 = "无";
                } else {
                    str2 = printBean.getData().getEMName();
                }
                if (MyApplication.mRechargeMap.containsKey("服务员工")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("服务员工: " + str2).getBytes("gb2312"));
                }

                if (MyApplication.mRechargeMap.containsKey("会员卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员卡号: " + printBean.getData().getVCH_Card()).getBytes("gb2312"));
                }
                String facenum = printBean.getData().getVIP_FaceNumber() == null ? "无" : printBean.getData().getVIP_FaceNumber();
                if (facenum.equals("")) {
                    facenum = "无";
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("卡面卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡面卡号: " + facenum).getBytes("gb2312"));
                }
                String memName = printBean.getData().getVIP_Name();
                if (memName == null || memName.equals("")) {
                    memName = "无";
                }
                if (MyApplication.mRechargeMap.containsKey("会员姓名")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员姓名: " + memName).getBytes("gb2312"));
                }
                if (MyApplication.mRechargeMap.containsKey("卡内余额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内余额: " + "￥" + printBean.getData().getVCH_Money()).getBytes("gb2312"));
                }
                if (MyApplication.mRechargeMap.containsKey("卡内积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内积分: " + printBean.getData().getVCH_Point()).getBytes("gb2312"));
                }
                if (MyApplication.mRechargeMap.containsKey("会员地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员地址: " + (printBean.getData().getVIPAddress() == null ? "" : printBean.getData().getVIPAddress())).getBytes("gb2312"));
                }
                if (MyApplication.mRechargeMap.containsKey("打印时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));
                }
                if (MyApplication.mRechargeMap.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mRechargeMap.get("商户电话")).getBytes("gb2312"));
                }
                if (MyApplication.mRechargeMap.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mRechargeMap.get("联系地址")).getBytes("gb2312"));
                }
                if (MyApplication.mRechargeMap.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mRechargeMap.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }

                if (MyApplication.mRechargeMap.containsKey("二维码") && MyApplication.HYCZ_QR != null && !MyApplication.HYCZ_QR.equals("")) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.HYCZ_QR);
                    File file = (File) CacheData.restoreObject("HYCZ_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
//                    list.add(center);
                }
                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(titlebigger);
                list.add("欢迎光临".getBytes("gb2312"));
                list.add(titlesmall);

                list.add(nextLine2);
                list.add(left);
                list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("充值金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getConsumeMoney())))
                        .getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("赠送金额: " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getGiveMoney())))
                        .getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("充值合计: " + "￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData()
                        .getRechargeTotal()))).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("应    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("实    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("支付详情: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getPayInfo().getBytes("gb2312"));//


                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("找    零: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));

                list.add(nextLine2);
                list.add(center);
                list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }
            return ESCUtil.byteMerger(list);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 世麦pos 会员充值
     *
     * @param printBean
     */
    @Override
    public void printBaseWinPOS_HYCZ(Print_HYCZ_Bean printBean) {
        String contents = "==============================" + "\n" +
                "会员充值小票" + "\n" +
                "会员卡号：" + printBean.getData().getVCH_Card() + "\n" +
                "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                "账户余额：" + printBean.getData().getVCH_Money() + "\n" +
                "==============================" + "\n" +
                "充值金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getRechargeTotal())) + "\n" +
                "赠送金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getGiveMoney())) + "\n" +
                "==============================" + "\n" +
                "应付金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                "实付金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney())) + "\n" +
                "支付详情：" + printBean.getData().getPayInfo() + "\n" +
                "找零金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney())) + "\n" +
                "操作人员：" + printBean.getData().getCashier() + "\n" +
                "消费时间：" + mConsumeTime.format(new Date()) + "\n";

        // 組打印json字符串
        JSONArray printTest = new JSONArray();
        // add text printer
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        JSONObject json3 = new JSONObject();
        try {
            //头部
            json1.put("content-type", "txt");
            json1.put("content", "欢迎光临");
            json1.put("size", "2");
            json1.put("position", "center");
            json1.put("offset", "0");
            json1.put("bold", "0");
            json1.put("italic", "0");
            json1.put("height", "-1");
            //正文
            json2.put("content-type", "txt");
            json2.put("content", contents);
            json2.put("size", "2");
            json2.put("position", "left");
            json2.put("offset", "0");
            json2.put("bold", "0");
            json2.put("italic", "0");
            json2.put("height", "-1");
            //尾部
            json3.put("content-type", "txt");
            json3.put("content", "谢谢惠顾！");
            json3.put("size", "2");
            json3.put("position", "center");
            json3.put("offset", "0");
            json3.put("bold", "0");
            json3.put("italic", "0");
            json3.put("height", "-1");
            printTest.put(json1);
            printTest.put(json2);
            printTest.put(json3);
            printJson.put("spos", printTest);

            // 设置底部空3行
            ServiceManager.getInstence().getPrinter().printBottomFeedLine(3);

            //将打印的信息保存在变量中
            pPrintJsonStr_KS = printJson.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拉卡拉打印 会员充值
     *
     * @param printBean
     */
    @Override
    public void printLKL_HYCZ(Print_HYCZ_Bean printBean) {
        pDetails = "==============================" + "\n" +
                "会员充值小票" + "\n" +
                "消费单号：" + printBean.getData().getOrderCode() + "\n" +
                "会员编号：" + printBean.getData().getVCH_Card() + "\n" +
                "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                "账户余额：" + printBean.getData().getVCH_Money() + "\n" +
                "==============================" + "\n" +
                "订单金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                "折后金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                "找零金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney())) + "\n" +
                "获得积分：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getIntegralAdd())) + "\n" +
                "==============================" + "\n" +
                "充值金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getRechargeTotal())) + "\n" +
                "赠送金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getGiveMoney())) + "\n" +
                "==============================" + "\n" +
                "应付金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                "实付金额：￥ " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                "支付详情：" + printBean.getData().getPayInfo() + "\n" +
                "操作人员：" + printBean.getData().getCashier() + "\n" +
                "消费时间：" + mConsumeTime.format(new Date()) + "\n" +
                "客户签名：" + "\n\n\n";
    }

    /**
     * 蓝牙打印  会员充次
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_HYCC(Print_HYCC_Bean printBean) {
        Li("--------------------------------蓝牙打印 会员充次--random:" + new Gson().toJson(printBean));
        try {
            List<Print_HYCC_Bean.DataBean.ServiceListBean> serviceList = printBean.getData().getServiceList();
            List<byte[]> list = new ArrayList<>();

            if (!MyApplication.mTimesRechargeMap.isEmpty()) {
                if (MyApplication.mTimesRechargeMap.containsKey("LOGO") && MyApplication.HYCC_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.HYCC_LOGO);
                    File file = (File) CacheData.restoreObject("HYCC_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mTimesRechargeMap.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mTimesRechargeMap.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);
                }
                if (MyApplication.mTimesRechargeMap.containsKey("收银员")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));
                }
                if (MyApplication.mTimesRechargeMap.containsKey("结账日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));
                }
                if (MyApplication.mTimesRechargeMap.containsKey("流水单号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));
                }
                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                String kongge = "";
                int length = 7;
                if (!MyApplication.mTimesRechargeMap.containsKey("原价") && !MyApplication.mTimesRechargeMap.containsKey("折扣")) {
                    kongge = "     ";
                    length = 11;
                } else if (!MyApplication.mTimesRechargeMap.containsKey("原价") || !MyApplication.mTimesRechargeMap.containsKey("折扣")) {
                    kongge = "  ";
                    length = 9;
                } else {
                    kongge = "";
                    length = 7;
                }

                list.add(nextLine1);
                list.add(boldOn);
                list.add("服务  ".getBytes("gb2312"));
                list.add((kongge + bank).getBytes("gb2312"));
                list.add("单价".getBytes("gb2312"));
                if (MyApplication.mTimesRechargeMap.containsKey("原价")) {
                    list.add("/原价".getBytes("gb2312"));
                }
                list.add(bank.getBytes("gb2312"));
                if (MyApplication.mTimesRechargeMap.containsKey("折扣")) {
                    list.add("折扣".getBytes("gb2312"));
                }
                list.add(bank.getBytes("gb2312"));
                list.add("数量".getBytes("gb2312"));
                list.add(bank.getBytes("gb2312"));
                list.add("小计".getBytes("gb2312"));
                list.add(boldOff);

                list.add(nextLine1);
                double alltatol = 0;
                for (int i = 0; i < serviceList.size(); i++) {
                    double price = serviceList.get(i).getPM_UnitPrice();
                    int num = serviceList.get(i).getPM_Number();
                    double discount = serviceList.get(i).getPM_Discount();
                    String total = Decima2KeeplUtil.stringToDecimal(DoubleMathUtil.mul(DoubleMathUtil.mul(price, num), discount) + "");
                    alltatol += DoubleMathUtil.mul(price, num);
                    list.add(serviceList.get(i).getPM_Name().getBytes("gb2312"));
                    if (MyApplication.mTimesRechargeMap.containsKey("到期时间")) {
                        if (serviceList.get(i).getGOD_ExpireDate() != null) {
                            list.add(("(" + serviceList.get(i).getGOD_ExpireDate() + "到期)").getBytes("gb2312"));
                        } else {
                            list.add(("(永久有效)").getBytes("gb2312"));
                        }
                    }
                    list.add(nextLine1);

                    String siglePrice = Decima2KeeplUtil.stringToDecimal(serviceList.get(i).getGOD_DiscountPrice() / serviceList.get(i).getPM_Number() + "");
                    String pmcode = serviceList.get(i).getPM_Code();
                    String pmcode1 = null;
                    String pmcode2 = null;
                    if (pmcode.length() > length) {
                        pmcode1 = pmcode.substring(0, length - 1);
                        pmcode2 = pmcode.substring(length - 1, pmcode.length());
                    } else {
                        pmcode1 = pmcode;
                        while (pmcode1.length() < length - 1) {
                            pmcode1 = pmcode1 + bank;
                        }
                    }
                    if (MyApplication.mTimesRechargeMap.containsKey("商品编码")) {
                        list.add(pmcode1.getBytes("gb2312"));
//                        list.add((bank + siglePrice).getBytes("gb2312"));
                        list.add((bank + serviceList.get(i).getPM_UnitPrice()).getBytes("gb2312"));
                    } else {
//                        list.add(("      " + bank + kongge + siglePrice).getBytes("gb2312"));
                        list.add(("      " + bank + kongge + serviceList.get(i).getPM_UnitPrice()).getBytes("gb2312"));
                    }
                    if (MyApplication.mTimesRechargeMap.containsKey("原价")) {
//                        list.add(("/" + serviceList.get(i).getPM_UnitPrice()).getBytes("gb2312"));
                        list.add(("/" + serviceList.get(i).getPM_OriginalPrice()).getBytes("gb2312"));
                    }
                    if (MyApplication.mTimesRechargeMap.containsKey("折扣")) {
                        list.add((bank + serviceList.get(i).getPM_Discount()).getBytes("gb2312"));
                    }
                    list.add((bank + serviceList.get(i).getPM_Number()).getBytes("gb2312"));
//                    list.add((bank + total).getBytes("gb2312"));
                    list.add((bank + serviceList.get(i).getGOD_DiscountPrice()).getBytes("gb2312"));
                    list.add(nextLine1);
                    if (MyApplication.mTimesRechargeMap.containsKey("商品编码")) {
                        if (pmcode.length() > length) {
                            list.add(pmcode2.getBytes("gb2312"));
                            list.add(nextLine1);
                        }
                    }

                }
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("订单金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(printBean.getData().getConsumeTotal())).getBytes("gb2312"));

                if (MyApplication.mTimesRechargeMap.containsKey("赠送积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("赠送积分: " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData()
                            .getIntegralAdd()))).getBytes("gb2312"));
                }

                if (MyApplication.mFastConsumeMap.containsKey("优惠金额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("优惠金额: " + Decima2KeeplUtil.stringToDecimal(printBean.getData().getDiscountAmount())).getBytes("gb2312"));
                }

                if (MyApplication.mFastConsumeMap.containsKey("优惠详情")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("优惠详情: " + printBean.getData().getDiscountAmountDetail()).getBytes("gb2312"));
                }

//                String str1 = printBean.getData().getActivityName() == null ? "无" : printBean.getData().getActivityName() + "";
//                if (MyApplication.mTimesRechargeMap.containsKey("优惠活动")) {
//                    list.add(nextLine1);
//                    list.add(left);
//                    list.add(("优惠活动: " + str1).getBytes("gb2312"));
//                }


//                if (MyApplication.mTimesRechargeMap.containsKey("优惠金额")) {
//                    list.add(nextLine1);
//                    list.add(left);
//                    list.add(("优惠金额: " + Decima2KeeplUtil.stringToDecimal(String.valueOf(alltatol - printBean.getData().getYSMoney()))).getBytes("gb2312"));
//                }

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("应    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("实    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("支付详情: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getPayInfo().getBytes("gb2312"));//


                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("找    零: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                String str = printBean.getData().getRemark() == null ? "无" : printBean.getData().getRemark();
                if (MyApplication.mTimesRechargeMap.containsKey("备注")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("备    注: " + str).getBytes("gb2312"));
                }

                String str2;
                if (printBean.getData().getEMName() == null || printBean.getData().getEMName().equals("")) {
                    str2 = "无";
                } else {
                    str2 = printBean.getData().getEMName();
                }
                if (MyApplication.mTimesRechargeMap.containsKey("服务员工")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("服务员工: " + str2).getBytes("gb2312"));
                }

                if (MyApplication.mTimesRechargeMap.containsKey("会员卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员卡号: " + printBean.getData().getVCH_Card()).getBytes("gb2312"));
                }
                String facenum = printBean.getData().getVIP_FaceNumber() == null ? "无" : printBean.getData().getVIP_FaceNumber();
                if (facenum.equals("")) {
                    facenum = "无";
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("卡面卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡面卡号: " + facenum).getBytes("gb2312"));
                }
                String memName = printBean.getData().getVIP_Name();
                if (memName == null || memName.equals("")) {
                    memName = "无";
                }
                if (MyApplication.mTimesRechargeMap.containsKey("会员姓名")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员姓名: " + memName).getBytes("gb2312"));
                }
                if (MyApplication.mTimesRechargeMap.containsKey("卡内余额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内余额: " + Decima2KeeplUtil.stringToDecimal(printBean.getData().getVCH_Money() + "")).getBytes("gb2312"));
                }
                if (MyApplication.mTimesRechargeMap.containsKey("卡内积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内积分: " + printBean.getData().getVCH_Point()).getBytes("gb2312"));
                }
                if (MyApplication.mTimesRechargeMap.containsKey("会员地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员地址: " + (printBean.getData().getVIPAddress() == null ? "" : printBean.getData().getVIPAddress())).getBytes("gb2312"));
                }
                if (MyApplication.mTimesRechargeMap.containsKey("打印时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));
                }
                if (MyApplication.mTimesRechargeMap.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mTimesRechargeMap.get("商户电话")).getBytes("gb2312"));
                }
                if (MyApplication.mTimesRechargeMap.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mTimesRechargeMap.get("联系地址")).getBytes("gb2312"));
                }
                if (MyApplication.mTimesRechargeMap.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mTimesRechargeMap.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }
                if (MyApplication.mTimesRechargeMap.containsKey("二维码") && MyApplication.HYCC_QR != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.HYCC_QR);
                    File file = (File) CacheData.restoreObject("HYCC_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(titlebigger);
                list.add("欢迎光临".getBytes("gb2312"));
                list.add(titlesmall);

                list.add(nextLine2);
                list.add(left);
                list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));


                list.add(nextLine1);
                list.add(boldOn);
                list.add(("商品名称" + bank).getBytes("gb2312"));
                list.add(("单价" + bank).getBytes("gb2312"));
                list.add(("折扣" + bank).getBytes("gb2312"));
                list.add(("数量" + bank).getBytes("gb2312"));
                list.add("小计".getBytes("gb2312"));
                list.add(boldOff);
                list.add(nextLine1);

                for (int i = 0; i < serviceList.size(); i++) {
                    list.add(serviceList.get(i).getPM_Name().getBytes("gb2312"));
                    list.add(nextLine1);
                    double siglePrice = serviceList.get(i).getGOD_DiscountPrice() / serviceList.get(i).getPM_Number();
                    double price = serviceList.get(i).getPM_UnitPrice();
                    int num = serviceList.get(i).getPM_Number();
                    double discount = serviceList.get(i).getPM_Discount();
                    String total = Decima2KeeplUtil.stringToDecimal(DoubleMathUtil.mul(DoubleMathUtil.mul(price, num), discount) + "");

//                    list.add((bank + "      ￥" + siglePrice).getBytes("gb2312"));
                    list.add((bank + "      ￥" + serviceList.get(i).getPM_UnitPrice()).getBytes("gb2312"));
                    list.add((bank + "  " + Decima2KeeplUtil.stringToDecimal(serviceList.get(i).getPM_Discount() + "")).getBytes("gb2312"));
                    list.add((bank + serviceList.get(i).getPM_Number()).getBytes("gb2312"));
                    list.add((bank + "￥" + serviceList.get(i).getGOD_DiscountPrice()).getBytes("gb2312"));
                    list.add(nextLine1);
                }
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("应    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("实    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("支付详情: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getPayInfo().getBytes("gb2312"));//


                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("找    零: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));

                list.add(nextLine2);
                list.add(center);
                list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }
            return ESCUtil.byteMerger(list);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 世麦pos 会员充次
     *
     * @param printBean
     */
    @Override
    public void printBaseWinPOS_HYCC(Print_HYCC_Bean printBean) {
        String head = "==============================" + "\n" +
                "会员充次小票" + "\n" +
                "订单编号：" + printBean.getData().getOrderCode() + "\n" +
                "会员编号：" + printBean.getData().getVCH_Card() + "\n" +
                "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                "账户余额：" + printBean.getData().getVCH_Money() + "\n";

        String contents = "==============================" + "\n";
        for (int i = 0; i < printBean.getData().getServiceList().size(); i++) {

            contents = contents +
                    "服务名称：" + printBean.getData().getServiceList().get(i).getPM_Name() + "\n" +
                    "服务单价：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getServiceList().get(i).getPM_UnitPrice())) + "\n" +
                    "服务数量：" + printBean.getData().getServiceList().get(i).getPM_Number() + "\n" +
                    "小计：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getServiceList().get(i).getGOD_DiscountPrice() +
                    printBean.getData().getServiceList().get(i).getPM_UnitPrice())) + "\n" +
                    "==============================" + "\n";

        }

        String bottom = "应付金额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                "实收金额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney())) + "\n" +
                "找零金额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney())) + "\n" +
                "支付详情：" + printBean.getData().getPayInfo() + "\n" +
                "操作人员：" + printBean.getData().getCashier() + "\n" +
                "消费时间：" + mConsumeTime.format(new Date()) + "\n" +
                "客户签名：" + "\n\n\n";

        // 組打印json字符串
        JSONArray printTest = new JSONArray();
        // 添加文本打印,正常
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        JSONObject json3 = new JSONObject();
        JSONObject json4 = new JSONObject();
        JSONObject json5 = new JSONObject();
        try {
            //店铺名称
            json1.put("content-type", "txt");
            json1.put("content", "欢迎光临");
            json1.put("size", "2");
            json1.put("position", "center");
            json1.put("offset", "0");
            json1.put("bold", "1");
            json1.put("italic", "0");
            json1.put("height", "-1");
            //头部
            json2.put("content-type", "txt");
            json2.put("content", head);
            json2.put("size", "2");
            json2.put("position", "left");
            json2.put("offset", "0");
            json2.put("bold", "0");
            json2.put("italic", "0");
            json2.put("height", "-1");
            //正文
            json3.put("content-type", "txt");
            json3.put("content", contents);
            json3.put("size", "2");
            json3.put("position", "left");
            json3.put("offset", "0");
            json3.put("bold", "0");
            json3.put("italic", "0");
            json3.put("height", "-1");
            //尾部
            json4.put("content-type", "txt");
            json4.put("content", bottom);
            json4.put("size", "2");
            json4.put("position", "left");
            json4.put("offset", "0");
            json4.put("bold", "0");
            json4.put("italic", "0");
            json4.put("height", "-1");
            //结束
            json5.put("content-type", "txt");
            json5.put("content", "谢谢惠顾！");
            json5.put("size", "2");
            json5.put("position", "center");
            json5.put("offset", "0");
            json5.put("bold", "1");
            json5.put("italic", "0");
            json5.put("height", "-1");
            printTest.put(json1);
            printTest.put(json2);
            printTest.put(json3);
            printTest.put(json4);
            printTest.put(json5);
            printJson.put("spos", printTest);

            // 设置底部空3行
            ServiceManager.getInstence().getPrinter().printBottomFeedLine(3);

            pPrintJsonStr_KS = printJson.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 拉卡拉pos 会员充次
     *
     * @param printBean
     */
    @Override
    public void printLKL_HYCC(Print_HYCC_Bean printBean) {
        String head = "==============================" + "\n" +
                "会员充次小票" + "\n" +
                "订单编号：" + printBean.getData().getOrderCode() + "\n" +
                "会员编号：" + printBean.getData().getVCH_Card() + "\n" +
                "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                "账户余额：" + printBean.getData().getVCH_Money() + "\n";

        String contents = "==============================" + "\n";
        for (int i = 0; i < printBean.getData().getServiceList().size(); i++) {

            contents = contents +
                    "服务名称：" + printBean.getData().getServiceList().get(i).getPM_Name() + "\n" +
                    "服务单价：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getServiceList().get(i).getPM_UnitPrice())) + "\n" +
                    "服务数量：" + printBean.getData().getServiceList().get(i).getPM_Number() + "\n" +
                    "小计：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getServiceList().get(i).getGOD_DiscountPrice() +
                    printBean.getData().getServiceList().get(i).getPM_UnitPrice())) + "\n" +
                    "==============================" + "\n";

        }

        String bottom = "应付金额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney())) + "\n" +
                "实收金额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney())) + "\n" +
                "找零金额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney())) + "\n" +
                "支付详情：" + printBean.getData().getPayInfo() + "\n" +
                "操作人员：" + printBean.getData().getCashier() + "\n" +
                "消费时间：" + mConsumeTime.format(new Date()) + "\n" +
                "客户签名：" + "\n\n\n";

        pDetails = head + contents + bottom;
    }

    /**
     * 计次消费 蓝牙打印
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_JCXF(Print_JCXF_Bean printBean) {
        try {
            List<Print_JCXF_Bean.DataBean.ServiceListBean> serviceList = printBean.getData().getServiceList();
            List<byte[]> list = new ArrayList<>();

            if (!MyApplication.mTimesConsumeMap.isEmpty()) {
                if (MyApplication.mTimesConsumeMap.containsKey("LOGO") && MyApplication.JCXF_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.JCXF_LOGO);
                    File file = (File) CacheData.restoreObject("JCXF_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mTimesConsumeMap.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mTimesConsumeMap.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);
                }
                if (MyApplication.mTimesConsumeMap.containsKey("收银员")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));
                }
                if (MyApplication.mTimesConsumeMap.containsKey("结账日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));
                }
                if (MyApplication.mTimesConsumeMap.containsKey("流水单号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));
                }
                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));


                list.add(nextLine1);
                list.add(boldOn);
                list.add(("服务名称    " + bank + bank + bank + bank + bank + bank).getBytes("gb2312"));
                list.add(("使用/剩余").getBytes("gb2312"));
                list.add(boldOff);
                list.add(nextLine1);

                for (int i = 0; i < serviceList.size(); i++) {
                    String pmcode = serviceList.get(i).getSG_Code();
                    list.add(serviceList.get(i).getSG_Name().getBytes("gb2312"));
                    list.add(nextLine1);
                    if (MyApplication.mTimesConsumeMap.containsKey("商品编码")) {
                        list.add(pmcode.getBytes("gb2312"));
                        list.add((bank + bank + serviceList.get(i).getWOD_UseNumber() + "/" + serviceList.get(i).getWOD_ResidueDegree()).getBytes("gb2312"));
                    } else {
                        list.add(("         " + bank + bank + bank + bank + bank + bank + serviceList.get(i).getWOD_UseNumber() + "/" + serviceList.get(i).getWOD_ResidueDegree()).getBytes("gb2312"));
                    }
                    list.add(nextLine1);
                }
                list.add(left);
                list.add(mLine.getBytes("gb2312"));


                String str1 = printBean.getData().getActivityName() == null ? "无" : printBean.getData().getActivityName() + "";
                if (MyApplication.mTimesConsumeMap.containsKey("优惠活动")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("优惠活动: " + str1).getBytes("gb2312"));
                }

                if (MyApplication.mTimesConsumeMap.containsKey("赠送积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("赠送积分: " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData()
                            .getIntegralAdd()))).getBytes("gb2312"));
                }

                String str = printBean.getData().getRemark() == null ? "无" : printBean.getData().getRemark();
                if (MyApplication.mTimesConsumeMap.containsKey("备注")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("备    注: " + str).getBytes("gb2312"));
                }
                String str2;
                if (printBean.getData().getEMName() == null || printBean.getData().getEMName().equals("")) {
                    str2 = "无";
                } else {
                    str2 = printBean.getData().getEMName();
                }
                if (MyApplication.mTimesConsumeMap.containsKey("服务员工")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("服务员工: " + str2).getBytes("gb2312"));
                }

                if (MyApplication.mTimesConsumeMap.containsKey("会员卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员卡号: " + printBean.getData().getVCH_Card()).getBytes("gb2312"));
                }
                String facenum = printBean.getData().getVIP_FaceNumber() == null ? "无" : printBean.getData().getVIP_FaceNumber();
                if (facenum.equals("")) {
                    facenum = "无";
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("卡面卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡面卡号: " + facenum).getBytes("gb2312"));
                }
                String memName = printBean.getData().getVIP_Name();
                if (memName == null || memName.equals("")) {
                    memName = "无";
                }
                if (MyApplication.mTimesConsumeMap.containsKey("会员姓名")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员姓名: " + memName).getBytes("gb2312"));
                }
                if (MyApplication.mTimesConsumeMap.containsKey("卡内余额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内余额: " + printBean.getData().getVCH_Money()).getBytes("gb2312"));
                }
                if (MyApplication.mTimesConsumeMap.containsKey("卡内积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内积分: " + printBean.getData().getVCH_Point()).getBytes("gb2312"));
                }
                if (MyApplication.mTimesConsumeMap.containsKey("会员地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员地址: " + (printBean.getData().getVIPAddress() == null ? "" : printBean.getData().getVIPAddress())).getBytes("gb2312"));
                }
                if (MyApplication.mTimesConsumeMap.containsKey("打印时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));
                }
                if (MyApplication.mTimesConsumeMap.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mTimesConsumeMap.get("商户电话")).getBytes("gb2312"));
                }
                if (MyApplication.mTimesConsumeMap.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mTimesConsumeMap.get("联系地址")).getBytes("gb2312"));
                }
                if (MyApplication.mTimesConsumeMap.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mTimesConsumeMap.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }
                if (MyApplication.mTimesConsumeMap.containsKey("二维码") && MyApplication.JCXF_QR != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.JCXF_QR);
                    File file = (File) CacheData.restoreObject("JCXF_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));

                }
                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(titlebigger);
                list.add("欢迎光临".getBytes("gb2312"));
                list.add(titlesmall);

                list.add(nextLine2);
                list.add(left);
                list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(boldOn);
                list.add(("服务名称    " + bank + bank + bank + bank + bank + bank).getBytes("gb2312"));
                list.add(("使用/剩余").getBytes("gb2312"));
                list.add(boldOff);
                list.add(nextLine1);

                for (int i = 0; i < serviceList.size(); i++) {

                    list.add(serviceList.get(i).getSG_Name().getBytes("gb2312"));
                    list.add(nextLine1);
                    list.add(("         " + bank + bank + bank + bank + bank + bank + serviceList.get(i).getWOD_UseNumber() + "/" + serviceList.get(i).getWOD_ResidueDegree()).getBytes("gb2312"));
                    list.add(nextLine1);
                }
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));

                list.add(nextLine2);
                list.add(center);
                list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }
            return ESCUtil.byteMerger(list);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 计次消费 世麦打印
     *
     * @param printBean
     */
    @Override
    public void printBaseWinPOS_JCXF(Print_JCXF_Bean printBean) {
        String head = "==============================" + "\n" +
                "计次消费小票" + "\n" +
                "订单编号：" + printBean.getData().getOrderCode() + "\n" +
                "会员编号：" + printBean.getData().getVCH_Card() + "\n" +
                "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                "账户余额：" + printBean.getData().getVCH_Money() + "\n";

        String contents = "==============================" + "\n";
        for (int i = 0; i < printBean.getData().getServiceList().size(); i++) {

            contents = contents +
                    "服务名称：" + printBean.getData().getServiceList().get(i).getSG_Name() + "\n" +
                    "使用次数：" + printBean.getData().getServiceList().get(i).getWOD_UseNumber() + "\n" +
                    "剩余次数：" + printBean.getData().getServiceList().get(i).getWOD_ResidueDegree() + "\n" +
                    "==============================" + "\n";

        }

        String bottom = "卡内余额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getVCH_Money())) + "\n" +
                "卡内积分：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getVCH_Point())) + "\n" +
                "操作人员：" + printBean.getData().getCashier() + "\n" +
                "消费时间：" + mConsumeTime.format(new Date()) + "\n" +
                "客户签名：" + "\n\n\n";

        // 組打印json字符串
        JSONArray printTest = new JSONArray();
        // 添加文本打印,正常
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        JSONObject json3 = new JSONObject();
        JSONObject json4 = new JSONObject();
        JSONObject json5 = new JSONObject();
        try {
            //店铺名称
            json1.put("content-type", "txt");
            json1.put("content", "欢迎光临");
            json1.put("size", "2");
            json1.put("position", "center");
            json1.put("offset", "0");
            json1.put("bold", "1");
            json1.put("italic", "0");
            json1.put("height", "-1");
            //头部
            json2.put("content-type", "txt");
            json2.put("content", head);
            json2.put("size", "2");
            json2.put("position", "left");
            json2.put("offset", "0");
            json2.put("bold", "0");
            json2.put("italic", "0");
            json2.put("height", "-1");
            //正文
            json3.put("content-type", "txt");
            json3.put("content", contents);
            json3.put("size", "2");
            json3.put("position", "left");
            json3.put("offset", "0");
            json3.put("bold", "0");
            json3.put("italic", "0");
            json3.put("height", "-1");
            //尾部
            json4.put("content-type", "txt");
            json4.put("content", bottom);
            json4.put("size", "2");
            json4.put("position", "left");
            json4.put("offset", "0");
            json4.put("bold", "0");
            json4.put("italic", "0");
            json4.put("height", "-1");
            //结束
            json5.put("content-type", "txt");
            json5.put("content", "谢谢惠顾！");
            json5.put("size", "2");
            json5.put("position", "center");
            json5.put("offset", "0");
            json5.put("bold", "1");
            json5.put("italic", "0");
            json5.put("height", "-1");
            printTest.put(json1);
            printTest.put(json2);
            printTest.put(json3);
            printTest.put(json4);
            printTest.put(json5);
            printJson.put("spos", printTest);

            // 设置底部空3行
            ServiceManager.getInstence().getPrinter().printBottomFeedLine(3);

            pPrintJsonStr_KS = printJson.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 计次消费 拉卡拉打印
     *
     * @param printBean
     */
    @Override
    public void printLKL_JCXF(Print_JCXF_Bean printBean) {
        String head = "==============================" + "\n" +
                "计次消费小票" + "\n" +
                "订单编号：" + printBean.getData().getOrderCode() + "\n" +
                "会员编号：" + printBean.getData().getVCH_Card() + "\n" +
                "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                "账户余额：" + printBean.getData().getVCH_Money() + "\n";

        String contents = "==============================" + "\n";

        for (int i = 0; i < printBean.getData().getServiceList().size(); i++) {
            contents = contents +
                    "服务名称：" + printBean.getData().getServiceList().get(i).getSG_Name() + "\n" +
                    "使用次数：" + printBean.getData().getServiceList().get(i).getWOD_UseNumber() + "\n" +
                    "剩余次数：" + printBean.getData().getServiceList().get(i).getWOD_ResidueDegree() + "\n" +
                    "==============================" + "\n";

        }

        String bottom = "卡内余额：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getVCH_Money())) + "\n" +
                "卡内积分：￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getVCH_Point())) + "\n" +
                "操作人员：" + printBean.getData().getCashier() + "\n" +
                "消费时间：" + mConsumeTime.format(new Date()) + "\n" +
                "客户签名：" + "\n\n\n";

        pDetails = head + contents + bottom;
    }

    /**
     * 蓝牙打印  限时消费
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_XSXF(XSXF_Success_Bean printBean) {
        try {
            List<byte[]> list = new ArrayList<>();

            if (!MyApplication.mXSXFonsumeMap.isEmpty()) {

                if (MyApplication.mXSXFonsumeMap.containsKey("LOGO") && MyApplication.XSXF_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.XSXF_LOGO);
                    File file = (File) CacheData.restoreObject("XSXF_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mXSXFonsumeMap.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mXSXFonsumeMap.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);
                }
                String STR = printBean.getData().getCashier() == null ? "" : (String) printBean.getData().getCashier();
                if (MyApplication.mXSXFonsumeMap.containsKey("收银员")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("收 银 员: " + STR).getBytes("gb2312"));
                }

                if (MyApplication.mXSXFonsumeMap.containsKey("消费次数")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("消费次数: " + printBean.getData().getTS_MinusTimes()).getBytes("gb2312"));
                }

                if (MyApplication.mXSXFonsumeMap.containsKey("剩余次数")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("剩余次数: " + printBean.getData().getTS_SurplusTimes()).getBytes("gb2312"));
                }

                if (MyApplication.mXSXFonsumeMap.containsKey("消费时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(boldOn);
                    list.add(("消费时间: " + printBean.getData().getTS_UpdateTime()).getBytes("gb2312"));
                    list.add(boldOff);
                }

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));


                String STR1 = printBean.getData().getRemark() == null ? "" : (String) printBean.getData().getRemark();
                if (MyApplication.mXSXFonsumeMap.containsKey("备注")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("备    注: " + STR1).getBytes("gb2312"));
                }

                if (MyApplication.mXSXFonsumeMap.containsKey("会员卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员卡号: " + printBean.getData().getVCH_Card()).getBytes("gb2312"));
                }

                if (MyApplication.mXSXFonsumeMap.containsKey("卡面卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡面卡号: " + printBean.getData().getVIP_FaceNumber()).getBytes("gb2312"));
                }
                String vipName = null;
                if (printBean.getData().getVIP_Name() != null && !printBean.getData().getVIP_Name().equals("")) {
                    vipName = printBean.getData().getVIP_Name();
                } else {
                    vipName = printBean.getData().getVCH_Card();
                }

                if (MyApplication.mXSXFonsumeMap.containsKey("会员姓名")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员姓名: " + vipName).getBytes("gb2312"));
                }
                if (MyApplication.mXSXFonsumeMap.containsKey("卡内余额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内余额: " + printBean.getData().getInitialAmount()).getBytes("gb2312"));
                }

                if (MyApplication.mXSXFonsumeMap.containsKey("卡内积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内积分: " + printBean.getData().getInitialIntegral()).getBytes("gb2312"));
                }
                if (MyApplication.mXSXFonsumeMap.containsKey("会员地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员地址: " + (printBean.getData().getVIPAddress() == null ? "" : printBean.getData().getVIPAddress())).getBytes("gb2312"));
                }
                if (MyApplication.mXSXFonsumeMap.containsKey("打印时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("打印时间: " + DataUtils.getDataTime()).getBytes("gb2312"));
                }

                if (MyApplication.mXSXFonsumeMap.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mXSXFonsumeMap.get("商户电话")).getBytes("gb2312"));
                }
                if (MyApplication.mXSXFonsumeMap.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mXSXFonsumeMap.get("联系地址")).getBytes("gb2312"));
                }

                if (MyApplication.mXSXFonsumeMap.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mXSXFonsumeMap.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }
                if (MyApplication.mXSXFonsumeMap.containsKey("二维码") && MyApplication.XSXF_QR != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.XSXF_QR);
                    File file = (File) CacheData.restoreObject("XSXF_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                    list.add(center);
                }
                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);

            } else {

                list.add(nextLine1);
                list.add(center);
                list.add(titlebigger);
                list.add("欢迎光临".getBytes("gb2312"));
                list.add(titlesmall);

                String STR = printBean.getData().getCashier() == null ? "" : (String) printBean.getData().getCashier();

                list.add(nextLine1);
                list.add(left);
                list.add(("收 银 员: " + STR).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add(("消费时间: " + printBean.getData().getTS_UpdateTime()).getBytes("gb2312"));
                list.add(boldOff);

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("会员卡号: " + printBean.getData().getVCH_Card()).getBytes("gb2312"));

                String vipName = null;
                if (printBean.getData().getVIP_Name() != null && !printBean.getData().getVIP_Name().equals("")) {
                    vipName = printBean.getData().getVIP_Name();
                } else {
                    vipName = printBean.getData().getVCH_Card();
                }

                list.add(nextLine1);
                list.add(left);
                list.add(("会员姓名: " + vipName).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("打印时间: " + DataUtils.getDataTime()).getBytes("gb2312"));

                list.add(nextLine2);
                list.add(center);
                list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }

            return ESCUtil.byteMerger(list);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 积分兑换 蓝牙打印
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_JFDH(Print_JFDH_Bean printBean) {
        try {
            List<Print_JFDH_Bean.DataBean.GiftListBean> goodList = printBean.getData().getGiftList();
            List<byte[]> list = new ArrayList<>();

            if (!MyApplication.mIntegralExchangeMap.isEmpty()) {
                if (MyApplication.mIntegralExchangeMap.containsKey("LOGO") && MyApplication.JFDH_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.JFDH_LOGO);
                    File file = (File) CacheData.restoreObject("JFDH_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mIntegralExchangeMap.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mIntegralExchangeMap.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);
                }
                if (MyApplication.mIntegralExchangeMap.containsKey("收银员")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));
                }
                if (MyApplication.mIntegralExchangeMap.containsKey("结账日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));
                }
                if (MyApplication.mIntegralExchangeMap.containsKey("流水单号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));
                }
                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));


                list.add(nextLine1);
                list.add(boldOn);
                list.add(("礼品名称" + bank + bank + bank).getBytes("gb2312"));
                list.add(("数量 " + bank + bank).getBytes("gb2312"));
                list.add(("积分 " + bank + bank).getBytes("gb2312"));
                list.add("合计".getBytes("gb2312"));
                list.add(boldOff);

                list.add(nextLine1);
                for (int i = 0; i < goodList.size(); i++) {
                    String pmcode = goodList.get(i).getGM_Code();
                    String pmcode1 = null;
                    String pmcode2 = null;
                    if (pmcode.length() > 11) {
                        pmcode1 = pmcode.substring(0, 10);
                        pmcode2 = pmcode.substring(10, pmcode.length());
                    } else {
                        pmcode1 = pmcode;
                        while (pmcode1.length() < 10) {
                            pmcode1 = pmcode1 + bank;
                        }
                    }

                    list.add(goodList.get(i).getGM_Name().getBytes("gb2312"));
                    if (MyApplication.mGoodsConsumeMap.containsKey("规格")) {
                        list.add((bank + goodList.get(i).getGM_Modle()).getBytes("gb2312"));
                    }
                    list.add(nextLine1);

                    if (MyApplication.mIntegralExchangeMap.containsKey("商品编码")) {
                        list.add(pmcode1.getBytes("gb2312"));
                        list.add((bank + goodList.get(i).getGM_Acount()).getBytes("gb2312"));
                    } else {
                        list.add(("        " + bank + bank + bank + goodList.get(i).getGM_Acount()).getBytes("gb2312"));
                    }
                    list.add(("   " + bank + bank + goodList.get(i).getGM_Integral()).getBytes("gb2312"));
                    list.add(("  " + bank + bank + goodList.get(i).getEGD_Score()).getBytes("gb2312"));
                    list.add(nextLine1);
                    if (MyApplication.mIntegralExchangeMap.containsKey("商品编码")) {
                        if (pmcode.length() > 11) {
                            list.add(pmcode2.getBytes("gb2312"));
                            list.add(nextLine1);
                        }
                    }
                }
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(boldOn);
                list.add("兑换数量: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(String.valueOf(printBean.getData().getExchangeNum()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(boldOn);
                list.add("消耗积分: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(String.valueOf(printBean.getData().getIntegralDeduct()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(boldOn);
                list.add("剩余积分: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(String.valueOf(printBean.getData().getVCH_Point()).getBytes("gb2312"));
                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                String str = printBean.getData().getRemark() == null ? "无" : printBean.getData().getRemark();
                if (MyApplication.mIntegralExchangeMap.containsKey("备注")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("备    注: " + str).getBytes("gb2312"));
                }

                if (MyApplication.mIntegralExchangeMap.containsKey("会员卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员卡号: " + printBean.getData().getVCH_Card()).getBytes("gb2312"));
                }
                String facenum = printBean.getData().getVIP_FaceNumber() == null ? "无" : printBean.getData().getVIP_FaceNumber();
                if (facenum.equals("")) {
                    facenum = "无";
                }
                if (MyApplication.mGoodsConsumeMap.containsKey("卡面卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡面卡号: " + facenum).getBytes("gb2312"));
                }

                String memName = printBean.getData().getVIP_Name();
                if (memName == null || memName.equals("")) {
                    memName = "无";
                }
                if (MyApplication.mIntegralExchangeMap.containsKey("会员姓名")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员姓名: " + memName).getBytes("gb2312"));
                }

                if (MyApplication.mIntegralExchangeMap.containsKey("卡内余额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内余额: " + printBean.getData().getVCH_Money()).getBytes("gb2312"));
                }
                if (MyApplication.mIntegralExchangeMap.containsKey("会员地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员地址: " + (printBean.getData().getVIPAddress() == null ? "" : printBean.getData().getVIPAddress())).getBytes("gb2312"));
                }
                if (MyApplication.mIntegralExchangeMap.containsKey("打印时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));
                }
                if (MyApplication.mIntegralExchangeMap.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mIntegralExchangeMap.get("商户电话")).getBytes("gb2312"));
                }
                if (MyApplication.mIntegralExchangeMap.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mIntegralExchangeMap.get("联系地址")).getBytes("gb2312"));
                }
                if (MyApplication.mIntegralExchangeMap.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mIntegralExchangeMap.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }
                if (MyApplication.mIntegralExchangeMap.containsKey("二维码") && MyApplication.JFDH_QR != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.JFDH_QR);
                    File file = (File) CacheData.restoreObject("JFDH_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                    list.add(center);
                }
                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(titlebigger);
                list.add("欢迎光临".getBytes("gb2312"));
                list.add(titlesmall);

                list.add(nextLine2);
                list.add(left);
                list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(boldOn);
                list.add(("礼品名称" + bank + bank + bank).getBytes("gb2312"));
                list.add(("数量 " + bank + bank).getBytes("gb2312"));
                list.add(("积分 " + bank + bank).getBytes("gb2312"));
                list.add("合计".getBytes("gb2312"));
                list.add(boldOff);

                list.add(nextLine1);
                for (int i = 0; i < goodList.size(); i++) {
                    String pmcode = goodList.get(i).getGM_Code();
                    String pmcode1 = null;
                    String pmcode2 = null;
                    if (pmcode.length() > 7) {
                        pmcode1 = pmcode.substring(0, 6);
                        pmcode2 = pmcode.substring(6, pmcode.length());
                    } else {
                        pmcode1 = pmcode;
                    }

                    list.add(goodList.get(i).getGM_Name().getBytes("gb2312"));
                    list.add(nextLine1);

                    if (MyApplication.mIntegralExchangeMap.containsKey("商品编码")) {
                        list.add(pmcode1.getBytes("gb2312"));
                        list.add((bank + goodList.get(i).getGM_Acount()).getBytes("gb2312"));
                    } else {
                        list.add(("       " + bank + bank + bank + goodList.get(i).getGM_Acount()).getBytes("gb2312"));
                    }
                    list.add(("   " + bank + bank + goodList.get(i).getGM_Integral()).getBytes("gb2312"));
                    list.add(("  " + bank + bank + goodList.get(i).getEGD_Score()).getBytes("gb2312"));
                    list.add(nextLine1);
                    if (MyApplication.mIntegralExchangeMap.containsKey("商品编码")) {
                        if (pmcode.length() > 7) {
                            list.add(pmcode2.getBytes("gb2312"));
                            list.add(nextLine1);
                        }
                    }
                }
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(boldOn);
                list.add("兑换数量: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(String.valueOf(printBean.getData().getExchangeNum()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(boldOn);
                list.add("消耗积分: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(String.valueOf(printBean.getData().getIntegralDeduct()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(boldOn);
                list.add("剩余积分: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(String.valueOf(printBean.getData().getVCH_Point()).getBytes("gb2312"));
                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));

                list.add(nextLine2);
                list.add(center);
                list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }
            return ESCUtil.byteMerger(list);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 积分兑换 世麦打印
     *
     * @param printBean
     */
    @Override
    public void printBaseWinPOS_JFDH(Print_JFDH_Bean printBean) {
        String head = "==============================" + "\n" +
                "积分兑换小票" + "\n" +
                "订单编号：" + printBean.getData().getOrderCode() + "\n" +
                "会员编号：" + printBean.getData().getVCH_Card() + "\n" +
                "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                "账户余额：" + printBean.getData().getVCH_Money() + "\n";
        String contents = "==============================" + "\n";
        for (int i = 0; i < printBean.getData().getGiftList().size(); i++) {

            contents = contents +
                    "礼品名称：" + printBean.getData().getGiftList().get(i).getGM_Name() + "\n" +
                    "礼品数量：" + printBean.getData().getGiftList().get(i).getGM_Acount() + "\n" +
                    "礼品积分：" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getGiftList().get(i).getGM_Integral())) + "\n" +
                    "合计：" + printBean.getData().getGiftList().get(i).getEGD_Score() + "\n" +
                    "==============================" + "\n";

        }

        String bottom = "兑换数量：" + printBean.getData().getExchangeNum() + "\n" +
                "消费积分：" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getIntegralDeduct())) + "\n" +
                "剩余积分：" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getVCH_Point())) + "\n" +
                "操作人员：" + printBean.getData().getCashier() + "\n" +
                "消费时间：" + mConsumeTime.format(new Date()) + "\n" +
                "客户签名：" + "\n\n\n";

        // 組打印json字符串
        JSONArray printTest = new JSONArray();
        // 添加文本打印,正常
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        JSONObject json3 = new JSONObject();
        JSONObject json4 = new JSONObject();
        JSONObject json5 = new JSONObject();
        try {
            //店铺名称
            json1.put("content-type", "txt");
            json1.put("content", "欢迎光临");
            json1.put("size", "2");
            json1.put("position", "center");
            json1.put("offset", "0");
            json1.put("bold", "1");
            json1.put("italic", "0");
            json1.put("height", "-1");
            //头部
            json2.put("content-type", "txt");
            json2.put("content", head);
            json2.put("size", "2");
            json2.put("position", "left");
            json2.put("offset", "0");
            json2.put("bold", "0");
            json2.put("italic", "0");
            json2.put("height", "-1");
            //正文
            json3.put("content-type", "txt");
            json3.put("content", contents);
            json3.put("size", "2");
            json3.put("position", "left");
            json3.put("offset", "0");
            json3.put("bold", "0");
            json3.put("italic", "0");
            json3.put("height", "-1");
            //尾部
            json4.put("content-type", "txt");
            json4.put("content", bottom);
            json4.put("size", "2");
            json4.put("position", "left");
            json4.put("offset", "0");
            json4.put("bold", "0");
            json4.put("italic", "0");
            json4.put("height", "-1");
            //结束
            json5.put("content-type", "txt");
            json5.put("content", "谢谢惠顾！");
            json5.put("size", "2");
            json5.put("position", "center");
            json5.put("offset", "0");
            json5.put("bold", "1");
            json5.put("italic", "0");
            json5.put("height", "-1");
            printTest.put(json1);
            printTest.put(json2);
            printTest.put(json3);
            printTest.put(json4);
            printTest.put(json5);
            printJson.put("spos", printTest);

            // 设置底部空3行
            ServiceManager.getInstence().getPrinter().printBottomFeedLine(3);

            pPrintJsonStr_KS = printJson.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 积分兑换 拉卡拉打印
     *
     * @param printBean
     */
    @Override
    public void printLKL_JFDH(Print_JFDH_Bean printBean) {
        String head = "==============================" + "\n" +
                "积分兑换小票" + "\n" +
                "订单编号：" + printBean.getData().getOrderCode() + "\n" +
                "会员编号：" + printBean.getData().getVCH_Card() + "\n" +
                "会员名称：" + printBean.getData().getVIP_Name() + "\n" +
                "账户余额：" + printBean.getData().getVCH_Money() + "\n";

        String contents = "==============================" + "\n";
        for (int i = 0; i < printBean.getData().getGiftList().size(); i++) {

            contents = contents +
                    "礼品名称：" + printBean.getData().getGiftList().get(i).getGM_Name() + "\n" +
                    "礼品数量：" + printBean.getData().getGiftList().get(i).getGM_Acount() + "\n" +
                    "礼品积分：" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getGiftList().get(i).getGM_Integral())) + "\n" +
                    "合计：" + printBean.getData().getGiftList().get(i).getEGD_Score() + "\n" +
                    "==============================" + "\n";

        }

        String bottom = "兑换数量：" + printBean.getData().getExchangeNum() + "\n" +
                "消费积分：" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getIntegralDeduct())) + "\n" +
                "剩余积分：" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getVCH_Point())) + "\n" +
                "操作人员：" + printBean.getData().getCashier() + "\n" +
                "消费时间：" + mConsumeTime.format(new Date()) + "\n" +
                "客户签名：" + "\n\n\n";

        pDetails = head + contents + bottom;
    }


    /**
     * 蓝牙打印  会员开卡
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_HYKK(Print_HYKK_Bean printBean) {
        Li("--------------------------------蓝牙打印 会员开卡--random:" + new Gson().toJson(printBean));
        try {

            List<byte[]> list = new ArrayList<>();

            if (!MyApplication.mCardOpenMap.isEmpty()) {
                if (MyApplication.mCardOpenMap.containsKey("LOGO") && MyApplication.OPENCARD_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.OPENCARD_LOGO);
                    File file = (File) CacheData.restoreObject("OPENCARD_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mCardOpenMap.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mCardOpenMap.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);
                }
                if (MyApplication.mCardOpenMap.containsKey("收银员")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));
                }
                if (MyApplication.mCardOpenMap.containsKey("结账日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));
                }

                if (MyApplication.mCardOpenMap.containsKey("开卡单号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("开卡单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("开卡费用: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getVCH_Fee()))).getBytes("gb2312"));//

                if (MyApplication.mCardOpenMap.containsKey("初始金额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("初始金额: " + printBean.getData().getInitialAmount()).getBytes("gb2312"));
                }
                if (MyApplication.mCardOpenMap.containsKey("初始积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("初始积分: " + printBean.getData().getInitialIntegral()).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                String str = printBean.getData().getRemark() == null ? "无" : printBean.getData().getRemark();
                if (MyApplication.mCardOpenMap.containsKey("备注")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("备    注: " + str).getBytes("gb2312"));
                }
                String str2;
                if (printBean.getData().getEMName() == null || printBean.getData().getEMName().equals("")) {
                    str2 = "无";
                } else {
                    str2 = printBean.getData().getEMName();
                }
                if (MyApplication.mCardOpenMap.containsKey("服务员工")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("服务员工: " + str2).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("会员卡号: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getVCH_Card().getBytes("gb2312"));//

                String facenum = printBean.getData().getVIP_FaceNumber() == null ? "无" : printBean.getData().getVIP_FaceNumber();
                if (facenum.equals("")) {
                    facenum = "无";
                }
                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("卡面卡号: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add((facenum).getBytes("gb2312"));//

                String memName = printBean.getData().getVIP_Name();
                if (memName == null || memName.equals("")) {
                    memName = "无";
                }

                if (MyApplication.mCardOpenMap.containsKey("会员姓名")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员姓名: " + memName).getBytes("gb2312"));
                }
                if (MyApplication.mCardOpenMap.containsKey("会员地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员地址: " + (printBean.getData().getVIPAddress() == null ? "" : printBean.getData().getVIPAddress())).getBytes("gb2312"));
                }
                if (MyApplication.mCardOpenMap.containsKey("打印时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));
                }
                if (MyApplication.mCardOpenMap.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mCardOpenMap.get("商户电话")).getBytes("gb2312"));
                }
                if (MyApplication.mCardOpenMap.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mCardOpenMap.get("联系地址")).getBytes("gb2312"));
                }
                if (MyApplication.mCardOpenMap.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mCardOpenMap.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }
                if (MyApplication.mCardOpenMap.containsKey("二维码") && MyApplication.OPENCARD_QR != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.OPENCARD_QR);
                    File file = (File) CacheData.restoreObject("OPENCARD_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                    list.add(center);
                }
                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(titlebigger);
                list.add("欢迎光临".getBytes("gb2312"));
                list.add(titlesmall);

                list.add(nextLine2);
                list.add(left);
                list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("开卡费用: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getVCH_Fee()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("会员卡号: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getVCH_Card().getBytes("gb2312"));//

                String facenum = printBean.getData().getVIP_FaceNumber() == null ? "无" : printBean.getData().getVIP_FaceNumber();
                if (facenum.equals("")) {
                    facenum = "无";
                }
                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("卡面卡号: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add((facenum).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));

                list.add(nextLine2);
                list.add(center);
                list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }
            return ESCUtil.byteMerger(list);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 蓝牙打印  入库
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_RK(RK_Success_Bean printBean) {
        RK_Success_Bean.DataBean bean = printBean.getData();
        String shopname = (String) uSharedPreferencesUtiles.get(mContext, "ShopName", "默认店铺");
        try {
            List<byte[]> list = new ArrayList<>();

            if (!MyApplication.mGoodsIn.isEmpty()) {

                if (MyApplication.mGoodsIn.containsKey("LOGO") && MyApplication.RK_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.RK_LOGO);
                    File file = (File) CacheData.restoreObject("RK_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mGoodsIn.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mGoodsIn.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);
                }
                String STR = printBean.getData().getSI_Hander() == null ? "" : printBean.getData().getSI_Hander();
                if (MyApplication.mGoodsIn.containsKey("经手人")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("经 手 人: " + STR).getBytes("gb2312"));
                }
                if (MyApplication.mGoodsIn.containsKey("供应商")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("供 应 商: " + printBean.getData().getSL_Name()).getBytes("gb2312"));
                }
                String str = null;
                if (printBean.getData().getSI_InType().equals("1")) {//1采购进货 2调拨 3退货
                    str = "采购进货";
                } else if (printBean.getData().getSI_InType().equals("2")) {
                    str = "库存调拨";
                } else if (printBean.getData().getSI_InType().equals("3")) {
                    str = "商品退货";
                }

                if (MyApplication.mGoodsIn.containsKey("入库类型")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("入库类型: " + str).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsIn.containsKey("入库日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("入库日期: " + printBean.getData().getSI_CreateTime()).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsIn.containsKey("流水单号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("流水单号: " + printBean.getData().getSI_TrackingNo()).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(boldOn);
                list.add(("商品名称" + bank + bank + bank).getBytes("gb2312"));
                list.add(("进价" + bank + bank).getBytes("gb2312"));
                list.add(("数量" + bank + bank).getBytes("gb2312"));
                list.add("小计".getBytes("gb2312"));
                list.add(boldOff);

                double totalPrice = 0;
                double totalNum = 0;
                list.add(nextLine1);
                for (int i = 0; i < bean.getStockInDetailList().size(); i++) {
                    list.add(bean.getStockInDetailList().get(i).getPM_Name().getBytes("gb2312"));
                    if (MyApplication.mGoodsIn.containsKey("规格")) {
                        list.add((bank + bean.getStockInDetailList().get(i).getPM_Modle()).getBytes("gb2312"));
                    }
                    list.add(nextLine1);
                    String pmcode = bean.getStockInDetailList().get(i).getPM_Code();
                    String pmcode1 = null;
                    String pmcode2 = null;
                    if (pmcode.length() > 11) {
                        pmcode1 = pmcode.substring(0, 10);
                        pmcode2 = pmcode.substring(10, pmcode.length());
                    } else {
                        pmcode1 = pmcode;
                        while (pmcode1.length() < 10) {
                            pmcode1 = pmcode1 + bank;
                        }
                    }
                    if (MyApplication.mGoodsIn.containsKey("商品编码")) {
                        list.add(pmcode1.getBytes("gb2312"));
                        list.add((bank + " " + bean.getStockInDetailList().get(i).getPM_UnitPrice()).getBytes("gb2312"));
                    } else {
                        list.add(("        " + bank + bank + bank + bean.getStockInDetailList().get(i).getPM_UnitPrice()).getBytes("gb2312"));
                    }
                    list.add((bank + bank + bean.getStockInDetailList().get(i).getSID_Amount()).getBytes("gb2312"));
                    list.add((bank + bank + bean.getStockInDetailList().get(i).getSID_TotalPrice()).getBytes("gb2312"));
                    list.add(nextLine1);
                    if (MyApplication.mGoodsIn.containsKey("商品编码")) {
                        if (pmcode.length() > 11) {
                            list.add(pmcode2.getBytes("gb2312"));
                            list.add(nextLine1);
                        }
                    }
                    totalPrice += bean.getStockInDetailList().get(i).getSID_TotalPrice();
                    totalNum += bean.getStockInDetailList().get(i).getSID_Amount();
                }
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                if (MyApplication.mGoodsIn.containsKey("合计金额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("合计金额: " + totalPrice).getBytes("gb2312"));
                }


                if (MyApplication.mGoodsIn.containsKey("其他金额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("其他金额: " + printBean.getData().getSI_OtherMoney()).getBytes("gb2312"));
                }
                String all = Decima2KeeplUtil.stringToDecimal(String.valueOf(totalPrice + printBean.getData().getSI_OtherMoney()));
                list.add(nextLine1);
                list.add(boldOn);
                list.add(left);
                list.add(("总计金额: " + all).getBytes("gb2312"));
                list.add(boldOff);

                String str1 = null;
                if (printBean.getData().getSI_PayCode() != null) {
                    if (printBean.getData().getSI_PayCode().equals("XJZF")) {
                        str1 = "现金支付";
                    } else if (printBean.getData().getSI_PayCode().equals("YLZF")) {
                        str1 = "银联支付";
                    } else if (printBean.getData().getSI_PayCode().equals("WX_JZ")) {
                        str1 = "微信记账";
                    } else if (printBean.getData().getSI_PayCode().equals("ZFB_JZ")) {
                        str1 = "支付宝记账";
                    }
                } else {
                    str1 = "";
                }


                if (MyApplication.mGoodsIn.containsKey("支付方式")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("支付方式: " + str1 + "(" + all + ")").getBytes("gb2312"));
                }

                if (MyApplication.mGoodsIn.containsKey("合计数量")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("合计数量: " + totalNum).getBytes("gb2312"));
                }

                String STR1 = printBean.getData().getSI_Remark() == null ? "" : printBean.getData().getSI_Remark();
                if (MyApplication.mGoodsIn.containsKey("备注信息")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("备注信息: " + STR1).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsIn.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mGoodsIn.get("商户电话")).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsIn.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mGoodsIn.get("联系地址")).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsIn.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mGoodsIn.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }
                if (MyApplication.mGoodsIn.containsKey("二维码") && MyApplication.mGoodsIn != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.RK_QR);
                    File file = (File) CacheData.restoreObject("RK_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                    list.add(center);
                }
                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);

            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(boldOn);
                list.add(shopname.getBytes("gb2312"));
                list.add(boldOff);

                list.add(nextLine2);
                list.add(left);
                list.add(("操 作 员: " + bean.getStockInDetailList().get(0).getSID_Creator()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("入库时间: " + bean.getStockInDetailList().get(0).getSID_CreateTime()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("流水单号: " + printBean.getData().getSI_TrackingNo()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("供 应 商: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add((printBean.getData().getSL_Name()).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("商品名称: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(bean.getStockInDetailList().get(0).getPM_Name().getBytes("gb2312"));//


                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("入库数量: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add((bean.getStockInDetailList().get(0).getSID_Amount() + "").getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("备    注: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getSI_Remark().getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("签    名: ".getBytes("gb2312"));
                list.add(boldOff);
//            list.add("".getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }

            return ESCUtil.byteMerger(list);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 蓝牙打印  出库
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_CK(CK_Success_Bean printBean) {
        CK_Success_Bean.DataBean bean = printBean.getData();
        String shopname = (String) uSharedPreferencesUtiles.get(mContext, "ShopName", "默认店铺");
        try {
            List<byte[]> list = new ArrayList<>();

            if (!MyApplication.mGoodsOut.isEmpty()) {

                if (MyApplication.mGoodsOut.containsKey("LOGO") && MyApplication.CK_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.CK_LOGO);
                    File file = (File) CacheData.restoreObject("CK_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mGoodsOut.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mGoodsOut.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);
                }
                String STR = printBean.getData().getSO_Hander() == null ? "" : (String) printBean.getData().getSO_Hander();
                if (MyApplication.mGoodsOut.containsKey("经手人")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("经 手 人: " + STR).getBytes("gb2312"));
                }

                String str = null;//2 采购退货  0 商品报废 3 库存调拨 1 商品消费
                if (printBean.getData().getSO_OutType().equals("2")) {//1采购进货 2调拨 3退货
                    str = "采购退货";
                } else if (printBean.getData().getSO_OutType().equals("0")) {
                    str = "商品报废";
                } else if (printBean.getData().getSO_OutType().equals("3")) {
                    str = "其它";
                } else if (printBean.getData().getSO_OutType().equals("1")) {
                    str = "商品消费";
                }

                if (MyApplication.mGoodsOut.containsKey("出库类型")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("出库类型: " + str).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsOut.containsKey("出库日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("出库日期: " + printBean.getData().getSO_CreateTime()).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsOut.containsKey("流水单号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("流水单号: " + printBean.getData().getSO_TrackingNo()).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                String kongge = "";
                int length = 11;
                if (MyApplication.mGoodsOut.containsKey("原价")) {
                    length = 9;
                    kongge = "";
                } else {
                    length = 11;
                    kongge = "  ";
                }

                list.add(nextLine1);
                list.add(boldOn);
                list.add(("商品  " + bank + bank + bank + kongge).getBytes("gb2312"));
                list.add(("单价").getBytes("gb2312"));
                if (MyApplication.mGoodsOut.containsKey("原价")) {
                    list.add(("/原价" + bank).getBytes("gb2312"));
                }
                list.add((bank).getBytes("gb2312"));
                list.add(("数量 " + bank).getBytes("gb2312"));
                list.add("小计".getBytes("gb2312"));
                list.add(boldOff);

                double totalPrice = 0;
                double totalNum = 0;

                list.add(nextLine1);
                for (int i = 0; i < bean.getStockOutDetailList().size(); i++) {
                    double siglePrice = bean.getStockOutDetailList().get(i).getSOD_DiscountPrice() / bean.getStockOutDetailList().get(i).getSOD_Amount();
                    list.add(bean.getStockOutDetailList().get(i).getPM_Name().getBytes("gb2312"));
                    if (MyApplication.mGoodsOut.containsKey("规格")) {
                        list.add((bank + bean.getStockOutDetailList().get(i).getPM_Modle()).getBytes("gb2312"));
                    }
                    list.add(nextLine1);

                    String pmcode = bean.getStockOutDetailList().get(i).getPM_Code();
                    String pmcode1 = null;
                    String pmcode2 = null;
                    if (pmcode.length() > length) {
                        pmcode1 = pmcode.substring(0, length - 1);
                        pmcode2 = pmcode.substring(length - 1, pmcode.length());
                    } else {
                        pmcode1 = pmcode;
                        while (pmcode1.length() < length - 1) {
                            pmcode1 = pmcode1 + bank;
                        }
                    }
                    if (MyApplication.mGoodsOut.containsKey("商品编码")) {
                        list.add(pmcode1.getBytes("gb2312"));
                        list.add((bank + bank + kongge + siglePrice).getBytes("gb2312"));
                    } else {
                        list.add(("       " + bank + bank + kongge + siglePrice).getBytes("gb2312"));
                    }
                    if (MyApplication.mGoodsOut.containsKey("原价")) {
                        list.add(("/" + bean.getStockOutDetailList().get(i).getPM_UnitPrice()).getBytes("gb2312"));
                    }

                    if ((String.valueOf(siglePrice).length() + String.valueOf(bean.getStockOutDetailList().get(i).getPM_UnitPrice()).length()) < 8) {
                        list.add((bank).getBytes("gb2312"));
                    }
                    list.add((bank + bank + bean.getStockOutDetailList().get(i).getSOD_Amount()).getBytes("gb2312"));
                    list.add((bank + bank + bean.getStockOutDetailList().get(i).getSOD_TotalPrice()).getBytes("gb2312"));
                    list.add(nextLine1);
                    if (MyApplication.mGoodsOut.containsKey("商品编码")) {
                        if (pmcode.length() > length) {
                            list.add(pmcode2.getBytes("gb2312"));
                            list.add(nextLine1);
                        }
                    }
                    totalPrice += bean.getStockOutDetailList().get(i).getSOD_TotalPrice();
                    totalNum += bean.getStockOutDetailList().get(i).getSOD_Amount();
                }
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                if (MyApplication.mGoodsOut.containsKey("合计金额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("合计金额: " + totalPrice).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsOut.containsKey("其他金额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("其他金额: " + printBean.getData().getSO_OtherMoney()).getBytes("gb2312"));
                }
                String all = Decima2KeeplUtil.stringToDecimal(String.valueOf(totalPrice + printBean.getData().getSO_OtherMoney()));
                list.add(nextLine1);
                list.add(boldOn);
                list.add(left);
                list.add(("总计金额: " + all).getBytes("gb2312"));
                list.add(boldOff);

                String str1 = null;
                if (printBean.getData().getSO_PayCode().equals("XJZF")) {
                    str1 = "现金支付";
                } else if (printBean.getData().getSO_PayCode().equals("YLZF")) {
                    str1 = "银联支付";
                } else if (printBean.getData().getSO_PayCode().equals("WX_JZ")) {
                    str1 = "微信记账";
                } else if (printBean.getData().getSO_PayCode().equals("ZFB_JZ")) {
                    str1 = "支付宝记账";
                }

                if (MyApplication.mGoodsOut.containsKey("支付方式")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("支付方式: " + str1).getBytes("gb2312"));
                }

                String STR1 = printBean.getData().getSO_Remark() == null ? "" : (String) printBean.getData().getSO_Remark();
                if (MyApplication.mGoodsOut.containsKey("备注信息")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("备注信息: " + STR1).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsOut.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mGoodsOut.get("商户电话")).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsOut.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mGoodsOut.get("联系地址")).getBytes("gb2312"));
                }

                if (MyApplication.mGoodsOut.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mGoodsOut.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }
                if (MyApplication.mGoodsOut.containsKey("二维码") && MyApplication.mGoodsOut != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.CK_QR);
                    File file = (File) CacheData.restoreObject("CK_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                    list.add(center);
                }
                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);

            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(boldOn);
                list.add(shopname.getBytes("gb2312"));
                list.add(boldOff);

                list.add(nextLine2);
                list.add(left);
                list.add(("操 作 员: " + bean.getStockOutDetailList().get(0).getSOD_Creator()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("出库时间: " + bean.getStockOutDetailList().get(0).getSOD_CreateTime()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("流水单号: " + printBean.getData().getSO_TrackingNo()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));


                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("商品名称: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(bean.getStockOutDetailList().get(0).getPM_Name().getBytes("gb2312"));//


                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("出库数量: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add((bean.getStockOutDetailList().get(0).getSOD_Amount() + "").getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("备    注: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add((printBean.getData().getSO_Remark() + "").getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("签    名: ".getBytes("gb2312"));
                list.add(boldOff);
//            list.add("".getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }

            return ESCUtil.byteMerger(list);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 蓝牙打印  交班
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_JB(JB_Success_Bean printBean) {
        List<byte[]> list = new ArrayList<>();
        try {
            if (!MyApplication.mHandOverMap.isEmpty()) {

                if (MyApplication.mHandOverMap.containsKey("LOGO") && MyApplication.JB_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.JB_LOGO);
                    File file = (File) CacheData.restoreObject("JB_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mHandOverMap.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mHandOverMap.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);
                }
                if (MyApplication.mHandOverMap.containsKey("收银员")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("收 银 员: " + printBean.getData().getDataList().get(0).getSA_ShiftName()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("结账日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("结账日期: " + printBean.getData().getDataList().get(0).getSA_CreateTime()).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("会员相关数据统计".getBytes("gb2312"));
                list.add(boldOff);

                if (MyApplication.mHandOverMap.containsKey("新增会员数")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("新增会员数: " + printBean.getData().getDataList().get(0).getSA_NewMemberNumber()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("会员开卡")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员开卡: " + printBean.getData().getDataList().get(0).getSA_OpenCardTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("会员充值")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员充值: " + printBean.getData().getDataList().get(0).getSA_RechargeTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("会员充次")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员充次: " + printBean.getData().getDataList().get(0).getSA_ChargeTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("会员延期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员延期: " + printBean.getData().getDataList().get(0).getSA_DelayTotal()).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("消费数据统计".getBytes("gb2312"));
                list.add(boldOff);

                if (MyApplication.mHandOverMap.containsKey("一键加油")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("一键加油: " + printBean.getData().getDataList().get(0).getSA_RefuelTotal()).getBytes("gb2312"));
                }

                if (MyApplication.mHandOverMap.containsKey("商品消费")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商品消费: " + printBean.getData().getDataList().get(0).getSA_ConsumptionTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("快速消费")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("快速消费: " + printBean.getData().getDataList().get(0).getSA_FastConsumptionTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("套餐消费")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("套餐消费: " + printBean.getData().getDataList().get(0).getSA_ComboTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("计时消费")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("计时消费: " + printBean.getData().getDataList().get(0).getSA_TimeTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("房台消费")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("房台消费: " + printBean.getData().getDataList().get(0).getSA_HouseTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("商品退货")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商品退货: " + printBean.getData().getDataList().get(0).getSA_ReturnGoodsTotal()).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("综合数据统计".getBytes("gb2312"));
                list.add(boldOff);

                if (MyApplication.mHandOverMap.containsKey("现金收入")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("现金收入: " + printBean.getData().getDataList().get(0).getSA_CashTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("银联收入")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("银联收入: " + printBean.getData().getDataList().get(0).getSA_UnionTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("余额收入")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("余额收入: " + printBean.getData().getDataList().get(0).getSA_BalanceTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("微信收入")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("微信收入: " + printBean.getData().getDataList().get(0).getSA_WeChatpayTotal()).getBytes("gb2312"));
                }

                if (MyApplication.mHandOverMap.containsKey("积分收入")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("积分收入: " + printBean.getData().getDataList().get(0).getSA_IntegralTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("扫呗收入")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("扫呗收入: " + printBean.getData().getDataList().get(0).getSA_BarCodeTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("支付宝收入")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("支付宝收入: " + printBean.getData().getDataList().get(0).getSA_AlipaypayTotal()).getBytes("gb2312"));
                }

                if (MyApplication.mHandOverMap.containsKey("优惠券收入")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("优惠券收入: " + printBean.getData().getDataList().get(0).getSA_CouponTotal()).getBytes("gb2312"));
                }

                if (MyApplication.mHandOverMap.containsKey("其他收入")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("其他收入: " + printBean.getData().getDataList().get(0).getSA_OtherpayTotal()).getBytes("gb2312"));
                }

                if (MyApplication.mHandOverMap.containsKey("抹零")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("抹    零: " + printBean.getData().getDataList().get(0).getSA_AlipaypayTotal()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("总收入")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("总收入: " + printBean.getData().getDataList().get(0).getSA_AllTotal()).getBytes("gb2312"));
                }

                list.add(nextLine1);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("交班信息".getBytes("gb2312"));
                list.add(boldOff);

                double jieyu = printBean.getData().getDataList().get(0).getSA_IssuedBusiness() + printBean.getData().getDataList().get(0).getSA_HandInBusiness() + printBean.getData().getDataList().get(0).getSA_CashTotal();

                list.add(nextLine1);
                list.add(left);
                list.add(("上次结余: " + jieyu).getBytes("gb2312"));


                if (MyApplication.mHandOverMap.containsKey("上交营业金额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("上交营业金额: " + printBean.getData().getDataList().get(0).getSA_HandInBusiness()).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("下发营业金额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("下发营业金额: " + printBean.getData().getDataList().get(0).getSA_IssuedBusiness()).getBytes("gb2312"));
                }


                list.add(nextLine1);
                list.add(left);
                list.add(("接班用户: " + printBean.getData().getDataList().get(0).getSA_SuccessionName()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(mLine.getBytes("gb2312"));

                if (MyApplication.mHandOverMap.containsKey("打印时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mHandOverMap.get("商户电话")).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mHandOverMap.get("联系地址")).getBytes("gb2312"));
                }
                if (MyApplication.mHandOverMap.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mHandOverMap.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));

                }
                if (MyApplication.mHandOverMap.containsKey("二维码") && MyApplication.JB_QR != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.JB_QR);
                    File file = (File) CacheData.restoreObject("JB_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                    list.add(center);
                }
                list.add(nextLine4);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);

            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(titlebigger);
                list.add("欢迎光临".getBytes("gb2312"));
                list.add(titlesmall);

                list.add(nextLine1);
                list.add(left);
                list.add(("收 银 员: " + printBean.getData().getDataList().get(0).getSA_ShiftName()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("结账日期: " + printBean.getData().getDataList().get(0).getSA_CreateTime()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("会员开卡数据".getBytes("gb2312"));
                list.add(boldOff);

                list.add(nextLine1);
                list.add(left);
                list.add(("新增会员数: " + printBean.getData().getDataList().get(0).getSA_NewMemberNumber()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("会员开卡: " + printBean.getData().getDataList().get(0).getSA_OpenCardTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("会员充值: " + printBean.getData().getDataList().get(0).getSA_RechargeTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("会员充次: " + printBean.getData().getDataList().get(0).getSA_ChargeTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("会员延期: " + printBean.getData().getDataList().get(0).getSA_DelayTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("消费数据统计".getBytes("gb2312"));
                list.add(boldOff);

                list.add(nextLine1);
                list.add(left);
                list.add(("商品消费: " + printBean.getData().getDataList().get(0).getSA_ConsumptionTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("快速消费: " + printBean.getData().getDataList().get(0).getSA_FastConsumptionTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("套餐消费: " + printBean.getData().getDataList().get(0).getSA_ComboTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("计时消费: " + printBean.getData().getDataList().get(0).getSA_TimeTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("房台消费: " + printBean.getData().getDataList().get(0).getSA_HouseTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("商品退货: " + printBean.getData().getDataList().get(0).getSA_ReturnGoodsTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("综合数据统计".getBytes("gb2312"));
                list.add(boldOff);

                list.add(nextLine1);
                list.add(left);
                list.add(("现金收入: " + printBean.getData().getDataList().get(0).getSA_CashTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("银联收入: " + printBean.getData().getDataList().get(0).getSA_UnionTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("余额收入: " + printBean.getData().getDataList().get(0).getSA_BalanceTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("微信收入: " + printBean.getData().getDataList().get(0).getSA_WeChatpayTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("支付宝收入: " + printBean.getData().getDataList().get(0).getSA_AlipaypayTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("总收入: " + printBean.getData().getDataList().get(0).getSA_AllTotal()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("交班信息".getBytes("gb2312"));
                list.add(boldOff);

                list.add(nextLine1);
                list.add(left);
                list.add(("上交营业金额: " + printBean.getData().getDataList().get(0).getSA_HandInBusiness()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("下发营业金额: " + printBean.getData().getDataList().get(0).getSA_IssuedBusiness()).getBytes("gb2312"));

                list.add(nextLine4);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }

            return ESCUtil.byteMerger(list);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 蓝牙打印 商品退货
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_SPTH(Print_SPTH_Bean printBean) {
        try {
            List<Print_SPTH_Bean.DataBean.GoodsListBean> goodList = printBean.getData().getGoodsList();
            List<byte[]> list = new ArrayList<>();

            if (!MyApplication.mReTureOrder.isEmpty()) {
                if (MyApplication.mReTureOrder.containsKey("LOGO") && MyApplication.SPTH_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.SPTH_LOGO);
                    File file = (File) CacheData.restoreObject("SPTH_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mReTureOrder.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mReTureOrder.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);
                }
                if (MyApplication.mReTureOrder.containsKey("收银员")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));
                }
                if (MyApplication.mReTureOrder.containsKey("结账日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));
                }
                if (MyApplication.mReTureOrder.containsKey("流水单号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));
                }
                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                String kongge = "";
                int length = 9;
                if (!MyApplication.mReTureOrder.containsKey("原价")) {
                    length = 11;
                    kongge = "  ";
                } else {
                    length = 9;
                    kongge = "";
                }

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add(("商品" + bank + bank + bank + kongge).getBytes("gb2312"));
                list.add(("单价").getBytes("gb2312"));
                if (MyApplication.mReTureOrder.containsKey("原价")) {
                    list.add(("/原价").getBytes("gb2312"));
                }
                list.add((bank).getBytes("gb2312"));
                list.add(("数量" + bank).getBytes("gb2312"));
                list.add("小计".getBytes("gb2312"));
                list.add(boldOff);
                list.add(nextLine1);

                double totalnum = 0;
                for (int i = 0; i < goodList.size(); i++) {

                    double siglePrice = goodList.get(i).getROD_DiscountPrice() / goodList.get(i).getPM_Number();
                    double num = goodList.get(i).getPM_Number();
                    totalnum += num;

                    list.add(left);
                    list.add(goodList.get(i).getPM_Name().getBytes("gb2312"));
                    if (MyApplication.mReTureOrder.containsKey("规格")) {
                        list.add((bank + goodList.get(i).getPM_Modle()).getBytes("gb2312"));
                    }
                    list.add(nextLine1);

                    String pmcode = goodList.get(i).getPM_Code();
                    String pmcode1 = null;
                    String pmcode2 = null;
                    if (pmcode.length() > length) {
                        pmcode1 = pmcode.substring(0, length - 1);
                        pmcode2 = pmcode.substring(length - 1, pmcode.length());
                    } else {
                        pmcode1 = pmcode;
                        while (pmcode1.length() < length - 2) {
                            pmcode1 = pmcode1 + bank;
                        }
                    }
                    if (MyApplication.mReTureOrder.containsKey("商品编码")) {
                        list.add(pmcode1.getBytes("gb2312"));
                        list.add((bank + siglePrice).getBytes("gb2312"));
                    } else {
                        list.add(("       " + bank + bank + bank + kongge + siglePrice).getBytes("gb2312"));
                    }
                    if (MyApplication.mReTureOrder.containsKey("原价")) {
                        list.add(("/" + goodList.get(i).getPM_OriginalPrice()).getBytes("gb2312"));
                    }
                    list.add((bank + goodList.get(i).getPM_Number()).getBytes("gb2312"));
                    list.add((bank + Decima2KeeplUtil.stringToDecimal(goodList.get(i).getROD_DiscountPrice() + "")).getBytes("gb2312"));
                    list.add(nextLine1);
                    if (MyApplication.mReTureOrder.containsKey("商品编码")) {
                        if (pmcode.length() > 9) {
                            list.add(pmcode2.getBytes("gb2312"));
                            list.add(nextLine1);
                        }
                    }
                }

                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("合计数量: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add((totalnum + "").getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("扣除积分: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add((printBean.getData().getIntegralDeduct() + "").getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("退款总额: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + printBean.getData().getYSMoney()).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("退款方式: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getPayInfo().getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                String str = printBean.getData().getRemark() == null ? "无" : printBean.getData().getRemark();
                if (MyApplication.mReTureOrder.containsKey("备注")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("备    注: " + str).getBytes("gb2312"));
                }

                if (MyApplication.mReTureOrder.containsKey("会员卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员卡号: " + printBean.getData().getVCH_Card()).getBytes("gb2312"));
                }
                String facenum = printBean.getData().getVIP_FaceNumber() == null ? "无" : printBean.getData().getVIP_FaceNumber();
                if (facenum.equals("")) {
                    facenum = "无";
                }
                if (MyApplication.mReTureOrder.containsKey("卡面卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡面卡号: " + facenum).getBytes("gb2312"));
                }
                String memName = printBean.getData().getVIP_Name();
                if (memName == null || memName.equals("")) {
                    memName = "无";
                }

                if (MyApplication.mReTureOrder.containsKey("会员姓名")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员姓名: " + memName).getBytes("gb2312"));
                }
                if (MyApplication.mReTureOrder.containsKey("会员地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员地址: " + (printBean.getData().getVIPAddress() == null ? "" : printBean.getData().getVIPAddress())).getBytes("gb2312"));
                }
                if (MyApplication.mReTureOrder.containsKey("卡内余额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内余额: " + "￥" + printBean.getData().getVCH_Money()).getBytes("gb2312"));
                }
                if (MyApplication.mReTureOrder.containsKey("卡内积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内积分: " + printBean.getData().getVCH_Point()).getBytes("gb2312"));
                }
                if (MyApplication.mReTureOrder.containsKey("打印时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));
                }
                if (MyApplication.mReTureOrder.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mReTureOrder.get("商户电话")).getBytes("gb2312"));
                }
                if (MyApplication.mReTureOrder.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mReTureOrder.get("联系地址")).getBytes("gb2312"));
                }
                if (MyApplication.mReTureOrder.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mReTureOrder.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }
                if (MyApplication.mReTureOrder.containsKey("二维码") && MyApplication.SPXF_QR != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.SPTH_QR);
                    File file = (File) CacheData.restoreObject("SPTH_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                    list.add(center);
                }
                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(titlebigger);
                list.add("欢迎光临".getBytes("gb2312"));
                list.add(titlesmall);

                list.add(nextLine2);
                list.add(left);
                list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add(("商品名称" + bank).getBytes("gb2312"));
                list.add(("单价" + bank + bank).getBytes("gb2312"));
                list.add(("数量" + bank + bank).getBytes("gb2312"));
                list.add("小计".getBytes("gb2312"));
                list.add(boldOff);

                list.add(nextLine1);

                double totalnum = 0;
                for (int i = 0; i < goodList.size(); i++) {
                    double price = goodList.get(i).getPM_UnitPrice();
                    double num = goodList.get(i).getPM_Number();
                    totalnum += num;
                    double discount = goodList.get(i).getPM_Discount();
                    double total = DoubleMathUtil.mul(DoubleMathUtil.mul(price, num), discount);
                    list.add(left);
                    list.add(goodList.get(i).getPM_Name().getBytes("gb2312"));
                    list.add(nextLine1);
                    list.add((bank + "      ￥" + goodList.get(i).getPM_UnitPrice()).getBytes("gb2312"));
                    list.add((bank + bank + goodList.get(i).getPM_Number()).getBytes("gb2312"));
                    list.add((bank + bank + "￥" + Decima2KeeplUtil.stringToDecimal(goodList.get(i).getROD_DiscountPrice() + "")).getBytes("gb2312"));
                    list.add(nextLine1);
                }

                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("合计数量: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add((totalnum + "").getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("扣除积分: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add((printBean.getData().getIntegralDeduct() + "").getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("退款总额: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + printBean.getData().getYSMoney()).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("退款方式: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getPayInfo().getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));

                list.add(nextLine2);
                list.add(center);
                list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }
            return ESCUtil.byteMerger(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 蓝牙打印 一键加油
     *
     * @param printBean
     * @return
     */
    @Override
    public byte[] printBlueTooth_YJJY(Print_YJJY_Bean printBean) {
        try {
            List<byte[]> list = new ArrayList<>();
            String discount;
            if (printBean.getData().getDiscount() == 1 || printBean.getData().getDiscount() == 0) {
                discount = "不打折";
            } else {
                discount = String.valueOf(printBean.getData().getDiscount());
            }
            if (!MyApplication.mYJJYMap.isEmpty()) {
                if (MyApplication.mYJJYMap.containsKey("LOGO") && MyApplication.YJJY_LOGO != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.YJJY_LOGO);
                    File file = (File) CacheData.restoreObject("YJJY_LOGO");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                }
                if (MyApplication.mYJJYMap.containsKey("标题")) {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add((MyApplication.mYJJYMap.get("标题").getBytes("gb2312")));
                    list.add(titlesmall);
                } else {
                    list.add(nextLine1);
                    list.add(center);
                    list.add(titlebigger);
                    list.add(text_four);
                    list.add("欢迎光临".getBytes("gb2312"));
                    list.add(titlesmall);

                }
                if (MyApplication.mYJJYMap.containsKey("收银员")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("收 银 员: " + printBean.getData().getOilsObj().getCO_Creator()).getBytes("gb2312"));
                }
                if (MyApplication.mYJJYMap.containsKey("结账日期")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));
                }
                if (MyApplication.mYJJYMap.containsKey("流水单号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));
                }
                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));


                list.add(nextLine1);
                list.add(left);
                list.add(("油    品: " + printBean.getData().getOilsObj().getOM_Name()).getBytes("gb2312"));


                list.add(nextLine1);
                list.add(left);
                list.add(("数    量: " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getOilsObj().getCO_Number()))).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("油    价: " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getOilsObj().getCO_Price()))).getBytes("gb2312"));


                list.add(nextLine1);
                list.add(left);
                list.add(("消费金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getConsumeMoney())))
                        .getBytes("gb2312"));


                if (MyApplication.mYJJYMap.containsKey("赠送积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("赠送积分: " + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData()
                            .getIntegralAdd()))).getBytes("gb2312"));
                }


                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("应    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getOilsObj().getCO_OrderAmount()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("实    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("支付详情: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getOilsObj().getCO_PayWay().getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("找    零: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getOilsObj().getCO_ZLMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

//                String str =printBean.getData().getRemark()==null?"无":printBean.getData().getRemark();
//                if (MyApplication.mYJJYMap.containsKey("备注")) {
//                    list.add(nextLine1);
//                    list.add(left);
//                    list.add(("备    注: " + str).getBytes("gb2312"));
//                }
//                String str2;
//                if (printBean.getData().getEMName()==null||printBean.getData().getEMName().equals("")){
//                    str2 = "无";
//                }else {
//                    str2 = printBean.getData().getEMName();
//                }
//                if (MyApplication.mYJJYMap.containsKey("服务员工")) {
//                    list.add(nextLine1);
//                    list.add(left);
//                    list.add(("服务员工: " +str2).getBytes("gb2312"));
//                }
                if (MyApplication.mYJJYMap.containsKey("会员卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员卡号: " + printBean.getData().getVCH_Card()).getBytes("gb2312"));
                }

                String facenum = printBean.getData().getVIP_FaceNumber() == null ? "无" : printBean.getData().getVIP_FaceNumber();
                if (facenum.equals("")) {
                    facenum = "无";
                }
                if (MyApplication.mYJJYMap.containsKey("卡面卡号")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡面卡号: " + facenum).getBytes("gb2312"));
                }
                String memName = printBean.getData().getVIP_Name();
                if (memName == null || memName.equals("")) {
                    memName = "无";
                }
                if (MyApplication.mYJJYMap.containsKey("会员姓名")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员姓名: " + memName).getBytes("gb2312"));
                }
                if (MyApplication.mYJJYMap.containsKey("会员地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("会员地址: " + (printBean.getData().getVIPAddress() == null ? "" : printBean.getData().getVIPAddress())).getBytes("gb2312"));
                }
                if (MyApplication.mYJJYMap.containsKey("卡内余额")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内余额: " + "￥" + printBean.getData().getVCH_Money()).getBytes("gb2312"));
                }
                if (MyApplication.mYJJYMap.containsKey("卡内积分")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("卡内积分: " + printBean.getData().getVCH_Point()).getBytes("gb2312"));
                }
                if (MyApplication.mYJJYMap.containsKey("打印时间")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));
                }
                if (MyApplication.mYJJYMap.containsKey("商户电话")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("商户电话: " + MyApplication.mYJJYMap.get("商户电话")).getBytes("gb2312"));
                }
                if (MyApplication.mYJJYMap.containsKey("联系地址")) {
                    list.add(nextLine1);
                    list.add(left);
                    list.add(("联系地址: " + MyApplication.mYJJYMap.get("联系地址")).getBytes("gb2312"));
                }
                if (MyApplication.mYJJYMap.containsKey("脚注")) {
                    list.add(nextLine2);
                    list.add(center);
                    list.add((MyApplication.mYJJYMap.get("脚注")).getBytes("gb2312"));
                } else {
                    list.add(nextLine2);
                    list.add(center);
                    list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));
                }
                if (MyApplication.mYJJYMap.containsKey("二维码") && MyApplication.YJJY_QR != null) {
//                    Bitmap bitmap = ESCUtil.scaleImage(MyApplication.YJJY_QR);
                    File file = (File) CacheData.restoreObject("YJJY_QR");
                    Bitmap bFlag = BitmapFactory.decodeFile(file.getPath());
                    Bitmap bitmap = ESCUtil.scaleImage(bFlag);
                    list.add(nextLine1);
                    list.add(center);
                    list.add(ESCUtil.printBitmap(bitmap));
                    list.add(center);
                }
                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            } else {
                list.add(nextLine1);
                list.add(center);
                list.add(titlebigger);
                list.add("欢迎光临".getBytes("gb2312"));
                list.add(titlesmall);


                list.add(nextLine2);
                list.add(left);
                list.add(("收 银 员: " + printBean.getData().getCashier()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("结账日期: " + printBean.getData().getCheckoutDate()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("流水单号: " + printBean.getData().getOrderCode()).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("消费金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getConsumeMoney())))
                        .getBytes("gb2312"));

//                list.add(nextLine1);
//                list.add(left);
//                list.add(("会员折扣: " + discount).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("折后金额: " + "￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData()
                        .getYSMoney()))).getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("应    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getYSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("实    收: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getSSMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("支付详情: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(printBean.getData().getPayInfo().getBytes("gb2312"));//


                list.add(nextLine1);
                list.add(left);
                list.add(boldOn);
                list.add("找    零: ".getBytes("gb2312"));
                list.add(boldOff);
                list.add(("￥" + Decima2KeeplUtil.stringToDecimal(String.valueOf(printBean.getData().getZLMoney()))).getBytes("gb2312"));//

                list.add(nextLine1);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));

                list.add(nextLine1);
                list.add(left);
                list.add(("打印时间: " + mConsumeTime.format(new Date())).getBytes("gb2312"));

                list.add(nextLine2);
                list.add(center);
                list.add("谢谢惠顾,欢迎下次光临！".getBytes("gb2312"));

                list.add(nextLine4);
                list.add(left);
                list.add(mLine.getBytes("gb2312"));
                list.add(breakPartial);
                list.add(nextLine1);
            }
            return ESCUtil.byteMerger(list);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
