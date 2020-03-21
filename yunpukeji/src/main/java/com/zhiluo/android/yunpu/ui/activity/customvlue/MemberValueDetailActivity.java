package com.zhiluo.android.yunpu.ui.activity.customvlue;

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
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.CustomValueBean;
import com.zhiluo.android.yunpu.entity.eventbus.MessageEvent;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyMenuPopWindow;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 会员自定义属性详情
 */
public class MemberValueDetailActivity extends BaseActivity  {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.iv_member_value_detail_menu)
    ImageView ivMenu;
    @Bind(R.id.tv_member_value_detail_type)
    TextView tvType;
    @Bind(R.id.tv_member_value_detail_name)
    TextView tvName;
    @Bind(R.id.tv_member_value_detail_sort)
    TextView tvSort;
    @Bind(R.id.rb_member_value_detail_no_empty)
    RadioButton rbNoEmpty;
    @Bind(R.id.rb_member_value_detail_empty)
    RadioButton rbEmpty;
    @Bind(R.id.rb_member_value_detail_show)
    RadioButton rbShow;
    @Bind(R.id.rb_member_value_detail_hide)
    RadioButton rbHide;
    @Bind(R.id.tv_member_value_detail_data)
    TextView tvData;

    private CustomValueBean mValueBean;
    private SweetAlertDialog mSweetAlertDialog;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_value_detail);
        ButterKnife.bind(this);
        mValueBean = (CustomValueBean) getIntent().getSerializableExtra("Value");
        updateView(mValueBean);
        setListener();
    }

    private void updateView(CustomValueBean bean) {
        if (bean != null) {
            tvType.setText(bean.getCF_FieldType());
            tvName.setText(bean.getCF_FieldName());
            tvSort.setText(bean.getCF_Order() + "");
            if (bean.getCF_Required().equals("是")) {
                rbNoEmpty.setChecked(true);
            } else {
                rbEmpty.setChecked(true);
            }
            if (bean.getCF_IsShowVIP() == 1) {
                rbShow.setChecked(true);
            } else {
                rbHide.setChecked(true);
            }
            tvData.setText(bean.getCF_ItemsValue() == null ? "" : bean.getCF_ItemsValue());
        }
    }

    private void setListener() {
        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Intent intent = new Intent(MemberValueDetailActivity.this, EditMemberValueActivity.class);
                intent.putExtra("Value", mValueBean);
                startActivity(intent);
            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                //删除
                mSweetAlertDialog = new SweetAlertDialog(MemberValueDetailActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("删除会员自定义属性");
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
                        deleteValue();
                        mSweetAlertDialog.dismiss();
                    }
                });
                mSweetAlertDialog.show();

            }
        });
    }




    private void deleteValue() {
        RequestParams params = new RequestParams();
        params.put("CF_GID", mValueBean.getCF_GID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                /**
                 * 删除成功evnetbus刷新列表 20191019 cy
                 */
                MessageEvent messageEvent = new MessageEvent();
                messageEvent.setType("member_value_fragment_refresh");
                EventBus.getDefault().post(messageEvent);
                /************************  20191019 cy */
                finish();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(MemberValueDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在删除...", false);
        HttpHelper.post(this, "CustomFieldsVIP/DeleteFieldsVIP", params, callBack);
    }
}
