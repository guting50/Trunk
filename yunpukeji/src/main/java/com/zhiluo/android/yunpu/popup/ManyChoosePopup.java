package com.zhiluo.android.yunpu.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.Interface.InterfaceBack;
import com.zhiluo.android.yunpu.goods.consume.adapter.NewManyChooseAdapter;
import com.zhiluo.android.yunpu.goods.consume.bean.GoodsModelBean;

import java.util.Iterator;
import java.util.List;

public class ManyChoosePopup extends PopupWindow {

    private View view_bg;
    private TextView tv_close;
    private RecyclerView recyclerView;
    private Button btn_submit;

    private NewManyChooseAdapter mShopRulesAdapter;

    private String manyStr;

    protected OnShopClickEvent mClickEvent;

    public interface OnShopClickEvent {
        void OnShopClickEvent(String str);
    }

    /**
     * @param modelList 组装规格数据
     * @param manyStr   多规格商品  many:多规格,not_many:不是多规格商品
     */
    public ManyChoosePopup(Context context, View parent, List<List<GoodsModelBean>> modelList, String manyStr, OnShopClickEvent clickEvent) {
        View view = View.inflate(context, R.layout.popup_many_choose, null);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //PopupWindow响应返回键，那么必须给PopupWindow设置一个背景才行
        setBackgroundDrawable(new ColorDrawable(0x00000000));
        setFocusable(true);
        //点击区域外不关闭弹框
        setOutsideTouchable(true);
        setContentView(view);
        showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        update();
        mClickEvent = clickEvent;

        view_bg = (View) view.findViewById(R.id.view_bg);
        tv_close = (TextView) view.findViewById(R.id.tv_close);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        btn_submit = (Button) view.findViewById(R.id.btn_submit);
        initData(context, modelList, manyStr);
    }

    private void initData(final Context context, List<List<GoodsModelBean>> modelist, String manyFlag) {
        List<List<GoodsModelBean>> flagList = modelist;

        //删除没有的规格
        for (int i = 0; i < flagList.size(); i++) {
            List<GoodsModelBean> beanList = flagList.get(i);
            for (Iterator<GoodsModelBean> iterator = beanList.iterator(); iterator.hasNext(); ) {
                if (iterator.next().getPM_Properties().equals("")) {
                    iterator.remove();
                }
            }
            if (flagList.get(i).size() == 0) {
                flagList.remove(i);
                i--;
            }
        }

        StringBuilder modelName = new StringBuilder("");
        for (int j = 0; j < flagList.size(); j++) {
            for (int k = 0; k < flagList.get(j).size(); k++) {
                if (flagList.get(j).get(k).isChecked()) {
                    modelName.append(flagList.get(j).get(k).getPM_Properties() + "|");
                }
            }
        }
        String str = String.valueOf(modelName);
        if (str.length() > 0) {
            if (str.substring(str.length() - 1, str.length()).equals("|")) {
                str = str.substring(0, str.length() - 1);
            }
        }
        manyStr = str;

        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        mShopRulesAdapter = new NewManyChooseAdapter(context, flagList, manyFlag, new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                List<List<GoodsModelBean>> mManyModelList = (List<List<GoodsModelBean>>) response;
                StringBuilder modelName = new StringBuilder("");
                for (int j = 0; j < mManyModelList.size(); j++) {
                    for (int k = 0; k < mManyModelList.get(j).size(); k++) {
                        if (mManyModelList.get(j).get(k).isChecked()) {
                            modelName.append(mManyModelList.get(j).get(k).getPM_Properties() + "|");
                        }
                    }
                }
                String str = String.valueOf(modelName);
                if (str.length() > 0) {
                    if (str.substring(str.length() - 1, str.length()).equals("|")) {
                        str = str.substring(0, str.length() - 1);
                    }
                }
                manyStr = str;
            }

            @Override
            public void onErrorResponse(Object msg) {

            }
        });
        recyclerView.setAdapter(mShopRulesAdapter);

        view_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickEvent.OnShopClickEvent(manyStr);
                dismiss();
            }
        });
    }

}
