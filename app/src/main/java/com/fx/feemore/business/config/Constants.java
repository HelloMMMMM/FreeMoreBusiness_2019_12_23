package com.fx.feemore.business.config;

import com.fx.feemore.business.http.ServiceUrlUtil;

import java.util.HashMap;

/**
 * function:基础常量
 * author: frj
 * modify date: 2018/12/23
 */
public interface Constants {


    String BASE_URL = "https://nanjing.mhdsh.cn/";

    //public static final String BASE_URL = "https://nanjing.mhdsh.cn/";

    String API_URL = BASE_URL + "api/store/";

    String ROOT_DIR = "FeeMoreBusiness";

    /**
     * 获取验证码
     */
    String SEND_VERIFICATION_CODE = "api/store/getPhoneCode";

    /**
     * 校验验证码
     */
    String CHECK_VERIFICATION_CODE = "api/store/checkPhoneNo";

    /**
     * 注册开店
     */
    String REGISTER = "api/store/register";
    /**
     * 获取店铺栏目类型
     */
    String STORY_CATEGORY = "api/store/category/list";
    /**
     * 店铺登录
     */
    String STORY_LOGIN = "api/store/login";

    /**
     * 店铺刷新接口
     */
    String STORY_REFRESH = "api/store/refresh";

    /**
     * 获取店铺卡包栏目列表
     */
    String STORY_CARD_CATEGORY = "api/store/card/categorys";

    /**
     * 店铺上传卡包
     */
    String STORY_CARD_ADD = "api/store/card/add";

    /**
     * 店铺卡包申请列表
     */
    String STORY_CARD_LIST = "api/store/card/list";

    /**
     * 店铺卡包申请列表(根据状态查)
     */
    String STORY_CARD_LIST_BY_TYPE = "api/store/card/status/list";

    /**
     * 店铺统计
     */
    String STORE_TOTAL_INFO = "api/store/statistics";

    /**
     * 店铺卡包信息
     */
    String STORY_CARD_INFO = "api/store/card/info";

    /**
     * 检查店铺是否缴纳保证金接口
     */
    String STORE_CHECK_BOND = "api/store/checkbond";

    /**
     * 店铺缴纳保证金接口
     */
    String STORE_PAY_BOND = "api/store/wxprepay";

    /**
     * 店铺缴纳保证金接口（支付宝）
     */
    String STORE_PAY_BOND_ALIPAY = "api/store/payment";

    /**
     * 店铺卡包列表
     */
    String STORY_CARD_GOOD_LIST = "api/store/goods/list";
    /**
     * 店铺卡包下架
     */
    String STORY_CARD_GOOD_OBTAINED = "api/store/goods/pullshelves";
    /**
     * 店铺卡包上架
     */
    String STORE_CARD_GOOD_SHELF = "api/store/goods/putsale";
    /**
     * 店铺卡包删除
     */
    String STORE_CARD_GOOD_DELETE = "api/store/goods/del";

    /**
     * 店铺订单维权列表
     */
    String STORE_ORDER_RIGHTS_PROTECTION = "api/store/order/safeguard/list";

    /**
     * 店铺订单-维权订单详情
     */
    String STORE_ORDER_RIGHTS_PROTECTION_DETAIL = "api/store/order/safeguard/info";

    /**
     * 店铺订单-维权订单-同意退款
     */
    String STORE_ORDER_RIGHTS_PROTECTION_REFUND = "api/store/order/safeguard/agreement";
    /**
     * 店铺订单-维权订单-驳回退款
     */
    String STORE_ORDER_RIGHTS_PROTECTION_REFUSED = "api/store/order/safeguard/bohui";

    /**
     * 店铺评论列表
     */
    String STORE_COMMENT_LIST = "api/store/comment/list";

    /**
     * 店铺评论详情
     */
    String STORE_COMMENT_DETAIL = "api/store/comment/info";
    /**
     * 店铺评论回复
     */
    String STORE_COMMENT_REPLY = "api/store/comment/reply";

    /**
     * 店铺交易数据接口
     */
    String STORE_TRANSACTION_DATA = "api/store/order/transaction/list";

    /**
     * 店铺访客列表接口
     */
    String STORE_VISITOR = "api/store/visitorrecord/list";

    /**
     * 店铺被收藏列表接口
     */
    String STORE_COLLECTIONS = "api/store/collection/list";

    /**
     * 店铺消费记录
     */
    String STORE_CONSUME_RECORD = "api/store/card/userecord/list";
    /**
     * 待成团订单
     */
    String STORE_ORDER_WAIT_GROUP = "api/store/order/notgroup/list";
    /**
     * 已完成订单
     */
    String STORE_ORDER_FINISHED = "api/store/order/finished/list";
    /**
     * 我的团队列表
     */
    String STORE_MY_TEAM = "api/store/performance/list";

    /**
     * 添加银行卡
     */
    String STORE_ADD_BANK = "api/store/bank/add";
    /**
     * 获取已添加的银行卡列表
     */
    String STORE_BANK_LIST = "api/store/bank/list";
    /**
     * 删除银行卡接口
     */
    String STORE_BANK_DEL = "api/store/bank/del";
    /**
     * 店铺提现
     */
    String STORE_WITHDRAW = "api/store/withdraw";
    /**
     * 提现说明
     */
    String STORE_WITHDRAW_INTRO = "api/store/withdraw/explain";
    /**
     * 店铺提现列表
     */
    String STORE_WITHDRAW_LIST = "api/store/withdraw/list";
    /**
     * 店铺提现详情
     */
    String STORE_WITHDRAW_INFO = "api/store/withdraw/info";
    /**
     * 更换手机号
     */
    String STORE_CHANGE_PHONE = "api/store/changephoneno";
    /**
     * 商家收入记录
     */
    String STORE_REVENUE_LIST = "api/store/incomerecord/list";
    /**
     * 商家联盟列表
     */
    String STORE_ALLIANCE_LIST = "api/store/merchantalliance/list";
    /**
     * 发起商家联盟
     */
    String STORE_PUBLISH_ALLIANCE = "api/store/merchantalliance/add";

    /**
     * 订单通知列表
     */
    String STORE_NOTIFY_LIST = "api/store/messagerecord/list";
    /**
     * 订单通知详情
     */
    String STORE_NOTIFY_DETAIL = "api/store/messagerecord/info";

    /**
     * 子账号列表
     */
    String STORE_ACCOUNT_LIST = "api/store/ziaccount/list";

    /**
     * 添加子账号
     */
    String STORE_ACCOUNT_ADD = "api/store/addaccount";

    /**
     * 删除子账号
     */
    String STORE_ACCOUNT_DEL = "api/store/delaccount";

    /**
     * 权益包店铺内审核通过
     */
    String STORE_INTERESET_REVIEW_PASS = "api/store/card/pass";

    /**
     * 权益包店铺内审核不通过
     */
    String STORE_INTEREST_REVIEW_REFUSE = "api/store/card/fail";

    /**
     * v3版home页得我的数据+平台数据
     */
    String HOME_V3_MY_DATA = "api/system/store/statistics";
}
