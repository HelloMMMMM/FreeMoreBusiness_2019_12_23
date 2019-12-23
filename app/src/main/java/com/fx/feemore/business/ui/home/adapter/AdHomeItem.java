package com.fx.feemore.business.ui.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fx.feemore.business.base.FragmentViewPagerBase;

import java.util.List;

/**
 * function:首页的ViewPage项的适配器
 * author: frj
 * modify date: 2018/12/23
 */
public class AdHomeItem extends FragmentPagerAdapter
{
    private List<FragmentViewPagerBase> fragments;
    private String[] tabs;

    public AdHomeItem(FragmentManager fm, List<FragmentViewPagerBase> fragments, String[] tabs)
    {
        super(fm);
        this.fragments = fragments;
        this.tabs = tabs;
    }

    @Override
    public Fragment getItem(int i)
    {
        return fragments != null ? fragments.get(i) : null;
    }

    @Override
    public int getCount()
    {
        return fragments != null ? fragments.size() : 0;
    }

    public List<FragmentViewPagerBase> getFragments()
    {
        return fragments;
    }

    public void setFragments(List<FragmentViewPagerBase> fragments)
    {
        this.fragments = fragments;
    }


    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabs != null ? tabs[position] : null;
    }
}
