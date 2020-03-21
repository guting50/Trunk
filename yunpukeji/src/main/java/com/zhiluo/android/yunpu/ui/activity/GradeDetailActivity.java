package com.zhiluo.android.yunpu.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DataBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Date: 2018/1/11 11:45
 * Author: 罗咏哲
 * Description: 会员等级详情
 * Version:
 */
public class GradeDetailActivity extends AppCompatActivity {
    @Bind(R.id.tv_back_activity)
    TextView tvBackActivity;
    @Bind(R.id.iv_grade_detail_menu)
    ImageView ivMenu;
    @Bind(R.id.et_grade_detail_name)
    EditText etName;
    @Bind(R.id.et_grade_detail_money)
    EditText etMoney;
    @Bind(R.id.et_grade_fast_consume_discount_scale)
    EditText etFastConsumeDiscountScale;
    @Bind(R.id.et_grade_goods_consume_discount_scale)
    EditText etGoodsConsumeDiscountScale;
    @Bind(R.id.tv_discount_set_detail)
    TextView tvDiscountSetDetail;
    @Bind(R.id.cb_grade_discount_set)
    CheckBox cbDiscountSet;
    @Bind(R.id.cb_grade_discount_detail_set)
    CheckBox cbDiscountDetailSet;
    @Bind(R.id.tv_switch_recharge_money)
    TextView tvSwitchRechargeMoney;
    @Bind(R.id.tv_switch_integral)
    TextView tvSwitchIntegral;
    @Bind(R.id.tv_switch_disscount)
    TextView tvSwitchDisscount;
    @Bind(R.id.tv_switch_limit_time)
    TextView tvSwitchLimitTime;
    @Bind(R.id.tv_switch_limit_count)
    TextView tvSwitchLimitCount;
    @Bind(R.id.r_chunzhi_set)
    RelativeLayout rChuZhi;
    @Bind(R.id.r_jifen_set)
    RelativeLayout rJiFen;
    @Bind(R.id.r_shekou_set)
    RelativeLayout rZheKou;
    @Bind(R.id.r_xianshi_set)
    RelativeLayout rXianShi;
    @Bind(R.id.r_jici_set)
    RelativeLayout rJiCi;
    @Bind(R.id.r_rise_fall_set)
    RelativeLayout rRiseFallSet;

    private PopupWindow mPopupWindow;
    DataBean mGrade;
    private SweetAlertDialog mSweetAlertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_detail);
        ButterKnife.bind(this);
        initSet();
        setListener();
        init();
    }

    private void initSet() {
        etName.setInputType(InputType.TYPE_NULL);
        etMoney.setInputType(InputType.TYPE_NULL);
        etFastConsumeDiscountScale.setInputType(InputType.TYPE_NULL);
        etGoodsConsumeDiscountScale.setInputType(InputType.TYPE_NULL);

        cbDiscountSet.setClickable(false);
        cbDiscountDetailSet.setClickable(false);
    }

    private void init() {
        mGrade = (DataBean) getIntent().getSerializableExtra("Grade");
        mPopupWindow = new PopupWindow(this);

        update();
    }

    private void update() {
        etName.setText(mGrade.getVG_Name());

        //储值设定
        if (mGrade.getVG_IsAccount() == 1) {
            tvSwitchRechargeMoney.setText("开启");
        } else {
            tvSwitchRechargeMoney.setText("关闭");
        }
        etMoney.setText(Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_CardAmount()));
        //积分设定
        if (mGrade.getVG_IsIntegral() == 1) {
            tvSwitchIntegral.setText("开启");
        } else {
            tvSwitchIntegral.setText("关闭");
        }


        //折扣设定
        if (mGrade.getVG_IsDiscount() == 1) {
            tvSwitchDisscount.setText("开启");
        } else {
            tvSwitchDisscount.setText("关闭");
        }
        etFastConsumeDiscountScale.setText("" + (int) mGrade.getDS_Value());

        //限时设置
        if (mGrade.getVG_IsTime() == 1) {
            tvSwitchLimitTime.setText("开启");
        } else {
            tvSwitchLimitTime.setText("关闭");
        }


        //计次设定
        if (mGrade.getVG_IsCount() == 1) {
            tvSwitchLimitCount.setText("开启");
        } else {
            tvSwitchLimitCount.setText("关闭");
        }


        switch (mGrade.getVG_DiscountRuleType()) {
            case 0:
                cbDiscountSet.setChecked(true);
                cbDiscountDetailSet.setChecked(false);
                etGoodsConsumeDiscountScale.setText("" + mGrade.getVG_DiscountUniformRuleValue());
                tvDiscountSetDetail.setClickable(false);
                tvDiscountSetDetail.setTextColor(getResources().getColor(R.color.gray));
                break;
            case 1:
                cbDiscountSet.setChecked(false);
                cbDiscountDetailSet.setChecked(true);
                etGoodsConsumeDiscountScale.setText("");
                tvDiscountSetDetail.setClickable(true);
                tvDiscountSetDetail.setTextColor(getResources().getColor(R.color.blue));
                break;
        }

    }

    /**
     * @param archon ,
     *               编辑弹窗
     */
    private void showPopWindow(View archon) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_ysl_edit_vip_level, null);
        RelativeLayout mEdit = view.findViewById(R.id.r_edit);
        RelativeLayout mDelet = view.findViewById(R.id.r_delet);
        mPopupWindow.setContentView(view);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        mPopupWindow.setWidth(width / 2);
        mPopupWindow.setHeight(height / 5);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAsDropDown(archon);
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //编辑
                mPopupWindow.dismiss();
                Intent intent = new Intent(GradeDetailActivity.this, YSLVipLevelEdit.class);
                intent.putExtra("Grade", mGrade);
                startActivityForResult(intent, 500);
            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除
                mPopupWindow.dismiss();
                mSweetAlertDialog = new SweetAlertDialog(GradeDetailActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("删除会员等级");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setCancelText("取消");
                mSweetAlertDialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                    }
                });
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        delete();
                        mSweetAlertDialog.dismiss();
                        ActivityUtils.finishActivity(GradeManagerActivity.class);
                    }
                });
                mSweetAlertDialog.show();
            }
        });

    }


    private void setListener() {
        rChuZhi.setOnClickListener(new View.OnClickListener() {//储值详情
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeDetailActivity.this, VipLevelChuZhiDetailActivity.class);
                intent.putExtra("chuzhicontent", Decima2KeeplUtil.stringToDecimal("" + mGrade.getVG_InitialAmount()));
                startActivity(intent);
            }
        });
        rJiFen.setOnClickListener(new View.OnClickListener() {//积分详情
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeDetailActivity.this, GradeIntegralDetailActivity.class);
                intent.putExtra("GD", mGrade);
                startActivity(intent);
            }
        });
        rZheKou.setOnClickListener(new View.OnClickListener() {//折扣详情
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeDetailActivity.this, DiscountDetailActivity.class);
                intent.putExtra("GD", mGrade);
                startActivity(intent);
            }
        });
        rXianShi.setOnClickListener(new View.OnClickListener() {//限时详情
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeDetailActivity.this, GradeXianShiDetailActivity.class);
                intent.putExtra("GD", mGrade);
                startActivity(intent);
            }
        });
        rJiCi.setOnClickListener(new View.OnClickListener() {//计次详情
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeDetailActivity.this, TimesDetailActivity.class);
                intent.putExtra("GD", mGrade);
                startActivity(intent);
            }
        });
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  showPopupWindow(v);
                showPopWindow(v);
            }
        });
        tvBackActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //查看折扣详情
        tvDiscountSetDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeDetailActivity.this, DiscountDetailActivity.class);
                intent.putExtra("GD", mGrade);
                startActivity(intent);
            }
        });

        rRiseFallSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GradeDetailActivity.this, VipLevelRiseFallDetailActivity.class);
                intent.putExtra("mGrade", mGrade);
                startActivity(intent);
            }
        });
    }


    /**
     * 删除
     */
    private void delete() {
        RequestParams params = new RequestParams();
        params.put("GID", mGrade.getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(GradeDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("删除成功");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        finish();
                    }
                });
                mSweetAlertDialog.show();

                getAllMessage();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(GradeDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在删除...", false);
        HttpHelper.post(this, "VIPGrade/DelVIPGrade", params, callBack);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 500 && resultCode == 500) {
            if (data != null) {
                mGrade = (DataBean) data.getSerializableExtra("GD");
                update();
                initSet();
            }
        }
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
     * 更新会员等级缓存
     *
     * **/

    private void getAllMessage() {
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);
                if (reportbean != null) {

                    List<ReportMessageBean.DataBean.VIPGradeListBean> gradebean = reportbean.getData().getVIPGradeList();
                    CacheData.saveObject("grade", gradebean);//缓存等级列表到本地

                }

            }

            @Override
            public void onFailure(String msg) {
            }
        });
    }
}
