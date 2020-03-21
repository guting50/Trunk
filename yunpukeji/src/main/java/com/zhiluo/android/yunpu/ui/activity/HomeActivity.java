package com.zhiluo.android.yunpu.ui.activity;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.jauker.widget.BadgeView;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.fragments.MineFragment;
import com.zhiluo.android.yunpu.fragments.SummaryFragment;
import com.zhiluo.android.yunpu.fragments.SurfaceFragment;
import com.zhiluo.android.yunpu.home.jsonbean.MessageCenterJsonBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.ui.bean.Indurybean;
import com.zhiluo.android.yunpu.ui.fragment.MessageFragment;
import com.zhiluo.android.yunpu.ui.fragment.ReportFragment;
import com.zhiluo.android.yunpu.ui.fragment.ShopFragment;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CardOperationUtils;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.DensityUtil;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends BaseActivity {
    private RadioGroup rgBottomMenu;//底部菜单
    private RadioButton rbHome, rbMessage, rbReport, rbShop, rbMy;//首页、消息、服务、我的
    private FrameLayout frameLayout;

    private FragmentManager mFragmentManager;
    private SurfaceFragment mSurfaceFragment;
    private MessageFragment mMessageFragment;
    private ReportFragment mReportFragment;
    private SummaryFragment summaryFragment;
    private MineFragment mineFragment;
    private ShopFragment mShopFragment;
    private String mStoreName, mStoreGid, mStoreOwer;//店铺名称，店铺GID，店铺所有人
    private int mImageSize;//下方菜单图标大小
    private Button btnMessage;
    private BadgeView badgeView;
    private LoginUpbean mLoginBean;
    private boolean mZongHeTongji = false;
    private PopupWindow mMainTypePW;
    private Dialog chooseDialog;
    private Indurybean indurybean;
    private List<String> indurylist = new ArrayList<>();
    public static String induryGid;
    private RelativeLayout rlhome;
    private EditText edit;
    private long exitTime = 0;
    private NfcAdapter mNFCAdapter;
    PendingIntent mPendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(null);
        setContentView(R.layout.activity_ysl_home);
        if (MyApplication.IS_SUNMI_POS_V1S_DEVICE) {
            // 获取默认的NFC控制器
            mNFCAdapter = NfcAdapter.getDefaultAdapter(this);
            mPendingIntent =
                    PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        }
        //设置沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        EventBus.getDefault().register(this);
        initView();
        initVariable();
        // loadData();//去掉消息条数的显示
        setListener();
    }

    /**
     * 初始化控件
     */
    private void initView() {

        mImageSize = DensityUtil.dip2px(HomeActivity.this, 22);
        rgBottomMenu = (RadioGroup) findViewById(R.id.rg_homepage_bottom);
        rbHome = (RadioButton) findViewById(R.id.rb_homepage_home);
        rbReport = (RadioButton) findViewById(R.id.rb_homepage_report);

        rbMy = (RadioButton) findViewById(R.id.rb_homepage_my);
        frameLayout = (FrameLayout) findViewById(R.id.fl_homepage);
        Drawable home = getResources().getDrawable(R.drawable.homepage_home_selector);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        home.setBounds(0, 0, mImageSize, mImageSize);
        //设置图片在文字的哪个方向
        rbHome.setCompoundDrawables(null, home, null, null);
        rlhome = findViewById(R.id.home_rl);

        Drawable report = getResources().getDrawable(R.drawable.homepage_report_selector);
        report.setBounds(0, 0, mImageSize, mImageSize);
        rbReport.setCompoundDrawables(null, report, null, null);

        Drawable my = getResources().getDrawable(R.drawable.homepage_my_selector);
        my.setBounds(0, 0, mImageSize, mImageSize);
        rbMy.setCompoundDrawables(null, my, null, null);
        btnMessage = (Button) findViewById(R.id.btn_message);
        mMainTypePW = new PopupWindow(this);

    }


    @Override
    public void onResume() {
        super.onResume();
        if (mNFCAdapter != null) {
            //允许隐式启动
            mNFCAdapter.enableForegroundDispatch(this, mPendingIntent, null, null);
        }

    }

    /**
     * 初始化变量
     */
    private void initVariable() {
        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        if (mLoginBean != null) {
            getIduryType();
            if (mLoginBean.getData().getShopList().get(0).getSM_Industry()==null ||mLoginBean.getData().getShopList().get(0).getSM_Industry().equals("")){
                rlhome.post(new Runnable() {
                    @Override
                    public void run() {
                        showMainTypePW();
                        YSLUtils.setBackgroundAlpha(0.5f, HomeActivity.this);

                    }
                });
            }
            if (mLoginBean.getData().getUM_IsAmin() == 1) {//判断是不是超管
                mZongHeTongji = true;
            } else {
                if (mLoginBean.getData().getMenuInfoList().size()>0) {
                    judgelimit();
                } else {
                    CustomToast.makeText(HomeActivity.this, "获取报表管理权限失败，请重新登录后尝试", Toast.LENGTH_SHORT).show();
                }
            }
        }

        mStoreName = getIntent().getStringExtra("shopname");
        mStoreGid = getIntent().getStringExtra("shopGid");
        mStoreOwer = getIntent().getStringExtra("creator");
        mFragmentManager = getSupportFragmentManager();
        // mHomeFragment = new HomeFragment();
        mSurfaceFragment = new SurfaceFragment();

        summaryFragment=new SummaryFragment();
        mineFragment = new MineFragment();
        // mShopFragment = new ShopFragment();
        startFragment(mSurfaceFragment);
    }

    private void showMainTypePW() {
//        View viewToLoad = LayoutInflater.from(this.getParent()).inflate(R.layout.activity_ysl_home, null);
//        this.setContentView(viewToLoad);
        View view = LayoutInflater.from(this).inflate(R.layout.home_indury, null, false);
       RelativeLayout rlpop = view.findViewById(R.id.rl_pop);
        edit = view.findViewById(R.id.ed_edit_main_type);
        TextView save = view.findViewById(R.id.tv_save);
        mMainTypePW.setContentView(view);
        mMainTypePW.setBackgroundDrawable(new ColorDrawable());
        mMainTypePW.setOutsideTouchable(false);
        mMainTypePW.setFocusable(false);
        mMainTypePW.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mMainTypePW.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        mMainTypePW.setAnimationStyle(R.style.pop_show_style);
        mMainTypePW.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edit.getText().toString()) || edit.getText().toString().equals("")) {
                    CustomToast.makeText(HomeActivity.this, "请先选择行业", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    saveInduryType();
//                    YSLUtils.setBackgroundAlpha(1.0f, HomeActivity.this);
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(indurylist, edit);
            }
        });


    }

    private void showChooseDialog(List<String> mlist, final TextView mTextView) {
        int j =0;
        for (int i =0;i<mlist.size();i++){
            if (mlist.get(i).equals(mTextView.getText().toString())){
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        induryGid = indurybean.getData().get(position).getGID()+"";
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();

    }

    private void getIduryType(){
        RequestParams params = new RequestParams();
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                indurybean = CommonFun.JsonToObj(responseString,Indurybean.class);
                for (int i = 0;i<indurybean.getData().size();i++){
                    indurylist.add(indurybean.getData().get(i).getIT_Name());
                    if (mLoginBean.getData().getShopList().get(0).getSM_Industry()!=null &&mLoginBean.getData().getShopList().get(0).getSM_Industry().equals(indurybean.getData().get(i).getIT_Name())){
                        induryGid = indurybean.getData().get(i).getGID()+"";
                    }
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT);
            }
        };
        callBack.setmAPI("Shops/GetIndustryList");
        callBack.setLoadingAnimation(this,"请稍后",false);
        HttpHelper.post(this, HttpAPI.API().INDURY_TYPE,params,callBack);
    }
    private void saveInduryType(){
        RequestParams params = new RequestParams();
        params.put("GID",induryGid);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(HomeActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                mMainTypePW.dismiss();
                YSLUtils.setBackgroundAlpha(1.0f, HomeActivity.this);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT);
            }
        };
        callBack.setmAPI("Shops/UpdateShopIndustry");
        callBack.setLoadingAnimation(this,"请稍后",false);
        HttpHelper.post(MyApplication.getmContext(),HttpAPI.API().EDIT_INDURY_TYPE,params,callBack);
    }


    private void judgelimit(){//判断报表管理权限
        for (int i = 0; i < mLoginBean.getData().getMenuInfoList().size(); i++){
            if (mLoginBean.getData().getMenuInfoList().get(i).getMM_Name().equals("报表管理")){
                mZongHeTongji = true;
            }
        }
    }

    /**
     * 设置监听
     */
    private void setListener() {
        rgBottomMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //首页
                if (checkedId == rbHome.getId()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("StoreName", mStoreName);
                    startFragment(mSurfaceFragment, bundle);
                }

               //统计

                    if (checkedId == rbReport.getId()) {
                        if (mLoginBean.getData().getUM_IsAmin() != 1){
                            if (!mZongHeTongji){
                                CustomToast.makeText(HomeActivity.this, "报表管理权限未开启，请设置", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("StoreGid", mStoreGid);
                        // startFragment(mMyFragment, bundle);
                        startFragment(summaryFragment, bundle);


                    }

                //我的
                if (checkedId == rbMy.getId()) {
                    Bundle bundle = new Bundle();
                    bundle.putString("StoreGid", mStoreGid);
                    // startFragment(mMyFragment, bundle);
                    startFragment(mineFragment, bundle);
                }
            }
        });
    }

    private void startFragment(Fragment fm) {
        if (!fm.isAdded()) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_homepage, fm);
            transaction.commit();
        }
    }

    private void startFragment(Fragment fm, Bundle bundle) {
        if (!fm.isAdded()) {
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            transaction.replace(R.id.fl_homepage, fm);
            fm.setArguments(bundle);
            transaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            CustomToast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            ActivityCollector.finishAll();
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }

//        new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
//                .setTitleText("提示")
//                .setCancelText("取消")
//                .setConfirmText("确认")
//                .setContentText("真的要退出吗？")
//                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                    @Override
//                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                        sweetAlertDialog.dismiss();
//                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
//                        finish();
//                      //  android.os.Process.killProcess(android.os.Process.myPid());
//                       // ActivityCollector.finishAll();
//                       // System.exit(0);
//                    }
//                }).show();
    }


//    private void getMessage() {
//        String mAccountGid = (String) uSharedPreferencesUtiles.get(HomeActivity.this, "Account_GID", "");
//        String mCommanyGid = (String) uSharedPreferencesUtiles.get(HomeActivity.this, "CY_GID", "");
//        RequestParams params = new RequestParams();
//        params.put("User_GID", mAccountGid);
//        params.put("CY_GID", mCommanyGid);
//        params.put("PageIndex", 1);
//        params.put("PageSize", 10);
//        HttpHelper.post(HomeActivity.this, "Notice/QueryDataList", params, new CallBack() {
//            @Override
//            public void onSuccess(String responseString, Gson gson) {
//                int num = 0;
//                MessageCenterJsonBean bean = CommonFun.JsonToObj(responseString, MessageCenterJsonBean.class);
//                for (int i = 0; i < bean.getData().getDataList().size(); i++) {
//                    if (bean.getData().getDataList().get(i).getPopState() != 1) {
//                        num++;
//                    }
//                }
//                if (badgeView == null) {
//                    badgeView = new BadgeView(HomeActivity.this);
//                }
//                badgeView.setTargetView(btnMessage);
//                badgeView.setBadgeCount(num);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//
//            }
//        });
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateMessageNum(MessageCenterJsonBean bean) {
        int num = 0;
        for (int i = 0; i < bean.getData().getDataList().size(); i++) {
            if (bean.getData().getDataList().get(i).getPopState() != 1) {
                num++;
            }
        }
        if (badgeView == null) {
            badgeView = new BadgeView(HomeActivity.this);
            badgeView.setTargetView(btnMessage);
        }
        badgeView.setBadgeCount(num);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }


}
