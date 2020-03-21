package com.zhiluo.android.yunpu.http;

import com.zhiluo.android.yunpu.config.MyApplication;

/**
 * author Yuan Cheng
 * blog yuancheng91.top
 * email jasoncheng9111@163.com
 * created 2017/4/26 10:06
 */
public class HttpAPI {
    //正式版和测试版轮换打包测试或发布的时候，注释另一个就行

    /**
     * 测试版
     *
     * @return
     */
    /*public static HttpAPIBeta API() {
        return HttpAPIBeta.getHttpAPIBeta();
    }*/


    /**
     * 正式版
     *
     * @return
     */
    public static HttpAPIOfficial API() {
        return HttpAPIOfficial.getHttpAPIOfficial();
    }


    /**
     * 内测API
     */
    public static final class HttpAPIBeta {
        //会员头像默认地址
        public static final String DEFALUT_HEAD_IMAGE = "/img/nohead.png";

        static final HttpAPIBeta instance = new HttpAPIBeta();
        public static final String MAIN_DOMAIN = "http://djtest.zhiluovip.com";//域名
        public static final String DOMAIN = "http://cttest.zhiluovip.com";//测试版域名

        // 静态方法返回该类的实例
        static HttpAPIBeta getHttpAPIBeta() {
            return instance;
        }

        //编辑商品主类
        public static final String EDIT_MAIN_CLASS = "ProductTypeManager/EditProductType";
        //删除商品主类
        public static final String DELET_MAIN_CLASS = "ProductTypeManager/DelProductType";
        //添加礼品
        public static final String ADD_GIFS_MANAGER = "ProductManger/AddProduct";

    }


    /**
     * 正式版API
     */
    public static final class HttpAPIOfficial {
        public HttpAPIOfficial() {

        }

        static final HttpAPIOfficial instance = new HttpAPIOfficial();

        // 静态方法返回该类的实例
        static HttpAPIOfficial getHttpAPIOfficial() {
            return instance;
        }

        /**
         *
         */
        //会员头像默认地址
        public static final String DEFALUT_HEAD_IMAGE = "/img/nohead.png";



        //员工列表
        public static final String GET_EMPLLIST = MyApplication.BASE_URL+"Empl/GetEmplList";
        //店+运营服务系统升级地址
        public static final String VCODE = MyApplication.CTMONEY_URL+"AppUpgrade/GetNewsVersion";
        //轮播图
        public static final String BANNER = MyApplication.CTMONEY_URL+"Advertising/GetCarouselFigure";
        //消息中心
        public static final String NOTICE = MyApplication.BASE_URL+"Notice/QueryDataList";

        //登陆
        public static final String LOGIN = MyApplication.BASE_URL+"UserManager/Login";
        //商米sn权限验证
        public static final String SN_PERMISSION = MyApplication.CTMONEY_URL + "api/SunmiAuthorization/Verification";

        //自动获取商米授权
        public static final String SN_PERMISSIONself = MyApplication.CTMONEY_URL + "api/ShangMi/AddSunmiAuthorize";

        //查询所有店铺
        public static final String CHECKALLSHOP = MyApplication.BASE_URL+"VIP/QueryAllShop";
        //查询单个店铺信息
        public static final String SINGLE_SHOP_INFO = MyApplication.BASE_URL+"Shops/GetShops";
        //店铺详细信息
        public static final String GET_SHOP_INFO = MyApplication.BASE_URL+"Shops/GetShopInfo";
        //店铺列表
        public static final String GET_SHOP_LIST = MyApplication.BASE_URL+"Shops/GetShopsList";

        //编辑店铺
        public static final String EDITORSHOP = MyApplication.BASE_URL+"Shops/EditShops";
        //会员列表
        public static final String VIPLIST = MyApplication.BASE_URL+"VIP/QueryDataList";
        //会员等级查询
        public static final String USERGRADE = MyApplication.BASE_URL+"VIPGrade/QueryDataList";
        //添加会员
        public static final String ADDUSER = MyApplication.BASE_URL+"VIP/AddVIP";
        //获取验证码图片
        public static final String GETCODE = MyApplication.BASE_URL+"VerifyCode/GetCode";
        //注册发送验证码
        public static final String REGISTER = MyApplication.BASE_URL+"User/RegisterVerify";
        //注册
        public static final String ZHUCE = MyApplication.BASE_URL+"UserManager/Register";


        /**
         * 发送验证码和服务器进行比对
         */
        public static final String SENDCODE = MyApplication.BASE_URL+"User/IsResetPasswordVerify";
        //修改密码
        public static final String SETNEWPWD = MyApplication.BASE_URL+"User/SetNewPwd";
        //添加会员等级
        public static final String ADDGRADE = MyApplication.BASE_URL+"VIPGrade/AddVIPGrade";
        //消费历史记录
        public static final String History = MyApplication.BASE_URL+"ConsumeOrder/QueryAllOrderList";
        //一键加油历史记录
        public static final String YJJY_REPORT = MyApplication.BASE_URL+"JYConsumeOrder/GetConsumeOrderListPageing";

        //限时消费历史记录
        public static final String XSXF_History = MyApplication.BASE_URL+"MemberCheckInAccount/MemberCheckInReport";
        //根据GroupGID查询商品数据
        public static final String GROUPGOODSLIST = MyApplication.BASE_URL+"ProductManger/GetGroupGoodsList";
        //查询服务商品
        public static final String SERVICEGOODS = MyApplication.BASE_URL+"ProductManger/QueryDataList";
        //查询带套餐商品列表
        public static final String COMBOGOODS = MyApplication.BASE_URL+"ProductManger/QueryProductAndComboDataList";
        //出入库商品列表查询
        public static final String GOODS_HOUSE = MyApplication.BASE_URL+"ProductManger/GetMulitPurchasingProductList";
        //查询商品规格
        public static final String GOODSMODEL = MyApplication.BASE_URL+"ProductConfig/GetModelList";
        //查询油品名称
        public static final String GET_OIL_LIST = MyApplication.BASE_URL+"OilsManager/GetOilsList";

        //查询单个会员信息
        public static final String QUERY_SINGLE_MEMBER = MyApplication.BASE_URL+"VIP/QuerySingle";
        //查询用户数
        public static final String QUERY_USER_NUM = MyApplication.BASE_URL+"UserManager/QueryUsersList";
        //删除会员
        public static final String DELETEVIP = MyApplication.BASE_URL+"VIP/DelVIP";
        //修改会员信息
        public static final String EDIVIP = MyApplication.BASE_URL+"VIP/EditVIP";
        //修改会员密码
        public static final String ALTERPWD = MyApplication.BASE_URL+"VIP/AlterPwd";
        //会员换卡
        public static final String ALTERCARD = MyApplication.BASE_URL+"VIP/AlterCard";

        //会员延期
        public static final String VIPDELAY = MyApplication.BASE_URL+"VIP/Delay";
        //产品类型
        public static final String PRODUCTTYPE = MyApplication.BASE_URL+"ProductTypeManager/QueryAllProductTypeBySM_ID";
        //添加商品,新接口
        public static final String ADDGOODS = MyApplication.BASE_URL+"ProductManger/AddProduct";
        //添加商品分类
        public static final String ADDTYPE = MyApplication.BASE_URL+"ProductTypeManager/AddProductType";
        //删除商品
        public static final String DELETEGOODS = MyApplication.BASE_URL+"ProductManger/DelProduct";
        //查询商品
        public static final String SEARCHGOODS = MyApplication.BASE_URL+"ConsumeOrder/QueryProductDiscountList";
        //修改商品
        public static final String EDITGOODS = MyApplication.BASE_URL+"ProductManger/EditProduct";

        //获取礼品分类
        public static final String GIFTTYPELIST = MyApplication.BASE_URL+"GiftType/QueryDataList";
        //积分兑换礼品
        public static final String EXCHANGEGIFT = MyApplication.BASE_URL+"Integral/IntegralExchangeGifts";
        //充次信息
        public static final String CHARGELIST = MyApplication.BASE_URL+"WouldOrder/QueryChargeAccountList";
        //会员冲刺
        public static final String VIPADDSER = MyApplication.BASE_URL+"Charge/AddCharge";
        //查询企业短信库存查询
        public static final String SMSNUM = MyApplication.BASE_URL+"Messages/QuerySMSInventory";
        //获取签名列表
        public static final String SIGNLIST = MyApplication.BASE_URL+"SignatureManagement/GetSignList";
        //发送短信
        public static final String SENDSMS = MyApplication.BASE_URL+"Messages/MsgSend";
        //打印设置
        public static final String PRINTSET = MyApplication.BASE_URL+"PrintSet/GetPrintSet";
         //短信开关
        public static final String SMS_LIST = MyApplication.BASE_URL+"SysSMSTemp/GetList";
        //yunpu分享网页
        public static final String YUNPU = "http://m.yunvip123.com";
        //加减积分
        public static final String ADDJIFEN = MyApplication.BASE_URL+"Integral/RushMinus";
        //获取会员数
//        public static final String GETVIPNUM = MyApplication.BASE_URL+"Report/GetWelcomeData";
        public static final String GETVIPNUM = MyApplication.BASE_URL+"VIP/GetMberNumber";

        /**
         * 消费接口
         *
         * **/
        //会员充值接口提交
        public static final String MEM_RECHARGE_SUB = MyApplication.BASE_URL+"Recharge/SubmitRecharge";

        //会员充值接口支付
        public static final String MEM_RECHARGE_PAY = MyApplication.BASE_URL+"Recharge/PaymentRecharge";

        //商品消费提交
        public static final String GOODS_CONSUME_SUB = MyApplication.BASE_URL+"ConsumeOrder/SubmitConsumOrder";

        //商品消费支付
        public static final String GOODS_CONSUME_PAY = MyApplication.BASE_URL+"ConsumeOrder/PaymentConsumOrder";

        //快速消费提交
        public static final String FAST_CONSUME_SUB = MyApplication.BASE_URL+"ConsumeOrder/SubmitCelerityOrder";

        //快速消费支付
        public static final String FAST_CONSUME_PAY = MyApplication.BASE_URL+"ConsumeOrder/PaymentCelerityOrder";

        //会员充次提交
        public static final String MEM_CHARGE_SUB = MyApplication.BASE_URL+"Charge/SubmitChargeBatch";

        //会员充次支付
        public static final String MEM_CHARGE_PAY = MyApplication.BASE_URL+"Charge/PaymentConsumOrder";

        //生成充次消费订单
        public static final String CHAORDER = MyApplication.BASE_URL+"WouldOrder/AddWouldOrder";
        //定项消费消费订单
        public static final String dingxiang = MyApplication.BASE_URL+"WouldOrder/ConstantConsumeOrder";


        //生成充次消费单号
        public static final String CHACODE = MyApplication.BASE_URL+"WouldOrder/GetWouldOrderCode";



        /**
         * 获取订单
         * 传递参数CODE
         * SP:商品消费单号 KS:快速消费单号 JS：计次 CZ:充值 CC:充次 TX:提现 LP:礼品
         */

        //短信模板
        public static final String SMSTEMPLATES = MyApplication.BASE_URL+"MessageTemp/QueryTempClassDataList";
        //添加短信模板
        public static final String ADDTEMPLATES = MyApplication.BASE_URL+"MessageTemp/AddMsgTemplate";
        //获取自定义短信模板
        public static final String GETMSGTEMPLATES = MyApplication.BASE_URL+"MessageTemp/GetMsgTemplateList";
        //密码验证
        public static final String PASSWORDVERIFY = MyApplication.BASE_URL+"VIP/PasswordVerify";
        //员等级相关信息
        public static final String EDITVIPGRADE = MyApplication.BASE_URL+"VIPGrade/EditVIPGrade";
        /**
         * 上传会员头像
         */
        public static final String UPLOAD_MEMBER_PHOTO = MyApplication.BASE_URL+"RecvImage/UploadImg";
        //获取优惠活动列表
        public static final String GET_DISCOUNT_TYPE = MyApplication.BASE_URL+"RechargePackage/GetAllList";
        public static final String MAIN_DOMAIN = MyApplication.IMAGE_URL;//域名

        public static final String DOMAIN = MyApplication.CTMONEY_URL;//正式版域名
        //会员标签
        public static final String MEMBER_LABEL = MyApplication.BASE_URL+"MemberLabel/QueryDataList";
        //获取商品消费打印参数
        public static final String GET_GOODS_PRINT_DATA = MyApplication.BASE_URL+"PrintTemplate/PrintConsumeOrder";
        //获取快速消费打印参数
        public static final String GET_FAST_PRINT_DATA = MyApplication.BASE_URL+"PrintTemplate/PrintConsumeOrder";
        //获取计次消费打印参数
        public static final String GET_JICI_PRINT_DATA = MyApplication.BASE_URL+"PrintTemplate/PrintConsumeTimes";
        //打印小票 会员充值 参数 GID
        public static final String PRINT_HYCZ = MyApplication.BASE_URL+"PrintTemplate/PrintChargeMoney";
        //打印小票 积分兑换 参数 GID
        public static final String PRINT_JFDH = MyApplication.BASE_URL+"PrintTemplate/PrintIntegralExchange";
        //打印小票 会员充次 参数 GID
        public static final String PRINT_HYCC = MyApplication.BASE_URL+"PrintTemplate/PrintChargeTimes";
        //打印小票 会员开卡 参数 GID
        public static final String PRINT_HYKK = MyApplication.BASE_URL+"PrintTemplate/PrintVipOpenCard";
        //打印小票 入库 参数 GID
        public static final String PRINT_RK = MyApplication.BASE_URL+"PrintTemplate/StockInReportDetail";
        //打印小票 出库 参数 GID
        public static final String PRINT_CK = MyApplication.BASE_URL+"PrintTemplate/StockOutReportDetail";
        //打印小票 出库 参数 GID
        public static final String PRINT_YJJY = MyApplication.BASE_URL+"PrintTemplate/PrintRefuelOrder";
        //打印小票 交班 参数 GID
        public static final String PRINT_JB = MyApplication.BASE_URL+"PrintTemplate/PrintShift";

        //打印小票 退货 参数 GID
        public static final String PRINT_SPTH = MyApplication.BASE_URL+"PrintTemplate/ProntReturnOrderDetail";
        //获取有效优惠活动
        public static final String DISSCOUNT_ACTIVITY = MyApplication.BASE_URL+"RechargePackage/GetValidList";
        //打印参数设置
        public static final String PRINT_SET = MyApplication.BASE_URL+"PrintTemplate/GetPrintTemplateList";
        //APP更新次数
        public static final String UPDATE_TIMES = MyApplication.CTMONEY_URL+"AppUpgrade/UpdateVersionTimes";
        /**
         * 报表相关
         */
        //会员充次记录
        public static final String REPORT_RECHARGE_TIMES = MyApplication.BASE_URL+"Charge/GetTimesRecords";
        //延期记录
        public static final String REPORT_DELAY = MyApplication.BASE_URL+"Report/GetDelayReport";
        //积分明细
        public static final String REPORT_POINT_DETAIL = MyApplication.BASE_URL+"Integral/GetIntegralDetailedList";
        //金额明细
        public static final String REPORT_MONEY_DETAIL = MyApplication.BASE_URL+"BillingDetails/GetAmountDetails";
        //会员签到记录
        public static final String REPORT_MEMBER_SIGN = MyApplication.BASE_URL+"MemberCheckIn/MembersCheckInReport";
        //推荐列表
        public static final String REPORT_RECOMEND_LIST = MyApplication.BASE_URL+"VIP/QueryRecommendMemberPage";
        //返利明细
        public static final String REPORT_REBATE_DETAIL = MyApplication.BASE_URL+"Integral/QueryRebagePage";
        //提成员工
        public static final String REPORT_STAFF_REBATE = MyApplication.BASE_URL+"StaffCommission/GetEmployeeRoyaltyRecordsPage";

        //订单记录里的消费总计
        public static final String QUERY_ORDER = MyApplication.BASE_URL+"ConsumeOrder/QueryOrderStatics";
        //计次消费订单记录
        public static final String REPORT_TIMES_CONSUME = MyApplication.BASE_URL+"WouldOrder/QueryWouldOrderList";
        //积分兑换记录
        public static final String REPORT_POINT_EXCHANGE = MyApplication.BASE_URL+"Integral/GetExchangeGiftsList";
        //获取订单记录里商品详情
        public static final String GOOD_DETAIL = MyApplication.BASE_URL+"GoodsOrderDetail/GetGoodsDetailList";
        //获取订单记录里计次商品详情
        public static final String TIME_DETAIL = MyApplication.BASE_URL+"WouldOrderDetail/QueryOrderDetailList";
        //上传礼品图片
        public static final String UPLOAD_GIFT_IMAGE = MyApplication.BASE_URL+"RecvImage/UploadImgByCode";
        //获取交班综合数据统计
        public static final String SHIFT_AMOUNT = MyApplication.BASE_URL+"Shift/GetShiftAmount";
        public static final String SHIFT_AMOUNT_COPY = MyApplication.BASE_URL+"Shift/GetShiftAmountCopy";
        //获取会员分析数据统计
        public static final String VIP_ANALYSIS = MyApplication.BASE_URL+"Report/GetMemberSalesAnalysisData";
        //获取商品销售统计
        public static final String GOOD_ANALYSIS = MyApplication.BASE_URL+"Report/GetGoodsSalesAnalysisData";

        //商品消费退货
        public static final String RETURE_ORDER = MyApplication.BASE_URL+"ReturnOrder/AddReturnOrder";
        //快速消费撤单
        public static final String UNDO_FAST_ORDER = MyApplication.BASE_URL+"ConsumeOrder/UndoFastOrder";
        //计次消费撤单
        public static final String UNDO_ORDER = MyApplication.BASE_URL+"WouldOrder/UndoOrder";
        //积分兑换撤单
        public static final String JF_UNDO_ORDER = MyApplication.BASE_URL+"Integral/UndoOrder";
        //会员充值撤单
        public static final String CZ_UNDO_ORDER = MyApplication.BASE_URL+"Recharge/UndoMoneyOrder";
        //会员充次撤单
        public static final String CC_UNDO_ORDER = MyApplication.BASE_URL+"Charge/UndoTimeOrder";
        //一键加油撤单
        public static final String YJJY_UNDO_ORDER = MyApplication.BASE_URL+"JYConsumeOrder/UndoOrder";

        //标记消息已读
        public static final String FLAG_HAVE_READ = MyApplication.BASE_URL+"Notice/AddNoticeRelation";
        //获取库存数据
        public static final String STOCK_TAKING = MyApplication.BASE_URL+"StockChecking/GetStockInventoryDataList";
        //批量盘点数据
        public static final String STOCK_MANY = MyApplication.BASE_URL+"ProductManger/GetMulitPurchasingProductList";

        //获取供应商
        public static final String PRIVADER = MyApplication.BASE_URL+"SupplierManager/QuerySupplierList";
        //添加供应商
        public static final String ADD_PROVIDER = MyApplication.BASE_URL+"SupplierManager/AddSupplier";
        //修改供应商
        public static final String MODIFY_PROVIDER = MyApplication.BASE_URL+"SupplierManager/EditSupplier";
        //库存盘点
        public static final String STOCK = MyApplication.BASE_URL+"StockChecking/AdjustNumber";
        //批量库存盘点
        public static final String STOCK_MUCH = MyApplication.BASE_URL+"StockChecking/AddStockInfo";

        //采购入库
        public static final String INPUT_STOCK = MyApplication.BASE_URL+"StockManager/StockIn";
        //出库
        public static final String OUT_STOCK = MyApplication.BASE_URL+"StockManager/StockOut";
        //出库
        public static final String OUT_STOCK_DETAIL_LIST = MyApplication.BASE_URL+"StockManager/GetStockInDetailDataList";



        //编辑计次规则
        public static final String EDIT_RULES = MyApplication.BASE_URL+"WouldRules/EditRules";
        //添加计次规则
        public static final String ADD_RULES = MyApplication.BASE_URL+"WouldRules/AddRules";
        //删除计次规则
        public static final String DEL_RULES = MyApplication.BASE_URL+"WouldRules/DelRules";
        //计次规则列表
        public static final String LIST_RULES = MyApplication.BASE_URL+"WouldRules/QueryPageDataList";

        //登录预加载接口
        public static final String PRE_LOAD = MyApplication.BASE_URL+"Report/PreloadingData";
        //获取店铺行业信息
        public static final String INDURY_TYPE = MyApplication.BASE_URL+"Shops/GetIndustryList";
        //获取店铺行业信息
        public static final String EDIT_INDURY_TYPE = MyApplication.BASE_URL+"Shops/UpdateShopIndustry";
        //获取店铺权限信息
        public static final String APP_LIMIT = MyApplication.BASE_URL+"APPMenuRoleRelation/QueryAppMenuList";
        //退出登录
        public static final String SIGNOUT = MyApplication.BASE_URL+"User/SignOut";
        //快速计次项目查询
        public static final String FASTCHARGE = MyApplication.BASE_URL+"ServicesGoods/QueryProductDiscountList";

        //添加用户
        public static final String ADD_USER = MyApplication.BASE_URL+"UserManager/AddUsers";
        //获取店铺列表
        public static final String GETSHOPLIST = MyApplication.BASE_URL+"Shops/GetShopsList";
        //获取用户列表
        public static final String USER_MANAGER_LIST = MyApplication.BASE_URL+"UserManager/QueryUsersList";
        //获取员工列表
        public static final String EMPLOEE_LIST = MyApplication.BASE_URL+"Empl/QueryEmpl";
        //获取员工提成部门
        public static final String EMPLOEE_DEP = MyApplication.BASE_URL+"StaffCommission/GetValidRule";

        //删除角色
        public static final String DELET_ROLE = MyApplication.BASE_URL+"Role/DelRole";
        //获取角色列表
        public static final String ROLE_LIST = MyApplication.BASE_URL+"Role/GetRoleList";
        //升降级设置
        public static final String RISEANDFALL = MyApplication.BASE_URL+"VIPGrade/UpDownSet";

        //会员签到
        public static final String FAST_CHECKIN = MyApplication.BASE_URL+"MemberCheckIn/MemberFastCheckIn";
        //会员签到项目查找
        public static final String SEARCH_PROJECT = MyApplication.BASE_URL+"MemberCheckIn/GetMemberCheckInProjectList";
        //限时消费
        public static final String CHECK_IN_ACCOUNT = MyApplication.BASE_URL+"MemberCheckInAccount/MemberCheckIn";
        //限时消费-小票打印
        public static final String CHECK_IN_PRINT = MyApplication.BASE_URL+"PrintTemplate/PrintMberCheckin";
        /**交班**///限时消费-小票打印
        public static final String FAST_HANDOVER = MyApplication.BASE_URL+"Shift/FastHandover";



        /**一键加油*/
        //一键加油提交
        public static final String ONE_KEY_OIL_SUB = MyApplication.BASE_URL+"JYConsumeOrder/SubmitRefuelOrder";
        //一键加油支付
        public static final String ONE_KEY_OIL_PAY = MyApplication.BASE_URL+"JYConsumeOrder/PaymentRefuelOrder";



        /**支出管理***/
        //支出录入
        public static final String AddExpenses = MyApplication.BASE_URL+"Expenses/AddExpenses";
        //获取支出类别列表
        public static final String Expenses_TypeLsit = MyApplication.BASE_URL+"Expenses/GetTypeList";
        //增加支出类别
        public static final String Expenses_AddType = MyApplication.BASE_URL+"Expenses/AddType";
        //删除支出类别
        public static final String Expenses_DelteType = MyApplication.BASE_URL+"Expenses/DelType";
        //编辑支出类别
        public static final String Expenses_EditType = MyApplication.BASE_URL+"Expenses/EditType";
        //支出明细列表
        public static final String Expenses_DetailList = MyApplication.BASE_URL+"Expenses/GetExpensesDetailListPage";
        //删除支出明细
        public static final String Expenses_DelDetail = MyApplication.BASE_URL+"Expenses/DelExpenses";
        //编辑支出明细
        public static final String Expenses_EditDetail = MyApplication.BASE_URL+"Expenses/EditExpenses";
        //编辑支出明细
        public static final String Expenses_DetailAnalysis = MyApplication.BASE_URL+"Expenses/GetExpensesDetailAnalysis";


        //扫呗支付状态查询
        public static final String MERCHANT_INFO = MyApplication.BASE_URL+ "Merchant/GetMerchantInfo";
        //扫呗支付申请
        public static final String MERCHANT = MyApplication.BASE_URL + "Merchant/AddMerchant";
        //扫呗支付申请
        public static final String UPDATAMERCHANT = MyApplication.BASE_URL + "Merchant/UpdateMerchant";

        //扫呗支付二级行业类别
        public static final String getmccwx1 ="https://saobei.lcsw.cn/lcsw/mccwx/getmccuni1";
        //扫呗支付三级行业类别
        public static final String getmccwx2 = "https://saobei.lcsw.cn/lcsw/mccwx/getmccuni2";
        //扫呗支付申请获取市名
        public static final String getcity = "https://saobei.lcsw.cn/lcsw/provincecitystd/getcity";
        //扫呗支付申请获取区域名
        public static final String getcounty = "https://saobei.lcsw.cn/lcsw/provincecitystd/getcounty";
        //扫呗支付申请获取银行名
        public static final String getbank = "https://saobei.lcsw.cn/lcsw/bank/getbank";

        //申请开通扫呗支付
        public static final String OPENSAOBEI = MyApplication.BASE_URL+"Merchant/AddTermina";



    }

}

