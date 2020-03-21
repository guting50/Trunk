package com.zhiluo.android.yunpu.ui.activity.staff;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DepBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 编辑部门
 */
public class EditDepActivity extends AppCompatActivity {
    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_edit_dep_save)
    TextView tvSave;
    @Bind(R.id.et_edit_dep_name)
    EditText etName;
    @Bind(R.id.et_edit_dep_remark)
    EditText etRemark;
    private DepBean mDepBean;
    private SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dep);
        ButterKnife.bind(this);
        mDepBean = (DepBean) getIntent().getSerializableExtra("Dep");
        init(mDepBean);
        setListener();
    }

    private void init(DepBean bean) {
        if (bean != null) {
            etName.setText(bean.getDM_Name());
            etName.setSelection(etName.getText().toString().length());
            if (bean.getDM_Remark() != null) {
                etRemark.setText(bean.getDM_Remark());
            }
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
                }
            }
        });
    }

    private void edit() {
        RequestParams params = new RequestParams();
        params.put("DM_Name", etName.getText().toString());
        params.put("GID", mDepBean.getGID());
        params.put("DM_Remark", TextUtils.isEmpty(etRemark.getText()) ? "" : etRemark.getText().toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(EditDepActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("编辑成功！")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                                finish();
                            }
                        })
                        .setConfirmText("确认")
                        .show();

                getAllMessage();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditDepActivity.this, "编辑失败！" + msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, "Dept/EditDept", params, callBack);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
            mSweetAlertDialog = null;
        }
    }


    /***
     *
     * 更新部门员工缓存
     *
     * **/

    private void getAllMessage(){
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                if (reportbean!=null){

                    List<ReportMessageBean.DataBean.DepartmentListBean> deptbean = reportbean.getData().getDepartmentList();
                    CacheData.saveObject("dept", deptbean);//缓存部门列表到本地
                    Log.i("staff缓存成功", "onSuccess: staff缓存成功");
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditDepActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
