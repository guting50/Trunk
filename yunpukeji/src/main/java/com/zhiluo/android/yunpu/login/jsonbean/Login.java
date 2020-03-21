package com.zhiluo.android.yunpu.login.jsonbean;

import java.util.List;

/**
 * Created by Administrator on 2016/9/6.
 */
public class Login {
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
        private Object UM_ThirdPartyOpenID;
        private Object UM_Unionid;
        private Object UM_OpenID;
        private Object UM_CallCode;
        private Object UM_RegSource;
        private Object UM_RegSourceParam;
        private Object UM_RegIP;
        private String GID;
        private String UM_Acount;
        private Object UM_Pwd;
        private String UM_Name;
        private Object UM_Contact;
        private Object UM_Right;
        private int UM_IsLock;
        private String UM_Remark;
        private String UM_IP;
        private Object UM_LoginTime;
        private String UM_Creator;
        private String UM_UpdateTime;
        private int UM_IsAmin;
        private String CY_GID;
        private Object UM_CreateTime;
        private Object UM_UpdateState;
        private String ShopID;
        private String SM_Name;
        private Object RoleName;
        private String RoleID;
        private Object EM_GID;
        private Object EM_Name;
        private Object RM_Name;
        private List<ShopListBean> ShopList;
        private List<MenuInfoListBean> MenuInfoList;
        private List<MenuRoleListBean> MenuRoleList;

        public Object getUM_ThirdPartyOpenID() {
            return UM_ThirdPartyOpenID;
        }

        public void setUM_ThirdPartyOpenID(Object UM_ThirdPartyOpenID) {
            this.UM_ThirdPartyOpenID = UM_ThirdPartyOpenID;
        }

        public Object getUM_Unionid() {
            return UM_Unionid;
        }

        public void setUM_Unionid(Object UM_Unionid) {
            this.UM_Unionid = UM_Unionid;
        }

        public Object getUM_OpenID() {
            return UM_OpenID;
        }

        public void setUM_OpenID(Object UM_OpenID) {
            this.UM_OpenID = UM_OpenID;
        }

        public Object getUM_CallCode() {
            return UM_CallCode;
        }

        public void setUM_CallCode(Object UM_CallCode) {
            this.UM_CallCode = UM_CallCode;
        }

        public Object getUM_RegSource() {
            return UM_RegSource;
        }

        public void setUM_RegSource(Object UM_RegSource) {
            this.UM_RegSource = UM_RegSource;
        }

        public Object getUM_RegSourceParam() {
            return UM_RegSourceParam;
        }

        public void setUM_RegSourceParam(Object UM_RegSourceParam) {
            this.UM_RegSourceParam = UM_RegSourceParam;
        }

        public Object getUM_RegIP() {
            return UM_RegIP;
        }

        public void setUM_RegIP(Object UM_RegIP) {
            this.UM_RegIP = UM_RegIP;
        }

        public String getGID() {
            return GID;
        }

        public void setGID(String GID) {
            this.GID = GID;
        }

        public String getUM_Acount() {
            return UM_Acount;
        }

        public void setUM_Acount(String UM_Acount) {
            this.UM_Acount = UM_Acount;
        }

        public Object getUM_Pwd() {
            return UM_Pwd;
        }

        public void setUM_Pwd(Object UM_Pwd) {
            this.UM_Pwd = UM_Pwd;
        }

        public String getUM_Name() {
            return UM_Name;
        }

        public void setUM_Name(String UM_Name) {
            this.UM_Name = UM_Name;
        }

        public Object getUM_Contact() {
            return UM_Contact;
        }

        public void setUM_Contact(Object UM_Contact) {
            this.UM_Contact = UM_Contact;
        }

        public Object getUM_Right() {
            return UM_Right;
        }

        public void setUM_Right(Object UM_Right) {
            this.UM_Right = UM_Right;
        }

        public int getUM_IsLock() {
            return UM_IsLock;
        }

        public void setUM_IsLock(int UM_IsLock) {
            this.UM_IsLock = UM_IsLock;
        }

        public String getUM_Remark() {
            return UM_Remark;
        }

        public void setUM_Remark(String UM_Remark) {
            this.UM_Remark = UM_Remark;
        }

        public String getUM_IP() {
            return UM_IP;
        }

        public void setUM_IP(String UM_IP) {
            this.UM_IP = UM_IP;
        }

        public Object getUM_LoginTime() {
            return UM_LoginTime;
        }

        public void setUM_LoginTime(Object UM_LoginTime) {
            this.UM_LoginTime = UM_LoginTime;
        }

        public String getUM_Creator() {
            return UM_Creator;
        }

        public void setUM_Creator(String UM_Creator) {
            this.UM_Creator = UM_Creator;
        }

        public String getUM_UpdateTime() {
            return UM_UpdateTime;
        }

        public void setUM_UpdateTime(String UM_UpdateTime) {
            this.UM_UpdateTime = UM_UpdateTime;
        }

        public int getUM_IsAmin() {
            return UM_IsAmin;
        }

        public void setUM_IsAmin(int UM_IsAmin) {
            this.UM_IsAmin = UM_IsAmin;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public Object getUM_CreateTime() {
            return UM_CreateTime;
        }

        public void setUM_CreateTime(Object UM_CreateTime) {
            this.UM_CreateTime = UM_CreateTime;
        }

        public Object getUM_UpdateState() {
            return UM_UpdateState;
        }

        public void setUM_UpdateState(Object UM_UpdateState) {
            this.UM_UpdateState = UM_UpdateState;
        }

        public String getShopID() {
            return ShopID;
        }

        public void setShopID(String ShopID) {
            this.ShopID = ShopID;
        }

        public String getSM_Name() {
            return SM_Name;
        }

        public void setSM_Name(String SM_Name) {
            this.SM_Name = SM_Name;
        }

        public Object getRoleName() {
            return RoleName;
        }

        public void setRoleName(Object RoleName) {
            this.RoleName = RoleName;
        }

        public String getRoleID() {
            return RoleID;
        }

        public void setRoleID(String RoleID) {
            this.RoleID = RoleID;
        }

        public Object getEM_GID() {
            return EM_GID;
        }

        public void setEM_GID(Object EM_GID) {
            this.EM_GID = EM_GID;
        }

        public Object getEM_Name() {
            return EM_Name;
        }

        public void setEM_Name(Object EM_Name) {
            this.EM_Name = EM_Name;
        }

        public Object getRM_Name() {
            return RM_Name;
        }

        public void setRM_Name(Object RM_Name) {
            this.RM_Name = RM_Name;
        }

        public List<ShopListBean> getShopList() {
            return ShopList;
        }

        public void setShopList(List<ShopListBean> ShopList) {
            this.ShopList = ShopList;
        }

        public List<MenuInfoListBean> getMenuInfoList() {
            return MenuInfoList;
        }

        public void setMenuInfoList(List<MenuInfoListBean> MenuInfoList) {
            this.MenuInfoList = MenuInfoList;
        }

        public List<MenuRoleListBean> getMenuRoleList() {
            return MenuRoleList;
        }

        public void setMenuRoleList(List<MenuRoleListBean> MenuRoleList) {
            this.MenuRoleList = MenuRoleList;
        }

        public static class ShopListBean {
            /**
             * SM_DefaultCode : null
             * GID : 8a293faa-786a-436a-ad75-d7e4e59d7178
             * CY_GID : f639d88e-7ace-445e-bf37-c14eb4d548c6
             * SM_Name : 业务小店一号
             * SM_Contacter : 程序员
             * SM_Phone : 18010676022
             * SM_Addr : null
             * SM_Remark :
             * SM_State : 0
             * SM_UpdateTime : 2017-11-10 09:50:15
             * SM_Picture : http://img.yunvip123.com/CommonImg/325bbed1-398f-4d86-be0f-6f18142f914d.png
             * SM_Creator : 测试
             * SM_XLong : null
             * SM_YLat : null
             * SM_Industry :
             * SM_Range :
             * SM_Country : null
             * SM_Province : 四川省
             * SM_Disctrict : 成华区
             * SM_DetailAddr :
             * SM_MapAddr : null
             * SM_UpdateState : 1
             * SM_AcountNum : 10
             * SM_Type : 1
             * SM_EndTime : 2018-11-09 19:41:26
             * SM_CreateTime : 2017-11-10 09:50:15
             * SM_City : 成都市
             * VipNumber : 0
             * ProNumber : 0
             */

            private Object SM_DefaultCode;
            private String GID;
            private String CY_GID;
            private String SM_Name;
            private String SM_Contacter;
            private String SM_Phone;
            private Object SM_Addr;
            private String SM_Remark;
            private int SM_State;
            private String SM_UpdateTime;
            private String SM_Picture;
            private String SM_Creator;
            private Object SM_XLong;
            private Object SM_YLat;
            private String SM_Industry;
            private String SM_Range;
            private Object SM_Country;
            private String SM_Province;
            private String SM_Disctrict;
            private String SM_DetailAddr;
            private Object SM_MapAddr;
            private int SM_UpdateState;
            private int SM_AcountNum;
            private int SM_Type;
            private String SM_EndTime;
            private String SM_CreateTime;
            private String SM_City;
            private int VipNumber;
            private int ProNumber;

            public Object getSM_DefaultCode() {
                return SM_DefaultCode;
            }

            public void setSM_DefaultCode(Object SM_DefaultCode) {
                this.SM_DefaultCode = SM_DefaultCode;
            }

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(String CY_GID) {
                this.CY_GID = CY_GID;
            }

            public String getSM_Name() {
                return SM_Name;
            }

            public void setSM_Name(String SM_Name) {
                this.SM_Name = SM_Name;
            }

            public String getSM_Contacter() {
                return SM_Contacter;
            }

            public void setSM_Contacter(String SM_Contacter) {
                this.SM_Contacter = SM_Contacter;
            }

            public String getSM_Phone() {
                return SM_Phone;
            }

            public void setSM_Phone(String SM_Phone) {
                this.SM_Phone = SM_Phone;
            }

            public Object getSM_Addr() {
                return SM_Addr;
            }

            public void setSM_Addr(Object SM_Addr) {
                this.SM_Addr = SM_Addr;
            }

            public String getSM_Remark() {
                return SM_Remark;
            }

            public void setSM_Remark(String SM_Remark) {
                this.SM_Remark = SM_Remark;
            }

            public int getSM_State() {
                return SM_State;
            }

            public void setSM_State(int SM_State) {
                this.SM_State = SM_State;
            }

            public String getSM_UpdateTime() {
                return SM_UpdateTime;
            }

            public void setSM_UpdateTime(String SM_UpdateTime) {
                this.SM_UpdateTime = SM_UpdateTime;
            }

            public String getSM_Picture() {
                return SM_Picture;
            }

            public void setSM_Picture(String SM_Picture) {
                this.SM_Picture = SM_Picture;
            }

            public String getSM_Creator() {
                return SM_Creator;
            }

            public void setSM_Creator(String SM_Creator) {
                this.SM_Creator = SM_Creator;
            }

            public Object getSM_XLong() {
                return SM_XLong;
            }

            public void setSM_XLong(Object SM_XLong) {
                this.SM_XLong = SM_XLong;
            }

            public Object getSM_YLat() {
                return SM_YLat;
            }

            public void setSM_YLat(Object SM_YLat) {
                this.SM_YLat = SM_YLat;
            }

            public String getSM_Industry() {
                return SM_Industry;
            }

            public void setSM_Industry(String SM_Industry) {
                this.SM_Industry = SM_Industry;
            }

            public String getSM_Range() {
                return SM_Range;
            }

            public void setSM_Range(String SM_Range) {
                this.SM_Range = SM_Range;
            }

            public Object getSM_Country() {
                return SM_Country;
            }

            public void setSM_Country(Object SM_Country) {
                this.SM_Country = SM_Country;
            }

            public String getSM_Province() {
                return SM_Province;
            }

            public void setSM_Province(String SM_Province) {
                this.SM_Province = SM_Province;
            }

            public String getSM_Disctrict() {
                return SM_Disctrict;
            }

            public void setSM_Disctrict(String SM_Disctrict) {
                this.SM_Disctrict = SM_Disctrict;
            }

            public String getSM_DetailAddr() {
                return SM_DetailAddr;
            }

            public void setSM_DetailAddr(String SM_DetailAddr) {
                this.SM_DetailAddr = SM_DetailAddr;
            }

            public Object getSM_MapAddr() {
                return SM_MapAddr;
            }

            public void setSM_MapAddr(Object SM_MapAddr) {
                this.SM_MapAddr = SM_MapAddr;
            }

            public int getSM_UpdateState() {
                return SM_UpdateState;
            }

            public void setSM_UpdateState(int SM_UpdateState) {
                this.SM_UpdateState = SM_UpdateState;
            }

            public int getSM_AcountNum() {
                return SM_AcountNum;
            }

            public void setSM_AcountNum(int SM_AcountNum) {
                this.SM_AcountNum = SM_AcountNum;
            }

            public int getSM_Type() {
                return SM_Type;
            }

            public void setSM_Type(int SM_Type) {
                this.SM_Type = SM_Type;
            }

            public String getSM_EndTime() {
                return SM_EndTime;
            }

            public void setSM_EndTime(String SM_EndTime) {
                this.SM_EndTime = SM_EndTime;
            }

            public String getSM_CreateTime() {
                return SM_CreateTime;
            }

            public void setSM_CreateTime(String SM_CreateTime) {
                this.SM_CreateTime = SM_CreateTime;
            }

            public String getSM_City() {
                return SM_City;
            }

            public void setSM_City(String SM_City) {
                this.SM_City = SM_City;
            }

            public int getVipNumber() {
                return VipNumber;
            }

            public void setVipNumber(int VipNumber) {
                this.VipNumber = VipNumber;
            }

            public int getProNumber() {
                return ProNumber;
            }

            public void setProNumber(int ProNumber) {
                this.ProNumber = ProNumber;
            }
        }

        public static class MenuInfoListBean {
            /**
             * GID : b0f53a86-9e73-4761-9cbb-b1d11d01254
             * MM_LinkUrl : /WebUI/Member/MRechargePackageList.html
             * MM_PictureAddr :
             * MM_Name : 优惠活动
             * MM_Remark : null
             * MM_CreateTime : null
             * MM_Creator : null
             * MM_Sort : 1
             * MM_ParentID : c15f452a-c8b1-4b18-9ba9-ed78537abce3
             * MM_Icon :
             */

            private String GID;
            private String MM_LinkUrl;
            private String MM_PictureAddr;
            private String MM_Name;
            private Object MM_Remark;
            private Object MM_CreateTime;
            private Object MM_Creator;
            private int MM_Sort;
            private String MM_ParentID;
            private String MM_Icon;

            public String getGID() {
                return GID;
            }

            public void setGID(String GID) {
                this.GID = GID;
            }

            public String getMM_LinkUrl() {
                return MM_LinkUrl;
            }

            public void setMM_LinkUrl(String MM_LinkUrl) {
                this.MM_LinkUrl = MM_LinkUrl;
            }

            public String getMM_PictureAddr() {
                return MM_PictureAddr;
            }

            public void setMM_PictureAddr(String MM_PictureAddr) {
                this.MM_PictureAddr = MM_PictureAddr;
            }

            public String getMM_Name() {
                return MM_Name;
            }

            public void setMM_Name(String MM_Name) {
                this.MM_Name = MM_Name;
            }

            public Object getMM_Remark() {
                return MM_Remark;
            }

            public void setMM_Remark(Object MM_Remark) {
                this.MM_Remark = MM_Remark;
            }

            public Object getMM_CreateTime() {
                return MM_CreateTime;
            }

            public void setMM_CreateTime(Object MM_CreateTime) {
                this.MM_CreateTime = MM_CreateTime;
            }

            public Object getMM_Creator() {
                return MM_Creator;
            }

            public void setMM_Creator(Object MM_Creator) {
                this.MM_Creator = MM_Creator;
            }

            public int getMM_Sort() {
                return MM_Sort;
            }

            public void setMM_Sort(int MM_Sort) {
                this.MM_Sort = MM_Sort;
            }

            public String getMM_ParentID() {
                return MM_ParentID;
            }

            public void setMM_ParentID(String MM_ParentID) {
                this.MM_ParentID = MM_ParentID;
            }

            public String getMM_Icon() {
                return MM_Icon;
            }

            public void setMM_Icon(String MM_Icon) {
                this.MM_Icon = MM_Icon;
            }
        }

        public static class MenuRoleListBean {
            /**
             * RR_IsCheck : 1
             * RS_GID : 59247620
             * RS_Type : 会员账户
             * RS_Addr : /api/Report/GetDelayReport
             * RS_Picture : null
             * RS_Name : 延期记录
             * MR_GID : null
             * MM_GID : null
             * MM_LinkUrl : null
             * MM_Name : null
             * MM_ParentID : null
             * MM_Sort : null
             * CY_GID : null
             * RM_GID : null
             */

            private int RR_IsCheck;
            private String RS_GID;
            private String RS_Type;
            private String RS_Addr;
            private Object RS_Picture;
            private String RS_Name;
            private Object MR_GID;
            private Object MM_GID;
            private Object MM_LinkUrl;
            private Object MM_Name;
            private Object MM_ParentID;
            private Object MM_Sort;
            private Object CY_GID;
            private Object RM_GID;

            public int getRR_IsCheck() {
                return RR_IsCheck;
            }

            public void setRR_IsCheck(int RR_IsCheck) {
                this.RR_IsCheck = RR_IsCheck;
            }

            public String getRS_GID() {
                return RS_GID;
            }

            public void setRS_GID(String RS_GID) {
                this.RS_GID = RS_GID;
            }

            public String getRS_Type() {
                return RS_Type;
            }

            public void setRS_Type(String RS_Type) {
                this.RS_Type = RS_Type;
            }

            public String getRS_Addr() {
                return RS_Addr;
            }

            public void setRS_Addr(String RS_Addr) {
                this.RS_Addr = RS_Addr;
            }

            public Object getRS_Picture() {
                return RS_Picture;
            }

            public void setRS_Picture(Object RS_Picture) {
                this.RS_Picture = RS_Picture;
            }

            public String getRS_Name() {
                return RS_Name;
            }

            public void setRS_Name(String RS_Name) {
                this.RS_Name = RS_Name;
            }

            public Object getMR_GID() {
                return MR_GID;
            }

            public void setMR_GID(Object MR_GID) {
                this.MR_GID = MR_GID;
            }

            public Object getMM_GID() {
                return MM_GID;
            }

            public void setMM_GID(Object MM_GID) {
                this.MM_GID = MM_GID;
            }

            public Object getMM_LinkUrl() {
                return MM_LinkUrl;
            }

            public void setMM_LinkUrl(Object MM_LinkUrl) {
                this.MM_LinkUrl = MM_LinkUrl;
            }

            public Object getMM_Name() {
                return MM_Name;
            }

            public void setMM_Name(Object MM_Name) {
                this.MM_Name = MM_Name;
            }

            public Object getMM_ParentID() {
                return MM_ParentID;
            }

            public void setMM_ParentID(Object MM_ParentID) {
                this.MM_ParentID = MM_ParentID;
            }

            public Object getMM_Sort() {
                return MM_Sort;
            }

            public void setMM_Sort(Object MM_Sort) {
                this.MM_Sort = MM_Sort;
            }

            public Object getCY_GID() {
                return CY_GID;
            }

            public void setCY_GID(Object CY_GID) {
                this.CY_GID = CY_GID;
            }

            public Object getRM_GID() {
                return RM_GID;
            }

            public void setRM_GID(Object RM_GID) {
                this.RM_GID = RM_GID;
            }
        }
    }
}
