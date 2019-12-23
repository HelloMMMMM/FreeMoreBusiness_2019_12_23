package com.fx.feemore.business.ui.interest.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.BuildConfig;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.CategoryBean;
import com.fx.feemore.business.config.Constants;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.util.BitmapUtil;
import com.fx.feemore.business.util.VerificationUtil;
import com.hengxian.baselib.bean.HttpFailBean;
import com.hengxian.baselib.bean.ResponseBean;
import com.hengxian.baselib.utils.RxSchedules;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * function:发布权益
 * author: frj
 * modify date: 2018/12/30
 */
public class VMPublishInterest extends ViewModel {

    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> failRes = new MutableLiveData<>();

    public MutableLiveData<List<CategoryBean>> categoryListRes = new MutableLiveData<>();

    public MutableLiveData<ResponseBean> resSucc = new MutableLiveData<>();

    @Inject
    public VMPublishInterest(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取店铺权益包类型
     *
     * @param storyId 店铺id
     */
    public void getCategory(String storyId) {
        dataSource.getStoryCardCategory(storyId).subscribe(new ApiCallback<List<CategoryBean>>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                failRes.setValue(httpFail);
            }

            @Override
            public void onNext(List<CategoryBean> categoryBeans) {
                categoryListRes.setValue(categoryBeans);
            }
        });
    }

    /**
     * 处理图片，完成之后发布
     *
     * @param storyId       店铺id
     * @param name          权益包名称
     * @param des           权益包描述
     * @param categoryId    商品类型id
     * @param type          权益包类型；1普通，2拼团
     * @param useNum        使用人数
     * @param quantity      总数
     * @param availablenum  使用次数
     * @param startDate     开始时间
     * @param endDate       结束时间
     * @param price         价格
     * @param isPresell     是否预售    0否，1是
     * @param isRefund      是否可72小时无理由退款    0否，1是
     * @param startCostTime 开始消费时间
     * @param endCostTime   结束消费时间
     * @param contact       联系方式
     * @param notice        商家公告
     * @param imgs          权益包图片
     */
    public void handleImgs(String storyId, String name, String des, String categoryId, String type, String useNum, String quantity, String availablenum, String startDate, String endDate, String price, String isPresell, String isRefund, String startCostTime, String endCostTime, String contact, String isHolidayAvalable, String consumeWeekStart, String consumeWeekEnd, String notice, String currentUseType, String currentType, List<String> imgs) {
        if (imgs == null) {
            return;
        }
        Observable.just(imgs).map(s -> {
            List<String> list = new ArrayList<>();
            if (VerificationUtil.noEmpty(s)) {
                for (String str : s) {
                    byte[] bytes = BitmapUtil.decodeThumbBitmapByteForFile(str, BitmapUtil.DEFAULT_WIDTH, BitmapUtil.DEFAULT_HEIGHT, true);
                    File cacheDir = AppContext.getInstanceBase().getCacheDir();
                    File saveDir = new File(cacheDir, Constants.ROOT_DIR);
                    if (!saveDir.exists()) {
                        saveDir.mkdirs();
                    }
                    String fileName = str.substring(str.lastIndexOf("/") + 1);
                    File file = new File(saveDir, fileName);
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(bytes);
                    fos.flush();
                    fos.close();
                    list.add(file.getAbsolutePath());
                }
            }
            return list;
        }).compose(RxSchedules.ioToMain()).subscribe(new Observer<List<String>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(List<String> list) {
                publish(storyId, name, des, categoryId, type, useNum, quantity, availablenum, startDate, endDate, price, isPresell, isRefund, startCostTime, endCostTime, contact, isHolidayAvalable, consumeWeekStart, consumeWeekEnd, notice, currentUseType, currentType, AppContext.getInstanceBase().getStoryUserBean().getZIACCOUNT(), list);
            }

            @Override
            public void onError(Throwable e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
                HttpFailBean httpFailBean = new HttpFailBean(1, "图片上传失败");
                failRes.setValue(httpFailBean);
            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 发布权益包
     *
     * @param storyId           店铺id
     * @param name              权益包名称
     * @param des               权益包描述
     * @param categoryId        商品类型id
     * @param type              权益包类型；1普通，2拼团
     * @param useNum            使用人数
     * @param quantity          总数
     * @param availablenum      使用次数
     * @param startDate         开始时间
     * @param endDate           结束时间
     * @param price             价格
     * @param isPresell         是否预售    0否，1是
     * @param isRefund          是否可72小时无理由退款    0否，1是
     * @param startCostTime     开始消费时间
     * @param endCostTime       结束消费时间
     * @param contact           联系方式
     * @param isHolidayAvalable 是否节假日可用 0否，1是
     * @param consumeWeekStart  一周内可开始消费的周日期
     * @param consumeWeekEnd    一周内结束消费的周日期
     * @param notice            商家公告
     * @param account           发布者帐号
     * @param imgs              权益包图片
     */
    private void publish(String storyId, String name, String des, String categoryId, String type, String useNum, String quantity, String availablenum, String startDate, String endDate, String price, String isPresell, String isRefund, String startCostTime, String endCostTime, String contact, String isHolidayAvalable, String consumeWeekStart, String consumeWeekEnd, String notice, String currentUseType, String currentType, String account, List<String> imgs) {
        dataSource.publishInterest(storyId, name, des, categoryId, type, useNum, quantity, availablenum, startDate, endDate, price, isPresell, isRefund, startCostTime, endCostTime, contact, isHolidayAvalable, consumeWeekStart, consumeWeekEnd, notice, currentUseType, currentType, account, imgs).subscribe(new ApiCallback<ResponseBean>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                failRes.setValue(httpFail);
            }

            @Override
            public void onNext(ResponseBean responseBean) {
                resSucc.setValue(responseBean);
            }
        });
    }
}
