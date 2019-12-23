package com.fx.feemore.business.ui.home.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.fx.feemore.business.R;
import com.fx.feemore.business.app.AppContext;
import com.fx.feemore.business.databinding.FUtilBinding;
import com.fx.feemore.business.ui.apply.AcApplyManager;
import com.fx.feemore.business.ui.apply.AcPayDeposit;
import com.fx.feemore.business.ui.commodity.AcCommodityManager;
import com.fx.feemore.business.ui.home.adapter.AdUtil;
import com.fx.feemore.business.ui.home.vm.VMHome;
import com.fx.feemore.business.ui.home.vm.VMUtilLabel;
import com.fx.feemore.business.ui.home.vm.VMUtilOption;
import com.fx.feemore.business.ui.interest.AcPublishInterestV2;
import com.fx.feemore.business.ui.luckdraw.AcLuckDraw;
import com.fx.feemore.business.ui.notify.AcNotifyManager;
import com.fx.feemore.business.ui.order.AcOrderManager;
import com.fx.feemore.business.util.AlertDialogUtil;
import com.fx.feemore.business.util.RequestAndResultCode;
import com.hengxian.baselib.base.BaseBindFragment;
import com.hengxian.common.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

public class FragmentUtil extends BaseBindFragment<FUtilBinding, VMHome> {
    @Override
    protected int getLayoutId() {
        return R.layout.f_util;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.header.getLayoutParams();
            if (params != null) {
                params.topMargin += DisplayUtil.getStatusBarHeight(getActivity());
                binding.header.setLayoutParams(params);
            }
        }

        List<MultiItemEntity> data = new ArrayList<>();
        data.add(new VMUtilLabel(getString(R.string.util_option)));
        data.add(new VMUtilOption(R.mipmap.ic_util_option_1, getString(R.string.util_option_publish_equity), 1));
        data.add(new VMUtilOption(R.mipmap.ic_util_option_2, getString(R.string.util_option_publish_luck_draw), 2));
        data.add(new VMUtilLabel(getString(R.string.util_gm)));
        data.add(new VMUtilOption(R.mipmap.ic_util_gm_1, getString(R.string.util_gm_equity), 3));
        data.add(new VMUtilOption(R.mipmap.ic_util_gm_2, getString(R.string.util_gm_luck_draw), 4));
        data.add(new VMUtilOption(R.mipmap.ic_util_gm_3, getString(R.string.util_gm_card), 5));
        data.add(new VMUtilOption(R.mipmap.ic_util_gm_4, getString(R.string.util_gm_activity), 6));
        data.add(new VMUtilLabel(getString(R.string.util_data)));
        data.add(new VMUtilOption(R.mipmap.ic_util_data_1, getString(R.string.util_data_order), 7));
        data.add(new VMUtilOption(R.mipmap.ic_util_data_2, getString(R.string.util_data_operate), 8));
        data.add(new VMUtilOption(R.mipmap.ic_util_data_3, getString(R.string.util_data_staff), 9));
        data.add(new VMUtilLabel(getString(R.string.util_operate)));
        data.add(new VMUtilOption(R.mipmap.ic_util_operate_1, getString(R.string.util_operate_business_circle), 10));
        data.add(new VMUtilOption(R.mipmap.ic_util_operate_2, getString(R.string.util_operate_up), 11));
        data.add(new VMUtilOption(R.mipmap.ic_util_operate_3, getString(R.string.util_operate_activity), 12));
        data.add(new VMUtilLabel(getString(R.string.util_other)));
        data.add(new VMUtilOption(R.mipmap.ic_util_other_1, getString(R.string.util_other_customer_service), 13));
        data.add(new VMUtilOption(R.mipmap.ic_util_other_2, getString(R.string.util_other_rate), 14));
        data.add(new VMUtilOption(R.mipmap.ic_util_other_3, getString(R.string.util_other_message), 15));
        data.add(new VMUtilOption(R.mipmap.ic_util_other_4, getString(R.string.util_other_request), 16));
        binding.utilList.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        AdUtil adUtil = new AdUtil(data);
        adUtil.setSpanSizeLookup((gridLayoutManager, position) -> data.get(position).getItemType() == AdUtil.TYPE_LABEL ? 4 : 1);
        adUtil.setOnItemClickListener((adapter, view, position) -> {
            MultiItemEntity multiItemEntity = (MultiItemEntity) adapter.getItem(position);
            if (multiItemEntity instanceof VMUtilOption) {
                VMUtilOption vmUtilOption = (VMUtilOption) multiItemEntity;
                switch (vmUtilOption.getOptionId()) {
                    case 1:
                        if (AppContext.getInstanceBase().getStoryUserBean().getMARGIN() == 0) {
                            AlertDialogUtil.create(getActivity(), getString(R.string.service_money), (dialog, which) -> jump(AcPayDeposit.class), null).show();
                            return;
                        }
                        jump(AcPublishInterestV2.class, RequestAndResultCode.REQUEST_NEED_REFRESH_CODE);
                        break;
                    case 2:
                        jump(AcLuckDraw.class);
                        break;
                    case 3:
                        Bundle bundle3 = new Bundle();
                        bundle3.putInt("from", 1);
                        jump(AcCommodityManager.class, bundle3, false);
                        break;
                    case 5:
                        Bundle bundle5 = new Bundle();
                        bundle5.putInt("from", 2);
                        jump(AcCommodityManager.class, bundle5, false);
                        break;
                    case 7:
                        jump(AcOrderManager.class);
                        break;
                    case 15:
                        jump(AcNotifyManager.class);
                        break;
                    case 16:
                        jump(AcApplyManager.class);
                        break;
                }
            }
        });
        binding.utilList.setAdapter(adUtil);
    }
}
