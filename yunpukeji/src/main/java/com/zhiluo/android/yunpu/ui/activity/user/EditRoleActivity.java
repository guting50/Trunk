package com.zhiluo.android.yunpu.ui.activity.user;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.AppMenuListBean;
import com.zhiluo.android.yunpu.entity.ListMenuBean;
import com.zhiluo.android.yunpu.entity.NoticeEvent;
import com.zhiluo.android.yunpu.entity.RoleBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.adapter.RoleMenuAdapter;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CommonFun;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * 编辑角色
 */
public class EditRoleActivity extends BaseActivity {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_edit_role_save)
    TextView tvSave;
    @Bind(R.id.et_edit_role_name)
    EditText etName;
    @Bind(R.id.et_edit_role_remark)
    EditText etRemark;
    @Bind(R.id.rv_role_menu)
    RecyclerView mRecyclerView;

    private AppMenuListBean mAppMenuListBean;//App菜单权限
    private ListMenuBean mListMenu;
    private String MM_GID;
    private List<String> MM_LIST = new ArrayList<>();
    private RoleMenuAdapter mMenuAdapter;
    private RoleBean mRoleBean;
    private SweetAlertDialog mSweetAlertDialog;
    private LoginUpbean mLoginBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_role);
        ButterKnife.bind(this);
        mRoleBean = (RoleBean) getIntent().getSerializableExtra("Role");
        getData();
        setListener();
    }

    private void getData() {
        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");

        MyApplication.isDingXiang = false;
        if (mLoginBean !=null){
            try {
                if (mLoginBean.getData().getShopList().get(0).getSM_FunctionList() != null && !mLoginBean.getData().getShopList().get(0).getSM_FunctionList().equals("")) {

                    JSONArray jsonArray = new JSONArray(mLoginBean.getData().getShopList().get(0).getSM_FunctionList());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (jsonObject.get("code").equals("1D0")) {
                            Integer  s = (Integer) jsonObject.get("value");
                            if (s == 1)
                                MyApplication.isDingXiang = true;
                        }
                    }
                } else {
                    MyApplication.isDingXiang = false;
                }
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        getListMenu();
        if (mRoleBean != null) {
            getRoleMenu(mRoleBean.getGID());
            etName.setText(mRoleBean.getRM_Name());
            etRemark.setText(mRoleBean.getRM_Remark());
        }
    }

    private void setListener() {
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify()) {
                    save();
                }
            }
        });
    }

    private boolean verify() {
        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(EditRoleActivity.this, "角色名称不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void save() {
        RequestParams params = new RequestParams();
        params.put("GID", mRoleBean.getGID());
        params.put("RM_Name", etName.getText().toString());
        params.put("RM_Remark", TextUtils.isEmpty(etRemark.getText()) ? "" : etRemark.getText().toString());
//        params.put("RS_Remark", TextUtils.isEmpty(etRemark.getText()) ? "" : etRemark.getText().toString());
        List<String> STR = new ArrayList<>();
        for (int i = 0; i < mAppMenuListBean.getData().size(); i++) {
            if (mAppMenuListBean.getData().get(i).getAR_IsCheck() != null) {
                if ( mAppMenuListBean.getData().get(i).getAR_IsCheck().equals("1")) {
                    STR.add(mAppMenuListBean.getData().get(i).getGID());
                }
            }

        }
        STR.add("0000");
        params.put("AM_GIDList[]", STR);
//        params.put("AM_GIDList[]", "0000");
        params.put("MM_GIDList[]",MM_LIST);
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                EventBus.getDefault().post(new NoticeEvent());
                mSweetAlertDialog = new SweetAlertDialog(EditRoleActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("编辑成功！")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                                finish();
                            }
                        })
                        .setConfirmText("确认")
                        .show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditRoleActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, "Role/EditRole", params, callBack);
    }

    /**
     * 获取APP权限菜单
     *
     * @param gid
     */
    private void getRoleMenu(String gid) {
        RequestParams params = new RequestParams();
        params.put("RM_GID", gid);
        HttpHelper.post(this, "APPMenuRoleRelation/QueryAppMenuList", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mAppMenuListBean = CommonFun.JsonToObj(responseString, AppMenuListBean.class);
                for (int i = 0; i < mAppMenuListBean.getData().size(); i++) {
                    if (mAppMenuListBean.getData().get(i).getAM_Name().equals("公共资源")) {
                        mAppMenuListBean.getData().remove(i);

                    }
                    if (mAppMenuListBean.getData().get(i).getAM_Name().equals("定项消费") && !MyApplication.isDingXiang) {
                        mAppMenuListBean.getData().remove(i);
                    }
                }
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,
                        StaggeredGridLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(layoutManager);
                mMenuAdapter = new RoleMenuAdapter(mAppMenuListBean);
                mRecyclerView.setAdapter(mMenuAdapter);
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditRoleActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
    /**
     * 获取网页权限菜单
     *
     * @param
     */
    private void getListMenu() {
        RequestParams params = new RequestParams();
        HttpHelper.post(this, "MenuManager/ListMenu", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mListMenu = CommonFun.JsonToObj(responseString, ListMenuBean.class);
                for (int i = 0; i < mListMenu.getData().size(); i++) {
                    if (mListMenu.getData().get(i).getMM_Name().equals("公用资源")) {
                        MM_GID = mListMenu.getData().get(i).getGID();
                    }
                    MM_LIST.add(mListMenu.getData().get(i).getGID());
                }

            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditRoleActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
