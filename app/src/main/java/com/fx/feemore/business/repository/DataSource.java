package com.fx.feemore.business.repository;

import android.text.TextUtils;

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
import com.fx.feemore.business.http.ApiService;
import com.fx.feemore.business.ui.order.bean.CommentBean;
import com.fx.feemore.business.ui.order.bean.CommentListBean;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.bean.ResponseBean;
import com.hengxian.baselib.dagger.scope.ApplicationScope;
import com.hengxian.baselib.utils.RxSchedules;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


/**
 * 数据仓库
 */
@ApplicationScope
public class DataSource {

    private ApiService apiService;

    @Inject
    public DataSource(ApiService apiService) {
        this.apiService = apiService;
    }


    /**
     * 发送手机验证码
     *
     * @param phone 手机号
     * @return
     */
    public Observable<ResponseBean> sendVerificationCode(String phone) {
        return apiService.sendVerificationCode(phone).compose(RxSchedules.ioToMain());
    }

    /**
     * 校验手机验证码
     *
     * @param phone 手机号
     * @param code  验证码
     * @return
     */
    public Observable<ResponseBean> checkVerificationCode(String phone, String code) {
        return apiService.checkVerificationCode(phone, code).compose(RxSchedules.ioToMain());
    }

    /**
     * 注册开店
     *
     * @param name              店铺名称
     * @param phone             手机号
     * @param areaCode          区域码
     * @param address           地址
     * @param lon               经度
     * @param lat               纬度
     * @param type              店铺类型
     * @param storePhotoPath    门店照片地址
     * @param inStorePhotoPath  店内照片地址
     * @param idCardPhotoPath   身份证照片地址
     * @param businessPhotoPath 营业执照照片地址
     * @return
     */
    public Observable<StoryUserBean> registerStore(String name, String phone, String areaCode, String address, String lon, String lat, String type, String storePhotoPath, String inStorePhotoPath, String idCardPhotoPath, String businessPhotoPath) {
        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
        File storeFile = new File(storePhotoPath);
        build.addFormDataPart("img1", storeFile.getName(), RequestBody.create(MediaType.parse("image/*"), storeFile));
        File inStoreFile = new File(inStorePhotoPath);
        build.addFormDataPart("img2", storeFile.getName(), RequestBody.create(MediaType.parse("image/*"), inStoreFile));
        File idCardFile = new File(idCardPhotoPath);
        build.addFormDataPart("img3", storeFile.getName(), RequestBody.create(MediaType.parse("image/*"), idCardFile));
        File businessFile = new File(businessPhotoPath);
        build.addFormDataPart("img4", storeFile.getName(), RequestBody.create(MediaType.parse("image/*"), businessFile));

        build.addFormDataPart("name", VerificationUtil.verifyDefault(name));
        build.addFormDataPart("phoneno", VerificationUtil.verifyDefault(phone));
        build.addFormDataPart("areacode", VerificationUtil.verifyDefault(areaCode));
        build.addFormDataPart("address", VerificationUtil.verifyDefault(address));
        build.addFormDataPart("lon", VerificationUtil.verifyDefault(lon));
        build.addFormDataPart("lat", VerificationUtil.verifyDefault(lat));
        build.addFormDataPart("categoryid", VerificationUtil.verifyDefault(type));
        RequestBody requestBody = build.build();
        return apiService.registerStore(requestBody).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取店铺栏目类型
     *
     * @return
     */
    public Observable<List<CategoryBean>> getStoryCategory() {
        return apiService.getStoryCategory().compose(RxSchedules.ioToMain());
    }

    /**
     * 登录
     *
     * @param phone 手机号
     * @param code  验证码
     * @return
     */
    public Observable<StoryUserBean> login(String phone, String code) {
        return apiService.login(phone, code).compose(RxSchedules.ioToMain());
    }

    /**
     * 刷新店铺信息
     *
     * @param storyId 店铺id
     * @param account 当前登录帐号
     * @return
     */
    public Observable<StoryUserBean> refreshStoryInfo(String storyId, String account) {
        return apiService.refreshStoreInfo(storyId, account).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取店铺权益包栏目
     *
     * @param storyId 店铺id
     * @return
     */
    public Observable<List<CategoryBean>> getStoryCardCategory(String storyId) {
        return apiService.getStoryCardCategory(storyId).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取权益包列表
     *
     * @param storyId  店铺id
     * @param status   状态值；0：申请中；1：审核通过；-1：下线锁定
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<List<InterestBean>> getInterestList(String storyId, int status, int page, int pageSize) {
        return apiService.getCardList(storyId, status, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取店铺发布的权益包列表
     *
     * @param storeId  店铺id
     * @param type     状态；1：待审核；2：不通过；3：正常
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<List<InterestBean>> getInterestListByType(String storeId, int type, int page, int pageSize) {
        return apiService.getCardListByType(storeId, type, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取店铺统计信息
     *
     * @param storeId 店铺id
     * @return
     */
    public Observable<StoreStatisticInfoBean> getStoreStatisticInfo(String storeId) {
        return apiService.getStoreStatisticInfo(storeId).compose(RxSchedules.ioToMain());
    }

    /**
     * 发布权益包
     *
     * @param storyId            //店铺id
     * @param name               //名称
     * @param des                //描述
     * @param categoryId         //栏目id
     * @param type               //权益包类型；1普通，2拼团
     * @param useNum             //使用人数
     * @param quantity           //数量
     * @param availablenum       //有效使用次数
     * @param startDate          //开始时间
     * @param endDate            //结束时间
     * @param price              //价格
     * @param isPresell          //是否预售；0否，1是
     * @param isRefund           //是否无理由退款；0否，1是
     * @param startCostTime      //上午消费时间
     * @param endCostTime        //下午消费时间
     * @param contact            //联系方式
     * @param isHolidayAvailable //是否节假日可用：0否，1是
     * @param consumeWeekStart   //一周内可开始使用周日期
     * @param consumeWeekEnd     //一周内结束使用周日期
     * @param notice             //商家公告
     * @param account            //发布者帐号
     * @param imgs               //权益包图片集
     * @return
     */
    public Observable<ResponseBean> publishInterest(String storyId, String name, String des, String categoryId, String type, String useNum, String quantity, String availablenum, String startDate, String endDate, String price, String isPresell, String isRefund, String startCostTime, String endCostTime, String contact, String isHolidayAvailable, String consumeWeekStart, String consumeWeekEnd, String notice, String currentUseType, String currentType, String account, List<String> imgs) {
        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (VerificationUtil.noEmpty(imgs)) {
            for (String str : imgs) {
                File storeFile = new File(str);
                build.addFormDataPart("files", storeFile.getName(), RequestBody.create(MediaType.parse("image/*"), storeFile));
            }
        }
        build.addFormDataPart("name", VerificationUtil.verifyDefault(name));
        build.addFormDataPart("storeid", VerificationUtil.verifyDefault(storyId));
        build.addFormDataPart("description", VerificationUtil.verifyDefault(des));
        build.addFormDataPart("categoryid", VerificationUtil.verifyDefault(categoryId));
        build.addFormDataPart("type", VerificationUtil.verifyDefault(type));
        build.addFormDataPart("usersnum", VerificationUtil.verifyDefault(useNum));
        build.addFormDataPart("quantity", VerificationUtil.verifyDefault(quantity));
        build.addFormDataPart("availablenum", VerificationUtil.verifyDefault(availablenum));
        build.addFormDataPart("startdate", VerificationUtil.verifyDefault(startDate));
        build.addFormDataPart("enddate", VerificationUtil.verifyDefault(endDate));
        build.addFormDataPart("price", VerificationUtil.verifyDefault(price));
        build.addFormDataPart("ispresell", VerificationUtil.verifyDefault(isPresell));
        build.addFormDataPart("isrefund", VerificationUtil.verifyDefault(isRefund));
        build.addFormDataPart("iscurrency", VerificationUtil.verifyDefault(isHolidayAvailable));
        build.addFormDataPart("startcosttime", VerificationUtil.verifyDefault(startCostTime));
        build.addFormDataPart("endcosttime", VerificationUtil.verifyDefault(endCostTime));
        build.addFormDataPart("startcostdate", VerificationUtil.verifyDefault(consumeWeekStart));
        build.addFormDataPart("endcostdate", VerificationUtil.verifyDefault(consumeWeekEnd));
        build.addFormDataPart("contacte", VerificationUtil.verifyDefault(contact));
        build.addFormDataPart("notice", VerificationUtil.verifyDefault(notice));
        build.addFormDataPart("usetype", VerificationUtil.verifyDefault(currentUseType));
        build.addFormDataPart("qbtype", VerificationUtil.verifyDefault(currentType));
        build.addFormDataPart("account", VerificationUtil.verifyDefault(account));
        RequestBody requestBody = build.build();
        return apiService.addCard(requestBody).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取权益包详情
     *
     * @param storyId 店铺id
     * @param cardId  权益包id
     * @return
     */
    public Observable<InterestInfoBean> getInterestInfo(String storyId, String cardId) {
        return apiService.getCardInfo(storyId, cardId).compose(RxSchedules.ioToMain());
    }

    /**
     * 检查店铺是否缴纳保证金
     *
     * @param storeId 店铺id
     * @return
     */
    public Observable<ResponseBean> checkBond(String storeId) {
        return apiService.checkBond(storeId).compose(RxSchedules.ioToMain());
    }

    /**
     * 缴纳保证金
     *
     * @param storeId 店铺id
     * @param bond    服务保证金
     * @param fee     营销费
     * @return
     */
    public Observable<WChatPayBean> payBond(String storeId, String bond, String fee) {
        return apiService.payBond(storeId, bond, fee).compose(RxSchedules.ioToMain());
    }

    /**
     * 缴纳保证金（支付宝支付）
     *
     * @param storeId 店铺id
     * @param bond    服务保证金
     * @param fee     营销费
     * @return
     */
    public Observable<String> payBondAlipay(String storeId, String bond, String fee) {
        return apiService.payBondAlipay(storeId, bond, fee).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取已上架的权益包列表
     *
     * @param storyId  店铺id
     * @param status   状态：1：审核通过；-99:已下架
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<InterestListBean> getInterestGoodList(String storyId, int status, int page, int pageSize) {
        return apiService.getCardGoodList(storyId, status, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 下架权益包
     *
     * @param storyId 店铺id
     * @param cardId  权益包id
     * @return
     */
    public Observable<ResponseBean> obtainedInterest(String storyId, String cardId) {
        return apiService.obtainedCard(storyId, cardId).compose(RxSchedules.ioToMain());
    }

    /**
     * 上架权益包
     *
     * @param storyId 店铺id
     * @param cardId  权益包id
     * @return
     */
    public Observable<ResponseBean> shelfInterest(String storyId, String cardId) {
        return apiService.shelfCard(storyId, cardId).compose(RxSchedules.ioToMain());
    }

    /**
     * 删除权益包
     *
     * @param storyId 店铺id
     * @param cardId  权益包id
     * @return
     */
    public Observable<ResponseBean> delInterest(String storyId, String cardId) {
        return apiService.deleteCard(storyId, cardId).compose(RxSchedules.ioToMain());
    }

    /**
     * 维权订单列表
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页大小
     * @return
     */
    public Observable<List<OrderRefundBean>> getOrderRightProtections(String storeId, int page, int pageSize) {
        return apiService.getOrderRightProtections(storeId, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取维权订单详情
     *
     * @param storeId 店铺id
     * @param orderId 订单id
     * @return
     */
    public Observable<OrderRefundBean> getOrderRightProtectionInfo(String storeId, String orderId) {
        return apiService.getOrderRightProtectionInfo(storeId, orderId).compose(RxSchedules.ioToMain());
    }

    /**
     * 同意维权订单退款
     *
     * @param storeId 店铺id
     * @param orderId 订单id
     * @return
     */
    public Observable<ResponseBean> agreeRightProtectionRefund(String storeId, String orderId) {
        return apiService.agreeRightProtectionRefund(storeId, orderId).compose(RxSchedules.ioToMain());
    }

    /**
     * 驳回维权订单退款
     *
     * @param storeId 店铺id
     * @param orderId 订单id
     * @param reason  驳回原因
     * @return
     */
    public Observable<ResponseBean> refusedRightProtectionRefund(String storeId, String orderId, String reason) {
        return apiService.refusedRightProtectionRefund(storeId, orderId, reason).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取评论列表
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<CommentListBean> getComments(String storeId, int page, int pageSize) {
        return apiService.getComments(storeId, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取评论详情
     *
     * @param storeId   店铺id
     * @param commentId 评论id
     * @return
     */
    public Observable<CommentBean> getCommentInfo(String storeId, String commentId) {
        return apiService.getCommentInfo(storeId, commentId).compose(RxSchedules.ioToMain());
    }

    /**
     * 回复评论
     *
     * @param storeId   店铺id
     * @param commentId 评论id
     * @param text      回复内容
     * @return
     */
    public Observable<ResponseBean> answerComment(String storeId, String commentId, String text) {
        return apiService.answerComment(storeId, commentId, text).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取店铺交易数据
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<List<TransactionBean>> getTransactionList(String storeId, int page, int pageSize) {
        return apiService.getTransactionList(storeId, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取店铺访客记录
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<List<VisitorBean>> getVisitorList(String storeId, int page, int pageSize) {
        return apiService.getVisitorList(storeId, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取店铺收藏记录
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<List<CollectionBean>> getCollectionList(String storeId, int page, int pageSize) {
        return apiService.getCollectionList(storeId, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取订单核销信息
     *
     * @param url     核销链接
     * @param storeId 店铺id
     * @return
     */
    public Observable<ConsumeRes> writeOffConsume(String url, String storeId) {
        return apiService.writeOffConsume(url, storeId).compose(RxSchedules.ioToMain());
    }

    /**
     * 订单核销
     *
     * @param url     核销确认链接
     * @param numbers 当次使用次数
     * @return
     */
    public Observable<ResponseBean> orderWriteOff(String url, String numbers) {
        return apiService.orderWriteOff(url, numbers).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取消费记录列表
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<ConsumeRecordRes> getConsumeRecord(String storeId, int page, int pageSize) {
        return apiService.getConsumeRecord(storeId, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取待成团订单列表
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<List<OrderBean>> getWaitGroupOrder(String storeId, int page, int pageSize) {
        return apiService.getWaitGroupOrder(storeId, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取已完成订单列表
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<List<OrderBean>> getFinishedOrder(String storeId, int page, int pageSize) {
        return apiService.getFinishedOrder(storeId, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取我的团队收益
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     * @param level    邀请级别
     * @return
     */
    public Observable<TeamIncomeRes> getTeamIncomes(String storeId, int page, int pageSize, int level) {
        return apiService.getTeamIncome(storeId, page, pageSize, level).compose(RxSchedules.ioToMain());
    }

    /**
     * 添加银行卡
     *
     * @param storeId  店铺id
     * @param bankCard 银行卡号
     * @param name     姓名
     * @param phoneNo  手机号
     * @param code     验证码
     * @return
     */
    public Observable<ResponseBean> addBankCard(String storeId, String bankCard, String name, String phoneNo, String code) {
        return apiService.addBankCard(storeId, bankCard, name, phoneNo, code).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取银行卡列表
     *
     * @param storeId 店铺id
     * @return
     */
    public Observable<ArrayList<BankCardBean>> getBankCards(String storeId) {
        return apiService.getBankCards(storeId).compose(RxSchedules.ioToMain());
    }

    /**
     * 删除银行卡
     *
     * @param storeId    店铺id
     * @param bankCardId 银行卡id
     * @return
     */
    public Observable<ResponseBean> delBankCard(String storeId, String bankCardId) {
        return apiService.delBankCard(storeId, bankCardId).compose(RxSchedules.ioToMain());
    }

    /**
     * 提现
     *
     * @param storeId  店铺id
     * @param bankCard 银行卡号
     * @param amount   金额
     * @param account  用户帐号
     * @return
     */
    public Observable<ResponseBean> withdraw(String storeId, String bankCard, String amount, String account) {
        return apiService.withdraw(storeId, bankCard, amount, account).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取提现说明
     *
     * @return
     */
    public Observable<WithdrawIntroBean> getWithdrawIntro(String storeId) {
        return apiService.getWithdrawIntro(storeId).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取提现记录
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     * @param status   状态
     *                 1	待审核
     *                 2	汇款中
     *                 3	提现成功
     *                 4	被驳回
     *                 0	全部
     * @return
     */
    public Observable<List<WithdrawRecordBean>> getWithdrawRecords(String storeId, int page, int pageSize, int status) {
        return apiService.getWithdrawRecords(storeId, page, pageSize, status).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取提现详情
     *
     * @param storeId    店铺id
     * @param withdrawId 提现记录id
     * @return
     */
    public Observable<WithdrawRecordBean> getWithdrawInfo(String storeId, String withdrawId) {
        return apiService.getWithdrawInfo(storeId, withdrawId).compose(RxSchedules.ioToMain());
    }

    /**
     * 更换手机号
     *
     * @param storeId 店铺id
     * @param phoneNo 手机号
     * @param code    验证码
     * @return
     */
    public Observable<ResponseBean> changePhone(String storeId, String phoneNo, String code) {
        return apiService.changePhone(storeId, phoneNo, code).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取财政收入记录
     *
     * @param storeId   店铺id
     * @param page      页码
     * @param pageSize  页数据大小
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public Observable<List<RevenueRecordBean>> getRevenueRecords(String storeId, int page, int pageSize, String startDate, String endDate) {
        return apiService.getRevenueRecords(storeId, page, pageSize, startDate, endDate).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取商家联盟
     *
     * @param type 为空值时，表示全部商家联盟；不为空值时表示我的商家联盟
     * @return
     */
    public Observable<List<AllianceBean>> getAlliances(String storeId, int page, int pageSize, String type) {
        return apiService.getAlliances(storeId, page, pageSize, type).compose(RxSchedules.ioToMain());
    }

    /**
     * 发布商家联盟
     *
     * @param storeId  店铺id
     * @param name     商家联盟名称
     * @param filePath 二维码路径
     * @return
     */
    public Observable<ResponseBean> publishAlliance(String storeId, String name, String filePath) {
        MultipartBody.Builder build = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (!TextUtils.isEmpty(filePath)) {
            File storeFile = new File(filePath);
            build.addFormDataPart("file", storeFile.getName(), RequestBody.create(MediaType.parse("image/*"), storeFile));
        }
        build.addFormDataPart("storeid", VerificationUtil.verifyDefault(storeId));
        build.addFormDataPart("name", VerificationUtil.verifyDefault(name));
        RequestBody requestBody = build.build();
        return apiService.publishAlliance(requestBody).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取消息列表
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<List<NotifyBean>> getNotifyList(String storeId, int page, int pageSize) {
        return apiService.getStoreNotifyList(storeId, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取消息详情
     *
     * @param storeId         店铺id
     * @param messageRecordId 消息记录id
     * @return
     */
    public Observable<NotifyBean> getNotifyDetail(String storeId, String messageRecordId) {
        return apiService.getStoreNotifyDetail(storeId, messageRecordId).compose(RxSchedules.ioToMain());
    }

    /**
     * 获取子账号列表
     *
     * @param storeId  店铺id
     * @param page     页码
     * @param pageSize 页数据大小
     * @return
     */
    public Observable<List<AccountBean>> getAccounts(String storeId, int page, int pageSize) {
        return apiService.getStoreAccounts(storeId, page, pageSize).compose(RxSchedules.ioToMain());
    }

    /**
     * 添加子账号
     *
     * @param storeId 店铺id
     * @param type    账号类型，1运营者，2操作者
     * @param account 子帐号
     * @param name    帐号名称
     * @return
     */
    public Observable<ResponseBean> addAccount(String storeId, int type, String account, String name) {
        return apiService.addAccount(storeId, account, type, name).compose(RxSchedules.ioToMain());
    }

    /**
     * 删除子帐号
     *
     * @param storeId     店铺id
     * @param storeUserId 店铺子帐号id
     * @return
     */
    public Observable<ResponseBean> delAccount(String storeId, String storeUserId) {
        return apiService.delAccount(storeId, storeUserId).compose(RxSchedules.ioToMain());
    }

    /**
     * 权益包店铺内审核通过
     *
     * @param storeId    店铺id
     * @param account    审核帐号
     * @param interestId 权益包id
     * @return
     */
    public Observable<ResponseBean> interestPass(String storeId, String account, String interestId) {
        return apiService.interesetPass(storeId, account, interestId).compose(RxSchedules.ioToMain());
    }

    /**
     * 权益包店铺内审核不通过
     *
     * @param storeId    店铺id
     * @param account    审核帐号
     * @param interestId 权益包id
     * @param reason     不通过原因
     * @return
     */
    public Observable<ResponseBean> interestRefuse(String storeId, String account, String interestId, String reason) {
        return apiService.interesetRefuse(storeId, account, interestId, reason).compose(RxSchedules.ioToMain());
    }

    /**
     * v3版首页我的数据接口
     *
     * @param storeId 店铺id
     * @return
     */
    public Observable<HomeV3MyDataBean> myData(String storeId) {
        return apiService.myData(storeId).compose(RxSchedules.ioToMain());
    }
}
