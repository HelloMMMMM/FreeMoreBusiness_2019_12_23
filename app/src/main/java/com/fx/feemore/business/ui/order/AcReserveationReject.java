package com.fx.feemore.business.ui.order;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.fx.feemore.business.R;
import com.fx.feemore.business.databinding.AcReservationRejectBinding;
import com.fx.feemore.business.ui.order.vm.VMReservationReject;
import com.fx.feemore.business.util.CompatiblePopupwindow;
import com.hengxian.baselib.base.BaseBindActivity;

import java.util.Arrays;
import java.util.List;

/**
 * function:预约订单驳回
 * author: frj
 * modify date: 2018/12/27
 */
public class AcReserveationReject extends BaseBindActivity<AcReservationRejectBinding, VMReservationReject>
{

    private CompatiblePopupwindow popupwindow;

    @Override
    protected int getLayoutId()
    {
        return R.layout.ac_reservation_reject;
    }

    @Override
    protected void init()
    {
        binding.setVm(viewModel);
        setToolbarTitle(R.string.reservation_reject_title);
        initPop();
        binding.tvReservationRejectType.setOnClickListener(v -> {
            if (popupwindow == null)
            {
                initPop();
            }
            //13是指13个像素的阴影
            popupwindow.showAsDropDown(binding.tvReservationRejectType, binding.tvReservationRejectType.getMeasuredWidth() - getResources().getDimensionPixelSize(R.dimen.reservation_reject_type_pop_w) + 13, 0);
        });
    }

    /**
     * 初始化Popupwindow
     */
    private void initPop()
    {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_reservation_reject_type, null);
        ListView listView = view.findViewById(R.id.listView);
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.reservation_reject_types));
        AdReasonType adReasonType = new AdReasonType(this);
        adReasonType.addList(list, false);
        listView.setAdapter(adReasonType);
        listView.setOnItemClickListener((parent, view1, position, id) -> {
            AdReasonType adapter = (AdReasonType) parent.getAdapter();
            if (adapter != null)
            {
                adapter.setSelect(position);
                binding.tvReservationRejectType.setText(adapter.getItem(position));
                popupwindow.dismiss();
            }
        });
        int itemHeight = getResources().getDimensionPixelSize(R.dimen.reservation_reject_type_pop_item_h);
        //pop的高度是项高度之和再加一个项高度
        popupwindow = new CompatiblePopupwindow(view, getResources().getDimensionPixelSize(R.dimen.reservation_reject_type_pop_w), itemHeight * (list.size() + 1));
        popupwindow.setFocusable(true);
        popupwindow.setOutsideTouchable(true);
        popupwindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
