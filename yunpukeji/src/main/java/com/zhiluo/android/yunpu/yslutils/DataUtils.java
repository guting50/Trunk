package com.zhiluo.android.yunpu.yslutils;

import android.content.Context;

import com.zhiluo.android.yunpu.member.consume.activity.MemberRechargeActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * Created by ${YSL} on 2018-04-14.
 */

public class DataUtils {
    private static Calendar c = Calendar.getInstance();

    /**
     * 比较 当前时间和活动的时间大小
     *
     * @param startTime      ,活动开始时间
     * @param endTime，活动结束时间
     * @return 当前时间在活动时间范围内，返回true；不在则返回false；
     */
    public static boolean isOverTime(String date){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long overtime =  sdf.parse(date+"").getTime();
            long currentTime =  sdf.parse(sdf.format(System.currentTimeMillis())).getTime();
            long time = overtime- currentTime;
            if (time<0){
                return false;
            }else {
//                postVip(mMemberInfo.getVCH_Card());

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return true;

    }
    /**
     * 得到几天前的时间
     * @param day    ,
     * @return   返回 2018-04-14 11:03:43格式的时间
     */
    public static String getDateBefore(int day){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar now =Calendar.getInstance();
        now.setTime(new Date(System.currentTimeMillis()));
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return  simpleDateFormat.format(now.getTime());
    }

    /**
     * 得到几天后的时间
     * @param day      ,
     * @return   返回 2018-04-14 11:03:43格式的时间
     */
    public static String getDateAfter(int day){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar now =Calendar.getInstance();
        now.setTime(new Date(System.currentTimeMillis()));
        now.set(Calendar.DATE,now.get(Calendar.DATE)+day);
        return simpleDateFormat.format(now.getTime());
    }
    /**
     * @return 返回 2018-04-14 11:03:43格式的时间
     */
    public static String getDataTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

    /**
     * @return 获取星期，如星期三 ，返回值 : "3"
     * 特别说明：Java默认情况下为美国的星期，在美国，星期日为一周的第一天	SUN	MON	TUE	WED	THU	FRI	SAT
     * DAY_OF_WEEK返回值	1	2	3	4	5	6	7，和国内不一样；要想输出和国内一样，
     * 第一个：要么0，然后在周日为-1的时候做处理，把它设为7
     * 第二个： Locale.setDefault(Locale.CHINA);Locale换成中国的，但是第二个好像有点问题，所以用第一个稍微处理下就好了，也不麻烦
     */
    public static String getW() {
        int mDay = getCalendar().get(Calendar.DAY_OF_WEEK) - 1;
        if (mDay == 0) {
            mDay = 7;
        }
        return mDay + "";
    }

    /**
     * @return 获取日，如9号 ，返回值 : "9"
     */
    public static String getD() {
        String mDay = getCalendar().get(Calendar.DAY_OF_MONTH) + "";
        return mDay;
    }

    /**
     * @return 获取年月日，如2018年3月9号 ，返回值 : "201839"
     */
    public static String getYMD() {
        String mYear = getCalendar().get(Calendar.YEAR) + "";
        String mMonth = getCalendar().get(Calendar.MONTH) + 1 + "";
        String mDay = getCalendar().get(Calendar.DAY_OF_MONTH) + "";
        return mYear + mMonth + mDay;
    }

    public static String checkNull(String str){
        String string = str ==null?"":str;
        return string;
    }

    /**
     * @return 获取月日，如3月9号 ，返回值 : "39"
     */
    public static String getMD() {
        String mMonth = getCalendar().get(Calendar.MONTH) + 1 + "";
        String mDay = getCalendar().get(Calendar.DAY_OF_MONTH) + "";
        return mMonth + mDay;
    }

    private static Calendar getCalendar() {
        if (c == null) {
            c = Calendar.getInstance();
        }
        return c;
    }
}
