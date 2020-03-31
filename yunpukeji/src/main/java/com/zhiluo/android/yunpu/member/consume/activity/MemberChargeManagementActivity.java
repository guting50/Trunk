package com.zhiluo.android.yunpu.member.consume.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.UserGradeJsonbean;
import com.zhiluo.android.yunpu.gift.bean.ChargeListBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.consume.adapter.JiCiConsumeAdapter;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.member.jsonbean.VipListBean;
import com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.mvp.presenter.PostVipPresenter;
import com.zhiluo.android.yunpu.mvp.view.IPostVipView;
import com.zhiluo.android.yunpu.setting.bluetooths.BluetoothUtil;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ByteArrayToHexString;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.CreateOrder;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;
import com.zhiluo.android.yunpu.zxing.activity.CaptureActivity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * 计次管消费
 */
public class MemberChargeManagementActivity extends BaseActivity implements JiCiConsumeAdapter.Add, JiCiConsumeAdapter.Reduce {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_scan)
    LinearLayout llScan;
    //   private BaseListView lv_memberchargemanagementactivity;
    private boolean moveTag;
    //    private ServiceListAdapter adapter;
    //    private TextView tv_charge_memberchargemanagementactivity;//充次控件声明
    private VipListBean.DataBean.DataListBean vipdetail;
    private MemberInfoBean.DataBean vipdatalist;
    private TextView tv_back_memberchargemanagementactivity;//返回控件声明
    private List<ChargeListBean.DataBean> chargeList;
    private WaveSwipeRefreshLayout refreshLayout;
    private String num;
    private int number, posi;
    private String abstra;
    private String ordder;
    private ImageView iv_scan_memberchargemanagementactivity;
    private EditText et_search_memberchargemanagementactivity;
    private TextView tvMemberName, tvMemberCard, tvBalance, tvIntegral;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private BluetoothUtil bluetoothUtil = new BluetoothUtil(this);
    private String mMaccode;
    private boolean flag;

    private boolean isMemberCharge = true; //是否可以计次消费
    private TextView mTvChooseMember;
    private ImageView mIvChooseMember;
    private AllMemberListBean.DataBean.DataListBean mMemberInfo;//会员信息
    private AllMemberListBean.DataBean.DataListBean mVipInfo;//会员信息
    private String vipGid;
    private String MDZZ;

    private InputHandler mInputHandler = new InputHandler();
    private boolean hasTimerDown = false;
    private long pretime;//第一次的时间
    private String content;

    private JiCiConsumeAdapter mJiCiConsumeAdapter;
    //    private SwipeActionAdapter mAdapter;
    private BaseListView recyclerView;
    private SweetAlertDialog mSweetAlertDialog;//提示框
    private EditText edit;
    private TextView etdata;
    private PopupWindow mChildTypePW;
    private Dialog chooseDialog;
    private List<ChargeListBean.DataBean> mList = new ArrayList<>();//提交的商品信息
    private TextView tvSummary;
    private int mTotalNum;//总次数
    private int mTotalType;//合计商品种类数

    private CheckBox cbMessage, cbPrint;
    private TextView tvComfirm;
    private RelativeLayout rChoiseVip;
    private TextView tv_VipName;

    private NfcAdapter mNFCAdapter;
    private PendingIntent mPendingIntent;
    private List<String> mlist;
    private PostVipPresenter vippresenter;
    private IPostVipView vipView;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;//参数开关
    private boolean isSearch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_charge_management);
        ButterKnife.bind(this);
        postOrder();
        initview();
        inidata();
        /**
         * 判断会员卡号值是否有效
         * 这一步主要是在从会员详情跳转过来的时候判断会员卡号是否为空
         * 如果不为空，则根据会员卡号查询会员等级名称，再根据会员等级名称查询会员等级相关的等级功能设定开关
         */
        if (tvMemberCard != null) {
            if (!tvMemberCard.getText().toString().equals("")
                    & !tvMemberCard.getText().equals("00000")
                    & !tvMemberCard.getText().equals("     ")) {
                isSearch = false;
                et_search_memberchargemanagementactivity.setText(tvMemberCard.getText().toString());
                vippresenter.postVip(tvMemberCard.getText().toString(),isSearch);
//                postVip(tvMemberCard.getText().toString());
            }
        } else {
            postchargelist();
        }

        initSp();
        initAdapter();
        initLisinner();

        //V1S刷卡
        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            Intent nfcIntent = new Intent(this, getClass());
            nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            mPendingIntent =
                    PendingIntent.getActivity(this, 0, nfcIntent, 0);
        }

    }


    @Override
    public void reduceClick(View view, int position) {


        if (chargeList.get(position).getCount() > 0) {
            chargeList.get(position).setCount(chargeList.get(position).getCount() - 1);
        } else {
            chargeList.get(position).setCount(0);
        }
        handler.sendEmptyMessage(0);
        mJiCiConsumeAdapter.notifyDataSetChanged();
    }

    @Override
    public void addClick(View view, int position) {
//        ChargeListBean.DataBean bean = chargeList.get(position);
//        int num  = 0;
//        if(!mList.isEmpty()){
//            for (int i = 0;i<mList.size();i++){
//                if (bean.getSG_GID().equals(mList.get(i).getSG_GID())){
//                    num = mList.get(i).getSelected_Count();
//                    mList.remove(i);
////                    mList.get(i).setSelected_Count(mList.get(i).getSelected_Count()+1);
//                }
//            }
//        }
//        num += 1 ;
//        bean.setSelected_Count(num);
//        mList.add(bean);


        if (chargeList.get(position).getCount() >= chargeList.get(position).getMCA_HowMany()) {
            CustomToast.makeText(this, "不能超过剩余次数！", Toast.LENGTH_SHORT).show();
            return;
        } else if (!isOverTime(chargeList.get(position).getMCA_OverTime())) {
            CustomToast.makeText(this, "该服务已过期！", Toast.LENGTH_SHORT).show();
            return;
        } else if (chargeList.get(position).getCount() >= chargeList.get(position).getRegularResiduals()) {
            String WR_Name = "【无】";
            if (chargeList.get(position).getWR_Name()!=null&& !chargeList.get(position).getWR_Name().equals("")){
                WR_Name = chargeList.get(position).getWR_Name();
            }
            CustomToast.makeText(this, "该服务消费数量已达" + WR_Name + "上限！", Toast.LENGTH_SHORT).show();
            return;
        } else {
            chargeList.get(position).setCount(chargeList.get(position).getCount() + 1);
        }
        handler.sendEmptyMessage(1);
        mJiCiConsumeAdapter.notifyDataSetChanged();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0://减少
                    getSummary();
                    tvSummary.setText(mTotalType + "种商品，" + "共" + mTotalNum + "次");
                    break;
                case 1://增加
                    getSummary();
                    tvSummary.setText(mTotalType + "种商品，" + "共" + mTotalNum + "次");
                    break;
            }
        }
    };

    private void getSummary() {
        mTotalNum = 0;
        mTotalType = 0;
        for (int i = 0; i < chargeList.size(); i++) {
            if (chargeList.get(i).getCount() > 0) {
                mTotalType += 1;
            }
            mTotalNum += chargeList.get(i).getCount();
        }
    }

    //获取系统隐式启动的
    @Override
    public void onNewIntent(Intent intent) {
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String CardId = ByteArrayToHexString.ByteArrayToHex(tagFromIntent.getId());
        if (null != CardId) {
            MyApplication.VIP_CARD = CardId;
            while (CardId.length() < 10) {
                CardId = "0" + CardId;
            }
            et_search_memberchargemanagementactivity.setText(CardId);

        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mNFCAdapter != null) {
            //隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }

        /**
         * lkl、sunmi 、basewin的检卡 读卡操作
         */
        if (!MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            new CardOperationUtils(this, et_search_memberchargemanagementactivity);
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
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }
    }

    @Override
    protected void onStop() {

        //终止检卡
        new CardOperationUtils().close();

        Log.d("TAG", "onStop: ");
        super.onStop();
    }


    private void initSp() {
        sp = getSharedPreferences("setting", MODE_PRIVATE);
        editor = sp.edit();
        mMaccode = sp.getString("adress", "");
    }

    /**
     * 查询会员等级的相关设定----计次设定
     *
     * @param memberCardNumber 会员卡号
     */
    private void postRecordNumberSetting(final String memberCardNumber) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookie = new PersistentCookieStore(this);
        client.setCookieStore(cookie);
        RequestParams params = new RequestParams();
        params.put("GID", "");
        client.post(HttpAPI.API().USERGRADE, params, new AsyncHttpResponseHandler() {
            @SuppressLint("WrongConstant")
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    Gson gson = new Gson();
                    try {
                        UserGradeJsonbean userGradeJsonbean = CommonFun.JsonToObj(new String(responseBody, "UTF-8"), UserGradeJsonbean.class);
                        if (userGradeJsonbean.getData() != null) {
                            for (int i = 0; i < userGradeJsonbean.getData().size(); i++) {
                                //判断当前的会员等级名称是否等于查询api返回的json中的会员等级名称
                                if (userGradeJsonbean.getData().get(i).getVG_Name().equals(memberCardNumber)) {
                                    //当计次设定  与   计时设定 同时 关闭的时候  计次消费功能不可用
                                    //1：计次消费打开 0：计次消费不可用
                                    //1：计时设定打开 0：计时设定关闭
                                    int isCount = userGradeJsonbean.getData().get(i).getVG_IsCount();
                                    int isTime = userGradeJsonbean.getData().get(i).getVG_IsTime();

                                    if (isCount == 0 && isTime == 0) {
                                        isMemberCharge = false;
                                        SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(MemberChargeManagementActivity.this, SweetAlertDialog.WARNING_TYPE);
                                        sweetAlertDialog.setTitleText("提示");
                                        sweetAlertDialog.setContentText("该会员不能计次消费，请前往PC端进行相关设置！");
                                        sweetAlertDialog.setConfirmText("确定");
                                        sweetAlertDialog.setCancelable(true);
                                        sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                sweetAlertDialog.dismiss();
                                            }
                                        });
                                        sweetAlertDialog.show();
                                    } else {
                                        isMemberCharge = true;
                                        initnew();
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i(TAG, "onFailure: " + statusCode + "|" + error);

            }
        });

    }


    private void initnew() {
        if (vipdatalist.getVIP_Name() != null) {
            tvMemberName.setText(vipdatalist.getVIP_Name());
        } else {
            tvMemberName.setText("无");
        }
        tvMemberCard.setText(vipdatalist.getVCH_Card());
        tvBalance.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableBalance() + ""));
        tvIntegral.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableIntegral() + ""));
        postchargelist();
    }

    /**
     * 获取订单号
     *
     * @param
     */
    private void postOrder() {
        ordder = CreateOrder.createOrder("JC");
    }

    private void initLisinner() {

        tvComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (judge()) {
                    Intent intent = new Intent(MemberChargeManagementActivity.this, MemberChargePayActivity.class);
                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("GOODS_LIST", (Serializable) mList);
                    intent.putExtra("GOODS_LIST", (Serializable) mList);
//                    intent.putExtra("bundle", bundle);
                    intent.putExtra("MDZZ", MDZZ);
                    intent.putExtra("membercard", tvMemberCard.getText().toString());
                    intent.putExtra("WO_OrderCode", ordder);
                    intent.putExtra("membername", tvMemberName.getText().toString());
                    startActivity(intent);
                }

            }
        });

        //选择会员
        rChoiseVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (YSLUtils.isFastClick()) {
                    Intent intent = new Intent(MemberChargeManagementActivity.this, CheckMemberInfoActivity.class);
                    startActivityForResult(intent, 888);
                }
            }
        });

        iv_scan_memberchargemanagementactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //打开扫描界面扫描条形码或二维码
                Intent openCameraIntent = new Intent(MemberChargeManagementActivity.this, CaptureActivity.class);
                startActivityForResult(openCameraIntent, 0);
            }
        });

        //会员卡号的值改变的监听
        et_search_memberchargemanagementactivity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pretime = System.currentTimeMillis();
                if (!hasTimerDown) {
                    doQurry();
                }
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                chargeList.clear();
                mJiCiConsumeAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //根据会员卡号查询会员的相关信息
                content = editable.toString();
                // postVip(editable.toString());

            }
        });

        tv_back_memberchargemanagementactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MDZZ != null && "mdzz".equals(MDZZ)) {
//                    Intent intent = new Intent(MemberChargeManagementActivity.this, MemberDetailsActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("VIP_INFO", mVipInfo);
//                    intent.putExtra("bundle", bundle);
//                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MemberChargeManagementActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        });

        //滑动刷新监听
        refreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isMemberCharge && mMemberInfo != null) {
                    postchargelist();
                    moveTag = true;
                }
            }

            @Override
            public void onLoad() {
                refreshLayout.setLoading(false);
            }

            @Override
            public boolean canLoadMore() {
                return true;
            }

            @Override
            public boolean canRefresh() {
                if (isMemberCharge && mMemberInfo != null) {
                    postchargelist();
                    moveTag = true;
                    return true;
                }
                return false;
            }
        });


        //Item长按监听
        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                if (chargeList.get(position).getMCA_HowMany() > 0) {
                    if (chargeList.get(position).getMCA_OverTime() == null) {
                        //为空代表永久 不做操作
//                    CustomToast.makeText(MemberChargeManagementActivity.this, "该服务已是永久有效，不需要操作！", Toast.LENGTH_SHORT).show();
                    } else if (isOverTime(chargeList.get(position).getMCA_OverTime())) {
                        showAddPartPopWindow(position);
                        YSLUtils.setBackgroundAlpha(0.5f, MemberChargeManagementActivity.this);
                    } else {
                        //删除/延期
                        mSweetAlertDialog = new SweetAlertDialog(MemberChargeManagementActivity.this, SweetAlertDialog.WARNING_TYPE);
                        mSweetAlertDialog.setTitleText("提示");
                        mSweetAlertDialog.setContentText("操作" + chargeList.get(position).getSG_Name() + "商品！");
                        mSweetAlertDialog.setCancelText("延期");
                        mSweetAlertDialog.setConfirmText("删除");
                        mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                addDel(position);
                                mSweetAlertDialog.dismiss();

                                //删除
                                mSweetAlertDialog = new SweetAlertDialog(MemberChargeManagementActivity.this, SweetAlertDialog.WARNING_TYPE);
                                mSweetAlertDialog.setTitleText("提示");
                                mSweetAlertDialog.setContentText("是否删除" + chargeList.get(position).getSG_Name() + "商品！");
                                mSweetAlertDialog.setCancelText("取消");
                                mSweetAlertDialog.setConfirmText("删除");
                                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        addDel(position);
                                        mSweetAlertDialog.dismiss();

                                    }
                                });

                                mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        mSweetAlertDialog.dismiss();
                                    }
                                });
                                mSweetAlertDialog.show();
                            }
                        });
                        mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                                showAddPartPopWindow(position);
                                YSLUtils.setBackgroundAlpha(0.5f, MemberChargeManagementActivity.this);
                            }
                        });
                        mSweetAlertDialog.show();
                    }
                } else {
                    //删除
                    mSweetAlertDialog = new SweetAlertDialog(MemberChargeManagementActivity.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("提示");
                    mSweetAlertDialog.setContentText("是否删除" + chargeList.get(position).getSG_Name() + "商品！");
                    mSweetAlertDialog.setCancelText("取消");
                    mSweetAlertDialog.setConfirmText("删除");
                    mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            addDel(position);
                            mSweetAlertDialog.dismiss();

                        }
                    });

                    mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mSweetAlertDialog.dismiss();
                        }
                    });

                    mSweetAlertDialog.show();

                }
                return true;//true长按事件自己消费操作，false还会触发点击事件
            }
        });

    }


    private boolean isOverTime(String date) {
        boolean isover = true;
        if (date == null) {
            return isover;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long overtime = sdf.parse(date + "").getTime();
            long currentTime = sdf.parse(sdf.format(System.currentTimeMillis())).getTime();
            long time = overtime - currentTime;
            if (time < 0) {
                isover = false;
            } else {
//                postVip(mMemberInfo.getVCH_Card());
                isover = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isover;

    }


    /**
     * 长按弹窗
     */
    private void showAddPartPopWindow(final int position) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_times_delay, null);

        mChildTypePW = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        edit = view.findViewById(R.id.ed_shijian);//num
        etdata = view.findViewById(R.id.t_timetype);//天
        mChildTypePW.setContentView(view);
        TextView save = view.findViewById(R.id.tv_save);
        TextView delete = view.findViewById(R.id.tv_delete);
        RelativeLayout choiseparent = view.findViewById(R.id.r_choisetime);

//        mChildTypePW.setBackgroundDrawable(new ColorDrawable());
        mChildTypePW.setBackgroundDrawable(null);//null

        mChildTypePW.setOutsideTouchable(true);

        mChildTypePW.setFocusable(true);
        mChildTypePW.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mChildTypePW.setWidth(YSLUtils.getPhoneWidth() * 7 / 8);
        mChildTypePW.setAnimationStyle(R.style.pop_show_style);
        view.setFocusableInTouchMode(true);
        mChildTypePW.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        mChildTypePW.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                YSLUtils.setBackgroundAlpha(1.0f, MemberChargeManagementActivity.this);

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YSLUtils.setBackgroundAlpha(1.0f, MemberChargeManagementActivity.this);
                mChildTypePW.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edit.getText()) || edit.getText().toString().equals("")) {
                    CustomToast.makeText(MemberChargeManagementActivity.this, "时间不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Integer.parseInt(edit.getText().toString()) < 1) {
                    CustomToast.makeText(MemberChargeManagementActivity.this, "时间不能为0", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    addDelay(position);
                    YSLUtils.setBackgroundAlpha(1.0f, MemberChargeManagementActivity.this);
                    mChildTypePW.dismiss();
                }
            }
        });
        choiseparent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(mlist, etdata);
            }
        });


    }

    /**
     * chooseDialog
     */
    private void showChooseDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();
        chooseDialog.show();
    }

    /**
     * 删除
     */
    private void addDel(int postion) {
        RequestParams params = new RequestParams();
        params.put("GID", chargeList.get(postion).getGID());

        if (mMemberInfo != null) {
            vipGid = mMemberInfo.getGID();
        } else {
            vipGid = vipdatalist.getGID();
        }
        params.put("VIPGID", vipGid);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(MemberChargeManagementActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                postchargelist();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MemberChargeManagementActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交...", false);
        HttpHelper.post(this, "ChargeAccount/DelChargeItem", params, callBack);
    }

    /**
     * 延期
     */
    private void addDelay(int postion) {
        RequestParams params = new RequestParams();
//        AC_GID: 4e371dd3-ca4c-4a80-b785-248077f3ac65
//        DelayNum: 1
//        DelayUnit: 天
        params.put("AC_GID", chargeList.get(postion).getGID());
        params.put("DelayNum", TextUtils.isEmpty(edit.getText()) ? "" : edit.getText().toString());
        params.put("DelayUnit", etdata.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(MemberChargeManagementActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                postchargelist();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MemberChargeManagementActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交...", false);
        HttpHelper.post(this, "WouldOrder/DelayWouldOrder", params, callBack);
    }


    private boolean judge() {
        if (TextUtils.isEmpty(tvMemberCard.getText())) {
            CustomToast.makeText(this, "请选择会员！", Toast.LENGTH_SHORT).show();
            return false;
        }
        getParamList();
        if (mList.size() < 1) {
            CustomToast.makeText(this, "没有选择商品次数！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private void getParamList() {
        mList.clear();
        for (int i = 0; i < chargeList.size(); i++) {
            if (chargeList.get(i).getCount() > 0) {
                mList.add(chargeList.get(i));
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ("mdzz".equals(MDZZ)) {
//                Intent intent = new Intent(MemberChargeManagementActivity.this, MemberDetailsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("VIP_INFO", mVipInfo);
//                intent.putExtra("bundle", bundle);
//                startActivity(intent);
            } else {
                Intent intent = new Intent(MemberChargeManagementActivity.this, HomeActivity.class);
                startActivity(intent);
            }
            finish();
        }
        return true;
    }

    private void showNum(final int po, final String ordder) {
        LayoutInflater inflater = getLayoutInflater();
        final View dialog = inflater.inflate(R.layout.dialog_addgifttype_gifttype, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        final EditText ediaddtybename = (EditText) dialog.findViewById(R.id.edi_addgifttypename_dia);
        final EditText ediaddtyperemark = (EditText) dialog.findViewById(R.id.edi_addgifttyperemark_dia);
        ediaddtybename.setHint("请输入消费数量");
        ediaddtybename.setInputType(InputType.TYPE_CLASS_NUMBER);
        Button btnedivipok = (Button) dialog.findViewById(R.id.btn_addgifttypeok_dia);
        Button btnedivipno = (Button) dialog.findViewById(R.id.btn_addgifttypeno_dia);
        btnedivipno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnedivipok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posi = po;
                num = ediaddtybename.getText().toString();
                number = Integer.valueOf(num).intValue();
                abstra = ediaddtyperemark.getText().toString();
                //完成计次消费
                postusecharge(number, posi, ordder);
                alertDialog.cancel();
            }
        });
        alertDialog.setTitle("添加消费信息");
        alertDialog.setView(dialog);
        alertDialog.show();
    }

    /**
     * 完成计次消费
     *
     * @param number
     * @param posi
     * @param ordder
     */
    private void postusecharge(int number, int posi, String ordder) {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("VIP_Card", tvMemberCard.getText().toString());
        params.put("WO_OrderCode", ordder);
        params.put("WO_Remark", "");
        params.put("wouldOrderDetail[" + 0 + "][SG_GID]", chargeList.get(posi).getSG_GID());
        params.put("wouldOrderDetail[" + 0 + "][SG_Name]", chargeList.get(posi).getSG_Name());
        params.put("wouldOrderDetail[" + 0 + "][SG_Code]", chargeList.get(posi).getSG_Code());
        params.put("wouldOrderDetail[" + 0 + "][SG_Price]", chargeList.get(posi).getSG_Price());
        params.put("wouldOrderDetail[" + 0 + "][SGC_ClasName]", chargeList.get(posi).getSGC_ClasName());
        params.put("wouldOrderDetail[" + 0 + "][WOD_UseNumber]", number);
        params.put("IS_Sms", cbMessage.isChecked());
        client.post(HttpAPI.API().CHAORDER, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resurt = null;
                    try {
                        resurt = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        Adduserbean adduserbean = CommonFun.JsonToObj(resurt, Adduserbean.class);
                        boolean isture = adduserbean.isSuccess();
                        if (isture) {
                            new SweetAlertDialog(MemberChargeManagementActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("消费成功！")
                                    .setConfirmText("了解")
                                    .show();
                            //  CustomToast.makeText(MemberChargeManagementActivity.this, "消费成功", Toast.LENGTH_SHORT).show();
                            if (flag) {
                                //  bluetoothUtil.send("a");
                            }
                        } else {
                            new SweetAlertDialog(MemberChargeManagementActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(adduserbean.getMsg() + "!")
                                    .setConfirmText("了解")
                                    .show();
                            //                            CustomToast.makeText(MemberChargeManagementActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }


    private void initAdapter() {
        mJiCiConsumeAdapter = new JiCiConsumeAdapter(this, chargeList, this, this);
        recyclerView.setAdapter(mJiCiConsumeAdapter);

    }

    private void initview() {
        mTvChooseMember = (TextView) findViewById(R.id.tv_choose_member);
        mIvChooseMember = (ImageView) findViewById(R.id.iv_choose_member);
        tvMemberName = (TextView) findViewById(R.id.tv_member_info_name);
        tvMemberCard = (TextView) findViewById(R.id.tv_member_info_card);
        tvBalance = (TextView) findViewById(R.id.tv_member_info_balance);
        tvIntegral = (TextView) findViewById(R.id.tv_member_info_integral);
        tvSummary = findViewById(R.id.tv_summmary);
        tvComfirm = findViewById(R.id.btn_pay_confirm_submit);
        cbMessage = findViewById(R.id.cb_short_message);
        cbPrint = findViewById(R.id.cb_print);
        rChoiseVip = findViewById(R.id.r_choise_vip);
        tv_VipName = findViewById(R.id.tv_choose_member);
        iv_scan_memberchargemanagementactivity = (ImageView) findViewById(R.id.iv_scan);
//        //        imvserch = (ImageView) findViewById(R.id.imv_search_list);
        et_search_memberchargemanagementactivity = (EditText) findViewById(R.id.et_membercard);
        // lv_memberchargemanagementactivity = (BaseListView) findViewById(R.id.lv_memberchargemanagementactivity);
        recyclerView = findViewById(R.id.recycler_view);
        tv_back_memberchargemanagementactivity = (TextView) findViewById(R.id.tv_back_activity);
        refreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.srl_freshchargrlist_activity);
        if (mMaccode != null && bluetoothUtil.isOpen() && !mMaccode.equals("")) {
            flag = bluetoothUtil.connect(mMaccode);
            if (flag == false) {
                // 连接失败
                new SweetAlertDialog(MemberChargeManagementActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("提示")
                        .setContentText("连接失败！")
                        .setConfirmText("了解")
                        .show();
                //                CustomToast.makeText(MemberChargeManagementActivity.this, "连接失败！", Toast.LENGTH_SHORT).show();
            } else {
                // 连接成功
                //  CustomToast.makeText(MemberRechargeActivity.this,"连接成功！",Toast.LENGTH_SHORT).show();

            }
        }
        chargeList = new ArrayList<>();
    }

    private void inidata() {
        tvTitle.setText("计次消费");
        Intent intent = getIntent();
        if (intent.getStringExtra("MDZZ") != null) {
            MDZZ = intent.getStringExtra("MDZZ");
            tvMemberName.setText(intent.getStringExtra("name"));
            tvMemberCard.setText(intent.getStringExtra("card"));
//            et_search_memberchargemanagementactivity.setText(tvMemberCard.getText().toString());
//            tvBalance.setText(Decima2KeeplUtil.stringToDecimal(intent.getStringExtra("yue")));
//            tvIntegral.setText(Decima2KeeplUtil.stringToDecimal(intent.getStringExtra("jifen")));
        }
//        mChildTypePW = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//        mChildTypePW = new PopupWindow(MemberChargeManagementActivity.this);
        mlist = new ArrayList<>();
        mlist.add("天");
        mlist.add("月");
        mlist.add("年");

        vippresenter = new PostVipPresenter(this);
        vipView = new IPostVipView() {
            @Override
            public void getvipsuccesss(MemberInfoBean bean) {
                vipdatalist = bean.getData();

                //会员等级名称
                if (!vipdatalist.getVG_Name().equals("")) {
                    //查询会员等级----计次设定
                    postRecordNumberSetting(vipdatalist.getVG_Name());
                }//18011111  18064243650
                String mMemberName = vipdatalist.getVIP_Name();
                if (mMemberName != null && !"".equals(mMemberName)) {
                    tv_VipName.setText(mMemberName);
                    tvMemberName.setText(mMemberName);
                } else {
                    tv_VipName.setText(vipdatalist.getVCH_Card());
                    tvMemberName.setText("无");
                }


                tvMemberCard.setText(vipdatalist.getVCH_Card());
                tvBalance.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableBalance() +""));
                tvIntegral.setText(Decima2KeeplUtil.stringToDecimal(vipdatalist.getMA_AvailableIntegral() +""));
            }

            @Override
            public void getvipfail(String result) {
                if (MyApplication.VIP_CARD != null) {
                    vippresenter.postVip(MyApplication.VIP_CARD,isSearch);
//                    postVip(MyApplication.VIP_CARD);
                    MyApplication.VIP_CARD = null;
                } else {
                    tvMemberName.setText("无");
                    tvMemberCard.setText("无");
                    tvBalance.setText("0.00");
                    tvIntegral.setText("0.00");
                    tv_VipName.setText("选择会员");
                }
                if (result.contains("没有任何执行操作")) {
                    Intent intent = new Intent(MemberChargeManagementActivity.this, CheckMemberInfoActivity.class);
                    intent.putExtra("number", content);
                    startActivityForResult(intent, 888);
                }

            }
        };
        vippresenter.attachView(vipView);

        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {

                    //必须刷卡
                    if ("214".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            rChoiseVip.setEnabled(false);
                            llScan.setEnabled(false);
                            llScan.setBackgroundColor(getResources().getColor(R.color.lightgray));
                            et_search_memberchargemanagementactivity.setHint("请使用刷卡机刷卡");
                            et_search_memberchargemanagementactivity.setFocusable(false);
                            et_search_memberchargemanagementactivity.setFocusableInTouchMode(false);
                            iv_scan_memberchargemanagementactivity.setEnabled(false);


                        } else {
                            rChoiseVip.setEnabled(true);
                            llScan.setEnabled(true);
                            llScan.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                            et_search_memberchargemanagementactivity.setHint("请输入会员卡号/手机号");
                            et_search_memberchargemanagementactivity.setFocusable(true);
                            et_search_memberchargemanagementactivity.setFocusableInTouchMode(true);
                            iv_scan_memberchargemanagementactivity.setEnabled(true);
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
                                    rChoiseVip.setEnabled(false);
                                    llScan.setEnabled(false);
                                    llScan.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                    et_search_memberchargemanagementactivity.setHint("请使用刷卡机刷卡");
                                    et_search_memberchargemanagementactivity.setFocusable(false);
                                    et_search_memberchargemanagementactivity.setFocusableInTouchMode(false);
                                    iv_scan_memberchargemanagementactivity.setEnabled(false);


                                } else {
                                    rChoiseVip.setEnabled(true);
                                    llScan.setEnabled(true);
                                    llScan.setBackground(getResources().getDrawable(R.drawable.shape_border_view_radius));
                                    et_search_memberchargemanagementactivity.setHint("请输入会员卡号/手机号");
                                    et_search_memberchargemanagementactivity.setFocusable(true);
                                    et_search_memberchargemanagementactivity.setFocusableInTouchMode(true);
                                    iv_scan_memberchargemanagementactivity.setEnabled(true);
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MemberChargeManagementActivity.this, "获取系统开关失败" + msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(MemberChargeManagementActivity.this, HttpAPI.API().PRE_LOAD, callBack);
    }

    private void postchargelist() {
        /*chargeList.clear();
        adapter.notifyDataSetChanged();*/
        RequestParams params = new RequestParams();
        params.put("Card", tvMemberCard.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Adduserbean adduserbean = CommonFun.JsonToObj(responseString, Adduserbean.class);

                ChargeListBean chargeListBean = CommonFun.JsonToObj(responseString, ChargeListBean.class);
                chargeList.clear();
                if (moveTag) {
                    chargeList.clear();
                    moveTag = false;
                }
                chargeList.addAll(chargeListBean.getData());
                ArrayList<ChargeListBean.DataBean> list = new ArrayList<>();
                list.addAll(chargeListBean.getData());
                if (chargeList.size() < 1) {
                    CustomToast.makeText(MemberChargeManagementActivity.this, "该会员没有计次信息", Toast.LENGTH_SHORT).show();
                }
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                mJiCiConsumeAdapter.setdata(chargeList);
                mJiCiConsumeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(String msg) {
                Le("failure");
                refreshLayout.setRefreshing(false);
                refreshLayout.setLoading(false);
                chargeList.clear();
                mJiCiConsumeAdapter.notifyDataSetChanged();
                CustomToast.makeText(MemberChargeManagementActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().CHARGELIST, params, callBack);

        mJiCiConsumeAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == MyApplication.SCAN_RETURN) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            et_search_memberchargemanagementactivity.setText(scanResult);
        }

        if (requestCode == 888 && resultCode == 2222) {
            mMemberInfo = (AllMemberListBean.DataBean.DataListBean) data.getSerializableExtra("VIP");
            if (mMemberInfo.getVIP_Name() != null && !"".equals(mMemberInfo.getVIP_Name())) {
                tv_VipName.setText(mMemberInfo.getVIP_Name());
            }
            if (mMemberInfo != null) {
                isSearch = false;
                et_search_memberchargemanagementactivity.setText(mMemberInfo.getVCH_Card());
                if (mMemberInfo.getVIP_Name() != null) {
                    tvMemberName.setText(mMemberInfo.getVIP_Name());
                } else {
                    tvMemberName.setText("无");
                }
                tvMemberCard.setText(mMemberInfo.getVCH_Card());
                tvBalance.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableBalance() + ""));
                tvIntegral.setText(Decima2KeeplUtil.stringToDecimal(mMemberInfo.getMA_AvailableIntegral() + ""));
//                postVip(mMemberInfo.getVCH_Card());
            } else {
                tvMemberName.setText("散客");
                tvMemberCard.setText("00000");
                tvBalance.setText("0.00");
                tvIntegral.setText("0.00");
                tv_VipName.setText("选择会员");
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (isMemberCharge) {
//            postchargelist();
            moveTag = true;
        }
    }


    private class InputHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1://1输入完成
                    doQurry();
                    break;
            }
        }
    }


    /**
     * 查询会员
     */
    private void doQurry() {
        hasTimerDown = true;
        if (System.currentTimeMillis() - pretime > 500) {
            vippresenter.postVip(content,isSearch);
            hasTimerDown = false;
        } else {
            mInputHandler.sendEmptyMessageDelayed(1, 300);
        }
    }
}