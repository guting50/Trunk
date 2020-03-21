package com.zhiluo.android.yunpu.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Date: 2017/11/29 17:13
 * Author: 罗咏哲
 * Description: 新增主分类
 * Version:
 */
public class AddMainTypeActivity extends Activity {

    @Bind(R.id.rb_sync_no)
    RadioButton rbSyncNo;
    @Bind(R.id.rb_sync_yes)
    RadioButton rbSyncYes;
    @Bind(R.id.rg_add_main_type)
    RadioGroup rgAddMainType;
    @Bind(R.id.et_add_main_type_name)
    EditText etAddMainTypeName;
    @Bind(R.id.tv_add_main_type_cancel)
    TextView tvAddMainTypeCancel;
    @Bind(R.id.tv_add_main_type_confirm)
    TextView tvAddMainTypeConfirm;
    private int mSyncType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_main_type);
        ButterKnife.bind(this);
        initView();
        setListener();
    }

    @Override
    public void onAttachedToWindow() {
        View view = getWindow().getDecorView();
        WindowManager.LayoutParams lp = (WindowManager.LayoutParams) view
                .getLayoutParams();
        lp.gravity = Gravity.CENTER;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindowManager().updateViewLayout(view, lp);
    }

    private void initView() {

    }

    private void setListener() {
        tvAddMainTypeCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvAddMainTypeConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etAddMainTypeName.getText())) {
                    addMainType();
                } else {
                    CustomToast.makeText(AddMainTypeActivity.this, "请输入主分类名！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rgAddMainType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbSyncNo.getId()) {
                    mSyncType = 0;
                }
                if (checkedId == rbSyncYes.getId()) {
                    mSyncType = 1;
                }
            }
        });
    }

    /**
     * 添加主分类
     */
    private void addMainType() {
        RequestParams params = new RequestParams();
        params.put("PT_Name", etAddMainTypeName.getText().toString());
        params.put("PT_Parent", "");
        params.put("PT_SynType", mSyncType);
        params.put("PT_Remark", "");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(AddMainTypeActivity.this, "添加成功,请刷新页面！", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddMainTypeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, HttpAPI.API().ADDTYPE, params, callBack);
    }

}
