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
import com.zhiluo.android.yunpu.ui.activity.AddMainTypeActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import org.w3c.dom.Text;

import butterknife.Bind;

/**
 * Created by ${YSL} on 2018-04-19.
 */

public class YSLAddMaintypeActivity extends AppCompatActivity {
    private EditText edmaintypename,etremark;
    private RadioButton rbSyncNo, rbSyncYes;
    private TextView edsave;
    private RadioGroup rgAddMainType;
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
                YSLAddMaintypeActivity.this.finish();
            }
        });
        edsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edmaintypename.getText().toString()) || edmaintypename.getText().toString().equals("")) {
                    CustomToast.makeText(YSLAddMaintypeActivity.this, "请输入主分类名！", Toast.LENGTH_SHORT).show();
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
        params.put("PT_Name", edmaintypename.getText().toString());
        params.put("PT_Parent", "");
//        params.put("PT_SynType", mSyncType);
        params.put("PT_Remark", etremark.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(YSLAddMaintypeActivity.this, "添加成功,请刷新页面！", Toast.LENGTH_SHORT).show();
                YSLAddMaintypeActivity.this.finish();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(YSLAddMaintypeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, HttpAPI.API().ADDTYPE, params, callBack);
    }

    private void initViews() {
        edmaintypename = findViewById(R.id.ed_main_type_name);
        etremark = findViewById(R.id.et_remark_detail);
        rbSyncNo = findViewById(R.id.rb_sync_no);
        rbSyncYes = findViewById(R.id.rb_sync_yes);
        edsave = findViewById(R.id.te_save);
        rgAddMainType = findViewById(R.id.rg_add_main_type);
        mBack = findViewById(R.id.tv_back);
    }
}
