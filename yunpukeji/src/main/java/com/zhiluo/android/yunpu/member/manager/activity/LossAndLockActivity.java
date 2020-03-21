package com.zhiluo.android.yunpu.member.manager.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.consume.activity.MemberRechargeActivity;
import com.zhiluo.android.yunpu.member.manager.bean.MemberInfoBean;
import com.zhiluo.android.yunpu.member.manager.bean.YSLMemberInfoBean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ${YSL} on 2018-04-03.
 */

public class LossAndLockActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner mSpState;
    private int mVipState = 0;
    private Bundle mBundle;
    private TextView mVipCard;
    private YSLMemberInfoBean.DataBean.DataListBean mVipInfo;//会员信息
    private MemberInfoBean.DataBean mMemberInfo;//这也是会员信息
    private RelativeLayout mCommit;
    private TextView mBack;
    private Intent mIntentl;
    private Dialog chooseDialog;
    private List<String> mList=new ArrayList<>();
    private TextView teChoiseType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_lossandlock);
        initViews();
        initDatas();
        String[] data = getResources().getStringArray(R.array.vip_state);
        for (String str : data) {
            mList.add(str);
        }
    }

    private void initDatas() {
        mBundle = getIntent().getBundleExtra("bundle");
        mVipInfo = (YSLMemberInfoBean.DataBean.DataListBean) mBundle.getSerializable("YSL_VIP_INFO");//带计次的会员信息
        mVipCard.setText(mVipInfo.getVCH_Card());
        if (mVipInfo!=null){
            mVipState = mVipInfo.getVIP_State();
        }
        switch (mVipState){
            case 0:
                teChoiseType.setText("正常");
                break;
            case 1:
                teChoiseType.setText("锁定");
                break;
            case 2:
                teChoiseType.setText("挂失");
                break;
        }
        mIntentl=new Intent(this,YSLMemberDetailsActivity.class);
        //showStatus();
    }
    private void showChooseDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0;i<mlist.size();i++){
            if (mTextView.getText().toString().equals(mlist.get(i))){
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        mVipState=position;
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }
    @SuppressLint("WrongViewCast")
    private void initViews() {
        mSpState = findViewById(R.id.sp_dialog_gs_jg);
        mVipCard = findViewById(R.id.tv_card);
        mCommit = findViewById(R.id.re_ok);
        mVipCard = findViewById(R.id.tv_card);
        mBack = findViewById(R.id.tv_back);
        teChoiseType=findViewById(R.id.te_choise_type);

        mCommit.setOnClickListener(this);
        mBack.setOnClickListener(this);
        teChoiseType.setOnClickListener(this);


    }

    /**
     * @param state 状态
     *              提交修改
     */
    private void commitResult(final int state) {
        RequestParams params = new RequestParams();
        params.put("VCH_Card", mVipInfo.getVCH_Card());
        params.put("VIP_State", state);
        params.put("IS_Sms", true);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                final SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(LossAndLockActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                switch (state) {
                    case 0:
                        sweetAlertDialog.setTitleText("解挂成功!");
                        break;
                    case 1:
                        sweetAlertDialog.setTitleText("锁定成功!");
                        break;
                    case 2:
                        sweetAlertDialog.setTitleText("挂失成功!");
                        break;
                }
                sweetAlertDialog.setConfirmText("确定");
                sweetAlertDialog.setCancelable(false);
                sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        sweetAlertDialog.dismiss();
                        Intent intent = new Intent();

                        intent.putExtra("state", state);
                        setResult(92,intent);
                        finish();
                    }
                });
                sweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                int type = 3;
                if ("BuySms".equals(msg)) {
                    msg = "短信未发送,短信库存不足";
                    type = SweetAlertDialog.SUCCESS_TYPE;
                }else if ("SmsSign".equals(msg)){
                    msg = "短信未发送,未设置默认签名";
                    type = SweetAlertDialog.SUCCESS_TYPE;
                }else {
                    type = SweetAlertDialog.WARNING_TYPE;
                }
                new SweetAlertDialog(LossAndLockActivity.this, type)
                        .setTitleText("提示")
                        .setContentText(msg + "!")
                        .setConfirmText("了解")
                        .show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交...", false);
        HttpHelper.post(this, "VIP/CardLoss", params, callBack);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.re_ok:
                commitResult(mVipState);
                break;
            case R.id.tv_back:
                LossAndLockActivity.this.finish();
              /*  Bundle bundle = new Bundle();
                bundle.putSerializable("YSL_VIP_INFO_BACK", mVipInfo);
                mIntentl.putExtra("bundles", bundle);*/
                setResult(95, mIntentl);
                break;
            case R.id.te_choise_type:
                showChooseDialog(mList,teChoiseType);
                break;

        }
    }
}
