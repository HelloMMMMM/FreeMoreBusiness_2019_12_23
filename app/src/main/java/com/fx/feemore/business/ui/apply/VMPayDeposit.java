package com.fx.feemore.business.ui.apply;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.alipay.sdk.app.PayTask;
import com.fx.feemore.business.BuildConfig;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.PayResult;
import com.fx.feemore.business.bean.WChatPayBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.util.ShareUtils;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;
import com.hengxian.baselib.utils.RxSchedules;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * function:缴纳保证金ViewModel
 * author: frj
 * modify date: 2018/12/27
 */
public class VMPayDeposit extends ViewModel {

    private DataSource dataSource;

    private IWXAPI mApi;        //微信Api对象

    public MutableLiveData<ResponseBean> resCheckResult = new MutableLiveData<>();
    public MutableLiveData<HttpFailBean> resHttpFail = new MutableLiveData<>();
    public MutableLiveData<WChatPayBean> resPayBean = new MutableLiveData<>();
    public MutableLiveData<String> resAlipay = new MutableLiveData<>();
    public MutableLiveData<PayResult> resAlipayResult = new MutableLiveData<>();

    @Inject
    public VMPayDeposit(DataSource dataSource) {
        this.dataSource = dataSource;
        mApi = WXAPIFactory.createWXAPI(AppContext.getInstanceBase(), ShareUtils.WECHAT_APPID, !BuildConfig.DEBUG);
        mApi.registerApp(ShareUtils.WECHAT_APPID);
    }

    /**
     * 检查当前店铺是否已缴纳保证金
     *
     * @param storeId 店铺id
     */
    public void checkBond(String storeId) {
        dataSource.checkBond(storeId).subscribe(new ApiCallback<ResponseBean>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean) {
                resCheckResult.setValue(responseBean);
            }
        });
    }

    /**
     * 缴纳保证金
     *
     * @param storeId 店铺id
     */
    public void payBond(String storeId) {
        dataSource.payBond(storeId, BuildConfig.DEBUG ? "0.01" : "2560", BuildConfig.DEBUG ? "0.01" : "1000").subscribe(new ApiCallback<WChatPayBean>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(WChatPayBean wChatPayBean) {
                resPayBean.setValue(wChatPayBean);
            }
        });
    }

    /**
     * 缴纳保证金（支付宝）
     *
     * @param storeId 店铺id
     */
    public void payBondAlipay(String storeId) {
        dataSource.payBondAlipay(storeId, "0.01", "0.01").subscribe(new ApiCallback<String>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(String s) {
                resAlipay.setValue(s);
            }
        });
    }

    /**
     * 微信支付
     *
     * @param bean 微信支付实体
     */
    public void wechatPay(WChatPayBean bean) {
        if (bean != null) {
            PayReq request = new PayReq();
            request.appId = bean.getAppid();
            request.partnerId = bean.getPartnerid();
            request.prepayId = bean.getPrepayid();
            request.packageValue = bean.getPackageStr();
            request.nonceStr = bean.getNoncestr();
            request.timeStamp = bean.getTimestamp();
            request.sign = bean.getSign();
            if (mApi != null) {
                mApi.sendReq(request);
            }
        }
    }

    /**
     * 支付宝支付
     *
     * @param orderStr 支付字符串
     */
    public void alipay(Activity context, String orderStr) {

        Observable.just(orderStr).map(s -> {
            PayTask task = new PayTask(context);
            Map<String, String> result = task.payV2(s, true);
            return new PayResult(result);
        }).compose(RxSchedules.ioToMain()).subscribe(new ApiCallback<PayResult>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                resHttpFail.setValue(httpFail);
            }

            @Override
            public void onNext(PayResult payResult) {
                resAlipayResult.setValue(payResult);
            }
        });
    }

}
