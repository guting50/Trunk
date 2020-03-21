package com.zhiluo.android.yunpu.paymanager.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

/**
 * Created by ZPH on 2019-05-22.
 */

public class AddExpensesMainTypeActivity extends BaseActivity {

    private EditText edmaintypename,etremark;

    private TextView edsave;
    //    private int mSyncType = 0;
    private TextView mBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_add_main_type);
        initViews();
        listener();
    }

    private void listener() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddExpensesMainTypeActivity.this.finish();
            }
        });
        edsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edmaintypename.getText().toString()) || edmaintypename.getText().toString().equals("")) {
                    CustomToast.makeText(AddExpensesMainTypeActivity.this, "请输入主分类名！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    addMainType();
                }
            }
        });
//        rgAddMainType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == rbSyncNo.getId()) {
//                    mSyncType = 0;
//                }
//                if (checkedId == rbSyncYes.getId()) {
//                    mSyncType = 1;
//                }
//            }
//        });
    }

    /**
     * 添加主分类
     */
    private void addMainType() {
        RequestParams params = new RequestParams();
        params.put("GID", "");
        params.put("Name", edmaintypename.getText().toString());
        params.put("ParentGID", "");
        params.put("Remark", etremark.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(AddExpensesMainTypeActivity.this, "添加成功,请刷新页面！", Toast.LENGTH_SHORT).show();
                AddExpensesMainTypeActivity.this.finish();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddExpensesMainTypeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, HttpAPI.API().Expenses_AddType, params, callBack);
    }

    private void initViews() {
        edmaintypename = findViewById(R.id.ed_main_type_name);
        etremark = findViewById(R.id.et_remark_detail);
        edsave = findViewById(R.id.te_save);

        mBack = findViewById(R.id.tv_back);
    }
}
