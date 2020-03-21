package com.zhiluo.android.yunpu.popup;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhiluo.android.yunpu.R;
import com.zhiluo.android.yunpu.config.MyApplication;
import com.zhiluo.android.yunpu.goods.consume.Interface.InterfaceBack;
import com.zhiluo.android.yunpu.goods.consume.activity.GoodsConsumeActivity;
import com.zhiluo.android.yunpu.goods.consume.adapter.NewShopRulesAdapter;
import com.zhiluo.android.yunpu.goods.consume.bean.GoodsModelBean;
import com.zhiluo.android.yunpu.goods.consume.bean.ShopEntity;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsCheckResponseByType;
import com.zhiluo.android.yunpu.goods.manager.bean.GoodsDataBean;
import com.zhiluo.android.yunpu.ui.view.CustomToast;

import org.litepal.crud.DataSupport;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.zhiluo.android.yunpu.yslutils.LogUtils.Li;

public class ManyShopPopup extends PopupWindow {

    private View view_bg;
    private TextView tv_close;
    private RecyclerView recyclerView;
    private ImageView iv_image;
    private TextView tvName;
    private TextView tvCode;
    private TextView tvPrice;
    private TextView tvesPrice;
    private LinearLayout ll_esprice;
    private TextView tvStork;
    private TextView tv_shop_num;
    private ImageView iv_reduce, iv_add;
    private Button btn_submit;
    private RelativeLayout rl_delete, rl_shop_info;

    private NewShopRulesAdapter mShopRulesAdapter;

    private List<List<GoodsModelBean>> mManyModelList = new ArrayList<>();
    private List<ShopEntity> mShopList = new ArrayList<>();
    private ShopEntity goodsitem;

    private GoodsCheckResponseByType.DataBean.DataListBean mFlagData;

    protected OnShopClickEvent mClickEvent;

    public interface OnShopClickEvent {
        void OnShopClickEvent();
    }

    /**
     * @param modelList 组装规格数据
     * @param shopList  选中商品的多规格列表
     * @param flagData  多规格临时值,点击确定合并数据
     */
    public ManyShopPopup(Context context, View parent, List<List<GoodsModelBean>> modelList, List<ShopEntity> shopList, GoodsCheckResponseByType.DataBean.DataListBean flagData, boolean mIsZeroSale, OnShopClickEvent clickEvent) {
        View view = View.inflate(context, R.layout.popup_many_shop, null);
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
        mShopList = shopList;
        mFlagData = flagData;
        mClickEvent = clickEvent;

        view_bg = (View) view.findViewById(R.id.view_bg);
        tv_close = (TextView) view.findViewById(R.id.tv_close);
        iv_image = (ImageView) view.findViewById(R.id.iv_image);
        tvName = (TextView) view.findViewById(R.id.tv_item_goods_name);
        tvCode = (TextView) view.findViewById(R.id.tv_item_goods_code);
        tvPrice = (TextView) view.findViewById(R.id.tv_item_goods_price);
        tvesPrice = (TextView) view.findViewById(R.id.tv_item_goods_esprice);
        ll_esprice = (LinearLayout) view.findViewById(R.id.ll_esprice);
        tvStork = (TextView) view.findViewById(R.id.tv_item_goods_stock);
        tv_shop_num = (TextView) view.findViewById(R.id.tv_shop_num);
        iv_reduce = (ImageView) view.findViewById(R.id.iv_reduce);
        iv_add = (ImageView) view.findViewById(R.id.iv_add);
        btn_submit = (Button) view.findViewById(R.id.btn_submit);
        rl_delete = (RelativeLayout) view.findViewById(R.id.rl_delete);
        rl_shop_info = (RelativeLayout) view.findViewById(R.id.rl_shop_info);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        initData(context, modelList, mIsZeroSale);
    }

    private void initData(final Context context, List<List<GoodsModelBean>> modelist, boolean mIsZeroSale) {
        List<List<GoodsModelBean>> flagList = modelist;

        //删除没有的规格
        for (int i = 0; i < flagList.size(); i++) {
            List<GoodsModelBean> beanList = flagList.get(i);
            for (Iterator<GoodsModelBean> iterator = beanList.iterator(); iterator.hasNext(); ) {
                if (!iterator.next().isEnable()) {
                    iterator.remove();
                }
            }
            if (flagList.get(i).size() == 0) {
                flagList.remove(i);
                i--;
            }
        }
        //设置第一个默认选中
        for (List<GoodsModelBean> item : flagList) {
            item.get(0).setChecked(true);
        }

        Li("获取商品多规格列表信息=======manyGoodsData======random:" + new Gson().toJson(flagList));

        mManyModelList = flagList;

        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        mShopRulesAdapter = new NewShopRulesAdapter(context, mManyModelList, new InterfaceBack() {
            @Override
            public void onResponse(Object response) {
                mManyModelList = (List<List<GoodsModelBean>>) response;
                initpop(context);
            }

            @Override
            public void onErrorResponse(Object msg) {

            }
        });
        recyclerView.setAdapter(mShopRulesAdapter);

        initpop(context);

        setListener(context, mIsZeroSale);
    }

    private void setListener(final Context context, final boolean mIsZeroSale) {
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
        //减
        iv_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goodsitem == null) {
                    CustomToast.makeText(context, "无此规格商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.parseInt(tv_shop_num.getText().toString());
                int stockNum = (int) goodsitem.getStock_Number();
                if (mIsZeroSale && stockNum == 0) {
                    CustomToast.makeText(context, "当前库存不足", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (num == 0) {
                    return;
                }
                tv_shop_num.setText((num - 1) + "");
            }
        });
        //加
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goodsitem == null) {
                    CustomToast.makeText(context, "无此规格商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                int stockNum = (int) goodsitem.getStock_Number();
                int num = Integer.parseInt(tv_shop_num.getText().toString()) + 1;
                if (mIsZeroSale) {
                    if (stockNum == 0) {
                        CustomToast.makeText(context, "当前库存不足", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (num > stockNum) {
                        CustomToast.makeText(context, "当前库存不足", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                tv_shop_num.setText(num + "");
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (goodsitem == null) {
                    CustomToast.makeText(context, "无此规格商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.parseInt(tv_shop_num.getText().toString());
                if (num == 0) {
                    CustomToast.makeText(context, "购买数量不能为0", Toast.LENGTH_SHORT).show();
                    return;
                }
                submit();
            }
        });
        rl_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        rl_shop_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /**
     * 确定后合并数据
     */
    private void submit() {

        mFlagData.setGID(goodsitem.getGID());
        mFlagData.setPM_BigImg(goodsitem.getPM_BigImg());
        mFlagData.setSP_GID(goodsitem.getSP_GID());
        mFlagData.setPM_Modle(goodsitem.getPM_Modle());
        mFlagData.setPM_UnitPrice(goodsitem.getPM_UnitPrice());

        int num = Integer.parseInt(tv_shop_num.getText().toString());
        mFlagData.setNum(num);
        Li("获取商品多规格列表信息=======manyGoodsData======random:" + new Gson().toJson(goodsitem));
        GoodsCheckResponseByType.DataBean.DataListBean newData = new GoodsCheckResponseByType.DataBean.DataListBean();
        setNewData(newData);
        Li("=======mFlagData======random:" + new Gson().toJson(mFlagData));
        //取购物车列表
        List<GoodsDataBean> findList = DataSupport.where("gid==?", newData.getGID()).find(GoodsDataBean.class);

        //有数据
        if (findList != null && findList.size() > 0) {
            GoodsDataBean flag = findList.get(0);
            flag.setNum(num);
            flag.save();
            //购物车修改数量
            for (int i = 0, len = GoodsConsumeActivity.mHaveChoosedGoodList.size(); i < len; i++) {
                if (GoodsConsumeActivity.mHaveChoosedGoodList.get(i).getGID().equals(newData.getGID())) {
                    GoodsConsumeActivity.mHaveChoosedGoodList.get(i).setNum(num);
                    break;
                }
            }
        } else {
            GoodsDataBean beanLitePal = new GoodsDataBean();
            beanLitePal.setNum(num);
            beanLitePal.setGid(newData.getGID());
            beanLitePal.setGroupId(newData.getPM_GroupGID());
            beanLitePal.save();
            newData.setNum(num);
            //添加购物车
            GoodsConsumeActivity.mHaveChoosedGoodList.add(newData);
        }

        mClickEvent.OnShopClickEvent();
        dismiss();
    }

    private void initpop(Context context) {
        goodsitem = null;
        StringBuilder modelName = new StringBuilder("");
        for (int j = 0; j < mManyModelList.size(); j++) {
            for (int k = 0; k < mManyModelList.get(j).size(); k++) {
                if (mManyModelList.get(j).get(k).isChecked()) {
                    modelName.append(mManyModelList.get(j).get(k).getPM_Properties() + "|");
                }
            }
        }
        String str = String.valueOf(modelName);
        if (str.substring(str.length() - 1, str.length()).equals("|")) {
            str = str.substring(0, str.length() - 1);
        }

        for (int i = 0; i < mShopList.size(); i++) {
            if (str.equals(mShopList.get(i).getPM_Modle())) {
                goodsitem = mShopList.get(i);
            }
        }

        String gidFlag = "no";
        if (goodsitem != null) {
            gidFlag = goodsitem.getGID();
            //头像
            String imageUrl = obtainUrl(noNullHandle(goodsitem.getPM_BigImg()).toString());
            Glide.with(context).load(imageUrl).into(iv_image);

            tvName.setText(goodsitem.getPM_Name() + "");
            tvCode.setText(goodsitem.getPM_Code() + "");


            if (noNullHandle(goodsitem.getPM_IsDiscount()).toString().equals("1")) {
                ll_esprice.setVisibility(View.VISIBLE);
                if (!noNullHandle(goodsitem.getPM_SpecialOfferMoney()).toString().equals("0.0") && goodsitem.getPM_SpecialOfferMoney() != -1) {
                    //无最低折扣
                    tvesPrice.setText("特：" + goodsitem.getPM_SpecialOfferMoney());
                    tvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中划线
                    tvPrice.setTextColor(context.getResources().getColor(R.color.a5a5a5));

                } else if (!noNullHandle(goodsitem.getPM_SpecialOfferValue()).toString().equals("0.0")) {
                    //有特价折扣
                    if (noNullHandle(goodsitem.getPM_MinDisCountValue()).toString().equals("0.0")) {
                        //无最低折扣
                        tvesPrice.setText("特：" + twoNum(multiply(goodsitem.getPM_UnitPrice() + "", goodsitem.getPM_SpecialOfferValue() + "")));
                    } else {
                        //有最低折扣
                        if (goodsitem.getPM_SpecialOfferValue() > goodsitem.getPM_MinDisCountValue()) {
                            tvesPrice.setText("特：" + twoNum(multiply(goodsitem.getPM_UnitPrice() + "", goodsitem.getPM_SpecialOfferValue() + "")));
                        } else {
                            tvesPrice.setText("特：" + twoNum(multiply(goodsitem.getPM_UnitPrice() + "", goodsitem.getPM_MinDisCountValue() + "")));
                        }
                    }
                    tvPrice.setTextColor(context.getResources().getColor(R.color.a5a5a5));
                    tvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中划线
                } else {
                    //无特价折扣
                    if (!noNullHandle(goodsitem.getPM_MemPrice()).toString().equals("")) {
                        //有会员价
                        tvesPrice.setText("会：" + twoNum(noNullHandle(goodsitem.getPM_MemPrice()).toString()));
                        ll_esprice.setVisibility(View.VISIBLE);
                    } else {
                        tvesPrice.setText("");
                        ll_esprice.setVisibility(View.GONE);
                    }
                    tvPrice.getPaint().setFlags(0 | Paint.ANTI_ALIAS_FLAG); // 取消设置的的划线
                    tvPrice.setTextColor(context.getResources().getColor(R.color.text60));
                }
            } else {
                if (!noNullHandle(goodsitem.getPM_MemPrice()).toString().equals("")) {
                    tvesPrice.setText("会：" + twoNum(noNullHandle(goodsitem.getPM_MemPrice()).toString()));
                    ll_esprice.setVisibility(View.VISIBLE);
                } else {
                    tvesPrice.setText("");
                    ll_esprice.setVisibility(View.GONE);
                }
                tvPrice.getPaint().setFlags(0 | Paint.ANTI_ALIAS_FLAG); // 取消设置的的划线
                tvPrice.setTextColor(context.getResources().getColor(R.color.text60));
            }

            tvPrice.setText("售：" + twoNum(goodsitem.getPM_UnitPrice() + ""));
//            tvesPrice.setText("特："+  twoNum(goodsitem.getPM_UnitPrice() + ""));

            //库存
            if (goodsitem.getPM_Metering() != null) {
                tvStork.setText(String.valueOf(goodsitem.getStock_Number()) + goodsitem.getPM_Metering());
            } else {
                tvStork.setText(goodsitem.getStock_Number() + "");
            }
        } else {
            iv_image.setImageResource(R.mipmap.messge_nourl);
            tvName.setText("无此规格商品");
            tvCode.setVisibility(View.GONE);
            tvPrice.setText("¥0.00");
            tvStork.setText("0");
        }

        //取购物车列表
        List<GoodsDataBean> findList = DataSupport.where("gid==?", gidFlag).find(GoodsDataBean.class);
        //有数据
        if (findList != null && findList.size() > 0) {
            tv_shop_num.setText(findList.get(0).getNum() + "");
        } else {
            tv_shop_num.setText("0");
        }
    }

    public static String twoNum(String num) {
        DecimalFormat df = new DecimalFormat("0.00");
        return num == null || num.equals("0") || num.equals("0.0") || num.equals("") ? df.format(0.00) : df.format(Double.parseDouble(num));
    }

    public Object noNullHandle(Object ob) {
        if (null == ob) {
            if (ob instanceof Integer) {
                return 0;
            } else if (ob instanceof String) {
                return "";
            } else if (ob instanceof Double) {
                return 0.0;
            } else if (ob instanceof Boolean) {
                return false;
            } else {
                return "";
            }
        } else {
            return ob;
        }
    }

    /**
     * 提供精确的乘法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数的数学积，以字符串格式返回
     */
    public String multiply(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(replaceBlank(v1));
        BigDecimal b2 = new BigDecimal(replaceBlank(v2));
        return b1.multiply(b2).toString();
    }

    /**
     * 去除空格
     *
     * @param str
     * @return
     */
    public String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public String obtainUrl(String url) {
        if (url.startsWith("/")) {
            return MyApplication.BASE_URL + url;
        } else {
            return url;
        }
    }

    private void setNewData(GoodsCheckResponseByType.DataBean.DataListBean bean) {
        bean.setFildsId(mFlagData.getFildsId());
        bean.setFildsValue(mFlagData.getFildsValue());
        bean.setSM_Name(mFlagData.getSM_Name());
        bean.setStock_Number(goodsitem.getStock_Number());
        bean.setPM_PurchasePrice(goodsitem.getPM_PurchasePrice());
        bean.setPM_MemPrice(goodsitem.getPM_MemPrice());
        bean.setPM_IsDiscount(goodsitem.getPM_IsDiscount());
        bean.setPM_IsPoint(goodsitem.getPM_IsPoint());
        bean.setPM_IsService(goodsitem.getPM_IsService());
        bean.setPM_IsServiceName(mFlagData.getPM_IsServiceName());
        bean.setGID(goodsitem.getGID());
        bean.setPT_ID(goodsitem.getPT_ID());
        bean.setPM_Code(goodsitem.getPM_Code());
        bean.setPM_Name(goodsitem.getPM_Name());
        bean.setPM_SimpleCode(goodsitem.getPM_SimpleCode());
        bean.setPM_Metering(goodsitem.getPM_Metering());
        bean.setPM_UnitPrice(goodsitem.getPM_UnitPrice());
        bean.setPM_UpdateTime(mFlagData.getPM_UpdateTime());
        bean.setPM_Remark(mFlagData.getPM_Remark());
        bean.setPM_Creator(mFlagData.getPM_Creator());
        bean.setPM_BigImg(goodsitem.getPM_BigImg());
        bean.setPM_SmallImg(goodsitem.getPM_SmallImg());
        bean.setPM_Detail(mFlagData.getPM_Detail());
        bean.setPM_Description(goodsitem.getPM_Description());
        bean.setPM_Modle(goodsitem.getPM_Modle());
        bean.setPM_Brand(goodsitem.getPM_Brand());
        bean.setCY_GID(mFlagData.getCY_GID());
        bean.setPM_SynType(mFlagData.getPM_SynType());
        bean.setPM_SpecialOfferMoney(goodsitem.getPM_SpecialOfferMoney());
        bean.setPM_SpecialOfferValue(goodsitem.getPM_SpecialOfferValue());
        bean.setPM_MinDisCountValue(goodsitem.getPM_MinDisCountValue());
        bean.setPM_FixedIntegralValue(goodsitem.getPM_FixedIntegralValue());
        bean.setPM_StockPoliceValue(mFlagData.getPM_StockPoliceValue());
        bean.setPM_DeleteSign(mFlagData.getPM_DeleteSign());
        bean.setPT_Name(goodsitem.getPT_Name());
        bean.setSM_ID(goodsitem.getSM_ID());
        bean.setPM_Repertory(goodsitem.getPM_Repertory());
        bean.setSP_GID(goodsitem.getSP_GID());
        bean.setSP_Message(mFlagData.getSP_Message());
        bean.setPercent(mFlagData.getPercent());
        bean.setEachMoney(mFlagData.getEachMoney());
        bean.setZHMoney(mFlagData.getZHMoney());
        bean.setEmployeeValue(mFlagData.getEmployeeValue());
        bean.setNow_Stock(mFlagData.getNow_Stock());
        bean.setPM_GroupGID(goodsitem.getPM_GroupGID());
        bean.setGroupCount(mFlagData.getGroupCount());
    }
}
