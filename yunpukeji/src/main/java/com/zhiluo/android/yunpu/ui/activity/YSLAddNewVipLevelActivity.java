package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.suke.widget.SwitchButton;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.entity.EditGradeResultBean;
import com.zhiluo.android.yunpu.entity.GradeSetBean;
import com.zhiluo.android.yunpu.entity.NewGradeBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Le;

/**
 * Created by ${YSL} on 2018-04-10.
 */

public class YSLAddNewVipLevelActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.tv_rise_fall_grade)
    TextView tvRiseFallGrade;
    @Bind(R.id.r_rise_fall_grade)
    RelativeLayout rRiseFallGrade;
    @Bind(R.id.title_rise_fall_grade)
    TextView titleRiseFallGrade;
    private EditText mLevelName, mLevelCode, mSaleCardPrice;
    private SwitchButton sbZheKou, sbChuzhi, sbJiFen, sbXianShi, sbJiCi;
    private TextView tvTitle;
    private Intent mIntent;
    private String stringchuzhi, stringoriginjifen, stringOneKeyOil, stringfastshop, stringviprechange, stringunitfied;
    private String stringkuaixiaobili = "100", stringtongyizhekou = "100";
    private String mintergalType = "";//积分类型，在设置详情界面传过来，分为统一设置和详情设置
    private RequestParams params;
    private GradeSetBean mGradeSetBean = new GradeSetBean();
    private String TypeJiFen = "";
    private String TypeZheKou = "tongyi";
    private boolean typeCiShu;
    private String timeType, stringcishu, stringshijian;
    private List<DataBean.InitServiceListBean> mListEditedMsg = new ArrayList<>();
    private SweetAlertDialog mSweetAlertDialog;
    private List<DataBean.SettingsBean> mSetList = new ArrayList<>();
    private boolean mHasSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_viplevel_edit);
        ButterKnife.bind(this);
        params = new RequestParams();
        initViews();

        action();

    }

    private void dataAttachViews() {
    }


    private boolean modify() {
        if (TextUtils.isEmpty(mLevelName.getText().toString())) {
            CustomToast.makeText(this, "请输入等级名称", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
                YSLAddNewVipLevelActivity.this.finish();
                break;
            case R.id.img_ok:
                if (modify()) {
                    save();
                }
                break;
            case R.id.r_chunzhi_set://储值
                if (!sbChuzhi.isChecked()) {
                    CustomToast.makeText(this, "请打开储值开关后再设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                mIntent = null;
                mIntent = new Intent(YSLAddNewVipLevelActivity.this, VipLevelChuZhi.class);
                startActivityForResult(mIntent, 10);
                break;
            case R.id.r_jifen_set://积分
                if (!sbJiFen.isChecked()) {
                    CustomToast.makeText(this, "请打开积分开关后再设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                mIntent = null;
                mIntent = new Intent(YSLAddNewVipLevelActivity.this, NewVipIntergalsetActivity.class);
                startActivityForResult(mIntent, 20);
                break;
            case R.id.r_zhekou_set://折扣
                if (!sbZheKou.isChecked()) {
                    CustomToast.makeText(this, "请打开折扣开关后再设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                mIntent = null;
                mIntent = new Intent(YSLAddNewVipLevelActivity.this, NewYSLDiscountActivity.class);
                mIntent.putExtra("kxzk", stringkuaixiaobili);
                mIntent.putExtra("tyzk", stringtongyizhekou);
                startActivityForResult(mIntent, 30);
                break;
            case R.id.r_jici_set://计次
                if (!sbJiCi.isChecked()) {
                    CustomToast.makeText(YSLAddNewVipLevelActivity.this, "请先打开开关后再设置", Toast.LENGTH_SHORT);
                    return;
                }
                mIntent = null;
                mIntent = new Intent(YSLAddNewVipLevelActivity.this, NewDisplayJiCiServiceACtivity.class);
                mIntent.putExtra("Service", (Serializable) mListEditedMsg);
                startActivityForResult(mIntent, 40);
                break;
            case R.id.r_xianshi_set://限时
                if (!sbXianShi.isChecked()) {
                    CustomToast.makeText(this, "请打开限时开关后再设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                mIntent = null;
                mIntent = new Intent(YSLAddNewVipLevelActivity.this, NewYSLXianShiSetActivity.class);
                mIntent.putExtra("cishu", typeCiShu);
                mIntent.putExtra("tShiJianDanWei", timeType);
                mIntent.putExtra("edcishu", stringcishu);
                mIntent.putExtra("edshijian", stringshijian);
                startActivityForResult(mIntent, 50);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10://储值
                try {
                    stringchuzhi = data.getStringExtra("chuzhi");
                } catch (Exception e) {
                }
                break;
            case 20://积分
                try {
                    GradeSetBean bean = (GradeSetBean) data.getSerializableExtra("integral_set");
                    if (bean != null) {
                        if (mHasSet) {
                            for (int i = 0; i < bean.getData().size(); i++) {
                                mSetList.get(i).setVS_CMoney(bean.getData().get(i).getVS_CMoney());
                                mSetList.get(i).setVS_Number(bean.getData().get(i).getVS_Number());
                            }
                        } else {
                            for (int i = 0; i < bean.getData().size(); i++) {
                                DataBean.SettingsBean sBean = new DataBean.SettingsBean();
                                sBean.setVS_CMoney(bean.getData().get(i).getVS_CMoney());
                                sBean.setVS_Number(bean.getData().get(i).getVS_Number());
                                sBean.setPT_GID(bean.getData().get(i).getGID());
                                mSetList.add(sBean);
                            }
                            mHasSet = true;
                        }
                    }
                    mintergalType = data.getStringExtra("intergalsettype");//返回的积分类型
                    Le("---------2----" + mintergalType);
                    stringoriginjifen = data.getStringExtra("edOriginIntergal");//初始积分
                    stringOneKeyOil = data.getStringExtra("edOneKeyOil");//一键加油比列
                    stringfastshop = data.getStringExtra("edFastShop");//快速消费比例
                    stringviprechange = data.getStringExtra("edVipRechange");//会员充值比例
                    stringunitfied = data.getStringExtra("unitfiedSets");//统一设置
                } catch (Exception e) {

                }

                break;
            case 30://折扣
                try {
                    TypeZheKou = data.getStringExtra("tongyi");
                    stringkuaixiaobili = data.getStringExtra("kuaixiaobili");
                    stringtongyizhekou = data.getStringExtra("tongyizhekou");
                    NewGradeBean bean = (NewGradeBean) data.getSerializableExtra("discount_set");
                    if (bean != null) {
                        if (mHasSet) {
                            for (int i = 0; i < bean.getData().size(); i++) {
                                mSetList.get(i).setPD_Discount(bean.getData().get(i).getPD_Discount());
                            }
                        } else {
                            for (int i = 0; i < bean.getData().size(); i++) {
                                DataBean.SettingsBean sBean = new DataBean.SettingsBean();
                                sBean.setPD_Discount(bean.getData().get(i).getPD_Discount());
                                sBean.setPT_GID(bean.getData().get(i).getGID());
                                mSetList.add(sBean);
                            }
                            mHasSet = true;
                        }
                    }

                } catch (Exception e) {
                }
                break;
            case 40://计次
                mListEditedMsg = (List<DataBean.InitServiceListBean>) data.getSerializableExtra("Service");
                break;
            case 50://限时
                try {
                    typeCiShu = data.getBooleanExtra("cishu", false);
                    timeType = data.getStringExtra("tShiJianDanWei");
                    stringcishu = data.getStringExtra("edcishu");
                    stringshijian = data.getStringExtra("edshijian");
                } catch (Exception e) {
                }
                break;
        }
    }

    private void save() {
        judgeParams();
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
//                EditGradeResultBean bean = CommonFun.JsonToObj(responseString, EditGradeResultBean.class);
//                final DataBean dataBean = bean.getData();
                mSweetAlertDialog = new SweetAlertDialog(YSLAddNewVipLevelActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("编辑成功");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
//                        Intent intent = new Intent();
//                        intent.putExtra("GD", dataBean);
//                        setResult(500, intent);
                        YSLAddNewVipLevelActivity.this.finish();
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(YSLAddNewVipLevelActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        HttpHelper.post(YSLAddNewVipLevelActivity.this, HttpAPI.API().ADDGRADE, params, callBack);
    }

    /**
     * 对一些参数做判断，并添加到表单
     */
    private void judgeParams() {
        if (TextUtils.isEmpty(mLevelName.getText().toString()) || mLevelName.getText().toString().equals("")) {
            CustomToast.makeText(this, "请填写", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(mLevelName.getText().toString()) || mLevelName.getText().toString().equals("")) {
            CustomToast.makeText(this, "请填写等级名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sbJiFen.isChecked() && TypeJiFen.equals("nuitfied")) {
            if (TextUtils.isEmpty(stringunitfied) || stringunitfied.equals("") || stringunitfied == null) {
                CustomToast.makeText(this, "积分设置中，请设置保存后再提交", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (sbZheKou.isChecked() && TypeZheKou.equals("tongyi")) {
            if (TextUtils.isEmpty(stringtongyizhekou) || stringtongyizhekou.equals("") || stringtongyizhekou == null) {
                CustomToast.makeText(this, "折扣设置中，请设置保存后再提交", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (sbZheKou.isChecked()) {
            if (TextUtils.isEmpty(stringkuaixiaobili) || stringkuaixiaobili.equals("") || stringkuaixiaobili == null) {
                CustomToast.makeText(this, "折扣设置中，请设置保存后再提交", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        params.put("US_Code", "IntegerFulfil");
        params.put("VG_CardAmount", mSaleCardPrice.getText() == null ? 0 : mSaleCardPrice.getText().toString());//售卡金额
//        params.put("US_Value", mUpDateCondition.getText().toString());//升级条件
        params.put("VG_Code", mLevelCode.getText() == null ? "" : mLevelCode.getText().toString());//登记编码
        params.put("VG_Name", mLevelName.getText().toString());//等级名称
        params.put("VG_Remark", "");
        //积分统一设置
        if (sbJiFen.isChecked()) {
            if (mintergalType.equals("nuitfied")) {
                params.put("VG_IntegralRuleType", 0);
                params.put("VG_IntegralUniformRuleValue", Decima2KeeplUtil.stringToDecimal(stringunitfied));//stringunitfied 统一设置积分比例
                params.put("Settings[]", "");
            } else {//详情设置
                params.put("VG_IntegralRuleType", 1);
                for (int i = 0; i < mSetList.size(); i++) {
                    params.put("Settings[" + i + "][PT_ID]", mSetList.get(i).getPT_GID());
                    params.put("Settings[" + i + "][VS_CMoney]", mSetList.get(i).getVS_CMoney());
                    params.put("Settings[" + i + "][VS_Number]", mSetList.get(i).getVS_Number());
                    params.put("Settings[" + i + "][PTD_Type]", 0);
                }
            }
        } else {
//            params.put("VG_IntegralRuleType", 1);
            //积分开关没有打开时传固定值（仿PC端）
            params.put("Settings[" + 0 + "][PT_ID]", "");
            params.put("Settings[" + 0 + "][VS_CMoney]", 0);
            params.put("Settings[" + 0 + "][VS_Number]", 1);
            params.put("Settings[" + 0 + "][PTD_Type]", 1);
        }

        //折扣统一设置
        if (TypeZheKou.equals("tongyi")) {
            params.put("VG_DiscountRuleType", 0);
            params.put("VG_DiscountUniformRuleValue", stringtongyizhekou == null ? 100 : stringtongyizhekou);
            for (int i = 0; i < mSetList.size(); i++) {
                params.put("Settings[" + i + "][PT_ID]", mSetList.get(i).getPT_GID());
                params.put("Settings[" + i + "][PD_Discount]", mSetList.get(i).getPD_Discount());
                params.put("Settings[" + i + "][PTD_Type]", 1);
            }
        } else {
            params.put("VG_DiscountRuleType", 1);
            params.put("VG_DiscountUniformRuleValue", "100");
            for (int i = 0; i < mSetList.size(); i++) {
                params.put("Settings[" + i + "][PT_ID]", mSetList.get(i).getPT_GID());
                params.put("Settings[" + i + "][PD_Discount]", mSetList.get(i).getPD_Discount());
                params.put("Settings[" + i + "][PTD_Type]", 1);
            }
        }
        //有效次数有限
        if (!typeCiShu) {
            params.put("VG_IsTimeTimes", TextUtils.isEmpty(stringcishu) ? "" : stringcishu);
        }
        //有效时间限时
        params.put("VG_IsTimeNum", TextUtils.isEmpty(stringshijian) ? "" : stringshijian);
        params.put("VG_IsTimeUnit", timeType);

        //计次设定
        if (mListEditedMsg.size() > 0) {
            for (int i = 0; i < mListEditedMsg.size(); i++) {
                params.put("InitServiceList[" + i + "][SC_GD]", mListEditedMsg.get(i).getSC_GD());
                params.put("InitServiceList[" + i + "][SR_Number]", mListEditedMsg.get(i).getSR_Number());
                if (mListEditedMsg.get(i).getSR_Timer() > 0) {
                    params.put("InitServiceList[" + i + "][SR_Timer]", mListEditedMsg.get(i).getSR_Timer());
                    params.put("InitServiceList[" + i + "][SR_TimeUnit]", mListEditedMsg.get(i).getSR_TimeUnit());
                }
            }
        }
        params.put("VG_InitialAmount", sbChuzhi.isChecked() == true ? (stringchuzhi == null ? 0 : stringchuzhi) : 0);//储值设定
        params.put("VG_InitialIntegral", sbJiFen.isChecked() == true ? (stringoriginjifen == null ? 0 : stringoriginjifen) : 0);//积分设定，初始积分
//下面两个是积分设定。比例设定
        int rv = 0;
        if (stringviprechange != null && !stringviprechange.equals("0")) {
            rv = 1;
        }
        params.put("RS_Value", rv);
        params.put("RS_CMoney", sbJiFen.isChecked() == true ? (stringviprechange == null ? 0 : stringviprechange) : 0);//积分设定，充值比例
        if (stringOneKeyOil != null){
            params.put("VG_OilIntegral", sbJiFen.isChecked() == true ? (stringOneKeyOil == null ? 0 : stringOneKeyOil) : 0);//积分设定，一键加油积分比列
        }

        int fv = 0;
        if (stringfastshop != null && !stringfastshop.equals("0")) {
            fv = 1;
        }
        params.put("VS_Value", fv);
        params.put("VS_CMoney", sbJiFen.isChecked() == true ? (stringfastshop == null ? 0 : stringfastshop) : 0);//积分设定，快速消费比例
//
//折扣设置中的快消比例
        params.put("DS_Value", stringkuaixiaobili);
//        params.put("VG_IsLock", mAutoUpDate.isChecked() ? 1 : 0);// 自动升级
//        params.put("VG_IsDownLock", mAutoDwonDate.isChecked() ? 1 : 0);//自动降级
        /***
         * 积分开关
         */
        params.put("VG_IsIntegral", sbJiFen.isChecked() ? 1 : 0);
        /**
         * 储关值开
         * **/
        params.put("VG_IsAccount", sbChuzhi.isChecked() ? 1 : 0);
        /***
         * 折扣开关
         * */
        params.put("VG_IsDiscount", sbZheKou.isChecked() ? 1 : 0);
        /***
         * 限时开关
         * */
        params.put("VG_IsTime", sbXianShi.isChecked() ? 1 : 0);
        /***
         * 计次开关
         * */
        params.put("VG_IsCount", sbJiCi.isChecked() ? 1 : 0);

    }

    private void initViews() {
        tvTitle = findViewById(R.id.tv_titel);
        tvTitle.setText("添加等级");
        sbZheKou = findViewById(R.id.switch_button_zhekou);//折扣开关
        sbChuzhi = findViewById(R.id.switch_button_chuzhi);//储值开关
        sbJiFen = findViewById(R.id.switch_button_jifen);//积分开关
        sbXianShi = findViewById(R.id.switch_button_xianshi);//限时开关
        sbJiCi = findViewById(R.id.switch_button_jici);//计次开关
        mLevelName = findViewById(R.id.et_grade_detail_name);
        mLevelCode = findViewById(R.id.et_grade_detail_code);
        mSaleCardPrice = findViewById(R.id.et_grade_detail_money);
//        mAutoUpDate.setChecked(true);
//        mAutoUpDate.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
//        mAutoUpDate.setTextColor(Color.parseColor("#FF8739"));

        sbZheKou.setChecked(true);
        sbChuzhi.setChecked(true);
        sbJiFen.setChecked(true);
        sbXianShi.setChecked(false);
        sbJiCi.setChecked(true);
        tvRiseFallGrade.setVisibility(View.GONE);
        rRiseFallGrade.setVisibility(View.GONE);
        titleRiseFallGrade.setVisibility(View.GONE);

    }

    private void action() {
        findViewById(R.id.tv_back).setOnClickListener(this);//返回
        findViewById(R.id.img_ok).setOnClickListener(this);//确定
        findViewById(R.id.r_chunzhi_set).setOnClickListener(this);//储值设定
        findViewById(R.id.r_jifen_set).setOnClickListener(this);//积分设定
        findViewById(R.id.r_zhekou_set).setOnClickListener(this);//折扣设定
        findViewById(R.id.r_jici_set).setOnClickListener(this);//计次设定
        findViewById(R.id.r_xianshi_set).setOnClickListener(this);//限时设定

        sbJiFen.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {//积分开关
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (isChecked) {
                } else {
                    CustomToast.makeText(YSLAddNewVipLevelActivity.this, "积分设置关闭，设置数据清零", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sbChuzhi.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (!isChecked) {
                    CustomToast.makeText(YSLAddNewVipLevelActivity.this, "储值设置关闭，设置数据清零", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sbZheKou.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (!isChecked) {
                    CustomToast.makeText(YSLAddNewVipLevelActivity.this, "折扣设置关闭，设置数据清零", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
