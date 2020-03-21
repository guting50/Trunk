package com.zhiluo.android.yunpu.statistics.rebate.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/7 16:51.
 * 邮箱：137615198@qq.com
 */

public class RebateDetailReportBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":2,"PageSize":20,"DataCount":28,"PageIndex":1,"DataList":[{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"快速消费返利","Number":"0.95","IntegralTime":"2017/8/18 16:17:51","Reamrk":"获得0.95积分。下线9005快速消费,单号：KS1708181617112144","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"快速消费返利","Number":"0.01","IntegralTime":"2017/8/15 11:02:04","Reamrk":"获得0.01积分。下线9005快速消费,单号：KS1708151102021952","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/8/10 10:37:21","Reamrk":"获得200积分。推荐会员9005","Creator":null},{"VCH_Card":"005","VIP_Name":"王菲","Type":"1","Identifying":"商品消费返利","Number":"1.00","IntegralTime":"2017/8/9 15:44:00","Reamrk":"获得1.00积分。下线900商品消费,单号：CZ1708091543423976","Creator":null},{"VCH_Card":"005","VIP_Name":"王菲","Type":"1","Identifying":"商品消费返利","Number":"2.70","IntegralTime":"2017/8/4 14:17:27","Reamrk":"获得2.7积分。下线0123商品消费,单号：SP1708041417039523","Creator":null},{"VCH_Card":"005","VIP_Name":"王菲","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/7/28 11:35:19","Reamrk":"获得200积分。推荐会员0123","Creator":null},{"VCH_Card":"005","VIP_Name":"王菲","Type":"1","Identifying":"商品消费返利","Number":"1.00","IntegralTime":"2017/7/26 15:31:04","Reamrk":"获得1.00积分。下线900商品消费,单号：CZ1707261530579324","Creator":null},{"VCH_Card":"005","VIP_Name":"王菲","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/7/26 11:14:33","Reamrk":"获得200积分。推荐会员900","Creator":null},{"VCH_Card":"002","VIP_Name":"李斯","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/7/25 10:27:33","Reamrk":"获得200积分。推荐会员555","Creator":null},{"VCH_Card":"003","VIP_Name":"周凯","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/7/21 11:35:04","Reamrk":"获得200积分。推荐会员427","Creator":null},{"VCH_Card":"002","VIP_Name":"李斯","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/7/21 11:27:31","Reamrk":"获得200积分。推荐会员543","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"2.00","IntegralTime":"2017/7/14 17:16:14","Reamrk":"获得2.00积分。下线1234商品消费,单号：CZ1707141715593170","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"2.00","IntegralTime":"2017/7/14 17:14:04","Reamrk":"获得2.00积分。下线1234商品消费,单号：CZ1707141713473496","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"12.34","IntegralTime":"2017/7/12 20:52:39","Reamrk":"获得12.34积分。下线1234商品消费,单号：SP1707122052267552","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"1.00","IntegralTime":"2017/7/12 20:50:18","Reamrk":"获得1.00积分。下线1234商品消费,单号：CZ1707122049367079","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"2.00","IntegralTime":"2017/7/12 20:47:19","Reamrk":"获得2.00积分。下线1234商品消费,单号：CZ1707122046366742","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"快速消费返利","Number":"1.50","IntegralTime":"2017/7/12 20:45:38","Reamrk":"获得1.50积分。下线1234快速消费,单号：KS1707122045151589","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"快速消费返利","Number":"0.41","IntegralTime":"2017/7/12 16:09:57","Reamrk":"获得0.41积分。下线1234快速消费,单号：KS1707121609389501","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"0.43","IntegralTime":"2017/7/12 15:25:29","Reamrk":"获得0.43积分。下线1234商品消费,单号：SP1707121525108156","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"0.35","IntegralTime":"2017/7/12 15:23:39","Reamrk":"获得0.35积分。下线1234商品消费,单号：SP1707121522159115","Creator":null}],"StatisticsInfo":{"Monetary":1430.47,"DiscountMoney":null,"AllNumber":28}}
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
         * PageTotal : 2
         * PageSize : 20
         * DataCount : 28
         * PageIndex : 1
         * DataList : [{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"快速消费返利","Number":"0.95","IntegralTime":"2017/8/18 16:17:51","Reamrk":"获得0.95积分。下线9005快速消费,单号：KS1708181617112144","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"快速消费返利","Number":"0.01","IntegralTime":"2017/8/15 11:02:04","Reamrk":"获得0.01积分。下线9005快速消费,单号：KS1708151102021952","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/8/10 10:37:21","Reamrk":"获得200积分。推荐会员9005","Creator":null},{"VCH_Card":"005","VIP_Name":"王菲","Type":"1","Identifying":"商品消费返利","Number":"1.00","IntegralTime":"2017/8/9 15:44:00","Reamrk":"获得1.00积分。下线900商品消费,单号：CZ1708091543423976","Creator":null},{"VCH_Card":"005","VIP_Name":"王菲","Type":"1","Identifying":"商品消费返利","Number":"2.70","IntegralTime":"2017/8/4 14:17:27","Reamrk":"获得2.7积分。下线0123商品消费,单号：SP1708041417039523","Creator":null},{"VCH_Card":"005","VIP_Name":"王菲","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/7/28 11:35:19","Reamrk":"获得200积分。推荐会员0123","Creator":null},{"VCH_Card":"005","VIP_Name":"王菲","Type":"1","Identifying":"商品消费返利","Number":"1.00","IntegralTime":"2017/7/26 15:31:04","Reamrk":"获得1.00积分。下线900商品消费,单号：CZ1707261530579324","Creator":null},{"VCH_Card":"005","VIP_Name":"王菲","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/7/26 11:14:33","Reamrk":"获得200积分。推荐会员900","Creator":null},{"VCH_Card":"002","VIP_Name":"李斯","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/7/25 10:27:33","Reamrk":"获得200积分。推荐会员555","Creator":null},{"VCH_Card":"003","VIP_Name":"周凯","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/7/21 11:35:04","Reamrk":"获得200积分。推荐会员427","Creator":null},{"VCH_Card":"002","VIP_Name":"李斯","Type":"1","Identifying":"推荐会员返利","Number":"200.00","IntegralTime":"2017/7/21 11:27:31","Reamrk":"获得200积分。推荐会员543","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"2.00","IntegralTime":"2017/7/14 17:16:14","Reamrk":"获得2.00积分。下线1234商品消费,单号：CZ1707141715593170","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"2.00","IntegralTime":"2017/7/14 17:14:04","Reamrk":"获得2.00积分。下线1234商品消费,单号：CZ1707141713473496","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"12.34","IntegralTime":"2017/7/12 20:52:39","Reamrk":"获得12.34积分。下线1234商品消费,单号：SP1707122052267552","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"1.00","IntegralTime":"2017/7/12 20:50:18","Reamrk":"获得1.00积分。下线1234商品消费,单号：CZ1707122049367079","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"2.00","IntegralTime":"2017/7/12 20:47:19","Reamrk":"获得2.00积分。下线1234商品消费,单号：CZ1707122046366742","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"快速消费返利","Number":"1.50","IntegralTime":"2017/7/12 20:45:38","Reamrk":"获得1.50积分。下线1234快速消费,单号：KS1707122045151589","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"快速消费返利","Number":"0.41","IntegralTime":"2017/7/12 16:09:57","Reamrk":"获得0.41积分。下线1234快速消费,单号：KS1707121609389501","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"0.43","IntegralTime":"2017/7/12 15:25:29","Reamrk":"获得0.43积分。下线1234商品消费,单号：SP1707121525108156","Creator":null},{"VCH_Card":"001","VIP_Name":"赵三","Type":"1","Identifying":"商品消费返利","Number":"0.35","IntegralTime":"2017/7/12 15:23:39","Reamrk":"获得0.35积分。下线1234商品消费,单号：SP1707121522159115","Creator":null}]
         * StatisticsInfo : {"Monetary":1430.47,"DiscountMoney":null,"AllNumber":28}
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private StatisticsInfoBean StatisticsInfo;
        private List<DataListBean> DataList;

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

        public StatisticsInfoBean getStatisticsInfo() {
            return StatisticsInfo;
        }

        public void setStatisticsInfo(StatisticsInfoBean StatisticsInfo) {
            this.StatisticsInfo = StatisticsInfo;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class StatisticsInfoBean {
            /**
             * Monetary : 1430.47
             * DiscountMoney : null
             * AllNumber : 28
             */

            private double Monetary;
            private Object DiscountMoney;
            private int AllNumber;

            public double getMonetary() {
                return Monetary;
            }

            public void setMonetary(double Monetary) {
                this.Monetary = Monetary;
            }

            public Object getDiscountMoney() {
                return DiscountMoney;
            }

            public void setDiscountMoney(Object DiscountMoney) {
                this.DiscountMoney = DiscountMoney;
            }

            public int getAllNumber() {
                return AllNumber;
            }

            public void setAllNumber(int AllNumber) {
                this.AllNumber = AllNumber;
            }
        }

        public static class DataListBean implements Serializable{
            /**
             * VCH_Card : 001
             * VIP_Name : 赵三
             * Type : 1
             * Identifying : 快速消费返利
             * Number : 0.95
             * IntegralTime : 2017/8/18 16:17:51
             * Reamrk : 获得0.95积分。下线9005快速消费,单号：KS1708181617112144
             * Creator : null
             */

            private String VCH_Card;
            private String VIP_Name;
            private String Type;
            private String Identifying;
            private String Number;
            private String IntegralTime;
            private String Reamrk;
            private Object Creator;

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

            public Object getCreator() {
                return Creator;
            }

            public void setCreator(Object Creator) {
                this.Creator = Creator;
            }
        }
    }
}
