package com.zhiluo.android.yunpu.goods.manager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsOriginalCategoryForChooseCategoryAdapter;
import com.zhiluo.android.yunpu.goods.manager.adapter.GoodsSubCategoryAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsTypeA;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsTypeB;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsTypeC;
import com.zhiluo.android.yunpu.goods.manager.bean.ProductTypeBean;
import com.zhiluo.android.yunpu.member.jsonbean.Adduserbean;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

/**
 * 选择商品分类
 */
public class GoodsChooseCategoryListActivity extends BaseActivity {
    private boolean Tag = false;
    private BaseListView lvshow, lvlist;
    private List<ProductTypeBean.DataBean> tybelist = new ArrayList<>();
    private GoodsSubCategoryAdapter badapter;
    private GoodsOriginalCategoryForChooseCategoryAdapter madapter;
    private WaveSwipeRefreshLayout refreshLayout;
    private List<String> stringList = new ArrayList<>();
    private List<GoodsTypeA> goodstypea = new ArrayList<>();
    private List<GoodsTypeB> goodstypeb = new ArrayList<>();
    private List<GoodsTypeC> goodstypec = new ArrayList<>();
    private TextView tv_back_activity;
    private TextView addsmall;
    String typegid;
    String typename;
    private TextView addbig;
    private String key;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_choose_category);
        initView();
        initAdapter();
        inittypename();
        initListener();
    }

    private void initListener() {
        tv_back_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsChooseCategoryListActivity.this, AddGoodsActivity.class);
                intent.putExtra("goodstype", "");
                intent.putExtra("ptid", "");
                intent.putExtra("SyncType", 0);
                setResult(1, intent);
                finish();
            }
        });
        lvshow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (goodstypea.size() > 0) {
                    madapter.setSelectedPosition(position);
                    madapter.notifyDataSetInvalidated();
                    goodstypec.clear();
                    key = goodstypea.get(position).getGID();
                    name = goodstypea.get(position).getPT_Name();
                    for (int i = 0; i < goodstypeb.size(); i++) {
                        if (goodstypeb.get(i).getPT_Parent().equals(key)) {
                            GoodsTypeC typec = new GoodsTypeC(goodstypeb.get(i).getGID(), goodstypeb.get(i).getPT_Name(), goodstypeb.get(i)
                                    .getPT_Parent(), goodstypeb.get(i).getPT_SynType());
                            goodstypec.add(typec);
                        }
                    }
                    badapter.notifyDataSetChanged();
                }
            }
        });
        lvlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (goodstypec.size() == 0) {
                    return;
                }
                Intent intent = new Intent(GoodsChooseCategoryListActivity.this, AddGoodsActivity.class);

                intent.putExtra("goodstype", goodstypec.get(position).getPT_Name());
                intent.putExtra("ptid", goodstypec.get(position).getGID());
                intent.putExtra("SyncType", goodstypec.get(position).getPT_SynType());
                setResult(1, intent);
                finish();
            }
        });

        refreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Tag = true;
                inittypename();
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
        addbig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsChooseCategoryListActivity.this, AddGoodsOriginalCategoryActivity.class);
                startActivity(intent);
            }
        });
        addsmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoodsChooseCategoryListActivity.this, AddGoodsSubCategoryActivity.class);
                intent.putExtra("key", key);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }


    private void initAdapter() {
        madapter = new GoodsOriginalCategoryForChooseCategoryAdapter(this, goodstypea);
        View item_add_goods_originalcategory = getLayoutInflater().inflate(R.layout.item_add_goods_originalcategory, null);
        addbig = (TextView) item_add_goods_originalcategory.findViewById(R.id.tv_addbig);
        lvshow.addFooterView(item_add_goods_originalcategory);
        lvshow.setAdapter(madapter);
        badapter = new GoodsSubCategoryAdapter(this, goodstypec);
        View item_add_goods_subcategory = getLayoutInflater().inflate(R.layout.item_add_goods_subcategory, null);
        addsmall = (TextView) item_add_goods_subcategory.findViewById(R.id.tv_add2);
        lvlist.addFooterView(item_add_goods_subcategory);
        lvlist.setAdapter(badapter);
    }

    private void initView() {
        refreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.srl_freshgoodslisttmng_activity);
        lvshow = (BaseListView) findViewById(R.id.lv_goodstype_activity);
        lvlist = (BaseListView) findViewById(R.id.lv_goodslistdmgt_activity);
        tv_back_activity = (TextView) findViewById(R.id.tv_back_activity);
    }

    /**
     * 网络请求获取分类名称
     */
    private void inittypename() {
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        client.post(HttpAPI.API().PRODUCTTYPE, params, new AsyncHttpResponseHandler() {
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
                            ProductTypeBean tybebean = CommonFun.JsonToObj(resurt, ProductTypeBean.class);
                            if (Tag) {
                                tybelist.clear();
                                goodstypeb.clear();
                                goodstypea.clear();
                                goodstypec.clear();
                                stringList.clear();
                                Tag = false;
                            }
                            tybelist.addAll(tybebean.getData());
                            for (int i = 0; i < tybelist.size(); i++) {
                                String s = tybelist.get(i).getPT_Parent();
                                if (s == null || s.equals("")) {
                                    GoodsTypeA typea = new GoodsTypeA(tybelist.get(i).getGID(), tybelist.get(i).getPT_Name(),
                                            "", tybelist.get(i).getPT_SynType());
                                    goodstypea.add(typea);
                                } else {
                                    GoodsTypeB typeb = new GoodsTypeB(tybelist.get(i).getGID(), tybelist.get(i).getPT_Name(),
                                            s, tybelist.get(i).getPT_SynType());
                                    goodstypeb.add(typeb);
                                }
                            }
                            refreshLayout.setRefreshing(false);
                            lvshow.performItemClick(null, 0, 0);
                            madapter.notifyDataSetChanged();
                        } else {
                            new SweetAlertDialog(GoodsChooseCategoryListActivity.this, SweetAlertDialog.WARNING_TYPE)
                                    .setTitleText("提示")
                                    .setContentText(adduserbean.getMsg() + "!")
                                    .setConfirmText("了解")
                                    .show();
//                            CustomToast.makeText(GoodsChooseCategoryListActivity.this, adduserbean.getMsg(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GoodsChooseCategoryListActivity.this, AddGoodsActivity.class);
        intent.putExtra("goodstype", "");
        intent.putExtra("ptid", "");
        setResult(1, intent);
        finish();

    }
}
