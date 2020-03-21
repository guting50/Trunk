package com.zhiluo.android.yunpu.ui.activity.staff;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.NoticeEvent;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddDepActivity extends Activity {

    @Bind(R.id.et_add_dep_name)
    EditText etAddDepName;
    @Bind(R.id.et_add_dep_remark)
    EditText etAddDepRemark;
    @Bind(R.id.btn_add_dep_confirm)
    Button btnAddDepConfirm;
    @Bind(R.id.btn_add_dep_cancel)
    Button btnAddDepCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dep);
        ButterKnife.bind(this);
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

    private void setListener() {
        //取消
        btnAddDepCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //确认
        btnAddDepConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etAddDepName.getText())) {
                    addDep();
                } else {
                    CustomToast.makeText(AddDepActivity.this, "请输入部门名称", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 新增部门
     */
    private void addDep() {
        RequestParams params = new RequestParams();
        params.put("DM_Name", etAddDepName.getText().toString());
        params.put("DM_Remark", TextUtils.isEmpty(etAddDepRemark.getText()) ? "" : etAddDepRemark.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                finish();
                EventBus.getDefault().post(new NoticeEvent());
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddDepActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交...", false);
        HttpHelper.post(this, "Dept/AddDept", params, callBack);
    }
}
