package com.zhiluo.android.yunpu.member.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.zhiluo.android.yunpu.ui.activity.BaseActivity;
import com.zhiluo.android.yunpu.http.HttpAPI;
import com.zhiluo.android.yunpu.ui.view.NoDoubleClickListener;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.member.adapter.EmployeeListAdapter;
import com.zhiluo.android.yunpu.member.manager.activity.AddMemberActivity;
import com.zhiluo.android.yunpu.member.jsonbean.EmployeeJsonBean;
import com.zhiluo.android.yunpu.utils.CommonFun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * 员工列表选择界面
 * iauthor：Cheng
 * date: 2017/6/10 14:12
 * email：jasoncheng9111@gmail.com
 */
public class EmployeeListActivity extends BaseActivity
{
    private TextView tv_back;
    private TextView tv_commission_empl_name;
    private ListView lv_empllist;
    private List<EmployeeJsonBean.DataBean> list_employee = new ArrayList<>();
    private EmployeeListAdapter adapter;
    public static Map<String, String> map_emGID = new HashMap<>();

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employeelist);

        initView();

        post_getDatas();

        initListener();
    }

    /**
     * 从服务器拉去数据
     */
    private void post_getDatas()
    {
        AsyncHttpClient client = new AsyncHttpClient();
        PersistentCookieStore cookieStore = new PersistentCookieStore(this);
        client.setCookieStore(cookieStore);
        client.post(HttpAPI.API().GET_EMPLLIST, new AsyncHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody)
            {
                if (statusCode == 200)
                {
                    try
                    {
                        Gson gson = new Gson();
                        EmployeeJsonBean employeeJsonBean = CommonFun.JsonToObj(new String(responseBody, "UTF-8"), EmployeeJsonBean.class);
                        list_employee = employeeJsonBean.getData();
                        adapter = new EmployeeListAdapter(EmployeeListActivity.this, list_employee);
                        lv_empllist.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        for (int i=0; i<list_employee.size(); i++)
                        {
                            //将员工列表的GID和编号存入
                            map_emGID.put(list_employee.get(i).getEM_Code(),list_employee.get(i).getGID());
                        }
                    }
                    catch (Exception e)
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

    private void initListener()
    {
        //返回
        tv_back.setOnClickListener(new NoDoubleClickListener()
        {
            @Override
            public void onNoDoubleClick(View v)
            {
                finish();
            }
        });

        //点击列表
        lv_empllist.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                TextView tv_employee_name = (TextView) view.findViewById(R.id.tv_employee_name);
                Intent intent = new Intent(EmployeeListActivity.this, AddMemberActivity.class);
                intent.putExtra("employee_name", tv_employee_name.getText().toString());
                setResult(2, intent);
                finish();
            }
        });
    }

    private void initView()
    {
        tv_back = (TextView) findViewById(R.id.tv_goods_comsume_back);
        lv_empllist = (ListView) findViewById(R.id.lv_empllist);
    }


}
