package com.zhiluo.android.yunpu.ui.activity.label;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.NoticeEvent;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.MemberLabel;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 编辑标签
 */
public class EditLabelActivity extends BaseActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_edit_label_save)
    TextView tvSave;
    @Bind(R.id.et_edit_label_name)
    EditText etName;
    //    @Bind(R.id.tv_edit_label_color)
//    TextView tvColor;
//    @Bind(R.id.rb_edit_label_member)
//    RadioButton rbMember;
//    @Bind(R.id.rb_edit_label_fans)
//    RadioButton rbFans;
    @Bind(R.id.et_edit_label_remark)
    EditText etRemark;

    private MemberLabel.DataBean mLabelBean;
    private SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_label);
        ButterKnife.bind(this);
        mLabelBean = (MemberLabel.DataBean) getIntent().getSerializableExtra("Label");
        init(mLabelBean);
        setListener();
    }

    private void init(MemberLabel.DataBean bean) {
        if (bean != null) {
            etName.setText(bean.getML_Name());
            etName.setSelection(bean.getML_Name().length());
//            tvColor.setBackgroundColor(Color.parseColor(bean.getML_ColorValue()));
//            if (bean.getML_Type() == 0) {
//                rbMember.setChecked(true);
//            }
//            if (bean.getML_Type() == 1) {
//                rbFans.setChecked(true);
//            }
            etRemark.setText(bean.getML_Remark() == null ? "" : bean.getML_Remark());
        }
    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etName.getText())) {
                    edit();
                } else {
                    CustomToast.makeText(EditLabelActivity.this, "标签名称不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void edit() {
        RequestParams params = new RequestParams();
        params.put("GID", mLabelBean.getML_GID());
        params.put("ML_Name", etName.getText().toString());
        params.put("ML_ColorValue", mLabelBean.getML_ColorValue());
        params.put("ML_Remark", TextUtils.isEmpty(etRemark.getText()) ? "" : etRemark.getText().toString());
        params.put("ML_Type", mLabelBean.getML_Type());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(EditLabelActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("编辑成功")
                        .setConfirmText("确认")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                                ActivityManager.getInstance().exit();
                                Intent intent = new Intent(EditLabelActivity.this,LabelManagerActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .show();
                EventBus.getDefault().post(new NoticeEvent());
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditLabelActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交...", false);
        HttpHelper.post(this, "MemberLabel/EditMemberLabel", params, callBack);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
            mSweetAlertDialog = null;
        }
    }
}
