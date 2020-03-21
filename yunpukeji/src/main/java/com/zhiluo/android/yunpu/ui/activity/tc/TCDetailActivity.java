package com.zhiluo.android.yunpu.ui.activity.tc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.TCBean;
import com.zhiluo.android.yunpu.entity.TCGoodBean;
import com.zhiluo.android.yunpu.goods.consume.view.ShapedImageView;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.adapter.TCGoodsAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.ui.view.MyListView;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.Decima2KeeplUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class TCDetailActivity extends AppCompatActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.iv_tc_detail_menu)
    ImageView ivMenu;
    @Bind(R.id.rl_tc_detail_head)
    RelativeLayout rlHead;
    @Bind(R.id.tv_tc_detail_code)
    TextView tvCode;
    @Bind(R.id.tv_tc_detail_name)
    TextView tvName;
    @Bind(R.id.iv_upload_tv_picture)
    ShapedImageView ivPicture;
    @Bind(R.id.tv_tc_detail_price)
    TextView tvPrice;
    @Bind(R.id.tv_tc_detail_model)
    TextView tvModel;
    @Bind(R.id.tv_tc_detail_dis)
    TextView tvDis;
    @Bind(R.id.tv_tc_detail_integral)
    TextView tvIntegral;
    @Bind(R.id.tv_tc_detail_remark)
    TextView tvRemark;
    @Bind(R.id.lv_tc_detail)
    MyListView lvTc;
    @Bind(R.id.tv_tc_detail_type)
    TextView tvTcDetailType;

    private TCBean mTcBean;
    private ArrayList<TCGoodBean> mTcGoodBeans;
    private TCGoodsAdapter mAdapter;
    private SweetAlertDialog mSweetAlertDialog;
    private PopupWindow mPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(TCDetailActivity.this);
        setContentView(R.layout.activity_tcdetail);
        ButterKnife.bind(this);
        mTcBean = (TCBean) getIntent().getSerializableExtra("TC");
        init();
        update(mTcBean);
        setListener();
    }

    private void init() {
        mPopupWindow = new PopupWindow(this);
    }

    private void update(TCBean bean) {
        if (bean != null) {
            //设置图片
            if (bean.getPC_Image() != null) {
                if (bean.getPC_Image().contains("http")) {
                    Glide.with(this).load(bean.getPC_Image()).error(R.drawable.ysl_goods).into(ivPicture);
                } else {
                    String url = bean.getPC_Image();
                    if (url.contains("../")) {
                        String[] s = url.split("\\.\\./");
                        url = s[2];
                    }
                    Glide.with(this).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).
                            into(ivPicture);
                }
            } else {
                ivPicture.setImageResource(R.drawable.ysl_goods);
            }
            tvCode.setText(bean.getPC_Code());
            tvName.setText(bean.getPC_Name());
            tvPrice.setText(Decima2KeeplUtil.stringToDecimal("" + bean.getPC_Price()));
            tvModel.setText(bean.getPC_specification() == null ? "" : bean.getPC_specification());
            tvDis.setText(Decima2KeeplUtil.stringToDecimal("" + bean.getPC_Discount()));
            tvIntegral.setText("" + bean.getPC_Integral());
            
 tvTcDetailType.setText(bean.getPC_Type() ==0?"普通套餐":"充次套餐");            tvRemark.setText(bean.getPC_Remark() == null ? "" : bean.getPC_Remark());
            if (bean.getPC_ProductJson() != null) {
                Type type = new TypeToken<ArrayList<TCGoodBean>>() {
                }.getType();
                Gson gson = new Gson();
                mTcGoodBeans = CommonFun.JsonToObj(bean.getPC_ProductJson(), type);
                mAdapter = new TCGoodsAdapter(TCDetailActivity.this, mTcGoodBeans);
                lvTc.setAdapter(mAdapter);
            }
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
                showPopWindow(v);
            }
        });
    }

    private void showPopWindow(View archon) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_ysl_edit_vip_level, null);
        RelativeLayout mEdit = view.findViewById(R.id.r_edit);
        mEdit.setVisibility(View.GONE);
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
                Intent intent = new Intent(TCDetailActivity.this, EditTCActivity.class);
                intent.putExtra("TC", mTcBean);
                startActivity(intent);
            }
        });
        mDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
                //删除
                mSweetAlertDialog = new SweetAlertDialog(TCDetailActivity.this, SweetAlertDialog.WARNING_TYPE);
                mSweetAlertDialog.setTitleText("删除套餐");
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
     * 删除套餐
     */
    private void delete() {
        RequestParams params = new RequestParams();
        params.put("GIDList[]", mTcBean.getGID());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                ActivityManager.getInstance().exit();
                Intent intent = new Intent(TCDetailActivity.this, TCManagerActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(TCDetailActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在删除...", false);
        HttpHelper.post(this, "ProductCombo/DeleteProductCombo", params, callBack);
    }
}
