package com.fx.feemore.business.util;

import com.fx.feemore.business.app.AppContext;

/**
 * function:操作权限管理
 * author: frj
 * modify date: 2019/5/15
 */
public class PermissionUtil
{
    /**
     * 操作权限：店主
     */
    public static final int PERMISSION_STORE_MASTER = 0;
    /**
     * 操作权限：运营者
     */
    public static final int PERMISSION_STORE_OPERATOR = 1;
    /**
     * 操作权限：操作者
     */
    public static final int PERMISSION_STORE_MANIPULATOR = 2;

    /**
     * 根据需要的权限，判断当前登录用户是否拥有操作权限;例如：needPermission为1时，表示至少需要运营者权限才能操作。
     *
     * @param needPermission 操作所需权限
     * @return
     */
    public static boolean havePermission(int needPermission)
    {
        int currPermission = AppContext.getInstanceBase().getStoryUserBean().getACCTYPE();
        return currPermission <= needPermission;
    }


}
