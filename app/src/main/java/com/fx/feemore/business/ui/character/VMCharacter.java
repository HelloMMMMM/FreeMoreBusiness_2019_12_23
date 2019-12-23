package com.fx.feemore.business.ui.character;

import android.arch.lifecycle.ViewModel;

import com.hengxian.baselib.utils.SharedPreferencesPlus;

import javax.inject.Inject;

/**
 * function:角色选择页ViewModel
 * author: frj
 * modify date: 2018/12/23
 */
public class VMCharacter extends ViewModel
{
    /**
     * 角色Key值
     */
    public static final String KEY_CHARACTER = "key_character";
    /**
     * 商家角色
     */
    public static final int CHARACTER_BUSINESS = 1;
    /**
     * 代理角色
     */
    public static final int CHARACTER_PROXY = 2;

    private SharedPreferencesPlus sharedPreferencesPlus;

    @Inject
    public VMCharacter(SharedPreferencesPlus sharedPreferencesPlus)
    {
        this.sharedPreferencesPlus = sharedPreferencesPlus;
    }

    /**
     * 保存角色配置
     *
     * @param character 角色值{@link VMCharacter#CHARACTER_BUSINESS},{@link VMCharacter#CHARACTER_PROXY}
     */
    public void setCharacter(int character)
    {
        sharedPreferencesPlus.putInt(KEY_CHARACTER, character);
    }

}
