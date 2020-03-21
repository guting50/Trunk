package com.zhiluo.android.yunpu.popup;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.goods.consume.activity.GoodsConsumeActivity;
import com.zhiluo.android.yunpu.goods.consume.adapter.ShoppingCartAdapter;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsDataBean;

import org.litepal.crud.DataSupport;

public class ShoppingCartPopup extends PopupWindow {

    private RecyclerView recyclerView;
    private ShoppingCartAdapter mAdapter;

    protected OnShopRefreshClickEvent mClickEvent;

    public interface OnShopRefreshClickEvent {
        void OnShopRefreshEvent();
    }

    public ShoppingCartPopup(Context context, View parent, OnShopRefreshClickEvent clickEvent) {
        View view = View.inflate(context, R.layout.popup_many_shop_cart, null);
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

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        View view_bg = (View) view.findViewById(R.id.view_bg);
        TextView tv_close = (TextView) view.findViewById(R.id.tv_close);
        TextView tv_delete = (TextView) view.findViewById(R.id.tv_delete);

        initRecyclerView(context);

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
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //清空购物车
                GoodsConsumeActivity.mHaveChoosedGoodList.clear();
                //清空数据库里的购物车数据
                DataSupport.deleteAll(GoodsDataBean.class);
                mClickEvent.OnShopRefreshEvent();
                dismiss();
            }
        });
    }

    private void initRecyclerView(final Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        mAdapter = new ShoppingCartAdapter(context, GoodsConsumeActivity.mHaveChoosedGoodList, new ShoppingCartAdapter.ItemClickEvent() {
            @Override
            public void refreshClick() {
                mClickEvent.OnShopRefreshEvent();
                if (GoodsConsumeActivity.mHaveChoosedGoodList.size() == 0) {
                    dismiss();
                }
            }
        });

        recyclerView.setAdapter(mAdapter);
    }
}
