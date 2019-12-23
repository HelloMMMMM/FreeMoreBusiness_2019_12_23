package com.fx.feemore.business.di;

import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.ui.apply.VMApplyRecord;
import com.fx.feemore.business.ui.apply.VMPayDeposit;
import com.fx.feemore.business.ui.apply.vm.VMApplyDetail;
import com.fx.feemore.business.ui.apply.vm.VMApplyItem;
import com.fx.feemore.business.ui.apply.vm.VMApplyManager;
import com.fx.feemore.business.ui.character.VMCharacter;
import com.fx.feemore.business.ui.commodity.vm.VMCommodityConsumeRecord;
import com.fx.feemore.business.ui.commodity.vm.VMCommodityManager;
import com.fx.feemore.business.ui.commodity.vm.VMCommodityRemoved;
import com.fx.feemore.business.ui.commodity.vm.VMCommodityUsing;
import com.fx.feemore.business.ui.commodity.vm.VMConsumeDetail;
import com.fx.feemore.business.ui.finance.vm.VMAddCard;
import com.fx.feemore.business.ui.finance.vm.VMFinance;
import com.fx.feemore.business.ui.finance.vm.VMIncomeRecord;
import com.fx.feemore.business.ui.finance.vm.VMMyBank;
import com.fx.feemore.business.ui.finance.vm.VMWithdraw;
import com.fx.feemore.business.ui.finance.vm.VMWithdrawDetail;
import com.fx.feemore.business.ui.finance.vm.VMWithdrawRecord;
import com.fx.feemore.business.ui.forgot.VMForgotPwd;
import com.fx.feemore.business.ui.home.vm.VMHome;
import com.fx.feemore.business.ui.home.vm.VMInterest;
import com.fx.feemore.business.ui.home.vm.VMInterestItem;
import com.fx.feemore.business.ui.home.vm.VMMain;
import com.fx.feemore.business.ui.home.vm.VMOrderItem;
import com.fx.feemore.business.ui.home.vm.VMPerson;
import com.fx.feemore.business.ui.interest.vm.VMInterestDetail;
import com.fx.feemore.business.ui.interest.vm.VMPublishInterest;
import com.fx.feemore.business.ui.login.vm.VMAccountPassword;
import com.fx.feemore.business.ui.login.vm.VMLogin;
import com.fx.feemore.business.ui.login.vm.VMPhoneVerify;
import com.fx.feemore.business.ui.merchant.about.vm.VMAbout;
import com.fx.feemore.business.ui.merchant.account.vm.VMAccountList;
import com.fx.feemore.business.ui.merchant.account.vm.VMAddAccount;
import com.fx.feemore.business.ui.merchant.margin.vm.VMMyMargin;
import com.fx.feemore.business.ui.merchant.review.vm.VMReview;
import com.fx.feemore.business.ui.merchant.set.vm.VMChangePhone;
import com.fx.feemore.business.ui.merchant.team.vm.VMMyTeam;
import com.fx.feemore.business.ui.merchant.team.vm.VMTeamLevel;
import com.fx.feemore.business.ui.merchant.vm.VMActiveDetail;
import com.fx.feemore.business.ui.merchant.vm.VMAllianceList;
import com.fx.feemore.business.ui.merchant.vm.VMAllianceQRCode;
import com.fx.feemore.business.ui.merchant.vm.VMApplyActive;
import com.fx.feemore.business.ui.merchant.vm.VMApplyActiveDetail;
import com.fx.feemore.business.ui.merchant.vm.VMApplyActiveItem;
import com.fx.feemore.business.ui.merchant.vm.VMApplyInterest;
import com.fx.feemore.business.ui.merchant.vm.VMApplyInterestDetails;
import com.fx.feemore.business.ui.merchant.vm.VMApplyInterestItem;
import com.fx.feemore.business.ui.merchant.vm.VMInitateActive;
import com.fx.feemore.business.ui.merchant.vm.VMInitateAlliance;
import com.fx.feemore.business.ui.merchant.vm.VMMember;
import com.fx.feemore.business.ui.merchant.vm.VMMyActive;
import com.fx.feemore.business.ui.merchant.vm.VMMyAlliance;
import com.fx.feemore.business.ui.notify.vm.VMNotifyManager;
import com.fx.feemore.business.ui.notify.vm.VMNotifyOrder;
import com.fx.feemore.business.ui.notify.vm.VMNotifyOther;
import com.fx.feemore.business.ui.order.vm.VMCommentDetail;
import com.fx.feemore.business.ui.order.vm.VMCompleteDetail;
import com.fx.feemore.business.ui.order.vm.VMOrderComment;
import com.fx.feemore.business.ui.order.vm.VMOrderComplete;
import com.fx.feemore.business.ui.order.vm.VMOrderManager;
import com.fx.feemore.business.ui.order.vm.VMOrderRightsProtection;
import com.fx.feemore.business.ui.order.vm.VMOrderWaitGroup;
import com.fx.feemore.business.ui.order.vm.VMOrderWriteOff;
import com.fx.feemore.business.ui.order.vm.VMReservationDetail;
import com.fx.feemore.business.ui.order.vm.VMReservationReject;
import com.fx.feemore.business.ui.order.vm.VMRightsProtectionDetail;
import com.fx.feemore.business.ui.order.vm.VMWaitGroupDetail;
import com.fx.feemore.business.ui.register.VMInputData;
import com.fx.feemore.business.ui.register.VMMapSelection;
import com.fx.feemore.business.ui.register.VMRegister;
import com.fx.feemore.business.ui.transaction.vm.VMTransactionAmount;
import com.fx.feemore.business.ui.transaction.vm.VMTransactionCollege;
import com.fx.feemore.business.ui.transaction.vm.VMTransactionData;
import com.fx.feemore.business.ui.transaction.vm.VMTransactionTransaction;
import com.fx.feemore.business.ui.transaction.vm.VMTransactionVisitor;
import com.hengxian.baselib.dagger.ViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


/**
 * ViewModel注册类，所有需要使用依赖注入生成实例的ViewModel都需要在这里注册
 *
 * @author wfq
 * @date 2018/6/1
 */
@Module
public interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(VMCharacter.class)
    ViewModel characterSelected(VMCharacter vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMLogin.class)
    ViewModel login(VMLogin vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMAccountPassword.class)
    ViewModel accountPassword(VMAccountPassword vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMPhoneVerify.class)
    ViewModel phoneVerify(VMPhoneVerify vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMForgotPwd.class)
    ViewModel forgotPwd(VMForgotPwd vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMRegister.class)
    ViewModel register(VMRegister vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMInputData.class)
    ViewModel inputData(VMInputData vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMApplyRecord.class)
    ViewModel applyRecord(VMApplyRecord vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMPayDeposit.class)
    ViewModel payDeposit(VMPayDeposit vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMMain.class)
    ViewModel main(VMMain vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMHome.class)
    ViewModel home(VMHome vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMOrderItem.class)
    ViewModel orderItem(VMOrderItem vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMReservationReject.class)
    ViewModel reservationReject(VMReservationReject vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMReservationDetail.class)
    ViewModel reservationDetail(VMReservationDetail vm);

//    @Binds
//    @IntoMap
//    @ViewModelKey(VMOrderComplete.class)
//    ViewModel orderCompleteDetail(VMOrderComplete vm);

//    @Binds
//    @IntoMap
//    @ViewModelKey(VMOrderRightsProtection.class)
//    ViewModel orderRightsProtectionDetail(VMOrderRightsProtection vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMInterest.class)
    ViewModel interest(VMInterest vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMInterestItem.class)
    ViewModel interestItem(VMInterestItem vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMInterestDetail.class)
    ViewModel interestDetail(VMInterestDetail vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMPublishInterest.class)
    ViewModel publishInterest(VMPublishInterest vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMFinance.class)
    ViewModel finance(VMFinance vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMIncomeRecord.class)
    ViewModel incomeRecord(VMIncomeRecord vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMAddCard.class)
    ViewModel addCard(VMAddCard vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMWithdraw.class)
    ViewModel withdraw(VMWithdraw vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMWithdrawDetail.class)
    ViewModel withdrawDetail(VMWithdrawDetail vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMPerson.class)
    ViewModel person(VMPerson vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMMember.class)
    ViewModel member(VMMember vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMMyActive.class)
    ViewModel myActive(VMMyActive vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMInitateActive.class)
    ViewModel initateActive(VMInitateActive vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMActiveDetail.class)
    ViewModel activeDetail(VMActiveDetail vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMMyAlliance.class)
    ViewModel myAlliance(VMMyAlliance vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMAllianceQRCode.class)
    ViewModel allianceQRCode(VMAllianceQRCode vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMInitateAlliance.class)
    ViewModel initateAlliance(VMInitateAlliance vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMApplyInterest.class)
    ViewModel applyInterest(VMApplyInterest vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMApplyInterestItem.class)
    ViewModel applyInterestItem(VMApplyInterestItem vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMApplyInterestDetails.class)
    ViewModel applyInterestDetail(VMApplyInterestDetails vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMApplyActive.class)
    ViewModel applyActive(VMApplyActive vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMApplyActiveItem.class)
    ViewModel applyActiveItem(VMApplyActiveItem vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMApplyActiveDetail.class)
    ViewModel applyActiveDetail(VMApplyActiveDetail vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMTransactionTransaction.class)
    ViewModel transactionTransaction(VMTransactionTransaction vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMTransactionCollege.class)
    ViewModel transactionCollege(VMTransactionCollege vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMTransactionData.class)
    ViewModel transactionData(VMTransactionData vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMTransactionVisitor.class)
    ViewModel transactionVisitor(VMTransactionVisitor vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMTransactionAmount.class)
    ViewModel transactionAmount(VMTransactionAmount vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMNotifyManager.class)
    ViewModel notifyManager(VMNotifyManager vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMNotifyOrder.class)
    ViewModel notifyOrder(VMNotifyOrder vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMNotifyOther.class)
    ViewModel notifyOther(VMNotifyOther vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMOrderComment.class)
    ViewModel orderComment(VMOrderComment vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMOrderComplete.class)
    ViewModel orderCompelte(VMOrderComplete vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMOrderRightsProtection.class)
    ViewModel orderRightsProtection(VMOrderRightsProtection vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMOrderWaitGroup.class)
    ViewModel orderWaitGroup(VMOrderWaitGroup vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMOrderManager.class)
    ViewModel orderManager(VMOrderManager vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMCommodityRemoved.class)
    ViewModel commodityRemoved(VMCommodityRemoved vm);


    @Binds
    @IntoMap
    @ViewModelKey(VMCommodityUsing.class)
    ViewModel commodityUsing(VMCommodityUsing vm);


    @Binds
    @IntoMap
    @ViewModelKey(VMCommentDetail.class)
    ViewModel commentDetail(VMCommentDetail vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMWaitGroupDetail.class)
    ViewModel waitGroupDetail(VMWaitGroupDetail vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMRightsProtectionDetail.class)
    ViewModel rightsProtectionDetail(VMRightsProtectionDetail vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMCompleteDetail.class)
    ViewModel completeDetail(VMCompleteDetail vm);


    @Binds
    @IntoMap
    @ViewModelKey(VMCommodityManager.class)
    ViewModel commodityManager(VMCommodityManager vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMApplyManager.class)
    ViewModel applyManager(VMApplyManager vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMApplyItem.class)
    ViewModel applyItem(VMApplyItem vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMApplyDetail.class)
    ViewModel applyDetail(VMApplyDetail vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMMyBank.class)
    ViewModel myBank(VMMyBank vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMWithdrawRecord.class)
    ViewModel withdrawRecord(VMWithdrawRecord vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMMapSelection.class)
    ViewModel mapSelection(VMMapSelection vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMChangePhone.class)
    ViewModel changePhone(VMChangePhone vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMMyMargin.class)
    ViewModel myMargin(VMMyMargin vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMMyTeam.class)
    ViewModel myTeam(VMMyTeam vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMTeamLevel.class)
    ViewModel teamLevel(VMTeamLevel vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMOrderWriteOff.class)
    ViewModel orderWriteOff(VMOrderWriteOff vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMAbout.class)
    ViewModel about(VMAbout vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMCommodityConsumeRecord.class)
    ViewModel commodityConsumeRecord(VMCommodityConsumeRecord vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMConsumeDetail.class)
    ViewModel consumeDetail(VMConsumeDetail vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMAllianceList.class)
    ViewModel allianceList(VMAllianceList vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMAddAccount.class)
    ViewModel addAccount(VMAddAccount vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMReview.class)
    ViewModel reviewList(VMReview vm);

    @Binds
    @IntoMap
    @ViewModelKey(VMAccountList.class)
    ViewModel accountList(VMAccountList vm);
}
