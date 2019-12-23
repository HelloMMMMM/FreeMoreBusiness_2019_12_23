package com.fx.feemore.business.ui.merchant.team.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.OrderBean;
import com.fx.feemore.business.bean.TeamIncomeRes;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:我的团队-推荐列表
 * author: frj
 * modify date: 2019/1/22
 */
public class VMTeamLevel extends ViewModel
{

    public static final int LEVEL_ONE = 1;
    public static final int LEVEL_TWO = 2;

    private DataSource dataSource;
    public int currPage;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();

    public MutableLiveData<TeamIncomeRes> resTeamIncome = new MutableLiveData<>();


    @Inject
    public VMTeamLevel(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取邀请收益数据
     *
     * @param page     页码
     * @param pageSize 页数据大小
     * @param level    邀请级别
     */
    public void getData(int page, int pageSize, int level)
    {
        dataSource.getTeamIncomes(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), page, pageSize, level).subscribe(new ApiCallback<TeamIncomeRes>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(TeamIncomeRes teamIncomeRes)
            {
                currPage = page;
                resTeamIncome.setValue(teamIncomeRes);
            }
        });
    }
}
