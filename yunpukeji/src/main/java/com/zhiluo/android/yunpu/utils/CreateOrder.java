package com.zhiluo.android.yunpu.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 生成订单号
 * 作者：罗咏哲 on 2017/9/21 14:51.
 * 邮箱：137615198@qq.com
 */

public class CreateOrder {
    public static String createOrder(String type) {
        StringBuilder builder = new StringBuilder(type);
        DateFormat df = new SimpleDateFormat("yyMMddHHmmss");
        builder.append(df.format(new Date()));//获取年月日时分秒
        builder.append(new Random().nextInt(9000) + 1000);//获取一个四位随机数
        return builder.toString();
    }

}
