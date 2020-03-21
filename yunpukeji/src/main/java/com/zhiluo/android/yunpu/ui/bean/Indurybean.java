package com.zhiluo.android.yunpu.ui.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZPH on 2018-12-06.
 */

public class Indurybean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : [{"GID":100,"IT_Name":"汽车美容","IT_Sort":0},{"GID":101,"IT_Name":"美容美发","IT_Sort":1},{"GID":102,"IT_Name":"服装鞋帽","IT_Sort":2},{"GID":103,"IT_Name":"母婴用品","IT_Sort":3},{"GID":104,"IT_Name":"健身瑜伽","IT_Sort":4},{"GID":105,"IT_Name":"运动球馆","IT_Sort":5},{"GID":106,"IT_Name":"游乐园馆","IT_Sort":6},{"GID":107,"IT_Name":"台球茶楼","IT_Sort":7},{"GID":108,"IT_Name":"推拿养生","IT_Sort":8},{"GID":109,"IT_Name":"休闲浴足","IT_Sort":9},{"GID":110,"IT_Name":"家政服务","IT_Sort":10},{"GID":111,"IT_Name":"干洗店","IT_Sort":11},{"GID":112,"IT_Name":"教育培训","IT_Sort":12},{"GID":113,"IT_Name":"小型零售","IT_Sort":13},{"GID":114,"IT_Name":"化妆品店","IT_Sort":14},{"GID":115,"IT_Name":"其他行业","IT_Sort":15}]
     */

    private boolean success;
    private Object code;
    private String msg;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * GID : 100
         * IT_Name : 汽车美容
         * IT_Sort : 0
         */

        private int GID;
        private String IT_Name;
        private int IT_Sort;

        public int getGID() {
            return GID;
        }

        public void setGID(int GID) {
            this.GID = GID;
        }

        public String getIT_Name() {
            return IT_Name;
        }

        public void setIT_Name(String IT_Name) {
            this.IT_Name = IT_Name;
        }

        public int getIT_Sort() {
            return IT_Sort;
        }

        public void setIT_Sort(int IT_Sort) {
            this.IT_Sort = IT_Sort;
        }
    }
}
