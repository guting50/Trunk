package com.zhiluo.android.yunpu.sms.activity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ZPH on 2019-03-22.
 */

public class MsgNotice extends BaseActivity {

    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_notice);
        ButterKnife.bind(this);

        String text1 = "       1.短信计费标准：";
        String text2 = "70";
        String text3 = "个字符为一条计费，最多支持";
        String text4 = "500";
        String text5 = "个字符，实际支持的最大字符根据通道方限制为准(一般是300—500个字符不等)。超过70个字按67个字符一条计费，例如，给一个号码提交了一条137个字符的短信137=67*2+3超过了2条计费一个号码就会按3条计费。【70=1条；71=2条；134=2条；135=3条】" + "<br>" +
                "        2.短信发送时间：9：00-18：00。" + "<br>" +
                "        3.根据工信部《通信短信息服务管理规定》要求，所有营销短信需在结尾加【退订回T】 的退订机制，系统已经内置【";
        String text6 = "退订回T";
        String text7 = "】，无需重复添加。" + "<br>" +
                "        4.群发之前，请务必给自己发送一条测试短信，若未收到，请联系客服。" + "<br>" +
                "        5.发现没收到短信的情况，请立即联系客服，超过48小时则无法查询和返还。" + "<br>" +
                "        6.本公司保留追究发送违法违规短信者的权利，视情节给予封禁短信功能或账号的处罚。";




        String text8 = text1 + "<font color=\"#FF8739\">" + text2 + "</font>" + text3 +
                "<font color=\"#FF8739\">" + text4 + "</font>" + text5 +
                "<font color=\"#FF8739\">" + text6 + "</font>" + text7;
        tvContent.setText(Html.fromHtml(text8));
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
