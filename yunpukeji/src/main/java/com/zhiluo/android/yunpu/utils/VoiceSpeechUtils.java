package com.zhiluo.android.yunpu.utils;

import android.content.Context;
import android.util.Log;

import com.baidu.tts.auth.AuthInfo;
import com.baidu.tts.client.SpeechError;
import com.baidu.tts.client.SpeechSynthesizer;
import com.baidu.tts.client.SpeechSynthesizerListener;
import com.baidu.tts.client.TtsMode;

/**
 * 语音合成（文字转语音）
 * iauthor：Yc
 * date: 2017/7/8 15:00
 * email：jasoncheng9111@gmail.com
 */
public class VoiceSpeechUtils implements SpeechSynthesizerListener
{
    private Context mContext;
    private SpeechSynthesizer mSpeechSynthesizer;

    private AuthInfo authInfo;

    public VoiceSpeechUtils()
    {

    }

    public VoiceSpeechUtils(Context context)
    {
        this.mContext = context;
        initialTts();
    }

    private void initialTts()
    {
        this.mSpeechSynthesizer = SpeechSynthesizer.getInstance();
        this.mSpeechSynthesizer.setContext(mContext);
        this.mSpeechSynthesizer.setSpeechSynthesizerListener(this);
        this.mSpeechSynthesizer.setAppId("9861268");
        this.mSpeechSynthesizer.setApiKey("lrAOAslFVlmPYhk3WmRN4WC2", "8fwp9QZAzs8udP0kzILBfj1RWAhOiAEE  ");
        // 发音人（在线引擎），可用参数为0,1,2,3。。。（服务器端会动态增加，各值含义参考文档，以文档说明为准。0--普通女声，1--普通男声，2--特别男声，3--情感男声。。。）
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEAKER, "1");
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_SPEED, "6");

        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_VOLUME, "9");
        // 设置Mix模式的合成策略
        this.mSpeechSynthesizer.setParam(SpeechSynthesizer.PARAM_MIX_MODE, SpeechSynthesizer.MIX_MODE_HIGH_SPEED_SYNTHESIZE_WIFI);
        // 授权检测接口(只是通过AuthInfo进行检验授权是否成功。)
        // AuthInfo接口用于测试开发者是否成功申请了在线或者离线授权，如果测试授权成功了，可以删除AuthInfo部分的代码（该接口首次验证时比较耗时），不会影响正常使用（合成使用时SDK内部会自动验证授权）
        authInfo = this.mSpeechSynthesizer.auth(TtsMode.MIX);
    }

    public void speech()
    {
        if (authInfo.isSuccess())
        {
            // 初始化tts
            mSpeechSynthesizer.initTts(TtsMode.MIX);
            mSpeechSynthesizer.speak("刷卡成功！");
        }
        else
        {
            String errorMsg = authInfo.getTtsError().getDetailMessage();
        }
    }

    public void release()
    {
        mSpeechSynthesizer.release();
    }

    @Override
    public void onSynthesizeStart(String s)
    {
        Log.d("TAG", "开始播报 ");
    }

    @Override
    public void onSynthesizeDataArrived(String s, byte[] bytes, int i)
    {
        Log.d("TAG", "开始播报 ");

    }

    @Override
    public void onSynthesizeFinish(String s)
    {
        Log.d("TAG", "开始播报 ");

    }

    @Override
    public void onSpeechStart(String s)
    {
        Log.d("TAG", "onSpeechStart: ");
    }

    @Override
    public void onSpeechProgressChanged(String s, int i)
    {
        Log.d("TAG", "开始播报 ");

    }

    @Override
    public void onSpeechFinish(String s)
    {
        Log.d("TAG", "开始播报 ");

    }

    @Override
    public void onError(String s, SpeechError speechError)
    {
        Log.d("TAG", "开始播报 ");

    }
}
