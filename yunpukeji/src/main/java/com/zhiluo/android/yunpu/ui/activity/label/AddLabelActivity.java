package com.zhiluo.android.yunpu.ui.activity.label;

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
import cn.pedant.SweetAlert.SweetAlertDialog;

public class AddLabelActivity extends Activity {

    @Bind(R.id.et_add_label_name)
    EditText etName;
    @Bind(R.id.et_add_label_remark)
    EditText etRemark;
    @Bind(R.id.btn_add_label_confirm)
    Button btnConfirm;
    @Bind(R.id.btn_add_label_cancel)
    Button btnCancel;

    private SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_label);
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
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etName.getText())) {
                    addLabel();
                } else {
                    CustomToast.makeText(AddLabelActivity.this, "请输入标签名称", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addLabel() {
        RequestParams params = new RequestParams();
        params.put("GID", "");
        params.put("ML_Name", etName.getText().toString());
        params.put("ML_ColorValue", "#FF8739");
        params.put("ML_Remark", TextUtils.isEmpty(etRemark.getText()) ? "" : etRemark.getText().toString());
        params.put("ML_Type", 0);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(AddLabelActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("新增成功！")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                                finish();
                            }
                        })
                        .setConfirmText("确认")
                        .show();
                EventBus.getDefault().post(new NoticeEvent());
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddLabelActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交...", false);
        HttpHelper.post(this, "MemberLabel/AddMemberLabel", params, callBack);
    }
}
