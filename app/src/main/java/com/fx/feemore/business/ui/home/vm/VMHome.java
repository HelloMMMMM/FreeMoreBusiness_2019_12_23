package com.fx.feemore.business.ui.home.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.blankj.utilcode.util.ToastUtils;
import com.fx.feemore.business.bean.HomeV3MyDataBean;
import com.fx.feemore.business.bean.StoreStatisticInfoBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.ui.home.fragment.FragmentHomeV3;
import com.hengxian.baselib.bean.HttpFailBean;

import javax.inject.Inject;

/**
 * function:首页ViewModel
 * author: frj
 * modify date: 2018/12/24
 */
public class VMHome extends ViewModel {
    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();

    public MutableLiveData<StoreStatisticInfoBean> resStoreInfo = new MutableLiveData<>();

    @Inject
    public VMHome(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取店铺统计信息
     *
     * @param storeId 店铺id
     */
    public void getStoreStatisticInfo(String storeId) {
        dataSource.getStoreStatisticInfo(storeId).subscribe(new ApiCallback<StoreStatisticInfoBean>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(StoreStatisticInfoBean storeStatisticInfoBean) {
                resStoreInfo.setValue(storeStatisticInfoBean);
            }
        });
    }

    /**
     * 获取我的数据(home_v3版)
     *
     * @param storeId 店铺id
     */
    public void getMyData(String storeId, FragmentHomeV3 homeV3) {
        dataSource.myData(storeId).subscribe(new ApiCallback<HomeV3MyDataBean>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                resFail.setValue(httpFail);
                homeV3.refreshFinish();
                ToastUtils.showShort(httpFail.getText());
            }

            @Override
            public void onNext(HomeV3MyDataBean homeV3MyDataBean) {
                homeV3.refreshData(homeV3MyDataBean);
            }
        });
    }
}
