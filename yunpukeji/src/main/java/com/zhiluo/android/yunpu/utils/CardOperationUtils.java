package com.zhiluo.android.yunpu.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.EditText;

import com.basewin.interfaces.OnDetectListener;
import com.basewin.log.LogUtil;
import com.basewin.services.CardBinder;
import com.basewin.services.ServiceManager;
import com.google.gson.Gson;
import com.lkl.cloudpos.aidl.magcard.MagCardListener;
import com.lkl.cloudpos.aidl.magcard.TrackData;
import com.lkl.cloudpos.util.HexUtil;
import com.pos.sdk.card.PosCardInfo;
import com.sunmi.pay.hardware.aidl.bean.CardInfo;
import com.sunmi.pay.hardware.aidl.readcard.ReadCardCallback;
import com.sunmi.payservice.hardware.aidl.bean.PayCardInfo;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.util.Timer;
import java.util.TimerTask;

import static com.zhiluo.android.yunpu.config.MyApplication.IS_BASEWIN_POS_DEVICE;
import static com.zhiluo.android.yunpu.config.MyApplication.IS_LAKALA_POS_DEVICE;
import static com.zhiluo.android.yunpu.config.MyApplication.IS_SUNMI_POS_P1_DEVICE;
import static com.zhiluo.android.yunpu.config.MyApplication.RF_Card;
import static com.zhiluo.android.yunpu.config.MyApplication.TAG;
import static com.zhiluo.android.yunpu.config.MyApplication.mHardwareOpt;
import static com.zhiluo.android.yunpu.config.MyApplication.magCardDev;
import static com.zhiluo.android.yunpu.config.MyApplication.sReadCardOpt;
import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * pos机器卡片的操作
 * iauthor：Yc
 * date: 2017/7/6 10:44
 * email：jasoncheng9111@gmail.com
 */
public class CardOperationUtils extends Activity {
    private Context mContext;
    private EditText mEditText;
    private Handler handler_lkl_RfCard = new Handler();
    private Handler handler_lkl_MagCard = new Handler();
    private Handler handler_sunmi_ReadCard = new Handler();
    private Handler handler_basewin_ReadCard = new Handler();
    private CardBinder mCardBinder;
    private SoundPool soundPool;
    private String mCardNumber;
    private String mMifareCardNumber;


    public CardOperationUtils() {
    }

    public CardOperationUtils(Context context, EditText editText) {
        this.mContext = context;
        this.mEditText = editText;
        soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        soundPool.load(mContext, R.raw.swipingcard_voice, 1);


        /**
         * SUNMI
         */
        if (IS_SUNMI_POS_P1_DEVICE)
            handler_sunmi_ReadCard.post(runnable_sunmi_ReadCard);

        /**
         * 拉卡拉
         */
        if (IS_LAKALA_POS_DEVICE) {
            handler_lkl_RfCard.post(runnable_lkl_RfCard);//LKL感应卡

            handler_lkl_MagCard.postDelayed(runnable_lkl_MagCard, 100);//LKL刷卡
        }

        /**
         * BASEWIN 世麦
         */
        if (IS_BASEWIN_POS_DEVICE) {
            initCard();
            handler_sunmi_ReadCard.post(runnable_basewin_ReadCard);
        }
    }

    /**
     * 刷卡成功后的提示音
     */
    @SuppressLint("HandlerLeak")
    private Handler voiceHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            new Thread(voiceRunnable).start();

        }
    };

    /**
     * 刷卡成功后的播放音频
     */
    private Runnable voiceRunnable = new Runnable() {
        @Override
        public void run() {

            soundPool.play(1, 1, 1, 0, 0, 1);
        }
    };

    /**
     * SUNMI 检卡 读卡
     */
    private Runnable runnable_sunmi_ReadCard = new Runnable() {
        @Override
        public void run() {
            if (IS_SUNMI_POS_P1_DEVICE) {
                try {
                    /**
                     * 7：（磁条卡、IC卡、NFC卡）
                     * 100：超时 秒为单位
                     */
                    //PIN
                    if (MyApplication.SUNMI_DEVICE_MODEL != null && MyApplication.SUNMI_DEVICE_MODEL.equals("P1N")) {
                        mHardwareOpt.checkCard(1 | 8, mReadCardCallback, 10);
                    } else {//P10
                        sReadCardOpt.readCard(7, readCardCallback, 100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * SUNMI  P1N
     * 检卡回调
     */
    private com.sunmi.payservice.hardware.aidl.ReadCardCallback mReadCardCallback = new com.sunmi.payservice.hardware.aidl.ReadCardCallback.Stub() {

        @Override
        public void onCardDetected(final PayCardInfo payCardInfo) throws RemoteException {
            voiceHandler.sendEmptyMessage(0);//调用刷卡成功的音频
            //解析并读取卡号
            Message message = new Message();
            message.what = 0x01;
            message.obj = payCardInfo;
            mmHandler.sendMessage(message);
            handler_sunmi_ReadCard.postDelayed(runnable_sunmi_ReadCard, 100);
        }

        @Override
        public void onError(int i, String s) throws RemoteException {
            Log.e(TAG, "onError:" + i + " " + s);
            handler_sunmi_ReadCard.postDelayed(runnable_sunmi_ReadCard, 100);
        }

        @Override
        public void onStartCheckCard() throws RemoteException {
            Log.e(TAG, "onStartCheckCard:正在检卡");
        }
    };


    @SuppressLint("HandlerLeak")
    private Handler mmHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Le("进入mmhandler");
            switch (msg.what) {
                case 0x01:
                    PayCardInfo payCardInfo = (PayCardInfo) msg.obj;
                    if (payCardInfo.cardType == 8) {
                        /***************P1N 感应卡***********************/
                        /**读卡结果  {"cardType":8,"uuid":"4942FA29"}*/
                        String uuid = payCardInfo.uuid;
                        //取每两位十六进制逆向排列为市面上的通用卡号
                        String common_uuid = uuid.substring(6, 8) + uuid.substring(4, 6) + uuid.substring(2, 4) + uuid.substring(0, 2);
                        //通用卡号
                        mCardNumber = String.valueOf(Long.parseLong(common_uuid, 16));
                        //非通用卡号
                        mMifareCardNumber = String.valueOf(Long.parseLong(payCardInfo.uuid, 16));
                        MyApplication.VIP_CARD =  mMifareCardNumber;
                        /**
                         * 正式使用时，mCardNumber是市面上90%读卡器识别出来的卡号
                         * mMifareCardNumber是市面上少数10%读卡器识别出来的卡号，请自行更换。
                         * 默认显示通用卡号
                         */
//                        if (mMifareCardNumber !=null &&mMifareCardNumber.length()>9){
//                            mEditText.setText(mMifareCardNumber);
//                        }else if (mMifareCardNumber !=null){
//                            mEditText.setText("0"+mMifareCardNumber);
//                        }
                        while (mMifareCardNumber !=null &&mMifareCardNumber.length()<10){
                            mMifareCardNumber = "0"+mMifareCardNumber;
                        }
                        mEditText.setText(mMifareCardNumber);

                        Le("uuid===" + uuid + "-----common_uuid---" + common_uuid);
                    } else {
                        /**************P1N 磁条卡*********************/
                        /**读卡结果 {"cardType":1,"track2":"021220"}*/
                        mCardNumber = payCardInfo.track2;
//                        if (mCardNumber !=null &&mCardNumber.length()>9){
//                            mEditText.setText(mCardNumber);
//                        }else if (mCardNumber !=null){
//                            mEditText.setText("0"+mCardNumber);
//                        }
                        MyApplication.VIP_CARD =  mCardNumber;

//                        while (mCardNumber !=null &&mCardNumber.length()<10){
//                            mCardNumber = "0"+mCardNumber;
//                        }
                        mEditText.setText(mCardNumber);

                    }
                    break;
                case 0x02:
                    CardInfo cardInfo = (CardInfo) msg.obj;
                    if (cardInfo.isIC) {
                        /***************P10 感应卡***********************/
                        /** 读卡结果 {"cardType":4,"isIC":true,"type":"TYPE_A","uuid":"4942FA29"}**/
                        String uuid = cardInfo.uuid;
                        //取每两位十六进制逆向排列为市面上的通用卡号
                        String common_uuid = uuid.substring(6, 8) + uuid.substring(4, 6) + uuid.substring(2, 4) + uuid.substring(0, 2);
                        //通用卡号
                        mCardNumber = String.valueOf(Long.parseLong(common_uuid, 16));
                        //非通用卡号
                        mMifareCardNumber = String.valueOf(Long.parseLong(cardInfo.uuid, 16));
                        MyApplication.VIP_CARD =  mMifareCardNumber;

                        /**
                         * 正式使用时，mCardNumber是市面上90%读卡器识别出来的卡号
                         * mMifareCardNumber是市面上少数10%读卡器识别出来的卡号，请自行更换。
                         * 默认显示通用卡号
                         */
//                        if (mMifareCardNumber !=null &&mMifareCardNumber.length()>9){
//                            mEditText.setText(mMifareCardNumber);
//                        }else if (mMifareCardNumber !=null){
//                            mEditText.setText("0"+mMifareCardNumber);
//                        }

                        while (mMifareCardNumber !=null &&mMifareCardNumber.length()<10){
                            mMifareCardNumber = "0"+mMifareCardNumber;
                        }
                        mEditText.setText(mMifareCardNumber);
                        Le("uuid===" + uuid + "-----common_uuid---" + common_uuid);
                    } else {
                        /***************P10 磁条卡*********************/
                        /**读卡结果 {"cardNo":"021220","cardType":1,"expiryDate":"￿￿￿￿","isIC":false,"track2":"021220"}*/
                        mCardNumber = cardInfo.track2;
                        MyApplication.VIP_CARD =  mCardNumber;
//                        while (mCardNumber !=null &&mCardNumber.length()<10){
//                            mCardNumber = "0"+mCardNumber;
//                        }
                        mEditText.setText(mCardNumber);
//                        if (mCardNumber !=null &&mCardNumber.length()>9){
//                            mEditText.setText(mCardNumber);
//                        }else if (mCardNumber !=null){
//                            mEditText.setText("0"+mCardNumber);
//                        }
//                        mEditText.setText(mCardNumber);
                    }
                    break;
            }
        }
    };
    /**
     * SUNMI检卡回调
     * 刷磁条卡、IC卡、插卡、RF卡、M1卡、挥卡感应
     */
    private ReadCardCallback readCardCallback = new ReadCardCallback.Stub() {

        @Override
        public void onStartReadCard() throws RemoteException {
        }

        /**
         * 刷卡操作的回调
         * @param cardInfo
         * @throws RemoteException
         *           磁卡
         */
        @Override
        public void onFindMAGCard(final CardInfo cardInfo) throws RemoteException {
            Log.d("TAG", "onFindMAGCard成功");
            Le(new Gson().toJson(cardInfo));
            voiceHandler.sendEmptyMessage(0);//调用刷卡成功的音频
            //解析并读取卡号
            Message message = new Message();
            message.what = 0x02;
            message.obj = cardInfo;
            mmHandler.sendMessage(message);
            handler_sunmi_ReadCard.postDelayed(runnable_sunmi_ReadCard, 100);
        }

        /**
         * 感应卡操作回调
         * @param cardInfo
         * @throws RemoteException
         *              感应卡
         */
        @Override
        public void onFindNFCCard(final CardInfo cardInfo) throws RemoteException {
            Log.d("TAG", "onFindNFCCard成功");
            Le(new Gson().toJson(cardInfo));
            voiceHandler.sendEmptyMessage(0);//调用刷卡成功的音频
            //解析并读取卡号
            Message message = new Message();
            message.what = 0x02;
            message.obj = cardInfo;
            mmHandler.sendMessage(message);
            handler_sunmi_ReadCard.postDelayed(runnable_sunmi_ReadCard, 500);
        }

        /**
         * 插IC卡操作回调
         * @param cardInfo
         * @throws RemoteException
         */
        @Override
        public void onFindICCard(final CardInfo cardInfo) throws RemoteException {
            Log.d("TAG", "onFindICCard成功");
            Le(new Gson().toJson(cardInfo));
            voiceHandler.sendEmptyMessage(0);//调用刷卡成功的音频
            //解析并读取卡号
            Message message = new Message();
            message.what = 0x02;
            message.obj = cardInfo;
            mmHandler.sendMessage(message);
            handler_sunmi_ReadCard.postDelayed(runnable_sunmi_ReadCard, 100);
        }

        /**
         * 读卡失败
         * @param i
         * @throws RemoteException
         */
        @Override
        public void onError(int i) throws RemoteException {
            Log.d("TAG", "读卡失败");
            handler_sunmi_ReadCard.postDelayed(runnable_sunmi_ReadCard, 100);
        }

        /**
         * 读卡超时
         * @throws RemoteException
         */
        @Override
        public void onTimeOut() throws RemoteException {
            Log.d("TAG", "读卡超时");
            handler_sunmi_ReadCard.postDelayed(runnable_sunmi_ReadCard, 100);
        }
    };

    /**
     * 拉卡拉刷卡
     */
    private Runnable runnable_lkl_MagCard = new Runnable() {
        @Override
        public void run() {
            try {
                //刷卡磁条卡
                if (MyApplication.MAG_CARD) {
                    getTrackData();
                    handler_lkl_MagCard.postDelayed(runnable_lkl_MagCard, 200);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 获取拉卡拉磁条卡明文数据
     * author Yuan Cheng
     * created 2017/5/5 15:21
     */
    private void getTrackData() {
        if (magCardDev != null) {
            try {
                magCardDev.searchCard(60000, new MagCardListener.Stub() {
                    @Override
                    public void onTimeout() throws RemoteException {
                        Log.d("TAG", "刷卡超时");
                        handler_lkl_MagCard.postDelayed(runnable_lkl_MagCard, 200);
                    }

                    @Override
                    public void onSuccess(final TrackData trackData) throws RemoteException {
                        Log.d("TAG", "刷卡成功");
                        Log.d("TAG", "2磁道数据" + trackData.getSecondTrackData());
                        if (!trackData.getSecondTrackData().equals("")) {
                            voiceHandler.sendEmptyMessage(0);//调用刷卡成功的音频

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    String str = String.valueOf(YSLUtils.change16To10(trackData.getSecondTrackData()));
                                    MyApplication.VIP_CARD =  str;

//                                    while (str!=null &&str.length()<10){
//                                        str = "0"+str;
//                                    }
                                    mEditText.setText("" + str);

                                }
                            });

                            handler_lkl_MagCard.postDelayed(runnable_lkl_MagCard, 200);
                        }
                    }

                    @Override
                    public void onGetTrackFail() throws RemoteException {
                        Log.d("TAG", "刷卡失败");
                        handler_lkl_MagCard.postDelayed(runnable_lkl_MagCard, 200);
                    }

                    @Override
                    public void onError(int arg0) throws RemoteException {
                        Log.d("TAG", "刷卡错误，错误码为" + arg0);
                        handler_lkl_MagCard.postDelayed(runnable_lkl_MagCard, 200);
                    }

                    @Override
                    public void onCanceled() throws RemoteException {
                        Log.d("TAG", "刷卡被取消");
                        handler_lkl_MagCard.postDelayed(runnable_lkl_MagCard, 200);
                    }
                });
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Log.d("TAG", "刷卡异常");
                handler_lkl_MagCard.postDelayed(runnable_lkl_MagCard, 200);
            }
        }
    }

    /**
     * 拉卡拉感应卡
     */
    private Runnable runnable_lkl_RfCard = new Runnable() {
        @Override
        public void run() {
            try {
                //非接触式感应卡
                if (MyApplication.RFCARD_DEV) {
                    //判断卡片是否存在
                    if (RF_Card.isExist()) {
                        //获得复位码
                        byte[] data = RF_Card.reset(RF_Card.getCardType());

                        if (data != null) {
                            Message msg = new Message();
                            Bundle bundle = new Bundle();
                            bundle.putByteArray("data", data);
                            msg.setData(bundle);
                            mHandler.sendMessage(msg);
                        }

                    }

                    handler_lkl_RfCard.postDelayed(runnable_lkl_RfCard, 100);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            try {
                Bundle bundle = msg.getData();
                final byte[] data = bundle.getByteArray("data");
                if (!HexUtil.bcd2str(data).equals("")) {
                    voiceHandler.sendEmptyMessage(0);//调用刷卡成功的音频
                    String string = String.valueOf(YSLUtils.change16To10(Long.parseLong(HexUtil.bcd2str(data).substring(4, 12), 16) + ""));
                    MyApplication.VIP_CARD =  string;
                    while (string!=null &&string.length()<10){
                        string = "0"+string;
                    }
                    mEditText.setText("" + string);

//                    if (string != null && string.length() > 9) {
//                        mEditText.setText(string);
//                    } else {
//                        mEditText.setText("0" + string);
//                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 世麦pos 感应卡、刷卡
     */
    private Runnable runnable_basewin_ReadCard = new Runnable() {
        @Override
        public void run() {
            try {
                Log.d(TAG, "resetCardresetCardresetCardresetCardresetCardresetCard: ");
                mCardBinder.resetCard();
                mCardBinder.openM1_MagAndDetect(60000, new OnDetectListener() {
                    @Override
                    public void onSuccess(int i) {
                        PosCardInfo pos = new PosCardInfo();
                        mCardBinder.getCardInfo(i, pos);
                        if (i == 4) {
                            /**
                             * 这部分代码自己根据实际情况修改
                             */
                            String str = new String(pos.mAttribute);
                            str = str.substring(2);
                            if (str != null) {
                                voiceHandler.sendEmptyMessage(0);//调用刷卡成功的音频

                                String string = String.valueOf(YSLUtils.change16To10(str));
                                MyApplication.VIP_CARD =  string;
                                while (string!=null &&string.length()<10){
                                    string = "0"+string;
                                }
                                mEditText.setText("" + string);


//                                mEditText.setText("" + YSLUtils.change16To10(str));
                                Log.d(TAG, "CardOperationUtilsCardOperationUtilsCardOperationUtilsCardOperationUtils: ");

                                try {
                                    ServiceManager.getInstence().getBeeper().beep(100, 500, 1);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            handler_basewin_ReadCard.postDelayed(runnable_basewin_ReadCard, 200);

                        } else if (i == 2) {
                            String s = String2Byte2StringUtil.bcdToString(pos.mSerialNum);
                            final Long ii = Long.valueOf(s, 16);
                            voiceHandler.sendEmptyMessage(0);//调用刷卡成功的音频

                            String string = String.valueOf(YSLUtils.change16To10(ii+""));
                            MyApplication.VIP_CARD =  string;
                            while (string!=null &&string.length()<10){
                                string = "0"+string;
                            }
                            mEditText.setText("" + string);


//                            mEditText.setText("" + YSLUtils.change16To10(ii + ""));
                            Log.d(TAG, "CardOperationUtilsCardOperationUtilsCardOperationUtilsCardOperationUtils: ");

                            try {
                                ServiceManager.getInstence().getBeeper().beep(100, 500, 1);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        handler_basewin_ReadCard.postDelayed(runnable_basewin_ReadCard, 200);

                    }

                    @Override
                    public void onError(int arg0, String arg1) {
                        Log.d("getCard", "onError:" + arg0 + arg1);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 世麦pos
     * 初始化卡流程
     */
    private void initCard() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (mCardBinder == null) {
                        mCardBinder = ServiceManager.getInstence().getCard();
                        Log.d(TAG, "ServiceManager.getInstence().getCard(): ");
                    }

                } catch (Exception e) {
                    LogUtil.i(getClass(), "获取service异常:" + e.getMessage());
                    e.printStackTrace();
                }
            }
        }, 100);
    }

    /**
     * 关闭所有pos检卡读卡程序
     */
    public void close() {
        try {
            /**
             * SUNMI pos
             */
            if (MyApplication.SUNMI_DEVICE_MODEL == null || MyApplication.SUNMI_DEVICE_MODEL.equals("P1N") ) {
                if (mHardwareOpt != null) {
                    mHardwareOpt.cancelCheckCard();
                }
            } else {
                if (sReadCardOpt != null) {
                    sReadCardOpt.cancelCheckCard();
                    handler_sunmi_ReadCard.removeCallbacks(runnable_sunmi_ReadCard);
                }
            }
            /**
             * LKL pos
             */
            if (magCardDev != null) {
                magCardDev.stopSearch();
                handler_lkl_MagCard.removeCallbacks(runnable_lkl_MagCard);
                handler_lkl_RfCard.removeCallbacks(runnable_lkl_RfCard);
            }

            /**
             * 世麦pos
             */
            if (mCardBinder != null) {
                try {
                    mCardBinder.removeCard();
                    handler_basewin_ReadCard.removeCallbacks(runnable_basewin_ReadCard);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
