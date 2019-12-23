package com.fx.feemore.business.ui.apply;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.baoyachi.stepview.bean.StepBean;
import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.bean.ApplyRecordBean;
import com.fx.feemore.business.databinding.AcApplyRecordBinding;
import com.fx.feemore.business.ui.character.AcCharacterSelected;
import com.fx.feemore.business.ui.home.MainActivity;
import com.fx.feemore.business.util.JPushUtil;
import com.fx.feemore.business.util.SpaceDecorationUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * function:申请记录
 * author: frj
 * modify date: 2018/12/24
 */
public class AcApplyRecord extends BaseBindActivity<AcApplyRecordBinding, VMApplyRecord> implements SwipeRefreshLayout.OnRefreshListener {
    @Override
    protected int getLayoutId() {
        return R.layout.ac_apply_record;
    }

    @Override
    protected void init() {
        binding.setVm(viewModel);
        setToolbarTitle("申请记录");

        initLiveData();

        binding.swipeRefreshLayout.setOnRefreshListener(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(SpaceDecorationUtil.getDecoration(getResources().getDimensionPixelSize(R.dimen.apply_record_item_space), true, false, true));
        AdApplyRecord adapter = new AdApplyRecord();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setNewData(getList());
        adapter.setOnItemClickListener((adapter1, view, position) -> {
//            jump(AcPayDeposit.class, false);
        });

        binding.btnLogout.setOnClickListener(v -> {
            if (isFastDoubleClick(v)) {
                return;
            }
            AppContext.getInstanceBase().setStoryUserBean(null);
            JPushUtil.jpushLogout();
            Intent intent = new Intent(this, AcCharacterSelected.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private List<ApplyRecordBean> getList() {
        List<ApplyRecordBean> list = new ArrayList<>();
        list.add(new ApplyRecordBean(getSteps(AppContext.getInstanceBase().getStoryUserBean().getSTATUS()), AppContext.getInstanceBase().getStoryUserBean().getNAME(), AppContext.getInstanceBase().getStoryUserBean().getIMG1(), AppContext.getInstanceBase().getStoryUserBean().getSTATUS(), AppContext.getInstanceBase().getStoryUserBean().getREMARK()));
        return list;
    }

    /**
     * 获取步数列表
     *
     * @param status 状态，0申请中，1通过，-1不通过
     * @return
     */
    private List<StepBean> getSteps(int status) {
        List<StepBean> steps = new ArrayList<>();
        if (status == 0) {
            steps.add(new StepBean("审核中", StepBean.STEP_CURRENT));
            steps.add(new StepBean("审核通过", StepBean.STEP_UNDO));
        } else if (status == 1) {
            steps.add(new StepBean("审核通过", StepBean.STEP_COMPLETED));
            steps.add(new StepBean("审核通过", StepBean.STEP_COMPLETED));
            jump(MainActivity.class, true);
        } else {
            steps.add(new StepBean("审核中", StepBean.STEP_COMPLETED));
            steps.add(new StepBean("审核驳回", StepBean.STEP_COMPLETED));
        }

//        steps.add(new StepBean("已缴纳保证金", StepBean.STEP_UNDO));
        return steps;
    }

    private void initLiveData() {
        viewModel.resFail.observe(this, httpFailBean ->
        {
            if (binding.swipeRefreshLayout != null) {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (httpFailBean != null) {
                ToastUtil.show(httpFailBean.getText());
            }
        });

        viewModel.resStoryUserBean.observe(this, storyUserBean -> {
            if (binding.swipeRefreshLayout != null) {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (storyUserBean != null) {
                AdApplyRecord adapter = (AdApplyRecord) binding.recyclerView.getAdapter();
                adapter.setNewData(getList());
            }
        });
    }


    @Override
    public void onRefresh() {
        viewModel.refreshStoryInfo(AppContext.getInstanceBase().getStoryUserBean().getSTORE_ID(), AppContext.getInstanceBase().getStoryUserBean().getZIACCOUNT());
    }
}
