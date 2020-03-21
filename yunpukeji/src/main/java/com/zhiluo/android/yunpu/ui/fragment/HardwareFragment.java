package com.zhiluo.android.yunpu.ui.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhiluo.android.yunpu.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HardwareFragment extends Fragment {

    @Bind(R.id.iv_hardware_card)
    ImageView ivCard;
    @Bind(R.id.iv_hardware_flush)
    ImageView ivFlush;
    @Bind(R.id.iv_hardware_sunmi)
    ImageView ivSunmi;
    @Bind(R.id.iv_hardware_print)
    ImageView ivPrint;
    @Bind(R.id.iv_hardware_paper)
    ImageView ivPaper;
    @Bind(R.id.iv_hardware_scan)
    ImageView ivScan;
    @Bind(R.id.iv_hardware_keyboard)
    ImageView ivKeyboard;
    @Bind(R.id.iv_hardware_money_box)
    ImageView ivMoneyBox;
    @Bind(R.id.iv_hardware_display)
    ImageView ivDisplay;
    @Bind(R.id.iv_hardware_voice_box)
    ImageView ivVoiceBox;

    private final String mMemberCard = "https://item.jd.com/18453443588.html";//会员卡
    private final String mPosMachine = "https://item.jd.com/14542490303.html";//刷卡机
    private final String mSunMi = "https://item.jd.com/14743814497.html";//商米
    private final String mPaperPrintMachine = "https://item.jd.com/14744504361.html";//小票打印机
    private final String mPrintPaper = "https://item.jd.com/19073245225.html";//小票打印纸
    private final String mScanGun = "https://mall.jd.com/view_search-696654-6900575-99-1-24-1.html";//扫码枪
    private final String mKeyBoard = "https://item.jd.com/14397325938.html";//密码键盘
    private final String mMoneyBox = "https://mall.jd.com/view_search-696654-6900574-99-1-24-1.html";//钱箱
    private final String mDisplay = "https://item.jd.com/14612792165.html";//顾客显示器
    private final String mBox = "https://item.jd.com/15767471456.html";//来电盒

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hardware, container, false);
        ButterKnife.bind(this, view);
        setListener();
        return view;
    }

    private void setListener() {
        ivCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(mMemberCard);
            }
        });
        ivFlush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(mPosMachine);
            }
        });
        ivSunmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(mSunMi);
            }
        });
        ivPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(mPaperPrintMachine);
            }
        });
        ivPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(mPrintPaper);
            }
        });
        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(mScanGun);
            }
        });
        ivKeyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(mKeyBoard);
            }
        });
        ivMoneyBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(mMoneyBox);
            }
        });
        ivDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(mDisplay);
            }
        });
        ivVoiceBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl(mBox);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void openUrl(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
//        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        startActivity(intent);
    }
}
