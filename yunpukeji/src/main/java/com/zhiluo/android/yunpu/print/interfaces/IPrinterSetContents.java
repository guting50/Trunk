package com.zhiluo.android.yunpu.print.interfaces;

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
import com.zhiluo.android.yunpu.ui.activity.handduty.HandDutyBean;

/**
 * 打印小票接口
 * iauthor：Yc
 * date: 2017/7/26 09:52
 * email：jasoncheng9111@gmail.com
 */
public interface IPrinterSetContents {
    /********************快速消费*******************/

    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_KSXF(Print_KSXF_Bean printBean);

    /**
     * basewin_pos打印小票  世麦机器
     */
    void printBaseWinPOS_KSXF(Print_KSXF_Bean printBean);

    /**
     * 拉卡拉打印小票  卡拉卡a8机器
     */
    void printLKL_KSXF(Print_KSXF_Bean printBean);


    /********************商品消费*******************/

    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_SPXF(Print_SPXF_Bean printBean);

    /**
     * basewin_pos打印小票  世麦机器
     */
    void printBaseWinPOS_SPXF(Print_SPXF_Bean printBean);

    /**
     * 拉卡拉打印小票  卡拉卡a8机器
     */
    void printLKL_SPXF(Print_SPXF_Bean printBean);


    /********************会员充值*******************/

    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_HYCZ(Print_HYCZ_Bean printBean);

    /**
     * basewin_pos打印小票  世麦机器
     */
    void printBaseWinPOS_HYCZ(Print_HYCZ_Bean printBean);

    /**
     * 拉卡拉打印小票  卡拉卡a8机器
     */
    void printLKL_HYCZ(Print_HYCZ_Bean printBean);


    /********************会员充次*******************/

    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_HYCC(Print_HYCC_Bean printBean);

    /**
     * basewin_pos打印小票  世麦机器
     */
    void printBaseWinPOS_HYCC(Print_HYCC_Bean printBean);

    /**
     * 拉卡拉打印小票  卡拉卡a8机器
     */
    void printLKL_HYCC(Print_HYCC_Bean printBean);


    /********************计次消费*******************/

    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_JCXF(Print_JCXF_Bean printBean);

    /**
     * basewin_pos打印小票  世麦机器
     */
    void printBaseWinPOS_JCXF(Print_JCXF_Bean printBean);

    /**
     * 拉卡拉打印小票  卡拉卡a8机器
     */
    void printLKL_JCXF(Print_JCXF_Bean printBean);

    /********************限时消费*******************/

    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_XSXF(XSXF_Success_Bean printBean);


    /********************积分兑换*******************/

    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_JFDH(Print_JFDH_Bean printBean);

    /**
     * basewin_pos打印小票  世麦机器
     */
    void printBaseWinPOS_JFDH(Print_JFDH_Bean printBean);

    /**
     * 拉卡拉打印小票  卡拉卡a8机器
     */
    void printLKL_JFDH(Print_JFDH_Bean printBean);



    /********************会员开卡*******************/
    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_HYKK(Print_HYKK_Bean printBean);

    /********************商品入库*******************/
    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_RK(RK_Success_Bean printBean);

    /********************商品出库*******************/
    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_CK(CK_Success_Bean printBean);


    /********************交班*******************/
    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_JB(JB_Success_Bean printBean);

    /********************商品退货*******************/

    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_SPTH(Print_SPTH_Bean printBean);

    /**
     * 蓝牙打印sunmi机器
     */
    byte[] printBlueTooth_YJJY(Print_YJJY_Bean printBean);


}
