package com.zhiluo.android.yunpu.home.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.ui.view.NoDoubleClickListener;
import com.zhiluo.android.yunpu.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * 分享页面
 */
public class ShareActivity extends BaseActivity
{

    private WebView webView;

    final String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=1732251674&version=1";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        init();
        ShareSDK.initSDK(this);
        click();
    }



//    /**
//     * 一键分享
//     */
//    private void showShare()
//    {
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
//        oks.setTitle("云上铺");
//        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
//        oks.setTitleUrl("http://m.yunvip123.com");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("互联网+店铺会员营销管理平台。");
//        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        //        oks.setImageUrl("android.resource://" + getApplicationContext().getPackageName() + "/" + R.mipmap.zhiluo_logo);
//        oks.setImageUrl(HttpAPI.API().DOMAIN + "/Upload/AdvertImage/981250276.jpg");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://m.yunvip123.com");
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        //        oks.setComment("我是测试评论文本");
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite("云上铺");
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://m.yunvip123.com");
//
//        // 启动分享GUI
//        oks.show(this);
//    }

    private void click()
    {

    }

    private class MyWebViewDownLoadListener implements DownloadListener
    {

        @Override
        public void onDownloadStart(String url, String userAgent,
                                    String contentDisposition, String mimetype, long contentLength)
        {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    }


    @SuppressLint("WrongConstant")
    private void init()
    {
//        btn_consult_qq = (Button) findViewById(R.id.btn_consult_qq);
//        btn_share = (Button) findViewById(R.id.btn_share);
        webView = (WebView) findViewById(R.id.wv_share);
        //WebView加载web资源
        //启用支持javascript
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAppCacheEnabled(false);
        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        //优先使用缓存
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.loadUrl(HttpAPI.API().YUNPU);
        webView.setDownloadListener(new MyWebViewDownLoadListener());
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url)
            {
                if (url.startsWith("http") || url.startsWith("https"))
                {
                    return super.shouldInterceptRequest(view, url);
                }
                else
                {
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(in);
                    return null;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                super.onPageFinished(view, url);
            }
        });

//        findViewById(R.id.fab_share).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showShare();
//            }
//        });
    }
}
