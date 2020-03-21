package com.zhiluo.android.yunpu.home.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.MyWebViewClient;
import com.zhiluo.android.yunpu.R;

/**
 * 消息中心内容
 * iauthor：Cheng
 * date: 2017/5/11 16:40
 * email：jasoncheng9111@gmail.com
 */
public class MessageContentActivity extends BaseActivity {
    private TextView tv_Back, tv_notice_content;
    private WebView wv_MessageContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_content);

        initView();

        initListtener();

        initData();
    }

    private void initView() {
        tv_Back = (TextView) findViewById(R.id.tv_back_activity);
        wv_MessageContent = (WebView) findViewById(R.id.wv_message_content);
        //        tv_notice_content = (TextView) findViewById(R.id.tv_notice_content);
    }

    private void initListtener() {
        //返回
        tv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initData() {
        //设置webView控件
        WebSettings settings = wv_MessageContent.getSettings();
        settings.setJavaScriptEnabled(true);//支持javaScript
        settings.setDefaultTextEncodingName("UTF-8");
        wv_MessageContent.setWebViewClient(new MyWebViewClient());

        if (getIntent() != null && !getIntent().getStringExtra("content").equals("")) {
//            String css = "<style type=\"text/css\"> img {" +
//                    "width:100%;" +//限定图片宽度填充屏幕
//                    "height:auto;" +//限定图片高度自动
//                    "}" +
//                    "</style>";
//
//            String html =
//                    "<html><header>" +
//                            css +
//                            "</header>" +
//                            "<body style='word-break:break-all'>" +
//                            getIntent().getStringExtra("content") +
//                            "</body>" +
//                            "</html>";

            String start = "<html><head><meta http-equiv='Content-Type' content='text/html' charset='UTF-8' /><style> body {" +
                    " word-wrap:break-word;}</style></head><body>";
            String content = getIntent().getStringExtra("content").replaceAll("<p style=\"white-space: normal;\"><span style=\"white-space: nowrap;\">", "<p " +
                    "style=\"word-wrap:break-word;\"><span " +
                    "style=\"word-wrap:break-word;\">");
            String end = "</body></html>";


            Log.i("lyz", getIntent().getStringExtra("content"));
            wv_MessageContent.loadDataWithBaseURL(null, start + content + end, "text/html", "UTF-8", null);
        }
    }

}
