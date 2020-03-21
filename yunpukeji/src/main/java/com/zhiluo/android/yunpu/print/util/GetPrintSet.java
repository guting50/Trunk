package com.zhiluo.android.yunpu.print.util;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.print.bean.PrintSetBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.print.bean.PrintParamSetBean;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.List;

/**
 * 获取打印设置
 * 作者：罗咏哲 on 2017/8/18 14:05.
 * 邮箱：137615198@qq.com
 */

public class GetPrintSet {
    /**
     * 获取打印设置
     */
    public static void getPrintParamSet() {
        MyApplication.mTimesRechargeMap.clear();
        MyApplication.mRechargeMap.clear();
        MyApplication.mGoodsConsumeMap.clear();
        MyApplication.mTimesConsumeMap.clear();
        MyApplication.mIntegralExchangeMap.clear();
        MyApplication.mFastConsumeMap.clear();
        MyApplication.mXSXFonsumeMap.clear();
        MyApplication.mCardOpenMap.clear();
        MyApplication.mHandOverMap.clear();
        MyApplication.mGoodsIn.clear();
        MyApplication.mGoodsOut.clear();
        MyApplication.mReTureOrder.clear();
        MyApplication.mYJJYMap.clear();



        RequestParams params = new RequestParams();
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                PrintParamSetBean bean = CommonFun.JsonToObj(responseString, PrintParamSetBean.class);
                for (int i = 0; i < bean.getData().size(); i++) {
                    List<PrintParamSetBean.DataBean.TemplateItemsBean> itemsBean = bean.getData().get(i).getTemplateItems();
                    if (itemsBean!=null) {
                        //会员充次
                        if ("HYCC".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "HYCC", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "HYCC", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "HYCC", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "HYCC", false);
                                    }
                                }
                                MyApplication.mTimesRechargeMap.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }
                        //会员充值
                        if ("HYCZ".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "HYCZ", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "HYCZ", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "HYCZ", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "HYCZ", false);
                                    }
                                }
                                MyApplication.mRechargeMap.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }
                        //商品消费
                        if ("SPXF".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "SPXF", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "SPXF", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "SPXF", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "SPXF", false);
                                    }
                                }
                                MyApplication.mGoodsConsumeMap.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }
                        //计次消费
                        if ("JCXF".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "JCXF", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "JCXF", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "JCXF", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "JCXF", false);
                                    }
                                }
                                MyApplication.mTimesConsumeMap.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }
                        //积分兑换
                        if ("JFDH".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "JFDH", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "JFDH", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "JFDH", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "JFDH", false);
                                    }
                                }
                                MyApplication.mIntegralExchangeMap.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }
                        //快速消费
                        if ("KSXF".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "KSXF", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "KSXF", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "KSXF", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "KSXF", false);
                                    }
                                }
                                MyApplication.mFastConsumeMap.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }
                        //限时消费
                        if ("HYDJ".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "XSXF", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "XSXF", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "XSXF", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "XSXF", false);
                                    }
                                }
                                MyApplication.mXSXFonsumeMap.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }

                        //会员开卡
                        if ("HYKK".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "KYKK", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "HYKK", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "HYKK", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "HYKK", false);
                                    }
                                }
                                MyApplication.mCardOpenMap.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }

                        //交班
                        if ("JB".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "JB", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "JB", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "JB", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "JB", false);
                                    }
                                }
                                MyApplication.mHandOverMap.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }

                        //入库
                        if ("RKJLXQ".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "RK", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "RK", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "RK", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "RK", false);
                                    }
                                }
                                MyApplication.mGoodsIn.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }

                        //出库
                        if ("CKJLXQ".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "CK", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "CK", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "CK", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "CK", false);
                                    }
                                }
                                MyApplication.mGoodsOut.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }
                        //商品退货
                        if ("SPTH".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "SPTH", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "SPTH", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "SPTH", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "SPTH", false);
                                    }
                                }
                                MyApplication.mReTureOrder.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }

                        //一键加油
                        if ("1JJY".equals(bean.getData().get(i).getPT_Code())) {
                            for (int j = 0; j < itemsBean.size(); j++) {
                                if ("LOGO".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "YJJY", true);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "YJJY", true);
                                    }
                                }
                                if ("二维码".equals(itemsBean.get(j).getItemName())) {
                                    if (itemsBean.get(j).getItemValue().contains("http")) {
                                        HttpHelper.getBitmap(itemsBean.get(j).getItemValue(), "YJJY", false);
                                    } else {
                                        HttpHelper.getBitmap(MyApplication.IMAGE_URL + itemsBean.get(j).getItemValue(),
                                                "YJJY", false);
                                    }
                                }
                                MyApplication.mYJJYMap.put(itemsBean.get(j).getItemName(), itemsBean.get(j).getItemValue());
                            }
                        }

                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MyApplication.getmContext(), msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(MyApplication.getmContext(),HttpAPI.API().PRINT_SET,params, callBack);

    }

    /**
     * 获取打印设置
     */
    public static void getPrintSet() {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookies = new PersistentCookieStore(MyApplication.getmContext());
        client.setCookieStore(cookies);
        client.post(HttpAPI.API().PRINTSET, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                PrintSetBean printSetBean = CommonFun.JsonToObj(responseString, PrintSetBean.class);
                if (printSetBean.getData().getPS_IsEnabled() == 1) {
                    MyApplication.PRINT_IS_OPEN = true;
                } else {
                    MyApplication.PRINT_IS_OPEN = false;
                }
                if (printSetBean.getData() != null && printSetBean.getData().getPrintTimesList() != null) {
                    for (int i = 0; i < printSetBean.getData().getPrintTimesList().size(); i++) {
                        PrintSetBean.DataBean.PrintTimesListBean bean = printSetBean.getData().getPrintTimesList().get(i);
                        if ("HYCZ".equals(bean.getPT_Code())) {
                            MyApplication.HYCZ_PRINT_TIMES = bean.getPT_Times();
                        }
                        if ("HYCC".equals(bean.getPT_Code())) {
                            MyApplication.HYCC_PRINT_TIMES = bean.getPT_Times();
                        }
                        if ("SPXF".equals(bean.getPT_Code())) {
                            MyApplication.SPXF_PRINT_TIMES = bean.getPT_Times();
                        }

                        if ("YJJY".equals(bean.getPT_Code())) {
                            MyApplication.YJJY_PRINT_TIMES = bean.getPT_Times();
                        }

                        if ("KSXF".equals(bean.getPT_Code())) {
                            MyApplication.KSXF_PRINT_TIMES = bean.getPT_Times();
                        }
                        if ("JCXF".equals(bean.getPT_Code())) {
                            MyApplication.JCXF_PRINT_TIMES = bean.getPT_Times();
                        }
                        if ("JFDH".equals(bean.getPT_Code())) {
                            MyApplication.JFDH_PRINT_TIMES = bean.getPT_Times();
                        }
                        if ("TCXF".equals(bean.getPT_Code())) {
                            MyApplication.TCXF_PRINT_TIMES = bean.getPT_Times();
                        }
                        if ("HYKK".equals(bean.getPT_Code())) {
                            MyApplication.HYKK_PRINT_TIMES = bean.getPT_Times();
                        }
                        if ("JB".equals(bean.getPT_Code())) {
                            MyApplication.JB_PRINT_TIMES = bean.getPT_Times();
                        }
                        if ("RK".equals(bean.getPT_Code())) {
                            MyApplication.RK_PRINT_TIMES = bean.getPT_Times();
                        }
                        if ("CK".equals(bean.getPT_Code())) {
                            MyApplication.CK_PRINT_TIMES = bean.getPT_Times();
                        }
                        if ("SPTH".equals(bean.getPT_Code())) {
                            MyApplication.SPTH_PRINT_TIMES = bean.getPT_Times();
                        }
                        if ("XSXF".equals(bean.getPT_Code())) {
                            MyApplication.XSXF_PRINT_TIMES = bean.getPT_Times();
                        }
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                MyApplication.PRINT_IS_OPEN = false;
                CustomToast.makeText(MyApplication.getmContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
