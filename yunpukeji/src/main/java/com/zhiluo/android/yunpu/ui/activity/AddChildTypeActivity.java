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
 * Date: 2017/11/29 17:53
 * Author: 罗咏哲
 * Description: 新增子分类
 * Version:
 */
public class AddChildTypeActivity extends Activity {

    @Bind(R.id.rb_sync_no)
    RadioButton rbSyncNo;
    @Bind(R.id.rb_sync_yes)
    RadioButton rbSyncYes;
    @Bind(R.id.rg_add_child_type)
    RadioGroup rgAddChildType;
    @Bind(R.id.et_add_child_type_main)
    EditText etAddChildTypeMain;
    @Bind(R.id.et_add_child_type_child)
    EditText etAddChildTypeChild;
    @Bind(R.id.tv_add_child_type_cancel)
    TextView tvAddChildTypeCancel;
    @Bind(R.id.tv_add_child_type_confirm)
    TextView tvAddChildTypeConfirm;
    private String mParentGid, mParentName;//主分类GID,主分类名称
    private int mParentSync, mChildSync;//父分类同步状态，子分类同步状态


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child_type);
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
        mParentGid = getIntent().getStringExtra("PT_GID");
        mParentSync = getIntent().getIntExtra("SYNC", 0);
        mParentName = getIntent().getStringExtra("PT_NAME");
        etAddChildTypeMain.setText(mParentName);
        if (mParentSync == 0) {
            rgAddChildType.check(rbSyncNo.getId());
            rbSyncYes.setEnabled(false);
        }
    }

    private void setListener() {
        tvAddChildTypeCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvAddChildTypeConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etAddChildTypeChild.getText())) {
                    addChildType();
                } else {
                    CustomToast.makeText(AddChildTypeActivity.this, "请输入子分类名称！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rgAddChildType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbSyncNo.getId()) {
                    mChildSync = 0;
                }
                if (checkedId == rbSyncYes.getId()) {
                    mChildSync = 1;
                }
            }
        });
    }

    private void addChildType() {
        RequestParams params = new RequestParams();
        params.put("PT_Name", etAddChildTypeChild.getText().toString());
        params.put("PT_Parent", mParentGid);
        params.put("PT_SynType", mChildSync);
        params.put("PT_Remark", "");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                CustomToast.makeText(AddChildTypeActivity.this, "添加成功,请刷新页面！", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddChildTypeActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, HttpAPI.API().ADDTYPE, params, callBack);
    }


}
