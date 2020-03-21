package com.zhiluo.android.yunpu.member.manager.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liangmutian.mypicker.DateUtil;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.ReportMessageBean;
import com.zhiluo.android.yunpu.member.manager.adapter.addCostomfieldsAdapter;
import com.zhiluo.android.yunpu.member.manager.bean.AllMemberListBean;
import com.zhiluo.android.yunpu.member.manager.bean.LabelBean;
import com.zhiluo.android.yunpu.member.manager.bean.VarLabBean;
import com.zhiluo.android.yunpu.member.manager.bean.YSLMemberInfoBean;
import com.zhiluo.android.yunpu.popup.ImageCustomPopup;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ${YSL} on 2018-05-05.
 */

public class VipBaseMessage extends AppCompatActivity implements View.OnClickListener, addCostomfieldsAdapter.OnImageClickEvent {
    @Bind(R.id.civ_add_member_head)
    CircleImageView civAddMemberHead;
    @Bind(R.id.et_add_member_lab)
    TextView etAddMemberLab;
    @Bind(R.id.rl_add_member_face_number)
    LinearLayout rlFaceNumber;
    private TextView tvPhone, tvName, tvCard, tvFaceCard, tvLevel, tvSex, tvCreateCardCost, tvPayWay, tvInitMoney;
    private TextView tvInitIntergal, tvCreateCardTime, tvOverTime, tvStaff, tvVipBirthday, tvTuiJian, tvEmal;
    private LinearLayout llinitmoney;
    private TextView tvIDCard, tvAddress, tvRemake, tvBack;
    private ImageView imgMore;
    private YSLMemberInfoBean.DataBean.DataListBean mMemberInfo;//会员信息
    private AllMemberListBean.DataBean.DataListBean vipdetail;//不完整会员信息
    private boolean mDeletvip;//删除会员权限
    private boolean mEditvip;//编辑会员权限
    private PopupWindow mPopupWindow;
    private SweetAlertDialog mSweetAlertDialog;
    private Bundle mBundle;
    private List<LabelBean> mLabList;
    private StringBuilder mLabName;

    private RecyclerView reLabRecycle;
    private addCostomfieldsAdapter mLabAdapter;
    private List<ReportMessageBean.DataBean.GetCustomFieldsVIPBean> costomfields;//会员自定义属性
    private ChangeHandler changeHandler;
    private List<ReportMessageBean.DataBean.GetSysSwitchListBean> mSwitchEntity;

    private String mMemberPhotoAddress = "/img/nohead.png";//会员头像地址

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(VipBaseMessage.this);
        setContentView(R.layout.activity_vip_base_message);
        ButterKnife.bind(this);
        initViews();
        initDatas();
        initSwitch();
        setDatas();
    }

    private void setDatas() {

        if (mMemberInfo != null) {
            if (mMemberInfo.getVIP_HeadImg() != null && !"".equals(mMemberInfo.getVIP_HeadImg())) {
                String uri;
                if (mMemberInfo.getVIP_HeadImg().contains("http")) {
                    uri = mMemberInfo.getVIP_HeadImg();
                } else {
                    uri = HttpAPI.API().MAIN_DOMAIN + mMemberInfo.getVIP_HeadImg();
                }
                mMemberPhotoAddress = mMemberInfo.getVIP_HeadImg();
                Glide.with(VipBaseMessage.this).load(Uri.parse(uri)).into(civAddMemberHead);
            }
        }
        tvPhone.setText(mMemberInfo.getVIP_CellPhone());
        tvName.setText(mMemberInfo.getVIP_Name());
        tvCard.setText(mMemberInfo.getVCH_Card());
        tvFaceCard.setText(mMemberInfo.getVIP_FaceNumber());
        tvLevel.setText(mMemberInfo.getVG_Name());
        tvSex.setText(getSex());
        tvCreateCardCost.setText(mMemberInfo.getVCH_Fee() + "");
        tvInitMoney.setText(mMemberInfo.getMA_AvailableBalance() + "");
        tvInitIntergal.setText(mMemberInfo.getMA_AvailableIntegral() + "");
        tvCreateCardTime.setText(mMemberInfo.getVCH_CreateTime());
        tvOverTime.setText(getOverTime());
        tvStaff.setText(mMemberInfo.getEM_Name());

        if (mMemberInfo.getVIP_IsLunarCalendar() == 1) {
            List<Integer> mlist = DateUtil.getDateForString(mMemberInfo.getVIP_Birthday().substring(0, 10) + "");
            if (mMemberInfo.getVIP_InterCalaryMonth() == 0) {

                tvVipBirthday.setText(mlist.get(0) + "年" + DateUtil.monthNumToMonthName(mlist.get(1) + "") + DateUtil.dayToDayName(mlist.get(2) + ""));
            } else {
                tvVipBirthday.setText(mlist.get(0) + "年闰" + DateUtil.monthNumToMonthName(mlist.get(1) + "") + DateUtil.dayToDayName(mlist.get(2) + ""));
            }
        } else {
            if (mMemberInfo.getVIP_Birthday() != null && mMemberInfo.getVIP_Birthday().length() > 9) {
                tvVipBirthday.setText(mMemberInfo.getVIP_Birthday().substring(0, 10));
            } else {
                tvVipBirthday.setText(mMemberInfo.getVIP_Birthday());
            }
        }

        tvTuiJian.setText(mMemberInfo.getVIP_Referee());
        tvEmal.setText(mMemberInfo.getVIP_Email());
        tvIDCard.setText(mMemberInfo.getVIP_ICCard());
        tvAddress.setText(mMemberInfo.getVIP_Addr());
        tvRemake.setText(mMemberInfo.getVIP_Remark());
        //标签
        if (mMemberInfo.getVIP_Label() != null && !"".equals(mMemberInfo.getVIP_Label())
                && !mMemberInfo.getVIP_Label().contains("|")) {
            Gson gson1 = new Gson();
            VarLabBean varLabBean = gson1.fromJson("{'lab':" + mMemberInfo.getVIP_Label() + "}", VarLabBean.class);
            if (varLabBean != null) {
                for (int i = 0; i < varLabBean.getLab().size(); i++) {

                    if (i == mLabList.size() - 1 || i == 0) {
                        mLabName.append(varLabBean.getLab().get(i).getItemName());
                    } else {
                        mLabName.append(varLabBean.getLab().get(i).getItemName() + "、");
                    }
                }
            }
            etAddMemberLab.setText(mLabName);

        }

//        getMemberLabel();

    }

    private void initSwitch() {
        mSwitchEntity = (List<ReportMessageBean.DataBean.GetSysSwitchListBean>) CacheData.restoreObject("switch");
        if (mSwitchEntity != null) {
            if (mSwitchEntity.size() > 0) {
                for (int i = 0; i < mSwitchEntity.size(); i++) {
                    //初始积分金额
                    if ("203".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            llinitmoney.setVisibility(View.VISIBLE);
                        } else {
                            llinitmoney.setVisibility(View.GONE);
                        }
                    }
                    //卡面号码
                    if ("208".equals(mSwitchEntity.get(i).getSS_Code())) {
                        if (mSwitchEntity.get(i).getSS_State() == 1) {
                            rlFaceNumber.setVisibility(View.VISIBLE);
                        } else {
                            rlFaceNumber.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    private void initDatas() {
        costomfields = (List<ReportMessageBean.DataBean.GetCustomFieldsVIPBean>) CacheData.restoreObject("costomfield");
        mLabList = new ArrayList<>();
        mLabName = new StringBuilder("");

        mBundle = new Bundle();
        Intent intent = getIntent();
        if (intent != null) {
            mBundle = intent.getBundleExtra("bundle");
            mMemberInfo = (YSLMemberInfoBean.DataBean.DataListBean) mBundle.getSerializable("YSL_VIP_INFO");
            mDeletvip = intent.getBooleanExtra("deletvip", false);
            mEditvip = intent.getBooleanExtra("editvip", false);
            vipdetail = (AllMemberListBean.DataBean.DataListBean) mBundle.getSerializable("VIP_INFO");
        }

        if (costomfields == null) {
            getMemberGrade(2);//获取自定义属性
        } else {
            initadatpter();

        }

    }

    private void initViews() {
        changeHandler = new ChangeHandler();
        tvPhone = findViewById(R.id.et_add_member_phone);
        tvName = findViewById(R.id.et_add_member_name);
        tvCard = findViewById(R.id.et_add_member_num);
        tvFaceCard = findViewById(R.id.et_add_member_face_num);
        tvLevel = findViewById(R.id.tv_vip_level);
        tvSex = findViewById(R.id.tv_sex);
        tvCreateCardCost = findViewById(R.id.et_add_member_money);
        llinitmoney = findViewById(R.id.ll_add_member_init_money);
        tvInitMoney = findViewById(R.id.et_add_member_init_money);
        tvInitIntergal = findViewById(R.id.et_add_member_init_point);
        tvCreateCardTime = findViewById(R.id.tv_create_card_time);
        tvOverTime = findViewById(R.id.et_add_member_overdue_date);
        tvStaff = findViewById(R.id.tv_add_member_commission);
        tvVipBirthday = findViewById(R.id.tv_add_member_birthday);
        tvTuiJian = findViewById(R.id.et_add_member_recommend);
        tvEmal = findViewById(R.id.et_add_member_email);
        tvIDCard = findViewById(R.id.et_add_member_id);
        tvAddress = findViewById(R.id.et_add_member_address);
        tvRemake = findViewById(R.id.et_add_member_remark);
        tvBack = findViewById(R.id.tv_back);
        imgMore = findViewById(R.id.img_more);
        reLabRecycle = findViewById(R.id.lv_add_costomfiled);
        tvBack.setOnClickListener(this);
        imgMore.setOnClickListener(this);
    }

    private String getOverTime() {
        String s = "";
        if (mMemberInfo.getVIP_IsForver() == 1) {
            s = "永久有效";
        } else if (mMemberInfo.getVIP_IsForver() == 0) {
            s = mMemberInfo.getVIP_Overdue();
        }
        return s;
    }

    private String getSex() {
        String s = "";
        if (mMemberInfo.getVIP_Sex() == 0) {
            s = "男";
        } else if (mMemberInfo.getVIP_Sex() == 1) {
            s = "女";
        } else if (mMemberInfo.getVIP_Sex() == 2) {
            s = "保密";
        }
        return s;
    }

    private void showPopWindow(View archon) {
        mPopupWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.pop_ysl_edit_vip_level, null);
        RelativeLayout mEdit = view.findViewById(R.id.r_edit);
        RelativeLayout mDelet = view.findViewById(R.id.r_delet);
        mPopupWindow.setContentView(view);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        //int width = wm.getDefaultDisplay().getWidth();
//        int height = wm.getDefaultDisplay().getHeight();
        int height = YSLUtils.getPhoneHeight();
        int width = YSLUtils.getPhoneWidth();
        mPopupWindow.setWidth(width / 2);
        mPopupWindow.setHeight(height / 5);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAsDropDown(archon);
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                if (YSLUtils.isOpenActivon("编辑会员")) {
                    Intent intent = new Intent(VipBaseMessage.this, ModifyMemberActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("vipdetail", mMemberInfo);
                    intent.putExtra("bundle", bundle);
                    startActivityForResult(intent, 10);
                } else {
                    CustomToast.makeText(VipBaseMessage.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                //删除
                mSweetAlertDialog = new SweetAlertDialog(VipBaseMessage.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("删除会员");
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
                        if (YSLUtils.isOpenActivon("删除会员")) {
                            deleteVip();
                        } else {
                            CustomToast.makeText(VipBaseMessage.this, "没有该功能权限", Toast.LENGTH_SHORT).show();
                        }
                        mSweetAlertDialog.dismiss();
                    }
                });
                mSweetAlertDialog.show();

            }
        });
    }

    /**
     * 查询会员等级
     */
    private void getMemberGrade(final int num) {

        RequestParams params = new RequestParams();
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {

                ReportMessageBean reportbean = CommonFun.JsonToObj(responseString, ReportMessageBean.class);

                costomfields = reportbean.getData().getGetCustomFieldsVIP();
                CacheData.saveObject("costomfield", costomfields);//缓存自定义属性到本地
                if (num == 2) {
                    initadatpter();
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        };
        callBack.setLoadingAnimation(this, "加载数据...", false);
        HttpHelper.post(this, HttpAPI.API().PRE_LOAD, params, callBack);
    }

    /**
     * 删除会员
     */
    private void deleteVip() {
        RequestParams params = new RequestParams();
        params.put("GID", mMemberInfo.getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(VipBaseMessage.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("删除会员");
                mSweetAlertDialog.setContentText("删除会员成功");
                mSweetAlertDialog.setConfirmText("确认");
                mSweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        mSweetAlertDialog.dismiss();
                        ActivityManager.getInstance().exit();
                        startActivity(new Intent(VipBaseMessage.this, MemberListActivity.class));
                        VipBaseMessage.this.finish();

                    }
                });
                mSweetAlertDialog.show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(VipBaseMessage.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(VipBaseMessage.this, "删除中...", false);
        HttpHelper.post(this, HttpAPI.API().DELETEVIP, params, callBack);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back:
//                Intent mIntent = new Intent(VipBaseMessage.this, YSLMemberDetailsActivity.class);
//
//                mBundle.putSerializable("VIP_INFO", vipdetail);
//                mBundle.putSerializable("YSL_VIP_INFO", mMemberInfo);//被初始化的实体类
//                mIntent.putExtra("bundle", mBundle);
//                startActivity(mIntent);
                finish();
                break;
            case R.id.img_more:
                showPopWindow(v);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {

//            getMemberLabel();
        }
    }

    private void initadatpter() {
        for (int i = 0; i < costomfields.size(); i++) {
            if (mMemberInfo.getCustomeFieldList() != null && mMemberInfo.getCustomeFieldList().size() > 0) {
                for (int j = 0; j < mMemberInfo.getCustomeFieldList().size(); j++) {
                    if (costomfields.get(i).getCF_FieldName().equals(mMemberInfo.getCustomeFieldList().get(j).getCF_FieldName())) {
                        costomfields.get(i).setM_ItemsValue(mMemberInfo.getCustomeFieldList().get(j).getCF_Value());
                    }
                }
            }
        }
//        for (int i = 0; i < costomfields.size(); i++) {
//            if (costomfields.get(i).getCF_IsShowVIP() == 0) {
//                costomfields.remove(i);
//            }
//        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        reLabRecycle.setLayoutManager(linearLayoutManager);
        mLabAdapter = new addCostomfieldsAdapter(VipBaseMessage.this, costomfields, changeHandler, 2);
        mLabAdapter.setHasStableIds(true);
        mLabAdapter.setImageClickEvent(this);
        reLabRecycle.setAdapter(mLabAdapter);
    }

    public class ChangeHandler extends Handler {
        /**
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    costomfields = (List<ReportMessageBean.DataBean.GetCustomFieldsVIPBean>) msg.obj;

                    break;
                case 2:

                    break;
            }
        }
    }

    @Override
    public void OnImageClickEvent(int position,boolean isEmpty) {
        if(isEmpty){
            new ImageCustomPopup(this, civAddMemberHead, costomfields.get(position).getM_ItemsValue());
        }
    }
}
