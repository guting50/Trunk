package com.zhiluo.android.yunpu.payment.lkl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import java.util.Calendar;

import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;

/**
 * 拉卡拉支付（银联卡刷卡、微信、支付宝扫码支付）
 * iauthor：Yc
 * date: 2017/6/25 10:07
 * email：jasoncheng9111@gmail.com
 */
@SuppressLint("WrongConstant")
public class PayLKL extends Activity
{
    public static final int RQ_LKLPAY = 0x01;

    /**
     * 刷卡或感应卡支付
     *
     * @param activity    跳转过来的Activity名称
     * @param orderNumber 订单编号
     * @param payAmount   支付总金额
     * @param orderInfo   订单具体信息（这里传消费界面的名称：比如从快速消费消费结算的就是快速消费，从会员充值结算的就是会员充值）
     * @param payType     支付方式 0：刷卡  1：扫码（微信 支付宝）
     * @param disposCode  交易处理码  000000：刷卡挥卡  660000:扫码（微信 支付宝）
     */
    public void pay4Lkl(Activity activity, String orderNumber, String payAmount, String orderInfo, String payType, String disposCode)
    {
        try
        {
            ComponentName componentName = new ComponentName("com.lkl.cloudpos.payment",
                    "com.lkl.cloudpos.payment.activity_integralrecoder_item.MainMenuActivity");
            Intent intent = new Intent();
            intent.setComponent(componentName);

            Bundle bundle = new Bundle();
            bundle.putString("msg_tp", "0200");//报文类型
            bundle.putString("pay_tp", payType);//支付方式
            bundle.putString("proc_tp", "00");//交易类型
            bundle.putString("proc_cd", disposCode);//交易处理码
            bundle.putString("amt", payAmount);//交易金额
            bundle.putString("order_no", orderNumber);//订单号
            bundle.putString("appid", activity.getPackageName());//调用者包名

            Calendar c = getInstance();
            String year = String.valueOf(c.get(YEAR));
            String month = String.valueOf(c.get(MONTH));
            String date = String.valueOf(c.get(DATE));
            String hour = String.valueOf(c.get(HOUR_OF_DAY));
            String minute = String.valueOf(c.get(MINUTE));
            String second = String.valueOf(c.get(SECOND));

            bundle.putString("time_stamp", year + month + date + hour + minute + second);//交易时间
            bundle.putString("order_info", orderInfo);//订单具体信息
            intent.putExtras(bundle);
            activity.startActivityForResult(intent, RQ_LKLPAY);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
