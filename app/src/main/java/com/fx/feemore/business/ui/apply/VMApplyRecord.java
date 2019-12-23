package com.fx.feemore.business.ui.apply;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.StoryUserBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import javax.inject.Inject;

/**
 * function:申请记录ViewModel
 * author: frj
 * modify date: 2018/12/24
 */
public class VMApplyRecord extends ViewModel {
    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();
    public MutableLiveData<StoryUserBean> resStoryUserBean = new MutableLiveData<>();


    @Inject
    public VMApplyRecord(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 刷新店铺信息
     *
     * @param storyId 店铺id
     * @param account 当前登录帐号
     */
    public void refreshStoryInfo(String storyId, String account) {
        dataSource.refreshStoryInfo(storyId, account).subscribe(new ApiCallback<StoryUserBean>() {
            @Override
            public void onFail(HttpFailBean httpFail) {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(StoryUserBean storyUserBean) {
                if (storyUserBean != null && !TextUtils.isEmpty(storyUserBean.getSTORE_ID())) {
                    AppContext.getInstanceBase().setStoryUserBean(storyUserBean);
                }
                resStoryUserBean.setValue(storyUserBean);
            }
        });
    }
}
