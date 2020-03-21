package com.zhiluo.android.yunpu.statistics.account.bean;

import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/7 11:04.
 * 邮箱：137615198@qq.com
 */

public class IntegralDetailReportBean {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":29,"PageSize":10,"DataCount":289,"PageIndex":1,"DataList":[{"VCH_Card":"155544266977","VIP_Name":"张","Type":"1","Identifying":"快速消费","Number":"100.00","IntegralTime":"2018-07-31 10:37:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18321","VIP_Name":"测试","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18021","VIP_Name":"啦啦啦","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18051","VIP_Name":"一哈哈哈哈","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18351","VIP_Name":"测试","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"17991","VIP_Name":"测试短信","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18081","VIP_Name":"第三方","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18371","VIP_Name":"zyh666","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18091","VIP_Name":"考虑考虑解决","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18501","VIP_Name":"吴新洲","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"}],"StatisticsInfo":[{"Integral":31492.8,"Type":1}]}
     */

    private boolean success;
    private Object code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * PageTotal : 29
         * PageSize : 10
         * DataCount : 289
         * PageIndex : 1
         * DataList : [{"VCH_Card":"155544266977","VIP_Name":"张","Type":"1","Identifying":"快速消费","Number":"100.00","IntegralTime":"2018-07-31 10:37:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18321","VIP_Name":"测试","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18021","VIP_Name":"啦啦啦","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18051","VIP_Name":"一哈哈哈哈","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18351","VIP_Name":"测试","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"17991","VIP_Name":"测试短信","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18081","VIP_Name":"第三方","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18371","VIP_Name":"zyh666","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18091","VIP_Name":"考虑考虑解决","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"},{"VCH_Card":"18501","VIP_Name":"吴新洲","Type":"1","Identifying":"新增会员","Number":"100.00","IntegralTime":"2018-07-26 10:38:20","Reamrk":"获得100.00积分","Creator":"销售001"}]
         * StatisticsInfo : [{"Integral":31492.8,"Type":1}]
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private List<DataListBean> DataList;
        private List<StatisticsInfoBean> StatisticsInfo;

        public int getPageTotal() {
            return PageTotal;
        }

        public void setPageTotal(int PageTotal) {
            this.PageTotal = PageTotal;
        }

        public int getPageSize() {
            return PageSize;
        }

        public void setPageSize(int PageSize) {
            this.PageSize = PageSize;
        }

        public int getDataCount() {
            return DataCount;
        }

        public void setDataCount(int DataCount) {
            this.DataCount = DataCount;
        }

        public int getPageIndex() {
            return PageIndex;
        }

        public void setPageIndex(int PageIndex) {
            this.PageIndex = PageIndex;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public List<StatisticsInfoBean> getStatisticsInfo() {
            return StatisticsInfo;
        }

        public void setStatisticsInfo(List<StatisticsInfoBean> StatisticsInfo) {
            this.StatisticsInfo = StatisticsInfo;
        }

        public static class DataListBean {
            /**
             * VCH_Card : 155544266977
             * VIP_Name : 张
             * Type : 1
             * Identifying : 快速消费
             * Number : 100.00
             * IntegralTime : 2018-07-31 10:37:20
             * Reamrk : 获得100.00积分
             * Creator : 销售001
             */

            private String VCH_Card;
            private String VIP_Name;
            private String Type;
            private String Identifying;
            private String Number;
            private String IntegralTime;
            private String Reamrk;
            private String Creator;

            public String getVCH_Card() {
                return VCH_Card;
            }

            public void setVCH_Card(String VCH_Card) {
                this.VCH_Card = VCH_Card;
            }

            public String getVIP_Name() {
                return VIP_Name;
            }

            public void setVIP_Name(String VIP_Name) {
                this.VIP_Name = VIP_Name;
            }

            public String getType() {
                return Type;
            }

            public void setType(String Type) {
                this.Type = Type;
            }

            public String getIdentifying() {
                return Identifying;
            }

            public void setIdentifying(String Identifying) {
                this.Identifying = Identifying;
            }

            public String getNumber() {
                return Number;
            }

            public void setNumber(String Number) {
                this.Number = Number;
            }

            public String getIntegralTime() {
                return IntegralTime;
            }

            public void setIntegralTime(String IntegralTime) {
                this.IntegralTime = IntegralTime;
            }

            public String getReamrk() {
                return Reamrk;
            }

            public void setReamrk(String Reamrk) {
                this.Reamrk = Reamrk;
            }

            public String getCreator() {
                return Creator;
            }

            public void setCreator(String Creator) {
                this.Creator = Creator;
            }
        }

        public static class StatisticsInfoBean {
            /**
             * Integral : 31492.8
             * Type : 1.0
             */

            private double Integral;
            private double Type;

            public double getIntegral() {
                return Integral;
            }

            public void setIntegral(double Integral) {
                this.Integral = Integral;
            }

            public double getType() {
                return Type;
            }

            public void setType(double Type) {
                this.Type = Type;
            }
        }
    }
}
