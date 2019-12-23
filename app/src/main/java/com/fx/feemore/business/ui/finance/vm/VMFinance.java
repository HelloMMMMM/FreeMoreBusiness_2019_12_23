package com.fx.feemore.business.ui.finance.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.StoryUserBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.fx.feemore.business.util.BroadCastUtil;
import com.hengxian.baselib.bean.HttpFailBean;

import javax.inject.Inject;

/**
 * function:财务ViewModel
 * author: frj
 * modify date: 2018/12/30
 */
public class VMFinance extends ViewModel
{

    private DataSource dataSource;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();
    public MutableLiveData<StoryUserBean> resStoryUserBean = new MutableLiveData<>();

    @Inject
    public VMFinance(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 刷新店铺信息
     *
     * @param storyId 店铺id
     * @param account 当前登录帐号
     */
    public void refreshStoryInfo(String storyId,String account)
    {
        dataSource.refreshStoryInfo(storyId,account).subscribe(new ApiCallback<StoryUserBean>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(StoryUserBean storyUserBean)
            {

                if (storyUserBean != null && !TextUtils.isEmpty(storyUserBean.getSTORE_ID()))
                {
                    AppContext.getInstanceBase().setStoryUserBean(storyUserBean);
                }
                resStoryUserBean.setValue(storyUserBean);
                BroadCastUtil.sendRefreshShowStoreInfo();
            }
        });
    }
}
