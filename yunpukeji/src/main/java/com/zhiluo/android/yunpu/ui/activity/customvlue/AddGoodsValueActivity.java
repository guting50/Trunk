package com.zhiluo.android.yunpu.ui.activity.customvlue;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.eventbus.MessageEvent;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 新增商品自定义属性
 */
public class AddGoodsValueActivity extends BaseActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_add_goods_value_save)
    TextView tvSave;
    @Bind(R.id.et_add_goods_value_name)
    EditText etName;
    @Bind(R.id.et_add_goods_value_sort)
    EditText etSort;
    @Bind(R.id.rb_add_goods_value_no_empty)
    RadioButton rbNoEmpty;
    @Bind(R.id.rb_add_goods_value_empty)
    RadioButton rbEmpty;
    @Bind(R.id.et_add_goods_data)
    EditText etData;
    @Bind(R.id.tv_choise_value)
    TextView tvChoiseType;

    private ArrayList<String> mVlueTypeList;//属性类型
    private ArrayAdapter<String> mArrayAdapter;
    private SweetAlertDialog mSweetAlertDialog;
    private Dialog chooseDialog;
    private int flags;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods_value);
        ButterKnife.bind(this);
        init();
        setListener();
    }

    private void init() {
        mVlueTypeList = new ArrayList<>();
        mVlueTypeList.add("文本");
        mVlueTypeList.add("日期");
        mVlueTypeList.add("选项");
        mVlueTypeList.add("数字");
        etData.setBackgroundColor(getResources().getColor(R.color.background));
        etData.setInputType(InputType.TYPE_NULL);


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
                    add();
                }
            }
        });
/*        spType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 3) {
                    etData.setBackgroundColor(getResources().getColor(R.color.white));
                    etData.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    etData.setBackgroundColor(getResources().getColor(R.color.background));
                    etData.setInputType(InputType.TYPE_NULL);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                etData.setBackgroundColor(getResources().getColor(R.color.background));
                etData.setInputType(InputType.TYPE_NULL);
            }
        });*/
        tvChoiseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(mVlueTypeList, tvChoiseType);
            }
        });
    }

    private void showChooseDialog(List<String> mlist, final TextView mTextView) {
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(1).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        flags = position;
                        if (position == 2) {
                            etData.setBackgroundColor(getResources().getColor(R.color.white));
                            etData.setInputType(InputType.TYPE_CLASS_TEXT);
                        } else {
                            etData.setBackgroundColor(getResources().getColor(R.color.background));
                            etData.setInputType(InputType.TYPE_NULL);
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    private boolean verify() {
        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(AddGoodsValueActivity.this, "属性名称不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(etSort.getText())) {
            CustomToast.makeText(AddGoodsValueActivity.this, "属性排序不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (flags == 2) {
            if (TextUtils.isEmpty(etData.getText())) {
                CustomToast.makeText(AddGoodsValueActivity.this, "数据集不能为空！", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    /**
     * 新增商品自定义属性
     */
    private void add() {
        RequestParams params = new RequestParams();
        params.put("CF_FieldName", etName.getText().toString());
        params.put("CF_FieldType", tvChoiseType.getText().toString());
        params.put("CF_ItemsValue", TextUtils.isEmpty(etData.getText()) ? "" : etData.getText().toString());
        params.put("CF_Required", rbEmpty.isChecked() ? "否" : "是");
        params.put("CF_Order", flags+1);
        params.put("CF_Value", "");
        params.put("CF_GID", "");
        params.put("PM_GID", 1);
        params.put("VIP_GID", "");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(AddGoodsValueActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("新增成功！")
                        .setConfirmText("确认")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                                finish();
                            }
                        })
                        .show();

                /**
                 * 添加成功evnetbus刷新列表 20191019 cy
                 */
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setType("goodes_value_fragment_refresh");
                EventBus.getDefault().post(messageEvent);
                /************************  20191019 cy */
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(AddGoodsValueActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, "CustomFields/AddFields", params, callBack);
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
