package com.zhiluo.android.yunpu.ui.bean;

/**
 * Created by ZPH on 2019-09-03.
 */

public class GetMerchantInfoBean {


    /**
     * success : true
     * code : null
     * msg : 执行成功
     * data : {"_id":"5d6893e812bdc33efc11093e","merchant_no":"865107011000005","merchant_status":2,"CY_GID":"33b26f6e-78c8-4da8-bdad-6b47c06f4aa4","SM_GID":"4a7b1141-8ca3-4bef-961f-ffd153357823","merchant_name":"初步","merchant_alias":"商户简称42","merchant_company":"商户名称123","merchant_province":"北京市","merchant_province_code":"110","merchant_city":"北京市","merchant_city_code":"1000","merchant_county":"西城区","merchant_county_code":"1001","merchant_address":"哦lol了","merchant_person":"鄂律DJ","merchant_phone":"18355358635","merchant_email":"10286588@qq.com","business_name":"贵重珠宝、首饰，钟表零售","business_code":"5094","merchant_business_type":3,"account_type":2,"settlement_type":1,"license_type":2,"account_name":"就来了","account_no":"57583575742577525","bank_name":"中国工商银行股份有限公司北京西直门支行","bank_no":"102100006502","license_no":"500242199210288473","license_expire":"2019-09-03","artif_nm":"就来了","legalIdnum":"500242199210288473","legalIdnumExpire":"2019-09-03","merchant_id_no":"500242199210288473","merchant_id_expire":"20190903","account_phone":null,"img_license":null,"img_idcard_a":"http://img.yunvip123.com/CommonImg/c296f040-8cee-4769-b1ef-5f4862348b57.png","img_idcard_b":"http://img.yunvip123.com/CommonImg/78c4a037-b8e8-4edb-8492-4b7cb6d7cfe0.png","img_bankcard_a":"http://img.yunvip123.com/CommonImg/a77b8f17-db55-4d65-befd-f4d2b30ae29a.png","img_bankcard_b":"http://img.yunvip123.com/CommonImg/c87f4ee7-3a9a-43f5-aab1-672802b6aff3.png","img_logo":"http://img.yunvip123.com/CommonImg/9e2bf7fc-cd21-4edd-bcab-f3d274a720b6.png","img_indoor":"http://img.yunvip123.com/CommonImg/59fb4521-471a-45a1-8b23-97d8b7bec8e8.jpeg","img_idcard_holding":"http://img.yunvip123.com/CommonImg/ac11242f-233b-43b4-9f4d-a9b711aea1cf.png","img_open_permits":null,"img_org_code":null,"img_tax_reg":null,"img_unincorporated":null,"img_private_idcard_a":"http://img.yunvip123.com/CommonImg/c296f040-8cee-4769-b1ef-5f4862348b57.png","img_private_idcard_b":"http://img.yunvip123.com/CommonImg/1db758f7-0ce3-488f-a173-afe93ee7beb2.jpeg","Terminal_ID":null,"merchantwx1id":"1","merchantwx2id":"1","bankparentID":"63","bankprovinceID":"23","bankcityID":"258","return_msg":"请按照要求提交各资质照片，填写商户基本信息"}
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
         * _id : 5d6893e812bdc33efc11093e
         * merchant_no : 865107011000005
         * merchant_status : 2
         * CY_GID : 33b26f6e-78c8-4da8-bdad-6b47c06f4aa4
         * SM_GID : 4a7b1141-8ca3-4bef-961f-ffd153357823
         * merchant_name : 初步
         * merchant_alias : 商户简称42
         * merchant_company : 商户名称123
         * merchant_province : 北京市
         * merchant_province_code : 110
         * merchant_city : 北京市
         * merchant_city_code : 1000
         * merchant_county : 西城区
         * merchant_county_code : 1001
         * merchant_address : 哦lol了
         * merchant_person : 鄂律DJ
         * merchant_phone : 18355358635
         * merchant_email : 10286588@qq.com
         * business_name : 贵重珠宝、首饰，钟表零售
         * business_code : 5094
         * merchant_business_type : 3
         * account_type : 2
         * settlement_type : 1
         * license_type : 2
         * account_name : 就来了
         * account_no : 57583575742577525
         * bank_name : 中国工商银行股份有限公司北京西直门支行
         * bank_no : 102100006502
         * license_no : 500242199210288473
         * license_expire : 2019-09-03
         * artif_nm : 就来了
         * legalIdnum : 500242199210288473
         * legalIdnumExpire : 2019-09-03
         * merchant_id_no : 500242199210288473
         * merchant_id_expire : 20190903
         * account_phone : null
         * img_license : null
         * img_idcard_a : http://img.yunvip123.com/CommonImg/c296f040-8cee-4769-b1ef-5f4862348b57.png
         * img_idcard_b : http://img.yunvip123.com/CommonImg/78c4a037-b8e8-4edb-8492-4b7cb6d7cfe0.png
         * img_bankcard_a : http://img.yunvip123.com/CommonImg/a77b8f17-db55-4d65-befd-f4d2b30ae29a.png
         * img_bankcard_b : http://img.yunvip123.com/CommonImg/c87f4ee7-3a9a-43f5-aab1-672802b6aff3.png
         * img_logo : http://img.yunvip123.com/CommonImg/9e2bf7fc-cd21-4edd-bcab-f3d274a720b6.png
         * img_indoor : http://img.yunvip123.com/CommonImg/59fb4521-471a-45a1-8b23-97d8b7bec8e8.jpeg
         * img_idcard_holding : http://img.yunvip123.com/CommonImg/ac11242f-233b-43b4-9f4d-a9b711aea1cf.png
         * img_open_permits : null
         * img_org_code : null
         * img_tax_reg : null
         * img_unincorporated : null
         * img_private_idcard_a : http://img.yunvip123.com/CommonImg/c296f040-8cee-4769-b1ef-5f4862348b57.png
         * img_private_idcard_b : http://img.yunvip123.com/CommonImg/1db758f7-0ce3-488f-a173-afe93ee7beb2.jpeg
         * Terminal_ID : null
         * merchantwx1id : 1
         * merchantwx2id : 1
         * bankparentID : 63
         * bankprovinceID : 23
         * bankcityID : 258
         * return_msg : 请按照要求提交各资质照片，填写商户基本信息
         */

        private String _id;
        private String merchant_no;
        private int merchant_status;
        private String CY_GID;
        private String SM_GID;
        private String merchant_name;
        private String merchant_alias;
        private String merchant_company;
        private String merchant_province;
        private String merchant_province_code;
        private String merchant_city;
        private String merchant_city_code;
        private String merchant_county;
        private String merchant_county_code;
        private String merchant_address;
        private String merchant_person;
        private String merchant_phone;
        private String merchant_email;
        private String business_name;
        private String business_code;
        private int merchant_business_type;
        private int account_type;
        private int settlement_type;
        private int license_type;
        private String account_name;
        private String account_no;
        private String bank_name;
        private String bank_no;
        private String license_no;
        private String license_expire;
        private String artif_nm;
        private String legalIdnum;
        private String legalIdnumExpire;
        private String merchant_id_no;
        private String merchant_id_expire;
        private Object account_phone;
        private String img_license;
        private String img_idcard_a;
        private String img_idcard_b;
        private String img_bankcard_a;
        private String img_bankcard_b;
        private String img_logo;
        private String img_indoor;
        private String img_idcard_holding;
        private String img_open_permits;
        private String img_org_code;
        private Object img_tax_reg;
        private Object img_unincorporated;
        private String img_private_idcard_a;
        private String img_private_idcard_b;
        private Object Terminal_ID;
        private String merchantwx1id;
        private String merchantwx2id;
        private String bankparentID;
        private String bankprovinceID;
        private String bankcityID;
        private String return_msg;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getMerchant_no() {
            return merchant_no;
        }

        public void setMerchant_no(String merchant_no) {
            this.merchant_no = merchant_no;
        }

        public int getMerchant_status() {
            return merchant_status;
        }

        public void setMerchant_status(int merchant_status) {
            this.merchant_status = merchant_status;
        }

        public String getCY_GID() {
            return CY_GID;
        }

        public void setCY_GID(String CY_GID) {
            this.CY_GID = CY_GID;
        }

        public String getSM_GID() {
            return SM_GID;
        }

        public void setSM_GID(String SM_GID) {
            this.SM_GID = SM_GID;
        }

        public String getMerchant_name() {
            return merchant_name;
        }

        public void setMerchant_name(String merchant_name) {
            this.merchant_name = merchant_name;
        }

        public String getMerchant_alias() {
            return merchant_alias;
        }

        public void setMerchant_alias(String merchant_alias) {
            this.merchant_alias = merchant_alias;
        }

        public String getMerchant_company() {
            return merchant_company;
        }

        public void setMerchant_company(String merchant_company) {
            this.merchant_company = merchant_company;
        }

        public String getMerchant_province() {
            return merchant_province;
        }

        public void setMerchant_province(String merchant_province) {
            this.merchant_province = merchant_province;
        }

        public String getMerchant_province_code() {
            return merchant_province_code;
        }

        public void setMerchant_province_code(String merchant_province_code) {
            this.merchant_province_code = merchant_province_code;
        }

        public String getMerchant_city() {
            return merchant_city;
        }

        public void setMerchant_city(String merchant_city) {
            this.merchant_city = merchant_city;
        }

        public String getMerchant_city_code() {
            return merchant_city_code;
        }

        public void setMerchant_city_code(String merchant_city_code) {
            this.merchant_city_code = merchant_city_code;
        }

        public String getMerchant_county() {
            return merchant_county;
        }

        public void setMerchant_county(String merchant_county) {
            this.merchant_county = merchant_county;
        }

        public String getMerchant_county_code() {
            return merchant_county_code;
        }

        public void setMerchant_county_code(String merchant_county_code) {
            this.merchant_county_code = merchant_county_code;
        }

        public String getMerchant_address() {
            return merchant_address;
        }

        public void setMerchant_address(String merchant_address) {
            this.merchant_address = merchant_address;
        }

        public String getMerchant_person() {
            return merchant_person;
        }

        public void setMerchant_person(String merchant_person) {
            this.merchant_person = merchant_person;
        }

        public String getMerchant_phone() {
            return merchant_phone;
        }

        public void setMerchant_phone(String merchant_phone) {
            this.merchant_phone = merchant_phone;
        }

        public String getMerchant_email() {
            return merchant_email;
        }

        public void setMerchant_email(String merchant_email) {
            this.merchant_email = merchant_email;
        }

        public String getBusiness_name() {
            return business_name;
        }

        public void setBusiness_name(String business_name) {
            this.business_name = business_name;
        }

        public String getBusiness_code() {
            return business_code;
        }

        public void setBusiness_code(String business_code) {
            this.business_code = business_code;
        }

        public int getMerchant_business_type() {
            return merchant_business_type;
        }

        public void setMerchant_business_type(int merchant_business_type) {
            this.merchant_business_type = merchant_business_type;
        }

        public int getAccount_type() {
            return account_type;
        }

        public void setAccount_type(int account_type) {
            this.account_type = account_type;
        }

        public int getSettlement_type() {
            return settlement_type;
        }

        public void setSettlement_type(int settlement_type) {
            this.settlement_type = settlement_type;
        }

        public int getLicense_type() {
            return license_type;
        }

        public void setLicense_type(int license_type) {
            this.license_type = license_type;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getAccount_no() {
            return account_no;
        }

        public void setAccount_no(String account_no) {
            this.account_no = account_no;
        }

        public String getBank_name() {
            return bank_name;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public String getBank_no() {
            return bank_no;
        }

        public void setBank_no(String bank_no) {
            this.bank_no = bank_no;
        }

        public String getLicense_no() {
            return license_no;
        }

        public void setLicense_no(String license_no) {
            this.license_no = license_no;
        }

        public String getLicense_expire() {
            return license_expire;
        }

        public void setLicense_expire(String license_expire) {
            this.license_expire = license_expire;
        }

        public String getArtif_nm() {
            return artif_nm;
        }

        public void setArtif_nm(String artif_nm) {
            this.artif_nm = artif_nm;
        }

        public String getLegalIdnum() {
            return legalIdnum;
        }

        public void setLegalIdnum(String legalIdnum) {
            this.legalIdnum = legalIdnum;
        }

        public String getLegalIdnumExpire() {
            return legalIdnumExpire;
        }

        public void setLegalIdnumExpire(String legalIdnumExpire) {
            this.legalIdnumExpire = legalIdnumExpire;
        }

        public String getMerchant_id_no() {
            return merchant_id_no;
        }

        public void setMerchant_id_no(String merchant_id_no) {
            this.merchant_id_no = merchant_id_no;
        }

        public String getMerchant_id_expire() {
            return merchant_id_expire;
        }

        public void setMerchant_id_expire(String merchant_id_expire) {
            this.merchant_id_expire = merchant_id_expire;
        }

        public Object getAccount_phone() {
            return account_phone;
        }

        public void setAccount_phone(Object account_phone) {
            this.account_phone = account_phone;
        }

        public String getImg_license() {
            return img_license;
        }

        public void setImg_license(String img_license) {
            this.img_license = img_license;
        }

        public String getImg_idcard_a() {
            return img_idcard_a;
        }

        public void setImg_idcard_a(String img_idcard_a) {
            this.img_idcard_a = img_idcard_a;
        }

        public String getImg_idcard_b() {
            return img_idcard_b;
        }

        public void setImg_idcard_b(String img_idcard_b) {
            this.img_idcard_b = img_idcard_b;
        }

        public String getImg_bankcard_a() {
            return img_bankcard_a;
        }

        public void setImg_bankcard_a(String img_bankcard_a) {
            this.img_bankcard_a = img_bankcard_a;
        }

        public String getImg_bankcard_b() {
            return img_bankcard_b;
        }

        public void setImg_bankcard_b(String img_bankcard_b) {
            this.img_bankcard_b = img_bankcard_b;
        }

        public String getImg_logo() {
            return img_logo;
        }

        public void setImg_logo(String img_logo) {
            this.img_logo = img_logo;
        }

        public String getImg_indoor() {
            return img_indoor;
        }

        public void setImg_indoor(String img_indoor) {
            this.img_indoor = img_indoor;
        }

        public String getImg_idcard_holding() {
            return img_idcard_holding;
        }

        public void setImg_idcard_holding(String img_idcard_holding) {
            this.img_idcard_holding = img_idcard_holding;
        }

        public String getImg_open_permits() {
            return img_open_permits;
        }

        public void setImg_open_permits(String img_open_permits) {
            this.img_open_permits = img_open_permits;
        }

        public String getImg_org_code() {
            return img_org_code;
        }

        public void setImg_org_code(String img_org_code) {
            this.img_org_code = img_org_code;
        }

        public Object getImg_tax_reg() {
            return img_tax_reg;
        }

        public void setImg_tax_reg(Object img_tax_reg) {
            this.img_tax_reg = img_tax_reg;
        }

        public Object getImg_unincorporated() {
            return img_unincorporated;
        }

        public void setImg_unincorporated(Object img_unincorporated) {
            this.img_unincorporated = img_unincorporated;
        }

        public String getImg_private_idcard_a() {
            return img_private_idcard_a;
        }

        public void setImg_private_idcard_a(String img_private_idcard_a) {
            this.img_private_idcard_a = img_private_idcard_a;
        }

        public String getImg_private_idcard_b() {
            return img_private_idcard_b;
        }

        public void setImg_private_idcard_b(String img_private_idcard_b) {
            this.img_private_idcard_b = img_private_idcard_b;
        }

        public Object getTerminal_ID() {
            return Terminal_ID;
        }

        public void setTerminal_ID(Object Terminal_ID) {
            this.Terminal_ID = Terminal_ID;
        }

        public String getMerchantwx1id() {
            return merchantwx1id;
        }

        public void setMerchantwx1id(String merchantwx1id) {
            this.merchantwx1id = merchantwx1id;
        }

        public String getMerchantwx2id() {
            return merchantwx2id;
        }

        public void setMerchantwx2id(String merchantwx2id) {
            this.merchantwx2id = merchantwx2id;
        }

        public String getBankparentID() {
            return bankparentID;
        }

        public void setBankparentID(String bankparentID) {
            this.bankparentID = bankparentID;
        }

        public String getBankprovinceID() {
            return bankprovinceID;
        }

        public void setBankprovinceID(String bankprovinceID) {
            this.bankprovinceID = bankprovinceID;
        }

        public String getBankcityID() {
            return bankcityID;
        }

        public void setBankcityID(String bankcityID) {
            this.bankcityID = bankcityID;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }
    }
}
