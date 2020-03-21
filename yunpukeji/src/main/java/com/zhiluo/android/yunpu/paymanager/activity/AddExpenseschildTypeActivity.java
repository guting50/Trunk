package com.zhiluo.android.yunpu.paymanager.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.zhiluo.android.yunpu.goods.manager.activity.YSLAddChildTypeActivity;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

/**
 * Created by ZPH on 2019-05-22.
 */

public class AddExpenseschildTypeActivity extends BaseActivity {

    private TextView mBack, temaintypename;
    private EditText edchildetypename,etremark;
    private RadioButton rbSyncNo, rbSyncYes;
    private TextView edsave;
    private RadioGroup rgAddChildType;
    private String mParentGid, mParentName;//主分类GID,主分类名称


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_add_child_type);
        initView();
        initDatas();
        setListener();
    }

    private void initDatas() {
        mParentGid = getIntent().getStringExtra("ET_GID");
//        mParentSync = getIntent().getIntExtra("SYNC", 0);
        mParentName = getIntent().getStringExtra("ET_NAME");
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
                AddExpenseschildTypeActivity.this.finish();
            }
        });
        edsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edchildetypename.getText().toString()) || edchildetypename.getText().toString().equals("")) {
                    CustomToast.makeText(AddExpenseschildTypeActivity.this, "请输入二级分类名称！", Toast.LENGTH_SHORT).show();
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

        params.put("GID", "");
        params.put("Name", edchildetypename.getText().toString());
        params.put("ParentGID", mParentGid);
        params.put("Remark", etremark.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(AddExpenseschildTypeActivity.this, "添加成功,请刷新页面！", Toast.LENGTH_SHORT).show();
                AddExpenseschildTypeActivity.this.finish();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddExpenseschildTypeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, HttpAPI.API().Expenses_AddType, params, callBack);
    }
}
