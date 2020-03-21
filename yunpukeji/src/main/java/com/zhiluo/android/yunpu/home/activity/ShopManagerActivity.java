package com.zhiluo.android.yunpu.home.activity;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.entity.UserNumEntity;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.http.CallBack;
import com.zhiluo.android.yunpu.http.HttpHelper;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.home.jsonbean.AllShopListBean;
import com.zhiluo.android.yunpu.home.jsonbean.TotalVipNum;
import com.zhiluo.android.yunpu.home.jsonbean.WelcomeData;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.network.MyTextHttpResponseHandler;
import com.zhiluo.android.yunpu.sms.jsonbean.SMSNumBean;
import com.zhiluo.android.yunpu.utils.CommonFun;
import com.zhiluo.android.yunpu.utils.ShowSweetDialogUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

import static com.zhiluo.android.yunpu.R.id.et_shopname;

/**
 * 店铺设置界面
 */
public class ShopManagerActivity extends BaseActivity {
    private EditText etshopname, etusername, etphonenum;
    private TextView back;
    private TextView tvbaocun, tveditime, tvmostuser, tvlasttime, tvbanben, vipzongshu, duanxinkucun;
    private List<AllShopListBean.DataBean> shoplisst = new ArrayList<>();
    private String gid;
    private int state;
    private int sm_type;
    private TotalVipNum totalVipNum;
    private String versioname;
    private String viptotal;
    private String mStorePictureUrl;
    private TextView tvUser, tvGoodsNum;
    private String storeGid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_manager);
        getAPPVersion();
        mStorePictureUrl = getIntent().getStringExtra("picture");
        storeGid = getIntent().getStringExtra("SM_GID");
        initView();
        //获取店铺信息
        postshoplist();
        //获取库存短信条数
        postsmsnum();
        initlistenin();
    }

    private void getUserNum() {
        RequestParams params = new RequestParams();
        params.put("SM_GID", storeGid);
        params.put("RM_GID", "");
        HttpHelper.post(ShopManagerActivity.this, "UserManager/QueryUsersList", params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                UserNumEntity entity = CommonFun.JsonToObj(responseString, UserNumEntity.class);
                if (sm_type == 0) {
                    tvUser.setText("1/1");
                } else if (sm_type == 1) {
                    tvUser.setText(entity.getData().size() + "/10");
                }
            }

            @Override
            public void onFailure(String msg) {
                tvUser.setText("1/1");
            }
        });
    }

    private void getGoodsNum() {
        RequestParams params = new RequestParams();
        params.put("PageIndex", 1);
        params.put("PageSize", 1);
        params.put("PC_CodeOrName", "");
        HttpHelper.post(ShopManagerActivity.this, HttpAPI.API().SERVICEGOODS, params, new CallBack() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                GoodsCheckResponseByType goods = CommonFun.JsonToObj(responseString, GoodsCheckResponseByType.class);
                if (sm_type == 0) {
                    tvGoodsNum.setText(goods.getData().getDataCount() + "/100");
                } else if (sm_type == 1) {
                    tvGoodsNum.setText(goods.getData().getDataCount() + "/无上限");
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        });
    }

    private void postvipnum() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        client.post(HttpAPI.API().GETVIPNUM, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resurt = null;
                    try {
                        resurt = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        Adduserbean adduserbean = CommonFun.JsonToObj(resurt, Adduserbean.class);
                        boolean isture = adduserbean.isSuccess();
                        if (isture) {
                            WelcomeData totalVipNum1 = CommonFun.JsonToObj(resurt, WelcomeData.class);
                            viptotal = totalVipNum1.getData().getSA_AllMembersCount();
                            double xx = Double.parseDouble(viptotal);
                            if (sm_type == 0) {
                                vipzongshu.setText((int) xx + "/365");
                            }
                            if (sm_type == 1) {
                                vipzongshu.setText((int) xx + "/无上限");
                            }
                        } else {
                            ShowSweetDialogUtil.showWarningDialog(ShopManagerActivity.this, adduserbean.getMsg());
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void updataview() {
        gid = shoplisst.get(0).getGID();
        state = shoplisst.get(0).getSM_State();

        etshopname.setText(shoplisst.get(0).getSM_Name());
        if (shoplisst.get(0).getSM_Phone() != null) {
            etphonenum.setText(shoplisst.get(0).getSM_Phone().toString());
        }
        if (shoplisst.get(0).getSM_Contacter() != null) {
            etusername.setText(shoplisst.get(0).getSM_Contacter().toString());
        }
        //        if (shoplisst.get(0).getSM_DefaultCode() != null)
        //        {
        //            etmima.setText(shoplisst.get(0).getSM_DefaultCode().toString());
        //
        //     }
        //到期时间
        String endTime = shoplisst.get(0).getSM_EndTime();
        if (endTime != null) {
            endTime = endTime.substring(0, 10);
        }
        tvlasttime.setText(endTime);
        //编辑时间
        String editTime = shoplisst.get(0).getSM_CreateTime();
        if (editTime != null) {
            editTime = editTime.substring(0, 10);
        }
        tveditime.setText(editTime);
        sm_type = shoplisst.get(0).getSM_Type();
        switch (sm_type) {
            case 0:
                tvbanben.setText("免费版");
                break;
            case 1:
                tvbanben.setText("高级版");
                break;
            case 2:
                tvbanben.setText("高级版（永久）");
                break;
            case 11:
                tvbanben.setText("高级版（1年）");
                break;
            case 12:
                tvbanben.setText("高级版（2年）");
                break;
            case 110:
                tvbanben.setText("高级版（10年）");
                break;
            default:
                tvbanben.setText("免费版");
                break;
        }

        //获取会员数
        postvipnum();
        getUserNum();
        getGoodsNum();
    }

    private void postshoplist() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        client.post(HttpAPI.API().CHECKALLSHOP, params, new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                AllShopListBean allShopListBean = CommonFun.JsonToObj(responseString, AllShopListBean.class);
                shoplisst.addAll(allShopListBean.getData());
                updataview();
            }

            @Override
            public void onFailure(String msg) {
                ShowSweetDialogUtil.showWarningDialog(ShopManagerActivity.this, msg);
            }
        });
    }

    private void initlistenin() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //保存提交
        tvbaocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //校验必填字段
                if (postVerifyData()) {
                    //提交店铺修改数据
                    postSave();
                }

            }
        });
    }

    /**
     * 提交保存之前对三个字段进行数据校验
     * 店铺名称
     * 联系人
     * 联系电话
     */
    private boolean postVerifyData() {
        //店铺名称
        if (etshopname.getText().toString().equals("")) {
            ShowSweetDialogUtil.showWarningDialog(ShopManagerActivity.this, "请检查 [店铺名称] 输入是否有效！");
//            CustomToast.makeText(this, "请检查 [店铺名称] 输入是否有效！", Toast.LENGTH_SHORT).show();
            return false;
        }
        //联系人
        else if (etusername.getText().toString().equals("")) {
            ShowSweetDialogUtil.showWarningDialog(ShopManagerActivity.this, "请检查 [联系人] 输入是否有效！");
//            CustomToast.makeText(this, "请检查 [联系人] 输入是否有效！", Toast.LENGTH_SHORT).show();
            return false;
        }
        //联系电话
        else if (etphonenum.getText().toString().equals("")) {
            ShowSweetDialogUtil.showWarningDialog(ShopManagerActivity.this, "请检查 [联系电话] 输入是否有效！");
//            CustomToast.makeText(this, "请检查 [联系电话] 输入是否有效！", Toast.LENGTH_SHORT).show();
            return false;
        } else if (etphonenum.getText().toString().length() != 7 & etphonenum.getText().toString().length() != 11) {
            ShowSweetDialogUtil.showWarningDialog(ShopManagerActivity.this, "请检查 [联系电话] 长度是否有效！");
//            CustomToast.makeText(this, "请检查 [联系电话] 长度是否有效！", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    /**
     * 提交数据保存
     */
    private void postSave() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("GID", gid);
        params.put("SM_State", state);
        params.put("SM_City", "中国");
        params.put("SM_Contacter", etusername.getText().toString());
        params.put("SM_Name", etshopname.getText().toString());
        params.put("SM_Phone", etphonenum.getText().toString());
        params.put("SM_Addr", "");
        params.put("SM_Picture", mStorePictureUrl);
        MyTextHttpResponseHandler responseHandler = new MyTextHttpResponseHandler() {
            @Override
            public void onSuccess(String responseString, Gson gson) {
                //将修改好的店铺名称保存到本地，以便于回到主界面后店铺名称不能及时更新
                SharedPreferences.Editor editor = getSharedPreferences("setting", 0).edit();
                editor.putString("mytitle", etshopname.getText().toString());
                editor.commit();
                ShowSweetDialogUtil.showSuccessDialog(ShopManagerActivity.this, "提交成功！", true);
            }

            @Override
            public void onFailure(String msg) {
                ShowSweetDialogUtil.showWarningDialog(ShopManagerActivity.this, msg);
            }
        };
        responseHandler.setDialog(ShopManagerActivity.this, "正在提交...", false);
        client.post(HttpAPI.API().EDITORSHOP, params, responseHandler);
    }

    private void initView() {
        tvUser = (TextView) findViewById(R.id.tv_user_num);
        tvGoodsNum = (TextView) findViewById(R.id.tv_goods_num);
        tvbanben = (TextView) findViewById(R.id.tv_isgaojiban);
        vipzongshu = (TextView) findViewById(R.id.tv_huiyuanshu);
        duanxinkucun = (TextView) findViewById(R.id.tv_duanxinkucun);
        back = (TextView) findViewById(R.id.tv_back_activity);
        tvbaocun = (TextView) findViewById(R.id.tv_baocun);
        //        tvbanben = (TextView) findViewById(R.id.tv_banben);
        tveditime = (TextView) findViewById(R.id.tv_editime);
        //  tvmostuser = (TextView) findViewById(R.id.tv_mostuser);
        tvlasttime = (TextView) findViewById(R.id.tv_lasttime);
        etshopname = (EditText) findViewById(et_shopname);
        etusername = (EditText) findViewById(R.id.et_username);
        //        etmima = (EditText) findViewById(R.id.et_mima);
        etphonenum = (EditText) findViewById(R.id.et_phonenum);

    }

    /**
     * 获取软件版本号
     */
    private void getAPPVersion() {
        PackageManager pm = this.getPackageManager();//得到PackageManager对象

        try {
            PackageInfo pi = pm.getPackageInfo(this.getPackageName(), 0);//得到PackageInfo对象，封装了一些软件包的信息在里面
            versioname = pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    //获取短信条数
    private void postsmsnum() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        client.post(HttpAPI.API().SMSNUM, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String resurt = null;
                    try {
                        resurt = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        Adduserbean adduserbean = CommonFun.JsonToObj(resurt, Adduserbean.class);
                        boolean isture = adduserbean.isSuccess();
                        if (isture) {
                            int i = adduserbean.getMsg().length();
                            if (i == 4) {
                                SMSNumBean smsNumBean = CommonFun.JsonToObj(resurt, SMSNumBean.class);
                                duanxinkucun.setText(smsNumBean.getData().getUStorage() + "");
                            } else {
                                duanxinkucun.setText("" + 0);
                            }
                        } else {
                            ShowSweetDialogUtil.showWarningDialog(ShopManagerActivity.this, adduserbean.getMsg());
//                            new SweetAlertDialog(ShopManagerActivity.this, SweetAlertDialog.WARNING_TYPE)
//                                    .setTitleText("提示")
//                                    .setContentText(adduserbean.getMsg() + "!")
//                                    .setConfirmText("了解")
//                                    .show();
                            //                            CustomToast.makeText(ShopManagerActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            }
        });
    }
}
