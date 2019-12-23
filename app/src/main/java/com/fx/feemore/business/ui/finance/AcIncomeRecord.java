package com.fx.feemore.business.ui.finance;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcIncomeRecordBinding;
import com.fx.feemore.business.ui.finance.adapter.AdIncomeRecord;
import com.fx.feemore.business.ui.finance.vm.VMIncomeRecord;
import com.fx.feemore.business.util.CompatiblePopupwindow;
import com.fx.feemore.business.util.LoadManageUtil;
import com.hengxian.baselib.base.BaseBindActivity;
import com.hengxian.common.DateUtils;
import com.hengxian.common.ToastUtil;

import java.util.Calendar;

/**
 * function:收益记录
 * author: frj
 * modify date: 2018/12/31
 */
public class AcIncomeRecord extends BaseBindActivity<AcIncomeRecordBinding, VMIncomeRecord> implements SwipeRefreshLayout.OnRefreshListener
{

    private TimePickerView timePickerView;
    private CompatiblePopupwindow popupwindow;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_income_record;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle("收入记录");

        initLiveData();

        binding.swipeRefreshLayout.setOnRefreshListener(this);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AdIncomeRecord adapter = new AdIncomeRecord();
        adapter.bindToRecyclerView(binding.recyclerView);
        adapter.setOnLoadMoreListener(() ->
                viewModel.getData(viewModel.currPage + 1, PAGE_NUM), binding.recyclerView);
        binding.tvDate.setOnClickListener(v -> {
            if (timePickerView == null)
            {
                timePickerView = createTimePicker();
            }
            timePickerView.show();
        });
//        binding.tvType.setOnClickListener(v -> {
//            if (popupwindow == null)
//            {
//                initPop();
//            }
//            popupwindow.showAsDropDown(binding.toolbar);
//        });

        binding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initLiveData()
    {
        viewModel.resHttpFail.observe(this, httpFailBean -> {
            if (binding.swipeRefreshLayout != null)
            {
                binding.swipeRefreshLayout.setRefreshing(false);
            }
            if (httpFailBean != null)
            {
                ToastUtil.show(httpFailBean.getText());
            }
        });
        viewModel.resRecords.observe(this, list -> LoadManageUtil.handleLoadData(binding.swipeRefreshLayout, (AdIncomeRecord) binding.recyclerView.getAdapter(), viewModel.currPage, list));
    }

    /**
     * 创建日期选择器
     */
    private TimePickerView createTimePicker()
    {
        Calendar calendar = Calendar.getInstance();
        //往前最多5年
        calendar.set(calendar.get(Calendar.YEAR) - 5, calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        //默认选中
        Calendar defaultCalendar = Calendar.getInstance();
        //默认上个月
        defaultCalendar.set(defaultCalendar.get(Calendar.YEAR), defaultCalendar.get(Calendar.MONTH) - 1, defaultCalendar.get(Calendar.DAY_OF_MONTH));
        return new TimePickerBuilder(this, (date, v) -> {
            if (binding.tvDate != null)
            {
                String time = DateUtils.DateToStr(date, "yyyy-MM-dd");
                binding.tvDate.setText(time);
                viewModel.startDate = time;
                binding.swipeRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setCancelText("取消")
                .setSubmitText("确认")
                .setCancelColor(Color.parseColor("#030303"))
                .setSubmitColor(Color.parseColor("#030303"))
                .setTextColorOut(Color.parseColor("#999999"))
                .setTextColorCenter(Color.parseColor("#666666"))
                .setRangDate(calendar, Calendar.getInstance())
                .setDate(defaultCalendar)
                .setTitleSize(18)
                .setContentTextSize(15)
                .setLineSpacingMultiplier(2.0f)
//                .isDialog(true)
                .isCyclic(true)
                .isCenterLabel(true)
                .build();
    }

    /**
     * 初始化Pop
     */
    private void initPop()
    {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_income_record_type, null);
        view.findViewById(R.id.fl_content).setOnClickListener(v -> popupwindow.dismiss());
        View.OnClickListener onClickListener = v -> {
            popupwindow.dismiss();
//            binding.tvType.setText(((TextView) v).getText());
        };
        view.findViewById(R.id.tv_all).setOnClickListener(onClickListener);
        view.findViewById(R.id.tv_month).setOnClickListener(onClickListener);
        view.findViewById(R.id.tv_year).setOnClickListener(onClickListener);
        view.findViewById(R.id.tv_custom).setOnClickListener(onClickListener);
        popupwindow = new CompatiblePopupwindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupwindow.setFocusable(true);
        popupwindow.setOutsideTouchable(true);
        popupwindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void onRefresh()
    {
        viewModel.getData(PAGE_START, PAGE_NUM);
    }
}
