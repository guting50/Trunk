package com.zhiluo.android.yunpu.sms.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsManagerChooseActivity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ZPH on 2019-03-30.
 */

public class AddSmsTemplatesActivity extends Activity {
    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.tv_title_sure)
    TextView tvTitleSure;
    @Bind(R.id.tv_surplus_sms_title)
    TextView tvSurplusSmsTitle;
    @Bind(R.id.et_msg_content_title)
    EditText etMsgContentTitle;
    @Bind(R.id.et_msg_content_name)
    EditText etMsgContentName;
    @Bind(R.id.sp_sms_wildcard)
    Spinner spSmsWildcard;
    @Bind(R.id.activity_send_msg)
    LinearLayout activitySendMsg;


    private ArrayAdapter<String> mSpinnerAdapter;
    private ArrayList<String> TypeList = new ArrayList<>();//类型
    private SweetAlertDialog mSweetAlertDialog;//提示框

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sms);
        ButterKnife.bind(this);
        initData();
        initLisenter();
    }

    private void initLisenter() {
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitleSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (judge()){
                    addSmstemplate();
                }
            }
        });

        spSmsWildcard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position ==1){
                    etMsgContentName.setText(etMsgContentName.getText().toString()+"#会员名称#");
                    etMsgContentName.setSelection(etMsgContentName.getText().toString().length());
                }else if (position ==2){
                    etMsgContentName.setText(etMsgContentName.getText().toString()+"#店铺名称#");
                    etMsgContentName.setSelection(etMsgContentName.getText().toString().length());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void initData() {
        TypeList.add("请选择");
        TypeList.add("会员名称");
        TypeList.add("店铺名称");

        mSpinnerAdapter = new ArrayAdapter<String>(AddSmsTemplatesActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, TypeList);
        spSmsWildcard.setAdapter(mSpinnerAdapter);
        spSmsWildcard.setSelection(0);

    }
    private boolean judge() {
        if (etMsgContentTitle.getText().toString().isEmpty()||etMsgContentTitle.getText().toString().equals("")){
            CustomToast.makeText(AddSmsTemplatesActivity.this,"请输入模板标题", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (etMsgContentName.getText().toString().isEmpty()||etMsgContentName.getText().toString().equals("")){
            CustomToast.makeText(AddSmsTemplatesActivity.this,"请输入模板内容", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    private void addSmstemplate(){
        RequestParams params = new RequestParams();
        params.put("GID","");
        params.put("TM_Name",etMsgContentTitle.getText().toString());
        params.put("TM_Content",etMsgContentName.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(AddSmsTemplatesActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("提示");
                mSweetAlertDialog.setContentText("添加模板成功");
                mSweetAlertDialog.setCancelable(false);
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        finish();
                    }
                });
                mSweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddSmsTemplatesActivity.this,msg, Toast.LENGTH_SHORT).show();

            }
        };

        HttpHelper.post(this,HttpAPI.API().ADDTEMPLATES,params,callBack);


    }

}
