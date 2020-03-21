package com.zhiluo.android.yunpu.sms.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.SignBean;
import com.zhiluo.android.yunpu.entity.SuccessBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.activity.CheckMemberInfoActivity;
import com.zhiluo.android.yunpu.member.manager.activity.MemberListActivity;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.sms.jsonbean.SMSNumBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.HomeActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.uSharedPreferencesUtiles;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 发短信界面
 */
public class SendMsgActivity extends BaseActivity {
    @Bind(R.id.tv_title_notice)
    TextView tvTitleNotice;
    @Bind(R.id.tv_choose_sms_member)
    TextView tvChooseSmsMember;
    private TextView tvSmsNum, vipnum, tv_remaining_words_num;

    private int mSmsNum;
    private TextView tvSmsInputNum;//短信输入条数

    private Button btnSaveSign;//保存签名
    private EditText etMsgContent, etSignName, etMsgname;
    private TextView ivback;
    private Button btnSend;
    private String mSmName;
    private ArrayList<String> mPhoneList = new ArrayList<>();
    private List<AllMemberListBean.DataBean.DataListBean> namelist = new ArrayList<>();
    private StringBuilder sendName = new StringBuilder("");
    private TextView tvChooseT;
    SignBean mSignBean;
    private String mSignGid;
    private SweetAlertDialog mSweetAlertDialog;

    public static void fromSMSContentsAdapter(Context context, String contents, ArrayList<String> plist, List<AllMemberListBean.DataBean.DataListBean> namelist) {
        Intent intent = new Intent(context, SendMsgActivity.class);
        intent.putExtra("contents", contents);
        intent.putExtra("phonelist", plist);
        intent.putExtra("namelist", (Serializable) namelist);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate: ");
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_send_msg);
        ButterKnife.bind(this);
        initView();
        initdata();
        //获取库存短信条数
        getSmsNum();
        //获取签名列表
        getSign();
        setListener();
    }


    private void initView() {
        tvSmsInputNum = (TextView) findViewById(R.id.tv_sms_copies);
        etSignName = (EditText) findViewById(R.id.et_signature_member);
        btnSend = (Button) findViewById(R.id.btn_sendsms);
        ivback = (TextView) findViewById(R.id.tv_back_activity);
        etMsgContent = (EditText) findViewById(R.id.et_msg_content_member);
        etMsgname = findViewById(R.id.et_msg_content_name);
        vipnum = (TextView) findViewById(R.id.tv_vipselcnum);
        tv_remaining_words_num = (TextView) findViewById(R.id.tv_remaining_words_num);
        tvSmsNum = (TextView) findViewById(R.id.tv_surplus_sms_num_member);
        tvChooseT = (TextView) findViewById(R.id.tv_choose_sms_templates_member);
        btnSaveSign = (Button) findViewById(R.id.btn_save_sign_name);
        etSignName.setInputType(InputType.TYPE_CLASS_TEXT);
    }

    private void initdata() {
        if (getIntent().getStringArrayListExtra("phonelist") != null) {
            mPhoneList = getIntent().getStringArrayListExtra("phonelist");
            vipnum.setText(mPhoneList.size() + "");
        }
        if (getIntent() != null) {
            namelist = (List<AllMemberListBean.DataBean.DataListBean>) getIntent().getSerializableExtra("namelist");
            if (namelist != null && namelist.size() > 0) {
                for (int i = 0; i < namelist.size(); i++) {
                    String name = namelist.get(i).getVIP_Name() == null ? namelist.get(i).getVCH_Card() : namelist.get(i).getVIP_Name();
                    if (i == namelist.size() - 1) {
                        sendName.append(name + "<" + namelist.get(i).getVIP_CellPhone() + ">");
                    } else {
                        sendName.append(name + "<" + namelist.get(i).getVIP_CellPhone() + ">" + ",");
                    }

                }
                etMsgname.setText(sendName);
                sendName.delete(0, sendName.length());//清空数据
            }
        }
        if (getIntent().getStringExtra("contents") != null) {
            if (!getIntent().getStringExtra("contents").equals("")) {
                String smsContents = getIntent().getStringExtra("contents").toString();
                String shopName = (String) uSharedPreferencesUtiles.get(SendMsgActivity.this, "ShopName", "默认店铺");

//                if (smsContents.contains("#会员名称#")) {
//                    //获得第一个点的位置
//                    int index = smsContents.indexOf("#");
//                    System.out.println(index);
//                    //根据第一个点的位置 获得第二个点的位置
//                    index = smsContents.indexOf("#", index + 1);
//                    smsContents = smsContents.replace(smsContents.substring(smsContents.indexOf("#"), index + 1), "会员");
//                }
                if (smsContents.contains("#店铺名称#")) {
                    smsContents = smsContents.replace("#店铺名称#", shopName);
                    etMsgContent.setText(smsContents);
                    if (!smsContents.equals("") && smsContents.length() >= 1) {
                        isRemainingWrods(etMsgContent.getText().toString());
                    }
                } else {
                    etMsgContent.setText(smsContents);
                    isRemainingWrods(etMsgContent.getText().toString());
                }

            }
        }
    }

    /**
     * 设置监听
     */
    private void setListener() {

        etMsgContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().equals(" ")) {
                    etMsgContent.setText("");
                    editable.clear();
                }
                isRemainingWrods(editable.toString());
            }
        });
        //打开短信模板选择界面
        tvChooseT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendMsgActivity.this, ChooseSMSTemplatesActivity.class);
                intent.putStringArrayListExtra("plist", (ArrayList<String>) mPhoneList);
                intent.putExtra("namelist", (Serializable) namelist);
                startActivity(intent);
            }
        });
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitleNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendMsgActivity.this, MsgNotice.class);
                startActivity(intent);
            }
        });
        tvChooseSmsMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendMsgActivity.this, MemberListActivity.class);
                intent.putExtra("memberlisttype","msg");
                intent.putStringArrayListExtra("phonelist", (ArrayList<String>) mPhoneList);
                intent.putExtra("namelist", (Serializable) namelist);
                startActivityForResult(intent,222);
            }
        });
        //发送短信
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPhoneList ==null ||mPhoneList.size()<1){
                    mSweetAlertDialog = new SweetAlertDialog(SendMsgActivity.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("提示");
                    mSweetAlertDialog.setContentText("请先选择发送对象！");
                    mSweetAlertDialog.setConfirmText("确认");
                    mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mSweetAlertDialog.dismiss();
                        }
                    });
                    mSweetAlertDialog.show();
                }else
                if (TextUtils.isEmpty(etSignName.getText())) {
                    mSweetAlertDialog = new SweetAlertDialog(SendMsgActivity.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("提示");
                    mSweetAlertDialog.setContentText("请设置默认签名！");
                    mSweetAlertDialog.setConfirmText("确认");
                    mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            mSweetAlertDialog.dismiss();
                        }
                    });
                    mSweetAlertDialog.show();
                } else if (TextUtils.isEmpty(etMsgContent.getText())) {
                    mSweetAlertDialog = new SweetAlertDialog(SendMsgActivity.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("提示");
                    mSweetAlertDialog.setContentText("短信内容不能为空！");
                    mSweetAlertDialog.setConfirmText("了解");
                    mSweetAlertDialog.show();
                } else {
                    int spendNum = 0;
                    int ts = Integer.parseInt(tvSmsInputNum.getText().toString());
                    spendNum = mPhoneList.size() * ts;
                    if (mSmsNum >= spendNum) {
                        //发送短信
                        sendMsg();
                    } else {
                        CustomToast.makeText(SendMsgActivity.this, "短信库存不足！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //设置签名
        btnSaveSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etSignName.getText().toString().isEmpty()||etSignName.getText().toString().equals("")){
                    CustomToast.makeText(SendMsgActivity.this, "签名只能是由3-8个汉字、数字、字母构成", Toast.LENGTH_SHORT).show();
                }else {
                    saveSign();
                }
            }
        });

        etSignName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String editable = etSignName.getText().toString();
                String str = stringFilter(editable.toString());
                if (!editable.equals(str)) {
                    etSignName.setText(str);
                    //设置新的光标所在位置
                    etSignName.setSelection(str.length());
                }
                isRemainingWrods(etMsgContent.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.toString().length() < 3) {
                    CustomToast.makeText(SendMsgActivity.this, "签名只能是由3-8个汉字、数字、字母构成", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母、数字和汉字
        String regEx = "[^a-zA-Z0-9\u4E00-\u9FA5]";
        String reg = "[^a-zA-Z0-9\u4E00-\u9FA5_]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 判断短信内容框内的字数是否已达到上线
     *
     * @param editable
     */

    private void isRemainingWrods(final String editable) {
        tv_remaining_words_num.setText(editable.length()+etSignName.getText().toString().length()+7 + "");
        tvSmsInputNum.setText(1+"");
        if ((editable.length()+7+etSignName.getText().toString().length()) >= 70) {
            //记录条数
            if ((editable.length()+7+etSignName.getText().toString().length()) > 70) {
                tvSmsInputNum.setText(2 + "");
            }
            if ((editable.length()+7+etSignName.getText().toString().length()) > 134) {
                tvSmsInputNum.setText(3 + "");
            }
        }
    }


    /**
     * 获取签名
     */
    private void getSign() {
        HttpHelper.post(this, HttpAPI.API().SIGNLIST, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSignBean = CommonFun.JsonToObj(responseString, SignBean.class);
                mSmName = mSignBean.getData().getSM_Name();
                if(!TextUtils.isEmpty(mSmName)){
                    etSignName.setText(mSmName);
                }
                mSignGid = mSignBean.getData().getGID();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(SendMsgActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 保存签名
     */
    private void saveSign() {
        RequestParams params = new RequestParams();
        params.put("SM_Name", etSignName.getText().toString());
        params.put("GID", mSignGid);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSignBean = CommonFun.JsonToObj(responseString, SignBean.class);
                mSmName = mSignBean.getData().getSM_Name();
                etSignName.setText(mSmName);
                etSignName.setSelection(etSignName.getText().toString().length());
                mSignGid = mSignBean.getData().getGID();
                CustomToast.makeText(SendMsgActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(SendMsgActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        };
        callBack.setLoadingAnimation(this, "保存中...", false);
        HttpHelper.post(this, "SignatureManagement/EditSign", params, callBack);
    }

    /**
     * 获取短信条数
     */
    private void getSmsNum() {
        HttpHelper.post(this, HttpAPI.API().SMSNUM, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SuccessBean bean = CommonFun.JsonToObj(responseString, SuccessBean.class);
                if (bean.isSuccess()) {
                    if (bean.getMsg().contains("没有结果")) {
                        mSmsNum = 0;
                        tvSmsNum.setText("" + mSmsNum);
                    } else {
                        SMSNumBean numBean = CommonFun.JsonToObj(responseString, SMSNumBean.class);
                        mSmsNum = numBean.getData().getUStorage();
                        tvSmsNum.setText("" + mSmsNum);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(SendMsgActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 发送短信
     */
    private void sendMsg() {
        String content = etMsgContent.getText().toString();
        if (!content.substring(content.length()-1,content.length()).equals("。")){
            content = content+"。";
        }
        String msg = "【" + etSignName.getText().toString() + "】" + content + "退订回T";
        RequestParams params = new RequestParams();
        if (!etMsgContent.getText().toString().contains("#会员名称#")) {
            params.put("isWildcards", false);
            params.put("smsContent", msg);
            for (int i = 0; i < mPhoneList.size(); i++) {
                params.put("PhoneList[" + i + "]", mPhoneList.get(i));
            }
        } else {
            params.put("isWildcards", true);
            params.put("smsContent", "");
            for (int i = 0; i < mPhoneList.size(); i++) {
                params.put("ReplaceData[" + i + "][SmsContent]", replacName(msg, i));
                params.put("ReplaceData[" + i + "][Phone]", mPhoneList.get(i));
            }
        }

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(SendMsgActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                sweetAlertDialog.setTitleText("发送成功！");
                sweetAlertDialog.setConfirmText("确定");
                sweetAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        Intent intent = new Intent(SendMsgActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                sweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                if (msg.lastIndexOf("买") == msg.length() - 2) {
                    new SweetAlertDialog(SendMsgActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("短信库存不足，请前往PC端充值短信库存后方可继续下一步操作！")
                            .setConfirmText("了解")
                            .show();
                } else if (msg.contains("null")) {
                    new SweetAlertDialog(SendMsgActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText("手机号码不能为空!")
                            .setConfirmText("了解")
                            .show();
                } else {
                    new SweetAlertDialog(SendMsgActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提示")
                            .setContentText(msg + "!")
                            .setConfirmText("了解")
                            .show();
                }
            }
        };
        callBack.setLoadingAnimation(this, "正在发送...", false);
        HttpHelper.post(this, HttpAPI.API().SENDSMS, params, callBack);
    }

    private String replacName(String str, int i) {
        String str1 = str;
        if (str1.contains("#会员名称#")) {
            str1 = str1.replace("#会员名称#", namelist.get(i).getVIP_Name());
        }
        return str1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode ==222 &&requestCode ==222){
            if (data!=null){
                if (data.getStringArrayListExtra("phonelist") != null) {
                    mPhoneList = data.getStringArrayListExtra("phonelist");
                }
                if (data.getSerializableExtra("namelist")!=null){
                    namelist = (List<AllMemberListBean.DataBean.DataListBean>) data.getSerializableExtra("namelist");
                    if (namelist != null && namelist.size() > 0) {
                        for (int i = 0; i < namelist.size(); i++) {
                            String name = namelist.get(i).getVIP_Name() == null ? namelist.get(i).getVCH_Card() : namelist.get(i).getVIP_Name();
                            if (i == namelist.size() - 1) {
                                sendName.append(name + "<" + namelist.get(i).getVIP_CellPhone() + ">");
                            } else {
                                sendName.append(name + "<" + namelist.get(i).getVIP_CellPhone() + ">" + ",");
                            }
                        }
                        etMsgname.setText(sendName);
                        sendName.delete(0, sendName.length());//清空数据
                    }
                }



            }
        }
    }
}
