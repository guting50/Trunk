package com.zhiluo.android.yunpu.home.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.mvp.model.AdviceTypeEntity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * 反馈建议界面
 */
public class FeedbackActivity extends BaseActivity {
    private EditText mEtContactType, mEtSubject, mEtDetail;
    private Spinner mSpinnerAdviceType, mSpinnerEmergency;
    private ArrayList<String> mAdviceTypeList, mEmergencyList;
    private int mEmergencyGrade = 0;
    private String mAdviceTypeGid = "";
    private AdviceTypeEntity mAdviceTypeEntity;
    private SweetAlertDialog mDialog;
    private Dialog chooseDialog;
    private TextView teType, teChengDu;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
        loadData();
        setListener();
    }

    private void initView() {
        mEtContactType = (EditText) findViewById(R.id.et_feedback_contact_type);
        mEtSubject = (EditText) findViewById(R.id.et_feedback_subject);
        mEtDetail = (EditText) findViewById(R.id.et_feedback_detail);
        mSpinnerAdviceType = (Spinner) findViewById(R.id.sp_feedback_type);
        mSpinnerEmergency = (Spinner) findViewById(R.id.sp_feedback_emergency);
        teChengDu = findViewById(R.id.te_chengdu);
        teType = findViewById(R.id.te_type);
    }

    private void loadData() {
        mAdviceTypeList = new ArrayList<>();
        mList=new ArrayList<>();
        getAdviceType();
        mEmergencyList = new ArrayList<>();
        mEmergencyList.add("非常紧急");
        mEmergencyList.add("紧急");
        mEmergencyList.add("一般");
        mEmergencyList.add("不急");
        mList.add("想有这个功能");
        mList.add("功能不会用");
        mList.add("页面打不开");
        mList.add("系统错误");
        mList.add("其它问题");

      /*  mSpinnerEmergency.setAdapter(new ArrayAdapter<>(FeedbackActivity.this,
                R.layout.item_spinner, R.id.tv_item_spinner, mEmergencyList));*/
    }

    private void showChengDuDialog(List<String> mlist, final TextView mTextView) {
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(1).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        switch (position) {
                            case 0:
                                mEmergencyGrade = 1;
                                break;
                            case 1:
                                mEmergencyGrade = 2;
                                break;
                            case 2:
                                mEmergencyGrade = 3;
                                break;
                            case 3:
                                mEmergencyGrade = 4;
                                break;
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }
    private void showTypeDialog(List<String> mlist, final TextView mTextView) {
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(1).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        if (mAdviceTypeEntity !=null){
                            mAdviceTypeGid = mAdviceTypeEntity.getData().get(position).getQT_GID();
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    private void setListener() {
        teType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTypeDialog(mList,teType);
            }
        });
        teChengDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChengDuDialog(mEmergencyList,teChengDu);
            }
        });
        findViewById(R.id.tv_back_activity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mSpinnerAdviceType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mAdviceTypeGid = mAdviceTypeEntity.getData().get(position).getQT_GID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerEmergency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mEmergencyGrade = 1;
                        break;
                    case 1:
                        mEmergencyGrade = 2;
                        break;
                    case 2:
                        mEmergencyGrade = 3;
                        break;
                    case 3:
                        mEmergencyGrade = 4;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        findViewById(R.id.iv_feedback_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAdvice();
            }
        });
    }



    /**
     * 提交建议
     */
    private void submitAdvice() {
        if (TextUtils.isEmpty(mEtContactType.getText().toString())) {
            CustomToast.makeText(this, "请输入联系方式！", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(mAdviceTypeGid) && mAdviceTypeGid.equals("")) {
            CustomToast.makeText(this, "请选择问题类型！", Toast.LENGTH_SHORT).show();
            return;
        } else if (mEmergencyGrade ==0) {
            CustomToast.makeText(this, "请选择紧急程度！", Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(mEtSubject.getText().toString()) && mEtSubject.getText().toString().equals("")) {
            CustomToast.makeText(this, "请输入主题！", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestParams params = new RequestParams();
        params.put("QuestionTypeGID", mAdviceTypeGid);
        params.put("QuestionPhone", mEtContactType.getText().toString());
        params.put("Emergency", mEmergencyGrade);
        params.put("Subject", mEtSubject.getText().toString());
        params.put("Body", mEtDetail.getText().toString());
        HttpHelper.post(FeedbackActivity.this, "WorkOrder/AddWorkOrder", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mDialog = new SweetAlertDialog(FeedbackActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mDialog.setTitleText("意见反馈");
                mDialog.setContentText("反馈意见成功！");
                mDialog.setConfirmText("继续反馈");
                mDialog.setCancelText("反馈完成");
                mDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mEtContactType.setText("");
                        mEtSubject.setText("");
                        mEtDetail.setText("");
                        mAdviceTypeGid = "";
                        mEmergencyGrade =0;
                        teType.setText("");
                        teChengDu.setText("");
                        mSpinnerAdviceType.setSelection(0);
                        mSpinnerEmergency.setSelection(0);
                        mDialog.dismiss();
                    }
                });
                mDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mDialog.dismiss();
                        FeedbackActivity.this.finish();
                    }
                });
                mDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(FeedbackActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 获取意见类型
     */
    private void getAdviceType() {
        HttpHelper.post(FeedbackActivity.this, "WorkOrder/GetWorkOrderTypeList", new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mAdviceTypeEntity = CommonFun.JsonToObj(responseString, AdviceTypeEntity.class);
                for (int i = 0; i < mAdviceTypeEntity.getData().size(); i++) {
                    mAdviceTypeList.add(mAdviceTypeEntity.getData().get(i).getQT_Name());
                }
                mSpinnerAdviceType.setAdapter(new ArrayAdapter<>(FeedbackActivity.this,
                        R.layout.item_spinner, R.id.tv_item_spinner, mAdviceTypeList));
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(FeedbackActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
