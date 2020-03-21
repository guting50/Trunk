package com.zhiluo.android.yunpu.ui.activity.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.RoleListBean;
import com.zhiluo.android.yunpu.entity.UserBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.activity.ChangePwdActivity;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.ActivityManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 用户详情
 */
public class UserDetailActivity extends BaseActivity implements MyMenuPopWindow.OnItemClickListener {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.iv_user_detail_menu)
    ImageView ivMenu;
    @Bind(R.id.rl_user_detail_head)
    RelativeLayout rlHead;
    @Bind(R.id.tv_user_detail_account)
    TextView tvAccount;
    @Bind(R.id.tv_user_detail_name)
    TextView tvName;
    //    @Bind(R.id.tv_user_detail_paw)
//    TextView tvPaw;
    @Bind(R.id.tv_user_detail_role)
    TextView tvRole;
    @Bind(R.id.tv_user_detail_shop)
    TextView tvShop;
    @Bind(R.id.tv_user_detail_phone)
    TextView tvPhone;
    @Bind(R.id.tv_user_detail_remark)
    TextView tvRemark;
    @Bind(R.id.cb_islock_no)
    CheckBox cbIsLockNo;
    @Bind(R.id.cb_islock_yse)
    CheckBox cbIsLockYse;
    @Bind(R.id.civ_add_member_head)
    CircleImageView civAddMemberHead;

    private UserBean mUserBean;
    private RoleListBean mRoleListBean;
    private MyMenuPopWindow mPopupMenu;
    private SweetAlertDialog mSweetAlertDialog;
    private PopupWindow mPopupWindow;
    private String superAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
        } catch (Exception e) {
        }
        ActivityManager.getInstance().addActivity(this);
        savedInstanceState = null;
//        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        cbIsLockNo.setClickable(false);
        cbIsLockYse.setClickable(false);
        mUserBean = (UserBean) getIntent().getSerializableExtra("User");
        mRoleListBean = (RoleListBean) getIntent().getSerializableExtra("Role");
        superAccount = getIntent().getStringExtra("superAccount");
        update(mUserBean);
        mPopupMenu = new MyMenuPopWindow(this, "编辑", "删除", null);
        mPopupMenu.setOnItemClickListener(this);
        setListener();
    }

    private void showPopWindow(View archon) {
        mPopupWindow = new PopupWindow(this);
        View view = LayoutInflater.from(this).inflate(R.layout.pop_user_detail, null);
        RelativeLayout mEdit = view.findViewById(R.id.r_edit);
        RelativeLayout mDelet = view.findViewById(R.id.r_delet);
        RelativeLayout mReSet = view.findViewById(R.id.r_reset);
        mPopupWindow.setContentView(view);
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        mPopupWindow.setWidth(width / 2);
        mPopupWindow.setHeight(height / 4);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAsDropDown(archon);
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //编辑
                mPopupWindow.dismiss();
                Intent intent = new Intent(UserDetailActivity.this, EditUserActivity.class);
                intent.putExtra("User", mUserBean);
//                mRoleListBean.getData().remove(0);
                intent.putExtra("Role", mRoleListBean);
                intent.putExtra("superAccount",superAccount);
                startActivityForResult(intent, 500);
            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                //删除
                if (mUserBean.getUM_IsAmin() != 1) {
                    mSweetAlertDialog = new SweetAlertDialog(UserDetailActivity.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("删除用户");
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
                } else {
                    CustomToast.makeText(UserDetailActivity.this, "超级管理员无法删除！", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mReSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                Intent intent = new Intent(UserDetailActivity.this, ChangePwdActivity.class);
                intent.putExtra("User", mUserBean);
                intent.putExtra("TYPE", "user");
                startActivity(intent);
            }
        });
    }

    private void update(UserBean bean) {
        if (bean != null) {

            if (bean.getUM_ChatHead() != null && !"".equals((bean.getUM_ChatHead()))) {
                String uri;
                if (bean.getUM_ChatHead().contains("http")) {
                    uri = bean.getUM_ChatHead();
                } else {
                    uri = HttpAPI.API().MAIN_DOMAIN + bean.getUM_ChatHead();
                }
                Glide.with(UserDetailActivity.this).load(Uri.parse(uri)).into(civAddMemberHead);
            }

            tvAccount.setText(bean.getUM_Acount());
            tvName.setText(bean.getUM_Name());
//            tvPaw.setText(bean.getUM_Pwd());
            if (mRoleListBean != null) {
                for (int i = 0; i < mRoleListBean.getData().size(); i++) {
                    if (bean.getRoleID().equals(mRoleListBean.getData().get(i).getGID())) {
                        tvRole.setText(mRoleListBean.getData().get(i).getRM_Name());
                    }
                }
            }
            tvShop.setText(bean.getSM_Name());
            if (bean.getUM_IsLock() == 0) {
                cbIsLockNo.setChecked(true);
                cbIsLockNo.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                cbIsLockNo.setTextColor(Color.parseColor("#FF8739"));
                cbIsLockYse.setChecked(false);
                cbIsLockYse.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                cbIsLockYse.setTextColor(Color.parseColor("#333333"));
            } else {
                cbIsLockYse.setChecked(true);
                cbIsLockYse.setBackgroundResource(R.drawable.ysl_checkbox_shape_selected);
                cbIsLockYse.setTextColor(Color.parseColor("#FF8739"));
                cbIsLockNo.setChecked(false);
                cbIsLockNo.setBackgroundResource(R.drawable.ysl_checkbox_shape_unselectrd);
                cbIsLockNo.setTextColor(Color.parseColor("#333333"));
            }
            tvPhone.setText(bean.getUM_Contact() == null ? "" : bean.getUM_Contact());
            tvRemark.setText(bean.getUM_Remark() == null ? "" : bean.getUM_Remark());
        }
    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showPopupWindow(v);
                showPopWindow(v);
            }
        });

    }

    /**
     * 显示PopMenu
     */
    private void showPopupWindow(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        mPopupMenu.showAsDropDown(UserDetailActivity.this.findViewById(R.id.rl_user_detail_head),
                location[0] - mPopupMenu.getWidth() - 100, 0);
    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            case R.id.tv_menu_pop_first:
                //编辑
                mPopupMenu.dismiss();
                Intent intent = new Intent(UserDetailActivity.this, EditUserActivity.class);
                intent.putExtra("User", mUserBean);
                mRoleListBean.getData().remove(0);
                intent.putExtra("Role", mRoleListBean);
                startActivityForResult(intent, 500);
                break;
            case R.id.tv_menu_pop_second:
                mPopupMenu.dismiss();
                //删除
                if (mUserBean.getUM_IsAmin() != 1) {
                    mSweetAlertDialog = new SweetAlertDialog(UserDetailActivity.this, SweetAlertDialog.WARNING_TYPE);
                    mSweetAlertDialog.setTitleText("删除用户");
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
                } else {
                    CustomToast.makeText(UserDetailActivity.this, "超级管理员无法删除！", Toast.LENGTH_SHORT).show();
                }
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
        params.put("UM_GID", mUserBean.getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                finish();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(UserDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在删除...", false);
        HttpHelper.post(this, "UserManager/DelUsers", params, callBack);
    }

    /**
     * 编辑完成时详情页面刷新
     *
     * @param bean
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(UserBean bean) {
        update(bean);
        mUserBean = bean;
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
}
