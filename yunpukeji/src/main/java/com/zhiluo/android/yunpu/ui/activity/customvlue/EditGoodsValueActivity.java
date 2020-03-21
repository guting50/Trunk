package com.zhiluo.android.yunpu.ui.activity.customvlue;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.CustomValueBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 编辑商品自定义属性
 */
public class EditGoodsValueActivity extends BaseActivity {
    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_edit_member_value_save)
    TextView tvSave;
    @Bind(R.id.tv_edit_goods_value_type)
    TextView tvType;
    @Bind(R.id.et_edit_goods_value_name)
    EditText etName;
    @Bind(R.id.et_edit_goods_value_sort)
    EditText etSort;
    @Bind(R.id.rb_edit_goods_value_no_empty)
    RadioButton rbNoEmpty;
    @Bind(R.id.rb_edit_goods_value_empty)
    RadioButton rbEmpty;
    @Bind(R.id.et_edit_goods_data)
    EditText etData;
    private CustomValueBean mValueBean;
    private SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_goods_value);
        ButterKnife.bind(this);
        mValueBean = (CustomValueBean) getIntent().getSerializableExtra("Value");
        updateView(mValueBean);
        setListener();
    }
    private void updateView(CustomValueBean bean) {
        if (bean != null) {
            tvType.setText(bean.getCF_FieldType());
            etName.setText(bean.getCF_FieldName());
            etSort.setText(bean.getCF_Order() + "");
            if (bean.getCF_Required().equals("是")) {
                rbNoEmpty.setChecked(true);
            } else {
                rbEmpty.setChecked(true);
            }
            etData.setText(bean.getCF_ItemsValue() == null ? "" : bean.getCF_ItemsValue());
            if (!bean.getCF_FieldType().equals("选项")) {
                etData.setInputType(InputType.TYPE_NULL);
                etData.setBackgroundColor(getResources().getColor(R.color.background));
            } else {
                etData.setInputType(InputType.TYPE_CLASS_TEXT);
                etData.setBackgroundColor(getResources().getColor(R.color.white));
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
                if (verify()) {
                    edit();
                }
            }
        });
    }

    private boolean verify() {
        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(EditGoodsValueActivity.this, "属性名称不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etSort.getText())) {
            CustomToast.makeText(EditGoodsValueActivity.this, "属性排序不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mValueBean.getCF_FieldType().equals("选项")) {
            if (TextUtils.isEmpty(etData.getText())) {
                CustomToast.makeText(EditGoodsValueActivity.this, "数据集不能为空！", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    /**
     * 编辑会员自定义属性
     */
    private void edit() {
        RequestParams params = new RequestParams();
        params.put("CF_FieldName", etName.getText().toString());
        params.put("CF_FieldType", mValueBean.getCF_FieldType());
        params.put("CF_ItemsValue", TextUtils.isEmpty(etData.getText()) ? "" : etData.getText().toString());
        params.put("CF_Required", rbEmpty.isChecked() ? "否" : "是");
        params.put("CF_Order", etSort.getText().toString());
        params.put("CF_Value", "");
        params.put("CF_GID", mValueBean.getCF_GID());
        params.put("PM_GID", 1);
        params.put("VIP_GID","");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(EditGoodsValueActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("编辑成功！")
                        .setConfirmText("确认")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                            }
                        })
                        .show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditGoodsValueActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, "CustomFields/EditFields", params, callBack);
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
