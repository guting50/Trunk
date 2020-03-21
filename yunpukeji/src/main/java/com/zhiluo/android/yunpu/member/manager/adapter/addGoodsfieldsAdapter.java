package com.zhiluo.android.yunpu.member.manager.adapter;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.liangmutian.mypicker.DataPickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.fragments.OrdinaryGoodsFragment;
import com.zhiluo.android.yunpu.fragments.ServiceFragment;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsDetailActivity;
import com.zhiluo.android.yunpu.goods.manager.activity.GoodsModificationActivity;
import com.zhiluo.android.yunpu.login.jsonbean.GoodsFiledsBean;
import com.zhiluo.android.yunpu.utils.DateTimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by YSL on 2018-10-17.
 */

public class addGoodsfieldsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<GoodsFiledsBean.DataBean> costomfields;//自定义属性
    private OrdinaryGoodsFragment.ChangeHandler changeHandler;
    private ServiceFragment.ChangeHandler changeHandler1;
    private GoodsModificationActivity.ChangeHandler changeHandler2;
    private GoodsDetailActivity.ChangeHandler changeHandler3;
    private int type;
    private Dialog chooseDialog;
    private Dialog datesDialog;

    final int VIEW_TYPE = 4;
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;
    final int TYPE_4 = 3;
    final int TYPE_5 = 5;

    private OnImageClickEvent mClickEvent;

    public interface OnImageClickEvent {
        void OnImageClickEvent(int position, boolean isEmpty);
    }

    public void setImageClickEvent(OnImageClickEvent clickEvent) {
        mClickEvent = clickEvent;
    }

    public addGoodsfieldsAdapter(Context context, List<GoodsFiledsBean.DataBean> costomfields, OrdinaryGoodsFragment.ChangeHandler changeHandler, int type) {
        this.mContext = context;
        this.costomfields = costomfields;
        this.changeHandler = changeHandler;
        this.type = type;

    }

    public addGoodsfieldsAdapter(Context context, List<GoodsFiledsBean.DataBean> costomfields, ServiceFragment.ChangeHandler changeHandler, int type) {
        this.mContext = context;
        this.costomfields = costomfields;
        this.changeHandler1 = changeHandler;
        this.type = type;

    }

    public addGoodsfieldsAdapter(Context context, List<GoodsFiledsBean.DataBean> costomfields, GoodsModificationActivity.ChangeHandler changeHandler, int type) {
        this.mContext = context;
        this.costomfields = costomfields;
        this.changeHandler2 = changeHandler;
        this.type = type;

    }

    public addGoodsfieldsAdapter(Context context, List<GoodsFiledsBean.DataBean> costomfields, GoodsDetailActivity.ChangeHandler changeHandler, int type) {
        this.mContext = context;
        this.costomfields = costomfields;
        this.changeHandler3 = changeHandler;
        this.type = type;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_member_costom_text, null);
            return new ViewHolder1(view);
        } else if (viewType == TYPE_2) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_member_costom_number, null);
            return new ViewHolder2(view);
        } else if (viewType == TYPE_3) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_member_costom_choose, null);
            return new ViewHolder3(view);
        } else if (viewType == TYPE_4) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_member_costom_data, null);
            return new ViewHolder4(view);
        }  else if (viewType == TYPE_5) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_member_costom_image, null);
            return new ViewHolder5(view);
        }else {
            view = LayoutInflater.from(mContext).inflate(R.layout.activity_add_member_costom_text, null);
            return new ViewHolder1(view);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        GoodsFiledsBean.DataBean bean = costomfields.get(position);

        if (holder instanceof ViewHolder1) {
            ViewHolder1 viewHolder = (ViewHolder1) holder;

            viewHolder.etAddFieldTitle.setText(bean.getCF_FieldName());
            if (bean.getCF_Required().equals("是")) {
                viewHolder.isFill.setVisibility(View.VISIBLE);
            } else if (bean.getCF_Required().equals("否")) {
                viewHolder.isFill.setVisibility(View.GONE);
            }
            viewHolder.etAddFieldText.setHint("请输入" + costomfields.get(position).getCF_FieldName());
//            viewHolder.etAddFieldText.setText(costomfields.get(position).getM_ItemsValue());
            viewHolder.etAddFieldText.setText(costomfields.get(position).getCF_Value());
            if (type == 2) {
                viewHolder.etAddFieldText.setEnabled(false);
                viewHolder.etAddFieldText.setHint("未填写");
            }
            viewHolder.etAddFieldText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    costomfields.get(position).setM_ItemsValue(s.toString());
                    senMsgToActivity();
                }
            });

        } else if (holder instanceof ViewHolder2) {
            ViewHolder2 viewHolder = (ViewHolder2) holder;
            viewHolder.etAddFieldTitle.setText(bean.getCF_FieldName());
            if (bean.getCF_Required().equals("是")) {
                viewHolder.isFill.setVisibility(View.VISIBLE);
            } else if (bean.getCF_Required().equals("否")) {
                viewHolder.isFill.setVisibility(View.GONE);
            }
            viewHolder.etAddFieldNumber.setHint("请输入" + costomfields.get(position).getCF_FieldName());

            viewHolder.etAddFieldNumber.setText(costomfields.get(position).getM_ItemsValue());
            if (type == 2) {
                viewHolder.etAddFieldNumber.setEnabled(false);
                viewHolder.etAddFieldNumber.setHint("未填写");
            }
            viewHolder.etAddFieldNumber.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    costomfields.get(position).setM_ItemsValue(s.toString());
                    senMsgToActivity();
                }
            });

        } else if (holder instanceof ViewHolder3) {
            final ViewHolder3 viewHolder = (ViewHolder3) holder;
            viewHolder.tvTitle.setText(bean.getCF_FieldName());
            if (bean.getCF_Required().equals("是")) {
                viewHolder.isFill.setVisibility(View.VISIBLE);
            } else if (bean.getCF_Required().equals("否")) {
                viewHolder.isFill.setVisibility(View.GONE);
            }

            viewHolder.tvChoose.setText(costomfields.get(position).getM_ItemsValue());

            if (type == 2) {

                viewHolder.tvChoose.setEnabled(false);
                viewHolder.tvChoose.setHint("未填写");
            }
            viewHolder.tvChoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str = costomfields.get(position).getCF_ItemsValue();
                    if (str != null && !str.equals("")) {
                        showVipDialog(getDateForString(str), viewHolder.tvChoose, position);
                    }
                }
            });


        } else if (holder instanceof ViewHolder4) {
            final ViewHolder4 viewHolder = (ViewHolder4) holder;
//            Drawable drawable = mContext.getDrawable(R.mipmap.ysl_canlander); //(API 21以上才能使用此方法)
//
//            //或者：Drawable drawable = context.getResource().getDrawable(R.drawable.***);
//
//            drawable.setBounds(0, 0, 60, 50); //width即为你需要设置的图片宽度，height即为你设置的图片的高度
//            viewHolder.tvChoose.setCompoundDrawables(null, null, drawable, null);


            viewHolder.tvTitle.setText(bean.getCF_FieldName());
            if (bean.getCF_Required().equals("是")) {
                viewHolder.isFill.setVisibility(View.VISIBLE);
            } else if (bean.getCF_Required().equals("否")) {
                viewHolder.isFill.setVisibility(View.GONE);
            }

            viewHolder.tvChoose.setText(costomfields.get(position).getM_ItemsValue());

            if (type == 2) {
                viewHolder.tvChoose.setEnabled(false);
                viewHolder.tvChoose.setHint("未填写");
            }
            viewHolder.imDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (viewHolder.tvChoose.getText().toString().isEmpty()) {
                        showDateDialog(DateUtil.getDateForString(DateTimeUtil.getNowDate()), viewHolder.tvChoose, position);
                    } else {
                        showDateDialog(DateUtil.getDateForString(viewHolder.tvChoose.getText().toString()), viewHolder.tvChoose, position);
                    }
                }
            });
        } else if (holder instanceof ViewHolder5) {
            final ViewHolder5 viewHolder = (ViewHolder5) holder;

            viewHolder.tvTitle.setText(bean.getCF_FieldName());
            if (bean.getCF_Required().equals("是")) {
                viewHolder.isFill.setVisibility(View.VISIBLE);
            } else if (bean.getCF_Required().equals("否")) {
                viewHolder.isFill.setVisibility(View.GONE);
            }
            if (type == 2) {
                viewHolder.tvChoose.setEnabled(false);
            }
            if (costomfields.get(position).getCF_Value() != null && !costomfields.get(position).getCF_Value().equals("")) {
                Glide.with(mContext).load(costomfields.get(position).getCF_Value()).into(viewHolder.iv_bg);
            }

            viewHolder.iv_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickEvent != null) {
                        boolean isEmpty = false;
                        if (costomfields.get(position).getCF_Value() != null && !costomfields.get(position).getCF_Value().equals("")) {
                            isEmpty = true;
                        }
                        mClickEvent.OnImageClickEvent(position, isEmpty);
                    }
                }
            });
            viewHolder.tvChoose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickEvent != null) {
                        boolean isEmpty = false;
                        if (costomfields.get(position).getM_ItemsValue() != null && !costomfields.get(position).getM_ItemsValue().equals("")) {
                            isEmpty = true;
                        }
                        mClickEvent.OnImageClickEvent(position, isEmpty);
                    }
                }
            });
        }


    }

    @Override
    public int getItemViewType(int position) {

        String type = costomfields.get(position).getCF_FieldType();
        if (type.contains("文本"))
            return TYPE_1;
        else if (type.contains("数字"))
            return TYPE_2;
        else if (type.contains("选项"))
            return TYPE_3;
        else if (type.contains("日期"))
            return TYPE_4;
        else if (type.contains("图片"))
            return TYPE_5;
        else
            return TYPE_1;

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return costomfields == null ? 0 : costomfields.size();
    }


    /**
     * @param date      ,
     * @param mTextView ,
     *                  选择日期
     */
    private void showDateDialog(List<Integer> date, final TextView mTextView, final int position) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(mContext);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int[] dates) {

                mTextView.setText(dates[0] + "-" + (dates[1] > 9 ? dates[1] : ("0" + dates[1])) + "-"
                        + (dates[2] > 9 ? dates[2] : ("0" + dates[2])));
                costomfields.get(position).setM_ItemsValue(mTextView.getText().toString());
                senMsgToActivity();

            }

            @Override
            public void onCancel() {

            }
        })
                .setSelectYear(date.get(0) - 1)
                .setSelectMonth(date.get(1) - 1)
                .setSelectDay(date.get(2) - 1);
        datesDialog = builder.create();
        datesDialog.show();
    }


    /**
     * @param mlist 选择自定义属性选项
     */
    private void showVipDialog(List<String> mlist, final TextView mTextView, final int position1) {
        int j = 0;
        for (int i = 0; i < mlist.size(); i++) {
            if (mTextView.getText().toString().equals(mlist.get(i))) {
                j = i;
            }
        }
        DataPickerDialog.Builder builder = new DataPickerDialog.Builder(mContext);
        chooseDialog = builder.setData(mlist).setSelection(j).setTitle("取消")
                .setOnDataSelectedListener(new DataPickerDialog.OnDataSelectedListener() {
                    @Override
                    public void onDataSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                        costomfields.get(position1).setM_ItemsValue(mTextView.getText().toString());
                        senMsgToActivity();
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    public static List<String> getDateForString(String date) {
        String[] dates = date.split("\\|");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < dates.length; i++) {
            list.add(dates[i]);
        }
        return list;
    }

    private void senMsgToActivity() {
        Message message = Message.obtain();
        message.what = 1;
        message.obj = costomfields;
        if (type == 0) {
            changeHandler.sendMessage(message);
        } else if (type == 1) {
            changeHandler2.sendMessage(message);
        } else if (type == 2) {
            changeHandler3.sendMessage(message);
        } else if (type == 3) {
            changeHandler1.sendMessage(message);
        }

    }

    public void setParam(List<GoodsFiledsBean.DataBean> goodsfields) {
        costomfields = goodsfields;

    }
//    public class ViewHolder1 extends RecyclerView.ViewHolder {
//
//        private TextView etAddFieldTitle;
//        private TextView isFill;
//        private EditText etAddFieldText;
//
//        public ViewHolder1(View itemView) {
//            super(itemView);
//            etAddFieldTitle = itemView.findViewById(R.id.et_add_field_title);
//            isFill = itemView.findViewById(R.id.is_fill);
//            etAddFieldText = itemView.findViewById(R.id.et_add_field_text);
//        }
//    }

    //各个布局的控件资源

    static class ViewHolder1 extends RecyclerView.ViewHolder {


        @Bind(R.id.et_add_field_title)
        TextView etAddFieldTitle;
        @Bind(R.id.is_fill)
        TextView isFill;
        @Bind(R.id.et_add_field_text)
        EditText etAddFieldText;

        public ViewHolder1(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder {
        @Bind(R.id.et_add_field_title)
        TextView etAddFieldTitle;
        @Bind(R.id.is_fill)
        TextView isFill;
        @Bind(R.id.et_add_field_number)
        EditText etAddFieldNumber;

        ViewHolder2(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder3 extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.is_fill)
        TextView isFill;
        @Bind(R.id.tv_choose)
        TextView tvChoose;

        ViewHolder3(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    static class ViewHolder4 extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.is_fill)
        TextView isFill;
        @Bind(R.id.tv_choose)
        TextView tvChoose;
        @Bind(R.id.im_date)
        ImageView imDate;

        ViewHolder4(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder5 extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.is_fill)
        TextView isFill;
        @Bind(R.id.tv_choose)
        TextView tvChoose;
        @Bind(R.id.iv_bg)
        ImageView iv_bg;

        ViewHolder5(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
