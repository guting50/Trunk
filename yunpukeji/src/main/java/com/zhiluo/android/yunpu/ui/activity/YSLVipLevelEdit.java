package com.zhiluo.android.yunpu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.suke.widget.SwitchButton;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.entity.GradeSetBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.MemberGrade;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * Created by ${YSL} on 2018-04-10.
 * 编辑等级
 */

public class YSLVipLevelEdit extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.r_rise_fall_grade)
    RelativeLayout rRiseFallGrade;
    private EditText mLevelName, mLevelCode, mSaleCardPrice;
    private SwitchButton sbZheKou, sbChuzhi, sbJiFen, sbXianShi, sbJiCi;
    private Intent mIntent;
    private String stringchuzhi, stringoriginjifen, stringOneKeyOil, stringfastshop, stringviprechange, stringunitfied;
    private String stringkuaixiaobili, stringtongyizhekou;
    private int zhekouzhi;
    private DataBean mGrade;
    private GradeSetBean mGradeSetBean;
    private String mintergalType = "";//积分类型，在设置详情界面传过来，分为统一设置和详情设置
    private RequestParams params;
    //    private GradeSetBean mGradeSetBean = new GradeSetBean();
    private String TypeJiFen = "";
    private String TypeZheKou = "";
    private boolean typeCiShu, typeShiJian;
    private String timeType, stringcishu, stringshijian;
    private List<DataBean.InitServiceListBean> mListEditedMsg = new ArrayList<>();
    private SweetAlertDialog mSweetAlertDialog;
    private MemberGrade mMemberGrade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ysl_viplevel_edit);
        ButterKnife.bind(this);
        params = new RequestParams();
        mGrade = (DataBean) getIntent().getSerializableExtra("Grade");
        stringkuaixiaobili = mGrade.getDS_Value() + "";
        initViews();
        if (mGrade != null) {
            dataAttachViews();
        }
        action();

    }

    private void dataAttachViews() {
        mLevelName.setText(mGrade.getVG_Name() == null ? "未设置" : mGrade.getVG_Name());//等级名称
        mLevelName.setSelection(mLevelName.getText().toString().length());
        mLevelCode.setText(mGrade.getVG_Code() == null ? "未设置" : mGrade.getVG_Code());//等级编码
        mSaleCardPrice.setText(Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_CardAmount()));//售卡金额

        //储值设定
        if (mGrade.getVG_IsAccount() == 1) {
            sbChuzhi.setChecked(true);
            stringchuzhi = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_InitialAmount());
        } else {
            sbChuzhi.setChecked(false);
        }
        //积分开关
        if (mGrade.getVG_IsIntegral() == 1) {
            sbJiFen.setChecked(true);
            if (mGrade.getVG_IntegralRuleType() == 0) {//返回的积分类型
                mintergalType = "nuitfied";
            }
            stringoriginjifen = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_InitialIntegral());//初始积分
            stringOneKeyOil = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_OilIntegral());//一键加油积分比列
            stringfastshop = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVS_CMoney());//快速消费比例
            stringviprechange = Decima2KeeplUtil.stringToDecimal("" + mGrade.getRS_CMoney());//会员充值比例
            stringunitfied = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_IntegralUniformRuleValue());//统一设置


            if (mGrade.getSettings() != null) {
                for (int i = 0; i < mGrade.getSettings().size(); i++) {
                    mGrade.getSettings().get(i).setVS_CMoney(mGrade.getSettings().get(i).getVS_CMoney());
                    mGrade.getSettings().get(i).setVS_Number(mGrade.getSettings().get(i).getVS_Number());
                }
            }
        } else {
            sbJiFen.setChecked(false);
        }
        //折扣开关
        if (mGrade.getVG_IsDiscount() == 1) {
            sbZheKou.setChecked(true);
            if (mGrade.getVG_DiscountRuleType() == 0) {//返回的积分类型
                TypeZheKou = "tongyi";
            }
//            TypeZheKou = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_DiscountRuleType());
            stringkuaixiaobili = Decima2KeeplUtil.stringToDecimal("" + mGrade.getDS_Value());
            stringtongyizhekou = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_DiscountUniformRuleValue());
            if (mGrade.getSettings() != null) {
                for (int i = 0; i < mGrade.getSettings().size(); i++) {
                    mGrade.getSettings().get(i).setPD_Discount(mGrade.getSettings().get(i).getPD_Discount());//修改对象中的值
                }
            }
        } else {
            sbZheKou.setChecked(false);
        }
        //限时开关
        if (mGrade.getVG_IsTime() == 1) {
            if (mGrade.getVG_IsTimeTimes() != null) {
                stringcishu = "" + mGrade.getVG_IsTimeTimes();
            }
            if (mGrade.getVG_IsTimeTimes() == null || mGrade.getVG_IsTimeTimes().equals("null")) {
                typeCiShu = true;
            }
            if (mGrade.getVG_IsTimeNum() != null) {
                stringshijian = "" + mGrade.getVG_IsTimeNum();
                timeType = mGrade.getVG_IsTimeUnit() + "";
            }
            sbXianShi.setChecked(true);
        } else {
            sbXianShi.setChecked(false);
        }
        //计次设定
        if (mGrade.getVG_IsCount() == 1) {
            sbJiCi.setChecked(true);
            if (mGrade.getInitServiceList() != null && mGrade.getInitServiceList().size() > 0) {
                mListEditedMsg.addAll(mGrade.getInitServiceList());
            }
        } else {
            sbJiCi.setChecked(false);
        }


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
                YSLVipLevelEdit.this.finish();
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
                mIntent = new Intent(YSLVipLevelEdit.this, VipLevelChuZhi.class);
                mIntent.putExtra("Grade", mGrade);
                mIntent.putExtra("GID", mGrade.getGID());
                startActivityForResult(mIntent, 10);
                break;
            case R.id.r_jifen_set://积分
                if (!sbJiFen.isChecked()) {
                    CustomToast.makeText(this, "请打开积分开关后再设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                mIntent = null;
                mIntent = new Intent(YSLVipLevelEdit.this, VipIntergalsetACtivity.class);
                mIntent.putExtra("Grade", mGrade);
                mIntent.putExtra("GID", mGrade.getGID());
                mGrade = (DataBean) getIntent().getSerializableExtra("Grade");
                mGradeSetBean = new GradeSetBean();
                List<GradeSetBean.DataBean> ls = new ArrayList<>();
                for (int i = 0; i < mGrade.getSettings().size(); i++) {
                    GradeSetBean.DataBean bean = new GradeSetBean.DataBean();
                    bean.setPT_Name(mGrade.getSettings().get(i).getPT_Name());
                    bean.setGID(mGrade.getSettings().get(i).getPT_GID());
                    bean.setPD_Discount(mGrade.getSettings().get(i).getPD_Discount());
                    bean.setVS_CMoney(mGrade.getSettings().get(i).getVS_CMoney());
                    bean.setVS_Number(mGrade.getSettings().get(i).getVS_Number());
                    ls.add(bean);
                }
                mGradeSetBean.setData(ls);
                mIntent.putExtra("GD", mGradeSetBean);
                startActivityForResult(mIntent, 20);
                break;
            case R.id.r_zhekou_set://折扣
                if (!sbZheKou.isChecked()) {
                    CustomToast.makeText(this, "请打开折扣开关后再设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                mIntent = null;
                mIntent = new Intent(YSLVipLevelEdit.this, YSLDiscountACtivity.class);
                mIntent.putExtra("Grade", mGrade);
//                mIntent.putExtra("ZK", mGradeSetBean);
                mIntent.putExtra("GID", mGrade.getGID());
                startActivityForResult(mIntent, 30);
                break;
            case R.id.r_jici_set://计次
                if (!sbJiCi.isChecked()) {
                    CustomToast.makeText(YSLVipLevelEdit.this, "请先打开开关后再设置", Toast.LENGTH_SHORT);
                    return;
                }
                mIntent = null;
                mIntent = new Intent(YSLVipLevelEdit.this, DisplayJiCiServiceActivity.class);
                mIntent.putExtra("Service", (Serializable) mListEditedMsg);
//                mIntent.putExtra("Grade", mGrade);
                startActivityForResult(mIntent, 40);
                break;
            case R.id.r_xianshi_set://限时
                if (!sbXianShi.isChecked()) {
                    CustomToast.makeText(this, "请打开限时开关后再设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                mIntent = null;
                mIntent = new Intent(YSLVipLevelEdit.this, YSLXianShiSetActivity.class);
                mIntent.putExtra("Grade", mGrade);
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
                    GradeSetBean set = (GradeSetBean) data.getSerializableExtra("integral_set");
                    if (set != null) {
                        for (int i = 0; i < set.getData().size(); i++) {
                            mGrade.getSettings().get(i).setVS_CMoney(set.getData().get(i).getVS_CMoney());
                            mGrade.getSettings().get(i).setVS_Number(set.getData().get(i).getVS_Number());
                        }
                    }
                    mintergalType = data.getStringExtra("intergalsettype");//返回的积分类型
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
                    DataBean set = (DataBean) data.getSerializableExtra("discount_set");
                    if (set != null) {
                        for (int i = 0; i < set.getSettings().size(); i++) {
                            mGrade.getSettings().get(i).setPD_Discount(set.getSettings().get(i).getPD_Discount());//修改对象中的值
                        }
                        if (!TypeZheKou.equals("tongyi")) {
                            mGrade.setVG_DiscountUniformRuleValue(set.getVG_DiscountUniformRuleValue());
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
                    typeShiJian = data.getBooleanExtra("shijian", false);
                    timeType = data.getStringExtra("tShiJianDanWei");
                    stringcishu = data.getStringExtra("edcishu");
                    stringshijian = data.getStringExtra("edshijian");
                } catch (Exception e) {
                }
                break;
            case 600:
                if (data != null) {
                    String gradegid = data.getStringExtra("GD");
                    getMemberGrade(gradegid);
                }
                break;
        }
    }

    private void save() {

        if (TextUtils.isEmpty(mLevelName.getText().toString()) || mLevelName.getText().toString().equals("")) {
            CustomToast.makeText(this, "请填写等级名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (sbChuzhi.isChecked() && TypeJiFen.equals("nuitfied")) {
            if (TextUtils.isEmpty(stringchuzhi) || stringchuzhi.equals("") || stringchuzhi == null) {
                CustomToast.makeText(this, "储值设置中，请设置保存后再提交", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (sbJiFen.isChecked() && TypeJiFen.equals("nuitfied")) {
            if (TextUtils.isEmpty(stringunitfied) || stringunitfied.equals("") || stringunitfied == null) {
                CustomToast.makeText(this, "积分设置中，请设置保存后再提交", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (sbJiFen.isChecked()) {
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


        params.put("GID", mGrade.getGID());
        params.put("VG_Name", mLevelName.getText().toString());//等级名称
        params.put("VG_Code", mLevelCode.getText() == null ? "" : mLevelCode.getText().toString());//登记编码
        params.put("VG_Remark", "");
        params.put("VG_CardAmount", mSaleCardPrice.getText() == null ? 0 : mSaleCardPrice.getText().toString());//售卡金额
        params.put("VG_InitialAmount", sbChuzhi.isChecked() == true ? (stringchuzhi == null ? 0 : stringchuzhi) : 0);//储值设定
        params.put("VG_InitialIntegral", sbJiFen.isChecked() == true ? (stringoriginjifen == null ? 0 : stringoriginjifen) : 0);//积分设定，初始积分
        /**
         * 储关值开
         * **/
        params.put("VG_IsAccount", sbChuzhi.isChecked() ? 1 : 0);
        /***
         * 积分开关
         */
        params.put("VG_IsIntegral", sbJiFen.isChecked() ? 1 : 0);
        /***
         * 折扣开关
         * */
        params.put("VG_IsDiscount", sbZheKou.isChecked() ? 1 : 0);
        /***
         * 计次开关
         * */
        params.put("VG_IsCount", sbJiCi.isChecked() ? 1 : 0);
        /***
         * 限时开关
         * */
        params.put("VG_IsTime", sbXianShi.isChecked() ? 1 : 0);
        //折扣设置中的快消比例
        params.put("DS_Value", stringkuaixiaobili);
        //积分设定，充值比例
        params.put("RS_CMoney", sbJiFen.isChecked() == true ? (stringviprechange == null ? 0 : stringviprechange) : 0);
        //下面两个是积分设定。比例设定
        int rv = 0;
        if (stringviprechange != null && !stringviprechange.equals("0")) {
            rv = 1;
        }
        params.put("RS_Value", rv);
        params.put("VS_CMoney", sbJiFen.isChecked() == true ? (stringfastshop == null ? 0 : stringfastshop) : 0);//积分设定，快速消费比例
        int fv = 0;
        if (stringfastshop != null && !stringfastshop.equals("0")) {
            fv = 1;
        }
        params.put("VS_Value", fv);

        Li("======等级管理=====random:" + "GID: " + mGrade.getGID());
        Li("======等级管理=====random:" + "VG_Name: " + mLevelName.getText().toString());
        Li("======等级管理=====random:" + "VG_Code: " + (mLevelCode.getText() == null ? "" : mLevelCode.getText().toString()));
        Li("======等级管理=====random:" + "VG_Remark: ");
        Li("======等级管理=====random:" + "VG_CardAmount: " + (mSaleCardPrice.getText() == null ? 0 : mSaleCardPrice.getText().toString()));
        Li("======等级管理=====random:" + "VG_InitialAmount: " + (sbChuzhi.isChecked() == true ? (stringchuzhi == null ? 0 : stringchuzhi) : 0));
        Li("======等级管理=====random:" + "VG_InitialIntegral: " + (sbJiFen.isChecked() == true ? (stringoriginjifen == null ? 0 : stringoriginjifen) : 0));
        Li("======等级管理=====random:" + "VG_IsAccount: " + (sbChuzhi.isChecked() ? 1 : 0));
        Li("======等级管理=====random:" + "VG_IsIntegral: " + (sbJiFen.isChecked() ? 1 : 0));
        Li("======等级管理=====random:" + "VG_IsDiscount: " + (sbZheKou.isChecked() ? 1 : 0));
        Li("======等级管理=====random:" + "VG_IsCount: " + (sbJiCi.isChecked() ? 1 : 0));
        Li("======等级管理=====random:" + "VG_IsTime: " + (sbXianShi.isChecked() ? 1 : 0));
        Li("======等级管理=====random:" + "DS_Value: " + stringkuaixiaobili);
        Li("======等级管理=====random:" + "RS_CMoney: " + (sbJiFen.isChecked() == true ? (stringviprechange == null ? 0 : stringviprechange) : 0));
        Li("======等级管理=====random:" + "RS_Value: " + rv);
        Li("======等级管理=====random:" + "VS_CMoney: " + (sbJiFen.isChecked() == true ? (stringfastshop == null ? 0 : stringfastshop) : 0));
        Li("======等级管理=====random:" + "VS_Value: " + fv);

        for (int i = 0; i < mGrade.getSettings().size(); i++) {
            params.put("Settings[" + i + "][PT_ID]", mGrade.getSettings().get(i).getPT_GID());
            Li("======等级管理=====random:" + "Settings[" + i + "][PT_ID]: "+mGrade.getSettings().get(i).getPT_GID());
            //一键加油没有这个参数,而且PD_Discount参数不传
            if (MyApplication.isOneKey) {

            } else {
                params.put("Settings[" + i + "][PD_Discount]", mGrade.getSettings().get(i).getPD_Discount());
                Li("======等级管理=====random:" + "Settings[" + i + "][PD_Discount]: "+mGrade.getSettings().get(i).getPD_Discount());
            }
            params.put("Settings[" + i + "][VS_CMoney]", mGrade.getSettings().get(i).getVS_CMoney());
            params.put("Settings[" + i + "][VS_Number]", mGrade.getSettings().get(i).getVS_Number());
            params.put("Settings[" + i + "][PTD_Type]", 1);
        }
        //积分统一设置
        if (mintergalType.equals("nuitfied")) {
            params.put("VG_IntegralRuleType", 0);
            params.put("VG_IntegralUniformRuleValue", Decima2KeeplUtil.stringToDecimal(stringunitfied));//stringunitfied 统一设置积分比例
        } else {//详情设置
            params.put("VG_IntegralRuleType", 1);
            params.put("VG_IntegralUniformRuleValue", mGrade.getVG_IntegralUniformRuleValue());//stringunitfied 统一设置积分比例
        }
        //折扣统一设置
        if (TypeZheKou.equals("tongyi")) {
            double d = Double.parseDouble(stringtongyizhekou);
            zhekouzhi = (int) d;
            params.put("VG_DiscountRuleType", 0);
            params.put("VG_DiscountUniformRuleValue", zhekouzhi);

        } else {
            params.put("VG_DiscountRuleType", 1);
            params.put("VG_DiscountUniformRuleValue", mGrade.getVG_DiscountUniformRuleValue());
        }


        params.put("US_Code", "IntegerFulfil");
//        params.put("US_Value", mUpDateCondition.getText().toString());//升级条件
        //有效次数有限
        if (!typeCiShu) {
            params.put("VG_IsTimeTimes", TextUtils.isEmpty(stringcishu) ? "" : stringcishu);
        }
        //有效时间限时
        if (!typeShiJian) {
            params.put("VG_IsTimeNum", TextUtils.isEmpty(stringshijian) ? "" : stringshijian);
            params.put("VG_IsTimeUnit", timeType);
        }
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


        if (stringOneKeyOil != null) {
            params.put("VG_OilIntegral", sbJiFen.isChecked() == true ? (stringOneKeyOil == null ? 0 : stringOneKeyOil) : 0);//积分设定，一键加油积分比列
        }

//        params.put("VG_IsLock", mAutoUpDate.isChecked() ? 1 : 0);// 自动升级
//        params.put("VG_IsDownLock", mAutoDwonDate.isChecked() ? 1 : 0);//自动降级


        Li("======等级管理=====random:" + params);

        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
//                EditGradeResultBean bean = CommonFun.JsonToObj(responseString, EditGradeResultBean.class);


//                final DataBean dataBean = bean.getData();
                mSweetAlertDialog = new SweetAlertDialog(YSLVipLevelEdit.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("编辑成功");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
//                        Intent intent = new Intent();
//                        intent.putExtra("GD", dataBean);
//                        setResult(500, intent);
                        YSLVipLevelEdit.this.finish();
                        ActivityUtils.finishActivity(GradeDetailActivity.class);
                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(YSLVipLevelEdit.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "保存中...", false);
        HttpHelper.post(YSLVipLevelEdit.this, HttpAPI.API().EDITVIPGRADE, params, callBack);
    }


    private void initViews() {
        sbZheKou = findViewById(R.id.switch_button_zhekou);//折扣开关
        sbChuzhi = findViewById(R.id.switch_button_chuzhi);//储值开关
        sbJiFen = findViewById(R.id.switch_button_jifen);//积分开关
        sbXianShi = findViewById(R.id.switch_button_xianshi);//限时开关
        sbJiCi = findViewById(R.id.switch_button_jici);//计次开关

        mLevelName = findViewById(R.id.et_grade_detail_name);
        mLevelName.requestFocus();
        mLevelCode = findViewById(R.id.et_grade_detail_code);
        mSaleCardPrice = findViewById(R.id.et_grade_detail_money);

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
                    mintergalType = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_IntegralRuleType());//返回的积分类型
                    stringoriginjifen = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_InitialIntegral());//初始积分
                    stringOneKeyOil = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_OilIntegral());//一键加油比例
                    stringfastshop = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVS_CMoney());//快速消费比例
                    stringviprechange = Decima2KeeplUtil.stringToDecimal("" + mGrade.getRS_CMoney());//会员充值比例
                    stringunitfied = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_IntegralUniformRuleValue());//统一设置


                    if (mGrade.getSettings() != null) {
                        for (int i = 0; i < mGrade.getSettings().size(); i++) {
                            mGrade.getSettings().get(i).setVS_CMoney(mGrade.getSettings().get(i).getVS_CMoney());
                            mGrade.getSettings().get(i).setVS_Number(mGrade.getSettings().get(i).getVS_Number());
                        }
                    }
                } else {
                    mintergalType = "";
                    stringfastshop = "";
                    stringOneKeyOil = null;
                    stringviprechange = "";
                    stringunitfied = "";
//                    mGrade.getSettings().clear();
                    if (mGrade.getSettings() != null) {
                        for (int i = 0; i < mGrade.getSettings().size(); i++) {
                            mGrade.getSettings().get(i).setVS_CMoney(0.00);
                            mGrade.getSettings().get(i).setVS_Number(0.00);
                        }
                    }

                    CustomToast.makeText(YSLVipLevelEdit.this, "积分设置关闭，设置数据清零", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sbChuzhi.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if (!isChecked) {
                    stringchuzhi = "";
                    CustomToast.makeText(YSLVipLevelEdit.this, "储值设置关闭，设置数据清零", Toast.LENGTH_SHORT).show();
                } else {
                    stringchuzhi = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_InitialAmount());
                }
            }
        });
        sbZheKou.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (!isChecked) {
                    TypeZheKou = "";
//                    stringkuaixiaobili = "";
                    stringtongyizhekou = "";

//                    if (mGrade.getSettings() != null) {
//                        for (int i = 0; i < mGrade.getSettings().size(); i++) {
//                            mGrade.getSettings().get(i).setPD_Discount(0);//修改对象中的值
//                        }
//                    }
//                    CustomToast.makeText(YSLVipLevelEdit.this, "折扣设置关闭，设置数据清零", Toast.LENGTH_SHORT).show();
                } else {
                    TypeZheKou = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_DiscountRuleType());
                    stringkuaixiaobili = Decima2KeeplUtil.stringToDecimal("" + mGrade.getDS_Value());
                    stringtongyizhekou = Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_DiscountUniformRuleValue());

                    if (mGrade.getSettings() != null) {
                        for (int i = 0; i < mGrade.getSettings().size(); i++) {
                            mGrade.getSettings().get(i).setPD_Discount(mGrade.getSettings().get(i).getPD_Discount());//修改对象中的值
                        }
                    }
                }
            }
        });

        rRiseFallGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(YSLVipLevelEdit.this, VipLevelRiseOrFallActivity.class);
                intent.putExtra("Grade", mGrade);
                startActivityForResult(intent, 600);
            }
        });

    }

    private void getMemberGrade(final String gid) {
        RequestParams params = new RequestParams();
        params.put("GID", "");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mMemberGrade = CommonFun.JsonToObj(responseString, MemberGrade.class);
                for (int i = 0; i < mMemberGrade.getData().size(); i++) {
                    if (gid.equals(mMemberGrade.getData().get(i).getGID())) {
                        mGrade = mMemberGrade.getData().get(i);
                    }
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(YSLVipLevelEdit.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(YSLVipLevelEdit.this, "更新中...", false);
        HttpHelper.post(this, HttpAPI.API().USERGRADE, params, callBack);

    }


}
