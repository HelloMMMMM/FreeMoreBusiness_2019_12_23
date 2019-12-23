package com.fx.feemore.business.ui.merchant.team.adapter;

import com.fx.feemore.business.R;
import com.fx.feemore.business.base.BaseDataBindingAdapter;
import com.fx.feemore.business.bean.IncomeBean;
import com.fx.feemore.business.databinding.AdTeamLevelItemBinding;
import com.fx.feemore.business.util.GlideLoad;

/**
 * function:我的团队-推荐列表适配器
 * author: frj
 * modify date: 2019/1/22
 */
public class AdTeamLevelItem extends BaseDataBindingAdapter<IncomeBean, AdTeamLevelItemBinding>
{
    public AdTeamLevelItem()
    {
        super(R.layout.ad_team_level_item);
    }

    @Override
    protected void convert(AdTeamLevelItemBinding binding, IncomeBean item)
    {
        binding.setItem(item);
        GlideLoad.loadAvartar(binding.img, item.getTO_IMG());
    }
}
