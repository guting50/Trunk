package com.zhiluo.android.yunpu.jpush;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.login.activity.LoginActivity;

import butterknife.Bind;

import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

/**
 * Created by ZPH on 2019-06-04.
 */

public class NoticeActivity extends Activity {

    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_content)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_jpush_notice);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            String title = null;
            String content = null;
            if (bundle != null) {
                title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
                content = bundle.getString(JPushInterface.EXTRA_ALERT);
            }
            tvTitle.setText(title);
            tvContent.setText("    "+content);

        }

        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( NoticeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

//        TextView tv = new TextView(this);
//        tv.setText("用户自定义打开的Activity");
//        Intent intent = getIntent();
//        if (null != intent) {
//            Bundle bundle = getIntent().getExtras();
//            String title = null;
//            String content = null;
//            if(bundle!=null){
//                title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
//                content = bundle.getString(JPushInterface.EXTRA_ALERT);
//            }
//            tv.setText("Title : " + title + "  " + "Content : " + content);
//        }
//        addContentView(tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));

    }
}
