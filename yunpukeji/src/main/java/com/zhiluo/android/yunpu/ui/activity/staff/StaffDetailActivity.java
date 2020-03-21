package com.zhiluo.android.yunpu.ui.activity.staff;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.DepListBean;
import com.zhiluo.android.yunpu.entity.StaffBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.ui.activity.tc.EditTCActivity;
import com.zhiluo.android.yunpu.ui.activity.tc.TCDetailActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class  StaffDetailActivity extends AppCompatActivity implements MyMenuPopWindow.OnItemClickListener {
    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.iv_staff_detail_menu)
    ImageView ivMenu;
    @Bind(R.id.rl_staff_detail_head)
    RelativeLayout rlHead;
    @Bind(R.id.tv_staff_detail_name)
    TextView tvName;
    @Bind(R.id.tv_staff_detail_number)
    TextView tvNumber;
    @Bind(R.id.tv_staff_detail_del)
    TextView tvDel;
    @Bind(R.id.tv_staff_detail_store)
    TextView tvStore;
    @Bind(R.id.tv_staff_detail_phone)
    TextView tvPhone;
    @Bind(R.id.tv_staff_detail_address)
    TextView tvAddress;
    @Bind(R.id.tv_staff_detail_birthday)
    TextView tvBirthday;
    @Bind(R.id.cb_add_staff_tc_sale_card)
    CheckBox cbSaleCard;
    @Bind(R.id.cb_add_staff_tc_recharge_money)
    CheckBox cbRechargeMoney;
    @Bind(R.id.cb_add_staff_tc_recharge_times)
    CheckBox cbRechargeTimes;
    @Bind(R.id.cb_add_staff_tc_goods_consume)
    CheckBox cbGoodsConsume;
    @Bind(R.id.cb_add_staff_tc_fast_consume)
    CheckBox cbFastConsume;
    @Bind(R.id.cb_add_staff_tc_times_consume)
    CheckBox cbTimesConsume;
    @Bind(R.id.et_staff_detail_remark)
    EditText etRemark;
    @Bind(R.id.te_sex)
    TextView teSex;

    private StaffBean mStaffBean;
    private DepListBean mDepListBean;
    private MyMenuPopWindow mPopupMenu;
    private SweetAlertDialog mSweetAlertDialog;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mStaffBean = (StaffBean) getIntent().getSerializableExtra("Staff");
        mDepListBean = (DepListBean) getIntent().getSerializableExtra("Dep");
        etRemark.setInputType(InputType.TYPE_NULL);
        mPopupMenu = new MyMenuPopWindow(this, "编辑", "删除", null);
        mPopupMenu.setOnItemClickListener(this);
        update(mStaffBean);
        setListener();
    }

    private void update(StaffBean bean) {
        if (bean != null) {
            tvName.setText(bean.getEM_Name());
            tvNumber.setText(bean.getEM_Code());
            tvDel.setText(bean.getDM_Name());
            if (bean.getSM_Name() != null) {
                tvStore.setText(bean.getSM_Name() + "");
            } else {
                tvStore.setText("不归属任何店铺");
            }
            //男
            if (bean.getEM_Sex() == 0) {
                teSex.setText("男");
            }
            //女
            if (bean.getEM_Sex() == 1) {
                teSex.setText("女");
            }
            tvPhone.setText(bean.getEM_Phone());
            tvAddress.setText(bean.getEM_Addr());
            if (bean.getEM_Birthday() != null && !bean.getEM_Birthday().equals("null")) {
                String birthday = bean.getEM_Birthday().toString();
                if (birthday.length() > 9) {
                    birthday = birthday.substring(0, 10);
                }
                tvBirthday.setText(birthday);
            }
            etRemark.setText(bean.getEM_Remark());
            //售卡
            if (bean.getEM_TipCard() == 1) {
                cbSaleCard.setChecked(true);
            } else {
                cbSaleCard.setChecked(false);
            }
            //充值
            if (bean.getEM_TipRecharge() == 1) {
                cbRechargeMoney.setChecked(true);
            } else {
                cbRechargeMoney.setChecked(false);
            }
            //充次
            if (bean.getEM_TipChargeTime() == 1) {
                cbRechargeTimes.setChecked(true);
            } else {
                cbRechargeTimes.setChecked(false);
            }
            //商品消费
            if (bean.getEM_TipGoodsConsume() == 1) {
                cbGoodsConsume.setChecked(true);
            } else {
                cbGoodsConsume.setChecked(false);
            }
            //快速消费
            if (bean.getEM_TipFastConsume() == 1) {
                cbFastConsume.setChecked(true);
            } else {
                cbFastConsume.setChecked(false);
            }
            //计次消费
            if (bean.getEM_TipTimesConsume() == 1) {
                cbTimesConsume.setChecked(true);
            } else {
                cbTimesConsume.setChecked(false);
            }
        }
        initCheckBox(cbTimesConsume,cbTimesConsume.isChecked());
        initCheckBox(cbFastConsume,cbFastConsume.isChecked());
        initCheckBox(cbGoodsConsume,cbGoodsConsume.isChecked());
        initCheckBox(cbRechargeTimes,cbRechargeTimes.isChecked());
        initCheckBox(cbRechargeMoney,cbRechargeMoney.isChecked());
        initCheckBox(cbSaleCard,cbSaleCard.isChecked());
    }

    private void showPopWindow(View archon) {
        mPopupWindow=new PopupWindow(this);
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
                Intent intent = new Intent(StaffDetailActivity.this, EditStaffActivity.class);
                intent.putExtra("Staff", mStaffBean);
                mDepListBean.getData().remove(0);
                intent.putExtra("Dep", mDepListBean);
                startActivityForResult(intent, 500);
            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                //删除
                mSweetAlertDialog = new SweetAlertDialog(StaffDetailActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("删除员工");
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
                    }
                });
                mSweetAlertDialog.show();
            }
        });
    }
    /**
     * @param box ,
     * @param isChecked .
     *                  初始化CheckBox
     */
    private void initCheckBox(CheckBox box, boolean isChecked) {
        if (isChecked) {
            box.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
            box.setTextColor(Color.parseColor("#FF8739"));
        } else {
            box.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
            box.setTextColor(Color.parseColor("#333333"));
        }
    }

    private void setListener() {
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // showPopupWindow(v);
                showPopWindow(v);
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    /**
     * 显示PopMenu
     */
    private void showPopupWindow(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        mPopupMenu.showAsDropDown(StaffDetailActivity.this.findViewById(R.id.rl_staff_detail_head),
                location[0] - mPopupMenu.getWidth() - 100, 0);
    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.tv_menu_pop_first:
                //编辑
                mPopupMenu.dismiss();
                Intent intent = new Intent(StaffDetailActivity.this, EditStaffActivity.class);
                intent.putExtra("Staff", mStaffBean);
                mDepListBean.getData().remove(0);
                intent.putExtra("Dep", mDepListBean);
                startActivityForResult(intent, 500);
                break;
            case R.id.tv_menu_pop_second:
                mPopupMenu.dismiss();
                //删除
                mSweetAlertDialog = new SweetAlertDialog(StaffDetailActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("删除员工");
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
                    }
                });
                mSweetAlertDialog.show();
                break;
            default:
                mPopupMenu.dismiss();
                break;
        }
    }

    /**
     * 删除员工
     */
    private void delete() {
        RequestParams params = new RequestParams();
        params.put("GID", mStaffBean.getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                getAllMessage();
                mSweetAlertDialog = new SweetAlertDialog(StaffDetailActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("删除员工成功");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        ActivityManager.getInstance().exit();
                        Intent intent = new Intent(StaffDetailActivity.this,StaffManagerActivity.class);
                        startActivity(intent);
                        finish();
                        mSweetAlertDialog.dismiss();
                    }
                });
                mSweetAlertDialog.show();



            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(StaffDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在删除...", false);
        HttpHelper.post(this, "Empl/DelEmpl", params, callBack);
    }

    /**
     * 编辑完成时详情页面刷新
     *
     * @param bean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(StaffBean bean) {
        update(bean);
        mStaffBean = bean;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSweetAlertDialog != null) {
            mSweetAlertDialog.dismiss();
            mSweetAlertDialog = null;
        }
        EventBus.getDefault().unregister(this);
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
                    List<ReportMessageBean.DataBean.EmplistBean> staffbean = reportbean.getData().getEmplist();
                    CacheData.saveObject("staff", staffbean);//缓存员工列表到本地
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(StaffDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
