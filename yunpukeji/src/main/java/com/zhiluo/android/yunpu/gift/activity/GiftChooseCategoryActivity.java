package com.zhiluo.android.yunpu.gift.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.maimengmami.waveswiperefreshlayout.WaveSwipeRefreshLayout;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.ui.view.BaseListView;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.gift.bean.GiftTypeBean;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

/**
 * 选择礼品分类
 */
public class GiftChooseCategoryActivity extends BaseActivity {
    private boolean Tag = false;
    private TextView tvaddtype;
    private TextView tv_back_activity;
    private BaseListView lvtype;
    private ArrayAdapter madapter;
    private WaveSwipeRefreshLayout refreshLayout;
    private List<String> stringList = new ArrayList<>();
    List<GiftTypeBean.DataBean> dataBeanList = new ArrayList<>();
    private TextView tvaddgifttype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_choose_category);
        initView();
        initAdapter();
        postTypelist();
        initLinsinter();

    }

    private void initLinsinter() {
        tv_back_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiftChooseCategoryActivity.this, AddGiftsActivity.class);
                intent.putExtra("gtname", "");
                intent.putExtra("gtgit", "");
                setResult(0, intent);
                finish();
            }
        });

        lvtype.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GiftChooseCategoryActivity.this, AddGiftsActivity.class);
                intent.putExtra("gtname", dataBeanList.get(position).getGT_Name());
                intent.putExtra("gtgit", dataBeanList.get(position).getGID());
                setResult(0, intent);
                finish();
            }
        });
        tvaddgifttype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GiftChooseCategoryActivity.this, AddGiftsCategoryActivity.class);
                startActivity(intent);
            }
        });
        refreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Tag = true;
                postTypelist();
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


    private void postTypelist() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        client.post(HttpAPI.API().GIFTTYPELIST, params, new AsyncHttpResponseHandler() {
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
                            GiftTypeBean typeBean = CommonFun.JsonToObj(resurt, GiftTypeBean.class);
                            if (Tag) {
                                dataBeanList.clear();
                                stringList.clear();
                            }
                            dataBeanList.addAll(typeBean.getData());
                            for (int i = 0; i < dataBeanList.size(); i++) {
                                stringList.add(dataBeanList.get(i).getGT_Name());
                            }
                            refreshLayout.setRefreshing(false);
                            madapter.notifyDataSetChanged();


                        } else {
                            new SweetAlertDialog(GiftChooseCategoryActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(adduserbean.getMsg() + "!")
                                    .setConfirmText("了解")
                                    .show();
//                            CustomToast.makeText(GiftChooseCategoryActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
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

    private void initAdapter() {
        madapter = new ArrayAdapter(this, R.layout.item_gift_choose_category, stringList);
        lvtype.setAdapter(madapter);
    }

    private void initView() {
        refreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.re_gifttype);
        lvtype = (BaseListView) findViewById(R.id.lv_gifttype_activity);
        tv_back_activity = (TextView) findViewById(R.id.tv_back_activity);
        tvaddtype = (TextView) findViewById(R.id.tv_addgifttype_activity);
        tvaddgifttype = (TextView) findViewById(R.id.tv_addgifttype_activity);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GiftChooseCategoryActivity.this, AddGiftsActivity.class);
        intent.putExtra("gtname", "");
        intent.putExtra("gtgit", "");
        setResult(0, intent);
        finish();
    }
}
