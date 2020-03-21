package com.zhiluo.android.yunpu.yslutils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.print.util.PrinterSetContentsImpl;
import com.zhiluo.android.yunpu.sms.jsonbean.SmsSwitch;

import com.zhiluo.android.yunpu.utils.CacheData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-04-04.
 */

public class YSLUtils {

    /**
     * 打印bean
     */
    private static  ReportMessageBean.DataBean.PrintSetBean printSetBean;
    /**
     * 短信开关
     */
    private static SmsSwitch smsSwitch;

    /**
     * @param condition condition取值如下，对应相应的权限
     *                  会员管理 会员充次,会员充值,删除会员,编辑会员,新增会员,编辑会员,删除会员,会员挂失,修改密码,会员换卡,会员延期,
     *                  商品消费,快速消费,计次消费,套餐消费,快捷收款
     *                  积分兑换,积分变动
     *                  密码管理
     *                  套餐管理,供应商管理,标签管理,用户管理,员工管理,等级管理,报表管理,礼品管理,商品管理,充值管理,报表管理,
     *                  库存盘点,商品入库,编辑店铺,自定义属性,优惠活动,参数设置 打印设置 编辑店铺  密码管理
     * @return  true，表示在condition功能下，有该功能的权限，false表示没有该功能的权限
     */
    public static boolean isOpenActivon(String condition) {
        boolean isAction = false;
        LoginUpbean mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        /**如果为空，点击会闪退  20191018 cy */
        if (mLoginBean == null) {
            return false;
        }
        /*********************** 20191018 cy */
        for (int i = 0; i < mLoginBean.getData().getMenuInfoList().size(); i++) {
            LoginUpbean.DataBean.MenuInfoListBean bean = mLoginBean.getData().getMenuInfoList().get(i);
            Li("---da---"+bean.getMM_Name());
        }
        if (mLoginBean != null) {
            if (mLoginBean.getData().getUM_IsAmin() == 1) {//UM_IsAmin==1表示超级管管理员
                isAction = true;
            } else {
                for (int i = 0; i < mLoginBean.getData().getMenuInfoList().size(); i++) {
                    LoginUpbean.DataBean.MenuInfoListBean bean = mLoginBean.getData().getMenuInfoList().get(i);
                    if (bean.getMM_Name().equals(condition)) {
                        isAction = true;
                    }
                }
            }
        }
        return isAction;
    }
    public static double siOutFiveIn(double d) {
        // 新方法，如果不需要四舍五入，可以使用RoundingMode.DOWN
        BigDecimal bg = new BigDecimal(d).setScale(2, RoundingMode.UP);
        return bg.doubleValue();
    }


    /**
     * @return ，
     * 获取开关
     */
    public static SmsSwitch.DataBean getSmsSwitch(String code) {
        if (smsSwitch == null) {
            smsSwitch = null;
        }
            smsSwitch = (SmsSwitch) CacheData.restoreObject("shortmessage");
        if (smsSwitch!=null){
            for (int i=0;i<smsSwitch.getData().size();i++){
                if (smsSwitch.getData().get(i).getST_Code().equals(code)){//商品消费011 快速消费 010 计次消费 012  礼品兑换 013 添加会员001  会员充值 002 会员充次003 积分变动007
                    return smsSwitch.getData().get(i);
                }
            }
        }
        return null;
    }

    /**
     * @return 获取系统打印开关bean
     * 这里不能用单例，单例静态变量在下次获取对象的时候依旧用上次的，引起的麻烦是：在保存设置后结算界面的打印按钮并没有变化，必须重登才生效
     */
    public static ReportMessageBean.DataBean.PrintSetBean getPrints() {
        if (printSetBean != null) {
            printSetBean = null; //用过的静态变量置空，内存紧张是，GC自动回收，不置空静态变量不容易回收
        }
        printSetBean = (ReportMessageBean.DataBean.PrintSetBean) CacheData.restoreObject("print");

        if (printSetBean != null){
            MyApplication.PRINTPAPER = printSetBean.getPS_PaperType();
            if (MyApplication.PRINTPAPER ==3){
                PrinterSetContentsImpl.mLine = "------------------------------------------------";
                PrinterSetContentsImpl.bank = "    ";
            }else if (MyApplication.PRINTPAPER ==2){
                PrinterSetContentsImpl.mLine = "--------------------------------";
                PrinterSetContentsImpl.bank = " ";
            }
        }
        return printSetBean;
    }

    /**
     * @param view 测量的控件
     * @return 控件宽度
     */
    public static int getViewWidth(View view) {
        int width1 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height1 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width1, height1);
        int width = view.getMeasuredWidth();
        return width;
    }

    /**
     * @param view 测量的控件
     * @return 控件高度
     */
    public static int getViewHeight(View view) {
        int width1 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height1 = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width1, height1);
        int height = view.getMeasuredHeight();
        return height;
    }

    /**
     * @return 获取屏幕的宽度
     */
    public static int getPhoneWidth() {
        WindowManager wm = (WindowManager) MyApplication.getmContext().getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        return width;
    }

    /**
     * @return 获取屏幕的高度
     */
    public static int getPhoneHeight() {
        WindowManager wm = (WindowManager) MyApplication.getmContext().getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        return height;
    }

    /**
     * @param bgAlpha  透明度
     * @param mContext ，上下文
     *                 设置屏幕的透明度，屏幕透明度0.0-1.0之间；0.0表示曲莫黑 0.5半透明 1表示完全透明
     */
    public static void setBackgroundAlpha(float bgAlpha, Context mContext) {
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) mContext).getWindow().setAttributes(lp);
    }


    /**
     * @param s 判断的字符串
     * @return true 是十六进制数，false 不是十六进制数
     */
    public static boolean is16(String s) {
        char a;
        boolean c = false;
        for (int i = 0; i < s.length(); i++) {
            a = s.charAt(i);
            if (a == 'A' || a == 'B' || a == 'C' || a == 'D' || a == 'E' || a == 'F') {
                c = true;
            } else {
                c = false;
            }
        }
        return c;
    }

    /**
     * @param strHex ，需要转换的字符串
     * @return 十进制数
     * 十六进制转换成十进制
     */
    public static int change16To10(String strHex) {
        int nResult = 0;
        if (!IsHex(strHex))
            return nResult;
        String str = strHex.toUpperCase();
        if (str.length() > 2) {
            if (str.charAt(0) == '0' && str.charAt(1) == 'X') {
                str = str.substring(2);
            }
        }
        int nLen = str.length();
        for (int i = 0; i < nLen; ++i) {
            char ch = str.charAt(nLen - i - 1);
            try {
                nResult += (GetHex(ch) * GetPower(16, i));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return nResult;
    }

    /**
     * @param strHex
     * @return 判断是否是16进制数
     */
    public static boolean IsHex(String strHex) {
        int i = 0;
        if (strHex.length() > 2) {
            if (strHex.charAt(0) == '0' && (strHex.charAt(1) == 'X' || strHex.charAt(1) == 'x')) {
                i = 2;
            }
        }
        for (; i < strHex.length(); ++i) {
            char ch = strHex.charAt(i);
            if ((ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'F') || (ch >= 'a' && ch <= 'f'))
                continue;
            return false;
        }
        return true;
    }

    /**
     * @param ch
     * @return
     * @throws Exception 计算16进制对应的数值
     */
    public static int GetHex(char ch) throws Exception {
        if (ch >= '0' && ch <= '9')
            return (int) (ch - '0');
        if (ch >= 'a' && ch <= 'f')
            return (int) (ch - 'a' + 10);
        if (ch >= 'A' && ch <= 'F')
            return (int) (ch - 'A' + 10);
        throw new Exception("error param");
    }

    /**
     * @param nValue
     * @param nCount
     * @return
     * @throws Exception 十六进制计算幂
     */
    public static int GetPower(int nValue, int nCount) throws Exception {
        if (nCount < 0)
            throw new Exception("nCount can't small than 1!");
        if (nCount == 0)
            return 1;
        int nSum = 1;
        for (int i = 0; i < nCount; ++i) {
            nSum = nSum * nValue;
        }
        return nSum;
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][35879]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    /**
     * @param email
     * @return 邮箱验证
     */
    public static boolean isEmail(String email) {
        boolean flag = false;
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            try {
                String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                Pattern regex = Pattern.compile(check);
                Matcher matcher = regex.matcher(email);
                flag = matcher.matches();
            } catch (Exception e) {
                flag = false;
            }
            return flag;
        }
    }

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
    //扫码支付返回码
    public static String payResult(String str){
        if (str.equals("410001")){
            return "转入退款";
        }else if (str.equals("410002")){
            return "用户未支付";
        }else if (str.equals("410003")){
            return "已关闭";
        }else if (str.equals("410005")){
            return "用户已撤销";
        }else if (str.equals("410006")){
            return "未支付支付超时";
        }else if (str.equals("410007")){
            return "支付失败";
        }else {
            return str;
        }
    }


}
