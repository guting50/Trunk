package com.zhiluo.android.yunpu.utils;

import android.os.RemoteException;

import com.lkl.cloudpos.aidl.magcard.MagCardListener;
import com.lkl.cloudpos.aidl.magcard.TrackData;

import static com.loopj.android.http.AsyncHttpClient.log;
import static com.zhiluo.android.yunpu.config.MyApplication.magCardDev;

/**
 * 获取磁条卡明文数据
 * iauthor：Cheng
 * date: 2017/5/5 15:30
 * email：jasoncheng9111@gmail.com
 */
public class GetTrackDataUtil
{
    private static String cardNumber = "";

    /**
     *
     * 获取磁条卡明文数据
     * author Yuan Cheng
     * blog yuancheng91.top
     * email jasoncheng9111@163.com
     * created 2017/5/5 15:21
     */
    public static String getTrackData()
    {
        if (magCardDev != null)
        {
            try
            {
//                log.d("TAG", "请刷卡");
                magCardDev.searchCard(60000, new MagCardListener.Stub()
                {
                    @Override
                    public void onTimeout() throws RemoteException
                    {
                        log.d("TAG", "刷卡超时");
                    }

                    @Override
                    public void onSuccess(TrackData trackData)
                            throws RemoteException
                    {
                        log.d("TAG", "刷卡成功");
                        log.d("TAG", "1磁道数据" + trackData.getFirstTrackData());
                        log.d("TAG", "2磁道数据" + trackData.getSecondTrackData());
                        log.d("TAG", "3磁道数据" + trackData.getThirdTrackData());
                        log.d("TAG", "卡号数据" + trackData.getCardno());
                        log.d("TAG", "卡片有效期" + trackData.getExpiryDate());
                        log.d("TAG", "格式化磁道数据" + trackData.getFormatTrackData());
                        log.d("TAG", "卡片服务码" + trackData.getServiceCode());
                        if (!trackData.getSecondTrackData().equals(""))
                        {
                            cardNumber = trackData.getSecondTrackData().toString();
                        }
                    }

                    @Override
                    public void onGetTrackFail() throws RemoteException
                    {
                        log.d("TAG", "刷卡失败");
                    }

                    @Override
                    public void onError(int arg0) throws RemoteException
                    {
                        log.d("TAG", "刷卡错误，错误码为" + arg0);
                    }

                    @Override
                    public void onCanceled() throws RemoteException
                    {
                        log.d("TAG", "刷卡被取消");
                    }
                });
            }
            catch (RemoteException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                log.d("TAG", "刷卡异常");
            }
        }

        return cardNumber;
    }
}
