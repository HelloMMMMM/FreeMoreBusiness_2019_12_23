package com.fx.feemore.business.di;

import com.fx.feemore.business.ui.apply.AcApplyDetail;
import com.fx.feemore.business.ui.apply.AcApplyManager;
import com.fx.feemore.business.ui.apply.AcApplyRecord;
import com.fx.feemore.business.ui.apply.AcPayDeposit;
import com.fx.feemore.business.ui.apply.item.FApplyItem;
import com.fx.feemore.business.ui.character.AcCharacterSelected;
import com.fx.feemore.business.ui.commodity.AcCommodityManager;
import com.fx.feemore.business.ui.commodity.AcConsumeDetail;
import com.fx.feemore.business.ui.commodity.item.FCommodityConsumeRecord;
import com.fx.feemore.business.ui.commodity.item.FCommodityRemoved;
import com.fx.feemore.business.ui.commodity.item.FCommodityUsing;
import com.fx.feemore.business.ui.finance.AcAddCard;
import com.fx.feemore.business.ui.finance.AcFinance;
import com.fx.feemore.business.ui.finance.AcIncomeRecord;
import com.fx.feemore.business.ui.finance.AcMyBank;
import com.fx.feemore.business.ui.finance.AcWithdraw;
import com.fx.feemore.business.ui.finance.AcWithdrawDetail;
import com.fx.feemore.business.ui.finance.AcWithdrawRecord;
import com.fx.feemore.business.ui.forgot.AcForgotPwd;
import com.fx.feemore.business.ui.home.MainActivity;
import com.fx.feemore.business.ui.home.fragment.FragmentDrainage;
import com.fx.feemore.business.ui.home.fragment.FragmentHome;
import com.fx.feemore.business.ui.home.fragment.FragmentHomeV2;
import com.fx.feemore.business.ui.home.fragment.FragmentHomeV3;
import com.fx.feemore.business.ui.home.fragment.FragmentInterest;
import com.fx.feemore.business.ui.home.fragment.FragmentMine;
import com.fx.feemore.business.ui.home.fragment.FragmentNews;
import com.fx.feemore.business.ui.home.fragment.FragmentPerson;
import com.fx.feemore.business.ui.home.fragment.FragmentUtil;
import com.fx.feemore.business.ui.home.fragment.interest.FConsumeRecord;
import com.fx.feemore.business.ui.home.fragment.interest.FMyInterest;
import com.fx.feemore.business.ui.home.fragment.order.FOrderComplete;
import com.fx.feemore.business.ui.home.fragment.order.FOrderReservation;
import com.fx.feemore.business.ui.home.fragment.order.FOrderRightsProtection;
import com.fx.feemore.business.ui.home.fragment.order.FOrderWaitConsume;
import com.fx.feemore.business.ui.interest.AcInterestDetail;
import com.fx.feemore.business.ui.interest.AcPublishInterest;
import com.fx.feemore.business.ui.interest.AcPublishInterestV2;
import com.fx.feemore.business.ui.login.AcLogin;
import com.fx.feemore.business.ui.login.fragment.FAccountPassword;
import com.fx.feemore.business.ui.login.fragment.FPhoneVerify;
import com.fx.feemore.business.ui.luckdraw.AcLuckDraw;
import com.fx.feemore.business.ui.merchant.AcActiveDetail;
import com.fx.feemore.business.ui.merchant.AcAllianceList;
import com.fx.feemore.business.ui.merchant.AcAllianceQRCode;
import com.fx.feemore.business.ui.merchant.AcApplayActive;
import com.fx.feemore.business.ui.merchant.AcApplyActiveDetail;
import com.fx.feemore.business.ui.merchant.AcApplyInterestDetails;
import com.fx.feemore.business.ui.merchant.AcAppyInterest;
import com.fx.feemore.business.ui.merchant.AcInitateActive;
import com.fx.feemore.business.ui.merchant.AcInitateAlliance;
import com.fx.feemore.business.ui.merchant.AcMember;
import com.fx.feemore.business.ui.merchant.AcMyActive;
import com.fx.feemore.business.ui.merchant.AcMyAlliance;
import com.fx.feemore.business.ui.merchant.FApplyActiveItem;
import com.fx.feemore.business.ui.merchant.FApplyInterest;
import com.fx.feemore.business.ui.merchant.about.AcAbout;
import com.fx.feemore.business.ui.merchant.account.AcAccountList;
import com.fx.feemore.business.ui.merchant.account.AcAddAccount;
import com.fx.feemore.business.ui.merchant.margin.AcMyMargin;
import com.fx.feemore.business.ui.merchant.review.AcReview;
import com.fx.feemore.business.ui.merchant.set.AcChangePhone;
import com.fx.feemore.business.ui.merchant.team.AcMyTeam;
import com.fx.feemore.business.ui.merchant.team.item.FTeamLevel;
import com.fx.feemore.business.ui.notify.AcNotifyManager;
import com.fx.feemore.business.ui.notify.FNotifyOrder;
import com.fx.feemore.business.ui.notify.FNotifyOther;
import com.fx.feemore.business.ui.order.AcCommentDetail;
import com.fx.feemore.business.ui.order.AcCompleteDetail;
import com.fx.feemore.business.ui.order.AcOrderComplete;
import com.fx.feemore.business.ui.order.AcOrderManager;
import com.fx.feemore.business.ui.order.AcOrderRightsProtection;
import com.fx.feemore.business.ui.order.AcOrderWriteOff;
import com.fx.feemore.business.ui.order.AcReservationDetail;
import com.fx.feemore.business.ui.order.AcReserveationReject;
import com.fx.feemore.business.ui.order.AcRightsProtection;
import com.fx.feemore.business.ui.order.AcWaitGroupDetail;
import com.fx.feemore.business.ui.order.item.FOrderComment;
import com.fx.feemore.business.ui.order.item.FOrderWaitGroup;
import com.fx.feemore.business.ui.register.AcInputData;
import com.fx.feemore.business.ui.register.AcMapSelection;
import com.fx.feemore.business.ui.register.AcRegister;
import com.fx.feemore.business.ui.transaction.AcTransactionData;
import com.fx.feemore.business.ui.transaction.FTransactionAmount;
import com.fx.feemore.business.ui.transaction.FTransactionCollege;
import com.fx.feemore.business.ui.transaction.FTransactionTransaction;
import com.fx.feemore.business.ui.transaction.FTransactionVisitor;
import com.hengxian.baselib.dagger.scope.ActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Activity注册模块，需要依赖注入的Activity必须在这里注册
 *
 * @author wfq
 * @date 2018/6/1
 */
@Module
interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    AcCharacterSelected characterSelected();

    @ActivityScope
    @ContributesAndroidInjector
    AcLogin login();

    @ActivityScope
    @ContributesAndroidInjector
    FAccountPassword accountPassword();

    @ActivityScope
    @ContributesAndroidInjector
    FPhoneVerify phoneVerify();

    @ActivityScope
    @ContributesAndroidInjector
    AcForgotPwd forgotPwd();

    @ActivityScope
    @ContributesAndroidInjector
    AcRegister register();

    @ActivityScope
    @ContributesAndroidInjector
    AcInputData inputData();

    @ActivityScope
    @ContributesAndroidInjector
    AcApplyRecord applyRecord();

    @ActivityScope
    @ContributesAndroidInjector
    AcPayDeposit payDeposit();

    @ActivityScope
    @ContributesAndroidInjector
    MainActivity main();

    @ActivityScope
    @ContributesAndroidInjector
    FragmentHome home();

    @ActivityScope
    @ContributesAndroidInjector
    FragmentInterest interest();

    @ActivityScope
    @ContributesAndroidInjector
    FragmentPerson person();

    @ActivityScope
    @ContributesAndroidInjector
    FOrderReservation orderReservation();

    @ActivityScope
    @ContributesAndroidInjector
    FOrderWaitConsume orderWaitConsume();

    @ActivityScope
    @ContributesAndroidInjector
    FOrderRightsProtection orderRightsProtection();

    @ActivityScope
    @ContributesAndroidInjector
    FOrderComplete orderComplete();

    @ActivityScope
    @ContributesAndroidInjector
    AcReserveationReject reservationReject();

    @ActivityScope
    @ContributesAndroidInjector
    AcReservationDetail reservationDetail();

    @ActivityScope
    @ContributesAndroidInjector
    AcOrderComplete orderCompleteDetail();

    @ActivityScope
    @ContributesAndroidInjector
    AcOrderRightsProtection orderRightsProtectionDetail();

    @ActivityScope
    @ContributesAndroidInjector
    FMyInterest myInterest();

    @ActivityScope
    @ContributesAndroidInjector
    FConsumeRecord consumeRecord();

    @ActivityScope
    @ContributesAndroidInjector
    AcInterestDetail interestDetail();

    @ActivityScope
    @ContributesAndroidInjector
    AcPublishInterest publishInterest();

    @ActivityScope
    @ContributesAndroidInjector
    AcIncomeRecord incomeRecord();

    @ActivityScope
    @ContributesAndroidInjector
    AcFinance finance();

    @ActivityScope
    @ContributesAndroidInjector
    AcAddCard addCard();

    @ActivityScope
    @ContributesAndroidInjector
    AcWithdraw withdraw();

    @ActivityScope
    @ContributesAndroidInjector
    AcWithdrawDetail withdrawDetail();

    @ActivityScope
    @ContributesAndroidInjector
    AcMember member();

    @ActivityScope
    @ContributesAndroidInjector
    AcMyActive myActive();

    @ActivityScope
    @ContributesAndroidInjector
    AcInitateActive initateActive();

    @ActivityScope
    @ContributesAndroidInjector
    AcActiveDetail activeDetail();

    @ActivityScope
    @ContributesAndroidInjector
    AcMyAlliance myAlliance();

    @ActivityScope
    @ContributesAndroidInjector
    AcAllianceQRCode allianceQRCode();

    @ActivityScope
    @ContributesAndroidInjector
    AcInitateAlliance initateAlliance();

    @ActivityScope
    @ContributesAndroidInjector
    AcAppyInterest applyInterest();

    @ActivityScope
    @ContributesAndroidInjector
    FApplyInterest applyInterestItem();

    @ActivityScope
    @ContributesAndroidInjector
    AcApplyInterestDetails applyInterestDetail();

    @ActivityScope
    @ContributesAndroidInjector
    AcApplayActive applyActive();

    @ActivityScope
    @ContributesAndroidInjector
    FApplyActiveItem applyActiveItem();

    @ActivityScope
    @ContributesAndroidInjector
    AcApplyActiveDetail applyActiveDetail();

    @ActivityScope
    @ContributesAndroidInjector
    AcTransactionData transactionData();

    @ActivityScope
    @ContributesAndroidInjector
    FTransactionTransaction transactionTransaction();

    @ActivityScope
    @ContributesAndroidInjector
    FTransactionAmount transactionAmount();

    @ActivityScope
    @ContributesAndroidInjector
    FTransactionCollege transactionCollege();

    @ActivityScope
    @ContributesAndroidInjector
    FTransactionVisitor transactionVisitor();

    @ActivityScope
    @ContributesAndroidInjector
    AcNotifyManager notifyManager();

    @ActivityScope
    @ContributesAndroidInjector
    FNotifyOrder notifyOrder();

    @ActivityScope
    @ContributesAndroidInjector
    FNotifyOther notifyOther();

    @ActivityScope
    @ContributesAndroidInjector
    FragmentHomeV2 homeV2();

    @ActivityScope
    @ContributesAndroidInjector
    FragmentHomeV3 homeV3();

    @ActivityScope
    @ContributesAndroidInjector
    FragmentDrainage drainage();

    @ActivityScope
    @ContributesAndroidInjector
    FragmentNews news();

    @ActivityScope
    @ContributesAndroidInjector
    FragmentUtil util();

    @ActivityScope
    @ContributesAndroidInjector
    FragmentMine mine();

    @ActivityScope
    @ContributesAndroidInjector
    AcOrderManager orderManager();

    @ActivityScope
    @ContributesAndroidInjector
    com.fx.feemore.business.ui.order.item.FOrderComplete orderCompleteV2();

    @ActivityScope
    @ContributesAndroidInjector
    FOrderComment orderComment();

    @ActivityScope
    @ContributesAndroidInjector
    com.fx.feemore.business.ui.order.item.FOrderRightsProtection orderRightsProtectionV2();

    @ActivityScope
    @ContributesAndroidInjector
    FOrderWaitGroup orderWaitGroup();

    @ActivityScope
    @ContributesAndroidInjector
    AcCommodityManager commodityManager();

    @ActivityScope
    @ContributesAndroidInjector
    FCommodityRemoved commodityRemoved();

    @ActivityScope
    @ContributesAndroidInjector
    FCommodityUsing commodityUsing();

    @ActivityScope
    @ContributesAndroidInjector
    AcCommentDetail commentDetail();

    @ActivityScope
    @ContributesAndroidInjector
    AcWaitGroupDetail waitGroup();

    @ActivityScope
    @ContributesAndroidInjector
    AcRightsProtection rightsProtection();

    @ActivityScope
    @ContributesAndroidInjector
    AcCompleteDetail completeDetail();

    @ActivityScope
    @ContributesAndroidInjector
    AcApplyManager applyManager();

    @ActivityScope
    @ContributesAndroidInjector
    FApplyItem applyItem();

    @ActivityScope
    @ContributesAndroidInjector
    AcApplyDetail applyDetail();

    @ActivityScope
    @ContributesAndroidInjector
    AcPublishInterestV2 publishInterestV2();

    @ActivityScope
    @ContributesAndroidInjector
    AcMyBank myBank();

    @ActivityScope
    @ContributesAndroidInjector
    AcWithdrawRecord withdrawRecord();

    @ActivityScope
    @ContributesAndroidInjector
    AcMapSelection mapSelection();

    @ActivityScope
    @ContributesAndroidInjector
    AcChangePhone changePhone();

    @ActivityScope
    @ContributesAndroidInjector
    AcMyMargin myMargin();

    @ActivityScope
    @ContributesAndroidInjector
    AcMyTeam myTeam();

    @ActivityScope
    @ContributesAndroidInjector
    FTeamLevel teamLevel();

    @ActivityScope
    @ContributesAndroidInjector
    AcOrderWriteOff orderWriteOff();

    @ActivityScope
    @ContributesAndroidInjector
    AcAbout about();

    @ActivityScope
    @ContributesAndroidInjector
    FCommodityConsumeRecord commodityConsumeRecord();

    @ActivityScope
    @ContributesAndroidInjector
    AcConsumeDetail consumeDetail();

    @ActivityScope
    @ContributesAndroidInjector
    AcAllianceList allianceList();

    @ActivityScope
    @ContributesAndroidInjector
    AcAddAccount addAccount();

    @ActivityScope
    @ContributesAndroidInjector
    AcReview reviewList();

    @ActivityScope
    @ContributesAndroidInjector
    AcAccountList accountList();

    @ActivityScope
    @ContributesAndroidInjector
    AcLuckDraw luckDraw();
}
