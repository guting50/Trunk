package com.zhiluo.android.yunpu.statistics.order.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.print.util.HttpGetPrintContents;
import com.zhiluo.android.yunpu.statistics.account.adapter.InflateCountAdapter;
import com.zhiluo.android.yunpu.statistics.order.adapter.JiCiRecoderAdapter;
import com.zhiluo.android.yunpu.statistics.order.adapter.TimesDetailAdapter;
import com.zhiluo.android.yunpu.statistics.order.bean.TimesConsumeOrderBean;
import com.zhiluo.android.yunpu.statistics.order.bean.TimesConsumeReportBean;
import com.zhiluo.android.yunpu.statistics.order.bean.TimesGoodDetailBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.sql.Time;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 计次消费详情记录
 */
public class TimesConsumeDetailActivity extends BaseActivity {
    private TimesConsumeOrderBean.DataBean.DataListBean mBean;
    private TextView mTvTitle, mTvOrderNo, mTvVipName, mTvVipCard, mTvPhone, mTvShopName, mTvCreator,
            mTvConPhone, mTvDevice,mTvStatus, mTvCreateTime, mTvOperation, mTvDetail,mTvRemark;
    private String mGid;
    private BaseListView mDialogListView;
    private TimesDetailAdapter mTimesDetailAdapter;
    private TimesGoodDetailBean mTimesGoodDetailBean;

    private RecyclerView recyclerView;
    private JiCiRecoderAdapter adapter;
    private ImageView igMore;
    private PopupWindow popupWindow;
    private SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jici_item_recoder);
        initView();
        initVariable();
        setListener();
        upChildView();

    }

    private void initView() {
        mTvTitle = (TextView) findViewById(R.id.tv_no_confirm_title);
        mTvTitle.setText("计次消费详情");
        mTvOrderNo = (TextView) findViewById(R.id.tv_times_consume_order);
        mTvVipName = (TextView) findViewById(R.id.tv_times_consume_vip_name);
        mTvVipCard = (TextView) findViewById(R.id.tv_times_consume_vip_card);
        mTvPhone = (TextView) findViewById(R.id.tv_times_consume_vip_phone);
        mTvShopName = (TextView) findViewById(R.id.tv_times_consume_shop_name);
        mTvCreator = (TextView) findViewById(R.id.tv_times_consume_create_name);
        mTvConPhone = (TextView) findViewById(R.id.tv_times_consume_con_phone);
        mTvDevice = (TextView) findViewById(R.id.tv_times_consume_device);
        mTvStatus = (TextView) findViewById(R.id.tv_times_consume_status);
        mTvCreateTime = (TextView) findViewById(R.id.tv_times_consume_create_time);
        mTvOperation = (TextView) findViewById(R.id.tv_times_consume_operation);
        recyclerView = findViewById(R.id.recycler_view);
        mTvRemark = findViewById(R.id.tv_goods_order_remark);
        igMore = findViewById(R.id.iv_more);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(manager);
    }

    private void initVariable() {
        mBean = (TimesConsumeOrderBean.DataBean.DataListBean) getIntent().getSerializableExtra("times");
        if (mBean != null) {
            mTvOrderNo.setText(mBean.getWO_OrderCode() == null ? "" : mBean.getWO_OrderCode());
            mTvVipName.setText(mBean.getVIP_Name() == null ? "" : mBean.getVIP_Name());
            mTvVipCard.setText(mBean.getVIP_Card() == null ? "" : mBean.getVIP_Card());
            mTvPhone.setText(mBean.getVIP_Phone() == null ? "" : mBean.getVIP_Phone());
            mTvShopName.setText(mBean.getSM_Name() == null ? "" : mBean.getSM_Name());
            mTvCreator.setText(mBean.getSM_Contacter() == null ? "" : mBean.getSM_Contacter());
            mTvConPhone.setText(mBean.getSM_Phone() == null ? "" : mBean.getSM_Phone());
            if ("2".equals(mBean.getWO_Device())) {
                mTvDevice.setText("安卓手机");
            }else if ("4".equals(mBean.getWO_Device())) {
                mTvDevice.setText("IOS");
            }else {
                mTvDevice.setText("浏览器");
            }
            mTvStatus.setText(mBean.getWO_State() == 0 ? "已完成":"已撤单");
            mTvRemark.setText(mBean.getWO_Remark()==null?"":mBean.getWO_Remark());
            mTvCreateTime.setText(mBean.getWO_UpdateTime() == null ? "" : mBean.getWO_UpdateTime());
            mTvOperation.setText(mBean.getWO_Creator() == null ? "" : mBean.getWO_Creator());
            mGid = mBean.getGID();
        }
    }

    private void setListener() {
        findViewById(R.id.tv_no_confirm_back).setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        igMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPop(v);
            }
        });

    }

    private void showPop(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_menu_cancel_order, null);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        popupWindow = new PopupWindow(this);
        popupWindow.setContentView(view);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setWidth(width / 2);
        popupWindow.setHeight(height / 5);
        popupWindow.showAsDropDown(v);
        RelativeLayout allreture = view.findViewById(R.id.r_all_reture);
        RelativeLayout print = view.findViewById(R.id.r_print);
        allreture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (MyApplication.IS_CANCEL_ORDER) {
                    showPasswordDialog();
                } else {
                    undoOrder();
                }
            }
        });
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                if (MyApplication.PRINT_IS_OPEN){
                    new HttpGetPrintContents(TimesConsumeDetailActivity.this, MyApplication.JCXF_PRINT_TIMES, mBean.getGID()).JCXF();
                }else {
                    CustomToast.makeText(TimesConsumeDetailActivity.this,"打印开关未开启", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * 密码输入框
     */
    private void showPasswordDialog() {
        LayoutInflater inflater = getLayoutInflater();
        final View dialog = inflater.inflate(R.layout.input_password_dialog_layout, (ViewGroup) findViewById(R.id.input_dialog));
        final EditText etPassword = (EditText) dialog.findViewById(R.id.et_input_dialog_password);
        Button btnCancel = (Button) dialog.findViewById(R.id.btn_input_cancel);
        Button btnConfirm = (Button) dialog.findViewById(R.id.btn_input_confirm);
        AlertDialog.Builder builder = new AlertDialog.Builder(TimesConsumeDetailActivity.this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setContentView(dialog);
        alertDialog.setView(dialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (!etPassword.getText().toString().isEmpty()&&etPassword.getText().toString().equals(MyApplication.CANCEL_PAW)){
                    undoOrder();
                }else {
                    CustomToast.makeText(TimesConsumeDetailActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


    private void showDetailDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View dialog = inflater.inflate(R.layout.dialog_report_layout, (ViewGroup) findViewById(R.id.dialog_report_detail));
        mDialogListView = (BaseListView) dialog.findViewById(R.id.lv_report_detail);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = builder.create();
        alertDialog.setContentView(dialog);
        alertDialog.setView(dialog);
        getServiceData(mGid);
    }

    private void getServiceData(String gid) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore myCookieStore = new PersistentCookieStore(TimesConsumeDetailActivity.this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("PageIndex", 1);
        params.put("PageSize", 50);
        params.put("WO_GID", gid == null ? "" : gid);
        client.post(HttpAPI.API().TIME_DETAIL, params, new MyTextHttpResponseHandler() {//WouldOrderDetail/QueryOrderDetailList
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mTimesGoodDetailBean = CommonFun.JsonToObj(responseString, TimesGoodDetailBean.class);
                // mTimesDetailAdapter = new TimesDetailAdapter(TimesConsumeDetailActivity.this, mTimesGoodDetailBean);
                //  mDialogListView.setAdapter(mTimesDetailAdapter);
//                dialog.show();
//                adapter = new JiCiRecoderAdapter(TimesConsumeDetailActivity.this, mTimesGoodDetailBean);
//                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(TimesConsumeDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void upChildView(){
        if (mBean.getWouldOrderDetail().size()>0){
//            mTimesGoodDetailBean = (TimesGoodDetailBean) mBean.getWouldOrderDetail();
            adapter = new JiCiRecoderAdapter(TimesConsumeDetailActivity.this, mBean);
            recyclerView.setAdapter(adapter);

        }else {
            getServiceData(mGid);
        }
    }

    private void undoOrder() {
        RequestParams params = new RequestParams();
        params.put("WO_GID", mBean.getGID());

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(TimesConsumeDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("撤单成功");
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        mSweetAlertDialog.dismiss();
                        finish();
                    }
                });
                mSweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(TimesConsumeDetailActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().UNDO_ORDER, params, callBack);
    }
}
