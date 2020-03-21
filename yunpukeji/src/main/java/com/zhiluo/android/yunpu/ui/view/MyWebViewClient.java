package com.zhiluo.android.yunpu.ui.view;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 作者：罗咏哲 on 2017/6/14 14:06.
 * 邮箱：137615198@qq.com
 */

public class MyWebViewClient extends WebViewClient {
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        imgReset(view);//html加载完成以后，调用js的方法处理适配
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    /**
     * 通过 js的语法来循环遍历接收到的数据,提取出带有”img”标签的内容,
     * 高度不管,设置它的宽度占屏幕的100%
     * @param wb
     */
    private void imgReset(WebView wb) {
        wb.loadUrl("javascript:(function(){"
                + "var objs = document.getElementsByTagName('img'); "
                + "for(var i=0;i<objs.length;i++)  " + "{"
                + "var img = objs[i];   "
                + "    img.style.width = '100%';   "
                + "    img.style.height = 'auto';   "
                + "}" + "})()");
    }
}
