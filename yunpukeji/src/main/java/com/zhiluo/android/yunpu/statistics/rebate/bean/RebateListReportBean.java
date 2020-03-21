package com.zhiluo.android.yunpu.statistics.rebate.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：罗咏哲 on 2017/9/7 16:36.
 * 邮箱：137615198@qq.com
 */

public class RebateListReportBean {

    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"PageTotal":1,"PageSize":1000,"DataCount":6,"PageIndex":1,"DataList":[{"GID":null,"VG_GID":"d0f28778-f3b8-40a9-80b9-b4d6ded0e5f7","VT_Code":null,"VIP_Name":"余文乐","VIP_CellPhone":"18631214444","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"001","VIP_RecommendName":"赵三","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"9005","VCH_Fee":0,"VG_Name":"普通会员","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":199.1,"MA_AvailableIntegral":195.9,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-08-10 10:37:21","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null},{"GID":null,"VG_GID":"d0f28778-f3b8-40a9-80b9-b4d6ded0e5f7","VT_Code":null,"VIP_Name":"张杰","VIP_CellPhone":"13980563218","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"005","VIP_RecommendName":"王菲","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"0123","VCH_Fee":0,"VG_Name":"普通会员","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":100,"MA_AvailableIntegral":170,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-07-28 11:35:19","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null},{"GID":null,"VG_GID":"d0f28778-f3b8-40a9-80b9-b4d6ded0e5f7","VT_Code":null,"VIP_Name":"李琪","VIP_CellPhone":"15685858552","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":1,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"005","VIP_RecommendName":"王菲","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"900","VCH_Fee":0,"VG_Name":"普通会员","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":200,"MA_AvailableIntegral":300,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-07-26 11:14:32","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null},{"GID":null,"VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","VT_Code":null,"VIP_Name":"王芳方","VIP_CellPhone":"15845525874","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":1,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"003","VIP_RecommendName":"周凯","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"427","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":100,"MA_AvailableIntegral":100,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-07-21 11:35:03","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null},{"GID":null,"VG_GID":"bd9a443b-1337-4f6b-bd5b-4ed5bc416b53","VT_Code":null,"VIP_Name":"新增会员测试","VIP_CellPhone":"15865321111","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":1,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"002","VIP_RecommendName":"李斯","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"543","VCH_Fee":0,"VG_Name":"白金会员","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":200,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-07-21 11:27:31","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null},{"GID":null,"VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","VT_Code":null,"VIP_Name":"于蓝","VIP_CellPhone":"15965656565","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":1,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"001","VIP_RecommendName":"赵三","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"1234","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":875,"MA_AvailableIntegral":601.54,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-07-12 11:20:45","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null}],"StatisticsInfo":null}
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
         * PageTotal : 1
         * PageSize : 1000
         * DataCount : 6
         * PageIndex : 1
         * DataList : [{"GID":null,"VG_GID":"d0f28778-f3b8-40a9-80b9-b4d6ded0e5f7","VT_Code":null,"VIP_Name":"余文乐","VIP_CellPhone":"18631214444","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"001","VIP_RecommendName":"赵三","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"9005","VCH_Fee":0,"VG_Name":"普通会员","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":199.1,"MA_AvailableIntegral":195.9,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-08-10 10:37:21","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null},{"GID":null,"VG_GID":"d0f28778-f3b8-40a9-80b9-b4d6ded0e5f7","VT_Code":null,"VIP_Name":"张杰","VIP_CellPhone":"13980563218","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":0,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"005","VIP_RecommendName":"王菲","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"0123","VCH_Fee":0,"VG_Name":"普通会员","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":100,"MA_AvailableIntegral":170,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-07-28 11:35:19","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null},{"GID":null,"VG_GID":"d0f28778-f3b8-40a9-80b9-b4d6ded0e5f7","VT_Code":null,"VIP_Name":"李琪","VIP_CellPhone":"15685858552","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":1,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"005","VIP_RecommendName":"王菲","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"900","VCH_Fee":0,"VG_Name":"普通会员","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":200,"MA_AvailableIntegral":300,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-07-26 11:14:32","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null},{"GID":null,"VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","VT_Code":null,"VIP_Name":"王芳方","VIP_CellPhone":"15845525874","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":1,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"003","VIP_RecommendName":"周凯","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"427","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":100,"MA_AvailableIntegral":100,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-07-21 11:35:03","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null},{"GID":null,"VG_GID":"bd9a443b-1337-4f6b-bd5b-4ed5bc416b53","VT_Code":null,"VIP_Name":"新增会员测试","VIP_CellPhone":"15865321111","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":1,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"002","VIP_RecommendName":"李斯","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"543","VCH_Fee":0,"VG_Name":"白金会员","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":200,"MA_AvailableIntegral":0,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-07-21 11:27:31","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null},{"GID":null,"VG_GID":"b6a603d4-ec07-4b85-8252-87bfa8d79697","VT_Code":null,"VIP_Name":"于蓝","VIP_CellPhone":"15965656565","VIP_Birthday":null,"VIP_Email":null,"EM_ID":null,"VIP_Overdue":null,"VIP_IsForver":0,"VIP_State":0,"VIP_Sex":1,"VIP_FixedPhone":null,"VIP_UpdateTime":"0001-01-01 00:00:00","VIP_Referee":"001","VIP_RecommendName":"赵三","VIP_FaceNumber":null,"VIP_Remark":null,"CY_GID":null,"VIP_HeadPicture":null,"SM_ID":null,"VIP_IsDeleted":0,"VIP_Percent":0,"VCH_Card":"1234","VCH_Fee":0,"VG_Name":"默认等级","VGC_Code":null,"US_Value":null,"VT_Name":null,"VCH_Type":0,"VCH_UpdateTime":"0001-01-01 00:00:00","MA_AccountBalance":0,"MA_AggregateAmount":0,"MA_AvailableBalance":875,"MA_AvailableIntegral":601.54,"MA_FreezeBalance":0,"VIP_Addr":null,"VIP_HeadImg":null,"SM_Name":null,"VCH_CreateTime":"2017-07-12 11:20:45","VIP_Creator":null,"VCH_Pwd":null,"VIP_ICCard":null,"US_Name":null,"VIP_Label":null,"VG_IsAccount":null,"VG_IsIntegral":null,"VG_IsDiscount":null,"VG_IsCount":null,"VG_IsTime":null}]
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
             * GID : null
             * VG_GID : d0f28778-f3b8-40a9-80b9-b4d6ded0e5f7
             * VT_Code : null
             * VIP_Name : 余文乐
             * VIP_CellPhone : 18631214444
             * VIP_Birthday : null
             * VIP_Email : null
             * EM_ID : null
             * VIP_Overdue : null
             * VIP_IsForver : 0
             * VIP_State : 0
             * VIP_Sex : 0
             * VIP_FixedPhone : null
             * VIP_UpdateTime : 0001-01-01 00:00:00
             * VIP_Referee : 001
             * VIP_RecommendName : 赵三
             * VIP_FaceNumber : null
             * VIP_Remark : null
             * CY_GID : null
             * VIP_HeadPicture : null
             * SM_ID : null
             * VIP_IsDeleted : 0
             * VIP_Percent : 0
             * VCH_Card : 9005
             * VCH_Fee : 0
             * VG_Name : 普通会员
             * VGC_Code : null
             * US_Value : null
             * VT_Name : null
             * VCH_Type : 0
             * VCH_UpdateTime : 0001-01-01 00:00:00
             * MA_AccountBalance : 0.0
             * MA_AggregateAmount : 0.0
             * MA_AvailableBalance : 199.1
             * MA_AvailableIntegral : 195.9
             * MA_FreezeBalance : 0.0
             * VIP_Addr : null
             * VIP_HeadImg : null
             * SM_Name : null
             * VCH_CreateTime : 2017-08-10 10:37:21
             * VIP_Creator : null
             * VCH_Pwd : null
             * VIP_ICCard : null
             * US_Name : null
             * VIP_Label : null
             * VG_IsAccount : null
             * VG_IsIntegral : null
             * VG_IsDiscount : null
             * VG_IsCount : null
             * VG_IsTime : null
             */

            private Object GID;
            private String VG_GID;
            private Object VT_Code;
            private String VIP_Name;
            private String VIP_CellPhone;
            private Object VIP_Birthday;
            private Object VIP_Email;
            private Object EM_ID;
            private Object VIP_Overdue;
            private int VIP_IsForver;
            private int VIP_State;
            private int VIP_Sex;
            private Object VIP_FixedPhone;
            private String VIP_UpdateTime;
            private String VIP_Referee;
            private String VIP_RecommendName;
            private Object VIP_FaceNumber;
            private Object VIP_Remark;
            private Object CY_GID;
            private Object VIP_HeadPicture;
            private Object SM_ID;
            private int VIP_IsDeleted;
            private int VIP_Percent;
            private String VCH_Card;
            private int VCH_Fee;
            private String VG_Name;
            private Object VGC_Code;
            private Object US_Value;
            private Object VT_Name;
            private int VCH_Type;
            private String VCH_UpdateTime;
            private double MA_AccountBalance;
            private double MA_AggregateAmount;
            private double MA_AvailableBalance;
            private double MA_AvailableIntegral;
            private double MA_FreezeBalance;
            private Object VIP_Addr;
            private Object VIP_HeadImg;
            private Object SM_Name;
            private String VCH_CreateTime;
            private Object VIP_Creator;
            private Object VCH_Pwd;
            private Object VIP_ICCard;
            private Object US_Name;
            private Object VIP_Label;
            private Object VG_IsAccount;
            private Object VG_IsIntegral;
            private Object VG_IsDiscount;
            private Object VG_IsCount;
            private Object VG_IsTime;

            public Object getGID() {
                return GID;
            }

            public void setGID(Object GID) {
                this.GID = GID;
            }

            public String getVG_GID() {
                return VG_GID;
            }

            public void setVG_GID(String VG_GID) {
                this.VG_GID = VG_GID;
            }

            public Object getVT_Code() {
                return VT_Code;
            }

            public void setVT_Code(Object VT_Code) {
                this.VT_Code = VT_Code;
            }

            public String getVIP_Name() {
                return VIP_Name;
            }

            public void setVIP_Name(String VIP_Name) {
                this.VIP_Name = VIP_Name;
            }

            public String getVIP_CellPhone() {
                return VIP_CellPhone;
            }

            public void setVIP_CellPhone(String VIP_CellPhone) {
                this.VIP_CellPhone = VIP_CellPhone;
            }

            public Object getVIP_Birthday() {
                return VIP_Birthday;
            }

            public void setVIP_Birthday(Object VIP_Birthday) {
                this.VIP_Birthday = VIP_Birthday;
            }

            public Object getVIP_Email() {
                return VIP_Email;
            }

            public void setVIP_Email(Object VIP_Email) {
                this.VIP_Email = VIP_Email;
            }

            public Object getEM_ID() {
                return EM_ID;
            }

            public void setEM_ID(Object EM_ID) {
                this.EM_ID = EM_ID;
            }

            public Object getVIP_Overdue() {
                return VIP_Overdue;
            }

            public void setVIP_Overdue(Object VIP_Overdue) {
                this.VIP_Overdue = VIP_Overdue;
            }

            public int getVIP_IsForver() {
                return VIP_IsForver;
            }

            public void setVIP_IsForver(int VIP_IsForver) {
                this.VIP_IsForver = VIP_IsForver;
            }

            public int getVIP_State() {
                return VIP_State;
            }

            public void setVIP_State(int VIP_State) {
                this.VIP_State = VIP_State;
            }

            public int getVIP_Sex() {
                return VIP_Sex;
            }

            public void setVIP_Sex(int VIP_Sex) {
                this.VIP_Sex = VIP_Sex;
            }

            public Object getVIP_FixedPhone() {
                return VIP_FixedPhone;
            }

            public void setVIP_FixedPhone(Object VIP_FixedPhone) {
                this.VIP_FixedPhone = VIP_FixedPhone;
            }

            public String getVIP_UpdateTime() {
                return VIP_UpdateTime;
            }

            public void setVIP_UpdateTime(String VIP_UpdateTime) {
                this.VIP_UpdateTime = VIP_UpdateTime;
            }

            public String getVIP_Referee() {
                return VIP_Referee;
            }

            public void setVIP_Referee(String VIP_Referee) {
                this.VIP_Referee = VIP_Referee;
            }

            public String getVIP_RecommendName() {
                return VIP_RecommendName;
            }

            public void setVIP_RecommendName(String VIP_RecommendName) {
                this.VIP_RecommendName = VIP_RecommendName;
            }

            public Object getVIP_FaceNumber() {
                return VIP_FaceNumber;
            }

            public void setVIP_FaceNumber(Object VIP_FaceNumber) {
                this.VIP_FaceNumber = VIP_FaceNumber;
            }

            public Object getVIP_Remark() {
                return VIP_Remark;
            }

            public void setVIP_Remark(Object VIP_Remark) {
                this.VIP_Remark = VIP_Remark;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }

            public Object getVIP_HeadPicture() {
                return VIP_HeadPicture;
            }

            public void setVIP_HeadPicture(Object VIP_HeadPicture) {
                this.VIP_HeadPicture = VIP_HeadPicture;
            }

            public Object getSM_ID() {
                return SM_ID;
            }

            public void setSM_ID(Object SM_ID) {
                this.SM_ID = SM_ID;
            }

            public int getVIP_IsDeleted() {
                return VIP_IsDeleted;
            }

            public void setVIP_IsDeleted(int VIP_IsDeleted) {
                this.VIP_IsDeleted = VIP_IsDeleted;
            }

            public int getVIP_Percent() {
                return VIP_Percent;
            }

            public void setVIP_Percent(int VIP_Percent) {
                this.VIP_Percent = VIP_Percent;
            }

            public String getVCH_Card() {
                return VCH_Card;
            }

            public void setVCH_Card(String VCH_Card) {
                this.VCH_Card = VCH_Card;
            }

            public int getVCH_Fee() {
                return VCH_Fee;
            }

            public void setVCH_Fee(int VCH_Fee) {
                this.VCH_Fee = VCH_Fee;
            }

            public String getVG_Name() {
                return VG_Name;
            }

            public void setVG_Name(String VG_Name) {
                this.VG_Name = VG_Name;
            }

            public Object getVGC_Code() {
                return VGC_Code;
            }

            public void setVGC_Code(Object VGC_Code) {
                this.VGC_Code = VGC_Code;
            }

            public Object getUS_Value() {
                return US_Value;
            }

            public void setUS_Value(Object US_Value) {
                this.US_Value = US_Value;
            }

            public Object getVT_Name() {
                return VT_Name;
            }

            public void setVT_Name(Object VT_Name) {
                this.VT_Name = VT_Name;
            }

            public int getVCH_Type() {
                return VCH_Type;
            }

            public void setVCH_Type(int VCH_Type) {
                this.VCH_Type = VCH_Type;
            }

            public String getVCH_UpdateTime() {
                return VCH_UpdateTime;
            }

            public void setVCH_UpdateTime(String VCH_UpdateTime) {
                this.VCH_UpdateTime = VCH_UpdateTime;
            }

            public double getMA_AccountBalance() {
                return MA_AccountBalance;
            }

            public void setMA_AccountBalance(double MA_AccountBalance) {
                this.MA_AccountBalance = MA_AccountBalance;
            }

            public double getMA_AggregateAmount() {
                return MA_AggregateAmount;
            }

            public void setMA_AggregateAmount(double MA_AggregateAmount) {
                this.MA_AggregateAmount = MA_AggregateAmount;
            }

            public double getMA_AvailableBalance() {
                return MA_AvailableBalance;
            }

            public void setMA_AvailableBalance(double MA_AvailableBalance) {
                this.MA_AvailableBalance = MA_AvailableBalance;
            }

            public double getMA_AvailableIntegral() {
                return MA_AvailableIntegral;
            }

            public void setMA_AvailableIntegral(double MA_AvailableIntegral) {
                this.MA_AvailableIntegral = MA_AvailableIntegral;
            }

            public double getMA_FreezeBalance() {
                return MA_FreezeBalance;
            }

            public void setMA_FreezeBalance(double MA_FreezeBalance) {
                this.MA_FreezeBalance = MA_FreezeBalance;
            }

            public Object getVIP_Addr() {
                return VIP_Addr;
            }

            public void setVIP_Addr(Object VIP_Addr) {
                this.VIP_Addr = VIP_Addr;
            }

            public Object getVIP_HeadImg() {
                return VIP_HeadImg;
            }

            public void setVIP_HeadImg(Object VIP_HeadImg) {
                this.VIP_HeadImg = VIP_HeadImg;
            }

            public Object getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(Object SM_Name) {
                this.SM_Name = SM_Name;
            }

            public String getVCH_CreateTime() {
                return VCH_CreateTime;
            }

            public void setVCH_CreateTime(String VCH_CreateTime) {
                this.VCH_CreateTime = VCH_CreateTime;
            }

            public Object getVIP_Creator() {
                return VIP_Creator;
            }

            public void setVIP_Creator(Object VIP_Creator) {
                this.VIP_Creator = VIP_Creator;
            }

            public Object getVCH_Pwd() {
                return VCH_Pwd;
            }

            public void setVCH_Pwd(Object VCH_Pwd) {
                this.VCH_Pwd = VCH_Pwd;
            }

            public Object getVIP_ICCard() {
                return VIP_ICCard;
            }

            public void setVIP_ICCard(Object VIP_ICCard) {
                this.VIP_ICCard = VIP_ICCard;
            }

            public Object getUS_Name() {
                return US_Name;
            }

            public void setUS_Name(Object US_Name) {
                this.US_Name = US_Name;
            }

            public Object getVIP_Label() {
                return VIP_Label;
            }

            public void setVIP_Label(Object VIP_Label) {
                this.VIP_Label = VIP_Label;
            }

            public Object getVG_IsAccount() {
                return VG_IsAccount;
            }

            public void setVG_IsAccount(Object VG_IsAccount) {
                this.VG_IsAccount = VG_IsAccount;
            }

            public Object getVG_IsIntegral() {
                return VG_IsIntegral;
            }

            public void setVG_IsIntegral(Object VG_IsIntegral) {
                this.VG_IsIntegral = VG_IsIntegral;
            }

            public Object getVG_IsDiscount() {
                return VG_IsDiscount;
            }

            public void setVG_IsDiscount(Object VG_IsDiscount) {
                this.VG_IsDiscount = VG_IsDiscount;
            }

            public Object getVG_IsCount() {
                return VG_IsCount;
            }

            public void setVG_IsCount(Object VG_IsCount) {
                this.VG_IsCount = VG_IsCount;
            }

            public Object getVG_IsTime() {
                return VG_IsTime;
            }

            public void setVG_IsTime(Object VG_IsTime) {
                this.VG_IsTime = VG_IsTime;
            }
        }
    }
}
