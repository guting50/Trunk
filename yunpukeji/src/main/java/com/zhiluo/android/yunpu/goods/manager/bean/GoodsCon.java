package com.zhiluo.android.yunpu.goods.manager.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/18.
 */
public class GoodsCon implements Serializable
{
    public int num;
    public String goodsname;
    public String goodscode;

    public int getPosition()
    {
        return position;
    }

    public void setPosition(int position)
    {
        this.position = position;
    }

    public GoodsCon(int num, String goodsname, String goodscode, String ptgid, String pmnane, String pmgid, String
            metering, String simcode, String pmgdescr, String model, String brand, double goodsprice, int position)
    {
        this.num = num;
        this.goodsname = goodsname;
        this.goodscode = goodscode;
        this.ptgid = ptgid;
        this.pmnane = pmnane;
        this.pmgid = pmgid;
        this.metering = metering;
        this.simcode = simcode;
        this.pmgdescr = pmgdescr;
        this.model = model;
        this.brand = brand;
        this.goodsprice = goodsprice;
        this.position = position;
    }

    public String ptgid;
    public String pmnane;
    public String pmgid;
    public String metering;
    public String simcode;
    public String pmgdescr;
    public String model;
    public String brand;
    public double goodsprice;
    public int position;

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GoodsCon goodsCon = (GoodsCon) o;

        if (num != goodsCon.num)
            return false;
        if (Double.compare(goodsCon.goodsprice, goodsprice) != 0)
            return false;
        if (goodsname != null ? !goodsname.equals(goodsCon.goodsname) : goodsCon.goodsname != null)
            return false;
        return goodscode != null ? goodscode.equals(goodsCon.goodscode) : goodsCon.goodscode == null;
    }

    @Override
    public int hashCode()
    {
        int result;
        long temp;
        result = num;
        result = 31 * result + (goodsname != null ? goodsname.hashCode() : 0);
        result = 31 * result + (goodscode != null ? goodscode.hashCode() : 0);
        temp = Double.doubleToLongBits(goodsprice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public int getNum()
    {

        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public String getGoodsname()
    {
        return goodsname;
    }

    public void setGoodsname(String goodsname)
    {
        this.goodsname = goodsname;
    }

    public String getGoodscode()
    {
        return goodscode;
    }

    public void setGoodscode(String goodscode)
    {
        this.goodscode = goodscode;
    }

    public double getGoodsprice()
    {
        return goodsprice;
    }

    public void setGoodsprice(double goodsprice)
    {
        this.goodsprice = goodsprice;
    }

    @Override
    public String toString()
    {
        return "GoodsCon{" +
                "num=" + num +
                ", goodsname='" + goodsname + '\'' +
                ", goodscode='" + goodscode + '\'' +
                ", ptgid='" + ptgid + '\'' +
                ", pmnane='" + pmnane + '\'' +
                ", pmgid='" + pmgid + '\'' +
                ", metering='" + metering + '\'' +
                ", simcode='" + simcode + '\'' +
                ", pmgdescr='" + pmgdescr + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", goodsprice=" + goodsprice +
                '}';
    }

    public String getPtgid()
    {
        return ptgid;
    }

    public void setPtgid(String ptgid)
    {
        this.ptgid = ptgid;
    }

    public String getPmnane()
    {
        return pmnane;
    }

    public void setPmnane(String pmnane)
    {
        this.pmnane = pmnane;
    }

    public String getPmgid()
    {
        return pmgid;
    }

    public void setPmgid(String pmgid)
    {
        this.pmgid = pmgid;
    }

    public String getMetering()
    {
        return metering;
    }

    public void setMetering(String metering)
    {
        this.metering = metering;
    }

    public String getSimcode()
    {
        return simcode;
    }

    public void setSimcode(String simcode)
    {
        this.simcode = simcode;
    }

    public String getPmgdescr()
    {
        return pmgdescr;
    }

    public void setPmgdescr(String pmgdescr)
    {
        this.pmgdescr = pmgdescr;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public String getBrand()
    {
        return brand;
    }

    public void setBrand(String brand)
    {
        this.brand = brand;
    }
}
