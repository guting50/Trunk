package com.zhiluo.android.yunpu.ui.activity.label;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.NoticeEvent;
import com.zhiluo.android.yunpu.http.CallBack;

import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.member.manager.bean.MemberLabel;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.adapter.LabelListAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.yslutils.YSLUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class LabelManagerActivity extends BaseActivity implements PopupWindow.OnDismissListener {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.lv_label_manager)
    ListView mListView;
    @Bind(R.id.refresh_label_manager)
    WaveSwipeRefreshLayout mRefresh;
    @Bind(R.id.fab_label)
    FloatingActionButton mFab;

    private MemberLabel mMemberLabel;
    private LabelListAdapter mLabelListAdapter;
    private PopupWindow mPopWindow;
    private EditText etRmake;
    private EditText etName;
    private SweetAlertDialog mSweetAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label_manager);
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        mPopWindow = new PopupWindow(this);
        EventBus.getDefault().register(this);
        getLabel();
        setListener();
    }

    /**
     * 新增部门弹窗
     */
    private void showAddPartPopWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_add_lable, null, false);
        etRmake = view.findViewById(R.id.ed_edit_child_type);
        mPopWindow.setContentView(view);
        TextView save = view.findViewById(R.id.tv_save);
        TextView delete = view.findViewById(R.id.tv_delete);
        etName = view.findViewById(R.id.te_choise_parent_type);
        mPopWindow.setBackgroundDrawable(new ColorDrawable());
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopWindow.setWidth(YSLUtils.getPhoneWidth() * 7 / 8);
        mPopWindow.setAnimationStyle(R.style.pop_show_style);
        mPopWindow.showAtLocation(this.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YSLUtils.setBackgroundAlpha(1.0f, LabelManagerActivity.this);
                mPopWindow.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etName.getText()) || etName.getText().toString().equals("")) {
                    CustomToast.makeText(LabelManagerActivity.this, "标签名称不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    addLabel();
                    YSLUtils.setBackgroundAlpha(1.0f, LabelManagerActivity.this);
                    mPopWindow.dismiss();
                }
            }
        });
    }

    private void setListener() {
        mPopWindow.setOnDismissListener(this);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(LabelManagerActivity.this, AddLabelActivity.class);
                //startActivity(intent);
                YSLUtils.setBackgroundAlpha(0.5f,LabelManagerActivity.this);
                showAddPartPopWindow();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LabelManagerActivity.this, LabelDetailActivity.class);
                intent.putExtra("Label", mMemberLabel.getData().get(position));
                startActivity(intent);
            }
        });
        mRefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getLabel();
            }

            @Override
            public void onLoad() {

            }

            @Override
            public boolean canLoadMore() {
                return false;
            }

            @Override
            public boolean canRefresh() {
                return true;
            }
        });
    }

    /**
     * 获取会员标签
     */
    private void getLabel() {
        AsyncHttpClient mClient = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        mClient.setCookieStore(cookieStore);


        RequestParams params = new RequestParams();
        params.put("ML_Name", "");
        mClient.post(HttpAPI.API().MEMBER_LABEL, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mMemberLabel = CommonFun.JsonToObj(responseString,MemberLabel.class);
//                mMemberLabel = result.getData();
                mLabelListAdapter = new LabelListAdapter(LabelManagerActivity.this, mMemberLabel);
                mListView.setAdapter(mLabelListAdapter);
                mRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(LabelManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
                mRefresh.setRefreshing(false);
            }
        });

    }


    private void addLabel() {
        RequestParams params = new RequestParams();
        params.put("GID", "");
        params.put("ML_Name", etName.getText().toString());
        params.put("ML_ColorValue", "#FF8739");
        params.put("ML_Remark", TextUtils.isEmpty(etRmake.getText()) ? "" : etRmake.getText().toString());
        params.put("ML_Type", 0);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mSweetAlertDialog = new SweetAlertDialog(LabelManagerActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("新增成功！")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                                getLabel();
                            }
                        })
                        .setConfirmText("确认")
                        .show();
                EventBus.getDefault().post(new NoticeEvent());
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(LabelManagerActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在提交...", false);
        HttpHelper.post(this, "MemberLabel/AddMemberLabel", params, callBack);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void update(NoticeEvent event) {
        getLabel();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getLabel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDismiss() {
        YSLUtils.setBackgroundAlpha(1.0f, LabelManagerActivity.this);
    }
}
