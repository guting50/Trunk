package com.zhiluo.android.yunpu.member.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.entity.UsergradeBean;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

/**
 * 会员等级选择
 */
public class MemberGradeActivity extends BaseActivity
{
    private ListView listView;
    private List<String> stringList = new ArrayList<>();
    private TextView addgrade;
    private String grade;
    private TextView back;
    private List<UsergradeBean.DataBean> UsergradeBeanList = new ArrayList<>();
    private ArrayAdapter madapter;
    public static MemberGradeActivity instence = null;
    private SwipeRefreshLayout srlfresh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usergrade);
        instence = this;
        //初始化视图
        initView();
        //初始化适配器
        initadapter();
        //初始化数据

        if (!(stringList.size() > 0))
        {
            initdata();
        }
        //初始化监听
        initListenin();
    }

    private void initListenin()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(MemberGradeActivity.this, AddMemberActivity.class);
                intent.putExtra("usergrade", stringList.get(position));
                intent.putExtra("VGGID", UsergradeBeanList.get(position).getGID());
                setResult(1, intent);
                finish();
            }
        });
        addgrade.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new SweetAlertDialog(MemberGradeActivity.this, SweetAlertDialog.NORMAL_TYPE)
                        .setTitleText("提示")
                        .setContentText("新增会员等级请前往PC端操作！")
                        .setConfirmText("了解")
                        .show();
//                Intent intent = new Intent(AddGradeActivity.this, AddMemberGradeActivity.class);
//                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MemberGradeActivity.this, AddMemberActivity.class);
                intent.putExtra("usergrade", grade);
                setResult(1, intent);
                finish();
            }
        });
        srlfresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                UsergradeBeanList.clear();
                stringList.clear();
                initdata();
            }
        });

    }

    private void initadapter()
    {
        madapter = new ArrayAdapter(this, R.layout.item_gift_choose_category, stringList);
        listView.setAdapter(madapter);
    }

    private void initdata()
    {
        Intent gradeintent = getIntent();
        grade = gradeintent.getStringExtra("grade");
        //网络请求获取数据
        AsyncHttpClient client = new AsyncHttpClient();
        final PersistentCookieStore myCookieStore = new PersistentCookieStore(this);
        client.setCookieStore(myCookieStore);
        RequestParams params = new RequestParams();
        params.put("GID","");
        client.post(HttpAPI.API().USERGRADE, params, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                if (statusCode == 200)
                {
                    String resurt = null;
                    try
                    {
                        resurt = new String(responseBody, "UTF-8");
                        Gson gson = new Gson();
                        UsergradeBean usergradeBean = CommonFun.JsonToObj(resurt, UsergradeBean.class);
                        UsergradeBeanList.addAll(usergradeBean.getData());
                        for (int i = 0; i < UsergradeBeanList.size(); i++)
                        {
                            String vgname = UsergradeBeanList.get(i).getVG_Name();
                            stringList.add(vgname);
                        }
                        srlfresh.setRefreshing(false);
                        madapter.notifyDataSetChanged();

                    }
                    catch (UnsupportedEncodingException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error)
            {
            }
        });
    }

    private void initView()
    {
        listView = (ListView) findViewById(R.id.lv_usergrade_activity);
        //添加分类
        addgrade = (TextView) findViewById(R.id.tv_addgrade_activity);
        back = (TextView) findViewById(R.id.tv_goods_comsume_back);
        srlfresh = (SwipeRefreshLayout) findViewById(R.id.srl_freshusergrad_activity);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(MemberGradeActivity.this, AddMemberActivity.class);
        intent.putExtra("usergrade", grade);
        setResult(1, intent);
        finish();
    }

}
