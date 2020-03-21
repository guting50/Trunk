package com.zhiluo.android.yunpu.goods.manager.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.AddChildTypeActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

/**
 * Created by ${YSL} on 2018-04-19.
 */

public class YSLAddChildTypeActivity extends AppCompatActivity {
    private TextView mBack, temaintypename;
    private EditText edchildetypename,etremark;
    private RadioButton rbSyncNo, rbSyncYes;
    private TextView edsave;
    private RadioGroup rgAddChildType;
    private String mParentGid, mParentName;//主分类GID,主分类名称
//    private int mParentSync, mChildSync;//父分类同步状态，子分类同步状态

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_add_child_type);
        initView();
        initDatas();
        setListener();
    }

    private void initDatas() {
        mParentGid = getIntent().getStringExtra("PT_GID");
//        mParentSync = getIntent().getIntExtra("SYNC", 0);
        mParentName = getIntent().getStringExtra("PT_NAME");
        temaintypename.setText(mParentName);
//        if (mParentSync == 0) {
//            rgAddChildType.check(rbSyncNo.getId());
//            rbSyncYes.setEnabled(false);
//        }

    }

    private void setListener() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YSLAddChildTypeActivity.this.finish();
            }
        });
        edsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edchildetypename.getText().toString()) || edchildetypename.getText().toString().equals("")) {
                    CustomToast.makeText(YSLAddChildTypeActivity.this, "请输入二级分类名称！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    addChildType();
                }

            }
        });
    }

    private void initView() {
        edchildetypename = findViewById(R.id.ed_child_type_name);
        etremark = findViewById(R.id.et_remark_detail);
        rbSyncNo = findViewById(R.id.rb_sync_no);
        rbSyncYes = findViewById(R.id.rb_sync_yes);
        edsave = findViewById(R.id.te_save);
        rgAddChildType = findViewById(R.id.rg_add_child_type);
        mBack = findViewById(R.id.tv_back);
        temaintypename = findViewById(R.id.te_main_type_name);
    }

    private void addChildType() {
        RequestParams params = new RequestParams();
        params.put("PT_Name", edchildetypename.getText().toString());
        params.put("PT_Parent", mParentGid);
//        params.put("PT_SynType", mChildSync);
        params.put("PT_Remark", etremark.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(YSLAddChildTypeActivity.this, "添加成功,请刷新页面！", Toast.LENGTH_SHORT).show();
                YSLAddChildTypeActivity.this.finish();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(YSLAddChildTypeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, HttpAPI.API().ADDTYPE, params, callBack);
    }
}
