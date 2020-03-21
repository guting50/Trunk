package com.zhiluo.android.yunpu.ui.activity.user;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liangmutian.mypicker.DataPickerDialog;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.EditUserBean;
import com.zhiluo.android.yunpu.entity.RoleListBean;
import com.zhiluo.android.yunpu.entity.ShopListBean;
import com.zhiluo.android.yunpu.entity.StaffListBean;
import com.zhiluo.android.yunpu.entity.UserBean;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.login.jsonbean.LoginUpbean;
import com.zhiluo.android.yunpu.member.jsonbean.MemberPhotoBean;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.CustomPopWindow;
import com.zhiluo.android.yunpu.ui.view.CustomToast;
import com.zhiluo.android.yunpu.utils.ActivityManager;
import com.zhiluo.android.yunpu.utils.Base64Encoder;
import com.zhiluo.android.yunpu.utils.CacheData;
import com.zhiluo.android.yunpu.utils.CheckPromiss;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.GetImagePath;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

/**
 * 编辑用户
 */
public class EditUserActivity extends BaseActivity implements CustomPopWindow.OnItemClickListener {

    @Bind(R.id.tv_back_activity)
    TextView tvBack;
    @Bind(R.id.tv_edit_user_save)
    TextView tvSave;
    @Bind(R.id.et_add_user_account)
    EditText etAccount;
    @Bind(R.id.et_edit_user_name)
    EditText etName;
    //    @Bind(R.id.et_edit_user_paw)
//    EditText etPaw;
    @Bind(R.id.et_edit_user_phone)
    EditText etPhone;
    @Bind(R.id.et_edit_user_remark)
    EditText etRemark;
    @Bind(R.id.tv_role)
    TextView tvRole;
    @Bind(R.id.tv_shop)
    TextView tvShop;
    @Bind(R.id.civ_add_member_head)
    CircleImageView civAddMemberHead;
    @Bind(R.id.et_add_user_num)
    EditText etAddUserNum;
    @Bind(R.id.tv_issuoding)
    TextView tvIssuoding;

    private RoleListBean mRoleListBean;//角色列表
    private ArrayList<String> mRoleNameList;//角色名称
    private ArrayList<String> mIsLockList = new ArrayList<>();
    private ArrayAdapter<String> mRoleAdapter;//角色Adapter
    private ShopListBean mShopListBean;
    private ArrayList<String> mShopNameList;//店铺名称
    private ArrayAdapter<String> mShopAdapter;//店铺Adapter
    private LoginUpbean mLoginBean;
    private StaffListBean mStaffListBean;//员工
    private ArrayList<String> mStaffNameList;//员工名称
    private ArrayAdapter<String> mStaffNameAdapter;//员工Adapter
    private String mRoleGid, mShopGid, mRoleName, mShopName;
    private SweetAlertDialog mSweetAlertDialog;
    private UserBean mUserBean;
    private Dialog chooseDialog;
    private int islock = 0;


    /**
     * 会员头像相关
     */
    private CustomPopWindow mCustomPopWindow;//自定义PopWindow声明
    private static String IMAGE_FILE_NAME = "vip_photo.png";
    private static String CROP_IMAGE_FILE_NAME = "vip_photo_temp.png";
    private static String IMAGE_GALLERY_NAME = "image/*";
    File mCameraFile = new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME);//照相机的File对象
    File mCropFile = new File(Environment.getExternalStorageDirectory(), CROP_IMAGE_FILE_NAME);//裁剪后的File对象
    File mGalleryFile = new File(Environment.getExternalStorageDirectory(), IMAGE_GALLERY_NAME);//相册的File对象
    private static final int RESULT_REQUEST_CODE = 1001;//剪裁结果处理
    private static final int IMAGE_REQUEST_CODE = 1002;//从相册选择照片结果处理（版本小于7.0）
    private static final int SELECT_PIC_KITKAT = 1003;//从相册选择照片结果处理（版本大于等于7.0）
    private static final int CAMERA_REQUEST_CODE = 1004;//拍照结果处理
    private MemberPhotoBean memberPhotoBean;//上传会员头像响应结果
    private Uri mUri;
    private String mMemberPhotoAddress = "/img/nohead.png";//会员头像地址
    private String superAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        ButterKnife.bind(this);
        etAddUserNum.requestFocus();
        init();
        getEmpl();
        setListener();
        updateView(mUserBean);
    }

    private void init() {
        Intent intent = getIntent();
        if (intent != null) {
            superAccount = intent.getStringExtra("superAccount");
//            etAccount.setText(superAccount+"_");
//            etAccount.setEnabled(false);
        }

        mLoginBean = (LoginUpbean) CacheData.restoreObject("login");
        mCustomPopWindow = new CustomPopWindow(EditUserActivity.this);
        mCustomPopWindow.setOnItemClickListener(this);
        mUserBean = (UserBean) getIntent().getSerializableExtra("User");

        mRoleListBean = (RoleListBean) getIntent().getSerializableExtra("Role");
        mRoleNameList = new ArrayList<>();
        mRoleNameList.add("请选择角色");
        if (mRoleListBean != null) {
            mRoleListBean.getData().remove(0);
            for (int i = 0; i < mRoleListBean.getData().size(); i++) {
                mRoleNameList.add(mRoleListBean.getData().get(i).getRM_Name());
            }
        }
        mShopListBean = (ShopListBean) CacheData.restoreObject("shop");
        mShopNameList = new ArrayList<>();
        mShopNameList.add("请选择");
        if (mShopListBean == null) {
            getShopList();
        } else {
            addShopList();
        }
        if (mUserBean != null) {
            if (mUserBean.getUM_ChatHead() != null && !"".equals((mUserBean.getUM_ChatHead()))) {
                String uri;
                if (mUserBean.getUM_ChatHead().contains("http")) {
                    uri = mUserBean.getUM_ChatHead();
                } else {
                    uri = HttpAPI.API().MAIN_DOMAIN + mUserBean.getUM_ChatHead();
                }
                Glide.with(EditUserActivity.this).load(Uri.parse(uri)).into(civAddMemberHead);
            }
        }
        mIsLockList.add("否");
        mIsLockList.add("是");
        tvIssuoding.setText("否");
    }

    private void addShopList() {
        for (int i = 0; i < mShopListBean.getData().size(); i++) {
            mShopNameList.add(mShopListBean.getData().get(i).getSM_Name());
        }

        if (mLoginBean != null) {
            for (int i = 0; i < mShopListBean.getData().size(); i++) {
                if (mLoginBean.getData().getShopID().equals(mShopListBean.getData().get(i).getGID())) {
                    mShopGid = mShopListBean.getData().get(i).getGID();
                }
            }
        }
    }

//    /**
//     * @param mlist     ,
//     * @param mTextView ,
//     *                  选择关联员工
//     */
//    private void showChooseStaffDialog(List<String> mlist, final TextView mTextView) {
//        int j = 0;
//        for (int i = 0; i < mlist.size(); i++) {
//            if (mTextView.getText().toString().equals(mlist.get(i))) {
//                j = i;
//            }
//        }
//        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
//        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
//                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
//                    @Override
//                    public void onDataSelected(String itemValue, int position) {
//                        mTextView.setText(itemValue);
//                        if (position == 0) {
//                            etName.setInputType(InputType.TYPE_CLASS_TEXT);
//                            etName.setBackgroundColor(getResources().getColor(R.color.white));
//                            mStaffGid = "";
//                        } else {
//                            mStaffGid = mStaffListBean.getData().get(position - 1).getGID();
//                            etName.setInputType(InputType.TYPE_NULL);
//                            etName.setBackgroundColor(getResources().getColor(R.color.background));
//                            etName.setText(itemValue);
//                        }
//                    }
//
//                    @Override
//                    public void onCancel() {
//
//                    }
//                }).create();
//
//        chooseDialog.show();
//    }

    /**
     * @param mlist     ,
     * @param mTextView ,
     *                  选择店铺
     */
    private void showChooseShopDialog(List<String> mlist, final TextView mTextView) {
        int j = 1;
        for (int i = 0; i < mShopListBean.getData().size(); i++) {//设置默认值
            if (tvShop.getText().toString().equals(mShopListBean.getData().get(i).getSM_Name())) {
                j = i + 1;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        if (position != 0) {
                            mShopGid = mShopListBean.getData().get(position - 1).getGID();
                            mShopName = mShopListBean.getData().get(position - 1).getSM_Name();
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * @param mlist     ,
     * @param mTextView ,
     *                  选择角色
     */
    private void showChooseRoleDialog(List<String> mlist, final TextView mTextView) {
        int j = 1;
        for (int i = 0; i < mRoleListBean.getData().size(); i++) {//设置默认值
            if (tvRole.getText().toString().equals(mRoleListBean.getData().get(i).getRM_Name())) {
                j = i + 1;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        if (position != 0) {
                            mRoleGid = mRoleListBean.getData().get(position - 1).getGID();
                            mRoleName = mRoleListBean.getData().get(position - 1).getRM_Name();
                        }
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    private void updateView(UserBean bean) {
        if (bean != null) {
            if (bean.getUM_IsAmin() == 0) {
                etAddUserNum.setText(bean.getUM_Acount().substring(bean.getUM_Acount().length() - 3, bean.getUM_Acount().length()));
                etAddUserNum.setSelection(etAddUserNum.getText().toString().length());
            }

            String flagStr = mUserBean.getUM_Acount();
            if (mUserBean.getUM_Acount().length() > 10) {
                etAccount.setText(flagStr.substring(0, flagStr.length() - 3));
            } else {
                etAccount.setText(flagStr);
            }
            etName.setText(bean.getUM_Name());
            tvShop.setText(bean.getSM_Name());
            tvRole.setText(bean.getRM_Name());
            mRoleGid = bean.getRoleID() + "";
            mShopGid = bean.getShopID() + "";
            mRoleName = bean.getRM_Name() + "";
            mShopName = bean.getSM_Name() + "";
//            mStaffGid = String.valueOf(bean.getEM_GID() + "");
//            etPaw.setText(bean.getUM_Pwd());
            if (mRoleListBean != null) {
                for (int i = 0; i < mRoleListBean.getData().size(); i++) {
                    if (bean.getRoleID().equals(mRoleListBean.getData().get(i).getGID())) {
                    }
                }
            }
            islock = bean.getUM_IsLock();
            if (bean.getUM_IsLock() == 0) {
                tvIssuoding.setText("否");

            } else {
                tvIssuoding.setText("是");
            }
            etPhone.setText(bean.getUM_Contact() == null ? "" : bean.getUM_Contact());
            etRemark.setText(bean.getUM_Remark() == null ? "" : bean.getUM_Remark());
            //超管
            if (bean.getUM_IsAmin() == 1) {
                etAddUserNum.setInputType(InputType.TYPE_NULL);
                etAccount.setEnabled(false);
                etAddUserNum.setBackgroundColor(getResources().getColor(R.color.background));

                tvShop.setEnabled(false);
                tvRole.setEnabled(false);
                tvIssuoding.setEnabled(false);

//                etRemark.setInputType(InputType.TYPE_NULL);
//                etRemark.setBackgroundColor(getResources().getColor(R.color.background));
            }
        }
    }

    /**
     * @param mlist     ,
     * @param mTextView ,
     *                  是否锁定
     */
    private void showIsLockDialog(List<String> mlist, final TextView mTextView) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        islock = position;
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    private void setListener() {

        //会员头像点击监听
        civAddMemberHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCustomPopWindow.showAtLocation(EditUserActivity.this.findViewById(R.id.activity_edit_user),
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
        });
//        tvStaff.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showChooseStaffDialog(mStaffNameList, tvStaff);
//            }
//        });

        tvIssuoding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showIsLockDialog(mIsLockList, tvIssuoding);
            }
        });

        tvShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mShopNameList.size() < 1) {
                    CustomToast.makeText(EditUserActivity.this, "，店铺未添加，暂无可选择店铺", Toast.LENGTH_SHORT).show();
                    return;
                    //mShopGid = "";
                    //mShopName = "";
                } else {
                    if (mLoginBean.getData() != null && mLoginBean.getData().getUM_IsAmin() == 1) {
                        showChooseShopDialog(mShopNameList, tvShop);
                    } else {
                        CustomToast.makeText(EditUserActivity.this, "子账户无法选择店铺", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tvRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRoleNameList.size() < 1) {
                    CustomToast.makeText(EditUserActivity.this, "角色未添加，暂无可选择角色", Toast.LENGTH_SHORT).show();
                    return;
                    // mRoleGid ="";
                    //mRoleName ="";
                } else {
                    showChooseRoleDialog(mRoleNameList, tvRole);
                }
            }
        });

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
        if (TextUtils.isEmpty(etAccount.getText())) {
            CustomToast.makeText(this, "账户不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mUserBean.getUM_IsAmin() != 1) {
//            if (!etAccount.getText().toString().equals(superAccount + "_")) {
//                CustomToast.makeText(this, "子账号由超管账号+编号", Toast.LENGTH_SHORT).show();
//                return false;
//            }

            if (etAddUserNum.getText().toString().isEmpty() || etAddUserNum.getText().toString().length() < 3) {
                CustomToast.makeText(EditUserActivity.this, "编号只能是三位数字", Toast.LENGTH_SHORT).show();
                return false;
            }
        }


        if (TextUtils.isEmpty(etName.getText())) {
            CustomToast.makeText(this, "用户姓名不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(tvRole.getText())) {
            CustomToast.makeText(this, "用户角色不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (TextUtils.isEmpty(tvShop.getText())) {
            CustomToast.makeText(this, "所属店铺不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (TextUtils.isEmpty(etPaw.getText())) {
//            CustomToast.makeText(this, "初始密码不能为空！", Toast.LENGTH_SHORT).show();
//            return false;
//        } else {
//            if (etPaw.getText().toString().length() < 4) {
//                CustomToast.makeText(this, "密码长度不能小于4！", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        }
//        if (!TextUtils.isEmpty(etPhone.getText())) {
//            if (!RegexUtil.isTelPhoneNumber(etPhone.getText().toString())) {
//                CustomToast.makeText(this, "手机号码格式不正确！", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//        }
        return true;
    }

    private void save() {
        RequestParams params = new RequestParams();
        params.put("UM_IsAmin", mUserBean.getUM_IsAmin());
        params.put("GID", mUserBean.getGID());
//        params.put("UM_Acount", superAccount);
        params.put("UM_Acount", etAccount.getText().toString());
        params.put("UM_Number", etAddUserNum.getText().toString());
        params.put("RM_GID", mRoleGid);
        params.put("SM_GID", mShopGid);
        params.put("RM_Name", mRoleName);
        params.put("SM_Name", mShopName);
        params.put("UM_Pwd", "");
        params.put("UM_Name", etName.getText().toString());
//        params.put("EM_GID", mStaffGid);
        params.put("UM_Contact", TextUtils.isEmpty(etPhone.getText()) ? "" : etPhone.getText().toString());
        params.put("UM_IsLock", islock);
        params.put("UM_Remark", TextUtils.isEmpty(etRemark.getText()) ? "" : etRemark.getText().toString());

        Li("---------------------random:" + params.toString());
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                Li("-------------onSuccess--------random:" + responseString);
                final EditUserBean bean = CommonFun.JsonToObj(responseString, EditUserBean.class);
                mSweetAlertDialog = new SweetAlertDialog(EditUserActivity.this, SweetAlertDialog.SUCCESS_TYPE);
                mSweetAlertDialog.setTitleText("编辑成功！")
                        .setConfirmText("确认")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                mSweetAlertDialog.dismiss();
                                EventBus.getDefault().post(bean.getData());
                                ActivityManager.getInstance().exit();
                                Intent intent = new Intent(EditUserActivity.this, UserManagerActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).show();
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditUserActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "提交中...", false);
        HttpHelper.post(this, "UserManager/EditUsers", params, callBack);
    }


    /**
     * 获取店铺列表
     */
    private void getShopList() {
        HttpHelper.post(this, "Shops/GetShopsList", new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mShopListBean = CommonFun.JsonToObj(responseString, ShopListBean.class);
                CacheData.saveObject("shop", mShopListBean);
                addShopList();
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    /**
     * 获取员工列表
     */
    private void getEmpl() {
        RequestParams params = new RequestParams();
        params.put("DM_GID", "");//部门GID
        HttpHelper.post(this, "Empl/QueryEmpl", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                mStaffListBean = CommonFun.JsonToObj(responseString, StaffListBean.class);
                mStaffNameList = new ArrayList<>();
                mStaffNameList.add("不关联员工");
                for (int i = 0; i < mStaffListBean.getData().size(); i++) {
                    mStaffNameList.add(mStaffListBean.getData().get(i).getEM_Name());
                }
            }

            @Override
            public void onFailure(String msg) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case IMAGE_REQUEST_CODE://版本<7.0  图库后返回
                if (data != null) {
                    startPhotoZoom(data.getData());
//                    civHeadImg.setImageURI(data.getData());
                }
                break;
            case SELECT_PIC_KITKAT://版本>= 7.0
                if (data != null && GetImagePath.getPath(getApplicationContext(), data.getData()) != null) {
                    File imgUri = new File(GetImagePath.getPath(getApplicationContext(), data.getData()));
                    Uri dataUri = FileProvider.getUriForFile(EditUserActivity.this, MyApplication.FILE_ADDRESS, imgUri);
                    startPhotoZoom(dataUri);
//                    civHeadImg.setImageURI(dataUri);
                }
                break;
            case CAMERA_REQUEST_CODE: //照相后返回

                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri inputUri = FileProvider.getUriForFile(EditUserActivity.this, MyApplication.FILE_ADDRESS, mCameraFile);//通过FileProvider创建一个content类型的Uri
                        mUri = inputUri;
                        startPhotoZoom(inputUri);//设置输入类型
                    } else {
                        Uri inputUri = Uri.fromFile(mCameraFile);
                        mUri = inputUri;
                        startPhotoZoom(inputUri);
                    }
                }
                break;
            case RESULT_REQUEST_CODE://剪裁结果用ImageView显示

                if (resultCode == RESULT_OK) {
                    Uri inputUri = FileProvider.getUriForFile(EditUserActivity.this,
                            MyApplication.FILE_ADDRESS, mCropFile);//通过FileProvider创建一个content类型的Uri
                    mUri = inputUri;
                    postUploadMemberPhoto();
//                    civHeadImg.setImageURI(mUri);
                }
                break;
        }
        if (requestCode == 10086 && resultCode == 10086) {
            Uri imageUri = Uri.parse(data.getStringExtra("memberPhoto"));
            mMemberPhotoAddress = data.getStringExtra("result");
            mUri = imageUri;
            civAddMemberHead.setImageURI(imageUri);
        }
    }

    @Override
    public void setOnItemClick(View v) {
        switch (v.getId()) {
            //  拍照
            case R.id.tv_popwindow_photo:
                jump2Caramel();
                mCustomPopWindow.dismiss();
                break;
            //从手机相册选择
            case R.id.tv_popwindow_select:
                jump2Album();
                mCustomPopWindow.dismiss();
                break;
            //退出
            case R.id.tv_popwindow_cancel:
                mCustomPopWindow.dismiss();
                break;
        }
    }

    /**
     * 拍照
     */
    private void jump2Caramel() {

        if (hasCamera()) {
            if (CheckPromiss.checkPermission(EditUserActivity.this)) {
                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0及以上
                    Uri uriForFile = FileProvider.getUriForFile(EditUserActivity.this,
                            MyApplication.FILE_ADDRESS, mCameraFile);
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intentFromCapture.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                    intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile));
                }
                startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);

            } else {
                warnDialog("未获取到相机权限，请确认后尝试！");
            }


        } else {
            CustomToast.makeText(EditUserActivity.this, "未获取到相机，请确认后再尝试！", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    /**
     * 警告弹窗
     *
     * @param msg
     */
    private void warnDialog(String msg) {
        mSweetAlertDialog = new SweetAlertDialog(EditUserActivity.this, SweetAlertDialog.WARNING_TYPE);
        mSweetAlertDialog.setTitleText("提示");
        mSweetAlertDialog.setContentText(msg);
        mSweetAlertDialog.setCancelable(false);
        mSweetAlertDialog.setConfirmText("确定");
        mSweetAlertDialog.show();
    }

    public static boolean hasCamera() {
        return hasBackFacingCamera() || hasFrontFacingCamera();
    }


    /**
     * 检查设备是否有后置摄像头
     *
     * @return true, 有后置摄像头；false,后置摄像头
     */
    public static boolean hasBackFacingCamera() {
        final int CAMERA_FACING_BACK = 0;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }


    /**
     * 检查设备是否有前置摄像头
     *
     * @return true, 有前置摄像头；false,前置摄像头
     */
    public static boolean hasFrontFacingCamera() {
        final int CAMERA_FACING_BACK = 1;
        return checkCameraFacing(CAMERA_FACING_BACK);
    }

    private static boolean checkCameraFacing(final int facing) {
//        if (getSdkVersion() < Build.VERSION_CODES.GINGERBREAD) {
//            return false;
//        }
        final int cameraCount = Camera.getNumberOfCameras();
        Camera.CameraInfo info = new Camera.CameraInfo();
        for (int i = 0; i < cameraCount; i++) {
            Camera.getCameraInfo(i, info);
            if (facing == info.facing) {
                return true;
            }
        }
        return false;
    }


    /**
     * 从相册中选择
     */
    private void jump2Album() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        intent.setType("image/*");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果大于等于7.0使用FileProvider
            Uri uriForFile = FileProvider.getUriForFile(EditUserActivity.this, MyApplication.FILE_ADDRESS, mGalleryFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(intent, SELECT_PIC_KITKAT);
        } else {
            intent.setType("image/*");
            startActivityForResult(intent, IMAGE_REQUEST_CODE);
        }
    }

    /**
     * 剪裁图片
     *
     * @param inputUri
     */
    public void startPhotoZoom(Uri inputUri) {
        if (inputUri == null) {
            Log.i("TAG", "The uri is not exist.");
            return;
        }

        Intent intent = new Intent("com.android.camera.action.CROP");
        //sdk>=24
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri outPutUri = Uri.fromFile(mCropFile);
            intent.setDataAndType(inputUri, "image/*");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
            intent.putExtra("noFaceDetection", false);//去除默认的人脸识别，否则和剪裁匡重叠
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            Uri outPutUri = Uri.fromFile(mCropFile);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                String url = GetImagePath.getPath(getApplicationContext(), inputUri);//这个方法是处理4.4以上图片返回的Uri对象不同的处理方法
                intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
            } else {
                intent.setDataAndType(inputUri, "image/*");
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outPutUri);
        }

        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());// 图片格式
        startActivityForResult(intent, RESULT_REQUEST_CODE);//这里就将裁剪后的图片的Uri返回了
    }

    /**
     * 上传图片
     */
    private void postUploadMemberPhoto() {
        RequestParams params = new RequestParams();
        params.put("DataBase64", imageToBase64(mCropFile));
        params.put("ZIP", "");
        CallBack callBack = new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                memberPhotoBean = CommonFun.JsonToObj(responseString, MemberPhotoBean.class);
                mMemberPhotoAddress = memberPhotoBean.getData();
                if (mMemberPhotoAddress.contains("http")) {
                    Glide.with(EditUserActivity.this).load(mMemberPhotoAddress).into(civAddMemberHead);
                } else {
                    String url = mMemberPhotoAddress;
                    if (url.contains("../")) {
                        String[] s = url.split("\\.\\./");
                        url = s[2];
                    }

                    Glide.with(EditUserActivity.this).load(HttpAPI.API().MAIN_DOMAIN + "/" + url).into(civAddMemberHead);
                }
            }

            @Override
            public void onFailure(String msg) {
                CustomToast.makeText(EditUserActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };
        callBack.setLoadingAnimation(this, "正在上传头像...", false);
        HttpHelper.post(this, HttpAPI.API().UPLOAD_GIFT_IMAGE, params, callBack);
    }

    /**
     * 将图片文件转成Base64编码
     *
     * @param file 文件
     * @return base64码
     */
    public static String imageToBase64(File file) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(file);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        Base64Encoder encoder = new Base64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }
}
