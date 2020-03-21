package com.zhiluo.android.yunpu.goods.manager.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/26 18:31.
 * 邮箱：137615198@qq.com
 */

public class ProviderBean implements Serializable {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":20,"DataCount":3,"PageIndex":1,"DataList":[{"SL_Province":"四川省","SL_City":"成都市","SL_Disctrict":"锦江区","SL_Brand":"品牌","GID":"06e52fd8-8c47-469f-953a-91bfc51e7eae","SP_GID":null,"ST_GID":null,"SE_GID":null,"SL_Name":"成都早餐1","SL_Code":null,"SL_Capital":null,"SL_Tariff":null,"SL_AccountNo":null,"SL_Addr":"四川省成都市双流县","SL_Website":null,"SL_Representative":null,"SL_Banck":null,"SL_FixedPhone":null,"SL_Fax":null,"SL_ZipCode":null,"SL_Credit":null,"SL_Remark":"备注","SL_Creator":"销售001","SL_CreateTime":"2019-03-05 10:09:04","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SL_IsAdmin":null,"SL_DefaultPhone":"13698799645","SL_DefaultName":"阿达","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SL_QQ":null,"SL_WeChat":null},{"SL_Province":null,"SL_City":null,"SL_Disctrict":null,"SL_Brand":null,"GID":"84dd08db-d5cb-47c5-a4d3-a812a9864cae","SP_GID":null,"ST_GID":null,"SE_GID":null,"SL_Name":"二毛子","SL_Code":null,"SL_Capital":null,"SL_Tariff":null,"SL_AccountNo":null,"SL_Addr":"","SL_Website":null,"SL_Representative":null,"SL_Banck":null,"SL_FixedPhone":null,"SL_Fax":null,"SL_ZipCode":null,"SL_Credit":null,"SL_Remark":null,"SL_Creator":"销售001","SL_CreateTime":"2018-09-12 15:18:36","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SL_IsAdmin":null,"SL_DefaultPhone":"13888888888","SL_DefaultName":"小毛","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","SL_QQ":null,"SL_WeChat":null},{"SL_Province":null,"SL_City":null,"SL_Disctrict":null,"SL_Brand":null,"GID":"3f4379b2-1ff9-4f0e-9b2c-13864e452f21","SP_GID":null,"ST_GID":null,"SE_GID":null,"SL_Name":"小狗子","SL_Code":null,"SL_Capital":null,"SL_Tariff":null,"SL_AccountNo":null,"SL_Addr":"","SL_Website":null,"SL_Representative":null,"SL_Banck":null,"SL_FixedPhone":null,"SL_Fax":null,"SL_ZipCode":null,"SL_Credit":null,"SL_Remark":null,"SL_Creator":"销售001","SL_CreateTime":"2018-09-12 15:23:59","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SL_IsAdmin":null,"SL_DefaultPhone":"13666666666","SL_DefaultName":"小狗","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","SL_QQ":null,"SL_WeChat":null}],"StatisticsInfo":null}
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

    public static class DataBean implements Serializable{
        /**
         * PageTotal : 1
         * PageSize : 20
         * DataCount : 3
         * PageIndex : 1
         * DataList : [{"SL_Province":"四川省","SL_City":"成都市","SL_Disctrict":"锦江区","SL_Brand":"品牌","GID":"06e52fd8-8c47-469f-953a-91bfc51e7eae","SP_GID":null,"ST_GID":null,"SE_GID":null,"SL_Name":"成都早餐1","SL_Code":null,"SL_Capital":null,"SL_Tariff":null,"SL_AccountNo":null,"SL_Addr":"四川省成都市双流县","SL_Website":null,"SL_Representative":null,"SL_Banck":null,"SL_FixedPhone":null,"SL_Fax":null,"SL_ZipCode":null,"SL_Credit":null,"SL_Remark":"备注","SL_Creator":"销售001","SL_CreateTime":"2019-03-05 10:09:04","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SL_IsAdmin":null,"SL_DefaultPhone":"13698799645","SL_DefaultName":"阿达","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"jll2","SL_QQ":null,"SL_WeChat":null},{"SL_Province":null,"SL_City":null,"SL_Disctrict":null,"SL_Brand":null,"GID":"84dd08db-d5cb-47c5-a4d3-a812a9864cae","SP_GID":null,"ST_GID":null,"SE_GID":null,"SL_Name":"二毛子","SL_Code":null,"SL_Capital":null,"SL_Tariff":null,"SL_AccountNo":null,"SL_Addr":"","SL_Website":null,"SL_Representative":null,"SL_Banck":null,"SL_FixedPhone":null,"SL_Fax":null,"SL_ZipCode":null,"SL_Credit":null,"SL_Remark":null,"SL_Creator":"销售001","SL_CreateTime":"2018-09-12 15:18:36","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SL_IsAdmin":null,"SL_DefaultPhone":"13888888888","SL_DefaultName":"小毛","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","SL_QQ":null,"SL_WeChat":null},{"SL_Province":null,"SL_City":null,"SL_Disctrict":null,"SL_Brand":null,"GID":"3f4379b2-1ff9-4f0e-9b2c-13864e452f21","SP_GID":null,"ST_GID":null,"SE_GID":null,"SL_Name":"小狗子","SL_Code":null,"SL_Capital":null,"SL_Tariff":null,"SL_AccountNo":null,"SL_Addr":"","SL_Website":null,"SL_Representative":null,"SL_Banck":null,"SL_FixedPhone":null,"SL_Fax":null,"SL_ZipCode":null,"SL_Credit":null,"SL_Remark":null,"SL_Creator":"销售001","SL_CreateTime":"2018-09-12 15:23:59","CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SL_IsAdmin":null,"SL_DefaultPhone":"13666666666","SL_DefaultName":"小狗","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","SM_Name":"默认店铺","SL_QQ":null,"SL_WeChat":null}]
         * StatisticsInfo : null
         */

        private int PageTotal;
        private int PageSize;
        private int DataCount;
        private int PageIndex;
        private Object StatisticsInfo;
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

        public Object getStatisticsInfo() {
            return StatisticsInfo;
        }

        public void setStatisticsInfo(Object StatisticsInfo) {
            this.StatisticsInfo = StatisticsInfo;
        }

        public List<DataListBean> getDataList() {
            return DataList;
        }

        public void setDataList(List<DataListBean> DataList) {
            this.DataList = DataList;
        }

        public static class DataListBean implements Serializable{
            /**
             * SL_Province : 四川省
             * SL_City : 成都市
             * SL_Disctrict : 锦江区
             * SL_Brand : 品牌
             * GID : 06e52fd8-8c47-469f-953a-91bfc51e7eae
             * SP_GID : null
             * ST_GID : null
             * SE_GID : null
             * SL_Name : 成都早餐1
             * SL_Code : null
             * SL_Capital : null
             * SL_Tariff : null
             * SL_AccountNo : null
             * SL_Addr : 四川省成都市双流县
             * SL_Website : null
             * SL_Representative : null
             * SL_Banck : null
             * SL_FixedPhone : null
             * SL_Fax : null
             * SL_ZipCode : null
             * SL_Credit : null
             * SL_Remark : 备注
             * SL_Creator : 销售001
             * SL_CreateTime : 2019-03-05 10:09:04
             * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
             * SL_IsAdmin : null
             * SL_DefaultPhone : 13698799645
             * SL_DefaultName : 阿达
             * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
             * SM_Name : jll2
             * SL_QQ : null
             * SL_WeChat : null
             */

            private String SL_Province;
            private String SL_City;
            private String SL_Disctrict;
            private String SL_Brand;
            private String GID;
            private Object SP_GID;
            private Object ST_GID;
            private Object SE_GID;
            private String SL_Name;
            private Object SL_Code;
            private Object SL_Capital;
            private Object SL_Tariff;
            private Object SL_AccountNo;
            private String SL_Addr;
            private Object SL_Website;
            private String SL_Representative;
            private String SL_Banck;
            private String SL_FixedPhone;
            private String SL_Fax;
            private Object SL_ZipCode;
            private Object SL_Credit;
            private String SL_Remark;
            private String SL_Creator;
            private String SL_CreateTime;
            private String CY_GID;
            private Object SL_IsAdmin;
            private String SL_DefaultPhone;
            private String SL_DefaultName;
            private String SM_GID;
            private String SM_Name;
            private String SL_QQ;
            private String SL_WeChat;

            public String getSL_Province() {
                return SL_Province;
            }

            public void setSL_Province(String SL_Province) {
                this.SL_Province = SL_Province;
            }

            public String getSL_City() {
                return SL_City;
            }

            public void setSL_City(String SL_City) {
                this.SL_City = SL_City;
            }

            public String getSL_Disctrict() {
                return SL_Disctrict;
            }

            public void setSL_Disctrict(String SL_Disctrict) {
                this.SL_Disctrict = SL_Disctrict;
            }

            public String getSL_Brand() {
                return SL_Brand;
            }

            public void setSL_Brand(String SL_Brand) {
                this.SL_Brand = SL_Brand;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public Object getSP_GID() {
                return SP_GID;
            }

            public void setSP_GID(Object SP_GID) {
                this.SP_GID = SP_GID;
            }

            public Object getST_GID() {
                return ST_GID;
            }

            public void setST_GID(Object ST_GID) {
                this.ST_GID = ST_GID;
            }

            public Object getSE_GID() {
                return SE_GID;
            }

            public void setSE_GID(Object SE_GID) {
                this.SE_GID = SE_GID;
            }

            public String getSL_Name() {
                return SL_Name;
            }

            public void setSL_Name(String SL_Name) {
                this.SL_Name = SL_Name;
            }

            public Object getSL_Code() {
                return SL_Code;
            }

            public void setSL_Code(Object SL_Code) {
                this.SL_Code = SL_Code;
            }

            public Object getSL_Capital() {
                return SL_Capital;
            }

            public void setSL_Capital(Object SL_Capital) {
                this.SL_Capital = SL_Capital;
            }

            public Object getSL_Tariff() {
                return SL_Tariff;
            }

            public void setSL_Tariff(Object SL_Tariff) {
                this.SL_Tariff = SL_Tariff;
            }

            public Object getSL_AccountNo() {
                return SL_AccountNo;
            }

            public void setSL_AccountNo(Object SL_AccountNo) {
                this.SL_AccountNo = SL_AccountNo;
            }

            public String getSL_Addr() {
                return SL_Addr;
            }

            public void setSL_Addr(String SL_Addr) {
                this.SL_Addr = SL_Addr;
            }

            public Object getSL_Website() {
                return SL_Website;
            }

            public void setSL_Website(Object SL_Website) {
                this.SL_Website = SL_Website;
            }

            public String getSL_Representative() {
                return SL_Representative;
            }

            public void setSL_Representative(String SL_Representative) {
                this.SL_Representative = SL_Representative;
            }

            public String getSL_Banck() {
                return SL_Banck;
            }

            public void setSL_Banck(String SL_Banck) {
                this.SL_Banck = SL_Banck;
            }

            public String getSL_FixedPhone() {
                return SL_FixedPhone;
            }

            public void setSL_FixedPhone(String SL_FixedPhone) {
                this.SL_FixedPhone = SL_FixedPhone;
            }

            public String getSL_Fax() {
                return SL_Fax;
            }

            public void setSL_Fax(String SL_Fax) {
                this.SL_Fax = SL_Fax;
            }

            public Object getSL_ZipCode() {
                return SL_ZipCode;
            }

            public void setSL_ZipCode(Object SL_ZipCode) {
                this.SL_ZipCode = SL_ZipCode;
            }

            public Object getSL_Credit() {
                return SL_Credit;
            }

            public void setSL_Credit(Object SL_Credit) {
                this.SL_Credit = SL_Credit;
            }

            public String getSL_Remark() {
                return SL_Remark;
            }

            public void setSL_Remark(String SL_Remark) {
                this.SL_Remark = SL_Remark;
            }

            public String getSL_Creator() {
                return SL_Creator;
            }

            public void setSL_Creator(String SL_Creator) {
                this.SL_Creator = SL_Creator;
            }

            public String getSL_CreateTime() {
                return SL_CreateTime;
            }

            public void setSL_CreateTime(String SL_CreateTime) {
                this.SL_CreateTime = SL_CreateTime;
            }

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
                this.CY_GID = CY_GID;
            }

            public Object getSL_IsAdmin() {
                return SL_IsAdmin;
            }

            public void setSL_IsAdmin(Object SL_IsAdmin) {
                this.SL_IsAdmin = SL_IsAdmin;
            }

            public String getSL_DefaultPhone() {
                return SL_DefaultPhone;
            }

            public void setSL_DefaultPhone(String SL_DefaultPhone) {
                this.SL_DefaultPhone = SL_DefaultPhone;
            }

            public String getSL_DefaultName() {
                return SL_DefaultName;
            }

            public void setSL_DefaultName(String SL_DefaultName) {
                this.SL_DefaultName = SL_DefaultName;
            }

            public String getSM_GID() {
                return SM_GID;
            }

            public void setSM_GID(String SM_GID) {
                this.SM_GID = SM_GID;
            }

            public String getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(String SM_Name) {
                this.SM_Name = SM_Name;
            }

            public String getSL_QQ() {
                return SL_QQ;
            }

            public void setSL_QQ(String SL_QQ) {
                this.SL_QQ = SL_QQ;
            }

            public String getSL_WeChat() {
                return SL_WeChat;
            }

            public void setSL_WeChat(String SL_WeChat) {
                this.SL_WeChat = SL_WeChat;
            }
        }
    }
}
