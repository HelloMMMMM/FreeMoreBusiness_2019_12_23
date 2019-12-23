package com.fx.feemore.business.ui.transaction.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.fx.feemore.business.bean.VisitorBean;
import com.fx.feemore.business.http.ApiCallback;
import com.fx.feemore.business.repository.DataSource;
import com.hengxian.baselib.bean.HttpFailBean;

import java.util.List;

import javax.inject.Inject;

/**
 * function:近日交易数据-访客
 * author: frj
 * modify date: 2019/1/17
 */
public class VMTransactionVisitor extends ViewModel
{

    private DataSource dataSource;


    public int currPage;

    public MutableLiveData<HttpFailBean> resFail = new MutableLiveData<>();
    public MutableLiveData<List<VisitorBean>> resList = new MutableLiveData<>();

    @Inject
    public VMTransactionVisitor(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    /**
     * 获取访客记录
     *
     * @param storeId 店铺id
     * @param page    页码
     * @param pageNum 页数据大小
     */
    public void getData(String storeId, int page, int pageNum)
    {
        dataSource.getVisitorList(storeId, page, pageNum).subscribe(new ApiCallback<List<VisitorBean>>()
        {
            @Override
            public void onFail(HttpFailBean httpFail)
            {
                resFail.setValue(httpFail);
            }

            @Override
            public void onNext(List<VisitorBean> visitorBeans)
            {
                currPage = page;
                resList.setValue(visitorBeans);
            }
        });
    }
}
