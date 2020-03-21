package com.zhiluo.android.yunpu.goods.manager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.mvp.model.GoodsType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ExpandableListAdapter
 * 作者：罗咏哲 on 2017/7/29 13:47.
 * 邮箱：137615198@qq.com
 */

public class GoodsTypeExpandAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<GoodsType.DataBean> mParentType;//父类型数据
    private Map<GoodsType.DataBean, List<GoodsType.DataBean>> mChild;

    public GoodsTypeExpandAdapter(Context mContext, List<GoodsType.DataBean> mParentType,
                                  Map<GoodsType.DataBean, List<GoodsType.DataBean>> mChild) {
        this.mContext = mContext;
        this.mParentType = mParentType;
        this.mChild = mChild;
    }

    //获取父类型数量
    @Override
    public int getGroupCount() {
        return mParentType.size();
    }

    //获取子类型数量
    @Override
    public int getChildrenCount(int groupPosition) {
        return mChild.get(mParentType.get(groupPosition)).size();
    }

    //获取某个父类型
    @Override
    public Object getGroup(int groupPosition) {
        return mChild.get(mParentType.get(groupPosition));
    }

    //获取某个父类型的某个子类型
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChild.get(mParentType.get(groupPosition)).get(childPosition);
    }

    //获取某个父类型的id
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //获取某个父类型的某个子类型id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int parentPosition, boolean b, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        GroupHolder groupHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.expandable_item_parent, null);
            groupHolder = new GroupHolder(view);
            view.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) view.getTag();
        }
        groupHolder.tvParentName.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        if (mParentType.get(parentPosition).isChecked()) {
            groupHolder.checked.setBackgroundResource(R.color.yunpu);
        } else {
            // groupHolder.tvParentName.setTextColor(mContext.getResources().getColor(R.color.color_main_text_black));
            groupHolder.checked.setBackgroundResource(R.color.background);
        }
        groupHolder.tvParentName.setText(mParentType.get(parentPosition).getPT_Name());
        groupHolder.tvParentName.setTag(parentPosition);
        return view;
    }

    @Override
    public View getChildView(int parentPosition, int childPosition, boolean b, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        ChildHolder childHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.expandable_item_child, null);
            childHolder = new ChildHolder(view);
            view.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) view.getTag();
        }

        childHolder.tvChildName.setText(mChild.get(mParentType.get(parentPosition)).get(childPosition).getPT_Name());
        if (mChild.get(mParentType.get(parentPosition)).get(childPosition).isChecked()) {
            childHolder.checked.setBackgroundResource(R.color.text_black);
            childHolder.tvChildName.setBackgroundResource(R.color.white);
//            childHolder.tvChildName.setTextColor(MyApplication.getmContext().getResources().getColor(R.color.red));
        } else {
            childHolder.checked.setBackgroundResource(R.color.background);
            childHolder.tvChildName.setBackgroundResource(R.color.background);
//            childHolder.tvChildName.setTextColor(MyApplication.getmContext().getResources().getColor(R.color.gray));
        }
        return view;
    }

    /**
     * 子项是否可选中
     *
     * @param i
     * @param i1
     * @return
     */
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class GroupHolder {
        TextView tvParentName, checked;

        public GroupHolder(View view) {
            tvParentName = (TextView) view.findViewById(R.id.tv_item_parent);
            checked = (TextView) view.findViewById(R.id.tv_expandable_child_checked);

        }
    }

    class ChildHolder {
        TextView tvChildName, checked;

        public ChildHolder(View view) {
            tvChildName = (TextView) view.findViewById(R.id.tv_item_child);
            checked = (TextView) view.findViewById(R.id.tv_expandable_child_checked);
        }
    }
}
