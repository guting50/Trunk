package com.zhiluo.android.yunpu.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.suke.widget.SwitchButton;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.MoneyInputFilter;
import com.zhiluo.android.yunpu.yslutils.DataUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by ZPH on 2019-03-28.
 */

public class VipLevelRiseOrFallActivity extends Activity {


    @Bind(R.id.tv_back)
    TextView tvBack;
    @Bind(R.id.tv_titel)
    TextView tvTitel;
    @Bind(R.id.img_ok)
    ImageView imgOk;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.tv_rise_set)
    TextView tvRiseSet;
    @Bind(R.id.switch_button_rise_set)
    SwitchButton switchButtonRiseSet;
    @Bind(R.id.tv_rise_set_name)
    TextView tvRiseSetName;
    @Bind(R.id.tv_rise_set_content)
    TextView tvRiseSetContent;
    @Bind(R.id.rl_rise_set)
    RelativeLayout rlRiseSet;
    @Bind(R.id.ll_rise_set)
    LinearLayout llRiseSet;
    @Bind(R.id.tv_fall_set)
    TextView tvFallSet;
    @Bind(R.id.switch_button_fall_set)
    SwitchButton switchButtonFallSet;
    @Bind(R.id.tv_fall_set_name)
    TextView tvFallSetName;
    @Bind(R.id.tv_fall_set_content)
    TextView tvFallSetContent;
    @Bind(R.id.rl_fall_set)
    RelativeLayout rlFallSet;
    @Bind(R.id.ll_fall_set)
    LinearLayout llFallSet;
    @Bind(R.id.tv_case_name)
    TextView tvCaseName;
    @Bind(R.id.tv_case)
    TextView tvCase;
    @Bind(R.id.tv_min)
    EditText tvMin;
    @Bind(R.id.tv_max)
    EditText tvMax;
    @Bind(R.id.cb_content)
    CheckBox cbContent;
    @Bind(R.id.ll_statistics_time_other)
    LinearLayout llStatisticsTimeOther;
    @Bind(R.id.tv_notice_rise)
    TextView tvNoticeRise;
    @Bind(R.id.tv_notice_fall)
    TextView tvNoticeFall;

    private Dialog chooseDialog;
    private List<ReportMessageBean.DataBean.VIPGradeListBean> mMemberGrade;//会员等级实体
    private String mGradeRiseGid, mGradeFallGid;//等级GID
    private List<String> mGradeNameList = new ArrayList<>();//会员等级名称集合

    private List<String> caseNameList = new ArrayList<>();
    private DataBean mGrade;
    private int caseType = 0;
    private SweetAlertDialog mSweetAlertDialog;//提示框


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rise_fall_grade_set);
        ButterKnife.bind(this);
        initData();
        initLisenter();

    }

    private void initData() {
        mMemberGrade = (List<ReportMessageBean.DataBean.VIPGradeListBean>) CacheData.restoreObject("grade");
        Intent intent = getIntent();
        if (intent != null) {
            mGrade = (DataBean) intent.getSerializableExtra("Grade");
            if (mGrade != null) {
                setDate();
            }
        }
        if (mMemberGrade == null) {
            getMemberGrade(1);//获取会员等级
        } else {
            initGrade();
        }

        caseNameList.add("按账户积分");
        caseNameList.add("按账户储值");
        caseNameList.add("按累计积分");
        caseNameList.add("按累计储值");
        caseNameList.add("按累计消费");

        //金额过滤器
        MoneyInputFilter filter = new MoneyInputFilter(20, true, "最多只能输入20位金额");
        filter.setMessage("最多只能输入20位金额");
        tvMin.setFilters(new InputFilter[]{filter});
        tvMax.setFilters(new InputFilter[]{filter});

    }

    private void setDate() {
        if (mGrade.getVG_IsLock() == 1) {
            switchButtonRiseSet.setChecked(true);
            tvRiseSetContent.setEnabled(true);
            tvMin.setEnabled(true);
            tvMax.setEnabled(true);
            cbContent.setEnabled(true);
        } else {
            switchButtonRiseSet.setChecked(false);
            tvRiseSetContent.setText("");
            tvRiseSetContent.setEnabled(false);
            if (!switchButtonFallSet.isChecked()) {
                tvMin.setEnabled(false);
                tvMax.setEnabled(false);
                cbContent.setEnabled(false);
            }

        }
        if (mGrade.getVG_IsDownLock() == 1) {
            switchButtonFallSet.setChecked(true);
            tvFallSetContent.setEnabled(true);
            tvMin.setEnabled(true);
            tvMax.setEnabled(true);
            cbContent.setEnabled(true);
        } else {
            switchButtonFallSet.setChecked(false);
            tvFallSetContent.setText("");
            tvFallSetContent.setEnabled(false);
            if (!switchButtonRiseSet.isChecked()) {
                tvMin.setEnabled(false);
                tvMax.setEnabled(false);
                cbContent.setEnabled(false);
            }
        }
        tvRiseSetContent.setText(DataUtils.checkNull(mGrade.getVG_NextGradeName()));
        tvFallSetContent.setText(DataUtils.checkNull(mGrade.getVG_LastGradeName()));
        mGradeRiseGid = DataUtils.checkNull(mGrade.getVG_NextGradeGID());
        mGradeFallGid = DataUtils.checkNull(mGrade.getVG_LastGradeGID());

        if (mGrade.getVG_UpDownType() != null) {
            if (mGrade.getVG_UpDownType() == 0) {
                tvCase.setText("按账户积分");
                caseType = 0;
            } else if (mGrade.getVG_UpDownType() == 1) {
                tvCase.setText("按账户储值");
                caseType = 1;
            } else if (mGrade.getVG_UpDownType() == 2) {
                tvCase.setText("按累计积分");
                caseType = 2;
            } else if (mGrade.getVG_UpDownType() == 3) {
                tvCase.setText("按累计储值");
                caseType = 3;
            } else if (mGrade.getVG_UpDownType() == 4) {
                tvCase.setText("按累计消费");
                caseType = 4;
            }
        }

        tvMin.setText(mGrade.getUS_Value() + "");
        tvMin.setSelection(tvMin.getText().toString().length());
        tvMax.setText(DataUtils.checkNull(mGrade.getUS_ValueMax()));
        tvMax.setSelection(tvMax.getText().toString().length());
        if (mGrade.getVG_UpDownType() != null && mGrade.getUS_ValueMax() == null) {
            cbContent.setChecked(true);
            tvMax.setText("无穷大");
            tvMax.setEnabled(false);
        }
        if (cbContent.isChecked()){
            tvNoticeRise.setText("1.该等级条件上限为无穷大，将不会升级。");
        }else {
            if (switchButtonRiseSet.isChecked()){
                tvNoticeRise.setText("1.当前等级的会员账户积分大于等于【"+tvMax.getText().toString()+"】时将自动升级到【"+tvRiseSetContent.getText().toString()+"】");
            }else {
                tvNoticeRise.setText("1.自动升级关闭");
            }
        }

        if (switchButtonFallSet.isChecked()){
            tvNoticeFall.setText("2.当前等级的会员账户积分小于【"+tvMin.getText().toString()+"】时将自动降级到【"+tvFallSetContent.getText().toString()+"】");
        }else {
            tvNoticeFall.setText("2.自动降级关闭");
        }

    }


    private void initGrade() {
        for (int i = 0; i < mMemberGrade.size(); i++) {
            if (tvRiseSetContent.getText().toString().equals(mMemberGrade.get(i).getVG_Name())) {
                mGradeRiseGid = mMemberGrade.get(i).getGID();
            }
        }
        if (mGrade != null) {
            mGradeNameList = groupGradeName(mGrade.getVG_Name()+"");//组装会员等级名称集合
        }
    }

    private void initLisenter() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (judge()) {
                    saveCase();
                }

            }
        });

        //升级开关
        switchButtonRiseSet.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    tvRiseSetContent.setEnabled(true);
                    tvMin.setEnabled(true);
                    tvMax.setEnabled(true);
                    cbContent.setEnabled(true);
                    tvNoticeRise.setText("1.当前等级的会员账户积分大于等于【"+tvMax.getText().toString()+"】时将自动升级到【"+tvRiseSetContent.getText().toString()+"】");
                    if (cbContent.isChecked()){
                        tvMax.setEnabled(false);
                    }else {
                        tvMax.setEnabled(true);
                    }
                } else {
                    tvRiseSetContent.setText("");
                    tvRiseSetContent.setEnabled(false);
                    tvNoticeRise.setText("1.自动升级关闭");
                    if (!switchButtonFallSet.isChecked()) {
                        tvMin.setEnabled(false);
                        tvMax.setEnabled(false);
                        cbContent.setEnabled(false);
                        cbContent.setChecked(false);
                        tvMin.setText("");
                        tvMax.setText("");
                    }
                }
            }
        });

        //降级开关
        switchButtonFallSet.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                    tvNoticeFall.setText("2.当前等级的会员账户积分小于【"+tvMin.getText().toString()+"】时将自动降级到【"+tvFallSetContent.getText().toString()+"】");
                    tvFallSetContent.setEnabled(true);
                    tvMin.setEnabled(true);
                    cbContent.setEnabled(true);
                    if (cbContent.isChecked()){
                        tvMax.setEnabled(false);
                    }else {
                        tvMax.setEnabled(true);
                    }
                } else {
                    tvFallSetContent.setText("");
                    tvFallSetContent.setEnabled(false);
                    tvNoticeFall.setText("2.自动降级关闭");
                    if (!switchButtonRiseSet.isChecked()) {
                        tvMin.setEnabled(false);
                        tvMax.setEnabled(false);
                        cbContent.setEnabled(false);
                        cbContent.setChecked(false);
                        tvMin.setText("");
                        tvMax.setText("");

                    }
                }
            }
        });

        cbContent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tvMax.setText("");
                    tvMax.setEnabled(false);
                    tvNoticeRise.setText("1.该等级条件上限为无穷大，将不会升级。");
                } else {
                    tvMax.setEnabled(true);
                    if (switchButtonRiseSet.isChecked()){
                        tvNoticeRise.setText("1.当前等级的会员账户积分大于等于【"+tvMax.getText().toString()+"】时将自动升级到【"+tvRiseSetContent.getText().toString()+"】");
                    }else {
                        tvNoticeRise.setText("1.自动升级关闭");
                    }
                }
            }
        });

        tvRiseSetContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVipLevelDialog(mGradeNameList, tvRiseSetContent);
            }
        });
        tvFallSetContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVipLevelfallDialog(mGradeNameList, tvFallSetContent);
            }
        });

        tvCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showVipCaseDialog(caseNameList, tvCase);
            }
        });

        tvMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (switchButtonFallSet.isChecked()){
                    tvNoticeFall.setText("2.当前等级的会员账户积分小于【"+tvMin.getText().toString()+"】时将自动降级到【"+tvFallSetContent.getText().toString()+"】");
                }
            }
        });
        tvMax.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (switchButtonRiseSet.isChecked()) {
                    tvNoticeRise.setText("1.当前等级的会员账户积分大于等于【" + tvMax.getText().toString() + "】时将自动升级到【" + tvRiseSetContent.getText().toString() + "】");
                }
            }
        });

    }


    private boolean judge() {
        if (switchButtonFallSet.isChecked()) {
            if (tvFallSetContent.getText().toString().isEmpty() || tvFallSetContent.getText().toString().equals("")) {
                CustomToast.makeText(VipLevelRiseOrFallActivity.this, "请选择要自动降级的会员等级", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (tvMin.getText().toString().isEmpty() || tvMin.getText().toString().equals("") || (
                    (tvMax.getText().toString().isEmpty() || tvMax.getText().toString().equals("")) && !cbContent.isChecked())) {
                CustomToast.makeText(VipLevelRiseOrFallActivity.this, "请输入条件范围值", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                if (!tvMax.getText().toString().equals("无穷大")) {
                    if (!tvMax.getText().toString().isEmpty() && !tvMax.getText().toString().equals("") &&
                            Double.parseDouble(tvMax.getText().toString()) <= Double.parseDouble(tvMin.getText().toString())) {
                        CustomToast.makeText(VipLevelRiseOrFallActivity.this, "条件范围的下限不可大于等于上限值", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            }
        }
        if (switchButtonRiseSet.isChecked()) {
            if (tvRiseSetContent.getText().toString().isEmpty() || tvRiseSetContent.getText().toString().equals("")) {
                CustomToast.makeText(VipLevelRiseOrFallActivity.this, "请选择要自动升级的会员等级", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (tvMin.getText().toString().isEmpty() || tvMin.getText().toString().equals("") || (
                    (tvMax.getText().toString().isEmpty() || tvMax.getText().toString().equals("")) && !cbContent.isChecked())) {
                CustomToast.makeText(VipLevelRiseOrFallActivity.this, "请输入条件范围值", Toast.LENGTH_SHORT).show();
                return false;
            } else {
                if (!tvMax.getText().toString().equals("无穷大")){
                    if (!tvMax.getText().toString().isEmpty() && !tvMax.getText().toString().equals("") &&
                            Double.parseDouble(tvMax.getText().toString()) <= Double.parseDouble(tvMin.getText().toString())) {
                        CustomToast.makeText(VipLevelRiseOrFallActivity.this, "条件范围的下限不可大于等于上限值", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }

            }
        }
        return true;
    }

    /**
     * 组装会员等级名称数据集合
     */
    private List<String> groupGradeName(String str) {
        List<String> list = new ArrayList<>();
        if (mMemberGrade != null) {
            for (int i = 0; i < mMemberGrade.size(); i++) {
                if (!str.equals(mMemberGrade.get(i).getVG_Name())) {
                    list.add(mMemberGrade.get(i).getVG_Name());
                }
            }
        }
        return list;
    }

    /**
     * @param mlist
     * @param mTextView 选择会员等级
     */
    private void showVipLevelDialog(final List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        tvNoticeRise.setText("1.当前等级的会员账户积分大于等于【"+tvMax.getText().toString()+"】时将自动升级到【"+tvRiseSetContent.getText().toString()+"】");
                        for (int i = 0; i < mMemberGrade.size(); i++) {
                            if (itemValue.equals(mMemberGrade.get(i).getVG_Name())) {
                                mGradeRiseGid = mMemberGrade.get(i).getGID();
                            }
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * @param mlist
     * @param mTextView 降级选择会员等级
     */
    private void showVipLevelfallDialog(final List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        tvNoticeFall.setText("2.当前等级的会员账户积分小于【"+tvMin.getText().toString()+"】时将自动降级到【"+tvFallSetContent.getText().toString()+"】");
                        for (int i = 0; i < mMemberGrade.size(); i++) {
                            if (itemValue.equals(mMemberGrade.get(i).getVG_Name())) {
                                mGradeFallGid = mMemberGrade.get(i).getGID();
                            }
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * @param mlist
     * @param mTextView 选择升降级方案
     */
    private void showVipCaseDialog(final List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        caseType = position;

                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * 查询会员等级
     */

    private void getMemberGrade(final int num) {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        client.setCookieStore(cookieStore);
        RequestParams params = new RequestParams();

        client.post(HttpAPI.API().PRE_LOAD, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                mMemberGrade = reportbean.getData().getVIPGradeList();
                CacheData.saveObject("grade", mMemberGrade);//缓存等级列表到本地

                initGrade();

            }

            @Override
            public void onFailure(String msg) {
            }
        });

    }

    /**
     * 保存升降级设置
     **/

    private void saveCase() {
        RequestParams params = new RequestParams();
        params.put("GID", mGrade.getGID());
        params.put("US_ValueMin", tvMin.getText().toString());
        if (!cbContent.isChecked()) {
            params.put("US_ValueMax", tvMax.getText().toString());
        }
        params.put("VG_IsLock", switchButtonRiseSet.isChecked() ? 1 : 0);
        params.put("VG_IsDownLock", switchButtonFallSet.isChecked() ? 1 : 0);
        params.put("VG_UpDownType", caseType);
        params.put("VG_NextGradeName", tvRiseSetContent.getText().toString());
        params.put("VG_NextGradeGID", mGradeRiseGid);
        params.put("VG_LastGradeName", tvFallSetContent.getText().toString());
        params.put("VG_LastGradeGID", mGradeFallGid);

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {


                mSweetAlertDialog = new SweetAlertDialog(VipLevelRiseOrFallActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("提示");
                mSweetAlertDialog.setContentText("设置成功");
                mSweetAlertDialog.setCancelable(false);
                mSweetAlertDialog.setConfirmText("确定");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        Intent intent = new Intent();
                        intent.putExtra("GD", mGrade.getGID());
                        setResult(600, intent);
                        finish();
                    }
                });
                mSweetAlertDialog.show();

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(VipLevelRiseOrFallActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(this, HttpAPI.API().RISEANDFALL, params, callBack);

    }

}
