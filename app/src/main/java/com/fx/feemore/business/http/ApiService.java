package com.fx.feemore.business.http;

import com.fx.feemore.business.bean.AccountBean;
import com.fx.feemore.business.bean.AllianceBean;
import com.fx.feemore.business.bean.BankCardBean;
import com.fx.feemore.business.bean.CategoryBean;
import com.fx.feemore.business.bean.CollectionBean;
import com.fx.feemore.business.bean.ConsumeRecordRes;
import com.fx.feemore.business.bean.ConsumeRes;
import com.fx.feemore.business.bean.HomeV3MyDataBean;
import com.fx.feemore.business.bean.InterestBean;
import com.fx.feemore.business.bean.InterestInfoBean;
import com.fx.feemore.business.bean.InterestListBean;
import com.fx.feemore.business.bean.NotifyBean;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.bean.OrderRefundBean;
import com.fx.feemore.business.bean.RevenueRecordBean;
import com.fx.feemore.business.bean.StoreStatisticInfoBean;
import com.fx.feemore.business.bean.StoryUserBean;
import com.fx.feemore.business.bean.TeamIncomeRes;
import com.fx.feemore.business.bean.TransactionBean;
import com.fx.feemore.business.bean.VisitorBean;
import com.fx.feemore.business.bean.WChatPayBean;
import com.fx.feemore.business.bean.WithdrawIntroBean;
import com.fx.feemore.business.bean.WithdrawRecordBean;
import com.fx.feemore.business.config.Constants;
import com.fx.feemore.business.ui.order.bean.CommentBean;
import com.fx.feemore.business.ui.order.bean.CommentListBean;
import com.hengxian.baselib.bean.ResponseBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * 网络请求接口定义
 */
public interface ApiService {

    /**
     * 发送手机验证码
     *
     * @param phone 手机号
     * @return
     */
    @GET(Constants.SEND_VERIFICATION_CODE)
    Observable<ResponseBean> sendVerificationCode(@Query("phoneno") String phone);

    /**
     * 校验手机验证码
     *
     * @param phone 手机号
     * @param code  验证码
     * @return
     */
    @GET(Constants.CHECK_VERIFICATION_CODE)
    Observable<ResponseBean> checkVerificationCode(@Query("phoneno") String phone, @Query("code") String code);

    /**
     * 注册开店
     *
     * @param body 参数实体
     * @return
     */
    @POST(Constants.REGISTER)
    Observable<StoryUserBean> registerStore(@Body RequestBody body);

    /**
     * 获取店铺栏目
     *
     * @return
     */
    @GET(Constants.STORY_CATEGORY)
    Observable<List<CategoryBean>> getStoryCategory();

    /**
     * 店铺登录接口
     *
     * @param phone 手机号
     * @param code  验证码
     * @return
     */
    @GET(Constants.STORY_LOGIN)
    Observable<StoryUserBean> login(@Query("phoneno") String phone, @Query("code") String code);

    /**
     * 店铺信息刷新
     *
     * @param storyId 店铺id
     * @param account 当前登录帐号
     * @return
     */
    @GET(Constants.STORY_REFRESH)
    Observable<StoryUserBean> refreshStoreInfo(@Query("storeid") String storyId, @Query("account") String account);

    /**
     * 获取店铺卡包栏目接口
     *
     * @param storyId 店铺id
     * @return
     */
    @GET(Constants.STORY_CARD_CATEGORY)
    Observable<List<CategoryBean>> getStoryCardCategory(@Query("storeid") String storyId);

    /**
     * 发布权益包
     *
     * @param body
     * @return
     */
    @POST(Constants.STORY_CARD_ADD)
    Observable<ResponseBean> addCard(@Body RequestBody body);

    /**
     * 获取店铺发布的权益包列表
     *
     * @param storeId 店铺id
     * @param status  状态；0：申请中；1：审核通过；-1：下线锁定
     * @param page    页码
     * @param count   页数据大小
     * @return
     */
    @GET(Constants.STORY_CARD_LIST)
    Observable<List<InterestBean>> getCardList(@Query("storeid") String storeId, @Query("status") int status, @Query("page") int page, @Query("count") int count);

    /**
     * 获取店铺发布的权益包列表
     *
     * @param storeId 店铺id
     * @param type    状态；1：待审核；2：不通过；3：正常
     * @param page    页码
     * @param count   页数据大小
     * @return
     */
    @GET(Constants.STORY_CARD_LIST_BY_TYPE)
    Observable<List<InterestBean>> getCardListByType(@Query("storeid") String storeId, @Query("type") int type, @Query("page") int page, @Query("count") int count);

    /**
     * 获取店铺统计信息
     *
     * @param storeId 店铺id
     * @return
     */
    @GET(Constants.STORE_TOTAL_INFO)
    Observable<StoreStatisticInfoBean> getStoreStatisticInfo(@Query("storeid") String storeId);

    /**
     * 获取权益包详情
     *
     * @param storeId 店铺id
     * @param cardId  权益包id
     * @return
     */
    @GET(Constants.STORY_CARD_INFO)
    Observable<InterestInfoBean> getCardInfo(@Query("storeid") String storeId, @Query("cardid") String cardId);

    /**
     * 检查店铺是否缴纳保证金
     *
     * @param storeId 店铺id
     * @return
     */
    @GET(Constants.STORE_CHECK_BOND)
    Observable<ResponseBean> checkBond(@Query("storeid") String storeId);

    /**
     * 缴纳保证金
     *
     * @param storeId 店铺id
     * @param bond    服务保证金
     * @param fee     营销费
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.STORE_PAY_BOND)
    Observable<WChatPayBean> payBond(@Field("storeid") String storeId, @Field("bond") String bond, @Field("fee") String fee);

    /**
     * 缴纳保证金（支付宝）
     *
     * @param storeId 店铺id
     * @param bond    服务保证金
     * @param fee     营销费
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.STORE_PAY_BOND_ALIPAY)
    Observable<String> payBondAlipay(@Field("storeid") String storeId, @Field("bond") String bond, @Field("fee") String fee);

    /**
     * 获取店铺发布的权益包列表
     *
     * @param storeId 店铺id
     * @param status  状态：1：审核通过；-99:已下架
     * @param page    页码
     * @param count   页数据大小
     * @return
     */
    @GET(Constants.STORY_CARD_GOOD_LIST)
    Observable<InterestListBean> getCardGoodList(@Query("storeid") String storeId, @Query("status") int status, @Query("page") int page, @Query("count") int count);

    /**
     * 下架权益包
     *
     * @param storeId 店铺id
     * @param cardId  权益包id
     * @return
     */
    @GET(Constants.STORY_CARD_GOOD_OBTAINED)
    Observable<ResponseBean> obtainedCard(@Query("storeid") String storeId, @Query("cardid") String cardId);

    /**
     * 上架权益包
     *
     * @param storeId 店铺id
     * @param cardId  权益包id
     * @return
     */
    @GET(Constants.STORE_CARD_GOOD_SHELF)
    Observable<ResponseBean> shelfCard(@Query("storeid") String storeId, @Query("cardid") String cardId);

    /**
     * 删除权益包
     *
     * @param storeId 店铺id
     * @param cardId  权益包id
     * @return
     */
    @GET(Constants.STORE_CARD_GOOD_DELETE)
    Observable<ResponseBean> deleteCard(@Query("storeid") String storeId, @Query("cardid") String cardId);

    /**
     * 获取维权订单列表
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   页数据大小
     * @return
     */
    @GET(Constants.STORE_ORDER_RIGHTS_PROTECTION)
    Observable<List<OrderRefundBean>> getOrderRightProtections(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count);

    /**
     * 获取维权订单详情接口
     *
     * @param storeId 店铺id
     * @param orderId 订单id
     * @return
     */
    @GET(Constants.STORE_ORDER_RIGHTS_PROTECTION_DETAIL)
    Observable<OrderRefundBean> getOrderRightProtectionInfo(@Query("storeid") String storeId, @Query("orderid") String orderId);

    /**
     * 同意维权订单退款
     *
     * @param storeId 店铺id
     * @param orderId 订单id
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.STORE_ORDER_RIGHTS_PROTECTION_REFUND)
    Observable<ResponseBean> agreeRightProtectionRefund(@Field("storeid") String storeId, @Field("orderid") String orderId);

    /**
     * 驳回维权订单退款
     *
     * @param storeId 店铺id
     * @param orderId 订单id
     * @param reason  驳回原因
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.STORE_ORDER_RIGHTS_PROTECTION_REFUSED)
    Observable<ResponseBean> refusedRightProtectionRefund(@Field("storeid") String storeId, @Field("orderid") String orderId, @Field("text") String reason);

    /**
     * 获取评论列表
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   页数据大小
     * @return
     */
    @GET(Constants.STORE_COMMENT_LIST)
    Observable<CommentListBean> getComments(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count);

    /**
     * 评论详情
     *
     * @param storeId   店铺id
     * @param commentId 评论id
     * @return
     */
    @GET(Constants.STORE_COMMENT_DETAIL)
    Observable<CommentBean> getCommentInfo(@Query("storeid") String storeId, @Query("commentid") String commentId);

    /**
     * 回复评论
     *
     * @param storeId   店铺id
     * @param commentId 评论id
     * @param text      回复内容
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.STORE_COMMENT_REPLY)
    Observable<ResponseBean> answerComment(@Field("storeid") String storeId, @Field("commentid") String commentId, @Field("text") String text);

    /**
     * 获取店铺交易数据信息
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   页数据大小
     * @return
     */
    @GET(Constants.STORE_TRANSACTION_DATA)
    Observable<List<TransactionBean>> getTransactionList(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count);

    /**
     * 获取店铺访问信息
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   页数据大小
     * @return
     */
    @GET(Constants.STORE_VISITOR)
    Observable<List<VisitorBean>> getVisitorList(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count);

    /**
     * 获取店铺收藏信息
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   页数据大小
     * @return
     */
    @GET(Constants.STORE_COLLECTIONS)
    Observable<List<CollectionBean>> getCollectionList(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count);

    /**
     * 核销接口
     *
     * @param url     扫描二维码获取到的核销链接
     * @param storeId 店铺id
     * @return
     */
    @GET
    Observable<ConsumeRes> writeOffConsume(@Url String url, @Query("storeid") String storeId);

    /**
     * 订单核销
     *
     * @param url     核销确认链接
     * @param numbers 当次使用多少次
     * @return
     */
    @GET
    Observable<ResponseBean> orderWriteOff(@Url String url, @Query("numbers") String numbers);

    /**
     * 消费记录
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   数据大小
     * @return
     */
    @GET(Constants.STORE_CONSUME_RECORD)
    Observable<ConsumeRecordRes> getConsumeRecord(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count);

    /**
     * 待成团订单记录
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   数据大小
     * @return
     */
    @GET(Constants.STORE_ORDER_WAIT_GROUP)
    Observable<List<OrderBean>> getWaitGroupOrder(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count);

    /**
     * 已完成订单记录
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   数据大小
     * @return
     */
    @GET(Constants.STORE_ORDER_FINISHED)
    Observable<List<OrderBean>> getFinishedOrder(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count);

    /**
     * 获取我的团队的收益记录
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   数据大小
     * @param level   邀请级别
     * @return
     */
    @GET(Constants.STORE_MY_TEAM)
    Observable<TeamIncomeRes> getTeamIncome(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count, @Query("depth") int level);

    /**
     * 添加银行卡号
     *
     * @param storeId 店铺id
     * @param bank    银行卡号
     * @param name    姓名
     * @param phoneNo 手机号
     * @param code    验证码
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.STORE_ADD_BANK)
    Observable<ResponseBean> addBankCard(@Field("storeid") String storeId, @Field("bankno") String bank, @Field("name") String name, @Field("phoneno") String phoneNo, @Field("code") String code);

    /**
     * 获取银行卡列表接口
     *
     * @param storeId 店铺id
     * @return
     */
    @GET(Constants.STORE_BANK_LIST)
    Observable<ArrayList<BankCardBean>> getBankCards(@Query("storeid") String storeId);

    /**
     * 删除银行卡接口
     *
     * @param storeId    店铺id
     * @param bankCardId 银行卡id
     * @return
     */
    @GET(Constants.STORE_BANK_DEL)
    Observable<ResponseBean> delBankCard(@Query("storeid") String storeId, @Query("bankid") String bankCardId);

    /**
     * 提现
     *
     * @param storeId 店铺id
     * @param bank    银行卡号
     * @param amount  金额
     * @param account 用户帐号
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.STORE_WITHDRAW)
    Observable<ResponseBean> withdraw(@Field("storeid") String storeId, @Field("bankno") String bank, @Field("amount") String amount, @Field("account") String account);

    /**
     * 获取提现说明
     *
     * @return
     */
    @GET(Constants.STORE_WITHDRAW_INTRO)
    Observable<WithdrawIntroBean> getWithdrawIntro(@Query("storeid") String storeId);

    /**
     * 获取提现记录列表
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   数据大小
     * @param status  1	待审核
     *                2	汇款中
     *                3	提现成功
     *                4	被驳回
     *                0	全部
     * @return
     */
    @GET(Constants.STORE_WITHDRAW_LIST)
    Observable<List<WithdrawRecordBean>> getWithdrawRecords(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count, @Query("status") int status);

    /**
     * 获取店铺提现详情
     *
     * @param storeId    店铺id
     * @param withdrawId 提现id
     * @return
     */
    @GET(Constants.STORE_WITHDRAW_INFO)
    Observable<WithdrawRecordBean> getWithdrawInfo(@Query("storeid") String storeId, @Query("withdrawid") String withdrawId);

    /**
     * 更换手机号
     *
     * @param storeId 店铺id
     * @param phoneNo 新手机号
     * @param code    验证码
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.STORE_CHANGE_PHONE)
    Observable<ResponseBean> changePhone(@Field("storeid") String storeId, @Field("phoneno") String phoneNo, @Field("code") String code);

    /**
     * 获取财政收入记录列表
     *
     * @param storeId   店铺id
     * @param page      页码
     * @param count     页数据大小
     * @param startDate 统计开始日期
     * @param endDate   结束日期
     * @return
     */
    @GET(Constants.STORE_REVENUE_LIST)
    Observable<List<RevenueRecordBean>> getRevenueRecords(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count, @Query("startdate") String startDate, @Query("enddate") String endDate);


    /**
     * 获取商家联盟
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   页数据大小
     * @param type    为空值时，表示全部商家联盟；不为空值时表示我的商家联盟
     * @return
     */
    @GET(Constants.STORE_ALLIANCE_LIST)
    Observable<List<AllianceBean>> getAlliances(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count, @Query("type") String type);

    /**
     * 发布商家联盟
     *
     * @param body
     * @return
     */
    @POST(Constants.STORE_PUBLISH_ALLIANCE)
    Observable<ResponseBean> publishAlliance(@Body RequestBody body);

    /**
     * 获取消息列表
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   页数据大小
     * @return
     */
    @GET(Constants.STORE_NOTIFY_LIST)
    Observable<List<NotifyBean>> getStoreNotifyList(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count);

    /**
     * 获取消息详情
     *
     * @param storeId         店铺id
     * @param messagerecordId 消息记录id
     * @return
     */
    @GET(Constants.STORE_NOTIFY_DETAIL)
    Observable<NotifyBean> getStoreNotifyDetail(@Query("storeid") String storeId, @Query("messagerecordid") String messagerecordId);

    /**
     * 获取子账号列表
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param count   页数据大小
     * @return
     */
    @GET(Constants.STORE_ACCOUNT_LIST)
    Observable<List<AccountBean>> getStoreAccounts(@Query("storeid") String storeId, @Query("page") int page, @Query("count") int count);

    /**
     * 删除子账号
     *
     * @param storeId     店铺id
     * @param storeUserId 店铺子账号用户id
     * @return
     */
    @POST(Constants.STORE_ACCOUNT_DEL)
    Observable<ResponseBean> delAccount(@Query("storeid") String storeId, @Query("storeuserid") String storeUserId);

    /**
     * 新增子账号
     *
     * @param storeId 店铺id
     * @param account 帐号
     * @param type    账号类型，1运营者，2操作者
     * @param name    子账号名称
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.STORE_ACCOUNT_ADD)
    Observable<ResponseBean> addAccount(@Query("storeid") String storeId, @Field("account") String account, @Field("type") int type, @Field("name") String name);

    /**
     * 权益包店铺内审核通过
     *
     * @param storeId    店铺id
     * @param account    审核帐号
     * @param interestId 权益包id
     * @return
     */
    @GET(Constants.STORE_INTERESET_REVIEW_PASS)
    Observable<ResponseBean> interesetPass(@Query("storeid") String storeId, @Query("account") String account, @Query("cardid") String interestId);

    /**
     * 权益包店铺内审核不通过
     *
     * @param storeId    店铺id
     * @param account    审核帐号
     * @param interestId 权益包id
     * @param reason     不通过原因
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.STORE_INTEREST_REVIEW_REFUSE)
    Observable<ResponseBean> interesetRefuse(@Field("storeid") String storeId, @Field("account") String account, @Field("cardid") String interestId, @Field("reason") String reason);

    /**
     * v3版首页我的数据接口
     *
     * @param storeId 店铺id
     * @return
     */
    @FormUrlEncoded
    @POST(Constants.HOME_V3_MY_DATA)
    Observable<HomeV3MyDataBean> myData(@Field("storeid") String storeId);
}
