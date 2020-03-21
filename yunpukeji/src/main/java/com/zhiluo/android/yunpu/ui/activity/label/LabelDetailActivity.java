package com.zhiluo.android.yunpu.ui.activity.label;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.MemberLabel;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.activity.user.EditUserActivity;
import com.zhiluo.android.yunpu.ui.activity.user.UserDetailActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 标签详情
 */
public class LabelDetailActivity extends BaseActivity {
    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.iv_user_detail_menu)
    ImageView ivMenu;
    @Bind(R.id.rl_label_detail_head)
    RelativeLayout rlHead;
    @Bind(R.id.tv_label_detail_name)
    TextView tvName;
    //    @Bind(R.id.tv_label_detail_color)
//    TextView tvColor;
//    @Bind(R.id.rb_label_detail_member)
//    RadioButton rbMember;
//    @Bind(R.id.rb_label_detail_fans)
//    RadioButton rbFans;
    @Bind(R.id.tv_label_detail_remark)
    TextView tvRemark;

    private MemberLabel.DataBean mLabelBean;
    private MyMenuPopWindow mPopupMenu;
    private SweetAlertDialog mSweetAlertDialog;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_detail);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
       // mPopupMenu = new MyMenuPopWindow(this, "编辑", "删除", null);
        mLabelBean = (MemberLabel.DataBean) getIntent().getSerializableExtra("Label");
        update(mLabelBean);
        setListener();
    }

    private void update(MemberLabel.DataBean bean) {
        if (bean != null) {
            tvName.setText(bean.getML_Name());
//            tvColor.setBackgroundColor(Color.parseColor(bean.getML_ColorValue()));
//            if (bean.getML_Type() == 0) {
//                rbMember.setChecked(true);
//            }
//            if (bean.getML_Type() == 1) {
//                rbFans.setChecked(true);
//            }
            tvRemark.setText(bean.getML_Remark() == null ? "" : bean.getML_Remark());
        }
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
                //编辑
                mPopupWindow.dismiss();
                //编辑
                //mPopupMenu.dismiss();
                Intent intent = new Intent(LabelDetailActivity.this, EditLabelActivity.class);
                intent.putExtra("Label", mLabelBean);
                startActivity(intent);
            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                //删除
                mSweetAlertDialog = new SweetAlertDialog(LabelDetailActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("删除标签");
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
                        deleteLabel();
                        mSweetAlertDialog.dismiss();
                    }
                });
                mSweetAlertDialog.show();

            }
        });
    }

    /**
     * 显示PopMenu
     */
    private void showPopupWindow(View view) {
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        mPopupMenu.showAsDropDown(LabelDetailActivity.this.findViewById(R.id.rl_label_detail_head),
                location[0] - mPopupMenu.getWidth() - 100, 0);
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
//                showPopupWindow(v);
                showPopWindow(v);
            }
        });
    }


    private void deleteLabel() {
        RequestParams params = new RequestParams();
        params.put("GID", mLabelBean.getML_GID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ActivityManager.getInstance().exit();
                Intent intent = new Intent(LabelDetailActivity.this,LabelManagerActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(LabelDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在删除...", false);
        HttpHelper.post(this, "MemberLabel/DelMemberLabel", params, callBack);
    }
}
