package com.zhiluo.android.yunpu.member.manager.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.adapter.MemberCheckInListAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberFastCheckInBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberProjectListBean;
import com.zhiluo.android.yunpu.mvp.presenter.PostVipPresenter;
import com.zhiluo.android.yunpu.mvp.view.IPostVipView;
import com.zhiluo.android.yunpu.sms.jsonbean.SmsSwitch;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CacheFun;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ZPH on 2019-05-15.
 */

public class MemberSignActivity extends BaseActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.et_membercard_memberrechargeactivity)
    EditText etSearch;
    @Bind(R.id.iv_scan_memberrechargeactivity)
    ImageView ivScan;
    @Bind(R.id.ll_scan_memberrechargeactivity)
    LinearLayout llScanMemberrechargeactivity;
    @Bind(R.id.tv_sign_member)
    TextView tvSignMember;
    @Bind(R.id.ll_sign_member)
    LinearLayout llSignMember;
    @Bind(R.id.tv_sign_project)
    TextView tvSignProject;
    @Bind(R.id.ll_sign_project)
    LinearLayout llSignProject;
    @Bind(R.id.list_view_sign)
    BaseListView ListViewSign;
    @Bind(R.id.cb_short_message)
    CheckBox cbShortMessage;
    @Bind(R.id.btn_ok)
    TextView btnSubmit;

    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;
    private boolean mMustCard;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;//参数开关

    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间
    private PostVipPresenter vippresenter;
    private IPostVipView vipView;
    private MemberInfoBean.DataBean vipdatalist;
    private String mVipCard = null;
    private String content;
    private AllMemberListBean.DataBean.DataListBean mMemberInfo = new AllMemberListBean.DataBean.DataListBean();//会员信息
    private MemberProjectListBean.DataBean.DataListBean memberProjectinfo;
    private MemberFastCheckInBean mMemberFastCheckInBean;
    private List<MemberFastCheckInBean.DataBean.DataListBean> mMemberCheckList = new ArrayList<>();
    private MemberCheckInListAdapter mAdapter;
    private LoginUpbean mLoginBean;
    private boolean isSearch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        initData();
        initLisenter();
        setCbShortMessage("024");

        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            Intent nfcIntent = new Intent(this, getClass());
            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mPendingIntent =
                    PendingIntent.getActivity(this, 0, nfcIntent, 0);

        }
    }

    private void initLisenter() {

        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pretime = System.currentTimeMillis();
                if (!hasTimerDown) {
                    doQurry();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable)) {
                    content = editable.toString();
                    //postVip(editable.toString());
                }
            }
        });

        ivScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(MemberSignActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        //设置选择会员监听
        tvSignMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberSignActivity.this, CheckMemberInfoActivity.class);
                startActivityForResult(intent, 888);
            }
        });

        //设置选择签到项目
        tvSignProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MemberSignActivity.this, MemberSearchProjectActivity.class);
                startActivityForResult(intent, 888);
            }
        });

        ListViewSign.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MemberFastCheckInBean.DataBean.DataListBean checkInfo = mMemberCheckList.get(mMemberCheckList.size() - 1 - position);//获得被点击的Item的对象
                //回传给开启Activity的Activity选择项目信息实体
                Intent intent = new Intent(MemberSignActivity.this, MemberSignDetailActivity.class);
                intent.putExtra("checkInfo", checkInfo);
                startActivity(intent);
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vipdatalist == null) {
                    CustomToast.makeText(MemberSignActivity.this, "请选择签到会员", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tvSignProject.getText().toString().equals("")) {
                    CustomToast.makeText(MemberSignActivity.this, "请选择签到项目", Toast.LENGTH_SHORT).show();
                    return;
                }
                memberFastCheckIn();
            }
        });

    }

    private void initData() {
        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        if (mLoginBean != null) {
            memberProjectinfo = (MemberProjectListBean.DataBean.DataListBean) CacheFun.restoreObject(mLoginBean.getData().getUM_Acount() + "PROJECT");
            if (memberProjectinfo != null) {
                tvSignProject.setText("" + memberProjectinfo.getMCP_Name());
            }
        }
        comfirmParams();

        vippresenter = new PostVipPresenter(this);
        vipView = new IPostVipView() {
            @Override
            public void getvipsuccesss(MemberInfoBean bean) {
                isSearch = true;
                vipdatalist = bean.getData();
                mVipCard = vipdatalist.getVCH_Card();
                String mMemberName = vipdatalist.getVIP_Name();
                if (mMemberName != null && !"".equals(mMemberName)) {
                    tvSignMember.setText(mMemberName);
                } else {
                    tvSignMember.setText(mVipCard);
                }
//                if (memberProjectinfo!=null){
//                    memberFastCheckIn();
//                }
            }

            @Override
            public void getvipfail(String result) {

                if (MyApplication.VIP_CARD != null) {
                    vippresenter.postVip(MyApplication.VIP_CARD, isSearch);
                    MyApplication.VIP_CARD = null;
                } else {
                    tvSignMember.setText("");
//                    etSearch.setText("");
                    mMemberInfo = null;
                }
                if (result.contains("没有任何执行操作")) {
                    Intent intent = new Intent(MemberSignActivity.this, CheckMemberInfoActivity.class);
                    intent.putExtra("number", content);
                    startActivityForResult(intent, 888);
                }
            }
        };
        vippresenter.attachView(vipView);

    }


    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {
            if (!TextUtils.isEmpty(etSearch.getText())) {
                vippresenter.postVip(content, isSearch);
//                postVip(content);
            }
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }


    private class InputHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 0://0正在输入
                    break;
                case 1://1输入完成
                    doQurry();
                    break;
            }
        }
    }

    private void comfirmParams() {
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {


                    //必须刷卡
                    if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            mMustCard = true;
                            tvSignMember.setEnabled(false);
                            llScanMemberrechargeactivity.setEnabled(false);
                            llScanMemberrechargeactivity.setBackgroundColor(getResources().getColor(R.color.lightgray));
                            etSearch.setHint("请使用刷卡机刷卡");
                            etSearch.setFocusable(false);
                            etSearch.setFocusableInTouchMode(false);
                            ivScan.setEnabled(false);


                        } else {
                            mMustCard = false;
                            tvSignMember.setEnabled(true);
                            llScanMemberrechargeactivity.setEnabled(true);
                            llScanMemberrechargeactivity.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                            etSearch.setHint("请输入会员卡号/手机号");
                            etSearch.setFocusable(true);
                            etSearch.setFocusableInTouchMode(true);
                            ivScan.setEnabled(true);
                        }
                    }

                }
            }
        } else {
            getSystemSwitch();
        }
    }

    /**
     * 获取系统开关
     */
    public void getSystemSwitch() {
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean entity = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                mSwitchEntity = entity.getData().getGetSysSwitchList();
                CacheData.saveObject("switch", mSwitchEntity);//缓存系统开关到本地
                if (mSwitchEntity != null) {
                    if (mSwitchEntity.size() > 0) {
                        for (int i = 0; i < mSwitchEntity.size(); i++) {

                            //必须刷卡
                            if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                                if (mSwitchEntity.get(i).getSS_State() == 1) {
                                    mMustCard = true;
                                    tvSignMember.setEnabled(false);
                                    llScanMemberrechargeactivity.setEnabled(false);
                                    llScanMemberrechargeactivity.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                    etSearch.setHint("请使用刷卡机刷卡");
                                    etSearch.setFocusable(false);
                                    etSearch.setFocusableInTouchMode(false);
                                    ivScan.setEnabled(false);


                                } else {
                                    mMustCard = false;
                                    tvSignMember.setEnabled(true);
                                    llScanMemberrechargeactivity.setEnabled(true);
                                    llScanMemberrechargeactivity.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                                    etSearch.setHint("请输入会员卡号/手机号");
                                    etSearch.setFocusable(true);
                                    etSearch.setFocusableInTouchMode(true);
                                    ivScan.setEnabled(true);
                                }
                            }
                        }
                    } else {
                        mMustCard = false;

                    }
                } else {
                    mMustCard = false;

                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MemberSignActivity.this, "获取系统开关失败" + msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(MemberSignActivity.this, HttpAPI.API().PRE_LOAD, callBack);
    }

    private void memberFastCheckIn() {
        RequestParams params = new RequestParams();
        params.put("PageIndex", 1);
        params.put("PageSize", 20);
        params.put("MCP_GID", memberProjectinfo.getGID());
        params.put("VIP_GID", vipdatalist.getGID());
        params.put("VIP_Card", vipdatalist.getVCH_Card());
        params.put("IS_Sms", cbShortMessage.isChecked());
        params.put("Type", 1);

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(MemberSignActivity.this, "快速签到成功", Toast.LENGTH_SHORT).show();
                tvSignMember.setText("");
                etSearch.setText("");
                vipdatalist = null;
                mMemberFastCheckInBean = CommonFun.JsonToObj(responseString, MemberFastCheckInBean.class);
                if (mMemberCheckList.size() < 15) {
                    mMemberCheckList.add(mMemberFastCheckInBean.getData().getDataList().get(0));
                } else {

                    mMemberCheckList.add(mMemberFastCheckInBean.getData().getDataList().get(0));
                }


                mAdapter = new MemberCheckInListAdapter(MemberSignActivity.this, mMemberCheckList);
                ListViewSign.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MemberSignActivity.this, msg, Toast.LENGTH_SHORT).show();
                tvSignMember.setText("");
                etSearch.setText("");
                vipdatalist = null;
            }
        };
        HttpHelper.post(this, HttpAPI.API().FAST_CHECKIN, params, callBack);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            etSearch.setText(scanResult);
        }

        //选择会员
        if (requestCode == 888 && resultCode == 2222) {
            mMemberInfo = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");
            if (mMemberInfo != null) {
//                if (mMemberInfo.getVIP_Overdue() == null || mMemberInfo.getVIP_Overdue().equals("")) {
////                    postVip(mMemberInfo.getVCH_Card());
//                } else {
//                    if (!isOverTime(mMemberInfo.getVIP_Overdue())) {
//                        return;
//                    }
//
//                }
                mVipCard = mMemberInfo.getVCH_Card();
                isSearch = false;
                etSearch.setText(mVipCard);
                etSearch.setSelection(mVipCard.length());
                if (mMemberInfo.getVIP_Name() != null) {
                    tvSignMember.setText(mMemberInfo.getVIP_Name());
                } else {
                    tvSignMember.setText(mMemberInfo.getVCH_Card());
                }

            } else {
                tvSignMember.setText("");
                etSearch.setText("");
                mMemberInfo = null;

            }
        }
        if (requestCode == 888 && resultCode == 88888) {
            memberProjectinfo = (MemberProjectListBean.DataBean.DataListBean) data.getSerializableExtra("Project");
            if (memberProjectinfo != null) {
                tvSignProject.setText(memberProjectinfo.getMCP_Name());
                CacheFun.saveObject(mLoginBean.getData().getUM_Acount() + "PROJECT", memberProjectinfo);
//                if (memberProjectinfo!=null){
//                    memberFastCheckIn();
//                }
            }
        }
    }


    //获取系统隐式启动的
    @Override
    public void onNewIntent(Intent intent) {
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        if (tagFromIntent != null) {
            String CardId = ByteArrayToHexString.ByteArrayToHex(tagFromIntent.getId());
            if (null != CardId) {
                MyApplication.VIP_CARD = CardId;
                while (CardId.length() < 10) {
                    CardId = "0" + CardId;
                }
                etSearch.setText(CardId);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mNFCAdapter != null) {

//             mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, intentFiltersArray, techListsArray);
            //隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
        /**
         * lkl、sunmi 、basewin的检卡 读卡操作
         */
        if (!MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            new CardOperationUtils(this, etSearch);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mNFCAdapter != null) {
            //隐式启动
            mNFCAdapter.disableForegroundDispatch(this);
        }
    }

    /**
     * @param code ,参照SmsSwitch实体类的值
     *             根据短信发送开关是否打开，设置checkbox
     */
    private void setCbShortMessage(String code) {
        try {
            SmsSwitch.DataBean smsSwitch = YSLUtils.getSmsSwitch(code);
            if (smsSwitch != null) {
                if (smsSwitch.getST_State() == null || !smsSwitch.getST_State().equals("1")) {
                    cbShortMessage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CustomToast.makeText(MemberSignActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
                            cbShortMessage.setChecked(false);
                        }
                    });
                } else {
                    cbShortMessage.setChecked(true);
                }
            } else {
                getSmsSet(code);
            }

        } catch (Exception e) {
            cbShortMessage.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 获取短信开关
     */
    private void getSmsSet(final String code) {
        HttpHelper.post(MemberSignActivity.this, HttpAPI.API().SMS_LIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SmsSwitch bean = CommonFun.JsonToObj(responseString, SmsSwitch.class);

                for (int i = 0; i < bean.getData().size(); i++) {
                    if (bean.getData().get(i).getST_Code().equals(code)) {
                        if (bean.getData().get(i).getST_State() == null || !bean.getData().get(i).getST_State().equals("1")) {
                            cbShortMessage.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    CustomToast.makeText(MemberSignActivity.this, "发送短信未开启，请到PC端去开启", Toast.LENGTH_SHORT).show();
                                    cbShortMessage.setChecked(false);
                                }
                            });
                        } else {
                            cbShortMessage.setChecked(true);
                        }
                    }
                }


                CacheData.saveObject("shortmessage", bean);//缓存短信开关到本地
                Li("获取短信开关成功");
            }

            @Override
            public void onFailure(String msg) {
                Li("获取短信开关" + msg);
            }
        });
    }

}
