package com.fx.feemore.business.http;

import com.fx.feemore.business.location.Const;

import java.util.HashMap;

public class ServiceUrlUtil {

    private static HashMap<String, String> baseUrlMap;

    static {
        baseUrlMap = new HashMap<>();
        baseUrlMap.put("2654", "https://mhd.feixingtech.com/");
        baseUrlMap.put("270", "https://mhd.feixingtech.com/");
        baseUrlMap.put("289", "https://mhd.feixingtech.com/");
        baseUrlMap.put("218", "https://mhd.feixingtech.com/");
        baseUrlMap.put("315", "https://nanjing.mhdsh.cn/");
    }

    public static String getDefault() {
        return baseUrlMap.get(Const.CITY_CODE) == null ? "https://mhd.feixingtech.com/" : baseUrlMap.get(Const.CITY_CODE);
    }
}
